
package in.hiaccounts.hinext.company_setup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BtId implements Serializable {

    @SerializedName("businessTypeDesc")
    @Expose
    public String businessTypeDesc;
    @SerializedName("businessTypeName")
    @Expose
    public String businessTypeName;
    @SerializedName("businessTypeId")
    @Expose
    public Long businessTypeId;
    @SerializedName("industryClassificationId")
    @Expose
    public IndustryClassificationList industryClassificationId;
    @SerializedName("btflag")
    @Expose
    public boolean btflag;

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

    public boolean isBtflag() {
        return btflag;
    }

    public void setBtflag(boolean btflag) {
        this.btflag = btflag;
    }
}
