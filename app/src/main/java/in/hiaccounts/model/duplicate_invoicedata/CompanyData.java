
package in.hiaccounts.model.duplicate_invoicedata;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompanyData implements Serializable
{

    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("fileName")
    @Expose
    private String fileName;
    @SerializedName("currencyId")
    @Expose
    private long currencyId;
    @SerializedName("companyInfoId")
    @Expose
    private long companyInfoId;
    @SerializedName("companyId")
    @Expose
    private long companyId;
    @SerializedName("registeredCompany")
    @Expose
    private boolean registeredCompany;
    @SerializedName("gstregisteredDate")
    @Expose
    private String gstregisteredDate;
    @SerializedName("createOrSelectAM")
    @Expose
    private String createOrSelectAM;
    @SerializedName("complitionstatus")
    @Expose
    private String complitionstatus;
    @SerializedName("industryclassificationId")
    @Expose
    private long industryclassificationId;
    @SerializedName("businesstypeId")
    @Expose
    private long businesstypeId;
    @SerializedName("lang")
    @Expose
    private String lang;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("logo")
    @Expose
    private Object logo;
    @SerializedName("imageFile")
    @Expose
    private String imageFile;
    @SerializedName("lan")
    @Expose
    private String lan;
    @SerializedName("gstId")
    @Expose
    private long gstId;
    @SerializedName("bufferDays")
    @Expose
    private long bufferDays;
    @SerializedName("imageBlob")
    @Expose
    private Object imageBlob;
    @SerializedName("company_name")
    @Expose
    private String companyName;
    @SerializedName("company_no")
    @Expose
    private long companyNo;
    @SerializedName("accCodeSep")
    @Expose
    private String accCodeSep;
    @SerializedName("closingMethod")
    @Expose
    private String closingMethod;
    @SerializedName("web")
    @Expose
    private String web;
    @SerializedName("fax")
    @Expose
    private String fax;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("closingperiod")
    @Expose
    private String closingperiod;
    @SerializedName("endyear")
    @Expose
    private String endyear;
    @SerializedName("registerNo")
    @Expose
    private String registerNo;
    @SerializedName("startperiod")
    @Expose
    private String startperiod;
    @SerializedName("startyear")
    @Expose
    private String startyear;
    @SerializedName("countryId")
    @Expose
    private long countryId;
    @SerializedName("incdate")
    @Expose
    private String incdate;
    @SerializedName("yearclosing")
    @Expose
    private String yearclosing;
    private final static long serialVersionUID = -5951505035623312832L;

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
     * @param imageFile
     * @param accCodeSep
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
     * @param address
     * @param email
     * @param fileName
     * @param companyId
     * @param gstregisteredDate
     * @param incdate
     */
    public CompanyData(String address, String fileName, long currencyId, long companyInfoId, long companyId, boolean registeredCompany, String gstregisteredDate, String createOrSelectAM, String complitionstatus, long industryclassificationId, long businesstypeId, String lang, String email, Object logo, String imageFile, String lan, long gstId, long bufferDays, Object imageBlob, String companyName, long companyNo, String accCodeSep, String closingMethod, String web, String fax, String phone, String closingperiod, String endyear, String registerNo, String startperiod, String startyear, long countryId, String incdate, String yearclosing) {
        this.address = address;
        this.fileName = fileName;
        this.currencyId = currencyId;
        this.companyInfoId = companyInfoId;
        this.companyId = companyId;
        this.registeredCompany = registeredCompany;
        this.gstregisteredDate = gstregisteredDate;
        this.createOrSelectAM = createOrSelectAM;
        this.complitionstatus = complitionstatus;
        this.industryclassificationId = industryclassificationId;
        this.businesstypeId = businesstypeId;
        this.lang = lang;
        this.email = email;
        this.logo = logo;
        this.imageFile = imageFile;
        this.lan = lan;
        this.gstId = gstId;
        this.bufferDays = bufferDays;
        this.imageBlob = imageBlob;
        this.companyName = companyName;
        this.companyNo = companyNo;
        this.accCodeSep = accCodeSep;
        this.closingMethod = closingMethod;
        this.web = web;
        this.fax = fax;
        this.phone = phone;
        this.closingperiod = closingperiod;
        this.endyear = endyear;
        this.registerNo = registerNo;
        this.startperiod = startperiod;
        this.startyear = startyear;
        this.countryId = countryId;
        this.incdate = incdate;
        this.yearclosing = yearclosing;
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

    public long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(long currencyId) {
        this.currencyId = currencyId;
    }

    public long getCompanyInfoId() {
        return companyInfoId;
    }

    public void setCompanyInfoId(long companyInfoId) {
        this.companyInfoId = companyInfoId;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public boolean isRegisteredCompany() {
        return registeredCompany;
    }

    public void setRegisteredCompany(boolean registeredCompany) {
        this.registeredCompany = registeredCompany;
    }

    public String getGstregisteredDate() {
        return gstregisteredDate;
    }

    public void setGstregisteredDate(String gstregisteredDate) {
        this.gstregisteredDate = gstregisteredDate;
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

    public long getIndustryclassificationId() {
        return industryclassificationId;
    }

    public void setIndustryclassificationId(long industryclassificationId) {
        this.industryclassificationId = industryclassificationId;
    }

    public long getBusinesstypeId() {
        return businesstypeId;
    }

    public void setBusinesstypeId(long businesstypeId) {
        this.businesstypeId = businesstypeId;
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

    public Object getLogo() {
        return logo;
    }

    public void setLogo(Object logo) {
        this.logo = logo;
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    public String getLan() {
        return lan;
    }

    public void setLan(String lan) {
        this.lan = lan;
    }

    public long getGstId() {
        return gstId;
    }

    public void setGstId(long gstId) {
        this.gstId = gstId;
    }

    public long getBufferDays() {
        return bufferDays;
    }

    public void setBufferDays(long bufferDays) {
        this.bufferDays = bufferDays;
    }

    public Object getImageBlob() {
        return imageBlob;
    }

    public void setImageBlob(Object imageBlob) {
        this.imageBlob = imageBlob;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public long getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(long companyNo) {
        this.companyNo = companyNo;
    }

    public String getAccCodeSep() {
        return accCodeSep;
    }

    public void setAccCodeSep(String accCodeSep) {
        this.accCodeSep = accCodeSep;
    }

    public String getClosingMethod() {
        return closingMethod;
    }

    public void setClosingMethod(String closingMethod) {
        this.closingMethod = closingMethod;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
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

    public String getEndyear() {
        return endyear;
    }

    public void setEndyear(String endyear) {
        this.endyear = endyear;
    }

    public String getRegisterNo() {
        return registerNo;
    }

    public void setRegisterNo(String registerNo) {
        this.registerNo = registerNo;
    }

    public String getStartperiod() {
        return startperiod;
    }

    public void setStartperiod(String startperiod) {
        this.startperiod = startperiod;
    }

    public String getStartyear() {
        return startyear;
    }

    public void setStartyear(String startyear) {
        this.startyear = startyear;
    }

    public long getCountryId() {
        return countryId;
    }

    public void setCountryId(long countryId) {
        this.countryId = countryId;
    }

    public String getIncdate() {
        return incdate;
    }

    public void setIncdate(String incdate) {
        this.incdate = incdate;
    }

    public String getYearclosing() {
        return yearclosing;
    }

    public void setYearclosing(String yearclosing) {
        this.yearclosing = yearclosing;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}


