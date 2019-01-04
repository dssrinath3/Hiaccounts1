package in.hiaccounts.hinext.restaurant.model.checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Admin on 11/22/2017.
 */

public class CardPayment implements Serializable {

    Long paymentType;
    double cardAmt;
    String cardDate;
    String cardNo;
    String transactionNo;

    @SerializedName("cardPaymentList")
    @Expose
    private List<MultiCardPaymentList> multiCardPaymentList;

    public Long getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Long paymentType) {
        this.paymentType = paymentType;
    }

    public double getCardAmt() {
        return cardAmt;
    }

    public void setCardAmt(double cardAmt) {
        this.cardAmt = cardAmt;
    }

    public String getCardDate() {
        return cardDate;
    }

    public void setCardDate(String cardDate) {
        this.cardDate = cardDate;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

    public List<MultiCardPaymentList> getMultiCardPaymentList() {
        return multiCardPaymentList;
    }

    public void setMultiCardPaymentList(List<MultiCardPaymentList> multiCardPaymentList) {
        this.multiCardPaymentList = multiCardPaymentList;
    }
}
