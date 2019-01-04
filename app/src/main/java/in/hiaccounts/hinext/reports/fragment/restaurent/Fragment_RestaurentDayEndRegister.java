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
import butterknife.Unbinder;
import in.hiaccounts.R;
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.employee.EmployeeList;
import in.hiaccounts.hinext.crm.activity.Activity_ShowEmployeeList;
import in.hiaccounts.hinext.customer.activity.Activity_Customer;
import in.hiaccounts.hinext.customer.model.Customer;
import in.hiaccounts.hinext.reports.adapter.restuarant.RestuarantDayEndAdapter;
import in.hiaccounts.hinext.reports.adapter.restuarant.RestuarantDayEndRegisterAdapter;
import in.hiaccounts.hinext.reports.adapter.restuarant.RestuarantInvoiceRegisterAdapter;
import in.hiaccounts.hinext.reports.fragment.restaurent.presenter.IFragmentRestaurentDayEndRegisterCompl;
import in.hiaccounts.hinext.reports.fragment.restaurent.presenter.IFragmentRestaurentDayEndRegisterPresenter;
import in.hiaccounts.hinext.reports.fragment.restaurent.view.IFragmentRestaurentDayEndRegisterView;
import in.hiaccounts.hinext.reports.model.CustomerList;
import in.hiaccounts.hinext.reports.model.LocationList;
import in.hiaccounts.hinext.reports.model.SalesList;
import in.hiaccounts.hinext.reports.model.SalesReportData;
import in.hiaccounts.hinext.reports.model.restuarant.CompanyDetails;
import in.hiaccounts.hinext.reports.model.restuarant.Restaurant_Day_End;
import in.hiaccounts.hinext.reports.model.restuarant.Restaurant_Day_EndData_Register;
import in.hiaccounts.hinext.reports.model.restuarant.Restaurant_Day_EndData_Register_Data;
import in.hiaccounts.hinext.reports.model.restuarant.SalesReportList;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;

import static android.app.Activity.RESULT_OK;
import static in.hiaccounts.R.string.response_error;

/**
 * ACreated by Srinath on 15/12/2018.
 */
public class Fragment_RestaurentDayEndRegister extends Fragment implements IFragmentRestaurentDayEndRegisterView {


    public static final String TAG = Fragment_RestaurentDayEndRegister.class.getSimpleName();
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
    @BindView(R.id.location)
    Spinner spinLocation;
    @BindView(R.id.fromInvoiceNo)
    Spinner spinFromInvoiceNo;
    @BindView(R.id.toInvoiceNo)
    Spinner spinToInvoiceNo;
    @BindView(R.id.edEmployeeName)
    EditText edEmployeeName;
    @BindView(R.id.customerName)
    EditText edCustomerName;
    @BindView(R.id.searchView)
    Button searchView;
    @BindView(R.id.dayEndListview)
    ListView dayEndListview;
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
    private String serverUrl;
    private ServiceHandler serviceHandler;
    private boolean isInternetPresent = false;
    private String frmDate = "", toDate = "";
    private List<Object> locatObjectList;
    private List<Object> fromObjectList;
    private List<Object> toObjectList;
    private LocationList locationListData = null;
    private SalesList salesListData = null;
    private List<CustomerList> customerList = new ArrayList<>();
    private Long customerId, sFromId, sToId, locationId;
    Customer selected_customer;
    private List<SalesReportList> dataList;
    private RestuarantDayEndRegisterAdapter adapter;
    private Long pageNo = 0L;
    private EmployeeList empList;
    private Long empId;
    private IFragmentRestaurentDayEndRegisterPresenter mPresenter;
    private List<Restaurant_Day_EndData_Register_Data> itemSalesList;
    private boolean filterApplied =false;
    private Long currencyId=1L;

    public static Fragment newInstance() {
        Fragment_RestaurentDayEndRegister fragment = new Fragment_RestaurentDayEndRegister();
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
        View view = inflater.inflate(R.layout.fragment__restaurent_day_end_register, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView(view);
        return view;
    }

    private void initView(View view) {
        sharedPreference = SharedPreference.getInstance(activity);
        serverUrl = UtilView.getUrl(activity);
        serviceHandler = new ServiceHandler(activity);
        isInternetPresent = serviceHandler.isConnectingToInternet();

        //presenter
        mPresenter = new IFragmentRestaurentDayEndRegisterCompl(this,activity,serviceHandler,sharedPreference,serverUrl,isInternetPresent);

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

            }
        }


