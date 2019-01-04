package in.hiaccounts.hinext.controlpanel.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

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
import in.hiaccounts.hinext.controlpanel.adapter.SearchHsnAdapter;
import in.hiaccounts.hinext.inventory.model.hsncode.SearchHsnCode;
import in.hiaccounts.hinext.sales.model.sales_pagedata.HinextSalesPageData;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;

import static in.hiaccounts.R.string.response_error;

/**
 * Created by Prateek on 8/24/2017.
 */

public class Activity_CoSetup_SearchHsn extends AppCompatActivity {


    public static boolean[] thumbnailsselection;
    public final String TAG = Activity_CoSetup_SearchHsn.class.getSimpleName();
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.edSearch)
    EditText edSearch;
    @BindView(R.id.llSearch)
    LinearLayout llSearch;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.btnClose)
    Button btnClose;
    Activity_CoSetup_SearchHsn mActivity;
    Boolean isInternetPresent = false;
    List<SearchHsnCode> searchHsnCodeList = new ArrayList<>();
    SearchHsnAdapter searchHsnAdapter;
    String serverUrl;
    SharedPreference sharedPreference;
    private ServiceHandler serviceHandler;
    private HinextSalesPageData pageData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csetup_serachhsn);
        initComponentView();
    }

    private void initComponentView() {
        ButterKnife.bind(this);
        mActivity = this;
        serviceHandler = new ServiceHandler(mActivity);
        serverUrl = UtilView.getUrl(mActivity);
        sharedPreference = SharedPreference.getInstance(mActivity);
        getPageLoadData();
        getHSNCODeDetailsFromServer();

        LayoutInflater inflater = mActivity.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.controlpanel_adapter_searchhsn, null, false);



        if (listView != null)
            listView.addHeaderView(header);
    }

    @OnClick({R.id.llSearch, R.id.btnSave, R.id.btnClose})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llSearch:
                UtilView.hideSoftKeyboard(mActivity, edSearch);

                break;
            case R.id.btnSave:
                callSaveApi();
                break;
            case R.id.btnClose:
                finish();
                break;
        }
    }

    private void callSaveApi() {


        final ArrayList<SearchHsnCode> selectSearchCode = new ArrayList<SearchHsnCode>();
        boolean noSelect = false;

        if (thumbnailsselection != null && thumbnailsselection.length > 0) {
            selectSearchCode.clear();

            for (int i = 0; i < thumbnailsselection.length; i++) {
                if (thumbnailsselection[i] == true) {
                    noSelect = true;

                    selectSearchCode.add(searchHsnCodeList.get(i));

                }
            }
            if (!noSelect) {
                Toast.makeText(mActivity, "Please Select HSN Code!", Toast.LENGTH_SHORT).show();
            } else {

                UtilView.showProgessBar(mActivity, progressBar);
                isInternetPresent = serviceHandler.isConnectingToInternet();
                if (serverUrl != null) {
                    if (isInternetPresent) {
                        String url = serverUrl + Constant.FUNTION_ADDHSNFROMFILE;

                        PostDataTask postDataTAsk = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                            @Override
                            public void onTaskComplete(String result) {
                                UtilView.hideProgessBar(mActivity, progressBar);

                                if (result != null) {
                                    if (result.equals("invalid")) {
                                        UtilView.showToast(mActivity, getResources().getString(R.string.accesstoken_error));
                                        Intent intent = new Intent(mActivity, Activity_Login.class);
                                        mActivity.startActivity(intent);
                                        mActivity.finish();
                                    } else {

                                        try {
                                            JSONArray jsonArray = new JSONArray(result.toString());
                                            if (jsonArray.length() > 0) {
                                                Intent returnIntent = new Intent();
                                                setResult(Activity.RESULT_OK, returnIntent);
                                                finish();

                                            }
                                        } catch (JSONException je) {
                                            UtilView.showLogCat(TAG, "json exception " + je.getMessage());
                                        }


                                    }
                                } else {
                                    progressBar.setVisibility(View.GONE);
                                    UtilView.showErrorDialog(getResources().getString(R.string.error_null), mActivity);
                                }
                            }
                        }, false);
                        postDataTAsk.execute(new Gson().toJson(selectSearchCode), url, "");


                    } else {
                        UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                    }
                } else {
                    UtilView.gotToLogin(mActivity);
                }

            }


        }


    }

    private void getPageLoadData() {
        String url = serverUrl + "/hipos//0/" + Constant.FUNCTION_GETPAGELOADDATA;
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
                UtilView.showProgessBar(mActivity, progressBar);
                GetDataTask getDataTask = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {

                        if (result != null) {
                            if (result.toString().equals(getResources().getString(R.string.response200_sessionLost))) {
                                UtilView.gotToLogin(mActivity);
                            } else {
                                sharedPreference.saveData(Constant.HINEXTSALESPAGEDATA_KEY, result.toString());

                                Gson gson = new Gson();
                                try {
                                    pageData = gson.fromJson(result.toString(), HinextSalesPageData.class);

                                } catch (Exception e) {
                                    UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                                }
                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                        }
                    }
                }, false);
                getDataTask.execute(url, "");
            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
            }
        } else {
            UtilView.gotToLogin(mActivity);
        }
    }

    private void getHSNCODeDetailsFromServer() {
        String url = null;
        String companyData = sharedPreference.getData(Constant.HINEXTSALESPAGEDATA_KEY);
        Gson gson = new Gson();
        try {
            if(companyData !=null){
                pageData = gson.fromJson(companyData, HinextSalesPageData.class);

                if(pageData.getCountryId()!= null){
                    UtilView.showProgessBar(mActivity, progressBar);
                     url = serverUrl +"/hipos//1/"+ Constant.FUNTION_GETHSNCODESFROMFILE+"?countryId="+pageData.getCountryId();
                }
            }


        } catch (Exception e) {
            UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
        }

        

        if (serverUrl != null) {
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {


                GetDataTask task = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(mActivity, progressBar);

                        if (result != null) {
                            if (result.equals("invalid")) {
                                UtilView.showToast(mActivity, getResources().getString(R.string.accesstoken_error));
                                Intent intent = new Intent(mActivity, Activity_Login.class);
                                mActivity.startActivity(intent);
                                mActivity.finish();
                            } else {

                                Gson gson = new Gson();


                                try {
                                    if (searchHsnCodeList != null && searchHsnCodeList.size() > 0) {
                                        searchHsnCodeList.clear();
                                    }
                                    JSONArray jsonArray = new JSONArray(result.toString());
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                                        SearchHsnCode searchHsnCode = gson.fromJson(jsonObject.toString(), SearchHsnCode.class);
                                        searchHsnCodeList.add(searchHsnCode);
                                    }
                                    if (searchHsnCodeList.size() > 0) {
                                        thumbnailsselection = new boolean[searchHsnCodeList.size()];
                                        searchHsnAdapter = new SearchHsnAdapter(mActivity, searchHsnCodeList);
                                        listView.setAdapter(searchHsnAdapter);
                                        searchHsnAdapter.notifyDataSetChanged();
                                    } else {

                                        searchHsnCodeList.clear();
                                        searchHsnAdapter = new SearchHsnAdapter(mActivity, searchHsnCodeList);
                                        listView.setAdapter(searchHsnAdapter);
                                        searchHsnAdapter.notifyDataSetChanged();
                                        UtilView.showSuccessDialog(getResources().getString(R.string.category_notavailbale), mActivity);

                                    }


                                } catch (Exception e) {
                                    UtilView.showLogCat(TAG, "TaskCompleteListener Exception " + e.toString());
                                }
                            }
                        } else {
                            progressBar.setVisibility(View.GONE);
                            UtilView.showErrorDialog(getResources().getString(R.string.error_null), mActivity);
                        }
                    }
                }, false);
                task.execute(url, "");
            }
            if (!isInternetPresent) {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
            }
        } else {
            UtilView.gotToLogin(mActivity);
        }


    }

}
