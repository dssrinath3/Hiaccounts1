package in.hiaccounts.hinext.controlpanel.fragment.companysetup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.controlpanel.model.company_setup.compnayinformation.CompanyData;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 2/9/2017.
 */

public class Fragment_FinancialYear extends Fragment {


    public static String TAG = Fragment_FinancialYear.class.getSimpleName();
    @BindView(R.id.edClosingdate)
    EditText edClosingdate;
    @BindView(R.id.edClosingmethod)
    EditText edClosingmethod;
    @BindView(R.id.edStartingPeriod)
    EditText edStartingPeriod;
    @BindView(R.id.edCurrentYrFrom)
    EditText edCurrentYrFrom;
    @BindView(R.id.edCurrentYrTo)
    EditText edCurrentYrTo;
    @BindView(R.id.edClosingPeriod)
    EditText edClosingPeriod;
    @BindView(R.id.edExtension)
    EditText edExtension;
    @BindView(R.id.edExtensionPeriod)
    EditText edExtensionPeriod;
    @BindView(R.id.spinnerGstPeriod)
    Spinner spinnerGstPeriod;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    Unbinder unbinder;

    private ServiceHandler serviceHandler;
    private Activity mActivity;
    private Boolean isInternetPresent = false;
    private CompanyData companyData;
    private String serverurl;


    public static Fragment_FinancialYear newInstance() {
        Fragment_FinancialYear fragment = new Fragment_FinancialYear();
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
        View view = inflater.inflate(R.layout.fragment_csetupfinancialyear, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponents(view);
        return view;
    }

    private void initComponents(View view) {
        serviceHandler = new ServiceHandler(mActivity);
        serverurl= UtilView.getUrl(mActivity);
        getCompanyFromServer();
    }

    private void getCompanyFromServer() {

        String url = serverurl + Constant.FUNTION_GETCOMPANYDATA;

        if (serverurl!=null) {
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
                                    companyData = gson.fromJson(jsonObject.toString(), CompanyData.class);

                                    setUpView(companyData);

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
        }else {
            UtilView.gotToLogin(mActivity);
        }
    }

    private void setUpView(CompanyData companyData) {

        this.companyData = companyData;

        if (companyData != null) {

            if (companyData.getYearclosing() != null)
                edClosingdate.setText(companyData.getYearclosing());

            if (companyData.getStartperiod() != null)
                edStartingPeriod.setText(companyData.getStartperiod());

            if (companyData.getStartyear() != null)
                edCurrentYrFrom.setText(companyData.getStartyear());

            if (companyData.getEndyear() != null)
                edCurrentYrTo.setText(companyData.getEndyear());

            if (companyData.getClosingperiod() != null)
                edClosingPeriod.setText(companyData.getClosingperiod());

            if (companyData.getClosingMethod() != null) {
                if (companyData.getClosingMethod().equals("yearCl")) {
                    edClosingmethod.setText("Yearly");
                } else if (companyData.getClosingMethod().equals("halfCl")) {
                    edClosingmethod.setText("Half Yearly");
                } else {
                    edClosingmethod.setText(companyData.getClosingMethod());
                }

            }


            if (companyData.getYearclosing() != null)
                edExtension.setText(companyData.getYearclosing());

            if (companyData.getStartperiod() != null)
                edStartingPeriod.setText(companyData.getStartperiod());

            UtilView.setupAdapter(mActivity, spinnerGstPeriod, getResources().getStringArray(R.array.fy_gstperiod));
        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
