
package in.hiaccounts.hinext.checkout.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class CashPayment implements Serializable{

    @SerializedName("totalCPAmountTendered")
    @Expose
    private double totalCPAmountTendered;
    @SerializedName("multiCashPaymentList")
    @Expose
    private List<MultiCashPaymentList> multiCashPaymentList;

    private Long paymentTypeId;
    @SerializedName("paymentType")
    @Expose
    private String paymentTypeCash;

    public String getPaymentTypeCash() {
        return paymentTypeCash;
    }

    public void setPaymentTypeCash(String paymentTypeCash) {
        this.paymentTypeCash = paymentTypeCash;
    }

    public Long getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(Long paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public double getTotalCPAmountTendered() {
        return totalCPAmountTendered;
    }

    public void setTotalCPAmountTendered(double totalCPAmountTendered) {
        this.totalCPAmountTendered = totalCPAmountTendered;
    }

    public List<MultiCashPaymentList> getMultiCashPaymentList() {
        return multiCashPaymentList;
    }

    public void setMultiCashPaymentList(List<MultiCashPaymentList> multiCashPaymentList) {
        this.multiCashPaymentList = multiCashPaymentList;
    }
}
