
package in.hiaccounts.hinext.sales.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class CashPayment {

    @SerializedName("totalCPAmountTendered")
    @Expose
    private double totalCPAmountTendered;



    @SerializedName("multiCashPaymentList")
    @Expose
    private List<MultiCashPaymentList> multiCashPaymentList;



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
