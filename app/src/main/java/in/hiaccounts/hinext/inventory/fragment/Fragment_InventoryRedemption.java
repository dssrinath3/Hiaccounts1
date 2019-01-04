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

import in.hiaccounts.hinext.inventory.activity.Activity_AddRedemption;
import in.hiaccounts.hinext.inventory.adapter.RedemptionAdapter;
import in.hiaccounts.hinext.inventory.model.redemption.RedemptionSelectData;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_InventoryRedemption extends Fragment implements TextWatcher {
    public static String TAG = Fragment_InventoryRedemption.class.getSimpleName();
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.lvRedemption)
    ListView lvRedemption;
    @BindView(R.id.edSearchRedemption)
    EditText edSearchRedemption;
    @BindView(R.id.llSearchRedemption)
    LinearLayout llSearchRedemption;
    @BindView(R.id.ll)
    RelativeLayout ll;
    @BindView(R.id.ll_searchRedemption)
    LinearLayout ll_searchRedemption;
    Unbinder unbinder;


    private ServiceHandler serviceHandler;
    private Activity activity;
    private Boolean isInternetPresent = false;
    private String redemptionSearch = "", serverUrl;
    List<RedemptionSelectData> redemptionSelectData;


    public static Fragment_InventoryRedemption newInstance() {
        Fragment_InventoryRedemption fragment = new Fragment_InventoryRedemption();
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
        View view = inflater.inflate(R.layout.fragment_inventory_redemption, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponents(view);
        return view;
    }

    private void initComponents(View view) {
        setHasOptionsMenu(true);
        serviceHandler = new ServiceHandler(activity);
        serverUrl= UtilView.getUrl(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.inventory_adapter_redemptionview, null, false);
       /* if (lvRedemption != null)
            lvRedemption.addHeaderView(header);*/
    /*    TextView tv_Redemptionprogram	 = (TextView) header.findViewById(R.id.tv_loyaltyRedemptionprogram);
        TextView tv_Currency = (TextView) header.findViewById(R.id.tv_loyaltyCurrency);
        TextView tv_PointsPerCurrency = (TextView) header.findViewById(R.id.tv_loyaltyPointsPerCurrency);
        TextView tv_Order = (TextView) header.findViewById(R.id.tv_loyaltyOrder);
        TextView tv_PointsPerOrder = (TextView) header.findViewById(R.id.tv_loyaltyPointsPerOrder);
        TextView tv_Product = (TextView) header.findViewById(R.id.tv_loyaltyProduct);
        TextView tv_PointsPerProduct = (TextView) header.findViewById(R.id.tv_loyaltyPointsPerProduct);
        TextView tv_Edit = (TextView) header.findViewById(R.id.tv_LoyaltyEdit);
        tv_Redemptionprogram.setText("Redemption Program");
        tv_Currency.setText("Currency");
        tv_PointsPerCurrency.setText("Points Per Currency");
        tv_Order.setText("Order");
        tv_PointsPerOrder.setText("Points Per Order");
        tv_Product.setText("Product");
        tv_PointsPerProduct.setText("Points Per Product");
        tv_Edit.setText("Action");
        UtilView.setTextAppearanceSmall(activity, tv_Redemptionprogram);
        UtilView.setTextAppearanceSmall(activity, tv_Currency);
        UtilView.setTextAppearanceSmall(activity, tv_PointsPerCurrency);
        UtilView.setTextAppearanceSmall(activity, tv_Order);
        UtilView.setTextAppearanceSmall(activity, tv_PointsPerOrder);
        UtilView.setTextAppearanceSmall(activity, tv_Product);
        UtilView.setTextAppearanceSmall(activity, tv_PointsPerProduct);
        UtilView.setTextAppearanceSmall(activity, tv_Edit);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tv_Redemptionprogram);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tv_Currency);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tv_PointsPerCurrency);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tv_Order);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tv_PointsPerOrder);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tv_Product);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tv_PointsPerProduct);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tv_Edit);
        if (lvRedemption != null)
            lvRedemption.addHeaderView(header);*/



        edSearchRedemption.addTextChangedListener(this);

        getRedemptionFromServer("");

        edSearchRedemption.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(activity, edSearchRedemption);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    redemptionSearch = edSearchRedemption.getText().toString().trim();
                    getRedemptionFromServer(redemptionSearch);
                }
                return handled;
            }
        });
    }

    private void getRedemptionFromServer(String search) {
        String url = "";

        if (search.equals("")){
            url =serverUrl+ "/hipos//1/" + Constant.FUNTION_GETREDEMPTION+ "?searchText=&type=";
        }
        if (!search.equals("")){
            url =serverUrl+ "/hipos//1/" + Constant.FUNTION_GETREDEMPTION+ "?searchText=" + search.replace(" ", "%20")+"&type=";
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
                                redemptionSelectData = new ArrayList<RedemptionSelectData>();
                                JSONArray resultJsonArray = new JSONArray(result.toString());
                                for (int i = 0; i < resultJsonArray.length(); i++) {
                                    JSONObject asJson = resultJsonArray.getJSONObject(i);
                                    Gson gson = new Gson();
                                    RedemptionSelectData data = gson.fromJson(asJson.toString(), RedemptionSelectData.class);
                                    redemptionSelectData.add(data);
                                }
                                if (redemptionSelectData!=null && redemptionSelectData.size()>0){
                                    RedemptionAdapter adapter = new RedemptionAdapter(activity,redemptionSelectData);
                                    lvRedemption.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                }
                                else {
                                    redemptionSelectData.clear();
                                    RedemptionAdapter adapter = new RedemptionAdapter(activity, redemptionSelectData);
                                    lvRedemption.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                    UtilView.showErrorDialog(getResources().getString(R.string.loyalty_notavailbale), activity);

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

    @OnClick({R.id.llSearchRedemption})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llSearchRedemption:
                UtilView.hideSoftKeyboard(activity, edSearchRedemption);
                redemptionSearch = edSearchRedemption.getText().toString().trim();
                getRedemptionFromServer(redemptionSearch);
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
                Intent intent = new Intent(activity, Activity_AddRedemption.class);
                intent.putExtra("callingFor", Constant.CALL_ADDREDEMPTION);
                startActivityForResult(intent, Constant.RESQUSTCODE_ADDREDEMPTION);
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
            UtilView.hideSoftKeyboard(activity, edSearchRedemption);
            redemptionSearch = "";
            getRedemptionFromServer(redemptionSearch);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == Constant.RESQUSTCODE_ADDREDEMPTION) {
            getRedemptionFromServer("");

        }

        if (resultCode == Activity.RESULT_OK && requestCode == Constant.RESQUSTCODE_EDITREDEMPTION) {
            getRedemptionFromServer("");
        }

    }
}
