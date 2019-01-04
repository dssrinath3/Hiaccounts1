
package in.hiaccounts.hinext.sales.model.return_posinvoices;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Return_PosInvoiceData implements Serializable{

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
    private Object cashPayment;
    @SerializedName("creditPayment")
    @Expose
    private Object creditPayment;
    @SerializedName("voucherPayment")
    @Expose
    private Object voucherPayment;
    @SerializedName("multiPayment")
    @Expose
    private Object multiPayment;
    @SerializedName("hiPosServiceCharge")
    @Expose
    private double hiPosServiceCharge;
    @SerializedName("paymentType")
    @Expose
    private Object paymentType;
    @SerializedName("totalCheckOutamt")
    @Expose
    private double totalCheckOutamt;
    @SerializedName("totalTaxAmt")
    @Expose
    private double totalTaxAmt;
    @SerializedName("cessTotalTaxAmt")
    @Expose
    private double cessTotalTaxAmt;
    @SerializedName("itemCount")
    @Expose
    private Integer itemCount;
    @SerializedName("customerId")
    @Expose
    private Integer customerId;
    @SerializedName("taxType")
    @Expose
    private String taxType;
    @SerializedName("siid")
    @Expose
    private Integer siid;
    @SerializedName("customerEmail")
    @Expose
    private Object customerEmail;
    @SerializedName("totalServiceCharge")
    @Expose
    private double totalServiceCharge;
    @SerializedName("siNo")
    @Expose
    private Object siNo;
    @SerializedName("srlnNo")
    @Expose
    private String srlnNo;
    @SerializedName("cutomerName")
    @Expose
    private String cutomerName;
    @SerializedName("companydupData")
    @Expose
    private CompanydupData companydupData;
    @SerializedName("companyData")
    @Expose
    private CompanyData companyData;
    @SerializedName("balanceAmount")
    @Expose
    private double balanceAmount;
    @SerializedName("customer")
    @Expose
    private Object customer;
    @SerializedName("discountAmount")
    @Expose
    private double discountAmount;
    @SerializedName("totalTenderedAmount")
    @Expose
    private double totalTenderedAmount;
    @SerializedName("userName")
    @Expose
    private Object userName;
    @SerializedName("printType")
    @Expose
    private Object printType;
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
    private Object inventoryContactNo;
    @SerializedName("inventoryEmail")
    @Expose
    private Object inventoryEmail;
    @SerializedName("inventoryFax")
    @Expose
    private Object inventoryFax;
    @SerializedName("advancepayment")
    @Expose
    private Object advancepayment;
    @SerializedName("formNo")
    @Expose
    private Object formNo;
    @SerializedName("totalCashPymtAmtReturned")
    @Expose
    private Object totalCashPymtAmtReturned;
    @SerializedName("salesOrderNo")
    @Expose
    private Object salesOrderNo;
    @SerializedName("totalActualWeight")
    @Expose
    private double totalActualWeight;
    @SerializedName("totalSellableWeight")
    @Expose
    private double totalSellableWeight;
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
    @SerializedName("recieptFooter")
    @Expose
    private Object recieptFooter;
    @SerializedName("companyLogoPath")
    @Expose
    private Object companyLogoPath;
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
    private double totalCashPayment;
    @SerializedName("totalVoucherPayment")
    @Expose
    private double totalVoucherPayment;
    @SerializedName("totalCardPayment")
    @Expose
    private double totalCardPayment;
    @SerializedName("customerAddress")
    @Expose
    private Object customerAddress;
    @SerializedName("customerState")
    @Expose
    private Object customerState;
    @SerializedName("customerGst")
    @Expose
    private Object customerGst;
    @SerializedName("locationAddress")
    @Expose
    private Object locationAddress;
    @SerializedName("locationState")
    @Expose
    private Object locationState;
    @SerializedName("locationGst")
    @Expose
    private Object locationGst;
    @SerializedName("amountReturned")
    @Expose
    private double amountReturned;

    @SerializedName("arbalance")
    @Expose
    private double arbalance;

    private double totalReturnAmt;


    public double getTotalReturnAmt() {
        return totalReturnAmt;
    }

    public void setTotalReturnAmt(double totalReturnAmt) {
        this.totalReturnAmt = totalReturnAmt;
    }

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

    public Object getCashPayment() {
        return cashPayment;
    }

    public void setCashPayment(Object cashPayment) {
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

    public Object getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Object paymentType) {
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

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public Integer getSiid() {
        return siid;
    }

    public void setSiid(Integer siid) {
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

    public Object getSiNo() {
        return siNo;
    }

    public void setSiNo(Object siNo) {
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

    public Object getSalesOrderNo() {
        return salesOrderNo;
    }

    public void setSalesOrderNo(Object salesOrderNo) {
        this.salesOrderNo = salesOrderNo;
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

    public double getAmountReturned() {
        return amountReturned;
    }

    public void setAmountReturned(double amountReturned) {
        this.amountReturned = amountReturned;
    }

    public double getArbalance() {
        return arbalance;
    }

    public void setArbalance(double arbalance) {
        this.arbalance = arbalance;
    }
}
