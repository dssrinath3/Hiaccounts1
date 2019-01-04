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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.controlpanel.model.useraccount_setup.Location;
import in.hiaccounts.hinext.controlpanel.model.useraccount_setup.UserAccountData;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;
import in.hiaccounts.utility.Validation;

/**
 * Created by Prateek on 8/21/2017.
 */

public class Activity_Config_User extends AppCompatActivity implements TextWatcher {


    UserAccountData userDetail;

    public static String TAG = Activity_Config_User.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edUserName)
    EditText edUserName;
    @BindView(R.id.edPassowrd)
    EditText edPassowrd;
    @BindView(R.id.edFullname)
    EditText edFullname;
    @BindView(R.id.edSQuestion)
    EditText edSQuestion;
    @BindView(R.id.edAnswer)
    EditText edAnswer;
    @BindView(R.id.edTNumber)
    EditText edTNumber;
    @BindView(R.id.edEmail)
    EditText edEmail;
    @BindView(R.id.spinnerLocation)
    Spinner spinnerLocation;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.btnClose)
    Button btnClose;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.tvCurrentPassword)
    TextView tvCurrentPassword;
    @BindView(R.id.llCurrentPassword)
    LinearLayout llCurrentPassword;
    @BindView(R.id.edNewPassowrd)
    EditText edNewPassowrd;
    @BindView(R.id.llNewPassPassword)
    LinearLayout llNewPassPassword;
    @BindView(R.id.llFaxNo)
    LinearLayout llFaxNo;
    @BindView(R.id.textView7)
    TextView textView7;
    @BindView(R.id.llGSTIN)
    LinearLayout llGSTIN;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.ll1)
    LinearLayout ll1;
    @BindView(R.id.spinnerStatus)
    Spinner spinnerStatus;

    private Activity activity;
    private ServiceHandler serviceHandler;
    private boolean isInternatePrsenet = false;
    String callingFor, serverUrl;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uaccount_user);
        ButterKnife.bind(this);
        initComponentView();
    }

    private void initComponentView() {

        activity = this;
        serviceHandler = new ServiceHandler(activity);
        serverUrl= UtilView.getUrl(activity);
        isInternatePrsenet = serviceHandler.isConnectingToInternet();
        getUserAccountSetupDetail();


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

        //UtilView.setupAdapter(activity, spinnerLocation, getResources().getStringArray(R.array.config_agent_ecoomerce));


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

    private void setUpInitView(List<Location> list) {
        Intent intent = getIntent();

        if (intent != null) {
            callingFor = intent.getStringExtra("callingFor");
            toolbar.setTitle(callingFor);
            UtilView.setUserLocationAdapter(activity, spinnerLocation, list);
            UtilView.setupAdapter(activity, spinnerStatus, getResources().getStringArray(R.array.usersetup_status));

            if (callingFor.equals(Constant.CALL_EDITUSER)) {


                llNewPassPassword.setVisibility(View.VISIBLE);
                tvCurrentPassword.setText("Current Password");
                edUserName.setFocusable(false);
                edUserName.setFocusableInTouchMode(false);
                edUserName.setBackgroundColor(getResources().getColor(R.color.colorGrey));


                userDetail = (UserAccountData) intent.getSerializableExtra("userData");
                if (userDetail.getUserLoginId() != null)
                    edUserName.setText(userDetail.getUserLoginId());
                if (userDetail.getFullName() != null)
                    edFullname.setText(userDetail.getFullName());
                if (userDetail.getEmail() != null)
                    edEmail.setText(userDetail.getEmail());
                if (userDetail.getSecurityQuestion() != null)
                    edSQuestion.setText(userDetail.getSecurityQuestion());
                if (userDetail.getSecurityAnswer() != null)
                    edAnswer.setText(userDetail.getSecurityAnswer());
                if (userDetail.getPhone() != null)
                    edTNumber.setText(userDetail.getPhone());
                if (userDetail.getCurrentPassword() != null)
                    edPassowrd.setText(userDetail.getCurrentPassword());

                if (userDetail.getLocation() != null) {
                    if (userDetail.getLocation().getInventoryLocationId() != null) {
                        for (int i = 0; i < list.size(); i++) {
                            if (userDetail.getLocation().getInventoryLocationId().equals(list.get(i).getInventoryLocationId())) {
                                spinnerLocation.setSelection(i);
                            }
                        }

                    }
                }

            }

            if (callingFor.equals(Constant.CALL_ADDUSER)) {
                llNewPassPassword.setVisibility(View.GONE);
            }
        }

    }

    private void getUserAccountSetupDetail() {
        String url = serverUrl + "/hipos//0/" + Constant.FUNTION_ADDUSERDATA;

        if (serverUrl != null) {
            if (isInternatePrsenet) {

                UtilView.showProgessBar(activity, progressBar);
                GetDataTask task = new GetDataTask(activity, new AsyncTaskCompleteListener<String>() {
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
                                List<Location> list = new ArrayList<>();
                                try {
                                    JSONArray jsonArray = new JSONArray(result.toString());
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                                        Location location = gson.fromJson(jsonObject.toString(), Location.class);
                                        list.add(location);

                                    }
                                    if (list.size() > 0) {

                                        setUpInitView(list);

                                    } else {

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
                task.execute(url, "");
            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
            }
        } else {
            UtilView.gotToLogin(activity);
        }

        //task.execute(url, "");


    }


    private void addUser() {

        boolean isValid = true;
        String userName = edUserName.getText().toString().trim();
        String userPassword = edPassowrd.getText().toString().trim();
        String newPassword = edNewPassowrd.getText().toString().trim();
        String fullName = edFullname.getText().toString().trim();
        String securityQues = edSQuestion.getText().toString().trim();
        String securityAns = edAnswer.getText().toString().trim();
        String email = edEmail.getText().toString().trim();
        String phoneNumber = edTNumber.getText().toString().trim();
        Location locationId = (Location) spinnerLocation.getSelectedItem();
        String status = (String) spinnerStatus.getSelectedItem();

        if (email != null && !email.equals("")) {
            isValid = Validation.isValidEmail(email);
        }
        if (!userName.equals("") && userName != null
                && !userPassword.equals("") && userPassword != null
                && !fullName.equals("") && fullName != null
                && !securityQues.equals("") && securityQues != null
                && !securityAns.equals("") && securityAns != null
                && locationId != null
                && !phoneNumber.equals("") && phoneNumber != null
                && !email.equals("") && email != null
                ) {

            if (callingFor.equals(Constant.CALL_EDITUSER)) {
                if (newPassword.equals("") || newPassword == null) {
                    isValid = false;
                    edNewPassowrd.setError(getResources().getString(R.string.err_msg));
                }
            }


            if (isValid) {


                UserAccountData userAccountData = new UserAccountData();
                userAccountData.setUserLoginId(userName);

                userAccountData.setFullName(fullName);
                userAccountData.setSecurityQuestion(securityQues);
                userAccountData.setSecurityAnswer(securityAns);
                userAccountData.setPhone(phoneNumber);
                userAccountData.setEmail(email);
                userAccountData.setLocation(locationId);
                userAccountData.setStatus(status);


                if (callingFor != null) {
                    if (callingFor.equals(Constant.CALL_EDITUSER)) {
                        if (userDetail != null) {
                            if (userDetail.getUseraccountId() != null)
                                userAccountData.setUseraccountId(userDetail.getUseraccountId());
                            userAccountData.setCurrentPassword(userPassword);
                            userAccountData.setPasswordUser(newPassword);
                        }
                    }
                    if (callingFor.equals(Constant.CALL_ADDUSER)) {
                        userAccountData.setPasswordUser(userPassword);
                    }

                }

                String url = serverUrl + "/hipos//1/" + Constant.FUNTION_ADDUSERDETAIL;

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

                                        Gson gson = new Gson();
                                        try {
                                            JSONObject jsonObject = new JSONObject(result.toString());
                                            UserAccountData data = gson.fromJson(jsonObject.toString(), UserAccountData.class);
                                            if (data != null) {
                                                if (data.getApimessage() != null) {
                                                    UtilView.showToast(activity, data.getApimessage());
                                                }else {
                                                    UtilView.showToast(activity,"User Add Successfully.");
                                                    Intent returnIntent = new Intent();
                                                    activity.setResult(Activity.RESULT_OK, returnIntent);
                                                    activity.finish();
                                                }

                                            }

                                            /*if (data.getApimessage()!=null && data.getApimessage().equals("User count is exceeded.")){
                                                UtilView.showToast(activity,data.getApimessage());
                                                resetView();
                                            }
                                            else if (data.getApimessage().equals("Empty Data")){
                                                UtilView.showToast(activity,"Some field is missing");

                                            }
                                            else
                                                if (data != null) {
                                                    UtilView.showToast(activity,"User Add Successfully.");
                                                    Intent returnIntent = new Intent();
                                                    activity.setResult(Activity.RESULT_OK, returnIntent);
                                                    activity.finish();
                                                }*/

                                        } catch (Exception e) {
                                            UtilView.showLogCat(TAG, "TaskCompleteListener Exception " + e.toString());
                                        }
                                    }
                                } else {
                                    UtilView.showErrorDialog(getResources().getString(R.string.error_null), activity);
                                }
                            }
                        }, false);
                        task.execute(new Gson().toJson(userAccountData), url, "");

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
            if (userName == null || userName.equals("")) {
                edUserName.setError(getResources().getString(R.string.err_msg));
            }
            if (userPassword == null || userPassword.equals("")) {
                edPassowrd.setError(getResources().getString(R.string.err_msg));
            }
            if (fullName == null || fullName.equals("")) {
                edFullname.setError(getResources().getString(R.string.err_msg));
            }
            if (securityQues == null || securityQues.equals("")) {
                edSQuestion.setError(getResources().getString(R.string.err_msg));
            }
            if (securityAns == null || securityAns.equals("")) {
                edAnswer.setError(getResources().getString(R.string.err_msg));
            }
            if (phoneNumber == null || phoneNumber.equals("")) {
                edTNumber.setError(getResources().getString(R.string.err_msg));
            }
            if (email == null || email.equals("")) {
                edEmail.setError(getResources().getString(R.string.err_msg));
            }
            if (locationId == null) {
                TextView tv = (TextView) spinnerLocation.getSelectedView();
                tv.setError(getResources().getString(R.string.err_msg));
            }


        }


    }

    private void resetView() {
        edUserName.setText("");
        edPassowrd.setText("");
        edFullname.setText("");
        edSQuestion.setText("");
        edAnswer.setText("");
        edTNumber.setText("");
        edEmail.setText("");
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @OnClick({R.id.btnSave, R.id.btnClose})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSave:
                addUser();
                break;
            case R.id.btnClose:
                finish();
                break;
        }
    }


}
