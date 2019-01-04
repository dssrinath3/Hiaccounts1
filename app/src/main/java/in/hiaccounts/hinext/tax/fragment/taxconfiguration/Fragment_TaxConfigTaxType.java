package in.hiaccounts.hinext.tax.fragment.taxconfiguration;

import android.app.Activity;
import android.app.Dialog;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
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
import in.hiaccounts.hinext.tax.adapter.TaxTypeAdapter;
import in.hiaccounts.hinext.tax.model.taxconfig.AddTax;
import in.hiaccounts.hinext.tax.model.taxconfig.TaxClassId;
import in.hiaccounts.hinext.tax.model.taxconfig.TaxGroupId;
import in.hiaccounts.hinext.tax.model.taxconfig.TaxTypeData;
import in.hiaccounts.hinext.tax.model.taxconfig.TaxTypeDatum;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 2/9/2017.
 */

public class Fragment_TaxConfigTaxType extends Fragment implements TextWatcher {


    public static String TAG = Fragment_TaxConfigTaxType.class.getSimpleName();
    public Activity mActivity;

    Unbinder unbinder;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.listView)
    ListView listView;
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

    private ServiceHandler serviceHandler;
    Boolean isInternetPresent = false;
    List<TaxTypeDatum> taxTypeList = new ArrayList<>();
    TaxTypeAdapter taxTypeAdapter;
    ProgressView progress_bar;
    List<TaxClassId> taxClassList = new ArrayList<>();
    List<TaxGroupId> taxGroupList = new ArrayList<>();

    EditText edTaxType;
    EditText edTaxDescritpion;
    Spinner spinnerTaxClass;
    Spinner spinnerTaxGroup;
    Button btnSave, btnClose;
    TaxClassId taxClass;
    TaxGroupId taxGroup;
    TaxTypeDatum taxTypeDatum;
    String search = "", serverUrl;
    int pageNo = 0;

    public static Fragment_TaxConfigTaxType newInstance() {
        Fragment_TaxConfigTaxType fragment = new Fragment_TaxConfigTaxType();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;

    }

    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes
        View view = inflater.inflate(R.layout.fragment_taxconfigtaxtype, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponentView(view);
        return view;
    }

    private void initComponentView(View view) {

        setHasOptionsMenu(true);
        serverUrl = UtilView.getUrl(mActivity);
        serviceHandler = new ServiceHandler(mActivity);
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (isInternetPresent) {
            getTaxDetailsFromServer("", true, false, "0", false, false);
        } else {
            UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
        }
        LayoutInflater inflater = mActivity.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.tax_adapter_taxtypeheader, null, false);
        listView.addHeaderView(header);

        edSearch.addTextChangedListener(this);
        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    search = edSearch.getText().toString().trim();
                    isInternetPresent = serviceHandler.isConnectingToInternet();
                    if (isInternetPresent) {
                        getTaxDetailsFromServer(search, true, false, "0", false, false);
                    }
                    if (!isInternetPresent) {
                        UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                    }
                }
                return handled;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                if (view.getId() == R.id.imgviewEdit) {

                    dialogAddTaxType(taxTypeList.get(position));

                }

            }
        });


    }

    private void getTaxDetailsFromServer(String search, boolean isFirst, boolean isPrev, String pageNumber, boolean isNext, boolean isLast) {

        //  String url = Constant.NGROK_URL + Constant.URL_TAXPATH + Constant.FUNCTION_GETTAXTYPE;

        String url = "";
        if (search.equals("")) {
            url = serverUrl + Constant.URL_TAXPATH + Constant.FUNCTION_GETTAXTYPE + "" + "&firstPage=" + isFirst + "&lastPage=" + isLast + "&nextPage=" + isNext + "&pageNo=" + pageNumber + "&prevPage=" + isPrev;
        }
        if (!search.equals("")) {
            url = serverUrl + Constant.URL_TAXPATH + Constant.FUNCTION_GETTAXTYPE + search.replace(" ", "%20") + "&firstPage=" + isFirst + "&lastPage=" + isLast + "&nextPage=" + isNext + "&pageNo=" + pageNumber + "&prevPage=" + isPrev;
        }

        if (serverUrl != null) {
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {
                // prepare the Request
                if (progressBar != null)
                    progressBar.setVisibility(View.VISIBLE);
                GetDataTask task = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        if (progressBar != null)
                            progressBar.setVisibility(View.GONE);

                        if (result != null) {
                            if (result.equals("invalid")) {
                                UtilView.showToast(mActivity, getResources().getString(R.string.accesstoken_error));
                                Intent intent = new Intent(mActivity, Activity_Login.class);
                                mActivity.startActivity(intent);
                                mActivity.finish();
                            } else {

                                Gson gson = new Gson();
                                taxTypeList.clear();
                                try {

                                    TaxTypeData data = gson.fromJson(result.toString(), TaxTypeData.class);
                                    if (data != null) {
                                        if (listView != null) {
                                            setUpView(data);
                                            if (data.getData() != null && data.getData().size() > 0) {
                                                taxTypeList = data.getData();
                                                taxTypeAdapter = new TaxTypeAdapter(mActivity, taxTypeList);
                                                listView.setAdapter(taxTypeAdapter);
                                                taxTypeAdapter.notifyDataSetChanged();

                                            } else {

                                                taxTypeList.clear();
                                                taxTypeAdapter = new TaxTypeAdapter(mActivity, taxTypeList);
                                                listView.setAdapter(taxTypeAdapter);
                                                taxTypeAdapter.notifyDataSetChanged();
                                                UtilView.showSuccessDialog(getResources().getString(R.string.tax_notavailbale), mActivity);
                                            }
                                        }
                                    }

                                } catch (Exception e) {
                                    UtilView.showErrorDialog(getResources().getString(R.string.response_error), mActivity);
                                }
                            }
                        } else {
                            if (progressBar != null)
                                progressBar.setVisibility(View.GONE);
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


    private void setUpView(TaxTypeData data) {

        if (llPaginationView != null)
            llPaginationView.setVisibility(View.VISIBLE);

        if (tvFirst != null) {
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
        }

        if (tvNext != null) {
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
        }

        if (tvPrev != null) {
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
        }

        if (tvLast != null) {
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
        }
        pageNo = data.getPageNo();


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tvFirst, R.id.tvPrev, R.id.tvNext, R.id.tvLast, R.id.llSearch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
          /*  case fabAdd:
                isInternetPresent = serviceHandler.isConnectingToInternet();
                if (isInternetPresent) {
                    dialogAddTaxType(null);
                }
                if (!isInternetPresent) {
                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                }

                break;*/
            case R.id.llSearch:
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                search = edSearch.getText().toString().trim();
                isInternetPresent = serviceHandler.isConnectingToInternet();
                if (isInternetPresent) {
                    getTaxDetailsFromServer(search, true, false, "0", false, false);
                } else {
                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                }
                break;
            case R.id.tvFirst:
                isInternetPresent = serviceHandler.isConnectingToInternet();
                if (isInternetPresent) {
                    pageNo = 0;
                    getTaxDetailsFromServer("", true, false, "0", false, false);
                } else {
                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                }
                break;
            case R.id.tvPrev:
                isInternetPresent = serviceHandler.isConnectingToInternet();
                if (isInternetPresent) {

                    if (pageNo > 0) {
                        pageNo = pageNo - 1;
                    }
                    getTaxDetailsFromServer("", false, true, "" + pageNo, false, false);


                } else {
                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                }
                break;
            case R.id.tvNext:
                isInternetPresent = serviceHandler.isConnectingToInternet();
                if (isInternetPresent) {
                    pageNo = pageNo + 1;
                    getTaxDetailsFromServer("", false, false, "" + pageNo, true, false);
                } else {
                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                }
                break;
            case R.id.tvLast:
                isInternetPresent = serviceHandler.isConnectingToInternet();
                if (isInternetPresent) {
                    pageNo = 0;
                    getTaxDetailsFromServer("", false, false, "0", false, true);
                } else {
                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                }
                break;
        }
    }

    private void dialogAddTaxType(final TaxTypeDatum taxType) {

        taxTypeDatum = taxType;

        final Dialog dialog = new Dialog(mActivity);
        dialog.setContentView(R.layout.dialog_tax_taxtype);
        dialog.setCancelable(false);

        edTaxType = dialog.findViewById(R.id.edTaxType);
        edTaxDescritpion = dialog.findViewById(R.id.edTaxDescritpion);
        spinnerTaxClass = dialog.findViewById(R.id.spinnerTaxClass);
        spinnerTaxGroup = dialog.findViewById(R.id.spinnerTaxGroup);
        progress_bar = dialog.findViewById(R.id.progress_bar);
        btnSave = dialog.findViewById(R.id.btnAdd);
        btnClose = dialog.findViewById(R.id.btnClose);


        dialog.show();
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });
        progress_bar.setVisibility(View.VISIBLE);
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (isInternetPresent) {
            callTaxClassServer();
        }
        if (!isInternetPresent) {
            UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
        }


        edTaxType.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.toString().trim() == null || s.toString().trim().equals("")) {

                    edTaxType.setError(getResources().getString(R.string.err_msg));
                } else {
                    edTaxType.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String taxType = edTaxType.getText().toString().trim();
                String taxDescription = edTaxDescritpion.getText().toString().trim();

                if (taxClass != null && taxGroup != null && taxType != null && !taxType.equals("")) {

                    AddTax addTax = new AddTax();

                    if (taxTypeDatum != null) {

                        addTax.setTaxTypeId(taxTypeDatum.getTaxTypeId());
                    }

                    addTax.setTaxTypeName(taxType);
                    addTax.setTaxTypeDesc(taxDescription);
                    addTax.setTaxClassId(taxClass.getTaxClassId());
                    addTax.setTaxGroupId(taxGroup.getTaxGroupId());

                    String url = serverUrl + Constant.URL_TAXPATH + Constant.FUNCTION_SAVETAXTYPE;
                    if (serverUrl != null) {
                        progress_bar.setVisibility(View.VISIBLE);
                        PostDataTask postAddTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                            @Override
                            public void onTaskComplete(String result) {
                                if (result != null) {
                                    if (result.equals("invalid")) {
                                        UtilView.showToast(mActivity, getResources().getString(R.string.accesstoken_error));
                                        Intent intent = new Intent(mActivity, Activity_Login.class);
                                        mActivity.startActivity(intent);
                                        mActivity.finish();
                                    } else {

                                        if (result.equals(getString(R.string.error_rsonsecode204))) {
                                            if (taxTypeDatum != null) {
                                                UtilView.showToast(mActivity, "TaxType Update Successfully");
                                                dialog.dismiss();
                                                isInternetPresent = serviceHandler.isConnectingToInternet();
                                                if (isInternetPresent) {
                                                    getTaxDetailsFromServer(search, true, false, "0", false, false);
                                                }
                                                if (!isInternetPresent) {
                                                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                                                }
                                            } else {
                                                UtilView.showToast(mActivity, "TaxType Add Successfully");
                                                dialog.dismiss();
                                                isInternetPresent = serviceHandler.isConnectingToInternet();
                                                if (isInternetPresent) {
                                                    getTaxDetailsFromServer(search, true, false, "0", false, false);
                                                }
                                                if (!isInternetPresent) {
                                                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                                                }

                                            }

                                        } else {
                                            getTaxDetailsFromServer(search, true, false, "0", false, false);
                                            UtilView.showToast(mActivity, "TaxType Add Successfully");
                                            dialog.dismiss();
                                        }

                                    }
                                } else {

                                    UtilView.showErrorDialog(getResources().getString(R.string.error_null), mActivity);
                                }
                            }
                        }, false);

                        postAddTask.execute(new Gson().toJson(addTax).toString(), url, "");
                    } else {
                        UtilView.gotToLogin(mActivity);
                    }
                } else {

                    if (taxClass == null) {
                        TextView tv = (TextView) spinnerTaxClass.getSelectedView();
                        tv.setError(getString(R.string.err_msg));
                    }

                    if (taxGroup == null) {

                        TextView tv = (TextView) spinnerTaxClass.getSelectedView();
                        tv.setError(getString(R.string.err_msg));
                    }

                    if (taxType == null || taxType.equals("")) {
                        edTaxType.setError(getString(R.string.err_msg));
                    }
                }


            }
        });
    }

    private void callTaxClassServer() {
        String url = serverUrl + Constant.URL_TAXPATH + Constant.FUNCTION_GETTAXCLASS;

        if (serverUrl != null) {
            PostDataTask getTaxClassTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                @Override
                public void onTaskComplete(String result) {
                    if (result != null) {
                        if (result.equals("invalid")) {
                            UtilView.showToast(mActivity, getResources().getString(R.string.accesstoken_error));
                            Intent intent = new Intent(mActivity, Activity_Login.class);
                            mActivity.startActivity(intent);
                            mActivity.finish();
                        } else {
                            if (taxClassList != null)
                                taxClassList.clear();
                            try {
                                JSONArray jsonArray = new JSONArray(result.toString());

                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    TaxClassId taxClassId = new Gson().fromJson(jsonObject.toString(), TaxClassId.class);
                                    taxClassList.add(taxClassId);
                                }

                                callTaxGroupServer();


                            } catch (Exception e) {

                                UtilView.showErrorDialog(getResources().getString(R.string.response_error), mActivity);
                            }


                        }
                    } else {

                        UtilView.showErrorDialog(getResources().getString(R.string.error_null), mActivity);
                    }
                }


            }, false);
            getTaxClassTask.execute(new Gson().toString(), url, "");
        } else {
            UtilView.gotToLogin(mActivity);
        }
    }

    private void callTaxGroupServer() {
        String url1 = serverUrl + Constant.URL_TAXPATH + Constant.FUNCTION_GETTAXGROUP;
        if (serverUrl != null) {
            PostDataTask getTaxGroupTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                @Override
                public void onTaskComplete(String result) {
                    if (result != null) {
                        if (result.equals("invalid")) {
                            UtilView.showToast(mActivity, getResources().getString(R.string.accesstoken_error));
                            Intent intent = new Intent(mActivity, Activity_Login.class);
                            mActivity.startActivity(intent);
                            mActivity.finish();
                        } else {

                            if (taxGroupList != null) {
                                taxGroupList.clear();
                            }
                            progress_bar.setVisibility(View.GONE);

                            try {
                                JSONArray jsonArray = new JSONArray(result.toString());

                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    TaxGroupId taxGroupId = new Gson().fromJson(jsonObject.toString(), TaxGroupId.class);
                                    taxGroupList.add(taxGroupId);
                                }

                                setupSpinnerValue();
                            } catch (Exception je) {
                                UtilView.showErrorDialog(getResources().getString(R.string.response_error), mActivity);

                            }
                        }
                    } else {
                        UtilView.showErrorDialog(getResources().getString(R.string.error_null), mActivity);
                    }
                }
            }, false);
            getTaxGroupTask.execute(new Gson().toString(), url1, "");
        } else {
            UtilView.gotToLogin(mActivity);
        }
    }

    private void setupSpinnerValue() {

        if (progress_bar != null)
            progress_bar.setVisibility(View.GONE);


        if (taxClassList != null && taxClassList.size() > 0)
            UtilView.setupTaxClassAdapter(mActivity, spinnerTaxClass, taxClassList);

        if (taxGroupList != null && taxGroupList.size() > 0)
            UtilView.setupTaxGroupAdapter(mActivity, spinnerTaxGroup, taxGroupList);


        if (taxTypeDatum != null) {
            btnSave.setText("Update");
            if (taxTypeDatum.getTaxTypeName() != null)
                edTaxType.setText(taxTypeDatum.getTaxTypeName());

            if (taxTypeDatum.getTaxTypeDesc() != null)
                edTaxDescritpion.setText(taxTypeDatum.getTaxTypeName());


            if (taxTypeDatum.getTaxClassId() != null) {

                for (int i = 0; i < taxClassList.size(); i++) {
                    if (taxTypeDatum.getTaxClassId().getTaxClassId() == (taxClassList.get(i).getTaxClassId())) {
                        spinnerTaxClass.setSelection(i);
                    }
                }
            }


            if (taxTypeDatum.getTaxGroupId() != null) {

                for (int i = 0; i < taxGroupList.size(); i++) {
                    if (taxTypeDatum.getTaxGroupId().getTaxGroupId() == (taxGroupList.get(i).getTaxGroupId())) {
                        spinnerTaxGroup.setSelection(i);
                    }
                }
            }


        }
        spinnerTaxClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                spinnerTaxClass.setSelection(position);
                taxClass = (TaxClassId) spinnerTaxClass.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerTaxGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                spinnerTaxGroup.setSelection(position);
                taxGroup = (TaxGroupId) spinnerTaxGroup.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {
                getTaxDetailsFromServer(search, true, false, "0", false, false);

            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
            }

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
                isInternetPresent = serviceHandler.isConnectingToInternet();
                if (isInternetPresent) {
                    dialogAddTaxType(null);
                }
                if (!isInternetPresent) {
                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                }
                break;


        }
        return super.onOptionsItemSelected(item);
    }
}
