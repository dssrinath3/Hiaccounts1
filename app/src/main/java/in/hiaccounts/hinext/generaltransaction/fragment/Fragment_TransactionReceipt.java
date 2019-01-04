package in.hiaccounts.hinext.generaltransaction.fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;
import com.yinglan.keyboard.HideUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.application.activity.NavigationDrawer_Activity;
import in.hiaccounts.hinext.checkout.activity.Activity_Checkout;
import in.hiaccounts.hinext.generaltransaction.activity.Activity_PrintlistReceipt;
import in.hiaccounts.hinext.generaltransaction.activity.Activity_SelectAccount;
import in.hiaccounts.hinext.generaltransaction.adapter.ContactList_Adapter;
import in.hiaccounts.hinext.generaltransaction.adapter.ExpensesAccountCartList_Adapter;
import in.hiaccounts.hinext.generaltransaction.model.AccountPosCreator;
import in.hiaccounts.hinext.generaltransaction.model.AccountPosHelper;
import in.hiaccounts.hinext.generaltransaction.model.checkout.GTCheckoutData;
import in.hiaccounts.hinext.generaltransaction.model.pagedata.GeneralTransactionPageData;
import in.hiaccounts.hinext.generaltransaction.model.pagedata.TaxList;
import in.hiaccounts.hinext.generaltransaction.model.payee_account.Account;
import in.hiaccounts.hinext.generaltransaction.model.payto_contact.Contact;
import in.hiaccounts.hinext.generaltransaction.model.response.SelectedAccountList;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

import static java.lang.Double.parseDouble;

/**
 * Created by Prateek on 2/9/2017.
 */

public class Fragment_TransactionReceipt extends Fragment {


