package in.hiaccounts.hinext.restaurant.fragment;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.NavigationDrawer_Activity;
import in.hiaccounts.hinext.customer.activity.Activity_Customer;
import in.hiaccounts.hinext.customer.model.Customer;
import in.hiaccounts.hinext.customer.model.CustomerList;
import in.hiaccounts.hinext.restaurant.activity.Activity_RestraPayment;
import in.hiaccounts.hinext.restaurant.activity.Activity_RestraPayment1;
import in.hiaccounts.hinext.restaurant.adapter.RecyclerRestuarantCategoryAdapter;
import in.hiaccounts.hinext.restaurant.adapter.RecyclerRestuarantItemsAdapter;
import in.hiaccounts.hinext.restaurant.adapter.Restrapos_Adapter;
import in.hiaccounts.hinext.restaurant.model.category_item.SelectedItemsList;
import in.hiaccounts.hinext.restaurant.model.checkout.RestraCheckoutData;
import in.hiaccounts.hinext.restaurant.model.checkout.RestraCheckoutItem;
import in.hiaccounts.hinext.restaurant.model.restaurant_pagedata.HinextRestuarantPageData;
import in.hiaccounts.hinext.restaurant.model.restaurant_pagedata.InclusiveJson;
import in.hiaccounts.hinext.restaurant.model.restaurant_pagedata.ItemCategory;
import in.hiaccounts.hinext.restaurant.model.restaurant_pagedata.TaxList;
import in.hiaccounts.hinext.restaurant.model.restra_pos.RestraPosCartItem;
import in.hiaccounts.hinext.restaurant.model.restra_pos.RestraPosCreator;
import in.hiaccounts.hinext.restaurant.model.restra_pos.RestraPosHelper;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;

import static in.hiaccounts.R.string.response_error;

/**
 * Srinath 20-11-2017.
 */
public class Fragment_Restaurant extends Fragment {

    public static String TAG = Fragment_Restaurant.class.getSimpleName();
    @BindView(R.id.btnSelectCategory)
    TextView btnSelectCategory;
    @BindView(R.id.id_recyclerview_category)
    RecyclerView idRecyclerviewCategory;
    @BindView(R.id.btnSelectItem)
    TextView btnSelectItem;
    @BindView(R.id.id_recyclerview_Item)
    RecyclerView idRecyclerviewItem;
    @BindView(R.id.listviewSelec)
    ListView listviewSelec;
    @BindView(R.id.fabPayment)
    FloatingActionButton fabPayment;
    @BindView(R.id.fabclearall)
    FloatingActionButton fabclearall;

    @BindView(R.id.restaaurent)
    FrameLayout restaaurent;
    Unbinder unbinder;
    @BindView(R.id.edCartQty)
    EditText edCartQty;
    @BindView(R.id.edCartTotalamount)
    EditText edCartTotalamount;
    @BindView(R.id.llCategory)
    LinearLayout llCategory;
    @BindView(R.id.llItem)
    LinearLayout llItem;
    @BindView(R.id.llListview)
    LinearLayout llListview;
    @BindView(R.id.rlBottomLayout)
    RelativeLayout rlBottomLayout;
    @BindView(R.id.rlContent)
    RelativeLayout rlContent;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    private String taxString = "", serverUrl;
    private Activity mActivity;
    private Dialog dialog;
    private ServiceHandler serviceHandler;
    Boolean isInternetPresent = false;
    private SharedPreference sharedPreference;
    private HinextRestuarantPageData pageData;
    List<SelectedItemsList> itemList;
    LinearLayoutManager linearLayoutManager;
    private Long categoryId;

    RestraPosCreator posCreator;
    Restrapos_Adapter cartItemAdapter;
    public static List<SelectedItemsList> selectItemForDelete = new ArrayList<SelectedItemsList>();

