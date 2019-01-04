package in.hiaccounts.hinext.controlpanel.fragment.companysetup;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.controlpanel.model.company_setup.compnayinformation.CompanyData;
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
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Prateek on 2/9/2017.
 */

public class Fragment_CompanyInformation extends Fragment implements EasyPermissions.PermissionCallbacks{

    private static final int REQUEST_GALLERY_CODE = 200;
    private static final int READ_REQUEST_CODE = 300;
    private Uri uri;
    File file=null;

    public static String TAG = Fragment_CompanyInformation.class.getSimpleName();
    @BindView(R.id.edCompanyName)
    EditText edCompanyName;
    @BindView(R.id.edCompanyNo)
    EditText edCompanyNo;
    @BindView(R.id.edPanNo)
    EditText edPanNo;
    @BindView(R.id.edAddress)
    EditText edAddress;
    @BindView(R.id.edPhoneNo)
    EditText edPhoneNo;
    @BindView(R.id.edFaxNo)
    EditText edFaxNo;
    @BindView(R.id.edLangauge)
    EditText edLangauge;
    @BindView(R.id.edEmail)
    EditText edEmail;
    @BindView(R.id.edWebsite)
    EditText edWebsite;
    @BindView(R.id.edCountry)
    EditText edCountry;
    @BindView(R.id.edCurrency)
    EditText edCurrency;
    @BindView(R.id.edState)
    EditText edState;
    @BindView(R.id.edIsGSTRRegistered)
    EditText edIsGSTRRegistered;
    @BindView(R.id.edGSTNo)
    EditText edGSTNo;
    @BindView(R.id.edGSTRRegisteredDate)
    EditText edGSTRRegisteredDate;
    @BindView(R.id.edSelectFile)
    EditText edSelectFile;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;

    @BindView(R.id.imgViewCompanyLogo)
    ImageView imgViewCompanyLogo;


    Unbinder unbinder;
    @BindView(R.id.edIncDate)
    EditText edIncDate;
    private ServiceHandler serviceHandler;
    private Activity mActivity;
    private Boolean isInternetPresent = false;
    private CompanyData companyData;
    private String serverUrl;

