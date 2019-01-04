package in.hiaccounts.hinext.controlpanel.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.controlpanel.model.openingbalance.PurchaseInvoiceData;
import in.hiaccounts.hinext.supplier.activity.Activity_Supplier;
import in.hiaccounts.hinext.supplier.model.GetSupplier;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 8/21/2017.
 */

public class Activity_OpeningBalance_Purchase extends AppCompatActivity {

    public static String TAG = Activity_OpeningBalance_Purchase.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edCustomerName)
    EditText edCustomerName;
    @BindView(R.id.edInvDate)
    EditText edInvDate;
    @BindView(R.id.edInvoiceNumber)
    EditText edInvoiceNumber;
    @BindView(R.id.edInvAmt)
    EditText edInvAmt;
    @BindView(R.id.edTaxAmt)
    EditText edTaxAmt;
    @BindView(R.id.edTotalRecievable)
    EditText edTotalRecievable;
    @BindView(R.id.edAmtReceived)
    EditText edAmtReceived;
    @BindView(R.id.edBalanceAmount)
    EditText edBalanceAmount;
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
    String callingFor;

    GetSupplier supplier;
    String selectedDate = "", serverUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opnbalance_purchase);
        ButterKnife.bind(this);
        initComponentView();
    }

    private void initComponentView() {

        activity = this;
        serviceHandler = new ServiceHandler(activity);
        serverUrl = UtilView.getUrl(activity);

        Intent intent = getIntent();
        if (intent != null) {
            callingFor = intent.getStringExtra("callingFor");
            if (callingFor != null)
                toolbar.setTitle(callingFor);
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


        edCustomerName.addTextChangedListener(new TextWatcher() {
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
        edInvoiceNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                edInvoiceNumber.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        edInvAmt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                double invAmt = 0;
                double taxAmt = 0;
                double amtTillDateRecieved = 0;
                boolean isValidInvAmt = false;
                boolean isValidTaxAmt = false;
                boolean isValidAmtTillDate = false;
                if (s.toString() != null && !s.toString().equals("")) {
                    try {
                        edInvAmt.setError(null);
                        invAmt = Double.parseDouble(s.toString());
                        isValidInvAmt = true;
                    } catch (NumberFormatException ne) {
                        isValidInvAmt = false;
                    }

                    String taxAmount = edTaxAmt.getText().toString().trim();
                    if (taxAmount != null && !taxAmount.equals("")) {
                        edTaxAmt.setError(null);
                        try {
                            taxAmt = Double.parseDouble(taxAmount);
                            isValidTaxAmt = true;
                        } catch (NumberFormatException ne) {
                            isValidTaxAmt = false;
                        }
                    } else {
                        taxAmt = 0;
                        isValidTaxAmt = true;

                    }


                    String amtTillData = edAmtReceived.getText().toString().trim();
                    if (amtTillData != null && !amtTillData.equals("")) {
                        edAmtReceived.setError(null);
                        try {
                            amtTillDateRecieved = Double.parseDouble(amtTillData);
                            isValidAmtTillDate = true;
                        } catch (NumberFormatException ne) {
                            isValidAmtTillDate = false;
                        }
                    } else {
                        amtTillDateRecieved = 0;
                        isValidAmtTillDate = true;

                    }

                    if (isValidInvAmt && isValidTaxAmt && isValidAmtTillDate) {
                        calculateAmt(invAmt, taxAmt, amtTillDateRecieved);
                    } else {
                        if (!isValidInvAmt) {
                            edInvAmt.setError(getResources().getString(R.string.error_numberformate));
                        }

                        if (!isValidTaxAmt) {
                            edTaxAmt.setError(getResources().getString(R.string.error_numberformate));
                        }
                        if (!isValidAmtTillDate) {
                            edAmtReceived.setError(getResources().getString(R.string.error_numberformate));
                        }
                    }


                } else {
                    edTotalRecievable.setText("0");
                    edBalanceAmount.setText("0");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edTaxAmt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                double invAmt = 0;
                double taxAmt = 0;
                double amtTillDateRecieved = 0;
                boolean isValidInvAmt = false;
                boolean isValidTaxAmt = false;
                boolean isValidAmtTillDate = false;
                if (s.toString() != null && !s.toString().equals("")) {
                    try {
                        edTaxAmt.setError(null);
                        taxAmt = Double.parseDouble(s.toString());
                        isValidTaxAmt = true;
                    } catch (NumberFormatException ne) {
                        isValidTaxAmt = false;
                    }

                    String invAmtString = edInvAmt.getText().toString().trim();
                    if (invAmtString != null && !invAmtString.equals("")) {
                        edInvAmt.setError(null);
                        try {
                            invAmt = Double.parseDouble(invAmtString);
                            isValidInvAmt = true;
                        } catch (NumberFormatException ne) {
                            isValidInvAmt = false;
                        }
                    } else {
                        invAmt = 0;
                        isValidInvAmt = true;

                    }


                    String amtTillData = edAmtReceived.getText().toString().trim();
                    if (amtTillData != null && !amtTillData.equals("")) {
                        edAmtReceived.setError(null);
                        try {
                            amtTillDateRecieved = Double.parseDouble(amtTillData);
                            isValidAmtTillDate = true;
                        } catch (NumberFormatException ne) {
                            isValidAmtTillDate = false;
                        }
                    } else {
                        amtTillDateRecieved = 0;
                        isValidAmtTillDate = true;

                    }

                    if (isValidInvAmt && isValidTaxAmt && isValidAmtTillDate) {
                        calculateAmt(invAmt, taxAmt, amtTillDateRecieved);
                    } else {
                        if (!isValidInvAmt) {
                            edInvAmt.setError(getResources().getString(R.string.error_numberformate));
                        }

                        if (!isValidTaxAmt) {
                            edTaxAmt.setError(getResources().getString(R.string.error_numberformate));
                        }
                        if (!isValidAmtTillDate) {
                            edAmtReceived.setError(getResources().getString(R.string.error_numberformate));
                        }
                    }


                } else {
                    edTotalRecievable.setText("0");
                    edBalanceAmount.setText("0");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edAmtReceived.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                double invAmt = 0;
                double taxAmt = 0;
                double amtTillDateRecieved = 0;
                boolean isValidInvAmt = false;
                boolean isValidTaxAmt = false;
                boolean isValidAmtTillDate = false;
                if (s.toString() != null && !s.toString().equals("")) {
                    try {
                        edAmtReceived.setError(null);
                        amtTillDateRecieved = Double.parseDouble(s.toString());
                        isValidAmtTillDate = true;
                    } catch (NumberFormatException ne) {
                        isValidAmtTillDate = false;

                    }

                    String invAmtString = edInvAmt.getText().toString().trim();
                    if (invAmtString != null && !invAmtString.equals("")) {
                        edInvAmt.setError(null);
                        try {
                            invAmt = Double.parseDouble(invAmtString);
                            isValidInvAmt = true;
                        } catch (NumberFormatException ne) {
                            isValidInvAmt = false;
                        }
                    } else {
                        invAmt = 0;
                        isValidInvAmt = true;

                    }


                    String taxAmtData = edTaxAmt.getText().toString().trim();
                    if (taxAmtData != null && !taxAmtData.equals("")) {
                        edTaxAmt.setError(null);
                        try {
                            taxAmt = Double.parseDouble(taxAmtData);
                            isValidTaxAmt = true;
                        } catch (NumberFormatException ne) {
                            isValidTaxAmt = false;
                        }
                    } else {
                        taxAmt = 0;
                        isValidTaxAmt = true;

                    }

                    if (isValidInvAmt && isValidTaxAmt && isValidAmtTillDate) {
                        calculateAmt(invAmt, taxAmt, amtTillDateRecieved);
                    } else {
                        if (!isValidInvAmt) {
                            edInvAmt.setError(getResources().getString(R.string.error_numberformate));
                        }

                        if (!isValidTaxAmt) {
                            edTaxAmt.setError(getResources().getString(R.string.error_numberformate));
                        }
                        if (!isValidAmtTillDate) {
                            edAmtReceived.setError(getResources().getString(R.string.error_numberformate));
                        }
                    }


                } else {
                    edTotalRecievable.setText("0");
                    edBalanceAmount.setText("0");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


    private void addSalesInvoice() {


        String customerName = edCustomerName.getText().toString().trim();

        String invNumber = edInvoiceNumber.getText().toString().trim();
        String invAmt = edInvAmt.getText().toString().trim();
        String taxAmt = edTaxAmt.getText().toString().trim();
        String totalReceiveable = edTotalRecievable.getText().toString().trim();
        String amtRecieved = edAmtReceived.getText().toString().trim();
        String balanceAmt = edBalanceAmount.getText().toString().trim();


        if (!customerName.equals("") && customerName != null
                && !selectedDate.equals("") && selectedDate != null
                && !invNumber.equals("") && invNumber != null
                && !invAmt.equals("") && invAmt != null
                && !taxAmt.equals("") && taxAmt != null
                && !totalReceiveable.equals("") && totalReceiveable != null
                && !amtRecieved.equals("") && amtRecieved != null
                && !balanceAmt.equals("") && balanceAmt != null) {


            if (balanceAmt.contains("-") || balanceAmt.equals("0")) {
                UtilView.showToast(activity, "Balance Amt can't be negative or zero");
            } else {
                if (supplier != null) {
                    try {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("supplierID", supplier.getSupplierId());
                        jsonObject.put("supplierName", supplier.getSupplierName());
                        jsonObject.put("piDate", selectedDate);
                        jsonObject.put("formNo", invNumber);
                        jsonObject.put("taxamount", taxAmt);
                        jsonObject.put("totalPayable", totalReceiveable);
                        jsonObject.put("totalReceivableBC", invAmt);
                        jsonObject.put("amountPaidTillDt", amtRecieved);
                        jsonObject.put("balanceAmount", balanceAmt);

                        String url = serverUrl + "/hipos//1/" + Constant.FUNTION_SAVEPURCHASEOPENING;

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
                                                    PurchaseInvoiceData data = gson.fromJson(jsonObject.toString(), PurchaseInvoiceData.class);

                                                    if (data != null) {
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
                                task.execute(jsonObject.toString(), url, "");

                            }
                            if (!isInternatePrsenet) {
                                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
                            }
                        } else {
                            UtilView.gotToLogin(activity);
                        }

                    } catch (Exception e) {
                        UtilView.showLogCat(TAG, "add SaleInvocie exception " + e.toString());
                    }
                }
            }
        } else {

            if (customerName == null || customerName.equals("")) {
                edCustomerName.setError(getResources().getString(R.string.err_msg));
            }
            if (selectedDate == null || selectedDate.equals("")) {
                edInvDate.setError(getResources().getString(R.string.err_msg));
            }
            if (invNumber == null || invNumber.equals("")) {
                edInvoiceNumber.setError(getResources().getString(R.string.err_msg));
            }
            if (invAmt == null || invAmt.equals("")) {
                edInvAmt.setError(getResources().getString(R.string.err_msg));
            }
            if (taxAmt == null || taxAmt.equals("")) {
                edTaxAmt.setError(getResources().getString(R.string.err_msg));
            }
            if (totalReceiveable == null || totalReceiveable.equals("")) {
                edTotalRecievable.setError(getResources().getString(R.string.err_msg));
            }
            if (amtRecieved == null || amtRecieved.equals("")) {
                edAmtReceived.setError(getResources().getString(R.string.err_msg));
            }
            if (balanceAmt == null || balanceAmt.equals("")) {
                edBalanceAmount.setError(getResources().getString(R.string.err_msg));
            }


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
                edInvDate.setText(String.valueOf(new StringBuilder().append(dayOfMonth).append("/").append(month + 1).append("/").append(year)));
                calendar.set(year, month, dayOfMonth);
                selectedDate = f.format(calendar.getTime());
                edInvDate.setError(null);
            }
        }, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();


    }

    @OnClick({R.id.edCustomerName, R.id.edInvDate, R.id.btnSave, R.id.btnClose})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edCustomerName:

                Intent intent = new Intent(activity, Activity_Supplier.class);
                startActivityForResult(intent, Constant.RESQUSTCODE_SUPPLIER);
                break;
            case R.id.edInvDate:
                getDatePicker(activity);
                break;
            case R.id.btnSave:
                addSalesInvoice();
                break;
            case R.id.btnClose:
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.RESQUSTCODE_SUPPLIER && resultCode == RESULT_OK) {
            supplier = (GetSupplier) data.getSerializableExtra("supplier");
            edCustomerName.setText(supplier.getSupplierName());
        }
    }


    public void calculateAmt(double invoiceAmt, double taxAmt, double amtRecTillDate) {
        edTotalRecievable.setError(null);
        edBalanceAmount.setError(null);
        edTotalRecievable.setText("" + (invoiceAmt + taxAmt));
        edBalanceAmount.setText("" + (invoiceAmt + taxAmt - amtRecTillDate));


    }
}
