package in.hiaccounts.hinext.controlpanel.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;
import com.yinglan.keyboard.HideUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.controlpanel.adapter.OpeningBalanceSheetAddAdapter;
import in.hiaccounts.hinext.controlpanel.adapter.OpeningBalance_AccountListAdapter;
import in.hiaccounts.hinext.controlpanel.model.openingbalance.Account;
import in.hiaccounts.hinext.controlpanel.model.openingbalance.BalanceSheetData;
import in.hiaccounts.hinext.controlpanel.model.openingbalance.SaveBalanceSheetData;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 8/21/2017.
 */

public class Activity_OpeningBalance_BalanceSheet extends AppCompatActivity implements TextWatcher {


    public static String TAG = Activity_OpeningBalance_BalanceSheet.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edSearch)
    EditText edSearch;
    @BindView(R.id.llSearch)
    LinearLayout llSearch;
    @BindView(R.id.tvsSelectAccount)
    TextView tvsSelectAccount;
    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.btnClose)
    Button btnClose;
    @BindView(R.id.ll1)
    LinearLayout ll1;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.edTotalDebit)
    EditText edTotalDebit;
    @BindView(R.id.edTotalCredit)
    EditText edTotalCredit;
    private Activity mActivity;
    private ServiceHandler serviceHandler;
    private boolean isInternatePrsenet = false;

    private List<Account> accountList = new ArrayList<>();
    private HashMap<Long, Account> openingbalanceAccountList = new HashMap<>();
    OpeningBalanceSheetAddAdapter accountAdapter;

    public static boolean isError = false;
    public String search = "", serverUrl;
    public Dialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opnbalance_balancesheet);
        ButterKnife.bind(this);
        initComponentView();
    }

    private void initComponentView() {
        mActivity = this;
        serviceHandler = new ServiceHandler(mActivity);
        serverUrl = UtilView.getUrl(mActivity);
        toolbar.setTitle("Add Opening Balances");

        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        edSearch.addTextChangedListener(this);

        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    search = edSearch.getText().toString().trim();
                    dailogAccount(edSearch.getText().toString().trim());
                }
                return handled;
            }
        });


    }


    @OnClick({R.id.llSearch, R.id.tvsSelectAccount, R.id.btnSave, R.id.btnClose})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llSearch:
                dailogAccount(edSearch.getText().toString().trim());
                break;
            case R.id.tvsSelectAccount:
                dailogAccount("");
                break;
            case R.id.btnSave:
                saveAccounts();
                break;
            case R.id.btnClose:
                break;
        }
    }

    public void setTotal() {
        double totalDebit = 0;
        double totalCredit = 0;
        if (openingbalanceAccountList != null && openingbalanceAccountList.size() > 0 && !isError) {
            for (Map.Entry map : openingbalanceAccountList.entrySet()) {
                Account accountDetail = (Account) map.getValue();
                totalCredit += accountDetail.getCrdtAmt();
                totalDebit += accountDetail.getDebitAmt();


            }

            edTotalCredit.setText("" + totalCredit);
            edTotalDebit.setText("" + totalDebit);
        }
    }

    private void saveAccounts() {


        double totalDebit = 0;
        double totalCredit = 0;
        List<Account> accountList = new ArrayList<>();

        if (openingbalanceAccountList != null && openingbalanceAccountList.size() > 0 && !isError) {

            for (Map.Entry map : openingbalanceAccountList.entrySet()) {

                Account accountDetail = (Account) map.getValue();
                UtilView.showLogCat(TAG, " Account Name " + accountDetail.getAccountName() + " : " + accountDetail.getDebitAmount() + " : " + accountDetail.getCreditAmount());

                totalCredit += accountDetail.getCrdtAmt();
                totalDebit += accountDetail.getDebitAmt();

                accountDetail.setCreditAmount(""+accountDetail.getCrdtAmt());
                accountDetail.setDebitAmount(""+accountDetail.getDebitAmt());
                accountList.add(accountDetail);
            }


            SaveBalanceSheetData saveData = new SaveBalanceSheetData();
            saveData.setTotalCredit(totalCredit);
            saveData.setTotalDebit(totalDebit);
            saveData.setSelectedAccountList(accountList);

            UtilView.showLogCat(TAG, "Json " + new Gson().toJson(saveData));

            isInternatePrsenet = serviceHandler.isConnectingToInternet();
            if (serverUrl != null) {
                if (isInternatePrsenet) {
                    UtilView.showProgessBar(mActivity, progressBar);
                    String url = serverUrl + "/hipos//0/" + Constant.FUNTION_SAVEBALANCESHEETDATA;
                    PostDataTask task = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                        @Override
                        public void onTaskComplete(String result) {
                            UtilView.hideProgessBar(mActivity, progressBar);
                            HideUtil.init(mActivity);
                            if (result != null) {
                                if (result.equals("invalid")) {
                                    UtilView.showToast(mActivity, getResources().getString(R.string.accesstoken_error));
                                    Intent intent = new Intent(mActivity, Activity_Login.class);
                                    mActivity.startActivity(intent);
                                    mActivity.finish();
                                } else if (result.equals(getResources().getString(R.string.error_rsonsecode204))) {
                                    UtilView.showToast(mActivity, "Some Error. Please try again.");
                                } else {
                                    try {
                                        JSONObject customerJson = new JSONObject(result.toString());
                                        Gson gson = new Gson();
                                        BalanceSheetData data = gson.fromJson(customerJson.toString(), BalanceSheetData.class);
                                        if (data != null) {
                                            if (openingbalanceAccountList!=null) {
                                                edTotalDebit.setText("");
                                                edTotalCredit.setText("");
                                                openingbalanceAccountList.clear();
                                                accountAdapter = new OpeningBalanceSheetAddAdapter(mActivity, openingbalanceAccountList);
                                                listview.setAdapter(accountAdapter);
                                                accountAdapter.notifyDataSetChanged();
                                                finish();

                                                UtilView.showToast(mActivity, "Opening Balance add successfully.");
                                            }
                                        } else {
                                            UtilView.showToast(mActivity, "Opening Balance doesn't add successfully Please try again.");
                                        }
                                    } catch (Exception e) {

                                        UtilView.showLogCat(TAG, "GetCustomerTaskListener Exception " + e.toString());
                                    }
                                }
                            }
                        }
                    }, false);
                    task.execute(new Gson().toJson(saveData).toString(), url, "");
                } else {
                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                }
            } else {
                UtilView.gotToLogin(mActivity);
            }
        } else {
            if (isError) {
                UtilView.showToast(mActivity, "Please enter valid amount");
            }


        }


    }

    private void dailogAccount(String searchAccount) {

        dialog = new Dialog(mActivity);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.activity_generaltransaction_account);


        final LinearLayout llListView= dialog.findViewById(R.id.llListView);

        ImageView imgviewClose = dialog.findViewById(R.id.imgviewClose);
        final EditText edSearch = dialog.findViewById(R.id.edSearch);
        ImageView imgviewSearch = dialog.findViewById(R.id.imgviewSearch);
        final ListView lvAccount = dialog.findViewById(R.id.listview);
        Button btnClose = dialog.findViewById(R.id.btnClose);
        final ProgressView pg = dialog.findViewById(R.id.progress_bar);


        imgviewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        imgviewSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAccountFromserver(edSearch.getText().toString().trim().replace(" ", "%20"), edSearch, pg, lvAccount, llListView);
            }
        });

