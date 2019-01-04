package in.hiaccounts.hinext.application.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.model.LoginDetail;
import in.hiaccounts.hinext.controlpanel.model.company_setup.compnayinformation.CompanyData;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;
import in.hiaccounts.utility.Validation;

/**
 * Created by Prateek on 3/18/2017.
 */
public class Activity_NGRokLogin extends AppCompatActivity implements TextWatcher {

    public static String TAG = Activity_NGRokLogin.class.getSimpleName();
    @BindView(R.id.edCompanyEmail)
    EditText edCompanyEmail;
    @BindView(R.id.edUsername)
    EditText edUsername;
    @BindView(R.id.edPassword)
    EditText edPassword;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;
    @BindView(R.id.llBack)
    LinearLayout llBack;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.tvForgotPassword)
    TextView tvForgotPassword;
    @BindView(R.id.btnSignup)
    Button btnSignup;
    Boolean isInternetPresent = false;
    SharedPreference sharedPreference;
    Activity activity;
    private ServiceHandler serviceHandler;
    String compnayEmail;
    String userName;
    String password;
    String callingFrom="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        initComponent();


    }

    private void initComponent() {
        ButterKnife.bind(this);
        activity = this;
        serviceHandler = new ServiceHandler(activity);
        sharedPreference = SharedPreference.getInstance(activity);

        Intent intent=getIntent();

        if (intent!=null){
            callingFrom=intent.getStringExtra("callingFrom");
        }

        edCompanyEmail.setText("demo@hiaccounts.in");
        edUsername.setText("bansal");
        edPassword.setText("bansal");

        /*edBaseUrl.addTextChangedListener(this);
        edPortNumber.addTextChangedListener(this);
        edApplicationname.addTextChangedListener(this);
        */
        edCompanyEmail.addTextChangedListener(this);
        edUsername.addTextChangedListener(this);
        edPassword.addTextChangedListener(this);


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();


        if (callingFrom!=null && !callingFrom.equals("")){
            if (callingFrom.equals("ServerConfig")){
                finish();
            }
        }
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                finishAffinity();
            }
            System.exit(0);
        }

    }


    @OnClick({R.id.btnSubmit,R.id.llBack,R.id.tvForgotPassword,R.id.btnSignup})
    public void onViewClicked(View v) {
        switch (v.getId()) {

            case R.id.tvForgotPassword:
                break;

            case R.id.btnSignup:
                isInternetPresent=serviceHandler.isConnectingToInternet();
                if (isInternetPresent) {
                    Intent intent = new Intent(activity, Activity_SignUp.class);
                    startActivity(intent);
                }else {
                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status),activity);
                }
                break;





            case R.id.btnSubmit:
                isInternetPresent = serviceHandler.isConnectingToInternet();

               /* String baseUrl = edBaseUrl.getText().toString().trim();
                String portNumber = edPortNumber.getText().toString().trim();
                String applicationName = edApplicationname.getText().toString().trim();
               */
               String compnayEmail = edCompanyEmail.getText().toString().trim();
                String userName = edUsername.getText().toString().trim();
                String password = edPassword.getText().toString().trim();

             /*   if (baseUrl.equals("")) {

                    edBaseUrl.setError(getResources().getString(R.string.err_msg_baseurl));
                }
                if (portNumber.equals("")) {

                    edPortNumber.setError(getResources().getString(R.string.err_msg_portnumber));
                }
                if (applicationName.equals("")) {

                    edApplicationname.setError(getResources().getString(R.string.err_msg_application));
                }*/


                if (!compnayEmail.equals("") && !userName.equals("") && !password.equals("")) {

                    if (isInternetPresent) {

                        if (Validation.isValidEmail(compnayEmail)) {

//                            callLoginUrl(baseUrl, portNumber, applicationName, compnayEmail, userName, password);
                            callLoginUrl(compnayEmail, userName, password);
                        } else {
                            edCompanyEmail.setError(getResources().getString(R.string.err_msg_email));
                        }


                    } else {
                        UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
                    }
                }else {
                    if (compnayEmail.equals("")) {

                        edCompanyEmail.setError(getResources().getString(R.string.err_msg_companyemail));
                    }
                    if (userName.equals("")) {
                        edUsername.setError(getResources().getString(R.string.err_msg_username));
                    }
                    if (password.equals("")) {
                        edPassword.setError(getResources().getString(R.string.err_msg_password));
                    }
                }
                break;

            default:
                break;

        }


    }

    private void callLoginUrl(String companyEmail, String userName, String password) {
        try {
            JSONObject requestJson = new JSONObject();
//            requestJson.put(Constant.COMPANYEMAIL, companyEmail);
            requestJson.put("emailId", companyEmail);

            requestJson.put(Constant.USERNAME, userName);
            requestJson.put(Constant.PASSWORD, password);
            sharedPreference.saveData(Constant.USERNAME,userName);
            UtilView.showProgessBar(this,progressBar);
            PostDataTask postDataTask = new PostDataTask(activity, new TaskCompleteListener(), false);
            postDataTask.execute(requestJson.toString(), Constant.BASE_URL,Constant.FUNCTION_LOGIN);


        } catch (Exception e) {
            UtilView.showLogCat(TAG, "callLoginUrl exception " + e.toString());

        }




        /*if (app_module.equals("hipos")) {


            RetailPos_PageData pageData = gson.fromJson(response.toString(), RetailPos_PageData.class);
            UtilView.showLogCat(TAG, "Josn Retail getPageData " + response.toString());
            Intent intent = new Intent(Activity_POSSelection.this, Activity_RetailPOS.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }

        if (app_module.equals("restaurant")) {
            RestraPos_PageData pageData = gson.fromJson(response.toString(), RestraPos_PageData.class);
            UtilView.showLogCat(TAG, "Josn Rastaurant getPageData " + response.toString());
            Intent intent = new Intent(Activity_POSSelection.this, Activity_RestraPOS.class);
            startActivity(intent);
            finish();
        }*/


    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        if (!s.equals("")) {
            edCompanyEmail.setError(null);
            edUsername.setError(null);
            edPassword.setError(null);
           /* edApplicationname.setError(null);
            edPortNumber.setError(null);
            edBaseUrl.setError(null);*/
        }

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    private class TaskCompleteListener implements AsyncTaskCompleteListener<String> {
        @Override
        public void onTaskComplete(String result) {
            UtilView.hideProgessBar(Activity_NGRokLogin.this,progressBar);
            if (result != null) {
                try {
                    Gson gson=new Gson();
                    LoginDetail loginDetail=gson.fromJson(result.toString(), LoginDetail.class);
                    if (loginDetail!=null){
                        if (loginDetail.getStatus()!=null) {
                            if (loginDetail.getStatus().equals("fail")) {
                                UtilView.showErrorDialog(getResources().getString(R.string.credentials_error), activity);
                            }
                            if (loginDetail.getStatus().equals("success")) {
                                if (loginDetail.getAuthToken() != null && !loginDetail.getAuthToken().equals("")
                                        && loginDetail.getRedirecturli() != null && !loginDetail.getRedirecturli().equals("")) {


                                    sharedPreference.saveData(Constant.LOGINDETAIL, new Gson().toJson(loginDetail));
                                    sharedPreference.saveData(Constant.ACCESSTOKEN, loginDetail.getAuthToken());
                                    sharedPreference.saveData(Constant.SERVER_URL, loginDetail.getRedirecturli());

                                   // Constant.NGROK_URL = loginDetail.getRedirecturli();
                                    UtilView.showProgessBar(Activity_NGRokLogin.this, progressBar);
                                    GetDataTask task = new GetDataTask(activity, new AsyncTaskCompleteListener<String>() {
                                        @Override
                                        public void onTaskComplete(String result) {
                                            UtilView.hideProgessBar(activity, progressBar);
                                            if (result != null) {
                                                try {
                                                    JSONObject jsonObject = new JSONObject(result.toString());
                                                    Gson gson = new Gson();
                                                    CompanyData companyData = gson.fromJson(jsonObject.toString(), CompanyData.class);
                                                    if (companyData != null) {
                                                        sharedPreference.saveData(Constant.COMPANYDATA, gson.toJson(companyData));
                                                        Intent intent = new Intent(activity, NavigationDrawer_Activity.class);
                                                        intent.putExtra(Constant.NAVIGATION_GROUPHEADER, Constant.NAVIGATION_DASHBOARD);
                                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                        startActivity(intent);
                                                        finish();
                                                    } else {
                                                        UtilView.showErrorDialog(getResources().getString(R.string.response_error), activity);
                                                    }


                                                } catch (Exception e) {
                                                    UtilView.showErrorDialog(getResources().getString(R.string.response_error), activity);
                                                }
                                            }

                                        }
                                    }, false);
                                    task.execute(loginDetail.getRedirecturli() + Constant.FUNTION_GETCOMPANYDATA, "");
                                }
                                else {
                                    UtilView.showErrorDialog(getResources().getString(R.string.response_error),activity);
                                }
                            }
                        }
                    }else {
                        UtilView.showErrorDialog(getResources().getString(R.string.response_error),activity);
                    }
                }catch (Exception e){
                    UtilView.showErrorDialog(getResources().getString(R.string.response_error),activity);
                }

            }
        }
    }


}
