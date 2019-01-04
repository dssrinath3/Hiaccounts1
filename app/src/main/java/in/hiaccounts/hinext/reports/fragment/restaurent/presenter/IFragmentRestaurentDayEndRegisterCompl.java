package in.hiaccounts.hinext.reports.fragment.restaurent.presenter;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.support.annotation.NonNull;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import in.hiaccounts.hinext.reports.fragment.restaurent.interactor.DayEndRegisterInterator;
import in.hiaccounts.hinext.reports.fragment.restaurent.interactor.DayEndRegisterInteratorImpl;
import in.hiaccounts.hinext.reports.fragment.restaurent.view.IFragmentRestaurentDayEndRegisterView;
import in.hiaccounts.hinext.reports.model.restuarant.Restaurant_Day_EndData_Register;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import io.reactivex.disposables.CompositeDisposable;

public class IFragmentRestaurentDayEndRegisterCompl implements IFragmentRestaurentDayEndRegisterPresenter,DayEndRegisterInterator.onDayEndFinishedListner{

    private IFragmentRestaurentDayEndRegisterView mView;
    private Activity activity;
    private String fromDate="",toDate="";
    private DayEndRegisterInterator mInteractor;
    private CompositeDisposable mCompositeDisposable;
    public IFragmentRestaurentDayEndRegisterCompl(IFragmentRestaurentDayEndRegisterView mView, Activity activity, ServiceHandler serviceHandler, SharedPreference sharedPreference, String serverUrl, boolean isInternetPresent) {
        this.mView = mView;
        this.activity = activity;
        mCompositeDisposable = new CompositeDisposable();
        mInteractor = new DayEndRegisterInteratorImpl(activity,serviceHandler,sharedPreference,serverUrl,isInternetPresent,mCompositeDisposable);
    }


    @Override
    public void fromDatePicker(Activity activity, EditText edFromdate) {
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
                    mView.setFromDate(fromDate.toString().trim());
                }
            }, year, month, day);
            datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
            datePickerDialog.show();
        }

    }

    @Override
    public void toDatePicker(Activity activity, EditText edToDate) {

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
                    edToDate.setText(String.valueOf(new StringBuilder().append(year).append("-").append(month + 1).append("-").append(dayOfMonth)));
                    calendar.set(year, month, dayOfMonth);
                    toDate = f.format(calendar.getTime());
                    mView.setFromDate(toDate.toString().trim());
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
    public void selectEmplyee() {
        if (mView!=null){
            mView.employeeData();
        }
    }



    @Override
    public void viewItem(Long currencyId, Long customerId, boolean filterApplied, String frmDate, Long sFromId, Long locationId, String employeeName, String tDate, Long sToId) {
        if (this.mView!=null){
            mView.showProgressBar();
            mInteractor.callviewItemData( currencyId,  customerId,  filterApplied,  fromDate,  sFromId,  locationId,  employeeName,  toDate,  sToId,this);
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
    public void getdayEndList(String search, boolean isFirst, boolean isPrev, String pageNumber, boolean isNext, boolean isLast) {
        if (this.mView!=null){
            mView.showProgressBar();
            mInteractor.callDayEndListData(this);
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
    public void locationAdapterData(List<Object> locationObjectList) {
        if (mView!=null){
            mView.setSpinnerLocation(locationObjectList);
        }
    }

    @Override
    public void fromInvoiceAdapterData(List<Object> fromInvoiceObjectList) {
        if (mView!=null){
            mView.setSpinnerFromInv(fromInvoiceObjectList);
        }
    }

    @Override
    public void toInvoiceAdapterData(List<Object> toInvoiceObjectList) {
        if (mView!=null){
            mView.setSpinnerToInv(toInvoiceObjectList);
        }
    }

    @Override
    public void onFinished(Restaurant_Day_EndData_Register registerList) {
        if (mView!=null){
            mView.hideProgressBar();
            mView.setDataRegisterAdapterView(registerList);
        }
    }


}