/*
        LayoutInflater inflater = mActivity.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.adapter_generaltransaction_account, null, false);

        TextView tvAccountCode = (TextView) header.findViewById(R.id.tvAccountCode);
        TextView tvAccountName = (TextView) header.findViewById(R.id.tvAccountName);
        TextView tvAccountType = (TextView) header.findViewById(R.id.tvAccountType);


        tvAccountCode.setText(getResources().getString(R.string.accountCode));
        tvAccountName.setText(getResources().getString(R.string.accountName));
        tvAccountType.setText(getResources().getString(R.string.caccountType));

        UtilView.setTextAppearanceSmall(mActivity, tvAccountCode);
        UtilView.setTextAppearanceSmall(mActivity, tvAccountName);
        UtilView.setTextAppearanceSmall(mActivity, tvAccountType);

        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvAccountCode);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvAccountName);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvAccountType);

        UtilView.setTexttypeFace(null, Typeface.BOLD, tvAccountCode);
        UtilView.setTexttypeFace(null, Typeface.BOLD, tvAccountName);
        UtilView.setTexttypeFace(null, Typeface.BOLD, tvAccountType);

        lvAccount.addHeaderView(header);*/

        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    getAccountFromserver(edSearch.getText().toString().trim().replace(" ", "%20"), edSearch, pg, listview, llListView);
                }
                return handled;
            }
        });
        dialog.show();
        getAccountFromserver(searchAccount, edSearch, pg, lvAccount,llListView);

        lvAccount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

