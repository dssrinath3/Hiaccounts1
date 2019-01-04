package in.hiaccounts.hinext.generaltransaction.activity;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lowagie.text.DocumentException;
import com.rey.material.widget.ProgressView;
import com.yinglan.keyboard.HideUtil;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.generaltransaction.model.checkout.CashPayment;
import in.hiaccounts.hinext.generaltransaction.model.checkout.CreditPayment;
import in.hiaccounts.hinext.generaltransaction.model.checkout.GTCheckoutData;
import in.hiaccounts.hinext.generaltransaction.model.response.SaveResponseData;
import in.hiaccounts.pdfgenerator.PdfUtils;
import in.hiaccounts.pdfgenerator.generaltransaction.GTExpenseVoucherPdf;
import in.hiaccounts.pdfgenerator.generaltransaction.GTExpenseVoucherPdfImpl;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 8/5/2017.
 */

public class Activity_PurchasePayment extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edTotalAmount)
    EditText edTotalAmount;
    @BindView(R.id.edDiscount)
    EditText edDiscount;
    @BindView(R.id.edPaymentAmount)
    EditText edPaymentAmount;
    @BindView(R.id.edAmtTendered)
    EditText edAmtTendered;
    @BindView(R.id.edSupplierInvoiceNo)
    EditText edSupplierInvoiceNo;
    @BindView(R.id.edAmountReturn)
    EditText edAmountReturn;
    @BindView(R.id.btn_saveemail)
    Button btnSaveemail;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.ll_bottomlayout)
    LinearLayout llBottomlayout;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;

    double discountAmt;
    double tenderedAmt;
    double returnAmount;
    boolean isCheckoutable = false;
    GTCheckoutData getCheckout;
    private int currentAPIVersion = Build.VERSION.SDK_INT;
    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 123;
    @BindView(R.id.llAmountTendered)
    LinearLayout llAmountTendered;
    @BindView(R.id.llAmountReturned)
    LinearLayout llAmountReturned;
    @BindView(R.id.edTransactionNo)
    EditText edTransactionNo;
    @BindView(R.id.llTransactionNumber)
    LinearLayout llTransactionNumber;

    SaveResponseData saveResponseData = null;
    Activity activity;

    private ServiceHandler serviceHandler;
    Boolean isInternetPresent = false;
    private String serverUrl, checkoutType = "",transactionNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        setContentView(R.layout.activity_cashpayment);
        ButterKnife.bind(this);
        initComponentView();

    }

    private void initComponentView() {
        ButterKnife.bind(this);
        activity = this;
        serviceHandler = new ServiceHandler(activity);
        serverUrl = UtilView.getUrl(activity);
        getCheckout = (GTCheckoutData) getIntent().getSerializableExtra("checkoutData");
        checkoutType = getIntent().getStringExtra("checkoutType");
        if (checkoutType.equals("Cash")){
            toolbar.setTitle(getResources().getString(R.string.cash_payment));
        }
        if (checkoutType.equals("Cheque")){
            toolbar.setTitle(getResources().getString(R.string.bank_payment));
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



        if (getCheckout != null) {
            edTotalAmount.setText("" + getCheckout.getTotalCheckOutamt());
            edPaymentAmount.setText("" + getCheckout.getTotalCheckOutamt());
            edDiscount.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    try {

                        discountAmt = Double.parseDouble(s.toString().trim());
                        edDiscount.setError(null);
                        if (discountAmt < getCheckout.getTotalCheckOutamt()) {
                            isCheckoutable = true;
                            edPaymentAmount.setText("" + (getCheckout.getTotalCheckOutamt() - discountAmt));
                        } else {
                            isCheckoutable = false;
                            edDiscount.setError(getResources().getString(R.string.error_discountprice));
                        }

                    } catch (NumberFormatException ne) {
                        isCheckoutable = false;
                        edDiscount.setError(getResources().getString(R.string.error_numberformate));
                    }

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            edAmtTendered.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    try {

                        tenderedAmt = Double.parseDouble(s.toString().trim());

                        if (tenderedAmt < (getCheckout.getTotalCheckOutamt() - discountAmt)) {
                            isCheckoutable = false;
                            edAmountReturn.setText("0");
                            edAmtTendered.setError(getResources().getString(R.string.error_tenderamount));
                        } else {
                            returnAmount = tenderedAmt - getCheckout.getTotalCheckOutamt() - discountAmt;
                            edAmountReturn.setText(("" + returnAmount));
                            isCheckoutable = true;
                        }

                    } catch (NumberFormatException ne) {
                        isCheckoutable = false;
                        edAmtTendered.setError(getResources().getString(R.string.error_numberformate));
                    }


                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            edTransactionNo.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    if (s==null || s.toString().equals("")){
                        edTransactionNo.setError(getResources().getString(R.string.err_msg));
                        isCheckoutable = false;
                    }else {
                        isCheckoutable = true;
                        transactionNo=s.toString();

                    }




                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            if (checkoutType!=null && !checkoutType.equals("")) {
                if (checkoutType.equals("Cash")) {
                    llAmountReturned.setVisibility(View.VISIBLE);
                    llAmountTendered.setVisibility(View.VISIBLE);
                    llTransactionNumber.setVisibility(View.GONE);

                }
                if (checkoutType.equals("Cheque")) {

                    edDiscount.setFocusable(true);
                    edDiscount.setFocusableInTouchMode(true);
                    edDiscount.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                    llAmountReturned.setVisibility(View.GONE);
                    llAmountTendered.setVisibility(View.GONE);
                    llTransactionNumber.setVisibility(View.VISIBLE);

                }
            }
        }
    }

    @OnClick({R.id.btn_saveemail, R.id.btn_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_saveemail:


                if (isCheckoutable && getCheckout != null) {
                    if (checkoutType.equals("Cash")){
                        CashPayment cashPayment = new CashPayment();
                        cashPayment.setTotalCPDiscount(discountAmt);
                        cashPayment.setTotalCPAmountRefunded("" + returnAmount);
                        cashPayment.setTotalCPAmountTendered(tenderedAmt);
                        getCheckout.setCashPayment(cashPayment);

                        callServer();
                    }
                    if (checkoutType.equals("Cheque")){
                        CreditPayment creditPayment=new CreditPayment();
                        creditPayment.setTotalCCPDiscount(""+discountAmt);
                        creditPayment.setTotalCCPAfterDiscount("" + edPaymentAmount.getText().toString().trim());
                        creditPayment.setTransactionNo(transactionNo);
                        getCheckout.setCreditPayment(creditPayment);

                        if (transactionNo==null || transactionNo.equals("")){

                            edTransactionNo.setError(getResources().getString(R.string.err_msg));
                        }else {
                            callServer();
                        }
                    }


                } else {
                    UtilView.showToast(this, "Please fill the details first.");
                }


                break;
            case R.id.btn_cancel:
                finish();
                break;
        }
    }
    public void callServer(){
        final Gson gson = new Gson();
        getCheckout.setPaymentType(checkoutType);
        getCheckout.setSupplierInvNo(edSupplierInvoiceNo.getText().toString().trim());
        String url = serverUrl + "/generalTransaction//0/" + Constant.FUNTION_SAVEGTPURCHASE;


        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
                UtilView.showProgessBar(activity, progressBar);
                PostDataTask postDataTask = new PostDataTask(this, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(activity, progressBar);

                        HideUtil.init(Activity_PurchasePayment.this);
                        if (result != null) {
                            if (result.equals("invalid")) {
                                UtilView.showToast(activity, getResources().getString(R.string.accesstoken_error));
                                Intent intent = new Intent(activity, Activity_Login.class);
                                startActivity(intent);
                                finish();
                            } else {
                                try {
                                    JSONObject jsonObject = new JSONObject(result.toString());
                                    saveResponseData = gson.fromJson(jsonObject.toString(), SaveResponseData.class);
                                    if (saveResponseData != null) {
                                        if (currentAPIVersion >= Build.VERSION_CODES.M) {
                                            checkPermission();
                                        } else {
                                            //new PdfGenerationTask().execute();
                                            createPdf();
                                        }
                                    } else {
                                        UtilView.showToast(Activity_PurchasePayment.this, "Some Error. Please try Again.");
                                    }
                                    /*
                                    UtilView.showToast(Activity_PurchasePayment.this,"Payment Voucher Generate Successfully.");
                                        Intent returnIntent = new Intent();
                                        setResult(Activity.RESULT_OK, returnIntent);
                                        finish();*/


                                } catch (Exception ex) {
                                    UtilView.showErrorDialog(getResources().getString(R.string.response_error), activity);
                                }
                            }
                        }
                    }
                }, false);
                postDataTask.execute(gson.toJson(getCheckout).toString(), url, "");
            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
            }
        } else {
            UtilView.gotToLogin(activity);
        }

    }

    private void checkPermission() {

        String permission = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(this, new String[]{permission}, MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{permission}, MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            }
        } else {

            if (saveResponseData != null) {
                createPdf();
            }


        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (ActivityCompat.checkSelfPermission(this, permissions[0]) == PackageManager.PERMISSION_GRANTED) {
            switch (requestCode) {
                case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE:
                    if (saveResponseData != null) {
                        createPdf();
                    }
                    break;
            }


        } else {
            Toast.makeText(this, "Permisson Deny", Toast.LENGTH_LONG).show();
        }
    }

    private void createPdf() {
        File pdfFile = null;
        pdfFile = PdfUtils.createFile(saveResponseData.getOpNo() + ".pdf", Constant.DIRECTORY_GT, Constant.DIRECTORY_GTEXPENSE);


        if (pdfFile != null) {

            InputStream logoInputStream = PdfUtils.getLogoInputStream(this, progressBar);
            if (logoInputStream != null) {
                try {
                    GTExpenseVoucherPdf voucherPdf = new GTExpenseVoucherPdfImpl();
                    voucherPdf.generateExpenseVoucherPdf(activity, saveResponseData, new FileOutputStream(pdfFile), logoInputStream);

                    openPdf(saveResponseData.getOpNo() + ".pdf", Constant.DIRECTORY_GT, Constant.DIRECTORY_GTEXPENSE);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    GTExpenseVoucherPdf voucherPdf = new GTExpenseVoucherPdfImpl();
                    voucherPdf.generateExpenseVoucherPdf(this, saveResponseData, new FileOutputStream(pdfFile), null);
                    openPdf(saveResponseData.getOpNo() + ".pdf", Constant.DIRECTORY_GT, Constant.DIRECTORY_GTEXPENSE);
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
                UtilView.showToast(activity, "Your device is compatible for view Pdf File.");
            }
        } else {
            UtilView.showToast(activity, "No Invoice generated.");
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101) {

            Intent returnIntent = new Intent();
            setResult(Activity.RESULT_OK, returnIntent);
            finish();

        }
    }
}
