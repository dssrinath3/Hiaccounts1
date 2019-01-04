package in.hiaccounts.hinext.inventory.fragment;


import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
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
import butterknife.Unbinder;
import in.hiaccounts.R;
import in.hiaccounts.hinext.checkout.model.sales.Checkout_Sales_ResponseData;
import in.hiaccounts.hinext.generaltransaction.model.response.SaveResponseData;
import in.hiaccounts.hinext.inventory.activity.Activity_AddPaymentVoucher;
import in.hiaccounts.hinext.inventory.adapter.PaymentVoucherAdapter;
import in.hiaccounts.hinext.inventory.model.paymentvoucher.PaymentVoucherSelectData;
import in.hiaccounts.hinext.purchase.model.purchase_saveresponses.Checkout_ResponseData;
import in.hiaccounts.pdfgenerator.PdfUtils;
import in.hiaccounts.pdfgenerator.generaltransaction.GTExpenseVoucherPdf;
import in.hiaccounts.pdfgenerator.generaltransaction.GTExpenseVoucherPdfImpl;
import in.hiaccounts.pdfgenerator.sales.salesinvoice.PosA4SalesInvociePdfImpl;
import in.hiaccounts.pdfgenerator.sales.salesinvoice.PosSalesInvoicePdf;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * created by srinath 09-12-2017.
 */
public class Fragment_InventoryPaymentVoucher extends Fragment implements TextWatcher {

    public static String TAG = Fragment_InventoryPaymentVoucher.class.getSimpleName();

