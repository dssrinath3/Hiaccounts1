
package in.hiaccounts.hinext.purchase.model.purchase_saveresponses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class PiData implements Serializable {

    @SerializedName("message")
    @Expose
    public Object message;
    @SerializedName("status")
    @Expose
    public long status;
    @SerializedName("selectedItemsList")
    @Expose
    public List<SelectedItemsList> selectedItemsList = null;
    @SerializedName("taxSummaryList")
    @Expose
    public List<TaxSummaryList> taxSummaryList = null;
    @SerializedName("operation")
    @Expose
    public String operation;
    @SerializedName("cashPayment")
    @Expose
    public CashPayment cashPayment;
    @SerializedName("creditPayment")
    @Expose
    public Object creditPayment;
    @SerializedName("voucherPayment")
    @Expose
    public Object voucherPayment;
    @SerializedName("multiPayment")
    @Expose
    public Object multiPayment;
    @SerializedName("hiPosServiceCharge")
    @Expose
    public double hiPosServiceCharge;
    @SerializedName("paymentType")
    @Expose
    public String paymentType;
    @SerializedName("totalCheckOutamt")
    @Expose
    public double totalCheckOutamt;
    @SerializedName("totalTaxAmt")
    @Expose
    public double totalTaxAmt;
    @SerializedName("itemCount")
    @Expose
    public long itemCount;
    @SerializedName("supplierId")
    @Expose
    public long supplierId;
    @SerializedName("taxType")
    @Expose
    public String taxType;
    @SerializedName("apBalance")
    @Expose
    public double apBalance;
    @SerializedName("piid")
    @Expose
    public long piid;
    @SerializedName("supplierEmail")
    @Expose
    public String supplierEmail;
    @SerializedName("totalServiceCharge")
    @Expose
    public double totalServiceCharge;
    @SerializedName("piNo")
    @Expose
    public String piNo;
    @SerializedName("prlnNo")
    @Expose
    public Object prlnNo;
    @SerializedName("supplierName")
    @Expose
    public String supplierName;
    @SerializedName("companydupData")
    @Expose
    public Object companydupData;
    @SerializedName("companyData")
    @Expose
    public Object companyData;
    @SerializedName("balanceAmount")
    @Expose
    public double balanceAmount;
    @SerializedName("supplier")
    @Expose
    public Object supplier;
    @SerializedName("discountAmount")
    @Expose
    public double discountAmount;
    @SerializedName("totalTenderedAmount")
    @Expose
    public double totalTenderedAmount;
    @SerializedName("userName")
    @Expose
    public Object userName;
    @SerializedName("printType")
    @Expose
    public String printType;
    @SerializedName("billToAddress")
    @Expose
    public Object billToAddress;
    @SerializedName("shipToAddress")
    @Expose
    public String shipToAddress;
    @SerializedName("email")
    @Expose
    public Object email;
    @SerializedName("phoneNumber")
    @Expose
    public Object phoneNumber;
    @SerializedName("inventoryAddress")
    @Expose
    public Object inventoryAddress;
    @SerializedName("inventoryContactNo")
    @Expose
    public String inventoryContactNo;
    @SerializedName("inventoryEmail")
    @Expose
    public String inventoryEmail;
    @SerializedName("advancepayment")
    @Expose
    public Object advancepayment;
    @SerializedName("supplierInvNo")
    @Expose
    public String supplierInvNo;
    @SerializedName("from_reg")
    @Expose
    public Object fromReg;
    @SerializedName("to_reg")
    @Expose
    public Object toReg;
    @SerializedName("user_id")
    @Expose
    public Object userId;
    @SerializedName("type_doc")
    @Expose
    public Object typeDoc;
    @SerializedName("type_flag")
    @Expose
    public Object typeFlag;
    @SerializedName("cmpyLoc")
    @Expose
    public String cmpyLoc;
    @SerializedName("suppLoc")
    @Expose
    public Object suppLoc;
    @SerializedName("termsId")
    @Expose
    public long termsId;
    @SerializedName("dateOfInvoice")
    @Expose
    public String dateOfInvoice;
    @SerializedName("cessTotalTaxAmt")
    @Expose
    public double cessTotalTaxAmt;
    @SerializedName("amountReturned")
    @Expose
    public double amountReturned;
    @SerializedName("selfBuildInvoice")
    @Expose
    public Object selfBuildInvoice;
    @SerializedName("supplierAddress")
    @Expose
    public String supplierAddress;
    @SerializedName("supplierState")
    @Expose
    public String supplierState;
    @SerializedName("supplierGst")
    @Expose
    public String supplierGst;
    @SerializedName("locationAddress")
    @Expose
    public Object locationAddress;
    @SerializedName("locationState")
    @Expose
    public String locationState;
    @SerializedName("locationGst")
    @Expose
    public Object locationGst;
    @SerializedName("locationName")
    @Expose
    public Object locationName;
    @SerializedName("locationSupplierName")
    @Expose
    public String locationSupplierName;

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
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

    public Object getCreditPayment() {
        return creditPayment;
    }

    public void setCreditPayment(Object creditPayment) {
        this.creditPayment = creditPayment;
    }

    public Object getVoucherPayment() {
        return voucherPayment;
    }

    public void setVoucherPayment(Object voucherPayment) {
        this.voucherPayment = voucherPayment;
    }

    public Object getMultiPayment() {
        return multiPayment;
    }

    public void setMultiPayment(Object multiPayment) {
        this.multiPayment = multiPayment;
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

    public long getItemCount() {
        return itemCount;
    }

    public void setItemCount(long itemCount) {
        this.itemCount = itemCount;
    }

    public long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(long supplierId) {
        this.supplierId = supplierId;
    }

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public double getApBalance() {
        return apBalance;
    }

    public void setApBalance(double apBalance) {
        this.apBalance = apBalance;
    }

    public long getPiid() {
        return piid;
    }

    public void setPiid(long piid) {
        this.piid = piid;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    public double getTotalServiceCharge() {
        return totalServiceCharge;
    }

    public void setTotalServiceCharge(double totalServiceCharge) {
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

    public Object getCompanydupData() {
        return companydupData;
    }

    public void setCompanydupData(Object companydupData) {
        this.companydupData = companydupData;
    }

    public Object getCompanyData() {
        return companyData;
    }

    public void setCompanyData(Object companyData) {
        this.companyData = companyData;
    }

    public double getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(double balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public Object getSupplier() {
        return supplier;
    }

    public void setSupplier(Object supplier) {
        this.supplier = supplier;
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

    public String getShipToAddress() {
        return shipToAddress;
    }

    public void setShipToAddress(String shipToAddress) {
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

    public String getInventoryEmail() {
        return inventoryEmail;
    }

    public void setInventoryEmail(String inventoryEmail) {
        this.inventoryEmail = inventoryEmail;
    }

    public Object getAdvancepayment() {
        return advancepayment;
    }

    public void setAdvancepayment(Object advancepayment) {
        this.advancepayment = advancepayment;
    }

    public String getSupplierInvNo() {
        return supplierInvNo;
    }

    public void setSupplierInvNo(String supplierInvNo) {
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

    public String getCmpyLoc() {
        return cmpyLoc;
    }

    public void setCmpyLoc(String cmpyLoc) {
        this.cmpyLoc = cmpyLoc;
    }

    public Object getSuppLoc() {
        return suppLoc;
    }

    public void setSuppLoc(Object suppLoc) {
        this.suppLoc = suppLoc;
    }

    public long getTermsId() {
        return termsId;
    }

    public void setTermsId(long termsId) {
        this.termsId = termsId;
    }

    public String getDateOfInvoice() {
        return dateOfInvoice;
    }

    public void setDateOfInvoice(String dateOfInvoice) {
        this.dateOfInvoice = dateOfInvoice;
    }

    public double getCessTotalTaxAmt() {
        return cessTotalTaxAmt;
    }

    public void setCessTotalTaxAmt(double cessTotalTaxAmt) {
        this.cessTotalTaxAmt = cessTotalTaxAmt;
    }

    public double getAmountReturned() {
        return amountReturned;
    }

    public void setAmountReturned(double amountReturned) {
        this.amountReturned = amountReturned;
    }

    public Object getSelfBuildInvoice() {
        return selfBuildInvoice;
    }

    public void setSelfBuildInvoice(Object selfBuildInvoice) {
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

    public Object getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(Object locationAddress) {
        this.locationAddress = locationAddress;
    }

    public String getLocationState() {
        return locationState;
    }

    public void setLocationState(String locationState) {
        this.locationState = locationState;
    }

    public Object getLocationGst() {
        return locationGst;
    }

    public void setLocationGst(Object locationGst) {
        this.locationGst = locationGst;
    }

    public Object getLocationName() {
        return locationName;
    }

    public void setLocationName(Object locationName) {
        this.locationName = locationName;
    }

    public String getLocationSupplierName() {
        return locationSupplierName;
    }

    public void setLocationSupplierName(String locationSupplierName) {
        this.locationSupplierName = locationSupplierName;
    }
}
