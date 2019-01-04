package in.hiaccounts.hinext.restaurant.model.restaurant_pagedata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GetTempDataDeatails implements Serializable{
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
    @SerializedName("orderNo")
    @Expose
    public Object orderNo;
    @SerializedName("agentId")
    @Expose
    public String agentId;
    @SerializedName("useraccount_id")
    @Expose
    public String useraccountId;
    @SerializedName("removedItemsList")
    @Expose
    public Object removedItemsList;
    @SerializedName("customerBill")
    @Expose
    public Boolean customerBill;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public Object getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Object orderNo) {
        this.orderNo = orderNo;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getUseraccountId() {
        return useraccountId;
    }

    public void setUseraccountId(String useraccountId) {
        this.useraccountId = useraccountId;
    }

    public Object getRemovedItemsList() {
        return removedItemsList;
    }

    public void setRemovedItemsList(Object removedItemsList) {
        this.removedItemsList = removedItemsList;
    }

    public Boolean getCustomerBill() {
        return customerBill;
    }

    public void setCustomerBill(Boolean customerBill) {
        this.customerBill = customerBill;
    }
}
