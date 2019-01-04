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
import in.hiaccounts.hinext.controlpanel.activity.Activity_Config_State;
import in.hiaccounts.hinext.controlpanel.adapter.StateAdapter;
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.state.AddStateDatum;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * srinath 30-01-2018.
 */
public class Fragment_State extends Fragment implements TextWatcher {


    public static String TAG = Fragment_State.class.getSimpleName();
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
    Unbinder unbinder;


    private ServiceHandler serviceHandler;
    private Activity mActivity;
    private Boolean isInternetPresent = false;
    private String search = "", serverUrl;
    private List<AddStateDatum> listdata;
    private StateAdapter adapter;

    public static Fragment_State newInstance() {
        Fragment_State fragment = new Fragment_State();
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
        View view = inflater.inflate(R.layout.fragment_state, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponents(view);
        return view;
    }

    private void initComponents(View view) {
        serverUrl = UtilView.getUrl(mActivity);
        setHasOptionsMenu(true);
        serviceHandler = new ServiceHandler(mActivity);
        LayoutInflater inflater = mActivity.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.controlpanel_adapter_state, null, false);

        TextView tvCountryName = header.findViewById(R.id.tvCountryName);
        TextView tvStateName = header.findViewById(R.id.tvStateName);
        TextView tvStatus = header.findViewById(R.id.tvStatus);
        TextView tvEdit = header.findViewById(R.id.tvEdit);

        tvCountryName.setText("Country Name");
        tvStateName.setText("State Name");
        tvStatus.setText("Status");
        tvEdit.setText("Action");

        UtilView.setTextAppearanceSmall(mActivity, tvCountryName);
        UtilView.setTextAppearanceSmall(mActivity, tvStateName);
        UtilView.setTextAppearanceSmall(mActivity, tvStatus);
        UtilView.setTextAppearanceSmall(mActivity, tvEdit);

        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvCountryName);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvStateName);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvStatus);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvEdit);

        if (listview != null)
            listview.addHeaderView(header);
        edSearch.addTextChangedListener(this);

        getStateFromServer("");

        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    search = edSearch.getText().toString().trim();
                    getStateFromServer(search);
                }
                return handled;
            }
        });


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (view.getId() == R.id.imgviewEdit) {
                    if (listdata != null) {
                        Intent intent = new Intent(mActivity, Activity_Config_State.class);
                        intent.putExtra("callingFor", Constant.CALL_EDITSTATE);
                        intent.putExtra("stateData",  listdata.get(position));
                        startActivityForResult(intent, Constant.RESQUSTCODE_ADDSTATE);
                    }
                }

            }
        });
    }

    private void getStateFromServer(String search) {
        String url = "";
        if (search.equals("")) {
            url = serverUrl + "/hipos//1/" + Constant.FUNTION_GETSTATELIST + "?stateSearchText="+"&type=";
        }
        if (!search.equals("")) {
        url = serverUrl + "/hipos//1/" + Constant.FUNTION_GETSTATELIST + "?stateSearchText=" + search.replace(" ", "%20")+"&type=";
        }
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
                                    listdata = new ArrayList<AddStateDatum>();
                                    Gson gson = new Gson();
                                    JSONArray resultJsonArray = new JSONArray(result.toString());
                                    for (int i = 0; i < resultJsonArray.length(); i++) {
                                        JSONObject customerJson = resultJsonArray.getJSONObject(i);

                                        AddStateDatum vdata = gson.fromJson(customerJson.toString(), AddStateDatum.class);
                                        listdata.add(vdata);
                                    }

                                    if (listdata!=null && listdata.size()>0){
                                        rlContent.setVisibility(View.VISIBLE);
                                         adapter = new StateAdapter(mActivity,listdata);
                                        listview.setAdapter(adapter);
                                        adapter.notifyDataSetChanged();
                                    }
                                    else {
                                        listdata.clear();
                                         adapter = new StateAdapter(mActivity, listdata);
                                        listview.setAdapter(adapter);
                                        adapter.notifyDataSetChanged();
                                        UtilView.showErrorDialog(getResources().getString(R.string.state_notavailbale), mActivity);

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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && requestCode == Constant.RESQUSTCODE_ADDSTATE) {
            getStateFromServer("");
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
                Intent intent = new Intent(mActivity, Activity_Config_State.class);
                intent.putExtra("callingFor", Constant.CALL_ADDSTATE);
                startActivityForResult(intent, Constant.RESQUSTCODE_ADDSTATE);
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
            getStateFromServer(search);
        }
    }

    @OnClick({R.id.llSearch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llSearch:
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                search = edSearch.getText().toString().trim();
                getStateFromServer("");
                break;

        }
    }
}
