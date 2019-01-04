package in.hiaccounts.hinext.purchase.model.purchase_purchaseorder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Admin on 7/29/2017.
 */

public class PurchaseOrderSave {

    @SerializedName("operation")
    @Expose
    private String operation;
    @SerializedName("piid")
    @Expose
    private Integer piid;
    @SerializedName("invoiceNo")
    @Expose
    private String invoiceNo;

    @SerializedName("receiveItemId")
    @Expose
    private Long receiveItemId;

    @SerializedName("exchangerateValue")
    @Expose
    private Long exchangerateValue;
    @SerializedName("currencyId")
    @Expose
    private Long currencyId;

    @SerializedName("selectedItemsList")
    @Expose
    private List<PurchaseOrderItem> selectedItemsList = null;
    @SerializedName("totalCheckOutamt")
    @Expose
    private double totalCheckOutamt;
    @SerializedName("paymentType")
    @Expose
    private String paymentType;
    @SerializedName("totalTaxAmt")
    @Expose
    private double totalTaxAmt;
    @SerializedName("cessTotalTaxAmt")
    @Expose
    private String cessTotalTaxAmt;
    @SerializedName("taxType")
    @Expose
    private String taxType;
    @SerializedName("supplierId")
    @Expose
    private long supplierId;
    @SerializedName("supplierEmail")
    @Expose
    private String supplierEmail;
    @SerializedName("supplierInvNo")
    @Expose
    private String supplierInvNo;
    @SerializedName("supplierName")
    @Expose
    private String supplierName;
    @SerializedName("from_reg")
    @Expose
    private String fromReg;
    @SerializedName("to_reg")
    @Expose
    private String toReg;
    @SerializedName("type_doc")
    @Expose
    private String typeDoc;
    @SerializedName("dateOfInvoice")
    @Expose
    private String dateOfInvoice;
    @SerializedName("shippingDate")
    @Expose
    private String shippingDate;

    @SerializedName("suppLoc")
    @Expose
    private Long suppLoc;
    @SerializedName("cmpyLoc")
    @Expose
    private Long cmpyLoc;
    @SerializedName("purchaseQuotationId")
    @Expose
    private Long purchaseQuotationId;
    @SerializedName("typeOfInvoice")
    @Expose
    private String typeOfInvoice;

    public String getTypeOfInvoice() {
        return typeOfInvoice;
    }

    public void setTypeOfInvoice(String typeOfInvoice) {
        this.typeOfInvoice = typeOfInvoice;
    }

    public Long getExchangerateValue() {
        return exchangerateValue;
    }

    public void setExchangerateValue(Long exchangerateValue) {
        this.exchangerateValue = exchangerateValue;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(String shippingDate) {
        this.shippingDate = shippingDate;
    }

    public Long getPurchaseQuotationId() {
        return purchaseQuotationId;
    }

    public void setPurchaseQuotationId(Long purchaseQuotationId) {
        this.purchaseQuotationId = purchaseQuotationId;
    }

    public Long getReceiveItemId() {
        return receiveItemId;
    }

    public void setReceiveItemId(Long receiveItemId) {
        this.receiveItemId = receiveItemId;
    }

    public Integer getPiid() {
        return piid;
    }

    public void setPiid(Integer piid) {
        this.piid = piid;
    }

    public String getOperation() {
        return operation;
    }




    public Long getSuppLoc() {
        return suppLoc;
    }

    public void setSuppLoc(Long suppLoc) {
        this.suppLoc = suppLoc;
    }

    public Long getCmpyLoc() {
        return cmpyLoc;
    }

    public void setCmpyLoc(Long cmpyLoc) {
        this.cmpyLoc = cmpyLoc;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public List<PurchaseOrderItem> getSelectedItemsList() {
        return selectedItemsList;
    }

    public void setSelectedItemsList(List<PurchaseOrderItem> selectedItemsList) {
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

    public String getCessTotalTaxAmt() {
        return cessTotalTaxAmt;
    }

    public void setCessTotalTaxAmt(String cessTotalTaxAmt) {
        this.cessTotalTaxAmt = cessTotalTaxAmt;
    }

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    public String getSupplierInvNo() {
        return supplierInvNo;
    }

    public void setSupplierInvNo(String supplierInvNo) {
        this.supplierInvNo = supplierInvNo;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getFromReg() {
        return fromReg;
    }

    public void setFromReg(String fromReg) {
        this.fromReg = fromReg;
    }

    public String getToReg() {
        return toReg;
    }

    public void setToReg(String toReg) {
        this.toReg = toReg;
    }

    public String getTypeDoc() {
        return typeDoc;
    }

    public void setTypeDoc(String typeDoc) {
        this.typeDoc = typeDoc;
    }

    public String getDateOfInvoice() {
        return dateOfInvoice;
    }

    public void setDateOfInvoice(String dateOfInvoice) {
        this.dateOfInvoice = dateOfInvoice;
    }
}
