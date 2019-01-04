package in.hiaccounts.hinext.inventory.model.assests;

/**
 * Created by shrinath on 11/29/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemUOMTypeDTOList {

    @SerializedName("unitOfMeasurementName")
    @Expose
    private String unitOfMeasurementName;
    @SerializedName("unitOfMeasurementDescription")
    @Expose
    private String unitOfMeasurementDescription;
    @SerializedName("unitOfMeasurementStatus")
    @Expose
    private String unitOfMeasurementStatus;
    @SerializedName("unitOfMeasurementId")
    @Expose
    private Long unitOfMeasurementId;


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

    public Long getUnitOfMeasurementId() {
        return unitOfMeasurementId;
    }

    public void setUnitOfMeasurementId(Long unitOfMeasurementId) {
        this.unitOfMeasurementId = unitOfMeasurementId;
    }

    @Override
    public String toString() {
        return unitOfMeasurementName;
    }
}
