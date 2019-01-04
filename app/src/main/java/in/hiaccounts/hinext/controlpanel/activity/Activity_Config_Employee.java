package in.hiaccounts.hinext.controlpanel.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
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
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.employee.EmployeeDatum;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 8/21/2017.
 */

public class Activity_Config_Employee extends AppCompatActivity implements TextWatcher {


    public static String TAG = Activity_Config_Employee.class.getSimpleName();
    EmployeeDatum employeeDetail;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edEName)
    EditText edEName;
    @BindView(R.id.edECode)
    EditText edECode;
    @BindView(R.id.edEAddress)
    EditText edEAddress;
    @BindView(R.id.edEPhone)
    EditText edEPhone;
    @BindView(R.id.edEDOB)
    EditText edEDOB;
    @BindView(R.id.edEDOJ)
    EditText edEDOJ;
    @BindView(R.id.spinnerEGender)
    Spinner spinnerEGender;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.btnClose)
    Button btnClose;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    String callingFor, serverUrl;
    String dateOfJoining = "", dateOfBirth = "";
    String edate, eval;
    @BindView(R.id.spinnerStatus)
    Spinner spinnerStatus;
    private Activity activity;
    private ServiceHandler serviceHandler;
    private boolean isInternatePrsenet = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpanel_configemployee);
        ButterKnife.bind(this);

        initComponentView();
    }

    private void initComponentView() {

        activity = this;
        serviceHandler = new ServiceHandler(activity);
        serverUrl = UtilView.getUrl(activity);
        Intent intent = getIntent();
        UtilView.setupAdapter(activity, spinnerEGender, getResources().getStringArray(R.array.config_employee_gender));
        UtilView.setupAdapter(activity, spinnerStatus, getResources().getStringArray(R.array.config_project_status));
        if (intent != null) {
            callingFor = intent.getStringExtra("callingFor");
            if (callingFor != null) {
                if (callingFor.equals("Restra")){
                    toolbar.setTitle(Constant.CALL_ADDEMPLOYEE);
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                }else {
                    toolbar.setTitle(Constant.CALL_ADDEMPLOYEE);
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }
            }

            if (callingFor != null) {
                if (callingFor.equals(Constant.CALL_EDITEMPLOYEE)) {
                    employeeDetail = (EmployeeDatum) intent.getSerializableExtra("employeeData");

                    if (employeeDetail != null) {
                        if (employeeDetail.getEmployeeName() != null)
                            edEName.setText(employeeDetail.getEmployeeName());
                        if (employeeDetail.getEmployeeCode() != null)
                            edECode.setText(employeeDetail.getEmployeeCode());
                        if (employeeDetail.getEmployeeAddr() != null)
                            edEAddress.setText(employeeDetail.getEmployeeAddr());
                        if (employeeDetail.getEmployeeDOJ() != null) {

                            edEDOJ.setText("" + employeeDetail.getEmployeeDOJ());
                            dateOfJoining = employeeDetail.getEmployeeDOJ();
                        }

                        if (employeeDetail.getEmployeeDOB() != null) {

                            edEDOB.setText("" + employeeDetail.getEmployeeDOB());
                            dateOfBirth = employeeDetail.getEmployeeDOB();
                        }
                        if (employeeDetail.getEmployeePhone() != null)
                            edEPhone.setText(employeeDetail.getEmployeePhone());
                        edEName.setFocusable(false);
                        edEName.setFocusableInTouchMode(false);
                        edEName.setBackgroundColor(getResources().getColor(R.color.colorGrey));

                        if (employeeDetail.getGender() != null) {
                            if (employeeDetail.getGender().equals("Male")) {
                                spinnerEGender.setSelection(0);
                            }

                            if (employeeDetail.getGender().equals("Female")) {
                                spinnerEGender.setSelection(1);
                            }
                        }
                        if (employeeDetail.getStatus1() !=null){
                            if (employeeDetail.getStatus1().equals("Active"))
                            {
                                spinnerStatus.setSelection(0);
                            }
                            if (employeeDetail.getStatus1().equals("InActive"))
                            {
                                spinnerStatus.setSelection(1);
                            }
                        }


                        spinnerEGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                spinnerEGender.setSelection(position);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });

                    }
                } else if (callingFor != null && callingFor.equals("Restra")) {

                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


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
        edEName.addTextChangedListener(this);

        spinnerStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }

    private void addEmployee() {

        String name = edEName.getText().toString().trim();
        String address = edEAddress.getText().toString().trim();
        String code = edECode.getText().toString().trim();
        String number = edEPhone.getText().toString().trim();

        if (!name.equals("") && name != null) {


            EmployeeDatum employeeData = new EmployeeDatum();
            employeeData.setEmployeeName(name);
            employeeData.setEmployeeAddr(address);
            employeeData.setEmployeeCode(code);
            employeeData.setEmployeeDOB(dateOfBirth);
            employeeData.setEmployeeDOJ(dateOfJoining);
            employeeData.setEmployeePhone(number);
            employeeData.setGender((String) spinnerEGender.getSelectedItem());
            employeeData.setStatus1((String) spinnerStatus.getSelectedItem());


            if (callingFor.equals(Constant.CALL_EDITEMPLOYEE)) {
                employeeData.setEmployeeId(employeeDetail.getEmployeeId());
            }

            String url = serverUrl + "/hipos//1/" + Constant.FUNTION_ADDEMPLOYEE;

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
                                        EmployeeDatum employee = gson.fromJson(jsonObject.toString(), EmployeeDatum.class);

                                        if (employee != null) {
                                            UtilView.showToast(activity, "Employee Added Successfully.");
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
                    task.execute(new Gson().toJson(employeeData), url, "");

                }
                if (!isInternatePrsenet) {
                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
                }
            } else {
                UtilView.gotToLogin(activity);
            }


        } else {
            if (name == null || name.equals("")) {
                edEName.setError(getResources().getString(R.string.err_msg));
            }
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        edEName.setError(null);

    }

    @Override
    public void afterTextChanged(Editable s) {

    }


    @OnClick({R.id.edEDOB, R.id.edEDOJ, R.id.btnSave, R.id.btnClose})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edEDOB:
                getDOBPicker(activity);
                break;
            case R.id.edEDOJ:
                getDOJPicker(activity);
                break;
            case R.id.btnSave:
                addEmployee();
                break;
            case R.id.btnClose:
                finish();
                break;
        }
    }


    public void getDOBPicker(Activity mActivity) {
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
                edEDOB.setText(String.valueOf(new StringBuilder().append(year).append("-").append(month + 1).append("-").append(dayOfMonth)));
                calendar.set(year, month, dayOfMonth);
                dateOfBirth = f.format(calendar.getTime());

            }
        }, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();


    }


    public void getDOJPicker(Activity mActivity) {
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
                edEDOJ.setText(String.valueOf(new StringBuilder().append(year).append("-").append(month + 1).append("-").append(dayOfMonth)));
                calendar.set(year, month, dayOfMonth);
                dateOfJoining = f.format(calendar.getTime());
            }
        }, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();


    }
}
