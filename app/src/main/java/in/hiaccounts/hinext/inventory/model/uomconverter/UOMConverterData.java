package in.hiaccounts.hinext.inventory.model.uomconverter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by shrinath on 12/6/2017.
 */

public class UOMConverterData implements Serializable {
    @SerializedName("uomConvertorId")
    @Expose
    private Long uomConvertorId;
    @SerializedName("unitOfMeasurement")
    @Expose
    private String unitOfMeasurement;
    @SerializedName("uomConvertorEffectiveDate")
    @Expose
    private String uomConvertorEffectiveDate;
    @SerializedName("uomValue")
    @Expose
    private String uomValue;
    @SerializedName("locationId")
    @Expose
    private Long locationId;
    @SerializedName("useraccount_id")
    @Expose
    private Long useraccountId;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("uomConvertedName")
    @Expose
    private String uomConvertedName;
    @SerializedName("unitOfMeasurementId")
    @Expose
    private Long unitOfMeasurementId;

    public Long getUomConvertorId() {
        return uomConvertorId;
    }

    public void setUomConvertorId(Long uomConvertorId) {
        this.uomConvertorId = uomConvertorId;
    }

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public String getUomConvertorEffectiveDate() {
        return uomConvertorEffectiveDate;
    }

    public void setUomConvertorEffectiveDate(String uomConvertorEffectiveDate) {
        this.uomConvertorEffectiveDate = uomConvertorEffectiveDate;
    }

    public String getUomValue() {
        return uomValue;
    }

    public void setUomValue(String uomValue) {
        this.uomValue = uomValue;
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

    public String getUomConvertedName() {
        return uomConvertedName;
    }

    public void setUomConvertedName(String uomConvertedName) {
        this.uomConvertedName = uomConvertedName;
    }

    public Long getUnitOfMeasurementId() {
        return unitOfMeasurementId;
    }

    public void setUnitOfMeasurementId(Long unitOfMeasurementId) {
        this.unitOfMeasurementId = unitOfMeasurementId;
    }
}
