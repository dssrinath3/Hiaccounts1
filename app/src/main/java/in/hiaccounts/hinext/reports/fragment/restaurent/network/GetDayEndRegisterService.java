package in.hiaccounts.hinext.reports.fragment.restaurent.network;

import java.util.List;

import in.hiaccounts.hinext.reports.model.SalesReportData;
import in.hiaccounts.hinext.reports.model.restuarant.Restaurant_Day_EndData_Register;
import in.hiaccounts.utility.Constant;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface GetDayEndRegisterService {
    //get onpageload data
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("/reports/sales/invoice/" + Constant.FUNTION_SALESREPORTONLOADPAGEDATA)
    Call<SalesReportData> getOnloadpageData(@Header("Cookie") String auth);


    //get day service data
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("/reports/sales/salesDayEndInvoice?toDate=2018-12-26T09:54:46.440Z")
    Call<Restaurant_Day_EndData_Register> getDayEndRegisterService(@Header("Cookie") String auth);


}
