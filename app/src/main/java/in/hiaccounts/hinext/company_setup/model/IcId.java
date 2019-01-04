
package in.hiaccounts.hinext.company_setup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class IcId implements Serializable {

    @SerializedName("locationId")
    @Expose
    public Long locationId;
    @SerializedName("useraccount_id")
    @Expose
    public Long useraccountId;
    @SerializedName("industryClassificationDesc")
    @Expose
    public String industryClassificationDesc;
    @SerializedName("industryClassificationName")
    @Expose
    public String industryClassificationName;
    @SerializedName("industryClassificationId")
    @Expose
    public Long industryClassificationId;
    @SerializedName("icflag")
    @Expose
    public boolean icflag;

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Long getUseraccountId() {
        return useraccountId;
    }

    public void setUseraccountId(Long useraccountId) {
        this.useraccountId = useraccountId;
    }

    public String getIndustryClassificationDesc() {
        return industryClassificationDesc;
    }

    public void setIndustryClassificationDesc(String industryClassificationDesc) {
        this.industryClassificationDesc = industryClassificationDesc;
    }

    public String getIndustryClassificationName() {
        return industryClassificationName;
    }

    public void setIndustryClassificationName(String industryClassificationName) {
        this.industryClassificationName = industryClassificationName;
    }

    public Long getIndustryClassificationId() {
        return industryClassificationId;
    }

    public void setIndustryClassificationId(Long industryClassificationId) {
        this.industryClassificationId = industryClassificationId;
    }

    public boolean isIcflag() {
        return icflag;
    }

    public void setIcflag(boolean icflag) {
        this.icflag = icflag;
    }
}
