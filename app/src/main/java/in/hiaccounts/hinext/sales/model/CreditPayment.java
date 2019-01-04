
package in.hiaccounts.hinext.sales.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CreditPayment {

    @SerializedName("cardPaymentList")
    @Expose
    private List<CardPaymentList> cardPaymentList = null;
    @SerializedName("totalBPAmountRefunded")
    @Expose
    private Double totalBPAmountRefunded;

    @SerializedName("totalBPAmountTendered")
    @Expose
    private Long totalBPAmountTendered;
    @SerializedName("totalBPBeforDiscount")
    @Expose
    private Long totalBPBeforDiscount;
    @SerializedName("totalBPDiscount")
    @Expose
    private Long totalBPDiscount;


    public Double getTotalBPAmountRefunded() {
        return totalBPAmountRefunded;
    }

    public void setTotalBPAmountRefunded(Double totalBPAmountRefunded) {
        this.totalBPAmountRefunded = totalBPAmountRefunded;
    }

    public Long getTotalBPAmountTendered() {
        return totalBPAmountTendered;
    }

    public void setTotalBPAmountTendered(Long totalBPAmountTendered) {
        this.totalBPAmountTendered = totalBPAmountTendered;
    }

    public Long getTotalBPBeforDiscount() {
        return totalBPBeforDiscount;
    }

    public void setTotalBPBeforDiscount(Long totalBPBeforDiscount) {
        this.totalBPBeforDiscount = totalBPBeforDiscount;
    }

    public Long getTotalBPDiscount() {
        return totalBPDiscount;
    }

    public void setTotalBPDiscount(Long totalBPDiscount) {
        this.totalBPDiscount = totalBPDiscount;
    }

    public List<CardPaymentList> getCardPaymentList() {
        return cardPaymentList;
    }

    public void setCardPaymentList(List<CardPaymentList> cardPaymentList) {
        this.cardPaymentList = cardPaymentList;
    }

}
