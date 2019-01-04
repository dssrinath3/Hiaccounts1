package in.hiaccounts.hinext.inventory.model.inventorylocationtransfer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by administrator on 27/1/18.
 */

public class InventoryLocationTransfer implements Serializable{

    @SerializedName("itemName")
    @Expose
    public String itemName;
    @SerializedName("itemCode")
    @Expose
    public String itemCode;
    @SerializedName("fromLocationName")
    @Expose
    public String fromLocationName;
    @SerializedName("toLocationName")
    @Expose
    public String toLocationName;
    @SerializedName("date")
    @Expose
    public String date;
    @SerializedName("quantity")
    @Expose
    public Double quantity;
    @SerializedName("memo")
    @Expose
    public String memo;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getFromLocationName() {
        return fromLocationName;
    }

    public void setFromLocationName(String fromLocationName) {
        this.fromLocationName = fromLocationName;
    }

    public String getToLocationName() {
        return toLocationName;
    }

    public void setToLocationName(String toLocationName) {
        this.toLocationName = toLocationName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
