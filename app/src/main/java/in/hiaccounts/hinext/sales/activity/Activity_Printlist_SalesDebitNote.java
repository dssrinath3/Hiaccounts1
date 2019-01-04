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
import in.hiaccounts.hinext.checkout.model.sales.Checkout_Sales_ResponseData;
import in.hiaccounts.hinext.purchase.model.purchase_saveresponses.Checkout_ResponseData;
import in.hiaccounts.hinext.sales.adapter.SalesDebitNoteList_Adapter;
import in.hiaccounts.hinext.sales.model.printlist.debitnote.Printlist_DebitnoteData;
import in.hiaccounts.hinext.sales.model.printlist.debitnote.Printlist_debitData;
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


public class Activity_Printlist_SalesDebitNote extends AppCompatActivity implements TextWatcher {
    private static String TAG = Activity_Printlist_SalesDebitNote.class.getSimpleName();
    private Checkout_Sales_ResponseData invoiceData = null;
    private int currentAPIVersion = Build.VERSION.SDK_INT;
    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 123;



    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edSearch)
    EditText edSearch;
    @BindView(R.id.imgviewSearch)
    ImageView imgviewSearch;
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
    @BindView(R.id.listviewDebitNote)
    ListView listviewDebitNote;
    @BindView(R.id.llListView)
    LinearLayout llListView;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;


    private Activity mActivity;
    private Boolean isInternetPresent = false;
    private ServiceHandler serviceHandler;
    private String serverUrl,debitSearch="",formno="";
    private Long id;
    private SalesPosCreator salesPosCreator;
    private List<Printlist_DebitnoteData> listData;
    private SalesDebitNoteList_Adapter adapter;
    private Printlist_DebitnoteData invData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_printlist_sales_debit_note);
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
        toolbar.setTitle(getResources().getString(R.string.menu_duplicatesalesdebitnote));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getSalesDebitNoteListitem("");

        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    debitSearch = edSearch.getText().toString().trim();
                    getSalesDebitNoteListitem(debitSearch);
                }
                return handled;
            }
        });

        listviewDebitNote.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                invData = listData.get(position);
                formno = invData.getFormNo();

                if (view.getId() == R.id.imgview_duplicateprint) {
                    isInternetPresent = serviceHandler.isConnectingToInternet();
                    if (serverUrl != null) {
                        if (isInternetPresent) {

                            String url = serverUrl + "/retail//" + Constant.FUNTION_GETSALESDEBITNOTEPRINT + "?invoiceNo=" + formno;
                            GetDataTask getInvoicetask = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                                @Override
                                public void onTaskComplete(String result) {
                                    try {

                                        Gson gson = new Gson();
                                        invoiceData = gson.fromJson(result.toString(), Checkout_Sales_ResponseData.class);
                                        if (invoiceData != null) {
                                            if (invoiceData.getSiData() != null) {
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
                if (view.getId() == R.id.imgviewPost) {
                    initPostDialog(formno);
                }
            }
        });
    }

    private void initPostDialog(String formno) {
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (isInternetPresent) {
            String url = "";

            url = serverUrl + "/retail//"+ Constant.FUNTION_SALESPOSTDEBITNOTE+"?formNo="+formno;
            PostDataTask postDataTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                @Override
                public void onTaskComplete(String result) {

                    if (result.toString() != null) {
                        Gson gson = new Gson();
                        try {

                            Printlist_DebitnoteData data = gson.fromJson(result.toString(), Printlist_DebitnoteData.class);
                            if (data != null) {
                                if (data.toString().trim() !=null) {
                                    Toast.makeText(mActivity, "Posted Successfully.", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(mActivity, "failed.", Toast.LENGTH_SHORT).show();
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
            postDataTask.execute(invData.toString(),url, "");


        } else {
            UtilView.showErrorDialog(mActivity.getResources().getString(R.string.intertnet_status), mActivity);
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

                    url = serverUrl + "/retail//"+ Constant.FUNTION_SALESINVOICECANCELDEBITNOTE+"?formNo="+formno;
                    PostDataTask postDataTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                        @Override
                        public void onTaskComplete(String result) {
                            if (pDialog != null)
                                pDialog.dismiss();

                            if (result.toString() != null) {
                                Gson gson = new Gson();
                                try {

                                    Printlist_DebitnoteData data = gson.fromJson(result.toString(), Printlist_DebitnoteData.class);
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
                                            getSalesDebitNoteListitem("");

                                        }
                                        else {
                                            if (sDialog != null) {
                                                sDialog.setTitleText(mActivity.getString(R.string.delfail))
                                                            /*.setContentText("Category "+category.getItemCategoryName())*/
                                                        .setConfirmText(mActivity.getString(R.string.bntok))
                                                        .setConfirmClickListener(null)
                                                        .changeAlertType(SweetAlertDialog.ERROR_TYPE);

                                            }
                                            getSalesDebitNoteListitem("");
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
                    postDataTask.execute(new Gson().toJson(invData),url, "");

                }
            });
            if (pDialog != null)
                pDialog.show();
        } else {
            UtilView.showErrorDialog(mActivity.getResources().getString(R.string.intertnet_status), mActivity);
        }
    }

    private void initEditDialog(final int position) {
        invData = listData.get(position);
        formno = invData.getFormNo();
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {

                String url = serverUrl + "/retail//" + Constant.FUNTION_GETSALESDEBITNOTEEDIT + "?invoiceNo=" + formno;
                GetDataTask getInvoicetask = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        try {
                            List<Printlist_debitData> list = new ArrayList<Printlist_debitData>();
                            Gson gson = new Gson();
                            Printlist_debitData debitnoteData = gson.fromJson(result.toString(), Printlist_debitData.class);
                            list.add(debitnoteData);
                            if (debitnoteData != null) {
                                Intent in = new Intent(mActivity,Activity_AddDebitNote.class);
                                in.putExtra("debitnoteData",list.get(position));
                                in.putExtra("callingFrom",Constant.FUNTION_EDITDEBITNOTE);
                                startActivityForResult(in,106);
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


    private void getSalesDebitNoteListitem(String search) {
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
                if (progressBar != null)
                    progressBar.setVisibility(View.VISIBLE);

                String url ="";
                if (search.equals("")) {
                    url = serverUrl + "/retail//" + Constant.FUNTION_GETSALESDEBITNOTE+"/?itemSearchText=&locationId=";

                }

                if (!search.equals("")) {
                    url = serverUrl + "/retail//" + Constant.FUNTION_GETSALESDEBITNOTE+"/?itemSearchText="+ search.replace(" ", "%20")+"&locationId=";

                }

                GetDataTask getOrderList = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        if (progressBar != null)
                            progressBar.setVisibility(View.GONE);
                        if (result != null) {
                            Gson gson = new Gson();
                            listData = new ArrayList<Printlist_DebitnoteData>();
                            try {
                                JSONArray jsonArray = new JSONArray(result.toString());
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonInovice = jsonArray.getJSONObject(i);
                                    Printlist_DebitnoteData inovice = gson.fromJson(jsonInovice.toString(), Printlist_DebitnoteData.class);
                                    listData.add(inovice);
                                }
                                if (listData != null && listData.size() > 0) {

                                    llListView.setVisibility(View.VISIBLE);
                                    adapter = new SalesDebitNoteList_Adapter(mActivity, listData);
                                    listviewDebitNote.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                } else {
                                    llListView.setVisibility(View.GONE);
                                    adapter = new SalesDebitNoteList_Adapter(mActivity, listData);
                                    listviewDebitNote.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                    UtilView.showSuccessDialog("No Sales Debit Note Available", mActivity);
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
            debitSearch = "";
            getSalesDebitNoteListitem(debitSearch);
        }
    }

    @OnClick(R.id.imgviewSearch)
    public void onClick() {
        debitSearch = edSearch.getText().toString().trim();
        getSalesDebitNoteListitem(debitSearch);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 106) {
            getSalesDebitNoteListitem("");
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
        pdfFile = PdfUtils.createFile(invoiceData.getSiData().getSrlnNo() + ".pdf", Constant.DIRECTORY_DUPLICATESALES, Constant.DIRECTORY_SALESPOS);

        if (pdfFile != null) {

            InputStream logoInputStream = PdfUtils.getLogoInputStream(this, progressBar);

            if (logoInputStream != null) {
                try {
                    PosSalesInvoicePdf invoicePdf = new PosA4SalesInvociePdfImpl();
                    invoicePdf.generateTaxInvoice(mActivity, invoiceData, new FileOutputStream(pdfFile), logoInputStream);
                    UtilView.hideProgessBar(mActivity, progressBar);
                    openPdfInvoice(invoiceData.getSiData().getSrlnNo() + ".pdf", Constant.DIRECTORY_DUPLICATESALES, Constant.DIRECTORY_SALESPOS);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    PosSalesInvoicePdf invoicePdf = new PosA4SalesInvociePdfImpl();
                    invoicePdf.generateTaxInvoice(this, invoiceData, new FileOutputStream(pdfFile), null);
                    UtilView.hideProgessBar(mActivity, progressBar);
                    openPdfInvoice(invoiceData.getSiData().getSrlnNo() + ".pdf", Constant.DIRECTORY_DUPLICATESALES, Constant.DIRECTORY_SALESPOS);

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
