package in.hiaccounts.hinext.controlpanel.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
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
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.currency.CurrencyDatum;
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.exchange_rate.ExchangeRateDatum;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 8/21/2017.
 */

public class Activity_Config_ExchangeRate extends AppCompatActivity implements TextWatcher {


    ExchangeRateDatum exchangeDetail;

    public static String TAG = Activity_Config_ExchangeRate.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.spinnerCurrency)
    Spinner spinnerCurrency;
    @BindView(R.id.edExchangeRate)
    EditText edExchangeRate;
    @BindView(R.id.edEffectiveDate)
    EditText edEffectiveDate;
    @BindView(R.id.edCustomesExRate)
    EditText edCustomesExRate;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.btnClose)
    Button btnClose;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;

    private Activity activity;
    private ServiceHandler serviceHandler;
    private boolean isInternatePrsenet = false;
    String callingFor, serverUrl;
    String effectiveDate = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpanel_configexchangerate);
        ButterKnife.bind(this);
        initComponentView();
    }

    private void initComponentView() {

        activity = this;
        serviceHandler = new ServiceHandler(activity);
        serverUrl = UtilView.getUrl(activity);

        getCurrencyFromServer();

        Intent intent = getIntent();
        if (intent != null) {
            callingFor = intent.getStringExtra("callingFor");
            if (callingFor != null)
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


        edExchangeRate.addTextChangedListener(this);


    }


    private void addExchangeRate() {

        String exchangeRate = edExchangeRate.getText().toString().trim();
        String customExhangeRate = edCustomesExRate.getText().toString().trim();
        CurrencyDatum currency = (CurrencyDatum) spinnerCurrency.getSelectedItem();
        Long currencyId = currency.getCurrencyId();
        if (!exchangeRate.equals("") && exchangeRate != null && currencyId != null) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("exchangeRateValue", exchangeRate);
                jsonObject.put("exchangeRateEffectiveDate", effectiveDate);
                jsonObject.put("customsexchangeRateValue", customExhangeRate);
                jsonObject.put("currency", currencyId);
                jsonObject.put("currencyId", currencyId);
                //

                if (callingFor.equals(Constant.CALL_EDITEXRATE)) {
                    jsonObject.put("exchangeRateId", exchangeDetail.getExchangeRateId());

                }

            } catch (JSONException je) {

                UtilView.showLogCat(TAG, "JSon Exception " + je.getMessage());
            }


            String url = serverUrl + "/hipos//undefined/" + Constant.FUNTION_ADDEXCHANGERATE;

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
                                    if (result.equals(getResources().getString(R.string.error_rsonsecode204))) {
                                        UtilView.showToast(activity, "Currency already used.");
                                    } else {

                                        Gson gson = new Gson();
                                        try {
                                            JSONObject jsonObject = new JSONObject(result.toString());
                                            ExchangeRateDatum exchangeRateData1 = gson.fromJson(jsonObject.toString(), ExchangeRateDatum.class);

                                            if (exchangeRateData1 != null) {
                                                Intent returnIntent = new Intent();
                                                activity.setResult(Activity.RESULT_OK, returnIntent);
                                                activity.finish();
                                                UtilView.showToast(activity, "Exchange rate Added successfully.");
                                            }


                                        } catch (Exception e) {
                                            UtilView.showLogCat(TAG, "TaskCompleteListener Exception " + e.toString());
                                        }
                                    }
                                }
                            } else {
                                UtilView.showErrorDialog(getResources().getString(R.string.error_null), activity);
                            }
                        }
                    }, false);
                    task.execute(jsonObject.toString(), url, "");

                }
                if (!isInternatePrsenet) {
                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
                }
            } else {
                UtilView.gotToLogin(activity);
            }
        } else {
            if (exchangeRate == null || exchangeRate.equals("")) {
                edExchangeRate.setError(getResources().getString(R.string.err_msg));
            }

            if (currencyId == null || currencyId.equals("")) {
                TextView tv = (TextView) spinnerCurrency.getSelectedView();
                tv.setError(getResources().getString(R.string.err_msg));

            }

        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        edExchangeRate.setError(null);

    }

    @Override
    public void afterTextChanged(Editable s) {


    }


    @OnClick({R.id.edEffectiveDate, R.id.btnSave, R.id.btnClose})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edEffectiveDate:
                getDatePicker(activity);
                break;
            case R.id.btnSave:
                addExchangeRate();
                break;
            case R.id.btnClose:
                finish();
                break;
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
                edEffectiveDate.setText(String.valueOf(new StringBuilder().append(year).append("-").append(month + 1).append("-").append(dayOfMonth)));
                calendar.set(year, month, dayOfMonth);
                effectiveDate = f.format(calendar.getTime());
            }
        }, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();


    }


    private void getCurrencyFromServer() {
        String url = serverUrl + "/hipos//1/" + Constant.FUNTION_GETEXRATECURRENCYLIST;

        if (serverUrl != null) {
            isInternatePrsenet = serviceHandler.isConnectingToInternet();
            if (isInternatePrsenet) {
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

                                Gson gson = new Gson();
                                List<CurrencyDatum> list = new ArrayList<>();
                                try {
                                    JSONArray jsonArray = new JSONArray(result.toString());
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                                        CurrencyDatum currencyData = gson.fromJson(jsonObject.toString(), CurrencyDatum.class);
                                        list.add(currencyData);
                                    }
                                    UtilView.setupCurrencyDataAdapter(activity, spinnerCurrency, list);

                                    if (callingFor != null) {
                                        if (callingFor.equals(Constant.CALL_EDITEXRATE)) {
                                            Intent intent = getIntent();
                                            exchangeDetail = (ExchangeRateDatum) intent.getSerializableExtra("exchangeRateData");

                                            if (exchangeDetail != null) {
                                                if (exchangeDetail.getExchangeRateEffectiveDate() != null)
                                                    edEffectiveDate.setText(exchangeDetail.getExchangeRateEffectiveDate());
                                                    effectiveDate = exchangeDetail.getExchangeRateEffectiveDate();


                                                if (exchangeDetail.getCustomsexchangeRateValue() != null)
                                                    edExchangeRate.setText(exchangeDetail.getExchangeRateValue());


                                                if (exchangeDetail.getCustomsexchangeRateValue() != null)
                                                    edCustomesExRate.setText(exchangeDetail.getCustomsexchangeRateValue());


                                                for (int i=0;i<exchangeDetail.getCurrencyDTOList().size();i++){
                                                    if (exchangeDetail.getCurrencyDTOList().get(i).getCurrencyId() != null) {

                                                        for (int j = 0; j < list.size(); j++) {

                                                            if (exchangeDetail.getCurrencyDTOList().get(i).getCurrencyId().equals(list.get(j).getCurrencyId())) {
                                                                UtilView.showLogCat(TAG, "Edit currecny id " + exchangeDetail.getCurrencyDTOList().get(i).getCurrencyId() + " : " + list.get(j).getCurrencyId());

                                                                spinnerCurrency.setSelection(j);
                                                                //UtilView.showLogCat(TAG, "Equal Currecy id " + exchangeDetail.getCurrencyDTOList().get(i).getCurrencyId());
                                                            }

                                                        }
                                                    }
                                                }


                                            }
                                        }
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
                //task.execute(url, "");
            }
            if (!isInternatePrsenet) {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
            }
        } else {
            UtilView.gotToLogin(activity);
        }
    }
}
