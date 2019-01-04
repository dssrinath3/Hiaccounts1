
package in.hiaccounts.model.invoice_data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class InvoiceData implements Serializable {

    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("companyData")
    @Expose
    private CompanyData companyData;
    @SerializedName("itemDetils")
    @Expose
    private Object itemDetils;
    @SerializedName("recieptFooter")
    @Expose
    private String recieptFooter;
    @SerializedName("siData")
    @Expose
    private SiData siData;
    @SerializedName("inventoryLocationData")
    @Expose
    private InventoryLocationData inventoryLocationData;
    @SerializedName("userAccountSetupData")
    @Expose
    private Object userAccountSetupData;
    @SerializedName("companyLogoPath")
    @Expose
    private Object companyLogoPath;

    /**
     * No args constructor for use in serialization
     *
     */
    public InvoiceData() {
    }

    /**
     *
     * @param companyLogoPath
     * @param companyData
     * @param result
     * @param time
     * @param recieptFooter
     * @param inventoryLocationData
     * @param userAccountSetupData
     * @param siData
     * @param userName
     * @param itemDetils
     * @param date
     */
    public InvoiceData(String result, String time, String date, String userName, CompanyData companyData, Object itemDetils, String recieptFooter, SiData siData, InventoryLocationData inventoryLocationData, Object userAccountSetupData, Object companyLogoPath) {
        super();
        this.result = result;
        this.time = time;
        this.date = date;
        this.userName = userName;
        this.companyData = companyData;
        this.itemDetils = itemDetils;
        this.recieptFooter = recieptFooter;
        this.siData = siData;
        this.inventoryLocationData = inventoryLocationData;
        this.userAccountSetupData = userAccountSetupData;
        this.companyLogoPath = companyLogoPath;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public CompanyData getCompanyData() {
        return companyData;
    }

    public void setCompanyData(CompanyData companyData) {
        this.companyData = companyData;
    }

    public Object getItemDetils() {
        return itemDetils;
    }

    public void setItemDetils(Object itemDetils) {
        this.itemDetils = itemDetils;
    }

    public String getRecieptFooter() {
        return recieptFooter;
    }

    public void setRecieptFooter(String recieptFooter) {
        this.recieptFooter = recieptFooter;
    }

    public SiData getSiData() {
        return siData;
    }

    public void setSiData(SiData siData) {
        this.siData = siData;
    }

    public InventoryLocationData getInventoryLocationData() {
        return inventoryLocationData;
    }

    public void setInventoryLocationData(InventoryLocationData inventoryLocationData) {
        this.inventoryLocationData = inventoryLocationData;
    }

    public Object getUserAccountSetupData() {
        return userAccountSetupData;
    }

    public void setUserAccountSetupData(Object userAccountSetupData) {
        this.userAccountSetupData = userAccountSetupData;
    }

    public Object getCompanyLogoPath() {
        return companyLogoPath;
    }

    public void setCompanyLogoPath(Object companyLogoPath) {
        this.companyLogoPath = companyLogoPath;
    }

}
