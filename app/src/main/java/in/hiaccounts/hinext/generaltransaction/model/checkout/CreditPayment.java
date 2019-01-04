
package in.hiaccounts.hinext.generaltransaction.model.checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CreditPayment {


    @SerializedName("totalCCPAfterDiscount")
    @Expose
    private String totalCCPAfterDiscount;
    @SerializedName("totalCCPDiscount")
    @Expose
    private String totalCCPDiscount;
    @SerializedName("transactionNo")
    @Expose
    private String transactionNo;

    @SerializedName("cardPaymentList")
    @Expose
    private List<CardPaymentList> cardPaymentList = null;

    public List<CardPaymentList> getCardPaymentList() {
        return cardPaymentList;
    }

    public void setCardPaymentList(List<CardPaymentList> cardPaymentList) {
        this.cardPaymentList = cardPaymentList;
    }

    public String getTotalCCPAfterDiscount() {
        return totalCCPAfterDiscount;
    }

    public void setTotalCCPAfterDiscount(String totalCCPAfterDiscount) {
        this.totalCCPAfterDiscount = totalCCPAfterDiscount;
    }

    public String getTotalCCPDiscount() {
        return totalCCPDiscount;
    }

    public void setTotalCCPDiscount(String totalCCPDiscount) {
        this.totalCCPDiscount = totalCCPDiscount;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }
}
