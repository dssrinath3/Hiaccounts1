
package in.hiaccounts.hinext.sales.model.printlist.payement_receipt;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PaymentRecieptDatum implements Serializable {
    @SerializedName("paymenetId")
    @Expose
    public Long paymenetId;
    @SerializedName("customerName")
    @Expose
    public String customerName;
    @SerializedName("address")
    @Expose
    public Object address;
    @SerializedName("email")
    @Expose
    public Object email;
    @SerializedName("phoneNumber1")
    @Expose
    public Object phoneNumber1;
    @SerializedName("voucherPayment")
    @Expose
    public Object voucherPayment;
    @SerializedName("cardPayment")
    @Expose
    public Object cardPayment;
    @SerializedName("totalCashPayment")
    @Expose
    public Double totalCashPayment;
    @SerializedName("totalVoucherPayment")
    @Expose
    public Double totalVoucherPayment;
    @SerializedName("totalBankPayment")
    @Expose
    public Double totalBankPayment;
    @SerializedName("totalCardPayment")
    @Expose
    public Double totalCardPayment;
    @SerializedName("totalAmount")
    @Expose
    public Double totalAmount;
    @SerializedName("duplicatePrint")
    @Expose
    public Object duplicatePrint;
    @SerializedName("count")
    @Expose
    public Long count;
    @SerializedName("totalTaxAmount")
    @Expose
    public Double totalTaxAmount;
    @SerializedName("roundingAdjustment")
    @Expose
    public Double roundingAdjustment;
    @SerializedName("locationId")
    @Expose
    public Object locationId;
    @SerializedName("useraccount_id")
    @Expose
    public Object useraccountId;
    @SerializedName("companyName")
    @Expose
    public Object companyName;
    @SerializedName("company_no")
    @Expose
    public Object companyNo;
    @SerializedName("gstNo")
    @Expose
    public Object gstNo;
    @SerializedName("tranactionId")
    @Expose
    public Object tranactionId;
    @SerializedName("registerNo")
    @Expose
    public Object registerNo;
    @SerializedName("typePrefix")
    @Expose
    public Object typePrefix;
    @SerializedName("nextref")
    @Expose
    public Object nextref;
    @SerializedName("fileName")
    @Expose
    public Object fileName;
    @SerializedName("phoneNo")
    @Expose
    public Object phoneNo;
    @SerializedName("paymentNo")
    @Expose
    public String paymentNo;
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
    public Double invoiceAmount;
    @SerializedName("supplierName")
    @Expose
    public Object supplierName;
    @SerializedName("balance")
    @Expose
    public Double balance;
    @SerializedName("amountPaid")
    @Expose
    public Double amountPaid;
    @SerializedName("sino")
    @Expose
    public String sino;
    @SerializedName("sidate")
    @Expose
    public Object sidate;
    @SerializedName("totalReceivable")
    @Expose
    public Double totalReceivable;

    public Long getPaymenetId() {
        return paymenetId;
    }

    public void setPaymenetId(Long paymenetId) {
        this.paymenetId = paymenetId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public Object getPhoneNumber1() {
        return phoneNumber1;
    }

    public void setPhoneNumber1(Object phoneNumber1) {
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

    public Double getTotalCashPayment() {
        return totalCashPayment;
    }

    public void setTotalCashPayment(Double totalCashPayment) {
        this.totalCashPayment = totalCashPayment;
    }

    public Double getTotalVoucherPayment() {
        return totalVoucherPayment;
    }

    public void setTotalVoucherPayment(Double totalVoucherPayment) {
        this.totalVoucherPayment = totalVoucherPayment;
    }

    public Double getTotalBankPayment() {
        return totalBankPayment;
    }

    public void setTotalBankPayment(Double totalBankPayment) {
        this.totalBankPayment = totalBankPayment;
    }

    public Double getTotalCardPayment() {
        return totalCardPayment;
    }

    public void setTotalCardPayment(Double totalCardPayment) {
        this.totalCardPayment = totalCardPayment;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Object getDuplicatePrint() {
        return duplicatePrint;
    }

    public void setDuplicatePrint(Object duplicatePrint) {
        this.duplicatePrint = duplicatePrint;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Double getTotalTaxAmount() {
        return totalTaxAmount;
    }

    public void setTotalTaxAmount(Double totalTaxAmount) {
        this.totalTaxAmount = totalTaxAmount;
    }

    public Double getRoundingAdjustment() {
        return roundingAdjustment;
    }

    public void setRoundingAdjustment(Double roundingAdjustment) {
        this.roundingAdjustment = roundingAdjustment;
    }

    public Object getLocationId() {
        return locationId;
    }

    public void setLocationId(Object locationId) {
        this.locationId = locationId;
    }

    public Object getUseraccountId() {
        return useraccountId;
    }

    public void setUseraccountId(Object useraccountId) {
        this.useraccountId = useraccountId;
    }

    public Object getCompanyName() {
        return companyName;
    }

    public void setCompanyName(Object companyName) {
        this.companyName = companyName;
    }

    public Object getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(Object companyNo) {
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

    public Object getRegisterNo() {
        return registerNo;
    }

    public void setRegisterNo(Object registerNo) {
        this.registerNo = registerNo;
    }

    public Object getTypePrefix() {
        return typePrefix;
    }

    public void setTypePrefix(Object typePrefix) {
        this.typePrefix = typePrefix;
    }

    public Object getNextref() {
        return nextref;
    }

    public void setNextref(Object nextref) {
        this.nextref = nextref;
    }

    public Object getFileName() {
        return fileName;
    }

    public void setFileName(Object fileName) {
        this.fileName = fileName;
    }

    public Object getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(Object phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo;
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

    public Double getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(Double invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public Object getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(Object supplierName) {
        this.supplierName = supplierName;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getSino() {
        return sino;
    }

    public void setSino(String sino) {
        this.sino = sino;
    }

    public Object getSidate() {
        return sidate;
    }

    public void setSidate(Object sidate) {
        this.sidate = sidate;
    }

    public Double getTotalReceivable() {
        return totalReceivable;
    }

    public void setTotalReceivable(Double totalReceivable) {
        this.totalReceivable = totalReceivable;
    }
}
