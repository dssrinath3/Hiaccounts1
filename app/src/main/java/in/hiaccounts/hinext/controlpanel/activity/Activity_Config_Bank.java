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
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.bank.BankDatum;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;
import in.hiaccounts.utility.Validation;

/**
 * Created by Prateek on 8/21/2017.
 */

public class Activity_Config_Bank extends AppCompatActivity implements TextWatcher {


    public static String TAG = Activity_Config_Bank.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edAccountNumber)
    EditText edAccountNumber;
    @BindView(R.id.edBankName)
    EditText edBankName;
    @BindView(R.id.edBranchName)
    EditText edBranchName;
    @BindView(R.id.edBankAddress)
    EditText edBankAddress;
    @BindView(R.id.edPhoneNo)
    EditText edPhoneNo;
    @BindView(R.id.edEmail)
    EditText edEmail;
    @BindView(R.id.edBankIFSCCode)
    EditText edBankIFSCCode;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.btnClose)
    Button btnClose;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    BankDatum bankDetail;
    String serverUrl;
    String callingFor;
    private Activity activity;
    private ServiceHandler serviceHandler;
    private boolean isInternatePrsenet = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpanel_configbank);
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
                if (callingFor.equals(Constant.CALL_EDITBANK)) {
                    bankDetail = (BankDatum) intent.getSerializableExtra("bankData");
                    if (bankDetail != null) {
                        if (bankDetail.getBankAccountNo() != null)
                            edAccountNumber.setText(bankDetail.getBankAccountNo());

                        if (bankDetail.getBankName() != null)
                            edBankName.setText(bankDetail.getBankName());

                        if (bankDetail.getBranchName() != null)
                            edBranchName.setText(bankDetail.getBranchName());

                        if (bankDetail.getAddress() != null)
                            edBankAddress.setText(bankDetail.getAddress());

                        if (bankDetail.getBankPhoneNo() != null)
                            edPhoneNo.setText(bankDetail.getBankPhoneNo());

                        if (bankDetail.getBankEmail() != null)
                            edEmail.setText(bankDetail.getBankEmail());

                        if (bankDetail.getiFSCCode() != null)
                            edBankIFSCCode.setText(bankDetail.getiFSCCode());
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


        edAccountNumber.addTextChangedListener(this);
        edBankName.addTextChangedListener(this);
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

    @OnClick({R.id.btnSave, R.id.btnClose})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSave:

                addBank();
                break;
            case R.id.btnClose:
                finish();
                break;
        }
    }

    private void addBank() {

        boolean isValid = true;
        String accountnumber = edAccountNumber.getText().toString().trim();
        String bankName = edBankName.getText().toString().trim();
        String branchName = edBranchName.getText().toString().trim();
        String bankAddress = edBankAddress.getText().toString().trim();
        String phoneNo = edPhoneNo.getText().toString().trim();
        String email = edEmail.getText().toString().trim();
        String bankifsccode = edBankIFSCCode.getText().toString().trim();

        if (email != null && !email.equals("")) {
            isValid = Validation.isValidEmail(email);
        }


        if (!bankName.equals("") && bankName != null && accountnumber != null && !accountnumber.equals("")) {

            if (isValid) {


                BankDatum bankData = new BankDatum();
                bankData.setBankAccountNo(accountnumber);
                bankData.setBankName(bankName);
                bankData.setAddress(bankAddress);
                bankData.setBankEmail(email);
                bankData.setBranchName(branchName);
                bankData.setBankPhoneNo(phoneNo);
                bankData.setiFSCCode(bankifsccode);

                if (callingFor.equals(Constant.CALL_EDITBANK)) {
                    bankData.setBankId(bankDetail.getBankId());
                }


                String url = serverUrl + "/hipos//0/" + Constant.FUNTION_ADDBANK;

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
                                            BankDatum bankData = gson.fromJson(jsonObject.toString(), BankDatum.class);

                                            if (bankData != null) {
                                                UtilView.showToast(activity, "Bank Details Added Successfully.");
                                                Intent returnIntent = new Intent();
                                                returnIntent.putExtra("bankData", bankData);
                                                activity.setResult(Activity.RESULT_OK, returnIntent);
                                                activity.finish();
                                            }


                                        } catch (Exception e) {
                                            UtilView.showLogCat(TAG, "TaskCompleteListener Exception " + e.toString());
                                        }
                                    }
                                } else {
                                    progressBar.setVisibility(View.GONE);
                                    UtilView.showErrorDialog(getResources().getString(R.string.error_null), activity);
                                }
                            }
                        }, false);
                        task.execute(new Gson().toJson(bankData), url, "");

                    }
                    if (!isInternatePrsenet) {
                        UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
                    }
                } else {
                    UtilView.getUrl(activity);
                }

            } else {
                edEmail.setError(getResources().getString(R.string.err_msg_email));
            }

        } else {
            if (bankName == null || bankName.equals("")) {
                edBankName.setError(getResources().getString(R.string.err_msg));
            }

            if (accountnumber == null || accountnumber.equals("")) {
                edAccountNumber.setError(getResources().getString(R.string.err_msg));
            }
        }


    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        edAccountNumber.setError(null);
        edBankName.setError(null);
    }

    @Override
    public void afterTextChanged(Editable s) {


    }
}
