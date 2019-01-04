package in.hiaccounts.hinext.checkout.model.sales;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompanyData {
    @SerializedName("tempId")
    @Expose
    public Object tempId;
    @SerializedName("lang")
    @Expose
    public Object lang;
    @SerializedName("countryId")
    @Expose
    public Long countryId;
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
    @SerializedName("web")
    @Expose
    public String web;
    @SerializedName("incdate")
    @Expose
    public Object incdate;
    @SerializedName("currencyId")
    @Expose
    public Object currencyId;
    @SerializedName("accCodeSep")
    @Expose
    public Object accCodeSep;
    @SerializedName("gstId")
    @Expose
    public Long gstId;
    @SerializedName("industryClassificationId")
    @Expose
    public Object industryClassificationId;
    @SerializedName("businessTypeId")
    @Expose
    public Object businessTypeId;
    @SerializedName("logo")
    @Expose
    public Object logo;
    @SerializedName("companyId")
    @Expose
    public Object companyId;
    @SerializedName("panNumber")
    @Expose
    public String panNumber;
    @SerializedName("yearclosing")
    @Expose
    public Object yearclosing;
    @SerializedName("startperiod")
    @Expose
    public Object startperiod;
    @SerializedName("closingperiod")
    @Expose
    public Object closingperiod;
    @SerializedName("startyear")
    @Expose
    public Object startyear;
    @SerializedName("endyear")
    @Expose
    public Object endyear;
    @SerializedName("closingMethod")
    @Expose
    public Object closingMethod;
    @SerializedName("bufferDays")
    @Expose
    public Long bufferDays;
    @SerializedName("gstRegisteredDate")
    @Expose
    public Object gstRegisteredDate;
    @SerializedName("bufferDate")
    @Expose
    public Object bufferDate;
    @SerializedName("createOrSelectAM")
    @Expose
    public Object createOrSelectAM;
    @SerializedName("coaBLDBList")
    @Expose
    public Object coaBLDBList;
    @SerializedName("coaPLDBList")
    @Expose
    public Object coaPLDBList;
    @SerializedName("myFile")
    @Expose
    public Object myFile;
    @SerializedName("imageBlob")
    @Expose
    public Object imageBlob;
    @SerializedName("myFileContentType")
    @Expose
    public Object myFileContentType;
    @SerializedName("myFileFileName")
    @Expose
    public Object myFileFileName;
    @SerializedName("accreceivable")
    @Expose
    public Boolean accreceivable;
    @SerializedName("accpayable")
    @Expose
    public Boolean accpayable;
    @SerializedName("recurring")
    @Expose
    public Boolean recurring;
    @SerializedName("registrationalert")
    @Expose
    public Boolean registrationalert;
    @SerializedName("gstreturnalert")
    @Expose
    public Boolean gstreturnalert;
    @SerializedName("debatoralert")
    @Expose
    public Boolean debatoralert;
    @SerializedName("creditoralert")
    @Expose
    public Boolean creditoralert;
    @SerializedName("dueMounthly")
    @Expose
    public Boolean dueMounthly;
    @SerializedName("dueYearly")
    @Expose
    public Boolean dueYearly;
    @SerializedName("notaccReceivable")
    @Expose
    public Object notaccReceivable;
    @SerializedName("notaccPayable")
    @Expose
    public Object notaccPayable;
    @SerializedName("notRegistration")
    @Expose
    public Object notRegistration;
    @SerializedName("notGSTReturn")
    @Expose
    public Object notGSTReturn;
    @SerializedName("notDebator")
    @Expose
    public Object notDebator;
    @SerializedName("notCreditor")
    @Expose
    public Object notCreditor;
    @SerializedName("notDueMonthly")
    @Expose
    public Object notDueMonthly;
    @SerializedName("notDueYearly")
    @Expose
    public Object notDueYearly;
    @SerializedName("accreceivabledue")
    @Expose
    public Object accreceivabledue;
    @SerializedName("accpayabledue")
    @Expose
    public Object accpayabledue;
    @SerializedName("registrationdue")
    @Expose
    public Object registrationdue;
    @SerializedName("gstreturnalertdue")
    @Expose
    public Object gstreturnalertdue;
    @SerializedName("gstreturnalertduedate")
    @Expose
    public Object gstreturnalertduedate;
    @SerializedName("accPayable")
    @Expose
    public Boolean accPayable;
    @SerializedName("registrationAlert")
    @Expose
    public Boolean registrationAlert;
    @SerializedName("accReceivable")
    @Expose
    public Boolean accReceivable;
    @SerializedName("gstReturnAlert")
    @Expose
    public Boolean gstReturnAlert;
    @SerializedName("comprehensiveScheme")
    @Expose
    public Boolean comprehensiveScheme;
    @SerializedName("panNo")
    @Expose
    public Object panNo;
    @SerializedName("stateId")
    @Expose
    public Long stateId;
    @SerializedName("stateName")
    @Expose
    public String stateName;
    @SerializedName("fileName")
    @Expose
    public Object fileName;
    @SerializedName("pincode")
    @Expose
    public String pincode;
    @SerializedName("vehicleSeries")
    @Expose
    public String vehicleSeries;
    @SerializedName("currencyCode")
    @Expose
    public Object currencyCode;
    @SerializedName("gstregisteredDate")
    @Expose
    public String gstregisteredDate;
    @SerializedName("coaCodeImport")
    @Expose
    public Object coaCodeImport;

    public Object getTempId() {
        return tempId;
    }

    public void setTempId(Object tempId) {
        this.tempId = tempId;
    }

    public Object getLang() {
        return lang;
    }

    public void setLang(Object lang) {
        this.lang = lang;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
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

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
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

    public Long getGstId() {
        return gstId;
    }

    public void setGstId(Long gstId) {
        this.gstId = gstId;
    }

    public Object getIndustryClassificationId() {
        return industryClassificationId;
    }

    public void setIndustryClassificationId(Object industryClassificationId) {
        this.industryClassificationId = industryClassificationId;
    }

    public Object getBusinessTypeId() {
        return businessTypeId;
    }

    public void setBusinessTypeId(Object businessTypeId) {
        this.businessTypeId = businessTypeId;
    }

    public Object getLogo() {
        return logo;
    }

    public void setLogo(Object logo) {
        this.logo = logo;
    }

    public Object getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Object companyId) {
        this.companyId = companyId;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
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

    public Long getBufferDays() {
        return bufferDays;
    }

    public void setBufferDays(Long bufferDays) {
        this.bufferDays = bufferDays;
    }

    public Object getGstRegisteredDate() {
        return gstRegisteredDate;
    }

    public void setGstRegisteredDate(Object gstRegisteredDate) {
        this.gstRegisteredDate = gstRegisteredDate;
    }

    public Object getBufferDate() {
        return bufferDate;
    }

    public void setBufferDate(Object bufferDate) {
        this.bufferDate = bufferDate;
    }

    public Object getCreateOrSelectAM() {
        return createOrSelectAM;
    }

    public void setCreateOrSelectAM(Object createOrSelectAM) {
        this.createOrSelectAM = createOrSelectAM;
    }

    public Object getCoaBLDBList() {
        return coaBLDBList;
    }

    public void setCoaBLDBList(Object coaBLDBList) {
        this.coaBLDBList = coaBLDBList;
    }

    public Object getCoaPLDBList() {
        return coaPLDBList;
    }

    public void setCoaPLDBList(Object coaPLDBList) {
        this.coaPLDBList = coaPLDBList;
    }

    public Object getMyFile() {
        return myFile;
    }

    public void setMyFile(Object myFile) {
        this.myFile = myFile;
    }

    public Object getImageBlob() {
        return imageBlob;
    }

    public void setImageBlob(Object imageBlob) {
        this.imageBlob = imageBlob;
    }

    public Object getMyFileContentType() {
        return myFileContentType;
    }

    public void setMyFileContentType(Object myFileContentType) {
        this.myFileContentType = myFileContentType;
    }

    public Object getMyFileFileName() {
        return myFileFileName;
    }

    public void setMyFileFileName(Object myFileFileName) {
        this.myFileFileName = myFileFileName;
    }

    public Boolean getAccreceivable() {
        return accreceivable;
    }

    public void setAccreceivable(Boolean accreceivable) {
        this.accreceivable = accreceivable;
    }

    public Boolean getAccpayable() {
        return accpayable;
    }

    public void setAccpayable(Boolean accpayable) {
        this.accpayable = accpayable;
    }

    public Boolean getRecurring() {
        return recurring;
    }

    public void setRecurring(Boolean recurring) {
        this.recurring = recurring;
    }

    public Boolean getRegistrationalert() {
        return registrationalert;
    }

    public void setRegistrationalert(Boolean registrationalert) {
        this.registrationalert = registrationalert;
    }

    public Boolean getGstreturnalert() {
        return gstreturnalert;
    }

    public void setGstreturnalert(Boolean gstreturnalert) {
        this.gstreturnalert = gstreturnalert;
    }

    public Boolean getDebatoralert() {
        return debatoralert;
    }

    public void setDebatoralert(Boolean debatoralert) {
        this.debatoralert = debatoralert;
    }

    public Boolean getCreditoralert() {
        return creditoralert;
    }

    public void setCreditoralert(Boolean creditoralert) {
        this.creditoralert = creditoralert;
    }

    public Boolean getDueMounthly() {
        return dueMounthly;
    }

    public void setDueMounthly(Boolean dueMounthly) {
        this.dueMounthly = dueMounthly;
    }

    public Boolean getDueYearly() {
        return dueYearly;
    }

    public void setDueYearly(Boolean dueYearly) {
        this.dueYearly = dueYearly;
    }

    public Object getNotaccReceivable() {
        return notaccReceivable;
    }

    public void setNotaccReceivable(Object notaccReceivable) {
        this.notaccReceivable = notaccReceivable;
    }

    public Object getNotaccPayable() {
        return notaccPayable;
    }

    public void setNotaccPayable(Object notaccPayable) {
        this.notaccPayable = notaccPayable;
    }

    public Object getNotRegistration() {
        return notRegistration;
    }

    public void setNotRegistration(Object notRegistration) {
        this.notRegistration = notRegistration;
    }

    public Object getNotGSTReturn() {
        return notGSTReturn;
    }

    public void setNotGSTReturn(Object notGSTReturn) {
        this.notGSTReturn = notGSTReturn;
    }

    public Object getNotDebator() {
        return notDebator;
    }

    public void setNotDebator(Object notDebator) {
        this.notDebator = notDebator;
    }

    public Object getNotCreditor() {
        return notCreditor;
    }

    public void setNotCreditor(Object notCreditor) {
        this.notCreditor = notCreditor;
    }

    public Object getNotDueMonthly() {
        return notDueMonthly;
    }

    public void setNotDueMonthly(Object notDueMonthly) {
        this.notDueMonthly = notDueMonthly;
    }

    public Object getNotDueYearly() {
        return notDueYearly;
    }

    public void setNotDueYearly(Object notDueYearly) {
        this.notDueYearly = notDueYearly;
    }

    public Object getAccreceivabledue() {
        return accreceivabledue;
    }

    public void setAccreceivabledue(Object accreceivabledue) {
        this.accreceivabledue = accreceivabledue;
    }

    public Object getAccpayabledue() {
        return accpayabledue;
    }

    public void setAccpayabledue(Object accpayabledue) {
        this.accpayabledue = accpayabledue;
    }

    public Object getRegistrationdue() {
        return registrationdue;
    }

    public void setRegistrationdue(Object registrationdue) {
        this.registrationdue = registrationdue;
    }

    public Object getGstreturnalertdue() {
        return gstreturnalertdue;
    }

    public void setGstreturnalertdue(Object gstreturnalertdue) {
        this.gstreturnalertdue = gstreturnalertdue;
    }

    public Object getGstreturnalertduedate() {
        return gstreturnalertduedate;
    }

    public void setGstreturnalertduedate(Object gstreturnalertduedate) {
        this.gstreturnalertduedate = gstreturnalertduedate;
    }

    public Boolean getAccPayable() {
        return accPayable;
    }

    public void setAccPayable(Boolean accPayable) {
        this.accPayable = accPayable;
    }

    public Boolean getRegistrationAlert() {
        return registrationAlert;
    }

    public void setRegistrationAlert(Boolean registrationAlert) {
        this.registrationAlert = registrationAlert;
    }

    public Boolean getAccReceivable() {
        return accReceivable;
    }

    public void setAccReceivable(Boolean accReceivable) {
        this.accReceivable = accReceivable;
    }

    public Boolean getGstReturnAlert() {
        return gstReturnAlert;
    }

    public void setGstReturnAlert(Boolean gstReturnAlert) {
        this.gstReturnAlert = gstReturnAlert;
    }

    public Boolean getComprehensiveScheme() {
        return comprehensiveScheme;
    }

    public void setComprehensiveScheme(Boolean comprehensiveScheme) {
        this.comprehensiveScheme = comprehensiveScheme;
    }

    public Object getPanNo() {
        return panNo;
    }

    public void setPanNo(Object panNo) {
        this.panNo = panNo;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public Object getFileName() {
        return fileName;
    }

    public void setFileName(Object fileName) {
        this.fileName = fileName;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getVehicleSeries() {
        return vehicleSeries;
    }

    public void setVehicleSeries(String vehicleSeries) {
        this.vehicleSeries = vehicleSeries;
    }

    public Object getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(Object currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getGstregisteredDate() {
        return gstregisteredDate;
    }

    public void setGstregisteredDate(String gstregisteredDate) {
        this.gstregisteredDate = gstregisteredDate;
    }

    public Object getCoaCodeImport() {
        return coaCodeImport;
    }

    public void setCoaCodeImport(Object coaCodeImport) {
        this.coaCodeImport = coaCodeImport;
    }
}
