package in.hiaccounts.hinext.company_setup.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.hiaccounts.R;
import in.hiaccounts.hinext.company_setup.activity.Activity_CompanySubcription;
import in.hiaccounts.hinext.company_setup.adapter.AccountList_Adapter;
import in.hiaccounts.hinext.company_setup.model.BalanceAccountDatum;
import in.hiaccounts.hinext.company_setup.model.BuisnessType;
import in.hiaccounts.hinext.company_setup.model.CompanyPageInfo;
import in.hiaccounts.hinext.company_setup.model.IndustryClassificationList;
import in.hiaccounts.hinext.controlpanel.fragment.companysetup.UploadImageInterface;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static in.hiaccounts.R.string.response_error;

/**
 * Created by Prateek on 6/20/2017.
 */

public class Fragment_CompanyChartsAccounts extends Fragment {

    public static final String TAG = Fragment_CompanyChartsAccounts.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.radioGrp)
    RadioGroup radioGrp;
    @BindView(R.id.spinnerclassification)
    Spinner spinnerclassification;
    @BindView(R.id.spinnertypeofBuisness)
    Spinner spinnertypeofBuisness;
    @BindView(R.id.lvBalaceSheet)
    ListView lvBalaceSheet;
    @BindView(R.id.lvProfitLoss)
    ListView lvProfitLoss;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.tvBack)
    TextView tvBack;
    @BindView(R.id.radioBtnSelectList)
    RadioButton radioBtnSelectList;
    @BindView(R.id.radioBtnCreateList)
    RadioButton radioBtnCreateList;
    @BindView(R.id.tvNext)
    TextView tvNext;
    @BindView(R.id.llContent)
    LinearLayout llContent;

    Unbinder unbinder;
    private ServiceHandler serviceHandler;
    private SharedPreference sharedPreference;
    private Boolean isInternetPresent = false;
    Activity mActivity;
    Gson gson;
    CompanyPageInfo companyPageInfo = null;
    String serverUrl = "";
    List<Object> objectListIndustryClassification = null;
    List<Object> businessTypeList = null;
    List<BalanceAccountDatum> balanceAccountList = null;
    List<BalanceAccountDatum> profitLossAccountList = null;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    // call when instace of Fra_GeneralInfo is created.
    public static Fragment_CompanyChartsAccounts newInstance() {
        Fragment_CompanyChartsAccounts fragment = new Fragment_CompanyChartsAccounts();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.compnaysubcription_chartsaccounts, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponentsView();
        return view;

    }

    private void initComponentsView() {

        serviceHandler = new ServiceHandler(mActivity);
        sharedPreference = SharedPreference.getInstance(mActivity);

        serverUrl = UtilView.getUrl(mActivity);
        toolbar.setTitle(getString(R.string.comapnyChartsofAccount));
        toolbar.setTitleTextColor(Color.WHITE);
        gson = new Gson();
        objectListIndustryClassification = new ArrayList<>();
        objectListIndustryClassification.add("Select Industry");

        String companyInfoData = sharedPreference.getData(Constant.KEY_COMPANYINFORMATION);
        if (companyInfoData != null) {
            companyPageInfo = gson.fromJson(companyInfoData, CompanyPageInfo.class);
            if (companyPageInfo != null) {
                if (companyPageInfo.getIndustryClassificationsList() != null) {
                    objectListIndustryClassification.addAll(companyPageInfo.getIndustryClassificationsList());
                }
            }
        }

        UtilView.setupStateAdapter(mActivity, spinnerclassification, objectListIndustryClassification);


        int radioGrpId = radioGrp.getCheckedRadioButtonId();

        if (radioGrpId == R.id.radioBtnSelectList) {
            radioBtnSelectList.setChecked(true);
            radioBtnCreateList.setChecked(false);
            llContent.setVisibility(View.VISIBLE);
        }
        if (radioGrpId == R.id.radioBtnCreateList) {
            radioBtnSelectList.setChecked(false);
            radioBtnCreateList.setChecked(true);
            llContent.setVisibility(View.GONE);
        }

        radioGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId == R.id.radioBtnSelectList) {
                    radioBtnSelectList.setChecked(true);
                    radioBtnCreateList.setChecked(false);
                    llContent.setVisibility(View.VISIBLE);
                }
                if (checkedId == R.id.radioBtnCreateList) {
                    radioBtnSelectList.setChecked(false);
                    radioBtnCreateList.setChecked(true);
                    llContent.setVisibility(View.GONE);
                }


            }
        });

        spinnerclassification.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Object object = objectListIndustryClassification.get(position);

                if (object instanceof IndustryClassificationList) {
                    IndustryClassificationList classificationList = (IndustryClassificationList) object;
                    if (businessTypeList != null) {
                        businessTypeList.clear();
                    }

                    getTypeOfBusiness(classificationList);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnertypeofBuisness.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Object object = businessTypeList.get(position);

                if (object instanceof BuisnessType) {
                    getBalanceSheetAccountList();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void getBalanceSheetAccountList() {

        Object object = spinnerclassification.getSelectedItem();
        final IndustryClassificationList classificationList = (IndustryClassificationList) object;
        Object object1 = spinnertypeofBuisness.getSelectedItem();
        final BuisnessType buisnessType = (BuisnessType) object1;

        if (classificationList != null && classificationList.getIndustryClassificationId() != null
                && buisnessType != null && buisnessType.getBusinessTypeId() != null) {
            String url = serverUrl + Constant.FUNTION_GETCOMPANYBALANCESHEETACCOUNT + "?industryId=" + classificationList.getIndustryClassificationId() + "&BusinessTypeId=" + buisnessType.getBusinessTypeId();
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (serverUrl != null) {
                if (isInternetPresent) {
                    UtilView.showProgessBar(mActivity, progressBar);
                    balanceAccountList = new ArrayList<>();
                    GetDataTask getDataTask = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                        @Override
                        public void onTaskComplete(String result) {
                            UtilView.hideProgessBar(mActivity, progressBar);
                            if (result != null) {
                                if (result.toString().equals(getResources().getString(R.string.response200_sessionLost))) {
                                    UtilView.gotToLogin(mActivity);
                                } else {

                                    getProfitLossAccountList(classificationList, buisnessType);


                                    try {
                                        JSONArray jsonArray = new JSONArray(result.toString());
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                                            BalanceAccountDatum account = gson.fromJson(jsonObject.toString(), BalanceAccountDatum.class);
                                            balanceAccountList.add(account);
                                        }
                                    } catch (Exception e) {
                                        UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                                    }


                                }
                            } else {
                                UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                            }
                        }
                    }, false);
                    getDataTask.execute(url, "");

                } else {
                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
                }
            } else {
                UtilView.gotToLogin(mActivity);
            }
        }

    }

    private void getProfitLossAccountList(final IndustryClassificationList classificationList, final BuisnessType buisnessType) {
        String url = serverUrl + Constant.FUNTION_GETCOMPANYPROFITLOSSACCOUNT + "?industryId=" + classificationList.getIndustryClassificationId() + "&BusinessTypeId=" + buisnessType.getBusinessTypeId();
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
                UtilView.showProgessBar(mActivity, progressBar);
                profitLossAccountList = new ArrayList<>();
                GetDataTask getDataTask = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(mActivity, progressBar);
                        if (result != null) {
                            if (result.toString().equals(getResources().getString(R.string.response200_sessionLost))) {
                                UtilView.gotToLogin(mActivity);
                            } else {
                                try {
                                    JSONArray jsonArray = new JSONArray(result.toString());
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                                        BalanceAccountDatum account = gson.fromJson(jsonObject.toString(), BalanceAccountDatum.class);
                                        profitLossAccountList.add(account);
                                    }
                                    setupAccountListAdapter();
                                } catch (Exception e) {
                                    UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                                }


                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                        }
                    }
                }, false);
                getDataTask.execute(url, "");

            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
            }
        } else {
            UtilView.gotToLogin(mActivity);
        }

    }

    private void setupAccountListAdapter() {
        if (lvProfitLoss != null) {
            int count = lvProfitLoss.getHeaderViewsCount();
            AccountList_Adapter adapter = new AccountList_Adapter(mActivity, profitLossAccountList);
            lvProfitLoss.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            lvProfitLoss.setVisibility(View.VISIBLE);
        }
        if (lvBalaceSheet != null) {
            AccountList_Adapter adapter = new AccountList_Adapter(mActivity, balanceAccountList);
            lvBalaceSheet.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            lvBalaceSheet.setVisibility(View.VISIBLE);
        }
    }

    private void getTypeOfBusiness(IndustryClassificationList classificationList) {


        String url = serverUrl + Constant.FUNTION_GETBUSINESSTYPES + classificationList.getIndustryClassificationId();
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternetPresent) {
                UtilView.showProgessBar(mActivity, progressBar);
                GetDataTask getDataTask = new GetDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(mActivity, progressBar);
                        if (result != null) {
                            if (result.toString().equals(getResources().getString(R.string.response200_sessionLost))) {
                                UtilView.gotToLogin(mActivity);
                            } else {
                                try {
                                    businessTypeList = new ArrayList<>();
                                    businessTypeList.add("Select Business Type");

                                    JSONArray jsonArray = new JSONArray(result.toString());
                                    Gson gson = new Gson();
                                    if (jsonArray != null) {
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                                            BuisnessType buisnessType = gson.fromJson(jsonObject.toString(), BuisnessType.class);
                                            businessTypeList.add(buisnessType);
                                        }
                                    }
                                    UtilView.setupStateAdapter(mActivity, spinnertypeofBuisness, businessTypeList);


                                } catch (Exception e) {
                                    UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                                }


                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(response_error), mActivity);
                        }
                    }
                }, false);
                getDataTask.execute(url, "");

            } else {
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

    @OnClick({R.id.tvBack, R.id.tvNext})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvBack:
                ((Activity_CompanySubcription) getActivity()).showCompanyFinancialYear();

                break;
            case R.id.tvNext:

                String companyInformationString = sharedPreference.getData(Constant.KEY_COMPANYINFORMATION);
                Gson gson = new Gson();
                CompanyPageInfo companyPageInfo = gson.fromJson(companyInformationString, CompanyPageInfo.class);

                String createOrSelect = "";

                int radioGrpId = radioGrp.getCheckedRadioButtonId();

                if (radioGrpId == R.id.radioBtnSelectList) {
                    createOrSelect = "Create";
                }
                if (radioGrpId == R.id.radioBtnCreateList) {

                }
                IndustryClassificationList classificationList = null;
                BuisnessType buisnessType = null;


                Object classificationObject = spinnerclassification.getSelectedItem();
                if (classificationObject instanceof IndustryClassificationList) {
                    classificationList = (IndustryClassificationList) classificationObject;
                }
                Object businessTypeObject = spinnertypeofBuisness.getSelectedItem();
                if (businessTypeObject instanceof BuisnessType) {
                    buisnessType = (BuisnessType) businessTypeObject;
                }

                if (classificationList != null && buisnessType != null) {

                    companyPageInfo.setCreateOrSelectAM(createOrSelect);
                    companyPageInfo.setIndustryClassificationId(classificationList.getIndustryClassificationId());
                    companyPageInfo.setBusinessTypeId(buisnessType.getBusinessTypeId());
                    sharedPreference.saveData(Constant.KEY_COMPANYINFORMATION, gson.toJson(companyPageInfo).toString());


                    RequestBody mFile = null;
                    MultipartBody.Part fileToUpload = null;
                    File file = companyPageInfo.getFile();
                    if (file != null) {
                        //RequestBody mFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                        mFile = RequestBody.create(MediaType.parse("image/*"), file);
                        fileToUpload = MultipartBody.Part.createFormData("myFile", file.getName(), mFile);
                    } else {
                        //mFile = RequestBody.create(MediaType.parse("image/*"), new File(""));
                        mFile = RequestBody.create(MediaType.parse("application/octet-stream"), "");
                        fileToUpload = MultipartBody.Part.createFormData("myFile", "", mFile);
                    }
                    String type = "text/plain";
                    CompanyPageInfo companyPageInfoSetup = new CompanyPageInfo();
                    companyPageInfoSetup.setCompanyName(companyPageInfo.getCompanyName());
                    companyPageInfoSetup.setCompany_no(companyPageInfo.getCompany_no());
                    companyPageInfoSetup.setIncdate(companyPageInfo.getIncdate());
                    companyPageInfo.setRegisteredCompany(companyPageInfo.isRegisteredCompany());
                    companyPageInfo.setRegisterNo(companyPageInfo.getRegisterNo());
                    companyPageInfo.setGstRegisteredDate(companyPageInfo.getGstRegisteredDate());
                    companyPageInfoSetup.setAddress(companyPageInfo.getAddress());
                    companyPageInfoSetup.setPanNumber(companyPageInfo.getPanNumber());
                    companyPageInfoSetup.setPhone(companyPageInfo.getPhone());
                    companyPageInfoSetup.setFax(companyPageInfo.getFax());
                    companyPageInfoSetup.setLang(companyPageInfo.getLang());
                    companyPageInfoSetup.setEmail(companyPageInfo.getEmail());
                    companyPageInfoSetup.setWeb(companyPageInfo.getWeb());
                    companyPageInfoSetup.setCountryId(companyPageInfo.getCountryId());
                    companyPageInfoSetup.setStateId(companyPageInfo.getStateId());
                    companyPageInfoSetup.setCurrencyId(companyPageInfo.getCurrencyId());
                    companyPageInfoSetup.setYearclosing(companyPageInfo.getYearclosing());
                    companyPageInfoSetup.setClosingMethod(companyPageInfo.getClosingMethod());
                    companyPageInfoSetup.setStartperiod(companyPageInfo.getStartperiod());
                    companyPageInfoSetup.setClosingperiod(companyPageInfo.getClosingperiod());
                    companyPageInfoSetup.setStartyear(companyPageInfo.getStartyear());
                    companyPageInfoSetup.setEndyear(companyPageInfo.getEndyear());
                    companyPageInfoSetup.setBufferDays(companyPageInfo.getBufferDays());
                    companyPageInfoSetup.setBufferDate(companyPageInfo.getBufferDate());
                    companyPageInfoSetup.setGstreturnalertdue(companyPageInfo.getGstreturnalertdue());
                    companyPageInfoSetup.setCreateOrSelectAM(createOrSelect);
                    companyPageInfoSetup.setIndustryClassificationId(classificationList.getIndustryClassificationId());
                    companyPageInfoSetup.setBusinessTypeId(buisnessType.getBusinessTypeId());


                    UtilView.showLogCat(TAG, "SaveData " + gson.toJson(companyPageInfoSetup));
                    RequestBody company_name = RequestBody.create(MediaType.parse(type), companyPageInfo.getCompanyName());


                    RequestBody company_no = RequestBody.create(MediaType.parse(type), companyPageInfo.getCompany_no());
                    RequestBody incdate = RequestBody.create(MediaType.parse(type), companyPageInfo.getIncdate());
                    RequestBody registeredCompany = RequestBody.create(MediaType.parse(type), "" + companyPageInfo.isRegisteredCompany());
                    RequestBody registerNo = RequestBody.create(MediaType.parse(type), companyPageInfo.getRegisterNo());
                    RequestBody gstregisteredDate = RequestBody.create(MediaType.parse(type), companyPageInfo.getGstRegisteredDate());
                    RequestBody address = RequestBody.create(MediaType.parse(type), companyPageInfo.getAddress());
                    RequestBody panNumber = RequestBody.create(MediaType.parse(type), companyPageInfo.getPanNumber());
                    RequestBody phone = RequestBody.create(MediaType.parse(type), companyPageInfo.getPhone());
                    RequestBody fax = RequestBody.create(MediaType.parse(type), companyPageInfo.getFax());
                    RequestBody lang = RequestBody.create(MediaType.parse(type), companyPageInfo.getLang());
                    RequestBody email = RequestBody.create(MediaType.parse(type), companyPageInfo.getEmail());
                    RequestBody web = RequestBody.create(MediaType.parse(type), companyPageInfo.getWeb());
                    RequestBody countryId = RequestBody.create(MediaType.parse(type), "" + companyPageInfo.getCountryId());
                    RequestBody stateId = RequestBody.create(MediaType.parse(type), "" + companyPageInfo.getStateId());
                    RequestBody currencyId = RequestBody.create(MediaType.parse(type), "" + companyPageInfo.getCurrencyId());
                    RequestBody closingYear = RequestBody.create(MediaType.parse(type), companyPageInfo.getYearclosing());
                    RequestBody closingMethod = RequestBody.create(MediaType.parse(type), companyPageInfo.getClosingMethod());
                    RequestBody startperiod = RequestBody.create(MediaType.parse(type), companyPageInfo.getStartperiod());
                    RequestBody closingperiod = RequestBody.create(MediaType.parse(type), companyPageInfo.getClosingperiod());
                    RequestBody bufferDays = RequestBody.create(MediaType.parse(type), companyPageInfo.getBufferDays());
                    RequestBody startyear = RequestBody.create(MediaType.parse(type), companyPageInfo.getStartyear());
                    RequestBody endyear = RequestBody.create(MediaType.parse(type), companyPageInfo.getEndyear());
                    RequestBody bufferDate = RequestBody.create(MediaType.parse(type), companyPageInfo.getBufferDate());
                    RequestBody gstreturnalertdue = RequestBody.create(MediaType.parse(type), "" + companyPageInfo.getGstreturnalertdue());
                    RequestBody createOrSelectAM = RequestBody.create(MediaType.parse(type), companyPageInfo.getCreateOrSelectAM());
                    RequestBody industryClassificationId = RequestBody.create(MediaType.parse(type), "" + companyPageInfo.getIndustryClassificationId());
                    RequestBody businessTypeId = RequestBody.create(MediaType.parse(type), "" + companyPageInfo.getBusinessTypeId());
                    RequestBody tempId = RequestBody.create(MediaType.parse(type), "");

                    final String accessToken = sharedPreference.getData(Constant.ACCESSTOKEN);
                    if (accessToken != null && !accessToken.equals("invalid")) {
                        if (progressBar != null) {
                            progressBar.setVisibility(View.VISIBLE);
                        }


                        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                                .connectTimeout(5, TimeUnit.MINUTES)
                                .readTimeout(5, TimeUnit.MINUTES)
                                .writeTimeout(5, TimeUnit.MINUTES);
                        httpClient.addInterceptor(new Interceptor() {
                            @Override
                            public okhttp3.Response intercept(Chain chain) throws IOException {
                                Request original = chain.request();

                                Request request = original.newBuilder()
                                        .header("Content-Type", "multipart/form-data")
                                        .header("Accept", "application/json")
                                        .header("Cookie", "accessToken=" + accessToken)
                                        .method(original.method(), original.body())
                                        .build();

                                return chain.proceed(request);
                            }
                        });

                        UtilView.showLogCat(TAG, "Server Url for okHttp " + serverUrl);
                        OkHttpClient client = httpClient.build();
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(serverUrl)
                                .addConverterFactory(GsonConverterFactory.create())
                                .client(client)
                                .build();
                        UploadImageInterface uploadImage = retrofit.create(UploadImageInterface.class);

                        Call<CompanyPageInfo> serverResponse = uploadImage.uploadCompnaySetup(fileToUpload, company_name, company_no, incdate, registeredCompany,
                                registerNo, gstregisteredDate, address, panNumber, phone, fax, lang, email, web, countryId, stateId, currencyId,
                                closingYear, closingMethod, startperiod, closingperiod, bufferDays, startyear, endyear, bufferDate, gstreturnalertdue,
                                createOrSelectAM, industryClassificationId, businessTypeId, tempId);


                        serverResponse.enqueue(new Callback<CompanyPageInfo>() {
                            @Override
                            public void onResponse(Call<CompanyPageInfo> call, Response<CompanyPageInfo> response) {
                                if (progressBar != null) {
                                    progressBar.setVisibility(View.GONE);
                                }
                                if (response.code() == 200) {
                                    sharedPreference.saveData(Constant.NAVIGATION_REDIRECTPAGE,Constant.PAGE_CONFIG);
                                    ((Activity_CompanySubcription) getActivity()).showCompanyConfiguration();
                                } else {
                                    UtilView.showToast(mActivity, "Please try again.");
                                }
                            }

                            @Override
                            public void onFailure(Call call, Throwable t) {
                                if (progressBar != null) {
                                    progressBar.setVisibility(View.GONE);
                                }
                                UtilView.showLogCat(TAG, "onFailure Error ==> " + t.getMessage());
                            }
                        });
                    } else {
                        UtilView.gotToLogin(mActivity);
                    }


                } else {

                    UtilView.showToast(mActivity, "Please select Industry and Type of Buisness.");
                }


                break;


        }


    }
}
