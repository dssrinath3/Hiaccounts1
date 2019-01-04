
package in.hiaccounts.model.item;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemCountTypeDTOList implements Serializable
{

    @SerializedName("inventoryMovementName")
    @Expose
    private String inventoryMovementName;
    @SerializedName("inventoryMovementId")
    @Expose
    private long inventoryMovementId;
    @SerializedName("inventoryMovementStatus")
    @Expose
    private String inventoryMovementStatus;
    @SerializedName("inventoryMovementDesc")
    @Expose
    private String inventoryMovementDesc;
    private final static long serialVersionUID = 2373790774105113761L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ItemCountTypeDTOList() {
    }

    /**
     * 
     * @param inventoryMovementName
     * @param inventoryMovementDesc
     * @param inventoryMovementStatus
     * @param inventoryMovementId
     */
    public ItemCountTypeDTOList(String inventoryMovementName, long inventoryMovementId, String inventoryMovementStatus, String inventoryMovementDesc) {
        super();
        this.inventoryMovementName = inventoryMovementName;
        this.inventoryMovementId = inventoryMovementId;
        this.inventoryMovementStatus = inventoryMovementStatus;
        this.inventoryMovementDesc = inventoryMovementDesc;
    }

    public String getInventoryMovementName() {
        return inventoryMovementName;
    }

    public void setInventoryMovementName(String inventoryMovementName) {
        this.inventoryMovementName = inventoryMovementName;
    }

    public long getInventoryMovementId() {
        return inventoryMovementId;
    }

    public void setInventoryMovementId(long inventoryMovementId) {
        this.inventoryMovementId = inventoryMovementId;
    }

    public String getInventoryMovementStatus() {
        return inventoryMovementStatus;
    }

    public void setInventoryMovementStatus(String inventoryMovementStatus) {
        this.inventoryMovementStatus = inventoryMovementStatus;
    }

    public String getInventoryMovementDesc() {
        return inventoryMovementDesc;
    }

    public void setInventoryMovementDesc(String inventoryMovementDesc) {
        this.inventoryMovementDesc = inventoryMovementDesc;
    }

    @Override
    public String toString() {
        return inventoryMovementName;
    }

}
