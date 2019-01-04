package in.hiaccounts.model.pos_invoice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Admin on 2/20/2017.
 */

public class VoucherPayment implements Serializable{

    @SerializedName("voucherDate")
    @Expose
    private String voucherDate;
    @SerializedName("voucherNo")
    @Expose
    private String voucherNo;
    @SerializedName("totalVoucherAmt")
    @Expose
    private double totalVoucherAmt;
    @SerializedName("totalVPDiscount")
    @Expose
    private double totalVPDiscount;
    @SerializedName("totalVPAmountRefunded")
    @Expose
    private double totalVPAmountRefunded;
    @SerializedName("totalVPAfterAllDeductions")
    @Expose
    private double totalVPAfterAllDeductions;
    @SerializedName("totalVPAmountTendered")
    @Expose
    private double totalVPAmountTendered;
    @SerializedName("totalVPBeforDiscount")
    @Expose
    private double totalVPBeforDiscount;

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
     * @param totalVPAfterAllDeductions
     * @param voucherNo
     * @param voucherDate
     * @param totalVPBeforDiscount
     */
    public VoucherPayment(String  voucherDate, String voucherNo, double totalVoucherAmt, double totalVPDiscount, double totalVPAmountRefunded, double totalVPAfterAllDeductions, double totalVPAmountTendered, double totalVPBeforDiscount) {
        super();
        this.voucherDate = voucherDate;
        this.voucherNo = voucherNo;
        this.totalVoucherAmt = totalVoucherAmt;
        this.totalVPDiscount = totalVPDiscount;
        this.totalVPAmountRefunded = totalVPAmountRefunded;
        this.totalVPAfterAllDeductions = totalVPAfterAllDeductions;
        this.totalVPAmountTendered = totalVPAmountTendered;
        this.totalVPBeforDiscount = totalVPBeforDiscount;
    }

    public String getVoucherDate() {
        return voucherDate;
    }

    public void setVoucherDate(String voucherDate) {
        this.voucherDate = voucherDate;
    }

    public String getVoucherNo() {
        return voucherNo;
    }

    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo;
    }

    public double getTotalVoucherAmt() {
        return totalVoucherAmt;
    }

    public void setTotalVoucherAmt(double totalVoucherAmt) {
        this.totalVoucherAmt = totalVoucherAmt;
    }

    public double getTotalVPDiscount() {
        return totalVPDiscount;
    }

    public void setTotalVPDiscount(double totalVPDiscount) {
        this.totalVPDiscount = totalVPDiscount;
    }

    public double getTotalVPAmountRefunded() {
        return totalVPAmountRefunded;
    }

    public void setTotalVPAmountRefunded(double totalVPAmountRefunded) {
        this.totalVPAmountRefunded = totalVPAmountRefunded;
    }

    public double getTotalVPAfterAllDeductions() {
        return totalVPAfterAllDeductions;
    }

    public void setTotalVPAfterAllDeductions(double totalVPAfterAllDeductions) {
        this.totalVPAfterAllDeductions = totalVPAfterAllDeductions;
    }

    public double getTotalVPAmountTendered() {
        return totalVPAmountTendered;
    }

    public void setTotalVPAmountTendered(double totalVPAmountTendered) {
        this.totalVPAmountTendered = totalVPAmountTendered;
    }

    public double getTotalVPBeforDiscount() {
        return totalVPBeforDiscount;
    }

    public void setTotalVPBeforDiscount(double totalVPBeforDiscount) {
        this.totalVPBeforDiscount = totalVPBeforDiscount;
    }
}
