
package in.hiaccounts.model.pos_invoice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CompanyData implements Serializable{

    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("fileName")
    @Expose
    private Object fileName;
    @SerializedName("company_no")
    @Expose
    private String companyNo;
    @SerializedName("gstId")
    @Expose
    private int gstId;
    @SerializedName("imageFile")
    @Expose
    private Object imageFile;
    @SerializedName("yearclosing")
    @Expose
    private String yearclosing;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("bufferDays")
    @Expose
    private int bufferDays;
    @SerializedName("registerNo")
    @Expose
    private String registerNo;
    @SerializedName("accCodeSep")
    @Expose
    private Object accCodeSep;
    @SerializedName("lan")
    @Expose
    private Object lan;
    @SerializedName("company_name")
    @Expose
    private String companyName;
    @SerializedName("countryId")
    @Expose
    private CountryId countryId;
    @SerializedName("endyear")
    @Expose
    private String endyear;
    @SerializedName("web")
    @Expose
    private String web;
    @SerializedName("closingMethod")
    @Expose
    private String closingMethod;
    @SerializedName("startperiod")
    @Expose
    private String startperiod;
    @SerializedName("fax")
    @Expose
    private String fax;
    @SerializedName("lang")
    @Expose
    private String lang;
    @SerializedName("incdate")
    @Expose
    private Object incdate;
    @SerializedName("imageBlob")
    @Expose
    private Object imageBlob;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("closingperiod")
    @Expose
    private String closingperiod;
    @SerializedName("startyear")
    @Expose
    private String startyear;
    @SerializedName("logo")
    @Expose
    private Object logo;
    @SerializedName("currencyId")
    @Expose
    private CurrencyId currencyId;
    @SerializedName("companyInfoId")
    @Expose
    private CompanyInfoId companyInfoId;
    @SerializedName("companyId")
    @Expose
    private int companyId;
    @SerializedName("industryclassificationId")
    @Expose
    private IndustryclassificationId industryclassificationId;
    @SerializedName("createOrSelectAM")
    @Expose
    private String createOrSelectAM;
    @SerializedName("complitionstatus")
    @Expose
    private String complitionstatus;
    @SerializedName("businesstypeId")
    @Expose
    private BusinesstypeId businesstypeId;
    @SerializedName("gstregisteredDate")
    @Expose
    private String gstregisteredDate;
    @SerializedName("registeredCompany")
    @Expose
    private boolean registeredCompany;

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
     * @param companyNo
     * @param closingMethod
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
     * @param startperiod
     * @param web
     * @param companyName
     * @param registeredCompany
     * @param registerNo
     * @param bufferDays
     * @param email
     * @param address
     * @param fileName
     * @param companyId
     * @param gstregisteredDate
     * @param incdate
     */
    public CompanyData(String address, Object fileName, String companyNo, int gstId, Object imageFile, String yearclosing, String email, int bufferDays, String registerNo, Object accCodeSep, Object lan, String companyName, CountryId countryId, String endyear, String web, String closingMethod, String startperiod, String fax, String lang, Object incdate, Object imageBlob, String phone, String closingperiod, String startyear, Object logo, CurrencyId currencyId, CompanyInfoId companyInfoId, int companyId, IndustryclassificationId industryclassificationId, String createOrSelectAM, String complitionstatus, BusinesstypeId businesstypeId, String gstregisteredDate, boolean registeredCompany) {
        super();
        this.address = address;
        this.fileName = fileName;
        this.companyNo = companyNo;
        this.gstId = gstId;
        this.imageFile = imageFile;
        this.yearclosing = yearclosing;
        this.email = email;
        this.bufferDays = bufferDays;
        this.registerNo = registerNo;
        this.accCodeSep = accCodeSep;
        this.lan = lan;
        this.companyName = companyName;
        this.countryId = countryId;
        this.endyear = endyear;
        this.web = web;
        this.closingMethod = closingMethod;
        this.startperiod = startperiod;
        this.fax = fax;
        this.lang = lang;
        this.incdate = incdate;
        this.imageBlob = imageBlob;
        this.phone = phone;
        this.closingperiod = closingperiod;
        this.startyear = startyear;
        this.logo = logo;
        this.currencyId = currencyId;
        this.companyInfoId = companyInfoId;
        this.companyId = companyId;
        this.industryclassificationId = industryclassificationId;
        this.createOrSelectAM = createOrSelectAM;
        this.complitionstatus = complitionstatus;
        this.businesstypeId = businesstypeId;
        this.gstregisteredDate = gstregisteredDate;
        this.registeredCompany = registeredCompany;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Object getFileName() {
        return fileName;
    }

    public void setFileName(Object fileName) {
        this.fileName = fileName;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public int getGstId() {
        return gstId;
    }

    public void setGstId(int gstId) {
        this.gstId = gstId;
    }

    public Object getImageFile() {
        return imageFile;
    }

    public void setImageFile(Object imageFile) {
        this.imageFile = imageFile;
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

    public int getBufferDays() {
        return bufferDays;
    }

    public void setBufferDays(int bufferDays) {
        this.bufferDays = bufferDays;
    }

    public String getRegisterNo() {
        return registerNo;
    }

    public void setRegisterNo(String registerNo) {
        this.registerNo = registerNo;
    }

    public Object getAccCodeSep() {
        return accCodeSep;
    }

    public void setAccCodeSep(Object accCodeSep) {
        this.accCodeSep = accCodeSep;
    }

    public Object getLan() {
        return lan;
    }

    public void setLan(Object lan) {
        this.lan = lan;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public CountryId getCountryId() {
        return countryId;
    }

    public void setCountryId(CountryId countryId) {
        this.countryId = countryId;
    }

    public String getEndyear() {
        return endyear;
    }

    public void setEndyear(String endyear) {
        this.endyear = endyear;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
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

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Object getIncdate() {
        return incdate;
    }

    public void setIncdate(Object incdate) {
        this.incdate = incdate;
    }

    public Object getImageBlob() {
        return imageBlob;
    }

    public void setImageBlob(Object imageBlob) {
        this.imageBlob = imageBlob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public Object getLogo() {
        return logo;
    }

    public void setLogo(Object logo) {
        this.logo = logo;
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

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public IndustryclassificationId getIndustryclassificationId() {
        return industryclassificationId;
    }

    public void setIndustryclassificationId(IndustryclassificationId industryclassificationId) {
        this.industryclassificationId = industryclassificationId;
    }

    public String getCreateOrSelectAM() {
        return createOrSelectAM;
    }

    public void setCreateOrSelectAM(String createOrSelectAM) {
        this.createOrSelectAM = createOrSelectAM;
    }

    public String getComplitionstatus() {
        return complitionstatus;
    }

    public void setComplitionstatus(String complitionstatus) {
        this.complitionstatus = complitionstatus;
    }

    public BusinesstypeId getBusinesstypeId() {
        return businesstypeId;
    }

    public void setBusinesstypeId(BusinesstypeId businesstypeId) {
        this.businesstypeId = businesstypeId;
    }

    public String getGstregisteredDate() {
        return gstregisteredDate;
    }

    public void setGstregisteredDate(String gstregisteredDate) {
        this.gstregisteredDate = gstregisteredDate;
    }

    public boolean isRegisteredCompany() {
        return registeredCompany;
    }

    public void setRegisteredCompany(boolean registeredCompany) {
        this.registeredCompany = registeredCompany;
    }

}
