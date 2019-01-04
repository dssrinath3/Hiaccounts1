package in.hiaccounts.hinext.checkout.model.sales;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Checkout_Sales_ResponseData {
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
    public String billToGST;
    @SerializedName("totalIncludingTax")
    @Expose
    public Double totalIncludingTax;
    @SerializedName("taxAmt")
    @Expose
    public Double taxAmt;
    @SerializedName("cessTaxAmt")
    @Expose
    public Double cessTaxAmt;
    @SerializedName("totalExcludingTax")
    @Expose
    public Double totalExcludingTax;
    @SerializedName("totalPaid")
    @Expose
    public Double totalPaid;
    @SerializedName("balance")
    @Expose
    public Double balance;
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
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("exchangeRateValue")
    @Expose
    public String exchangeRateValue;

    @SerializedName("hiposServiceChargeAmt")
    @Expose
    public Double hiposServiceChargeAmt;
    @SerializedName("hiPosServiceCharge")
    @Expose
    public Double hiPosServiceCharge;
    @SerializedName("formSetupTemplateElementList")
    @Expose
    public Object formSetupTemplateElementList;
    @SerializedName("otherContactsName")
    @Expose
    public Object otherContactsName;
    @SerializedName("memo")
    @Expose
    public Object memo;

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

    public Double getCessTaxAmt() {
        return cessTaxAmt;
    }

    public void setCessTaxAmt(Double cessTaxAmt) {
        this.cessTaxAmt = cessTaxAmt;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExchangeRateValue() {
        return exchangeRateValue;
    }

    public void setExchangeRateValue(String exchangeRateValue) {
        this.exchangeRateValue = exchangeRateValue;
    }

    public Double getHiposServiceChargeAmt() {
        return hiposServiceChargeAmt;
    }

    public void setHiposServiceChargeAmt(Double hiposServiceChargeAmt) {
        this.hiposServiceChargeAmt = hiposServiceChargeAmt;
    }

    public Double getHiPosServiceCharge() {
        return hiPosServiceCharge;
    }

    public void setHiPosServiceCharge(Double hiPosServiceCharge) {
        this.hiPosServiceCharge = hiPosServiceCharge;
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

    public Object getMemo() {
        return memo;
    }

    public void setMemo(Object memo) {
        this.memo = memo;
    }
}
