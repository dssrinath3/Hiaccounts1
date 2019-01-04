package in.hiaccounts.hinext.inventory.model.counttype;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Prateek on 8/1/2017.
 */

public class InventoryCountType {
    @SerializedName("inventoryMovementId")
    @Expose
    private long inventoryMovementId;
    @SerializedName("inventoryMovementStatus")
    @Expose
    private String inventoryMovementStatus;
    @SerializedName("companyId")
    @Expose
    private long companyId;
    @SerializedName("inventoryMovementName")
    @Expose
    private String inventoryMovementName;
    @SerializedName("inventoryMovementDesc")
    @Expose
    private String inventoryMovementDesc;
    @SerializedName("useraccount_id")
    @Expose
    private long useraccountId;

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

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
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

    public long getUseraccountId() {
        return useraccountId;
    }

    public void setUseraccountId(long useraccountId) {
        this.useraccountId = useraccountId;
    }
}
