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
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.state.AddStateDatum;
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.state.CountryId;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

public class Activity_Config_State extends AppCompatActivity implements TextWatcher {
    public static String TAG = Activity_Config_State.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.spinnerCountry)
    Spinner spinnerCountry;
    @BindView(R.id.llcountry)
    LinearLayout llcountry;
    @BindView(R.id.textView6)
    TextView textView6;
    @BindView(R.id.edState)
    EditText edState;
    @BindView(R.id.llstate)
    LinearLayout llstate;
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
    String callingFor,serverUrl,status;
    private AddStateDatum stateDetails;
    private CountryId countryListData  = null;
    private List<CountryId> countryIdList;
    private List<Object> objectContryDataList = new ArrayList<>();
    private Long countryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_state);
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
                if (callingFor.equals(Constant.CALL_EDITSTATE)) {
                    stateDetails = (AddStateDatum) intent.getSerializableExtra("stateData");

                    if (stateDetails != null) {
                        if (stateDetails.getStateName() != null)
                            edState.setText(stateDetails.getStateName());

                        if (stateDetails.getCountryId() != null) {



                            List<CountryId> list = new ArrayList<>();
                            list.add(stateDetails.getCountryId());

                            objectContryDataList.clear();
                            objectContryDataList.addAll(list);
                            UtilView.setupItemAdapter(activity, spinnerCountry, objectContryDataList);

                            for (int i = 0;i<list.size() ;i++){
                                if (stateDetails.getCountryId().getCountryId()!= null ) {

                                    spinnerCountry.setSelection(i);
                                    countryId = stateDetails.getCountryId().getCountryId();
                                }
                            }


                        }

                        if (stateDetails.getStatus() !=null){
                            if (stateDetails.getStatus().equals("Active"))
                            {
                                spinnerStatus.setSelection(0);
                            }
                            if (stateDetails.getStatus().equals("InActive"))
                            {
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

        getCountryDetails();

        edState.addTextChangedListener(this);

        spinnerStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = objectContryDataList.get(i);
                if (obj instanceof CountryId) {
                    spinnerCountry.setSelection(i);
                    countryListData = (CountryId) spinnerCountry.getSelectedItem();
                    countryId = countryListData.getCountryId();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }


    private void getCountryDetails() {
        String url = "";

        if (callingFor != null) {
            url = serverUrl + "/hipos//1/" + Constant.FUNTION_GETCONTRYLIST;
        }


        if (serverUrl != null) {
            isInternatePrsenet = serviceHandler.isConnectingToInternet();
            if (isInternatePrsenet) {
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
                                    countryIdList = new ArrayList<CountryId>();
                                    JSONArray resultJsonArray = new JSONArray(result.toString());
                                    for (int i = 0; i < resultJsonArray.length(); i++) {
                                        JSONObject uomJson = resultJsonArray.getJSONObject(i);

                                        Gson gson = new Gson();
                                        CountryId sdata = gson.fromJson(uomJson.toString(), CountryId.class);
                                        countryIdList.add(sdata);

                                    }
                                    if (countryIdList != null && countryIdList.size() > 0) {
                                        objectContryDataList.clear();
                                        objectContryDataList.add("Select");
                                        objectContryDataList.addAll(countryIdList);
                                        UtilView.setupItemAdapter(activity, spinnerCountry, objectContryDataList);


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
            if (!isInternatePrsenet) {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
            }
        } else {
            UtilView.gotToLogin(activity);
        }
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
                addstate();
                break;
            case R.id.btnClose:
                finish();
                break;
        }
    }

    private void addstate() {
        final String statename = edState.getText().toString().trim();



        if (!statename.equals("")) {
            isInternatePrsenet = serviceHandler.isConnectingToInternet();
            if (isInternatePrsenet) {

                UtilView.showProgessBar(activity, progressBar);
                AddStateDatum addStateDatum = new AddStateDatum();
                if (callingFor.equals(Constant.CALL_EDITSTATE)) {
                    addStateDatum.setId(stateDetails.getId());
                     countryId = countryListData.getCountryId();
                }


                addStateDatum.setCountryid(countryId);
                addStateDatum.setStateName(statename);

                addStateDatum.setStatus((String) spinnerStatus.getSelectedItem());


                final Gson gson = new Gson();
                String itemJson = gson.toJson(addStateDatum);
                String url = "";
                if (url != null) {
                    url = serverUrl + "/hipos//1/" + Constant.FUNTION_ADDSTATE;
                }
                if (serverUrl != null) {
                    PostDataTask postDataTask = new PostDataTask(activity, new AsyncTaskCompleteListener<String>() {
                        @Override
                        public void onTaskComplete(String result) {
                            UtilView.hideProgessBar(activity, progressBar);
                            if (result != null) {
                                try {
                                    List<AddStateDatum> itemsLists=new ArrayList<>();

                                    AddStateDatum item=gson.fromJson(result.toString(),AddStateDatum.class);
                                    itemsLists.add(item);
                                    UtilView.showToast(activity, statename + "create successfully.");
                                }catch (Exception e){

                                    UtilView.showErrorDialog(getResources().getString(R.string.response_error),activity);
                                }

                                Intent returnIntent = new Intent();
                                activity.setResult(Activity.RESULT_OK, returnIntent);
                                activity.finish();


                            } else {
                                UtilView.showToast(activity, statename + " doesn't create successfully.");
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
            if (statename == null || statename.equals("")) {
                edState.setError(getResources().getString(R.string.err_msg));
            }


        }
    }

}