    Fragment_Restaurant fragmentRestaurant;
    Long customerId = 2L;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
        fragmentRestaurant = this;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_restaurant, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponents(view, savedInstanceState);
        return view;
    }

    private void initComponents(View view, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        posCreator = RestraPosHelper.getPosCreator();
        serverUrl = UtilView.getUrl(mActivity);
        dialog = new Dialog(mActivity);
        serviceHandler = new ServiceHandler(mActivity);
        sharedPreference = SharedPreference.getInstance(mActivity);
        getPageLoadData();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.restuarant_menu, menu);
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
                Intent intent = new Intent(mActivity, Activity_Customer.class);
                intent.putExtra("callingfrom", Constant.NAVIGATION_RESTAURANTCATEGORY_ITEM);
                mActivity.startActivityForResult(intent, Constant.RESQUSTCODE_CUSTOMERS);
                break;
            case R.id.deleteMenu:
                if (selectItemForDelete != null && selectItemForDelete.size() > 0) {
                    posCreator.delete(selectItemForDelete);
                    checkCartList();
                    selectItemForDelete.clear();
                } else {
                    UtilView.showToast(mActivity, "Please select at leat one item to delete");
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void getPageLoadData() {
        String url = serverUrl + "/restaurant/" + Constant.FUNTION_RESTRAGETPAGELOADDATA;
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
                UtilView.showProgessBar(mActivity, progressBar);
                GetDataTask getDataTask = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                             UtilView.hideProgessBar(mActivity, progressBar);
                        if (result != null) {
                            if (result.toString().equals(getResources().getString(R.string.response200_sessionLost))) {
                                UtilView.gotToLogin(mActivity);
                            } else {
                                Gson gson = new Gson();
                                pageData = gson.fromJson(result.toString(), HinextRestuarantPageData.class);
                                sharedPreference.saveData(Constant.HINEXTRESTUARANTDATA_KEY,new Gson().toJson(pageData));
                                setupPageData();
                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                        }
                    }
                }, false);
                getDataTask.execute(url, "");
            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
            }
        } else {
            UtilView.gotToLogin(mActivity);
        }
    }


    private void setupPageData() {

        if (llCategory!=null) {
            llCategory.setVisibility(View.VISIBLE);
            idRecyclerviewCategory.setHasFixedSize(true);
            linearLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false);
            idRecyclerviewCategory.setLayoutManager(linearLayoutManager);

            idRecyclerviewCategory.setAdapter(new RecyclerRestuarantCategoryAdapter(mActivity, pageData.getItemCategorys(), new RecyclerRestuarantCategoryAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(ItemCategory item) {
                    // Toast.makeText(getContext(), "Item Clicked "+item.getItemCategoryId(), Toast.LENGTH_LONG).show();
                    categoryId = item.getItemCategoryId();
                    getPageLoadItemData(categoryId);
                }
            }));
        }

    }

    private void getPageLoadItemData(Long categoryId) {


        String url = serverUrl + "/hipos/0/" + Constant.FUNTION_RESTRAITEMLISTINCCATEGOERY + "?" + Constant.FUNTION_RESTRAITEMLISTINCCATEGOERYID + "=" + categoryId;
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
                 UtilView.showProgessBar(mActivity, progressBar);
                GetDataTask getDataTask = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                            UtilView.hideProgessBar(mActivity, progressBar);
                        if (result != null) {
                            if (result.toString().equals(getResources().getString(R.string.response200_sessionLost))) {
                                UtilView.gotToLogin(mActivity);
                            } else {
                                Gson gson = new Gson();
                                itemList = new ArrayList<>();

                                try {
                                    JSONArray jsonArray = new JSONArray(result.toString());

                                    if (jsonArray != null) {
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                                            SelectedItemsList pageItemData = gson.fromJson(jsonObject.toString(), SelectedItemsList.class);
                                            itemList.add(pageItemData);
                                        }

                                        if (itemList != null && itemList.size() > 0) {
                                            if(llItem!=null) {
                                                llItem.setVisibility(View.VISIBLE);
                                                setupPageItemData();
                                            }
                                        }else {
                                            UtilView.showToast(mActivity,"No Items. Please add Items.");
                                        }

                                    }else {
                                        UtilView.showToast(mActivity,getResources().getString(R.string.response_error));
                                    }


                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                        }
                    }
                }, false);
                getDataTask.execute(url, "");
            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
            }
        } else {
            UtilView.gotToLogin(mActivity);
        }
    }

    private void setupPageItemData() {
        idRecyclerviewItem.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false);
        idRecyclerviewItem.setLayoutManager(linearLayoutManager);
        idRecyclerviewItem.setAdapter(new RecyclerRestuarantItemsAdapter(true,mActivity, itemList, new RecyclerRestuarantItemsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SelectedItemsList item) {
                //Toast.makeText(getContext(), "Item Clicked " + item.getItemName(), Toast.LENGTH_LONG).show();
                double gstTaxPercantage =0.00;
                if (pageData!=null){
                    List<TaxList>taxList=pageData.getTaxList();
                    if (taxList!=null) {
                        for (int i = 0; i < taxList.size(); i++) {
                            if (item.getOutputTaxId() == taxList.get(i).getTaxid()) {
                                gstTaxPercantage = taxList.get(i).getTaxPercantage();
                            }
                        }
                    }
                }
                double itemUnitPrice=0.00;
                if (item.getInclusiveJSON()!=null) {
                    Gson gson=new Gson();
                    InclusiveJson inclusiveJson = gson.fromJson(item.getInclusiveJSON(), InclusiveJson.class);
                    if (inclusiveJson.isSales()){
                        itemUnitPrice = item.getSalesPrice() / (1 + (gstTaxPercantage * 0.01));
                    }else {
                        if (item.getSalesPrice()!=null)
                            itemUnitPrice=item.getSalesPrice();
                    }
                }

                item.setGstTaxPercantage(gstTaxPercantage);
                item.setUnitPrice(itemUnitPrice);
                long itemQty=posCreator.checkItemQty(item);
                item.setItemQuantity(itemQty);
                posCreator.addItem(new RestraPosCartItem(item, 1), item.getItemId());
                checkCartList();
            }
        }));
    }


    public void checkCartList() {
        if (posCreator != null) {
            if (posCreator.getItems().size() > 0) {
                // showTotalPrice();
                List<RestraPosCartItem> posCartItems = posCreator.getItems();
                cartItemAdapter = new Restrapos_Adapter(fragmentRestaurant, mActivity, posCartItems);
                try {
                      listviewSelec.smoothScrollToPosition(posCartItems.size());
                    if (listviewSelec != null)
                        listviewSelec.setAdapter(cartItemAdapter);
                    cartItemAdapter.notifyDataSetChanged();
                    showTotalPrice();
                } catch (Exception e) {
                    UtilView.showLogCat(TAG, "checkCartList cart Exception " + e.toString());
                }

                if (llListview!=null)
                llListview.setVisibility(View.VISIBLE);
                if (rlBottomLayout!=null)
                    rlBottomLayout.setVisibility(View.VISIBLE);

            } else {
                List<RestraPosCartItem> posCartItems = posCreator.getItems();
                if (cartItemAdapter != null) {
                    cartItemAdapter.updateCartItems(posCartItems);
                    cartItemAdapter.notifyDataSetChanged();
                }
                if (llListview!=null)
                    llListview.setVisibility(View.GONE);
                if (rlBottomLayout!=null)
                    rlBottomLayout.setVisibility(View.GONE);
            }
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void showTotalPrice() {
        Double posCartTotal = 0.00;
        int posTotalItem = 0;
        for (int i = 0; i < posCreator.getItems().size(); i++) {
            posCartTotal += posCreator.getItems().get(i).getItem().getItemTotalAmount();
            posTotalItem += posCreator.getItems().get(i).getItem().getItemQuantity();
        }


        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        UtilView.showLogCat(TAG,""+df.format(posCartTotal));
        UtilView.showLogCat(TAG,""+(int) Math.ceil(posCartTotal));
        edCartTotalamount.setText(""+(int) Math.ceil(posCartTotal));
       //edCartTotalamount.setText(String.valueOf(new BigDecimal(posCartTotal).setScale(2,RoundingMode.HALF_UP)));
        edCartQty.setText("" + posTotalItem);


        posCreator.setTotalItems(posTotalItem);
        posCreator.setTotalPrice(posCartTotal);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constant.RESQUSTCODE_CUSTOMERS && resultCode == Activity.RESULT_OK) {
            CustomerList cust = (CustomerList) data.getSerializableExtra("customer");
            if (cust != null) {
                UtilView.showToast(mActivity, "Cutomer " + cust.getCustomerName());
                Customer selected_customer = new Customer();
                customerId = cust.getCustomerId();

                if (cust.getCustomerEmail() == null) {
                    selected_customer.setCustomerEmail("");
                } else {
                    selected_customer.setCustomerEmail(cust.getCustomerEmail());
                }

                if (cust.getCustomerNumber() == null) {
                    selected_customer.setCustomerNumber("");
                } else {
                    selected_customer.setCustomerNumber(cust.getCustomerNumber());
                }

                if (cust.getCustomerContact() == null) {
                    selected_customer.setCustomerContact("");
                } else {
                    selected_customer.setCustomerContact(cust.getCustomerContact());
                }
                if (cust.getCustomerName() == null) {
                    selected_customer.setCustomerName("");
                } else {
                    selected_customer.setCustomerName(cust.getCustomerName());
                }

                selected_customer.setCustomerId(cust.getCustomerId());
            }
        }
        if (requestCode== Constant.RESQUSTCODE_CHECKOUT && resultCode== Activity.RESULT_OK){

            if (posCreator!=null){
                posCreator.clear();
                checkCartList();
            }

        }
    }

    @OnClick({R.id.fabPayment,R.id.fabclearall})
    public void onViewClicked(View view) {

        switch (view.getId()) {

            case R.id.fabPayment:
               // checkoutDialog();

                if (posCreator!=null && posCreator.getItems()!=null && posCreator.getItems().size()>0) {
                    Intent intent = new Intent(mActivity, Activity_RestraPayment1.class);
                    intent.putExtra("checkoutData", getCheckoutData());
                    checkoutType = "cashPayment";
                    intent.putExtra("checkoutType", checkoutType);
                    startActivityForResult(intent, Constant.RESQUSTCODE_CHECKOUT);
                }else {
                   UtilView.showToast(mActivity,"No item in Cart. Please add item.");
                }

                break;

            case R.id.fabclearall:
                if (posCreator!=null) {
                    posCreator.clear();
                    checkCartList();
                }
                break;
        }

    }

    String checkoutType = "";

    private void checkoutDialog() {

        final Dialog dialog = new Dialog(mActivity);
        dialog.setContentView(R.layout.dialog_generaltransaction_purchasereturn);
        // set the custom dialog components - text, image and button
        LinearLayout ll_cash = dialog.findViewById(R.id.ll_cash);
        LinearLayout ll_cheque = dialog.findViewById(R.id.ll_cheque);

        Button btnClose = dialog.findViewById(R.id.btn_close);
        ImageView imageview_close = dialog.findViewById(R.id.imageview_close);
        TextView btn_cash = dialog.findViewById(R.id.btn_cash);


        Typeface font = Typeface.createFromAsset(mActivity.getAssets(), "fonts/fontawesome-webfont.ttf");
        btn_cash.setTypeface(font);

        if (dialog != null)
            dialog.show();

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog != null)
                    dialog.dismiss();
            }
        });

        imageview_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog != null)
                    dialog.dismiss();
            }
        });


        ll_cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mActivity, Activity_RestraPayment.class);
                intent.putExtra("checkoutData", getCheckoutData());
                checkoutType = "cashPayment";
                intent.putExtra("checkoutType", checkoutType);
                startActivityForResult(intent, Constant.RESQUSTCODE_CHECKOUT);
                if (dialog != null && dialog.isShowing()) dialog.dismiss();
            }

        });
              /*  ll_cheque.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(mActivity, Activity_RestraPayment.class);
                        intent.putExtra("checkoutData", getCheckoutData());
                        checkoutType="Cheque";
                        intent.putExtra("checkoutType",checkoutType);
                        startActivityForResult(intent, Constant.RESQUSTCODE_CHECKOUT);
                        if (dialog != null && dialog.isShowing()) dialog.dismiss();
                    }

                });*/
    }

    private RestraCheckoutData getCheckoutData() {
        RestraCheckoutData data = new RestraCheckoutData();
        List<RestraCheckoutItem> itemList = new ArrayList<>();
        double totalTaxAmt = 0;
        double totalAmt = 0;
        if (posCreator != null && posCreator.getItems().size() > 0) {
            for (int i = 0; i < posCreator.getItems().size(); i++) {
                RestraCheckoutItem item = new RestraCheckoutItem();
                item.setItemCode(posCreator.getItems().get(i).getItem().getItemCode());
                item.setItemId(posCreator.getItems().get(i).getItem().getItemId());
                item.setItemName(posCreator.getItems().get(i).getItem().getItemName());
                item.setUnitPrice(posCreator.getItems().get(i).getItem().getUnitPrice());
                item.setGstItemTax(posCreator.getItems().get(i).getItem().getGstTaxAmt());
                item.setAmtinclusivetax(posCreator.getItems().get(i).getItem().getItemTotalAmountExTax());
                item.setAmtinclusivetax(posCreator.getItems().get(i).getItem().getItemTotalAmountInTax());
                item.setQty(posCreator.getItems().get(i).getItem().getItemQuantity());
                totalTaxAmt += posCreator.getItems().get(i).getItem().getGstTaxAmt();
                totalAmt += posCreator.getItems().get(i).getItem().getItemTotalAmount();
                itemList.add(item);
            }
            data.setTaxType("CGST:SGST/UGST");
            data.setPaymentType("multiPayment");
            data.setCustomerId(customerId);
            data.setSelectedItemsList(itemList);
            data.setTotalCheckOutamt(totalAmt);
            data.setTotalTaxAmt(totalTaxAmt);


        }
        return data;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (posCreator!=null) {
            posCreator.clear();
            checkCartList();
        }


    }
}
