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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.inventory.model.hsncode.HSNCodeData;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 8/21/2017.
 */

public class Activity_Config_HsnCode extends AppCompatActivity implements TextWatcher {


    public static String TAG = Activity_Config_HsnCode.class.getSimpleName();
    HSNCodeData hsnCodeDetail;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edCode)
    EditText edCode;
    @BindView(R.id.edDescription)
    EditText edDescription;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.btnClose)
    Button btnClose;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    String callingFor, serverUrl;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.llCategoryName)
    LinearLayout llCategoryName;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.llContcatPerson)
    LinearLayout llContcatPerson;
    @BindView(R.id.textView1)
    TextView textView1;
    @BindView(R.id.spinnerStatus)
    Spinner spinnerStatus;
    @BindView(R.id.llstatus)
    LinearLayout llstatus;
    @BindView(R.id.ll1)
    LinearLayout ll1;
    private Activity activity;
    private ServiceHandler serviceHandler;
    private boolean isInternatePrsenet = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpanel_hsncode);
        ButterKnife.bind(this);
        initComponentView();
    }

    private void initComponentView() {

        activity = this;
        serviceHandler = new ServiceHandler(activity);
        serverUrl = UtilView.getUrl(activity);
        UtilView.setupAdapter(activity, spinnerStatus, getResources().getStringArray(R.array.config_status));

        Intent intent = getIntent();

        if (intent != null) {
            callingFor = intent.getStringExtra("callingFor");
            if (callingFor != null)
                toolbar.setTitle(callingFor);

            if (callingFor.equals(Constant.CALL_EDITHSN)) {

                hsnCodeDetail = (HSNCodeData) intent.getSerializableExtra("hsnCodeData");

                if (hsnCodeDetail != null) {
                    if (hsnCodeDetail.getMscid() != null)
                        edCode.setText(hsnCodeDetail.getMsiccode());

                    if (hsnCodeDetail.getDescrip() != null)
                        edDescription.setText(hsnCodeDetail.getDescrip());
                }
                if (hsnCodeDetail.getStatus() != null) {
                    if (hsnCodeDetail.getStatus().equals("Active")) {
                        spinnerStatus.setSelection(0);
                    }

                    if (hsnCodeDetail.getStatus().equals("InActive")) {
                        spinnerStatus.setSelection(1);
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


        edCode.addTextChangedListener(this);
        edDescription.addTextChangedListener(this);


    }


    private void addHsnCode() {

        String hsnCode = edCode.getText().toString().trim();
        String hsnDescription = edDescription.getText().toString().trim();


        HSNCodeData hsnCodeData = new HSNCodeData();

        if (hsnCode != null && !hsnCode.equals("") && hsnDescription != null && !hsnDescription.equals("")) {


            hsnCodeData.setMsiccode(hsnCode);
            hsnCodeData.setDescrip(hsnDescription);


            if (callingFor != null) {
                if (callingFor.equals(Constant.CALL_EDITHSN)) {
                    if (hsnCodeDetail != null) {
                        if (hsnCodeDetail.getMscid() != null)
                            hsnCodeData.setMscid(hsnCodeDetail.getMscid());

                        if (hsnCodeDetail.getMsiccomid() != null)
                            hsnCodeData.setMsiccomid(hsnCodeDetail.getMsiccomid());


                    }

                    hsnCodeData.setStatus((String) spinnerStatus.getSelectedItem());
                }

            }

            String url = serverUrl + "/hipos//0/" + Constant.FUNTION_ADDHSNCODE;

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

                                    Gson gson = new Gson();
                                    try {
                                        JSONObject jsonObject = new JSONObject(result.toString());
                                        HSNCodeData data = gson.fromJson(jsonObject.toString(), HSNCodeData.class);

                                        if (data != null) {
                                            UtilView.showToast(activity, "HSN Code add Successfully");
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
                    task.execute(new Gson().toJson(hsnCodeData).toString(), url, "");

                }
                if (!isInternatePrsenet) {
                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
                }
            } else {
                UtilView.gotToLogin(activity);
            }

        } else {
            edCode.setError(getString(R.string.err_msg));
            edDescription.setError(getString(R.string.err_msg));
        }


    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        edCode.setError(null);
        edDescription.setError(null);

    }

    @Override
    public void afterTextChanged(Editable s) {


    }


    @OnClick({R.id.btnSave, R.id.btnClose})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSave:
                addHsnCode();
                break;
            case R.id.btnClose:
                finish();
                break;
        }
    }
}
