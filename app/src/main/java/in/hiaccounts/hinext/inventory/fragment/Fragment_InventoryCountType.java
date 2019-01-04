package in.hiaccounts.hinext.inventory.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
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
import cn.pedant.SweetAlert.SweetAlertDialog;
import in.hiaccounts.R;
import in.hiaccounts.hinext.inventory.adapter.CountTypeAdapter;
import in.hiaccounts.hinext.inventory.model.counttype.InventoryCountType;
import in.hiaccounts.hinext.inventory.model.counttype.InventoryCountTypeData;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 2/9/2017.
 */

public class Fragment_InventoryCountType extends Fragment implements TextWatcher {
    public static final String TAG = Fragment_InventoryCountType.class.getSimpleName();
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.lvCountType)
    ListView lvCountType;
    @BindView(R.id.edSearchCountType)
    EditText edSearchCountType;
    @BindView(R.id.llSearchCountType)
    LinearLayout llSearchCountType;
    @BindView(R.id.ll)
    LinearLayout ll;
    Unbinder unbinder;
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
    private Activity activity;
    private Boolean isInternetPresent = false;
    private List<Object> countTypeList = new ArrayList<Object>();
    private CountTypeAdapter countTypeAdapter;
    private String coutnTypeSearch = "", serverUrl;
    private int pageNo = 0;

    public static Fragment_InventoryCountType newInstance() {
        Fragment_InventoryCountType fragment = new Fragment_InventoryCountType();
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
        View view = inflater.inflate(R.layout.fragment_inventorymovementtype, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponents(view);
        return view;
    }

    private void initComponents(View view) {
        serviceHandler = new ServiceHandler(activity);
        serverUrl = UtilView.getUrl(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.inventory_adapter_counttype, null, false);
        TextView tvName = header.findViewById(R.id.tvName);
        TextView tvDescription = header.findViewById(R.id.tvDescription);
        TextView tvStatus = header.findViewById(R.id.tvStatus);
        tvName.setText("Count Type");
        tvDescription.setText("Description");
        tvStatus.setText("Status");
        UtilView.setTextAppearanceSmall(activity, tvName);
        UtilView.setTextAppearanceSmall(activity, tvDescription);
        UtilView.setTextAppearanceSmall(activity, tvStatus);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvName);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvDescription);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvStatus);
        if (lvCountType != null)
            lvCountType.addHeaderView(header);
        if (edSearchCountType != null)
            edSearchCountType.addTextChangedListener(this);

        getCountTypeFromServer("", true, false, "0", false, false);

        edSearchCountType.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(activity, edSearchCountType);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    coutnTypeSearch = edSearchCountType.getText().toString().trim();
                    getCountTypeFromServer(coutnTypeSearch, true, false, "0", false, false);
                }
                return handled;
            }
        });

    }

    private void getCountTypeFromServer(String search, boolean isFirst, boolean isPrev, String pageNumber, boolean isNext, boolean isLast) {
        String url = "";
        if (search.equals(""))
            url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETINVENTORY_COUNTTYPE + "?inventorySearchText=&firstPage=" + isFirst + "&lastPage=" + isLast + "&nextPage=" + isNext + "&pageNo=" + pageNumber + "&prevPage=" + isPrev;
        if (!search.equals(""))
            url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETINVENTORY_COUNTTYPE + "?inventorySearchText=" + search.replace(" ", "%20") + "&firstPage=" + isFirst + "&lastPage=" + isLast + "&nextPage=" + isNext + "&pageNo=" + pageNumber + "&prevPage=" + isPrev;
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
                            try {
                                countTypeList.clear();

                                List<InventoryCountType> list = new ArrayList<>();
                                InventoryCountTypeData data = gson.fromJson(result.toString(), InventoryCountTypeData.class);
                                if (data != null) {
                                    setUpView(data);
                                    if (lvCountType != null) {
                                        if (data.getData() != null && data.getData().size() > 0) {
                                            list = data.getData();
                                            countTypeList.addAll(list);
                                            countTypeAdapter = new CountTypeAdapter(activity, countTypeList);
                                            lvCountType.setAdapter(countTypeAdapter);
                                            countTypeAdapter.notifyDataSetChanged();
                                        } else {
                                            countTypeList.clear();
                                            countTypeAdapter = new CountTypeAdapter(activity, countTypeList);
                                            lvCountType.setAdapter(countTypeAdapter);
                                            countTypeAdapter.notifyDataSetChanged();
                                            SweetAlertDialog pDialog = new SweetAlertDialog(activity, SweetAlertDialog.SUCCESS_TYPE);
                                            pDialog.setTitleText(getResources().getString(R.string.counttype_notavailbale));
                                            pDialog.setCancelable(false);
                                            pDialog.show();
                                        }
                                    }
                                }


                            } catch (Exception e) {
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

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.toString().equals("")) {
            UtilView.hideSoftKeyboard(activity, edSearchCountType);
            coutnTypeSearch = "";
            getCountTypeFromServer(coutnTypeSearch, true, false, "0", false, false);
        }
    }

    private void setUpView(InventoryCountTypeData data) {
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

    @OnClick({R.id.tvFirst, R.id.tvPrev, R.id.tvNext, R.id.tvLast, R.id.llSearchCountType})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llSearchCountType:
                UtilView.hideSoftKeyboard(activity, edSearchCountType);
                coutnTypeSearch = edSearchCountType.getText().toString().trim();
                getCountTypeFromServer(coutnTypeSearch, true, false, "0", false, false);
                break;
            case R.id.tvFirst:
                pageNo = 0;
                getCountTypeFromServer("", true, false, "0", false, false);
                break;
            case R.id.tvPrev:
                if (pageNo > 0)
                    pageNo = pageNo - 1;
                getCountTypeFromServer("", false, true, "" + pageNo, false, false);
                break;
            case R.id.tvNext:
                pageNo = pageNo + 1;
                getCountTypeFromServer("", false, false, "" + pageNo, true, false);
                break;
            case R.id.tvLast:
                pageNo = 0;
                getCountTypeFromServer("", false, false, "0", false, true);
                break;
        }
    }
}
