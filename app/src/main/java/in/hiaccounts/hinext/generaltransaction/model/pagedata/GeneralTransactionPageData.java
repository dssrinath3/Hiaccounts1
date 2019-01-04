
package in.hiaccounts.hinext.generaltransaction.model.pagedata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class GeneralTransactionPageData implements Serializable{

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
    private List<Object> cmpylocationList = null;
    @SerializedName("supplocationList")
    @Expose
    private List<Object> supplocationList = null;
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
    public GeneralTransactionPageData() {
    }

    /**
     *
     * @param currencyList
     * @param tableConfigDetails
     * @param companyLogoPath
     * @param supplocationList
     * @param agentList
     * @param termsAndConditionList
     * @param status
     * @param fullUserName
     * @param companyName
     * @param cmpylocationList
     * @param suppliers
     * @param projectList
     * @param message
     * @param taxList
     * @param supplierAccountList
     * @param itemCategorys
     * @param exchangeRateList
     * @param userRights
     * @param hiPosServiceCharge
     */
    public GeneralTransactionPageData(String message, int status, List<Object> supplierAccountList, List<TaxList> taxList, UserRights userRights, List<Supplier> suppliers, List<Object> tableConfigDetails, List<Object> itemCategorys, Object hiPosServiceCharge, Object companyLogoPath, String companyName, String fullUserName, List<Object> cmpylocationList, List<Object> supplocationList, List<TermsAndConditionList> termsAndConditionList, List<ExchangeRateList> exchangeRateList, List<Object> agentList, List<CurrencyList> currencyList, List<Object> projectList) {
        super();
        this.message = message;
        this.status = status;
        this.supplierAccountList = supplierAccountList;
        this.taxList = taxList;
        this.userRights = userRights;
        this.suppliers = suppliers;
        this.tableConfigDetails = tableConfigDetails;
        this.itemCategorys = itemCategorys;
        this.hiPosServiceCharge = hiPosServiceCharge;
        this.companyLogoPath = companyLogoPath;
        this.companyName = companyName;
        this.fullUserName = fullUserName;
        this.cmpylocationList = cmpylocationList;
        this.supplocationList = supplocationList;
        this.termsAndConditionList = termsAndConditionList;
        this.exchangeRateList = exchangeRateList;
        this.agentList = agentList;
        this.currencyList = currencyList;
        this.projectList = projectList;
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

    public List<Object> getCmpylocationList() {
        return cmpylocationList;
    }

    public void setCmpylocationList(List<Object> cmpylocationList) {
        this.cmpylocationList = cmpylocationList;
    }

    public List<Object> getSupplocationList() {
        return supplocationList;
    }

    public void setSupplocationList(List<Object> supplocationList) {
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
