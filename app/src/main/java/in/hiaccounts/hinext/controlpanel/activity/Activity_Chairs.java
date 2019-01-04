package in.hiaccounts.hinext.controlpanel.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableRow;
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
import in.hiaccounts.hinext.controlpanel.model.display_setup.chairs.AddChairsData;
import in.hiaccounts.hinext.controlpanel.model.display_setup.chairs.ChairsDataList;
import in.hiaccounts.hinext.controlpanel.model.display_setup.chairs.NoOfChairsData;
import in.hiaccounts.hinext.controlpanel.model.display_setup.table_configuration.table.AddTableData;
import in.hiaccounts.hinext.controlpanel.model.display_setup.table_configuration.table.TableConfigList;
import in.hiaccounts.hinext.controlpanel.model.display_setup.table_configuration.table.TableDataList;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

public class Activity_Chairs extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.textViewConfigurationName)
    TextView textViewConfigurationName;
    @BindView(R.id.spinnerConfigname)
    Spinner spinnerConfigname;
    @BindView(R.id.llConfigurationName)
    LinearLayout llConfigurationName;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.spinnerTableName)
    Spinner spinnerTableName;
    @BindView(R.id.llTableName)
    LinearLayout llTableName;
    @BindView(R.id.textViewchairs)
    TextView textViewchairs;
    @BindView(R.id.edNoChairs)
    EditText edNoChairs;
    @BindView(R.id.llNoofChairs)
    LinearLayout llNoofChairs;
    @BindView(R.id.llChairName)
    LinearLayout llChairName;
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
    private ChairsDataList chairsDetails;
    private List<TableConfigList> configLists;
    private List<TableDataList> tableDataList;
    private TableDataList tableNameData;
    private List<String> configurNameList = new ArrayList<>();
    private List<String> chairList;
    String configureName="",tableName="",status="";
    private Long tableId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chairs);
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
            if (callingFor.equals(Constant.CALL_ADDCHAIR)) {
                toolbar.setTitle(callingFor);
                getTableConfigurationList();

                spinnerConfigname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        configureName = (String) spinnerConfigname.getSelectedItem();
                        callGetTableNameList(configureName);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });
                spinnerTableName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        tableNameData = (TableDataList) spinnerTableName.getSelectedItem();
                        if (tableNameData!=null){
                            tableId = tableNameData.getTableid();
                            callGetNoOfChairs(tableNameData);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });

            }

            if (callingFor != null) {
                toolbar.setTitle(callingFor);
                if (callingFor.equals(Constant.CALL_EDITCHAIR)) {
                    chairsDetails = (ChairsDataList) intent.getSerializableExtra("chairData");
                    spinnerConfigname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            configureName = (String) spinnerConfigname.getSelectedItem();
                            callEditTableName(configureName);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                    spinnerTableName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            tableNameData = (TableDataList) spinnerTableName.getSelectedItem();
                            if (tableNameData!=null){
                                tableId = tableNameData.getTableid();
                                callEditGetNoOfChairs(String.valueOf(tableId));
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                    getTableEditConfigurationList();

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
    }

    private void getTableEditConfigurationList() {
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
                                        for (int i=0;i<configurNameList.size();i++){
                                            if (configurNameList.get(i).equals(chairsDetails.getConfig())){
                                                spinnerConfigname.setSelection(i);
                                                configureName = chairsDetails.getConfig();
                                                callEditTableName(chairsDetails.getConfig());
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

    private void callEditTableName(String configureName) {
        String url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETTABLENAMELIST + "?configName="+configureName+"&locationId=0" ;

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
                                Log.e("tablesesult",result.toString());

                                Gson gson = new Gson();
                                tableDataList = gson.fromJson(result.toString(),new TypeToken<List<TableDataList>>() {
                                }.getType());

                                if (tableDataList!=null && tableDataList.size()>0){
                                    UtilView.setupTableListAdapter(activity, spinnerTableName, tableDataList);
                                    for (int i=0;i<tableDataList.size();i++){
                                        if (tableDataList.get(i).getTableName().equals(chairsDetails.getTable())){
                                            spinnerTableName.setSelection(i);
                                            if (tableDataList.get(i).getTableid()!=null){
                                                tableId = tableDataList.get(i).getTableid();
                                                callEditGetNoOfChairs(String.valueOf(tableDataList.get(i).getTableid()));
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

    private void callEditGetNoOfChairs(String tableid) {
        String url = serverUrl + "/hipos//0/getTable?tableId="+tableid ;

        if (serverUrl != null) {
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {
                UtilView.showProgessBar(activity, progressBar);
                GetDataTask task = new GetDataTask(activity, new AsyncTaskCompleteListener<String>() {
                    @SuppressLint("NewApi")
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
                                Log.e("tablerchairsesult",result.toString());
                                Gson gson = new Gson();
                                NoOfChairsData noOfChairsData =gson.fromJson(result.toString(),NoOfChairsData.class);
                                if (noOfChairsData!=null){
                                    if (noOfChairsData.getNoOfChairs()!=null){

                                        edNoChairs.setText(""+noOfChairsData.getNoOfChairs());
                                        LinearLayout llEditChair = findViewById(R.id.llChairName);
                                        llEditChair.removeAllViews();
                                        chairList = new ArrayList<>();
                                        chairList.clear();
                                        for (int ch=0;ch<noOfChairsData.getNoOfChairs();ch++){
                                            EditText et = new EditText(activity);
                                            TableRow.LayoutParams params = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                            params.setMargins(10, 10, 10, 50);
                                            et.setHeight(100);
                                            et.setWidth(100);
                                            ShapeDrawable shape = new ShapeDrawable(new RectShape());
                                            shape.getPaint().setColor(Color.RED);
                                            shape.getPaint().setStyle(Paint.Style.STROKE);
                                            shape.getPaint().setStrokeWidth(3);

                                            // Assign the created border to EditText widget
                                            // et.setBackground(shape);
                                            et.setLayoutParams(params);
                                            et.setBackgroundResource(R.drawable.black_rectangle_with_border);
                                            et.setId(ch+1);
                                            et.setText("ch"+ch);
                                            llEditChair.addView(et);
                                            Log.e("chairs", String.valueOf(ch));
                                            String chairNames = et.getText().toString();
                                            chairList.add(chairNames);
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

    private void callGetNoOfChairs(TableDataList tableNameData) {
        String url = serverUrl + "/hipos//0/getTable?tableId="+tableNameData.getTableid() ;

        if (serverUrl != null) {
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {
                UtilView.showProgessBar(activity, progressBar);
                GetDataTask task = new GetDataTask(activity, new AsyncTaskCompleteListener<String>() {
                    @SuppressLint("NewApi")
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
                                Log.e("tablerchairsesult",result.toString());
                                Gson gson = new Gson();
                                NoOfChairsData noOfChairsData =gson.fromJson(result.toString(),NoOfChairsData.class);
                                if (noOfChairsData!=null){
                                    if (noOfChairsData.getNoOfChairs()!=null){

                                        edNoChairs.setText(""+noOfChairsData.getNoOfChairs());
                                        LinearLayout llEditChair = findViewById(R.id.llChairName);
                                        llEditChair.removeAllViews();
                                        chairList = new ArrayList<>();
                                        chairList.clear();
                                        for (int ch=0;ch<noOfChairsData.getNoOfChairs();ch++){
                                            EditText et = new EditText(activity);
                                            TableRow.LayoutParams params = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                            params.setMargins(10, 10, 10, 50);
                                            et.setHeight(100);
                                            et.setWidth(100);
                                            ShapeDrawable shape = new ShapeDrawable(new RectShape());
                                            shape.getPaint().setColor(Color.RED);
                                            shape.getPaint().setStyle(Paint.Style.STROKE);
                                            shape.getPaint().setStrokeWidth(3);

                                            // Assign the created border to EditText widget
                                           // et.setBackground(shape);
                                            et.setLayoutParams(params);
                                            et.setBackgroundResource(R.drawable.black_rectangle_with_border);
                                            et.setId(ch+1);
                                            et.setText("ch"+ch);
                                            llEditChair.addView(et);
                                            Log.e("chairs", String.valueOf(ch));
                                            String chairNames = et.getText().toString();
                                            chairList.add(chairNames);
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

    private void callGetTableNameList(String configureName) {
        String url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETTABLENAMELIST + "?configName="+configureName+"&locationId=0" ;

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
                                Log.e("tablesesult",result.toString());

                                Gson gson = new Gson();
                               /* TableDataList dataList = new TableDataList();
                                dataList.setTableName("Select");
                                tableDataList.add(dataList);*/
                                tableDataList = gson.fromJson(result.toString(),new TypeToken<List<TableDataList>>() {
                                }.getType());

                                if (tableDataList!=null && tableDataList.size()>0){
                                    UtilView.setupTableListAdapter(activity, spinnerTableName, tableDataList);
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
                addChairs();
                break;
            case R.id.btnClose:
                finish();
                break;
        }
    }

    private void addChairs() {
        String noofchairs = edNoChairs.getText().toString().trim();


        if (!noofchairs.equals("")) {
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {

                UtilView.showProgessBar(activity, progressBar);
                AddChairsData addChairsData = new AddChairsData();
                if (callingFor.equals(Constant.CALL_EDITTABLE)) {
                    addChairsData.setChairsId(chairsDetails.getChairsId());
                }
                if (tableId!=null){
                    addChairsData.setTable(tableId);
                }

                if (configureName!=null){
                    addChairsData.setConfig(configureName);
                }

                addChairsData.setChairNames(chairList);


                final Gson gson = new Gson();
                String itemJson = gson.toJson(addChairsData);
                String url = "";
                if (url != null) {
                    url = serverUrl + "/hipos//1/" + Constant.FUNTION_SAVECHAIR;
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
                                UtilView.showToast(activity,  "Chairs doesn't create successfully.");
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
        }
    }
}
