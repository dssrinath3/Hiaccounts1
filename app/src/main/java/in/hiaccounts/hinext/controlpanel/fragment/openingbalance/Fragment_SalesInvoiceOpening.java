package in.hiaccounts.hinext.controlpanel.fragment.openingbalance;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
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

import com.github.clans.fab.FloatingActionButton;
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
import in.hiaccounts.hinext.controlpanel.activity.Activity_OpeningBalance_Sales;
import in.hiaccounts.hinext.controlpanel.adapter.OpeningSalesInvoiceAdapter;
import in.hiaccounts.hinext.controlpanel.model.openingbalance.SalesInvoiceData;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 2/9/2017.
 */

public class Fragment_SalesInvoiceOpening extends Fragment implements TextWatcher {


    public static String TAG = Fragment_SalesInvoiceOpening.class.getSimpleName();
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.fabAdd)
    FloatingActionButton fabAdd;
    @BindView(R.id.edSearch)
    EditText edSearch;
    @BindView(R.id.llSearch)
    LinearLayout llSearch;
    Unbinder unbinder;

    private ServiceHandler serviceHandler;
    private Activity mActivity;
    private Boolean isInternetPresent = false;
    private List<Object> salesInventoryList = new ArrayList<Object>();
    private OpeningSalesInvoiceAdapter salesinvoiceAdapter;
    private String search = "", serverUrl;

    public static Fragment_SalesInvoiceOpening newInstance() {
        Fragment_SalesInvoiceOpening fragment = new Fragment_SalesInvoiceOpening();
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
        View view = inflater.inflate(R.layout.fragment_salesinoviceopening, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponents(view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.fabAdd, R.id.llSearch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fabAdd:
                Intent intent = new Intent(mActivity, Activity_OpeningBalance_Sales.class);
                intent.putExtra("callingFor", Constant.CALL_ADDINVENTORUOPENINGSALSE);
                startActivityForResult(intent, Constant.RESQUSTCODE_ADDINVENTORYOPENINGSALES);
                break;
            case R.id.llSearch:
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                search = edSearch.getText().toString().trim();
                getInvListFromServer();

                break;
        }
    }

    private void initComponents(View view) {
        serverUrl = UtilView.getUrl(mActivity);
        serviceHandler = new ServiceHandler(mActivity);
        LayoutInflater inflater = mActivity.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.oepningbalance_adapter_salesinventory, null, false);

        if (fabAdd != null)
            fabAdd.setVisibility(View.VISIBLE);

        TextView tvDate = header.findViewById(R.id.tvDate);
        TextView tvInvoiceNumber = header.findViewById(R.id.tvInvoiceNumber);
        TextView tvCustomerName = header.findViewById(R.id.tvCustomerName);
        TextView tvInvoiceAmount = header.findViewById(R.id.tvInvoiceAmount);
        TextView tvBalanceAmt = header.findViewById(R.id.tvBalanceAmt);

        tvDate.setText("Invoice Date");
        tvInvoiceNumber.setText("Invoice No");
        tvCustomerName.setText("Customer Name");
        tvInvoiceAmount.setText("Inv Amount");
        tvBalanceAmt.setText("Balance Amtount");

        UtilView.setTextAppearanceSmall(mActivity, tvDate);
        UtilView.setTextAppearanceSmall(mActivity, tvInvoiceNumber);
        UtilView.setTextAppearanceSmall(mActivity, tvCustomerName);
        UtilView.setTextAppearanceSmall(mActivity, tvInvoiceAmount);
        UtilView.setTextAppearanceSmall(mActivity, tvBalanceAmt);

        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvDate);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvInvoiceNumber);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvCustomerName);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvInvoiceAmount);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvBalanceAmt);

        UtilView.setTexttypeFace(null, Typeface.BOLD, tvDate);
        UtilView.setTexttypeFace(null, Typeface.BOLD, tvInvoiceNumber);
        UtilView.setTexttypeFace(null, Typeface.BOLD, tvCustomerName);
        UtilView.setTexttypeFace(null, Typeface.BOLD, tvInvoiceAmount);
        UtilView.setTexttypeFace(null, Typeface.BOLD, tvBalanceAmt);

        if (listview != null)
            listview.addHeaderView(header);
        edSearch.addTextChangedListener(this);

        getInvListFromServer();

        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    search = edSearch.getText().toString().trim();
                    getInvListFromServer();
                }
                return handled;
            }
        });


    }

    private void getInvListFromServer() {
        String url = serverUrl + "/hipos//1/" + Constant.FUNTION_GETOPNINGSALES;

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

                                Gson gson = new Gson();
                                salesInventoryList.clear();
                                List<SalesInvoiceData> list = new ArrayList<>();
                                try {
                                    JSONArray jsonArray = new JSONArray(result.toString());
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                                        SalesInvoiceData inventoryData = gson.fromJson(jsonObject.toString(), SalesInvoiceData.class);
                                        list.add(inventoryData);

                                    }
                                    if (list.size() > 0) {


                                        salesInventoryList.addAll(list);
                                        salesinvoiceAdapter = new OpeningSalesInvoiceAdapter(mActivity, salesInventoryList);
                                        listview.setAdapter(salesinvoiceAdapter);
                                        salesinvoiceAdapter.notifyDataSetChanged();
                                    } else {
                                        salesInventoryList.clear();
                                        salesinvoiceAdapter = new OpeningSalesInvoiceAdapter(mActivity, salesInventoryList);
                                        listview.setAdapter(salesinvoiceAdapter);
                                        salesinvoiceAdapter.notifyDataSetChanged();
                                        UtilView.showErrorDialog(getResources().getString(R.string.inventory_notavailbale), mActivity);

                                    }


                                } catch (Exception e) {
                                    UtilView.showLogCat(TAG, "TaskCompleteListener Exception " + e.toString());
                                }
                            }
                        } else {

                            UtilView.showErrorDialog(getResources().getString(R.string.error_null), mActivity);
                        }
                    }
                }, false);
                task.execute(url + search.replace(" ", "%20"), "");
                //task.execute(url, "");
            }
            if (!isInternetPresent) {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
            }
        } else {
            UtilView.gotToLogin(mActivity);
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


            UtilView.hideSoftKeyboard(mActivity, edSearch);
            progressBar.setVisibility(View.VISIBLE);
            search = "";
            getInvListFromServer();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && requestCode == Constant.RESQUSTCODE_ADDINVENTORYOPENINGSALES) {
            getInvListFromServer();
        }
    }
}
