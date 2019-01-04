package in.hiaccounts.hinext.sales.activity;

import android.app.Activity;
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
import android.widget.ImageView;
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
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.sales.adapter.SalesReturnItemListAdpater;
import in.hiaccounts.hinext.sales.model.checkout.SalesSaveData;
import in.hiaccounts.hinext.sales.model.checkout.SelectedItemsList;
import in.hiaccounts.hinext.sales.model.sales_pos.SalesPosCartItem;
import in.hiaccounts.hinext.sales.model.sales_pos.SalesPosCreator;
import in.hiaccounts.hinext.sales.model.sales_pos.SalesPosHelper;
import in.hiaccounts.hinext.sales.model.sales_returnitem.Sales_ReturnItemData;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;

import static in.hiaccounts.R.string.response_error;

public class Activity_SalesReturnItemList extends AppCompatActivity implements TextWatcher {
    private static String TAG = Activity_SalesReturnItemList.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edSearch)
    EditText edSearch;
    @BindView(R.id.imgviewSearch)
    ImageView imgviewSearch;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.tvFormNo)
    TextView tvFormNo;
    @BindView(R.id.tvSupplierName)
    TextView tvSupplierName;
    @BindView(R.id.tvTotalRecieved)
    TextView tvTotalRecieved;
    @BindView(R.id.lvSalesReturnItemList)
    ListView lvSalesReturnItemList;
    @BindView(R.id.llListview)
    LinearLayout llListview;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;

    private Activity mActivity = this;
    private ServiceHandler serviceHandler;
    private Boolean isInternetPresent = false;
    private SharedPreference sharedPreference;
    private String serverUrl,returnItemSearch="";
    SalesPosCreator salesPosCreator;

    private List<Sales_ReturnItemData> listData;
    private Sales_ReturnItemData returnItemData = new Sales_ReturnItemData();
    private SalesReturnItemListAdpater adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_return_item_list);
        ButterKnife.bind(this);
        initComponentView();
    }

    private void initComponentView() {
        ButterKnife.bind(this);
        mActivity = this;
        edSearch.addTextChangedListener(this);
        serverUrl = UtilView.getUrl(mActivity);
        serviceHandler = new ServiceHandler(mActivity);
        salesPosCreator = SalesPosHelper.getPosCreator();
        toolbar.setTitle(getResources().getString(R.string.menu_salesreturnitem));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getSalesReturnitemList("");

        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    returnItemSearch = edSearch.getText().toString().trim();
                    getSalesReturnitemList(returnItemSearch);
                }
                return handled;
            }
        });

        lvSalesReturnItemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                Sales_ReturnItemData salesReturnItemData = listData.get(position);
                isInternetPresent = serviceHandler.isConnectingToInternet();
                if (isInternetPresent) {
                    UtilView.showProgessBar(mActivity, progressBar);

                    String url = serverUrl + "/retail//" + Constant.FUNTION_GETSALESINVOICE_RETURN+"?invoiceNo="+salesReturnItemData.getFormNo();

                    if (serverUrl != null) {
                        isInternetPresent = serviceHandler.isConnectingToInternet();
                        if (isInternetPresent) {
                            UtilView.showProgessBar(mActivity, progressBar);
                            GetDataTask getDataTask = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
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


                                                            salesPosCreator.addItem(new SalesPosCartItem(invokeItems, 1));
                                                        }
                                                    }
                                                    Intent returnIntent = new Intent();
                                                   /* returnIntent.putExtra("siid",orderData.getSiid());
                                                    returnIntent.putExtra("srlnNo",orderData.getSrlnNo());*/

                                                    returnIntent.putExtra("ReturnItem",Constant.SALESRETURNITEM);
                                                    returnIntent.putExtra("returnItemData",orderData);
                                                    mActivity.setResult(Activity.RESULT_OK, returnIntent);
                                                    mActivity.finish();



                                                } else {
                                                    UtilView.showToast(mActivity, "No Sales Invoice Available");
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
                            getDataTask.execute(url, "");

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

    private void getSalesReturnitemList(String search) {
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
                if (progressBar != null)
                    progressBar.setVisibility(View.VISIBLE);

                String url ="";
                if (search.equals("")) {
                    url = serverUrl + "/retail//" + Constant.FUNTION_GETINVOICES+"?itemSearchText=&type=Return&locationId=";

                }

                if (!search.equals("")) {
                    url = serverUrl + "/retail//" + Constant.FUNTION_GETINVOICES+"?itemSearchText="+ search.replace(" ", "%20")+"&type=Return&locationId=";

                }

                GetDataTask getOrderList = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        if (progressBar != null)
                            progressBar.setVisibility(View.GONE);
                        if (result != null) {
                            Gson gson = new Gson();
                            listData = new ArrayList<Sales_ReturnItemData>();
                            try {
                                JSONArray jsonArray = new JSONArray(result.toString());
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonInovice = jsonArray.getJSONObject(i);
                                    Sales_ReturnItemData inovice = gson.fromJson(jsonInovice.toString(), Sales_ReturnItemData.class);
                                    listData.add(inovice);
                                }
                                if (listData != null && listData.size() > 0) {

                                    llListview.setVisibility(View.VISIBLE);
                                    adapter = new SalesReturnItemListAdpater(mActivity, listData);
                                    lvSalesReturnItemList.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                } else {
                                    llListview.setVisibility(View.GONE);
                                    adapter = new SalesReturnItemListAdpater(mActivity, listData);
                                    lvSalesReturnItemList.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                    UtilView.showSuccessDialog("No Sales Return Item Available", mActivity);
                                }
                            } catch (Exception ex) {
                                UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                        }
                    }

                }, false);
                getOrderList.execute(url, "");
            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
            }
        } else {
            UtilView.gotToLogin(mActivity);
        }

    }

    @OnClick(R.id.imgviewSearch)
    public void onViewClicked() {
        returnItemSearch = edSearch.getText().toString().trim();
        getSalesReturnitemList(returnItemSearch);
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
            UtilView.hideSoftKeyboard(mActivity, edSearch);
            returnItemSearch = "";
            getSalesReturnitemList(returnItemSearch);
        }
    }
}
