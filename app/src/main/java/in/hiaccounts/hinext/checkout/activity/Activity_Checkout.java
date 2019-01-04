package in.hiaccounts.hinext.checkout.activity;


import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lowagie.text.DocumentException;
import com.mocoo.hang.rtprinter.driver.Contants;
import com.mocoo.hang.rtprinter.driver.HsBluetoothPrintDriver;
import com.rey.material.widget.ProgressView;
import com.yinglan.keyboard.HideUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.application.activity.NavigationDrawer_Activity;
import in.hiaccounts.hinext.checkout.adapter.BankPayment_Adapter;
import in.hiaccounts.hinext.checkout.adapter.CardPayment_Adapter;
import in.hiaccounts.hinext.checkout.adapter.PaymentType_Adapter;
import in.hiaccounts.hinext.checkout.adapter.VoucherPayment_Adapter;
import in.hiaccounts.hinext.checkout.model.BankPayment;
import in.hiaccounts.hinext.checkout.model.CardPaymentList;
import in.hiaccounts.hinext.checkout.model.CashPayment;
import in.hiaccounts.hinext.checkout.model.CreditPayment;
import in.hiaccounts.hinext.checkout.model.MultiBankPaymentList;
import in.hiaccounts.hinext.checkout.model.MultiCashPaymentList;
import in.hiaccounts.hinext.checkout.model.MultiRedeemPaymentList;
import in.hiaccounts.hinext.checkout.model.MultiVoucherPayment;
import in.hiaccounts.hinext.checkout.model.PaymentType;
import in.hiaccounts.hinext.checkout.model.PurchaseItem;
import in.hiaccounts.hinext.checkout.model.PurchaseSaveData;
import in.hiaccounts.hinext.checkout.model.VoucherPayment;
import in.hiaccounts.hinext.checkout.model.sales.Checkout_Sales_ResponseData;
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.configuration.ConfigurationData;
import in.hiaccounts.hinext.customer.model.Customer;
import in.hiaccounts.hinext.generaltransaction.model.AccountPosCreator;
import in.hiaccounts.hinext.generaltransaction.model.AccountPosHelper;
import in.hiaccounts.hinext.generaltransaction.model.response.SaveResponseData;
import in.hiaccounts.hinext.generaltransaction.model.response.SelectedAccountList;
import in.hiaccounts.hinext.purchase.model.SaveRemaining_Payment;
import in.hiaccounts.hinext.purchase.model.purchase_payments.PurchaseAdvancePayment;
import in.hiaccounts.hinext.purchase.model.purchase_pos.PurchasePosCreator;
import in.hiaccounts.hinext.purchase.model.purchase_pos.PurchasePosHelper;
import in.hiaccounts.hinext.purchase.model.purchase_saveresponses.Checkout_ResponseData;
import in.hiaccounts.hinext.purchase.model.purchase_saveresponses.Purchase_PaymentResponse;
import in.hiaccounts.hinext.sales.model.sales_pos.SalesPosCreator;
import in.hiaccounts.hinext.sales.model.sales_pos.SalesPosHelper;
import in.hiaccounts.hinext.sales.pos_printer.DeviceListActivity;
import in.hiaccounts.hinext.sales.pos_printer.PrintReceipt;
import in.hiaccounts.hinext.supplier.model.GetSupplier;
import in.hiaccounts.pdfgenerator.PdfUtils;
import in.hiaccounts.pdfgenerator.generaltransaction.GTExpenseVoucherPdf;
import in.hiaccounts.pdfgenerator.generaltransaction.GTExpenseVoucherPdfImpl;
import in.hiaccounts.pdfgenerator.purchase.payment.PosA4PurchasePaymentPdfImpl;
import in.hiaccounts.pdfgenerator.purchase.payment.PosPurchasePaymetPdf;
import in.hiaccounts.pdfgenerator.purchase.purchase_inovices.PosA4PurchaseInvociePdfImpl;
import in.hiaccounts.pdfgenerator.purchase.purchase_inovices.PosPurchaseInvoicePdf;
import in.hiaccounts.pdfgenerator.sales.salesinvoice.PosA4SalesInvociePdfImpl;
import in.hiaccounts.pdfgenerator.sales.salesinvoice.PosSalesInvoicePdf;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.NonScrollListView;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;
import in.hiaccounts.utility.Validation;

import static in.hiaccounts.R.id.resetConnection;

/**
 * Created by Prateek on 2/9/2017.
 */

