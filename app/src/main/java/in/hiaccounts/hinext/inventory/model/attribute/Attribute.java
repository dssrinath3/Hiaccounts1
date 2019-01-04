package in.hiaccounts.hinext.inventory.model.attribute;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by shrinath on 12/8/2017.
 */

public class Attribute implements Serializable {
    @SerializedName("attributeId")
    @Expose
    private Long attributeId;
    @SerializedName("attributeName")
    @Expose
    private String attributeName;
    @SerializedName("attributeConfigurator")
    @Expose
    private String attributeConfigurator;
    @SerializedName("attributeDescription")
    @Expose
    private String attributeDescription;
    @SerializedName("attributeStatus")
    @Expose
    private String attributeStatus;
    @SerializedName("companyId")
    @Expose
    private Long companyId;
    @SerializedName("locationId")
    @Expose
    private Long locationId;
    @SerializedName("useraccount_id")
    @Expose
    private Long useraccountId;
    @SerializedName("attributeConfiguratorId")
    @Expose
    private Long attributeConfiguratorId;
    @SerializedName("status")
    @Expose
    private String status;

    public Long getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Long attributeId) {
        this.attributeId = attributeId;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeConfigurator() {
        return attributeConfigurator;
    }

    public void setAttributeConfigurator(String attributeConfigurator) {
        this.attributeConfigurator = attributeConfigurator;
    }

    public String getAttributeDescription() {
        return attributeDescription;
    }

    public void setAttributeDescription(String attributeDescription) {
        this.attributeDescription = attributeDescription;
    }

    public String getAttributeStatus() {
        return attributeStatus;
    }

    public void setAttributeStatus(String attributeStatus) {
        this.attributeStatus = attributeStatus;
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

    public Long getAttributeConfiguratorId() {
        return attributeConfiguratorId;
    }

    public void setAttributeConfiguratorId(Long attributeConfiguratorId) {
        this.attributeConfiguratorId = attributeConfiguratorId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return attributeConfigurator;
    }
}
