package in.hiaccounts.hinext.sales.activity;


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
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
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
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.configuration.ConfigurationData;
import in.hiaccounts.hinext.customer.model.Customer;
import in.hiaccounts.hinext.sales.adapter.PaymentType_Adapter;
import in.hiaccounts.hinext.sales.adapter.PosGSTSummary_Adapter;
import in.hiaccounts.hinext.sales.adapter.PosItem_Adapter;
import in.hiaccounts.hinext.sales.adapter.SalesCardPayment_Adapter;
import in.hiaccounts.hinext.sales.adapter.SalesVoucherPayment_Adapter;
import in.hiaccounts.hinext.sales.model.CardPaymentList;
import in.hiaccounts.hinext.sales.model.CashPayment;
import in.hiaccounts.hinext.sales.model.MultiCashPaymentList;
import in.hiaccounts.hinext.sales.model.MultiVoucherPayment;
import in.hiaccounts.hinext.sales.model.RemainingPaymentSaveData;
import in.hiaccounts.hinext.sales.model.SalesAdvancePayment;
import in.hiaccounts.hinext.sales.model.checkout.PaymentType;
import in.hiaccounts.hinext.sales.model.checkout.SalesSaveData;
import in.hiaccounts.hinext.sales.model.checkout.SelectedItemsList;
import in.hiaccounts.hinext.sales.model.invoke_salesorder.InvokeSalesOrderItem;
import in.hiaccounts.hinext.sales.model.invoke_salesorder.InvokeSalesSaveData;
import in.hiaccounts.hinext.sales.model.sales_pos.SalesPosCreator;
import in.hiaccounts.hinext.sales.model.sales_pos.SalesPosHelper;
import in.hiaccounts.hinext.sales.model.save_response.CompanyData;
import in.hiaccounts.hinext.sales.model.save_response.SavePaymentPrintResponse;
import in.hiaccounts.hinext.sales.model.save_response.SavePrintResponse;
import in.hiaccounts.hinext.sales.pos_printer.DeviceListActivity;
import in.hiaccounts.pdfgenerator.PdfUtils;
import in.hiaccounts.pdfgenerator.sales.payment.PosA4SalesPaymentPdfImpl;
import in.hiaccounts.pdfgenerator.sales.payment.PosSalesPaymetPdf;
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

