
package in.hiaccounts.hinext.generaltransaction.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BusinesstypeId {

    @SerializedName("businessTypeId")
    @Expose
    public Integer businessTypeId;
    @SerializedName("businessTypeName")
    @Expose
    public String businessTypeName;
    @SerializedName("industryClassificationId")
    @Expose
    public IndustryclassificationId industryClassificationId;
    @SerializedName("btflag")
    @Expose
    public Boolean btflag;
    @SerializedName("businessTypeDesc")
    @Expose
    public String businessTypeDesc;

}
