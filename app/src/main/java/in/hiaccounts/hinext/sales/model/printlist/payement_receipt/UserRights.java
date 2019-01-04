
package in.hiaccounts.hinext.sales.model.printlist.payement_receipt;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserRights implements Serializable {

    @SerializedName("id")
    @Expose
    public long id;
    @SerializedName("supplierMaintenanace")
    @Expose
    public String supplierMaintenanace;
    @SerializedName("purchaseOrder")
    @Expose
    public String purchaseOrder;
    @SerializedName("receiveItem")
    @Expose
    public String receiveItem;
    @SerializedName("purchaseDebitNote")
    @Expose
    public String purchaseDebitNote;
    @SerializedName("purchaseCreditNote")
    @Expose
    public String purchaseCreditNote;
    @SerializedName("purchaseInvoice")
    @Expose
    public String purchaseInvoice;
    @SerializedName("directPurchaseInvoice")
    @Expose
    public String directPurchaseInvoice;
    @SerializedName("deemedSuppliesSalesInvoice")
    @Expose
    public String deemedSuppliesSalesInvoice;
    @SerializedName("selfBilledInvoice")
    @Expose
    public String selfBilledInvoice;
    @SerializedName("importServiceInvoice")
    @Expose
    public String importServiceInvoice;
    @SerializedName("importGoodsAdvanceInvoice")
    @Expose
    public String importGoodsAdvanceInvoice;
    @SerializedName("supplierPayment")
    @Expose
    public String supplierPayment;
    @SerializedName("customerMaintenance")
    @Expose
    public String customerMaintenance;
    @SerializedName("salesQuotation")
    @Expose
    public String salesQuotation;
    @SerializedName("salesOrder")
    @Expose
    public String salesOrder;
    @SerializedName("deliveryorder")
    @Expose
    public String deliveryorder;
    @SerializedName("salesInvoice")
    @Expose
    public String salesInvoice;
    @SerializedName("directSalesInvoice")
    @Expose
    public String directSalesInvoice;
    @SerializedName("proformaSalesInvoice")
    @Expose
    public String proformaSalesInvoice;
    @SerializedName("salesDebitNote")
    @Expose
    public String salesDebitNote;
    @SerializedName("salesCreditNote")
    @Expose
    public String salesCreditNote;
    @SerializedName("receivePayment")
    @Expose
    public String receivePayment;
    @SerializedName("locationTransfer")
    @Expose
    public String locationTransfer;
    @SerializedName("itemAdjustments")
    @Expose
    public String itemAdjustments;
    @SerializedName("itemCountAdjust")
    @Expose
    public String itemCountAdjust;
    @SerializedName("salesPricing")
    @Expose
    public String salesPricing;
    @SerializedName("purchasePricing")
    @Expose
    public String purchasePricing;
    @SerializedName("othePaymentWithItem")
    @Expose
    public String othePaymentWithItem;
    @SerializedName("otherPaymentWithoutItem")
    @Expose
    public String otherPaymentWithoutItem;
    @SerializedName("otherReceiptWithItem")
    @Expose
    public String otherReceiptWithItem;
    @SerializedName("otherReceiptWithoutItem")
    @Expose
    public String otherReceiptWithoutItem;
    @SerializedName("journalEntry")
    @Expose
    public String journalEntry;
    @SerializedName("gsttype")
    @Expose
    public String gsttype;
    @SerializedName("salesGSTListing")
    @Expose
    public String salesGSTListing;
    @SerializedName("purchaseGSTListing")
    @Expose
    public String purchaseGSTListing;
    @SerializedName("otherListing")
    @Expose
    public String otherListing;
    @SerializedName("salesAdjustment")
    @Expose
    public String salesAdjustment;
    @SerializedName("purchaseAdjustment")
    @Expose
    public String purchaseAdjustment;
    @SerializedName("badDebtRelief")
    @Expose
    public String badDebtRelief;
    @SerializedName("changeOfUse")
    @Expose
    public String changeOfUse;
    @SerializedName("changeOfAccountingBasis")
    @Expose
    public String changeOfAccountingBasis;
    @SerializedName("partialExemption")
    @Expose
    public String partialExemption;
    @SerializedName("anualAdjustment")
    @Expose
    public String anualAdjustment;
    @SerializedName("zakatMaintenance")
    @Expose
    public String zakatMaintenance;
    @SerializedName("openingBalance")
    @Expose
    public String openingBalance;
    @SerializedName("openingBalancePurchaseInvoice")
    @Expose
    public String openingBalancePurchaseInvoice;
    @SerializedName("openingBalanceSalesInvoice")
    @Expose
    public String openingBalanceSalesInvoice;
    @SerializedName("openingBalanceOfInventory")
    @Expose
    public String openingBalanceOfInventory;
    @SerializedName("lastYearFigures")
    @Expose
    public String lastYearFigures;
    @SerializedName("monthEndProcessing")
    @Expose
    public String monthEndProcessing;
    @SerializedName("yearEndProcessing")
    @Expose
    public String yearEndProcessing;
    @SerializedName("databaseRestore")
    @Expose
    public String databaseRestore;
    @SerializedName("databaseBackup")
    @Expose
    public String databaseBackup;
    @SerializedName("recurringTransaction")
    @Expose
    public String recurringTransaction;
    @SerializedName("employee")
    @Expose
    public String employee;
    @SerializedName("country")
    @Expose
    public String country;
    @SerializedName("currency")
    @Expose
    public String currency;
    @SerializedName("unitMeasure")
    @Expose
    public String unitMeasure;
    @SerializedName("exchangeRate")
    @Expose
    public String exchangeRate;
    @SerializedName("taxType")
    @Expose
    public String taxType;
    @SerializedName("tax")
    @Expose
    public String tax;
    @SerializedName("itemCategories")
    @Expose
    public String itemCategories;
    @SerializedName("itemType")
    @Expose
    public String itemType;
    @SerializedName("item")
    @Expose
    public String item;
    @SerializedName("shippingMethod")
    @Expose
    public String shippingMethod;
    @SerializedName("paymentMethod")
    @Expose
    public String paymentMethod;
    @SerializedName("termsandcondition")
    @Expose
    public String termsandcondition;
    @SerializedName("productMerger")
    @Expose
    public String productMerger;
    @SerializedName("agent")
    @Expose
    public String agent;
    @SerializedName("languages")
    @Expose
    public String languages;
    @SerializedName("inventoryLocation")
    @Expose
    public String inventoryLocation;
    @SerializedName("inventoryMovementType")
    @Expose
    public String inventoryMovementType;
    @SerializedName("project")
    @Expose
    public String project;
    @SerializedName("introduction")
    @Expose
    public String introduction;
    @SerializedName("companyInformation")
    @Expose
    public String companyInformation;
    @SerializedName("serviceCharge")
    @Expose
    public String serviceCharge;
    @SerializedName("companyProfile")
    @Expose
    public String companyProfile;
    @SerializedName("iaccountsalert")
    @Expose
    public String iaccountsalert;
    @SerializedName("userAccountSetup")
    @Expose
    public String userAccountSetup;
    @SerializedName("accountGroup")
    @Expose
    public String accountGroup;
    @SerializedName("createChartAccount")
    @Expose
    public String createChartAccount;
    @SerializedName("accountTypeDescription")
    @Expose
    public String accountTypeDescription;
    @SerializedName("parametersetup")
    @Expose
    public String parametersetup;
    @SerializedName("formsetup")
    @Expose
    public String formsetup;
    @SerializedName("auditTrail")
    @Expose
    public String auditTrail;
    @SerializedName("shortCut")
    @Expose
    public String shortCut;
    @SerializedName("bank")
    @Expose
    public String bank;
    @SerializedName("hipos")
    @Expose
    public String hipos;
    @SerializedName("hiposOptions")
    @Expose
    public String hiposOptions;
    @SerializedName("msicCode")
    @Expose
    public String msicCode;
    @SerializedName("emailServer")
    @Expose
    public String emailServer;
    @SerializedName("selfBuildApprovalDate")
    @Expose
    public String selfBuildApprovalDate;
    @SerializedName("serviceReceiveItem")
    @Expose
    public String serviceReceiveItem;
    @SerializedName("serviceDeleiveryItem")
    @Expose
    public String serviceDeleiveryItem;
    @SerializedName("serviceReplaceItem")
    @Expose
    public Object serviceReplaceItem;
    @SerializedName("commisionAgentListing")
    @Expose
    public String commisionAgentListing;
    @SerializedName("commisionpatientListing")
    @Expose
    public String commisionpatientListing;
    @SerializedName("goldSalesInvoice")
    @Expose
    public String goldSalesInvoice;
    @SerializedName("pharmaSalesInvoice")
    @Expose
    public String pharmaSalesInvoice;
    @SerializedName("itemBrand")
    @Expose
    public String itemBrand;
    @SerializedName("serialItem")
    @Expose
    public String serialItem;
    @SerializedName("posSalesInvoice")
    @Expose
    public String posSalesInvoice;
    @SerializedName("zakathRedistributionForm")
    @Expose
    public String zakathRedistributionForm;
    @SerializedName("gafFileExport")
    @Expose
    public String gafFileExport;
    @SerializedName("gafPosExport")
    @Expose
    public String gafPosExport;
    @SerializedName("posConfiguration")
    @Expose
    public String posConfiguration;
    @SerializedName("tableConfiguration")
    @Expose
    public String tableConfiguration;
    @SerializedName("serviceCharges")
    @Expose
    public Object serviceCharges;
    @SerializedName("restareantpos")
    @Expose
    public Object restareantpos;
    @SerializedName("retailpos")
    @Expose
    public Object retailpos;
    @SerializedName("budget")
    @Expose
    public String budget;
    @SerializedName("alerts")
    @Expose
    public Object alerts;
    @SerializedName("reportsReceiveItemListing")
    @Expose
    public String reportsReceiveItemListing;
    @SerializedName("reportsSupplierStatementListing")
    @Expose
    public String reportsSupplierStatementListing;
    @SerializedName("reportsPurchaseOrderListing")
    @Expose
    public String reportsPurchaseOrderListing;
    @SerializedName("reportsSupplierListing")
    @Expose
    public String reportsSupplierListing;
    @SerializedName("reportsPurchaseInvoiceListing")
    @Expose
    public String reportsPurchaseInvoiceListing;
    @SerializedName("reportsPurchaseReturnListing")
    @Expose
    public String reportsPurchaseReturnListing;
    @SerializedName("reportsSupplierPayment")
    @Expose
    public String reportsSupplierPayment;
    @SerializedName("reportsCreditorsAgeing")
    @Expose
    public String reportsCreditorsAgeing;
    @SerializedName("reportsPurchaseAdjustment")
    @Expose
    public String reportsPurchaseAdjustment;
    @SerializedName("reportsCancelledPurchaseInvoice")
    @Expose
    public String reportsCancelledPurchaseInvoice;
    @SerializedName("reportsSalesQuotationListing")
    @Expose
    public String reportsSalesQuotationListing;
    @SerializedName("reportsSalesOrderListing")
    @Expose
    public String reportsSalesOrderListing;
    @SerializedName("reportsSalesInvoiceListing")
    @Expose
    public String reportsSalesInvoiceListing;
    @SerializedName("reportsSalesReturnListing")
    @Expose
    public String reportsSalesReturnListing;
    @SerializedName("reportsCustomerStatement")
    @Expose
    public String reportsCustomerStatement;
    @SerializedName("reportsProductDeliveryListing")
    @Expose
    public String reportsProductDeliveryListing;
    @SerializedName("reportsPaymentReport")
    @Expose
    public String reportsPaymentReport;
    @SerializedName("reportsPriceListing")
    @Expose
    public String reportsPriceListing;
    @SerializedName("reportsDebtorsAgening")
    @Expose
    public String reportsDebtorsAgening;
    @SerializedName("reportsAgentListing")
    @Expose
    public String reportsAgentListing;
    @SerializedName("reportsCustomerListing")
    @Expose
    public String reportsCustomerListing;
    @SerializedName("reportsPatientListing")
    @Expose
    public String reportsPatientListing;
    @SerializedName("reportsCustomerPayment")
    @Expose
    public String reportsCustomerPayment;
    @SerializedName("reportsSalesAdjustment")
    @Expose
    public String reportsSalesAdjustment;
    @SerializedName("reportsCancelledSalesInvoice")
    @Expose
    public String reportsCancelledSalesInvoice;
    @SerializedName("reportsGRNValuationReport")
    @Expose
    public String reportsGRNValuationReport;
    @SerializedName("reportsInventoryPlaningReport")
    @Expose
    public String reportsInventoryPlaningReport;
    @SerializedName("reportsInventoryValuationReport")
    @Expose
    public String reportsInventoryValuationReport;
    @SerializedName("reportsInventorySalesReport")
    @Expose
    public String reportsInventorySalesReport;
    @SerializedName("reportsInventoryNonMovementReport")
    @Expose
    public String reportsInventoryNonMovementReport;
    @SerializedName("reportsInventoryMovementReport")
    @Expose
    public String reportsInventoryMovementReport;
    @SerializedName("reportsInventoryListReport")
    @Expose
    public String reportsInventoryListReport;
    @SerializedName("reportsOutstandingGRNReport")
    @Expose
    public String reportsOutstandingGRNReport;
    @SerializedName("reportsStockCheckReport")
    @Expose
    public String reportsStockCheckReport;
    @SerializedName("reportsAccountBalance")
    @Expose
    public String reportsAccountBalance;
    @SerializedName("reportsIncomeExpenseTrend")
    @Expose
    public String reportsIncomeExpenseTrend;
    @SerializedName("reportsCustomerReceiptReport")
    @Expose
    public String reportsCustomerReceiptReport;
    @SerializedName("reportsSupplierPaymentReport")
    @Expose
    public String reportsSupplierPaymentReport;
    @SerializedName("reportsIncomeComparisionReport")
    @Expose
    public String reportsIncomeComparisionReport;
    @SerializedName("reportsExpenseComparisionReport")
    @Expose
    public String reportsExpenseComparisionReport;
    @SerializedName("reportsCustomerInvoicePayment")
    @Expose
    public String reportsCustomerInvoicePayment;
    @SerializedName("reportsSupplierInvoicePayment")
    @Expose
    public String reportsSupplierInvoicePayment;
    @SerializedName("reportsTopVendors")
    @Expose
    public String reportsTopVendors;
    @SerializedName("reportsTopItems")
    @Expose
    public String reportsTopItems;
    @SerializedName("reportsTopCustomers")
    @Expose
    public String reportsTopCustomers;
    @SerializedName("reportsStatementOfComprehensiveIncome")
    @Expose
    public String reportsStatementOfComprehensiveIncome;
    @SerializedName("reportsStatementOfFinancialPosition")
    @Expose
    public String reportsStatementOfFinancialPosition;
    @SerializedName("reportsTrialBalance")
    @Expose
    public String reportsTrialBalance;
    @SerializedName("reportsGeneralLedger")
    @Expose
    public String reportsGeneralLedger;
    @SerializedName("reportsViewLedger")
    @Expose
    public String reportsViewLedger;
    @SerializedName("reportsGeneralJournal")
    @Expose
    public String reportsGeneralJournal;
    @SerializedName("reportsStatementOfAccount")
    @Expose
    public String reportsStatementOfAccount;
    @SerializedName("reportsCheque")
    @Expose
    public String reportsCheque;
    @SerializedName("reportsRatios")
    @Expose
    public String reportsRatios;
    @SerializedName("locationId")
    @Expose
    public Object locationId;
    @SerializedName("useraccount_id")
    @Expose
    public Object useraccountId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSupplierMaintenanace() {
        return supplierMaintenanace;
    }

    public void setSupplierMaintenanace(String supplierMaintenanace) {
        this.supplierMaintenanace = supplierMaintenanace;
    }

    public String getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(String purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public String getReceiveItem() {
        return receiveItem;
    }

    public void setReceiveItem(String receiveItem) {
        this.receiveItem = receiveItem;
    }

    public String getPurchaseDebitNote() {
        return purchaseDebitNote;
    }

    public void setPurchaseDebitNote(String purchaseDebitNote) {
        this.purchaseDebitNote = purchaseDebitNote;
    }

    public String getPurchaseCreditNote() {
        return purchaseCreditNote;
    }

    public void setPurchaseCreditNote(String purchaseCreditNote) {
        this.purchaseCreditNote = purchaseCreditNote;
    }

    public String getPurchaseInvoice() {
        return purchaseInvoice;
    }

    public void setPurchaseInvoice(String purchaseInvoice) {
        this.purchaseInvoice = purchaseInvoice;
    }

    public String getDirectPurchaseInvoice() {
        return directPurchaseInvoice;
    }

    public void setDirectPurchaseInvoice(String directPurchaseInvoice) {
        this.directPurchaseInvoice = directPurchaseInvoice;
    }

    public String getDeemedSuppliesSalesInvoice() {
        return deemedSuppliesSalesInvoice;
    }

    public void setDeemedSuppliesSalesInvoice(String deemedSuppliesSalesInvoice) {
        this.deemedSuppliesSalesInvoice = deemedSuppliesSalesInvoice;
    }

    public String getSelfBilledInvoice() {
        return selfBilledInvoice;
    }

    public void setSelfBilledInvoice(String selfBilledInvoice) {
        this.selfBilledInvoice = selfBilledInvoice;
    }

    public String getImportServiceInvoice() {
        return importServiceInvoice;
    }

    public void setImportServiceInvoice(String importServiceInvoice) {
        this.importServiceInvoice = importServiceInvoice;
    }

    public String getImportGoodsAdvanceInvoice() {
        return importGoodsAdvanceInvoice;
    }

    public void setImportGoodsAdvanceInvoice(String importGoodsAdvanceInvoice) {
        this.importGoodsAdvanceInvoice = importGoodsAdvanceInvoice;
    }

    public String getSupplierPayment() {
        return supplierPayment;
    }

    public void setSupplierPayment(String supplierPayment) {
        this.supplierPayment = supplierPayment;
    }

    public String getCustomerMaintenance() {
        return customerMaintenance;
    }

    public void setCustomerMaintenance(String customerMaintenance) {
        this.customerMaintenance = customerMaintenance;
    }

    public String getSalesQuotation() {
        return salesQuotation;
    }

    public void setSalesQuotation(String salesQuotation) {
        this.salesQuotation = salesQuotation;
    }

    public String getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(String salesOrder) {
        this.salesOrder = salesOrder;
    }

    public String getDeliveryorder() {
        return deliveryorder;
    }

    public void setDeliveryorder(String deliveryorder) {
        this.deliveryorder = deliveryorder;
    }

    public String getSalesInvoice() {
        return salesInvoice;
    }

    public void setSalesInvoice(String salesInvoice) {
        this.salesInvoice = salesInvoice;
    }

    public String getDirectSalesInvoice() {
        return directSalesInvoice;
    }

    public void setDirectSalesInvoice(String directSalesInvoice) {
        this.directSalesInvoice = directSalesInvoice;
    }

    public String getProformaSalesInvoice() {
        return proformaSalesInvoice;
    }

    public void setProformaSalesInvoice(String proformaSalesInvoice) {
        this.proformaSalesInvoice = proformaSalesInvoice;
    }

    public String getSalesDebitNote() {
        return salesDebitNote;
    }

    public void setSalesDebitNote(String salesDebitNote) {
        this.salesDebitNote = salesDebitNote;
    }

    public String getSalesCreditNote() {
        return salesCreditNote;
    }

    public void setSalesCreditNote(String salesCreditNote) {
        this.salesCreditNote = salesCreditNote;
    }

    public String getReceivePayment() {
        return receivePayment;
    }

    public void setReceivePayment(String receivePayment) {
        this.receivePayment = receivePayment;
    }

    public String getLocationTransfer() {
        return locationTransfer;
    }

    public void setLocationTransfer(String locationTransfer) {
        this.locationTransfer = locationTransfer;
    }

    public String getItemAdjustments() {
        return itemAdjustments;
    }

    public void setItemAdjustments(String itemAdjustments) {
        this.itemAdjustments = itemAdjustments;
    }

    public String getItemCountAdjust() {
        return itemCountAdjust;
    }

    public void setItemCountAdjust(String itemCountAdjust) {
        this.itemCountAdjust = itemCountAdjust;
    }

    public String getSalesPricing() {
        return salesPricing;
    }

    public void setSalesPricing(String salesPricing) {
        this.salesPricing = salesPricing;
    }

    public String getPurchasePricing() {
        return purchasePricing;
    }

    public void setPurchasePricing(String purchasePricing) {
        this.purchasePricing = purchasePricing;
    }

    public String getOthePaymentWithItem() {
        return othePaymentWithItem;
    }

    public void setOthePaymentWithItem(String othePaymentWithItem) {
        this.othePaymentWithItem = othePaymentWithItem;
    }

    public String getOtherPaymentWithoutItem() {
        return otherPaymentWithoutItem;
    }

    public void setOtherPaymentWithoutItem(String otherPaymentWithoutItem) {
        this.otherPaymentWithoutItem = otherPaymentWithoutItem;
    }

    public String getOtherReceiptWithItem() {
        return otherReceiptWithItem;
    }

    public void setOtherReceiptWithItem(String otherReceiptWithItem) {
        this.otherReceiptWithItem = otherReceiptWithItem;
    }

    public String getOtherReceiptWithoutItem() {
        return otherReceiptWithoutItem;
    }

    public void setOtherReceiptWithoutItem(String otherReceiptWithoutItem) {
        this.otherReceiptWithoutItem = otherReceiptWithoutItem;
    }

    public String getJournalEntry() {
        return journalEntry;
    }

    public void setJournalEntry(String journalEntry) {
        this.journalEntry = journalEntry;
    }

    public String getGsttype() {
        return gsttype;
    }

    public void setGsttype(String gsttype) {
        this.gsttype = gsttype;
    }

    public String getSalesGSTListing() {
        return salesGSTListing;
    }

    public void setSalesGSTListing(String salesGSTListing) {
        this.salesGSTListing = salesGSTListing;
    }

    public String getPurchaseGSTListing() {
        return purchaseGSTListing;
    }

    public void setPurchaseGSTListing(String purchaseGSTListing) {
        this.purchaseGSTListing = purchaseGSTListing;
    }

    public String getOtherListing() {
        return otherListing;
    }

    public void setOtherListing(String otherListing) {
        this.otherListing = otherListing;
    }

    public String getSalesAdjustment() {
        return salesAdjustment;
    }

    public void setSalesAdjustment(String salesAdjustment) {
        this.salesAdjustment = salesAdjustment;
    }

    public String getPurchaseAdjustment() {
        return purchaseAdjustment;
    }

    public void setPurchaseAdjustment(String purchaseAdjustment) {
        this.purchaseAdjustment = purchaseAdjustment;
    }

    public String getBadDebtRelief() {
        return badDebtRelief;
    }

    public void setBadDebtRelief(String badDebtRelief) {
        this.badDebtRelief = badDebtRelief;
    }

    public String getChangeOfUse() {
        return changeOfUse;
    }

    public void setChangeOfUse(String changeOfUse) {
        this.changeOfUse = changeOfUse;
    }

    public String getChangeOfAccountingBasis() {
        return changeOfAccountingBasis;
    }

    public void setChangeOfAccountingBasis(String changeOfAccountingBasis) {
        this.changeOfAccountingBasis = changeOfAccountingBasis;
    }

    public String getPartialExemption() {
        return partialExemption;
    }

    public void setPartialExemption(String partialExemption) {
        this.partialExemption = partialExemption;
    }

    public String getAnualAdjustment() {
        return anualAdjustment;
    }

    public void setAnualAdjustment(String anualAdjustment) {
        this.anualAdjustment = anualAdjustment;
    }

    public String getZakatMaintenance() {
        return zakatMaintenance;
    }

    public void setZakatMaintenance(String zakatMaintenance) {
        this.zakatMaintenance = zakatMaintenance;
    }

    public String getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(String openingBalance) {
        this.openingBalance = openingBalance;
    }

    public String getOpeningBalancePurchaseInvoice() {
        return openingBalancePurchaseInvoice;
    }

    public void setOpeningBalancePurchaseInvoice(String openingBalancePurchaseInvoice) {
        this.openingBalancePurchaseInvoice = openingBalancePurchaseInvoice;
    }

    public String getOpeningBalanceSalesInvoice() {
        return openingBalanceSalesInvoice;
    }

    public void setOpeningBalanceSalesInvoice(String openingBalanceSalesInvoice) {
        this.openingBalanceSalesInvoice = openingBalanceSalesInvoice;
    }

    public String getOpeningBalanceOfInventory() {
        return openingBalanceOfInventory;
    }

    public void setOpeningBalanceOfInventory(String openingBalanceOfInventory) {
        this.openingBalanceOfInventory = openingBalanceOfInventory;
    }

    public String getLastYearFigures() {
        return lastYearFigures;
    }

    public void setLastYearFigures(String lastYearFigures) {
        this.lastYearFigures = lastYearFigures;
    }

    public String getMonthEndProcessing() {
        return monthEndProcessing;
    }

    public void setMonthEndProcessing(String monthEndProcessing) {
        this.monthEndProcessing = monthEndProcessing;
    }

    public String getYearEndProcessing() {
        return yearEndProcessing;
    }

    public void setYearEndProcessing(String yearEndProcessing) {
        this.yearEndProcessing = yearEndProcessing;
    }

    public String getDatabaseRestore() {
        return databaseRestore;
    }

    public void setDatabaseRestore(String databaseRestore) {
        this.databaseRestore = databaseRestore;
    }

    public String getDatabaseBackup() {
        return databaseBackup;
    }

    public void setDatabaseBackup(String databaseBackup) {
        this.databaseBackup = databaseBackup;
    }

    public String getRecurringTransaction() {
        return recurringTransaction;
    }

    public void setRecurringTransaction(String recurringTransaction) {
        this.recurringTransaction = recurringTransaction;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getUnitMeasure() {
        return unitMeasure;
    }

    public void setUnitMeasure(String unitMeasure) {
        this.unitMeasure = unitMeasure;
    }

    public String getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getItemCategories() {
        return itemCategories;
    }

    public void setItemCategories(String itemCategories) {
        this.itemCategories = itemCategories;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getTermsandcondition() {
        return termsandcondition;
    }

    public void setTermsandcondition(String termsandcondition) {
        this.termsandcondition = termsandcondition;
    }

    public String getProductMerger() {
        return productMerger;
    }

    public void setProductMerger(String productMerger) {
        this.productMerger = productMerger;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getInventoryLocation() {
        return inventoryLocation;
    }

    public void setInventoryLocation(String inventoryLocation) {
        this.inventoryLocation = inventoryLocation;
    }

    public String getInventoryMovementType() {
        return inventoryMovementType;
    }

    public void setInventoryMovementType(String inventoryMovementType) {
        this.inventoryMovementType = inventoryMovementType;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getCompanyInformation() {
        return companyInformation;
    }

    public void setCompanyInformation(String companyInformation) {
        this.companyInformation = companyInformation;
    }

    public String getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(String serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public String getCompanyProfile() {
        return companyProfile;
    }

    public void setCompanyProfile(String companyProfile) {
        this.companyProfile = companyProfile;
    }

    public String getIaccountsalert() {
        return iaccountsalert;
    }

    public void setIaccountsalert(String iaccountsalert) {
        this.iaccountsalert = iaccountsalert;
    }

    public String getUserAccountSetup() {
        return userAccountSetup;
    }

    public void setUserAccountSetup(String userAccountSetup) {
        this.userAccountSetup = userAccountSetup;
    }

    public String getAccountGroup() {
        return accountGroup;
    }

    public void setAccountGroup(String accountGroup) {
        this.accountGroup = accountGroup;
    }

    public String getCreateChartAccount() {
        return createChartAccount;
    }

    public void setCreateChartAccount(String createChartAccount) {
        this.createChartAccount = createChartAccount;
    }

    public String getAccountTypeDescription() {
        return accountTypeDescription;
    }

    public void setAccountTypeDescription(String accountTypeDescription) {
        this.accountTypeDescription = accountTypeDescription;
    }

    public String getParametersetup() {
        return parametersetup;
    }

    public void setParametersetup(String parametersetup) {
        this.parametersetup = parametersetup;
    }

    public String getFormsetup() {
        return formsetup;
    }

    public void setFormsetup(String formsetup) {
        this.formsetup = formsetup;
    }

    public String getAuditTrail() {
        return auditTrail;
    }

    public void setAuditTrail(String auditTrail) {
        this.auditTrail = auditTrail;
    }

    public String getShortCut() {
        return shortCut;
    }

    public void setShortCut(String shortCut) {
        this.shortCut = shortCut;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getHipos() {
        return hipos;
    }

    public void setHipos(String hipos) {
        this.hipos = hipos;
    }

    public String getHiposOptions() {
        return hiposOptions;
    }

    public void setHiposOptions(String hiposOptions) {
        this.hiposOptions = hiposOptions;
    }

    public String getMsicCode() {
        return msicCode;
    }

    public void setMsicCode(String msicCode) {
        this.msicCode = msicCode;
    }

    public String getEmailServer() {
        return emailServer;
    }

    public void setEmailServer(String emailServer) {
        this.emailServer = emailServer;
    }

    public String getSelfBuildApprovalDate() {
        return selfBuildApprovalDate;
    }

    public void setSelfBuildApprovalDate(String selfBuildApprovalDate) {
        this.selfBuildApprovalDate = selfBuildApprovalDate;
    }

    public String getServiceReceiveItem() {
        return serviceReceiveItem;
    }

    public void setServiceReceiveItem(String serviceReceiveItem) {
        this.serviceReceiveItem = serviceReceiveItem;
    }

    public String getServiceDeleiveryItem() {
        return serviceDeleiveryItem;
    }

    public void setServiceDeleiveryItem(String serviceDeleiveryItem) {
        this.serviceDeleiveryItem = serviceDeleiveryItem;
    }

    public Object getServiceReplaceItem() {
        return serviceReplaceItem;
    }

    public void setServiceReplaceItem(Object serviceReplaceItem) {
        this.serviceReplaceItem = serviceReplaceItem;
    }

    public String getCommisionAgentListing() {
        return commisionAgentListing;
    }

    public void setCommisionAgentListing(String commisionAgentListing) {
        this.commisionAgentListing = commisionAgentListing;
    }

    public String getCommisionpatientListing() {
        return commisionpatientListing;
    }

    public void setCommisionpatientListing(String commisionpatientListing) {
        this.commisionpatientListing = commisionpatientListing;
    }

    public String getGoldSalesInvoice() {
        return goldSalesInvoice;
    }

    public void setGoldSalesInvoice(String goldSalesInvoice) {
        this.goldSalesInvoice = goldSalesInvoice;
    }

    public String getPharmaSalesInvoice() {
        return pharmaSalesInvoice;
    }

    public void setPharmaSalesInvoice(String pharmaSalesInvoice) {
        this.pharmaSalesInvoice = pharmaSalesInvoice;
    }

    public String getItemBrand() {
        return itemBrand;
    }

    public void setItemBrand(String itemBrand) {
        this.itemBrand = itemBrand;
    }

    public String getSerialItem() {
        return serialItem;
    }

    public void setSerialItem(String serialItem) {
        this.serialItem = serialItem;
    }

    public String getPosSalesInvoice() {
        return posSalesInvoice;
    }

    public void setPosSalesInvoice(String posSalesInvoice) {
        this.posSalesInvoice = posSalesInvoice;
    }

    public String getZakathRedistributionForm() {
        return zakathRedistributionForm;
    }

    public void setZakathRedistributionForm(String zakathRedistributionForm) {
        this.zakathRedistributionForm = zakathRedistributionForm;
    }

    public String getGafFileExport() {
        return gafFileExport;
    }

    public void setGafFileExport(String gafFileExport) {
        this.gafFileExport = gafFileExport;
    }

    public String getGafPosExport() {
        return gafPosExport;
    }

    public void setGafPosExport(String gafPosExport) {
        this.gafPosExport = gafPosExport;
    }

    public String getPosConfiguration() {
        return posConfiguration;
    }

    public void setPosConfiguration(String posConfiguration) {
        this.posConfiguration = posConfiguration;
    }

    public String getTableConfiguration() {
        return tableConfiguration;
    }

    public void setTableConfiguration(String tableConfiguration) {
        this.tableConfiguration = tableConfiguration;
    }

    public Object getServiceCharges() {
        return serviceCharges;
    }

    public void setServiceCharges(Object serviceCharges) {
        this.serviceCharges = serviceCharges;
    }

    public Object getRestareantpos() {
        return restareantpos;
    }

    public void setRestareantpos(Object restareantpos) {
        this.restareantpos = restareantpos;
    }

    public Object getRetailpos() {
        return retailpos;
    }

    public void setRetailpos(Object retailpos) {
        this.retailpos = retailpos;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public Object getAlerts() {
        return alerts;
    }

    public void setAlerts(Object alerts) {
        this.alerts = alerts;
    }

    public String getReportsReceiveItemListing() {
        return reportsReceiveItemListing;
    }

    public void setReportsReceiveItemListing(String reportsReceiveItemListing) {
        this.reportsReceiveItemListing = reportsReceiveItemListing;
    }

    public String getReportsSupplierStatementListing() {
        return reportsSupplierStatementListing;
    }

    public void setReportsSupplierStatementListing(String reportsSupplierStatementListing) {
        this.reportsSupplierStatementListing = reportsSupplierStatementListing;
    }

    public String getReportsPurchaseOrderListing() {
        return reportsPurchaseOrderListing;
    }

    public void setReportsPurchaseOrderListing(String reportsPurchaseOrderListing) {
        this.reportsPurchaseOrderListing = reportsPurchaseOrderListing;
    }

    public String getReportsSupplierListing() {
        return reportsSupplierListing;
    }

    public void setReportsSupplierListing(String reportsSupplierListing) {
        this.reportsSupplierListing = reportsSupplierListing;
    }

    public String getReportsPurchaseInvoiceListing() {
        return reportsPurchaseInvoiceListing;
    }

    public void setReportsPurchaseInvoiceListing(String reportsPurchaseInvoiceListing) {
        this.reportsPurchaseInvoiceListing = reportsPurchaseInvoiceListing;
    }

    public String getReportsPurchaseReturnListing() {
        return reportsPurchaseReturnListing;
    }

    public void setReportsPurchaseReturnListing(String reportsPurchaseReturnListing) {
        this.reportsPurchaseReturnListing = reportsPurchaseReturnListing;
    }

    public String getReportsSupplierPayment() {
        return reportsSupplierPayment;
    }

    public void setReportsSupplierPayment(String reportsSupplierPayment) {
        this.reportsSupplierPayment = reportsSupplierPayment;
    }

    public String getReportsCreditorsAgeing() {
        return reportsCreditorsAgeing;
    }

    public void setReportsCreditorsAgeing(String reportsCreditorsAgeing) {
        this.reportsCreditorsAgeing = reportsCreditorsAgeing;
    }

    public String getReportsPurchaseAdjustment() {
        return reportsPurchaseAdjustment;
    }

    public void setReportsPurchaseAdjustment(String reportsPurchaseAdjustment) {
        this.reportsPurchaseAdjustment = reportsPurchaseAdjustment;
    }

    public String getReportsCancelledPurchaseInvoice() {
        return reportsCancelledPurchaseInvoice;
    }

    public void setReportsCancelledPurchaseInvoice(String reportsCancelledPurchaseInvoice) {
        this.reportsCancelledPurchaseInvoice = reportsCancelledPurchaseInvoice;
    }

    public String getReportsSalesQuotationListing() {
        return reportsSalesQuotationListing;
    }

    public void setReportsSalesQuotationListing(String reportsSalesQuotationListing) {
        this.reportsSalesQuotationListing = reportsSalesQuotationListing;
    }

    public String getReportsSalesOrderListing() {
        return reportsSalesOrderListing;
    }

    public void setReportsSalesOrderListing(String reportsSalesOrderListing) {
        this.reportsSalesOrderListing = reportsSalesOrderListing;
    }

    public String getReportsSalesInvoiceListing() {
        return reportsSalesInvoiceListing;
    }

    public void setReportsSalesInvoiceListing(String reportsSalesInvoiceListing) {
        this.reportsSalesInvoiceListing = reportsSalesInvoiceListing;
    }

    public String getReportsSalesReturnListing() {
        return reportsSalesReturnListing;
    }

    public void setReportsSalesReturnListing(String reportsSalesReturnListing) {
        this.reportsSalesReturnListing = reportsSalesReturnListing;
    }

    public String getReportsCustomerStatement() {
        return reportsCustomerStatement;
    }

    public void setReportsCustomerStatement(String reportsCustomerStatement) {
        this.reportsCustomerStatement = reportsCustomerStatement;
    }

    public String getReportsProductDeliveryListing() {
        return reportsProductDeliveryListing;
    }

    public void setReportsProductDeliveryListing(String reportsProductDeliveryListing) {
        this.reportsProductDeliveryListing = reportsProductDeliveryListing;
    }

    public String getReportsPaymentReport() {
        return reportsPaymentReport;
    }

    public void setReportsPaymentReport(String reportsPaymentReport) {
        this.reportsPaymentReport = reportsPaymentReport;
    }

    public String getReportsPriceListing() {
        return reportsPriceListing;
    }

    public void setReportsPriceListing(String reportsPriceListing) {
        this.reportsPriceListing = reportsPriceListing;
    }

    public String getReportsDebtorsAgening() {
        return reportsDebtorsAgening;
    }

    public void setReportsDebtorsAgening(String reportsDebtorsAgening) {
        this.reportsDebtorsAgening = reportsDebtorsAgening;
    }

    public String getReportsAgentListing() {
        return reportsAgentListing;
    }

    public void setReportsAgentListing(String reportsAgentListing) {
        this.reportsAgentListing = reportsAgentListing;
    }

    public String getReportsCustomerListing() {
        return reportsCustomerListing;
    }

    public void setReportsCustomerListing(String reportsCustomerListing) {
        this.reportsCustomerListing = reportsCustomerListing;
    }

    public String getReportsPatientListing() {
        return reportsPatientListing;
    }

    public void setReportsPatientListing(String reportsPatientListing) {
        this.reportsPatientListing = reportsPatientListing;
    }

    public String getReportsCustomerPayment() {
        return reportsCustomerPayment;
    }

    public void setReportsCustomerPayment(String reportsCustomerPayment) {
        this.reportsCustomerPayment = reportsCustomerPayment;
    }

    public String getReportsSalesAdjustment() {
        return reportsSalesAdjustment;
    }

    public void setReportsSalesAdjustment(String reportsSalesAdjustment) {
        this.reportsSalesAdjustment = reportsSalesAdjustment;
    }

    public String getReportsCancelledSalesInvoice() {
        return reportsCancelledSalesInvoice;
    }

    public void setReportsCancelledSalesInvoice(String reportsCancelledSalesInvoice) {
        this.reportsCancelledSalesInvoice = reportsCancelledSalesInvoice;
    }

    public String getReportsGRNValuationReport() {
        return reportsGRNValuationReport;
    }

    public void setReportsGRNValuationReport(String reportsGRNValuationReport) {
        this.reportsGRNValuationReport = reportsGRNValuationReport;
    }

    public String getReportsInventoryPlaningReport() {
        return reportsInventoryPlaningReport;
    }

    public void setReportsInventoryPlaningReport(String reportsInventoryPlaningReport) {
        this.reportsInventoryPlaningReport = reportsInventoryPlaningReport;
    }

    public String getReportsInventoryValuationReport() {
        return reportsInventoryValuationReport;
    }

    public void setReportsInventoryValuationReport(String reportsInventoryValuationReport) {
        this.reportsInventoryValuationReport = reportsInventoryValuationReport;
    }

    public String getReportsInventorySalesReport() {
        return reportsInventorySalesReport;
    }

    public void setReportsInventorySalesReport(String reportsInventorySalesReport) {
        this.reportsInventorySalesReport = reportsInventorySalesReport;
    }

    public String getReportsInventoryNonMovementReport() {
        return reportsInventoryNonMovementReport;
    }

    public void setReportsInventoryNonMovementReport(String reportsInventoryNonMovementReport) {
        this.reportsInventoryNonMovementReport = reportsInventoryNonMovementReport;
    }

    public String getReportsInventoryMovementReport() {
        return reportsInventoryMovementReport;
    }

    public void setReportsInventoryMovementReport(String reportsInventoryMovementReport) {
        this.reportsInventoryMovementReport = reportsInventoryMovementReport;
    }

    public String getReportsInventoryListReport() {
        return reportsInventoryListReport;
    }

    public void setReportsInventoryListReport(String reportsInventoryListReport) {
        this.reportsInventoryListReport = reportsInventoryListReport;
    }

    public String getReportsOutstandingGRNReport() {
        return reportsOutstandingGRNReport;
    }

    public void setReportsOutstandingGRNReport(String reportsOutstandingGRNReport) {
        this.reportsOutstandingGRNReport = reportsOutstandingGRNReport;
    }

    public String getReportsStockCheckReport() {
        return reportsStockCheckReport;
    }

    public void setReportsStockCheckReport(String reportsStockCheckReport) {
        this.reportsStockCheckReport = reportsStockCheckReport;
    }

    public String getReportsAccountBalance() {
        return reportsAccountBalance;
    }

    public void setReportsAccountBalance(String reportsAccountBalance) {
        this.reportsAccountBalance = reportsAccountBalance;
    }

    public String getReportsIncomeExpenseTrend() {
        return reportsIncomeExpenseTrend;
    }

    public void setReportsIncomeExpenseTrend(String reportsIncomeExpenseTrend) {
        this.reportsIncomeExpenseTrend = reportsIncomeExpenseTrend;
    }

    public String getReportsCustomerReceiptReport() {
        return reportsCustomerReceiptReport;
    }

    public void setReportsCustomerReceiptReport(String reportsCustomerReceiptReport) {
        this.reportsCustomerReceiptReport = reportsCustomerReceiptReport;
    }

    public String getReportsSupplierPaymentReport() {
        return reportsSupplierPaymentReport;
    }

    public void setReportsSupplierPaymentReport(String reportsSupplierPaymentReport) {
        this.reportsSupplierPaymentReport = reportsSupplierPaymentReport;
    }

    public String getReportsIncomeComparisionReport() {
        return reportsIncomeComparisionReport;
    }

    public void setReportsIncomeComparisionReport(String reportsIncomeComparisionReport) {
        this.reportsIncomeComparisionReport = reportsIncomeComparisionReport;
    }

    public String getReportsExpenseComparisionReport() {
        return reportsExpenseComparisionReport;
    }

    public void setReportsExpenseComparisionReport(String reportsExpenseComparisionReport) {
        this.reportsExpenseComparisionReport = reportsExpenseComparisionReport;
    }

    public String getReportsCustomerInvoicePayment() {
        return reportsCustomerInvoicePayment;
    }

    public void setReportsCustomerInvoicePayment(String reportsCustomerInvoicePayment) {
        this.reportsCustomerInvoicePayment = reportsCustomerInvoicePayment;
    }

    public String getReportsSupplierInvoicePayment() {
        return reportsSupplierInvoicePayment;
    }

    public void setReportsSupplierInvoicePayment(String reportsSupplierInvoicePayment) {
        this.reportsSupplierInvoicePayment = reportsSupplierInvoicePayment;
    }

    public String getReportsTopVendors() {
        return reportsTopVendors;
    }

    public void setReportsTopVendors(String reportsTopVendors) {
        this.reportsTopVendors = reportsTopVendors;
    }

    public String getReportsTopItems() {
        return reportsTopItems;
    }

    public void setReportsTopItems(String reportsTopItems) {
        this.reportsTopItems = reportsTopItems;
    }

    public String getReportsTopCustomers() {
        return reportsTopCustomers;
    }

    public void setReportsTopCustomers(String reportsTopCustomers) {
        this.reportsTopCustomers = reportsTopCustomers;
    }

    public String getReportsStatementOfComprehensiveIncome() {
        return reportsStatementOfComprehensiveIncome;
    }

    public void setReportsStatementOfComprehensiveIncome(String reportsStatementOfComprehensiveIncome) {
        this.reportsStatementOfComprehensiveIncome = reportsStatementOfComprehensiveIncome;
    }

    public String getReportsStatementOfFinancialPosition() {
        return reportsStatementOfFinancialPosition;
    }

    public void setReportsStatementOfFinancialPosition(String reportsStatementOfFinancialPosition) {
        this.reportsStatementOfFinancialPosition = reportsStatementOfFinancialPosition;
    }

    public String getReportsTrialBalance() {
        return reportsTrialBalance;
    }

    public void setReportsTrialBalance(String reportsTrialBalance) {
        this.reportsTrialBalance = reportsTrialBalance;
    }

    public String getReportsGeneralLedger() {
        return reportsGeneralLedger;
    }

    public void setReportsGeneralLedger(String reportsGeneralLedger) {
        this.reportsGeneralLedger = reportsGeneralLedger;
    }

    public String getReportsViewLedger() {
        return reportsViewLedger;
    }

    public void setReportsViewLedger(String reportsViewLedger) {
        this.reportsViewLedger = reportsViewLedger;
    }

    public String getReportsGeneralJournal() {
        return reportsGeneralJournal;
    }

    public void setReportsGeneralJournal(String reportsGeneralJournal) {
        this.reportsGeneralJournal = reportsGeneralJournal;
    }

    public String getReportsStatementOfAccount() {
        return reportsStatementOfAccount;
    }

    public void setReportsStatementOfAccount(String reportsStatementOfAccount) {
        this.reportsStatementOfAccount = reportsStatementOfAccount;
    }

    public String getReportsCheque() {
        return reportsCheque;
    }

    public void setReportsCheque(String reportsCheque) {
        this.reportsCheque = reportsCheque;
    }

    public String getReportsRatios() {
        return reportsRatios;
    }

    public void setReportsRatios(String reportsRatios) {
        this.reportsRatios = reportsRatios;
    }

    public Object getLocationId() {
        return locationId;
    }

    public void setLocationId(Object locationId) {
        this.locationId = locationId;
    }

    public Object getUseraccountId() {
        return useraccountId;
    }

    public void setUseraccountId(Object useraccountId) {
        this.useraccountId = useraccountId;
    }
}
