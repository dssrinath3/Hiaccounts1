
package in.hiaccounts.hinext.controlpanel.model.useraccount_setup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CompanyId implements Serializable {

    @SerializedName("companyId")
    @Expose
    public Long companyId;
    @SerializedName("companyName")
    @Expose
    public String companyName;
    @SerializedName("cfgFileName")
    @Expose
    public Object cfgFileName;
    @SerializedName("dbName")
    @Expose
    public Object dbName;
    @SerializedName("financialYear")
    @Expose
    public Object financialYear;
    @SerializedName("createdDate")
    @Expose
    public Object createdDate;
    @SerializedName("cmpStatus")
    @Expose
    public String cmpStatus;
    @SerializedName("locationId")
    @Expose
    public Object locationId;
    @SerializedName("useraccount_id")
    @Expose
    public Object useraccountId;
    @SerializedName("saasToken")
    @Expose
    public String saasToken;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public Object getLocationId() {
        return locationId;
    }

    public void setLocationId(Object locationId) {
        this.locationId = locationId;
    }

    public Object getUseraccountId() {
        return useraccountId;
    }

    public void setUseraccountId(Object useraccountId) {
        this.useraccountId = useraccountId;
    }

    public String getSaasToken() {
        return saasToken;
    }

    public void setSaasToken(String saasToken) {
        this.saasToken = saasToken;
    }
}
