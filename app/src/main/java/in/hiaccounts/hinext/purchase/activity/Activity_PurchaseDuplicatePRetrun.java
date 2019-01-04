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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hiaccounts.R;
import in.hiaccounts.hinext.purchase.adapter.PurchaseReturnItem_Adapter;
import in.hiaccounts.hinext.purchase.model.purchase_payments.PurchaseAdvancePayment;
import in.hiaccounts.hinext.purchase.model.purchase_saveresponses.Checkout_ResponseData;
import in.hiaccounts.pdfgenerator.PdfUtils;
import in.hiaccounts.pdfgenerator.purchase.purchase_orders.PosA4PurchaseOrderPdfImpl;
import in.hiaccounts.pdfgenerator.purchase.purchase_orders.PosPurchaseOrderPdf;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;
import static in.hiaccounts.R.string.response_error;

/**
 * Created by Prateek on 7/26/2017.
 */

public class Activity_PurchaseDuplicatePRetrun extends AppCompatActivity implements TextWatcher {

    private Checkout_ResponseData invoiceData = null;
    private int currentAPIVersion = Build.VERSION.SDK_INT;
    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 123;
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
    @BindView(R.id.llListView)
    LinearLayout llListView;
    private ArrayList<PurchaseAdvancePayment> returnItemList = new ArrayList<>();
    private static String TAG = Activity_PurchaseDuplicatePRetrun.class.getSimpleName();
    private PurchaseAdvancePayment invData = new PurchaseAdvancePayment();
    private Activity mActivity;
    private Boolean isInternetPresent = false;
    private ServiceHandler serviceHandler;
    private String serverUrl,formno,returnSearch="";
    PurchaseReturnItem_Adapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_duplicateinvoice);
        ButterKnife.bind(this);
        initComponents();
    }

    private void initComponents() {
        ButterKnife.bind(this);
        mActivity = this;
        edSearch.addTextChangedListener(this);
        serverUrl = UtilView.getUrl(mActivity);
        serviceHandler = new ServiceHandler(mActivity);
        toolbar.setTitle("Purchase Return List");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getPurchaseReturnListitem("");

        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    returnSearch = edSearch.getText().toString().trim();
                    getPurchaseReturnListitem(returnSearch);
                }
                return handled;
            }
        });


        listviewInvoices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (view.getId() == R.id.imgview_duplicateprint) {
                    isInternetPresent = serviceHandler.isConnectingToInternet();
                    invData = returnItemList.get(position);
                    formno = invData.getFormNo();
                    if (serverUrl != null) {
                        if (isInternetPresent) {
                            String url = serverUrl + "/purchase//0/" + Constant.FUNTION_GETPURCHASEDUPLICATEPR + "/" + formno;
                            UtilView.showProgessBar(mActivity, progressBar);
                            GetDataTask getInvoicetask = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                                @Override
                                public void onTaskComplete(String result) {

                                    if (result != null) {
                                        Gson gson = new Gson();
                                        try {

                                            invoiceData = gson.fromJson(result.toString(), Checkout_ResponseData.class);
                                            if (invoiceData != null) {
                                                if (invoiceData.getPiData() != null) {

                                                    if (currentAPIVersion >= Build.VERSION_CODES.M) {
                                                        checkPermission();
                                                    } else {
                                                        createPdf();
                                                    }
                                                } else {
                                                    UtilView.showToast(mActivity, "Some Error. Please try again.");
                                                }
                                            }
                                        } catch (Exception e) {
                                            UtilView.showErrorDialog(getResources().getString(R.string.response_error), mActivity);
                                        }
                                    } else {
                                        UtilView.showErrorDialog(getResources().getString(R.string.response_error), mActivity);
                                    }
                                }
                            }, false);
                            getInvoicetask.execute(url, "");
                        } else {
                            UtilView.showToast(mActivity, getResources().getString(R.string.intertnet_status));
                        }
                    } else {
                        UtilView.gotToLogin(mActivity);
                    }
                }
            }
        });
    }

    private void getPurchaseReturnListitem(String search) {
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
                if (progressBar != null)
                    progressBar.setVisibility(View.VISIBLE);

                String url ="";
                if (search.equals("")) {
                    url = serverUrl + "/purchase//0/" + Constant.FUNTION_GETPURCHASERETURN+"/?itemSearchText=&locationId=";

                }

                if (!search.equals("")) {
                    url = serverUrl + "/purchase//0/" + Constant.FUNTION_GETPURCHASERETURN+"/?itemSearchText="+ search.replace(" ", "%20")+"&locationId=";

                }

                GetDataTask getOrderList = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        if (progressBar != null)
                            progressBar.setVisibility(View.GONE);
                        if (result != null) {
                            Gson gson = new Gson();
                            returnItemList = new ArrayList<PurchaseAdvancePayment>();
                            try {
                                JSONArray jsonArray = new JSONArray(result.toString());
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonInovice = jsonArray.getJSONObject(i);
                                    PurchaseAdvancePayment inovice = gson.fromJson(jsonInovice.toString(), PurchaseAdvancePayment.class);
                                    returnItemList.add(inovice);
                                }
                                if (returnItemList != null && returnItemList.size() > 0) {

                                    llListView.setVisibility(View.VISIBLE);

                                    adapter = new PurchaseReturnItem_Adapter(mActivity, returnItemList);
                                    listviewInvoices.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                } else {
                                    llListView.setVisibility(View.GONE);

                                    adapter = new PurchaseReturnItem_Adapter(mActivity, returnItemList);
                                    listviewInvoices.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                    UtilView.showSuccessDialog("No Purchase Return Items Available", mActivity);
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
    public void onClick(View view) {
        returnSearch = edSearch.getText().toString().trim();
        getPurchaseReturnListitem(returnSearch);
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
            if (invoiceData != null) {
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
                    if (invoiceData != null) {
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
        pdfFile = PdfUtils.createFile(invoiceData.getPiData().getPiNo() + ".pdf", Constant.DIRECTORY_DUPLICATEPURCHASE, Constant.DIRECTORY_PURCHASERETURN);

        if (pdfFile != null) {

            InputStream logoInputStream = PdfUtils.getLogoInputStream(mActivity, progressBar);


            if (logoInputStream != null) {
                try {
                    PosPurchaseOrderPdf invoicePdf = new PosA4PurchaseOrderPdfImpl();
                    invoicePdf.generatePurchaseOrderInvoice(mActivity, invoiceData, new FileOutputStream(pdfFile), logoInputStream);
                    UtilView.hideProgessBar(mActivity, progressBar);
                    openPdfInvoice(invoiceData.getPiData().getPiNo() + ".pdf", Constant.DIRECTORY_DUPLICATEPURCHASE, Constant.DIRECTORY_PURCHASERETURN);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    PosPurchaseOrderPdf invoicePdf = new PosA4PurchaseOrderPdfImpl();
                    invoicePdf.generatePurchaseOrderInvoice(mActivity, invoiceData, new FileOutputStream(pdfFile), logoInputStream);
                    UtilView.hideProgessBar(mActivity, progressBar);
                    openPdfInvoice(invoiceData.getPiData().getPiNo() + ".pdf", Constant.DIRECTORY_DUPLICATEPURCHASE, Constant.DIRECTORY_PURCHASERETURN);
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
    public void afterTextChanged(Editable s) {
        if (s.toString().equals("")) {
            UtilView.hideSoftKeyboard(mActivity, edSearch);
            returnSearch = "";
            getPurchaseReturnListitem(returnSearch);
        }
    }
}
