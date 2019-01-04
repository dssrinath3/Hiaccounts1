package in.hiaccounts.hinext.generaltransaction.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;
import com.yinglan.keyboard.HideUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.generaltransaction.adapter.AccountList_Adapter;
import in.hiaccounts.hinext.generaltransaction.model.AccountPosCreator;
import in.hiaccounts.hinext.generaltransaction.model.AccountPosHelper;
import in.hiaccounts.hinext.generaltransaction.model.pagedata.GeneralTransactionPageData;
import in.hiaccounts.hinext.generaltransaction.model.pagedata.TaxList;
import in.hiaccounts.hinext.generaltransaction.model.payee_account.Account;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

import static java.lang.Double.parseDouble;

/**
 * Created by Admin on 9/18/2017.
 */

public class Activity_SelectAccount extends AppCompatActivity implements TextWatcher {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edSearch)
    EditText edSearch;
    @BindView(R.id.imgviewSearch)
    ImageView imgviewSearch;
    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.llListView)
    LinearLayout llListView;

    private String callingFrom = "", taxType = "", serverUrl;

    public final static String TAG = Activity_SelectAccount.class.getSimpleName();

    private Activity mActivity;
    private ServiceHandler serviceHandler;
    private Boolean isInternetPresent = false;

    private List<Account> accountList = new ArrayList<>();
    GeneralTransactionPageData pageData = null;


    AccountPosCreator accountPosCreator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generaltransaction_accountpayee);
        ButterKnife.bind(this);
        initComponentView();
    }

    private void initComponentView() {
        mActivity = this;
        serviceHandler = new ServiceHandler(mActivity);
        serverUrl = UtilView.getUrl(mActivity);

        accountPosCreator = AccountPosHelper.getPosCreator();

        toolbar.setTitle("Select Account");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        callingFrom = intent.getStringExtra("callingFrom");
        if (callingFrom != null) {
            taxType = intent.getStringExtra("taxType");
        }

        edSearch.addTextChangedListener(this);

        getPageLoadData();


        imgviewSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAccountFromserver(edSearch.getText().toString().trim().replace(" ", "%20"));
            }
        });
        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    getAccountFromserver(edSearch.getText().toString().trim().replace(" ", "%20"));
                }
                return handled;
            }
        });


     /*   LayoutInflater inflater = mActivity.getLayoutInflater();
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
*/

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    isInternetPresent = serviceHandler.isConnectingToInternet();
                    if (serverUrl != null) {
                        if (isInternetPresent) {
                            UtilView.showProgessBar(mActivity, progressBar);
                            String url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETACCOUNTDETAIL + "?accountCode=" + accountList.get(position).getAccountid();
                            GetDataTask task = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
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
                                        } else {
                                            try {
                                                JSONArray resultJsonArray = new JSONArray(result.toString());
                                                if (resultJsonArray.length() > 0) {

                                                    for (int i = 0; i < resultJsonArray.length(); i++) {
                                                        JSONObject customerJson = resultJsonArray.getJSONObject(i);
                                                        Gson gson = new Gson();
                                                        Account account = gson.fromJson(customerJson.toString(), Account.class);

                                                        dailoagAccountDetail(account);

                                                    }
                                                } else {
                                                    edSearch.setText("");
                                                    UtilView.showErrorDialog(getResources().getString(R.string.noaccountdetail_available), mActivity);
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
                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                        }
                    } else {
                        UtilView.gotToLogin(mActivity);
                    }
                }


        });


    }

    private void getPageLoadData() {
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
                UtilView.showProgessBar(mActivity, progressBar);
                String url = serverUrl + "/purchase//0/" + Constant.FUNCTION_GETPAGELOADDATA;
                GetDataTask task = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
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
                            } else {
                                try {

                                    JSONObject jsonObject = new JSONObject(result.toString());
                                    Gson gson = new Gson();
                                    pageData = gson.fromJson(jsonObject.toString(), GeneralTransactionPageData.class);
                                    getAccountFromserver("");

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
            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
            }
        } else {
            UtilView.gotToLogin(mActivity);
        }

    }


    private void getAccountFromserver(String searchAccount) {

        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
                UtilView.showProgessBar(mActivity, progressBar);
                String url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETACCOUNTLIST + "?accountSearchText=" + searchAccount;
                GetDataTask task = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
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
                                            AccountList_Adapter accountListAdapter = new AccountList_Adapter(mActivity, accountList);
                                            listview.setAdapter(accountListAdapter);
                                            accountListAdapter.notifyDataSetChanged();
                                            llListView.setVisibility(View.VISIBLE);
                                        }
                                    } else {
                                        edSearch.setText("");
                                        llListView.setVisibility(View.GONE);
                                        UtilView.showErrorDialog(getResources().getString(R.string.noaccount_available), mActivity);
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
            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
            }
        } else {
            UtilView.gotToLogin(mActivity);
        }
    }

    EditText edAccountName;
    EditText edAccountDescritpion;
    EditText edInoviceNumber;
    EditText edActAmtExTax;
    EditText edTaxTypeAmount;
    EditText edTaxamt;
    EditText edActAmtIncTax;
    Spinner spinActtax;
    TextView tvTaxType;
    boolean isValid = false;
    Long taxId;

    private void dailoagAccountDetail(final Account account) {

        final Dialog dialog = new Dialog(mActivity);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_generaltransaction_accountadddetail);

        edAccountName = dialog.findViewById(R.id.edAccountName);
        edAccountDescritpion = dialog.findViewById(R.id.edAccountDescritpion);
        edInoviceNumber = dialog.findViewById(R.id.edInoviceNumber);
        edActAmtExTax = dialog.findViewById(R.id.edActAmtExTax);
        edTaxTypeAmount = dialog.findViewById(R.id.edTaxTypeAmount);
        edTaxamt = dialog.findViewById(R.id.edTaxamt);
        edActAmtIncTax = dialog.findViewById(R.id.edActAmtIncTax);
        spinActtax = dialog.findViewById(R.id.spinActtax);
        tvTaxType = dialog.findViewById(R.id.tvTaxType);

        Button btnAdd = dialog.findViewById(R.id.btnAdd);
        Button btnClose = dialog.findViewById(R.id.btnClose);

        if (account.getAccountName() != null)
            edAccountName.setText(account.getAccountName());

        if (account.getAccountDescription() != null)
            edAccountDescritpion.setText(account.getAccountDescription());


        spinActtax.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {

                    TaxList tax = (TaxList) spinActtax.getSelectedItem();
                    if (tax != null) {
                        taxId = tax.getTaxid();
                    }

                    double amtExtax = Double.parseDouble(edActAmtExTax.getText().toString().trim());
                    updateAmount(amtExtax);
                    isValid = true;
                } catch (NumberFormatException ne) {
                    edActAmtExTax.setError("Must be numeric value with only one .");
                    isValid = false;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        if (taxType != null)
            tvTaxType.setText(taxType);

        if (pageData != null) {
            if (pageData.getTaxList() != null)
                UtilView.setupGTTaxAdapter(mActivity, spinActtax, pageData.getTaxList());
        }

        edActAmtExTax.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")) {

                    // edActAmtExTax.setText("0");
                    edActAmtIncTax.setText("0");
                    edTaxamt.setText("0");
                    edActAmtExTax.setError("Amount can't be empty.");
                    isValid = false;

                } else {
                    try {
                        double amtExTAx = parseDouble(s.toString());
                        updateAmount(amtExTAx);
                        isValid = true;
                    } catch (NumberFormatException e) {

                        edActAmtExTax.setError("Must be numeric value with only one .");
                        isValid = false;
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        if (dialog != null)
            dialog.show();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String actDescription = edAccountDescritpion.getText().toString().trim();
                String invoiceNumber = edInoviceNumber.getText().toString().trim();
                String amtExTax = edActAmtExTax.getText().toString().trim();
                String amtTax = edTaxamt.getText().toString().trim();
                String amtIncTax = edActAmtIncTax.getText().toString().trim();

                try {
                    double amountExTax = Double.parseDouble(amtExTax);
                    if (amountExTax != 0.00 && isValid) {

                        account.setAccountDescription(actDescription);
                        account.setInvoiceNo(invoiceNumber);
                        account.setAmtexclusivetax(Double.parseDouble(amtExTax));
                        account.setTaxamt(Double.parseDouble(amtTax));
                        account.setAmtinclusivetax(Double.parseDouble(amtIncTax));
                        account.setTaxid(taxId);
                        account.setTaxAmountSplit(Double.parseDouble(amtTax));

                        accountPosCreator.addAccount(account);
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("accountData",account);
                        setResult(Activity.RESULT_OK, returnIntent);
                        finish();

                        if (dialog != null) {
                            if (dialog.isShowing())
                                dialog.dismiss();
                        }

                    } else {
                        isValid = false;
                        edActAmtExTax.setError("Amount can't be empty.");
                    }
                } catch (NumberFormatException ne) {

                    edActAmtExTax.setError("Must be numeric value with only one .");
                    isValid = false;
                }
            }
        });


        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });


    }

    private void updateAmount(double amtExTAx) {
        TaxList tax = (TaxList) spinActtax.getSelectedItem();
        double taxPercentage = 0;
        if (tax != null) {
            taxPercentage = getTax(tax.getTaxString());
        }

        double amtTax = (amtExTAx * taxPercentage) / 100;
        double amtIncTax = amtExTAx + amtTax;

        edTaxamt.setText(String.format("%.2f", amtTax));

        if (taxType.equals(getString(R.string.igst))) {
            edTaxTypeAmount.setText(String.format("%.2f", amtTax));
        }

        if (taxType.equals(getString(R.string.cgst))) {
            edTaxTypeAmount.setText(String.format("%.2f", amtTax / 2) + "+" + String.format("%.2f", amtTax / 2));
        }

        edActAmtIncTax.setText(String.format("%.2f", amtIncTax));


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
            getAccountFromserver("");
        }
    }

    private double getTax(String tax) {

        try {
            int pos = tax.indexOf("|");
            double taxValue = parseDouble(tax.substring(0, pos).trim());
            UtilView.showLogCat("@Flow", "getTax taxVAlue " + taxValue);
            return taxValue;
        } catch (Exception e) {
            UtilView.showLogCat("@Flow", "getTax Exception " + e.toString());

        }


        return 0d;


    }
}
