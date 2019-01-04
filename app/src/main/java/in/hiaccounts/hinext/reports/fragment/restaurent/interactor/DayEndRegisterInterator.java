package in.hiaccounts.hinext.reports.fragment.restaurent.interactor;

import java.util.List;

import in.hiaccounts.hinext.reports.model.restuarant.Restaurant_Day_EndData_Register;

public interface DayEndRegisterInterator {


    interface onDayEndFinishedListner{
        void onFailure();
        void onSuccess();
        void locationAdapterData(List<Object> locationObjectList );
        void fromInvoiceAdapterData(List<Object> fromInvoiceObjectList );
        void toInvoiceAdapterData(List<Object> toInvoiceObjectList );
        void onFinished(Restaurant_Day_EndData_Register registerList);
    }
    void callOnLoadPageData(onDayEndFinishedListner listner);
    void callDayEndListData(onDayEndFinishedListner listner);
    void callviewItemData(Long currencyId, Long customerId, boolean filterApplied, String frmDate, Long sFromId, Long locationId, String employeeName, String toDate, Long sToId, onDayEndFinishedListner listner);


}
