package in.hiaccounts.hinext.restaurant.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lowagie.text.DocumentException;
import com.mocoo.hang.rtprinter.driver.Contants;
import com.mocoo.hang.rtprinter.driver.HsBluetoothPrintDriver;
import com.rey.material.widget.ProgressView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.application.activity.NavigationDrawer_Activity;
import in.hiaccounts.hinext.controlpanel.model.company_setup.compnayinformation.CompanyData;
import in.hiaccounts.hinext.purchase.model.purchase_saveresponses.Checkout_ResponseData;
import in.hiaccounts.hinext.restaurant.adapter.PrintList_Invoice_Adapter;
import in.hiaccounts.hinext.restaurant.model.PrintlistInvoiceData;
import in.hiaccounts.hinext.restaurant.model.checkout.DialyReportData;
import in.hiaccounts.hinext.restaurant.model.checkout.RestraSaveCheckoutData;
import in.hiaccounts.hinext.sales.pos_printer.DeviceListActivity;
import in.hiaccounts.hinext.sales.pos_printer.PrintReceipt;
import in.hiaccounts.pdfgenerator.PdfUtils;
import in.hiaccounts.pdfgenerator.sales.salesinvoice.PosA4SalesInvociePdfImpl;
import in.hiaccounts.pdfgenerator.sales.salesinvoice.PosSalesInvoicePdf;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.AsyncTaskCompleteListener1;
import in.hiaccounts.task.GetCompanyLogoTask;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;

import static in.hiaccounts.R.string.response_error;

public class Activity_PrintList extends AppCompatActivity implements TextWatcher {
    private static String TAG = Activity_PrintList.class.getSimpleName();


    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 123;
    private AlertDialog.Builder alertDlgBuilder;
    private static BluetoothDevice device;
    private static final int REQUEST_CONNECT_DEVICE = 1;
    private static final int REQUEST_ENABLE_BT = 2;
    private BluetoothAdapter mBluetoothAdapter = null;
    public static HsBluetoothPrintDriver BLUETOOTH_PRINTER = null;
    private static TextView tvPrinterStatus = null;
    Intent serverIntent = null;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edSearch)
    EditText edSearch;
    @BindView(R.id.imgviewSearch)
    ImageView imgviewSearch;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.tv_formno)
    TextView tvFormno;
    @BindView(R.id.tv_customername)
    TextView tvCustomername;
    @BindView(R.id.tv_totalamount)
    TextView tvTotalamount;
    @BindView(R.id.tv_itemtotalamount)
    TextView tvItemtotalamount;
    @BindView(R.id.ll_listtitle)
    LinearLayout llListtitle;
    @BindView(R.id.listviewInvoices)
    ListView listviewInvoices;
    @BindView(R.id.llListView)
    LinearLayout llListView;
    @BindView(R.id.rlContent)
    RelativeLayout rlContent;
    @BindView(R.id.dailyReport)
    LinearLayout dailyReport;
    @BindView(R.id.btnReport)
    Button btnReport;


    @BindView(R.id.progress_bar)
    ProgressView progressBar;

    private PrintList_Invoice_Adapter adapter;
    private ArrayList<PrintlistInvoiceData> invoiceList = new ArrayList<>();
    private PrintlistInvoiceData invData = new PrintlistInvoiceData();

    private Activity mActivity;
    private Boolean isInternetPresent = false;
    private ServiceHandler serviceHandler;
    private String serverUrl, invoiceSearch = "", formno = "", printerType = "";
    private String dialyReport = "";
    private SharedPreference sharedPreference;
    private GetDataTask getDataTask;
    private PostDataTask postDataTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_printlist);
        ButterKnife.bind(this);
        initComponents();

    }

    private void initComponents() {
        ButterKnife.bind(this);
        mActivity = this;
        edSearch.addTextChangedListener(this);
        serverUrl = UtilView.getUrl(mActivity);
        serviceHandler = new ServiceHandler(mActivity);
        sharedPreference = SharedPreference.getInstance(mActivity);
        toolbar.setTitle(getResources().getString(R.string.menu_invoice));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvPrinterStatus = (TextView) findViewById(R.id.tvPrinterStatus);
        tvPrinterStatus = (TextView) findViewById(R.id.tvPrinterStatus1);
        printerSetup();

        dialyReport = getIntent().getStringExtra("DailyReport");
        if (dialyReport.equals("DailyReport")) {

            if (rlContent != null) {
                rlContent.setVisibility(View.INVISIBLE);
                dailyReport.setVisibility(View.VISIBLE);

            }


        } else if(dialyReport.equals("printList")){
            rlContent.setVisibility(View.VISIBLE);
            dailyReport.setVisibility(View.INVISIBLE);
            getInvoiceListitem("");
        }


        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    invoiceSearch = edSearch.getText().toString().trim();
                    getInvoiceListitem(invoiceSearch);
                }
                return handled;
            }
        });

        listviewInvoices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                invData = invoiceList.get(position);
                formno = invData.getFormNo();
                id = invData.getId();
                if (view.getId() == R.id.imgview_duplicateprint) {

                    if (BLUETOOTH_PRINTER != null) {
                        if (!BLUETOOTH_PRINTER.IsNoConnection()) {
                         /*   if(device!=null){
                                BLUETOOTH_PRINTER.WakeUpPritner();
                                BLUETOOTH_PRINTER.connect(device);

                            }*/
                            UtilView.showLogCat(TAG, "state " + BLUETOOTH_PRINTER.getState());
                            if (BLUETOOTH_PRINTER.getState() == 3) {
                                callServerPrint(formno);
                            } else if (BLUETOOTH_PRINTER.getState() == 2) {


                            } else {
                                UtilView.showToast(mActivity, "Please setup the printer connection first.");
                            }

                        } else {
                            UtilView.showToast(mActivity, "Please setup the printer connection first.");
                        }
                    } else {
                        UtilView.showToast(mActivity, "Please setup the printer connection first.");
                    }

                }
                if (view.getId() == R.id.imgviewCancel) {
                    initCancelDialog(id);
                }
               /* if (view.getId() == R.id.imgviewCancelDraft) {
                    initCancelDraftDialog(id);
                }*/


            }
        });
    }

