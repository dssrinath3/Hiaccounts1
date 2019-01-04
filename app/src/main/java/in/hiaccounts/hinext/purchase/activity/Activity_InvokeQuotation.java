package in.hiaccounts.hinext.purchase.activity;

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
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.purchase.adapter.Purchase_QuotationAdapater;
import in.hiaccounts.hinext.purchase.model.purchase_pos.PurchasePosCartItem;
import in.hiaccounts.hinext.purchase.model.purchase_pos.PurchasePosCreator;
import in.hiaccounts.hinext.purchase.model.purchase_pos.PurchasePosHelper;
import in.hiaccounts.hinext.purchase.model.purchase_pos.SelectedItemsList;
import in.hiaccounts.hinext.purchase.model.purchase_purchaseorder.PurchaseInvokeOrderData;
import in.hiaccounts.hinext.purchase.model.purchase_purchaseorder.PurchaseOrderData;
import in.hiaccounts.hinext.purchase.model.purchase_quotation.Purchase_QuotationListData;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;

public class Activity_InvokeQuotation extends AppCompatActivity implements TextWatcher {
    private static String TAG = Activity_InvokeQuotation.class.getSimpleName();
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
    @BindView(R.id.lvInvokeQuotationList)
    ListView lvInvokeQuotationList;
    @BindView(R.id.llListview)
    LinearLayout llListview;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;

    private Activity mActivity = this;
    private Dialog dialog;
    private ServiceHandler serviceHandler;
    private Boolean isInternetPresent = false;
    private SharedPreference sharedPreference;
    private String serverUrl;

    List<Purchase_QuotationListData> purchaseQuotationListData;
    Purchase_QuotationAdapater purchaseQuotationAdapater;
    private PurchasePosCreator purchasePosCreator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoke_quotation);
        ButterKnife.bind(this);
        initComponentVIew();
    }

    private void initComponentVIew() {
        ButterKnife.bind(this);
        edSearch.addTextChangedListener(this);
        serverUrl = UtilView.getUrl(mActivity);
        purchasePosCreator = PurchasePosHelper.getPosCreator();
        toolbar.setTitle(getResources().getString(R.string.purchaseInvokeQuotationList));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        dialog = new com.rey.material.app.Dialog(this);
        serviceHandler = new ServiceHandler(this);
        sharedPreference = SharedPreference.getInstance(this);

        getQuotationListData();

        lvInvokeQuotationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                Purchase_QuotationListData purchaseOrderNo = purchaseQuotationListData.get(position);
                isInternetPresent = serviceHandler.isConnectingToInternet();
                if (isInternetPresent) {
                    UtilView.showProgessBar(mActivity, progressBar);
                    PurchaseOrderData purchaseOrderData = new PurchaseOrderData();
                    purchaseOrderData.setPurchaseQuotationId(purchaseOrderNo.getId());

                    String url = serverUrl + "/purchase//0/" + Constant.FUNTION_GETINVOKEQUOTATIONlIST+"/"+purchaseOrderNo.getFormNo();

                    if (serverUrl != null) {
                        isInternetPresent = serviceHandler.isConnectingToInternet();
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


                                            purchasePosCreator.clear();
                                            Gson gson = new Gson();
                                            try {
                                                JSONObject jsonObject = new JSONObject(result.toString());
                                                PurchaseOrderData orderData = gson.fromJson(jsonObject.toString(), PurchaseOrderData.class);

                                                if (orderData!=null){
                                                    if (orderData.getSelectedItemsList()!=null && orderData.getSelectedItemsList().size()>0){
                                                        for (int i=0;i<orderData.getSelectedItemsList().size();i++){
                                                            PurchaseInvokeOrderData item=orderData.getSelectedItemsList().get(i);
                                                            SelectedItemsList invokeItems = new SelectedItemsList();
                                                            invokeItems.setItemName(item.getItemName());
                                                            invokeItems.setItemId(item.getItemId());
                                                            invokeItems.setUnitPrice(item.getUnitPrice());
                                                            invokeItems.setQty("" + item.getQty());
                                                            invokeItems.setItemQuantity(item.getQty());
                                                            invokeItems.setReturnQty(item.getReturnQty());
                                                            invokeItems.setRemainingQty("" + item.getRemainingQty());
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
                                                            invokeItems.setPrice(BigDecimal.valueOf(item.getUnitPrice()));
                                                            invokeItems.setUom(item.getUom());
                                                            invokeItems.setUomConvertorList(item.getUomConvertorList());
                                                            invokeItems.setItemDescription(item.getItemDescription());
                                                            invokeItems.setTaxName(item.getTaxName());
                                                            invokeItems.setPrice(BigDecimal.valueOf(item.getUnitPrice()));
                                                            invokeItems.setPurchaseOrderId(item.getPurchaseOrderDetailsId());
                                                            invokeItems.setPurchaseQuotationId(item.getPurchaseQuotationId());
                                                            invokeItems.setReceiveItemId(item.getReceiveItemId());

                                                            purchasePosCreator.addItem(new PurchasePosCartItem(invokeItems, 1), invokeItems.getItemId());
                                                        }
                                                    }

                                                    Intent returnIntent = new Intent();
                                                    mActivity.setResult(Activity.RESULT_OK, returnIntent);
                                                    mActivity.finish();

                                                } else {
                                                    UtilView.showToast(mActivity, "No Items for the selected quotation Number");
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
                            postDataTask.execute(new Gson().toJson(purchaseOrderData), url, "");

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



    public void getQuotationListData() {
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
                UtilView.showProgessBar(mActivity, progressBar);
                String url = serverUrl + "/purchase//0/" + Constant.FUNTION_GETPURCHASEINVOKEQUOTATION+"/?itemSearchText=&type=Invoke&locationId=";
                GetDataTask getPurInvoiceList = new GetDataTask(this, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(mActivity, progressBar);
                        if (result != null) {
                            try {
                                purchaseQuotationListData = new ArrayList<Purchase_QuotationListData>();
                                JSONArray jsonArray = new JSONArray(result.toString());
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject json = jsonArray.getJSONObject(i);
                                    Gson gson = new Gson();
                                    Purchase_QuotationListData paymentInvoice = gson.fromJson(json.toString(), Purchase_QuotationListData.class);
                                    purchaseQuotationListData.add(paymentInvoice);
                                }
                                if (purchaseQuotationListData.size() > 0) {
                                    llListview.setVisibility(View.VISIBLE);
                                    purchaseQuotationAdapater = new Purchase_QuotationAdapater(mActivity, purchaseQuotationListData);
                                    lvInvokeQuotationList.setAdapter(purchaseQuotationAdapater);
                                    purchaseQuotationAdapater.notifyDataSetChanged();
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
