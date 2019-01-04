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
import android.widget.CheckBox;
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
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.controlpanel.adapter.PaymentMethod_AccountList_Adapter;
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.paymentmethod.AccountMaster;
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.paymentmethod.PaymentMethodAddDatum;
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.paymentmethod.PaymentMethodDatum;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 8/21/2017.
 */

public class Activity_Config_PaymentMethod extends AppCompatActivity implements TextWatcher {
    public static String TAG = Activity_Config_PaymentMethod.class.getSimpleName();
    PaymentMethodDatum paymentMethodDetail;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edPaymentTitle)
    EditText edPaymentTitle;
    @BindView(R.id.edDescription)
    EditText edDescription;
    @BindView(R.id.spinnerPaymentType)
    Spinner spinnerPaymentType;
    @BindView(R.id.edAccount)
    EditText edAccount;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.btnClose)
    Button btnClose;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.llCategoryName)
    LinearLayout llCategoryName;
    @BindView(R.id.id_default_type)
    TextView idDefaultType;
    @BindView(R.id.checkbox_type)
    CheckBox checkboxType;
    String callingFor, serverUrl;
    private ServiceHandler serviceHandler;
    private boolean isInternatePrsenet = false;
    private Activity activity;

    private List<AccountMaster> accountList = new ArrayList<>();
    private AccountMaster selectAccount = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpanel_configpaymentmethod);
        ButterKnife.bind(this);

        initComponentView();
    }

    private void initComponentView() {

        activity = this;
        serviceHandler = new ServiceHandler(activity);
        serverUrl = UtilView.getUrl(activity);
        Intent intent = getIntent();

        UtilView.setupAdapter(activity, spinnerPaymentType, getResources().getStringArray(R.array.config_paymentmethod_type));

        if (intent != null) {
            callingFor = intent.getStringExtra("callingFor");
            if (callingFor != null) {
                toolbar.setTitle(callingFor);

                if (callingFor.equals(Constant.CALL_EDITPAYMENTMETHOD)) {
                    paymentMethodDetail = (PaymentMethodDatum) intent.getSerializableExtra("paymentData");

                    if (paymentMethodDetail != null) {

                        if (paymentMethodDetail.getPaymentmethodName() != null)
                            edPaymentTitle.setText(paymentMethodDetail.getPaymentmethodName());

                        if (paymentMethodDetail.getPaymentmethodDescription() != null)
                            edDescription.setText(paymentMethodDetail.getPaymentmethodDescription());

                        if (paymentMethodDetail.getPaymentmethodType() != null) {


                            if (paymentMethodDetail.getPaymentmethodType().equals("Cash")) {
                                spinnerPaymentType.setSelection(0);
                            }

                            if (paymentMethodDetail.getPaymentmethodType().equals("Bank")) {
                                spinnerPaymentType.setSelection(1);
                            }

                            if (paymentMethodDetail.getPaymentmethodType().equals("Voucher")) {
                                spinnerPaymentType.setSelection(2);
                            }
                            if (paymentMethodDetail.getPaymentmethodType().equals("Card")) {
                                spinnerPaymentType.setSelection(3);
                            }

                        }
                        if (paymentMethodDetail.getDefaultType() == true){
                            checkboxType.setChecked(paymentMethodDetail.getDefaultType());
                        }else{
                            checkboxType.setChecked(paymentMethodDetail.getDefaultType());
                        }

                        if (paymentMethodDetail.getAccountMaster() != null) {
                            edAccount.setError(null);

                            if (paymentMethodDetail.getAccountMaster() != null)
                                edAccount.setText(paymentMethodDetail.getAccountMaster());
                        }
                    }
                }
            }



        }

        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        edPaymentTitle.addTextChangedListener(this);

    }


    private void addPaymentMehtod() {


        String paymentTitle = edPaymentTitle.getText().toString().trim();
        String description = edDescription.getText().toString().trim();
        boolean defaultType = defaultTypeCheckMehtod();
        String account = edAccount.getText().toString().trim();
        if (!paymentTitle.equals("") && paymentTitle != null && account != null) {


            PaymentMethodAddDatum paymentData = new PaymentMethodAddDatum();
            paymentData.setAccountMaster(selectAccount);
            paymentData.setPaymentmethodType((String) spinnerPaymentType.getSelectedItem());
            paymentData.setPaymentmethodDescription(description);
            paymentData.setPaymentmethodName(paymentTitle);
            paymentData.setDefaultType(defaultType);

            if (callingFor.equals(Constant.CALL_EDITPAYMENTMETHOD)) {
                paymentData.setAccountMaster(selectAccount);
                paymentData.setPaymentmethodId(paymentMethodDetail.getPaymentmethodId());
            }


            String url = serverUrl + "/retail//" + Constant.FUNTION_ADDPAYMNETMETHOD;

            if (serverUrl != null) {
                isInternatePrsenet = serviceHandler.isConnectingToInternet();
                if (isInternatePrsenet) {
                    UtilView.showProgessBar(activity, progressBar);
                    PostDataTask task = new PostDataTask(activity, new AsyncTaskCompleteListener<String>() {
                        @Override
                        public void onTaskComplete(String result) {
                            UtilView.hideProgessBar(activity, progressBar);

                            if (result != null) {
                                if (result.equals("invalid")) {
                                    UtilView.showToast(activity, getResources().getString(R.string.accesstoken_error));
                                    Intent intent = new Intent(activity, Activity_Login.class);
                                    activity.startActivity(intent);
                                    activity.finish();
                                } else {

                                    Gson gson = new Gson();
                                    try {
                                        JSONObject jsonObject = new JSONObject(result.toString());
                                        PaymentMethodAddDatum data = gson.fromJson(jsonObject.toString(), PaymentMethodAddDatum.class);

                                        if (data != null) {
                                            UtilView.showToast(activity, "Payment Method Added Successfully.");
                                            Intent returnIntent = new Intent();
                                            activity.setResult(Activity.RESULT_OK, returnIntent);
                                            activity.finish();
                                        }


                                    } catch (Exception e) {
                                        UtilView.showLogCat(TAG, "TaskCompleteListener Exception " + e.toString());
                                    }
                                }
                            } else {
                                UtilView.showErrorDialog(getResources().getString(R.string.error_null), activity);
                            }
                        }
                    }, false);
                    task.execute(new Gson().toJson(paymentData), url, "");

                }
                if (!isInternatePrsenet) {
                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
                }
            } else {
                UtilView.gotToLogin(activity);
            }

        } else {
            if (paymentTitle == null || paymentTitle.equals("")) {
                edPaymentTitle.setError(getResources().getString(R.string.err_msg));
            }
            if (account == null) {
                edAccount.setError(getResources().getString(R.string.err_msg));
            }
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        edPaymentTitle.setError(null);

    }

    @Override
    public void afterTextChanged(Editable s) {

    }


    @OnClick({R.id.edAccount, R.id.btnSave, R.id.btnClose, R.id.checkbox_type})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edAccount:
                dailogAccount();
                break;
            case R.id.btnSave:
                addPaymentMehtod();
                break;
            case R.id.checkbox_type:
                defaultTypeCheckMehtod();
                break;
            case R.id.btnClose:
                finish();
                break;
        }
    }

    private boolean defaultTypeCheckMehtod() {
        return checkboxType.isChecked();
    }

    private void dailogAccount() {

        final Dialog dialog = new Dialog(activity);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.activity_generaltransaction_account);

        final LinearLayout llListView = dialog.findViewById(R.id.llListView);
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
                getAccountFromserver(edSearch.getText().toString().trim().replace(" ", "%20"), edSearch, pg, lvAccount, dialog, llListView);
            }
        });
