
package in.hiaccounts.hinext.generaltransaction.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompanyInfoId {

    @SerializedName("companyId")
    @Expose
    public Integer companyId;
    @SerializedName("companyName")
    @Expose
    public String companyName;
    @SerializedName("cfgFileName")
    @Expose
    public Object cfgFileName;
    @SerializedName("dbName")
    @Expose
    public Object dbName;
    @SerializedName("financialYear")
    @Expose
    public Object financialYear;
    @SerializedName("createdDate")
    @Expose
    public Object createdDate;
    @SerializedName("cmpStatus")
    @Expose
    public String cmpStatus;
    @SerializedName("locationId")
    @Expose
    public Object locationId;
    @SerializedName("useraccount_id")
    @Expose
    public Object useraccountId;
    @SerializedName("saasToken")
    @Expose
    public String saasToken;

}
