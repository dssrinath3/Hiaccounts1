package in.hiaccounts.hinext.controlpanel.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
import in.hiaccounts.hinext.controlpanel.model.display_setup.table_configuration.AddTableConfigData;
import in.hiaccounts.hinext.controlpanel.model.display_setup.table_configuration.TableConfigDataList;
import in.hiaccounts.hinext.controlpanel.model.display_setup.table_configuration.table.AddTableData;
import in.hiaccounts.hinext.controlpanel.model.display_setup.table_configuration.table.TableConfigList;
import in.hiaccounts.hinext.controlpanel.model.display_setup.table_configuration.table.TableDataList;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

public class Activity_Table extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.edTableName)
    EditText edTableName;
    @BindView(R.id.llTableName)
    LinearLayout llTableName;
    @BindView(R.id.textViewchairs)
    TextView textViewchairs;
    @BindView(R.id.edNoChairs)
    EditText edNoChairs;
    @BindView(R.id.llNoofChairs)
    LinearLayout llNoofChairs;
    @BindView(R.id.textViewConfigurationName)
    TextView textViewConfigurationName;
    @BindView(R.id.spinnerConfigname)
    Spinner spinnerConfigname;
    @BindView(R.id.llConfigurationName)
    LinearLayout llConfigurationName;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.spinnerRows)
    Spinner spinnerRows;
    @BindView(R.id.llrows)
    LinearLayout llrows;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.spinnerColumns)
    Spinner spinnerColumns;
    @BindView(R.id.llcolumns)
    LinearLayout llcolumns;
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
    private boolean isInternetPresent = false;
    String callingFor, serverUrl;
    private TableDataList tableDataDetails;
    private List<TableConfigList> configLists;
    private List<String> configurNameList = new ArrayList<>();
    private List<String> rowsandcolomnsList = new ArrayList<>();
    private List<String> rowList = new ArrayList<>();
    private List<String> columnList = new ArrayList<>();
    String configureName="",rows="",columns="",status="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
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
            if (callingFor.equals(Constant.CALL_ADDTABLE)) {
                toolbar.setTitle(callingFor);
                getTableConfigurationList();
            }


            if (callingFor != null) {
                toolbar.setTitle(callingFor);
                if (callingFor.equals(Constant.CALL_EDITTABLE)) {
                    tableDataDetails = (TableDataList) intent.getSerializableExtra("tableData");

                    if (tableDataDetails != null) {
                        if (tableDataDetails.getTableName() != null)
                            edTableName.setText(tableDataDetails.getTableName());


                        if (tableDataDetails.getChair() !=null){
                            edNoChairs.setText(""+tableDataDetails.getChair());
                        }
                        if (tableDataDetails.getConfigurationname() !=null){
                            getTableEditConfigurationList(tableDataDetails);

                        }

                        if (tableDataDetails.getStatus() !=null){
                            if (tableDataDetails.getStatus().equals("Active"))
                            {
                                spinnerStatus.setSelection(0);
                            }
                            if (tableDataDetails.getStatus().equals("InActive"))
                            {
                                spinnerStatus.setSelection(1);
                            }
                        }

                    }

                }
            }
        }
        spinnerConfigname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                configureName = (String) spinnerConfigname.getSelectedItem();
                    callRowsAndColumns(configureName);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        spinnerRows.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                rows = (String) spinnerRows.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        spinnerColumns.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                columns = (String) spinnerColumns.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spinnerStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                status = (String) spinnerStatus.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }

    private void getTableEditConfigurationList(TableDataList tableDataDetails) {
        String url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETTABLECONFIGLIST + "?tableConfigSearchText=" ;

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
                                Log.e("result",result.toString());

                                Gson gson = new Gson();
                                configLists = (List<TableConfigList>) gson.fromJson(result.toString(),
                                        new TypeToken<List<TableConfigList>>() {
                                        }.getType());

                                Log.e("configLists", String.valueOf(configLists.size()));
                                if (configLists!=null && configLists.size()>0){
                                    configurNameList.clear();
                                    configurNameList.add("Select");
                                    for (int i=0;i<configLists.size();i++){

                                        configurNameList.add(configLists.get(i).getConfigurationname());

                                    }

                                    if (configurNameList!=null && configurNameList.size()>0){
                                        UtilView.setupStringArraylistAdapter(activity, spinnerConfigname, configurNameList);
                                    }
                                    if (configurNameList!=null && configurNameList.size()>0){
                                        for (int i=1;i<configurNameList.size();i++){
                                            Log.e("configurNameListitem",configurNameList.get(i));
                                            if (configurNameList.get(i).equals(tableDataDetails.getConfigurationname())){
                                                spinnerConfigname.setSelection(i);
                                                configureName = (String) spinnerConfigname.getSelectedItem();
                                                callEditRowsAndColumns(configureName);
                                            }
                                        }
                                    }


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

    private void callEditRowsAndColumns(String configureName) {
        String url = serverUrl + "/hipos//0/getTableConfigList/"+ configureName;

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
                                    JSONArray array = new JSONArray(result.toString());
                                    if (array!=null ){
                                        for (int i=0;i<array.length();i++){
                                            Log.e("rowscolosresult", String.valueOf(array.getJSONArray(i)));
                                            Gson gson = new Gson();

                                            if ( i == 0){
                                                rowList.clear();
                                                rowList.add("Select");
                                                rowList = gson.fromJson(array.getJSONArray(i).toString(),new TypeToken<List<String>>() {
                                                }.getType());
                                                if (rowList!=null && rowList.size()>0){
                                                    UtilView.setupStringArraylistAdapter(activity, spinnerRows, rowList);
                                                }
                                                if (tableDataDetails.getGridLocationH() !=null){
                                                    if (rowList!=null && rowList.size()>0){
                                                        for (int row=0;row<rowList.size();row++){
                                                            if (rowList.get(row).equals(tableDataDetails.getGridLocationH())){
                                                                spinnerRows.setSelection(row);
                                                                rows = (String) spinnerRows.getSelectedItem();
                                                            }
                                                        }
                                                    }
                                                }

                                            }else if (i == 1){
                                                columnList.clear();
                                                columnList.add("Select");
                                                columnList = gson.fromJson(array.getJSONArray(i).toString(),new TypeToken<List<String>>() {
                                                }.getType());
                                                if (columnList!=null && columnList.size()>0){
                                                    UtilView.setupStringArraylistAdapter(activity, spinnerColumns, columnList);
                                                }
                                                if (tableDataDetails.getGridLocationV() !=null){
                                                    if (columnList!=null && columnList.size()>0){
                                                        for (int col=0;col<columnList.size();col++){
                                                            if (columnList.get(col).equals(tableDataDetails.getGridLocationV())){
                                                                spinnerColumns.setSelection(col);
                                                                columns = (String) spinnerColumns.getSelectedItem();
                                                            }
                                                        }
                                                    }
                                                }
                                            }

                                        }
                                    }
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

    private void callRowsAndColumns(String configureName) {
        String url = serverUrl + "/hipos//0/getTableConfigList/"+ configureName;

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
                                    JSONArray array = new JSONArray(result.toString());
                                    if (array!=null ){
                                        for (int i=0;i<array.length();i++){
                                            Log.e("rowscolosresult", String.valueOf(array.getJSONArray(i)));
                                            Gson gson = new Gson();

                                            if ( i == 0){
                                                rowList.clear();
                                                rowList.add("Select");
                                                rowList = gson.fromJson(array.getJSONArray(i).toString(),new TypeToken<List<String>>() {
                                                }.getType());
                                                if (rowList!=null && rowList.size()>0){
                                                    UtilView.setupStringArraylistAdapter(activity, spinnerRows, rowList);
                                                }
                                            }else if (i == 1){
                                                columnList.clear();
                                                columnList.add("Select");
                                                columnList = gson.fromJson(array.getJSONArray(i).toString(),new TypeToken<List<String>>() {
                                                }.getType());
                                                if (columnList!=null && columnList.size()>0){
                                                    UtilView.setupStringArraylistAdapter(activity, spinnerColumns, columnList);
                                                }
                                            }

                                        }
                                    }
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

    private void getTableConfigurationList() {
           String url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETTABLECONFIGLIST + "?tableConfigSearchText=" ;

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
                                Log.e("result",result.toString());

                                Gson gson = new Gson();
                                configLists = (List<TableConfigList>) gson.fromJson(result.toString(),
                                        new TypeToken<List<TableConfigList>>() {
                                        }.getType());

                                Log.e("configLists", String.valueOf(configLists.size()));
                                if (configLists!=null && configLists.size()>0){
                                    configurNameList.clear();
                                    configurNameList.add("Select");
                                    for (int i=0;i<configLists.size();i++){

                                        configurNameList.add(configLists.get(i).getConfigurationname());

                                    }

                                   if (configurNameList!=null && configurNameList.size()>0){
                                        UtilView.setupStringArraylistAdapter(activity, spinnerConfigname, configurNameList);
                                   }


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
                addTable();
                break;
            case R.id.btnClose:
                finish();
                break;
        }
    }

    private void addTable() {
         String tableName = edTableName.getText().toString().trim();
        String chairs = edNoChairs.getText().toString().trim();


        if (!tableName.equals("")  && !columns.equals("") && !rows.equals("")) {
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {

                UtilView.showProgessBar(activity, progressBar);
                AddTableData addTableData = new AddTableData();
                if (callingFor.equals(Constant.CALL_EDITTABLE)) {
                    addTableData.setTableid(tableDataDetails.getTableid());
                }
                addTableData.setTableName(tableName);
                addTableData.setChair(chairs);
                addTableData.setConfigurationname(configureName);
                addTableData.setGridLocationH(Long.valueOf(rows));
                addTableData.setGridLocationV(Long.valueOf(columns));
                addTableData.setStatus(status);


                final Gson gson = new Gson();
                String itemJson = gson.toJson(addTableData);
                String url = "";
                if (url != null) {
                    url = serverUrl + "/hipos//1/" + Constant.FUNTION_SAVETABLE;
                }
                if (serverUrl != null) {
                    PostDataTask postDataTask = new PostDataTask(activity, new AsyncTaskCompleteListener<String>() {
                        @Override
                        public void onTaskComplete(String result) {
                            UtilView.hideProgessBar(activity, progressBar);
                            if (result != null) {
                                try {
                                    if (callingFor.equals(Constant.CALL_EDITTABLECONFIG)) {
                                        UtilView.showToast(activity,  "Table Updated Successfully.");

                                    }else{
                                        UtilView.showToast(activity,  "Table Added Successfully.");

                                    }
                                    Intent returnIntent = new Intent();
                                    activity.setResult(Activity.RESULT_OK, returnIntent);
                                    activity.finish();
                                }catch (Exception e){

                                    UtilView.showErrorDialog(getResources().getString(R.string.response_error),activity);
                                }




                            } else {
                                UtilView.showToast(activity, tableName + " doesn't create successfully.");
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
            if (tableName == null || tableName.equals("")) {
                edTableName.setError(getResources().getString(R.string.err_msg));
            }

            if (rows == null || rows.equals("")) {
                edNoChairs.setError(getResources().getString(R.string.err_msg));
            }

        }
    }
}
