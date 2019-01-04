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
import cn.pedant.SweetAlert.SweetAlertDialog;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.application.activity.NavigationDrawer_Activity;
import in.hiaccounts.hinext.checkout.model.sales.Checkout_Sales_ResponseData;
import in.hiaccounts.hinext.purchase.model.purchase_saveresponses.Checkout_ResponseData;
import in.hiaccounts.hinext.sales.adapter.SalesDuplicateSalesOrder_Adapter;
import in.hiaccounts.hinext.sales.model.checkout.SalesSaveData;
import in.hiaccounts.hinext.sales.model.checkout.SelectedItemsList;
import in.hiaccounts.hinext.sales.model.printlist.duplicate_salesorder.Duplicate_SalesOrder;
import in.hiaccounts.hinext.sales.model.sales_pos.SalesPosCartItem;
import in.hiaccounts.hinext.sales.model.sales_pos.SalesPosCreator;
import in.hiaccounts.hinext.sales.model.sales_pos.SalesPosHelper;
import in.hiaccounts.pdfgenerator.PdfUtils;
import in.hiaccounts.pdfgenerator.sales.salesorder.PosA4SalesSaveOrderPdfImpl;
import in.hiaccounts.pdfgenerator.sales.salesorder.PosSalesSaveOrderPdf;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

import static in.hiaccounts.R.string.response_error;

/**
 * Created by Prateek on 7/26/2017.
 */

public class Activity_SalesDuplicateSalesOrder extends AppCompatActivity implements TextWatcher {
    private static String TAG = Activity_SalesDuplicateSalesOrder.class.getSimpleName();

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

    private Checkout_Sales_ResponseData saveSalesOrderResponse = null;
    private int currentAPIVersion = Build.VERSION.SDK_INT;
    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 123;
    private List<Duplicate_SalesOrder> listData;

