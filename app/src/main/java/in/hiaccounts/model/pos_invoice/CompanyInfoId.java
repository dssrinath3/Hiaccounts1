
package in.hiaccounts.model.pos_invoice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CompanyInfoId implements Serializable {

    @SerializedName("companyName")
    @Expose
    private String companyName;
    @SerializedName("financialYear")
    @Expose
    private Object financialYear;
    @SerializedName("createdDate")
    @Expose
    private Object createdDate;
    @SerializedName("cmpStatus")
    @Expose
    private String cmpStatus;
    @SerializedName("cfgFileName")
    @Expose
    private Object cfgFileName;
    @SerializedName("dbName")
    @Expose
    private Object dbName;
    @SerializedName("companyId")
    @Expose
    private int companyId;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CompanyInfoId() {
    }

    /**
     * 
     * @param cfgFileName
     * @param cmpStatus
     * @param financialYear
     * @param dbName
     * @param companyId
     * @param companyName
     * @param createdDate
     */
    public CompanyInfoId(String companyName, Object financialYear, Object createdDate, String cmpStatus, Object cfgFileName, Object dbName, int companyId) {
        super();
        this.companyName = companyName;
        this.financialYear = financialYear;
        this.createdDate = createdDate;
        this.cmpStatus = cmpStatus;
        this.cfgFileName = cfgFileName;
        this.dbName = dbName;
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Object getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(Object financialYear) {
        this.financialYear = financialYear;
    }

    public Object getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Object createdDate) {
        this.createdDate = createdDate;
    }

    public String getCmpStatus() {
        return cmpStatus;
    }

    public void setCmpStatus(String cmpStatus) {
        this.cmpStatus = cmpStatus;
    }

    public Object getCfgFileName() {
        return cfgFileName;
    }

    public void setCfgFileName(Object cfgFileName) {
        this.cfgFileName = cfgFileName;
    }

    public Object getDbName() {
        return dbName;
    }

    public void setDbName(Object dbName) {
        this.dbName = dbName;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

}
