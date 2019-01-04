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
import in.hiaccounts.hinext.inventory.adapter.CategoryAdapter;
import in.hiaccounts.hinext.inventory.model.category.Category;
import in.hiaccounts.hinext.inventory.model.category.CategoryData;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;


/**
 * Created by Prateek on 2/9/2017.
 */

public class Fragment_InventoryCategory extends Fragment implements TextWatcher {

    public static String TAG = Fragment_InventoryCategory.class.getSimpleName();
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.lvCategory)
    ListView lvCategory;
    @BindView(R.id.edSearchCategory)
    EditText edSearchCategory;
    @BindView(R.id.llSearchCategory)
    LinearLayout llSearchCategory;
    Unbinder unbinder;
    @BindView(R.id.tvFirst)
    TextView tvFirst;
    @BindView(R.id.tvPrev)
    TextView tvPrev;
    @BindView(R.id.tvNext)
    TextView tvNext;
    @BindView(R.id.tvLast)
    TextView tvLast;
    @BindView(R.id.llPaginationView)
    LinearLayout llPaginationView;
    CheckBox check;
    List<Category> catList;
    private ServiceHandler serviceHandler;
    private Activity activity;
    private Boolean isInternetPresent = false;
    private List<Object> categoryList = new ArrayList<Object>();
    private CategoryAdapter categoryAdapter;
    private String categorySearch = "", serverUrl;
    private int pageNo = 0;

    public static Fragment_InventoryCategory newInstance() {
        Fragment_InventoryCategory fragment = new Fragment_InventoryCategory();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Returning the layout file after inflating
        View view = inflater.inflate(R.layout.fragment_inventorycategory, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponents(view);
        return view;
    }

    private void initComponents(View view) {
        setHasOptionsMenu(true);
        serverUrl = UtilView.getUrl(activity);
        serviceHandler = new ServiceHandler(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.inventory_adapter_categoryview, null, false);
        TextView tvCategoryCode = header.findViewById(R.id.tvCategoryCode);
        TextView tvCategoryName = header.findViewById(R.id.tvCategoryName);
        TextView tvCategoryDescrip = header.findViewById(R.id.tvCategoryDescrip);
        TextView tvCategoryEdit = header.findViewById(R.id.tvCategoryEdit);
        tvCategoryCode.setText("Code");
        tvCategoryName.setText("Name");
        tvCategoryDescrip.setText("Description");
        tvCategoryEdit.setText("Action");
        UtilView.setTextAppearanceSmall(activity, tvCategoryCode);
        UtilView.setTextAppearanceSmall(activity, tvCategoryName);
        UtilView.setTextAppearanceSmall(activity, tvCategoryDescrip);
        UtilView.setTextAppearanceSmall(activity, tvCategoryEdit);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvCategoryCode);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvCategoryName);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvCategoryDescrip);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvCategoryEdit);
        if (lvCategory != null)
            lvCategory.addHeaderView(header);
        if (edSearchCategory != null)
            edSearchCategory.addTextChangedListener(this);

        getCategoryFromServer("", true, false, "0", false, false);

        edSearchCategory.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(activity, edSearchCategory);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    categorySearch = edSearchCategory.getText().toString().trim();
                    getCategoryFromServer(categorySearch, true, false, "0", false, false);
                }
                return handled;
            }
        });

        lvCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Category category = (Category) categoryList.get(position);
                editCategory(category);
            }
        });
    }

    private void getCategoryFromServer(String search, boolean isFirst, boolean isPrev, String pageNumber, boolean isNext, boolean isLast) {
        String url = "";
        if (search.equals(""))
            url = serverUrl + "/hipos/0/" + Constant.FUNTION_GETCATGEGORY + "?itemCategorySearchText=&type=&firstPage=" + isFirst + "&lastPage=" + isLast + "&nextPage=" + isNext + "&pageNo=" + pageNumber + "&prevPage=" + isPrev;
        if (!search.equals(""))
            url = serverUrl + "/hipos/0/" + Constant.FUNTION_GETCATGEGORY + "?itemCategorySearchText=" + search.replace(" ", "%20") + "&type=&firstPage=" + isFirst + "&lastPage=" + isLast + "&nextPage=" + isNext + "&pageNo=" + pageNumber + "&prevPage=" + isPrev;
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
                                CategoryData categoryData = gson.fromJson(result.toString(), CategoryData.class);
                                if (categoryData != null) {
                                    setUpView(categoryData);
                                    if (categoryData.getData() != null && categoryData.getData().size() > 0) {
                                        catList = categoryData.getData();
                                        categoryList.addAll(catList);
                                        categoryAdapter = new CategoryAdapter(activity, categoryList);
                                        if (lvCategory != null)
                                            lvCategory.setAdapter(categoryAdapter);
                                        categoryAdapter.notifyDataSetChanged();
                                    } else {
                                        categoryList.clear();
                                        categoryAdapter = new CategoryAdapter(activity, categoryList);
                                        if (lvCategory != null)
                                            lvCategory.setAdapter(categoryAdapter);
                                        categoryAdapter.notifyDataSetChanged();
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

    private void setUpView(CategoryData categoryData) {

        llPaginationView.setVisibility(View.VISIBLE);
        if (llPaginationView != null)
            llPaginationView.setVisibility(View.VISIBLE);

        if (categoryData.isFirst()) {
            //  tvFirst.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
            if (tvFirst != null) {
                tvFirst.setTextColor(getResources().getColor(R.color.colorBlack));
                tvFirst.setClickable(false);
            }

        }
        if (!categoryData.isFirst()) {
            // tvFirst.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
            if (tvFirst != null) {
                tvFirst.setTextColor(getResources().getColor(R.color.colorWhite));
                tvFirst.setClickable(true);
            }
        }


        if (categoryData.isNext()) {
            // tvNext.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
            if (tvNext != null) {
                tvNext.setTextColor(getResources().getColor(R.color.colorBlack));
                tvNext.setClickable(false);
            }

        }
        if (!categoryData.isNext()) {
            //  tvNext.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
            if (tvNext != null) {
                tvNext.setTextColor(getResources().getColor(R.color.colorWhite));
                tvNext.setClickable(true);
            }
        }


        if (categoryData.isPrev()) {
            //  tvPrev.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
            if (tvPrev != null) {
                tvPrev.setTextColor(getResources().getColor(R.color.colorBlack));
                tvPrev.setClickable(false);
            }

        }
        if (!categoryData.isPrev()) {
            //  tvPrev.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
            if (tvPrev != null) {
                tvPrev.setTextColor(getResources().getColor(R.color.colorWhite));
                tvPrev.setClickable(true);
            }
        }

        if (categoryData.isLast()) {
            //  tvLast.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
            if (tvLast != null) {
                tvLast.setTextColor(getResources().getColor(R.color.colorBlack));
                tvLast.setClickable(false);
            }
        }
        if (!categoryData.isLast()) {
            //   tvLast.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
            if (tvLast != null) {
                tvLast.setTextColor(getResources().getColor(R.color.colorWhite));
                tvLast.setClickable(true);
            }
        }

        pageNo = categoryData.getPageNo();


    }

    private void addCategroy(String categoryName, String categoryDescription, boolean defaultType, final Dialog dialog, final ProgressView pgview, Spinner spinnerStatus) {
        String url = serverUrl + "/hipos//0/" + Constant.FUNTION_ADDCATEGORY;

        if (serverUrl != null) {
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {
                // prepare the Request
                if (pgview != null)
                    pgview.setVisibility(View.VISIBLE);
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("itemCategoryName", categoryName);
                    jsonObject.put("itemCategoryDesc", categoryDescription);
                    jsonObject.put("defaultType", defaultType);
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

                                Category category = gson.fromJson(result.toString(), Category.class);
                                if (category != null) {
                                    UtilView.showToast(activity, "Category " + category.getItemCategoryName() + " Add Successfully");
                                    if (dialog != null && dialog.isShowing()) {
                                        dialog.dismiss();
                                    }
                                    getCategoryFromServer("", true, false, "0", false, false);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null)
            unbinder.unbind();
    }

    @OnClick({R.id.llSearchCategory, R.id.tvFirst, R.id.tvPrev, R.id.tvNext, R.id.tvLast})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llSearchCategory:
                categorySearch = edSearchCategory.getText().toString().trim();
                getCategoryFromServer(categorySearch, true, false, "0", false, false);
                break;
            case R.id.tvFirst:
                pageNo = 0;
                getCategoryFromServer("", true, false, "0", false, false);
                break;
            case R.id.tvPrev:
                if (pageNo > 0)
                    pageNo = pageNo - 1;
                getCategoryFromServer("", false, true, "" + pageNo, false, false);
                break;
            case R.id.tvNext:
                pageNo = pageNo + 1;
                getCategoryFromServer("", false, false, "" + pageNo, true, false);
                break;
            case R.id.tvLast:
                pageNo = 0;
                getCategoryFromServer("", false, false, "0", false, true);
                break;
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
            UtilView.hideSoftKeyboard(activity, edSearchCategory);
            categorySearch = "";
            getCategoryFromServer(categorySearch, true, false, "0", false, false);
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
                dialogAddCategory();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void dialogAddCategory() {
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.dialog_inventory_addcategory);
        dialog.setCancelable(false);
        final EditText edCategoryName = dialog.findViewById(R.id.edCategoryName);
        final EditText edCategoryDescritpion = dialog.findViewById(R.id.edCategoryDescritpion);
        Button btnClose = dialog.findViewById(R.id.btnClose);
        Button btnAdd = dialog.findViewById(R.id.btnAdd);
        TextView defaultType = dialog.findViewById(R.id.id_default_type);
        final Spinner spinnerStatus = dialog.findViewById(R.id.spinnerStatus);
        check = dialog.findViewById(R.id.checkbox_type);
        final ProgressView pgview = dialog.findViewById(R.id.progress_bar);

        UtilView.setupAdapter(activity, spinnerStatus, getResources().getStringArray(R.array.config_status));

        if (btnClose != null)
            btnClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dialog != null) dialog.dismiss();
                }
            });

        edCategoryName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edCategoryName.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        if (btnAdd != null)
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String cName = edCategoryName.getText().toString().trim();
                    String cDescription = edCategoryDescritpion.getText().toString().trim();
                    boolean defaultType = defaultTypeCheckMehtod();
                    if (cName == null || cName.equals("")) {
                        edCategoryName.setError("Name can't be empty");
                    } else {
                        addCategroy(cName, cDescription, defaultType, dialog, pgview,spinnerStatus);
                    }
                }
            });
        if (dialog != null)
            dialog.show();
    }


    public void editCategory(final Category category) {
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.dialog_inventory_addcategory);
        dialog.setCancelable(false);
        final EditText edCategoryName = dialog.findViewById(R.id.edCategoryName);
        final EditText edCategoryDescritpion = dialog.findViewById(R.id.edCategoryDescritpion);
        TextView defaultType = dialog.findViewById(R.id.id_default_type);
         check = dialog.findViewById(R.id.checkbox_type);
        final Spinner spinnerStatus = dialog.findViewById(R.id.spinnerStatus);

        Button btnClose = dialog.findViewById(R.id.btnClose);
        Button btnAdd = dialog.findViewById(R.id.btnAdd);
        final ProgressView pgview = dialog.findViewById(R.id.progress_bar);
        UtilView.setupAdapter(activity, spinnerStatus, getResources().getStringArray(R.array.config_status));
        edCategoryName.setFocusable(false);
        edCategoryName.setFocusableInTouchMode(false);
        edCategoryName.setBackgroundColor(activity.getResources().getColor(R.color.colorGrey));

        if (edCategoryDescritpion != null) {
            edCategoryDescritpion.setText(category.getItemCategoryDesc());
        }

        if (edCategoryName != null) {
            edCategoryName.setText(category.getItemCategoryName());
        }
        if (category.getStatus() != null) {
            if (category.getStatus().equals("Active")) {
                spinnerStatus.setSelection(0);
            }

            if (category.getStatus().equals("InActive")) {
                spinnerStatus.setSelection(1);
            }
        }
        if(category.getDefaultType() != null){

            if (category.getDefaultType() == true){
                check.setChecked(true);
            }else{
                check.setChecked(false);
            }
        }
        else {
            check.setChecked(false);
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

        edCategoryName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                edCategoryName.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        if (btnAdd != null)
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isInternetPresent = serviceHandler.isConnectingToInternet();
                    if (isInternetPresent) {
                        String cName = edCategoryName.getText().toString().trim();
                        String cDescription = edCategoryDescritpion.getText().toString().trim();

                        category.setItemCategoryName(cName);
                        category.setItemCategoryDesc(cDescription);
                        category.setDefaultType(defaultTypeCheckMehtod());
                        category.setStatus((String) spinnerStatus.getSelectedItem());
                        if (pgview != null)
                            pgview.setVisibility(View.VISIBLE);
                        String url = serverUrl+ "/hipos//" + category.getItemCategoryId() + "/" + Constant.FUNTION_ADDCATEGORY;
                        PostDataTask postDataTask = new PostDataTask(activity, new AsyncTaskCompleteListener<String>() {
                            @Override
                            public void onTaskComplete(String result) {
                                if (pgview != null)
                                    pgview.setVisibility(View.GONE);
                                if (result != null) {
                                    try {

                                        Gson gson = new Gson();
                                        Category category = gson.fromJson(result.toString(), Category.class);
                                        if (category != null) {

                                            UtilView.showToast(activity, "Category update Successfully");
                                            if (dialog != null)
                                                dialog.dismiss();

                                        } else {
                                            UtilView.showToast(activity, "Category not update. Please try again.");
                                        }

                                    } catch (Exception e) {
                                        UtilView.showErrorDialog(activity.getResources().getString(R.string.response_error), activity);
                                    }
                                } else {
                                    UtilView.showToast(activity, activity.getResources().getString(R.string.response_error));
                                }
                            }
                        }, false);
                        postDataTask.execute(new Gson().toJson(category).toString(), url, "");
                    } else {
                        UtilView.showErrorDialog(activity.getResources().getString(R.string.intertnet_status), activity);
                    }
                }
            });
        if (dialog != null)
            dialog.show();
    }
    private boolean defaultTypeCheckMehtod() {
        return check.isChecked();
    }
}
