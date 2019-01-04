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
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.shippingmethod.ShippingDatum;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 8/21/2017.
 */

public class Activity_Config_ShippingMethod extends AppCompatActivity implements TextWatcher {


    public static String TAG = Activity_Config_ShippingMethod.class.getSimpleName();
    ShippingDatum shippingDetail;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edShippingMethodName)
    EditText edShippingMethodName;
    @BindView(R.id.edDescription)
    EditText edDescription;
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
        setContentView(R.layout.activity_cpanel_configsjippingmethod);
        ButterKnife.bind(this);

        initComponentView();
    }

    private void initComponentView() {

        activity = this;
        serviceHandler = new ServiceHandler(activity);
        serverUrl= UtilView.getUrl(activity);
        Intent intent = getIntent();

        if (intent != null) {
            callingFor = intent.getStringExtra("callingFor");


            if (callingFor!=null){
                toolbar.setTitle(callingFor);

                if (callingFor.equals(Constant.CALL_EDITSHIPPINGMETHOD)) {
                    shippingDetail = (ShippingDatum) intent.getSerializableExtra("shippingData");

                    if (shippingDetail!=null){
                        if (shippingDetail.getShippingMethodName()!=null)
                            edShippingMethodName.setText(shippingDetail.getShippingMethodName());


                        if (shippingDetail.getShippingMethodDescription()!=null)
                            edDescription.setText(shippingDetail.getShippingMethodDescription());
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


        edShippingMethodName.addTextChangedListener(this);

    }


    private void addShippingMehtod() {


        String shippingMethodName = edShippingMethodName.getText().toString().trim();
        String description = edDescription.getText().toString().trim();

        if (!shippingMethodName.equals("") && shippingMethodName != null) {

            ShippingDatum shippingData=new ShippingDatum();
            shippingData.setShippingMethodName(shippingMethodName);
            shippingData.setShippingMethodDescription(description);

            if (callingFor!=null){
                if (callingFor.equals(Constant.CALL_EDITSHIPPINGMETHOD)) {
                    if (shippingDetail!=null){
                        if (shippingDetail.getShippingMethodId()!=null)
                            shippingData.setShippingMethodId(shippingDetail.getShippingMethodId());
                    }

                }

            }

            String url =serverUrl + "/hipos//0/" + Constant.FUNTION_ADDSHIPPINGMETHOD;

            if (serverUrl!=null) {
                isInternatePrsenet = serviceHandler.isConnectingToInternet();
                if (isInternatePrsenet) {
                    // prepare the Request
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
                                        ShippingDatum data = gson.fromJson(jsonObject.toString(), ShippingDatum.class);

                                        if (data != null) {
                                            UtilView.showToast(activity, "Shipping Added Successfully.");
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
                    task.execute(new Gson().toJson(shippingData), url, "");

                }
                if (!isInternatePrsenet) {
                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
                }
            }else {
                UtilView.gotToLogin(activity);
            }

        } else {
            edShippingMethodName.setError(getResources().getString(R.string.err_msg));
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        edShippingMethodName.setError(null);

    }

    @Override
    public void afterTextChanged(Editable s) {

    }


    @OnClick({R.id.btnSave, R.id.btnClose})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSave:
                addShippingMehtod();
                break;
            case R.id.btnClose:
                finish();
                break;
        }
    }
}
