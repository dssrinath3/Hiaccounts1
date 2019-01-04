package in.hiaccounts.hinext.controlpanel.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.servicecharge.ServiceChargeData;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

public class Activity_ServiceCharge extends AppCompatActivity implements TextWatcher {

    ServiceChargeData serviceChargeDetails;
    public static String TAG = Activity_ServiceCharge.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.edServiceChargeName)
    EditText edServiceChargeName;
    @BindView(R.id.llServiceChargeName)
    LinearLayout llServiceChargeName;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.edServicePercenatge)
    EditText edServicePercenatge;
    @BindView(R.id.llServicePercenatge)
    LinearLayout llServicePercenatge;
    @BindView(R.id.edSurgeryDate)
    EditText edSurgeryDate;
    @BindView(R.id.llsurgerydate)
    LinearLayout llsurgerydate;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.btnClose)
    Button btnClose;
    @BindView(R.id.ll1)
    LinearLayout ll1;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;

    private Activity activity;
    private ServiceHandler serviceHandler;
    private boolean isInternatePrsenet = false;
    String callingFor, serverUrl;
    String surgeryDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_charge);
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

                if (callingFor.equals(Constant.CALL_EDITSERVICECHARGE)) {
                    serviceChargeDetails = (ServiceChargeData) intent.getSerializableExtra("serviceChargeData");

                    if (serviceChargeDetails != null) {
                        if (serviceChargeDetails.getServiceChargeName() != null)
                            edServiceChargeName.setText(serviceChargeDetails.getServiceChargeName());

                        if (serviceChargeDetails.getServicePercentage() != null)
                            edServicePercenatge.setText("" +serviceChargeDetails.getServicePercentage());

                        if (serviceChargeDetails.getEffectiveDate() != null){
                            edSurgeryDate.setText("" +serviceChargeDetails.getEffectiveDate());
                            surgeryDate = serviceChargeDetails.getEffectiveDate();
                        }

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


        edServiceChargeName.addTextChangedListener(this);
        edServicePercenatge.addTextChangedListener(this);
    }



    @OnClick({R.id.edSurgeryDate, R.id.btnSave, R.id.btnClose})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edSurgeryDate:
                getSurgeryDatePicker(activity);
                break;
            case R.id.btnSave:
                addServiceCharge();
                break;
            case R.id.btnClose:
                finish();
                break;
        }
    }

    private void addServiceCharge() {
        boolean isValid = true;
        String serviceChargeName = edServiceChargeName.getText().toString().trim();
        String servicePercentage = edServicePercenatge.getText().toString().trim();
        double percent = 0;
        if (servicePercentage != null && !servicePercentage.equals("")) {
            try {
                percent = Double.parseDouble(servicePercentage);
                isValid = true;
            } catch (NumberFormatException ne) {
                isValid = false;
                edServicePercenatge.setError(getResources().getString(R.string.error_numberformate));
            }

        }
        if (!serviceChargeName.equals("") && serviceChargeName != null ) {


            ServiceChargeData serviceData = new ServiceChargeData();
            serviceData.setServiceChargeName(serviceChargeName);
            serviceData.setServicePercentage(percent);
            serviceData.setEffectiveDate(surgeryDate);


            if (callingFor.equals(Constant.CALL_EDITSERVICECHARGE)) {
                serviceData.setServiceChargeId(serviceChargeDetails.getServiceChargeId());
            }


            String url = serverUrl + "/hipos//1/" + Constant.FUNTION_ADDSERVICECHARGE;

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
                                        ServiceChargeData serviceCharge = gson.fromJson(jsonObject.toString(), ServiceChargeData.class);

                                        if (serviceCharge != null) {
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
                    task.execute(new Gson().toJson(serviceData), url, "");

                }
                if (!isInternatePrsenet) {
                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
                }
            } else {
                UtilView.gotToLogin(activity);
            }


        } else {
            if (serviceChargeName == null || serviceChargeName.equals("")) {
                edServiceChargeName.setError(getResources().getString(R.string.err_msg));
            }
            double comm = 0;
            if (servicePercentage == null && servicePercentage.equals("")) {
                try {
                    comm = Double.parseDouble(servicePercentage);
                    isValid = true;
                } catch (NumberFormatException ne) {
                    isValid = false;
                    edServicePercenatge.setError(getResources().getString(R.string.error_numberformate));
                }

            }
        }
    }
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    public void getSurgeryDatePicker(Activity mActivity) {
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
                edSurgeryDate.setText(String.valueOf(new StringBuilder().append(year).append("-").append(month + 1).append("-").append(dayOfMonth)));
                calendar.set(year, month, dayOfMonth);
                surgeryDate = f.format(calendar.getTime());
            }
        }, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();


    }
}
