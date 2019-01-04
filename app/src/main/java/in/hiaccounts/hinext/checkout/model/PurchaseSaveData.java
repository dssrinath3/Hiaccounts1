
package in.hiaccounts.hinext.checkout.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import in.hiaccounts.hinext.generaltransaction.model.response.SelectedAccountList;


public class PurchaseSaveData implements Serializable{

    @SerializedName("suppLoc")
    @Expose
    private Long suppLoc;
    @SerializedName("cmpyLoc")
    @Expose
    private Long cmpyLoc;
    @SerializedName("cmpyLocation")
    @Expose
    private Long cmpyLocation;
    @SerializedName("custLocation")
    @Expose
    private Long custLocation;
    @SerializedName("customerEmail")
    @Expose
    private String customerEmail;
    @SerializedName("customerId")
    @Expose
    private Long customerId;
    @SerializedName("siid")
    @Expose
    private Long siid;

    @SerializedName("tcsAmount")
    @Expose
    private Long tcsAmount;
    @SerializedName("tdsAmount")
    @Expose
    private Long tdsAmount;
    @SerializedName("cessTotalTaxAmt")
    @Expose
    private Double cessTotalTaxAmt;


    @SerializedName("selectedAccountList")
    @Expose
    private List<SelectedAccountList> selectedAccountList = null;

    @SerializedName("selectedItemsList")
    @Expose
    private List<PurchaseItem> purchaseItemList = null;

    @SerializedName("bankPayment")
    @Expose
    private BankPayment bankPayment;

    @SerializedName("cashPayment")
    @Expose
    private CashPayment cashPayment;
    @SerializedName("creditPayment")
    @Expose
    private CreditPayment creditPayment;
    @SerializedName("voucherPayment")
    @Expose
    private VoucherPayment voucherPayment;
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
    @SerializedName("supplierId")
    @Expose
    private long supplierId;
    @SerializedName("supplierEmail")
    @Expose
    private String supplierEmail;
    @SerializedName("typeOfInvoice")
    @Expose
    private String typeOfInvoice;

    @SerializedName("cutomerName")
    @Expose
    private String cutomerName;
    @SerializedName("amountReturned")
    @Expose
    private String amountReturned;
    @SerializedName("discountAmount")
    @Expose
    private double discountAmount;
    @SerializedName("totalTenderedAmount")
    @Expose
    private double totalTenderedAmount;
    @SerializedName("supplierInvNo")
    @Expose
    private String supplierInvNo;
    @SerializedName("advancepayment")
    @Expose
    private boolean advancepayment;
    @SerializedName("selfBuildInvoice")
    @Expose
    private boolean selfBuildInvoice;
    @SerializedName("dateOfInvoice")
    @Expose
    private String dateOfInvoice;

    @SerializedName("shippingDate")
    @Expose
    private String shippingDate;

    @SerializedName("from_reg")
    @Expose
    private String from_reg;
    @SerializedName("to_reg")
    @Expose
    private String to_reg;
    @SerializedName("memo")
    @Expose
    private String memo;

    @SerializedName("operation")
    @Expose
    private String operation;
    @SerializedName("salesOrderId")
    @Expose
    private String salesOrderId;
    @SerializedName("salesDeliveryOrderNO")
    @Expose
    private String salesDeliveryOrderNO;
    @SerializedName("exchangerateValue")
    @Expose
    private Long exchangerateValue;
    @SerializedName("currencyIdOfInvoice")
    @Expose
    private Long currencyIdOfInvoice;
    @SerializedName("exportInvoice")
    @Expose
    private Boolean exportInvoice;
    @SerializedName("purchaseQuotationId")
    @Expose
    private String purchaseQuotationId;
    @SerializedName("purchaseOrderId")
    @Expose
    private String purchaseOrderId;
    @SerializedName("currencyId")
    @Expose
    private Long currencyId;
    @SerializedName("receiveItemId")
    @Expose
    private Long receiveItemId;
    @SerializedName("deliveryOrderId")
    @Expose
    private String deliveryOrderId;
    @SerializedName("type_doc")
    @Expose
    private String typeDoc;
    @SerializedName("supplierName")
    @Expose
    private String supplierName;

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getTypeDoc() {
        return typeDoc;
    }

    public void setTypeDoc(String typeDoc) {
        this.typeDoc = typeDoc;
    }

    public String getDeliveryOrderId() {
        return deliveryOrderId;
    }

    public void setDeliveryOrderId(String deliveryOrderId) {
        this.deliveryOrderId = deliveryOrderId;
    }

    public Long getReceiveItemId() {
        return receiveItemId;
    }

