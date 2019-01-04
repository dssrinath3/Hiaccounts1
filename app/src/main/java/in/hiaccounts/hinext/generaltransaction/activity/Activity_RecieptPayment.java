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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.generaltransaction.adapter.GTCardPayment_Adapter;
import in.hiaccounts.hinext.generaltransaction.adapter.GTVoucherPayment_Adapter;
import in.hiaccounts.hinext.generaltransaction.model.checkout.CardPaymentList;
import in.hiaccounts.hinext.generaltransaction.model.checkout.CashPayment;
import in.hiaccounts.hinext.generaltransaction.model.checkout.CreditPayment;
import in.hiaccounts.hinext.generaltransaction.model.checkout.GTCheckoutData;
import in.hiaccounts.hinext.generaltransaction.model.checkout.MultiVoucherPayment;
import in.hiaccounts.hinext.generaltransaction.model.checkout.VoucherPayment;
import in.hiaccounts.hinext.generaltransaction.model.response.SaveResponseData;
import in.hiaccounts.pdfgenerator.PdfUtils;
import in.hiaccounts.pdfgenerator.generaltransaction.GTReceiptVoucherPdf;
import in.hiaccounts.pdfgenerator.generaltransaction.GTReceiptVoucherPdfImpl;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.NonScrollListView;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 8/7/2017.
 */

