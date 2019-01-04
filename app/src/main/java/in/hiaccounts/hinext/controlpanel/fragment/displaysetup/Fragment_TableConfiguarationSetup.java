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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.controlpanel.activity.Activity_TableConfiguration;
import in.hiaccounts.hinext.controlpanel.adapter.TableConfiguartionAdapter;
import in.hiaccounts.hinext.controlpanel.model.display_setup.table_configuration.TableConfigData;
import in.hiaccounts.hinext.controlpanel.model.display_setup.table_configuration.TableConfigDataList;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Srinath on 23/5/2018.
 */
public class Fragment_TableConfiguarationSetup extends Fragment implements TextWatcher {

    public static String TAG = Fragment_TableConfiguarationSetup.class.getSimpleName();
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.listviewTableConfig)
    ListView listviewTableConfig;
    @BindView(R.id.edSearch)
    EditText edSearch;
    @BindView(R.id.llSearch)
    LinearLayout llSearch;
    @BindView(R.id.rlContent)
    RelativeLayout rlContent;
    Unbinder unbinder;
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
    private ServiceHandler serviceHandler;
    private Activity mActivity;
    private Boolean isInternetPresent = false;
    private String search = "", serverUrl;
    private List<TableConfigDataList> tableConfigDataList;
    private TableConfiguartionAdapter adapter;
    private Long pageNo = 0L;
    public static Fragment_TableConfiguarationSetup newInstance() {
        Fragment_TableConfiguarationSetup fragment = new Fragment_TableConfiguarationSetup();
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

        View view = inflater.inflate(R.layout.fragment_table_configuaration_setup, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponents(view);
        return view;
    }

    private void initComponents(View view) {
        setHasOptionsMenu(true);
        serverUrl = UtilView.getUrl(mActivity);
        serviceHandler = new ServiceHandler(mActivity);
        LayoutInflater inflater = mActivity.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.controlpanel_adapter_tableconfiguaration, null, false);
        TextView tvConfigName = header.findViewById(R.id.tvConfigName);
        TextView tvRows = header.findViewById(R.id.tvRows);
        TextView tvColumns = header.findViewById(R.id.tvColumns);
        TextView tvEdit = header.findViewById(R.id.tvEdit);


        tvConfigName.setText("Configuration Name");
        tvRows.setText("No. of Rows");
        tvColumns.setText("No. of Columns");
        tvEdit.setText("Action");

        UtilView.setTextAppearanceSmall(mActivity, tvConfigName);
        UtilView.setTextAppearanceSmall(mActivity, tvRows);
        UtilView.setTextAppearanceSmall(mActivity, tvColumns);
        UtilView.setTextAppearanceSmall(mActivity, tvEdit);

        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvConfigName);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvRows);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvColumns);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvEdit);

        if (listviewTableConfig != null)
            listviewTableConfig.addHeaderView(header);
        edSearch.addTextChangedListener(this);

        getTableConfigFromServer("", true, false, "0", false, false);

        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    search = edSearch.getText().toString().trim();
                    getTableConfigFromServer(search, true, false, "0", false, false);
                }
                return handled;
            }
        });


        listviewTableConfig.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                if (view.getId() == R.id.imgviewEdit) {
                    if (tableConfigDataList != null) {
                        TableConfigDataList details = tableConfigDataList.get(position);
                        Intent intent = new Intent(mActivity, Activity_TableConfiguration.class);
                        intent.putExtra("callingFor", Constant.CALL_EDITTABLECONFIG);
                        intent.putExtra("tableConfigData", details);
                        startActivityForResult(intent, Constant.RESQUSTCODE_EDITTABLECONFIG);
                    }
                }
            }
        });
    }

    private void getTableConfigFromServer(String search, boolean isFirst, boolean isPrev, String pageNumber, boolean isNext, boolean isLast) {
        String url = "";
        if (search.equals("")) {
            url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETTABLECONFIG + "?searchText=&pageValue=undefined&firstPage="+isFirst+"&lastPage="+isLast+"&nextPage="+isNext+"&pageNo="+pageNumber+"&prevPage="+isPrev;
        }
        if (!search.equals("")) {
            url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETTABLECONFIG + "?searchText=" + search.replace(" ", "%20") + "&pageValue=undefined&firstPage="+isFirst+"&lastPage="+isLast+"&nextPage="+isNext+"&pageNo="+pageNumber+"&prevPage="+isPrev;
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
                                    tableConfigDataList = new ArrayList<>();

                                        TableConfigData tableConfigData = gson.fromJson(result.toString(), TableConfigData.class);

                                        if (tableConfigData!=null){
                                            setUpView(tableConfigData);
                                            tableConfigDataList=tableConfigData.getData();
                                        }



                                    if (tableConfigDataList != null && tableConfigDataList.size() > 0) {
                                        adapter = new TableConfiguartionAdapter(mActivity, tableConfigDataList);
                                        listviewTableConfig.setAdapter(adapter);
                                        adapter.notifyDataSetChanged();
                                    } else {
                                        tableConfigDataList.clear();
                                        adapter = new TableConfiguartionAdapter(mActivity, tableConfigDataList);
                                        listviewTableConfig.setAdapter(adapter);
                                        adapter.notifyDataSetChanged();
                                        UtilView.showErrorDialog(getResources().getString(R.string.cart_tableconfigavailbale), mActivity);
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

    private void setUpView(TableConfigData data) {
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
            getTableConfigFromServer(search, true, false, "0", false, false);
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
                Intent intent = new Intent(mActivity, Activity_TableConfiguration.class);
                intent.putExtra("callingFor", Constant.CALL_ADDTABLECONFIG);
                startActivityForResult(intent, Constant.RESQUSTCODE_ADDTABLECONFIG);

                break;


        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.RESQUSTCODE_ADDTABLECONFIG && resultCode == Activity.RESULT_OK) {
            getTableConfigFromServer("", true, false, "0", false, false);
        }
        if (requestCode == Constant.RESQUSTCODE_EDITTABLECONFIG && resultCode == Activity.RESULT_OK) {
            getTableConfigFromServer("", true, false, "0", false, false);
        }


    }
  /*  @OnClick({R.id.edSearch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edSearch:
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                search = edSearch.getText().toString().trim();
                getTableConfigFromServer(search, true, false, "0", false, false);
                break;

        }
    }*/

}
