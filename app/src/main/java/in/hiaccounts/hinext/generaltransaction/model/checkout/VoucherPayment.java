
package in.hiaccounts.hinext.generaltransaction.model.checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VoucherPayment {

    @SerializedName("totalVPBeforDiscount")
    @Expose
    private double totalVPBeforDiscount;
    @SerializedName("totalVPDiscount")
    @Expose
    private String totalVPDiscount;
    @SerializedName("totalVoucherAmt")
    @Expose
    private double totalVoucherAmt;
    @SerializedName("totalVPAmountTendered")
    @Expose
    private double totalVPAmountTendered;
    @SerializedName("totalVPAmountRefunded")
    @Expose
    private String totalVPAmountRefunded;
    @SerializedName("multiVoucherPayments")
    @Expose
    private List<MultiVoucherPayment> multiVoucherPayments = null;

    public double getTotalVPBeforDiscount() {
        return totalVPBeforDiscount;
    }

    public void setTotalVPBeforDiscount(double totalVPBeforDiscount) {
        this.totalVPBeforDiscount = totalVPBeforDiscount;
    }

    public String getTotalVPDiscount() {
        return totalVPDiscount;
    }

    public void setTotalVPDiscount(String totalVPDiscount) {
        this.totalVPDiscount = totalVPDiscount;
    }

    public double getTotalVoucherAmt() {
        return totalVoucherAmt;
    }

    public void setTotalVoucherAmt(double totalVoucherAmt) {
        this.totalVoucherAmt = totalVoucherAmt;
    }

    public double getTotalVPAmountTendered() {
        return totalVPAmountTendered;
    }

    public void setTotalVPAmountTendered(double totalVPAmountTendered) {
        this.totalVPAmountTendered = totalVPAmountTendered;
    }

    public String getTotalVPAmountRefunded() {
        return totalVPAmountRefunded;
    }

    public void setTotalVPAmountRefunded(String totalVPAmountRefunded) {
        this.totalVPAmountRefunded = totalVPAmountRefunded;
    }

    public List<MultiVoucherPayment> getMultiVoucherPayments() {
        return multiVoucherPayments;
    }

    public void setMultiVoucherPayments(List<MultiVoucherPayment> multiVoucherPayments) {
        this.multiVoucherPayments = multiVoucherPayments;
    }
}
