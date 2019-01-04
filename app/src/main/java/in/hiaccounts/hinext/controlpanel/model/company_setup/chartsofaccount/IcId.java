
package in.hiaccounts.hinext.controlpanel.model.company_setup.chartsofaccount;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class IcId implements Serializable{

    @SerializedName("locationId")
    @Expose
    public Object locationId;
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
    public Boolean icflag;

    public Object getLocationId() {
        return locationId;
    }

    public void setLocationId(Object locationId) {
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

    public Boolean getIcflag() {
        return icflag;
    }

    public void setIcflag(Boolean icflag) {
        this.icflag = icflag;
    }
}
