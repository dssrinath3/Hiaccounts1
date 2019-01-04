package in.hiaccounts.hinext.sales.model.sales_notifications;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import in.hiaccounts.hinext.checkout.model.BankPayment;
import in.hiaccounts.hinext.checkout.model.CardPaymentList;
import in.hiaccounts.hinext.checkout.model.CashPayment;
import in.hiaccounts.hinext.checkout.model.CreditPayment;
import in.hiaccounts.hinext.checkout.model.MultiBankPaymentList;
import in.hiaccounts.hinext.checkout.model.MultiCashPaymentList;
import in.hiaccounts.hinext.checkout.model.MultiVoucherPayments;
import in.hiaccounts.hinext.checkout.model.VoucherPayment;
import in.hiaccounts.hinext.purchase.model.MultiPartialPaymentList;
import in.hiaccounts.hinext.sales.model.checkout.SelectedItemsList;
import in.hiaccounts.hinext.sales.model.checkout.UomConvertorList;
import in.hiaccounts.model.pos_invoice.TaxSummaryList;

public class Map implements Serializable{
    @SerializedName("cashPayment")
    @Expose
    public CashPayment cashPayment;
    @SerializedName("siNo")
    @Expose
    public String siNo;
    @SerializedName("transcationStatus")
    @Expose
    public String transcationStatus;
    @SerializedName("amtToBePaid")
    @Expose
    public Double amtToBePaid;
    @SerializedName("discountAmount")
    @Expose
    public Double discountAmount;
    @SerializedName("memo")
    @Expose
    public String memo;
    @SerializedName("taxSummaryList")
    @Expose
    public TaxSummaryList taxSummaryList;
    @SerializedName("suppLoc")
    @Expose
    public String suppLoc;
    @SerializedName("bankPayment")
    @Expose
    public BankPayment bankPayment;
    @SerializedName("locationSupplierName")
    @Expose
    public String locationSupplierName;
    @SerializedName("cessPer")
    @Expose
    public Double cessPer;
    @SerializedName("totalCashAmt")
    @Expose
    public Double totalCashAmt;
    @SerializedName("checkForHoldStock")
    @Expose
    public Boolean checkForHoldStock;
    @SerializedName("locationState")
    @Expose
    public String locationState;
    @SerializedName("exchangerateValue")
    @Expose
    public String exchangerateValue;
    @SerializedName("currencyId")
    @Expose
    public String currencyId;
    @SerializedName("taxAmt")
    @Expose
    public Double taxAmt;
    @SerializedName("cessTotalTaxAmt")
    @Expose
    public Double cessTotalTaxAmt;
    @SerializedName("taxType")
    @Expose
    public String taxType;
    @SerializedName("termsId")
    @Expose
    public Long termsId;
    @SerializedName("incrementAmt")
    @Expose
    public Double incrementAmt;
    @SerializedName("cmpyLoc")
    @Expose
    public String cmpyLoc;
    @SerializedName("from_reg")
    @Expose
    public String from_reg;
    @SerializedName("supplierInvNo")
    @Expose
    public String supplierInvNo;
    @SerializedName("frightCharges")
    @Expose
    public Double frightCharges;
    @SerializedName("piNo")
    @Expose
    public String piNo;
    @SerializedName("invoiceAmt")
    @Expose
    public Double invoiceAmt;
    @SerializedName("totalTenderedAmount")
    @Expose
    public Double totalTenderedAmount;
    @SerializedName("hiPosServiceCharge")
    @Expose
    public Double hiPosServiceCharge;
    @SerializedName("inventoryEmail")
    @Expose
    public String inventoryEmail;
    @SerializedName("dateOfInvoice")
    @Expose
    public String dateOfInvoice;
    @SerializedName("exchangeRateId")
    @Expose
    public Long exchangeRateId;
    @SerializedName("subscriptionType")
    @Expose
    public String subscriptionType;
    @SerializedName("totalCardPayment")
    @Expose
    public Double totalCardPayment;
    @SerializedName("typeDoc")
    @Expose
    public String typeDoc;
    @SerializedName("totalCheckOutamt")
    @Expose
    public Double totalCheckOutamt;
    @SerializedName("amountReturned")
    @Expose
    public Double amountReturned;
    @SerializedName("supplierState")
    @Expose
    public String supplierState;
    @SerializedName("amtIncTax")
    @Expose
    public Double amtIncTax;
    @SerializedName("totalActualWeight")
    @Expose
    public Double totalActualWeight;
    @SerializedName("dutyAmount")
    @Expose
    public Double dutyAmount;
    @SerializedName("type_flag")
    @Expose
    public String type_flag;
    @SerializedName("status")
    @Expose
    public Long status;
    @SerializedName("agentId")
    @Expose
    public Long agentId;
    @SerializedName("supplierId")
    @Expose
    public Long supplierId;
    @SerializedName("siid")
    @Expose
    public Long siid;
    @SerializedName("shippingDate")
    @Expose
    public String shippingDate;
    @SerializedName("tokenNo")
    @Expose
    public Double tokenNo;
    @SerializedName("cessAmt")
    @Expose
    public Double cessAmt;
    @SerializedName("totalCreditCardAmt")
    @Expose
    public Double totalCreditCardAmt;
    @SerializedName("paymentType")
    @Expose
    public String paymentType;
    @SerializedName("supplierGst")
    @Expose
    public String supplierGst;
    @SerializedName("totalTaxAmt")
    @Expose
    public Double totalTaxAmt;
    @SerializedName("inventoryContactNo")
    @Expose
    public String inventoryContactNo;
    @SerializedName("customerId")
    @Expose
    public Long customerId;
    @SerializedName("totalVoucherAmt")
    @Expose
    public Double totalVoucherAmt;
    @SerializedName("incPercent")
    @Expose
    public Double incPercent;
    @SerializedName("apBalance")
    @Expose
    public Double apBalance;
    @SerializedName("piid")
    @Expose
    public Long piid;
    @SerializedName("supplierName")
    @Expose
    public String supplierName;
    @SerializedName("totalCashPayment")
    @Expose
    public Double totalCashPayment;
    @SerializedName("printType")
    @Expose
    public String printType;
    @SerializedName("supplierEmail")
    @Expose
    public String supplierEmail;
    @SerializedName("voucherPayment")
    @Expose
    public VoucherPayment voucherPayment;
    @SerializedName("referenceNo")
    @Expose
    public String referenceNo;
    @SerializedName("invAmt")
    @Expose
    public Double invAmt;
    @SerializedName("multiPartialPaymentList")
    @Expose
    public MultiPartialPaymentList multiPartialPaymentList;
    @SerializedName("invokeRemaningQty")
    @Expose
    public Double invokeRemaningQty;
    @SerializedName("to_reg")
    @Expose
    public String to_reg;
    @SerializedName("balanceAmount")
    @Expose
    public Double balanceAmount;
    @SerializedName("creditPayment")
    @Expose
    public CreditPayment creditPayment;
    @SerializedName("totalServiceCharge")
    @Expose
    public Double totalServiceCharge;
    @SerializedName("itemCount")
    @Expose
    public Long itemCount;
    @SerializedName("piStatus")
    @Expose
    public String piStatus;
    @SerializedName("totalSellableWeight")
    @Expose
    public Double totalSellableWeight;
    @SerializedName("custNotiId")
    @Expose
    public String custNotiId;
    @SerializedName("locationGst")
    @Expose
    public String locationGst;
    @SerializedName("formNo")
    @Expose
    public String formNo;
    @SerializedName("totalVoucherPayment")
    @Expose
    public Double totalVoucherPayment;
    @SerializedName("selectedItemsList")
    @Expose
    public SelectedItemsList selectedItemsList;
    @SerializedName("multiCashPaymentList")
    @Expose
    public MultiCashPaymentList multiCashPaymentList;
    @SerializedName("totalCPDiscount")
    @Expose
    public Double totalCPDiscount;
    @SerializedName("totalCPAmountRefunded")
    @Expose
    public Double totalCPAmountRefunded;
    @SerializedName("totalCPAmountTendered")
    @Expose
    public Double totalCPAmountTendered;

