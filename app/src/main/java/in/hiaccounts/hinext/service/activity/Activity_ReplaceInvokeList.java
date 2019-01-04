package in.hiaccounts.hinext.service.activity;

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
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import in.hiaccounts.R;
import in.hiaccounts.hinext.service.adapter.Service_InvokeList_Adapter;
import in.hiaccounts.hinext.service.model.SelectedItemsList;
import in.hiaccounts.hinext.service.model.service_invoice.ServiceSaveData;
import in.hiaccounts.hinext.service.model.service_invoke.InvokeList_Data;
import in.hiaccounts.hinext.service.model.service_pos.ServicePosCartItem;
import in.hiaccounts.hinext.service.model.service_pos.ServicePosCreator;
import in.hiaccounts.hinext.service.model.service_pos.ServicePosHelper;
import in.hiaccounts.pdfgenerator.PdfUtils;
import in.hiaccounts.pdfgenerator.service.serviceinvoice.PosA4ServiceInvociePdfImpl;
import in.hiaccounts.pdfgenerator.service.serviceinvoice.PosServiceInvoicePdf;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

import static in.hiaccounts.R.string.response_error;

public class Activity_ReplaceInvokeList extends AppCompatActivity implements TextWatcher {
    private static String TAG = Activity_ReplaceInvokeList.class.getSimpleName();

