package in.hiaccounts.hinext.controlpanel.model.configurator_bank.versioncontrol;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by shrinath on 11/28/2017.
 */

public class VersionControlPermissions implements Serializable {

    @SerializedName("message")
    @Expose
    private Object message;
    @SerializedName("status")
    @Expose
    private Long status;
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("projectModuleId")
    @Expose
    private String projectModuleId;
    @SerializedName("dashboard")
    @Expose
    private Boolean dashboard;
    @SerializedName("dashboardDateTime")
    @Expose
    private Boolean dashboardDateTime;
    @SerializedName("dashboardCashInHand")
    @Expose
    private Boolean dashboardCashInHand;
    @SerializedName("dashboardCashInBank")
    @Expose
    private Boolean dashboardCashInBank;
    @SerializedName("dashboardTotalReceivable")
    @Expose
    private Boolean dashboardTotalReceivable;
    @SerializedName("dashboardTotalPayable")
    @Expose
    private Boolean dashboardTotalPayable;
    @SerializedName("dashboardExtra1")
    @Expose
    private Boolean dashboardExtra1;
    @SerializedName("dashboardExtra2")
    @Expose
    private Boolean dashboardExtra2;
    @SerializedName("sales")
    @Expose
    private Boolean sales;
    @SerializedName("salesItemAdd")
    @Expose
    private Boolean salesItemAdd;
    @SerializedName("salesCustomerAdd")
    @Expose
    private Boolean salesCustomerAdd;
    @SerializedName("salesSavesDraft")
    @Expose
    private Boolean salesSavesDraft;
    @SerializedName("salesClearDraft")
    @Expose
    private Boolean salesClearDraft;
    @SerializedName("salesPrintList")
    @Expose
    private Boolean salesPrintList;
    @SerializedName("salesAdvancePayment")
    @Expose
    private Boolean salesAdvancePayment;
    @SerializedName("salesSaveSalesOrder")
    @Expose
    private Boolean salesSaveSalesOrder;
    @SerializedName("salesAdvancePaymentList")
    @Expose
    private Boolean salesAdvancePaymentList;
    @SerializedName("salesInvokeSalesOrder")
    @Expose
    private Boolean salesInvokeSalesOrder;
    @SerializedName("salesClearAll")
    @Expose
    private Boolean salesClearAll;
    @SerializedName("salesCheckOut")
    @Expose
    private Boolean salesCheckOut;
    @SerializedName("salesReturn")
    @Expose
    private Boolean salesReturn;
    @SerializedName("salesRecievePayment")
    @Expose
    private Boolean salesRecievePayment;
    @SerializedName("salesInvokeDraft")
    @Expose
    private Boolean salesInvokeDraft;
    @SerializedName("purchase")
    @Expose
    private Boolean purchase;
    @SerializedName("purchaseItemAdd")
    @Expose
    private Boolean purchaseItemAdd;
    @SerializedName("purchaseSupplierAdd")
    @Expose
    private Boolean purchaseSupplierAdd;
    @SerializedName("purchasePrintList")
    @Expose
    private Boolean purchasePrintList;
    @SerializedName("purchaseAdvancePayment")
    @Expose
    private Boolean purchaseAdvancePayment;
    @SerializedName("purchaseSavePurchaseOrder")
    @Expose
    private Boolean purchaseSavePurchaseOrder;
    @SerializedName("purchaseAdvancePaymentList")
    @Expose
    private Boolean purchaseAdvancePaymentList;
    @SerializedName("purchaseInvokePurchaseOrder")
    @Expose
    private Boolean purchaseInvokePurchaseOrder;
    @SerializedName("purchaseClearAll")
    @Expose
    private Boolean purchaseClearAll;
    @SerializedName("purchaseCheckOut")
    @Expose
    private Boolean purchaseCheckOut;
    @SerializedName("purchaseReturn")
    @Expose
    private Boolean purchaseReturn;
    @SerializedName("purchaseSupplierPayment")
    @Expose
    private Boolean purchaseSupplierPayment;
    @SerializedName("purchaseSelfBilled")
    @Expose
    private Boolean purchaseSelfBilled;
    @SerializedName("gt")
    @Expose
    private Boolean gt;
    @SerializedName("gtExpense")
    @Expose
    private Boolean gtExpense;
    @SerializedName("gtReciepts")
    @Expose
    private Boolean gtReciepts;
    @SerializedName("gtJournalEntry")
    @Expose
    private Boolean gtJournalEntry;
    @SerializedName("inventory")
    @Expose
    private Boolean inventory;
    @SerializedName("inventoryCategory")
    @Expose
    private Boolean inventoryCategory;
    @SerializedName("inventoryBrand")
    @Expose
    private Boolean inventoryBrand;
    @SerializedName("inventoryUOM")
    @Expose
    private Boolean inventoryUOM;
    @SerializedName("inventoryItem")
    @Expose
    private Boolean inventoryItem;
    @SerializedName("inventorySalesPricing")
    @Expose
    private Boolean inventorySalesPricing;
    @SerializedName("inventoryPurchasePricing")
    @Expose
    private Boolean inventoryPurchasePricing;
    @SerializedName("inventoryInventoryLocation")
    @Expose
    private Boolean inventoryInventoryLocation;
    @SerializedName("inventoryInventoryMovementType")
    @Expose
    private Boolean inventoryInventoryMovementType;
    @SerializedName("tax")
    @Expose
    private Boolean tax;
    @SerializedName("taxTaxConfiguration")
    @Expose
    private Boolean taxTaxConfiguration;
    @SerializedName("taxGST")
    @Expose
    private Boolean taxGST;
    @SerializedName("controlPanel")
    @Expose
    private Boolean controlPanel;
    @SerializedName("controlPanelOBofBS")
    @Expose
    private Boolean controlPanelOBofBS;
    @SerializedName("controlPanelOBofPI")
    @Expose
    private Boolean controlPanelOBofPI;
    @SerializedName("controlPanelOBofSI")
    @Expose
    private Boolean controlPanelOBofSI;
    @SerializedName("controlPanelOBofINV")
    @Expose
    private Boolean controlPanelOBofINV;
    @SerializedName("controlPanelLastYearFigure")
    @Expose
    private Boolean controlPanelLastYearFigure;
    @SerializedName("controlPanelMonthEnd")
    @Expose
    private Boolean controlPanelMonthEnd;
    @SerializedName("controlPanelYearEnd")
    @Expose
    private Boolean controlPanelYearEnd;
    @SerializedName("controlPanelConfigurator")
    @Expose
    private Boolean controlPanelConfigurator;
    @SerializedName("cPConfiguratorBank")
    @Expose
    private Boolean cPConfiguratorBank;
    @SerializedName("cPConfiguratorAgent")
    @Expose
    private Boolean cPConfiguratorAgent;
    @SerializedName("cPConfiguratorCurrency")
    @Expose
    private Boolean cPConfiguratorCurrency;
    @SerializedName("cPConfiguratorEmployee")
    @Expose
    private Boolean cPConfiguratorEmployee;
    @SerializedName("cPConfiguratorExchangeRate")
    @Expose
    private Boolean cPConfiguratorExchangeRate;
    @SerializedName("cPConfiguratorProjectTitle")
    @Expose
    private Boolean cPConfiguratorProjectTitle;
    @SerializedName("cPConfiguratorShippingMethod")
    @Expose
    private Boolean cPConfiguratorShippingMethod;
    @SerializedName("cPConfiguratorEmailserver")
    @Expose
    private Boolean cPConfiguratorEmailserver;
    @SerializedName("cPConfiguratorPaymentMethod")
    @Expose
    private Boolean cPConfiguratorPaymentMethod;
    @SerializedName("cPConfiguratorConfiguration")
    @Expose
    private Boolean cPConfiguratorConfiguration;
    @SerializedName("controlPanelCompanySetup")
    @Expose
    private Boolean controlPanelCompanySetup;
    @SerializedName("cPCompanySetupCI")
    @Expose
    private Boolean cPCompanySetupCI;
    @SerializedName("cPCompanySetupFY")
    @Expose
    private Boolean cPCompanySetupFY;
    @SerializedName("cPCompanySetupCA")
    @Expose
    private Boolean cPCompanySetupCA;
    @SerializedName("cPCompanySetupHSN")
    @Expose
    private Boolean cPCompanySetupHSN;
    @SerializedName("controlPanelUAS")
    @Expose
    private Boolean controlPanelUAS;
    @SerializedName("controlPanelUASuas")
    @Expose
    private Boolean controlPanelUASuas;
    @SerializedName("controlPanelUASat")
    @Expose
    private Boolean controlPanelUASat;
    @SerializedName("controlPanelAccountMaintenance")
    @Expose
    private Boolean controlPanelAccountMaintenance;
    @SerializedName("controlPanelAMag")
    @Expose
    private Boolean controlPanelAMag;
    @SerializedName("controlPanelAMcoa")
    @Expose
    private Boolean controlPanelAMcoa;
    @SerializedName("controlPanelAMbudget")
    @Expose
    private Boolean controlPanelAMbudget;
    @SerializedName("controlPanelDisplaySetup")
    @Expose
    private Boolean controlPanelDisplaySetup;
    @SerializedName("controlPanelDSfs")
    @Expose
    private Boolean controlPanelDSfs;
    @SerializedName("controlPanelDSlayout")
    @Expose
    private Boolean controlPanelDSlayout;
    @SerializedName("controlPanelDStermsCond")
    @Expose
    private Boolean controlPanelDStermsCond;
    @SerializedName("contact")
    @Expose
    private Boolean contact;
    @SerializedName("contactSupplier")
    @Expose
    private Boolean contactSupplier;
    @SerializedName("contactCustomer")
    @Expose
    private Boolean contactCustomer;
    @SerializedName("contactContacts")
    @Expose
    private Boolean contactContacts;
    @SerializedName("report")
    @Expose
    private Boolean report;
    @SerializedName("reportPurchase")
    @Expose
    private Boolean reportPurchase;
    @SerializedName("reportPurchaseSSR")
    @Expose
    private Boolean reportPurchaseSSR;
    @SerializedName("reportPurchasePOL")
    @Expose
    private Boolean reportPurchasePOL;
    @SerializedName("reportPurchasePIL")
    @Expose
    private Boolean reportPurchasePIL;
    @SerializedName("reportPurchasePRL")
    @Expose
    private Boolean reportPurchasePRL;
    @SerializedName("reportPurchaseSP")
    @Expose
    private Boolean reportPurchaseSP;
    @SerializedName("reportPurchasePA")
    @Expose
    private Boolean reportPurchasePA;
    @SerializedName("reportSale")
    @Expose
    private Boolean reportSale;
    @SerializedName("reportSaleSOL")
    @Expose
    private Boolean reportSaleSOL;
    @SerializedName("reportSaleICL")
    @Expose
    private Boolean reportSaleICL;
    @SerializedName("reportSaleSIL")
    @Expose
    private Boolean reportSaleSIL;
    @SerializedName("reportSaleSRL")
    @Expose
    private Boolean reportSaleSRL;
    @SerializedName("reportSalePR")
    @Expose
    private Boolean reportSalePR;
    @SerializedName("reportSalePL")
    @Expose
    private Boolean reportSalePL;
    @SerializedName("reportSaleCA")
    @Expose
    private Boolean reportSaleCA;
    @SerializedName("reportSaleCP")
    @Expose
    private Boolean reportSaleCP;
    @SerializedName("reportSaleSA")
    @Expose
    private Boolean reportSaleSA;
    @SerializedName("reportInventory")
    @Expose
    private Boolean reportInventory;
    @SerializedName("reportInventoryIVR")
    @Expose
    private Boolean reportInventoryIVR;
    @SerializedName("reportInventoryISR")
    @Expose
    private Boolean reportInventoryISR;
    @SerializedName("reportInventoryilSCR")
    @Expose
    private Boolean reportInventoryilSCR;
    @SerializedName("reportAnalysis")
    @Expose
    private Boolean reportAnalysis;
    @SerializedName("reportAnalysisTopVendor")
    @Expose
    private Boolean reportAnalysisTopVendor;
    @SerializedName("reportAnalysisTopItem")
    @Expose
    private Boolean reportAnalysisTopItem;
    @SerializedName("reportAnalysisTopCustomer")
    @Expose
    private Boolean reportAnalysisTopCustomer;
    @SerializedName("reportFinancialStatement")
    @Expose
    private Boolean reportFinancialStatement;
    @SerializedName("reportFStatementPL")
    @Expose
    private Boolean reportFStatementPL;
    @SerializedName("reportFStatementBS")
    @Expose
    private Boolean reportFStatementBS;
    @SerializedName("reportFStatementTB")
    @Expose
    private Boolean reportFStatementTB;
    @SerializedName("reportFStatementGL")
    @Expose
    private Boolean reportFStatementGL;
    @SerializedName("reportFStatementVL")
    @Expose
    private Boolean reportFStatementVL;
    @SerializedName("reportFStatementGJ")
    @Expose
    private Boolean reportFStatementGJ;
    @SerializedName("reportBankRecon")
    @Expose
    private Boolean reportBankRecon;
    @SerializedName("reportBankReconRatio")
    @Expose
    private Boolean reportBankReconRatio;

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectModuleId() {
        return projectModuleId;
    }

