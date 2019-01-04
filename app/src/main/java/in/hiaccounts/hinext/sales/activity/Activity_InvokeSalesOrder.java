package in.hiaccounts.hinext.sales.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.hiaccounts.R;
import in.hiaccounts.hinext.sales.adapter.SalesInvokeOrder_Adapter;
import in.hiaccounts.hinext.sales.model.checkout.SalesSaveData;
import in.hiaccounts.hinext.sales.model.checkout.SelectedItemsList;
import in.hiaccounts.hinext.sales.model.sales_pos.SalesPosCartItem;
import in.hiaccounts.hinext.sales.model.sales_pos.SalesPosCreator;
import in.hiaccounts.hinext.sales.model.sales_pos.SalesPosHelper;
import in.hiaccounts.hinext.sales.model.save_salesorder.SalesInvokeOrderList;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;

import static in.hiaccounts.R.string.response_error;

public class Activity_InvokeSalesOrder extends AppCompatActivity implements TextWatcher {
    private static String TAG = Activity_InvokeSalesOrder.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edSearch)
    EditText edSearch;
    @BindView(R.id.tvFormNo)
    TextView tvFormNo;
    @BindView(R.id.tvCustomerName)
    TextView tvCustomerName;
    @BindView(R.id.tvTotalRecieved)
    TextView tvTotalRecieved;
    @BindView(R.id.lvInvokeSalesOrderList)
    ListView lvInvokeSalesOrderList;
    @BindView(R.id.llListview)
    LinearLayout llListview;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;

    private Activity mActivity = this;
    private ServiceHandler serviceHandler;
    private Boolean isInternetPresent = false;
    private SharedPreference sharedPreference;
    private String serverUrl;
    private SalesPosCreator salesposCreator;
    List<SalesInvokeOrderList> salesInvokeOrderList ;
    SalesInvokeOrderList salesInvokeOrder = new SalesInvokeOrderList();
    SalesInvokeOrder_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoke_sales_order);
        ButterKnife.bind(this);
        initComponentView();
    }

    private void initComponentView() {
        ButterKnife.bind(this);
        edSearch.addTextChangedListener(this);
        serverUrl = UtilView.getUrl(mActivity);
        salesposCreator = SalesPosHelper.getPosCreator();
        toolbar.setTitle(getResources().getString(R.string.menu_invokesalesorder));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        serviceHandler = new ServiceHandler(this);
        sharedPreference = SharedPreference.getInstance(this);

        getInvokeSalesOrderList();

        lvInvokeSalesOrderList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                SalesInvokeOrderList salesOrderNo = salesInvokeOrderList.get(position);
                isInternetPresent = serviceHandler.isConnectingToInternet();
                if (serverUrl != null) {
                    if (isInternetPresent) {
                        if (progressBar != null)
                            progressBar.setVisibility(View.VISIBLE);
                        String url = serverUrl + "/retail//" + Constant.FUNTION_GETSALESSELECTINVOKEORDER + "?invoiceNo=" + salesOrderNo.getFormNo();
                        GetDataTask getOrderDetail = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                            @Override
                            public void onTaskComplete(String result) {
                                if (result != null) {
                                    if (progressBar != null)
                                        progressBar.setVisibility(View.GONE);
                                    try {
                                        salesposCreator.clear();
                                        Gson gson = new Gson();

                                        SalesSaveData orderData = gson.fromJson(result.toString(), SalesSaveData.class);
                                        if (orderData!=null){

                                            if (orderData.getSelectedItemsList()!=null && orderData.getSelectedItemsList().size()>0){
                                                for (int i=0;i<orderData.getSelectedItemsList().size();i++){
                                                    SelectedItemsList item = orderData.getSelectedItemsList().get(i);
                                                    SelectedItemsList invokeItems = new SelectedItemsList();
                                                    invokeItems.setItemName(item.getItemName());
                                                    invokeItems.setItemId(item.getItemId());
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
                                                    invokeItems.setUnitPrice(item.getUnitPrice());
                                                    invokeItems.setUnitPriceIn(item.getUnitPrice());
                                                    invokeItems.setUom(item.getUom());
                                                    invokeItems.setUomConvertorList(item.getUomConvertorList());
                                                    invokeItems.setPrice(BigDecimal.valueOf(item.getUnitPrice()));
                                                    invokeItems.setInclusiveJSON(item.getInclusiveJSON());
                                                    invokeItems.setSerializableStatus(item.getSerializableStatus());
                                                    invokeItems.setSalesQuotationId(item.getSalesQuotationId());
                                                    invokeItems.setSalesQuotationDetailsId(item.getSalesQuotationDetailsId());
                                                    invokeItems.setSalesOrderDetailsId(item.getSalesOrderDetailsId());
                                                    invokeItems.setSalesOrderId(item.getSalesOrderId());
                                                    salesposCreator.addItem(new SalesPosCartItem(invokeItems, 1));
                                                }
                                            }
                                            Intent returnIntent = new Intent();
                                            returnIntent.putExtra("invokesalesOrder",orderData.getSalesOrderId());
                                            mActivity.setResult(Activity.RESULT_OK, returnIntent);
                                            mActivity.finish();



                                        } else {
                                            UtilView.showToast(mActivity, "No Sales Invoice Order Available");
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
        });
    }

    private void getInvokeSalesOrderList() {
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
                UtilView.showProgessBar(mActivity, progressBar);
                String url = serverUrl + "/retail//" + Constant.FUNTION_GETSALESINVOKEORDER + "/?itemSearchText=&locationId=";
                GetDataTask getPurInvoiceList = new GetDataTask(this, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(mActivity, progressBar);
                        if (result != null) {
                            try {
                                salesInvokeOrderList = new ArrayList<SalesInvokeOrderList>();
                                JSONArray jsonArray = new JSONArray(result.toString());
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject json = jsonArray.getJSONObject(i);
                                    Gson gson = new Gson();
                                    SalesInvokeOrderList paymentInvoice = gson.fromJson(json.toString(), SalesInvokeOrderList.class);
                                    salesInvokeOrderList.add(paymentInvoice);
                                }
                                if (salesInvokeOrderList.size() > 0) {
                                    llListview.setVisibility(View.VISIBLE);
                                    adapter = new SalesInvokeOrder_Adapter(mActivity, salesInvokeOrderList);
                                    lvInvokeSalesOrderList.setAdapter(adapter);
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
                getPurInvoiceList.execute(url, "");
            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), this);

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

    }


}
