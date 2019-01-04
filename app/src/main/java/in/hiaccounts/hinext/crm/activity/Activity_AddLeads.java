package in.hiaccounts.hinext.crm.activity;

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
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.employee.EmployeeList;
import in.hiaccounts.hinext.crm.model.leads.AddLeadsData;
import in.hiaccounts.hinext.crm.model.leads.SelectLeadsData;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

public class Activity_AddLeads extends AppCompatActivity {
    public static String TAG = Activity_AddLeads.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edName)
    EditText edName;
    @BindView(R.id.edLeadsDate)
    EditText edLeadsDate;
    @BindView(R.id.edDescription)
    EditText edDescription;
    @BindView(R.id.edSubject)
    EditText edSubject;
    @BindView(R.id.edEmployee)
    EditText edEmployee;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.btnClose)
    Button btnClose;
    @BindView(R.id.btnSave)
    Button btnSave;


    private Activity activity;
    private ServiceHandler serviceHandler;
    private Boolean isInternetPresent = false;
    String callingFor;
    Long empID;
    String leadsDate = "";
    private boolean isAllValid = true;
    String serverUrl;
    EmployeeList empList;
    Long empId;
    SelectLeadsData selectLeadsDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addleads);
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
                if (callingFor.equals(Constant.CALL_EDITLEADS)) {
                    selectLeadsDetails = (SelectLeadsData) intent.getSerializableExtra("leadsData");
                    if (selectLeadsDetails != null) {

                        if (selectLeadsDetails.getFullName() != null)
                            edName.setText(selectLeadsDetails.getFullName());

                        if (selectLeadsDetails.getSubject() != null)
                            edSubject.setText(selectLeadsDetails.getSubject());

                        if (selectLeadsDetails.getDescription() != null)
                            edDescription.setText(selectLeadsDetails.getDescription());

                        if (selectLeadsDetails.getEmployeeName() != null)
                            edEmployee.setText(selectLeadsDetails.getEmployeeName());

                        if (selectLeadsDetails.getDob() != null) {

                            Calendar calendar = Calendar.getInstance();
                            calendar.setTimeInMillis(Long.parseLong(selectLeadsDetails.getDob()));
                            int year = calendar.get(Calendar.YEAR);
                            int month = calendar.get(Calendar.MONTH);
                            final int day = calendar.get(Calendar.DAY_OF_MONTH);
                            String FromDate = String.valueOf(new StringBuilder().append(year).append("-").append(month + 1).append("-").append(day));
                            edLeadsDate.setText(FromDate);

                            final TimeZone utc = TimeZone.getTimeZone("UTC");
                            final SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                            f.setTimeZone(utc);
                            leadsDate = f.format(calendar.getTime());
                        }

                        empId = selectLeadsDetails.getEmployeeId();

                        edEmployee.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                getEmployeeData();
                            }
                        });

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
        edEmployee.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getEmployeeData();
            }
        });
    }

    private void getEmployeeData() {
        if (serverUrl != null) {

            Intent intent = new Intent(activity, Activity_ShowEmployeeList.class);
            intent.putExtra("callingfrom", Constant.CALL_ADDCRMOPENING_EMPLOYEE);
            startActivityForResult(intent, Constant.RESQUSTCODE_EMPLOYEESEARCH);
        } else {
            UtilView.gotToLogin(activity);
        }
    }

    @OnClick({R.id.edLeadsDate, R.id.btnClose, R.id.btnSave})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edLeadsDate:
                getLeadsDatePicker(activity);
                break;
            case R.id.btnClose:
                finish();
                break;
            case R.id.btnSave:
                addCrmLeads();
                break;
        }
    }

    private void addCrmLeads() {
        String edNames = edName.getText().toString().trim();
        String edDesc = edDescription.getText().toString().trim();
        String edSubjet = edSubject.getText().toString().trim();
        String edEmpname = edEmployee.getText().toString().trim();


        if (edNames != null && !edNames.equals("") && edEmpname != null && !edEmpname.equals("") && isAllValid) {

            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {

                UtilView.showProgessBar(activity, progressBar);
                AddLeadsData addLeadsData = new AddLeadsData();
                addLeadsData.setOtherContactId(null);
                addLeadsData.setFullName(edNames);
                addLeadsData.setDob(leadsDate);
                addLeadsData.setDescription(edDesc);
                addLeadsData.setSubject(edSubjet);
                addLeadsData.setStatus(null);
                addLeadsData.setEmployeeId(empId);



                if (callingFor.equals(Constant.CALL_EDITLEADS)) {

                    addLeadsData.setOtherContactId(selectLeadsDetails.getOtherContactId());
                }

                String url = serverUrl + "/crm//" + Constant.FUNTION_ADDLEADS;

                if (serverUrl != null) {
                    isInternetPresent = serviceHandler.isConnectingToInternet();
                    if (isInternetPresent) {
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
                                                AddLeadsData addleadData = gson.fromJson(jsonObject.toString(), AddLeadsData.class);

                                                if (addleadData != null) {
                                                    Intent returnIntent = new Intent();
                                                    activity.setResult(Activity.RESULT_OK, returnIntent);
                                                    activity.finish();
                                                    UtilView.showToast(activity, "Leads added successfully.");

                                                } else {
                                                    UtilView.showToast(activity, "Something Error.");
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
                        task.execute(new Gson().toJson(addLeadsData), url, "");

                    }
                    if (!isInternetPresent) {
                        UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
                    }
                } else {
                    UtilView.getUrl(activity);
                }
            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
            }
        } else {
            if (edNames.equals("") || edNames == null) {
                edName.setError(getString(R.string.err_msg));
            }
            if (edEmpname.equals("") || edEmpname == null) {
                edEmployee.setError(getResources().getString(R.string.err_msg));
            }


        }

    }

    public void getLeadsDatePicker(Activity mActivity) {
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
                edLeadsDate.setText(String.valueOf(new StringBuilder().append(year).append("-").append(month + 1).append("-").append(dayOfMonth)));
                calendar.set(year, month, dayOfMonth);
                leadsDate = f.format(calendar.getTime());
            }
        }, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constant.RESQUSTCODE_EMPLOYEESEARCH && resultCode == RESULT_OK) {
            empList = (EmployeeList) data.getSerializableExtra("employee");
            if (empList != null)

                empId = empList.getEmployeeId();
            if (empList.getEmployeeName() != null)
                edEmployee.setText(empList.getEmployeeName());
        }



    }


}
