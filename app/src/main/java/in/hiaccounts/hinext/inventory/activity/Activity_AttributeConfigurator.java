package in.hiaccounts.hinext.inventory.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.inventory.model.attributrconfigurator.AddAttributeConfigurator;
import in.hiaccounts.hinext.inventory.model.attributrconfigurator.AttributeConfig;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

public class Activity_AttributeConfigurator extends AppCompatActivity {
    public static String TAG = Activity_AttributeConfigurator.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edAttributeConfigName)
    EditText edAttributeConfigName;
    @BindView(R.id.edAtrributeConfigDesc)
    EditText edAtrributeConfigDesc;
    @BindView(R.id.spinAttributeConfigStatus)
    Spinner spinAttributeConfigStatus;
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
    private boolean isAllValid = true;
    String attributeStatus;
    String serverUrl;

    private List<Object> objectAttrConfigStatusList = new ArrayList<Object>();

    AttributeConfig attributeConfigDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attribute_configurator);
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
                if (callingFor.equals(Constant.CALL_EDITATTRIBUTECONFIGURATOR)) {
                    attributeConfigDetails = (AttributeConfig) intent.getSerializableExtra("attributeConfigData");
                    if (attributeConfigDetails != null) {

                        if (attributeConfigDetails.getAttributeConfiguratorName() != null)
                            edAttributeConfigName.setText(attributeConfigDetails.getAttributeConfiguratorName());


                        if (attributeConfigDetails.getAttributeConfiguratorDescription() != null) {
                            edAtrributeConfigDesc.setText(attributeConfigDetails.getAttributeConfiguratorDescription());
                        }



                        objectAttrConfigStatusList.clear();
                        objectAttrConfigStatusList.add("Active");
                        objectAttrConfigStatusList.add("InActive");
                        UtilView.setupItemAdapter(activity, spinAttributeConfigStatus, objectAttrConfigStatusList);
                        if (attributeConfigDetails.getStatus() != null) {
                            if (attributeConfigDetails.getStatus().equals("Active")) {
                                spinAttributeConfigStatus.setSelection(0);
                            }
                            if (attributeConfigDetails.getStatus().equals("InActive")) {
                                spinAttributeConfigStatus.setSelection(1);
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



        spinAttributeConfigStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = objectAttrConfigStatusList.get(i);
                if (obj instanceof String) {
                    spinAttributeConfigStatus.setSelection(i);
                    attributeStatus = (String) spinAttributeConfigStatus.getSelectedItem();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        objectAttrConfigStatusList.clear();
        objectAttrConfigStatusList.add("Active");
        objectAttrConfigStatusList.add("InActive");
        UtilView.setupItemAdapter(activity, spinAttributeConfigStatus, objectAttrConfigStatusList);


    }


    @OnClick({R.id.btnSave, R.id.btnClose})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSave:
                addAttributeConfigurator();
                break;
            case R.id.btnClose:
                finish();
                break;
        }
    }

    private void addAttributeConfigurator() {
        String attributeConfigName = edAttributeConfigName.getText().toString().trim();
        String attributeConfigDesc = edAtrributeConfigDesc.getText().toString().trim();

        if ((!attributeConfigName.equals("") && attributeConfigName != null) && (!attributeConfigDesc.equals("") && attributeConfigDesc != null) && isAllValid) {

            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {

                UtilView.showProgessBar(activity, progressBar);
                AddAttributeConfigurator attributeConfigData = new AddAttributeConfigurator();
                attributeConfigData.setAttributeConfiguratorName(attributeConfigName);
                attributeConfigData.setStatus(attributeStatus);
                attributeConfigData.setAttributeConfiguratorDescription(attributeConfigDesc);

                if (callingFor.equals(Constant.CALL_EDITATTRIBUTECONFIGURATOR)) {
                    attributeConfigData.setAttributeConfiguratorId(attributeConfigDetails.getAttributeConfiguratorId());
                }

                String url = serverUrl + "/hipos//1/" + Constant.FUNTION_ADDATTRIBUTECONFIGURATOR;

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
                                            AddAttributeConfigurator addAttribute = gson.fromJson(jsonObject.toString(), AddAttributeConfigurator.class);

                                            if (addAttribute != null) {
                                                Intent returnIntent = new Intent();
                                                activity.setResult(Activity.RESULT_OK, returnIntent);
                                                activity.finish();
                                                UtilView.showToast(activity, "Attribute Configurator added successfully.");
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
                        task.execute(new Gson().toJson(attributeConfigData), url, "");

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
            if (attributeConfigName.equals("")) {
                edAttributeConfigName.setError(getString(R.string.err_msg));
            }
            if (attributeConfigDesc.equals("")) {
                edAtrributeConfigDesc.setError(getString(R.string.err_msg));
            }


        }
    }
}
