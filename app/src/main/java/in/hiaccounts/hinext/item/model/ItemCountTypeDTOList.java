package in.hiaccounts.hinext.item.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Prateek on 7/25/2017.
 */

public class ItemCountTypeDTOList implements Serializable {

    @SerializedName("inventoryMovementId")
    @Expose
    private long inventoryMovementId;
    @SerializedName("inventoryMovementName")
    @Expose
    private String inventoryMovementName;
    @SerializedName("inventoryMovementDesc")
    @Expose
    private String inventoryMovementDesc;
    @SerializedName("inventoryMovementStatus")
    @Expose
    private String inventoryMovementStatus;


    public long getInventoryMovementId() {
        return inventoryMovementId;
    }

    public void setInventoryMovementId(long inventoryMovementId) {
        this.inventoryMovementId = inventoryMovementId;
    }

    public String getInventoryMovementName() {
        return inventoryMovementName;
    }

    public void setInventoryMovementName(String inventoryMovementName) {
        this.inventoryMovementName = inventoryMovementName;
    }

    public String getInventoryMovementDesc() {
        return inventoryMovementDesc;
    }

    public void setInventoryMovementDesc(String inventoryMovementDesc) {
        this.inventoryMovementDesc = inventoryMovementDesc;
    }

    public String getInventoryMovementStatus() {
        return inventoryMovementStatus;
    }

    public void setInventoryMovementStatus(String inventoryMovementStatus) {
        this.inventoryMovementStatus = inventoryMovementStatus;
    }

    @Override
    public String toString() {
        return inventoryMovementName;
    }
}
