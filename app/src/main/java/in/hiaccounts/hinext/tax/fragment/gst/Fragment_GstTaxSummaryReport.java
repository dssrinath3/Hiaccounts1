package in.hiaccounts.hinext.tax.fragment.gst;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.rey.material.widget.ProgressView;

import org.json.JSONArray;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.hiaccounts.R;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 2/9/2017.
 */

public class Fragment_GstTaxSummaryReport extends Fragment {
    public static String TAG = Fragment_GstTaxSummaryReport.class.getSimpleName();
    @BindView(R.id.edGstInputTax)
    EditText edGstInputTax;
    @BindView(R.id.edGstOutputTax)
    EditText edGstOutputTax;
    @BindView(R.id.edAmount)
    EditText edAmount;
    @BindView(R.id.btnView)
    Button btnView;
    Unbinder unbinder;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    private Activity mActivity;
    private ServiceHandler serviceHandler;
    private Boolean isInternetPresent = false;
    private String serverUrl;

    public static Fragment_GstTaxSummaryReport newInstance() {
        Fragment_GstTaxSummaryReport fragment = new Fragment_GstTaxSummaryReport();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Returning the layout file after inflating
        View view = inflater.inflate(R.layout.fragment_gsttaxsummaryreport, container, false);
        unbinder = ButterKnife.bind(this, view);
        serviceHandler = new ServiceHandler(mActivity);
        serverUrl = UtilView.getUrl(mActivity);
        return view;
    }

    private void getGSTSummaryReportFromServer() {
        String url = serverUrl + Constant.FUNCTION_GETGSTSUMMARYREPORT;
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
                            try {
                                JSONArray jsonArray = new JSONArray(result.toString());
                                double gstInputTax = Math.abs(jsonArray.getDouble(0));
                                double gstOutPutTax = Math.abs(jsonArray.getDouble(1));
                                double amt = gstOutPutTax - gstInputTax;
                                // BigDecimal bigAmount = new BigDecimal(gstOutPutTax).setScale(2, BigDecimal.ROUND_UP).subtract(new BigDecimal(gstInputTax).setScale(2, BigDecimal.ROUND_UP));
                                edAmount.setText(String.format("%.2f", amt));
                                edGstInputTax.setText(String.format("%.2f", gstInputTax));
                                edGstOutputTax.setText(String.format("%.2f", gstOutPutTax));
                              /*  edGstInputTax.setText("" + new BigDecimal(gstInputTax).setScale(2, BigDecimal.ROUND_UP));
                                edGstOutputTax.setText("" + new BigDecimal(gstOutPutTax).setScale(2, BigDecimal.ROUND_UP));*/
                            } catch (Exception e) {
                                UtilView.showErrorDialog(getResources().getString(R.string.response_error), mActivity);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null)
            unbinder.unbind();
    }

    @OnClick(R.id.btnView)
    public void onViewClicked(View view) {

        if (view.getId() == R.id.btnView) {
            getGSTSummaryReportFromServer();
        }
    }
}
