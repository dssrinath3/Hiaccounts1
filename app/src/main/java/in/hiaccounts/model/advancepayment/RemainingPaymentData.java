
package in.hiaccounts.model.advancepayment;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RemainingPaymentData implements Serializable
{

    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("companyName")
    @Expose
    private String companyName;
    @SerializedName("customerName")
    @Expose
    private String customerName;
    @SerializedName("invoiceAmount")
    @Expose
    private double invoiceAmount;
    @SerializedName("companyNo")
    @Expose
    private String companyNo;
    @SerializedName("cashAmount")
    @Expose
    private double cashAmount;
    @SerializedName("voucherAmount")
    @Expose
    private double voucherAmount;
    @SerializedName("tranactionId")
    @Expose
    private String tranactionId;
    @SerializedName("phoneNo")
    @Expose
    private String phoneNo;
    @SerializedName("gstNo")
    @Expose
    private String gstNo;
    @SerializedName("invoiceNo")
    @Expose
    private String invoiceNo;
    @SerializedName("cardAmount")
    @Expose
    private double cardAmount;
    @SerializedName("invoiceDate")
    @Expose
    private String invoiceDate;
    @SerializedName("balanceAmount")
    @Expose
    private double balanceAmount;
    @SerializedName("amountPaid")
    @Expose
    private double amountPaid;
    @SerializedName("email")
    @Expose
    private String email;

    /**
     * No args constructor for use in serialization
     * 
     */
    public RemainingPaymentData() {
    }

    /**
     * 
     * @param customerName
     * @param invoiceDate
     * @param invoiceAmount
     * @param gstNo
     * @param companyNo
     * @param companyName
     * @param invoiceNo
     * @param voucherAmount
     * @param phoneNo
     * @param tranactionId
     * @param cardAmount
     * @param email
     * @param address
     * @param balanceAmount
     * @param amountPaid
     * @param cashAmount
     */
    public RemainingPaymentData(String address, String companyName, String customerName, double invoiceAmount, String companyNo, double cashAmount, double voucherAmount, String tranactionId, String phoneNo, String gstNo, String invoiceNo, double cardAmount, String invoiceDate, double balanceAmount, double amountPaid, String email) {
        this.address = address;
        this.companyName = companyName;
        this.customerName = customerName;
        this.invoiceAmount = invoiceAmount;
        this.companyNo = companyNo;
        this.cashAmount = cashAmount;
        this.voucherAmount = voucherAmount;
        this.tranactionId = tranactionId;
        this.phoneNo = phoneNo;
        this.gstNo = gstNo;
        this.invoiceNo = invoiceNo;
        this.cardAmount = cardAmount;
        this.invoiceDate = invoiceDate;
        this.balanceAmount = balanceAmount;
        this.amountPaid = amountPaid;
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(double invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public double getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(double cashAmount) {
        this.cashAmount = cashAmount;
    }

    public double getVoucherAmount() {
        return voucherAmount;
    }

    public void setVoucherAmount(double voucherAmount) {
        this.voucherAmount = voucherAmount;
    }

    public String getTranactionId() {
        return tranactionId;
    }

    public void setTranactionId(String tranactionId) {
        this.tranactionId = tranactionId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getGstNo() {
        return gstNo;
    }

    public void setGstNo(String gstNo) {
        this.gstNo = gstNo;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public double getCardAmount() {
        return cardAmount;
    }

    public void setCardAmount(double cardAmount) {
        this.cardAmount = cardAmount;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public double getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(double balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
