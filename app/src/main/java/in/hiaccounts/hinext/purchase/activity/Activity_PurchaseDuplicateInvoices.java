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
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import in.hiaccounts.R;
import in.hiaccounts.hinext.purchase.adapter.PurchaseDuplicateInvoice_Adapter;
import in.hiaccounts.hinext.purchase.model.purchase_payments.PurchaseAdvancePayment;
import in.hiaccounts.hinext.purchase.model.purchase_saveresponses.Checkout_ResponseData;
import in.hiaccounts.pdfgenerator.PdfUtils;
import in.hiaccounts.pdfgenerator.purchase.purchase_inovices.PosA4PurchaseInvociePdfImpl;
import in.hiaccounts.pdfgenerator.purchase.purchase_inovices.PosPurchaseInvoicePdf;
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

public class Activity_PurchaseDuplicateInvoices extends AppCompatActivity implements TextWatcher {

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
    PurchaseDuplicateInvoice_Adapter adapter;
    private static String TAG = Activity_PurchaseDuplicateInvoices.class.getSimpleName();
    private Activity mActivity;
    private Boolean isInternetPresent = false;
    private ServiceHandler serviceHandler;
    private String serverUrl;
    String invoiceSearch ="";
    Long invoiceId;
    ArrayList<PurchaseAdvancePayment> invoiceList;
    PurchaseAdvancePayment invData = new PurchaseAdvancePayment();

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
        toolbar.setTitle("Purchase Invoice List");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getInvoiceListitem("");

        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    invoiceSearch = edSearch.getText().toString().trim();
                    getInvoiceListitem(invoiceSearch);
                }
                return handled;
            }
        });



        listviewInvoices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                invData = invoiceList.get(position);
                invoiceId = invData.getId();


                if (view.getId() == R.id.imgview_duplicateprint) {
                    isInternetPresent = serviceHandler.isConnectingToInternet();
                    if (serverUrl != null) {
                        if (isInternetPresent) {
                            UtilView.showProgessBar(mActivity, progressBar);
                            String url = serverUrl + "/purchase//0/" + Constant.FUNTION_GETPURCHASEDUPLICATEINVOICE + "/" + invData.getFormNo();
                            GetDataTask getInvoicetask = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                                @Override
                                public void onTaskComplete(String result) {
                                    UtilView.showProgessBar(mActivity, progressBar);
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
                                                    UtilView.showToast(mActivity, getResources().getString(R.string.response_error));
                                                }
                                            } else {
                                                UtilView.showToast(mActivity, getResources().getString(R.string.response_error));
                                            }
                                        } catch (Exception e) {
                                            UtilView.showErrorDialog(getResources().getString(R.string.response_error), mActivity);
                                        }
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

                if (view.getId() == R.id.imgviewCancel) {

                    initCancelDialog(invoiceId);
                }
            }
        });



    }

    private void getInvoiceListitem(String search) {
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
                if (progressBar != null)
                    progressBar.setVisibility(View.VISIBLE);

                String url ="";
                if (search.equals("")) {
                    url = serverUrl + "/purchase//0/" + Constant.FUNTION_GETINVOICES+"?itemSearchText=&locationId=";

                }

                if (!search.equals("")) {
                    url = serverUrl + "/purchase//0/" + Constant.FUNTION_GETINVOICES+"?itemSearchText="+ search.replace(" ", "%20");

                }

                GetDataTask getOrderList = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        if (progressBar != null)
                            progressBar.setVisibility(View.GONE);
                        if (result != null) {
                            Gson gson = new Gson();
                            invoiceList = new ArrayList<PurchaseAdvancePayment>();
                            try {
                                JSONArray jsonArray = new JSONArray(result.toString());
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonInovice = jsonArray.getJSONObject(i);
                                    PurchaseAdvancePayment inovice = gson.fromJson(jsonInovice.toString(), PurchaseAdvancePayment.class);
                                    invoiceList.add(inovice);
                                }
                                if (invoiceList != null && invoiceList.size() > 0) {

                                        llListView.setVisibility(View.VISIBLE);
                                        adapter = new PurchaseDuplicateInvoice_Adapter(mActivity, invoiceList);
                                        listviewInvoices.setAdapter(adapter);
                                        adapter.notifyDataSetChanged();
                                } else {
                                    llListView.setVisibility(View.GONE);

                                    adapter = new PurchaseDuplicateInvoice_Adapter(mActivity, invoiceList);
                                    listviewInvoices.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                    UtilView.showSuccessDialog("No Invoice Available", mActivity);
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


    private void initCancelDialog(final Long id) {
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (isInternetPresent) {
            SweetAlertDialog pDialog = new SweetAlertDialog(mActivity, SweetAlertDialog.WARNING_TYPE);
            pDialog.setTitleText(mActivity.getString(R.string.cancelwarningmsg));
            pDialog.setConfirmText(mActivity.getString(R.string.yesCancel));
            pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(final SweetAlertDialog sDialog) {
                    final SweetAlertDialog pDialog = new SweetAlertDialog(mActivity, SweetAlertDialog.PROGRESS_TYPE);
                    pDialog.getProgressHelper().setBarColor(mActivity.getResources().getColor(R.color.colorPrimary));
                    pDialog.setTitleText(mActivity.getString(R.string.pleasewait));
                    pDialog.setCancelable(false);
                    if (pDialog != null)
                        pDialog.show();



                    JSONObject obj = new JSONObject();
                    try {
                        obj.put("id",id);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    String url = "";

                    url = serverUrl + "/purchase//2/"+ Constant.FUNTION_PURCHASEINVOICECANCEL;
                    PostDataTask postDataTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                        @Override
                        public void onTaskComplete(String result) {
                            if (pDialog != null)
                                pDialog.dismiss();

                            if (result.toString() != null) {
                                if (result.toString().trim().equals("Cancelled SuccessFully")) {
                                    if (sDialog != null) {
                                        sDialog.setTitleText(mActivity.getString(R.string.delconfirm))
                                                            /*.setContentText("Category "+category.getItemCategoryName())*/
                                                .setConfirmText(mActivity.getString(R.string.bntok))
                                                .setConfirmClickListener(null)
                                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);

                                        adapter.notifyDataSetChanged();



                                    }
                                    getInvoiceListitem("");

                                }
                                else {
                                    if (sDialog != null) {
                                        sDialog.setTitleText(mActivity.getString(R.string.delfail))
                                                            /*.setContentText("Category "+category.getItemCategoryName())*/
                                                .setConfirmText(mActivity.getString(R.string.bntok))
                                                .setConfirmClickListener(null)
                                                .changeAlertType(SweetAlertDialog.ERROR_TYPE);

                                    }
                                    getInvoiceListitem("");
                                }

                                  /*  Gson gson = new Gson();
                                    try {

                                        PurchaseAdvancePayment data = gson.fromJson(result.toString(), PurchaseAdvancePayment.class);
                                        if (data != null) {
                                            Toast.makeText(Activity_PurchaseDuplicateInvoices.this, "ghgh", Toast.LENGTH_SHORT).show();

                                        }
                                    } catch (Exception e) {
                                        UtilView.showErrorDialog(mActivity.getResources().getString(R.string.response_error), mActivity);
                                    }*/

                            } else {

                                UtilView.showToast(mActivity, mActivity.getResources().getString(R.string.response_error));
                            }
                        }
                    }, false);
                    postDataTask.execute(obj.toString(), url, Constant.FUNTION_ADVANCEPARTIALPAYMENT);

                }
            });
            if (pDialog != null)
                pDialog.show();
        } else {
            UtilView.showErrorDialog(mActivity.getResources().getString(R.string.intertnet_status), mActivity);
        }

    }


    @OnClick(R.id.imgviewSearch)
    public void onClick() {

        invoiceSearch = edSearch.getText().toString().trim();
        getInvoiceListitem(invoiceSearch);


    }



