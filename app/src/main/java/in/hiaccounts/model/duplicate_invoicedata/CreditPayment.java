
package in.hiaccounts.model.duplicate_invoicedata;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreditPayment implements Serializable
{

    @SerializedName("totalCCPAfterDiscount")
    @Expose
    private double totalCCPAfterDiscount;
    @SerializedName("totalCCPAmountRefunded")
    @Expose
    private double totalCCPAmountRefunded;
    @SerializedName("totalCCPAmountTendered")
    @Expose
    private double totalCCPAmountTendered;
    @SerializedName("cardPaymentList")
    @Expose
    private List<CardPaymentList> cardPaymentList = null;
    @SerializedName("totalCCPDiscount")
    @Expose
    private double totalCCPDiscount;
    @SerializedName("transactionNo")
    @Expose
    private String transactionNo;
    private final static long serialVersionUID = 7726339208554654553L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CreditPayment() {
    }

    /**
     * 
     * @param totalCCPAfterDiscount
     * @param totalCCPAmountRefunded
     * @param transactionNo
     * @param cardPaymentList
     * @param totalCCPDiscount
     * @param totalCCPAmountTendered
     */
    public CreditPayment(double totalCCPAfterDiscount, double totalCCPAmountRefunded, double totalCCPAmountTendered, List<CardPaymentList> cardPaymentList, double totalCCPDiscount, String transactionNo) {
        this.totalCCPAfterDiscount = totalCCPAfterDiscount;
        this.totalCCPAmountRefunded = totalCCPAmountRefunded;
        this.totalCCPAmountTendered = totalCCPAmountTendered;
        this.cardPaymentList = cardPaymentList;
        this.totalCCPDiscount = totalCCPDiscount;
        this.transactionNo = transactionNo;
    }

    public double getTotalCCPAfterDiscount() {
        return totalCCPAfterDiscount;
    }

    public void setTotalCCPAfterDiscount(double totalCCPAfterDiscount) {
        this.totalCCPAfterDiscount = totalCCPAfterDiscount;
    }

    public double getTotalCCPAmountRefunded() {
        return totalCCPAmountRefunded;
    }

    public void setTotalCCPAmountRefunded(double totalCCPAmountRefunded) {
        this.totalCCPAmountRefunded = totalCCPAmountRefunded;
    }

    public double getTotalCCPAmountTendered() {
        return totalCCPAmountTendered;
    }

    public void setTotalCCPAmountTendered(double totalCCPAmountTendered) {
        this.totalCCPAmountTendered = totalCCPAmountTendered;
    }

    public List<CardPaymentList> getCardPaymentList() {
        return cardPaymentList;
    }

    public void setCardPaymentList(List<CardPaymentList> cardPaymentList) {
        this.cardPaymentList = cardPaymentList;
    }

    public double getTotalCCPDiscount() {
        return totalCCPDiscount;
    }

    public void setTotalCCPDiscount(double totalCCPDiscount) {
        this.totalCCPDiscount = totalCCPDiscount;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }
}
