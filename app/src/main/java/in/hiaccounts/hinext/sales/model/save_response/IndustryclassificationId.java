
package in.hiaccounts.hinext.sales.model.save_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IndustryclassificationId {

    @SerializedName("locationId")
    @Expose
    private Object locationId;
    @SerializedName("useraccount_id")
    @Expose
    private Object useraccountId;
    @SerializedName("industryClassificationId")
    @Expose
    private Long industryClassificationId;
    @SerializedName("industryClassificationName")
    @Expose
    private String industryClassificationName;
    @SerializedName("icflag")
    @Expose
    private Boolean icflag;
    @SerializedName("industryClassificationDesc")
    @Expose
    private String industryClassificationDesc;


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

    public Boolean getIcflag() {
        return icflag;
    }

    public void setIcflag(Boolean icflag) {
        this.icflag = icflag;
    }

    public String getIndustryClassificationDesc() {
        return industryClassificationDesc;
    }

    public void setIndustryClassificationDesc(String industryClassificationDesc) {
        this.industryClassificationDesc = industryClassificationDesc;
    }

}
