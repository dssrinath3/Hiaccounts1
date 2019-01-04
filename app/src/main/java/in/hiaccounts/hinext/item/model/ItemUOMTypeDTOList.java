package in.hiaccounts.hinext.item.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Prateek on 7/25/2017.
 */

public class ItemUOMTypeDTOList implements Serializable{
    @SerializedName("unitOfMeasurementId")
    @Expose
    private long unitOfMeasurementId;
    @SerializedName("unitOfMeasurementName")
    @Expose
    private String unitOfMeasurementName;
    @SerializedName("unitOfMeasurementDescription")
    @Expose
    private String unitOfMeasurementDescription;
    @SerializedName("unitOfMeasurementStatus")
    @Expose
    private Object unitOfMeasurementStatus;

    public long getUnitOfMeasurementId() {
        return unitOfMeasurementId;
    }

    public void setUnitOfMeasurementId(long unitOfMeasurementId) {
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

    public Object getUnitOfMeasurementStatus() {
        return unitOfMeasurementStatus;
    }

    public void setUnitOfMeasurementStatus(Object unitOfMeasurementStatus) {
        this.unitOfMeasurementStatus = unitOfMeasurementStatus;
    }


    @Override
    public String toString() {
        return unitOfMeasurementName;
    }
}
