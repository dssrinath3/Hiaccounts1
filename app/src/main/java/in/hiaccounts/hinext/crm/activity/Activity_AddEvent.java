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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.employee.EmployeeList;
import in.hiaccounts.hinext.crm.model.event.AddEventData;
import in.hiaccounts.hinext.crm.model.event.SelectEventData;
import in.hiaccounts.hinext.sales.model.sales_pagedata.CmpylocationList;
import in.hiaccounts.hinext.sales.model.sales_pagedata.HinextSalesPageData;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;

import static in.hiaccounts.R.string.response_error;

public class Activity_AddEvent extends AppCompatActivity {
    public static String TAG = Activity_AddEvent.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edName)
    EditText edName;
    @BindView(R.id.edEventDate)
    EditText edEventDate;
    @BindView(R.id.edAssignedTo)
    EditText edAssignedTo;
    @BindView(R.id.spinActivitytype)
    Spinner spinActivitytype;
    @BindView(R.id.id_Activitytype)
    LinearLayout idActivitytype;
    @BindView(R.id.spinFromLocation)
    Spinner spinFromLocation;
    @BindView(R.id.id_FromLocation)
    LinearLayout idFromLocation;
    @BindView(R.id.chkNotification)
    CheckBox chkNotification;
    @BindView(R.id.id_Notification)
    LinearLayout idNotification;
    @BindView(R.id.spinPriority)
    Spinner spinPriority;
    @BindView(R.id.id_Priority)
    LinearLayout idPriority;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.btnClose)
    Button btnClose;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.llBottomLayout)
    LinearLayout llBottomLayout;


    private Activity activity;
    private ServiceHandler serviceHandler;
    private Boolean isInternetPresent = false;
    String callingFor;
    Long empId,companyLocationId = null;
    String eventDate = "";
    private boolean isAllValid = true;
    private HinextSalesPageData pageData;
    private SharedPreference sharedPreference;
    String serverUrl,activityType,fromLocation,priority;
    EmployeeList empList;
    private List<Object> objectActivityTypeList = new ArrayList<Object>();
    private List<Object> objectPriorityList = new ArrayList<Object>();

    private final CmpylocationList[] selectedCompanyLocation = {null};
    SelectEventData selectEventDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addevent);
        ButterKnife.bind(this);
        initComponentView();
    }

    private void initComponentView() {
        activity = this;
        serviceHandler = new ServiceHandler(activity);
        serverUrl = UtilView.getUrl(activity);
        sharedPreference = SharedPreference.getInstance(activity);

        objectPriorityList.clear();
        objectPriorityList.add("select");
        objectPriorityList.add("high");
        objectPriorityList.add("low");
        objectPriorityList.add("medium");
        UtilView.setupItemAdapter(activity, spinPriority, objectPriorityList);

        objectActivityTypeList.clear();
        objectActivityTypeList.add("select");
        objectActivityTypeList.add("call");
        objectActivityTypeList.add("meeting");
        UtilView.setupItemAdapter(activity, spinActivitytype, objectActivityTypeList);



        Intent intent = getIntent();
        if (intent != null) {
            callingFor = intent.getStringExtra("callingFor");
            if (callingFor != null) {
                toolbar.setTitle(callingFor);
                if (callingFor.equals(Constant.CALL_EDITEVENT)) {
                    selectEventDetails = (SelectEventData) intent.getSerializableExtra("eventData");
                    if (selectEventDetails != null) {

                        if (selectEventDetails.getEventName() != null)
                            edName.setText(selectEventDetails.getEventName());



                        if (selectEventDetails.getEmployeeName() != null)
                            edAssignedTo.setText(selectEventDetails.getEmployeeName());

                        if (selectEventDetails.getEventDate() != null) {

                            Calendar calendar = Calendar.getInstance();
                            calendar.setTimeInMillis(Long.parseLong(selectEventDetails.getEventDate()));
                            int year = calendar.get(Calendar.YEAR);
                            int month = calendar.get(Calendar.MONTH);
                            final int day = calendar.get(Calendar.DAY_OF_MONTH);
                            String FromDate = String.valueOf(new StringBuilder().append(year).append("-").append(month + 1).append("-").append(day));
                            edEventDate.setText(FromDate);

                            final TimeZone utc = TimeZone.getTimeZone("UTC");
                            final SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                            f.setTimeZone(utc);
                            eventDate = f.format(calendar.getTime());
                        }

                        empId = selectEventDetails.getEmployeeId();

                        edAssignedTo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                getEmployeeData();
                            }
                        });


                        if (selectEventDetails.getActivityType() != null) {
                            if (selectEventDetails.getActivityType().equals("call")) {
                                spinActivitytype.setSelection(1);
                            }
                            if (selectEventDetails.getActivityType().equals("meeting")) {
                                spinActivitytype.setSelection(2);
                            }
                        }




                        if (selectEventDetails.getPriority() != null) {

                            if (selectEventDetails.getPriority().equals("high")) {
                                spinPriority.setSelection(1);
                            }
                            else if (selectEventDetails.getPriority().equals("low")) {
                                spinPriority.setSelection(2);
                            }
                            else if (selectEventDetails.getPriority().equals("medium")) {
                                spinPriority.setSelection(3);
                            }
                        }




                        if (selectEventDetails.getNotification().equals("Yes")){
                                chkNotification.setChecked(true);
                                getNotification(activity);

                            }else{
                                chkNotification.setChecked(false);
                                getNotification(activity);
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

        getPageLoadData();


        edAssignedTo.addTextChangedListener(new TextWatcher() {
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

        edAssignedTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getEmployeeData();
            }
        });
        spinActivitytype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = objectActivityTypeList.get(i);
                if (obj instanceof String) {
                    spinActivitytype.setSelection(i);
                    activityType = (String) spinActivitytype.getSelectedItem();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });



        spinPriority.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = objectPriorityList.get(i);
                if (obj instanceof String) {
                    spinPriority.setSelection(i);
                    priority = (String) spinPriority.getSelectedItem();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


    }

    private void getPageLoadData() {
        String companyData = sharedPreference.getData(Constant.HINEXTSALESPAGEDATA_KEY);
        Gson gson = new Gson();
        try {
            if(companyData !=null){
                pageData = gson.fromJson(companyData, HinextSalesPageData.class);

                ArrayAdapter<CmpylocationList> spinnerCompanyLocationAdapter = UtilView.setupCompanyLocationAdapter(activity, spinFromLocation, pageData.getCmpylocationList());
                selectedCompanyLocation[0] = spinnerCompanyLocationAdapter.getItem(0);
                spinFromLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        selectedCompanyLocation[0] = (CmpylocationList) adapterView.getSelectedItem();
                        if (selectedCompanyLocation[0] != null) {
                            companyLocationId = selectedCompanyLocation[0].getInventoryLocationId();

                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }


        } catch (Exception e) {
            UtilView.showErrorDialog(getResources().getString(response_error), activity);
        }

    }





    @OnClick({R.id.edEventDate, R.id.chkNotification, R.id.btnClose, R.id.btnSave})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edEventDate:
                getEventDatePicker(activity);
                break;
            case R.id.chkNotification:
                getNotification(activity);
                break;
            case R.id.btnClose:
                break;
            case R.id.btnSave:
                addEventData();
                break;
        }
    }

    private boolean getNotification(Activity activity) {
        return chkNotification.isChecked() == true;
    }

    private void addEventData() {
        String name = edName.getText().toString().trim();


        if (name != null && !name.equals("") && isAllValid) {

            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {

                UtilView.showProgessBar(activity, progressBar);
                AddEventData addEventData = new AddEventData();
                addEventData.setEventName(name);
                addEventData.setEventDate(eventDate);
                addEventData.setActivityType(activityType);
                addEventData.setLocation(companyLocationId);
                addEventData.setNotification(getNotification(activity));
                addEventData.setPriority(priority);
                addEventData.setEmployeeId(empId);



                if (callingFor.equals(Constant.CALL_EDITEVENT)) {

                    addEventData.setEventId(selectEventDetails.getEventId());
                }

                String url = serverUrl + "/crm//" + Constant.FUNTION_ADDEVENT;

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
                                            AddEventData addEventsData = gson.fromJson(jsonObject.toString(), AddEventData.class);

                                            if (addEventsData != null) {
                                                Intent returnIntent = new Intent();
                                                activity.setResult(Activity.RESULT_OK, returnIntent);
                                                activity.finish();
                                                UtilView.showToast(activity, "Event added successfully.");

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
                        task.execute(new Gson().toJson(addEventData), url, "");

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
            if (name.equals("") || name == null) {
                edName.setError(getString(R.string.err_msg));
            }



        }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constant.RESQUSTCODE_EMPLOYEESEARCH && resultCode == RESULT_OK) {
            empList = (EmployeeList) data.getSerializableExtra("employee");
            if (empList != null)

                empId = empList.getEmployeeId();
            if (empList.getEmployeeName() != null)
                edAssignedTo.setText(empList.getEmployeeName());
        }



    }

    private void getEventDatePicker(Activity activity) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final TimeZone utc = TimeZone.getTimeZone("UTC");
        final SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        f.setTimeZone(utc);
        DatePickerDialog datePickerDialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                edEventDate.setText(String.valueOf(new StringBuilder().append(year).append("-").append(month + 1).append("-").append(dayOfMonth)));
                calendar.set(year, month, dayOfMonth);
                eventDate = f.format(calendar.getTime());
            }
        }, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();

    }
}
