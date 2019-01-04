
package in.hiaccounts.hinext.customer.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerPageData {

    @SerializedName("message")
    @Expose
    private Object message;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("accountmasterid")
    @Expose
    private Object accountmasterid;
    @SerializedName("linkedaccount")
    @Expose
    private Object linkedaccount;
    @SerializedName("accountid")
    @Expose
    private Object accountid;
    @SerializedName("account_name")
    @Expose
    private Object accountName;
    @SerializedName("stringAccountCode")
    @Expose
    private Object stringAccountCode;
    @SerializedName("customerName")
    @Expose
    private Object customerName;
    @SerializedName("customerEmail")
    @Expose
    private Object customerEmail;
    @SerializedName("customerContact")
    @Expose
    private Object customerContact;
    @SerializedName("customerNumber")
    @Expose
    private Object customerNumber;
    @SerializedName("customerAddress")
    @Expose
    private Object customerAddress;
    @SerializedName("companyRegNo")
    @Expose
    private Object companyRegNo;
    @SerializedName("notificationFlag")
    @Expose
    private Object notificationFlag;
    @SerializedName("from_Reg_Comp")
    @Expose
    private Object fromRegComp;
    @SerializedName("to_Reg_Comp")
    @Expose
    private Object toRegComp;
    @SerializedName("notificationId")
    @Expose
    private Object notificationId;
    @SerializedName("shipToAddress")
    @Expose
    private Object shipToAddress;
    @SerializedName("gstIn")
    @Expose
    private Object gstIn;
    @SerializedName("state")
    @Expose
    private Object state;
    @SerializedName("stateDTOList")
    @Expose
    private List<StateDTOList> stateDTOList = null;
    @SerializedName("countryDTOList")
    @Expose
    private List<CountryDTOList> countryDTOList = null;
    @SerializedName("currencyDTOList")
    @Expose
    private List<CurrencyDTOList> currencyDTOList = null;
    @SerializedName("bankName")
    @Expose
    private Object bankName;
    @SerializedName("accountNo")
    @Expose
    private Object accountNo;
    @SerializedName("iFSCCode")
    @Expose
    private Object iFSCCode;
    @SerializedName("branchName")
    @Expose
    private Object branchName;
    @SerializedName("personIncharge")
    @Expose
    private Object personIncharge;
    @SerializedName("website")
    @Expose
    private Object website;
    @SerializedName("country")
    @Expose
    private Object country;
    @SerializedName("currency")
    @Expose
    private Object currency;
    @SerializedName("cmpyCountry")
    @Expose
    private String cmpyCountry;
    @SerializedName("cmpyCurrency")
    @Expose
    private String cmpyCurrency;
    @SerializedName("cmpyState")
    @Expose
    private String cmpyState;
    @SerializedName("panNumber")
    @Expose
    private Object panNumber;

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getAccountmasterid() {
        return accountmasterid;
    }

    public void setAccountmasterid(Object accountmasterid) {
        this.accountmasterid = accountmasterid;
    }

    public Object getLinkedaccount() {
        return linkedaccount;
    }

    public void setLinkedaccount(Object linkedaccount) {
        this.linkedaccount = linkedaccount;
    }

    public Object getAccountid() {
        return accountid;
    }

    public void setAccountid(Object accountid) {
        this.accountid = accountid;
    }

    public Object getAccountName() {
        return accountName;
    }

    public void setAccountName(Object accountName) {
        this.accountName = accountName;
    }

    public Object getStringAccountCode() {
        return stringAccountCode;
    }

    public void setStringAccountCode(Object stringAccountCode) {
        this.stringAccountCode = stringAccountCode;
    }

    public Object getCustomerName() {
        return customerName;
    }

    public void setCustomerName(Object customerName) {
        this.customerName = customerName;
    }

    public Object getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(Object customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Object getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(Object customerContact) {
        this.customerContact = customerContact;
    }

    public Object getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Object customerNumber) {
        this.customerNumber = customerNumber;
    }

    public Object getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(Object customerAddress) {
        this.customerAddress = customerAddress;
    }

    public Object getCompanyRegNo() {
        return companyRegNo;
    }

    public void setCompanyRegNo(Object companyRegNo) {
        this.companyRegNo = companyRegNo;
    }

    public Object getNotificationFlag() {
        return notificationFlag;
    }

    public void setNotificationFlag(Object notificationFlag) {
        this.notificationFlag = notificationFlag;
    }

    public Object getFromRegComp() {
        return fromRegComp;
    }

    public void setFromRegComp(Object fromRegComp) {
        this.fromRegComp = fromRegComp;
    }

    public Object getToRegComp() {
        return toRegComp;
    }

    public void setToRegComp(Object toRegComp) {
        this.toRegComp = toRegComp;
    }

    public Object getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Object notificationId) {
        this.notificationId = notificationId;
    }

    public Object getShipToAddress() {
        return shipToAddress;
    }

    public void setShipToAddress(Object shipToAddress) {
        this.shipToAddress = shipToAddress;
    }

    public Object getGstIn() {
        return gstIn;
    }

    public void setGstIn(Object gstIn) {
        this.gstIn = gstIn;
    }

    public Object getState() {
        return state;
    }

    public void setState(Object state) {
        this.state = state;
    }

    public List<StateDTOList> getStateDTOList() {
        return stateDTOList;
    }

    public void setStateDTOList(List<StateDTOList> stateDTOList) {
        this.stateDTOList = stateDTOList;
    }

    public List<CountryDTOList> getCountryDTOList() {
        return countryDTOList;
    }

    public void setCountryDTOList(List<CountryDTOList> countryDTOList) {
        this.countryDTOList = countryDTOList;
    }

    public List<CurrencyDTOList> getCurrencyDTOList() {
        return currencyDTOList;
    }

    public void setCurrencyDTOList(List<CurrencyDTOList> currencyDTOList) {
        this.currencyDTOList = currencyDTOList;
    }

    public Object getBankName() {
        return bankName;
    }

    public void setBankName(Object bankName) {
        this.bankName = bankName;
    }

    public Object getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(Object accountNo) {
        this.accountNo = accountNo;
    }

    public Object getIFSCCode() {
        return iFSCCode;
    }

    public void setIFSCCode(Object iFSCCode) {
        this.iFSCCode = iFSCCode;
    }

    public Object getBranchName() {
        return branchName;
    }

    public void setBranchName(Object branchName) {
        this.branchName = branchName;
    }

    public Object getPersonIncharge() {
        return personIncharge;
    }

    public void setPersonIncharge(Object personIncharge) {
        this.personIncharge = personIncharge;
    }

    public Object getWebsite() {
        return website;
    }

    public void setWebsite(Object website) {
        this.website = website;
    }

    public Object getCountry() {
        return country;
    }

    public void setCountry(Object country) {
        this.country = country;
    }

    public Object getCurrency() {
        return currency;
    }

    public void setCurrency(Object currency) {
        this.currency = currency;
    }

    public String getCmpyCountry() {
        return cmpyCountry;
    }

    public void setCmpyCountry(String cmpyCountry) {
        this.cmpyCountry = cmpyCountry;
    }

    public String getCmpyCurrency() {
        return cmpyCurrency;
    }

    public void setCmpyCurrency(String cmpyCurrency) {
        this.cmpyCurrency = cmpyCurrency;
    }

    public String getCmpyState() {
        return cmpyState;
    }

    public void setCmpyState(String cmpyState) {
        this.cmpyState = cmpyState;
    }

    public Object getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(Object panNumber) {
        this.panNumber = panNumber;
    }

}
