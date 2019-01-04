
package in.hiaccounts.hinext.purchase.model.purchase_saveresponses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CompanyData implements Serializable {

    @SerializedName("companyId")
    @Expose
    public Long companyId;
    @SerializedName("lang")
    @Expose
    public String lang;
    @SerializedName("countryId")
    @Expose
    public Long countryId;
    @SerializedName("registeredCompany")
    @Expose
    public boolean registeredCompany;
    @SerializedName("registerNo")
    @Expose
    public String registerNo;
    @SerializedName("company_name")
    @Expose
    public String companyName;
    @SerializedName("company_no")
    @Expose
    public String companyNo;
    @SerializedName("address")
    @Expose
    public String address;
    @SerializedName("phone")
    @Expose
    public String phone;
    @SerializedName("fax")
    @Expose
    public String fax;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("hiConnectStatus")
    @Expose
    public Object hiConnectStatus;
    @SerializedName("hiConnectCompnyRegNo")
    @Expose
    public Object hiConnectCompnyRegNo;
    @SerializedName("web")
    @Expose
    public String web;
    @SerializedName("yearclosing")
    @Expose
    public String yearclosing;
    @SerializedName("startperiod")
    @Expose
    public String startperiod;
    @SerializedName("closingperiod")
    @Expose
    public String closingperiod;
    @SerializedName("startyear")
    @Expose
    public String startyear;
    @SerializedName("endyear")
    @Expose
    public String endyear;
    @SerializedName("closingMethod")
    @Expose
    public String closingMethod;
    @SerializedName("incdate")
    @Expose
    public Object incdate;
    @SerializedName("currencyId")
    @Expose
    public CurrencyId currencyId;
    @SerializedName("accCodeSep")
    @Expose
    public Object accCodeSep;
    @SerializedName("gstId")
    @Expose
    public long gstId;
    @SerializedName("industryclassificationId")
    @Expose
    public IndustryclassificationId industryclassificationId;
    @SerializedName("businesstypeId")
    @Expose
    public BusinesstypeId businesstypeId;
    @SerializedName("logo")
    @Expose
    public Object logo;
    @SerializedName("bufferDays")
    @Expose
    public long bufferDays;
    @SerializedName("createOrSelectAM")
    @Expose
    public String createOrSelectAM;
    @SerializedName("companyInfoId")
    @Expose
    public CompanyInfoId companyInfoId;
    @SerializedName("lan")
    @Expose
    public String lan;
    @SerializedName("complitionstatus")
    @Expose
    public String complitionstatus;
    @SerializedName("imageFile")
    @Expose
    public String imageFile;
    @SerializedName("locationId")
    @Expose
    public Long locationId;
    @SerializedName("useraccount_id")
    @Expose
    public Long useraccountId;
    @SerializedName("fileName")
    @Expose
    public String fileName;
    @SerializedName("imageBlob")
    @Expose
    public String imageBlob;
    @SerializedName("state")
    @Expose
    public State state;
    @SerializedName("stateName")
    @Expose
    private String stateName;
    @SerializedName("panNumber")
    @Expose
    public String panNumber;
    @SerializedName("gstregisteredDate")
    @Expose
    public String gstregisteredDate;
    @SerializedName("stateId")
    @Expose
    public Long stateId;

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public boolean isRegisteredCompany() {
        return registeredCompany;
    }

    public void setRegisteredCompany(boolean registeredCompany) {
        this.registeredCompany = registeredCompany;
    }

    public String getRegisterNo() {
        return registerNo;
    }

    public void setRegisterNo(String registerNo) {
        this.registerNo = registerNo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getYearclosing() {
        return yearclosing;
    }

    public void setYearclosing(String yearclosing) {
        this.yearclosing = yearclosing;
    }

    public String getStartperiod() {
        return startperiod;
    }

    public void setStartperiod(String startperiod) {
        this.startperiod = startperiod;
    }

    public String getClosingperiod() {
        return closingperiod;
    }

    public void setClosingperiod(String closingperiod) {
        this.closingperiod = closingperiod;
    }

    public String getStartyear() {
        return startyear;
    }

    public void setStartyear(String startyear) {
        this.startyear = startyear;
    }

    public String getEndyear() {
        return endyear;
    }

    public void setEndyear(String endyear) {
        this.endyear = endyear;
    }

    public String getClosingMethod() {
        return closingMethod;
    }

    public void setClosingMethod(String closingMethod) {
        this.closingMethod = closingMethod;
    }

    public Object getIncdate() {
        return incdate;
    }

    public void setIncdate(Object incdate) {
        this.incdate = incdate;
    }

    public CurrencyId getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(CurrencyId currencyId) {
        this.currencyId = currencyId;
    }

    public Object getAccCodeSep() {
        return accCodeSep;
    }

    public void setAccCodeSep(Object accCodeSep) {
        this.accCodeSep = accCodeSep;
    }

    public long getGstId() {
        return gstId;
    }

    public void setGstId(long gstId) {
        this.gstId = gstId;
    }

    public IndustryclassificationId getIndustryclassificationId() {
        return industryclassificationId;
    }

    public void setIndustryclassificationId(IndustryclassificationId industryclassificationId) {
        this.industryclassificationId = industryclassificationId;
    }

    public BusinesstypeId getBusinesstypeId() {
        return businesstypeId;
    }

    public void setBusinesstypeId(BusinesstypeId businesstypeId) {
        this.businesstypeId = businesstypeId;
    }

    public Object getLogo() {
        return logo;
    }

    public void setLogo(Object logo) {
        this.logo = logo;
    }

    public long getBufferDays() {
        return bufferDays;
    }

    public void setBufferDays(long bufferDays) {
        this.bufferDays = bufferDays;
    }

    public String getCreateOrSelectAM() {
        return createOrSelectAM;
    }

    public void setCreateOrSelectAM(String createOrSelectAM) {
        this.createOrSelectAM = createOrSelectAM;
    }

    public CompanyInfoId getCompanyInfoId() {
        return companyInfoId;
    }

    public void setCompanyInfoId(CompanyInfoId companyInfoId) {
        this.companyInfoId = companyInfoId;
    }



    public String getComplitionstatus() {
        return complitionstatus;
    }

    public void setComplitionstatus(String complitionstatus) {
        this.complitionstatus = complitionstatus;
    }

    public Object getImageFile() {
        return imageFile;
    }



    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public Object getPanNumber() {
        return panNumber;
    }



    public String getGstregisteredDate() {
        return gstregisteredDate;
    }

    public void setGstregisteredDate(String gstregisteredDate) {
        this.gstregisteredDate = gstregisteredDate;
    }

    public String getLan() {
        return lan;
    }

    public void setLan(String lan) {
        this.lan = lan;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Long getUseraccountId() {
        return useraccountId;
    }

    public void setUseraccountId(Long useraccountId) {
        this.useraccountId = useraccountId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getImageBlob() {
        return imageBlob;
    }

    public void setImageBlob(String imageBlob) {
        this.imageBlob = imageBlob;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }
}
