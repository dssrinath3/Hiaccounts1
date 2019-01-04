
package in.hiaccounts.hinext.restaurant.model.restaurant_pagedata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Customer implements Serializable {

    @SerializedName("customerId")
    @Expose
    private Long customerId;
    @SerializedName("customerName")
    @Expose
    private String customerName;
    @SerializedName("customerNumber")
    @Expose
    private String customerNumber;
    @SerializedName("customerEmail")
    @Expose
    private Object customerEmail;
    @SerializedName("customerContact")
    @Expose
    private Object customerContact;
    @SerializedName("gstIn")
    @Expose
    private Object gstIn;
    @SerializedName("state")
    @Expose
    private Object state;
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
    @SerializedName("countryId")
    @Expose
    private Object countryId;
    @SerializedName("currencyId")
    @Expose
    private Object currencyId;
    @SerializedName("panNO")
    @Expose
    private Object panNO;
    @SerializedName("website")
    @Expose
    private Object website;
    @SerializedName("billingAddress")
    @Expose
    private Object billingAddress;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
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

    public Object getiFSCCode() {
        return iFSCCode;
    }

    public void setiFSCCode(Object iFSCCode) {
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

    public Object getCountryId() {
        return countryId;
    }

    public void setCountryId(Object countryId) {
        this.countryId = countryId;
    }

    public Object getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Object currencyId) {
        this.currencyId = currencyId;
    }

    public Object getPanNO() {
        return panNO;
    }

    public void setPanNO(Object panNO) {
        this.panNO = panNO;
    }

    public Object getWebsite() {
        return website;
    }

    public void setWebsite(Object website) {
        this.website = website;
    }

    public Object getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Object billingAddress) {
        this.billingAddress = billingAddress;
    }
}
