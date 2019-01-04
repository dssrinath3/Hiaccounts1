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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lowagie.text.DocumentException;
import com.rey.material.app.Dialog;
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
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.application.activity.NavigationDrawer_Activity;
import in.hiaccounts.hinext.checkout.model.sales.Checkout_Sales_ResponseData;
import in.hiaccounts.hinext.purchase.model.purchase_saveresponses.Checkout_ResponseData;
import in.hiaccounts.hinext.sales.adapter.SalesDuplicateInvoice_Adapter;
import in.hiaccounts.hinext.sales.model.checkout.SalesSaveData;
import in.hiaccounts.hinext.sales.model.checkout.SelectedItemsList;
import in.hiaccounts.hinext.sales.model.printlist.duplicate_invoice.PrintList_PosInvoices;
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
import in.hiaccounts.utility.Validation;

import static in.hiaccounts.R.string.response_error;

/**
 * Created by Prateek on 7/26/2017.
 */

public class Activity_SalesDuplicateInvoices extends AppCompatActivity implements TextWatcher {
    private static String TAG = Activity_SalesDuplicateInvoices.class.getSimpleName();
    //private SavePrintResponse saleInoviceData = null;
    private Checkout_Sales_ResponseData saleInoviceData = null;
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

    private SalesDuplicateInvoice_Adapter adapter;
    private ArrayList<PrintList_PosInvoices> invoiceList = new ArrayList<>();
    private PrintList_PosInvoices invData = new PrintList_PosInvoices();

