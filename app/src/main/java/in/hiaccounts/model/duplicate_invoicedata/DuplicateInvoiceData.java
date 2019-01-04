
package in.hiaccounts.model.duplicate_invoicedata;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DuplicateInvoiceData implements Serializable
{

    @SerializedName("selectedItemsList")
    @Expose
    private List<SelectedItemsList> selectedItemsList = null;
    @SerializedName("voucherPayment")
    @Expose
    private VoucherPayment voucherPayment;
    @SerializedName("discountAmount")
    @Expose
    private double discountAmount;
    @SerializedName("itemCount")
    @Expose
    private long itemCount;
    @SerializedName("operation")
    @Expose
    private String operation;
    @SerializedName("companyData")
    @Expose
    private CompanyData companyData;
    @SerializedName("customerEmail")
    @Expose
    private String customerEmail;
    @SerializedName("hiPosServiceCharge")
    @Expose
    private double hiPosServiceCharge;
    @SerializedName("totalServiceCharge")
    @Expose
    private double totalServiceCharge;
    @SerializedName("amountReturned")
    @Expose
    private double amountReturned;
    @SerializedName("totalTenderedAmount")
    @Expose
    private double totalTenderedAmount;
    @SerializedName("totalCheckOutamt")
    @Expose
    private double totalCheckOutamt;
    @SerializedName("companydupData")
    @Expose
    private CompanydupData companydupData;
    @SerializedName("taxSummaryList")
    @Expose
    private List<TaxSummaryList> taxSummaryList = null;
    @SerializedName("taxType")
    @Expose
    private String taxType;
    @SerializedName("siid")
    @Expose
    private long siid;
    @SerializedName("customerId")
    @Expose
    private long customerId;
    @SerializedName("balanceAmount")
    @Expose
    private double balanceAmount;
    @SerializedName("totalTaxAmt")
    @Expose
    private double totalTaxAmt;
    @SerializedName("cashPayment")
    @Expose
    private CashPayment cashPayment;
    @SerializedName("cutomerName")
    @Expose
    private String cutomerName;
    @SerializedName("siNo")
    @Expose
    private long siNo;
    @SerializedName("paymentType")
    @Expose
    private String paymentType;
    @SerializedName("multiPayment")
    @Expose
    private String multiPayment;
    @SerializedName("creditPayment")
    @Expose
    private CreditPayment creditPayment;
    @SerializedName("srlnNo")
    @Expose
    private String srlnNo;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private long status;
    private final static long serialVersionUID = 5572472036372775822L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public DuplicateInvoiceData() {
    }

    /**
     *
     * @param creditPayment
     * @param cashPayment
     * @param siNo
     * @param totalTenderedAmount
     * @param selectedItemsList
     * @param customerEmail
     * @param discountAmount
     * @param cutomerName
     * @param operation
     * @param taxSummaryList
     * @param customerId
     * @param balanceAmount
     * @param totalTaxAmt
     * @param totalCheckOutamt
     * @param multiPayment
     * @param companyData
     * @param totalServiceCharge
     * @param status
     * @param srlnNo
     * @param paymentType
     * @param companydupData
     * @param message
     * @param voucherPayment
     * @param amountReturned
     * @param taxType
     * @param siid
     * @param itemCount
     * @param hiPosServiceCharge
     */

    public DuplicateInvoiceData(List<SelectedItemsList> selectedItemsList, VoucherPayment voucherPayment, double discountAmount, long itemCount, String operation, CompanyData companyData, String customerEmail, double hiPosServiceCharge, double totalServiceCharge, double amountReturned, double totalTenderedAmount, double totalCheckOutamt, CompanydupData companydupData, List<TaxSummaryList> taxSummaryList, String taxType, long siid, long customerId, double balanceAmount, double totalTaxAmt, CashPayment cashPayment, String cutomerName, long siNo, String paymentType, String multiPayment, CreditPayment creditPayment, String srlnNo, String message, long status) {
        this.selectedItemsList = selectedItemsList;
        this.voucherPayment = voucherPayment;
        this.discountAmount = discountAmount;
        this.itemCount = itemCount;
        this.operation = operation;
        this.companyData = companyData;
        this.customerEmail = customerEmail;
        this.hiPosServiceCharge = hiPosServiceCharge;
        this.totalServiceCharge = totalServiceCharge;
        this.amountReturned = amountReturned;
        this.totalTenderedAmount = totalTenderedAmount;
        this.totalCheckOutamt = totalCheckOutamt;
        this.companydupData = companydupData;
        this.taxSummaryList = taxSummaryList;
        this.taxType = taxType;
        this.siid = siid;
        this.customerId = customerId;
        this.balanceAmount = balanceAmount;
        this.totalTaxAmt = totalTaxAmt;
        this.cashPayment = cashPayment;
        this.cutomerName = cutomerName;
        this.siNo = siNo;
        this.paymentType = paymentType;
        this.multiPayment = multiPayment;
        this.creditPayment = creditPayment;
        this.srlnNo = srlnNo;
        this.message = message;
        this.status = status;
    }

    public List<SelectedItemsList> getSelectedItemsList() {
        return selectedItemsList;
    }

    public void setSelectedItemsList(List<SelectedItemsList> selectedItemsList) {
        this.selectedItemsList = selectedItemsList;
    }

    public VoucherPayment getVoucherPayment() {
        return voucherPayment;
    }

    public void setVoucherPayment(VoucherPayment voucherPayment) {
        this.voucherPayment = voucherPayment;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public long getItemCount() {
        return itemCount;
    }

    public void setItemCount(long itemCount) {
        this.itemCount = itemCount;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public CompanyData getCompanyData() {
        return companyData;
    }

    public void setCompanyData(CompanyData companyData) {
        this.companyData = companyData;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public double getHiPosServiceCharge() {
        return hiPosServiceCharge;
    }

    public void setHiPosServiceCharge(double hiPosServiceCharge) {
        this.hiPosServiceCharge = hiPosServiceCharge;
    }

    public double getTotalServiceCharge() {
        return totalServiceCharge;
    }

    public void setTotalServiceCharge(double totalServiceCharge) {
        this.totalServiceCharge = totalServiceCharge;
    }

    public double getAmountReturned() {
        return amountReturned;
    }

    public void setAmountReturned(double amountReturned) {
        this.amountReturned = amountReturned;
    }

    public double getTotalTenderedAmount() {
        return totalTenderedAmount;
    }

    public void setTotalTenderedAmount(double totalTenderedAmount) {
        this.totalTenderedAmount = totalTenderedAmount;
    }

    public double getTotalCheckOutamt() {
        return totalCheckOutamt;
    }

    public void setTotalCheckOutamt(double totalCheckOutamt) {
        this.totalCheckOutamt = totalCheckOutamt;
    }

    public CompanydupData getCompanydupData() {
        return companydupData;
    }

    public void setCompanydupData(CompanydupData companydupData) {
        this.companydupData = companydupData;
    }

    public List<TaxSummaryList> getTaxSummaryList() {
        return taxSummaryList;
    }

    public void setTaxSummaryList(List<TaxSummaryList> taxSummaryList) {
        this.taxSummaryList = taxSummaryList;
    }

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public long getSiid() {
        return siid;
    }

    public void setSiid(long siid) {
        this.siid = siid;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public double getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(double balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public double getTotalTaxAmt() {
        return totalTaxAmt;
    }

    public void setTotalTaxAmt(double totalTaxAmt) {
        this.totalTaxAmt = totalTaxAmt;
    }

    public CashPayment getCashPayment() {
        return cashPayment;
    }

    public void setCashPayment(CashPayment cashPayment) {
        this.cashPayment = cashPayment;
    }

    public String getCutomerName() {
        return cutomerName;
    }

    public void setCutomerName(String cutomerName) {
        this.cutomerName = cutomerName;
    }

    public long getSiNo() {
        return siNo;
    }

    public void setSiNo(long siNo) {
        this.siNo = siNo;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getMultiPayment() {
        return multiPayment;
    }

    public void setMultiPayment(String multiPayment) {
        this.multiPayment = multiPayment;
    }

    public CreditPayment getCreditPayment() {
        return creditPayment;
    }

    public void setCreditPayment(CreditPayment creditPayment) {
        this.creditPayment = creditPayment;
    }

    public String getSrlnNo() {
        return srlnNo;
    }

    public void setSrlnNo(String srlnNo) {
        this.srlnNo = srlnNo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }
}
