
package in.hiaccounts.hinext.purchase.model.purchase_invoicereturn;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CashPayment implements Serializable{

    @SerializedName("totalCPAmountRefunded")
    @Expose
    private Double totalCPAmountRefunded;
    @SerializedName("totalCPDiscount")
    @Expose
    private Double totalCPDiscount;
    @SerializedName("totalCPAmountTendered")
    @Expose
    private Double totalCPAmountTendered;
    @SerializedName("paymentType")
    @Expose
    private Object paymentType;

    public Double getTotalCPAmountRefunded() {
        return totalCPAmountRefunded;
    }

    public void setTotalCPAmountRefunded(Double totalCPAmountRefunded) {
        this.totalCPAmountRefunded = totalCPAmountRefunded;
    }

    public Double getTotalCPDiscount() {
        return totalCPDiscount;
    }

    public void setTotalCPDiscount(Double totalCPDiscount) {
        this.totalCPDiscount = totalCPDiscount;
    }

    public Double getTotalCPAmountTendered() {
        return totalCPAmountTendered;
    }

    public void setTotalCPAmountTendered(Double totalCPAmountTendered) {
        this.totalCPAmountTendered = totalCPAmountTendered;
    }

    public Object getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Object paymentType) {
        this.paymentType = paymentType;
    }

}
