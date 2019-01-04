package in.hiaccounts.hinext.company_setup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Admin on 11/11/2017.
 */

public class IndustryClassificationList implements Serializable {

    @SerializedName("locationId")
    @Expose
    public Long locationId;

    @SerializedName("useraccount_id")
    @Expose
    public Long useraccount_id;

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

    public Long getUseraccount_id() {
        return useraccount_id;
    }

    public void setUseraccount_id(Long useraccount_id) {
        this.useraccount_id = useraccount_id;
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

    @Override
    public String toString() {
        return industryClassificationName;
    }
}
