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
import in.hiaccounts.hinext.inventory.activity.Activity_AddAdvanceDiscountConfiguration;

import in.hiaccounts.hinext.inventory.adapter.AdvanceDiscountConfigAdapter;
import in.hiaccounts.hinext.inventory.model.advancediscountconfiguration.AdvanceDiscountConfigSelectData;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Created by srinath on 12/14/2017.
 */
public class Fragment_InventoryAdvanceDiscountConfiguration extends Fragment implements TextWatcher {

    public static final String TAG = Fragment_InventoryAdvanceDiscountConfiguration.class.getSimpleName();
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.lvAdvanceDiscountConfig)
    ListView lvAdvanceDiscountConfig;
    @BindView(R.id.edSearchAdvanceDiscountConfig)
    EditText edSearchAdvanceDiscountConfig;
    @BindView(R.id.llSearchAdvanceDiscountConfig)
    LinearLayout llSearchAdvanceDiscountConfig;
    @BindView(R.id.ll)
    RelativeLayout ll;
    @BindView(R.id.ll_searchAdvanceDiscountConfig)
    LinearLayout ll_searchAdvanceDiscountConfig;
    Unbinder unbinder;

    private ServiceHandler serviceHandler;
    private Activity activity;
    private Boolean isInternetPresent = false;
    private String advanceDiscountSearch = "", serverUrl;
    List<AdvanceDiscountConfigSelectData> advDiscConfigSelectDataList;

    public static Fragment_InventoryAdvanceDiscountConfiguration newInstance() {

        Fragment_InventoryAdvanceDiscountConfiguration fragment = new Fragment_InventoryAdvanceDiscountConfiguration();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inventory_advance_discount_configuration, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponents(view);
        return view;
    }

    private void initComponents(View view) {
        setHasOptionsMenu(true);
        serviceHandler = new ServiceHandler(activity);
        serverUrl = UtilView.getUrl(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.inventory_adapter_advance_discount_configurationview, null, false);
        TextView tv_customerName = header.findViewById(R.id.tv_customerName);
        TextView tv_itemName = header.findViewById(R.id.tv_itemName);
        TextView tv_promotionName = header.findViewById(R.id.tv_promotionName);
        TextView tv_advanceDiscountConfigEdit = header.findViewById(R.id.tv_Edit);
        tv_customerName.setText("Customer Name");
        tv_itemName.setText("Item Name");
        tv_promotionName.setText("Promotion Name");
        tv_advanceDiscountConfigEdit.setText("Action");
        UtilView.setTextAppearanceSmall(activity, tv_customerName);
        UtilView.setTextAppearanceSmall(activity, tv_itemName);
        UtilView.setTextAppearanceSmall(activity, tv_promotionName);
        UtilView.setTextAppearanceSmall(activity, tv_advanceDiscountConfigEdit);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tv_customerName);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tv_itemName);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tv_promotionName);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tv_advanceDiscountConfigEdit);
        if (lvAdvanceDiscountConfig != null)
            lvAdvanceDiscountConfig.addHeaderView(header);
        edSearchAdvanceDiscountConfig.addTextChangedListener(this);

        getAdvanceDiscountConfigFromServer("");

        edSearchAdvanceDiscountConfig.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(activity, edSearchAdvanceDiscountConfig);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    advanceDiscountSearch = edSearchAdvanceDiscountConfig.getText().toString().trim();
                    getAdvanceDiscountConfigFromServer(advanceDiscountSearch);
                }
                return handled;
            }
        });

    }

    private void getAdvanceDiscountConfigFromServer(String search) {
        String url = "";
        if (search.equals("")) {
            url =serverUrl+ "/hipos//2/" + Constant.FUNTION_GETSALESDISCOUNTCONFIG+ "?salesDiscountConfigurationSearchText=&type=";
        }
        if (!search.equals("")) {
            url =serverUrl+ "/hipos//2/" + Constant.FUNTION_GETSALESDISCOUNTCONFIG+ "?salesDiscountConfigurationSearchText=" + search.replace(" ", "%20")+"&type=";
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
                                advDiscConfigSelectDataList = new ArrayList<AdvanceDiscountConfigSelectData>();
                                JSONArray resultJsonArray = new JSONArray(result.toString());
                                for (int i = 0; i < resultJsonArray.length(); i++) {
                                    JSONObject asJson = resultJsonArray.getJSONObject(i);
                                    Gson gson = new Gson();
                                    AdvanceDiscountConfigSelectData data = gson.fromJson(asJson.toString(), AdvanceDiscountConfigSelectData.class);
                                    advDiscConfigSelectDataList.add(data);
                                }
                                if (advDiscConfigSelectDataList!=null && advDiscConfigSelectDataList.size()>0){
                                    AdvanceDiscountConfigAdapter adapter = new AdvanceDiscountConfigAdapter(activity,advDiscConfigSelectDataList);
                                    lvAdvanceDiscountConfig.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                }
                                else {
                                    advDiscConfigSelectDataList.clear();
                                    AdvanceDiscountConfigAdapter adapter = new AdvanceDiscountConfigAdapter(activity, advDiscConfigSelectDataList);
                                    lvAdvanceDiscountConfig.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                    UtilView.showErrorDialog(getResources().getString(R.string.advancediscountconfig_notavailbale), activity);

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
    @OnClick({R.id.llSearchAdvanceDiscountConfig})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llSearchAdvanceDiscountConfig:
                UtilView.hideSoftKeyboard(activity, edSearchAdvanceDiscountConfig);
                advanceDiscountSearch = edSearchAdvanceDiscountConfig.getText().toString().trim();
                getAdvanceDiscountConfigFromServer(advanceDiscountSearch);
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
                Intent intent = new Intent(activity, Activity_AddAdvanceDiscountConfiguration.class);
                intent.putExtra("callingFor", Constant.CALL_ADDADVANCEDISCOUNTCONFIG);
                startActivityForResult(intent, Constant.RESQUSTCODE_ADDADVANCESDISCOUNTCONFIG);
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
            UtilView.hideSoftKeyboard(activity, edSearchAdvanceDiscountConfig);
            advanceDiscountSearch = "";
            getAdvanceDiscountConfigFromServer(advanceDiscountSearch);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == Constant.RESQUSTCODE_ADDADVANCESDISCOUNTCONFIG) {
            getAdvanceDiscountConfigFromServer("");
        }

        if (resultCode == Activity.RESULT_OK && requestCode == Constant.RESQUSTCODE_EDITADVANCESDISCOUNTCONFIG) {
            getAdvanceDiscountConfigFromServer("");
        }

    }
}
