package in.hiaccounts.hinext.reports.fragment.restaurent;


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
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rey.material.widget.ProgressView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
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
import in.hiaccounts.hinext.customer.activity.Activity_Customer;
import in.hiaccounts.hinext.reports.adapter.restuarant.CancelledItemsAdapter;
import in.hiaccounts.hinext.reports.adapter.restuarant.RestuarantInvoiceRegisterAdapter;
import in.hiaccounts.hinext.reports.model.LocationList;
import in.hiaccounts.hinext.reports.model.SalesList;
import in.hiaccounts.hinext.reports.model.SalesReportData;
import in.hiaccounts.hinext.reports.model.restuarant.CancelledItems;
import in.hiaccounts.hinext.reports.model.restuarant.CompanyDetails;
import in.hiaccounts.hinext.reports.model.restuarant.Restaurant_Invoice_Register;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;

import static in.hiaccounts.R.string.response_error;

/**
 * Srinath 22-11-2018.
 */
public class Fragment_RestaurentCancelledItemReports extends Fragment {


    public static final String TAG = Fragment_RestaurentCancelledItemReports.class.getSimpleName();
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
    @BindView(R.id.idLocation)
    TextView idLocation;
    @BindView(R.id.spinLocation)
    Spinner spinLocation;
    @BindView(R.id.spinTokenNo)
    Spinner spinTokenNo;
    @BindView(R.id.searchView)
    Button searchView;
    @BindView(R.id.invoiceListview)
    ListView invoiceListview;
    Unbinder unbinder;
    private Activity activity;
    private SharedPreference sharedPreference;
    private String serverUrl;
    private ServiceHandler serviceHandler;
    private boolean isInternetPresent = false;
    private String frmDate = "", toDate = "",tokenNo="";
    private List<Object> locationObjectList = new ArrayList<>();
    private List<Object> fromObjectList = new ArrayList<>();
    private List<Object> toObjectList = new ArrayList<>();
    private LocationList locationListData = null;
    private Long customerId, sFromId, sToId, locationId=0L;
    private List<CancelledItems> cancelledItems;
    private CancelledItemsAdapter adapter;
    private List<String> tokenList;
    private Long lFromDate,lTodate;
    private List<Object> objectList = new ArrayList<>();


    public static Fragment newInstance() {
        Fragment_RestaurentCancelledItemReports fragment = new Fragment_RestaurentCancelledItemReports();
        return fragment;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurent_cancelled_item_reports, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponent();
        return view;
    }

