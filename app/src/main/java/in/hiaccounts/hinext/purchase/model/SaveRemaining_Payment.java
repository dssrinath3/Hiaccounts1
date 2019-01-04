package in.hiaccounts.hinext.purchase.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import in.hiaccounts.hinext.checkout.model.BankPayment;
import in.hiaccounts.hinext.checkout.model.CashPayment;
import in.hiaccounts.hinext.checkout.model.CreditPayment;
import in.hiaccounts.hinext.checkout.model.VoucherPayment;

/**
 * Created by Admin on 7/29/2017.
 */

public class SaveRemaining_Payment {
    @SerializedName("cashPayment")
    @Expose
    private CashPayment cashPayment;
    @SerializedName("bankPayment")
    @Expose
    private BankPayment bankPayment;

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
    @SerializedName("supplierId")
    @Expose
    private long supplierId;
    @SerializedName("supplierName")
    @Expose
    private String supplierName;
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

    @SerializedName("customerId")
    @Expose
    private long customerId;
    @SerializedName("cutomerName")
    @Expose
    private String cutomerName;
    @SerializedName("currencyId")
    @Expose
    private Long currencyId;
    @SerializedName("exchangeRateId")
    @Expose
    private Long exchangeRateId;
    @SerializedName("exchangerateValue")
    @Expose
    private Long exchangerateValue;
    @SerializedName("multiPartialPaymentList")
    @Expose
    private List<MultiPartialPaymentList> multiPartialPaymentList =null;
    @SerializedName("tcsAmount")
    @Expose
    private Long tcsAmount;
    @SerializedName("tdsAmount")
    @Expose
    private Long tdsAmount;

    public Long getTcsAmount() {
        return tcsAmount;
    }

    public void setTcsAmount(Long tcsAmount) {
        this.tcsAmount = tcsAmount;
    }

    public Long getTdsAmount() {
        return tdsAmount;
    }

    public void setTdsAmount(Long tdsAmount) {
        this.tdsAmount = tdsAmount;
    }

    public List<MultiPartialPaymentList> getMultiPartialPaymentList() {
        return multiPartialPaymentList;
    }

    public void setMultiPartialPaymentList(List<MultiPartialPaymentList> multiPartialPaymentList) {
        this.multiPartialPaymentList = multiPartialPaymentList;
    }

    public Long getCurrencyId() {
        return currencyId;
    }



    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public Long getExchangeRateId() {
        return exchangeRateId;
    }

    public void setExchangeRateId(Long exchangeRateId) {
        this.exchangeRateId = exchangeRateId;
    }

    public Long getExchangerateValue() {
        return exchangerateValue;
    }

    public void setExchangerateValue(Long exchangerateValue) {
        this.exchangerateValue = exchangerateValue;
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

    public BankPayment getBankPayment() {
        return bankPayment;
    }

    public void setBankPayment(BankPayment bankPayment) {
        this.bankPayment = bankPayment;
    }

    public String getDateOfInvoice() {
        return dateOfInvoice;
    }

    public void setDateOfInvoice(String dateOfInvoice) {
        this.dateOfInvoice = dateOfInvoice;
    }

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

    public long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
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
}
