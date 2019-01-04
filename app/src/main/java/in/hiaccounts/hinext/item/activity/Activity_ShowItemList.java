package in.hiaccounts.hinext.item.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.rey.material.app.Dialog;
import com.rey.material.widget.ProgressView;
import com.yinglan.keyboard.HideUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.model.PageLoadDataForAll;
import in.hiaccounts.hinext.customer.model.Customer;
import in.hiaccounts.hinext.inventory.model.inventory_pos.InventoryPosCartItem;
import in.hiaccounts.hinext.inventory.model.inventory_pos.InventoryPosCreator;
import in.hiaccounts.hinext.inventory.model.inventory_pos.InventoryPosHelper;
import in.hiaccounts.hinext.inventory.model.inventory_pos.InventorySelectItemData;
import in.hiaccounts.hinext.item.adapter.GetItemAdapter;
import in.hiaccounts.hinext.item.model.SelectedItemsList;
import in.hiaccounts.hinext.purchase.model.purchase_pagedata.HinextPurchasePageData;
import in.hiaccounts.hinext.purchase.model.purchase_pagedata.TaxList;
import in.hiaccounts.hinext.purchase.model.purchase_pos.PurchasePosCartItem;
import in.hiaccounts.hinext.purchase.model.purchase_pos.PurchasePosCreator;
import in.hiaccounts.hinext.purchase.model.purchase_pos.PurchasePosHelper;
import in.hiaccounts.hinext.sales.model.sales_pagedata.HinextSalesPageData;
import in.hiaccounts.hinext.sales.model.sales_pos.SalesPosCartItem;
import in.hiaccounts.hinext.sales.model.sales_pos.SalesPosCreator;
import in.hiaccounts.hinext.sales.model.sales_pos.SalesPosHelper;
import in.hiaccounts.hinext.service.model.service_pos.ServicePosCartItem;
import in.hiaccounts.hinext.service.model.service_pos.ServicePosCreator;
import in.hiaccounts.hinext.service.model.service_pos.ServicePosHelper;
import in.hiaccounts.hinext.supplier.model.GetSupplier;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 3/20/2017.
 */

