package in.hiaccounts.hinext.sales.model.sales_quotation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import in.hiaccounts.hinext.purchase.model.purchase_saveresponses.CompanyData;
import in.hiaccounts.hinext.purchase.model.purchase_saveresponses.InventoryLocationData;
import in.hiaccounts.hinext.purchase.model.purchase_saveresponses.PiData;

/**
 * Created by administrator on 18/1/18.
 */

public class Sales_QuoationResponseData implements Serializable {
    @SerializedName("companyName")
    @Expose
    public String companyName;
    @SerializedName("companyNo")
    @Expose
    public String companyNo;
    @SerializedName("gstNo")
    @Expose
    public String gstNo;
    @SerializedName("tranactionId")
    @Expose
    public String tranactionId;
    @SerializedName("result")
    @Expose
    public String result;
    @SerializedName("date")
    @Expose
    public String date;
    @SerializedName("time")
    @Expose
    public String time;
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
    @SerializedName("piData")
    @Expose
    public PiData piData;
    @SerializedName("siData")
    @Expose
    private SiData siData;
    @SerializedName("recieptFooter")
    @Expose
    public String recieptFooter;
    @SerializedName("companyLogoPath")
    @Expose
    public String companyLogoPath;
    @SerializedName("userName")
    @Expose
    public String userName;
    @SerializedName("footer")
    @Expose
    public String footer;
    @SerializedName("supplierName")
    @Expose
    public String supplierName;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("address")
    @Expose
    public String address;
    @SerializedName("phoneNo")
    @Expose
    public String phoneNo;
    @SerializedName("invoiceNo")
    @Expose
    public String invoiceNo;
    @SerializedName("invoiceDate")
    @Expose
    public String invoiceDate;
    @SerializedName("invoiceAmount")
    @Expose
    public double invoiceAmount;
    @SerializedName("amountPaid")
    @Expose
    public double amountPaid;
    @SerializedName("balanceAmount")
    @Expose
    public double balanceAmount;
    @SerializedName("cashAmount")
    @Expose
    public double cashAmount;
    @SerializedName("cardAmount")
    @Expose
    public double cardAmount;
    @SerializedName("voucherAmount")
    @Expose
    public double voucherAmount;
    @SerializedName("hsnCode")
    @Expose
    public String hsnCode;
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
    public double balance;
    @SerializedName("taxInvoice")
    @Expose
    public String taxInvoice;
    @SerializedName("soNo")
    @Expose
    private String soNo;



    @SerializedName("cmpyLocation")
    @Expose
    private String cmpyLocation;
    @SerializedName("custLocation")
    @Expose
    private String custLocation;
    @SerializedName("suppLocation")
    @Expose
    private String suppLocation;
    @SerializedName("billToGST")
    @Expose
    private String billToGST;


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

    public String getGstNo() {
        return gstNo;
    }

    public void setGstNo(String gstNo) {
        this.gstNo = gstNo;
    }

    public String getTranactionId() {
        return tranactionId;
    }

    public void setTranactionId(String tranactionId) {
        this.tranactionId = tranactionId;
    }

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

    public PiData getPiData() {
        return piData;
    }

    public void setPiData(PiData piData) {
        this.piData = piData;
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

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public double getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(double invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public double getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(double balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public double getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(double cashAmount) {
        this.cashAmount = cashAmount;
    }

    public double getCardAmount() {
        return cardAmount;
    }

    public void setCardAmount(double cardAmount) {
        this.cardAmount = cardAmount;
    }

    public double getVoucherAmount() {
        return voucherAmount;
    }

    public void setVoucherAmount(double voucherAmount) {
        this.voucherAmount = voucherAmount;
    }

    public String getHsnCode() {
        return hsnCode;
    }

    public void setHsnCode(String hsnCode) {
        this.hsnCode = hsnCode;
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getTaxInvoice() {
        return taxInvoice;
    }

    public void setTaxInvoice(String taxInvoice) {
        this.taxInvoice = taxInvoice;
    }

    public String getSoNo() {
        return soNo;
    }

    public void setSoNo(String soNo) {
        this.soNo = soNo;
    }

    public String getCmpyLocation() {
        return cmpyLocation;
    }

    public void setCmpyLocation(String cmpyLocation) {
        this.cmpyLocation = cmpyLocation;
    }

    public String getCustLocation() {
        return custLocation;
    }

    public void setCustLocation(String custLocation) {
        this.custLocation = custLocation;
    }

    public String getSuppLocation() {
        return suppLocation;
    }

    public void setSuppLocation(String suppLocation) {
        this.suppLocation = suppLocation;
    }

    public String getBillToGST() {
        return billToGST;
    }

    public void setBillToGST(String billToGST) {
        this.billToGST = billToGST;
    }
}
