
package in.hiaccounts.hinext.purchase.model.purchase_invoicereturn;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CompanyData implements Serializable{

    @SerializedName("companyId")
    @Expose
    private Object companyId;
    @SerializedName("lang")
    @Expose
    private Object lang;
    @SerializedName("countryId")
    @Expose
    private Object countryId;
    @SerializedName("registeredCompany")
    @Expose
    private Boolean registeredCompany;
    @SerializedName("registerNo")
    @Expose
    private Object registerNo;
    @SerializedName("company_name")
    @Expose
    private Object companyName;
    @SerializedName("company_no")
    @Expose
    private Object companyNo;
    @SerializedName("address")
    @Expose
    private Object address;
    @SerializedName("phone")
    @Expose
    private Object phone;
    @SerializedName("fax")
    @Expose
    private Object fax;
    @SerializedName("email")
    @Expose
    private Object email;
    @SerializedName("hiConnectStatus")
    @Expose
    private Object hiConnectStatus;
    @SerializedName("hiConnectCompnyRegNo")
    @Expose
    private Object hiConnectCompnyRegNo;
    @SerializedName("web")
    @Expose
    private Object web;
    @SerializedName("yearclosing")
    @Expose
    private Object yearclosing;
    @SerializedName("startperiod")
    @Expose
    private Object startperiod;
    @SerializedName("closingperiod")
    @Expose
    private Object closingperiod;
    @SerializedName("startyear")
    @Expose
    private Object startyear;
    @SerializedName("endyear")
    @Expose
    private Object endyear;
    @SerializedName("closingMethod")
    @Expose
    private Object closingMethod;
    @SerializedName("incdate")
    @Expose
    private Object incdate;
    @SerializedName("currencyId")
    @Expose
    private Object currencyId;
    @SerializedName("accCodeSep")
    @Expose
    private Object accCodeSep;
    @SerializedName("gstId")
    @Expose
    private Integer gstId;
    @SerializedName("industryclassificationId")
    @Expose
    private Object industryclassificationId;
    @SerializedName("businesstypeId")
    @Expose
    private Object businesstypeId;
    @SerializedName("logo")
    @Expose
    private Object logo;
    @SerializedName("bufferDays")
    @Expose
    private Integer bufferDays;
    @SerializedName("createOrSelectAM")
    @Expose
    private Object createOrSelectAM;
    @SerializedName("companyInfoId")
    @Expose
    private Object companyInfoId;
    @SerializedName("lan")
    @Expose
    private Object lan;
    @SerializedName("complitionstatus")
    @Expose
    private Object complitionstatus;
    @SerializedName("imageFile")
    @Expose
    private Object imageFile;
    @SerializedName("locationId")
    @Expose
    private Object locationId;
    @SerializedName("useraccount_id")
    @Expose
    private Object useraccountId;
    @SerializedName("fileName")
    @Expose
    private Object fileName;
    @SerializedName("imageBlob")
    @Expose
    private Object imageBlob;
    @SerializedName("state")
    @Expose
    private Object state;
    @SerializedName("panNumber")
    @Expose
    private Object panNumber;
    @SerializedName("gstregisteredDate")
    @Expose
    private Object gstregisteredDate;

    public Object getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Object companyId) {
        this.companyId = companyId;
    }

    public Object getLang() {
        return lang;
    }

    public void setLang(Object lang) {
        this.lang = lang;
    }

    public Object getCountryId() {
        return countryId;
    }

    public void setCountryId(Object countryId) {
        this.countryId = countryId;
    }

    public Boolean getRegisteredCompany() {
        return registeredCompany;
    }

    public void setRegisteredCompany(Boolean registeredCompany) {
        this.registeredCompany = registeredCompany;
    }

    public Object getRegisterNo() {
        return registerNo;
    }

    public void setRegisterNo(Object registerNo) {
        this.registerNo = registerNo;
    }

    public Object getCompanyName() {
        return companyName;
    }

    public void setCompanyName(Object companyName) {
        this.companyName = companyName;
    }

    public Object getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(Object companyNo) {
        this.companyNo = companyNo;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public Object getPhone() {
        return phone;
    }

    public void setPhone(Object phone) {
        this.phone = phone;
    }

    public Object getFax() {
        return fax;
    }

    public void setFax(Object fax) {
        this.fax = fax;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
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

    public Object getWeb() {
        return web;
    }

    public void setWeb(Object web) {
        this.web = web;
    }

    public Object getYearclosing() {
        return yearclosing;
    }

    public void setYearclosing(Object yearclosing) {
        this.yearclosing = yearclosing;
    }

    public Object getStartperiod() {
        return startperiod;
    }

    public void setStartperiod(Object startperiod) {
        this.startperiod = startperiod;
    }

    public Object getClosingperiod() {
        return closingperiod;
    }

    public void setClosingperiod(Object closingperiod) {
        this.closingperiod = closingperiod;
    }

    public Object getStartyear() {
        return startyear;
    }

    public void setStartyear(Object startyear) {
        this.startyear = startyear;
    }

    public Object getEndyear() {
        return endyear;
    }

    public void setEndyear(Object endyear) {
        this.endyear = endyear;
    }

    public Object getClosingMethod() {
        return closingMethod;
    }

    public void setClosingMethod(Object closingMethod) {
        this.closingMethod = closingMethod;
    }

    public Object getIncdate() {
        return incdate;
    }

    public void setIncdate(Object incdate) {
        this.incdate = incdate;
    }

    public Object getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Object currencyId) {
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

    public Object getIndustryclassificationId() {
        return industryclassificationId;
    }

    public void setIndustryclassificationId(Object industryclassificationId) {
        this.industryclassificationId = industryclassificationId;
    }

    public Object getBusinesstypeId() {
        return businesstypeId;
    }

    public void setBusinesstypeId(Object businesstypeId) {
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

    public Object getCreateOrSelectAM() {
        return createOrSelectAM;
    }

    public void setCreateOrSelectAM(Object createOrSelectAM) {
        this.createOrSelectAM = createOrSelectAM;
    }

    public Object getCompanyInfoId() {
        return companyInfoId;
    }

    public void setCompanyInfoId(Object companyInfoId) {
        this.companyInfoId = companyInfoId;
    }

    public Object getLan() {
        return lan;
    }

    public void setLan(Object lan) {
        this.lan = lan;
    }

    public Object getComplitionstatus() {
        return complitionstatus;
    }

    public void setComplitionstatus(Object complitionstatus) {
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

    public Object getState() {
        return state;
    }

    public void setState(Object state) {
        this.state = state;
    }

    public Object getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(Object panNumber) {
        this.panNumber = panNumber;
    }

    public Object getGstregisteredDate() {
        return gstregisteredDate;
    }

    public void setGstregisteredDate(Object gstregisteredDate) {
        this.gstregisteredDate = gstregisteredDate;
    }

}
