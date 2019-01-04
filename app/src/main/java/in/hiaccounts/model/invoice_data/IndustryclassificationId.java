
package in.hiaccounts.model.invoice_data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class IndustryclassificationId implements Serializable {

    @SerializedName("useraccount_id")
    @Expose
    private Object useraccountId;
    @SerializedName("industryClassificationId")
    @Expose
    private long industryClassificationId;
    @SerializedName("industryClassificationName")
    @Expose
    private String industryClassificationName;
    @SerializedName("industryClassificationDesc")
    @Expose
    private String industryClassificationDesc;
    @SerializedName("icflag")
    @Expose
    private boolean icflag;
    @SerializedName("locationId")
    @Expose
    private Object locationId;

    /**
     * No args constructor for use in serialization
     *
     */
    public IndustryclassificationId() {
    }

    /**
     *
     * @param industryClassificationName
     * @param industryClassificationDesc
     * @param industryClassificationId
     * @param locationId
     * @param useraccountId
     * @param icflag
     */
    public IndustryclassificationId(Object useraccountId, long industryClassificationId, String industryClassificationName, String industryClassificationDesc, boolean icflag, Object locationId) {
        super();
        this.useraccountId = useraccountId;
        this.industryClassificationId = industryClassificationId;
        this.industryClassificationName = industryClassificationName;
        this.industryClassificationDesc = industryClassificationDesc;
        this.icflag = icflag;
        this.locationId = locationId;
    }

    public Object getUseraccountId() {
        return useraccountId;
    }

    public void setUseraccountId(Object useraccountId) {
        this.useraccountId = useraccountId;
    }

    public long getIndustryClassificationId() {
        return industryClassificationId;
    }

    public void setIndustryClassificationId(long industryClassificationId) {
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

    public Object getLocationId() {
        return locationId;
    }

    public void setLocationId(Object locationId) {
        this.locationId = locationId;
    }

}