    public void setProjectModuleId(String projectModuleId) {
        this.projectModuleId = projectModuleId;
    }

    public Boolean getDashboard() {
        return dashboard;
    }

    public void setDashboard(Boolean dashboard) {
        this.dashboard = dashboard;
    }

    public Boolean getDashboardDateTime() {
        return dashboardDateTime;
    }

    public void setDashboardDateTime(Boolean dashboardDateTime) {
        this.dashboardDateTime = dashboardDateTime;
    }

    public Boolean getDashboardCashInHand() {
        return dashboardCashInHand;
    }

    public void setDashboardCashInHand(Boolean dashboardCashInHand) {
        this.dashboardCashInHand = dashboardCashInHand;
    }

    public Boolean getDashboardCashInBank() {
        return dashboardCashInBank;
    }

    public void setDashboardCashInBank(Boolean dashboardCashInBank) {
        this.dashboardCashInBank = dashboardCashInBank;
    }

    public Boolean getDashboardTotalReceivable() {
        return dashboardTotalReceivable;
    }

    public void setDashboardTotalReceivable(Boolean dashboardTotalReceivable) {
        this.dashboardTotalReceivable = dashboardTotalReceivable;
    }

    public Boolean getDashboardTotalPayable() {
        return dashboardTotalPayable;
    }

