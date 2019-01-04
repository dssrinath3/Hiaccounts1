
package in.hiaccounts.hinext.sales.model.printlist.payement_receipt;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BusinesstypeId implements Serializable{

    @SerializedName("industryClassificationId")
    @Expose
    public IndustryClassificationId industryClassificationId;
    @SerializedName("businessTypeId")
    @Expose
    public long businessTypeId;
    @SerializedName("businessTypeName")
    @Expose
    public String businessTypeName;
    @SerializedName("businessTypeDesc")
    @Expose
    public String businessTypeDesc;
    @SerializedName("btflag")
    @Expose
    public boolean btflag;

    public IndustryClassificationId getIndustryClassificationId() {
        return industryClassificationId;
    }

    public void setIndustryClassificationId(IndustryClassificationId industryClassificationId) {
        this.industryClassificationId = industryClassificationId;
    }

    public long getBusinessTypeId() {
        return businessTypeId;
    }

    public void setBusinessTypeId(long businessTypeId) {
        this.businessTypeId = businessTypeId;
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
