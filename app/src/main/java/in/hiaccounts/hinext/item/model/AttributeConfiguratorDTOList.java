package in.hiaccounts.hinext.item.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by administrator on 24/1/18.
 */

public class AttributeConfiguratorDTOList implements Serializable {
    @SerializedName("attributeConfiguratorId")
    @Expose
    public Long attributeConfiguratorId;
    @SerializedName("attributeConfiguratorEffectiveDate")
    @Expose
    public String attributeConfiguratorEffectiveDate;
    @SerializedName("attributeConfiguratorValue")
    @Expose
    public String attributeConfiguratorValue;
    @SerializedName("attributeConfiguratorName")
    @Expose
    public String attributeConfiguratorName;
    @SerializedName("attributeConfiguratorDescription")
    @Expose
    public String attributeConfiguratorDescription;
    @SerializedName("companyId")
    @Expose
    public Long companyId;
    @SerializedName("locationId")
    @Expose
    public Long locationId;
    @SerializedName("useraccount_id")
    @Expose
    public Long useraccountId;
    @SerializedName("status")
    @Expose
    public Boolean status;
    @SerializedName("attributeDTOList")
    @Expose
    public String attributeDTOList;

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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getAttributeDTOList() {
        return attributeDTOList;
    }

    public void setAttributeDTOList(String attributeDTOList) {
        this.attributeDTOList = attributeDTOList;
    }
}
