package in.hiaccounts.hinext.company_setup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Admin on 11/11/2017.
 */

public class CompanyPageInfo implements Serializable {

    @SerializedName("companyName")
    @Expose
    public String companyName;

    @SerializedName("countries")
    @Expose
    public List<CountryList> countriesList = null;

    @SerializedName("industryClassifications")
    @Expose
    public List<IndustryClassificationList> industryClassificationsList = null;

    @SerializedName("currencies")
    @Expose
    public List<CurrencyList> currenciesList = null;

    @SerializedName("states")
    @Expose
    public List<StateList> stateList = null;

    @SerializedName("tempId")
    @Expose
    public String tempId;

    @SerializedName("company_no")
    @Expose
    public String company_no;

    @SerializedName("incdate")
    @Expose
    public String incdate; // 31/12/2016

    @SerializedName("address")
    @Expose
    public String address;

    @SerializedName("phone")
    @Expose
    public String phone;

    @SerializedName("fax")
    @Expose
    public String fax;

    @SerializedName("panNumber")
    @Expose
    public String panNumber;

    @SerializedName("lang")
    @Expose
    public String lang;


    @SerializedName("email")
    @Expose
    public String email;


    @SerializedName("web")
    @Expose
    public String web;

    @SerializedName("countryId")
    @Expose
    public Long countryId;

    @SerializedName("stateId")
    @Expose
    public Long stateId;

    @SerializedName("currencyId")
    @Expose
    public Long currencyId;

    @SerializedName("registeredCompany")
    @Expose
    public boolean registeredCompany;

    @SerializedName("registerNo")
    @Expose
    public String registerNo;

    @SerializedName("gstRegisteredDate")
    @Expose
    public String gstRegisteredDate;

    @SerializedName("yearclosing")
    @Expose
    public String yearclosing;

    @SerializedName("closingMethod")
    @Expose
    public String closingMethod;

    @SerializedName("startperiod")
    @Expose
    public String startperiod;

    @SerializedName("closingperiod")
    @Expose
    public String closingperiod;

    @SerializedName("bufferDays")
    @Expose
    public String bufferDays;

    @SerializedName("startyear")
    @Expose
    public String startyear;

    @SerializedName("endyear")
    @Expose
    public String endyear;

    @SerializedName("bufferDate")
    @Expose
    public String bufferDate;

    @SerializedName("gstreturnalertdue")
    @Expose
    public Integer gstreturnalertdue;

    @SerializedName("createOrSelectAM")
    @Expose
    public String createOrSelectAM;


    @SerializedName("filename")
    @Expose
    public String filename;



    @SerializedName("industryClassificationId")
    @Expose
    public Long industryClassificationId;

    @SerializedName("businessTypeId")
    @Expose
    public Long businessTypeId;


    public Calendar companyRegisterTime;
    public Calendar gstRegisterTime;

    @SerializedName("file")
    @Expose
    private File file;

    public Calendar getCompanyRegisterTime() {
        return companyRegisterTime;
    }

    public void setCompanyRegisterTime(Calendar companyRegisterTime) {
        this.companyRegisterTime = companyRegisterTime;
    }

    public Calendar getGstRegisterTime() {
        return gstRegisterTime;
    }

    public void setGstRegisterTime(Calendar gstRegisterTime) {
        this.gstRegisterTime = gstRegisterTime;
    }

    public String getTempId() {
        return tempId;
    }

    public void setTempId(String tempId) {
        this.tempId = tempId;
    }

    public String getCompany_no() {
        return company_no;
    }

    public void setCompany_no(String company_no) {
        this.company_no = company_no;
    }

    public String getIncdate() {
        return incdate;
    }

    public void setIncdate(String incdate) {
        this.incdate = incdate;
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

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
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

    public String getGstRegisteredDate() {
        return gstRegisteredDate;
    }

    public void setGstRegisteredDate(String gstRegisteredDate) {
        this.gstRegisteredDate = gstRegisteredDate;
    }

    public String getYearclosing() {
        return yearclosing;
    }

    public void setYearclosing(String yearclosing) {
        this.yearclosing = yearclosing;
    }

    public String getClosingMethod() {
        return closingMethod;
    }

    public void setClosingMethod(String closingMethod) {
        this.closingMethod = closingMethod;
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

    public String getBufferDays() {
        return bufferDays;
    }

    public void setBufferDays(String bufferDays) {
        this.bufferDays = bufferDays;
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

    public String getBufferDate() {
        return bufferDate;
    }

    public void setBufferDate(String bufferDate) {
        this.bufferDate = bufferDate;
    }

    public Integer getGstreturnalertdue() {
        return gstreturnalertdue;
    }

    public void setGstreturnalertdue(Integer gstreturnalertdue) {
        this.gstreturnalertdue = gstreturnalertdue;
    }

    public String getCreateOrSelectAM() {
        return createOrSelectAM;
    }

    public void setCreateOrSelectAM(String createOrSelectAM) {
        this.createOrSelectAM = createOrSelectAM;
    }

    public Long getIndustryClassificationId() {
        return industryClassificationId;
    }

    public void setIndustryClassificationId(Long industryClassificationId) {
        this.industryClassificationId = industryClassificationId;
    }

    public Long getBusinessTypeId() {
        return businessTypeId;
    }

    public void setBusinessTypeId(Long businessTypeId) {
        this.businessTypeId = businessTypeId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<CountryList> getCountriesList() {
        return countriesList;
    }

    public void setCountriesList(List<CountryList> countriesList) {
        this.countriesList = countriesList;
    }

    public List<IndustryClassificationList> getIndustryClassificationsList() {
        return industryClassificationsList;
    }

    public void setIndustryClassificationsList(List<IndustryClassificationList> industryClassificationsList) {
        this.industryClassificationsList = industryClassificationsList;
    }

    public List<CurrencyList> getCurrenciesList() {
        return currenciesList;
    }

    public void setCurrenciesList(List<CurrencyList> currenciesList) {
        this.currenciesList = currenciesList;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public List<StateList> getStateList() {
        return stateList;
    }

    public void setStateList(List<StateList> stateList) {
        this.stateList = stateList;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