    private Activity mActivity;
    private Boolean isInternetPresent = false;
    private ServiceHandler serviceHandler;
    private String serverUrl,invoiceSearch="",formno="";
    private SalesPosCreator salesPosCreator;
    private Long id;

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
        salesPosCreator = SalesPosHelper.getPosCreator();
        toolbar.setTitle(getResources().getString(R.string.menu_duplicatesalesinvoice));
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
                formno = invData.getFormNo();
                id = invData.getId();
                if (view.getId() == R.id.imgview_duplicateprint) {
                    isInternetPresent = serviceHandler.isConnectingToInternet();
                    if (serverUrl != null) {
                        if (isInternetPresent) {
                            UtilView.showProgessBar(mActivity, progressBar);
                            String url = serverUrl + "/retail//" + Constant.FUNTION_GETDUPLICATEINVOICE + "?invoiceNo=" + formno;
                            GetDataTask getInvoicetask = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                                @Override
                                public void onTaskComplete(String result) {
                                    UtilView.hideProgessBar(mActivity, progressBar);
                                    Gson gson = new Gson();
                                    try {

                                        saleInoviceData = gson.fromJson(result.toString(), Checkout_Sales_ResponseData.class);
                                        if (saleInoviceData != null) {
                                            if (saleInoviceData.getSiData() != null) {
                                                if (saleInoviceData.getSiData().getPrintType() != null) {
                                                    if (currentAPIVersion >= Build.VERSION_CODES.M) {
                                                        checkPermission();
                                                    } else {
                                                        createPdf();
                                                    }
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
                    initCancelDialog(id);
                }
                if (view.getId() == R.id.imgviewCancelDraft) {
                    initCancelDraftDialog(id);
                }

                if (view.getId() == R.id.imgviewEdit) {
                    initEditDialog(position);
                }
            }
        });
    }

    private void initEditDialog(int position) {
        isInternetPresent = serviceHandler.isConnectingToInternet();

        invData = invoiceList.get(position);
        if (isInternetPresent) {
            UtilView.showProgessBar(mActivity, progressBar);
            String url = serverUrl + "/retail//" + Constant.FUNTION_GETSALESINVOICE+"?invoiceNo="+invData.getFormNo();

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
                                                    salesPosCreator.addItem(new SalesPosCartItem(invokeItems, 1));
                                                }
                                            }
                                            Intent returnIntent = new Intent();
                                            returnIntent.putExtra("salesInvoiceNo",orderData.getSiNo());
                                            returnIntent.putExtra("salesInvoiceSiid",orderData.getSiid());
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

    private void initCancelDraftDialog(final long id) {
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

                    url = serverUrl + "/retail//"+ Constant.FUNTION_SALESCANCELDRAFTINVOICE;
                    PostDataTask postDataTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                        @Override
                        public void onTaskComplete(String result) {
                            if (pDialog != null)
                                pDialog.dismiss();

                            if (result!= null) {
                                if (result.equals("")) {
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
                                        sDialog.setTitleText(mActivity.getString(R.string.delconfirmDraft))
                                                            /*.setContentText("Category "+category.getItemCategoryName())*/
                                                .setConfirmText(mActivity.getString(R.string.bntok))
                                                .setConfirmClickListener(null)
                                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);

                                    }
                                    getInvoiceListitem("");
                                }
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

                    url = serverUrl + "/retail//"+ Constant.FUNTION_SALESINVOICECANCEL;
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

    private void getInvoiceListitem(String search) {
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
                if (progressBar != null)
                    progressBar.setVisibility(View.VISIBLE);

                String url ="";
                if (search.equals("")) {
                    url = serverUrl + "/retail//" + Constant.FUNTION_GETINVOICES+"?itemSearchText=&type=Print&locationId=";

                }

                if (!search.equals("")) {
                    url = serverUrl + "/retail//" + Constant.FUNTION_GETINVOICES+"?itemSearchText="+ search.replace(" ", "%20");

                }

                GetDataTask getOrderList = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        if (progressBar != null)
                            progressBar.setVisibility(View.GONE);
                        if (result != null) {
                            Gson gson = new Gson();
                            invoiceList = new ArrayList<PrintList_PosInvoices>();
                            try {
                                JSONArray jsonArray = new JSONArray(result.toString());
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonInovice = jsonArray.getJSONObject(i);
                                    PrintList_PosInvoices inovice = gson.fromJson(jsonInovice.toString(), PrintList_PosInvoices.class);
                                    invoiceList.add(inovice);
                                }
                                if (invoiceList != null && invoiceList.size() > 0) {

                                    llListView.setVisibility(View.VISIBLE);
                                    adapter = new SalesDuplicateInvoice_Adapter(mActivity, invoiceList);
                                    listviewInvoices.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                } else {
                                    llListView.setVisibility(View.GONE);

                                    adapter = new SalesDuplicateInvoice_Adapter(mActivity, invoiceList);
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


    @OnClick(R.id.imgviewSearch)
    public void onClick() {
        invoiceSearch = edSearch.getText().toString().trim();
        getInvoiceListitem(invoiceSearch);
    }

    private void initEmaiDialog(final int position) {
        // custom dialog
        final Dialog dialog = new Dialog(mActivity);
        dialog.setContentView(R.layout.dialog_email);
        Button btnClose = dialog.findViewById(R.id.btn_close);
        ImageView imageview_close = dialog.findViewById(R.id.imageview_close);
        Button btnSave = dialog.findViewById(R.id.btnSave);
        final EditText edMail = dialog.findViewById(R.id.edMail);
        String custmoreEmail = invoiceList.get(position).getCustomerEmail();
        if (custmoreEmail != null) {
            edMail.setText(custmoreEmail);
        }
        edMail.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(mActivity, edMail);
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    handled = true;
                    String email = edMail.getText().toString();
                    if (!email.equals("")) {
                        if (Validation.isValidEmail(email)) {
                            String url = serverUrl + "/retail//" + Constant.FUNTION_MAILDUPLICATEINVOICE + "/" + invoiceList.get(position).getFormNo() + "?customerEmail=" + email;
                            isInternetPresent = serviceHandler.isConnectingToInternet();
                            if (serverUrl != null) {
                                if (isInternetPresent) {
                                    GetDataTask getInvoicetask = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                                        @Override
                                        public void onTaskComplete(String result) {
                                            if (result != null) {
                                                if (result.equals("Mail sent successfully")) {
                                                    if (dialog != null)
                                                        dialog.dismiss();
                                                    UtilView.showToast(mActivity, "Mail sent successfully");
                                                } else {
                                                    if (dialog != null)
                                                        dialog.dismiss();
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
                if (dialog != null) dialog.dismiss();
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog != null) dialog.dismiss();
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
                        String url = serverUrl + "/retail//" + Constant.FUNTION_MAILDUPLICATEINVOICE + "/" + invoiceList.get(position).getFormNo() + "?customerEmail=" + email;

                        if (serverUrl != null) {
                            isInternetPresent = serviceHandler.isConnectingToInternet();
                            if (isInternetPresent) {
                                GetDataTask getInvoicetask = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                                    @Override
                                    public void onTaskComplete(String result) {
                                        if (result != null) {
                                            if (result.equals("Mail sent successfully")) {
                                                if (dialog != null)
                                                    dialog.dismiss();
                                                UtilView.showToast(mActivity, "Mail sent successfully");
                                            } else {
                                                if (dialog != null)
                                                    dialog.dismiss();
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
            adapter = new SalesDuplicateInvoice_Adapter(mActivity, invoiceList);
            listviewInvoices.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
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
