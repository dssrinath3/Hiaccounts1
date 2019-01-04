package in.hiaccounts.hinext.purchase.activity;

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
import in.hiaccounts.hinext.purchase.model.purchase_debitnote.Purchase_DebitNoteList;
import in.hiaccounts.hinext.purchase.model.purchase_pagedata.HinextPurchasePageData;
import in.hiaccounts.hinext.purchase.model.purchase_pagedata.TaxList;
import in.hiaccounts.hinext.purchase.model.purchase_saveresponses.Checkout_ResponseData;
import in.hiaccounts.hinext.sales.model.printlist.debitnote.Printlist_DebitnoteData;
import in.hiaccounts.hinext.sales.model.sales_debitnote.AddDebitNoteData;
import in.hiaccounts.pdfgenerator.PdfUtils;
import in.hiaccounts.pdfgenerator.purchase.purchase_orders.PosA4PurchaseOrderPdfImpl;
import in.hiaccounts.pdfgenerator.purchase.purchase_orders.PosPurchaseOrderPdf;
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
    private Checkout_ResponseData invoiceData = null;
    private int currentAPIVersion = Build.VERSION.SDK_INT;

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
    Purchase_DebitNoteList debitNoteDetails;
    Purchase_DebitNoteList debitNoteDataDetails;

    private Activity activity;
    SharedPreference sharedPreference;
    private Boolean isInternetPresent = false;
    private List<Object> objectItemTaxList = new ArrayList<Object>();
    private HinextPurchasePageData pageData;
    private final TaxList[] selectTaxList = {null};
    Long taxid;
    private String amtInclusivetax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_debitnote);
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
            debitNoteDetails = (Purchase_DebitNoteList) intent.getSerializableExtra("debitnoteData");
        }
        if(callingfrom.equals(Constant.FUNTION_ADDDEBITNOTE) ){
            debitNoteDataDetails = (Purchase_DebitNoteList) intent.getSerializableExtra("debitnoteData");

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
            if (debitNoteDetails.getSupplierName() !=null) {
                edSupplierName.setText(debitNoteDetails.getSupplierName().toString());
            }



            if (debitNoteDetails.getSiDate() !=null)
            {
                edInvoiceDate.setText(debitNoteDetails.getSiDate());
                invoiceDate = debitNoteDetails.getSiDate().toString();
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
                pageData = gson.fromJson(companyData, HinextPurchasePageData.class);

                ArrayAdapter<TaxList> spinnerCompanyLocationAdapter = UtilView.setupHinextPurchaseTaxAdapter(activity, spinnerTax, pageData.getTaxList());
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
                    addDebitData.setInvoiceNo(String.valueOf(debitNoteDetails.getInvoiceNo()));
                    addDebitData.setIncPercent("");
                    addDebitData.setInvoiceDate(invoiceDate);
                    addDebitData.setIncrementAmt("-"+incamount);
                    addDebitData.setTaxAmt(0d);
                    addDebitData.setTaxPer(taxid);
                    addDebitData.setCessPer(cess);
                    addDebitData.setMemo(memo);
                    addDebitData.setAmtIncTax(Double.valueOf("-"+amtInclusivetax));

                }

                if(callingfrom.equals(Constant.FUNTION_ADDDEBITNOTE) ) {
                    addDebitData.setInvoiceNo(debitNoteDataDetails.getFormNo());

                    addDebitData.setIncPercent("");
                    addDebitData.setInvoiceDate(invoiceDate);
                    addDebitData.setIncrementAmt("-"+incamount);
                    addDebitData.setTaxAmt(0d);
                    addDebitData.setTaxPer(taxid);
                    addDebitData.setCessPer(cess);
                    addDebitData.setMemo(memo);
                    addDebitData.setFormNo("");
                    addDebitData.setAmtIncTax(Double.valueOf("-"+amtInclusivetax));
                }






                String url = serverUrl + "/purchase//1/" + Constant.ADDCREDITNOTE+"?cdFlag=undefined";

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
                                            JSONObject jsonObject = new JSONObject(result.toString());
                                            invoiceData= gson.fromJson(jsonObject.toString(), Checkout_ResponseData.class);
                                            if (invoiceData != null) {
                                                if (invoiceData.getPiData() != null) {
                                                    if (currentAPIVersion >= Build.VERSION_CODES.M) {
                                                        checkPermission();
                                                    } else {
                                                        createPdf();
                                                    }
                                                }
                                            } else {
                                                UtilView.showToast(activity, "Save Purchase Order not placed. Please try again.");
                                            }
                                        } catch (Exception e) {
                                            UtilView.showErrorDialog(getResources().getString(response_error), activity);
                                        }
                                       /* try {
                                            JSONObject jsonObject = new JSONObject(result.toString());
                                            AddDebitNoteData addData = gson.fromJson(jsonObject.toString(), AddDebitNoteData.class);

                                            if (addData != null) {
                                                Intent returnIntent = new Intent();
                                                activity.setResult(Activity.RESULT_OK, returnIntent);
                                                activity.finish();
                                                UtilView.showToast(activity, "DebitNote added successfully.");

                                            } else {
                                                UtilView.showToast(activity, "Something Error.");
                                            }


                                        } catch (Exception e) {
                                            UtilView.showLogCat(TAG, "TaskCompleteListener Exception " + e.toString());
                                        }*/

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
            if (invoiceData != null) {
                createPdf();
            }
           /* if (paymentInvoice != null) {
                createPaymentPdf();
            }*/

        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (ActivityCompat.checkSelfPermission(activity, permissions[0]) == PackageManager.PERMISSION_GRANTED) {
            switch (requestCode) {

                case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE:
                    if (invoiceData != null) {
                        createPdf();
                    }
                    /*if (paymentInvoice != null) {
                        createPaymentPdf();
                    }
*/
                    break;
            }


        } else {
            Toast.makeText(activity, "Permisson Deny", Toast.LENGTH_LONG).show();
        }
    }

    private void createPdf() {
        File pdfFile = null;
        pdfFile = PdfUtils.createFile(invoiceData.getPiData().getPiNo() + ".pdf", Constant.DIRECTORY_PURCHASE, Constant.DIRECTORY_PURCHASEORDER);

        if (pdfFile != null) {

            InputStream logoInputStream = PdfUtils.getLogoInputStream(activity, progressBar);


            if (logoInputStream != null) {
                try {
                    PosPurchaseOrderPdf invoicePdf = new PosA4PurchaseOrderPdfImpl();
                    invoicePdf.generatePurchaseOrderInvoice(activity, invoiceData, new FileOutputStream(pdfFile), logoInputStream);

                    openPdfInvoice(invoiceData.getPiData().getPiNo() + ".pdf", Constant.DIRECTORY_PURCHASE, Constant.DIRECTORY_PURCHASEORDER);


                } catch (IOException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    PosPurchaseOrderPdf invoicePdf = new PosA4PurchaseOrderPdfImpl();
                    invoicePdf.generatePurchaseOrderInvoice(activity, invoiceData, new FileOutputStream(pdfFile), logoInputStream);
                    openPdfInvoice(invoiceData.getPiData().getPiNo() + ".pdf", Constant.DIRECTORY_PURCHASE, Constant.DIRECTORY_PURCHASEORDER);

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
