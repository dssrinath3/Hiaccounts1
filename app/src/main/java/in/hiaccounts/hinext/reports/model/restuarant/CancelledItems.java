package in.hiaccounts.hinext.reports.model.restuarant;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CancelledItems implements Serializable {
    @SerializedName("itemId")
    @Expose
    public Object itemId;
    @SerializedName("itemName")
    @Expose
    public String itemName;
    @SerializedName("price")
    @Expose
    public Object price;
    @SerializedName("qty")
    @Expose
    public String qty;
    @SerializedName("quantity")
    @Expose
    public Double quantity;
    @SerializedName("type")
    @Expose
    public Object type;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("tableName")
    @Expose
    public String tableName;
    @SerializedName("waiterName")
    @Expose
    public String waiterName;
    @SerializedName("tokenNo")
    @Expose
    public String tokenNo;
    @SerializedName("date")
    @Expose
    public Long date;
    @SerializedName("itemDeteails")
    @Expose
    public Object itemDeteails;

    public Object getItemId() {
        return itemId;
    }

    public void setItemId(Object itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Object getPrice() {
        return price;
    }

    public void setPrice(Object price) {
        this.price = price;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getWaiterName() {
        return waiterName;
    }

    public void setWaiterName(String waiterName) {
        this.waiterName = waiterName;
    }

    public String getTokenNo() {
        return tokenNo;
    }

    public void setTokenNo(String tokenNo) {
        this.tokenNo = tokenNo;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Object getItemDeteails() {
        return itemDeteails;
    }

    public void setItemDeteails(Object itemDeteails) {
        this.itemDeteails = itemDeteails;
    }
}