    private void initComponent() {
        sharedPreference = SharedPreference.getInstance(activity);
        serverUrl = UtilView.getUrl(activity);
        serviceHandler = new ServiceHandler(activity);

        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        SimpleDateFormat df3 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
       // String formattedDate3 = df3.format(calendar.getTime());

        calendar.set(year, month, day);
        frmDate = df3.format(calendar.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date date = null;
        try {
            date = sdf.parse(frmDate);
            lFromDate = date.getTime();
            lTodate = date.getTime();

            Log.e("fromDatevbnbvn", String.valueOf(lFromDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }


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
        callGetTokenList();
        LayoutInflater inflater = activity.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.report_adapter_cancelleditems_list, null, false);
        TextView tvDate = header.findViewById(R.id.tvDate);
        TextView tvTableName = header.findViewById(R.id.tvTableName);
        TextView tvWaiterName = header.findViewById(R.id.tvWaiterName);
        TextView tvTokenNo = header.findViewById(R.id.tvTokenNo);
        TextView tvCancelledItems = header.findViewById(R.id.tvCancelledItems);

        tvDate.setText("Date");
        tvTableName.setText("Table Name");
        tvWaiterName.setText("Waiter Name");
        tvTokenNo.setText("Token No");
        tvCancelledItems.setText("Cancelled Items");
        UtilView.setTextAppearanceSmall(activity, tvDate);
        UtilView.setTextAppearanceSmall(activity, tvTableName);
        UtilView.setTextAppearanceSmall(activity, tvWaiterName);
        UtilView.setTextAppearanceSmall(activity, tvTokenNo);
        UtilView.setTextAppearanceSmall(activity, tvCancelledItems);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvDate);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvTableName);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvWaiterName);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvTokenNo);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvCancelledItems);

        if (invoiceListview != null)
            invoiceListview.addHeaderView(header);


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
        spinTokenNo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object obj = objectList.get(position);
                if (obj instanceof String) {
                    spinTokenNo.setSelection(position);
                    tokenNo = String.valueOf(spinTokenNo.getSelectedItem());

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRestuarantCancelItemList("search", true, false, "0", false, false);
            }
        });
        getRestuarantCancelItemList("", true, false, "0", false, false);

    }

    private void callGetTokenList() {
        String url = serverUrl + "/restaurant/getTokenList";
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
                                Log.e("tokenData", result.toString());
                                tokenList = new ArrayList<>();
                                Gson gson = new Gson();
                                try {
                                     objectList = new ArrayList<>();
                                    tokenList =  new Gson().fromJson(result.toString(), new TypeToken<List<String>>() {}.getType());

                                    objectList.add("Select");
                                    objectList.addAll(tokenList);
                                    UtilView.setupSalesReportAdapter(activity, spinTokenNo, objectList);

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

    private void getRestuarantCancelItemList(String search, boolean isFirst, boolean isPrev, String pageNumber, boolean isNext, boolean isLast) {
        String url = "";
        if (!search.equals("")) {
            url = serverUrl + "/restaurant/" + Constant.FUNTION_REPORTRESTAURENTCANCLELLEDLIST + "?firstPage=true&fromDate=" + lFromDate + "&lastPage=false&location=" + locationId + "&nextPage=false&prevPage=false&toDate=" + lTodate+"&tokenNo="+tokenNo;
        } else {
            url = serverUrl + "/restaurant/" + Constant.FUNTION_REPORTRESTAURENTCANCLELLEDLIST + "?firstPage=true&fromDate=" + lFromDate + "&lastPage=false&location=" + locationId + "&nextPage=false&prevPage=false&toDate=" + lTodate;
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
                                cancelledItems = new ArrayList<>();
                                Log.e("result", result.toString());
                                JSONArray resultJsonArray = new JSONArray(result.toString());
                                for (int i = 0; i < resultJsonArray.length(); i++) {
                                    JSONObject asJson = resultJsonArray.getJSONObject(i);
                                    CancelledItems cancelledData = gson.fromJson(asJson.toString(),CancelledItems.class);
                                    cancelledItems.add(cancelledData);
                                }
                                if (cancelledItems!=null && cancelledItems.size()>0){
                                    adapter = new CancelledItemsAdapter(activity,cancelledItems);
                                    invoiceListview.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                }else {
                                    cancelledItems.clear();
                                    adapter = new CancelledItemsAdapter(activity,cancelledItems);
                                    invoiceListview.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                    UtilView.showErrorDialog("Items not available", activity);

                                }
                            } catch (Exception e) {
                               // UtilView.showErrorDialog(activity.getResources().getString(R.string.response_error), activity);
                            }
                        } else {
                           // UtilView.showErrorDialog(getResources().getString(R.string.error_null), activity);
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
                                            locationObjectList.add("Select");
                                            locationObjectList.addAll(salesReportData.getLocationList());
                                            UtilView.setupSalesReportAdapter(activity, spinLocation, locationObjectList);
                                        }



                                    }

                                } catch (Exception e) {
                                    //UtilView.showErrorDialog(getResources().getString(response_error), activity);
                                }
                            }
                        } else {
                           // UtilView.showErrorDialog(getResources().getString(response_error), activity);
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
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                Date date = null;
                try {
                    date = sdf.parse(frmDate);
                    lFromDate = date.getTime();

                    Log.e("lFromDate", String.valueOf(lFromDate));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
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
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                Date date = null;
                try {
                    date = sdf.parse(toDate);
                    lTodate = date.getTime();

                    Log.e("lTodate", String.valueOf(lTodate));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
