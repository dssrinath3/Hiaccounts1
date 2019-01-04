package in.hiaccounts.hinext.purchase.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.Gson;
import com.rey.material.app.Dialog;
import com.rey.material.widget.ProgressView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.hiaccounts.R;
import in.hiaccounts.hinext.checkout.activity.Activity_Checkout;
import in.hiaccounts.hinext.purchase.adapter.PurchaseAdvancePayment_Adapter;
import in.hiaccounts.hinext.purchase.model.purchase_payments.PurchaseAdvancePayment;
import in.hiaccounts.hinext.supplier.model.GetSupplier;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 7/22/2017.
 */

public class ActivityPurchase_AdvancePayment extends AppCompatActivity implements TextWatcher {
    private static String TAG = ActivityPurchase_AdvancePayment.class.getSimpleName();
    @BindView(R.id.edSearch)
    EditText edSearch;
    @BindView(R.id.lvPaymentList)
    ListView lvPaymentList;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.llContent)
    LinearLayout llContent;

    private Activity mActivity;
    private GetSupplier supplier = null;
    private Dialog dialog;
    private ServiceHandler serviceHandler;
    private Boolean isInternetPresent = false;
    private SharedPreference sharedPreference;
    private List<PurchaseAdvancePayment> paymentInvoiceList;
    private Gson gson = new Gson();
    private String selectedTaxType = "", selectedDate = "", serverUrl;
    PurchaseAdvancePayment_Adapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salesadvancepaymentlist);
        initComponentVIew();
    }

    private void initComponentVIew() {
        ButterKnife.bind(this);
        mActivity = ActivityPurchase_AdvancePayment.this;
        serverUrl = UtilView.getUrl(mActivity);

        edSearch.addTextChangedListener(this);
        supplier = (GetSupplier) getIntent().getSerializableExtra("supplier");
        selectedTaxType = getIntent().getStringExtra("selectedTax");
        selectedDate = getIntent().getStringExtra("selectedDate");

        toolbar.setTitle(getResources().getString(R.string.advancePayment));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        dialog = new Dialog(this);
        serviceHandler = new ServiceHandler(this);
        sharedPreference = SharedPreference.getInstance(this);
        paymentInvoiceList = new ArrayList<PurchaseAdvancePayment>();

     /*   LayoutInflater inflater = this.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.adapter_purchase_advancepaymentlist, lvPaymentList, false);
        header.findViewById(R.id.tvFormNo);
        header.findViewById(R.id.tvSupplierName);
        header.findViewById(R.id.tvRemainingAmount);
        header.findViewById(R.id.tvTotalRecieved);

        UtilView.setTextAppearanceSmall(this, (TextView) header.findViewById(R.id.tvFormNo));
        UtilView.setTextAppearanceSmall(this, (TextView) header.findViewById(R.id.tvSupplierName));
        UtilView.setTextAppearanceSmall(this, (TextView) header.findViewById(R.id.tvRemainingAmount));
        UtilView.setTextAppearanceSmall(this, (TextView) header.findViewById(R.id.tvTotalRecieved));

        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), (TextView) header.findViewById(R.id.tvFormNo));
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), (TextView) header.findViewById(R.id.tvSupplierName));
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), (TextView) header.findViewById(R.id.tvRemainingAmount));
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), (TextView) header.findViewById(R.id.tvTotalRecieved));

        UtilView.setTexttypeFace(null, Typeface.BOLD, (TextView) header.findViewById(R.id.tvFormNo));
        UtilView.setTexttypeFace(null, Typeface.BOLD, (TextView) header.findViewById(R.id.tvSupplierName));
        UtilView.setTexttypeFace(null, Typeface.BOLD, (TextView) header.findViewById(R.id.tvRemainingAmount));
        UtilView.setTexttypeFace(null, Typeface.BOLD, (TextView) header.findViewById(R.id.tvTotalRecieved));
        lvPaymentList.addHeaderView(header);*/
        getAdvancePaymentList();


        lvPaymentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                if (view.getId() == R.id.tvCreateInvoice) {
                    if (serverUrl != null) {
                        if (isInternetPresent) {
                            UtilView.showProgessBar(mActivity, progressBar);
                            String url = serverUrl + "/purchase//0/" + Constant.FUNTION_CREATEADVANCEPOSINVOICES + "?formNo=" + paymentInvoiceList.get(position).getFormNo();
                            GetDataTask getInvoicetask = new GetDataTask(ActivityPurchase_AdvancePayment.this, new AsyncTaskCompleteListener<String>() {
                                @Override
                                public void onTaskComplete(String result) {
                                    UtilView.hideProgessBar(mActivity, progressBar);
                                    if (result != null) {
                                        try {
                                            if (result.toString().equals("success")) {
                                                paymentInvoiceList.remove(position);
                                                UtilView.showToast(mActivity, getResources().getString(R.string.createInvoiceSuceessfully));
                                                PurchaseAdvancePayment_Adapter adapter = new PurchaseAdvancePayment_Adapter(mActivity, paymentInvoiceList);
                                                lvPaymentList.setAdapter(adapter);
                                                adapter.notifyDataSetChanged();
                                            } else {
                                                UtilView.showToast(mActivity, getResources().getString(R.string.createInvoiceSuceessfully));
                                            }

                                        } catch (Exception e) {
                                            UtilView.showErrorDialog(getResources().getString(R.string.response_error), mActivity);
                                        }
                                    } else {
                                        UtilView.showErrorDialog(getResources().getString(R.string.error_null), mActivity);
                                    }
                                }
                            }, false);
                            getInvoicetask.execute(url, "");
                        } else {
                            UtilView.showToast(ActivityPurchase_AdvancePayment.this, getResources().getString(R.string.intertnet_status));
                        }
                    } else {
                        UtilView.gotToLogin(mActivity);
                    }
                }
                if (view.getId() == R.id.tvRemainingPayment) {
                    if (supplier != null) {
                        Intent intent = new Intent(mActivity, Activity_Checkout.class);

                        intent.putExtra("selectedTax", selectedTaxType);
                        intent.putExtra("selectedDate", selectedDate);
                        intent.putExtra("paymentInvoice", paymentInvoiceList.get(position));
                        intent.putExtra("supplier", supplier);
                        intent.putExtra("checkoutType","advance");
                        intent.putExtra("callingfrom", Constant.NAVIGATION_GROUP_PURCHASE);
                        startActivityForResult(intent, Constant.RESQUSTCODE_CHECKOUT);
                    } else {
                        UtilView.showToast(mActivity, getResources().getString(R.string.selectCustomerError));
                    }
                }


            }

        });

    }

    private void getAdvancePaymentList() {
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
                UtilView.showProgessBar(mActivity, progressBar);
                String url = serverUrl + "/purchase//0/" + Constant.FUNTION_GETADVANCEPAYMENTINVOICES;
                GetDataTask getOrderList = new GetDataTask(this, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(mActivity, progressBar);
                        if (result != null) {
                            try {
                                if (paymentInvoiceList.size() > 0) {
                                    paymentInvoiceList.clear();
                                }
                                JSONArray jsonArray = new JSONArray(result);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject json = jsonArray.getJSONObject(i);
                                    PurchaseAdvancePayment paymentInvoice = gson.fromJson(json.toString(), PurchaseAdvancePayment.class);
                                    paymentInvoiceList.add(paymentInvoice);
                                }
                                if (paymentInvoiceList.size() > 0) {
                                    adapter = new PurchaseAdvancePayment_Adapter(ActivityPurchase_AdvancePayment.this, paymentInvoiceList);
                                    lvPaymentList.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                    llContent.setVisibility(View.VISIBLE);
                                } else {
                                    llContent.setVisibility(View.GONE);
                                    UtilView.showErrorDialog("No Invoices", ActivityPurchase_AdvancePayment.this);
                                }
                            } catch (Exception e) {
                                UtilView.showLogCat(TAG, "DataTaskListener Exception " + e.toString());
                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.error_null), ActivityPurchase_AdvancePayment.this);
                        }
                    }
                }, false);
                getOrderList.execute(url, "");
            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), this);
            }
        } else {
            UtilView.gotToLogin(mActivity);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constant.RESQUSTCODE_CHECKOUT && resultCode == RESULT_OK) {
            getAdvancePaymentList();
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
            getAdvancePaymentList();

        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
