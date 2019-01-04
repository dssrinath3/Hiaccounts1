package in.hiaccounts.hinext.inventory.model.inventorylocationtransfer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by administrator on 27/1/18.
 */

public class MoveList implements Serializable {
    @SerializedName("inventoryMovementId")
    @Expose
    public Long inventoryMovementId;
    @SerializedName("inventoryMovementStatus")
    @Expose
    public String inventoryMovementStatus;
    @SerializedName("companyId")
    @Expose
    public CompanyId companyId;
    @SerializedName("inventoryMovementName")
    @Expose
    public String inventoryMovementName;
    @SerializedName("inventoryMovementDesc")
    @Expose
    public String inventoryMovementDesc;
    @SerializedName("useraccount_id")
    @Expose
    public String useraccountId;

    public Long getInventoryMovementId() {
        return inventoryMovementId;
    }

    public void setInventoryMovementId(Long inventoryMovementId) {
        this.inventoryMovementId = inventoryMovementId;
    }

    public String getInventoryMovementStatus() {
        return inventoryMovementStatus;
    }

    public void setInventoryMovementStatus(String inventoryMovementStatus) {
        this.inventoryMovementStatus = inventoryMovementStatus;
    }

    public CompanyId getCompanyId() {
        return companyId;
    }

    public void setCompanyId(CompanyId companyId) {
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

    public String getUseraccountId() {
        return useraccountId;
    }

    public void setUseraccountId(String useraccountId) {
        this.useraccountId = useraccountId;
    }

    @Override
    public String toString() {
        return  inventoryMovementName;
    }


}
