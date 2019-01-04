package in.hiaccounts.hinext.reports.fragment.sales.customerRegisterMVP.interactor;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import in.hiaccounts.R;
import in.hiaccounts.hinext.application.server.RetrofitInstance;
import in.hiaccounts.hinext.reports.fragment.restaurent.interactor.DayEndRegisterInterator;
import in.hiaccounts.hinext.reports.fragment.restaurent.network.ApiClient;
import in.hiaccounts.hinext.reports.fragment.restaurent.network.ApiService;
import in.hiaccounts.hinext.reports.fragment.restaurent.network.GetDayEndRegisterService;
import in.hiaccounts.hinext.reports.fragment.sales.customerRegisterMVP.network.CustomerRegisterList;
import in.hiaccounts.hinext.reports.fragment.sales.customerRegisterMVP.network.SalesApiClient;
import in.hiaccounts.hinext.reports.fragment.sales.customerRegisterMVP.network.SalesApiService;
import in.hiaccounts.hinext.reports.model.CustomerList;
import in.hiaccounts.hinext.reports.model.restuarant.Restaurant_Day_EndData_Register;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class IFragmentCustomerRegisterInteractorImp implements IFragmentCustomerRegisterInteractor{
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
    private String accessToken="";
    private SalesApiService apiService;
    private CompositeDisposable mCompositeDisposable;
    public IFragmentCustomerRegisterInteractorImp(Activity activity, ServiceHandler serviceHandler, SharedPreference sharedPreference, String serverUrl, Boolean isInternetPresent, CompositeDisposable mCompositeDisposable) {
        this.activity = activity;
        this.serviceHandler = serviceHandler;
        this.sharedPreference = sharedPreference;
        this.serverUrl = serverUrl;
        this.isInternetPresent = isInternetPresent;
        this.mCompositeDisposable = mCompositeDisposable;
        /** Call the method with parameter in the interface to get the dayend data*/
        accessToken=sharedPreference.getData(Constant.ACCESSTOKEN);
        apiService = SalesApiClient.getClient(accessToken,this.serverUrl).create(SalesApiService.class);

    }

    @Override
    public void callOnLoadPageData(onCustomerFinishedListner onDayEndFinishedListner) {

    }

    @Override
    public void callCustomerRegisterData(onCustomerFinishedListner listner) {
        if (serverUrl != null) {
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {

                /**
                 * Making Retrofit call to fetch all data
                 */

                mCompositeDisposable.add(apiService.getCustomerRegisterService()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<CustomerRegisterList>() {
                            @Override
                            public void onSuccess(CustomerRegisterList dataRegister) {
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

            }
            if (!isInternetPresent) {
                UtilView.showErrorDialog(activity.getResources().getString(R.string.intertnet_status), activity);
            }
        } else {
            UtilView.gotToLogin(activity);
        }
    }


}
