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
import android.widget.TextView;

import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.inventory.model.attribute.AddAttributeData;
import in.hiaccounts.hinext.inventory.model.attribute.Attribute;
import in.hiaccounts.hinext.inventory.model.attribute.AttributeConfigNameData;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

public class Activity_Attribute extends AppCompatActivity {

    public static String TAG = Activity_Attribute.class.getSimpleName();

    String serverUrl;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.spinAttributeConfigName)
    Spinner spinAttributeConfigName;
    @BindView(R.id.edAttributeName)
    EditText edAttributeName;
    @BindView(R.id.edAtrributeDesc)
    EditText edAtrributeDesc;
    @BindView(R.id.spinAttributeStatus)
    Spinner spinAttributeStatus;
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

    private List<AttributeConfigNameData> attributeConfigList;
    private List<Object> objectAttributeList = new ArrayList<Object>();
    private List<Object> objectAttributeStatusList = new ArrayList<Object>();

    AttributeConfigNameData attributeList;
    Attribute attributeDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attribute);
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
                if (callingFor.equals(Constant.CALL_EDITATTRIBUTE)) {
                    attributeDetails = (Attribute) intent.getSerializableExtra("attributeData");
                    if (attributeDetails != null) {

                        if (attributeDetails.getAttributeName() != null)
                            edAttributeName.setText(attributeDetails.getAttributeName());


                        if (attributeDetails.getAttributeDescription() != null) {
                            edAtrributeDesc.setText(attributeDetails.getAttributeDescription());
                        }

                        if (attributeDetails.getAttributeConfigurator() != null) {
                            List<Attribute> data = new ArrayList<>();
                           // data.add("");
                            objectAttributeList.clear();
                            objectAttributeList.addAll(data);
                            UtilView.setupItemAdapter(activity, spinAttributeConfigName, objectAttributeList);
                            for (int i = 0; i < data.size(); i++) {
                                if (attributeDetails.getAttributeConfigurator() != null) {
                                    spinAttributeConfigName.setSelection(i);
                                }

                            }

                        }

                        objectAttributeStatusList.clear();
                        objectAttributeStatusList.add("Active");
                        objectAttributeStatusList.add("InActive");
                        UtilView.setupItemAdapter(activity, spinAttributeStatus, objectAttributeStatusList);
                        if (attributeDetails.getStatus() != null) {
                            if (attributeDetails.getStatus().equals("Active")) {
                                spinAttributeStatus.setSelection(0);
                            }
                            if (attributeDetails.getStatus().equals("InActive")) {
                                spinAttributeStatus.setSelection(1);
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


        spinAttributeConfigName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = objectAttributeList.get(i);
                if (obj instanceof AttributeConfigNameData) {
                    spinAttributeConfigName.setSelection(i);
                    attributeList = (AttributeConfigNameData) spinAttributeConfigName.getSelectedItem();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinAttributeStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = objectAttributeStatusList.get(i);
                if (obj instanceof String) {
                    spinAttributeStatus.setSelection(i);
                    attributeStatus = (String) spinAttributeStatus.getSelectedItem();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        getAttributeConfigName();
    }






    private void getAttributeConfigName() {
        String url = "";

        if (callingFor != null) {
            url = serverUrl + "/hipos//1/" + Constant.FUNTION_GETATTRIBUTECONFIGNAMElIST;
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
                                    attributeConfigList = new ArrayList<AttributeConfigNameData>();
                                    JSONArray resultJsonArray = new JSONArray(result.toString());
                                    for (int i = 0; i < resultJsonArray.length(); i++) {
                                        JSONObject uomJson = resultJsonArray.getJSONObject(i);
                                        Gson gson = new Gson();
                                        AttributeConfigNameData sdata = gson.fromJson(uomJson.toString(), AttributeConfigNameData.class);
                                        attributeConfigList.add(sdata);

                                    }
                                    if (attributeConfigList != null && attributeConfigList.size() > 0) {
                                        objectAttributeList.clear();
                                        objectAttributeList.add("Select");
                                        objectAttributeList.addAll(attributeConfigList);
                                        UtilView.setupItemAdapter(activity, spinAttributeConfigName, objectAttributeList);


                                    }

                                    objectAttributeStatusList.clear();
                                    objectAttributeStatusList.add("Active");
                                    objectAttributeStatusList.add("InActive");
                                    UtilView.setupItemAdapter(activity, spinAttributeStatus, objectAttributeStatusList);


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

    @OnClick({R.id.btnSave, R.id.btnClose})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSave:
                addAttribute();
                break;
            case R.id.btnClose:
                finish();
                break;
        }
    }

    private void addAttribute() {
        String attributeName = edAttributeName.getText().toString().trim();
        String attributeDesc = edAtrributeDesc.getText().toString().trim();

        if ((!attributeName.equals("") && attributeName != null) && (!attributeDesc.equals("") && attributeDesc != null)  && (attributeList != null && !attributeList.equals("")) && isAllValid) {
            Long AttributeConfiguratorId = attributeList.getAttributeConfiguratorId();

            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {

                UtilView.showProgessBar(activity, progressBar);
                AddAttributeData attributeData = new AddAttributeData();
                attributeData.setAttributeName(attributeName);
                attributeData.setAttributeStatus(attributeStatus);
                attributeData.setAttributeDescription(attributeDesc);
                attributeData.setAttributeConfigurator(String.valueOf(AttributeConfiguratorId));






                if (callingFor.equals(Constant.CALL_EDITATTRIBUTE)) {

                    attributeData.setAttributeId(attributeDetails.getAttributeId());
                }

                String url = serverUrl + "/hipos//1/" + Constant.FUNTION_ADDATTRIBUTE;

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
                                            AddAttributeData addAttribute = gson.fromJson(jsonObject.toString(), AddAttributeData.class);

                                            if (addAttribute != null) {
                                                Intent returnIntent = new Intent();
                                                activity.setResult(Activity.RESULT_OK, returnIntent);
                                                activity.finish();
                                                UtilView.showToast(activity, "Attribute added successfully.");
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
                        task.execute(new Gson().toJson(attributeData), url, "");

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
            if (attributeName.equals("")) {
                edAttributeName.setError(getString(R.string.err_msg));
            }
            if (attributeDesc.equals("")) {
                edAtrributeDesc.setError(getString(R.string.err_msg));
            }
            if (attributeList == null) {
                TextView tv = (TextView) spinAttributeConfigName.getSelectedView();
                tv.setError(getString(R.string.err_msg));
            }

        }
    }

}
