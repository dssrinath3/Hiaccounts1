package in.hiaccounts.hinext.inventory.model.uom;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Prateek on 8/1/2017.
 */

public class UOM {
    @SerializedName("unitOfMeasurementId")
    @Expose
    private Long unitofmeasurementid;


    @SerializedName("unitOfMeasurementName")
    @Expose
    private String unitofmeasurementname;
    @SerializedName("unitOfMeasurementDescription")
    @Expose
    private String unitOfMeasurementDescription;
    @SerializedName("unitOfMeasurementStatus")
    @Expose
    private String unitOfMeasurementStatus;

    public String getUnitofmeasurementname() {
        return unitofmeasurementname;
    }

    public void setUnitofmeasurementname(String unitofmeasurementname) {
        this.unitofmeasurementname = unitofmeasurementname;
    }

    public Long getUnitofmeasurementid() {
        return unitofmeasurementid;
    }

    public void setUnitofmeasurementid(Long unitofmeasurementid) {
        this.unitofmeasurementid = unitofmeasurementid;
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
}
