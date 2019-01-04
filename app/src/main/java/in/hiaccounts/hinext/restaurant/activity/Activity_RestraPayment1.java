package in.hiaccounts.hinext.restaurant.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mocoo.hang.rtprinter.driver.Contants;
import com.mocoo.hang.rtprinter.driver.HsBluetoothPrintDriver;
import com.nikoyuwono.realmbrowser.RealmBrowser;
import com.rey.material.widget.ProgressView;
import com.yinglan.keyboard.HideUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.application.activity.NavigationDrawer_Activity;
import in.hiaccounts.hinext.application.realm_manager.RealmManager;
import in.hiaccounts.hinext.checkout.model.BankAcount;
import in.hiaccounts.hinext.checkout.model.BankPayment;
import in.hiaccounts.hinext.checkout.model.CardPaymentList;
import in.hiaccounts.hinext.checkout.model.CashPayment;
import in.hiaccounts.hinext.checkout.model.CreditPayment;
import in.hiaccounts.hinext.checkout.model.MultiBankPaymentList;
import in.hiaccounts.hinext.checkout.model.MultiCashPaymentList;
import in.hiaccounts.hinext.checkout.model.MultiVoucherPayment;
import in.hiaccounts.hinext.checkout.model.PaymentType;
import in.hiaccounts.hinext.checkout.model.VoucherPayment;
import in.hiaccounts.hinext.controlpanel.model.company_setup.compnayinformation.CompanyData;
import in.hiaccounts.hinext.restaurant.adapter.BankPayment_Adapter;
import in.hiaccounts.hinext.restaurant.adapter.CardPayment_Adapter;
import in.hiaccounts.hinext.restaurant.adapter.PaymentType_Adapter;
import in.hiaccounts.hinext.restaurant.adapter.VoucherPayment_Adapter;
import in.hiaccounts.hinext.restaurant.model.checkout.RestraCheckoutData;
import in.hiaccounts.hinext.restaurant.model.checkout.RestraCheckoutItem;
import in.hiaccounts.hinext.restaurant.model.checkout.RestraSaveCheckoutData;
import in.hiaccounts.hinext.restaurant.model.restaurant_pagedata.HinextRestuarantPageData;
import in.hiaccounts.hinext.restaurant.model.restaurant_pagedata.SubRow;
import in.hiaccounts.hinext.sales.pos_printer.DeviceListActivity;
import in.hiaccounts.hinext.sales.pos_printer.PrintReceipt;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.AsyncTaskCompleteListener1;
import in.hiaccounts.task.GetCompanyLogoTask;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.NonScrollListView;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 8/5/2017.
 */