    public void setDashboardTotalPayable(Boolean dashboardTotalPayable) {
        this.dashboardTotalPayable = dashboardTotalPayable;
    }

    public Boolean getDashboardExtra1() {
        return dashboardExtra1;
    }

    public void setDashboardExtra1(Boolean dashboardExtra1) {
        this.dashboardExtra1 = dashboardExtra1;
    }

    public Boolean getDashboardExtra2() {
        return dashboardExtra2;
    }

    public void setDashboardExtra2(Boolean dashboardExtra2) {
        this.dashboardExtra2 = dashboardExtra2;
    }

    public Boolean getSales() {
        return sales;
    }

    public void setSales(Boolean sales) {
        this.sales = sales;
    }

    public Boolean getSalesItemAdd() {
        return salesItemAdd;
    }

    public void setSalesItemAdd(Boolean salesItemAdd) {
        this.salesItemAdd = salesItemAdd;
    }

    public Boolean getSalesCustomerAdd() {
        return salesCustomerAdd;
    }

    public void setSalesCustomerAdd(Boolean salesCustomerAdd) {
        this.salesCustomerAdd = salesCustomerAdd;
    }

    public Boolean getSalesSavesDraft() {
        return salesSavesDraft;
    }

    public void setSalesSavesDraft(Boolean salesSavesDraft) {
        this.salesSavesDraft = salesSavesDraft;
    }

