package in.hiaccounts.hinext.application.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Prateek on 8/29/2017.
 */

public class SalesDashBoardDataDtoList implements Serializable {

    @SerializedName("salesDate")
    @Expose
    public String salesDate;
    @SerializedName("totalRecievable")
    @Expose
    public double totalRecievable;
    @SerializedName("arBalance")
    @Expose
    public double arBalance;

    public String getSalesDate() {
        return salesDate;
    }

    public void setSalesDate(String salesDate) {
        this.salesDate = salesDate;
    }

    public double getTotalRecievable() {
        return totalRecievable;
    }

    public void setTotalRecievable(double totalRecievable) {
        this.totalRecievable = totalRecievable;
    }

    public double getArBalance() {
        return arBalance;
    }

    public void setArBalance(double arBalance) {
        this.arBalance = arBalance;
    }
}
