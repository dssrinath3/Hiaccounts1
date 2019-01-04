package in.hiaccounts.hinext.supplier.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
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
import butterknife.OnClick;
import in.hiaccounts.R;
import in.hiaccounts.hinext.supplier.adapter.SupplierList_Adapter;
import in.hiaccounts.hinext.supplier.model.GetSupplier;
import in.hiaccounts.hinext.supplier.model.SupplierPageData;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 5/8/2017.
 */

public class Activity_Supplier extends AppCompatActivity implements TextWatcher {
    public static String TAG = Activity_Supplier.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edSearch)
    EditText edSearch;
    @BindView(R.id.imgviewSearch)
    ImageView imgviewSearch;
    @BindView(R.id.lvSupplier)
    ListView lvSupplier;
    @BindView(R.id.llSupplierList)
    LinearLayout llSupplierList;
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
    @BindView(R.id.rlContent)
    RelativeLayout rlContent;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.llListView)
    LinearLayout llListView;
    private Activity mActivity;
    private ServiceHandler serviceHandler;
    private Boolean isInternetPresent = false;
    private SharedPreference sharedPreference;
    private String search_cname = "";
    private List<GetSupplier> supplierList = new ArrayList<GetSupplier>();
    private String callingFrom,serverUrl;
    private int pageNo = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_supplier);
        ButterKnife.bind(this);
        initViewCompenents();

    }

    private void initViewCompenents() {
        ButterKnife.bind(this);
        mActivity = this;
        callingFrom = getIntent().getStringExtra("callingFrom");
        serverUrl=UtilView.getUrl(mActivity);

        toolbar.setTitle(getResources().getString(R.string.selectSupplier));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        serviceHandler = new ServiceHandler(mActivity);

        edSearch.addTextChangedListener(this);
/*
        LayoutInflater inflater = mActivity.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.adapter_purchase_supplier, null, false);

        TextView tvSContact = (TextView) header.findViewById(R.id.tvSContact);
        TextView tvSEmail = (TextView) header.findViewById(R.id.tvSEmail);
        TextView tvSName = (TextView) header.findViewById(R.id.tvSName);
        TextView tvEdit = (TextView) header.findViewById(R.id.tvEdit);
        tvEdit.setText("Action");
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvEdit);
        UtilView.setTexttypeFace(null, Typeface.BOLD, tvEdit);


        tvSContact.setText(getResources().getString(R.string.s_contact));
        tvSEmail.setText(getResources().getString(R.string.s_email));
        tvSName.setText(getResources().getString(R.string.s_name));

        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvSContact);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvSEmail);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvSName);
        UtilView.setTexttypeFace(null, Typeface.BOLD, tvSContact);
        UtilView.setTexttypeFace(null, Typeface.BOLD, tvSEmail);
        UtilView.setTexttypeFace(null, Typeface.BOLD, tvSName);

        if (lvSupplier != null)
            lvSupplier.addHeaderView(header);*/

        getSupplierFromserver(search_cname, true, false, 0, false, false);
        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    getSupplierFromserver(edSearch.getText().toString().trim(), true, false, 0, false, false);
                }
                return handled;
            }
        });
        lvSupplier.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (callingFrom != null && callingFrom.equals(Constant.NAVIGATION_GROUP_CONTACT)) {
                } else {
                    Intent returnIntent = new Intent();
                        returnIntent.putExtra("supplier", supplierList.get(position));
                        mActivity.setResult(Activity.RESULT_OK, returnIntent);
                        mActivity.finish();

                }
            }
        });
    }

    private void getSupplierFromserver(String search, boolean isFirst, boolean isPrev, int pageNumber, boolean isNext, boolean isLast) {
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (isInternetPresent) {
            String url = "";
            if (search.equals("")) {
                url = serverUrl + "/hipos/0/" + Constant.FUNTION_GETSUPPLIERLIST + "?searchSupplierText=&type=&firstPage=" + isFirst + "&lastPage=" + isLast + "&nextPage=" + isNext + "&pageNo=" + pageNumber + "&prevPage=" + isPrev;
            }
            if (!search.equals("")) {
                url = serverUrl + "/hipos/0/" + Constant.FUNTION_GETSUPPLIERLIST + "?searchSupplierText=" + search.replace(" ", "%20") + "&type=&firstPage=" + isFirst + "&lastPage=" + isLast + "&nextPage=" + isNext + "&pageNo=" + pageNumber + "&prevPage=" + isPrev;
            }
            if (serverUrl!=null) {
                UtilView.showProgessBar(mActivity, progressBar);
                GetDataTask task = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(mActivity, progressBar);
                        if (result != null) {
                            Gson gson = new Gson();
                            try {

                            supplierList.clear();
                            List<GetSupplier> list = new ArrayList<>();
                            SupplierPageData data = gson.fromJson(result.toString(), SupplierPageData.class);
                            if (data != null) {
                                setUpView(data);
                                if (data.getData() != null && data.getData().size() > 0) {
                                    list = data.getData();
                                    supplierList.addAll(list);
                                    llSupplierList.setVisibility(View.VISIBLE);
                                    llListView.setVisibility(View.VISIBLE);
                                    SupplierList_Adapter customerListAdapter = new SupplierList_Adapter(mActivity, supplierList);
                                    lvSupplier.setAdapter(customerListAdapter);
                                    customerListAdapter.notifyDataSetChanged();

                                } else {
                                    edSearch.setText("");
                                    supplierList.clear();
                                    SupplierList_Adapter customerListAdapter = new SupplierList_Adapter(mActivity, supplierList);
                                    lvSupplier.setAdapter(customerListAdapter);
                                    customerListAdapter.notifyDataSetChanged();
                                    UtilView.showErrorDialog(getResources().getString(R.string.nosupplier_available), mActivity);
                                    llListView.setVisibility(View.GONE);
                                }

                            }

                            }catch (Exception e){
                                UtilView.showErrorDialog(getResources().getString(R.string.response_error),mActivity);
                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.response_error), mActivity);
                        }
                    }
                }, false);
                task.execute(url, "");
            }else {
                UtilView.gotToLogin(mActivity);
            }
        } else {
            UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
        }

    }

    @OnClick({R.id.tvFirst, R.id.tvPrev, R.id.tvNext, R.id.tvLast, R.id.imgviewSearch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgviewSearch:
                getSupplierFromserver(edSearch.getText().toString().trim(), true, false, 0, false, false);
                break;

            case R.id.tvFirst:
                pageNo = 0;
                getSupplierFromserver("", true, false, pageNo, false, false);
                break;
            case R.id.tvPrev:
                if (pageNo > 0) {
                    pageNo = pageNo - 1;
                }
                getSupplierFromserver("", false, true, pageNo, false, false);
                break;
            case R.id.tvNext:
                pageNo = pageNo + 1;
                getSupplierFromserver("", false, false, pageNo, true, false);
                break;
            case R.id.tvLast:
                pageNo = 0;
                getSupplierFromserver("", false, false, 0, false, true);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constant.RESQUSTCODE_ADDSUUPLIER && resultCode == RESULT_OK) {
            getSupplierFromserver("", true, false, 0, false, false);
        }
        if (requestCode == Constant.RESQUSTCODE_EDITSUUPLIER && resultCode == RESULT_OK) {
            getSupplierFromserver("", true, false, 0, false, false);
        }


    }

    private void setUpView(SupplierPageData data) {

        if (rlContent != null)
            rlContent.setVisibility(View.VISIBLE);

        if (data.isFirst()) {
            //  tvFirst.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
            tvFirst.setTextColor(getResources().getColor(R.color.colorBlack));
            tvFirst.setClickable(false);

        }
        if (!data.isFirst()) {
            // tvFirst.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
            tvFirst.setTextColor(getResources().getColor(R.color.colorWhite));
            tvFirst.setClickable(true);
        }


        if (data.isNext()) {
            // tvNext.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
            tvNext.setTextColor(getResources().getColor(R.color.colorBlack));
            tvNext.setClickable(false);

        }
        if (!data.isNext()) {
            //  tvNext.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
            tvNext.setTextColor(getResources().getColor(R.color.colorWhite));
            tvNext.setClickable(true);
        }


        if (data.isPrev()) {
            //  tvPrev.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
            tvPrev.setTextColor(getResources().getColor(R.color.colorBlack));
            tvPrev.setClickable(false);

        }
        if (!data.isPrev()) {
            //  tvPrev.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
            tvPrev.setTextColor(getResources().getColor(R.color.colorWhite));
            tvPrev.setClickable(true);
        }

        if (data.isLast()) {
            //  tvLast.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
            tvLast.setTextColor(getResources().getColor(R.color.colorBlack));
            tvLast.setClickable(false);
        }
        if (!data.isLast()) {
            //   tvLast.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
            tvLast.setTextColor(getResources().getColor(R.color.colorWhite));
            tvLast.setClickable(true);
        }

        pageNo = data.getPageNo();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.inventory_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.addMenu:
                Intent intent = new Intent(mActivity, Activity_AddSupplier.class);
                intent.putExtra("callingFrom", Constant.SUPPLIER_ADD);
                startActivityForResult(intent, Constant.RESQUSTCODE_ADDSUUPLIER);

                break;


        }
        return super.onOptionsItemSelected(item);
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
            getSupplierFromserver("", true, false, 0, false, false);
        }
    }
}
