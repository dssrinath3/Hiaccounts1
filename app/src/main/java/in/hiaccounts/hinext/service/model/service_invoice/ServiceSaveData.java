package in.hiaccounts.hinext.service.model.service_invoice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import in.hiaccounts.hinext.service.model.SelectedItemsList;

/**
 * Created by administrator on 23/2/18.
 */

public class ServiceSaveData implements Serializable {
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("status")
    @Expose
    public Long status;
    @SerializedName("selectedItemsList")
    @Expose
    public List<SelectedItemsList> selectedItemsList = null;
    @SerializedName("taxSummaryList")
    @Expose
    public List<TaxSummaryList> taxSummaryList = null;
    @SerializedName("multiPartialPaymentList")
    @Expose
    public List<Object> multiPartialPaymentList = null;
    @SerializedName("operation")
    @Expose
    public String operation;
    @SerializedName("cashPayment")
    @Expose
    public String cashPayment;
    @SerializedName("creditPayment")
    @Expose
    public String creditPayment;
    @SerializedName("voucherPayment")
    @Expose
    public String voucherPayment;
    @SerializedName("multiPayment")
    @Expose
    public String multiPayment;
    @SerializedName("bankPayment")
    @Expose
    public String bankPayment;
    @SerializedName("redeemPayment")
    @Expose
    public String redeemPayment;
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
    @SerializedName("cessTotalTaxAmt")
    @Expose
    public Double cessTotalTaxAmt;
    @SerializedName("itemCount")
    @Expose
    public Long itemCount;
    @SerializedName("customerId")
    @Expose
    public Long customerId;
    @SerializedName("taxType")
    @Expose
    public String taxType;
    @SerializedName("siid")
    @Expose
    public Long siid;
    @SerializedName("customerEmail")
    @Expose
    public String customerEmail;
    @SerializedName("totalServiceCharge")
    @Expose
    public Double totalServiceCharge;
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
    public CompanydupData companydupData;
    @SerializedName("companyData")
    @Expose
    public CompanyData companyData;
    @SerializedName("balanceAmount")
    @Expose
    public Double balanceAmount;
    @SerializedName("customer")
    @Expose
    public String customer;
    @SerializedName("discountAmount")
    @Expose
    public Double discountAmount;
    @SerializedName("totalTenderedAmount")
    @Expose
    public Double totalTenderedAmount;
    @SerializedName("userName")
    @Expose
    public String userName;
    @SerializedName("printType")
    @Expose
    public String printType;
    @SerializedName("billToAddress")
    @Expose
    public String billToAddress;
    @SerializedName("shipToAddress")
    @Expose
    public String shipToAddress;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("phoneNumber")
    @Expose
    public String phoneNumber;
    @SerializedName("inventoryAddress")
    @Expose
    public String inventoryAddress;
    @SerializedName("inventoryContactNo")
    @Expose
    public String inventoryContactNo;
    @SerializedName("inventoryEmail")
    @Expose
    public String inventoryEmail;
    @SerializedName("inventoryFax")
    @Expose
    public String inventoryFax;
    @SerializedName("advancepayment")
    @Expose
    public String advancepayment;
    @SerializedName("formNo")
    @Expose
    public String formNo;
    @SerializedName("totalCashPymtAmtReturned")
    @Expose
    public Double totalCashPymtAmtReturned;
    @SerializedName("totalChequePymtAmtReturned")
    @Expose
    public Double totalChequePymtAmtReturned;
    @SerializedName("totalVoucherPymtAmtReturned")
    @Expose
    public Double totalVoucherPymtAmtReturned;
    @SerializedName("chequeNumber")
    @Expose
    public Long chequeNumber;
    @SerializedName("voucherNumber")
    @Expose
    public Long voucherNumber;
    @SerializedName("salesOrderNo")
    @Expose
    public Long salesOrderNo;
    @SerializedName("salesDeliveryOrderNO")
    @Expose
    public Long salesDeliveryOrderNO;
    @SerializedName("totalActualWeight")
    @Expose
    public Double totalActualWeight;
    @SerializedName("totalSellableWeight")
    @Expose
    public Double totalSellableWeight;
    @SerializedName("from_reg")
    @Expose
    public String fromReg;
    @SerializedName("to_reg")
    @Expose
    public String toReg;
    @SerializedName("user_id")
    @Expose
    public Long userId;
    @SerializedName("type_doc")
    @Expose
    public String typeDoc;
    @SerializedName("type_flag")
    @Expose
    public String typeFlag;
    @SerializedName("recieptFooter")
    @Expose
    public String recieptFooter;
    @SerializedName("companyLogoPath")
    @Expose
    public String companyLogoPath;
    @SerializedName("cmpyLocation")
    @Expose
    public String cmpyLocation;
    @SerializedName("custLocation")
    @Expose
    public String custLocation;
    @SerializedName("termsId")
    @Expose
    public Long termsId;
    @SerializedName("dateOfInvoice")
    @Expose
    public String dateOfInvoice;
    @SerializedName("agentIdOfInvoice")
    @Expose
    public String agentIdOfInvoice;
    @SerializedName("projectIdOfInvoice")
    @Expose
    public String projectIdOfInvoice;
    @SerializedName("termCondIdOfInvoice")
    @Expose
    public String termCondIdOfInvoice;
    @SerializedName("currencyIdOfInvoice")
    @Expose
    public String currencyIdOfInvoice;
    @SerializedName("exchangeRateIdOfInvoice")
    @Expose
    public String exchangeRateIdOfInvoice;
    @SerializedName("totalCashPayment")
    @Expose
    public Double totalCashPayment;
    @SerializedName("totalVoucherPayment")
    @Expose
    public Double totalVoucherPayment;
    @SerializedName("totalCardPayment")
    @Expose
    public Double totalCardPayment;
    @SerializedName("customerAddress")
    @Expose
    public String customerAddress;
    @SerializedName("customerState")
    @Expose
    public String customerState;
    @SerializedName("customerGst")
    @Expose
    public String customerGst;
    @SerializedName("locationAddress")
    @Expose
    public String locationAddress;
    @SerializedName("locationState")
    @Expose
    public String locationState;
    @SerializedName("locationGst")
    @Expose
    public String locationGst;
    @SerializedName("salesOrderId")
    @Expose
    public String salesOrderId;
    @SerializedName("salesOrderDetailsId")
    @Expose
    public String salesOrderDetailsId;
    @SerializedName("memo")
    @Expose
    public String memo;
    @SerializedName("returnReason")
    @Expose
    public String returnReason;
    @SerializedName("totalRemaininBalance")
    @Expose
    public String totalRemaininBalance;
    @SerializedName("shippingDate")
    @Expose
    public String shippingDate;
    @SerializedName("shippingDateString")
    @Expose
    public String shippingDateString;
    @SerializedName("shippingReferenceNo")
    @Expose
    public String shippingReferenceNo;
    @SerializedName("referenceNo")
    @Expose
    public String referenceNo;
    @SerializedName("shippingmethodId")
    @Expose
    public String shippingmethodId;
    @SerializedName("salesQuotationId")
    @Expose
    public String salesQuotationId;
    @SerializedName("proFormaId")
    @Expose
    public String proFormaId;
    @SerializedName("proFormaDetailsId")
    @Expose
    public String proFormaDetailsId;
    @SerializedName("totalVoucherAmt")
    @Expose
    public Double totalVoucherAmt;
    @SerializedName("totalCreditCardAmt")
    @Expose
    public Double totalCreditCardAmt;
    @SerializedName("totalCashAmt")
    @Expose
    public Double totalCashAmt;
    @SerializedName("patientId")
    @Expose
    public String patientId;
    @SerializedName("serviceCharge")
    @Expose
    public Double serviceCharge;
    @SerializedName("agentName")
    @Expose
    public String agentName;
    @SerializedName("exchangerateValue")
    @Expose
    public String exchangerateValue;
    @SerializedName("tc_value")
    @Expose
    public String tcValue;
    @SerializedName("salesQuotationDetailsId")
    @Expose
    public String salesQuotationDetailsId;
    @SerializedName("otherContactId")
    @Expose
    public String otherContactId;
    @SerializedName("invoiceDate")
    @Expose
    public String invoiceDate;
    @SerializedName("invoiceAmt")
    @Expose
    public Double invoiceAmt;
    @SerializedName("incrementAmt")
    @Expose
    public Double incrementAmt;
    @SerializedName("employeeId")
    @Expose
    public String employeeId;
    @SerializedName("tokenNo")
    @Expose
    public Double tokenNo;
    @SerializedName("table")
    @Expose
    public String table;
    @SerializedName("invoiceNo")
    @Expose
    public String invoiceNo;
    @SerializedName("formDate")
    @Expose
    public String formDate;
    @SerializedName("projectName")
    @Expose
    public String projectName;
    @SerializedName("shippingMethodName")
    @Expose
    public String shippingMethodName;
    @SerializedName("invokeOrderId")
    @Expose
    public Long invokeOrderId;
    @SerializedName("invokeOrderName")
    @Expose
    public String invokeOrderName;
    @SerializedName("generatedVoucherNo")
    @Expose
    public String generatedVoucherNo;
    @SerializedName("customerPo")
    @Expose
    public String customerPo;
    @SerializedName("serviceDeliveryId")
    @Expose
    public String serviceDeliveryId;
    @SerializedName("exportInvoice")
    @Expose
    public String exportInvoice;
    @SerializedName("invokeRemaningQty")
    @Expose
    public Double invokeRemaningQty;
    @SerializedName("amtToBePaid")
    @Expose
    public Double amtToBePaid;
    @SerializedName("siStatus")
    @Expose
    public String siStatus;
    @SerializedName("checkForHoldStock")
    @Expose
    public Boolean checkForHoldStock;
    @SerializedName("currencyObj")
    @Expose
    public String currencyObj;
    @SerializedName("otherDetailsDto")
    @Expose
    public String otherDetailsDto;
    @SerializedName("customerObj")
    @Expose
    public CustomerObj customerObj;
    @SerializedName("roundingAdj")
    @Expose
    public Double roundingAdj;
    @SerializedName("totalAmt")
    @Expose
    public Double totalAmt;
    @SerializedName("tcsAmount")
    @Expose
    public Double tcsAmount;
    @SerializedName("tdsAmount")
    @Expose
    public Double tdsAmount;
    @SerializedName("deliveryOrderId")
    @Expose
    public String deliveryOrderId;
    @SerializedName("deliveryOrderIdDetails")
    @Expose
    public String deliveryOrderIdDetails;
    @SerializedName("amountReturned")
    @Expose
    public Double amountReturned;
    @SerializedName("arbalance")
    @Expose
    public Double arbalance;