    public Boolean getSalesClearDraft() {
        return salesClearDraft;
    }

    public void setSalesClearDraft(Boolean salesClearDraft) {
        this.salesClearDraft = salesClearDraft;
    }

    public Boolean getSalesPrintList() {
        return salesPrintList;
    }

    public void setSalesPrintList(Boolean salesPrintList) {
        this.salesPrintList = salesPrintList;
    }

    public Boolean getSalesAdvancePayment() {
        return salesAdvancePayment;
    }

    public void setSalesAdvancePayment(Boolean salesAdvancePayment) {
        this.salesAdvancePayment = salesAdvancePayment;
    }

    public Boolean getSalesSaveSalesOrder() {
        return salesSaveSalesOrder;
    }

    public void setSalesSaveSalesOrder(Boolean salesSaveSalesOrder) {
        this.salesSaveSalesOrder = salesSaveSalesOrder;
    }

    public Boolean getSalesAdvancePaymentList() {
        return salesAdvancePaymentList;
    }

    public void setSalesAdvancePaymentList(Boolean salesAdvancePaymentList) {
        this.salesAdvancePaymentList = salesAdvancePaymentList;
    }

    public Boolean getSalesInvokeSalesOrder() {
        return salesInvokeSalesOrder;
    }

    public void setSalesInvokeSalesOrder(Boolean salesInvokeSalesOrder) {
        this.salesInvokeSalesOrder = salesInvokeSalesOrder;
    }

    public Boolean getSalesClearAll() {
        return salesClearAll;
    }

    public void setSalesClearAll(Boolean salesClearAll) {
        this.salesClearAll = salesClearAll;
    }

    public Boolean getSalesCheckOut() {
        return salesCheckOut;
    }

    public void setSalesCheckOut(Boolean salesCheckOut) {
        this.salesCheckOut = salesCheckOut;
    }

    public Boolean getSalesReturn() {
        return salesReturn;
    }

    public void setSalesReturn(Boolean salesReturn) {
        this.salesReturn = salesReturn;
    }

    public Boolean getSalesRecievePayment() {
        return salesRecievePayment;
    }

    public void setSalesRecievePayment(Boolean salesRecievePayment) {
        this.salesRecievePayment = salesRecievePayment;
    }

    public Boolean getSalesInvokeDraft() {
        return salesInvokeDraft;
    }

    public void setSalesInvokeDraft(Boolean salesInvokeDraft) {
        this.salesInvokeDraft = salesInvokeDraft;
    }

    public Boolean getPurchase() {
        return purchase;
    }

    public void setPurchase(Boolean purchase) {
        this.purchase = purchase;
    }

    public Boolean getPurchaseItemAdd() {
        return purchaseItemAdd;
    }

    public void setPurchaseItemAdd(Boolean purchaseItemAdd) {
        this.purchaseItemAdd = purchaseItemAdd;
    }

    public Boolean getPurchaseSupplierAdd() {
        return purchaseSupplierAdd;
    }

    public void setPurchaseSupplierAdd(Boolean purchaseSupplierAdd) {
        this.purchaseSupplierAdd = purchaseSupplierAdd;
    }

    public Boolean getPurchasePrintList() {
        return purchasePrintList;
    }

    public void setPurchasePrintList(Boolean purchasePrintList) {
        this.purchasePrintList = purchasePrintList;
    }

    public Boolean getPurchaseAdvancePayment() {
        return purchaseAdvancePayment;
    }

    public void setPurchaseAdvancePayment(Boolean purchaseAdvancePayment) {
        this.purchaseAdvancePayment = purchaseAdvancePayment;
    }

    public Boolean getPurchaseSavePurchaseOrder() {
        return purchaseSavePurchaseOrder;
    }

    public void setPurchaseSavePurchaseOrder(Boolean purchaseSavePurchaseOrder) {
        this.purchaseSavePurchaseOrder = purchaseSavePurchaseOrder;
    }

    public Boolean getPurchaseAdvancePaymentList() {
        return purchaseAdvancePaymentList;
    }

    public void setPurchaseAdvancePaymentList(Boolean purchaseAdvancePaymentList) {
        this.purchaseAdvancePaymentList = purchaseAdvancePaymentList;
    }

    public Boolean getPurchaseInvokePurchaseOrder() {
        return purchaseInvokePurchaseOrder;
    }

    public void setPurchaseInvokePurchaseOrder(Boolean purchaseInvokePurchaseOrder) {
        this.purchaseInvokePurchaseOrder = purchaseInvokePurchaseOrder;
    }

    public Boolean getPurchaseClearAll() {
        return purchaseClearAll;
    }

    public void setPurchaseClearAll(Boolean purchaseClearAll) {
        this.purchaseClearAll = purchaseClearAll;
    }

    public Boolean getPurchaseCheckOut() {
        return purchaseCheckOut;
    }

    public void setPurchaseCheckOut(Boolean purchaseCheckOut) {
        this.purchaseCheckOut = purchaseCheckOut;
    }

