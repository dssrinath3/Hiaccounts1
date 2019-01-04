package in.hiaccounts.hinext.contact.activity;

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

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hiaccounts.R;
import in.hiaccounts.hinext.contact.model.Contact;
import in.hiaccounts.hinext.contact.model.ContactDatum;
import in.hiaccounts.hinext.contact.model.CountryDTOList;
import in.hiaccounts.hinext.contact.model.CurrencyDTOList;
import in.hiaccounts.hinext.contact.model.StateDTOList;
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

public class Activity_AddContact extends AppCompatActivity {


    private static String TAG = Activity_AddContact.class.getSimpleName();
    final StateDTOList[] state = new StateDTOList[1];
    final CountryDTOList[] country = new CountryDTOList[1];
    final CurrencyDTOList[] currency = new CurrencyDTOList[1];
    String serverUrl = "";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.switchHiconnect)
    Switch switchHiconnect;
    @BindView(R.id.edName)
    EditText edName;
    @BindView(R.id.atcvName)
    AutoCompleteTextView atcvName;
    @BindView(R.id.edEmail)
    EditText edEmail;
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
    Boolean isInternetPresent = false;
    SharedPreference sharedPreference;
    long contactId;
    String callingFrom = "";
    private Activity mActivity;
    private ServiceHandler serviceHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcontact);
        ButterKnife.bind(this);

        initComponenets();

    }

    private void initComponenets() {
        ButterKnife.bind(this);
        mActivity = this;
        sharedPreference = SharedPreference.getInstance(mActivity);
        serviceHandler = new ServiceHandler(mActivity);

        serverUrl = UtilView.getUrl(mActivity);
        callingFrom = getIntent().getStringExtra("callingFrom");
        contactId = getIntent().getLongExtra("contactId", 0);
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

        if (serverUrl != null) {
            getPageDataFromServer();
        } else {
            UtilView.gotToLogin(mActivity);
        }

        edName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                edName.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void getPageDataFromServer() {

        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (isInternetPresent) {
            UtilView.showProgessBar(mActivity, progressBar);

            String url = "";

            if (contactId != 0) {
                url = serverUrl + "/hipos//0/getEditOtherContact?searchOtherContactText=" + contactId;
            } else {
                url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETCONTACTPAGEDAT;
            }
            GetDataTask task = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                @Override
                public void onTaskComplete(String result) {
                    UtilView.hideProgessBar(mActivity, progressBar);
                    if (result != null) {
                        try {
                            JSONObject jsonObject = new JSONObject(result.toString());
                            Gson gson = new Gson();
                            ContactDatum pageData = gson.fromJson(jsonObject.toString(), ContactDatum.class);
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
    }

    private void setUpPageData(ContactDatum pageData) {


        if (pageData != null) {

            if (pageData.getStateDTOList() != null) {

                if (spinnerState != null)
                    UtilView.setupContactStateAdapter(mActivity, spinnerState, pageData.getStateDTOList());
                if (spinnerCountry != null)
                    UtilView.setupContactCountryAdapter(mActivity, spinnerCountry, pageData.getCountryDTOList());
                if (spinnerCurrency != null)
                    UtilView.setupContactCurrencyAdapter(mActivity, spinnerCurrency, pageData.getCurrencyDTOList());

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


            if (contactId != 0) {

                if (edName != null) {
                    if (pageData.getFullName() != null)
                        edName.setText(pageData.getFullName());
                }

                if (edEmail != null) {
                    if (pageData.getEmail() != null)
                        edEmail.setText(pageData.getEmail());
                }

                if (edContact != null) {
                    if (pageData.getContactNumber() != null)
                        edContact.setText(pageData.getContactNumber());
                }

                if (edAddress != null) {
                    if (pageData.getAddress() != null)
                        edAddress.setText(pageData.getAddress());
                }

                if (edGSTIN != null) {
                    if (pageData.getGstCode() != null)
                        edGSTIN.setText(pageData.getGstCode());
                }

                if (edPersonIncharge != null) {
                    if (pageData.getPersonIncharge() != null)
                        edPersonIncharge.setText(pageData.getPersonIncharge());
                }

                if (edPanNo != null) {
                    if (pageData.getPanNO() != null)
                        edPanNo.setText(pageData.getPanNO());
                }

                if (edAccountNo != null) {
                    if (pageData.getAccountNo() != null)
                        edAccountNo.setText(pageData.getAccountNo());
                }

                if (edBankName != null) {
                    if (pageData.getBankName() != null)
                        edBankName.setText(pageData.getBankName());
                }

                if (edBankBranch != null) {
                    if (pageData.getBranchName() != null)
                        edBankBranch.setText(pageData.getBranchName());
                }

                if (edIFSCcode != null) {
                    if (pageData.getiFSCCode() != null)
                        edIFSCcode.setText(pageData.getiFSCCode());
                }

                if (edWebsite != null) {
                    if (pageData.getWebsite() != null)
                        edWebsite.setText(pageData.getWebsite());
                }

                if (pageData.getCurrencyDTOList() != null && pageData.getCurrencyDTOList().size() > 0) {
                    for (int i = 0; i < pageData.getCurrencyDTOList().size(); i++) {
                        try {
                            if (Long.parseLong(pageData.getCurrency()) == pageData.getStateDTOList().get(i).getId()) {
                                spinnerCurrency.setSelection(i);
                            }
                        } catch (NumberFormatException ne) {
                            UtilView.showLogCat(TAG, "Currency Id Format " + ne.getMessage());
                        }
                    }
                }
                if (pageData.getCountryDTOList() != null && pageData.getCountryDTOList().size() > 0) {
                    for (int i = 0; i < pageData.getCountryDTOList().size(); i++) {
                        try {
                            if (Long.parseLong(pageData.getCountry()) == pageData.getCountryDTOList().get(i).getCountryId()) {
                                spinnerCountry.setSelection(i);
                            }
                        } catch (NumberFormatException ne) {
                            UtilView.showLogCat(TAG, "Country Id Format " + ne.getMessage());
                        }
                    }
                }
                if (pageData.getStateDTOList() != null && pageData.getStateDTOList().size() > 0) {
                    for (int i = 0; i < pageData.getStateDTOList().size(); i++) {
                        try {
                            if (Long.parseLong(pageData.getState()) == pageData.getStateDTOList().get(i).getId()) {
                                spinnerState.setSelection(i);
                            }
                        } catch (NumberFormatException ne) {
                            UtilView.showLogCat(TAG, "State Id Format " + ne.getMessage());
                        }
                    }
                }

            }
        }
    }

    @OnClick({R.id.btnSave, R.id.btnMore, R.id.btnLess})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSave:
                if (serverUrl != null) {
                    addContact();
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

    private void addContact() {

        String name = edName.getText().toString().trim();
        String email = edEmail.getText().toString().trim();
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

            Contact contact = new Contact();

            contact.setFullName(name);
            contact.setEmail(email);
            contact.setContactNumber(contactNumber);
            contact.setAddress(address);
            contact.setGstCode(gstin);
            contact.setPersonIncharge(personincharge);
            contact.setPanNO(panno);
            contact.setAccountNo(accountnumber);
            contact.setBankName(bankName);
            contact.setBranchName(bankBranch);
            contact.setiFSCCode(ifsccode);
            contact.setWebsite(website);

            if (contactId != 0) {
                contact.setOtherContactId(contactId);
            }

            if (state[0] != null) {
                contact.setState(state[0].getId());
            }
            if (currency[0] != null) {
                contact.setCurrency(currency[0].getCurrencyId());
            }
            if (country[0] != null) {
                contact.setCountry(country[0].getCountryId());
            }

            final Gson gson = new Gson();

            String url = "";
            if (callingFrom.equals(getResources().getString(R.string.title_editcontact))) {
                url = serverUrl + "/hipos//0/" + Constant.FUNTION_UPDATECONTACT;
            }
            if (callingFrom.equals(getResources().getString(R.string.title_addcontact))) {
                url = serverUrl + "/hipos//0/" + Constant.FUNTION_ADDCONTACT;
            }

            if (!url.equals("")) {

                if (progressBar != null)
                    UtilView.showProgessBar(mActivity, progressBar);
                PostDataTask postDataTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(mActivity, progressBar);
                        HideUtil.init(mActivity);
                        if (result != null) {

                            if (result.toString().equals("No Response Body")) {
                                UtilView.showToast(mActivity, "Contact Details update successfully.");
                                Intent returnIntent = new Intent();
                                mActivity.setResult(Activity.RESULT_OK, returnIntent);
                                mActivity.finish();
                            } else {

                                try {
                                    JSONObject jsonObject = new JSONObject(result.toString());
                                    Contact contact = gson.fromJson(jsonObject.toString(), Contact.class);
                                    if (contact != null) {
                                        if (callingFrom.equals(getResources().getString(R.string.title_editcontact))) {
                                            UtilView.showToast(mActivity, "Contact " + contact.getFullName() + " update successfully.");

                                        }
                                        if (callingFrom.equals(getResources().getString(R.string.title_addcontact))) {
                                            UtilView.showToast(mActivity, "Contact " + contact.getFullName() + " add successfully.");

                                        }
                                        Intent returnIntent = new Intent();
                                        returnIntent.putExtra("contact", contact);
                                        mActivity.setResult(Activity.RESULT_OK, returnIntent);
                                        mActivity.finish();
                                    }


                                } catch (Exception ex) {

                                }
                            }

                        }

                    }
                }, false);
                postDataTask.execute(gson.toJson(contact).toString(), url, "");
            }
        } else {
            if (name == null || name.equals("")) {
                edName.setError(getResources().getString(R.string.error_contactname));
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
                llMoreOrLess.setVisibility(View.VISIBLE);
                btnLess.setVisibility(View.VISIBLE);
                btnMore.setVisibility(View.INVISIBLE);
            }

            if (currency[0] == null) {
                TextView tv = (TextView) spinnerCurrency.getSelectedView();
                tv.setError("Please select Currency");
                UtilView.showToast(mActivity, "Please select Currency");
                llMoreOrLess.setVisibility(View.VISIBLE);
                btnLess.setVisibility(View.VISIBLE);
                btnMore.setVisibility(View.INVISIBLE);
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
