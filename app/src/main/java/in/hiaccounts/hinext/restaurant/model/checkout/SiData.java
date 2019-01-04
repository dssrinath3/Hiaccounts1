
package in.hiaccounts.hinext.restaurant.model.checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import in.hiaccounts.hinext.checkout.model.BankPayment;
import in.hiaccounts.hinext.checkout.model.CashPayment;
import in.hiaccounts.hinext.checkout.model.CreditPayment;
import in.hiaccounts.hinext.checkout.model.VoucherPayment;

public class SiData implements Serializable{

    @SerializedName("message")
    @Expose
    public Object message;
    @SerializedName("status")
    @Expose
    public long status;
    @SerializedName("selectedItemsList")
    @Expose
    public List<RestraCheckoutItem> selectedItemsList = null;
    @SerializedName("taxSummaryList")
    @Expose
    public List<Object> taxSummaryList = null;
    @SerializedName("operation")
    @Expose
    public Object operation;
    @SerializedName("employeeName")
    @Expose
    public String employeeName;
    @SerializedName("table")
    @Expose
    public String table;
    @SerializedName("hiPosServiceCharge")
    @Expose
    public double hiPosServiceCharge;
    @SerializedName("discountAmtInPercentage")
    @Expose
    public Double discountAmtInPercentage;


    @SerializedName("paymentType")
    @Expose
    public String paymentType;
    @SerializedName("totalCheckOutamt")
    @Expose
    public double totalCheckOutamt;
    @SerializedName("totalTaxAmt")
    @Expose
    public double totalTaxAmt;
    @SerializedName("cessTotalTaxAmt")
    @Expose
    public double cessTotalTaxAmt;
    @SerializedName("itemCount")
    @Expose
    public long itemCount;
    @SerializedName("customerId")
    @Expose
    public long customerId;
    @SerializedName("taxType")
    @Expose
    public String taxType;
    @SerializedName("siid")
    @Expose
    public long siid;
    @SerializedName("customerEmail")
    @Expose
    public Object customerEmail;
    @SerializedName("totalServiceCharge")
    @Expose
    public double totalServiceCharge;
    @SerializedName("siNo")
    @Expose
    public String siNo;
    @SerializedName("srlnNo")
    @Expose
    public String srlnNo;
    @SerializedName("cutomerName")
    @Expose
    public String cutomerName;
    @SerializedName("companydupData")
    @Expose
    public Object companydupData;
    @SerializedName("companyData")
    @Expose
    public Object companyData;
    @SerializedName("balanceAmount")
    @Expose
    public double balanceAmount;
    @SerializedName("customer")
    @Expose
    public Object customer;
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
    @SerializedName("inventoryFax")
    @Expose
    public Object inventoryFax;
    @SerializedName("advancepayment")
    @Expose
    public Object advancepayment;
    @SerializedName("formNo")
    @Expose
    public Object formNo;
    @SerializedName("totalCashPymtAmtReturned")
    @Expose
    public Object totalCashPymtAmtReturned;
    @SerializedName("totalChequePymtAmtReturned")
    @Expose
    public Object totalChequePymtAmtReturned;
    @SerializedName("totalVoucherPymtAmtReturned")
    @Expose
    public Object totalVoucherPymtAmtReturned;
    @SerializedName("chequeNumber")
    @Expose
    public Object chequeNumber;
    @SerializedName("voucherNumber")
    @Expose
    public Object voucherNumber;
    @SerializedName("salesOrderNo")
    @Expose
    public Object salesOrderNo;
    @SerializedName("salesDeliveryOrderNO")
    @Expose
    public Object salesDeliveryOrderNO;
    @SerializedName("totalActualWeight")
    @Expose
    public double totalActualWeight;
    @SerializedName("totalSellableWeight")
    @Expose
    public double totalSellableWeight;
    @SerializedName("from_reg")
    @Expose
    public String fromReg;
    @SerializedName("to_reg")
    @Expose
    public String toReg;
    @SerializedName("customerNo")
    @Expose
    public String customerNo;