    private Checkout_Sales_ResponseData saleInoviceData = null;
    private int currentAPIVersion = Build.VERSION.SDK_INT;
    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 123;

    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.lvPaymentVoucher)
    ListView lvPaymentVoucher;
    @BindView(R.id.edSearchPaymentVoucher)
    EditText edSearchPaymentVoucher;
    @BindView(R.id.llSearchPaymentVoucher)
    LinearLayout llSearchPaymentVoucher;
    @BindView(R.id.ll)
    RelativeLayout ll;
    @BindView(R.id.ll_searchpaymentvoucher)
    LinearLayout llSearchpaymentvoucher;
    Unbinder unbinder;
    List<PaymentVoucherSelectData> voucherSelectData;
    PaymentVoucherSelectData paymentVoucherDetails;
    private ServiceHandler serviceHandler;
    private Activity activity;
    private Boolean isInternetPresent = false;
    private String paymentVoucherSearch = "", serverUrl;
    PaymentVoucherSelectData paymentVoucherSelectData;

    public static Fragment_InventoryPaymentVoucher newInstance() {
        Fragment_InventoryPaymentVoucher fragment = new Fragment_InventoryPaymentVoucher();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inventory_payment_voucher, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponents(view);
        return view;
    }

    private void initComponents(View view) {
        setHasOptionsMenu(true);
        serviceHandler = new ServiceHandler(activity);
        serverUrl= UtilView.getUrl(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.inventory_adapter_payment_voucherview, null, false);
        TextView tv_paymentVoucherAmount = header.findViewById(R.id.tv_paymentVoucherAmount);
        TextView tv_paymentVoucherReason = header.findViewById(R.id.tv_paymentVoucherReason);
        TextView tv_paymentVoucherNo = header.findViewById(R.id.tv_paymentVoucherNo);
        TextView tv_paymentVoucherEdit = header.findViewById(R.id.tv_paymentVoucherEdit);
        tv_paymentVoucherNo.setText("Voucher No");
        tv_paymentVoucherAmount.setText("Amount");
        tv_paymentVoucherReason.setText("Reason");
        tv_paymentVoucherEdit.setText("Action");
        UtilView.setTextAppearanceSmall(activity, tv_paymentVoucherAmount);
        UtilView.setTextAppearanceSmall(activity, tv_paymentVoucherReason);
        UtilView.setTextAppearanceSmall(activity, tv_paymentVoucherNo);
        UtilView.setTextAppearanceSmall(activity, tv_paymentVoucherEdit);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tv_paymentVoucherAmount);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tv_paymentVoucherReason);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tv_paymentVoucherNo);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tv_paymentVoucherEdit);
        if (lvPaymentVoucher != null)
            lvPaymentVoucher.addHeaderView(header);
        edSearchPaymentVoucher.addTextChangedListener(this);

        getPaymentVoucherFromServer("");

        edSearchPaymentVoucher.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(activity, edSearchPaymentVoucher);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    paymentVoucherSearch = edSearchPaymentVoucher.getText().toString().trim();
                    getPaymentVoucherFromServer(paymentVoucherSearch);
                }
                return handled;
            }
        });
        lvPaymentVoucher.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               paymentVoucherSelectData = voucherSelectData.get(position);
                if (view.getId() == R.id.imgviewEdit) {
                    if (voucherSelectData != null) {

                        Intent intent = new Intent(activity, Activity_AddPaymentVoucher.class);
                        intent.putExtra("paymentVoucherData", paymentVoucherSelectData);
                        intent.putExtra("callingFor", Constant.CALL_EDITPAYMENTVOUCHER);
                        startActivityForResult(intent, Constant.RESQUSTCODE_EDITPAYMENTVOUCHER);
                    }

                }
                 if (view.getId() == R.id.imgviewPrint) {
                     isInternetPresent = serviceHandler.isConnectingToInternet();
                     if (serverUrl != null) {
                         if (isInternetPresent) {
                             UtilView.showProgessBar(activity, progressBar);
                             String url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETPAYMENTVOUCHERPRINT +"/"+ paymentVoucherSelectData.getPvId();
                             PostDataTask getInvoicetask = new PostDataTask(activity, new AsyncTaskCompleteListener<String>() {
                                 @Override
                                 public void onTaskComplete(String result) {
                                     Gson gson = new Gson();
                                     try {

                                         saleInoviceData = gson.fromJson(result.toString(), Checkout_Sales_ResponseData.class);
                                         if (saleInoviceData != null) {
                                             if (saleInoviceData.getSiData() != null) {
                                                // if (saleInoviceData.getSiData().getPrintType() != null) {
                                                     if (currentAPIVersion >= Build.VERSION_CODES.M) {
                                                         checkPermission();
                                                     } else {
                                                         createPdf();
                                                     }
                                                // }
                                             } else {
                                                 UtilView.showToast(activity, getResources().getString(R.string.response_error));
                                             }
                                         } else {
                                             UtilView.showToast(activity, getResources().getString(R.string.response_error));
                                         }

                                     } catch (Exception e) {
                                         UtilView.showErrorDialog(getResources().getString(R.string.response_error), activity);
                                     }
                                 }
                             }, false);
                             getInvoicetask.execute(new Gson().toJson(paymentVoucherSelectData),url, "");
                         } else {
                             UtilView.showToast(activity, getResources().getString(R.string.intertnet_status));
                         }
                     } else {
                         UtilView.gotToLogin(activity);
                     }

                }
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.llSearchPaymentVoucher})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llSearchPaymentVoucher:
                UtilView.hideSoftKeyboard(activity, edSearchPaymentVoucher);
                paymentVoucherSearch = edSearchPaymentVoucher.getText().toString().trim();
                getPaymentVoucherFromServer(paymentVoucherSearch);
                break;

        }
    }
    private void getPaymentVoucherFromServer(String search) {
        String url = "";
        if (search.equals("")) {
            url = serverUrl + "/hipos//1/" + Constant.FUNTION_GETPAYMENTVOUCHER + "?paymentVoucherSearchText=&type=";
        }
        if (!search.equals("")) {
            url = serverUrl + "/hipos//1/" + Constant.FUNTION_GETPAYMENTVOUCHER + "?paymentVoucherSearchText=" + search.replace(" ", "%20") + "&type=";
        }

        if (serverUrl!=null) {
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {
                // prepare the Request
                UtilView.showProgessBar(activity, progressBar);
                GetDataTask task = new GetDataTask(activity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(activity, progressBar);
                        if (result != null) {
                            try {
                                voucherSelectData = new ArrayList<PaymentVoucherSelectData>();
                                JSONArray resultJsonArray = new JSONArray(result.toString());
                                for (int i = 0; i < resultJsonArray.length(); i++) {
                                    JSONObject asJson = resultJsonArray.getJSONObject(i);
                                    Gson gson = new Gson();
                                    PaymentVoucherSelectData data = gson.fromJson(asJson.toString(), PaymentVoucherSelectData.class);
                                    voucherSelectData.add(data);
                                }
                                if (voucherSelectData!=null && voucherSelectData.size()>0){
                                    PaymentVoucherAdapter adapter = new PaymentVoucherAdapter(activity,voucherSelectData);
                                    lvPaymentVoucher.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                }
                                else {
                                    voucherSelectData.clear();
                                    PaymentVoucherAdapter adapter = new PaymentVoucherAdapter(activity, voucherSelectData);
                                    lvPaymentVoucher.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                    UtilView.showErrorDialog(getResources().getString(R.string.paymentVoucher_notavailbale), activity);

                                }
                            }catch (Exception e){
                                UtilView.showErrorDialog(getResources().getString(R.string.error_null), activity);
                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.error_null), activity);
                        }
                    }
                }, false);
                task.execute(url, "");
            }
            if (!isInternetPresent) {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
            }
        }else {
            UtilView.gotToLogin(activity);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == Constant.RESQUSTCODE_ADDPAYMENTVOUCHER) {
            getPaymentVoucherFromServer("");
        }

        if (resultCode == Activity.RESULT_OK && requestCode == Constant.RESQUSTCODE_EDITPAYMENTVOUCHER) {
            getPaymentVoucherFromServer("");
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
            UtilView.hideSoftKeyboard(activity, edSearchPaymentVoucher);
            paymentVoucherSearch = "";
            getPaymentVoucherFromServer(paymentVoucherSearch);
        }
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.inventory_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.addMenu:
                Intent intent = new Intent(activity, Activity_AddPaymentVoucher.class);
                intent.putExtra("callingFor", Constant.CALL_ADDPAYMENTVOUCHER);
                startActivityForResult(intent, Constant.RESQUSTCODE_ADDPAYMENTVOUCHER);
                break;
        }
        return super.onOptionsItemSelected(item);
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
            if (saleInoviceData != null) {
                createPdf();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (ActivityCompat.checkSelfPermission(activity, permissions[0]) == PackageManager.PERMISSION_GRANTED) {
            switch (requestCode) {
                case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE:
                    if (saleInoviceData != null) {
                        createPdf();
                    }
                    break;
            }
        } else {
            Toast.makeText(activity, "Permisson Deny", Toast.LENGTH_LONG).show();
        }
    }

    private void createPdf() {
        File pdfFile = null;
        pdfFile = PdfUtils.createFile(saleInoviceData.getSiData().getSrlnNo() + ".pdf", Constant.DIRECTORY_DUPLICATESALES, Constant.DIRECTORY_SALESPOS);

        if (pdfFile != null) {

            InputStream logoInputStream = PdfUtils.getLogoInputStream(activity, progressBar);

            if (logoInputStream != null) {
                try {
                    PosSalesInvoicePdf invoicePdf = new PosA4SalesInvociePdfImpl();
                    invoicePdf.generateTaxInvoice(activity, saleInoviceData, new FileOutputStream(pdfFile), logoInputStream);
                    UtilView.hideProgessBar(activity, progressBar);
                    openPdfInvoice(saleInoviceData.getSiData().getSrlnNo() + ".pdf", Constant.DIRECTORY_DUPLICATESALES, Constant.DIRECTORY_SALESPOS);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    PosSalesInvoicePdf invoicePdf = new PosA4SalesInvociePdfImpl();
                    invoicePdf.generateTaxInvoice(activity, saleInoviceData, new FileOutputStream(pdfFile), null);
                    UtilView.hideProgessBar(activity, progressBar);
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
                UtilView.showToast(activity, "Your device is compatible for view Pdf File.");
            }
        } else {
            UtilView.showToast(activity, "No Invoice generated.");
        }

    }


}
