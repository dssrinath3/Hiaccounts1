package in.hiaccounts.hinext.restaurant.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mocoo.hang.rtprinter.driver.Contants;
import com.mocoo.hang.rtprinter.driver.HsBluetoothPrintDriver;
import com.rey.material.widget.ProgressView;

import org.json.JSONArray;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.NavigationDrawer_Activity;
import in.hiaccounts.hinext.controlpanel.model.company_setup.compnayinformation.CompanyData;
import in.hiaccounts.hinext.restaurant.adapter.PrintListDailyReport_Adapter;
import in.hiaccounts.hinext.restaurant.model.category_item.DailyReportData;
import in.hiaccounts.hinext.restaurant.model.category_item.DailyReportItemData;
import in.hiaccounts.hinext.restaurant.model.category_item.ReportItemList;
import in.hiaccounts.hinext.sales.pos_printer.DeviceListActivity;
import in.hiaccounts.hinext.sales.pos_printer.PrintReceipt;
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

public class Activity_PrintList_DailyReport extends AppCompatActivity {
    public static String TAG = Activity_PrintList_DailyReport.class.getSimpleName();
    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 123;
    private static final int REQUEST_CONNECT_DEVICE = 1;
    private static final int REQUEST_ENABLE_BT = 2;
    @BindView(R.id.totalQty)
    TextView totalQty;
    @BindView(R.id.totalAmount)
    TextView totalAmount;
    private AlertDialog.Builder alertDlgBuilder;
    private static BluetoothDevice device;
    private BluetoothAdapter mBluetoothAdapter = null;
    public static HsBluetoothPrintDriver BLUETOOTH_PRINTER = null;
    private static TextView tvPrinterStatus = null;
    Intent serverIntent = null;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.lvInvoiceItems)
    ExpandableListView lvInvoiceItems;
    @BindView(R.id.btnCloseing)
    Button btnCloseing;
    @BindView(R.id.btnPrint)
    Button btnPrint;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.rlBottomLayout)
    RelativeLayout rlBottomLayout;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    private Activity activity;
    private ProgressDialog mProgressDialog;
    private ServiceHandler serviceHandler;
    private String serverUrl;
    Boolean isInternetPresent = false;
    PrintListDailyReport_Adapter adapter;
    List<DailyReportItemData> itemList = new ArrayList<DailyReportItemData>();
    private JSONArray jsonArray;
    private String printerType = "";
    DailyReportData dailyReportData;
    private boolean flagClose = false;
    private String paymentType = "";
    private SharedPreference sharedPreference;
    private GetDataTask getDataTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_printlist_dailyreport);
        ButterKnife.bind(this);
        initComponents();
    }

    private void initComponents() {
        ButterKnife.bind(this);
        activity = this;
        serverUrl = UtilView.getUrl(activity);
        serviceHandler = new ServiceHandler(activity);
        sharedPreference = SharedPreference.getInstance(activity);
        toolbar.setTitle(getResources().getString(R.string.menu_dialyreport));
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
        //btnCloseing.setVisibility(View.GONE);
        printerSetup();
        prepareListData();
    }

    @OnClick({R.id.btnCloseing, R.id.btnPrint, R.id.btn_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnCloseing:

                alertDlgBuilder = new AlertDialog.Builder(activity);
                alertDlgBuilder.setTitle("Closing Report");
                alertDlgBuilder.setMessage("Would you like to close the Accounts?");
                alertDlgBuilder.setNegativeButton(getResources().getString(R.string.alert_btn_negative), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }
                );
                alertDlgBuilder.setPositiveButton(getResources().getString(R.string.alert_btn_positive), new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                callClosingDialyReport();
                            }
                        }
                );
                alertDlgBuilder.show();


                break;
            case R.id.btnPrint:

                callDialyReportPrintData();


                break;
            case R.id.btn_cancel:
                finish();
                break;
        }
    }

    private void callDialyReportPrintData() {
        if (BLUETOOTH_PRINTER != null) {
            if (BLUETOOTH_PRINTER.IsNoConnection())
                BLUETOOTH_PRINTER.stop();
        }

        if (BLUETOOTH_PRINTER != null) {
            if (!BLUETOOTH_PRINTER.IsNoConnection()) {
                UtilView.showLogCat(TAG, "state " + BLUETOOTH_PRINTER.getState());
                if (BLUETOOTH_PRINTER.getState() == 3) {
                    flagClose = true;
                    try {
                        List<String> headerList = new ArrayList<>();
                        headerList.clear();
                        itemList.clear();
                        if (dailyReportData != null) {
                            if (dailyReportData.getItemList() != null && dailyReportData.getItemList().size() > 0) {
                                for (ReportItemList reportItemList : dailyReportData.getItemList()) {
                                    DailyReportItemData reportItemData = new DailyReportItemData();
                                    reportItemData.setItemId(reportItemList.getItemId());
                                    reportItemData.setItemName(reportItemList.getItemName());
                                    reportItemData.setItemCategoryName(reportItemList.getItemCategoryName());
                                    reportItemData.setItemQuantity(reportItemList.getQtySold());
                                    reportItemData.setItemTotalAmountInTax(reportItemList.getTotalAmtReceived());
                                    itemList.add(reportItemData);
                                }
                            }


                        }

                        if (itemList != null && itemList.size() > 0) {
                            for (DailyReportItemData category : itemList) {
                                headerList.add(category.getItemCategoryName());
                            }
                        }

                        List<String> newList = new ArrayList<String>(new HashSet<String>(headerList));

                        HashMap<String, List<DailyReportItemData>> childList = new HashMap<String, List<DailyReportItemData>>();
                        childList.clear();
                        if (newList != null && newList.size() > 0) {
                            for (String category : newList) {
                                List<DailyReportItemData> itemListData = new ArrayList<>();
                                for (int i = 0; i < itemList.size(); i++) {
                                    if (category.equals(itemList.get(i).getItemCategoryName())) {
                                        UtilView.showLogCat("@Flow", "Category in header list1 " + category + " ItemName  " + itemList.get(i).getItemName());
                                        itemListData.add(itemList.get(i));
                                    }
                                    childList.put(category, itemListData);
                                }
                            }
                        }


                        // using for-each loop for iteration over Map.entrySet()
                        for (Map.Entry<String, List<DailyReportItemData>> entry : childList.entrySet()) {
                            if (entry.getValue().size() == 0) {
                                headerList.remove(entry.getKey());
                            }
                        }

                        for (String category : headerList) {
                            UtilView.showLogCat("@Flow", "Category in header list2 " + category);
                        }

                        if (newList != null && newList.size() > 0 && childList != null && childList.size() > 0) {
                            callDailyPrinservices(dailyReportData, newList, childList);
                        }


                    } catch (Exception ex) {
                        //Log.e("ExceptionDailyReport",ex.toString());
                        UtilView.showErrorDialog(getResources().getString(response_error), activity);
                    }
                } else if (BLUETOOTH_PRINTER.getState() == 2) {


                } else {
                    UtilView.showToast(activity, "Please setup the printer connection first.");
                }

            } else {
                UtilView.showToast(activity, "Please setup the printer connection first.");
            }
        } else {
            UtilView.showToast(activity, "Please setup the printer connection first.");
        }
    }

    private void callDailyPrinservices(DailyReportData dailyReportData, List<String> headerList, HashMap<String, List<DailyReportItemData>> childList) {
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
                                makePrintDailyReport(bmp, headerList, childList, companyData, dailyReportData);
                            }
                        }, false);
                        task.execute(imageLogoUrl, "");

                    } else {
                        makePrintDailyReport(null, headerList, childList, companyData, dailyReportData);
                    }
                } else {
                    makePrintDailyReport(null, headerList, childList, companyData, dailyReportData);
                }
            }
        }
    }

    private void makePrintDailyReport(Bitmap bmp, List<String> headerList, HashMap<String, List<DailyReportItemData>> childList, CompanyData companyData, DailyReportData dailyReportData) {
        boolean printStatus = false;
        UtilView.showToast(activity, "Printed Successfully");
        String tableName = "NA";
        Log.e("printerTypeData", printerType);
        if (!printerType.equals("") && printerType.equals(getResources().getString(R.string.pos_3inch))) {
            printStatus = PrintReceipt.printDailyReport3Inch(activity, headerList, childList, bmp, tableName, companyData, dailyReportData, paymentType);
        } else {
            printStatus = PrintReceipt.printDailyReport2Inch(activity, headerList, childList, bmp, tableName, companyData, dailyReportData, paymentType);

        }
        if (printStatus) {

            //BLUETOOTH_PRINTER.stop();
            // Intent returnIntent = new Intent();
            // setResult(Activity.RESULT_OK, returnIntent);
            //finish();

        }
    }

    private void callClosingDialyReportPrintData() {
        if (BLUETOOTH_PRINTER != null) {
            if (BLUETOOTH_PRINTER.IsNoConnection())
                BLUETOOTH_PRINTER.stop();
        }

        if (BLUETOOTH_PRINTER != null) {
            if (!BLUETOOTH_PRINTER.IsNoConnection()) {
                UtilView.showLogCat(TAG, "state " + BLUETOOTH_PRINTER.getState());
                if (BLUETOOTH_PRINTER.getState() == 3) {
                    flagClose = true;
                    try {
                        List<String> headerList = new ArrayList<>();
                        headerList.clear();
                        itemList.clear();
                        if (dailyReportData != null) {
                            if (dailyReportData.getItemList() != null && dailyReportData.getItemList().size() > 0) {
                                for (ReportItemList reportItemList : dailyReportData.getItemList()) {
                                    DailyReportItemData reportItemData = new DailyReportItemData();
                                    reportItemData.setItemId(reportItemList.getItemId());
                                    reportItemData.setItemName(reportItemList.getItemName());
                                    reportItemData.setItemCategoryName(reportItemList.getItemCategoryName());
                                    reportItemData.setItemQuantity(reportItemList.getQtySold());
                                    reportItemData.setItemTotalAmountInTax(reportItemList.getTotalAmtReceived());
                                    itemList.add(reportItemData);
                                }
                            }


                        }

                        if (itemList != null && itemList.size() > 0) {
                            for (DailyReportItemData category : itemList) {
                                headerList.add(category.getItemCategoryName());
                            }
                        }

                        List<String> newList = new ArrayList<String>(new HashSet<String>(headerList));

                        HashMap<String, List<DailyReportItemData>> childList = new HashMap<String, List<DailyReportItemData>>();
                        childList.clear();
                        if (newList != null && newList.size() > 0) {
                            for (String category : newList) {
                                List<DailyReportItemData> itemListData = new ArrayList<>();
                                for (int i = 0; i < itemList.size(); i++) {
                                    if (category.equals(itemList.get(i).getItemCategoryName())) {
                                        UtilView.showLogCat("@Flow", "Category in header list1 " + category + " ItemName  " + itemList.get(i).getItemName());
                                        itemListData.add(itemList.get(i));
                                    }
                                    childList.put(category, itemListData);
                                }
                            }
                        }


                        // using for-each loop for iteration over Map.entrySet()
                        for (Map.Entry<String, List<DailyReportItemData>> entry : childList.entrySet()) {
                            if (entry.getValue().size() == 0) {
                                headerList.remove(entry.getKey());
                            }
                        }

                        for (String category : headerList) {
                            UtilView.showLogCat("@Flow", "Category in header list2 " + category);
                        }

                        if (newList != null && newList.size() > 0 && childList != null && childList.size() > 0) {
                            callPrinservices(newList, childList);
                        }


                    } catch (Exception ex) {
                        //Log.e("ExceptionDailyReport",ex.toString());
                        UtilView.showErrorDialog(getResources().getString(response_error), activity);
                    }
                } else if (BLUETOOTH_PRINTER.getState() == 2) {


                } else {
                    UtilView.showToast(activity, "Please setup the printer connection first.");
                }

            } else {
                UtilView.showToast(activity, "Please setup the printer connection first.");
            }
        } else {
            UtilView.showToast(activity, "Please setup the printer connection first.");
        }
    }

    private void callClosingDialyReport() {

        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
                if (progressBar != null)
                    progressBar.setVisibility(View.VISIBLE);
                String url = serverUrl + "/restaurant/" + Constant.FUNTION_RESTARUANTITEMDAILYREPORTLIST + "?dayEndStatus=true&fromDate=&toDate=";

                 getDataTask = new GetDataTask(activity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        if (progressBar != null)
                            progressBar.setVisibility(View.GONE);
                        if (result != null) {
                            callClosingDialyReportPrintData();
                            if (flagClose) {
                                UtilView.showToast(activity, "Today Account is cleared now.");
                                Intent returnIntent = new Intent();
                                setResult(Activity.RESULT_OK, returnIntent);
                                finish();
                            }

                        } else {
                            UtilView.showErrorDialog(getResources().getString(response_error), activity);
                        }
                    }

                }, false);
                getDataTask.execute(url, "");

            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
            }
        } else {
            UtilView.gotToLogin(activity);
        }
    }

    private void prepareListData() {

        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
                if (progressBar != null)
                    progressBar.setVisibility(View.VISIBLE);
                String url = serverUrl + "/restaurant/" + Constant.FUNTION_RESTARUANTITEMDAILYREPORTLIST;

                getDataTask = new GetDataTask(activity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        if (progressBar != null)
                            progressBar.setVisibility(View.GONE);
                        if (result != null) {
                            paymentType = result.toString();
                            Gson gson = new Gson();
                            try {
                                 Double grandTotalQty=0.00,grandTotalAmount=0.00,roundVal=0.00;
                                List<String> headerList = new ArrayList<>();
                                dailyReportData = gson.fromJson(result.toString(), DailyReportData.class);

                                if (dailyReportData != null) {
                                    if (dailyReportData.getItemList() != null && dailyReportData.getItemList().size() > 0) {
                                        for (ReportItemList reportItemList : dailyReportData.getItemList()) {
                                            DailyReportItemData reportItemData = new DailyReportItemData();
                                            reportItemData.setItemId(reportItemList.getItemId());
                                            reportItemData.setItemName(reportItemList.getItemName());
                                            reportItemData.setItemCategoryName(reportItemList.getItemCategoryName());
                                            reportItemData.setItemQuantity(reportItemList.getQtySold());
                                            reportItemData.setItemTotalAmountInTax(reportItemList.getTotalAmtReceived());
                                            itemList.add(reportItemData);
                                            Log.e("qty", String.valueOf(reportItemList.getQtySold()));
                                            Log.e("qty123", String.valueOf(reportItemList.getTotalAmtReceived()));
                                            grandTotalQty+=reportItemList.getQtySold();
                                            grandTotalAmount+=reportItemList.getTotalAmtReceived();
                                        }
                                    }


                                }
                                if (grandTotalQty!=null){
                                    totalQty.setText(""+grandTotalQty);
                                }
                                if (dailyReportData.getTotalAmount()!=null){
                                    totalAmount.setText(""+dailyReportData.getTotalAmount());

                                }
                                if (itemList != null && itemList.size() > 0) {
                                    for (DailyReportItemData category : itemList) {
                                        headerList.add(category.getItemCategoryName());
                                    }
                                }

                                List<String> newList = new ArrayList<String>(new HashSet<String>(headerList));

                                HashMap<String, List<DailyReportItemData>> childList = new HashMap<String, List<DailyReportItemData>>();

                                if (newList != null && newList.size() > 0) {
                                    for (String category : newList) {
                                        List<DailyReportItemData> itemListData = new ArrayList<>();
                                        for (int i = 0; i < itemList.size(); i++) {
                                            if (category.equals(itemList.get(i).getItemCategoryName())) {
                                                UtilView.showLogCat("@Flow", "Category in header list1 " + category + " ItemName  " + itemList.get(i).getItemName());
                                                itemListData.add(itemList.get(i));
                                            }
                                            childList.put(category, itemListData);
                                        }
                                    }

                                }


                                // using for-each loop for iteration over Map.entrySet()
                                for (Map.Entry<String, List<DailyReportItemData>> entry : childList.entrySet()) {
                                    if (entry.getValue().size() == 0) {
                                        headerList.remove(entry.getKey());
                                    }
                                }

                                for (String category : headerList) {
                                    UtilView.showLogCat("@Flow", "Category in header list2 " + category);
                                }

                                if (newList != null && newList.size() > 0 && childList != null && childList.size() > 0) {
                                    adapter = new PrintListDailyReport_Adapter(activity, newList, childList);
                                    lvInvoiceItems.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();

                                }



                            } catch (Exception ex) {
                                Log.e("ExceptionDailyReport", ex.toString());
                                //UtilView.showErrorDialog(getResources().getString(response_error), activity);
                            }

                        } else {
                            UtilView.showErrorDialog(getResources().getString(response_error), activity);
                        }
                    }

                }, false);
                getDataTask.execute(url, "");

            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
            }
        } else {
            UtilView.gotToLogin(activity);
        }

    }


    private void callPrinservices(List<String> headerList, HashMap<String, List<DailyReportItemData>> childList) {

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
                                makePrint(bmp, headerList, childList, companyData);
                            }
                        }, false);
                        task.execute(imageLogoUrl, "");

                    } else {
                        makePrint(null, headerList, childList, companyData);
                    }
                } else {
                    makePrint(null, headerList, childList, companyData);
                }
            }
        }

    }

    private void makePrint(Bitmap bmp, List<String> headerList, HashMap<String, List<DailyReportItemData>> childList, CompanyData companyData) {
        boolean printStatus = false;
        UtilView.showToast(activity, "Printed Successfully");
        String tableName = "NA";
        if (!printerType.equals("") && printerType.equals(getResources().getString(R.string.pos_3inch))) {
            printStatus = PrintReceipt.printCloseDailyReport3Inch(activity, headerList, childList, bmp, tableName, companyData);
        } else {
            printStatus = PrintReceipt.printCloseDailyReport2Inch(activity, headerList, childList, bmp, tableName, companyData);

        }
        if (printStatus) {

            //BLUETOOTH_PRINTER.stop();
            // Intent returnIntent = new Intent();
            // setResult(Activity.RESULT_OK, returnIntent);
            //finish();

        }
    }

    private void printerSetup() {


        tvPrinterStatus.setVisibility(View.VISIBLE);
        // Get device's Bluetooth adapter
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        // If the adapter is null, then Bluetooth is not available in your device
        if (mBluetoothAdapter == null) {
            UtilView.showToast(activity, "Bluetooth is not available");
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
                        if (device != null) {
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
        MenuInflater inflater = activity.getMenuInflater();
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
                                        BLUETOOTH_PRINTER.stop();
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
       /* if (BLUETOOTH_PRINTER != null) {
            if (BLUETOOTH_PRINTER.IsNoConnection())
                BLUETOOTH_PRINTER.stop();
        }
        activity.finish();*/
        if(getDataTask != null) {
            getDataTask.cancel(true);
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
        BLUETOOTH_PRINTER.setHandler(new BluetoothHandler(Activity_PrintList_DailyReport.this));

    }

    /**
     * The Handler that gets information back from Bluetooth Devices
     */
    static class BluetoothHandler extends Handler {
        private final WeakReference<Activity_PrintList_DailyReport> myWeakReference;

        //Creating weak reference of BluetoothPrinterActivity class to avoid any leak
        BluetoothHandler(Activity_PrintList_DailyReport weakReference) {
            myWeakReference = new WeakReference<Activity_PrintList_DailyReport>(weakReference);
        }

        @Override
        public void handleMessage(Message msg) {
            Activity_PrintList_DailyReport bluetoothPrinterActivity = myWeakReference.get();
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
                                if (device != null)
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

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG, "++ ON START ++");
        Log.e("onStart", "onStart");
        //in = new Intent();

        try {

            try {

                String kotaddress = sharedPreference.getData(Constant.BLUETOOTHADDRESSBILL);
                String printerTypekot1 = sharedPreference.getData(Constant.BLUETOOTHTYPEBILL);
                Log.e("printerTypekot1", printerTypekot1);
                if (printerTypekot1 != null && printerTypekot1.equals("2-inch")) {
                    printerType = "2-inch";
                } else {
                    printerType = "3-inch";
                }
                Log.e("singlekotaddress", kotaddress);
                device = mBluetoothAdapter.getRemoteDevice(kotaddress);
                BLUETOOTH_PRINTER.start();
                BLUETOOTH_PRINTER.connect(device);
            } catch (Exception e) {
                Log.e("kotprinter excption", e.toString());
            }

        } catch (Exception e) {
            Log.e("Printer error", e.toString());
        }

    }
}
