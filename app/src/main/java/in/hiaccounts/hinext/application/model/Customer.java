package in.hiaccounts.hinext.application.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by administrator on 23/2/18.
 */

public class Customer implements Serializable {
    @SerializedName("customerId")
    @Expose
    private Long customerId;

    @SerializedName("billingAddress")
    @Expose
    private String billingAddress;

    @SerializedName("panNO")
    @Expose
    private String panNO;

    @SerializedName("customerName")
    @Expose
    private String customerName;
    @SerializedName("customerEmail")
    @Expose
    private String customerEmail;
    @SerializedName("customerContact")
    @Expose
    private String customerContact;
    @SerializedName("customerAddress")
    @Expose
    private String customerAddress;
    @SerializedName("companyRegNo")
    @Expose
    private String companyRegNo;
    @SerializedName("notificationFlag")
    @Expose
    private String notificationFlag;
    @SerializedName("from_Reg_Comp")
    @Expose
    private String fromRegComp;
    @SerializedName("to_Reg_Comp")
    @Expose
    private String toRegComp;
    @SerializedName("gstIn")
    @Expose
    private String gstIn;

    @SerializedName("personIncharge")
    @Expose
    private String personIncharge;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("country")
    @Expose
    private Long countryID;
    @SerializedName("currency")
    @Expose
    private Long currencyId;
    @SerializedName("bankName")
    @Expose
    private String bankName;
    @SerializedName("accountNo")
    @Expose
    private String accountNo;
    @SerializedName("branchName")
    @Expose
    private String branchName;
    @SerializedName("iFSCCode")
    @Expose
    private String iFSCCode;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("panNumber")
    @Expose
    private String panNumber;
    @SerializedName("customerNumber")
    @Expose
    private String customerNumber;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getPanNO() {
        return panNO;
    }

    public void setPanNO(String panNO) {
        this.panNO = panNO;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(String customerContact) {
        this.customerContact = customerContact;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCompanyRegNo() {
        return companyRegNo;
    }

    public void setCompanyRegNo(String companyRegNo) {
        this.companyRegNo = companyRegNo;
    }

    public String getNotificationFlag() {
        return notificationFlag;
    }

    public void setNotificationFlag(String notificationFlag) {
        this.notificationFlag = notificationFlag;
    }

    public String getFromRegComp() {
        return fromRegComp;
    }

    public void setFromRegComp(String fromRegComp) {
        this.fromRegComp = fromRegComp;
    }

    public String getToRegComp() {
        return toRegComp;
    }

    public void setToRegComp(String toRegComp) {
        this.toRegComp = toRegComp;
    }

    public String getGstIn() {
        return gstIn;
    }

    public void setGstIn(String gstIn) {
        this.gstIn = gstIn;
    }

    public String getPersonIncharge() {
        return personIncharge;
    }

    public void setPersonIncharge(String personIncharge) {
        this.personIncharge = personIncharge;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getCountryID() {
        return countryID;
    }

    public void setCountryID(Long countryID) {
        this.countryID = countryID;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getiFSCCode() {
        return iFSCCode;
    }

    public void setiFSCCode(String iFSCCode) {
        this.iFSCCode = iFSCCode;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }
}
