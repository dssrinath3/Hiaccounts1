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
import in.hiaccounts.hinext.inventory.model.paymentvoucher.AddPaymentVoucherData;
import in.hiaccounts.hinext.inventory.model.paymentvoucher.PaymentMethodType;
import in.hiaccounts.hinext.inventory.model.paymentvoucher.PaymentVoucherSelectData;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

public class Activity_AddPaymentVoucher extends AppCompatActivity {

    public static String TAG = Activity_AddPaymentVoucher.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edAmount)
    EditText edAmount;
    @BindView(R.id.llAmount)
    LinearLayout llAmount;
    @BindView(R.id.edReason)
    EditText edReason;
    @BindView(R.id.llReason)
    LinearLayout llReason;
    @BindView(R.id.edFromDate)
    EditText edFromDate;
    @BindView(R.id.llFromDate)
    LinearLayout llFromDate;
    @BindView(R.id.edToDate)
    EditText edToDate;
    @BindView(R.id.llToDate)
    LinearLayout llToDate;
    @BindView(R.id.spinVouvherStatus)
    Spinner spinVouvherStatus;
    @BindView(R.id.id_voucherStatus)
    LinearLayout idVoucherStatus;
    @BindView(R.id.edTotalAmount)
    EditText edTotalAmount;
    @BindView(R.id.llTotalAmount)
    LinearLayout llTotalAmount;
    @BindView(R.id.edTaxAmount)
    EditText edTaxAmount;
    @BindView(R.id.llTaxAmount)
    LinearLayout llTaxAmount;
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

    String serverUrl;
    @BindView(R.id.spinPaymentMethodName)
    Spinner spinPaymentMethodName;
    private Activity activity;
    private ServiceHandler serviceHandler;
    private Boolean isInternetPresent = false;
    String callingFor;
    String fromDate = "", toDate = "";
    private boolean isAllValid = true;
    List<PaymentMethodType> paymentMethodTypeList;
    private List<Object> objectPaymentMethodTypeList = new ArrayList<Object>();
    private List<Object> objectVoucherStatusList = new ArrayList<Object>();
    String voucherStatus;
    PaymentVoucherSelectData paymentVoucherSelectDetails;
    PaymentMethodType paymentType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment_voucher);
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
                if (callingFor.equals(Constant.CALL_EDITPAYMENTVOUCHER)) {
                    paymentVoucherSelectDetails = (PaymentVoucherSelectData) intent.getSerializableExtra("paymentVoucherData");
                    if (paymentVoucherSelectDetails != null) {

                        if (paymentVoucherSelectDetails.getAmount() != null)
                            edAmount.setText(paymentVoucherSelectDetails.getAmount().toString());

                        if (paymentVoucherSelectDetails.getReason() != null)
                            edReason.setText(paymentVoucherSelectDetails.getReason());

                        if (paymentVoucherSelectDetails.getTaxamount() != null)
                            edTaxAmount.setText(paymentVoucherSelectDetails.getTaxamount().toString());

                        if (paymentVoucherSelectDetails.getTotalamount() != null)
                            edTotalAmount.setText(paymentVoucherSelectDetails.getTotalamount().toString());


                        if (paymentVoucherSelectDetails.getPaymentmethodId() != null) {
                            List<PaymentMethodType> data = new ArrayList<>();
                            objectPaymentMethodTypeList.clear();
                            objectPaymentMethodTypeList.addAll(data);
                            UtilView.setupItemAdapter(activity, spinPaymentMethodName, objectPaymentMethodTypeList);
                            for (int i = 0; i < data.size(); i++) {
                                if (paymentVoucherSelectDetails.getPaymentmethodId()  == paymentType.getPaymentmethodId()) {
                                    spinPaymentMethodName.setSelection(i);
                                }

                            }

                        }

                        if (paymentVoucherSelectDetails.getStatus() != null) {

                            if (paymentVoucherSelectDetails.getStatus().equals("Used")) {
                                spinVouvherStatus.setSelection(0);
                            }
                            if (paymentVoucherSelectDetails.getStatus().equals("UnUsed")) {
                                spinVouvherStatus.setSelection(1);
                            }

                            objectVoucherStatusList.clear();
                            objectVoucherStatusList.add("Used");
                            objectVoucherStatusList.add("UnUsed");
                            UtilView.setupItemAdapter(activity, spinVouvherStatus, objectVoucherStatusList);


                        }


                        if (paymentVoucherSelectDetails.getFromDate() != null) {
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTimeInMillis(Long.parseLong(paymentVoucherSelectDetails.getFromDate()));
                            int year = calendar.get(Calendar.YEAR);
                            int month = calendar.get(Calendar.MONTH);
                            final int day = calendar.get(Calendar.DAY_OF_MONTH);
                            String FromDate = String.valueOf(new StringBuilder().append(year).append("-").append(month + 1).append("-").append(day));
                            edFromDate.setText(FromDate);

                            final TimeZone utc = TimeZone.getTimeZone("UTC");
                            final SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                            f.setTimeZone(utc);
                            fromDate = f.format(calendar.getTime());
                        }


                        if (paymentVoucherSelectDetails.getToDate() != null) {
                            Calendar calendar1 = Calendar.getInstance();
                            calendar1.setTimeInMillis(Long.parseLong(paymentVoucherSelectDetails.getToDate()));
                            int year1 = calendar1.get(Calendar.YEAR);
                            int month1 = calendar1.get(Calendar.MONTH);
                            final int day1 = calendar1.get(Calendar.DAY_OF_MONTH);
                            String ToDate = String.valueOf(new StringBuilder().append(year1).append("-").append(month1 + 1).append("-").append(day1));
                            edToDate.setText(ToDate);

                            final TimeZone utc = TimeZone.getTimeZone("UTC");
                            final SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                            f.setTimeZone(utc);
                            toDate = f.format(calendar1.getTime());
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

        spinVouvherStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = objectVoucherStatusList.get(i);
                if (obj instanceof String) {
                    spinVouvherStatus.setSelection(i);
                    voucherStatus = (String) spinVouvherStatus.getSelectedItem();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spinPaymentMethodName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = objectPaymentMethodTypeList.get(i);
                if (obj instanceof PaymentMethodType) {
                    spinPaymentMethodName.setSelection(i);
                    paymentType = (PaymentMethodType) spinPaymentMethodName.getSelectedItem();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        getPaymentMethodType();

    }

    private void getPaymentMethodType() {
        String url = "";

        if (callingFor != null) {
            url = serverUrl + "/hipos//1/" + Constant.FUNTION_GETPAYMENTVOUCHERTYPESlIST;
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
                                    paymentMethodTypeList = new ArrayList<PaymentMethodType>();
                                    JSONArray resultJsonArray = new JSONArray(result.toString());
                                    for (int i = 0; i < resultJsonArray.length(); i++) {
                                        JSONObject pmJson = resultJsonArray.getJSONObject(i);
                                        Gson gson = new Gson();
                                        PaymentMethodType sdata = gson.fromJson(pmJson.toString(), PaymentMethodType.class);
                                        paymentMethodTypeList.add(sdata);

                                    }
                                    if (paymentMethodTypeList != null && paymentMethodTypeList.size() > 0) {
                                        objectPaymentMethodTypeList.clear();
                                        //objectPaymentMethodTypeList.add("Select");
                                        objectPaymentMethodTypeList.addAll(paymentMethodTypeList);
                                        UtilView.setupItemAdapter(activity, spinPaymentMethodName, objectPaymentMethodTypeList);


                                    }

                                    objectVoucherStatusList.clear();
                                    objectVoucherStatusList.add("Used");
                                    objectVoucherStatusList.add("UnUsed");
                                    UtilView.setupItemAdapter(activity, spinVouvherStatus, objectVoucherStatusList);

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
                addPaymentVoucher();
                break;
            case R.id.btnClose:
                finish();
                break;
        }

    }

    private void addPaymentVoucher() {
        String amount = edAmount.getText().toString().trim();
        String reason = edReason.getText().toString().trim();
        String totalAmount = edTotalAmount.getText().toString().trim();
        String taxAmount = edTaxAmount.getText().toString().trim();


        if ((amount != null && !amount.equals("")) && (totalAmount != null && !totalAmount.equals("")) && (taxAmount != null && !taxAmount.equals("")) && (!reason.equals("") && reason != null) && (fromDate != null && !fromDate.equals("")) && (toDate != null && !toDate.equals("") && isAllValid)) {

            Long PaymentmethodId = paymentType.getPaymentmethodId();
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {

                UtilView.showProgessBar(activity, progressBar);
                AddPaymentVoucherData voucherData = new AddPaymentVoucherData();
                voucherData.setAmount(amount);
                voucherData.setReason(reason);
                voucherData.setTotalamount(totalAmount);
                voucherData.setTaxamount(taxAmount);
                voucherData.setFromDate(fromDate);
                voucherData.setPaymentmethodId(PaymentmethodId);
                voucherData.setToDate(toDate);
                voucherData.setStatus(voucherStatus);


                if (callingFor.equals(Constant.CALL_EDITPAYMENTVOUCHER)) {

                    voucherData.setPvId(paymentVoucherSelectDetails.getPvId());
                }

                String url = serverUrl + "/hipos//1/" + Constant.FUNTION_ADDPAYMENTVOUCHER;

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
                                            AddPaymentVoucherData addVoucherData = gson.fromJson(jsonObject.toString(), AddPaymentVoucherData.class);

                                            if (addVoucherData != null) {
                                                Intent returnIntent = new Intent();
                                                activity.setResult(Activity.RESULT_OK, returnIntent);
                                                activity.finish();
                                                UtilView.showToast(activity, "Payment Voucher added successfully.");
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
                        task.execute(new Gson().toJson(voucherData), url, "");

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
            if (reason.equals("")) {
                edReason.setError(getString(R.string.err_msg));
            }
            if (fromDate.equals("")) {
                edFromDate.setError(getString(R.string.err_msg));
            }
            if (toDate.equals("")) {
                edToDate.setError(getString(R.string.err_msg));
            }

            double Amount = 0, TotalAmount = 0, TaxAmount = 0;
            if (amount.equals("") || amount == null) {
                isAllValid = true;
                edAmount.setError(null);
            } else {
                try {
                    Amount = Double.parseDouble(amount);
                    isAllValid = true;
                } catch (NumberFormatException e) {
                    edAmount.setError(getResources().getString(R.string.error_numberformate));
                    isAllValid = false;
                }
            }

            if (totalAmount.equals("") || totalAmount == null) {
                isAllValid = true;
                edTotalAmount.setError(null);
            } else {
                try {
                    TotalAmount = Double.parseDouble(totalAmount);
                    isAllValid = true;
                } catch (NumberFormatException e) {
                    edTotalAmount.setError(getResources().getString(R.string.error_numberformate));
                    isAllValid = false;
                }
            }
            if (taxAmount.equals("") || taxAmount == null) {
                isAllValid = true;
                edTaxAmount.setError(null);
            } else {
                try {
                    TaxAmount = Double.parseDouble(amount);
                    isAllValid = true;
                } catch (NumberFormatException e) {
                    edTaxAmount.setError(getResources().getString(R.string.error_numberformate));
                    isAllValid = false;
                }
            }


        }
    }

    public void getFromDatePicker(Activity mActivity) {
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
                edFromDate.setText(String.valueOf(new StringBuilder().append(year).append("-").append(month + 1).append("-").append(dayOfMonth)));
                calendar.set(year, month, dayOfMonth);
                fromDate = f.format(calendar.getTime());
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
        final TimeZone utc = TimeZone.getTimeZone("UTC");
        final SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        f.setTimeZone(utc);
        DatePickerDialog datePickerDialog = new DatePickerDialog(mActivity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                edToDate.setText(String.valueOf(new StringBuilder().append(year).append("-").append(month + 1).append("-").append(dayOfMonth)));
                calendar.set(year, month, dayOfMonth);
                toDate = f.format(calendar.getTime());
            }
        }, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();


    }

}