    public void setReceiveItemId(Long receiveItemId) {
        this.receiveItemId = receiveItemId;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public String getPurchaseQuotationId() {
        return purchaseQuotationId;
    }

    public void setPurchaseQuotationId(String purchaseQuotationId) {
        this.purchaseQuotationId = purchaseQuotationId;
    }

    public String getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(String purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public List<SelectedAccountList> getSelectedAccountList() {
        return selectedAccountList;
    }

    public void setSelectedAccountList(List<SelectedAccountList> selectedAccountList) {
        this.selectedAccountList = selectedAccountList;
    }

    public Long getExchangerateValue() {
        return exchangerateValue;
    }

    public void setExchangerateValue(Long exchangerateValue) {
        this.exchangerateValue = exchangerateValue;
    }

    public Long getCurrencyIdOfInvoice() {
        return currencyIdOfInvoice;
    }

    public void setCurrencyIdOfInvoice(Long currencyIdOfInvoice) {
        this.currencyIdOfInvoice = currencyIdOfInvoice;
    }

    public Boolean getExportInvoice() {
        return exportInvoice;
    }

    public void setExportInvoice(Boolean exportInvoice) {
        this.exportInvoice = exportInvoice;
    }

    public String getSalesDeliveryOrderNO() {
        return salesDeliveryOrderNO;
    }

    public void setSalesDeliveryOrderNO(String salesDeliveryOrderNO) {
        this.salesDeliveryOrderNO = salesDeliveryOrderNO;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getSalesOrderId() {
        return salesOrderId;
    }

    public void setSalesOrderId(String salesOrderId) {
        this.salesOrderId = salesOrderId;
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

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getSiid() {
        return siid;
    }

    public void setSiid(Long siid) {
        this.siid = siid;
    }

    public Long getTcsAmount() {
        return tcsAmount;
    }

    public void setTcsAmount(Long tcsAmount) {
        this.tcsAmount = tcsAmount;
    }

    public Long getTdsAmount() {
        return tdsAmount;
    }

    public void setTdsAmount(Long tdsAmount) {
        this.tdsAmount = tdsAmount;
    }

    public String getTypeOfInvoice() {
        return typeOfInvoice;
    }

    public void setTypeOfInvoice(String typeOfInvoice) {
        this.typeOfInvoice = typeOfInvoice;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getFrom_reg() {
        return from_reg;
    }

    public void setFrom_reg(String from_reg) {
        this.from_reg = from_reg;
    }

    public String getTo_reg() {
        return to_reg;
    }

    public void setTo_reg(String to_reg) {
        this.to_reg = to_reg;
    }

    public List<PurchaseItem> getPurchaseItemList() {
        return purchaseItemList;
    }

    public void setPurchaseItemList(List<PurchaseItem> purchaseItemList) {
        this.purchaseItemList = purchaseItemList;
    }

    public CashPayment getCashPayment() {
        return cashPayment;
    }

    public void setCashPayment(CashPayment cashPayment) {
        this.cashPayment = cashPayment;
    }

    public CreditPayment getCreditPayment() {
        return creditPayment;
    }

    public void setCreditPayment(CreditPayment creditPayment) {
        this.creditPayment = creditPayment;
    }

    public VoucherPayment getVoucherPayment() {
        return voucherPayment;
    }

    public void setVoucherPayment(VoucherPayment voucherPayment) {
        this.voucherPayment = voucherPayment;
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

    public String getCutomerName() {
        return cutomerName;
    }

    public void setCutomerName(String cutomerName) {
        this.cutomerName = cutomerName;
    }

    public String getAmountReturned() {
        return amountReturned;
    }

    public void setAmountReturned(String amountReturned) {
        this.amountReturned = amountReturned;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public double getTotalTenderedAmount() {
        return totalTenderedAmount;
    }

    public void setTotalTenderedAmount(double totalTenderedAmount) {
        this.totalTenderedAmount = totalTenderedAmount;
    }

    public String getSupplierInvNo() {
        return supplierInvNo;
    }

    public void setSupplierInvNo(String supplierInvNo) {
        this.supplierInvNo = supplierInvNo;
    }

    public boolean isAdvancepayment() {
        return advancepayment;
    }

    public void setAdvancepayment(boolean advancepayment) {
        this.advancepayment = advancepayment;
    }

    public boolean isSelfBuildInvoice() {
        return selfBuildInvoice;
    }

    public void setSelfBuildInvoice(boolean selfBuildInvoice) {
        this.selfBuildInvoice = selfBuildInvoice;
    }

    public String getDateOfInvoice() {
        return dateOfInvoice;
    }

    public void setDateOfInvoice(String dateOfInvoice) {
        this.dateOfInvoice = dateOfInvoice;
    }

    public String getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(String shippingDate) {
        this.shippingDate = shippingDate;
    }

    public Double getCessTotalTaxAmt() {
        return cessTotalTaxAmt;
    }

    public void setCessTotalTaxAmt(Double cessTotalTaxAmt) {
        this.cessTotalTaxAmt = cessTotalTaxAmt;
    }

    public Long getCmpyLoc() {
        return cmpyLoc;
    }

    public void setCmpyLoc(Long cmpyLoc) {
        this.cmpyLoc = cmpyLoc;
    }

    public Long getSuppLoc() {
        return suppLoc;
    }

    public void setSuppLoc(Long suppLoc) {
        this.suppLoc = suppLoc;
    }

    public BankPayment getBankPayment() {
        return bankPayment;
    }

    public void setBankPayment(BankPayment bankPayment) {
        this.bankPayment = bankPayment;
    }
}