/*
                if (position == 0) {

                } else {*/
                    Account selectedAccount = accountList.get(position);

                    isInternatePrsenet = serviceHandler.isConnectingToInternet();

                    if (serverUrl != null) {
                        if (isInternatePrsenet) {
                            if (pg != null)
                                pg.setVisibility(View.VISIBLE);
                            String url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETACCOUNTDETAIL + "?accountCode=" + selectedAccount.getAccountid();
                            GetDataTask task = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                                @Override
                                public void onTaskComplete(String result) {
                                    if (pg != null)
                                        pg.setVisibility(View.GONE);
                                    HideUtil.init(mActivity);
                                    if (result != null) {
                                        if (result.equals("invalid")) {
                                            UtilView.showToast(mActivity, getResources().getString(R.string.accesstoken_error));
                                            Intent intent = new Intent(mActivity, Activity_Login.class);
                                            mActivity.startActivity(intent);
                                            mActivity.finish();
                                        } else if (result.equals(getResources().getString(R.string.error_rsonsecode204))) {

                                        } else {
                                            try {
                                                JSONArray resultJsonArray = new JSONArray(result.toString());
                                                if (resultJsonArray.length() > 0) {
                                                    JSONObject customerJson = resultJsonArray.getJSONObject(0);
                                                    Gson gson = new Gson();
                                                    Account account = gson.fromJson(customerJson.toString(), Account.class);
                                                    if (!openingbalanceAccountList.containsKey(account.getAccountid())) {
                                                        openingbalanceAccountList.put(account.getAccountid(), account);
                                                        accountAdapter = new OpeningBalanceSheetAddAdapter(mActivity, openingbalanceAccountList);
                                                        listview.setAdapter(accountAdapter);
                                                        accountAdapter.notifyDataSetChanged();
                                                        dialog.dismiss();
                                                    } else {
                                                        UtilView.showToast(mActivity, "Account already used.");
                                                    }


                                                } else {
                                                    edSearch.setText("");
                                                    UtilView.showErrorDialog(getResources().getString(R.string.noaccountdetail_available), mActivity);
                                                }

                                            } catch (Exception e) {

                                                UtilView.showLogCat(TAG, "GetCustomerTaskListener Exception " + e.toString());
                                            }
                                        }
                                    }
                                }
                            }, false);
                            task.execute(url, "");
                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                        }
                    } else {
                        UtilView.gotToLogin(mActivity);
                    }

            //    }
            }
        });


    }

    private void getAccountFromserver(String searchAccount, final EditText edSearch, final ProgressView pg, final ListView lvAccount, final LinearLayout llListView) {

        isInternatePrsenet = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternatePrsenet) {
                if (pg != null)
                    pg.setVisibility(View.VISIBLE);
                String url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETACCOUNTLIST + "?accountSearchText=" + searchAccount;
                GetDataTask task = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        if (pg != null)
                            pg.setVisibility(View.GONE);
                        HideUtil.init(mActivity);


                        if (result != null) {

                            if (result.equals("invalid")) {
                                UtilView.showToast(mActivity, getResources().getString(R.string.accesstoken_error));
                                Intent intent = new Intent(mActivity, Activity_Login.class);
                                mActivity.startActivity(intent);
                                mActivity.finish();
                            } else if (result.equals(getResources().getString(R.string.error_rsonsecode204))) {

                            } else {

                                try {
                                    JSONArray resultJsonArray = new JSONArray(result.toString());
                                    if (resultJsonArray.length() > 0) {
                                        if (accountList != null) {
                                            accountList.clear();
                                        }
                                        for (int i = 0; i < resultJsonArray.length(); i++) {
                                            JSONObject customerJson = resultJsonArray.getJSONObject(i);
                                            Gson gson = new Gson();
                                            Account account = gson.fromJson(customerJson.toString(), Account.class);
                                            accountList.add(account);
                                        }

                                        if (accountList != null && accountList.size() > 0) {
                                            OpeningBalance_AccountListAdapter accountListAdapter = new OpeningBalance_AccountListAdapter(mActivity, accountList);
                                            lvAccount.setAdapter(accountListAdapter);
                                            accountListAdapter.notifyDataSetChanged();
                                            llListView.setVisibility(View.VISIBLE);
                                        }
                                    } else {
                                        edSearch.setText("");
                                        UtilView.showErrorDialog(getResources().getString(R.string.noaccount_available), mActivity);
                                    }

                                } catch (Exception e) {

                                    UtilView.showLogCat(TAG, "GetCustomerTaskListener Exception " + e.toString());
                                }
                            }
                        }
                    }
                }, false);
                task.execute(url, "");
            } else {
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
            if (dialog != null) {
                if (dialog.isShowing())
                    dialog.dismiss();
            }

        }

    }
}