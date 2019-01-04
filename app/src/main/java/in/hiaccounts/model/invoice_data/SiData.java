
package in.hiaccounts.model.invoice_data;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SiData  implements Serializable{
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("companyData")
    @Expose
    private Object companyData;
    @SerializedName("from_reg")
    @Expose
    private Object fromReg;
    @SerializedName("customerEmail")
    @Expose
    private String customerEmail;
    @SerializedName("customer")
    @Expose
    private Object customer;
    @SerializedName("customerId")
    @Expose
    private long customerId;
    @SerializedName("selectedItemsList")
    @Expose
    private List<SelectedItemsList> selectedItemsList = null;
    @SerializedName("advancepayment")
    @Expose
    private Object advancepayment;
    @SerializedName("voucherPayment")
    @Expose
    private VoucherPayment voucherPayment;
    @SerializedName("user_id")
    @Expose
    private Object userId;
    @SerializedName("operation")
    @Expose
    private Object operation;
    @SerializedName("type_doc")
    @Expose
    private Object typeDoc;
    @SerializedName("paymentType")
    @Expose
    private String paymentType;
    @SerializedName("billToAddress")
    @Expose
    private Object billToAddress;
    @SerializedName("multiPayment")
    @Expose
    private Object multiPayment;
    @SerializedName("to_reg")
    @Expose
    private Object toReg;
    @SerializedName("totalTaxAmt")
    @Expose
    private double totalTaxAmt;
    @SerializedName("cashPayment")
    @Expose
    private CashPayment cashPayment;
    @SerializedName("creditPayment")
    @Expose
    private CreditPayment creditPayment;
    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("type_flag")
    @Expose
    private Object typeFlag;
    @SerializedName("salesOrderNo")
    @Expose
    private Object salesOrderNo;
    @SerializedName("srlnNo")
    @Expose
    private String srlnNo;
    @SerializedName("cutomerName")
    @Expose
    private String cutomerName;
    @SerializedName("inventoryAddress")
    @Expose
    private String inventoryAddress;
    @SerializedName("discountAmount")
    @Expose
    private double discountAmount;
    @SerializedName("taxType")
    @Expose
    private String taxType;
    @SerializedName("printType")
    @Expose
    private String printType;
    @SerializedName("balanceAmount")
    @Expose
    private double balanceAmount;
    @SerializedName("formNo")
    @Expose
    private Object formNo;
    @SerializedName("shipToAddress")
    @Expose
    private String shipToAddress;
    @SerializedName("siid")
    @Expose
    private long siid;
    @SerializedName("email")
    @Expose
    private Object email;
    @SerializedName("siNo")
    @Expose
    private String siNo;
    @SerializedName("itemCount")
    @Expose
    private long itemCount;
    @SerializedName("companydupData")
    @Expose
    private Object companydupData;
    @SerializedName("hiPosServiceCharge")
    @Expose
    private double hiPosServiceCharge;
    @SerializedName("totalCheckOutamt")
    @Expose
    private double totalCheckOutamt;
    @SerializedName("totalTenderedAmount")
    @Expose
    private double totalTenderedAmount;
    @SerializedName("taxSummaryList")
    @Expose
    private List<TaxSummaryList> taxSummaryList = null;
    @SerializedName("totalServiceCharge")
    @Expose
    private double totalServiceCharge;
    @SerializedName("inventoryContactNo")
    @Expose
    private String inventoryContactNo;
    @SerializedName("inventoryEmail")
    @Expose
    private String inventoryEmail;
    @SerializedName("totalActualWeight")
    @Expose
    private double totalActualWeight;
    @SerializedName("amountReturned")
    @Expose
    private double amountReturned;
    @SerializedName("totalSellableWeight")
    @Expose
    private double totalSellableWeight;
    @SerializedName("totalCashPymtAmtReturned")
    @Expose
    private Object totalCashPymtAmtReturned;
    @SerializedName("message")
    @Expose
    private Object message;
    @SerializedName("status")
    @Expose
    private long status;

    /**
     * No args constructor for use in serialization
     *
     */
    public SiData() {
    }

    /**
     *
     * @param creditPayment
     * @param inventoryEmail
     * @param customerEmail
     * @param customer
     * @param operation
     * @param taxSummaryList
     * @param phoneNumber
     * @param userId
     * @param totalTaxAmt
     * @param userName
     * @param totalCheckOutamt
     * @param fromReg
     * @param companyData
     * @param shipToAddress
     * @param status
     * @param billToAddress
     * @param printType
     * @param inventoryContactNo
     * @param companydupData
     * @param toReg
     * @param advancepayment
     * @param typeDoc
     * @param email
     * @param amountReturned
     * @param itemCount
     * @param hiPosServiceCharge
     * @param salesOrderNo
     * @param cashPayment
     * @param siNo
     * @param selectedItemsList
     * @param totalTenderedAmount
     * @param typeFlag
     * @param inventoryAddress
     * @param discountAmount
     * @param cutomerName
     * @param totalActualWeight
     * @param customerId
     * @param balanceAmount
     * @param formNo
     * @param multiPayment
     * @param totalCashPymtAmtReturned
     * @param totalSellableWeight
     * @param totalServiceCharge
     * @param srlnNo
     * @param paymentType
     * @param message
     * @param voucherPayment
     * @param taxType
     * @param siid
     */
    public SiData(String userName, Object companyData, Object fromReg, String customerEmail, Object customer, long customerId, List<SelectedItemsList> selectedItemsList, Object advancepayment, VoucherPayment voucherPayment, Object userId, Object operation, Object typeDoc, String paymentType, Object billToAddress, Object multiPayment, Object toReg, double totalTaxAmt, CashPayment cashPayment, CreditPayment creditPayment, String phoneNumber, Object typeFlag, Object salesOrderNo, String srlnNo, String cutomerName, String inventoryAddress, double discountAmount, String taxType, String printType, double balanceAmount, Object formNo, String shipToAddress, long siid, Object email, String siNo, long itemCount, Object companydupData, double hiPosServiceCharge, double totalCheckOutamt, double totalTenderedAmount, List<TaxSummaryList> taxSummaryList, double totalServiceCharge, String inventoryContactNo, String inventoryEmail, double totalActualWeight, double amountReturned, double totalSellableWeight, Object totalCashPymtAmtReturned, Object message, long status) {
        super();
        this.userName = userName;
        this.companyData = companyData;
        this.fromReg = fromReg;
        this.customerEmail = customerEmail;
        this.customer = customer;
        this.customerId = customerId;
        this.selectedItemsList = selectedItemsList;
        this.advancepayment = advancepayment;
        this.voucherPayment = voucherPayment;
        this.userId = userId;
        this.operation = operation;
        this.typeDoc = typeDoc;
        this.paymentType = paymentType;
        this.billToAddress = billToAddress;
        this.multiPayment = multiPayment;
        this.toReg = toReg;
        this.totalTaxAmt = totalTaxAmt;
        this.cashPayment = cashPayment;
        this.creditPayment = creditPayment;
        this.phoneNumber = phoneNumber;
        this.typeFlag = typeFlag;
        this.salesOrderNo = salesOrderNo;
        this.srlnNo = srlnNo;
        this.cutomerName = cutomerName;
        this.inventoryAddress = inventoryAddress;
        this.discountAmount = discountAmount;
        this.taxType = taxType;
        this.printType = printType;
        this.balanceAmount = balanceAmount;
        this.formNo = formNo;
        this.shipToAddress = shipToAddress;
        this.siid = siid;
        this.email = email;
        this.siNo = siNo;
        this.itemCount = itemCount;
        this.companydupData = companydupData;
        this.hiPosServiceCharge = hiPosServiceCharge;
        this.totalCheckOutamt = totalCheckOutamt;
        this.totalTenderedAmount = totalTenderedAmount;
        this.taxSummaryList = taxSummaryList;
        this.totalServiceCharge = totalServiceCharge;
        this.inventoryContactNo = inventoryContactNo;
        this.inventoryEmail = inventoryEmail;
        this.totalActualWeight = totalActualWeight;
        this.amountReturned = amountReturned;
        this.totalSellableWeight = totalSellableWeight;
        this.totalCashPymtAmtReturned = totalCashPymtAmtReturned;
        this.message = message;
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Object getCompanyData() {
        return companyData;
    }

    public void setCompanyData(Object companyData) {
        this.companyData = companyData;
    }

    public Object getFromReg() {
        return fromReg;
    }

    public void setFromReg(Object fromReg) {
        this.fromReg = fromReg;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Object getCustomer() {
        return customer;
    }

    public void setCustomer(Object customer) {
        this.customer = customer;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public List<SelectedItemsList> getSelectedItemsList() {
        return selectedItemsList;
    }

    public void setSelectedItemsList(List<SelectedItemsList> selectedItemsList) {
        this.selectedItemsList = selectedItemsList;
    }

    public Object getAdvancepayment() {
        return advancepayment;
    }

    public void setAdvancepayment(Object advancepayment) {
        this.advancepayment = advancepayment;
    }

    public VoucherPayment getVoucherPayment() {
        return voucherPayment;
    }

    public void setVoucherPayment(VoucherPayment voucherPayment) {
        this.voucherPayment = voucherPayment;
    }

    public Object getUserId() {
        return userId;
    }

    public void setUserId(Object userId) {
        this.userId = userId;
    }

    public Object getOperation() {
        return operation;
    }

    public void setOperation(Object operation) {
        this.operation = operation;
    }

    public Object getTypeDoc() {
        return typeDoc;
    }

    public void setTypeDoc(Object typeDoc) {
        this.typeDoc = typeDoc;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Object getBillToAddress() {
        return billToAddress;
    }

    public void setBillToAddress(Object billToAddress) {
        this.billToAddress = billToAddress;
    }

    public Object getMultiPayment() {
        return multiPayment;
    }

    public void setMultiPayment(Object multiPayment) {
        this.multiPayment = multiPayment;
    }

    public Object getToReg() {
        return toReg;
    }

    public void setToReg(Object toReg) {
        this.toReg = toReg;
    }

    public double getTotalTaxAmt() {
        return totalTaxAmt;
    }

    public void setTotalTaxAmt(double totalTaxAmt) {
        this.totalTaxAmt = totalTaxAmt;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Object getTypeFlag() {
        return typeFlag;
    }

    public void setTypeFlag(Object typeFlag) {
        this.typeFlag = typeFlag;
    }

    public Object getSalesOrderNo() {
        return salesOrderNo;
    }

    public void setSalesOrderNo(Object salesOrderNo) {
        this.salesOrderNo = salesOrderNo;
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

    public String getInventoryAddress() {
        return inventoryAddress;
    }

    public void setInventoryAddress(String inventoryAddress) {
        this.inventoryAddress = inventoryAddress;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public String getPrintType() {
        return printType;
    }

    public void setPrintType(String printType) {
        this.printType = printType;
    }

    public double getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(double balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public Object getFormNo() {
        return formNo;
    }

    public void setFormNo(Object formNo) {
        this.formNo = formNo;
    }

    public String getShipToAddress() {
        return shipToAddress;
    }

    public void setShipToAddress(String shipToAddress) {
        this.shipToAddress = shipToAddress;
    }

    public long getSiid() {
        return siid;
    }

    public void setSiid(long siid) {
        this.siid = siid;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public String getSiNo() {
        return siNo;
    }

    public void setSiNo(String siNo) {
        this.siNo = siNo;
    }

    public long getItemCount() {
        return itemCount;
    }

    public void setItemCount(long itemCount) {
        this.itemCount = itemCount;
    }

    public Object getCompanydupData() {
        return companydupData;
    }

    public void setCompanydupData(Object companydupData) {
        this.companydupData = companydupData;
    }

    public double getHiPosServiceCharge() {
        return hiPosServiceCharge;
    }

    public void setHiPosServiceCharge(double hiPosServiceCharge) {
        this.hiPosServiceCharge = hiPosServiceCharge;
    }

    public double getTotalCheckOutamt() {
        return totalCheckOutamt;
    }

    public void setTotalCheckOutamt(double totalCheckOutamt) {
        this.totalCheckOutamt = totalCheckOutamt;
    }

    public double getTotalTenderedAmount() {
        return totalTenderedAmount;
    }

    public void setTotalTenderedAmount(double totalTenderedAmount) {
        this.totalTenderedAmount = totalTenderedAmount;
    }

    public List<TaxSummaryList> getTaxSummaryList() {
        return taxSummaryList;
    }

    public void setTaxSummaryList(List<TaxSummaryList> taxSummaryList) {
        this.taxSummaryList = taxSummaryList;
    }

    public double getTotalServiceCharge() {
        return totalServiceCharge;
    }

    public void setTotalServiceCharge(double totalServiceCharge) {
        this.totalServiceCharge = totalServiceCharge;
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

    public double getTotalActualWeight() {
        return totalActualWeight;
    }

    public void setTotalActualWeight(double totalActualWeight) {
        this.totalActualWeight = totalActualWeight;
    }

    public double getAmountReturned() {
        return amountReturned;
    }

    public void setAmountReturned(double amountReturned) {
        this.amountReturned = amountReturned;
    }

    public double getTotalSellableWeight() {
        return totalSellableWeight;
    }

    public void setTotalSellableWeight(double totalSellableWeight) {
        this.totalSellableWeight = totalSellableWeight;
    }

    public Object getTotalCashPymtAmtReturned() {
        return totalCashPymtAmtReturned;
    }

    public void setTotalCashPymtAmtReturned(Object totalCashPymtAmtReturned) {
        this.totalCashPymtAmtReturned = totalCashPymtAmtReturned;
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

}