/*    private void setUpPrinterConnection() {
        if (BLUETOOTH_PRINTER != null) {
            if (!BLUETOOTH_PRINTER.IsNoConnection()) {
                         *//*   if(device!=null){
                                BLUETOOTH_PRINTER.WakeUpPritner();
                                BLUETOOTH_PRINTER.connect(device);

                            }*//*
                UtilView.showLogCat(TAG, "state " + BLUETOOTH_PRINTER.getState());
                if (BLUETOOTH_PRINTER.getState() == 3) {
                    printDialyReport();
                } else if (BLUETOOTH_PRINTER.getState() == 2) {


                } else {
                    UtilView.showToast(mActivity, "Please setup the printer connection first.");
                }

            } else {
                UtilView.showToast(mActivity, "Please setup the printer connection first.");
            }
        } else {
            UtilView.showToast(mActivity, "Please setup the printer connection first.");
        }
    }*/

    private void printDialyReport() {

        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {

                if (progressBar != null)
                    progressBar.setVisibility(View.VISIBLE);
                String url = serverUrl + "/restaurant/" + Constant.FUNTION_RESTARUANTDIALYREPORT + "?dayEndStatus=false&fromDate=&toDate=";

                getDataTask = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        if (progressBar != null)
                            progressBar.setVisibility(View.GONE);
                        if (result != null) {
                            Gson gson = new Gson();
                            try {
                                if (result.equals("invalid")) {
                                    UtilView.showToast(mActivity, getResources().getString(R.string.accesstoken_error));
                                    Intent intent = new Intent(mActivity, Activity_Login.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    try {
                                        JSONObject jsonObject = new JSONObject(result.toString());
                                        final RestraSaveCheckoutData saveResponseData = gson.fromJson(jsonObject.toString(), RestraSaveCheckoutData.class);
                                        if (saveResponseData != null) {
                                            callPrintDialyReportservices(saveResponseData);

                                        }

                                    } catch (JSONException ex) {
                                        UtilView.showErrorDialog(getResources().getString(R.string.response_error), mActivity);
                                    }
                                }

                            } catch (Exception e) {
                                UtilView.showErrorDialog(getResources().getString(R.string.response_error), mActivity);
                            }




                        } else {
                            UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                        }
                    }

                }, false);
                getDataTask.execute(url, "");
            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
            }
        } else {
            UtilView.gotToLogin(mActivity);
        }
    }

    private void callPrintDialyReportservices(final RestraSaveCheckoutData saveResponseData) {
        if (saveResponseData != null) {
            SharedPreference sharedPreference = SharedPreference.getInstance(mActivity);
            if (sharedPreference != null) {

                String applicationDataJson = sharedPreference.getData(Constant.COMPANYDATA);


                if (applicationDataJson != null) {
                    Gson gson = new Gson();
                    String serverUrl = UtilView.getUrl(mActivity);

                    CompanyData companyData = gson.fromJson(applicationDataJson, CompanyData.class);
                    if (companyData != null) {

                        makeDialyReportPrint(null, saveResponseData);

                    }
                }
            }


        }
    }

    private void makeDialyReportPrint(Bitmap bmp, RestraSaveCheckoutData saveResponseData) {
        boolean printStatus = false;
        UtilView.showToast(Activity_PrintList.this, "Successfully");
        if (!printerType.equals("") && printerType.equals(getResources().getString(R.string.pos_3inch))) {
            printStatus = PrintReceipt.printInvoiceDialyReport3Inch(Activity_PrintList.this, saveResponseData, bmp);
        } else {
              printStatus = PrintReceipt.printInvoicePrintlist2Inch(Activity_PrintList.this, saveResponseData, bmp);

        }
        if (printStatus) {
            //BLUETOOTH_PRINTER.stop();
            Intent returnIntent = new Intent();
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }
    }

    private void callServerPrint(String formno) {

        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
                UtilView.showProgessBar(mActivity, progressBar);
                String url = serverUrl + "/retail//" + Constant.FUNTION_GETDUPLICATEINVOICE + "?invoiceNo=" + formno;
                getDataTask = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(mActivity, progressBar);
                        Gson gson = new Gson();
                        try {
                            if (result.equals("invalid")) {
                                UtilView.showToast(mActivity, getResources().getString(R.string.accesstoken_error));
                                Intent intent = new Intent(mActivity, Activity_Login.class);
                                startActivity(intent);
                                finish();
                            } else {
                                //try {
                                    JSONObject jsonObject = new JSONObject(result.toString());
                                     RestraSaveCheckoutData saveResponseData = gson.fromJson(jsonObject.toString(), RestraSaveCheckoutData.class);
                                    if (saveResponseData != null) {
                                        callPrinservices(saveResponseData);

                                    }

                                /*} catch (JSONException ex) {
                                    UtilView.showErrorDialog(getResources().getString(R.string.response_error), mActivity);
                                }*/
                            }

                        } catch (Exception e) {
                            Log.e("PrintListException",e.toString());
                           // UtilView.showErrorDialog(getResources().getString(R.string.response_error), mActivity);
                        }
                    }
                }, false);
                getDataTask.execute(url, "");
            } else {
                UtilView.showToast(mActivity, getResources().getString(R.string.intertnet_status));
            }
        } else {
            UtilView.gotToLogin(mActivity);
        }
    }

    private void callPrinservices(final RestraSaveCheckoutData saveResponseData) {
        if (saveResponseData != null) {
            SharedPreference sharedPreference = SharedPreference.getInstance(mActivity);
            if (sharedPreference != null) {

                String applicationDataJson = sharedPreference.getData(Constant.COMPANYDATA);
                String imageLogoUrl;

                if (applicationDataJson != null) {
                    Gson gson = new Gson();
                    String serverUrl = UtilView.getUrl(mActivity);

                    CompanyData companyData = gson.fromJson(applicationDataJson, CompanyData.class);
                    if (companyData != null) {
                        if (companyData.getFileName() != null) {
                            imageLogoUrl = serverUrl + companyData.getFileName();
                            GetCompanyLogoTask task = new GetCompanyLogoTask(mActivity, new AsyncTaskCompleteListener1<Bitmap>() {
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
        Log.e("printerData",printerType);
        if (!printerType.equals("") && printerType.equals(getResources().getString(R.string.pos_3inch))) {
            printStatus = PrintReceipt.printInvoicePrintlist3Inch(Activity_PrintList.this, saveResponseData, bmp);
        } else {
            printStatus = PrintReceipt.printInvoicePrintlist2Inch(Activity_PrintList.this, saveResponseData, bmp);

        }
        if (printStatus) {
            UtilView.showToast(Activity_PrintList.this, "Successfully");
            //BLUETOOTH_PRINTER.stop();
          /*  Intent returnIntent = new Intent();
            setResult(Activity.RESULT_OK, returnIntent);
            finish();*/
        }

    }


    private void printerSetup() {


        tvPrinterStatus.setVisibility(View.VISIBLE);
        // Get device's Bluetooth adapter
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        // If the adapter is null, then Bluetooth is not available in your device
        if (mBluetoothAdapter == null) {
            UtilView.showToast(mActivity, "Bluetooth is not available");
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
                        if (device!=null){
                            tvPrinterStatus.append(device.getName());
                        }


                    }
                }

            }
        } else {
            tvPrinterStatus.setText(R.string.bluettoth_doesnt);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = mActivity.getMenuInflater();
        inflater.inflate(R.menu.menu_salespayment, menu);
        MenuItem printDisConnect = menu.findItem(R.id.resetConnection);
        printDisConnect.setVisible(false);
        return super.onCreateOptionsMenu(menu);


    }

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
                        serverIntent = new Intent(mActivity, DeviceListActivity.class);
                        startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);
                    } else { //If an existing connection is still alive then ask user to kill it and re-connect again
                        alertDlgBuilder = new AlertDialog.Builder(mActivity);
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
                                        sharedPreference.setRemovePrefrence(Constant.BLUETOOTHADDRESS);
                                        sharedPreference.setRemovePrefrence(Constant.BLUETOOTHTYPE);
                                        serverIntent = new Intent(mActivity, DeviceListActivity.class);
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
       /* if (BLUETOOTH_PRINTER != null) {
            if (BLUETOOTH_PRINTER.IsNoConnection())
                BLUETOOTH_PRINTER.stop();
        }
        mActivity.finish();*/
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
        BLUETOOTH_PRINTER.setHandler(new BluetoothHandler(Activity_PrintList.this));

    }

    /**
     * The Handler that gets information back from Bluetooth Devices
     */
    static class BluetoothHandler extends Handler {
        private final WeakReference<Activity_PrintList> myWeakReference;

        //Creating weak reference of BluetoothPrinterActivity class to avoid any leak
        BluetoothHandler(Activity_PrintList weakReference) {
            myWeakReference = new WeakReference<Activity_PrintList>(weakReference);
        }

        @Override
        public void handleMessage(Message msg) {
            Activity_PrintList bluetoothPrinterActivity = myWeakReference.get();
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
                                if (device!=null)
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


    private void initCancelDraftDialog(final long id) {
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (isInternetPresent) {
            SweetAlertDialog pDialog = new SweetAlertDialog(mActivity, SweetAlertDialog.WARNING_TYPE);
            pDialog.setTitleText(mActivity.getString(R.string.cancelwarningmsg));
            pDialog.setConfirmText(mActivity.getString(R.string.yesCancel));
            pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(final SweetAlertDialog sDialog) {
                    final SweetAlertDialog pDialog = new SweetAlertDialog(mActivity, SweetAlertDialog.PROGRESS_TYPE);
                    pDialog.getProgressHelper().setBarColor(mActivity.getResources().getColor(R.color.colorPrimary));
                    pDialog.setTitleText(mActivity.getString(R.string.pleasewait));
                    pDialog.setCancelable(false);
                    if (pDialog != null)
                        pDialog.show();


                    JSONObject obj = new JSONObject();
                    try {
                        obj.put("id", id);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    String url = "";

                    url = serverUrl + "/retail//" + Constant.FUNTION_SALESCANCELDRAFTINVOICE;
                     postDataTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                        @Override
                        public void onTaskComplete(String result) {
                            if (pDialog != null)
                                pDialog.dismiss();

                            if (result != null) {
                                if (result.equals("")) {
                                    if (sDialog != null) {
                                        sDialog.setTitleText(mActivity.getString(R.string.delconfirm))
                                                            /*.setContentText("Category "+category.getItemCategoryName())*/
                                                .setConfirmText(mActivity.getString(R.string.bntok))
                                                .setConfirmClickListener(null)
                                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);

                                        adapter.notifyDataSetChanged();


                                    }
                                    getInvoiceListitem("");

                                } else {
                                    if (sDialog != null) {
                                        sDialog.setTitleText(mActivity.getString(R.string.delconfirmDraft))
                                                            /*.setContentText("Category "+category.getItemCategoryName())*/
                                                .setConfirmText(mActivity.getString(R.string.bntok))
                                                .setConfirmClickListener(null)
                                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);

                                    }
                                    getInvoiceListitem("");
                                }
                            } else {

                                UtilView.showToast(mActivity, mActivity.getResources().getString(R.string.response_error));
                            }
                        }
                    }, false);
                    postDataTask.execute(obj.toString(), url, Constant.FUNTION_ADVANCEPARTIALPAYMENT);

                }
            });
            if (pDialog != null)
                pDialog.show();
        } else {
            UtilView.showErrorDialog(mActivity.getResources().getString(R.string.intertnet_status), mActivity);
        }
    }

    private void initCancelDialog(final Long id) {
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (isInternetPresent) {
            SweetAlertDialog pDialog = new SweetAlertDialog(mActivity, SweetAlertDialog.WARNING_TYPE);
            pDialog.setTitleText(mActivity.getString(R.string.cancelwarningmsg));
            pDialog.setConfirmText(mActivity.getString(R.string.yesCancel));
            pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(final SweetAlertDialog sDialog) {
                    final SweetAlertDialog pDialog = new SweetAlertDialog(mActivity, SweetAlertDialog.PROGRESS_TYPE);
                    pDialog.getProgressHelper().setBarColor(mActivity.getResources().getColor(R.color.colorPrimary));
                    pDialog.setTitleText(mActivity.getString(R.string.pleasewait));
                    pDialog.setCancelable(false);
                    if (pDialog != null)
                        pDialog.show();


                    JSONObject obj = new JSONObject();
                    try {
                        obj.put("id", id);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    String url = "";

                    url = serverUrl + "/retail//" + Constant.FUNTION_SALESINVOICECANCEL;
                     postDataTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                        @Override
                        public void onTaskComplete(String result) {
                            if (pDialog != null)
                                pDialog.dismiss();

                            if (result.toString() != null) {
                                if (result.toString().trim().equals("Cancelled SuccessFully")) {
                                    if (sDialog != null) {
                                        sDialog.setTitleText(mActivity.getString(R.string.delconfirm))
                                                            /*.setContentText("Category "+category.getItemCategoryName())*/
                                                .setConfirmText(mActivity.getString(R.string.bntok))
                                                .setConfirmClickListener(null)
                                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);

                                        adapter.notifyDataSetChanged();


                                    }
                                    getInvoiceListitem("");

                                } else {
                                    if (sDialog != null) {
                                        sDialog.setTitleText(mActivity.getString(R.string.delfail))
                                                            /*.setContentText("Category "+category.getItemCategoryName())*/
                                                .setConfirmText(mActivity.getString(R.string.bntok))
                                                .setConfirmClickListener(null)
                                                .changeAlertType(SweetAlertDialog.ERROR_TYPE);

                                    }
                                    getInvoiceListitem("");
                                }
                            } else {

                                UtilView.showToast(mActivity, mActivity.getResources().getString(R.string.response_error));
                            }
                        }
                    }, false);
                    postDataTask.execute(obj.toString(), url, Constant.FUNTION_ADVANCEPARTIALPAYMENT);

                }
            });
            if (pDialog != null)
                pDialog.show();
        } else {
            UtilView.showErrorDialog(mActivity.getResources().getString(R.string.intertnet_status), mActivity);
        }
    }


    private void getInvoiceListitem(String search) {
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
                if (progressBar != null)
                    progressBar.setVisibility(View.VISIBLE);

                String url = "";
                if (search.equals("")) {
                    url = serverUrl + "/retail//" + Constant.FUNTION_GETINVOICES + "?itemSearchText=&type=&locationId=";

                }

                if (!search.equals("")) {
                    url = serverUrl + "/retail//" + Constant.FUNTION_GETINVOICES + "?itemSearchText=" + search.replace(" ", "%20") + "&type=&locationId=";

                }

                getDataTask = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        if (progressBar != null)
                            progressBar.setVisibility(View.GONE);
                        if (result != null) {
                            Gson gson = new Gson();
                            invoiceList = new ArrayList<PrintlistInvoiceData>();
                            try {
                                JSONArray jsonArray = new JSONArray(result.toString());
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonInovice = jsonArray.getJSONObject(i);
                                    PrintlistInvoiceData inovice = gson.fromJson(jsonInovice.toString(), PrintlistInvoiceData.class);
                                    invoiceList.add(inovice);
                                }
                                if (invoiceList != null && invoiceList.size() > 0) {

                                    llListView.setVisibility(View.VISIBLE);
                                    adapter = new PrintList_Invoice_Adapter(mActivity, invoiceList);
                                    listviewInvoices.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                } else {
                                    llListView.setVisibility(View.GONE);

                                    adapter = new PrintList_Invoice_Adapter(mActivity, invoiceList);
                                    listviewInvoices.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                    UtilView.showSuccessDialog("No Invoice Available", mActivity);
                                }
                            } catch (Exception ex) {
                                UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                        }
                    }

                }, false);
                getDataTask.execute(url, "");
            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
            }
        } else {
            UtilView.gotToLogin(mActivity);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (editable.toString().equals("")) {
            UtilView.hideSoftKeyboard(mActivity, edSearch);
            invoiceSearch = "";
            getInvoiceListitem(invoiceSearch);
        }
    }


    @OnClick({R.id.imgviewSearch, R.id.btnReport})
    public void onViewClicked(View view) {
        switch (view.getId()) {


            case R.id.imgviewSearch:
                invoiceSearch = edSearch.getText().toString().trim();
                getInvoiceListitem(invoiceSearch);
                break;
            case R.id.btnReport:
                if (BLUETOOTH_PRINTER != null) {
                    if (!BLUETOOTH_PRINTER.IsNoConnection()) {
                         /*   if(device!=null){
                                BLUETOOTH_PRINTER.WakeUpPritner();
                                BLUETOOTH_PRINTER.connect(device);

                            }*/
                        UtilView.showLogCat(TAG, "state " + BLUETOOTH_PRINTER.getState());


                        if (BLUETOOTH_PRINTER.getState() == 3) {
                            printDialyReport();
                        } else if (BLUETOOTH_PRINTER.getState() == 2) {


                        } else {
                            UtilView.showToast(mActivity, "Please setup the printer connection first.");
                        }

                    } else {
                        UtilView.showToast(mActivity, "Please setup the printer connection first.");
                    }
                }

                break;

        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG, "++ ON START ++");
        Log.e("onStart", "onStart");
        //in = new Intent();

        try {

                String kotaddress = sharedPreference.getData(Constant.BLUETOOTHADDRESSBILL);
                String printerTypekot1 = sharedPreference.getData(Constant.BLUETOOTHTYPEBILL);
                Log.e("printerTypekot1",printerTypekot1);
                if (printerTypekot1!=null && printerTypekot1.equals("2-inch")){
                    printerType = "2-inch";
                }else {
                    printerType = "3-inch";
                }
                Log.e("singlekotaddress",kotaddress);
                device = mBluetoothAdapter.getRemoteDevice(kotaddress);
                BLUETOOTH_PRINTER.start();
                BLUETOOTH_PRINTER.connect(device);



        } catch (Exception e) {
            Log.e("Printer error", e.toString());
        }

    }
}
