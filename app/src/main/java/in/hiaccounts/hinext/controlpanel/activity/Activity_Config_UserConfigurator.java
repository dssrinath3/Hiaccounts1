package in.hiaccounts.hinext.controlpanel.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import in.hiaccounts.hinext.controlpanel.adapter.BankAccount_Adapter;
import in.hiaccounts.hinext.controlpanel.adapter.CashAccount_Adapter;
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.configuration.Account;
import in.hiaccounts.hinext.controlpanel.model.useraccount_setup.AddUserConfiguratorData;
import in.hiaccounts.hinext.controlpanel.model.useraccount_setup.UserAccountData;
import in.hiaccounts.hinext.controlpanel.model.useraccount_setup.UserConfiguratorData;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

public class Activity_Config_UserConfigurator extends AppCompatActivity {

    public static String TAG = Activity_Config_UserConfigurator.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edCashAccount)
    EditText edCashAccount;
    @BindView(R.id.edBankAccount)
    EditText edBankAccount;
    @BindView(R.id.spinUnitPrice)
    Spinner spinUnitPrice;
    @BindView(R.id.id_Priority)
    LinearLayout idPriority;
    @BindView(R.id.spinDiscount)
    Spinner spinDiscount;
    @BindView(R.id.id_Discount)
    LinearLayout idDiscount;
    @BindView(R.id.spinCess)
    Spinner spinCess;
    @BindView(R.id.id_Cess)
    LinearLayout idCess;
    @BindView(R.id.spinUOM)
    Spinner spinUOM;
    @BindView(R.id.id_UOM)
    LinearLayout idUOM;
    @BindView(R.id.spinTax)
    Spinner spinTax;
    @BindView(R.id.id_Tax)
    LinearLayout idTax;
    @BindView(R.id.spinDate)
    Spinner spinDate;
    @BindView(R.id.id_Date)
    LinearLayout idDate;
    @BindView(R.id.spinPaymentType)
    Spinner spinPaymentType;
    @BindView(R.id.id_PaymentType)
    LinearLayout idPaymentType;
    @BindView(R.id.spinQuantity)
    Spinner spinQuantity;
    @BindView(R.id.id_Quantity)
    LinearLayout idQuantity;
    @BindView(R.id.spinDescription)
    Spinner spinDescription;
    @BindView(R.id.id_Description)
    LinearLayout idDescription;
    @BindView(R.id.radioBtnNormal)
    RadioButton radioBtnNormal;
    @BindView(R.id.radioBtnPOS)
    RadioButton radioBtnPOS;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.btnClose)
    Button btnClose;
    @BindView(R.id.ll1)
    LinearLayout ll1;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;

    private Activity activity;
    private ServiceHandler serviceHandler;
    private boolean isInternatePrsenet = false;
    String callingFor, serverUrl, spUnitPrice, spDiscount, spCess, spUom, spTax, spDate, spPaymentType, spQuantity, spDescription;
    UserAccountData userAccountDetails;

    private List<Object> objectUnitPriceList = new ArrayList<Object>();
    private List<Object> objectDiscountList = new ArrayList<Object>();
    private List<Object> objectCessList = new ArrayList<Object>();
    private List<Object> objectUOMList = new ArrayList<Object>();
    private List<Object> objectTaxList = new ArrayList<Object>();
    private List<Object> objectDateList = new ArrayList<Object>();
    private List<Object> objectPaymentTypeList = new ArrayList<Object>();
    private List<Object> objectQuantityList = new ArrayList<Object>();
    private List<Object> objectDescriptionList = new ArrayList<Object>();

    List<UserConfiguratorData> userConfiguratorDataList;
    private List<Account> cashAccountList;
    private List<Account> bankAccountList;
    private Account cashAccount, bankAccount;
    Long cashAccountId, bankAccountId,userAccountSetupID;

    UserConfiguratorData userConfiguratorData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_user_configurator);
        ButterKnife.bind(this);
        initComponentView();
    }

    private void initComponentView() {
        activity = this;
        serviceHandler = new ServiceHandler(activity);
        serverUrl = UtilView.getUrl(activity);
        isInternatePrsenet = serviceHandler.isConnectingToInternet();

        Intent intent = getIntent();

        if (intent != null) {
            callingFor = intent.getStringExtra("callingFor");
            userAccountDetails = (UserAccountData) intent.getSerializableExtra("configuaratorData");
            toolbar.setTitle(callingFor);

        }

        if (userAccountDetails != null && userAccountDetails.getUserAccessRightsId() != null) {
            getUserAccountConfigurationDetail();
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




    }

    private void getUserAccountConfigurationDetail() {
        String url = serverUrl + "/hipos//1/" + Constant.FUNTION_GTEUSERCONFIGURATOR + userAccountDetails.getUserAccessRightsId();// prepare the Request


        if (serverUrl != null) {
            isInternatePrsenet = serviceHandler.isConnectingToInternet();
            if (isInternatePrsenet) {
                UtilView.showProgessBar(activity, progressBar);
                GetDataTask task = new GetDataTask(activity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(activity, progressBar);
                        if (result != null) {
                            try {
                                Gson gson = new Gson();
                                try {
                                    JSONObject jsonObject = new JSONObject(result.toString());
                                    userConfiguratorData = gson.fromJson(jsonObject.toString(), UserConfiguratorData.class);
                                    if (userConfiguratorData != null) {
                                        setUpView(userConfiguratorData);
                                    }


                                } catch (Exception e) {
                                    UtilView.showLogCat(TAG, "TaskCompleteListener Exception " + e.toString());
                                }

                            } catch (Exception e) {
                                UtilView.showErrorDialog(getResources().getString(R.string.error_null), activity);
                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.error_null), activity);
                        }
                    }
                }, false);
                task.execute(url, "");
            }
            if (!isInternatePrsenet) {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
            }
        } else {
            UtilView.gotToLogin(activity);
        }
    }

    private void setUpView(UserConfiguratorData userConfiguratorData) {

        cashAccountId = userConfiguratorData.getPosCashAccount();
        bankAccountId = userConfiguratorData.getPosBankAccount();

        userAccountSetupID = userConfiguratorData.getUserAccountSetupID();
        if (userConfiguratorData.getPosBankAccount() != null)
            edBankAccount.setText(userConfiguratorData.getPosBankAccount().toString());

        if (userConfiguratorData.getPosCashAccount() != null)
            edCashAccount.setText(userConfiguratorData.getPosCashAccount().toString());



        UtilView.setupAdapter(activity, spinUnitPrice, getResources().getStringArray(R.array.config_configuration));

        UtilView.setupAdapter(activity, spinDiscount, getResources().getStringArray(R.array.config_configuration));

        UtilView.setupAdapter(activity, spinCess, getResources().getStringArray(R.array.config_configuration));


        UtilView.setupAdapter(activity, spinUOM, getResources().getStringArray(R.array.config_configuration));

        UtilView.setupAdapter(activity, spinTax, getResources().getStringArray(R.array.config_configuration));

        UtilView.setupAdapter(activity, spinDate, getResources().getStringArray(R.array.config_configuration));

        UtilView.setupAdapter(activity, spinPaymentType, getResources().getStringArray(R.array.config_configuration));

        UtilView.setupAdapter(activity, spinQuantity, getResources().getStringArray(R.array.config_configuration));

        UtilView.setupAdapter(activity, spinDescription, getResources().getStringArray(R.array.config_configuration));

        if (userConfiguratorData.getUnitPriceAccess() != null) {

            if (userConfiguratorData.getUnitPriceAccess().equals("Editable")) {
                spinUnitPrice.setSelection(0);
            }
            if (userConfiguratorData.getUnitPriceAccess().equals("Non-editable")) {
                spinUnitPrice.setSelection(1);
            }
        }

        if (userConfiguratorData.getPrintType() != null) {
            if (userConfiguratorData.getPrintType().equals("pos")) {
                radioBtnPOS.setChecked(true);
            }
            if (userConfiguratorData.getPrintType().equals("normal")) {
                radioBtnNormal.setChecked(true);
            }
        }
        if (userConfiguratorData.getDiscountAccess() != null) {
            if (userConfiguratorData.getDiscountAccess().equals("Editable")) {
                spinDiscount.setSelection(0);
            }
            if (userConfiguratorData.getDiscountAccess().equals("Non-editable")) {
                spinDiscount.setSelection(1);
            }
        }
        if (userConfiguratorData.getCessAccess() != null) {
            if (userConfiguratorData.getCessAccess().equals("Editable")) {
                spinCess.setSelection(0);
            }
            if (userConfiguratorData.getCessAccess().equals("Non-editable")) {
                spinCess.setSelection(1);
            }
        }
        if (userConfiguratorData.getUomAccess() != null) {
            if (userConfiguratorData.getUomAccess().equals("Editable")) {
                spinUOM.setSelection(0);
            }
            if (userConfiguratorData.getUomAccess().equals("Non-editable")) {
                spinUOM.setSelection(1);
            }
        }
        if (userConfiguratorData.getTaxAccess() != null) {
            if (userConfiguratorData.getTaxAccess().equals("Editable")) {
                spinTax.setSelection(0);
            }
            if (userConfiguratorData.getTaxAccess().equals("Non-editable")) {
                spinTax.setSelection(1);
            }
        }
        if (userConfiguratorData.getDateAccess() != null) {
            if (userConfiguratorData.getDateAccess().equals("Editable")) {
                spinDate.setSelection(0);
            }
            if (userConfiguratorData.getDateAccess().equals("Non-editable")) {
                spinDate.setSelection(1);
            }
        }
        if (userConfiguratorData.getPaymentTypeAccess() != null) {
            if (userConfiguratorData.getPaymentTypeAccess().equals("Editable")) {
                spinPaymentType.setSelection(0);
            }
            if (userConfiguratorData.getPaymentTypeAccess().equals("Non-editable")) {
                spinPaymentType.setSelection(1);
            }
        }
        if (userConfiguratorData.getQuantityAccess() != null) {
            if (userConfiguratorData.getQuantityAccess().equals("Editable")) {
                spinQuantity.setSelection(0);
            }
            if (userConfiguratorData.getQuantityAccess().equals("Non-editable")) {
                spinQuantity.setSelection(1);
            }
        }
        if (userConfiguratorData.getDescriptionAccess() != null) {
            if (userConfiguratorData.getDescriptionAccess().equals("Editable")) {
                spinDescription.setSelection(0);
            }
            if (userConfiguratorData.getDescriptionAccess().equals("Non-editable")) {
                spinDescription.setSelection(1);
            }
        }
    }


    @OnClick({R.id.edCashAccount, R.id.edBankAccount, R.id.radioBtnNormal, R.id.radioBtnPOS, R.id.btnSave, R.id.btnClose})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edCashAccount:
                dialogCashAccount();
                break;
            case R.id.edBankAccount:
                dialogBankAccount();
                break;
            case R.id.radioBtnNormal:
                break;
            case R.id.radioBtnPOS:
                break;
            case R.id.btnSave:
                saveConfigurator();
                break;
            case R.id.btnClose:
                finish();
                break;
        }
    }

    private void saveConfigurator() {
        String unitPrice = (String) spinUnitPrice.getSelectedItem();
        String discount = (String) spinDiscount.getSelectedItem();
        String cess = (String) spinCess.getSelectedItem();
        String Uom = (String) spinUOM.getSelectedItem();
        String tax = (String) spinTax.getSelectedItem();
        String date = (String) spinDate.getSelectedItem();
        String paymenttype = (String) spinPaymentType.getSelectedItem();
        String quantity = (String) spinQuantity.getSelectedItem();
        String description = (String) spinDescription.getSelectedItem();

        String bankAccount = edBankAccount.getText().toString();
        String cashAccount = edCashAccount.getText().toString();
        String printType = "";
        int selectedId = radioGroup.getCheckedRadioButtonId();
        if (selectedId == radioBtnNormal.getId()) {
            printType = "normal";
        }
        if (selectedId == radioBtnPOS.getId()) {
            printType = "pos";
        }

        UtilView.showLogCat(TAG, unitPrice);
        UtilView.showLogCat(TAG, discount);
        UtilView.showLogCat(TAG, printType);
        UtilView.showLogCat(TAG, "" + bankAccountId);
        UtilView.showLogCat(TAG, "" + cashAccountId);

        if (unitPrice != null && !unitPrice.equals("") &&
                discount != null && !discount.equals("") &&
                printType != null && !printType.equals("") &&
                bankAccountId != null && cashAccountId != null) {


            AddUserConfiguratorData configurData = new AddUserConfiguratorData();
            configurData.setUnitPriceAccess(unitPrice);
            configurData.setQuantityAccess(quantity);
            configurData.setDescriptionAccess(description);
            configurData.setCessAccess(cess);
            configurData.setUomAccess(Uom);
            configurData.setTaxAccess(tax);
            configurData.setDateAccess(date);
            configurData.setDiscountAccess(discount);
            configurData.setPaymentTypeAccess(paymenttype);
            configurData.setUserAccountSetupID(userAccountSetupID);
            configurData.setPosBankAccount(bankAccountId);
            configurData.setPosCashAccount(cashAccountId);
            configurData.setPrintType(printType);

            String url = serverUrl + "/hipos//1/" + Constant.FUNTION_SAVEUSERCONFIGURATOR;

            if (serverUrl != null) {
                isInternatePrsenet = serviceHandler.isConnectingToInternet();
                if (isInternatePrsenet) {
                    // prepare the Request
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
                                        AddUserConfiguratorData data = gson.fromJson(jsonObject.toString(), AddUserConfiguratorData.class);
                                        if (data != null) {
                                            UtilView.showToast(activity, "Configurator Save Suceessfully.");
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
                    task.execute(new Gson().toJson(configurData), url, "");

                }
                if (!isInternatePrsenet) {
                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
                }
            } else {
                UtilView.gotToLogin(activity);
            }
        } else {
            if (unitPrice != null || !unitPrice.equals("")) {
                TextView tv = (TextView) spinUnitPrice.getSelectedView();
                tv.setError(getResources().getString(R.string.err_msg));
            }
            if (discount != null || !discount.equals("")) {
                TextView tv = (TextView) spinDiscount.getSelectedView();
                tv.setError(getResources().getString(R.string.err_msg));
            }
            if (printType.equals("")) {
                UtilView.showToast(activity, "Please select Printtype");
            }
            if (cashAccountId == null) {

                edCashAccount.setError("Please Select Cash Account");
            }

            if (bankAccountId == null) {
                edBankAccount.setError("Please Select Bank Account");
            }
        }
    }

    public void dialogCashAccount() {

        final Dialog dialog = new Dialog(activity);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_configusrator_cashaccount);

        ImageView imgviewClose = dialog.findViewById(R.id.imgviewClose);
        final EditText edSearch = dialog.findViewById(R.id.edSearch);
        ImageView imgviewSearch = dialog.findViewById(R.id.imgviewSearch);
        final ListView lvAccount = dialog.findViewById(R.id.listview);
        Button btnClose = dialog.findViewById(R.id.btnClose);
        final ProgressView pg = dialog.findViewById(R.id.progress_bar);


        imgviewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog != null)
                    dialog.dismiss();
            }
        });
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog != null)
                    dialog.dismiss();
            }
        });

        imgviewSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCashAccountFromserver(edSearch.getText().toString().trim().replace(" ", "%20"), edSearch, pg, lvAccount);
            }
        });


        LayoutInflater inflater = activity.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.adapter_configurator_cashaccount, null, false);

        TextView tvAccountCode = header.findViewById(R.id.tvAccountCode);
        TextView tvAccountName = header.findViewById(R.id.tvAccountName);
        TextView tvAccountType = header.findViewById(R.id.tvAccountType);


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
            lvAccount.addHeaderView(header);

        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(activity, edSearch);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    getCashAccountFromserver(edSearch.getText().toString().trim().replace(" ", "%20"), edSearch, pg, lvAccount);
                }
                return handled;
            }
        });
        if (dialog != null)
            dialog.show();
        getCashAccountFromserver("", edSearch, pg, lvAccount);


        lvAccount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {

                } else {
                    cashAccount = cashAccountList.get(position - 1);
                    if (cashAccount.getAccountName() != null)
                        edCashAccount.setText(cashAccount.getAccountName());
                    cashAccountId = cashAccount.getAccountid();
                    if (dialog != null)
                        dialog.dismiss();
                }
            }
        });
    }

    private void getCashAccountFromserver(String searchAccount, final EditText edSearch, final ProgressView pg, final ListView lvAccount) {

        isInternatePrsenet = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternatePrsenet) {

                cashAccountList = new ArrayList<Account>();
                bankAccountList = new ArrayList<Account>();

                if (pg != null)
                    pg.setVisibility(View.VISIBLE);
                String url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETCASHACCOUNTLIST + "?accountSearchText=" + searchAccount;
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
                                    if (cashAccountList != null) {
                                        cashAccountList.clear();
                                    }
                                    for (int i = 0; i < resultJsonArray.length(); i++) {
                                        JSONObject customerJson = resultJsonArray.getJSONObject(i);
                                        Gson gson = new Gson();
                                        Account account = gson.fromJson(customerJson.toString(), Account.class);
                                        cashAccountList.add(account);
                                    }

                                    if (cashAccountList != null && cashAccountList.size() > 0) {
                                        CashAccount_Adapter accountListAdapter = new CashAccount_Adapter(activity, cashAccountList);
                                        lvAccount.setAdapter(accountListAdapter);
                                        accountListAdapter.notifyDataSetChanged();
                                    }
                                } else {
                                    edSearch.setText("");
                                    UtilView.showErrorDialog(getResources().getString(R.string.nocashaccount_available), activity);
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
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
            }
        } else {
            UtilView.gotToLogin(activity);
        }
    }


    private void dialogBankAccount() {

        final Dialog dialog = new Dialog(activity);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_configusrator_cashaccount);

        ImageView imgviewClose = dialog.findViewById(R.id.imgviewClose);
        final EditText edSearch = dialog.findViewById(R.id.edSearch);
        ImageView imgviewSearch = dialog.findViewById(R.id.imgviewSearch);
        final ListView lvAccount = dialog.findViewById(R.id.listview);
        Button btnClose = dialog.findViewById(R.id.btnClose);
        final ProgressView pg = dialog.findViewById(R.id.progress_bar);


        imgviewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog != null)
                    dialog.dismiss();
            }
        });
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog != null)
                    dialog.dismiss();
            }
        });

        imgviewSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBankAccountFromserver(edSearch.getText().toString().trim().replace(" ", "%20"), edSearch, pg, lvAccount);
            }
        });


        LayoutInflater inflater = activity.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.adapter_configurator_cashaccount, null, false);

        TextView tvAccountCode = header.findViewById(R.id.tvAccountCode);
        TextView tvAccountName = header.findViewById(R.id.tvAccountName);
        TextView tvAccountType = header.findViewById(R.id.tvAccountType);


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
            lvAccount.addHeaderView(header);

        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(activity, edSearch);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    getBankAccountFromserver(edSearch.getText().toString().trim().replace(" ", "%20"), edSearch, pg, lvAccount);
                }
                return handled;
            }
        });
        if (dialog != null)
            dialog.show();
        getBankAccountFromserver("", edSearch, pg, lvAccount);


        lvAccount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {

                } else {
                    bankAccount = bankAccountList.get(position - 1);
                    if (bankAccount.getAccountName() != null)
                        edBankAccount.setText(bankAccount.getAccountName());
                    bankAccountId = bankAccount.getAccountid();
                    if (dialog != null)
                        dialog.dismiss();
                }
            }
        });
    }


    private void getBankAccountFromserver(String searchAccount, final EditText edSearch, final ProgressView pg, final ListView lvAccount) {

        isInternatePrsenet = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternatePrsenet) {
                bankAccountList = new ArrayList<Account>();

                if (pg != null)
                    pg.setVisibility(View.VISIBLE);
                String url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETBANKACCOUNTLIST + "?accountSearchText=" + searchAccount;
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
                                    if (bankAccountList != null) {
                                        bankAccountList.clear();
                                    }
                                    for (int i = 0; i < resultJsonArray.length(); i++) {
                                        JSONObject customerJson = resultJsonArray.getJSONObject(i);
                                        Gson gson = new Gson();
                                        Account account = gson.fromJson(customerJson.toString(), Account.class);
                                        bankAccountList.add(account);
                                    }

                                    if (bankAccountList != null && bankAccountList.size() > 0) {
                                        BankAccount_Adapter accountListAdapter = new BankAccount_Adapter(activity, bankAccountList);
                                        lvAccount.setAdapter(accountListAdapter);
                                        accountListAdapter.notifyDataSetChanged();
                                    }
                                } else {
                                    edSearch.setText("");
                                    UtilView.showErrorDialog(getResources().getString(R.string.nobankaccount_available), activity);
                                }

                            } catch (JSONException e) {
                                UtilView.showLogCat(TAG, "getBankAccountFromserver Exception " + e.toString());
                            }

                        } else {
                            //  showErrorDialog("Getting Error to fetch Customers.");
                        }
                    }
                }, false);
                task.execute(url, "");
            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
            }
        } else {
            UtilView.gotToLogin(activity);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == Constant.RESQUSTCODE_ADDBANK) {

        }

    }

}
