package in.hiaccounts.hinext.sales.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

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
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.application.activity.NavigationDrawer_Activity;
import in.hiaccounts.hinext.checkout.activity.Activity_Checkout;
import in.hiaccounts.hinext.customer.model.Customer;
import in.hiaccounts.hinext.purchase.model.purchase_payments.PurchaseAdvancePayment;
import in.hiaccounts.hinext.sales.adapter.SalesAdvancePayment_Adapter;
import in.hiaccounts.hinext.sales.adapter.SalesShowStockItems_Adapter;
import in.hiaccounts.hinext.sales.model.advance_payment.Sales_AdvancePayment;
import in.hiaccounts.hinext.sales.model.advanceandremainingsales_retrun.Advance_Remaining_SalesReturn;
import in.hiaccounts.hinext.sales.model.checkout.SelectedItemsList;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.NonScrollListView;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;

import static in.hiaccounts.R.string.response_error;
import static in.hiaccounts.utility.Constant.RESQUSTCODE_CHECKOUT;

/**
 * Created by Prateek on 7/22/2017.
 */

public class Activity_SalesAdvancePayment extends AppCompatActivity implements TextWatcher {
    private static String TAG = Activity_SalesAdvancePayment.class.getSimpleName();
    @BindView(R.id.edSearch)
    EditText edSearch;
    @BindView(R.id.lvPaymentList)
    ListView lvPaymentList;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.imgviewSearch)
    ImageView imgviewSearch;

    @BindView(R.id.llContent)
    LinearLayout llContent;


    private Activity mActivity = this;
    private Customer selected_customer = null;
    private Dialog dialog;
    private ServiceHandler serviceHandler;
    private Boolean isInternetPresent = false;
    private SharedPreference sharedPreference;
    private List<PurchaseAdvancePayment> paymentInvoiceList;
    private Gson gson = new Gson();
    private String selectedTaxType = "", selectedDate = "", serverUrl;
    SalesAdvancePayment_Adapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salesadvancepaymentlist);
        ButterKnife.bind(this);
        initComponentVIew();
    }

    private void initComponentVIew() {
        ButterKnife.bind(this);
        serverUrl = UtilView.getUrl(mActivity);
        selected_customer = (Customer) getIntent().getSerializableExtra("customer");
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

        edSearch.addTextChangedListener(this);
        /*LayoutInflater inflater = this.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.adapter_sales_advancepaymentlist, lvPaymentList, false);
        header.findViewById(R.id.tvFormNo);
        header.findViewById(R.id.tvCustomerName);
        header.findViewById(R.id.tvRemainingAmount);
        header.findViewById(R.id.tvTotalRecieved);

        UtilView.setTextAppearanceSmall(this, (TextView) header.findViewById(R.id.tvFormNo));
        UtilView.setTextAppearanceSmall(this, (TextView) header.findViewById(R.id.tvCustomerName));
        UtilView.setTextAppearanceSmall(this, (TextView) header.findViewById(R.id.tvRemainingAmount));
        UtilView.setTextAppearanceSmall(this, (TextView) header.findViewById(R.id.tvTotalRecieved));

        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), (TextView) header.findViewById(R.id.tvFormNo));
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), (TextView) header.findViewById(R.id.tvCustomerName));
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), (TextView) header.findViewById(R.id.tvRemainingAmount));
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), (TextView) header.findViewById(R.id.tvTotalRecieved));

        UtilView.setTexttypeFace(null, Typeface.BOLD, (TextView) header.findViewById(R.id.tvFormNo));
        UtilView.setTexttypeFace(null, Typeface.BOLD, (TextView) header.findViewById(R.id.tvCustomerName));
        UtilView.setTexttypeFace(null, Typeface.BOLD, (TextView) header.findViewById(R.id.tvRemainingAmount));
        UtilView.setTexttypeFace(null, Typeface.BOLD, (TextView) header.findViewById(R.id.tvTotalRecieved));
        if (lvPaymentList != null)
            lvPaymentList.addHeaderView(header);*/
        getAdvancePaymentList();

        lvPaymentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                if (view.getId() == R.id.tvCreateInvoice) {
                    if (paymentInvoiceList!=null && paymentInvoiceList.size()>0){
                        PurchaseAdvancePayment purchaseAdvancePayment =   paymentInvoiceList.get(position);
                        Log.e("purchaseAdvancePayment",purchaseAdvancePayment.getFormNo());
                        validateCheckoutBasedOnInvoice(purchaseAdvancePayment,position);
                    }




                }
                if (view.getId() == R.id.imageviewReturn) {
                    initReturnDialog(position);
                }
                if (view.getId() == R.id.tvRemainingPayment) {
                    if (selected_customer != null) {
                        Intent intent = new Intent(mActivity, Activity_Checkout.class);
                        intent.putExtra("selectedTax", selectedTaxType);
                        intent.putExtra("selectedDate", selectedDate);
                        intent.putExtra("paymentInvoice", paymentInvoiceList.get(position));
                        intent.putExtra("customer", selected_customer);
                        intent.putExtra("checkoutType", "salesAdvance");
                        intent.putExtra("callingfrom", Constant.NAVIGATION_GROUP_RETAIL);
                        startActivityForResult(intent, Constant.RESQUSTCODE_CHECKOUT);
                    } else {
                        UtilView.showToast(mActivity, getResources().getString(R.string.selectCustomerError));
                    }
                }
            }

        });
    }

    private void callCreateInvoice(int position) {
        if (serverUrl != null) {
            if (isInternetPresent) {
                UtilView.showProgessBar(mActivity, progressBar);
                String url = serverUrl + "/retail//" + Constant.FUNTION_CREATEADVANCEPOSINVOICES + "?formNo=" + paymentInvoiceList.get(position).getFormNo();
                GetDataTask getInvoicetask = new GetDataTask(Activity_SalesAdvancePayment.this, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(mActivity, progressBar);
                        if (result != null) {
                            if (result.equals("invalid")) {
                                UtilView.showToast(mActivity, getResources().getString(R.string.accesstoken_error));
                                Intent intent = new Intent(mActivity, Activity_Login.class);
                                startActivity(intent);
                                finish();
                            } else {
                                try {
                                    if (result.equals("success")) {
                                        paymentInvoiceList.remove(position);
                                        UtilView.showToast(mActivity, getResources().getString(R.string.createInvoiceSuceessfully));
                                        adapter = new SalesAdvancePayment_Adapter(mActivity, paymentInvoiceList);
                                        lvPaymentList.setAdapter(adapter);
                                        adapter.notifyDataSetChanged();

                                    }
                                } catch (Exception e) {

                                    UtilView.showErrorDialog(getResources().getString(R.string.response_error), mActivity);
                                }
                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.response_error), mActivity);
                        }
                    }
                }, false);
                getInvoicetask.execute(url, "");
            } else {
                UtilView.showToast(Activity_SalesAdvancePayment.this, getResources().getString(R.string.intertnet_status));
            }
        } else {
            UtilView.gotToLogin(mActivity);
        }
    }

    private void validateCheckoutBasedOnInvoice(PurchaseAdvancePayment purchaseAdvancePayment, int position) {


        Sales_AdvancePayment saveData = new Sales_AdvancePayment();
        saveData.setTaxType("CGST:SGST/UGST");
        saveData.setCustomerId(2L);
        saveData.setCutomerName(purchaseAdvancePayment.getCustomerName());
        saveData.setTypeDoc("SO");
        saveData.setDateOfInvoice("2018-09-28T06:13:35.078Z");
        saveData.setCmpyLocation(2L);
        saveData.setCustLocation(18L);
        saveData.setExchangeRateIdOfInvoice(1L);
        saveData.setExchangerateValue("1");
        saveData.setCurrencyIdOfInvoice(1L);
        saveData.setFromReg("com1234");
        saveData.setToReg("com5678");

            String invokeOrderId = "";
            String url = serverUrl + "/retail//" + Constant.FUNTION_VALIDATECHECKOUT + "?invokeOrderId=" + invokeOrderId;
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (serverUrl != null) {
                if (isInternetPresent) {
                    UtilView.showProgessBar(mActivity, progressBar);
                    PostDataTask postDataTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                        @Override
                        public void onTaskComplete(String result) {
                            UtilView.hideProgessBar(mActivity, progressBar);
                            if (result != null) {
                                Log.e("validatedata",result.toString());

                                callCreateInvoice(position);
                            } else {
                                UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                            }
                        }


                    }, false);
                    postDataTask.execute(new Gson().toJson(saveData), url, "");
                } else {
                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                }
            } else {
                UtilView.gotToLogin(mActivity);
            }

    }
    private void getAdvancePaymentList() {
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
                UtilView.showProgessBar(mActivity, progressBar);
                String url = serverUrl + "/retail//" + Constant.FUNTION_GETADVANCEPAYMENTINVOICES;
                GetDataTask getOrderList = new GetDataTask(this, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(mActivity, progressBar);
                        if (result != null) {
                            if (result.equals("invalid")) {
                                UtilView.showToast(Activity_SalesAdvancePayment.this, getResources().getString(R.string.accesstoken_error));
                                Intent intent = new Intent(Activity_SalesAdvancePayment.this, Activity_Login.class);
                                startActivity(intent);
                                finish();
                            } else {
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
                                        llContent.setVisibility(View.VISIBLE);
                                         adapter = new SalesAdvancePayment_Adapter(Activity_SalesAdvancePayment.this, paymentInvoiceList);
                                        lvPaymentList.setAdapter(adapter);
                                      //  adapter.notifyDataSetChanged();

                                    } else {
                                        llContent.setVisibility(View.GONE);
                                        UtilView.showErrorDialog("No Invoices", Activity_SalesAdvancePayment.this);

                                    }
                                } catch (Exception e) {

                                    UtilView.showErrorDialog(getResources().getString(R.string.response_error), mActivity);
                                }
                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.response_error), Activity_SalesAdvancePayment.this);
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


    private void initReturnDialog(final int position) {
        dialog.setContentView(R.layout.dialog_sales_returncheckout);
        LinearLayout ll_cash = dialog.findViewById(R.id.ll_cash);
        LinearLayout ll_cheque = dialog.findViewById(R.id.ll_cheque);
        Button btnClose = dialog.findViewById(R.id.btn_close);
        ImageView imageview_close = dialog.findViewById(R.id.imageview_close);
        TextView btn_cash = dialog.findViewById(R.id.btn_cash);
        Typeface font = Typeface.createFromAsset(this.getAssets(), "fonts/fontawesome-webfont.ttf");
        btn_cash.setTypeface(font);
        if (dialog != null)
            dialog.show();
        ll_cheque.setVisibility(View.GONE);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog != null) dialog.dismiss();
            }
        });

        imageview_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog != null) dialog.dismiss();
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
        final ProgressView progressView = dialog.findViewById(R.id.progress_bar);
        ImageView imageview_close = dialog.findViewById(R.id.imageview_close);
        EditText ed_return_totalamount = dialog.findViewById(R.id.ed_return_totalamount);
        Button btn_returnamt = dialog.findViewById(R.id.btn_returnamt);
        Button btn_close = dialog.findViewById(R.id.btn_close);

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
                    if (serverUrl != null) {
                        if (isInternetPresent) {
                            if (progressView != null)
                                progressView.setVisibility(View.VISIBLE);
                            String url = serverUrl + "/retail//" + Constant.FUNTION_RETURNADVANCEPAYMENT;
                            String post_json = gson.toJson(returnItemData);
                            PostDataTask postDataTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                                @Override
                                public void onTaskComplete(String result) {
                                    if (progressView != null)
                                        progressView.setVisibility(View.GONE);
                                    if (result != null) {
                                        try {
                                            if (result.equals("success\n")) {
                                                paymentInvoiceList.remove(position);
                                                UtilView.showToast(mActivity, getResources().getString(R.string.amoutReturnedSuccessfylly));
                                                 adapter = new SalesAdvancePayment_Adapter(mActivity, paymentInvoiceList);
                                                if (lvPaymentList != null)
                                                    lvPaymentList.setAdapter(adapter);
                                              //  adapter.notifyDataSetChanged();
                                                if (dialog != null && dialog.isShowing()) {
                                                    dialog.dismiss();
                                                }
                                            } else {
                                                UtilView.showToast(mActivity, "Return Unsuccessfully. Please try again.");
                                            }
                                        } catch (Exception e) {

                                            UtilView.showErrorDialog(getResources().getString(R.string.response_error), mActivity);
                                        }
                                    } else {
                                        UtilView.showErrorDialog(getResources().getString(R.string.response_error), mActivity);
                                    }
                                }
                            }, false);
                            postDataTask.execute(post_json, url, "");
                        } else {
                            UtilView.showToast(mActivity, getResources().getString(R.string.intertnet_status));
                        }
                    } else {
                        UtilView.gotToLogin(mActivity);
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
        if (dialog != null)
            dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.RESQUSTCODE_CHECKOUT && resultCode == RESULT_OK) {
            getAdvancePaymentList();
        }
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

      /*  //setFilterDatatoList(s);
        if (s != null && !s.equals("")) {
            adapter.getFilter().filter(s.toString());
        } else {
            getAdvancePaymentList();
        }*/

     //   setFilterDatatoList(s.toString());


        if (s.toString() !=null && !s.toString().equals("")) {
            adapter.getFilter().filter(s.toString());

        } else {
            // Do something when there's no input
            getAdvancePaymentList();
        }

    }

    @Override
    public void afterTextChanged(Editable s) {

    }



   /* public void setFilterDatatoList(String filterDatatoList) {

        ArrayList<SalesAdvancePayment> filteredContacs = new ArrayList<SalesAdvancePayment>();

        for (SalesAdvancePayment c : paymentInvoiceList) {
            if (c instanceof SalesAdvancePayment){
                SalesAdvancePayment nonConnectedHoopContact= (SalesAdvancePayment) c;
                if (nonConnectedHoopContact.getCustomerName().toUpperCase().contains(filterDatatoList.toString().toUpperCase())){

                    filteredContacs.add(c);
                }
            }
        }

        if (filteredContacs.size()>0) {
            UtilView.showLogCat(TAG, "Filter Size " + filteredContacs.size());

            adapter = new SalesAdvancePayment_Adapter(Activity_SalesAdvancePayment.this, filteredContacs);
            lvPaymentList.invalidate();
            lvPaymentList.setAdapter(adapter);

            //adapter.notifyDataSetChanged();
        }*//*else {
            llContent.setVisibility(View.VISIBLE);
            adapter = new SalesAdvancePayment_Adapter(Activity_SalesAdvancePayment.this, paymentInvoiceList);
            lvPaymentList.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }*//*


    }*/
}

