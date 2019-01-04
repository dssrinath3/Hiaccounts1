package in.hiaccounts.hinext.inventory.model.uomconverter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UnitOfMeasurement implements Serializable {
    @SerializedName("companyId")
    @Expose
    public CompanyId companyId;
    @SerializedName("locationId")
    @Expose
    public Object locationId;
    @SerializedName("useraccount_id")
    @Expose
    public Object useraccountId;
    @SerializedName("unitOfMeasurementId")
    @Expose
    public Long unitOfMeasurementId;
    @SerializedName("unitOfMeasurementName")
    @Expose
    public String unitOfMeasurementName;
    @SerializedName("unitOfMeasurementDescription")
    @Expose
    public String unitOfMeasurementDescription;
    @SerializedName("unitOfMeasurementStatus")
    @Expose
    public String unitOfMeasurementStatus;

    public CompanyId getCompanyId() {
        return companyId;
    }

    public void setCompanyId(CompanyId companyId) {
        this.companyId = companyId;
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

    public Long getUnitOfMeasurementId() {
        return unitOfMeasurementId;
    }

    public void setUnitOfMeasurementId(Long unitOfMeasurementId) {
        this.unitOfMeasurementId = unitOfMeasurementId;
    }

    public String getUnitOfMeasurementName() {
        return unitOfMeasurementName;
    }

    public void setUnitOfMeasurementName(String unitOfMeasurementName) {
        this.unitOfMeasurementName = unitOfMeasurementName;
    }

    public String getUnitOfMeasurementDescription() {
        return unitOfMeasurementDescription;
    }

    public void setUnitOfMeasurementDescription(String unitOfMeasurementDescription) {
        this.unitOfMeasurementDescription = unitOfMeasurementDescription;
    }

    public String getUnitOfMeasurementStatus() {
        return unitOfMeasurementStatus;
    }

    public void setUnitOfMeasurementStatus(String unitOfMeasurementStatus) {
        this.unitOfMeasurementStatus = unitOfMeasurementStatus;
    }


    @Override
    public String toString() {
        return unitOfMeasurementName;
    }
}
