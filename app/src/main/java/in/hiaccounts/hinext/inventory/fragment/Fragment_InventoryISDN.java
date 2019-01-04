package in.hiaccounts.hinext.inventory.fragment;


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
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.inventory.activity.Activity_ISDN;
import in.hiaccounts.hinext.inventory.adapter.ISDNAdapter;
import in.hiaccounts.hinext.inventory.model.isdn.ISDNDataList;
import in.hiaccounts.hinext.inventory.model.isdn.ISDNDatum;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_InventoryISDN extends Fragment implements TextWatcher {
    public static String TAG = Fragment_InventoryISDN.class.getSimpleName();
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.listview)
    ListView listview;
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
    Unbinder unbinder;
    int pageNo = 0;
    private ServiceHandler serviceHandler;
    private Activity mActivity;
    private Boolean isInternetPresent = false;
    private List<Object> isdnList = new ArrayList<Object>();
    private String search = "", serverUrl;

    public static Fragment_InventoryISDN newInstance() {
        Fragment_InventoryISDN fragment = new Fragment_InventoryISDN();
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
        View view = inflater.inflate(R.layout.fragment_inventory_isdn, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponents(view);
        return view;
    }




    private void initComponents(View view) {
        serverUrl = UtilView.getUrl(mActivity);
        setHasOptionsMenu(true);
        serviceHandler = new ServiceHandler(mActivity);
        LayoutInflater inflater = mActivity.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.inventory_adapter_isdn, null, false);
        TextView tvState = header.findViewById(R.id.tvState);
        TextView tvISDNNumber = header.findViewById(R.id.tvIsdnno);
        TextView tvDate= header.findViewById(R.id.tvDate);
        TextView tvEdit = header.findViewById(R.id.tvEdit);


        tvState.setText("State");
        tvISDNNumber.setText("ISDN No");
        tvDate.setText("Date");
        tvEdit.setText("Action");

        UtilView.setTextAppearanceSmall(mActivity, tvState);
        UtilView.setTextAppearanceSmall(mActivity, tvISDNNumber);
        UtilView.setTextAppearanceSmall(mActivity, tvDate);
        UtilView.setTextAppearanceSmall(mActivity, tvEdit);

        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvState);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvISDNNumber);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvDate);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvEdit);

        if (listview != null)
            listview.addHeaderView(header);
        if (edSearch != null)
        edSearch.addTextChangedListener(this);
        getISDNFromServer("", true, false, "0", false, false);

        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    search = edSearch.getText().toString().trim();
                    getISDNFromServer(search, true, false, "0", false, false);
                }
                return handled;
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (view.getId() == R.id.imgviewEdit) {
                    if (isdnList != null) {
                        Intent intent = new Intent(mActivity, Activity_ISDN.class);
                        intent.putExtra("callingFor", Constant.CALL_EDITISDN);
                        intent.putExtra("isdnData", (ISDNDatum) isdnList.get(position));
                        mActivity.startActivityForResult(intent, Constant.RESQUSTCODE_EDITISDN);
                    }
                }
            }
        });
    }


    private void getISDNFromServer(String search, boolean isFirst, boolean isPrev, String pageNumber, boolean isNext, boolean isLast) {
        String url = "";
        if (search.equals("")) {
            url = serverUrl + "/hipos//1/" + Constant.FUNTION_GETISDNLIST + "?selfBillSearchText=&type=" + "&firstPage=" + isFirst + "&lastPage=" + isLast + "&nextPage=" + isNext + "&pageNo=" + pageNumber + "&prevPage=" + isPrev;
        }
        if (!search.equals("")) {
            url = serverUrl + "/hipos//1/" + Constant.FUNTION_GETISDNLIST + "?selfBillSearchText=" + search.replace(" ", "%20") + "&type=&firstPage=" + isFirst + "&lastPage=" + isLast + "&nextPage=" + isNext + "&pageNo=" + pageNumber + "&prevPage=" + isPrev;
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
                                    isdnList.clear();
                                    List<ISDNDatum> listData = new ArrayList<>();
                                    ISDNDataList data = gson.fromJson(result.toString(), ISDNDataList.class);
                                    if (data != null) {
                                        if (listview != null) {
                                            setUpView(data);
                                            if (data.getData() != null && data.getData().size() > 0) {
                                                listData = data.getData();
                                                isdnList.addAll(listData);
                                                ISDNAdapter isdnAdapter = new ISDNAdapter(mActivity, isdnList);
                                                listview.setAdapter(isdnAdapter);
                                                isdnAdapter.notifyDataSetChanged();
                                            } else {
                                                isdnList.clear();
                                                ISDNAdapter isdnAdapter = new ISDNAdapter(mActivity, isdnList);
                                                listview.setAdapter(isdnAdapter);
                                                isdnAdapter.notifyDataSetChanged();
                                                UtilView.showErrorDialog(getResources().getString(R.string.isdn_notavailbale), mActivity);
                                            }
                                        }

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
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }



    @OnClick({R.id.llSearch , R.id.tvFirst, R.id.tvPrev, R.id.tvNext, R.id.tvLast })
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.llSearch:
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                search = edSearch.getText().toString().trim();
                pageNo = 0;
                getISDNFromServer(search, true, false, "0", false, false);
                break;

            case R.id.tvFirst:
                pageNo = 0;
                getISDNFromServer("", true, false, "0", false, false);
                break;
            case R.id.tvPrev:
                if (pageNo > 0)
                    pageNo = pageNo - 1;

                getISDNFromServer("", false, true, "" + pageNo, false, false);
                break;
            case R.id.tvNext:
                pageNo = pageNo + 1;
                getISDNFromServer("", false, false, "" + pageNo, true, false);
                break;
            case R.id.tvLast:
                pageNo = 0;
                getISDNFromServer("", false, false, "0", false, true);
                break;
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
            search = "";
            getISDNFromServer(search, true, false, "0", false, false);
        }
    }


    private void setUpView(ISDNDataList data) {

        if (llPaginationView != null)
            llPaginationView.setVisibility(View.VISIBLE);

        if (tvFirst != null) {
            if (data.getFirst()) {
                //  tvFirst.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
                tvFirst.setTextColor(getResources().getColor(R.color.colorBlack));
                tvFirst.setClickable(false);

            }
            if (!data.getFirst()) {
                // tvFirst.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
                tvFirst.setTextColor(getResources().getColor(R.color.colorWhite));
                tvFirst.setClickable(true);
            }
        }

        if (tvNext != null) {
            if (data.getNext()) {
                // tvNext.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
                tvNext.setTextColor(getResources().getColor(R.color.colorBlack));
                tvNext.setClickable(false);

            }
            if (!data.getNext()) {
                //  tvNext.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
                tvNext.setTextColor(getResources().getColor(R.color.colorWhite));
                tvNext.setClickable(true);
            }
        }

        if (tvPrev != null) {
            if (data.getPrev()) {
                //  tvPrev.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
                tvPrev.setTextColor(getResources().getColor(R.color.colorBlack));
                tvPrev.setClickable(false);

            }
            if (!data.getPrev()) {
                //  tvPrev.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
                tvPrev.setTextColor(getResources().getColor(R.color.colorWhite));
                tvPrev.setClickable(true);
            }
        }

        if (tvLast != null) {
            if (data.getLast()) {
                //  tvLast.setBackgroundColor(getResources().getColor(R.color.paginetionButtonInvisible));
                tvLast.setTextColor(getResources().getColor(R.color.colorBlack));
                tvLast.setClickable(false);
            }
            if (!data.getLast()) {
                //   tvLast.setBackgroundColor(getResources().getColor(R.color.paginetionButtonVisible));
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
                Intent intent = new Intent(mActivity, Activity_ISDN.class);
                intent.putExtra("callingFor", Constant.CALL_ADDISDN);
                startActivityForResult(intent, Constant.RESQUSTCODE_ADDISDN);
                break;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == Constant.RESQUSTCODE_ADDISDN) {
            getISDNFromServer("", true, false, "0", false, false);
        }
        if (resultCode == Activity.RESULT_OK && requestCode == Constant.RESQUSTCODE_EDITISDN) {
            getISDNFromServer("", true, false, "0", false, false);
        }
    }
}
