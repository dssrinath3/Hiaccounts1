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
import in.hiaccounts.hinext.inventory.activity.Activity_AddSalesDiscountConfiguration;
import in.hiaccounts.hinext.inventory.adapter.SalesDiscountConfigAdapter;
import in.hiaccounts.hinext.inventory.model.salesdiscountconfiguration.SalesDiscountConfigData;
import in.hiaccounts.hinext.inventory.model.salesdiscountconfiguration.SalesDiscountConfigDataDatum;
import in.hiaccounts.hinext.inventory.model.salesdiscountconfiguration.SalesDiscountConfigSelectData;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Created by srinath on 12/14/2017.
 */

public class Fragment_InventorySalesDiscountConfig extends Fragment implements TextWatcher {

    public static final String TAG = Fragment_InventorySalesDiscountConfig.class.getSimpleName();
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.lvSalesDiscountConfig)
    ListView lvSalesDiscountConfig;
    @BindView(R.id.edSearchSalesDiscountConfig)
    EditText edSearchSalesDiscountConfig;
    @BindView(R.id.llSearchSalesDiscountConfig)
    LinearLayout llSearchSalesDiscountConfig;
    @BindView(R.id.ll)
    RelativeLayout ll;
    @BindView(R.id.ll_searchSalesDiscountConfig)
    LinearLayout ll_searchSalesDiscountConfig;
    Unbinder unbinder;
    List<SalesDiscountConfigDataDatum> salesDiscountConfigList;
    private ServiceHandler serviceHandler;
    private Activity activity;
    private Boolean isInternetPresent = false;
    private String salesDiscountSearch = "", serverUrl;

    public static Fragment_InventorySalesDiscountConfig newInstance() {
        Fragment_InventorySalesDiscountConfig fragment = new Fragment_InventorySalesDiscountConfig();
        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inventorysalesdiscountconfig, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponents(view);
        return view;
    }

    private void initComponents(View view) {
        setHasOptionsMenu(true);
        serviceHandler = new ServiceHandler(activity);
        serverUrl = UtilView.getUrl(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.inventory_adapter_sales_discount_configurationview, null, false);
        TextView tv_itemName = header.findViewById(R.id.tv_itemName);
        TextView tv_salesDiscountConfigType = header.findViewById(R.id.tv_discountType);
        TextView tv_salesDiscountConfigValue = header.findViewById(R.id.tv_discountValue);
        TextView tv_salesDiscountConfigEdit = header.findViewById(R.id.tv_discountEdit);
        tv_itemName.setText("Item Name");
        tv_salesDiscountConfigType.setText("Discount Type");
        tv_salesDiscountConfigValue.setText("Value");
        tv_salesDiscountConfigEdit.setText("Action");
        UtilView.setTextAppearanceSmall(activity, tv_itemName);
        UtilView.setTextAppearanceSmall(activity, tv_salesDiscountConfigType);
        UtilView.setTextAppearanceSmall(activity, tv_salesDiscountConfigValue);
        UtilView.setTextAppearanceSmall(activity, tv_salesDiscountConfigEdit);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tv_itemName);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tv_salesDiscountConfigType);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tv_salesDiscountConfigValue);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tv_salesDiscountConfigEdit);
        if (lvSalesDiscountConfig != null)
            lvSalesDiscountConfig.addHeaderView(header);
        edSearchSalesDiscountConfig.addTextChangedListener(this);

        getSalesDiscountConfigFromServer("");

        edSearchSalesDiscountConfig.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(activity, edSearchSalesDiscountConfig);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    salesDiscountSearch = edSearchSalesDiscountConfig.getText().toString().trim();
                    getSalesDiscountConfigFromServer(salesDiscountSearch);
                }
                return handled;
            }
        });

        lvSalesDiscountConfig.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (view.getId() == R.id.imgviewEdit) {
                    if (salesDiscountConfigList != null) {
                        SalesDiscountConfigDataDatum salesDiscountConfigSelectData = salesDiscountConfigList.get(position);
                        Intent intent = new Intent(activity, Activity_AddSalesDiscountConfiguration.class);
                        intent.putExtra("salesDiscountConfigData", salesDiscountConfigSelectData);
                        intent.putExtra("callingFor", Constant.CALL_EDITSALESDISCOUNTCONFIG);
                        startActivityForResult(intent, Constant.RESQUSTCODE_EDITSALESDISCOUNTCONFIG);
                    }

                }
            }
        });
    }

    private void getSalesDiscountConfigFromServer(String search) {
        String url = "";
        if (search.equals("")) {
            url = serverUrl + "/hipos//1/" + Constant.FUNTION_GETSALESDISCOUNTCONFIG + "?salesDiscountConfigurationSearchText=&type=Active&name=&pageValue=";
        }
        if (!search.equals("")) {
            url = serverUrl + "/hipos//1/" + Constant.FUNTION_GETSALESDISCOUNTCONFIG + "?salesDiscountConfigurationSearchText=" + search.replace(" ", "%20") + "&type=Active&name=&pageValue=";
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
                        if (result.toString() != null) {
                            try {
                                salesDiscountConfigList = new ArrayList<SalesDiscountConfigDataDatum>();

                                    Gson gson = new Gson();
                                SalesDiscountConfigData data = gson.fromJson(result.toString(), SalesDiscountConfigData.class);
                                if (data!=null){
                                    salesDiscountConfigList = data.getData();

                                    if (salesDiscountConfigList!=null && salesDiscountConfigList.size()>0){
                                        SalesDiscountConfigAdapter adapter = new SalesDiscountConfigAdapter(activity,salesDiscountConfigList);
                                        lvSalesDiscountConfig.setAdapter(adapter);
                                        adapter.notifyDataSetChanged();
                                    }
                                    else {
                                        salesDiscountConfigList.clear();
                                        SalesDiscountConfigAdapter adapter = new SalesDiscountConfigAdapter(activity, salesDiscountConfigList);
                                        lvSalesDiscountConfig.setAdapter(adapter);
                                        adapter.notifyDataSetChanged();
                                        UtilView.showErrorDialog(getResources().getString(R.string.salesdiscountconfig_notavailbale), activity);

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
        unbinder.unbind();
    }

    @OnClick({R.id.llSearchSalesDiscountConfig})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llSearchSalesDiscountConfig:
                UtilView.hideSoftKeyboard(activity, edSearchSalesDiscountConfig);
                salesDiscountSearch = edSearchSalesDiscountConfig.getText().toString().trim();
                getSalesDiscountConfigFromServer(salesDiscountSearch);
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
                Intent intent = new Intent(activity, Activity_AddSalesDiscountConfiguration.class);
                intent.putExtra("callingFor", Constant.CALL_ADDSALESDISCOUNTCONFIG);
                startActivityForResult(intent, Constant.RESQUSTCODE_ADDSALESDISCOUNTCONFIG);
                break;
        }
        return super.onOptionsItemSelected(item);
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
            UtilView.hideSoftKeyboard(activity, edSearchSalesDiscountConfig);
            salesDiscountSearch = "";
            getSalesDiscountConfigFromServer(salesDiscountSearch);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == Constant.RESQUSTCODE_ADDSALESDISCOUNTCONFIG) {
            getSalesDiscountConfigFromServer("");
        }

        if (resultCode == Activity.RESULT_OK && requestCode == Constant.RESQUSTCODE_EDITSALESDISCOUNTCONFIG) {
            getSalesDiscountConfigFromServer("");
        }

    }
}
