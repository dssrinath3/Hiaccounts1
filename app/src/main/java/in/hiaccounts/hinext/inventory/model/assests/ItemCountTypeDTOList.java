package in.hiaccounts.hinext.inventory.model.assests;

/**
 * Created by shrinath on 11/29/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemCountTypeDTOList {

    @SerializedName("inventoryMovementName")
    @Expose
    private String inventoryMovementName;
    @SerializedName("inventoryMovementId")
    @Expose
    private Long inventoryMovementId;
    @SerializedName("inventoryMovementDesc")
    @Expose
    private String inventoryMovementDesc;
    @SerializedName("inventoryMovementStatus")
    @Expose
    private String inventoryMovementStatus;

    public String getInventoryMovementName() {
        return inventoryMovementName;
    }

    public void setInventoryMovementName(String inventoryMovementName) {
        this.inventoryMovementName = inventoryMovementName;
    }

    public Long getInventoryMovementId() {
        return inventoryMovementId;
    }

    public void setInventoryMovementId(Long inventoryMovementId) {
        this.inventoryMovementId = inventoryMovementId;
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

