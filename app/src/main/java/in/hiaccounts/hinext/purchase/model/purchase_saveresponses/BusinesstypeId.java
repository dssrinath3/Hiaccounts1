
package in.hiaccounts.hinext.purchase.model.purchase_saveresponses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BusinesstypeId implements Serializable{

    @SerializedName("businessTypeId")
    @Expose
    public Long businessTypeId;
    @SerializedName("industryClassificationId")
    @Expose
    public IndustryclassificationId industryClassificationId;
    @SerializedName("businessTypeName")
    @Expose
    public String businessTypeName;
    @SerializedName("businessTypeDesc")
    @Expose
    public String businessTypeDesc;
    @SerializedName("btflag")
    @Expose
    public boolean btflag;

    public Long getBusinessTypeId() {
        return businessTypeId;
    }

    public void setBusinessTypeId(Long businessTypeId) {
        this.businessTypeId = businessTypeId;
    }

    public IndustryclassificationId getIndustryClassificationId() {
        return industryClassificationId;
    }

    public void setIndustryClassificationId(IndustryclassificationId industryClassificationId) {
        this.industryClassificationId = industryClassificationId;
    }

    public String getBusinessTypeName() {
        return businessTypeName;
    }

    public void setBusinessTypeName(String businessTypeName) {
        this.businessTypeName = businessTypeName;
    }

    public String getBusinessTypeDesc() {
        return businessTypeDesc;
    }

    public void setBusinessTypeDesc(String businessTypeDesc) {
        this.businessTypeDesc = businessTypeDesc;
    }

    public boolean isBtflag() {
        return btflag;
    }

    public void setBtflag(boolean btflag) {
        this.btflag = btflag;
    }
}
