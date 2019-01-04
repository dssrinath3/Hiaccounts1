package in.hiaccounts.hinext.purchase.model.purchase_purchaseorder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
/**
 * Created by administrator on 28/12/17.
 */

public class PurchaseOrderData implements Serializable {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("selectedItemsList")
    @Expose
    private List<PurchaseInvokeOrderData> selectedItemsList = null;

    @SerializedName("operation")
    @Expose
    private String operation;
    @SerializedName("cashPayment")
    @Expose
    private Double cashPayment;
    @SerializedName("creditPayment")
    @Expose
    private Double creditPayment;
    @SerializedName("voucherPayment")
    @Expose
    private Double voucherPayment;
    @SerializedName("multiPayment")
    @Expose
    private String multiPayment;
    @SerializedName("bankPayment")
    @Expose
    private Double bankPayment;
    @SerializedName("hiPosServiceCharge")
    @Expose
    private Double hiPosServiceCharge;
    @SerializedName("paymentType")
    @Expose
    private String paymentType;
    @SerializedName("totalCheckOutamt")
    @Expose
    private Double totalCheckOutamt;
    @SerializedName("totalTaxAmt")
    @Expose
    private Double totalTaxAmt;
    @SerializedName("itemCount")
    @Expose
    private Double itemCount;
    @SerializedName("supplierId")
    @Expose
    private Long supplierId;
    @SerializedName("agentId")
    @Expose
    private Long agentId;
    @SerializedName("exchangeRateId")
    @Expose
    private Long exchangeRateId;
    @SerializedName("taxType")
    @Expose
    private String taxType;
    @SerializedName("piStatus")
    @Expose
    private String piStatus;
    @SerializedName("memo")
    @Expose
    private String memo;
    @SerializedName("apBalance")
    @Expose
    private Long apBalance;
    @SerializedName("piid")
    @Expose
    private Long piid;
    @SerializedName("supplierEmail")
    @Expose
    private String supplierEmail;
    @SerializedName("totalServiceCharge")
    @Expose
    private Long totalServiceCharge;
    @SerializedName("piNo")
    @Expose
    private Long piNo;
    @SerializedName("prlnNo")
    @Expose
    private Long prlnNo;
    @SerializedName("supplierName")
    @Expose
    private String supplierName;
    @SerializedName("discountAmount")
    @Expose
    private Double discountAmount;
    @SerializedName("totalTenderedAmount")
    @Expose
    private Double totalTenderedAmount;
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("printType")
    @Expose
    private String printType;
    @SerializedName("billToAddress")
    @Expose
    private String billToAddress;
    @SerializedName("shipToAddress")
    @Expose
    private String shipToAddress;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("inventoryAddress")
    @Expose
    private String inventoryAddress;
    @SerializedName("inventoryContactNo")
    @Expose
    private String inventoryContactNo;
    @SerializedName("inventoryEmail")
    @Expose
    private String inventoryEmail;
    @SerializedName("advancepayment")
    @Expose
    private String advancepayment;
    @SerializedName("supplierInvNo")
    @Expose
    private String supplierInvNo;
    @SerializedName("from_reg")
    @Expose
    private String fromReg;
    @SerializedName("to_reg")
    @Expose
    private String toReg;
    @SerializedName("user_id")
    @Expose
    private Long userId;
    @SerializedName("type_doc")
    @Expose
    private String typeDoc;
    @SerializedName("type_flag")
    @Expose
    private String typeFlag;
    @SerializedName("cmpyLoc")
    @Expose
    private String cmpyLoc;
    @SerializedName("suppLoc")
    @Expose
    private String suppLoc;
    @SerializedName("termsId")
    @Expose
    private Long termsId;
    @SerializedName("dateOfInvoice")
    @Expose
    private String dateOfInvoice;
    @SerializedName("cessTotalTaxAmt")
    @Expose
    private String cessTotalTaxAmt;
    @SerializedName("amountReturned")
    @Expose
    private String amountReturned;
    @SerializedName("selfBuildInvoice")
    @Expose
    private String selfBuildInvoice;
    @SerializedName("supplierAddress")
    @Expose
    private String supplierAddress;
    @SerializedName("supplierState")
    @Expose
    private String supplierState;
    @SerializedName("supplierGst")
    @Expose
    private String supplierGst;
    @SerializedName("locationAddress")
    @Expose
    private String locationAddress;
    @SerializedName("locationState")
    @Expose
    private String locationState;
    @SerializedName("locationGst")
    @Expose
    private String locationGst;
    @SerializedName("locationName")
    @Expose
    private String locationName;
    @SerializedName("locationSupplierName")
    @Expose
    private String locationSupplierName;
    @SerializedName("agentName")
    @Expose
    private String agentName;

