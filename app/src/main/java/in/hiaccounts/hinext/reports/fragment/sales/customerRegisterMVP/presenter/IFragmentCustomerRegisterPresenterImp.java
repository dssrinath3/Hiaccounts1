package in.hiaccounts.hinext.reports.fragment.sales.customerRegisterMVP.presenter;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;


import in.hiaccounts.hinext.reports.fragment.sales.customerRegisterMVP.interactor.IFragmentCustomerRegisterInteractor;
import in.hiaccounts.hinext.reports.fragment.sales.customerRegisterMVP.interactor.IFragmentCustomerRegisterInteractorImp;
import in.hiaccounts.hinext.reports.fragment.sales.customerRegisterMVP.network.CustomerRegisterList;
import in.hiaccounts.hinext.reports.fragment.sales.customerRegisterMVP.view.IFragmentCustomerRegisterView;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import io.reactivex.disposables.CompositeDisposable;

public class IFragmentCustomerRegisterPresenterImp implements IFragmentCustomerRegisterPresenter,IFragmentCustomerRegisterInteractor.onCustomerFinishedListner {

    private IFragmentCustomerRegisterView mView;
    private Activity activity;
    private String fromDate="",toDate="";
    private CompositeDisposable mCompositeDisposable;
    private IFragmentCustomerRegisterInteractor mInteractor;

    public IFragmentCustomerRegisterPresenterImp(IFragmentCustomerRegisterView mView, Activity activity, ServiceHandler serviceHandler, SharedPreference sharedPreference, String serverUrl, boolean isInternetPresent) {
        this.mView = mView;
        this.activity = activity;
        mCompositeDisposable = new CompositeDisposable();
        mInteractor = new IFragmentCustomerRegisterInteractorImp(activity,serviceHandler,sharedPreference,serverUrl,isInternetPresent,mCompositeDisposable);
    }

    @Override
    public void datePicker(Activity activity, EditText edFromdate) {
        if (mView!=null){
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            final int day = calendar.get(Calendar.DAY_OF_MONTH);

            final TimeZone utc = TimeZone.getTimeZone("UTC");
            final SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            f.setTimeZone(utc);
            DatePickerDialog datePickerDialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    edFromdate.setText(String.valueOf(new StringBuilder().append(year).append("-").append(month + 1).append("-").append(dayOfMonth)));
                    calendar.set(year, month, dayOfMonth);
                    fromDate = f.format(calendar.getTime());
                    mView.setCustomerDate(fromDate.toString().trim());
                }
            }, year, month, day);
            datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
            datePickerDialog.show();
        }
    }

    @Override
    public void selectCustomerData() {
        if (mView!=null){
            mView.customerData();
        }
    }

    @Override
    public void onDestroy() {
        if (mView!=null){
            mView =null;
            // DO NOT CALL .dispose()
            mCompositeDisposable.clear();
        }
    }

    @Override
    public void getCustomerRegisterList(String search, boolean isFirst, boolean isPrev, String pageNumber, boolean isNext, boolean isLast) {
        if (this.mView!=null){
            mView.showProgressBar();
            mInteractor.callCustomerRegisterData(this);
        }
    }

    @Override
    public void getOnpageLoadData() {
        if (this.mView!=null){
            mView.showProgressBar();
            mInteractor.callOnLoadPageData(this);

        }
    }

    @Override
    public void onFailure() {
        if (mView!=null){
            mView.hideProgressBar();
        }
    }

    @Override
    public void onSuccess() {
        if (mView!=null){
            mView.hideProgressBar();
        }
    }

    @Override
    public void onFinished(CustomerRegisterList customerRegisterList) {
        if (mView!=null){
            mView.hideProgressBar();
            mView.setCustomerRegisterAdapterView(customerRegisterList);
        }
    }
}
