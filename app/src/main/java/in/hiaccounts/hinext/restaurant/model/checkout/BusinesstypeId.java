
package in.hiaccounts.hinext.restaurant.model.checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import in.hiaccounts.hinext.sales.model.printlist.payement_receipt.IndustryClassificationId;

public class BusinesstypeId implements Serializable {

    @SerializedName("businessTypeDesc")
    @Expose
    public String businessTypeDesc;
    @SerializedName("businessTypeName")
    @Expose
    public String businessTypeName;
    @SerializedName("btflag")
    @Expose
    public boolean btflag;
    @SerializedName("businessTypeId")
    @Expose
    public long businessTypeId;
    @SerializedName("industryClassificationId")
    @Expose
    public IndustryClassificationId industryClassificationId;

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

    public boolean isBtflag() {
        return btflag;
    }

    public void setBtflag(boolean btflag) {
        this.btflag = btflag;
    }

    public long getBusinessTypeId() {
        return businessTypeId;
    }

    public void setBusinessTypeId(long businessTypeId) {
        this.businessTypeId = businessTypeId;
    }

    public IndustryClassificationId getIndustryClassificationId() {
        return industryClassificationId;
    }

    public void setIndustryClassificationId(IndustryClassificationId industryClassificationId) {
        this.industryClassificationId = industryClassificationId;
    }
}
