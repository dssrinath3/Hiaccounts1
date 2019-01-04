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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hiaccounts.R;
import in.hiaccounts.hinext.purchase.adapter.PurchaseReturn_Adapter;
import in.hiaccounts.hinext.purchase.model.purchase_invoicereturn.PurchaseInvoiceReturnData;
import in.hiaccounts.hinext.purchase.model.purchase_pos.PurchasePosCartItem;
import in.hiaccounts.hinext.purchase.model.purchase_pos.PurchasePosCreator;
import in.hiaccounts.hinext.purchase.model.purchase_pos.PurchasePosHelper;
import in.hiaccounts.hinext.purchase.model.purchase_pos.SelectedItemsList;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;

import static in.hiaccounts.R.string.response_error;

public class Activity_PurchaseInvoice_ReturnItem extends AppCompatActivity implements TextWatcher {


    public static String TAG = Activity_PurchaseInvoice_ReturnItem.class.getSimpleName();
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
    @BindView(R.id.lvPIReturnList)
    ListView lvPIReturnList;
    @BindView(R.id.llListview)
    LinearLayout llListview;
    @BindView(R.id.imgviewSearch)
    ImageView imgviewSearch;

    @BindView(R.id.progress_bar)
    ProgressView progressBar;

    private Activity mActivity = this;
    private Dialog dialog;
    private ServiceHandler serviceHandler;
    private Boolean isInternetPresent = false;
    private SharedPreference sharedPreference;
    private String serverUrl,returnItemsearch="";
    private PurchasePosCreator purchasePosCreator;
    private ArrayList<PurchaseInvoiceReturnData> returnList;
    PurchaseReturn_Adapter adapter;
    PurchaseInvoiceReturnData invoiceReturnData = new PurchaseInvoiceReturnData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_invoice_return_item);
        ButterKnife.bind(this);
        initComponents();
    }

    private void initComponents() {
        ButterKnife.bind(this);
        mActivity = this;
        edSearch.addTextChangedListener(this);
        serverUrl = UtilView.getUrl(mActivity);
        serviceHandler = new ServiceHandler(mActivity);
        purchasePosCreator = PurchasePosHelper.getPosCreator();
        toolbar.setTitle("Type Invoice No");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getPIReturnItemList("");

        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    returnItemsearch = edSearch.getText().toString().trim();
                    getPIReturnItemList(returnItemsearch);
                }
                return handled;
            }
        });

        lvPIReturnList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                invoiceReturnData = returnList.get(position);

                    isInternetPresent = serviceHandler.isConnectingToInternet();
                    if (serverUrl != null) {
                        if (isInternetPresent) {
                            UtilView.showProgessBar(mActivity, progressBar);
                            String url = serverUrl + "/purchase//0/" + Constant.FUNTION_GETPURCHASEINVOICE + "/" + invoiceReturnData.getPiNo().toUpperCase();
                            GetDataTask getInvoicetask = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                                @Override
                                public void onTaskComplete(String result) {
                                    UtilView.showProgessBar(mActivity, progressBar);
                                    if (result != null) {
                                        purchasePosCreator.clear();
                                        Gson gson = new Gson();
                                       // try {
                                             invoiceReturnData = gson.fromJson(result.toString(), PurchaseInvoiceReturnData.class);
                                            if (invoiceReturnData != null) {


                                                if (invoiceReturnData.getSelectedItemsList() != null && invoiceReturnData.getSelectedItemsList().size() > 0) {
                                                    for (int i = 0; i < invoiceReturnData.getSelectedItemsList().size(); i++) {
                                                        SelectedItemsList item = invoiceReturnData.getSelectedItemsList().get(i);


                                                        SelectedItemsList invokeItems = new SelectedItemsList();
                                                        invokeItems.setItemName(item.getItemName());
                                                        invokeItems.setItemId(item.getItemId());
                                                        invokeItems.setUnitPrice(item.getUnitPrice());


                                                        invokeItems.setQty(item.getQty());

                                                        String[] qty = invokeItems.getQty().split("\\.");
                                                        invokeItems.setItemQuantity(Long.parseLong(qty[0]));
                                                        invokeItems.setReturnQty(item.getReturnQty());
                                                        invokeItems.setRemainingQty("" + item.getRemainingQty());
                                                        invokeItems.setAmtexclusivetax(item.getAmtexclusivetax());
                                                        invokeItems.setTaxid(item.getTaxid());
                                                        invokeItems.setTaxpercent("" + item.getTaxpercent());
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
                                                        //invokeItems.setPrice(BigDecimal.valueOf(Long.parseLong(item.getUnitPrice())));
                                                        invokeItems.setUomConvertorList(item.getUomConvertorList());
                                                        invokeItems.setTaxAmountSplit(item.getTaxAmountSplit());
                                                        invokeItems.setTaxTypeSelection(item.getTaxTypeSelection());

                                                        if (invokeItems.getUnitPrice()!=null){

                                                            invokeItems.setPrice(new BigDecimal(item.getUnitPrice()));
                                                        }
                                                        else{
                                                            invokeItems.setPrice(BigDecimal.ZERO);

                                                        }


                                                        purchasePosCreator.addItem(new PurchasePosCartItem(invokeItems, 1), invokeItems.getItemId());

                                                    }


                                                }
                                                Intent returnIntent = new Intent();
                                                returnIntent.putExtra("returnItemData", invoiceReturnData);
                                                mActivity.setResult(Activity.RESULT_OK, returnIntent);
                                                mActivity.finish();


                                            } else {
                                                UtilView.showToast(mActivity, "No order items Available ");
                                            }
//                                        } catch (Exception e) {
//                                            UtilView.showErrorDialog(getResources().getString(R.string.response_error), mActivity);
//                                        }
                                    }
                                }
                            }, false);
                            getInvoicetask.execute(url, "");
                        } else {
                            UtilView.showToast(mActivity, getResources().getString(R.string.intertnet_status));
                        }
                    } else {
                        UtilView.gotToLogin(mActivity);
                    }



            }
        });
    }

    private void getPIReturnItemList(String search) {
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
                if (progressBar != null)
                    progressBar.setVisibility(View.VISIBLE);

                String url ="";
                if (search.equals("")) {
                    url = serverUrl + "/purchase//0/" + Constant.FUNTION_GETPURCHASEINVOICESLIST+"?itemSearchText=";
                }

                if (!search.equals("")) {
                    url = serverUrl + "/purchase//0/" + Constant.FUNTION_GETPURCHASEINVOICESLIST+"?itemSearchText="+ search.replace(" ", "%20");

                }

                GetDataTask getOrderList = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        if (progressBar != null)
                            progressBar.setVisibility(View.GONE);
                        if (result != null) {
                            Gson gson = new Gson();
                            returnList = new ArrayList<PurchaseInvoiceReturnData>();
                            try {
                                JSONArray jsonArray = new JSONArray(result.toString());
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonInovice = jsonArray.getJSONObject(i);
                                    PurchaseInvoiceReturnData inovice = gson.fromJson(jsonInovice.toString(), PurchaseInvoiceReturnData.class);
                                    returnList.add(inovice);
                                }
                                if (returnList != null && returnList.size() > 0) {

                                    llListview.setVisibility(View.VISIBLE);
                                    adapter = new PurchaseReturn_Adapter(mActivity, returnList);
                                    lvPIReturnList.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                } else {
                                    returnList.clear();
                                    llListview.setVisibility(View.GONE);
                                    adapter = new PurchaseReturn_Adapter(mActivity, returnList);
                                    lvPIReturnList.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                    UtilView.showSuccessDialog("No Invoices Available", mActivity);
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
    public void onClick(View view) {
        returnItemsearch = edSearch.getText().toString().trim();
        getPIReturnItemList(returnItemsearch);
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
            returnItemsearch = "";
            getPIReturnItemList(returnItemsearch);
        }
    }
}

