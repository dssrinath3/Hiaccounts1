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
import in.hiaccounts.hinext.inventory.adapter.PurchasePriceAdapter;
import in.hiaccounts.hinext.inventory.model.purchasepricing.InventoryPurchasePricing;
import in.hiaccounts.hinext.inventory.model.purchasepricing.InventoryPurchasePricingData;
import in.hiaccounts.hinext.item.activity.Activity_ShowItemList;
import in.hiaccounts.hinext.item.model.SelectedItemsList;
import in.hiaccounts.hinext.supplier.activity.Activity_Supplier;
import in.hiaccounts.hinext.supplier.model.GetSupplier;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 2/9/2017.
 */

public class Fragment_InventoryPurchasePricing extends Fragment implements TextWatcher {
    public static final String TAG = Fragment_InventoryPurchasePricing.class.getSimpleName();
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
    private List<Object> purchasePriceList = new ArrayList<Object>();
    private PurchasePriceAdapter purchasePriceAdapter;
    private String search = "", serverUrl, supplierID;

    private GetSupplier supplier = null;
    private SelectedItemsList itemData = null;
    private Long itemID;
    private EditText edSupplierName;
    private EditText edItemName;

    public static Fragment_InventoryPurchasePricing newInstance() {
        Fragment_InventoryPurchasePricing fragment = new Fragment_InventoryPurchasePricing();
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
        View view = inflater.inflate(R.layout.fragment_inventorypurchasepricing, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponents(view);
        return view;
    }

    private void initComponents(View view) {
        setHasOptionsMenu(true);
        serviceHandler = new ServiceHandler(activity);
        serverUrl= UtilView.getUrl(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.inventory_adapter_purchasepriceview, null, false);
        if (header != null) {
            TextView tvItemName = header.findViewById(R.id.tvItemName);
            TextView tvSupplierName = header.findViewById(R.id.tvSupplierName);
            TextView tvPurchasePrice = header.findViewById(R.id.tvPurchasePrice);
            TextView tvEdit = header.findViewById(R.id.tvEdit);
            tvItemName.setText("Item Name");
            tvSupplierName.setText("GetSupplier Name");
            tvPurchasePrice.setText("Purchase Price");
            tvEdit.setText("Edit");
            UtilView.setTextAppearanceSmall(activity, tvItemName);
            UtilView.setTextAppearanceSmall(activity, tvSupplierName);
            UtilView.setTextAppearanceSmall(activity, tvPurchasePrice);
            UtilView.setTextAppearanceSmall(activity, tvEdit);
            UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvItemName);
            UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvSupplierName);
            UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvPurchasePrice);
            UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvEdit);
            if (listview != null)
                listview.addHeaderView(header);
        }
        edSearch.addTextChangedListener(this);
        getPurchasePriceFromServer("", true, false, "0", false, false);
        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(activity, edSearch);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    search = edSearch.getText().toString().trim();
                    getPurchasePriceFromServer(search, true, false, "0", false, false);
                }
                return handled;
            }
        });
    }

    private void getPurchasePriceFromServer(String search, boolean isFirst, boolean isPrev, String pageNumber, boolean isNext, boolean isLast) {
        String url = "";
        if (search.equals("")) {
            url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETINVENTORY_PURCHASEPRICELISTING + "?purchasePricingSearchText=&firstPage=" + isFirst + "&lastPage=" + isLast + "&nextPage=" + isNext + "&pageNo=" + pageNumber + "&prevPage=" + isPrev;
        }
        if (!search.equals("")) {
            url =serverUrl + "/hipos//0/" + Constant.FUNTION_GETINVENTORY_PURCHASEPRICELISTING + "?purchasePricingSearchText=" + search + "&firstPage=" + isFirst + "&lastPage=" + isLast + "&nextPage=" + isNext + "&pageNo=" + pageNumber + "&prevPage=" + isPrev;
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
                            purchasePriceList.clear();
                            List<InventoryPurchasePricing> list = new ArrayList<>();
                            InventoryPurchasePricingData data = gson.fromJson(result.toString(), InventoryPurchasePricingData.class);
                            if (data != null) {
                                setUpView(data);
                                if (listview != null) {
                                    if (data.getData() != null && data.getData().size() > 0) {
                                        list = data.getData();
                                        purchasePriceList.addAll(list);
                                        purchasePriceAdapter = new PurchasePriceAdapter(activity, purchasePriceList);
                                        listview.setAdapter(purchasePriceAdapter);
                                        purchasePriceAdapter.notifyDataSetChanged();
                                    } else {
                                        purchasePriceList.clear();
                                        purchasePriceAdapter = new PurchasePriceAdapter(activity, purchasePriceList);
                                        listview.setAdapter(purchasePriceAdapter);
                                        purchasePriceAdapter.notifyDataSetChanged();
                                        UtilView.showSuccessDialog(getResources().getString(R.string.purchaseprice_notavailbale), activity);
                                    }
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
                getPurchasePriceFromServer(search, true, false, "0", false, false);
                break;
            case R.id.tvFirst:
                pageNo = 0;
                getPurchasePriceFromServer("", true, false, "0", false, false);
                break;
            case R.id.tvPrev:
                if (pageNo > 0) {
                    pageNo = pageNo - 1;
                }
                getPurchasePriceFromServer("", false, true, "" + pageNo, false, false);
                break;
            case R.id.tvNext:
                pageNo = pageNo + 1;
                getPurchasePriceFromServer("", false, false, "" + pageNo, true, false);
                break;
            case R.id.tvLast:
                pageNo = 0;
                getPurchasePriceFromServer("", false, false, "0", false, true);
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
            getPurchasePriceFromServer("", true, false, "0", false, false);
        }

    }

    private void setUpView(InventoryPurchasePricingData data) {
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
                addPurchasePricing();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.RESQUSTCODE_SUPPLIER && resultCode == Activity.RESULT_OK) {
            supplier = (GetSupplier) data.getSerializableExtra("supplier");
            if (supplier.getSupplierName() != null)
                edSupplierName.setText(supplier.getSupplierName());
        }
        if (requestCode == Constant.RESQUSTCODE_ITEMSEARCH && resultCode == Activity.RESULT_OK) {
            itemData = (SelectedItemsList) data.getSerializableExtra("item");
            if (itemData.getItemName() != null)
                edItemName.setText(itemData.getItemName());
        }

    }

    private void addPurchasePricing() {
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.dialog_inventory_purchaseprice);
        dialog.setCancelable(false);
        edSupplierName = dialog.findViewById(R.id.edSupplierName);
        edItemName = dialog.findViewById(R.id.edItemName);
        final EditText edPurchasePrice = dialog.findViewById(R.id.edPurchasePrice);
        Button btnClose = dialog.findViewById(R.id.btnClose);
        Button btnAdd = dialog.findViewById(R.id.btnAdd);
        final ProgressView pgview = dialog.findViewById(R.id.progress_bar);

        if (btnAdd != null)
            btnAdd.setText("Add");

        edSupplierName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, Activity_Supplier.class);
                startActivityForResult(intent, Constant.RESQUSTCODE_SUPPLIER);

            }
        });

        edItemName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getItemData();
            }
        });


        if (btnClose != null)
            btnClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dialog != null) dialog.dismiss();
                }
            });

        if (btnAdd != null)
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String price = edPurchasePrice.getText().toString().trim();
                    String suppliername = edSupplierName.getText().toString().trim();
                    String itemname = edItemName.getText().toString().trim();

                    boolean isUpdatble = true;
                    double doubleprice = 0;
                    if (price == null || price.equals("")) {
                        edPurchasePrice.setError("Price can't be empty");
                    } else {

                        try {
                            doubleprice = Double.parseDouble(price);
                            isUpdatble = true;
                        } catch (NumberFormatException ne) {
                            edPurchasePrice.setError("Please enter valid price");
                            isUpdatble = false;

                        }

                        if (isUpdatble) {
                            String url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETINVENTORY_UPDATEPRUCHASEPRICING;

                            if (!url.equals("")) {
                                isInternetPresent = serviceHandler.isConnectingToInternet();
                                if (isInternetPresent) {
                                    UtilView.showProgessBar(activity, progressBar);
                                    if (pgview != null)
                                        pgview.setVisibility(View.VISIBLE);
                                    InventoryPurchasePricing purchasePriceData = new InventoryPurchasePricing();


                                    purchasePriceData.setSupplierID(supplier.getSupplierId());
                                    purchasePriceData.setSupplierName(suppliername);
                                    purchasePriceData.setItemID(itemData.getItemId());
                                    purchasePriceData.setItemName(itemname);
                                    purchasePriceData.setPrice(doubleprice);


                                    PostDataTask postDataTask = new PostDataTask(activity, new AsyncTaskCompleteListener<String>() {
                                        @Override
                                        public void onTaskComplete(String result) {
                                            if (pgview != null)
                                                pgview.setVisibility(View.GONE);
                                            UtilView.hideProgessBar(activity, progressBar);

                                            if (result != null) {
                                                if (result.toString().equals("No Response Body")) {
                                                    if (dialog != null && dialog.isShowing()) {
                                                        dialog.dismiss();
                                                    }
                                                    getPurchasePriceFromServer("", true, false, "0", false, false);
                                                    UtilView.showToast(activity, "Item already Exist.");

                                                } else {
                                                    if (dialog != null && dialog.isShowing()) {
                                                        dialog.dismiss();
                                                    }
                                                    getPurchasePriceFromServer("", true, false, "0", false, false);
                                                    UtilView.showToast(activity, "Purchase Price Added Successfully");

                                                }

                                                Gson gson = new Gson();
                                                try {

                                                    InventoryPurchasePricing purchasePrice = gson.fromJson(result.toString(), InventoryPurchasePricing.class);
                                                    if (purchasePrice != null) {
                                                        if (dialog != null && dialog.isShowing()) {
                                                            dialog.dismiss();
                                                        }
                                                        getPurchasePriceFromServer("", true, false, "0", false, false);
                                                        UtilView.showToast(activity, "Purchase Price Added Successfully");

                                                    } else {
                                                        UtilView.showToast(activity, "Purchase Price not Added successfully. Please Try Again.");
                                                    }

                                                } catch (Exception e) {
                                                    //UtilView.showErrorDialog(activity.getResources().getString(R.string.response_error), activity);
                                                }
                                            } else {
                                                UtilView.showToast(activity, activity.getResources().getString(R.string.response_error));
                                            }
                                        }
                                    }, false);
                                    postDataTask.execute(new Gson().toJson(purchasePriceData).toString(), url, "");
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
