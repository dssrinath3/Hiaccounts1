
package in.hiaccounts.hinext.generaltransaction.model.checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CashPayment {

        @SerializedName("totalCPAmountTendered")
    @Expose
    private double totalCPAmountTendered;

    @SerializedName("totalCPAmountRefunded")
    @Expose
    private String totalCPAmountRefunded;

    @SerializedName("totalCPDiscount")
    @Expose
    private double totalCPDiscount;


    public double getTotalCPAmountTendered() {
        return totalCPAmountTendered;
    }

    public void setTotalCPAmountTendered(double totalCPAmountTendered) {
        this.totalCPAmountTendered = totalCPAmountTendered;
    }

    public String getTotalCPAmountRefunded() {
        return totalCPAmountRefunded;
    }

    public void setTotalCPAmountRefunded(String totalCPAmountRefunded) {
        this.totalCPAmountRefunded = totalCPAmountRefunded;
    }

    public double getTotalCPDiscount() {
        return totalCPDiscount;
    }

    public void setTotalCPDiscount(double totalCPDiscount) {
        this.totalCPDiscount = totalCPDiscount;
    }
}
