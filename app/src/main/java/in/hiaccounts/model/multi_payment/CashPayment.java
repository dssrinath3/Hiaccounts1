
package in.hiaccounts.model.multi_payment;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CashPayment implements Serializable
{

    @SerializedName("totalCPAmountTendered")
    @Expose
    private double totalCPAmountTendered;

    @SerializedName("totalCPAmountRefunded")
    @Expose
    private Double totalCPAmountRefunded;


    @SerializedName("totalCPDiscount")
    @Expose
    private Double totalCPDiscount;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CashPayment() {
    }

    /**
     * 
     * @param totalCPAmountTendered
     */
    public CashPayment(double totalCPAmountTendered) {
        super();
        this.totalCPAmountTendered = totalCPAmountTendered;
    }

    public CashPayment(double totalCPAmountTendered, Double totalCPAmountRefunded, Double totalCPDiscount) {
        this.totalCPAmountTendered = totalCPAmountTendered;
        this.totalCPAmountRefunded = totalCPAmountRefunded;
        this.totalCPDiscount = totalCPDiscount;
    }

    public double getTotalCPAmountTendered() {
        return totalCPAmountTendered;
    }

    public void setTotalCPAmountTendered(double totalCPAmountTendered) {
        this.totalCPAmountTendered = totalCPAmountTendered;
    }

    public Double getTotalCPAmountRefunded() {
        return totalCPAmountRefunded;
    }

    public void setTotalCPAmountRefunded(Double totalCPAmountRefunded) {
        this.totalCPAmountRefunded = totalCPAmountRefunded;
    }

    public Double getTotalCPDiscount() {
        return totalCPDiscount;
    }

    public void setTotalCPDiscount(Double totalCPDiscount) {
        this.totalCPDiscount = totalCPDiscount;
    }
}
