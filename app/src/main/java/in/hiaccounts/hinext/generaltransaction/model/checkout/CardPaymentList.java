
package in.hiaccounts.hinext.generaltransaction.model.checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CardPaymentList {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("transactionNo")
    @Expose
    private String transactionNo;
    @SerializedName("cardAmount")
    @Expose
    private double cardAmount;

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
}