    public Boolean getPurchaseReturn() {
        return purchaseReturn;
    }

    public void setPurchaseReturn(Boolean purchaseReturn) {
        this.purchaseReturn = purchaseReturn;
    }

    public Boolean getPurchaseSupplierPayment() {
        return purchaseSupplierPayment;
    }

    public void setPurchaseSupplierPayment(Boolean purchaseSupplierPayment) {
        this.purchaseSupplierPayment = purchaseSupplierPayment;
    }

    public Boolean getPurchaseSelfBilled() {
        return purchaseSelfBilled;
    }

    public void setPurchaseSelfBilled(Boolean purchaseSelfBilled) {
        this.purchaseSelfBilled = purchaseSelfBilled;
    }

    public Boolean getGt() {
        return gt;
    }

    public void setGt(Boolean gt) {
        this.gt = gt;
    }

    public Boolean getGtExpense() {
        return gtExpense;
    }

    public void setGtExpense(Boolean gtExpense) {
        this.gtExpense = gtExpense;
    }

    public Boolean getGtReciepts() {
        return gtReciepts;
    }

    public void setGtReciepts(Boolean gtReciepts) {
        this.gtReciepts = gtReciepts;
    }

    public Boolean getGtJournalEntry() {
        return gtJournalEntry;
    }

    public void setGtJournalEntry(Boolean gtJournalEntry) {
        this.gtJournalEntry = gtJournalEntry;
    }

    public Boolean getInventory() {
        return inventory;
    }

    public void setInventory(Boolean inventory) {
        this.inventory = inventory;
    }

    public Boolean getInventoryCategory() {
        return inventoryCategory;
    }

    public void setInventoryCategory(Boolean inventoryCategory) {
        this.inventoryCategory = inventoryCategory;
    }

    public Boolean getInventoryBrand() {
        return inventoryBrand;
    }

    public void setInventoryBrand(Boolean inventoryBrand) {
        this.inventoryBrand = inventoryBrand;
    }

    public Boolean getInventoryUOM() {
        return inventoryUOM;
    }

    public void setInventoryUOM(Boolean inventoryUOM) {
        this.inventoryUOM = inventoryUOM;
    }

    public Boolean getInventoryItem() {
        return inventoryItem;
    }

    public void setInventoryItem(Boolean inventoryItem) {
        this.inventoryItem = inventoryItem;
    }

    public Boolean getInventorySalesPricing() {
        return inventorySalesPricing;
    }

    public void setInventorySalesPricing(Boolean inventorySalesPricing) {
        this.inventorySalesPricing = inventorySalesPricing;
    }

    public Boolean getInventoryPurchasePricing() {
        return inventoryPurchasePricing;
    }

    public void setInventoryPurchasePricing(Boolean inventoryPurchasePricing) {
        this.inventoryPurchasePricing = inventoryPurchasePricing;
    }

    public Boolean getInventoryInventoryLocation() {
        return inventoryInventoryLocation;
    }

    public void setInventoryInventoryLocation(Boolean inventoryInventoryLocation) {
        this.inventoryInventoryLocation = inventoryInventoryLocation;
    }

    public Boolean getInventoryInventoryMovementType() {
        return inventoryInventoryMovementType;
    }

    public void setInventoryInventoryMovementType(Boolean inventoryInventoryMovementType) {
        this.inventoryInventoryMovementType = inventoryInventoryMovementType;
    }

    public Boolean getTax() {
        return tax;
    }

    public void setTax(Boolean tax) {
        this.tax = tax;
    }

    public Boolean getTaxTaxConfiguration() {
        return taxTaxConfiguration;
    }

    public void setTaxTaxConfiguration(Boolean taxTaxConfiguration) {
        this.taxTaxConfiguration = taxTaxConfiguration;
    }

    public Boolean getTaxGST() {
        return taxGST;
    }

    public void setTaxGST(Boolean taxGST) {
        this.taxGST = taxGST;
    }

    public Boolean getControlPanel() {
        return controlPanel;
    }

    public void setControlPanel(Boolean controlPanel) {
        this.controlPanel = controlPanel;
    }

    public Boolean getControlPanelOBofBS() {
        return controlPanelOBofBS;
    }

    public void setControlPanelOBofBS(Boolean controlPanelOBofBS) {
        this.controlPanelOBofBS = controlPanelOBofBS;
    }

    public Boolean getControlPanelOBofPI() {
        return controlPanelOBofPI;
    }

    public void setControlPanelOBofPI(Boolean controlPanelOBofPI) {
        this.controlPanelOBofPI = controlPanelOBofPI;
    }

    public Boolean getControlPanelOBofSI() {
        return controlPanelOBofSI;
    }

    public void setControlPanelOBofSI(Boolean controlPanelOBofSI) {
        this.controlPanelOBofSI = controlPanelOBofSI;
    }

    public Boolean getControlPanelOBofINV() {
        return controlPanelOBofINV;
    }

    public void setControlPanelOBofINV(Boolean controlPanelOBofINV) {
        this.controlPanelOBofINV = controlPanelOBofINV;
    }

    public Boolean getControlPanelLastYearFigure() {
        return controlPanelLastYearFigure;
    }

