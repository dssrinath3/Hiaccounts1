package in.hiaccounts.hinext.controlpanel.fragment.openingbalance;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
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
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.controlpanel.activity.Activity_OpeningBalance_Inventory;
import in.hiaccounts.hinext.controlpanel.adapter.OpeningInventoryAdapter;
import in.hiaccounts.hinext.controlpanel.model.openingbalance.InventoryData;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 2/9/2017.
 */

public class Fragment_InventoryOpening extends Fragment implements TextWatcher {


    public static String TAG = Fragment_InventoryOpening.class.getSimpleName();
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.fabAdd)
    FloatingActionButton fabAdd;
    @BindView(R.id.edSearch)
    EditText edSearch;
    @BindView(R.id.llSearch)
    LinearLayout llSearch;
    @BindView(R.id.ll)
    LinearLayout ll;
    Unbinder unbinder;

    private ServiceHandler serviceHandler;
    private Activity mActivity;
    private Boolean isInternetPresent = false;
    private List<Object> inventoryList = new ArrayList<Object>();
    private OpeningInventoryAdapter inventoryAdapter;
    private String search = "", serverUrl;


    public static Fragment_InventoryOpening newInstance() {
        Fragment_InventoryOpening fragment = new Fragment_InventoryOpening();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;

    }

    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Returning the layout file after inflating
        View view = inflater.inflate(R.layout.fragment_inventoryopening, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponents(view);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.fabAdd, R.id.llSearch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fabAdd:
                Intent intent = new Intent(mActivity, Activity_OpeningBalance_Inventory.class);
                intent.putExtra("callingFor", Constant.CALL_ADDINVENTORUOPENING);
                startActivityForResult(intent, Constant.RESQUSTCODE_ADDINVENTORYOPENING);
                break;
            case R.id.llSearch:
                search = edSearch.getText().toString().trim();
                getInvListFromServer();
                break;
        }
    }

    private void initComponents(View view) {
        serverUrl = UtilView.getUrl(mActivity);

        serviceHandler = new ServiceHandler(mActivity);
        LayoutInflater inflater = mActivity.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.oepningbalance_adapter_inventory, null, false);


        TextView tvItemName = header.findViewById(R.id.tvItemName);
        TextView tvInventoryLocation = header.findViewById(R.id.tvInventoryLocation);
        TextView tvItemQuantity = header.findViewById(R.id.tvItemQuantity);
        TextView tvOpeningBalance = header.findViewById(R.id.tvOpeningBalance);
        TextView tvCalPrice = header.findViewById(R.id.tvCalPrice);


        tvItemName.setText("Name");
        tvInventoryLocation.setText("Inv. Location");
        tvItemQuantity.setText("Initial Qty");
        tvOpeningBalance.setText("Opening Balance");
        tvCalPrice.setText("Cal. Price");

        UtilView.setTextAppearanceSmall(mActivity, tvItemName);
        UtilView.setTextAppearanceSmall(mActivity, tvInventoryLocation);
        UtilView.setTextAppearanceSmall(mActivity, tvItemQuantity);
        UtilView.setTextAppearanceSmall(mActivity, tvOpeningBalance);
        UtilView.setTextAppearanceSmall(mActivity, tvCalPrice);

        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvItemName);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvInventoryLocation);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvItemQuantity);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvOpeningBalance);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvCalPrice);

        UtilView.setTexttypeFace(null, Typeface.BOLD, tvItemName);
        UtilView.setTexttypeFace(null, Typeface.BOLD, tvInventoryLocation);
        UtilView.setTexttypeFace(null, Typeface.BOLD, tvItemQuantity);
        UtilView.setTexttypeFace(null, Typeface.BOLD, tvOpeningBalance);
        UtilView.setTexttypeFace(null, Typeface.BOLD, tvCalPrice);

        if (listview != null)
            listview.addHeaderView(header);
        edSearch.addTextChangedListener(this);

        getInvListFromServer();

        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    search = edSearch.getText().toString().trim();
                    getInvListFromServer();
                }
                return handled;
            }
        });


    }

    private void getInvListFromServer() {
        String url = serverUrl + "/hipos//1/" + Constant.FUNTION_GETINVENTORYOPENING;

        if (serverUrl != null) {
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {
                // prepare the Request
                UtilView.showProgessBar(mActivity, progressBar);
                GetDataTask task = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
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

                                Gson gson = new Gson();
                                inventoryList.clear();
                                List<InventoryData> list = new ArrayList<>();
                                try {
                                    JSONArray jsonArray = new JSONArray(result.toString());
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                                        InventoryData inventoryData = gson.fromJson(jsonObject.toString(), InventoryData.class);
                                        list.add(inventoryData);

                                    }
                                    if (list.size() > 0) {


                                        inventoryList.addAll(list);
                                        inventoryAdapter = new OpeningInventoryAdapter(mActivity, inventoryList);
                                        listview.setAdapter(inventoryAdapter);
                                        inventoryAdapter.notifyDataSetChanged();
                                    } else {
                                        fabAdd.setVisibility(View.VISIBLE);
                                        inventoryList.clear();
                                        inventoryAdapter = new OpeningInventoryAdapter(mActivity, inventoryList);
                                        listview.setAdapter(inventoryAdapter);
                                        inventoryAdapter.notifyDataSetChanged();
                                        UtilView.showErrorDialog(getResources().getString(R.string.inventory_notavailbale), mActivity);

                                    }


                                } catch (Exception e) {
                                    UtilView.showLogCat(TAG, "TaskCompleteListener Exception " + e.toString());
                                }
                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.error_null), mActivity);
                        }
                    }
                }, false);
                task.execute(url + search.replace(" ", "%20"), "");
                //task.execute(url, "");
            }
            if (!isInternetPresent) {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
            }
        } else {
            UtilView.gotToLogin(mActivity);
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
            UtilView.hideSoftKeyboard(mActivity, edSearch);
            search = "";
            getInvListFromServer();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && requestCode == Constant.RESQUSTCODE_ADDINVENTORYOPENING) {
            getInvListFromServer();
        }

    }
}
