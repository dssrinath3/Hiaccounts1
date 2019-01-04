package in.hiaccounts.hinext.service.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rey.material.app.Dialog;
import com.rey.material.widget.ProgressView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.model.PageLoadDataForAll;
import in.hiaccounts.hinext.customer.model.Customer;
import in.hiaccounts.hinext.purchase.model.purchase_pagedata.TaxList;
import in.hiaccounts.hinext.purchase.model.purchase_pos.PurchasePosCartItem;
import in.hiaccounts.hinext.sales.model.sales_pos.SalesPosCartItem;
import in.hiaccounts.hinext.service.adapter.Service_ReplaceList_Adapter;
import in.hiaccounts.hinext.service.model.SelectedItemsList;
import in.hiaccounts.hinext.service.model.service_invoice.ServiceSaveData;
import in.hiaccounts.hinext.service.model.service_pos.ServicePosCartItem;
import in.hiaccounts.hinext.service.model.service_pos.ServicePosCreator;
import in.hiaccounts.hinext.service.model.service_pos.ServicePosHelper;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;

import static in.hiaccounts.R.string.response_error;

public class Activity_ReplaceList extends AppCompatActivity implements TextWatcher {
    private static String TAG = Activity_ReplaceList.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edSearch)
    EditText edSearch;
    @BindView(R.id.llSearch)
    LinearLayout llSearch;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.tvItemCode)
    TextView tvItemCode;
    @BindView(R.id.tvItemName)
    TextView tvItemName;
    @BindView(R.id.tvDescription)
    TextView tvDescription;
    @BindView(R.id.lvInvokeList)
    ListView lvInvokeList;
    @BindView(R.id.llListview)
    LinearLayout llListview;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;

    private Activity mActivity;
    private Boolean isInternetPresent = false;
    private ServiceHandler serviceHandler;
    private String serverUrl,invoiceSearch="",formno="", selectedTaxType;;
    private ServicePosCreator servicePosCreator;
    private List<SelectedItemsList> listData;
    private Service_ReplaceList_Adapter adapter;
    private SelectedItemsList replaceData = new SelectedItemsList();
    private SharedPreference sharedPreference;
    public boolean discountRight = false;
    public boolean isCheckoutable = true;
    private Customer selectedCustomer = null;
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
    in.hiaccounts.hinext.item.model.SelectedItemsList itemDatum;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replace_list);
        ButterKnife.bind(this);
        initComponents();
    }

    private void initComponents() {
        ButterKnife.bind(this);
        mActivity = this;
        edSearch.addTextChangedListener(this);
        serverUrl = UtilView.getUrl(mActivity);
        serviceHandler = new ServiceHandler(mActivity);
        final Dialog dialog = new Dialog(mActivity);
        final Intent intent = getIntent();
        sharedPreference = SharedPreference.getInstance(mActivity);
        servicePosCreator = ServicePosHelper.getPosCreator();
        toolbar.setTitle(getResources().getString(R.string.menu_invokerepairlist));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        selectedCustomer = (Customer) intent.getSerializableExtra("customer");

        String getServicePageLoadData = sharedPreference.getData(Constant.GETPAGELOADDATA_KEY);
        final PageLoadDataForAll servicePageData = gson.fromJson(getServicePageLoadData,PageLoadDataForAll.class);




    getReplaceListitem("");

        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    invoiceSearch = edSearch.getText().toString().trim();
                    getReplaceListitem(invoiceSearch);
                }
                return handled;
            }
        });

        lvInvokeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                replaceData = listData.get(position);
                isInternetPresent = serviceHandler.isConnectingToInternet();
                if (serverUrl != null) {
                    if (isInternetPresent) {
                        if (progressBar != null)
                            progressBar.setVisibility(View.VISIBLE);
                        final String url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETSERVICEREPLACEITEM + "?itemCode=" + replaceData.getItemCode();
                        final GetDataTask getOrderDetail = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                            @Override
                            public void onTaskComplete(String result) {
                                if (result != null) {
                                    if (progressBar != null)
                                        progressBar.setVisibility(View.GONE);
                                    try {
                                        Gson gson = new Gson();
                                        listData = new ArrayList<SelectedItemsList>();

                                        JSONArray jsonArray = new JSONArray(result.toString());
                                        if (jsonArray.length()>0){
                                            gson = new Gson();
                                            itemDatum = gson.fromJson(jsonArray.getJSONObject(0).toString(), in.hiaccounts.hinext.item.model.SelectedItemsList.class);
                                            itemDatum.setPrice(new BigDecimal(0.0));
                                            itemDatum.setSerializableNumbers("");


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


                                                        if (selectedCustomer == null) {
                                                            isCheckoutable = false;
                                                            flag = 9;
                                                        } else {
                                                            isCheckoutable = true;
                                                            flag = 0;
                                                        }

                                                        if (edItemUnitPrice.getText().toString() == null || edItemUnitPrice.getText().toString().equals("") ||
                                                                edItemUnitPrice.getText().toString().equals("0.00") || edItemUnitPrice.getText().toString().equals("0")) {
                                                            UtilView.showToast(mActivity, "Unit price can't be Zero");
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
                                                            returnIntent.putExtra("ReplaceItem","Replace Item");
                                                            setResult(Activity.RESULT_OK, returnIntent);
                                                            finish();
                                                            if (dialog != null)
                                                                dialog.dismiss();
                                                        } else {
                                                            if (flag == 1) {
                                                                UtilView.showToast(mActivity, " Item Quantity can't be empty");
                                                            }
                                                            if (flag == 2) {
                                                                UtilView.showToast(mActivity, " Item Quantity can't be zero");
                                                            }
                                                            if (flag == 3) {
                                                                UtilView.showToast(mActivity, " Enter valid quantity");
                                                            }
                                                            if (flag == 4) {
                                                                UtilView.showToast(mActivity, " Unit Price can't be empty");
                                                            }
                                                            if (flag == 5) {
                                                                UtilView.showToast(mActivity, " Enter valid price.");
                                                            }
                                                            if (flag == 6) {
                                                                UtilView.showToast(mActivity, " Enter valid discount price.");
                                                            }
                                                            if (flag == 7) {
                                                                UtilView.showToast(mActivity, " Discount amt should be less than Total Amount.");
                                                            }
                                                            if (flag == 8) {
                                                                UtilView.showToast(mActivity, " Enter valid cess percentage.");
                                                            }
                                                            if (flag == 9) {
                                                                UtilView.showToast(mActivity, " Please Select customer first.");
                                                            }
                                                            if (flag == 10) {
                                                                UtilView.showToast(mActivity, " Please Select supplier first.");
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




                                            edItemQuantity.setText("1");
                                            itemDatum.setItemQuantity(1);

                                            BigDecimal totalPrice = BigDecimal.ZERO;
                                            totalPrice = totalPrice.add(itemDatum.getPrice().multiply(BigDecimal.valueOf(itemDatum.getItemQuantity())));
                                            edItemAmtExTax.setText("" + totalPrice.setScale(2, BigDecimal.ROUND_HALF_UP));
                                            itemDatum.setAmtexclusivetax(Double.parseDouble(String.valueOf(totalPrice)));


                                            if (itemDatum.getCess() != null)
                                                edCessPercantage.setText(itemDatum.getCess());


                                                String defaultTax = "";
                                                if (servicePageData != null) {
                                                    if (servicePageData.getTaxList().size() > 0) {
                                                        UtilView.setupHinextServiceTaxAdapter(mActivity, spinItemInputtax, servicePageData.getTaxList());
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


                                            defaultTax = "";






                                            spinItemInputtax.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                @Override
                                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                                                    spinItemInputtax.setSelection(i);


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


                                        }


                                    } catch (Exception e) {
                                        UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                                    }
                                } else {
                                    UtilView.showErrorDialog(getResources().getString(R.string.error_null), mActivity);
                                }
                            }
                        }, false);
                        getOrderDetail.execute(url, "");
                    } else {
                        UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                    }
                } else {
                    UtilView.gotToLogin(mActivity);
                }



            }
        });
    }

    private void getReplaceListitem(String search) {

        if (servicePosCreator.getItems() != null && servicePosCreator.getItems().size() > 0){

        final String saveData = saveposData();
        String url = "";
        if (search.equals("")) {
            url = serverUrl + "/service//" + Constant.FUNTION_GETSERVICEREPLACEITEMLIST ;
        }


        if (serverUrl!=null) {
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {

                UtilView.showProgessBar(mActivity, progressBar);
                PostDataTask task = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(mActivity, progressBar);
                        if (result != null) {
                            try {
                                listData = new ArrayList<SelectedItemsList>();
                                JSONArray jsonArray = new JSONArray(result.toString());
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject json = jsonArray.getJSONObject(i);
                                    Gson gson = new Gson();
                                    SelectedItemsList paymentInvoice = gson.fromJson(json.toString(), SelectedItemsList.class);
                                    listData.add(paymentInvoice);
                                }
                                if (listData.size() > 0) {
                                    llListview.setVisibility(View.VISIBLE);
                                    adapter = new Service_ReplaceList_Adapter(mActivity, listData);
                                    lvInvokeList.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                } else {
                                    listData.clear();
                                    llListview.setVisibility(View.GONE);
                                    adapter = new Service_ReplaceList_Adapter(mActivity, listData);
                                    lvInvokeList.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                    UtilView.showErrorDialog("No Invoices Available", mActivity);

                                }
                            }catch (Exception e){
                                UtilView.showErrorDialog(getResources().getString(R.string.error_null), mActivity);
                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.error_null), mActivity);
                        }
                    }
                }, false);
                task.execute(saveData,url, Constant.FUNTION_ADVANCEPARTIALPAYMENT);
            }
            if (!isInternetPresent) {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
            }
        }else {
            UtilView.gotToLogin(mActivity);
        }

        }
    }


    private String saveposData() {

        List<ServicePosCartItem> posCartItems = servicePosCreator.getItems();
        Gson gson = new Gson();
        return gson.toJson(posCartItems);

    }


    public String getTaxString() {
        return taxString;
    }

    private void setTaxString(String taxString) {
        this.taxString = taxString;
    }

    private BigDecimal getCessPercantage(String cessPercanteString) {
        return BigDecimal.valueOf(Double.parseDouble(cessPercanteString.toString()));
    }


    private BigDecimal getTax(String tax, in.hiaccounts.hinext.item.model.SelectedItemsList itemDatum) {

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

    private void updateTaxAmount(BigDecimal taxPercantage, in.hiaccounts.hinext.item.model.SelectedItemsList itemDatum, EditText edTaxTypeAmount, EditText edItemAmtExTax, BigDecimal cessPercantage) {
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


            taxAmt = taxAmt.add(amtExTax.subtract(new BigDecimal(discout)).multiply(taxPercantage.divide(BigDecimal.valueOf(100))).setScale(2, BigDecimal.ROUND_HALF_UP));

            BigDecimal cessTaxamt = amtExTax.subtract(new BigDecimal(discout)).multiply(cessPercantage.divide(BigDecimal.valueOf(100))).setScale(2, BigDecimal.ROUND_HALF_UP);

            itemDatum.setCessTaxAmt("" + cessTaxamt);
            edCessAmt.setText("" + cessTaxamt);

            totalTaxAmt = taxAmt.add(cessTaxamt);
            edItemTotalTaxamt.setText("" + totalTaxAmt);
            itemTotalamt = amtExTax.add(totalTaxAmt).subtract(new BigDecimal(discout));

            itemDatum.setAmtinclusivetax(Double.parseDouble(String.valueOf(itemTotalamt.setScale(2, BigDecimal.ROUND_HALF_UP))));
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


    private void updateByQuantityTotalExTax(in.hiaccounts.hinext.item.model.SelectedItemsList itemDatum, int quantity) {
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


    private void updateDiscountPrice(in.hiaccounts.hinext.item.model.SelectedItemsList itemDatum, double discount) {

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


    private void updateItemTotalExTax(in.hiaccounts.hinext.item.model.SelectedItemsList itemDatum, BigDecimal unitPrice) {
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

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