    private ServiceSaveData invoiceData = null;
    private int currentAPIVersion = Build.VERSION.SDK_INT;
    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 123;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edSearch)
    EditText edSearch;
    @BindView(R.id.llSearch)
    LinearLayout llSearch;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.tvFormNo)
    TextView tvFormNo;
    @BindView(R.id.tvCustomerName)
    TextView tvCustomerName;
    @BindView(R.id.tvAction)
    TextView tvAction;
    @BindView(R.id.lvInvokeList)
    ListView lvInvokeList;
    @BindView(R.id.llListview)
    LinearLayout llListview;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;


    private Activity mActivity;
    private Boolean isInternetPresent = false;
    private ServiceHandler serviceHandler;
    private String serverUrl, invoiceSearch = "", formno = "";
    private ServicePosCreator servicePosCreator;
    private InvokeList_Data serviceInvoiceData;
    private List<InvokeList_Data> invoiceDataList;
    private Service_InvokeList_Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replace_invoke_list);
        ButterKnife.bind(this);
        initComponents();
    }

    private void initComponents() {
        ButterKnife.bind(this);
        mActivity = this;
        edSearch.addTextChangedListener(this);
        serverUrl = UtilView.getUrl(mActivity);
        serviceHandler = new ServiceHandler(mActivity);
        servicePosCreator = ServicePosHelper.getPosCreator();
        toolbar.setTitle(getResources().getString(R.string.menu_invokereplacelist));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getReplaceInvokeListitem("");

        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    invoiceSearch = edSearch.getText().toString().trim();
                    getReplaceInvokeListitem(invoiceSearch);
                }
                return handled;
            }
        });

        lvInvokeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                serviceInvoiceData = invoiceDataList.get(position);

                id = serviceInvoiceData.getId();
                formno = serviceInvoiceData.getSrNo();
                if (view.getId() == R.id.imgviewPrint) {
                    isInternetPresent = serviceHandler.isConnectingToInternet();
                    if (serverUrl != null) {
                        if (isInternetPresent) {
                            UtilView.showProgessBar(mActivity, progressBar);
                            String url = serverUrl + "/service//" + Constant.FUNTION_GETSERVICEDELIVERYPRINTLIST + "?id=" + id;
                            GetDataTask getInvoicetask = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                                @Override
                                public void onTaskComplete(String result) {
                                    Gson gson = new Gson();
                                    try {
                                        invoiceData = gson.fromJson(result.toString(), ServiceSaveData.class);
                                        if (invoiceData != null) {
                                            if (currentAPIVersion >= Build.VERSION_CODES.M) {
                                                checkPermission();
                                            } else {
                                                createPdf();
                                            }
                                            //UtilView.showToast(mActivity, "Recieve Payment successfully created.");
                                        } else {
                                            UtilView.showToast(mActivity, "Please try again");
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
                    initEditDialog(id);
                }
            }
        });
    }

    private void initEditDialog(long id) {
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
                if (progressBar != null)
                    progressBar.setVisibility(View.VISIBLE);
                String url = serverUrl + "/service//" + Constant.FUNTION_GETSERVICEDELIVERYPRINTLIST + "?id=" + id;
                GetDataTask getOrderDetail = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        if (result != null) {
                            if (progressBar != null)
                                progressBar.setVisibility(View.GONE);
                            try {
                                servicePosCreator.clear();
                                Gson gson = new Gson();
                                ServiceSaveData orderData = gson.fromJson(result.toString(), ServiceSaveData.class);
                                if (orderData!=null){

                                    if (orderData.getSelectedItemsList()!=null && orderData.getSelectedItemsList().size()>0){
                                        for (int i=0;i<orderData.getSelectedItemsList().size();i++){
                                            SelectedItemsList item = orderData.getSelectedItemsList().get(i);
                                            SelectedItemsList invokeItems = new SelectedItemsList();
                                            invokeItems.setItemName(item.getItemName());
                                            invokeItems.setItemId(item.getItemId());
                                            invokeItems.setQty(item.getQty());
                                            double d = item.getQty();
                                            invokeItems.setItemQuantity((new Double(d)).longValue());
                                            invokeItems.setReturnQty(item.getReturnQty());
                                            invokeItems.setRemainingQty(item.getRemainingQty());
                                            invokeItems.setAmtexclusivetax(item.getAmtexclusivetax());
                                            invokeItems.setTaxid(item.getTaxid());
                                            invokeItems.setTaxpercent(Double.valueOf(String.valueOf(item.getTaxpercent())));
                                            invokeItems.setTaxamt(item.getTaxamt());
                                            invokeItems.setAmtinclusivetax(item.getAmtinclusivetax());
                                            invokeItems.setDiscountAmt(item.getDiscountAmt());
                                            invokeItems.setItemDescription(item.getItemDescription());
                                            invokeItems.setTaxName(item.getTaxName());
                                            invokeItems.setItemCode(item.getItemCode());
                                            invokeItems.setCess(item.getCess());

                                            invokeItems.setCessTaxAmt(item.getCessTaxAmt());
                                            invokeItems.setHsnCode(item.getHsnCode());
                                            invokeItems.setUomName(item.getUomName());
                                            invokeItems.setItemTotalAmount(item.getAmtinclusivetax());
                                            invokeItems.setUnitPrice(item.getUnitPrice());

                                            invokeItems.setUom(item.getUom());
                                            invokeItems.setUomConvertorList(item.getUomConvertorList());
                                            invokeItems.setPrice(BigDecimal.valueOf(item.getUnitPrice()));
                                            invokeItems.setInclusiveJSON(item.getInclusiveJSON());
                                            invokeItems.setSerializableStatus(item.getSerializableStatus());
                                            invokeItems.setSalesQuotationId(item.getSalesQuotationId());
                                            invokeItems.setSalesQuotationDetailsId(item.getSalesQuotationDetailsId());
                                            invokeItems.setSalesOrderDetailsId(item.getSalesOrderDetailsId());
                                            invokeItems.setSalesOrderId(item.getSalesOrderId());
                                            servicePosCreator.addItem(new ServicePosCartItem(invokeItems, 1));
                                        }
                                    }
                                    Intent returnIntent = new Intent();
                                    returnIntent.putExtra("ReplaceItem","Replace Item");
                                    mActivity.setResult(Activity.RESULT_OK, returnIntent);
                                    mActivity.finish();



                                } else {
                                    UtilView.showToast(mActivity, "No Service Invoice Available");
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

    public void initCancelDialog(final String formno) {
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

                    url = serverUrl + "/service//"+ Constant.FUNTION_SERVICECANCELREPAIR+"?formNo="+formno;
                    GetDataTask getDataTask = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                        @Override
                        public void onTaskComplete(String result) {
                            if (pDialog != null)
                                pDialog.dismiss();

                            if (result.toString() != null) {

                                if (sDialog != null) {
                                    sDialog.setTitleText(mActivity.getString(R.string.delconfirm))
                                                            /*.setContentText("Category "+category.getItemCategoryName())*/
                                            .setConfirmText(mActivity.getString(R.string.bntok))
                                            .setConfirmClickListener(null)
                                            .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);

                                    adapter.notifyDataSetChanged();



                                }
                                getReplaceInvokeListitem("");

                            } else {

                                UtilView.showToast(mActivity, mActivity.getResources().getString(R.string.response_error));
                            }
                        }
                    }, false);
                    getDataTask.execute(url, Constant.FUNTION_ADVANCEPARTIALPAYMENT);

                }
            });
            if (pDialog != null)
                pDialog.show();
        } else {
            UtilView.showErrorDialog(mActivity.getResources().getString(R.string.intertnet_status), mActivity);
        }
    }

    private void getReplaceInvokeListitem(String search) {
        String url = "";
        if (search.equals("")) {
            url = serverUrl + "/service//" + Constant.FUNTION_GETSERVICEINVOKELIST ;
        }
        if (!search.equals("")) {
            url = serverUrl + "/service//" + Constant.FUNTION_GETSERVICEINVOKELIST  + "?itemSearchText=" + search.replace(" ", "%20");
        }

        if (serverUrl!=null) {
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {

                UtilView.showProgessBar(mActivity, progressBar);
                GetDataTask task = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(mActivity, progressBar);
                        if (result != null) {
                            try {
                                invoiceDataList = new ArrayList<InvokeList_Data>();
                                JSONArray jsonArray = new JSONArray(result.toString());
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject json = jsonArray.getJSONObject(i);
                                    Gson gson = new Gson();
                                    InvokeList_Data paymentInvoice = gson.fromJson(json.toString(), InvokeList_Data.class);
                                    invoiceDataList.add(paymentInvoice);
                                }
                                if (invoiceDataList.size() > 0) {
                                    llListview.setVisibility(View.VISIBLE);
                                    adapter = new Service_InvokeList_Adapter(mActivity, invoiceDataList);
                                    lvInvokeList.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                } else {
                                    invoiceDataList.clear();
                                    llListview.setVisibility(View.GONE);
                                    adapter = new Service_InvokeList_Adapter(mActivity, invoiceDataList);
                                    lvInvokeList.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                    UtilView.showErrorDialog("No Invoices Available", mActivity);

                                }
                            }catch (Exception e){
                                UtilView.showErrorDialog(getResources().getString(R.string.error_null), mActivity);
                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.error_null), mActivity);
                        }
                    }
                }, false);
                task.execute(url, "");
            }
            if (!isInternetPresent) {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
            }
        }else {
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

        pdfFile = PdfUtils.createFile(invoiceData.getSrlnNo() + ".pdf", Constant.DIRECTORY_SALES, Constant.DIRECTORY_SALESPOS);
        if (pdfFile != null) {

            progressBar.setVisibility(View.VISIBLE);


            InputStream logoInputStream = PdfUtils.getLogoInputStream(mActivity, progressBar);
            if (logoInputStream != null) {
                progressBar.setVisibility(View.GONE);
                try {
                    PosServiceInvoicePdf invoicePdf = new PosA4ServiceInvociePdfImpl();
                    invoicePdf.generateTaxInvoice(mActivity, invoiceData, new FileOutputStream(pdfFile), logoInputStream);
                    UtilView.hideProgessBar(mActivity, progressBar);
                    openPdfInvoice(invoiceData.getSrlnNo() + ".pdf", Constant.DIRECTORY_SALES, Constant.DIRECTORY_SALESPOS);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    PosServiceInvoicePdf invoicePdf = new PosA4ServiceInvociePdfImpl();
                    invoicePdf.generateTaxInvoice(this, invoiceData, new FileOutputStream(pdfFile), null);
                    UtilView.hideProgessBar(mActivity, progressBar);
                    openPdfInvoice(invoiceData.getSrlnNo() + ".pdf", Constant.DIRECTORY_SALES, Constant.DIRECTORY_SALESPOS);
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
