
package in.hiaccounts.model.multi_payment;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreditPayment implements Serializable
{

    @SerializedName("cardPaymentList")
    @Expose
    private List<CardPaymentList> cardPaymentList = null;
    private final static long serialVersionUID = 6350669895730880509L;


    @SerializedName("transactionNo")
    @Expose
    private String transactionNo;
    @SerializedName("totalCCPDiscount")
    @Expose
    private double totalCCPDiscount;
    @SerializedName("totalCCPAfterDiscountc")
    @Expose
    private double totalCCPAfterDiscount;
    /**
     * No args constructor for use in serialization
     * 
     */
    public CreditPayment() {
    }

    /**
     * 
     * @param cardPaymentList
     */
    public CreditPayment(List<CardPaymentList> cardPaymentList) {
        super();
        this.cardPaymentList = cardPaymentList;
    }

    public CreditPayment(String transactionNo, double totalCCPDiscount, double totalCCPAfterDiscount) {
        this.transactionNo = transactionNo;
        this.totalCCPDiscount = totalCCPDiscount;
        this.totalCCPAfterDiscount = totalCCPAfterDiscount;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

    public double getTotalCCPDiscount() {
        return totalCCPDiscount;
    }

    public void setTotalCCPDiscount(double totalCCPDiscount) {
        this.totalCCPDiscount = totalCCPDiscount;
    }

    public double getTotalCCPAfterDiscount() {
        return totalCCPAfterDiscount;
    }

    public void setTotalCCPAfterDiscount(double totalCCPAfterDiscount) {
        this.totalCCPAfterDiscount = totalCCPAfterDiscount;
    }

    public List<CardPaymentList> getCardPaymentList() {
        return cardPaymentList;
    }

    public void setCardPaymentList(List<CardPaymentList> cardPaymentList) {
        this.cardPaymentList = cardPaymentList;
    }

}
