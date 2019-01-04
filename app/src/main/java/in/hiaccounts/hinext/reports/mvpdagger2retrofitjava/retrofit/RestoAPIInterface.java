package in.hiaccounts.hinext.reports.mvpdagger2retrofitjava.retrofit;

import java.util.List;

import in.hiaccounts.hinext.reports.mvpdagger2retrofitjava.pojo.CryptoData;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface RestoAPIInterface {
    @GET("ticker/?")
    Observable<List<CryptoData>> getData(@Query("limit") String limit);



}
