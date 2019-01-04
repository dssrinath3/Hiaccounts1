
package in.hiaccounts.model.pos_invoice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Invoicedata implements Serializable {

    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("companyData")
    @Expose
    private CompanyData companyData;
    @SerializedName("itemDetils")
    @Expose
    private Object itemDetils;
    @SerializedName("siData")
    @Expose
    private SiData siData;
    @SerializedName("recieptFooter")
    @Expose
    private String recieptFooter;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Invoicedata() {
    }

    /**
     * 
     * @param companyData
     * @param result
     * @param time
     * @param recieptFooter
     * @param siData
     * @param itemDetils
     * @param date
     */
    public Invoicedata(String result, String time, String date, CompanyData companyData, Object itemDetils, SiData siData, String recieptFooter) {
        super();
        this.result = result;
        this.time = time;
        this.date = date;
        this.companyData = companyData;
        this.itemDetils = itemDetils;
        this.siData = siData;
        this.recieptFooter = recieptFooter;
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

    public SiData getSiData() {
        return siData;
    }

    public void setSiData(SiData siData) {
        this.siData = siData;
    }

    public String getRecieptFooter() {
        return recieptFooter;
    }

    public void setRecieptFooter(String recieptFooter) {
        this.recieptFooter = recieptFooter;
    }

}
