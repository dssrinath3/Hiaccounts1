
package in.hiaccounts.hinext.sales.model.save_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CreditPayment implements Serializable{

    @SerializedName("totalCCPAmountRefunded")
    @Expose
    private Double totalCCPAmountRefunded;
    @SerializedName("totalCCPDiscount")
    @Expose
    private Double totalCCPDiscount;
    @SerializedName("totalCCPAmountTendered")
    @Expose
    private Double totalCCPAmountTendered;
    @SerializedName("transactionNo")
    @Expose
    private Object transactionNo;
    @SerializedName("totalCCPAfterDiscount")
    @Expose
    private Object totalCCPAfterDiscount;
    @SerializedName("cardPaymentList")
    @Expose
    private List<CardPaymentList> cardPaymentList = null;


    public Double getTotalCCPAmountRefunded() {
        return totalCCPAmountRefunded;
    }

    public void setTotalCCPAmountRefunded(Double totalCCPAmountRefunded) {
        this.totalCCPAmountRefunded = totalCCPAmountRefunded;
    }

    public Double getTotalCCPDiscount() {
        return totalCCPDiscount;
    }

    public void setTotalCCPDiscount(Double totalCCPDiscount) {
        this.totalCCPDiscount = totalCCPDiscount;
    }

    public Double getTotalCCPAmountTendered() {
        return totalCCPAmountTendered;
    }

    public void setTotalCCPAmountTendered(Double totalCCPAmountTendered) {
        this.totalCCPAmountTendered = totalCCPAmountTendered;
    }

    public Object getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(Object transactionNo) {
        this.transactionNo = transactionNo;
    }

    public Object getTotalCCPAfterDiscount() {
        return totalCCPAfterDiscount;
    }

    public void setTotalCCPAfterDiscount(Object totalCCPAfterDiscount) {
        this.totalCCPAfterDiscount = totalCCPAfterDiscount;
    }

    public List<CardPaymentList> getCardPaymentList() {
        return cardPaymentList;
    }

    public void setCardPaymentList(List<CardPaymentList> cardPaymentList) {
        this.cardPaymentList = cardPaymentList;
    }

}
