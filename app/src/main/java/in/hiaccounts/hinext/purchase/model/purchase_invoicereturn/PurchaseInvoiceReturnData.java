
package in.hiaccounts.hinext.purchase.model.purchase_invoicereturn;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import in.hiaccounts.hinext.purchase.model.purchase_pos.SelectedItemsList;

public class PurchaseInvoiceReturnData implements Serializable{

    @SerializedName("message")
    @Expose
    private Object message;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("selectedItemsList")
    @Expose
    private List<SelectedItemsList> selectedItemsList = null;
    @SerializedName("taxSummaryList")
    @Expose
    private List<TaxSummaryList> taxSummaryList = null;
    @SerializedName("operation")
    @Expose
    private String operation;
    @SerializedName("cashPayment")
    @Expose
    private CashPayment cashPayment;
    @SerializedName("creditPayment")
    @Expose
    private CreditPayment creditPayment;
    @SerializedName("voucherPayment")
    @Expose
    private VoucherPayment voucherPayment;
    @SerializedName("multiPayment")
    @Expose
    private Object multiPayment;
    @SerializedName("hiPosServiceCharge")
    @Expose
    private Double hiPosServiceCharge;
    @SerializedName("paymentType")
    @Expose
    private Object paymentType;
    @SerializedName("totalCheckOutamt")
    @Expose
    private Double totalCheckOutamt;
    @SerializedName("totalTaxAmt")
    @Expose
    private Double totalTaxAmt;
    @SerializedName("itemCount")
    @Expose
    private Integer itemCount;
    @SerializedName("supplierId")
    @Expose
    private Integer supplierId;
    @SerializedName("taxType")
    @Expose
    private String taxType;
    @SerializedName("apBalance")
    @Expose
    private Double apBalance;
    @SerializedName("piid")
    @Expose
    private Integer piid;
    @SerializedName("supplierEmail")
    @Expose
    private Object supplierEmail;
    @SerializedName("totalServiceCharge")
    @Expose
    private Double totalServiceCharge;
    @SerializedName("piNo")
    @Expose
    private String piNo;
    @SerializedName("prlnNo")
    @Expose
    private Object prlnNo;
    @SerializedName("supplierName")
    @Expose
    private String supplierName;
    @SerializedName("companydupData")
    @Expose
    private CompanydupData companydupData;
    @SerializedName("companyData")
    @Expose
    private CompanyData companyData;
    @SerializedName("balanceAmount")
    @Expose
    private Double balanceAmount;
    @SerializedName("supplier")
    @Expose
    private Object supplier;
    @SerializedName("discountAmount")
    @Expose
    private Double discountAmount;
    @SerializedName("totalTenderedAmount")
    @Expose
    private Double totalTenderedAmount;
    @SerializedName("userName")
    @Expose
    private Object userName;
    @SerializedName("printType")
    @Expose
    private String printType;
    @SerializedName("billToAddress")
    @Expose
    private Object billToAddress;
    @SerializedName("shipToAddress")
    @Expose
    private Object shipToAddress;
    @SerializedName("email")
    @Expose
    private Object email;
    @SerializedName("phoneNumber")
    @Expose
    private Object phoneNumber;
    @SerializedName("inventoryAddress")
    @Expose
    private Object inventoryAddress;
    @SerializedName("inventoryContactNo")
    @Expose
    private String inventoryContactNo;
    @SerializedName("inventoryEmail")
    @Expose
    private Object inventoryEmail;
    @SerializedName("advancepayment")
    @Expose
    private Object advancepayment;
    @SerializedName("supplierInvNo")
    @Expose
    private Object supplierInvNo;
    @SerializedName("from_reg")
    @Expose
    private Object fromReg;
    @SerializedName("to_reg")
    @Expose
    private Object toReg;
    @SerializedName("user_id")
    @Expose
    private Object userId;
    @SerializedName("type_doc")
    @Expose
    private Object typeDoc;
    @SerializedName("type_flag")
    @Expose
    private Object typeFlag;
    @SerializedName("cmpyLoc")
    @Expose
    private Object cmpyLoc;
    @SerializedName("suppLoc")
    @Expose
    private Object suppLoc;
    @SerializedName("termsId")
    @Expose
    private Integer termsId;
    @SerializedName("dateOfInvoice")
    @Expose
    private Object dateOfInvoice;
    @SerializedName("cessTotalTaxAmt")
    @Expose
    private Double cessTotalTaxAmt;
    @SerializedName("amountReturned")
    @Expose
    private Double amountReturned;
    @SerializedName("selfBuildInvoice")
    @Expose
    private String selfBuildInvoice;
    @SerializedName("supplierAddress")
    @Expose
    private Object supplierAddress;
    @SerializedName("supplierState")
    @Expose
    private Object supplierState;
    @SerializedName("supplierGst")
    @Expose
    private Object supplierGst;
    @SerializedName("locationAddress")
    @Expose
    private Object locationAddress;
    @SerializedName("locationState")
    @Expose
    private Object locationState;
    @SerializedName("locationGst")
    @Expose
    private Object locationGst;

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<SelectedItemsList> getSelectedItemsList() {
        return selectedItemsList;
    }

