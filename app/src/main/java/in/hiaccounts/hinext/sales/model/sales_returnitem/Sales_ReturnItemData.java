package in.hiaccounts.hinext.sales.model.sales_returnitem;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by administrator on 17/1/18.
 */

public class Sales_ReturnItemData implements Serializable {
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
    public Long gtAmountPaid;
    @SerializedName("totalDebit")
    @Expose
    public Long totalDebit;
    @SerializedName("totalCredit")
    @Expose
    public Long totalCredit;
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

    public Long getGtAmountPaid() {
        return gtAmountPaid;
    }

    public void setGtAmountPaid(Long gtAmountPaid) {
        this.gtAmountPaid = gtAmountPaid;
    }

    public Long getTotalDebit() {
        return totalDebit;
    }

    public void setTotalDebit(Long totalDebit) {
        this.totalDebit = totalDebit;
    }

    public Long getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(Long totalCredit) {
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
