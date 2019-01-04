
package in.hiaccounts.hinext.checkout.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class VoucherPayment implements Serializable{

    @SerializedName("totalVPBeforDiscount")
    @Expose
    private double totalVPBeforDiscount;
    @SerializedName("totalVPDiscount")
    @Expose
    private double totalVPDiscount;
    @SerializedName("voucherNo")
    @Expose
    private long voucherNo;
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
    @SerializedName("paymentType")
    @Expose
    private String paymentTypeCash;

    public String getPaymentTypeCash() {
        return paymentTypeCash;
    }

    public void setPaymentTypeCash(String paymentTypeCash) {
        this.paymentTypeCash = paymentTypeCash;
    }

    public double getTotalVPBeforDiscount() {
        return totalVPBeforDiscount;
    }

    public void setTotalVPBeforDiscount(double totalVPBeforDiscount) {
        this.totalVPBeforDiscount = totalVPBeforDiscount;
    }

    public double getTotalVPDiscount() {
        return totalVPDiscount;
    }

    public void setTotalVPDiscount(double totalVPDiscount) {
        this.totalVPDiscount = totalVPDiscount;
    }

    public long getVoucherNo() {
        return voucherNo;
    }

    public void setVoucherNo(long voucherNo) {
        this.voucherNo = voucherNo;
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
