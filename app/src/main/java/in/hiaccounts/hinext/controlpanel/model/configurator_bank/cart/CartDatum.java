package in.hiaccounts.hinext.controlpanel.model.configurator_bank.cart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by administrator on 30/1/18.
 */

public class CartDatum implements Serializable {
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
    @SerializedName("analysisreportList")
    @Expose
    public String analysisreportList;
    @SerializedName("salesReportList")
    @Expose
    public String salesReportList;
    @SerializedName("salesInvoiceReportList")
    @Expose
    public String salesInvoiceReportList;
    @SerializedName("customerResponseList")
    @Expose
    public String customerResponseList;
    @SerializedName("salesPricingList")
    @Expose
    public String salesPricingList;
    @SerializedName("agentListing")
    @Expose
    public String agentListing;
    @SerializedName("ineventoryReportlist")
    @Expose
    public String ineventoryReportlist;
    @SerializedName("salesDeliveryReportList")
    @Expose
    public String salesDeliveryReportList;
    @SerializedName("itemReportDto")
    @Expose
    public String itemReportDto;
    @SerializedName("serviceReceiveDelivary")
    @Expose
    public String serviceReceiveDelivary;
    @SerializedName("jsonData")
    @Expose
    public String jsonData;
    @SerializedName("salesDebtorsList")
    @Expose
    public String salesDebtorsList;
    @SerializedName("currencyList")
    @Expose
    public String currencyList;
    @SerializedName("customerList")
    @Expose
    public String customerList;
    @SerializedName("locationList")
    @Expose
    public String locationList;
    @SerializedName("supplierSalesReportList")
    @Expose
    public String supplierSalesReportList;
    @SerializedName("creditorsAgeingList")
    @Expose
    public String creditorsAgeingList;
    @SerializedName("supplierReportList")
    @Expose
    public String supplierReportList;
    @SerializedName("customerreceiptList")
    @Expose
    public String customerreceiptList;
    @SerializedName("data")
    @Expose
    public List<AddCartDatum> data = null;
    @SerializedName("appleDTOLists")
    @Expose
    public List<Object> appleDTOLists = null;
    @SerializedName("pageNo")
    @Expose
    public Integer pageNo;

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

    public String getAnalysisreportList() {
        return analysisreportList;
    }

    public void setAnalysisreportList(String analysisreportList) {
        this.analysisreportList = analysisreportList;
    }

    public String getSalesReportList() {
        return salesReportList;
    }

    public void setSalesReportList(String salesReportList) {
        this.salesReportList = salesReportList;
    }

    public String getSalesInvoiceReportList() {
        return salesInvoiceReportList;
    }

    public void setSalesInvoiceReportList(String salesInvoiceReportList) {
        this.salesInvoiceReportList = salesInvoiceReportList;
    }

    public String getCustomerResponseList() {
        return customerResponseList;
    }

    public void setCustomerResponseList(String customerResponseList) {
        this.customerResponseList = customerResponseList;
    }

    public String getSalesPricingList() {
        return salesPricingList;
    }

    public void setSalesPricingList(String salesPricingList) {
        this.salesPricingList = salesPricingList;
    }

    public String getAgentListing() {
        return agentListing;
    }

    public void setAgentListing(String agentListing) {
        this.agentListing = agentListing;
    }

    public String getIneventoryReportlist() {
        return ineventoryReportlist;
    }

    public void setIneventoryReportlist(String ineventoryReportlist) {
        this.ineventoryReportlist = ineventoryReportlist;
    }

    public String getSalesDeliveryReportList() {
        return salesDeliveryReportList;
    }

    public void setSalesDeliveryReportList(String salesDeliveryReportList) {
        this.salesDeliveryReportList = salesDeliveryReportList;
    }

    public String getItemReportDto() {
        return itemReportDto;
    }

    public void setItemReportDto(String itemReportDto) {
        this.itemReportDto = itemReportDto;
    }

    public String getServiceReceiveDelivary() {
        return serviceReceiveDelivary;
    }

    public void setServiceReceiveDelivary(String serviceReceiveDelivary) {
        this.serviceReceiveDelivary = serviceReceiveDelivary;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    public String getSalesDebtorsList() {
        return salesDebtorsList;
    }

    public void setSalesDebtorsList(String salesDebtorsList) {
        this.salesDebtorsList = salesDebtorsList;
    }

    public String getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(String currencyList) {
        this.currencyList = currencyList;
    }

    public String getCustomerList() {
        return customerList;
    }

    public void setCustomerList(String customerList) {
        this.customerList = customerList;
    }

    public String getLocationList() {
        return locationList;
    }

    public void setLocationList(String locationList) {
        this.locationList = locationList;
    }

    public String getSupplierSalesReportList() {
        return supplierSalesReportList;
    }

    public void setSupplierSalesReportList(String supplierSalesReportList) {
        this.supplierSalesReportList = supplierSalesReportList;
    }

    public String getCreditorsAgeingList() {
        return creditorsAgeingList;
    }

    public void setCreditorsAgeingList(String creditorsAgeingList) {
        this.creditorsAgeingList = creditorsAgeingList;
    }

    public String getSupplierReportList() {
        return supplierReportList;
    }

    public void setSupplierReportList(String supplierReportList) {
        this.supplierReportList = supplierReportList;
    }

    public String getCustomerreceiptList() {
        return customerreceiptList;
    }

    public void setCustomerreceiptList(String customerreceiptList) {
        this.customerreceiptList = customerreceiptList;
    }

    public List<AddCartDatum> getData() {
        return data;
    }

    public void setData(List<AddCartDatum> data) {
        this.data = data;
    }

    public List<Object> getAppleDTOLists() {
        return appleDTOLists;
    }

    public void setAppleDTOLists(List<Object> appleDTOLists) {
        this.appleDTOLists = appleDTOLists;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }
}