/*
    private void initEmaiDialog(final int position) {
        // custom dialog
        final Dialog dialog = new Dialog(mActivity);
        dialog.setContentView(R.layout.dialog_email);

        Button btnClose = (Button) dialog.findViewById(R.id.btn_close);
        ImageView imageview_close = (ImageView) dialog.findViewById(R.id.imageview_close);
        Button btnSave = (Button) dialog.findViewById(R.id.btnSave);
        final EditText edMail = (EditText) dialog.findViewById(R.id.edMail);


        String custmoreEmail = posInvoices.get(position).getCustomerEmail();


        if (custmoreEmail != null) {
            edMail.setText(custmoreEmail);
        }

        edMail.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    handled = true;
                    String email = edMail.getText().toString();

                    if (!email.equals("")) {

                        if (Validation.isValidEmail(email)) {
                            String url = serverUrl + "/retail//" + Constant.FUNTION_MAILDUPLICATEINVOICE + "/" + posInvoices.get(position).getFormNo() + "?customerEmail=" + email;

                            if (serverUrl != null) {
                                if (isInternetPresent) {
                                    GetDataTask getInvoicetask = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                                        @Override
                                        public void onTaskComplete(String result) {
                                            if (dialog != null)
                                                dialog.dismiss();
                                            if (result != null) {
                                                if (result.equals("Mail sent successfully")) {
                                                    UtilView.showToast(mActivity, "Mail sent successfully");
                                                } else {
                                                    UtilView.showToast(mActivity, "Please try again.");
                                                }
                                            } else {
                                                UtilView.showToast(mActivity, getResources().getString(R.string.response_error));
                                            }
                                        }
                                    }, true);
                                    getInvoicetask.execute(url, "");

                                } else {
                                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                                }

                            } else {
                                UtilView.gotToLogin(mActivity);
                            }

                        } else {
                            edMail.setError(getResources().getString(R.string.err_msg_email));
                        }
                    } else {
                        edMail.setError(getResources().getString(R.string.err_msg));
                    }
                }
                return handled;
            }
        });

        imageview_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog != null)
                    dialog.dismiss();
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog != null)
                    dialog.dismiss();
            }
        });

        edMail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (Validation.isValidEmail(s.toString())) {
                    edMail.setError(null);
                } else {
                    edMail.setError(getResources().getString(R.string.err_msg_email));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = edMail.getText().toString();

                if (!email.equals("")) {
                    if (Validation.isValidEmail(email)) {
                        if (serverUrl != null) {
                            isInternetPresent = serviceHandler.isConnectingToInternet();
                            if (isInternetPresent) {
                                String url = serverUrl + "/retail//" + Constant.FUNTION_MAILDUPLICATEINVOICE + "/" + posInvoices.get(position).getFormNo() + "?customerEmail=" + email;
                                GetDataTask getInvoicetask = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                                    @Override
                                    public void onTaskComplete(String result) {
                                        if (dialog != null)
                                            dialog.dismiss();
                                        if (result != null) {
                                            if (result.equals("Mail sent successfully")) {
                                                UtilView.showToast(mActivity, "Mail sent successfully");
                                            } else {
                                                UtilView.showToast(mActivity, "Please try again.");
                                            }
                                        } else {
                                            UtilView.showToast(mActivity, getResources().getString(R.string.response_error));
                                        }

                                    }
                                }, true);
                                getInvoicetask.execute(url, "");

                            } else {
                                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                            }

                        } else {
                            UtilView.gotToLogin(mActivity);
                        }

                    } else {
                        edMail.setError(getResources().getString(R.string.err_msg_email));
                    }
                } else {
                    edMail.setError(getResources().getString(R.string.err_msg));
                }
            }
        });
        if (dialog != null)
            dialog.show();

    }*/

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
            if (invoiceData != null) {
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
                    if (invoiceData != null) {
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
        pdfFile = PdfUtils.createFile("Invoice" + invoiceData.getPiData().getSupplierInvNo() + ".pdf", Constant.DIRECTORY_DUPLICATEPURCHASE, Constant.DIRECTORY_PURCHASEINVOICES);

        if (pdfFile != null) {

            InputStream logoInputStream = PdfUtils.getLogoInputStream(this, progressBar);

            if (logoInputStream != null) {
                try {
                    PosPurchaseInvoicePdf invoicePdf = new PosA4PurchaseInvociePdfImpl();
                    invoicePdf.generatePurchaseTaxInvoice(mActivity, invoiceData, new FileOutputStream(pdfFile), logoInputStream);
                    UtilView.hideProgessBar(mActivity, progressBar);
                    openPdfInvoice("Invoice" + invoiceData.getPiData().getSupplierInvNo() + ".pdf", Constant.DIRECTORY_DUPLICATEPURCHASE, Constant.DIRECTORY_PURCHASEINVOICES);


                } catch (IOException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    PosPurchaseInvoicePdf invoicePdf = new PosA4PurchaseInvociePdfImpl();
                    invoicePdf.generatePurchaseTaxInvoice(mActivity, invoiceData, new FileOutputStream(pdfFile), logoInputStream);
                    UtilView.hideProgessBar(mActivity, progressBar);
                    openPdfInvoice("Invoice" + invoiceData.getPiData().getSupplierInvNo() + ".pdf", Constant.DIRECTORY_DUPLICATEPURCHASE, Constant.DIRECTORY_PURCHASEINVOICES);

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
/*
            Intent intent = new Intent(this, NavigationDrawer_Activity.class);
            intent.putExtra(Constant.NAVIGATION_GROUPHEADER, Constant.NAVIGATION_GROUP_PURCHASE);
            startActivity(intent);
            finish();*/

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
            invoiceSearch = "";
            getInvoiceListitem(invoiceSearch);
        }
    }
}
