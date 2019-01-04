package in.hiaccounts.hinext.reports.fragment.restaurent.network;

import java.util.List;

import in.hiaccounts.hinext.reports.model.restuarant.Restaurant_Day_EndData_Register;
import in.hiaccounts.hinext.reports.model.restuarant.Restaurant_Item_Sales;
import in.hiaccounts.utility.Constant;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiService {

    //filter restaurent day end register data
    @GET("/reports/sales/salesDayEndInvoice")
    Single<Restaurant_Day_EndData_Register> getViewItemDataService(@Query("currency") Long currency, @Query("customerId") Long customerId, @Query("filterApplied") Boolean filterApplay, @Query("fromDate") String fromDate, @Query("fromSID") Long fromSID, @Query("location") Long location, @Query("selectedList") String emp, @Query("toDate") String toDate, @Query("toSID") Long toSID);

    //filter restaurent item sales report data
    @GET("/reports/sales/restItemWise")
    Single<Restaurant_Item_Sales> getViewItemSalesReportDataService(@Query("currency") Long currency, @Query("filterApplied") Boolean filterApplay, @Query("fromDate") String fromDate, @Query("fromSID") Long fromSID, @Query("itemCategoryId") Long itemCategoryId,@Query("itemId") Long itemId, @Query("location") Long location, @Query("toDate") String toDate, @Query("toSID") Long toSID);

    //filter restaurent search monthend report data
    @GET("/reports/sales/"+Constant.FUNTION_REPORTRESTAURENTMONTHEENDLIST)
    Single<Restaurant_Item_Sales> getViewSearchItemMonthEndReportDataService(@Query("currency") Long currency, @Query("filterApplied") Boolean filterApplay, @Query("fromDate") String fromDate, @Query("fromSID") Long fromSID, @Query("itemCategoryId") Long itemCategoryId,@Query("itemId") Long itemId, @Query("location") Long location, @Query("toDate") String toDate, @Query("toSID") Long toSID);

    //filter restaurent monthend report data
    @GET("/reports/sales/"+Constant.FUNTION_REPORTRESTAURENTMONTHEENDLIST)
    Single<Restaurant_Item_Sales> getViewItemMonthEndReportDataService(@Query("toDate") String toDate);



}
