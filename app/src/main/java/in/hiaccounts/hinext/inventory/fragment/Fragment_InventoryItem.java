package in.hiaccounts.hinext.inventory.fragment;

import android.app.Activity;
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
import in.hiaccounts.hinext.inventory.adapter.ViewItemAdapter;
import in.hiaccounts.hinext.inventory.model.InventoryItemData;
import in.hiaccounts.hinext.item.activity.Activity_AddItem;
import in.hiaccounts.hinext.item.model.ItemModelData;
import in.hiaccounts.hinext.item.model.SelectedItemsList;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 2/9/2017.
 */

public class Fragment_InventoryItem extends Fragment implements TextWatcher {

    public static final String TAG = Fragment_InventoryItem.class.getSimpleName();
    Unbinder unbinder;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.listviewItems)
    ListView listviewItems;
    @BindView(R.id.edSearchItem)
    EditText edSearchItem;
    @BindView(R.id.llSearchItem)
    LinearLayout llSearchItem;
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
    private ServiceHandler serviceHandler;
    private Activity activity;
    private Boolean isInternetPresent = false;
    private List<Object> list = new ArrayList<Object>();
    private ViewItemAdapter itemAdapter;
    private String itemSearch = "",serverUrl;
    private int pageNo = 0;

    public static Fragment_InventoryItem newInstance() {
        Fragment_InventoryItem fragment = new Fragment_InventoryItem();
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
        View view = inflater.inflate(R.layout.fragment_inventoryitem, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponents(view);
        return view;
    }

    private void initComponents(View view) {
        setHasOptionsMenu(true);
        serviceHandler = new ServiceHandler(activity);
        serverUrl= UtilView.getUrl(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.inventory_adapter_itemview, null, false);
        TextView tv_itmecode = header.findViewById(R.id.tv_itmecode);
        TextView tv_itemname = header.findViewById(R.id.tv_itemname);
        TextView tv_itemdescription = header.findViewById(R.id.tv_itemdescription);
        TextView tv_itemEdit = header.findViewById(R.id.tv_itemEdit);
        tv_itmecode.setText("Item Code");
        tv_itemname.setText("Name");
        tv_itemdescription.setText("Available Qty");
        tv_itemEdit.setText("Edit");
        UtilView.setTextAppearanceSmall(activity, tv_itmecode);
        UtilView.setTextAppearanceSmall(activity, tv_itemname);
        UtilView.setTextAppearanceSmall(activity, tv_itemdescription);
        UtilView.setTextAppearanceSmall(activity, tv_itemEdit);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tv_itmecode);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tv_itemname);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tv_itemdescription);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tv_itemEdit);
        if (listviewItems != null)
            listviewItems.addHeaderView(header);
        edSearchItem.addTextChangedListener(this);

        getItemFromServer("", true, false, "0", false, false);

        edSearchItem.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(activity, edSearchItem);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    itemSearch = edSearchItem.getText().toString().trim();
                    getItemFromServer(itemSearch, true, false, "0", false, false);
                }
                return handled;
            }
        });

    }

    private void getItemFromServer(String search, boolean isFirst, boolean isPrev, String pageNumber, boolean isNext, boolean isLast) {
        String url = "";
        if (search.equals("")) {
            url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETITEMLISTINVENTORY + "?itemSearchText=&type=Active&pageValue=&firstPage=" + isFirst + "&lastPage=" + isLast + "&nextPage=" + isNext + "&pageNo=" + pageNumber + "&prevPage=" + isPrev;
        }
        if (!search.equals("")) {
            url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETITEMLISTINVENTORY + "?itemSearchText=" + search.replace(" ", "%20") + "&type=Active&pageValue=&firstPage=" + isFirst + "&lastPage=" + isLast + "&nextPage=" + isNext + "&pageNo=" + pageNumber + "&prevPage=" + isPrev;
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

                            list.clear();
                            List<ItemModelData> itemList = new ArrayList<>();
                            InventoryItemData data = gson.fromJson(result.toString(), InventoryItemData.class);
                            if (data != null) {
                                setUpView(data);
                                if (listviewItems != null) {
                                    if (data.getData() != null && data.getData().size() > 0) {
                                        itemList = data.getData();
                                        list.addAll(itemList);
                                        itemAdapter = new ViewItemAdapter(activity, list);
                                        listviewItems.setAdapter(itemAdapter);
                                        itemAdapter.notifyDataSetChanged();
                                    } else {
                                        list.clear();
                                        itemAdapter = new ViewItemAdapter(activity, list);
                                        listviewItems.setAdapter(itemAdapter);
                                        itemAdapter.notifyDataSetChanged();
                                        UtilView.showSuccessDialog(getResources().getString(R.string.items_notavailbale), activity);
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

    @OnClick({R.id.tvFirst, R.id.tvPrev, R.id.tvNext, R.id.tvLast, R.id.llSearchItem})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llSearchItem:
                itemSearch = edSearchItem.getText().toString().trim();
                getItemFromServer(itemSearch, true, false, "0", false, false);
                break;
            case R.id.tvFirst:
                pageNo = 0;
                getItemFromServer("", true, false, "0", false, false);
                break;
            case R.id.tvPrev:
                if (pageNo > 0) {
                    pageNo = pageNo - 1;
                }
                getItemFromServer("", false, true, "" + pageNo, false, false);
                break;
            case R.id.tvNext:
                pageNo = pageNo + 1;
                getItemFromServer("", false, false, "" + pageNo, true, false);
                break;
            case R.id.tvLast:
                pageNo = 0;
                getItemFromServer("", false, false, "0", false, true);
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
            UtilView.hideSoftKeyboard(activity, edSearchItem);
            itemSearch = "";
            getItemFromServer(itemSearch, true, false, "0", false, false);
        }
    }

    private void setUpView(InventoryItemData data) {
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
                Intent intent = new Intent(activity, Activity_AddItem.class);
                intent.putExtra("callingfrom", Constant.NAVIGATION_INVENTORY_ITEM);
                startActivityForResult(intent, Constant.RESQUSTCODE_ADDITEM);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constant.RESQUSTCODE_ADDITEM && resultCode == Activity.RESULT_OK){
            getItemFromServer("", true, false, "0", false, false);

        }


    }
}
