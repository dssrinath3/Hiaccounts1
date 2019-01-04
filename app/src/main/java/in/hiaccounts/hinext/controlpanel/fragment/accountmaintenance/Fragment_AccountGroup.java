package in.hiaccounts.hinext.controlpanel.fragment.accountmaintenance;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.controlpanel.adapter.ChartListAdapter;
import in.hiaccounts.hinext.controlpanel.model.accountmaintance.AccountData;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 2/9/2017.
 */

public class Fragment_AccountGroup extends Fragment {


    public static String TAG = Fragment_AccountGroup.class.getSimpleName();
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.listView)
    ListView listView;
    Unbinder unbinder;
    private ServiceHandler serviceHandler;
    private Activity mActivity;
    private Boolean isInternetPresent = false;
    ChartListAdapter chartListAdapter;
    List<AccountData> accountDataList = new ArrayList<>();
    private String serverUrl;

    public static Fragment_AccountGroup newInstance() {
        Fragment_AccountGroup fragment = new Fragment_AccountGroup();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes
        View view = inflater.inflate(R.layout.fragment_actmainactgrp, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponentView(view);
        return view;
    }

    private void initComponentView(View view) {

        serviceHandler = new ServiceHandler(mActivity);
        serverUrl = UtilView.getUrl(mActivity);
        if (serverUrl != null) {
            getAccountListFromServer();
        } else {
            UtilView.gotToLogin(mActivity);
        }


    }

    private void getAccountListFromServer() {

        String url = serverUrl + "/hipos//0" + Constant.FUNTION_GETACCOUNTGROUPLIST;

        if (!url.equals("")) {
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {
                // prepare the Request
                UtilView.showProgessBar(mActivity, progressBar);
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
                                try {
                                    if (accountDataList != null && accountDataList.size() > 0) {
                                        accountDataList.clear();
                                    }

                                    JSONArray jsonArray = new JSONArray(result.toString());
                                    if (jsonArray.length() > 0) {
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                                            Gson gson = new Gson();
                                            AccountData accountData = gson.fromJson(jsonObject.toString(), AccountData.class);
                                            accountDataList.add(accountData);
                                        }
                                    }

                                    if (accountDataList.size() > 0) {
                                        List<Object> objectList = new ArrayList<>();

                                        objectList.addAll(accountDataList);

                                        LayoutInflater inflater = mActivity.getLayoutInflater();
                                        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.companysetup_adapter_profitloss, null, false);

                                        TextView tvActCode = header.findViewById(R.id.tvActCode);
                                        TextView tvActName = header.findViewById(R.id.tvActName);
                                        TextView tvActClass = header.findViewById(R.id.tvActClass);
                                        TextView tvActGroup = header.findViewById(R.id.tvActGroup);


                                        tvActCode.setText("Account Code");
                                        tvActName.setText("Account Name");
                                        tvActClass.setText("Account Class");
                                        tvActGroup.setText("Status");

                                        UtilView.setTextAppearanceSmall(mActivity, tvActCode);
                                        UtilView.setTextAppearanceSmall(mActivity, tvActName);
                                        UtilView.setTextAppearanceSmall(mActivity, tvActClass);
                                        UtilView.setTextAppearanceSmall(mActivity, tvActGroup);


                                        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvActCode);
                                        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvActName);
                                        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvActClass);
                                        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvActGroup);


                                        if (listView != null)
                                            listView.addHeaderView(header);

                                        chartListAdapter = new ChartListAdapter(mActivity, objectList);
                                        listView.setAdapter(chartListAdapter);
                                        chartListAdapter.notifyDataSetChanged();
                                    } else {
                                        UtilView.showToast(mActivity, getResources().getString(R.string.noaccount_available));
                                    }

                                } catch (Exception e) {
                                    UtilView.showLogCat(TAG, "TaskCompleteListener Exception " + e.toString());
                                }
                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.error_null), mActivity);
                        }
                    }
                }, false);
                task.execute(url, "");
                //task.execute(url, "");
            }
            if (!isInternetPresent) {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
            }
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
