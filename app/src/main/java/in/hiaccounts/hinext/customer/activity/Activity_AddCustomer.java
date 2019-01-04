package in.hiaccounts.hinext.customer.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
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

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hiaccounts.R;
import in.hiaccounts.hinext.customer.model.CountryDTOList;
import in.hiaccounts.hinext.customer.model.CurrencyDTOList;
import in.hiaccounts.hinext.customer.model.Customer;
import in.hiaccounts.hinext.customer.model.CustomerPageData;
import in.hiaccounts.hinext.customer.model.StateDTOList;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Admin on 8/3/2017.
 */

public class Activity_AddCustomer extends AppCompatActivity {


    final StateDTOList[] state = new StateDTOList[1];
    final CountryDTOList[] country = new CountryDTOList[1];
    final CurrencyDTOList[] currency = new CurrencyDTOList[1];


    private static String TAG = Activity_AddCustomer.class.getSimpleName();
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
    @BindView(R.id.spinnerStatus)
    Spinner spinnerStatus;

    private Activity mActivity;
    private ServiceHandler serviceHandler;
    Boolean isInternetPresent = false;
    SharedPreference sharedPreference;
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
        if (callingFrom != null) {
            if (callingFrom.equals("Restra")) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            } else {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }
        }
        UtilView.setupAdapter(mActivity, spinnerStatus, getResources().getStringArray(R.array.config_project_status));

        toolbar.setTitle("Add Customer");
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
                UtilView.showProgessBar(mActivity, progressBar);
                String url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETCUSTOMERPAGEDATA;
                GetDataTask task = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(mActivity, progressBar);

                        if (result != null) {

                            try {
                                JSONObject jsonObject = new JSONObject(result.toString());
                                Gson gson = new Gson();
                                CustomerPageData pageData = gson.fromJson(jsonObject.toString(), CustomerPageData.class);
                                if (pageData != null) {

                                    setUpPageData(pageData);

                                } else {
//                                rlContentView.setVisibility(View.INVISIBLE);
                                }

                            } catch (Exception ex) {

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

    private void setUpPageData(CustomerPageData pageData) {
        if (pageData != null) {
            if (pageData.getStateDTOList() != null)
                UtilView.setupCustomerStateAdapter(mActivity, spinnerState, pageData.getStateDTOList());
            if (pageData.getCountryDTOList() != null)
                UtilView.setupCustomerCountryAdapter(mActivity, spinnerCountry, pageData.getCountryDTOList());
            if (pageData.getCurrencyDTOList() != null)
                UtilView.setupCustomerCurrencyAdapter(mActivity, spinnerCurrency, pageData.getCurrencyDTOList());


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
            customer.setCustStatus((String) spinnerStatus.getSelectedItem());
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

            String url = serverUrl + "/hipos//0/" + Constant.FUNTION_ADDCUSTOMER;

            UtilView.showProgessBar(mActivity, progressBar);
            PostDataTask postDataTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                @Override
                public void onTaskComplete(String result) {
                    UtilView.hideProgessBar(mActivity, progressBar);
                    HideUtil.init(mActivity);
                    if (result != null) {
                        try {
                            JSONObject jsonObject = new JSONObject(result.toString());
                            Customer customer = gson.fromJson(jsonObject.toString(), Customer.class);
                            if (customer != null) {

                                UtilView.showToast(mActivity, "Customer " + customer.getCustomerName() + " add successfully.");

                                Intent returnIntent = new Intent();
                                returnIntent.putExtra("customer", customer);
                                mActivity.setResult(Activity.RESULT_OK, returnIntent);
                                mActivity.finish();
                            }


                        } catch (Exception ex) {
                            UtilView.showErrorDialog(getResources().getString(R.string.error_null), mActivity);
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
                visibilityOn();
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
        if (btnLess != null)
            btnLess.setVisibility(View.INVISIBLE);
        if (btnMore != null)
            btnMore.setVisibility(View.VISIBLE);
        if (llMoreOrLess != null)
            llMoreOrLess.setVisibility(View.INVISIBLE);
    }

    private void visibilityOn() {
        if (btnLess != null)
            btnLess.setVisibility(View.VISIBLE);
        if (btnMore != null)
            btnMore.setVisibility(View.INVISIBLE);
        if (llMoreOrLess != null)
            llMoreOrLess.setVisibility(View.VISIBLE);
    }

}
