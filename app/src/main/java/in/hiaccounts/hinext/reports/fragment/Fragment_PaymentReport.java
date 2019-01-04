package in.hiaccounts.hinext.reports.fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.hiaccounts.R;

import in.hiaccounts.hinext.reports.adapter.PaymentReport_Adapter;
import in.hiaccounts.model.paymentreport.PaymentReport;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 2/9/2017.
 */

public class Fragment_PaymentReport extends Fragment {


    public static String TAG = Fragment_PaymentReport.class.getSimpleName();
    @BindView(R.id.tvFormDate)
    TextView tvFormDate;
    @BindView(R.id.tvToDate)
    TextView tvToDate;
    @BindView(R.id.llViewReport)
    LinearLayout llViewReport;
    @BindView(R.id.lvPaymentReport)
    ListView lvPaymentReport;
    Unbinder unbinder;
    String date = "";
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    Boolean isInternetPresent = false;
    SharedPreference sharedPreference;
    Activity activity;
    private ServiceHandler serviceHandler;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }

    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes
        View view = inflater.inflate(R.layout.fragment_paymentreport, container, false);
        initView(view);

        return view;
    }

    private void initView(View view) {
        setHasOptionsMenu(false);
        unbinder = ButterKnife.bind(this, view);
        serviceHandler = new ServiceHandler(activity);
        sharedPreference = SharedPreference.getInstance(activity);
        isInternetPresent=serviceHandler.isConnectingToInternet();
        if (isInternetPresent) {
            getReportSercerCall();
        } else {
            UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tvFormDate, R.id.tvToDate, R.id.llViewReport})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvFormDate:
                tvFormDate.setText(getDatePicker());
                break;
            case R.id.tvToDate:
                tvToDate.setText(getDatePicker());
                break;
            case R.id.llViewReport:
                isInternetPresent=serviceHandler.isConnectingToInternet();
                if (isInternetPresent) {
                    getReportSercerCall();
                } else {
                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
                }
                break;
        }
    }


    private void getReportSercerCall() {
        String fromDate = tvFormDate.getText().toString().trim();
        String toDate = tvToDate.getText().toString().trim();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("fromDate", fromDate);
            jsonObject.put("toDate", toDate);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //:http://cloud.hiaccounts.com:8883/web044/hiposrestapi/paymentReport/getCompletePageData
        String url = sharedPreference.getData(Constant.SERVER_URL) + Constant.POS_URLPATH + "paymentReport/" + Constant.FUNTION_GETPAYMENTREPORT;

        progressBar.setVisibility(View.VISIBLE);
        PostDataTask task = new PostDataTask(activity, new AsyncTaskCompleteListener<String>() {
            @Override
            public void onTaskComplete(String result) {
                progressBar.setVisibility(View.GONE);
                if (result != null) {
                    try {

                        Gson gson=new Gson();
                        List<PaymentReport> paymentReportList=new ArrayList<PaymentReport>();
                        JSONArray jsonArray=new JSONArray(result);
                        if (jsonArray!=null) {
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject=jsonArray.getJSONObject(i);
                                PaymentReport paymentReport=gson.fromJson(jsonObject.toString(),PaymentReport.class);
                                paymentReportList.add(paymentReport);
                            }

                            PaymentReport_Adapter adapter=new PaymentReport_Adapter(activity,paymentReportList);
                            lvPaymentReport.setAdapter(adapter);
                            adapter.notifyDataSetChanged();

                        }
                        UtilView.showLogCat(TAG, "Result " + result.toString());

                    } catch (Exception e) {

                    }
                } else {
                    UtilView.showErrorDialog(getResources().getString(R.string.error_null), activity);
                }
            }
        }, false);
        task.execute(jsonObject.toString(), url, "");
    }


    public String getDatePicker() {
        Calendar calendar = null;
        calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                date = String.valueOf(new StringBuilder().append(month + 1).append("/").append(dayOfMonth).append("/").append(year));
            }
        }, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();

        return date;

    }

}
