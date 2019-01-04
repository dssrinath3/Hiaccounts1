package in.hiaccounts.hinext.controlpanel.activity;

import android.app.Activity;
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
import android.widget.EditText;

import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.currency.CurrencyDatum;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 8/21/2017.
 */

public class Activity_Config_Currency extends AppCompatActivity implements TextWatcher {


    public static String TAG = Activity_Config_Currency.class.getSimpleName();
    CurrencyDatum currencyDetail;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edCurrencyCode)
    EditText edCurrencyCode;
    @BindView(R.id.edCurrencyName)
    EditText edCurrencyName;
    @BindView(R.id.edCurrencyDescription)
    EditText edCurrencyDescription;
    @BindView(R.id.edCurrencySymbol)
    EditText edCurrencySymbol;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.btnClose)
    Button btnClose;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    String callingFor, serverUrl;
    private Activity activity;
    private ServiceHandler serviceHandler;
    private boolean isInternatePrsenet = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpanel_configcurrency);
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
                if (callingFor.equals(Constant.CALL_EDITCURRENCY)) {
                    currencyDetail = (CurrencyDatum) intent.getSerializableExtra("currencyData");

                    if (currencyDetail != null) {
                        if (currencyDetail.getCurrencySymbol() != null)
                            edCurrencySymbol.setText(currencyDetail.getCurrencySymbol());

                        if (currencyDetail.getCurrencyName() != null)
                            edCurrencyName.setText(currencyDetail.getCurrencyName());

                        if (currencyDetail.getCurrencyCode() != null)
                            edCurrencyCode.setText(currencyDetail.getCurrencyCode());

                        if (currencyDetail.getCurrencyDescription() != null)
                            edCurrencyDescription.setText(currencyDetail.getCurrencyDescription());
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


        edCurrencyName.addTextChangedListener(this);
        edCurrencyCode.addTextChangedListener(this);
        edCurrencySymbol.addTextChangedListener(this);

    }


    private void addCurrency() {

        String currencyCode = edCurrencyCode.getText().toString().trim();
        String currencyDescriotion = edCurrencyDescription.getText().toString().trim();
        String currencyName = edCurrencyName.getText().toString().trim();
        String currencySymbol = edCurrencySymbol.getText().toString().trim();


        if (!currencyCode.equals("") && currencyCode != null && currencyName != null && !currencyName.equals("")
                && currencySymbol != null && !currencySymbol.equals("")) {

            CurrencyDatum currencyData = new CurrencyDatum();
            currencyData.setCurrencyCode(currencyCode);
            currencyData.setCurrencyDescription(currencyDescriotion);
            currencyData.setCurrencyName(currencyName);
            currencyData.setCurrencySymbol(currencySymbol);


            if (callingFor.equals(Constant.CALL_EDITCURRENCY)) {
                currencyData.setCurrencyId(currencyDetail.getCurrencyId());
            }

            String url = serverUrl + "/hipos//0/" + Constant.FUNTION_ADDCURRENCY;

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

                                    if (result.equals(getResources().getString(R.string.error_rsonsecode204))) {
                                        UtilView.showToast(activity, "Currency already used.");
                                    } else {
                                        Gson gson = new Gson();
                                        try {
                                            JSONObject jsonObject = new JSONObject(result.toString());
                                            CurrencyDatum currencyData1 = gson.fromJson(jsonObject.toString(), CurrencyDatum.class);
                                            if (currencyData1 != null) {
                                                UtilView.showToast(activity, "Currency Added Successfully.");
                                                Intent returnIntent = new Intent();
                                                activity.setResult(Activity.RESULT_OK, returnIntent);
                                                activity.finish();
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
                    task.execute(new Gson().toJson(currencyData), url, "");

                }
                if (!isInternatePrsenet) {
                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
                }
            } else {
                UtilView.gotToLogin(activity);
            }
        } else {
            if (currencyCode == null || currencyCode.equals("")) {
                edCurrencyCode.setError(getResources().getString(R.string.err_msg));
            }

            if (currencyName == null || currencyName.equals("")) {
                edCurrencyName.setError(getResources().getString(R.string.err_msg));
            }
            if (currencySymbol == null || currencySymbol.equals("")) {
                edCurrencySymbol.setError(getResources().getString(R.string.err_msg));
            }
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        edCurrencyName.setError(null);
        edCurrencyCode.setError(null);
        edCurrencySymbol.setError(null);
    }

    @Override
    public void afterTextChanged(Editable s) {


    }

    @OnClick({R.id.btnSave, R.id.btnClose})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSave:
                addCurrency();
                break;
            case R.id.btnClose:
                finish();
                break;
        }
    }
}
