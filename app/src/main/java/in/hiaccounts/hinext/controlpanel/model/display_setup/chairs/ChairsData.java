package in.hiaccounts.hinext.controlpanel.model.display_setup.chairs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ChairsData implements Serializable {
    @SerializedName("first")
    @Expose
    public Boolean first;
    @SerializedName("prev")
    @Expose
    public Boolean prev;
    @SerializedName("next")
    @Expose
    public Boolean next;
    @SerializedName("last")
    @Expose
    public Boolean last;
    @SerializedName("doubleMap")
    @Expose
    public Object doubleMap;
    @SerializedName("itemCategorys")
    @Expose
    public List<Object> itemCategorys = null;
    @SerializedName("analysisreportList")
    @Expose
    public Object analysisreportList;
    @SerializedName("salesReportList")
    @Expose
    public Object salesReportList;
    @SerializedName("salesInvoiceReportList")
    @Expose
    public Object salesInvoiceReportList;
    @SerializedName("customerResponseList")
    @Expose
    public Object customerResponseList;
    @SerializedName("salesPricingList")
    @Expose
    public Object salesPricingList;
    @SerializedName("agentListing")
    @Expose
    public Object agentListing;
    @SerializedName("ineventoryReportlist")
    @Expose
    public Object ineventoryReportlist;
    @SerializedName("salesDeliveryReportList")
    @Expose
    public Object salesDeliveryReportList;
    @SerializedName("estimatedPaymentDetailsPojos")
    @Expose
    public Object estimatedPaymentDetailsPojos;
    @SerializedName("itemReportDto")
    @Expose
    public Object itemReportDto;
    @SerializedName("serviceReceiveDelivary")
    @Expose
    public Object serviceReceiveDelivary;
    @SerializedName("viewLedgerResponsePojos")
    @Expose
    public Object viewLedgerResponsePojos;
    @SerializedName("dayEndPojos")
    @Expose
    public Object dayEndPojos;
    @SerializedName("total")
    @Expose
    public Double total;
    @SerializedName("jsonData")
    @Expose
    public Object jsonData;
    @SerializedName("salesDebtorsList")
    @Expose
    public Object salesDebtorsList;
    @SerializedName("currencyList")
    @Expose
    public Object currencyList;
    @SerializedName("customerList")
    @Expose
    public Object customerList;
    @SerializedName("locationList")
    @Expose
    public Object locationList;
    @SerializedName("supplierSalesReportList")
    @Expose
    public Object supplierSalesReportList;
    @SerializedName("creditorsAgeingList")
    @Expose
    public Object creditorsAgeingList;
    @SerializedName("supplierReportList")
    @Expose
    public Object supplierReportList;
    @SerializedName("customerreceiptList")
    @Expose
    public Object customerreceiptList;
    @SerializedName("data")
    @Expose
    public List<ChairsDataList> data = null;
    @SerializedName("pageNo")
    @Expose
    public Long pageNo;
    @SerializedName("appleDTOLists")
    @Expose
    public List<Object> appleDTOLists = null;

    public Boolean getFirst() {
        return first;
    }

    public void setFirst(Boolean first) {
        this.first = first;
    }

    public Boolean getPrev() {
        return prev;
    }

    public void setPrev(Boolean prev) {
        this.prev = prev;
    }

    public Boolean getNext() {
        return next;
    }

    public void setNext(Boolean next) {
        this.next = next;
    }

    public Boolean getLast() {
        return last;
    }

    public void setLast(Boolean last) {
        this.last = last;
    }

    public Object getDoubleMap() {
        return doubleMap;
    }

    public void setDoubleMap(Object doubleMap) {
        this.doubleMap = doubleMap;
    }

    public List<Object> getItemCategorys() {
        return itemCategorys;
    }

    public void setItemCategorys(List<Object> itemCategorys) {
        this.itemCategorys = itemCategorys;
    }

    public Object getAnalysisreportList() {
        return analysisreportList;
    }

    public void setAnalysisreportList(Object analysisreportList) {
        this.analysisreportList = analysisreportList;
    }

    public Object getSalesReportList() {
        return salesReportList;
    }

    public void setSalesReportList(Object salesReportList) {
        this.salesReportList = salesReportList;
    }

    public Object getSalesInvoiceReportList() {
        return salesInvoiceReportList;
    }

    public void setSalesInvoiceReportList(Object salesInvoiceReportList) {
        this.salesInvoiceReportList = salesInvoiceReportList;
    }

    public Object getCustomerResponseList() {
        return customerResponseList;
    }

    public void setCustomerResponseList(Object customerResponseList) {
        this.customerResponseList = customerResponseList;
    }

    public Object getSalesPricingList() {
        return salesPricingList;
    }

    public void setSalesPricingList(Object salesPricingList) {
        this.salesPricingList = salesPricingList;
    }

    public Object getAgentListing() {
        return agentListing;
    }

    public void setAgentListing(Object agentListing) {
        this.agentListing = agentListing;
    }

    public Object getIneventoryReportlist() {
        return ineventoryReportlist;
    }

    public void setIneventoryReportlist(Object ineventoryReportlist) {
        this.ineventoryReportlist = ineventoryReportlist;
    }

    public Object getSalesDeliveryReportList() {
        return salesDeliveryReportList;
    }

    public void setSalesDeliveryReportList(Object salesDeliveryReportList) {
        this.salesDeliveryReportList = salesDeliveryReportList;
    }

    public Object getEstimatedPaymentDetailsPojos() {
        return estimatedPaymentDetailsPojos;
    }

    public void setEstimatedPaymentDetailsPojos(Object estimatedPaymentDetailsPojos) {
        this.estimatedPaymentDetailsPojos = estimatedPaymentDetailsPojos;
    }

    public Object getItemReportDto() {
        return itemReportDto;
    }

    public void setItemReportDto(Object itemReportDto) {
        this.itemReportDto = itemReportDto;
    }

    public Object getServiceReceiveDelivary() {
        return serviceReceiveDelivary;
    }

    public void setServiceReceiveDelivary(Object serviceReceiveDelivary) {
        this.serviceReceiveDelivary = serviceReceiveDelivary;
    }

    public Object getViewLedgerResponsePojos() {
        return viewLedgerResponsePojos;
    }

    public void setViewLedgerResponsePojos(Object viewLedgerResponsePojos) {
        this.viewLedgerResponsePojos = viewLedgerResponsePojos;
    }

    public Object getDayEndPojos() {
        return dayEndPojos;
    }

    public void setDayEndPojos(Object dayEndPojos) {
        this.dayEndPojos = dayEndPojos;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Object getJsonData() {
        return jsonData;
    }

    public void setJsonData(Object jsonData) {
        this.jsonData = jsonData;
    }

    public Object getSalesDebtorsList() {
        return salesDebtorsList;
    }

    public void setSalesDebtorsList(Object salesDebtorsList) {
        this.salesDebtorsList = salesDebtorsList;
    }

    public Object getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(Object currencyList) {
        this.currencyList = currencyList;
    }

    public Object getCustomerList() {
        return customerList;
    }

    public void setCustomerList(Object customerList) {
        this.customerList = customerList;
    }

    public Object getLocationList() {
        return locationList;
    }

    public void setLocationList(Object locationList) {
        this.locationList = locationList;
    }

    public Object getSupplierSalesReportList() {
        return supplierSalesReportList;
    }

    public void setSupplierSalesReportList(Object supplierSalesReportList) {
        this.supplierSalesReportList = supplierSalesReportList;
    }

    public Object getCreditorsAgeingList() {
        return creditorsAgeingList;
    }

    public void setCreditorsAgeingList(Object creditorsAgeingList) {
        this.creditorsAgeingList = creditorsAgeingList;
    }

    public Object getSupplierReportList() {
        return supplierReportList;
    }

    public void setSupplierReportList(Object supplierReportList) {
        this.supplierReportList = supplierReportList;
    }

    public Object getCustomerreceiptList() {
        return customerreceiptList;
    }

    public void setCustomerreceiptList(Object customerreceiptList) {
        this.customerreceiptList = customerreceiptList;
    }

    public List<ChairsDataList> getData() {
        return data;
    }

    public void setData(List<ChairsDataList> data) {
        this.data = data;
    }

    public Long getPageNo() {
        return pageNo;
    }

    public void setPageNo(Long pageNo) {
        this.pageNo = pageNo;
    }

    public List<Object> getAppleDTOLists() {
        return appleDTOLists;
    }

    public void setAppleDTOLists(List<Object> appleDTOLists) {
        this.appleDTOLists = appleDTOLists;
    }
}
