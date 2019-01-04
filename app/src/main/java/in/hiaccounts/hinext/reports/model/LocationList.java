package in.hiaccounts.hinext.reports.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocationList {
    @SerializedName("inventoryLocationName")
    @Expose
    public String inventoryLocationName;
    @SerializedName("inventoryLocationId")
    @Expose
    public Long inventoryLocationId;

    public String getInventoryLocationName() {
        return inventoryLocationName;
    }

    public void setInventoryLocationName(String inventoryLocationName) {
        this.inventoryLocationName = inventoryLocationName;
    }

    public Long getInventoryLocationId() {
        return inventoryLocationId;
    }

    public void setInventoryLocationId(Long inventoryLocationId) {
        this.inventoryLocationId = inventoryLocationId;
    }

    @Override
    public String toString() {
        return inventoryLocationName;
    }
}
