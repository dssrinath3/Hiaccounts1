
package in.hiaccounts.hinext.sales.model.save_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CashPayment   implements Serializable{

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
    private String paymentType;


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

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

}
