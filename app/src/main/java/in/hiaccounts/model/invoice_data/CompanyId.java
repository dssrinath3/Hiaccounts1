
package in.hiaccounts.model.invoice_data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CompanyId implements Serializable{
    @SerializedName("companyName")
    @Expose
    private String companyName;
    @SerializedName("useraccount_id")
    @Expose
    private Object useraccountId;
    @SerializedName("dbName")
    @Expose
    private Object dbName;
    @SerializedName("createdDate")
    @Expose
    private Object createdDate;
    @SerializedName("cmpStatus")
    @Expose
    private String cmpStatus;
    @SerializedName("cfgFileName")
    @Expose
    private Object cfgFileName;
    @SerializedName("financialYear")
    @Expose
    private Object financialYear;
    @SerializedName("locationId")
    @Expose
    private Object locationId;
    @SerializedName("companyId")
    @Expose
    private long companyId;

    /**
     * No args constructor for use in serialization
     *
     */
    public CompanyId() {
    }

    /**
     *
     * @param cfgFileName
     * @param financialYear
     * @param cmpStatus
     * @param dbName
     * @param locationId
     * @param useraccountId
     * @param companyId
     * @param companyName
     * @param createdDate
     */
    public CompanyId(String companyName, Object useraccountId, Object dbName, Object createdDate, String cmpStatus, Object cfgFileName, Object financialYear, Object locationId, long companyId) {
        super();
        this.companyName = companyName;
        this.useraccountId = useraccountId;
        this.dbName = dbName;
        this.createdDate = createdDate;
        this.cmpStatus = cmpStatus;
        this.cfgFileName = cfgFileName;
        this.financialYear = financialYear;
        this.locationId = locationId;
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Object getUseraccountId() {
        return useraccountId;
    }

    public void setUseraccountId(Object useraccountId) {
        this.useraccountId = useraccountId;
    }

    public Object getDbName() {
        return dbName;
    }

    public void setDbName(Object dbName) {
        this.dbName = dbName;
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

    public Object getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(Object financialYear) {
        this.financialYear = financialYear;
    }

    public Object getLocationId() {
        return locationId;
    }

    public void setLocationId(Object locationId) {
        this.locationId = locationId;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

}