    private Activity mActivity;
    private Boolean isInternetPresent = false;
    private ServiceHandler serviceHandler;
    private String serverUrl,salesOrderSearch="",formno="";
    SalesDuplicateSalesOrder_Adapter adapter;
    Duplicate_SalesOrder salesOrder = new Duplicate_SalesOrder();
    private SalesPosCreator salesPosCreator;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_duplicatesalesorder);
        ButterKnife.bind(this);
        initComponents();
    }

    private void initComponents() {
        ButterKnife.bind(this);
        mActivity = this;
        edSearch.addTextChangedListener(this);
        serverUrl=UtilView.getUrl(mActivity);
        serviceHandler = new ServiceHandler(mActivity);
        salesPosCreator = SalesPosHelper.getPosCreator();
        toolbar.setTitle(getResources().getString(R.string.menu_duplicatesalesorderlist));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getSalesOrderListitem("");

        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    salesOrderSearch = edSearch.getText().toString().trim();
                    getSalesOrderListitem(salesOrderSearch);
                }
                return handled;
            }
        });

        listviewInvoices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                salesOrder = listData.get(position);
                formno = salesOrder.getFormNo();
                if (view.getId() == R.id.imgview_duplicateprint) {

                    isInternetPresent = serviceHandler.isConnectingToInternet();
                    if (serverUrl!=null) {
                        if (isInternetPresent) {
                            UtilView.showProgessBar(mActivity, progressBar);
                            String url = serverUrl + "/retail//" + Constant.FUNTION_GETDUPLICATESALESORDER + "?invoiceNo=" + formno;
                            GetDataTask getInvoicetask = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                                @Override
                                public void onTaskComplete(String result) {
                                    UtilView.hideProgessBar(mActivity, progressBar);
                                    Gson gson = new Gson();
                                    try {
                                        saveSalesOrderResponse = gson.fromJson(result.toString(), Checkout_Sales_ResponseData.class);
                                        if (saveSalesOrderResponse != null) {
                                            if (saveSalesOrderResponse.getSiData() != null) {
                                                if (currentAPIVersion >= Build.VERSION_CODES.M) {
                                                    checkPermission();
                                                } else {
                                                    createPdf();
                                                }
                                            } else {
                                                UtilView.showToast(mActivity, "Save Sales Order doesn't save. Please try again");
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
                    }
                    else {
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
        isInternetPresent = serviceHandler.isConnectingToInternet();

        salesOrder = listData.get(position);
        if (isInternetPresent) {
            UtilView.showProgessBar(mActivity, progressBar);

            String url = serverUrl + "/retail//" + Constant.FUNTION_GETSALESSELECTINVOKEORDER+"?invoiceNo="+salesOrder.getFormNo();

            if (serverUrl != null) {
                isInternetPresent = serviceHandler.isConnectingToInternet();
                if (isInternetPresent) {
                    UtilView.showProgessBar(mActivity, progressBar);
                    GetDataTask getDataTask = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                        @Override
                        public void onTaskComplete(String result) {
                            UtilView.hideProgessBar(mActivity, progressBar);

                            if (result != null) {
                                if (result.equals("invalid")) {
                                    UtilView.showToast(mActivity, getResources().getString(R.string.accesstoken_error));
                                    Intent intent = new Intent(mActivity, Activity_Login.class);
                                    mActivity.startActivity(intent);
                                    mActivity.finish();
                                } else {
                                    salesPosCreator.clear();
                                    Gson gson = new Gson();
                                    try {
                                        SalesSaveData orderData = gson.fromJson(result.toString(), SalesSaveData.class);
                                        if (orderData!=null){

                                            if (orderData.getSelectedItemsList()!=null && orderData.getSelectedItemsList().size()>0){
                                                for (int i=0;i<orderData.getSelectedItemsList().size();i++){
                                                    SelectedItemsList item = orderData.getSelectedItemsList().get(i);
                                                    SelectedItemsList invokeItems = new SelectedItemsList();
                                                    invokeItems.setItemName(item.getItemName());
                                                    invokeItems.setItemId(item.getItemId());
                                                    invokeItems.setUnitPrice(item.getUnitPrice());
                                                    invokeItems.setQty(item.getQty());
                                                    invokeItems.setItemQuantity(item.getQty());
                                                    invokeItems.setReturnQty(item.getReturnQty());
                                                    invokeItems.setRemainingQty(item.getRemainingQty());
                                                    invokeItems.setAmtexclusivetax(item.getAmtexclusivetax());
                                                    invokeItems.setTaxid(item.getTaxid());
                                                    invokeItems.setTaxpercent(String.valueOf(item.getTaxpercent()));
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
                                                    invokeItems.setSerializableStatus(item.getSerializableStatus());
                                                    invokeItems.setSalesOrderId(item.getSalesOrderId());
                                                    invokeItems.setSalesOrderDetailsId(item.getSalesOrderDetailsId());
                                                    invokeItems.setInclusiveJSON(item.getInclusiveJSON());
                                                    salesPosCreator.addItem(new SalesPosCartItem(invokeItems, 1));
                                                }
                                            }
                                            Intent returnIntent = new Intent();
                                            returnIntent.putExtra("salesorderId",orderData.getSalesOrderId());
                                            mActivity.setResult(Activity.RESULT_OK, returnIntent);
                                            mActivity.finish();

                                        } else {
                                            UtilView.showToast(mActivity, "No Sales Quotation Available");
                                        }

                                    } catch (Exception e) {
                                        UtilView.showLogCat(TAG, "TaskCompleteListener Exception " + e.toString());
                                    }

                                }
                            } else {
                                progressBar.setVisibility(View.GONE);
                                UtilView.showErrorDialog(getResources().getString(R.string.error_null), mActivity);
                            }
                        }
                    }, false);
                    getDataTask.execute(url, "");

                }
                if (!isInternetPresent) {
                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                }
            } else {
                UtilView.getUrl(mActivity);
            }
        } else {
            UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
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

                    url = serverUrl + "/retail//"+ Constant.FUNTION_SALESINVOICECANCELORDER+"?formNo="+formno;
                    GetDataTask getDataTask = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                        @Override
                        public void onTaskComplete(String result) {
                            if (pDialog != null)
                                pDialog.dismiss();

                            if (result.toString() != null) {
                                Gson gson = new Gson();
                                try {

                                    Duplicate_SalesOrder data = gson.fromJson(result.toString(), Duplicate_SalesOrder.class);
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
                                            getSalesOrderListitem("");

                                        }
                                        else {
                                            if (sDialog != null) {
                                                sDialog.setTitleText(mActivity.getString(R.string.delfail))
                                                            /*.setContentText("Category "+category.getItemCategoryName())*/
                                                        .setConfirmText(mActivity.getString(R.string.bntok))
                                                        .setConfirmClickListener(null)
                                                        .changeAlertType(SweetAlertDialog.ERROR_TYPE);

                                            }
                                            getSalesOrderListitem("");
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

    private void getSalesOrderListitem(String search) {
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
                if (progressBar != null)
                    progressBar.setVisibility(View.VISIBLE);

                String url ="";
                if (search.equals("")) {
                    url = serverUrl + "/retail//" + Constant.FUNTION_GETSALESORDERDETAIL+"/?itemSearchText=&locationId=";

                }

                if (!search.equals("")) {
                    url = serverUrl + "/retail//" + Constant.FUNTION_GETSALESORDERDETAIL+"/?itemSearchText="+ search.replace(" ", "%20")+"&locationId=";

                }

                GetDataTask getOrderList = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        if (progressBar != null)
                            progressBar.setVisibility(View.GONE);
                        if (result != null) {
                            Gson gson = new Gson();
                            listData = new ArrayList<Duplicate_SalesOrder>();
                            try {
                                JSONArray jsonArray = new JSONArray(result.toString());
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonInovice = jsonArray.getJSONObject(i);
                                    Duplicate_SalesOrder inovice = gson.fromJson(jsonInovice.toString(), Duplicate_SalesOrder.class);
                                    listData.add(inovice);
                                }
                                if (listData != null && listData.size() > 0) {

                                    llListview.setVisibility(View.VISIBLE);
                                    adapter = new SalesDuplicateSalesOrder_Adapter(mActivity, listData);
                                    listviewInvoices.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                } else {
                                    llListview.setVisibility(View.GONE);

                                    adapter = new SalesDuplicateSalesOrder_Adapter(mActivity, listData);
                                    listviewInvoices.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                    UtilView.showSuccessDialog("No Sales Order Available", mActivity);
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
            if (saveSalesOrderResponse != null) {
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
                    if (saveSalesOrderResponse != null) {
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
        pdfFile = PdfUtils.createFile(saveSalesOrderResponse.getSoNo() + ".pdf", Constant.DIRECTORY_DUPLICATESALES, Constant.DIRECTORY_SAVESALESORDER);
        if (pdfFile != null) {
            InputStream logoInputStream = PdfUtils.getLogoInputStream(mActivity, progressBar);
            if (logoInputStream != null) {
                try {
                    PosSalesSaveOrderPdf invoicePdf = new PosA4SalesSaveOrderPdfImpl();
                    invoicePdf.generateSalesSaveOrder(mActivity, saveSalesOrderResponse, new FileOutputStream(pdfFile), logoInputStream);
                    UtilView.hideProgessBar(mActivity, progressBar);
                    openPdfInvoice(saveSalesOrderResponse.getSoNo() + ".pdf", Constant.DIRECTORY_DUPLICATESALES, Constant.DIRECTORY_SAVESALESORDER);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    PosSalesSaveOrderPdf invoicePdf = new PosA4SalesSaveOrderPdfImpl();
                    invoicePdf.generateSalesSaveOrder(mActivity, saveSalesOrderResponse, new FileOutputStream(pdfFile), null);
                    UtilView.hideProgessBar(mActivity, progressBar);
                    openPdfInvoice(saveSalesOrderResponse.getSoNo() + ".pdf", Constant.DIRECTORY_DUPLICATESALES, Constant.DIRECTORY_SAVESALESORDER);
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
            Intent intent = new Intent(this, NavigationDrawer_Activity.class);
            intent.putExtra(Constant.NAVIGATION_GROUPHEADER, Constant.NAVIGATION_GROUP_RETAIL);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.toString() != null && !s.toString().equals("")) {
            adapter.getFilter().filter(s.toString());
        } else {
            adapter = new SalesDuplicateSalesOrder_Adapter(mActivity, listData);
            listviewInvoices.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (editable.toString().equals("")) {
            UtilView.hideSoftKeyboard(mActivity, edSearch);
            salesOrderSearch = "";
            getSalesOrderListitem(salesOrderSearch);
        }
    }

    @OnClick(R.id.imgviewSearch)
    public void onClick() {
        salesOrderSearch = edSearch.getText().toString().trim();
        getSalesOrderListitem(salesOrderSearch);
    }
}
