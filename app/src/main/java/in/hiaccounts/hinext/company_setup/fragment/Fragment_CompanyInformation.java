package in.hiaccounts.hinext.company_setup.fragment;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.hiaccounts.R;
import in.hiaccounts.hinext.company_setup.activity.Activity_CompanySubcription;
import in.hiaccounts.hinext.company_setup.model.CompanyPageInfo;
import in.hiaccounts.hinext.company_setup.model.CountryList;
import in.hiaccounts.hinext.company_setup.model.CurrencyList;
import in.hiaccounts.hinext.company_setup.model.StateList;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;
import pub.devrel.easypermissions.EasyPermissions;

import static in.hiaccounts.R.string.response_error;

/**
 * Created by Prateek on 6/20/2017.
 */

public class Fragment_CompanyInformation extends Fragment implements EasyPermissions.PermissionCallbacks,TextWatcher {

    private static final int REQUEST_GALLERY_CODE = 200;
    private static final int READ_REQUEST_CODE = 300;
    private Uri uri;
    File file = null;

    public static final String TAG = Fragment_CompanyInformation.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tvBack)
    TextView tvBack;
    @BindView(R.id.tvNext)
    TextView tvNext;
    Unbinder unbinder;
    @BindView(R.id.edCompnayName)
    EditText edCompnayName;
    @BindView(R.id.edCompnayRegNo)
    EditText edCompnayRegNo;
    @BindView(R.id.edCompnayIncorDate)
    EditText edCompnayIncorDate;
    @BindView(R.id.radibtnYes)
    RadioButton radibtnYes;
    @BindView(R.id.radibtnNo)
    RadioButton radibtnNo;
    @BindView(R.id.radigroupGstRegister)
    RadioGroup radigroupGstRegister;
    @BindView(R.id.edGSTNo)
    EditText edGSTNo;
    @BindView(R.id.edGSTRegDate)
    EditText edGSTRegDate;
    @BindView(R.id.edAddress)
    EditText edAddress;
    @BindView(R.id.edPanNumber)
    EditText edPanNo;
    @BindView(R.id.edPhoneNumber)
    EditText edPhoneNumber;
    @BindView(R.id.edFaxNumber)
    EditText edFaxNumber;
    @BindView(R.id.edLanguage)
    EditText edLanguage;
    @BindView(R.id.edEmail)
    EditText edEmail;
    @BindView(R.id.edWebsite)
    EditText edWebsite;
    @BindView(R.id.spinnerCountry)
    Spinner spinnerCountry;
    @BindView(R.id.spinnerState)
    Spinner spinnerState;
    @BindView(R.id.spinnerCurrency)
    Spinner spinnerCurrency;
    @BindView(R.id.edCompanyLogo)
    EditText edCompanyLogo;
    @BindView(R.id.imgViewLogo)
    ImageView imgViewLogo;
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;

    private Activity mActivity;
    private String serverUrl = "";
    private ServiceHandler serviceHandler;
    private SharedPreference sharedPreference;
    private Boolean isInternetPresent = false;
    List<Object> stateObjectList = new ArrayList<Object>();
    /*
        List<CountryList>countryLists=new ArrayList<CountryList>();
        List<CurrencyList>currencyLists=new ArrayList<CurrencyList>();
    */
    final StateList[] selectedState = new StateList[1];
    final CountryList[] selectedCountry = new CountryList[1];
    final CurrencyList[] selectedCurrency = new CurrencyList[1];
    List<CurrencyList>currencyList = new ArrayList<>();
    List<CountryList>countryList = new ArrayList<>();
    List<StateList>stateList = new ArrayList<>();

    String filePath;
    Calendar calenderCompnayIncorp=null;
    Calendar calenderCompnayGSTDate=null;

    // call when instace of Fra_GeneralInfo is created.
    public static Fragment_CompanyInformation newInstance() {
        Fragment_CompanyInformation fragment = new Fragment_CompanyInformation();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.compnaysubcription_cominfo, container, false);
        unbinder = ButterKnife.bind(this, view);


        initComponents();

        return view;

    }

    private void initComponents() {

        toolbar.setTitle(getString(R.string.comapnyInformation));
        toolbar.setTitleTextColor(Color.WHITE);
       /* toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.finish();
            }
        });
*/
        serverUrl = UtilView.getUrl(mActivity);
        serviceHandler = new ServiceHandler(mActivity);
        sharedPreference = SharedPreference.getInstance(mActivity);

        edCompnayRegNo.addTextChangedListener(this);
        edCompnayIncorDate.addTextChangedListener(this);
        edGSTNo.addTextChangedListener(this);
        edGSTRegDate.addTextChangedListener(this);

        String compnayInfoData=sharedPreference.getData(Constant.KEY_COMPANYINFORMATION);

        if (compnayInfoData!=null){
            CompanyPageInfo companyPageInfo=new Gson().fromJson(compnayInfoData,CompanyPageInfo.class);

            if (companyPageInfo!=null){

                if (companyPageInfo.getCompanyName()!=null)
                    edCompnayName.setText(companyPageInfo.getCompanyName());

                if (companyPageInfo.getRegisterNo()!=null)
                    edCompnayRegNo.setText(companyPageInfo.getRegisterNo());

                if (companyPageInfo.getCompanyRegisterTime()!=null) {

                    calenderCompnayIncorp=companyPageInfo.getCompanyRegisterTime();
                    edCompnayIncorDate.setText(calenderCompnayIncorp.get(Calendar.DAY_OF_MONTH)+"/"
                            +(calenderCompnayIncorp.get(Calendar.MONTH)+1)+"/"
                            +calenderCompnayIncorp.get(Calendar.YEAR));

                }

                if (companyPageInfo.getRegisterNo()!=null)
                    edGSTNo.setText(companyPageInfo.getRegisterNo());

                if (companyPageInfo.getGstRegisteredDate()!=null){
                    calenderCompnayGSTDate=companyPageInfo.getGstRegisterTime();
                    edGSTRegDate.setText(calenderCompnayGSTDate.get(Calendar.DAY_OF_MONTH)+"/"
                            +(calenderCompnayGSTDate.get(Calendar.MONTH)+1)+"/"
                            +calenderCompnayGSTDate.get(Calendar.YEAR));
                    //edGSTRegDate.setText(companyPageInfo.getGstRegisteredDate());
                }


                if (companyPageInfo.getAddress()!=null)
                    edAddress.setText(companyPageInfo.getAddress());

                if (companyPageInfo.getPanNumber()!=null)
                    edPanNo.setText(companyPageInfo.getPanNumber());

                if (companyPageInfo.getPhone()!=null)
                    edPhoneNumber.setText(companyPageInfo.getPhone());

                if (companyPageInfo.getFax()!=null)
                    edFaxNumber.setText(companyPageInfo.getFax());

                if (companyPageInfo.getLang()!=null)
                    edLanguage.setText(companyPageInfo.getLang());

                if (companyPageInfo.getEmail()!=null)
                    edEmail.setText(companyPageInfo.getEmail());

                if (companyPageInfo.getWeb()!=null)
                    edWebsite.setText(companyPageInfo.getWeb());

                if (companyPageInfo.getFile()!=null)
                    edCompanyLogo.setText(companyPageInfo.getFilename());

                if (companyPageInfo.getCountriesList()!=null) {
                    for (int i = 0; i < companyPageInfo.getCountriesList().size(); i++) {
                        if (companyPageInfo.getCountryId()!=null) {
                            if (companyPageInfo.getCountriesList().get(i).getCountryId()==companyPageInfo.getCountryId()) {
                                spinnerCountry.setSelection(i+1);
                            }
                        }
                    }
                }
                if (companyPageInfo.getStateList()!=null) {
                    for (int i = 0; i < companyPageInfo.getStateList().size(); i++) {
                        if (companyPageInfo.getStateId()!=null) {
                            if (companyPageInfo.getStateList().get(i).getId().equals(companyPageInfo.getStateId())) {
                                spinnerState.setSelection(i+1);
                            }
                        }
                    }
                }
                if (companyPageInfo.getCurrenciesList()!=null) {
                    for (int i = 0; i < companyPageInfo.getCurrenciesList().size(); i++) {
                        if (companyPageInfo.getCurrencyId()!=null) {
                            if (companyPageInfo.getCurrenciesList().get(i).getCurrencyId()==companyPageInfo.getCurrencyId()) {
                                spinnerCurrency.setSelection(i);

                            }
                        }
                    }
                }

                if (companyPageInfo.getFile()!=null){
                            Picasso.with(mActivity).load(companyPageInfo.getFile())
                                    .networkPolicy(NetworkPolicy.NO_CACHE)
                                    .memoryPolicy(MemoryPolicy.NO_CACHE)
                                    .placeholder(R.drawable.ic_progress)
                                    .error(R.drawable.ic_app_icon)
                                    .into(imgViewLogo);
                }
            }


        }
        getCompanyInformationData();
    }

    private void getCompanyInformationData() {
//        http://localhost:7771/company/populate_info_page?
        String url = serverUrl + Constant.FUNTION_GETCOMPANYSETUPINFO;
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
                                    JSONObject jsonObject = new JSONObject(result.toString());
                                    Gson gson = new Gson();
                                    CompanyPageInfo pageInfo = gson.fromJson(jsonObject.toString(), CompanyPageInfo.class);
                                    sharedPreference.saveData(Constant.KEY_COMPANYINFORMATION, new Gson().toJson(pageInfo).toString());
                                    if (pageInfo != null) {
                                        setUpView(pageInfo);
                                    }


                                } catch (Exception e) {
                                    UtilView.showErrorDialog(getResources().getString(R.string.response_error), mActivity);
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

    private void setUpView(CompanyPageInfo pageInfo) {

        String companyName = pageInfo.getCompanyName();

        if (companyName != null) {
            edCompnayName.setText(companyName);
        }

        if (pageInfo.getCountriesList() != null && pageInfo.getCountriesList().size() > 0) {
            UtilView.setupCountryAdapter(mActivity, spinnerCountry, pageInfo.getCountriesList());
        }

        if (pageInfo.getCurrenciesList() != null && pageInfo.getCurrenciesList().size() > 0) {
            UtilView.setupCurrencyAdapter(mActivity, spinnerCurrency, pageInfo.getCurrenciesList());
        }

        stateList=pageInfo.getStateList();
        countryList=pageInfo.getCountriesList();
        currencyList=pageInfo.getCurrenciesList();

        stateObjectList.add("Select State");
        if (pageInfo.getStateList() != null && pageInfo.getStateList().size() > 0) {
            stateObjectList.addAll(pageInfo.getStateList());
            UtilView.setupStateAdapter(mActivity, spinnerState, stateObjectList);
        }


        spinnerState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = stateObjectList.get(i);
                if (obj instanceof StateList) {
                    spinnerState.setSelection(i);
                    selectedState[0] = (StateList) spinnerState.getSelectedItem();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerCountry.setSelection(i);
                selectedCountry[0] = (CountryList) spinnerCountry.getSelectedItem();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        spinnerCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerCurrency.setSelection(i);
                selectedCurrency[0] = (CurrencyList) spinnerCurrency.getSelectedItem();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tvBack, R.id.tvNext, R.id.edCompanyLogo, R.id.edCompnayIncorDate, R.id.edGSTRegDate})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.edCompanyLogo:
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK);
                openGalleryIntent.setType("image/*");
                startActivityForResult(openGalleryIntent, REQUEST_GALLERY_CODE);
                break;

            case R.id.edCompnayIncorDate:
                getDatePicker(mActivity);
                break;


            case R.id.edGSTRegDate:
                getDatePicker1(mActivity);
                break;

            case R.id.tvBack:
                ((Activity_CompanySubcription) getActivity()).showCompanyIntroduction();
                break;
            case R.id.tvNext:

                String companyName = edCompnayName.getText().toString();
                String companyRegNo = edCompnayRegNo.getText().toString().trim();
                String companyIncorpDate = edCompnayIncorDate.getText().toString().trim();
                String companyGstNo = edGSTNo.getText().toString().trim();
                String gstRegisterDate = edGSTRegDate.getText().toString().trim();
                String address = edAddress.getText().toString().trim();
                String panNumber = edPanNo.getText().toString().trim();
                String phoneNumber = edPhoneNumber.getText().toString().trim();
                String faxNumber = edFaxNumber.getText().toString().trim();
                String language = edLanguage.getText().toString().trim();
                String email = edEmail.getText().toString().trim();
                String webSite = edWebsite.getText().toString().trim();
                String imagePath = edCompanyLogo.getText().toString().trim();

                Object countryObject = spinnerCountry.getSelectedItem();
                Object stateObject = spinnerState.getSelectedItem();
                Object currencyObject = spinnerCurrency.getSelectedItem();

                CountryList country = null;
                StateList state = null;
                CurrencyList currency = null;

                if (countryObject instanceof CountryList)
                    country = (CountryList) countryObject;

                if (stateObject instanceof StateList)
                    state = (StateList) stateObject;

                if (currencyObject instanceof CurrencyList)
                    currency = (CurrencyList) currencyObject;


                int radioGrpId = radigroupGstRegister.getCheckedRadioButtonId();
                boolean isGstRegistered = true;
                if (radioGrpId == R.id.radibtnYes) {
                    isGstRegistered = true;
                }
                if (radioGrpId == R.id.radibtnNo) {
                    isGstRegistered = false;
                }

                boolean isDataValidate = false;



                if (companyIncorpDate!=null && !companyIncorpDate.equals("")&&companyRegNo != null && !companyRegNo.equals("") && state != null) {
                    if (isGstRegistered) {
                        if (companyGstNo != null && !companyGstNo.equals("") && companyGstNo.length() == 15 && gstRegisterDate != null && !gstRegisterDate.equals("")) {
                            isDataValidate = true;

                        } else {
                            isDataValidate = false;
                            if (companyGstNo != null || !companyGstNo.equals("")) {
                                edGSTNo.setError("Please enter GST Number");
                            }
                            if (companyGstNo.length() < 15) {
                                edGSTNo.setError("Enter only 15 digit number. ");
                            }
                            if (gstRegisterDate == null || gstRegisterDate.equals("")) {
                                isDataValidate = false;
                                edGSTRegDate.setError("Please select GST Registered Date.");
                            }
                        }
                    } else {
                        isDataValidate = true;
                    }

                } else {
                    isDataValidate = false;
                    if (companyIncorpDate==null || companyIncorpDate.equals("")){
                        edCompnayIncorDate.setError("Please Select State.");
                    }
                    if (companyRegNo == null || companyRegNo.equals("")) {
                        edCompnayRegNo.setError(getString(R.string.err_msg));
                    }
                    if (state == null) {
                        TextView tv = (TextView) spinnerState.getSelectedView();
                        tv.setError("Please Select State.");
                    }
                    if (isGstRegistered) {
                        if (companyGstNo == null || !companyGstNo.equals("")) {
                            edGSTNo.setError("Please enter GST Number");
                        }
                        if (companyGstNo.length() < 15) {
                            edGSTNo.setError("Enter only 15 digit number. ");
                        }
                        isDataValidate = false;
                        if (gstRegisterDate == null || gstRegisterDate.equals("")) {
                            edGSTRegDate.setError("Please select GST Registered Date.");
                        }
                    }

                }


                if (isDataValidate) {

                    if (calenderCompnayGSTDate != null && calenderCompnayIncorp != null) {

                        long gstRegisterTime = calenderCompnayGSTDate.getTimeInMillis();
                        long companyRegisterTime = calenderCompnayIncorp.getTimeInMillis();

                        if (companyRegisterTime < gstRegisterTime) {
                            //  CompanyPageInfo companySetUpData=new CompanyPageInfo();
                            String compnayInformation = sharedPreference.getData(Constant.KEY_COMPANYINFORMATION);
                            Gson gson = new Gson();
                            CompanyPageInfo companySetUpData = gson.fromJson(compnayInformation, CompanyPageInfo.class);

                            companySetUpData.setGstRegisterTime(calenderCompnayGSTDate);
                            companySetUpData.setCompanyRegisterTime(calenderCompnayIncorp);
                            if (country != null)
                                companySetUpData.setCountryId(country.getCountryId());

                            if (state != null)
                                companySetUpData.setStateId(state.getId());

                            if (currency != null)
                                companySetUpData.setCurrencyId(currency.getCurrencyId());

                            companySetUpData.setCountriesList(countryList);
                            companySetUpData.setStateList(stateList);
                            companySetUpData.setCurrenciesList(currencyList);
                            companySetUpData.setFilename(filePath);
                            companySetUpData.setFile(file);
                            companySetUpData.setCompanyName(companyName);
                            companySetUpData.setCompany_no(companyRegNo);
                            companySetUpData.setIncdate(companyIncorpDate);
                            companySetUpData.setRegisterNo(companyGstNo);
                            companySetUpData.setAddress(address);
                            companySetUpData.setPhone(phoneNumber);
                            companySetUpData.setFax(faxNumber);
                            companySetUpData.setLang(language);
                            companySetUpData.setEmail(email);
                            companySetUpData.setWeb(webSite);
                            companySetUpData.setFilename(imagePath);
                            companySetUpData.setPanNumber(panNumber);
                            if (radioGrpId == R.id.radibtnYes) {
                                companySetUpData.setRegisteredCompany(true);
                                companySetUpData.setGstRegisteredDate(gstRegisterDate);
                            }
                            if (radioGrpId == R.id.radibtnNo) {
                                companySetUpData.setRegisteredCompany(false);
                            }
                            sharedPreference.saveData(Constant.KEY_COMPANYINFORMATION, new Gson().toJson(companySetUpData).toString());
                            ((Activity_CompanySubcription) getActivity()).showCompanyFinancialYear();
                        } else {
                            UtilView.showToast(mActivity, "GST register date should be after company incorp date");
                        }
                    } else {
                        UtilView.showToast(mActivity, "Please fill the data properly.");
                    }
                } else {
                    UtilView.showToast(mActivity, "Please fill the data properly.");
                }
                break;
        }
    }


    public void getDatePicker(Activity mActivity) {
        calenderCompnayIncorp = Calendar.getInstance();
        int year = calenderCompnayIncorp.get(Calendar.YEAR);
        int month = calenderCompnayIncorp.get(Calendar.MONTH);
        final int day = calenderCompnayIncorp.get(Calendar.DAY_OF_MONTH);
        /*final TimeZone utc = TimeZone.getTimeZone("UTC");
        final SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        f.setTimeZone(utc);*/


        DatePickerDialog datePickerDialog = new DatePickerDialog(mActivity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                edCompnayIncorDate.setText(String.valueOf(new StringBuilder().append(dayOfMonth).append("/").append(month + 1).append("/").append(year)));
                calenderCompnayIncorp.set(year, month, dayOfMonth);
//                effectiveDate = f.format(calendar.getTime());
            }
        }, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();


    }


    public void getDatePicker1(Activity mActivity) {
        calenderCompnayGSTDate = Calendar.getInstance();
        int year = calenderCompnayGSTDate.get(Calendar.YEAR);
        int month = calenderCompnayGSTDate.get(Calendar.MONTH);
        final int day = calenderCompnayGSTDate.get(Calendar.DAY_OF_MONTH);
        /*final TimeZone utc = TimeZone.getTimeZone("UTC");
        final SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        f.setTimeZone(utc);*/


        DatePickerDialog datePickerDialog = new DatePickerDialog(mActivity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                edGSTRegDate.setText(String.valueOf(new StringBuilder().append(dayOfMonth).append("/").append(month + 1).append("/").append(year)));
                calenderCompnayGSTDate.set(year, month, dayOfMonth);
//                effectiveDate = f.format(calendar.getTime());
            }
        }, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_GALLERY_CODE && resultCode == Activity.RESULT_OK) {
            uri = data.getData();
            if (EasyPermissions.hasPermissions(mActivity, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                filePath = getRealPathFromURIPath(uri, mActivity);
                file = new File(filePath);
                if (file != null) {
                    edCompanyLogo.setText("" + file.getName());

                    if (file.getName() != null) {
                        Picasso.with(mActivity).load(file)
                                .networkPolicy(NetworkPolicy.NO_CACHE)
                                .memoryPolicy(MemoryPolicy.NO_CACHE)
                                .placeholder(R.drawable.ic_progress)
                                .error(R.drawable.ic_app_icon)
                                .into(imgViewLogo);
                    }
                }
            } else {
                EasyPermissions.requestPermissions(this, getString(R.string.read_file), READ_REQUEST_CODE, Manifest.permission.READ_EXTERNAL_STORAGE);
            }
        }
    }

    private String getRealPathFromURIPath(Uri contentURI, Activity activity) {
        Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if (uri != null) {
            String filePath = getRealPathFromURIPath(uri, mActivity);
            file = new File(filePath);

        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.d(TAG, "Permission has been denied");
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        edCompnayRegNo.setError(null);
        edCompnayIncorDate.setError(null);
        edGSTNo.setError(null);
        edGSTRegDate.setError(null);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
