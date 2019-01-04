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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.hiaccounts.R;
import in.hiaccounts.hinext.inventory.adapter.UOMAdapter;
import in.hiaccounts.hinext.inventory.model.uom.UOM;
import in.hiaccounts.hinext.inventory.model.uom.UomData;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 2/9/2017.
 */

public class Fragment_InventoryUOM extends Fragment implements TextWatcher {


    public static final String TAG = Fragment_InventoryUOM.class.getSimpleName();
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.lvUOM)
    ListView lvUOM;
    @BindView(R.id.edSearchUOM)
    EditText edSearchUOM;
    @BindView(R.id.llSearchUOM)
    LinearLayout llSearchUOM;
    @BindView(R.id.ll)
    LinearLayout ll;
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
    Unbinder unbinder;
    private int pageNo = 0;
    private ServiceHandler serviceHandler;
    private Activity activity;
    private Boolean isInternetPresent = false;
    private List<Object> uomList = new ArrayList<Object>();
    private UOMAdapter uomAdapter;
    private String uomSearch = "",serverUrl;

    public static Fragment_InventoryUOM newInstance() {
        Fragment_InventoryUOM fragment = new Fragment_InventoryUOM();
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
        View view = inflater.inflate(R.layout.fragment_inventoryuom, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponents(view);
        return view;
    }

    private void initComponents(View view) {
        setHasOptionsMenu(true);
        serviceHandler = new ServiceHandler(activity);
        serverUrl= UtilView.getUrl(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.inventory_adapter_uomview, null, false);
        TextView tvUomName = header.findViewById(R.id.tvUomName);
        TextView tvUomDescription = header.findViewById(R.id.tvUomDescription);
        TextView tvUomEdit = header.findViewById(R.id.tvUomEdit);
        tvUomName.setText("Name");
        tvUomDescription.setText("Description");
        tvUomEdit.setText("Action");
        UtilView.setTextAppearanceSmall(activity, tvUomName);
        UtilView.setTextAppearanceSmall(activity, tvUomDescription);
        UtilView.setTextAppearanceSmall(activity, tvUomEdit);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvUomName);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvUomDescription);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvUomEdit);
        if (lvUOM != null)
            lvUOM.addHeaderView(header);
        edSearchUOM.addTextChangedListener(this);
        getUOMFromServer("", true, false, "0", false, false);

        edSearchUOM.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(activity, edSearchUOM);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    uomSearch = edSearchUOM.getText().toString().trim();
                    getUOMFromServer(uomSearch, true, false, "0", false, false);
                }
                return handled;
            }
        });

        lvUOM.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UOM uom = (UOM) uomList.get(position);

                editUom(uom);
            }
        });
    }



    private void getUOMFromServer(String search, boolean isFirst, boolean isPrev, String pageNumber, boolean isNext, boolean isLast) {
        String url = "";
        if (search.equals("")) {
            url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETOPMS + "?uomItemSearchText=&type=&firstPage=" + isFirst + "&lastPage=" + isLast + "&nextPage=" + isNext + "&pageNo=" + pageNumber + "&prevPage=" + isPrev;
        }
        if (!search.equals("")) {
            url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETOPMS + "?uomItemSearchText=" + search.replace(" ", "%20") + "&type=&firstPage=" + isFirst + "&lastPage=" + isLast + "&nextPage=" + isNext + "&pageNo=" + pageNumber + "&prevPage=" + isPrev;
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
                            try {
                            uomList.clear();
                            List<UOM> list = new ArrayList<>();
                            UomData data = gson.fromJson(result.toString(), UomData.class);
                            if (data != null) {
                                setUpView(data);
                                if (data.getData() != null && data.getData().size() > 0) {
                                    list = data.getData();
                                    uomList.addAll(list);
                                    uomAdapter = new UOMAdapter(activity, uomList);
                                    if (lvUOM != null)
                                        lvUOM.setAdapter(uomAdapter);
                                    uomAdapter.notifyDataSetChanged();

                                } else {

                                    uomList.clear();
                                    uomAdapter = new UOMAdapter(activity, uomList);
                                    if (lvUOM != null)
                                        lvUOM.setAdapter(uomAdapter);
                                    uomAdapter.notifyDataSetChanged();
                                    UtilView.showSuccessDialog(getResources().getString(R.string.uom_notavailbale), activity);
                                }
                            }


                            }catch (Exception e){
                                UtilView.showErrorDialog(activity.getResources().getString(R.string.response_error),activity);
                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.error_null), activity);
                        }
                    }
                }, false);
                task.execute(url, "");
                //task.execute(url, "");
            }
            if (!isInternetPresent) {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
            }
        }else {
            UtilView.gotToLogin(activity);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null)
            unbinder.unbind();
    }

    @OnClick({R.id.tvFirst, R.id.tvPrev, R.id.tvNext, R.id.tvLast, R.id.llSearchUOM})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llSearchUOM:
                UtilView.hideSoftKeyboard(activity, edSearchUOM);
                uomSearch = edSearchUOM.getText().toString().trim();
                pageNo = 0;
                getUOMFromServer(uomSearch, true, false, "0", false, false);
                break;
            case R.id.tvFirst:
                pageNo = 0;
                getUOMFromServer("", true, false, "0", false, false);
                break;
            case R.id.tvPrev:
                if (pageNo > 0) {
                    pageNo = pageNo - 1;
                }
                getUOMFromServer("", false, true, "" + pageNo, false, false);
                break;
            case R.id.tvNext:
                pageNo = pageNo + 1;
                getUOMFromServer("", false, false, "" + pageNo, true, false);
                break;
            case R.id.tvLast:
                pageNo = 0;
                getUOMFromServer("", false, false, "0", false, true);
                break;
        }
    }

    private void addUOM() {

        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.dialog_inventory_adduom);
        dialog.setCancelable(false);
        final EditText edName = dialog.findViewById(R.id.edName);
        final EditText edDescritpion = dialog.findViewById(R.id.edDescritpion);
        final Spinner spinnerStatus = dialog.findViewById(R.id.spinnerStatus);
        Button btnClose = dialog.findViewById(R.id.btnClose);
        Button btnAdd = dialog.findViewById(R.id.btnAdd);
        final ProgressView pgview = dialog.findViewById(R.id.progress_bar);
        UtilView.setupAdapter(activity, spinnerStatus, getResources().getStringArray(R.array.config_status));
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
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
                String uomName = edName.getText().toString().trim();
                String uomDescription = edDescritpion.getText().toString().trim();
                if (uomName == null || uomName.equals("")) {
                    edName.setError("Name can't be empty");
                } else {
                    String url = serverUrl + "/hipos//0/" + Constant.FUNTION_ADDUOM;
                    if (serverUrl!=null) {
                        isInternetPresent = serviceHandler.isConnectingToInternet();
                        if (isInternetPresent) {
                            // prepare the Request
                            if (pgview != null)
                                pgview.setVisibility(View.VISIBLE);
                            UOM uom = new UOM();
                            uom.setUnitofmeasurementname(uomName);
                            uom.setUnitOfMeasurementDescription(uomDescription);
                            uom.setUnitOfMeasurementStatus((String) spinnerStatus.getSelectedItem());
                            PostDataTask postDataTask = new PostDataTask(activity, new AsyncTaskCompleteListener<String>() {
                                @Override
                                public void onTaskComplete(String result) {
                                    if (pgview != null)
                                        pgview.setVisibility(View.GONE);
                                    if (result!= null) {
                                        Gson gson = new Gson();
                                        try {
                                        UOM serverReponse = gson.fromJson(result.toString(), UOM.class);
                                        if (serverReponse != null) {
                                            UtilView.showToast(activity, "Unit Added Successfully");
                                            if (dialog != null && dialog.isShowing()) {
                                                dialog.dismiss();
                                            }
                                            getUOMFromServer("", true, false, "0", false, false);

                                        } else {
                                            UtilView.showToast(activity, "Unit cant add. Please Try Again.");
                                        }


                                        }catch (Exception e){
                                            UtilView.showErrorDialog(activity.getResources().getString(R.string.response_error),activity);
                                        }
                                    } else {
                                        UtilView.showToast(activity, activity.getResources().getString(R.string.response_error));
                                    }
                                }
                            }, false);
                            postDataTask.execute(new Gson().toJson(uom).toString(), url, "");
                        }
                        if (!isInternetPresent) {
                            UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
                        }
                    }else {
                        UtilView.gotToLogin(activity);
                    }
                }
            }
        });
        if (dialog != null)
            dialog.show();
    }

    private void editUom(final UOM uom) {
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.dialog_inventory_adduom);
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

        if (edName != null) {
            edName.setFocusable(false);
            edName.setFocusableInTouchMode(false);
            edName.setBackgroundColor(activity.getResources().getColor(R.color.colorGrey));

            if (uom.getUnitofmeasurementname() != null)
                edName.setText(uom.getUnitofmeasurementname());
        }
        if (edDescritpion != null) {
            if (uom.getUnitOfMeasurementDescription() != null)
                edDescritpion.setText(uom.getUnitOfMeasurementDescription());
        }

        if (uom.getUnitOfMeasurementStatus() != null) {
            if (uom.getUnitOfMeasurementStatus().equals("Active")) {
                spinnerStatus.setSelection(0);
            }

            if (uom.getUnitOfMeasurementStatus().equals("InActive")) {
                spinnerStatus.setSelection(1);
            }
        }

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


                String uomName = edName.getText().toString().trim();
                String uomDescription = edDescritpion.getText().toString().trim();

                if (uomName == null || uomName.equals("")) {
                    edName.setError("Name can't be empty");

                } else {
                    String url = "";
                    if (uom.getUnitofmeasurementid() != null) {
                        url = serverUrl + "/hipos//" + uom.getUnitofmeasurementid() + "/" + Constant.FUNTION_ADDUOM;
                    }


                    if (!url.equals("")) {
                        isInternetPresent = serviceHandler.isConnectingToInternet();
                        if (isInternetPresent) {

                            if (pgview != null)
                                pgview.setVisibility(View.VISIBLE);


                            uom.setUnitofmeasurementname(uomName);
                            uom.setUnitOfMeasurementDescription(uomDescription);
                            uom.setUnitOfMeasurementStatus((String) spinnerStatus.getSelectedItem());

                            PostDataTask postDataTask = new PostDataTask(activity, new AsyncTaskCompleteListener<String>() {
                                @Override
                                public void onTaskComplete(String result) {
                                    if (pgview != null)
                                        pgview.setVisibility(View.GONE);
                                    if (result != null) {
                                        Gson gson = new Gson();
                                        try {

                                            UOM uom = gson.fromJson(result.toString(), UOM.class);
                                            if (uom != null) {

                                                if (dialog != null && dialog.isShowing()) {
                                                    dialog.dismiss();
                                                }

                                                UtilView.showToast(activity, "Unit Update Successfully");
                                                getUOMFromServer("", true, false, "0", false, false);
                                            } else {
                                                UtilView.showToast(activity, "Unit not update successfully. Please Try Again.");
                                            }
                                        } catch (Exception e) {
                                            UtilView.showErrorDialog(activity.getResources().getString(R.string.response_error), activity);
                                        }

                                    } else {
                                        UtilView.showToast(activity, activity.getResources().getString(R.string.response_error));
                                    }
                                }
                            }, false);
                            postDataTask.execute(new Gson().toJson(uom).toString(), url, "");
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

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.toString().equals("")) {
            UtilView.hideSoftKeyboard(activity, edSearchUOM);
            uomSearch = "";
            getUOMFromServer(uomSearch, true, false, "0", false, false);
        }
    }

    private void setUpView(UomData data) {
        if (llPaginationView != null)
            llPaginationView.setVisibility(View.VISIBLE);
        if (data.isFirst()) {
            //  tvFirst.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
            if (tvFirst != null) {
                tvFirst.setTextColor(getResources().getColor(R.color.colorBlack));
                tvFirst.setClickable(false);
            }
        }
        if (!data.isFirst()) {
            // tvFirst.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
            if (tvFirst != null) {
                tvFirst.setTextColor(getResources().getColor(R.color.colorWhite));
                tvFirst.setClickable(true);
            }
        }
        if (data.isNext()) {
            // tvNext.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
            if (tvNext != null) {
                tvNext.setTextColor(getResources().getColor(R.color.colorBlack));
                tvNext.setClickable(false);
            }
        }
        if (!data.isNext()) {
            //  tvNext.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
            if (tvNext != null) {
                tvNext.setTextColor(getResources().getColor(R.color.colorWhite));
                tvNext.setClickable(true);
            }
        }
        if (data.isPrev()) {
            //  tvPrev.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
            if (tvPrev != null) {
                tvPrev.setTextColor(getResources().getColor(R.color.colorBlack));
                tvPrev.setClickable(false);
            }
        }
        if (!data.isPrev()) {
            //  tvPrev.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
            if (tvPrev != null) {
                tvPrev.setTextColor(getResources().getColor(R.color.colorWhite));
                tvPrev.setClickable(true);
            }
        }
        if (data.isLast()) {
            //  tvLast.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
            if (tvLast != null) {
                tvLast.setTextColor(getResources().getColor(R.color.colorBlack));
                tvLast.setClickable(false);
            }
        }
        if (!data.isLast()) {
            //   tvLast.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
            if (tvLast != null) {
                tvLast.setTextColor(getResources().getColor(R.color.colorWhite));
                tvLast.setClickable(true);
            }
        }
        pageNo = data.getPageNo();
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
                addUOM();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
