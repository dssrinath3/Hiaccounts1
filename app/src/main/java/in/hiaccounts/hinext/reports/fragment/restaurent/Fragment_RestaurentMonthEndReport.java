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
import in.hiaccounts.hinext.item.activity.Activity_ShowItemList;
import in.hiaccounts.hinext.item.model.SelectedItemsList;
import in.hiaccounts.hinext.reports.activity.Activity_CategoryList;
import in.hiaccounts.hinext.reports.adapter.restuarant.RestuarantItemMonthEndAdapter;
import in.hiaccounts.hinext.reports.adapter.restuarant.RestuarantItemSalesAdapter;
import in.hiaccounts.hinext.reports.fragment.restaurent.network.ApiClient;
import in.hiaccounts.hinext.reports.fragment.restaurent.network.ApiService;
import in.hiaccounts.hinext.reports.model.CategoryList;
import in.hiaccounts.hinext.reports.model.CurrencyList;
import in.hiaccounts.hinext.reports.model.CustomerList;
import in.hiaccounts.hinext.reports.model.LocationList;
import in.hiaccounts.hinext.reports.model.SalesList;
import in.hiaccounts.hinext.reports.model.SalesReportData;
import in.hiaccounts.hinext.reports.model.restuarant.CompanyDetails;
import in.hiaccounts.hinext.reports.model.restuarant.Restaurant_Day_EndData_Register;
import in.hiaccounts.hinext.reports.model.restuarant.Restaurant_Item_Sales;
import in.hiaccounts.hinext.reports.model.restuarant.SalesReportList;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static in.hiaccounts.R.string.response_error;

/**
 * Srinath 28-12-2018.
 */
public class Fragment_RestaurentMonthEndReport extends Fragment {


    public static final String TAG = Fragment_RestaurentMonthEndReport.class.getSimpleName();
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
    @BindView(R.id.idMonth)
    TextView idMonth;
    @BindView(R.id.spinnerMonth)
    Spinner spinnerMonth;
    @BindView(R.id.edItemName)
    EditText edItemName;
    @BindView(R.id.edCategoryName)
    EditText edCategoryName;
    @BindView(R.id.idLocation)
    TextView idLocation;
    @BindView(R.id.location)
    Spinner spinLocation;
    @BindView(R.id.searchView)
    Button searchView;
    @BindView(R.id.restaurantMonthEndListview)
    ListView restaurantMonthEndListview;
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
    private String serverUrl, itemStatus;
    private ServiceHandler serviceHandler;
    private boolean isInternetPresent = false;
    private String frmDate = "", toDate = "";
    private List<Object> fromObjectList = new ArrayList<>();
    private List<Object> toObjectList = new ArrayList<>();
    private SalesList salesListData = null;
    private CurrencyList currencyListData = null;
    private List<CustomerList> customerList = new ArrayList<>();
    private List<String> monthEndObjectList = new ArrayList<String>();
    private Long customerId, sFromId, sToId, locationId, currencyId, itemId;
    private List<Object> locationObjectList = new ArrayList<>();
    private LocationList locationListData = null;
    private List<SalesReportList> itemSalesList;
    private CategoryList categoryListData;
    private Long pageNo = 0L;
    private RestuarantItemMonthEndAdapter adapter;
    private Long categoryId,currency=1L;
    private ApiService apiService;
    private String accessToken="";
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public static Fragment newInstance() {
        Fragment_RestaurentMonthEndReport fragment = new Fragment_RestaurentMonthEndReport();
        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        activity = (Activity) context;
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__restaurent_month_end_report, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView(view);
        return view;
    }

