package in.hiaccounts.hinext.service.model.service_invoice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import in.hiaccounts.hinext.sales.model.printlist.payement_receipt.CompanyId;
import in.hiaccounts.hinext.sales.model.printlist.payement_receipt.Country;
import in.hiaccounts.hinext.sales.model.printlist.payement_receipt.CurrencyCode;
import in.hiaccounts.hinext.sales.model.printlist.payement_receipt.DiscountGiven;
import in.hiaccounts.hinext.sales.model.printlist.payement_receipt.LinkedAccount;
import in.hiaccounts.hinext.sales.model.printlist.payement_receipt.StateId;

/**
 * Created by administrator on 24/2/18.
 */

public class CustomerObj implements Serializable {
    @SerializedName("customerId")
    @Expose
    public Long customerId;
    @SerializedName("customerName")
    @Expose
    public String customerName;
    @SerializedName("customerNumber")
    @Expose
    public String customerNumber;
    @SerializedName("customerCode")
    @Expose
    public Object customerCode;
    @SerializedName("companyNumber")
    @Expose
    public Object companyNumber;
    @SerializedName("companyCode")
    @Expose
    public Object companyCode;
    @SerializedName("address")
    @Expose
    public Object address;
    @SerializedName("attention")
    @Expose
    public Object attention;
    @SerializedName("email")
    @Expose
    public Object email;
    @SerializedName("phoneNumber1")
    @Expose
    public Object phoneNumber1;
    @SerializedName("phoneNumber2")
    @Expose
    public Object phoneNumber2;
    @SerializedName("faxTelex")
    @Expose
    public Object faxTelex;
    @SerializedName("contactPerson")
    @Expose
    public Object contactPerson;
    @SerializedName("generalNote")
    @Expose
    public Object generalNote;
    @SerializedName("website")
    @Expose
    public Object website;
    @SerializedName("gstCode")
    @Expose
    public String gstCode;
    @SerializedName("terms")
    @Expose
    public Object terms;
    @SerializedName("linkedAccount")
    @Expose
    public LinkedAccount linkedAccount;
    @SerializedName("creditedLimitAlert")
    @Expose
    public Boolean creditedLimitAlert;
    @SerializedName("creditedLimit")
    @Expose
    public Object creditedLimit;
    @SerializedName("companyId")
    @Expose
    public CompanyId companyId;
    @SerializedName("country")
    @Expose
    public Country country;
    @SerializedName("periodOfVerification")
    @Expose
    public String periodOfVerification;
    @SerializedName("latestFeeColl")
    @Expose
    public Object latestFeeColl;
    @SerializedName("discountGiven")
    @Expose
    public DiscountGiven discountGiven;
    @SerializedName("clientDeposite")
    @Expose
    public Object clientDeposite;
    @SerializedName("createdDate")
    @Expose
    public String createdDate;
    @SerializedName("verificationDate")
    @Expose
    public String verificationDate;
    @SerializedName("currencyWord")
    @Expose
    public String currencyWord;
    @SerializedName("currencySymbol")
    @Expose
    public String currencySymbol;
    @SerializedName("shipToAddress")
    @Expose
    public Object shipToAddress;
    @SerializedName("currencyCode")
    @Expose
    public CurrencyCode currencyCode;
    @SerializedName("locationId")
    @Expose
    public String locationId;
    @SerializedName("useraccount_id")
    @Expose
    public String useraccountId;
    @SerializedName("hiConnectStatus")
    @Expose
    public String hiConnectStatus;
    @SerializedName("hiConnectCompnyRegNo")
    @Expose
    public Object hiConnectCompnyRegNo;
    @SerializedName("panNo")
    @Expose
    public Object panNo;
    @SerializedName("stateId")
    @Expose
    public StateId stateId;
    @SerializedName("bankName")
    @Expose
    public Object bankName;
    @SerializedName("accountNo")
    @Expose
    public Object accountNo;
    @SerializedName("iFSCCode")
    @Expose
    public Object iFSCCode;
    @SerializedName("branchName")
    @Expose
    public Object branchName;
    @SerializedName("personIncharge")
    @Expose
    public Object personIncharge;
    @SerializedName("loyaltyPoints")
    @Expose
    public Double loyaltyPoints;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("badDebts")
    @Expose
    public Object badDebts;
    @SerializedName("freightColld")
    @Expose
    public Object freightColld;
    @SerializedName("arAccount")
    @Expose
    public Object arAccount;

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

    public Object getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(Object customerCode) {
        this.customerCode = customerCode;
    }

    public Object getCompanyNumber() {
        return companyNumber;
    }

    public void setCompanyNumber(Object companyNumber) {
        this.companyNumber = companyNumber;
    }

