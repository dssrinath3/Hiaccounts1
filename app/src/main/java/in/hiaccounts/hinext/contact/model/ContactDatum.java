package in.hiaccounts.hinext.contact.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Admin on 8/3/2017.
 */

public class ContactDatum implements Serializable{

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("otherContactId")
    @Expose
    private long otherContactId;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("prefixName")
    @Expose
    private String prefixName;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("middleName")
    @Expose
    private String middleName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("contactNumber")
    @Expose
    private String contactNumber;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("companyName")
    @Expose
    private String companyName;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phoneNumber1")
    @Expose
    private String phoneNumber1;
    @SerializedName("phoneNumber2")
    @Expose
    private String phoneNumber2;
    @SerializedName("faxTelex")
    @Expose
    private String faxTelex;
    @SerializedName("contactPerson")
    @Expose
    private String contactPerson;
    @SerializedName("generalNote")
    @Expose
    private String generalNote;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("gstCode")
    @Expose
    private String gstCode;
    @SerializedName("panNO")
    @Expose
    private String panNO;
    @SerializedName("bankName")
    @Expose
    private String bankName;
    @SerializedName("accountNo")
    @Expose
    private String accountNo;
    @SerializedName("iFSCCode")
    @Expose
    private String iFSCCode;
    @SerializedName("branchName")
    @Expose
    private String branchName;
    @SerializedName("personIncharge")
    @Expose
    private String personIncharge;
    @SerializedName("imageFile")
    @Expose
    private String imageFile;
    @SerializedName("locationId")
    @Expose
    private long locationId;
    @SerializedName("useraccount_id")
    @Expose
    private long useraccountId;
    @SerializedName("hiConnectStatus")
    @Expose
    private Object hiConnectStatus;
    @SerializedName("hiConnectCompnyRegNo")
    @Expose
    private Object hiConnectCompnyRegNo;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("stateDTOList")
    @Expose
    private List<StateDTOList> stateDTOList = null;
    @SerializedName("countryDTOList")
    @Expose
    private List<CountryDTOList> countryDTOList = null;
    @SerializedName("currencyDTOList")
    @Expose
    private List<CurrencyDTOList> currencyDTOList = null;

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

    public long getOtherContactId() {
        return otherContactId;
    }

    public void setOtherContactId(long otherContactId) {
        this.otherContactId = otherContactId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPrefixName() {
        return prefixName;
    }

    public void setPrefixName(String prefixName) {
        this.prefixName = prefixName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getFaxTelex() {
        return faxTelex;
    }

    public void setFaxTelex(String faxTelex) {
        this.faxTelex = faxTelex;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getGeneralNote() {
        return generalNote;
    }

    public void setGeneralNote(String generalNote) {
        this.generalNote = generalNote;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getGstCode() {
        return gstCode;
    }

    public void setGstCode(String gstCode) {
        this.gstCode = gstCode;
    }

    public String getPanNO() {
        return panNO;
    }

    public void setPanNO(String panNO) {
        this.panNO = panNO;
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

    public String getiFSCCode() {
        return iFSCCode;
    }

    public void setiFSCCode(String iFSCCode) {
        this.iFSCCode = iFSCCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getPersonIncharge() {
        return personIncharge;
    }

    public void setPersonIncharge(String personIncharge) {
        this.personIncharge = personIncharge;
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public long getUseraccountId() {
        return useraccountId;
    }

    public void setUseraccountId(long useraccountId) {
        this.useraccountId = useraccountId;
    }

    public Object getHiConnectStatus() {
        return hiConnectStatus;
    }

    public void setHiConnectStatus(Object hiConnectStatus) {
        this.hiConnectStatus = hiConnectStatus;
    }

    public Object getHiConnectCompnyRegNo() {
        return hiConnectCompnyRegNo;
    }

    public void setHiConnectCompnyRegNo(Object hiConnectCompnyRegNo) {
        this.hiConnectCompnyRegNo = hiConnectCompnyRegNo;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
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
}
