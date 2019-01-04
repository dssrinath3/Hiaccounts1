
package in.hiaccounts.model.invoice_data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CardPaymentList implements Serializable{

    @SerializedName("cardAmount")
    @Expose
    private double cardAmount;
    @SerializedName("transactionNo")
    @Expose
    private String transactionNo;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CardPaymentList() {
    }

    /**
     * 
     * @param cardAmount
     * @param transactionNo
     */
    public CardPaymentList(double cardAmount, String transactionNo) {
        super();
        this.cardAmount = cardAmount;
        this.transactionNo = transactionNo;
    }

    public double getCardAmount() {
        return cardAmount;
    }

    public void setCardAmount(double cardAmount) {
        this.cardAmount = cardAmount;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

}
