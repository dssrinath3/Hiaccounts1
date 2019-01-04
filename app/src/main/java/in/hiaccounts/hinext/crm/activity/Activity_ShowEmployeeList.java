package in.hiaccounts.hinext.crm.activity;

import android.app.Activity;
import android.content.Intent;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hiaccounts.R;
import in.hiaccounts.hinext.controlpanel.activity.Activity_Config_Employee;
import in.hiaccounts.hinext.controlpanel.adapter.SelectEmployeeListAdapter;
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.employee.EmployeeList;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;

public class Activity_ShowEmployeeList extends AppCompatActivity implements TextWatcher {

    private static String TAG = Activity_ShowEmployeeList.class.getSimpleName();
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edSearch)
    EditText edSearch;
    @BindView(R.id.imgviewSearch)
    ImageView imgviewSearch;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvEmail)
    TextView tvEmail;
    @BindView(R.id.tvContact)
    TextView tvContact;
    @BindView(R.id.lvEmployee)
    ListView lvEmployee;
    @BindView(R.id.llListView)
    LinearLayout llListView;
    @BindView(R.id.llEmployeeList)
    LinearLayout llEmployeeList;
    @BindView(R.id.rlContent)
    RelativeLayout rlContent;

    List<EmployeeList> employeeList;
    private Activity mActivity;
    private ServiceHandler serviceHandler;
    private Boolean isInternetPresent = false;
    private SharedPreference sharedPreference;
    private String serverUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_select);
        ButterKnife.bind(this);
        initComponentView();
    }

    private void initComponentView() {
        mActivity = this;
        serverUrl = UtilView.getUrl(mActivity);
        ButterKnife.bind(this);
        edSearch.addTextChangedListener(this);
        toolbar.setTitle(getResources().getString(R.string.select_employee));
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


        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    getEmployeeFromserver(edSearch.getText().toString().trim());

                }
                return handled;
            }
        });
        lvEmployee.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String callingFrom = getIntent().getStringExtra("callingFrom");
                if (callingFrom != null && callingFrom.equals(Constant.CALL_ADDCRMOPENING_EMPLOYEE)) {
                } else {
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("employee", employeeList.get(position));
                    mActivity.setResult(Activity.RESULT_OK, returnIntent);
                    mActivity.finish();
                }
            }
        });

        getEmployeeFromserver("");

    }

    private void getEmployeeFromserver(String search) {

        String url = serverUrl + "/hipos//1/" + Constant.FUNTION_GETEMPLOYELIST + "?employeeSearchText="+ search.replace(" ", "%20");
        if (serverUrl!=null) {
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {
                // prepare the Request
                UtilView.showProgessBar(mActivity, progressBar);
                GetDataTask task = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(mActivity, progressBar);
                        if (result != null) {
                            try {
                                employeeList = new ArrayList<EmployeeList>();
                                JSONArray resultJsonArray = new JSONArray(result.toString());
                                for (int i = 0; i < resultJsonArray.length(); i++) {
                                    JSONObject asJson = resultJsonArray.getJSONObject(i);
                                    Gson gson = new Gson();
                                    EmployeeList data = gson.fromJson(asJson.toString(), EmployeeList.class);
                                    employeeList.add(data);
                                }
                                if (employeeList!=null && employeeList.size()>0){
                                    SelectEmployeeListAdapter adapter = new SelectEmployeeListAdapter(mActivity,employeeList);
                                    lvEmployee.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                }
                                else {
                                    employeeList.clear();
                                    SelectEmployeeListAdapter adapter = new SelectEmployeeListAdapter(mActivity, employeeList);
                                    lvEmployee.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                    UtilView.showErrorDialog(getResources().getString(R.string.advancediscountconfig_notavailbale), mActivity);

                                }
                            }catch (Exception e){
                                UtilView.showErrorDialog(getResources().getString(R.string.error_null), mActivity);
                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.error_null), mActivity);
                        }
                    }
                }, false);
                task.execute(url, "");
            }
            if (!isInternetPresent) {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
            }
        }else {
            UtilView.gotToLogin(mActivity);
        }

    }
    @OnClick({R.id.imgviewSearch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgviewSearch:
                getEmployeeFromserver(edSearch.getText().toString().trim());
                break;

        }
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
                Intent intent = new Intent(mActivity, Activity_Config_Employee.class);
                intent.putExtra("callingFor", Constant.CALL_ADDEMPLOYEE);
                startActivityForResult(intent, Constant.RESQUSTCODE_ADDEMPLOYEE);
                break;


        }
        return super.onOptionsItemSelected(item);
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
            getEmployeeFromserver("");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constant.RESQUSTCODE_ADDEMPLOYEE && resultCode == RESULT_OK) {
            getEmployeeFromserver("");
        }

        if (requestCode == Constant.RESQUSTCODE_EDITEMPLOYEE && resultCode == RESULT_OK) {
            getEmployeeFromserver("");
        }


    }
}
