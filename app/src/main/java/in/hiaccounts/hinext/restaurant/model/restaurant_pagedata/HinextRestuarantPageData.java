package in.hiaccounts.hinext.restaurant.model.restaurant_pagedata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import in.hiaccounts.hinext.controlpanel.model.configurator_bank.employee.EmployeeList;


/**
 * Created by shrinath on 11/21/2017.
 */

public class HinextRestuarantPageData implements Serializable {

    @SerializedName("message")
    @Expose
    private Object message;
    @SerializedName("status")
    @Expose
    private long status;
    @SerializedName("customerAccountList")
    @Expose
    private List<Object> customerAccountList = null;
    @SerializedName("taxList")
    @Expose
    private List<TaxList> taxList = null;
    @SerializedName("userRights")
    @Expose
    private UserRights userRights;
    @SerializedName("userAccessRights")
    @Expose
    private Object userAccessRights;
    @SerializedName("customers")
    @Expose
    private List<Customer> customers = null;
    @SerializedName("tableConfigDetails")
    @Expose
    private List<TableConfigDetail> tableConfigDetails = null;
    @SerializedName("itemCategorys")
    @Expose
    private List<ItemCategory> itemCategorys = null;
    @SerializedName("hiPosServiceCharge")
    @Expose
    private Object hiPosServiceCharge;
    @SerializedName("companyLogoPath")
    @Expose
    private Object companyLogoPath;
    @SerializedName("fullUserName")
    @Expose
    private String fullUserName;
    @SerializedName("companyName")
    @Expose
    private String companyName;
    @SerializedName("cmpylocationList")
    @Expose
    private List<Object> cmpylocationList = null;
    @SerializedName("custlocationList")
    @Expose
    private List<Object> custlocationList = null;
    @SerializedName("termsAndConditionList")
    @Expose
    private List<Object> termsAndConditionList = null;
    @SerializedName("exchangeRateList")
    @Expose
    private List<Object> exchangeRateList = null;
    @SerializedName("agentList")
    @Expose
    private List<Object> agentList = null;
    @SerializedName("currencyList")
    @Expose
    private List<Object> currencyList = null;
    @SerializedName("projectList")
    @Expose
    private List<Object> projectList = null;
    @SerializedName("shippingMethodList")
    @Expose
    private List<Object> shippingMethodList = null;
    @SerializedName("employeeList")
    @Expose
    public List<EmployeeList> employeeList = null;
    @SerializedName("budgetList")
    @Expose
    private Object budgetList;
    @SerializedName("menuTypeGold")
    @Expose
    private Object menuTypeGold;
    @SerializedName("menuTypeResturant")
    @Expose
    private Object menuTypeResturant;
    @SerializedName("menuTypePharma")
    @Expose
    private Object menuTypePharma;
    @SerializedName("menuTypeMobile")
    @Expose
    private Object menuTypeMobile;

    public List<EmployeeList> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<EmployeeList> employeeList) {
        this.employeeList = employeeList;
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

    public List<Object> getCustomerAccountList() {
        return customerAccountList;
    }

    public void setCustomerAccountList(List<Object> customerAccountList) {
        this.customerAccountList = customerAccountList;
    }

    public List<TaxList> getTaxList() {
        return taxList;
    }

    public void setTaxList(List<TaxList> taxList) {
        this.taxList = taxList;
    }

    public UserRights getUserRights() {
        return userRights;
    }

    public void setUserRights(UserRights userRights) {
        this.userRights = userRights;
    }

    public Object getUserAccessRights() {
        return userAccessRights;
    }

    public void setUserAccessRights(Object userAccessRights) {
        this.userAccessRights = userAccessRights;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<TableConfigDetail> getTableConfigDetails() {
        return tableConfigDetails;
    }

    public void setTableConfigDetails(List<TableConfigDetail> tableConfigDetails) {
        this.tableConfigDetails = tableConfigDetails;
    }

    public List<ItemCategory> getItemCategorys() {
        return itemCategorys;
    }

    public void setItemCategorys(List<ItemCategory> itemCategorys) {
        this.itemCategorys = itemCategorys;
    }

    public Object getHiPosServiceCharge() {
        return hiPosServiceCharge;
    }

    public void setHiPosServiceCharge(Object hiPosServiceCharge) {
        this.hiPosServiceCharge = hiPosServiceCharge;
    }

    public Object getCompanyLogoPath() {
        return companyLogoPath;
    }

    public void setCompanyLogoPath(Object companyLogoPath) {
        this.companyLogoPath = companyLogoPath;
    }

    public String getFullUserName() {
        return fullUserName;
    }

    public void setFullUserName(String fullUserName) {
        this.fullUserName = fullUserName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<Object> getCmpylocationList() {
        return cmpylocationList;
    }

    public void setCmpylocationList(List<Object> cmpylocationList) {
        this.cmpylocationList = cmpylocationList;
    }

    public List<Object> getCustlocationList() {
        return custlocationList;
    }

    public void setCustlocationList(List<Object> custlocationList) {
        this.custlocationList = custlocationList;
    }

    public List<Object> getTermsAndConditionList() {
        return termsAndConditionList;
    }

    public void setTermsAndConditionList(List<Object> termsAndConditionList) {
        this.termsAndConditionList = termsAndConditionList;
    }

    public List<Object> getExchangeRateList() {
        return exchangeRateList;
    }

    public void setExchangeRateList(List<Object> exchangeRateList) {
        this.exchangeRateList = exchangeRateList;
    }

    public List<Object> getAgentList() {
        return agentList;
    }

    public void setAgentList(List<Object> agentList) {
        this.agentList = agentList;
    }

    public List<Object> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(List<Object> currencyList) {
        this.currencyList = currencyList;
    }

    public List<Object> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Object> projectList) {
        this.projectList = projectList;
    }

    public List<Object> getShippingMethodList() {
        return shippingMethodList;
    }

    public void setShippingMethodList(List<Object> shippingMethodList) {
        this.shippingMethodList = shippingMethodList;
    }

    public Object getBudgetList() {
        return budgetList;
    }

    public void setBudgetList(Object budgetList) {
        this.budgetList = budgetList;
    }

    public Object getMenuTypeGold() {
        return menuTypeGold;
    }

    public void setMenuTypeGold(Object menuTypeGold) {
        this.menuTypeGold = menuTypeGold;
    }

    public Object getMenuTypeResturant() {
        return menuTypeResturant;
    }

    public void setMenuTypeResturant(Object menuTypeResturant) {
        this.menuTypeResturant = menuTypeResturant;
    }

    public Object getMenuTypePharma() {
        return menuTypePharma;
    }

    public void setMenuTypePharma(Object menuTypePharma) {
        this.menuTypePharma = menuTypePharma;
    }

    public Object getMenuTypeMobile() {
        return menuTypeMobile;
    }

    public void setMenuTypeMobile(Object menuTypeMobile) {
        this.menuTypeMobile = menuTypeMobile;
    }
}