    public static String TAG = Fragment_TransactionReceipt.class.getSimpleName();
    public Fragment_TransactionReceipt fragment = this;
    Unbinder unbinder;
    @BindView(R.id.edCalender)
    EditText edCalender;
    @BindView(R.id.spinnerTaxType)
    Spinner spinnerTaxType;
    @BindView(R.id.edRecieptFrom)
    EditText edRecieptFrom;
    @BindView(R.id.edSelectAccount)
    EditText edSelectAccount;
    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.tvClearAll)
    TextView tvClearAll;
    @BindView(R.id.btnCheckout)
    Button btnCheckout;
    @BindView(R.id.llBottomLayout)
    LinearLayout llBottomLayout;
    @BindView(R.id.llPageView)
    RelativeLayout llPageView;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.tvTryagain)
    TextView tvTryagain;
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
    AccountPosCreator accountPosCreator;
    Long supplierId, customerId;
    GeneralTransactionPageData pageData = null;
    Account account;
    private Activity mActivity;
    private ServiceHandler serviceHandler;
    private Boolean isInternetPresent = false;
    private List<Contact> contactList = new ArrayList<>();
    private String selectedDate = "", serverUrl,checkoutType="",taxType = "";
    private Dialog dialog = null;
    private List<Account> accountList = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Returning the layout file after inflating
        View view = inflater.inflate(R.layout.fragment_transactionreceipt, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponentView(view);
        return view;
    }

    private void initComponentView(View view) {
        setHasOptionsMenu(true);
        accountPosCreator = AccountPosHelper.getPosCreator();
        serviceHandler = new ServiceHandler(mActivity);
        serverUrl = UtilView.getUrl(mActivity);

        dialog = new Dialog(mActivity);
        accountPosCreator = AccountPosHelper.getPosCreator();

        if (llPageView != null)
            llPageView.setVisibility(View.VISIBLE);
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        edCalender.setText(String.valueOf(new StringBuilder().append(day).append("/").append(month + 1).append("/").append(year)));
        final TimeZone utc = TimeZone.getTimeZone("UTC");
        final SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        f.setTimeZone(utc);
        calendar.set(year, month, day);
        selectedDate = f.format(calendar.getTime());

        getPageLoadData();

        edRecieptFrom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edRecieptFrom.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        edCalender.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edCalender.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        if (spinnerTaxType != null)
            UtilView.setupAdapter(mActivity, spinnerTaxType, getResources().getStringArray(R.array.tax));

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dailogEditAccountDetail(account,position);
            }


        });

        checkCartList();

    }

    private void getPageLoadData() {
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
                UtilView.showProgessBar(mActivity, progressBar);
                String url = serverUrl + "/generalTransaction//0/" + Constant.FUNCTION_GETPAGELOADDATA;
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



    private void dailogEditAccountDetail(final Account account, final int position) {

        dialog.setContentView(R.layout.dialog_generaltransaction_accountadddetail);
        dialog.setCancelable(false);


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

        if (account.getInvoiceNo() != null)
            edInoviceNumber.setText(account.getInvoiceNo());


        edActAmtExTax.setText(account.getAmtexclusivetax()+"");
        edActAmtIncTax.setText(account.getAmtinclusivetax()+"");


        edTaxamt.setText(account.getTaxamt()+"");



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
                    //edActAmtIncTax.setText("0");
                    //edTaxamt.setText("0");
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

                        accountPosCreator.update(account,position);

                        checkCartList();

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

    public void callGTReceiptPrintList(){
        Intent intent = new Intent(mActivity, Activity_PrintlistReceipt.class);
        mActivity.startActivityForResult(intent, Constant.RESQUSTCODE_GENERALTRANSACTION_PRINTLIST);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (accountPosCreator != null)
            accountPosCreator.clear();
    }

    @OnClick({R.id.edCalender, R.id.edSelectAccount, R.id.edRecieptFrom, R.id.tvClearAll, R.id.btnCheckout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edCalender:
                getDatePicker(mActivity);
                break;
            case R.id.edRecieptFrom:
                dialogPaytoContact();
                break;
            case R.id.tvClearAll:
                callClearAll();
                break;
            case R.id.edSelectAccount:
                String taxType = (String) spinnerTaxType.getSelectedItem();
                Intent intent = new Intent(mActivity, Activity_SelectAccount.class);
                intent.putExtra("callingFrom", "expenseTransaction");
                intent.putExtra("taxType", taxType);
                mActivity.startActivityForResult(intent, Constant.RESQUSTCODE_GTSELECTACCOUNT);
                break;

            case R.id.btnCheckout:
                String receiptFrom = edRecieptFrom.getText().toString().toString().trim();
                if (receiptFrom == null || receiptFrom.equals("")) {
                    edRecieptFrom.setError(getString(R.string.err_msg));
                } else if (accountPosCreator == null || accountPosCreator.getAccounts().size() == 0) {
                    UtilView.showToast(mActivity, "Please select atleast one account for payment.");
                } else if (selectedDate.equals("")) {
                    edCalender.setError("Please select date");
                } else {
                    checkoutDialog();
                   /* Intent intent1 = new Intent(mActivity, Activity_RecieptPayment.class);
                    intent1.putExtra("checkoutData", getCheckoutData());
                    startActivityForResult(intent1, Constant.RESQUSTCODE_CHECKOUT);*/
                }
                break;

        }
    }

    public void callClearAll() {
        if (accountPosCreator != null) {
            accountPosCreator.clear();
            checkCartList();
        }
    }

    private void getContactFromserver(String searchAccount, final EditText edSearch, final ProgressView pg, final ListView lvContact) {
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
                if (pg != null)
                    pg.setVisibility(View.VISIBLE);
                String url = serverUrl + "/hipos//undefined/" + Constant.FUNTION_GETCONTACTLIST + "?searchContactText=" + searchAccount.replace(" ", "%20");
                PostDataTask task = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        if (pg != null)
                            pg.setVisibility(View.GONE);
                        HideUtil.init(mActivity);
                        if (result != null) {
                            try {
                                JSONArray resultJsonArray = new JSONArray(result.toString());
                                if (resultJsonArray.length() > 0) {
                                    if (contactList != null) {
                                        contactList.clear();
                                    }
                                    for (int i = 0; i < resultJsonArray.length(); i++) {
                                        JSONObject customerJson = resultJsonArray.getJSONObject(i);
                                        Gson gson = new Gson();
                                        Contact contact = gson.fromJson(customerJson.toString(), Contact.class);
                                        contactList.add(contact);
                                    }

                                    if (contactList != null && contactList.size() > 0) {
                                        ContactList_Adapter accountListAdapter = new ContactList_Adapter(mActivity, contactList);
                                        if (lvContact != null)
                                            lvContact.setAdapter(accountListAdapter);
                                        accountListAdapter.notifyDataSetChanged();
                                    }
                                } else {
                                    if (edSearch != null)
                                        edSearch.setText("");
                                    UtilView.showErrorDialog(getResources().getString(R.string.nocustomer_available), mActivity);
                                }

                            } catch (Exception e) {

                                UtilView.showErrorDialog(getResources().getString(R.string.response_error), mActivity);
                            }

                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.response_error), mActivity);

                        }
                    }
                }, false);
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("searchContactText", searchAccount.replace(" ", "%20"));
                    task.execute(jsonObject.toString(), url, "");
                } catch (JSONException je) {

                }
            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
            }
        } else {
            UtilView.gotToLogin(mActivity);
        }
    }
    private void checkoutDialog() {

        boolean isChckoutAble = true;
        if (accountPosCreator != null && accountPosCreator.getAccounts() != null && accountPosCreator.getAccounts().size() > 0) {
            for (int i = 0; i < accountPosCreator.getAccounts().size(); i++) {
                if (accountPosCreator.getAccounts().get(i).getAmtexclusivetax() == 0) {
                    isChckoutAble = false;
                }
            }
            if (isChckoutAble) {
                String saveData = getCheckoutData("multiPayment");
                Intent intent = new Intent(mActivity, Activity_Checkout.class);
                intent.putExtra("GTSaveData", saveData);
                intent.putExtra("checkoutType", "Save");
                intent.putExtra("callingfrom", Constant.NAVIGATION_GROUP_GENERALTRANSACTION_RECIEPT);
                mActivity.startActivityForResult(intent, Constant.RESQUSTCODE_CHECKOUT);

            } else {
                UtilView.showToast(mActivity, getResources().getString(R.string.err_msg_amt));
            }
        }
    }


    private void dialogPaytoContact() {

        final Dialog dialog = new Dialog(mActivity);
        dialog.setContentView(R.layout.dialog_generaltransaction_paytocontact);
        ImageView imgviewClose = dialog.findViewById(R.id.imgviewClose);
        final EditText edSearch = dialog.findViewById(R.id.edSearch);
        ImageView imgviewSearch = dialog.findViewById(R.id.imgviewSearch);
        final ListView lvContact = dialog.findViewById(R.id.listview);
        Button btnClose = dialog.findViewById(R.id.btnClose);
        final ProgressView pg = dialog.findViewById(R.id.progress_bar);

        imgviewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog != null) dialog.dismiss();
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
                getContactFromserver(edSearch.getText().toString().trim().replace(" ", "%20"), edSearch, pg, lvContact);
            }
        });


        LayoutInflater inflater = mActivity.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.adapter_generaltransaction_contact, null, false);

        TextView tvCustomerName = header.findViewById(R.id.tvCustomerName);
        TextView tvCustomerEmail = header.findViewById(R.id.tvCustomerEmail);
        TextView tvCustomerContact = header.findViewById(R.id.tvCustomerContact);


        tvCustomerName.setText(getResources().getString(R.string.accountCode));
        tvCustomerEmail.setText(getResources().getString(R.string.accountName));
        tvCustomerContact.setText(getResources().getString(R.string.caccountType));

        UtilView.setTextAppearanceSmall(mActivity, tvCustomerName);
        UtilView.setTextAppearanceSmall(mActivity, tvCustomerEmail);
        UtilView.setTextAppearanceSmall(mActivity, tvCustomerContact);

        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvCustomerName);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvCustomerEmail);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvCustomerContact);

        UtilView.setTexttypeFace(null, Typeface.BOLD, tvCustomerName);
        UtilView.setTexttypeFace(null, Typeface.BOLD, tvCustomerEmail);
        UtilView.setTexttypeFace(null, Typeface.BOLD, tvCustomerContact);
        if (lvContact != null)
            lvContact.addHeaderView(header);

        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    getContactFromserver(edSearch.getText().toString().trim().replace(" ", "%20"), edSearch, pg, lvContact);
                }
                return handled;
            }
        });
        if (dialog != null)
            dialog.show();
        getContactFromserver("", edSearch, pg, lvContact);

        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {

                } else {
                    edRecieptFrom.setText(contactList.get(position - 1).getFullName());
                    customerId = contactList.get(position - 1).getOtherContactId();
                    if (dialog != null)
                        dialog.dismiss();
                }

            }
        });


    }

    private String getCheckoutData(String paymentType) {

        List<Account> accountList = accountPosCreator.getAccounts();
        List<SelectedAccountList> accountItemsList = new ArrayList<>();
        Gson gson = new Gson();
        Iterator<Account> itr = accountList.iterator();

        while (itr.hasNext()) {
            Account item = itr.next();


            SelectedAccountList saveItem = new SelectedAccountList();
            saveItem.setAccountCode(item.getAccountCode());
            saveItem.setAccountName(item.getAccountName());
            saveItem.setAccountDescription(item.getAccountDescription());
            saveItem.setAccountGroup(item.getAccountGroup());
            saveItem.setAccountid(item.getAccountid());
            saveItem.setAmtinclusivetax(item.getAmtinclusivetax());
            saveItem.setAmtexclusivetax(item.getAmtexclusivetax());
            saveItem.setAparcode(item.getAparcode());
            saveItem.setInvoiceNumber(item.getInvoiceNo());
            saveItem.setParentAccountName(item.getParentAccountName());
            saveItem.setStatus(item.getStatus());
            saveItem.setStringAccountCode(item.getStringAccountCode());
            saveItem.setTaxid(item.getTaxid());
            saveItem.setTaxamt(item.getTaxamt());
            saveItem.setTaxName(item.getTaxName());
            saveItem.setTaxAmountSplit(String.valueOf(item.getTaxAmountSplit()));


            try {
                saveItem.setTaxpercent(item.getTaxpercent());
            } catch (Exception e) {
                saveItem.setTaxpercent(0.0);
            }
            accountItemsList.add(saveItem);

        }
        GTCheckoutData checkoutData = new GTCheckoutData();
        checkoutData.setSelectedAccountList(accountItemsList);
        checkoutData.setDateOfInvoice(selectedDate);
        checkoutData.setSupplierName(edRecieptFrom.getText().toString().trim());
        checkoutData.setTaxType((String) spinnerTaxType.getSelectedItem());
        checkoutData.setPaymentType(checkoutType);
        checkoutData.setSupplierId(supplierId);
        checkoutData.setCustomerId(customerId);

        double totalCheckoutAmt = 0;
        double totalTaxAmt = 0;

        for (int i = 0; i < accountPosCreator.getAccounts().size(); i++) {
            totalCheckoutAmt += accountPosCreator.getAccounts().get(i).getAmtexclusivetax();
            totalTaxAmt += accountPosCreator.getAccounts().get(i).getTaxamt();
        }
        checkoutData.setTotalCheckOutamt(totalCheckoutAmt);
        checkoutData.setTotalTaxAmt(totalTaxAmt);
        return gson.toJson(checkoutData);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.RESQUSTCODE_CHECKOUT && resultCode == Activity.RESULT_OK) {
            accountPosCreator.clear();
            edRecieptFrom.setText("");
            checkCartList();
        }


        if (requestCode == Constant.RESQUSTCODE_GTSELECTACCOUNT && resultCode == Activity.RESULT_OK) {
            account = (Account) data.getSerializableExtra("accountData");

            checkCartList();

        }
    }

    private void checkCartList() {

        if (accountPosCreator != null) {

            if (accountPosCreator.getAccounts() != null && accountPosCreator.getAccounts().size() > 0) {
                ExpensesAccountCartList_Adapter accountListAdapter = new ExpensesAccountCartList_Adapter(mActivity, accountPosCreator.getAccounts());
                if (listview != null)
                    listview.setAdapter(accountListAdapter);
                accountListAdapter.notifyDataSetChanged();
                if (llBottomLayout != null)
                    llBottomLayout.setVisibility(View.VISIBLE);
            } else {
                if (llBottomLayout != null)
                    llBottomLayout.setVisibility(View.GONE);
                ExpensesAccountCartList_Adapter accountListAdapter = new ExpensesAccountCartList_Adapter(mActivity, accountPosCreator.getAccounts());
                if (listview != null)
                    listview.setAdapter(accountListAdapter);
                accountListAdapter.notifyDataSetChanged();
            }
        } else {
            if (llBottomLayout != null)
                llBottomLayout.setVisibility(View.GONE);
        }


    }

    public void getDatePicker(Activity mActivity) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final TimeZone utc = TimeZone.getTimeZone("UTC");
        final SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        f.setTimeZone(utc);

        DatePickerDialog datePickerDialog = new DatePickerDialog(mActivity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                edCalender.setText(String.valueOf(new StringBuilder().append(dayOfMonth).append("/").append(month + 1).append("/").append(year)));
                calendar.set(year, month, dayOfMonth);
                selectedDate = f.format(calendar.getTime());
            }
        }, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.general_transaction_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {

        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {

            case R.id.action_settings:
                ((NavigationDrawer_Activity)mActivity).openRightDrawer(Constant.MODULE_GENERAL_TRANSACTION);
                break;


        }
        return super.onOptionsItemSelected(item);
    }
}
