package in.hiaccounts.hinext.inventory.fragment;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.hiaccounts.R;
import in.hiaccounts.hinext.inventory.adapter.AssestsCategoryAdapter;
import in.hiaccounts.hinext.inventory.model.assestscategory.AssestsCategory;
import in.hiaccounts.hinext.inventory.model.assestscategory.AssestsCategoryData;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_InventoryAssests_Category extends Fragment implements TextWatcher {

    public static String TAG = Fragment_InventoryAssests_Category.class.getSimpleName();
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.lvAssestsCategory)
    ListView lvAssestsCategory;
    @BindView(R.id.edSearchAssestsCategory)
    EditText edSearchAssestsCategory;
    @BindView(R.id.llSearchAssestsCategory)
    LinearLayout llSearchAssestsCategory;
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
    Unbinder unbinder1;

    CheckBox check;
    private ServiceHandler serviceHandler;
    private Activity activity;
    private Boolean isInternetPresent = false;
    private List<Object> categoryList = new ArrayList<Object>();
    private String categorySearch = "", serverUrl;
    private int pageNo = 0;
    List<AssestsCategory> catList;
    AssestsCategoryAdapter assetCategoryAdpater;

    public static Fragment_InventoryAssests_Category newInstance() {
        Fragment_InventoryAssests_Category fragment = new Fragment_InventoryAssests_Category();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inventory_assests_category, container, false);
        unbinder1 = ButterKnife.bind(this, view);
        initComponents(view);
        return view;
    }

    private void initComponents(View view) {
        setHasOptionsMenu(true);
        serverUrl = UtilView.getUrl(activity);
        serviceHandler = new ServiceHandler(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.inventory_adapter_assests_categoryview, null, false);
        TextView tvAssestsCategoryCode = header.findViewById(R.id.tvAssestsCategoryCode);
        TextView tvAssestsCategoryName = header.findViewById(R.id.tvAssestsCategoryName);
        TextView tvAssestsCategoryDescrip = header.findViewById(R.id.tvAssestsCategoryDescrip);
        TextView tvAssestsCategoryEdit = header.findViewById(R.id.tvAssestsCategoryEdit);
        tvAssestsCategoryCode.setText("Assests Category Code");
        tvAssestsCategoryName.setText("Name");
        tvAssestsCategoryDescrip.setText("Description");
        tvAssestsCategoryEdit.setText("Action");
        UtilView.setTextAppearanceSmall(activity, tvAssestsCategoryCode);
        UtilView.setTextAppearanceSmall(activity, tvAssestsCategoryName);
        UtilView.setTextAppearanceSmall(activity, tvAssestsCategoryDescrip);
        UtilView.setTextAppearanceSmall(activity, tvAssestsCategoryEdit);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvAssestsCategoryCode);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvAssestsCategoryName);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvAssestsCategoryDescrip);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvAssestsCategoryEdit);
        if (lvAssestsCategory != null)
            lvAssestsCategory.addHeaderView(header);
        if (edSearchAssestsCategory != null)
            edSearchAssestsCategory.addTextChangedListener(this);

        getAssestsCategoryFromServer("", true, false, "0", false, false);

        edSearchAssestsCategory.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(activity, edSearchAssestsCategory);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    categorySearch = edSearchAssestsCategory.getText().toString().trim();
                    getAssestsCategoryFromServer(categorySearch, true, false, "0", false, false);
                }
                return handled;
            }
        });

        lvAssestsCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AssestsCategory category = (AssestsCategory) categoryList.get(position);

                editAssestsCategory(category);
            }
        });
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
                dialogAddAssestsCategory();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void dialogAddAssestsCategory() {
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.dialog_inventory_add_assestscategory);
        dialog.setCancelable(false);
        final EditText edAssestsCategoryName = dialog.findViewById(R.id.edAssestCategoryName);
        final EditText edAssestsCategoryDescritpion = dialog.findViewById(R.id.edAssestsCategoryDescritpion);
        final Spinner spinnerStatus = dialog.findViewById(R.id.spinnerStatus);
        Button btnClose = dialog.findViewById(R.id.btnClose);
        Button btnAdd = dialog.findViewById(R.id.btnAdd);
        TextView defaultType = dialog.findViewById(R.id.id_default_type);
        check = dialog.findViewById(R.id.checkbox_type);

        UtilView.setupAdapter(activity, spinnerStatus, getResources().getStringArray(R.array.config_status));
        final ProgressView pgview = dialog.findViewById(R.id.progress_bar);

        if (btnClose != null){
            btnClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(dialog!=null)
                        dialog.dismiss();
                }
            });
        }
        edAssestsCategoryName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                edAssestsCategoryName.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        if (btnAdd != null)
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String cName = edAssestsCategoryName.getText().toString().trim();
                    String cDescription = edAssestsCategoryDescritpion.getText().toString().trim();
                    boolean defaultType = defaultTypeCheckMehtod();
                    if (cName == null || cName.equals("")) {
                        edAssestsCategoryName.setError("Name can't be empty");
                    } else {
                        addAssestsCategroy(cName, cDescription, defaultType, dialog, pgview,spinnerStatus);
                    }
                }
            });

        if (dialog != null)
            dialog.show();
    }

    private void addAssestsCategroy(String assestsCategoryName, String assestsCategoryDescription, boolean defaultType, final Dialog dialog, final ProgressView pgview, Spinner spinnerStatus) {
        String url = serverUrl + "/hipos/1/" + Constant.FUNTION_ADDASSESTCATEGORY;
        if (serverUrl != null) {
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {
                if (pgview != null)
                    pgview.setVisibility(View.VISIBLE);
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("assestsCategoryName", assestsCategoryName);
                    jsonObject.put("assestsCategoryDesc", assestsCategoryDescription);
                    jsonObject.put("restaurentStatus", defaultType);
                    jsonObject.put("status", spinnerStatus.getSelectedItem());

                } catch (Exception e) {
                }
                PostDataTask postDataTask = new PostDataTask(activity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        if (pgview != null)
                            pgview.setVisibility(View.GONE);
                        if (result != null) {
                            Gson gson = new Gson();
                            try {

                                AssestsCategory category = gson.fromJson(result.toString(), AssestsCategory.class);
                                if (category != null) {
                                    UtilView.showToast(activity, "Category " + category.getAssestsCategoryName() + " Add Successfully");
                                    if (dialog != null && dialog.isShowing()) {
                                        dialog.dismiss();
                                    }
                                    getAssestsCategoryFromServer("", true, false, "0", false, false);
                                } else {
                                    UtilView.showToast(activity, "Category cant add. Please Try Again.");
                                }

                            } catch (Exception e) {
                                UtilView.showErrorDialog(activity.getResources().getString(R.string.response_error), activity);
                            }
                        } else {
                            UtilView.showToast(activity, activity.getResources().getString(R.string.response_error));
                        }
                    }
                }, false);
                postDataTask.execute(jsonObject.toString(), url, "");
            }
            if (!isInternetPresent) {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
            }
        } else {
            UtilView.gotToLogin(activity);
        }
    }

    private void getAssestsCategoryFromServer(String search, boolean isFirst, boolean isPrev, String pageNumber, boolean isNext, boolean isLast) {
        String url = "";
        if (search.equals(""))
            url = serverUrl + "/hipos/0/" + Constant.FUNTION_GETASSETSCATGEGORY + "?assestCategorySearchText=&firstPage=" + isFirst + "&lastPage=" + isLast + "&nextPage=" + isNext + "&pageNo=" + pageNumber + "&prevPage=" + isPrev;
        if (!search.equals(""))
            url = serverUrl + "/hipos/0/" + Constant.FUNTION_GETASSETSCATGEGORY + "?assestCategorySearchText=" + search + "&firstPage=" + isFirst + "&lastPage=" + isLast + "&nextPage=" + isNext + "&pageNo=" + pageNumber + "&prevPage=" + isPrev;
        if (serverUrl != null) {
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {
                // prepare the Request
                UtilView.showProgessBar(activity, progressBar);
                GetDataTask task = new GetDataTask(activity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(activity, progressBar);
                        if (result != null) {
                            Gson gson = new Gson();
                            categoryList.clear();
                            catList = new ArrayList<>();
                            try {
                                AssestsCategoryData categoryData = gson.fromJson(result.toString(), AssestsCategoryData.class);
                                if (categoryData != null) {
                                    setUpView(categoryData);
                                    if (categoryData.getData() != null && categoryData.getData().size() > 0) {
                                        catList = categoryData.getData();
                                        categoryList.addAll(catList);
                                        assetCategoryAdpater = new AssestsCategoryAdapter(activity, categoryList);
                                        if (lvAssestsCategory != null)
                                            lvAssestsCategory.setAdapter(assetCategoryAdpater);
                                            assetCategoryAdpater.notifyDataSetChanged();
                                    } else {
                                        categoryList.clear();
                                        assetCategoryAdpater = new AssestsCategoryAdapter(activity, categoryList);
                                        if (lvAssestsCategory != null)
                                            lvAssestsCategory.setAdapter(assetCategoryAdpater);
                                            assetCategoryAdpater.notifyDataSetChanged();
                                            UtilView.showErrorDialog(getResources().getString(R.string.category_notavailbale), activity);
                                    }
                                }
                            } catch (Exception e) {
                                UtilView.showLogCat(TAG, "TaskCompleteListener Exception " + e.toString());
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
        } else {
            UtilView.gotToLogin(activity);
        }
    }

    private void setUpView(AssestsCategoryData categoryData) {

        llPaginationView.setVisibility(View.VISIBLE);
        if (llPaginationView != null)
            llPaginationView.setVisibility(View.VISIBLE);

        if (categoryData.getFirst()) {
            //  tvFirst.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
            if (tvFirst != null) {
                tvFirst.setTextColor(getResources().getColor(R.color.colorBlack));
                tvFirst.setClickable(false);
            }

        }
        if (!categoryData.getFirst()) {
            // tvFirst.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
            if (tvFirst != null) {
                tvFirst.setTextColor(getResources().getColor(R.color.colorWhite));
                tvFirst.setClickable(true);
            }
        }


        if (categoryData.getNext()) {
            // tvNext.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
            if (tvNext != null) {
                tvNext.setTextColor(getResources().getColor(R.color.colorBlack));
                tvNext.setClickable(false);
            }

        }
        if (!categoryData.getNext()) {
            //  tvNext.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
            if (tvNext != null) {
                tvNext.setTextColor(getResources().getColor(R.color.colorWhite));
                tvNext.setClickable(true);
            }
        }


        if (categoryData.getPrev()) {
            //  tvPrev.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
            if (tvPrev != null) {
                tvPrev.setTextColor(getResources().getColor(R.color.colorBlack));
                tvPrev.setClickable(false);
            }

        }
        if (!categoryData.getPrev()) {
            //  tvPrev.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
            if (tvPrev != null) {
                tvPrev.setTextColor(getResources().getColor(R.color.colorWhite));
                tvPrev.setClickable(true);
            }
        }

        if (categoryData.getLast()) {
            //  tvLast.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
            if (tvLast != null) {
                tvLast.setTextColor(getResources().getColor(R.color.colorBlack));
                tvLast.setClickable(false);
            }
        }
        if (!categoryData.getLast()) {
            //   tvLast.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
            if (tvLast != null) {
                tvLast.setTextColor(getResources().getColor(R.color.colorWhite));
                tvLast.setClickable(true);
            }
        }

        pageNo = categoryData.getPageNo();


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
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
            UtilView.hideSoftKeyboard(activity, edSearchAssestsCategory);
            categorySearch = "";
            getAssestsCategoryFromServer(categorySearch, true, false, "0", false, false);
        }
    }

    @OnClick({R.id.llSearchAssestsCategory, R.id.tvFirst, R.id.tvPrev, R.id.tvNext, R.id.tvLast})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llSearchAssestsCategory:
                categorySearch = edSearchAssestsCategory.getText().toString().trim();
                getAssestsCategoryFromServer(categorySearch, true, false, "0", false, false);
                break;
            case R.id.tvFirst:
                pageNo = 0;
                getAssestsCategoryFromServer("", true, false, "0", false, false);
                break;
            case R.id.tvPrev:
                if (pageNo > 0)
                    pageNo = pageNo - 1;
                getAssestsCategoryFromServer("", false, true, "" + pageNo, false, false);
                break;
            case R.id.tvNext:
                pageNo = pageNo + 1;
                getAssestsCategoryFromServer("", false, false, "" + pageNo, true, false);
                break;
            case R.id.tvLast:
                pageNo = 0;
                getAssestsCategoryFromServer("", false, false, "0", false, true);
                break;
        }
    }

    public void editAssestsCategory(final AssestsCategory assestsCategory) {
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.dialog_inventory_add_assestscategory);
        dialog.setCancelable(false);
        final EditText edAssestsCategoryName = dialog.findViewById(R.id.edAssestCategoryName);
        final EditText edAssestsCategoryDescritpion = dialog.findViewById(R.id.edAssestsCategoryDescritpion);
        final Spinner spinnerStatus = dialog.findViewById(R.id.spinnerStatus);

        Button btnClose = dialog.findViewById(R.id.btnClose);
        Button btnAdd = dialog.findViewById(R.id.btnAdd);
        TextView defaultType = dialog.findViewById(R.id.id_default_type);
        check = dialog.findViewById(R.id.checkbox_type);
        final ProgressView pgview = dialog.findViewById(R.id.progress_bar);
        UtilView.setupAdapter(activity, spinnerStatus, getResources().getStringArray(R.array.config_status));


        edAssestsCategoryName.setFocusable(false);
        edAssestsCategoryName.setFocusableInTouchMode(false);
        edAssestsCategoryName.setBackgroundColor(activity.getResources().getColor(R.color.colorGrey));

        if (edAssestsCategoryDescritpion != null) {
            edAssestsCategoryDescritpion.setText(assestsCategory.getAssestsCategoryDesc());
        }
        if (edAssestsCategoryName != null) {
            edAssestsCategoryName.setText(assestsCategory.getAssestsCategoryName());
        }
        if(assestsCategory.getRestaurentStatus() != null) {
            if (assestsCategory.getRestaurentStatus()== true ) {
                check.setChecked(true);
            } else {
                check.setChecked(false);
            }
        }

        if (assestsCategory.getStatus() != null) {
            if (assestsCategory.getStatus().equals("Active")) {
                spinnerStatus.setSelection(0);
            }

            if (assestsCategory.getStatus().equals("InActive")) {
                spinnerStatus.setSelection(1);
            }
        }

        if (btnAdd != null)
            btnAdd.setText("Update");

        if (btnClose != null)
            btnClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dialog != null) dialog.dismiss();
                }
            });

        edAssestsCategoryName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                edAssestsCategoryName.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        if (btnAdd != null) {
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isInternetPresent = serviceHandler.isConnectingToInternet();
                    if (isInternetPresent) {
                        String cName = edAssestsCategoryName.getText().toString().trim();
                        String cDescription = edAssestsCategoryDescritpion.getText().toString().trim();


                        assestsCategory.setAssestsCategoryName(cName);
                        assestsCategory.setAssestsCategoryDesc(cDescription);
                        assestsCategory.setRestaurentStatus(defaultTypeCheckMehtod());
                        assestsCategory.setStatus((String) spinnerStatus.getSelectedItem());
                        if (pgview != null)
                            pgview.setVisibility(View.VISIBLE);

                        String url = serverUrl+ "/hipos/" + assestsCategory.getAssestsCategoryId() + "/" + Constant.FUNTION_ADDASSESTCATEGORY;
                        PostDataTask postDataTask = new PostDataTask(activity, new AsyncTaskCompleteListener<String>() {
                            @Override
                            public void onTaskComplete(String result) {
                                if (pgview != null)
                                    pgview.setVisibility(View.GONE);
                                if (result != null) {
                                    try {

                                        Gson gson = new Gson();
                                        AssestsCategory category = gson.fromJson(result.toString(), AssestsCategory.class);
                                        if (category != null) {

                                            UtilView.showToast(activity, "Assests Category update Successfully");
                                            if (dialog != null)
                                                dialog.dismiss();

                                        } else {
                                            UtilView.showToast(activity, "Assests Category not update. Please try again.");
                                        }

                                    } catch (Exception e) {
                                        UtilView.showErrorDialog(activity.getResources().getString(R.string.response_error), activity);
                                    }
                                } else {
                                    UtilView.showToast(activity, activity.getResources().getString(R.string.response_error));
                                }
                            }
                        }, false);
                        postDataTask.execute(new Gson().toJson(assestsCategory).toString(), url, "");
                    } else {
                        UtilView.showErrorDialog(activity.getResources().getString(R.string.intertnet_status), activity);
                    }
                }
            });
        }
        if (dialog != null)
            dialog.show();
    }


    private boolean defaultTypeCheckMehtod() {
        return check.isChecked();
    }
}
