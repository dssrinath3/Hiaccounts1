package in.hiaccounts.hinext.controlpanel.fragment.accountmaintenance;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.controlpanel.activity.Activity_ActMaintance_CreateAccount;
import in.hiaccounts.hinext.controlpanel.adapter.ChartListAdapter;
import in.hiaccounts.hinext.controlpanel.model.accountmaintance.ChartAccountData;
import in.hiaccounts.hinext.controlpanel.model.accountmaintance.ChartAccountDatum;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 2/9/2017.
 */

public class Fragment_CreateChart extends Fragment implements TextWatcher {


    public static String TAG = Fragment_CreateChart.class.getSimpleName();
    String search = "";
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.edSearch)
    EditText edSearch;
    @BindView(R.id.llSearch)
    LinearLayout llSearch;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.tvFirst)
    TextView tvFirst;
    @BindView(R.id.tvPrev)
    TextView tvPrev;
    @BindView(R.id.tvPageNo)
    TextView tvPageNo;
    @BindView(R.id.tvNext)
    TextView tvNext;
    @BindView(R.id.tvLast)
    TextView tvLast;
    @BindView(R.id.llPaginationView)
    LinearLayout llPaginationView;
    @BindView(R.id.rlContent)
    RelativeLayout rlContent;
    private ServiceHandler serviceHandler;
    private Activity mActivity;
    private Boolean isInternetPresent = false;
    ChartListAdapter chartListAdapter;
    Unbinder unbinder;
    List<ChartAccountDatum> accountDataList = new ArrayList<>();
    int pageNo = 0;
    private String serverUrl;

    public static Fragment_CreateChart newInstance() {
        Fragment_CreateChart fragment = new Fragment_CreateChart();
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
        View view = inflater.inflate(R.layout.fragment_actmaincreatechart, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponentView(view);
        return view;
    }

    private void initComponentView(View view) {

        serverUrl = UtilView.getUrl(mActivity);
        setHasOptionsMenu(true);
        LayoutInflater inflater = mActivity.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.companysetup_adapter_profitloss, null, false);

        TextView tvActCode = header.findViewById(R.id.tvActCode);
        TextView tvActName = header.findViewById(R.id.tvActName);
        TextView tvActClass = header.findViewById(R.id.tvActClass);
        TextView tvActGroup = header.findViewById(R.id.tvActGroup);


        tvActCode.setText("Account Code");
        tvActName.setText("Account Name");
        tvActClass.setText("Account Type");
        tvActGroup.setText("Account Group");

        UtilView.setTextAppearanceSmall(mActivity, tvActCode);
        UtilView.setTextAppearanceSmall(mActivity, tvActName);
        UtilView.setTextAppearanceSmall(mActivity, tvActClass);
        UtilView.setTextAppearanceSmall(mActivity, tvActGroup);


        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvActCode);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvActName);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvActClass);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvActGroup);


        edSearch.addTextChangedListener(this);
        if (listview != null)
            listview.addHeaderView(header);
        serviceHandler = new ServiceHandler(mActivity);
        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    search = edSearch.getText().toString().trim();
                    getChartAccountListFromServer(search, true, false, "0", false, false);
                }
                return handled;
            }
        });
        getChartAccountListFromServer("", true, false, "0", false, false);


    }

    private void getChartAccountListFromServer(String search, boolean isFirst, boolean isPrev, String pageNumber, boolean isNext, boolean isLast) {

        // String url = Constant.NGROK_URL + Constant.FUNTION_GETCHARTACCOUNTGROUPLIST + search;
        String url = "";
        if (search.equals("")) {
            url = serverUrl + Constant.FUNTION_GETCHARTACCOUNTGROUPLIST + "&firstPage=" + isFirst + "&lastPage=" + isLast + "&nextPage=" + isNext + "&pageNo=" + pageNumber + "&prevPage=" + isPrev;
        }
        if (!search.equals("")) {
            url = serverUrl + Constant.FUNTION_GETCHARTACCOUNTGROUPLIST + search.replace(" ", "%20") + "&firstPage=" + isFirst + "&lastPage=" + isLast + "&nextPage=" + isNext + "&pageNo=" + pageNumber + "&prevPage=" + isPrev;
        }
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
                                Gson gson = new Gson();
                                accountDataList.clear();
                                List<Object> objectList = new ArrayList<>();
                                try {

                                    ChartAccountData data = gson.fromJson(result.toString(), ChartAccountData.class);
                                    if (data != null) {
                                        setUpView(data);
                                        if (data.getData() != null && data.getData().size() > 0) {
                                            accountDataList = data.getData();
                                            objectList.addAll(accountDataList);
                                            chartListAdapter = new ChartListAdapter(mActivity, objectList);
                                            listview.setAdapter(chartListAdapter);
                                            chartListAdapter.notifyDataSetChanged();
                                        } else {
                                            objectList.clear();
                                            chartListAdapter = new ChartListAdapter(mActivity, objectList);
                                            listview.setAdapter(chartListAdapter);
                                            chartListAdapter.notifyDataSetChanged();
                                            UtilView.showToast(mActivity, getResources().getString(R.string.noaccount_available));

                                        }
                                    }

                                } catch (Exception e) {
                                    UtilView.showErrorDialog(getResources().getString(R.string.error_null), mActivity);
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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tvFirst, R.id.tvPrev, R.id.tvNext, R.id.tvLast, R.id.llSearch})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.llSearch:
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                search = edSearch.getText().toString();
                getChartAccountListFromServer(search, true, false, "0", false, false);
                break;
            case R.id.tvFirst:
                pageNo = 0;
                getChartAccountListFromServer("", true, false, "0", false, false);
                break;
            case R.id.tvPrev:
                if (pageNo > 0)
                    pageNo = pageNo - 1;
                getChartAccountListFromServer("", false, true, "" + pageNo, false, false);
                break;
            case R.id.tvNext:
                pageNo = pageNo + 1;
                getChartAccountListFromServer("", false, false, "" + pageNo, true, false);
                break;
            case R.id.tvLast:
                pageNo = 0;
                getChartAccountListFromServer("", false, false, "0", false, true);
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

        if (s.toString().equals("")) {
            UtilView.hideSoftKeyboard(mActivity, edSearch);
            getChartAccountListFromServer("", true, false, "0", false, false);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == Constant.RESQUSTCODE_CREATECHARTACCOUNT) {
            getChartAccountListFromServer("", true, false, "0", false, false);

        }
    }

    private void setUpView(ChartAccountData data) {

        if (rlContent != null)
            rlContent.setVisibility(View.VISIBLE);
        if (llPaginationView != null)
            llPaginationView.setVisibility(View.VISIBLE);

        if (tvFirst != null) {
            if (data.isFirst()) {
                //  tvFirst.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
                tvFirst.setTextColor(getResources().getColor(R.color.colorBlack));
                tvFirst.setClickable(false);

            }
            if (!data.isFirst()) {
                // tvFirst.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
                tvFirst.setTextColor(getResources().getColor(R.color.colorWhite));
                tvFirst.setClickable(true);
            }
        }

        if (tvNext != null) {
            if (data.isNext()) {
                // tvNext.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
                tvNext.setTextColor(getResources().getColor(R.color.colorBlack));
                tvNext.setClickable(false);

            }
            if (!data.isNext()) {
                //  tvNext.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
                tvNext.setTextColor(getResources().getColor(R.color.colorWhite));
                tvNext.setClickable(true);
            }
        }

        if (tvPrev != null) {
            if (data.isPrev()) {
                //  tvPrev.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
                tvPrev.setTextColor(getResources().getColor(R.color.colorBlack));
                tvPrev.setClickable(false);

            }
            if (!data.isPrev()) {
                //  tvPrev.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
                tvPrev.setTextColor(getResources().getColor(R.color.colorWhite));
                tvPrev.setClickable(true);
            }
        }

        if (tvLast != null) {
            if (data.isLast()) {
                //  tvLast.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
                tvLast.setTextColor(getResources().getColor(R.color.colorBlack));
                tvLast.setClickable(false);
            }
            if (!data.isLast()) {
                //   tvLast.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
                tvLast.setTextColor(getResources().getColor(R.color.colorWhite));
                tvLast.setClickable(true);
            }
        }
        pageNo = data.getPageNo();


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.inventory_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.addMenu:
                Intent intent = new Intent(mActivity, Activity_ActMaintance_CreateAccount.class);
                intent.putExtra("callingFor", Constant.CALL_CREATECHARTACCOUNT);
                startActivityForResult(intent, Constant.RESQUSTCODE_CREATECHARTACCOUNT);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
