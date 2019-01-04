package in.hiaccounts.hinext.restaurant.model.checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import in.hiaccounts.hinext.checkout.model.BankPayment;
import in.hiaccounts.hinext.checkout.model.CashPayment;
import in.hiaccounts.hinext.checkout.model.CreditPayment;
import in.hiaccounts.hinext.checkout.model.VoucherPayment;

/**
 * Created by Admin on 11/23/2017.
 */

public class RestraCheckoutData implements Serializable {

    @SerializedName("selectedItemsList")
    @Expose
    public List<RestraCheckoutItem> selectedItemsList = null;

    @SerializedName("totalCheckOutamt")
    @Expose
    public double totalCheckOutamt;
    @SerializedName("totalTenderedAmount")
    @Expose
    public double totalTenderedAmount;
    @SerializedName("totalRemaininBalance")
    @Expose
    public double totalRemaininBalance;
    @SerializedName("paymentType")
    @Expose
    public String paymentType;
    @SerializedName("totalTaxAmt")
    @Expose
    public double totalTaxAmt;
    @SerializedName("serviceCharge")
    @Expose
    public long serviceCharge;
    @SerializedName("taxType")
    @Expose
    public String taxType;
    @SerializedName("customerId")
    @Expose
    public Long customerId;
    @SerializedName("from_reg")
    @Expose
    public String fromReg;
    @SerializedName("to_reg")
    @Expose
    public String toReg;
    @SerializedName("type_doc")
    @Expose
    public String typeDoc;
    @SerializedName("tokenNo")
    @Expose
    public String tokenNo;
    @SerializedName("customerName")
    @Expose
    public String customerName;
    @SerializedName("customerNo")
    @Expose
    public String customerNo;
    @SerializedName("customerAddress")
    @Expose
    public String customerAddress;
    @SerializedName("cutomerName")
    @Expose
    public String cutomerName;
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
    @SerializedName("hiPosServiceCharge")
    @Expose
    private double hiPosServiceCharge;
    private String tableName;
  //  private TableNamesData tableName;
    private String tableVal;
    private Double discountAmount;
    private Double discountAmtInPercentage;
    private String userId;
    private String waiterName;
    private Boolean mobileOrder;
    private String roundingOffValue;
    private Boolean bluetoothStatus;
    private Boolean notificationAppend;
    private String tableId;
    private String type;
    private Long tableNo;

    public Long getTableNo() {
        return tableNo;
    }

    public void setTableNo(Long tableNo) {
        this.tableNo = tableNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public Boolean getNotificationAppend() {
        return notificationAppend;
    }

    public void setNotificationAppend(Boolean notificationAppend) {
        this.notificationAppend = notificationAppend;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCutomerName() {
        return cutomerName;
    }

    public void setCutomerName(String cutomerName) {
        this.cutomerName = cutomerName;
    }

    public double getTotalTenderedAmount() {
        return totalTenderedAmount;
    }

    public void setTotalTenderedAmount(double totalTenderedAmount) {
        this.totalTenderedAmount = totalTenderedAmount;
    }

    public double getTotalRemaininBalance() {
        return totalRemaininBalance;
    }

    public void setTotalRemaininBalance(double totalRemaininBalance) {
        this.totalRemaininBalance = totalRemaininBalance;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getTokenNo() {
        return tokenNo;
    }

    public void setTokenNo(String tokenNo) {
        this.tokenNo = tokenNo;
    }

    public Boolean getBluetoothStatus() {
        return bluetoothStatus;
    }

    public void setBluetoothStatus(Boolean bluetoothStatus) {
        this.bluetoothStatus = bluetoothStatus;
    }

    public String getRoundingOffValue() {
        return roundingOffValue;
    }

    public void setRoundingOffValue(String roundingOffValue) {
        this.roundingOffValue = roundingOffValue;
    }

    public Boolean getMobileOrder() {
        return mobileOrder;
    }

    public void setMobileOrder(Boolean mobileOrder) {
        this.mobileOrder = mobileOrder;
    }

    public CreditPayment getCreditPayment() {
        return creditPayment;
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

    public void setCreditPayment(CreditPayment creditPayment) {
        this.creditPayment = creditPayment;
    }

    public VoucherPayment getVoucherPayment() {
        return voucherPayment;
    }

    public void setVoucherPayment(VoucherPayment voucherPayment) {
        this.voucherPayment = voucherPayment;
    }

    public Double getDiscountAmtInPercentage() {
        return discountAmtInPercentage;
    }

    public void setDiscountAmtInPercentage(Double discountAmtInPercentage) {
        this.discountAmtInPercentage = discountAmtInPercentage;
    }

    public String getWaiterName() {
        return waiterName;
    }

    public void setWaiterName(String waiterName) {
        this.waiterName = waiterName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }



    public String getTableVal() {
        return tableVal;
    }

    public void setTableVal(String tableVal) {
        this.tableVal = tableVal;
    }

    public double getHiPosServiceCharge() {
        return hiPosServiceCharge;
    }

    public void setHiPosServiceCharge(double hiPosServiceCharge) {
        this.hiPosServiceCharge = hiPosServiceCharge;
    }

    public List<RestraCheckoutItem> getSelectedItemsList() {
        return selectedItemsList;
    }

    public void setSelectedItemsList(List<RestraCheckoutItem> selectedItemsList) {
        this.selectedItemsList = selectedItemsList;
    }



    public double getTotalCheckOutamt() {
        return totalCheckOutamt;
    }

    public void setTotalCheckOutamt(double totalCheckOutamt) {
        this.totalCheckOutamt = totalCheckOutamt;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public double getTotalTaxAmt() {
        return totalTaxAmt;
    }

    public void setTotalTaxAmt(double totalTaxAmt) {
        this.totalTaxAmt = totalTaxAmt;
    }

    public long getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(long serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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

    public String getTypeDoc() {
        return typeDoc;
    }

    public void setTypeDoc(String typeDoc) {
        this.typeDoc = typeDoc;
    }


}
