package in.hiaccounts.hinext.inventory.model.attribute;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by shrinath on 12/8/2017.
 */

public class AttributeData implements Serializable {
    @SerializedName("first")
    @Expose
    private Boolean first;
    @SerializedName("prev")
    @Expose
    private Boolean prev;
    @SerializedName("next")
    @Expose
    private Boolean next;
    @SerializedName("last")
    @Expose
    private Boolean last;
    @SerializedName("analysisreportList")
    @Expose
    private String analysisreportList;
    @SerializedName("salesReportList")
    @Expose
    private String salesReportList;
    @SerializedName("customerResponseList")
    @Expose
    private String customerResponseList;
    @SerializedName("salesPricingList")
    @Expose
    private String salesPricingList;
    @SerializedName("agentListing")
    @Expose
    private String agentListing;
    @SerializedName("ineventoryReportlist")
    @Expose
    private String ineventoryReportlist;
    @SerializedName("jsonData")
    @Expose
    private String jsonData;
    @SerializedName("supplierSalesReportList")
    @Expose
    private String supplierSalesReportList;
    @SerializedName("creditorsAgeingList")
    @Expose
    private String creditorsAgeingList;
    @SerializedName("supplierReportList")
    @Expose
    private String supplierReportList;
    @SerializedName("customerreceiptList")
    @Expose
    private String customerreceiptList;
    @SerializedName("data")
    @Expose
    private List<Attribute> data = null;
    @SerializedName("appleDTOLists")
    @Expose
    private List<Object> appleDTOLists = null;
    @SerializedName("pageNo")
    @Expose
    private int pageNo;

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

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
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

    public List<Attribute> getData() {
        return data;
    }

    public void setData(List<Attribute> data) {
        this.data = data;
    }

    public List<Object> getAppleDTOLists() {
        return appleDTOLists;
    }

    public void setAppleDTOLists(List<Object> appleDTOLists) {
        this.appleDTOLists = appleDTOLists;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }
}
