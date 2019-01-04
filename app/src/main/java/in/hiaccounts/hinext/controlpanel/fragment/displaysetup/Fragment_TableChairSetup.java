package in.hiaccounts.hinext.controlpanel.fragment.displaysetup;


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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.controlpanel.activity.Activity_Chairs;
import in.hiaccounts.hinext.controlpanel.activity.Activity_Table;
import in.hiaccounts.hinext.controlpanel.adapter.ChairDataAdapter;
import in.hiaccounts.hinext.controlpanel.adapter.TableDataAdapter;
import in.hiaccounts.hinext.controlpanel.model.display_setup.chairs.ChairsData;
import in.hiaccounts.hinext.controlpanel.model.display_setup.chairs.ChairsDataList;
import in.hiaccounts.hinext.controlpanel.model.display_setup.table_configuration.table.TableData;
import in.hiaccounts.hinext.controlpanel.model.display_setup.table_configuration.table.TableDataList;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Srinath on 30/11/2018.
 */
public class Fragment_TableChairSetup extends Fragment implements TextWatcher {


    public static final String TAG = Fragment_TableChairSetup.class.getSimpleName();
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.listviewChairs)
    ListView listviewChairs;
    @BindView(R.id.edSearch)
    EditText edSearch;
    @BindView(R.id.llSearch)
    LinearLayout llSearch;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.rlContent)
    RelativeLayout rlContent;
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
    private List<ChairsDataList> chairsDataList;
    private ChairDataAdapter adapter;
    private ServiceHandler serviceHandler;
    private Activity mActivity;
    private Boolean isInternetPresent = false;
    private String search = "", serverUrl;
    private Long pageNo = 0L;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }


    public static Fragment newInstance() {
        Fragment_TableChairSetup fragment = new Fragment_TableChairSetup();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_table_chair_setup, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponents(view);
        return view;
    }
    private void initComponents(View view) {
        setHasOptionsMenu(true);
        serverUrl = UtilView.getUrl(mActivity);
        serviceHandler = new ServiceHandler(mActivity);
        LayoutInflater inflater = mActivity.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.controlpanel_adapter_chairs, null, false);
        TextView tvNoChairs = header.findViewById(R.id.tvNoChairs);
        TextView tvTableName = header.findViewById(R.id.tvTableName);
        TextView tvConfigName = header.findViewById(R.id.tvConfigName);
        TextView tvEdit = header.findViewById(R.id.tvEdit);

        tvNoChairs.setText("No:Of Chairs");
        tvTableName.setText("Table Name");
        tvConfigName.setText("Config Name");
        tvEdit.setText("Edit");

        UtilView.setTextAppearanceSmall(mActivity, tvNoChairs);
        UtilView.setTextAppearanceSmall(mActivity, tvTableName);
        UtilView.setTextAppearanceSmall(mActivity, tvConfigName);
        UtilView.setTextAppearanceSmall(mActivity, tvEdit);

        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvNoChairs);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvTableName);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvConfigName);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvEdit);

        if (listviewChairs != null)
            listviewChairs.addHeaderView(header);
        edSearch.addTextChangedListener(this);

        getChairsFromServer("", true, false, "0", false, false);

        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    search = edSearch.getText().toString().trim();
                    getChairsFromServer(search, true, false, "0", false, false);
                }
                return handled;
            }
        });


        listviewChairs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                if (view.getId() == R.id.imgviewEdit) {
                    if (chairsDataList != null) {
                        ChairsDataList details = chairsDataList.get(position);
                        Intent intent = new Intent(mActivity, Activity_Chairs.class);
                        intent.putExtra("callingFor", Constant.CALL_EDITCHAIR);
                        intent.putExtra("chairData", details);
                        startActivityForResult(intent, Constant.RESQUSTCODE_EDITCHAIR);
                    }
                }
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void getChairsFromServer(String search, boolean isFirst, boolean isPrev, String pageNumber, boolean isNext, boolean isLast) {
        String url = "";
        if (search.equals("")) {
            url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETCHAIR + "?tableSearchText=&type=Active&pageValue=undefined&firstPage="+isFirst+"&lastPage="+isLast+"&nextPage="+isNext+"&pageNo="+pageNumber+"&prevPage="+isPrev;
        }
        if (!search.equals("")) {
            url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETCHAIR + "?tableSearchText=" + search.replace(" ", "%20") + "&type=Active&pageValue=undefined&firstPage="+isFirst+"&lastPage="+isLast+"&nextPage="+isNext+"&pageNo="+pageNumber+"&prevPage="+isPrev;
        }
        if (serverUrl != null) {
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {
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

                                    Gson gson = new Gson();
                                    chairsDataList = new ArrayList<>();
                                    ChairsData chairsData = gson.fromJson(result.toString(), ChairsData.class);
                                    if (chairsData != null) {
                                        setUpView(chairsData);
                                        chairsDataList = chairsData.getData();

                                    }

                                    if (chairsDataList != null && chairsDataList.size() > 0) {
                                        adapter = new ChairDataAdapter(mActivity, chairsDataList);
                                        listviewChairs.setAdapter(adapter);
                                        adapter.notifyDataSetChanged();
                                    } else {
                                        chairsDataList.clear();
                                        adapter = new ChairDataAdapter(mActivity, chairsDataList);
                                        listviewChairs.setAdapter(adapter);
                                        adapter.notifyDataSetChanged();
                                        UtilView.showErrorDialog("Chairs not available", mActivity);
                                    }
                                } catch (Exception e) {
                                    UtilView.showErrorDialog(getResources().getString(R.string.error_null), mActivity);
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
        } else {
            UtilView.gotToLogin(mActivity);
        }
    }

    private void setUpView(ChairsData data) {
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


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (editable.toString().equals("")) {
            UtilView.hideSoftKeyboard(mActivity, edSearch);
            search = "";
            getChairsFromServer(search, true, false, "0", false, false);
        }
    }
    /*    @OnClick({R.id.edSearch})
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.edSearch:
                    UtilView.hideSoftKeyboard(mActivity, edSearch);
                    search = edSearch.getText().toString().trim();
                    getTableFromServer(search, true, false, "0", false, false);
                    break;

            }
        }*/
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
                Intent intent = new Intent(mActivity, Activity_Chairs.class);
                intent.putExtra("callingFor", Constant.CALL_ADDCHAIR);
                startActivityForResult(intent, Constant.RESQUSTCODE_ADDCHAIR);

                break;


        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.RESQUSTCODE_ADDCHAIR && resultCode == Activity.RESULT_OK) {
            getChairsFromServer("", true, false, "0", false, false);
        }
        if (requestCode == Constant.RESQUSTCODE_EDITCHAIR && resultCode == Activity.RESULT_OK) {
            getChairsFromServer("", true, false, "0", false, false);
        }


    }


}
