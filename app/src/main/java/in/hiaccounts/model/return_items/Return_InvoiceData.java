package in.hiaccounts.model.return_items;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Return_InvoiceData implements Serializable {

    @SerializedName("creditPayment")
    @Expose
    private Object creditPayment;
    @SerializedName("cashPayment")
    @Expose
    private Object cashPayment;
    @SerializedName("totalServiceCharge")
    @Expose
    private int totalServiceCharge;
    @SerializedName("siNo")
    @Expose
    private Object siNo;
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("selectedItemsList")
    @Expose
    private List<SelectedItemsList> selectedItemsList = null;
    @SerializedName("paymentType")
    @Expose
    private Object paymentType;
    @SerializedName("customerEmail")
    @Expose
    private Object customerEmail;
    @SerializedName("cutomerName")
    @Expose
    private Object cutomerName;
    @SerializedName("message")
    @Expose
    private Object message;
    @SerializedName("voucherPayment")
    @Expose
    private Object voucherPayment;
    @SerializedName("operation")
    @Expose
    private String operation;
    @SerializedName("taxSummaryList")
    @Expose
    private List<Object> taxSummaryList = null;
    @SerializedName("customerId")
    @Expose
    private int customerId;
    @SerializedName("totalTaxAmt")
    @Expose
    private double totalTaxAmt;
    @SerializedName("taxType")
    @Expose
    private Object taxType;
    @SerializedName("siid")
    @Expose
    private int siid;
    @SerializedName("totalCheckOutamt")
    @Expose
    private float totalCheckOutamt;
    @SerializedName("multiPayment")
    @Expose
    private Object multiPayment;
    @SerializedName("itemCount")
    @Expose
    private int itemCount;
    @SerializedName("hiPosServiceCharge")
    @Expose
    private int hiPosServiceCharge;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Return_InvoiceData() {
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
     * @param customerEmail
     * @param cutomerName
     * @param message
     * @param voucherPayment
     * @param operation
     * @param taxSummaryList
     * @param customerId
     * @param siid
     * @param taxType
     * @param totalTaxAmt
     * @param multiPayment
     * @param totalCheckOutamt
     * @param itemCount
     * @param hiPosServiceCharge
     */
    public Return_InvoiceData(Object creditPayment, Object cashPayment, int totalServiceCharge, Object siNo, int status, List<SelectedItemsList> selectedItemsList, Object paymentType, Object customerEmail, Object cutomerName, Object message, Object voucherPayment, String operation, List<Object> taxSummaryList, int customerId, double totalTaxAmt, Object taxType, int siid, float totalCheckOutamt, Object multiPayment, int itemCount, int hiPosServiceCharge) {
        super();
        this.creditPayment = creditPayment;
        this.cashPayment = cashPayment;
        this.totalServiceCharge = totalServiceCharge;
        this.siNo = siNo;
        this.status = status;
        this.selectedItemsList = selectedItemsList;
        this.paymentType = paymentType;
        this.customerEmail = customerEmail;
        this.cutomerName = cutomerName;
        this.message = message;
        this.voucherPayment = voucherPayment;
        this.operation = operation;
        this.taxSummaryList = taxSummaryList;
        this.customerId = customerId;
        this.totalTaxAmt = totalTaxAmt;
        this.taxType = taxType;
        this.siid = siid;
        this.totalCheckOutamt = totalCheckOutamt;
        this.multiPayment = multiPayment;
        this.itemCount = itemCount;
        this.hiPosServiceCharge = hiPosServiceCharge;
    }

    public Object getCreditPayment() {
        return creditPayment;
    }

    public void setCreditPayment(Object creditPayment) {
        this.creditPayment = creditPayment;
    }

    public Object getCashPayment() {
        return cashPayment;
    }

    public void setCashPayment(Object cashPayment) {
        this.cashPayment = cashPayment;
    }

    public int getTotalServiceCharge() {
        return totalServiceCharge;
    }

    public void setTotalServiceCharge(int totalServiceCharge) {
        this.totalServiceCharge = totalServiceCharge;
    }

    public Object getSiNo() {
        return siNo;
    }

    public void setSiNo(Object siNo) {
        this.siNo = siNo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<SelectedItemsList> getSelectedItemsList() {
        return selectedItemsList;
    }

    public void setSelectedItemsList(List<SelectedItemsList> selectedItemsList) {
        this.selectedItemsList = selectedItemsList;
    }

    public Object getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Object paymentType) {
        this.paymentType = paymentType;
    }

    public Object getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(Object customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Object getCutomerName() {
        return cutomerName;
    }

    public void setCutomerName(Object cutomerName) {
        this.cutomerName = cutomerName;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public Object getVoucherPayment() {
        return voucherPayment;
    }

    public void setVoucherPayment(Object voucherPayment) {
        this.voucherPayment = voucherPayment;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public List<Object> getTaxSummaryList() {
        return taxSummaryList;
    }

    public void setTaxSummaryList(List<Object> taxSummaryList) {
        this.taxSummaryList = taxSummaryList;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getTotalTaxAmt() {
        return totalTaxAmt;
    }

    public void setTotalTaxAmt(double totalTaxAmt) {
        this.totalTaxAmt = totalTaxAmt;
    }

    public Object getTaxType() {
        return taxType;
    }

    public void setTaxType(Object taxType) {
        this.taxType = taxType;
    }

    public int getSiid() {
        return siid;
    }

    public void setSiid(int siid) {
        this.siid = siid;
    }

    public float getTotalCheckOutamt() {
        return totalCheckOutamt;
    }

    public void setTotalCheckOutamt(float totalCheckOutamt) {
        this.totalCheckOutamt = totalCheckOutamt;
    }

    public Object getMultiPayment() {
        return multiPayment;
    }

    public void setMultiPayment(Object multiPayment) {
        this.multiPayment = multiPayment;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public int getHiPosServiceCharge() {
        return hiPosServiceCharge;
    }

    public void setHiPosServiceCharge(int hiPosServiceCharge) {
        this.hiPosServiceCharge = hiPosServiceCharge;
    }

}
