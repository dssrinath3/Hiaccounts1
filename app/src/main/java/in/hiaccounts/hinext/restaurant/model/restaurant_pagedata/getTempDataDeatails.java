package in.hiaccounts.hinext.restaurant.model.restaurant_pagedata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class getTempDataDeatails implements Serializable {
    @SerializedName("id")
    @Expose
    public Long id;
    @SerializedName("tableName")
    @Expose
    public String tableName;
    @SerializedName("tableId")
    @Expose
    public String tableId;
    @SerializedName("selectedItemsList")
    @Expose
    public String selectedItemsList;
    @SerializedName("customerId")
    @Expose
    public Long customerId;
    @SerializedName("locationId")
    @Expose
    public String locationId;
    @SerializedName("useraccount_id")
    @Expose
    public Object useraccountId;
    @SerializedName("removedItemsList")
    @Expose
    public Object removedItemsList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getSelectedItemsList() {
        return selectedItemsList;
    }

    public void setSelectedItemsList(String selectedItemsList) {
        this.selectedItemsList = selectedItemsList;
    }



    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public Object getUseraccountId() {
        return useraccountId;
    }

    public void setUseraccountId(Object useraccountId) {
        this.useraccountId = useraccountId;
    }

    public Object getRemovedItemsList() {
        return removedItemsList;
    }

    public void setRemovedItemsList(Object removedItemsList) {
        this.removedItemsList = removedItemsList;
    }
}
