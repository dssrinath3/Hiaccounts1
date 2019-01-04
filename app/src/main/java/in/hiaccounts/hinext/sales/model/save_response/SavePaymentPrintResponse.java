package in.hiaccounts.hinext.sales.model.save_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 9/11/2017.
 */

public class SavePaymentPrintResponse {
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
    @SerializedName("customerName")
    @Expose
    public String customerName;
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
    @SerializedName("printType")
    @Expose
    public String printType;

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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public String getPrintType() {
        return printType;
    }

    public void setPrintType(String printType) {
        this.printType = printType;
    }
}
