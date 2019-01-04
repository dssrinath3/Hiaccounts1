package in.hiaccounts.hinext.service.model.service_invoice;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by administrator on 23/2/18.
 */

public class ServiceInvoiceData implements Serializable {
    @SerializedName("customerName")
    @Expose
    public String customerName;
    @SerializedName("formNo")
    @Expose
    public String formNo;
    @SerializedName("srNo")
    @Expose
    public String srNo;
    @SerializedName("amount")
    @Expose
    public Double amount;
    @SerializedName("customerEmail")
    @Expose
    public String customerEmail;
    @SerializedName("arbalance")
    @Expose
    public Double arbalance;
    @SerializedName("supplierName")
    @Expose
    public String supplierName;
    @SerializedName("apBalance")
    @Expose
    public Double apBalance;
    @SerializedName("supplierEmail")
    @Expose
    public String supplierEmail;
    @SerializedName("gtAmountPaid")
    @Expose
    public Object gtAmountPaid;
    @SerializedName("totalDebit")
    @Expose
    public Double totalDebit;
    @SerializedName("totalCredit")
    @Expose
    public Double totalCredit;
    @SerializedName("id")
    @Expose
    public Long id;
    @SerializedName("piStatus")
    @Expose
    public String piStatus;
    @SerializedName("siDate")
    @Expose
    public String siDate;
    @SerializedName("posting")
    @Expose
    public String posting;
    @SerializedName("tax")
    @Expose
    public Double tax;
    @SerializedName("currencyId")
    @Expose
    public Long currencyId;
    @SerializedName("exchangeRateValue")
    @Expose
    public Double exchangeRateValue;

    public String getSrNo() {
        return srNo;
    }

    public void setSrNo(String srNo) {
        this.srNo = srNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getFormNo() {
        return formNo;
    }

    public void setFormNo(String formNo) {
        this.formNo = formNo;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Double getArbalance() {
        return arbalance;
    }

    public void setArbalance(Double arbalance) {
        this.arbalance = arbalance;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Double getApBalance() {
        return apBalance;
    }

    public void setApBalance(Double apBalance) {
        this.apBalance = apBalance;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    public Object getGtAmountPaid() {
        return gtAmountPaid;
    }

    public void setGtAmountPaid(Object gtAmountPaid) {
        this.gtAmountPaid = gtAmountPaid;
    }

    public Double getTotalDebit() {
        return totalDebit;
    }

    public void setTotalDebit(Double totalDebit) {
        this.totalDebit = totalDebit;
    }

    public Double getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(Double totalCredit) {
        this.totalCredit = totalCredit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPiStatus() {
        return piStatus;
    }

    public void setPiStatus(String piStatus) {
        this.piStatus = piStatus;
    }

    public String getSiDate() {
        return siDate;
    }

    public void setSiDate(String siDate) {
        this.siDate = siDate;
    }

    public String getPosting() {
        return posting;
    }

    public void setPosting(String posting) {
        this.posting = posting;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public Double getExchangeRateValue() {
        return exchangeRateValue;
    }

    public void setExchangeRateValue(Double exchangeRateValue) {
        this.exchangeRateValue = exchangeRateValue;
    }
}
