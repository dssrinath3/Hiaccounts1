package in.hiaccounts.hinext.controlpanel.fragment.configurator;

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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.controlpanel.activity.Activity_Config_Bank;
import in.hiaccounts.hinext.controlpanel.adapter.BankAdapter;
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.bank.BankData;
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.bank.BankDatum;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 2/9/2017.
 */

public class Fragment_ConfigBank extends Fragment implements TextWatcher {


    public static String TAG = Fragment_ConfigBank.class.getSimpleName();


    Unbinder unbinder;
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
    @BindView(R.id.rlContent)
    RelativeLayout rlContent;
    private ServiceHandler serviceHandler;
    private Activity mActivity;
    private Boolean isInternetPresent = false;
    private List<Object> bankList = new ArrayList<Object>();
    private BankAdapter bankAdapter;
    private String search = "", serverUrl;
    int pageNo = 0;

    public static Fragment_ConfigBank newInstance() {
        Fragment_ConfigBank fragment = new Fragment_ConfigBank();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Returning the layout file after inflating
        View view = inflater.inflate(R.layout.fragment_configbank, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponents(view);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tvFirst, R.id.tvPrev, R.id.tvNext, R.id.tvLast, R.id.llSearch})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.llSearch:
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                search = edSearch.getText().toString().trim();
                getBankFromServer(search, true, false, "0", false, false);
                break;

            case R.id.tvFirst:
                pageNo = 0;
                getBankFromServer("", true, false, "0", false, false);
                break;
            case R.id.tvPrev:
                if (pageNo > 0)
                    pageNo = pageNo - 1;

                getBankFromServer("", false, true, "" + pageNo, false, false);
                break;
            case R.id.tvNext:
                pageNo = pageNo + 1;
                getBankFromServer("", false, false, "" + pageNo, true, false);
                break;
            case R.id.tvLast:
                pageNo = 0;
                getBankFromServer("", false, false, "0", false, true);
                break;
        }
    }

    private void initComponents(View view) {

        serverUrl = UtilView.getUrl(mActivity);
        setHasOptionsMenu(true);
        serviceHandler = new ServiceHandler(mActivity);
        LayoutInflater inflater = mActivity.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.controlpanel_adapter_bank, null, false);

        TextView tvBankName = header.findViewById(R.id.tvBankName);
        TextView tvBranch = header.findViewById(R.id.tvBranch);
        TextView tvAccountNumber = header.findViewById(R.id.tvAccountNumber);
        TextView tvEdit = header.findViewById(R.id.tvEdit);

        tvBankName.setText("Name");
        tvBranch.setText("Branch");
        tvAccountNumber.setText("Account No.");
        tvEdit.setText("Action");

        UtilView.setTextAppearanceSmall(mActivity, tvBankName);
        UtilView.setTextAppearanceSmall(mActivity, tvBranch);
        UtilView.setTextAppearanceSmall(mActivity, tvAccountNumber);
        UtilView.setTextAppearanceSmall(mActivity, tvEdit);


        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvBankName);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvBranch);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvAccountNumber);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvEdit);

        if (listview != null)
            listview.addHeaderView(header);
        edSearch.addTextChangedListener(this);

        getBankFromServer("", true, false, "0", false, false);


        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    search = edSearch.getText().toString().trim();
                    getBankFromServer(search, true, false, "0", false, false);
                }
                return handled;
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (view.getId() == R.id.imgviewEdit) {
                    if (bankList != null) {
                        Intent intent = new Intent(mActivity, Activity_Config_Bank.class);
                        intent.putExtra("callingFor", Constant.CALL_EDITBANK);
                        intent.putExtra("bankData", (BankDatum) bankList.get(position));
                        startActivityForResult(intent, Constant.RESQUSTCODE_EDITBANK);
                    }
                }
            }
        });


    }

    private void getBankFromServer(String search, boolean isFirst, boolean isPrev, String pageNumber, boolean isNext, boolean isLast) {
        // String url = Constant.NGROK_URL + "/hipos//0/" + Constant.FUNTION_GETBANKLIST + "?bankSearchText=";
        String url = "";
        if (search.equals("")) {
            url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETBANKLIST + "?bankSearchText=" + "&type=Active&firstPage=" + isFirst + "&lastPage=" + isLast + "&nextPage=" + isNext + "&pageNo=" + pageNumber + "&prevPage=" + isPrev;
        }
        if (!search.equals("")) {
            url = serverUrl + "/hipos//0/" + Constant.FUNTION_GETBANKLIST + "?bankSearchText=" + search.replace(" ", "%20") + "&type=Active&firstPage=" + isFirst + "&lastPage=" + isLast + "&nextPage=" + isNext + "&pageNo=" + pageNumber + "&prevPage=" + isPrev;
        }

        if (serverUrl != null) {
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {
                // prepare the Request
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
                                    bankList.clear();
                                    List<BankDatum> list = new ArrayList<>();
                                    BankData data = gson.fromJson(result.toString(), BankData.class);
                                    if (data != null) {

                                        if (listview != null) {
                                            setUpView(data);
                                            if (data.getData() != null && data.getData().size() > 0) {
                                                list = data.getData();
                                                bankList.addAll(list);
                                                bankAdapter = new BankAdapter(mActivity, bankList);
                                                listview.setAdapter(bankAdapter);
                                                bankAdapter.notifyDataSetChanged();

                                            } else {

                                                bankList.clear();
                                                bankAdapter = new BankAdapter(mActivity, bankList);
                                                listview.setAdapter(bankAdapter);
                                                bankAdapter.notifyDataSetChanged();
                                                UtilView.showErrorDialog(getResources().getString(R.string.bank_notavailbale), mActivity);

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
                //task.execute(url, "");
            }
            if (!isInternetPresent) {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
            }
        } else {
            UtilView.gotToLogin(mActivity);
        }
    }

    private void setUpView(BankData data) {

        if (rlContent != null)
            rlContent.setVisibility(View.VISIBLE);
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
            getBankFromServer(search, true, false, "0", false, false);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == Constant.RESQUSTCODE_ADDBANK) {
            getBankFromServer("", true, false, "0", false, false);
        }
        if (resultCode == Activity.RESULT_OK && requestCode == Constant.RESQUSTCODE_EDITBANK) {
            getBankFromServer("", true, false, "0", false, false);
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
                Intent intent = new Intent(mActivity, Activity_Config_Bank.class);
                intent.putExtra("callingFor", Constant.CALL_ADDBANK);
                startActivityForResult(intent, Constant.RESQUSTCODE_ADDBANK);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
