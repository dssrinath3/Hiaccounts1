package in.hiaccounts.hinext.supplier.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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
import in.hiaccounts.hinext.supplier.model.AddSupplier;
import in.hiaccounts.hinext.supplier.model.CountryDTOList;
import in.hiaccounts.hinext.supplier.model.CurrencyDTOList;
import in.hiaccounts.hinext.supplier.model.GetSupplier;
import in.hiaccounts.hinext.supplier.model.StateDTOList;
import in.hiaccounts.hinext.supplier.model.SupplierPageDatum;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 8/3/2017.
 */

public class Activity_AddSupplier extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.switchHiconnect)
    Switch switchHiconnect;
    @BindView(R.id.edSupplierName)
    EditText edSupplierName;
    @BindView(R.id.atcvSuuplierName)
    AutoCompleteTextView atcvSuuplierName;
    @BindView(R.id.edSupplierEmail)
    EditText edSupplierEmail;
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
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    private Activity mActivity;
    private static String TAG = Activity_AddSupplier.class.getSimpleName();
    private ServiceHandler serviceHandler;
    private Boolean isInternetPresent = false;
    private SharedPreference sharedPreference;
    private String callingFrom,serverUrl;
    private final StateDTOList[] state = new StateDTOList[1];
    private final CountryDTOList[] country = new CountryDTOList[1];
    private final CurrencyDTOList[] currency = new CurrencyDTOList[1];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addsupplier);
        initComponentView();
    }

    private void initComponentView() {
        ButterKnife.bind(this);
        mActivity = this;
        serverUrl=UtilView.getUrl(mActivity);
        sharedPreference = SharedPreference.getInstance(mActivity);
        serviceHandler = new ServiceHandler(mActivity);
        callingFrom = getIntent().getStringExtra("callingFrom");
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
        edSupplierName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                edSupplierName.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void getPageDataFromServer() {
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl!=null) {
            if (isInternetPresent) {
                UtilView.showProgessBar(mActivity, progressBar);
                String url = serverUrl + "/hipos//0/" + Constant.FUNTION_PURCHASEADDSUPPLIER;
                GetDataTask task = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(mActivity, progressBar);
                        if (result != null) {
                            try {
                                JSONObject jsonObject = new JSONObject(result.toString());
                                Gson gson = new Gson();
                                SupplierPageDatum pageData = gson.fromJson(jsonObject.toString(), SupplierPageDatum.class);
                                if (pageData != null) {
                                    setUpPageData(pageData);
                                } else {
//                                rlContentView.setVisibility(View.INVISIBLE);
                                }
                            } catch (Exception ex) {
                                UtilView.showErrorDialog(getResources().getString(R.string.response_error), mActivity);
                            }
                        }
                    }
                }, false);
                task.execute(url, "");
            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
            }
        }else {
            UtilView.gotToLogin(mActivity);
        }
    }

    private void setUpPageData(SupplierPageDatum pageData) {

        if (pageData != null) {
            if (pageData.getStateDTOList() != null) {
                UtilView.setupSupplierStateAdapter(mActivity, spinnerState, pageData.getStateDTOList());
                UtilView.setupSupplierCountryAdapter(mActivity, spinnerCountry, pageData.getCountryDTOList());
                UtilView.setupSupplierCurrencyAdapter(mActivity, spinnerCurrency, pageData.getCurrencyDTOList());
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
    }

    @OnClick({R.id.btnSave, R.id.btnMore, R.id.btnLess})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSave:
                addSupplier();
                break;
            case R.id.btnMore:
                visibilityOn();
                break;
            case R.id.btnLess:
                visibilityOff();
                break;
        }
    }

    private void addSupplier() {

        String name = edSupplierName.getText().toString().trim();
        String email = edSupplierEmail.getText().toString().trim();
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

            AddSupplier supplier = new AddSupplier();
            supplier.setSupplierName(name);
            supplier.setSupplierEmail(email);
            supplier.setSupplierPhoneNumber1(contactNumber);
            supplier.setSupplierBillingAddress(address);
            supplier.setGstIn(gstin);
            supplier.setPersonIncharge(personincharge);
            supplier.setPanNumber(panno);
            supplier.setAccountNo(accountnumber);
            supplier.setBankName(bankName);
            supplier.setBranchName(bankBranch);
            supplier.setiFSCCode(ifsccode);
            supplier.setWebsite(website);
            supplier.setFromRegComp("");
            supplier.setToRegComp("");
            supplier.setNotificationFlag("");
            supplier.setCompanyRegNo("");

            if (state[0] != null) {
                supplier.setStateID(state[0].getId());
            }
            if (currency[0] != null) {
                supplier.setCurrencyId(currency[0].getCurrencyId());
            }
            if (country[0] != null) {
                supplier.setCountryID(country[0].getCountryId());
            }

            final Gson gson = new Gson();

            String url = serverUrl + "/hipos//0/" + Constant.FUNTION_ADDSUPPLIER;
            UtilView.showProgessBar(mActivity, progressBar);

            if (serverUrl!=null) {
                isInternetPresent=serviceHandler.isConnectingToInternet();
                if (isInternetPresent) {
                    PostDataTask postDataTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                        @Override
                        public void onTaskComplete(String result) {
                            UtilView.hideProgessBar(mActivity, progressBar);
                            HideUtil.init(mActivity);
                            if (result != null) {
                                try {
                                    JSONObject jsonObject = new JSONObject(result.toString());
                                    GetSupplier supplier = gson.fromJson(jsonObject.toString(), GetSupplier.class);
                                    if (supplier != null) {
                                        Intent returnIntent = new Intent();
                                        returnIntent.putExtra("supplier", supplier);
                                        mActivity.setResult(Activity.RESULT_OK, returnIntent);
                                        mActivity.finish();
                                    }
                                } catch (Exception ex) {
                                    UtilView.showErrorDialog(getResources().getString(R.string.response_error), mActivity);
                                }
                            }
                        }
                    }, false);
                    postDataTask.execute(gson.toJson(supplier).toString(), url, "");
                }
                else {
                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status),mActivity);
                }
            }else {
                UtilView.gotToLogin(mActivity);
            }

        } else {
            if (name == null || name.equals("")) {
                edSupplierName.setError(getResources().getString(R.string.error_custmorname));
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
