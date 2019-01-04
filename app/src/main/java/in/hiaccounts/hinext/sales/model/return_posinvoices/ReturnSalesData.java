
package in.hiaccounts.hinext.sales.model.return_posinvoices;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import in.hiaccounts.hinext.sales.model.checkout.SelectedItemsList;

public class ReturnSalesData {
    @SerializedName("dateOfInvoice")
    @Expose
    private String dateOfInvoice;
    @SerializedName("cessTotalTaxAmt")
    @Expose
    private String cessTotalTaxAmt;
    @SerializedName("totalRemaininBalance")
    @Expose
    private double totalRemaininBalance;


    @SerializedName("cmpyLocation")
    @Expose
    private Long cmpyLocation;
    @SerializedName("custLocation")
    @Expose
    private Long custLocation;
    @SerializedName("returnReason")
    @Expose
    private String returnReason;

    @SerializedName("memo")
    @Expose
    private String memo;
    @SerializedName("chequeNumber")
    @Expose
    private String chequeNumber;
    @SerializedName("voucherNumber")
    @Expose
    private String voucherNumber;


    @SerializedName("siid")
    @Expose
    private long siid;
    @SerializedName("operation")
    @Expose
    private String operation;
    @SerializedName("selectedItemsList")
    @Expose
    private List<SelectedItemsList> selectedItemsList = null;
    @SerializedName("totalCheckOutamt")
    @Expose
    private double totalCheckOutamt;
    @SerializedName("paymentType")
    @Expose
    private String paymentType;
    @SerializedName("totalTaxAmt")
    @Expose
    private double totalTaxAmt;
    @SerializedName("taxType")
    @Expose
    private String taxType;
    @SerializedName("customerId")
    @Expose
    private long customerId;
    @SerializedName("formNo")
    @Expose
    private String formNo;
    @SerializedName("totalCashPymtAmtReturned")
    @Expose
    private double totalCashPymtAmtReturned;

    @SerializedName("totalChequePymtAmtReturned")
    @Expose
    private double totalChequePymtAmtReturned;

    @SerializedName("totalVoucherPymtAmtReturned")
    @Expose
    private double totalVoucherPymtAmtReturned;

    public long getSiid() {
        return siid;
    }

    public void setSiid(long siid) {
        this.siid = siid;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public List<SelectedItemsList> getSelectedItemsList() {
        return selectedItemsList;
    }

    public void setSelectedItemsList(List<SelectedItemsList> selectedItemsList) {
        this.selectedItemsList = selectedItemsList;
    }

    public double getTotalCheckOutamt() {
        return totalCheckOutamt;
    }

    public void setTotalCheckOutamt(double totalCheckOutamt) {
        this.totalCheckOutamt = totalCheckOutamt;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
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

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getFormNo() {
        return formNo;
    }

    public void setFormNo(String formNo) {
        this.formNo = formNo;
    }

    public double getTotalCashPymtAmtReturned() {
        return totalCashPymtAmtReturned;
    }

    public void setTotalCashPymtAmtReturned(double totalCashPymtAmtReturned) {
        this.totalCashPymtAmtReturned = totalCashPymtAmtReturned;
    }

    public double getTotalChequePymtAmtReturned() {
        return totalChequePymtAmtReturned;
    }

    public void setTotalChequePymtAmtReturned(double totalChequePymtAmtReturned) {
        this.totalChequePymtAmtReturned = totalChequePymtAmtReturned;
    }

    public double getTotalVoucherPymtAmtReturned() {
        return totalVoucherPymtAmtReturned;
    }

    public void setTotalVoucherPymtAmtReturned(double totalVoucherPymtAmtReturned) {
        this.totalVoucherPymtAmtReturned = totalVoucherPymtAmtReturned;
    }

    public Long getCmpyLocation() {
        return cmpyLocation;
    }

    public void setCmpyLocation(Long cmpyLocation) {
        this.cmpyLocation = cmpyLocation;
    }

    public Long getCustLocation() {
        return custLocation;
    }

    public void setCustLocation(Long custLocation) {
        this.custLocation = custLocation;
    }

    public String getReturnReason() {
        return returnReason;
    }

    public void setReturnReason(String returnReason) {
        this.returnReason = returnReason;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getChequeNumber() {
        return chequeNumber;
    }

    public void setChequeNumber(String chequeNumber) {
        this.chequeNumber = chequeNumber;
    }

    public String getVoucherNumber() {
        return voucherNumber;
    }

    public void setVoucherNumber(String voucherNumber) {
        this.voucherNumber = voucherNumber;
    }

    public String getDateOfInvoice() {
        return dateOfInvoice;
    }

    public void setDateOfInvoice(String dateOfInvoice) {
        this.dateOfInvoice = dateOfInvoice;
    }

    public String getCessTotalTaxAmt() {
        return cessTotalTaxAmt;
    }

    public void setCessTotalTaxAmt(String cessTotalTaxAmt) {
        this.cessTotalTaxAmt = cessTotalTaxAmt;
    }

    public double getTotalRemaininBalance() {
        return totalRemaininBalance;
    }

    public void setTotalRemaininBalance(double totalRemaininBalance) {
        this.totalRemaininBalance = totalRemaininBalance;
    }
}
