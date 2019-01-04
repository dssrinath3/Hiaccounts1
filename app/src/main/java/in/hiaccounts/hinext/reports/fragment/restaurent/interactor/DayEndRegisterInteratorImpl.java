package in.hiaccounts.hinext.reports.fragment.restaurent.interactor;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import in.hiaccounts.R;
import in.hiaccounts.hinext.application.server.RetrofitInstance;
import in.hiaccounts.hinext.reports.fragment.restaurent.network.ApiClient;
import in.hiaccounts.hinext.reports.fragment.restaurent.network.ApiService;
import in.hiaccounts.hinext.reports.fragment.restaurent.network.GetDayEndRegisterService;
import in.hiaccounts.hinext.reports.model.CustomerList;
import in.hiaccounts.hinext.reports.model.SalesReportData;
import in.hiaccounts.hinext.reports.model.restuarant.Restaurant_Day_EndData_Register;
import in.hiaccounts.hinext.reports.model.restuarant.Restaurant_Day_EndData_Register_Data;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DayEndRegisterInteratorImpl implements DayEndRegisterInterator {

    private CompositeDisposable disposable = new CompositeDisposable();
    private List<Object> locationObjectList = new ArrayList<>();
    private List<Object> fromObjectList = new ArrayList<>();
    private List<Object> toObjectList = new ArrayList<>();
    private List<CustomerList> customerList = new ArrayList<>();
    private Activity activity;
    private ServiceHandler serviceHandler;
    private SharedPreference sharedPreference;
    private String serverUrl;
    private Boolean isInternetPresent;
    private GetDayEndRegisterService service;
    private String accessToken="";
    private ApiService apiService;
    private CompositeDisposable mCompositeDisposable;

    public DayEndRegisterInteratorImpl(Activity activity, ServiceHandler serviceHandler, SharedPreference sharedPreference, String serverUrl, Boolean isInternetPresent, CompositeDisposable mCompositeDisposable) {
        this.activity = activity;
        this.serviceHandler = serviceHandler;
        this.sharedPreference = sharedPreference;
        this.serverUrl = serverUrl;
        this.isInternetPresent = isInternetPresent;
        this.mCompositeDisposable = mCompositeDisposable;
        /** Call the method with parameter in the interface to get the dayend data*/
        accessToken=sharedPreference.getData(Constant.ACCESSTOKEN);
        /** Create handle for the RetrofitInstance interface*/
        service = RetrofitInstance.getRetrofitInstance(this.serverUrl).create(GetDayEndRegisterService.class);

    }

    @Override
    public void callOnLoadPageData( onDayEndFinishedListner listner) {
        if (serverUrl != null) {
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {
                Call<SalesReportData> call = service.getOnloadpageData("accessToken="+accessToken);
                /**Log the URL called*/
                 Log.e("Url", call.request().url() + "");
                call.enqueue(new Callback<SalesReportData>() {
                    @Override
                    public void onResponse(Call<SalesReportData> call, Response<SalesReportData> response) {
                        SalesReportData salesReportData = response.body();
                        if (salesReportData!=null){
                            Log.e("resposeString",response.body().getLocationList().toString());
                        }

                        if (salesReportData != null) {
                            if (salesReportData.getLocationList() != null) {
                                locationObjectList.clear();
                                locationObjectList.addAll(salesReportData.getLocationList());
                                listner.locationAdapterData(locationObjectList);
                            }
                            if (salesReportData.getSalesList() != null) {
                                fromObjectList.clear();
                                fromObjectList.addAll(salesReportData.getSalesList());
                                listner.fromInvoiceAdapterData(fromObjectList);
                            }
                            if (salesReportData.getSalesList() != null) {
                                toObjectList.clear();
                                toObjectList.addAll(salesReportData.getSalesList());
                                listner.toInvoiceAdapterData(fromObjectList);
                            }
                            if (salesReportData.getCustomerList() != null) {
                                customerList.addAll(salesReportData.getCustomerList());
                            }

                        }
                        listner.onSuccess();
                    }

                    @Override
                    public void onFailure(Call<SalesReportData> call, Throwable t) {
                        listner.onFailure();
                    }
                });
            }
            if (!isInternetPresent) {
                UtilView.showErrorDialog(activity.getResources().getString(R.string.intertnet_status), activity);
            }
        } else {
            UtilView.gotToLogin(activity);
        }
    }

    @Override
    public void callDayEndListData(onDayEndFinishedListner listner) {
        if (serverUrl != null) {
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {

                Call<Restaurant_Day_EndData_Register> call = service.getDayEndRegisterService("accessToken="+accessToken);
                //Log the URL called
                 Log.e("Url", call.request().url() + "");
                call.enqueue(new Callback<Restaurant_Day_EndData_Register>() {
                    @Override
                    public void onResponse(Call<Restaurant_Day_EndData_Register> call, Response<Restaurant_Day_EndData_Register> response) {
                        Restaurant_Day_EndData_Register dataRegister = response.body();
                        if (dataRegister!=null){
                            Log.e("resposeString",response.body().toString());
                            listner.onFinished(dataRegister);
                        }
                        listner.onSuccess();
                    }

                    @Override
                    public void onFailure(Call<Restaurant_Day_EndData_Register> call, Throwable t) {
                        listner.onFailure();
                    }
                });
            }
            if (!isInternetPresent) {
                UtilView.showErrorDialog(activity.getResources().getString(R.string.intertnet_status), activity);
            }
        } else {
            UtilView.gotToLogin(activity);
        }
    }

    @Override
    public void callviewItemData(Long currencyId, Long customerId, boolean filterApplied, String frmDate, Long sFromId, Long locationId, String employeeName, String toDate, Long sToId, onDayEndFinishedListner listner) {
        if (serverUrl != null) {
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {

                /**
                 * Making Retrofit call to fetch all data
                 */
                apiService = ApiClient.getClient(accessToken,this.serverUrl).create(ApiService.class);
                mCompositeDisposable.add(apiService.getViewItemDataService(currencyId,customerId,filterApplied,frmDate,sFromId,locationId,employeeName,toDate,sToId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<Restaurant_Day_EndData_Register>() {
                                    @Override
                                    public void onSuccess(Restaurant_Day_EndData_Register dataRegister) {
                                        if (dataRegister!=null){
                                            listner.onSuccess();
                                            listner.onFinished(dataRegister);
                                        }
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        listner.onFailure();
                                    }
                                }));



            /*    Call<Restaurant_Day_EndData_Register> call = service.getViewItemDataService(currencyId,filterApplied,frmDate,sFromId,locationId,employeeName,toDate,sToId,"accessToken="+accessToken);
                *//**Log the URL called*//*
                Log.e("Url", call.request().url() + "");
                call.enqueue(new Callback<Restaurant_Day_EndData_Register>() {
                    @Override
                    public void onResponse(Call<Restaurant_Day_EndData_Register> call, Response<Restaurant_Day_EndData_Register> response) {
                        Restaurant_Day_EndData_Register dataRegister = response.body();
                        if (dataRegister!=null){
                            Log.e("resposeString",response.body().toString());
                            listner.onFinished(dataRegister);
                        }
                        listner.onSuccess();
                    }

                    @Override
                    public void onFailure(Call<Restaurant_Day_EndData_Register> call, Throwable t) {
                        listner.onFailure();
                    }
                });*/
            }
            if (!isInternetPresent) {
                UtilView.showErrorDialog(activity.getResources().getString(R.string.intertnet_status), activity);
            }
        } else {
            UtilView.gotToLogin(activity);
        }
    }



}
