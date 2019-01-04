package in.hiaccounts.hinext.service.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
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
import butterknife.OnClick;
import in.hiaccounts.R;
import in.hiaccounts.hinext.service.adapter.Service_DeliveryList_Adapter;
import in.hiaccounts.hinext.service.model.SelectedItemsList;
import in.hiaccounts.hinext.service.model.service_deliverylist.DeliveryList_Data;
import in.hiaccounts.hinext.service.model.service_invoice.ServiceSaveData;
import in.hiaccounts.hinext.service.model.service_pos.ServicePosCartItem;
import in.hiaccounts.hinext.service.model.service_pos.ServicePosCreator;
import in.hiaccounts.hinext.service.model.service_pos.ServicePosHelper;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;
import static in.hiaccounts.R.string.response_error;

public class Activity_DeliveryList extends AppCompatActivity implements TextWatcher {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edSearch)
    EditText edSearch;
    @BindView(R.id.llSearch)
    LinearLayout llSearch;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.tvFormNo)
    TextView tvFormNo;
    @BindView(R.id.tvCustomerName)
    TextView tvCustomerName;
    @BindView(R.id.tvTotalRecieved)
    TextView tvTotalRecieved;
    @BindView(R.id.lvDeliveryList)
    ListView lvDeliveryList;
    @BindView(R.id.llListview)
    LinearLayout llListview;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;

    private Activity mActivity = this;
    private Dialog dialog;
    private ServiceHandler serviceHandler;
    private Boolean isInternetPresent = false;
    private SharedPreference sharedPreference;
    private String serverUrl,searchItem="";
    private ServicePosCreator servicePosCreator;
    private DeliveryList_Data deliveryListData = new DeliveryList_Data();
    private List<DeliveryList_Data> listData;
    private List<Object> deliveryList = new ArrayList<Object>();
    private Service_DeliveryList_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliverylist);
        ButterKnife.bind(this);
        initComponentView();
    }

    private void initComponentView() {
        ButterKnife.bind(this);
        edSearch.addTextChangedListener(this);
        serverUrl = UtilView.getUrl(mActivity);
        servicePosCreator = ServicePosHelper.getPosCreator();
        toolbar.setTitle(getResources().getString(R.string.menu_deliverylist));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        setSupportActionBar(toolbar);
        serviceHandler = new ServiceHandler(this);
        sharedPreference = SharedPreference.getInstance(this);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getDeliveryListItem("");

        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    searchItem = edSearch.getText().toString().trim();
                    getDeliveryListItem(searchItem);
                }
                return handled;
            }
        });





        lvDeliveryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                DeliveryList_Data invoiceNo = listData.get(position);
                isInternetPresent = serviceHandler.isConnectingToInternet();
                if (serverUrl != null) {
                    if (isInternetPresent) {
                        if (progressBar != null)
                            progressBar.setVisibility(View.VISIBLE);
                        String url = serverUrl + "/service//" + Constant.FUNTION_GETSERVICEDELIVERYPRINTLIST + "?id=" + invoiceNo.getId();
                        GetDataTask getOrderDetail = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                            @Override
                            public void onTaskComplete(String result) {
                                if (result != null) {
                                    if (progressBar != null)
                                        progressBar.setVisibility(View.GONE);
                                    try {
                                        servicePosCreator.clear();
                                        Gson gson = new Gson();
                                        ServiceSaveData orderData = gson.fromJson(result.toString(), ServiceSaveData.class);
                                        if (orderData!=null){

                                            if (orderData.getSelectedItemsList()!=null && orderData.getSelectedItemsList().size()>0){
                                                for (int i=0;i<orderData.getSelectedItemsList().size();i++){
                                                    SelectedItemsList item = orderData.getSelectedItemsList().get(i);
                                                    SelectedItemsList invokeItems = new SelectedItemsList();
                                                    invokeItems.setItemName(item.getItemName());
                                                    invokeItems.setItemId(item.getItemId());
                                                    invokeItems.setQty(item.getQty());
                                                    double d = item.getQty();
                                                    invokeItems.setItemQuantity((new Double(d)).longValue());
                                                    invokeItems.setReturnQty(item.getReturnQty());
                                                    invokeItems.setRemainingQty(item.getRemainingQty());
                                                    invokeItems.setAmtexclusivetax(item.getAmtexclusivetax());
                                                    invokeItems.setTaxid(item.getTaxid());
                                                    invokeItems.setTaxpercent(Double.valueOf(String.valueOf(item.getTaxpercent())));
                                                    invokeItems.setTaxamt(item.getTaxamt());
                                                    invokeItems.setAmtinclusivetax(item.getAmtinclusivetax());
                                                    invokeItems.setDiscountAmt(item.getDiscountAmt());
                                                    invokeItems.setItemDescription(item.getItemDescription());
                                                    invokeItems.setTaxName(item.getTaxName());
                                                    invokeItems.setItemCode(item.getItemCode());
                                                    invokeItems.setCess(item.getCess());

                                                    invokeItems.setCessTaxAmt(item.getCessTaxAmt());
                                                    invokeItems.setHsnCode(item.getHsnCode());
                                                    invokeItems.setUomName(item.getUomName());
                                                    invokeItems.setItemTotalAmount(item.getAmtinclusivetax());
                                                    invokeItems.setUnitPrice(item.getUnitPrice());

                                                    invokeItems.setUom(item.getUom());
                                                    invokeItems.setUomConvertorList(item.getUomConvertorList());
                                                    invokeItems.setPrice(BigDecimal.valueOf(item.getUnitPrice()));
                                                    invokeItems.setInclusiveJSON(item.getInclusiveJSON());
                                                    invokeItems.setSerializableStatus(item.getSerializableStatus());
                                                    invokeItems.setSalesQuotationId(item.getSalesQuotationId());
                                                    invokeItems.setSalesQuotationDetailsId(item.getSalesQuotationDetailsId());
                                                    invokeItems.setSalesOrderDetailsId(item.getSalesOrderDetailsId());
                                                    invokeItems.setSalesOrderId(item.getSalesOrderId());
                                                    servicePosCreator.addItem(new ServicePosCartItem(invokeItems, 1));
                                                }
                                            }
                                            Intent returnIntent = new Intent();
                                            returnIntent.putExtra("serviceDelivaeryId",orderData.getServiceDeliveryId());
                                            mActivity.setResult(Activity.RESULT_OK, returnIntent);
                                            mActivity.finish();



                                        } else {
                                            UtilView.showToast(mActivity, "No Service Invoice Available");
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

    private void getDeliveryListItem(String search) {
        String url = "";
        if (search.equals("")) {
            url = serverUrl + "/service//" + Constant.FUNTION_GETSERVICEDELIVERYLIST;
        }
        if (!search.equals("")) {
            url = serverUrl + "/service//" + Constant.FUNTION_GETSERVICEDELIVERYLIST  + "?itemSearchText=" + search.replace(" ", "%20");
        }

        if (serverUrl!=null) {
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {

                UtilView.showProgessBar(mActivity, progressBar);
                GetDataTask task = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(mActivity, progressBar);
                        if (result != null) {
                            try {
                                Gson gson = new Gson();
                                listData = new ArrayList<DeliveryList_Data>();
                                JSONArray jsonArray = new JSONArray(result.toString());
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject json = jsonArray.getJSONObject(i);

                                    DeliveryList_Data paymentInvoice = gson.fromJson(json.toString(), DeliveryList_Data.class);
                                    listData.add(paymentInvoice);
                                }
                                if (listData.size() > 0) {
                                    llListview.setVisibility(View.VISIBLE);
                                    adapter = new Service_DeliveryList_Adapter(mActivity, listData);
                                    lvDeliveryList.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                } else {
                                    listData.clear();
                                    llListview.setVisibility(View.GONE);
                                    adapter = new Service_DeliveryList_Adapter(mActivity, listData);
                                    lvDeliveryList.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                    UtilView.showErrorDialog("No Invoices Available", mActivity);

                                }

                            }catch (Exception e){
                                UtilView.showErrorDialog(mActivity.getResources().getString(R.string.response_error),mActivity);
                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.error_null), mActivity);
                        }
                    }
                }, false);
                task.execute(url, "");
            }
            if (!isInternetPresent) {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
            }
        }else {
            UtilView.gotToLogin(mActivity);
        }
    }

    @OnClick(R.id.llSearch)
    public void onViewClicked() {

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
