package in.hiaccounts.hinext.controlpanel.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.controlpanel.model.accountmaintance.AccountData;
import in.hiaccounts.hinext.controlpanel.model.accountmaintance.ChartAccountDatum;
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.agent.AgentDatum;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 8/21/2017.
 */

public class Activity_ActMaintance_CreateAccount extends AppCompatActivity implements TextWatcher {


    AgentDatum agentDetail;

    public static String TAG = Activity_ActMaintance_CreateAccount.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.edActName)
    EditText edActName;
    @BindView(R.id.spinnerActGroup)
    Spinner spinnerActGroup;
    @BindView(R.id.spinnerActLevel1)
    Spinner spinnerActLevel1;
    @BindView(R.id.spinnerActLevel2)
    Spinner spinnerActLevel2;
    @BindView(R.id.spinnerActType)
    Spinner spinnerActType;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.btnClose)
    Button btnClose;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    private Activity activity;
    private ServiceHandler serviceHandler;
    private boolean isInternatePrsenet = false;
    String callingFor;
    List<AccountData> accountDataList = new ArrayList<>();
    String serverUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actmaintance_createact);
        ButterKnife.bind(this);
        initComponentView();
    }

    private void initComponentView() {

        activity = this;
        serviceHandler = new ServiceHandler(activity);

        Intent intent = getIntent();

        if (intent != null) {
            callingFor = intent.getStringExtra("callingFor");
            toolbar.setTitle(callingFor);
        }

        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        UtilView.setupAdapter(activity, spinnerActType, getResources().getStringArray(R.array.actmaintance_acttype));
        edActName.addTextChangedListener(this);

        serverUrl = UtilView.getUrl(activity);

        getAccountGroupList();


        spinnerActGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                AccountData accountData = (AccountData) parent.getSelectedItem();
                if (accountData != null) {
                    if (accountData.getAgId() != null) {
                        getAccountGroupLevel1List(accountData.getAgId());
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerActLevel1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                ChartAccountDatum accountData = (ChartAccountDatum) parent.getSelectedItem();

                if (accountData != null) {
                    if (accountData.getAccountid() != null) {
                        getAccountGroupLevel2List(accountData.getAccountid());
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void getAccountGroupLevel2List(Long accountid) {

        String url = serverUrl + Constant.FUNTION_GETCHARTACCONTGROUPLEVEL2 + "" + accountid;

        if (serverUrl != null) {
            isInternatePrsenet = serviceHandler.isConnectingToInternet();
            if (isInternatePrsenet) {
                // prepare the Request
                UtilView.showProgessBar(activity, progressBar);
                PostDataTask task = new PostDataTask(activity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(activity, progressBar);

                        if (result != null) {
                            if (result.equals("invalid")) {
                                UtilView.showToast(activity, getResources().getString(R.string.accesstoken_error));
                                Intent intent = new Intent(activity, Activity_Login.class);
                                activity.startActivity(intent);
                                activity.finish();
                            } else {
                                try {

                                    List<ChartAccountDatum> level2AccountList = new ArrayList<>();

                                    ChartAccountDatum accountData1 = new ChartAccountDatum();
                                    accountData1.setAccountid(0L);
                                    accountData1.setAccount_name("Create New");
                                    level2AccountList.add(accountData1);


                                    JSONArray jsonArray = new JSONArray(result.toString());
                                    if (jsonArray.length() > 0) {
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                                            Gson gson = new Gson();
                                            ChartAccountDatum accountData = gson.fromJson(jsonObject.toString(), ChartAccountDatum.class);
                                            level2AccountList.add(accountData);
                                        }
                                    }
                                    if (level2AccountList.size() > 0) {
                                        UtilView.setupGroupActLevelAdapter(activity, spinnerActLevel2, level2AccountList);
                                    }

                                } catch (Exception e) {
                                    UtilView.showLogCat(TAG, "TaskCompleteListener Exception " + e.toString());
                                }
                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.error_null), activity);
                        }
                    }
                }, false);

                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("firstLevelId", accountid);
                    task.execute(jsonObject.toString(), url, "");
                } catch (JSONException je) {

                }

            }
            if (!isInternatePrsenet) {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
            }
        } else {
            UtilView.gotToLogin(activity);
        }
    }

    private void getAccountGroupLevel1List(Long agId) {
        String url = serverUrl + Constant.FUNTION_GETCHARTACCONTGROUPLEVEL1 + "" + agId;

        if (!serverUrl.equals("")) {
            isInternatePrsenet = serviceHandler.isConnectingToInternet();
            if (isInternatePrsenet) {
                UtilView.showProgessBar(activity, progressBar);
                PostDataTask task = new PostDataTask(activity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(activity, progressBar);

                        if (result != null) {
                            if (result.equals("invalid")) {
                                UtilView.showToast(activity, getResources().getString(R.string.accesstoken_error));
                                Intent intent = new Intent(activity, Activity_Login.class);
                                activity.startActivity(intent);
                                activity.finish();
                            } else {
                                try {

                                    List<ChartAccountDatum> level1AccountList = new ArrayList<>();

                                    JSONArray jsonArray = new JSONArray(result.toString());
                                    if (jsonArray.length() > 0) {
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                                            Gson gson = new Gson();
                                            ChartAccountDatum accountData = gson.fromJson(jsonObject.toString(), ChartAccountDatum.class);
                                            level1AccountList.add(accountData);
                                        }
                                    }
                                    if (level1AccountList.size() > 0) {
                                        UtilView.setupGroupActLevelAdapter(activity, spinnerActLevel1, level1AccountList);

                                    } else {
                                        UtilView.showToast(activity, getResources().getString(R.string.noaccount_available));
                                    }

                                } catch (Exception e) {
                                    UtilView.showLogCat(TAG, "TaskCompleteListener Exception " + e.toString());
                                }
                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.error_null), activity);
                        }
                    }
                }, false);

                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("accGroupId", agId);
                    task.execute(jsonObject.toString(), url, "");
                } catch (JSONException je) {

                }


            }
            if (!isInternatePrsenet) {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
            }
        } else {
            UtilView.gotToLogin(activity);
        }


    }

    private void getAccountGroupList() {
        if (serverUrl != null) {
            String url = serverUrl + Constant.FUNTION_GETCHARTACCONTGROUP;

            if (!url.equals("")) {
                isInternatePrsenet = serviceHandler.isConnectingToInternet();
                if (isInternatePrsenet) {
                    UtilView.showProgessBar(activity, progressBar);
                    PostDataTask task = new PostDataTask(activity, new AsyncTaskCompleteListener<String>() {
                        @Override
                        public void onTaskComplete(String result) {
                            UtilView.hideProgessBar(activity, progressBar);

                            if (result != null) {
                                if (result.equals("invalid")) {
                                    UtilView.showToast(activity, getResources().getString(R.string.accesstoken_error));
                                    Intent intent = new Intent(activity, Activity_Login.class);
                                    activity.startActivity(intent);
                                    activity.finish();
                                } else {
                                    try {
                                        if (accountDataList.size() > 0) {
                                            accountDataList.clear();
                                        }

                                        JSONArray jsonArray = new JSONArray(result.toString());
                                        if (jsonArray.length() > 0) {
                                            for (int i = 0; i < jsonArray.length(); i++) {
                                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                                Gson gson = new Gson();
                                                AccountData accountData = gson.fromJson(jsonObject.toString(), AccountData.class);
                                                accountDataList.add(accountData);
                                            }
                                        }
                                        if (accountDataList.size() > 0) {

                                            UtilView.setupGroupActAdapter(activity, spinnerActGroup, accountDataList);

                                        } else {
                                            UtilView.showToast(activity, getResources().getString(R.string.noaccount_available));
                                        }

                                    } catch (Exception e) {
                                        UtilView.showLogCat(TAG, "TaskCompleteListener Exception " + e.toString());
                                    }
                                }
                            } else {
                                UtilView.showErrorDialog(getResources().getString(R.string.error_null), activity);
                            }
                        }
                    }, false);
                    task.execute(new Gson().toString(), url, "");
                    //task.execute(url, "");
                }
                if (!isInternatePrsenet) {
                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
                }
            }
        } else {
            UtilView.gotToLogin(activity);
        }

    }


    private void addAccount() {

        String actName = edActName.getText().toString().trim();
        AccountData actGroup = (AccountData) spinnerActGroup.getSelectedItem();
        ChartAccountDatum level1Act = (ChartAccountDatum) spinnerActLevel1.getSelectedItem();
        ChartAccountDatum level2Act = (ChartAccountDatum) spinnerActLevel2.getSelectedItem();
        String actType = (String) spinnerActType.getSelectedItem();

        Long accountGroupId = actGroup.getAgId();
        Long firstLevelAccountId = level1Act.getAccountid();
        Long secoundLevelAccountId = level2Act.getAccountid();
        String firstLevelStringAccCode = level1Act.getStringAccountCode();
        String secoundLevelStringAccCode = level2Act.getStringAccountCode();
        String url = serverUrl + Constant.FUNTION_ADDCHARTACCOUNT;
        JSONObject jsonObject = new JSONObject();

        if (actName != null && !actName.equals("")
                && actType != null && !actType.equals("")
                && accountGroupId != null
                && !firstLevelAccountId.equals("")
                && secoundLevelAccountId != null) {


            try {

                jsonObject.put("accountName", actName);
                jsonObject.put("accountGroup", accountGroupId);
                jsonObject.put("firstLevelAccountId", firstLevelAccountId);
                jsonObject.put("firstLevelStringAccCode", firstLevelStringAccCode);
                jsonObject.put("accountType", actType);
                if (secoundLevelAccountId != null && secoundLevelAccountId != 0) {
                    jsonObject.put("secoundLevelStringAccCode", secoundLevelStringAccCode);
                    jsonObject.put("secoundLevelAccountId", secoundLevelAccountId);
                }
            } catch (JSONException je) {

            }
            isInternatePrsenet = serviceHandler.isConnectingToInternet();
            if (isInternatePrsenet) {

                if (serverUrl != null) {

                    // prepare the Request
                    UtilView.showProgessBar(activity, progressBar);
                    PostDataTask task = new PostDataTask(activity, new AsyncTaskCompleteListener<String>() {
                        @Override
                        public void onTaskComplete(String result) {
                            UtilView.hideProgessBar(activity, progressBar);

                            if (result != null) {
                                if (result.equals("invalid")) {
                                    UtilView.showToast(activity, getResources().getString(R.string.accesstoken_error));
                                    Intent intent = new Intent(activity, Activity_Login.class);
                                    activity.startActivity(intent);
                                    activity.finish();
                                } else {
                                    try {


                                        JSONArray jsonArray = new JSONArray(result.toString());

                                        if (jsonArray != null && jsonArray.length() > 0) {
                                            UtilView.showToast(activity, "Create Chart of Account add Successfully.");
                                            Intent returnIntent = new Intent();
                                            activity.setResult(Activity.RESULT_OK, returnIntent);
                                            activity.finish();
                                        } else {
                                            UtilView.showToast(activity, "Create Chart of Account not add Successfully. Please try again.");
                                        }
                                    } catch (JSONException e) {
                                        UtilView.showLogCat(TAG, "TaskCompleteListener Exception " + e.toString());
                                    }
                                }
                            } else {
                                UtilView.showErrorDialog(getResources().getString(R.string.error_null), activity);
                            }
                        }
                    }, false);
                    task.execute(jsonObject.toString(), url, "");

                }
            } else {
                UtilView.gotToLogin(activity);
            }
            if (!isInternatePrsenet) {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
            }
        } else {
            if (actName == null || actName.equals("")) {
                edActName.setError(getResources().getString(R.string.err_msg));
            }
            if (actType == null || actType.equals("")) {
                TextView tv = (TextView) spinnerActType.getSelectedView();
                tv.setError(getResources().getString(R.string.err_msg));
            }
            if (accountGroupId == null) {
                TextView tv = (TextView) spinnerActGroup.getSelectedView();
                tv.setError(getResources().getString(R.string.err_msg));
            }
            if (firstLevelAccountId == null) {
                TextView tv = (TextView) spinnerActLevel1.getSelectedView();
                tv.setError(getResources().getString(R.string.err_msg));
            }

            if (secoundLevelAccountId == null) {
                TextView tv = (TextView) spinnerActLevel2.getSelectedView();
                tv.setError(getResources().getString(R.string.err_msg));
            }
        }

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        edActName.setError(null);

    }

    @Override
    public void afterTextChanged(Editable s) {

    }


    @OnClick({R.id.btnSave, R.id.btnClose})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSave:
                addAccount();
                break;
            case R.id.btnClose:
                finish();
                break;
        }
    }
}
