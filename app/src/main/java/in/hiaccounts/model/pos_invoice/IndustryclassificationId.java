
package in.hiaccounts.model.pos_invoice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class IndustryclassificationId implements Serializable {

    @SerializedName("icflag")
    @Expose
    private boolean icflag;
    @SerializedName("industryClassificationId")
    @Expose
    private int industryClassificationId;
    @SerializedName("industryClassificationDesc")
    @Expose
    private String industryClassificationDesc;
    @SerializedName("industryClassificationName")
    @Expose
    private String industryClassificationName;

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
     * @param icflag
     */
    public IndustryclassificationId(boolean icflag, int industryClassificationId, String industryClassificationDesc, String industryClassificationName) {
        super();
        this.icflag = icflag;
        this.industryClassificationId = industryClassificationId;
        this.industryClassificationDesc = industryClassificationDesc;
        this.industryClassificationName = industryClassificationName;
    }

    public boolean isIcflag() {
        return icflag;
    }

    public void setIcflag(boolean icflag) {
        this.icflag = icflag;
    }

    public int getIndustryClassificationId() {
        return industryClassificationId;
    }

    public void setIndustryClassificationId(int industryClassificationId) {
        this.industryClassificationId = industryClassificationId;
    }

    public String getIndustryClassificationDesc() {
        return industryClassificationDesc;
    }

    public void setIndustryClassificationDesc(String industryClassificationDesc) {
        this.industryClassificationDesc = industryClassificationDesc;
    }

    public String getIndustryClassificationName() {
        return industryClassificationName;
    }

    public void setIndustryClassificationName(String industryClassificationName) {
        this.industryClassificationName = industryClassificationName;
    }

}
