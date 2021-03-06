package in.hiaccounts.hinext.sales.model.save_salesorder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import in.hiaccounts.hinext.sales.model.CashPayment;
import in.hiaccounts.hinext.sales.model.CreditPayment;
import in.hiaccounts.hinext.sales.model.VoucherPayment;
import in.hiaccounts.hinext.sales.model.checkout.SelectedItemsList;

/**
 * Created by Admin on 9/9/2017.
 */

public class SaveSalesOrderData implements Serializable {

    @SerializedName("selectedItemsList")
    @Expose
    private List<SelectedItemsList> selectedItemsList = null;
    @SerializedName("cashPayment")
    @Expose
    private CashPayment cashPayment;
    @SerializedName("creditPayment")
    @Expose
    private CreditPayment creditPayment;
    @SerializedName("voucherPayment")
    @Expose
    private VoucherPayment voucherPayment;
    @SerializedName("totalCheckOutamt")
    @Expose
    private String totalCheckOutamt;
    @SerializedName("paymentType")
    @Expose
    private String paymentType;
    @SerializedName("totalTaxAmt")
    @Expose
    private double totalTaxAmt;
    @SerializedName("taxType")
    @Expose
    private String taxType;
    @SerializedName("customerId")
    @Expose
    private long customerId;
    @SerializedName("proFormaId")
    @Expose
    private String proFormaId;

    @SerializedName("customerEmail")
    @Expose
    private String customerEmail;
    @SerializedName("cutomerName")
    @Expose
    private String cutomerName;
    @SerializedName("amountReturned")
    @Expose
    private String amountReturned;
    @SerializedName("discountAmount")
    @Expose
    private double discountAmount;
    @SerializedName("totalTenderedAmount")
    @Expose
    private double totalTenderedAmount;
    @SerializedName("advancepayment")
    @Expose
    private boolean advancepayment;
    @SerializedName("custLocation")
    @Expose
    private long custLocation;
    @SerializedName("cmpyLocation")
    @Expose
    private long cmpyLocation;


    @SerializedName("dateOfInvoice")
    @Expose
    private String dateOfInvoice;
    @SerializedName("cessTotalTaxAmt")
    @Expose
    private String cessTotalTaxAmt;

    @SerializedName("from_reg")
    @Expose
    private String from_reg;
    @SerializedName("to_reg")
    @Expose
    private String to_reg;
    @SerializedName("type_doc")
    @Expose
    private String type_doc;

    @SerializedName("invokeOrderId")
    @Expose
    private String invokeOrderId;
    @SerializedName("invokeOrderName")
    @Expose
    private String invokeOrderName;
    @SerializedName("operation")
    @Expose
    private String operation;
    @SerializedName("shippingDate")
    @Expose
    public String shippingDate;
    @SerializedName("serializableItemsDTOList")
    @Expose
    public Object serializableItemsDTOList;

    @SerializedName("salesOrderId")
    @Expose
    private String salesOrderId;

    @SerializedName("salesDeliveryOrderNO")
    @Expose
    private String salesDeliveryOrderNO;
    @SerializedName("salesQuotationId")
    @Expose
    private Long salesQuotationId;

    @SerializedName("srlnNo")
    @Expose
    private String srlnNo;

    @SerializedName("siid")
    @Expose
    private Long siid;
    @SerializedName("exchangerateValue")
    @Expose
    private String exchangerateValue;
    @SerializedName("currencyIdOfInvoice")
    @Expose
    private String currencyIdOfInvoice;

    public String getExchangerateValue() {
        return exchangerateValue;
    }

    public void setExchangerateValue(String exchangerateValue) {
        this.exchangerateValue = exchangerateValue;
    }

    public String getCurrencyIdOfInvoice() {
        return currencyIdOfInvoice;
    }

    public void setCurrencyIdOfInvoice(String currencyIdOfInvoice) {
        this.currencyIdOfInvoice = currencyIdOfInvoice;
    }

