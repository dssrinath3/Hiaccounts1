package in.hiaccounts.hinext.controlpanel.fragment.configurator;


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
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.controlpanel.activity.Activity_ServiceCharge;
import in.hiaccounts.hinext.controlpanel.adapter.ServiceChargeAdapter;
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.servicecharge.ServiceChargeData;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * created by srinath.
 */
public class Fragment_ServiceCharge extends Fragment implements TextWatcher {

    public static String TAG = Fragment_ServiceCharge.class.getSimpleName();
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
    @BindView(R.id.rlContent)
    RelativeLayout rlContent;

    private ServiceHandler serviceHandler;
    private Activity mActivity;
    private Boolean isInternetPresent = false;
    private String search = "", serverUrl;
    List<ServiceChargeData> serviceListData;

    public static Fragment_ServiceCharge newInstance() {
        Fragment_ServiceCharge fragment = new Fragment_ServiceCharge();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_servicecharge, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponents(view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initComponents(View view) {
        setHasOptionsMenu(true);
        serverUrl = UtilView.getUrl(mActivity);
        serviceHandler = new ServiceHandler(mActivity);
        LayoutInflater inflater = mActivity.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.controlpanel_adapter_servicecharge, null, false);
        TextView tvServiceChargeName = header.findViewById(R.id.tvServiceChargeName);
        TextView tvServicePercentage = header.findViewById(R.id.tvServicePercentage);
        TextView tvEdit = header.findViewById(R.id.tvEdit);


        tvServiceChargeName.setText("Service Charge Name");
        tvServicePercentage.setText("Service Percentage");
        tvEdit.setText("Action");

        UtilView.setTextAppearanceSmall(mActivity, tvServiceChargeName);
        UtilView.setTextAppearanceSmall(mActivity, tvServicePercentage);
        UtilView.setTextAppearanceSmall(mActivity, tvEdit);

        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvServiceChargeName);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvServicePercentage);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvEdit);

        if (listview != null)
            listview.addHeaderView(header);
        edSearch.addTextChangedListener(this);

        getServiceChargeFromServer("");

        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    search = edSearch.getText().toString().trim();
                    getServiceChargeFromServer(search);
                }
                return handled;
            }
        });
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (view.getId() == R.id.imgviewEdit ) {
                    if (serviceListData != null) {
                        Intent intent = new Intent(mActivity, Activity_ServiceCharge.class);
                        intent.putExtra("callingFor", Constant.CALL_EDITSERVICECHARGE);
                        intent.putExtra("serviceChargeData", serviceListData.get(position));
                        startActivityForResult(intent, Constant.RESQUSTCODE_EDITSERVICECHARGE);
                    }
                }


            }
        });
    }

    private void getServiceChargeFromServer(String search) {
        String url = "";
        //if (!search.equals("")) {
        url = serverUrl + "/hipos//1/" + Constant.FUNTION_GETSERVICECHARGE + "?servicechargeSearchText=" + search.replace(" ", "%20");
        //}
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
                                serviceListData = new ArrayList<ServiceChargeData>();
                                JSONArray resultJsonArray = null;
                                try {
                                    resultJsonArray = new JSONArray(result.toString());
                                    for (int i = 0; i < resultJsonArray.length(); i++) {
                                        JSONObject customerJson = resultJsonArray.getJSONObject(i);
                                        Gson gson = new Gson();
                                        ServiceChargeData servicedata = gson.fromJson(customerJson.toString(), ServiceChargeData.class);
                                        serviceListData.add(servicedata);
                                    }
                                    if (serviceListData!=null && serviceListData.size()>0){
                                        rlContent.setVisibility(View.VISIBLE);
                                        ServiceChargeAdapter adapter = new ServiceChargeAdapter(mActivity,serviceListData);
                                        listview.setAdapter(adapter);
                                        adapter.notifyDataSetChanged();
                                    }
                                    else {
                                        serviceListData.clear();
                                        ServiceChargeAdapter adapter = new ServiceChargeAdapter(mActivity, serviceListData);
                                        listview.setAdapter(adapter);
                                        adapter.notifyDataSetChanged();
                                        UtilView.showErrorDialog(getResources().getString(R.string.servicecharge_notavailbale), mActivity);

                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }





                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.error_null), mActivity);
                        }
                    }
                }, false);
                task.execute(url, "");
            }
            if (!isInternetPresent) {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
            }
        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.inventory_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.addMenu:
                Intent intent = new Intent(mActivity, Activity_ServiceCharge.class);
                intent.putExtra("callingFor", Constant.CALL_ADDSERVICECHARGE);
                startActivityForResult(intent, Constant.RESQUSTCODE_ADDSERVICECHARGE);
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
            UtilView.hideSoftKeyboard(mActivity, edSearch);
            search = "";
            getServiceChargeFromServer(search);
        }

    }

    @OnClick({R.id.llSearch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llSearch:
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                search = edSearch.getText().toString().trim();
                getServiceChargeFromServer("");
                break;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && requestCode == Constant.RESQUSTCODE_ADDSERVICECHARGE) {
            getServiceChargeFromServer("");
        }
        if (resultCode == Activity.RESULT_OK && requestCode == Constant.RESQUSTCODE_EDITSERVICECHARGE) {
            getServiceChargeFromServer("");
        }
    }
}
