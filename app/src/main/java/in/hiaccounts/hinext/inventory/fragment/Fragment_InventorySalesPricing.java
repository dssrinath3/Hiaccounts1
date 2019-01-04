package in.hiaccounts.hinext.inventory.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.hiaccounts.R;
import in.hiaccounts.hinext.customer.activity.Activity_Customer;
import in.hiaccounts.hinext.customer.model.CustomerList;
import in.hiaccounts.hinext.inventory.adapter.SalesPriceAdapter;
import in.hiaccounts.hinext.inventory.model.salespricing.InventorySalesPricing;
import in.hiaccounts.hinext.inventory.model.salespricing.InventorySalesPricingData;
import in.hiaccounts.hinext.item.activity.Activity_ShowItemList;
import in.hiaccounts.hinext.item.model.SelectedItemsList;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 2/9/2017.
 */

public class Fragment_InventorySalesPricing extends Fragment implements TextWatcher {
    public static final String TAG = Fragment_InventorySalesPricing.class.getSimpleName();
    Unbinder unbinder;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.edSearch)
    EditText edSearch;
    @BindView(R.id.llSearch)
    LinearLayout llSearch;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.tvFirst)
    TextView tvFirst;
    @BindView(R.id.tvPrev)
    TextView tvPrev;
    @BindView(R.id.tvPageNo)
    TextView tvPageNo;
    @BindView(R.id.tvNext)
    TextView tvNext;
    @BindView(R.id.tvLast)
    TextView tvLast;
    @BindView(R.id.llPaginationView)
    LinearLayout llPaginationView;
    private int pageNo = 0;
    private Activity activity;
    private ServiceHandler serviceHandler;
    private Boolean isInternetPresent = false;
    private List<Object> salesPriceList = new ArrayList<Object>();
    private SalesPriceAdapter salesPriceAdapter;
    private String search = "",serverUrl;
    private CustomerList customer = null;
    private SelectedItemsList itemData = null;
    private Long itemID;

    private EditText edCustomerName;
    private EditText edItemName;

    public static Fragment_InventorySalesPricing newInstance() {
        Fragment_InventorySalesPricing fragment = new Fragment_InventorySalesPricing();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Returning the layout file after inflating
        View view = inflater.inflate(R.layout.fragment_inventorysalespricing, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponents(view);
        return view;
    }

    private void initComponents(View view) {
        setHasOptionsMenu(true);
        serviceHandler = new ServiceHandler(activity);
        serverUrl= UtilView.getUrl(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.inventory_adapter_salespriceview, null, false);
        TextView tvItemName = header.findViewById(R.id.tvItemName);
        TextView tvCustomerName = header.findViewById(R.id.tvCustomerName);
        TextView tvSalesPrice = header.findViewById(R.id.tvSalesPrice);
        TextView tvEdit = header.findViewById(R.id.tvEdit);
        tvItemName.setText("Item Name");
        tvCustomerName.setText("Customer Name");
        tvSalesPrice.setText("Sales Price");
        tvEdit.setText("Edit");
        UtilView.setTextAppearanceSmall(activity, tvItemName);
        UtilView.setTextAppearanceSmall(activity, tvCustomerName);
        UtilView.setTextAppearanceSmall(activity, tvSalesPrice);
        UtilView.setTextAppearanceSmall(activity, tvEdit);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvItemName);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvCustomerName);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvSalesPrice);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvEdit);
        if (listview != null)
            listview.addHeaderView(header);
        edSearch.addTextChangedListener(this);
        getSalesPriceFromServer("", true, false, "0", false, false);
        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(activity, edSearch);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    search = edSearch.getText().toString().trim();
                    getSalesPriceFromServer(search, true, false, "0", false, false);
                }
                return handled;
            }
        });
    }

    private void getSalesPriceFromServer(String search, boolean isFirst, boolean isPrev, String pageNumber, boolean isNext, boolean isLast) {
        String url = "";
        if (search.equals("")) {
            url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETINVENTORY_SALESPRICELISTING + "?salesPricingSearchText=&firstPage=" + isFirst + "&lastPage=" + isLast + "&nextPage=" + isNext + "&pageNo=" + pageNumber + "&prevPage=" + isPrev;
        }
        if (!search.equals("")) {
            url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETINVENTORY_SALESPRICELISTING + "?salesPricingSearchText=" + search + "&firstPage=" + isFirst + "&lastPage=" + isLast + "&nextPage=" + isNext + "&pageNo=" + pageNumber + "&prevPage=" + isPrev;
        }
        if (serverUrl!=null) {
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {
                // prepare the Request
                UtilView.showProgessBar(activity, progressBar);
                GetDataTask task = new GetDataTask(activity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(activity, progressBar);
                        if (result != null) {
                            Gson gson = new Gson();
                            try {

                            salesPriceList.clear();
                            List<InventorySalesPricing> list = new ArrayList<>();
                            InventorySalesPricingData data = gson.fromJson(result.toString(), InventorySalesPricingData.class);
                            if (data != null) {
                                setUpView(data);
                                if (data.getData() != null && data.getData().size() > 0) {
                                    list = data.getData();
                                    salesPriceList.addAll(list);
                                    salesPriceAdapter = new SalesPriceAdapter(activity, salesPriceList);
                                    if (listview != null)
                                        listview.setAdapter(salesPriceAdapter);
                                    salesPriceAdapter.notifyDataSetChanged();
                                } else {
                                    salesPriceList.clear();
                                    salesPriceAdapter = new SalesPriceAdapter(activity, salesPriceList);
                                    if (listview != null)
                                        listview.setAdapter(salesPriceAdapter);
                                    salesPriceAdapter.notifyDataSetChanged();
                                    UtilView.showSuccessDialog(getResources().getString(R.string.salesprice_notavailbale), activity);
                                }
                            }

                            }catch (Exception e){
                                UtilView.showErrorDialog(getResources().getString(R.string.error_null), activity);
                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.error_null), activity);
                        }
                    }
                }, false);
                task.execute(url, "");
            }
            if (!isInternetPresent) {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
            }
        }else {
            UtilView.gotToLogin(activity);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null)
            unbinder.unbind();
    }

    @OnClick({R.id.tvFirst, R.id.tvPrev, R.id.tvNext, R.id.tvLast, R.id.llSearch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llSearch:
                UtilView.hideSoftKeyboard(activity, edSearch);
                search = edSearch.getText().toString().trim();
                pageNo = 0;
                getSalesPriceFromServer(search, true, false, "0", false, false);
                break;
            case R.id.tvFirst:
                pageNo = 0;
                getSalesPriceFromServer("", true, false, "0", false, false);
                break;
            case R.id.tvPrev:
                if (pageNo > 0) {
                    pageNo = pageNo - 1;
                }
                getSalesPriceFromServer("", false, true, "" + pageNo, false, false);
                break;
            case R.id.tvNext:
                pageNo = pageNo + 1;
                getSalesPriceFromServer("", false, false, "" + pageNo, true, false);
                break;
            case R.id.tvLast:
                pageNo = 0;
                getSalesPriceFromServer("", false, false, "0", false, true);
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.toString().equals("")) {
            UtilView.hideSoftKeyboard(activity, edSearch);
            search = "";
            getSalesPriceFromServer(search, true, false, "0", false, false);
        }
    }

    private void setUpView(InventorySalesPricingData data) {
        if (llPaginationView != null)
            llPaginationView.setVisibility(View.VISIBLE);
        if (data.isFirst()) {
            //  tvFirst.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
            if (tvFirst != null) {
                tvFirst.setTextColor(getResources().getColor(R.color.colorBlack));
                tvFirst.setClickable(false);
            }
        }
        if (!data.isFirst()) {
            // tvFirst.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
            if (tvFirst != null) {
                tvFirst.setTextColor(getResources().getColor(R.color.colorWhite));
                tvFirst.setClickable(true);
            }
        }
        if (data.isNext()) {
            // tvNext.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
            if (tvNext != null) {
                tvNext.setTextColor(getResources().getColor(R.color.colorBlack));
                tvNext.setClickable(false);
            }
        }
        if (!data.isNext()) {
            //  tvNext.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
            if (tvNext != null) {
                tvNext.setTextColor(getResources().getColor(R.color.colorWhite));
                tvNext.setClickable(true);
            }
        }
        if (data.isPrev()) {
            //  tvPrev.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
            if (tvPrev != null) {
                tvPrev.setTextColor(getResources().getColor(R.color.colorBlack));
                tvPrev.setClickable(false);
            }
        }
        if (!data.isPrev()) {
            //  tvPrev.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
            if (tvPrev != null) {
                tvPrev.setTextColor(getResources().getColor(R.color.colorWhite));
                tvPrev.setClickable(true);
            }
        }
        if (data.isLast()) {
            //  tvLast.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
            if (tvLast != null) {
                tvLast.setTextColor(getResources().getColor(R.color.colorBlack));
                tvLast.setClickable(false);
            }
        }
        if (!data.isLast()) {
            //   tvLast.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
            if (tvLast != null) {
                tvLast.setTextColor(getResources().getColor(R.color.colorWhite));
                tvLast.setClickable(true);
            }
        }
        pageNo = data.getPageNo();

    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.inventory_menu, menu);
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
                addSalesPricing();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.RESQUSTCODE_CUSTOMERS && resultCode == Activity.RESULT_OK) {
            customer = (CustomerList) data.getSerializableExtra("customer");
            if (customer.getCustomerName() != null)
                edCustomerName.setText(customer.getCustomerName());
        }
        if (requestCode == Constant.RESQUSTCODE_ITEMSEARCH && resultCode == Activity.RESULT_OK) {
            itemData = (SelectedItemsList) data.getSerializableExtra("item");
            if (itemData.getItemName() != null)
                edItemName.setText(itemData.getItemName());
        }

    }


    private void addSalesPricing() {
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.dialog_inventory_salesprice);
        dialog.setCancelable(false);
        edCustomerName = dialog.findViewById(R.id.edCustomerName);
        edItemName = dialog.findViewById(R.id.edItemName);
        final EditText edPrice = dialog.findViewById(R.id.edPrice);
        Button btnClose = dialog.findViewById(R.id.btnClose);
        Button btnAdd = dialog.findViewById(R.id.btnAdd);
        final ProgressView pgview = dialog.findViewById(R.id.progress_bar);


        btnAdd.setText("Add");

        edCustomerName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, Activity_Customer.class);
                startActivityForResult(intent, Constant.RESQUSTCODE_CUSTOMERS);
            }
        });

        edItemName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getItemData();
            }
        });


        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });



        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String price = edPrice.getText().toString().trim();
                String customername = edCustomerName.getText().toString().trim();
                String itemname = edItemName.getText().toString().trim();
                boolean isUpdatble = true;
                double doubleprice = 0;
                if (price == null || price.equals("")) {
                    edPrice.setError("Price can't be empty");
                } else {

                    try {
                        doubleprice = Double.parseDouble(price);
                        isUpdatble = true;
                    } catch (NumberFormatException ne) {
                        edPrice.setError("Please enter valid price");
                        isUpdatble = false;

                    }

                    if (isUpdatble) {
                        String url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETINVENTORY_UPDATESALESPRICING;


                        if (!url.equals("")) {
                            isInternetPresent = serviceHandler.isConnectingToInternet();
                            if (isInternetPresent) {

                                pgview.setVisibility(View.VISIBLE);
                                UtilView.showProgessBar(activity, progressBar);
                                InventorySalesPricing salesPriceData = new InventorySalesPricing();


                                salesPriceData.setCustomerID(customer.getCustomerId());
                                salesPriceData.setCustomerName(customername);
                                salesPriceData.setItemID(itemData.getItemId());
                                salesPriceData.setItemName(itemname);
                                salesPriceData.setPrice(doubleprice);


                                PostDataTask postDataTask = new PostDataTask(activity, new AsyncTaskCompleteListener<String>() {
                                    @Override
                                    public void onTaskComplete(String result) {
                                        pgview.setVisibility(View.GONE);
                                        UtilView.hideProgessBar(activity, progressBar);
                                        if (result != null) {
                                            Gson gson = new Gson();
                                            InventorySalesPricing salesPrice = gson.fromJson(result.toString(), InventorySalesPricing.class);
                                            if (salesPrice != null) {

                                                if (dialog != null && dialog.isShowing()) {
                                                    dialog.dismiss();
                                                }
                                                UtilView.showToast(activity, "Sales Price Added Successfully");
                                                getSalesPriceFromServer("", false, false, "0", false, true);

                                            } else {
                                                UtilView.showToast(activity, "Sales Price not Added successfully. Please Try Again.");
                                            }
                                        } else {
                                            UtilView.showToast(activity, activity.getResources().getString(R.string.response_error));
                                        }
                                    }
                                }, false);
                                postDataTask.execute(new Gson().toJson(salesPriceData).toString(), url, "");


                            }
                            if (!isInternetPresent) {
                                UtilView.showErrorDialog(activity.getResources().getString(R.string.intertnet_status), activity);
                            }
                        }
                    }

                }
            }
        });

        if (dialog != null)
            dialog.show();
    }

    private void getItemData() {
        if (serverUrl != null) {
            String url = serverUrl + "/hipos//1/" + Constant.FUNTION_GETITEMLIST + "?itemSearchText=";
            Intent intent = new Intent(activity, Activity_ShowItemList.class);
            intent.putExtra("url", url);
            intent.putExtra("taxType", "");
            intent.putExtra("itemsearch", "");
            intent.putExtra("callingfrom", Constant.CALL_ADDINVENTORUOPENING_ITEM);
            startActivityForResult(intent, Constant.RESQUSTCODE_ITEMSEARCH);
        } else {
            UtilView.gotToLogin(activity);
        }
    }

}
