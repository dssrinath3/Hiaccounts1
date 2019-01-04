package in.hiaccounts.hinext.sales.activity;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lowagie.text.DocumentException;
import com.rey.material.widget.ProgressView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hiaccounts.R;
import in.hiaccounts.hinext.sales.adapter.SalesPaymentReceipt_Adapter;
import in.hiaccounts.hinext.sales.model.printlist.payement_receipt.PaymentRecieptDatum;
import in.hiaccounts.hinext.sales.model.printlist.payement_receipt.PaymentRecieptDatumData;
import in.hiaccounts.hinext.sales.model.printlist.payement_receipt.PrintPaymentReceiptData;
import in.hiaccounts.pdfgenerator.PdfUtils;
import in.hiaccounts.pdfgenerator.sales.payment.PosA4ReceiptPaymentPdfImpl;
import in.hiaccounts.pdfgenerator.sales.payment.PosRecieptPaymentPdf;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

import static in.hiaccounts.R.string.response_error;

/**
 * Created by Prateek on 7/26/2017.
 */

public class Activity_PaymentReceipt extends AppCompatActivity implements TextWatcher {
    private static String TAG = Activity_PaymentReceipt.class.getSimpleName();
    @BindView(R.id.edSearch)
    EditText edSearch;
    @BindView(R.id.imgviewSearch)
    ImageView imgviewSearch;
    @BindView(R.id.listviewInvoices)
    ListView listviewInvoices;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.llListview)
    LinearLayout llListview;




    private Activity mActivity;
    private ServiceHandler serviceHandler;
    Boolean isInternetPresent = false;
    private int currentAPIVersion = Build.VERSION.SDK_INT;
    private PrintPaymentReceiptData printPaymnetResponseData = null;
    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 123;
    private String serverUrl,paymentSearch="";
    private SalesPaymentReceipt_Adapter adapter;
    private List<PaymentRecieptDatumData> paymentRecieptData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_paymentreceipt);
        ButterKnife.bind(this);
        initComponents();
    }

    private void initComponents() {
        ButterKnife.bind(this);
        mActivity = this;
        edSearch.addTextChangedListener(this);
        serverUrl = UtilView.getUrl(mActivity);
        serviceHandler = new ServiceHandler(mActivity);
        toolbar.setTitle(getResources().getString(R.string.menu_recieptpayment));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getSalesReceivedPaymentItemList("");

        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    paymentSearch = edSearch.getText().toString().trim();
                    getSalesReceivedPaymentItemList(paymentSearch);
                }
                return handled;
            }
        });

        listviewInvoices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (view.getId() == R.id.imgviewReceiptPrint) {
                    isInternetPresent = serviceHandler.isConnectingToInternet();
                    if (serverUrl != null) {
                        if (isInternetPresent) {
                            UtilView.showProgessBar(mActivity, progressBar);
                            String url = serverUrl + "/retail//" + Constant.FUNTION_GETSALESPAYMENTLIST +"?paymentId="+ paymentRecieptData.get(position).getPaymenetId();
                            PostDataTask getOrderList = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                                @Override
                                public void onTaskComplete(String result) {
                                    UtilView.hideProgessBar(mActivity, progressBar);
                                    if (result != null) {
                                        Gson gson = new Gson();
                                        try {

                                            printPaymnetResponseData = gson.fromJson(result.toString(), PrintPaymentReceiptData.class);
                                            if (printPaymnetResponseData != null) {
                                                if (currentAPIVersion >= Build.VERSION_CODES.M) {
                                                    checkPermission();
                                                } else {
                                                    createPaymentPdf();
                                                }
                                            } else {
                                                UtilView.showLogCat(TAG, getResources().getString(R.string.response_error));
                                            }

                                        } catch (Exception e) {
                                            UtilView.showErrorDialog(getResources().getString(R.string.response_error), mActivity);
                                        }
                                    } else {
                                        UtilView.showErrorDialog(getResources().getString(R.string.response_error), mActivity);
                                    }

                                }
                            }, false);
                            getOrderList.execute(new Gson().toString(), url, "");
                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                        }
                    } else {
                        UtilView.gotToLogin(mActivity);
                    }
                }

            }

        });


    }

    private void getSalesReceivedPaymentItemList(String search) {

        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
                if (progressBar != null)
                    progressBar.setVisibility(View.VISIBLE);

                String url ="";
                if (search.equals("")) {
                    url = serverUrl + "/retail//" + Constant.FUNTION_GETRECIEPTDETAIL+"/?itemSearchText=&locationId=";

                }

                if (!search.equals("")) {
                    url = serverUrl + "/retail//" + Constant.FUNTION_GETRECIEPTDETAIL+"/?itemSearchText="+ search.replace(" ", "%20");

                }

                GetDataTask getOrderList = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        if (progressBar != null)
                            progressBar.setVisibility(View.GONE);
                        if (result != null) {
                            Gson gson = new Gson();
                            paymentRecieptData = new ArrayList<PaymentRecieptDatumData>();
                            try {
                                JSONArray jsonArray = new JSONArray(result.toString());
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonInovice = jsonArray.getJSONObject(i);

                                    PaymentRecieptDatum inovice = gson.fromJson(jsonInovice.toString(), PaymentRecieptDatum.class);

                                    PaymentRecieptDatumData paymentData=new PaymentRecieptDatumData();

                                    if (inovice != null) {
                                        if (inovice.getSino()!=null){
                                            paymentData.setFormNumber(inovice.getSino());
                                        }
                                        if (inovice.getCustomerName() != null) {
                                                paymentData.setCustomerName(inovice.getCustomerName());
                                        }
                                        paymentData.setTotalAmount(""+inovice.getTotalReceivable());
                                    }

                                    paymentData.setAmountPaid("" + inovice.getAmountPaid());
                                    paymentData.setRemaininAmt(String.valueOf(inovice.getTotalReceivable() - inovice.getAmountPaid()));
                                    paymentData.setPaymenetId(inovice.getPaymenetId());
                                    paymentRecieptData.add(paymentData);
                                }
                                if (paymentRecieptData != null && paymentRecieptData.size() > 0) {

                                    llListview.setVisibility(View.VISIBLE);
                                    adapter = new SalesPaymentReceipt_Adapter(mActivity, paymentRecieptData);
                                    listviewInvoices.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();

                                } else {

                                    paymentRecieptData.clear();
                                    llListview.setVisibility(View.GONE);
                                    adapter = new SalesPaymentReceipt_Adapter(mActivity, paymentRecieptData);
                                    listviewInvoices.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                    UtilView.showSuccessDialog("No Reciept Item Available", mActivity);
                                }
                            } catch (Exception ex) {
                                UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                        }
                    }

                }, false);
                getOrderList.execute(url, "");
            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
            }
        } else {
            UtilView.gotToLogin(mActivity);
        }
    }




    @OnClick(R.id.imgviewSearch)
    public void onClick() {
        paymentSearch = edSearch.getText().toString().trim();
        getSalesReceivedPaymentItemList(paymentSearch);
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

            if (printPaymnetResponseData != null) {
                createPaymentPdf();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (ActivityCompat.checkSelfPermission(this, permissions[0]) == PackageManager.PERMISSION_GRANTED) {
            switch (requestCode) {
                case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE:

                    if (printPaymnetResponseData != null) {
                        createPaymentPdf();
                    }
                    break;
            }
        } else {
            Toast.makeText(this, "Permisson Deny", Toast.LENGTH_LONG).show();
        }
    }

    private void createPaymentPdf() {
        File pdfFile = null;
        pdfFile = PdfUtils.createFile(printPaymnetResponseData.getLocationId() + ".pdf", Constant.DIRECTORY_DUPLICATESALES, Constant.DIRECTORY_SALESPAYMENTLIST);
        if (pdfFile != null) {
            InputStream logoInputStream = PdfUtils.getLogoInputStream(this, progressBar);
            if (logoInputStream != null) {
                try {
                    PosRecieptPaymentPdf invoicePdf = new PosA4ReceiptPaymentPdfImpl();
                    invoicePdf.generatePaymentPdf(mActivity, printPaymnetResponseData, new FileOutputStream(pdfFile), logoInputStream);
                    openPdfInvoice(printPaymnetResponseData.getLocationId() + ".pdf", Constant.DIRECTORY_DUPLICATESALES, Constant.DIRECTORY_SALESPAYMENTLIST);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    PosRecieptPaymentPdf invoicePdf = new PosA4ReceiptPaymentPdfImpl();
                    invoicePdf.generatePaymentPdf(this, printPaymnetResponseData, new FileOutputStream(pdfFile), null);
                    openPdfInvoice(printPaymnetResponseData.getLocationId() + ".pdf", Constant.DIRECTORY_DUPLICATESALES, Constant.DIRECTORY_SALESPAYMENTLIST);
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

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (editable.toString().equals("")) {
            UtilView.hideSoftKeyboard(mActivity, edSearch);
            paymentSearch = "";
            getSalesReceivedPaymentItemList(paymentSearch);
        }
    }
}