    public void setControlPanelLastYearFigure(Boolean controlPanelLastYearFigure) {
        this.controlPanelLastYearFigure = controlPanelLastYearFigure;
    }

    public Boolean getControlPanelMonthEnd() {
        return controlPanelMonthEnd;
    }

    public void setControlPanelMonthEnd(Boolean controlPanelMonthEnd) {
        this.controlPanelMonthEnd = controlPanelMonthEnd;
    }

    public Boolean getControlPanelYearEnd() {
        return controlPanelYearEnd;
    }

    public void setControlPanelYearEnd(Boolean controlPanelYearEnd) {
        this.controlPanelYearEnd = controlPanelYearEnd;
    }

    public Boolean getControlPanelConfigurator() {
        return controlPanelConfigurator;
    }

    public void setControlPanelConfigurator(Boolean controlPanelConfigurator) {
        this.controlPanelConfigurator = controlPanelConfigurator;
    }

    public Boolean getCPConfiguratorBank() {
        return cPConfiguratorBank;
    }

    public void setCPConfiguratorBank(Boolean cPConfiguratorBank) {
        this.cPConfiguratorBank = cPConfiguratorBank;
    }

    public Boolean getCPConfiguratorAgent() {
        return cPConfiguratorAgent;
    }

    public void setCPConfiguratorAgent(Boolean cPConfiguratorAgent) {
        this.cPConfiguratorAgent = cPConfiguratorAgent;
    }

    public Boolean getCPConfiguratorCurrency() {
        return cPConfiguratorCurrency;
    }

    public void setCPConfiguratorCurrency(Boolean cPConfiguratorCurrency) {
        this.cPConfiguratorCurrency = cPConfiguratorCurrency;
    }

    public Boolean getCPConfiguratorEmployee() {
        return cPConfiguratorEmployee;
    }

    public void setCPConfiguratorEmployee(Boolean cPConfiguratorEmployee) {
        this.cPConfiguratorEmployee = cPConfiguratorEmployee;
    }

    public Boolean getCPConfiguratorExchangeRate() {
        return cPConfiguratorExchangeRate;
    }

    public void setCPConfiguratorExchangeRate(Boolean cPConfiguratorExchangeRate) {
        this.cPConfiguratorExchangeRate = cPConfiguratorExchangeRate;
    }

    public Boolean getCPConfiguratorProjectTitle() {
        return cPConfiguratorProjectTitle;
    }

    public void setCPConfiguratorProjectTitle(Boolean cPConfiguratorProjectTitle) {
        this.cPConfiguratorProjectTitle = cPConfiguratorProjectTitle;
    }

    public Boolean getCPConfiguratorShippingMethod() {
        return cPConfiguratorShippingMethod;
    }

    public void setCPConfiguratorShippingMethod(Boolean cPConfiguratorShippingMethod) {
        this.cPConfiguratorShippingMethod = cPConfiguratorShippingMethod;
    }

    public Boolean getCPConfiguratorEmailserver() {
        return cPConfiguratorEmailserver;
    }

    public void setCPConfiguratorEmailserver(Boolean cPConfiguratorEmailserver) {
        this.cPConfiguratorEmailserver = cPConfiguratorEmailserver;
    }

    public Boolean getCPConfiguratorPaymentMethod() {
        return cPConfiguratorPaymentMethod;
    }

    public void setCPConfiguratorPaymentMethod(Boolean cPConfiguratorPaymentMethod) {
        this.cPConfiguratorPaymentMethod = cPConfiguratorPaymentMethod;
    }

    public Boolean getCPConfiguratorConfiguration() {
        return cPConfiguratorConfiguration;
    }

    public void setCPConfiguratorConfiguration(Boolean cPConfiguratorConfiguration) {
        this.cPConfiguratorConfiguration = cPConfiguratorConfiguration;
    }

    public Boolean getControlPanelCompanySetup() {
        return controlPanelCompanySetup;
    }

    public void setControlPanelCompanySetup(Boolean controlPanelCompanySetup) {
        this.controlPanelCompanySetup = controlPanelCompanySetup;
    }

    public Boolean getCPCompanySetupCI() {
        return cPCompanySetupCI;
    }

    public void setCPCompanySetupCI(Boolean cPCompanySetupCI) {
        this.cPCompanySetupCI = cPCompanySetupCI;
    }

    public Boolean getCPCompanySetupFY() {
        return cPCompanySetupFY;
    }

    public void setCPCompanySetupFY(Boolean cPCompanySetupFY) {
        this.cPCompanySetupFY = cPCompanySetupFY;
    }

    public Boolean getCPCompanySetupCA() {
        return cPCompanySetupCA;
    }

    public void setCPCompanySetupCA(Boolean cPCompanySetupCA) {
        this.cPCompanySetupCA = cPCompanySetupCA;
    }

    public Boolean getCPCompanySetupHSN() {
        return cPCompanySetupHSN;
    }

    public void setCPCompanySetupHSN(Boolean cPCompanySetupHSN) {
        this.cPCompanySetupHSN = cPCompanySetupHSN;
    }

    public Boolean getControlPanelUAS() {
        return controlPanelUAS;
    }

    public void setControlPanelUAS(Boolean controlPanelUAS) {
        this.controlPanelUAS = controlPanelUAS;
    }

    public Boolean getControlPanelUASuas() {
        return controlPanelUASuas;
    }

