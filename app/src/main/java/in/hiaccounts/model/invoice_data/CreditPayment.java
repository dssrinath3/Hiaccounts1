
package in.hiaccounts.model.invoice_data;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreditPayment implements Serializable {
    @SerializedName("transactionNo")
    @Expose
    private Object transactionNo;
    @SerializedName("totalCCPAmountRefunded")
    @Expose
    private double totalCCPAmountRefunded;
    @SerializedName("cardPaymentList")
    @Expose
    private List<CardPaymentList> cardPaymentList = null;
    @SerializedName("totalCCPDiscount")
    @Expose
    private double totalCCPDiscount;
    @SerializedName("totalCCPAfterDiscount")
    @Expose
    private Object totalCCPAfterDiscount;
    @SerializedName("totalCCPAmountTendered")
    @Expose
    private double totalCCPAmountTendered;

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
     * @param totalCCPAmountTendered
     * @param totalCCPDiscount
     */
    public CreditPayment(Object transactionNo, double totalCCPAmountRefunded, List<CardPaymentList> cardPaymentList, double totalCCPDiscount, Object totalCCPAfterDiscount, double totalCCPAmountTendered) {
        super();
        this.transactionNo = transactionNo;
        this.totalCCPAmountRefunded = totalCCPAmountRefunded;
        this.cardPaymentList = cardPaymentList;
        this.totalCCPDiscount = totalCCPDiscount;
        this.totalCCPAfterDiscount = totalCCPAfterDiscount;
        this.totalCCPAmountTendered = totalCCPAmountTendered;
    }

    public Object getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(Object transactionNo) {
        this.transactionNo = transactionNo;
    }

    public double getTotalCCPAmountRefunded() {
        return totalCCPAmountRefunded;
    }

    public void setTotalCCPAmountRefunded(double totalCCPAmountRefunded) {
        this.totalCCPAmountRefunded = totalCCPAmountRefunded;
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

    public Object getTotalCCPAfterDiscount() {
        return totalCCPAfterDiscount;
    }

    public void setTotalCCPAfterDiscount(Object totalCCPAfterDiscount) {
        this.totalCCPAfterDiscount = totalCCPAfterDiscount;
    }

    public double getTotalCCPAmountTendered() {
        return totalCCPAmountTendered;
    }

    public void setTotalCCPAmountTendered(double totalCCPAmountTendered) {
        this.totalCCPAmountTendered = totalCCPAmountTendered;
    }

}
