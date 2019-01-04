package in.hiaccounts.hinext.application.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;

import org.json.JSONObject;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.application.adapter.ViewPagerAdapter;
import in.hiaccounts.hinext.application.model.DashBoardData;
import in.hiaccounts.hinext.application.model.PageLoadDataForAll;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;

import static in.hiaccounts.R.string.response_error;

/**
 * Created by Prateek on 2/9/2017.
 */

public class Fragment_DashBoard extends Fragment {

    public static final String TAG = Fragment_DashBoard.class.getSimpleName();

    @BindView(R.id.tvCurrentTime)
    TextView tvCurrentTime;
    @BindView(R.id.tvCurrentDate)
    TextView tvCurrentDate;
    @BindView(R.id.tvCashInHand)
    TextView tvCashInHand;
    @BindView(R.id.tvCashInBank)
    TextView tvCashInBank;
    @BindView(R.id.tvTotalReceivable)
    TextView tvTotalReceivable;
    @BindView(R.id.tcTotalPayable)
    TextView tcTotalPayable;
    Unbinder unbinder;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.llContent)
    LinearLayout llContent;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    Boolean isInternetPresent = false;
    DashBoardData dashBoardData;
    @BindView(R.id.cardDateAndTime)
    CardView cardDateAndTime;
    @BindView(R.id.cardCashInHand)
    CardView cardCashInHand;
    @BindView(R.id.cardCashInBank)
    CardView cardCashInBank;
    @BindView(R.id.cardTotalRecievable)
    CardView cardTotalRecievable;
    @BindView(R.id.cardTotalPayable)
    CardView cardTotalPayable;
    private Activity mActivity;
    private ServiceHandler serviceHandler;
    private SharedPreference sharedPreference;
    private String serverUrl;
    private PageLoadDataForAll pageData;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;

    }

    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponents(view);
        return view;
    }

    private void initComponents(View view) {
        setHasOptionsMenu(true);
        serviceHandler = new ServiceHandler(mActivity);
        sharedPreference = SharedPreference.getInstance(mActivity);
        serverUrl = UtilView.getUrl(mActivity);
        if (serverUrl != null) {
            getPageLoadDataForAll();
            getPageDataFromServer();


        } else {
            UtilView.gotToLogin(mActivity);
        }


    }

    private void getPageDataFromServer() {

        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (isInternetPresent) {

            String url = serverUrl + Constant.FUNTION_GETDASHBOARDDATA + "?locationId=";
            UtilView.showProgessBar(mActivity, progressBar);
            PostDataTask task = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
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
                                dashBoardData = gson.fromJson(jsonObject.toString(), DashBoardData.class);
                                if (dashBoardData != null) {
                                    sharedPreference.saveData(Constant.DASHBOARD_KEY, result.toString());
                                    setUpView();
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
            task.execute(new Gson().toString(), url, "");
        } else {
            UtilView.gotToLogin(mActivity);
        }
    }

    private void setUpView() {
        if (llContent != null)
            llContent.setVisibility(View.VISIBLE);
        String getPageLoadData = sharedPreference.getData(Constant.GETPAGELOADDATA_KEY);
        if (getPageLoadData != null) {

            Gson gson = new Gson();
            pageData = gson.fromJson(getPageLoadData, PageLoadDataForAll.class);
            if (pageData != null) {
                if (pageData.getUserAccessRights().getDashboardDateTime() == true) {

                    if (cardDateAndTime!=null)
                        cardDateAndTime.setVisibility(View.VISIBLE);
                }
                if (pageData.getUserAccessRights().getDashboardCashInHand() == true) {
                    if (tvCashInHand != null)
                        tvCashInHand.setVisibility(View.VISIBLE);
                    tvCashInHand.setText(String.format("%.2f", dashBoardData.getCashInHand()));

                    if (cardCashInHand!=null)
                        cardCashInHand.setVisibility(View.VISIBLE);
                }

                if (pageData.getUserAccessRights().getDashboardCashInBank() == true) {
                    if (tvCashInBank != null)
                        tvCashInBank.setVisibility(View.VISIBLE);
                        tvCashInBank.setText(String.format("%.2f", dashBoardData.getCashInBank()));

                    if (cardCashInBank!=null)
                        cardCashInBank.setVisibility(View.VISIBLE);
                }
                if (pageData.getUserAccessRights().getDashboardTotalPayable() == true) {
                    if (tcTotalPayable != null)
                        tcTotalPayable.setVisibility(View.VISIBLE);
                        tcTotalPayable.setText(String.format("%.2f", dashBoardData.getTotalPayable()));

                    if (cardTotalPayable!=null)
                        cardTotalPayable.setVisibility(View.VISIBLE);
                }
                if (pageData.getUserAccessRights().getDashboardTotalReceivable() == true) {
                    if (tvTotalReceivable != null)
                        tvTotalReceivable.setVisibility(View.VISIBLE);
                        tvTotalReceivable.setText(String.format("%.2f", dashBoardData.getTotalRecievable()));

                    if (cardTotalRecievable!=null)
                        cardTotalRecievable.setVisibility(View.VISIBLE);
                }


            }

        }

        setDate();
        setTimer();


        final boolean run = true; //set it to false if you want to stop the timer
        final Handler mHandler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (run) {
                    try {
                        Thread.sleep(1000);
                        mHandler.post(new Runnable() {

                            @Override
                            public void run() {
                                setTimer();
                            }
                        });
                    } catch (Exception e) {
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (run) {
                    try {
                        Thread.sleep(1000);
                        mHandler.post(new Runnable() {

                            @Override
                            public void run() {
                                setDate();

                            }


                        });
                    } catch (Exception e) {
                    }
                }
            }
        }).start();

      /*  if (viewPager != null) {
            setupViewPager(viewPager);
            tabLayout.setupWithViewPager(viewPager);
        }*/

    }

    private void getPageLoadDataForAll() {

        String url = serverUrl + "/hipos//1/" + Constant.FUNCTION_GETPAGELOADDATA;
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
                UtilView.showProgessBar(mActivity, progressBar);
                GetDataTask getDataTask = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(mActivity, progressBar);
                        if (result != null) {
                            try {

                                sharedPreference.saveData(Constant.GETPAGELOADDATA_KEY, result.toString());
                                Gson gson = new Gson();
                                PageLoadDataForAll pageLoadDataForAll = gson.fromJson(result.toString(), PageLoadDataForAll.class);

                            } catch (Exception e) {
                                UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                        }
                    }
                }, false);
                getDataTask.execute(url, "");
            } else {
                UtilView.showErrorDialog(mActivity.getResources().getString(R.string.intertnet_status), mActivity);
            }
        } else {
            UtilView.gotToLogin(mActivity);
        }
    }

    private void setupViewPager(ViewPager viewPager) {

        final ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        adapter.addFragment(new Fragment_DashboardSalesChart(), getResources().getString(R.string.salesChart));
        adapter.addFragment(new Fragment_DashboardPurchaseChart(), getResources().getString(R.string.purchaseChart));
        viewPager.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }

    private void setDate() {
        Calendar calendar = Calendar.getInstance();
        int monthdate = calendar.get(Calendar.DAY_OF_MONTH);
        int year = calendar.get(Calendar.YEAR);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int month = calendar.get(Calendar.MONTH);
        String dayName = "";
        String monthName = "";

        switch (dayOfWeek) {

            case 1:
                dayName = "Sun";
                break;

            case 2:
                dayName = "Mon";
                break;

            case 3:
                dayName = "Tue";
                break;

            case 4:
                dayName = "Wed";
                break;

            case 5:
                dayName = "Thu";
                break;

            case 6:
                dayName = "Fri";
                break;

            case 7:
                dayName = "Sat";
                break;
        }

        switch (month) {
            case 0:
                monthName = "Jan";
                break;

            case 1:
                monthName = "Feb";
                break;

            case 2:
                monthName = "Mar";
                break;

            case 3:
                monthName = "Apr";
                break;

            case 4:
                monthName = "May";
                break;

            case 5:
                monthName = "Jun";
                break;

            case 6:
                monthName = "Jul";
                break;

            case 7:
                monthName = "Aug";
                break;
            case 8:
                monthName = "Sep";
                break;
            case 9:
                monthName = "Oct";
                break;
            case 10:
                monthName = "Nov";
                break;
            case 11:
                monthName = "Dec";
                break;

        }
        if (tvCurrentDate != null)
            tvCurrentDate.setText(String.valueOf(dayName) + " " + String.valueOf(monthName) + " " + String.valueOf(monthdate) + " " + String.valueOf(year));


    }

    private void setTimer() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        int am_pm = calendar.get(Calendar.AM_PM);
        if (am_pm == 0) {
            if (tvCurrentTime != null)
                tvCurrentTime.setText(String.valueOf(hour) + ":" + String.valueOf(minute) + ":" + String.valueOf(second) + " " + "AM");
        }
        if (am_pm == 1) {
            if (tvCurrentTime != null)
                tvCurrentTime.setText(String.valueOf(hour) + ":" + String.valueOf(minute) + ":" + String.valueOf(second) + " " + "PM");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
