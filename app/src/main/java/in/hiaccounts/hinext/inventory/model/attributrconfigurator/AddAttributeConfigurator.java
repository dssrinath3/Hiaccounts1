package in.hiaccounts.hinext.inventory.model.attributrconfigurator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by shrinath on 12/8/2017.
 */

public class AddAttributeConfigurator implements Serializable {
    @SerializedName("attributeConfiguratorId")
    @Expose
    private Long attributeConfiguratorId;
    @SerializedName("attributeConfiguratorEffectiveDate")
    @Expose
    private String attributeConfiguratorEffectiveDate;
    @SerializedName("attributeConfiguratorValue")
    @Expose
    private String attributeConfiguratorValue;
    @SerializedName("attributeConfiguratorName")
    @Expose
    private String attributeConfiguratorName;
    @SerializedName("attributeConfiguratorDescription")
    @Expose
    private String attributeConfiguratorDescription;
    @SerializedName("companyId")
    @Expose
    private Long companyId;
    @SerializedName("locationId")
    @Expose
    private Long locationId;
    @SerializedName("useraccount_id")
    @Expose
    private Long useraccountId;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("attributeDTOList")
    @Expose
    private String attributeDTOList;

    public Long getAttributeConfiguratorId() {
        return attributeConfiguratorId;
    }

    public void setAttributeConfiguratorId(Long attributeConfiguratorId) {
        this.attributeConfiguratorId = attributeConfiguratorId;
    }

    public String getAttributeConfiguratorEffectiveDate() {
        return attributeConfiguratorEffectiveDate;
    }

    public void setAttributeConfiguratorEffectiveDate(String attributeConfiguratorEffectiveDate) {
        this.attributeConfiguratorEffectiveDate = attributeConfiguratorEffectiveDate;
    }

    public String getAttributeConfiguratorValue() {
        return attributeConfiguratorValue;
    }

    public void setAttributeConfiguratorValue(String attributeConfiguratorValue) {
        this.attributeConfiguratorValue = attributeConfiguratorValue;
    }

    public String getAttributeConfiguratorName() {
        return attributeConfiguratorName;
    }

    public void setAttributeConfiguratorName(String attributeConfiguratorName) {
        this.attributeConfiguratorName = attributeConfiguratorName;
    }

    public String getAttributeConfiguratorDescription() {
        return attributeConfiguratorDescription;
    }

    public void setAttributeConfiguratorDescription(String attributeConfiguratorDescription) {
        this.attributeConfiguratorDescription = attributeConfiguratorDescription;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAttributeDTOList() {
        return attributeDTOList;
    }

    public void setAttributeDTOList(String attributeDTOList) {
        this.attributeDTOList = attributeDTOList;
    }
}