public class Activity_SalesCheckout extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public static final int CASE_SAVE_PRINT = 0;
    public static final int CASE_SAVE_EMAIL_EXIST = 1;
    public static final int CASE_SAVE_EMAIL_INPUT = 2;
    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 123;
    private int currentAPIVersion = Build.VERSION.SDK_INT;
    private static String TAG = Activity_SalesCheckout.class.getSimpleName();
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
    @BindView(R.id.ll_bottomlayout)
    LinearLayout llBottomlayout;

    @BindView(R.id.ed_cashamounttendered)
    EditText edCashamounttendered;
    SharedPreference sharedPreference;
    @BindView(R.id.ed_emailid)
    EditText edEmailid;
    @BindView(R.id.ll_email)
    LinearLayout llEmail;
    String input_email = "";
    Boolean isInternetPresent = false;
    String callingfrom = "";
    @BindView(R.id.edSupplierInvNo)
    EditText edSupplierInvNo;
    @BindView(R.id.llSupplierInvoiceNo)
    LinearLayout llSupplierInvoiceNo;
    @BindView(R.id.llContent)
    LinearLayout llContent;
    @BindView(R.id.imgViewCompanyLogo)
    ImageView imgViewCompanyLogo;
    @BindView(R.id.tv_comanyname)
    TextView tvComanyname;
    @BindView(R.id.tvCompnayTelNo)
    TextView tvCompnayTelNo;
    @BindView(R.id.tvCompnayFaxNo)
    TextView tvCompnayFaxNo;
    @BindView(R.id.tvGstNo)
    TextView tvGstNo;
    @BindView(R.id.tvInvDate)
    TextView tvInvDate;
    @BindView(R.id.tvInvNumber)
    TextView tvInvNumber;
    @BindView(R.id.tvCustomerName)
    TextView tvCustomerName;
    @BindView(R.id.lv_invoiceitems)
    NonScrollListView lvInvoiceitems;
    @BindView(R.id.tvItemCount)
    TextView tvItemCount;
    @BindView(R.id.tvTotalAmtIncGst)
    TextView tvTotalAmtIncGst;
    @BindView(R.id.tvTotalDisAmt)
    TextView tvTotalDisAmt;
    @BindView(R.id.tvRoundingAdjustment)
    TextView tvRoundingAdjustment;
    @BindView(R.id.lvGstSummary)
    NonScrollListView lvGstSummary;
    @BindView(R.id.tvTaxType)
    TextView tvTaxType;
    @BindView(R.id.tvCessAmt)
    TextView tvCessAmt;
    @BindView(R.id.tvPrintDate)
    TextView tvPrintDate;
    @BindView(R.id.tvSalesPerson)
    TextView tvSalesPerson;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.btnPrint)
    Button btnPrint;
    @BindView(R.id.rlContent)
    RelativeLayout rlContent;
    @BindView(R.id.recycleViewPaymentType)
    RecyclerView recycleViewPaymentType;


    private static TextView tvPrinterStatus = null;


    private List<CardPaymentList> cardPaymentList = new ArrayList<CardPaymentList>();
    private List<MultiVoucherPayment> voucherPaymentList = new ArrayList<MultiVoucherPayment>();
    private SalesCardPayment_Adapter cardPaymentAdapter;
    private SalesVoucherPayment_Adapter voucherPaymentAdapter;
    private SalesSaveData saveData;

    private Activity activity;
    private ServiceHandler serviceHandler;
    private SalesAdvancePayment paymentInvoice = null;
    private Customer customer = null;
    private String selectedTaxType = "", checkoutType = null, selectedDate = "", serverUrl, printerStatus = "", printerType = "";
    private SavePrintResponse saleInoviceData = null;
    private SavePaymentPrintResponse printPaymnetResponseData = null;
    private ConfigurationData configData;
    private SalesPosCreator posCreator;


    private static Context CONTEXT;

    private AlertDialog.Builder alertDlgBuilder;
    private static BluetoothDevice device;
    private static final int REQUEST_CONNECT_DEVICE = 1;
    private static final int REQUEST_ENABLE_BT = 2;
    private BluetoothAdapter mBluetoothAdapter = null;
    public static HsBluetoothPrintDriver BLUETOOTH_PRINTER = null;


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
    }

    private void initComponent() {
        ButterKnife.bind(this);
        tvPrinterStatus = (TextView) findViewById(R.id.tvPrinterStatus);
        CONTEXT = getApplicationContext();
        posCreator = SalesPosHelper.getPosCreator();
        alertDlgBuilder = new AlertDialog.Builder(Activity_SalesCheckout.this);

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
            getConfigurationDataFromServer();
            getPaymentTypeDataFromServer();
        } else {
            UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
        }
        Intent intent = getIntent();
        if (intent != null) {
            String jsonsaveData = intent.getStringExtra("saveData");
            callingfrom = intent.getStringExtra("callingfrom");
            selectedTaxType = intent.getStringExtra("selectedTax");
            checkoutType = intent.getStringExtra("checkoutType");
            selectedDate = intent.getStringExtra("selectedDate");
            paymentInvoice = (SalesAdvancePayment) intent.getSerializableExtra("paymentInvoice");
            customer = (Customer) intent.getSerializableExtra("customer");
            if (jsonsaveData != null) {
                Gson gson = new Gson();
                saveData = gson.fromJson(jsonsaveData, SalesSaveData.class);
                edTotalamount.setText(String.format("%.2f", saveData.getTotalCheckOutamt()));
                edTotaldiscount.setText(String.format("%.2f", saveData.getDiscountAmount()));
            }
            if (paymentInvoice != null) {
                btnSaveemail.setVisibility(View.GONE);
                edTotalamount.setText(String.format("%.2f", paymentInvoice.getArbalance()));

            }
        }
        edTotalamount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s == null || s.equals("")) {
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
                if (s == null || s.equals("")) {
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
                if (s == null || s.equals("")) {
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
                    cardPaymentAdapter = new SalesCardPayment_Adapter(activity, cardPaymentList);
                    nonscrolllistviewCredit.setAdapter(cardPaymentAdapter);
                    cardPaymentAdapter.notifyDataSetChanged();
                } else {
                    llCardlayuout.setVisibility(View.GONE);
                    cardPaymentList.clear();
                    cardPaymentAdapter = new SalesCardPayment_Adapter(activity, cardPaymentList);
                    nonscrolllistviewCredit.setAdapter(cardPaymentAdapter);
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
                    voucherPaymentAdapter = new SalesVoucherPayment_Adapter(activity, voucherPaymentList);
                    nonscrolllistviewVoucher.setAdapter(voucherPaymentAdapter);
                    voucherPaymentAdapter.notifyDataSetChanged();
                } else {
                    llVopucherlayuout.setVisibility(View.GONE);
                    voucherPaymentList.clear();
                    voucherPaymentAdapter = new SalesVoucherPayment_Adapter(activity, voucherPaymentList);
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

                        List<PaymentType>paymentTypeList=new ArrayList<>();

                        try {
                            JSONArray jsonArray=new JSONArray(result.toString());//5
                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject=jsonArray.getJSONObject(i);
                                Gson gson=new Gson();
                                PaymentType paymentType=gson.fromJson(jsonObject.toString(),PaymentType.class);
                                paymentTypeList.add(paymentType);
                            }

                            PaymentType_Adapter adapter=new PaymentType_Adapter(activity, paymentTypeList, new PaymentType_Adapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(PaymentType paymentType) {
                                    if(paymentType.getPaymentmethodId()!=null){
                                        if (paymentType.getPaymentmethodId()==1) {
                                            if (paymentType.isChecked()) {
                                                llCashlayuout.setVisibility(View.VISIBLE);
                                            } else {
                                                llCashlayuout.setVisibility(View.GONE);
                                                edCashamounttendered.setText("0");
                                            }
                                        }
                                        if (paymentType.getPaymentmethodId()==2) {

                                        }
                                        if (paymentType.getPaymentmethodId()==3) {
                                            if (paymentType.isChecked()) {
                                                llCardlayuout.setVisibility(View.VISIBLE);
                                                cardPaymentList.add(new CardPaymentList());
                                                cardPaymentAdapter = new SalesCardPayment_Adapter(activity, cardPaymentList);
                                                nonscrolllistviewCredit.setAdapter(cardPaymentAdapter);
                                                cardPaymentAdapter.notifyDataSetChanged();
                                            } else {
                                                llCardlayuout.setVisibility(View.GONE);
                                                cardPaymentList.clear();
                                                cardPaymentAdapter = new SalesCardPayment_Adapter(activity, cardPaymentList);
                                                nonscrolllistviewCredit.setAdapter(cardPaymentAdapter);
                                                cardPaymentAdapter.notifyDataSetChanged();
                                            }
                                        }
                                        if (paymentType.getPaymentmethodId()==4) {
                                            if (paymentType.isChecked()) {
                                                llVopucherlayuout.setVisibility(View.VISIBLE);
                                                voucherPaymentList.add(new MultiVoucherPayment());
                                                voucherPaymentAdapter = new SalesVoucherPayment_Adapter(activity, voucherPaymentList);
                                                nonscrolllistviewVoucher.setAdapter(voucherPaymentAdapter);
                                                voucherPaymentAdapter.notifyDataSetChanged();
                                            } else {
                                                llVopucherlayuout.setVisibility(View.GONE);
                                                voucherPaymentList.clear();
                                                voucherPaymentAdapter = new SalesVoucherPayment_Adapter(activity, voucherPaymentList);
                                                nonscrolllistviewVoucher.setAdapter(voucherPaymentAdapter);
                                                voucherPaymentAdapter.notifyDataSetChanged();
                                            }
                                        }


                                    }
                                }
                            });
                            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(activity,2);
                            recycleViewPaymentType.setLayoutManager(mLayoutManager);
                            recycleViewPaymentType.setItemAnimator(new DefaultItemAnimator());
                            recycleViewPaymentType.setAdapter(adapter);





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

    @OnClick({R.id.btn_saveprint, R.id.btn_saveemail, R.id.btn_cancel})
    public void onViewClicked(View view) {
        String url = serverUrl;
        String checkoutData = "";
        PostDataTask postDataTask;
        switch (view.getId()) {
            case R.id.btn_saveprint:
                if (saveData != null) {

                    if (configData != null) {
                        if (configData.getPrintType() != null) {
                            if (configData.getPrintType().equals(Constant.PRINTYPE_POS)) {
                                if (BLUETOOTH_PRINTER != null) {
                                    if (BLUETOOTH_PRINTER.IsNoConnection()) {

                                        UtilView.showToast(activity, getResources().getString(R.string.title_connection));
                                    } else {
                                        callSalesCheckout();
                                    }
                                }
                            } else {
                                callSalesCheckout();
                            }
                        } else {
                            callSalesCheckout();
                        }

                    } else {
                        callSalesCheckout();
                    }


                }
                if (paymentInvoice != null) {
                    callAdvanceRemainingPayment();
                }

                break;
            case R.id.btn_saveemail:
                if (saveData != null) {
                    if (!saveData.getCustomerEmail().equals("")) {
                        checkoutData = getCheckoutData(CASE_SAVE_EMAIL_EXIST);
                        isInternetPresent = serviceHandler.isConnectingToInternet();
                        if (serverUrl != null) {
                            if (isInternetPresent) {
                                UtilView.showProgessBar(activity, progressBar);
                                postDataTask = new PostDataTask(activity, new TaskCompleteListener(CASE_SAVE_EMAIL_EXIST), false);
                                postDataTask.execute(checkoutData, url + "/retail//" + Constant.FUNTION_INVOICESAVEANDMAIL, "");

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
                                        UtilView.showProgessBar(activity, progressBar);
                                        postDataTask = new PostDataTask(activity, new TaskCompleteListener(CASE_SAVE_EMAIL_INPUT), false);
                                        postDataTask.execute(checkoutData, url + "/retail//" + Constant.FUNTION_INVOICESAVEANDMAIL, "");
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
                        if (s == null || s.equals("")) {
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
                postDataTask = new PostDataTask(activity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(activity, progressBar);
                        if (result != null) {
                            Gson gson = new Gson();
                            try {
                                printPaymnetResponseData = gson.fromJson(result.toString(), SavePaymentPrintResponse.class);
                                if (printPaymnetResponseData != null) {
                                    if (currentAPIVersion >= Build.VERSION_CODES.M) {
                                        checkPermission();
                                    } else {
                                        createPaymentPdf();
                                    }
                                } else {
                                    UtilView.showLogCat(TAG, getResources().getString(R.string.response_error));
                                }


                            } catch (Exception e) {
                                UtilView.showErrorDialog(getResources().getString(R.string.response_error), activity);
                            }
                        }
                    }
                }, false);
                postDataTask.execute(checkoutData, url + "/retail//" + Constant.FUNTION_ADVANCEPARTIALPAYMENT + "/" + paymentInvoice.getFormNo(), "");
            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
            }
        } else {
            UtilView.gotToLogin(activity);
        }
    }
    public String getSalesInvoiceDemoData(){

        String salesData="{\n" +
                "  \"siid\": 0,\n" +
                "  \"selectedItemsList\": [\n" +
                "    {\n" +
                "      \"itemCode\": \"ITEM01\",\n" +
                "      \"itemId\": 1,\n" +
                "      \"itemName\": \"HP\",\n" +
                "      \"itemDescription\": \"hp laptops\",\n" +
                "      \"serializableStatus\": \"Serialize\",\n" +
                "      \"unitPrice\": 30000,\n" +
                "      \"unitPriceIn\": \"30000.00\",\n" +
                "      \"discountAmt\": 0,\n" +
                "      \"isDiscountInPercent\": false,\n" +
                "      \"cess\": 0,\n" +
                "      \"qty\": 1,\n" +
                "      \"returnQty\": 0,\n" +
                "      \"uomName\": \"Unit\",\n" +
                "      \"remainingQty\": 1,\n" +
                "      \"amtexclusivetax\": 30000,\n" +
                "      \"taxid\": 8,\n" +
                "      \"uom\": 0,\n" +
                "      \"convertedQuantity\": 0,\n" +
                "      \"uomConvertorList\": [\n" +
                "        {\n" +
                "          \"uomConvertorId\": 0,\n" +
                "          \"unitOfMeasurement\": null,\n" +
                "          \"uomConvertorEffectiveDate\": null,\n" +
                "          \"uomValue\": \"1\",\n" +
                "          \"locationId\": null,\n" +
                "          \"useraccount_id\": null,\n" +
                "          \"status\": null,\n" +
                "          \"uomConvertedName\": \"Unit\",\n" +
                "          \"unitOfMeasurementId\": null\n" +
                "        }\n" +
                "      ],\n" +
                "      \"taxpercent\": \"18.0 \",\n" +
                "      \"taxName\": \" GST-18 \",\n" +
                "      \"cessTaxAmt\": 0,\n" +
                "      \"taxamt\": 5400,\n" +
                "      \"igTax\": 5400,\n" +
                "      \"amtinclusivetax\": 35400,\n" +
                "      \"hsnCode\": \"29419014\",\n" +
                "      \"cessTotalTaxAmt\": 0,\n" +
                "      \"inclusiveJSON\": \"{\\\"purchases\\\":false,\\\"sales\\\":false}\",\n" +
                "      \"salesPrice\": 30000,\n" +
                "      \"taxAmountSplit\": \"2700+2700\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"cashPayment\": {\n" +
                "    \"multiCashPaymentList\": []\n" +
                "  },\n" +
                "  \"creditPayment\": {\n" +
                "    \"cardPaymentList\": []\n" +
                "  },\n" +
                "  \"bankPayment\": {\n" +
                "    \"multiBankPaymentList\": []\n" +
                "  },\n" +
                "  \"voucherPayment\": {\n" +
                "    \"multiVoucherPayments\": []\n" +
                "  },\n" +
                "  \"totalCheckOutamt\": 35400,\n" +
                "  \"paymentType\": \"multiPayment\",\n" +
                "  \"totalTaxAmt\": 5400,\n" +
                "  \"cessTotalTaxAmt\": \"0.00\",\n" +
                "  \"taxType\": \"CGST:SGST/UGST\",\n" +
                "  \"customerId\": 2,\n" +
                "  \"customerEmail\": \"\",\n" +
                "  \"cutomerName\": \"Cash Customer|01\",\n" +
                "  \"amountReturned\": 0,\n" +
                "  \"discountAmount\": 0,\n" +
                "  \"totalTenderedAmount\": 0,\n" +
                "  \"cmpyLocation\": 1,\n" +
                "  \"custLocation\": 18,\n" +
                "  \"shippingDate\": \"2017-11-25T05:16:13.843Z\",\n" +
                "  \"dateOfInvoice\": \"2017-11-25T05:16:14.098Z\",\n" +
                "  \"tcsAmount\": 0,\n" +
                "  \"tdsAmount\": 0\n" +
                "}";

        return salesData;

    }
    private void callSalesCheckout() {
        String url = serverUrl;
        String checkoutData = "";
        PostDataTask postDataTask;
        checkoutData = getCheckoutData(CASE_SAVE_PRINT);
       // checkoutData=getSalesInvoiceDemoData();
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
                UtilView.showProgessBar(activity, progressBar);
                if (callingfrom != null) {
                    if (callingfrom.equals(Constant.NAVIGATION_GROUP_RETAIL)) {
                        postDataTask = new PostDataTask(activity, new TaskCompleteListener(CASE_SAVE_PRINT), false);
                        postDataTask.execute(checkoutData, url + "/retail//" + Constant.FUNTION_INVOICESAVE, "");
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
        String jsonPaymentData = null;
        switch (caseSavePrint) {
            case CASE_SAVE_PRINT:
                if (saveData != null) {
                    customerEmail = saveData.getCustomerEmail();
                }
                if (paymentInvoice != null) {
                    customerEmail = paymentInvoice.getCustomerEmail();
                }
                break;
            case CASE_SAVE_EMAIL_EXIST:
                if (saveData != null) {
                    customerEmail = saveData.getCustomerEmail();
                }
                break;
            case CASE_SAVE_EMAIL_INPUT:
                customerEmail = input_email;
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
        double totalCPAmountTendered = Double.parseDouble(tender);
        String totalTenderedAmount = edCashamounttendered.getText().toString().trim();
        if (totalTenderedAmount == null || totalTenderedAmount.equals("")) {
            totalTenderedAmount = "0.00";
        }
        BigDecimal big2 = new BigDecimal(totalTenderedAmount);
        double tenderedAmount = Double.parseDouble(String.valueOf(big2.setScale(2, RoundingMode.HALF_UP)));
        CashPayment cashPayment = new CashPayment();
        List<MultiCashPaymentList> mulitCashPaymentList=new ArrayList<>();
        MultiCashPaymentList mulitCashPayment=new MultiCashPaymentList();
        mulitCashPayment.setCashAmt(tenderedAmount);
        mulitCashPayment.setPaymentType(1L);
        mulitCashPaymentList.add(mulitCashPayment);
        cashPayment.setMultiCashPaymentList(mulitCashPaymentList);
        cashPayment.setTotalCPAmountTendered(tenderedAmount);
      /*  CreditPayment creditPayment = new CreditPayment();
        creditPayment.setCardPaymentList(cardPaymentList);
        VoucherPayment voucherPayment = new VoucherPayment();
        voucherPayment.setMultiVoucherPayments(voucherPaymentList);*/
        if (saveData != null) {
            if (checkoutType != null && checkoutType.equals(Constant.CHECKOUTYPE_INVOKESALESORDER)) {
                InvokeSalesSaveData checkOutData = new InvokeSalesSaveData();
                List<InvokeSalesOrderItem> itemList = new ArrayList<InvokeSalesOrderItem>();
                //checkOutData.setCashPayment(cashPayment);
                checkOutData.setDiscountAmount(discountAmount);
                /*checkOutData.setVoucherPayment(voucherPayment);
                checkOutData.setCreditPayment(creditPayment);*/
                checkOutData.setTotalTenderedAmount(totalCPAmountTendered);
                checkOutData.setAmountReturned("" + amountReturned);
                checkOutData.setTaxType(saveData.getTaxType());
                checkOutData.setCessTotalTaxAmt("" + saveData.getCessTotalTaxAmt());
                checkOutData.setCustomerEmail(saveData.getCustomerEmail());
                checkOutData.setCustomerId(saveData.getCustomerId());
                checkOutData.setCutomerName(saveData.getCutomerName());
                checkOutData.setDateOfInvoice(saveData.getDateOfInvoice());
                checkOutData.setCustomerEmail(customerEmail);
                checkOutData.setPaymentType(saveData.getPaymentType());
                checkOutData.setSalesOrderId(saveData.getSalesOrderId());
                checkOutData.setTotalCheckOutamt(saveData.getTotalCheckOutamt());
                checkOutData.setTotalTaxAmt(saveData.getTotalTaxAmt());
                for (int i = 0; i < saveData.getSelectedItemsList().size(); i++) {
                    SelectedItemsList item = saveData.getSelectedItemsList().get(i);
                    InvokeSalesOrderItem saveItem = new InvokeSalesOrderItem();
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
                    saveItem.setUomName(item.getUomName());
                    saveItem.setItemDescription(item.getItemDescription());
                    saveItem.setTaxName(item.getTaxName());

                    try {
                        saveItem.setTaxpercent(Double.parseDouble(item.getTaxpercent()));
                    } catch (Exception e) {
                        saveItem.setTaxpercent(0);
                    }

                    itemList.add(saveItem);

                }
                checkOutData.setSelectedItemsList(itemList);
                jsonPaymentData = gson.toJson(checkOutData).toString();
                return jsonPaymentData;
            } else {
                //saveData.setCashPayment(cashPayment);
                saveData.setDiscountAmount(discountAmount);
               /* saveData.setVoucherPayment(voucherPayment);
                saveData.setCreditPayment(creditPayment);*/
                saveData.setTotalTenderedAmount(totalCPAmountTendered);
                saveData.setAmountReturned("" + amountReturned);
                saveData.setPaymentType("multiPayment");
                jsonPaymentData = gson.toJson(saveData).toString();
                return jsonPaymentData;
            }
        }

        if (paymentInvoice != null) {
            BigDecimal big = new BigDecimal(paymentInvoice.getArbalance());
            totalCheckoutAmt = Double.parseDouble(String.valueOf(big.setScale(2, RoundingMode.HALF_UP)));
            RemainingPaymentSaveData advanceRemainingPaymnetData = new RemainingPaymentSaveData();
            advanceRemainingPaymnetData.setCashPayment(cashPayment);
            /*advanceRemainingPaymnetData.setVoucherPayment(voucherPayment);
            advanceRemainingPaymnetData.setCreditPayment(creditPayment);*/
            advanceRemainingPaymnetData.setTotalCheckOutamt(totalCheckoutAmt);
            advanceRemainingPaymnetData.setDiscountAmount(discountAmount);
            advanceRemainingPaymnetData.setTotalTenderedAmount(totalCPAmountTendered);
            advanceRemainingPaymnetData.setAmountReturned("" + amountReturned);
            advanceRemainingPaymnetData.setTaxType(selectedTaxType);
            long customerID = 0;
            String customerName = "";
            if (customer != null) {
                customerID = customer.getCustomerId();
                customerName = customer.getCustomerName() + "|" + customer.getCustomerNumber();
                advanceRemainingPaymnetData.setCustomerId(customerID);
                advanceRemainingPaymnetData.setCutomerName(customerName);
            }
            advanceRemainingPaymnetData.setDateOfInvoice(selectedDate);
            jsonPaymentData = gson.toJson(advanceRemainingPaymnetData).toString();
            return jsonPaymentData;
        }
        return null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (view.getId()) {
            case R.id.imageview_addcard:
                cardPaymentList.add(cardPaymentList.size(), new CardPaymentList());
                cardPaymentAdapter = new SalesCardPayment_Adapter(activity, cardPaymentList);
                nonscrolllistviewCredit.setAdapter(cardPaymentAdapter);
                cardPaymentAdapter.notifyDataSetChanged();
                setTenderedAmount();
                break;

            case R.id.imageview_removecard:
                cardPaymentList.remove(position);
                cardPaymentAdapter = new SalesCardPayment_Adapter(activity, cardPaymentList);
                nonscrolllistviewCredit.setAdapter(cardPaymentAdapter);
                cardPaymentAdapter.notifyDataSetChanged();
                setTenderedAmount();
                break;

            case R.id.imageview_voucheradd:
                voucherPaymentList.add(voucherPaymentList.size(), new MultiVoucherPayment());
                voucherPaymentAdapter = new SalesVoucherPayment_Adapter(activity, voucherPaymentList);
                nonscrolllistviewVoucher.setAdapter(voucherPaymentAdapter);
                voucherPaymentAdapter.notifyDataSetChanged();
                setTenderedAmount();
                break;

            case R.id.imageview_voucherremove:
                voucherPaymentList.remove(position);
                voucherPaymentAdapter = new SalesVoucherPayment_Adapter(activity, voucherPaymentList);
                nonscrolllistviewVoucher.setAdapter(voucherPaymentAdapter);
                voucherPaymentAdapter.notifyDataSetChanged();
                setTenderedAmount();
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
        }
        for (int j = 0; j < voucherPaymentList.size(); j++) {
            tenderedAmount += voucherPaymentList.get(j).getAmt();
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

   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            Intent intent = new Intent(this, NavigationDrawer_Activity.class);
            intent.putExtra(Constant.NAVIGATION_GROUPHEADER, Constant.NAVIGATION_GROUP_RETAIL);
            startActivity(intent);
            finish();
        }


    }*/

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
                            saleInoviceData = gson.fromJson(result.toString(), SavePrintResponse.class);
                            if (saleInoviceData != null) {
                                if (saleInoviceData.getResult().equals("SUCCESS")) {
                                    posCreator.clear();
                                    if (saleInoviceData.getSiData() != null) {
                                        UtilView.showToast(activity,"Save Sales Order Successfully.");
                                        /*if (currentAPIVersion >= Build.VERSION_CODES.M) {
                                            checkPermission();
                                        } else {
                                            createPdf();
                                        }*/
                                    } else {
                                        UtilView.showToast(activity, "Sales Order not placed. Please try again.");
                                    }
                                } else {
                                    UtilView.showToast(activity, getResources().getString(R.string.response_error));
                                }
                            } else {
                                UtilView.showToast(activity, getResources().getString(R.string.response_error));
                            }
                            break;

                        case CASE_SAVE_EMAIL_EXIST:
                            posCreator.clear();
                            final SweetAlertDialog pDialog = new SweetAlertDialog(activity, SweetAlertDialog.SUCCESS_TYPE);
                            pDialog.setTitleText("Invoice Sent Successfully to ");
                            pDialog.setContentText(saveData.getCustomerEmail());
                            pDialog.setCancelable(false);
                            pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    if (pDialog.isShowing()) {
                                        pDialog.dismiss();
                                    }
                                    Intent intent = new Intent(activity, NavigationDrawer_Activity.class);
                                    if (callingfrom != null) {
                                        if (callingfrom.equals(Constant.NAVIGATION_GROUP_PURCHASE)) {
                                            intent.putExtra(Constant.NAVIGATION_GROUPHEADER, Constant.NAVIGATION_GROUP_PURCHASE);
                                        }
                                        if (callingfrom.equals(Constant.NAVIGATION_GROUP_RETAIL)) {
                                            intent.putExtra(Constant.NAVIGATION_GROUPHEADER, Constant.NAVIGATION_GROUP_RETAIL);
                                        }
                                        startActivity(intent);
                                        finish();
                                    }

                                }
                            });
                            if (pDialog != null)
                                pDialog.show();
                            break;

                        case CASE_SAVE_EMAIL_INPUT:
                            if (result.equals(getResources().getString(R.string.error_rsonsecode204))) {
                                UtilView.showErrorDialog(getResources().getString(R.string.error_null), activity);
                            } else {
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
                                            if (callingfrom != null) {
                                                if (callingfrom.equals(Constant.NAVIGATION_GROUP_PURCHASE)) {
                                                    intent.putExtra(Constant.NAVIGATION_GROUPHEADER, Constant.NAVIGATION_GROUP_PURCHASE);
                                                }
                                                if (callingfrom.equals(Constant.NAVIGATION_GROUP_RETAIL)) {
                                                    intent.putExtra(Constant.NAVIGATION_GROUPHEADER, Constant.NAVIGATION_GROUP_RETAIL);
                                                }
                                                startActivity(intent);
                                                finish();
                                            }
                                        }
                                    });
                                    if (pDialog1 != null)
                                        pDialog1.show();
                                }
                            }
                            break;
                    }
                } catch (Exception e) {
                    UtilView.showErrorDialog(getResources().getString(R.string.response_error), activity);
                }
            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.response_error), activity);
            }
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
            if (saveData != null) {
                //createPdf();
            }
            if (paymentInvoice != null) {
                createPaymentPdf();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (ActivityCompat.checkSelfPermission(this, permissions[0]) == PackageManager.PERMISSION_GRANTED) {
            switch (requestCode) {
                case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE:
                    if (saleInoviceData != null) {
                        //createPdf();
                    }
                    if (paymentInvoice != null) {
                        createPaymentPdf();
                    }
                    break;
            }
        } else {
            Toast.makeText(this, "Permisson Deny", Toast.LENGTH_LONG).show();
        }
    }

