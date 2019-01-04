package in.hiaccounts.hinext.reports.fragment.restaurent;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
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
import android.widget.TextView;
import android.widget.Toast;

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
import in.hiaccounts.hinext.reports.adapter.restuarant.RestuarantDayEndAdapter;
import in.hiaccounts.hinext.reports.adapter.restuarant.RestuarantItemSalesAdapter;
import in.hiaccounts.hinext.reports.model.restuarant.CompanyDetails;
import in.hiaccounts.hinext.reports.model.restuarant.Restaurant_Day_End;
import in.hiaccounts.hinext.reports.model.restuarant.Restaurant_Day_EndData;
import in.hiaccounts.hinext.reports.model.restuarant.Restaurant_Item_Sales;
import in.hiaccounts.hinext.reports.model.restuarant.SalesReportList;
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
public class Fragment_RestaurentDayEndReport extends Fragment {


    public static final String TAG = Fragment_RestaurentDayEndReport.class.getSimpleName();

    Unbinder unbinder;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.restaurantItemReportListview)
    ListView restaurantItemReportListview;
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
    @BindView(R.id.idFrom)
    TextView idFrom;
    @BindView(R.id.edFromDate)
    EditText edFromDate;
    @BindView(R.id.idTo)
    TextView idTo;
    @BindView(R.id.edToDate)
    EditText edToDate;
    @BindView(R.id.searchView)
    Button searchView;
    private Activity activity;
    private SharedPreference sharedPreference;
    private String serverUrl;
    private ServiceHandler serviceHandler;
    private boolean isInternetPresent = false;
    private String frmDate = "", toDate = "";
    private List<Restaurant_Day_EndData> itemSalesList;
    private RestuarantDayEndAdapter adapter;
    private Long pageNo = 0L;
    public static Fragment newInstance() {
        Fragment_RestaurentDayEndReport fragment = new Fragment_RestaurentDayEndReport();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurent_day_end_report, container, false);
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

        LayoutInflater inflater = activity.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.report_restuarant_day_end_view, null, false);
        TextView tvSalesDate = header.findViewById(R.id.tvSalesDate);
        TextView tvTotalAmount = header.findViewById(R.id.tvTotalAmount);
        TextView tvNoOfItems = header.findViewById(R.id.tvNoOfItems);
        TextView tvEdit = header.findViewById(R.id.tvEdit);
        tvSalesDate.setText("Date");
        tvTotalAmount.setText("Total Amount");
        tvNoOfItems.setText("No:Of Item");
        tvEdit.setText("Action");

        UtilView.setTextAppearanceSmall(activity, tvSalesDate);
        UtilView.setTextAppearanceSmall(activity, tvTotalAmount);
        UtilView.setTextAppearanceSmall(activity, tvNoOfItems);
        UtilView.setTextAppearanceSmall(activity, tvEdit);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvSalesDate);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvTotalAmount);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvNoOfItems);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvEdit);

        if (restaurantItemReportListview != null)
            restaurantItemReportListview.addHeaderView(header);

        callDayEndReportData("", true, false, "0", false, false);


        restaurantItemReportListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               callDayEndPrint(itemSalesList.get(position));
            }
        });



    }

    private void callDayEndPrint(Restaurant_Day_EndData restaurant_day_endData) {
        String url = serverUrl + "/reports/inventory/" + Constant.FUNTION_RESTARUANTDAYENDREPORTPRINT + "?fromDate="+restaurant_day_endData.getDate();
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
                                Log.e("printDayEndReport", result.toString());

                                Gson gson = new Gson();
                                try {


                                } catch (Exception e) {
                                    UtilView.showErrorDialog(activity.getResources().getString(R.string.response_error), activity);
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

    private void callDayEndReportData(String search, boolean isFirst, boolean isPrev, String pageNumber, boolean isNext, boolean isLast) {
        String url = serverUrl + "/reports/inventory/" + Constant.FUNTION_RESTARUANTDAYENDREPORTLIST + "?firstPage="+isFirst+"&fromDate="+frmDate+"&lastPage="+isLast+"&nextPage="+isNext+"&pageNo="+pageNumber+"&prevPage="+isPrev+"&toDate="+toDate;
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
                                Log.e("loadDayEndReport", result.toString());

                                Gson gson = new Gson();
                                try {
                                    itemSalesList = new ArrayList<>();
                                    Log.e("result",result.toString());
                                    Restaurant_Day_End data = gson.fromJson(result.toString(),Restaurant_Day_End.class);
                                    if (data!=null){
                                        if (data != null) {
                                            setUpView(data);
                                            if (data.getData() != null && data.getData().size() > 0) {
                                                itemSalesList = data.getData();
                                                adapter = new RestuarantDayEndAdapter(activity, itemSalesList);
                                                if (restaurantItemReportListview != null)
                                                    restaurantItemReportListview.setAdapter(adapter);

                                                adapter.notifyDataSetChanged();
                                                Double grandTotal=0.00;
                                                if (itemSalesList!=null && itemSalesList.size()>0){
                                                    for (int i=0;i<itemSalesList.size();i++){
                                                        grandTotal +=itemSalesList.get(i).getAmount();
                                                    }

                                                }
                                                grandTotalAmount.setText(""+grandTotal);
                                            } else {
                                                itemSalesList.clear();
                                                adapter = new RestuarantDayEndAdapter(activity, itemSalesList);
                                                if (restaurantItemReportListview != null)
                                                    restaurantItemReportListview.setAdapter(adapter);
                                                adapter.notifyDataSetChanged();
                                                UtilView.showSuccessDialog("Item sales not available", activity);
                                            }
                                        }
                                    }
                                } catch (Exception e) {
                                    UtilView.showErrorDialog(activity.getResources().getString(R.string.response_error), activity);
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

    private void setUpView(Restaurant_Day_End data) {
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
