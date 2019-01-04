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
import in.hiaccounts.hinext.inventory.activity.Activity_AddAssests;
import in.hiaccounts.hinext.inventory.adapter.AssestsAdapter;
import in.hiaccounts.hinext.inventory.model.assests.AssestsSelectData;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * created by srinath 29-11-2017.
 */
public class Fragment_InventoryAssests extends Fragment implements TextWatcher {

    public static String TAG = Fragment_InventoryAssests.class.getSimpleName();
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.lvAssests)
    ListView lvAssests;
    @BindView(R.id.edSearchAssests)
    EditText edSearchAssests;
    @BindView(R.id.llSearchAssests)
    LinearLayout llSearchAssests;

    Unbinder unbinder;
    List<AssestsSelectData> assestsDataList;
    AssestsAdapter adapter;
    private ServiceHandler serviceHandler;
    private Activity activity;
    private Boolean isInternetPresent = false;
    private String assetsSearch = "", serverUrl;

    public static Fragment_InventoryAssests newInstance() {
        Fragment_InventoryAssests fragment = new Fragment_InventoryAssests();
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
        View view = inflater.inflate(R.layout.fragment_inventory_assests, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponents(view);
        return view;
    }

    private void initComponents(View view) {
        setHasOptionsMenu(true);
        serviceHandler = new ServiceHandler(activity);
        serverUrl= UtilView.getUrl(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.inventory_adapter_assestsview, null, false);
        TextView tv_assestscode = header.findViewById(R.id.tv_assestscode);
        TextView tv_assestsname = header.findViewById(R.id.tv_assestsname);
        TextView tv_assestsAvailbality = header.findViewById(R.id.tv_assestsstock);
        TextView tv_assestsEdit = header.findViewById(R.id.tv_assestsEdit);
        tv_assestscode.setText("Assests Code");
        tv_assestsname.setText("Assests Name");
        tv_assestsAvailbality.setText("Available Qty");
        tv_assestsEdit.setText("Action");
        UtilView.setTextAppearanceSmall(activity, tv_assestscode);
        UtilView.setTextAppearanceSmall(activity, tv_assestsname);
        UtilView.setTextAppearanceSmall(activity, tv_assestsAvailbality);
        UtilView.setTextAppearanceSmall(activity, tv_assestsEdit);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tv_assestscode);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tv_assestsname);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tv_assestsAvailbality);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tv_assestsEdit);
        if (lvAssests != null)
            lvAssests.addHeaderView(header);
        edSearchAssests.addTextChangedListener(this);

        getAssestsFromServer("");

        edSearchAssests.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(activity, edSearchAssests);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    assetsSearch = edSearchAssests.getText().toString().trim();
                    getAssestsFromServer(assetsSearch);
                }
                return handled;
            }
        });

    }

    private void getAssestsFromServer(String search) {
        String url = "";
        if (search.equals("")) {
            url = serverUrl + "/hipos//1/" + Constant.FUNTION_GETASSESTSLISTINVENTORY + "?assestSearchText=&type=";
        }
        if (!search.equals("")) {
            url = serverUrl + "/hipos//1/" + Constant.FUNTION_GETASSESTSLISTINVENTORY + "?assestSearchText=" + search.replace(" ", "%20") + "&type=";
        }

        if (serverUrl!=null) {
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {

                UtilView.showProgessBar(activity, progressBar);
                GetDataTask task = new GetDataTask(activity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(activity, progressBar);
                        if (result != null) {
                            try {
                                assestsDataList = new ArrayList<AssestsSelectData>();
                                JSONArray resultJsonArray = new JSONArray(result.toString());
                                for (int i = 0; i < resultJsonArray.length(); i++) {
                                    JSONObject asJson = resultJsonArray.getJSONObject(i);
                                    Gson gson = new Gson();
                                    AssestsSelectData data = gson.fromJson(asJson.toString(), AssestsSelectData.class);
                                    assestsDataList.add(data);
                                }
                                if (assestsDataList!=null && assestsDataList.size()>0){
                                   adapter = new AssestsAdapter(activity,assestsDataList);
                                    lvAssests.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                }
                                else {
                                    assestsDataList.clear();
                                    adapter = new AssestsAdapter(activity, assestsDataList);
                                    lvAssests.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                    UtilView.showErrorDialog(getResources().getString(R.string.assests_notavailbale), activity);

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


    @OnClick({R.id.llSearchAssests})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llSearchAssests:
                UtilView.hideSoftKeyboard(activity, edSearchAssests);
                assetsSearch = edSearchAssests.getText().toString().trim();
                getAssestsFromServer(assetsSearch);
                break;

        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
                Intent intent = new Intent(activity, Activity_AddAssests.class);
                intent.putExtra("callingfrom", Constant.NAVIGATION_INVENTORY_ASSESTS);
                startActivityForResult(intent, Constant.RESQUSTCODE_ADDASSESTS);
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
            UtilView.hideSoftKeyboard(activity, edSearchAssests);
            assetsSearch = "";
            getAssestsFromServer(assetsSearch);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == Constant.RESQUSTCODE_ADDASSESTS) {
            getAssestsFromServer("");
        }

        if (resultCode == Activity.RESULT_OK && requestCode == Constant.RESQUSTCODE_EDITASSESTS) {
            getAssestsFromServer("");
        }

    }
}