    @SerializedName("taxPercentageSplit")
    @Expose
    public Double taxPercentageSplit;
    @SerializedName("taxId")
    @Expose
    public Long taxId;
    @SerializedName("taxPercent")
    @Expose
    public Double taxPercent;
    @SerializedName("amt")
    @Expose
    public Double amt;
    @SerializedName("taxAmount")
    @Expose
    public Double taxAmount;
    @SerializedName("taxName")
    @Expose
    public String taxName;
    @SerializedName("amount")
    @Expose
    public Double amount;
    @SerializedName("multiBankPaymentList")
    @Expose
    public MultiBankPaymentList multiBankPaymentList;
    @SerializedName("totalBPDiscount")
    @Expose
    public Double totalBPDiscount;
    @SerializedName("totalBPAmountRefunded")
    @Expose
    public Double totalBPAmountRefunded;
    @SerializedName("totalBPAmountTendered")
    @Expose
    public Double totalBPAmountTendered;
    @SerializedName("cardAmount")
    @Expose
    public Double cardAmount;
    @SerializedName("totalVPAfterAllDeductions")
    @Expose
    public Double totalVPAfterAllDeductions;
    @SerializedName("totalVPBeforDiscount")
    @Expose
    public Double totalVPBeforDiscount;
    @SerializedName("multiVoucherPayments")
    @Expose
    public MultiVoucherPayments multiVoucherPayments;
    @SerializedName("totalVPAmountRefunded")
    @Expose
    public Double totalVPAmountRefunded;
    @SerializedName("totalVPDiscount")
    @Expose
    public Double totalVPDiscount;
    @SerializedName("totalVPAmountTendered")
    @Expose
    public Double totalVPAmountTendered;
    @SerializedName("cardPaymentList")
    @Expose
    public CardPaymentList cardPaymentList;
    @SerializedName("totalCCPAmountTendered")
    @Expose
    public Double totalCCPAmountTendered;
    @SerializedName("totalCCPAmountRefunded")
    @Expose
    public Double totalCCPAmountRefunded;
    @SerializedName("totalCCPDiscount")
    @Expose
    public Double totalCCPDiscount;

