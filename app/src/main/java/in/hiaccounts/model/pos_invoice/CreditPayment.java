
package in.hiaccounts.model.pos_invoice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CreditPayment implements Serializable {
    @SerializedName("transactionNo")
    @Expose
    private String transactionNo;
    @SerializedName("totalCCPAmountTendered")
    @Expose
    private double totalCCPAmountTendered;
    @SerializedName("totalCCPDiscount")
    @Expose
    private double totalCCPDiscount;
    @SerializedName("totalCCPAmountRefunded")
    @Expose
    private double totalCCPAmountRefunded;

    /**
     * No args constructor for use in serialization
     */
    public CreditPayment() {
    }

    /**
     * @param totalCCPAmountRefunded
     * @param transactionNo
     * @param totalCCPDiscount
     * @param totalCCPAmountTendered
     */
    public CreditPayment(String transactionNo, double totalCCPAmountTendered, double totalCCPDiscount, double totalCCPAmountRefunded) {
        super();
        this.transactionNo = transactionNo;
        this.totalCCPAmountTendered = totalCCPAmountTendered;
        this.totalCCPDiscount = totalCCPDiscount;
        this.totalCCPAmountRefunded = totalCCPAmountRefunded;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

    public double getTotalCCPAmountTendered() {
        return totalCCPAmountTendered;
    }

    public void setTotalCCPAmountTendered(double totalCCPAmountTendered) {
        this.totalCCPAmountTendered = totalCCPAmountTendered;
    }

    public double getTotalCCPDiscount() {
        return totalCCPDiscount;
    }

    public void setTotalCCPDiscount(double totalCCPDiscount) {
        this.totalCCPDiscount = totalCCPDiscount;
    }

    public double getTotalCCPAmountRefunded() {
        return totalCCPAmountRefunded;
    }

    public void setTotalCCPAmountRefunded(double totalCCPAmountRefunded) {
        this.totalCCPAmountRefunded = totalCCPAmountRefunded;
    }
}