public class Activity_ShowItemList extends AppCompatActivity implements TextWatcher {
    public static String TAG = Activity_ShowItemList.class.getSimpleName();
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.listview_items)
    ListView listView;
    @BindView(R.id.ed_searchitem)
    EditText edSearchitem;
    @BindView(R.id.imgview_search)
    ImageView imageviewSearch;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_scanitembarcode)
    TextView tv_scanitembarcode;
    @BindView(R.id.llListview)
    LinearLayout llListview;
    InventoryPosCreator inventoryPosCreator;
    private GetItemAdapter itemAdapter;
    private PurchasePosCreator purchasePosCreator;
    private SalesPosCreator salesPosCreator;
    private ServicePosCreator servicePosCreator;
    private Boolean isInternetPresent = false;
    private SharedPreference sharedPreference;
    private String itemSearch = "";
    private String url = "";
    private List<Object> list = new ArrayList<Object>();
    private Activity activity;
    private String callingFrom, selectedTaxType;
    private Customer selectedCustomer = null;
    private GetSupplier selectedsupplier = null;
    private ServiceHandler serviceHandler;
    private String serverUrl,itemCodeId="";
    private  List<String> codeid = new ArrayList<>();
    private List<InventoryPosCartItem> posCartItem;
    private Dialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_select);
        initCompenents();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                UtilView.hideSoftKeyboard(activity, edSearchitem);



                int position = i;
                Object ob = list.get(i);
                if (ob instanceof SelectedItemsList) {
                    SelectedItemsList itemDatum = (SelectedItemsList) ob;
                    isInternetPresent = serviceHandler.isConnectingToInternet();
                    if (isInternetPresent) {
                        if (callingFrom.equals(Constant.NAVIGATION_GROUP_RETAIL)) {

                            if (serverUrl != null) {
                                String url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETITEM + "?itemCode=" + itemDatum.getItemCode().replace(" ", "%20");
                                UtilView.showProgessBar(activity, progressBar);
                                GetDataTask task = new GetDataTask(Activity_ShowItemList.this, new GetSerizableItemDetailListener(itemDatum.getItemCode()), false);
                                task.execute(url, "");
                            } else {
                                UtilView.gotToLogin(activity);
                            }
                        }

                        if (callingFrom.equals(Constant.NAVIGATION_GROUP_PURCHASE)) {
                            if (serverUrl != null) {
                                String url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETITEM + "?itemCode=" + itemDatum.getItemCode().replace(" ", "%20");
                                UtilView.showProgessBar(activity, progressBar);
                                GetDataTask task = new GetDataTask(Activity_ShowItemList.this, new GetSerizableItemDetailListener(itemDatum.getItemCode()), false);
                                task.execute(url, "");
                            } else {
                                UtilView.gotToLogin(activity);
                            }
                        }
                        if (callingFrom.equals(Constant.NAVIGATION_GROUP_SERVICE)) {

                            if (serverUrl != null) {
                                String url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETITEM + "?itemCode=" + itemDatum.getItemCode().replace(" ", "%20");
                                UtilView.showProgessBar(activity, progressBar);
                                GetDataTask task = new GetDataTask(Activity_ShowItemList.this, new GetSerizableItemDetailListener(itemDatum.getItemCode()), false);
                                task.execute(url, "");
                            } else {
                                UtilView.gotToLogin(activity);
                            }
                        }

                        if (callingFrom.equals(Constant.CALL_ADDINVENTORUOPENING_ITEM)) {
                            Intent returnIntent = new Intent();
                            returnIntent.putExtra("item", itemDatum);
                            setResult(Activity.RESULT_OK, returnIntent);
                            finish();
                        }
                    } else {
                        UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), Activity_ShowItemList.this);
                    }
                } else if (ob instanceof InventorySelectItemData) {
                    InventorySelectItemData itemDatum = (InventorySelectItemData) ob;
                    isInternetPresent = serviceHandler.isConnectingToInternet();
                    if (isInternetPresent) {



                        //UtilView.showLogCat("itemCodeId..."+i+"",itemCodeId);

                            if (itemCodeId.equals(itemDatum.getItemCode()) && itemDatum.getItemCode() !=null){
                                if (callingFrom.equals(Constant.NAVIGATION_GROUP_INVENTORY)) {

                                   // inventoryPosCreator.update(new InventoryPosCartItem(itemDatum, 1),position);
                                    Intent in = new Intent();
                                    activity.setResult(Activity.RESULT_OK, in);
                                    activity.finish();
                                }
                            }else /*if(itemCodeId.equals("") && itemDatum.getItemCode() !=null)*/{
                                if (callingFrom.equals(Constant.NAVIGATION_GROUP_INVENTORY)) {
                                    inventoryPosCreator.addItem(new InventoryPosCartItem(itemDatum, 1), itemDatum.getItemId());
                                    Intent in = new Intent();
                                    activity.setResult(Activity.RESULT_OK, in);
                                    activity.finish();
                                }
                            }


                    } else {
                        UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), Activity_ShowItemList.this);
                    }
                }

            }

            class GetSerizableItemDetailListener implements AsyncTaskCompleteListener<String> {

                public boolean discountRight = false;
                public boolean isCheckoutable = true;
                public int flag = 0;
                EditText edCessAmt;
                EditText edItemName;
                EditText edItemDescritpion;
                EditText edItemUnitPrice;
                EditText edItemQuantity;
                EditText edItemAmtExTax;
                TextView textView10;
                Spinner spinItemInputtax;
                TextView tvTaxType;
                EditText edTaxTypeAmount;
                EditText edCessPercantage;
                EditText edItemTotalTaxamt;
                EditText edItemDiscount;
                EditText itemAmtIncTax;
                Spinner spinItemSerialNumber;
                Button btnClose;
                Button btnAdd;
                Button btnMore, btnLess;
                LinearLayout llMoreOrLess;
                String itemCode;
                String taxString = "";

                BigDecimal itemTotalamt = BigDecimal.ZERO;
                BigDecimal totalTaxAmt = BigDecimal.ZERO;
                BigDecimal cessPercantage = BigDecimal.ZERO;
                BigDecimal taxPercantage = BigDecimal.ZERO;
                Gson gson = new Gson();
                SelectedItemsList itemDatum;
                String hinextpagedatajson = sharedPreference.getData(Constant.HINEXTSALESPAGEDATA_KEY);
                final HinextSalesPageData salesPageData = gson.fromJson(hinextpagedatajson.toString(), HinextSalesPageData.class);
                String hinextpurchasepagedatajson = sharedPreference.getData(Constant.HINEXTPURCHASESPAGEDATA_KEY);
                final HinextPurchasePageData purchasePageData = gson.fromJson(hinextpurchasepagedatajson.toString(), HinextPurchasePageData.class);

                String getServicePageLoadData = sharedPreference.getData(Constant.GETPAGELOADDATA_KEY);
                PageLoadDataForAll servicePageData = gson.fromJson(getServicePageLoadData,PageLoadDataForAll.class);
                public GetSerizableItemDetailListener(String itemCode) {
                    this.itemCode = itemCode;
                }

                public String getTaxString() {
                    return taxString;
                }

                private void setTaxString(String taxString) {
                    this.taxString = taxString;
                }

                @Override
                public void onTaskComplete(String result) {
                    UtilView.hideProgessBar(activity, progressBar);

                    if (result != null) {
                        try {

                            JSONArray jsonArray = new JSONArray(result.toString());
                            if (jsonArray.length() > 0) {

                                Gson gson = new Gson();
                                 itemDatum = gson.fromJson(jsonArray.getJSONObject(0).toString(), SelectedItemsList.class);
                                itemDatum.setPrice(new BigDecimal(0.0));
                                itemDatum.setSerializableNumbers("");
                                Log.e("itemDatumUnitPrice", String.valueOf(itemDatum.getUnitPrice()));


                                dialog.setContentView(R.layout.dialog_sales_positem);
                                dialog.setCancelable(false);
                                dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
                                edCessAmt = dialog.findViewById(R.id.edCessAmt);
                                edItemTotalTaxamt = dialog.findViewById(R.id.edItemTotalTaxamt);
                                spinItemSerialNumber = dialog.findViewById(R.id.spinItemSerialNumber);
                                edItemName = dialog.findViewById(R.id.edItemName);
                                edItemDescritpion = dialog.findViewById(R.id.edItemDescritpion);
                                edItemUnitPrice = dialog.findViewById(R.id.edItemUnitPrice);
                                edItemQuantity = dialog.findViewById(R.id.edItemQuantity);
                                edItemAmtExTax = dialog.findViewById(R.id.edItemAmtExTax);
                                spinItemInputtax = dialog.findViewById(R.id.spinItemInputtax);
                                tvTaxType = dialog.findViewById(R.id.tvTaxType);
                                edTaxTypeAmount = dialog.findViewById(R.id.edTaxTypeAmount);
                                edCessPercantage = dialog.findViewById(R.id.edCessPercantage);
                                edItemDiscount = dialog.findViewById(R.id.edItemDiscount);
                                itemAmtIncTax = dialog.findViewById(R.id.itemAmtIncTax);
                                edTaxTypeAmount = dialog.findViewById(R.id.edTaxTypeAmount);
                                btnAdd = dialog.findViewById(R.id.btnAdd);
                                btnClose = dialog.findViewById(R.id.btnClose);
                                btnMore = dialog.findViewById(R.id.btnMore);
                                btnLess = dialog.findViewById(R.id.btnLess);
                                llMoreOrLess = dialog.findViewById(R.id.llMoreOrLess);
                                LinearLayout llDiscount = dialog.findViewById(R.id.llDiscount);

                                tvTaxType.setText(selectedTaxType);
                                itemDatum.setTaxTypeSelection(selectedTaxType);

                                btnMore.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (llMoreOrLess != null) {
                                            llMoreOrLess.setVisibility(View.VISIBLE);
                                            btnLess.setVisibility(View.VISIBLE);
                                            btnMore.setVisibility(View.GONE);
                                        }
                                    }
                                });

                                btnLess.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (llMoreOrLess != null) {
                                            llMoreOrLess.setVisibility(View.GONE);
                                            btnMore.setVisibility(View.VISIBLE);
                                            btnLess.setVisibility(View.GONE);
                                        }
                                    }
                                });


                                if (itemDatum.getSerializableStatus() != null) {
                                    if (itemDatum.getSerializableStatus().equals(Constant.ITEM_BULK)) {

                                        edItemQuantity.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                                        edItemQuantity.setFocusableInTouchMode(true);
                                        edItemQuantity.setFocusable(true);
                                    }

                                    if (itemDatum.getSerializableStatus().equals(Constant.ITEM_SERIALIZABLE)) {

                                        edItemQuantity.setBackgroundColor(getResources().getColor(R.color.colorGrey));
                                        edItemQuantity.setFocusableInTouchMode(false);
                                        edItemQuantity.setFocusable(false);
                                    }
                                }
                                if (callingFrom.equals(Constant.NAVIGATION_GROUP_RETAIL)) {
                                    if (salesPageData.getUserRights().isDiscount()) {
                                        edItemDiscount.setFocusable(true);
                                        edItemDiscount.setFocusableInTouchMode(true);
                                    } else {
                                        edItemDiscount.setFocusable(false);
                                        edItemDiscount.setFocusableInTouchMode(false);
                                    }
                                    if (salesPageData.getUserRights().isUnitPriceEditable()) {

                                        edItemUnitPrice.setFocusable(true);
                                        edItemUnitPrice.setFocusableInTouchMode(true);
                                    } else {
                                        edItemUnitPrice.setFocusable(false);
                                        edItemUnitPrice.setFocusableInTouchMode(false);
                                    }

                                }

                                if (callingFrom.equals(Constant.NAVIGATION_GROUP_PURCHASE)) {
                                    llDiscount.setVisibility(View.GONE);

                                    if (purchasePageData.getUserRights().isDiscount()) {
                                        edItemDiscount.setFocusable(true);
                                        edItemDiscount.setFocusableInTouchMode(true);
                                    } else {
                                        edItemDiscount.setFocusable(false);
                                        edItemDiscount.setFocusableInTouchMode(false);
                                    }
                                    if (purchasePageData.getUserRights().isUnitPriceEditable()) {

                                        edItemUnitPrice.setFocusable(true);
                                        edItemUnitPrice.setFocusableInTouchMode(true);
                                    } else {
                                        edItemUnitPrice.setFocusable(false);
                                        edItemUnitPrice.setFocusableInTouchMode(false);
                                    }
                                }
                                if (callingFrom.equals(Constant.NAVIGATION_GROUP_SERVICE)) {


                                    if (servicePageData.getUserRights().getDiscount()) {
                                        edItemDiscount.setFocusable(true);
                                        edItemDiscount.setFocusableInTouchMode(true);
                                    } else {
                                        edItemDiscount.setFocusable(false);
                                        edItemDiscount.setFocusableInTouchMode(false);
                                    }
                                    if (servicePageData.getUserRights().getUnitPriceEditable()) {

                                        edItemUnitPrice.setFocusable(true);
                                        edItemUnitPrice.setFocusableInTouchMode(true);
                                    } else {
                                        edItemUnitPrice.setFocusable(false);
                                        edItemUnitPrice.setFocusableInTouchMode(false);
                                    }
                                }


                                btnClose.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (dialog != null)
                                            dialog.dismiss();
                                    }
                                });

                                btnAdd.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        if (callingFrom.equals(Constant.NAVIGATION_GROUP_SERVICE)) {
                                            if (selectedCustomer == null) {
                                                isCheckoutable = false;
                                                flag = 9;
                                            } else {
                                                isCheckoutable = true;
                                                flag = 0;
                                            }

                                            if (edItemUnitPrice.getText().toString() == null || edItemUnitPrice.getText().toString().equals("") ||
                                                    edItemUnitPrice.getText().toString().equals("0.00") || edItemUnitPrice.getText().toString().equals("0")) {
                                                UtilView.showToast(activity, "Unit price can't be Zero");
                                                isCheckoutable = false;
                                            } else {
                                                isCheckoutable = true;
                                            }

                                            if (flag == 0 && isCheckoutable) {
                                                in.hiaccounts.hinext.service.model.SelectedItemsList serviceItem = new in.hiaccounts.hinext.service.model.SelectedItemsList();


                                                try {
                                                    try {
                                                        serviceItem.setCess(Double.parseDouble(itemDatum.getCess()));
                                                    } catch (NumberFormatException ne) {
                                                        serviceItem.setCess(Double.valueOf(0));
                                                    }

                                                    serviceItem.setItemCode(itemDatum.getItemCode());
                                                    serviceItem.setItemDescription(itemDatum.getItemDesc());
                                                    serviceItem.setItemName(itemDatum.getItemName());
                                                    serviceItem.setItemId(itemDatum.getItemId());
                                                    serviceItem.setItemTypeName(itemDatum.getItemTypeName());
                                                    serviceItem.setItemCategoryName(itemDatum.getItemCategoryName());
                                                    serviceItem.setItemQuantity(itemDatum.getItemQuantity());
                                                    serviceItem.setRemainingQty(Double.valueOf(itemDatum.getItemQuantity()));
                                                    serviceItem.setItemTotalAmount(itemDatum.getItemTotalAmount());
                                                    serviceItem.setPrice(itemDatum.getPrice());
                                                    serviceItem.setUnitPrice(Double.parseDouble(""+itemDatum.getPrice()));

                                                    serviceItem.setRemainingQty(Double.valueOf(itemDatum.getRemainingQty()));
                                                    serviceItem.setReturnQty(Double.valueOf(itemDatum.getReturnQty()));
                                                    serviceItem.setSelectSerialItem(itemDatum.isSelectSerialItem());
                                                    serviceItem.setSerializableStatus(itemDatum.getSerializableStatus());
                                                    serviceItem.setQty(Double.valueOf(itemDatum.getQty()));

                                                    serviceItem.setTaxamt(itemDatum.getTaxamt());
                                                    serviceItem.setTaxName(itemDatum.getTaxName());
                                                    serviceItem.setTaxTypeSelection(itemDatum.getTaxTypeSelection());
                                                    serviceItem.setTaxpercent(Double.valueOf(itemDatum.getTaxpercent()));
                                                    serviceItem.setTaxid(itemDatum.getTaxid());
                                                    serviceItem.setItemTypeName(itemDatum.getItemTypeName());

                                                    serviceItem.setAmtexclusivetax(itemDatum.getAmtexclusivetax());
                                                    serviceItem.setItemTotalAmount(itemDatum.getAmtexclusivetax());
                                                    serviceItem.setAmtinclusivetax(itemDatum.getAmtinclusivetax());


                                                    serviceItem.setDiscountAmt(itemDatum.getDiscountAmt());
                                                    if (itemDatum.getCessTaxAmt()!=null){
                                                        serviceItem.setCessTaxAmt(Double.parseDouble(itemDatum.getCessTaxAmt()));
                                                    }

                                                    serviceItem.setHsnCode(itemDatum.getHsnCode());
                                                    serviceItem.setUomName(itemDatum.getUomName());
                                                    serviceItem.setInclusiveJSON(itemDatum.getInclusiveJSON());


                                                    if (itemDatum.getUomConvertorList()!=null) {
                                                        serviceItem.setUomConvertorList(itemDatum.getUomConvertorList());
                                                    }else {
                                                        serviceItem.setUomConvertorList(itemDatum.getUomConvertorList());
                                                    }


                                                } catch (Exception e) {

                                                }
                                                servicePosCreator.addItem(new ServicePosCartItem(serviceItem, 1));

                                                Intent returnIntent = new Intent();
                                                setResult(Activity.RESULT_OK, returnIntent);
                                                finish();
                                                if (dialog != null)
                                                    dialog.dismiss();
                                            } else {
                                                if (flag == 1) {
                                                    UtilView.showToast(activity, " Item Quantity can't be empty");
                                                }
                                                if (flag == 2) {
                                                    UtilView.showToast(activity, " Item Quantity can't be zero");
                                                }
                                                if (flag == 3) {
                                                    UtilView.showToast(activity, " Enter valid quantity");
                                                }
                                                if (flag == 4) {
                                                    UtilView.showToast(activity, " Unit Price can't be empty");
                                                }
                                                if (flag == 5) {
                                                    UtilView.showToast(activity, " Enter valid price.");
                                                }
                                                if (flag == 6) {
                                                    UtilView.showToast(activity, " Enter valid discount price.");
                                                }
                                                if (flag == 7) {
                                                    UtilView.showToast(activity, " Discount amt should be less than Total Amount.");
                                                }
                                                if (flag == 8) {
                                                    UtilView.showToast(activity, " Enter valid cess percentage.");
                                                }
                                                if (flag == 9) {
                                                    UtilView.showToast(activity, " Please Select customer first.");
                                                }
                                                if (flag == 10) {
                                                    UtilView.showToast(activity, " Please Select supplier first.");
                                                }


                                            }

                                        }


                                        if (callingFrom.equals(Constant.NAVIGATION_GROUP_PURCHASE)) {
                                            if (selectedsupplier == null) {
                                                isCheckoutable = false;
                                                flag = 10;
                                            } else {
                                                isCheckoutable = true;
                                                flag = 0;
                                            }

                                            if (edItemUnitPrice.getText().toString() == null || edItemUnitPrice.getText().toString().equals("") ||
                                                    edItemUnitPrice.getText().toString().equals("0.00") || edItemUnitPrice.getText().toString().equals("0")) {

                                                UtilView.showToast(activity, "Unit price can't be Zero");
                                                isCheckoutable = false;
                                            } else {
                                                isCheckoutable = true;
                                            }
                                            if (flag == 0 && isCheckoutable) {

                                                in.hiaccounts.hinext.purchase.model.purchase_pos.SelectedItemsList purchaseItem = new in.hiaccounts.hinext.purchase.model.purchase_pos.SelectedItemsList();


                                                try {
                                                    try {
                                                        purchaseItem.setCess(Double.parseDouble(itemDatum.getCess()));
                                                    } catch (NumberFormatException ex) {
                                                    }


                                                    purchaseItem.setAmtexclusivetax(itemDatum.getAmtexclusivetax());
                                                    purchaseItem.setAmtinclusivetax(itemDatum.getAmtinclusivetax());
                                                    purchaseItem.setCessTaxAmt(Double.parseDouble(itemDatum.getCessTaxAmt()));
                                                    purchaseItem.setDiscountAmt(itemDatum.getDiscountAmt());
                                                    purchaseItem.setDiscountInPercent(itemDatum.isDiscountInPercent());
                                                    purchaseItem.setHsnCode(itemDatum.getHsnCode());
                                                    purchaseItem.setUomName(itemDatum.getUomName());
                                                    purchaseItem.setIgTax(Double.parseDouble(itemDatum.getCgstsgstsplitvalues()));
                                                    purchaseItem.setItemCode(itemDatum.getItemCode());
                                                    purchaseItem.setItemDescription(itemDatum.getItemDesc());
                                                    purchaseItem.setItemName(itemDatum.getItemName());
                                                    purchaseItem.setItemId(itemDatum.getItemId());
                                                    purchaseItem.setItemQuantity(itemDatum.getItemQuantity());
                                                    purchaseItem.setRemainingQty("" + itemDatum.getItemQuantity());
                                                    purchaseItem.setItemTotalAmount(itemDatum.getItemTotalAmount());
                                                    purchaseItem.setPrice(itemDatum.getPrice());
                                                    purchaseItem.setUnitPrice(Double.valueOf(""+itemDatum.getPrice()));
                                                    purchaseItem.setUnitPriceIn(Double.valueOf(""+itemDatum.getPrice()));
                                                    purchaseItem.setReturnQty(itemDatum.getReturnQty());
                                                    purchaseItem.setSelectSerialItem(itemDatum.isSelectSerialItem());
                                                    purchaseItem.setSerializableStatus(itemDatum.getSerializableStatus());
                                                    purchaseItem.setQty("" + itemDatum.getQty());
                                                    purchaseItem.setTaxAmountSplit(itemDatum.getTaxpercent());
                                                    purchaseItem.setTaxamt(itemDatum.getTaxamt());
                                                    purchaseItem.setTaxName(itemDatum.getTaxName());
                                                    purchaseItem.setTaxTypeSelection(itemDatum.getTaxTypeSelection());
                                                    purchaseItem.setTaxpercent(itemDatum.getTaxpercent());
                                                    purchaseItem.setTaxid(itemDatum.getTaxid());
                                                    purchaseItem.setItemTypeName(itemDatum.getItemTypeName());
                                                    purchaseItem.setItemCategoryName(itemDatum.getItemCategoryName());
                                                    purchaseItem.setItemCategoryId(itemDatum.getItemCategoryId());
                                                    purchaseItem.setInclusiveJSON(itemDatum.getInclusiveJSON());
                                                    purchaseItem.setDiscountConfigAmt(0.00);

                                                } catch (Exception e) {

                                                }

                                                purchasePosCreator.addItem(new PurchasePosCartItem(purchaseItem, 1), purchaseItem.getItemId());
                                                //  String saveData = saveposData("", "", selectedCustomer);
                                                //  UtilView.showLogCat(TAG, "AddItem Data " + saveData);
                                                Intent returnIntent = new Intent();
                                                setResult(Activity.RESULT_OK, returnIntent);
                                                finish();

                                            } else {
                                                if (flag == 1) {
                                                    UtilView.showToast(activity, " Item Quantity can't be empty");
                                                }
                                                if (flag == 2) {
                                                    UtilView.showToast(activity, " Item Quantity can't be zero");
                                                }
                                                if (flag == 3) {
                                                    UtilView.showToast(activity, " Enter valid quantity");
                                                }
                                                if (flag == 4) {
                                                    UtilView.showToast(activity, " Unit Price can't be empty");
                                                }
                                                if (flag == 5) {
                                                    UtilView.showToast(activity, " Enter valid price.");
                                                }
                                                if (flag == 6) {
                                                    UtilView.showToast(activity, " Enter valid discount price.");
                                                }
                                                if (flag == 7) {
                                                    UtilView.showToast(activity, " Discount amt should be less than Total Amount.");
                                                }
                                                if (flag == 8) {
                                                    UtilView.showToast(activity, " Enter valid cess percentage.");
                                                }
                                                if (flag == 9) {
                                                    UtilView.showToast(activity, " Please Select customer first.");
                                                }
                                                if (flag == 10) {
                                                    UtilView.showToast(activity, " Please Select supplier first.");
                                                }


                                            }

                                        }
                                        if (callingFrom.equals(Constant.NAVIGATION_GROUP_RETAIL)) {
                                            if (selectedCustomer == null) {
                                                isCheckoutable = false;
                                                flag = 9;
                                            } else {
                                                isCheckoutable = true;
                                                flag = 0;
                                            }

                                            if (edItemUnitPrice.getText().toString() == null || edItemUnitPrice.getText().toString().equals("") ||
                                                    edItemUnitPrice.getText().toString().equals("0.00") || edItemUnitPrice.getText().toString().equals("0")) {
                                                UtilView.showToast(activity, "Unit price can't be Zero");
                                                isCheckoutable = false;
                                            } else {
                                                isCheckoutable = true;
                                            }

                                            if (flag == 0 && isCheckoutable) {

                                                in.hiaccounts.hinext.sales.model.checkout.SelectedItemsList salesItem = new in.hiaccounts.hinext.sales.model.checkout.SelectedItemsList();


                                                try {
                                                    try {
                                                        salesItem.setCess(Double.parseDouble(itemDatum.getCess()));
                                                    } catch (NumberFormatException ne) {
                                                        salesItem.setCess(0);
                                                    }

                                                    salesItem.setAmtexclusivetax(Double.valueOf(String.valueOf(new BigDecimal(String.valueOf(itemDatum.getAmtexclusivetax())).setScale(2, BigDecimal.ROUND_HALF_UP))));
                                                    salesItem.setItemTotalAmount(Double.valueOf(String.valueOf(new BigDecimal(String.valueOf(itemDatum.getAmtexclusivetax())).setScale(2, BigDecimal.ROUND_HALF_UP))));
                                                    salesItem.setAmtinclusivetax(Double.valueOf(String.valueOf(new BigDecimal(String.valueOf(itemDatum.getAmtinclusivetax())).setScale(2, BigDecimal.ROUND_HALF_UP))));

                                                    Log.e("itemDatumInclisive", String.valueOf(salesItem.getAmtinclusivetax()));
                                                    salesItem.setCessTaxAmt(Double.parseDouble(itemDatum.getCessTaxAmt()));
                                                    salesItem.setDiscountAmt(itemDatum.getDiscountAmt());
                                                    salesItem.setDiscountInPercent(itemDatum.isDiscountInPercent());
                                                    salesItem.setHsnCode(itemDatum.getHsnCode());
                                                    salesItem.setUomName(itemDatum.getUomName());


                                                    if (itemDatum.getUomConvertorList()!=null) {
                                                        salesItem.setUomConvertorList(itemDatum.getUomConvertorList());
                                                    }else {
                                                        salesItem.setUomConvertorList(itemDatum.getUomConvertorList());
                                                    }

                                                    salesItem.setIgTax(Double.parseDouble(itemDatum.getCgstsgstsplitvalues()));
                                                    salesItem.setItemCode(itemDatum.getItemCode());
                                                    salesItem.setItemDescription(itemDatum.getItemDesc());
                                                    salesItem.setItemName(itemDatum.getItemName());
                                                    salesItem.setItemId(itemDatum.getItemId());
                                                    salesItem.setItemQuantity(itemDatum.getItemQuantity());
                                                    salesItem.setRemainingQty(itemDatum.getItemQuantity());
                                                    salesItem.setItemTotalAmount(itemDatum.getItemTotalAmount());
                                                    salesItem.setPrice(BigDecimal.valueOf(Double.valueOf(String.valueOf(new BigDecimal(String.valueOf(itemDatum.getPrice())).setScale(2, BigDecimal.ROUND_HALF_UP)))));
                                                    salesItem.setUnitPrice(Double.valueOf(String.valueOf(new BigDecimal(String.valueOf(itemDatum.getPrice())).setScale(2, BigDecimal.ROUND_HALF_UP))));
                                                    salesItem.setUnitPriceIn(Double.valueOf(String.valueOf(new BigDecimal(String.valueOf(itemDatum.getPrice())).setScale(2, BigDecimal.ROUND_HALF_UP))));
                                                    salesItem.setRemainingQty(itemDatum.getRemainingQty());
                                                    salesItem.setReturnQty(itemDatum.getReturnQty());
                                                    salesItem.setSelectSerialItem(itemDatum.isSelectSerialItem());
                                                    salesItem.setSerializableStatus(itemDatum.getSerializableStatus());
                                                    salesItem.setQty(itemDatum.getQty());
                                                    salesItem.setTaxAmountSplit(itemDatum.getTaxPercentageSplit());
                                                    salesItem.setTaxamt(itemDatum.getTaxamt());
                                                    salesItem.setTaxName(itemDatum.getTaxName());
                                                    salesItem.setTaxTypeSelection(itemDatum.getTaxTypeSelection());
                                                    salesItem.setTaxpercent(itemDatum.getTaxpercent());
                                                    salesItem.setTaxid(itemDatum.getTaxid());
                                                    salesItem.setItemTypeName(itemDatum.getItemTypeName());
                                                    salesItem.setItemCategoryName(itemDatum.getItemCategoryName());
                                                    salesItem.setItemCategoryId(itemDatum.getItemCategoryId());
                                                    salesItem.setInclusiveJSON(itemDatum.getInclusiveJSON());
                                                    salesItem.setDiscountConfigAmt(0.00);

                                                } catch (Exception e) {

                                                }
                                                salesPosCreator.addItem(new SalesPosCartItem(salesItem, 1));
                                                //  String saveData = saveposData("", "", selectedCustomer);
                                                //  UtilView.showLogCat(TAG, "AddItem Data " + saveData);
                                                Intent returnIntent = new Intent();
                                                setResult(Activity.RESULT_OK, returnIntent);
                                                finish();
                                                if (dialog != null)
                                                    dialog.dismiss();
                                            } else {
                                                if (flag == 1) {
                                                    UtilView.showToast(activity, " Item Quantity can't be empty");
                                                }
                                                if (flag == 2) {
                                                    UtilView.showToast(activity, " Item Quantity can't be zero");
                                                }
                                                if (flag == 3) {
                                                    UtilView.showToast(activity, " Enter valid quantity");
                                                }
                                                if (flag == 4) {
                                                    UtilView.showToast(activity, " Unit Price can't be empty");
                                                }
                                                if (flag == 5) {
                                                    UtilView.showToast(activity, " Enter valid price.");
                                                }
                                                if (flag == 6) {
                                                    UtilView.showToast(activity, " Enter valid discount price.");
                                                }
                                                if (flag == 7) {
                                                    UtilView.showToast(activity, " Discount amt should be less than Total Amount.");
                                                }
                                                if (flag == 8) {
                                                    UtilView.showToast(activity, " Enter valid cess percentage.");
                                                }
                                                if (flag == 9) {
                                                    UtilView.showToast(activity, " Please Select customer first.");
                                                }
                                                if (flag == 10) {
                                                    UtilView.showToast(activity, " Please Select supplier first.");
                                                }


                                            }

                                        }




                                    }
                                });

                                if (dialog != null)
                                    dialog.show();


                                edItemName.setText(itemDatum.getItemName());
                                edItemDescritpion.setText(itemDatum.getItemDesc());
                                edItemDescritpion.addTextChangedListener(new TextWatcher() {
                                    @Override
                                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                    }

                                    @Override
                                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                                        itemDatum.setItemDesc(s.toString());
                                    }

                                    @Override
                                    public void afterTextChanged(Editable s) {

                                    }
                                });

                                if (callingFrom.equals(Constant.NAVIGATION_GROUP_RETAIL)) {
                                    double itemSalesPrice = 0;
                                    if (itemDatum.getUnitPrice() != 0) {
                                        itemSalesPrice = itemDatum.getUnitPrice();
                                    }
                                    if (itemDatum.getSalesPrice() != 0) {
                                        itemSalesPrice = itemDatum.getSalesPrice();
                                        itemDatum.setPrice(BigDecimal.valueOf(itemSalesPrice));
                                        edItemUnitPrice.setText(String.valueOf(itemDatum.getPrice().setScale(2, BigDecimal.ROUND_HALF_UP)));

                                    }

                                    Double gstTaxPercantage =0.00;
                                    if (salesPageData!=null){
                                        List<in.hiaccounts.hinext.sales.model.sales_pagedata.TaxList> taxList=salesPageData.getTaxList();
                                        if (taxList!=null) {
                                            for (int i = 0; i < taxList.size(); i++) {
                                                if (itemDatum.getOuputTaxId() == taxList.get(i).getTaxid()) {
                                                    gstTaxPercantage = (Double) taxList.get(i).getTaxPercentage();
                                                }
                                            }
                                        }

                                        UtilView.showLogCat("tax  data  ",gstTaxPercantage.toString());
                                    }


                                    double itemUnitPrice=0.00;
                                    if (itemDatum.getInclusiveJSON()!=null){
                                        JSONObject jsonObject= new JSONObject(itemDatum.getInclusiveJSON());
                                        boolean isSales = jsonObject.optBoolean("sales");

                                        if (isSales){
                                            itemUnitPrice = itemDatum.getSalesPrice() / (1 + (gstTaxPercantage * 0.01));


                                        }else {
                                            if (itemDatum.getSalesPrice()!=0)
                                                itemUnitPrice=itemDatum.getSalesPrice();
                                        }
                                        itemDatum.setPrice(BigDecimal.valueOf(itemUnitPrice));
                                        itemDatum.setSalesPrice(Double.valueOf(String.valueOf(itemDatum.getPrice())));
                                        edItemUnitPrice.setText(String.valueOf(itemDatum.getPrice().setScale(2, BigDecimal.ROUND_HALF_UP)));

                                     /*   if (isSales){
                                      BigDecimal taxAmt = BigDecimal.valueOf(0);
                                          *//*  double taxPercentage=0;
                                            if (itemDatum.getItemOPTaxDTOList()!=null && itemDatum.getItemOPTaxDTOList().size()>0) {
                                                for (int i=0;i<itemDatum.getItemOPTaxDTOList().size();i++){
                                                    if (itemDatum.getOuputTaxId().equals(itemDatum.getItemOPTaxDTOList().get(i))){
                                                        taxPercentage=itemDatum.getItemOPTaxDTOList().get(i).getTaxPer();
                                                    }}}*//*

                                            BigDecimal taxAmt = BigDecimal.valueOf(0);
                                            taxAmt =itemDatum.getPrice().subtract(new BigDecimal(itemDatum.getDiscountAmt())).multiply(BigDecimal.valueOf(gstTaxPercantage).divide(BigDecimal.valueOf(100))).setScale(2, BigDecimal.ROUND_HALF_UP);
                                            itemDatum.setPrice(itemDatum.getPrice().subtract(taxAmt));
                                            itemDatum.setSalesPrice(Double.valueOf(String.valueOf(itemDatum.getPrice())));
                                            edItemUnitPrice.setText(String.valueOf(itemDatum.getPrice().setScale(2, BigDecimal.ROUND_HALF_UP)));
                                        }
                                        else{


                                        }*/


                                    }


                                }


                                if (callingFrom.equals(Constant.NAVIGATION_GROUP_PURCHASE)) {
                                    double itemPurchasePrice = 0;
                                    if (itemDatum.getUnitPrice() != 0) {
                                        itemPurchasePrice = itemDatum.getUnitPrice();
                                    }
                                    if (itemDatum.getPurchasePrice() != 0) {
                                        itemPurchasePrice = itemDatum.getPurchasePrice();
                                    }

                                    Double gstTaxPercantage =0.00;
                                    if (purchasePageData!=null){
                                        List<TaxList> taxList=purchasePageData.getTaxList();
                                        if (taxList!=null) {
                                            for (int i = 0; i < taxList.size(); i++) {
                                                if (itemDatum.getInputTaxId() == taxList.get(i).getTaxid()) {
                                                    gstTaxPercantage = Double.valueOf(taxList.get(i).getTaxPercentage());
                                                }
                                            }
                                        }

                                        UtilView.showLogCat("tax  data  ",gstTaxPercantage.toString());
                                    }

                                    double itemUnitPrice=0.00;
                                    if (itemDatum.getInclusiveJSON()!=null){
                                        JSONObject jsonObject= new JSONObject(itemDatum.getInclusiveJSON());
                                        boolean isPurchase = jsonObject.optBoolean("purchases");

                                        if (isPurchase){
                                            itemUnitPrice = itemDatum.getPurchasePrice() / (1 + (gstTaxPercantage * 0.01));


                                        }else {
                                            if (itemDatum.getPurchasePrice()!=0)
                                                itemUnitPrice=itemDatum.getPurchasePrice();
                                        }
                                        itemDatum.setPrice(BigDecimal.valueOf(itemUnitPrice));
                                        itemDatum.setPurchasePrice(Double.valueOf(String.valueOf(itemDatum.getPrice())));
                                        edItemUnitPrice.setText(String.valueOf(itemDatum.getPrice().setScale(2, BigDecimal.ROUND_HALF_UP)));

                                    }


                                    //itemDatum.setPrice(BigDecimal.valueOf(itemPurchasePrice));
                                   // edItemUnitPrice.setText(String.valueOf(itemDatum.getPrice().setScale(2, BigDecimal.ROUND_HALF_UP)));
                                }
                                if (callingFrom.equals(Constant.NAVIGATION_GROUP_SERVICE)) {
                                    double itemServicePrice = 0;
                                    if (itemDatum.getUnitPrice() != 0) {
                                        itemServicePrice = itemDatum.getUnitPrice();
                                    }
                                    if (itemDatum.getSalesPrice() != 0) {
                                        itemServicePrice = itemDatum.getSalesPrice();
                                        itemDatum.setPrice(BigDecimal.valueOf(itemServicePrice));
                                        edItemUnitPrice.setText(String.valueOf(itemDatum.getPrice().setScale(2, BigDecimal.ROUND_HALF_UP)));

                                    }

                                    Double gstTaxPercantage =0.00;
                                    if (servicePageData!=null){
                                        List<TaxList> taxList=servicePageData.getTaxList();
                                        if (taxList!=null) {
                                            for (int i = 0; i < taxList.size(); i++) {
                                                if (itemDatum.getOuputTaxId() == taxList.get(i).getTaxid()) {
                                                    gstTaxPercantage = Double.valueOf(taxList.get(i).getTaxPercentage());
                                                }
                                            }
                                        }

                                        UtilView.showLogCat("tax  data  ",gstTaxPercantage.toString());
                                    }


                                    double itemUnitPrice=0.00;
                                    if (itemDatum.getInclusiveJSON()!=null){
                                        JSONObject jsonObject= new JSONObject(itemDatum.getInclusiveJSON());
                                        boolean isSales = jsonObject.optBoolean("sales");

                                        if (isSales){
                                            itemUnitPrice = itemDatum.getSalesPrice() / (1 + (gstTaxPercantage * 0.01));


                                        }else {
                                            if (itemDatum.getSalesPrice()!=0)
                                                itemUnitPrice=itemDatum.getSalesPrice();
                                        }
                                        itemDatum.setPrice(BigDecimal.valueOf(itemUnitPrice));
                                        itemDatum.setSalesPrice(Double.valueOf(String.valueOf(itemDatum.getPrice())));
                                        edItemUnitPrice.setText(String.valueOf(itemDatum.getPrice().setScale(2, BigDecimal.ROUND_HALF_UP)));


                                    }


                                }

                                edItemQuantity.setText("1");
                                itemDatum.setItemQuantity(1);

                                BigDecimal totalPrice = BigDecimal.ZERO;
                                totalPrice = totalPrice.add(itemDatum.getPrice().multiply(BigDecimal.valueOf(itemDatum.getItemQuantity())));
                                edItemAmtExTax.setText("" + totalPrice.setScale(2, BigDecimal.ROUND_HALF_UP));
                                itemDatum.setAmtexclusivetax(Double.parseDouble(String.valueOf(totalPrice)));


                                if (itemDatum.getCess() != null)
                                    edCessPercantage.setText(itemDatum.getCess());

                                if (callingFrom.equals(Constant.NAVIGATION_GROUP_SERVICE)) {
                                    String defaultTax = "";
                                    if (servicePageData != null) {
                                        if (servicePageData.getTaxList().size() > 0) {
                                            UtilView.setupHinextServiceTaxAdapter(activity, spinItemInputtax, servicePageData.getTaxList());
                                            for (int i = 0; i < servicePageData.getTaxList().size(); i++) {
                                                if (itemDatum.getOuputTaxId() == (servicePageData.getTaxList().get(i).getTaxid())) {
                                                    spinItemInputtax.setSelection(i);
                                                    defaultTax = servicePageData.getTaxList().get(i).getTaxString();
                                                    setTaxString(defaultTax);
                                                    String cessString = edCessPercantage.getText().toString();
                                                    if (cessString.equals("") || cessString == null) {
                                                        cessString = "0";
                                                    } else {
                                                        cessString = edCessPercantage.getText().toString().trim();
                                                    }

                                                    updateTaxAmount(getTax(getTaxString(), itemDatum), itemDatum, edTaxTypeAmount, edItemAmtExTax, getCessPercantage(cessString));
                                                } else {
                                                    UtilView.showLogCat(TAG, "Tax not seleted111");
                                                }
                                            }
                                        }
                                    }
                                }

                                String defaultTax = "";

                                if (callingFrom.equals(Constant.NAVIGATION_GROUP_RETAIL)) {
                                    if (salesPageData != null) {
                                        if (salesPageData.getTaxList().size() > 0) {
                                            UtilView.setupHinextSalesTaxAdapter(activity, spinItemInputtax, salesPageData.getTaxList());
                                            for (int i = 0; i < salesPageData.getTaxList().size(); i++) {
                                                if (itemDatum.getOuputTaxId() == (salesPageData.getTaxList().get(i).getTaxid())) {
                                                    spinItemInputtax.setSelection(i);
                                                    defaultTax = salesPageData.getTaxList().get(i).getTaxString();
                                                    setTaxString(defaultTax);
                                                    String cessString = edCessPercantage.getText().toString();
                                                    if (cessString.equals("") || cessString == null) {
                                                        cessString = "0";
                                                    } else {
                                                        cessString = edCessPercantage.getText().toString().trim();
                                                    }

                                                    updateTaxAmount(getTax(getTaxString(), itemDatum), itemDatum, edTaxTypeAmount, edItemAmtExTax, getCessPercantage(cessString));
                                                } else {
                                                    UtilView.showLogCat(TAG, "Tax not seleted");
                                                }
                                            }
                                        }
                                    }
                                }

                                if (callingFrom.equals(Constant.NAVIGATION_GROUP_PURCHASE)) {
                                    if (purchasePageData != null) {
                                        if (purchasePageData.getTaxList().size() > 0) {
                                            UtilView.setupHinextPurchaseTaxAdapter(activity, spinItemInputtax, purchasePageData.getTaxList());
                                            for (int i = 0; i < purchasePageData.getTaxList().size(); i++) {
                                                if (itemDatum.getInputTaxId() == (purchasePageData.getTaxList().get(i).getTaxid())) {
                                                    spinItemInputtax.setSelection(i);
                                                    defaultTax = purchasePageData.getTaxList().get(i).getTaxString();
                                                    setTaxString(defaultTax);
                                                    String cessString = edCessPercantage.getText().toString();
                                                    if (cessString.equals("") || cessString == null) {
                                                        cessString = "0";
                                                    } else {
                                                        cessString = edCessPercantage.getText().toString().trim();
                                                    }

                                                    updateTaxAmount(getTax(getTaxString(), itemDatum), itemDatum, edTaxTypeAmount, edItemAmtExTax, getCessPercantage(cessString));
                                                } else {
                                                    UtilView.showLogCat(TAG, "Tax not seleted");
                                                }
                                            }
                                        }
                                    }
                                }


                                spinItemInputtax.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                                        spinItemInputtax.setSelection(i);
                                        if (callingFrom.equals(Constant.NAVIGATION_GROUP_RETAIL)) {
                                            itemDatum.setTaxid(Long.valueOf(salesPageData.getTaxList().get(i).getTaxid()));
                                            itemDatum.setTaxId(Long.valueOf(salesPageData.getTaxList().get(i).getTaxid()));
                                            String tax = salesPageData.getTaxList().get(i).getTaxString();
                                            setTaxString(tax);
                                            String cessString = edCessPercantage.getText().toString();
                                            if (cessString.equals("") || cessString == null) {
                                                cessString = "0";
                                            } else {
                                                cessString = edCessPercantage.getText().toString().trim();
                                            }
                                            updateTaxAmount(getTax(tax, itemDatum), itemDatum, edTaxTypeAmount, edItemAmtExTax, getCessPercantage(cessString));
                                        }
                                        if (callingFrom.equals(Constant.NAVIGATION_GROUP_PURCHASE)) {
                                            itemDatum.setTaxid(Long.valueOf(purchasePageData.getTaxList().get(i).getTaxid()));
                                            itemDatum.setTaxId(Long.valueOf(purchasePageData.getTaxList().get(i).getTaxid()));
                                            String tax = purchasePageData.getTaxList().get(i).getTaxString();
                                            setTaxString(tax);
                                            String cessString = edCessPercantage.getText().toString();
                                            if (cessString.equals("") || cessString == null) {
                                                cessString = "0";
                                            } else {
                                                cessString = edCessPercantage.getText().toString().trim();
                                            }
                                            updateTaxAmount(getTax(tax, itemDatum), itemDatum, edTaxTypeAmount, edItemAmtExTax, getCessPercantage(cessString));
                                        }
                                        if (callingFrom.equals(Constant.NAVIGATION_GROUP_SERVICE)) {
                                            itemDatum.setTaxid(Long.valueOf(servicePageData.getTaxList().get(i).getTaxid()));
                                            itemDatum.setTaxId(Long.valueOf(servicePageData.getTaxList().get(i).getTaxid()));
                                            String tax = servicePageData.getTaxList().get(i).getTaxString();
                                            setTaxString(tax);
                                            String cessString = edCessPercantage.getText().toString();
                                            if (cessString.equals("") || cessString == null) {
                                                cessString = "0";
                                            } else {
                                                cessString = edCessPercantage.getText().toString().trim();
                                            }
                                            updateTaxAmount(getTax(tax, itemDatum), itemDatum, edTaxTypeAmount, edItemAmtExTax, getCessPercantage(cessString));
                                        }


                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {

                                    }
                                });

                                edItemQuantity.addTextChangedListener(new TextWatcher() {
                                    @Override
                                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                    }

                                    @Override
                                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                                        if (s.toString().equals("")) {
                                            edItemQuantity.setError("Quantity can't be empty.");
                                            isCheckoutable = false;
                                            flag = 1;
                                        } else {
                                            try {
                                                int quantity = Integer.parseInt(s.toString());
                                                if (quantity == 0) {
                                                    edItemQuantity.setError("Quantity can't be zero.");
                                                    isCheckoutable = false;
                                                    flag = 2;
                                                } else {
                                                    isCheckoutable = true;
                                                    flag = 0;
                                                    edItemQuantity.setError(null);
                                                    itemDatum.setItemQuantity(quantity);

                                                    String cessString = edCessPercantage.getText().toString();
                                                    if (cessString.equals("") || cessString == null) {
                                                        cessString = "0";
                                                    } else {
                                                        cessString = edCessPercantage.getText().toString().trim();
                                                    }
                                                    updateByQuantityTotalExTax(itemDatum, quantity);
                                                    updateTaxAmount(getTax(getTaxString(), itemDatum), itemDatum, edTaxTypeAmount, edItemAmtExTax, getCessPercantage(cessString));


                                                }

                                            } catch (NumberFormatException e) {
                                                edItemQuantity.setError("Enter only digits.");
                                                isCheckoutable = false;
                                                flag = 3;
                                            }
                                        }
                                    }

                                    @Override
                                    public void afterTextChanged(Editable s) {

                                    }
                                });


                                edItemUnitPrice.addTextChangedListener(new TextWatcher() {
                                    @Override
                                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                    }

                                    @Override
                                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                                        if (s.toString().equals("")) {

                                            edItemAmtExTax.setText("0");
                                            itemAmtIncTax.setText("0");
                                            edTaxTypeAmount.setText("0");
                                            edItemDiscount.setText("0");
                                            edItemUnitPrice.setError("Unit price can't be empty.");
                                            flag = 4;
                                            isCheckoutable = false;
                                        } else {
                                            try {
                                                if (s.toString().equals("0.00")) {
                                                    flag = 4;
                                                    isCheckoutable = false;

                                                } else {
                                                    flag = 0;
                                                    isCheckoutable = true;
                                                    edItemUnitPrice.setError(null);
                                                    itemDatum.setPrice(new BigDecimal(s.toString()));
                                                    itemDatum.setUnitPrice(Double.parseDouble(s.toString()));


                                                    updateItemTotalExTax(itemDatum, new BigDecimal(s.toString()));
                                                    String cessString = edCessPercantage.getText().toString();
                                                    if (cessString.equals("") || cessString == null) {
                                                        cessString = "0";
                                                    } else {
                                                        cessString = edCessPercantage.getText().toString().trim();
                                                    }

                                                    updateTaxAmount(getTax(getTaxString(), itemDatum), itemDatum, edTaxTypeAmount, edItemAmtExTax, getCessPercantage(cessString));
                                                    String amtEx = edItemAmtExTax.getText().toString().trim();
                                                    double amtExc = Double.parseDouble(amtEx);
                                                    double amTax = Double.parseDouble(String.valueOf(totalTaxAmt));
                                                    double discount = 0;

                                                    String discountPrice = edItemDiscount.getText().toString().trim();
                                                    if (discountPrice != null && !discountPrice.equals("")) {
                                                        if (flag == 5) {

                                                        } else {
                                                            discount = Double.parseDouble(discountPrice);
                                                        }
                                                    }
                                                    if ((amtExc + amTax) > discount) {
                                                        edItemDiscount.setError(null);
                                                        updateDiscountPrice(itemDatum, discount);
                                                        flag = 0;
                                                        isCheckoutable = true;
                                                    } else {
                                                        if (discount != 0) {
                                                            flag = 7;
                                                            isCheckoutable = false;
                                                            edItemDiscount.setError("Discount Amount should be less than total amount.");
                                                        }

                                                    }
                                                }

                                            } catch (NumberFormatException e) {
                                                flag = 5;
                                                isCheckoutable = false;
                                                edItemUnitPrice.setError("Must be numeric value with only one .");
                                            }
                                        }

                                    }

                                    @Override
                                    public void afterTextChanged(Editable s) {

                                    }
                                });


                                edCessPercantage.addTextChangedListener(new TextWatcher() {
                                    @Override
                                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                    }

                                    @Override
                                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                                        if (s.toString().equals("")) {
                                            edCessPercantage.setError(null);
                                            isCheckoutable = true;
                                            flag = 0;
                                            cessPercantage = BigDecimal.ZERO;
                                            updateTaxAmount(getTax(getTaxString(), itemDatum), itemDatum, edTaxTypeAmount, edItemAmtExTax, cessPercantage);
                                        } else {
                                            try {
                                                isCheckoutable = true;
                                                flag = 0;
                                                edCessPercantage.setError(null);
                                                cessPercantage = getCessPercantage(s.toString());
                                                itemDatum.setCess(s.toString());
                                                updateTaxAmount(getTax(getTaxString(), itemDatum), itemDatum, edTaxTypeAmount, edItemAmtExTax, cessPercantage);


                                            } catch (NumberFormatException e) {
                                                edCessPercantage.setError("Must be numeric value with only one .");
                                                isCheckoutable = false;
                                                flag = 8;

                                            }
                                        }

                                    }


                                    @Override
                                    public void afterTextChanged(Editable s) {
                                    }
                                });

                                edItemDiscount.addTextChangedListener(new TextWatcher() {
                                    @Override
                                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                    }

                                    @Override
                                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                                        try {
                                            String discount;
                                            if (s.toString().equals("")) {
                                                discount = "0";
                                            } else {
                                                discount = s.toString().trim();
                                            }
                                            String cessString = edCessPercantage.getText().toString();
                                            if (cessString.equals("") || cessString == null) {
                                                cessString = "0";
                                            } else {
                                                cessString = edCessPercantage.getText().toString().trim();
                                            }

                                            Double disc =Double.parseDouble(discount);

                                            itemDatum.setDiscountAmt(disc);


                                            String amtEx = edItemAmtExTax.getText().toString().trim();

                                            double amtExc = Double.parseDouble(amtEx);
                                            double amTax = Double.parseDouble(String.valueOf(totalTaxAmt));

                                            if ((amtExc + amTax) > Double.parseDouble(discount)) {
                                                updateDiscountPrice(itemDatum, Double.parseDouble(discount));
                                                updateTaxAmount(getTax(getTaxString(), itemDatum), itemDatum, edTaxTypeAmount, edItemAmtExTax, getCessPercantage(cessString));

                                            } else {
                                                if (s.toString() == null || s.toString().equals("")) {
                                                    edItemDiscount.setError(null);
                                                    flag = 0;
                                                    isCheckoutable = true;
                                                } else {
                                                    flag = 7;
                                                    isCheckoutable = false;
                                                    edItemDiscount.setError("Disocunt Amount should be less than total amount.");
                                                }
                                            }
                                        } catch (NumberFormatException e) {
                                            flag = 6;
                                            isCheckoutable = false;
                                            edItemDiscount.setError("Must be numeric value with only one .");

                                        }
                                    }

                                    @Override
                                    public void afterTextChanged(Editable s) {

                                    }
                                });


                            } else {

                                UtilView.showToast(activity, "Item not found with barcode " + itemCode);
                            }


                        } catch (Exception e) {
                            UtilView.showErrorDialog(getResources().getString(R.string.error_null), activity);
                        }
                    } else {
                        UtilView.showErrorDialog(getResources().getString(R.string.error_null), activity);
                    }


                }


                private BigDecimal getCessPercantage(String cessPercanteString) {
                    return BigDecimal.valueOf(Double.parseDouble(cessPercanteString.toString()));
                }


                private BigDecimal getTax(String tax, SelectedItemsList itemDatum) {

                    try {
                        int pos = tax.indexOf("|");
                        BigDecimal taxValue = new BigDecimal(tax.substring(0, pos).trim());
                        String subString = tax.substring(pos + 1);
                        String taxName = subString.substring(0, subString.indexOf("|"));
                        itemDatum.setTaxName(taxName);
                        UtilView.showLogCat("tax name", taxName);
                        itemDatum.setTaxpercent(String.valueOf(taxValue));
                        return taxValue;
                    } catch (Exception e) {

                    }


                    return new BigDecimal(0.00);

                }

                private void updateTaxAmount(BigDecimal taxPercantage, SelectedItemsList itemDatum, EditText edTaxTypeAmount, EditText edItemAmtExTax, BigDecimal cessPercantage) {
                    try {

                        String discout = edItemDiscount.getText().toString().trim();
                        String amtEx = edItemAmtExTax.getText().toString().trim();


                        if (discout.equals("")) {
                            discout = "0";
                        }

                        BigDecimal taxAmt = BigDecimal.valueOf(0);

                        BigDecimal amtExTax;
                        if (amtEx.equals("") || amtEx == null) {
                            amtExTax = BigDecimal.ZERO;
                        } else {
                            amtExTax = BigDecimal.valueOf(Double.parseDouble(amtEx));
                        }


                        taxAmt = taxAmt.add(amtExTax.subtract(new BigDecimal(discout)).multiply(taxPercantage.divide(BigDecimal.valueOf(100))).setScale(2, BigDecimal.ROUND_HALF_DOWN));

                        BigDecimal cessTaxamt = amtExTax.subtract(new BigDecimal(discout)).multiply(cessPercantage.divide(BigDecimal.valueOf(100))).setScale(2, BigDecimal.ROUND_HALF_UP);

                        itemDatum.setCessTaxAmt("" + cessTaxamt);
                        edCessAmt.setText("" + cessTaxamt);

                        totalTaxAmt = taxAmt.add(cessTaxamt);


                        edItemTotalTaxamt.setText("" + totalTaxAmt);
                        itemTotalamt = amtExTax.add(totalTaxAmt).subtract(new BigDecimal(discout));

                        itemDatum.setAmtinclusivetax(Double.parseDouble(String.valueOf(itemTotalamt.setScale(2, BigDecimal.ROUND_HALF_UP))));
                        UtilView.showLogCat("itemAmtIncTax ", String.valueOf(itemDatum.getAmtinclusivetax()));
                        itemAmtIncTax.setText(String.valueOf(itemTotalamt.setScale(2, BigDecimal.ROUND_HALF_UP)));

                        itemDatum.setTaxamt(Double.parseDouble(String.valueOf(totalTaxAmt)));
                        itemDatum.setDiscountAmountRpercent((Float.parseFloat(discout)));


                        double amtIncTax;
                        if (itemAmtIncTax.getText().toString().trim() == null || itemAmtIncTax.getText().toString().trim().equals("")) {
                            amtIncTax = 0;
                        } else {
                            amtIncTax = Double.parseDouble(itemAmtIncTax.getText().toString().trim());
                        }
                        itemDatum.setItemTotalAmount(amtIncTax);

                        itemDatum.setCgstsgstsplitvalues(String.valueOf(taxAmt));
                        if (selectedTaxType.equals(getResources().getString(R.string.igst))) {
                            edTaxTypeAmount.setText("" + taxAmt.setScale(2, BigDecimal.ROUND_HALF_UP));
                            double taxValue = Double.parseDouble(String.valueOf(taxAmt.setScale(2, BigDecimal.ROUND_HALF_UP)));
                            itemDatum.setTaxPercentageSplit(String.valueOf(taxValue));

                        }
                        if (selectedTaxType.equals(getResources().getString(R.string.cgst))) {
                            BigDecimal bgTax = taxAmt.divide(BigDecimal.valueOf(2));
                            edTaxTypeAmount.setText("" + bgTax.setScale(2, BigDecimal.ROUND_HALF_UP) + ":" + bgTax.setScale(2, BigDecimal.ROUND_HALF_UP));
                            itemDatum.setTaxPercentageSplit(edTaxTypeAmount.getText().toString());
                        }



                        /*if (itemDatum.getSerializableStatus() == null || itemDatum.getSerializableStatus().equals(Constant.ITEM_BULK)) {
                            posCreator.updateitemPriceIncludeTax(itemDatum, amtIncTax);

                        }
                        if (itemDatum.getSerializableStatus() != null && itemDatum.getSerializableStatus().equals(Constant.ITEM_SERIALIZABLE)) {
                            itemDatum.setItemTotalAmount(amtIncTax);
                        }*/


                    } catch (Exception e) {
                        UtilView.showLogCat(TAG, "updateTaxAmount Exception " + e.toString());
                    }


                }


                private void updateByQuantityTotalExTax(SelectedItemsList itemDatum, int quantity) {
                    BigDecimal totalPrice = BigDecimal.ZERO;
                    totalPrice = totalPrice.add(itemDatum.getPrice().multiply(BigDecimal.valueOf(quantity)).setScale(2, BigDecimal.ROUND_HALF_UP));
                    edItemAmtExTax.setText("" + totalPrice);
                    if (quantity == 0) {
                        edItemQuantity.setText("0");
                    }
                    itemDatum.setAmtexclusivetax(Double.parseDouble(String.valueOf(totalPrice.setScale(2, BigDecimal.ROUND_HALF_UP))));

                    String discount = edItemDiscount.getText().toString().trim();
                    if (discount.equals("")) {
                        discount = "0";
                    }
                    double amtIncTax;

                    if (itemAmtIncTax.getText().toString().trim() == null || itemAmtIncTax.getText().toString().trim().equals("")) {
                        amtIncTax = 0;
                    } else {
                        amtIncTax = Double.parseDouble(itemAmtIncTax.getText().toString().trim());
                    }
                    //  posCreator.updateitemPriceIncludeTax(itemDatum, amtIncTax - Double.parseDouble(discount));
                    itemAmtIncTax.setText("" + (amtIncTax - Double.parseDouble(discount)));
                    itemDatum.setAmtinclusivetax((amtIncTax - Double.parseDouble(discount)));
                }


                private void updateDiscountPrice(SelectedItemsList itemDatum, double discount) {

                    //String amtTax = edTaxTypeAmount.getText().toString().trim();
                    String amtEx = edItemAmtExTax.getText().toString().trim();

                    double amtExc;
                    if (amtEx == null || amtEx.equals("")) {
                        amtExc = 0;
                    } else {
                        amtExc = Double.parseDouble(amtEx);
                    }

                    double amtTaxd;
                    amtTaxd = Double.parseDouble(String.valueOf(totalTaxAmt));
                   // itemDatum.setDiscountAmt(discount);
                    itemDatum.setDiscountAmountRpercent(discount);

                    BigDecimal finalIncusiveTaxAmt = new BigDecimal(amtExc).add(new BigDecimal(amtTaxd)).subtract(new BigDecimal(discount));
                    itemAmtIncTax.setText("" + finalIncusiveTaxAmt.setScale(2, BigDecimal.ROUND_HALF_UP));

                    double amtIncTax;
                    if (itemAmtIncTax.getText().toString().trim() == null || itemAmtIncTax.getText().toString().trim().equals("")) {
                        amtIncTax = 0;
                    } else {
                        amtIncTax = Double.parseDouble(itemAmtIncTax.getText().toString().trim());
                    }
                    itemDatum.setItemTotalAmount(amtIncTax);

                }


                private void updateItemTotalExTax(SelectedItemsList itemDatum, BigDecimal unitPrice) {
                    double itemQuantity;
                    itemQuantity = Double.valueOf(itemDatum.getItemQuantity());


                    BigDecimal totalPrice = BigDecimal.ZERO;
                    totalPrice = totalPrice.add(unitPrice.multiply(BigDecimal.valueOf(itemQuantity)));
                    UtilView.showLogCat(TAG, "updateItemTotalExTax unit price " + unitPrice + " TotalAmoutExTax: " + totalPrice);
                    edItemAmtExTax.setText(String.valueOf(totalPrice.setScale(2, BigDecimal.ROUND_HALF_UP)));


                    itemDatum.setAmtexclusivetax(Double.parseDouble(String.valueOf(totalPrice.setScale(2, BigDecimal.ROUND_HALF_UP))));


                    double amtIncTax;
                    if (itemAmtIncTax.getText().toString().trim() == null || itemAmtIncTax.getText().toString().trim().equals("")) {
                        amtIncTax = 0;
                    } else {
                        amtIncTax = Double.parseDouble(itemAmtIncTax.getText().toString().trim());
                    }
                    itemDatum.setAmtinclusivetax(amtIncTax);
                    itemDatum.setItemTotalAmount(amtIncTax);
                }

                public void showTotalPrice() {
                    Double posCartTotal = 0.00;
                    for (int i = 0; i < purchasePosCreator.getItems().size(); i++) {
                        posCartTotal += purchasePosCreator.getItems().get(i).getItem().getItemTotalAmount();
                    }
                    purchasePosCreator.setTotalPrice(posCartTotal);
                }
            }
        });


        edSearchitem.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(activity, edSearchitem);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    isInternetPresent = serviceHandler.isConnectingToInternet();
                    if (isInternetPresent) {
                        // prepare the Request
                        UtilView.showProgessBar(activity, progressBar);
                        GetDataTask task = new GetDataTask(Activity_ShowItemList.this, new GetDataTaskListenr(), false);
                        task.execute(url + edSearchitem.getText().toString().replace(" ", "%20"), "");
                    }
                    if (!isInternetPresent) {
                        UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
                    }
                }
                return handled;
            }
        });
    }

    private void getItemFromServer() {

        if (!url.equals("") && itemSearch.equals("")) {
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {
                // prepare the Request
                UtilView.showProgessBar(activity, progressBar);
                GetDataTask task = new GetDataTask(Activity_ShowItemList.this, new GetDataTaskListenr(), false);
                task.execute(url + itemSearch.replace(" ", "%20"), "");
            }
            if (!isInternetPresent) {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
            }
        }

     /*   if (!url.equals("") && !itemSearch.equals("")) {

            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {
                edSearchitem.setText(itemSearch);
                UtilView.showProgessBar(activity, progressBar);
                GetDataTask task = new GetDataTask(Activity_ShowItemList.this, new GetDataTaskListenr(), false);
                task.execute(url + itemSearch.replace(" ", "%20"), "");
            }
            if (!isInternetPresent) {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
            }

        }*/


    }

    private void initCompenents() {
        ButterKnife.bind(this);
        HideUtil.init(this);
        activity = Activity_ShowItemList.this;
        serverUrl = UtilView.getUrl(activity);
        serviceHandler = new ServiceHandler(activity);
        sharedPreference = SharedPreference.getInstance(activity);
        dialog = new Dialog(activity);
        final Intent intent = getIntent();
        itemSearch = intent.getStringExtra("itemsearch");
        callingFrom = intent.getStringExtra("callingfrom");
        url = intent.getStringExtra("url");
        selectedCustomer = (Customer) intent.getSerializableExtra("selectedcustomer");
        selectedsupplier = (GetSupplier) intent.getSerializableExtra("selectedsupplier");
        selectedTaxType = intent.getStringExtra("taxType");
        itemCodeId = intent.getStringExtra("itemCodeId");
        purchasePosCreator = PurchasePosHelper.getPosCreator();
        salesPosCreator = SalesPosHelper.getPosCreator();
        servicePosCreator = ServicePosHelper.getPosCreator();
        inventoryPosCreator = InventoryPosHelper.getPosCreator();
/*
        if (callingFrom != null) {
            if (callingFrom.equals(Constant.NAVIGATION_GROUP_PURCHASE)) {
                tv_scanitembarcode.setVisibility(View.GONE);
            }
        }*/


      /*  LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.listview_selectitems, null, false);

        TextView tv_itmecode = (TextView) header.findViewById(R.id.tv_itmecode);
        TextView tv_itemname = (TextView) header.findViewById(R.id.tv_itemname);
        TextView tv_itemdescription = (TextView) header.findViewById(R.id.tv_itemdescription);

        tv_itmecode.setText("Item Code");
        tv_itemname.setText("Name");
        tv_itemdescription.setText("Available Qty");

        UtilView.setTextAppearanceSmall(this, tv_itmecode);
        UtilView.setTextAppearanceSmall(this, tv_itemname);
        UtilView.setTextAppearanceSmall(this, tv_itemdescription);

        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tv_itmecode);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tv_itemname);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tv_itemdescription);

        UtilView.setTexttypeFace(null, Typeface.BOLD, tv_itmecode);
        UtilView.setTexttypeFace(null, Typeface.BOLD, tv_itemname);
        UtilView.setTexttypeFace(null, Typeface.BOLD, tv_itemdescription);


        listView.addHeaderView(header);*/
        edSearchitem.addTextChangedListener(this);

        toolbar.setTitle(getResources().getString(R.string.select_item));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getItemFromServer();


    }


    @OnClick({R.id.tv_scanitembarcode, R.id.imgview_search})
    public void onClick(View view) {
        switch (view.getId()) {
        /*    case R.id.btn_close:
                finish();
                break;
            case R.id.imageview_close:
                finish();
                break;
*/
            case R.id.tv_scanitembarcode:

                if (callingFrom != null) {
                    Intent intent = new Intent(Activity_ShowItemList.this, Activity_ScannerCode.class);
                    intent.putExtra("callingfrom", callingFrom);
                    startActivity(intent);
                    finish();

                }


                break;

            case R.id.imgview_search:
                UtilView.hideSoftKeyboard(activity, edSearchitem);
                isInternetPresent = serviceHandler.isConnectingToInternet();
                if (isInternetPresent) {
                    // prepare the Request
                    UtilView.showProgessBar(activity, progressBar);
                    GetDataTask task = new GetDataTask(Activity_ShowItemList.this, new GetDataTaskListenr(), false);
                    task.execute(url + edSearchitem.getText().toString().replace(" ", "%20"), "");
                }
                if (!isInternetPresent) {
                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
                }
                break;
        }
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {


    }

    @Override
    public void afterTextChanged(Editable s) {

        if (s.toString().equals("")) {

            HideUtil.init(this);
            UtilView.showProgessBar(activity, progressBar);
            GetDataTask task = new GetDataTask(Activity_ShowItemList.this, new GetDataTaskListenr(), false);
            task.execute(url, "");
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.item_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.addItems:

                Intent intent = new Intent(this, Activity_AddItem.class);
                intent.putExtra("callingfrom", callingFrom);
                startActivity(intent);

                break;


        }
        return super.onOptionsItemSelected(item);
    }

    private class GetDataTaskListenr implements AsyncTaskCompleteListener<String> {


        @Override
        public void onTaskComplete(String result) {

            UtilView.hideProgessBar(activity, progressBar);

            if (result != null) {
                Gson gson = new Gson();
                list.clear();
                if (callingFrom.equals(Constant.NAVIGATION_GROUP_INVENTORY)) {
                    try {
                        List<InventorySelectItemData> itemList = new ArrayList<>();
                        JSONArray jsonArray = new JSONArray(result.toString());
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            UtilView.showLogCat("@Flow", "Item " + jsonObject.toString());
                            InventorySelectItemData getItemDatum = gson.fromJson(jsonObject.toString(), InventorySelectItemData.class);
                            itemList.add(getItemDatum);



                        }
                        if (itemList.size() > 0) {
                            llListview.setVisibility(View.VISIBLE);
                            list.addAll(itemList);
                            itemAdapter = new GetItemAdapter(Activity_ShowItemList.this, list);
                            listView.setAdapter(itemAdapter);
                            itemAdapter.notifyDataSetChanged();
                        } else {
                            list.clear();
                            itemAdapter = new GetItemAdapter(Activity_ShowItemList.this, list);
                            listView.setAdapter(itemAdapter);
                            itemAdapter.notifyDataSetChanged();
                            SweetAlertDialog pDialog = new SweetAlertDialog(Activity_ShowItemList.this, SweetAlertDialog.SUCCESS_TYPE);
                            pDialog.setTitleText(getResources().getString(R.string.items_notavailbale));
                            pDialog.setCancelable(false);
                            pDialog.show();
                        }


                    } catch (Exception e) {
                        UtilView.showLogCat(TAG, "TaskCompleteListener Exception " + e.toString());
                    }

                } else {
                    List<SelectedItemsList> itemList = new ArrayList<>();
                    try {
                        JSONArray jsonArray = new JSONArray(result.toString());
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            SelectedItemsList getItemDatum = gson.fromJson(jsonObject.toString(), SelectedItemsList.class);
                            itemList.add(getItemDatum);

                        }
                        if (itemList.size() > 0) {
                            llListview.setVisibility(View.VISIBLE);
                            list.addAll(itemList);
                            itemAdapter = new GetItemAdapter(Activity_ShowItemList.this, list);
                            listView.setAdapter(itemAdapter);
                            itemAdapter.notifyDataSetChanged();
                        } else {
                            list.clear();
                            itemAdapter = new GetItemAdapter(Activity_ShowItemList.this, list);
                            listView.setAdapter(itemAdapter);
                            itemAdapter.notifyDataSetChanged();
                            SweetAlertDialog pDialog = new SweetAlertDialog(Activity_ShowItemList.this, SweetAlertDialog.SUCCESS_TYPE);
                            pDialog.setTitleText(getResources().getString(R.string.items_notavailbale));
                            pDialog.setCancelable(false);
                            pDialog.show();
                        }


                    } catch (Exception e) {
                        UtilView.showLogCat(TAG, "TaskCompleteListener Exception " + e.toString());
                    }
                }


            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.error_null), activity);

            }
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        if ( dialog!=null && dialog.isShowing() ){
            dialog.cancel();
        }
    }
}


