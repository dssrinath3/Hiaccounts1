package in.hiaccounts.hinext.application.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Prateek on 8/29/2017.
 */

public class DashBoardData implements Serializable {
    @SerializedName("cashInHand")
    @Expose
    public double cashInHand;
    @SerializedName("cashInBank")
    @Expose
    public double cashInBank;
    @SerializedName("totalRecievable")
    @Expose
    public double totalRecievable;
    @SerializedName("totalPayable")
    @Expose
    public double totalPayable;
    @SerializedName("purchaseDashBoardDataDtoList")
    @Expose
    public List<PurchaseDashBoardDataDtoList> purchaseDashBoardDataDtoList = null;
    @SerializedName("salesDashBoardDataDtoList")
    @Expose
    public List<SalesDashBoardDataDtoList> salesDashBoardDataDtoList = null;

    public double getCashInHand() {
        return cashInHand;
    }

    public void setCashInHand(double cashInHand) {
        this.cashInHand = cashInHand;
    }

    public double getCashInBank() {
        return cashInBank;
    }

    public void setCashInBank(double cashInBank) {
        this.cashInBank = cashInBank;
    }

    public double getTotalRecievable() {
        return totalRecievable;
    }

    public void setTotalRecievable(double totalRecievable) {
        this.totalRecievable = totalRecievable;
    }

    public double getTotalPayable() {
        return totalPayable;
    }

    public void setTotalPayable(double totalPayable) {
        this.totalPayable = totalPayable;
    }

    public List<PurchaseDashBoardDataDtoList> getPurchaseDashBoardDataDtoList() {
        return purchaseDashBoardDataDtoList;
    }

    public void setPurchaseDashBoardDataDtoList(List<PurchaseDashBoardDataDtoList> purchaseDashBoardDataDtoList) {
        this.purchaseDashBoardDataDtoList = purchaseDashBoardDataDtoList;
    }

    public List<SalesDashBoardDataDtoList> getSalesDashBoardDataDtoList() {
        return salesDashBoardDataDtoList;
    }

    public void setSalesDashBoardDataDtoList(List<SalesDashBoardDataDtoList> salesDashBoardDataDtoList) {
        this.salesDashBoardDataDtoList = salesDashBoardDataDtoList;
    }
}
