package in.hiaccounts.hinext.restaurant.model.checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Admin on 11/23/2017.
 */

public class RestraSaveCheckoutData implements Serializable {

    @SerializedName("result")
    @Expose
    public Object result;
    @SerializedName("date")
    @Expose
    public String date;

    @SerializedName("restaurantDate")
    @Expose
    public String restaurantDate;
    @SerializedName("time")
    @Expose
    public Object time;
    @SerializedName("itemDetils")
    @Expose
    public Object itemDetils;
    @SerializedName("companyData")
    @Expose
    public CompanyData companyData;
    @SerializedName("userAccountSetupData")
    @Expose
    public Object userAccountSetupData;
    @SerializedName("inventoryLocationData")
    @Expose
    public InventoryLocationData inventoryLocationData;
    @SerializedName("siData")
    @Expose
    public SiData siData;
    @SerializedName("recieptFooter")
    @Expose
    public Object recieptFooter;
    @SerializedName("companyLogoPath")
    @Expose
    public Object companyLogoPath;
    @SerializedName("userName")
    @Expose
    public Object userName;
    @SerializedName("footer")
    @Expose
    public Object footer;
    @SerializedName("hsnCode")
    @Expose
    public Object hsnCode;
    @SerializedName("cmpyLocation")
    @Expose
    public Object cmpyLocation;
    @SerializedName("custLocation")
    @Expose
    public Object custLocation;
    @SerializedName("suppLocation")
    @Expose
    public Object suppLocation;
    @SerializedName("billToGST")
    @Expose
    public Object billToGST;
    @SerializedName("totalIncludingTax")
    @Expose
    public double totalIncludingTax;
    @SerializedName("taxAmt")
    @Expose
    public double taxAmt;
    @SerializedName("cessTaxAmt")
    @Expose
    public double cessTaxAmt;
    @SerializedName("totalExcludingTax")
    @Expose
    public double totalExcludingTax;
    @SerializedName("totalPaid")
    @Expose
    public double totalPaid;
    @SerializedName("balance")
    @Expose
    public Object balance;
    @SerializedName("soNo")
    @Expose
    public Object soNo;
    @SerializedName("printType")
    @Expose
    public Object printType;
    @SerializedName("taxInvoice")
    @Expose
    public Object taxInvoice;
    @SerializedName("doNo")
    @Expose
    public Object doNo;
    @SerializedName("formSetupTemplateElementList")
    @Expose
    public Object formSetupTemplateElementList;
    @SerializedName("otherContactsName")
    @Expose
    public Object otherContactsName;

    @SerializedName("restaurantTokenRecord")
    @Expose
    public RestaurantTokenRecord restaurantTokenRecord;

    public String getRestaurantDate() {
        return restaurantDate;
    }

    public void setRestaurantDate(String restaurantDate) {
        this.restaurantDate = restaurantDate;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Object getTime() {
        return time;
    }

    public void setTime(Object time) {
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

    public Object getRecieptFooter() {
        return recieptFooter;
    }

    public void setRecieptFooter(Object recieptFooter) {
        this.recieptFooter = recieptFooter;
    }

    public Object getCompanyLogoPath() {
        return companyLogoPath;
    }

    public void setCompanyLogoPath(Object companyLogoPath) {
        this.companyLogoPath = companyLogoPath;
    }

    public Object getUserName() {
        return userName;
    }

    public void setUserName(Object userName) {
        this.userName = userName;
    }

    public Object getFooter() {
        return footer;
    }

    public void setFooter(Object footer) {
        this.footer = footer;
    }

    public Object getHsnCode() {
        return hsnCode;
    }

    public void setHsnCode(Object hsnCode) {
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

    public Object getBillToGST() {
        return billToGST;
    }

    public void setBillToGST(Object billToGST) {
        this.billToGST = billToGST;
    }

    public double getTotalIncludingTax() {
        return totalIncludingTax;
    }

    public void setTotalIncludingTax(double totalIncludingTax) {
        this.totalIncludingTax = totalIncludingTax;
    }

    public double getTaxAmt() {
        return taxAmt;
    }

    public void setTaxAmt(double taxAmt) {
        this.taxAmt = taxAmt;
    }

    public double getCessTaxAmt() {
        return cessTaxAmt;
    }

    public void setCessTaxAmt(double cessTaxAmt) {
        this.cessTaxAmt = cessTaxAmt;
    }

    public double getTotalExcludingTax() {
        return totalExcludingTax;
    }

    public void setTotalExcludingTax(double totalExcludingTax) {
        this.totalExcludingTax = totalExcludingTax;
    }

    public double getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(double totalPaid) {
        this.totalPaid = totalPaid;
    }

    public Object getBalance() {
        return balance;
    }

    public void setBalance(Object balance) {
        this.balance = balance;
    }

    public Object getSoNo() {
        return soNo;
    }

    public void setSoNo(Object soNo) {
        this.soNo = soNo;
    }

    public Object getPrintType() {
        return printType;
    }

    public void setPrintType(Object printType) {
        this.printType = printType;
    }

    public Object getTaxInvoice() {
        return taxInvoice;
    }

    public void setTaxInvoice(Object taxInvoice) {
        this.taxInvoice = taxInvoice;
    }

    public Object getDoNo() {
        return doNo;
    }

    public void setDoNo(Object doNo) {
        this.doNo = doNo;
    }

    public Object getFormSetupTemplateElementList() {
        return formSetupTemplateElementList;
    }

    public void setFormSetupTemplateElementList(Object formSetupTemplateElementList) {
        this.formSetupTemplateElementList = formSetupTemplateElementList;
    }

    public Object getOtherContactsName() {
        return otherContactsName;
    }

    public void setOtherContactsName(Object otherContactsName) {
        this.otherContactsName = otherContactsName;
    }

    public RestaurantTokenRecord getRestaurantTokenRecord() {
        return restaurantTokenRecord;
    }

    public void setRestaurantTokenRecord(RestaurantTokenRecord restaurantTokenRecord) {
        this.restaurantTokenRecord = restaurantTokenRecord;
    }
}
