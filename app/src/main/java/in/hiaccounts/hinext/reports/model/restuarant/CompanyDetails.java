package in.hiaccounts.hinext.reports.model.restuarant;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CompanyDetails implements Serializable {
    @SerializedName("companyId")
    @Expose
    public Long companyId;
    @SerializedName("lang")
    @Expose
    public String lang;
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
    public String hiConnectStatus;
    @SerializedName("hiConnectCompnyRegNo")
    @Expose
    public String hiConnectCompnyRegNo;
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
    public Long startyear;
    @SerializedName("endyear")
    @Expose
    public Long endyear;
    @SerializedName("closingMethod")
    @Expose
    public String closingMethod;
    @SerializedName("incdate")
    @Expose
    public String incdate;
    @SerializedName("accCodeSep")
    @Expose
    public Object accCodeSep;
    @SerializedName("gstId")
    @Expose
    public Long gstId;
    @SerializedName("logo")
    @Expose
    public Object logo;
    @SerializedName("bufferDays")
    @Expose
    public Long bufferDays;
    @SerializedName("createOrSelectAM")
    @Expose
    public String createOrSelectAM;
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
    public String useraccountId;
    @SerializedName("fileName")
    @Expose
    public Object fileName;
    @SerializedName("imageBlob")
    @Expose
    public Object imageBlob;
    @SerializedName("panNumber")
    @Expose
    public String panNumber;
    @SerializedName("gstregisteredDate")
    @Expose
    public String gstregisteredDate;

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

    public String getHiConnectStatus() {
        return hiConnectStatus;
    }

    public void setHiConnectStatus(String hiConnectStatus) {
        this.hiConnectStatus = hiConnectStatus;
    }

    public String getHiConnectCompnyRegNo() {
        return hiConnectCompnyRegNo;
    }

    public void setHiConnectCompnyRegNo(String hiConnectCompnyRegNo) {
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

    public Long getStartyear() {
        return startyear;
    }

    public void setStartyear(Long startyear) {
        this.startyear = startyear;
    }

    public Long getEndyear() {
        return endyear;
    }

    public void setEndyear(Long endyear) {
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

    public Object getAccCodeSep() {
        return accCodeSep;
    }

    public void setAccCodeSep(Object accCodeSep) {
        this.accCodeSep = accCodeSep;
    }

    public Long getGstId() {
        return gstId;
    }

    public void setGstId(Long gstId) {
        this.gstId = gstId;
    }

    public Object getLogo() {
        return logo;
    }

    public void setLogo(Object logo) {
        this.logo = logo;
    }

    public Long getBufferDays() {
        return bufferDays;
    }

    public void setBufferDays(Long bufferDays) {
        this.bufferDays = bufferDays;
    }

    public String getCreateOrSelectAM() {
        return createOrSelectAM;
    }

    public void setCreateOrSelectAM(String createOrSelectAM) {
        this.createOrSelectAM = createOrSelectAM;
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

    public String getUseraccountId() {
        return useraccountId;
    }

    public void setUseraccountId(String useraccountId) {
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
