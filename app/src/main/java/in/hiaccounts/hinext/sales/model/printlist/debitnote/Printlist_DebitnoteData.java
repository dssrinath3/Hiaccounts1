package in.hiaccounts.hinext.sales.model.printlist.debitnote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by administrator on 16/1/18.
 */

public class Printlist_DebitnoteData implements Serializable {

    @SerializedName("customerName")
    @Expose
    public String customerName;
    @SerializedName("formNo")
    @Expose
    public String formNo;
    @SerializedName("amount")
    @Expose
    public Double amount;
    @SerializedName("customerEmail")
    @Expose
    public String customerEmail;
    @SerializedName("arbalance")
    @Expose
    public Long arbalance;
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
    public Long gtAmountPaid;
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
    @SerializedName("invoiceNo")
    @Expose
    public Long invoiceNo;
    @SerializedName("incPercent")
    @Expose
    public Double incPercent;
    @SerializedName("invAmt")
    @Expose
    public Double invAmt;
    @SerializedName("taxPer")
    @Expose
    public Double taxPer;
    @SerializedName("incrementAmt")
    @Expose
    public Double incrementAmt;
    @SerializedName("taxAmt")
    @Expose
    public Double taxAmt;
    @SerializedName("cessPer")
    @Expose
    public Double cessPer;
    @SerializedName("cessAmt")
    @Expose
    public Double cessAmt;

    public Long getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(Long invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public Double getIncPercent() {
        return incPercent;
    }

    public void setIncPercent(Double incPercent) {
        this.incPercent = incPercent;
    }

    public Double getInvAmt() {
        return invAmt;
    }

    public void setInvAmt(Double invAmt) {
        this.invAmt = invAmt;
    }

    public Double getTaxPer() {
        return taxPer;
    }

    public void setTaxPer(Double taxPer) {
        this.taxPer = taxPer;
    }

    public Double getIncrementAmt() {
        return incrementAmt;
    }

    public void setIncrementAmt(Double incrementAmt) {
        this.incrementAmt = incrementAmt;
    }

    public Double getTaxAmt() {
        return taxAmt;
    }

    public void setTaxAmt(Double taxAmt) {
        this.taxAmt = taxAmt;
    }

    public Double getCessPer() {
        return cessPer;
    }

    public void setCessPer(Double cessPer) {
        this.cessPer = cessPer;
    }

    public Double getCessAmt() {
        return cessAmt;
    }

    public void setCessAmt(Double cessAmt) {
        this.cessAmt = cessAmt;
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

    public Long getArbalance() {
        return arbalance;
    }

    public void setArbalance(Long arbalance) {
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

    public Long getGtAmountPaid() {
        return gtAmountPaid;
    }

    public void setGtAmountPaid(Long gtAmountPaid) {
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
}