    public static Fragment_CompanyInformation newInstance() {
        Fragment_CompanyInformation fragment = new Fragment_CompanyInformation();
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
        View view = inflater.inflate(R.layout.fragment_csetupcinfo, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponents(view);
        return view;
    }

    private void initComponents(View view) {

        serviceHandler = new ServiceHandler(mActivity);
        serverUrl = UtilView.getUrl(mActivity);
        getCompanyFromServer();
    }

    private void getCompanyFromServer() {

        String url = serverUrl + Constant.FUNTION_GETCOMPANYDATA;
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
                                    JSONObject jsonObject = new JSONObject(result.toString());
                                    Gson gson = new Gson();
                                    companyData = gson.fromJson(jsonObject.toString(), CompanyData.class);

                                    if (companyData != null)
                                        setUpView(companyData);

                                } catch (Exception e) {
                                    UtilView.showLogCat(TAG, "TaskCompleteListener Exception " + e.toString());
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




    private void setUpView(CompanyData companyData) {

        this.companyData = companyData;

        if (companyData != null) {

            if (companyData.getCompanyName() != null)
                edCompanyName.setText(companyData.getCompanyName());

            if (companyData.getCompanyNo() != null)
                edCompanyNo.setText(companyData.getCompanyNo());

            if (companyData.getPanNumber() != null)
                edPanNo.setText(companyData.getPanNumber());

            if (companyData.getAddress() != null)
                edAddress.setText(companyData.getAddress());

            if (companyData.getPhone() != null)
                edPhoneNo.setText(companyData.getPhone());

            if (companyData.getFax() != null)
                edFaxNo.setText(companyData.getFax());

            if (companyData.getLang() != null)
                edLangauge.setText(companyData.getLang());

            if (companyData.getEmail() != null)
                edEmail.setText(companyData.getEmail());

            if (companyData.getWeb() != null)
                edWebsite.setText(companyData.getWeb());

            if (companyData.getCountryId() != null) {
                if (companyData.getCountryId().getCountryName() != null)
                    edCountry.setText(companyData.getCountryId().getCountryName());
            }

            if (companyData.getCurrencyId() != null) {
                if (companyData.getCurrencyId().getCurrencyName() != null)
                    edCurrency.setText(companyData.getCurrencyId().getCurrencyName());
            }

            if (companyData.getState() != null) {
                if (companyData.getState().getStateName() != null)
                    edState.setText(companyData.getState().getStateName());
            }

            if (companyData.isRegisteredCompany()) {
                edIsGSTRRegistered.setText("Yes");
            } else {
                edIsGSTRRegistered.setText("No");
            }

            if (companyData.getIncdate() != null) {
                edIncDate.setText(companyData.getIncdate());
            }

            //edIsGSTRRegistered.setText("" + companyData.isRegisteredCompany());


            if (companyData.getRegisterNo() != null)
                edGSTNo.setText(companyData.getRegisterNo());

            if (companyData.getGstregisteredDate() != null)
                edGSTRRegisteredDate.setText(companyData.getGstregisteredDate());


            if (companyData.getFileName() != null) {
                String url= UtilView.getUrl(mActivity);

                if (url!=null) {

                    Log.e("image url",url + companyData.getFileName());
                    Picasso.with(mActivity).load(url + companyData.getFileName())
                            .networkPolicy(NetworkPolicy.NO_CACHE)
                            .memoryPolicy(MemoryPolicy.NO_CACHE)
                            .placeholder(R.drawable.ic_progress)
                            .error(R.drawable.ic_app_icon)
                            .into(imgViewCompanyLogo);
                }else {
                    UtilView.gotToLogin(mActivity);
                }
            }

        }


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.edSelectFile, R.id.btnSave})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edSelectFile:
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK);
                openGalleryIntent.setType("image/*");
                startActivityForResult(openGalleryIntent, REQUEST_GALLERY_CODE);
                break;
            case R.id.btnSave:
                if (companyData!=null){
                    if (companyData.getCompanyName()!=null){
                        updateCompnayInfo();
                    }else {
                        UtilView.showToast(mActivity,getResources().getString(R.string.response_error));
                    }
                }else {
                    UtilView.showToast(mActivity,getResources().getString(R.string.response_error));
                }

