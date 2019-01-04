package in.hiaccounts.hinext.restaurant.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.nikoyuwono.realmbrowser.RealmBrowser;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.realm_manager.RealmManager;
import in.hiaccounts.hinext.checkout.model.BankPayment;
import in.hiaccounts.hinext.checkout.model.CardPaymentList;
import in.hiaccounts.hinext.checkout.model.CashPayment;
import in.hiaccounts.hinext.checkout.model.CreditPayment;
import in.hiaccounts.hinext.checkout.model.MultiBankPaymentList;
import in.hiaccounts.hinext.checkout.model.MultiVoucherPayment;
import in.hiaccounts.hinext.checkout.model.VoucherPayment;
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.employee.EmployeeDatum;
import in.hiaccounts.hinext.restaurant.adapter.Restrapos_Adapter1;
import in.hiaccounts.hinext.restaurant.adapter.SelectItemList_Adapter;
import in.hiaccounts.hinext.restaurant.adapter.SplittBill_Adapter;
import in.hiaccounts.hinext.restaurant.adapter.SplittBill_Edit_Adapter;
import in.hiaccounts.hinext.restaurant.model.category_item.SelectedItemsList;
import in.hiaccounts.hinext.restaurant.model.checkout.RestraCheckoutData;
import in.hiaccounts.hinext.restaurant.model.checkout.RestraCheckoutItem;
import in.hiaccounts.hinext.restaurant.model.restaurant_pagedata.HinextRestuarantPageData;
import in.hiaccounts.hinext.restaurant.model.restaurant_pagedata.SubRow;
import in.hiaccounts.hinext.restaurant.model.restra_pos.RestraPosCartItem;
import in.hiaccounts.hinext.restaurant.model.restra_pos.RestraPosCreator;
import in.hiaccounts.hinext.restaurant.model.restra_pos.RestraPosHelper;
import in.hiaccounts.model.realm_model.data.Realm_Checkout_SplitTable;
import in.hiaccounts.model.realm_model.data.Realm_SelectItemList;
import in.hiaccounts.model.realm_model.data.Realm_SplitTable;
import in.hiaccounts.model.realm_model.utils.DataGenerator;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;

