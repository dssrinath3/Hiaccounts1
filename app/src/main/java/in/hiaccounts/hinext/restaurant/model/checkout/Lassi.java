package in.hiaccounts.hinext.restaurant.model.checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by administrator on 16/2/18.
 */

public class Lassi implements Serializable {
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
    @SerializedName("total")
    @Expose
    public String total;


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

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
