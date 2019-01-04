package in.hiaccounts.hinext.controlpanel.fragment.companysetup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;

import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.controlpanel.model.company_setup.chartsofaccount.BalanceSheetAccnt;
import in.hiaccounts.hinext.controlpanel.model.company_setup.chartsofaccount.ChartsListData;
import in.hiaccounts.hinext.controlpanel.model.company_setup.chartsofaccount.ProfitLossAccnt;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 2/9/2017.
 */

public class Fragment_ChartsOfAccount extends Fragment {


    public static String TAG = Fragment_ChartsOfAccount.class.getSimpleName();
    @BindView(R.id.edClassification)
    EditText edClassification;
    @BindView(R.id.edBusinessType)
    EditText edBusinessType;
    @BindView(R.id.framlayoutFragment)
    FrameLayout framlayoutFragment;
    Unbinder unbinder;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.llContent)
    LinearLayout llContent;
    @BindView(R.id.tvBalanceSheet)
    TextView tvBalanceSheet;
    @BindView(R.id.tvProfitLoss)
    TextView tvProfitLoss;

    private ServiceHandler serviceHandler;
    private Activity mActivity;
    private Boolean isInternetPresent = false;
    ChartsListData chartLsitData;

    public static List<ProfitLossAccnt> profitLossAccntsList;
    public static List<BalanceSheetAccnt> balanceSheetAccntList;
    private String serverUrl;

    public static Fragment_ChartsOfAccount newInstance() {
        Fragment_ChartsOfAccount fragment = new Fragment_ChartsOfAccount();
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
        View view = inflater.inflate(R.layout.fragment_csetupchatofaccounts, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponentView();
        return view;
    }

    private void initComponentView() {
        serviceHandler = new ServiceHandler(mActivity);
        serverUrl = UtilView.getUrl(mActivity);
        getChartListFromServer();
    }

    private void getChartListFromServer() {

        String url = serverUrl + Constant.FUNTION_GETCHARTS;

        if (serverUrl != null) {
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
                                    JSONObject jsonObject = new JSONObject(result.toString());
                                    Gson gson = new Gson();
                                    chartLsitData = gson.fromJson(jsonObject.toString(), ChartsListData.class);
                                    if (chartLsitData != null) {
                                        if (llContent != null)
                                            llContent.setVisibility(View.VISIBLE);
                                        if (chartLsitData.getBusinessTypeName() != null)
                                            edBusinessType.setText(chartLsitData.getBusinessTypeName());

                                        if (chartLsitData.getIndustryName() != null)
                                            edClassification.setText(chartLsitData.getIndustryName());

                                        if (chartLsitData.getBalanceSheetAccnts() != null) {
                                            addBalanceSheetFragment();
                                        }
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
            }
            if (!isInternetPresent) {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
            }
        } else {
            UtilView.gotToLogin(mActivity);
        }
    }

    private void addBalanceSheetFragment() {


        if (tvBalanceSheet != null) {
            tvBalanceSheet.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            tvBalanceSheet.setTextColor(getResources().getColor(R.color.colorBlack));
        }
        if (tvProfitLoss != null) {
            tvProfitLoss.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            tvProfitLoss.setTextColor(getResources().getColor(R.color.colorWhite));
        }

        Fragment balanceSheetFragment = new Fragment_MasterBalanceSheet();
        balanceSheetAccntList = chartLsitData.getBalanceSheetAccnts();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.framlayoutFragment, balanceSheetFragment).commit();
    }

    private void addProfiLossFragment() {

        if (tvProfitLoss != null) {
            tvProfitLoss.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            tvProfitLoss.setTextColor(getResources().getColor(R.color.colorBlack));
        }

        if (tvBalanceSheet != null) {
            tvBalanceSheet.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            tvBalanceSheet.setTextColor(getResources().getColor(R.color.colorWhite));
        }
        Fragment profitLossFragment = new Fragment_ProfitLossSheet();
        profitLossAccntsList = chartLsitData.getProfitLossAccnts();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.framlayoutFragment, profitLossFragment).commit();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R.id.tvBalanceSheet, R.id.tvProfitLoss})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvBalanceSheet:
                if (chartLsitData != null) {
                    if (chartLsitData.getBalanceSheetAccnts() != null) {
                        addBalanceSheetFragment();
                    }
                }
                break;
            case R.id.tvProfitLoss:
                if (chartLsitData != null) {
                    if (chartLsitData.getProfitLossAccnts() != null) {
                        addProfiLossFragment();
                    }
                }

                break;
        }
    }
}