public class Activity_SplitBill extends AppCompatActivity {
    public static final String TAG = Activity_SplitBill.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.listViewSelecItems)
    ListView listViewSelecItems;
    @BindView(R.id.llListview)
    LinearLayout llListview;
    @BindView(R.id.listViewSplittItems)
    ListView listViewSplittItems;
    @BindView(R.id.llListviewSplitt)
    LinearLayout llListviewSplitt;
    @BindView(R.id.lllist)
    LinearLayout lllist;
    @BindView(R.id.btnClose)
    Button btnClose;
    @BindView(R.id.btnCheckout)
    Button btnCheckout;
    RestraPosCreator splitPosCreator;
    RestraPosCreator splitCheckoutPosCreator;
    Activity mActivity;
    SplittBill_Adapter splitPosAdapter;
    SplittBill_Edit_Adapter splitCheckoutPosAdapter;
    String checkoutType = "";
    Long  customerId;
    SubRow tableData;
    private EmployeeDatum employeeDetail;
    private boolean bluetoothStatus;
    CashPayment cashPayment = new CashPayment();
    private List<CardPaymentList> cardPaymentList = new ArrayList<CardPaymentList>();
    private List<MultiVoucherPayment> voucherPaymentList = new ArrayList<MultiVoucherPayment>();
    private List<MultiBankPaymentList> bankPaymentList = new ArrayList<MultiBankPaymentList>();
    private RestraCheckoutData getCheckout;
    private Boolean splitBillStatus=false;
    String WaiterName="",clearSplitOrders="";
    List<SelectedItemsList> posCartTableItems;
    List<SelectedItemsList> chekoutCartTableItems;
    private RealmBrowser realmBrowser;
    private String serverUrl;
    private ServiceHandler serviceHandler;
    private SharedPreference sharedPreference;
    private Dialog dialog;
    private Spinner spinQty;
     long changeQty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splitbill);
        ButterKnife.bind(this);

        initComponentView();
    }

    private void initComponentView() {
        mActivity=Activity_SplitBill.this;
        toolbar.setTitle(getResources().getString(R.string.menu_splitbill));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        setSupportActionBar(toolbar);


        serverUrl = UtilView.getUrl(mActivity);
        serviceHandler = new ServiceHandler(mActivity);
        sharedPreference = SharedPreference.getInstance(mActivity);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
        RealmManager.open();
        UtilView.showLogCat("@Flow", "relam " + RealmManager.open().getPath());
        dialog = new Dialog(mActivity);

        customerId = getIntent().getLongExtra("customerId",0);
        tableData = (SubRow) getIntent().getSerializableExtra("tableData");
        employeeDetail = (EmployeeDatum) getIntent().getSerializableExtra("employeeDetail");
       // clearSplitOrders = sharedPreference.getData("splitBill");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        checkSplitTable1();
        listViewSelecItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
              Realm_SplitTable splittItem  = RealmManager.createRestaurentDao().getRestSingleSplittItem(posCartTableItems.get(position));
                if (splittItem!=null){
                        String itemCode = splittItem.getItemCode();
                        RealmManager.createRestaurentDao().saveRestaSplitCheckoutItem(DataGenerator.generateRestaSplitCheckoutItem(splittItem,tableData,itemCode),itemCode,tableData);
                        RealmManager.createRestaurentDao().deletRealmSplitOrders(splittItem);
                        checkSplitTable1();
                        checkCheckoutSplitTable();
                }
            }
        });

        listViewSelecItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (posCartTableItems.get(i)!=null){
                    Realm_SelectItemList selctData =  RealmManager.createRestaurentDao().getItemCodeDetails(posCartTableItems.get(i).getItemCode(),posCartTableItems.get(i).getTableId(), posCartTableItems.get(i).getFloorId());
                    Log.e("itemQtyrealm", String.valueOf(selctData.getQty()));
                    callEditCheckoutQty(selctData,posCartTableItems.get(i));
                }

                return true;
            }
        });


        listViewSplittItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Realm_Checkout_SplitTable chekoutCart  = RealmManager.createRestaurentDao().getRestSingleCheckoutSplittItem(chekoutCartTableItems.get(position));
                if (chekoutCart!=null ){
                    String itemCode = chekoutCart.getItemCode();
                    Realm_SelectItemList selctData =  RealmManager.createRestaurentDao().getItemCodeDetails(chekoutCartTableItems.get(position).getItemCode(),chekoutCartTableItems.get(position).getTableId(), chekoutCartTableItems.get(position).getFloorId());
                    RealmManager.createRestaurentDao().saveRestaurentSplitItem(DataGenerator.generateRestaSplitItem(selctData,tableData,itemCode),itemCode,tableData);
                    RealmManager.createRestaurentDao().deletRealmCheckoutSplitOrders(chekoutCart);
                    checkCheckoutSplitTable();
                    checkSplitTable1();
                }

            }
        });
    }

    private void callEditCheckoutQty(Realm_SelectItemList selectedItemsList, SelectedItemsList itemsList) {
        dialog.setContentView(R.layout.dialog_restaurent_splitbill_editqty);
        Button btnClose = dialog.findViewById(R.id.btnClose);
        Button btnSave = dialog.findViewById(R.id.btnSave);
        spinQty = dialog.findViewById(R.id.spinQty);
        List<Long> itemQty = new ArrayList<Long>();
        if (selectedItemsList!=null && selectedItemsList.getQty()!=0){
            for (long i=1;i<=selectedItemsList.getQty();i++){
                itemQty.add(i);
            }
        }

        ArrayAdapter<Long> dataAdapter = new ArrayAdapter<Long>(this, android.R.layout.simple_spinner_item, itemQty);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinQty.setAdapter(dataAdapter);

        spinQty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                changeQty = (long) spinQty.getSelectedItem();
                Realm_SelectItemList selctData =  RealmManager.createRestaurentDao().getItemCodeDetails(selectedItemsList.getItemCode(),selectedItemsList.getTableId(), selectedItemsList.getFloorId());
                Log.e("itemQtyrealm", String.valueOf(selctData.getQty()));
                RealmManager.createRestaurentDao().saveRestaSplitCheckoutItem(DataGenerator.generateRestaSplitEditCheckoutItem(selectedItemsList,tableData,selectedItemsList.getItemCode(),changeQty),selectedItemsList.getItemCode(),tableData);


                Long  quantity =  (selctData.getQty() - changeQty);
                Log.e("current", String.valueOf(quantity));
                Log.e("prev", String.valueOf(selectedItemsList.getQty()));
                if (selctData.getQty()>quantity){
                    Log.e("test", "less");
                    RealmManager.createRestaurentDao().updateRestSplitTableData(selectedItemsList,quantity);

                }
                if (selctData.getQty() == changeQty){
                    Log.e("test", "equal");
                    RealmManager.createRestaurentDao().deletRealmSplitOrdersforKot(itemsList);
                    checkSplitTable1();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        if (dialog != null) {
            dialog.show();
        }
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity_SplitBill)mActivity).checkCheckoutSplitTable();
                ((Activity_SplitBill)mActivity).checkSplitTable1();
                dialog.dismiss();
            }
        });

    }



    public void checkCheckoutSplitTable() {
        chekoutCartTableItems  = RealmManager.createRestaurentDao().getRestSplittCheckoutItemList(tableData);
        if (chekoutCartTableItems != null && chekoutCartTableItems.size() > 0) {
            splitCheckoutPosAdapter = new SplittBill_Edit_Adapter(mActivity, chekoutCartTableItems);
            try {
                listViewSplittItems.smoothScrollToPosition(chekoutCartTableItems.size());
                if (listViewSplittItems != null)
                    listViewSplittItems.setAdapter(splitCheckoutPosAdapter);
                splitCheckoutPosAdapter.notifyDataSetChanged();
            } catch (Exception e) {
                UtilView.showLogCat(TAG, "checkCartList cart Exception " + e.toString());
            }

            if (llListview != null)
                llListview.setVisibility(View.VISIBLE);
        }else{
            splitCheckoutPosAdapter = new SplittBill_Edit_Adapter(mActivity, chekoutCartTableItems);
            try {
                listViewSplittItems.smoothScrollToPosition(chekoutCartTableItems.size());
                if (listViewSplittItems != null)
                    listViewSplittItems.setAdapter(splitCheckoutPosAdapter);
                splitCheckoutPosAdapter.notifyDataSetChanged();
            } catch (Exception e) {
                UtilView.showLogCat(TAG, "checkCartList cart Exception " + e.toString());
            }
        }
    }

    public void checkSplitTable1() {
        posCartTableItems  = RealmManager.createRestaurentDao().getRestSplittItemList(tableData);
        if (posCartTableItems!=null && posCartTableItems.size()>0){
            splitPosAdapter = new SplittBill_Adapter(mActivity, posCartTableItems);
            try {
                listViewSelecItems.smoothScrollToPosition(posCartTableItems.size());
                if (listViewSelecItems != null)
                    listViewSelecItems.setAdapter(splitPosAdapter);
                splitPosAdapter.notifyDataSetChanged();

            } catch (Exception e) {
                UtilView.showLogCat(TAG, "checkCartList cart Exception " + e.toString());
            }

            if (llListview != null)
                llListview.setVisibility(View.VISIBLE);
        }else{

            splitPosAdapter = new SplittBill_Adapter(mActivity, posCartTableItems);
            try {
                listViewSelecItems.smoothScrollToPosition(posCartTableItems.size());
                if (listViewSelecItems != null)
                    listViewSelecItems.setAdapter(splitPosAdapter);
                splitPosAdapter.notifyDataSetChanged();

            } catch (Exception e) {
                UtilView.showLogCat(TAG, "checkCartList cart Exception " + e.toString());
            }
        }
    }


    @OnClick({R.id.btnClose, R.id.btnCheckout})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.btnClose:
                finish();
                break;
            case R.id.btnCheckout:
                callCheckout();
                break;

        }
    }

    private void callCheckout() {
        chekoutCartTableItems  = RealmManager.createRestaurentDao().getRestSplittCheckoutItemList(tableData);
        if (chekoutCartTableItems != null  && chekoutCartTableItems.size() > 0) {
            Intent intent = new Intent(mActivity, Activity_RestraPayment1.class);
            intent.putExtra("checkoutData", getCheckoutData(chekoutCartTableItems));
            checkoutType = "multiPayment";
            intent.putExtra("checkoutType", checkoutType);
            intent.putExtra("splitBillStatusData", true);
            startActivityForResult(intent, Constant.RESQUSTCODE_SPLITTBILLCHECKOUT);
        } else {
            UtilView.showToast(mActivity, "No item in Cart. Please add item.");
        }
    }

    private RestraCheckoutData getCheckoutData(List<SelectedItemsList> chekoutCartTableItems) {
        RestraCheckoutData data = new RestraCheckoutData();
        List<RestraCheckoutItem> itemList = new ArrayList<>();
        double totalTaxAmt = 0;
        double totalAmt = 0;
        if (chekoutCartTableItems != null && chekoutCartTableItems.size() > 0) {
            for (int i = 0; i < chekoutCartTableItems.size(); i++) {
                RestraCheckoutItem item = new RestraCheckoutItem();
                item.setItemCode(chekoutCartTableItems.get(i).getItemCode());
                item.setItemId(chekoutCartTableItems.get(i).getItemId());
                item.setItemName(chekoutCartTableItems.get(i).getItemName());
                item.setUnitPrice(chekoutCartTableItems.get(i).getUnitPrice());
                item.setGstItemTax(chekoutCartTableItems.get(i).getGstTaxAmt());
                item.setAmtexclusivetax(chekoutCartTableItems.get(i).getItemTotalAmountExTax());
                item.setAmtinclusivetax(chekoutCartTableItems.get(i).getItemTotalAmountInTax());
                item.setQty(chekoutCartTableItems.get(i).getItemQuantity());
                item.setTaxamt(chekoutCartTableItems.get(i).getGstTaxAmt());
                item.setGstTaxPercantage(chekoutCartTableItems.get(i).getGstTaxPercantage());
                item.setItemCategoryId(chekoutCartTableItems.get(i).getItemCategoryId());
                item.setItemCategoryName(chekoutCartTableItems.get(i).getItemCategoryName());
                item.setInclusiveJSON(chekoutCartTableItems.get(i).getInclusiveJSON());
                item.setTaxId(chekoutCartTableItems.get(i).getTaxId());
                item.setUom(chekoutCartTableItems.get(i).getUom());
                item.setTaxid(chekoutCartTableItems.get(i).getOutputTaxId());
                item.setInputTaxId(chekoutCartTableItems.get(i).getInputTaxId());
                item.setOutputTaxId(chekoutCartTableItems.get(i).getOutputTaxId());
                item.setItemTypeId(chekoutCartTableItems.get(i).getItemTypeId());
                item.setItemTypeName(chekoutCartTableItems.get(i).getItemTypeName());
                item.setTableId(chekoutCartTableItems.get(i).getTableId());
                item.setFloorId(chekoutCartTableItems.get(i).getFloorId());
                item.setFlag(false);
                item.setWaiterName(chekoutCartTableItems.get(i).getWaiterName());
                totalTaxAmt += chekoutCartTableItems.get(i).getGstTaxAmt();
                totalAmt += chekoutCartTableItems.get(i).getUnitPrice()*item.getQty();
                itemList.add(item);
                WaiterName = item.getWaiterName();
            }
            List<in.hiaccounts.hinext.checkout.model.MultiCashPaymentList> multiCashPaymentLists = new ArrayList<>();
            in.hiaccounts.hinext.checkout.model.MultiCashPaymentList cashPaymentList = new in.hiaccounts.hinext.checkout.model.MultiCashPaymentList();
            cashPaymentList.setCashAmt(0.00);
            cashPaymentList.setPaymentType(1L);
            multiCashPaymentLists.add(cashPaymentList);
            cashPayment.setMultiCashPaymentList(multiCashPaymentLists);

            CreditPayment creditPayment = new CreditPayment();
            creditPayment.setCardPaymentList(cardPaymentList);

            VoucherPayment voucherPayment = new VoucherPayment();
            voucherPayment.setMultiVoucherPayments(voucherPaymentList);

            BankPayment bankPayment = new BankPayment();
            bankPayment.setMultiBankPaymentList(bankPaymentList);
            data.setRoundingOffValue("0.00");
            data.setCashPayment(cashPayment);
            data.setBankPayment(bankPayment);
            data.setVoucherPayment(voucherPayment);
            data.setCreditPayment(creditPayment);
            data.setTaxType("CGST:SGST/UGST");
            data.setPaymentType("multiPayment");
            data.setCustomerId(customerId);
            data.setSelectedItemsList(itemList);
            data.setTotalCheckOutamt(totalAmt);
            data.setNotificationAppend(false);
            data.setTotalTaxAmt(totalTaxAmt);
            bluetoothStatus = true;
            data.setBluetoothStatus(bluetoothStatus);
            if (tableData != null) {
                data.setTableVal(tableData.getTableId());
                data.setTableName(tableData.getTableName());
            }

            Log.e("WaiterName",WaiterName);
            data.setWaiterName(WaiterName);


        }
        return data;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.RESQUSTCODE_SPLITTBILLCHECKOUT && resultCode == RESULT_OK) {
            splitBillStatus = getIntent().getBooleanExtra("splitBillPay",false);
            getCheckout = (RestraCheckoutData) data.getSerializableExtra("paidTableName");
            updateSplitCheckout(getCheckout);

        }
    }

    private void updateSplitCheckout(RestraCheckoutData getCheckout) {
        posCartTableItems = RealmManager.createRestaurentDao().getRestuarentItemList(tableData);
        if (posCartTableItems!=null && posCartTableItems.size()>0){
            for (int ck=0;ck<posCartTableItems.size();ck++){
                if (getCheckout.getSelectedItemsList() != null && getCheckout.getSelectedItemsList().size() > 0) {
                    for (int i = 0; i < getCheckout.getSelectedItemsList().size(); i++) {

                        if (posCartTableItems.get(ck).getItemCode().equals(getCheckout.getSelectedItemsList().get(i).getItemCode()) ){
                            if (posCartTableItems.get(ck).getQty().equals(getCheckout.getSelectedItemsList().get(i).getQty())){

                                Log.e("splittablechekoutName", posCartTableItems.get(ck).getItemName());
                                RealmManager.createRestaurentDao().deletRealmDataAll(getCheckout.getSelectedItemsList().get(i));
                                RealmManager.createRestaurentDao().deletRealmTempDataAll(getCheckout.getSelectedItemsList().get(i));
                                RealmManager.createRestaurentDao().deletRealmTempOrderDataAllTable();


                            }else{
                                long qty =0;
                                qty = posCartTableItems.get(ck).getItemQuantity() - getCheckout.getSelectedItemsList().get(i).getQty();
                                Log.e("splittableposcartName", posCartTableItems.get(ck).getItemName());
                                Log.e("splittableposcartQty", String.valueOf(posCartTableItems.get(ck).getItemQuantity()));
                                Log.e("splittableremainQty", String.valueOf(qty));
                                RealmManager.createRestaurentDao().updateRestSplitRemainingOrderData(posCartTableItems.get(ck),qty);
                                //RealmManager.createRestaurentDao().updateRestSplitRemainingOrderToTempData(posCartTableItems.get(ck),qty);
                            }
                        }
                    }

                    Intent returnIntent = new Intent();
                    setResult(Activity.RESULT_OK, returnIntent);
                    mActivity.finish();
                }

            }

        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        UtilView.showLogCat(TAG, "onStart");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

       // RealmManager.createRestaurentDao().deletRealmSplitTableAllData();
       // RealmManager.createRestaurentDao().deletRealmSplitCheckoutTableAllData();
    }
    @Override
    protected void onResume() {
        super.onResume();

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        UtilView.showLogCat(TAG, "onResume");
        realmBrowser = new RealmBrowser();
        realmBrowser.start();
        realmBrowser.showServerAddress(mActivity);
    }

    @Override
    protected void onPause() {
        super.onPause();
        UtilView.showLogCat(TAG, "onPause");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        UtilView.showLogCat(TAG, "onReStart");
    }
    @Override
    protected void onStop() {
        super.onStop();
        UtilView.showLogCat(TAG, "onStop");
        if (realmBrowser != null) {
            realmBrowser.stop();
        }
    }
}
