package in.hiaccounts.hinext.restaurant.model.checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Admin on 11/23/2017.
 */

public class CashPayment implements Serializable{
    @SerializedName("paymentmethodId")
    @Expose
    public Long paymentmethodId;
    @SerializedName("totalCPAmountRefunded")
    @Expose
    public String totalCPAmountRefunded;
    @SerializedName("totalCPDiscount")
    @Expose
    public double totalCPDiscount;
    @SerializedName("totalCPAmountTendered")
    @Expose
    public double totalCPAmountTendered;

    @SerializedName("multiCashPaymentList")
    @Expose
    private List<MultiCashPaymentList> multiCashPaymentList;

    public List<MultiCashPaymentList> getMultiCashPaymentList() {
        return multiCashPaymentList;
    }

    public void setMultiCashPaymentList(List<MultiCashPaymentList> multiCashPaymentList) {
        this.multiCashPaymentList = multiCashPaymentList;
    }

    public Long getPaymentmethodId() {
        return paymentmethodId;
    }

    public void setPaymentmethodId(Long paymentmethodId) {
        this.paymentmethodId = paymentmethodId;
    }

    public String getTotalCPAmountRefunded() {
        return totalCPAmountRefunded;
    }

    public void setTotalCPAmountRefunded(String totalCPAmountRefunded) {
        this.totalCPAmountRefunded = totalCPAmountRefunded;
    }

    public double getTotalCPDiscount() {
        return totalCPDiscount;
    }

    public void setTotalCPDiscount(double totalCPDiscount) {
        this.totalCPDiscount = totalCPDiscount;
    }

    public double getTotalCPAmountTendered() {
        return totalCPAmountTendered;
    }

    public void setTotalCPAmountTendered(double totalCPAmountTendered) {
        this.totalCPAmountTendered = totalCPAmountTendered;
    }
}