    @SerializedName("tc_value")
    @Expose
    private String tcValue;
    @SerializedName("shippingmethodId")
    @Expose
    private Long shippingmethodId;
    @SerializedName("exchangerateId")
    @Expose
    private Long exchangerateId;
    @SerializedName("exchangerateValue")
    @Expose
    private String exchangerateValue;
    @SerializedName("currencyId")
    @Expose
    private String currencyId;

    @SerializedName("shippingDate")
    @Expose
    private String shippingDate;
    @SerializedName("shippingReferenceNo")
    @Expose
    private String shippingReferenceNo;
    @SerializedName("referenceNo")
    @Expose
    private String referenceNo;
    @SerializedName("projectId")
    @Expose
    private Long projectId;
    @SerializedName("termsandConditionsId")
    @Expose
    private Long termsandConditionsId;
    @SerializedName("purchaseQuotationId")
    @Expose
    private Long purchaseQuotationId;
    @SerializedName("purchaseQuotationDetailsId")
    @Expose
    private Long purchaseQuotationDetailsId;
    @SerializedName("otherContactId")
    @Expose
    private Long otherContactId;
    @SerializedName("poId")
    @Expose
    private Long poId;
    @SerializedName("purchaseOrderId")
    @Expose
    private Long purchaseOrderId;
    @SerializedName("receiveItemId")
    @Expose
    private Long receiveItemId;
    @SerializedName("invoiceDate")
    @Expose
    private String invoiceDate;
    @SerializedName("invoiceAmt")
    @Expose
    private Double invoiceAmt;
    @SerializedName("incrementAmt")
    @Expose
    private Double incrementAmt;
    @SerializedName("employeeId")
    @Expose
    private Long employeeId;
    @SerializedName("invoiceNo")
    @Expose
    private String invoiceNo;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("tcsAmount")
    @Expose
    private Long tcsAmount;
    @SerializedName("tdsAmount")
    @Expose
    private Double tdsAmount;




    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<PurchaseInvokeOrderData> getSelectedItemsList() {
        return selectedItemsList;
    }