    public void setControlPanelUASuas(Boolean controlPanelUASuas) {
        this.controlPanelUASuas = controlPanelUASuas;
    }

    public Boolean getControlPanelUASat() {
        return controlPanelUASat;
    }

    public void setControlPanelUASat(Boolean controlPanelUASat) {
        this.controlPanelUASat = controlPanelUASat;
    }

    public Boolean getControlPanelAccountMaintenance() {
        return controlPanelAccountMaintenance;
    }

    public void setControlPanelAccountMaintenance(Boolean controlPanelAccountMaintenance) {
        this.controlPanelAccountMaintenance = controlPanelAccountMaintenance;
    }

    public Boolean getControlPanelAMag() {
        return controlPanelAMag;
    }

    public void setControlPanelAMag(Boolean controlPanelAMag) {
        this.controlPanelAMag = controlPanelAMag;
    }

    public Boolean getControlPanelAMcoa() {
        return controlPanelAMcoa;
    }

    public void setControlPanelAMcoa(Boolean controlPanelAMcoa) {
        this.controlPanelAMcoa = controlPanelAMcoa;
    }

    public Boolean getControlPanelAMbudget() {
        return controlPanelAMbudget;
    }

    public void setControlPanelAMbudget(Boolean controlPanelAMbudget) {
        this.controlPanelAMbudget = controlPanelAMbudget;
    }

    public Boolean getControlPanelDisplaySetup() {
        return controlPanelDisplaySetup;
    }

    public void setControlPanelDisplaySetup(Boolean controlPanelDisplaySetup) {
        this.controlPanelDisplaySetup = controlPanelDisplaySetup;
    }

    public Boolean getControlPanelDSfs() {
        return controlPanelDSfs;
    }

    public void setControlPanelDSfs(Boolean controlPanelDSfs) {
        this.controlPanelDSfs = controlPanelDSfs;
    }

    public Boolean getControlPanelDSlayout() {
        return controlPanelDSlayout;
    }

    public void setControlPanelDSlayout(Boolean controlPanelDSlayout) {
        this.controlPanelDSlayout = controlPanelDSlayout;
    }

    public Boolean getControlPanelDStermsCond() {
        return controlPanelDStermsCond;
    }

    public void setControlPanelDStermsCond(Boolean controlPanelDStermsCond) {
        this.controlPanelDStermsCond = controlPanelDStermsCond;
    }

    public Boolean getContact() {
        return contact;
    }

    public void setContact(Boolean contact) {
        this.contact = contact;
    }

    public Boolean getContactSupplier() {
        return contactSupplier;
    }

    public void setContactSupplier(Boolean contactSupplier) {
        this.contactSupplier = contactSupplier;
    }

    public Boolean getContactCustomer() {
        return contactCustomer;
    }

    public void setContactCustomer(Boolean contactCustomer) {
        this.contactCustomer = contactCustomer;
    }

    public Boolean getContactContacts() {
        return contactContacts;
    }

    public void setContactContacts(Boolean contactContacts) {
        this.contactContacts = contactContacts;
    }

    public Boolean getReport() {
        return report;
    }

    public void setReport(Boolean report) {
        this.report = report;
    }

    public Boolean getReportPurchase() {
        return reportPurchase;
    }

    public void setReportPurchase(Boolean reportPurchase) {
        this.reportPurchase = reportPurchase;
    }

    public Boolean getReportPurchaseSSR() {
        return reportPurchaseSSR;
    }

    public void setReportPurchaseSSR(Boolean reportPurchaseSSR) {
        this.reportPurchaseSSR = reportPurchaseSSR;
    }

    public Boolean getReportPurchasePOL() {
        return reportPurchasePOL;
    }

    public void setReportPurchasePOL(Boolean reportPurchasePOL) {
        this.reportPurchasePOL = reportPurchasePOL;
    }

    public Boolean getReportPurchasePIL() {
        return reportPurchasePIL;
    }

    public void setReportPurchasePIL(Boolean reportPurchasePIL) {
        this.reportPurchasePIL = reportPurchasePIL;
    }

    public Boolean getReportPurchasePRL() {
        return reportPurchasePRL;
    }

    public void setReportPurchasePRL(Boolean reportPurchasePRL) {
        this.reportPurchasePRL = reportPurchasePRL;
    }

    public Boolean getReportPurchaseSP() {
        return reportPurchaseSP;
    }

    public void setReportPurchaseSP(Boolean reportPurchaseSP) {
        this.reportPurchaseSP = reportPurchaseSP;
    }

    public Boolean getReportPurchasePA() {
        return reportPurchasePA;
    }

    public void setReportPurchasePA(Boolean reportPurchasePA) {
        this.reportPurchasePA = reportPurchasePA;
    }

    public Boolean getReportSale() {
        return reportSale;
    }

    public void setReportSale(Boolean reportSale) {
        this.reportSale = reportSale;
    }

    public Boolean getReportSaleSOL() {
        return reportSaleSOL;
    }

    public void setReportSaleSOL(Boolean reportSaleSOL) {
        this.reportSaleSOL = reportSaleSOL;
    }

    public Boolean getReportSaleICL() {
        return reportSaleICL;
    }

    public void setReportSaleICL(Boolean reportSaleICL) {
        this.reportSaleICL = reportSaleICL;
    }

