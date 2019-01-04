package in.hiaccounts.hinext.customer.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;
import com.yinglan.keyboard.HideUtil;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hiaccounts.R;
import in.hiaccounts.hinext.customer.model.CountryDTOList;
import in.hiaccounts.hinext.customer.model.CurrencyDTOList;
import in.hiaccounts.hinext.customer.model.Customer;
import in.hiaccounts.hinext.customer.model.StateDTOList;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Admin on 8/3/2017.
 */

public class Activity_EditCustomer extends AppCompatActivity {


    final StateDTOList[] state = new StateDTOList[1];
    final CountryDTOList[] country = new CountryDTOList[1];
    final CurrencyDTOList[] currency = new CurrencyDTOList[1];


    private static String TAG = Activity_EditCustomer.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.switchHiconnect)
    Switch switchHiconnect;
    @BindView(R.id.edCustomerName)
    EditText edCustomerName;
    @BindView(R.id.atcvCustomerName)
    AutoCompleteTextView atcvCustomerName;
    @BindView(R.id.edCusotmerEmail)
    EditText edCusotmerEmail;
    @BindView(R.id.edContact)
    EditText edContact;
    @BindView(R.id.edAddress)
    EditText edAddress;
    @BindView(R.id.edGSTIN)
    EditText edGSTIN;
    @BindView(R.id.spinnerState)
    Spinner spinnerState;
    @BindView(R.id.edPersonIncharge)
    EditText edPersonIncharge;
    @BindView(R.id.spinnerCountry)
    Spinner spinnerCountry;
    @BindView(R.id.spinnerCurrency)
    Spinner spinnerCurrency;
    @BindView(R.id.edPanNo)
    EditText edPanNo;
    @BindView(R.id.edAccountNo)
    EditText edAccountNo;
    @BindView(R.id.edBankName)
    EditText edBankName;
    @BindView(R.id.edBankBranch)
    EditText edBankBranch;
    @BindView(R.id.edIFSCcode)
    EditText edIFSCcode;
    @BindView(R.id.edWebsite)
    EditText edWebsite;
    @BindView(R.id.llMoreOrLess)
    LinearLayout llMoreOrLess;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.btnMore)
    Button btnMore;
    @BindView(R.id.btnLess)
    Button btnLess;
    @BindView(R.id.rlContentView)
    RelativeLayout rlContentView;

    private Activity mActivity;
    private ServiceHandler serviceHandler;
    Boolean isInternetPresent = false;
    SharedPreference sharedPreference;
    Long customerId;
    private String serverUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcustomer);
        ButterKnife.bind(this);

        initComponenets();

    }

    private void initComponenets() {
        ButterKnife.bind(this);
        mActivity = this;
        serverUrl = UtilView.getUrl(mActivity);
        sharedPreference = SharedPreference.getInstance(mActivity);
        serviceHandler = new ServiceHandler(mActivity);

        String callingFrom = getIntent().getStringExtra("callingFrom");
        customerId = getIntent().getLongExtra("customerId", 0);
        if (callingFrom != null)
            toolbar.setTitle(callingFrom);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        HideUtil.init(mActivity);

        getPageDataFromServer();

        edCusotmerEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edCustomerName.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void getPageDataFromServer() {
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("searchCustomerText", customerId);
                } catch (JSONException je) {

                }
                UtilView.showProgessBar(mActivity, progressBar);
                String url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETEDITCUSTOMER + "?searchCustomerText=" + customerId;
                PostDataTask task = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(mActivity, progressBar);
                        if (result != null) {
                            try {
                                JSONObject jsonObject = new JSONObject(result.toString());
                                Gson gson = new Gson();
                                Customer customerData = gson.fromJson(jsonObject.toString(), Customer.class);
                                if (customerData != null) {
                                    setUpPageData(customerData);
                                } else {
//                                rlContentView.setVisibility(View.INVISIBLE);
                                }

                            } catch (Exception ex) {
                                UtilView.showErrorDialog(getResources().getString(R.string.error_null), mActivity);
                            }
                        }

                    }
                }, false);
                task.execute(jsonObject.toString(), url, "");
            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
            }
        } else {
            UtilView.gotToLogin(mActivity);
        }
    }

    private void setUpPageData(Customer customerData) {

        edCustomerName.setFocusable(false);
        edCustomerName.setFocusableInTouchMode(false);
        edCustomerName.setBackgroundColor(getResources().getColor(R.color.colorGrey));

        if (customerData != null) {

            if (customerData.getStateDTOList() != null && customerData.getStateDTOList().size() > 0) {
                UtilView.setupCustomerStateAdapter(mActivity, spinnerState, customerData.getStateDTOList());
            }

            if (customerData.getCurrencyDTOList() != null && customerData.getCurrencyDTOList().size() > 0) {
                UtilView.setupCustomerCountryAdapter(mActivity, spinnerCountry, customerData.getCountryDTOList());
            }

            if (customerData.getCountryDTOList() != null && customerData.getCountryDTOList().size() > 0) {
                UtilView.setupCustomerCurrencyAdapter(mActivity, spinnerCurrency, customerData.getCurrencyDTOList());
            }

            if (customerData.getCustomerName() != null)
                edCustomerName.setText(customerData.getCustomerName());

            if (customerData.getCustomerEmail() != null)
                edCusotmerEmail.setText(customerData.getCustomerEmail());

            if (customerData.getCustomerNumber() != null)
                edContact.setText(customerData.getCustomerNumber());

            if (customerData.getBillingAddress() != null)
                edAddress.setText(customerData.getBillingAddress());

            if (customerData.getGstIn() != null)
                edGSTIN.setText(customerData.getGstIn());

            if (customerData.getPersonIncharge() != null)
                edPersonIncharge.setText(customerData.getPersonIncharge());

            if (customerData.getAccountNo() != null)
                edAccountNo.setText(customerData.getAccountNo());

            if (customerData.getPanNO() != null)
                edPanNo.setText(customerData.getPanNO());

            if (customerData.getBankName() != null)
                edBankName.setText(customerData.getBankName());

            if (customerData.getBranchName() != null)
                edBankBranch.setText(customerData.getBranchName());

            if (customerData.getiFSCCode() != null)
                edIFSCcode.setText(customerData.getiFSCCode());

            if (customerData.getWebsite() != null)
                edWebsite.setText(customerData.getWebsite());


            if (customerData.getStateID() != null) {

                for (int i = 0; i < customerData.getStateDTOList().size(); i++) {

                    String stateId = "" + customerData.getStateDTOList().get(i).getId();
                    try {
                        if (Long.parseLong(stateId) == (customerData.getStateID())) {
                            spinnerState.setSelection(i);
                        }
                    } catch (NumberFormatException ne) {

                    }

                }
            }
            if (customerData.getCountryID() != null) {

                for (int i = 0; i < customerData.getCountryDTOList().size(); i++) {

                    String countryId = "" + customerData.getCountryDTOList().get(i).getCountryId();
                    try {
                        if (Long.parseLong(countryId) == (customerData.getCountryID())) {
                            spinnerCountry.setSelection(i);
                        }
                    } catch (NumberFormatException ne) {

                    }

                }
            }
            if (customerData.getCurrencyId() != null) {

                for (int i = 0; i < customerData.getCurrencyDTOList().size(); i++) {

                    String currencyId = "" + customerData.getCurrencyDTOList().get(i).getCurrencyId();
                    try {
                        if (Long.parseLong(currencyId) == (customerData.getCurrencyId())) {
                            spinnerCurrency.setSelection(i);
                        }
                    } catch (NumberFormatException ne) {

                    }

                }
            }
        }
        spinnerState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                state[0] = (StateDTOList) adapterView.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                country[0] = (CountryDTOList) adapterView.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                currency[0] = (CurrencyDTOList) adapterView.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    @OnClick({R.id.btnSave, R.id.btnMore, R.id.btnLess})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSave:
                if (serverUrl != null) {
                    addCustomer();
                } else {
                    UtilView.gotToLogin(mActivity);
                }
                break;
            case R.id.btnMore:
                visibilityOn();
                break;
            case R.id.btnLess:
                visibilityOff();
                break;
        }
    }

    private void addCustomer() {

        String name = edCustomerName.getText().toString().trim();
        String email = edCusotmerEmail.getText().toString().trim();
        String contactNumber = edContact.getText().toString().trim();
        String address = edAddress.getText().toString().trim();
        String gstin = edGSTIN.getText().toString().trim();
        String personincharge = edPersonIncharge.getText().toString().trim();
        String panno = edPanNo.getText().toString().trim();
        String accountnumber = edAccountNo.getText().toString().trim();
        String bankName = edBankName.getText().toString().trim();
        String bankBranch = edBankBranch.getText().toString().trim();
        String ifsccode = edIFSCcode.getText().toString().trim();
        String website = edWebsite.getText().toString().trim();


        if (name != null && !name.equals("") && state[0] != null && currency[0] != null && country[0] != null) {

            Customer customer = new Customer();

            customer.setCustomerName(name);
            customer.setCustomerEmail(email);
            customer.setCustomerContact(contactNumber);
            customer.setCustomerAddress(address);
            customer.setGstIn(gstin);
            customer.setPersonIncharge(personincharge);
            customer.setPanNumber(panno);
            customer.setAccountNo(accountnumber);
            customer.setBankName(bankName);
            customer.setBranchName(bankBranch);
            customer.setiFSCCode(ifsccode);
            customer.setWebsite(website);
            customer.setFromRegComp("");
            customer.setToRegComp("");
            customer.setNotificationFlag("");
            customer.setCompanyRegNo("");
            customer.setCustomerId(customerId);

            if (state[0] != null) {
                customer.setStateID(state[0].getId());
            }
            if (currency[0] != null) {
                customer.setCurrencyId(currency[0].getCurrencyId());
            }
            if (country[0] != null) {
                customer.setCountryID(country[0].getCountryId());
            }

            final Gson gson = new Gson();

            String url = serverUrl + "/hipos//0/" + Constant.FUNTION_UPDATECUSTOMER;
            UtilView.showProgessBar(mActivity, progressBar);
            PostDataTask postDataTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                @Override
                public void onTaskComplete(String result) {
                    UtilView.hideProgessBar(mActivity, progressBar);
                    HideUtil.init(mActivity);
                    if (result != null) {
                        if (result.equals(getResources().getString(R.string.error_rsonsecode204))) {
                            UtilView.showToast(mActivity, "Customer update successfully.");
                            Intent returnIntent = new Intent();
                            mActivity.setResult(Activity.RESULT_OK, returnIntent);
                            mActivity.finish();
                        } else {
                            UtilView.showToast(mActivity, "Customer not update successfully. Please try again.");
                        }
                    }
                }
            }, false);
            postDataTask.execute(gson.toJson(customer).toString(), url, "");

        } else {
            if (name == null || name.equals("")) {
                edCustomerName.setError(getResources().getString(R.string.error_custmorname));
            }
            if (state[0] == null) {
                TextView tv = (TextView) spinnerState.getSelectedView();
                tv.setError("Please select State");
                UtilView.showToast(mActivity, "Please select State");
            }

            if (country[0] == null) {
                TextView tv = (TextView) spinnerCountry.getSelectedView();
                tv.setError("Please select Country");
                UtilView.showToast(mActivity, "Please select Country");
                visibilityOn();
            }

            if (currency[0] == null) {
                TextView tv = (TextView) spinnerCurrency.getSelectedView();
                tv.setError("Please select Currency");
                UtilView.showToast(mActivity, "Please select Currency");
                visibilityOn();
            }
        }


    }

    private void visibilityOff() {
        btnLess.setVisibility(View.INVISIBLE);
        btnMore.setVisibility(View.VISIBLE);
        llMoreOrLess.setVisibility(View.INVISIBLE);
    }

    private void visibilityOn() {
        btnLess.setVisibility(View.VISIBLE);
        btnMore.setVisibility(View.INVISIBLE);
        llMoreOrLess.setVisibility(View.VISIBLE);
    }

}
