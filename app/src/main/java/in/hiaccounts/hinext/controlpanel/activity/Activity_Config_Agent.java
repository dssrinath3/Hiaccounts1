package in.hiaccounts.hinext.controlpanel.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.agent.AgentDatum;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;
import in.hiaccounts.utility.Validation;

/**
 * Created by Prateek on 8/21/2017.
 */

public class Activity_Config_Agent extends AppCompatActivity implements TextWatcher {


    public static String TAG = Activity_Config_Agent.class.getSimpleName();
    AgentDatum agentDetail;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edAgentName)
    EditText edAgentName;
    @BindView(R.id.edCommission)
    EditText edCommission;
    @BindView(R.id.edGstin)
    EditText edGstin;
    @BindView(R.id.edBankAddress)
    EditText edBankAddress;
    @BindView(R.id.edPhoneNo)
    EditText edPhoneNo;
    @BindView(R.id.edEmail)
    EditText edEmail;
    @BindView(R.id.edDate)
    EditText edDate;
    @BindView(R.id.spinnerEcommerce)
    Spinner spinnerEcommerce;
    @BindView(R.id.spinnerStatus)
    Spinner spinnerStatus;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.btnClose)
    Button btnClose;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    String callingFor;
    String selectedDate = "";
    String serverUrl;
    private Activity activity;
    private ServiceHandler serviceHandler;
    private boolean isInternatePrsenet = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpanel_configagent);
        ButterKnife.bind(this);
        initComponentView();
    }

    private void initComponentView() {

        activity = this;
        serviceHandler = new ServiceHandler(activity);

        Intent intent = getIntent();
        UtilView.setupAdapter(activity, spinnerEcommerce, getResources().getStringArray(R.array.config_agent_ecoomerce));
        UtilView.setupAdapter(activity, spinnerStatus, getResources().getStringArray(R.array.config_project_status));
        if (intent != null) {
            callingFor = intent.getStringExtra("callingFor");

            if (callingFor != null) {
                toolbar.setTitle(callingFor);

                if (callingFor.equals(Constant.CALL_EDITAGENT)) {
                    agentDetail = (AgentDatum) intent.getSerializableExtra("agentData");

                    if (agentDetail != null) {
                        if (agentDetail.getAgentName() != null)
                            edAgentName.setText(agentDetail.getAgentName());

                        if (agentDetail.getMobile() != null)
                            edPhoneNo.setText(agentDetail.getMobile());

                        if (agentDetail.getEmail() != null)
                            edEmail.setText(agentDetail.getEmail());

                        if (agentDetail.getAddress() != null)
                            edBankAddress.setText(agentDetail.getAddress());

                        edCommission.setText("" + agentDetail.getCommission());

                        if (agentDetail.getGstinNo() != null)
                            edGstin.setText(agentDetail.getGstinNo());

                        if (agentDetail.getEffectiveDate() != null)
                            edDate.setText(agentDetail.getEffectiveDate());

                        selectedDate = agentDetail.getEffectiveDate();

                        if (agentDetail.getEcommerce() != null) {

                            if (agentDetail.getEcommerce().equals("Yes")) {
                                spinnerEcommerce.setSelection(0);
                            }

                            if (agentDetail.getEcommerce().equals("No")) {
                                spinnerEcommerce.setSelection(1);
                            }
                        }
                        if (agentDetail.getStatus() !=null){
                            if (agentDetail.getStatus().equals("Active"))
                            {
                                spinnerStatus.setSelection(0);
                            }
                            if (agentDetail.getStatus().equals("InActive"))
                            {
                                spinnerStatus.setSelection(1);
                            }
                        }

                    }

                }
            }
        }

        serverUrl = UtilView.getUrl(activity);

        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        spinnerStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        edAgentName.addTextChangedListener(this);
        edCommission.addTextChangedListener(this);
        edEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                boolean isValid = Validation.isValidEmail(s.toString());

                if (isValid) {
                    edEmail.setError(null);
                } else {
                    edEmail.setError(getResources().getString(R.string.err_msg_email));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }


    private void addAgent() {

        boolean isValid = true;
        String agentName = edAgentName.getText().toString().trim();

        String commission = edCommission.getText().toString().trim();
        String address = edBankAddress.getText().toString().trim();
        String phoneNo = edPhoneNo.getText().toString().trim();
        String email = edEmail.getText().toString().trim();
        String gstin = edGstin.getText().toString().trim();

        if (email != null && !email.equals("")) {
            isValid = Validation.isValidEmail(email);
        }

        double comm = 0;
        if (commission != null && !commission.equals("")) {
            try {
                comm = Double.parseDouble(commission);
                isValid = true;
            } catch (NumberFormatException ne) {
                isValid = false;
                edCommission.setError(getResources().getString(R.string.error_numberformate));
            }

        }


        if (!agentName.equals("") && agentName != null) {

            if (isValid) {
                AgentDatum agentData = new AgentDatum();
                agentData.setAgentName(agentName);
                agentData.setEffectiveDate(selectedDate);
                agentData.setAddress(address);
                agentData.setMobile(phoneNo);
                agentData.setEmail(email);
                agentData.setCommission(comm);
                agentData.setGstinNo(gstin);
                agentData.setEcommerce((String) spinnerEcommerce.getSelectedItem());
                agentData.setStatus((String) spinnerStatus.getSelectedItem());
                if (callingFor.equals(Constant.CALL_EDITAGENT)) {
                    agentData.setAgentId(agentDetail.getAgentId());
                }

                String url = serverUrl + "/hipos//1/" + Constant.FUNTION_ADDAGENT;

                if (serverUrl != null) {
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

                                        Gson gson = new Gson();
                                        try {
                                            JSONObject jsonObject = new JSONObject(result.toString());
                                            AgentDatum agent = gson.fromJson(jsonObject.toString(), AgentDatum.class);

                                            if (agent != null) {
                                                UtilView.showToast(activity, "Agent Details Added Successfully.");
                                                Intent returnIntent = new Intent();
                                                activity.setResult(Activity.RESULT_OK, returnIntent);
                                                activity.finish();
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
                        task.execute(new Gson().toJson(agentData), url, "");

                    }
                    if (!isInternatePrsenet) {
                        UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
                    }
                } else {
                    UtilView.gotToLogin(activity);
                }

            } else {
                edEmail.setError(getResources().getString(R.string.err_msg_email));
            }

        } else {
            if (agentName == null || agentName.equals("")) {
                edAgentName.setError(getResources().getString(R.string.err_msg));
            }


        }


    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        edAgentName.setError(null);
        edCommission.setError(null);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @OnClick({R.id.edDate, R.id.btnSave, R.id.btnClose})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edDate:
                getDatePicker(activity);
                break;
            case R.id.btnSave:
                addAgent();
                break;
            case R.id.btnClose:
                finish();
                break;
        }
    }

    public void getDatePicker(Activity mActivity) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final TimeZone utc = TimeZone.getTimeZone("UTC");
        final SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        f.setTimeZone(utc);

        DatePickerDialog datePickerDialog = new DatePickerDialog(mActivity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                edDate.setText(String.valueOf(new StringBuilder().append(dayOfMonth).append("/").append(month + 1).append("/").append(year)));
                calendar.set(year, month, dayOfMonth);
                selectedDate = f.format(calendar.getTime());
            }
        }, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();


    }
}
