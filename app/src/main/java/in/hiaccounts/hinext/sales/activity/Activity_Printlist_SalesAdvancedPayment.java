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
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hiaccounts.R;
import in.hiaccounts.hinext.checkout.model.sales.Checkout_Sales_ResponseData;
import in.hiaccounts.hinext.purchase.model.purchase_saveresponses.Checkout_ResponseData;
import in.hiaccounts.hinext.sales.adapter.Sales_AdvancedPaymentListAdapter;
import in.hiaccounts.hinext.sales.model.advance_payment.Sales_AdvancedPaymentListData;
import in.hiaccounts.pdfgenerator.PdfUtils;
import in.hiaccounts.pdfgenerator.sales.salesinvoice.PosA4SalesInvociePdfImpl;
import in.hiaccounts.pdfgenerator.sales.salesinvoice.PosSalesInvoicePdf;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

import static in.hiaccounts.R.string.response_error;

public class Activity_Printlist_SalesAdvancedPayment extends AppCompatActivity implements TextWatcher {
    private static String TAG = Activity_Printlist_SalesAdvancedPayment.class.getSimpleName();

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
    @BindView(R.id.listviewAdvancedPayment)
    ListView listviewAdvancedPayment;
    @BindView(R.id.llListview)
    LinearLayout llListview;
    @BindView(R.id.rlContent)
    RelativeLayout rlContent;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;



    Sales_AdvancedPaymentListAdapter adapter;
    private Checkout_Sales_ResponseData salesReturnOrderResponse = null;
    private int currentAPIVersion = Build.VERSION.SDK_INT;
    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 123;
    private List<Sales_AdvancedPaymentListData> advancedList;

