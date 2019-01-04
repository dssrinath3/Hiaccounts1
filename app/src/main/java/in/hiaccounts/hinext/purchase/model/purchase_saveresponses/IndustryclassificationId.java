
package in.hiaccounts.hinext.purchase.model.purchase_saveresponses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class IndustryclassificationId implements Serializable{

    @SerializedName("locationId")
    @Expose
    public Long locationId;
    @SerializedName("useraccount_id")
    @Expose
    public Object useraccountId;
    @SerializedName("industryClassificationId")
    @Expose
    public Long industryClassificationId;
    @SerializedName("industryClassificationName")
    @Expose
    public String industryClassificationName;
    @SerializedName("industryClassificationDesc")
    @Expose
    public String industryClassificationDesc;
    @SerializedName("icflag")
    @Expose
    public boolean icflag;

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Object getUseraccountId() {
        return useraccountId;
    }

    public void setUseraccountId(Object useraccountId) {
        this.useraccountId = useraccountId;
    }

    public Long getIndustryClassificationId() {
        return industryClassificationId;
    }

    public void setIndustryClassificationId(Long industryClassificationId) {
        this.industryClassificationId = industryClassificationId;
    }

    public String getIndustryClassificationName() {
        return industryClassificationName;
    }

    public void setIndustryClassificationName(String industryClassificationName) {
        this.industryClassificationName = industryClassificationName;
    }

    public String getIndustryClassificationDesc() {
        return industryClassificationDesc;
    }

    public void setIndustryClassificationDesc(String industryClassificationDesc) {
        this.industryClassificationDesc = industryClassificationDesc;
    }

    public boolean isIcflag() {
        return icflag;
    }

    public void setIcflag(boolean icflag) {
        this.icflag = icflag;
    }
}
