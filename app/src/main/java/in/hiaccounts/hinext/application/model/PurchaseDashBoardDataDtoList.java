package in.hiaccounts.hinext.application.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Prateek on 8/29/2017.
 */

public class PurchaseDashBoardDataDtoList implements Serializable{
    @SerializedName("purchaseDate")
    @Expose
    public String purchaseDate;
    @SerializedName("totalPayable")
    @Expose
    public double totalPayable;
    @SerializedName("apBalance")
    @Expose
    public double apBalance;

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public double getTotalPayable() {
        return totalPayable;
    }

    public void setTotalPayable(double totalPayable) {
        this.totalPayable = totalPayable;
    }

    public double getApBalance() {
        return apBalance;
    }

    public void setApBalance(double apBalance) {
        this.apBalance = apBalance;
    }
}
