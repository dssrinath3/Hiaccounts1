package in.hiaccounts.hinext.sales.model.printlist.debitnote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by administrator on 16/1/18.
 */

public class Printlist_debitData implements Serializable {
    @SerializedName("invoiceNo")
    @Expose
    public String invoiceNo;
    @SerializedName("invoiceDate")
    @Expose
    public String invoiceDate;
    @SerializedName("incPercent")
    @Expose
    public Double incPercent;
    @SerializedName("invAmt")
    @Expose
    public Double invAmt;
    @SerializedName("customer")
    @Expose
    public Long customer;
    @SerializedName("taxPer")
    @Expose
    public Long taxPer;
    @SerializedName("memo")
    @Expose
    public String memo;
    @SerializedName("incrementAmt")
    @Expose
    public Double incrementAmt;
    @SerializedName("taxAmt")
    @Expose
    public Double taxAmt;
    @SerializedName("formNo")
    @Expose
    public String formNo;
    @SerializedName("cessPer")
    @Expose
    public Double cessPer;
    @SerializedName("cessAmt")
    @Expose
    public Double cessAmt;
    @SerializedName("result")
    @Expose
    public String result;
    @SerializedName("custName")
    @Expose
    public String custName;
    @SerializedName("address")
    @Expose
    public String address;
    @SerializedName("phoneNumber")
    @Expose
    public String phoneNumber;
    @SerializedName("shippingAddress")
    @Expose
    public String shippingAddress;
    @SerializedName("regNo")
    @Expose
    public String regNo;
    @SerializedName("companyName")
    @Expose
    public String companyName;
    @SerializedName("companyNo")
    @Expose
    public String companyNo;
    @SerializedName("email")
    @Expose
    public String email;

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
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

    public Long getCustomer() {
        return customer;
    }

    public void setCustomer(Long customer) {
        this.customer = customer;
    }

    public Long getTaxPer() {
        return taxPer;
    }

    public void setTaxPer(Long taxPer) {
        this.taxPer = taxPer;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
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

    public String getFormNo() {
        return formNo;
    }

    public void setFormNo(String formNo) {
        this.formNo = formNo;
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
