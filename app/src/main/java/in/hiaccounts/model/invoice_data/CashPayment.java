
package in.hiaccounts.model.invoice_data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CashPayment implements Serializable {

    @SerializedName("paymentType")
    @Expose
    private Object paymentType;
    @SerializedName("totalCPAmountRefunded")
    @Expose
    private double totalCPAmountRefunded;
    @SerializedName("totalCPDiscount")
    @Expose
    private double totalCPDiscount;
    @SerializedName("totalCPAmountTendered")
    @Expose
    private double totalCPAmountTendered;

    /**
     * No args constructor for use in serialization
     *
     */
    public CashPayment() {
    }

    /**
     *
     * @param totalCPAmountTendered
     * @param paymentType
     * @param totalCPDiscount
     * @param totalCPAmountRefunded
     */
    public CashPayment(Object paymentType, double totalCPAmountRefunded, double totalCPDiscount, double totalCPAmountTendered) {
        super();
        this.paymentType = paymentType;
        this.totalCPAmountRefunded = totalCPAmountRefunded;
        this.totalCPDiscount = totalCPDiscount;
        this.totalCPAmountTendered = totalCPAmountTendered;
    }

    public Object getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Object paymentType) {
        this.paymentType = paymentType;
    }

    public double getTotalCPAmountRefunded() {
        return totalCPAmountRefunded;
    }

    public void setTotalCPAmountRefunded(double totalCPAmountRefunded) {
        this.totalCPAmountRefunded = totalCPAmountRefunded;
    }

    public double getTotalCPDiscount() {
        return totalCPDiscount;
    }

    public void setTotalCPDiscount(double totalCPDiscount) {
        this.totalCPDiscount = totalCPDiscount;
    }

    public double getTotalCPAmountTendered() {
        return totalCPAmountTendered;
    }

    public void setTotalCPAmountTendered(double totalCPAmountTendered) {
        this.totalCPAmountTendered = totalCPAmountTendered;
    }

}
