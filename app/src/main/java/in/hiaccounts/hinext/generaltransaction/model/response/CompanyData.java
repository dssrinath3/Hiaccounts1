
package in.hiaccounts.hinext.generaltransaction.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CompanyData implements Serializable {

    @SerializedName("companyId")
    @Expose
    public Integer companyId;
    @SerializedName("lang")
    @Expose
    public String lang;
    @SerializedName("countryId")
    @Expose
    public CountryId countryId;
    @SerializedName("registeredCompany")
    @Expose
    public Boolean registeredCompany;
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
    public String incdate;
    @SerializedName("currencyId")
    @Expose
    public CurrencyId currencyId;
    @SerializedName("accCodeSep")
    @Expose
    public Object accCodeSep;
    @SerializedName("gstId")
    @Expose
    public Integer gstId;
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
    public Integer bufferDays;
    @SerializedName("createOrSelectAM")
    @Expose
    public String createOrSelectAM;
    @SerializedName("companyInfoId")
    @Expose
    public CompanyInfoId companyInfoId;
    @SerializedName("lan")
    @Expose
    public Object lan;
    @SerializedName("complitionstatus")
    @Expose
    public String complitionstatus;
    @SerializedName("imageFile")
    @Expose
    public Object imageFile;
    @SerializedName("locationId")
    @Expose
    public Object locationId;
    @SerializedName("useraccount_id")
    @Expose
    public Object useraccountId;
    @SerializedName("fileName")
    @Expose
    public Object fileName;
    @SerializedName("imageBlob")
    @Expose
    public Object imageBlob;
    @SerializedName("state")
    @Expose
    public State state;
    @SerializedName("panNumber")
    @Expose
    public String panNumber;
    @SerializedName("gstregisteredDate")
    @Expose
    public String gstregisteredDate;

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public CountryId getCountryId() {
        return countryId;
    }

    public void setCountryId(CountryId countryId) {
        this.countryId = countryId;
    }

    public Boolean getRegisteredCompany() {
        return registeredCompany;
    }

    public void setRegisteredCompany(Boolean registeredCompany) {
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

    public String getIncdate() {
        return incdate;
    }

    public void setIncdate(String incdate) {
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

    public Integer getGstId() {
        return gstId;
    }

    public void setGstId(Integer gstId) {
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

    public Integer getBufferDays() {
        return bufferDays;
    }

    public void setBufferDays(Integer bufferDays) {
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

    public Object getLan() {
        return lan;
    }

    public void setLan(Object lan) {
        this.lan = lan;
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

    public void setImageFile(Object imageFile) {
        this.imageFile = imageFile;
    }

    public Object getLocationId() {
        return locationId;
    }

    public void setLocationId(Object locationId) {
        this.locationId = locationId;
    }

    public Object getUseraccountId() {
        return useraccountId;
    }

    public void setUseraccountId(Object useraccountId) {
        this.useraccountId = useraccountId;
    }

    public Object getFileName() {
        return fileName;
    }

    public void setFileName(Object fileName) {
        this.fileName = fileName;
    }

    public Object getImageBlob() {
        return imageBlob;
    }

    public void setImageBlob(Object imageBlob) {
        this.imageBlob = imageBlob;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getGstregisteredDate() {
        return gstregisteredDate;
    }

    public void setGstregisteredDate(String gstregisteredDate) {
        this.gstregisteredDate = gstregisteredDate;
    }
}
