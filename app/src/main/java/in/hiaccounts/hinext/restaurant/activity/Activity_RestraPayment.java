package in.hiaccounts.hinext.restaurant.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mocoo.hang.rtprinter.driver.Contants;
import com.mocoo.hang.rtprinter.driver.HsBluetoothPrintDriver;
import com.rey.material.widget.ProgressView;
import com.yinglan.keyboard.HideUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.application.activity.NavigationDrawer_Activity;
import in.hiaccounts.hinext.restaurant.model.checkout.CashPayment;
import in.hiaccounts.hinext.restaurant.model.checkout.RestraCheckoutData;
import in.hiaccounts.hinext.restaurant.model.checkout.RestraSaveCheckoutData;
import in.hiaccounts.hinext.sales.pos_printer.DeviceListActivity;
import in.hiaccounts.hinext.sales.pos_printer.PrintReceipt;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;


/**
 * Created by Prateek on 8/5/2017.
 */

public class Activity_RestraPayment extends AppCompatActivity {

    private AlertDialog.Builder alertDlgBuilder;
    private static BluetoothDevice device;
    private static final int REQUEST_CONNECT_DEVICE = 1;
    private static final int REQUEST_ENABLE_BT = 2;
    private BluetoothAdapter mBluetoothAdapter = null;
    public static HsBluetoothPrintDriver BLUETOOTH_PRINTER = null;
    public static final String TAG=Activity_RestraPayment.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edTotalAmount)
    EditText edTotalAmount;
    @BindView(R.id.edDiscount)
    EditText edDiscount;
    @BindView(R.id.edPaymentAmount)
    EditText edPaymentAmount;
    @BindView(R.id.edAmtTendered)
    EditText edAmtTendered;
    @BindView(R.id.edSupplierInvoiceNo)
    EditText edSupplierInvoiceNo;
    @BindView(R.id.edAmountReturn)
    EditText edAmountReturn;
    @BindView(R.id.btn_saveemail)
    Button btnSaveemail;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.ll_bottomlayout)
    LinearLayout llBottomlayout;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;

    /*@BindView(R.id.tvPrinterStatus)
    TextView tvPrinterStatus;*/



    private static TextView tvPrinterStatus = null;
    @BindView(R.id.llSupplierInvoice)
    LinearLayout llSupplierInvoice;


    double discountAmt;
    double tenderedAmt;
    double returnAmount;
    boolean isCheckoutable = false;
    RestraCheckoutData getCheckout;
    private int currentAPIVersion = Build.VERSION.SDK_INT;
    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 123;
    @BindView(R.id.llAmountTendered)
    LinearLayout llAmountTendered;
    @BindView(R.id.llAmountReturned)
    LinearLayout llAmountReturned;
    @BindView(R.id.edTransactionNo)
    EditText edTransactionNo;
    @BindView(R.id.llTransactionNumber)
    LinearLayout llTransactionNumber;

    RestraSaveCheckoutData saveResponseData = null;
    Activity activity;

    private ServiceHandler serviceHandler;
    Boolean isInternetPresent = false;
    private String serverUrl, checkoutType = "",transactionNo,printerType="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        setContentView(R.layout.activity_cashpayment);
        ButterKnife.bind(this);
        initComponentView();

    }

    private void initComponentView() {
        ButterKnife.bind(this);
        activity = this;
        serviceHandler = new ServiceHandler(activity);
        serverUrl = UtilView.getUrl(activity);
        getCheckout = (RestraCheckoutData) getIntent().getSerializableExtra("checkoutData");
        checkoutType = getIntent().getStringExtra("checkoutType");
        if (checkoutType.equals("cashPayment")){
            toolbar.setTitle(getResources().getString(R.string.cash_payment));
        }
        if (checkoutType.equals("Cheque")){
            toolbar.setTitle(getResources().getString(R.string.bank_payment));
        }


        tvPrinterStatus = (TextView) findViewById(R.id.tvPrinterStatus);

        printerSetup();




        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        if (getCheckout != null) {
            edTotalAmount.setText("" + getCheckout.getTotalCheckOutamt());
            edPaymentAmount.setText("" + getCheckout.getTotalCheckOutamt());
            edDiscount.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    try {

                        discountAmt = Double.parseDouble(s.toString().trim());
                        edDiscount.setError(null);
                        if (discountAmt < getCheckout.getTotalCheckOutamt()) {
                            isCheckoutable = true;
                            edPaymentAmount.setText("" + (getCheckout.getTotalCheckOutamt() - discountAmt));
                        } else {
                            isCheckoutable = false;
                            edDiscount.setError(getResources().getString(R.string.error_discountprice));
                        }

                    } catch (NumberFormatException ne) {
                        isCheckoutable = false;
                        edDiscount.setError(getResources().getString(R.string.error_numberformate));
                    }

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            edAmtTendered.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    try {

                        tenderedAmt = Double.parseDouble(s.toString().trim());

                        if (tenderedAmt < (getCheckout.getTotalCheckOutamt() - discountAmt)) {
                            isCheckoutable = false;
                            edAmountReturn.setText("0");
                            edAmtTendered.setError(getResources().getString(R.string.error_tenderamount));
                        } else {
                            returnAmount = tenderedAmt - getCheckout.getTotalCheckOutamt() - discountAmt;
                            edAmountReturn.setText(("" + returnAmount));
                            isCheckoutable = true;
                        }

                    } catch (NumberFormatException ne) {
                        isCheckoutable = false;
                        edAmtTendered.setError(getResources().getString(R.string.error_numberformate));
                    }


                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            edTransactionNo.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    if (s==null || s.toString().equals("")){
                        edTransactionNo.setError(getResources().getString(R.string.err_msg));
                        isCheckoutable = false;
                    }else {
                        isCheckoutable = true;
                        transactionNo=s.toString();

                    }




                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            if (checkoutType!=null && !checkoutType.equals("")) {
                if (checkoutType.equals("cashPayment")) {
                    llAmountReturned.setVisibility(View.VISIBLE);
                    llAmountTendered.setVisibility(View.VISIBLE);
                    llTransactionNumber.setVisibility(View.GONE);

                    llSupplierInvoice.setVisibility(View.GONE);

                    edDiscount.setFocusable(true);
                    edDiscount.setFocusableInTouchMode(true);
                    edDiscount.setBackgroundColor(getResources().getColor(R.color.colorWhite));

                }
                if (checkoutType.equals("Cheque")) {

                    edDiscount.setFocusable(true);
                    edDiscount.setFocusableInTouchMode(true);
                    edDiscount.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                    llAmountReturned.setVisibility(View.GONE);
                    llAmountTendered.setVisibility(View.GONE);
                    llTransactionNumber.setVisibility(View.VISIBLE);

                }
            }
        }
    }

    private void printerSetup() {


        tvPrinterStatus.setVisibility(View.VISIBLE);
        // Get device's Bluetooth adapter
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        // If the adapter is null, then Bluetooth is not available in your device
        if (mBluetoothAdapter == null) {
            UtilView.showToast(activity,"Bluetooth is not available");
        }
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

    @OnClick({R.id.btn_saveemail, R.id.btn_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_saveemail:


                if (isCheckoutable && getCheckout != null) {
                    if (checkoutType.equals("cashPayment")){
                        CashPayment cashPayment = new CashPayment();
                        cashPayment.setTotalCPDiscount(discountAmt);
                        cashPayment.setTotalCPAmountRefunded("" + returnAmount);
                        cashPayment.setTotalCPAmountTendered(tenderedAmt);
                       // getCheckout.setCashPayment(cashPayment);

                        callServer();
                    }
                    if (checkoutType.equals("Cheque")){
                       /* CreditPayment creditPayment=new CreditPayment();
                        creditPayment.setTotalCCPDiscount(""+discountAmt);
                        creditPayment.setTotalCCPAfterDiscount("" + edPaymentAmount.getText().toString().trim());
                        creditPayment.setTransactionNo(transactionNo);*/
                      //  getCheckout.setCreditPayment(creditPayment);

                        if (transactionNo==null || transactionNo.equals("")){

                            edTransactionNo.setError(getResources().getString(R.string.err_msg));
                        }else {
                            callServer();
                        }
                    }


                } else {
                    UtilView.showToast(this, "Please fill the details first.");
                }


                break;
            case R.id.btn_cancel:
                finish();
                break;
        }
    }
    public void callServer(){
        final Gson gson = new Gson();
        getCheckout.setPaymentType(checkoutType);
      //  getCheckout.setSupplierInvNo(edSupplierInvoiceNo.getText().toString().trim());
        String url = serverUrl + "/restaurant/save?tableNo=undefined&tableName=undefined";


        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
                UtilView.showProgessBar(activity, progressBar);
                PostDataTask postDataTask = new PostDataTask(this, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(activity, progressBar);

                        HideUtil.init(Activity_RestraPayment.this);
                        if (result != null) {
                            if (result.equals("invalid")) {
                                UtilView.showToast(activity, getResources().getString(R.string.accesstoken_error));
                                Intent intent = new Intent(activity, Activity_Login.class);
                                startActivity(intent);
                                finish();
                            } else {
                                try {
                                    JSONObject jsonObject = new JSONObject(result.toString());
                                    RestraSaveCheckoutData saveResponseData = gson.fromJson(jsonObject.toString(), RestraSaveCheckoutData.class);
                                    if (saveResponseData != null) {
                                        boolean printStatus = false;
                                        UtilView.showToast(Activity_RestraPayment.this, "Successfully");
                                        if (!printerType.equals("") && printerType.equals(getResources().getString(R.string.pos_3inch))) {
                                          //  printStatus = PrintReceipt.printInvoice3Inch(Activity_RestraPayment.this, saveResponseData);
                                        } else {
                                           // printStatus = PrintReceipt.printInvoice2Inch(Activity_RestraPayment.this, saveResponseData);
                                        }
                                        if (printStatus) {
                                           //BLUETOOTH_PRINTER.stop();
                                            Intent returnIntent = new Intent();
                                            setResult(Activity.RESULT_OK, returnIntent);
                                            finish();
                                        } else {

                                        }
                                       /* if (currentAPIVersion >= Build.VERSION_CODES.M) {
                                            checkPermission();
                                        } else {
                                            //new PdfGenerationTask().execute();
                                            createPdf();
                                        }*/

                                    } else {
                                        UtilView.showToast(Activity_RestraPayment.this, "Some Error. Please try Again.");
                                    }
                                    /*
                                    UtilView.showToast(Activity_PurchasePayment.this,"Payment Voucher Generate Successfully.");
                                        Intent returnIntent = new Intent();
                                        setResult(Activity.RESULT_OK, returnIntent);
                                        finish();*/


                                } catch (JSONException ex) {
                                    UtilView.showErrorDialog(getResources().getString(R.string.response_error), activity);
                                }
                            }
                        }
                    }
                }, false);
                UtilView.showLogCat(TAG,"Post Data Value "+gson.toJson(getCheckout).toString());
                postDataTask.execute(gson.toJson(getCheckout).toString(), url, "");
            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
            }
        } else {
            UtilView.gotToLogin(activity);
        }

    }

   /* private void checkPermission() {

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

            if (saveResponseData != null) {
                createPdf();
            }


        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (ActivityCompat.checkSelfPermission(this, permissions[0]) == PackageManager.PERMISSION_GRANTED) {
            switch (requestCode) {
                case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE:
                    if (saveResponseData != null) {
                        createPdf();
                    }
                    break;
            }


        } else {
            Toast.makeText(this, "Permisson Deny", Toast.LENGTH_LONG).show();
        }
    }

    private void createPdf() {
        File pdfFile = null;
        pdfFile = PdfUtils.createFile(saveResponseData.getOpNo() + ".pdf", Constant.DIRECTORY_GT, Constant.DIRECTORY_GTEXPENSE);


        if (pdfFile != null) {

            InputStream logoInputStream = PdfUtils.getLogoInputStream(this, progressBar);
            if (logoInputStream != null) {
                try {
                    GTExpenseVoucherPdf voucherPdf = new GTExpenseVoucherPdfImpl();
                    voucherPdf.generateExpenseVoucherPdf(activity, saveResponseData, new FileOutputStream(pdfFile), logoInputStream);

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101) {

            Intent returnIntent = new Intent();
            setResult(Activity.RESULT_OK, returnIntent);
            finish();

        }
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = activity.getMenuInflater();
        inflater.inflate(R.menu.menu_salespayment, menu);

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

            case R.id.resetConnection:

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
        BLUETOOTH_PRINTER.setHandler(new BluetoothHandler(Activity_RestraPayment.this));
    }

    /**
     * The Handler that gets information back from Bluetooth Devices
     */
    static class BluetoothHandler extends Handler {
        private final WeakReference<Activity_RestraPayment> myWeakReference;

        //Creating weak reference of BluetoothPrinterActivity class to avoid any leak
        BluetoothHandler(Activity_RestraPayment weakReference) {
            myWeakReference = new WeakReference<Activity_RestraPayment>(weakReference);
        }

        @Override
        public void handleMessage(Message msg) {
            Activity_RestraPayment bluetoothPrinterActivity = myWeakReference.get();
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
