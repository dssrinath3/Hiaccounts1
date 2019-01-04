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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.hiaccounts.R;
import in.hiaccounts.hinext.inventory.activity.Activity_AddItemCommission;
import in.hiaccounts.hinext.inventory.adapter.ItemCommissionAdapter;
import in.hiaccounts.hinext.inventory.model.itemcommission.ItemCommissionSelectData;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Srinath on 13/12/2017.
 */

public class Fragment_InventoryItemCommission extends Fragment implements TextWatcher {

    public static final String TAG = Fragment_InventoryItemCommission.class.getSimpleName();
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.lvItemCommission)
    ListView lvItemCommission;
    @BindView(R.id.edSearchItemCommission)
    EditText edSearchItemCommission;
    @BindView(R.id.llSearchItemCommission)
    LinearLayout llSearchItemCommission;
    @BindView(R.id.ll)
    RelativeLayout ll;
    @BindView(R.id.ll_searchItemCommission)
    LinearLayout ll_searchItemCommission;
    Unbinder unbinder;
    List<ItemCommissionSelectData> itemCommissionList;
    ItemCommissionSelectData itemCommissionData;
    private ServiceHandler serviceHandler;
    private Activity activity;
    private Boolean isInternetPresent = false;
    private String itemCommissionSearch = "", serverUrl;

    public static Fragment_InventoryItemCommission newInstance() {

        Fragment_InventoryItemCommission fragment = new Fragment_InventoryItemCommission();
        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_inventoryitemcommission, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponents(view);
      
        return view;
    }

    private void initComponents(View view) {
        setHasOptionsMenu(true);
        serviceHandler = new ServiceHandler(activity);
        serverUrl = UtilView.getUrl(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.inventory_adapter_item_commissionview, null, false);
        TextView tv_itemName = header.findViewById(R.id.tv_itemName);
        TextView tv_itemCommissionType = header.findViewById(R.id.tv_itemCommissionType);
        TextView tv_itemCommissionValue = header.findViewById(R.id.tv_itemCommissionValue);
        TextView tv_itemCommissionEdit = header.findViewById(R.id.tv_itemCommissionEdit);
        tv_itemName.setText("Item Name");
        tv_itemCommissionType.setText("Commission Type");
        tv_itemCommissionValue.setText("Value");
        tv_itemCommissionEdit.setText("Action");
        UtilView.setTextAppearanceSmall(activity, tv_itemName);
        UtilView.setTextAppearanceSmall(activity, tv_itemCommissionType);
        UtilView.setTextAppearanceSmall(activity, tv_itemCommissionValue);
        UtilView.setTextAppearanceSmall(activity, tv_itemCommissionEdit);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tv_itemName);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tv_itemCommissionType);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tv_itemCommissionValue);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tv_itemCommissionEdit);
        if (lvItemCommission != null)
            lvItemCommission.addHeaderView(header);
        edSearchItemCommission.addTextChangedListener(this);

        getItemCommissionFromServer("");

        edSearchItemCommission.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(activity, edSearchItemCommission);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    itemCommissionSearch = edSearchItemCommission.getText().toString().trim();
                    getItemCommissionFromServer(itemCommissionSearch);
                }
                return handled;
            }
        });
        lvItemCommission.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (view.getId() == R.id.imgviewEdit) {
                    if (itemCommissionList != null) {
                        ItemCommissionSelectData itemCommissionSelectData = itemCommissionList.get(position);
                        Intent intent = new Intent(activity, Activity_AddItemCommission.class);
                        intent.putExtra("itemCommissionData", itemCommissionSelectData);
                        intent.putExtra("callingFor", Constant.CALL_EDITITEMCOMMISSION);
                        startActivityForResult(intent, Constant.RESQUSTCODE_EDITITEMCOMMISSION);
                    }

                }
            }
        });


    }

    private void getItemCommissionFromServer(String search) {
        String url = "";
        if (search.equals("")) {
            url = serverUrl + "/hipos//1/" + Constant.FUNTION_GETITEMCOMMISSION + "?itemCommissionSearchText=&type=";
        }
        if (!search.equals("")) {
            url = serverUrl + "/hipos//1/" + Constant.FUNTION_GETITEMCOMMISSION + "?itemCommissionSearchText=" + search.replace(" ", "%20") + "&type=";
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
                            try {
                                itemCommissionList = new ArrayList<ItemCommissionSelectData>();
                                JSONArray resultJsonArray = new JSONArray(result.toString());
                                for (int i = 0; i < resultJsonArray.length(); i++) {
                                    JSONObject asJson = resultJsonArray.getJSONObject(i);
                                    Gson gson = new Gson();
                                    ItemCommissionSelectData data = gson.fromJson(asJson.toString(), ItemCommissionSelectData.class);
                                    itemCommissionList.add(data);
                                }
                                if (itemCommissionList!=null && itemCommissionList.size()>0){
                                    ItemCommissionAdapter adapter = new ItemCommissionAdapter(activity,itemCommissionList);
                                    lvItemCommission.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                }
                                else {
                                    itemCommissionList.clear();
                                    ItemCommissionAdapter adapter = new ItemCommissionAdapter(activity, itemCommissionList);
                                    lvItemCommission.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                    UtilView.showErrorDialog(getResources().getString(R.string.itemcommision_notavailbale), activity);

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

    @OnClick({R.id.llSearchItemCommission})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llSearchItemCommission:
                UtilView.hideSoftKeyboard(activity, edSearchItemCommission);
                itemCommissionSearch = edSearchItemCommission.getText().toString().trim();
                getItemCommissionFromServer(itemCommissionSearch);
                break;

        }
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
                Intent intent = new Intent(activity, Activity_AddItemCommission.class);
                intent.putExtra("callingFor", Constant.CALL_ADDITEMCOMMISSION);
                startActivityForResult(intent, Constant.RESQUSTCODE_ADDITEMCOMMISSION);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
            UtilView.hideSoftKeyboard(activity, edSearchItemCommission);
            itemCommissionSearch = "";
            getItemCommissionFromServer(itemCommissionSearch);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == Constant.RESQUSTCODE_ADDITEMCOMMISSION) {
            getItemCommissionFromServer("");
        }

        if (resultCode == Activity.RESULT_OK && requestCode == Constant.RESQUSTCODE_EDITITEMCOMMISSION) {
            getItemCommissionFromServer("");
        }

    }
}
