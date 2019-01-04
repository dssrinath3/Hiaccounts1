
package in.hiaccounts.hinext.sales.model.sales_pagedata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class HinextSalesPageData implements Serializable {


    @SerializedName("status")
    @Expose
    public long status;

    @SerializedName("taxList")
    @Expose
    public List<TaxList> taxList = null;
    @SerializedName("userRights")
    @Expose
    public UserRights userRights;
    @SerializedName("userAccessRights")
    @Expose
    public UserAccessRights userAccessRights;
    @SerializedName("customers")
    @Expose
    public List<Customer> customers = null;

    @SerializedName("fullUserName")
    @Expose
    public String fullUserName;
    @SerializedName("companyName")
    @Expose
    public String companyName;
    @SerializedName("cmpylocationList")
    @Expose
    public List<CmpylocationList> cmpylocationList = null;
    @SerializedName("custlocationList")
    @Expose
    public List<CustlocationList> custlocationList = null;
    @SerializedName("termsAndConditionList")
    @Expose
    public List<TermsAndConditionList> termsAndConditionList = null;
    @SerializedName("exchangeRateList")
    @Expose
    public List<ExchangeRateList> exchangeRateList = null;
    @SerializedName("currencyList")
    @Expose
    public List<CurrencyList> currencyList = null;

    @SerializedName("countryId")
    @Expose
    public Long countryId;
    @SerializedName("cmpyCurrencyId")
    @Expose
    public Long cmpyCurrencyId;

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
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

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
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

    public List<CmpylocationList> getCmpylocationList() {
        return cmpylocationList;
    }

    public void setCmpylocationList(List<CmpylocationList> cmpylocationList) {
        this.cmpylocationList = cmpylocationList;
    }

    public List<CustlocationList> getCustlocationList() {
        return custlocationList;
    }

    public void setCustlocationList(List<CustlocationList> custlocationList) {
        this.custlocationList = custlocationList;
    }

    public List<TermsAndConditionList> getTermsAndConditionList() {
        return termsAndConditionList;
    }

    public void setTermsAndConditionList(List<TermsAndConditionList> termsAndConditionList) {
        this.termsAndConditionList = termsAndConditionList;
    }

    public List<ExchangeRateList> getExchangeRateList() {
        return exchangeRateList;
    }

    public void setExchangeRateList(List<ExchangeRateList> exchangeRateList) {
        this.exchangeRateList = exchangeRateList;
    }

    public List<CurrencyList> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(List<CurrencyList> currencyList) {
        this.currencyList = currencyList;
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
