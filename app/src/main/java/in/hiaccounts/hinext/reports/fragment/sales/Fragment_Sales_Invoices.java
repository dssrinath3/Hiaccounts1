package in.hiaccounts.hinext.reports.fragment.sales;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.hiaccounts.R;
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.employee.EmployeeList;
import in.hiaccounts.hinext.crm.activity.Activity_ShowEmployeeList;
import in.hiaccounts.hinext.customer.activity.Activity_Customer;
import in.hiaccounts.hinext.customer.model.Customer;
import in.hiaccounts.hinext.reports.adapter.salesreport.SalesReportInvoiceRegisterAdapter;
import in.hiaccounts.hinext.reports.adapter.salesreport.SalesReportItemCommisionAdapter;
import in.hiaccounts.hinext.reports.adapter.salesreport.SalesReportOrderDetailsAdapter;
import in.hiaccounts.hinext.reports.model.CurrencyList;
import in.hiaccounts.hinext.reports.model.CustomerList;
import in.hiaccounts.hinext.reports.model.LocationList;
import in.hiaccounts.hinext.reports.model.SalesList;
import in.hiaccounts.hinext.reports.model.SalesReportData;
import in.hiaccounts.hinext.reports.model.SalesReportList;
import in.hiaccounts.hinext.reports.model.SalesReportQuotationList;
import in.hiaccounts.hinext.reports.model.restuarant.CompanyDetails;
import in.hiaccounts.hinext.reports.model.sales.Sales_ItemCommision_Report;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;

import static in.hiaccounts.R.string.response_error;

/**
 * Created by Prateek on 2/9/2017.
 */

public class Fragment_Sales_Invoices extends Fragment {