public class Activity_Checkout extends AppCompatActivity implements AdapterView.OnItemClickListener {
    public static final int CASE_SAVE_PRINT = 0;
    public static final int CASE_SAVE_EMAIL_EXIST = 1;
    public static final int CASE_SAVE_EMAIL_INPUT = 2;
    public static final int CASE_DRAFT_PRINT = 3;
    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 123;
    private static final int REQUEST_CONNECT_DEVICE = 1;
    private static final int REQUEST_ENABLE_BT = 2;
    public static String TAG = Activity_Checkout.class.getSimpleName();
    public static HsBluetoothPrintDriver BLUETOOTH_PRINTER = null;
    private static TextView tvPrinterStatus = null;
    private static Context CONTEXT;
    private static BluetoothDevice device;
    @BindView(R.id.tv_bankicon)
    TextView tvBankicon;
    @BindView(R.id.nonscrolllistview_bank)
    NonScrollListView nonscrolllistviewBank;
    @BindView(R.id.ll_banklayuout)
    LinearLayout llBanklayuout;
    @BindView(R.id.ed_tcs)
    EditText edTcs;
    @BindView(R.id.ed_tds)
    EditText edTds;
    @BindView(R.id.btnPrint)
    Button btnPrint;
    @BindView(R.id.chbx_cash)
    CheckBox chbxCash;
    @BindView(R.id.chbx_card)
    CheckBox chbxCard;
    @BindView(R.id.chbx_voucher)
    CheckBox chbxVoucher;
    @BindView(R.id.tv_cashicon)
    TextView tvCashicon;
    @BindView(R.id.tv_cardicon)
    TextView tvCardicon;
    @BindView(R.id.tv_voicon)
    TextView tvVoicon;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ed_totalamount)
    EditText edTotalamount;
    @BindView(R.id.ed_totaldiscount)
    EditText edTotaldiscount;
    @BindView(R.id.ed_tenderedamt)
    EditText edTenderedamt;
    @BindView(R.id.ed_returnamount)
    EditText edReturnamount;
    @BindView(R.id.ll_cashlayuout)
    LinearLayout llCashlayuout;
    @BindView(R.id.nonscrolllistview_credit)
    NonScrollListView nonscrolllistviewCredit;
    @BindView(R.id.ll_cardlayuout)
    LinearLayout llCardlayuout;
    @BindView(R.id.nonscrolllistview_voucher)
    NonScrollListView nonscrolllistviewVoucher;
    @BindView(R.id.ll_vopucherlayuout)
    LinearLayout llVopucherlayuout;
    @BindView(R.id.btn_saveprint)
    Button btnSaveprint;
    @BindView(R.id.btn_saveemail)
    Button btnSaveemail;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.btn_draftprint)
    Button btn_draftprint;
    @BindView(R.id.ll_bottomlayout)
    LinearLayout llBottomlayout;
    @BindView(R.id.ed_cashamounttendered)
    EditText edCashamounttendered;
    @BindView(R.id.ed_emailid)
    EditText edEmailid;
    @BindView(R.id.ll_email)
    LinearLayout llEmail;
    @BindView(R.id.edSupplierInvNo)
    EditText edSupplierInvNo;
    @BindView(R.id.llSupplierInvoiceNo)
    LinearLayout llSupplierInvoiceNo;
    @BindView(R.id.edMemo)
    EditText edMemo;
    @BindView(R.id.llMemo)
    LinearLayout llMemo;
    @BindView(R.id.rlContent)
    RelativeLayout rlContent;
    @BindView(R.id.recycleViewPaymentType)
    RecyclerView recycleViewPaymentType;
    CashPayment cashPayment = new CashPayment();
    PaymentType_Adapter paymentTypeAdapter;
    Intent serverIntent = null;
    private Purchase_PaymentResponse paymentResponseData = null;
    private SaveResponseData saveResponseData;
    private SharedPreference sharedPreference;
    private Typeface font;
    private CardPayment_Adapter cardPaymentAdapter;
    private VoucherPayment_Adapter voucherPaymentAdapter;
    private BankPayment_Adapter bankPaymentAdapter;
    private Activity activity;
    private List<CardPaymentList> cardPaymentList = new ArrayList<CardPaymentList>();
    private List<MultiVoucherPayment> voucherPaymentList = new ArrayList<MultiVoucherPayment>();
    private List<MultiBankPaymentList> bankPaymentList = new ArrayList<MultiBankPaymentList>();
    private PurchaseSaveData purchaseSaveData;
    private String input_email = "";
    private Boolean isInternetPresent = false;
    private Boolean exportInvoice = true;
    private PurchaseAdvancePayment paymentInvoice;
    private String callingfrom = "", serverUrl;
    private GetSupplier supplier;
    private ServiceHandler serviceHandler;
    private String selectedTax = "", selectedDate = "";
    private Checkout_ResponseData invoiceData = null;
    private Checkout_Sales_ResponseData salesInvoiceData=null;
    private int currentAPIVersion = Build.VERSION.SDK_INT;
    private String selectedTaxType = "",jsonsaveData="", checkoutType = null, printerStatus = "", printerType = "";
    private SalesPosCreator salesPosCreator;
    private PurchasePosCreator posCreator;
    private Customer customer = null;
    private ConfigurationData configData;
    private AccountPosCreator accountPosCreator;
    private AlertDialog.Builder alertDlgBuilder;
    private BluetoothAdapter mBluetoothAdapter = null;
    private String purchaseQuotationId,purchaseOrderId,purchaseReciveItemid,salesdeliveryOrderId,export;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_new);
        ButterKnife.bind(this);
        initComponent();

        setonClcik();
    }

    private void setonClcik() {
        nonscrolllistviewCredit.setOnItemClickListener(this);
        nonscrolllistviewVoucher.setOnItemClickListener(this);
        nonscrolllistviewBank.setOnItemClickListener(this);
    }

    private void initComponent() {
        ButterKnife.bind(this);
        tvPrinterStatus = (TextView) findViewById(R.id.tvPrinterStatus);
        CONTEXT = getApplicationContext();

        salesPosCreator = SalesPosHelper.getPosCreator();
        posCreator = PurchasePosHelper.getPosCreator();
        accountPosCreator = AccountPosHelper.getPosCreator();

        alertDlgBuilder = new AlertDialog.Builder(Activity_Checkout.this);

        // Get device's Bluetooth adapter
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        // If the adapter is null, then Bluetooth is not available in your device
        if (mBluetoothAdapter == null) {
            tvPrinterStatus.setText("Bluetooth is not available");
        }


        activity = this;
        serverUrl = UtilView.getUrl(activity);
        sharedPreference = SharedPreference.getInstance(this);
        serviceHandler = new ServiceHandler(activity);
        rlContent.setVisibility(View.VISIBLE);


        llSupplierInvoiceNo.setVisibility(View.VISIBLE);
        llMemo.setVisibility(View.VISIBLE);


        Intent intent = getIntent();
        callingfrom = intent.getStringExtra("callingfrom");


        if (callingfrom.equals(Constant.NAVIGATION_GROUP_PURCHASE)) {
            jsonsaveData = intent.getStringExtra("purchaseSaveData");
        }else if (callingfrom.equals(Constant.NAVIGATION_GROUP_RETAIL)) {
            jsonsaveData = intent.getStringExtra("saveData");
        } else if (callingfrom.equals(Constant.NAVIGATION_GROUP_GENERALTRANSACTION_EXPENSE)) {
            jsonsaveData = intent.getStringExtra("GTSaveData");

        }else if (callingfrom.equals(Constant.NAVIGATION_GROUP_GENERALTRANSACTION_RECIEPT)){
            jsonsaveData = intent.getStringExtra("GTSaveData");

        }




        supplier = (GetSupplier) intent.getSerializableExtra("supplier");


        customer = (Customer) intent.getSerializableExtra("customer");
        paymentInvoice = (PurchaseAdvancePayment) intent.getSerializableExtra("paymentInvoice");

        selectedTax = (String) intent.getSerializableExtra("selectedTax");
        checkoutType = intent.getStringExtra("checkoutType");
        selectedDate = (String) intent.getSerializableExtra("selectedDate");


        UtilView.showLogCat("remaining  ",checkoutType);


        exportInvoice = intent.getBooleanExtra(Constant.EXPORTINVOICE,false);






        toolbar.setTitle(getResources().getString(R.string.payment));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (isInternetPresent) {

            getPaymentTypeDataFromServer();
            getConfigurationDataFromServer();
        } else {
            UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
        }

        if (Constant.discountRight) {
            edTotaldiscount.setVisibility(View.VISIBLE);
            edTotaldiscount.setFocusable(true);
            edTotaldiscount.setFocusableInTouchMode(true);
            edTotaldiscount.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        } else {
            edTotaldiscount.setVisibility(View.VISIBLE);
            edTotaldiscount.setFocusable(false);
            edTotaldiscount.setFocusableInTouchMode(false);
            edTotaldiscount.setBackgroundColor(getResources().getColor(R.color.colorCheckbox));
        }

        if (intent != null) {
            if (callingfrom.equals(Constant.NAVIGATION_GROUP_PURCHASE)) {
                if (jsonsaveData != null) {
                    Gson gson = new Gson();
                    purchaseSaveData = gson.fromJson(jsonsaveData, PurchaseSaveData.class);

                    edTotalamount.setText("" + purchaseSaveData.getTotalCheckOutamt());
                    edTotaldiscount.setText("" + purchaseSaveData.getDiscountAmount());
                }
                if (paymentInvoice != null) {
                    llSupplierInvoiceNo.setVisibility(View.GONE);
                    llMemo.setVisibility(View.GONE);
                    btnSaveemail.setVisibility(View.GONE);
                    edTotalamount.setText("" + paymentInvoice.getApBalance());
                }
            }

            if (callingfrom.equals(Constant.NAVIGATION_GROUP_RETAIL)) {

                if (jsonsaveData != null) {
                    Gson gson = new Gson();
                    purchaseSaveData = gson.fromJson(jsonsaveData, PurchaseSaveData.class);
                    edTotalamount.setText(String.format("%.2f", purchaseSaveData.getTotalCheckOutamt()));
                    edTotaldiscount.setText(String.format("%.2f", purchaseSaveData.getDiscountAmount()));
                }

                if(checkoutType != null && checkoutType.equals(Constant.SALESDRAFTINVOICE) )
                {
                    llSupplierInvoiceNo.setVisibility(View.GONE);
                    llMemo.setVisibility(View.GONE);
                    btnSaveemail.setVisibility(View.GONE);
                    btnSaveprint.setVisibility(View.GONE);
                }
                else if(checkoutType != null && checkoutType.equals(Constant.CHECKOUTYPE_INVOKESALESORDER)) {


                    llSupplierInvoiceNo.setVisibility(View.GONE);
                    llMemo.setVisibility(View.VISIBLE);
                    btnSaveemail.setVisibility(View.VISIBLE);
                    btnSaveprint.setVisibility(View.VISIBLE);
                } else if(checkoutType != null && checkoutType.equals("SalesCheckout")) {


                    llSupplierInvoiceNo.setVisibility(View.GONE);
                    llMemo.setVisibility(View.VISIBLE);
                    btnSaveemail.setVisibility(View.VISIBLE);
                    btnSaveprint.setVisibility(View.VISIBLE);
                }
                else if (checkoutType == null){

                    llSupplierInvoiceNo.setVisibility(View.GONE);
                    llMemo.setVisibility(View.VISIBLE);
                    btnSaveemail.setVisibility(View.VISIBLE);
                    btnSaveprint.setVisibility(View.VISIBLE);
                }


                if (paymentInvoice != null) {
                    llSupplierInvoiceNo.setVisibility(View.GONE);
                    llMemo.setVisibility(View.GONE);
                    btnSaveemail.setVisibility(View.GONE);
                    edTotalamount.setText(String.format("%.2f", paymentInvoice.getArbalance()));

                }
            }

            if (callingfrom.equals(Constant.NAVIGATION_GROUP_GENERALTRANSACTION_EXPENSE)) {

                if (jsonsaveData != null) {
                    Gson gson = new Gson();
                    purchaseSaveData = gson.fromJson(jsonsaveData, PurchaseSaveData.class);
                    edTotalamount.setText(String.format("%.2f", purchaseSaveData.getTotalCheckOutamt()));
                    edTotaldiscount.setText(String.format("%.2f", purchaseSaveData.getDiscountAmount()));
                }

                if (checkoutType !=null && checkoutType.equals(Constant.GENERAL_TRANSACTION_SAVEINVOICE))
                {
                    llSupplierInvoiceNo.setVisibility(View.GONE);
                    llMemo.setVisibility(View.GONE);
                    btnSaveemail.setVisibility(View.VISIBLE);
                    btnSaveprint.setVisibility(View.VISIBLE);
                    btn_draftprint.setVisibility(View.GONE);
                }else{
                    llSupplierInvoiceNo.setVisibility(View.GONE);
                    llMemo.setVisibility(View.GONE);
                    btnSaveemail.setVisibility(View.VISIBLE);
                    btnSaveprint.setVisibility(View.VISIBLE);
                    btn_draftprint.setVisibility(View.GONE);
                }

            }
            if (callingfrom.equals(Constant.NAVIGATION_GROUP_GENERALTRANSACTION_RECIEPT)) {

                if (jsonsaveData != null) {
                    Gson gson = new Gson();
                    purchaseSaveData = gson.fromJson(jsonsaveData, PurchaseSaveData.class);
                    edTotalamount.setText(String.format("%.2f", purchaseSaveData.getTotalCheckOutamt()));
                    edTotaldiscount.setText(String.format("%.2f", purchaseSaveData.getDiscountAmount()));
                }

                if (checkoutType !=null && checkoutType.equals(Constant.GENERAL_TRANSACTION_SAVEINVOICE))
                {
                    llSupplierInvoiceNo.setVisibility(View.GONE);
                    llMemo.setVisibility(View.GONE);
                    btnSaveemail.setVisibility(View.VISIBLE);
                    btnSaveprint.setVisibility(View.VISIBLE);
                    btn_draftprint.setVisibility(View.GONE);
                }else{
                    llSupplierInvoiceNo.setVisibility(View.GONE);
                    llMemo.setVisibility(View.GONE);
                    btnSaveemail.setVisibility(View.VISIBLE);
                    btnSaveprint.setVisibility(View.VISIBLE);
                    btn_draftprint.setVisibility(View.GONE);
                }

            }

        }
        font = Typeface.createFromAsset(getAssets(), "fonts/fontawesome-webfont.ttf");
        tvCashicon.setTypeface(font);
        tvCardicon.setTypeface(font);
        tvVoicon.setTypeface(font);



        edSupplierInvNo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("") || s.toString() == null) {
                    edSupplierInvNo.setError(getString(R.string.supplierInvoiceValidation));
                } else {
                    edSupplierInvNo.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edTotalamount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.equals("")) {
                    edTotaldiscount.setText("0.00");
                    edTotaldiscount.setError(null);
                } else {
                    try {
                        edTotaldiscount.setError(null);
                    } catch (NumberFormatException e) {
                        edTotaldiscount.setError(getResources().getString(R.string.error_numberformate));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        edCashamounttendered.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.equals("")) {
                    edCashamounttendered.setError(null);
                } else {
                    try {
                        edCashamounttendered.setError(null);
                        setTenderedAmount();
                        setReturnAmount();
                    } catch (NumberFormatException e) {
                        edCashamounttendered.setError(getResources().getString(R.string.error_numberformate));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        edTotaldiscount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.equals("")) {
                    edTotaldiscount.setError(null);
                } else {
                    try {
                        edTotaldiscount.setError(null);
                        setTenderedAmount();
                        setReturnAmount();
                    } catch (NumberFormatException e) {
                        edTotaldiscount.setError(getResources().getString(R.string.error_numberformate));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        chbxCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chbxCash.isChecked()) {
                    llCashlayuout.setVisibility(View.VISIBLE);
                } else {
                    llCashlayuout.setVisibility(View.GONE);
                    edCashamounttendered.setText("0");

                }
            }
        });


        chbxCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chbxCard.isChecked()) {
                    llCardlayuout.setVisibility(View.VISIBLE);
                    cardPaymentList.add(new CardPaymentList());
                    Toast.makeText(activity, "card Check Size"+cardPaymentList, Toast.LENGTH_SHORT).show();
                    Log.e("card Check Size", String.valueOf(cardPaymentList.size()));

                    cardPaymentAdapter = new CardPayment_Adapter(activity, cardPaymentList);
                    nonscrolllistviewCredit.setAdapter(cardPaymentAdapter);
                    cardPaymentAdapter.notifyDataSetChanged();
                } else {
                    llCardlayuout.setVisibility(View.GONE);
                    cardPaymentList.clear();
                    /*cardPaymentAdapter = new CardPayment_Adapter(activity, cardPaymentList);
                    nonscrolllistviewCredit.setAdapter(cardPaymentAdapter);*/
                    cardPaymentAdapter.notifyDataSetChanged();
                }
            }
        });

        chbxVoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chbxVoucher.isChecked()) {
                    llVopucherlayuout.setVisibility(View.VISIBLE);
                    voucherPaymentList.add(new MultiVoucherPayment());
                    voucherPaymentAdapter = new VoucherPayment_Adapter(activity, voucherPaymentList);
                    nonscrolllistviewVoucher.setAdapter(voucherPaymentAdapter);
                    voucherPaymentAdapter.notifyDataSetChanged();
                } else {
                    llVopucherlayuout.setVisibility(View.GONE);
                    voucherPaymentList.clear();
                    voucherPaymentAdapter = new VoucherPayment_Adapter(activity, voucherPaymentList);
                    nonscrolllistviewVoucher.setAdapter(voucherPaymentAdapter);
                    voucherPaymentAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void getConfigurationDataFromServer() {
        String url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETCONFIGURATIONDATA;
        UtilView.showProgessBar(activity, progressBar);
        PostDataTask task = new PostDataTask(activity, new AsyncTaskCompleteListener<String>() {
            @Override
            public void onTaskComplete(String result) {
                HideUtil.init(activity);
                UtilView.hideProgessBar(activity, progressBar);
                if (result != null) {
                    if (result.equals("invalid")) {
                        UtilView.showToast(activity, getResources().getString(R.string.accesstoken_error));
                        Intent intent = new Intent(activity, Activity_Login.class);
                        activity.startActivity(intent);
                        activity.finish();
                    } else {
                        try {

                            JSONObject jsonObject = new JSONObject(result.toString());
                            Gson gson = new Gson();
                            configData = gson.fromJson(jsonObject.toString(), ConfigurationData.class);
                            if (rlContent != null)
                                rlContent.setVisibility(View.VISIBLE);

                            if (configData != null) {
                                if (configData.getPrintType() != null && configData.getPrintType().equals(Constant.PRINTYPE_POS)) {

                                    invalidateOptionsMenu();
                                    tvPrinterStatus.setVisibility(View.VISIBLE);
                                    tvPrinterStatus.setText(R.string.title_connection);
                                    if (mBluetoothAdapter != null) {
                                        if (!mBluetoothAdapter.isEnabled()) {
                                            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                                            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
                                            // Otherwise, setup the chat session
                                        } else {
                                            if (BLUETOOTH_PRINTER == null) {
                                                initializeBluetoothDevice();
                                            } else {
                                                if (BLUETOOTH_PRINTER.IsNoConnection()) {
                                                    tvPrinterStatus.setText("Printer is offline");
                                                } else {
                                                    tvPrinterStatus.setText(R.string.title_connected_to);
                                                    tvPrinterStatus.append(device.getName());

                                                }
                                            }

                                        }
                                    } else {
                                        tvPrinterStatus.setText(R.string.bluettoth_doesnt);
                                    }

                                }
                            }
                        } catch (Exception e) {
                            UtilView.showLogCat(TAG, "getBankAccountFromserver Exception " + e.toString());
                        }
                    }
                } else {
                    UtilView.showErrorDialog("Some Error", activity);
                }
            }
        }, false);
        task.execute(new Gson().toString(), url, "");
    }

    private void initializeBluetoothDevice() {
        Log.d(TAG, "setupChat()");
        // Initialize HsBluetoothPrintDriver class to perform bluetooth connections
        BLUETOOTH_PRINTER = HsBluetoothPrintDriver.getInstance();//
        BLUETOOTH_PRINTER.setHandler(new BluetoothHandler(Activity_Checkout.this));
    }

    @OnClick({R.id.btn_draftprint, R.id.btn_saveprint, R.id.btn_saveemail, R.id.btn_cancel})
    public void onViewClicked(View view) {
        String url = serverUrl;
        //  String url = UtilView.getRetailUrl(sharedPreference);
        String checkoutData = "";
        PostDataTask postDataTask;
        switch (view.getId()) {


            case R.id.btn_draftprint:
                if (purchaseSaveData != null) {
                    callCheckout("Draft");
                }
                break;
            case R.id.btn_saveprint:
                if (callingfrom.equals(Constant.NAVIGATION_GROUP_GENERALTRANSACTION_EXPENSE)) {
                    if (purchaseSaveData != null) {

                        callCheckout("Save");
                    }

                }
                if (callingfrom.equals(Constant.NAVIGATION_GROUP_GENERALTRANSACTION_RECIEPT)) {
                    if (purchaseSaveData != null) {

                        callCheckout("Save");
                    }

                }
                if (callingfrom.equals(Constant.NAVIGATION_GROUP_PURCHASE)) {
                    if (purchaseSaveData != null) {

                        callCheckout("Save");
                    }
                    if (paymentInvoice != null) {
                        callAdvanceRemainingPayment();
                    }
                }
                if (callingfrom.equals(Constant.NAVIGATION_GROUP_RETAIL)) {

                    if (configData != null) {
                        if (configData.getPrintType() != null) {
                            if (configData.getPrintType().equals(Constant.PRINTYPE_POS)) {
                                if (BLUETOOTH_PRINTER != null) {
                                    if (BLUETOOTH_PRINTER.IsNoConnection()) {

                                        UtilView.showToast(activity, getResources().getString(R.string.title_connection));
                                    } else {
                                        callCheckout("Save");

                                    }
                                }
                               // callCheckout("Save");
                            } else {
                                if (purchaseSaveData != null) {

                                    callCheckout("Save");
                                }

                            }
                        } else {
                            if (purchaseSaveData != null) {

                                callCheckout("Save");
                            }

                        }

                    } else {

                        if (purchaseSaveData != null) {

                            callCheckout("Save");
                        }
                    }

                    if (paymentInvoice != null) {
                        callAdvanceRemainingPayment();
                    }

                }







                break;

            case R.id.btn_saveemail:
                if (purchaseSaveData != null) {
                    if (purchaseSaveData.getSupplierEmail() != null && !purchaseSaveData.getSupplierEmail().equals("")) {
                        checkoutData = getCheckoutData(CASE_SAVE_EMAIL_EXIST);
                        isInternetPresent = serviceHandler.isConnectingToInternet();
                        if (serverUrl != null) {
                            if (isInternetPresent) {
                                UtilView.showProgessBar(activity, progressBar);
                                postDataTask = new PostDataTask(activity, new TaskCompleteListener(CASE_SAVE_EMAIL_EXIST), false);
                                postDataTask.execute(checkoutData, url + "/purchase//2/" + Constant.FUNTION_INVOICESAVEANDMAIL, "");
                            } else {
                                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
                            }
                        } else {
                            UtilView.gotToLogin(activity);
                        }
                    } else {
                        checkoutData = getCheckoutData(CASE_SAVE_EMAIL_INPUT);
                        llEmail.setVisibility(View.VISIBLE);
                        input_email = edEmailid.getText().toString().trim();
                        if (input_email.equals("")) {
                            edEmailid.setError(getResources().getString(R.string.err_msg));
                        } else {
                            if (Validation.isValidEmail(input_email)) {
                                isInternetPresent = serviceHandler.isConnectingToInternet();
                                if (serverUrl != null) {
                                    if (isInternetPresent) {
                                        if (purchaseSaveData.isAdvancepayment()) {
                                            UtilView.showProgessBar(activity, progressBar);
                                            postDataTask = new PostDataTask(activity, new TaskCompleteListener(CASE_SAVE_EMAIL_INPUT), false);
                                            postDataTask.execute(checkoutData, url + "/purchase//2/" + Constant.FUNTION_INVOICESAVEANDMAIL, "");
                                        } else {
                                            if (edReturnamount.getText().toString().trim().contains("-")) {
                                                UtilView.showToast(activity, "Full amount not recieved");
                                            } else {
                                                UtilView.showProgessBar(activity, progressBar);
                                                postDataTask = new PostDataTask(activity, new TaskCompleteListener(CASE_SAVE_EMAIL_INPUT), false);
                                                postDataTask.execute(checkoutData, url + "/purchase//2/" + Constant.FUNTION_INVOICESAVEANDMAIL, "");
                                            }
                                        }
                                    } else {
                                        UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
                                    }
                                } else {
                                    UtilView.gotToLogin(activity);
                                }
                            } else {
                                input_email = "";
                                edEmailid.setError(getResources().getString(R.string.err_msg_email));
                            }

                        }
                    }
                }
                edEmailid.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (s.equals("")) {
                        } else {
                            if (Validation.isValidEmail(s.toString())) {
                                edEmailid.setError(null);
                            } else {
                                edEmailid.setError(getResources().getString(R.string.err_msg_email));
                            }
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });
                break;
            case R.id.btn_cancel:
                finish();
                setTenderedAmount();
                break;
        }
    }



    private void callAdvanceRemainingPayment() {
        String url = serverUrl;
        String checkoutData = "";
        PostDataTask postDataTask;
        checkoutData = getCheckoutData(CASE_SAVE_PRINT);

        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
                UtilView.showProgessBar(activity, progressBar);

                String finalurl = "";
                if (callingfrom.equals(Constant.NAVIGATION_GROUP_PURCHASE)) {
                    finalurl = url + "/purchase//0/" + Constant.FUNTION_ADVANCEPARTIALPAYMENT + "/" + paymentInvoice.getFormNo();
                }
                if (callingfrom.equals(Constant.NAVIGATION_GROUP_RETAIL)) {
                    finalurl = url + "/retail//" + Constant.FUNTION_ADVANCEPARTIALPAYMENT + "?formNo=" + paymentInvoice.getFormNo();
                }


                postDataTask = new PostDataTask(activity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(activity, progressBar);
                        if (result != null) {
                            Gson gson = new Gson();
                            try {

                                paymentResponseData = gson.fromJson(result.toString(), Purchase_PaymentResponse.class);
                                if (paymentResponseData != null) {
                                    if (currentAPIVersion >= Build.VERSION_CODES.M) {
                                        checkPermission();
                                    } else {
                                        createPaymentPdf();
                                    }
                                    UtilView.showToast(activity, "Recieve Payment successfully created.");
                                } else {
                                    UtilView.showToast(activity, "Please try again");
                                }


                            } catch (Exception e) {
                                UtilView.showErrorDialog(getResources().getString(R.string.response_error), activity);
                            }
                        }
                    }
                }, false);
                postDataTask.execute(checkoutData,finalurl , Constant.FUNTION_ADVANCEPARTIALPAYMENT);
            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
            }
        } else {
            UtilView.gotToLogin(activity);
        }
    }

    private void callCheckout(String checkoutType) {
        String url = serverUrl;
        //  String url = UtilView.getRetailUrl(sharedPreference);
        String checkoutData = "";
        PostDataTask postDataTask;

        checkoutData = getCheckoutData(CASE_SAVE_PRINT);
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
                String finalUrl = "";
                if (callingfrom.equals(Constant.NAVIGATION_GROUP_PURCHASE)) {
                    if (edSupplierInvNo.getText().toString() != null && !edSupplierInvNo.getText().toString().equals("")) {
                        if (checkoutType.equals("Save")) {
                            finalUrl = url + "/purchase//0/" + Constant.FUNTION_INVOICESAVE;
                        } else if (checkoutType.equals("Draft")) {
                            finalUrl = url + "/purchase//0/" + Constant.FUNTION_INVOICEDRAFTANDPRINT;
                        }

                        if (!finalUrl.equals("")) {
                            UtilView.showProgessBar(activity, progressBar);
                            postDataTask = new PostDataTask(activity, new TaskCompleteListener(CASE_SAVE_PRINT), false);
                            postDataTask.execute(checkoutData, finalUrl, "");
                        }

                    } else {
                        edSupplierInvNo.setError(getResources().getString(R.string.supplierInvoiceValidation));
                    }
                } else if (callingfrom.equals(Constant.NAVIGATION_GROUP_RETAIL)) {



                    if (checkoutType.equals("Save")) {
                        finalUrl = url + "/retail//" + Constant.FUNTION_INVOICESAVE;
                    } else if (checkoutType.equals("Draft")) {
                        finalUrl = url + "/retail//" + Constant.FUNTION_SALESINVOICEDRAFTANDPRINT;
                    }
                    if (!finalUrl.equals("")) {
                        UtilView.showProgessBar(activity, progressBar);
                        postDataTask = new PostDataTask(activity, new TaskCompleteListener(CASE_SAVE_PRINT), false);
                        postDataTask.execute(checkoutData, finalUrl, "");
                    }

                } else if (callingfrom.equals(Constant.NAVIGATION_GROUP_GENERALTRANSACTION_EXPENSE)) {

                    if (checkoutType.equals("Save")) {
                        finalUrl = url + "/generalTransaction//0/" + Constant.FUNTION_GENERALTRANSACTIONINVOICESAVE;
                    } else if (checkoutType.equals("Draft")) {
                        finalUrl = url + "/generalTransaction//0/" + Constant.FUNTION_SALESINVOICEDRAFTANDPRINT;
                    }
                    if (!finalUrl.equals("")) {
                        UtilView.showProgessBar(activity, progressBar);
                        postDataTask = new PostDataTask(activity, new TaskCompleteListener(CASE_SAVE_PRINT), false);
                        postDataTask.execute(checkoutData, finalUrl, "");
                    }

                } else if (callingfrom.equals(Constant.NAVIGATION_GROUP_GENERALTRANSACTION_RECIEPT)) {

                    if (checkoutType.equals("Save")) {
                        finalUrl = url + "/generalTransaction//0/" + Constant.FUNTION_GENERALTRANSACTIONRECIEPTINVOICESAVE;
                    } else if (checkoutType.equals("Draft")) {
                        finalUrl = url + "/generalTransaction//0/" + Constant.FUNTION_SALESINVOICEDRAFTANDPRINT;
                    }
                    if (!finalUrl.equals("")) {
                        UtilView.showProgessBar(activity, progressBar);
                        postDataTask = new PostDataTask(activity, new TaskCompleteListener(CASE_SAVE_PRINT), false);
                        postDataTask.execute(checkoutData, finalUrl, "");
                    }

                }


            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
            }
        } else {
            UtilView.gotToLogin(activity);
        }

    }


    private String getCheckoutData(int caseSavePrint) {

        Gson gson = new Gson();
        double totalCheckoutAmt;
        double totalTaxAmt;
        String customerEmail = "";
        String supplierEmail = "";
        String jsonPaymentData = null;


        switch (caseSavePrint) {

            case CASE_SAVE_PRINT:

                if (callingfrom.equals(Constant.NAVIGATION_GROUP_PURCHASE)) {
                    if (purchaseSaveData != null) {
                        supplierEmail = purchaseSaveData.getSupplierEmail();
                    }

                }

                if (callingfrom.equals(Constant.NAVIGATION_GROUP_RETAIL)) {
                    if (purchaseSaveData != null) {
                        customerEmail = purchaseSaveData.getCustomerEmail();
                    }
                    if (paymentInvoice != null) {
                        customerEmail = paymentInvoice.getCustomerEmail();
                    }

                }


                break;

            case CASE_SAVE_EMAIL_EXIST:
                if (purchaseSaveData != null) {
                    supplierEmail = purchaseSaveData.getSupplierEmail();
                }
                if (callingfrom.equals(Constant.NAVIGATION_GROUP_RETAIL)) {
                    if (purchaseSaveData != null) {
                        customerEmail = purchaseSaveData.getCustomerEmail();
                    }
                }
                break;

            case CASE_SAVE_EMAIL_INPUT:

                supplierEmail = input_email;

                if (callingfrom.equals(Constant.NAVIGATION_GROUP_RETAIL)) {
                    customerEmail = input_email;
                }
                break;
        }

        String amt = edReturnamount.getText().toString().trim();
        if (amt.equals("")) {
            amt = "0.00";
        }
        double amountReturned = Double.parseDouble(String.valueOf(new BigDecimal(amt).setScale(2, RoundingMode.HALF_UP)));
        String dis = (edTotaldiscount.getText().toString().trim());
        if (dis.equals("")) {
            dis = "0.00";
        }
        double discountAmount = Double.parseDouble(String.valueOf(new BigDecimal(dis).setScale(2, RoundingMode.HALF_UP)));

        String tender = (edTenderedamt.getText().toString().trim());
        if (tender.equals("")) {
            tender = "0.00";
        }
        String totalTenderedAmount = edCashamounttendered.getText().toString().trim();
        if (totalTenderedAmount == null || totalTenderedAmount.equals("")) {
            totalTenderedAmount = "0.00";
        }

        BigDecimal big2 = new BigDecimal(totalTenderedAmount);
        double tenderedAmount = Double.parseDouble(String.valueOf(big2.setScale(2, RoundingMode.HALF_UP)));


        List<MultiRedeemPaymentList> multiRedeemPaymentLists = new ArrayList<>();
        MultiRedeemPaymentList redeemPaymentList = new MultiRedeemPaymentList();

        List<MultiCashPaymentList> multiCashPaymentLists = new ArrayList<>();
        MultiCashPaymentList cashPaymentList = new MultiCashPaymentList();
        cashPaymentList.setCashAmt(tenderedAmount);
        cashPaymentList.setPaymentType(cashPayment.getPaymentTypeId());
        multiCashPaymentLists.add(cashPaymentList);

        //cashPayment.setTotalCPAmountTendered(tenderedAmount);
        cashPayment.setMultiCashPaymentList(multiCashPaymentLists);

        CreditPayment creditPayment = new CreditPayment();
        creditPayment.setCardPaymentList(cardPaymentList);

        VoucherPayment voucherPayment = new VoucherPayment();
        voucherPayment.setMultiVoucherPayments(voucherPaymentList);

        BankPayment bankPayment = new BankPayment();
        bankPayment.setMultiBankPaymentList(bankPaymentList);


        if (checkoutType != null && checkoutType.equals(Constant.SALESDRAFTINVOICE)) {

          //  Toast.makeText(activity, "gggg6", Toast.LENGTH_SHORT).show();
            List<PurchaseItem> itemList = new ArrayList<PurchaseItem>();

            purchaseSaveData.setMemo(edMemo.getText().toString().trim());
            purchaseSaveData.setCashPayment(cashPayment);
            purchaseSaveData.setBankPayment(bankPayment);
            purchaseSaveData.setDiscountAmount(discountAmount);
            purchaseSaveData.setVoucherPayment(voucherPayment);
            purchaseSaveData.setCreditPayment(creditPayment);
            purchaseSaveData.setTotalTenderedAmount(Double.parseDouble(totalTenderedAmount));
            purchaseSaveData.setAmountReturned("" + amountReturned);
            purchaseSaveData.setTaxType(purchaseSaveData.getTaxType());
            purchaseSaveData.setCessTotalTaxAmt(purchaseSaveData.getCessTotalTaxAmt());
            purchaseSaveData.setCustomerEmail(purchaseSaveData.getCustomerEmail());
            purchaseSaveData.setCustomerId(purchaseSaveData.getCustomerId());
            purchaseSaveData.setCutomerName(purchaseSaveData.getCutomerName());
            purchaseSaveData.setDateOfInvoice(purchaseSaveData.getDateOfInvoice());

            //-------------this implementation not done mobile part------
            purchaseSaveData.setExchangerateValue(1l);
            purchaseSaveData.setCurrencyIdOfInvoice(1l);
            purchaseSaveData.setExportInvoice(false);
            //--------------------------------------------------------

            purchaseSaveData.setShippingDate(purchaseSaveData.getDateOfInvoice());

            purchaseSaveData.setCustomerEmail(customerEmail);
            purchaseSaveData.setPaymentType(purchaseSaveData.getPaymentType());
            purchaseSaveData.setSalesOrderId(purchaseSaveData.getSalesOrderId());
            purchaseSaveData.setTotalCheckOutamt(purchaseSaveData.getTotalCheckOutamt());
            purchaseSaveData.setTotalTaxAmt(purchaseSaveData.getTotalTaxAmt());
            for (int i = 0; i < purchaseSaveData.getPurchaseItemList().size(); i++) {
                PurchaseItem item = purchaseSaveData.getPurchaseItemList().get(i);

                PurchaseItem saveItem = new PurchaseItem();
                saveItem.setQty(item.getItemQuantity());
                saveItem.setAmtinclusivetax(item.getAmtinclusivetax());
                saveItem.setAmtexclusivetax(item.getAmtexclusivetax());
                saveItem.setCess(item.getCess());
                saveItem.setCessTaxAmt(item.getCessTaxAmt());
                saveItem.setDiscountAmt(item.getDiscountAmt());
                saveItem.setHsnCode(item.getHsnCode());
                saveItem.setItemCode(item.getItemCode());
                saveItem.setItemDescription(item.getItemDescription());
                saveItem.setItemId(item.getItemId());
                saveItem.setItemName(item.getItemName());
                saveItem.setRemainingQty(item.getRemainingQty());
                saveItem.setReturnQty(item.getReturnQty());
                saveItem.setSalesOrderDetailsId(item.getSalesOrderDetailsId());
                saveItem.setSalesOrderId(item.getSalesOrderId());
                saveItem.setTaxAmountSplit(item.getTaxAmountSplit());
                saveItem.setTaxamt(item.getTaxamt());
                saveItem.setTaxid(item.getTaxid());
                saveItem.setUnitPrice(item.getUnitPrice());
                saveItem.setUnitPriceIn(item.getUnitPrice());
                saveItem.setUomName(item.getUomName());
                saveItem.setUom(item.getUom());
                saveItem.setUomConvertorList(item.getUomConvertorList());
                saveItem.setItemDescription(item.getItemDescription());
                saveItem.setTaxName(item.getTaxName());
                saveItem.setPrice(BigDecimal.valueOf(item.getUnitPrice()));

                try {
                    saveItem.setTaxpercent(item.getTaxpercent());
                } catch (Exception e) {
                   // saveItem.setTaxpercent(0.0);
                    saveItem.setTaxpercent("");
                }

                itemList.add(saveItem);

            }
            purchaseSaveData.setPurchaseItemList(itemList);
            jsonPaymentData = gson.toJson(purchaseSaveData).toString();
            return jsonPaymentData;

        } else if (checkoutType != null && checkoutType.equals(Constant.CHECKOUTYPE_INVOKESALESORDER)) {
           // Toast.makeText(activity, "gggg5", Toast.LENGTH_SHORT).show();

            List<PurchaseItem> itemList = new ArrayList<PurchaseItem>();

            purchaseSaveData.setMemo(edMemo.getText().toString().trim());
            purchaseSaveData.setCashPayment(cashPayment);
            purchaseSaveData.setBankPayment(bankPayment);
            purchaseSaveData.setDiscountAmount(discountAmount);
            purchaseSaveData.setVoucherPayment(voucherPayment);
            purchaseSaveData.setCreditPayment(creditPayment);
            purchaseSaveData.setTotalTenderedAmount(Double.parseDouble(totalTenderedAmount));
            purchaseSaveData.setAmountReturned("" + amountReturned);
            purchaseSaveData.setTaxType(purchaseSaveData.getTaxType());
            purchaseSaveData.setCessTotalTaxAmt(purchaseSaveData.getCessTotalTaxAmt());
            purchaseSaveData.setCustomerEmail(purchaseSaveData.getCustomerEmail());
            purchaseSaveData.setCustomerId(purchaseSaveData.getCustomerId());
            purchaseSaveData.setCutomerName(purchaseSaveData.getCutomerName());
            purchaseSaveData.setDateOfInvoice(purchaseSaveData.getDateOfInvoice());
            //-------------this implementation not done mobile part------
            purchaseSaveData.setExchangerateValue(1l);
            purchaseSaveData.setCurrencyIdOfInvoice(1l);
            purchaseSaveData.setExportInvoice(false);
            //--------------------------------------------------------

            purchaseSaveData.setShippingDate(selectedDate);
            purchaseSaveData.setCustomerEmail(customerEmail);
            purchaseSaveData.setPaymentType(purchaseSaveData.getPaymentType());
            purchaseSaveData.setSalesOrderId(purchaseSaveData.getSalesOrderId());
            purchaseSaveData.setTotalCheckOutamt(purchaseSaveData.getTotalCheckOutamt());
            purchaseSaveData.setTotalTaxAmt(purchaseSaveData.getTotalTaxAmt());
            for (int i = 0; i < purchaseSaveData.getPurchaseItemList().size(); i++) {
                PurchaseItem item = purchaseSaveData.getPurchaseItemList().get(i);

                PurchaseItem saveItem = new PurchaseItem();
                saveItem.setQty(item.getItemQuantity());
                saveItem.setAmtinclusivetax(item.getAmtinclusivetax());
                saveItem.setAmtexclusivetax(item.getAmtexclusivetax());
                saveItem.setCess(item.getCess());
                saveItem.setCessTaxAmt(item.getCessTaxAmt());
                saveItem.setDiscountAmt(item.getDiscountAmt());
                saveItem.setHsnCode(item.getHsnCode());
                saveItem.setItemCode(item.getItemCode());
                saveItem.setItemDescription(item.getItemDescription());
                saveItem.setItemId(item.getItemId());
                saveItem.setItemName(item.getItemName());
                saveItem.setRemainingQty(item.getRemainingQty());
                saveItem.setReturnQty(item.getReturnQty());
                saveItem.setSalesOrderDetailsId(item.getSalesOrderDetailsId());
                saveItem.setSalesOrderId(item.getSalesOrderId());
                saveItem.setTaxAmountSplit(item.getTaxAmountSplit());
                saveItem.setTaxamt(item.getTaxamt());
                saveItem.setTaxid(item.getTaxid());
                saveItem.setUnitPrice(item.getUnitPrice());
                saveItem.setUnitPriceIn(item.getUnitPrice());
                saveItem.setUomName(item.getUomName());
                saveItem.setUom(item.getUom());
                saveItem.setUomConvertorList(item.getUomConvertorList());
                saveItem.setItemDescription(item.getItemDescription());
                saveItem.setTaxName(item.getTaxName());
                saveItem.setPrice(BigDecimal.valueOf(item.getUnitPrice()));

                try {
                    saveItem.setTaxpercent(item.getTaxpercent());
                } catch (Exception e) {
                    saveItem.setTaxpercent("");
                }

                itemList.add(saveItem);

            }
            purchaseSaveData.setPurchaseItemList(itemList);
            jsonPaymentData = gson.toJson(purchaseSaveData).toString();
            return jsonPaymentData;

        } else if (checkoutType != null && checkoutType.equals(Constant.GENERAL_TRANSACTION_SAVEINVOICE)) {
           // Toast.makeText(activity, "gggg4", Toast.LENGTH_SHORT).show();
            List<SelectedAccountList> accountItemsList = null;

            purchaseSaveData.setCashPayment(cashPayment);
            purchaseSaveData.setBankPayment(bankPayment);
            purchaseSaveData.setDiscountAmount(discountAmount);
            purchaseSaveData.setVoucherPayment(voucherPayment);
            purchaseSaveData.setCreditPayment(creditPayment);

            purchaseSaveData.setTotalTenderedAmount(Double.parseDouble(totalTenderedAmount));
            purchaseSaveData.setAmountReturned("" + amountReturned);
            purchaseSaveData.setTaxType(purchaseSaveData.getTaxType());
            purchaseSaveData.setCessTotalTaxAmt(purchaseSaveData.getCessTotalTaxAmt());
            purchaseSaveData.setCustomerEmail(purchaseSaveData.getCustomerEmail());
            purchaseSaveData.setCustomerId(purchaseSaveData.getCustomerId());
            purchaseSaveData.setCutomerName(purchaseSaveData.getCutomerName());
            purchaseSaveData.setDateOfInvoice(purchaseSaveData.getDateOfInvoice());
            purchaseSaveData.setSupplierId(purchaseSaveData.getSupplierId());
            purchaseSaveData.setSupplierName(purchaseSaveData.getSupplierName());
            purchaseSaveData.setSupplierEmail("");
            purchaseSaveData.setSupplierInvNo("");
            purchaseSaveData.setTypeDoc("PO");

            //-------------this implementation not done mobile part------
            purchaseSaveData.setExchangerateValue(1l);
            purchaseSaveData.setCurrencyIdOfInvoice(1l);
            purchaseSaveData.setExportInvoice(false);
            purchaseSaveData.setCurrencyId(1L);
            //--------------------------------------------------------

            purchaseSaveData.setShippingDate(purchaseSaveData.getDateOfInvoice());
            purchaseSaveData.setCustomerEmail(customerEmail);
            purchaseSaveData.setPaymentType("multiPayment");
            purchaseSaveData.setSalesOrderId(purchaseSaveData.getSalesOrderId());
            purchaseSaveData.setTotalCheckOutamt(purchaseSaveData.getTotalCheckOutamt());
            purchaseSaveData.setTotalTaxAmt(purchaseSaveData.getTotalTaxAmt());
            for (int i = 0; i < purchaseSaveData.getSelectedAccountList().size(); i++) {
                accountItemsList = new ArrayList<>();
                SelectedAccountList item = purchaseSaveData.getSelectedAccountList().get(i);
                SelectedAccountList saveItem;
                saveItem = new SelectedAccountList();
                saveItem.setAccountCode(item.getAccountCode());
                saveItem.setAccountName(item.getAccountName());
                saveItem.setAccountGroup(item.getAccountGroup());
                saveItem.setAccountid(item.getAccountid());
                saveItem.setAmtinclusivetax(item.getAmtinclusivetax());
                saveItem.setAmtexclusivetax(item.getAmtexclusivetax());
                saveItem.setAparcode(item.getAparcode());
                saveItem.setParentAccountName(item.getParentAccountName());
                saveItem.setStatus(item.getStatus());
                saveItem.setStringAccountCode(item.getStringAccountCode());
                saveItem.setTaxid(item.getTaxid());
                saveItem.setTaxamt(item.getTaxamt());
                saveItem.setTaxName(item.getTaxName());
                saveItem.setTaxAmountSplit(item.getTaxAmountSplit());


                try {
                    saveItem.setTaxpercent(item.getTaxpercent());
                } catch (Exception e) {
                    saveItem.setTaxpercent(0.0);
                }

                accountItemsList.add(saveItem);

            }
            purchaseSaveData.setSelectedAccountList(accountItemsList);
            jsonPaymentData = gson.toJson(purchaseSaveData).toString();
            return jsonPaymentData;

        } else if (paymentInvoice != null && checkoutType != null) {

           // Toast.makeText(activity, "gggg3", Toast.LENGTH_SHORT).show();
            SaveRemaining_Payment remainingPaymnet = new SaveRemaining_Payment();
            remainingPaymnet.setCashPayment(cashPayment);
            remainingPaymnet.setBankPayment(bankPayment);
            remainingPaymnet.setCreditPayment(creditPayment);
            remainingPaymnet.setVoucherPayment(voucherPayment);
            remainingPaymnet.setTotalCheckOutamt(paymentInvoice.getAmount());

            if(supplier !=null)
            {
                remainingPaymnet.setSupplierId(supplier.getSupplierId());
                remainingPaymnet.setSupplierName(supplier.getSupplierName());
            }

            long customerID = 0;
            String customerName = "";
            if (customer != null) {
                customerID = customer.getCustomerId();
                customerName = customer.getCustomerName() + "|" + customer.getCustomerNumber();
                remainingPaymnet.setCustomerId(customerID);
                remainingPaymnet.setCutomerName(customerName);
            }

            remainingPaymnet.setAmountReturned("" + amountReturned);
            remainingPaymnet.setDiscountAmount(discountAmount);
            remainingPaymnet.setTotalTenderedAmount(tenderedAmount);
            remainingPaymnet.setTaxType(selectedTax);
            remainingPaymnet.setDateOfInvoice(selectedDate);
            remainingPaymnet.setCurrencyId(1l);
            remainingPaymnet.setExchangerateValue(1l);
            remainingPaymnet.setExchangeRateId(1l);
            remainingPaymnet.setMultiPartialPaymentList(null);
            remainingPaymnet.setTcsAmount(0l);
            remainingPaymnet.setTdsAmount(0l);

            jsonPaymentData = gson.toJson(remainingPaymnet).toString();
            return jsonPaymentData;
        }
        else if (purchaseSaveData != null && checkoutType != null && checkoutType.equals("PurchaseCheckout")) {
            //Toast.makeText(activity, "gggg2", Toast.LENGTH_SHORT).show();

            List<PurchaseItem> itemList = new ArrayList<PurchaseItem>();
            purchaseSaveData.setSupplierInvNo(edSupplierInvNo.getText().toString().trim());
            purchaseSaveData.setMemo(edMemo.getText().toString().trim());
            purchaseSaveData.setCashPayment(cashPayment);
            purchaseSaveData.setBankPayment(bankPayment);
            purchaseSaveData.setVoucherPayment(voucherPayment);
            purchaseSaveData.setCreditPayment(creditPayment);
            purchaseSaveData.setSupplierEmail(supplierEmail);
            purchaseSaveData.setAmountReturned(edReturnamount.getText().toString());
            purchaseSaveData.setExchangerateValue(1l);
            purchaseSaveData.setCurrencyIdOfInvoice(1l);
            purchaseSaveData.setExportInvoice(false);
            purchaseSaveData.setSuppLoc(17l);
            purchaseSaveData.setShippingDate(purchaseSaveData.getDateOfInvoice());

            for (int i = 0; i < purchaseSaveData.getPurchaseItemList().size(); i++) {
                PurchaseItem item = purchaseSaveData.getPurchaseItemList().get(i);

                PurchaseItem saveItem = new PurchaseItem();
                saveItem.setQty(item.getRemainingQty());
                saveItem.setAmtinclusivetax(item.getAmtinclusivetax());
                saveItem.setAmtexclusivetax(item.getAmtexclusivetax());
                saveItem.setCess(item.getCess());
                saveItem.setCessTaxAmt(item.getCessTaxAmt());
                saveItem.setDiscountAmt(item.getDiscountAmt());
                saveItem.setHsnCode(item.getHsnCode());
                saveItem.setItemCode(item.getItemCode());
                saveItem.setItemDescription(item.getItemDescription());
                saveItem.setItemId(item.getItemId());
                saveItem.setItemName(item.getItemName());
               // saveItem.setRemainingQty(item.getRemainingQty());
                //saveItem.setReturnQty(item.getReturnQty());
               // saveItem.setSalesOrderDetailsId(item.getSalesOrderDetailsId());
                //saveItem.setSalesOrderId(item.getSalesOrderId());
               // saveItem.setTaxAmountSplit(item.getTaxAmountSplit());
                saveItem.setTaxamt(item.getTaxamt());
                saveItem.setTaxid(item.getTaxid());
                saveItem.setUnitPrice(item.getUnitPrice());
                saveItem.setUnitPriceIn(item.getUnitPrice());
                saveItem.setUomName(item.getUomName());
                saveItem.setUomConvertorList(item.getUomConvertorList());
                saveItem.setItemDescription(item.getItemDescription());
                saveItem.setTaxName(item.getTaxName());
                saveItem.setPrice(BigDecimal.valueOf(item.getUnitPrice()));
                saveItem.setPurchaseOrderId(item.getPurchaseOrderDetailsId());
                saveItem.setPurchaseQuotationId(item.getPurchaseQuotationId());
                saveItem.setInclusiveJSON(item.getInclusiveJSON());
                saveItem.setItemCategoryId(item.getItemCategoryId());
                saveItem.setItemCategoryName(item.getItemCategoryName());
                saveItem.setDiscountConfigAmt(item.getDiscountConfigAmt());
                saveItem.setSerializableStatus(item.getSerializableStatus());
                saveItem.setStock(saveItem.getStock());
                if (purchaseQuotationId!=null)
                {
                    purchaseQuotationId = String.valueOf(item.getPurchaseQuotationId());
                }

                if (purchaseOrderId!=null){
                    purchaseOrderId = String.valueOf(item.getPurchaseOrderId());
                }

                if (purchaseReciveItemid!=null){
                    purchaseReciveItemid = String.valueOf(item.getReceiveItemId());
                }

                try {
                    saveItem.setTaxpercent(item.getTaxpercent());
                } catch (Exception e) {
                    saveItem.setTaxpercent("");
                }

                itemList.add(saveItem);

            }

            purchaseSaveData.setCurrencyId(1l);
            purchaseSaveData.setPurchaseItemList(itemList);

            if (purchaseQuotationId!=null){
                purchaseSaveData.setPurchaseQuotationId(purchaseQuotationId);
            }

            if (purchaseOrderId!=null){
                purchaseSaveData.setPurchaseOrderId(purchaseOrderId);
            }
            if (purchaseReciveItemid!=null){
                purchaseSaveData.setReceiveItemId(Long.valueOf(purchaseReciveItemid));

            }




            jsonPaymentData = gson.toJson(purchaseSaveData).toString();
            return jsonPaymentData;

        }
        else if (checkoutType != null && checkoutType.equals("SalesCheckout") ) {
            List<PurchaseItem> itemList = new ArrayList<PurchaseItem>();

           // Toast.makeText(activity, "gggg", Toast.LENGTH_SHORT).show();
            purchaseSaveData.setMemo(edMemo.getText().toString());
            purchaseSaveData.setCashPayment(cashPayment);
            purchaseSaveData.setBankPayment(bankPayment);
            purchaseSaveData.setDiscountAmount(discountAmount);
            purchaseSaveData.setVoucherPayment(voucherPayment);
            purchaseSaveData.setCreditPayment(creditPayment);
            purchaseSaveData.setTotalTenderedAmount(Double.parseDouble(totalTenderedAmount));
            purchaseSaveData.setAmountReturned("" + amountReturned);
            purchaseSaveData.setPaymentType("multiPayment");
            purchaseSaveData.setCustomerEmail(purchaseSaveData.getCustomerEmail());
            purchaseSaveData.setCustomerId(purchaseSaveData.getCustomerId());
            purchaseSaveData.setCutomerName(purchaseSaveData.getCutomerName());
            purchaseSaveData.setDateOfInvoice(purchaseSaveData.getDateOfInvoice());
            //-------------this implementation not done mobile part------
            purchaseSaveData.setExchangerateValue(1l);
            purchaseSaveData.setCurrencyIdOfInvoice(1l);
            //----------------------------------------------


            if (exportInvoice){
                purchaseSaveData.setExportInvoice(exportInvoice);
            }else {
                purchaseSaveData.setExportInvoice(exportInvoice);
            }




            //--------------------------------------------------------

            purchaseSaveData.setShippingDate(purchaseSaveData.getDateOfInvoice());
            purchaseSaveData.setCustomerEmail(customerEmail);
            purchaseSaveData.setPaymentType(purchaseSaveData.getPaymentType());
            purchaseSaveData.setSalesOrderId(purchaseSaveData.getSalesOrderId());
            purchaseSaveData.setTotalCheckOutamt(purchaseSaveData.getTotalCheckOutamt());
            purchaseSaveData.setTotalTaxAmt(purchaseSaveData.getTotalTaxAmt());
            for (int i = 0; i < purchaseSaveData.getPurchaseItemList().size(); i++) {
                PurchaseItem item = purchaseSaveData.getPurchaseItemList().get(i);

                PurchaseItem saveItem = new PurchaseItem();
                saveItem.setQty(item.getItemQuantity());
                saveItem.setAmtinclusivetax(item.getAmtinclusivetax());
                saveItem.setAmtexclusivetax(item.getAmtexclusivetax());
                saveItem.setCess(item.getCess());
                saveItem.setCessTaxAmt(item.getCessTaxAmt());
                saveItem.setDiscountAmt(item.getDiscountAmt());
                saveItem.setHsnCode(item.getHsnCode());
                saveItem.setItemCode(item.getItemCode());
                saveItem.setItemDescription(item.getItemDescription());
                saveItem.setItemId(item.getItemId());
                saveItem.setItemName(item.getItemName());
                saveItem.setRemainingQty(item.getRemainingQty());
                saveItem.setReturnQty(item.getReturnQty());
                saveItem.setSalesOrderDetailsId(item.getSalesOrderDetailsId());
                saveItem.setSalesOrderId(item.getSalesOrderId());
                saveItem.setTaxAmountSplit(item.getTaxAmountSplit());
                saveItem.setTaxamt(item.getTaxamt());
                saveItem.setTaxid(item.getTaxid());
                saveItem.setUnitPrice(item.getUnitPrice());
                saveItem.setUnitPriceIn(item.getUnitPrice());
                saveItem.setUomName(item.getUomName());
                saveItem.setUom(item.getUom());
                saveItem.setUomConvertorList(item.getUomConvertorList());
                saveItem.setItemDescription(item.getItemDescription());
                saveItem.setTaxName(item.getTaxName());
                saveItem.setPrice(BigDecimal.valueOf(item.getUnitPrice()));
                saveItem.setSalesQuotationId(item.getSalesQuotationId());
                saveItem.setSalesQuotationDetailsId(item.getSalesQuotationDetailsId());
                saveItem.setInclusiveJSON(item.getInclusiveJSON());
                saveItem.setSerializableStatus(item.getSerializableStatus());
                saveItem.setSalesOrderDetailsId(item.getSalesOrderDetailsId());
                saveItem.setSalesOrderId(item.getSalesOrderId());
                saveItem.setSalesDeliverOrderDetailsId(item.getSalesDeliverOrderDetailsId());
                saveItem.setSalesDeliverOrderId(item.getSalesDeliverOrderId());
                saveItem.setProFormaSalesInvoiceId(item.getProFormaSalesInvoiceId());
                saveItem.setProFormaSalesInvoiceDetailsId(item.getProFormaSalesInvoiceDetailsId());
                try {
                    saveItem.setTaxpercent(item.getTaxpercent());
                } catch (Exception e) {
                    saveItem.setTaxpercent("");
                }

                itemList.add(saveItem);
                if (salesdeliveryOrderId!=null){
                    salesdeliveryOrderId = String.valueOf(saveItem.getSalesDeliverOrderDetailsId());
                }

            }
            purchaseSaveData.setPurchaseItemList(itemList);
            if (salesdeliveryOrderId!=null){
                purchaseSaveData.setDeliveryOrderId(salesdeliveryOrderId);
            }

            jsonPaymentData = gson.toJson(purchaseSaveData).toString();
            return jsonPaymentData;
        }








        return jsonPaymentData;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (view.getId()) {
            case R.id.imageview_addcard:
                //Toast.makeText(activity, "addcard", Toast.LENGTH_SHORT).show();
                CardPaymentList cardPayment = new CardPaymentList();
                cardPayment.setCardNo("");
                cardPayment.setCardDate("");
                cardPayment.setCardAmt(0);
                cardPayment.setCardBankAccount("");

                cardPaymentList.add(cardPaymentList.size(), cardPayment);
               // Log.e("card Check Size1", String.valueOf(cardPaymentList.size()));
                //Toast.makeText(activity, "card size"+cardPaymentList.size(), Toast.LENGTH_SHORT).show();
                cardPaymentAdapter = new CardPayment_Adapter(activity, cardPaymentList);
                nonscrolllistviewCredit.setAdapter(cardPaymentAdapter);
                cardPaymentAdapter.notifyDataSetChanged();
                setTenderedAmount();

                break;

            case R.id.imageview_removecard:
               // Toast.makeText(activity, "removecard", Toast.LENGTH_SHORT).show();
                cardPaymentList.remove(position);
               // Log.e("card Check Size2", String.valueOf(cardPaymentList.size()));
               // Toast.makeText(activity, "remove size"+cardPaymentList.size(), Toast.LENGTH_SHORT).show();
                cardPaymentAdapter = new CardPayment_Adapter(activity, cardPaymentList);
                nonscrolllistviewCredit.setAdapter(cardPaymentAdapter);
                cardPaymentAdapter.notifyDataSetChanged();
                setTenderedAmount();
                if (cardPaymentList.size() == 0) {
                    llCardlayuout.setVisibility(View.GONE);
                }


                break;

            case R.id.imageview_voucheradd:
                MultiVoucherPayment multiVoucherPayment = new MultiVoucherPayment();
                multiVoucherPayment.setVoucherNo("");
                multiVoucherPayment.setAmt(0);
                voucherPaymentList.add(voucherPaymentList.size(), multiVoucherPayment);
                voucherPaymentAdapter = new VoucherPayment_Adapter(activity, voucherPaymentList);
                nonscrolllistviewVoucher.setAdapter(voucherPaymentAdapter);
                voucherPaymentAdapter.notifyDataSetChanged();
                setTenderedAmount();
                break;

            case R.id.imageview_voucherremove:
                voucherPaymentList.remove(position);
                voucherPaymentAdapter = new VoucherPayment_Adapter(activity, voucherPaymentList);
                nonscrolllistviewVoucher.setAdapter(voucherPaymentAdapter);
                voucherPaymentAdapter.notifyDataSetChanged();
                setTenderedAmount();
                if (voucherPaymentList.size() == 0) {
                    llVopucherlayuout.setVisibility(View.GONE);
                }
                break;


            case R.id.imageview_addbank:
                MultiBankPaymentList bankPayment = new MultiBankPaymentList();
                bankPayment.setBankName("");
                bankPayment.setTransactionNo("");
                bankPayment.setDate("");
                bankPayment.setBankAccount("");
                bankPayment.setAmount(0);
                bankPaymentList.add(bankPaymentList.size(), bankPayment);
                bankPaymentAdapter.notifyDataSetChanged();
                setTenderedAmount();
                break;

            case R.id.imageview_removebank:
                bankPaymentList.remove(position);
                bankPaymentAdapter = new BankPayment_Adapter(activity, bankPaymentList);
                nonscrolllistviewBank.setAdapter(bankPaymentAdapter);
                bankPaymentAdapter.notifyDataSetChanged();
                setTenderedAmount();
                if (bankPaymentList.size() == 0) {
                    llBanklayuout.setVisibility(View.GONE);
                }
                break;
        }
    }


    public void setTenderedAmount() {
        double tenderedAmount = 0.00;
        double cashTenderedAmount = 0.00;
        try {
            if (edCashamounttendered.getText().toString().equals("")) {
                cashTenderedAmount = 0.00;
            } else {
                cashTenderedAmount = Double.parseDouble(edCashamounttendered.getText().toString().trim());
            }
        } catch (NumberFormatException e) {
            edCashamounttendered.setError(getResources().getString(R.string.error_numberformate));
        }

        BigDecimal big = new BigDecimal(cashTenderedAmount);
        tenderedAmount = Double.parseDouble(String.valueOf(big.setScale(2, RoundingMode.HALF_UP)));
        for (int i = 0; i < cardPaymentList.size(); i++) {
            tenderedAmount += cardPaymentList.get(i).getCardAmount();
            UtilView.showLogCat(TAG, "Card Payment at " + i + " : " + cardPaymentList.get(i).getCardDate());


        }
        for (int j = 0; j < voucherPaymentList.size(); j++) {
            tenderedAmount += voucherPaymentList.get(j).getAmt();
        }

        for (int j = 0; j < bankPaymentList.size(); j++) {
            tenderedAmount += bankPaymentList.get(j).getAmount();
            MultiBankPaymentList bankPayment = bankPaymentList.get(j);
            UtilView.showLogCat(TAG, "Bank Payment at " + j + " : " + bankPayment.getBankName() + " : " + bankPayment.getTransactionNo() + " : " + bankPayment.getDate() + " : " + bankPayment.getAmount() + " : " + bankPayment.getBankAccount() + " : " + bankPayment.getBankAccountId());
        }

        edTenderedamt.setText("" + new BigDecimal(tenderedAmount).setScale(2, BigDecimal.ROUND_HALF_UP));

        setReturnAmount();


    }

    private void setReturnAmount() {

        String txtTenderamt = edTenderedamt.getText().toString();
        String txtTotalamt = edTotalamount.getText().toString();
        String txtDiscountamt = "0.00";
        try {
            txtDiscountamt = edTotaldiscount.getText().toString();

            double tenderedAmt = 0.00;
            if (txtTenderamt.equals("")) {
                tenderedAmt = 0.00;
            } else {
                tenderedAmt = Double.parseDouble(txtTenderamt);
            }
            double totalAmt = 0.00;
            if (txtTotalamt.equals("")) {
                totalAmt = 0.00;
            } else {
                totalAmt = Double.parseDouble(txtTotalamt);
            }


            double discountAmt = 0.00;
            if (txtDiscountamt.equals("")) {
                discountAmt = 0.00;
            } else {
                discountAmt = Double.parseDouble(txtDiscountamt);
            }


            double returnAmt = (tenderedAmt + discountAmt) - totalAmt;

            edReturnamount.setText("" + new BigDecimal(returnAmt).setScale(2, BigDecimal.ROUND_HALF_UP));

        } catch (NumberFormatException e) {
            edTotalamount.setError(getResources().getString(R.string.error_numberformate));
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = activity.getMenuInflater();
        inflater.inflate(R.menu.menu_salespayment, menu);

        for (int i = 0; i < menu.size(); i++) {
            if (configData != null) {
                if (configData.getPrintType() != null && configData.getPrintType().equals(Constant.PRINTYPE_POS)) {
                    menu.getItem(i).setVisible(true);
                } else {
                    menu.getItem(i).setVisible(false);

                }
            } else {
                menu.getItem(i).setVisible(false);
            }
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (BLUETOOTH_PRINTER != null) {
            if (BLUETOOTH_PRINTER.IsNoConnection())
                BLUETOOTH_PRINTER.stop();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101) {
            if (callingfrom.equals(Constant.NAVIGATION_GROUP_PURCHASE)) {
                Intent intent = new Intent(this, NavigationDrawer_Activity.class);
                intent.putExtra(Constant.NAVIGATION_GROUPHEADER, Constant.NAVIGATION_GROUP_PURCHASE);
                startActivity(intent);
                finish();
            }

            if (callingfrom.equals(Constant.NAVIGATION_GROUP_RETAIL)) {
                Intent intent = new Intent(this, NavigationDrawer_Activity.class);
                intent.putExtra(Constant.NAVIGATION_GROUPHEADER, Constant.NAVIGATION_GROUP_RETAIL);
                startActivity(intent);
                finish();
            }


        }
        switch (requestCode) {
            case REQUEST_CONNECT_DEVICE:
                // When DeviceListActivity returns with a device to connect
                if (resultCode == Activity.RESULT_OK) {
                    // Get the device MAC address
                    String address = data.getExtras().getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
                    printerType = data.getExtras().getString(DeviceListActivity.EXTRA_PRINTER_TYPE);
                    // Get the BLuetoothDevice object
                    device = mBluetoothAdapter.getRemoteDevice(address);
                    // Attempt to connect to the device
                    BLUETOOTH_PRINTER.start();
                    BLUETOOTH_PRINTER.connect(device);
                }
                break;
            case REQUEST_ENABLE_BT:
                // When the request to enable Bluetooth returns
                if (resultCode == Activity.RESULT_OK) {
                    // Bluetooth is now enabled, so set up a chat session
                    initializeBluetoothDevice();
                } else {
                    // User did not enable Bluetooth or an error occured
                    Log.d(TAG, "BT not enabled");
                    Toast.makeText(this, R.string.bt_not_enabled_leaving, Toast.LENGTH_SHORT).show();
                    finish();
                }

                break;


        }




    }

    private void checkPermission() {

        String permission = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(this, new String[]{permission}, MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{permission}, MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            }
        } else {

           /* if (purchaseSaveData != null) {
                createPdf();
            }*/

            if (invoiceData != null) {
                createPdf();
            }

            if (salesInvoiceData!=null){
                createSalesPdf();
            }
            if (paymentInvoice != null) {
                createPaymentPdf();
            }

            if (saveResponseData != null) {
                createGTPdf();
            }

        }


    }

    private void createSalesPdf() {
        File pdfFile = null;
        if (callingfrom.equals(Constant.NAVIGATION_GROUP_RETAIL)) {
            pdfFile = PdfUtils.createFile(salesInvoiceData.getSiData().getSrlnNo() + ".pdf", Constant.DIRECTORY_SALES, Constant.DIRECTORY_SALESPOS);
            if (pdfFile != null) {

                progressBar.setVisibility(View.VISIBLE);
                if (salesInvoiceData.getSiData().getPrintType() != null) {
                    //salesInvoiceData.getSiData().setPrintType("normal");
                    if (salesInvoiceData.getSiData().getPrintType().equals(Constant.PRINTYPE_POS)) {
                        boolean printStatus = false;
                        if (!printerType.equals("") && printerType.equals(getResources().getString(R.string.pos_3inch))) {
                            printStatus = PrintReceipt.printInvoice3Inch(Activity_Checkout.this, salesInvoiceData);
                        } else {
                            printStatus = PrintReceipt.printInvoice2Inch(Activity_Checkout.this, salesInvoiceData);
                        }
                        if (printStatus) {
                            BLUETOOTH_PRINTER.stop();
                            Intent returnIntent = new Intent();
                            setResult(Activity.RESULT_OK, returnIntent);
                            finish();
                        } else {

                        }

                    }

                    if (salesInvoiceData.getSiData().getPrintType().equals(Constant.PRINTYPE_NORMAL)) {
                        InputStream logoInputStream = PdfUtils.getLogoInputStream(activity,progressBar);
                        if (logoInputStream != null) {
                            progressBar.setVisibility(View.GONE);
                            try {
                                PosSalesInvoicePdf invoicePdf = new PosA4SalesInvociePdfImpl();
                                invoicePdf.generateTaxInvoice(activity, salesInvoiceData, new FileOutputStream(pdfFile), logoInputStream);
                                UtilView.hideProgessBar(activity, progressBar);
                                openPdfInvoice(salesInvoiceData.getSiData().getSrlnNo() + ".pdf", Constant.DIRECTORY_SALES, Constant.DIRECTORY_SALESPOS);
                                Intent returnIntent = new Intent();
                                setResult(Activity.RESULT_OK, returnIntent);
                                finish();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (DocumentException e) {
                                e.printStackTrace();
                            }
                        } else {
                            try {
                                PosSalesInvoicePdf invoicePdf = new PosA4SalesInvociePdfImpl();
                                invoicePdf.generateTaxInvoice(this, salesInvoiceData, new FileOutputStream(pdfFile), null);
                                UtilView.hideProgessBar(activity, progressBar);
                                openPdfInvoice(salesInvoiceData.getSiData().getSrlnNo() + ".pdf", Constant.DIRECTORY_SALES, Constant.DIRECTORY_SALESPOS);
                                Intent returnIntent = new Intent();
                                setResult(Activity.RESULT_OK, returnIntent);
                                finish();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (DocumentException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (ActivityCompat.checkSelfPermission(this, permissions[0]) == PackageManager.PERMISSION_GRANTED) {
            switch (requestCode) {

                case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE:
                    if (invoiceData != null) {
                        createPdf();
                    }

                    if (salesInvoiceData!=null){
                        createSalesPdf();
                    }
                    if (paymentInvoice != null) {
                        createPaymentPdf();
                    }
                    if (saveResponseData != null) {
                        createGTPdf();
                    }
                    break;
            }


        } else {
            Toast.makeText(this, "Permisson Deny", Toast.LENGTH_LONG).show();
        }
    }

    private void createPdf() {
        File pdfFile = null;
        if (callingfrom.equals(Constant.NAVIGATION_GROUP_PURCHASE)) {
            pdfFile = PdfUtils.createFile("Invoice" + invoiceData.getPiData().getSupplierInvNo() + ".pdf", Constant.DIRECTORY_PURCHASE, Constant.DIRECTORY_PURCHASEINVOICES);

            if (pdfFile != null) {
                InputStream logoInputStream = PdfUtils.getLogoInputStream(this, progressBar);

                if (logoInputStream != null) {
                    try {
                        PosPurchaseInvoicePdf invoicePdf = new PosA4PurchaseInvociePdfImpl();
                        invoicePdf.generatePurchaseTaxInvoice(activity, invoiceData, new FileOutputStream(pdfFile), logoInputStream);
                        UtilView.hideProgessBar(activity, progressBar);
                        openPdfInvoice("Invoice" + invoiceData.getPiData().getSupplierInvNo() + ".pdf", Constant.DIRECTORY_PURCHASE, Constant.DIRECTORY_PURCHASEINVOICES);


                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (DocumentException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        PosPurchaseInvoicePdf invoicePdf = new PosA4PurchaseInvociePdfImpl();
                        invoicePdf.generatePurchaseTaxInvoice(activity, invoiceData, new FileOutputStream(pdfFile), logoInputStream);
                        UtilView.hideProgessBar(activity, progressBar);
                        openPdfInvoice("Invoice" + invoiceData.getPiData().getSupplierInvNo() + ".pdf", Constant.DIRECTORY_PURCHASE, Constant.DIRECTORY_PURCHASEINVOICES);

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (DocumentException e) {
                        e.printStackTrace();
                    }
                }

            }
        }



    }

    private void createPaymentPdf() {
        File pdfFile = null;
        pdfFile = PdfUtils.createFile(paymentResponseData.getTranactionId() + ".pdf", Constant.DIRECTORY_PURCHASE, Constant.DIRECTORY_PURCHASEPAYMENT);
        if (pdfFile != null) {
            InputStream logoInputStream = PdfUtils.getLogoInputStream(this, progressBar);
            if (logoInputStream != null) {
                try {
                    PosPurchasePaymetPdf invoicePdf = new PosA4PurchasePaymentPdfImpl();
                    invoicePdf.generatePaymentPdf(activity, paymentResponseData, new FileOutputStream(pdfFile), logoInputStream);
                    UtilView.hideProgessBar(activity, progressBar);
                    openPdfInvoice(paymentResponseData.getTranactionId() + ".pdf", Constant.DIRECTORY_PURCHASE, Constant.DIRECTORY_PURCHASEPAYMENT);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    PosPurchasePaymetPdf invoicePdf = new PosA4PurchasePaymentPdfImpl();
                    invoicePdf.generatePaymentPdf(activity, paymentResponseData, new FileOutputStream(pdfFile), logoInputStream);
                    UtilView.hideProgessBar(activity, progressBar);
                    openPdfInvoice(paymentResponseData.getTranactionId() + ".pdf", Constant.DIRECTORY_PURCHASE, Constant.DIRECTORY_PURCHASEPAYMENT);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public void openPdfInvoice(String fileName, String groupDirectory, String childDirectory) {
        File file = PdfUtils.getFile(fileName, groupDirectory, childDirectory);
        if (file != null) {
            Intent target = new Intent(Intent.ACTION_VIEW);
            target.setDataAndType(Uri.fromFile(file), "application/pdf");
            target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            try {
                startActivityForResult(target, 101);
            } catch (ActivityNotFoundException e) {
                UtilView.showToast(activity, "Your device is compatible for view Pdf File.");
            }
        } else {
            UtilView.showToast(activity, "No Invoice generated.");
        }

    }

    private void createGTPdf() {
        File pdfFile = null;
        pdfFile = PdfUtils.createFile(saveResponseData.getOpNo() + ".pdf", Constant.DIRECTORY_GT, Constant.DIRECTORY_GTEXPENSE);


        if (pdfFile != null) {

            InputStream logoInputStream = PdfUtils.getLogoInputStream(this, progressBar);
            if (logoInputStream != null) {
                try {
                    GTExpenseVoucherPdf voucherPdf = new GTExpenseVoucherPdfImpl();
                    voucherPdf.generateExpenseVoucherPdf(activity, saveResponseData, new FileOutputStream(pdfFile), logoInputStream);
                    UtilView.hideProgessBar(activity, progressBar);
                    openPdf(saveResponseData.getOpNo() + ".pdf", Constant.DIRECTORY_GT, Constant.DIRECTORY_GTEXPENSE);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    GTExpenseVoucherPdf voucherPdf = new GTExpenseVoucherPdfImpl();
                    voucherPdf.generateExpenseVoucherPdf(this, saveResponseData, new FileOutputStream(pdfFile), null);
                    UtilView.hideProgessBar(activity, progressBar);
                    openPdf(saveResponseData.getOpNo() + ".pdf", Constant.DIRECTORY_GT, Constant.DIRECTORY_GTEXPENSE);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void openPdf(String fileName, String groupDirectory, String childDirectory) {
        File file = PdfUtils.getFile(fileName, groupDirectory, childDirectory);
        if (file != null) {
            Intent target = new Intent(Intent.ACTION_VIEW);
            target.setDataAndType(Uri.fromFile(file), "application/pdf");
            target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            try {
                startActivityForResult(target, 101);
            } catch (ActivityNotFoundException e) {
                UtilView.showToast(activity, "Your device is compatible for view Pdf File.");
            }
        } else {
            UtilView.showToast(activity, "No Invoice generated.");
        }

    }

    private void getPaymentTypeDataFromServer() {

        String url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETPAYMENTTYPE;
        UtilView.showProgessBar(activity, progressBar);
        GetDataTask task = new GetDataTask(activity, new AsyncTaskCompleteListener<String>() {
            @Override
            public void onTaskComplete(String result) {
                HideUtil.init(activity);
                UtilView.hideProgessBar(activity, progressBar);
                if (result != null) {
                    if (result.equals("invalid")) {
                        UtilView.showToast(activity, getResources().getString(R.string.accesstoken_error));
                        Intent intent = new Intent(activity, Activity_Login.class);
                        activity.startActivity(intent);
                        activity.finish();
                    } else {

                        List<PaymentType> paymentTypeList = new ArrayList<>();

                        try {
                            JSONArray jsonArray = new JSONArray(result.toString());//5
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Gson gson = new Gson();
                                PaymentType paymentType = gson.fromJson(jsonObject.toString(), PaymentType.class);
                                paymentTypeList.add(paymentType);
                            }

                            paymentTypeAdapter = new PaymentType_Adapter(activity, paymentTypeList, new PaymentType_Adapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(PaymentType paymentType) {
                                    if (paymentType.getPaymentmethodId() != null) {
                                        if (paymentType.getPaymentmethodId() == 1) {
                                            if (paymentType.isChecked()) {
                                                llCashlayuout.setVisibility(View.VISIBLE);
                                                cashPayment.setPaymentTypeId(paymentType.getPaymentmethodId());
                                            } else {
                                                llCashlayuout.setVisibility(View.GONE);
                                                edCashamounttendered.setText("0");
                                            }
                                        }
                                        if (paymentType.getPaymentmethodId() == 2) {
                                            if (paymentType.isChecked()) {
                                                llBanklayuout.setVisibility(View.VISIBLE);
                                                MultiBankPaymentList multiBankPaymentList = new MultiBankPaymentList();
                                                multiBankPaymentList.setAmount(0);
                                                multiBankPaymentList.setPaymentType(paymentType.getPaymentmethodId());
                                                bankPaymentList.add(multiBankPaymentList);
                                                bankPaymentAdapter = new BankPayment_Adapter(activity, bankPaymentList);
                                                nonscrolllistviewBank.setAdapter(bankPaymentAdapter);
                                                bankPaymentAdapter.notifyDataSetChanged();
                                            } else {
                                                llBanklayuout.setVisibility(View.GONE);
                                                bankPaymentList.clear();
                                                bankPaymentAdapter = new BankPayment_Adapter(activity, bankPaymentList);
                                                nonscrolllistviewBank.setAdapter(bankPaymentAdapter);
                                                bankPaymentAdapter.notifyDataSetChanged();
                                            }
                                        }
                                        if (paymentType.getPaymentmethodId() == 3) {
                                            if (paymentType.isChecked()) {
                                                llCardlayuout.setVisibility(View.VISIBLE);
                                                CardPaymentList cardPayment = new CardPaymentList();
                                                cardPayment.setCardAmt(0);
                                                cardPayment.setCardAmount(0);
                                                cardPayment.setPaymentType(paymentType.getPaymentmethodId());
                                                cardPaymentList.add(cardPayment);
                                                cardPaymentAdapter = new CardPayment_Adapter(activity, cardPaymentList);
                                                nonscrolllistviewCredit.setAdapter(cardPaymentAdapter);
                                                cardPaymentAdapter.notifyDataSetChanged();
                                            } else {
                                                llCardlayuout.setVisibility(View.GONE);
                                                cardPaymentList.clear();
                                                cardPaymentAdapter = new CardPayment_Adapter(activity, cardPaymentList);
                                                nonscrolllistviewCredit.setAdapter(cardPaymentAdapter);
                                                cardPaymentAdapter.notifyDataSetChanged();
                                            }
                                        }
                                        if (paymentType.getPaymentmethodId() == 4) {
                                            if (paymentType.isChecked()) {
                                                llVopucherlayuout.setVisibility(View.VISIBLE);
                                                MultiVoucherPayment voucherPayment = new MultiVoucherPayment();
                                                voucherPayment.setPaymentType(paymentType.getPaymentmethodId());
                                                voucherPaymentList.add(voucherPayment);
                                                voucherPaymentAdapter = new VoucherPayment_Adapter(activity, voucherPaymentList);
                                                nonscrolllistviewVoucher.setAdapter(voucherPaymentAdapter);
                                                voucherPaymentAdapter.notifyDataSetChanged();
                                            } else {
                                                llVopucherlayuout.setVisibility(View.GONE);
                                                voucherPaymentList.clear();
                                                voucherPaymentAdapter = new VoucherPayment_Adapter(activity, voucherPaymentList);
                                                nonscrolllistviewVoucher.setAdapter(voucherPaymentAdapter);
                                                voucherPaymentAdapter.notifyDataSetChanged();
                                            }
                                        }


                                    }
                                }
                            });
                            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(activity, 2);
                            recycleViewPaymentType.setLayoutManager(mLayoutManager);
                            recycleViewPaymentType.setItemAnimator(new DefaultItemAnimator());
                            recycleViewPaymentType.setAdapter(paymentTypeAdapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                } else {
                    UtilView.showErrorDialog("Some Error", activity);
                }
            }
        }, false);
        task.execute(url, "");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {

            case resetConnection:

                if (!mBluetoothAdapter.isEnabled()) {
                    Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
                } else {//If the connection is lost with last connected bluetooth printer

                    if (BLUETOOTH_PRINTER.IsNoConnection()) {
                        serverIntent = new Intent(activity, DeviceListActivity.class);
                        serverIntent.putExtra("callingFrom",callingfrom);
                        startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);
                    } else { //If an existing connection is still alive then ask user to kill it and re-connect again
                        alertDlgBuilder.setTitle(getResources().getString(R.string.alert_title));
                        alertDlgBuilder.setMessage(getResources().getString(R.string.alert_message));
                        alertDlgBuilder.setNegativeButton(getResources().getString(R.string.alert_btn_negative), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                }
                        );
                        alertDlgBuilder.setPositiveButton(getResources().getString(R.string.alert_btn_positive), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        BLUETOOTH_PRINTER.stop();
                                        serverIntent = new Intent(activity, DeviceListActivity.class);
                                        startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);
                                    }
                                }
                        );
                        alertDlgBuilder.show();

                    }
                }

                break;

        }


        return super.onOptionsItemSelected(item);


    }

    /**
     * The Handler that gets information back from Bluetooth Devices
     */
    static class BluetoothHandler extends Handler {
        private final WeakReference<Activity_Checkout> myWeakReference;

        //Creating weak reference of BluetoothPrinterActivity class to avoid any leak
        BluetoothHandler(Activity_Checkout weakReference) {
            myWeakReference = new WeakReference<Activity_Checkout>(weakReference);
        }

        @Override
        public void handleMessage(Message msg) {
            Activity_Checkout bluetoothPrinterActivity = myWeakReference.get();
            tvPrinterStatus.setVisibility(View.VISIBLE);
            if (bluetoothPrinterActivity != null) {
                super.handleMessage(msg);
                Bundle data = msg.getData();
                switch (data.getInt("flag")) {
                    case Contants.FLAG_STATE_CHANGE:
                        int state = data.getInt("state");
                        Log.i(TAG, "MESSAGE_STATE_CHANGE: " + state);
                        switch (state) {
                            case HsBluetoothPrintDriver.CONNECTED_BY_BLUETOOTH:

                                tvPrinterStatus.setText(R.string.title_connected_to);
                                tvPrinterStatus.append(device.getName());
                                Constant.isPrinterConnected = true;
                                //Toast.makeText(CONTEXT,"Connection successful.", Toast.LENGTH_SHORT).show();

                                break;
                            case HsBluetoothPrintDriver.FLAG_SUCCESS_CONNECT:
                                tvPrinterStatus.setText(R.string.title_connecting);
                                break;

                            case HsBluetoothPrintDriver.UNCONNECTED:
                                tvPrinterStatus.setText(R.string.no_printer_connected);
                                break;
                        }
                        break;
                    case Contants.FLAG_SUCCESS_CONNECT:
                        tvPrinterStatus.setText(R.string.title_connecting);
                        break;
                    case Contants.FLAG_FAIL_CONNECT:
                        // Toast.makeText(CONTEXT,"Connection failed.",Toast.LENGTH_SHORT).show();
                        tvPrinterStatus.setText("Connection failed.");
                        Constant.isPrinterConnected = false;
                        break;
                    default:
                        break;

                }
            }
        }

    }

    private class TaskCompleteListener implements AsyncTaskCompleteListener<String> {

        int casetype;

        public TaskCompleteListener(int caseSavePrint) {

            casetype = caseSavePrint;

        }

        @Override
        public void onTaskComplete(String result) {

            UtilView.hideProgessBar(activity, progressBar);
            if (result != null) {
                try {
                    Gson gson = new Gson();

                    switch (casetype) {
                        case CASE_SAVE_PRINT:

                            if (callingfrom.equals(Constant.NAVIGATION_GROUP_PURCHASE)) {
                                invoiceData = gson.fromJson(result.toString(), Checkout_ResponseData.class);

                                if (invoiceData != null) {
                                    if (invoiceData.getResult().equals("SUCCESS")) {
                                        posCreator.clear();
                                        if (invoiceData.getPiData() != null) {
                                            if (currentAPIVersion >= Build.VERSION_CODES.M) {
                                                checkPermission();
                                            } else {
                                                createPdf();
                                            }
                                        } else {
                                            UtilView.showToast(activity, "Purchase Order not placed. Please try again.");
                                        }
                                    } else {
                                        UtilView.showToast(activity, "Some Error");
                                    }
                                } else {
                                    UtilView.showErrorDialog("Purchase Order not placed successflly", activity);
                                }
                            }

                            if (callingfrom.equals(Constant.NAVIGATION_GROUP_RETAIL)) {
                                salesInvoiceData = gson.fromJson(result.toString(), Checkout_Sales_ResponseData.class);

                                if (salesInvoiceData != null) {
                                    if (salesInvoiceData.getResult().equals("SUCCESS")) {
                                        posCreator.clear();
                                        if (salesInvoiceData.getSiData() != null) {
                                            if (currentAPIVersion >= Build.VERSION_CODES.M) {
                                                checkPermission();
                                            } else {
                                                createSalesPdf();
                                            }
                                        } else {
                                            UtilView.showToast(activity, "Sales Order not placed. Please try again.");
                                        }

                                    } else {
                                        UtilView.showToast(activity, "Some Error");
                                    }
                                } else {
                                    UtilView.showErrorDialog("Sales Order not placed successflly", activity);
                                }
                            }

                            if (callingfrom.equals(Constant.NAVIGATION_GROUP_GENERALTRANSACTION_EXPENSE)) {
                                JSONObject jsonObject = new JSONObject(result.toString());
                                saveResponseData = gson.fromJson(jsonObject.toString(), SaveResponseData.class);
                                if (saveResponseData != null) {
                                    accountPosCreator.clear();
                                    if (currentAPIVersion >= Build.VERSION_CODES.M) {
                                        checkPermission();
                                    } else {

                                        createGTPdf();
                                    }
                                } else {
                                    UtilView.showToast(activity, "Some Error. Please try Again.");
                                }
                            }

                            if (callingfrom.equals(Constant.NAVIGATION_GROUP_GENERALTRANSACTION_RECIEPT)) {
                                JSONObject jsonObject = new JSONObject(result.toString());
                                saveResponseData = gson.fromJson(jsonObject.toString(), SaveResponseData.class);
                                if (saveResponseData != null) {
                                    accountPosCreator.clear();
                                    if (currentAPIVersion >= Build.VERSION_CODES.M) {
                                        checkPermission();
                                    } else {

                                        createGTPdf();
                                    }
                                } else {
                                    UtilView.showToast(activity, "Some Error. Please try Again.");
                                }
                            }
                            break;

                        case CASE_SAVE_EMAIL_EXIST:
                            posCreator.clear();
                            final SweetAlertDialog pDialog = new SweetAlertDialog(activity, SweetAlertDialog.SUCCESS_TYPE);
                            pDialog.setTitleText("Invoice Sent Successfully to ");
                            pDialog.setContentText(purchaseSaveData.getCutomerName());
                            pDialog.setCancelable(false);
                            pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    if (pDialog != null && pDialog.isShowing()) {
                                        pDialog.dismiss();
                                    }
                                    Intent intent = new Intent(activity, NavigationDrawer_Activity.class);
                                    intent.putExtra(Constant.NAVIGATION_GROUPHEADER, Constant.NAVIGATION_GROUP_PURCHASE);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                            if (pDialog != null)
                                pDialog.show();
                            break;

                        case CASE_SAVE_EMAIL_INPUT:
                            if (result.equals(getResources().getString(R.string.error_rsonsecode204))) {
                                UtilView.showErrorDialog(getResources().getString(R.string.error_null), activity);
                            } else {
                                try {


                                    JSONObject jsonObject = new JSONObject(result);
                                    String result1 = jsonObject.getString("result");
                                    if (result1.equals("no mail server")) {
                                        posCreator.clear();
                                        final SweetAlertDialog pDialog1 = new SweetAlertDialog(activity, SweetAlertDialog.SUCCESS_TYPE);
                                        pDialog1.setTitleText("No mail server configured.");
                                        pDialog1.setCancelable(false);
                                        pDialog1.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                if (pDialog1 != null && pDialog1.isShowing()) {
                                                    pDialog1.dismiss();
                                                }
                                            }
                                        });
                                        if (pDialog1 != null)
                                            pDialog1.show();
                                    } else {
                                        posCreator.clear();
                                        final SweetAlertDialog pDialog1 = new SweetAlertDialog(activity, SweetAlertDialog.SUCCESS_TYPE);
                                        pDialog1.setTitleText("Invoice Sent Successfully on ");
                                        pDialog1.setContentText(input_email);
                                        pDialog1.setCancelable(false);
                                        pDialog1.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                if (pDialog1 != null && pDialog1.isShowing()) {
                                                    pDialog1.dismiss();
                                                }
                                                Intent intent = new Intent(activity, NavigationDrawer_Activity.class);
                                                intent.putExtra(Constant.NAVIGATION_GROUPHEADER, Constant.NAVIGATION_GROUP_PURCHASE);
                                                startActivity(intent);
                                                finish();
                                            }
                                        });
                                        if (pDialog1 != null)
                                            pDialog1.show();
                                    }
                                } catch (Exception e) {

                                }
                            }
                            break;
                    }
                } catch (Exception e) {
                    Log.e("Sales Error",e.toString());
                   // UtilView.showErrorDialog(getResources().getString(R.string.error_null), activity);
                }
            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.error_null), activity);
            }
        }
    }
}
