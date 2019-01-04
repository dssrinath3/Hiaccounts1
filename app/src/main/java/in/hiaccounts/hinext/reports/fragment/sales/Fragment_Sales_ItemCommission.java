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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import in.hiaccounts.hinext.customer.model.Customer;
import in.hiaccounts.hinext.item.activity.Activity_ShowItemList;
import in.hiaccounts.hinext.item.model.SelectedItemsList;
import in.hiaccounts.hinext.reports.adapter.salesreport.SalesReportItemCommisionAdapter;
import in.hiaccounts.hinext.reports.model.CurrencyList;
import in.hiaccounts.hinext.reports.model.CustomerList;
import in.hiaccounts.hinext.reports.model.SalesList;
import in.hiaccounts.hinext.reports.model.restuarant.CompanyDetails;
import in.hiaccounts.hinext.reports.model.sales.Sales_ItemCommision_Report;
import in.hiaccounts.hinext.reports.model.sales.Sales_ItemCommision_ReportData;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;

/**
 * Created by srinath on 3/10/2018.
 */

public class Fragment_Sales_ItemCommission extends Fragment {


    public static String TAG = Fragment_Sales_ItemCommission.class.getSimpleName();
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.idFrom)
    TextView idFrom;
    @BindView(R.id.edFromDate)
    EditText edFromDate;
    @BindView(R.id.idTo)
    TextView idTo;
    @BindView(R.id.edToDate)
    EditText edToDate;
    @BindView(R.id.edItemName)
    EditText edItemName;
    @BindView(R.id.edEmployeeName)
    EditText edEmployeeName;
    @BindView(R.id.searchView)
    Button searchView;
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
    @BindView(R.id.salesItemCommisListview)
    ListView salesItemCommisListview;
    private Activity activity;
    private SharedPreference sharedPreference;
    private String serverUrl,itemStatus;
    private ServiceHandler serviceHandler;
    private boolean isInternetPresent = false;
    private String frmDate = "", toDate = "";
    private List<Object> fromObjectList = new ArrayList<>();
    private List<Object> toObjectList = new ArrayList<>();
    private SalesList salesListData = null;
    private CurrencyList currencyListData = null;
    private List<CustomerList> customerList = new ArrayList<>();
    private List<Object> salesOrderList = new ArrayList<Object>();
    Customer selected_customer;
    private SalesReportItemCommisionAdapter adapter;
    private Long customerId, sFromId, sToId, locationId, currencyId,itemId;
    private Long pageNo = 0L;
    private EmployeeList empList;
    private Long empId;
    private String itemName="";
    private List<Sales_ItemCommision_ReportData> dataList;

    public static Fragment_Sales_ItemCommission newInstance() {
        Fragment_Sales_ItemCommission fragment = new Fragment_Sales_ItemCommission();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reportsales_itmecommission, container, false);
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
        edItemName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getItemData();
            }
        });

        edEmployeeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEmployeeData();
            }
        });



        LayoutInflater inflater = activity.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.report_sales_item_commision_view, null, false);
        TextView tvSalesDate = header.findViewById(R.id.tvSalesDate);
        TextView tvEmployeeName = header.findViewById(R.id.tvEmployeeName);
        TextView tvItemName = header.findViewById(R.id.tvItemName);
        TextView tvUnitPrice = header.findViewById(R.id.tvUnitPrice);
        TextView tvQty = header.findViewById(R.id.tvQty);
        TextView tvAmount = header.findViewById(R.id.tvAmount);


        tvSalesDate.setText("Date");
        tvEmployeeName.setText("EmpName");
        tvItemName.setText("ItemName");
        tvUnitPrice.setText("price");
        tvAmount.setText("Amount(In)");
        tvQty.setText("Qty");

        UtilView.setTextAppearanceSmall(activity, tvSalesDate);
        UtilView.setTextAppearanceSmall(activity, tvEmployeeName);
        UtilView.setTextAppearanceSmall(activity, tvItemName);
        UtilView.setTextAppearanceSmall(activity, tvUnitPrice);
        UtilView.setTextAppearanceSmall(activity, tvAmount);
        UtilView.setTextAppearanceSmall(activity, tvQty);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvSalesDate);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvEmployeeName);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvItemName);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvUnitPrice);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvAmount);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvQty);

        if (salesItemCommisListview != null)
            salesItemCommisListview.addHeaderView(header);

        getSalesItemInvoceList("", true, false, "0", false, false);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSalesItemInvoceList("search", true, false, "0", false, false);
            }
        });

    }

    private void getSalesItemInvoceList(String search, boolean isFirst, boolean isPrev, String pageNumber, boolean isNext, boolean isLast) {
        String url = "";
        if (search.equals("search")) {
            url = serverUrl + "/reports/sales/" + Constant.FUNTION_SALESREPORTITEMSALESCOMMISLIST + "?empId="+empId+"&filterApplied=true&firstPage="+isFirst+"&fromDate=" + frmDate + "&lastPage=" + isLast + "&name="+itemName+"&nextPage="+isNext+"&pageNo="+pageNo+"&prevPage="+isPrev+"&toDate="+toDate;
        } else {
            url = serverUrl + "/reports/sales/" + Constant.FUNTION_SALESREPORTITEMSALESCOMMISLIST + "?firstPage="+isFirst+"&fromDate=" + frmDate + "&lastPage=" + isLast + "&nextPage="+isNext+"&pageNo="+pageNo+"&prevPage="+isPrev+"&toDate="+toDate;
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
                                dataList = new ArrayList<>();
                                Log.e("result", result.toString());
                                Sales_ItemCommision_Report data = gson.fromJson(result.toString(), Sales_ItemCommision_Report.class);
                                if (data != null) {
                                    if (data != null) {
                                        setUpView(data);
                                        if (data.getData() != null && data.getData().size() > 0) {
                                            dataList = data.getData();
                                            adapter = new SalesReportItemCommisionAdapter(activity, dataList);
                                            if (salesItemCommisListview != null)
                                                salesItemCommisListview.setAdapter(adapter);

                                            adapter.notifyDataSetChanged();
                                            Double grandTotal = 0.00;
                                            if (dataList != null && dataList.size() > 0) {
                                                for (int i = 0; i < dataList.size(); i++) {
                                                    grandTotal += dataList.get(i).getItemIncAmt();
                                                }

                                            }
                                            grandTotalAmount.setText("" + grandTotal);
                                        } else {
                                            dataList.clear();
                                            adapter = new SalesReportItemCommisionAdapter(activity, dataList);
                                            if (salesItemCommisListview != null)
                                                salesItemCommisListview.setAdapter(adapter);
                                            adapter.notifyDataSetChanged();
                                            UtilView.showSuccessDialog("Item Commision not availbale", activity);
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

    private void getItemData() {
        if (serverUrl != null) {
            String url = serverUrl + "/hipos//1/" + Constant.FUNTION_GETITEMLIST + "?itemSearchText=";
            Intent intent = new Intent(activity, Activity_ShowItemList.class);
            intent.putExtra("url", url);
            intent.putExtra("taxType", "");
            intent.putExtra("itemsearch", "");
            intent.putExtra("callingfrom", Constant.CALL_ADDINVENTORUOPENING_ITEM);
            startActivityForResult(intent, Constant.RESQUSTCODE_ITEMSEARCH);
        } else {
            UtilView.gotToLogin(activity);
        }
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
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == Constant.RESQUSTCODE_ITEMSEARCH && resultCode == Activity.RESULT_OK) {
            SelectedItemsList item = (SelectedItemsList) data.getSerializableExtra("item");

            itemName = item.getItemName();

            if (item.getItemName() != null)
                edItemName.setText(item.getItemName());
        }
        if (requestCode == Constant.RESQUSTCODE_EMPLOYEESEARCH && resultCode == Activity.RESULT_OK) {
            empList = (EmployeeList) data.getSerializableExtra("employee");
            if (empList != null)
                empId = empList.getEmployeeId();

            if (empList.getEmployeeName() != null)
                edEmployeeName.setText(empList.getEmployeeName());
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void setUpView(Sales_ItemCommision_Report data) {
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

    @OnClick({R.id.edFromDate, R.id.edToDate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edFromDate:
                getFromDatePicker(activity);
                break;
            case R.id.edToDate:
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
