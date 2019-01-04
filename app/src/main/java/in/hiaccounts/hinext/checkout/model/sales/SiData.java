package in.hiaccounts.hinext.checkout.model.sales;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import in.hiaccounts.hinext.checkout.model.BankPayment;
import in.hiaccounts.hinext.checkout.model.RedeemPayment;
import in.hiaccounts.hinext.restaurant.model.checkout.TaxWiseSummaryList;
import in.hiaccounts.hinext.sales.model.save_response.CashPayment;
import in.hiaccounts.hinext.sales.model.save_response.CreditPayment;
import in.hiaccounts.hinext.sales.model.save_response.SelectedItemsList;
import in.hiaccounts.hinext.sales.model.save_response.TaxSummaryList;
import in.hiaccounts.hinext.sales.model.save_response.VoucherPayment;

public class SiData {
    @SerializedName("message")
    @Expose
    public Object message;
    @SerializedName("status")
    @Expose
    public Long status;
    @SerializedName("selectedItemsList")
    @Expose
    public List<SelectedItemsList> selectedItemsList = null;
    @SerializedName("selectedAccountList")
    @Expose
    public List<Object> selectedAccountList = null;
    @SerializedName("taxSummaryList")
    @Expose
    public List<TaxSummaryList> taxSummaryList = null;
    @SerializedName("taxWiseSummaryList")
    @Expose
    public List<TaxWiseSummaryList> taxWiseSummaryList = null;
    @SerializedName("multiPartialPaymentList")
    @Expose
    public List<Object> multiPartialPaymentList = null;
    @SerializedName("operation")
    @Expose
    public Object operation;
    @SerializedName("cashPayment")
    @Expose
    public CashPayment cashPayment;
    @SerializedName("creditPayment")
    @Expose
    public CreditPayment creditPayment;
    @SerializedName("voucherPayment")
    @Expose
    public VoucherPayment voucherPayment;
    @SerializedName("multiPayment")
    @Expose
    public Object multiPayment;
    @SerializedName("bankPayment")
    @Expose
    public BankPayment bankPayment;
    @SerializedName("redeemPayment")
    @Expose
    public RedeemPayment redeemPayment;
    @SerializedName("hiPosServiceCharge")
    @Expose
    public Double hiPosServiceCharge;
    @SerializedName("paymentType")
    @Expose
    public String paymentType;
    @SerializedName("totalCheckOutamt")
    @Expose
    public Double totalCheckOutamt;
    @SerializedName("otherChargesAmt")
    @Expose
    public Double otherChargesAmt;
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
    public Object customerEmail;
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
    public Object companydupData;
    @SerializedName("companyData")
    @Expose
    public Object companyData;
    @SerializedName("balanceAmount")
    @Expose
    public Double balanceAmount;
    @SerializedName("customer")
    @Expose
    public Object customer;
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
    public String phoneNumber;
    @SerializedName("inventoryAddress")
    @Expose
    public Object inventoryAddress;
    @SerializedName("inventoryName")
    @Expose
    public String inventoryName;
    @SerializedName("inventoryContactNo")
    @Expose
    public String inventoryContactNo;
    @SerializedName("inventoryEmail")
    @Expose
    public String inventoryEmail;
    @SerializedName("inventoryFax")
    @Expose
    public Object inventoryFax;
    @SerializedName("advancepayment")
    @Expose
    public String advancepayment;
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
    public Double totalActualWeight;
    @SerializedName("totalSellableWeight")
    @Expose
    public Double totalSellableWeight;
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
    @SerializedName("recieptFooter")
    @Expose
    public Object recieptFooter;
    @SerializedName("companyLogoPath")
    @Expose
    public Object companyLogoPath;
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
    public Object agentIdOfInvoice;
    @SerializedName("projectIdOfInvoice")
    @Expose
    public Object projectIdOfInvoice;
    @SerializedName("termCondIdOfInvoice")
    @Expose
    public Object termCondIdOfInvoice;
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
    public Object customerAddress;
    @SerializedName("customerState")
    @Expose
    public String customerState;
    @SerializedName("customerGst")
    @Expose
    public String customerGst;
    @SerializedName("locationAddress")
    @Expose
    public Object locationAddress;
    @SerializedName("locationState")
    @Expose
    public String locationState;
    @SerializedName("locationGst")
    @Expose
    public String locationGst;
    @SerializedName("locationContactPerson")
    @Expose
    public Object locationContactPerson;
    @SerializedName("locationGContactNo")
    @Expose
    public String locationGContactNo;
    @SerializedName("salesOrderId")
    @Expose
    public Object salesOrderId;
    @SerializedName("salesOrderDetailsId")
    @Expose
    public Object salesOrderDetailsId;
    @SerializedName("memo")
    @Expose
    public String memo;
    @SerializedName("returnReason")
    @Expose
    public Object returnReason;
    @SerializedName("totalRemaininBalance")
    @Expose
    public Object totalRemaininBalance;
    @SerializedName("shippingDate")
    @Expose
    public Long shippingDate;
    @SerializedName("shippingDateString")
    @Expose
    public String shippingDateString;
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
    @SerializedName("proFormaId")
    @Expose
    public Object proFormaId;
    @SerializedName("proFormaDetailsId")
    @Expose
    public Object proFormaDetailsId;
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
    public Object patientId;
    @SerializedName("serviceCharge")
    @Expose
    public Object serviceCharge;
    @SerializedName("agentName")
    @Expose
    public Object agentName;
    @SerializedName("exchangerateValue")
    @Expose
    public String exchangerateValue;
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
    public Double invoiceAmt;
    @SerializedName("incrementAmt")
    @Expose
    public Double incrementAmt;
    @SerializedName("employeeId")
    @Expose
    public Object employeeId;
    @SerializedName("tokenNo")
    @Expose
    public Double tokenNo;
    @SerializedName("table")
    @Expose
    public Object table;
    @SerializedName("invoiceNo")
    @Expose
    public Object invoiceNo;
    @SerializedName("formDate")
    @Expose
    public Object formDate;
    @SerializedName("projectName")
    @Expose
    public Object projectName;
    @SerializedName("shippingMethodName")
    @Expose
    public Object shippingMethodName;
    @SerializedName("invokeOrderId")
    @Expose
    public Object invokeOrderId;
    @SerializedName("invokeOrderName")
    @Expose
    public Object invokeOrderName;
    @SerializedName("generatedVoucherNo")
    @Expose
    public Object generatedVoucherNo;
    @SerializedName("customerPo")
    @Expose
    public String customerPo;
    @SerializedName("serviceDeliveryId")
    @Expose
    public Object serviceDeliveryId;
    @SerializedName("exportInvoice")
    @Expose
    public Object exportInvoice;
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
    @SerializedName("employeeName")
    @Expose
    public Object employeeName;
    @SerializedName("toStateCode")
    @Expose
    public Double toStateCode;
    @SerializedName("fromStateCode")
    @Expose
    public Double fromStateCode;
    @SerializedName("vehicleNo")
    @Expose
    public Object vehicleNo;
    @SerializedName("distance")
    @Expose
    public Object distance;
    @SerializedName("transporterId")
    @Expose
    public Object transporterId;
    @SerializedName("customerPincode")
    @Expose
    public Object customerPincode;
    @SerializedName("vehicleSeries")
    @Expose
    public Object vehicleSeries;
    @SerializedName("ewayBillNo")
    @Expose
    public Object ewayBillNo;
    @SerializedName("roundingStatus")
    @Expose
    public Boolean roundingStatus;
    @SerializedName("roundingOffValue")
    @Expose
    public String roundingOffValue;
    @SerializedName("otherContactName")
    @Expose
    public Object otherContactName;
    @SerializedName("personIncharge")
    @Expose
    public Object personIncharge;
    @SerializedName("locationEmail")
    @Expose
    public Object locationEmail;
    @SerializedName("returnType")
    @Expose
    public Object returnType;
    @SerializedName("discountAmtInPercentage")
    @Expose
    public Double discountAmtInPercentage;
    @SerializedName("bluetoothStatus")
    @Expose
    public Boolean bluetoothStatus;
    @SerializedName("hiposServiceChargeAmt")
    @Expose
    public Double hiposServiceChargeAmt;
    @SerializedName("restaurantDiscount")
    @Expose
    public Double restaurantDiscount;
    @SerializedName("customerNo")
    @Expose
    public String customerNo;
    @SerializedName("custNotiId")
    @Expose
    public Object custNotiId;
    @SerializedName("currencyObj")
    @Expose
    public Object currencyObj;
    @SerializedName("customerObj")
    @Expose
    public Object customerObj;
    @SerializedName("roundingAdj")
    @Expose
    public Double roundingAdj;
    @SerializedName("totalAmt")
    @Expose
    public Double totalAmt;
    @SerializedName("tcsAmount")
    @Expose
    public String tcsAmount;
    @SerializedName("tdsAmount")
    @Expose
    public String tdsAmount;
    @SerializedName("deliveryOrderId")
    @Expose
    public Object deliveryOrderId;
    @SerializedName("deliveryOrderIdDetails")
    @Expose
    public Object deliveryOrderIdDetails;
    @SerializedName("amountReturned")
    @Expose
    public Double amountReturned;
    @SerializedName("doNo")
    @Expose
    public Object doNo;
    @SerializedName("arbalance")
    @Expose
    public Object arbalance;

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
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

