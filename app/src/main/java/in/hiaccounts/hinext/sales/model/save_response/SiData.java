
package in.hiaccounts.hinext.sales.model.save_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class SiData implements Serializable {


    @SerializedName("totalChequePymtAmtReturned")
    @Expose
    public double totalChequePymtAmtReturned;
    @SerializedName("totalVoucherPymtAmtReturned")
    @Expose
    public double totalVoucherPymtAmtReturned;
    @SerializedName("chequeNumber")
    @Expose
    public String chequeNumber;
    @SerializedName("voucherNumber")
    @Expose
    public String voucherNumber;
    @SerializedName("salesOrderId")
    @Expose
    public String salesOrderId;
    @SerializedName("salesOrderDetailsId")
    @Expose
    public Long salesOrderDetailsId;
    @SerializedName("memo")
    @Expose
    public String memo;
    @SerializedName("returnReason")
    @Expose
    public String returnReason;
    @SerializedName("totalRemaininBalance")
    @Expose
    public double totalRemaininBalance;
    @SerializedName("arbalance")
    @Expose
    public double arbalance;


    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private Long status;
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
    private String multiPayment;
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
    @SerializedName("cessTotalTaxAmt")
    @Expose
    private Double cessTotalTaxAmt;
    @SerializedName("itemCount")
    @Expose
    private Integer itemCount;
    @SerializedName("customerId")
    @Expose
    private Long customerId;
    @SerializedName("taxType")
    @Expose
    private String taxType;
    @SerializedName("siid")
    @Expose
    private Long siid;
    @SerializedName("customerEmail")
    @Expose
    private String customerEmail;
    @SerializedName("totalServiceCharge")
    @Expose
    private Double totalServiceCharge;
    @SerializedName("siNo")
    @Expose
    private String siNo;
    @SerializedName("srlnNo")
    @Expose
    private String srlnNo;
    @SerializedName("cutomerName")
    @Expose
    private String cutomerName;
    @SerializedName("companydupData")
    @Expose
    private Object companydupData;
    @SerializedName("companyData")
    @Expose
    private Object companyData;
    @SerializedName("balanceAmount")
    @Expose
    private Double balanceAmount;
    @SerializedName("customer")
    @Expose
    private Object customer;
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
    @SerializedName("inventoryFax")
    @Expose
    private String inventoryFax;
    @SerializedName("advancepayment")
    @Expose
    private String advancepayment;
    @SerializedName("formNo")
    @Expose
    private String formNo;
    @SerializedName("totalCashPymtAmtReturned")
    @Expose
    private Object totalCashPymtAmtReturned;
    @SerializedName("salesOrderNo")
    @Expose
    private Object salesOrderNo;
    @SerializedName("totalActualWeight")
    @Expose
    private Double totalActualWeight;
    @SerializedName("totalSellableWeight")
    @Expose
    private Double totalSellableWeight;
    @SerializedName("from_reg")
    @Expose
    private String fromReg;
    @SerializedName("to_reg")
    @Expose
    private String toReg;
    @SerializedName("user_id")
    @Expose
    private Object userId;
    @SerializedName("type_doc")
    @Expose
    private String typeDoc;
    @SerializedName("type_flag")
    @Expose
    private Object typeFlag;
    @SerializedName("recieptFooter")
    @Expose
    private String recieptFooter;
    @SerializedName("companyLogoPath")
    @Expose
    private String companyLogoPath;
    @SerializedName("cmpyLocation")
    @Expose
    private Long cmpyLocation;
    @SerializedName("custLocation")
    @Expose
    private Long custLocation;
    @SerializedName("termsId")
    @Expose
    private Long termsId;
    @SerializedName("dateOfInvoice")
    @Expose
    private String dateOfInvoice;
    @SerializedName("agentIdOfInvoice")
    @Expose
    private Object agentIdOfInvoice;
    @SerializedName("projectIdOfInvoice")
    @Expose
    private Object projectIdOfInvoice;
    @SerializedName("termCondIdOfInvoice")
    @Expose
    private Object termCondIdOfInvoice;
    @SerializedName("currencyIdOfInvoice")
    @Expose
    private Object currencyIdOfInvoice;
    @SerializedName("exchangeRateIdOfInvoice")
    @Expose
    private Object exchangeRateIdOfInvoice;
    @SerializedName("totalCashPayment")
    @Expose
    private Double totalCashPayment;
    @SerializedName("totalVoucherPayment")
    @Expose
    private Double totalVoucherPayment;
    @SerializedName("totalCardPayment")
    @Expose
    private Double totalCardPayment;
    @SerializedName("customerAddress")
    @Expose
    private String customerAddress;
    @SerializedName("customerState")
    @Expose
    private String customerState;
    @SerializedName("customerGst")
    @Expose
    private String customerGst;
    @SerializedName("locationAddress")
    @Expose
    private String locationAddress;
    @SerializedName("locationState")
    @Expose
    private String locationState;
    @SerializedName("locationGst")
    @Expose
    private String locationGst;
    @SerializedName("amountReturned")
    @Expose
    private Double amountReturned;

    @SerializedName("soNo")
    @Expose
    private String soNo;

    public String getSoNo() {
        return soNo;
    }

    public void setSoNo(String soNo) {
        this.soNo = soNo;
    }

    public double getTotalChequePymtAmtReturned() {
        return totalChequePymtAmtReturned;
    }

    public void setTotalChequePymtAmtReturned(double totalChequePymtAmtReturned) {
        this.totalChequePymtAmtReturned = totalChequePymtAmtReturned;
    }

    public double getTotalVoucherPymtAmtReturned() {
        return totalVoucherPymtAmtReturned;
    }

    public void setTotalVoucherPymtAmtReturned(double totalVoucherPymtAmtReturned) {
        this.totalVoucherPymtAmtReturned = totalVoucherPymtAmtReturned;
    }

    public String getChequeNumber() {
        return chequeNumber;
    }

    public void setChequeNumber(String chequeNumber) {
        this.chequeNumber = chequeNumber;
    }

    public String getVoucherNumber() {
        return voucherNumber;
    }

    public void setVoucherNumber(String voucherNumber) {
        this.voucherNumber = voucherNumber;
    }

    public String getSalesOrderId() {
        return salesOrderId;
    }

    public void setSalesOrderId(String salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public Long getSalesOrderDetailsId() {
        return salesOrderDetailsId;
    }

    public void setSalesOrderDetailsId(Long salesOrderDetailsId) {
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

    public double getTotalRemaininBalance() {
        return totalRemaininBalance;
    }

    public void setTotalRemaininBalance(double totalRemaininBalance) {
        this.totalRemaininBalance = totalRemaininBalance;
    }

    public double getArbalance() {
        return arbalance;
    }

    public void setArbalance(double arbalance) {
        this.arbalance = arbalance;
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

    public String getMultiPayment() {
        return multiPayment;
    }

    public void setMultiPayment(String multiPayment) {
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

    public Double getCessTotalTaxAmt() {
        return cessTotalTaxAmt;
    }

    public void setCessTotalTaxAmt(Double cessTotalTaxAmt) {
        this.cessTotalTaxAmt = cessTotalTaxAmt;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
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

    public Object getTotalCashPymtAmtReturned() {
        return totalCashPymtAmtReturned;
    }

    public void setTotalCashPymtAmtReturned(Object totalCashPymtAmtReturned) {
        this.totalCashPymtAmtReturned = totalCashPymtAmtReturned;
    }

    public Object getSalesOrderNo() {
        return salesOrderNo;
    }

    public void setSalesOrderNo(Object salesOrderNo) {
        this.salesOrderNo = salesOrderNo;
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

    public Double getAmountReturned() {
        return amountReturned;
    }

    public void setAmountReturned(Double amountReturned) {
        this.amountReturned = amountReturned;
    }
}
