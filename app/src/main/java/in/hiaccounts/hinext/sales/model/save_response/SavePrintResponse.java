
package in.hiaccounts.hinext.sales.model.save_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SavePrintResponse implements Serializable {

    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("itemDetils")
    @Expose
    private Object itemDetils;
    @SerializedName("companyData")
    @Expose
    private CompanyData companyData;
    @SerializedName("userAccountSetupData")
    @Expose
    private Object userAccountSetupData;
    @SerializedName("inventoryLocationData")
    @Expose
    private InventoryLocationData inventoryLocationData;
    @SerializedName("siData")
    @Expose
    private SiData siData;
    @SerializedName("recieptFooter")
    @Expose
    private String recieptFooter;
    @SerializedName("companyLogoPath")
    @Expose
    private String companyLogoPath;
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("footer")
    @Expose
    private String footer;
    @SerializedName("hsnCode")
    @Expose
    private String hsnCode;
    @SerializedName("cmpyLocation")
    @Expose
    private Object cmpyLocation;
    @SerializedName("custLocation")
    @Expose
    private Object custLocation;
    @SerializedName("suppLocation")
    @Expose
    private Object suppLocation;
    @SerializedName("billToGST")
    @Expose
    private String billToGST;
    @SerializedName("totalIncludingTax")
    @Expose
    private Double totalIncludingTax;
    @SerializedName("taxAmt")
    @Expose
    private Double taxAmt;
    @SerializedName("totalExcludingTax")
    @Expose
    private Double totalExcludingTax;
    @SerializedName("totalPaid")
    @Expose
    private Double totalPaid;
    @SerializedName("balance")
    @Expose
    private Double balance;
    @SerializedName("soNo")
    @Expose
    private String soNo;

    @SerializedName("cessTaxAmt")
    @Expose
    private Double cessTaxAmt;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Object getItemDetils() {
        return itemDetils;
    }

    public void setItemDetils(Object itemDetils) {
        this.itemDetils = itemDetils;
    }

    public CompanyData getCompanyData() {
        return companyData;
    }

    public void setCompanyData(CompanyData companyData) {
        this.companyData = companyData;
    }

    public Object getUserAccountSetupData() {
        return userAccountSetupData;
    }

    public void setUserAccountSetupData(Object userAccountSetupData) {
        this.userAccountSetupData = userAccountSetupData;
    }

    public InventoryLocationData getInventoryLocationData() {
        return inventoryLocationData;
    }

    public void setInventoryLocationData(InventoryLocationData inventoryLocationData) {
        this.inventoryLocationData = inventoryLocationData;
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

    public String getCompanyLogoPath() {
        return companyLogoPath;
    }

    public void setCompanyLogoPath(String companyLogoPath) {
        this.companyLogoPath = companyLogoPath;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public String getHsnCode() {
        return hsnCode;
    }

    public void setHsnCode(String hsnCode) {
        this.hsnCode = hsnCode;
    }

    public Object getCmpyLocation() {
        return cmpyLocation;
    }

    public void setCmpyLocation(Object cmpyLocation) {
        this.cmpyLocation = cmpyLocation;
    }

    public Object getCustLocation() {
        return custLocation;
    }

    public void setCustLocation(Object custLocation) {
        this.custLocation = custLocation;
    }

    public Object getSuppLocation() {
        return suppLocation;
    }

    public void setSuppLocation(Object suppLocation) {
        this.suppLocation = suppLocation;
    }

    public String getBillToGST() {
        return billToGST;
    }

    public void setBillToGST(String billToGST) {
        this.billToGST = billToGST;
    }

    public Double getTotalIncludingTax() {
        return totalIncludingTax;
    }

    public void setTotalIncludingTax(Double totalIncludingTax) {
        this.totalIncludingTax = totalIncludingTax;
    }

    public Double getTaxAmt() {
        return taxAmt;
    }

    public void setTaxAmt(Double taxAmt) {
        this.taxAmt = taxAmt;
    }

    public Double getTotalExcludingTax() {
        return totalExcludingTax;
    }

    public void setTotalExcludingTax(Double totalExcludingTax) {
        this.totalExcludingTax = totalExcludingTax;
    }

    public Double getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(Double totalPaid) {
        this.totalPaid = totalPaid;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getSoNo() {
        return soNo;
    }

    public void setSoNo(String soNo) {
        this.soNo = soNo;
    }

    public Double getCessTaxAmt() {
        return cessTaxAmt;
    }

    public void setCessTaxAmt(Double cessTaxAmt) {
        this.cessTaxAmt = cessTaxAmt;
    }
}
