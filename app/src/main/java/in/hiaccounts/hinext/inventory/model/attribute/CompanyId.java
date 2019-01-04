package in.hiaccounts.hinext.inventory.model.attribute;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by shrinath on 12/13/2017.
 */

public class CompanyId implements Serializable {
    @SerializedName("companyId")
    @Expose
    private Long companyId;
    @SerializedName("companyName")
    @Expose
    private String companyName;
    @SerializedName("cfgFileName")
    @Expose
    private String cfgFileName;
    @SerializedName("dbName")
    @Expose
    private String dbName;
    @SerializedName("financialYear")
    @Expose
    private String financialYear;
    @SerializedName("createdDate")
    @Expose
    private String createdDate;
    @SerializedName("cmpStatus")
    @Expose
    private String cmpStatus;
    @SerializedName("locationId")
    @Expose
    private Long locationId;
    @SerializedName("useraccount_id")
    @Expose
    private Long useraccountId;
    @SerializedName("saasToken")
    @Expose
    private String saasToken;

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
