
package in.hiaccounts.model.duplicate_invoicedata;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CashPayment implements Serializable
{

    @SerializedName("totalCPAmountRefunded")
    @Expose
    private double totalCPAmountRefunded;
    @SerializedName("totalCPAmountTendered")
    @Expose
    private double totalCPAmountTendered;
    @SerializedName("totalCPDiscount")
    @Expose
    private double totalCPDiscount;
    @SerializedName("paymentType")
    @Expose
    private String paymentType;
    private final static long serialVersionUID = 5934820363302604388L;

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
    public CashPayment(double totalCPAmountRefunded, double totalCPAmountTendered, double totalCPDiscount, String paymentType) {
        super();
        this.totalCPAmountRefunded = totalCPAmountRefunded;
        this.totalCPAmountTendered = totalCPAmountTendered;
        this.totalCPDiscount = totalCPDiscount;
        this.paymentType = paymentType;
    }

    public double getTotalCPAmountRefunded() {
        return totalCPAmountRefunded;
    }

    public void setTotalCPAmountRefunded(double totalCPAmountRefunded) {
        this.totalCPAmountRefunded = totalCPAmountRefunded;
    }

    public double getTotalCPAmountTendered() {
        return totalCPAmountTendered;
    }

    public void setTotalCPAmountTendered(double totalCPAmountTendered) {
        this.totalCPAmountTendered = totalCPAmountTendered;
    }

    public double getTotalCPDiscount() {
        return totalCPDiscount;
    }

    public void setTotalCPDiscount(double totalCPDiscount) {
        this.totalCPDiscount = totalCPDiscount;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String  paymentType) {
        this.paymentType = paymentType;
    }

}
