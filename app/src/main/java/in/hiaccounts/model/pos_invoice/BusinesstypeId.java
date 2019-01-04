
package in.hiaccounts.model.pos_invoice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BusinesstypeId implements Serializable{

    @SerializedName("btflag")
    @Expose
    private boolean btflag;
    @SerializedName("businessTypeId")
    @Expose
    private int businessTypeId;
    @SerializedName("industryClassificationId")
    @Expose
    private IndustryclassificationId industryClassificationId;
    @SerializedName("businessTypeName")
    @Expose
    private String businessTypeName;
    @SerializedName("businessTypeDesc")
    @Expose
    private String businessTypeDesc;

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
     * @param businessTypeDesc
     * @param businessTypeName
     */
    public BusinesstypeId(boolean btflag, int businessTypeId, IndustryclassificationId industryClassificationId, String businessTypeName, String businessTypeDesc) {
        super();
        this.btflag = btflag;
        this.businessTypeId = businessTypeId;
        this.industryClassificationId = industryClassificationId;
        this.businessTypeName = businessTypeName;
        this.businessTypeDesc = businessTypeDesc;
    }

    public boolean isBtflag() {
        return btflag;
    }

    public void setBtflag(boolean btflag) {
        this.btflag = btflag;
    }

    public int getBusinessTypeId() {
        return businessTypeId;
    }

    public void setBusinessTypeId(int businessTypeId) {
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

}
