package in.hiaccounts.hinext.reports.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SalesReportData {
    @SerializedName("currencyList")
    @Expose
    public List<CurrencyList> currencyList = null;
    @SerializedName("customerList")
    @Expose
    public List<CustomerList> customerList = null;
    @SerializedName("supplierList")
    @Expose
    public Object supplierList;
    @SerializedName("locationList")
    @Expose
    public List<LocationList> locationList = null;
    @SerializedName("salesList")
    @Expose
    public List<SalesList> salesList = null;
    @SerializedName("cancelPI")
    @Expose
    public Object cancelPI;
    @SerializedName("itemList")
    @Expose
    public List<ItemList> itemList = null;
    @SerializedName("employeeList")
    @Expose
    public Object employeeList;
    @SerializedName("salesPricingList")
    @Expose
    public Object salesPricingList;
    @SerializedName("itemCategoryList")
    @Expose
    public Object itemCategoryList;
    @SerializedName("salesResposePojoList")
    @Expose
    public Object salesResposePojoList;
    @SerializedName("salesReportList")
    @Expose
    public Object salesReportList;
    @SerializedName("salesCreditorsList")
    @Expose
    public Object salesCreditorsList;
    @SerializedName("salesDeliverOrderDetailsPojoList")
    @Expose
    public Object salesDeliverOrderDetailsPojoList;
    @SerializedName("estimatedPaymentDetailsPojos")
    @Expose
    public Object estimatedPaymentDetailsPojos;
    @SerializedName("deliverList")
    @Expose
    public Object deliverList;
    @SerializedName("estimatedPaymentDetailsPojoList")
    @Expose
    public Object estimatedPaymentDetailsPojoList;
    @SerializedName("salesDebtorsList")
    @Expose
    public Object salesDebtorsList;
    @SerializedName("agentList")
    @Expose
    public Object agentList;
    @SerializedName("salesInvoiceList")
    @Expose
    public Object salesInvoiceList;
    @SerializedName("projectList")
    @Expose
    public Object projectList;
    @SerializedName("paymentList")
    @Expose
    public Object paymentList;
    @SerializedName("cmpyCurrency")
    @Expose
    public String cmpyCurrency;
    @SerializedName("userLocation")
    @Expose
    public String userLocation;

    public List<CurrencyList> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(List<CurrencyList> currencyList) {
        this.currencyList = currencyList;
    }

    public List<CustomerList> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<CustomerList> customerList) {
        this.customerList = customerList;
    }

    public Object getSupplierList() {
        return supplierList;
    }

    public void setSupplierList(Object supplierList) {
        this.supplierList = supplierList;
    }

    public List<LocationList> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<LocationList> locationList) {
        this.locationList = locationList;
    }

    public List<SalesList> getSalesList() {
        return salesList;
    }

    public void setSalesList(List<SalesList> salesList) {
        this.salesList = salesList;
    }

    public Object getCancelPI() {
        return cancelPI;
    }

    public void setCancelPI(Object cancelPI) {
        this.cancelPI = cancelPI;
    }

    public List<ItemList> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemList> itemList) {
        this.itemList = itemList;
    }

    public Object getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(Object employeeList) {
        this.employeeList = employeeList;
    }

    public Object getSalesPricingList() {
        return salesPricingList;
    }

    public void setSalesPricingList(Object salesPricingList) {
        this.salesPricingList = salesPricingList;
    }

    public Object getItemCategoryList() {
        return itemCategoryList;
    }

    public void setItemCategoryList(Object itemCategoryList) {
        this.itemCategoryList = itemCategoryList;
    }

    public Object getSalesResposePojoList() {
        return salesResposePojoList;
    }

    public void setSalesResposePojoList(Object salesResposePojoList) {
        this.salesResposePojoList = salesResposePojoList;
    }

    public Object getSalesReportList() {
        return salesReportList;
    }

    public void setSalesReportList(Object salesReportList) {
        this.salesReportList = salesReportList;
    }

    public Object getSalesCreditorsList() {
        return salesCreditorsList;
    }

    public void setSalesCreditorsList(Object salesCreditorsList) {
        this.salesCreditorsList = salesCreditorsList;
    }

    public Object getSalesDeliverOrderDetailsPojoList() {
        return salesDeliverOrderDetailsPojoList;
    }

    public void setSalesDeliverOrderDetailsPojoList(Object salesDeliverOrderDetailsPojoList) {
        this.salesDeliverOrderDetailsPojoList = salesDeliverOrderDetailsPojoList;
    }

    public Object getEstimatedPaymentDetailsPojos() {
        return estimatedPaymentDetailsPojos;
    }

    public void setEstimatedPaymentDetailsPojos(Object estimatedPaymentDetailsPojos) {
        this.estimatedPaymentDetailsPojos = estimatedPaymentDetailsPojos;
    }

    public Object getDeliverList() {
        return deliverList;
    }

    public void setDeliverList(Object deliverList) {
        this.deliverList = deliverList;
    }

    public Object getEstimatedPaymentDetailsPojoList() {
        return estimatedPaymentDetailsPojoList;
    }

    public void setEstimatedPaymentDetailsPojoList(Object estimatedPaymentDetailsPojoList) {
        this.estimatedPaymentDetailsPojoList = estimatedPaymentDetailsPojoList;
    }

    public Object getSalesDebtorsList() {
        return salesDebtorsList;
    }

    public void setSalesDebtorsList(Object salesDebtorsList) {
        this.salesDebtorsList = salesDebtorsList;
    }

    public Object getAgentList() {
        return agentList;
    }

    public void setAgentList(Object agentList) {
        this.agentList = agentList;
    }

    public Object getSalesInvoiceList() {
        return salesInvoiceList;
    }

    public void setSalesInvoiceList(Object salesInvoiceList) {
        this.salesInvoiceList = salesInvoiceList;
    }

    public Object getProjectList() {
        return projectList;
    }

    public void setProjectList(Object projectList) {
        this.projectList = projectList;
    }

    public Object getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(Object paymentList) {
        this.paymentList = paymentList;
    }

    public String getCmpyCurrency() {
        return cmpyCurrency;
    }

    public void setCmpyCurrency(String cmpyCurrency) {
        this.cmpyCurrency = cmpyCurrency;
    }

    public String getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(String userLocation) {
        this.userLocation = userLocation;
    }
}