    @SerializedName("receiveItemFlag")
    @Expose
    public Boolean receiveItemFlag;
    @SerializedName("ipTaxPer")
    @Expose
    public Double ipTaxPer;
    @SerializedName("itemCode")
    @Expose
    public String itemCode;
    @SerializedName("amtinclusivetax")
    @Expose
    public Double amtinclusivetax;
    @SerializedName("makingCharge")
    @Expose
    public Double makingCharge;
    @SerializedName("itemName")
    @Expose
    public String itemName;
    @SerializedName("uom")
    @Expose
    public String uom;
    @SerializedName("deliveredQuantity")
    @Expose
    public Double deliveredQuantity;
    @SerializedName("taxid")
    @Expose
    public Long taxid;
    @SerializedName("itemDescription")
    @Expose
    public String itemDescription;
    @SerializedName("unitPrice")
    @Expose
    public Double unitPrice;
    @SerializedName("cessTaxAmt")
    @Expose
    public Double cessTaxAmt;
    @SerializedName("hsnCode")
    @Expose
    public String hsnCode;
    @SerializedName("deliverdQuantity")
    @Expose
    public Double deliverdQuantity;
    @SerializedName("returnQty")
    @Expose
    public Double returnQty;
    @SerializedName("amtexclusivetax")
    @Expose
    public Double amtexclusivetax;
    @SerializedName("received")
    @Expose
    public Double received;
    @SerializedName("salesInvoiceDetailsId")
    @Expose
    public Long salesInvoiceDetailsId;
    @SerializedName("batchExpDate")
    @Expose
    public String batchExpDate;
    @SerializedName("convertedReturnQty")
    @Expose
    public Double convertedReturnQty;
    @SerializedName("itemId")
    @Expose
    public Long itemId;
    @SerializedName("pORemaningQty")
    @Expose
    public Double pORemaningQty;
    @SerializedName("qty")
    @Expose
    public Double qty;
    @SerializedName("posamtinclusivetax")
    @Expose
    public Double posamtinclusivetax;
    @SerializedName("fifoAmount")
    @Expose
    public Double fifoAmount;
    @SerializedName("itemCommisionConfigAmount")
    @Expose
    public Double itemCommisionConfigAmount;
    @SerializedName("taxamt")
    @Expose
    public Double taxamt;
    @SerializedName("useraccount_id")
    @Expose
    public String useraccount_id;
    @SerializedName("inclusiveJSON")
    @Expose
    public String inclusiveJSON;
    @SerializedName("discountAmt")
    @Expose
    public Double discountAmt;
    @SerializedName("batchManfDate")
    @Expose
    public String batchManfDate;
    @SerializedName("purchaseDate")
    @Expose
    public String purchaseDate;
    @SerializedName("purchaseInvoiceDetailID")
    @Expose
    public Long purchaseInvoiceDetailID;
    @SerializedName("serviceRemainingQty")
    @Expose
    public Double serviceRemainingQty;
    @SerializedName("actualWeight")
    @Expose
    public Double actualWeight;
    @SerializedName("itemAmount")
    @Expose
    public Double itemAmount;
    @SerializedName("QtySent")
    @Expose
    public Double qtySent;
    @SerializedName("locationId")
    @Expose
    public String locationId;
    @SerializedName("uomConvertorList")
    @Expose
    public UomConvertorList uomConvertorList;
    @SerializedName("itemCommisionAmount")
    @Expose
    public Double itemCommisionAmount;
    @SerializedName("remainingQty")
    @Expose
    public Double remainingQty;
    @SerializedName("qtytotalSent")
    @Expose
    public Double qtytotalSent;
    @SerializedName("inputTaxid")
    @Expose
    public Long inputTaxid;
    @SerializedName("convertedQuantity")
    @Expose
    public Double convertedQuantity;
    @SerializedName("serializableStatus")
    @Expose
    public String serializableStatus;
    @SerializedName("discountConfigAmt")
    @Expose
    public Double discountConfigAmt;
    @SerializedName("cess")
    @Expose
    public Double cess;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("taxpercent")
    @Expose
    public Double taxpercent;
    @SerializedName("posamtexclusivetax")
    @Expose
    public Double posamtexclusivetax;
    @SerializedName("itemStock")
    @Expose
    public Double itemStock;
    @SerializedName("cgstsgstsplitvalues")
    @Expose
    public Double cgstsgstsplitvalues;
    @SerializedName("discPercent")
    @Expose
    public Double discPercent;
    @SerializedName("uomName")
    @Expose
    public String uomName;
    @SerializedName("serviceDescription")
    @Expose
    public String serviceDescription;
    @SerializedName("uomConvertorId")
    @Expose
    public Long uomConvertorId;
    @SerializedName("uomValue")
    @Expose
    public String uomValue;
    @SerializedName("uomConvertedName")
    @Expose
    public String uomConvertedName;

    public CashPayment getCashPayment() {
        return cashPayment;
    }

    public void setCashPayment(CashPayment cashPayment) {
        this.cashPayment = cashPayment;
    }

