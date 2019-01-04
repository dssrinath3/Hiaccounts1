package in.hiaccounts.hinext.sales.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.hiaccounts.R;
import in.hiaccounts.hinext.sales.adapter.SalesCreditNote_Adapter;
import in.hiaccounts.hinext.sales.model.sales_creditnote.CreditNoteData;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;

public class Activity_CreditNote extends AppCompatActivity implements TextWatcher {
    private static String TAG = Activity_CreditNote.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edSearch)
    EditText edSearch;
    @BindView(R.id.tvFormNo)
    TextView tvFormNo;
    @BindView(R.id.tvSupplierName)
    TextView tvSupplierName;
    @BindView(R.id.tvInvoiceDate)
    TextView tvInvoiceDate;
    @BindView(R.id.tvTotalRecieved)
    TextView tvTotalRecieved;
    @BindView(R.id.lvCrediteNoteList)
    ListView lvCrediteNoteList;
    @BindView(R.id.llListview)
    LinearLayout llListview;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;

    private Activity mActivity = this;
    private ServiceHandler serviceHandler;
    private Boolean isInternetPresent = false;
    private SharedPreference sharedPreference;
    private String serverUrl;
    List<CreditNoteData> creditNoteDataList;
    SalesCreditNote_Adapter creditNoteAdapter;
    CreditNoteData creditNoteData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creditnote);
        ButterKnife.bind(this);
        initComponentVIew();
    }

    private void initComponentVIew() {
        ButterKnife.bind(this);
        edSearch.addTextChangedListener(this);
        serverUrl = UtilView.getUrl(mActivity);
        toolbar.setTitle(getResources().getString(R.string.salesInvoiceList));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        serviceHandler = new ServiceHandler(this);
        sharedPreference = SharedPreference.getInstance(this);
        creditNoteDataList = new ArrayList<CreditNoteData>();
        getCreditNoteList();
        lvCrediteNoteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                if (view.getId() == R.id.imageviewReturn) {
                }
                if (view.getId() == R.id.tvCreateCreditNote) {
                    if (creditNoteDataList != null) {
                        Intent intent = new Intent(mActivity, Activity_AddCreditNote.class);
                        intent.putExtra("paymentInvoice", creditNoteDataList.get(position));
                        startActivity(intent);
                    } else {
                        UtilView.showToast(mActivity, getResources().getString(R.string.selectCustomerError));
                    }
                }
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    private void getCreditNoteList() {
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
                UtilView.showProgessBar(mActivity, progressBar);
                String url = serverUrl + "/retail//" + Constant.FUNTION_GETSALESDEBITNOTESEARCH +"?itemSearchText=";
                GetDataTask getPurInvoiceList = new GetDataTask(this, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(mActivity, progressBar);
                        if (result != null) {
                            try {
                                creditNoteDataList = new ArrayList<CreditNoteData>();
                                JSONArray jsonArray = new JSONArray(result.toString());
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject json = jsonArray.getJSONObject(i);
                                    Gson gson = new Gson();
                                    CreditNoteData paymentInvoice = gson.fromJson(json.toString(), CreditNoteData.class);
                                    creditNoteDataList.add(paymentInvoice);
                                }
                                if (creditNoteDataList.size() > 0) {
                                    llListview.setVisibility(View.VISIBLE);
                                    creditNoteAdapter = new SalesCreditNote_Adapter(mActivity, creditNoteDataList);
                                    lvCrediteNoteList.setAdapter(creditNoteAdapter);
                                    creditNoteAdapter.notifyDataSetChanged();
                                } else {
                                    llListview.setVisibility(View.GONE);
                                    UtilView.showErrorDialog("No Invoices", mActivity);
                                }
                            } catch (Exception e) {
                                UtilView.showErrorDialog(getResources().getString(R.string.error_null), mActivity);
                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.error_null), mActivity);
                        }
                    }
                }, false);
                getPurInvoiceList.execute(url, "");
            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), this);

            }
        } else {
            UtilView.gotToLogin(mActivity);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == Constant.RESQUSTCODE_CHECKOUT) {
            getCreditNoteList();
        }
    }
}
