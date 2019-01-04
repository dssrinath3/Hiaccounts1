package in.hiaccounts.hinext.tax.fragment.taxconfiguration;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.hiaccounts.R;
import in.hiaccounts.hinext.tax.activity.Activity_Tax;
import in.hiaccounts.hinext.tax.adapter.TaxAdapter;
import in.hiaccounts.hinext.tax.model.taxconfig.TaxData;
import in.hiaccounts.hinext.tax.model.taxconfig.TaxDatum;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 2/9/2017.
 */

public class Fragment_TaxConfigTax extends Fragment implements TextWatcher {

    private Unbinder unbinder;
    @BindView(R.id.rlContent)
    RelativeLayout rlContent;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.btnActivate)
    Button btnActivate;
    @BindView(R.id.btnDeactivate)
    Button btnDeactivate;
    @BindView(R.id.btnAdd)
    Button btnAdd;
    @BindView(R.id.llTopLayout)
    LinearLayout llTopLayout;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.fabAdd)
    FloatingActionButton fabAdd;
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
    private Activity mActivity;
    private ServiceHandler serviceHandler;
    private Boolean isInternetPresent = false;
    private List<TaxDatum> taxList = new ArrayList<>();
    private TaxAdapter taxAdapter;
    private String search = "", serverUrl;
    private int pageNo = 0;
    public static boolean[] thumbnailsselection;
    public static String TAG = Fragment_TaxConfigTax.class.getSimpleName();

    public static Fragment_TaxConfigTax newInstance() {
        Fragment_TaxConfigTax fragment = new Fragment_TaxConfigTax();
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
        View view = inflater.inflate(R.layout.fragment_taxconfigtax, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponentView(view);
        return view;
    }

    private void initComponentView(View view) {
        serviceHandler = new ServiceHandler(mActivity);
        serverUrl = UtilView.getUrl(mActivity);
        getTaxDetailsFromServer("", true, false, "0", false, false);
        edSearch.addTextChangedListener(this);
        LayoutInflater inflater = mActivity.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.tax_adapter_taxheader, null, false);
        if (listView != null)
            listView.addHeaderView(header);
        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    search = edSearch.getText().toString().trim();
                    getTaxDetailsFromServer(search, true, false, "0", false, false);
                }
                return handled;
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (view.getId() == R.id.imgviewEdit) {
                    Intent intent = new Intent(mActivity, Activity_Tax.class);
                    intent.putExtra("callingFor", Constant.CALL_EDITTAX);
                    intent.putExtra("taxDatum", taxList.get(position));
                    mActivity.startActivityForResult(intent, Constant.RESQUSTCODE_EDITTAX);
                }

            }
        });
    }


    private void getTaxDetailsFromServer(String search, boolean isFirst, boolean isPrev, String pageNumber, boolean isNext, boolean isLast) {
        String url = "";
        if (search.equals("")) {
            url = serverUrl + Constant.URL_TAXPATH + Constant.FUNCTION_GETTAX + "" + "&firstPage=" + isFirst + "&lastPage=" + isLast + "&nextPage=" + isNext + "&pageNo=" + pageNumber + "&prevPage=" + isPrev;
        }
        if (!search.equals("")) {
            url = serverUrl + Constant.URL_TAXPATH + Constant.FUNCTION_GETTAX + search.replace(" ", "%20") + "&firstPage=" + isFirst + "&lastPage=" + isLast + "&nextPage=" + isNext + "&pageNo=" + pageNumber + "&prevPage=" + isPrev;
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
                            Gson gson = new Gson();
                            taxList.clear();
                            if (rlContent != null)
                                rlContent.setVisibility(View.VISIBLE);

                            try {

                                TaxData data = gson.fromJson(result.toString(), TaxData.class);
                                if (data != null) {
                                    if (listView != null) {
                                        setUpView(data);
                                        if (data.getData() != null && data.getData().size() > 0) {
                                            taxList = data.getData();
                                            thumbnailsselection = new boolean[taxList.size()];
                                            taxAdapter = new TaxAdapter(mActivity, taxList);
                                            listView.setAdapter(taxAdapter);
                                            taxAdapter.notifyDataSetChanged();
                                        } else {
                                            taxList.clear();
                                            taxAdapter = new TaxAdapter(mActivity, taxList);
                                            listView.setAdapter(taxAdapter);
                                            taxAdapter.notifyDataSetChanged();
                                            UtilView.showSuccessDialog(getResources().getString(R.string.tax_notavailbale), mActivity);
                                        }
                                    }
                                }

                            } catch (Exception e) {
                                UtilView.showErrorDialog(getResources().getString(R.string.response_error), mActivity);
                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.response_error), mActivity);
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

    private void setUpView(TaxData data) {
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
        if (unbinder != null)
            unbinder.unbind();
    }

    @OnClick({R.id.tvFirst, R.id.tvPrev, R.id.tvNext, R.id.tvLast, R.id.llSearch, R.id.btnActivate, R.id.btnDeactivate, R.id.btnAdd})
    public void onViewClicked(View view) {

        switch (view.getId()) {

            case R.id.llSearch:
                UtilView.hideSoftKeyboard(mActivity, edSearch);
                search = edSearch.getText().toString().trim();
                getTaxDetailsFromServer(search, true, false, "0", false, false);
                break;
            case R.id.tvFirst:
                pageNo = 0;
                getTaxDetailsFromServer("", true, false, "0", false, false);
                break;
            case R.id.tvPrev:
                if (pageNo > 0) {
                    pageNo = pageNo - 1;
                }
                getTaxDetailsFromServer("", false, true, "" + pageNo, false, false);
                break;
            case R.id.tvNext:
                pageNo = pageNo + 1;
                getTaxDetailsFromServer("", false, false, "" + pageNo, true, false);
                break;
            case R.id.tvLast:
                pageNo = 0;
                getTaxDetailsFromServer("", false, false, "0", false, true);
                break;

            case R.id.btnActivate:
                callActivateApi();
                break;
            case R.id.btnDeactivate:
                callDeActivateApi();
                break;
            case R.id.btnAdd:
                Intent intent = new Intent(mActivity, Activity_Tax.class);
                intent.putExtra("callingFor", Constant.CALL_ADDTAX);
                mActivity.startActivityForResult(intent, Constant.RESQUSTCODE_ADDTAX);
                break;
        }
    }

    private void callDeActivateApi() {
        final ArrayList<String> selectedTaxDatum = new ArrayList<String>();
        boolean noSelect = false;
        if (thumbnailsselection != null && thumbnailsselection.length > 0) {
            selectedTaxDatum.clear();
            for (int i = 0; i < thumbnailsselection.length; i++) {
                if (thumbnailsselection[i] == true) {
                    noSelect = true;
                    selectedTaxDatum.add("" + taxList.get(i).getTaxId());
                }
            }
            if (!noSelect) {
                Toast.makeText(mActivity, "Please Select Tax!", Toast.LENGTH_SHORT).show();
            } else {
                isInternetPresent = serviceHandler.isConnectingToInternet();
                if (serverUrl != null) {
                    if (isInternetPresent) {
                        String url = serverUrl + Constant.URL_TAXPATH + Constant.FUNCTION_DEACTIVATETAX;
                        UtilView.showProgessBar(mActivity, progressBar);
                        PostDataTask postDataTAsk = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                            @Override
                            public void onTaskComplete(String result) {
                                UtilView.hideProgessBar(mActivity, progressBar);
                                if (result != null) {
                                    UtilView.showToast(mActivity, "DeActivate Successfully.");
                                    selectedTaxDatum.clear();
                                    getTaxDetailsFromServer("", true, false, "0", false, false);
                                } else {
                                    UtilView.showErrorDialog(getResources().getString(R.string.error_null), mActivity);
                                }
                            }
                        }, false);
                        postDataTAsk.execute(new Gson().toJson(selectedTaxDatum), url, "");
                    } else {
                        UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                    }
                } else {
                    UtilView.gotToLogin(mActivity);
                }
            }
        }
    }

    private void callActivateApi() {
        final ArrayList<String> selectedTaxDatum = new ArrayList<String>();
        boolean noSelect = false;
        if (thumbnailsselection != null && thumbnailsselection.length > 0) {
            selectedTaxDatum.clear();
            for (int i = 0; i < thumbnailsselection.length; i++) {
                if (thumbnailsselection[i] == true) {
                    noSelect = true;
                    selectedTaxDatum.add("" + taxList.get(i).getTaxId());
                }
            }
            if (!noSelect) {
                Toast.makeText(mActivity, "Please Select Tax!", Toast.LENGTH_SHORT).show();
            } else {
                isInternetPresent = serviceHandler.isConnectingToInternet();
                if (serverUrl != null) {
                    if (isInternetPresent) {
                        String url = serverUrl + Constant.URL_TAXPATH + Constant.FUNCTION_ACTIVATETAX;
                        UtilView.showProgessBar(mActivity, progressBar);
                        PostDataTask postDataTAsk = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                            @Override
                            public void onTaskComplete(String result) {
                                UtilView.hideProgessBar(mActivity, progressBar);
                                if (result != null) {
                                    UtilView.showToast(mActivity, "Activate Successfully.");
                                    selectedTaxDatum.clear();
                                    getTaxDetailsFromServer("", true, false, "0", false, false);
                                } else {
                                    UtilView.showErrorDialog(getResources().getString(R.string.error_null), mActivity);
                                }
                            }
                        }, false);
                        postDataTAsk.execute(new Gson().toJson(selectedTaxDatum), url, "");
                    } else {
                        UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                    }
                } else {
                    UtilView.gotToLogin(mActivity);
                }
            }
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && requestCode == Constant.RESQUSTCODE_ADDTAX) {
            getTaxDetailsFromServer("", true, false, "0", false, false);
        }
        if (resultCode == Activity.RESULT_OK && requestCode == Constant.RESQUSTCODE_EDITTAX) {
            getTaxDetailsFromServer("", true, false, "0", false, false);
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
            search = "";
            getTaxDetailsFromServer(search, true, false, "0", false, false);
        }
    }
}
