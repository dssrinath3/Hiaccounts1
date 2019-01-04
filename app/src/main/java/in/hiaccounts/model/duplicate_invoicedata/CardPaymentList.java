
package in.hiaccounts.model.duplicate_invoicedata;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CardPaymentList implements Serializable
{

    @SerializedName("transactionNo")
    @Expose
    private String transactionNo;
    @SerializedName("cardAmount")
    @Expose
    private double cardAmount;
    private final static long serialVersionUID = -8909919178124495097L;

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
    public CardPaymentList(String transactionNo, double cardAmount) {
        super();
        this.transactionNo = transactionNo;
        this.cardAmount = cardAmount;
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

}
