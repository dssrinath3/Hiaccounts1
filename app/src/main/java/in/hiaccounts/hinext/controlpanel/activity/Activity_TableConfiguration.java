package in.hiaccounts.hinext.controlpanel.activity;

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
import android.widget.Toast;

import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hiaccounts.R;
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.cart.AddCartDatum;
import in.hiaccounts.hinext.controlpanel.model.display_setup.table_configuration.AddTableConfigData;
import in.hiaccounts.hinext.controlpanel.model.display_setup.table_configuration.TableConfigData;
import in.hiaccounts.hinext.controlpanel.model.display_setup.table_configuration.TableConfigDataList;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

public class Activity_TableConfiguration extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edConfigurationName)
    EditText edConfigurationName;
    @BindView(R.id.edRows)
    EditText edRows;
    @BindView(R.id.edColumns)
    EditText edColumns;
    @BindView(R.id.spinnerStatus)
    Spinner spinnerStatus;
    @BindView(R.id.llstatus)
    LinearLayout llstatus;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.btnClose)
    Button btnClose;
    @BindView(R.id.ll1)
    LinearLayout ll1;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    private Activity activity;
    private ServiceHandler serviceHandler;
    private boolean isInternatePrsenet = false;
    String callingFor, serverUrl, status;
    private TableConfigDataList tableConfigDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_configuration);
        ButterKnife.bind(this);
        initComponentView();
    }

    private void initComponentView() {
        activity = this;
        serviceHandler = new ServiceHandler(activity);
        serverUrl = UtilView.getUrl(activity);
        UtilView.setupAdapter(activity, spinnerStatus, getResources().getStringArray(R.array.config_project_status));
        Intent intent = getIntent();
        if (intent != null) {
            callingFor = intent.getStringExtra("callingFor");


            if (callingFor != null) {
                toolbar.setTitle(callingFor);
                if (callingFor.equals(Constant.CALL_EDITTABLECONFIG)) {
                    tableConfigDetails = (TableConfigDataList) intent.getSerializableExtra("tableConfigData");

                    if (tableConfigDetails != null) {
                        if (tableConfigDetails.getConfigurationname() != null)
                            edConfigurationName.setText(tableConfigDetails.getConfigurationname());

                        if (tableConfigDetails.getRowtableconfig() != null)
                            edRows.setText(""+tableConfigDetails.getRowtableconfig());

                        if (tableConfigDetails.getColumntableconfig() != null)
                            edColumns.setText(""+tableConfigDetails.getColumntableconfig());



                   /*     if (tableConfigDetails.getStatus() !=null){
                            if (tableConfigDetails.getStatus().equals("Active"))
                            {
                                spinnerStatus.setSelection(0);
                            }
                            if (tableConfigDetails.getStatus().equals("InActive"))
                            {
                                spinnerStatus.setSelection(1);
                            }
                        }*/


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



        spinnerStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    @OnClick({R.id.btnSave, R.id.btnClose})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSave:
                addTableConfiguration();
                break;
            case R.id.btnClose:
                finish();
                break;
        }
    }

    private void addTableConfiguration() {
        final String configname = edConfigurationName.getText().toString().trim();
        String columns = edColumns.getText().toString().trim();
        String rows = edRows.getText().toString().trim();


        if (!configname.equals("")  && !columns.equals("") && !rows.equals("")) {
            isInternatePrsenet = serviceHandler.isConnectingToInternet();
            if (isInternatePrsenet) {

                UtilView.showProgessBar(activity, progressBar);
                AddTableConfigData addTableConfigData = new AddTableConfigData();
                if (callingFor.equals(Constant.CALL_EDITTABLECONFIG)) {
                    addTableConfigData.setTableconfigid(tableConfigDetails.getTableconfigid());
                }
                addTableConfigData.setConfigurationname(configname);
                addTableConfigData.setColumntableconfig(Long.valueOf(columns));
                addTableConfigData.setRowtableconfig(Long.valueOf(rows));
                //addTableConfigData.setStatus((String) spinnerStatus.getSelectedItem());


                final Gson gson = new Gson();
                String itemJson = gson.toJson(addTableConfigData);
                String url = "";
                if (url != null) {
                    url = serverUrl + "/hipos//1/" + Constant.FUNTION_ADDTABLECONFIG;
                }
                if (serverUrl != null) {
                    PostDataTask postDataTask = new PostDataTask(activity, new AsyncTaskCompleteListener<String>() {
                        @Override
                        public void onTaskComplete(String result) {
                            UtilView.hideProgessBar(activity, progressBar);
                            if (result != null) {
                                try {
                                    if (callingFor.equals(Constant.CALL_EDITTABLECONFIG)) {
                                        UtilView.showToast(activity,  "Table Configuration Updated Successfully.");

                                    }else{
                                        UtilView.showToast(activity,  "Table Configuration Added Successfully.");

                                    }
                                   Intent returnIntent = new Intent();
                                    activity.setResult(Activity.RESULT_OK, returnIntent);
                                    activity.finish();
                                }catch (Exception e){

                                    UtilView.showErrorDialog(getResources().getString(R.string.response_error),activity);
                                }




                            } else {
                                UtilView.showToast(activity, configname + " doesn't create successfully.");
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
            if (configname == null || configname.equals("")) {
                edConfigurationName.setError(getResources().getString(R.string.err_msg));
            }

            if (rows == null || rows.equals("")) {
                edRows.setError(getResources().getString(R.string.err_msg));
            }
            if (columns == null || columns.equals("")) {
                edColumns.setError(getResources().getString(R.string.err_msg));
            }
        }
    }
}