    public List<Object> getSelectedAccountList() {
        return selectedAccountList;
    }

    public void setSelectedAccountList(List<Object> selectedAccountList) {
        this.selectedAccountList = selectedAccountList;
    }

    public List<TaxSummaryList> getTaxSummaryList() {
        return taxSummaryList;
    }

    public void setTaxSummaryList(List<TaxSummaryList> taxSummaryList) {
        this.taxSummaryList = taxSummaryList;
    }

    public List<TaxWiseSummaryList> getTaxWiseSummaryList() {
        return taxWiseSummaryList;
    }

    public void setTaxWiseSummaryList(List<TaxWiseSummaryList> taxWiseSummaryList) {
        this.taxWiseSummaryList = taxWiseSummaryList;
    }

    public List<Object> getMultiPartialPaymentList() {
        return multiPartialPaymentList;
    }

    public void setMultiPartialPaymentList(List<Object> multiPartialPaymentList) {
        this.multiPartialPaymentList = multiPartialPaymentList;
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

    public BankPayment getBankPayment() {
        return bankPayment;
    }

    public void setBankPayment(BankPayment bankPayment) {
        this.bankPayment = bankPayment;
    }

    public RedeemPayment getRedeemPayment() {
        return redeemPayment;
    }

    public void setRedeemPayment(RedeemPayment redeemPayment) {
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

    public Double getOtherChargesAmt() {
        return otherChargesAmt;
    }

    public void setOtherChargesAmt(Double otherChargesAmt) {
        this.otherChargesAmt = otherChargesAmt;
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

    public Object getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(Object customerEmail) {
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

    public Double getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(Double balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public Object getCustomer() {
        return customer;
    }

    public void setCustomer(Object customer) {
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Object getInventoryAddress() {
        return inventoryAddress;
    }

    public void setInventoryAddress(Object inventoryAddress) {
        this.inventoryAddress = inventoryAddress;
    }

    public String getInventoryName() {
        return inventoryName;
    }

    public void setInventoryName(String inventoryName) {
        this.inventoryName = inventoryName;
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

    public Object getInventoryFax() {
        return inventoryFax;
    }

    public void setInventoryFax(Object inventoryFax) {
        this.inventoryFax = inventoryFax;
    }

    public String getAdvancepayment() {
        return advancepayment;
    }

    public void setAdvancepayment(String advancepayment) {
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

    public Object getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(Object customerAddress) {
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

    public String getLocationGst() {
        return locationGst;
    }

    public void setLocationGst(String locationGst) {
        this.locationGst = locationGst;
    }

    public Object getLocationContactPerson() {
        return locationContactPerson;
    }

    public void setLocationContactPerson(Object locationContactPerson) {
        this.locationContactPerson = locationContactPerson;
    }

    public String getLocationGContactNo() {
        return locationGContactNo;
    }

    public void setLocationGContactNo(String locationGContactNo) {
        this.locationGContactNo = locationGContactNo;
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

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
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

    public Long getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(Long shippingDate) {
        this.shippingDate = shippingDate;
    }

    public String getShippingDateString() {
        return shippingDateString;
    }

    public void setShippingDateString(String shippingDateString) {
        this.shippingDateString = shippingDateString;
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

    public Object getProFormaId() {
        return proFormaId;
    }

    public void setProFormaId(Object proFormaId) {
        this.proFormaId = proFormaId;
    }

    public Object getProFormaDetailsId() {
        return proFormaDetailsId;
    }

    public void setProFormaDetailsId(Object proFormaDetailsId) {
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

    public Object getPatientId() {
        return patientId;
    }

    public void setPatientId(Object patientId) {
        this.patientId = patientId;
    }

    public Object getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(Object serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public Object getAgentName() {
        return agentName;
    }

    public void setAgentName(Object agentName) {
        this.agentName = agentName;
    }

    public String getExchangerateValue() {
        return exchangerateValue;
    }

    public void setExchangerateValue(String exchangerateValue) {
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

    public Object getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Object employeeId) {
        this.employeeId = employeeId;
    }

    public Double getTokenNo() {
        return tokenNo;
    }

    public void setTokenNo(Double tokenNo) {
        this.tokenNo = tokenNo;
    }

    public Object getTable() {
        return table;
    }

    public void setTable(Object table) {
        this.table = table;
    }

    public Object getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(Object invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public Object getFormDate() {
        return formDate;
    }

    public void setFormDate(Object formDate) {
        this.formDate = formDate;
    }

    public Object getProjectName() {
        return projectName;
    }

    public void setProjectName(Object projectName) {
        this.projectName = projectName;
    }

    public Object getShippingMethodName() {
        return shippingMethodName;
    }

    public void setShippingMethodName(Object shippingMethodName) {
        this.shippingMethodName = shippingMethodName;
    }

    public Object getInvokeOrderId() {
        return invokeOrderId;
    }

    public void setInvokeOrderId(Object invokeOrderId) {
        this.invokeOrderId = invokeOrderId;
    }

    public Object getInvokeOrderName() {
        return invokeOrderName;
    }

    public void setInvokeOrderName(Object invokeOrderName) {
        this.invokeOrderName = invokeOrderName;
    }

    public Object getGeneratedVoucherNo() {
        return generatedVoucherNo;
    }

    public void setGeneratedVoucherNo(Object generatedVoucherNo) {
        this.generatedVoucherNo = generatedVoucherNo;
    }

    public String getCustomerPo() {
        return customerPo;
    }

    public void setCustomerPo(String customerPo) {
        this.customerPo = customerPo;
    }

    public Object getServiceDeliveryId() {
        return serviceDeliveryId;
    }

    public void setServiceDeliveryId(Object serviceDeliveryId) {
        this.serviceDeliveryId = serviceDeliveryId;
    }

    public Object getExportInvoice() {
        return exportInvoice;
    }

    public void setExportInvoice(Object exportInvoice) {
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

    public Object getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(Object employeeName) {
        this.employeeName = employeeName;
    }

    public Double getToStateCode() {
        return toStateCode;
    }

    public void setToStateCode(Double toStateCode) {
        this.toStateCode = toStateCode;
    }

    public Double getFromStateCode() {
        return fromStateCode;
    }

    public void setFromStateCode(Double fromStateCode) {
        this.fromStateCode = fromStateCode;
    }

    public Object getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(Object vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public Object getDistance() {
        return distance;
    }

    public void setDistance(Object distance) {
        this.distance = distance;
    }

    public Object getTransporterId() {
        return transporterId;
    }

    public void setTransporterId(Object transporterId) {
        this.transporterId = transporterId;
    }

    public Object getCustomerPincode() {
        return customerPincode;
    }

    public void setCustomerPincode(Object customerPincode) {
        this.customerPincode = customerPincode;
    }

    public Object getVehicleSeries() {
        return vehicleSeries;
    }

    public void setVehicleSeries(Object vehicleSeries) {
        this.vehicleSeries = vehicleSeries;
    }

    public Object getEwayBillNo() {
        return ewayBillNo;
    }

    public void setEwayBillNo(Object ewayBillNo) {
        this.ewayBillNo = ewayBillNo;
    }

    public Boolean getRoundingStatus() {
        return roundingStatus;
    }

    public void setRoundingStatus(Boolean roundingStatus) {
        this.roundingStatus = roundingStatus;
    }

    public String getRoundingOffValue() {
        return roundingOffValue;
    }

    public void setRoundingOffValue(String roundingOffValue) {
        this.roundingOffValue = roundingOffValue;
    }

    public Object getOtherContactName() {
        return otherContactName;
    }

    public void setOtherContactName(Object otherContactName) {
        this.otherContactName = otherContactName;
    }

    public Object getPersonIncharge() {
        return personIncharge;
    }

    public void setPersonIncharge(Object personIncharge) {
        this.personIncharge = personIncharge;
    }

    public Object getLocationEmail() {
        return locationEmail;
    }

    public void setLocationEmail(Object locationEmail) {
        this.locationEmail = locationEmail;
    }

    public Object getReturnType() {
        return returnType;
    }

    public void setReturnType(Object returnType) {
        this.returnType = returnType;
    }

    public Double getDiscountAmtInPercentage() {
        return discountAmtInPercentage;
    }

    public void setDiscountAmtInPercentage(Double discountAmtInPercentage) {
        this.discountAmtInPercentage = discountAmtInPercentage;
    }

    public Boolean getBluetoothStatus() {
        return bluetoothStatus;
    }

    public void setBluetoothStatus(Boolean bluetoothStatus) {
        this.bluetoothStatus = bluetoothStatus;
    }

    public Double getHiposServiceChargeAmt() {
        return hiposServiceChargeAmt;
    }

    public void setHiposServiceChargeAmt(Double hiposServiceChargeAmt) {
        this.hiposServiceChargeAmt = hiposServiceChargeAmt;
    }

    public Double getRestaurantDiscount() {
        return restaurantDiscount;
    }

    public void setRestaurantDiscount(Double restaurantDiscount) {
        this.restaurantDiscount = restaurantDiscount;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public Object getCustNotiId() {
        return custNotiId;
    }

    public void setCustNotiId(Object custNotiId) {
        this.custNotiId = custNotiId;
    }

    public Object getCurrencyObj() {
        return currencyObj;
    }

    public void setCurrencyObj(Object currencyObj) {
        this.currencyObj = currencyObj;
    }

    public Object getCustomerObj() {
        return customerObj;
    }

    public void setCustomerObj(Object customerObj) {
        this.customerObj = customerObj;
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

    public String getTcsAmount() {
        return tcsAmount;
    }

    public void setTcsAmount(String tcsAmount) {
        this.tcsAmount = tcsAmount;
    }

    public String getTdsAmount() {
        return tdsAmount;
    }

    public void setTdsAmount(String tdsAmount) {
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

    public Double getAmountReturned() {
        return amountReturned;
    }

    public void setAmountReturned(Double amountReturned) {
        this.amountReturned = amountReturned;
    }

    public Object getDoNo() {
        return doNo;
    }

    public void setDoNo(Object doNo) {
        this.doNo = doNo;
    }

    public Object getArbalance() {
        return arbalance;
    }

    public void setArbalance(Object arbalance) {
        this.arbalance = arbalance;
    }
}
