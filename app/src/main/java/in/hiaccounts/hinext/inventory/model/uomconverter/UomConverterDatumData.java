package in.hiaccounts.hinext.inventory.model.uomconverter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UomConverterDatumData implements Serializable {
    @SerializedName("uomConvertorId")
    @Expose
    public Long uomConvertorId;
    @SerializedName("unitOfMeasurement")
    @Expose
    public UnitOfMeasurement unitOfMeasurement;
    @SerializedName("uomConvertorEffectiveDate")
    @Expose
    public Object uomConvertorEffectiveDate;
    @SerializedName("uomValue")
    @Expose
    public String uomValue;
    @SerializedName("uomConvertedName")
    @Expose
    public String uomConvertedName;
    @SerializedName("uomConvertedNameDescription")
    @Expose
    public Object uomConvertedNameDescription;
    @SerializedName("companyId")
    @Expose
    public Object companyId;
    @SerializedName("locationId")
    @Expose
    public Object locationId;
    @SerializedName("useraccount_id")
    @Expose
    public Object useraccountId;
    @SerializedName("status")
    @Expose
    public String status;

    public Long getUomConvertorId() {
        return uomConvertorId;
    }

    public void setUomConvertorId(Long uomConvertorId) {
        this.uomConvertorId = uomConvertorId;
    }

    public UnitOfMeasurement getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(UnitOfMeasurement unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public Object getUomConvertorEffectiveDate() {
        return uomConvertorEffectiveDate;
    }

    public void setUomConvertorEffectiveDate(Object uomConvertorEffectiveDate) {
        this.uomConvertorEffectiveDate = uomConvertorEffectiveDate;
    }

    public String getUomValue() {
        return uomValue;
    }

    public void setUomValue(String uomValue) {
        this.uomValue = uomValue;
    }

    public String getUomConvertedName() {
        return uomConvertedName;
    }

    public void setUomConvertedName(String uomConvertedName) {
        this.uomConvertedName = uomConvertedName;
    }

    public Object getUomConvertedNameDescription() {
        return uomConvertedNameDescription;
    }

    public void setUomConvertedNameDescription(Object uomConvertedNameDescription) {
        this.uomConvertedNameDescription = uomConvertedNameDescription;
    }

    public Object getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Object companyId) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return uomConvertedName;
    }
}