    @SerializedName("user_id")
    @Expose
    public Object userId;
    @SerializedName("type_doc")
    @Expose
    public String typeDoc;
    @SerializedName("type_flag")
    @Expose
    public Object typeFlag;
    @SerializedName("recieptFooter")
    @Expose
    public Object recieptFooter;
    @SerializedName("companyLogoPath")
    @Expose
    public Object companyLogoPath;
    @SerializedName("cmpyLocation")
    @Expose
    public Object cmpyLocation;
    @SerializedName("custLocation")
    @Expose
    public Object custLocation;
    @SerializedName("termsId")
    @Expose
    public long termsId;
    @SerializedName("dateOfInvoice")
    @Expose
    public Object dateOfInvoice;
    @SerializedName("agentIdOfInvoice")
    @Expose
    public Object agentIdOfInvoice;
    @SerializedName("projectIdOfInvoice")
    @Expose
    public Object projectIdOfInvoice;
    @SerializedName("termCondIdOfInvoice")
    @Expose
    public Object termCondIdOfInvoice;
    @SerializedName("currencyIdOfInvoice")
    @Expose
    public Object currencyIdOfInvoice;
    @SerializedName("exchangeRateIdOfInvoice")
    @Expose
    public Object exchangeRateIdOfInvoice;
    @SerializedName("totalCashPayment")
    @Expose
    public double totalCashPayment;
    @SerializedName("totalVoucherPayment")
    @Expose
    public double totalVoucherPayment;
    @SerializedName("totalCardPayment")
    @Expose
    public double totalCardPayment;
    @SerializedName("customerAddress")
    @Expose
    public Object customerAddress;
    @SerializedName("customerState")
    @Expose
    public Object customerState;
    @SerializedName("customerGst")
    @Expose
    public Object customerGst;
    @SerializedName("locationAddress")
    @Expose
    public Object locationAddress;
    @SerializedName("locationState")
    @Expose
    public Object locationState;
    @SerializedName("locationGst")
    @Expose
    public Object locationGst;
    @SerializedName("salesOrderId")
    @Expose
    public Object salesOrderId;
    @SerializedName("salesOrderDetailsId")
    @Expose
    public Object salesOrderDetailsId;
    @SerializedName("memo")
    @Expose
    public Object memo;
    @SerializedName("returnReason")
    @Expose
    public Object returnReason;
    @SerializedName("totalRemaininBalance")
    @Expose
    public Object totalRemaininBalance;
    @SerializedName("shippingDate")
    @Expose
    public Object shippingDate;
    @SerializedName("shippingReferenceNo")
    @Expose
    public Object shippingReferenceNo;
    @SerializedName("referenceNo")
    @Expose
    public Object referenceNo;
    @SerializedName("shippingmethodId")
    @Expose
    public Object shippingmethodId;
    @SerializedName("salesQuotationId")
    @Expose
    public Object salesQuotationId;
    @SerializedName("totalVoucherAmt")
    @Expose
    public double totalVoucherAmt;
    @SerializedName("totalCreditCardAmt")
    @Expose
    public double totalCreditCardAmt;
    @SerializedName("totalCashAmt")
    @Expose
    public double totalCashAmt;
    @SerializedName("patientId")
    @Expose
    public Object patientId;
    @SerializedName("serviceCharge")
    @Expose
    public String serviceCharge;
    @SerializedName("agentName")
    @Expose
    public Object agentName;
    @SerializedName("exchangerateValue")
    @Expose
    public Object exchangerateValue;
    @SerializedName("tc_value")
    @Expose
    public Object tcValue;
    @SerializedName("salesQuotationDetailsId")
    @Expose
    public Object salesQuotationDetailsId;
    @SerializedName("otherContactId")
    @Expose
    public Object otherContactId;
    @SerializedName("invoiceDate")
    @Expose
    public Object invoiceDate;
    @SerializedName("invoiceAmt")
    @Expose
    public double invoiceAmt;
    @SerializedName("incrementAmt")
    @Expose
    public double incrementAmt;
    @SerializedName("tcsAmount")
    @Expose
    public Object tcsAmount;
    @SerializedName("tdsAmount")
    @Expose
    public Object tdsAmount;
    @SerializedName("deliveryOrderId")
    @Expose
    public Object deliveryOrderId;
    @SerializedName("deliveryOrderIdDetails")
    @Expose
    public Object deliveryOrderIdDetails;
    @SerializedName("amountReturned")
    @Expose
    public double amountReturned;
    @SerializedName("arbalance")
    @Expose
    public Object arbalance;
    @SerializedName("tokenNo")
    @Expose
    public Long tokenNo;
    @SerializedName("taxWiseSummaryList")
    @Expose
    public List<TaxWiseSummaryList> taxWiseSummaryList = null;
    private String roundingOffValue;

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

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public List<TaxWiseSummaryList> getTaxWiseSummaryList() {
        return taxWiseSummaryList;
    }

