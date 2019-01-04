package in.hiaccounts.hinext.company_setup.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.hiaccounts.R;
import in.hiaccounts.hinext.company_setup.activity.Activity_CompanySubcription;
import in.hiaccounts.hinext.company_setup.model.SubscriptionData;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;

import static in.hiaccounts.R.string.response_error;

/**
 * Created by Prateek on 6/20/2017.
 */

public class Fragment_CompanySubscription extends Fragment {

    public static final String TAG = Fragment_CompanySubscription.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edValidity)
    EditText edValidity;
    @BindView(R.id.edBusinessname)
    EditText edBusinessname;
    @BindView(R.id.edNumberofuser)
    EditText edNumberofuser;
    @BindView(R.id.edPractitionerName)
    EditText edPractitionerName;
    @BindView(R.id.edHiConnect)
    EditText edHiConnect;
    @BindView(R.id.edHiSync)
    EditText edHiSync;
    @BindView(R.id.tvNext)
    TextView tvNext;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    Unbinder unbinder;

    private String serverUrl = "";
    private Activity mActivity;
    private ServiceHandler serviceHandler;
    private SharedPreference sharedPreference;
    private Boolean isInternetPresent = false;

    // call when instace of Fra_GeneralInfo is created.
    public static Fragment_CompanySubscription newInstance() {
        Fragment_CompanySubscription fragment = new Fragment_CompanySubscription();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity= (Activity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.compnaysubcription_subcription, container, false);
        unbinder = ButterKnife.bind(this, view);

        sharedPreference=SharedPreference.getInstance(mActivity);
        serverUrl = UtilView.getUrl(mActivity);
        serviceHandler=new ServiceHandler(mActivity);
        getSubscriptionDetails();
        return view;

    }

    private void getSubscriptionDetails() {
        String url = serverUrl + Constant.FUNTION_GETCOMPANYSUBSCRIPTIONDETAILS;
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
                UtilView.showProgessBar(mActivity, progressBar);
                PostDataTask postDataTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(mActivity, progressBar);
                        if (result != null) {
                            if (result.toString().equals(getResources().getString(R.string.response200_sessionLost))) {
                                UtilView.gotToLogin(mActivity);
                            } else {
                                try {
                                    JSONObject jsonObject = new JSONObject(result.toString());
                                    Gson gson = new Gson();
                                    SubscriptionData subscriptionData=gson.fromJson(jsonObject.toString(),SubscriptionData.class);
                                    setUpView(subscriptionData);


                                } catch (Exception e) {
                                    UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                                }


                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                        }
                    }
                }, false);
                postDataTask.execute(new Gson().toString(),url, "");
            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
            }
        } else {
            UtilView.gotToLogin(mActivity);
        }

    }

    private void setUpView(SubscriptionData subscriptionData) {


        if (subscriptionData!=null){

            if (subscriptionData.getValidity()!=null){
                edValidity.setText(subscriptionData.getValidity());
            }

            if (subscriptionData.getBussinessName()!=null){
                edBusinessname.setText(subscriptionData.getBussinessName());
            }

            if (subscriptionData.getNumberOfUsers()!=null)
             edNumberofuser.setText(""+subscriptionData.getNumberOfUsers());

            if (subscriptionData.getPractitionerName()!=null){
                edPractitionerName.setText(subscriptionData.getPractitionerName());
            }
            if (subscriptionData.getHiConnect()!=null){
                edHiConnect.setText(subscriptionData.getHiConnect());
            }
            if (subscriptionData.getHiSync()!=null){
                edHiSync.setText(subscriptionData.getHiSync());
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tvNext})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.tvNext:
                //http://localhost:8090/company/subscriptionDone

                doneSubscription();


                break;
        }
    }

    private void doneSubscription() {
            String url = serverUrl + Constant.FUNTION_DONECOMPANYSUBSCRIPTION;
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (serverUrl != null) {
                if (isInternetPresent) {
                    UtilView.showProgessBar(mActivity, progressBar);
                    GetDataTask getDataTask = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                        @Override
                        public void onTaskComplete(String result) {
                            UtilView.hideProgessBar(mActivity, progressBar);
                            if (result != null) {
                                if (result.toString().equals(getResources().getString(R.string.response200_sessionLost))) {
                                    UtilView.gotToLogin(mActivity);
                                } else {
                                    try {
                                        JSONObject jsonObject = new JSONObject(result.toString());
                                        String message = jsonObject.getString("message");
                                        if (message.equals("success")) {
                                            sharedPreference.saveData(Constant.NAVIGATION_REDIRECTPAGE, Constant.PAGE_SETUP);
                                            ((Activity_CompanySubcription) getActivity()).showCompanyIntroduction();
                                        }
                                    }
                                    catch (JSONException e){

                                        UtilView.showToast(mActivity,"Subscription is not done. Please try again");
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
}
