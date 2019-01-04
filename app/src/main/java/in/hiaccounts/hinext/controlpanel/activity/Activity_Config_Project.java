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
import android.widget.Spinner;

import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.project.ProjectDatum;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 8/21/2017.
 */

public class Activity_Config_Project extends AppCompatActivity implements TextWatcher {


    public static String TAG = Activity_Config_Project.class.getSimpleName();
    ProjectDatum projectDetail;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edTitle)
    EditText edTitle;
    @BindView(R.id.edDescription)
    EditText edDescription;
    @BindView(R.id.spinnerStaus)
    Spinner spinnerStaus;
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
        setContentView(R.layout.activity_cpanel_configproject);
        ButterKnife.bind(this);
        initComponentView();
    }

    private void initComponentView() {

        activity = this;
        serviceHandler = new ServiceHandler(activity);

        serverUrl= UtilView.getUrl(activity);

        Intent intent = getIntent();
        UtilView.setupAdapter(activity, spinnerStaus, getResources().getStringArray(R.array.config_project_status));

        if (intent != null) {

            callingFor = intent.getStringExtra("callingFor");
            if (callingFor != null) {
                toolbar.setTitle(callingFor);
            }
            if (callingFor.equals(Constant.CALL_EDITPROJECT)) {

                projectDetail = (ProjectDatum) intent.getSerializableExtra("projectData");

                if (projectDetail != null) {
                    if (projectDetail.getProjectName() != null)
                        edTitle.setText(projectDetail.getProjectName());

                    if (projectDetail.getProjectDesc() != null)
                        edDescription.setText(projectDetail.getProjectDesc());


                    if (projectDetail.getStatus() != null) {

                        if (projectDetail.getStatus().equals("Active")) {
                            spinnerStaus.setSelection(0);
                        }

                        if (projectDetail.getStatus().equals("InActive")) {
                            spinnerStaus.setSelection(1);
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



        edTitle.addTextChangedListener(this);


    }


    private void addProject() {

        String projectTitle = edTitle.getText().toString().trim();
        String projectDescription = edDescription.getText().toString().trim();
        String projecTStatus = (String) spinnerStaus.getSelectedItem();

        ProjectDatum projectData = new ProjectDatum();

        if (projectTitle != null && !projectTitle.equals("")) {
            projectData.setProjectName(projectTitle);
            projectData.setProjectDesc(projectDescription);
            projectData.setStatus(projecTStatus);

            if (callingFor != null) {
                if (callingFor.equals(Constant.CALL_EDITPROJECT)) {
                    if (projectDetail != null) {
                        if (projectDetail.getProjectId() != null)
                            projectData.setProjectId(projectDetail.getProjectId());
                    }
                }
            }
            String url = serverUrl + "/hipos//0/" + Constant.FUNTION_ADDPROJECT;

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
                                        ProjectDatum data = gson.fromJson(jsonObject.toString(), ProjectDatum.class);

                                        if (data != null) {
                                            UtilView.showToast(activity, "Project Added Successfully.");
                                            Intent returnIntent = new Intent();
                                            activity.setResult(Activity.RESULT_OK, returnIntent);
                                            activity.finish();
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
                    task.execute(new Gson().toJson(projectData).toString(), url, "");

                }
                if (!isInternatePrsenet) {
                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
                }
            } else {
                UtilView.gotToLogin(activity);
            }

        } else {
            edTitle.setError(getString(R.string.err_msg));
        }


    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        edTitle.setError(null);

    }

    @Override
    public void afterTextChanged(Editable s) {


    }


    @OnClick({R.id.btnSave, R.id.btnClose})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSave:
                addProject();
                break;
            case R.id.btnClose:
                finish();
                break;
        }
    }
}
