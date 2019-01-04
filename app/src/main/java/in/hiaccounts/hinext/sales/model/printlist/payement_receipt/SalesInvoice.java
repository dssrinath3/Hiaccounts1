
package in.hiaccounts.hinext.sales.model.printlist.payement_receipt;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SalesInvoice implements Serializable{

    @SerializedName("serialNumber")
    @Expose
    public String serialNumber;
    @SerializedName("customerId")
    @Expose
    public CustomerId customerId;
    @SerializedName("referenceno")
    @Expose
    public Object referenceno;
    @SerializedName("flag")
    @Expose
    public String flag;
    @SerializedName("project")
    @Expose
    public Object project;
    @SerializedName("termsDesc")
    @Expose
    public Object termsDesc;
    @SerializedName("gstflag")
    @Expose
    public boolean gstflag;
  /*  @SerializedName("companyId")
    @Expose
    public CompanyId companyId;*/
    @SerializedName("totalamountsalesretun")
    @Expose
    public double totalamountsalesretun;
    @SerializedName("totCommissionAmount")
    @Expose
    public Object totCommissionAmount;
    @SerializedName("advancereceived")
    @Expose
    public double advancereceived;
    @SerializedName("advancetaxamount")
    @Expose
    public double advancetaxamount;
    /*@SerializedName("inventoryLocation")
    @Expose
    public InventoryLocation inventoryLocation;*/
    @SerializedName("dsit")
    @Expose
    public Object dsit;
    @SerializedName("badDeptRelief")
    @Expose
    public String badDeptRelief;
    @SerializedName("customerAdvacePaymentId")
    @Expose
    public Object customerAdvacePaymentId;
    @SerializedName("totalDiscountAmount")
    @Expose
    public double totalDiscountAmount;
    @SerializedName("taxInvoice")
    @Expose
    public String taxInvoice;
    @SerializedName("soId")
    @Expose
    public Object soId;
    @SerializedName("customerPaymentId")
    @Expose
    public Object customerPaymentId;
    @SerializedName("advancetotalAmount")
    @Expose
    public double advancetotalAmount;
  /*  @SerializedName("useraccount_id")
    @Expose
    public UseraccountId useraccountId;*/
    @SerializedName("hiConnectStatus")
    @Expose
    public Object hiConnectStatus;
    @SerializedName("companyLocation")
    @Expose
    public Object companyLocation;
    @SerializedName("customerLocation")
    @Expose
    public String customerLocation;
    @SerializedName("actualWeightTotalAmt")
    @Expose
    public double actualWeightTotalAmt;
    @SerializedName("sellablrWeightTotalAmt")
    @Expose
    public double sellablrWeightTotalAmt;
    @SerializedName("differenceInWeight")
    @Expose
    public double differenceInWeight;
    @SerializedName("cessTaxAmt")
    @Expose
    public double cessTaxAmt;
    @SerializedName("siid")
    @Expose
    public long siid;
    @SerializedName("tcid")
    @Expose
    public Object tcid;
    @SerializedName("exchangeRateValue")
    @Expose
    public double exchangeRateValue;
    /*@SerializedName("exchangeRateId")
    @Expose
    public ExchangeRateId exchangeRateId;*/
    @SerializedName("memo")
    @Expose
    public Object memo;
    @SerializedName("salesTotalTaxAmt")
    @Expose
    public double salesTotalTaxAmt;
    @SerializedName("totalReceivable")
    @Expose
    public double totalReceivable;
    /*@SerializedName("currencyId")
    @Expose
    public CurrencyId currencyId;*/
    @SerializedName("sino")
    @Expose
    public String sino;
    @SerializedName("posting")
    @Expose
    public String posting;
    @SerializedName("shipTo")
    @Expose
    public Object shipTo;
    @SerializedName("freightCharge")
    @Expose
    public double freightCharge;
    @SerializedName("freightTaxPer")
    @Expose
    public Object freightTaxPer;
    @SerializedName("patientId")
    @Expose
    public Object patientId;
    @SerializedName("userId")
    @Expose
    public Object userId;
    @SerializedName("shipMethod")
    @Expose
    public Object shipMethod;
    @SerializedName("custPONo")
    @Expose
    public Object custPONo;
    @SerializedName("showReport")
    @Expose
    public String showReport;
    @SerializedName("totalAmount")
    @Expose
    public double totalAmount;
    @SerializedName("agentId")
    @Expose
    public Object agentId;
    @SerializedName("totalReceived")
    @Expose
    public double totalReceived;
    @SerializedName("arbalance")
    @Expose
    public double arbalance;
    @SerializedName("sidate")
    @Expose
    public String sidate;
    @SerializedName("sistatus")
    @Expose
    public String sistatus;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public CustomerId getCustomerId() {
        return customerId;
    }

    public void setCustomerId(CustomerId customerId) {
        this.customerId = customerId;
    }

    public Object getReferenceno() {
        return referenceno;
    }

    public void setReferenceno(Object referenceno) {
        this.referenceno = referenceno;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Object getProject() {
        return project;
    }

    public void setProject(Object project) {
        this.project = project;
    }

    public Object getTermsDesc() {
        return termsDesc;
    }

    public void setTermsDesc(Object termsDesc) {
        this.termsDesc = termsDesc;
    }

    public boolean isGstflag() {
        return gstflag;
    }

    public void setGstflag(boolean gstflag) {
        this.gstflag = gstflag;
    }

  /*  public CompanyId getCompanyId() {
        return companyId;
    }

    public void setCompanyId(CompanyId companyId) {
        this.companyId = companyId;
    }*/

    public double getTotalamountsalesretun() {
        return totalamountsalesretun;
    }

    public void setTotalamountsalesretun(double totalamountsalesretun) {
        this.totalamountsalesretun = totalamountsalesretun;
    }

    public Object getTotCommissionAmount() {
        return totCommissionAmount;
    }

    public void setTotCommissionAmount(Object totCommissionAmount) {
        this.totCommissionAmount = totCommissionAmount;
    }

    public double getAdvancereceived() {
        return advancereceived;
    }

    public void setAdvancereceived(double advancereceived) {
        this.advancereceived = advancereceived;
    }

    public double getAdvancetaxamount() {
        return advancetaxamount;
    }

    public void setAdvancetaxamount(double advancetaxamount) {
        this.advancetaxamount = advancetaxamount;
    }

   /* public InventoryLocation getInventoryLocation() {
        return inventoryLocation;
    }

    public void setInventoryLocation(InventoryLocation inventoryLocation) {
        this.inventoryLocation = inventoryLocation;
    }*/

    public Object getDsit() {
        return dsit;
    }

    public void setDsit(Object dsit) {
        this.dsit = dsit;
    }

    public String getBadDeptRelief() {
        return badDeptRelief;
    }

    public void setBadDeptRelief(String badDeptRelief) {
        this.badDeptRelief = badDeptRelief;
    }

    public Object getCustomerAdvacePaymentId() {
        return customerAdvacePaymentId;
    }

    public void setCustomerAdvacePaymentId(Object customerAdvacePaymentId) {
        this.customerAdvacePaymentId = customerAdvacePaymentId;
    }

    public double getTotalDiscountAmount() {
        return totalDiscountAmount;
    }

    public void setTotalDiscountAmount(double totalDiscountAmount) {
        this.totalDiscountAmount = totalDiscountAmount;
    }

    public String getTaxInvoice() {
        return taxInvoice;
    }

    public void setTaxInvoice(String taxInvoice) {
        this.taxInvoice = taxInvoice;
    }

    public Object getSoId() {
        return soId;
    }

    public void setSoId(Object soId) {
        this.soId = soId;
    }

    public Object getCustomerPaymentId() {
        return customerPaymentId;
    }

    public void setCustomerPaymentId(Object customerPaymentId) {
        this.customerPaymentId = customerPaymentId;
    }

    public double getAdvancetotalAmount() {
        return advancetotalAmount;
    }

    public void setAdvancetotalAmount(double advancetotalAmount) {
        this.advancetotalAmount = advancetotalAmount;
    }

  /*  public UseraccountId getUseraccountId() {
        return useraccountId;
    }

    public void setUseraccountId(UseraccountId useraccountId) {
        this.useraccountId = useraccountId;
    }*/

    public Object getHiConnectStatus() {
        return hiConnectStatus;
    }

    public void setHiConnectStatus(Object hiConnectStatus) {
        this.hiConnectStatus = hiConnectStatus;
    }

    public Object getCompanyLocation() {
        return companyLocation;
    }

    public void setCompanyLocation(Object companyLocation) {
        this.companyLocation = companyLocation;
    }

    public String getCustomerLocation() {
        return customerLocation;
    }

    public void setCustomerLocation(String customerLocation) {
        this.customerLocation = customerLocation;
    }

    public double getActualWeightTotalAmt() {
        return actualWeightTotalAmt;
    }

    public void setActualWeightTotalAmt(double actualWeightTotalAmt) {
        this.actualWeightTotalAmt = actualWeightTotalAmt;
    }

    public double getSellablrWeightTotalAmt() {
        return sellablrWeightTotalAmt;
    }

    public void setSellablrWeightTotalAmt(double sellablrWeightTotalAmt) {
        this.sellablrWeightTotalAmt = sellablrWeightTotalAmt;
    }

    public double getDifferenceInWeight() {
        return differenceInWeight;
    }

    public void setDifferenceInWeight(double differenceInWeight) {
        this.differenceInWeight = differenceInWeight;
    }

    public double getCessTaxAmt() {
        return cessTaxAmt;
    }

    public void setCessTaxAmt(double cessTaxAmt) {
        this.cessTaxAmt = cessTaxAmt;
    }

    public long getSiid() {
        return siid;
    }

    public void setSiid(long siid) {
        this.siid = siid;
    }

    public Object getTcid() {
        return tcid;
    }

    public void setTcid(Object tcid) {
        this.tcid = tcid;
    }

    public double getExchangeRateValue() {
        return exchangeRateValue;
    }

    public void setExchangeRateValue(double exchangeRateValue) {
        this.exchangeRateValue = exchangeRateValue;
    }

  /*  public ExchangeRateId getExchangeRateId() {
        return exchangeRateId;
    }

    public void setExchangeRateId(ExchangeRateId exchangeRateId) {
        this.exchangeRateId = exchangeRateId;
    }*/

    public Object getMemo() {
        return memo;
    }

    public void setMemo(Object memo) {
        this.memo = memo;
    }

    public double getSalesTotalTaxAmt() {
        return salesTotalTaxAmt;
    }

    public void setSalesTotalTaxAmt(double salesTotalTaxAmt) {
        this.salesTotalTaxAmt = salesTotalTaxAmt;
    }

    public double getTotalReceivable() {
        return totalReceivable;
    }

    public void setTotalReceivable(double totalReceivable) {
        this.totalReceivable = totalReceivable;
    }

    /*public CurrencyId getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(CurrencyId currencyId) {
        this.currencyId = currencyId;
    }
*/
    public String getSino() {
        return sino;
    }

    public void setSino(String sino) {
        this.sino = sino;
    }

    public String getPosting() {
        return posting;
    }

    public void setPosting(String posting) {
        this.posting = posting;
    }

    public Object getShipTo() {
        return shipTo;
    }

    public void setShipTo(Object shipTo) {
        this.shipTo = shipTo;
    }

    public double getFreightCharge() {
        return freightCharge;
    }

    public void setFreightCharge(double freightCharge) {
        this.freightCharge = freightCharge;
    }

    public Object getFreightTaxPer() {
        return freightTaxPer;
    }

    public void setFreightTaxPer(Object freightTaxPer) {
        this.freightTaxPer = freightTaxPer;
    }

    public Object getPatientId() {
        return patientId;
    }

    public void setPatientId(Object patientId) {
        this.patientId = patientId;
    }

    public Object getUserId() {
        return userId;
    }

    public void setUserId(Object userId) {
        this.userId = userId;
    }

    public Object getShipMethod() {
        return shipMethod;
    }

    public void setShipMethod(Object shipMethod) {
        this.shipMethod = shipMethod;
    }

    public Object getCustPONo() {
        return custPONo;
    }

    public void setCustPONo(Object custPONo) {
        this.custPONo = custPONo;
    }

    public String getShowReport() {
        return showReport;
    }

    public void setShowReport(String showReport) {
        this.showReport = showReport;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Object getAgentId() {
        return agentId;
    }

    public void setAgentId(Object agentId) {
        this.agentId = agentId;
    }

    public double getTotalReceived() {
        return totalReceived;
    }

    public void setTotalReceived(double totalReceived) {
        this.totalReceived = totalReceived;
    }

    public double getArbalance() {
        return arbalance;
    }

    public void setArbalance(double arbalance) {
        this.arbalance = arbalance;
    }

    public String getSidate() {
        return sidate;
    }

    public void setSidate(String sidate) {
        this.sidate = sidate;
    }

    public String getSistatus() {
        return sistatus;
    }

    public void setSistatus(String sistatus) {
        this.sistatus = sistatus;
    }
}