    public void setSelectedItemsList(List<PurchaseInvokeOrderData> selectedItemsList) {
        this.selectedItemsList = selectedItemsList;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public double getCashPayment() {
        return cashPayment;
    }

    public void setCashPayment(double cashPayment) {
        this.cashPayment = cashPayment;
    }

    public double getCreditPayment() {
        return creditPayment;
    }

    public void setCreditPayment(double creditPayment) {
        this.creditPayment = creditPayment;
    }

    public double getVoucherPayment() {
        return voucherPayment;
    }

    public void setVoucherPayment(double voucherPayment) {
        this.voucherPayment = voucherPayment;
    }

    public String getMultiPayment() {
        return multiPayment;
    }

    public void setMultiPayment(String multiPayment) {
        this.multiPayment = multiPayment;
    }

    public double getBankPayment() {
        return bankPayment;
    }

    public void setBankPayment(double bankPayment) {
        this.bankPayment = bankPayment;
    }

    public double getHiPosServiceCharge() {
        return hiPosServiceCharge;
    }

    public void setHiPosServiceCharge(double hiPosServiceCharge) {
        this.hiPosServiceCharge = hiPosServiceCharge;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public double getTotalCheckOutamt() {
        return totalCheckOutamt;
    }

    public void setTotalCheckOutamt(double totalCheckOutamt) {
        this.totalCheckOutamt = totalCheckOutamt;
    }

    public double getTotalTaxAmt() {
        return totalTaxAmt;
    }

    public void setTotalTaxAmt(double totalTaxAmt) {
        this.totalTaxAmt = totalTaxAmt;
    }


    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public Long getExchangeRateId() {
        return exchangeRateId;
    }

    public void setExchangeRateId(Long exchangeRateId) {
        this.exchangeRateId = exchangeRateId;
    }

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public String getPiStatus() {
        return piStatus;
    }

    public void setPiStatus(String piStatus) {
        this.piStatus = piStatus;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Long getApBalance() {
        return apBalance;
    }

    public void setApBalance(Long apBalance) {
        this.apBalance = apBalance;
    }

    public Long getPiid() {
        return piid;
    }

    public void setPiid(Long piid) {
        this.piid = piid;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    public Long getTotalServiceCharge() {
        return totalServiceCharge;
    }

    public void setTotalServiceCharge(Long totalServiceCharge) {
        this.totalServiceCharge = totalServiceCharge;
    }

    public Long getPiNo() {
        return piNo;
    }

    public void setPiNo(Long piNo) {
        this.piNo = piNo;
    }

    public Long getPrlnNo() {
        return prlnNo;
    }

    public void setPrlnNo(Long prlnNo) {
        this.prlnNo = prlnNo;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPrintType() {
        return printType;
    }

    public void setPrintType(String printType) {
        this.printType = printType;
    }

    public String getBillToAddress() {
        return billToAddress;
    }

    public void setBillToAddress(String billToAddress) {
        this.billToAddress = billToAddress;
    }

    public String getShipToAddress() {
        return shipToAddress;
    }

    public void setShipToAddress(String shipToAddress) {
        this.shipToAddress = shipToAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getInventoryAddress() {
        return inventoryAddress;
    }

    public void setInventoryAddress(String inventoryAddress) {
        this.inventoryAddress = inventoryAddress;
    }

    public String getInventoryContactNo() {
        return inventoryContactNo;
    }

    public void setInventoryContactNo(String inventoryContactNo) {
        this.inventoryContactNo = inventoryContactNo;
    }

    public String getInventoryEmail() {
        return inventoryEmail;
    }

    public void setInventoryEmail(String inventoryEmail) {
        this.inventoryEmail = inventoryEmail;
    }

    public String getAdvancepayment() {
        return advancepayment;
    }

    public void setAdvancepayment(String advancepayment) {
        this.advancepayment = advancepayment;
    }

    public String getSupplierInvNo() {
        return supplierInvNo;
    }

    public void setSupplierInvNo(String supplierInvNo) {
        this.supplierInvNo = supplierInvNo;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTypeDoc() {
        return typeDoc;
    }

    public void setTypeDoc(String typeDoc) {
        this.typeDoc = typeDoc;
    }

    public String getTypeFlag() {
        return typeFlag;
    }

    public void setTypeFlag(String typeFlag) {
        this.typeFlag = typeFlag;
    }

    public String getCmpyLoc() {
        return cmpyLoc;
    }

    public void setCmpyLoc(String cmpyLoc) {
        this.cmpyLoc = cmpyLoc;
    }

    public String getSuppLoc() {
        return suppLoc;
    }

    public void setSuppLoc(String suppLoc) {
        this.suppLoc = suppLoc;
    }

    public Long getTermsId() {
        return termsId;
    }

    public void setTermsId(Long termsId) {
        this.termsId = termsId;
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

    public String getAmountReturned() {
        return amountReturned;
    }

    public void setAmountReturned(String amountReturned) {
        this.amountReturned = amountReturned;
    }

    public String getSelfBuildInvoice() {
        return selfBuildInvoice;
    }

    public void setSelfBuildInvoice(String selfBuildInvoice) {
        this.selfBuildInvoice = selfBuildInvoice;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    public String getSupplierState() {
        return supplierState;
    }

    public void setSupplierState(String supplierState) {
        this.supplierState = supplierState;
    }

    public String getSupplierGst() {
        return supplierGst;
    }

    public void setSupplierGst(String supplierGst) {
        this.supplierGst = supplierGst;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
    }

    public String getLocationState() {
        return locationState;
    }

    public void setLocationState(String locationState) {
        this.locationState = locationState;
    }

    public String getLocationGst() {
        return locationGst;
    }

    public void setLocationGst(String locationGst) {
        this.locationGst = locationGst;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationSupplierName() {
        return locationSupplierName;
    }

    public void setLocationSupplierName(String locationSupplierName) {
        this.locationSupplierName = locationSupplierName;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getExchangerateValue() {
        return exchangerateValue;
    }

    public void setExchangerateValue(String exchangerateValue) {
        this.exchangerateValue = exchangerateValue;
    }

    public String getTcValue() {
        return tcValue;
    }

    public void setTcValue(String tcValue) {
        this.tcValue = tcValue;
    }

    public Long getShippingmethodId() {
        return shippingmethodId;
    }

    public void setShippingmethodId(Long shippingmethodId) {
        this.shippingmethodId = shippingmethodId;
    }

    public Long getExchangerateId() {
        return exchangerateId;
    }

    public void setExchangerateId(Long exchangerateId) {
        this.exchangerateId = exchangerateId;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public String getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(String shippingDate) {
        this.shippingDate = shippingDate;
    }

    public String getShippingReferenceNo() {
        return shippingReferenceNo;
    }

    public void setShippingReferenceNo(String shippingReferenceNo) {
        this.shippingReferenceNo = shippingReferenceNo;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getTermsandConditionsId() {
        return termsandConditionsId;
    }

    public void setTermsandConditionsId(Long termsandConditionsId) {
        this.termsandConditionsId = termsandConditionsId;
    }

    public Long getPurchaseQuotationId() {
        return purchaseQuotationId;
    }

    public void setPurchaseQuotationId(Long purchaseQuotationId) {
        this.purchaseQuotationId = purchaseQuotationId;
    }

    public Long getPurchaseQuotationDetailsId() {
        return purchaseQuotationDetailsId;
    }

    public void setPurchaseQuotationDetailsId(Long purchaseQuotationDetailsId) {
        this.purchaseQuotationDetailsId = purchaseQuotationDetailsId;
    }

    public Long getOtherContactId() {
        return otherContactId;
    }

    public void setOtherContactId(Long otherContactId) {
        this.otherContactId = otherContactId;
    }

    public Long getPoId() {
        return poId;
    }

    public void setPoId(Long poId) {
        this.poId = poId;
    }

    public Long getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(Long purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public Long getReceiveItemId() {
        return receiveItemId;
    }

    public void setReceiveItemId(Long receiveItemId) {
        this.receiveItemId = receiveItemId;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public double getInvoiceAmt() {
        return invoiceAmt;
    }

    public void setInvoiceAmt(double invoiceAmt) {
        this.invoiceAmt = invoiceAmt;
    }

    public double getIncrementAmt() {
        return incrementAmt;
    }

    public void setIncrementAmt(double incrementAmt) {
        this.incrementAmt = incrementAmt;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getTcsAmount() {
        return tcsAmount;
    }

    public void setTcsAmount(Long tcsAmount) {
        this.tcsAmount = tcsAmount;
    }

    public double getTdsAmount() {
        return tdsAmount;
    }

    public void setTdsAmount(double tdsAmount) {
        this.tdsAmount = tdsAmount;
    }

    public Double getItemCount() {
        return itemCount;
    }

    public void setItemCount(Double itemCount) {
        this.itemCount = itemCount;
    }
}
