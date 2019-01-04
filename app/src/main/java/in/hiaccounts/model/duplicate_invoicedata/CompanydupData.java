
package in.hiaccounts.model.duplicate_invoicedata;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompanydupData implements Serializable
{

    @SerializedName("companyId")
    @Expose
    private long companyId;
    @SerializedName("cfgFileName")
    @Expose
    private String cfgFileName;
    @SerializedName("createdDate")
    @Expose
    private String createdDate;
    @SerializedName("dbName")
    @Expose
    private String dbName;
    @SerializedName("cmpStatus")
    @Expose
    private String cmpStatus;
    @SerializedName("financialYear")
    @Expose
    private String financialYear;
    @SerializedName("companyName")
    @Expose
    private String companyName;
    private final static long serialVersionUID = -5896071434117541454L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CompanydupData() {
    }

    /**
     * 
     * @param cfgFileName
     * @param financialYear
     * @param cmpStatus
     * @param dbName
     * @param companyId
     * @param companyName
     * @param createdDate
     */
    public CompanydupData(long companyId, String cfgFileName, String createdDate, String dbName, String cmpStatus, String financialYear, String companyName) {
        this.companyId = companyId;
        this.cfgFileName = cfgFileName;
        this.createdDate = createdDate;
        this.dbName = dbName;
        this.cmpStatus = cmpStatus;
        this.financialYear = financialYear;
        this.companyName = companyName;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public String getCfgFileName() {
        return cfgFileName;
    }

    public void setCfgFileName(String cfgFileName) {
        this.cfgFileName = cfgFileName;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getCmpStatus() {
        return cmpStatus;
    }

    public void setCmpStatus(String cmpStatus) {
        this.cmpStatus = cmpStatus;
    }

    public String getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(String financialYear) {
        this.financialYear = financialYear;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