    public String getSrlnNo() {
        return srlnNo;
    }

    public void setSrlnNo(String srlnNo) {
        this.srlnNo = srlnNo;
    }

    public Long getSiid() {
        return siid;
    }

    public void setSiid(Long siid) {
        this.siid = siid;
    }

    public String getProFormaId() {
        return proFormaId;
    }

    public void setProFormaId(String proFormaId) {
        this.proFormaId = proFormaId;
    }

    public Long getSalesQuotationId() {
        return salesQuotationId;
    }

    public void setSalesQuotationId(Long salesQuotationId) {
        this.salesQuotationId = salesQuotationId;
    }

    public String getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(String shippingDate) {
        this.shippingDate = shippingDate;
    }

    public Object getSerializableItemsDTOList() {
        return serializableItemsDTOList;
    }

    public void setSerializableItemsDTOList(Object serializableItemsDTOList) {
        this.serializableItemsDTOList = serializableItemsDTOList;
    }

    public String getInvokeOrderId() {
        return invokeOrderId;
    }

    public void setInvokeOrderId(String invokeOrderId) {
        this.invokeOrderId = invokeOrderId;
    }

    public String getInvokeOrderName() {
        return invokeOrderName;
    }

    public void setInvokeOrderName(String invokeOrderName) {
        this.invokeOrderName = invokeOrderName;
    }

    public List<SelectedItemsList> getSelectedItemsList() {
        return selectedItemsList;
    }

    public void setSelectedItemsList(List<SelectedItemsList> selectedItemsList) {
        this.selectedItemsList = selectedItemsList;
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

    public String getTotalCheckOutamt() {
        return totalCheckOutamt;
    }

    public void setTotalCheckOutamt(String totalCheckOutamt) {
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

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCutomerName() {
        return cutomerName;
    }

    public void setCutomerName(String cutomerName) {
        this.cutomerName = cutomerName;
    }

    public String getAmountReturned() {
        return amountReturned;
    }

    public void setAmountReturned(String amountReturned) {
        this.amountReturned = amountReturned;
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

    public boolean isAdvancepayment() {
        return advancepayment;
    }

    public void setAdvancepayment(boolean advancepayment) {
        this.advancepayment = advancepayment;
    }

    public long getCustLocation() {
        return custLocation;
    }

    public void setCustLocation(long custLocation) {
        this.custLocation = custLocation;
    }

    public long getCmpyLocation() {
        return cmpyLocation;
    }

    public void setCmpyLocation(long cmpyLocation) {
        this.cmpyLocation = cmpyLocation;
    }

    public String getDateOfInvoice() {
        return dateOfInvoice;
    }

    public void setDateOfInvoice(String dateOfInvoice) {
        this.dateOfInvoice = dateOfInvoice;
    }

    public String getCessTotalTaxAmt() {
        return cessTotalTaxAmt;
    }

    public void setCessTotalTaxAmt(String cessTotalTaxAmt) {
        this.cessTotalTaxAmt = cessTotalTaxAmt;
    }

    public String getFrom_reg() {
        return from_reg;
    }

    public void setFrom_reg(String from_reg) {
        this.from_reg = from_reg;
    }

    public String getTo_reg() {
        return to_reg;
    }

    public void setTo_reg(String to_reg) {
        this.to_reg = to_reg;
    }

    public String getType_doc() {
        return type_doc;
    }

    public void setType_doc(String type_doc) {
        this.type_doc = type_doc;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getSalesOrderId() {
        return salesOrderId;
    }

    public void setSalesOrderId(String salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public String getSalesDeliveryOrderNO() {
        return salesDeliveryOrderNO;
    }

    public void setSalesDeliveryOrderNO(String salesDeliveryOrderNO) {
        this.salesDeliveryOrderNO = salesDeliveryOrderNO;
    }
}
