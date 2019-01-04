
package in.hiaccounts.model.pos_invoice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class SiData implements Serializable {

    @SerializedName("selectedItemsList")
    @Expose
    private List<SelectedItemsList> selectedItemsList = null;
    @SerializedName("siNo")
    @Expose
    private String siNo;
    @SerializedName("cashPayment")
    @Expose
    private CashPayment cashPayment;
    @SerializedName("totalTaxAmt")
    @Expose
    private double totalTaxAmt;
    @SerializedName("paymentType")
    @Expose
    private String paymentType;
    @SerializedName("operation")
    @Expose
    private Object operation;
    @SerializedName("creditPayment")
    @Expose
    private CreditPayment creditPayment;
    @SerializedName("siid")
    @Expose
    private int siid;
    @SerializedName("voucherPayment")
    @Expose
    private VoucherPayment voucherPayment;
    @SerializedName("totalServiceCharge")
    @Expose
    private double totalServiceCharge;
    @SerializedName("hiPosServiceCharge")
    @Expose
    private double hiPosServiceCharge;
    @SerializedName("totalCheckOutamt")
    @Expose
    private double totalCheckOutamt;
    @SerializedName("taxSummaryList")
    @Expose
    private List<TaxSummaryList> taxSummaryList = null;
    @SerializedName("taxType")
    @Expose
    private String taxType;
    @SerializedName("itemCount")
    @Expose
    private int itemCount;
    @SerializedName("customerId")
    @Expose
    private int customerId;
    @SerializedName("message")
    @Expose
    private Object message;
    @SerializedName("status")
    @Expose
    private int status;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SiData() {
    }

    /**
     * 
     * @param creditPayment
     * @param cashPayment
     * @param totalServiceCharge
     * @param siNo
     * @param status
     * @param selectedItemsList
     * @param paymentType
     * @param message
     * @param operation
     * @param voucherPayment
     * @param taxSummaryList
     * @param customerId
     * @param taxType
     * @param totalTaxAmt
     * @param siid
     * @param totalCheckOutamt
     * @param itemCount
     * @param hiPosServiceCharge
     */
    public SiData(List<SelectedItemsList> selectedItemsList, String siNo, CashPayment cashPayment, double totalTaxAmt, String paymentType, Object operation, CreditPayment creditPayment, int siid, VoucherPayment voucherPayment, double totalServiceCharge, double hiPosServiceCharge, double totalCheckOutamt, List<TaxSummaryList> taxSummaryList, String taxType, int itemCount, int customerId, Object message, int status) {
        super();
        this.selectedItemsList = selectedItemsList;
        this.siNo = siNo;
        this.cashPayment = cashPayment;
        this.totalTaxAmt = totalTaxAmt;
        this.paymentType = paymentType;
        this.operation = operation;
        this.creditPayment = creditPayment;
        this.siid = siid;
        this.voucherPayment = voucherPayment;
        this.totalServiceCharge = totalServiceCharge;
        this.hiPosServiceCharge = hiPosServiceCharge;
        this.totalCheckOutamt = totalCheckOutamt;
        this.taxSummaryList = taxSummaryList;
        this.taxType = taxType;
        this.itemCount = itemCount;
        this.customerId = customerId;
        this.message = message;
        this.status = status;
    }

    public List<SelectedItemsList> getSelectedItemsList() {
        return selectedItemsList;
    }

    public void setSelectedItemsList(List<SelectedItemsList> selectedItemsList) {
        this.selectedItemsList = selectedItemsList;
    }

    public String getSiNo() {
        return siNo;
    }

    public void setSiNo(String siNo) {
        this.siNo = siNo;
    }

    public CashPayment getCashPayment() {
        return cashPayment;
    }

    public void setCashPayment(CashPayment cashPayment) {
        this.cashPayment = cashPayment;
    }

    public double getTotalTaxAmt() {
        return totalTaxAmt;
    }

    public void setTotalTaxAmt(double totalTaxAmt) {
        this.totalTaxAmt = totalTaxAmt;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Object getOperation() {
        return operation;
    }

    public void setOperation(Object operation) {
        this.operation = operation;
    }

    public CreditPayment getCreditPayment() {
        return creditPayment;
    }

    public void setCreditPayment(CreditPayment creditPayment) {
        this.creditPayment = creditPayment;
    }

    public int getSiid() {
        return siid;
    }

    public void setSiid(int siid) {
        this.siid = siid;
    }

    public VoucherPayment getVoucherPayment() {
        return voucherPayment;
    }

    public void setVoucherPayment(VoucherPayment voucherPayment) {
        this.voucherPayment = voucherPayment;
    }

    public double getTotalServiceCharge() {
        return totalServiceCharge;
    }

    public void setTotalServiceCharge(double totalServiceCharge) {
        this.totalServiceCharge = totalServiceCharge;
    }

    public double getHiPosServiceCharge() {
        return hiPosServiceCharge;
    }

    public void setHiPosServiceCharge(double hiPosServiceCharge) {
        this.hiPosServiceCharge = hiPosServiceCharge;
    }

    public double getTotalCheckOutamt() {
        return totalCheckOutamt;
    }

    public void setTotalCheckOutamt(double totalCheckOutamt) {
        this.totalCheckOutamt = totalCheckOutamt;
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

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
