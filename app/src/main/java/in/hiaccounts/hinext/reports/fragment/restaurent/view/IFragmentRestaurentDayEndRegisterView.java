package in.hiaccounts.hinext.reports.fragment.restaurent.view;

import android.app.Activity;
import android.widget.Spinner;

import java.util.List;

import in.hiaccounts.hinext.reports.model.restuarant.Restaurant_Day_EndData_Register;

public interface IFragmentRestaurentDayEndRegisterView {
    void showProgressBar();
    void hideProgressBar();
    void setFromDate(String fromDate);
    void setToDate(String toDate);
    void customerData();
    void employeeData();
    void viewItemDetails();
    void setSpinnerLocation(List<Object> locationObjectList);
    void setSpinnerFromInv(List<Object> fromInvObjectList);
    void setSpinnerToInv(List<Object> toInvObjectList);
    void setDataRegisterAdapterView(Restaurant_Day_EndData_Register registerlistAdapterView);

}
