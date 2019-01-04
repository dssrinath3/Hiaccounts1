package in.hiaccounts.hinext.reports.fragment.sales.customerRegisterMVP.presenter;

import android.app.Activity;
import android.widget.EditText;

public interface IFragmentCustomerRegisterPresenter {
    void datePicker(Activity activity, EditText edFromdate);
    void selectCustomerData();
    void onDestroy();
    void getCustomerRegisterList(String search, boolean isFirst, boolean isPrev, String pageNumber, boolean isNext, boolean isLast);
    void getOnpageLoadData();
}