    private void initView(View view) {
        sharedPreference = SharedPreference.getInstance(activity);
        serverUrl = UtilView.getUrl(activity);
        serviceHandler = new ServiceHandler(activity);
        accessToken=sharedPreference.getData(Constant.ACCESSTOKEN);


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



                //from date
                //String x = "1553970600000";
                if (companyDetails.getStartyear()!=null && companyDetails.getEndyear()!=null){
                    long foo1 = Long.parseLong(String.valueOf(companyDetails.getStartyear()));
                    Date date1 = new Date(foo1);

                    long foo2 = Long.parseLong(String.valueOf(companyDetails.getEndyear()));
                    Date date2 = new Date(foo2);

                   List<Date> date = datesBetween(date1,date2);
                    monthEndObjectList.clear();
                   for (Date monthDate : date ){
                       DateFormat formatter1 = new SimpleDateFormat("MMMM-yyyy");


                       String data = formatter1.format(monthDate);
                       Log.e("monthdate",data);
                       monthEndObjectList.add(data);
                   }
                   UtilView.setupStringArraylistAdapter(activity, spinnerMonth, monthEndObjectList);

                }




            }
        }

        callOnLoadPageData();
        LayoutInflater inflater = activity.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.report_restuarant_month_end_view, null, false);

        TextView tvCategoryName = header.findViewById(R.id.tvCategoryName);
        TextView tvItemName = header.findViewById(R.id.tvItemName);
        TextView tvQty = header.findViewById(R.id.tvQty);
        TextView tvTotalAmount = header.findViewById(R.id.tvTotalAmount);

        tvCategoryName.setText("Category Name");
        tvItemName.setText("Item Name");
        tvQty.setText("Qty");
        tvTotalAmount.setText("Total Amount");


        UtilView.setTextAppearanceSmall(activity, tvCategoryName);
        UtilView.setTextAppearanceSmall(activity, tvItemName);
        UtilView.setTextAppearanceSmall(activity, tvTotalAmount);
        UtilView.setTextAppearanceSmall(activity, tvQty);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvCategoryName);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvItemName);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvTotalAmount);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvQty);

        if (restaurantMonthEndListview != null)
            restaurantMonthEndListview.addHeaderView(header);


        getRestuarantItemSalesList("", true, false, "0", false, false);

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


        edItemName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getItemData();
            }
        });

        edCategoryName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callCategroyList();
            }
        });

        spinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerMonth.setSelection(i);
                String month = (String) spinnerMonth.getSelectedItem();
                Toast.makeText(activity, ""+month, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRestuarantItemSalesList("search", true, false, "0", false, false);
            }
        });


    }
    public static List<Date> datesBetween(Date d1, Date d2) {
        List<Date> ret = new ArrayList<Date>();
        Calendar c = Calendar.getInstance();
        c.setTime(d1);
        while (c.getTimeInMillis()<=d2.getTime()) {
            c.add(Calendar.MONTH, 1);
            ret.add(c.getTime());
        }
        return ret;
    }


    private void getRestuarantItemSalesList(String search, boolean isFirst, boolean isPrev, String pageNumber, boolean isNext, boolean isLast) {


        if (serverUrl != null) {
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {
                if (search.equals("search")) {

                    UtilView.showProgessBar(activity, progressBar);
                    apiService = ApiClient.getClient(accessToken, this.serverUrl).create(ApiService.class);
                    mCompositeDisposable.add(apiService.getViewSearchItemMonthEndReportDataService(1L, true, frmDate, sFromId, categoryId, itemId, locationId, toDate, sToId)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<Restaurant_Item_Sales>() {
                                @Override
                                public void onSuccess(Restaurant_Item_Sales dataRegister) {
                                    if (dataRegister != null) {
                                        UtilView.hideProgessBar(activity, progressBar);
                                        callSalesReportListAdapter(dataRegister);
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    UtilView.hideProgessBar(activity, progressBar);
                                    Log.e("monthend report",e.toString());
                                }
                            }));
                } else {

                    /**
                     * Making Retrofit call to fetch all data
                     */
                    UtilView.showProgessBar(activity, progressBar);
                    apiService = ApiClient.getClient(accessToken, this.serverUrl).create(ApiService.class);
                    mCompositeDisposable.add(apiService.getViewItemMonthEndReportDataService(toDate)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<Restaurant_Item_Sales>() {
                                @Override
                                public void onSuccess(Restaurant_Item_Sales dataRegister) {
                                    if (dataRegister != null) {
                                        callSalesReportListAdapter(dataRegister);
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    Log.e("monthend report",e.toString());
                                }
                            }));
                }


            }else  if (!isInternetPresent) {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
            }
        } else {
            UtilView.gotToLogin(activity);
        }
    }
    private void callSalesReportListAdapter(Restaurant_Item_Sales data) {
        if (data != null) {
            setUpView(data);
            if (data.getSalesReportList() != null && data.getSalesReportList().size() > 0) {
                itemSalesList = data.getSalesReportList();
                adapter = new RestuarantItemMonthEndAdapter(activity, itemSalesList);
                if (restaurantMonthEndListview != null)
                    restaurantMonthEndListview.setAdapter(adapter);

                adapter.notifyDataSetChanged();
                Double grandTotal=0.00;
                if (itemSalesList!=null && itemSalesList.size()>0){
                    for (int i=0;i<itemSalesList.size();i++){
                        grandTotal +=itemSalesList.get(i).getInvoiceAmount();
                    }

                }
                grandTotalAmount.setText(""+grandTotal);
            } else {
                grandTotalAmount.setText("0.00");
                itemSalesList.clear();
                adapter = new RestuarantItemMonthEndAdapter(activity, itemSalesList);
                if (restaurantMonthEndListview != null)
                    restaurantMonthEndListview.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                UtilView.showSuccessDialog("Item sales not available", activity);
            }
        }
    }
    private void callCategroyList() {
        if (serverUrl != null) {
            Intent intent = new Intent(activity, Activity_CategoryList.class);
            intent.putExtra("callingfrom", Constant.CALL_GET_CATEGORYLIST);
            startActivityForResult(intent, Constant.RESQUSTCODE_CATEGORYSEARCH);
        } else {
            UtilView.gotToLogin(activity);
        }
    }


    private void setUpView(Restaurant_Item_Sales data) {
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

                                        if (salesReportData.getCustomerList() != null) {
                                            customerList.addAll(salesReportData.getCustomerList());
                                        }

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == Constant.RESQUSTCODE_ITEMSEARCH && resultCode == Activity.RESULT_OK) {
            SelectedItemsList item = (SelectedItemsList) data.getSerializableExtra("item");

            itemId = item.getItemId();

            if (item.getItemName() != null)
                edItemName.setText(item.getItemName());
        }
        if (requestCode == Constant.RESQUSTCODE_CATEGORYSEARCH && resultCode == Activity.RESULT_OK) {
            categoryListData = (CategoryList) data.getSerializableExtra("categorylist");
            if (categoryListData != null)
                categoryId = categoryListData.getItemCategoryId();

            if (categoryListData.getItemCategoryName() != null)
                edCategoryName.setText(categoryListData.getItemCategoryName());

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        mCompositeDisposable.clear();
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
