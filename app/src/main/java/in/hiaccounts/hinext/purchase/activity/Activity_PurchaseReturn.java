package in.hiaccounts.hinext.purchase.activity;

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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lowagie.text.DocumentException;
import com.rey.material.widget.ProgressView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hiaccounts.R;
import in.hiaccounts.hinext.purchase.model.purchase_invoicereturn.PurchaseInvoiceReturnData;
import in.hiaccounts.hinext.purchase.model.purchase_pos.PurchasePosCreator;
import in.hiaccounts.hinext.purchase.model.purchase_pos.PurchasePosHelper;
import in.hiaccounts.hinext.purchase.model.purchase_saveresponses.Checkout_ResponseData;
import in.hiaccounts.pdfgenerator.PdfUtils;
import in.hiaccounts.pdfgenerator.purchase.purchase_orders.PosA4PurchaseOrderPdfImpl;
import in.hiaccounts.pdfgenerator.purchase.purchase_orders.PosPurchaseOrderPdf;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

public class Activity_PurchaseReturn extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tvTotalReturnAmt)
    TextView tvTotalReturnAmt;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.tvTotalCashAmt)
    TextView tvTotalCashAmt;
    @BindView(R.id.tvTotalCardAmt)
    TextView tvTotalCardAmt;
    @BindView(R.id.tvTotalVoucherAmt)
    TextView tvTotalVoucherAmt;
    @BindView(R.id.spinnerReturnReason)
    Spinner spinnerReturnReason;
    @BindView(R.id.tv_cashicon)
    TextView tvCashicon;
    @BindView(R.id.edCashAmount)
    EditText edCashAmount;
    @BindView(R.id.ll_cashlayuout)
    LinearLayout llCashlayuout;
    @BindView(R.id.tvVchIcon)
    TextView tvVchIcon;
    @BindView(R.id.edVchAmount)
    EditText edVchAmount;
    @BindView(R.id.edVchNumber)
    EditText edVchNumber;
    @BindView(R.id.tvCardIcon)
    TextView tvCardIcon;
    @BindView(R.id.edChequeAmt)
    EditText edChequeAmt;
    @BindView(R.id.edChequeNumber)
    EditText edChequeNumber;
    @BindView(R.id.edMemo)
    EditText edMemo;
    @BindView(R.id.btnReturnAmt)
    Button btnReturnAmt;
    @BindView(R.id.rlContent)
    LinearLayout rlContent;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;

    private int currentAPIVersion = Build.VERSION.SDK_INT;
    private Checkout_ResponseData salesReturnOrderResponse = null;
    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 123;
    private PurchaseInvoiceReturnData posInvoiceData;
    private Activity mActivity;
    private double cashAmt, vchrAmt, chequeAmt;
    private double amtReturn;
    private String memo, serverUrl;
    private boolean isCheckoutable = false;
    private ServiceHandler serviceHandler;
    private Boolean isInternetPresent = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_return);
        ButterKnife.bind(this);
        initComponentview();
    }

    private void initComponentview() {
        mActivity = this;
        serverUrl=UtilView.getUrl(mActivity);
        serviceHandler = new ServiceHandler(mActivity);
        toolbar.setTitle(getResources().getString(R.string.menu_return));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        posInvoiceData = (PurchaseInvoiceReturnData) intent.getSerializableExtra("returnInvoiceData");
        if (posInvoiceData != null) {

            tvTotalCashAmt.setText("");
            tvTotalCardAmt.setText("");
            tvTotalVoucherAmt.setText("");

            Double totalReturnAmt = Double.valueOf(posInvoiceData.getAmountReturned());
            if(totalReturnAmt >= posInvoiceData.getApBalance()){
                amtReturn = totalReturnAmt - posInvoiceData.getApBalance();
                BigDecimal bigAmtReturn = new BigDecimal(amtReturn).setScale(2, BigDecimal.ROUND_UP);
                tvTotalReturnAmt.setText("" + bigAmtReturn);
            }else {
                tvTotalReturnAmt.setText("0.00");
            }



        }
        UtilView.setupReturnReasonAdapter(mActivity, spinnerReturnReason, getResources().getStringArray(R.array.salesReutrnReason));

        edChequeAmt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim() == null || s.toString().trim().equals("")) {
                    //   edChequeAmt.setError(getResources().getString(R.string.err_msg));
                    chequeAmt = 0;
                    //   isCheckoutable=false;
                } else {
                    try {
                        edChequeAmt.setError(null);
                        chequeAmt = Double.parseDouble(s.toString().trim());
                        isCheckoutable = true;

                    } catch (NumberFormatException ne) {
                        edChequeAmt.setError(getResources().getString(R.string.error_numberformate));
                        isCheckoutable = false;
                        chequeAmt = 0;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        edVchAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim() == null || s.toString().trim().equals("")) {
                    vchrAmt = 0;
                } else {
                    try {
                        isCheckoutable = true;
                        edVchAmount.setError(null);
                        vchrAmt = Double.parseDouble(s.toString().trim());

                    } catch (NumberFormatException ne) {
                        isCheckoutable = false;
                        vchrAmt = 0;
                        edVchAmount.setError(getResources().getString(R.string.error_numberformate));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edCashAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim() == null || s.toString().trim().equals("")) {
                    edCashAmount.setError(getResources().getString(R.string.err_msg));
                    cashAmt = 0;
                    isCheckoutable = false;
                } else {
                    try {
                        isCheckoutable = true;
                        edVchAmount.setError(null);
                        cashAmt = Double.parseDouble(s.toString().trim());

                    } catch (NumberFormatException ne) {
                        edCashAmount.setError(getResources().getString(R.string.error_numberformate));
                        isCheckoutable = false;
                        cashAmt = 0;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        edMemo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString() == null || s.toString().equals("")) {
                    edMemo.setError(getResources().getString(R.string.err_msg));
                    isCheckoutable = false;
                } else {
                    isCheckoutable = true;
                    edMemo.setError(null);
                    memo = s.toString().trim();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @OnClick(R.id.btnReturnAmt)
    public void onViewClicked(View view) {
        if (view.getId() == R.id.btnReturnAmt) {
            String reasonRetun = (String) spinnerReturnReason.getSelectedItem();
            String chequeNumber = edChequeNumber.getText().toString().trim();
            String vchNumber = edVchNumber.getText().toString().trim();
            if (memo == null || memo.equals("")) {
                edMemo.setError(getResources().getString(R.string.err_msg));
                isCheckoutable = false;
            }
            if (posInvoiceData != null) {

                PurchaseInvoiceReturnData returnSalesData = new PurchaseInvoiceReturnData();
                if (isCheckoutable) {
                    if (amtReturn >= (cashAmt + chequeAmt + vchrAmt)) {
                        returnSalesData.setCmpyLoc(posInvoiceData.getCmpyLoc());
                        returnSalesData.setPiid(posInvoiceData.getPiid());
                        returnSalesData.setOperation("Return");
                        returnSalesData.setSelfBuildInvoice("false");
                        returnSalesData.setSelectedItemsList(posInvoiceData.getSelectedItemsList());
                        returnSalesData.setTotalCheckOutamt(Double.parseDouble(tvTotalReturnAmt.getText().toString()));
                        returnSalesData.setPaymentType("cashPayment");
                        returnSalesData.setTotalTaxAmt(posInvoiceData.getTotalTaxAmt());
                        returnSalesData.setTaxType(posInvoiceData.getTaxType());
                        returnSalesData.setSupplierId(posInvoiceData.getSupplierId());
                        returnSalesData.setSuppLoc(posInvoiceData.getSuppLoc());
                        returnSalesData.setTotalTenderedAmount(Double.parseDouble(tvTotalReturnAmt.getText().toString()));
                        returnSalesData.setCessTotalTaxAmt(posInvoiceData.getCessTotalTaxAmt());

                        returnSalesData.setDateOfInvoice(posInvoiceData.getDateOfInvoice());
                        isInternetPresent = serviceHandler.isConnectingToInternet();
                        if (serverUrl != null) {
                            if (isInternetPresent) {
                                String url = serverUrl + "/purchase//1/"+ Constant.FUNTION_RETURNINVOICE;
                                UtilView.showProgessBar(mActivity, progressBar);
                                PostDataTask dataTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                                    @Override
                                    public void onTaskComplete(String result) {
                                        UtilView.hideProgessBar(mActivity, progressBar);
                                        if (result != null) {
                                            Gson gson = new Gson();
                                            try {
                                                salesReturnOrderResponse = gson.fromJson(result.toString(), Checkout_ResponseData.class);
                                                if (salesReturnOrderResponse != null) {
                                                    if (salesReturnOrderResponse.getPiData() != null) {

                                                        PurchasePosCreator purchasePosCreator  = purchasePosCreator = PurchasePosHelper.getPosCreator();
                                                        purchasePosCreator.clear();
                                                        if (currentAPIVersion >= Build.VERSION_CODES.M) {
                                                            checkPermission();
                                                        } else {
                                                            createPdf();
                                                        }
                                                    } else {
                                                        UtilView.showToast(mActivity, "Retun Order doesn't placed successfully. Please try again");
                                                    }
                                                } else {
                                                    Intent returnIntent = new Intent();
                                                    UtilView.showToast(mActivity, "Some Server Error");
                                                    mActivity.setResult(Activity.RESULT_OK, returnIntent);
                                                    mActivity.finish();
                                                }


                                            } catch (Exception e) {
                                                UtilView.showErrorDialog(getResources().getString(R.string.response_error), mActivity);
                                            }
                                        }
                                    }
                                }, false);
                                dataTask.execute(new Gson().toJson(returnSalesData).toString(), url, "");
                            } else {
                                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                            }
                        } else {
                            UtilView.gotToLogin(mActivity);
                        }
                    } else {
                        UtilView.showToast(mActivity, "Payment Amount should be less or equal to returning amount.");
                    }
                }
            }
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
            if (salesReturnOrderResponse != null) {
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
                    if (salesReturnOrderResponse != null) {
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
        pdfFile = PdfUtils.createFile(salesReturnOrderResponse.getPiData().getPiNo() + ".pdf", Constant.DIRECTORY_PURCHASE, Constant.DIRECTORY_PURCHASEORDER);

        if (pdfFile != null) {

            InputStream logoInputStream = PdfUtils.getLogoInputStream(mActivity, progressBar);


            if (logoInputStream != null) {
                try {
                    PosPurchaseOrderPdf invoicePdf = new PosA4PurchaseOrderPdfImpl();
                    invoicePdf.generatePurchaseOrderInvoice(mActivity, salesReturnOrderResponse, new FileOutputStream(pdfFile), logoInputStream);

                    openPdfInvoice(salesReturnOrderResponse.getPiData().getPiNo() + ".pdf", Constant.DIRECTORY_PURCHASE, Constant.DIRECTORY_PURCHASEORDER);


                } catch (IOException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    PosPurchaseOrderPdf invoicePdf = new PosA4PurchaseOrderPdfImpl();
                    invoicePdf.generatePurchaseOrderInvoice(mActivity, salesReturnOrderResponse, new FileOutputStream(pdfFile), logoInputStream);
                    openPdfInvoice(salesReturnOrderResponse.getPiData().getPiNo() + ".pdf", Constant.DIRECTORY_PURCHASE, Constant.DIRECTORY_PURCHASEORDER);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            }


        }
    }

    public void openPdfInvoice(String fileName, String groupDirectory, String childDirectory) {
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
}
