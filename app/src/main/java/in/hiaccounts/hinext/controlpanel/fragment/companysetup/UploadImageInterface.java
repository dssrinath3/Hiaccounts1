package in.hiaccounts.hinext.controlpanel.fragment.companysetup;

import in.hiaccounts.hinext.company_setup.model.CompanyPageInfo;
import in.hiaccounts.hinext.controlpanel.model.company_setup.compnayinformation.CompanyData;
import in.hiaccounts.hinext.sales.model.sales_invoice.ResponseDataFromServer;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Admin on 10/10/2017.
 */

public interface UploadImageInterface {
    @Multipart
    @POST("/company/updateCompanyDetails")
    Call<CompanyData> uploadFile(@Part MultipartBody.Part file,
                                 @Part("company_name") RequestBody company_name,
                                 @Part("company_no") RequestBody company_no,
                                 @Part("panNo") RequestBody panNo,
                                 @Part("incdate") RequestBody incdate,
                                 @Part("address") RequestBody address,
                                 @Part("phone") RequestBody phone,
                                 @Part("fax") RequestBody fax,
                                 @Part("lang") RequestBody lang,
                                 @Part("email") RequestBody email,
                                 @Part("web") RequestBody web,
                                 @Part("registerNo") RequestBody registerNo,
                                 @Part("gstregisteredDate") RequestBody gstregisteredDate);



    @Multipart
    @POST("/company/add_company")
    Call<CompanyPageInfo>  uploadCompnaySetup(@Part MultipartBody.Part file,
                                              @Part("company_name") RequestBody company_name,
                                              @Part("company_no") RequestBody company_no,
                                              @Part("incdate") RequestBody incdate,
                                              @Part("registeredCompany") RequestBody registeredCompany,
                                              @Part("registerNo") RequestBody registerNo,
                                              @Part("gstregisteredDate") RequestBody gstregisteredDate,
                                              @Part("address") RequestBody address,
                                              @Part("panNumber") RequestBody panNo,
                                              @Part("phone") RequestBody phone,
                                              @Part("fax") RequestBody fax,
                                              @Part("lang") RequestBody lang,
                                              @Part("email") RequestBody email,
                                              @Part("web") RequestBody web,
                                              @Part("countryId") RequestBody countryId,
                                              @Part("stateId") RequestBody stateId,
                                              @Part("currencyId") RequestBody currencyId,

                                              @Part("yearclosing") RequestBody closingYear,
                                              @Part("closingMethod") RequestBody closingMethod,
                                              @Part("startperiod") RequestBody startperiod,
                                              @Part("closingperiod") RequestBody closingperiod,
                                              @Part("bufferDays") RequestBody bufferDays,
                                              @Part("startyear") RequestBody startyear,
                                              @Part("endyear") RequestBody endyear,
                                              @Part("bufferDate") RequestBody bufferDate,
                                              @Part("gstreturnalertdue") RequestBody gstreturnalertdue,

                                              @Part("createOrSelectAM") RequestBody createOrSelectAM,
                                              @Part("industryClassificationId") RequestBody industryClassificationId,
                                              @Part("businessTypeId") RequestBody businessTypeId,
                                              @Part("tempId") RequestBody tempId);


    @Multipart
    @POST("/hipos//0/itemAppendImage")
    Call<ResponseDataFromServer> uploadSalesInvoice(@Part MultipartBody.Part file);


}
