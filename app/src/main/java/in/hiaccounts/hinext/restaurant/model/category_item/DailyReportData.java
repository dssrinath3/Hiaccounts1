package in.hiaccounts.hinext.restaurant.model.category_item;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class DailyReportData implements Serializable {
    @SerializedName("Payment")
    @Expose
    public Payment payment;
    @SerializedName("Discount")
    @Expose
    public Double discount;
    @SerializedName("ItemList")
    @Expose
    public List<ReportItemList> itemList = null;
    @SerializedName("Total Amount")
    @Expose
    public Double totalAmount;
    @SerializedName("Rounding Off")
    @Expose
    public Double roundingOff;

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public List<ReportItemList> getItemList() {
        return itemList;
    }

    public void setItemList(List<ReportItemList> itemList) {
        this.itemList = itemList;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getRoundingOff() {
        return roundingOff;
    }

    public void setRoundingOff(Double roundingOff) {
        this.roundingOff = roundingOff;
    }
}
