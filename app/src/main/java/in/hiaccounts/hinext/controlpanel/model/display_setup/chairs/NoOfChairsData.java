package in.hiaccounts.hinext.controlpanel.model.display_setup.chairs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class NoOfChairsData implements Serializable {
    @SerializedName("tableid")
    @Expose
    public Long tableid;
    @SerializedName("tableName")
    @Expose
    public String tableName;
    @SerializedName("gridLocationH")
    @Expose
    public Long gridLocationH;
    @SerializedName("gridLocationV")
    @Expose
    public Long gridLocationV;
    @SerializedName("configurationname")
    @Expose
    public String configurationname;
    @SerializedName("noOfChairs")
    @Expose
    public Long noOfChairs;
    @SerializedName("companyId")
    @Expose
    public CompanyId companyId;
    @SerializedName("locationId")
    @Expose
    public String locationId;
    @SerializedName("useraccount_id")
    @Expose
    public String useraccountId;
    @SerializedName("status")
    @Expose
    public String status;

    public Long getTableid() {
        return tableid;
    }

    public void setTableid(Long tableid) {
        this.tableid = tableid;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Long getGridLocationH() {
        return gridLocationH;
    }

    public void setGridLocationH(Long gridLocationH) {
        this.gridLocationH = gridLocationH;
    }

    public Long getGridLocationV() {
        return gridLocationV;
    }

    public void setGridLocationV(Long gridLocationV) {
        this.gridLocationV = gridLocationV;
    }

    public String getConfigurationname() {
        return configurationname;
    }

    public void setConfigurationname(String configurationname) {
        this.configurationname = configurationname;
    }

    public Long getNoOfChairs() {
        return noOfChairs;
    }

    public void setNoOfChairs(Long noOfChairs) {
        this.noOfChairs = noOfChairs;
    }

    public CompanyId getCompanyId() {
        return companyId;
    }

    public void setCompanyId(CompanyId companyId) {
        this.companyId = companyId;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getUseraccountId() {
        return useraccountId;
    }

    public void setUseraccountId(String useraccountId) {
        this.useraccountId = useraccountId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
