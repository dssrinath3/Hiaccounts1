package in.hiaccounts.hinext.reports.fragment.restaurent.presenter;

import android.app.Activity;
import android.widget.EditText;

public interface IFragmentRestaurentDayEndRegisterPresenter {

    void fromDatePicker(Activity activity, EditText edFromdate);
    void toDatePicker(Activity activity,EditText edToDate);
    void selectCustomerData();
    void selectEmplyee();
    void viewItem(Long currencyId, Long customerId, boolean filterApplied, String frmDate, Long sFromId, Long locationId, String employeeName, String toDate, Long sToId);
    void onDestroy();
    void getdayEndList(String search, boolean isFirst, boolean isPrev, String pageNumber, boolean isNext, boolean isLast);
    void getOnpageLoadData();


}
