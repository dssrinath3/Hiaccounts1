
package in.hiaccounts.model.multi_payment;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CardPaymentList implements Serializable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("transactionNo")
    @Expose
    private String transactionNo;
    @SerializedName("cardAmount")
    @Expose
    private double cardAmount;

    @SerializedName("totalCCPDiscount")
    @Expose
    private double totalCCPDiscount;
    @SerializedName("totalCCPAfterDiscountc")
    @Expose
    private double totalCCPAfterDiscount;





    private final static long serialVersionUID = 8066205098011627936L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CardPaymentList() {
    }

    /**
     * 
     * @param id
     * @param cardAmount
     * @param transactionNo
     */
    public CardPaymentList(String id, String transactionNo, double cardAmount) {
        super();
        this.id = id;
        this.transactionNo = transactionNo;
        this.cardAmount = cardAmount;
    }

    public CardPaymentList(String transactionNo, double totalCCPDiscount, double totalCCPAfterDiscount) {
        this.transactionNo = transactionNo;
        this.totalCCPDiscount = totalCCPDiscount;
        this.totalCCPAfterDiscount = totalCCPAfterDiscount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

    public double getCardAmount() {
        return cardAmount;
    }

    public void setCardAmount(double cardAmount) {
        this.cardAmount = cardAmount;
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
}