    public String getSiNo() {
        return siNo;
    }

    public void setSiNo(String siNo) {
        this.siNo = siNo;
    }

    public String getTranscationStatus() {
        return transcationStatus;
    }

    public void setTranscationStatus(String transcationStatus) {
        this.transcationStatus = transcationStatus;
    }

    public Double getAmtToBePaid() {
        return amtToBePaid;
    }

    public void setAmtToBePaid(Double amtToBePaid) {
        this.amtToBePaid = amtToBePaid;
    }

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public TaxSummaryList getTaxSummaryList() {
        return taxSummaryList;
    }

    public void setTaxSummaryList(TaxSummaryList taxSummaryList) {
        this.taxSummaryList = taxSummaryList;
    }

    public String getSuppLoc() {
        return suppLoc;
    }

    public void setSuppLoc(String suppLoc) {
        this.suppLoc = suppLoc;
    }

    public BankPayment getBankPayment() {
        return bankPayment;
    }

    public void setBankPayment(BankPayment bankPayment) {
        this.bankPayment = bankPayment;
    }

    public String getLocationSupplierName() {
        return locationSupplierName;
    }

    public void setLocationSupplierName(String locationSupplierName) {
        this.locationSupplierName = locationSupplierName;
    }

    public Double getCessPer() {
        return cessPer;
    }

    public void setCessPer(Double cessPer) {
        this.cessPer = cessPer;
    }

    public Double getTotalCashAmt() {
        return totalCashAmt;
    }

    public void setTotalCashAmt(Double totalCashAmt) {
        this.totalCashAmt = totalCashAmt;
    }

    public Boolean getCheckForHoldStock() {
        return checkForHoldStock;
    }

    public void setCheckForHoldStock(Boolean checkForHoldStock) {
        this.checkForHoldStock = checkForHoldStock;
    }

    public String getLocationState() {
        return locationState;
    }

    public void setLocationState(String locationState) {
        this.locationState = locationState;
    }

    public String getExchangerateValue() {
        return exchangerateValue;
    }

