
package in.hiaccounts.model.duplicate_invoicedata;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VoucherPayment implements Serializable
{

    @SerializedName("voucherNo")
    @Expose
    private String voucherNo;
    @SerializedName("voucherDate")
    @Expose
    private String voucherDate;
    @SerializedName("totalVoucherAmt")
    @Expose
    private double totalVoucherAmt;
    @SerializedName("totalVPBeforDiscount")
    @Expose
    private double totalVPBeforDiscount;
    @SerializedName("totalVPAmountTendered")
    @Expose
    private double totalVPAmountTendered;
    @SerializedName("totalVPAmountRefunded")
    @Expose
    private double totalVPAmountRefunded;
    @SerializedName("multiVoucherPayments")
    @Expose
    private List<MultiVoucherPayment> multiVoucherPayments = null;
    @SerializedName("totalVPAfterAllDeductions")
    @Expose
    private double totalVPAfterAllDeductions;
    @SerializedName("totalVPDiscount")
    @Expose
    private double totalVPDiscount;
    @SerializedName("cardAmount")
    @Expose
    private double cardAmount;
    private final static long serialVersionUID = -5499115973182836140L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public VoucherPayment() {
    }

    /**
     * 
     * @param totalVPAmountRefunded
     * @param cardAmount
     * @param totalVPAmountTendered
     * @param totalVPDiscount
     * @param totalVoucherAmt
     * @param totalVPAfterAllDeductions
     * @param multiVoucherPayments
     * @param voucherDate
     * @param voucherNo
     * @param totalVPBeforDiscount
     */
    public VoucherPayment(String voucherNo, String voucherDate, double totalVoucherAmt, double totalVPBeforDiscount, double totalVPAmountTendered, double totalVPAmountRefunded, List<MultiVoucherPayment> multiVoucherPayments, double totalVPAfterAllDeductions, double totalVPDiscount, double cardAmount) {
        super();
        this.voucherNo = voucherNo;
        this.voucherDate = voucherDate;
        this.totalVoucherAmt = totalVoucherAmt;
        this.totalVPBeforDiscount = totalVPBeforDiscount;
        this.totalVPAmountTendered = totalVPAmountTendered;
        this.totalVPAmountRefunded = totalVPAmountRefunded;
        this.multiVoucherPayments = multiVoucherPayments;
        this.totalVPAfterAllDeductions = totalVPAfterAllDeductions;
        this.totalVPDiscount = totalVPDiscount;
        this.cardAmount = cardAmount;
    }

    public String getVoucherNo() {
        return voucherNo;
    }

    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo;
    }

    public String getVoucherDate() {
        return voucherDate;
    }

    public void setVoucherDate(String voucherDate) {
        this.voucherDate = voucherDate;
    }

    public double getTotalVoucherAmt() {
        return totalVoucherAmt;
    }

    public void setTotalVoucherAmt(double totalVoucherAmt) {
        this.totalVoucherAmt = totalVoucherAmt;
    }

    public double getTotalVPBeforDiscount() {
        return totalVPBeforDiscount;
    }

    public void setTotalVPBeforDiscount(double totalVPBeforDiscount) {
        this.totalVPBeforDiscount = totalVPBeforDiscount;
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

    public List<MultiVoucherPayment> getMultiVoucherPayments() {
        return multiVoucherPayments;
    }

    public void setMultiVoucherPayments(List<MultiVoucherPayment> multiVoucherPayments) {
        this.multiVoucherPayments = multiVoucherPayments;
    }

    public double getTotalVPAfterAllDeductions() {
        return totalVPAfterAllDeductions;
    }

    public void setTotalVPAfterAllDeductions(double totalVPAfterAllDeductions) {
        this.totalVPAfterAllDeductions = totalVPAfterAllDeductions;
    }

    public double getTotalVPDiscount() {
        return totalVPDiscount;
    }

    public void setTotalVPDiscount(double totalVPDiscount) {
        this.totalVPDiscount = totalVPDiscount;
    }

    public double getCardAmount() {
        return cardAmount;
    }

    public void setCardAmount(double cardAmount) {
        this.cardAmount = cardAmount;
    }

}
