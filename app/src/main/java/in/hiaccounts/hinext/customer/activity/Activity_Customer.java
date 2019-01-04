package in.hiaccounts.hinext.customer.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;
import com.yinglan.keyboard.HideUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.customer.adapter.CustomerList_Adapter;
import in.hiaccounts.hinext.customer.model.CusotmerData;
import in.hiaccounts.hinext.customer.model.CustomerList;
import in.hiaccounts.hinext.customer.model.RequestParam;
import in.hiaccounts.hinext.customer.model.RequestParamData;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 7/25/2017.
 */

public class Activity_Customer extends AppCompatActivity implements TextWatcher {


    private static String TAG = Activity_Customer.class.getSimpleName();
    List<CustomerList> customerList = new ArrayList<CustomerList>();
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edSearch)
    EditText edSearch;
    @BindView(R.id.imgviewSearch)
    ImageView imgviewSearch;
    @BindView(R.id.lvCustomer)
    ListView lvCustomer;
    @BindView(R.id.llCustomerList)
    LinearLayout llCustomerList;
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

    @BindView(R.id.llListView)
    LinearLayout llListView;
    @BindView(R.id.rlContent)
    RelativeLayout rlContent;

    private Activity mActivity;
    private ServiceHandler serviceHandler;
    private Boolean isInternetPresent = false;
    private SharedPreference sharedPreference;
    private String serverUrl;
    private int pageNo = 0;
    String callingFrom="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_customer);
        ButterKnife.bind(this);
        initComponentView();
    }

    private void initComponentView() {
        mActivity = this;
        serverUrl = UtilView.getUrl(mActivity);
        ButterKnife.bind(this);
        edSearch.addTextChangedListener(this);
        toolbar.setTitle(getResources().getString(R.string.select_customer));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        HideUtil.init(mActivity);
        sharedPreference = SharedPreference.getInstance(mActivity);
        serviceHandler = new ServiceHandler(mActivity);


        Intent intent=getIntent();

        if (intent!=null){
            callingFrom=intent.getStringExtra("callingFrom");
            if (callingFrom!=null) {
                if (callingFrom.equals("Restra")) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

                }
            }

        }


       /* LayoutInflater inflater = mActivity.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.adapter_sales_customer, null, false);

        TextView tvName = (TextView) header.findViewById(R.id.tvName);
        TextView tvEmail = (TextView) header.findViewById(R.id.tvEmail);
        TextView tvContact = (TextView) header.findViewById(R.id.tvContact);
        TextView tvEdit = (TextView) header.findViewById(R.id.tvEdit);

        tvName.setText(getResources().getString(R.string.c_name));
        tvContact.setText(getResources().getString(R.string.c_number));
        tvEmail.setText(getResources().getString(R.string.c_email));
        tvEdit.setText("Action");

        UtilView.setTextAppearanceSmall(mActivity, tvName);
        UtilView.setTextAppearanceSmall(mActivity, tvEmail);
        UtilView.setTextAppearanceSmall(mActivity, tvContact);
        UtilView.setTextAppearanceSmall(mActivity, tvEdit);

        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvName);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvEmail);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvContact);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvEdit);

        UtilView.setTexttypeFace(null, Typeface.BOLD, tvName);
        UtilView.setTexttypeFace(null, Typeface.BOLD, tvEmail);
        UtilView.setTexttypeFace(null, Typeface.BOLD, tvContact);
        UtilView.setTexttypeFace(null, Typeface.BOLD, tvEdit);

        if (lvCustomer != null)
            lvCustomer.addHeaderView(header);*/

        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    getCustomersFromserver(edSearch.getText().toString().trim(), true, false, 0, false, false);

                }
                return handled;
            }
        });
        lvCustomer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                callingFrom = getIntent().getStringExtra("callingFrom");
                if (callingFrom != null && callingFrom.equals(Constant.NAVIGATION_GROUP_CONTACT)) {
                } else {
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("customer", customerList.get(position));
                        mActivity.setResult(Activity.RESULT_OK, returnIntent);
                        mActivity.finish();
                }
            }
        });

        getCustomersFromserver("", true, false, 0, false, false);
    }

    private void getCustomersFromserver(String search, boolean isFirst, boolean isPrev, int pageNumber, boolean isNext, boolean isLast) {
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {

                String url = "";
                if (search.equals("")) {
                    url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETCUSTOMER + "?searchCustomerText=" + "&type=&firstPage=" + isFirst + "&lastPage=" + isLast + "&nextPage=" + isNext + "&pageNo=" + pageNumber + "&prevPage=" + isPrev;
                }
                if (!search.equals("")) {
                    url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETCUSTOMER + "?searchCustomerText=" + search.replace(" ", "%20") + "&type=&firstPage=" + isFirst + "&lastPage=" + isLast + "&nextPage=" + isNext + "&pageNo=" + pageNumber + "&prevPage=" + isPrev;
                }


                RequestParam param = new RequestParam(isFirst, isLast, pageNumber, isPrev, isNext);
                RequestParamData dataParam = new RequestParamData();
                dataParam.setParams(param);

                UtilView.showProgessBar(mActivity, progressBar);
                //String url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETCUSTOMER + "?searchCustomerText=" + search.replace(" ", "%20");
                GetDataTask task = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(mActivity, progressBar);
                        HideUtil.init(mActivity);
                        if (result != null) {
                            if (result.equals("invalid")) {
                                UtilView.showToast(mActivity, getResources().getString(R.string.accesstoken_error));
                                Intent intent = new Intent(mActivity, Activity_Login.class);
                                mActivity.startActivity(intent);
                                mActivity.finish();
                            } else {
                                try {

                                    Gson gson = new Gson();
                                    customerList.clear();
                                    List<CustomerList> list = new ArrayList<>();
                                    CusotmerData data = gson.fromJson(result.toString(), CusotmerData.class);
                                    if (data != null) {
                                        if (lvCustomer != null) {
                                            setUpView(data);
                                            if (data.getData() != null && data.getData().size() > 0) {
                                                list = data.getData();
                                                llListView.setVisibility(View.VISIBLE);
                                                customerList.addAll(list);
                                                CustomerList_Adapter customerListAdapter = new CustomerList_Adapter(mActivity, customerList);
                                                lvCustomer.setAdapter(customerListAdapter);
                                                customerListAdapter.notifyDataSetChanged();
                                            } else {
                                                llListView.setVisibility(View.GONE);
                                                customerList.clear();
                                                CustomerList_Adapter customerListAdapter = new CustomerList_Adapter(mActivity, customerList);
                                                lvCustomer.setAdapter(customerListAdapter);
                                                customerListAdapter.notifyDataSetChanged();
                                                edSearch.setText("");
                                                UtilView.showErrorDialog(getResources().getString(R.string.nocustomer_available), mActivity);
                                            }
                                        }

                                    } else {
                                        UtilView.showErrorDialog(getResources().getString(R.string.error_null), mActivity);
                                    }

                                } catch (Exception e) {
                                    UtilView.showErrorDialog(getResources().getString(R.string.error_null), mActivity);
                                }
                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.error_null), mActivity);
                        }
                    }
                }, false);
                task.execute(url, "");
            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
            }
        } else {
            UtilView.gotToLogin(mActivity);
        }
    }


    @OnClick({R.id.tvFirst, R.id.tvPrev, R.id.tvNext, R.id.tvLast, R.id.imgviewSearch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgviewSearch:
                getCustomersFromserver(edSearch.getText().toString().trim(), true, false, 0, false, false);
                break;
            case R.id.tvFirst:
                pageNo = 0;
                getCustomersFromserver("", true, false, 0, false, false);
                break;
            case R.id.tvPrev:
                if (pageNo > 0)
                    pageNo = pageNo - 1;
                getCustomersFromserver("", false, true, pageNo, false, false);
                break;
            case R.id.tvNext:
                pageNo = pageNo + 1;
                getCustomersFromserver("", false, false, pageNo, true, false);
                break;
            case R.id.tvLast:
                pageNo = 0;
                getCustomersFromserver("", false, false, 0, false, true);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constant.RESQUSTCODE_ADDCUSTOMER && resultCode == RESULT_OK) {
            getCustomersFromserver("", true, false, 0, false, false);
        }

        if (requestCode == Constant.RESQUSTCODE_EDITCUSOTMER && resultCode == RESULT_OK) {
            getCustomersFromserver("", true, false, 0, false, false);
        }


    }

    private void setUpView(CusotmerData data) {

        if (rlContent != null)
            rlContent.setVisibility(View.VISIBLE);

        if (data.isFirst()) {
            //  tvFirst.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
            tvFirst.setTextColor(getResources().getColor(R.color.colorBlack));
            tvFirst.setClickable(false);

        }
        if (!data.isFirst()) {
            // tvFirst.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
            tvFirst.setTextColor(getResources().getColor(R.color.colorWhite));
            tvFirst.setClickable(true);
        }


        if (data.isNext()) {
            // tvNext.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
            tvNext.setTextColor(getResources().getColor(R.color.colorBlack));
            tvNext.setClickable(false);

        }
        if (!data.isNext()) {
            //  tvNext.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
            tvNext.setTextColor(getResources().getColor(R.color.colorWhite));
            tvNext.setClickable(true);
        }


        if (data.isPrev()) {
            //  tvPrev.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
            tvPrev.setTextColor(getResources().getColor(R.color.colorBlack));
            tvPrev.setClickable(false);

        }
        if (!data.isPrev()) {
            //  tvPrev.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
            tvPrev.setTextColor(getResources().getColor(R.color.colorWhite));
            tvPrev.setClickable(true);
        }

        if (data.isLast()) {
            //  tvLast.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
            tvLast.setTextColor(getResources().getColor(R.color.colorBlack));
            tvLast.setClickable(false);
        }
        if (!data.isLast()) {
            //   tvLast.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
            tvLast.setTextColor(getResources().getColor(R.color.colorWhite));
            tvLast.setClickable(true);
        }

        pageNo = data.getPageNo();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.inventory_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.addMenu:
                Intent intent = new Intent(mActivity, Activity_AddCustomer.class);
                intent.putExtra("callingFrom", callingFrom);
                startActivityForResult(intent, Constant.RESQUSTCODE_ADDCUSTOMER);

                break;


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.toString().equals("")) {
            UtilView.hideSoftKeyboard(mActivity, edSearch);
            getCustomersFromserver("", true, false, 0, false, false);
        }
    }
}
