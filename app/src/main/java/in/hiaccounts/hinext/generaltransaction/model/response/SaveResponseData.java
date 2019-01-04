
package in.hiaccounts.hinext.generaltransaction.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SaveResponseData {

    @SerializedName("selectedAccountList")
    @Expose
    public List<SelectedAccountList> selectedAccountList = null;
    @SerializedName("taxSummaryList")
    @Expose
    public List<Object> taxSummaryList = null;
    @SerializedName("operation")
    @Expose
    public Object operation;
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
    public Double hiPosServiceCharge;
    @SerializedName("paymentType")
    @Expose
    public String paymentType;
    @SerializedName("totalCheckOutamt")
    @Expose
    public Double totalCheckOutamt;
    @SerializedName("totalTaxAmt")
    @Expose
    public Double totalTaxAmt;
    @SerializedName("itemCount")
    @Expose
    public Integer itemCount;
    @SerializedName("supplierId")
    @Expose
    public Integer supplierId;
    @SerializedName("taxType")
    @Expose
    public String taxType;
    @SerializedName("piid")
    @Expose
    public Integer piid;
    @SerializedName("supplierEmail")
    @Expose
    public String supplierEmail;
    @SerializedName("totalServiceCharge")
    @Expose
    public Double totalServiceCharge;
    @SerializedName("piNo")
    @Expose
    public Object piNo;
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
    public CompanyData companyData;
    @SerializedName("balanceAmount")
    @Expose
    public Double balanceAmount;
    @SerializedName("supplier")
    @Expose
    public Object supplier;
    @SerializedName("discountAmount")
    @Expose
    public Double discountAmount;
    @SerializedName("totalTenderedAmount")
    @Expose
    public Double totalTenderedAmount;
    @SerializedName("userName")
    @Expose
    public Object userName;
    @SerializedName("printType")
    @Expose
    public Object printType;
    @SerializedName("billToAddress")
    @Expose
    public Object billToAddress;
    @SerializedName("shipToAddress")
    @Expose
    public Object shipToAddress;
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
    public Object inventoryContactNo;
    @SerializedName("inventoryEmail")
    @Expose
    public Object inventoryEmail;
    @SerializedName("advancepayment")
    @Expose
    public Object advancepayment;
    @SerializedName("supplierInvNo")
    @Expose
    public String supplierInvNo;
    @SerializedName("from_reg")
    @Expose
    public String fromReg;
    @SerializedName("to_reg")
    @Expose
    public String toReg;
    @SerializedName("user_id")
    @Expose
    public Object userId;
    @SerializedName("type_doc")
    @Expose
    public String typeDoc;
    @SerializedName("type_flag")
    @Expose
    public Object typeFlag;
    @SerializedName("dateOfInvoice")
    @Expose
    public String dateOfInvoice;
    @SerializedName("opNo")
    @Expose
    public String opNo;
    @SerializedName("opDate")
    @Expose
    public String opDate;
    @SerializedName("amountPaid")
    @Expose
    public Double amountPaid;

    @SerializedName("orNo")
    @Expose
    public String orNo;

    @SerializedName("cutomerName")
    @Expose
    public String customerName;

    public List<SelectedAccountList> getSelectedAccountList() {
        return selectedAccountList;
    }

    public void setSelectedAccountList(List<SelectedAccountList> selectedAccountList) {
        this.selectedAccountList = selectedAccountList;
    }

    public List<Object> getTaxSummaryList() {
        return taxSummaryList;
    }

    public void setTaxSummaryList(List<Object> taxSummaryList) {
        this.taxSummaryList = taxSummaryList;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getOrNo() {
        return orNo;
    }

    public void setOrNo(String orNo) {
        this.orNo = orNo;
    }

    public Object getOperation() {
        return operation;
    }

    public void setOperation(Object operation) {
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

    public Double getHiPosServiceCharge() {
        return hiPosServiceCharge;
    }

    public void setHiPosServiceCharge(Double hiPosServiceCharge) {
        this.hiPosServiceCharge = hiPosServiceCharge;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
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

    public Integer getPiid() {
        return piid;
    }

    public void setPiid(Integer piid) {
        this.piid = piid;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    public Double getTotalServiceCharge() {
        return totalServiceCharge;
    }

    public void setTotalServiceCharge(Double totalServiceCharge) {
        this.totalServiceCharge = totalServiceCharge;
    }

    public Object getPiNo() {
        return piNo;
    }

    public void setPiNo(Object piNo) {
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

    public Object getPrintType() {
        return printType;
    }

    public void setPrintType(Object printType) {
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

    public Object getInventoryContactNo() {
        return inventoryContactNo;
    }

    public void setInventoryContactNo(Object inventoryContactNo) {
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

    public Object getUserId() {
        return userId;
    }

    public void setUserId(Object userId) {
        this.userId = userId;
    }

    public String getTypeDoc() {
        return typeDoc;
    }

    public void setTypeDoc(String typeDoc) {
        this.typeDoc = typeDoc;
    }

    public Object getTypeFlag() {
        return typeFlag;
    }

    public void setTypeFlag(Object typeFlag) {
        this.typeFlag = typeFlag;
    }

    public String getDateOfInvoice() {
        return dateOfInvoice;
    }

    public void setDateOfInvoice(String dateOfInvoice) {
        this.dateOfInvoice = dateOfInvoice;
    }

    public String getOpNo() {
        return opNo;
    }

    public void setOpNo(String opNo) {
        this.opNo = opNo;
    }

    public String getOpDate() {
        return opDate;
    }

    public void setOpDate(String opDate) {
        this.opDate = opDate;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }
}