    public Boolean getReportSaleSIL() {
        return reportSaleSIL;
    }

    public void setReportSaleSIL(Boolean reportSaleSIL) {
        this.reportSaleSIL = reportSaleSIL;
    }

    public Boolean getReportSaleSRL() {
        return reportSaleSRL;
    }

    public void setReportSaleSRL(Boolean reportSaleSRL) {
        this.reportSaleSRL = reportSaleSRL;
    }

    public Boolean getReportSalePR() {
        return reportSalePR;
    }

    public void setReportSalePR(Boolean reportSalePR) {
        this.reportSalePR = reportSalePR;
    }

    public Boolean getReportSalePL() {
        return reportSalePL;
    }

    public void setReportSalePL(Boolean reportSalePL) {
        this.reportSalePL = reportSalePL;
    }

    public Boolean getReportSaleCA() {
        return reportSaleCA;
    }

    public void setReportSaleCA(Boolean reportSaleCA) {
        this.reportSaleCA = reportSaleCA;
    }

    public Boolean getReportSaleCP() {
        return reportSaleCP;
    }

    public void setReportSaleCP(Boolean reportSaleCP) {
        this.reportSaleCP = reportSaleCP;
    }

    public Boolean getReportSaleSA() {
        return reportSaleSA;
    }

    public void setReportSaleSA(Boolean reportSaleSA) {
        this.reportSaleSA = reportSaleSA;
    }

    public Boolean getReportInventory() {
        return reportInventory;
    }

    public void setReportInventory(Boolean reportInventory) {
        this.reportInventory = reportInventory;
    }

    public Boolean getReportInventoryIVR() {
        return reportInventoryIVR;
    }

    public void setReportInventoryIVR(Boolean reportInventoryIVR) {
        this.reportInventoryIVR = reportInventoryIVR;
    }

    public Boolean getReportInventoryISR() {
        return reportInventoryISR;
    }

    public void setReportInventoryISR(Boolean reportInventoryISR) {
        this.reportInventoryISR = reportInventoryISR;
    }

    public Boolean getReportInventoryilSCR() {
        return reportInventoryilSCR;
    }

    public void setReportInventoryilSCR(Boolean reportInventoryilSCR) {
        this.reportInventoryilSCR = reportInventoryilSCR;
    }

    public Boolean getReportAnalysis() {
        return reportAnalysis;
    }

    public void setReportAnalysis(Boolean reportAnalysis) {
        this.reportAnalysis = reportAnalysis;
    }

    public Boolean getReportAnalysisTopVendor() {
        return reportAnalysisTopVendor;
    }

    public void setReportAnalysisTopVendor(Boolean reportAnalysisTopVendor) {
        this.reportAnalysisTopVendor = reportAnalysisTopVendor;
    }

    public Boolean getReportAnalysisTopItem() {
        return reportAnalysisTopItem;
    }

    public void setReportAnalysisTopItem(Boolean reportAnalysisTopItem) {
        this.reportAnalysisTopItem = reportAnalysisTopItem;
    }

    public Boolean getReportAnalysisTopCustomer() {
        return reportAnalysisTopCustomer;
    }

    public void setReportAnalysisTopCustomer(Boolean reportAnalysisTopCustomer) {
        this.reportAnalysisTopCustomer = reportAnalysisTopCustomer;
    }

    public Boolean getReportFinancialStatement() {
        return reportFinancialStatement;
    }

    public void setReportFinancialStatement(Boolean reportFinancialStatement) {
        this.reportFinancialStatement = reportFinancialStatement;
    }

    public Boolean getReportFStatementPL() {
        return reportFStatementPL;
    }

    public void setReportFStatementPL(Boolean reportFStatementPL) {
        this.reportFStatementPL = reportFStatementPL;
    }

    public Boolean getReportFStatementBS() {
        return reportFStatementBS;
    }

    public void setReportFStatementBS(Boolean reportFStatementBS) {
        this.reportFStatementBS = reportFStatementBS;
    }

    public Boolean getReportFStatementTB() {
        return reportFStatementTB;
    }

    public void setReportFStatementTB(Boolean reportFStatementTB) {
        this.reportFStatementTB = reportFStatementTB;
    }

    public Boolean getReportFStatementGL() {
        return reportFStatementGL;
    }

    public void setReportFStatementGL(Boolean reportFStatementGL) {
        this.reportFStatementGL = reportFStatementGL;
    }

    public Boolean getReportFStatementVL() {
        return reportFStatementVL;
    }

    public void setReportFStatementVL(Boolean reportFStatementVL) {
        this.reportFStatementVL = reportFStatementVL;
    }

    public Boolean getReportFStatementGJ() {
        return reportFStatementGJ;
    }

    public void setReportFStatementGJ(Boolean reportFStatementGJ) {
        this.reportFStatementGJ = reportFStatementGJ;
    }

    public Boolean getReportBankRecon() {
        return reportBankRecon;
    }

    public void setReportBankRecon(Boolean reportBankRecon) {
        this.reportBankRecon = reportBankRecon;
    }

    public Boolean getReportBankReconRatio() {
        return reportBankReconRatio;
    }

    public void setReportBankReconRatio(Boolean reportBankReconRatio) {
        this.reportBankReconRatio = reportBankReconRatio;
    }
}
