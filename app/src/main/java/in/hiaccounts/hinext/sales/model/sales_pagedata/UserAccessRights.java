
package in.hiaccounts.hinext.sales.model.sales_pagedata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserAccessRights implements Serializable {

    @SerializedName("message")
    @Expose
    public Object message;
    @SerializedName("status")
    @Expose
    public long status;
    @SerializedName("id")
    @Expose
    public long id;
    @SerializedName("userAccountSetupID")
    @Expose
    public String userAccountSetupID;
    @SerializedName("dashboard")
    @Expose
    public boolean dashboard;
    @SerializedName("dashboardDateTime")
    @Expose
    public boolean dashboardDateTime;
    @SerializedName("dashboardCashInHand")
    @Expose
    public boolean dashboardCashInHand;
    @SerializedName("dashboardCashInBank")
    @Expose
    public boolean dashboardCashInBank;
    @SerializedName("dashboardTotalReceivable")
    @Expose
    public boolean dashboardTotalReceivable;
    @SerializedName("dashboardTotalPayable")
    @Expose
    public boolean dashboardTotalPayable;
    @SerializedName("dashboardExtra1")
    @Expose
    public boolean dashboardExtra1;
    @SerializedName("dashboardExtra2")
    @Expose
    public boolean dashboardExtra2;
    @SerializedName("sales")
    @Expose
    public boolean sales;
    @SerializedName("salesItemAdd")
    @Expose
    public boolean salesItemAdd;
    @SerializedName("salesCustomerAdd")
    @Expose
    public boolean salesCustomerAdd;
    @SerializedName("salesSavesDraft")
    @Expose
    public boolean salesSavesDraft;
    @SerializedName("salesClearDraft")
    @Expose
    public boolean salesClearDraft;
    @SerializedName("salesPrintList")
    @Expose
    public boolean salesPrintList;
    @SerializedName("salesAdvancePayment")
    @Expose
    public boolean salesAdvancePayment;
    @SerializedName("salesSaveSalesOrder")
    @Expose
    public boolean salesSaveSalesOrder;
    @SerializedName("salesAdvancePaymentList")
    @Expose
    public boolean salesAdvancePaymentList;
    @SerializedName("salesInvokeSalesOrder")
    @Expose
    public boolean salesInvokeSalesOrder;
    @SerializedName("salesClearAll")
    @Expose
    public boolean salesClearAll;
    @SerializedName("salesCheckOut")
    @Expose
    public boolean salesCheckOut;
    @SerializedName("salesReturn")
    @Expose
    public boolean salesReturn;
    @SerializedName("salesRecievePayment")
    @Expose
    public boolean salesRecievePayment;
    @SerializedName("salesInvokeDraft")
    @Expose
    public boolean salesInvokeDraft;
    @SerializedName("salesRemoveItem")
    @Expose
    public Boolean salesRemoveItem;
    @SerializedName("purchase")
    @Expose
    public boolean purchase;
    @SerializedName("purchaseItemAdd")
    @Expose
    public boolean purchaseItemAdd;
    @SerializedName("purchaseSupplierAdd")
    @Expose
    public boolean purchaseSupplierAdd;
    @SerializedName("purchasePrintList")
    @Expose
    public boolean purchasePrintList;
    @SerializedName("purchaseAdvancePayment")
    @Expose
    public boolean purchaseAdvancePayment;
    @SerializedName("purchaseSavePurchaseOrder")
    @Expose
    public boolean purchaseSavePurchaseOrder;
    @SerializedName("purchaseAdvancePaymentList")
    @Expose
    public boolean purchaseAdvancePaymentList;
    @SerializedName("purchaseInvokePurchaseOrder")
    @Expose
    public boolean purchaseInvokePurchaseOrder;
    @SerializedName("purchaseClearAll")
    @Expose
    public boolean purchaseClearAll;
    @SerializedName("purchaseCheckOut")
    @Expose
    public boolean purchaseCheckOut;
    @SerializedName("purchaseReturn")
    @Expose
    public boolean purchaseReturn;
    @SerializedName("purchaseSupplierPayment")
    @Expose
    public boolean purchaseSupplierPayment;
    @SerializedName("purchaseSelfBilled")
    @Expose
    public boolean purchaseSelfBilled;
    @SerializedName("gt")
    @Expose
    public boolean gt;
    @SerializedName("gtExpense")
    @Expose
    public boolean gtExpense;
    @SerializedName("gtReciepts")
    @Expose
    public boolean gtReciepts;
    @SerializedName("gtJournalEntry")
    @Expose
    public boolean gtJournalEntry;
    @SerializedName("inventory")
    @Expose
    public boolean inventory;
    @SerializedName("inventoryCategory")
    @Expose
    public boolean inventoryCategory;
    @SerializedName("inventoryBrand")
    @Expose
    public boolean inventoryBrand;
    @SerializedName("inventoryUOM")
    @Expose
    public boolean inventoryUOM;
    @SerializedName("inventoryItem")
    @Expose
    public boolean inventoryItem;
    @SerializedName("inventorySalesPricing")
    @Expose
    public boolean inventorySalesPricing;
    @SerializedName("inventoryPurchasePricing")
    @Expose
    public boolean inventoryPurchasePricing;
    @SerializedName("inventoryInventoryLocation")
    @Expose
    public boolean inventoryInventoryLocation;
    @SerializedName("inventoryInventoryMovementType")
    @Expose
    public boolean inventoryInventoryMovementType;
    @SerializedName("tax")
    @Expose
    public boolean tax;
    @SerializedName("taxTaxConfiguration")
    @Expose
    public boolean taxTaxConfiguration;
    @SerializedName("taxGST")
    @Expose
    public boolean taxGST;
    @SerializedName("controlPanel")
    @Expose
    public boolean controlPanel;
    @SerializedName("controlPanelOBofBS")
    @Expose
    public boolean controlPanelOBofBS;
    @SerializedName("controlPanelOBofPI")
    @Expose
    public boolean controlPanelOBofPI;
    @SerializedName("controlPanelOBofSI")
    @Expose
    public boolean controlPanelOBofSI;
    @SerializedName("controlPanelOBofINV")
    @Expose
    public boolean controlPanelOBofINV;
    @SerializedName("controlPanelLastYearFigure")
    @Expose
    public boolean controlPanelLastYearFigure;
    @SerializedName("controlPanelMonthEnd")
    @Expose
    public boolean controlPanelMonthEnd;
    @SerializedName("controlPanelYearEnd")
    @Expose
    public boolean controlPanelYearEnd;
    @SerializedName("controlPanelConfigurator")
    @Expose
    public boolean controlPanelConfigurator;
    @SerializedName("cPConfiguratorBank")
    @Expose
    public boolean cPConfiguratorBank;
    @SerializedName("cPConfiguratorAgent")
    @Expose
    public boolean cPConfiguratorAgent;
    @SerializedName("cPConfiguratorCurrency")
    @Expose
    public boolean cPConfiguratorCurrency;
    @SerializedName("cPConfiguratorEmployee")
    @Expose
    public boolean cPConfiguratorEmployee;
    @SerializedName("cPConfiguratorExchangeRate")
    @Expose
    public boolean cPConfiguratorExchangeRate;
    @SerializedName("cPConfiguratorProjectTitle")
    @Expose
    public boolean cPConfiguratorProjectTitle;
    @SerializedName("cPConfiguratorShippingMethod")
    @Expose
    public boolean cPConfiguratorShippingMethod;
    @SerializedName("cPConfiguratorEmailserver")
    @Expose
    public boolean cPConfiguratorEmailserver;
    @SerializedName("cPConfiguratorPaymentMethod")
    @Expose
    public boolean cPConfiguratorPaymentMethod;
    @SerializedName("cPConfiguratorConfiguration")
    @Expose
    public boolean cPConfiguratorConfiguration;
    @SerializedName("controlPanelCompanySetup")
    @Expose
    public boolean controlPanelCompanySetup;
    @SerializedName("cPCompanySetupCI")
    @Expose
    public boolean cPCompanySetupCI;
    @SerializedName("cPCompanySetupFY")
    @Expose
    public boolean cPCompanySetupFY;
    @SerializedName("cPCompanySetupCA")
    @Expose
    public boolean cPCompanySetupCA;
    @SerializedName("cPCompanySetupHSN")
    @Expose
    public boolean cPCompanySetupHSN;
    @SerializedName("controlPanelUAS")
    @Expose
    public boolean controlPanelUAS;
    @SerializedName("controlPanelUASuas")
    @Expose
    public boolean controlPanelUASuas;
    @SerializedName("controlPanelUASat")
    @Expose
    public boolean controlPanelUASat;
    @SerializedName("controlPanelAccountMaintenance")
    @Expose
    public boolean controlPanelAccountMaintenance;
    @SerializedName("controlPanelAMag")
    @Expose
    public boolean controlPanelAMag;
    @SerializedName("controlPanelAMcoa")
    @Expose
    public boolean controlPanelAMcoa;
    @SerializedName("controlPanelAMbudget")
    @Expose
    public boolean controlPanelAMbudget;
    @SerializedName("controlPanelDisplaySetup")
    @Expose
    public boolean controlPanelDisplaySetup;
    @SerializedName("controlPanelDSfs")
    @Expose
    public boolean controlPanelDSfs;
    @SerializedName("controlPanelDSlayout")
    @Expose
    public boolean controlPanelDSlayout;
    @SerializedName("controlPanelDStermsCond")
    @Expose
    public boolean controlPanelDStermsCond;
    @SerializedName("contact")
    @Expose
    public boolean contact;
    @SerializedName("contactSupplier")
    @Expose
    public boolean contactSupplier;
    @SerializedName("contactCustomer")
    @Expose
    public boolean contactCustomer;
    @SerializedName("contactContacts")
    @Expose
    public boolean contactContacts;
    @SerializedName("report")
    @Expose
    public boolean report;
    @SerializedName("reportPurchase")
    @Expose
    public boolean reportPurchase;
    @SerializedName("reportPurchaseSSR")
    @Expose
    public boolean reportPurchaseSSR;
    @SerializedName("reportPurchasePOL")
    @Expose
    public boolean reportPurchasePOL;
    @SerializedName("reportPurchasePIL")
    @Expose
    public boolean reportPurchasePIL;
    @SerializedName("reportPurchasePRL")
    @Expose
    public boolean reportPurchasePRL;
    @SerializedName("reportPurchaseSP")
    @Expose
    public boolean reportPurchaseSP;
    @SerializedName("reportPurchasePA")
    @Expose
    public boolean reportPurchasePA;
    @SerializedName("reportSale")
    @Expose
    public boolean reportSale;
    @SerializedName("reportSaleSOL")
    @Expose
    public boolean reportSaleSOL;
    @SerializedName("reportSaleICL")
    @Expose
    public boolean reportSaleICL;
    @SerializedName("reportSaleSIL")
    @Expose
    public boolean reportSaleSIL;
    @SerializedName("reportSaleSRL")
    @Expose
    public boolean reportSaleSRL;
    @SerializedName("reportSalePR")
    @Expose
    public boolean reportSalePR;
    @SerializedName("reportSalePL")
    @Expose
    public boolean reportSalePL;
    @SerializedName("reportSaleCA")
    @Expose
    public boolean reportSaleCA;
    @SerializedName("reportSaleCP")
    @Expose
    public boolean reportSaleCP;
    @SerializedName("reportSaleSA")
    @Expose
    public boolean reportSaleSA;
    @SerializedName("reportInventory")
    @Expose
    public boolean reportInventory;
    @SerializedName("reportInventoryIVR")
    @Expose
    public boolean reportInventoryIVR;
    @SerializedName("reportInventoryISR")
    @Expose
    public boolean reportInventoryISR;
    @SerializedName("reportInventoryilSCR")
    @Expose
    public boolean reportInventoryilSCR;
    @SerializedName("reportAnalysis")
    @Expose
    public boolean reportAnalysis;
    @SerializedName("reportAnalysisTopVendor")
    @Expose
    public boolean reportAnalysisTopVendor;
    @SerializedName("reportAnalysisTopItem")
    @Expose
    public boolean reportAnalysisTopItem;
    @SerializedName("reportAnalysisTopCustomer")
    @Expose
    public boolean reportAnalysisTopCustomer;
    @SerializedName("reportFinancialStatement")
    @Expose
    public boolean reportFinancialStatement;
    @SerializedName("reportFStatementPL")
    @Expose
    public boolean reportFStatementPL;
    @SerializedName("reportFStatementBS")
    @Expose
    public boolean reportFStatementBS;
    @SerializedName("reportFStatementTB")
    @Expose
    public boolean reportFStatementTB;
    @SerializedName("reportFStatementGL")
    @Expose
    public boolean reportFStatementGL;
    @SerializedName("reportFStatementVL")
    @Expose
    public boolean reportFStatementVL;
    @SerializedName("reportFStatementGJ")
    @Expose
    public boolean reportFStatementGJ;
    @SerializedName("reportBankRecon")
    @Expose
    public boolean reportBankRecon;
    @SerializedName("reportBankReconRatio")
    @Expose
    public boolean reportBankReconRatio;

    public Boolean getSalesRemoveItem() {
        return salesRemoveItem;
    }

    public void setSalesRemoveItem(Boolean salesRemoveItem) {
        this.salesRemoveItem = salesRemoveItem;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserAccountSetupID() {
        return userAccountSetupID;
    }

    public void setUserAccountSetupID(String userAccountSetupID) {
        this.userAccountSetupID = userAccountSetupID;
    }

    public boolean isDashboard() {
        return dashboard;
    }

    public void setDashboard(boolean dashboard) {
        this.dashboard = dashboard;
    }

    public boolean isDashboardDateTime() {
        return dashboardDateTime;
    }

    public void setDashboardDateTime(boolean dashboardDateTime) {
        this.dashboardDateTime = dashboardDateTime;
    }

    public boolean isDashboardCashInHand() {
        return dashboardCashInHand;
    }

    public void setDashboardCashInHand(boolean dashboardCashInHand) {
        this.dashboardCashInHand = dashboardCashInHand;
    }

    public boolean isDashboardCashInBank() {
        return dashboardCashInBank;
    }

    public void setDashboardCashInBank(boolean dashboardCashInBank) {
        this.dashboardCashInBank = dashboardCashInBank;
    }

    public boolean isDashboardTotalReceivable() {
        return dashboardTotalReceivable;
    }

    public void setDashboardTotalReceivable(boolean dashboardTotalReceivable) {
        this.dashboardTotalReceivable = dashboardTotalReceivable;
    }

    public boolean isDashboardTotalPayable() {
        return dashboardTotalPayable;
    }

    public void setDashboardTotalPayable(boolean dashboardTotalPayable) {
        this.dashboardTotalPayable = dashboardTotalPayable;
    }

    public boolean isDashboardExtra1() {
        return dashboardExtra1;
    }

    public void setDashboardExtra1(boolean dashboardExtra1) {
        this.dashboardExtra1 = dashboardExtra1;
    }

    public boolean isDashboardExtra2() {
        return dashboardExtra2;
    }

    public void setDashboardExtra2(boolean dashboardExtra2) {
        this.dashboardExtra2 = dashboardExtra2;
    }

    public boolean isSales() {
        return sales;
    }

    public void setSales(boolean sales) {
        this.sales = sales;
    }

    public boolean isSalesItemAdd() {
        return salesItemAdd;
    }

    public void setSalesItemAdd(boolean salesItemAdd) {
        this.salesItemAdd = salesItemAdd;
    }

    public boolean isSalesCustomerAdd() {
        return salesCustomerAdd;
    }

    public void setSalesCustomerAdd(boolean salesCustomerAdd) {
        this.salesCustomerAdd = salesCustomerAdd;
    }

    public boolean isSalesSavesDraft() {
        return salesSavesDraft;
    }

    public void setSalesSavesDraft(boolean salesSavesDraft) {
        this.salesSavesDraft = salesSavesDraft;
    }

    public boolean isSalesClearDraft() {
        return salesClearDraft;
    }

    public void setSalesClearDraft(boolean salesClearDraft) {
        this.salesClearDraft = salesClearDraft;
    }

    public boolean isSalesPrintList() {
        return salesPrintList;
    }

    public void setSalesPrintList(boolean salesPrintList) {
        this.salesPrintList = salesPrintList;
    }

    public boolean isSalesAdvancePayment() {
        return salesAdvancePayment;
    }

    public void setSalesAdvancePayment(boolean salesAdvancePayment) {
        this.salesAdvancePayment = salesAdvancePayment;
    }

    public boolean isSalesSaveSalesOrder() {
        return salesSaveSalesOrder;
    }

    public void setSalesSaveSalesOrder(boolean salesSaveSalesOrder) {
        this.salesSaveSalesOrder = salesSaveSalesOrder;
    }

    public boolean isSalesAdvancePaymentList() {
        return salesAdvancePaymentList;
    }

    public void setSalesAdvancePaymentList(boolean salesAdvancePaymentList) {
        this.salesAdvancePaymentList = salesAdvancePaymentList;
    }

    public boolean isSalesInvokeSalesOrder() {
        return salesInvokeSalesOrder;
    }

    public void setSalesInvokeSalesOrder(boolean salesInvokeSalesOrder) {
        this.salesInvokeSalesOrder = salesInvokeSalesOrder;
    }

    public boolean isSalesClearAll() {
        return salesClearAll;
    }

    public void setSalesClearAll(boolean salesClearAll) {
        this.salesClearAll = salesClearAll;
    }

    public boolean isSalesCheckOut() {
        return salesCheckOut;
    }

    public void setSalesCheckOut(boolean salesCheckOut) {
        this.salesCheckOut = salesCheckOut;
    }

    public boolean isSalesReturn() {
        return salesReturn;
    }

    public void setSalesReturn(boolean salesReturn) {
        this.salesReturn = salesReturn;
    }

    public boolean isSalesRecievePayment() {
        return salesRecievePayment;
    }

    public void setSalesRecievePayment(boolean salesRecievePayment) {
        this.salesRecievePayment = salesRecievePayment;
    }

    public boolean isSalesInvokeDraft() {
        return salesInvokeDraft;
    }

    public void setSalesInvokeDraft(boolean salesInvokeDraft) {
        this.salesInvokeDraft = salesInvokeDraft;
    }

    public boolean isPurchase() {
        return purchase;
    }

    public void setPurchase(boolean purchase) {
        this.purchase = purchase;
    }

    public boolean isPurchaseItemAdd() {
        return purchaseItemAdd;
    }

    public void setPurchaseItemAdd(boolean purchaseItemAdd) {
        this.purchaseItemAdd = purchaseItemAdd;
    }

    public boolean isPurchaseSupplierAdd() {
        return purchaseSupplierAdd;
    }

    public void setPurchaseSupplierAdd(boolean purchaseSupplierAdd) {
        this.purchaseSupplierAdd = purchaseSupplierAdd;
    }

    public boolean isPurchasePrintList() {
        return purchasePrintList;
    }

    public void setPurchasePrintList(boolean purchasePrintList) {
        this.purchasePrintList = purchasePrintList;
    }

    public boolean isPurchaseAdvancePayment() {
        return purchaseAdvancePayment;
    }

    public void setPurchaseAdvancePayment(boolean purchaseAdvancePayment) {
        this.purchaseAdvancePayment = purchaseAdvancePayment;
    }

    public boolean isPurchaseSavePurchaseOrder() {
        return purchaseSavePurchaseOrder;
    }

    public void setPurchaseSavePurchaseOrder(boolean purchaseSavePurchaseOrder) {
        this.purchaseSavePurchaseOrder = purchaseSavePurchaseOrder;
    }

    public boolean isPurchaseAdvancePaymentList() {
        return purchaseAdvancePaymentList;
    }

    public void setPurchaseAdvancePaymentList(boolean purchaseAdvancePaymentList) {
        this.purchaseAdvancePaymentList = purchaseAdvancePaymentList;
    }

    public boolean isPurchaseInvokePurchaseOrder() {
        return purchaseInvokePurchaseOrder;
    }

    public void setPurchaseInvokePurchaseOrder(boolean purchaseInvokePurchaseOrder) {
        this.purchaseInvokePurchaseOrder = purchaseInvokePurchaseOrder;
    }

    public boolean isPurchaseClearAll() {
        return purchaseClearAll;
    }

    public void setPurchaseClearAll(boolean purchaseClearAll) {
        this.purchaseClearAll = purchaseClearAll;
    }

    public boolean isPurchaseCheckOut() {
        return purchaseCheckOut;
    }

    public void setPurchaseCheckOut(boolean purchaseCheckOut) {
        this.purchaseCheckOut = purchaseCheckOut;
    }

    public boolean isPurchaseReturn() {
        return purchaseReturn;
    }

    public void setPurchaseReturn(boolean purchaseReturn) {
        this.purchaseReturn = purchaseReturn;
    }

    public boolean isPurchaseSupplierPayment() {
        return purchaseSupplierPayment;
    }

    public void setPurchaseSupplierPayment(boolean purchaseSupplierPayment) {
        this.purchaseSupplierPayment = purchaseSupplierPayment;
    }

    public boolean isPurchaseSelfBilled() {
        return purchaseSelfBilled;
    }

    public void setPurchaseSelfBilled(boolean purchaseSelfBilled) {
        this.purchaseSelfBilled = purchaseSelfBilled;
    }

    public boolean isGt() {
        return gt;
    }

    public void setGt(boolean gt) {
        this.gt = gt;
    }

    public boolean isGtExpense() {
        return gtExpense;
    }

    public void setGtExpense(boolean gtExpense) {
        this.gtExpense = gtExpense;
    }

    public boolean isGtReciepts() {
        return gtReciepts;
    }

    public void setGtReciepts(boolean gtReciepts) {
        this.gtReciepts = gtReciepts;
    }

    public boolean isGtJournalEntry() {
        return gtJournalEntry;
    }

    public void setGtJournalEntry(boolean gtJournalEntry) {
        this.gtJournalEntry = gtJournalEntry;
    }

    public boolean isInventory() {
        return inventory;
    }

    public void setInventory(boolean inventory) {
        this.inventory = inventory;
    }

    public boolean isInventoryCategory() {
        return inventoryCategory;
    }

    public void setInventoryCategory(boolean inventoryCategory) {
        this.inventoryCategory = inventoryCategory;
    }

    public boolean isInventoryBrand() {
        return inventoryBrand;
    }

    public void setInventoryBrand(boolean inventoryBrand) {
        this.inventoryBrand = inventoryBrand;
    }

    public boolean isInventoryUOM() {
        return inventoryUOM;
    }

    public void setInventoryUOM(boolean inventoryUOM) {
        this.inventoryUOM = inventoryUOM;
    }

    public boolean isInventoryItem() {
        return inventoryItem;
    }

    public void setInventoryItem(boolean inventoryItem) {
        this.inventoryItem = inventoryItem;
    }

    public boolean isInventorySalesPricing() {
        return inventorySalesPricing;
    }

    public void setInventorySalesPricing(boolean inventorySalesPricing) {
        this.inventorySalesPricing = inventorySalesPricing;
    }

    public boolean isInventoryPurchasePricing() {
        return inventoryPurchasePricing;
    }

    public void setInventoryPurchasePricing(boolean inventoryPurchasePricing) {
        this.inventoryPurchasePricing = inventoryPurchasePricing;
    }

    public boolean isInventoryInventoryLocation() {
        return inventoryInventoryLocation;
    }

    public void setInventoryInventoryLocation(boolean inventoryInventoryLocation) {
        this.inventoryInventoryLocation = inventoryInventoryLocation;
    }

    public boolean isInventoryInventoryMovementType() {
        return inventoryInventoryMovementType;
    }

    public void setInventoryInventoryMovementType(boolean inventoryInventoryMovementType) {
        this.inventoryInventoryMovementType = inventoryInventoryMovementType;
    }

    public boolean isTax() {
        return tax;
    }

    public void setTax(boolean tax) {
        this.tax = tax;
    }

    public boolean isTaxTaxConfiguration() {
        return taxTaxConfiguration;
    }

    public void setTaxTaxConfiguration(boolean taxTaxConfiguration) {
        this.taxTaxConfiguration = taxTaxConfiguration;
    }

    public boolean isTaxGST() {
        return taxGST;
    }

    public void setTaxGST(boolean taxGST) {
        this.taxGST = taxGST;
    }

    public boolean isControlPanel() {
        return controlPanel;
    }

    public void setControlPanel(boolean controlPanel) {
        this.controlPanel = controlPanel;
    }

    public boolean isControlPanelOBofBS() {
        return controlPanelOBofBS;
    }

    public void setControlPanelOBofBS(boolean controlPanelOBofBS) {
        this.controlPanelOBofBS = controlPanelOBofBS;
    }

    public boolean isControlPanelOBofPI() {
        return controlPanelOBofPI;
    }

    public void setControlPanelOBofPI(boolean controlPanelOBofPI) {
        this.controlPanelOBofPI = controlPanelOBofPI;
    }

    public boolean isControlPanelOBofSI() {
        return controlPanelOBofSI;
    }

    public void setControlPanelOBofSI(boolean controlPanelOBofSI) {
        this.controlPanelOBofSI = controlPanelOBofSI;
    }

    public boolean isControlPanelOBofINV() {
        return controlPanelOBofINV;
    }

    public void setControlPanelOBofINV(boolean controlPanelOBofINV) {
        this.controlPanelOBofINV = controlPanelOBofINV;
    }

    public boolean isControlPanelLastYearFigure() {
        return controlPanelLastYearFigure;
    }

    public void setControlPanelLastYearFigure(boolean controlPanelLastYearFigure) {
        this.controlPanelLastYearFigure = controlPanelLastYearFigure;
    }

    public boolean isControlPanelMonthEnd() {
        return controlPanelMonthEnd;
    }

    public void setControlPanelMonthEnd(boolean controlPanelMonthEnd) {
        this.controlPanelMonthEnd = controlPanelMonthEnd;
    }

    public boolean isControlPanelYearEnd() {
        return controlPanelYearEnd;
    }

    public void setControlPanelYearEnd(boolean controlPanelYearEnd) {
        this.controlPanelYearEnd = controlPanelYearEnd;
    }

    public boolean isControlPanelConfigurator() {
        return controlPanelConfigurator;
    }

    public void setControlPanelConfigurator(boolean controlPanelConfigurator) {
        this.controlPanelConfigurator = controlPanelConfigurator;
    }

    public boolean iscPConfiguratorBank() {
        return cPConfiguratorBank;
    }

    public void setcPConfiguratorBank(boolean cPConfiguratorBank) {
        this.cPConfiguratorBank = cPConfiguratorBank;
    }

    public boolean iscPConfiguratorAgent() {
        return cPConfiguratorAgent;
    }

    public void setcPConfiguratorAgent(boolean cPConfiguratorAgent) {
        this.cPConfiguratorAgent = cPConfiguratorAgent;
    }

    public boolean iscPConfiguratorCurrency() {
        return cPConfiguratorCurrency;
    }

    public void setcPConfiguratorCurrency(boolean cPConfiguratorCurrency) {
        this.cPConfiguratorCurrency = cPConfiguratorCurrency;
    }

    public boolean iscPConfiguratorEmployee() {
        return cPConfiguratorEmployee;
    }

    public void setcPConfiguratorEmployee(boolean cPConfiguratorEmployee) {
        this.cPConfiguratorEmployee = cPConfiguratorEmployee;
    }

    public boolean iscPConfiguratorExchangeRate() {
        return cPConfiguratorExchangeRate;
    }

    public void setcPConfiguratorExchangeRate(boolean cPConfiguratorExchangeRate) {
        this.cPConfiguratorExchangeRate = cPConfiguratorExchangeRate;
    }

    public boolean iscPConfiguratorProjectTitle() {
        return cPConfiguratorProjectTitle;
    }

    public void setcPConfiguratorProjectTitle(boolean cPConfiguratorProjectTitle) {
        this.cPConfiguratorProjectTitle = cPConfiguratorProjectTitle;
    }

    public boolean iscPConfiguratorShippingMethod() {
        return cPConfiguratorShippingMethod;
    }

    public void setcPConfiguratorShippingMethod(boolean cPConfiguratorShippingMethod) {
        this.cPConfiguratorShippingMethod = cPConfiguratorShippingMethod;
    }

    public boolean iscPConfiguratorEmailserver() {
        return cPConfiguratorEmailserver;
    }

    public void setcPConfiguratorEmailserver(boolean cPConfiguratorEmailserver) {
        this.cPConfiguratorEmailserver = cPConfiguratorEmailserver;
    }

    public boolean iscPConfiguratorPaymentMethod() {
        return cPConfiguratorPaymentMethod;
    }

    public void setcPConfiguratorPaymentMethod(boolean cPConfiguratorPaymentMethod) {
        this.cPConfiguratorPaymentMethod = cPConfiguratorPaymentMethod;
    }

    public boolean iscPConfiguratorConfiguration() {
        return cPConfiguratorConfiguration;
    }

    public void setcPConfiguratorConfiguration(boolean cPConfiguratorConfiguration) {
        this.cPConfiguratorConfiguration = cPConfiguratorConfiguration;
    }

    public boolean isControlPanelCompanySetup() {
        return controlPanelCompanySetup;
    }

    public void setControlPanelCompanySetup(boolean controlPanelCompanySetup) {
        this.controlPanelCompanySetup = controlPanelCompanySetup;
    }

    public boolean iscPCompanySetupCI() {
        return cPCompanySetupCI;
    }

    public void setcPCompanySetupCI(boolean cPCompanySetupCI) {
        this.cPCompanySetupCI = cPCompanySetupCI;
    }

    public boolean iscPCompanySetupFY() {
        return cPCompanySetupFY;
    }

    public void setcPCompanySetupFY(boolean cPCompanySetupFY) {
        this.cPCompanySetupFY = cPCompanySetupFY;
    }

    public boolean iscPCompanySetupCA() {
        return cPCompanySetupCA;
    }

    public void setcPCompanySetupCA(boolean cPCompanySetupCA) {
        this.cPCompanySetupCA = cPCompanySetupCA;
    }

    public boolean iscPCompanySetupHSN() {
        return cPCompanySetupHSN;
    }

    public void setcPCompanySetupHSN(boolean cPCompanySetupHSN) {
        this.cPCompanySetupHSN = cPCompanySetupHSN;
    }

    public boolean isControlPanelUAS() {
        return controlPanelUAS;
    }

    public void setControlPanelUAS(boolean controlPanelUAS) {
        this.controlPanelUAS = controlPanelUAS;
    }

    public boolean isControlPanelUASuas() {
        return controlPanelUASuas;
    }

    public void setControlPanelUASuas(boolean controlPanelUASuas) {
        this.controlPanelUASuas = controlPanelUASuas;
    }

    public boolean isControlPanelUASat() {
        return controlPanelUASat;
    }

    public void setControlPanelUASat(boolean controlPanelUASat) {
        this.controlPanelUASat = controlPanelUASat;
    }

    public boolean isControlPanelAccountMaintenance() {
        return controlPanelAccountMaintenance;
    }

    public void setControlPanelAccountMaintenance(boolean controlPanelAccountMaintenance) {
        this.controlPanelAccountMaintenance = controlPanelAccountMaintenance;
    }

    public boolean isControlPanelAMag() {
        return controlPanelAMag;
    }

    public void setControlPanelAMag(boolean controlPanelAMag) {
        this.controlPanelAMag = controlPanelAMag;
    }

    public boolean isControlPanelAMcoa() {
        return controlPanelAMcoa;
    }

    public void setControlPanelAMcoa(boolean controlPanelAMcoa) {
        this.controlPanelAMcoa = controlPanelAMcoa;
    }

    public boolean isControlPanelAMbudget() {
        return controlPanelAMbudget;
    }

    public void setControlPanelAMbudget(boolean controlPanelAMbudget) {
        this.controlPanelAMbudget = controlPanelAMbudget;
    }

    public boolean isControlPanelDisplaySetup() {
        return controlPanelDisplaySetup;
    }

    public void setControlPanelDisplaySetup(boolean controlPanelDisplaySetup) {
        this.controlPanelDisplaySetup = controlPanelDisplaySetup;
    }

    public boolean isControlPanelDSfs() {
        return controlPanelDSfs;
    }

    public void setControlPanelDSfs(boolean controlPanelDSfs) {
        this.controlPanelDSfs = controlPanelDSfs;
    }

    public boolean isControlPanelDSlayout() {
        return controlPanelDSlayout;
    }

    public void setControlPanelDSlayout(boolean controlPanelDSlayout) {
        this.controlPanelDSlayout = controlPanelDSlayout;
    }

    public boolean isControlPanelDStermsCond() {
        return controlPanelDStermsCond;
    }

    public void setControlPanelDStermsCond(boolean controlPanelDStermsCond) {
        this.controlPanelDStermsCond = controlPanelDStermsCond;
    }

    public boolean isContact() {
        return contact;
    }

    public void setContact(boolean contact) {
        this.contact = contact;
    }

    public boolean isContactSupplier() {
        return contactSupplier;
    }

    public void setContactSupplier(boolean contactSupplier) {
        this.contactSupplier = contactSupplier;
    }

    public boolean isContactCustomer() {
        return contactCustomer;
    }

    public void setContactCustomer(boolean contactCustomer) {
        this.contactCustomer = contactCustomer;
    }

    public boolean isContactContacts() {
        return contactContacts;
    }

    public void setContactContacts(boolean contactContacts) {
        this.contactContacts = contactContacts;
    }

    public boolean isReport() {
        return report;
    }

    public void setReport(boolean report) {
        this.report = report;
    }

    public boolean isReportPurchase() {
        return reportPurchase;
    }

    public void setReportPurchase(boolean reportPurchase) {
        this.reportPurchase = reportPurchase;
    }

    public boolean isReportPurchaseSSR() {
        return reportPurchaseSSR;
    }

    public void setReportPurchaseSSR(boolean reportPurchaseSSR) {
        this.reportPurchaseSSR = reportPurchaseSSR;
    }

    public boolean isReportPurchasePOL() {
        return reportPurchasePOL;
    }

    public void setReportPurchasePOL(boolean reportPurchasePOL) {
        this.reportPurchasePOL = reportPurchasePOL;
    }

    public boolean isReportPurchasePIL() {
        return reportPurchasePIL;
    }

    public void setReportPurchasePIL(boolean reportPurchasePIL) {
        this.reportPurchasePIL = reportPurchasePIL;
    }

    public boolean isReportPurchasePRL() {
        return reportPurchasePRL;
    }

    public void setReportPurchasePRL(boolean reportPurchasePRL) {
        this.reportPurchasePRL = reportPurchasePRL;
    }

    public boolean isReportPurchaseSP() {
        return reportPurchaseSP;
    }

    public void setReportPurchaseSP(boolean reportPurchaseSP) {
        this.reportPurchaseSP = reportPurchaseSP;
    }

    public boolean isReportPurchasePA() {
        return reportPurchasePA;
    }

    public void setReportPurchasePA(boolean reportPurchasePA) {
        this.reportPurchasePA = reportPurchasePA;
    }

    public boolean isReportSale() {
        return reportSale;
    }

    public void setReportSale(boolean reportSale) {
        this.reportSale = reportSale;
    }

    public boolean isReportSaleSOL() {
        return reportSaleSOL;
    }

    public void setReportSaleSOL(boolean reportSaleSOL) {
        this.reportSaleSOL = reportSaleSOL;
    }

    public boolean isReportSaleICL() {
        return reportSaleICL;
    }

    public void setReportSaleICL(boolean reportSaleICL) {
        this.reportSaleICL = reportSaleICL;
    }

    public boolean isReportSaleSIL() {
        return reportSaleSIL;
    }

    public void setReportSaleSIL(boolean reportSaleSIL) {
        this.reportSaleSIL = reportSaleSIL;
    }

    public boolean isReportSaleSRL() {
        return reportSaleSRL;
    }

    public void setReportSaleSRL(boolean reportSaleSRL) {
        this.reportSaleSRL = reportSaleSRL;
    }

    public boolean isReportSalePR() {
        return reportSalePR;
    }

    public void setReportSalePR(boolean reportSalePR) {
        this.reportSalePR = reportSalePR;
    }

    public boolean isReportSalePL() {
        return reportSalePL;
    }

    public void setReportSalePL(boolean reportSalePL) {
        this.reportSalePL = reportSalePL;
    }

    public boolean isReportSaleCA() {
        return reportSaleCA;
    }

    public void setReportSaleCA(boolean reportSaleCA) {
        this.reportSaleCA = reportSaleCA;
    }

    public boolean isReportSaleCP() {
        return reportSaleCP;
    }

    public void setReportSaleCP(boolean reportSaleCP) {
        this.reportSaleCP = reportSaleCP;
    }

    public boolean isReportSaleSA() {
        return reportSaleSA;
    }

    public void setReportSaleSA(boolean reportSaleSA) {
        this.reportSaleSA = reportSaleSA;
    }

    public boolean isReportInventory() {
        return reportInventory;
    }

    public void setReportInventory(boolean reportInventory) {
        this.reportInventory = reportInventory;
    }

    public boolean isReportInventoryIVR() {
        return reportInventoryIVR;
    }

    public void setReportInventoryIVR(boolean reportInventoryIVR) {
        this.reportInventoryIVR = reportInventoryIVR;
    }

    public boolean isReportInventoryISR() {
        return reportInventoryISR;
    }

    public void setReportInventoryISR(boolean reportInventoryISR) {
        this.reportInventoryISR = reportInventoryISR;
    }

    public boolean isReportInventoryilSCR() {
        return reportInventoryilSCR;
    }

    public void setReportInventoryilSCR(boolean reportInventoryilSCR) {
        this.reportInventoryilSCR = reportInventoryilSCR;
    }

    public boolean isReportAnalysis() {
        return reportAnalysis;
    }

    public void setReportAnalysis(boolean reportAnalysis) {
        this.reportAnalysis = reportAnalysis;
    }

    public boolean isReportAnalysisTopVendor() {
        return reportAnalysisTopVendor;
    }

    public void setReportAnalysisTopVendor(boolean reportAnalysisTopVendor) {
        this.reportAnalysisTopVendor = reportAnalysisTopVendor;
    }

    public boolean isReportAnalysisTopItem() {
        return reportAnalysisTopItem;
    }

    public void setReportAnalysisTopItem(boolean reportAnalysisTopItem) {
        this.reportAnalysisTopItem = reportAnalysisTopItem;
    }

    public boolean isReportAnalysisTopCustomer() {
        return reportAnalysisTopCustomer;
    }

    public void setReportAnalysisTopCustomer(boolean reportAnalysisTopCustomer) {
        this.reportAnalysisTopCustomer = reportAnalysisTopCustomer;
    }

    public boolean isReportFinancialStatement() {
        return reportFinancialStatement;
    }

    public void setReportFinancialStatement(boolean reportFinancialStatement) {
        this.reportFinancialStatement = reportFinancialStatement;
    }

    public boolean isReportFStatementPL() {
        return reportFStatementPL;
    }

    public void setReportFStatementPL(boolean reportFStatementPL) {
        this.reportFStatementPL = reportFStatementPL;
    }

    public boolean isReportFStatementBS() {
        return reportFStatementBS;
    }

    public void setReportFStatementBS(boolean reportFStatementBS) {
        this.reportFStatementBS = reportFStatementBS;
    }

    public boolean isReportFStatementTB() {
        return reportFStatementTB;
    }

    public void setReportFStatementTB(boolean reportFStatementTB) {
        this.reportFStatementTB = reportFStatementTB;
    }

    public boolean isReportFStatementGL() {
        return reportFStatementGL;
    }

    public void setReportFStatementGL(boolean reportFStatementGL) {
        this.reportFStatementGL = reportFStatementGL;
    }

    public boolean isReportFStatementVL() {
        return reportFStatementVL;
    }

    public void setReportFStatementVL(boolean reportFStatementVL) {
        this.reportFStatementVL = reportFStatementVL;
    }

    public boolean isReportFStatementGJ() {
        return reportFStatementGJ;
    }

    public void setReportFStatementGJ(boolean reportFStatementGJ) {
        this.reportFStatementGJ = reportFStatementGJ;
    }

    public boolean isReportBankRecon() {
        return reportBankRecon;
    }

    public void setReportBankRecon(boolean reportBankRecon) {
        this.reportBankRecon = reportBankRecon;
    }

    public boolean isReportBankReconRatio() {
        return reportBankReconRatio;
    }

    public void setReportBankReconRatio(boolean reportBankReconRatio) {
        this.reportBankReconRatio = reportBankReconRatio;
    }
}
