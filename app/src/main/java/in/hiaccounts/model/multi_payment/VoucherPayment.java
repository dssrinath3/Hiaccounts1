
package in.hiaccounts.model.multi_payment;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VoucherPayment implements Serializable
{

    @SerializedName("totalVPBeforDiscount")
    @Expose
    private double totalVPBeforDiscount;
    @SerializedName("totalVPDiscount")
    @Expose
    private double totalVPDiscount;
    @SerializedName("voucherNo")
    @Expose
    private double voucherNo;
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
    private final static double serialVersionUID = 6216014042014451109L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public VoucherPayment() {
    }

    /**
     * 
     * @param totalVPAmountRefunded
     * @param totalVPAmountTendered
     * @param totalVPDiscount
     * @param totalVoucherAmt
     * @param multiVoucherPayments
     * @param voucherNo
     * @param totalVPBeforDiscount
     */
    public VoucherPayment(double totalVPBeforDiscount, double totalVPDiscount, double voucherNo, double totalVoucherAmt, double totalVPAmountTendered, String totalVPAmountRefunded, List<MultiVoucherPayment> multiVoucherPayments) {
        super();
        this.totalVPBeforDiscount = totalVPBeforDiscount;
        this.totalVPDiscount = totalVPDiscount;
        this.voucherNo = voucherNo;
        this.totalVoucherAmt = totalVoucherAmt;
        this.totalVPAmountTendered = totalVPAmountTendered;
        this.totalVPAmountRefunded = totalVPAmountRefunded;
        this.multiVoucherPayments = multiVoucherPayments;
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

    public double getVoucherNo() {
        return voucherNo;
    }

    public void setVoucherNo(double voucherNo) {
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
