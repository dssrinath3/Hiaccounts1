package in.hiaccounts.hinext.supplier.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Prateek on 8/18/2017.
 */

public class EditSupplier {

    @SerializedName("accountNo")
    @Expose
    public String accountNo;
    @SerializedName("bankName")
    @Expose
    public String bankName;
    @SerializedName("branchName")
    @Expose
    public String branchName;
    @SerializedName("countryId")
    @Expose
    public long countryId;
    @SerializedName("currencyId")
    @Expose
    public long currencyId;
    @SerializedName("from_Reg_Comp")
    @Expose
    public String fromRegComp;
    @SerializedName("gstIn")
    @Expose
    public String gstIn;
    @SerializedName("iFSCCode")
    @Expose
    public String iFSCCode;
    @SerializedName("notificationFlag")
    @Expose
    public String notificationFlag;
    @SerializedName("panNumber")
    @Expose
    public String panNumber;
    @SerializedName("panNO")
    @Expose
    public String panNO;

    @SerializedName("personIncharge")
    @Expose
    public String personIncharge;
    @SerializedName("state")
    @Expose
    public long state;


    @SerializedName("billingAddress")
    @Expose
    public String billingAddress;
    @SerializedName("supplierBillingAddress")
    @Expose
    public String supplierBillingAddress;
    @SerializedName("supplierEmail")
    @Expose
    public String supplierEmail;
    @SerializedName("supplierNumber")
    @Expose
    public String supplierNumber;
    @SerializedName("supplierId")
    @Expose
    public long supplierId;
    @SerializedName("supplierName")
    @Expose
    public String supplierName;
    @SerializedName("supplierPhoneNumber1")
    @Expose
    public String supplierPhoneNumber1;
    @SerializedName("to_Reg_Comp")
    @Expose
    public String toRegComp;
    @SerializedName("website")
    @Expose
    public String website;

    public String getSupplierNumber() {
        return supplierNumber;
    }

    public void setSupplierNumber(String supplierNumber) {
        this.supplierNumber = supplierNumber;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public long getCountryId() {
        return countryId;
    }

    public void setCountryId(long countryId) {
        this.countryId = countryId;
    }

    public long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(long currencyId) {
        this.currencyId = currencyId;
    }

    public String getFromRegComp() {
        return fromRegComp;
    }

    public void setFromRegComp(String fromRegComp) {
        this.fromRegComp = fromRegComp;
    }

    public String getGstIn() {
        return gstIn;
    }

    public void setGstIn(String gstIn) {
        this.gstIn = gstIn;
    }

    public String getiFSCCode() {
        return iFSCCode;
    }

    public void setiFSCCode(String iFSCCode) {
        this.iFSCCode = iFSCCode;
    }

    public String getNotificationFlag() {
        return notificationFlag;
    }

    public void setNotificationFlag(String notificationFlag) {
        this.notificationFlag = notificationFlag;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getPersonIncharge() {
        return personIncharge;
    }

    public void setPersonIncharge(String personIncharge) {
        this.personIncharge = personIncharge;
    }

    public long getState() {
        return state;
    }

    public void setState(long state) {
        this.state = state;
    }

    public String getSupplierBillingAddress() {
        return supplierBillingAddress;
    }

    public void setSupplierBillingAddress(String supplierBillingAddress) {
        this.supplierBillingAddress = supplierBillingAddress;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    public long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierPhoneNumber1() {
        return supplierPhoneNumber1;
    }

    public void setSupplierPhoneNumber1(String supplierPhoneNumber1) {
        this.supplierPhoneNumber1 = supplierPhoneNumber1;
    }

    public String getToRegComp() {
        return toRegComp;
    }

    public void setToRegComp(String toRegComp) {
        this.toRegComp = toRegComp;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
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
}