    public void setSelectedItemsList(List<SelectedItemsList> selectedItemsList) {
        this.selectedItemsList = selectedItemsList;
    }

    public List<TaxSummaryList> getTaxSummaryList() {
        return taxSummaryList;
    }

    public void setTaxSummaryList(List<TaxSummaryList> taxSummaryList) {
        this.taxSummaryList = taxSummaryList;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
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

    public Object getMultiPayment() {
        return multiPayment;
    }

    public void setMultiPayment(Object multiPayment) {
        this.multiPayment = multiPayment;
    }

    public Double getHiPosServiceCharge() {
        return hiPosServiceCharge;
    }

    public void setHiPosServiceCharge(Double hiPosServiceCharge) {
        this.hiPosServiceCharge = hiPosServiceCharge;
    }

    public Object getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Object paymentType) {
        this.paymentType = paymentType;
    }

    public Double getTotalCheckOutamt() {
        return totalCheckOutamt;
    }

    public void setTotalCheckOutamt(Double totalCheckOutamt) {
        this.totalCheckOutamt = totalCheckOutamt;
    }

    public Double getTotalTaxAmt() {
        return totalTaxAmt;
    }

    public void setTotalTaxAmt(Double totalTaxAmt) {
        this.totalTaxAmt = totalTaxAmt;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public Double getApBalance() {
        return apBalance;
    }

    public void setApBalance(Double apBalance) {
        this.apBalance = apBalance;
    }

    public Integer getPiid() {
        return piid;
    }

    public void setPiid(Integer piid) {
        this.piid = piid;
    }

    public Object getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(Object supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    public Double getTotalServiceCharge() {
        return totalServiceCharge;
    }

    public void setTotalServiceCharge(Double totalServiceCharge) {
        this.totalServiceCharge = totalServiceCharge;
    }

    public String getPiNo() {
        return piNo;
    }

    public void setPiNo(String piNo) {
        this.piNo = piNo;
    }

    public Object getPrlnNo() {
        return prlnNo;
    }

    public void setPrlnNo(Object prlnNo) {
        this.prlnNo = prlnNo;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public CompanydupData getCompanydupData() {
        return companydupData;
    }

    public void setCompanydupData(CompanydupData companydupData) {
        this.companydupData = companydupData;
    }

    public CompanyData getCompanyData() {
        return companyData;
    }

    public void setCompanyData(CompanyData companyData) {
        this.companyData = companyData;
    }

    public Double getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(Double balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public Object getSupplier() {
        return supplier;
    }

    public void setSupplier(Object supplier) {
        this.supplier = supplier;
    }

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Double getTotalTenderedAmount() {
        return totalTenderedAmount;
    }

    public void setTotalTenderedAmount(Double totalTenderedAmount) {
        this.totalTenderedAmount = totalTenderedAmount;
    }

    public Object getUserName() {
        return userName;
    }

    public void setUserName(Object userName) {
        this.userName = userName;
    }

    public String getPrintType() {
        return printType;
    }

    public void setPrintType(String printType) {
        this.printType = printType;
    }

    public Object getBillToAddress() {
        return billToAddress;
    }

    public void setBillToAddress(Object billToAddress) {
        this.billToAddress = billToAddress;
    }

    public Object getShipToAddress() {
        return shipToAddress;
    }

    public void setShipToAddress(Object shipToAddress) {
        this.shipToAddress = shipToAddress;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public Object getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Object phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Object getInventoryAddress() {
        return inventoryAddress;
    }

    public void setInventoryAddress(Object inventoryAddress) {
        this.inventoryAddress = inventoryAddress;
    }

    public String getInventoryContactNo() {
        return inventoryContactNo;
    }

    public void setInventoryContactNo(String inventoryContactNo) {
        this.inventoryContactNo = inventoryContactNo;
    }

    public Object getInventoryEmail() {
        return inventoryEmail;
    }

    public void setInventoryEmail(Object inventoryEmail) {
        this.inventoryEmail = inventoryEmail;
    }

    public Object getAdvancepayment() {
        return advancepayment;
    }

    public void setAdvancepayment(Object advancepayment) {
        this.advancepayment = advancepayment;
    }

    public Object getSupplierInvNo() {
        return supplierInvNo;
    }

    public void setSupplierInvNo(Object supplierInvNo) {
        this.supplierInvNo = supplierInvNo;
    }

    public Object getFromReg() {
        return fromReg;
    }

    public void setFromReg(Object fromReg) {
        this.fromReg = fromReg;
    }

    public Object getToReg() {
        return toReg;
    }

    public void setToReg(Object toReg) {
        this.toReg = toReg;
    }

    public Object getUserId() {
        return userId;
    }

    public void setUserId(Object userId) {
        this.userId = userId;
    }

    public Object getTypeDoc() {
        return typeDoc;
    }

    public void setTypeDoc(Object typeDoc) {
        this.typeDoc = typeDoc;
    }

    public Object getTypeFlag() {
        return typeFlag;
    }

    public void setTypeFlag(Object typeFlag) {
        this.typeFlag = typeFlag;
    }

    public Object getCmpyLoc() {
        return cmpyLoc;
    }

    public void setCmpyLoc(Object cmpyLoc) {
        this.cmpyLoc = cmpyLoc;
    }

    public Object getSuppLoc() {
        return suppLoc;
    }

    public void setSuppLoc(Object suppLoc) {
        this.suppLoc = suppLoc;
    }

    public Integer getTermsId() {
        return termsId;
    }

    public void setTermsId(Integer termsId) {
        this.termsId = termsId;
    }

    public Object getDateOfInvoice() {
        return dateOfInvoice;
    }

    public void setDateOfInvoice(Object dateOfInvoice) {
        this.dateOfInvoice = dateOfInvoice;
    }

    public Double getCessTotalTaxAmt() {
        return cessTotalTaxAmt;
    }

    public void setCessTotalTaxAmt(Double cessTotalTaxAmt) {
        this.cessTotalTaxAmt = cessTotalTaxAmt;
    }

    public Double getAmountReturned() {
        return amountReturned;
    }

    public void setAmountReturned(Double amountReturned) {
        this.amountReturned = amountReturned;
    }

    public String getSelfBuildInvoice() {
        return selfBuildInvoice;
    }

    public void setSelfBuildInvoice(String selfBuildInvoice) {
        this.selfBuildInvoice = selfBuildInvoice;
    }

    public Object getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(Object supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    public Object getSupplierState() {
        return supplierState;
    }

    public void setSupplierState(Object supplierState) {
        this.supplierState = supplierState;
    }

    public Object getSupplierGst() {
        return supplierGst;
    }

    public void setSupplierGst(Object supplierGst) {
        this.supplierGst = supplierGst;
    }

    public Object getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(Object locationAddress) {
        this.locationAddress = locationAddress;
    }

    public Object getLocationState() {
        return locationState;
    }

    public void setLocationState(Object locationState) {
        this.locationState = locationState;
    }

    public Object getLocationGst() {
        return locationGst;
    }

    public void setLocationGst(Object locationGst) {
        this.locationGst = locationGst;
    }

}