    private Activity mActivity;
    private Boolean isInternetPresent = false;
    private ServiceHandler serviceHandler;
    private String serverUrl,paymentSearch="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_printlist_sales_advanced_payment);
        ButterKnife.bind(this);
        initComponents();
    }

    private void initComponents() {
        ButterKnife.bind(this);
        mActivity = this;
        edSearch.addTextChangedListener(this);
        serverUrl= UtilView.getUrl(mActivity);
        serviceHandler = new ServiceHandler(mActivity);
        toolbar.setTitle(getResources().getString(R.string.menu_duplicatesalesadvancedlist));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getSalesAdvancedPaymentItemList("");

        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    paymentSearch = edSearch.getText().toString().trim();
                    getSalesAdvancedPaymentItemList(paymentSearch);
                }
                return handled;
            }
        });

        listviewAdvancedPayment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (view.getId() == R.id.imgview_duplicateprint) {
                    isInternetPresent = serviceHandler.isConnectingToInternet();
                    if (serverUrl!=null) {
                        if (isInternetPresent) {
                            UtilView.showProgessBar(mActivity, progressBar);
                            String url = serverUrl + "/retail//"+ Constant.FUNTION_GETDUPLICATEINVOICE +"?invoiceNo="+ advancedList.get(position).getFormNo();
                            GetDataTask dataTask = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                                @Override
                                public void onTaskComplete(String result) {
                                    UtilView.hideProgessBar(mActivity, progressBar);
                                    if (result != null) {
                                        Gson gson = new Gson();
                                        try {

                                            salesReturnOrderResponse = gson.fromJson(result.toString(), Checkout_Sales_ResponseData.class);
                                            if (salesReturnOrderResponse != null) {
                                                if (salesReturnOrderResponse.getSiData() != null) {
                                                    if (currentAPIVersion >= Build.VERSION_CODES.M) {
                                                        checkPermission();
                                                    } else {
                                                        createPdf();
                                                    }
                                                } else {
                                                    UtilView.showErrorDialog(getResources().getString(R.string.response_error), mActivity);
                                                }
                                            } else {
                                                UtilView.showErrorDialog(getResources().getString(R.string.response_error), mActivity);
                                            }

                                        } catch (Exception e) {
                                            UtilView.showErrorDialog(getResources().getString(R.string.response_error), mActivity);
                                        }
                                    } else {
                                        UtilView.showErrorDialog(getResources().getString(R.string.response_error), mActivity);
                                    }
                                }
                            }, false);
                            dataTask.execute(url, "");
                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                        }
                    }else {
                        UtilView.gotToLogin(mActivity);
                    }
                }
            }
        });
    }

    private void getSalesAdvancedPaymentItemList(String search) {
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
                if (progressBar != null)
                    progressBar.setVisibility(View.VISIBLE);

                String url ="";
                if (search.equals("")) {
                    url = serverUrl + "/retail//" + Constant.FUNTION_GETSALESADVANCEDPAYMENTLIST+"?locationId=";

                }

                if (!search.equals("")) {
                    url = serverUrl + "/retail//" + Constant.FUNTION_GETSALESADVANCEDPAYMENTLIST+ search.replace(" ", "%20")+"?locationId=";

                }

                GetDataTask getOrderList = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        if (progressBar != null)
                            progressBar.setVisibility(View.GONE);
                        if (result != null) {
                            Gson gson = new Gson();
                            advancedList = new ArrayList<Sales_AdvancedPaymentListData>();
                            try {
                                JSONArray jsonArray = new JSONArray(result.toString());
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonInovice = jsonArray.getJSONObject(i);
                                    Sales_AdvancedPaymentListData inovice = gson.fromJson(jsonInovice.toString(), Sales_AdvancedPaymentListData.class);
                                    advancedList.add(inovice);
                                }
                                if (advancedList != null && advancedList.size() > 0) {

                                    llListview.setVisibility(View.VISIBLE);
                                    adapter = new Sales_AdvancedPaymentListAdapter(mActivity, advancedList);
                                    listviewAdvancedPayment.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                } else {
                                    llListview.setVisibility(View.GONE);

                                    adapter = new Sales_AdvancedPaymentListAdapter(mActivity, advancedList);
                                    listviewAdvancedPayment.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                    UtilView.showSuccessDialog("No Advanced Payment Available", mActivity);
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
            paymentSearch = "";
            getSalesAdvancedPaymentItemList(paymentSearch);
        }
    }

    @OnClick(R.id.imgviewSearch)
    public void onClick() {
        paymentSearch = edSearch.getText().toString().trim();
        getSalesAdvancedPaymentItemList(paymentSearch);
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
            if (salesReturnOrderResponse != null) {
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
                    if (salesReturnOrderResponse != null) {
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
        pdfFile = PdfUtils.createFile(salesReturnOrderResponse.getSiData().getSrlnNo() + ".pdf", Constant.DIRECTORY_DUPLICATESALES, Constant.DIRECTORY_SALESPOS);

        if (pdfFile != null) {

            InputStream logoInputStream = PdfUtils.getLogoInputStream(this, progressBar);

            if (logoInputStream != null) {
                try {
                    PosSalesInvoicePdf invoicePdf = new PosA4SalesInvociePdfImpl();
                    invoicePdf.generateTaxInvoice(mActivity, salesReturnOrderResponse, new FileOutputStream(pdfFile), logoInputStream);
                    UtilView.hideProgessBar(mActivity, progressBar);
                    openPdfInvoice(salesReturnOrderResponse.getSiData().getSrlnNo() + ".pdf", Constant.DIRECTORY_DUPLICATESALES, Constant.DIRECTORY_SALESPOS);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    PosSalesInvoicePdf invoicePdf = new PosA4SalesInvociePdfImpl();
                    invoicePdf.generateTaxInvoice(this, salesReturnOrderResponse, new FileOutputStream(pdfFile), null);
                    UtilView.hideProgessBar(mActivity, progressBar);
                    openPdfInvoice(salesReturnOrderResponse.getSiData().getSrlnNo() + ".pdf", Constant.DIRECTORY_DUPLICATESALES, Constant.DIRECTORY_SALESPOS);

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
