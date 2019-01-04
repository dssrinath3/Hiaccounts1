package in.hiaccounts.hinext.reports.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
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
import com.yinglan.keyboard.HideUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hiaccounts.R;
import in.hiaccounts.hinext.reports.adapter.SelectCategoryListAdapter;
import in.hiaccounts.hinext.reports.model.CategoryList;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;

public class Activity_CategoryList extends AppCompatActivity implements TextWatcher {

    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edSearch)
    EditText edSearch;
    @BindView(R.id.imgviewSearch)
    ImageView imgviewSearch;
    @BindView(R.id.tvCategoryId)
    TextView tvCategoryId;
    @BindView(R.id.tvCategoryName)
    TextView tvCategoryName;
    @BindView(R.id.tvCategoryDesc)
    TextView tvCategoryDesc;
    @BindView(R.id.listviewCategory)
    ListView listviewCategory;
    @BindView(R.id.llListView)
    LinearLayout llListView;
    @BindView(R.id.llEmployeeList)
    LinearLayout llEmployeeList;
    @BindView(R.id.rlContent)
    RelativeLayout rlContent;
    private Activity mActivity;
    private ServiceHandler serviceHandler;
    private Boolean isInternetPresent = false;
    private SharedPreference sharedPreference;
    private String serverUrl;
    private List<CategoryList> categoryList;
    private SelectCategoryListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
        ButterKnife.bind(this);
        initComponent();
    }

    private void initComponent() {
        mActivity = this;
        serverUrl = UtilView.getUrl(mActivity);
        ButterKnife.bind(this);
        sharedPreference = SharedPreference.getInstance(mActivity);
        serviceHandler = new ServiceHandler(mActivity);

        edSearch.addTextChangedListener(this);
        toolbar.setTitle(getResources().getString(R.string.select_category));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getCategoryFromserver("");

        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    getCategoryFromserver(edSearch.getText().toString().trim());

                }
                return handled;
            }
        });

        listviewCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String callingFrom = getIntent().getStringExtra("callingfrom");
                    if (callingFrom!=null){
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("categorylist", categoryList.get(position));
                        mActivity.setResult(Activity.RESULT_OK, returnIntent);
                        mActivity.finish();
                    }


            }
        });
    }

    private void getCategoryFromserver(String search) {
        String url="";
        if (!search.equals("")){
             url = serverUrl + "/hipos//1/" + Constant.FUNTION_GETCATEGORYLIST + "?itemCategorySearchText="+ search.replace(" ", "%20");
        }else{
            url = serverUrl + "/hipos//1/" + Constant.FUNTION_GETCATEGORYLIST + "?itemCategorySearchText=";
        }

        if (serverUrl!=null) {
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {
                // prepare the Request
                UtilView.showProgessBar(mActivity, progressBar);
                GetDataTask task = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(mActivity, progressBar);
                        if (result != null) {
                            try {
                                categoryList = new ArrayList<CategoryList>();
                                JSONArray resultJsonArray = new JSONArray(result.toString());
                                for (int i = 0; i < resultJsonArray.length(); i++) {
                                    JSONObject asJson = resultJsonArray.getJSONObject(i);
                                    Gson gson = new Gson();
                                    CategoryList data = gson.fromJson(asJson.toString(), CategoryList.class);
                                    categoryList.add(data);
                                }
                                if (categoryList!=null && categoryList.size()>0){
                                     adapter = new SelectCategoryListAdapter(mActivity,categoryList);
                                    listviewCategory.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                }
                                else {
                                    categoryList.clear();
                                     adapter = new SelectCategoryListAdapter(mActivity, categoryList);
                                    listviewCategory.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                    UtilView.showErrorDialog("Category not available", mActivity);

                                }
                            }catch (Exception e){
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
        }else {
            UtilView.gotToLogin(mActivity);
        }
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
            getCategoryFromserver("");
        }
    }

    @OnClick({R.id.imgviewSearch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgviewSearch:
                getCategoryFromserver(edSearch.getText().toString().trim());
                break;

        }
    }
}
