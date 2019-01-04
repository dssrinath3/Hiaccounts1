
package in.hiaccounts.hinext.purchase.model.purchase_invoicereturn;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class VoucherPayment implements Serializable {

    @SerializedName("totalVPBeforDiscount")
    @Expose
    private Double totalVPBeforDiscount;
    @SerializedName("totalVPDiscount")
    @Expose
    private Double totalVPDiscount;
    @SerializedName("voucherNo")
    @Expose
    private Object voucherNo;
    @SerializedName("voucherDate")
    @Expose
    private Object voucherDate;
    @SerializedName("totalVoucherAmt")
    @Expose
    private Double totalVoucherAmt;
    @SerializedName("totalVPAfterAllDeductions")
    @Expose
    private Double totalVPAfterAllDeductions;
    @SerializedName("totalVPAmountTendered")
    @Expose
    private Double totalVPAmountTendered;
    @SerializedName("totalVPAmountRefunded")
    @Expose
    private Double totalVPAmountRefunded;
    @SerializedName("cardAmount")
    @Expose
    private Double cardAmount;
    @SerializedName("multiVoucherPayments")
    @Expose
    private List<Object> multiVoucherPayments = null;

    public Double getTotalVPBeforDiscount() {
        return totalVPBeforDiscount;
    }

    public void setTotalVPBeforDiscount(Double totalVPBeforDiscount) {
        this.totalVPBeforDiscount = totalVPBeforDiscount;
    }

    public Double getTotalVPDiscount() {
        return totalVPDiscount;
    }

    public void setTotalVPDiscount(Double totalVPDiscount) {
        this.totalVPDiscount = totalVPDiscount;
    }

    public Object getVoucherNo() {
        return voucherNo;
    }

    public void setVoucherNo(Object voucherNo) {
        this.voucherNo = voucherNo;
    }

    public Object getVoucherDate() {
        return voucherDate;
    }

    public void setVoucherDate(Object voucherDate) {
        this.voucherDate = voucherDate;
    }

    public Double getTotalVoucherAmt() {
        return totalVoucherAmt;
    }

    public void setTotalVoucherAmt(Double totalVoucherAmt) {
        this.totalVoucherAmt = totalVoucherAmt;
    }

    public Double getTotalVPAfterAllDeductions() {
        return totalVPAfterAllDeductions;
    }

    public void setTotalVPAfterAllDeductions(Double totalVPAfterAllDeductions) {
        this.totalVPAfterAllDeductions = totalVPAfterAllDeductions;
    }

    public Double getTotalVPAmountTendered() {
        return totalVPAmountTendered;
    }

    public void setTotalVPAmountTendered(Double totalVPAmountTendered) {
        this.totalVPAmountTendered = totalVPAmountTendered;
    }

    public Double getTotalVPAmountRefunded() {
        return totalVPAmountRefunded;
    }

    public void setTotalVPAmountRefunded(Double totalVPAmountRefunded) {
        this.totalVPAmountRefunded = totalVPAmountRefunded;
    }

    public Double getCardAmount() {
        return cardAmount;
    }

    public void setCardAmount(Double cardAmount) {
        this.cardAmount = cardAmount;
    }

    public List<Object> getMultiVoucherPayments() {
        return multiVoucherPayments;
    }

    public void setMultiVoucherPayments(List<Object> multiVoucherPayments) {
        this.multiVoucherPayments = multiVoucherPayments;
    }

}
