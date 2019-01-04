
package in.hiaccounts.model.item;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemUOMTypeDTOList implements Serializable
{

    @SerializedName("unitOfMeasurementId")
    @Expose
    private long unitOfMeasurementId;
    @SerializedName("unitOfMeasurementDescription")
    @Expose
    private String unitOfMeasurementDescription;
    @SerializedName("unitOfMeasurementStatus")
    @Expose
    private String unitOfMeasurementStatus;
    @SerializedName("unitOfMeasurementName")
    @Expose
    private String unitOfMeasurementName;
    private final static long serialVersionUID = -5914382095549841126L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ItemUOMTypeDTOList() {
    }

    /**
     * 
     * @param unitOfMeasurementName
     * @param unitOfMeasurementId
     * @param unitOfMeasurementStatus
     * @param unitOfMeasurementDescription
     */
    public ItemUOMTypeDTOList(long unitOfMeasurementId, String unitOfMeasurementDescription, String unitOfMeasurementStatus, String unitOfMeasurementName) {
        super();
        this.unitOfMeasurementId = unitOfMeasurementId;
        this.unitOfMeasurementDescription = unitOfMeasurementDescription;
        this.unitOfMeasurementStatus = unitOfMeasurementStatus;
        this.unitOfMeasurementName = unitOfMeasurementName;
    }

    public long getUnitOfMeasurementId() {
        return unitOfMeasurementId;
    }

    public void setUnitOfMeasurementId(long unitOfMeasurementId) {
        this.unitOfMeasurementId = unitOfMeasurementId;
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

    public String getUnitOfMeasurementName() {
        return unitOfMeasurementName;
    }

    public void setUnitOfMeasurementName(String unitOfMeasurementName) {
        this.unitOfMeasurementName = unitOfMeasurementName;
    }


    @Override
    public String toString() {
        return unitOfMeasurementName;
    }

}
