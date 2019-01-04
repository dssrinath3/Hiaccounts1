package in.hiaccounts.hinext.controlpanel.fragment.openingbalance;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.controlpanel.activity.Activity_OpeningBalance_BalanceSheet;
import in.hiaccounts.hinext.controlpanel.adapter.OpeningBalanceSheetAdapter;
import in.hiaccounts.hinext.controlpanel.model.openingbalance.BalanceSheetData;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

import static in.hiaccounts.utility.Constant.RESQUSTCODE_ADDOPENINGBALANCESHEET;

/**
 * Created by Prateek on 2/9/2017.
 */

public class Fragment_BalanceSheetOpening extends Fragment implements TextWatcher {


    public static String TAG = Fragment_BalanceSheetOpening.class.getSimpleName();
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.fabAdd)
    FloatingActionButton fabAdd;
    @BindView(R.id.edSearch)
    EditText edSearch;
    @BindView(R.id.llSearch)
    LinearLayout llSearch;
    Unbinder unbinder;

    private ServiceHandler serviceHandler;
    private Activity mActivity;
    private Boolean isInternetPresent = false;
    private List<Object> balanceSheetList = new ArrayList<Object>();
    private OpeningBalanceSheetAdapter opningBalanceAdapter;
    private String search = "", serverUrl;
    public static Fragment_BalanceSheetOpening newInstance() {
        Fragment_BalanceSheetOpening fragment = new Fragment_BalanceSheetOpening();
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
        View view = inflater.inflate(R.layout.fragment_balancesheetopening, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponents(view);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.fabAdd, R.id.llSearch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fabAdd:
                Intent intent = new Intent(mActivity, Activity_OpeningBalance_BalanceSheet.class);
                intent.putExtra("callingFor", Constant.CALL_ADDBALNACESHHET);
                startActivityForResult(intent, RESQUSTCODE_ADDOPENINGBALANCESHEET);


                break;
            case R.id.llSearch:
                search = edSearch.getText().toString().trim();
                getInvListFromServer();
                break;

        }
    }

    private void initComponents(View view) {

        serverUrl= UtilView.getUrl(mActivity);
        serviceHandler = new ServiceHandler(mActivity);
        LayoutInflater inflater = mActivity.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.oepningbalance_adapter_balancesheet, null, false);
        fabAdd.setVisibility(View.VISIBLE);

        TextView tvAmount = header.findViewById(R.id.tvAmount);
        TextView tvDebit = header.findViewById(R.id.tvDebit);
        TextView tvCredit = header.findViewById(R.id.tvCredit);

        tvAmount.setText("Account");
        tvDebit.setText("Debit");
        tvCredit.setText("Credit");

        UtilView.setTextAppearanceSmall(mActivity, tvAmount);
        UtilView.setTextAppearanceSmall(mActivity, tvDebit);
        UtilView.setTextAppearanceSmall(mActivity, tvCredit);

        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvAmount);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvDebit);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvCredit);

        UtilView.setTexttypeFace(null, Typeface.BOLD, tvAmount);
        UtilView.setTexttypeFace(null, Typeface.BOLD, tvDebit);
        UtilView.setTexttypeFace(null, Typeface.BOLD, tvCredit);

        if (listview != null)
            listview.addHeaderView(header);
        edSearch.addTextChangedListener(this);

        getInvListFromServer();


        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    search = edSearch.getText().toString().trim();
                    getInvListFromServer();
                }
                return handled;
            }
        });


    }

    private void getInvListFromServer() {
        String url = serverUrl + "/hipos//1/" + Constant.FUNTION_GETBALANCESHEET + search;

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
                                balanceSheetList.clear();
                                List<BalanceSheetData> list = new ArrayList<>();
                                try {
                                    JSONArray jsonArray = new JSONArray(result.toString());
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                                        BalanceSheetData data = gson.fromJson(jsonObject.toString(), BalanceSheetData.class);
                                        list.add(data);

                                    }
                                    if (list.size() > 0) {


                                        balanceSheetList.addAll(list);
                                        opningBalanceAdapter = new OpeningBalanceSheetAdapter(mActivity, balanceSheetList);
                                        listview.setAdapter(opningBalanceAdapter);
                                        opningBalanceAdapter.notifyDataSetChanged();
                                    } else {
                                        fabAdd.setVisibility(View.VISIBLE);
                                        balanceSheetList.clear();
                                        opningBalanceAdapter = new OpeningBalanceSheetAdapter(mActivity, balanceSheetList);
                                        listview.setAdapter(opningBalanceAdapter);
                                        opningBalanceAdapter.notifyDataSetChanged();
                                        UtilView.showErrorDialog(getResources().getString(R.string.balanceshhet_notavailbale), mActivity);

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
                task.execute(url + search.replace(" ", "%20"), "");
                //task.execute(url, "");
            }
            if (!isInternetPresent) {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
            }
        } else {
            UtilView.gotToLogin(mActivity);
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
            search = "";
            getInvListFromServer();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == RESQUSTCODE_ADDOPENINGBALANCESHEET) {
            getInvListFromServer();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getInvListFromServer();
    }
}
