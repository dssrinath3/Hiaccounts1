package in.hiaccounts.hinext.service.model.service_invoice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by administrator on 23/2/18.
 */

public class CompanydupData implements Serializable {
    @SerializedName("companyId")
    @Expose
    public Long companyId;
    @SerializedName("companyName")
    @Expose
    public String companyName;
    @SerializedName("cfgFileName")
    @Expose
    public String cfgFileName;
    @SerializedName("dbName")
    @Expose
    public String dbName;
    @SerializedName("financialYear")
    @Expose
    public String financialYear;
    @SerializedName("createdDate")
    @Expose
    public String createdDate;
    @SerializedName("cmpStatus")
    @Expose
    public String cmpStatus;
    @SerializedName("locationId")
    @Expose
    public Long locationId;
    @SerializedName("useraccount_id")
    @Expose
    public Long useraccountId;
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

    public String getCfgFileName() {
        return cfgFileName;
    }

    public void setCfgFileName(String cfgFileName) {
        this.cfgFileName = cfgFileName;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(String financialYear) {
        this.financialYear = financialYear;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCmpStatus() {
        return cmpStatus;
    }

    public void setCmpStatus(String cmpStatus) {
        this.cmpStatus = cmpStatus;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Long getUseraccountId() {
        return useraccountId;
    }

    public void setUseraccountId(Long useraccountId) {
        this.useraccountId = useraccountId;
    }

    public String getSaasToken() {
        return saasToken;
    }

    public void setSaasToken(String saasToken) {
        this.saasToken = saasToken;
    }
}