        LayoutInflater inflater = activity.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.report_restuarant_day_end_register_view, null, false);
        TextView tvSalesDate = header.findViewById(R.id.tvSalesDate);
        TextView tvSalesSqNo = header.findViewById(R.id.tvInvoiceNo);
        TextView tvSalesCustomerName = header.findViewById(R.id.tvCustomerName);
        TextView tvEmployeeName = header.findViewById(R.id.tvEmployeeName);
        TextView tvAmount = header.findViewById(R.id.tvAmount);
        tvSalesDate.setText("Date");
        tvSalesSqNo.setText("SINo");
        tvSalesCustomerName.setText("Customer");
        tvAmount.setText("Total Amount");
        tvEmployeeName.setText("Employee");
        UtilView.setTextAppearanceSmall(activity, tvSalesDate);
        UtilView.setTextAppearanceSmall(activity, tvSalesSqNo);
        UtilView.setTextAppearanceSmall(activity, tvSalesCustomerName);
        UtilView.setTextAppearanceSmall(activity, tvAmount);
        UtilView.setTextAppearanceSmall(activity, tvEmployeeName);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvSalesDate);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvSalesSqNo);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvSalesCustomerName);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvAmount);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvEmployeeName);


        edFromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.fromDatePicker(activity,edFromDate);
            }
        });

        edToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.toDatePicker(activity,edToDate);
            }
        });

        edCustomerName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.selectCustomerData();
            }
        });
        edEmployeeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.selectEmplyee();
            }
        });

        spinLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                Object obj = locatObjectList.get(position);
                if (obj instanceof LocationList) {
                    spinLocation.setSelection(position);
                    locationListData = (LocationList) spinLocation.getSelectedItem();
                    if (locationListData != null) {
                        locationId = locationListData.getInventoryLocationId();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.viewItem(currencyId,customerId,true,frmDate,sFromId,locationId,empList.getEmployeeName(),toDate,sToId);

            }
        });

        if (dayEndListview != null)
            dayEndListview.addHeaderView(header);
        mPresenter.getOnpageLoadData();
        mPresenter.getdayEndList("", true, false, "0", false, false);


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        mPresenter.onDestroy();
    }


    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void setFromDate(String fromDate) {
        edFromDate.setText(fromDate);
    }

    @Override
    public void setToDate(String toDate) {
        edToDate.setText(toDate);
    }

    @Override
    public void customerData() {
        Intent intent = new Intent(activity, Activity_Customer.class);
        startActivityForResult(intent, Constant.RESQUSTCODE_CUSTOMERS);
    }

    @Override
    public void employeeData() {
        if (serverUrl != null) {
            Intent intent = new Intent(activity, Activity_ShowEmployeeList.class);
            intent.putExtra("callingfrom", Constant.CALL_ADDCRMOPENING_EMPLOYEE);
            startActivityForResult(intent, Constant.RESQUSTCODE_EMPLOYEESEARCH);
        } else {
            UtilView.gotToLogin(activity);
        }
    }

    @Override
    public void viewItemDetails() {

    }

    @Override
    public void setSpinnerLocation(List<Object> locationObjectList) {
        locatObjectList = locationObjectList;
        UtilView.setupSalesReportAdapter(activity, spinLocation, locationObjectList);
    }

    @Override
    public void setSpinnerFromInv(List<Object> fromInvObjectList) {
        fromObjectList = fromInvObjectList;
        UtilView.setupSalesReportAdapter(activity, spinFromInvoiceNo, fromInvObjectList);
    }

    @Override
    public void setSpinnerToInv(List<Object> toInvObjectList) {
        toObjectList = toInvObjectList;
        UtilView.setupSalesReportAdapter(activity, spinToInvoiceNo, toInvObjectList);
    }

    @Override
    public void setDataRegisterAdapterView(Restaurant_Day_EndData_Register data) {

        Gson gson = new Gson();
        try {
            itemSalesList = new ArrayList<>();
            if (data!=null){
                if (data != null) {
                    setUpView(data);
                    if (data.getData() != null && data.getData().size() > 0) {
                        itemSalesList = data.getData();
                        adapter = new RestuarantDayEndRegisterAdapter(activity, itemSalesList);
                        if (dayEndListview != null)
                            dayEndListview.setAdapter(adapter);

                        adapter.notifyDataSetChanged();
                        Double grandTotal=0.00;
                        if (itemSalesList!=null && itemSalesList.size()>0){
                            for (int i=0;i<itemSalesList.size();i++){
                                grandTotal +=itemSalesList.get(i).getTotalAmount();
                            }

                        }
                        grandTotalAmount.setText(""+grandTotal);
                    } else {
                        itemSalesList.clear();
                        adapter = new RestuarantDayEndRegisterAdapter(activity, itemSalesList);
                        if (dayEndListview != null)
                            dayEndListview.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        UtilView.showSuccessDialog("Item sales not available", activity);
                    }
                }
            }
        } catch (Exception e) {
            UtilView.showErrorDialog(activity.getResources().getString(R.string.response_error), activity);
        }
    }

    private void setUpView(Restaurant_Day_EndData_Register data) {
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constant.RESQUSTCODE_CUSTOMERS && resultCode == RESULT_OK) {
            in.hiaccounts.hinext.customer.model.CustomerList cust = (in.hiaccounts.hinext.customer.model.CustomerList) data.getSerializableExtra("customer");
            if (cust != null) {
                selected_customer = new Customer();

                if (cust.getCustomerName() != null && !cust.getCustomerName().equals("")) {
                    Log.e("customername", cust.getCustomerName());
                    edCustomerName.setText(cust.getCustomerName());
                    selected_customer.setCustomerName(cust.getCustomerName());
                    customerId = cust.getCustomerId();
                    selected_customer.setCustomerId(customerId);

                }

            }


        }
        if (requestCode == Constant.RESQUSTCODE_EMPLOYEESEARCH && resultCode == RESULT_OK) {
            empList = (EmployeeList) data.getSerializableExtra("employee");
            if (empList != null)
                empId = empList.getEmployeeId();
            if (empList.getEmployeeName() != null)
                edEmployeeName.setText(empList.getEmployeeName());
        }
    }
}