    public void setExchangerateValue(String exchangerateValue) {
        this.exchangerateValue = exchangerateValue;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public Double getTaxAmt() {
        return taxAmt;
    }

    public void setTaxAmt(Double taxAmt) {
        this.taxAmt = taxAmt;
    }

    public Double getCessTotalTaxAmt() {
        return cessTotalTaxAmt;
    }

    public void setCessTotalTaxAmt(Double cessTotalTaxAmt) {
        this.cessTotalTaxAmt = cessTotalTaxAmt;
    }

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public Long getTermsId() {
        return termsId;
    }

    public void setTermsId(Long termsId) {
        this.termsId = termsId;
    }

    public Double getIncrementAmt() {
        return incrementAmt;
    }

    public void setIncrementAmt(Double incrementAmt) {
        this.incrementAmt = incrementAmt;
    }

    public String getCmpyLoc() {
        return cmpyLoc;
    }

    public void setCmpyLoc(String cmpyLoc) {
        this.cmpyLoc = cmpyLoc;
    }

    public String getFrom_reg() {
        return from_reg;
    }

    public void setFrom_reg(String from_reg) {
        this.from_reg = from_reg;
    }

    public String getSupplierInvNo() {
        return supplierInvNo;
    }

    public void setSupplierInvNo(String supplierInvNo) {
        this.supplierInvNo = supplierInvNo;
    }

    public Double getFrightCharges() {
        return frightCharges;
    }

    public void setFrightCharges(Double frightCharges) {
        this.frightCharges = frightCharges;
    }

    public String getPiNo() {
        return piNo;
    }

    public void setPiNo(String piNo) {
        this.piNo = piNo;
    }

    public Double getInvoiceAmt() {
        return invoiceAmt;
    }

    public void setInvoiceAmt(Double invoiceAmt) {
        this.invoiceAmt = invoiceAmt;
    }

    public Double getTotalTenderedAmount() {
        return totalTenderedAmount;
    }

    public void setTotalTenderedAmount(Double totalTenderedAmount) {
        this.totalTenderedAmount = totalTenderedAmount;
    }

    public Double getHiPosServiceCharge() {
        return hiPosServiceCharge;
    }

    public void setHiPosServiceCharge(Double hiPosServiceCharge) {
        this.hiPosServiceCharge = hiPosServiceCharge;
    }

    public String getInventoryEmail() {
        return inventoryEmail;
    }

    public void setInventoryEmail(String inventoryEmail) {
        this.inventoryEmail = inventoryEmail;
    }

    public String getDateOfInvoice() {
        return dateOfInvoice;
    }

    public void setDateOfInvoice(String dateOfInvoice) {
        this.dateOfInvoice = dateOfInvoice;
    }

    public Long getExchangeRateId() {
        return exchangeRateId;
    }

    public void setExchangeRateId(Long exchangeRateId) {
        this.exchangeRateId = exchangeRateId;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public Double getTotalCardPayment() {
        return totalCardPayment;
    }

    public void setTotalCardPayment(Double totalCardPayment) {
        this.totalCardPayment = totalCardPayment;
    }

    public String getTypeDoc() {
        return typeDoc;
    }

    public void setTypeDoc(String typeDoc) {
        this.typeDoc = typeDoc;
    }

    public Double getTotalCheckOutamt() {
        return totalCheckOutamt;
    }

    public void setTotalCheckOutamt(Double totalCheckOutamt) {
        this.totalCheckOutamt = totalCheckOutamt;
    }

    public Double getAmountReturned() {
        return amountReturned;
    }

    public void setAmountReturned(Double amountReturned) {
        this.amountReturned = amountReturned;
    }

    public String getSupplierState() {
        return supplierState;
    }

    public void setSupplierState(String supplierState) {
        this.supplierState = supplierState;
    }

    public Double getAmtIncTax() {
        return amtIncTax;
    }

    public void setAmtIncTax(Double amtIncTax) {
        this.amtIncTax = amtIncTax;
    }

    public Double getTotalActualWeight() {
        return totalActualWeight;
    }

    public void setTotalActualWeight(Double totalActualWeight) {
        this.totalActualWeight = totalActualWeight;
    }

    public Double getDutyAmount() {
        return dutyAmount;
    }

    public void setDutyAmount(Double dutyAmount) {
        this.dutyAmount = dutyAmount;
    }

    public String getType_flag() {
        return type_flag;
    }

    public void setType_flag(String type_flag) {
        this.type_flag = type_flag;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getSiid() {
        return siid;
    }

    public void setSiid(Long siid) {
        this.siid = siid;
    }

    public String getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(String shippingDate) {
        this.shippingDate = shippingDate;
    }

    public Double getTokenNo() {
        return tokenNo;
    }

    public void setTokenNo(Double tokenNo) {
        this.tokenNo = tokenNo;
    }

    public Double getCessAmt() {
        return cessAmt;
    }

    public void setCessAmt(Double cessAmt) {
        this.cessAmt = cessAmt;
    }

    public Double getTotalCreditCardAmt() {
        return totalCreditCardAmt;
    }

    public void setTotalCreditCardAmt(Double totalCreditCardAmt) {
        this.totalCreditCardAmt = totalCreditCardAmt;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getSupplierGst() {
        return supplierGst;
    }

    public void setSupplierGst(String supplierGst) {
        this.supplierGst = supplierGst;
    }

    public Double getTotalTaxAmt() {
        return totalTaxAmt;
    }

    public void setTotalTaxAmt(Double totalTaxAmt) {
        this.totalTaxAmt = totalTaxAmt;
    }

    public String getInventoryContactNo() {
        return inventoryContactNo;
    }

    public void setInventoryContactNo(String inventoryContactNo) {
        this.inventoryContactNo = inventoryContactNo;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Double getTotalVoucherAmt() {
        return totalVoucherAmt;
    }

    public void setTotalVoucherAmt(Double totalVoucherAmt) {
        this.totalVoucherAmt = totalVoucherAmt;
    }

    public Double getIncPercent() {
        return incPercent;
    }

    public void setIncPercent(Double incPercent) {
        this.incPercent = incPercent;
    }

    public Double getApBalance() {
        return apBalance;
    }

    public void setApBalance(Double apBalance) {
        this.apBalance = apBalance;
    }

    public Long getPiid() {
        return piid;
    }

    public void setPiid(Long piid) {
        this.piid = piid;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Double getTotalCashPayment() {
        return totalCashPayment;
    }

    public void setTotalCashPayment(Double totalCashPayment) {
        this.totalCashPayment = totalCashPayment;
    }

    public String getPrintType() {
        return printType;
    }

    public void setPrintType(String printType) {
        this.printType = printType;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    public VoucherPayment getVoucherPayment() {
        return voucherPayment;
    }

    public void setVoucherPayment(VoucherPayment voucherPayment) {
        this.voucherPayment = voucherPayment;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public Double getInvAmt() {
        return invAmt;
    }

    public void setInvAmt(Double invAmt) {
        this.invAmt = invAmt;
    }

    public MultiPartialPaymentList getMultiPartialPaymentList() {
        return multiPartialPaymentList;
    }

    public void setMultiPartialPaymentList(MultiPartialPaymentList multiPartialPaymentList) {
        this.multiPartialPaymentList = multiPartialPaymentList;
    }

    public Double getInvokeRemaningQty() {
        return invokeRemaningQty;
    }

    public void setInvokeRemaningQty(Double invokeRemaningQty) {
        this.invokeRemaningQty = invokeRemaningQty;
    }

    public String getTo_reg() {
        return to_reg;
    }

    public void setTo_reg(String to_reg) {
        this.to_reg = to_reg;
    }

    public Double getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(Double balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public CreditPayment getCreditPayment() {
        return creditPayment;
    }

    public void setCreditPayment(CreditPayment creditPayment) {
        this.creditPayment = creditPayment;
    }

    public Double getTotalServiceCharge() {
        return totalServiceCharge;
    }

    public void setTotalServiceCharge(Double totalServiceCharge) {
        this.totalServiceCharge = totalServiceCharge;
    }

    public Long getItemCount() {
        return itemCount;
    }

    public void setItemCount(Long itemCount) {
        this.itemCount = itemCount;
    }

    public String getPiStatus() {
        return piStatus;
    }

    public void setPiStatus(String piStatus) {
        this.piStatus = piStatus;
    }

    public Double getTotalSellableWeight() {
        return totalSellableWeight;
    }

    public void setTotalSellableWeight(Double totalSellableWeight) {
        this.totalSellableWeight = totalSellableWeight;
    }

    public String getCustNotiId() {
        return custNotiId;
    }

    public void setCustNotiId(String custNotiId) {
        this.custNotiId = custNotiId;
    }

    public String getLocationGst() {
        return locationGst;
    }

    public void setLocationGst(String locationGst) {
        this.locationGst = locationGst;
    }

    public String getFormNo() {
        return formNo;
    }

    public void setFormNo(String formNo) {
        this.formNo = formNo;
    }

    public Double getTotalVoucherPayment() {
        return totalVoucherPayment;
    }

    public void setTotalVoucherPayment(Double totalVoucherPayment) {
        this.totalVoucherPayment = totalVoucherPayment;
    }

    public SelectedItemsList getSelectedItemsList() {
        return selectedItemsList;
    }

    public void setSelectedItemsList(SelectedItemsList selectedItemsList) {
        this.selectedItemsList = selectedItemsList;
    }

    public MultiCashPaymentList getMultiCashPaymentList() {
        return multiCashPaymentList;
    }

    public void setMultiCashPaymentList(MultiCashPaymentList multiCashPaymentList) {
        this.multiCashPaymentList = multiCashPaymentList;
    }

    public Double getTotalCPDiscount() {
        return totalCPDiscount;
    }

    public void setTotalCPDiscount(Double totalCPDiscount) {
        this.totalCPDiscount = totalCPDiscount;
    }

    public Double getTotalCPAmountRefunded() {
        return totalCPAmountRefunded;
    }

    public void setTotalCPAmountRefunded(Double totalCPAmountRefunded) {
        this.totalCPAmountRefunded = totalCPAmountRefunded;
    }

    public Double getTotalCPAmountTendered() {
        return totalCPAmountTendered;
    }

    public void setTotalCPAmountTendered(Double totalCPAmountTendered) {
        this.totalCPAmountTendered = totalCPAmountTendered;
    }

    public Double getTaxPercentageSplit() {
        return taxPercentageSplit;
    }

    public void setTaxPercentageSplit(Double taxPercentageSplit) {
        this.taxPercentageSplit = taxPercentageSplit;
    }

    public Long getTaxId() {
        return taxId;
    }

    public void setTaxId(Long taxId) {
        this.taxId = taxId;
    }

    public Double getTaxPercent() {
        return taxPercent;
    }

    public void setTaxPercent(Double taxPercent) {
        this.taxPercent = taxPercent;
    }

    public Double getAmt() {
        return amt;
    }

    public void setAmt(Double amt) {
        this.amt = amt;
    }

    public Double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(Double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public String getTaxName() {
        return taxName;
    }

    public void setTaxName(String taxName) {
        this.taxName = taxName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public MultiBankPaymentList getMultiBankPaymentList() {
        return multiBankPaymentList;
    }

    public void setMultiBankPaymentList(MultiBankPaymentList multiBankPaymentList) {
        this.multiBankPaymentList = multiBankPaymentList;
    }

    public Double getTotalBPDiscount() {
        return totalBPDiscount;
    }

    public void setTotalBPDiscount(Double totalBPDiscount) {
        this.totalBPDiscount = totalBPDiscount;
    }

    public Double getTotalBPAmountRefunded() {
        return totalBPAmountRefunded;
    }

    public void setTotalBPAmountRefunded(Double totalBPAmountRefunded) {
        this.totalBPAmountRefunded = totalBPAmountRefunded;
    }

    public Double getTotalBPAmountTendered() {
        return totalBPAmountTendered;
    }

    public void setTotalBPAmountTendered(Double totalBPAmountTendered) {
        this.totalBPAmountTendered = totalBPAmountTendered;
    }

    public Double getCardAmount() {
        return cardAmount;
    }

    public void setCardAmount(Double cardAmount) {
        this.cardAmount = cardAmount;
    }

    public Double getTotalVPAfterAllDeductions() {
        return totalVPAfterAllDeductions;
    }

    public void setTotalVPAfterAllDeductions(Double totalVPAfterAllDeductions) {
        this.totalVPAfterAllDeductions = totalVPAfterAllDeductions;
    }

    public Double getTotalVPBeforDiscount() {
        return totalVPBeforDiscount;
    }

    public void setTotalVPBeforDiscount(Double totalVPBeforDiscount) {
        this.totalVPBeforDiscount = totalVPBeforDiscount;
    }

    public MultiVoucherPayments getMultiVoucherPayments() {
        return multiVoucherPayments;
    }

    public void setMultiVoucherPayments(MultiVoucherPayments multiVoucherPayments) {
        this.multiVoucherPayments = multiVoucherPayments;
    }

    public Double getTotalVPAmountRefunded() {
        return totalVPAmountRefunded;
    }

    public void setTotalVPAmountRefunded(Double totalVPAmountRefunded) {
        this.totalVPAmountRefunded = totalVPAmountRefunded;
    }

    public Double getTotalVPDiscount() {
        return totalVPDiscount;
    }

    public void setTotalVPDiscount(Double totalVPDiscount) {
        this.totalVPDiscount = totalVPDiscount;
    }

    public Double getTotalVPAmountTendered() {
        return totalVPAmountTendered;
    }

    public void setTotalVPAmountTendered(Double totalVPAmountTendered) {
        this.totalVPAmountTendered = totalVPAmountTendered;
    }

    public CardPaymentList getCardPaymentList() {
        return cardPaymentList;
    }

    public void setCardPaymentList(CardPaymentList cardPaymentList) {
        this.cardPaymentList = cardPaymentList;
    }

    public Double getTotalCCPAmountTendered() {
        return totalCCPAmountTendered;
    }

    public void setTotalCCPAmountTendered(Double totalCCPAmountTendered) {
        this.totalCCPAmountTendered = totalCCPAmountTendered;
    }

    public Double getTotalCCPAmountRefunded() {
        return totalCCPAmountRefunded;
    }

    public void setTotalCCPAmountRefunded(Double totalCCPAmountRefunded) {
        this.totalCCPAmountRefunded = totalCCPAmountRefunded;
    }

    public Double getTotalCCPDiscount() {
        return totalCCPDiscount;
    }

    public void setTotalCCPDiscount(Double totalCCPDiscount) {
        this.totalCCPDiscount = totalCCPDiscount;
    }

    public Boolean getReceiveItemFlag() {
        return receiveItemFlag;
    }

    public void setReceiveItemFlag(Boolean receiveItemFlag) {
        this.receiveItemFlag = receiveItemFlag;
    }

    public Double getIpTaxPer() {
        return ipTaxPer;
    }

    public void setIpTaxPer(Double ipTaxPer) {
        this.ipTaxPer = ipTaxPer;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public Double getAmtinclusivetax() {
        return amtinclusivetax;
    }

    public void setAmtinclusivetax(Double amtinclusivetax) {
        this.amtinclusivetax = amtinclusivetax;
    }

    public Double getMakingCharge() {
        return makingCharge;
    }

    public void setMakingCharge(Double makingCharge) {
        this.makingCharge = makingCharge;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public Double getDeliveredQuantity() {
        return deliveredQuantity;
    }

    public void setDeliveredQuantity(Double deliveredQuantity) {
        this.deliveredQuantity = deliveredQuantity;
    }

    public Long getTaxid() {
        return taxid;
    }

    public void setTaxid(Long taxid) {
        this.taxid = taxid;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getCessTaxAmt() {
        return cessTaxAmt;
    }

    public void setCessTaxAmt(Double cessTaxAmt) {
        this.cessTaxAmt = cessTaxAmt;
    }

    public String getHsnCode() {
        return hsnCode;
    }

    public void setHsnCode(String hsnCode) {
        this.hsnCode = hsnCode;
    }

    public Double getDeliverdQuantity() {
        return deliverdQuantity;
    }

    public void setDeliverdQuantity(Double deliverdQuantity) {
        this.deliverdQuantity = deliverdQuantity;
    }

    public Double getReturnQty() {
        return returnQty;
    }

    public void setReturnQty(Double returnQty) {
        this.returnQty = returnQty;
    }

    public Double getAmtexclusivetax() {
        return amtexclusivetax;
    }

    public void setAmtexclusivetax(Double amtexclusivetax) {
        this.amtexclusivetax = amtexclusivetax;
    }

    public Double getReceived() {
        return received;
    }

    public void setReceived(Double received) {
        this.received = received;
    }

    public Long getSalesInvoiceDetailsId() {
        return salesInvoiceDetailsId;
    }

    public void setSalesInvoiceDetailsId(Long salesInvoiceDetailsId) {
        this.salesInvoiceDetailsId = salesInvoiceDetailsId;
    }

    public String getBatchExpDate() {
        return batchExpDate;
    }

    public void setBatchExpDate(String batchExpDate) {
        this.batchExpDate = batchExpDate;
    }

    public Double getConvertedReturnQty() {
        return convertedReturnQty;
    }

    public void setConvertedReturnQty(Double convertedReturnQty) {
        this.convertedReturnQty = convertedReturnQty;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Double getpORemaningQty() {
        return pORemaningQty;
    }

    public void setpORemaningQty(Double pORemaningQty) {
        this.pORemaningQty = pORemaningQty;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public Double getPosamtinclusivetax() {
        return posamtinclusivetax;
    }

    public void setPosamtinclusivetax(Double posamtinclusivetax) {
        this.posamtinclusivetax = posamtinclusivetax;
    }

    public Double getFifoAmount() {
        return fifoAmount;
    }

    public void setFifoAmount(Double fifoAmount) {
        this.fifoAmount = fifoAmount;
    }

    public Double getItemCommisionConfigAmount() {
        return itemCommisionConfigAmount;
    }

    public void setItemCommisionConfigAmount(Double itemCommisionConfigAmount) {
        this.itemCommisionConfigAmount = itemCommisionConfigAmount;
    }

    public Double getTaxamt() {
        return taxamt;
    }

    public void setTaxamt(Double taxamt) {
        this.taxamt = taxamt;
    }

    public String getUseraccount_id() {
        return useraccount_id;
    }

    public void setUseraccount_id(String useraccount_id) {
        this.useraccount_id = useraccount_id;
    }

    public String getInclusiveJSON() {
        return inclusiveJSON;
    }

    public void setInclusiveJSON(String inclusiveJSON) {
        this.inclusiveJSON = inclusiveJSON;
    }

    public Double getDiscountAmt() {
        return discountAmt;
    }

    public void setDiscountAmt(Double discountAmt) {
        this.discountAmt = discountAmt;
    }

    public String getBatchManfDate() {
        return batchManfDate;
    }

    public void setBatchManfDate(String batchManfDate) {
        this.batchManfDate = batchManfDate;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Long getPurchaseInvoiceDetailID() {
        return purchaseInvoiceDetailID;
    }

    public void setPurchaseInvoiceDetailID(Long purchaseInvoiceDetailID) {
        this.purchaseInvoiceDetailID = purchaseInvoiceDetailID;
    }

    public Double getServiceRemainingQty() {
        return serviceRemainingQty;
    }

    public void setServiceRemainingQty(Double serviceRemainingQty) {
        this.serviceRemainingQty = serviceRemainingQty;
    }

    public Double getActualWeight() {
        return actualWeight;
    }

    public void setActualWeight(Double actualWeight) {
        this.actualWeight = actualWeight;
    }

    public Double getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(Double itemAmount) {
        this.itemAmount = itemAmount;
    }

    public Double getQtySent() {
        return qtySent;
    }

    public void setQtySent(Double qtySent) {
        this.qtySent = qtySent;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public UomConvertorList getUomConvertorList() {
        return uomConvertorList;
    }

    public void setUomConvertorList(UomConvertorList uomConvertorList) {
        this.uomConvertorList = uomConvertorList;
    }

    public Double getItemCommisionAmount() {
        return itemCommisionAmount;
    }

    public void setItemCommisionAmount(Double itemCommisionAmount) {
        this.itemCommisionAmount = itemCommisionAmount;
    }

    public Double getRemainingQty() {
        return remainingQty;
    }

    public void setRemainingQty(Double remainingQty) {
        this.remainingQty = remainingQty;
    }

    public Double getQtytotalSent() {
        return qtytotalSent;
    }

    public void setQtytotalSent(Double qtytotalSent) {
        this.qtytotalSent = qtytotalSent;
    }

    public Long getInputTaxid() {
        return inputTaxid;
    }

    public void setInputTaxid(Long inputTaxid) {
        this.inputTaxid = inputTaxid;
    }

    public Double getConvertedQuantity() {
        return convertedQuantity;
    }

    public void setConvertedQuantity(Double convertedQuantity) {
        this.convertedQuantity = convertedQuantity;
    }

    public String getSerializableStatus() {
        return serializableStatus;
    }

    public void setSerializableStatus(String serializableStatus) {
        this.serializableStatus = serializableStatus;
    }

    public Double getDiscountConfigAmt() {
        return discountConfigAmt;
    }

    public void setDiscountConfigAmt(Double discountConfigAmt) {
        this.discountConfigAmt = discountConfigAmt;
    }

    public Double getCess() {
        return cess;
    }

    public void setCess(Double cess) {
        this.cess = cess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Double getTaxpercent() {
        return taxpercent;
    }

    public void setTaxpercent(Double taxpercent) {
        this.taxpercent = taxpercent;
    }

    public Double getPosamtexclusivetax() {
        return posamtexclusivetax;
    }

    public void setPosamtexclusivetax(Double posamtexclusivetax) {
        this.posamtexclusivetax = posamtexclusivetax;
    }

    public Double getItemStock() {
        return itemStock;
    }

    public void setItemStock(Double itemStock) {
        this.itemStock = itemStock;
    }

    public Double getCgstsgstsplitvalues() {
        return cgstsgstsplitvalues;
    }

    public void setCgstsgstsplitvalues(Double cgstsgstsplitvalues) {
        this.cgstsgstsplitvalues = cgstsgstsplitvalues;
    }

    public Double getDiscPercent() {
        return discPercent;
    }

    public void setDiscPercent(Double discPercent) {
        this.discPercent = discPercent;
    }

    public String getUomName() {
        return uomName;
    }

    public void setUomName(String uomName) {
        this.uomName = uomName;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public Long getUomConvertorId() {
        return uomConvertorId;
    }

    public void setUomConvertorId(Long uomConvertorId) {
        this.uomConvertorId = uomConvertorId;
    }

    public String getUomValue() {
        return uomValue;
    }

    public void setUomValue(String uomValue) {
        this.uomValue = uomValue;
    }

    public String getUomConvertedName() {
        return uomConvertedName;
    }

    public void setUomConvertedName(String uomConvertedName) {
        this.uomConvertedName = uomConvertedName;
    }
}
