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
import in.hiaccounts.hinext.sales.activity.Activity_SalesAdvancePayment;
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

public class Activity_SupplierPayment extends AppCompatActivity implements TextWatcher {
    private static String TAG = Activity_SalesAdvancePayment.class.getSimpleName();
    @BindView(R.id.edSearch)
    EditText edSearch;
    @BindView(R.id.lvPaymentList)
    ListView lvPaymentList;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.llListview)
    LinearLayout llListview;

    private Activity mActivity = this;
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
        setContentView(R.layout.activity_purchase_supplierpayment);
        initComponentVIew();
    }

    private void initComponentVIew() {
        ButterKnife.bind(this);
        edSearch.addTextChangedListener(this);
        serverUrl = UtilView.getUrl(mActivity);
        supplier = (GetSupplier) getIntent().getSerializableExtra("supplier");
        selectedTaxType = getIntent().getStringExtra("selectedTax");
        selectedDate = getIntent().getStringExtra("selectedDate");

        toolbar.setTitle(getResources().getString(R.string.supplierPaymentList));
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

        /*LayoutInflater inflater = this.getLayoutInflater();
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
        getRecievePaymentList();


        lvPaymentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                if (view.getId() == R.id.imageviewReturn) {
                }
                if (view.getId() == R.id.tvRemainingPayment) {
                    if (supplier != null) {
                        Intent intent = new Intent(mActivity, Activity_Checkout.class);
                        intent.putExtra("selectedTax", selectedTaxType);
                        intent.putExtra("selectedDate", selectedDate);
                        intent.putExtra("paymentInvoice", paymentInvoiceList.get(position));
                        intent.putExtra("supplier", supplier);
                        intent.putExtra("checkoutType","supplier");
                        intent.putExtra("callingfrom", Constant.NAVIGATION_GROUP_PURCHASE);
                        startActivityForResult(intent, Constant.RESQUSTCODE_CHECKOUT);
                    } else {
                        UtilView.showToast(mActivity, getResources().getString(R.string.selectCustomerError));
                    }
                }
            }
        });
    }

    private void getRecievePaymentList() {
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
                UtilView.showProgessBar(mActivity, progressBar);
                String url = serverUrl + "/purchase//0/" + Constant.FUNTION_GETSUPPLIERPAYMENT+"?itemSearchText=";
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
                                    llListview.setVisibility(View.VISIBLE);
                                    adapter = new PurchaseAdvancePayment_Adapter(mActivity, paymentInvoiceList);
                                    lvPaymentList.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                } else {
                                    llListview.setVisibility(View.GONE);
                                    UtilView.showErrorDialog("No Invoices", mActivity);
                                }
                            } catch (Exception e) {
                                UtilView.showErrorDialog(getResources().getString(R.string.error_null), mActivity);
                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.error_null), mActivity);
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

   /* private void initReturnDialog(final int position) {


        // custom dialog
        dialog.setContentView(R.layout.dialog_sales_returncheckout);
        // set the custom dialog components - text, image and button
        LinearLayout ll_cash = (LinearLayout) dialog.findViewById(R.id.ll_cash);
        LinearLayout ll_cheque = (LinearLayout) dialog.findViewById(R.id.ll_cheque);

        Button btnClose = (Button) dialog.findViewById(R.id.btn_close);
        ImageView imageview_close = (ImageView) dialog.findViewById(R.id.imageview_close);
        TextView btn_cash = (TextView) dialog.findViewById(R.id.btn_cash);


        Typeface font = Typeface.createFromAsset(this.getAssets(), "fonts/fontawesome-webfont.ttf");
        btn_cash.setTypeface(font);

        dialog.show();

        ll_cheque.setVisibility(View.GONE);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        imageview_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        ll_cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                initCashPaymetDialog(position);


            }

        });

    }

    private void initCashPaymetDialog(final int position) {

        dialog.setContentView(R.layout.dialog_sales_returncash);

        final ProgressView progressView = (ProgressView) dialog.findViewById(R.id.progress_bar);
        ImageView imageview_close = (ImageView) dialog.findViewById(R.id.imageview_close);
        EditText ed_return_totalamount = (EditText) dialog.findViewById(R.id.ed_return_totalamount);
        Button btn_returnamt = (Button) dialog.findViewById(R.id.btn_returnamt);
        Button btn_close = (Button) dialog.findViewById(R.id.btn_close);

        ed_return_totalamount.setText("" + paymentInvoiceList.get(position).getAmount());

        btn_returnamt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (selected_customer != null) {
                    Advance_Remaining_SalesReturn returnItemData = new Advance_Remaining_SalesReturn();
                    returnItemData.setOperation("Return");
                    returnItemData.setPaymentType("cashPayment");
                    returnItemData.setFormNo(paymentInvoiceList.get(position).getFormNo());
                    returnItemData.setTotalCashPymtAmtReturned(paymentInvoiceList.get(position).getAmount());
                    returnItemData.setCustomerId(selected_customer.getCustomerId());
                    Gson gson = new Gson();
                    if (isInternetPresent) {
                        progressView.setVisibility(View.VISIBLE);
                        String url = Constant.NGROK_URL + "/retail//" + Constant.FUNTION_RETURNRECIEVEPAYMENT;
                        String post_json = gson.toJson(returnItemData);
                        progressView.setVisibility(View.VISIBLE);
                        PostDataTask postDataTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                            @Override
                            public void onTaskComplete(String result) {
                                progressView.setVisibility(View.GONE);
                                if (result != null) {
                                    if (result.equals("invalid")) {
                                        UtilView.showToast(mActivity, getResources().getString(R.string.accesstoken_error));
                                        Intent intent = new Intent(mActivity, Activity_Login.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        try {
                                            if (result.equals("success\n")) {
                                                paymentInvoiceList.remove(position);
                                                UtilView.showToast(mActivity, getResources().getString(R.string.amoutReturnedSuccessfylly));
                                                SalesAdvancePayment_Adapter adapter = new SalesAdvancePayment_Adapter(mActivity, paymentInvoiceList);
                                                lvPaymentList.setAdapter(adapter);
                                                adapter.notifyDataSetChanged();
                                                if (dialog.isShowing()) {
                                                    dialog.dismiss();
                                                }
                                            } else {
                                                UtilView.showToast(mActivity, "Return Unsuccessfully. Please try again.");
                                            }

                                        } catch (Exception e) {

                                            UtilView.showLogCat(TAG, "SavePosTaskListener Exception " + e.toString());
                                        }
                                    }
                                } else {
                                    UtilView.showErrorDialog(getResources().getString(R.string.error_null), mActivity);
                                }

                            }
                        }, false);
                        postDataTask.execute(post_json, url, "");
                    } else {
                        UtilView.showToast(mActivity, getResources().getString(R.string.intertnet_status));
                    }

                }


            }
        });


        imageview_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();


    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == Constant.RESQUSTCODE_CHECKOUT) {
            getRecievePaymentList();
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
            getRecievePaymentList();

        }


    }

    @Override
    public void afterTextChanged(Editable s) {
       /* adapter.getFilter().filter(s.toString());
        Log.e("Changed",s.toString());*/
    }
}
