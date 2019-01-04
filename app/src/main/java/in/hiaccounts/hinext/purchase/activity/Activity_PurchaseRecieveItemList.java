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
import java.math.BigDecimal;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hiaccounts.R;
import in.hiaccounts.hinext.purchase.adapter.PurchaseReceiveItemList_Adapter;
import in.hiaccounts.hinext.purchase.model.purchase_pos.PurchasePosCartItem;
import in.hiaccounts.hinext.purchase.model.purchase_pos.PurchasePosCreator;
import in.hiaccounts.hinext.purchase.model.purchase_pos.PurchasePosHelper;
import in.hiaccounts.hinext.purchase.model.purchase_pos.SelectedItemsList;
import in.hiaccounts.hinext.purchase.model.purchase_purchaseorder.PurchaseInvokeOrderData;
import in.hiaccounts.hinext.purchase.model.purchase_purchaseorder.PurchaseOrderData;
import in.hiaccounts.hinext.purchase.model.purchase_receiveitem.PurchaseRecieveItemListData;
import in.hiaccounts.hinext.purchase.model.purchase_saveresponses.Checkout_ResponseData;
import in.hiaccounts.pdfgenerator.PdfUtils;
import in.hiaccounts.pdfgenerator.purchase.purchase_inovices.PosA4PurchaseInvociePdfImpl;
import in.hiaccounts.pdfgenerator.purchase.purchase_inovices.PosPurchaseInvoicePdf;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

import static in.hiaccounts.R.string.response_error;

public class Activity_PurchaseRecieveItemList extends AppCompatActivity implements TextWatcher {

    private Checkout_ResponseData invoiceData = null;
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
    @BindView(R.id.listviewRecieveItem)
    ListView listviewRecieveItem;
    @BindView(R.id.llListView)
    LinearLayout llListView;
    @BindView(R.id.rlContent)
    RelativeLayout rlContent;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;

    PurchaseReceiveItemList_Adapter adapter;
    private ArrayList<PurchaseRecieveItemListData> receiveItemlist = new ArrayList<PurchaseRecieveItemListData>();
    private static String TAG = Activity_PurchaseRecieveItemList.class.getSimpleName();
    private Activity mActivity;
    private Boolean isInternetPresent = false;
    private ServiceHandler serviceHandler;
    private String serverUrl;
    String recieveItemsearch="",formno;
    PurchaseRecieveItemListData listData = new PurchaseRecieveItemListData();
    private PurchasePosCreator purchasePosCreator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_recieve_item_list);
        ButterKnife.bind(this);
        initComponents();
    }

    private void initComponents() {
        ButterKnife.bind(this);
        mActivity = this;
        edSearch.addTextChangedListener(this);
        serverUrl = UtilView.getUrl(mActivity);
        serviceHandler = new ServiceHandler(mActivity);
        purchasePosCreator = PurchasePosHelper.getPosCreator();
        toolbar.setTitle("Receive Item List");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getPurchaserReceiveItemListitem("");

        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    recieveItemsearch = edSearch.getText().toString().trim();
                    getPurchaserReceiveItemListitem(recieveItemsearch);
                }
                return handled;
            }
        });


        listviewRecieveItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                listData = receiveItemlist.get(position);
                formno = listData.getFormNo();


                if (view.getId() == R.id.imgview_duplicateprint) {
                    isInternetPresent = serviceHandler.isConnectingToInternet();
                    if (serverUrl != null) {
                        if (isInternetPresent) {
                            UtilView.showProgessBar(mActivity, progressBar);
                            String url = serverUrl + "/purchase//0/" + Constant.FUNTION_GETPURCHASERECIEVEITEMPRINT + "/" + formno;
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

                if (view.getId() == R.id.imgviewEdit) {

                    initEditDialog(position);
                }
            }
        });
    }

    private void initEditDialog(int position) {
        listData = receiveItemlist.get(position);
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
                if (progressBar != null)
                    progressBar.setVisibility(View.VISIBLE);


                String url = serverUrl + "/purchase//0/" + Constant.FUNTION_GETRECIEVEITEMLISTEDIT + "/" + listData.getFormNo()+"/Edit";
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
                                    returnIntent.putExtra("recieveitemId",orderData.getReceiveItemId());
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

    private void getPurchaserReceiveItemListitem(String search) {
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
                if (progressBar != null)
                    progressBar.setVisibility(View.VISIBLE);

                String url ="";
                if (search.equals("")) {
                    url = serverUrl + "/purchase//0/" + Constant.FUNTION_GETRECIEVEITEMLIST+"/?itemSearchText=&type=Print&locationId=";
                }

                if (!search.equals("")) {
                    url = serverUrl + "/purchase//0/" + Constant.FUNTION_GETRECIEVEITEMLIST+"/?itemSearchText="+ search.replace(" ", "%20")+"&type=Print&locationId=";

                }

                GetDataTask getOrderList = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        if (progressBar != null)
                            progressBar.setVisibility(View.GONE);
                        if (result != null) {
                            Gson gson = new Gson();
                            receiveItemlist = new ArrayList<PurchaseRecieveItemListData>();
                            try {
                                JSONArray jsonArray = new JSONArray(result.toString());
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonInovice = jsonArray.getJSONObject(i);
                                    PurchaseRecieveItemListData inovice = gson.fromJson(jsonInovice.toString(), PurchaseRecieveItemListData.class);
                                    receiveItemlist.add(inovice);
                                }
                                if (receiveItemlist != null && receiveItemlist.size() > 0) {

                                    llListView.setVisibility(View.VISIBLE);

                                    adapter = new PurchaseReceiveItemList_Adapter(mActivity, receiveItemlist);
                                    listviewRecieveItem.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                } else {
                                    llListView.setVisibility(View.GONE);

                                    adapter = new PurchaseReceiveItemList_Adapter(mActivity, receiveItemlist);
                                    listviewRecieveItem.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                    UtilView.showSuccessDialog("No Receive items Available", mActivity);
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
    @OnClick(R.id.imgviewSearch)
    public void onClick(View view) {
        recieveItemsearch = edSearch.getText().toString().trim();
        getPurchaserReceiveItemListitem(recieveItemsearch);
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (editable.toString().equals("")) {
            UtilView.hideSoftKeyboard(mActivity, edSearch);
            recieveItemsearch = "";
            getPurchaserReceiveItemListitem(recieveItemsearch);
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

        }
    }
}
