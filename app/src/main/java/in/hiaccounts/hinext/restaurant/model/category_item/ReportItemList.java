package in.hiaccounts.hinext.restaurant.model.category_item;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ReportItemList implements Serializable {
    @SerializedName("itemId")
    @Expose
    public Long itemId;
    @SerializedName("QtySold")
    @Expose
    public Double qtySold;
    @SerializedName("itemName")
    @Expose
    public String itemName;
    @SerializedName("totalAmtReceived")
    @Expose
    public Double totalAmtReceived;
    @SerializedName("itemCategoryName")
    @Expose
    public String itemCategoryName;
    @SerializedName("Rounding Off")
    @Expose
    public Double roundingOff;
    @SerializedName("Discount")
    @Expose
    public Double discount;

    public Double getRoundingOff() {
        return roundingOff;
    }

    public void setRoundingOff(Double roundingOff) {
        this.roundingOff = roundingOff;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Double getQtySold() {
        return qtySold;
    }

    public void setQtySold(Double qtySold) {
        this.qtySold = qtySold;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getTotalAmtReceived() {
        return totalAmtReceived;
    }

    public void setTotalAmtReceived(Double totalAmtReceived) {
        this.totalAmtReceived = totalAmtReceived;
    }

    public String getItemCategoryName() {
        return itemCategoryName;
    }

    public void setItemCategoryName(String itemCategoryName) {
        this.itemCategoryName = itemCategoryName;
    }
}
