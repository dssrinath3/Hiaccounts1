package in.hiaccounts.hinext.inventory.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.rey.material.widget.ProgressView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.inventory.model.isdn.ISDNDatum;
import in.hiaccounts.hinext.inventory.model.isdn.ISDNdata;
import in.hiaccounts.hinext.inventory.model.isdn.StateListData;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

public class Activity_ISDN extends AppCompatActivity {

    public static String TAG = Activity_ISDN.class.getSimpleName();
    String serverUrl;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.spinISDNState)
    Spinner spinISDNState;
    @BindView(R.id.id_isdnState)
    LinearLayout idIsdnState;
    @BindView(R.id.edIsdnNo)
    EditText edIsdnNo;
    @BindView(R.id.llISDNNo)
    LinearLayout llISDNNo;
    @BindView(R.id.edFromDate)
    EditText edFromDate;
    @BindView(R.id.llFromDate)
    LinearLayout llFromDate;
    @BindView(R.id.edToDate)
    EditText edToDate;
    @BindView(R.id.llToDate)
    LinearLayout llToDate;
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
    String callingFor;
    String fromDate = "", toDate = "";
    ISDNDatum isdnDatumDetails;
    @BindView(R.id.spinnerStatus)
    Spinner spinnerStatus;
    private Activity activity;
    private ServiceHandler serviceHandler;
    private Boolean isInternetPresent = false;
    private boolean isAllValid = true;
    private List<StateListData> objStateList;
    private List<Object> objectStateList = new ArrayList<Object>();
    private StateListData stateList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isdn);
        ButterKnife.bind(this);
        initComponentView();

    }

    private void initComponentView() {
        activity = this;
        serviceHandler = new ServiceHandler(activity);
        serverUrl = UtilView.getUrl(activity);
        Intent intent = getIntent();
        UtilView.setupAdapter(activity, spinnerStatus, getResources().getStringArray(R.array.config_status));

        if (intent != null) {
            callingFor = intent.getStringExtra("callingFor");

            if (callingFor != null) {
                toolbar.setTitle(callingFor);
                if (callingFor.equals(Constant.CALL_EDITISDN)) {
                    isdnDatumDetails = (ISDNDatum) intent.getSerializableExtra("isdnData");
                    if (isdnDatumDetails != null) {
                        if (isdnDatumDetails.getGstApprovalnumber() != null)
                            edIsdnNo.setText(isdnDatumDetails.getGstApprovalnumber());


                        if (isdnDatumDetails.getFromGstApprovalDate() != null) {

                            edFromDate.setText("" + isdnDatumDetails.getFromGstApprovalDate());

                            fromDate = isdnDatumDetails.getFromGstApprovalDate();


                        }
                        if (isdnDatumDetails.getStatus() != null) {
                            if (isdnDatumDetails.getStatus().equals("Active")) {
                                spinnerStatus.setSelection(0);
                            }

                            if (isdnDatumDetails.getStatus().equals("InActive")) {
                                spinnerStatus.setSelection(1);
                            }
                        }

                        if (isdnDatumDetails.getToGstApprovalDate() != null) {
                            edToDate.setText("" + isdnDatumDetails.getToGstApprovalDate());
                            toDate = isdnDatumDetails.getToGstApprovalDate();

                        }


                        if (isdnDatumDetails.getStatelist() != null) {

                            List<String> data = new ArrayList<>();
                            data.add(isdnDatumDetails.getStatelist());
                           /* objectStateList.clear();
                            objectStateList.addAll(isdnDatumDetails.getStatelist());*/
                            getAdapter();
                            for (int i = 0; i < data.size(); i++) {
                                if (isdnDatumDetails.getStatelist() != null) {
                                    spinISDNState.setSelection(i);
                                }

                            }

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


        spinISDNState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = objectStateList.get(i);
                if (obj instanceof StateListData) {
                    spinISDNState.setSelection(i);
                    stateList = (StateListData) spinISDNState.getSelectedItem();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        getStateList();


    }

    public void getStateList() {
        //UtilView.setupItemAdapter(activity, spinISDNState, objectStateList);
        JsonObject json = null;
        try {
            json = new JsonObject();
        } catch (Exception e) {

        }
        String url = "";

        if (callingFor != null) {
            url = serverUrl + "/hipos//1/" + Constant.FUNTION_GETSTATElIST;
        }


        if (serverUrl != null) {
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {
                // prepare the Request
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
                                try {
                                    objStateList = new ArrayList<StateListData>();
                                    JSONArray resultJsonArray = new JSONArray(result.toString());
                                    for (int i = 0; i < resultJsonArray.length(); i++) {
                                        JSONObject stateJson = resultJsonArray.getJSONObject(i);
                                        Gson gson = new Gson();
                                        StateListData sdata = gson.fromJson(stateJson.toString(), StateListData.class);
                                        objStateList.add(sdata);

                                    }
                                    if (objStateList != null && objStateList.size() > 0) {
                                        objectStateList.clear();
                                        objectStateList.add("Select");
                                        objectStateList.addAll(objStateList);
                                        getAdapter();


                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.error_null), activity);
                        }
                    }
                }, false);
                task.execute(url, "");
            }
            if (!isInternetPresent) {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
            }
        } else {
            UtilView.gotToLogin(activity);
        }
    }

    public void getAdapter() {
        UtilView.setupItemAdapter(activity, spinISDNState, objectStateList);
    }


    @OnClick({R.id.edFromDate, R.id.edToDate, R.id.btnSave, R.id.btnClose})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edFromDate:
                getFromDatePicker(activity);
                break;
            case R.id.edToDate:
                getToDatePicker(activity);
                break;
            case R.id.btnSave:
                addISDN();
                break;
            case R.id.btnClose:
                finish();
                break;
        }
    }

    private void addISDN() {
        String ISDNno = edIsdnNo.getText().toString().trim();


        if ((!ISDNno.equals("") && ISDNno != null) && fromDate != null && !fromDate.equals("") && (toDate != null && !toDate.equals("") && (stateList != null && !stateList.equals("")) && isAllValid)) {
            Long stateID = stateList.getId();

            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {

                UtilView.showProgessBar(activity, progressBar);
                ISDNdata isdNdata = new ISDNdata();
                isdNdata.setState(String.valueOf(stateID));
                isdNdata.setGstApprovalnumber(ISDNno);
                isdNdata.setFromGstApprovalDate(fromDate);
                isdNdata.setToGstApprovalDate(toDate);
                isdNdata.setStatus((String) spinnerStatus.getSelectedItem());

                if (callingFor.equals(Constant.CALL_EDITISDN)) {

                    isdNdata.setGstApprovalDateId(isdnDatumDetails.getGstApprovalDateId());
                }

                String url = serverUrl + "/hipos//1/" + Constant.FUNTION_ADDISDN;

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
                                        if (result.toString().equals("No Response Body")){
                                            UtilView.showToast(activity, "ISDN Updated successfully.");
                                            Intent returnIntent = new Intent();
                                            activity.setResult(Activity.RESULT_OK, returnIntent);
                                            activity.finish();
                                        }else {
                                            try {
                                                JSONObject jsonObject = new JSONObject(result.toString());
                                                ISDNdata isdata = gson.fromJson(jsonObject.toString(), ISDNdata.class);

                                                if (isdata != null) {
                                                    UtilView.showToast(activity, "ISDN added successfully.");
                                                    Intent returnIntent = new Intent();
                                                    returnIntent.putExtra("isdnData", isdata);
                                                    activity.setResult(Activity.RESULT_OK, returnIntent);
                                                    activity.finish();
                                                }


                                            } catch (Exception e) {
                                                UtilView.showLogCat(TAG, "TaskCompleteListener Exception " + e.toString());
                                            }
                                        }


                                    }
                                } else {
                                    progressBar.setVisibility(View.GONE);
                                    UtilView.showErrorDialog(getResources().getString(R.string.error_null), activity);
                                }
                            }
                        }, false);
                        task.execute(new Gson().toJson(isdNdata), url, "");

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
            if (ISDNno.equals("")) {
                edIsdnNo.setError(getString(R.string.err_msg));
            }
            if (stateList == null) {
                TextView tv = (TextView) spinISDNState.getSelectedView();
                tv.setError(getString(R.string.err_msg));
            }
            if (fromDate == null) {
                edFromDate.setError(getString(R.string.err_msg));
            }
            if (toDate == null) {
                edToDate.setError(getString(R.string.err_msg));
            }
        }
    }


    public void getFromDatePicker(Activity mActivity) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
      /*  final TimeZone utc = TimeZone.getTimeZone("UTC");
        final SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        f.setTimeZone(utc);
*/
        DatePickerDialog datePickerDialog = new DatePickerDialog(mActivity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                edFromDate.setText(String.valueOf(new StringBuilder().append(year).append("-").append(month + 1).append("-").append(dayOfMonth)));
                calendar.set(year, month, dayOfMonth);
                fromDate = edFromDate.getText().toString().trim();
            }
        }, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();


    }

    public void getToDatePicker(Activity mActivity) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);



      /*  final TimeZone utc = TimeZone.getTimeZone("UTC");
        final SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        f.setTimeZone(utc);
*/
        DatePickerDialog datePickerDialog = new DatePickerDialog(mActivity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                edToDate.setText(String.valueOf(new StringBuilder().append(year).append("-").append(month + 1).append("-").append(dayOfMonth)));
                calendar.set(year, month, dayOfMonth);
                toDate = edToDate.getText().toString().trim();
            }
        }, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();


    }

}
