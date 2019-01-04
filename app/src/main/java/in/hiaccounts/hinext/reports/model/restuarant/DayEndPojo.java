package in.hiaccounts.hinext.reports.model.restuarant;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DayEndPojo implements Serializable {
    @SerializedName("amount")
    @Expose
    public Double amount;
    @SerializedName("totalItems")
    @Expose
    public Double totalItems;
    @SerializedName("date")
    @Expose
    public Long date;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Double totalItems) {
        this.totalItems = totalItems;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }
}
