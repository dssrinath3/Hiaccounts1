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
import android.widget.RelativeLayout;
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
import cn.pedant.SweetAlert.SweetAlertDialog;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.checkout.model.sales.Checkout_Sales_ResponseData;
import in.hiaccounts.hinext.purchase.model.purchase_saveresponses.Checkout_ResponseData;
import in.hiaccounts.hinext.sales.adapter.Sales_ProFormaListAdapter;
import in.hiaccounts.hinext.sales.model.checkout.SalesSaveData;
import in.hiaccounts.hinext.sales.model.checkout.SelectedItemsList;
import in.hiaccounts.hinext.sales.model.printlist.proforma.PrintList_ProFormaData;
import in.hiaccounts.hinext.sales.model.sales_pos.SalesPosCartItem;
import in.hiaccounts.hinext.sales.model.sales_pos.SalesPosCreator;
import in.hiaccounts.hinext.sales.model.sales_pos.SalesPosHelper;
import in.hiaccounts.pdfgenerator.PdfUtils;
import in.hiaccounts.pdfgenerator.sales.salesinvoice.PosA4SalesInvociePdfImpl;
import in.hiaccounts.pdfgenerator.sales.salesinvoice.PosSalesInvoicePdf;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

import static in.hiaccounts.R.string.response_error;

public class Activity_Printlist_SalesProForma extends AppCompatActivity implements TextWatcher {
    private static String TAG = Activity_Printlist_SalesProForma.class.getSimpleName();

