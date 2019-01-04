package in.hiaccounts.hinext.sales.activity;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lowagie.text.DocumentException;
import com.rey.material.widget.ProgressView;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.checkout.model.sales.Checkout_Sales_ResponseData;
import in.hiaccounts.hinext.purchase.model.purchase_saveresponses.Checkout_ResponseData;
import in.hiaccounts.hinext.sales.model.printlist.debitnote.Printlist_DebitnoteData;
import in.hiaccounts.hinext.sales.model.printlist.debitnote.Printlist_debitData;
import in.hiaccounts.hinext.sales.model.sales_debitnote.AddDebitNoteData;
import in.hiaccounts.hinext.sales.model.sales_pagedata.HinextSalesPageData;
import in.hiaccounts.hinext.sales.model.sales_pagedata.TaxList;
import in.hiaccounts.pdfgenerator.PdfUtils;
import in.hiaccounts.pdfgenerator.sales.salesorder.PosA4SalesSaveOrderPdfImpl;
import in.hiaccounts.pdfgenerator.sales.salesorder.PosSalesSaveOrderPdf;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;

import static in.hiaccounts.R.string.response_error;


public class Activity_AddDebitNote extends AppCompatActivity {
    public static String TAG = Activity_AddDebitNote.class.getSimpleName();
    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 123;
    private static final int REQUEST_GALLERY_CODE = 200;
    private static final int READ_REQUEST_CODE = 300;
    private int currentAPIVersion = Build.VERSION.SDK_INT;
    private Checkout_Sales_ResponseData saveSalesOrderResponse = null;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edSupplierName)
    EditText edSupplierName;
    @BindView(R.id.edInvoiceDate)
    EditText edInvoiceDate;
    @BindView(R.id.edInvoiceNo)
    EditText edInvoiceNo;
    @BindView(R.id.edInvoiceAmount)
    EditText edInvoiceAmount;
    @BindView(R.id.edMemo)
    EditText edMemo;
    @BindView(R.id.edIncrementAmount)
    EditText edIncrementAmount;
    @BindView(R.id.edAmountExcludeTax)
    EditText edAmountExcludeTax;
    @BindView(R.id.spinnerTax)
    Spinner spinnerTax;
    @BindView(R.id.edCess)
    EditText edCess;
    @BindView(R.id.edTaxAmount)
    EditText edTaxAmount;
    @BindView(R.id.edAmountIncludeTax)
    EditText edAmountIncludeTax;
    @BindView(R.id.btnClose)
    Button btnClose;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;

    private String callingfrom = "",invoiceDate;
    private boolean isAllValid = true;
    private ServiceHandler serviceHandler;
    private String itemStatus, serverUrl;
    Printlist_debitData debitNoteDetails;
    Printlist_DebitnoteData debitNoteDataDetails;

    private Activity activity;
    SharedPreference sharedPreference;
    private Boolean isInternetPresent = false;
    private List<Object> objectItemTaxList = new ArrayList<Object>();
    private HinextSalesPageData pageData;
    private final TaxList[] selectTaxList = {null};
    Long taxid;
    private String amtInclusivetax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_debit_note);
        ButterKnife.bind(this);
        initContentView();
    }

    private void initContentView() {
        ButterKnife.bind(this);
        activity = Activity_AddDebitNote.this;
        serverUrl= UtilView.getUrl(activity);
        Intent intent = getIntent();
        callingfrom = intent.getStringExtra("callingFrom");
        if(callingfrom.equals(Constant.FUNTION_EDITDEBITNOTE) ){
            debitNoteDetails = (Printlist_debitData) intent.getSerializableExtra("debitnoteData");
        }
        if(callingfrom.equals(Constant.FUNTION_ADDDEBITNOTE) ){
            debitNoteDataDetails = (Printlist_DebitnoteData) intent.getSerializableExtra("debitnoteData");

        }


        serviceHandler = new ServiceHandler(activity);
        sharedPreference = SharedPreference.getInstance(activity);
        toolbar.setTitle(getResources().getString(R.string.AddDebitNote));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getPageLoadData();

        edIncrementAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().equals("")){
                    edAmountIncludeTax.setText(charSequence.toString());
                    edAmountExcludeTax.setText(charSequence.toString());
                    amtInclusivetax = edAmountIncludeTax.getText().toString();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        if (debitNoteDetails!= null){
            if (debitNoteDetails.getCustomer() !=null) {
                edSupplierName.setText(debitNoteDetails.getCustomer().toString());
            }



            if (debitNoteDetails.getInvoiceDate() !=null)
            {
                edInvoiceDate.setText(debitNoteDetails.getInvoiceDate());
                invoiceDate = debitNoteDetails.getInvoiceDate().toString();
            }


            if (debitNoteDetails.getInvoiceNo() !=null)
                edInvoiceNo.setText(""+debitNoteDetails.getInvoiceNo());

            if (debitNoteDetails.getIncrementAmt() !=null)
                edIncrementAmount.setText(""+debitNoteDetails.getIncrementAmt());

            if (debitNoteDetails.getInvAmt() !=null)
                edInvoiceAmount.setText(""+debitNoteDetails.getInvAmt());

            if (debitNoteDetails.getCessPer() !=null)
                edCess.setText(""+debitNoteDetails.getCessPer());

            if (debitNoteDetails.getMemo() !=null)
                edMemo.setText(""+debitNoteDetails.getMemo());

            edAmountExcludeTax.setText(edIncrementAmount.getText().toString());
            Double amountExcludetax = debitNoteDetails.getIncrementAmt()+debitNoteDetails.getCessAmt();
            edAmountIncludeTax.setText(""+amountExcludetax);
        }

        if(debitNoteDataDetails != null){
            if (debitNoteDataDetails.getCustomerName() !=null) {
                edSupplierName.setText(debitNoteDataDetails.getCustomerName().toString());
            }



            if (debitNoteDataDetails.getSiDate() !=null)
            {
                edInvoiceDate.setText(debitNoteDataDetails.getSiDate());
                invoiceDate = debitNoteDataDetails.getSiDate().toString();
            }


            if (debitNoteDataDetails.getFormNo() !=null)
                edInvoiceNo.setText(""+debitNoteDataDetails.getFormNo());

            if (debitNoteDataDetails.getIncrementAmt() !=null)
                edIncrementAmount.setText(""+debitNoteDataDetails.getIncrementAmt());

            if (debitNoteDataDetails.getAmount() !=null)
                edInvoiceAmount.setText(""+debitNoteDataDetails.getAmount());

            if (debitNoteDataDetails.getCessPer() !=null)
                edCess.setText(""+debitNoteDataDetails.getCessPer());




        }


    }

    private void getPageLoadData() {
        String companyData = sharedPreference.getData(Constant.HINEXTSALESPAGEDATA_KEY);
        Gson gson = new Gson();
        try {
            if(companyData !=null){
                pageData = gson.fromJson(companyData, HinextSalesPageData.class);

                ArrayAdapter<TaxList> spinnerCompanyLocationAdapter = UtilView.setupHinextSalesTaxAdapter(activity, spinnerTax, pageData.getTaxList());
                selectTaxList[0] = spinnerCompanyLocationAdapter.getItem(0);
                spinnerTax.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        selectTaxList[0] = (TaxList) adapterView.getSelectedItem();
                        if (selectTaxList[0] != null) {
                            taxid = selectTaxList[0].getTaxid();

                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }


        } catch (Exception e) {
            UtilView.showErrorDialog(getResources().getString(response_error), activity);
        }
    }

    @OnClick({R.id.edInvoiceDate ,R.id.btnClose, R.id.btnSave})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edInvoiceDate:
                getInvoiceDatePicker(activity);
                break;
            case R.id.btnClose:
                finish();
                break;
            case R.id.btnSave:
                addDebitNote();
                break;
        }
    }

    private void addDebitNote() {
        String customername = edSupplierName.getText().toString().trim();
        String invoiceNo = edInvoiceNo.getText().toString().trim();
        String incamount = edIncrementAmount.getText().toString().trim();
        String taxamt = edTaxAmount.getText().toString().trim();
        String cess = edCess.getText().toString().trim();
        String memo = edMemo.getText().toString().trim();





        if (incamount != null && !incamount.equals("") && isAllValid) {

            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {

                UtilView.showProgessBar(activity, progressBar);
                AddDebitNoteData addDebitData = new AddDebitNoteData();

                if(callingfrom.equals(Constant.FUNTION_EDITDEBITNOTE) ){

                    addDebitData.setFormNo(debitNoteDetails.getFormNo());
                    addDebitData.setInvoiceNo(debitNoteDetails.getInvoiceNo());
                    addDebitData.setIncPercent("");
                    addDebitData.setInvoiceDate(invoiceDate);
                    addDebitData.setIncrementAmt(incamount);
                    addDebitData.setTaxAmt(0d);
                    addDebitData.setTaxPer(taxid);
                    addDebitData.setCessPer(cess);
                    addDebitData.setMemo(memo);
                    addDebitData.setAmtIncTax(Double.valueOf(amtInclusivetax));

                }

                if(callingfrom.equals(Constant.FUNTION_ADDDEBITNOTE) ) {
                    addDebitData.setInvoiceNo(debitNoteDataDetails.getFormNo());

                    addDebitData.setIncPercent("");
                    addDebitData.setInvoiceDate(invoiceDate);
                    addDebitData.setIncrementAmt(incamount);
                    addDebitData.setTaxAmt(0d);
                    addDebitData.setTaxPer(taxid);
                    addDebitData.setCessPer(cess);
                    addDebitData.setMemo(memo);
                    addDebitData.setFormNo("");
                    addDebitData.setAmtIncTax(Double.valueOf(amtInclusivetax));
                }






                String url = serverUrl + "/retail//" + Constant.ADDDEBITNOTE+"?cdFlag=undefined";

                if (serverUrl != null) {
                    isInternetPresent = serviceHandler.isConnectingToInternet();
                    if (isInternetPresent) {
                        UtilView.showProgessBar(activity, progressBar);
                        PostDataTask task = new PostDataTask(activity, new AsyncTaskCompleteListener<String>() {
                            @Override
                            public void onTaskComplete(String result) {
                                UtilView.hideProgessBar(activity, progressBar);

                                if (result != null) {
                                    if (result.equals("invalid")) {
                                        UtilView.showToast(activity, getResources().getString(R.string.accesstoken_error));
                                        Intent intent = new Intent(activity, Activity_Login.class);
                                        activity.startActivity(intent);
                                        activity.finish();
                                    } else {

                                        Gson gson = new Gson();
                                        try {
                                            saveSalesOrderResponse = gson.fromJson(result.toString(), Checkout_Sales_ResponseData.class);
                                            if (saveSalesOrderResponse != null) {
                                                if (saveSalesOrderResponse.getSiData() != null) {
                                                    if (currentAPIVersion >= Build.VERSION_CODES.M) {
                                                        checkPermission();
                                                    } else {
                                                        createSalesPdf();
                                                    }
                                                } else {
                                                    UtilView.showToast(activity, "Save Sales Order doesn't save. Please try again");
                                                }
                                            } else {
                                                UtilView.showLogCat(TAG, "Some Error");
                                            }
                                        } catch (Exception e) {
                                            UtilView.showErrorDialog(getResources().getString(response_error), activity);
                                        }

                                    }
                                } else {
                                    progressBar.setVisibility(View.GONE);
                                    UtilView.showErrorDialog(getResources().getString(R.string.error_null), activity);
                                }
                            }
                        }, false);
                        task.execute(new Gson().toJson(addDebitData), url, "");

                    }
                    if (!isInternetPresent) {
                        UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
                    }
                } else {
                    UtilView.getUrl(activity);
                }
            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
            }
        } else {
          /*  if (customername.equals("") || customername == null) {
                edSupplierName.setError(getString(R.string.err_msg));
            }*/
            if (incamount.equals("") || incamount == null) {
                edIncrementAmount.setError(getString(R.string.err_msg));
            }



        }
    }

    public void getInvoiceDatePicker(Activity mActivity) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final TimeZone utc = TimeZone.getTimeZone("UTC");
        final SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        f.setTimeZone(utc);
        DatePickerDialog datePickerDialog = new DatePickerDialog(mActivity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                edInvoiceDate.setText(String.valueOf(new StringBuilder().append(year).append("-").append(month + 1).append("-").append(dayOfMonth)));
                calendar.set(year, month, dayOfMonth);
                invoiceDate = f.format(calendar.getTime());
            }
        }, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();


    }

    private void checkPermission() {
        String permission = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(activity, new String[]{permission}, MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            } else {
                ActivityCompat.requestPermissions(activity, new String[]{permission}, MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            }
        } else {

            if (saveSalesOrderResponse != null) {
                createSalesPdf();
            }


        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (ActivityCompat.checkSelfPermission(activity, permissions[0]) == PackageManager.PERMISSION_GRANTED) {
            switch (requestCode) {
                case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE:
                    if (saveSalesOrderResponse != null) {
                        createSalesPdf();
                    }

                    break;
            }
        } else {
            Toast.makeText(activity, "Permisson Deny", Toast.LENGTH_LONG).show();
        }
    }


    private void createSalesPdf() {
        File pdfFile = null;
        pdfFile = PdfUtils.createFile(saveSalesOrderResponse.getSiData().getSiNo() + ".pdf", Constant.DIRECTORY_DUPLICATESALES, Constant.DIRECTORY_SAVESALESORDER);
        if (pdfFile != null) {
            InputStream logoInputStream = PdfUtils.getLogoInputStream(activity, progressBar);
            if (logoInputStream != null) {
                try {
                    PosSalesSaveOrderPdf invoicePdf = new PosA4SalesSaveOrderPdfImpl();
                    invoicePdf.generateSalesSaveOrder(activity, saveSalesOrderResponse, new FileOutputStream(pdfFile), logoInputStream);
                    UtilView.hideProgessBar(activity, progressBar);
                    openPdfInvoice(saveSalesOrderResponse.getSiData().getSiNo() + ".pdf", Constant.DIRECTORY_DUPLICATESALES, Constant.DIRECTORY_SAVESALESORDER);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    PosSalesSaveOrderPdf invoicePdf = new PosA4SalesSaveOrderPdfImpl();
                    invoicePdf.generateSalesSaveOrder(activity, saveSalesOrderResponse, new FileOutputStream(pdfFile), null);
                    UtilView.hideProgessBar(activity, progressBar);
                    openPdfInvoice(saveSalesOrderResponse.getSiData().getSiNo() + ".pdf", Constant.DIRECTORY_DUPLICATESALES, Constant.DIRECTORY_SAVESALESORDER);
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
                UtilView.showToast(activity, "Your device is compatible for view Pdf File.");
            }
        } else {
            UtilView.showToast(activity, "No Invoice generated.");
        }

    }

}
