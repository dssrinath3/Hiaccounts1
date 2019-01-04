
package in.hiaccounts.hinext.checkout.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CardPaymentList implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("transactionNo")
    @Expose
    private String transactionNo;
    @SerializedName("cardAmount")
    @Expose
    private double cardAmount;

    @SerializedName("cardAmt")
    @Expose
    private double cardAmt;

    @SerializedName("cardBankAccount")
    @Expose
    private String cardBankAccount;

    @SerializedName("cardNo")
    @Expose
    private String cardNo;

    @SerializedName("cardDate")
    @Expose
    private String cardDate;

    @SerializedName("paymentType")
    @Expose
    private Long paymentType;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public double getCardAmt() {
        return cardAmt;
    }

    public void setCardAmt(double cardAmt) {
        this.cardAmt = cardAmt;
    }

    public String getCardBankAccount() {
        return cardBankAccount;
    }

    public void setCardBankAccount(String cardBankAccount) {
        this.cardBankAccount = cardBankAccount;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardDate() {
        return cardDate;
    }

    public void setCardDate(String cardDate) {
        this.cardDate = cardDate;
    }

    public Long getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Long paymentType) {
        this.paymentType = paymentType;
    }
}
