package in.hiaccounts.hinext.reports.fragment.sales.customerRegisterMVP.network;

import in.hiaccounts.hinext.reports.model.restuarant.Restaurant_Day_EndData_Register;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SalesApiService {

    //http://4a506bf2.ngrok.io/reports/sales/customerListing?fromDate=2019-01-04T07:44:43.436Z

    @GET("/reports/sales/customerListing?fromDate=2019-01-04T07:44:43.436Z")
    Single<CustomerRegisterList> getCustomerRegisterService();

}