public class Activity_RecieptPayment extends AppCompatActivity implements AdapterView.OnItemClickListener {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edSupplierInvNo)
    EditText edSupplierInvNo;
    @BindView(R.id.llSupplierInvoiceNo)
    LinearLayout llSupplierInvoiceNo;
    @BindView(R.id.ed_totalamount)
    EditText edTotalamount;
    @BindView(R.id.ed_totaldiscount)
    EditText edTotaldiscount;
    @BindView(R.id.ed_tenderedamt)
    EditText edTenderedamt;
    @BindView(R.id.ed_returnamount)
    EditText edReturnamount;
    @BindView(R.id.chbx_cash)
    CheckBox chbxCash;
    @BindView(R.id.chbx_card)
    CheckBox chbxCard;
    @BindView(R.id.chbx_voucher)
    CheckBox chbxVoucher;
    @BindView(R.id.ed_emailid)
    EditText edEmailid;
    @BindView(R.id.ll_email)
    LinearLayout llEmail;
    @BindView(R.id.tv_cashicon)
    TextView tvCashicon;
    @BindView(R.id.ed_cashamounttendered)
    EditText edCashamounttendered;
    @BindView(R.id.ll_cashlayuout)
    LinearLayout llCashlayuout;
    @BindView(R.id.tv_cardicon)
    TextView tvCardicon;
    @BindView(R.id.nonscrolllistview_credit)
    NonScrollListView nonscrolllistviewCredit;
    @BindView(R.id.ll_cardlayuout)
    LinearLayout llCardlayuout;
    @BindView(R.id.tv_voicon)
    TextView tvVoicon;
    @BindView(R.id.nonscrolllistview_voucher)
    NonScrollListView nonscrolllistviewVoucher;
    @BindView(R.id.ll_vopucherlayuout)
    LinearLayout llVopucherlayuout;
    @BindView(R.id.btn_saveprint)
    Button btnSaveprint;
    @BindView(R.id.btn_saveemail)
    Button btnSaveemail;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.ll_bottomlayout)
    LinearLayout llBottomlayout;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    Boolean isInternetPresent = false;
    private String serverUrl;

    Activity activity;
    private ServiceHandler serviceHandler;
    SharedPreference sharedPreference;

    List<CardPaymentList> cardPaymentList = new ArrayList<>();
    List<MultiVoucherPayment> voucherPaymentList = new ArrayList<MultiVoucherPayment>();
    GTCardPayment_Adapter cardPaymentAdapter;
    GTVoucherPayment_Adapter voucherPaymentAdapter;

    GTCheckoutData gtRecieptData;
    public static String TAG = Activity_RecieptPayment.class.getSimpleName();
    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 123;
    private int currentAPIVersion = Build.VERSION.SDK_INT;
    SaveResponseData saveResponseData = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generaltransaction_recieptpayment);
        ButterKnife.bind(this);
        initComponent();
        setonClcik();
    }

    private void setonClcik() {
        if (nonscrolllistviewCredit != null)
            nonscrolllistviewCredit.setOnItemClickListener(this);
        if (nonscrolllistviewVoucher != null)
            nonscrolllistviewVoucher.setOnItemClickListener(this);
    }

    private void initComponent() {
        ButterKnife.bind(this);
        activity = this;
        serverUrl = UtilView.getUrl(activity);
        sharedPreference = SharedPreference.getInstance(this);
        serviceHandler = new ServiceHandler(activity);


        toolbar.setTitle(getResources().getString(R.string.payment));
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
        if (intent != null) {

            gtRecieptData = (GTCheckoutData) intent.getSerializableExtra("checkoutData");
            if (gtRecieptData != null)
                edTotalamount.setText("" + gtRecieptData.getTotalCheckOutamt());

        }
        edTotalamount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.equals("")) {
                    edTotaldiscount.setText("0.00");
                    edTotaldiscount.setError(null);
                } else {
                    try {
                        edTotaldiscount.setError(null);

                    } catch (NumberFormatException e) {
                        edTotaldiscount.setError(getResources().getString(R.string.error_numberformate));
                    }
                }
            }


            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edCashamounttendered.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.equals("")) {
                    edCashamounttendered.setError(null);
                } else {
                    try {
                        edCashamounttendered.setError(null);
                        setTenderedAmount();
                        setReturnAmount();
                    } catch (NumberFormatException e) {
                        edCashamounttendered.setError(getResources().getString(R.string.error_numberformate));
                    }
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edTotaldiscount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.equals("")) {
                    edTotaldiscount.setError(null);
                } else {
                    try {
                        edTotaldiscount.setError(null);
                        setTenderedAmount();
                        setReturnAmount();
                    } catch (NumberFormatException e) {
                        edTotaldiscount.setError(getResources().getString(R.string.error_numberformate));
                    }
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        chbxCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (chbxCash.isChecked()) {
                    if (llCashlayuout != null)
                        llCashlayuout.setVisibility(View.VISIBLE);
                } else {
                    if (llCashlayuout != null)
                        llCashlayuout.setVisibility(View.GONE);

                    if (edCashamounttendered != null)
                        edCashamounttendered.setText("0");

                }
            }
        });


        chbxCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (chbxCard.isChecked()) {
                    if (llCardlayuout != null)
                        llCardlayuout.setVisibility(View.VISIBLE);

                    cardPaymentList.add(new CardPaymentList());

                    cardPaymentAdapter = new GTCardPayment_Adapter(activity, cardPaymentList);
                    if (nonscrolllistviewCredit != null)
                        nonscrolllistviewCredit.setAdapter(cardPaymentAdapter);
                    cardPaymentAdapter.notifyDataSetChanged();


                } else {
                    if (llCardlayuout != null)
                        llCardlayuout.setVisibility(View.GONE);
                    cardPaymentList.clear();
                    cardPaymentAdapter = new GTCardPayment_Adapter(activity, cardPaymentList);
                    if (nonscrolllistviewCredit != null)
                        nonscrolllistviewCredit.setAdapter(cardPaymentAdapter);
                    cardPaymentAdapter.notifyDataSetChanged();

                }
            }
        });

        chbxVoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (chbxVoucher.isChecked()) {
                    if (llVopucherlayuout != null)
                        llVopucherlayuout.setVisibility(View.VISIBLE);
                    voucherPaymentList.add(new MultiVoucherPayment());
                    voucherPaymentAdapter = new GTVoucherPayment_Adapter(activity, voucherPaymentList);
                    if (nonscrolllistviewVoucher != null)
                        nonscrolllistviewVoucher.setAdapter(voucherPaymentAdapter);
                    voucherPaymentAdapter.notifyDataSetChanged();
                } else {
                    if (llVopucherlayuout != null)
                        llVopucherlayuout.setVisibility(View.GONE);
                    voucherPaymentList.clear();
                    voucherPaymentAdapter = new GTVoucherPayment_Adapter(activity, voucherPaymentList);
                    if (nonscrolllistviewVoucher != null)
                        nonscrolllistviewVoucher.setAdapter(voucherPaymentAdapter);
                    voucherPaymentAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    public void setTenderedAmount() {

        double tenderedAmount = 0.00;
        double cashTenderedAmount = 0.00;
        try {
            if (edCashamounttendered.getText().toString().equals("")) {
                cashTenderedAmount = 0.00;
            } else {
                cashTenderedAmount = Double.parseDouble(edCashamounttendered.getText().toString().trim());
            }
        } catch (NumberFormatException e) {
            edCashamounttendered.setError(getResources().getString(R.string.error_numberformate));
        }

        BigDecimal big = new BigDecimal(cashTenderedAmount);
        tenderedAmount = Double.parseDouble(String.valueOf(big.setScale(2, RoundingMode.HALF_UP)));
        for (int i = 0; i < cardPaymentList.size(); i++) {
            tenderedAmount += cardPaymentList.get(i).getCardAmount();
        }
        for (int j = 0; j < voucherPaymentList.size(); j++) {
            tenderedAmount += voucherPaymentList.get(j).getAmt();
        }

        edTenderedamt.setText("" + new BigDecimal(tenderedAmount).setScale(2, BigDecimal.ROUND_HALF_UP));

        setReturnAmount();


    }

    private void setReturnAmount() {

        String txtTenderamt = edTenderedamt.getText().toString();
        String txtTotalamt = edTotalamount.getText().toString();
        String txtDiscountamt = "0.00";
        try {
            txtDiscountamt = edTotaldiscount.getText().toString();

            double tenderedAmt = 0.00;
            if (txtTenderamt.equals("")) {
                tenderedAmt = 0.00;
            } else {
                tenderedAmt = Double.parseDouble(txtTenderamt);
            }
            double totalAmt = 0.00;
            if (txtTotalamt.equals("")) {
                totalAmt = 0.00;
            } else {
                totalAmt = Double.parseDouble(txtTotalamt);
            }


            double discountAmt = 0.00;
            if (txtDiscountamt.equals("")) {
                discountAmt = 0.00;
            } else {
                discountAmt = Double.parseDouble(txtDiscountamt);
            }


            double returnAmt = (tenderedAmt + discountAmt) - totalAmt;

            edReturnamount.setText("" + new BigDecimal(returnAmt).setScale(2, BigDecimal.ROUND_HALF_UP));

        } catch (NumberFormatException e) {
            edTotalamount.setError(getResources().getString(R.string.error_numberformate));
        }


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (view.getId()) {
            case R.id.imageview_addcard:

                cardPaymentList.add(cardPaymentList.size(), new CardPaymentList());
                cardPaymentAdapter = new GTCardPayment_Adapter(activity, cardPaymentList);
                if (nonscrolllistviewCredit != null)
                    nonscrolllistviewCredit.setAdapter(cardPaymentAdapter);
                cardPaymentAdapter.notifyDataSetChanged();

                setTenderedAmount();

                break;

            case R.id.imageview_removecard:
                cardPaymentList.remove(position);
                cardPaymentAdapter = new GTCardPayment_Adapter(activity, cardPaymentList);
                if (nonscrolllistviewCredit != null)
                    nonscrolllistviewCredit.setAdapter(cardPaymentAdapter);
                cardPaymentAdapter.notifyDataSetChanged();
                setTenderedAmount();
                break;

            case R.id.imageview_voucheradd:
                voucherPaymentList.add(voucherPaymentList.size(), new MultiVoucherPayment());

                voucherPaymentAdapter = new GTVoucherPayment_Adapter(activity, voucherPaymentList);
                if (nonscrolllistviewVoucher != null)
                    nonscrolllistviewVoucher.setAdapter(voucherPaymentAdapter);
                voucherPaymentAdapter.notifyDataSetChanged();
                setTenderedAmount();

                break;

            case R.id.imageview_voucherremove:
                voucherPaymentList.remove(position);
                voucherPaymentAdapter = new GTVoucherPayment_Adapter(activity, voucherPaymentList);
                if (nonscrolllistviewVoucher != null)
                    nonscrolllistviewVoucher.setAdapter(voucherPaymentAdapter);
                voucherPaymentAdapter.notifyDataSetChanged();
                setTenderedAmount();

                break;
        }
    }


    @OnClick({R.id.btn_saveprint, R.id.btn_saveemail, R.id.btn_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_saveprint:
                callPrintCheckout();
                break;
            case R.id.btn_saveemail:
                break;
            case R.id.btn_cancel:
                finish();
                break;
        }
    }

    private void callPrintCheckout() {
        String url = serverUrl + "/generalTransaction//1/saveOtherRecieptGt";
        //  String url = UtilView.getRetailUrl(sharedPreference);
        String checkoutData = "";
        PostDataTask postDataTask;
        checkoutData = getCheckoutData();
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
                UtilView.showProgessBar(activity, progressBar);
                postDataTask = new PostDataTask(activity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {

                        if (result != null) {
                            if (result.equals("invalid")) {
                                UtilView.showToast(activity, getResources().getString(R.string.accesstoken_error));
                                Intent intent = new Intent(activity, Activity_Login.class);
                                activity.startActivity(intent);
                                activity.finish();
                            } else {
                                try {
                                    Gson gson = new Gson();
                                    saveResponseData = gson.fromJson(result.toString(), SaveResponseData.class);
                                    if (saveResponseData != null) {
                                        if (currentAPIVersion >= Build.VERSION_CODES.M) {
                                            checkPermission();
                                        } else {
                                            //new PdfGenerationTask().execute();
                                            createPdf();
                                        }
                                    } else {
                                        UtilView.showToast(activity, "Some Error. Please try Again.");
                                    }

                                } catch (Exception e) {
                                    UtilView.showErrorDialog(getResources().getString(R.string.response_error), activity);
                                }

                            }
                        }

                    }
                }, false);
                postDataTask.execute(checkoutData, url, "");


            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
            }
        } else {
            UtilView.gotToLogin(activity);
        }

    }


    private String getCheckoutData() {

        Gson gson = new Gson();
        double totalCheckoutAmt;
        double totalTaxAmt;
        String customerEmail = "";
        String jsonCheckoutData = null;


        String amt = edReturnamount.getText().toString().trim();
        if (amt.equals("")) {
            amt = "0.00";
        }
        double amountReturned = Double.parseDouble(String.valueOf(new BigDecimal(amt).setScale(2, RoundingMode.HALF_UP)));
        String dis = (edTotaldiscount.getText().toString().trim());
        if (dis.equals("")) {
            dis = "0.00";
        }
        double discountAmount = Double.parseDouble(String.valueOf(new BigDecimal(dis).setScale(2, RoundingMode.HALF_UP)));

        String tender = (edTenderedamt.getText().toString().trim());
        if (tender.equals("")) {
            tender = "0.00";
        }
        double totalCPAmountTendered = Double.parseDouble(tender);


        String totalTenderedAmount = edCashamounttendered.getText().toString().trim();
        if (totalTenderedAmount == null || totalTenderedAmount.equals("")) {
            totalTenderedAmount = "0.00";
        }

        BigDecimal big2 = new BigDecimal(totalTenderedAmount);
        double tenderedAmount = Double.parseDouble(String.valueOf(big2.setScale(2, RoundingMode.HALF_UP)));

        CashPayment cashPayment = new CashPayment();
        cashPayment.setTotalCPAmountTendered(tenderedAmount);

        CreditPayment creditPayment = new CreditPayment();
        creditPayment.setCardPaymentList(cardPaymentList);
        VoucherPayment voucherPayment = new VoucherPayment();
        voucherPayment.setMultiVoucherPayments(voucherPaymentList);

        if (gtRecieptData != null) {

            gtRecieptData.setCashPayment(cashPayment);
            gtRecieptData.setDiscountAmount(discountAmount);
            gtRecieptData.setVoucherPayment(voucherPayment);
            gtRecieptData.setCreditPayment(creditPayment);
            gtRecieptData.setTotalTenderedAmount(totalCPAmountTendered);
            gtRecieptData.setAmountReturned(amountReturned);
            jsonCheckoutData = gson.toJson(gtRecieptData).toString();
            UtilView.showLogCat(TAG, "payment checkoutData " + jsonCheckoutData);

        }

        return jsonCheckoutData;


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
        pdfFile = PdfUtils.createFile(saveResponseData.getOrNo() + ".pdf", Constant.DIRECTORY_GT, Constant.DIRECTORY_GTRECEIPT);


        if (pdfFile != null) {

            InputStream logoInputStream = PdfUtils.getLogoInputStream(this, progressBar);
            if (logoInputStream != null) {
                try {
                    GTReceiptVoucherPdf voucherPdf = new GTReceiptVoucherPdfImpl();
                    voucherPdf.generateReceiptVoucherPdf(activity, saveResponseData, new FileOutputStream(pdfFile), logoInputStream);
                    UtilView.hideProgessBar(activity, progressBar);
                    openPdf(saveResponseData.getOrNo() + ".pdf", Constant.DIRECTORY_GT, Constant.DIRECTORY_GTRECEIPT);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    GTReceiptVoucherPdf voucherPdf = new GTReceiptVoucherPdfImpl();
                    voucherPdf.generateReceiptVoucherPdf(activity, saveResponseData, new FileOutputStream(pdfFile), null);
                    UtilView.hideProgessBar(activity, progressBar);
                    openPdf(saveResponseData.getOrNo() + ".pdf", Constant.DIRECTORY_GT, Constant.DIRECTORY_GTRECEIPT);
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
