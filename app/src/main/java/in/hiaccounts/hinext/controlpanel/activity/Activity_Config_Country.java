package in.hiaccounts.hinext.controlpanel.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.country.CountryDatum;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 8/21/2017.
 */

public class Activity_Config_Country extends AppCompatActivity implements TextWatcher {


    public static String TAG = Activity_Config_Country.class.getSimpleName();
    CountryDatum countryDetail;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edCountryName)
    EditText edCountryName;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.btnClose)
    Button btnClose;
    @BindView(R.id.spinnerStatus)
    Spinner spinnerStatus;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    String callingFor, serverUrl;
    private Activity activity;
    private ServiceHandler serviceHandler;
    private boolean isInternatePrsenet = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpanel_configcountry);
        ButterKnife.bind(this);
        initComponentView();
    }

    private void initComponentView() {

        activity = this;
        serviceHandler = new ServiceHandler(activity);
        serverUrl= UtilView.getUrl(activity);

        Intent intent = getIntent();
        UtilView.setupAdapter(activity, spinnerStatus, getResources().getStringArray(R.array.config_status));
        if (intent != null) {
            callingFor = intent.getStringExtra("callingFor");

            if (callingFor!=null){
                toolbar.setTitle(callingFor);
                if (callingFor.equals(Constant.CALL_EDITCOUNTRY)) {
                    countryDetail = (CountryDatum) intent.getSerializableExtra("countryData");
                    if (countryDetail!=null){
                        if (countryDetail.getCountryName()!=null)
                            edCountryName.setText(countryDetail.getCountryName());
                        if (countryDetail.getStatus()!=null)
                        {
                            if (countryDetail.getStatus().equals("Active")){
                                spinnerStatus.setSelection(0);
                            }

                            if (countryDetail.getStatus().equals("InActive")){
                                spinnerStatus.setSelection(1);
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


        edCountryName.addTextChangedListener(this);

    }


    private void addCountry() {


        String countryName = edCountryName.getText().toString().trim();

        if (!countryName.equals("") && countryName != null) {


            CountryDatum countryData = new CountryDatum();
            countryData.setCountryName(countryName);
            countryData.setStatus(spinnerStatus.getSelectedItem());

            if (callingFor.equals(Constant.CALL_EDITCOUNTRY)) {
                countryData.setCountryId(countryDetail.getCountryId());
            }

            String url = serverUrl + "/hipos//2/" + Constant.FUNTION_ADDCOUNTRY;

            if (serverUrl!=null) {
                isInternatePrsenet = serviceHandler.isConnectingToInternet();
                if (isInternatePrsenet) {
                  UtilView.showProgessBar(activity,progressBar);
                    PostDataTask task = new PostDataTask(activity, new AsyncTaskCompleteListener<String>() {
                        @Override
                        public void onTaskComplete(String result) {
                            UtilView.hideProgessBar(activity,progressBar);

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
                                        CountryDatum country = gson.fromJson(jsonObject.toString(), CountryDatum.class);

                                        if (country != null) {
                                            UtilView.showToast(activity, "Country Added Successfully.");
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
                    task.execute(new Gson().toJson(countryData), url, "");

                }
                if (!isInternatePrsenet) {
                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
                }
            }else {
                UtilView.gotToLogin(activity);
            }

        } else {
            edCountryName.setError(getResources().getString(R.string.err_msg));
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        edCountryName.setError(null);

    }

    @Override
    public void afterTextChanged(Editable s) {

    }


    @OnClick({R.id.btnSave, R.id.btnClose})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSave:
                addCountry();
                break;
            case R.id.btnClose:
                finish();
                break;
        }
    }
}
