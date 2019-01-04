package in.hiaccounts.hinext.reports.fragment.sales;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.rey.material.widget.ProgressView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.hiaccounts.R;
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.employee.EmployeeList;
import in.hiaccounts.hinext.customer.activity.Activity_Customer;
import in.hiaccounts.hinext.customer.model.Customer;
import in.hiaccounts.hinext.reports.adapter.salesreport.SalesReportItemCommisionAdapter;
import in.hiaccounts.hinext.reports.fragment.sales.customerRegisterMVP.network.CustomerRegisterList;
import in.hiaccounts.hinext.reports.fragment.sales.customerRegisterMVP.presenter.IFragmentCustomerRegisterPresenter;
import in.hiaccounts.hinext.reports.fragment.sales.customerRegisterMVP.presenter.IFragmentCustomerRegisterPresenterImp;
import in.hiaccounts.hinext.reports.fragment.sales.customerRegisterMVP.view.IFragmentCustomerRegisterView;
import in.hiaccounts.hinext.reports.model.CurrencyList;
import in.hiaccounts.hinext.reports.model.CustomerList;
import in.hiaccounts.hinext.reports.model.SalesList;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;

import static android.app.Activity.RESULT_OK;

/**
 * Created by srinath on 31/12/2017.
 */

public class Fragment_Sales_Customer extends Fragment implements IFragmentCustomerRegisterView {


    public static String TAG = Fragment_Sales_Customer.class.getSimpleName();
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.idFrom)
    TextView idFrom;
    @BindView(R.id.edFromDate)
    EditText edFromDate;
    @BindView(R.id.edCustomerName)
    EditText edCustomerName;
    @BindView(R.id.searchView)
    Button searchView;
    @BindView(R.id.customerRegisterListview)
    ListView customerRegisterListview;
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
    private List<Object> salesOrderList = new ArrayList<Object>();
    Customer selected_customer;
    private SalesReportItemCommisionAdapter adapter;
    private Long customerId, sFromId, sToId, locationId, currencyId, itemId;
    private Long pageNo = 0L;
    private EmployeeList empList;
    private Long empId;
    private String itemName = "";
    private IFragmentCustomerRegisterPresenter mPresenter;

    public static Fragment_Sales_Customer newInstance() {
        Fragment_Sales_Customer fragment = new Fragment_Sales_Customer();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity) context;

    }

    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reportsales_customers, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView(view);
        return view;
    }

    private void initView(View view) {
        sharedPreference = SharedPreference.getInstance(activity);
        serverUrl = UtilView.getUrl(activity);
        serviceHandler = new ServiceHandler(activity);
        isInternetPresent = serviceHandler.isConnectingToInternet();

        mPresenter = new IFragmentCustomerRegisterPresenterImp(this,activity,serviceHandler,sharedPreference,serverUrl,isInternetPresent);

        edFromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.datePicker(activity,edFromDate);
            }
        });

        edCustomerName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.selectCustomerData();
            }
        });

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.getCustomerRegisterList("", true, false, "0", false, false);
            }
        });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
    public void setCustomerDate(String customerDate) {
        edFromDate.setText(customerDate);
    }

    @Override
    public void customerData() {
        Intent intent = new Intent(activity, Activity_Customer.class);
        startActivityForResult(intent, Constant.RESQUSTCODE_CUSTOMERS);
    }

    @Override
    public void setCustomerRegisterAdapterView(CustomerRegisterList customerRegisterList) {

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

    }
}
