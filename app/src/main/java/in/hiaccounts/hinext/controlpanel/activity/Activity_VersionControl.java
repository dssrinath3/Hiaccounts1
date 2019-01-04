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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hiaccounts.R;
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.versioncontrol.VersionControlData;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

public class Activity_VersionControl extends AppCompatActivity implements TextWatcher {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.edProjectName)
    EditText edProjectName;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.edProjectDesc)
    EditText edProjectDesc;
    @BindView(R.id.spinnerStatus)
    Spinner spinnerProjectStatus;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.btnClose)
    Button btnClose;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    String callingFor, serverUrl, projectStatus, projectName, projectDescription;
    VersionControlData versionControlData;
    private Activity activity;
    private ServiceHandler serviceHandler;
    private boolean isInternatePrsenet = false;
    private List<Object> objectProjectStatusList = new ArrayList<Object>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version_control);
        ButterKnife.bind(this);
        initComponentView();
    }

    private void initComponentView() {
        activity = this;
        serviceHandler = new ServiceHandler(activity);
        serverUrl = UtilView.getUrl(activity);
        UtilView.setupAdapter(activity, spinnerProjectStatus, getResources().getStringArray(R.array.config_project_status));
        Intent intent = getIntent();
        if (intent != null) {
            callingFor = intent.getStringExtra("callingFor");


            if (callingFor != null) {
                toolbar.setTitle(callingFor);
                if (callingFor.equals(Constant.CALL_EDITVERSIONCONTROL)) {
                    versionControlData = (VersionControlData) intent.getSerializableExtra("versionData");

                    if (versionControlData != null) {
                        if (versionControlData.getProjectname() != null)
                            edProjectName.setText(versionControlData.getProjectname());

                        if (versionControlData.getDescription() != null)
                            edProjectDesc.setText(versionControlData.getDescription());

                        if (versionControlData.getStatus() != null) {
                            if (versionControlData.getStatus().equals("Active")) {
                                spinnerProjectStatus.setSelection(0);
                            }
                            if (versionControlData.getStatus().equals("InActive")) {
                                spinnerProjectStatus.setSelection(1);
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


        edProjectName.addTextChangedListener(this);
        edProjectDesc.addTextChangedListener(this);

        spinnerProjectStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
    @OnClick({R.id.btnSave, R.id.btnClose})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSave:
                addConfigVersionControl();
                break;
            case R.id.btnClose:
                finish();
                break;
        }
    }


    private void addConfigVersionControl() {
        projectName = edProjectName.getText().toString().trim();
        projectDescription = edProjectDesc.getText().toString().trim();

        if (!projectName.equals("") && !projectDescription.equals("")) {
            isInternatePrsenet = serviceHandler.isConnectingToInternet();
            if (isInternatePrsenet) {

                UtilView.showProgessBar(activity, progressBar);
                VersionControlData versionControlDatas = new VersionControlData();
                if (callingFor.equals(Constant.CALL_EDITVERSIONCONTROL)) {
                    versionControlDatas.setId(versionControlData.getId());
                }
                versionControlDatas.setProjectname(projectName);
                versionControlDatas.setDescription(projectDescription);
                versionControlDatas.setStatus((String) spinnerProjectStatus.getSelectedItem());


                final Gson gson = new Gson();
                String itemJson = gson.toJson(versionControlDatas);
                String url = "";
                if (url != null) {
                    url = serverUrl + "/version//1/" + Constant.FUNTION_ADDVERSIONCONTROL;
                }
                if (serverUrl != null) {
                    PostDataTask postDataTask = new PostDataTask(activity, new AsyncTaskCompleteListener<String>() {
                        @Override
                        public void onTaskComplete(String result) {
                            UtilView.hideProgessBar(activity, progressBar);
                            if (result != null) {
                                try {
                                    List<VersionControlData> itemsLists=new ArrayList<>();

                                    VersionControlData item=gson.fromJson(result.toString(),VersionControlData.class);
                                    itemsLists.add(item);

                                }catch (Exception e){

                                    UtilView.showErrorDialog(getResources().getString(R.string.response_error),activity);
                                }
                                UtilView.showToast(activity, "Version Control Added Successfully.");
                                Intent returnIntent = new Intent();
                                activity.setResult(Activity.RESULT_OK, returnIntent);
                                activity.finish();


                            } else {
                                UtilView.showToast(activity, projectName + " doesn't create successfully.");
                            }
                        }
                    }, false);
                    postDataTask.execute(itemJson.toString(), url, "");
                } else {
                    UtilView.gotToLogin(activity);
                }
            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
            }
        } else {
            if (projectName == null || projectName.equals("")) {
                edProjectName.setError(getResources().getString(R.string.err_msg));
            }

            if (projectDescription == null || projectDescription.equals("")) {
                edProjectDesc.setError(getResources().getString(R.string.err_msg));
            }
        }





    }
}
