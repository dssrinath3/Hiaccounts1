package in.hiaccounts.hinext.sales.activity;

import android.app.Activity;
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
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.sales.adapter.Sales_DeliveryOrderAdapter;
import in.hiaccounts.hinext.sales.model.checkout.SalesSaveData;
import in.hiaccounts.hinext.sales.model.checkout.SelectedItemsList;
import in.hiaccounts.hinext.sales.model.sales_delivery_order.Sales_DeliveryOrderListData;
import in.hiaccounts.hinext.sales.model.sales_pos.SalesPosCartItem;
import in.hiaccounts.hinext.sales.model.sales_pos.SalesPosCreator;
import in.hiaccounts.hinext.sales.model.sales_pos.SalesPosHelper;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;

public class Activity_InvokeDeliveryOrder extends AppCompatActivity implements TextWatcher {
    private static String TAG = Activity_InvokeDeliveryOrder.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edSearch)
    EditText edSearch;
    @BindView(R.id.tvFormNo)
    TextView tvFormNo;
    @BindView(R.id.tvSupplierName)
    TextView tvSupplierName;
    @BindView(R.id.tvTotalRecieved)
    TextView tvTotalRecieved;
    @BindView(R.id.lvInvokeDeliveryList)
    ListView lvInvokeDeliveryList;
    @BindView(R.id.llListview)
    LinearLayout llListview;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;


    private Activity mActivity = this;
    private ServiceHandler serviceHandler;
    private Boolean isInternetPresent = false;
    private SharedPreference sharedPreference;
    private String serverUrl;
    SalesPosCreator salesPosCreator;
    List<Sales_DeliveryOrderListData> listData ;
    Sales_DeliveryOrderAdapter salesDeliveryAdapater;
    Sales_DeliveryOrderListData salesQuotData = new Sales_DeliveryOrderListData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoke_delivery_order);
        ButterKnife.bind(this);
        initComponentView();
    }

    private void initComponentView() {
        ButterKnife.bind(this);
        edSearch.addTextChangedListener(this);
        serverUrl = UtilView.getUrl(mActivity);
        serviceHandler = new ServiceHandler(this);
        salesPosCreator = SalesPosHelper.getPosCreator();
        toolbar.setTitle(getResources().getString(R.string.salesInvokeDeliveryOrder));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getDeliveryOrderListData();

        lvInvokeDeliveryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                Sales_DeliveryOrderListData salesListData = listData.get(position);
                isInternetPresent = serviceHandler.isConnectingToInternet();
                if (isInternetPresent) {
                    UtilView.showProgessBar(mActivity, progressBar);

                    String url = serverUrl + "/retail//" + Constant.FUNTION_GETSALESSELECTRETURNDELIVERYORDER+"?salesDeliveryOrderNO="+salesListData.getFormNo()+"&type=Invoke";

                    if (serverUrl != null) {
                        if (isInternetPresent) {
                            UtilView.showProgessBar(mActivity, progressBar);
                            PostDataTask postDataTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
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
                                                            invokeItems.setUomConvertorList(item.getUomConvertorList());
                                                            invokeItems.setPrice(BigDecimal.valueOf(item.getUnitPrice()));
                                                            invokeItems.setInclusiveJSON(item.getInclusiveJSON());
                                                            invokeItems.setSerializableStatus(item.getSerializableStatus());
                                                            invokeItems.setSalesQuotationId(item.getSalesQuotationId());
                                                            invokeItems.setSalesQuotationDetailsId(item.getSalesQuotationDetailsId());
                                                            invokeItems.setSalesOrderDetailsId(item.getSalesOrderDetailsId());
                                                            invokeItems.setSalesOrderId(item.getSalesOrderId());
                                                            invokeItems.setSalesDeliverOrderDetailsId(item.getSalesDeliverOrderDetailsId());
                                                            invokeItems.setSalesDeliverOrderId(item.getSalesDeliverOrderId());
                                                            salesPosCreator.addItem(new SalesPosCartItem(invokeItems, 1));
                                                        }
                                                    }
                                                    Intent returnIntent = new Intent();
                                                    mActivity.setResult(Activity.RESULT_OK, returnIntent);
                                                    mActivity.finish();



                                                } else {
                                                    UtilView.showToast(mActivity, "No Sales Delivery Order Available");
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
                            postDataTask.execute(new Gson().toJson(salesListData), url, "");

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
        });
    }

    private void getDeliveryOrderListData() {
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
                UtilView.showProgessBar(mActivity, progressBar);
                String url = serverUrl + "/retail//" + Constant.FUNTION_GETSALESINVOKEDELIVERYORDER+"/?itemSearchText=&orderType=Normal&locationId=";
                GetDataTask getInvoiceList = new GetDataTask(this, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(mActivity, progressBar);
                        if (result != null) {
                            try {
                                listData = new ArrayList<Sales_DeliveryOrderListData>();
                                JSONArray jsonArray = new JSONArray(result.toString());
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject json = jsonArray.getJSONObject(i);
                                    Gson gson = new Gson();
                                    Sales_DeliveryOrderListData paymentInvoice = gson.fromJson(json.toString(), Sales_DeliveryOrderListData.class);
                                    listData.add(paymentInvoice);
                                }
                                if (listData.size() > 0) {
                                    llListview.setVisibility(View.VISIBLE);
                                    salesDeliveryAdapater = new Sales_DeliveryOrderAdapter(mActivity, listData);
                                    lvInvokeDeliveryList.setAdapter(salesDeliveryAdapater);
                                    salesDeliveryAdapater.notifyDataSetChanged();
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
                getInvoiceList.execute(url, "");
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
