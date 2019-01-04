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
import in.hiaccounts.hinext.inventory.adapter.BrandAdapter;
import in.hiaccounts.hinext.inventory.model.brand.Brand;
import in.hiaccounts.hinext.inventory.model.brand.BrandData;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 2/9/2017.
 */

public class Fragment_InventoryBrand extends Fragment implements TextWatcher {
    public static String TAG = Fragment_InventoryBrand.class.getSimpleName();
    @BindView(R.id.progress_bar)
   ProgressView progressBar;
    @BindView(R.id.lvBrand)
    ListView lvBrand;
    @BindView(R.id.edSearchBrand)
    EditText edSearchBrand;
    @BindView(R.id.llSearchBrand)
    LinearLayout llSearchBrand;
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
    private int pageNo = 0;
    private Unbinder unbinder;
    private ServiceHandler serviceHandler;
    private Activity activity;
    private Boolean isInternetPresent = false;
    private List<Object> brandList = new ArrayList<Object>();
    private BrandAdapter brandAdapter;
    private String brandSearch = "",serverUrl;

    public static Fragment_InventoryBrand newInstance() {
        Fragment_InventoryBrand fragment = new Fragment_InventoryBrand();
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
        View view = inflater.inflate(R.layout.fragment_inventorybrand, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponents(view);
        return view;
    }

    private void initComponents(View view) {
        setHasOptionsMenu(true);
        serverUrl= UtilView.getUrl(activity);
        serviceHandler = new ServiceHandler(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.inventory_adapter_brandview, null, false);
        TextView tvBrandName = header.findViewById(R.id.tvBrandName);
        TextView tvBrandDescription = header.findViewById(R.id.tvBrandDescription);
        TextView tvBrandEdit = header.findViewById(R.id.tvBrandEdit);
        tvBrandName.setText("Name");
        tvBrandDescription.setText("Description");
        tvBrandEdit.setText("Action");
        UtilView.setTextAppearanceSmall(activity, tvBrandName);
        UtilView.setTextAppearanceSmall(activity, tvBrandDescription);
        UtilView.setTextAppearanceSmall(activity, tvBrandEdit);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvBrandName);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvBrandDescription);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvBrandEdit);
        if (lvBrand != null)
            lvBrand.addHeaderView(header);
        if (edSearchBrand != null)
            edSearchBrand.addTextChangedListener(this);

        getBrandFromServer("", true, false, "0", false, false);

        edSearchBrand.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(activity, edSearchBrand);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    brandSearch = edSearchBrand.getText().toString().trim();
                    getBrandFromServer(brandSearch, true, false, "0", false, false);
                }
                return handled;
            }
        });

        lvBrand.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Brand brand = (Brand) brandList.get(position);

                editBrand(brand);
            }
        });
    }

    private void getBrandFromServer(String search, boolean isFirst, boolean isPrev, String pageNumber, boolean isNext, boolean isLast) {
        String url = "";
        if (search.equals("")) {
            url = serverUrl + "/hipos/0/" + Constant.FUNTION_GETBRANDS + "?brandItemSearchText=&type=&firstPage=" + isFirst + "&lastPage=" + isLast + "&nextPage=" + isNext + "&pageNo=" + pageNumber + "&prevPage=" + isPrev;
        }
        if (!search.equals("")) {
            url = serverUrl + "/hipos/0/" + Constant.FUNTION_GETBRANDS + "?brandItemSearchText=" + search.replace(" ", "%20") + "&type=&firstPage=" + isFirst + "&lastPage=" + isLast + "&nextPage=" + isNext + "&pageNo=" + pageNumber + "&prevPage=" + isPrev;
        }
        if (serverUrl!=null) {
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
                            brandList.clear();
                            List<Brand> list = new ArrayList<>();
                            try {

                            BrandData brandData = gson.fromJson(result.toString(), BrandData.class);
                            if (brandData != null) {
                                setUpView(brandData);
                                if (brandData.getData() != null && brandData.getData().size() > 0) {
                                    list = brandData.getData();
                                    brandList.addAll(list);
                                    brandAdapter = new BrandAdapter(activity, brandList);
                                    if (lvBrand != null)
                                        lvBrand.setAdapter(brandAdapter);
                                    brandAdapter.notifyDataSetChanged();

                                } else {
                                    brandList.clear();
                                    brandAdapter = new BrandAdapter(activity, brandList);
                                    if (lvBrand != null)
                                        lvBrand.setAdapter(brandAdapter);
                                    brandAdapter.notifyDataSetChanged();
                                    UtilView.showErrorDialog(getResources().getString(R.string.brand_notavailbale), activity);
                                }
                            }

                            }catch (Exception e){
                                UtilView.showErrorDialog(getResources().getString(R.string.error_null), activity);
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
        }
        else {
            UtilView.gotToLogin(activity);
        }
    }

    private void setUpView(BrandData brandData) {
        if (llPaginationView != null)
            llPaginationView.setVisibility(View.VISIBLE);

        if (brandData.isFirst()) {
            //  tvFirst.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
            if (tvFirst != null) {
                tvFirst.setTextColor(getResources().getColor(R.color.colorBlack));
                tvFirst.setClickable(false);
            }

        }
        if (!brandData.isFirst()) {
            // tvFirst.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
            if (tvFirst != null) {
                tvFirst.setTextColor(getResources().getColor(R.color.colorWhite));
                tvFirst.setClickable(true);
            }
        }


        if (brandData.isNext()) {
            // tvNext.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
            if (tvNext != null) {
                tvNext.setTextColor(getResources().getColor(R.color.colorBlack));
                tvNext.setClickable(false);
            }

        }
        if (!brandData.isNext()) {
            //  tvNext.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
            if (tvNext != null) {
                tvNext.setTextColor(getResources().getColor(R.color.colorWhite));
                tvNext.setClickable(true);
            }
        }


        if (brandData.isPrev()) {
            //  tvPrev.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
            if (tvPrev != null) {
                tvPrev.setTextColor(getResources().getColor(R.color.colorBlack));
                tvPrev.setClickable(false);
            }

        }
        if (!brandData.isPrev()) {
            //  tvPrev.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
            if (tvPrev != null) {
                tvPrev.setTextColor(getResources().getColor(R.color.colorWhite));
                tvPrev.setClickable(true);
            }
        }

        if (brandData.isLast()) {
            //  tvLast.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
            if (tvLast != null) {
                tvLast.setTextColor(getResources().getColor(R.color.colorBlack));
                tvLast.setClickable(false);
            }
        }
        if (!brandData.isLast()) {
            //   tvLast.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
            if (tvLast != null) {
                tvLast.setTextColor(getResources().getColor(R.color.colorWhite));
                tvLast.setClickable(true);
            }
        }
        pageNo = brandData.getPageNo();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tvFirst, R.id.tvPrev, R.id.tvNext, R.id.tvLast, R.id.llSearchBrand})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llSearchBrand:
                UtilView.hideSoftKeyboard(activity, edSearchBrand);
                brandSearch = edSearchBrand.getText().toString().trim();
                getBrandFromServer(brandSearch, true, false, "0", false, false);
                break;

            case R.id.tvFirst:
                pageNo = 0;
                getBrandFromServer("", true, false, "0", false, false);
                break;
            case R.id.tvPrev:
                if (pageNo > 0)
                    pageNo = pageNo - 1;
                getBrandFromServer("", false, true, "" + pageNo, false, false);
                break;
            case R.id.tvNext:
                pageNo = pageNo + 1;
                getBrandFromServer("", false, false, "" + pageNo, true, false);
                break;
            case R.id.tvLast:
                pageNo = 0;
                getBrandFromServer("", false, false, "0", false, true);
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
            UtilView.hideSoftKeyboard(activity, edSearchBrand);
            brandSearch = "";
            getBrandFromServer(brandSearch, true, false, "0", false, false);
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
                final Dialog dialog = new Dialog(activity);
                dialog.setContentView(R.layout.dialog_inventory_addbrand);
                dialog.setCancelable(false);
                final EditText edName = dialog.findViewById(R.id.edName);
                final EditText edDescritpion = dialog.findViewById(R.id.edDescritpion);
                final Spinner spinnerStatus = dialog.findViewById(R.id.spinnerStatus);


                Button btnClose = dialog.findViewById(R.id.btnClose);
                Button btnAdd = dialog.findViewById(R.id.btnAdd);
                final ProgressView pgview = dialog.findViewById(R.id.progress_bar);

                UtilView.setupAdapter(activity, spinnerStatus, getResources().getStringArray(R.array.config_status));
                if (btnClose != null)
                    btnClose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (dialog != null) dialog.dismiss();
                        }
                    });

                edName.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        edName.setError(null);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });



                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String bName = edName.getText().toString().trim();
                        String bDescription = edDescritpion.getText().toString().trim();
                        if (bName == null || bName.equals("")) {
                            edName.setError("Name can't be empty");
                        } else {

                            addBrand(bName, bDescription, dialog, pgview, spinnerStatus);
                        }
                    }
                });
                if (dialog != null)
                    dialog.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void addBrand(String bName, String bDescription, final Dialog dialog, final ProgressView pgview, Spinner spinnerStatus) {
        String url = serverUrl + "/hipos//0/" + Constant.FUNTION_ADDBRAND;
        if (serverUrl!=null) {


            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {
                // prepare the Request
                if (pgview != null)
                    pgview.setVisibility(View.VISIBLE);
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("brandName", bName);
                    jsonObject.put("brandDescription", bDescription);
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

                            Brand brand = gson.fromJson(result.toString(), Brand.class);
                            if (brand != null) {
                                UtilView.showToast(activity, "Brand " + brand.getBrandName() + " Add Successfully");
                                if (dialog != null && dialog.isShowing()) {
                                    dialog.dismiss();
                                }
                                getBrandFromServer("", true, false, "0", false, false);
                            } else {
                                UtilView.showToast(activity, "Category cant add. Please Try Again.");
                            }

                            }catch (Exception e){
                                UtilView.showErrorDialog(activity.getResources().getString(R.string.response_error),activity);
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
        }else {
            UtilView.gotToLogin(activity);
        }
    }

    private void editBrand(final Brand brand) {
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.dialog_inventory_addbrand);
        dialog.setCancelable(false);
        final EditText edName = dialog.findViewById(R.id.edName);
        final EditText edDescritpion = dialog.findViewById(R.id.edDescritpion);
        final Spinner spinnerStatus = dialog.findViewById(R.id.spinnerStatus);
        Button btnClose = dialog.findViewById(R.id.btnClose);
        Button btnAdd = dialog.findViewById(R.id.btnAdd);
        final ProgressView pgview = dialog.findViewById(R.id.progress_bar);
        UtilView.setupAdapter(activity, spinnerStatus, getResources().getStringArray(R.array.config_status));
        if (btnAdd != null)
            btnAdd.setText("Update");

        if (edName != null)
            edName.setText(brand.getBrandName());
        if (edDescritpion != null)
            edDescritpion.setText(brand.getBrandDescription());

        if (brand.getStatus() != null) {
            if (brand.getStatus().equals("Active")) {
                spinnerStatus.setSelection(0);
            }

            if (brand.getStatus().equals("InActive")) {
                spinnerStatus.setSelection(1);
            }
        }

        if (btnClose != null)
            btnClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dialog != null) dialog.dismiss();
                }
            });

        edName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edName.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String bName = edName.getText().toString().trim();
                String bDescription = edDescritpion.getText().toString().trim();

                if (bName == null || bName.equals("")) {
                    edName.setError("Name can't be empty");

                } else {
                    String url = serverUrl + "/hipos//" + brand.getBrandId() + "/" + Constant.FUNTION_ADDBRAND;

                    if (!url.equals("")) {
                        isInternetPresent = serviceHandler.isConnectingToInternet();
                        if (isInternetPresent) {
                            // prepare the Request
                            if (pgview != null)
                                pgview.setVisibility(View.VISIBLE);

                            brand.setBrandName(bName);
                            brand.setBrandDescription(bDescription);
                            brand.setStatus((String) spinnerStatus.getSelectedItem());

                            PostDataTask postDataTask = new PostDataTask(activity, new AsyncTaskCompleteListener<String>() {
                                @Override
                                public void onTaskComplete(String result) {
                                    if (pgview != null)
                                        pgview.setVisibility(View.GONE);
                                    if (result != null) {
                                        try {

                                            Gson gson = new Gson();
                                            Brand brand = gson.fromJson(result.toString(), Brand.class);
                                            if (brand != null) {

                                                if (dialog != null && dialog.isShowing()) {
                                                    dialog.dismiss();
                                                }

                                                UtilView.showToast(activity, "Brand Update Successfully");
                                                getBrandFromServer("", true, false, "0", false, false);
                                            } else {
                                                UtilView.showToast(activity, "Brand not update successfully. Please Try Again.");
                                            }
                                        } catch (Exception e) {
                                            UtilView.showErrorDialog(activity.getResources().getString(R.string.response_error), activity);
                                        }
                                    } else {
                                        UtilView.showToast(activity, activity.getResources().getString(R.string.response_error));
                                    }
                                }
                            }, false);
                            postDataTask.execute(new Gson().toJson(brand).toString(), url, "");


                        }
                        if (!isInternetPresent) {
                            UtilView.showErrorDialog(activity.getResources().getString(R.string.intertnet_status), activity);
                        }
                    }
                }
            }
        });

        if (dialog != null)
            dialog.show();
    }
}
