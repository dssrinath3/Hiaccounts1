package in.hiaccounts.hinext.application.activity;

import android.Manifest;
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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;
import com.vistrav.ask.Ask;
import com.vistrav.ask.annotations.AskDenied;
import com.vistrav.ask.annotations.AskGranted;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.model.LoginDetail;
import in.hiaccounts.hinext.application.model.PageLoadDataForAll;
import in.hiaccounts.hinext.company_setup.activity.Activity_CompanySubcription;
import in.hiaccounts.hinext.controlpanel.model.company_setup.compnayinformation.CompanyData;
import in.hiaccounts.hinext.purchase.model.purchase_pagedata.HinextPurchasePageData;
import in.hiaccounts.hinext.restaurant.activity.RestuarantActivity;
import in.hiaccounts.model.purchase.hinextpurchase.PageLoadData;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;
import in.hiaccounts.utility.Validation;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static in.hiaccounts.R.string.response_error;

/**
 * Created by Prateek on 3/18/2017.
 */
public class Activity_Login extends AppCompatActivity implements TextWatcher {
    public static int PERMISSION_ID = 1;
    public static String TAG = Activity_Login.class.getSimpleName();
    @BindView(R.id.edCompanyEmail)
    EditText edCompanyEmail;
    @BindView(R.id.edURLPath)
    EditText edURLPath;
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
    @BindView(R.id.checkBox)
    CheckBox checkBox;
    @BindView(R.id.checkBoxRemember)
    CheckBox checkBoxRemember;
    Boolean isInternetPresent = false;
    SharedPreference sharedPreference;
    Activity activity;
    String compnayEmail;
    String userName;
    String password;
    String callingFrom = "";
    boolean[] isPermissionGranted = new boolean[4];
    private ServiceHandler serviceHandler;
    private String  serverUrl, companyRegNo="",userId="",accessToken="";
    private PageLoadDataForAll pageData;

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
        serverUrl = UtilView.getUrl(activity);
        serviceHandler = new ServiceHandler(activity);
        sharedPreference = SharedPreference.getInstance(activity);

        Intent intent = getIntent();
        rememberDetails();


