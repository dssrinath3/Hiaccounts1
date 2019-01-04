package in.hiaccounts.hinext.restaurant.model.checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Admin on 11/30/2017.
 */

public class MultiCardPaymentList implements Serializable {
    @SerializedName("paymentType")
    @Expose
    private Long paymentType;

    @SerializedName("cardNo")
    @Expose
    private String cardNo;

    @SerializedName("cardAmt")
    @Expose
    private String cardAmt;

    @SerializedName("cardBankAccount")
    @Expose
    private String cardBankAccount;

    @SerializedName("cardDate")
    @Expose
    private String cardDate;

    public Long getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Long paymentType) {
        this.paymentType = paymentType;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardAmt() {
        return cardAmt;
    }

    public void setCardAmt(String cardAmt) {
        this.cardAmt = cardAmt;
    }

    public String getCardBankAccount() {
        return cardBankAccount;
    }

    public void setCardBankAccount(String cardBankAccount) {
        this.cardBankAccount = cardBankAccount;
    }

    public String getCardDate() {
        return cardDate;
    }

    public void setCardDate(String cardDate) {
        this.cardDate = cardDate;
    }
}
