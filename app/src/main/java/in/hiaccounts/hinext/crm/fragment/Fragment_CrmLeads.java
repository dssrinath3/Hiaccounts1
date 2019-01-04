package in.hiaccounts.hinext.crm.fragment;


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
import in.hiaccounts.hinext.crm.activity.Activity_AddLeads;
import in.hiaccounts.hinext.crm.adapter.LeadsAdapter;
import in.hiaccounts.hinext.crm.model.leads.SelectLeadsData;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Srinath on 20/12/2017.
 */
public class Fragment_CrmLeads extends Fragment implements TextWatcher {

    public static String TAG = Fragment_CrmLeads.class.getSimpleName();
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.lvLeads)
    ListView lvLeads;
    @BindView(R.id.edSearchLeads)
    EditText edSearchLeads;
    @BindView(R.id.llSearchLeads)
    LinearLayout llSearchLeads;
    @BindView(R.id.ll)
    RelativeLayout ll;
    @BindView(R.id.ll_searchLeads)
    LinearLayout ll_searchLeads;
    Unbinder unbinder;

    public static Fragment_CrmLeads newInstance() {
        Fragment_CrmLeads fragment = new Fragment_CrmLeads();
        return fragment;
    }

    private ServiceHandler serviceHandler;
    private Activity activity;
    private Boolean isInternetPresent = false;
    private String leadsSearch = "", serverUrl;
    List<SelectLeadsData> selectLeadsDataList;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crm_leads, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponents(view);
        return view;
    }

    private void initComponents(View view) {
        setHasOptionsMenu(true);
        serviceHandler = new ServiceHandler(activity);
        serverUrl = UtilView.getUrl(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.crm_adapter_leadsview, null, false);
        TextView tv_Name = header.findViewById(R.id.tv_Name);
        TextView tv_Desc = header.findViewById(R.id.tv_Description);
        TextView tv_Sub = header.findViewById(R.id.tv_Subject);
        TextView tv_advanceDiscountConfigEdit = header.findViewById(R.id.tv_Edit);
        tv_Name.setText("Name");
        tv_Desc.setText("Description");
        tv_Sub.setText("Subject");
        tv_advanceDiscountConfigEdit.setText("Action");
        UtilView.setTextAppearanceSmall(activity, tv_Name);
        UtilView.setTextAppearanceSmall(activity, tv_Desc);
        UtilView.setTextAppearanceSmall(activity, tv_Sub);
        UtilView.setTextAppearanceSmall(activity, tv_advanceDiscountConfigEdit);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tv_Name);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tv_Desc);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tv_Sub);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tv_advanceDiscountConfigEdit);
        if (lvLeads != null)
            lvLeads.addHeaderView(header);
        edSearchLeads.addTextChangedListener(this);

        getCrmLeadsFromServer("");

        edSearchLeads.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(activity, edSearchLeads);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    leadsSearch = edSearchLeads.getText().toString().trim();
                    getCrmLeadsFromServer(leadsSearch);
                }
                return handled;
            }
        });

    }

    private void getCrmLeadsFromServer(String search) {
        String url = "";
        url =serverUrl+ "/crm//" + Constant.FUNTION_GETLEADS+ "?leadsSearchText=" + search.replace(" ", "%20");


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
                                selectLeadsDataList = new ArrayList<SelectLeadsData>();
                                JSONArray resultJsonArray = new JSONArray(result.toString());
                                for (int i = 0; i < resultJsonArray.length(); i++) {
                                    JSONObject asJson = resultJsonArray.getJSONObject(i);
                                    Gson gson = new Gson();
                                    SelectLeadsData data = gson.fromJson(asJson.toString(), SelectLeadsData.class);
                                    selectLeadsDataList.add(data);
                                }
                                if (selectLeadsDataList!=null && selectLeadsDataList.size()>0){
                                    LeadsAdapter adapter = new LeadsAdapter(activity,selectLeadsDataList);
                                    lvLeads.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                }
                                else {
                                    selectLeadsDataList.clear();
                                    LeadsAdapter adapter = new LeadsAdapter(activity, selectLeadsDataList);
                                    lvLeads.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                    UtilView.showErrorDialog(getResources().getString(R.string.leads_notavailbale), activity);

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

    @OnClick({R.id.llSearchLeads})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llSearchLeads:
                UtilView.hideSoftKeyboard(activity, edSearchLeads);
                leadsSearch = edSearchLeads.getText().toString().trim();
                getCrmLeadsFromServer(leadsSearch);
                break;

        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.crm_menu, menu);
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
                Intent intent = new Intent(activity, Activity_AddLeads.class);
                intent.putExtra("callingFor", Constant.CALL_ADDLEADS);
                startActivityForResult(intent, Constant.RESQUSTCODE_ADDLEADS);
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
            UtilView.hideSoftKeyboard(activity, edSearchLeads);
            leadsSearch = "";
            getCrmLeadsFromServer(leadsSearch);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == Constant.RESQUSTCODE_ADDLEADS) {
            getCrmLeadsFromServer("");
        }

        if (resultCode == Activity.RESULT_OK && requestCode == Constant.RESQUSTCODE_EDITLEADS) {
            getCrmLeadsFromServer("");
        }

    }
}
