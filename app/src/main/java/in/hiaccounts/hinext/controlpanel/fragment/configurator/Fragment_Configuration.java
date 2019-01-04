package in.hiaccounts.hinext.controlpanel.fragment.configurator;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
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
import butterknife.Unbinder;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.controlpanel.activity.Activity_Config_Bank;
import in.hiaccounts.hinext.controlpanel.adapter.BankAccount_Adapter;
import in.hiaccounts.hinext.controlpanel.adapter.CashAccount_Adapter;
import in.hiaccounts.hinext.controlpanel.adapter.ConfigurModuleAdapter;
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.configuration.Account;
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.configuration.ConfigurationData;
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.configuration.ProjectModuleDTOList;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

import static in.hiaccounts.R.string.response_error;

/**
 * Created by Prateek on 2/9/2017.
 */

public class Fragment_Configuration extends Fragment implements AdapterView.OnItemClickListener {


    public static String TAG = Fragment_Configuration.class.getSimpleName();
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.edCashAccount)
    EditText edCashAccount;
    @BindView(R.id.edBankAccount)
    EditText edBankAccount;
    @BindView(R.id.spinnerUnitPrice)
    Spinner spinnerUnitPrice;
    @BindView(R.id.spinnerDiscount)
    Spinner spinnerDiscount;
    @BindView(R.id.radioBtnNormal)
    RadioButton radioBtnNormal;
    @BindView(R.id.radioBtnPos)
    RadioButton radioBtnPos;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.btnAddBank)
    Button btnAddBank;
    @BindView(R.id.btnSave)
    Button btnSave;
    Unbinder unbinder;

    Activity mActivity;
    @BindView(R.id.id_list_Module)
    ListView idListModule;
    private ServiceHandler serviceHandler;
    private boolean isInternatePrsenet = false;
    private List<Account> cashAccountList;
    private List<Account> bankAccountList;
    private Account cashAccount, bankAccount;
    Long cashAccountId, bankAccountId;
    private String serverUrl;
    List<ProjectModuleDTOList> itemModuleList;

    ConfigurationData configData;

    public static Fragment_Configuration newInstance() {
        Fragment_Configuration fragment = new Fragment_Configuration();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
        serviceHandler = new ServiceHandler(mActivity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Returning the layout file after inflating
        View view = inflater.inflate(R.layout.fragment_configuration, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponentView(view);
        return view;
    }

    private void initComponentView(View view) {
        serverUrl = UtilView.getUrl(mActivity);
        UtilView.setupAdapter(mActivity, spinnerUnitPrice, getResources().getStringArray(R.array.config_configuration));
        UtilView.setupAdapter(mActivity, spinnerDiscount, getResources().getStringArray(R.array.config_configuration));
        if (serverUrl != null) {
            getConfigurationDataFromServer();

        } else {
            UtilView.getUrl(mActivity);
        }
    }
    private void getPageLoadItemData() {

        String url = serverUrl + "/hipos/1/" + Constant.FUNTION_CONFIGMODULELIST;
        isInternatePrsenet = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternatePrsenet) {
                // UtilView.showProgessBar(mActivity, progressBar);
                GetDataTask getDataTask = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        //     UtilView.hideProgessBar(mActivity, progressBar);
                        if (result != null) {
                            if (result.toString().equals(getResources().getString(R.string.response200_sessionLost))) {
                                UtilView.gotToLogin(mActivity);
                            } else {
                                Gson gson = new Gson();
                                itemModuleList = new ArrayList<>();

                                try {
                                    JSONArray jsonArray = new JSONArray(result.toString());

                                    if (jsonArray != null) {
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                                            ProjectModuleDTOList pageItemData = gson.fromJson(jsonObject.toString(), ProjectModuleDTOList.class);
                                            itemModuleList.add(pageItemData);
                                        }

                                        if (itemModuleList != null && itemModuleList.size() > 0) {
                                            if (configData!=null) {
                                                setupPageItemData();
                                                setUpView(configData);
                                            }
                                        }

                                    }


                                } catch (JSONException e) {
                                    e.printStackTrace();
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

    private void setupPageItemData() {
        ConfigurModuleAdapter adapter = new ConfigurModuleAdapter(mActivity,itemModuleList);
        idListModule.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        idListModule.setOnItemClickListener(this);
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        int viewId=view.getId();
        ProjectModuleDTOList items = itemModuleList.get(i);
        if (viewId== R.id.id_moduleCheck){
            CheckBox checkBox=view.findViewById(R.id.id_moduleCheck);
            items.setStatus(checkBox.isChecked());
        }
    }
    private void getConfigurationDataFromServer() {
        String url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETCONFIGURATIONDATA;
        UtilView.showProgessBar(mActivity, progressBar);
        PostDataTask task = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
            @Override
            public void onTaskComplete(String result) {
                HideUtil.init(mActivity);
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
                            configData = gson.fromJson(jsonObject.toString(), ConfigurationData.class);
                            if (configData != null) {
                                getPageLoadItemData();

                            }
                        } catch (Exception e) {
                            UtilView.showLogCat(TAG, "getBankAccountFromserver Exception " + e.toString());
                        }
                    }
                } else {
                    UtilView.showErrorDialog("Some Error", mActivity);
                }
            }
        }, false);
        task.execute(new Gson().toString(), url, "");
    }

    private void setUpView(ConfigurationData configData) {
        cashAccountId = configData.getCashAccountId();
        bankAccountId = configData.getBankAccountId();
        if (configData.getBankAccountName() != null)
            edBankAccount.setText(configData.getBankAccountName());

        if (configData.getCashAccountName() != null)
            edCashAccount.setText(configData.getCashAccountName());

        if (configData.getUnitPrice() != null) {
            if (configData.getUnitPrice().equals("Editable")) {
                spinnerUnitPrice.setSelection(0);
            }
            if (configData.getUnitPrice().equals("Non-editable")) {
                spinnerUnitPrice.setSelection(1);
            }
        }
        if (configData.getDiscount() != null) {
            if (configData.getDiscount().equals("Editable")) {
                spinnerDiscount.setSelection(0);
            }
            if (configData.getDiscount().equals("Non-editable")) {
                spinnerDiscount.setSelection(1);
            }
        }

        if (configData.getPrintType() != null) {
            if (configData.getPrintType().equals("pos")) {
                radioBtnPos.setChecked(true);
            }
            if (configData.getPrintType().equals("normal")) {
                radioBtnNormal.setChecked(true);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.edCashAccount, R.id.edBankAccount, R.id.btnAddBank, R.id.btnSave})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edCashAccount:
                dialogCashAccount();
                break;
            case R.id.edBankAccount:
                dialogBankAccount();

                break;
            case R.id.btnAddBank:
                Intent intent = new Intent(mActivity, Activity_Config_Bank.class);
                intent.putExtra("callingFor", Constant.CALL_ADDBANK);
                startActivityForResult(intent, Constant.RESQUSTCODE_ADDBANK);

                break;
            case R.id.btnSave:
                UtilView.showLogCat(TAG,"Module Item "+new Gson().toJson(itemModuleList));

                saveConfiguration();
                break;
        }
    }

    private void saveConfiguration() {
        String unitPrice = (String) spinnerUnitPrice.getSelectedItem();
        String discount = (String) spinnerDiscount.getSelectedItem();
        String printType = "";
        int selectedId = radioGroup.getCheckedRadioButtonId();
        if (selectedId == radioBtnNormal.getId()) {
            printType = "normal";
        }
        if (selectedId == radioBtnPos.getId()) {
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


            ConfigurationData configurationData = new ConfigurationData();
            configurationData.setUnitPrice(unitPrice);
            configurationData.setDiscount(discount);
            configurationData.setPrintType(printType);
            configurationData.setPosBankAccountForSaving(bankAccountId);
            configurationData.setPosCashAccountForSaving(cashAccountId);
            configurationData.setProjectModuleDTOList(itemModuleList);
            String url = serverUrl + "/hipos//1/" + Constant.FUNTION_SAVECONFIGURATION;

            if (serverUrl != null) {
                isInternatePrsenet = serviceHandler.isConnectingToInternet();
                if (isInternatePrsenet) {
                    // prepare the Request
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
                                    Gson gson = new Gson();
                                    try {
                                        JSONObject jsonObject = new JSONObject(result.toString());
                                        ConfigurationData data = gson.fromJson(jsonObject.toString(), ConfigurationData.class);
                                        if (data != null) {
                                            UtilView.showToast(mActivity, "Configuration Save Suceessfully.");
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
                    task.execute(new Gson().toJson(configurationData), url, "");

                }
                if (!isInternatePrsenet) {
                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                }
            } else {
                UtilView.gotToLogin(mActivity);
            }
        } else {
            if (unitPrice != null || !unitPrice.equals("")) {
                TextView tv = (TextView) spinnerUnitPrice.getSelectedView();
                tv.setError(getResources().getString(R.string.err_msg));
            }
            if (discount != null || !discount.equals("")) {
                TextView tv = (TextView) spinnerDiscount.getSelectedView();
                tv.setError(getResources().getString(R.string.err_msg));
            }
            if (printType.equals("")) {
                UtilView.showToast(mActivity, "Please select Printtype");
            }
            if (cashAccountId == null) {

                edCashAccount.setError("Please Select Cash Account");
            }

            if (bankAccountId == null) {
                edBankAccount.setError("Please Select Bank Account");
            }
        }


    }

    private void dialogCashAccount() {

        final Dialog dialog = new Dialog(mActivity);
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


        LayoutInflater inflater = mActivity.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.adapter_configurator_cashaccount, null, false);

        TextView tvAccountCode = header.findViewById(R.id.tvAccountCode);
        TextView tvAccountName = header.findViewById(R.id.tvAccountName);
        TextView tvAccountType = header.findViewById(R.id.tvAccountType);


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

        if (lvAccount != null)
            lvAccount.addHeaderView(header);

        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(mActivity, edSearch);
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
                GetDataTask task = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        if (pg != null)
                            pg.setVisibility(View.GONE);
                        HideUtil.init(mActivity);
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
                                        CashAccount_Adapter accountListAdapter = new CashAccount_Adapter(mActivity, cashAccountList);
                                        lvAccount.setAdapter(accountListAdapter);
                                        accountListAdapter.notifyDataSetChanged();
                                    }
                                } else {
                                    edSearch.setText("");
                                    UtilView.showErrorDialog(getResources().getString(R.string.nocashaccount_available), mActivity);
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
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
            }
        } else {
            UtilView.gotToLogin(mActivity);
        }
    }


    private void dialogBankAccount() {

        final Dialog dialog = new Dialog(mActivity);
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


        LayoutInflater inflater = mActivity.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.adapter_configurator_cashaccount, null, false);

        TextView tvAccountCode = header.findViewById(R.id.tvAccountCode);
        TextView tvAccountName = header.findViewById(R.id.tvAccountName);
        TextView tvAccountType = header.findViewById(R.id.tvAccountType);


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

        if (lvAccount != null)
            lvAccount.addHeaderView(header);

        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(mActivity, edSearch);
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
                GetDataTask task = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        if (pg != null)
                            pg.setVisibility(View.GONE);
                        HideUtil.init(mActivity);
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
                                        BankAccount_Adapter accountListAdapter = new BankAccount_Adapter(mActivity, bankAccountList);
                                        lvAccount.setAdapter(accountListAdapter);
                                        accountListAdapter.notifyDataSetChanged();
                                    }
                                } else {
                                    edSearch.setText("");
                                    UtilView.showErrorDialog(getResources().getString(R.string.nobankaccount_available), mActivity);
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
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
            }
        } else {
            UtilView.gotToLogin(mActivity);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == Constant.RESQUSTCODE_ADDBANK) {

        }

    }


}
