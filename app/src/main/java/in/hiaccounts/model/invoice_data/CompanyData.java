
package in.hiaccounts.model.invoice_data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CompanyData implements Serializable{

    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("fileName")
    @Expose
    private String fileName;
    @SerializedName("useraccount_id")
    @Expose
    private Object useraccountId;
    @SerializedName("registeredCompany")
    @Expose
    private boolean registeredCompany;
    @SerializedName("businesstypeId")
    @Expose
    private BusinesstypeId businesstypeId;
    @SerializedName("industryclassificationId")
    @Expose
    private IndustryclassificationId industryclassificationId;
    @SerializedName("hiConnectCompnyRegNo")
    @Expose
    private Object hiConnectCompnyRegNo;
    @SerializedName("complitionstatus")
    @Expose
    private String complitionstatus;
    @SerializedName("createOrSelectAM")
    @Expose
    private String createOrSelectAM;
    @SerializedName("gstregisteredDate")
    @Expose
    private String gstregisteredDate;
    @SerializedName("hiConnectStatus")
    @Expose
    private Object hiConnectStatus;
    @SerializedName("locationId")
    @Expose
    private Object locationId;
    @SerializedName("company_name")
    @Expose
    private String companyName;
    @SerializedName("startyear")
    @Expose
    private String startyear;
    @SerializedName("fax")
    @Expose
    private String fax;
    @SerializedName("gstId")
    @Expose
    private long gstId;
    @SerializedName("incdate")
    @Expose
    private Object incdate;
    @SerializedName("yearclosing")
    @Expose
    private String yearclosing;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("countryId")
    @Expose
    private CountryId countryId;
    @SerializedName("logo")
    @Expose
    private Object logo;
    @SerializedName("bufferDays")
    @Expose
    private long bufferDays;
    @SerializedName("imageFile")
    @Expose
    private Object imageFile;
    @SerializedName("registerNo")
    @Expose
    private String registerNo;
    @SerializedName("imageBlob")
    @Expose
    private Object imageBlob;
    @SerializedName("lang")
    @Expose
    private String lang;
    @SerializedName("endyear")
    @Expose
    private String endyear;
    @SerializedName("company_no")
    @Expose
    private String companyNo;
    @SerializedName("closingMethod")
    @Expose
    private String closingMethod;
    @SerializedName("closingperiod")
    @Expose
    private String closingperiod;
    @SerializedName("lan")
    @Expose
    private Object lan;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("accCodeSep")
    @Expose
    private Object accCodeSep;
    @SerializedName("startperiod")
    @Expose
    private String startperiod;
    @SerializedName("web")
    @Expose
    private String web;
    @SerializedName("companyId")
    @Expose
    private long companyId;
    @SerializedName("currencyId")
    @Expose
    private CurrencyId currencyId;
    @SerializedName("companyInfoId")
    @Expose
    private CompanyInfoId companyInfoId;

    /**
     * No args constructor for use in serialization
     *
     */
    public CompanyData() {
    }

    /**
     *
     * @param createOrSelectAM
     * @param industryclassificationId
     * @param phone
     * @param currencyId
     * @param endyear
     * @param locationId
     * @param closingMethod
     * @param companyNo
     * @param businesstypeId
     * @param lang
     * @param imageBlob
     * @param yearclosing
     * @param startyear
     * @param companyInfoId
     * @param lan
     * @param accCodeSep
     * @param imageFile
     * @param countryId
     * @param logo
     * @param complitionstatus
     * @param fax
     * @param closingperiod
     * @param gstId
     * @param hiConnectCompnyRegNo
     * @param startperiod
     * @param useraccountId
     * @param web
     * @param companyName
     * @param registeredCompany
     * @param registerNo
     * @param bufferDays
     * @param address
     * @param email
     * @param fileName
     * @param companyId
     * @param hiConnectStatus
     * @param gstregisteredDate
     * @param incdate
     */
    public CompanyData(String address, String fileName, Object useraccountId, boolean registeredCompany, BusinesstypeId businesstypeId, IndustryclassificationId industryclassificationId, Object hiConnectCompnyRegNo, String complitionstatus, String createOrSelectAM, String gstregisteredDate, Object hiConnectStatus, Object locationId, String companyName, String startyear, String fax, long gstId, Object incdate, String yearclosing, String email, CountryId countryId, Object logo, long bufferDays, Object imageFile, String registerNo, Object imageBlob, String lang, String endyear, String companyNo, String closingMethod, String closingperiod, Object lan, String phone, Object accCodeSep, String startperiod, String web, long companyId, CurrencyId currencyId, CompanyInfoId companyInfoId) {
        super();
        this.address = address;
        this.fileName = fileName;
        this.useraccountId = useraccountId;
        this.registeredCompany = registeredCompany;
        this.businesstypeId = businesstypeId;
        this.industryclassificationId = industryclassificationId;
        this.hiConnectCompnyRegNo = hiConnectCompnyRegNo;
        this.complitionstatus = complitionstatus;
        this.createOrSelectAM = createOrSelectAM;
        this.gstregisteredDate = gstregisteredDate;
        this.hiConnectStatus = hiConnectStatus;
        this.locationId = locationId;
        this.companyName = companyName;
        this.startyear = startyear;
        this.fax = fax;
        this.gstId = gstId;
        this.incdate = incdate;
        this.yearclosing = yearclosing;
        this.email = email;
        this.countryId = countryId;
        this.logo = logo;
        this.bufferDays = bufferDays;
        this.imageFile = imageFile;
        this.registerNo = registerNo;
        this.imageBlob = imageBlob;
        this.lang = lang;
        this.endyear = endyear;
        this.companyNo = companyNo;
        this.closingMethod = closingMethod;
        this.closingperiod = closingperiod;
        this.lan = lan;
        this.phone = phone;
        this.accCodeSep = accCodeSep;
        this.startperiod = startperiod;
        this.web = web;
        this.companyId = companyId;
        this.currencyId = currencyId;
        this.companyInfoId = companyInfoId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Object getUseraccountId() {
        return useraccountId;
    }

    public void setUseraccountId(Object useraccountId) {
        this.useraccountId = useraccountId;
    }

    public boolean isRegisteredCompany() {
        return registeredCompany;
    }

    public void setRegisteredCompany(boolean registeredCompany) {
        this.registeredCompany = registeredCompany;
    }

    public BusinesstypeId getBusinesstypeId() {
        return businesstypeId;
    }

    public void setBusinesstypeId(BusinesstypeId businesstypeId) {
        this.businesstypeId = businesstypeId;
    }

    public IndustryclassificationId getIndustryclassificationId() {
        return industryclassificationId;
    }

    public void setIndustryclassificationId(IndustryclassificationId industryclassificationId) {
        this.industryclassificationId = industryclassificationId;
    }

    public Object getHiConnectCompnyRegNo() {
        return hiConnectCompnyRegNo;
    }

    public void setHiConnectCompnyRegNo(Object hiConnectCompnyRegNo) {
        this.hiConnectCompnyRegNo = hiConnectCompnyRegNo;
    }

    public String getComplitionstatus() {
        return complitionstatus;
    }

    public void setComplitionstatus(String complitionstatus) {
        this.complitionstatus = complitionstatus;
    }

    public String getCreateOrSelectAM() {
        return createOrSelectAM;
    }

    public void setCreateOrSelectAM(String createOrSelectAM) {
        this.createOrSelectAM = createOrSelectAM;
    }

    public String getGstregisteredDate() {
        return gstregisteredDate;
    }

    public void setGstregisteredDate(String gstregisteredDate) {
        this.gstregisteredDate = gstregisteredDate;
    }

    public Object getHiConnectStatus() {
        return hiConnectStatus;
    }

    public void setHiConnectStatus(Object hiConnectStatus) {
        this.hiConnectStatus = hiConnectStatus;
    }

    public Object getLocationId() {
        return locationId;
    }

    public void setLocationId(Object locationId) {
        this.locationId = locationId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getStartyear() {
        return startyear;
    }

    public void setStartyear(String startyear) {
        this.startyear = startyear;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public long getGstId() {
        return gstId;
    }

    public void setGstId(long gstId) {
        this.gstId = gstId;
    }

    public Object getIncdate() {
        return incdate;
    }

    public void setIncdate(Object incdate) {
        this.incdate = incdate;
    }

    public String getYearclosing() {
        return yearclosing;
    }

    public void setYearclosing(String yearclosing) {
        this.yearclosing = yearclosing;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CountryId getCountryId() {
        return countryId;
    }

    public void setCountryId(CountryId countryId) {
        this.countryId = countryId;
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

    public Object getImageFile() {
        return imageFile;
    }

    public void setImageFile(Object imageFile) {
        this.imageFile = imageFile;
    }

    public String getRegisterNo() {
        return registerNo;
    }

    public void setRegisterNo(String registerNo) {
        this.registerNo = registerNo;
    }

    public Object getImageBlob() {
        return imageBlob;
    }

    public void setImageBlob(Object imageBlob) {
        this.imageBlob = imageBlob;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getEndyear() {
        return endyear;
    }

    public void setEndyear(String endyear) {
        this.endyear = endyear;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getClosingMethod() {
        return closingMethod;
    }

    public void setClosingMethod(String closingMethod) {
        this.closingMethod = closingMethod;
    }

    public String getClosingperiod() {
        return closingperiod;
    }

    public void setClosingperiod(String closingperiod) {
        this.closingperiod = closingperiod;
    }

    public Object getLan() {
        return lan;
    }

    public void setLan(Object lan) {
        this.lan = lan;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Object getAccCodeSep() {
        return accCodeSep;
    }

    public void setAccCodeSep(Object accCodeSep) {
        this.accCodeSep = accCodeSep;
    }

    public String getStartperiod() {
        return startperiod;
    }

    public void setStartperiod(String startperiod) {
        this.startperiod = startperiod;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public CurrencyId getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(CurrencyId currencyId) {
        this.currencyId = currencyId;
    }

    public CompanyInfoId getCompanyInfoId() {
        return companyInfoId;
    }

    public void setCompanyInfoId(CompanyInfoId companyInfoId) {
        this.companyInfoId = companyInfoId;
    }

}