    public void setTaxWiseSummaryList(List<TaxWiseSummaryList> taxWiseSummaryList) {
        this.taxWiseSummaryList = taxWiseSummaryList;
    }

    public BankPayment getBankPayment() {
        return bankPayment;
    }

    public void setBankPayment(BankPayment bankPayment) {
        this.bankPayment = bankPayment;
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

    public String getRoundingOffValue() {
        return roundingOffValue;
    }

    public void setRoundingOffValue(String roundingOffValue) {
        this.roundingOffValue = roundingOffValue;
    }

    public Double getDiscountAmtInPercentage() {
        return discountAmtInPercentage;
    }

    public void setDiscountAmtInPercentage(Double discountAmtInPercentage) {
        this.discountAmtInPercentage = discountAmtInPercentage;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

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

    public List<RestraCheckoutItem> getSelectedItemsList() {
        return selectedItemsList;
    }

    public void setSelectedItemsList(List<RestraCheckoutItem> selectedItemsList) {
        this.selectedItemsList = selectedItemsList;
    }

    public List<Object> getTaxSummaryList() {
        return taxSummaryList;
    }

    public void setTaxSummaryList(List<Object> taxSummaryList) {
        this.taxSummaryList = taxSummaryList;
    }

    public Object getOperation() {
        return operation;
    }

    public void setOperation(Object operation) {
        this.operation = operation;
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

    public double getCessTotalTaxAmt() {
        return cessTotalTaxAmt;
    }

    public void setCessTotalTaxAmt(double cessTotalTaxAmt) {
        this.cessTotalTaxAmt = cessTotalTaxAmt;
    }

    public long getItemCount() {
        return itemCount;
    }

    public void setItemCount(long itemCount) {
        this.itemCount = itemCount;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
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

    public Object getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(Object customerEmail) {
        this.customerEmail = customerEmail;
    }

    public double getTotalServiceCharge() {
        return totalServiceCharge;
    }

    public void setTotalServiceCharge(double totalServiceCharge) {
        this.totalServiceCharge = totalServiceCharge;
    }

    public String getSiNo() {
        return siNo;
    }

    public void setSiNo(String siNo) {
        this.siNo = siNo;
    }

    public String getSrlnNo() {
        return srlnNo;
    }

    public void setSrlnNo(String srlnNo) {
        this.srlnNo = srlnNo;
    }

    public String getCutomerName() {
        return cutomerName;
    }

    public void setCutomerName(String cutomerName) {
        this.cutomerName = cutomerName;
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

    public Object getCustomer() {
        return customer;
    }

    public void setCustomer(Object customer) {
        this.customer = customer;
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

    public Object getInventoryFax() {
        return inventoryFax;
    }

    public void setInventoryFax(Object inventoryFax) {
        this.inventoryFax = inventoryFax;
    }

    public Object getAdvancepayment() {
        return advancepayment;
    }

    public void setAdvancepayment(Object advancepayment) {
        this.advancepayment = advancepayment;
    }

    public Object getFormNo() {
        return formNo;
    }

    public void setFormNo(Object formNo) {
        this.formNo = formNo;
    }

    public Object getTotalCashPymtAmtReturned() {
        return totalCashPymtAmtReturned;
    }

    public void setTotalCashPymtAmtReturned(Object totalCashPymtAmtReturned) {
        this.totalCashPymtAmtReturned = totalCashPymtAmtReturned;
    }

    public Object getTotalChequePymtAmtReturned() {
        return totalChequePymtAmtReturned;
    }

    public void setTotalChequePymtAmtReturned(Object totalChequePymtAmtReturned) {
        this.totalChequePymtAmtReturned = totalChequePymtAmtReturned;
    }

    public Object getTotalVoucherPymtAmtReturned() {
        return totalVoucherPymtAmtReturned;
    }

    public void setTotalVoucherPymtAmtReturned(Object totalVoucherPymtAmtReturned) {
        this.totalVoucherPymtAmtReturned = totalVoucherPymtAmtReturned;
    }

    public Object getChequeNumber() {
        return chequeNumber;
    }

    public void setChequeNumber(Object chequeNumber) {
        this.chequeNumber = chequeNumber;
    }

    public Object getVoucherNumber() {
        return voucherNumber;
    }

    public void setVoucherNumber(Object voucherNumber) {
        this.voucherNumber = voucherNumber;
    }

    public Object getSalesOrderNo() {
        return salesOrderNo;
    }

    public void setSalesOrderNo(Object salesOrderNo) {
        this.salesOrderNo = salesOrderNo;
    }

    public Object getSalesDeliveryOrderNO() {
        return salesDeliveryOrderNO;
    }

    public void setSalesDeliveryOrderNO(Object salesDeliveryOrderNO) {
        this.salesDeliveryOrderNO = salesDeliveryOrderNO;
    }

    public double getTotalActualWeight() {
        return totalActualWeight;
    }

    public void setTotalActualWeight(double totalActualWeight) {
        this.totalActualWeight = totalActualWeight;
    }

    public double getTotalSellableWeight() {
        return totalSellableWeight;
    }

    public void setTotalSellableWeight(double totalSellableWeight) {
        this.totalSellableWeight = totalSellableWeight;
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

    public Object getRecieptFooter() {
        return recieptFooter;
    }

    public void setRecieptFooter(Object recieptFooter) {
        this.recieptFooter = recieptFooter;
    }

    public Object getCompanyLogoPath() {
        return companyLogoPath;
    }

    public void setCompanyLogoPath(Object companyLogoPath) {
        this.companyLogoPath = companyLogoPath;
    }

    public Object getCmpyLocation() {
        return cmpyLocation;
    }

    public void setCmpyLocation(Object cmpyLocation) {
        this.cmpyLocation = cmpyLocation;
    }

    public Object getCustLocation() {
        return custLocation;
    }

    public void setCustLocation(Object custLocation) {
        this.custLocation = custLocation;
    }

    public long getTermsId() {
        return termsId;
    }

    public void setTermsId(long termsId) {
        this.termsId = termsId;
    }

    public Object getDateOfInvoice() {
        return dateOfInvoice;
    }

    public void setDateOfInvoice(Object dateOfInvoice) {
        this.dateOfInvoice = dateOfInvoice;
    }

    public Object getAgentIdOfInvoice() {
        return agentIdOfInvoice;
    }

    public void setAgentIdOfInvoice(Object agentIdOfInvoice) {
        this.agentIdOfInvoice = agentIdOfInvoice;
    }

    public Object getProjectIdOfInvoice() {
        return projectIdOfInvoice;
    }

    public void setProjectIdOfInvoice(Object projectIdOfInvoice) {
        this.projectIdOfInvoice = projectIdOfInvoice;
    }

    public Object getTermCondIdOfInvoice() {
        return termCondIdOfInvoice;
    }

    public void setTermCondIdOfInvoice(Object termCondIdOfInvoice) {
        this.termCondIdOfInvoice = termCondIdOfInvoice;
    }

    public Object getCurrencyIdOfInvoice() {
        return currencyIdOfInvoice;
    }

    public void setCurrencyIdOfInvoice(Object currencyIdOfInvoice) {
        this.currencyIdOfInvoice = currencyIdOfInvoice;
    }

    public Object getExchangeRateIdOfInvoice() {
        return exchangeRateIdOfInvoice;
    }

    public void setExchangeRateIdOfInvoice(Object exchangeRateIdOfInvoice) {
        this.exchangeRateIdOfInvoice = exchangeRateIdOfInvoice;
    }

    public double getTotalCashPayment() {
        return totalCashPayment;
    }

    public void setTotalCashPayment(double totalCashPayment) {
        this.totalCashPayment = totalCashPayment;
    }

    public double getTotalVoucherPayment() {
        return totalVoucherPayment;
    }

    public void setTotalVoucherPayment(double totalVoucherPayment) {
        this.totalVoucherPayment = totalVoucherPayment;
    }

    public double getTotalCardPayment() {
        return totalCardPayment;
    }

    public void setTotalCardPayment(double totalCardPayment) {
        this.totalCardPayment = totalCardPayment;
    }

    public Object getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(Object customerAddress) {
        this.customerAddress = customerAddress;
    }

    public Object getCustomerState() {
        return customerState;
    }

    public void setCustomerState(Object customerState) {
        this.customerState = customerState;
    }

    public Object getCustomerGst() {
        return customerGst;
    }

    public void setCustomerGst(Object customerGst) {
        this.customerGst = customerGst;
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

    public Object getSalesOrderId() {
        return salesOrderId;
    }

    public void setSalesOrderId(Object salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public Object getSalesOrderDetailsId() {
        return salesOrderDetailsId;
    }

    public void setSalesOrderDetailsId(Object salesOrderDetailsId) {
        this.salesOrderDetailsId = salesOrderDetailsId;
    }

    public Object getMemo() {
        return memo;
    }

    public void setMemo(Object memo) {
        this.memo = memo;
    }

    public Object getReturnReason() {
        return returnReason;
    }

    public void setReturnReason(Object returnReason) {
        this.returnReason = returnReason;
    }

    public Object getTotalRemaininBalance() {
        return totalRemaininBalance;
    }

    public void setTotalRemaininBalance(Object totalRemaininBalance) {
        this.totalRemaininBalance = totalRemaininBalance;
    }

    public Object getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(Object shippingDate) {
        this.shippingDate = shippingDate;
    }

    public Object getShippingReferenceNo() {
        return shippingReferenceNo;
    }

    public void setShippingReferenceNo(Object shippingReferenceNo) {
        this.shippingReferenceNo = shippingReferenceNo;
    }

    public Object getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(Object referenceNo) {
        this.referenceNo = referenceNo;
    }

    public Object getShippingmethodId() {
        return shippingmethodId;
    }

    public void setShippingmethodId(Object shippingmethodId) {
        this.shippingmethodId = shippingmethodId;
    }

    public Object getSalesQuotationId() {
        return salesQuotationId;
    }

    public void setSalesQuotationId(Object salesQuotationId) {
        this.salesQuotationId = salesQuotationId;
    }

    public double getTotalVoucherAmt() {
        return totalVoucherAmt;
    }

    public void setTotalVoucherAmt(double totalVoucherAmt) {
        this.totalVoucherAmt = totalVoucherAmt;
    }

    public double getTotalCreditCardAmt() {
        return totalCreditCardAmt;
    }

    public void setTotalCreditCardAmt(double totalCreditCardAmt) {
        this.totalCreditCardAmt = totalCreditCardAmt;
    }

    public double getTotalCashAmt() {
        return totalCashAmt;
    }

    public void setTotalCashAmt(double totalCashAmt) {
        this.totalCashAmt = totalCashAmt;
    }

    public Object getPatientId() {
        return patientId;
    }

    public void setPatientId(Object patientId) {
        this.patientId = patientId;
    }

    public String getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(String serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public Object getAgentName() {
        return agentName;
    }

    public void setAgentName(Object agentName) {
        this.agentName = agentName;
    }

    public Object getExchangerateValue() {
        return exchangerateValue;
    }

    public void setExchangerateValue(Object exchangerateValue) {
        this.exchangerateValue = exchangerateValue;
    }

    public Object getTcValue() {
        return tcValue;
    }

    public void setTcValue(Object tcValue) {
        this.tcValue = tcValue;
    }

    public Object getSalesQuotationDetailsId() {
        return salesQuotationDetailsId;
    }

    public void setSalesQuotationDetailsId(Object salesQuotationDetailsId) {
        this.salesQuotationDetailsId = salesQuotationDetailsId;
    }

    public Object getOtherContactId() {
        return otherContactId;
    }

    public void setOtherContactId(Object otherContactId) {
        this.otherContactId = otherContactId;
    }

    public Object getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Object invoiceDate) {
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

    public Object getTcsAmount() {
        return tcsAmount;
    }

    public void setTcsAmount(Object tcsAmount) {
        this.tcsAmount = tcsAmount;
    }

    public Object getTdsAmount() {
        return tdsAmount;
    }

    public void setTdsAmount(Object tdsAmount) {
        this.tdsAmount = tdsAmount;
    }

    public Object getDeliveryOrderId() {
        return deliveryOrderId;
    }

    public void setDeliveryOrderId(Object deliveryOrderId) {
        this.deliveryOrderId = deliveryOrderId;
    }

    public Object getDeliveryOrderIdDetails() {
        return deliveryOrderIdDetails;
    }

    public void setDeliveryOrderIdDetails(Object deliveryOrderIdDetails) {
        this.deliveryOrderIdDetails = deliveryOrderIdDetails;
    }

    public double getAmountReturned() {
        return amountReturned;
    }

    public void setAmountReturned(double amountReturned) {
        this.amountReturned = amountReturned;
    }

    public Object getArbalance() {
        return arbalance;
    }

    public void setArbalance(Object arbalance) {
        this.arbalance = arbalance;
    }

    public Long getTokenNo() {
        return tokenNo;
    }

    public void setTokenNo(Long tokenNo) {
        this.tokenNo = tokenNo;
    }
}
