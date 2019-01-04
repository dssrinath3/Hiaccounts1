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
import in.hiaccounts.hinext.controlpanel.activity.Activity_VersionControl;
import in.hiaccounts.hinext.controlpanel.activity.Activity_VersionControl_Permission;
import in.hiaccounts.hinext.controlpanel.adapter.VersionControlAdapter;
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.versioncontrol.VersionControlData;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Created by srinath.
 */
public class Fragment_VersionControl extends Fragment implements TextWatcher {

    public static String TAG = Fragment_VersionControl.class.getSimpleName();
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

    VersionControlData versionControlData;
    List<VersionControlData> listdata;

    public static Fragment_VersionControl newInstance() {
        Fragment_VersionControl fragment = new Fragment_VersionControl();
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
        View view = inflater.inflate(R.layout.fragment_versioncontrol, container, false);
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
        serverUrl = UtilView.getUrl(mActivity);
        setHasOptionsMenu(true);
        serviceHandler = new ServiceHandler(mActivity);
        LayoutInflater inflater = mActivity.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.controlpanel_adapter_versioncontrol, null, false);

        TextView tvProjectName = header.findViewById(R.id.tvProjectName);
        TextView tvProjectDescription = header.findViewById(R.id.tvProjectDesc);
        TextView tvPermissions = header.findViewById(R.id.tvEditPermissions);
        TextView tvEdit = header.findViewById(R.id.tvEdit);

        tvProjectName.setText("Project Name");
        tvProjectDescription.setText("Project Description");
        tvPermissions.setText("Permissions");
        tvEdit.setText("Action");

        UtilView.setTextAppearanceSmall(mActivity, tvProjectName);
        UtilView.setTextAppearanceSmall(mActivity, tvProjectDescription);
        UtilView.setTextAppearanceSmall(mActivity, tvPermissions);
        UtilView.setTextAppearanceSmall(mActivity, tvEdit);

        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvProjectName);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvProjectDescription);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvPermissions);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvEdit);

        if (listview != null)
            listview.addHeaderView(header);
        edSearch.addTextChangedListener(this);

        getVersionControlFromServer("");

        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    search = edSearch.getText().toString().trim();
                    getVersionControlFromServer(search);
                }
                return handled;
            }
        });


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (view.getId() == R.id.imgviewEditPermissions ) {
                    if (listdata != null) {
                        Intent intent = new Intent(mActivity, Activity_VersionControl_Permission.class);
                        intent.putExtra("versionPermissionData", listdata.get(position));
                        startActivityForResult(intent, Constant.RESQUSTCODE_USERVERSIONPERMISSION);
                    }
                }
                if (view.getId() == R.id.imgviewEdit) {
                    if (listdata != null) {
                        Intent intent = new Intent(mActivity, Activity_VersionControl.class);
                        intent.putExtra("callingFor", Constant.CALL_EDITVERSIONCONTROL);
                        intent.putExtra("versionData", listdata.get(position));
                        startActivityForResult(intent, Constant.RESQUSTCODE_EDITVERSIONCONTROL);
                    }
                }

            }
        });
    }

    private void getVersionControlFromServer(String search) {
        String url = "";
        //if (!search.equals("")) {
            url = serverUrl + "/version//1/" + Constant.FUNTION_GETVERSIONCONTROLLIST + "?projectModuleSearchText=" + search.replace(" ", "%20");
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
                                try {
                                    listdata = new ArrayList<VersionControlData>();
                                    JSONArray resultJsonArray = new JSONArray(result.toString());
                                    for (int i = 0; i < resultJsonArray.length(); i++) {
                                        JSONObject customerJson = resultJsonArray.getJSONObject(i);
                                        Gson gson = new Gson();
                                        VersionControlData vdata = gson.fromJson(customerJson.toString(), VersionControlData.class);
                                        listdata.add(vdata);
                                    }

                                    if (listdata!=null && listdata.size()>0){
                                        rlContent.setVisibility(View.VISIBLE);
                                        VersionControlAdapter adapter = new VersionControlAdapter(mActivity,listdata);
                                        listview.setAdapter(adapter);
                                        adapter.notifyDataSetChanged();
                                    }
                                    else {
                                        listdata.clear();
                                        VersionControlAdapter adapter = new VersionControlAdapter(mActivity, listdata);
                                        listview.setAdapter(adapter);
                                        adapter.notifyDataSetChanged();
                                        UtilView.showErrorDialog(getResources().getString(R.string.version_notavailbale), mActivity);

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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && requestCode == Constant.RESQUSTCODE_ADDVERSIONCONTROL) {
            getVersionControlFromServer("");
        }
        if (resultCode == Activity.RESULT_OK && requestCode == Constant.RESQUSTCODE_EDITVERSIONCONTROL) {
            getVersionControlFromServer("");
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
                Intent intent = new Intent(mActivity, Activity_VersionControl.class);
                intent.putExtra("callingFor", Constant.CALL_ADDVERSIONCONTROL);
                startActivityForResult(intent, Constant.RESQUSTCODE_ADDVERSIONCONTROL);
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
            getVersionControlFromServer(search);
        }

    }

    @OnClick({R.id.llSearch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llSearch:
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                search = edSearch.getText().toString().trim();
                getVersionControlFromServer("");
                break;

        }
    }
}
