package in.hiaccounts.hinext.generaltransaction.fragment;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lowagie.text.DocumentException;
import com.rey.material.widget.ProgressView;
import com.yinglan.keyboard.HideUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.application.activity.NavigationDrawer_Activity;
import in.hiaccounts.hinext.generaltransaction.activity.Activity_PrintlistJournalEntry;
import in.hiaccounts.hinext.generaltransaction.activity.Activity_PrintlistReceipt;
import in.hiaccounts.hinext.generaltransaction.adapter.AccountList_Adapter;
import in.hiaccounts.hinext.generaltransaction.adapter.JournalCart_Adapter;
import in.hiaccounts.hinext.generaltransaction.model.checkout.GTJournalData;
import in.hiaccounts.hinext.generaltransaction.model.checkout.GTSaveJournalData;
import in.hiaccounts.hinext.generaltransaction.model.payee_account.Account;
import in.hiaccounts.pdfgenerator.PdfUtils;
import in.hiaccounts.pdfgenerator.generaltransaction.GTJournalVoucherPdf;
import in.hiaccounts.pdfgenerator.generaltransaction.GTJournalVoucherPdfImpl;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 2/9/2017.
 */

public class Fragment_TransactionJournalEntry extends Fragment implements TextWatcher {
    @BindView(R.id.edCalender)
    EditText edCalender;
    @BindView(R.id.tvAccount)
    TextView tvAccount;
    @BindView(R.id.edMemo)
    EditText edMemo;
    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.edTotalDebit)
    EditText edTotalDebit;
    @BindView(R.id.edTotalCredit)
    EditText edTotalCredit;
    @BindView(R.id.textView10)
    TextView textView10;
    @BindView(R.id.edOutofBalance)
    EditText edOutofBalance;
    @BindView(R.id.tvSave)
    TextView tvSave;
    @BindView(R.id.tvClearAll)
    TextView tvClearAll;
    @BindView(R.id.llBottomLayout)
    LinearLayout llBottomLayout;
    @BindView(R.id.llPageView)
    RelativeLayout llPageView;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.tvTryagain)
    TextView tvTryagain;
    Unbinder unbinder;

    Activity mActivity;
    private ServiceHandler serviceHandler;
    private Boolean isInternetPresent = false;
    private String searchAccount = "";
    private List<Account> accountList = new ArrayList<>();
    public static String TAG = Fragment_TransactionJournalEntry.class.getSimpleName();
    Account selectedAccount = null;
    private List<Account> accountCartList = new ArrayList<>();
    JournalCart_Adapter journalCartAdapter;
    Fragment_TransactionJournalEntry fragment;

    public static boolean isError = false;
    private String selectedDate = "", serverUrl;
    GTJournalData gtJournalResponseData;

    private HashMap<Long, Account> accountHashMap = new HashMap<>();
    private int currentAPIVersion = Build.VERSION.SDK_INT;
    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 123;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
        fragment = this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Returning the layout file after inflating
        View view = inflater.inflate(R.layout.fragment_transactionjournalentry, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponent(view);
        return view;
    }

    private void initComponent(View view) {
        setHasOptionsMenu(true);
        serviceHandler = new ServiceHandler(mActivity);
        selectedDate = UtilView.setCurrentDate(edCalender);
        serverUrl = UtilView.getUrl(mActivity);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.edCalender, R.id.tvAccount, R.id.tvSave, R.id.tvClearAll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edCalender:
                getDatePicker(mActivity);
                break;
            case R.id.tvAccount:
                dailogAccount();
                break;
            case R.id.tvSave:
                saveEntry();
                break;
            case R.id.tvClearAll:
                resetView();
                break;
        }
    }

    private void saveEntry() {
        String memo = edMemo.getText().toString();
        String outOfBalance = edOutofBalance.getText().toString().trim();

        if (accountHashMap != null && accountHashMap.size() > 0) {
            if (memo != null && !memo.equals("")
                    && selectedDate != null && !selectedDate.equals("")
                    && outOfBalance != null && outOfBalance.equals("0.0")
                    ) {
                if (!isError) {
                    double totalDebit = 0;
                    double totalCredit = 0;
                    final List<Account> accountList = new ArrayList<>();
                    if (accountHashMap != null && accountHashMap.size() > 0 && !isError) {
                        for (Map.Entry map : accountHashMap.entrySet()) {
                            Account accountDetail = (Account) map.getValue();
                            totalCredit += accountDetail.getCreditAmount();
                            totalDebit += accountDetail.getDebitAmount();
                            accountList.add(accountDetail);
                        }
                        GTSaveJournalData saveData = new GTSaveJournalData();
                        saveData.setTotalCredit(totalCredit);
                        saveData.setTotalDebit(totalDebit);
                        saveData.setJournalEntryDetailsList(accountList);
                        saveData.setMemo(memo);
                        saveData.setJeDate(selectedDate);
                        isInternetPresent = serviceHandler.isConnectingToInternet();
                        if (serverUrl != null) {
                            if (isInternetPresent) {
                                UtilView.showProgessBar(mActivity, progressBar);
                                String url = serverUrl + "/generalTransaction//0/" + Constant.FUNTION_SAVEGTJOURNAL;
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
                                                UtilView.showToast(mActivity, "Server Issue");
                                            } else {
                                                try {
                                                    JSONObject customerJson = new JSONObject(result.toString());
                                                    Gson gson = new Gson();
                                                    gtJournalResponseData = gson.fromJson(customerJson.toString(), GTJournalData.class);
                                                    if (gtJournalResponseData != null) {
                                                        if (accountList != null)
                                                            accountList.clear();
                                                        if (accountHashMap != null)
                                                            accountHashMap.clear();
                                                        if (accountCartList != null)
                                                            accountCartList.clear();
                                                        bottomViewInVisible();

                                                        resetView();

                                                        if (currentAPIVersion >= Build.VERSION_CODES.M) {
                                                            checkPermission();
                                                        } else {
                                                            //new PdfGenerationTask().execute();
                                                            createPdf();
                                                            }



                                                       // UtilView.showToast(mActivity, "Journal Entry Save Successfully.");

                                                    } else {
                                                        bottomViewVisible();
                                                        UtilView.showToast(mActivity, "Journal Entry doesn't Save Successfully. Please try again.");
                                                    }
                                                } catch (Exception e) {
                                                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
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
                if (isError) {
                    UtilView.showToast(mActivity, "Please add valid amount");
                }
            } else {
                if (memo == null || memo.equals("")) {
                    if (edMemo != null)
                        edMemo.setError(getResources().getString(R.string.err_msg));
                }
                if (selectedDate == null || selectedDate.equals("")) {
                    if (edCalender != null)
                        edCalender.setError(getResources().getString(R.string.err_msg));
                }
                if (outOfBalance == null || !outOfBalance.equals("0.0")) {
                    UtilView.showToast(mActivity, getResources().getString(R.string.err_msg_outofbalance));
                }
            }
        } else {
            UtilView.showToast(mActivity, "Please add account first");
        }


    }


    private void dailogAccount() {

        final Dialog dialog = new Dialog(mActivity);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.activity_generaltransaction_account);

        ImageView imgviewClose = dialog.findViewById(R.id.imgviewClose);
        final EditText edSearch = dialog.findViewById(R.id.edSearch);
        ImageView imgviewSearch = dialog.findViewById(R.id.imgviewSearch);
        final ListView lvAccount = dialog.findViewById(R.id.listview);
        Button btnClose = dialog.findViewById(R.id.btnClose);
        final ProgressView pg = dialog.findViewById(R.id.progress_bar);
        final LinearLayout llListview = dialog.findViewById(R.id.llListView);


        imgviewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog != null) dialog.dismiss();
            }
        });
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog != null) dialog.dismiss();
            }
        });

        imgviewSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAccountFromserver(edSearch.getText().toString().trim().replace(" ", "%20"), edSearch, pg, lvAccount, llListview);
            }
        });


        /*LayoutInflater inflater = mActivity.getLayoutInflater();
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

        if (lvAccount != null)
            lvAccount.addHeaderView(header);
*/
        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    getAccountFromserver(edSearch.getText().toString().trim().replace(" ", "%20"), edSearch, pg, listview, llListview);
                }
                return handled;
            }
        });
        if (dialog != null)
            dialog.show();
        getAccountFromserver("", edSearch, pg, lvAccount, llListview);

        lvAccount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


               /* if (position == 0) {

                } else {*/
                selectedAccount = accountList.get(position);

                isInternetPresent = serviceHandler.isConnectingToInternet();
                if (serverUrl != null) {
                    if (isInternetPresent) {
                        if (pg != null)
                            pg.setVisibility(View.VISIBLE);
                        String url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETACCOUNTDETAIL + "?accountCode=" + selectedAccount.getAccountid();
                        GetDataTask task = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                            @Override
                            public void onTaskComplete(String result) {
                                if (pg != null)
                                    pg.setVisibility(View.VISIBLE);

                                HideUtil.init(mActivity);


                                if (result != null) {
                                    try {
                                        JSONArray resultJsonArray = new JSONArray(result.toString());
                                        if (resultJsonArray.length() > 0) {
                                            JSONObject customerJson = resultJsonArray.getJSONObject(0);
                                            Gson gson = new Gson();
                                            Account account = gson.fromJson(customerJson.toString(), Account.class);
                                            if (!accountHashMap.containsKey(account.getAccountid())) {

                                                accountHashMap.put(account.getAccountid(), account);
                                                journalCartAdapter = new JournalCart_Adapter(mActivity, accountHashMap, fragment);
                                                if (listview != null)
                                                    listview.setAdapter(journalCartAdapter);
                                                journalCartAdapter.notifyDataSetChanged();
                                                if (dialog != null)
                                                    dialog.dismiss();
                                                bottomViewVisible();
                                            } else {
                                                UtilView.showToast(mActivity, "Account already used.");
                                            }
                                        } else {
                                            bottomViewInVisible();
                                            if (edSearch != null)
                                                edSearch.setText("");
                                            UtilView.showErrorDialog(getResources().getString(R.string.noaccountdetail_available), mActivity);
                                        }

                                    } catch (Exception e) {

                                        UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                                    }

                                } else {
                                    UtilView.showErrorDialog(getResources().getString(R.string.response_error), mActivity);
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

                //}
            }
        });


    }

    private void getAccountFromserver(String searchAccount, final EditText edSearch, final ProgressView pg, final ListView lvAccount, final LinearLayout llListview) {

        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
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
                                        llListview.setVisibility(View.VISIBLE);
                                        AccountList_Adapter accountListAdapter = new AccountList_Adapter(mActivity, accountList);
                                        if (lvAccount != null)
                                            lvAccount.setAdapter(accountListAdapter);
                                        accountListAdapter.notifyDataSetChanged();
                                    }
                                } else {
                                    llListview.setVisibility(View.GONE);
                                    if (edSearch != null)
                                        edSearch.setText("");
                                    UtilView.showErrorDialog(getResources().getString(R.string.noaccount_available), mActivity);
                                }

                            } catch (Exception e) {

                                UtilView.showErrorDialog(getResources().getString(R.string.response_error), mActivity);
                            }

                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.response_error), mActivity);
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


    public void setTotal() {
        double totalDebit = 0;
        double totalCredit = 0;
        if (accountHashMap != null && accountHashMap.size() > 0 && !isError) {
            for (Map.Entry map : accountHashMap.entrySet()) {
                Account accountDetail = (Account) map.getValue();
                totalCredit += accountDetail.getCreditAmount();
                totalDebit += accountDetail.getDebitAmount();
            }
            edTotalCredit.setText("" + totalCredit);
            edTotalDebit.setText("" + totalDebit);
            edOutofBalance.setText("" + (totalCredit - totalDebit));
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


    public void bottomViewVisible() {
        if (llBottomLayout != null)
            llBottomLayout.setVisibility(View.VISIBLE);
    }

    public void bottomViewInVisible() {

        if (llBottomLayout != null)
            llBottomLayout.setVisibility(View.GONE);
    }

    private void resetView() {

        if (edCalender != null)
            selectedDate = UtilView.setCurrentDate(edCalender);
        if (edMemo != null)
            edMemo.setText("");
        if (edTotalCredit != null)
            edTotalCredit.setText("0.0");
        if (edTotalDebit != null)
            edTotalDebit.setText("0.0");
        if (edOutofBalance != null)
            edOutofBalance.setText("0.0");

        bottomViewInVisible();

        if (accountHashMap != null) {
            accountHashMap.clear();
            journalCartAdapter = new JournalCart_Adapter(mActivity, accountHashMap, fragment);
            if (listview != null)
                listview.setAdapter(journalCartAdapter);
            journalCartAdapter.notifyDataSetChanged();
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

        if (edCalender != null)
            edCalender.setError(null);
        if (edMemo != null)
            edMemo.setError(null);
    }


    private void checkPermission() {

        String permission = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        if (ContextCompat.checkSelfPermission(mActivity, permission) != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity, permission)) {
                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(mActivity, new String[]{permission}, MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            } else {
                ActivityCompat.requestPermissions(mActivity, new String[]{permission}, MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            }
        } else {

            if (gtJournalResponseData != null) {
                createPdf();
            }


        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (ActivityCompat.checkSelfPermission(mActivity, permissions[0]) == PackageManager.PERMISSION_GRANTED) {
            switch (requestCode) {
                case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE:
                    if (gtJournalResponseData != null) {
                        createPdf();
                    }
                    break;
            }


        } else {
            Toast.makeText(mActivity, "Permisson Deny", Toast.LENGTH_LONG).show();
        }
    }

    private void createPdf() {
        File pdfFile = null;
        pdfFile = PdfUtils.createFile(gtJournalResponseData.getJeNo() + ".pdf", Constant.DIRECTORY_GT, Constant.DIRECTORY_GTJOURNAL);


        if (pdfFile != null) {

            InputStream logoInputStream = PdfUtils.getLogoInputStream(mActivity, progressBar);
            if (logoInputStream != null) {
                try {
                    GTJournalVoucherPdf voucherPdf = new GTJournalVoucherPdfImpl();
                    voucherPdf.generateJournalVoucherPdf(mActivity, gtJournalResponseData, new FileOutputStream(pdfFile), logoInputStream);

                    openPdf(gtJournalResponseData.getJeNo() + ".pdf", Constant.DIRECTORY_GT, Constant.DIRECTORY_GTJOURNAL);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    GTJournalVoucherPdf voucherPdf = new GTJournalVoucherPdfImpl();
                    voucherPdf.generateJournalVoucherPdf(mActivity,gtJournalResponseData, new FileOutputStream(pdfFile), null);
                    openPdf(gtJournalResponseData.getJeNo() + ".pdf", Constant.DIRECTORY_GT, Constant.DIRECTORY_GTJOURNAL);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void openPdf(String fileName, String groupDirectory, String childDirectory) {
        File file = PdfUtils.getFile(fileName, groupDirectory, childDirectory);
        if (file != null) {
            Intent target = new Intent(Intent.ACTION_VIEW);
            target.setDataAndType(Uri.fromFile(file), "application/pdf");
            target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            try {
                startActivityForResult(target, 101);
            } catch (ActivityNotFoundException e) {
                UtilView.showToast(mActivity, "Your device is compatible for view Pdf File.");
            }
        } else {
            UtilView.showToast(mActivity, "No Invoice generated.");
        }

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
                ((NavigationDrawer_Activity)mActivity).openRightDrawer(Constant.MODULE_GENERAL_TRANSACTION_JOURNAL);
                break;


        }
        return super.onOptionsItemSelected(item);
    }
    public void callGTJournalPrintList(){
        Intent intent = new Intent(mActivity, Activity_PrintlistJournalEntry.class);
        mActivity.startActivityForResult(intent, Constant.RESQUSTCODE_GENERALTRANSACTION_PRINTLIST);

    }
}