public class Activity_RestraPayment1 extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edTotalAmount)
    EditText edTotalamount;
    @BindView(R.id.edDiscount)
    EditText edTotaldiscount;
    @BindView(R.id.edAmtTendered)
    EditText edTenderedamt;
    @BindView(R.id.edAmountReturn)
    EditText edReturnamount;
    @BindView(R.id.edAmtCash)
    EditText edAmtCash;
    @BindView(R.id.btn_saveemail)
    Button btnSaveemail;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;

    Intent serverIntent = null;
    @BindView(R.id.btn_save)
    Button btnSave;
    @BindView(R.id.tvOne)
    TextView tvOne;
    @BindView(R.id.tvTwo)
    TextView tvTwo;
    @BindView(R.id.tvFive)
    TextView tvFive;
    @BindView(R.id.tvTen)
    TextView tvTen;
    @BindView(R.id.tvTweenty)
    TextView tvTweenty;
    @BindView(R.id.tvFifty)
    TextView tvFifty;
    @BindView(R.id.tvHundred)
    TextView tvHundred;
    @BindView(R.id.tvTwoHundred)
    TextView tvTwoHundred;
    @BindView(R.id.tvFiveHundred)
    TextView tvFiveHundred;
    @BindView(R.id.tvTwoThoushand)
    TextView tvTwoThoushand;
    @BindView(R.id.tvClearAmt)
    TextView tvClearAmt;
    @BindView(R.id.chbx_cash)
    CheckBox chbxCash;
    @BindView(R.id.chbx_card)
    CheckBox chbxCard;
    @BindView(R.id.chbx_voucher)
    CheckBox chbxVoucher;
    @BindView(R.id.recycleViewPaymentType)
    RecyclerView recycleViewPaymentType;
    @BindView(R.id.tv_cashicon)
    TextView tvCashicon;
    @BindView(R.id.ed_cashamounttendered)
    EditText edCashamounttendered;
    @BindView(R.id.ll_cashlayuout)
    LinearLayout llCashlayuout;
    @BindView(R.id.tv_cardicon)
    TextView tvCardicon;
    @BindView(R.id.nonscrolllistview_credit)
    NonScrollListView nonscrolllistviewCredit;
    @BindView(R.id.ll_cardlayuout)
    LinearLayout llCardlayuout;
    @BindView(R.id.tv_bankicon)
    TextView tvBankicon;
    @BindView(R.id.nonscrolllistview_bank)
    NonScrollListView nonscrolllistviewBank;
    @BindView(R.id.ll_banklayuout)
    LinearLayout llBanklayuout;
    @BindView(R.id.tv_voicon)
    TextView tvVoicon;
    @BindView(R.id.nonscrolllistview_voucher)
    NonScrollListView nonscrolllistviewVoucher;
    @BindView(R.id.ll_vopucherlayuout)
    LinearLayout llVopucherlayuout;
    @BindView(R.id.btn_print)
    Button btnPrint;
    @BindView(R.id.id_FreeMeal)
    CheckBox idFreeMeal;
    private Typeface font;
    Intent in;
    private RealmBrowser realmBrowser;
    private boolean mFlag = true;
    private AlertDialog.Builder alertDlgBuilder;
    private static BluetoothDevice device;
    private static final int REQUEST_CONNECT_DEVICE = 1;
    private static final int REQUEST_ENABLE_BT = 2;
    private BluetoothAdapter mBluetoothAdapter = null;
    public static HsBluetoothPrintDriver BLUETOOTH_PRINTER = null;
    public static final String TAG = Activity_RestraPayment1.class.getSimpleName();
    private static TextView tvPrinterStatus = null;
    double discountAmt;
    double tenderedAmt;
    double returnAmount;
    boolean isCheckoutable = true;
    RestraCheckoutData getCheckout;
    private int currentAPIVersion = Build.VERSION.SDK_INT;
    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 123;
    RestraSaveCheckoutData saveResponseData = null;
    Activity activity;
    private ServiceHandler serviceHandler;
    Boolean isInternetPresent = false;
    private String serverUrl, checkoutType = "", transactionNo, printerType = "", selectedDate = "";
    public static RestraSaveCheckoutData saveStarResponse = null;
    double cashAmt = 0.00, cardAmountPay = 0.00;
    private Context context;
    private SharedPreference sharedPreference;
    private ProgressDialog mProgressDialog;
    private String discountAmtPercentage;
    private String paidAmount;
    private CardPayment_Adapter cardPaymentAdapter;
    private VoucherPayment_Adapter voucherPaymentAdapter;
    private BankPayment_Adapter bankPaymentAdapter;
    Map<String, List<CardPaymentList>> mapCard = new HashMap<String, List<CardPaymentList>>();
    private List<CardPaymentList> cardPaymentList = new ArrayList<CardPaymentList>();
    private List<MultiVoucherPayment> voucherPaymentList = new ArrayList<MultiVoucherPayment>();
    private List<MultiBankPaymentList> bankPaymentList = new ArrayList<MultiBankPaymentList>();
    final List<BankAcount> bankAccountList = new ArrayList<>();
    CashPayment cashPayment = new CashPayment();
    PaymentType_Adapter paymentTypeAdapter;
    private static MenuItem printDisConnect;
    private static MenuItem printConnect;
    private String address;
    private CompanyData companyData;
    private Boolean splitBillStatus = false;
    private String payType = "", cashData = "", cardData = "", bankData = "", voucherData = "", cashString = "", cardstring = "", bankString = "", voucherString = "";
    List<Double> cardListData = new ArrayList<>();
    private Long cardPaymentId;
    double totalTaxAmt = 0.00;
    private HinextRestuarantPageData pageData;
    private boolean isNotification=false;
    private GetDataTask getDataTask;
    private PostDataTask postDataTask;


    @Override
    protected void onResume() {
        super.onResume();
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        UtilView.showLogCat("@Flow", "onResume");
        realmBrowser = new RealmBrowser();
        realmBrowser.start();
        realmBrowser.showServerAddress(activity);
        if (BLUETOOTH_PRINTER != null) {
            if (!BLUETOOTH_PRINTER.IsNoConnection()) {
                if (device != null) {
                    tvPrinterStatus.append(device.getName());
                    printConnect.setVisible(false);
                    printDisConnect.setVisible(false);
                    printConnect.setTitle(device.getName());
                }

            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        UtilView.showLogCat(TAG, "onStop");
       /* if (realmBrowser != null) {
            realmBrowser.stop();
        }*/

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  activity = this;
        setContentView(R.layout.activity_restracheckout);
        ButterKnife.bind(this);
        initComponentView();
        setonClcik();
    }

    private void setonClcik() {
        nonscrolllistviewCredit.setOnItemClickListener(this);
        nonscrolllistviewVoucher.setOnItemClickListener(this);
        nonscrolllistviewBank.setOnItemClickListener(this);
    }

    private void initComponentView() {
        ButterKnife.bind(this);
        activity = this;
        context = this;
        serviceHandler = new ServiceHandler(activity);
        sharedPreference = SharedPreference.getInstance(activity);
        serverUrl = UtilView.getUrl(activity);
       /* this.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);*/
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
        RealmManager.open();

        UtilView.showLogCat("@Flow", "relam " + RealmManager.open().getPath());
        getCheckout = (RestraCheckoutData) getIntent().getSerializableExtra("checkoutData");
        splitBillStatus = getIntent().getBooleanExtra("splitBillStatusData", false);
        UtilView.showLogCat(TAG, "CheckoutData " + new Gson().toJson(getCheckout));
        checkoutType = getIntent().getStringExtra("checkoutType");
        toolbar.setTitle("Payment");
        Log.e("splitBillStatuspayment", String.valueOf(splitBillStatus));
        Log.e("Connected12", printerType);

        tvPrinterStatus = (TextView) findViewById(R.id.tvPrinterStatus);

        printerSetup();
        invalidateOptionsMenu();

        //selectedDate = UtilView.setCurrentDate(edCardTransDate);

        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        font = Typeface.createFromAsset(getAssets(), "fonts/fontawesome-webfont.ttf");
        tvCashicon.setTypeface(font);
        tvCardicon.setTypeface(font);
        tvVoicon.setTypeface(font);
        tvBankicon.setTypeface(font);


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
                    tvOne.setClickable(true);
                    tvTwo.setClickable(true);
                    tvFive.setClickable(true);
                    tvTen.setClickable(true);
                    tvTweenty.setClickable(true);
                    tvFifty.setClickable(true);
                    tvHundred.setClickable(true);
                    tvTwoHundred.setClickable(true);
                    tvFiveHundred.setClickable(true);
                    tvTwoThoushand.setClickable(true);
                    edCashamounttendered.setError(null);
                } else {
                    try {
                        tvOne.setClickable(true);
                        tvTwo.setClickable(true);
                        tvFive.setClickable(true);
                        tvTen.setClickable(true);
                        tvTweenty.setClickable(true);
                        tvFifty.setClickable(true);
                        tvHundred.setClickable(true);
                        tvTwoHundred.setClickable(true);
                        tvFiveHundred.setClickable(true);
                        tvTwoThoushand.setClickable(true);
                        edCashamounttendered.setError(null);
                        setTenderedAmount();
                        // setReturnAmount();
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
            boolean mToggle = false;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // hideTheKeyboard(context, edTotaldiscount);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.equals("")) {
                    edTotaldiscount.setText("");
                    //callTotalAmount();

                    Log.e("edTotaldiscount1", s.toString());
                } else {


                    try {

                        //  Double txtDiscountamt = Double.parseDouble(edTotaldiscount.getText().toString());


                        setTenderedAmount();


                        //setReturnAmount();
                        //updateCashAmount(0.0);
                    } catch (NumberFormatException e) {
                        //edTotaldiscount.setError(getResources().getString(R.string.error_numberformate));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (isInternetPresent) {

            getPaymentTypeDataFromServer();

        } else {
            UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
        }
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        callTotalAmount();

    }

    private void callTotalAmount() {
        if (getCheckout != null) {
           /* BigDecimal  bd1 = new BigDecimal( getCheckout.getTotalCheckOutamt()-getCheckout.getTotalTaxAmt());
            bd1 = bd1.setScale(2,RoundingMode.HALF_UP);*/
            getCheckout.setTotalCheckOutamt(Double.parseDouble(String.format("%.2f", getCheckout.getTotalCheckOutamt())));
            Log.e("Amounttopay1", String.valueOf(getCheckout.getTotalCheckOutamt()));

            edTotalamount.setText("" + getCheckout.getTotalCheckOutamt());
            BigDecimal bd = new BigDecimal(getCheckout.getTotalTaxAmt());
            bd = bd.setScale(2, RoundingMode.HALF_UP);
            getCheckout.setTotalTaxAmt(bd.doubleValue());
            Log.e("AmounttopayTax", String.valueOf(getCheckout.getTotalTaxAmt()));
            DecimalFormat df2 = new DecimalFormat(".##");
            getCheckout.setTotalCheckOutamt(Double.parseDouble(df2.format(getCheckout.getTotalCheckOutamt() + getCheckout.getTotalTaxAmt())));
            Log.e("totalAmount", String.valueOf(getCheckout.getTotalCheckOutamt()));
            edAmtCash.setText("" + getCheckout.getTotalCheckOutamt());


        }
    }


    private void printerSetup() {


        tvPrinterStatus.setVisibility(View.GONE);
        // Get device's Bluetooth adapter
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        // If the adapter is null, then Bluetooth is not available in your device
        if (mBluetoothAdapter == null) {
            UtilView.showToast(activity, "Bluetooth is not available");
        }
        invalidateOptionsMenu();
        tvPrinterStatus.setVisibility(View.GONE);
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
                        printConnect.setVisible(false);
                        printDisConnect.setVisible(false);
                    } else {
                        tvPrinterStatus.setText(R.string.title_connected_to);

                        if (device != null) {

                            // Get the BLuetoothDevice object
                            // Attempt to connect to the device
                            //BLUETOOTH_PRINTER.start();
                            // BLUETOOTH_PRINTER.connect(device);
                            //device = mBluetoothAdapter.getRemoteDevice(address);
                            tvPrinterStatus.append(device.getName());
                            printConnect.setVisible(false);
                            printDisConnect.setVisible(false);
                            printConnect.setTitle(device.getName());
                        }


                    }
                }

            }
        } else {
            tvPrinterStatus.setText(R.string.bluettoth_doesnt);
        }
    }

    private void getPaymentTypeDataFromServer() {

        String url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETPAYMENTTYPE;
        UtilView.showProgessBar(activity, progressBar);
        getDataTask = new GetDataTask(activity, new AsyncTaskCompleteListener<String>() {
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
                            paymentTypeList.remove(0);
                            paymentTypeAdapter = new PaymentType_Adapter(activity, paymentTypeList, new PaymentType_Adapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(PaymentType paymentType) {
                                    if (paymentType.getPaymentmethodId() != null) {

                                        if (paymentType.getPaymentmethodType() != null && paymentType.getPaymentmethodType().equals("Cash")) {
                                            //cashData = paymentType.getPaymentmethodName();
                                            Log.e("cashPauData", paymentType.getPaymentmethodName());
                                            if (paymentType.isChecked()) {
                                                llCashlayuout.setVisibility(View.VISIBLE);
                                                cashPayment.setPaymentTypeId(paymentType.getPaymentmethodId());
                                                edCashamounttendered.setText("" + edCashamounttendered.getText().toString());
                                            } else {
                                                llCashlayuout.setVisibility(View.VISIBLE);
                                                edCashamounttendered.setText("0");
                                            }
                                        }
                                        if (paymentType.getPaymentmethodType() != null && paymentType.getPaymentmethodType().equals("Bank")) {
                                            if (paymentType.isChecked()) {

                                                // bankPaymentList.clear();
                                                tvOne.setClickable(false);
                                                tvTwo.setClickable(false);
                                                tvFive.setClickable(false);
                                                tvTen.setClickable(false);
                                                tvTweenty.setClickable(false);
                                                tvFifty.setClickable(false);
                                                tvHundred.setClickable(false);
                                                tvTwoHundred.setClickable(false);
                                                tvFiveHundred.setClickable(false);
                                                tvTwoThoushand.setClickable(false);
                                                llBanklayuout.setVisibility(View.VISIBLE);

                                                bankString = "Bank";
                                                bankData = paymentType.getPaymentmethodName();
                                                MultiBankPaymentList multiBankPaymentList = new MultiBankPaymentList();
                                                multiBankPaymentList.setAmount(0);
                                                multiBankPaymentList.setPaymentType(paymentType.getPaymentmethodId());
                                                getBankAccountList(paymentType,multiBankPaymentList);






                                            } else {

                                                Iterator<MultiBankPaymentList> i = bankPaymentList.iterator();
                                                while (i.hasNext()) {
                                                    MultiBankPaymentList s = i.next();
                                                    if (bankString != null && paymentType.getPaymentmethodId().equals(s.getPaymentType())) {
                                                        i.remove();
                                                        setTenderedAmount();
                                                    }
                                                }
                                                // bankPaymentList.clear();
                                              /*  MultiBankPaymentList multiBankPaymentList = new MultiBankPaymentList();
                                                multiBankPaymentList.setAmount(0);
                                                multiBankPaymentList.setPaymentType(paymentType.getPaymentmethodId());
                                                bankPaymentList.add(multiBankPaymentList);
                                                setTenderedAmount();*/
                                                tvOne.setClickable(true);
                                                tvTwo.setClickable(true);
                                                tvFive.setClickable(true);
                                                tvTen.setClickable(true);
                                                tvTweenty.setClickable(true);
                                                tvFifty.setClickable(true);
                                                tvHundred.setClickable(true);
                                                tvTwoHundred.setClickable(true);
                                                tvFiveHundred.setClickable(true);
                                                tvTwoThoushand.setClickable(true);
                                                bankData = "";
                                                bankString = "Bank";
                                                llBanklayuout.setVisibility(View.VISIBLE);
                                                llCashlayuout.setVisibility(View.VISIBLE);
                                                bankPaymentAdapter = new BankPayment_Adapter(activity, bankPaymentList);
                                                nonscrolllistviewBank.setAdapter(bankPaymentAdapter);
                                                bankPaymentAdapter.notifyDataSetChanged();

                                                if (bankPaymentList.size() == 0) {
                                                    llBanklayuout.setVisibility(View.GONE);
                                                    bankString = "";
                                                }
                                            }
                                        }
                                        if (paymentType.getPaymentmethodType() != null && paymentType.getPaymentmethodType().equals("Card")) {

                                            if (paymentType.isChecked()) {
                                                //  Toast.makeText(activity, "Card " + paymentType.getPaymentmethodName(), Toast.LENGTH_SHORT).show();

                                                try {

                                                    //cardPaymentList.clear();
                                                    tvOne.setClickable(false);
                                                    tvTwo.setClickable(false);
                                                    tvFive.setClickable(false);
                                                    tvTen.setClickable(false);
                                                    tvTweenty.setClickable(false);
                                                    tvFifty.setClickable(false);
                                                    tvHundred.setClickable(false);
                                                    tvTwoHundred.setClickable(false);
                                                    tvFiveHundred.setClickable(false);
                                                    tvTwoThoushand.setClickable(false);
                                                    cardPaymentId = paymentType.getPaymentmethodId();
                                                    payType = "Card";
                                                    cardData = paymentType.getPaymentmethodName();
                                                    cardstring = "Card";
                                                    llCardlayuout.setVisibility(View.VISIBLE);
                                                    CardPaymentList cardPayment = new CardPaymentList();
                                                    cardPayment.setCardAmt(0);
                                                    cardPayment.setCardAmount(0);
                                                    cardPayment.setPaymentType(paymentType.getPaymentmethodId());
                                                    cardPayment.setType(cardData);
                                                    cardPaymentList.add(cardPayment);
                                                    cardPaymentAdapter = new CardPayment_Adapter(activity, cardPaymentList);
                                                    nonscrolllistviewCredit.setAdapter(cardPaymentAdapter);
                                                    cardPaymentAdapter.notifyDataSetChanged();


                                                } catch (Exception e) {

                                                }


                                            } else {
                                                // Toast.makeText(activity, "UnCheckCard ", Toast.LENGTH_SHORT).show();

                                                //Log.e("StringId", String.valueOf(paymentType.getPaymentmethodId()));
                                                Iterator<CardPaymentList> i = cardPaymentList.iterator();
                                                while (i.hasNext()) {
                                                    CardPaymentList s = i.next();
                                                    // Log.e("StringListId", String.valueOf(s.getPaymentType()));
                                                    if (cardData != null && paymentType.getPaymentmethodId().equals(s.getPaymentType())) {
                                                        i.remove();
                                                        setTenderedAmount();
                                                        //llCardlayuout.setVisibility(View.VISIBLE);
                                                    }
                                                }
                                                //cardPaymentList.clear();
                                                tvOne.setClickable(true);
                                                tvTwo.setClickable(true);
                                                tvFive.setClickable(true);
                                                tvTen.setClickable(true);
                                                tvTweenty.setClickable(true);
                                                tvFifty.setClickable(true);
                                                tvHundred.setClickable(true);
                                                tvTwoHundred.setClickable(true);
                                                tvFiveHundred.setClickable(true);
                                                tvTwoThoushand.setClickable(true);
                                                cardData = "";
                                                cardstring = "Card";
                                                llCardlayuout.setVisibility(View.VISIBLE);
                                                llCashlayuout.setVisibility(View.VISIBLE);
                                                cardPaymentAdapter = new CardPayment_Adapter(activity, cardPaymentList);
                                                nonscrolllistviewCredit.setAdapter(cardPaymentAdapter);
                                                cardPaymentAdapter.notifyDataSetChanged();

                                                if (cardPaymentList.size() == 0) {
                                                    llCardlayuout.setVisibility(View.GONE);
                                                    cardstring = "";
                                                }


                                            }
                                        }
                                        if (paymentType.getPaymentmethodType() != null && paymentType.getPaymentmethodType().equals("Voucher")) {
                                            if (paymentType.isChecked()) {
                                                //voucherPaymentList.clear();
                                                payType = "Voucher";
                                                voucherString = "Voucher";
                                                tvOne.setClickable(false);
                                                tvTwo.setClickable(false);
                                                tvFive.setClickable(false);
                                                tvTen.setClickable(false);
                                                tvTweenty.setClickable(false);
                                                tvFifty.setClickable(false);
                                                tvHundred.setClickable(false);
                                                tvTwoHundred.setClickable(false);
                                                tvFiveHundred.setClickable(false);
                                                tvTwoThoushand.setClickable(false);
                                                llVopucherlayuout.setVisibility(View.VISIBLE);
                                                voucherData = paymentType.getPaymentmethodName();
                                                MultiVoucherPayment voucherPayment = new MultiVoucherPayment();
                                                voucherPayment.setPaymentType(paymentType.getPaymentmethodId());
                                                voucherPaymentList.add(voucherPayment);
                                                voucherPaymentAdapter = new VoucherPayment_Adapter(activity, voucherPaymentList);
                                                nonscrolllistviewVoucher.setAdapter(voucherPaymentAdapter);
                                                voucherPaymentAdapter.notifyDataSetChanged();
                                            } else {
                                               /* voucherPaymentList.clear();
                                                MultiVoucherPayment multiVoucherPayment = new MultiVoucherPayment();
                                                multiVoucherPayment.setVoucherNo("");
                                                multiVoucherPayment.setAmt(0);
                                                multiVoucherPayment.setPaymentType(paymentType.getPaymentmethodId());
                                                voucherPaymentList.add(voucherPaymentList.size(), multiVoucherPayment);
                                                setTenderedAmount();*/
                                                Iterator<MultiVoucherPayment> i = voucherPaymentList.iterator();
                                                while (i.hasNext()) {
                                                    MultiVoucherPayment s = i.next();
                                                    // Log.e("StringListId", String.valueOf(s.getPaymentType()));
                                                    if (cardData != null && paymentType.getPaymentmethodId().equals(s.getPaymentType())) {
                                                        i.remove();
                                                        setTenderedAmount();
                                                    }
                                                }
                                                tvOne.setClickable(true);
                                                tvTwo.setClickable(true);
                                                tvFive.setClickable(true);
                                                tvTen.setClickable(true);
                                                tvTweenty.setClickable(true);
                                                tvFifty.setClickable(true);
                                                tvHundred.setClickable(true);
                                                tvTwoHundred.setClickable(true);
                                                tvFiveHundred.setClickable(true);
                                                tvTwoThoushand.setClickable(true);
                                                voucherData = "";
                                                voucherString = "Voucher";
                                                llVopucherlayuout.setVisibility(View.VISIBLE);
                                                llCashlayuout.setVisibility(View.VISIBLE);
                                                voucherPaymentAdapter = new VoucherPayment_Adapter(activity, voucherPaymentList);
                                                nonscrolllistviewVoucher.setAdapter(voucherPaymentAdapter);
                                                voucherPaymentAdapter.notifyDataSetChanged();

                                                if (voucherPaymentList.size() == 0) {
                                                    llVopucherlayuout.setVisibility(View.GONE);
                                                    voucherString = "";
                                                }
                                            }
                                        }


                                    }
                                }
                            });
                            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(activity, 3);
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
        getDataTask.execute(url, "");
    }

    private void getBankAccountList(PaymentType paymentType, MultiBankPaymentList multiBankPaymentList) {
        UtilView.showProgessBar(activity, progressBar);

        final String url = serverUrl + "/hipos/0/" + Constant.FUNTION_GETACCOUNTDETAIL + "?accountCode="+paymentType.getAccountMasterId();
        getDataTask = new GetDataTask(activity, new AsyncTaskCompleteListener<String>() {
            @Override
            public void onTaskComplete(String result) {
                UtilView.hideProgessBar(activity, progressBar);
                HideUtil.init(activity);
                if (result != null) {
                    if (result.equals("invalid")) {
                        UtilView.showToast(activity, activity.getResources().getString(R.string.accesstoken_error));
                        Intent intent = new Intent(activity, Activity_Login.class);
                        activity.startActivity(intent);
                        activity.finish();
                    } else {
                        try {
                            bankAccountList.clear();
                            JSONArray jsonArray = new JSONArray(result.toString());


                            Gson gson = new Gson();
                            if (jsonArray != null && jsonArray.length() > 0) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    BankAcount bankAcount = gson.fromJson(jsonObject.toString(), BankAcount.class);
                                    bankAccountList.add(bankAcount);
                                }
                            }
                            if (bankAccountList != null && bankAccountList.size() > 0) {
                                multiBankPaymentList.setBankAccount(bankAccountList.get(0).getAccountName());
                                multiBankPaymentList.setBankAccountId(bankAccountList.get(0).getAccountid());
                                multiBankPaymentList.setBankName("");
                                multiBankPaymentList.setDate("");
                                multiBankPaymentList.setTransactionNo("");
                                bankPaymentList.add(multiBankPaymentList);
                                bankPaymentAdapter = new BankPayment_Adapter(activity, bankPaymentList);
                                nonscrolllistviewBank.setAdapter(bankPaymentAdapter);
                                bankPaymentAdapter.notifyDataSetChanged();

                            }

                        } catch (Exception e) {
                            UtilView.showErrorDialog(activity.getResources().getString(R.string.error_null), activity);
                        }
                    }
                } else {
                    UtilView.showErrorDialog(activity.getResources().getString(R.string.error_null), activity);
                }
            }
        }, false);

        getDataTask.execute(url, "");

    }

    private void updateCashAmount(double amountCollect) {
        double totalTaxAmt = 0.00,tenderedAmt=0.00;

        if (amountCollect != 0.00) {
            cashAmt = cashAmt + amountCollect;
            edAmtCash.setText("" + String.format("%.2f", cashAmt));


            tenderedAmt = (cashAmt);
            edTenderedamt.setText(String.format("%.2f", tenderedAmt));
            getCheckout.setTotalTenderedAmount(tenderedAmt);

            edCashamounttendered.setText("" + tenderedAmt);
            String txtTotalamt = edTotalamount.getText().toString();
            String txtDiscountamt = edTotaldiscount.getText().toString();

            try {


                double totalAmt = 0.00, d = 0.00;
                if (txtTotalamt.equals("")) {
                    totalAmt = 0.00;
                } else {
                    totalAmt = Double.parseDouble(txtTotalamt);
                    Log.e("totalAmt2 ", String.valueOf(totalAmt));
                }
                BigDecimal bd;
                double discountAmt = 0.00;
                BigDecimal bdtotalAmt = null;
                if (txtDiscountamt != null && !txtDiscountamt.equals("")) {
                    discountAmt = Double.valueOf(txtDiscountamt);
                    getCheckout.setDiscountAmtInPercentage(discountAmt);
                    getCheckout.setDiscountAmount(Double.valueOf(String.valueOf(new BigDecimal((((totalAmt) * discountAmt) / 100)).setScale(2, RoundingMode.HALF_UP))));
                    totalTaxAmt = getCheckout.getTotalTaxAmt() - ((getCheckout.getTotalTaxAmt() * Double.parseDouble(txtDiscountamt)) / 100);
                    totalAmt = (totalAmt - (((totalAmt * Double.parseDouble(txtDiscountamt)) / 100))) + totalTaxAmt;
                    if (totalAmt != 0.00) {
                        bdtotalAmt = new BigDecimal(totalAmt);
                        bdtotalAmt = bdtotalAmt.setScale(2, RoundingMode.HALF_UP);
                        if (bdtotalAmt != null) {
                            d = bdtotalAmt.doubleValue();
                        }

                    }
                } else {

                    getCheckout.setDiscountAmount(0.00);
                    getCheckout.setDiscountAmtInPercentage(0.00);
                    totalTaxAmt = getCheckout.getTotalTaxAmt();
                    d = totalAmt + totalTaxAmt;

                }

                getCheckout.setTotalTenderedAmount(tenderedAmt);


                double number = d;
                int decimal = (int) number;
                double fractional = number - decimal;
                String totalAmounToBePay = "";
                if (fractional < 0.50) {
                    totalAmounToBePay = String.format("%.2f", Math.floor(d));
                    getCheckout.setTotalCheckOutamt(Double.parseDouble(totalAmounToBePay));
                    bd = new BigDecimal(d - Math.floor(d));
                    bd = bd.setScale(2, RoundingMode.HALF_UP);
                    getCheckout.setRoundingOffValue(String.valueOf(bd));
                } else {
                    totalAmounToBePay = String.format("%.2f", Math.ceil(d));
                    getCheckout.setTotalCheckOutamt(Double.parseDouble(totalAmounToBePay));
                    bd = new BigDecimal(d - Math.ceil(d));
                    bd = bd.setScale(2, RoundingMode.HALF_DOWN);
                    getCheckout.setRoundingOffValue(String.valueOf(bd));
                    // getCheckout.setTotalTaxAmt(totalTaxAmt);
                }
                edAmtCash.setText(totalAmounToBePay);
                paidAmount = String.format("%.2f", getCheckout.getTotalCheckOutamt());
                // getCheckout.setTotalTaxAmt(totalTaxAmt);

                if (tenderedAmt != 0.00) {
                    double returnAmt = tenderedAmt - getCheckout.getTotalCheckOutamt();

                    edReturnamount.setText("" + new BigDecimal(returnAmt).setScale(2, BigDecimal.ROUND_HALF_UP));
                    getCheckout.setTotalRemaininBalance(returnAmt);
                } else {
                    edReturnamount.setText("0.00");
                    edTenderedamt.setText("0.00");
                    edCashamounttendered.setText("");
                }
                // double returnAmt = (tenderedAmt + discountAmt) - totalAmt;

            } catch (NumberFormatException e) {
                edTotalamount.setError(getResources().getString(R.string.error_numberformate));
            }
        } else {
            cashAmt = 0.00;
            edTenderedamt.setText("0.00");
            edReturnamount.setText("0.00");
            edCashamounttendered.setText("0");

        }


    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void callServer() {
        final Gson gson = new Gson();
        if (getCheckout != null) {
            getCheckout.setPaymentType(checkoutType);
            String tableId = getCheckout.getTableVal();
            String tableName = getCheckout.getTableName();

            String waiterName = getCheckout.getWaiterName();
            getCheckout.setCutomerName(getCheckout.getCustomerName());
            //  getCheckout.setSupplierInvNo(edSupplierInvoiceNo.getText().toString().trim());
            String url = serverUrl + "/restaurant/save?tableNo=" + tableId +"&tableName="+ tableName+ "&waiterName=" + waiterName.replace(" ", "%20") + "&printVal=savePrint";
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (serverUrl != null) {
                if (isInternetPresent) {
                    UtilView.showProgessBar(activity, progressBar);
                     postDataTask = new PostDataTask(this, new AsyncTaskCompleteListener<String>() {
                        @Override
                        public void onTaskComplete(String result) {
                            UtilView.hideProgessBar(activity, progressBar);

                            HideUtil.init(Activity_RestraPayment1.this);
                            if (result != null) {
                                if (result.equals("invalid")) {
                                    UtilView.showToast(activity, getResources().getString(R.string.accesstoken_error));
                                    Intent intent = new Intent(activity, Activity_Login.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    try {

                                        JSONObject jsonObject = new JSONObject(result.toString());
                                        Log.e("Data", jsonObject.getJSONObject("Desktop").toString());
                                        saveResponseData = gson.fromJson(jsonObject.getJSONObject("Desktop").toString(), RestraSaveCheckoutData.class);


                                    } catch (JSONException ex) {
                                        UtilView.showErrorDialog(getResources().getString(R.string.response_error), activity);
                                    }

                                    if (saveResponseData != null) {
                                        callPrinservices(saveResponseData);

                                    }
                                }
                            }
                        }
                    }, false);
                    UtilView.showLogCat(TAG, "Post Data Value " + gson.toJson(getCheckout).toString());
                    getCheckout.setTableName(null);
                    getCheckout.setTableVal(null);
                    postDataTask.execute(gson.toJson(getCheckout).toString(), url, "");
                } else {
                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
                }
            } else {
                UtilView.gotToLogin(activity);
            }
        }
    }

    public void callServer1() {
        final Gson gson = new Gson();
        if (getCheckout != null) {
            getCheckout.setPaymentType(checkoutType);

            getCheckout.setCutomerName(getCheckout.getCustomerName());
            String tableId = getCheckout.getTableVal();
            String tableName = getCheckout.getTableName();
            String waiterName = getCheckout.getWaiterName();

            //  getCheckout.setSupplierInvNo(edSupplierInvoiceNo.getText().toString().trim());
            String url = serverUrl + "/restaurant/save?tableNo=" + tableId +"&tableName="+ tableName+ "&waiterName=" + waiterName.replace(" ", "%20") + "&printVal=save";
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (serverUrl != null) {
                if (isInternetPresent) {
                    UtilView.showProgessBar(activity, progressBar);
                     postDataTask = new PostDataTask(this, new AsyncTaskCompleteListener<String>() {
                        @Override
                        public void onTaskComplete(String result) {
                            UtilView.hideProgessBar(activity, progressBar);

                            HideUtil.init(Activity_RestraPayment1.this);
                            if (result != null) {
                                if (result.equals("invalid")) {
                                    UtilView.showToast(activity, getResources().getString(R.string.accesstoken_error));
                                    Intent intent = new Intent(activity, Activity_Login.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    try {
                                        JSONObject jsonObject = new JSONObject(result.toString());
                                        RestraSaveCheckoutData saveResponseData = gson.fromJson(jsonObject.getJSONObject("Desktop").toString(), RestraSaveCheckoutData.class);
                                        if (saveResponseData != null) {
                                            UtilView.showToast(Activity_RestraPayment1.this, "Successfully");
                                            Intent returnIntent = new Intent();
                                            returnIntent.putExtra("paidTableName", getCheckout);
                                            Log.e("splitBillStatuspayment2", String.valueOf(splitBillStatus));
                                            returnIntent.putExtra("splitBillPay", splitBillStatus);
                                            setResult(Activity.RESULT_OK, returnIntent);
                                            finish();
                                        } else {
                                            UtilView.showToast(Activity_RestraPayment1.this, "Some Error. Please try Again.");
                                        }
                                    } catch (JSONException ex) {
                                        UtilView.showErrorDialog(getResources().getString(R.string.response_error), activity);
                                    }
                                }
                            }
                        }
                    }, false);
                    UtilView.showLogCat(TAG, "Post Data Value " + gson.toJson(getCheckout).toString());
                    getCheckout.setTableName(null);
                    getCheckout.setTableVal(null);
                    postDataTask.execute(gson.toJson(getCheckout).toString(), url, "");
                } else {
                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
                }
            } else {
                UtilView.gotToLogin(activity);
            }
        }


    }

    public void callPrinservices(final RestraSaveCheckoutData saveResponseData) {

        if (saveResponseData != null) {

            SharedPreference sharedPreference = SharedPreference.getInstance(activity);
            if (sharedPreference != null) {

                String applicationDataJson = sharedPreference.getData(Constant.COMPANYDATA);
                String imageLogoUrl;

                if (applicationDataJson != null) {
                    Gson gson = new Gson();
                    String serverUrl = UtilView.getUrl(activity);

                    CompanyData companyData = gson.fromJson(applicationDataJson, CompanyData.class);
                    if (companyData != null) {
                        if (companyData.getFileName() != null) {
                            imageLogoUrl = serverUrl + companyData.getFileName();
                            GetCompanyLogoTask task = new GetCompanyLogoTask(activity, new AsyncTaskCompleteListener1<Bitmap>() {
                                @Override
                                public void onTaskComplete(Bitmap bmp) {
                                    makePrint(bmp, saveResponseData);
                                }
                            }, false);
                            task.execute(imageLogoUrl, "");

                        } else {
                            makePrint(null, saveResponseData);
                        }
                    } else {
                        makePrint(null, saveResponseData);
                    }
                } else {
                    makePrint(null, saveResponseData);
                }
            } else {
                makePrint(null, saveResponseData);
            }
            //     InputStream logoInputStream = PdfUtils.getLogoInputStream(activity, progressBar);


        }


    }

    private void makePrint(Bitmap bmp, RestraSaveCheckoutData saveResponseData) {
        boolean printStatus = false;
        UtilView.showToast(Activity_RestraPayment1.this, "Successfully");

      //  String tableName = getCheckout.getTableName();
        if (!printerType.equals("") && printerType.equals(getResources().getString(R.string.pos_3inch))) {
            printStatus = PrintReceipt.printInvoice3Inch(Activity_RestraPayment1.this, saveResponseData, bmp, paidAmount,isNotification);
        } else {
            printStatus = PrintReceipt.printInvoice2Inch(Activity_RestraPayment1.this, saveResponseData, bmp, paidAmount,isNotification);
        }
        if (printStatus) {
            //BLUETOOTH_PRINTER.stop();
            Intent returnIntent = new Intent();
            returnIntent.putExtra("paidTableName", getCheckout);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = activity.getMenuInflater();
        inflater.inflate(R.menu.menu_restapayment, menu);
        printDisConnect = menu.findItem(R.id.idDisconnect);
        printConnect = menu.findItem(R.id.idConnect);
        printConnect.setVisible(false);
        printDisConnect.setVisible(false);

        if (BLUETOOTH_PRINTER != null) {
            if (BLUETOOTH_PRINTER.IsNoConnection()) {
                printConnect.setVisible(false);
                printDisConnect.setVisible(false);

            }
            if (!BLUETOOTH_PRINTER.IsNoConnection()) {

                if (device != null) {
                    BLUETOOTH_PRINTER.start();
                    BLUETOOTH_PRINTER.connect(device);
                    printConnect.setVisible(false);
                    printDisConnect.setVisible(false);
                    printConnect.setTitle(device.getName());
                }

            }
        }


        return true;


    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG, "++ ON START ++");
        Log.e("onStart", "onStart");
        //in = new Intent();

        try {

            if (!BLUETOOTH_PRINTER.IsNoConnection()) {
                String billaddress = sharedPreference.getData(Constant.BLUETOOTHADDRESSBILL);
                Log.e("billaddress",billaddress);
                if (billaddress!=null && !billaddress.equals("")){

                    printerType = sharedPreference.getData(Constant.BLUETOOTHTYPEBILL);
                    callBillingPrint(billaddress);
                }else {
                    String address = sharedPreference.getData(Constant.BLUETOOTHADDRESS);
                    printerType = sharedPreference.getData(Constant.BLUETOOTHTYPE);
                    //Log.e("address",address);
                    // Log.e("printerType",printerType);

                    if (address != null && !address.equals("")) {
                        //address = in.getExtras().getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
                        // printerType = in.getExtras().getString(DeviceListActivity.EXTRA_PRINTER_TYPE);
                        device = mBluetoothAdapter.getRemoteDevice(address);


                        if (device != null) {
                            BLUETOOTH_PRINTER.start();
                            BLUETOOTH_PRINTER.connect(device);
                            tvPrinterStatus.append(device.getName());
                            printConnect.setVisible(false);
                            printDisConnect.setVisible(false);
                            printConnect.setTitle(device.getName());
                        }
                    }

                }






            }


        } catch (Exception e) {
            Log.e("Printer error", e.toString());
        }


        // If BT is not on, request that to be enabled.
        // initializeBluetoothDevice() will then be called during onActivityResult

  /*      if (!mBluetoothAdapter.isEnabled()) {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
            // Otherwise, setup the chat session
        } else {
            if (BLUETOOTH_PRINTER == null) {
                initializeBluetoothDevice();
            } else if (BLUETOOTH_PRINTER != null) {
                if (BLUETOOTH_PRINTER.IsNoConnection()) {
                    tvPrinterStatus.setText("Printer is offline");

                    if (printConnect != null) {
                        printConnect.setVisible(false);
                        printDisConnect.setVisible(true);
                    }

                } else if (!BLUETOOTH_PRINTER.IsNoConnection()) {

                   // BLUETOOTH_PRINTER.start();
                    //BLUETOOTH_PRINTER.connect(device);
                    if (device != null) {
                        tvPrinterStatus.append(device.getName());
                        printConnect.setVisible(true);
                        printDisConnect.setVisible(false);
                        printConnect.setTitle(device.getName());
                    }




                 *//*   Intent in = new Intent();
                    String address = in.getExtras().getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
                    printerType = in.getExtras().getString(DeviceListActivity.EXTRA_PRINTER_TYPE);
                    // Get the BLuetoothDevice object
                    device = mBluetoothAdapter.getRemoteDevice(address);
                    // Attempt to connect to the device
                    BLUETOOTH_PRINTER.start();
                    BLUETOOTH_PRINTER.connect(device);

                    tvPrinterStatus.setText(R.string.title_connected_to);
                    tvPrinterStatus.append(device.getName());
*//*
                }
            }

        }*/
    }

    private void callBillingPrint(String billaddress) {
        try {

            device = mBluetoothAdapter.getRemoteDevice(billaddress);
            // Attempt to connect to the device
            BLUETOOTH_PRINTER.start();
            BLUETOOTH_PRINTER.connect(device);
            if (BLUETOOTH_PRINTER != null) {
                if (!BLUETOOTH_PRINTER.IsNoConnection()) {
                    UtilView.showLogCat(TAG, "state " + BLUETOOTH_PRINTER.getState());

                    if (BLUETOOTH_PRINTER.getState() == 3) {
                        Log.e("bill connection", "connected");
                        Log.e("billaddress",billaddress);
                    }
                }
            }
        }catch (Exception e){
            Log.e("kotprinter excption",e.toString());
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        UtilView.showLogCat(TAG, "onPause");
   /*     try {


            String address = sharedPreference.getData(Constant.BLUETOOTHADDRESS);
            printerType = sharedPreference.getData(Constant.BLUETOOTHTYPE);
            //Log.e("address",address);
            // Log.e("printerType",printerType);

            if (address != null && !address.equals("")) {
                //address = in.getExtras().getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
                //printerType = in.getExtras().getString(DeviceListActivity.EXTRA_PRINTER_TYPE);
                device = mBluetoothAdapter.getRemoteDevice(address);


                if (device != null) {
                    BLUETOOTH_PRINTER.start();
                    BLUETOOTH_PRINTER.connect(device);
                    tvPrinterStatus.append(device.getName());
                    printConnect.setVisible(false);
                    printDisConnect.setVisible(false);
                    printConnect.setTitle(device.getName());
                }
            }
        } catch (Exception e) {
            Log.e("Printer error", e.toString());
        }*/
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {

            case R.id.idDisconnect:
                if (!mBluetoothAdapter.isEnabled()) {
                    Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
                } else {
                    //If the connection is lost with last connected bluetooth printer

                    if (BLUETOOTH_PRINTER.IsNoConnection()) {
                        serverIntent = new Intent(activity, DeviceListActivity.class);
                        startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);
                    } else { //If an existing connection is still alive then ask user to kill it and re-connect again
                        alertDlgBuilder = new AlertDialog.Builder(activity);
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

                                       // BLUETOOTH_PRINTER.stop();
                                        sharedPreference.getData(Constant.BLUETOOTHADDRESS);
                                        sharedPreference.getData(Constant.BLUETOOTHTYPE);
                                        serverIntent = new Intent(activity, DeviceListActivity.class);
                                        startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);
                                    }
                                }
                        );
                        alertDlgBuilder.show();

                    }
                }

                break;
            case R.id.idConnect:
                if (!mBluetoothAdapter.isEnabled()) {
                    Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
                } else {
                    //If the connection is lost with last connected bluetooth printer

                    if (BLUETOOTH_PRINTER.IsNoConnection()) {
                        serverIntent = new Intent(activity, DeviceListActivity.class);
                        startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);
                    } else { //If an existing connection is still alive then ask user to kill it and re-connect again
                        alertDlgBuilder = new AlertDialog.Builder(activity);
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
                                        //BLUETOOTH_PRINTER.stop();
                                        sharedPreference.setRemovePrefrence(Constant.BLUETOOTHADDRESS);
                                        sharedPreference.setRemovePrefrence(Constant.BLUETOOTHTYPE);
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
        Log.e("onDestroy", "onDestroy");
      /*  if (BLUETOOTH_PRINTER != null) {
            if (BLUETOOTH_PRINTER.IsNoConnection()) {
                BLUETOOTH_PRINTER.stop();
                printConnect.setVisible(false);
                printDisConnect.setVisible(true);
            }


        }*/
        if(getDataTask != null) {
            getDataTask.cancel(true);
        }

        if (postDataTask!=null) {
            postDataTask.cancel(true);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case REQUEST_CONNECT_DEVICE:
                // When DeviceListActivity returns with a device to connect
                if (resultCode == Activity.RESULT_OK) {


                    // Get the device MAC address
                    address = data.getExtras().getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
                    printerType = data.getExtras().getString(DeviceListActivity.EXTRA_PRINTER_TYPE);
                    Log.e("Connected", printerType);

                    sharedPreference.saveData(Constant.BLUETOOTHADDRESS, address);
                    sharedPreference.saveData(Constant.BLUETOOTHTYPE, printerType);
                    // Get the BLuetoothDevice object
                    device = mBluetoothAdapter.getRemoteDevice(address);
                    // Attempt to connect to the device

                    if (device != null) {
                        BLUETOOTH_PRINTER.start();
                        BLUETOOTH_PRINTER.connect(device);
                        printConnect.setVisible(false);
                        printDisConnect.setVisible(false);
                        printConnect.setTitle(device.getName());
                    }

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
                    //  finish();
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
        BLUETOOTH_PRINTER.setHandler(new BluetoothHandler(Activity_RestraPayment1.this));

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (view.getId()) {
            case R.id.imageview_addcard:

                try {


                    payType = "AddCard";
                    CardPaymentList cardPayment = new CardPaymentList();
                    cardPayment.setCardAmt(0);
                    cardPayment.setCardAmount(0);
                    cardPayment.setPaymentType(cardPaymentList.get(position).getPaymentType());
                    cardPayment.setType(cardData);
                    cardPaymentList.add(cardPaymentList.size(), cardPayment);
                    cardPaymentAdapter = new CardPayment_Adapter(activity, cardPaymentList);
                    nonscrolllistviewCredit.setAdapter(cardPaymentAdapter);
                    cardPaymentAdapter.notifyDataSetChanged();
                    setTenderedAmount();
                } catch (Exception e) {

                }


                break;

            case R.id.imageview_removecard:
                if (cardPaymentList != null) {
                    for (int i = 0; i < cardPaymentList.size(); i++) {
                        if (cardData != null && cardData.equals(cardPaymentList.get(i).getType())) {
                            cardPaymentList.remove(i);
                        }
                    }
                }

                cardPaymentAdapter = new CardPayment_Adapter(activity, cardPaymentList);
                nonscrolllistviewCredit.setAdapter(cardPaymentAdapter);
                cardPaymentAdapter.notifyDataSetChanged();
                setTenderedAmount();
                if (cardPaymentList.size() == 0) {
                    llCardlayuout.setVisibility(View.GONE);
                }


                break;

            case R.id.imageview_voucheradd:
                try {
                    MultiVoucherPayment multiVoucherPayment = new MultiVoucherPayment();
                    multiVoucherPayment.setVoucherNo("");
                    multiVoucherPayment.setAmt(0);
                    multiVoucherPayment.setPaymentType(voucherPaymentList.get(position).getPaymentType());
                    voucherPaymentList.add(voucherPaymentList.size(), multiVoucherPayment);
                    voucherPaymentAdapter = new VoucherPayment_Adapter(activity, voucherPaymentList);
                    nonscrolllistviewVoucher.setAdapter(voucherPaymentAdapter);
                    voucherPaymentAdapter.notifyDataSetChanged();
                    setTenderedAmount();
                } catch (Exception e) {

                }

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
                try {
                    MultiBankPaymentList bankPayment = new MultiBankPaymentList();
                    bankPayment.setPaymentType(bankPaymentList.get(position).getPaymentType());
                    bankPayment.setBankAccount(bankPaymentList.get(position).getBankAccount());
                    bankPayment.setBankAccountId(bankPaymentList.get(position).getBankAccountId());
                    bankPayment.setBankName(bankPaymentList.get(position).getBankName());
                    bankPayment.setAmount(0);
                    bankPayment.setDate("");
                    bankPayment.setTransactionNo("");
                    bankPaymentList.add(bankPaymentList.size(), bankPayment);
                    bankPaymentAdapter = new BankPayment_Adapter(activity, bankPaymentList);
                    nonscrolllistviewBank.setAdapter(bankPaymentAdapter);
                    bankPaymentAdapter.notifyDataSetChanged();
                    setTenderedAmount();
                } catch (Exception e) {

                }

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
                //edCashamounttendered.setText(""+cashTenderedAmount);

            }
        } catch (NumberFormatException e) {
            edCashamounttendered.setError(getResources().getString(R.string.error_numberformate));
        }

        BigDecimal big = new BigDecimal(cashTenderedAmount);
        tenderedAmount = Double.parseDouble(String.valueOf(big.setScale(2, RoundingMode.HALF_UP)));
        for (int i = 0; i < cardPaymentList.size(); i++) {
            tenderedAmount += cardPaymentList.get(i).getCardAmount();
            UtilView.showLogCat(TAG, "Card Payment at " + i + " : " + cardPaymentList.get(i).getCardDate());
            UtilView.showLogCat(TAG, "CardtenderedAmount " + i + " : " + tenderedAmount);


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
        //String txtTenderamt = edTenderedamt.getText().toString();
        //String txtTotalamt = edTotalamount.getText().toString();
        String txtDiscountamt = edTotaldiscount.getText().toString();

        try {
            BigDecimal bd, disc;
            double discountAmt = 0.00;
            BigDecimal bdtotalAmt = null;
            double totalAmt = 0.00, d = 0.00;
            if (edTotalamount.getText().toString().trim().equals("")) {
                totalAmt = 0.00;
            } else {
                totalAmt = Double.parseDouble(edTotalamount.getText().toString());
            }

            double tenderedAmt = 0.00;
            if (edTenderedamt.getText().toString().trim().equals("")) {
                tenderedAmt = 0.00;
            } else {
                tenderedAmt = Double.parseDouble(edTenderedamt.getText().toString().trim());

            }

            if (txtDiscountamt != null && !txtDiscountamt.equals("")) {

                discountAmt = Double.valueOf(txtDiscountamt);
                getCheckout.setDiscountAmtInPercentage(discountAmt);
                getCheckout.setDiscountAmount(Double.valueOf(String.valueOf(new BigDecimal((((totalAmt) * discountAmt) / 100)).setScale(2, RoundingMode.HALF_UP))));
                totalTaxAmt = getCheckout.getTotalTaxAmt() - ((getCheckout.getTotalTaxAmt() * Double.parseDouble(txtDiscountamt)) / 100);
                Log.e("totalTaxAmt", String.valueOf(getCheckout.getTotalTaxAmt() - ((getCheckout.getTotalTaxAmt() * Double.parseDouble(txtDiscountamt)) / 100)));
                totalAmt = (totalAmt - (((totalAmt * Double.parseDouble(txtDiscountamt)) / 100))) + (getCheckout.getTotalTaxAmt() - ((getCheckout.getTotalTaxAmt() * Double.parseDouble(txtDiscountamt)) / 100));
                if (totalAmt != 0.00) {
                    bdtotalAmt = new BigDecimal(totalAmt);
                    bdtotalAmt = bdtotalAmt.setScale(2, RoundingMode.HALF_UP);
                    if (bdtotalAmt != null)
                        d = bdtotalAmt.doubleValue();
                    Log.e("totalAmt1", String.valueOf(d));
                }
            } else {

                getCheckout.setDiscountAmtInPercentage(0.00);
                getCheckout.setDiscountAmount(0.00);
                d = totalAmt + getCheckout.getTotalTaxAmt();


            }

            getCheckout.setTotalTenderedAmount(tenderedAmt);

            double number = d;
            int decimal = (int) number;
            double fractional = number - decimal;
            String totalAmounToBePay = "";
            if (fractional < 0.50) {
                totalAmounToBePay = String.format("%.2f", Math.floor(d));
                getCheckout.setTotalCheckOutamt(Double.parseDouble(totalAmounToBePay));
                bd = new BigDecimal(d - Math.floor(d));
                bd = bd.setScale(2, RoundingMode.HALF_UP);
                getCheckout.setRoundingOffValue(String.valueOf(bd));
            } else {
                totalAmounToBePay = String.format("%.2f", Math.ceil(d));
                getCheckout.setTotalCheckOutamt(Double.parseDouble(totalAmounToBePay));
                bd = new BigDecimal(d - Math.ceil(d));
                bd = bd.setScale(2, RoundingMode.HALF_DOWN);
                getCheckout.setRoundingOffValue(String.valueOf(bd));
            }

            Log.e("totalAmounToBePay",totalAmounToBePay);
            edAmtCash.setText(""+totalAmounToBePay);
            paidAmount = String.format("%.2f", getCheckout.getTotalCheckOutamt());

            if (tenderedAmt != 0.00) {
                double returnAmt = tenderedAmt - getCheckout.getTotalCheckOutamt();
                edReturnamount.setText("" + new BigDecimal(returnAmt).setScale(2, BigDecimal.ROUND_HALF_UP));
                getCheckout.setTotalRemaininBalance(returnAmt);
            } else {
                //Toast.makeText(this, "Amount should not be less than totalAmount", Toast.LENGTH_SHORT).show();
                edReturnamount.setText("0.00");
            }
            // double returnAmt = (tenderedAmt + discountAmt) - totalAmt;

        } catch (NumberFormatException e) {
            edTotalamount.setError(getResources().getString(R.string.error_numberformate));
        }


    }

    private void updateDiscountAmount(Double txtDiscountamt) {
        Double discAmt = 0.00, getTax = 0.00;
        List<RestraCheckoutItem> restraCheckoutItems = new ArrayList<>();
        RestraCheckoutData data = new RestraCheckoutData();
        try {

            List<RestraCheckoutItem> result = new ArrayList<>();

            for (int i = 0; i < getCheckout.getSelectedItemsList().size(); i++) {

                RestraCheckoutItem itemsDiscoun = new RestraCheckoutItem();


                //Log.e("ItemName",getCheckout.getSelectedItemsList().get(i).getItemName());
                itemsDiscoun.setItemCode(getCheckout.getSelectedItemsList().get(i).getItemCode());
                itemsDiscoun.setItemId(getCheckout.getSelectedItemsList().get(i).getItemId());
                itemsDiscoun.setItemName(getCheckout.getSelectedItemsList().get(i).getItemName());
                itemsDiscoun.setUnitPrice(getCheckout.getSelectedItemsList().get(i).getUnitPrice());
                itemsDiscoun.setGstTaxPercantage(getCheckout.getSelectedItemsList().get(i).getGstTaxPercantage());
                itemsDiscoun.setQty(getCheckout.getSelectedItemsList().get(i).getQty());
                itemsDiscoun.setItemCategoryId(getCheckout.getSelectedItemsList().get(i).getItemCategoryId());
                itemsDiscoun.setItemCategoryName(getCheckout.getSelectedItemsList().get(i).getItemCategoryName());
                itemsDiscoun.setInclusiveJSON(getCheckout.getSelectedItemsList().get(i).getInclusiveJSON());
                itemsDiscoun.setTaxId(getCheckout.getSelectedItemsList().get(i).getTaxId());
                itemsDiscoun.setUom(getCheckout.getSelectedItemsList().get(i).getUom());
                itemsDiscoun.setTaxid(getCheckout.getSelectedItemsList().get(i).getOutputTaxId());
                itemsDiscoun.setInputTaxId(getCheckout.getSelectedItemsList().get(i).getInputTaxId());
                itemsDiscoun.setOutputTaxId(getCheckout.getSelectedItemsList().get(i).getOutputTaxId());
                itemsDiscoun.setItemTypeId(getCheckout.getSelectedItemsList().get(i).getItemTypeId());
                itemsDiscoun.setItemTypeName(getCheckout.getSelectedItemsList().get(i).getItemTypeName());
                itemsDiscoun.setTableId(getCheckout.getSelectedItemsList().get(i).getTableId());
                itemsDiscoun.setFloorId(getCheckout.getSelectedItemsList().get(i).getFloorId());
                itemsDiscoun.setWaiterName(getCheckout.getSelectedItemsList().get(i).getWaiterName());
                itemsDiscoun.setDiscountConfigAmt(txtDiscountamt);
                itemsDiscoun.setDiscountAmt(Double.parseDouble(String.valueOf(new BigDecimal(((itemsDiscoun.getUnitPrice() * itemsDiscoun.getQty() * Double.valueOf(txtDiscountamt)) / 100)).setScale(2, RoundingMode.HALF_UP))));
                itemsDiscoun.setAmtexclusivetax((Double.parseDouble(String.valueOf(new BigDecimal((getCheckout.getSelectedItemsList().get(i).getUnitPrice() * itemsDiscoun.getQty() - itemsDiscoun.getDiscountAmt())).setScale(2, RoundingMode.HALF_UP)))));
                Log.e("encAmmm", String.valueOf(itemsDiscoun.getAmtexclusivetax()));

                DecimalFormat df = new DecimalFormat("0.00");
                df.setRoundingMode(RoundingMode.CEILING);
                String itemTax = df.format((itemsDiscoun.getAmtexclusivetax() * itemsDiscoun.getGstTaxPercantage()) / 100);
                Log.e("taxval1", String.valueOf(itemTax));
                itemsDiscoun.setGstItemTax(Double.parseDouble(itemTax));
                itemsDiscoun.setAmtinclusivetax(Double.parseDouble(String.valueOf(new BigDecimal(itemsDiscoun.getAmtexclusivetax() + itemsDiscoun.getGstItemTax()).setScale(2, RoundingMode.HALF_UP))));
                itemsDiscoun.setTaxamt(Double.parseDouble(String.valueOf(new BigDecimal(itemsDiscoun.getGstItemTax()).setScale(2, RoundingMode.HALF_UP))));
                discAmt += itemsDiscoun.getDiscountAmt();
                getTax += itemsDiscoun.getGstItemTax();
                result.add(itemsDiscoun);
                Log.e("inctax", String.valueOf(itemsDiscoun.getAmtinclusivetax()));
                Log.e("enctax", String.valueOf(itemsDiscoun.getAmtexclusivetax()));
                Log.e("gstTax", String.valueOf(itemsDiscoun.getGstItemTax()) + "ItemName" + itemsDiscoun.getItemName());

            }


            for (int j = 0; j < result.size(); j++) {
                getCheckout.getSelectedItemsList().addAll(j, result);
            }
            getCheckout.setSelectedItemsList(result);
            getCheckout.setDiscountAmount(Double.parseDouble(String.valueOf(new BigDecimal(discAmt).setScale(2, RoundingMode.HALF_UP))));
            getCheckout.setTotalTaxAmt(Double.parseDouble(String.valueOf(new BigDecimal(getTax).setScale(2, RoundingMode.HALF_UP))));
        } catch (ConcurrentModificationException e) {
        }


    }


    /**
     * The Handler that gets information back from Bluetooth Devices
     */
    static class BluetoothHandler extends Handler {
        private final WeakReference<Activity_RestraPayment1> myWeakReference;

        //Creating weak reference of BluetoothPrinterActivity class to avoid any leak
        BluetoothHandler(Activity_RestraPayment1 weakReference) {
            myWeakReference = new WeakReference<Activity_RestraPayment1>(weakReference);
        }

        @Override
        public void handleMessage(Message msg) {
            Activity_RestraPayment1 bluetoothPrinterActivity = myWeakReference.get();
            tvPrinterStatus.setVisibility(View.GONE);
            if (bluetoothPrinterActivity != null) {
                super.handleMessage(msg);
                Bundle data = msg.getData();
                switch (data.getInt("flag")) {
                    case Contants.FLAG_STATE_CHANGE:
                        int state = data.getInt("state");
                        Log.i(TAG, "MESSAGE_STATE_CHANGE: " + state);
                        switch (state) {
                            case HsBluetoothPrintDriver.CONNECTED_BY_BLUETOOTH:

                                if (device != null) {
                                    tvPrinterStatus.setText(R.string.title_connected_to);
                                    tvPrinterStatus.append(device.getName());
                                    Constant.isPrinterConnected = true;
                                    //Toast.makeText(CONTEXT,"Connection successful.", Toast.LENGTH_SHORT).show();
                                    printConnect.setVisible(false);
                                    printDisConnect.setVisible(false);
                                    printConnect.setTitle(device.getName());
                                }

                                break;
                            case HsBluetoothPrintDriver.FLAG_SUCCESS_CONNECT:
                                tvPrinterStatus.setText(R.string.title_connecting);
                                // printConnect.setTitle(R.string.title_connecting);
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

    @OnClick({R.id.id_FreeMeal,R.id.btn_saveemail, R.id.btn_save, R.id.btn_print, R.id.tvOne, R.id.tvTwo, R.id.tvFive, R.id.tvTen, R.id.tvTweenty, R.id.tvFifty, R.id.tvHundred, R.id.tvTwoHundred, R.id.tvFiveHundred, R.id.tvTwoThoushand, R.id.tvClearAmt})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.id_FreeMeal:
                if (idFreeMeal.isChecked()){
                    edTotaldiscount.setText("100");
                }else{
                    edTotaldiscount.setText("");
                }
                break;
            case R.id.btn_save:
                try {
                    String cash = edCashamounttendered.getText().toString().trim();
                    String tenderedAmt = edTenderedamt.getText().toString().trim();

                    Double amttopay = 0.00;
                    String amtToPay = edAmtCash.getText().toString();
                    if (amtToPay != null && !amtToPay.equals("")) {
                        amttopay = Double.valueOf(amtToPay);
                    }
                    Double tendAmt = 0.00;
                    if (tenderedAmt != null && !tenderedAmt.equals("")) {
                        tendAmt = Double.parseDouble(tenderedAmt);
                    }
                    Double cashAmount = 0.00;
                    if (cash != null && !cash.equals("")) {
                        cashAmount = Double.valueOf(cash);
                    }

                    //-----------------------------------------

                    String username = sharedPreference.getData(Constant.USERNAME);




                    getCheckout.setUserId(username);
                    getCheckout.setPaymentType("multiPayment");

                    List<MultiCashPaymentList> multiCashPaymentLists = new ArrayList<>();
                    if (cashAmount != null && cashAmount != 0.00) {
                        MultiCashPaymentList cashPaymentList = new MultiCashPaymentList();
                        //tendAmt = Double.valueOf(edCashamounttendered.getText().toString().trim());
                        cashPaymentList.setCashAmt(cashAmount);
                        cashPaymentList.setPaymentType(1L);
                        multiCashPaymentLists.add(cashPaymentList);
                        cashData = "Cash";
                        cashString = "Cash";
                        cashPayment.setPaymentTypeCash(cashData);

                    }

                    cashPayment.setMultiCashPaymentList(multiCashPaymentLists);


                    VoucherPayment voucherPayment = new VoucherPayment();




            /*    if (getCheckout.getDiscountAmtInPercentage()!=0.0){

                    //voucherPaymentList.clear();
                    MultiVoucherPayment multiVoucherPayment = new MultiVoucherPayment();
                    multiVoucherPayment.setVoucherNo("Discount Given");
                    multiVoucherPayment.setPaymentType(4L);
                    multiVoucherPayment.setVoucherAmt(getCheckout.getDiscountAmtInPercentage());
                    voucherPaymentList.add(multiVoucherPayment);

                    voucherPayment.setMultiVoucherPayments(voucherPaymentList);
                }else{*/

                    voucherPayment.setPaymentTypeCash(voucherData);
                    voucherPayment.setMultiVoucherPayments(voucherPaymentList);
               // }
                    voucherPayment.setPaymentTypeCash(voucherData);
                    voucherPayment.setMultiVoucherPayments(voucherPaymentList);
                    BankPayment bankPayment = new BankPayment();
                    bankPayment.setPaymentTypeCash(bankData);
                    bankPayment.setMultiBankPaymentList(bankPaymentList);
                    getCheckout.setCashPayment(cashPayment);
                    getCheckout.setBankPayment(bankPayment);
                    getCheckout.setVoucherPayment(voucherPayment);
                    CreditPayment creditPayment = new CreditPayment();
                    creditPayment.setPaymentTypeCash(cardData);
                    creditPayment.setCardPaymentList(cardPaymentList);
                    getCheckout.setCreditPayment(creditPayment);


                    //-------------------------------------------

                    //  Log.e("amttopayCard", String.valueOf(cardPaymentList.get(0).getCardAmount()+cashAmount));
                    boolean flagcash = false;
                    boolean flagcard = false;
                    boolean flagbank = false;
                    boolean flagVoucher = false;
                    boolean flagFullDiscount = false;

                    Log.e("cashData", String.valueOf(cashData));
                    Log.e("cashCardData", String.valueOf(cardData));
                    Log.e("cashBankData", String.valueOf(bankData));
                    Log.e("cashVoucherData", String.valueOf(voucherData));
                    Double totalPayAmount = 0.00;
                    if (cashString != null && !cashString.equals("")) {
                        if (amttopay != null && amttopay <= cashAmount) {
                            flagcash = true;
                        } else {
                            // UtilView.showToast(activity, "Payment should not be less than total amount");
                        }
                        totalPayAmount = totalPayAmount + cashAmount;
                    }


                    if (cardstring != null && !cardstring.equals("")) {
                        Double cardAmount = 0.00;
                        if (cardPaymentList != null && cardPaymentList.size() > 0) {
                            for (int c = 0; c < cardPaymentList.size(); c++) {
                                Log.e("cardamt", String.valueOf(cardPaymentList.get(c).getCardAmount()));
                                Log.e("cardcash", String.valueOf(cashAmount));
                                cardAmount += cardPaymentList.get(c).getCardAmount();
                            }


                        }
                        totalPayAmount = totalPayAmount + cardAmount;
                        if (amttopay != null && amttopay.equals(totalPayAmount)) {
                            flagcard = true;

                        } else {

                            // UtilView.showToast(activity, "Payment  should be equal to Total Amount");
                        }


                    }
                    if (bankString != null && !bankString.equals("")) {
                        Double bankAmount = 0.00;
                        if (bankPaymentList != null && bankPaymentList.size() > 0) {
                            for (int c = 0; c < bankPaymentList.size(); c++) {
                                bankAmount += bankPaymentList.get(c).getAmount();
                                Log.e("bankdata", String.valueOf(bankPaymentList.get(c).getAmount()));

                            }

                        }
                        totalPayAmount = totalPayAmount + bankAmount;
                        Log.e("totalPayAmount", String.valueOf(totalPayAmount));

                        if (amttopay != null && amttopay.equals(totalPayAmount)) {
                            flagbank = true;
                        } else {
                            //  UtilView.showToast(activity, "Payment  should be equal to Total Amount");
                        }
                    }
                    if (voucherString != null && !voucherString.equals("")) {
                        Double voucherAmount = 0.00;
                        if (voucherPaymentList != null && voucherPaymentList.size() > 0) {
                            for (int c = 0; c < voucherPaymentList.size(); c++) {
                                if (voucherPaymentList.get(c).getVoucherNo() != null && !voucherPaymentList.get(c).getVoucherNo().equals("Discount Given")) {
                                    voucherAmount += voucherPaymentList.get(c).getVoucherAmt();
                                    Log.e("voucherdata", String.valueOf(voucherPaymentList.get(c).getVoucherAmt()));
                                }


                            }

                        }
                        totalPayAmount = totalPayAmount + voucherAmount;
                        if (amttopay != null && amttopay.equals(totalPayAmount)) {
                            flagVoucher = true;

                        } else {

                            // UtilView.showToast(activity, "Payment  should be equal to Total Amount");
                        }
                    }

                    if (getCheckout.getDiscountAmtInPercentage()!=null &&  getCheckout.getDiscountAmtInPercentage() == 100){
                        Log.e("fullDiscount", String.valueOf(getCheckout.getDiscountAmtInPercentage()));
                            flagFullDiscount=true;
                    }else{
                        Log.e("fullDiscount", String.valueOf(getCheckout.getDiscountAmtInPercentage()));
                    }

                    Log.e("amttopay", String.valueOf(amttopay));
                    if (isCheckoutable && getCheckout != null && flagcash || flagcard || flagbank || flagVoucher || flagFullDiscount) {

                        if (totalTaxAmt != 0.00) {
                            getCheckout.setTotalTaxAmt(Double.valueOf(String.valueOf(new BigDecimal(totalTaxAmt).setScale(2, RoundingMode.HALF_UP))));
                        } else {
                            getCheckout.setTotalTaxAmt(Double.valueOf(String.valueOf(new BigDecimal(getCheckout.getTotalTaxAmt()).setScale(2, RoundingMode.HALF_UP))));
                        }

                        if (getCheckout.getDiscountAmtInPercentage() != null && getCheckout.getDiscountAmtInPercentage() != 0.00) {
                            updateDiscountAmount(getCheckout.getDiscountAmtInPercentage());
                        }


                        callServer1();
                    } else {
                        UtilView.showToast(activity, "Payment should not be less than or greater than total amount");

                       // roundingDone = true;
                    }
                } catch (Exception e) {
                    Log.e("Payment Exception", e.toString());
                }


                break;

            case R.id.btn_saveemail:

//                try {
                    String cash = edCashamounttendered.getText().toString().trim();
                    String tenderedAmt = edTenderedamt.getText().toString().trim();

                    Double amttopay = 0.00;
                    String amtToPay = edAmtCash.getText().toString();
                    if (amtToPay != null && !amtToPay.equals("")) {
                        amttopay = Double.valueOf(amtToPay);
                    }
                    Double tendAmt = 0.00;
                    if (tenderedAmt != null && !tenderedAmt.equals("")) {
                        tendAmt = Double.parseDouble(tenderedAmt);
                    }
                    Double cashAmount = 0.00;
                    if (cash != null && !cash.equals("")) {
                        cashAmount = Double.valueOf(cash);
                    }

                    //-----------------------------------------

                    String username = sharedPreference.getData(Constant.USERNAME);




                    getCheckout.setUserId(username);
                    getCheckout.setPaymentType("multiPayment");

                    List<MultiCashPaymentList> multiCashPaymentLists = new ArrayList<>();
                    if (cashAmount != null && cashAmount != 0.00) {
                        MultiCashPaymentList cashPaymentList = new MultiCashPaymentList();
                        //tendAmt = Double.valueOf(edCashamounttendered.getText().toString().trim());
                        cashPaymentList.setCashAmt(cashAmount);
                        cashPaymentList.setPaymentType(1L);
                        multiCashPaymentLists.add(cashPaymentList);
                        cashData = "Cash";
                        cashString = "Cash";
                        cashPayment.setPaymentTypeCash(cashData);

                    }

                    cashPayment.setMultiCashPaymentList(multiCashPaymentLists);

                    CreditPayment creditPayment = new CreditPayment();
                    creditPayment.setPaymentTypeCash(cardData);
                    creditPayment.setCardPaymentList(cardPaymentList);
                    VoucherPayment voucherPayment = new VoucherPayment();




                    voucherPayment.setPaymentTypeCash(voucherData);
                    voucherPayment.setMultiVoucherPayments(voucherPaymentList);
                    BankPayment bankPayment = new BankPayment();
                    bankPayment.setPaymentTypeCash(bankData);
                    bankPayment.setMultiBankPaymentList(bankPaymentList);
                    getCheckout.setCashPayment(cashPayment);
                    getCheckout.setBankPayment(bankPayment);
                    getCheckout.setVoucherPayment(voucherPayment);
                    getCheckout.setCreditPayment(creditPayment);

                    isNotification = getCheckout.getNotificationAppend();
                    Log.e("notificatinData", String.valueOf(getCheckout.getNotificationAppend()));
                    //-------------------------------------------

                    //  Log.e("amttopayCard", String.valueOf(cardPaymentList.get(0).getCardAmount()+cashAmount));
                    boolean flagcash = false;
                    boolean flagcard = false;
                    boolean flagbank = false;
                    boolean flagVoucher = false;
                    boolean flagFullDiscount = false;

                    Log.e("cashData", String.valueOf(cashData));
                    Log.e("cashCardData", String.valueOf(cardData));
                    Log.e("cashBankData", String.valueOf(bankData));
                    Log.e("cashVoucherData", String.valueOf(voucherData));
                    Double totalPayAmount = 0.00;
                    if (cashString != null && !cashString.equals("")) {
                        if (amttopay != null && amttopay <= cashAmount) {
                            flagcash = true;
                        } else {
                            // UtilView.showToast(activity, "Payment should not be less than total amount");
                        }
                        totalPayAmount = totalPayAmount + cashAmount;
                    }


                    if (cardstring != null && !cardstring.equals("")) {
                        Double cardAmount = 0.00;
                        if (cardPaymentList != null && cardPaymentList.size() > 0) {
                            for (int c = 0; c < cardPaymentList.size(); c++) {
                                Log.e("cardamt", String.valueOf(cardPaymentList.get(c).getCardAmount()));
                                Log.e("cardcash", String.valueOf(cashAmount));
                                cardAmount += cardPaymentList.get(c).getCardAmount();
                            }


                        }
                        totalPayAmount = totalPayAmount + cardAmount;
                        if (amttopay != null && amttopay.equals(totalPayAmount)) {
                            flagcard = true;

                        } else {

                            // UtilView.showToast(activity, "Payment  should be equal to Total Amount");
                        }


                    }
                    if (bankString != null && !bankString.equals("")) {
                        Double bankAmount = 0.00;
                        if (bankPaymentList != null && bankPaymentList.size() > 0) {
                            for (int c = 0; c < bankPaymentList.size(); c++) {
                                bankAmount += bankPaymentList.get(c).getAmount();
                                Log.e("bankdata", String.valueOf(bankPaymentList.get(c).getAmount()));

                            }

                        }
                        totalPayAmount = totalPayAmount + bankAmount;
                        Log.e("totalPayAmount", String.valueOf(totalPayAmount));

                        if (amttopay != null && amttopay.equals(totalPayAmount)) {
                            flagbank = true;
                        } else {
                            //  UtilView.showToast(activity, "Payment  should be equal to Total Amount");
                        }
                    }
                    if (voucherString != null && !voucherString.equals("")) {
                        Double voucherAmount = 0.00;
                        if (voucherPaymentList != null && voucherPaymentList.size() > 0) {
                            for (int c = 0; c < voucherPaymentList.size(); c++) {
                                if (voucherPaymentList.get(c).getVoucherNo() != null && !voucherPaymentList.get(c).getVoucherNo().equals("Discount Given")) {
                                    voucherAmount += voucherPaymentList.get(c).getVoucherAmt();
                                    Log.e("voucherdata", String.valueOf(voucherPaymentList.get(c).getVoucherAmt()));
                                }


                            }

                        }
                        totalPayAmount = totalPayAmount + voucherAmount;
                        if (amttopay != null && amttopay.equals(totalPayAmount)) {
                            flagVoucher = true;

                        } else {

                            // UtilView.showToast(activity, "Payment  should be equal to Total Amount");
                        }
                    }
                    if (getCheckout.getDiscountAmtInPercentage()!=null &&  getCheckout.getDiscountAmtInPercentage() == 100){
                        Log.e("fullDiscount", String.valueOf(getCheckout.getDiscountAmtInPercentage()));
                        flagFullDiscount=true;
                    }else{
                        Log.e("fullDiscount", String.valueOf(getCheckout.getDiscountAmtInPercentage()));
                    }
                    if (isCheckoutable && getCheckout != null && flagcash || flagcard || flagbank || flagVoucher || flagFullDiscount) {
                        if (totalTaxAmt != 0.00) {
                            getCheckout.setTotalTaxAmt(Double.valueOf(String.valueOf(new BigDecimal(totalTaxAmt).setScale(2, RoundingMode.HALF_UP))));
                        } else {
                            getCheckout.setTotalTaxAmt(Double.valueOf(String.valueOf(new BigDecimal(getCheckout.getTotalTaxAmt()).setScale(2, RoundingMode.HALF_UP))));
                        }
                        Double discount = 0.00;
                        // String discountAmt = edTotaldiscount.getText().toString();
                        if (getCheckout.getDiscountAmtInPercentage() != null && getCheckout.getDiscountAmtInPercentage() != 0.00) {
                            updateDiscountAmount(getCheckout.getDiscountAmtInPercentage());
                        } else {
                            discount = 0.00;
                        }

                        if (BLUETOOTH_PRINTER != null) {
                            if (!BLUETOOTH_PRINTER.IsNoConnection()) {

                                UtilView.showLogCat(TAG, "state " + BLUETOOTH_PRINTER.getState());
                                if (BLUETOOTH_PRINTER.getState() == 3) {
                                    callServer();
                                } else if (BLUETOOTH_PRINTER.getState() == 2) {


                                } else {
                                    UtilView.showToast(activity, "Please setup the printer connection first.");
                                }

                            } else {
                                UtilView.showToast(activity, "Please setup the printer connection first.");
                            }
                        } else {
                            // UtilView.showToast(activity, "Please setup the printer connection first.");
                        }
                    } else {
                        UtilView.showToast(activity, "Payment should not be less than or greater than total amount");

                    }
               /* } catch (Exception e) {
                    Log.e("Payment Exception", e.toString());
                }*/


                break;
            case R.id.btn_print:

                if (isCheckoutable && getCheckout != null) {

                    try {

                        if (paidAmount == null) {
                            paidAmount = String.format("%.2f", getCheckout.getTotalCheckOutamt());

                        }
                        isNotification = getCheckout.getNotificationAppend();
                        Log.e("notificatinData", String.valueOf(isNotification));
                        String username1 = sharedPreference.getData(Constant.USERNAME);
                        Log.e("usename", username1.toString());
                        String totalTenderedAmount = edCashamounttendered.getText().toString().trim();
                        if (totalTenderedAmount == null || totalTenderedAmount.equals("")) {
                            totalTenderedAmount = "0.00";
                        }

                        BigDecimal big2 = new BigDecimal(totalTenderedAmount);
                        double tenderedAmount = Double.parseDouble(String.valueOf(big2.setScale(2, RoundingMode.HALF_UP)));

                        String tenderedAmtPay = edTenderedamt.getText().toString().trim();

                        Double tendAmtPay = 0.00;
                        if (tenderedAmtPay != null && !tenderedAmtPay.equals("")) {
                            tendAmtPay = Double.parseDouble(tenderedAmtPay);
                        }
                        List<MultiCashPaymentList> multiCashPaymentLiss = new ArrayList<>();
                        if (tenderedAmount != 0.00) {
                            MultiCashPaymentList cashPaymentList = new MultiCashPaymentList();
                            cashPaymentList.setCashAmt(tenderedAmount);
                            cashPaymentList.setPaymentType(1L);
                            multiCashPaymentLiss.add(cashPaymentList);
                            cashData = "Cash";
                            cashString = "Cash";
                            cashPayment.setPaymentTypeCash(cashData);

                        }
                        cashPayment.setMultiCashPaymentList(multiCashPaymentLiss);
                        if (totalTaxAmt != 0.00) {
                            totalTaxAmt = Double.valueOf(String.valueOf(new BigDecimal(totalTaxAmt).setScale(2, RoundingMode.HALF_UP)));
                            Log.e("totalTaxAmt1", String.valueOf(totalTaxAmt));
                            // getCheckout.setTotalTaxAmt(Double.valueOf(String.valueOf(new BigDecimal(totalTaxAmt).setScale(2, RoundingMode.HALF_UP))));
                        } else {
                            totalTaxAmt = getCheckout.getTotalTaxAmt();
                            //getCheckout.setTotalTaxAmt(Double.valueOf(String.valueOf(new BigDecimal(getCheckout.getTotalTaxAmt()).setScale(2, RoundingMode.HALF_UP))));
                        }

                        CreditPayment creditPayment1 = new CreditPayment();
                        creditPayment1.setPaymentTypeCash(cardData);
                        creditPayment1.setCardPaymentList(cardPaymentList);
                        VoucherPayment voucherPayment1 = new VoucherPayment();
                        Double discount = 0.00;
                        if (getCheckout.getDiscountAmtInPercentage() != null) {
                            getCheckout.setDiscountAmtInPercentage(getCheckout.getDiscountAmtInPercentage());
                            // updateDiscountAmount(getCheckout.getDiscountAmount());
                        } else {
                            getCheckout.setDiscountAmtInPercentage(0.00);
                            discount = 0.00;
                        }
                        if (getCheckout.getDiscountAmount() != null) {
                            getCheckout.setDiscountAmount(getCheckout.getDiscountAmount());
                            // updateDiscountAmount(getCheckout.getDiscountAmount());
                        } else {
                            getCheckout.setDiscountAmount(0.00);
                            discount = 0.00;
                        }

                        voucherPayment1.setPaymentTypeCash(voucherData);
                        voucherPayment1.setMultiVoucherPayments(voucherPaymentList);
                        BankPayment bankPayment1 = new BankPayment();
                        bankPayment1.setPaymentTypeCash(bankData);
                        bankPayment1.setMultiBankPaymentList(bankPaymentList);

                        getCheckout.setCashPayment(cashPayment);
                        getCheckout.setBankPayment(bankPayment1);
                        getCheckout.setVoucherPayment(voucherPayment1);
                        getCheckout.setCreditPayment(creditPayment1);
                        getCheckout.setUserId(username1);
                        getCheckout.setPaymentType("multiPayment");

                    } catch (Exception e) {

                    }


                    if (BLUETOOTH_PRINTER != null) {
                        if (!BLUETOOTH_PRINTER.IsNoConnection()) {
                            UtilView.showLogCat(TAG, "state " + BLUETOOTH_PRINTER.getState());
                            if (BLUETOOTH_PRINTER.getState() == 3) {
                                callServerCustomer(totalTaxAmt);
                            } else if (BLUETOOTH_PRINTER.getState() == 2) {


                            } else {
                                UtilView.showToast(activity, "Please setup the printer connection first.");
                            }

                        } else {
                            UtilView.showToast(activity, "Please setup the printer connection first.");
                        }
                    } else {
                        // UtilView.showToast(activity, "Please setup the printer connection first.");
                    }
                } else {
                    UtilView.showToast(activity, "Payment should not be less than or greater than total amount");
                }
                break;

            case R.id.tvOne:
                updateCashAmount(1);
                break;
            case R.id.tvTwo:
                updateCashAmount(2);
                break;
            case R.id.tvFive:
                updateCashAmount(5);
                break;
            case R.id.tvTen:
                updateCashAmount(10);
                break;
            case R.id.tvTweenty:
                updateCashAmount(20);
                break;
            case R.id.tvFifty:
                updateCashAmount(50);
                break;
            case R.id.tvHundred:
                updateCashAmount(100);
                break;
            case R.id.tvTwoHundred:
                updateCashAmount(200);
                break;
            case R.id.tvFiveHundred:
                updateCashAmount(500);
                break;
            case R.id.tvTwoThoushand:
                updateCashAmount(2000);
                break;
            case R.id.tvClearAmt:
                updateCashAmount(0.00);
                //edTenderedamt.setText("0.00");
                //edReturnamount.setText("0.00");
              /*  if (text.length() > 0) {
                    String amount = text.substring(0, text.length() - 1);
                    edAmtCash.setText(amount);
                    if (amount == null || amount.equals("")) {
                        cashAmt = 0.00;
                    } else {
                        cashAmt = Double.parseDouble(amount);
                    }


                }*/


                break;


        }
    }

    private void callServerCustomer(double totalTaxAmt) {
        final Gson gson = new Gson();
        if (getCheckout != null) {
            getCheckout.setPaymentType(checkoutType);
            getCheckout.setTableNo(Long.valueOf(getCheckout.getTableVal()));
            String tableId = getCheckout.getTableVal();
            String tableName = getCheckout.getTableName();
            String waiterName = getCheckout.getWaiterName();
            getCheckout.setCutomerName(getCheckout.getCustomerName());
            String url = serverUrl + "/restaurant/printCustomerBill";
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (serverUrl != null) {
                if (isInternetPresent) {
                    UtilView.showProgessBar(activity, progressBar);
                     postDataTask = new PostDataTask(this, new AsyncTaskCompleteListener<String>() {
                        @Override
                        public void onTaskComplete(String result) {
                            UtilView.hideProgessBar(activity, progressBar);
                            if (result != null) {
                                Log.e("custresult",result.toString());
                                if (result.equals("invalid")) {
                                    UtilView.showToast(activity, getResources().getString(R.string.accesstoken_error));
                                    Intent intent = new Intent(activity, Activity_Login.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    callPrinservicesCustomer(getCheckout, totalTaxAmt);
                                }
                            }
                        }
                    }, false);
                    UtilView.showLogCat(TAG, "Post Data Value " + gson.toJson(getCheckout).toString());
                    getCheckout.setType("Mobile");
                    postDataTask.execute(gson.toJson(getCheckout).toString(), url, "");
                } else {
                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
                }
            } else {
                UtilView.gotToLogin(activity);
            }





        }
    }

    private void callPrinservicesCustomer(RestraCheckoutData saveResponseData, double totalTaxAmt) {
        if (saveResponseData != null) {

            SharedPreference sharedPreference = SharedPreference.getInstance(activity);
            if (sharedPreference != null) {

                String applicationDataJson = sharedPreference.getData(Constant.COMPANYDATA);
                String imageLogoUrl;

                if (applicationDataJson != null) {
                    Gson gson = new Gson();
                    String serverUrl = UtilView.getUrl(activity);

                    companyData = gson.fromJson(applicationDataJson, CompanyData.class);
                    if (companyData != null) {
                        if (companyData.getFileName() != null) {
                            imageLogoUrl = serverUrl + companyData.getFileName();
                            GetCompanyLogoTask task = new GetCompanyLogoTask(activity, new AsyncTaskCompleteListener1<Bitmap>() {
                                @Override
                                public void onTaskComplete(Bitmap bmp) {
                                    makePrintCustomer(bmp, saveResponseData, companyData, totalTaxAmt);
                                }
                            }, false);
                            task.execute(imageLogoUrl, "");

                        } else {
                            makePrintCustomer(null, saveResponseData, companyData, totalTaxAmt);
                        }
                    } else {
                        makePrintCustomer(null, saveResponseData, companyData, totalTaxAmt);
                    }
                } else {
                    makePrintCustomer(null, saveResponseData, companyData, totalTaxAmt);
                }
            } else {
                makePrintCustomer(null, saveResponseData, companyData, totalTaxAmt);
            }
            //     InputStream logoInputStream = PdfUtils.getLogoInputStream(activity, progressBar);


        }
    }

    private void makePrintCustomer(Bitmap bmp, RestraCheckoutData saveResponseData, CompanyData companyData, double totalTaxAmt) {
        boolean printStatus = false;
        Log.e("isNotification1", String.valueOf(isNotification));
       // String tableName = getCheckout.getTableName();
        if (printerType != null && printerType.equals(getResources().getString(R.string.pos_3inch))) {
            printStatus = PrintReceipt.printInvoice3InchCustomer(Activity_RestraPayment1.this, saveResponseData, bmp, paidAmount, companyData, totalTaxAmt,isNotification);
        } else if (printerType != null && printerType.equals(getResources().getString(R.string.pos_2inch))) {
            printStatus = PrintReceipt.printInvoice2InchCustomer(Activity_RestraPayment1.this, saveResponseData, bmp, paidAmount, companyData, totalTaxAmt,isNotification);

        }
        if (printStatus) {
            UtilView.showToast(Activity_RestraPayment1.this, "Successfully");

        }
    }


    public void getDatePicker(Activity mActivity) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final TimeZone utc = TimeZone.getTimeZone("UTC");
        final SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        f.setTimeZone(utc);
        DatePickerDialog datePickerDialog = new DatePickerDialog(mActivity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // edCardTransDate.setText(String.valueOf(new StringBuilder().append(dayOfMonth).append("/").append(month + 1).append("/").append(year)));
                calendar.set(year, month, dayOfMonth);
                selectedDate = f.format(calendar.getTime());
            }
        }, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }




}
