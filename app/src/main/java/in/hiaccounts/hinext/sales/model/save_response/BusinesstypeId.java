
package in.hiaccounts.hinext.sales.model.save_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BusinesstypeId implements Serializable{

    @SerializedName("industryClassificationId")
    @Expose
    private IndustryclassificationId industryClassificationId;
    @SerializedName("businessTypeId")
    @Expose
    private Long businessTypeId;
    @SerializedName("businessTypeName")
    @Expose
    private String businessTypeName;
    @SerializedName("btflag")
    @Expose
    private Boolean btflag;
    @SerializedName("businessTypeDesc")
    @Expose
    private String businessTypeDesc;

    /**
     * No args constructor for use in serialization
     * 
     */

    public IndustryclassificationId getIndustryClassificationId() {
        return industryClassificationId;
    }

    public void setIndustryClassificationId(IndustryclassificationId industryClassificationId) {
        this.industryClassificationId = industryClassificationId;
    }

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

    public Boolean getBtflag() {
        return btflag;
    }

    public void setBtflag(Boolean btflag) {
        this.btflag = btflag;
    }

    public String getBusinessTypeDesc() {
        return businessTypeDesc;
    }

    public void setBusinessTypeDesc(String businessTypeDesc) {
        this.businessTypeDesc = businessTypeDesc;
    }

}