/*    private void createPdf() {
        File pdfFile = null;
        pdfFile = PdfUtils.createFile(saleInoviceData.getSiData().getSrlnNo() + ".pdf", Constant.DIRECTORY_SALES, Constant.DIRECTORY_SALESPOS);
        if (pdfFile != null) {

            progressBar.setVisibility(View.VISIBLE);
            if (saleInoviceData.getSiData().getPrintType() != null) {
                    if (saleInoviceData.getSiData().getPrintType().equals(Constant.PRINTYPE_POS)) {
                        boolean printStatus = false;
                        if (!printerType.equals("") && printerType.equals(getResources().getString(R.string.pos_3inch))) {
                            printStatus = PrintReceipt.printInvoice3Inch(Activity_SalesCheckout.this, saleInoviceData);
                        } else {
                            printStatus = PrintReceipt.printInvoice2Inch(Activity_SalesCheckout.this, saleInoviceData);
                        }
                        if (printStatus) {
                            BLUETOOTH_PRINTER.stop();
                            Intent returnIntent = new Intent();
                            setResult(Activity.RESULT_OK, returnIntent);
                            finish();
                        } else {

                        }

                    }

                    if (saleInoviceData.getSiData().getPrintType().equals(Constant.PRINTYPE_NORMAL)) {
                        InputStream logoInputStream = PdfUtils.getLogoInputStream(activity,progressBar);
                        if (logoInputStream != null) {
                            progressBar.setVisibility(View.GONE);
                            try {
                                PosSalesInvoicePdf invoicePdf = new PosA4SalesInvociePdfImpl();
                                invoicePdf.generateTaxInvoice(activity, saleInoviceData, new FileOutputStream(pdfFile), logoInputStream);
                                UtilView.hideProgessBar(activity, progressBar);
                                openPdfInvoice(saleInoviceData.getSiData().getSrlnNo() + ".pdf", Constant.DIRECTORY_SALES, Constant.DIRECTORY_SALESPOS);
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (DocumentException e) {
                                e.printStackTrace();
                            }
                        } else {
                            try {
                                PosSalesInvoicePdf invoicePdf = new PosA4SalesInvociePdfImpl();
                                invoicePdf.generateTaxInvoice(this, saleInoviceData, new FileOutputStream(pdfFile), null);
                                UtilView.hideProgessBar(activity, progressBar);
                                openPdfInvoice(saleInoviceData.getSiData().getSrlnNo() + ".pdf", Constant.DIRECTORY_SALES, Constant.DIRECTORY_SALESPOS);
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (DocumentException e) {
                                e.printStackTrace();
                            }
                        }
                    }
            }
        }
    }*/

    private void createPaymentPdf() {
        File pdfFile = null;
        pdfFile = PdfUtils.createFile(printPaymnetResponseData.getTranactionId() + ".pdf", Constant.DIRECTORY_SALES, Constant.DIRECTORY_SALESPAYMENT);
        if (pdfFile != null) {
            InputStream logoInputStream = PdfUtils.getLogoInputStream(this, progressBar);
            if (logoInputStream != null) {
                try {
                    PosSalesPaymetPdf invoicePdf = new PosA4SalesPaymentPdfImpl();
                    invoicePdf.generatePaymentPdf(activity, printPaymnetResponseData, new FileOutputStream(pdfFile), logoInputStream);
                    openPdfInvoice(printPaymnetResponseData.getTranactionId() + ".pdf", Constant.DIRECTORY_SALES, Constant.DIRECTORY_SALESPAYMENT);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    PosSalesPaymetPdf invoicePdf = new PosA4SalesPaymentPdfImpl();
                    invoicePdf.generatePaymentPdf(this, printPaymnetResponseData, new FileOutputStream(pdfFile), null);
                    openPdfInvoice(printPaymnetResponseData.getTranactionId() + ".pdf", Constant.DIRECTORY_SALES, Constant.DIRECTORY_SALESPAYMENT);
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


    private void callPosPrinter() {
        CompanyData companyData = saleInoviceData.getCompanyData();
        String date = "", time = "", invoiceNo = "", invoiceCustomerName = "";
        if (saleInoviceData.getDate() != null) {
            date = saleInoviceData.getDate();
        }
        if (saleInoviceData.getTime() != null) {
            time = saleInoviceData.getTime();
        }
        if (saleInoviceData.getSiData() != null) {
            if (saleInoviceData.getSiData().getSrlnNo() != null)
                invoiceNo = saleInoviceData.getSiData().getSrlnNo();
            if (saleInoviceData.getSiData().getCutomerName() != null)
                invoiceCustomerName = saleInoviceData.getSiData().getCutomerName();
        }
        if (companyData != null) {
            if (saleInoviceData.getCompanyData().getCompanyName() != null) {
                tvComanyname.setText(saleInoviceData.getCompanyData().getCompanyName().toUpperCase());
            }
            if (companyData.getPhone() != null)
                tvCompnayTelNo.setText(Constant.POS_TEL + "" + companyData.getPhone());
            if (companyData.getFax() != null)
                tvCompnayFaxNo.setText(Constant.POS_FAX + "" + companyData.getFax());
            if (companyData.getRegisterNo() != null)
                tvGstNo.setText(Constant.POS_GSTNO + "" + companyData.getRegisterNo());
        }
        tvInvDate.setText(Constant.POS_DATE + "" + date + " " + time);
        tvInvNumber.setText(Constant.POS_INVOICENO + "" + invoiceNo);
        tvCustomerName.setText(Constant.POS_CNAME + "" + invoiceCustomerName);

        if (saleInoviceData.getSiData() != null) {
            if (saleInoviceData.getSiData().getSelectedItemsList() != null) {

                PosItem_Adapter cardPaymentAdapter = new PosItem_Adapter(activity, saleInoviceData.getSiData().getSelectedItemsList());
                lvInvoiceitems.setAdapter(cardPaymentAdapter);
                cardPaymentAdapter.notifyDataSetChanged();

            }
            if (saleInoviceData.getSiData().getTaxSummaryList() != null) {

                PosGSTSummary_Adapter gstSummaryAdapter = new PosGSTSummary_Adapter(activity, saleInoviceData.getSiData().getTaxSummaryList());
                lvGstSummary.setAdapter(gstSummaryAdapter);
                gstSummaryAdapter.notifyDataSetChanged();

            }


            if (saleInoviceData.getSiData().getItemCount() != null)
                tvItemCount.setText("" + saleInoviceData.getSiData().getItemCount());

            if (saleInoviceData.getSiData().getDiscountAmount() != null)
                tvTotalDisAmt.setText("" + saleInoviceData.getSiData().getDiscountAmount());

            String taxType = "";
            if (saleInoviceData.getSiData().getTaxType() != null) {
                taxType = saleInoviceData.getSiData().getTaxType();
            }

            String totalTaxAmt = "";
            if (saleInoviceData.getTaxAmt() != null) {
                totalTaxAmt = String.format("%.2f", saleInoviceData.getTaxAmt() / 2);
            }

            tvTaxType.setText(Constant.POS_TAXTYPE + "" + taxType + ":" + totalTaxAmt + "+" + totalTaxAmt);

            if (saleInoviceData.getSiData().getCessTotalTaxAmt() != null)
                tvCessAmt.setText(Constant.POS_CESS + "" + saleInoviceData.getSiData().getCessTotalTaxAmt());

        }

        if (saleInoviceData.getTotalIncludingTax() != null)
            tvTotalAmtIncGst.setText("" + saleInoviceData.getTotalIncludingTax());

        if (saleInoviceData.getDate() != null)
            tvPrintDate.setText(Constant.POS_PRINTDATE + " " + saleInoviceData.getDate());

        sharedPreference = SharedPreference.getInstance(activity);
        String userName = sharedPreference.getData(Constant.USERNAME);

        if (userName != null) {
            tvSalesPerson.setText(Constant.POS_SALESPERSON + " " + userName);

        }
        if (Activity_SalesCheckout.BLUETOOTH_PRINTER.IsNoConnection()) {

        } else {
            Activity_SalesCheckout.BLUETOOTH_PRINTER.Begin();
            llContent.setDrawingCacheEnabled(true);
            llContent.buildDrawingCache();
            llContent.getDrawingCache();
            final Bitmap bitmap = Bitmap.createBitmap(llContent.getWidth(), llContent.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            llContent.draw(canvas);

            if (bitmap != null) {
                try {
                    Activity_SalesCheckout.BLUETOOTH_PRINTER.printImage(bitmap);


                } catch (Exception e) {
                    Toast.makeText(activity, "Exception " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }


        }


    }

    public String getPrinterStatus() {
        return printerStatus;
    }

    public void setPrinterStatus(String printerStatus) {
        this.printerStatus = printerStatus;
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
    public void onStart() {
        super.onStart();
        Log.e(TAG, "++ ON START ++");

        // If BT is not on, request that to be enabled.
        // initializeBluetoothDevice() will then be called during onActivityResult

     /*   if (!mBluetoothAdapter.isEnabled()) {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
            // Otherwise, setup the chat session
        } else {
            if (BLUETOOTH_PRINTER == null){
                initializeBluetoothDevice();
            }else{
                if(BLUETOOTH_PRINTER.IsNoConnection()){
                    tvPrinterStatus.setText("Printer is offline");
                }else{
                    tvPrinterStatus.setText(R.string.title_connected_to);
                    tvPrinterStatus.append(device.getName());

                }
            }

        }*/
    }

    Intent serverIntent = null;

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

            case 101:

                Intent intent = new Intent(this, NavigationDrawer_Activity.class);
                intent.putExtra(Constant.NAVIGATION_GROUPHEADER, Constant.NAVIGATION_GROUP_RETAIL);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void initializeBluetoothDevice() {
        Log.d(TAG, "setupChat()");
        // Initialize HsBluetoothPrintDriver class to perform bluetooth connections
        BLUETOOTH_PRINTER = HsBluetoothPrintDriver.getInstance();//
        BLUETOOTH_PRINTER.setHandler(new BluetoothHandler(Activity_SalesCheckout.this));
    }

    /**
     * The Handler that gets information back from Bluetooth Devices
     */
    static class BluetoothHandler extends Handler {
        private final WeakReference<Activity_SalesCheckout> myWeakReference;

        //Creating weak reference of BluetoothPrinterActivity class to avoid any leak
        BluetoothHandler(Activity_SalesCheckout weakReference) {
            myWeakReference = new WeakReference<Activity_SalesCheckout>(weakReference);
        }

        @Override
        public void handleMessage(Message msg) {
            Activity_SalesCheckout bluetoothPrinterActivity = myWeakReference.get();
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


}
