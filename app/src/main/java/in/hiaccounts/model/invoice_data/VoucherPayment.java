
package in.hiaccounts.model.invoice_data;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VoucherPayment implements Serializable {


    @SerializedName("cardAmount")
    @Expose
    private double cardAmount;
    @SerializedName("totalVoucherAmt")
    @Expose
    private double totalVoucherAmt;
    @SerializedName("totalVPAmountTendered")
    @Expose
    private double totalVPAmountTendered;
    @SerializedName("totalVPAmountRefunded")
    @Expose
    private double totalVPAmountRefunded;
    @SerializedName("totalVPBeforDiscount")
    @Expose
    private double totalVPBeforDiscount;
    @SerializedName("totalVPDiscount")
    @Expose
    private double totalVPDiscount;
    @SerializedName("voucherDate")
    @Expose
    private Object voucherDate;
    @SerializedName("voucherNo")
    @Expose
    private String voucherNo;
    @SerializedName("totalVPAfterAllDeductions")
    @Expose
    private double totalVPAfterAllDeductions;
    @SerializedName("multiVoucherPayments")
    @Expose
    private List<MultiVoucherPayment> multiVoucherPayments = null;

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
     * @param cardAmount
     * @param totalVPDiscount
     * @param totalVoucherAmt
     * @param totalVPAfterAllDeductions
     * @param multiVoucherPayments
     * @param voucherNo
     * @param voucherDate
     * @param totalVPBeforDiscount
     */
    public VoucherPayment(double cardAmount, double totalVoucherAmt, double totalVPAmountTendered, double totalVPAmountRefunded, double totalVPBeforDiscount, double totalVPDiscount, Object voucherDate, String voucherNo, double totalVPAfterAllDeductions, List<MultiVoucherPayment> multiVoucherPayments) {
        super();
        this.cardAmount = cardAmount;
        this.totalVoucherAmt = totalVoucherAmt;
        this.totalVPAmountTendered = totalVPAmountTendered;
        this.totalVPAmountRefunded = totalVPAmountRefunded;
        this.totalVPBeforDiscount = totalVPBeforDiscount;
        this.totalVPDiscount = totalVPDiscount;
        this.voucherDate = voucherDate;
        this.voucherNo = voucherNo;
        this.totalVPAfterAllDeductions = totalVPAfterAllDeductions;
        this.multiVoucherPayments = multiVoucherPayments;
    }

    public double getCardAmount() {
        return cardAmount;
    }

    public void setCardAmount(double cardAmount) {
        this.cardAmount = cardAmount;
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

    public double getTotalVPAmountRefunded() {
        return totalVPAmountRefunded;
    }

    public void setTotalVPAmountRefunded(double totalVPAmountRefunded) {
        this.totalVPAmountRefunded = totalVPAmountRefunded;
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

    public Object getVoucherDate() {
        return voucherDate;
    }

    public void setVoucherDate(Object voucherDate) {
        this.voucherDate = voucherDate;
    }

    public String getVoucherNo() {
        return voucherNo;
    }

    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo;
    }

    public double getTotalVPAfterAllDeductions() {
        return totalVPAfterAllDeductions;
    }

    public void setTotalVPAfterAllDeductions(double totalVPAfterAllDeductions) {
        this.totalVPAfterAllDeductions = totalVPAfterAllDeductions;
    }

    public List<MultiVoucherPayment> getMultiVoucherPayments() {
        return multiVoucherPayments;
    }

    public void setMultiVoucherPayments(List<MultiVoucherPayment> multiVoucherPayments) {
        this.multiVoucherPayments = multiVoucherPayments;
    }

}
