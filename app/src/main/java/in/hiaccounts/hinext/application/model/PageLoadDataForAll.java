package in.hiaccounts.hinext.application.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


import in.hiaccounts.hinext.purchase.model.purchase_pagedata.CmpylocationList;
import in.hiaccounts.hinext.purchase.model.purchase_pagedata.CurrencyList;
import in.hiaccounts.hinext.purchase.model.purchase_pagedata.ExchangeRateList;
import in.hiaccounts.hinext.purchase.model.purchase_pagedata.TaxList;
import in.hiaccounts.hinext.purchase.model.purchase_pagedata.TermsAndConditionList;
import in.hiaccounts.hinext.sales.model.sales_pagedata.CustlocationList;

/**
 * Created by administrator on 12/2/18.
 */

public class PageLoadDataForAll implements Serializable {
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("customerAccountList")
    @Expose
    public List<Object> customerAccountList = null;
    @SerializedName("taxList")
    @Expose
    public List<TaxList> taxList = null;
    @SerializedName("customers")
    @Expose
    public List<Customer> customers = null;
    @SerializedName("userRights")
    @Expose
    public UserRights userRights;
    @SerializedName("userAccessRights")
    @Expose
    public UserAccessRights userAccessRights;
    /*@SerializedName("customers")
    @Expose
    public List<Customer> customers = null;
    @SerializedName("tableConfigDetails")
    @Expose
    public List<Object> tableConfigDetails = null;
    @SerializedName("itemCategorys")
    @Expose
    public List<Object> itemCategorys = null;
    @SerializedName("hiPosServiceCharge")
    @Expose
    public String hiPosServiceCharge;
    @SerializedName("companyLogoPath")
    @Expose
    public String companyLogoPath;
    @SerializedName("fullUserName")
    @Expose
    public String fullUserName;
    @SerializedName("companyName")
    @Expose
    public String companyName;*/
    /*@SerializedName("tableConfigs")
    @Expose
    public List<Object> tableConfigs = null;
    @SerializedName("attributeConfiguratorDTOList")
    @Expose
    public List<Object> attributeConfiguratorDTOList = null;
    @SerializedName("cmpylocationList")
    @Expose
    public List<CmpylocationList> cmpylocationList = null;
    @SerializedName("employeeList")
    @Expose
    public List<Object> employeeList = null;
    @SerializedName("custlocationList")
    @Expose
    public List<CustlocationList> custlocationList = null;
    @SerializedName("termsAndConditionList")
    @Expose
    public List<TermsAndConditionList> termsAndConditionList = null;
    @SerializedName("exchangeRateList")
    @Expose
    public List<ExchangeRateList> exchangeRateList = null;
    @SerializedName("agentList")
    @Expose
    public List<Object> agentList = null;
    @SerializedName("currencyList")
    @Expose
    public List<CurrencyList> currencyList = null;
    @SerializedName("projectList")
    @Expose
    public List<Object> projectList = null;
    @SerializedName("shippingMethodList")
    @Expose
    public List<Object> shippingMethodList = null;
    @SerializedName("budgetList")
    @Expose
    public List<Object> budgetList = null;*/
    @SerializedName("menuTypeGold")
    @Expose
    public Object menuTypeGold;
    @SerializedName("menuTypeResturant")
    @Expose
    public Object menuTypeResturant;
    @SerializedName("menuTypePharma")
    @Expose
    public Object menuTypePharma;
    @SerializedName("menuTypeMobile")
    @Expose
    public Object menuTypeMobile;
    @SerializedName("countryId")
    @Expose
    public Long countryId;
    @SerializedName("cmpyCurrencyId")
    @Expose
    public Long cmpyCurrencyId;

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public UserAccessRights getUserAccessRights() {
        return userAccessRights;
    }

    public void setUserAccessRights(UserAccessRights userAccessRights) {
        this.userAccessRights = userAccessRights;
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

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Long getCmpyCurrencyId() {
        return cmpyCurrencyId;
    }

    public void setCmpyCurrencyId(Long cmpyCurrencyId) {
        this.cmpyCurrencyId = cmpyCurrencyId;
    }
}
