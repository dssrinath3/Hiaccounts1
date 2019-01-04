
package in.hiaccounts.hinext.controlpanel.model.company_setup.chartsofaccount;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BtId  implements Serializable{

    @SerializedName("businessTypeId")
    @Expose
    public Long businessTypeId;
    @SerializedName("businessTypeName")
    @Expose
    public String businessTypeName;
    @SerializedName("industryClassificationId")
    @Expose
    public IndustryClassificationId industryClassificationId;
    @SerializedName("businessTypeDesc")
    @Expose
    public String businessTypeDesc;
    @SerializedName("btflag")
    @Expose
    public Boolean btflag;

    public Long getBusinessTypeId() {
        return businessTypeId;
    }

    public void setBusinessTypeId(Long businessTypeId) {
        this.businessTypeId = businessTypeId;
    }

    public String getBusinessTypeName() {
        return businessTypeName;
    }

    public void setBusinessTypeName(String businessTypeName) {
        this.businessTypeName = businessTypeName;
    }

    public IndustryClassificationId getIndustryClassificationId() {
        return industryClassificationId;
    }

    public void setIndustryClassificationId(IndustryClassificationId industryClassificationId) {
        this.industryClassificationId = industryClassificationId;
    }

    public String getBusinessTypeDesc() {
        return businessTypeDesc;
    }

    public void setBusinessTypeDesc(String businessTypeDesc) {
        this.businessTypeDesc = businessTypeDesc;
    }

    public Boolean getBtflag() {
        return btflag;
    }

    public void setBtflag(Boolean btflag) {
        this.btflag = btflag;
    }
}
