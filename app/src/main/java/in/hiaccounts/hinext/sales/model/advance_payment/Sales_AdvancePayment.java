package in.hiaccounts.hinext.sales.model.advance_payment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Sales_AdvancePayment  {
    @SerializedName("invokeOrderId")
    @Expose
    public String invokeOrderId;
    @SerializedName("invokeOrderName")
    @Expose
    public String invokeOrderName;
    @SerializedName("operation")
    @Expose
    public String operation;
    @SerializedName("selectedItemsList")
    @Expose
    public List<Object> selectedItemsList = null;
    @SerializedName("totalCheckOutamt")
    @Expose
    public Long totalCheckOutamt;
    @SerializedName("paymentType")
    @Expose
    public String paymentType;
    @SerializedName("totalTaxAmt")
    @Expose
    public Long totalTaxAmt;
    @SerializedName("cessTotalTaxAmt")
    @Expose
    public String cessTotalTaxAmt;
    @SerializedName("taxType")
    @Expose
    public String taxType;
    @SerializedName("customerId")
    @Expose
    public Long customerId;
    @SerializedName("customerEmail")
    @Expose
    public String customerEmail;
    @SerializedName("transporterId")
    @Expose
    public String transporterId;
    @SerializedName("vehicleNo")
    @Expose
    public String vehicleNo;
    @SerializedName("ewayBillNo")
    @Expose
    public String ewayBillNo;
    @SerializedName("distance")
    @Expose
    public String distance;
    @SerializedName("cutomerName")
    @Expose
    public String cutomerName;
    @SerializedName("customerPo")
    @Expose
    public String customerPo;
    @SerializedName("from_reg")
    @Expose
    public String fromReg;
    @SerializedName("to_reg")
    @Expose
    public String toReg;
    @SerializedName("type_doc")
    @Expose
    public String typeDoc;
    @SerializedName("dateOfInvoice")
    @Expose
    public String dateOfInvoice;
    @SerializedName("cmpyLocation")
    @Expose
    public Long cmpyLocation;
    @SerializedName("custLocation")
    @Expose
    public Long custLocation;
    @SerializedName("exchangeRateIdOfInvoice")
    @Expose
    public Long exchangeRateIdOfInvoice;
    @SerializedName("currencyIdOfInvoice")
    @Expose
    public Long currencyIdOfInvoice;
    @SerializedName("termCondIdOfInvoice")
    @Expose
    public String termCondIdOfInvoice;
    @SerializedName("agentIdOfInvoice")
    @Expose
    public String agentIdOfInvoice;
    @SerializedName("projectIdOfInvoice")
    @Expose
    public String projectIdOfInvoice;
    @SerializedName("employeeId")
    @Expose
    public String employeeId;
    @SerializedName("shippingmethodId")
    @Expose
    public String shippingmethodId;
    @SerializedName("serializableItemId")
    @Expose
    public Object serializableItemId;
    @SerializedName("referenceNo")
    @Expose
    public String referenceNo;
    @SerializedName("shippingReferenceNo")
    @Expose
    public String shippingReferenceNo;
    @SerializedName("shippingDate")
    @Expose
    public String shippingDate;
    @SerializedName("salesOrderId")
    @Expose
    public Object salesOrderId;
    @SerializedName("salesOrderDetailsId")
    @Expose
    public Object salesOrderDetailsId;
    @SerializedName("salesQuotationId")
    @Expose
    public Object salesQuotationId;
    @SerializedName("otherContactId")
    @Expose
    public String otherContactId;
    @SerializedName("salesQuotationDetailsId")
    @Expose
    public Object salesQuotationDetailsId;
    @SerializedName("salesDeliveryOrderNO")
    @Expose
    public String salesDeliveryOrderNO;
    @SerializedName("proFormaId")
    @Expose
    public Object proFormaId;
    @SerializedName("proFormaDetailsId")
    @Expose
    public Object proFormaDetailsId;
    @SerializedName("exchangerateValue")
    @Expose
    public String exchangerateValue;
    @SerializedName("checkForHoldStock")
    @Expose
    public String checkForHoldStock;

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

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public List<Object> getSelectedItemsList() {
        return selectedItemsList;
    }

    public void setSelectedItemsList(List<Object> selectedItemsList) {
        this.selectedItemsList = selectedItemsList;
    }

    public Long getTotalCheckOutamt() {
        return totalCheckOutamt;
    }

    public void setTotalCheckOutamt(Long totalCheckOutamt) {
        this.totalCheckOutamt = totalCheckOutamt;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Long getTotalTaxAmt() {
        return totalTaxAmt;
    }

    public void setTotalTaxAmt(Long totalTaxAmt) {
        this.totalTaxAmt = totalTaxAmt;
    }

    public String getCessTotalTaxAmt() {
        return cessTotalTaxAmt;
    }

    public void setCessTotalTaxAmt(String cessTotalTaxAmt) {
        this.cessTotalTaxAmt = cessTotalTaxAmt;
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

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getTransporterId() {
        return transporterId;
    }

    public void setTransporterId(String transporterId) {
        this.transporterId = transporterId;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getEwayBillNo() {
        return ewayBillNo;
    }

    public void setEwayBillNo(String ewayBillNo) {
        this.ewayBillNo = ewayBillNo;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getCutomerName() {
        return cutomerName;
    }

    public void setCutomerName(String cutomerName) {
        this.cutomerName = cutomerName;
    }

    public String getCustomerPo() {
        return customerPo;
    }

    public void setCustomerPo(String customerPo) {
        this.customerPo = customerPo;
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

    public String getDateOfInvoice() {
        return dateOfInvoice;
    }

    public void setDateOfInvoice(String dateOfInvoice) {
        this.dateOfInvoice = dateOfInvoice;
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

    public Long getExchangeRateIdOfInvoice() {
        return exchangeRateIdOfInvoice;
    }

    public void setExchangeRateIdOfInvoice(Long exchangeRateIdOfInvoice) {
        this.exchangeRateIdOfInvoice = exchangeRateIdOfInvoice;
    }

    public Long getCurrencyIdOfInvoice() {
        return currencyIdOfInvoice;
    }

    public void setCurrencyIdOfInvoice(Long currencyIdOfInvoice) {
        this.currencyIdOfInvoice = currencyIdOfInvoice;
    }

    public String getTermCondIdOfInvoice() {
        return termCondIdOfInvoice;
    }

    public void setTermCondIdOfInvoice(String termCondIdOfInvoice) {
        this.termCondIdOfInvoice = termCondIdOfInvoice;
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

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getShippingmethodId() {
        return shippingmethodId;
    }

    public void setShippingmethodId(String shippingmethodId) {
        this.shippingmethodId = shippingmethodId;
    }

    public Object getSerializableItemId() {
        return serializableItemId;
    }

    public void setSerializableItemId(Object serializableItemId) {
        this.serializableItemId = serializableItemId;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public String getShippingReferenceNo() {
        return shippingReferenceNo;
    }

    public void setShippingReferenceNo(String shippingReferenceNo) {
        this.shippingReferenceNo = shippingReferenceNo;
    }

    public String getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(String shippingDate) {
        this.shippingDate = shippingDate;
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

    public Object getSalesQuotationId() {
        return salesQuotationId;
    }

    public void setSalesQuotationId(Object salesQuotationId) {
        this.salesQuotationId = salesQuotationId;
    }

    public String getOtherContactId() {
        return otherContactId;
    }

    public void setOtherContactId(String otherContactId) {
        this.otherContactId = otherContactId;
    }

    public Object getSalesQuotationDetailsId() {
        return salesQuotationDetailsId;
    }

    public void setSalesQuotationDetailsId(Object salesQuotationDetailsId) {
        this.salesQuotationDetailsId = salesQuotationDetailsId;
    }

    public String getSalesDeliveryOrderNO() {
        return salesDeliveryOrderNO;
    }

    public void setSalesDeliveryOrderNO(String salesDeliveryOrderNO) {
        this.salesDeliveryOrderNO = salesDeliveryOrderNO;
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

    public String getExchangerateValue() {
        return exchangerateValue;
    }

    public void setExchangerateValue(String exchangerateValue) {
        this.exchangerateValue = exchangerateValue;
    }

    public String getCheckForHoldStock() {
        return checkForHoldStock;
    }

    public void setCheckForHoldStock(String checkForHoldStock) {
        this.checkForHoldStock = checkForHoldStock;
    }
}
