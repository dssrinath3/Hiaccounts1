
package in.hiaccounts.hinext.generaltransaction.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IndustryclassificationId {

    @SerializedName("locationId")
    @Expose
    public Object locationId;
    @SerializedName("useraccount_id")
    @Expose
    public Object useraccountId;
    @SerializedName("industryClassificationId")
    @Expose
    public Integer industryClassificationId;
    @SerializedName("industryClassificationName")
    @Expose
    public String industryClassificationName;
    @SerializedName("icflag")
    @Expose
    public Boolean icflag;
    @SerializedName("industryClassificationDesc")
    @Expose
    public String industryClassificationDesc;

}
