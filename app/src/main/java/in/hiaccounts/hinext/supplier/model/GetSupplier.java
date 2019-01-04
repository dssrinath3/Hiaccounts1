package in.hiaccounts.hinext.supplier.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Prateek on 7/26/2017.
 */

public class GetSupplier implements Serializable {

    @SerializedName("supplierId")
    @Expose
    private Long supplierId;
    @SerializedName("supplierName")
    @Expose
    private String supplierName;
    @SerializedName("panNO")
    @Expose
    private String panNO;
    @SerializedName("supplierNumber")
    @Expose
    private String supplierNumber;
    @SerializedName("supplierEmail")
    @Expose
    private String supplierEmail;
    @SerializedName("billingAddress")
    @Expose
    private String billingAddress;

    @SerializedName("phoneNumber1")
    @Expose
    private String phoneNumber1;
    @SerializedName("phoneNumber2")
    @Expose
    private String phoneNumber2;
    @SerializedName("fax")
    @Expose
    private String fax;
    @SerializedName("contactName")
    @Expose
    private String contactName;
    @SerializedName("generalNote")
    @Expose
    private String generalNote;

    @SerializedName("gstIn")
    @Expose
    private String gstIn;

    @SerializedName("personIncharge")
    @Expose
    private String personIncharge;

    @SerializedName("panNumber")
    @Expose
    private String panNumber;
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
    @SerializedName("state")
    @Expose
    private String stateID;
    @SerializedName("country")
    @Expose
    private long countryID;
    @SerializedName("currency")
    @Expose
    private long currencyId;

    @SerializedName("stateDTOList")
    @Expose
    private List<StateDTOList> stateDTOList = null;
    @SerializedName("countryDTOList")
    @Expose
    private List<CountryDTOList> countryDTOList = null;
    @SerializedName("currencyDTOList")
    @Expose
    private List<CurrencyDTOList> currencyDTOList = null;


    public String getStateID() {
        return stateID;
    }

    public void setStateID(String stateID) {
        this.stateID = stateID;
    }

    public long getCountryID() {
        return countryID;
    }

    public void setCountryID(long countryID) {
        this.countryID = countryID;
    }

    public long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(long currencyId) {
        this.currencyId = currencyId;
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

    @SerializedName("panNumber")



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

    public String getGstIn() {
        return gstIn;
    }

    public void setGstIn(String gstIn) {
        this.gstIn = gstIn;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierNumber() {
        return supplierNumber;
    }

    public void setSupplierNumber(String supplierNumber) {
        this.supplierNumber = supplierNumber;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhoneNumber1() {
        return phoneNumber1;
    }

    public void setPhoneNumber1(String phoneNumber1) {
        this.phoneNumber1 = phoneNumber1;
    }

    public String getPhoneNumber2() {
        return phoneNumber2;
    }

    public void setPhoneNumber2(String phoneNumber2) {
        this.phoneNumber2 = phoneNumber2;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getGeneralNote() {
        return generalNote;
    }

    public void setGeneralNote(String generalNote) {
        this.generalNote = generalNote;
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

    public String getPanNO() {
        return panNO;
    }

    public void setPanNO(String panNO) {
        this.panNO = panNO;
    }
}