        if (intent != null) {
            callingFrom = intent.getStringExtra("callingFrom");
        }
   /*   edCompanyEmail.setText("demo@hiaccounts.in");
        edUsername.setText("sri1");
        edPassword.setText("sri1");
        edURLPath.setText("http://cloud.hiaccounts.com:8090");*/
      /*  edCompanyEmail.setText("hiaccounts@gmail.com");
        edUsername.setText("sri");
        edPassword.setText("sri");
        edURLPath.setText("http://13dc52b3.ngrok.io");*/
        edCompanyEmail.setText("shwethabaradwaj1808@gmail.com");
        edUsername.setText("sri");
        edPassword.setText("sri");
        edURLPath.setText("http://4a506bf2.ngrok.io");
    /*    edCompanyEmail.setText("vidya94cs@gmail.com");
        edUsername.setText("sri");
        edPassword.setText("sri");
        edURLPath.setText("http://73f61d5d.ngrok.io");*/
/*        edBaseUrl.addTextChangedListener(this);
        edPortNumber.addTextChangedListener(this);
        edApplicationname.addTextChangedListener(this);*/
        edCompanyEmail.addTextChangedListener(this);
        edUsername.addTextChangedListener(this);
        edPassword.addTextChangedListener(this);
        callPermission();
    }

    private void callPermission() {
        try {
            Ask.on(activity)
                    .id(PERMISSION_ID) // in case you are invoking multiple time Ask from same activity or fragment
                    .forPermissions(READ_EXTERNAL_STORAGE,
                            WRITE_EXTERNAL_STORAGE,
                            CAMERA,
                            ACCESS_COARSE_LOCATION
                    )
                    .withRationales(getResources().getString(R.string.permission_read_storage),
                            getResources().getString(R.string.permission_write_storage),
                            getResources().getString(R.string.permission_camera),
                            getResources().getString(R.string.permission_access_coarse_location)) //optional
                    .go();
        }catch (Exception e){

        }
    }

    //optional
    @AskDenied(Manifest.permission.READ_EXTERNAL_STORAGE)
    public void readfileAccessDenied(int id) {
        isPermissionGranted[0] = false;
    }

    //optional
    @AskDenied(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public void writefileAccessDenied(int id) {
        isPermissionGranted[1] = false;
    }

    //optional
    @AskDenied(Manifest.permission.CAMERA)
    public void cameraAccessDenied(int id) {
        isPermissionGranted[2] = false;
    }

    //optional
    @AskDenied(Manifest.permission.ACCESS_COARSE_LOCATION)
    public void locationAccessDenied(int id) {
        isPermissionGranted[3] = false;
    }

    //optional
    @AskGranted(Manifest.permission.READ_EXTERNAL_STORAGE)
    public void writefileAccessGranted(int id) {
        isPermissionGranted[0] = true;
    }

    //optional
    @AskGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public void readfileAccessGranted(int id) {
        isPermissionGranted[1] = true;
    }

    //optional
    @AskGranted(Manifest.permission.CAMERA)
    public void cameraAccessGranted(int id) {
        isPermissionGranted[2] = true;
    }

    //optional
    @AskGranted(Manifest.permission.ACCESS_COARSE_LOCATION)
    public void locationAccessGranted(int id) {
        isPermissionGranted[3] = true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();


        if (callingFrom != null && !callingFrom.equals("")) {
            if (callingFrom.equals("ServerConfig")) {
                finish();
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                finishAffinity();
            }
            System.exit(0);
        }

    }


    @OnClick({R.id.checkBoxRemember,R.id.btnSubmit, R.id.llBack, R.id.tvForgotPassword, R.id.btnSignup})
    public void onViewClicked(View v) {
        switch (v.getId()) {

            case R.id.tvForgotPassword:
                break;

            case R.id.btnSignup:

                isInternetPresent = serviceHandler.isConnectingToInternet();
                if (isInternetPresent) {
                    Intent intent = new Intent(activity, Activity_SignUp.class);
                    startActivity(intent);
                } else {
                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
                }
                break;


            case R.id.btnSubmit:
/*
                int flag = 0;
                for (int i = 0; i < isPermissionGranted.length; i++) {
                    if (!isPermissionGranted[i]) {
                        flag++;
                    }
                }

                if (flag != 0) {
                    callPermission();
                } else {*/

                    isInternetPresent = serviceHandler.isConnectingToInternet();

               /* String baseUrl = edBaseUrl.getText().toString().trim();
                String portNumber = edPortNumber.getText().toString().trim();
                String applicationName = edApplicationname.getText().toString().trim();
               */
                    String compnayEmail = edCompanyEmail.getText().toString().trim();
                    String userName = edUsername.getText().toString().trim();
                    String password = edPassword.getText().toString().trim();
                    String urlPath=edURLPath.getText().toString().trim();

             /*   if (baseUrl.equals("")) {

                    edBaseUrl.setError(getResources().getString(R.string.err_msg_baseurl));
                }
                if (portNumber.equals("")) {

                    edPortNumber.setError(getResources().getString(R.string.err_msg_portnumber));
                }
                if (applicationName.equals("")) {

                    edApplicationname.setError(getResources().getString(R.string.err_msg_application));
                }*/


                    if (!urlPath.equals("")&&!compnayEmail.equals("") && !userName.equals("") && !password.equals("")) {

                        if (isInternetPresent) {
                            Constant.BASE_URL=urlPath;

                            if (Validation.isValidEmail(compnayEmail)) {
                                callLoginUrl(urlPath,compnayEmail, userName, password);
                            } else {
                                edCompanyEmail.setError(getResources().getString(R.string.err_msg_email));
                            }


                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
                        }
                    } else {
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

               // }
                break;
            default:
                break;

        }


    }

    private void rememberDetails() {
        if (sharedPreference!=null) {

            String loginDetailData = sharedPreference.getData(Constant.REMEMBERLOGINDETAILS);

            if (loginDetailData != null) {

                try {
                    JSONObject jsonData = new JSONObject(loginDetailData.toString());
                    String url          = jsonData.getString("urlpath");
                    String email        = jsonData.getString("companyEmail");
                    String username     = jsonData.getString("userName");
                    String password     = jsonData.getString("password");

                    if (url !=null && email !=null && username !=null && password !=null )
                    {
                        checkBoxRemember.setChecked(true);
                        edURLPath.setText(url.toString());
                        edCompanyEmail.setText(email.toString());
                        edUsername.setText(username.toString());
                        edPassword.setText(password.toString());
                    }
                    else {
                        checkBoxRemember.setChecked(false);
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                edURLPath.setText("");
                edCompanyEmail.setText("");
                edUsername.setText("");
                edPassword.setText("");
            }
        }

    }


    private void callLoginUrl(String urlPath, String companyEmail, String userName, String password) {
        try {
            JSONObject requestJson = new JSONObject();
            requestJson.put(Constant.COMPANYEMAIL, companyEmail);


            requestJson.put(Constant.USERNAME, userName);
            requestJson.put(Constant.PASSWORD, password);
            requestJson.put("urlpath",urlPath);
            sharedPreference.saveData(Constant.USERNAME, userName);
            if (checkBoxRemember.isChecked()){
                sharedPreference.saveData(Constant.REMEMBERLOGINDETAILS,requestJson.toString());

            }else {
                sharedPreference.setRemovePrefrence(Constant.REMEMBERLOGINDETAILS);
            }


            UtilView.showProgessBar(this, progressBar);
            PostDataTask postDataTask = new PostDataTask(activity, new TaskCompleteListener(), false);
            postDataTask.execute(requestJson.toString(), Constant.BASE_URL+"/hipos/login", Constant.FUNCTION_LOGIN);


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
            UtilView.hideProgessBar(Activity_Login.this, progressBar);
            try{
                LoginDetail loginDetail = new LoginDetail();
                JSONObject jsonObject=new JSONObject(result);
                if(jsonObject.has("companyRegNo")){
                    companyRegNo= jsonObject.optString("companyRegNo","companyRegNo");
                }
                accessToken= jsonObject.getString("accessToken");
                userId= jsonObject.getString("userId");
                loginDetail.setAuthToken(accessToken);
                if (companyRegNo!=null && !companyRegNo.equals("")){
                    loginDetail.setCompanyRegNo(companyRegNo);
                }else{
                    loginDetail.setCompanyRegNo("");
                }
                if (userId != null && userId.equals("")) {
                    loginDetail.setUserId(userId);
                }else{
                    loginDetail.setUserId("");
                }

                loginDetail.setRedirecturli(Constant.BASE_URL);
                sharedPreference.saveData(Constant.LOGINDETAIL, new Gson().toJson(loginDetail));
                sharedPreference.saveData(Constant.ACCESSTOKEN, accessToken);
                sharedPreference.saveData(Constant.SERVER_URL, Constant.BASE_URL);

                // Constant.NGROK_URL = loginDetail.getRedirecturli();
                UtilView.showProgessBar(Activity_Login.this, progressBar);

                GetDataTask task = new GetDataTask(activity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(activity, progressBar);
                        if (result != null) {
                            try {





//                                JSONObject jsonData = new JSONObject();
//                                jsonData.put("url",edURLPath.getText().toString());
//                                jsonData.put("username",edUsername);
//                                jsonData.put("password",edPassword);
//                                jsonData.put("email",edCompanyEmail);
//

//
                                JSONObject jsonObject = new JSONObject(result.toString());


                                String pageNumber=jsonObject.getString("pageName");
                                sharedPreference.saveData(Constant.NAVIGATION_REDIRECTPAGE,pageNumber);

                                if (pageNumber!=null){
                                    if (pageNumber.equals(Constant.PAGE_DAHSBOARD)){

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
                                                            sharedPreference.saveData(Constant.ISRESTARUSER, ""+checkBox.isChecked());


                                                            if (checkBox.isChecked()){
                                                                Intent intent = new Intent(activity, RestuarantActivity.class);
                                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                                startActivity(intent);
                                                                finish();
                                                            }else {
                                                                Intent intent = new Intent(activity, NavigationDrawer_Activity.class);
                                                                intent.putExtra(Constant.NAVIGATION_GROUPHEADER, Constant.NAVIGATION_DASHBOARD);
                                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                                startActivity(intent);
                                                                finish();
                                                            }







                                                        } else {
                                                            UtilView.showErrorDialog(getResources().getString(R.string.response_error), activity);
                                                        }


                                                    } catch (Exception e) {
                                                        UtilView.showErrorDialog(getResources().getString(R.string.response_error), activity);
                                                    }
                                                }

                                            }
                                        }, false);
                                        task.execute(Constant.BASE_URL + Constant.FUNTION_GETCOMPANYDATA, "");



                                    }else {
                                        Intent intent = new Intent(activity, Activity_CompanySubcription.class);
                                        intent.putExtra(Constant.NAVIGATION_REDIRECTPAGE, pageNumber);
                                        startActivity(intent);
                                        finish();
                                    }
                                }



                            } catch (JSONException e) {
                                UtilView.showErrorDialog(getResources().getString(R.string.response_error), activity);
                            }
                        }

                    }
                }, false);
                task.execute(Constant.BASE_URL+ Constant.FUNTION_GETCOMPANYSTATUS, "");
            }catch (Exception e){

            }





       /*     if (result != null) {
                try {
                    Gson gson = new Gson();
                    LoginDetail loginDetail = gson.fromJson(result.toString(), LoginDetail.class);
                    if (loginDetail != null) {
                        if (loginDetail.getStatus() != null) {
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
                                    UtilView.showProgessBar(Activity_Login.this, progressBar);
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
                                } else {
                                    UtilView.showErrorDialog(getResources().getString(R.string.response_error), activity);
                                }
                            }
                        }
                    } else {
                        UtilView.showErrorDialog(getResources().getString(R.string.response_error), activity);
                    }
                } catch (Exception e) {
                    UtilView.showErrorDialog(getResources().getString(R.string.response_error), activity);
                }
            }
*/
        }
    }






}
