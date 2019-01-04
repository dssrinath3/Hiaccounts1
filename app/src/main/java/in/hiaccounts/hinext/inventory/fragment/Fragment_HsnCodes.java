package in.hiaccounts.hinext.inventory.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
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
import in.hiaccounts.hinext.controlpanel.activity.Activity_CoSetup_SearchHsn;
import in.hiaccounts.hinext.controlpanel.activity.Activity_Config_HsnCode;
import in.hiaccounts.hinext.controlpanel.adapter.HsnCodeAdapter;
import in.hiaccounts.hinext.inventory.model.hsncode.HSNCodeData;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 2/9/2017.
 */

public class Fragment_HsnCodes extends Fragment implements TextWatcher {
    public static String TAG = Fragment_HsnCodes.class.getSimpleName();
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.fabAdd)
    FloatingActionButton fabAdd;
    @BindView(R.id.fabSearch)
    FloatingActionButton fabSearch;
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
    private List<Object> hsnCodeList = new ArrayList<Object>();
    private HsnCodeAdapter hsnCodeAdapter;
    private String search = "", serverUrl;

    public static Fragment_HsnCodes newInstance() {
        Fragment_HsnCodes fragment = new Fragment_HsnCodes();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Returning the layout file after inflating
        View view = inflater.inflate(R.layout.fragment_csetupalert, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponents(view);
        return view;
    }

    private void initComponents(View view) {
        serviceHandler = new ServiceHandler(mActivity);
        serverUrl = UtilView.getUrl(mActivity);
        LayoutInflater inflater = mActivity.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.controlpanel_adapter_hsncode, null, false);

        TextView tvCode = header.findViewById(R.id.tvCode);
        TextView tvDescription = header.findViewById(R.id.tvDescription);
        TextView tvEdit = header.findViewById(R.id.tvEdit);

        tvCode.setText("HSN/SAC Code");
        tvDescription.setText("HSN/SAC Descriptio");
        tvEdit.setText("Action");

        UtilView.setTextAppearanceSmall(mActivity, tvCode);
        UtilView.setTextAppearanceSmall(mActivity, tvDescription);
        UtilView.setTextAppearanceSmall(mActivity, tvEdit);

        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvCode);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvDescription);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvEdit);

        if (listview != null)
            listview.addHeaderView(header);
        if (edSearch != null)
            edSearch.addTextChangedListener(this);

        getHSNFromServer("");


        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    search = edSearch.getText().toString().trim();
                    getHSNFromServer(search);
                }
                return handled;
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (view.getId() == R.id.imgviewEdit) {
                    Intent intent = new Intent(mActivity, Activity_Config_HsnCode.class);
                    intent.putExtra("callingFor", Constant.CALL_EDITHSN);
                    intent.putExtra("hsnCodeData", (HSNCodeData) hsnCodeList.get(position));
                    startActivityForResult(intent, Constant.RESQUSTCODE_EDITHSN);
                }
            }
        });
    }

    private void getHSNFromServer(String search) {

        String url = "";
        if (search.equals("")) {
            url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETHSNCODES + "?hsnCodeSearchText=&type=&pageNo=0";
        }
        if (!search.equals("")) {
            url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETHSNCODES + "?hsnCodeSearchText=" + search.replace(" ", "%20") + "&type=&pageNo=0";
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
                            Log.e("result",result.toString());
                            Gson gson = new Gson();
                            hsnCodeList.clear();
                            List<HSNCodeData> list = new ArrayList<>();
                            try {
                            JSONArray jsonArray = null;
                            try {
                                JSONObject jsonO = new JSONObject(result.toString());
                                jsonArray = jsonO.getJSONArray("data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    HSNCodeData hsnCode = gson.fromJson(jsonObject.toString(), HSNCodeData.class);
                                    list.add(hsnCode);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                                if (list.size() > 0) {
                                    hsnCodeList.addAll(list);
                                    hsnCodeAdapter = new HsnCodeAdapter(mActivity, hsnCodeList);
                                    if (listview != null)
                                        listview.setAdapter(hsnCodeAdapter);
                                    hsnCodeAdapter.notifyDataSetChanged();
                                } else {
                                    fabAdd.setVisibility(View.VISIBLE);
                                    hsnCodeList.clear();
                                    hsnCodeAdapter = new HsnCodeAdapter(mActivity, hsnCodeList);
                                    if (listview != null)
                                        listview.setAdapter(hsnCodeAdapter);
                                    hsnCodeAdapter.notifyDataSetChanged();
                                    UtilView.showErrorDialog(getResources().getString(R.string.hsn_notavailbale), mActivity);
                                }
                            } catch (Exception e) {
                                UtilView.showErrorDialog(getResources().getString(R.string.error_null), mActivity);
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
            getHSNFromServer(search);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == Constant.RESQUSTCODE_ADDHSN) {
            getHSNFromServer("");
        }
        if (resultCode == Activity.RESULT_OK && requestCode == Constant.RESQUSTCODE_EDITHSN) {
            getHSNFromServer("");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.fabAdd, R.id.fabSearch, R.id.llSearch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fabAdd:
                Intent intent = new Intent(mActivity, Activity_Config_HsnCode.class);
                intent.putExtra("callingFor", Constant.CALL_ADDHSN);
                startActivityForResult(intent, Constant.RESQUSTCODE_ADDHSN);
                break;
            case R.id.fabSearch:
                Intent intent1 = new Intent(mActivity, Activity_CoSetup_SearchHsn.class);
                startActivityForResult(intent1, Constant.RESQUSTCODE_ADDHSN);
                break;
            case R.id.llSearch:
                search = edSearch.getText().toString().trim();
                getHSNFromServer(search);
                break;
        }
    }
}
