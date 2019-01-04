
package in.hiaccounts.hinext.purchase.model.purchase_pagedata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class HinextPurchasePageData implements Serializable{

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("supplierAccountList")
    @Expose
    private List<Object> supplierAccountList = null;
    @SerializedName("taxList")
    @Expose
    private List<TaxList> taxList = null;
    @SerializedName("userRights")
    @Expose
    private UserRights userRights;
    @SerializedName("suppliers")
    @Expose
    private List<Supplier> suppliers = null;
    @SerializedName("tableConfigDetails")
    @Expose
    private List<Object> tableConfigDetails = null;
    @SerializedName("itemCategorys")
    @Expose
    private List<Object> itemCategorys = null;
    @SerializedName("hiPosServiceCharge")
    @Expose
    private Object hiPosServiceCharge;
    @SerializedName("companyLogoPath")
    @Expose
    private Object companyLogoPath;
    @SerializedName("companyName")
    @Expose
    private String companyName;
    @SerializedName("fullUserName")
    @Expose
    private String fullUserName;
    @SerializedName("cmpylocationList")
    @Expose
    private List<CmpylocationList> cmpylocationList = null;
    @SerializedName("supplocationList")
    @Expose
    private List<SupplocationList> supplocationList = null;
    @SerializedName("termsAndConditionList")
    @Expose
    private List<TermsAndConditionList> termsAndConditionList = null;
    @SerializedName("exchangeRateList")
    @Expose
    private List<ExchangeRateList> exchangeRateList = null;
    @SerializedName("agentList")
    @Expose
    private List<Object> agentList = null;
    @SerializedName("currencyList")
    @Expose
    private List<CurrencyList> currencyList = null;
    @SerializedName("projectList")
    @Expose
    private List<Object> projectList = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public HinextPurchasePageData() {
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Object> getSupplierAccountList() {
        return supplierAccountList;
    }

    public void setSupplierAccountList(List<Object> supplierAccountList) {
        this.supplierAccountList = supplierAccountList;
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

    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    public List<Object> getTableConfigDetails() {
        return tableConfigDetails;
    }

    public void setTableConfigDetails(List<Object> tableConfigDetails) {
        this.tableConfigDetails = tableConfigDetails;
    }

    public List<Object> getItemCategorys() {
        return itemCategorys;
    }

    public void setItemCategorys(List<Object> itemCategorys) {
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getFullUserName() {
        return fullUserName;
    }

    public void setFullUserName(String fullUserName) {
        this.fullUserName = fullUserName;
    }

    public List<CmpylocationList> getCmpylocationList() {
        return cmpylocationList;
    }

    public void setCmpylocationList(List<CmpylocationList> cmpylocationList) {
        this.cmpylocationList = cmpylocationList;
    }

    public List<SupplocationList> getSupplocationList() {
        return supplocationList;
    }

    public void setSupplocationList(List<SupplocationList> supplocationList) {
        this.supplocationList = supplocationList;
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

    public List<Object> getAgentList() {
        return agentList;
    }

    public void setAgentList(List<Object> agentList) {
        this.agentList = agentList;
    }

    public List<CurrencyList> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(List<CurrencyList> currencyList) {
        this.currencyList = currencyList;
    }

    public List<Object> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Object> projectList) {
        this.projectList = projectList;
    }

}
