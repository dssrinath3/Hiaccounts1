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
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.agent.AgentDatum;
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.email_server.EmailServerDatum;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;
import in.hiaccounts.utility.Validation;

/**
 * Created by Prateek on 8/21/2017.
 */

public class Activity_Config_EmailServer extends AppCompatActivity implements TextWatcher {


    public static String TAG = Activity_Config_EmailServer.class.getSimpleName();
    EmailServerDatum emailServerDetail;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edEmail)
    EditText edEmail;
    @BindView(R.id.edPassword)
    EditText edPassword;
    @BindView(R.id.edPortNumber)
    EditText edPortNumber;
    @BindView(R.id.edServer)
    EditText edServer;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.btnClose)
    Button btnClose;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    String callingFor, serverUrl;
    private Activity activity;
    private ServiceHandler serviceHandler;
    private boolean isInternatePrsenet = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpanel_configemailserver);
        ButterKnife.bind(this);
        initComponentView();
    }

    private void initComponentView() {

        activity = this;
        serviceHandler = new ServiceHandler(activity);
        serverUrl = UtilView.getUrl(activity);
        Intent intent = getIntent();

        if (intent != null) {
            callingFor = intent.getStringExtra("callingFor");
            if (callingFor != null) {
                toolbar.setTitle(callingFor);
                if (callingFor.equals(Constant.CALL_EDITEMAILSERVER)) {
                    emailServerDetail = (EmailServerDatum) intent.getSerializableExtra("emailServerData");
                    if (emailServerDetail != null) {
                        if (emailServerDetail.getUserName() != null)
                            edEmail.setText(emailServerDetail.getUserName());
                        if (emailServerDetail.getPassword() != null)
                            edPassword.setText(emailServerDetail.getPassword());
                        if (emailServerDetail.getSmtpHost() != null)
                            edServer.setText(emailServerDetail.getSmtpHost());
                        if (emailServerDetail.getPort() != null)
                            edPortNumber.setText(emailServerDetail.getPort());
                    }
                }
            }
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

        edPortNumber.addTextChangedListener(this);
        edServer.addTextChangedListener(this);
        edPassword.addTextChangedListener(this);
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


    private void addEmailServer() {

        boolean isValid = true;
        String password = edPassword.getText().toString().trim();
        String smtpServer = edServer.getText().toString().trim();
        String smtpPort = edPortNumber.getText().toString().trim();
        String email = edEmail.getText().toString().trim();


        if (email != null && !email.equals("")) {
            isValid = Validation.isValidEmail(email);
        }
        if (!email.equals("") && email != null && !password.equals("") && password != null
                && !smtpServer.equals("") && smtpServer != null
                && !smtpPort.equals("") && smtpPort != null) {

            if (isValid) {
                EmailServerDatum emailData = new EmailServerDatum();

                emailData.setPassword(password);
                emailData.setPort(smtpPort);
                emailData.setSmtpHost(smtpServer);
                emailData.setUserName(email);

                if (callingFor.equals(Constant.CALL_EDITEMAILSERVER)) {
                    emailData.setId(emailServerDetail.getId());
                }

                String url = serverUrl + "/hipos//1/" + Constant.FUNTION_ADDEMAILSERVER;

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
                                                UtilView.showToast(activity, "Email Server Added Successfully.");
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
                        task.execute(new Gson().toJson(emailData), url, "");

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
            if (password == null || password.equals("")) {
                edPassword.setError(getResources().getString(R.string.err_msg));
            }
            if (smtpServer == null || smtpServer.equals("")) {
                edServer.setError(getResources().getString(R.string.err_msg));
            }
            if (smtpPort == null || smtpPort.equals("")) {
                edPortNumber.setError(getResources().getString(R.string.err_msg));
            }
            if (email == null || email.equals("")) {
                edEmail.setError(getResources().getString(R.string.err_msg));
            }


        }


    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        edEmail.setError(null);
        edPassword.setError(null);
        edPortNumber.setError(null);
        edServer.setError(null);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }


    @OnClick({R.id.btnSave, R.id.btnClose})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSave:
                addEmailServer();
                break;
            case R.id.btnClose:
                finish();
                break;
        }
    }
}
