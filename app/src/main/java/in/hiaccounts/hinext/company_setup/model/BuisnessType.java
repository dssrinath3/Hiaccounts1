package in.hiaccounts.hinext.company_setup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Admin on 11/13/2017.
 */

public class BuisnessType implements Serializable {

    @SerializedName("businessTypeDesc")
    @Expose
    private String businessTypeDesc;
    @SerializedName("businessTypeName")
    @Expose
    private String businessTypeName;
    @SerializedName("businessTypeId")
    @Expose
    private Long businessTypeId;
    @SerializedName("industryClassificationId")
    @Expose
    private IndustryClassificationList industryClassificationId;
    @SerializedName("btflag")
    @Expose
    private Boolean btflag;

    public String getBusinessTypeDesc() {
        return businessTypeDesc;
    }

    public void setBusinessTypeDesc(String businessTypeDesc) {
        this.businessTypeDesc = businessTypeDesc;
    }

    public String getBusinessTypeName() {
        return businessTypeName;
    }

    public void setBusinessTypeName(String businessTypeName) {
        this.businessTypeName = businessTypeName;
    }

    public Long getBusinessTypeId() {
        return businessTypeId;
    }

    public void setBusinessTypeId(Long businessTypeId) {
        this.businessTypeId = businessTypeId;
    }

    public IndustryClassificationList getIndustryClassificationId() {
        return industryClassificationId;
    }

    public void setIndustryClassificationId(IndustryClassificationList industryClassificationId) {
        this.industryClassificationId = industryClassificationId;
    }

    public Boolean getBtflag() {
        return btflag;
    }

    public void setBtflag(Boolean btflag) {
        this.btflag = btflag;
    }

    @Override
    public String toString() {
        return businessTypeName;
    }
}
