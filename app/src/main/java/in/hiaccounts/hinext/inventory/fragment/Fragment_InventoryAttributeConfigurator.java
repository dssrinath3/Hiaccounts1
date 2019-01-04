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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.hiaccounts.R;
import in.hiaccounts.hinext.inventory.activity.Activity_AttributeConfigurator;
import in.hiaccounts.hinext.inventory.adapter.AttributeConfiguratorAdapter;
import in.hiaccounts.hinext.inventory.model.attributrconfigurator.AttributeConfig;
import in.hiaccounts.hinext.inventory.model.attributrconfigurator.AttributeConfigData;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_InventoryAttributeConfigurator extends Fragment implements TextWatcher {

    public static String TAG = Fragment_InventoryAttributeConfigurator.class.getSimpleName();
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
    @BindView(R.id.tvFirst)
    TextView tvFirst;
    @BindView(R.id.tvPrev)
    TextView tvPrev;
    @BindView(R.id.tvPageNo)
    TextView tvPageNo;
    @BindView(R.id.tvNext)
    TextView tvNext;
    @BindView(R.id.tvLast)
    TextView tvLast;
    @BindView(R.id.llPaginationView)
    LinearLayout llPaginationView;
    Unbinder unbinder;
    int pageNo = 0;
    AttributeConfiguratorAdapter attributeConfigAdapter;
    private ServiceHandler serviceHandler;
    private Activity mActivity;
    private Boolean isInternetPresent = false;
    private List<Object> attributeConfigList = new ArrayList<Object>();
    private String attributeConfigSearch = "", serverUrl;

    public static Fragment_InventoryAttributeConfigurator newInstance() {
        Fragment_InventoryAttributeConfigurator fragment = new Fragment_InventoryAttributeConfigurator();
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
        View view = inflater.inflate(R.layout.fragment_inventory_attribute_configurator, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponents(view);
        return view;
    }

    private void initComponents(View view) {
        serverUrl = UtilView.getUrl(mActivity);
        setHasOptionsMenu(true);
        serviceHandler = new ServiceHandler(mActivity);
        LayoutInflater inflater = mActivity.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.inventory_adapter_attribute_configuratorview,null,false);
        TextView tvAttributeConfigName = header.findViewById(R.id.tvAttributeConfigName);
        TextView tvAttributeConfigDescription = header.findViewById(R.id.tvAttributeConfigDescription);
        TextView tvAttributeConfigEdit = header.findViewById(R.id.tvAttributeConfigEdit);
        tvAttributeConfigName.setText("Name");
        tvAttributeConfigDescription.setText("Description");
        tvAttributeConfigEdit.setText("Action");
        UtilView.setTextAppearanceSmall(mActivity, tvAttributeConfigName);
        UtilView.setTextAppearanceSmall(mActivity, tvAttributeConfigDescription);
        UtilView.setTextAppearanceSmall(mActivity, tvAttributeConfigEdit);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvAttributeConfigName);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvAttributeConfigDescription);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvAttributeConfigEdit);

        if(listview != null)
            listview.addHeaderView(header);

        if(edSearch !=null)
            edSearch.addTextChangedListener(this);

        getAttributeConfigFromServer("", true, false, "0", false, false);

        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    handled = true;
                    attributeConfigSearch = edSearch.getText().toString().trim();
                    getAttributeConfigFromServer(attributeConfigSearch, true, false, "0", false, false);
                }

                return handled;
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (view.getId() == R.id.imgviewEdit) {
                    if (attributeConfigList != null) {
                        AttributeConfig attributeConfig = (AttributeConfig) attributeConfigList.get(position);
                        Intent intent = new Intent(mActivity, Activity_AttributeConfigurator.class);
                        intent.putExtra("attributeConfigData", attributeConfig);
                        intent.putExtra("callingFor", Constant.CALL_EDITATTRIBUTECONFIGURATOR);
                        startActivityForResult(intent, Constant.RESQUSTCODE_EDITATTRIBUTECONFIGUARATOR);
                    }
                }
            }
        });

    }

    private void getAttributeConfigFromServer(String search, boolean isFirst, boolean isPrev, String pageNumber, boolean isNext, boolean isLast) {
        String url = "";
        if (search.equals("")) {
            url = serverUrl + "/hipos//1/" + Constant.FUNTION_GETATTRIBUTECONFIG + "?AttributeConfiguratorItemSearchText=&type=&firstPage=" + isFirst + "&lastPage=" + isLast + "&nextPage=" + isNext + "&pageNo=" + pageNumber + "&prevPage=" + isPrev;
        }
        if (!search.equals("")) {
            url = serverUrl + "/hipos//1/" + Constant.FUNTION_GETATTRIBUTECONFIG + "?AttributeConfiguratorItemSearchText=" + search.replace(" ", "%20") + "&type=&firstPage=" + isFirst + "&lastPage=" + isLast + "&nextPage=" + isNext + "&pageNo=" + pageNumber + "&prevPage=" + isPrev;
        }
        if (serverUrl!=null) {
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {
                UtilView.showProgessBar(mActivity, progressBar);
                GetDataTask task = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(mActivity, progressBar);
                        if (result != null) {
                            Gson gson = new Gson();
                            try {
                                attributeConfigList.clear();
                                List<AttributeConfig> list = new ArrayList<>();
                                AttributeConfigData data = gson.fromJson(result.toString(), AttributeConfigData.class);
                                if (data != null) {
                                    setUpView(data);
                                    if (data.getData() != null && data.getData().size() > 0) {
                                        list = data.getData();
                                        attributeConfigList.addAll(list);
                                        attributeConfigAdapter = new AttributeConfiguratorAdapter(mActivity, attributeConfigList);
                                        if (listview != null)
                                            listview.setAdapter(attributeConfigAdapter);
                                        attributeConfigAdapter.notifyDataSetChanged();

                                    } else {

                                        attributeConfigList.clear();
                                        attributeConfigAdapter = new AttributeConfiguratorAdapter(mActivity, attributeConfigList);
                                        if (listview != null)
                                            listview.setAdapter(attributeConfigAdapter);
                                        attributeConfigAdapter.notifyDataSetChanged();
                                        UtilView.showSuccessDialog(getResources().getString(R.string.attributeconfig_notavailbale), mActivity);
                                    }
                                }


                            }catch (Exception e){
                                UtilView.showErrorDialog(mActivity.getResources().getString(R.string.response_error),mActivity);
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
        }else {
            UtilView.gotToLogin(mActivity);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == Constant.RESQUSTCODE_ADDATTRIBUTECONFIGUARATOR) {
            getAttributeConfigFromServer("", true, false, "0", false, false);
        }
        if (resultCode == Activity.RESULT_OK && requestCode == Constant.RESQUSTCODE_EDITATTRIBUTECONFIGUARATOR) {
            getAttributeConfigFromServer("", true, false, "0", false, false);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tvFirst, R.id.tvPrev, R.id.tvNext, R.id.tvLast, R.id.llSearch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llSearch:
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                attributeConfigSearch = edSearch.getText().toString().trim();
                pageNo = 0;
                getAttributeConfigFromServer(attributeConfigSearch, true, false, "0", false, false);
                break;
            case R.id.tvFirst:
                pageNo = 0;
                getAttributeConfigFromServer("", true, false, "0", false, false);
                break;
            case R.id.tvPrev:
                if (pageNo > 0) {
                    pageNo = pageNo - 1;
                }
                getAttributeConfigFromServer("", false, true, "" + pageNo, false, false);
                break;
            case R.id.tvNext:
                pageNo = pageNo + 1;
                getAttributeConfigFromServer("", false, false, "" + pageNo, true, false);
                break;
            case R.id.tvLast:
                pageNo = 0;
                getAttributeConfigFromServer("", false, false, "0", false, true);
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
                Intent intent = new Intent(mActivity, Activity_AttributeConfigurator.class);
                intent.putExtra("callingFor", Constant.CALL_ADDATTRIBUTECONFIGURATOR);
                startActivityForResult(intent, Constant.RESQUSTCODE_ADDATTRIBUTECONFIGUARATOR);
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
            attributeConfigSearch = "";
            getAttributeConfigFromServer(attributeConfigSearch, true, false, "0", false, false);
        }
    }
    private void setUpView(AttributeConfigData data) {
        if (llPaginationView != null)
            llPaginationView.setVisibility(View.VISIBLE);
        if (data.getFirst()) {
            //  tvFirst.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
            if (tvFirst != null) {
                tvFirst.setTextColor(getResources().getColor(R.color.colorBlack));
                tvFirst.setClickable(false);
            }
        }
        if (!data.getFirst()) {
            // tvFirst.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
            if (tvFirst != null) {
                tvFirst.setTextColor(getResources().getColor(R.color.colorWhite));
                tvFirst.setClickable(true);
            }
        }
        if (data.getNext()) {
            // tvNext.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
            if (tvNext != null) {
                tvNext.setTextColor(getResources().getColor(R.color.colorBlack));
                tvNext.setClickable(false);
            }
        }
        if (!data.getNext()) {
            //  tvNext.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
            if (tvNext != null) {
                tvNext.setTextColor(getResources().getColor(R.color.colorWhite));
                tvNext.setClickable(true);
            }
        }
        if (data.getPrev()) {
            //  tvPrev.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
            if (tvPrev != null) {
                tvPrev.setTextColor(getResources().getColor(R.color.colorBlack));
                tvPrev.setClickable(false);
            }
        }
        if (!data.getPrev()) {
            //  tvPrev.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
            if (tvPrev != null) {
                tvPrev.setTextColor(getResources().getColor(R.color.colorWhite));
                tvPrev.setClickable(true);
            }
        }
        if (data.getLast()) {
            //  tvLast.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
            if (tvLast != null) {
                tvLast.setTextColor(getResources().getColor(R.color.colorBlack));
                tvLast.setClickable(false);
            }
        }
        if (!data.getLast()) {
            //   tvLast.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
            if (tvLast != null) {
                tvLast.setTextColor(getResources().getColor(R.color.colorWhite));
                tvLast.setClickable(true);
            }
        }
        pageNo = data.getPageNo();
    }
}