                break;
        }
    }

    private void updateCompnayInfo() {



        String compnayNumber = edCompanyNo.getText().toString().trim();
        String compnayPanNo = edPanNo.getText().toString().trim();
        String compnayAddress = edAddress.getText().toString().trim();
        String compnayPhoneNumber = edPhoneNo.getText().toString().trim();
        String compnayFaxNo = edFaxNo.getText().toString().trim();
        String compnayLanguage = edLangauge.getText().toString().trim();
        String compnayEmail = edEmail.getText().toString().trim();
        String compnayWebsite = edWebsite.getText().toString().trim();
        String compnayGSTNO = edGSTNo.getText().toString().trim();
        String compnayGstRegistredDate = edGSTRRegisteredDate.getText().toString().trim();
        String compnayIncDate = edIncDate.getText().toString().trim();
        RequestBody mFile=null;
        MultipartBody.Part fileToUpload=null;
        if (file!=null) {
            //RequestBody mFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            mFile = RequestBody.create(MediaType.parse("image/*"), file);
            fileToUpload = MultipartBody.Part.createFormData("myFile", file.getName(), mFile);
        }else {
            //mFile = RequestBody.create(MediaType.parse("image/*"), new File(""));
            mFile = RequestBody.create(MediaType.parse("application/octet-stream"), "");
            fileToUpload = MultipartBody.Part.createFormData("myFile", "",mFile);
        }
        String type = "text/plain";
        RequestBody company_name = RequestBody.create(MediaType.parse(type), companyData.getCompanyName());
        RequestBody company_no = RequestBody.create(MediaType.parse(type), compnayNumber);
        RequestBody panNo = RequestBody.create(MediaType.parse(type), compnayPanNo);
        RequestBody incdate = RequestBody.create(MediaType.parse(type), compnayIncDate);
        RequestBody address = RequestBody.create(MediaType.parse(type), compnayAddress);
        RequestBody phone = RequestBody.create(MediaType.parse(type), compnayPhoneNumber);
        RequestBody fax = RequestBody.create(MediaType.parse(type), compnayFaxNo);
        RequestBody lang = RequestBody.create(MediaType.parse(type), compnayLanguage);
        RequestBody email = RequestBody.create(MediaType.parse(type),compnayEmail);
        RequestBody web = RequestBody.create(MediaType.parse(type), compnayWebsite);
        RequestBody registerNo = RequestBody.create(MediaType.parse(type), compnayGSTNO);
        RequestBody gstregisteredDate = RequestBody.create(MediaType.parse(type), compnayGstRegistredDate);


        final SharedPreference sharedPreference= SharedPreference.getInstance(mActivity);
        final String accessToken=sharedPreference.getData(Constant.ACCESSTOKEN);

        if (accessToken!=null && !accessToken.equals("invalid")) {
            if (progressBar!=null){
                progressBar.setVisibility(View.VISIBLE);
            }


            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
                   /* .connectTimeout(5, TimeUnit.MINUTES)
                    .readTimeout(5, TimeUnit.MINUTES)
                    .writeTimeout(5, TimeUnit.MINUTES);*/
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

            Call<CompanyData> serverResponse = uploadImage.uploadFile(fileToUpload, company_name, company_no, panNo, incdate, address, phone, fax, lang, email, web, registerNo, gstregisteredDate);
            serverResponse.enqueue(new Callback<CompanyData>() {
                @Override
                public void onResponse(Call<CompanyData> call, Response<CompanyData> response) {
              /*  Toast.makeText(MainActivity.this, "Response " + response.raw().message(), Toast.LENGTH_LONG).show();
                Toast.makeText(MainActivity.this, "Success " + response.body().getSuccess(), Toast.LENGTH_LONG).show();*/
                    if (progressBar != null) {
                        progressBar.setVisibility(View.GONE);
                    }
                    if (response!=null) {
                        CompanyData responseCompanyData = response.body();

                        if (responseCompanyData != null) {
                            if (responseCompanyData.getCompanyId() != null) {
                                if (responseCompanyData.getCompanyName()!=null) {
                                    sharedPreference.saveData(Constant.COMPANYDATA,new Gson().toJson(responseCompanyData).toString());
                                    UtilView.showLogCat(TAG, "Server data" + new Gson().toJson(responseCompanyData).toString());
                                    UtilView.showToast(mActivity, "" + responseCompanyData.getCompanyName() + " Update Successfully");
                                }
                            }
                        }else
                        UtilView.showToast(mActivity, "Company not update. Please try again.");
                    }else {
                        UtilView.showToast(mActivity, "Some Error. Please try again.");
                    }
                }

                @Override
                public void onFailure(Call<CompanyData> call, Throwable t) {
                    if (progressBar != null) {
                        progressBar.setVisibility(View.GONE);
                    }
                    Log.e(TAG, "onFailure Error ==> " + t.getMessage());
                }
            });
        }else {
            UtilView.gotToLogin(mActivity);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_GALLERY_CODE && resultCode == Activity.RESULT_OK){
            uri = data.getData();
            if(EasyPermissions.hasPermissions(mActivity, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                String filePath = getRealPathFromURIPath(uri,mActivity);
                file = new File(filePath);
                if (file!=null) {

                    edSelectFile.setText("" + file.getName());

                    if (file.getName() != null) {
                            Picasso.with(mActivity).load(file)
                                    .networkPolicy(NetworkPolicy.NO_CACHE)
                                    .memoryPolicy(MemoryPolicy.NO_CACHE)
                                    .placeholder(R.drawable.ic_progress)
                                    .error(R.drawable.ic_app_icon)
                                    .into(imgViewCompanyLogo);
                    }
                }
            }else{
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
        if(uri != null){
            String filePath = getRealPathFromURIPath(uri, mActivity);
            file = new File(filePath);

        }
    }
    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.d(TAG, "Permission has been denied");
    }

}
