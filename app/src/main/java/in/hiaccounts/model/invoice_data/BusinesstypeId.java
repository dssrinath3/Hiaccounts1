
package in.hiaccounts.model.invoice_data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BusinesstypeId implements Serializable{

    @SerializedName("btflag")
    @Expose
    private boolean btflag;
    @SerializedName("industryClassificationId")
    @Expose
    private IndustryclassificationId industryClassificationId;
    @SerializedName("businessTypeId")
    @Expose
    private long businessTypeId;
    @SerializedName("businessTypeDesc")
    @Expose
    private String businessTypeDesc;
    @SerializedName("businessTypeName")
    @Expose
    private String businessTypeName;

    /**
     * No args constructor for use in serialization
     * 
     */
    public BusinesstypeId() {
    }

    /**
     * 
     * @param industryClassificationId
     * @param btflag
     * @param businessTypeId
     * @param businessTypeName
     * @param businessTypeDesc
     */
    public BusinesstypeId(boolean btflag, IndustryclassificationId industryClassificationId, long businessTypeId, String businessTypeDesc, String businessTypeName) {
        super();
        this.btflag = btflag;
        this.industryClassificationId = industryClassificationId;
        this.businessTypeId = businessTypeId;
        this.businessTypeDesc = businessTypeDesc;
        this.businessTypeName = businessTypeName;
    }

    public boolean isBtflag() {
        return btflag;
    }

    public void setBtflag(boolean btflag) {
        this.btflag = btflag;
    }

    public IndustryclassificationId getIndustryClassificationId() {
        return industryClassificationId;
    }

    public void setIndustryClassificationId(IndustryclassificationId industryClassificationId) {
        this.industryClassificationId = industryClassificationId;
    }

    public long getBusinessTypeId() {
        return businessTypeId;
    }

    public void setBusinessTypeId(long businessTypeId) {
        this.businessTypeId = businessTypeId;
    }

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

}