    public static String TAG = Fragment_Sales_Invoices.class.getSimpleName();
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.idFrom)
    TextView idFrom;
    @BindView(R.id.fromDate)
    EditText edFromDate;
    @BindView(R.id.idTo)
    TextView idTo;
    @BindView(R.id.toDate)
    EditText edToDate;
    @BindView(R.id.idCurrency)
    TextView idCurrency;
    @BindView(R.id.currency)
    Spinner spinCurrency;
    @BindView(R.id.fromInvoiceNo)
    Spinner spinFromInvoiceNo;
    @BindView(R.id.toInvoiceNo)
    Spinner spinToInvoiceNo;
    @BindView(R.id.idLocation)
    TextView idLocation;
    @BindView(R.id.location)
    Spinner spinLocation;
    @BindView(R.id.edCustomerName)
    EditText edCustomerName;
    @BindView(R.id.edEmployeeName)
    EditText edEmployeeName;
    @BindView(R.id.spinStatus)
    Spinner spinStatus;
    @BindView(R.id.searchView)
    Button searchView;
    @BindView(R.id.salesOrderListview)
    ListView salesOrderListview;
    @BindView(R.id.grandTotalAmount)
    TextView grandTotalAmount;
    @BindView(R.id.tvFirst)
    TextView tvFirst;
    @BindView(R.id.tvPrev)
    TextView tvPrev;
    @BindView(R.id.tvPageNo)
    TextView tvPageNo;
    @BindView(R.id.tvNext)
    TextView tvNext;
    @BindView(R.id.tvLast)
    TextView tvLast;
    @BindView(R.id.llPaginationView)
    LinearLayout llPaginationView;
    Unbinder unbinder;
    private Activity activity;
    private SharedPreference sharedPreference;
    private String serverUrl,itemStatus;
    private ServiceHandler serviceHandler;
    private boolean isInternetPresent = false;
    private String frmDate = "", toDate = "";
    private List<Object> locationObjectList = new ArrayList<>();
    private List<Object> fromObjectList = new ArrayList<>();
    private List<Object> toObjectList = new ArrayList<>();
    private List<Object> currencyObjectList = new ArrayList<>();
    private List<Object> statusObjectList = new ArrayList<>();
    private LocationList locationListData = null;
    private SalesList salesListData = null;
    private CurrencyList currencyListData = null;
    private List<CustomerList> customerList = new ArrayList<>();
    Customer selected_customer;
    private Long customerId, sFromId, sToId, locationId, currencyId,itemId;
    private Long pageNo = 0L;
    private EmployeeList empList;
    private Long empId;
    private String itemName="",empName="",status="";
    private SalesReportInvoiceRegisterAdapter adapter;
    private List<SalesReportList> dataList;

    public static Fragment_Sales_Invoices newInstance() {
        Fragment_Sales_Invoices fragment = new Fragment_Sales_Invoices();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reportsales_invoices, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponentView();
        return view;
    }

    private void initComponentView() {
        sharedPreference = SharedPreference.getInstance(activity);
        serverUrl = UtilView.getUrl(activity);
        serviceHandler = new ServiceHandler(activity);
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        SimpleDateFormat df3 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        String formattedDate3 = df3.format(calendar.getTime());

        calendar.set(year, month, day);
        frmDate = df3.format(calendar.getTime());
        toDate = df3.format(calendar.getTime());
        ;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date d = null;
        try {
            d = df3.parse(frmDate);
            edFromDate.setText(dateFormat.format(d));
            edToDate.setText(dateFormat.format(d));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String companyData = sharedPreference.getData(Constant.COMPANYDATA);

        if (companyData!=null){
            Gson gson = new Gson();
            CompanyDetails companyDetails = gson.fromJson(companyData.toString(),CompanyDetails.class);
            if (companyDetails!=null){
                Log.e("companyData", String.valueOf(companyDetails.getStartyear()));
                DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

                long milliSeconds= Long.parseLong(String.valueOf(companyDetails.getStartyear()));

                Calendar calen = Calendar.getInstance();
                calen.setTimeInMillis(milliSeconds);
                Log.e("companyDate",formatter.format(calen.getTime()));
                edFromDate.setText(formatter.format(calen.getTime()));

                DateFormat simple = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                Date result = new Date(milliSeconds);
                frmDate = simple.format(result);
            }
        }
        callOnLoadPageData();

        LayoutInflater inflater = activity.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.report_sales_invoice_register_view, null, false);
        TextView tvSalesDate = header.findViewById(R.id.tvSalesDate);
        TextView tvSalesInvoiceNo = header.findViewById(R.id.tvSalesInvoiceNo);
        TextView tvCustomerName = header.findViewById(R.id.tvCustomerName);
        TextView tvInvoiceAmount = header.findViewById(R.id.tvInvoiceAmount);
        TextView tvCurrency = header.findViewById(R.id.tvCurrency);
        TextView tvStatus = header.findViewById(R.id.tvStatus);
        tvSalesDate.setText("Inv Date");
        tvSalesInvoiceNo.setText("Sales Invoice No");
        tvCustomerName.setText("Customer Name");
        tvInvoiceAmount.setText("Invoice Amount");
        tvCurrency.setText("Currency");
        tvStatus.setText("Status");
        UtilView.setTextAppearanceSmall(activity, tvSalesDate);
        UtilView.setTextAppearanceSmall(activity, tvSalesInvoiceNo);
        UtilView.setTextAppearanceSmall(activity, tvCustomerName);
        UtilView.setTextAppearanceSmall(activity, tvInvoiceAmount);
        UtilView.setTextAppearanceSmall(activity, tvCurrency);
        UtilView.setTextAppearanceSmall(activity, tvStatus);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvSalesDate);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvSalesInvoiceNo);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvCustomerName);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvInvoiceAmount);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvCurrency);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvStatus);

        if (salesOrderListview != null)
            salesOrderListview.addHeaderView(header);

        getSalesInvoiceList("", true, false, "0", false, false);

        spinLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object obj = locationObjectList.get(position);
                if (obj instanceof LocationList) {
                    spinLocation.setSelection(position);
                    locationListData = (LocationList) spinLocation.getSelectedItem();
                    if (locationListData != null) {
                        locationId = locationListData.getInventoryLocationId();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinFromInvoiceNo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object obj = fromObjectList.get(position);
                if (obj instanceof SalesList) {
                    spinFromInvoiceNo.setSelection(position);
                    salesListData = (SalesList) spinFromInvoiceNo.getSelectedItem();

                    sFromId = salesListData.getSqid();
                    //Toast.makeText(activity, "sFromId"+sFromId, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinToInvoiceNo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object obj = toObjectList.get(position);
                if (obj instanceof SalesList) {
                    spinToInvoiceNo.setSelection(position);
                    salesListData = (SalesList) spinToInvoiceNo.getSelectedItem();
                    sToId = salesListData.getSqid();
                    // Toast.makeText(activity, "sToId"+sToId, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object obj = currencyObjectList.get(position);
                if (obj instanceof CurrencyList) {
                    spinCurrency.setSelection(position);
                    currencyListData = (CurrencyList) spinCurrency.getSelectedItem();
                    currencyId = currencyListData.getCurrencyId();
                    // Toast.makeText(activity, "sToId"+sToId, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = statusObjectList.get(i);
                if (obj instanceof String) {
                    spinStatus.setSelection(i);
                    itemStatus = (String) spinStatus.getSelectedItem();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        edCustomerName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, Activity_Customer.class);
                startActivityForResult(intent, Constant.RESQUSTCODE_CUSTOMERS);
            }
        });

        edEmployeeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEmployeeData();
            }
        });

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSalesInvoiceList("search", true, false, "0", false, false);

            }
        });


    }
    private void getEmployeeData() {
        if (serverUrl != null) {

            Intent intent = new Intent(activity, Activity_ShowEmployeeList.class);
            intent.putExtra("callingfrom", Constant.CALL_ADDCRMOPENING_EMPLOYEE);
            startActivityForResult(intent, Constant.RESQUSTCODE_EMPLOYEESEARCH);
        } else {
            UtilView.gotToLogin(activity);
        }
    }
    private void getSalesInvoiceList(String search, boolean isFirst, boolean isPrev, String pageNumber, boolean isNext, boolean isLast) {
        String url = "";
        if (search.equals("search")) {
            url = serverUrl + "/reports/sales/" + Constant.FUNTION_SALESREPORTITEMSALESINVOICELIST + "?currency=1"+"&customerId=" + customerId + "&filterApplied=true&fromDate=" + frmDate + "&fromSID=" + sFromId + "&location=" + locationId +"&selectedList="+empName+"&status="+itemStatus+"&toDate=" + toDate + "&toSID=" + sToId;
        } else {
            url = serverUrl + "/reports/sales/" + Constant.FUNTION_SALESREPORTITEMSALESINVOICELIST + "?toDate="+toDate;
        }
        if (serverUrl != null) {
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {
                UtilView.showProgessBar(activity, progressBar);
                GetDataTask task = new GetDataTask(activity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(activity, progressBar);
                        if (result != null) {
                            Gson gson = new Gson();
                            try {
                                Log.e("result", result.toString());
                                dataList = new ArrayList<>();

                                SalesReportQuotationList data = gson.fromJson(result.toString(), SalesReportQuotationList.class);
                                if (data != null) {
                                    if (data != null) {
                                        setUpView(data);
                                        if (data.getSalesReportList() != null && data.getSalesReportList().size() > 0) {
                                            dataList = data.getSalesReportList();
                                            adapter = new SalesReportInvoiceRegisterAdapter(activity, dataList);
                                            if (salesOrderListview != null)
                                                salesOrderListview.setAdapter(adapter);

                                            adapter.notifyDataSetChanged();
                                            Double grandTotal = 0.00;
                                            if (dataList != null && dataList.size() > 0) {
                                                for (int i = 0; i < dataList.size(); i++) {
                                                    grandTotal += dataList.get(i).getTotalReceivable();
                                                }

                                            }
                                            grandTotalAmount.setText("" + grandTotal);
                                        } else {
                                            dataList.clear();
                                            adapter = new SalesReportInvoiceRegisterAdapter(activity, dataList);
                                            if (salesOrderListview != null)
                                                salesOrderListview.setAdapter(adapter);
                                            adapter.notifyDataSetChanged();
                                            UtilView.showSuccessDialog("Sales Invoice not availbale", activity);
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                UtilView.showErrorDialog(activity.getResources().getString(R.string.response_error), activity);
                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.error_null), activity);
                        }
                    }
                }, false);
                task.execute(url, "");
            }
            if (!isInternetPresent) {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
            }
        } else {
            UtilView.gotToLogin(activity);
        }
    }
    private void setUpView(SalesReportQuotationList data) {
        if (llPaginationView != null)
            llPaginationView.setVisibility(View.VISIBLE);
        if (data.getFirst()) {
            //  tvFirst.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
            if (tvFirst != null) {
                tvFirst.setTextColor(getResources().getColor(R.color.colorBlack));
                tvFirst.setClickable(false);
            }
        }
        if (!data.getFirst()) {
            // tvFirst.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
            if (tvFirst != null) {
                tvFirst.setTextColor(getResources().getColor(R.color.colorWhite));
                tvFirst.setClickable(true);
            }
        }
        if (data.getNext()) {
            // tvNext.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
            if (tvNext != null) {
                tvNext.setTextColor(getResources().getColor(R.color.colorBlack));
                tvNext.setClickable(false);
            }
        }
        if (!data.getNext()) {
            //  tvNext.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
            if (tvNext != null) {
                tvNext.setTextColor(getResources().getColor(R.color.colorWhite));
                tvNext.setClickable(true);
            }
        }
        if (data.getPrev()) {
            //  tvPrev.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
            if (tvPrev != null) {
                tvPrev.setTextColor(getResources().getColor(R.color.colorBlack));
                tvPrev.setClickable(false);
            }
        }
        if (!data.getPrev()) {
            //  tvPrev.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
            if (tvPrev != null) {
                tvPrev.setTextColor(getResources().getColor(R.color.colorWhite));
                tvPrev.setClickable(true);
            }
        }
        if (data.getLast()) {
            //  tvLast.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
            if (tvLast != null) {
                tvLast.setTextColor(getResources().getColor(R.color.colorBlack));
                tvLast.setClickable(false);
            }
        }
        if (!data.getLast()) {
            //   tvLast.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
            if (tvLast != null) {
                tvLast.setTextColor(getResources().getColor(R.color.colorWhite));
                tvLast.setClickable(true);
            }
        }
        pageNo = data.getPageNo();
    }
    private void callOnLoadPageData() {
        String url = serverUrl + "/reports/sales/invoice/" + Constant.FUNTION_SALESREPORTONLOADPAGEDATA;
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
                UtilView.showProgessBar(activity, progressBar);
                GetDataTask getDataTask = new GetDataTask(activity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(activity, progressBar);
                        if (result != null) {
                            if (result.toString().equals(getResources().getString(R.string.response200_sessionLost))) {
                                UtilView.gotToLogin(activity);
                            } else {
                                Log.e("loadData", result.toString());

                                Gson gson = new Gson();
                                try {
                                    SalesReportData salesReportData = gson.fromJson(result.toString(), SalesReportData.class);

                                    if (salesReportData != null) {
                                        if (salesReportData.getLocationList() != null) {
                                            locationObjectList.clear();
                                            //locationObjectList.add("Select");
                                            locationObjectList.addAll(salesReportData.getLocationList());
                                            UtilView.setupSalesReportAdapter(activity, spinLocation, locationObjectList);
                                        }
                                        if (salesReportData.getSalesList() != null) {
                                            fromObjectList.clear();
                                            //fromObjectList.add("Select");
                                            fromObjectList.addAll(salesReportData.getSalesList());
                                            UtilView.setupSalesReportAdapter(activity, spinFromInvoiceNo, fromObjectList);
                                        }
                                        if (salesReportData.getSalesList() != null) {
                                            toObjectList.clear();
                                            //toObjectList.add("Select");
                                            toObjectList.addAll(salesReportData.getSalesList());
                                            UtilView.setupSalesReportAdapter(activity, spinToInvoiceNo, toObjectList);
                                        }
                                        if (salesReportData.getCurrencyList() != null) {
                                            currencyObjectList.clear();
                                            //currencyObjectList.add("Select");
                                            currencyObjectList.addAll(salesReportData.getCurrencyList());
                                            UtilView.setupSalesReportAdapter(activity, spinCurrency, currencyObjectList);
                                        }
                                        if (salesReportData.getCustomerList() != null) {
                                            customerList.addAll(salesReportData.getCustomerList());
                                        }

                                        statusObjectList.clear();
                                        statusObjectList.add("Prepared");
                                        statusObjectList.add("Draft");
                                        UtilView.setupSalesReportAdapter(activity, spinStatus, statusObjectList);

                                    }

                                } catch (Exception e) {
                                    UtilView.showErrorDialog(getResources().getString(response_error), activity);
                                }
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


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constant.RESQUSTCODE_CUSTOMERS && resultCode == Activity.RESULT_OK) {
            in.hiaccounts.hinext.customer.model.CustomerList cust = (in.hiaccounts.hinext.customer.model.CustomerList) data.getSerializableExtra("customer");
            if (cust != null) {
                selected_customer = new Customer();
                if (cust.getCustomerName() != null && !cust.getCustomerName().equals("")) {
                    Log.e("customername",cust.getCustomerName());
                    edCustomerName.setText(cust.getCustomerName());
                    selected_customer.setCustomerName(cust.getCustomerName());
                    customerId = cust.getCustomerId();
                    selected_customer.setCustomerId(customerId);

                }
            }
        }

        if (requestCode == Constant.RESQUSTCODE_EMPLOYEESEARCH && resultCode == Activity.RESULT_OK) {
            empList = (EmployeeList) data.getSerializableExtra("employee");
            if (empList != null)
                empId = empList.getEmployeeId();
                empName = empList.getEmployeeName();

            if (empList.getEmployeeName() != null)
                edEmployeeName.setText(empList.getEmployeeName());
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    @OnClick({R.id.fromDate, R.id.toDate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fromDate:
                getFromDatePicker(activity);
                break;
            case R.id.toDate:
                getToDatePicker(activity);
                break;

        }
    }
    public void getFromDatePicker(Activity activity) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        final TimeZone utc = TimeZone.getTimeZone("UTC");
        final SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        f.setTimeZone(utc);
        DatePickerDialog datePickerDialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                edFromDate.setText(String.valueOf(new StringBuilder().append(year).append("-").append(month + 1).append("-").append(dayOfMonth)));
                calendar.set(year, month, dayOfMonth);
                frmDate = f.format(calendar.getTime());
            }
        }, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();


    }
    private void getToDatePicker(Activity activity) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        final TimeZone utc = TimeZone.getTimeZone("UTC");
        final SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        f.setTimeZone(utc);
        DatePickerDialog datePickerDialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                edToDate.setText(String.valueOf(new StringBuilder().append(year).append("-").append(month + 1).append("-").append(dayOfMonth)));
                calendar.set(year, month, dayOfMonth);
                toDate = f.format(calendar.getTime());
            }
        }, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }
}
