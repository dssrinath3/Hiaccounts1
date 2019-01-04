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
import com.rey.material.widget.ProgressView;

import org.json.JSONArray;
import org.json.JSONException;
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
import in.hiaccounts.hinext.inventory.model.uomconverter.UOMConvSelectData;
import in.hiaccounts.hinext.inventory.model.uomconverter.UOMConverter;
import in.hiaccounts.hinext.inventory.model.uomconverter.UOMConverterData;
import in.hiaccounts.hinext.inventory.model.uomconverter.UnitOfMeasurement;
import in.hiaccounts.hinext.inventory.model.uomconverter.UomConverterDatumData;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

public class Activity_UOMConverter extends AppCompatActivity {
    String serverUrl;
    public static String TAG = Activity_UOMConverter.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.spinUOMConverter)
    Spinner spinUOMConverter;
    @BindView(R.id.edUOMConvertedName)
    EditText edUOMConvertedName;
    @BindView(R.id.edUOMConvertValue)
    EditText edUOMConvertValue;
    @BindView(R.id.edeffectiveDate)
    EditText edeffectiveDate;
    @BindView(R.id.spinUOMStatus)
    Spinner spinUOMStatus;
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
    String effectiveDate = "";
    private boolean isAllValid = true;
    private String uomStatus;
    private List<UOMConverter> uomcoverterList;
    private List<Object> objectUOMList = new ArrayList<Object>();
    private List<Object> objectUOMStatusList = new ArrayList<Object>();
    private UOMConverter uomList = null;
    UomConverterDatumData uomConvSelectDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uomconverter);
        ButterKnife.bind(this);
        initContentView();

    }

    private void initContentView() {
        activity = this;
        serviceHandler = new ServiceHandler(activity);
        serverUrl = UtilView.getUrl(activity);
        Intent intent = getIntent();

        if (intent != null) {
            callingFor = intent.getStringExtra("callingFor");

            if (callingFor != null) {
                toolbar.setTitle(callingFor);
                if (callingFor.equals(Constant.CALL_EDITUOMCONVERTER)) {
                    uomConvSelectDetails = (UomConverterDatumData) intent.getSerializableExtra("uomData");
                    if (uomConvSelectDetails != null) {

                        if (uomConvSelectDetails.getUomConvertedName() != null)
                            edUOMConvertedName.setText(uomConvSelectDetails.getUomConvertedName());


                        if (uomConvSelectDetails.getUomValue() != null) {
                            edUOMConvertValue.setText(""+uomConvSelectDetails.getUomValue());
                        }

                        if (uomConvSelectDetails.getUomConvertorEffectiveDate() != null) {
                            edeffectiveDate.setText(""+uomConvSelectDetails.getUomConvertorEffectiveDate());
                            effectiveDate= String.valueOf(uomConvSelectDetails.getUomConvertorEffectiveDate());
                        }


                        if (uomConvSelectDetails.getUnitOfMeasurement() != null) {

                            List<UnitOfMeasurement> data = new ArrayList<>();
                            data.add(uomConvSelectDetails.getUnitOfMeasurement());
                            objectUOMList.clear();
                            objectUOMList.addAll(data);
                            UtilView.setupItemAdapter(activity, spinUOMConverter, objectUOMList);
                            for (int i = 0; i < data.size(); i++) {
                                if (uomConvSelectDetails.getUnitOfMeasurement() != null) {
                                    spinUOMConverter.setSelection(i);
                                }

                            }

                        }

                        objectUOMStatusList.clear();
                        objectUOMStatusList.add("Active");
                        objectUOMStatusList.add("InActive");
                        UtilView.setupItemAdapter(activity, spinUOMStatus, objectUOMStatusList);
                        if (uomConvSelectDetails.getStatus() != null) {
                            if (uomConvSelectDetails.getStatus().equals("Active")) {
                                spinUOMStatus.setSelection(0);
                            }
                            if (uomConvSelectDetails.getStatus().equals("InActive")) {
                                spinUOMStatus.setSelection(1);
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


        spinUOMConverter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = objectUOMList.get(i);
                if (obj instanceof UOMConverter) {
                    spinUOMConverter.setSelection(i);
                    uomList = (UOMConverter) spinUOMConverter.getSelectedItem();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinUOMStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = objectUOMStatusList.get(i);
                if (obj instanceof String) {
                    spinUOMStatus.setSelection(i);
                    uomStatus = (String) spinUOMStatus.getSelectedItem();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        getUOMConverters();


    }

    public void getUOMConverters() {
        String url = "";

        if (callingFor != null) {
            url = serverUrl + "/hipos//1/" + Constant.FUNTION_GETUOMCOVERTERlIST;
        }


        if (serverUrl != null) {
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {
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
                                    uomcoverterList = new ArrayList<UOMConverter>();
                                    JSONArray resultJsonArray = new JSONArray(result.toString());
                                    for (int i = 0; i < resultJsonArray.length(); i++) {
                                        JSONObject uomJson = resultJsonArray.getJSONObject(i);
                                        Gson gson = new Gson();
                                        UOMConverter sdata = gson.fromJson(uomJson.toString(), UOMConverter.class);
                                        uomcoverterList.add(sdata);

                                    }
                                    if (uomcoverterList != null && uomcoverterList.size() > 0) {
                                        objectUOMList.clear();
                                        objectUOMList.add("Select");
                                        objectUOMList.addAll(uomcoverterList);
                                        UtilView.setupItemAdapter(activity, spinUOMConverter, objectUOMList);


                                    }

                                    objectUOMStatusList.clear();
                                    objectUOMStatusList.add("Active");
                                    objectUOMStatusList.add("InActive");
                                    UtilView.setupItemAdapter(activity, spinUOMStatus, objectUOMStatusList);


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


    @OnClick({R.id.edeffectiveDate, R.id.btnSave, R.id.btnClose})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edeffectiveDate:
                getDatePicker(activity);
                break;
            case R.id.btnSave:
                addUOMConverter();
                break;
            case R.id.btnClose:
                finish();
                break;
        }
    }

    private void addUOMConverter() {
        String UOMconvertedName = edUOMConvertedName.getText().toString().trim();
        String UOMconvertedvalue = edUOMConvertValue.getText().toString().trim();

        if ((!UOMconvertedName.equals("") && UOMconvertedName != null) && (!UOMconvertedvalue.equals("") && UOMconvertedvalue != null) && (effectiveDate!=null && !effectiveDate.equals("") && (uomList != null && !uomList.equals("")) && isAllValid) ){
            Long unitOfMeasurement = uomList.getUnitOfMeasurementId();

            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {

                UtilView.showProgessBar(activity, progressBar);
                UOMConverterData uomData = new UOMConverterData();
                uomData.setUomValue(UOMconvertedvalue);
                uomData.setUomConvertorEffectiveDate(effectiveDate);
                uomData.setStatus(uomStatus);
                uomData.setUomConvertedName(UOMconvertedName);
                uomData.setUnitOfMeasurement(String.valueOf(unitOfMeasurement));






                if (callingFor.equals(Constant.CALL_EDITUOMCONVERTER)) {

                    uomData.setUomConvertorId(uomConvSelectDetails.getUomConvertorId());
                }

                String url = serverUrl + "/hipos//1/" + Constant.FUNTION_ADDUOMCONVERTER;

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
                                            UOMConverterData uomcdata = gson.fromJson(jsonObject.toString(), UOMConverterData.class);

                                            if (uomcdata != null) {
                                                Intent returnIntent = new Intent();
                                                activity.setResult(Activity.RESULT_OK, returnIntent);
                                                activity.finish();
                                                UtilView.showToast(activity, "UOM Converter added successfully.");
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
                        task.execute(new Gson().toJson(uomData), url, "");

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
            if (UOMconvertedName.equals("")) {
                edUOMConvertedName.setError(getString(R.string.err_msg));
            }
            if (UOMconvertedvalue.equals("")) {
                edUOMConvertValue.setError(getString(R.string.err_msg));
            }
            if (uomList == null) {
                TextView tv = (TextView) spinUOMConverter.getSelectedView();
                tv.setError(getString(R.string.err_msg));
            }
            if(effectiveDate == null){
                edeffectiveDate.setError(getString(R.string.err_msg));
            }
        }
    }

    public void getDatePicker(Activity mActivity) {
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
                edeffectiveDate.setText(String.valueOf(new StringBuilder().append(year).append("-").append(month + 1).append("-").append(dayOfMonth)));
                calendar.set(year, month, dayOfMonth);
                effectiveDate = f.format(calendar.getTime());
            }
        }, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();


    }
}
