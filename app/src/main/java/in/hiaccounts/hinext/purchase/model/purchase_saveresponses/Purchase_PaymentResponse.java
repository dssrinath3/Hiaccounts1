package in.hiaccounts.hinext.purchase.model.purchase_saveresponses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Admin on 9/11/2017.
 */

public class Purchase_PaymentResponse implements Serializable {

    @SerializedName("companyName")
    @Expose
    public String companyName;
    @SerializedName("companyNo")
    @Expose
    public String companyNo;
    @SerializedName("gstNo")
    @Expose
    public String gstNo;
    @SerializedName("tranactionId")
    @Expose
    public String tranactionId;
    @SerializedName("result")
    @Expose
    public Object result;
    @SerializedName("date")
    @Expose
    public Object date;
    @SerializedName("time")
    @Expose
    public Object time;
    @SerializedName("itemDetils")
    @Expose
    public Object itemDetils;
    @SerializedName("companyData")
    @Expose
    public Object companyData;
    @SerializedName("userAccountSetupData")
    @Expose
    public Object userAccountSetupData;
    @SerializedName("inventoryLocationData")
    @Expose
    public Object inventoryLocationData;
    @SerializedName("piData")
    @Expose
    public Object piData;
    @SerializedName("recieptFooter")
    @Expose
    public Object recieptFooter;
    @SerializedName("companyLogoPath")
    @Expose
    public Object companyLogoPath;
    @SerializedName("userName")
    @Expose
    public Object userName;
    @SerializedName("footer")
    @Expose
    public Object footer;
    @SerializedName("supplierName")
    @Expose
    public String supplierName;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("address")
    @Expose
    public String address;
    @SerializedName("phoneNo")
    @Expose
    public String phoneNo;
    @SerializedName("invoiceNo")
    @Expose
    public String invoiceNo;
    @SerializedName("invoiceDate")
    @Expose
    public String invoiceDate;
    @SerializedName("invoiceAmount")
    @Expose
    public double invoiceAmount;
    @SerializedName("amountPaid")
    @Expose
    public double amountPaid;
    @SerializedName("balanceAmount")
    @Expose
    public double balanceAmount;
    @SerializedName("cashAmount")
    @Expose
    public double cashAmount;
    @SerializedName("cardAmount")
    @Expose
    public double cardAmount;
    @SerializedName("voucherAmount")
    @Expose
    public double voucherAmount;
    @SerializedName("hsnCode")
    @Expose
    public Object hsnCode;
    @SerializedName("totalIncludingTax")
    @Expose
    public double totalIncludingTax;
    @SerializedName("taxAmt")
    @Expose
    public double taxAmt;
    @SerializedName("cessTaxAmt")
    @Expose
    public double cessTaxAmt;
    @SerializedName("totalExcludingTax")
    @Expose
    public double totalExcludingTax;
    @SerializedName("totalPaid")
    @Expose
    public double totalPaid;
    @SerializedName("balance")
    @Expose
    public double balance;
    @SerializedName("taxInvoice")
    @Expose
    public Object taxInvoice;

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

    public String getGstNo() {
        return gstNo;
    }

    public void setGstNo(String gstNo) {
        this.gstNo = gstNo;
    }

    public String getTranactionId() {
        return tranactionId;
    }

    public void setTranactionId(String tranactionId) {
        this.tranactionId = tranactionId;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Object getDate() {
        return date;
    }

    public void setDate(Object date) {
        this.date = date;
    }

    public Object getTime() {
        return time;
    }

    public void setTime(Object time) {
        this.time = time;
    }

    public Object getItemDetils() {
        return itemDetils;
    }

    public void setItemDetils(Object itemDetils) {
        this.itemDetils = itemDetils;
    }

    public Object getCompanyData() {
        return companyData;
    }

    public void setCompanyData(Object companyData) {
        this.companyData = companyData;
    }

    public Object getUserAccountSetupData() {
        return userAccountSetupData;
    }

    public void setUserAccountSetupData(Object userAccountSetupData) {
        this.userAccountSetupData = userAccountSetupData;
    }

    public Object getInventoryLocationData() {
        return inventoryLocationData;
    }

    public void setInventoryLocationData(Object inventoryLocationData) {
        this.inventoryLocationData = inventoryLocationData;
    }

    public Object getPiData() {
        return piData;
    }

    public void setPiData(Object piData) {
        this.piData = piData;
    }

    public Object getRecieptFooter() {
        return recieptFooter;
    }

    public void setRecieptFooter(Object recieptFooter) {
        this.recieptFooter = recieptFooter;
    }

    public Object getCompanyLogoPath() {
        return companyLogoPath;
    }

    public void setCompanyLogoPath(Object companyLogoPath) {
        this.companyLogoPath = companyLogoPath;
    }

    public Object getUserName() {
        return userName;
    }

    public void setUserName(Object userName) {
        this.userName = userName;
    }

    public Object getFooter() {
        return footer;
    }

    public void setFooter(Object footer) {
        this.footer = footer;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

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

    public double getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(double invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public double getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(double balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public double getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(double cashAmount) {
        this.cashAmount = cashAmount;
    }

    public double getCardAmount() {
        return cardAmount;
    }

    public void setCardAmount(double cardAmount) {
        this.cardAmount = cardAmount;
    }

    public double getVoucherAmount() {
        return voucherAmount;
    }

    public void setVoucherAmount(double voucherAmount) {
        this.voucherAmount = voucherAmount;
    }

    public Object getHsnCode() {
        return hsnCode;
    }

    public void setHsnCode(Object hsnCode) {
        this.hsnCode = hsnCode;
    }

    public double getTotalIncludingTax() {
        return totalIncludingTax;
    }

    public void setTotalIncludingTax(double totalIncludingTax) {
        this.totalIncludingTax = totalIncludingTax;
    }

    public double getTaxAmt() {
        return taxAmt;
    }

    public void setTaxAmt(double taxAmt) {
        this.taxAmt = taxAmt;
    }

    public double getCessTaxAmt() {
        return cessTaxAmt;
    }

    public void setCessTaxAmt(double cessTaxAmt) {
        this.cessTaxAmt = cessTaxAmt;
    }

    public double getTotalExcludingTax() {
        return totalExcludingTax;
    }

    public void setTotalExcludingTax(double totalExcludingTax) {
        this.totalExcludingTax = totalExcludingTax;
    }

    public double getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(double totalPaid) {
        this.totalPaid = totalPaid;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Object getTaxInvoice() {
        return taxInvoice;
    }

    public void setTaxInvoice(Object taxInvoice) {
        this.taxInvoice = taxInvoice;
    }
}
