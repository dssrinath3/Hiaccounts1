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
import java.math.BigDecimal;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import in.hiaccounts.R;
import in.hiaccounts.hinext.purchase.adapter.PurchaseDuplicatePO_Adapter;
import in.hiaccounts.hinext.purchase.model.purchase_payments.PurchaseAdvancePayment;
import in.hiaccounts.hinext.purchase.model.purchase_pos.PurchasePosCartItem;
import in.hiaccounts.hinext.purchase.model.purchase_pos.PurchasePosCreator;
import in.hiaccounts.hinext.purchase.model.purchase_pos.PurchasePosHelper;
import in.hiaccounts.hinext.purchase.model.purchase_pos.SelectedItemsList;
import in.hiaccounts.hinext.purchase.model.purchase_purchaseorder.PurchaseInvokeOrderData;
import in.hiaccounts.hinext.purchase.model.purchase_purchaseorder.PurchaseOrderData;
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

public class Activity_PurchaseDuplicatePO extends AppCompatActivity implements TextWatcher {

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
    private ArrayList<PurchaseAdvancePayment> posInvoices = new ArrayList<>();
    private static String TAG = Activity_PurchaseDuplicatePO.class.getSimpleName();
    private ArrayList<PurchaseAdvancePayment> orderList;
    private PurchaseAdvancePayment invData = new PurchaseAdvancePayment();
    private PurchasePosCreator purchasePosCreator;
    private Activity mActivity;
    private Boolean isInternetPresent = false;
    private ServiceHandler serviceHandler;
    private String serverUrl;
    private PurchaseDuplicatePO_Adapter adapter;
    private String orderSearch="",formno;
    private Long orderId;



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
        serverUrl = UtilView.getUrl(mActivity);
        edSearch.addTextChangedListener(this);
        serviceHandler = new ServiceHandler(mActivity);
        purchasePosCreator = PurchasePosHelper.getPosCreator();
        toolbar.setTitle("Purchase Order List");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getPurchaseOrderListitem("");

        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    orderSearch = edSearch.getText().toString().trim();
                    getPurchaseOrderListitem(orderSearch);
                }
                return handled;
            }
        });


        listviewInvoices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                invData = orderList.get(position);
                formno = invData.getFormNo();

                if (view.getId() == R.id.imgview_duplicateprint) {
                    isInternetPresent = serviceHandler.isConnectingToInternet();
                    if (serverUrl != null) {
                        if (isInternetPresent) {

                            String url = serverUrl + "/purchase//0/" + Constant.FUNTION_GETPURCHASEDUPLICATEPO + "/" + invData.getFormNo();
                            GetDataTask getInvoicetask = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                                @Override
                                public void onTaskComplete(String result) {
                                    try {

                                        Gson gson = new Gson();
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
                                        } else {
                                            UtilView.showErrorDialog(getResources().getString(R.string.response_error), mActivity);
                                        }

                                    } catch (Exception e) {
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
                if (view.getId() == R.id.imgviewCancel) {
                    initCancelDialog(formno);
                }
                if (view.getId() == R.id.imgviewEdit) {
                    initEditDialog(position);
                }
            }
        });

    }

    private void initEditDialog(int position) {
        invData = orderList.get(position);
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
                if (progressBar != null)
                    progressBar.setVisibility(View.VISIBLE);
                String url = serverUrl + "/purchase//0/" + "getPurchaseOrder" + "/" + invData.getFormNo();
                GetDataTask getOrderDetail = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        if (result != null) {
                            if (progressBar != null)
                                progressBar.setVisibility(View.GONE);
                            try {
                                purchasePosCreator.clear();
                                Gson gson = new Gson();

                                PurchaseOrderData orderData = gson.fromJson(result.toString(), PurchaseOrderData.class);
                                if (orderData!=null){
                                    if (orderData.getSelectedItemsList()!=null && orderData.getSelectedItemsList().size()>0){
                                        for (int i=0;i<orderData.getSelectedItemsList().size();i++){
                                            PurchaseInvokeOrderData item=orderData.getSelectedItemsList().get(i);
                                            SelectedItemsList invokeItems = new SelectedItemsList();
                                            invokeItems.setItemName(item.getItemName());
                                            invokeItems.setItemId(item.getItemId());
                                            invokeItems.setUnitPrice(item.getUnitPrice());
                                            invokeItems.setQty("" + item.getQty());
                                            invokeItems.setItemQuantity(item.getQty());
                                            invokeItems.setReturnQty(item.getReturnQty());
                                            invokeItems.setRemainingQty("" + item.getRemainingQty());
                                            invokeItems.setAmtexclusivetax(item.getAmtexclusivetax());
                                            invokeItems.setTaxid(item.getTaxid());
                                            invokeItems.setTaxpercent("" + item.getTaxpercent());
                                            invokeItems.setTaxamt(item.getTaxamt());
                                            invokeItems.setAmtinclusivetax(item.getAmtinclusivetax());
                                            invokeItems.setDiscountAmt(item.getDiscountAmt());
                                            invokeItems.setItemDescription(item.getItemDescription());
                                            invokeItems.setTaxName(item.getTaxName());
                                            invokeItems.setItemCode(item.getItemCode());
                                            invokeItems.setCess(item.getCess());
                                            invokeItems.setTaxAmountSplit("" + item.getTaxPercentageSplit());
                                            invokeItems.setCessTaxAmt(item.getCessTaxAmt());
                                            invokeItems.setHsnCode(item.getHsnCode());
                                            invokeItems.setUomName(item.getUomName());
                                            invokeItems.setItemTotalAmount(item.getAmtinclusivetax());
                                            invokeItems.setPrice(BigDecimal.valueOf(item.getUnitPrice()));

                                            purchasePosCreator.addItem(new PurchasePosCartItem(invokeItems, 1), invokeItems.getItemId());
                                        }
                                    }

                                    Intent returnIntent = new Intent();
                                    mActivity.setResult(Activity.RESULT_OK, returnIntent);
                                    mActivity.finish();

                                } else {
                                    UtilView.showToast(mActivity, "No Items for the selected Order Number");
                                }

                            } catch (Exception e) {
                                UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.error_null), mActivity);
                        }
                    }
                }, false);
                getOrderDetail.execute(url, "");
            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
            }
        } else {
            UtilView.gotToLogin(mActivity);
        }

    }

    private void initCancelDialog(final String formno) {

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

                    String url = "";

                    url = serverUrl + "/purchase//1/"+ Constant.FUNTION_PURCHASEORDERCANCEL+"?formNo="+formno;
                    GetDataTask getDataTask = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                        @Override
                        public void onTaskComplete(String result) {
                            if (pDialog != null)
                                pDialog.dismiss();

                            if (result.toString() != null) {
                                    Gson gson = new Gson();
                                    try {

                                        PurchaseAdvancePayment data = gson.fromJson(result.toString(), PurchaseAdvancePayment.class);
                                        if (data != null) {
                                            if (data.toString().trim() !=null) {
                                                if (sDialog != null) {
                                                    sDialog.setTitleText(mActivity.getString(R.string.delconfirm))
                                                            /*.setContentText("Category "+category.getItemCategoryName())*/
                                                            .setConfirmText(mActivity.getString(R.string.bntok))
                                                            .setConfirmClickListener(null)
                                                            .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);

                                                    adapter.notifyDataSetChanged();



                                                }
                                                getPurchaseOrderListitem("");

                                            }
                                            else {
                                                if (sDialog != null) {
                                                    sDialog.setTitleText(mActivity.getString(R.string.delfail))
                                                            /*.setContentText("Category "+category.getItemCategoryName())*/
                                                            .setConfirmText(mActivity.getString(R.string.bntok))
                                                            .setConfirmClickListener(null)
                                                            .changeAlertType(SweetAlertDialog.ERROR_TYPE);

                                                }
                                                getPurchaseOrderListitem("");
                                            }

                                        }
                                    } catch (Exception e) {
                                        UtilView.showErrorDialog(mActivity.getResources().getString(R.string.response_error), mActivity);
                                    }

                            } else {

                                UtilView.showToast(mActivity, mActivity.getResources().getString(R.string.response_error));
                            }
                        }
                    }, false);
                    getDataTask.execute(url, "");

                }
            });
            if (pDialog != null)
                pDialog.show();
        } else {
            UtilView.showErrorDialog(mActivity.getResources().getString(R.string.intertnet_status), mActivity);
        }
    }

    private void getPurchaseOrderListitem(String search) {
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
                if (progressBar != null)
                    progressBar.setVisibility(View.VISIBLE);

                String url ="";

                if (search.equals("")) {
                    url = serverUrl + "/purchase//0/" + Constant.FUNTION_GETPURCHASEORDER+"?itemSearchText=&type=Print&locationId=";

                }

                if (!search.equals("")) {
                    url = serverUrl + "/purchase//0/" + Constant.FUNTION_GETPURCHASEORDER+"?itemSearchText="+ search.replace(" ", "%20")+"&type=Print&locationId=";

                }

                GetDataTask getOrderList = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        if (progressBar != null)
                            progressBar.setVisibility(View.GONE);
                        if (result != null) {
                            Gson gson = new Gson();
                            orderList = new ArrayList<PurchaseAdvancePayment>();
                            try {
                                JSONArray jsonArray = new JSONArray(result.toString());
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonInovice = jsonArray.getJSONObject(i);
                                    PurchaseAdvancePayment inovice = gson.fromJson(jsonInovice.toString(), PurchaseAdvancePayment.class);
                                    orderList.add(inovice);
                                }
                                if (orderList != null && orderList.size() > 0) {

                                    llListView.setVisibility(View.VISIBLE);

                                    adapter = new PurchaseDuplicatePO_Adapter(mActivity, orderList);
                                    listviewInvoices.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                } else {
                                    llListView.setVisibility(View.GONE);

                                    adapter = new PurchaseDuplicatePO_Adapter(mActivity, orderList);
                                    listviewInvoices.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                    UtilView.showSuccessDialog("No Purchase Order Available", mActivity);
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
        pdfFile = PdfUtils.createFile(invoiceData.getPiData().getPiNo() + ".pdf", Constant.DIRECTORY_DUPLICATEPURCHASE, Constant.DIRECTORY_PURCHASEORDER);

        if (pdfFile != null) {

            InputStream logoInputStream = PdfUtils.getLogoInputStream(mActivity, progressBar);


            if (logoInputStream != null) {
                try {
                    PosPurchaseOrderPdf invoicePdf = new PosA4PurchaseOrderPdfImpl();
                    invoicePdf.generatePurchaseOrderInvoice(mActivity, invoiceData, new FileOutputStream(pdfFile), logoInputStream);
                    UtilView.hideProgessBar(mActivity, progressBar);
                    openPdfInvoice(invoiceData.getPiData().getPiNo() + ".pdf", Constant.DIRECTORY_DUPLICATEPURCHASE, Constant.DIRECTORY_PURCHASEORDER);


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
                    openPdfInvoice(invoiceData.getPiData().getPiNo() + ".pdf", Constant.DIRECTORY_DUPLICATEPURCHASE, Constant.DIRECTORY_PURCHASEORDER);

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

    @OnClick(R.id.imgviewSearch)
    public void onClick(View view) {
        orderSearch = edSearch.getText().toString().trim();
        getPurchaseOrderListitem(orderSearch);
/*
        switch (view.getId()){

            case R.id.imgviewSearch:
                String search=edSearch.getText().toString().trim();
                if (search.toString() != null && !search.toString().equals("")) {
                    adapter.getFilter().filter(search.toString());
                } else {
                    adapter = new PurchaseDuplicatePO_Adapter(mActivity, posInvoices);
                    listviewInvoices.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                }
                break;

        }*/
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.toString() != null && !s.toString().equals("")) {
            adapter.getFilter().filter(s.toString());
        } else {
            adapter = new PurchaseDuplicatePO_Adapter(mActivity, posInvoices);
            listviewInvoices.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.toString().equals("")) {
            UtilView.hideSoftKeyboard(mActivity, edSearch);
            orderSearch = "";
            getPurchaseOrderListitem(orderSearch);
        }

    }

}
