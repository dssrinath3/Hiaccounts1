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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.hiaccounts.R;
import in.hiaccounts.hinext.inventory.activity.Activity_InventoryLocationTransfer;
import in.hiaccounts.hinext.inventory.adapter.InventoryLocationTransferAdapter;
import in.hiaccounts.hinext.inventory.model.inventorylocationtransfer.InventoryLocationTransfer;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 2/9/2017.
 */

public class Fragment_InventoryLocationTransfer extends Fragment implements TextWatcher {

    public static final String TAG = Fragment_InventoryLocationTransfer.class.getSimpleName();
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
    Unbinder unbinder;
    List<InventoryLocationTransfer> listData;
    private Activity activity;
    private ServiceHandler serviceHandler;
    private Boolean isInternetPresent = false;
    private List<Object> locationList = new ArrayList<Object>();
    private InventoryLocationTransferAdapter adapter;
    private String search = "",serverUrl;

    public static Fragment_InventoryLocationTransfer newInstance() {

        Fragment_InventoryLocationTransfer fragment = new Fragment_InventoryLocationTransfer();
        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_inventorylocationtransfer, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponents(view);
        return view;
    }

    private void initComponents(View view) {
        setHasOptionsMenu(true);
        serverUrl= UtilView.getUrl(activity);
        serviceHandler = new ServiceHandler(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.inventory_adapter_inventorylocationtransferview, null, false);
        TextView tvTransferredDate = header.findViewById(R.id.tvTransferredDate);
        TextView tvItemName = header.findViewById(R.id.tvItemName);
        TextView tvItemCode = header.findViewById(R.id.tvItemCode);
        TextView tvFromLocation = header.findViewById(R.id.tvFromLocation);
        TextView tvToLocation = header.findViewById(R.id.tvToLocation);
        TextView tvQuantity = header.findViewById(R.id.tvQuantity);

        UtilView.setTextAppearanceSmall(activity, tvTransferredDate);
        UtilView.setTextAppearanceSmall(activity, tvItemName);
        UtilView.setTextAppearanceSmall(activity, tvItemCode);
        UtilView.setTextAppearanceSmall(activity, tvFromLocation);
        UtilView.setTextAppearanceSmall(activity, tvToLocation);
        UtilView.setTextAppearanceSmall(activity, tvQuantity);

        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvTransferredDate);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvItemName);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvItemCode);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvFromLocation);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvToLocation);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvQuantity);
        if (listview != null)
            listview.addHeaderView(header);
        edSearch.addTextChangedListener(this);
        getLocationTransferFromServer("");
        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(activity, edSearch);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    search = edSearch.getText().toString().trim();
                    getLocationTransferFromServer(search);
                }
                return handled;
            }
        });


    }

    private void getLocationTransferFromServer(String search) {
        String url = "";
        if (search.equals("")) {
            url = serverUrl + "/inventory/" + Constant.FUNTION_GETINVENTORYLOCATIONTRANSFER + "?itemSearchText=";
        }
        if (!search.equals("")) {
            url =serverUrl+ "/inventory/" + Constant.FUNTION_GETINVENTORYLOCATIONTRANSFER + "?itemSearchText=" + search.replace(" ", "%20");
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

                                listData = new ArrayList<>();

                                JSONArray jsonArray = new JSONArray(result.toString());
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonInvoiceString = jsonArray.getJSONObject(i);
                                    InventoryLocationTransfer data = gson.fromJson(jsonInvoiceString.toString(), InventoryLocationTransfer.class);
                                    listData.add(data);
                                }
                                if (listData != null && listData.size() > 0) {
                                    adapter = new InventoryLocationTransferAdapter(activity, listData);
                                    listview.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                } else {
                                    listData.clear();
                                    adapter = new InventoryLocationTransferAdapter(activity, listData);
                                    listview.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                    UtilView.showSuccessDialog(getResources().getString(R.string.inventorylocation_notavailbale), activity);
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

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (editable.toString().equals("")) {
            UtilView.hideSoftKeyboard(activity, edSearch);
            search = "";
            getLocationTransferFromServer(search);
        }
    }


    @OnClick({R.id.llSearch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llSearch:
                UtilView.hideSoftKeyboard(activity, edSearch);
                search = edSearch.getText().toString().trim();
                getLocationTransferFromServer(search);
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
                Intent intent = new Intent(activity, Activity_InventoryLocationTransfer.class);
                startActivityForResult(intent, Constant.RESQUSTCODE_ADDLOCATIONTRANSFER);
                break;


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.RESQUSTCODE_ADDLOCATIONTRANSFER && resultCode == Activity.RESULT_OK) {
            getLocationTransferFromServer("");

        }
    }
}