    public String getSrlnNo() {
        return srlnNo;
    }

    public void setSrlnNo(String srlnNo) {
        this.srlnNo = srlnNo;
    }

    public CustomerObj getCustomerObj() {
        return customerObj;
    }

    public void setCustomerObj(CustomerObj customerObj) {
        this.customerObj = customerObj;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
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

    public List<Object> getMultiPartialPaymentList() {
        return multiPartialPaymentList;
    }

    public void setMultiPartialPaymentList(List<Object> multiPartialPaymentList) {
        this.multiPartialPaymentList = multiPartialPaymentList;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getCashPayment() {
        return cashPayment;
    }

    public void setCashPayment(String cashPayment) {
        this.cashPayment = cashPayment;
    }

    public String getCreditPayment() {
        return creditPayment;
    }

    public void setCreditPayment(String creditPayment) {
        this.creditPayment = creditPayment;
    }

    public String getVoucherPayment() {
        return voucherPayment;
    }

    public void setVoucherPayment(String voucherPayment) {
        this.voucherPayment = voucherPayment;
    }

    public String getMultiPayment() {
        return multiPayment;
    }

    public void setMultiPayment(String multiPayment) {
        this.multiPayment = multiPayment;
    }

    public String getBankPayment() {
        return bankPayment;
    }

    public void setBankPayment(String bankPayment) {
        this.bankPayment = bankPayment;
    }

    public String getRedeemPayment() {
        return redeemPayment;
    }

    public void setRedeemPayment(String redeemPayment) {
        this.redeemPayment = redeemPayment;
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

    public Double getCessTotalTaxAmt() {
        return cessTotalTaxAmt;
    }

    public void setCessTotalTaxAmt(Double cessTotalTaxAmt) {
        this.cessTotalTaxAmt = cessTotalTaxAmt;
    }

    public Long getItemCount() {
        return itemCount;
    }

    public void setItemCount(Long itemCount) {
        this.itemCount = itemCount;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public Long getSiid() {
        return siid;
    }

    public void setSiid(Long siid) {
        this.siid = siid;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Double getTotalServiceCharge() {
        return totalServiceCharge;
    }

    public void setTotalServiceCharge(Double totalServiceCharge) {
        this.totalServiceCharge = totalServiceCharge;
    }

    public String getSiNo() {
        return siNo;
    }

    public void setSiNo(String siNo) {
        this.siNo = siNo;
    }



    public String getCutomerName() {
        return cutomerName;
    }

    public void setCutomerName(String cutomerName) {
        this.cutomerName = cutomerName;
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

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
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

    public String getInventoryFax() {
        return inventoryFax;
    }

    public void setInventoryFax(String inventoryFax) {
        this.inventoryFax = inventoryFax;
    }

    public String getAdvancepayment() {
        return advancepayment;
    }

    public void setAdvancepayment(String advancepayment) {
        this.advancepayment = advancepayment;
    }

    public String getFormNo() {
        return formNo;
    }

    public void setFormNo(String formNo) {
        this.formNo = formNo;
    }

    public Double getTotalCashPymtAmtReturned() {
        return totalCashPymtAmtReturned;
    }

    public void setTotalCashPymtAmtReturned(Double totalCashPymtAmtReturned) {
        this.totalCashPymtAmtReturned = totalCashPymtAmtReturned;
    }

    public Double getTotalChequePymtAmtReturned() {
        return totalChequePymtAmtReturned;
    }

    public void setTotalChequePymtAmtReturned(Double totalChequePymtAmtReturned) {
        this.totalChequePymtAmtReturned = totalChequePymtAmtReturned;
    }

    public Double getTotalVoucherPymtAmtReturned() {
        return totalVoucherPymtAmtReturned;
    }

    public void setTotalVoucherPymtAmtReturned(Double totalVoucherPymtAmtReturned) {
        this.totalVoucherPymtAmtReturned = totalVoucherPymtAmtReturned;
    }

    public Long getChequeNumber() {
        return chequeNumber;
    }

    public void setChequeNumber(Long chequeNumber) {
        this.chequeNumber = chequeNumber;
    }

    public Long getVoucherNumber() {
        return voucherNumber;
    }

    public void setVoucherNumber(Long voucherNumber) {
        this.voucherNumber = voucherNumber;
    }

    public Long getSalesOrderNo() {
        return salesOrderNo;
    }

    public void setSalesOrderNo(Long salesOrderNo) {
        this.salesOrderNo = salesOrderNo;
    }

    public Long getSalesDeliveryOrderNO() {
        return salesDeliveryOrderNO;
    }

    public void setSalesDeliveryOrderNO(Long salesDeliveryOrderNO) {
        this.salesDeliveryOrderNO = salesDeliveryOrderNO;
    }

    public Double getTotalActualWeight() {
        return totalActualWeight;
    }

    public void setTotalActualWeight(Double totalActualWeight) {
        this.totalActualWeight = totalActualWeight;
    }

    public Double getTotalSellableWeight() {
        return totalSellableWeight;
    }

    public void setTotalSellableWeight(Double totalSellableWeight) {
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

    public String getRecieptFooter() {
        return recieptFooter;
    }

    public void setRecieptFooter(String recieptFooter) {
        this.recieptFooter = recieptFooter;
    }

    public String getCompanyLogoPath() {
        return companyLogoPath;
    }

    public void setCompanyLogoPath(String companyLogoPath) {
        this.companyLogoPath = companyLogoPath;
    }

    public String getCmpyLocation() {
        return cmpyLocation;
    }

    public void setCmpyLocation(String cmpyLocation) {
        this.cmpyLocation = cmpyLocation;
    }

    public String getCustLocation() {
        return custLocation;
    }

    public void setCustLocation(String custLocation) {
        this.custLocation = custLocation;
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

    public String getAgentIdOfInvoice() {
        return agentIdOfInvoice;
    }

    public void setAgentIdOfInvoice(String agentIdOfInvoice) {
        this.agentIdOfInvoice = agentIdOfInvoice;
    }

    public String getProjectIdOfInvoice() {
        return projectIdOfInvoice;
    }

    public void setProjectIdOfInvoice(String projectIdOfInvoice) {
        this.projectIdOfInvoice = projectIdOfInvoice;
    }

    public String getTermCondIdOfInvoice() {
        return termCondIdOfInvoice;
    }

    public void setTermCondIdOfInvoice(String termCondIdOfInvoice) {
        this.termCondIdOfInvoice = termCondIdOfInvoice;
    }

    public String getCurrencyIdOfInvoice() {
        return currencyIdOfInvoice;
    }

    public void setCurrencyIdOfInvoice(String currencyIdOfInvoice) {
        this.currencyIdOfInvoice = currencyIdOfInvoice;
    }

    public String getExchangeRateIdOfInvoice() {
        return exchangeRateIdOfInvoice;
    }

    public void setExchangeRateIdOfInvoice(String exchangeRateIdOfInvoice) {
        this.exchangeRateIdOfInvoice = exchangeRateIdOfInvoice;
    }

    public Double getTotalCashPayment() {
        return totalCashPayment;
    }

    public void setTotalCashPayment(Double totalCashPayment) {
        this.totalCashPayment = totalCashPayment;
    }

    public Double getTotalVoucherPayment() {
        return totalVoucherPayment;
    }

    public void setTotalVoucherPayment(Double totalVoucherPayment) {
        this.totalVoucherPayment = totalVoucherPayment;
    }

    public Double getTotalCardPayment() {
        return totalCardPayment;
    }

    public void setTotalCardPayment(Double totalCardPayment) {
        this.totalCardPayment = totalCardPayment;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerState() {
        return customerState;
    }

    public void setCustomerState(String customerState) {
        this.customerState = customerState;
    }

    public String getCustomerGst() {
        return customerGst;
    }

    public void setCustomerGst(String customerGst) {
        this.customerGst = customerGst;
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

    public String getSalesOrderId() {
        return salesOrderId;
    }

    public void setSalesOrderId(String salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public String getSalesOrderDetailsId() {
        return salesOrderDetailsId;
    }

    public void setSalesOrderDetailsId(String salesOrderDetailsId) {
        this.salesOrderDetailsId = salesOrderDetailsId;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getReturnReason() {
        return returnReason;
    }

    public void setReturnReason(String returnReason) {
        this.returnReason = returnReason;
    }

    public String getTotalRemaininBalance() {
        return totalRemaininBalance;
    }

    public void setTotalRemaininBalance(String totalRemaininBalance) {
        this.totalRemaininBalance = totalRemaininBalance;
    }

    public String getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(String shippingDate) {
        this.shippingDate = shippingDate;
    }

    public String getShippingDateString() {
        return shippingDateString;
    }

    public void setShippingDateString(String shippingDateString) {
        this.shippingDateString = shippingDateString;
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

    public String getShippingmethodId() {
        return shippingmethodId;
    }

    public void setShippingmethodId(String shippingmethodId) {
        this.shippingmethodId = shippingmethodId;
    }

    public String getSalesQuotationId() {
        return salesQuotationId;
    }

    public void setSalesQuotationId(String salesQuotationId) {
        this.salesQuotationId = salesQuotationId;
    }

    public String getProFormaId() {
        return proFormaId;
    }

    public void setProFormaId(String proFormaId) {
        this.proFormaId = proFormaId;
    }

    public String getProFormaDetailsId() {
        return proFormaDetailsId;
    }

    public void setProFormaDetailsId(String proFormaDetailsId) {
        this.proFormaDetailsId = proFormaDetailsId;
    }

    public Double getTotalVoucherAmt() {
        return totalVoucherAmt;
    }

    public void setTotalVoucherAmt(Double totalVoucherAmt) {
        this.totalVoucherAmt = totalVoucherAmt;
    }

    public Double getTotalCreditCardAmt() {
        return totalCreditCardAmt;
    }

    public void setTotalCreditCardAmt(Double totalCreditCardAmt) {
        this.totalCreditCardAmt = totalCreditCardAmt;
    }

    public Double getTotalCashAmt() {
        return totalCashAmt;
    }

    public void setTotalCashAmt(Double totalCashAmt) {
        this.totalCashAmt = totalCashAmt;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public Double getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(Double serviceCharge) {
        this.serviceCharge = serviceCharge;
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

    public String getSalesQuotationDetailsId() {
        return salesQuotationDetailsId;
    }

    public void setSalesQuotationDetailsId(String salesQuotationDetailsId) {
        this.salesQuotationDetailsId = salesQuotationDetailsId;
    }

    public String getOtherContactId() {
        return otherContactId;
    }

    public void setOtherContactId(String otherContactId) {
        this.otherContactId = otherContactId;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Double getInvoiceAmt() {
        return invoiceAmt;
    }

    public void setInvoiceAmt(Double invoiceAmt) {
        this.invoiceAmt = invoiceAmt;
    }

    public Double getIncrementAmt() {
        return incrementAmt;
    }

    public void setIncrementAmt(Double incrementAmt) {
        this.incrementAmt = incrementAmt;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public Double getTokenNo() {
        return tokenNo;
    }

    public void setTokenNo(Double tokenNo) {
        this.tokenNo = tokenNo;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getFormDate() {
        return formDate;
    }

    public void setFormDate(String formDate) {
        this.formDate = formDate;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getShippingMethodName() {
        return shippingMethodName;
    }

    public void setShippingMethodName(String shippingMethodName) {
        this.shippingMethodName = shippingMethodName;
    }

    public Long getInvokeOrderId() {
        return invokeOrderId;
    }

    public void setInvokeOrderId(Long invokeOrderId) {
        this.invokeOrderId = invokeOrderId;
    }

    public String getInvokeOrderName() {
        return invokeOrderName;
    }

    public void setInvokeOrderName(String invokeOrderName) {
        this.invokeOrderName = invokeOrderName;
    }

    public String getGeneratedVoucherNo() {
        return generatedVoucherNo;
    }

    public void setGeneratedVoucherNo(String generatedVoucherNo) {
        this.generatedVoucherNo = generatedVoucherNo;
    }

    public String getCustomerPo() {
        return customerPo;
    }

    public void setCustomerPo(String customerPo) {
        this.customerPo = customerPo;
    }

    public String getServiceDeliveryId() {
        return serviceDeliveryId;
    }

    public void setServiceDeliveryId(String serviceDeliveryId) {
        this.serviceDeliveryId = serviceDeliveryId;
    }

    public String getExportInvoice() {
        return exportInvoice;
    }

    public void setExportInvoice(String exportInvoice) {
        this.exportInvoice = exportInvoice;
    }

    public Double getInvokeRemaningQty() {
        return invokeRemaningQty;
    }

    public void setInvokeRemaningQty(Double invokeRemaningQty) {
        this.invokeRemaningQty = invokeRemaningQty;
    }

    public Double getAmtToBePaid() {
        return amtToBePaid;
    }

    public void setAmtToBePaid(Double amtToBePaid) {
        this.amtToBePaid = amtToBePaid;
    }

    public String getSiStatus() {
        return siStatus;
    }

    public void setSiStatus(String siStatus) {
        this.siStatus = siStatus;
    }

    public Boolean getCheckForHoldStock() {
        return checkForHoldStock;
    }

    public void setCheckForHoldStock(Boolean checkForHoldStock) {
        this.checkForHoldStock = checkForHoldStock;
    }

    public String getCurrencyObj() {
        return currencyObj;
    }

    public void setCurrencyObj(String currencyObj) {
        this.currencyObj = currencyObj;
    }

    public String getOtherDetailsDto() {
        return otherDetailsDto;
    }

    public void setOtherDetailsDto(String otherDetailsDto) {
        this.otherDetailsDto = otherDetailsDto;
    }



    public Double getRoundingAdj() {
        return roundingAdj;
    }

    public void setRoundingAdj(Double roundingAdj) {
        this.roundingAdj = roundingAdj;
    }

    public Double getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(Double totalAmt) {
        this.totalAmt = totalAmt;
    }

    public Double getTcsAmount() {
        return tcsAmount;
    }

    public void setTcsAmount(Double tcsAmount) {
        this.tcsAmount = tcsAmount;
    }

    public Double getTdsAmount() {
        return tdsAmount;
    }

    public void setTdsAmount(Double tdsAmount) {
        this.tdsAmount = tdsAmount;
    }

    public String getDeliveryOrderId() {
        return deliveryOrderId;
    }

    public void setDeliveryOrderId(String deliveryOrderId) {
        this.deliveryOrderId = deliveryOrderId;
    }

    public String getDeliveryOrderIdDetails() {
        return deliveryOrderIdDetails;
    }

    public void setDeliveryOrderIdDetails(String deliveryOrderIdDetails) {
        this.deliveryOrderIdDetails = deliveryOrderIdDetails;
    }

    public Double getAmountReturned() {
        return amountReturned;
    }

    public void setAmountReturned(Double amountReturned) {
        this.amountReturned = amountReturned;
    }

    public Double getArbalance() {
        return arbalance;
    }

    public void setArbalance(Double arbalance) {
        this.arbalance = arbalance;
    }
}
