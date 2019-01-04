
package in.hiaccounts.hinext.sales.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RemainingPaymentSaveData {

    @SerializedName("cashPayment")
    @Expose
    private CashPayment cashPayment;
    @SerializedName("creditPayment")
    @Expose
    private CreditPayment creditPayment;
    @SerializedName("voucherPayment")
    @Expose
    private VoucherPayment voucherPayment;
    @SerializedName("totalCheckOutamt")
    @Expose
    private double totalCheckOutamt;
    @SerializedName("totalTaxAmt")
    @Expose
    private double totalTaxAmt;
    @SerializedName("taxType")
    @Expose
    private String taxType;
    @SerializedName("customerId")
    @Expose
    private long customerId;
    @SerializedName("cutomerName")
    @Expose
    private String cutomerName;
    @SerializedName("amountReturned")
    @Expose
    private String amountReturned;
    @SerializedName("discountAmount")
    @Expose
    private double discountAmount;
    @SerializedName("totalTenderedAmount")
    @Expose
    private double totalTenderedAmount;
    @SerializedName("dateOfInvoice")
    @Expose
    private String dateOfInvoice;

    public CashPayment getCashPayment() {
        return cashPayment;
    }

    public void setCashPayment(CashPayment cashPayment) {
        this.cashPayment = cashPayment;
    }

    public CreditPayment getCreditPayment() {
        return creditPayment;
    }

    public void setCreditPayment(CreditPayment creditPayment) {
        this.creditPayment = creditPayment;
    }

    public VoucherPayment getVoucherPayment() {
        return voucherPayment;
    }

    public void setVoucherPayment(VoucherPayment voucherPayment) {
        this.voucherPayment = voucherPayment;
    }

    public double getTotalCheckOutamt() {
        return totalCheckOutamt;
    }

    public void setTotalCheckOutamt(double totalCheckOutamt) {
        this.totalCheckOutamt = totalCheckOutamt;
    }

    public double getTotalTaxAmt() {
        return totalTaxAmt;
    }

    public void setTotalTaxAmt(double totalTaxAmt) {
        this.totalTaxAmt = totalTaxAmt;
    }

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getCutomerName() {
        return cutomerName;
    }

    public void setCutomerName(String cutomerName) {
        this.cutomerName = cutomerName;
    }

    public String getAmountReturned() {
        return amountReturned;
    }

    public void setAmountReturned(String amountReturned) {
        this.amountReturned = amountReturned;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public double getTotalTenderedAmount() {
        return totalTenderedAmount;
    }

    public void setTotalTenderedAmount(double totalTenderedAmount) {
        this.totalTenderedAmount = totalTenderedAmount;
    }

    public String getDateOfInvoice() {
        return dateOfInvoice;
    }

    public void setDateOfInvoice(String dateOfInvoice) {
        this.dateOfInvoice = dateOfInvoice;
    }
}