    private Checkout_Sales_ResponseData saleInoviceData = null;
    private int currentAPIVersion = Build.VERSION.SDK_INT;
    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 123;


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edSearch)
    EditText edSearch;
    @BindView(R.id.imgviewSearch)
    ImageView imgviewSearch;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.tv_formno)
    TextView tvFormno;
    @BindView(R.id.tv_customername)
    TextView tvCustomername;
    @BindView(R.id.tv_totalamount)
    TextView tvTotalamount;
    @BindView(R.id.tv_itemtotalamount)
    TextView tvItemtotalamount;
    @BindView(R.id.ll_listtitle)
    LinearLayout llListtitle;
    @BindView(R.id.listviewProforma)
    ListView listviewProforma;
    @BindView(R.id.llListView)
    LinearLayout llListView;
    @BindView(R.id.rlContent)
    RelativeLayout rlContent;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;

    private Activity mActivity;
    private Boolean isInternetPresent = false;
    private ServiceHandler serviceHandler;
    private String serverUrl,proformaSearch="",formno="";
    private SalesPosCreator salesPosCreator;

    
    private Sales_ProFormaListAdapter adapter;
    private ArrayList<PrintList_ProFormaData> invoiceList;
    private PrintList_ProFormaData invData = new PrintList_ProFormaData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_printlist_sales_proforma);
        ButterKnife.bind(this);
        initComponents();
    }

    private void initComponents() {
        ButterKnife.bind(this);
        mActivity = this;
        edSearch.addTextChangedListener(this);
        serverUrl = UtilView.getUrl(mActivity);
        serviceHandler = new ServiceHandler(mActivity);
        salesPosCreator = SalesPosHelper.getPosCreator();
        toolbar.setTitle(getResources().getString(R.string.menu_duplicatesalesproforma));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getProFormaListitem("");

        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    proformaSearch = edSearch.getText().toString().trim();
                    getProFormaListitem(proformaSearch);
                }
                return handled;
            }
        });

        listviewProforma.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                invData = invoiceList.get(position);
                formno = invData.getFormNo();

                if (view.getId() == R.id.imgview_duplicateprint) {
                    isInternetPresent = serviceHandler.isConnectingToInternet();
                    if (serverUrl != null) {
                        if (isInternetPresent) {
                            UtilView.showProgessBar(mActivity, progressBar);
                            String url = serverUrl + "/retail//" + Constant.FUNTION_GETDUPLICATESALEPROFORMA + "?invoiceNo=" + formno;
                            GetDataTask getInvoicetask = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                                @Override
                                public void onTaskComplete(String result) {
                                    UtilView.hideProgessBar(mActivity, progressBar);
                                    Gson gson = new Gson();
                                    try {

                                        saleInoviceData = gson.fromJson(result.toString(), Checkout_Sales_ResponseData.class);
                                        if (saleInoviceData != null) {
                                            if (saleInoviceData.getSiData() != null) {
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
                    initEditDialog(formno);
                }
            }
        });
    }

    private void initEditDialog(String formno) {
        isInternetPresent = serviceHandler.isConnectingToInternet();

        if (isInternetPresent) {
            UtilView.showProgessBar(mActivity, progressBar);

            String url = serverUrl + "/retail//" + Constant.FUNTION_GETSALESINVOKEPROFORMA+"?quotationNo="+formno;

            if (serverUrl != null) {
                isInternetPresent = serviceHandler.isConnectingToInternet();
                if (isInternetPresent) {
                    UtilView.showProgessBar(mActivity, progressBar);
                    PostDataTask postDataTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
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
                                                    invokeItems.setInclusiveJSON(item.getInclusiveJSON());
                                                    invokeItems.setSerializableStatus(item.getSerializableStatus());
                                                    invokeItems.setProFormaSalesInvoiceId(item.getProFormaSalesInvoiceId());
                                                    invokeItems.setProFormaSalesInvoiceDetailsId(item.getProFormaSalesInvoiceDetailsId());
                                                    salesPosCreator.addItem(new SalesPosCartItem(invokeItems, 1));
                                                }
                                            }
                                            Intent returnIntent = new Intent();
                                            returnIntent.putExtra("proformaId",orderData.getProFormaId());
                                            mActivity.setResult(Activity.RESULT_OK, returnIntent);
                                            mActivity.finish();

                                        } else {
                                            UtilView.showToast(mActivity, "No Pro Forma Available");
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
                    postDataTask.execute(new Gson().toJson(invData), url, "");

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

                    url = serverUrl + "/retail//"+ Constant.FUNTION_SALESINVOICECANCELPROFORMA+"?formNo="+formno;
                    GetDataTask getDataTask = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                        @Override
                        public void onTaskComplete(String result) {
                            if (pDialog != null)
                                pDialog.dismiss();

                            if (result.toString() != null) {
                                Gson gson = new Gson();
                                try {

                                    PrintList_ProFormaData data = gson.fromJson(result.toString(), PrintList_ProFormaData.class);
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
                                            getProFormaListitem("");

                                        }
                                        else {
                                            if (sDialog != null) {
                                                sDialog.setTitleText(mActivity.getString(R.string.delfail))
                                                            /*.setContentText("Category "+category.getItemCategoryName())*/
                                                        .setConfirmText(mActivity.getString(R.string.bntok))
                                                        .setConfirmClickListener(null)
                                                        .changeAlertType(SweetAlertDialog.ERROR_TYPE);

                                            }
                                            getProFormaListitem("");
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

    private void getProFormaListitem(String search) {
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
                if (progressBar != null)
                    progressBar.setVisibility(View.VISIBLE);

                String url ="";
                if (search.equals("")) {
                    url = serverUrl + "/retail//" + Constant.FUNTION_GETSALESSELECTINVOKEPROFORMA+"/?itemSearchText=&locationId=";

                }

                if (!search.equals("")) {
                    url = serverUrl + "/retail//" + Constant.FUNTION_GETSALESSELECTINVOKEPROFORMA+"/?itemSearchText="+ search.replace(" ", "%20");

                }

                GetDataTask getOrderList = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        if (progressBar != null)
                            progressBar.setVisibility(View.GONE);
                        if (result != null) {
                            Gson gson = new Gson();
                            invoiceList = new ArrayList<PrintList_ProFormaData>();
                            try {
                                JSONArray jsonArray = new JSONArray(result.toString());
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonInovice = jsonArray.getJSONObject(i);
                                    PrintList_ProFormaData inovice = gson.fromJson(jsonInovice.toString(), PrintList_ProFormaData.class);
                                    invoiceList.add(inovice);
                                }
                                if (invoiceList != null && invoiceList.size() > 0) {

                                    llListView.setVisibility(View.VISIBLE);
                                    adapter = new Sales_ProFormaListAdapter(mActivity, invoiceList);
                                    listviewProforma.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                } else {
                                    llListView.setVisibility(View.GONE);

                                    adapter = new Sales_ProFormaListAdapter(mActivity, invoiceList);
                                    listviewProforma.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                    UtilView.showSuccessDialog("No Pro Forma Available", mActivity);
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

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (editable.toString().equals("")) {
            UtilView.hideSoftKeyboard(mActivity, edSearch);
            proformaSearch = "";
            getProFormaListitem(proformaSearch);
        }
    }

    @OnClick(R.id.imgviewSearch)
    public void onClick() {
        proformaSearch = edSearch.getText().toString().trim();
        getProFormaListitem(proformaSearch);
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
            if (saleInoviceData != null) {
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
                    if (saleInoviceData != null) {
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
        pdfFile = PdfUtils.createFile(saleInoviceData.getSiData().getSrlnNo() + ".pdf", Constant.DIRECTORY_DUPLICATESALES, Constant.DIRECTORY_SALESPOS);

        if (pdfFile != null) {

            InputStream logoInputStream = PdfUtils.getLogoInputStream(this, progressBar);

            if (logoInputStream != null) {
                try {
                    PosSalesInvoicePdf invoicePdf = new PosA4SalesInvociePdfImpl();
                    invoicePdf.generateTaxInvoice(mActivity, saleInoviceData, new FileOutputStream(pdfFile), logoInputStream);
                    UtilView.hideProgessBar(mActivity, progressBar);
                    openPdfInvoice(saleInoviceData.getSiData().getSrlnNo() + ".pdf", Constant.DIRECTORY_DUPLICATESALES, Constant.DIRECTORY_SALESPOS);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    PosSalesInvoicePdf invoicePdf = new PosA4SalesInvociePdfImpl();
                    invoicePdf.generateTaxInvoice(this, saleInoviceData, new FileOutputStream(pdfFile), null);
                    UtilView.hideProgessBar(mActivity, progressBar);
                    openPdfInvoice(saleInoviceData.getSiData().getSrlnNo() + ".pdf", Constant.DIRECTORY_DUPLICATESALES, Constant.DIRECTORY_SALESPOS);

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