    public Object getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(Object companyCode) {
        this.companyCode = companyCode;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public Object getAttention() {
        return attention;
    }

    public void setAttention(Object attention) {
        this.attention = attention;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public Object getPhoneNumber1() {
        return phoneNumber1;
    }

    public void setPhoneNumber1(Object phoneNumber1) {
        this.phoneNumber1 = phoneNumber1;
    }

    public Object getPhoneNumber2() {
        return phoneNumber2;
    }

    public void setPhoneNumber2(Object phoneNumber2) {
        this.phoneNumber2 = phoneNumber2;
    }

    public Object getFaxTelex() {
        return faxTelex;
    }

    public void setFaxTelex(Object faxTelex) {
        this.faxTelex = faxTelex;
    }

    public Object getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(Object contactPerson) {
        this.contactPerson = contactPerson;
    }

    public Object getGeneralNote() {
        return generalNote;
    }

    public void setGeneralNote(Object generalNote) {
        this.generalNote = generalNote;
    }

    public Object getWebsite() {
        return website;
    }

    public void setWebsite(Object website) {
        this.website = website;
    }

    public String getGstCode() {
        return gstCode;
    }

    public void setGstCode(String gstCode) {
        this.gstCode = gstCode;
    }

    public Object getTerms() {
        return terms;
    }

    public void setTerms(Object terms) {
        this.terms = terms;
    }

    public LinkedAccount getLinkedAccount() {
        return linkedAccount;
    }

    public void setLinkedAccount(LinkedAccount linkedAccount) {
        this.linkedAccount = linkedAccount;
    }

    public Boolean getCreditedLimitAlert() {
        return creditedLimitAlert;
    }

    public void setCreditedLimitAlert(Boolean creditedLimitAlert) {
        this.creditedLimitAlert = creditedLimitAlert;
    }

    public Object getCreditedLimit() {
        return creditedLimit;
    }

    public void setCreditedLimit(Object creditedLimit) {
        this.creditedLimit = creditedLimit;
    }

    public CompanyId getCompanyId() {
        return companyId;
    }

    public void setCompanyId(CompanyId companyId) {
        this.companyId = companyId;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getPeriodOfVerification() {
        return periodOfVerification;
    }

    public void setPeriodOfVerification(String periodOfVerification) {
        this.periodOfVerification = periodOfVerification;
    }

    public Object getLatestFeeColl() {
        return latestFeeColl;
    }

    public void setLatestFeeColl(Object latestFeeColl) {
        this.latestFeeColl = latestFeeColl;
    }

    public DiscountGiven getDiscountGiven() {
        return discountGiven;
    }

    public void setDiscountGiven(DiscountGiven discountGiven) {
        this.discountGiven = discountGiven;
    }

    public Object getClientDeposite() {
        return clientDeposite;
    }

    public void setClientDeposite(Object clientDeposite) {
        this.clientDeposite = clientDeposite;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getVerificationDate() {
        return verificationDate;
    }

    public void setVerificationDate(String verificationDate) {
        this.verificationDate = verificationDate;
    }

    public String getCurrencyWord() {
        return currencyWord;
    }

    public void setCurrencyWord(String currencyWord) {
        this.currencyWord = currencyWord;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public Object getShipToAddress() {
        return shipToAddress;
    }

    public void setShipToAddress(Object shipToAddress) {
        this.shipToAddress = shipToAddress;
    }

    public CurrencyCode getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(CurrencyCode currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getUseraccountId() {
        return useraccountId;
    }

    public void setUseraccountId(String useraccountId) {
        this.useraccountId = useraccountId;
    }

    public String getHiConnectStatus() {
        return hiConnectStatus;
    }

    public void setHiConnectStatus(String hiConnectStatus) {
        this.hiConnectStatus = hiConnectStatus;
    }

    public Object getHiConnectCompnyRegNo() {
        return hiConnectCompnyRegNo;
    }

    public void setHiConnectCompnyRegNo(Object hiConnectCompnyRegNo) {
        this.hiConnectCompnyRegNo = hiConnectCompnyRegNo;
    }

    public Object getPanNo() {
        return panNo;
    }

    public void setPanNo(Object panNo) {
        this.panNo = panNo;
    }

    public StateId getStateId() {
        return stateId;
    }

    public void setStateId(StateId stateId) {
        this.stateId = stateId;
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

    public Double getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(Double loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getBadDebts() {
        return badDebts;
    }

    public void setBadDebts(Object badDebts) {
        this.badDebts = badDebts;
    }

    public Object getFreightColld() {
        return freightColld;
    }

    public void setFreightColld(Object freightColld) {
        this.freightColld = freightColld;
    }

    public Object getArAccount() {
        return arAccount;
    }

    public void setArAccount(Object arAccount) {
        this.arAccount = arAccount;
    }
}
