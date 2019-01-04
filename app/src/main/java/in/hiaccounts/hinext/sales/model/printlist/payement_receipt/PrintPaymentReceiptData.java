package in.hiaccounts.hinext.sales.model.printlist.payement_receipt;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Prateek on 9/23/2017.
 */

public class PrintPaymentReceiptData implements Serializable{

    @SerializedName("paymenetId")
    @Expose
    public Object paymenetId;
    @SerializedName("customerName")
    @Expose
    public String customerName;
    @SerializedName("address")
    @Expose
    public String address;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("phoneNumber1")
    @Expose
    public String phoneNumber1;
    @SerializedName("voucherPayment")
    @Expose
    public Object voucherPayment;
    @SerializedName("cardPayment")
    @Expose
    public Object cardPayment;
    @SerializedName("totalCashPayment")
    @Expose
    public double totalCashPayment;
    @SerializedName("totalVoucherPayment")
    @Expose
    public double totalVoucherPayment;
    @SerializedName("totalCardPayment")
    @Expose
    public double totalCardPayment;
    @SerializedName("totalAmount")
    @Expose
    public double totalAmount;
    @SerializedName("duplicatePrint")
    @Expose
    public Object duplicatePrint;
    @SerializedName("count")
    @Expose
    public long count;
    @SerializedName("totalTaxAmount")
    @Expose
    public double totalTaxAmount;
    @SerializedName("roundingAdjustment")
    @Expose
    public double roundingAdjustment;
    @SerializedName("locationId")
    @Expose
    public String locationId;
    @SerializedName("useraccount_id")
    @Expose
    public Object useraccountId;
    @SerializedName("companyName")
    @Expose
    public String companyName;
    @SerializedName("company_no")
    @Expose
    public String companyNo;
    @SerializedName("gstNo")
    @Expose
    public Object gstNo;
    @SerializedName("tranactionId")
    @Expose
    public Object tranactionId;
    @SerializedName("registerNo")
    @Expose
    public String registerNo;
    @SerializedName("typePrefix")
    @Expose
    public String typePrefix;
    @SerializedName("nextref")
    @Expose
    public String nextref;
    @SerializedName("fileName")
    @Expose
    public String fileName;
    @SerializedName("phoneNo")
    @Expose
    public String phoneNo;
    @SerializedName("invoiceNo")
    @Expose
    public Object invoiceNo;
    @SerializedName("pidate")
    @Expose
    public Object pidate;
    @SerializedName("invoiceDate")
    @Expose
    public Object invoiceDate;
    @SerializedName("invoiceAmount")
    @Expose
    public double invoiceAmount;
    @SerializedName("supplierName")
    @Expose
    public Object supplierName;
    @SerializedName("balance")
    @Expose
    public double balance;
    @SerializedName("amountPaid")
    @Expose
    public double amountPaid;
    @SerializedName("totalReceivable")
    @Expose
    public double totalReceivable;
    @SerializedName("sino")
    @Expose
    public String sino;
    @SerializedName("sidate")
    @Expose
    public String sidate;

    public Object getPaymenetId() {
        return paymenetId;
    }

    public void setPaymenetId(Object paymenetId) {
        this.paymenetId = paymenetId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber1() {
        return phoneNumber1;
    }

    public void setPhoneNumber1(String phoneNumber1) {
        this.phoneNumber1 = phoneNumber1;
    }

    public Object getVoucherPayment() {
        return voucherPayment;
    }

    public void setVoucherPayment(Object voucherPayment) {
        this.voucherPayment = voucherPayment;
    }

    public Object getCardPayment() {
        return cardPayment;
    }

    public void setCardPayment(Object cardPayment) {
        this.cardPayment = cardPayment;
    }

    public double getTotalCashPayment() {
        return totalCashPayment;
    }

    public void setTotalCashPayment(double totalCashPayment) {
        this.totalCashPayment = totalCashPayment;
    }

    public double getTotalVoucherPayment() {
        return totalVoucherPayment;
    }

    public void setTotalVoucherPayment(double totalVoucherPayment) {
        this.totalVoucherPayment = totalVoucherPayment;
    }

    public double getTotalCardPayment() {
        return totalCardPayment;
    }

    public void setTotalCardPayment(double totalCardPayment) {
        this.totalCardPayment = totalCardPayment;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Object getDuplicatePrint() {
        return duplicatePrint;
    }

    public void setDuplicatePrint(Object duplicatePrint) {
        this.duplicatePrint = duplicatePrint;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public double getTotalTaxAmount() {
        return totalTaxAmount;
    }

    public void setTotalTaxAmount(double totalTaxAmount) {
        this.totalTaxAmount = totalTaxAmount;
    }

    public double getRoundingAdjustment() {
        return roundingAdjustment;
    }

    public void setRoundingAdjustment(double roundingAdjustment) {
        this.roundingAdjustment = roundingAdjustment;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public Object getUseraccountId() {
        return useraccountId;
    }

    public void setUseraccountId(Object useraccountId) {
        this.useraccountId = useraccountId;
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

    public Object getGstNo() {
        return gstNo;
    }

    public void setGstNo(Object gstNo) {
        this.gstNo = gstNo;
    }

    public Object getTranactionId() {
        return tranactionId;
    }

    public void setTranactionId(Object tranactionId) {
        this.tranactionId = tranactionId;
    }

    public String getRegisterNo() {
        return registerNo;
    }

    public void setRegisterNo(String registerNo) {
        this.registerNo = registerNo;
    }

    public String getTypePrefix() {
        return typePrefix;
    }

    public void setTypePrefix(String typePrefix) {
        this.typePrefix = typePrefix;
    }

    public String getNextref() {
        return nextref;
    }

    public void setNextref(String nextref) {
        this.nextref = nextref;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Object getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(Object invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public Object getPidate() {
        return pidate;
    }

    public void setPidate(Object pidate) {
        this.pidate = pidate;
    }

    public Object getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Object invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public double getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(double invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public Object getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(Object supplierName) {
        this.supplierName = supplierName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public double getTotalReceivable() {
        return totalReceivable;
    }

    public void setTotalReceivable(double totalReceivable) {
        this.totalReceivable = totalReceivable;
    }

    public String getSino() {
        return sino;
    }

    public void setSino(String sino) {
        this.sino = sino;
    }

    public String getSidate() {
        return sidate;
    }

    public void setSidate(String sidate) {
        this.sidate = sidate;
    }
}