/*

        LayoutInflater inflater = activity.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.adapter_generaltransaction_account, null, false);

        TextView tvAccountCode = (TextView) header.findViewById(R.id.tvAccountCode);
        TextView tvAccountName = (TextView) header.findViewById(R.id.tvAccountName);
        TextView tvAccountType = (TextView) header.findViewById(R.id.tvAccountType);


        tvAccountCode.setText(getResources().getString(R.string.accountCode));
        tvAccountName.setText(getResources().getString(R.string.accountName));
        tvAccountType.setText(getResources().getString(R.string.caccountType));

        UtilView.setTextAppearanceSmall(activity, tvAccountCode);
        UtilView.setTextAppearanceSmall(activity, tvAccountName);
        UtilView.setTextAppearanceSmall(activity, tvAccountType);

        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvAccountCode);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvAccountName);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvAccountType);

        UtilView.setTexttypeFace(null, Typeface.BOLD, tvAccountCode);
        UtilView.setTexttypeFace(null, Typeface.BOLD, tvAccountName);
        UtilView.setTexttypeFace(null, Typeface.BOLD, tvAccountType);

        if (lvAccount != null)
            lvAccount.addHeaderView(header);*/

        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(activity, edSearch);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    getAccountFromserver(edSearch.getText().toString().trim().replace(" ", "%20"), edSearch, pg, lvAccount, dialog, llListView);
                }
                return handled;
            }
        });
        dialog.show();


        getAccountFromserver("", edSearch, pg, lvAccount, dialog, llListView);


        lvAccount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              /*  if (position == 0) {

                } else {*/
                UtilView.showLogCat(TAG, "Select Accont " + new Gson().toJson(accountList.get(position)));

                edAccount.setError(null);
                selectAccount = accountList.get(position);
                if (selectAccount.getAccountName() != null)
                    edAccount.setText(selectAccount.getAccountName());
                dialog.dismiss();
                //}
            }
        });
    }

    private void getAccountFromserver(String searchAccount, final EditText edSearch, final ProgressView pg, final ListView lvAccount, final Dialog dialog, final LinearLayout llListView) {

        isInternatePrsenet = serviceHandler.isConnectingToInternet();
        if (isInternatePrsenet) {
            if (pg != null)
                pg.setVisibility(View.VISIBLE);
            String url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETMASTERACCOUNTLIST + "?accountSearchText=" + searchAccount;
            if (serverUrl != null) {
                GetDataTask task = new GetDataTask(activity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        if (pg != null)
                            pg.setVisibility(View.GONE);
                        HideUtil.init(activity);
                        if (result != null) {
                            try {
                                JSONArray resultJsonArray = new JSONArray(result.toString());
                                if (resultJsonArray.length() > 0) {
                                    if (accountList != null) {
                                        accountList.clear();
                                    }
                                    for (int i = 0; i < resultJsonArray.length(); i++) {
                                        JSONObject customerJson = resultJsonArray.getJSONObject(i);
                                        Gson gson = new Gson();
                                        AccountMaster account = gson.fromJson(customerJson.toString(), AccountMaster.class);
                                        accountList.add(account);
                                    }

                                    if (accountList != null && accountList.size() > 0) {
                                        PaymentMethod_AccountList_Adapter accountListAdapter = new PaymentMethod_AccountList_Adapter(activity, accountList);
                                        lvAccount.setAdapter(accountListAdapter);
                                        accountListAdapter.notifyDataSetChanged();
                                        llListView.setVisibility(View.VISIBLE);
                                    }
                                } else {
                                    edSearch.setText("");
                                    UtilView.showErrorDialog(getResources().getString(R.string.noaccount_available), activity);
                                    llListView.setVisibility(View.GONE);
                                }

                            } catch (JSONException e) {

                                UtilView.showLogCat(TAG, "getAccountFromserver Exception " + e.toString());
                            }

                        } else {
                            //  showErrorDialog("Getting Error to fetch Customers.");
                        }
                    }
                }, false);
                task.execute(url, "");
            } else {
                UtilView.gotToLogin(activity);
            }
        } else {
            UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
        }
    }

}
