package in.hiaccounts.hinext.controlpanel.model.useraccount_setup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by administrator on 9/2/18.
 */

public class VCuserAccessRightsDTO implements Serializable {
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("id")
    @Expose
    public Long id;
    @SerializedName("userAccountSetupID")
    @Expose
    public Long userAccountSetupID;
    @SerializedName("dashboard")
    @Expose
    public Boolean dashboard;
    @SerializedName("dashboardDateTime")
    @Expose
    public Boolean dashboardDateTime;
    @SerializedName("dashboardCashInHand")
    @Expose
    public Boolean dashboardCashInHand;
    @SerializedName("dashboardCashInBank")
    @Expose
    public Boolean dashboardCashInBank;
    @SerializedName("dashboardTotalReceivable")
    @Expose
    public Boolean dashboardTotalReceivable;
    @SerializedName("dashboardTotalPayable")
    @Expose
    public Boolean dashboardTotalPayable;
    @SerializedName("dashboardExtra1")
    @Expose
    public Boolean dashboardExtra1;
    @SerializedName("dashboardExtra2")
    @Expose
    public Boolean dashboardExtra2;
    @SerializedName("service")
    @Expose
    public Boolean service;
    @SerializedName("serviceItemAdd")
    @Expose
    public Boolean serviceItemAdd;
    @SerializedName("serviceCustomerAdd")
    @Expose
    public Boolean serviceCustomerAdd;
    @SerializedName("serviceSavesDraft")
    @Expose
    public Boolean serviceSavesDraft;
    @SerializedName("serviceClearDraft")
    @Expose
    public Boolean serviceClearDraft;
    @SerializedName("servicePrintList")
    @Expose
    public Boolean servicePrintList;
    @SerializedName("serviceAdvancePayment")
    @Expose
    public Boolean serviceAdvancePayment;
    @SerializedName("serviceSaveSalesQuotation")
    @Expose
    public Boolean serviceSaveSalesQuotation;
    @SerializedName("serviceInvokeSalesQuotation")
    @Expose
    public Boolean serviceInvokeSalesQuotation;
    @SerializedName("serviceSaveserviceOrder")
    @Expose
    public Boolean serviceSaveserviceOrder;
    @SerializedName("serviceAdvancePaymentList")
    @Expose
    public Boolean serviceAdvancePaymentList;
    @SerializedName("serviceInvokeSalesOrder")
    @Expose
    public Boolean serviceInvokeSalesOrder;
    @SerializedName("serviceClearAll")
    @Expose
    public Boolean serviceClearAll;
    @SerializedName("serviceCheckOut")
    @Expose
    public Boolean serviceCheckOut;
    @SerializedName("serviceReturn")
    @Expose
    public Boolean serviceReturn;
    @SerializedName("serviceRecievePayment")
    @Expose
    public Boolean serviceRecievePayment;
    @SerializedName("serviceInvokeDraft")
    @Expose
    public Boolean serviceInvokeDraft;
    @SerializedName("serviceSearchBarCode")
    @Expose
    public Boolean serviceSearchBarCode;
    @SerializedName("serviceBarCode")
    @Expose
    public Boolean serviceBarCode;
    @SerializedName("serviceSearchSerializeItem")
    @Expose
    public Boolean serviceSearchSerializeItem;
    @SerializedName("serviceSerializeItem")
    @Expose
    public Boolean serviceSerializeItem;
    @SerializedName("serviceCustomer")
    @Expose
    public Boolean serviceCustomer;
    @SerializedName("serviceImportItem")
    @Expose
    public Boolean serviceImportItem;
    @SerializedName("serviceTaxType")
    @Expose
    public Boolean serviceTaxType;
    @SerializedName("serviceInvLocAdd")
    @Expose
    public Boolean serviceInvLocAdd;
    @SerializedName("serviceInvLoc")
    @Expose
    public Boolean serviceInvLoc;
    @SerializedName("serviceStateAdd")
    @Expose
    public Boolean serviceStateAdd;
    @SerializedName("serviceState")
    @Expose
    public Boolean serviceState;
    @SerializedName("serviceRemoveItem")
    @Expose
    public Boolean serviceRemoveItem;
    @SerializedName("serviceMore")
    @Expose
    public Boolean serviceMore;
    @SerializedName("serviceContactAdd")
    @Expose
    public Boolean serviceContactAdd;
    @SerializedName("serviceContact")
    @Expose
    public Boolean serviceContact;
    @SerializedName("serviceAgentAdd")
    @Expose
    public Boolean serviceAgentAdd;
    @SerializedName("serviceAgent")
    @Expose
    public Boolean serviceAgent;
    @SerializedName("serviceCurrencyAdd")
    @Expose
    public Boolean serviceCurrencyAdd;
    @SerializedName("serviceCurrency")
    @Expose
    public Boolean serviceCurrency;
    @SerializedName("serviceEmployeeAdd")
    @Expose
    public Boolean serviceEmployeeAdd;
    @SerializedName("serviceEmployee")
    @Expose
    public Boolean serviceEmployee;
    @SerializedName("serviceExchangeRateAdd")
    @Expose
    public Boolean serviceExchangeRateAdd;
    @SerializedName("serviceExchangeRate")
    @Expose
    public Boolean serviceExchangeRate;
    @SerializedName("serviceTndCAdd")
    @Expose
    public Boolean serviceTndCAdd;
    @SerializedName("serviceTandC")
    @Expose
    public Boolean serviceTandC;
    @SerializedName("serviceCCAdd")
    @Expose
    public Boolean serviceCCAdd;
    @SerializedName("serviceCC")
    @Expose
    public Boolean serviceCC;
    @SerializedName("serviceSMAdd")
    @Expose
    public Boolean serviceSMAdd;
    @SerializedName("serviceSM")
    @Expose
    public Boolean serviceSM;
    @SerializedName("serviceShippingRefNo")
    @Expose
    public Boolean serviceShippingRefNo;
    @SerializedName("serviceRefNo")
    @Expose
    public Boolean serviceRefNo;
    @SerializedName("serviceSaveProforma")
    @Expose
    public Boolean serviceSaveProforma;
    @SerializedName("serviceInvokeProforma")
    @Expose
    public Boolean serviceInvokeProforma;
    @SerializedName("serviceSaveDeliveryOrder")
    @Expose
    public Boolean serviceSaveDeliveryOrder;
    @SerializedName("serviceInvokeDeliveryOrder")
    @Expose
    public Boolean serviceInvokeDeliveryOrder;
    @SerializedName("serviceReturnDeliveryOrder")
    @Expose
    public Boolean serviceReturnDeliveryOrder;
    @SerializedName("serviceDebitNote")
    @Expose
    public Boolean serviceDebitNote;
    @SerializedName("servicedraftInvoice")
    @Expose
    public Boolean servicedraftInvoice;
    @SerializedName("sales")
    @Expose
    public Boolean sales;
    @SerializedName("salesSearchBarCode")
    @Expose
    public Boolean salesSearchBarCode;
    @SerializedName("salesBarCode")
    @Expose
    public Boolean salesBarCode;
    @SerializedName("salesSearchSerializeItem")
    @Expose
    public Boolean salesSearchSerializeItem;
    @SerializedName("salesSerializeItem")
    @Expose
    public Boolean salesSerializeItem;
    @SerializedName("salesCustomer")
    @Expose
    public Boolean salesCustomer;
    @SerializedName("salesImportItem")
    @Expose
    public Boolean salesImportItem;
    @SerializedName("salesTaxType")
    @Expose
    public Boolean salesTaxType;
    @SerializedName("salesInvLocAdd")
    @Expose
    public Boolean salesInvLocAdd;
    @SerializedName("salesInvLoc")
    @Expose
    public Boolean salesInvLoc;
    @SerializedName("salesStateAdd")
    @Expose
    public Boolean salesStateAdd;
    @SerializedName("salesState")
    @Expose
    public Boolean salesState;
    @SerializedName("salesRemoveItem")
    @Expose
    public Boolean salesRemoveItem;
    @SerializedName("salesMore")
    @Expose
    public Boolean salesMore;
    @SerializedName("salesContactAdd")
    @Expose
    public Boolean salesContactAdd;
    @SerializedName("salesContact")
    @Expose
    public Boolean salesContact;
    @SerializedName("salesAgentAdd")
    @Expose
    public Boolean salesAgentAdd;
    @SerializedName("salesAgent")
    @Expose
    public Boolean salesAgent;
    @SerializedName("salesCurrencyAdd")
    @Expose
    public Boolean salesCurrencyAdd;
    @SerializedName("salesCurrency")
    @Expose
    public Boolean salesCurrency;
    @SerializedName("salesEmployeeAdd")
    @Expose
    public Boolean salesEmployeeAdd;
    @SerializedName("salesEmployee")
    @Expose
    public Boolean salesEmployee;
    @SerializedName("salesExchangeRateAdd")
    @Expose
    public Boolean salesExchangeRateAdd;
    @SerializedName("salesExchangeRate")
    @Expose
    public Boolean salesExchangeRate;
    @SerializedName("salesTndCAdd")
    @Expose
    public Boolean salesTndCAdd;
    @SerializedName("salesTandC")
    @Expose
    public Boolean salesTandC;
    @SerializedName("salesCCAdd")
    @Expose
    public Boolean salesCCAdd;
    @SerializedName("salesCC")
    @Expose
    public Boolean salesCC;
    @SerializedName("salesSMAdd")
    @Expose
    public Boolean salesSMAdd;
    @SerializedName("salesSM")
    @Expose
    public Boolean salesSM;
    @SerializedName("salesShippingRefNo")
    @Expose
    public Boolean salesShippingRefNo;
    @SerializedName("salesRefNo")
    @Expose
    public Boolean salesRefNo;
    @SerializedName("salesSaveProforma")
    @Expose
    public Boolean salesSaveProforma;
    @SerializedName("salesInvokeProforma")
    @Expose
    public Boolean salesInvokeProforma;
    @SerializedName("salesSaveDeliveryOrder")
    @Expose
    public Boolean salesSaveDeliveryOrder;
    @SerializedName("salesInvokeDeliveryOrder")
    @Expose
    public Boolean salesInvokeDeliveryOrder;
    @SerializedName("salesReturnDeliveryOrder")
    @Expose
    public Boolean salesReturnDeliveryOrder;
    @SerializedName("salesSaveSalesQuotation")
    @Expose
    public Boolean salesSaveSalesQuotation;
    @SerializedName("salesInvokeSalesQuotation")
    @Expose
    public Boolean salesInvokeSalesQuotation;
    @SerializedName("salesDebitNote")
    @Expose
    public Boolean salesDebitNote;
    @SerializedName("draftInvoice")
    @Expose
    public Boolean draftInvoice;
    @SerializedName("salesItemAdd")
    @Expose
    public Boolean salesItemAdd;
    @SerializedName("salesCustomerAdd")
    @Expose
    public Boolean salesCustomerAdd;
    @SerializedName("salesSavesDraft")
    @Expose
    public Boolean salesSavesDraft;
    @SerializedName("salesClearDraft")
    @Expose
    public Boolean salesClearDraft;
    @SerializedName("salesPrintList")
    @Expose
    public Boolean salesPrintList;
    @SerializedName("salesAdvancePayment")
    @Expose
    public Boolean salesAdvancePayment;
    @SerializedName("salesSaveSalesOrder")
    @Expose
    public Boolean salesSaveSalesOrder;
    @SerializedName("salesAdvancePaymentList")
    @Expose
    public Boolean salesAdvancePaymentList;
    @SerializedName("salesInvokeSalesOrder")
    @Expose
    public Boolean salesInvokeSalesOrder;
    @SerializedName("salesClearAll")
    @Expose
    public Boolean salesClearAll;
    @SerializedName("salesCheckOut")
    @Expose
    public Boolean salesCheckOut;
    @SerializedName("salesReturn")
    @Expose
    public Boolean salesReturn;
    @SerializedName("salesRecievePayment")
    @Expose
    public Boolean salesRecievePayment;
    @SerializedName("salesInvokeDraft")
    @Expose
    public Boolean salesInvokeDraft;
    @SerializedName("purchase")
    @Expose
    public Boolean purchase;
    @SerializedName("purchaseItemAdd")
    @Expose
    public Boolean purchaseItemAdd;
    @SerializedName("purchaseSupplierAdd")
    @Expose
    public Boolean purchaseSupplierAdd;
    @SerializedName("purchasePrintList")
    @Expose
    public Boolean purchasePrintList;
    @SerializedName("purchaseAdvancePayment")
    @Expose
    public Boolean purchaseAdvancePayment;
    @SerializedName("purchaseSavePurchaseOrder")
    @Expose
    public Boolean purchaseSavePurchaseOrder;
    @SerializedName("purchaseAdvancePaymentList")
    @Expose
    public Boolean purchaseAdvancePaymentList;
    @SerializedName("purchaseInvokePurchaseOrder")
    @Expose
    public Boolean purchaseInvokePurchaseOrder;
    @SerializedName("purchaseSavePurchaseQuotation")
    @Expose
    public Boolean purchaseSavePurchaseQuotation;
    @SerializedName("purchaseInvokePurchaseQuotation")
    @Expose
    public Boolean purchaseInvokePurchaseQuotation;
    @SerializedName("purchaseClearAll")
    @Expose
    public Boolean purchaseClearAll;
    @SerializedName("purchaseCheckOut")
    @Expose
    public Boolean purchaseCheckOut;
    @SerializedName("purchaseReturn")
    @Expose
    public Boolean purchaseReturn;
    @SerializedName("purchaseSupplierPayment")
    @Expose
    public Boolean purchaseSupplierPayment;
    @SerializedName("purchaseSelfBilled")
    @Expose
    public Boolean purchaseSelfBilled;
    @SerializedName("purSearchBarCode")
    @Expose
    public Boolean purSearchBarCode;
    @SerializedName("purBarCode")
    @Expose
    public Boolean purBarCode;
    @SerializedName("purRemoveItem")
    @Expose
    public Boolean purRemoveItem;
    @SerializedName("purMore")
    @Expose
    public Boolean purMore;
    @SerializedName("revChargeInv")
    @Expose
    public Boolean revChargeInv;
    @SerializedName("purCreditNote")
    @Expose
    public Boolean purCreditNote;
    @SerializedName("purSaveRecItem")
    @Expose
    public Boolean purSaveRecItem;
    @SerializedName("purInvokeRecItem")
    @Expose
    public Boolean purInvokeRecItem;
    @SerializedName("purReturnRecItem")
    @Expose
    public Boolean purReturnRecItem;
    @SerializedName("purTaxType")
    @Expose
    public Boolean purTaxType;
    @SerializedName("purInvLocAdd")
    @Expose
    public Boolean purInvLocAdd;
    @SerializedName("purInvLoc")
    @Expose
    public Boolean purInvLoc;
    @SerializedName("purStateAdd")
    @Expose
    public Boolean purStateAdd;
    @SerializedName("purState")
    @Expose
    public Boolean purState;
    @SerializedName("purContactAdd")
    @Expose
    public Boolean purContactAdd;
    @SerializedName("purContact")
    @Expose
    public Boolean purContact;
    @SerializedName("purAgentAdd")
    @Expose
    public Boolean purAgentAdd;
    @SerializedName("purAgent")
    @Expose
    public Boolean purAgent;
    @SerializedName("purCurrencyAdd")
    @Expose
    public Boolean purCurrencyAdd;
    @SerializedName("purCurrency")
    @Expose
    public Boolean purCurrency;
    @SerializedName("purEmployeeAdd")
    @Expose
    public Boolean purEmployeeAdd;
    @SerializedName("purEmployee")
    @Expose
    public Boolean purEmployee;
    @SerializedName("purExchangeRateAdd")
    @Expose
    public Boolean purExchangeRateAdd;
    @SerializedName("purExchangeRate")
    @Expose
    public Boolean purExchangeRate;
    @SerializedName("purTndCAdd")
    @Expose
    public Boolean purTndCAdd;
    @SerializedName("purTandC")
    @Expose
    public Boolean purTandC;
    @SerializedName("purCCAdd")
    @Expose
    public Boolean purCCAdd;
    @SerializedName("purCC")
    @Expose
    public Boolean purCC;
    @SerializedName("purSMAdd")
    @Expose
    public Boolean purSMAdd;
    @SerializedName("purSM")
    @Expose
    public Boolean purSM;
    @SerializedName("purShippingRefNo")
    @Expose
    public Boolean purShippingRefNo;
    @SerializedName("purRefNo")
    @Expose
    public Boolean purRefNo;
    @SerializedName("purImportItem")
    @Expose
    public Boolean purImportItem;
    @SerializedName("gt")
    @Expose
    public Boolean gt;
    @SerializedName("gtExpense")
    @Expose
    public Boolean gtExpense;
    @SerializedName("gtReciepts")
    @Expose
    public Boolean gtReciepts;
    @SerializedName("gtJournalEntry")
    @Expose
    public Boolean gtJournalEntry;
    @SerializedName("inventory")
    @Expose
    public Boolean inventory;
    @SerializedName("inventoryCategory")
    @Expose
    public Boolean inventoryCategory;
    @SerializedName("inventoryBrand")
    @Expose
    public Boolean inventoryBrand;
    @SerializedName("inventoryUOM")
    @Expose
    public Boolean inventoryUOM;
    @SerializedName("inventoryItem")
    @Expose
    public Boolean inventoryItem;
    @SerializedName("inventorySalesPricing")
    @Expose
    public Boolean inventorySalesPricing;
    @SerializedName("inventoryPurchasePricing")
    @Expose
    public Boolean inventoryPurchasePricing;
    @SerializedName("inventoryInventoryLocation")
    @Expose
    public Boolean inventoryInventoryLocation;
    @SerializedName("inventoryInventoryMovementType")
    @Expose
    public Boolean inventoryInventoryMovementType;
    @SerializedName("inventoryAttribute")
    @Expose
    public Boolean inventoryAttribute;
    @SerializedName("inventoryAttributeConfig")
    @Expose
    public Boolean inventoryAttributeConfig;
    @SerializedName("inventoryPayVoucher")
    @Expose
    public Boolean inventoryPayVoucher;
    @SerializedName("inventoryInvLocTransfer")
    @Expose
    public Boolean inventoryInvLocTransfer;
    @SerializedName("inventoryInvCountType")
    @Expose
    public Boolean inventoryInvCountType;
    @SerializedName("inventorySalesDiscountConfig")
    @Expose
    public Boolean inventorySalesDiscountConfig;
    @SerializedName("inventoryAdvDisConfig")
    @Expose
    public Boolean inventoryAdvDisConfig;
    @SerializedName("inventoryLoyality")
    @Expose
    public Boolean inventoryLoyality;
    @SerializedName("inventoryVoucher")
    @Expose
    public Boolean inventoryVoucher;
    @SerializedName("inventoryItemCountAdjust")
    @Expose
    public Boolean inventoryItemCountAdjust;
    @SerializedName("inventoryHsnCode")
    @Expose
    public Boolean inventoryHsnCode;
    @SerializedName("inventoryItemCommission")
    @Expose
    public Boolean inventoryItemCommission;
    @SerializedName("inventoryAssets")
    @Expose
    public Boolean inventoryAssets;
    @SerializedName("inventoryUomConverter")
    @Expose
    public Boolean inventoryUomConverter;
    @SerializedName("inventoryAssetsCat")
    @Expose
    public Boolean inventoryAssetsCat;
    @SerializedName("inventoryJSDN")
    @Expose
    public Boolean inventoryJSDN;
    @SerializedName("inventoryAdvTableTransfer")
    @Expose
    public Boolean inventoryAdvTableTransfer;
    @SerializedName("inventoryRedemption")
    @Expose
    public Boolean inventoryRedemption;
    @SerializedName("manufacturing")
    @Expose
    public Boolean manufacturing;
    @SerializedName("manufacturingAP")
    @Expose
    public Boolean manufacturingAP;
    @SerializedName("manufacturingPM")
    @Expose
    public Boolean manufacturingPM;
    @SerializedName("manufacturingAdvItemCountAdjust")
    @Expose
    public Boolean manufacturingAdvItemCountAdjust;
    @SerializedName("manufacturingCR")
    @Expose
    public Boolean manufacturingCR;
    @SerializedName("manufacturingFSCR")
    @Expose
    public Boolean manufacturingFSCR;
    @SerializedName("manufacturingAICAR")
    @Expose
    public Boolean manufacturingAICAR;
    @SerializedName("crm")
    @Expose
    public Boolean crm;
    @SerializedName("leads")
    @Expose
    public Boolean leads;
    @SerializedName("event")
    @Expose
    public Boolean event;
    @SerializedName("tax")
    @Expose
    public Boolean tax;
    @SerializedName("taxTaxConfiguration")
    @Expose
    public Boolean taxTaxConfiguration;
    @SerializedName("taxGST")
    @Expose
    public Boolean taxGST;
    @SerializedName("taxTax")
    @Expose
    public Boolean taxTax;
    @SerializedName("taxTaxtype")
    @Expose
    public Boolean taxTaxtype;
    @SerializedName("gstIndiaGSTSR")
    @Expose
    public Boolean gstIndiaGSTSR;
    @SerializedName("gstIndiaGSTR1Tool")
    @Expose
    public Boolean gstIndiaGSTR1Tool;
    @SerializedName("gstIndiaGSTR2Tool")
    @Expose
    public Boolean gstIndiaGSTR2Tool;
    @SerializedName("gstIndiaGST3B")
    @Expose
    public Boolean gstIndiaGST3B;
    @SerializedName("gstIndiaRIPC")
    @Expose
    public Boolean gstIndiaRIPC;
    @SerializedName("gstIndiaGSTTRANS1")
    @Expose
    public Boolean gstIndiaGSTTRANS1;
    @SerializedName("gstMalGSTSR")
    @Expose
    public Boolean gstMalGSTSR;
    @SerializedName("gstMalGAFExport")
    @Expose
    public Boolean gstMalGAFExport;
    @SerializedName("gstMalGSTReturn")
    @Expose
    public Boolean gstMalGSTReturn;
    @SerializedName("controlPanel")
    @Expose
    public Boolean controlPanel;
    @SerializedName("controlPanelOBofBS")
    @Expose
    public Boolean controlPanelOBofBS;
    @SerializedName("controlPanelOBofPI")
    @Expose
    public Boolean controlPanelOBofPI;
    @SerializedName("controlPanelOBofSI")
    @Expose
    public Boolean controlPanelOBofSI;
    @SerializedName("controlPanelOBofINV")
    @Expose
    public Boolean controlPanelOBofINV;
    @SerializedName("controlPanelLastYearFigure")
    @Expose
    public Boolean controlPanelLastYearFigure;
    @SerializedName("controlPanelMonthEnd")
    @Expose
    public Boolean controlPanelMonthEnd;
    @SerializedName("controlPanelYearEnd")
    @Expose
    public Boolean controlPanelYearEnd;
    @SerializedName("controlPanelConfigurator")
    @Expose
    public Boolean controlPanelConfigurator;
    @SerializedName("cPConfiguratorCountry")
    @Expose
    public Boolean cPConfiguratorCountry;
    @SerializedName("cPConfiguratorVersionControl")
    @Expose
    public Boolean cPConfiguratorVersionControl;
    @SerializedName("cPConfiguratorServiceCharge")
    @Expose
    public Boolean cPConfiguratorServiceCharge;
    @SerializedName("cPConfiguratorLabelPrintConfig")
    @Expose
    public Boolean cPConfiguratorLabelPrintConfig;
    @SerializedName("cPConfiguratorOffersConfig")
    @Expose
    public Boolean cPConfiguratorOffersConfig;
    @SerializedName("cPConfiguratorBank")
    @Expose
    public Boolean cPConfiguratorBank;
    @SerializedName("cPConfiguratorAgent")
    @Expose
    public Boolean cPConfiguratorAgent;
    @SerializedName("cPConfiguratorCurrency")
    @Expose
    public Boolean cPConfiguratorCurrency;
    @SerializedName("cPConfiguratorEmployee")
    @Expose
    public Boolean cPConfiguratorEmployee;
    @SerializedName("cPConfiguratorExchangeRate")
    @Expose
    public Boolean cPConfiguratorExchangeRate;
    @SerializedName("cPConfiguratorProjectTitle")
    @Expose
    public Boolean cPConfiguratorProjectTitle;
    @SerializedName("cPConfiguratorShippingMethod")
    @Expose
    public Boolean cPConfiguratorShippingMethod;
    @SerializedName("cPConfiguratorEmailserver")
    @Expose
    public Boolean cPConfiguratorEmailserver;
    @SerializedName("cPConfiguratorPaymentMethod")
    @Expose
    public Boolean cPConfiguratorPaymentMethod;
    @SerializedName("cPConfiguratorConfiguration")
    @Expose
    public Boolean cPConfiguratorConfiguration;
    @SerializedName("controlPanelCompanySetup")
    @Expose
    public Boolean controlPanelCompanySetup;
    @SerializedName("cPCompanySetupCI")
    @Expose
    public Boolean cPCompanySetupCI;
    @SerializedName("cPCompanySetupFY")
    @Expose
    public Boolean cPCompanySetupFY;
    @SerializedName("cPCompanySetupCA")
    @Expose
    public Boolean cPCompanySetupCA;
    @SerializedName("cPCompanySetupHSN")
    @Expose
    public Boolean cPCompanySetupHSN;
    @SerializedName("cPCompanySetupAlerts")
    @Expose
    public Boolean cPCompanySetupAlerts;
    @SerializedName("cPCompanySetupBandR")
    @Expose
    public Boolean cPCompanySetupBandR;
    @SerializedName("cPCompanySetupRS")
    @Expose
    public Boolean cPCompanySetupRS;
    @SerializedName("controlPanelUAS")
    @Expose
    public Boolean controlPanelUAS;
    @SerializedName("controlPanelUASuas")
    @Expose
    public Boolean controlPanelUASuas;
    @SerializedName("controlPanelUASat")
    @Expose
    public Boolean controlPanelUASat;
    @SerializedName("controlPanelAccountMaintenance")
    @Expose
    public Boolean controlPanelAccountMaintenance;
    @SerializedName("controlPanelAMag")
    @Expose
    public Boolean controlPanelAMag;
    @SerializedName("controlPanelAMcoa")
    @Expose
    public Boolean controlPanelAMcoa;
    @SerializedName("controlPanelAMbudget")
    @Expose
    public Boolean controlPanelAMbudget;
    @SerializedName("controlPanelDisplaySetup")
    @Expose
    public Boolean controlPanelDisplaySetup;
    @SerializedName("controlPanelDSfs")
    @Expose
    public Boolean controlPanelDSfs;
    @SerializedName("controlPanelDSlayout")
    @Expose
    public Boolean controlPanelDSlayout;
    @SerializedName("controlPanelDStermsCond")
    @Expose
    public Boolean controlPanelDStermsCond;
    @SerializedName("controlPanelDSTable")
    @Expose
    public Boolean controlPanelDSTable;
    @SerializedName("controlPanelDSTableConfig")
    @Expose
    public Boolean controlPanelDSTableConfig;
    @SerializedName("controlPanelDSEandSMSConfig")
    @Expose
    public Boolean controlPanelDSEandSMSConfig;
    @SerializedName("contact")
    @Expose
    public Boolean contact;
    @SerializedName("contactSupplier")
    @Expose
    public Boolean contactSupplier;
    @SerializedName("contactCustomer")
    @Expose
    public Boolean contactCustomer;
    @SerializedName("contactContacts")
    @Expose
    public Boolean contactContacts;
    @SerializedName("report")
    @Expose
    public Boolean report;
    @SerializedName("reportPurchase")
    @Expose
    public Boolean reportPurchase;
    @SerializedName("reportPurchaseSSR")
    @Expose
    public Boolean reportPurchaseSSR;
    @SerializedName("reportPurchasePOL")
    @Expose
    public Boolean reportPurchasePOL;
    @SerializedName("reportPurchasePIL")
    @Expose
    public Boolean reportPurchasePIL;
    @SerializedName("reportPurchasePRL")
    @Expose
    public Boolean reportPurchasePRL;
    @SerializedName("reportPurchaseSP")
    @Expose
    public Boolean reportPurchaseSP;
    @SerializedName("reportPurchasePA")
    @Expose
    public Boolean reportPurchasePA;
    @SerializedName("reportPurchaseRIL")
    @Expose
    public Boolean reportPurchaseRIL;
    @SerializedName("reportPurchaseSSL")
    @Expose
    public Boolean reportPurchaseSSL;
    @SerializedName("reportPurchaseSL")
    @Expose
    public Boolean reportPurchaseSL;
    @SerializedName("reportPurchaseCA")
    @Expose
    public Boolean reportPurchaseCA;
    @SerializedName("reportPurchaseCPA")
    @Expose
    public Boolean reportPurchaseCPA;
    @SerializedName("reportSale")
    @Expose
    public Boolean reportSale;
    @SerializedName("reportSaleSOL")
    @Expose
    public Boolean reportSaleSOL;
    @SerializedName("reportSaleICL")
    @Expose
    public Boolean reportSaleICL;
    @SerializedName("reportSaleSIL")
    @Expose
    public Boolean reportSaleSIL;
    @SerializedName("reportSaleSRL")
    @Expose
    public Boolean reportSaleSRL;
    @SerializedName("reportSalePR")
    @Expose
    public Boolean reportSalePR;
    @SerializedName("reportSalePL")
    @Expose
    public Boolean reportSalePL;
    @SerializedName("reportSaleCA")
    @Expose
    public Boolean reportSaleCA;
    @SerializedName("reportSaleCP")
    @Expose
    public Boolean reportSaleCP;
    @SerializedName("reportSaleSA")
    @Expose
    public Boolean reportSaleSA;
    @SerializedName("reportSaleSQL")
    @Expose
    public Boolean reportSaleSQL;
    @SerializedName("reportSalePDL")
    @Expose
    public Boolean reportSalePDL;
    @SerializedName("reportSaleCS")
    @Expose
    public Boolean reportSaleCS;
    @SerializedName("reportSaleDA")
    @Expose
    public Boolean reportSaleDA;
    @SerializedName("reportSaleCL")
    @Expose
    public Boolean reportSaleCL;
    @SerializedName("reportSaleAL")
    @Expose
    public Boolean reportSaleAL;
    @SerializedName("reportSaleCSI")
    @Expose
    public Boolean reportSaleCSI;
    @SerializedName("reportSaleCLP")
    @Expose
    public Boolean reportSaleCLP;
    @SerializedName("reportSaleML")
    @Expose
    public Boolean reportSaleML;
    @SerializedName("reportInventory")
    @Expose
    public Boolean reportInventory;
    @SerializedName("reportInventoryIVR")
    @Expose
    public Boolean reportInventoryIVR;
    @SerializedName("reportInventoryISR")
    @Expose
    public Boolean reportInventoryISR;
    @SerializedName("reportInventoryilSCR")
    @Expose
    public Boolean reportInventoryilSCR;
    @SerializedName("reportInventoryGRNVR")
    @Expose
    public Boolean reportInventoryGRNVR;
    @SerializedName("reportInventoryIPR")
    @Expose
    public Boolean reportInventoryIPR;
    @SerializedName("reportInventoryINMR")
    @Expose
    public Boolean reportInventoryINMR;
    @SerializedName("reportInventoryIMR")
    @Expose
    public Boolean reportInventoryIMR;
    @SerializedName("reportInventoryIL")
    @Expose
    public Boolean reportInventoryIL;
    @SerializedName("reportInventoryOGRNR")
    @Expose
    public Boolean reportInventoryOGRNR;
    @SerializedName("reportInventoryASCR")
    @Expose
    public Boolean reportInventoryASCR;
    @SerializedName("reportInventoryASSCR")
    @Expose
    public Boolean reportInventoryASSCR;
    @SerializedName("reportInventoryRPL")
    @Expose
    public Boolean reportInventoryRPL;
    @SerializedName("reportInventoryRPR")
    @Expose
    public Boolean reportInventoryRPR;
    @SerializedName("reportInventoryDEL")
    @Expose
    public Boolean reportInventoryDEL;
    @SerializedName("reportInventoryBER")
    @Expose
    public Boolean reportInventoryBER;
    @SerializedName("reportInventorySI")
    @Expose
    public Boolean reportInventorySI;
    @SerializedName("reportInventoryBI")
    @Expose
    public Boolean reportInventoryBI;
    @SerializedName("reportInventoryADVSCR")
    @Expose
    public Boolean reportInventoryADVSCR;
    @SerializedName("reportInventoryADVSSR")
    @Expose
    public Boolean reportInventoryADVSSR;
    @SerializedName("reportAnalysis")
    @Expose
    public Boolean reportAnalysis;
    @SerializedName("reportAnalysisTopVendor")
    @Expose
    public Boolean reportAnalysisTopVendor;
    @SerializedName("reportAnalysisTopItem")
    @Expose
    public Boolean reportAnalysisTopItem;
    @SerializedName("reportAnalysisTopCustomer")
    @Expose
    public Boolean reportAnalysisTopCustomer;
    @SerializedName("reportAnalysisAccountBalance")
    @Expose
    public Boolean reportAnalysisAccountBalance;
    @SerializedName("reportAnalysisCRR")
    @Expose
    public Boolean reportAnalysisCRR;
    @SerializedName("reportAnalysisCIP")
    @Expose
    public Boolean reportAnalysisCIP;
    @SerializedName("reportAnalysisSIP")
    @Expose
    public Boolean reportAnalysisSIP;
    @SerializedName("reportFinancialStatement")
    @Expose
    public Boolean reportFinancialStatement;
    @SerializedName("reportFStatementPL")
    @Expose
    public Boolean reportFStatementPL;
    @SerializedName("reportFStatementBS")
    @Expose
    public Boolean reportFStatementBS;
    @SerializedName("reportFStatementTB")
    @Expose
    public Boolean reportFStatementTB;
    @SerializedName("reportFStatementGL")
    @Expose
    public Boolean reportFStatementGL;
    @SerializedName("reportFStatementVL")
    @Expose
    public Boolean reportFStatementVL;
    @SerializedName("reportFStatementGJ")
    @Expose
    public Boolean reportFStatementGJ;
    @SerializedName("reportFStatementPLLocWise")
    @Expose
    public Boolean reportFStatementPLLocWise;
    @SerializedName("reportFStatementDB")
    @Expose
    public Boolean reportFStatementDB;
    @SerializedName("reportBankRecon")
    @Expose
    public Boolean reportBankRecon;
    @SerializedName("reportBankReconRatio")
    @Expose
    public Boolean reportBankReconRatio;
    @SerializedName("reportBankReconCheques")
    @Expose
    public Boolean reportBankReconCheques;
    @SerializedName("reportBankReconStmnt")
    @Expose
    public Boolean reportBankReconStmnt;
    @SerializedName("goldPurchases")
    @Expose
    public Boolean goldPurchases;
    @SerializedName("gPSearchSerializeItem")
    @Expose
    public Boolean gPSearchSerializeItem;
    @SerializedName("gPSerializeItem")
    @Expose
    public Boolean gPSerializeItem;
    @SerializedName("gPSearchBarCode")
    @Expose
    public Boolean gPSearchBarCode;
    @SerializedName("gPBarCode")
    @Expose
    public Boolean gPBarCode;
    @SerializedName("gPItemAdd")
    @Expose
    public Boolean gPItemAdd;
    @SerializedName("gPCustomerAdd")
    @Expose
    public Boolean gPCustomerAdd;
    @SerializedName("gPSavesDraft")
    @Expose
    public Boolean gPSavesDraft;
    @SerializedName("gPClearDraft")
    @Expose
    public Boolean gPClearDraft;
    @SerializedName("gPPrintList")
    @Expose
    public Boolean gPPrintList;
    @SerializedName("gPAdvancePayment")
    @Expose
    public Boolean gPAdvancePayment;
    @SerializedName("gPSaveQuotation")
    @Expose
    public Boolean gPSaveQuotation;
    @SerializedName("gPInvokeQuotation")
    @Expose
    public Boolean gPInvokeQuotation;
    @SerializedName("gPSaveOrder")
    @Expose
    public Boolean gPSaveOrder;
    @SerializedName("gPAdvancePaymentList")
    @Expose
    public Boolean gPAdvancePaymentList;
    @SerializedName("gPInvokeOrder")
    @Expose
    public Boolean gPInvokeOrder;
    @SerializedName("gPClearAll")
    @Expose
    public Boolean gPClearAll;
    @SerializedName("gPCheckOut")
    @Expose
    public Boolean gPCheckOut;
    @SerializedName("gPReturn")
    @Expose
    public Boolean gPReturn;
    @SerializedName("gPRecievePayment")
    @Expose
    public Boolean gPRecievePayment;
    @SerializedName("gPInvokeDraft")
    @Expose
    public Boolean gPInvokeDraft;
    @SerializedName("gPCustomer")
    @Expose
    public Boolean gPCustomer;
    @SerializedName("gPImportItem")
    @Expose
    public Boolean gPImportItem;
    @SerializedName("gPTaxType")
    @Expose
    public Boolean gPTaxType;
    @SerializedName("gPInvLocAdd")
    @Expose
    public Boolean gPInvLocAdd;
    @SerializedName("gPInvLoc")
    @Expose
    public Boolean gPInvLoc;
    @SerializedName("gPStateAdd")
    @Expose
    public Boolean gPStateAdd;
    @SerializedName("gPState")
    @Expose
    public Boolean gPState;
    @SerializedName("gPRemoveItem")
    @Expose
    public Boolean gPRemoveItem;
    @SerializedName("gPMore")
    @Expose
    public Boolean gPMore;
    @SerializedName("gPContactAdd")
    @Expose
    public Boolean gPContactAdd;
    @SerializedName("gPContact")
    @Expose
    public Boolean gPContact;
    @SerializedName("gPAgentAdd")
    @Expose
    public Boolean gPAgentAdd;
    @SerializedName("gPAgent")
    @Expose
    public Boolean gPAgent;
    @SerializedName("gPCurrencyAdd")
    @Expose
    public Boolean gPCurrencyAdd;
    @SerializedName("gPCurrency")
    @Expose
    public Boolean gPCurrency;
    @SerializedName("gPEmployeeAdd")
    @Expose
    public Boolean gPEmployeeAdd;
    @SerializedName("gPEmployee")
    @Expose
    public Boolean gPEmployee;
    @SerializedName("gPExchangeRateAdd")
    @Expose
    public Boolean gPExchangeRateAdd;
    @SerializedName("gPExchangeRate")
    @Expose
    public Boolean gPExchangeRate;
    @SerializedName("gPTndCAdd")
    @Expose
    public Boolean gPTndCAdd;
    @SerializedName("gPTandC")
    @Expose
    public Boolean gPTandC;
    @SerializedName("gPCCAdd")
    @Expose
    public Boolean gPCCAdd;
    @SerializedName("gPCC")
    @Expose
    public Boolean gPCC;
    @SerializedName("gPSMAdd")
    @Expose
    public Boolean gPSMAdd;
    @SerializedName("gPSM")
    @Expose
    public Boolean gPSM;
    @SerializedName("gPShippingRefNo")
    @Expose
    public Boolean gPShippingRefNo;
    @SerializedName("gPRefNo")
    @Expose
    public Boolean gPRefNo;
    @SerializedName("gPSaveProforma")
    @Expose
    public Boolean gPSaveProforma;
    @SerializedName("gPInvokeProforma")
    @Expose
    public Boolean gPInvokeProforma;
    @SerializedName("gPSaveDeliveryOrder")
    @Expose
    public Boolean gPSaveDeliveryOrder;
    @SerializedName("gPInvokeDeliveryOrder")
    @Expose
    public Boolean gPInvokeDeliveryOrder;
    @SerializedName("gPReturnDeliveryOrder")
    @Expose
    public Boolean gPReturnDeliveryOrder;
    @SerializedName("gPDebitNote")
    @Expose
    public Boolean gPDebitNote;
    @SerializedName("gPDraftInvoice")
    @Expose
    public Boolean gPDraftInvoice;
    @SerializedName("mobileSales")
    @Expose
    public Boolean mobileSales;
    @SerializedName("mSItemAdd")
    @Expose
    public Boolean mSItemAdd;
    @SerializedName("mSCustomerAdd")
    @Expose
    public Boolean mSCustomerAdd;
    @SerializedName("mSSavesDraft")
    @Expose
    public Boolean mSSavesDraft;
    @SerializedName("mSClearDraft")
    @Expose
    public Boolean mSClearDraft;
    @SerializedName("mSPrintList")
    @Expose
    public Boolean mSPrintList;
    @SerializedName("mSAdvancePayment")
    @Expose
    public Boolean mSAdvancePayment;
    @SerializedName("mSSaveQuotation")
    @Expose
    public Boolean mSSaveQuotation;
    @SerializedName("mSInvokeQuotation")
    @Expose
    public Boolean mSInvokeQuotation;
    @SerializedName("mSSaveOrder")
    @Expose
    public Boolean mSSaveOrder;
    @SerializedName("mSAdvancePaymentList")
    @Expose
    public Boolean mSAdvancePaymentList;
    @SerializedName("mSInvokeOrder")
    @Expose
    public Boolean mSInvokeOrder;
    @SerializedName("mSClearAll")
    @Expose
    public Boolean mSClearAll;
    @SerializedName("mSCheckOut")
    @Expose
    public Boolean mSCheckOut;
    @SerializedName("mSReturn")
    @Expose
    public Boolean mSReturn;
    @SerializedName("mSRecievePayment")
    @Expose
    public Boolean mSRecievePayment;
    @SerializedName("mSInvokeDraft")
    @Expose
    public Boolean mSInvokeDraft;
    @SerializedName("mSSearchBarCode")
    @Expose
    public Boolean mSSearchBarCode;
    @SerializedName("mSBarCode")
    @Expose
    public Boolean mSBarCode;
    @SerializedName("mSSearchSerializeItem")
    @Expose
    public Boolean mSSearchSerializeItem;
    @SerializedName("mSSerializeItem")
    @Expose
    public Boolean mSSerializeItem;
    @SerializedName("mSCustomer")
    @Expose
    public Boolean mSCustomer;
    @SerializedName("mSImportItem")
    @Expose
    public Boolean mSImportItem;
    @SerializedName("mSTaxType")
    @Expose
    public Boolean mSTaxType;
    @SerializedName("mSInvLocAdd")
    @Expose
    public Boolean mSInvLocAdd;
    @SerializedName("mSInvLoc")
    @Expose
    public Boolean mSInvLoc;
    @SerializedName("mSStateAdd")
    @Expose
    public Boolean mSStateAdd;
    @SerializedName("mSState")
    @Expose
    public Boolean mSState;
    @SerializedName("mSRemoveItem")
    @Expose
    public Boolean mSRemoveItem;
    @SerializedName("mSMore")
    @Expose
    public Boolean mSMore;
    @SerializedName("mSContactAdd")
    @Expose
    public Boolean mSContactAdd;
    @SerializedName("mSContact")
    @Expose
    public Boolean mSContact;
    @SerializedName("mSAgentAdd")
    @Expose
    public Boolean mSAgentAdd;
    @SerializedName("mSAgent")
    @Expose
    public Boolean mSAgent;
    @SerializedName("mSCurrencyAdd")
    @Expose
    public Boolean mSCurrencyAdd;
    @SerializedName("mSCurrency")
    @Expose
    public Boolean mSCurrency;
    @SerializedName("mSEmployeeAdd")
    @Expose
    public Boolean mSEmployeeAdd;
    @SerializedName("mSEmployee")
    @Expose
    public Boolean mSEmployee;
    @SerializedName("mSExchangeRateAdd")
    @Expose
    public Boolean mSExchangeRateAdd;
    @SerializedName("mSExchangeRate")
    @Expose
    public Boolean mSExchangeRate;
    @SerializedName("mSTndCAdd")
    @Expose
    public Boolean mSTndCAdd;
    @SerializedName("mSTandC")
    @Expose
    public Boolean mSTandC;
    @SerializedName("mSCCAdd")
    @Expose
    public Boolean mSCCAdd;
    @SerializedName("mSCC")
    @Expose
    public Boolean mSCC;
    @SerializedName("mSSMAdd")
    @Expose
    public Boolean mSSMAdd;
    @SerializedName("mSSM")
    @Expose
    public Boolean mSSM;
    @SerializedName("mSShippingRefNo")
    @Expose
    public Boolean mSShippingRefNo;
    @SerializedName("mSRefNo")
    @Expose
    public Boolean mSRefNo;
    @SerializedName("mSSaveProforma")
    @Expose
    public Boolean mSSaveProforma;
    @SerializedName("mSInvokeProforma")
    @Expose
    public Boolean mSInvokeProforma;
    @SerializedName("mSSaveDeliveryOrder")
    @Expose
    public Boolean mSSaveDeliveryOrder;
    @SerializedName("mSInvokeDeliveryOrder")
    @Expose
    public Boolean mSInvokeDeliveryOrder;
    @SerializedName("mSReturnDeliveryOrder")
    @Expose
    public Boolean mSReturnDeliveryOrder;
    @SerializedName("mSDebitNote")
    @Expose
    public Boolean mSDebitNote;
    @SerializedName("mSDraftInvoice")
    @Expose
    public Boolean mSDraftInvoice;
    @SerializedName("purchasesConstruction")
    @Expose
    public Boolean purchasesConstruction;
    @SerializedName("pCItemAdd")
    @Expose
    public Boolean pCItemAdd;
    @SerializedName("pCCustomerAdd")
    @Expose
    public Boolean pCCustomerAdd;
    @SerializedName("pCSavesDraft")
    @Expose
    public Boolean pCSavesDraft;
    @SerializedName("pCClearDraft")
    @Expose
    public Boolean pCClearDraft;
    @SerializedName("pCPrintList")
    @Expose
    public Boolean pCPrintList;
    @SerializedName("pCAdvancePayment")
    @Expose
    public Boolean pCAdvancePayment;
    @SerializedName("pCSaveQuotation")
    @Expose
    public Boolean pCSaveQuotation;
    @SerializedName("pCInvokeQuotation")
    @Expose
    public Boolean pCInvokeQuotation;
    @SerializedName("pCSaveOrder")
    @Expose
    public Boolean pCSaveOrder;
    @SerializedName("pCAdvancePaymentList")
    @Expose
    public Boolean pCAdvancePaymentList;
    @SerializedName("pCInvokeOrder")
    @Expose
    public Boolean pCInvokeOrder;
    @SerializedName("pCClearAll")
    @Expose
    public Boolean pCClearAll;
    @SerializedName("pCCheckOut")
    @Expose
    public Boolean pCCheckOut;
    @SerializedName("pCReturn")
    @Expose
    public Boolean pCReturn;
    @SerializedName("pCRecievePayment")
    @Expose
    public Boolean pCRecievePayment;
    @SerializedName("pCInvokeDraft")
    @Expose
    public Boolean pCInvokeDraft;
    @SerializedName("pCSearchBarCode")
    @Expose
    public Boolean pCSearchBarCode;
    @SerializedName("pCBarCode")
    @Expose
    public Boolean pCBarCode;
    @SerializedName("pCSearchSerializeItem")
    @Expose
    public Boolean pCSearchSerializeItem;
    @SerializedName("pCSerializeItem")
    @Expose
    public Boolean pCSerializeItem;
    @SerializedName("pCCustomer")
    @Expose
    public Boolean pCCustomer;
    @SerializedName("pCImportItem")
    @Expose
    public Boolean pCImportItem;
    @SerializedName("pCTaxType")
    @Expose
    public Boolean pCTaxType;
    @SerializedName("pCInvLocAdd")
    @Expose
    public Boolean pCInvLocAdd;
    @SerializedName("pCInvLoc")
    @Expose
    public Boolean pCInvLoc;
    @SerializedName("pCStateAdd")
    @Expose
    public Boolean pCStateAdd;
    @SerializedName("pCState")
    @Expose
    public Boolean pCState;
    @SerializedName("pCRemoveItem")
    @Expose
    public Boolean pCRemoveItem;
    @SerializedName("pCMore")
    @Expose
    public Boolean pCMore;
    @SerializedName("pCContactAdd")
    @Expose
    public Boolean pCContactAdd;
    @SerializedName("pCContact")
    @Expose
    public Boolean pCContact;
    @SerializedName("pCAgentAdd")
    @Expose
    public Boolean pCAgentAdd;
    @SerializedName("pCAgent")
    @Expose
    public Boolean pCAgent;
    @SerializedName("pCCurrencyAdd")
    @Expose
    public Boolean pCCurrencyAdd;
    @SerializedName("pCCurrency")
    @Expose
    public Boolean pCCurrency;
    @SerializedName("pCEmployeeAdd")
    @Expose
    public Boolean pCEmployeeAdd;
    @SerializedName("pCEmployee")
    @Expose
    public Boolean pCEmployee;
    @SerializedName("pCExchangeRateAdd")
    @Expose
    public Boolean pCExchangeRateAdd;
    @SerializedName("pCExchangeRate")
    @Expose
    public Boolean pCExchangeRate;
    @SerializedName("pCTndCAdd")
    @Expose
    public Boolean pCTndCAdd;
    @SerializedName("pCTandC")
    @Expose
    public Boolean pCTandC;
    @SerializedName("pCCCAdd")
    @Expose
    public Boolean pCCCAdd;
    @SerializedName("pCCC")
    @Expose
    public Boolean pCCC;
    @SerializedName("pCSMAdd")
    @Expose
    public Boolean pCSMAdd;
    @SerializedName("pCSM")
    @Expose
    public Boolean pCSM;
    @SerializedName("pCShippingRefNo")
    @Expose
    public Boolean pCShippingRefNo;
    @SerializedName("pCRefNo")
    @Expose
    public Boolean pCRefNo;
    @SerializedName("pCSaveProforma")
    @Expose
    public Boolean pCSaveProforma;
    @SerializedName("pCInvokeProforma")
    @Expose
    public Boolean pCInvokeProforma;
    @SerializedName("pCSaveDeliveryOrder")
    @Expose
    public Boolean pCSaveDeliveryOrder;
    @SerializedName("pCInvokeDeliveryOrder")
    @Expose
    public Boolean pCInvokeDeliveryOrder;
    @SerializedName("pCReturnDeliveryOrder")
    @Expose
    public Boolean pCReturnDeliveryOrder;
    @SerializedName("pCDebitNote")
    @Expose
    public Boolean pCDebitNote;
    @SerializedName("pCDraftInvoice")
    @Expose
    public Boolean pCDraftInvoice;
    @SerializedName("pharmaSales")
    @Expose
    public Boolean pharmaSales;
    @SerializedName("pSItemAdd")
    @Expose
    public Boolean pSItemAdd;
    @SerializedName("pSCustomerAdd")
    @Expose
    public Boolean pSCustomerAdd;
    @SerializedName("pSSavesDraft")
    @Expose
    public Boolean pSSavesDraft;
    @SerializedName("pSClearDraft")
    @Expose
    public Boolean pSClearDraft;
    @SerializedName("pSPrintList")
    @Expose
    public Boolean pSPrintList;
    @SerializedName("pSAdvancePayment")
    @Expose
    public Boolean pSAdvancePayment;
    @SerializedName("pSSaveQuotation")
    @Expose
    public Boolean pSSaveQuotation;
    @SerializedName("pSInvokeQuotation")
    @Expose
    public Boolean pSInvokeQuotation;
    @SerializedName("pSSaveOrder")
    @Expose
    public Boolean pSSaveOrder;
    @SerializedName("pSAdvancePaymentList")
    @Expose
    public Boolean pSAdvancePaymentList;
    @SerializedName("pSInvokeOrder")
    @Expose
    public Boolean pSInvokeOrder;
    @SerializedName("pSClearAll")
    @Expose
    public Boolean pSClearAll;
    @SerializedName("pSCheckOut")
    @Expose
    public Boolean pSCheckOut;
    @SerializedName("pSReturn")
    @Expose
    public Boolean pSReturn;
    @SerializedName("pSRecievePayment")
    @Expose
    public Boolean pSRecievePayment;
    @SerializedName("pSInvokeDraft")
    @Expose
    public Boolean pSInvokeDraft;
    @SerializedName("pSSearchBarCode")
    @Expose
    public Boolean pSSearchBarCode;
    @SerializedName("pSBarCode")
    @Expose
    public Boolean pSBarCode;
    @SerializedName("pSSearchSerializeItem")
    @Expose
    public Boolean pSSearchSerializeItem;
    @SerializedName("pSSerializeItem")
    @Expose
    public Boolean pSSerializeItem;
    @SerializedName("pSCustomer")
    @Expose
    public Boolean pSCustomer;
    @SerializedName("pSImportItem")
    @Expose
    public Boolean pSImportItem;
    @SerializedName("pSTaxType")
    @Expose
    public Boolean pSTaxType;
    @SerializedName("pSInvLocAdd")
    @Expose
    public Boolean pSInvLocAdd;
    @SerializedName("pSInvLoc")
    @Expose
    public Boolean pSInvLoc;
    @SerializedName("pSStateAdd")
    @Expose
    public Boolean pSStateAdd;
    @SerializedName("pSState")
    @Expose
    public Boolean pSState;
    @SerializedName("pSRemoveItem")
    @Expose
    public Boolean pSRemoveItem;
    @SerializedName("pSMore")
    @Expose
    public Boolean pSMore;
    @SerializedName("pSContactAdd")
    @Expose
    public Boolean pSContactAdd;
    @SerializedName("pSContact")
    @Expose
    public Boolean pSContact;
    @SerializedName("pSAgentAdd")
    @Expose
    public Boolean pSAgentAdd;
    @SerializedName("pSAgent")
    @Expose
    public Boolean pSAgent;
    @SerializedName("pSCurrencyAdd")
    @Expose
    public Boolean pSCurrencyAdd;
    @SerializedName("pSCurrency")
    @Expose
    public Boolean pSCurrency;
    @SerializedName("pSEmployeeAdd")
    @Expose
    public Boolean pSEmployeeAdd;
    @SerializedName("pSEmployee")
    @Expose
    public Boolean pSEmployee;
    @SerializedName("pSExchangeRateAdd")
    @Expose
    public Boolean pSExchangeRateAdd;
    @SerializedName("pSExchangeRate")
    @Expose
    public Boolean pSExchangeRate;
    @SerializedName("pSTndCAdd")
    @Expose
    public Boolean pSTndCAdd;
    @SerializedName("pSTandC")
    @Expose
    public Boolean pSTandC;
    @SerializedName("pSCCAdd")
    @Expose
    public Boolean pSCCAdd;
    @SerializedName("pSCC")
    @Expose
    public Boolean pSCC;
    @SerializedName("pSSMAdd")
    @Expose
    public Boolean pSSMAdd;
    @SerializedName("pSSM")
    @Expose
    public Boolean pSSM;
    @SerializedName("pSShippingRefNo")
    @Expose
    public Boolean pSShippingRefNo;
    @SerializedName("pSRefNo")
    @Expose
    public Boolean pSRefNo;
    @SerializedName("pSSaveProforma")
    @Expose
    public Boolean pSSaveProforma;
    @SerializedName("pSInvokeProforma")
    @Expose
    public Boolean pSInvokeProforma;
    @SerializedName("pSSaveDeliveryOrder")
    @Expose
    public Boolean pSSaveDeliveryOrder;
    @SerializedName("pSInvokeDeliveryOrder")
    @Expose
    public Boolean pSInvokeDeliveryOrder;
    @SerializedName("pSReturnDeliveryOrder")
    @Expose
    public Boolean pSReturnDeliveryOrder;
    @SerializedName("pSDebitNote")
    @Expose
    public Boolean pSDebitNote;
    @SerializedName("pSDraftInvoice")
    @Expose
    public Boolean pSDraftInvoice;
    @SerializedName("goldsales")
    @Expose
    public Boolean goldsales;
    @SerializedName("gSSearchBarCode")
    @Expose
    public Boolean gSSearchBarCode;
    @SerializedName("gSBarCode")
    @Expose
    public Boolean gSBarCode;
    @SerializedName("gSSearchSerializeItem")
    @Expose
    public Boolean gSSearchSerializeItem;
    @SerializedName("gSSerializeItem")
    @Expose
    public Boolean gSSerializeItem;
    @SerializedName("gSItemAdd")
    @Expose
    public Boolean gSItemAdd;
    @SerializedName("gSCustomerAdd")
    @Expose
    public Boolean gSCustomerAdd;
    @SerializedName("gSCustomerList")
    @Expose
    public Boolean gSCustomerList;
    @SerializedName("gSItemList")
    @Expose
    public Boolean gSItemList;
    @SerializedName("gSImportItem")
    @Expose
    public Boolean gSImportItem;
    @SerializedName("gSDate")
    @Expose
    public Boolean gSDate;
    @SerializedName("gSTax")
    @Expose
    public Boolean gSTax;
    @SerializedName("gSRemoveItem")
    @Expose
    public Boolean gSRemoveItem;
    @SerializedName("gSClearAll")
    @Expose
    public Boolean gSClearAll;
    @SerializedName("gSItemLength")
    @Expose
    public Boolean gSItemLength;
    @SerializedName("gSTotalAmt")
    @Expose
    public Boolean gSTotalAmt;
    @SerializedName("gSCheckOut")
    @Expose
    public Boolean gSCheckOut;
    @SerializedName("gSAdvancePayment")
    @Expose
    public Boolean gSAdvancePayment;
    @SerializedName("gSPrintList")
    @Expose
    public Boolean gSPrintList;
    @SerializedName("gSSaveSalesOrder")
    @Expose
    public Boolean gSSaveSalesOrder;
    @SerializedName("gSSalesInvoke")
    @Expose
    public Boolean gSSalesInvoke;
    @SerializedName("gSReturn")
    @Expose
    public Boolean gSReturn;
    @SerializedName("gSAdvancePaymentList")
    @Expose
    public Boolean gSAdvancePaymentList;
    @SerializedName("gSRecievePayment")
    @Expose
    public Boolean gSRecievePayment;
    @SerializedName("gSDraftInvoke")
    @Expose
    public Boolean gSDraftInvoke;
    @SerializedName("gSDraftClear")
    @Expose
    public Boolean gSDraftClear;
    @SerializedName("gSEmpty1")
    @Expose
    public Boolean gSEmpty1;
    @SerializedName("gSEmpty2")
    @Expose
    public Boolean gSEmpty2;
    @SerializedName("restaurant")
    @Expose
    public Boolean restaurant;
    @SerializedName("restItemSearch")
    @Expose
    public Boolean restItemSearch;
    @SerializedName("restBarcode")
    @Expose
    public Boolean restBarcode;
    @SerializedName("restAppendItem")
    @Expose
    public Boolean restAppendItem;
    @SerializedName("restItemAdd")
    @Expose
    public Boolean restItemAdd;
    @SerializedName("restRemoveItem")
    @Expose
    public Boolean restRemoveItem;
    @SerializedName("restTax")
    @Expose
    public Boolean restTax;
    @SerializedName("restAppendCustomer")
    @Expose
    public Boolean restAppendCustomer;
    @SerializedName("restCustomerAdd")
    @Expose
    public Boolean restCustomerAdd;
    @SerializedName("restTableAppend")
    @Expose
    public Boolean restTableAppend;
    @SerializedName("restSelectCategory")
    @Expose
    public Boolean restSelectCategory;
    @SerializedName("restSelectItem")
    @Expose
    public Boolean restSelectItem;
    @SerializedName("restTotalAmt")
    @Expose
    public Boolean restTotalAmt;
    @SerializedName("restOpenDrawer")
    @Expose
    public Boolean restOpenDrawer;
    @SerializedName("restClearAll")
    @Expose
    public Boolean restClearAll;
    @SerializedName("restPayment")
    @Expose
    public Boolean restPayment;
    @SerializedName("restSplitBill")
    @Expose
    public Boolean restSplitBill;
    @SerializedName("restRestaurentTable")
    @Expose
    public Boolean restRestaurentTable;
    @SerializedName("restPrint")
    @Expose
    public Boolean restPrint;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserAccountSetupID() {
        return userAccountSetupID;
    }

    public void setUserAccountSetupID(Long userAccountSetupID) {
        this.userAccountSetupID = userAccountSetupID;
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

    public Boolean getService() {
        return service;
    }

    public void setService(Boolean service) {
        this.service = service;
    }

    public Boolean getServiceItemAdd() {
        return serviceItemAdd;
    }

    public void setServiceItemAdd(Boolean serviceItemAdd) {
        this.serviceItemAdd = serviceItemAdd;
    }

    public Boolean getServiceCustomerAdd() {
        return serviceCustomerAdd;
    }

    public void setServiceCustomerAdd(Boolean serviceCustomerAdd) {
        this.serviceCustomerAdd = serviceCustomerAdd;
    }

    public Boolean getServiceSavesDraft() {
        return serviceSavesDraft;
    }

    public void setServiceSavesDraft(Boolean serviceSavesDraft) {
        this.serviceSavesDraft = serviceSavesDraft;
    }

    public Boolean getServiceClearDraft() {
        return serviceClearDraft;
    }

    public void setServiceClearDraft(Boolean serviceClearDraft) {
        this.serviceClearDraft = serviceClearDraft;
    }

    public Boolean getServicePrintList() {
        return servicePrintList;
    }

    public void setServicePrintList(Boolean servicePrintList) {
        this.servicePrintList = servicePrintList;
    }

    public Boolean getServiceAdvancePayment() {
        return serviceAdvancePayment;
    }

    public void setServiceAdvancePayment(Boolean serviceAdvancePayment) {
        this.serviceAdvancePayment = serviceAdvancePayment;
    }

    public Boolean getServiceSaveSalesQuotation() {
        return serviceSaveSalesQuotation;
    }

    public void setServiceSaveSalesQuotation(Boolean serviceSaveSalesQuotation) {
        this.serviceSaveSalesQuotation = serviceSaveSalesQuotation;
    }

    public Boolean getServiceInvokeSalesQuotation() {
        return serviceInvokeSalesQuotation;
    }

    public void setServiceInvokeSalesQuotation(Boolean serviceInvokeSalesQuotation) {
        this.serviceInvokeSalesQuotation = serviceInvokeSalesQuotation;
    }

    public Boolean getServiceSaveserviceOrder() {
        return serviceSaveserviceOrder;
    }

    public void setServiceSaveserviceOrder(Boolean serviceSaveserviceOrder) {
        this.serviceSaveserviceOrder = serviceSaveserviceOrder;
    }

    public Boolean getServiceAdvancePaymentList() {
        return serviceAdvancePaymentList;
    }

    public void setServiceAdvancePaymentList(Boolean serviceAdvancePaymentList) {
        this.serviceAdvancePaymentList = serviceAdvancePaymentList;
    }

    public Boolean getServiceInvokeSalesOrder() {
        return serviceInvokeSalesOrder;
    }

    public void setServiceInvokeSalesOrder(Boolean serviceInvokeSalesOrder) {
        this.serviceInvokeSalesOrder = serviceInvokeSalesOrder;
    }

    public Boolean getServiceClearAll() {
        return serviceClearAll;
    }

    public void setServiceClearAll(Boolean serviceClearAll) {
        this.serviceClearAll = serviceClearAll;
    }

    public Boolean getServiceCheckOut() {
        return serviceCheckOut;
    }

    public void setServiceCheckOut(Boolean serviceCheckOut) {
        this.serviceCheckOut = serviceCheckOut;
    }

    public Boolean getServiceReturn() {
        return serviceReturn;
    }

    public void setServiceReturn(Boolean serviceReturn) {
        this.serviceReturn = serviceReturn;
    }

    public Boolean getServiceRecievePayment() {
        return serviceRecievePayment;
    }

    public void setServiceRecievePayment(Boolean serviceRecievePayment) {
        this.serviceRecievePayment = serviceRecievePayment;
    }

    public Boolean getServiceInvokeDraft() {
        return serviceInvokeDraft;
    }

    public void setServiceInvokeDraft(Boolean serviceInvokeDraft) {
        this.serviceInvokeDraft = serviceInvokeDraft;
    }

    public Boolean getServiceSearchBarCode() {
        return serviceSearchBarCode;
    }

    public void setServiceSearchBarCode(Boolean serviceSearchBarCode) {
        this.serviceSearchBarCode = serviceSearchBarCode;
    }

    public Boolean getServiceBarCode() {
        return serviceBarCode;
    }

    public void setServiceBarCode(Boolean serviceBarCode) {
        this.serviceBarCode = serviceBarCode;
    }

    public Boolean getServiceSearchSerializeItem() {
        return serviceSearchSerializeItem;
    }

    public void setServiceSearchSerializeItem(Boolean serviceSearchSerializeItem) {
        this.serviceSearchSerializeItem = serviceSearchSerializeItem;
    }

    public Boolean getServiceSerializeItem() {
        return serviceSerializeItem;
    }

    public void setServiceSerializeItem(Boolean serviceSerializeItem) {
        this.serviceSerializeItem = serviceSerializeItem;
    }

    public Boolean getServiceCustomer() {
        return serviceCustomer;
    }

    public void setServiceCustomer(Boolean serviceCustomer) {
        this.serviceCustomer = serviceCustomer;
    }

    public Boolean getServiceImportItem() {
        return serviceImportItem;
    }

    public void setServiceImportItem(Boolean serviceImportItem) {
        this.serviceImportItem = serviceImportItem;
    }

    public Boolean getServiceTaxType() {
        return serviceTaxType;
    }

    public void setServiceTaxType(Boolean serviceTaxType) {
        this.serviceTaxType = serviceTaxType;
    }

    public Boolean getServiceInvLocAdd() {
        return serviceInvLocAdd;
    }

    public void setServiceInvLocAdd(Boolean serviceInvLocAdd) {
        this.serviceInvLocAdd = serviceInvLocAdd;
    }

    public Boolean getServiceInvLoc() {
        return serviceInvLoc;
    }

    public void setServiceInvLoc(Boolean serviceInvLoc) {
        this.serviceInvLoc = serviceInvLoc;
    }

    public Boolean getServiceStateAdd() {
        return serviceStateAdd;
    }

    public void setServiceStateAdd(Boolean serviceStateAdd) {
        this.serviceStateAdd = serviceStateAdd;
    }

    public Boolean getServiceState() {
        return serviceState;
    }

    public void setServiceState(Boolean serviceState) {
        this.serviceState = serviceState;
    }

    public Boolean getServiceRemoveItem() {
        return serviceRemoveItem;
    }

    public void setServiceRemoveItem(Boolean serviceRemoveItem) {
        this.serviceRemoveItem = serviceRemoveItem;
    }

    public Boolean getServiceMore() {
        return serviceMore;
    }

    public void setServiceMore(Boolean serviceMore) {
        this.serviceMore = serviceMore;
    }

    public Boolean getServiceContactAdd() {
        return serviceContactAdd;
    }

    public void setServiceContactAdd(Boolean serviceContactAdd) {
        this.serviceContactAdd = serviceContactAdd;
    }

    public Boolean getServiceContact() {
        return serviceContact;
    }

    public void setServiceContact(Boolean serviceContact) {
        this.serviceContact = serviceContact;
    }

    public Boolean getServiceAgentAdd() {
        return serviceAgentAdd;
    }

    public void setServiceAgentAdd(Boolean serviceAgentAdd) {
        this.serviceAgentAdd = serviceAgentAdd;
    }

    public Boolean getServiceAgent() {
        return serviceAgent;
    }

    public void setServiceAgent(Boolean serviceAgent) {
        this.serviceAgent = serviceAgent;
    }

    public Boolean getServiceCurrencyAdd() {
        return serviceCurrencyAdd;
    }

    public void setServiceCurrencyAdd(Boolean serviceCurrencyAdd) {
        this.serviceCurrencyAdd = serviceCurrencyAdd;
    }

    public Boolean getServiceCurrency() {
        return serviceCurrency;
    }

    public void setServiceCurrency(Boolean serviceCurrency) {
        this.serviceCurrency = serviceCurrency;
    }

    public Boolean getServiceEmployeeAdd() {
        return serviceEmployeeAdd;
    }

    public void setServiceEmployeeAdd(Boolean serviceEmployeeAdd) {
        this.serviceEmployeeAdd = serviceEmployeeAdd;
    }

    public Boolean getServiceEmployee() {
        return serviceEmployee;
    }

    public void setServiceEmployee(Boolean serviceEmployee) {
        this.serviceEmployee = serviceEmployee;
    }

    public Boolean getServiceExchangeRateAdd() {
        return serviceExchangeRateAdd;
    }

    public void setServiceExchangeRateAdd(Boolean serviceExchangeRateAdd) {
        this.serviceExchangeRateAdd = serviceExchangeRateAdd;
    }

    public Boolean getServiceExchangeRate() {
        return serviceExchangeRate;
    }

    public void setServiceExchangeRate(Boolean serviceExchangeRate) {
        this.serviceExchangeRate = serviceExchangeRate;
    }

    public Boolean getServiceTndCAdd() {
        return serviceTndCAdd;
    }

    public void setServiceTndCAdd(Boolean serviceTndCAdd) {
        this.serviceTndCAdd = serviceTndCAdd;
    }

    public Boolean getServiceTandC() {
        return serviceTandC;
    }

    public void setServiceTandC(Boolean serviceTandC) {
        this.serviceTandC = serviceTandC;
    }

    public Boolean getServiceCCAdd() {
        return serviceCCAdd;
    }

    public void setServiceCCAdd(Boolean serviceCCAdd) {
        this.serviceCCAdd = serviceCCAdd;
    }

    public Boolean getServiceCC() {
        return serviceCC;
    }

    public void setServiceCC(Boolean serviceCC) {
        this.serviceCC = serviceCC;
    }

    public Boolean getServiceSMAdd() {
        return serviceSMAdd;
    }

    public void setServiceSMAdd(Boolean serviceSMAdd) {
        this.serviceSMAdd = serviceSMAdd;
    }

    public Boolean getServiceSM() {
        return serviceSM;
    }

    public void setServiceSM(Boolean serviceSM) {
        this.serviceSM = serviceSM;
    }

    public Boolean getServiceShippingRefNo() {
        return serviceShippingRefNo;
    }

    public void setServiceShippingRefNo(Boolean serviceShippingRefNo) {
        this.serviceShippingRefNo = serviceShippingRefNo;
    }

    public Boolean getServiceRefNo() {
        return serviceRefNo;
    }

    public void setServiceRefNo(Boolean serviceRefNo) {
        this.serviceRefNo = serviceRefNo;
    }

    public Boolean getServiceSaveProforma() {
        return serviceSaveProforma;
    }

    public void setServiceSaveProforma(Boolean serviceSaveProforma) {
        this.serviceSaveProforma = serviceSaveProforma;
    }

    public Boolean getServiceInvokeProforma() {
        return serviceInvokeProforma;
    }

    public void setServiceInvokeProforma(Boolean serviceInvokeProforma) {
        this.serviceInvokeProforma = serviceInvokeProforma;
    }

    public Boolean getServiceSaveDeliveryOrder() {
        return serviceSaveDeliveryOrder;
    }

    public void setServiceSaveDeliveryOrder(Boolean serviceSaveDeliveryOrder) {
        this.serviceSaveDeliveryOrder = serviceSaveDeliveryOrder;
    }

    public Boolean getServiceInvokeDeliveryOrder() {
        return serviceInvokeDeliveryOrder;
    }

    public void setServiceInvokeDeliveryOrder(Boolean serviceInvokeDeliveryOrder) {
        this.serviceInvokeDeliveryOrder = serviceInvokeDeliveryOrder;
    }

    public Boolean getServiceReturnDeliveryOrder() {
        return serviceReturnDeliveryOrder;
    }

    public void setServiceReturnDeliveryOrder(Boolean serviceReturnDeliveryOrder) {
        this.serviceReturnDeliveryOrder = serviceReturnDeliveryOrder;
    }

    public Boolean getServiceDebitNote() {
        return serviceDebitNote;
    }

    public void setServiceDebitNote(Boolean serviceDebitNote) {
        this.serviceDebitNote = serviceDebitNote;
    }

    public Boolean getServicedraftInvoice() {
        return servicedraftInvoice;
    }

    public void setServicedraftInvoice(Boolean servicedraftInvoice) {
        this.servicedraftInvoice = servicedraftInvoice;
    }

    public Boolean getSales() {
        return sales;
    }

    public void setSales(Boolean sales) {
        this.sales = sales;
    }

    public Boolean getSalesSearchBarCode() {
        return salesSearchBarCode;
    }

    public void setSalesSearchBarCode(Boolean salesSearchBarCode) {
        this.salesSearchBarCode = salesSearchBarCode;
    }

    public Boolean getSalesBarCode() {
        return salesBarCode;
    }

    public void setSalesBarCode(Boolean salesBarCode) {
        this.salesBarCode = salesBarCode;
    }

    public Boolean getSalesSearchSerializeItem() {
        return salesSearchSerializeItem;
    }

    public void setSalesSearchSerializeItem(Boolean salesSearchSerializeItem) {
        this.salesSearchSerializeItem = salesSearchSerializeItem;
    }

    public Boolean getSalesSerializeItem() {
        return salesSerializeItem;
    }

    public void setSalesSerializeItem(Boolean salesSerializeItem) {
        this.salesSerializeItem = salesSerializeItem;
    }

    public Boolean getSalesCustomer() {
        return salesCustomer;
    }

    public void setSalesCustomer(Boolean salesCustomer) {
        this.salesCustomer = salesCustomer;
    }

    public Boolean getSalesImportItem() {
        return salesImportItem;
    }

    public void setSalesImportItem(Boolean salesImportItem) {
        this.salesImportItem = salesImportItem;
    }

    public Boolean getSalesTaxType() {
        return salesTaxType;
    }

    public void setSalesTaxType(Boolean salesTaxType) {
        this.salesTaxType = salesTaxType;
    }

    public Boolean getSalesInvLocAdd() {
        return salesInvLocAdd;
    }

    public void setSalesInvLocAdd(Boolean salesInvLocAdd) {
        this.salesInvLocAdd = salesInvLocAdd;
    }

    public Boolean getSalesInvLoc() {
        return salesInvLoc;
    }

    public void setSalesInvLoc(Boolean salesInvLoc) {
        this.salesInvLoc = salesInvLoc;
    }

    public Boolean getSalesStateAdd() {
        return salesStateAdd;
    }

    public void setSalesStateAdd(Boolean salesStateAdd) {
        this.salesStateAdd = salesStateAdd;
    }

    public Boolean getSalesState() {
        return salesState;
    }

    public void setSalesState(Boolean salesState) {
        this.salesState = salesState;
    }

    public Boolean getSalesRemoveItem() {
        return salesRemoveItem;
    }

    public void setSalesRemoveItem(Boolean salesRemoveItem) {
        this.salesRemoveItem = salesRemoveItem;
    }

    public Boolean getSalesMore() {
        return salesMore;
    }

    public void setSalesMore(Boolean salesMore) {
        this.salesMore = salesMore;
    }

    public Boolean getSalesContactAdd() {
        return salesContactAdd;
    }

    public void setSalesContactAdd(Boolean salesContactAdd) {
        this.salesContactAdd = salesContactAdd;
    }

    public Boolean getSalesContact() {
        return salesContact;
    }

    public void setSalesContact(Boolean salesContact) {
        this.salesContact = salesContact;
    }

    public Boolean getSalesAgentAdd() {
        return salesAgentAdd;
    }

    public void setSalesAgentAdd(Boolean salesAgentAdd) {
        this.salesAgentAdd = salesAgentAdd;
    }

    public Boolean getSalesAgent() {
        return salesAgent;
    }

    public void setSalesAgent(Boolean salesAgent) {
        this.salesAgent = salesAgent;
    }

    public Boolean getSalesCurrencyAdd() {
        return salesCurrencyAdd;
    }

    public void setSalesCurrencyAdd(Boolean salesCurrencyAdd) {
        this.salesCurrencyAdd = salesCurrencyAdd;
    }

    public Boolean getSalesCurrency() {
        return salesCurrency;
    }

    public void setSalesCurrency(Boolean salesCurrency) {
        this.salesCurrency = salesCurrency;
    }

    public Boolean getSalesEmployeeAdd() {
        return salesEmployeeAdd;
    }

    public void setSalesEmployeeAdd(Boolean salesEmployeeAdd) {
        this.salesEmployeeAdd = salesEmployeeAdd;
    }

    public Boolean getSalesEmployee() {
        return salesEmployee;
    }

    public void setSalesEmployee(Boolean salesEmployee) {
        this.salesEmployee = salesEmployee;
    }

    public Boolean getSalesExchangeRateAdd() {
        return salesExchangeRateAdd;
    }

    public void setSalesExchangeRateAdd(Boolean salesExchangeRateAdd) {
        this.salesExchangeRateAdd = salesExchangeRateAdd;
    }

    public Boolean getSalesExchangeRate() {
        return salesExchangeRate;
    }

    public void setSalesExchangeRate(Boolean salesExchangeRate) {
        this.salesExchangeRate = salesExchangeRate;
    }

    public Boolean getSalesTndCAdd() {
        return salesTndCAdd;
    }

    public void setSalesTndCAdd(Boolean salesTndCAdd) {
        this.salesTndCAdd = salesTndCAdd;
    }

    public Boolean getSalesTandC() {
        return salesTandC;
    }

    public void setSalesTandC(Boolean salesTandC) {
        this.salesTandC = salesTandC;
    }

    public Boolean getSalesCCAdd() {
        return salesCCAdd;
    }

    public void setSalesCCAdd(Boolean salesCCAdd) {
        this.salesCCAdd = salesCCAdd;
    }

    public Boolean getSalesCC() {
        return salesCC;
    }

    public void setSalesCC(Boolean salesCC) {
        this.salesCC = salesCC;
    }

    public Boolean getSalesSMAdd() {
        return salesSMAdd;
    }

    public void setSalesSMAdd(Boolean salesSMAdd) {
        this.salesSMAdd = salesSMAdd;
    }

    public Boolean getSalesSM() {
        return salesSM;
    }

    public void setSalesSM(Boolean salesSM) {
        this.salesSM = salesSM;
    }

    public Boolean getSalesShippingRefNo() {
        return salesShippingRefNo;
    }

    public void setSalesShippingRefNo(Boolean salesShippingRefNo) {
        this.salesShippingRefNo = salesShippingRefNo;
    }

    public Boolean getSalesRefNo() {
        return salesRefNo;
    }

    public void setSalesRefNo(Boolean salesRefNo) {
        this.salesRefNo = salesRefNo;
    }

    public Boolean getSalesSaveProforma() {
        return salesSaveProforma;
    }

    public void setSalesSaveProforma(Boolean salesSaveProforma) {
        this.salesSaveProforma = salesSaveProforma;
    }

    public Boolean getSalesInvokeProforma() {
        return salesInvokeProforma;
    }

    public void setSalesInvokeProforma(Boolean salesInvokeProforma) {
        this.salesInvokeProforma = salesInvokeProforma;
    }

    public Boolean getSalesSaveDeliveryOrder() {
        return salesSaveDeliveryOrder;
    }

    public void setSalesSaveDeliveryOrder(Boolean salesSaveDeliveryOrder) {
        this.salesSaveDeliveryOrder = salesSaveDeliveryOrder;
    }

    public Boolean getSalesInvokeDeliveryOrder() {
        return salesInvokeDeliveryOrder;
    }

    public void setSalesInvokeDeliveryOrder(Boolean salesInvokeDeliveryOrder) {
        this.salesInvokeDeliveryOrder = salesInvokeDeliveryOrder;
    }

    public Boolean getSalesReturnDeliveryOrder() {
        return salesReturnDeliveryOrder;
    }

    public void setSalesReturnDeliveryOrder(Boolean salesReturnDeliveryOrder) {
        this.salesReturnDeliveryOrder = salesReturnDeliveryOrder;
    }

    public Boolean getSalesSaveSalesQuotation() {
        return salesSaveSalesQuotation;
    }

    public void setSalesSaveSalesQuotation(Boolean salesSaveSalesQuotation) {
        this.salesSaveSalesQuotation = salesSaveSalesQuotation;
    }

    public Boolean getSalesInvokeSalesQuotation() {
        return salesInvokeSalesQuotation;
    }

    public void setSalesInvokeSalesQuotation(Boolean salesInvokeSalesQuotation) {
        this.salesInvokeSalesQuotation = salesInvokeSalesQuotation;
    }

    public Boolean getSalesDebitNote() {
        return salesDebitNote;
    }

    public void setSalesDebitNote(Boolean salesDebitNote) {
        this.salesDebitNote = salesDebitNote;
    }

    public Boolean getDraftInvoice() {
        return draftInvoice;
    }

    public void setDraftInvoice(Boolean draftInvoice) {
        this.draftInvoice = draftInvoice;
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

    public Boolean getPurchaseSavePurchaseQuotation() {
        return purchaseSavePurchaseQuotation;
    }

    public void setPurchaseSavePurchaseQuotation(Boolean purchaseSavePurchaseQuotation) {
        this.purchaseSavePurchaseQuotation = purchaseSavePurchaseQuotation;
    }

    public Boolean getPurchaseInvokePurchaseQuotation() {
        return purchaseInvokePurchaseQuotation;
    }

    public void setPurchaseInvokePurchaseQuotation(Boolean purchaseInvokePurchaseQuotation) {
        this.purchaseInvokePurchaseQuotation = purchaseInvokePurchaseQuotation;
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

    public Boolean getPurSearchBarCode() {
        return purSearchBarCode;
    }

    public void setPurSearchBarCode(Boolean purSearchBarCode) {
        this.purSearchBarCode = purSearchBarCode;
    }

    public Boolean getPurBarCode() {
        return purBarCode;
    }

    public void setPurBarCode(Boolean purBarCode) {
        this.purBarCode = purBarCode;
    }

    public Boolean getPurRemoveItem() {
        return purRemoveItem;
    }

    public void setPurRemoveItem(Boolean purRemoveItem) {
        this.purRemoveItem = purRemoveItem;
    }

    public Boolean getPurMore() {
        return purMore;
    }

    public void setPurMore(Boolean purMore) {
        this.purMore = purMore;
    }

    public Boolean getRevChargeInv() {
        return revChargeInv;
    }

    public void setRevChargeInv(Boolean revChargeInv) {
        this.revChargeInv = revChargeInv;
    }

    public Boolean getPurCreditNote() {
        return purCreditNote;
    }

    public void setPurCreditNote(Boolean purCreditNote) {
        this.purCreditNote = purCreditNote;
    }

    public Boolean getPurSaveRecItem() {
        return purSaveRecItem;
    }

    public void setPurSaveRecItem(Boolean purSaveRecItem) {
        this.purSaveRecItem = purSaveRecItem;
    }

    public Boolean getPurInvokeRecItem() {
        return purInvokeRecItem;
    }

    public void setPurInvokeRecItem(Boolean purInvokeRecItem) {
        this.purInvokeRecItem = purInvokeRecItem;
    }

    public Boolean getPurReturnRecItem() {
        return purReturnRecItem;
    }

    public void setPurReturnRecItem(Boolean purReturnRecItem) {
        this.purReturnRecItem = purReturnRecItem;
    }

    public Boolean getPurTaxType() {
        return purTaxType;
    }

    public void setPurTaxType(Boolean purTaxType) {
        this.purTaxType = purTaxType;
    }

    public Boolean getPurInvLocAdd() {
        return purInvLocAdd;
    }

    public void setPurInvLocAdd(Boolean purInvLocAdd) {
        this.purInvLocAdd = purInvLocAdd;
    }

    public Boolean getPurInvLoc() {
        return purInvLoc;
    }

    public void setPurInvLoc(Boolean purInvLoc) {
        this.purInvLoc = purInvLoc;
    }

    public Boolean getPurStateAdd() {
        return purStateAdd;
    }

    public void setPurStateAdd(Boolean purStateAdd) {
        this.purStateAdd = purStateAdd;
    }

    public Boolean getPurState() {
        return purState;
    }

    public void setPurState(Boolean purState) {
        this.purState = purState;
    }

    public Boolean getPurContactAdd() {
        return purContactAdd;
    }

    public void setPurContactAdd(Boolean purContactAdd) {
        this.purContactAdd = purContactAdd;
    }

    public Boolean getPurContact() {
        return purContact;
    }

    public void setPurContact(Boolean purContact) {
        this.purContact = purContact;
    }

    public Boolean getPurAgentAdd() {
        return purAgentAdd;
    }

    public void setPurAgentAdd(Boolean purAgentAdd) {
        this.purAgentAdd = purAgentAdd;
    }

    public Boolean getPurAgent() {
        return purAgent;
    }

    public void setPurAgent(Boolean purAgent) {
        this.purAgent = purAgent;
    }

    public Boolean getPurCurrencyAdd() {
        return purCurrencyAdd;
    }

    public void setPurCurrencyAdd(Boolean purCurrencyAdd) {
        this.purCurrencyAdd = purCurrencyAdd;
    }

    public Boolean getPurCurrency() {
        return purCurrency;
    }

    public void setPurCurrency(Boolean purCurrency) {
        this.purCurrency = purCurrency;
    }

    public Boolean getPurEmployeeAdd() {
        return purEmployeeAdd;
    }

    public void setPurEmployeeAdd(Boolean purEmployeeAdd) {
        this.purEmployeeAdd = purEmployeeAdd;
    }

    public Boolean getPurEmployee() {
        return purEmployee;
    }

    public void setPurEmployee(Boolean purEmployee) {
        this.purEmployee = purEmployee;
    }

    public Boolean getPurExchangeRateAdd() {
        return purExchangeRateAdd;
    }

    public void setPurExchangeRateAdd(Boolean purExchangeRateAdd) {
        this.purExchangeRateAdd = purExchangeRateAdd;
    }

    public Boolean getPurExchangeRate() {
        return purExchangeRate;
    }

    public void setPurExchangeRate(Boolean purExchangeRate) {
        this.purExchangeRate = purExchangeRate;
    }

    public Boolean getPurTndCAdd() {
        return purTndCAdd;
    }

    public void setPurTndCAdd(Boolean purTndCAdd) {
        this.purTndCAdd = purTndCAdd;
    }

    public Boolean getPurTandC() {
        return purTandC;
    }

    public void setPurTandC(Boolean purTandC) {
        this.purTandC = purTandC;
    }

    public Boolean getPurCCAdd() {
        return purCCAdd;
    }

    public void setPurCCAdd(Boolean purCCAdd) {
        this.purCCAdd = purCCAdd;
    }

    public Boolean getPurCC() {
        return purCC;
    }

    public void setPurCC(Boolean purCC) {
        this.purCC = purCC;
    }

    public Boolean getPurSMAdd() {
        return purSMAdd;
    }

    public void setPurSMAdd(Boolean purSMAdd) {
        this.purSMAdd = purSMAdd;
    }

    public Boolean getPurSM() {
        return purSM;
    }

    public void setPurSM(Boolean purSM) {
        this.purSM = purSM;
    }

    public Boolean getPurShippingRefNo() {
        return purShippingRefNo;
    }

    public void setPurShippingRefNo(Boolean purShippingRefNo) {
        this.purShippingRefNo = purShippingRefNo;
    }

    public Boolean getPurRefNo() {
        return purRefNo;
    }

    public void setPurRefNo(Boolean purRefNo) {
        this.purRefNo = purRefNo;
    }

    public Boolean getPurImportItem() {
        return purImportItem;
    }

    public void setPurImportItem(Boolean purImportItem) {
        this.purImportItem = purImportItem;
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

    public Boolean getInventoryAttribute() {
        return inventoryAttribute;
    }

    public void setInventoryAttribute(Boolean inventoryAttribute) {
        this.inventoryAttribute = inventoryAttribute;
    }

    public Boolean getInventoryAttributeConfig() {
        return inventoryAttributeConfig;
    }

    public void setInventoryAttributeConfig(Boolean inventoryAttributeConfig) {
        this.inventoryAttributeConfig = inventoryAttributeConfig;
    }

    public Boolean getInventoryPayVoucher() {
        return inventoryPayVoucher;
    }

    public void setInventoryPayVoucher(Boolean inventoryPayVoucher) {
        this.inventoryPayVoucher = inventoryPayVoucher;
    }

    public Boolean getInventoryInvLocTransfer() {
        return inventoryInvLocTransfer;
    }

    public void setInventoryInvLocTransfer(Boolean inventoryInvLocTransfer) {
        this.inventoryInvLocTransfer = inventoryInvLocTransfer;
    }

    public Boolean getInventoryInvCountType() {
        return inventoryInvCountType;
    }

    public void setInventoryInvCountType(Boolean inventoryInvCountType) {
        this.inventoryInvCountType = inventoryInvCountType;
    }

    public Boolean getInventorySalesDiscountConfig() {
        return inventorySalesDiscountConfig;
    }

    public void setInventorySalesDiscountConfig(Boolean inventorySalesDiscountConfig) {
        this.inventorySalesDiscountConfig = inventorySalesDiscountConfig;
    }

    public Boolean getInventoryAdvDisConfig() {
        return inventoryAdvDisConfig;
    }

    public void setInventoryAdvDisConfig(Boolean inventoryAdvDisConfig) {
        this.inventoryAdvDisConfig = inventoryAdvDisConfig;
    }

    public Boolean getInventoryLoyality() {
        return inventoryLoyality;
    }

    public void setInventoryLoyality(Boolean inventoryLoyality) {
        this.inventoryLoyality = inventoryLoyality;
    }

    public Boolean getInventoryVoucher() {
        return inventoryVoucher;
    }

    public void setInventoryVoucher(Boolean inventoryVoucher) {
        this.inventoryVoucher = inventoryVoucher;
    }

    public Boolean getInventoryItemCountAdjust() {
        return inventoryItemCountAdjust;
    }

    public void setInventoryItemCountAdjust(Boolean inventoryItemCountAdjust) {
        this.inventoryItemCountAdjust = inventoryItemCountAdjust;
    }

    public Boolean getInventoryHsnCode() {
        return inventoryHsnCode;
    }

    public void setInventoryHsnCode(Boolean inventoryHsnCode) {
        this.inventoryHsnCode = inventoryHsnCode;
    }

    public Boolean getInventoryItemCommission() {
        return inventoryItemCommission;
    }

    public void setInventoryItemCommission(Boolean inventoryItemCommission) {
        this.inventoryItemCommission = inventoryItemCommission;
    }

    public Boolean getInventoryAssets() {
        return inventoryAssets;
    }

    public void setInventoryAssets(Boolean inventoryAssets) {
        this.inventoryAssets = inventoryAssets;
    }

    public Boolean getInventoryUomConverter() {
        return inventoryUomConverter;
    }

    public void setInventoryUomConverter(Boolean inventoryUomConverter) {
        this.inventoryUomConverter = inventoryUomConverter;
    }

    public Boolean getInventoryAssetsCat() {
        return inventoryAssetsCat;
    }

    public void setInventoryAssetsCat(Boolean inventoryAssetsCat) {
        this.inventoryAssetsCat = inventoryAssetsCat;
    }

    public Boolean getInventoryJSDN() {
        return inventoryJSDN;
    }

    public void setInventoryJSDN(Boolean inventoryJSDN) {
        this.inventoryJSDN = inventoryJSDN;
    }

    public Boolean getInventoryAdvTableTransfer() {
        return inventoryAdvTableTransfer;
    }

    public void setInventoryAdvTableTransfer(Boolean inventoryAdvTableTransfer) {
        this.inventoryAdvTableTransfer = inventoryAdvTableTransfer;
    }

    public Boolean getInventoryRedemption() {
        return inventoryRedemption;
    }

    public void setInventoryRedemption(Boolean inventoryRedemption) {
        this.inventoryRedemption = inventoryRedemption;
    }

    public Boolean getManufacturing() {
        return manufacturing;
    }

    public void setManufacturing(Boolean manufacturing) {
        this.manufacturing = manufacturing;
    }

    public Boolean getManufacturingAP() {
        return manufacturingAP;
    }

    public void setManufacturingAP(Boolean manufacturingAP) {
        this.manufacturingAP = manufacturingAP;
    }

    public Boolean getManufacturingPM() {
        return manufacturingPM;
    }

    public void setManufacturingPM(Boolean manufacturingPM) {
        this.manufacturingPM = manufacturingPM;
    }

    public Boolean getManufacturingAdvItemCountAdjust() {
        return manufacturingAdvItemCountAdjust;
    }

    public void setManufacturingAdvItemCountAdjust(Boolean manufacturingAdvItemCountAdjust) {
        this.manufacturingAdvItemCountAdjust = manufacturingAdvItemCountAdjust;
    }

    public Boolean getManufacturingCR() {
        return manufacturingCR;
    }

    public void setManufacturingCR(Boolean manufacturingCR) {
        this.manufacturingCR = manufacturingCR;
    }

    public Boolean getManufacturingFSCR() {
        return manufacturingFSCR;
    }

    public void setManufacturingFSCR(Boolean manufacturingFSCR) {
        this.manufacturingFSCR = manufacturingFSCR;
    }

    public Boolean getManufacturingAICAR() {
        return manufacturingAICAR;
    }

    public void setManufacturingAICAR(Boolean manufacturingAICAR) {
        this.manufacturingAICAR = manufacturingAICAR;
    }

    public Boolean getCrm() {
        return crm;
    }

    public void setCrm(Boolean crm) {
        this.crm = crm;
    }

    public Boolean getLeads() {
        return leads;
    }

    public void setLeads(Boolean leads) {
        this.leads = leads;
    }

    public Boolean getEvent() {
        return event;
    }

    public void setEvent(Boolean event) {
        this.event = event;
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

    public Boolean getTaxTax() {
        return taxTax;
    }

    public void setTaxTax(Boolean taxTax) {
        this.taxTax = taxTax;
    }

    public Boolean getTaxTaxtype() {
        return taxTaxtype;
    }

    public void setTaxTaxtype(Boolean taxTaxtype) {
        this.taxTaxtype = taxTaxtype;
    }

    public Boolean getGstIndiaGSTSR() {
        return gstIndiaGSTSR;
    }

    public void setGstIndiaGSTSR(Boolean gstIndiaGSTSR) {
        this.gstIndiaGSTSR = gstIndiaGSTSR;
    }

    public Boolean getGstIndiaGSTR1Tool() {
        return gstIndiaGSTR1Tool;
    }

    public void setGstIndiaGSTR1Tool(Boolean gstIndiaGSTR1Tool) {
        this.gstIndiaGSTR1Tool = gstIndiaGSTR1Tool;
    }

    public Boolean getGstIndiaGSTR2Tool() {
        return gstIndiaGSTR2Tool;
    }

    public void setGstIndiaGSTR2Tool(Boolean gstIndiaGSTR2Tool) {
        this.gstIndiaGSTR2Tool = gstIndiaGSTR2Tool;
    }

    public Boolean getGstIndiaGST3B() {
        return gstIndiaGST3B;
    }

    public void setGstIndiaGST3B(Boolean gstIndiaGST3B) {
        this.gstIndiaGST3B = gstIndiaGST3B;
    }

    public Boolean getGstIndiaRIPC() {
        return gstIndiaRIPC;
    }

    public void setGstIndiaRIPC(Boolean gstIndiaRIPC) {
        this.gstIndiaRIPC = gstIndiaRIPC;
    }

    public Boolean getGstIndiaGSTTRANS1() {
        return gstIndiaGSTTRANS1;
    }

    public void setGstIndiaGSTTRANS1(Boolean gstIndiaGSTTRANS1) {
        this.gstIndiaGSTTRANS1 = gstIndiaGSTTRANS1;
    }

    public Boolean getGstMalGSTSR() {
        return gstMalGSTSR;
    }

    public void setGstMalGSTSR(Boolean gstMalGSTSR) {
        this.gstMalGSTSR = gstMalGSTSR;
    }

    public Boolean getGstMalGAFExport() {
        return gstMalGAFExport;
    }

    public void setGstMalGAFExport(Boolean gstMalGAFExport) {
        this.gstMalGAFExport = gstMalGAFExport;
    }

    public Boolean getGstMalGSTReturn() {
        return gstMalGSTReturn;
    }

    public void setGstMalGSTReturn(Boolean gstMalGSTReturn) {
        this.gstMalGSTReturn = gstMalGSTReturn;
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

    public Boolean getcPConfiguratorCountry() {
        return cPConfiguratorCountry;
    }

    public void setcPConfiguratorCountry(Boolean cPConfiguratorCountry) {
        this.cPConfiguratorCountry = cPConfiguratorCountry;
    }

    public Boolean getcPConfiguratorVersionControl() {
        return cPConfiguratorVersionControl;
    }

    public void setcPConfiguratorVersionControl(Boolean cPConfiguratorVersionControl) {
        this.cPConfiguratorVersionControl = cPConfiguratorVersionControl;
    }

    public Boolean getcPConfiguratorServiceCharge() {
        return cPConfiguratorServiceCharge;
    }

    public void setcPConfiguratorServiceCharge(Boolean cPConfiguratorServiceCharge) {
        this.cPConfiguratorServiceCharge = cPConfiguratorServiceCharge;
    }

    public Boolean getcPConfiguratorLabelPrintConfig() {
        return cPConfiguratorLabelPrintConfig;
    }

    public void setcPConfiguratorLabelPrintConfig(Boolean cPConfiguratorLabelPrintConfig) {
        this.cPConfiguratorLabelPrintConfig = cPConfiguratorLabelPrintConfig;
    }

    public Boolean getcPConfiguratorOffersConfig() {
        return cPConfiguratorOffersConfig;
    }

    public void setcPConfiguratorOffersConfig(Boolean cPConfiguratorOffersConfig) {
        this.cPConfiguratorOffersConfig = cPConfiguratorOffersConfig;
    }

    public Boolean getcPConfiguratorBank() {
        return cPConfiguratorBank;
    }

    public void setcPConfiguratorBank(Boolean cPConfiguratorBank) {
        this.cPConfiguratorBank = cPConfiguratorBank;
    }

    public Boolean getcPConfiguratorAgent() {
        return cPConfiguratorAgent;
    }

    public void setcPConfiguratorAgent(Boolean cPConfiguratorAgent) {
        this.cPConfiguratorAgent = cPConfiguratorAgent;
    }

    public Boolean getcPConfiguratorCurrency() {
        return cPConfiguratorCurrency;
    }

    public void setcPConfiguratorCurrency(Boolean cPConfiguratorCurrency) {
        this.cPConfiguratorCurrency = cPConfiguratorCurrency;
    }

    public Boolean getcPConfiguratorEmployee() {
        return cPConfiguratorEmployee;
    }

    public void setcPConfiguratorEmployee(Boolean cPConfiguratorEmployee) {
        this.cPConfiguratorEmployee = cPConfiguratorEmployee;
    }

    public Boolean getcPConfiguratorExchangeRate() {
        return cPConfiguratorExchangeRate;
    }

    public void setcPConfiguratorExchangeRate(Boolean cPConfiguratorExchangeRate) {
        this.cPConfiguratorExchangeRate = cPConfiguratorExchangeRate;
    }

    public Boolean getcPConfiguratorProjectTitle() {
        return cPConfiguratorProjectTitle;
    }

    public void setcPConfiguratorProjectTitle(Boolean cPConfiguratorProjectTitle) {
        this.cPConfiguratorProjectTitle = cPConfiguratorProjectTitle;
    }

    public Boolean getcPConfiguratorShippingMethod() {
        return cPConfiguratorShippingMethod;
    }

    public void setcPConfiguratorShippingMethod(Boolean cPConfiguratorShippingMethod) {
        this.cPConfiguratorShippingMethod = cPConfiguratorShippingMethod;
    }

    public Boolean getcPConfiguratorEmailserver() {
        return cPConfiguratorEmailserver;
    }

    public void setcPConfiguratorEmailserver(Boolean cPConfiguratorEmailserver) {
        this.cPConfiguratorEmailserver = cPConfiguratorEmailserver;
    }

    public Boolean getcPConfiguratorPaymentMethod() {
        return cPConfiguratorPaymentMethod;
    }

    public void setcPConfiguratorPaymentMethod(Boolean cPConfiguratorPaymentMethod) {
        this.cPConfiguratorPaymentMethod = cPConfiguratorPaymentMethod;
    }

    public Boolean getcPConfiguratorConfiguration() {
        return cPConfiguratorConfiguration;
    }

    public void setcPConfiguratorConfiguration(Boolean cPConfiguratorConfiguration) {
        this.cPConfiguratorConfiguration = cPConfiguratorConfiguration;
    }

    public Boolean getControlPanelCompanySetup() {
        return controlPanelCompanySetup;
    }

    public void setControlPanelCompanySetup(Boolean controlPanelCompanySetup) {
        this.controlPanelCompanySetup = controlPanelCompanySetup;
    }

    public Boolean getcPCompanySetupCI() {
        return cPCompanySetupCI;
    }

    public void setcPCompanySetupCI(Boolean cPCompanySetupCI) {
        this.cPCompanySetupCI = cPCompanySetupCI;
    }

    public Boolean getcPCompanySetupFY() {
        return cPCompanySetupFY;
    }

    public void setcPCompanySetupFY(Boolean cPCompanySetupFY) {
        this.cPCompanySetupFY = cPCompanySetupFY;
    }

    public Boolean getcPCompanySetupCA() {
        return cPCompanySetupCA;
    }

    public void setcPCompanySetupCA(Boolean cPCompanySetupCA) {
        this.cPCompanySetupCA = cPCompanySetupCA;
    }

    public Boolean getcPCompanySetupHSN() {
        return cPCompanySetupHSN;
    }

    public void setcPCompanySetupHSN(Boolean cPCompanySetupHSN) {
        this.cPCompanySetupHSN = cPCompanySetupHSN;
    }

    public Boolean getcPCompanySetupAlerts() {
        return cPCompanySetupAlerts;
    }

    public void setcPCompanySetupAlerts(Boolean cPCompanySetupAlerts) {
        this.cPCompanySetupAlerts = cPCompanySetupAlerts;
    }

    public Boolean getcPCompanySetupBandR() {
        return cPCompanySetupBandR;
    }

    public void setcPCompanySetupBandR(Boolean cPCompanySetupBandR) {
        this.cPCompanySetupBandR = cPCompanySetupBandR;
    }

    public Boolean getcPCompanySetupRS() {
        return cPCompanySetupRS;
    }

    public void setcPCompanySetupRS(Boolean cPCompanySetupRS) {
        this.cPCompanySetupRS = cPCompanySetupRS;
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

    public Boolean getControlPanelDSTable() {
        return controlPanelDSTable;
    }

    public void setControlPanelDSTable(Boolean controlPanelDSTable) {
        this.controlPanelDSTable = controlPanelDSTable;
    }

    public Boolean getControlPanelDSTableConfig() {
        return controlPanelDSTableConfig;
    }

    public void setControlPanelDSTableConfig(Boolean controlPanelDSTableConfig) {
        this.controlPanelDSTableConfig = controlPanelDSTableConfig;
    }

    public Boolean getControlPanelDSEandSMSConfig() {
        return controlPanelDSEandSMSConfig;
    }

    public void setControlPanelDSEandSMSConfig(Boolean controlPanelDSEandSMSConfig) {
        this.controlPanelDSEandSMSConfig = controlPanelDSEandSMSConfig;
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

    public Boolean getReportPurchaseRIL() {
        return reportPurchaseRIL;
    }

    public void setReportPurchaseRIL(Boolean reportPurchaseRIL) {
        this.reportPurchaseRIL = reportPurchaseRIL;
    }

    public Boolean getReportPurchaseSSL() {
        return reportPurchaseSSL;
    }

    public void setReportPurchaseSSL(Boolean reportPurchaseSSL) {
        this.reportPurchaseSSL = reportPurchaseSSL;
    }

    public Boolean getReportPurchaseSL() {
        return reportPurchaseSL;
    }

    public void setReportPurchaseSL(Boolean reportPurchaseSL) {
        this.reportPurchaseSL = reportPurchaseSL;
    }

    public Boolean getReportPurchaseCA() {
        return reportPurchaseCA;
    }

    public void setReportPurchaseCA(Boolean reportPurchaseCA) {
        this.reportPurchaseCA = reportPurchaseCA;
    }

    public Boolean getReportPurchaseCPA() {
        return reportPurchaseCPA;
    }

    public void setReportPurchaseCPA(Boolean reportPurchaseCPA) {
        this.reportPurchaseCPA = reportPurchaseCPA;
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

    public Boolean getReportSaleSQL() {
        return reportSaleSQL;
    }

    public void setReportSaleSQL(Boolean reportSaleSQL) {
        this.reportSaleSQL = reportSaleSQL;
    }

    public Boolean getReportSalePDL() {
        return reportSalePDL;
    }

    public void setReportSalePDL(Boolean reportSalePDL) {
        this.reportSalePDL = reportSalePDL;
    }

    public Boolean getReportSaleCS() {
        return reportSaleCS;
    }

    public void setReportSaleCS(Boolean reportSaleCS) {
        this.reportSaleCS = reportSaleCS;
    }

    public Boolean getReportSaleDA() {
        return reportSaleDA;
    }

    public void setReportSaleDA(Boolean reportSaleDA) {
        this.reportSaleDA = reportSaleDA;
    }

    public Boolean getReportSaleCL() {
        return reportSaleCL;
    }

    public void setReportSaleCL(Boolean reportSaleCL) {
        this.reportSaleCL = reportSaleCL;
    }

    public Boolean getReportSaleAL() {
        return reportSaleAL;
    }

    public void setReportSaleAL(Boolean reportSaleAL) {
        this.reportSaleAL = reportSaleAL;
    }

    public Boolean getReportSaleCSI() {
        return reportSaleCSI;
    }

    public void setReportSaleCSI(Boolean reportSaleCSI) {
        this.reportSaleCSI = reportSaleCSI;
    }

    public Boolean getReportSaleCLP() {
        return reportSaleCLP;
    }

    public void setReportSaleCLP(Boolean reportSaleCLP) {
        this.reportSaleCLP = reportSaleCLP;
    }

    public Boolean getReportSaleML() {
        return reportSaleML;
    }

    public void setReportSaleML(Boolean reportSaleML) {
        this.reportSaleML = reportSaleML;
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

    public Boolean getReportInventoryGRNVR() {
        return reportInventoryGRNVR;
    }

    public void setReportInventoryGRNVR(Boolean reportInventoryGRNVR) {
        this.reportInventoryGRNVR = reportInventoryGRNVR;
    }

    public Boolean getReportInventoryIPR() {
        return reportInventoryIPR;
    }

    public void setReportInventoryIPR(Boolean reportInventoryIPR) {
        this.reportInventoryIPR = reportInventoryIPR;
    }

    public Boolean getReportInventoryINMR() {
        return reportInventoryINMR;
    }

    public void setReportInventoryINMR(Boolean reportInventoryINMR) {
        this.reportInventoryINMR = reportInventoryINMR;
    }

    public Boolean getReportInventoryIMR() {
        return reportInventoryIMR;
    }

    public void setReportInventoryIMR(Boolean reportInventoryIMR) {
        this.reportInventoryIMR = reportInventoryIMR;
    }

    public Boolean getReportInventoryIL() {
        return reportInventoryIL;
    }

    public void setReportInventoryIL(Boolean reportInventoryIL) {
        this.reportInventoryIL = reportInventoryIL;
    }

    public Boolean getReportInventoryOGRNR() {
        return reportInventoryOGRNR;
    }

    public void setReportInventoryOGRNR(Boolean reportInventoryOGRNR) {
        this.reportInventoryOGRNR = reportInventoryOGRNR;
    }

    public Boolean getReportInventoryASCR() {
        return reportInventoryASCR;
    }

    public void setReportInventoryASCR(Boolean reportInventoryASCR) {
        this.reportInventoryASCR = reportInventoryASCR;
    }

    public Boolean getReportInventoryASSCR() {
        return reportInventoryASSCR;
    }

    public void setReportInventoryASSCR(Boolean reportInventoryASSCR) {
        this.reportInventoryASSCR = reportInventoryASSCR;
    }

    public Boolean getReportInventoryRPL() {
        return reportInventoryRPL;
    }

    public void setReportInventoryRPL(Boolean reportInventoryRPL) {
        this.reportInventoryRPL = reportInventoryRPL;
    }

    public Boolean getReportInventoryRPR() {
        return reportInventoryRPR;
    }

    public void setReportInventoryRPR(Boolean reportInventoryRPR) {
        this.reportInventoryRPR = reportInventoryRPR;
    }

    public Boolean getReportInventoryDEL() {
        return reportInventoryDEL;
    }

    public void setReportInventoryDEL(Boolean reportInventoryDEL) {
        this.reportInventoryDEL = reportInventoryDEL;
    }

    public Boolean getReportInventoryBER() {
        return reportInventoryBER;
    }

    public void setReportInventoryBER(Boolean reportInventoryBER) {
        this.reportInventoryBER = reportInventoryBER;
    }

    public Boolean getReportInventorySI() {
        return reportInventorySI;
    }

    public void setReportInventorySI(Boolean reportInventorySI) {
        this.reportInventorySI = reportInventorySI;
    }

    public Boolean getReportInventoryBI() {
        return reportInventoryBI;
    }

    public void setReportInventoryBI(Boolean reportInventoryBI) {
        this.reportInventoryBI = reportInventoryBI;
    }

    public Boolean getReportInventoryADVSCR() {
        return reportInventoryADVSCR;
    }

    public void setReportInventoryADVSCR(Boolean reportInventoryADVSCR) {
        this.reportInventoryADVSCR = reportInventoryADVSCR;
    }

    public Boolean getReportInventoryADVSSR() {
        return reportInventoryADVSSR;
    }

    public void setReportInventoryADVSSR(Boolean reportInventoryADVSSR) {
        this.reportInventoryADVSSR = reportInventoryADVSSR;
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

    public Boolean getReportAnalysisAccountBalance() {
        return reportAnalysisAccountBalance;
    }

    public void setReportAnalysisAccountBalance(Boolean reportAnalysisAccountBalance) {
        this.reportAnalysisAccountBalance = reportAnalysisAccountBalance;
    }

    public Boolean getReportAnalysisCRR() {
        return reportAnalysisCRR;
    }

    public void setReportAnalysisCRR(Boolean reportAnalysisCRR) {
        this.reportAnalysisCRR = reportAnalysisCRR;
    }

    public Boolean getReportAnalysisCIP() {
        return reportAnalysisCIP;
    }

    public void setReportAnalysisCIP(Boolean reportAnalysisCIP) {
        this.reportAnalysisCIP = reportAnalysisCIP;
    }

    public Boolean getReportAnalysisSIP() {
        return reportAnalysisSIP;
    }

    public void setReportAnalysisSIP(Boolean reportAnalysisSIP) {
        this.reportAnalysisSIP = reportAnalysisSIP;
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

    public Boolean getReportFStatementPLLocWise() {
        return reportFStatementPLLocWise;
    }

    public void setReportFStatementPLLocWise(Boolean reportFStatementPLLocWise) {
        this.reportFStatementPLLocWise = reportFStatementPLLocWise;
    }

    public Boolean getReportFStatementDB() {
        return reportFStatementDB;
    }

    public void setReportFStatementDB(Boolean reportFStatementDB) {
        this.reportFStatementDB = reportFStatementDB;
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

    public Boolean getReportBankReconCheques() {
        return reportBankReconCheques;
    }

    public void setReportBankReconCheques(Boolean reportBankReconCheques) {
        this.reportBankReconCheques = reportBankReconCheques;
    }

    public Boolean getReportBankReconStmnt() {
        return reportBankReconStmnt;
    }

    public void setReportBankReconStmnt(Boolean reportBankReconStmnt) {
        this.reportBankReconStmnt = reportBankReconStmnt;
    }

    public Boolean getGoldPurchases() {
        return goldPurchases;
    }

    public void setGoldPurchases(Boolean goldPurchases) {
        this.goldPurchases = goldPurchases;
    }

    public Boolean getgPSearchSerializeItem() {
        return gPSearchSerializeItem;
    }

    public void setgPSearchSerializeItem(Boolean gPSearchSerializeItem) {
        this.gPSearchSerializeItem = gPSearchSerializeItem;
    }

    public Boolean getgPSerializeItem() {
        return gPSerializeItem;
    }

    public void setgPSerializeItem(Boolean gPSerializeItem) {
        this.gPSerializeItem = gPSerializeItem;
    }

    public Boolean getgPSearchBarCode() {
        return gPSearchBarCode;
    }

    public void setgPSearchBarCode(Boolean gPSearchBarCode) {
        this.gPSearchBarCode = gPSearchBarCode;
    }

    public Boolean getgPBarCode() {
        return gPBarCode;
    }

    public void setgPBarCode(Boolean gPBarCode) {
        this.gPBarCode = gPBarCode;
    }

    public Boolean getgPItemAdd() {
        return gPItemAdd;
    }

    public void setgPItemAdd(Boolean gPItemAdd) {
        this.gPItemAdd = gPItemAdd;
    }

    public Boolean getgPCustomerAdd() {
        return gPCustomerAdd;
    }

    public void setgPCustomerAdd(Boolean gPCustomerAdd) {
        this.gPCustomerAdd = gPCustomerAdd;
    }

    public Boolean getgPSavesDraft() {
        return gPSavesDraft;
    }

    public void setgPSavesDraft(Boolean gPSavesDraft) {
        this.gPSavesDraft = gPSavesDraft;
    }

    public Boolean getgPClearDraft() {
        return gPClearDraft;
    }

    public void setgPClearDraft(Boolean gPClearDraft) {
        this.gPClearDraft = gPClearDraft;
    }

    public Boolean getgPPrintList() {
        return gPPrintList;
    }

    public void setgPPrintList(Boolean gPPrintList) {
        this.gPPrintList = gPPrintList;
    }

    public Boolean getgPAdvancePayment() {
        return gPAdvancePayment;
    }

    public void setgPAdvancePayment(Boolean gPAdvancePayment) {
        this.gPAdvancePayment = gPAdvancePayment;
    }

    public Boolean getgPSaveQuotation() {
        return gPSaveQuotation;
    }

    public void setgPSaveQuotation(Boolean gPSaveQuotation) {
        this.gPSaveQuotation = gPSaveQuotation;
    }

    public Boolean getgPInvokeQuotation() {
        return gPInvokeQuotation;
    }

    public void setgPInvokeQuotation(Boolean gPInvokeQuotation) {
        this.gPInvokeQuotation = gPInvokeQuotation;
    }

    public Boolean getgPSaveOrder() {
        return gPSaveOrder;
    }

    public void setgPSaveOrder(Boolean gPSaveOrder) {
        this.gPSaveOrder = gPSaveOrder;
    }

    public Boolean getgPAdvancePaymentList() {
        return gPAdvancePaymentList;
    }

    public void setgPAdvancePaymentList(Boolean gPAdvancePaymentList) {
        this.gPAdvancePaymentList = gPAdvancePaymentList;
    }

    public Boolean getgPInvokeOrder() {
        return gPInvokeOrder;
    }

    public void setgPInvokeOrder(Boolean gPInvokeOrder) {
        this.gPInvokeOrder = gPInvokeOrder;
    }

    public Boolean getgPClearAll() {
        return gPClearAll;
    }

    public void setgPClearAll(Boolean gPClearAll) {
        this.gPClearAll = gPClearAll;
    }

    public Boolean getgPCheckOut() {
        return gPCheckOut;
    }

    public void setgPCheckOut(Boolean gPCheckOut) {
        this.gPCheckOut = gPCheckOut;
    }

    public Boolean getgPReturn() {
        return gPReturn;
    }

    public void setgPReturn(Boolean gPReturn) {
        this.gPReturn = gPReturn;
    }

    public Boolean getgPRecievePayment() {
        return gPRecievePayment;
    }

    public void setgPRecievePayment(Boolean gPRecievePayment) {
        this.gPRecievePayment = gPRecievePayment;
    }

    public Boolean getgPInvokeDraft() {
        return gPInvokeDraft;
    }

    public void setgPInvokeDraft(Boolean gPInvokeDraft) {
        this.gPInvokeDraft = gPInvokeDraft;
    }

    public Boolean getgPCustomer() {
        return gPCustomer;
    }

    public void setgPCustomer(Boolean gPCustomer) {
        this.gPCustomer = gPCustomer;
    }

    public Boolean getgPImportItem() {
        return gPImportItem;
    }

    public void setgPImportItem(Boolean gPImportItem) {
        this.gPImportItem = gPImportItem;
    }

    public Boolean getgPTaxType() {
        return gPTaxType;
    }

    public void setgPTaxType(Boolean gPTaxType) {
        this.gPTaxType = gPTaxType;
    }

    public Boolean getgPInvLocAdd() {
        return gPInvLocAdd;
    }

    public void setgPInvLocAdd(Boolean gPInvLocAdd) {
        this.gPInvLocAdd = gPInvLocAdd;
    }

    public Boolean getgPInvLoc() {
        return gPInvLoc;
    }

    public void setgPInvLoc(Boolean gPInvLoc) {
        this.gPInvLoc = gPInvLoc;
    }

    public Boolean getgPStateAdd() {
        return gPStateAdd;
    }

    public void setgPStateAdd(Boolean gPStateAdd) {
        this.gPStateAdd = gPStateAdd;
    }

    public Boolean getgPState() {
        return gPState;
    }

    public void setgPState(Boolean gPState) {
        this.gPState = gPState;
    }

    public Boolean getgPRemoveItem() {
        return gPRemoveItem;
    }

    public void setgPRemoveItem(Boolean gPRemoveItem) {
        this.gPRemoveItem = gPRemoveItem;
    }

    public Boolean getgPMore() {
        return gPMore;
    }

    public void setgPMore(Boolean gPMore) {
        this.gPMore = gPMore;
    }

    public Boolean getgPContactAdd() {
        return gPContactAdd;
    }

    public void setgPContactAdd(Boolean gPContactAdd) {
        this.gPContactAdd = gPContactAdd;
    }

    public Boolean getgPContact() {
        return gPContact;
    }

    public void setgPContact(Boolean gPContact) {
        this.gPContact = gPContact;
    }

    public Boolean getgPAgentAdd() {
        return gPAgentAdd;
    }

    public void setgPAgentAdd(Boolean gPAgentAdd) {
        this.gPAgentAdd = gPAgentAdd;
    }

    public Boolean getgPAgent() {
        return gPAgent;
    }

    public void setgPAgent(Boolean gPAgent) {
        this.gPAgent = gPAgent;
    }

    public Boolean getgPCurrencyAdd() {
        return gPCurrencyAdd;
    }

    public void setgPCurrencyAdd(Boolean gPCurrencyAdd) {
        this.gPCurrencyAdd = gPCurrencyAdd;
    }

    public Boolean getgPCurrency() {
        return gPCurrency;
    }

    public void setgPCurrency(Boolean gPCurrency) {
        this.gPCurrency = gPCurrency;
    }

    public Boolean getgPEmployeeAdd() {
        return gPEmployeeAdd;
    }

    public void setgPEmployeeAdd(Boolean gPEmployeeAdd) {
        this.gPEmployeeAdd = gPEmployeeAdd;
    }

    public Boolean getgPEmployee() {
        return gPEmployee;
    }

    public void setgPEmployee(Boolean gPEmployee) {
        this.gPEmployee = gPEmployee;
    }

    public Boolean getgPExchangeRateAdd() {
        return gPExchangeRateAdd;
    }

    public void setgPExchangeRateAdd(Boolean gPExchangeRateAdd) {
        this.gPExchangeRateAdd = gPExchangeRateAdd;
    }

    public Boolean getgPExchangeRate() {
        return gPExchangeRate;
    }

    public void setgPExchangeRate(Boolean gPExchangeRate) {
        this.gPExchangeRate = gPExchangeRate;
    }

    public Boolean getgPTndCAdd() {
        return gPTndCAdd;
    }

    public void setgPTndCAdd(Boolean gPTndCAdd) {
        this.gPTndCAdd = gPTndCAdd;
    }

    public Boolean getgPTandC() {
        return gPTandC;
    }

    public void setgPTandC(Boolean gPTandC) {
        this.gPTandC = gPTandC;
    }

    public Boolean getgPCCAdd() {
        return gPCCAdd;
    }

    public void setgPCCAdd(Boolean gPCCAdd) {
        this.gPCCAdd = gPCCAdd;
    }

    public Boolean getgPCC() {
        return gPCC;
    }

    public void setgPCC(Boolean gPCC) {
        this.gPCC = gPCC;
    }

    public Boolean getgPSMAdd() {
        return gPSMAdd;
    }

    public void setgPSMAdd(Boolean gPSMAdd) {
        this.gPSMAdd = gPSMAdd;
    }

    public Boolean getgPSM() {
        return gPSM;
    }

    public void setgPSM(Boolean gPSM) {
        this.gPSM = gPSM;
    }

    public Boolean getgPShippingRefNo() {
        return gPShippingRefNo;
    }

    public void setgPShippingRefNo(Boolean gPShippingRefNo) {
        this.gPShippingRefNo = gPShippingRefNo;
    }

    public Boolean getgPRefNo() {
        return gPRefNo;
    }

    public void setgPRefNo(Boolean gPRefNo) {
        this.gPRefNo = gPRefNo;
    }

    public Boolean getgPSaveProforma() {
        return gPSaveProforma;
    }

    public void setgPSaveProforma(Boolean gPSaveProforma) {
        this.gPSaveProforma = gPSaveProforma;
    }

    public Boolean getgPInvokeProforma() {
        return gPInvokeProforma;
    }

    public void setgPInvokeProforma(Boolean gPInvokeProforma) {
        this.gPInvokeProforma = gPInvokeProforma;
    }

    public Boolean getgPSaveDeliveryOrder() {
        return gPSaveDeliveryOrder;
    }

    public void setgPSaveDeliveryOrder(Boolean gPSaveDeliveryOrder) {
        this.gPSaveDeliveryOrder = gPSaveDeliveryOrder;
    }

    public Boolean getgPInvokeDeliveryOrder() {
        return gPInvokeDeliveryOrder;
    }

    public void setgPInvokeDeliveryOrder(Boolean gPInvokeDeliveryOrder) {
        this.gPInvokeDeliveryOrder = gPInvokeDeliveryOrder;
    }

    public Boolean getgPReturnDeliveryOrder() {
        return gPReturnDeliveryOrder;
    }

    public void setgPReturnDeliveryOrder(Boolean gPReturnDeliveryOrder) {
        this.gPReturnDeliveryOrder = gPReturnDeliveryOrder;
    }

    public Boolean getgPDebitNote() {
        return gPDebitNote;
    }

    public void setgPDebitNote(Boolean gPDebitNote) {
        this.gPDebitNote = gPDebitNote;
    }

    public Boolean getgPDraftInvoice() {
        return gPDraftInvoice;
    }

    public void setgPDraftInvoice(Boolean gPDraftInvoice) {
        this.gPDraftInvoice = gPDraftInvoice;
    }

    public Boolean getMobileSales() {
        return mobileSales;
    }

    public void setMobileSales(Boolean mobileSales) {
        this.mobileSales = mobileSales;
    }

    public Boolean getmSItemAdd() {
        return mSItemAdd;
    }

    public void setmSItemAdd(Boolean mSItemAdd) {
        this.mSItemAdd = mSItemAdd;
    }

    public Boolean getmSCustomerAdd() {
        return mSCustomerAdd;
    }

    public void setmSCustomerAdd(Boolean mSCustomerAdd) {
        this.mSCustomerAdd = mSCustomerAdd;
    }

    public Boolean getmSSavesDraft() {
        return mSSavesDraft;
    }

    public void setmSSavesDraft(Boolean mSSavesDraft) {
        this.mSSavesDraft = mSSavesDraft;
    }

    public Boolean getmSClearDraft() {
        return mSClearDraft;
    }

    public void setmSClearDraft(Boolean mSClearDraft) {
        this.mSClearDraft = mSClearDraft;
    }

    public Boolean getmSPrintList() {
        return mSPrintList;
    }

    public void setmSPrintList(Boolean mSPrintList) {
        this.mSPrintList = mSPrintList;
    }

    public Boolean getmSAdvancePayment() {
        return mSAdvancePayment;
    }

    public void setmSAdvancePayment(Boolean mSAdvancePayment) {
        this.mSAdvancePayment = mSAdvancePayment;
    }

    public Boolean getmSSaveQuotation() {
        return mSSaveQuotation;
    }

    public void setmSSaveQuotation(Boolean mSSaveQuotation) {
        this.mSSaveQuotation = mSSaveQuotation;
    }

    public Boolean getmSInvokeQuotation() {
        return mSInvokeQuotation;
    }

    public void setmSInvokeQuotation(Boolean mSInvokeQuotation) {
        this.mSInvokeQuotation = mSInvokeQuotation;
    }

    public Boolean getmSSaveOrder() {
        return mSSaveOrder;
    }

    public void setmSSaveOrder(Boolean mSSaveOrder) {
        this.mSSaveOrder = mSSaveOrder;
    }

    public Boolean getmSAdvancePaymentList() {
        return mSAdvancePaymentList;
    }

    public void setmSAdvancePaymentList(Boolean mSAdvancePaymentList) {
        this.mSAdvancePaymentList = mSAdvancePaymentList;
    }

    public Boolean getmSInvokeOrder() {
        return mSInvokeOrder;
    }

    public void setmSInvokeOrder(Boolean mSInvokeOrder) {
        this.mSInvokeOrder = mSInvokeOrder;
    }

    public Boolean getmSClearAll() {
        return mSClearAll;
    }

    public void setmSClearAll(Boolean mSClearAll) {
        this.mSClearAll = mSClearAll;
    }

    public Boolean getmSCheckOut() {
        return mSCheckOut;
    }

    public void setmSCheckOut(Boolean mSCheckOut) {
        this.mSCheckOut = mSCheckOut;
    }

    public Boolean getmSReturn() {
        return mSReturn;
    }

    public void setmSReturn(Boolean mSReturn) {
        this.mSReturn = mSReturn;
    }

    public Boolean getmSRecievePayment() {
        return mSRecievePayment;
    }

    public void setmSRecievePayment(Boolean mSRecievePayment) {
        this.mSRecievePayment = mSRecievePayment;
    }

    public Boolean getmSInvokeDraft() {
        return mSInvokeDraft;
    }

    public void setmSInvokeDraft(Boolean mSInvokeDraft) {
        this.mSInvokeDraft = mSInvokeDraft;
    }

    public Boolean getmSSearchBarCode() {
        return mSSearchBarCode;
    }

    public void setmSSearchBarCode(Boolean mSSearchBarCode) {
        this.mSSearchBarCode = mSSearchBarCode;
    }

    public Boolean getmSBarCode() {
        return mSBarCode;
    }

    public void setmSBarCode(Boolean mSBarCode) {
        this.mSBarCode = mSBarCode;
    }

    public Boolean getmSSearchSerializeItem() {
        return mSSearchSerializeItem;
    }

    public void setmSSearchSerializeItem(Boolean mSSearchSerializeItem) {
        this.mSSearchSerializeItem = mSSearchSerializeItem;
    }

    public Boolean getmSSerializeItem() {
        return mSSerializeItem;
    }

    public void setmSSerializeItem(Boolean mSSerializeItem) {
        this.mSSerializeItem = mSSerializeItem;
    }

    public Boolean getmSCustomer() {
        return mSCustomer;
    }

    public void setmSCustomer(Boolean mSCustomer) {
        this.mSCustomer = mSCustomer;
    }

    public Boolean getmSImportItem() {
        return mSImportItem;
    }

    public void setmSImportItem(Boolean mSImportItem) {
        this.mSImportItem = mSImportItem;
    }

    public Boolean getmSTaxType() {
        return mSTaxType;
    }

    public void setmSTaxType(Boolean mSTaxType) {
        this.mSTaxType = mSTaxType;
    }

    public Boolean getmSInvLocAdd() {
        return mSInvLocAdd;
    }

    public void setmSInvLocAdd(Boolean mSInvLocAdd) {
        this.mSInvLocAdd = mSInvLocAdd;
    }

    public Boolean getmSInvLoc() {
        return mSInvLoc;
    }

    public void setmSInvLoc(Boolean mSInvLoc) {
        this.mSInvLoc = mSInvLoc;
    }

    public Boolean getmSStateAdd() {
        return mSStateAdd;
    }

    public void setmSStateAdd(Boolean mSStateAdd) {
        this.mSStateAdd = mSStateAdd;
    }

    public Boolean getmSState() {
        return mSState;
    }

    public void setmSState(Boolean mSState) {
        this.mSState = mSState;
    }

    public Boolean getmSRemoveItem() {
        return mSRemoveItem;
    }

    public void setmSRemoveItem(Boolean mSRemoveItem) {
        this.mSRemoveItem = mSRemoveItem;
    }

    public Boolean getmSMore() {
        return mSMore;
    }

    public void setmSMore(Boolean mSMore) {
        this.mSMore = mSMore;
    }

    public Boolean getmSContactAdd() {
        return mSContactAdd;
    }

    public void setmSContactAdd(Boolean mSContactAdd) {
        this.mSContactAdd = mSContactAdd;
    }

    public Boolean getmSContact() {
        return mSContact;
    }

    public void setmSContact(Boolean mSContact) {
        this.mSContact = mSContact;
    }

    public Boolean getmSAgentAdd() {
        return mSAgentAdd;
    }

    public void setmSAgentAdd(Boolean mSAgentAdd) {
        this.mSAgentAdd = mSAgentAdd;
    }

    public Boolean getmSAgent() {
        return mSAgent;
    }

    public void setmSAgent(Boolean mSAgent) {
        this.mSAgent = mSAgent;
    }

    public Boolean getmSCurrencyAdd() {
        return mSCurrencyAdd;
    }

    public void setmSCurrencyAdd(Boolean mSCurrencyAdd) {
        this.mSCurrencyAdd = mSCurrencyAdd;
    }

    public Boolean getmSCurrency() {
        return mSCurrency;
    }

    public void setmSCurrency(Boolean mSCurrency) {
        this.mSCurrency = mSCurrency;
    }

    public Boolean getmSEmployeeAdd() {
        return mSEmployeeAdd;
    }

    public void setmSEmployeeAdd(Boolean mSEmployeeAdd) {
        this.mSEmployeeAdd = mSEmployeeAdd;
    }

    public Boolean getmSEmployee() {
        return mSEmployee;
    }

    public void setmSEmployee(Boolean mSEmployee) {
        this.mSEmployee = mSEmployee;
    }

    public Boolean getmSExchangeRateAdd() {
        return mSExchangeRateAdd;
    }

    public void setmSExchangeRateAdd(Boolean mSExchangeRateAdd) {
        this.mSExchangeRateAdd = mSExchangeRateAdd;
    }

    public Boolean getmSExchangeRate() {
        return mSExchangeRate;
    }

    public void setmSExchangeRate(Boolean mSExchangeRate) {
        this.mSExchangeRate = mSExchangeRate;
    }

    public Boolean getmSTndCAdd() {
        return mSTndCAdd;
    }

    public void setmSTndCAdd(Boolean mSTndCAdd) {
        this.mSTndCAdd = mSTndCAdd;
    }

    public Boolean getmSTandC() {
        return mSTandC;
    }

    public void setmSTandC(Boolean mSTandC) {
        this.mSTandC = mSTandC;
    }

    public Boolean getmSCCAdd() {
        return mSCCAdd;
    }

    public void setmSCCAdd(Boolean mSCCAdd) {
        this.mSCCAdd = mSCCAdd;
    }

    public Boolean getmSCC() {
        return mSCC;
    }

    public void setmSCC(Boolean mSCC) {
        this.mSCC = mSCC;
    }

    public Boolean getmSSMAdd() {
        return mSSMAdd;
    }

    public void setmSSMAdd(Boolean mSSMAdd) {
        this.mSSMAdd = mSSMAdd;
    }

    public Boolean getmSSM() {
        return mSSM;
    }

    public void setmSSM(Boolean mSSM) {
        this.mSSM = mSSM;
    }

    public Boolean getmSShippingRefNo() {
        return mSShippingRefNo;
    }

    public void setmSShippingRefNo(Boolean mSShippingRefNo) {
        this.mSShippingRefNo = mSShippingRefNo;
    }

    public Boolean getmSRefNo() {
        return mSRefNo;
    }

    public void setmSRefNo(Boolean mSRefNo) {
        this.mSRefNo = mSRefNo;
    }

    public Boolean getmSSaveProforma() {
        return mSSaveProforma;
    }

    public void setmSSaveProforma(Boolean mSSaveProforma) {
        this.mSSaveProforma = mSSaveProforma;
    }

    public Boolean getmSInvokeProforma() {
        return mSInvokeProforma;
    }

    public void setmSInvokeProforma(Boolean mSInvokeProforma) {
        this.mSInvokeProforma = mSInvokeProforma;
    }

    public Boolean getmSSaveDeliveryOrder() {
        return mSSaveDeliveryOrder;
    }

    public void setmSSaveDeliveryOrder(Boolean mSSaveDeliveryOrder) {
        this.mSSaveDeliveryOrder = mSSaveDeliveryOrder;
    }

    public Boolean getmSInvokeDeliveryOrder() {
        return mSInvokeDeliveryOrder;
    }

    public void setmSInvokeDeliveryOrder(Boolean mSInvokeDeliveryOrder) {
        this.mSInvokeDeliveryOrder = mSInvokeDeliveryOrder;
    }

    public Boolean getmSReturnDeliveryOrder() {
        return mSReturnDeliveryOrder;
    }

    public void setmSReturnDeliveryOrder(Boolean mSReturnDeliveryOrder) {
        this.mSReturnDeliveryOrder = mSReturnDeliveryOrder;
    }

    public Boolean getmSDebitNote() {
        return mSDebitNote;
    }

    public void setmSDebitNote(Boolean mSDebitNote) {
        this.mSDebitNote = mSDebitNote;
    }

    public Boolean getmSDraftInvoice() {
        return mSDraftInvoice;
    }

    public void setmSDraftInvoice(Boolean mSDraftInvoice) {
        this.mSDraftInvoice = mSDraftInvoice;
    }

    public Boolean getPurchasesConstruction() {
        return purchasesConstruction;
    }

    public void setPurchasesConstruction(Boolean purchasesConstruction) {
        this.purchasesConstruction = purchasesConstruction;
    }

    public Boolean getpCItemAdd() {
        return pCItemAdd;
    }

    public void setpCItemAdd(Boolean pCItemAdd) {
        this.pCItemAdd = pCItemAdd;
    }

    public Boolean getpCCustomerAdd() {
        return pCCustomerAdd;
    }

    public void setpCCustomerAdd(Boolean pCCustomerAdd) {
        this.pCCustomerAdd = pCCustomerAdd;
    }

    public Boolean getpCSavesDraft() {
        return pCSavesDraft;
    }

    public void setpCSavesDraft(Boolean pCSavesDraft) {
        this.pCSavesDraft = pCSavesDraft;
    }

    public Boolean getpCClearDraft() {
        return pCClearDraft;
    }

    public void setpCClearDraft(Boolean pCClearDraft) {
        this.pCClearDraft = pCClearDraft;
    }

    public Boolean getpCPrintList() {
        return pCPrintList;
    }

    public void setpCPrintList(Boolean pCPrintList) {
        this.pCPrintList = pCPrintList;
    }

    public Boolean getpCAdvancePayment() {
        return pCAdvancePayment;
    }

    public void setpCAdvancePayment(Boolean pCAdvancePayment) {
        this.pCAdvancePayment = pCAdvancePayment;
    }

    public Boolean getpCSaveQuotation() {
        return pCSaveQuotation;
    }

    public void setpCSaveQuotation(Boolean pCSaveQuotation) {
        this.pCSaveQuotation = pCSaveQuotation;
    }

    public Boolean getpCInvokeQuotation() {
        return pCInvokeQuotation;
    }

    public void setpCInvokeQuotation(Boolean pCInvokeQuotation) {
        this.pCInvokeQuotation = pCInvokeQuotation;
    }

    public Boolean getpCSaveOrder() {
        return pCSaveOrder;
    }

    public void setpCSaveOrder(Boolean pCSaveOrder) {
        this.pCSaveOrder = pCSaveOrder;
    }

    public Boolean getpCAdvancePaymentList() {
        return pCAdvancePaymentList;
    }

    public void setpCAdvancePaymentList(Boolean pCAdvancePaymentList) {
        this.pCAdvancePaymentList = pCAdvancePaymentList;
    }

    public Boolean getpCInvokeOrder() {
        return pCInvokeOrder;
    }

    public void setpCInvokeOrder(Boolean pCInvokeOrder) {
        this.pCInvokeOrder = pCInvokeOrder;
    }

    public Boolean getpCClearAll() {
        return pCClearAll;
    }

    public void setpCClearAll(Boolean pCClearAll) {
        this.pCClearAll = pCClearAll;
    }

    public Boolean getpCCheckOut() {
        return pCCheckOut;
    }

    public void setpCCheckOut(Boolean pCCheckOut) {
        this.pCCheckOut = pCCheckOut;
    }

    public Boolean getpCReturn() {
        return pCReturn;
    }

    public void setpCReturn(Boolean pCReturn) {
        this.pCReturn = pCReturn;
    }

    public Boolean getpCRecievePayment() {
        return pCRecievePayment;
    }

    public void setpCRecievePayment(Boolean pCRecievePayment) {
        this.pCRecievePayment = pCRecievePayment;
    }

    public Boolean getpCInvokeDraft() {
        return pCInvokeDraft;
    }

    public void setpCInvokeDraft(Boolean pCInvokeDraft) {
        this.pCInvokeDraft = pCInvokeDraft;
    }

    public Boolean getpCSearchBarCode() {
        return pCSearchBarCode;
    }

    public void setpCSearchBarCode(Boolean pCSearchBarCode) {
        this.pCSearchBarCode = pCSearchBarCode;
    }

    public Boolean getpCBarCode() {
        return pCBarCode;
    }

    public void setpCBarCode(Boolean pCBarCode) {
        this.pCBarCode = pCBarCode;
    }

    public Boolean getpCSearchSerializeItem() {
        return pCSearchSerializeItem;
    }

    public void setpCSearchSerializeItem(Boolean pCSearchSerializeItem) {
        this.pCSearchSerializeItem = pCSearchSerializeItem;
    }

    public Boolean getpCSerializeItem() {
        return pCSerializeItem;
    }

    public void setpCSerializeItem(Boolean pCSerializeItem) {
        this.pCSerializeItem = pCSerializeItem;
    }

    public Boolean getpCCustomer() {
        return pCCustomer;
    }

    public void setpCCustomer(Boolean pCCustomer) {
        this.pCCustomer = pCCustomer;
    }

    public Boolean getpCImportItem() {
        return pCImportItem;
    }

    public void setpCImportItem(Boolean pCImportItem) {
        this.pCImportItem = pCImportItem;
    }

    public Boolean getpCTaxType() {
        return pCTaxType;
    }

    public void setpCTaxType(Boolean pCTaxType) {
        this.pCTaxType = pCTaxType;
    }

    public Boolean getpCInvLocAdd() {
        return pCInvLocAdd;
    }

    public void setpCInvLocAdd(Boolean pCInvLocAdd) {
        this.pCInvLocAdd = pCInvLocAdd;
    }

    public Boolean getpCInvLoc() {
        return pCInvLoc;
    }

    public void setpCInvLoc(Boolean pCInvLoc) {
        this.pCInvLoc = pCInvLoc;
    }

    public Boolean getpCStateAdd() {
        return pCStateAdd;
    }

    public void setpCStateAdd(Boolean pCStateAdd) {
        this.pCStateAdd = pCStateAdd;
    }

    public Boolean getpCState() {
        return pCState;
    }

    public void setpCState(Boolean pCState) {
        this.pCState = pCState;
    }

    public Boolean getpCRemoveItem() {
        return pCRemoveItem;
    }

    public void setpCRemoveItem(Boolean pCRemoveItem) {
        this.pCRemoveItem = pCRemoveItem;
    }

    public Boolean getpCMore() {
        return pCMore;
    }

    public void setpCMore(Boolean pCMore) {
        this.pCMore = pCMore;
    }

    public Boolean getpCContactAdd() {
        return pCContactAdd;
    }

    public void setpCContactAdd(Boolean pCContactAdd) {
        this.pCContactAdd = pCContactAdd;
    }

    public Boolean getpCContact() {
        return pCContact;
    }

    public void setpCContact(Boolean pCContact) {
        this.pCContact = pCContact;
    }

    public Boolean getpCAgentAdd() {
        return pCAgentAdd;
    }

    public void setpCAgentAdd(Boolean pCAgentAdd) {
        this.pCAgentAdd = pCAgentAdd;
    }

    public Boolean getpCAgent() {
        return pCAgent;
    }

    public void setpCAgent(Boolean pCAgent) {
        this.pCAgent = pCAgent;
    }

    public Boolean getpCCurrencyAdd() {
        return pCCurrencyAdd;
    }

    public void setpCCurrencyAdd(Boolean pCCurrencyAdd) {
        this.pCCurrencyAdd = pCCurrencyAdd;
    }

    public Boolean getpCCurrency() {
        return pCCurrency;
    }

    public void setpCCurrency(Boolean pCCurrency) {
        this.pCCurrency = pCCurrency;
    }

    public Boolean getpCEmployeeAdd() {
        return pCEmployeeAdd;
    }

    public void setpCEmployeeAdd(Boolean pCEmployeeAdd) {
        this.pCEmployeeAdd = pCEmployeeAdd;
    }

    public Boolean getpCEmployee() {
        return pCEmployee;
    }

    public void setpCEmployee(Boolean pCEmployee) {
        this.pCEmployee = pCEmployee;
    }

    public Boolean getpCExchangeRateAdd() {
        return pCExchangeRateAdd;
    }

    public void setpCExchangeRateAdd(Boolean pCExchangeRateAdd) {
        this.pCExchangeRateAdd = pCExchangeRateAdd;
    }

    public Boolean getpCExchangeRate() {
        return pCExchangeRate;
    }

    public void setpCExchangeRate(Boolean pCExchangeRate) {
        this.pCExchangeRate = pCExchangeRate;
    }

    public Boolean getpCTndCAdd() {
        return pCTndCAdd;
    }

    public void setpCTndCAdd(Boolean pCTndCAdd) {
        this.pCTndCAdd = pCTndCAdd;
    }

    public Boolean getpCTandC() {
        return pCTandC;
    }

    public void setpCTandC(Boolean pCTandC) {
        this.pCTandC = pCTandC;
    }

    public Boolean getpCCCAdd() {
        return pCCCAdd;
    }

    public void setpCCCAdd(Boolean pCCCAdd) {
        this.pCCCAdd = pCCCAdd;
    }

    public Boolean getpCCC() {
        return pCCC;
    }

    public void setpCCC(Boolean pCCC) {
        this.pCCC = pCCC;
    }

    public Boolean getpCSMAdd() {
        return pCSMAdd;
    }

    public void setpCSMAdd(Boolean pCSMAdd) {
        this.pCSMAdd = pCSMAdd;
    }

    public Boolean getpCSM() {
        return pCSM;
    }

    public void setpCSM(Boolean pCSM) {
        this.pCSM = pCSM;
    }

    public Boolean getpCShippingRefNo() {
        return pCShippingRefNo;
    }

    public void setpCShippingRefNo(Boolean pCShippingRefNo) {
        this.pCShippingRefNo = pCShippingRefNo;
    }

    public Boolean getpCRefNo() {
        return pCRefNo;
    }

    public void setpCRefNo(Boolean pCRefNo) {
        this.pCRefNo = pCRefNo;
    }

    public Boolean getpCSaveProforma() {
        return pCSaveProforma;
    }

    public void setpCSaveProforma(Boolean pCSaveProforma) {
        this.pCSaveProforma = pCSaveProforma;
    }

    public Boolean getpCInvokeProforma() {
        return pCInvokeProforma;
    }

    public void setpCInvokeProforma(Boolean pCInvokeProforma) {
        this.pCInvokeProforma = pCInvokeProforma;
    }

    public Boolean getpCSaveDeliveryOrder() {
        return pCSaveDeliveryOrder;
    }

    public void setpCSaveDeliveryOrder(Boolean pCSaveDeliveryOrder) {
        this.pCSaveDeliveryOrder = pCSaveDeliveryOrder;
    }

    public Boolean getpCInvokeDeliveryOrder() {
        return pCInvokeDeliveryOrder;
    }

    public void setpCInvokeDeliveryOrder(Boolean pCInvokeDeliveryOrder) {
        this.pCInvokeDeliveryOrder = pCInvokeDeliveryOrder;
    }

    public Boolean getpCReturnDeliveryOrder() {
        return pCReturnDeliveryOrder;
    }

    public void setpCReturnDeliveryOrder(Boolean pCReturnDeliveryOrder) {
        this.pCReturnDeliveryOrder = pCReturnDeliveryOrder;
    }

    public Boolean getpCDebitNote() {
        return pCDebitNote;
    }

    public void setpCDebitNote(Boolean pCDebitNote) {
        this.pCDebitNote = pCDebitNote;
    }

    public Boolean getpCDraftInvoice() {
        return pCDraftInvoice;
    }

    public void setpCDraftInvoice(Boolean pCDraftInvoice) {
        this.pCDraftInvoice = pCDraftInvoice;
    }

    public Boolean getPharmaSales() {
        return pharmaSales;
    }

    public void setPharmaSales(Boolean pharmaSales) {
        this.pharmaSales = pharmaSales;
    }

    public Boolean getpSItemAdd() {
        return pSItemAdd;
    }

    public void setpSItemAdd(Boolean pSItemAdd) {
        this.pSItemAdd = pSItemAdd;
    }

    public Boolean getpSCustomerAdd() {
        return pSCustomerAdd;
    }

    public void setpSCustomerAdd(Boolean pSCustomerAdd) {
        this.pSCustomerAdd = pSCustomerAdd;
    }

    public Boolean getpSSavesDraft() {
        return pSSavesDraft;
    }

    public void setpSSavesDraft(Boolean pSSavesDraft) {
        this.pSSavesDraft = pSSavesDraft;
    }

    public Boolean getpSClearDraft() {
        return pSClearDraft;
    }

    public void setpSClearDraft(Boolean pSClearDraft) {
        this.pSClearDraft = pSClearDraft;
    }

    public Boolean getpSPrintList() {
        return pSPrintList;
    }

    public void setpSPrintList(Boolean pSPrintList) {
        this.pSPrintList = pSPrintList;
    }

    public Boolean getpSAdvancePayment() {
        return pSAdvancePayment;
    }

    public void setpSAdvancePayment(Boolean pSAdvancePayment) {
        this.pSAdvancePayment = pSAdvancePayment;
    }

    public Boolean getpSSaveQuotation() {
        return pSSaveQuotation;
    }

    public void setpSSaveQuotation(Boolean pSSaveQuotation) {
        this.pSSaveQuotation = pSSaveQuotation;
    }

    public Boolean getpSInvokeQuotation() {
        return pSInvokeQuotation;
    }

    public void setpSInvokeQuotation(Boolean pSInvokeQuotation) {
        this.pSInvokeQuotation = pSInvokeQuotation;
    }

    public Boolean getpSSaveOrder() {
        return pSSaveOrder;
    }

    public void setpSSaveOrder(Boolean pSSaveOrder) {
        this.pSSaveOrder = pSSaveOrder;
    }

    public Boolean getpSAdvancePaymentList() {
        return pSAdvancePaymentList;
    }

    public void setpSAdvancePaymentList(Boolean pSAdvancePaymentList) {
        this.pSAdvancePaymentList = pSAdvancePaymentList;
    }

    public Boolean getpSInvokeOrder() {
        return pSInvokeOrder;
    }

    public void setpSInvokeOrder(Boolean pSInvokeOrder) {
        this.pSInvokeOrder = pSInvokeOrder;
    }

    public Boolean getpSClearAll() {
        return pSClearAll;
    }

    public void setpSClearAll(Boolean pSClearAll) {
        this.pSClearAll = pSClearAll;
    }

    public Boolean getpSCheckOut() {
        return pSCheckOut;
    }

    public void setpSCheckOut(Boolean pSCheckOut) {
        this.pSCheckOut = pSCheckOut;
    }

    public Boolean getpSReturn() {
        return pSReturn;
    }

    public void setpSReturn(Boolean pSReturn) {
        this.pSReturn = pSReturn;
    }

    public Boolean getpSRecievePayment() {
        return pSRecievePayment;
    }

    public void setpSRecievePayment(Boolean pSRecievePayment) {
        this.pSRecievePayment = pSRecievePayment;
    }

    public Boolean getpSInvokeDraft() {
        return pSInvokeDraft;
    }

    public void setpSInvokeDraft(Boolean pSInvokeDraft) {
        this.pSInvokeDraft = pSInvokeDraft;
    }

    public Boolean getpSSearchBarCode() {
        return pSSearchBarCode;
    }

    public void setpSSearchBarCode(Boolean pSSearchBarCode) {
        this.pSSearchBarCode = pSSearchBarCode;
    }

    public Boolean getpSBarCode() {
        return pSBarCode;
    }

    public void setpSBarCode(Boolean pSBarCode) {
        this.pSBarCode = pSBarCode;
    }

    public Boolean getpSSearchSerializeItem() {
        return pSSearchSerializeItem;
    }

    public void setpSSearchSerializeItem(Boolean pSSearchSerializeItem) {
        this.pSSearchSerializeItem = pSSearchSerializeItem;
    }

    public Boolean getpSSerializeItem() {
        return pSSerializeItem;
    }

    public void setpSSerializeItem(Boolean pSSerializeItem) {
        this.pSSerializeItem = pSSerializeItem;
    }

    public Boolean getpSCustomer() {
        return pSCustomer;
    }

    public void setpSCustomer(Boolean pSCustomer) {
        this.pSCustomer = pSCustomer;
    }

    public Boolean getpSImportItem() {
        return pSImportItem;
    }

    public void setpSImportItem(Boolean pSImportItem) {
        this.pSImportItem = pSImportItem;
    }

    public Boolean getpSTaxType() {
        return pSTaxType;
    }

    public void setpSTaxType(Boolean pSTaxType) {
        this.pSTaxType = pSTaxType;
    }

    public Boolean getpSInvLocAdd() {
        return pSInvLocAdd;
    }

    public void setpSInvLocAdd(Boolean pSInvLocAdd) {
        this.pSInvLocAdd = pSInvLocAdd;
    }

    public Boolean getpSInvLoc() {
        return pSInvLoc;
    }

    public void setpSInvLoc(Boolean pSInvLoc) {
        this.pSInvLoc = pSInvLoc;
    }

    public Boolean getpSStateAdd() {
        return pSStateAdd;
    }

    public void setpSStateAdd(Boolean pSStateAdd) {
        this.pSStateAdd = pSStateAdd;
    }

    public Boolean getpSState() {
        return pSState;
    }

    public void setpSState(Boolean pSState) {
        this.pSState = pSState;
    }

    public Boolean getpSRemoveItem() {
        return pSRemoveItem;
    }

    public void setpSRemoveItem(Boolean pSRemoveItem) {
        this.pSRemoveItem = pSRemoveItem;
    }

    public Boolean getpSMore() {
        return pSMore;
    }

    public void setpSMore(Boolean pSMore) {
        this.pSMore = pSMore;
    }

    public Boolean getpSContactAdd() {
        return pSContactAdd;
    }

    public void setpSContactAdd(Boolean pSContactAdd) {
        this.pSContactAdd = pSContactAdd;
    }

    public Boolean getpSContact() {
        return pSContact;
    }

    public void setpSContact(Boolean pSContact) {
        this.pSContact = pSContact;
    }

    public Boolean getpSAgentAdd() {
        return pSAgentAdd;
    }

    public void setpSAgentAdd(Boolean pSAgentAdd) {
        this.pSAgentAdd = pSAgentAdd;
    }

    public Boolean getpSAgent() {
        return pSAgent;
    }

    public void setpSAgent(Boolean pSAgent) {
        this.pSAgent = pSAgent;
    }

    public Boolean getpSCurrencyAdd() {
        return pSCurrencyAdd;
    }

    public void setpSCurrencyAdd(Boolean pSCurrencyAdd) {
        this.pSCurrencyAdd = pSCurrencyAdd;
    }

    public Boolean getpSCurrency() {
        return pSCurrency;
    }

    public void setpSCurrency(Boolean pSCurrency) {
        this.pSCurrency = pSCurrency;
    }

    public Boolean getpSEmployeeAdd() {
        return pSEmployeeAdd;
    }

    public void setpSEmployeeAdd(Boolean pSEmployeeAdd) {
        this.pSEmployeeAdd = pSEmployeeAdd;
    }

    public Boolean getpSEmployee() {
        return pSEmployee;
    }

    public void setpSEmployee(Boolean pSEmployee) {
        this.pSEmployee = pSEmployee;
    }

    public Boolean getpSExchangeRateAdd() {
        return pSExchangeRateAdd;
    }

    public void setpSExchangeRateAdd(Boolean pSExchangeRateAdd) {
        this.pSExchangeRateAdd = pSExchangeRateAdd;
    }

    public Boolean getpSExchangeRate() {
        return pSExchangeRate;
    }

    public void setpSExchangeRate(Boolean pSExchangeRate) {
        this.pSExchangeRate = pSExchangeRate;
    }

    public Boolean getpSTndCAdd() {
        return pSTndCAdd;
    }

    public void setpSTndCAdd(Boolean pSTndCAdd) {
        this.pSTndCAdd = pSTndCAdd;
    }

    public Boolean getpSTandC() {
        return pSTandC;
    }

    public void setpSTandC(Boolean pSTandC) {
        this.pSTandC = pSTandC;
    }

    public Boolean getpSCCAdd() {
        return pSCCAdd;
    }

    public void setpSCCAdd(Boolean pSCCAdd) {
        this.pSCCAdd = pSCCAdd;
    }

    public Boolean getpSCC() {
        return pSCC;
    }

    public void setpSCC(Boolean pSCC) {
        this.pSCC = pSCC;
    }

    public Boolean getpSSMAdd() {
        return pSSMAdd;
    }

    public void setpSSMAdd(Boolean pSSMAdd) {
        this.pSSMAdd = pSSMAdd;
    }

    public Boolean getpSSM() {
        return pSSM;
    }

    public void setpSSM(Boolean pSSM) {
        this.pSSM = pSSM;
    }

    public Boolean getpSShippingRefNo() {
        return pSShippingRefNo;
    }

    public void setpSShippingRefNo(Boolean pSShippingRefNo) {
        this.pSShippingRefNo = pSShippingRefNo;
    }

    public Boolean getpSRefNo() {
        return pSRefNo;
    }

    public void setpSRefNo(Boolean pSRefNo) {
        this.pSRefNo = pSRefNo;
    }

    public Boolean getpSSaveProforma() {
        return pSSaveProforma;
    }

    public void setpSSaveProforma(Boolean pSSaveProforma) {
        this.pSSaveProforma = pSSaveProforma;
    }

    public Boolean getpSInvokeProforma() {
        return pSInvokeProforma;
    }

    public void setpSInvokeProforma(Boolean pSInvokeProforma) {
        this.pSInvokeProforma = pSInvokeProforma;
    }

    public Boolean getpSSaveDeliveryOrder() {
        return pSSaveDeliveryOrder;
    }

    public void setpSSaveDeliveryOrder(Boolean pSSaveDeliveryOrder) {
        this.pSSaveDeliveryOrder = pSSaveDeliveryOrder;
    }

    public Boolean getpSInvokeDeliveryOrder() {
        return pSInvokeDeliveryOrder;
    }

    public void setpSInvokeDeliveryOrder(Boolean pSInvokeDeliveryOrder) {
        this.pSInvokeDeliveryOrder = pSInvokeDeliveryOrder;
    }

    public Boolean getpSReturnDeliveryOrder() {
        return pSReturnDeliveryOrder;
    }

    public void setpSReturnDeliveryOrder(Boolean pSReturnDeliveryOrder) {
        this.pSReturnDeliveryOrder = pSReturnDeliveryOrder;
    }

    public Boolean getpSDebitNote() {
        return pSDebitNote;
    }

    public void setpSDebitNote(Boolean pSDebitNote) {
        this.pSDebitNote = pSDebitNote;
    }

    public Boolean getpSDraftInvoice() {
        return pSDraftInvoice;
    }

    public void setpSDraftInvoice(Boolean pSDraftInvoice) {
        this.pSDraftInvoice = pSDraftInvoice;
    }

    public Boolean getGoldsales() {
        return goldsales;
    }

    public void setGoldsales(Boolean goldsales) {
        this.goldsales = goldsales;
    }

    public Boolean getgSSearchBarCode() {
        return gSSearchBarCode;
    }

    public void setgSSearchBarCode(Boolean gSSearchBarCode) {
        this.gSSearchBarCode = gSSearchBarCode;
    }

    public Boolean getgSBarCode() {
        return gSBarCode;
    }

    public void setgSBarCode(Boolean gSBarCode) {
        this.gSBarCode = gSBarCode;
    }

    public Boolean getgSSearchSerializeItem() {
        return gSSearchSerializeItem;
    }

    public void setgSSearchSerializeItem(Boolean gSSearchSerializeItem) {
        this.gSSearchSerializeItem = gSSearchSerializeItem;
    }

    public Boolean getgSSerializeItem() {
        return gSSerializeItem;
    }

    public void setgSSerializeItem(Boolean gSSerializeItem) {
        this.gSSerializeItem = gSSerializeItem;
    }

    public Boolean getgSItemAdd() {
        return gSItemAdd;
    }

    public void setgSItemAdd(Boolean gSItemAdd) {
        this.gSItemAdd = gSItemAdd;
    }

    public Boolean getgSCustomerAdd() {
        return gSCustomerAdd;
    }

    public void setgSCustomerAdd(Boolean gSCustomerAdd) {
        this.gSCustomerAdd = gSCustomerAdd;
    }

    public Boolean getgSCustomerList() {
        return gSCustomerList;
    }

    public void setgSCustomerList(Boolean gSCustomerList) {
        this.gSCustomerList = gSCustomerList;
    }

    public Boolean getgSItemList() {
        return gSItemList;
    }

    public void setgSItemList(Boolean gSItemList) {
        this.gSItemList = gSItemList;
    }

    public Boolean getgSImportItem() {
        return gSImportItem;
    }

    public void setgSImportItem(Boolean gSImportItem) {
        this.gSImportItem = gSImportItem;
    }

    public Boolean getgSDate() {
        return gSDate;
    }

    public void setgSDate(Boolean gSDate) {
        this.gSDate = gSDate;
    }

    public Boolean getgSTax() {
        return gSTax;
    }

    public void setgSTax(Boolean gSTax) {
        this.gSTax = gSTax;
    }

    public Boolean getgSRemoveItem() {
        return gSRemoveItem;
    }

    public void setgSRemoveItem(Boolean gSRemoveItem) {
        this.gSRemoveItem = gSRemoveItem;
    }

    public Boolean getgSClearAll() {
        return gSClearAll;
    }

    public void setgSClearAll(Boolean gSClearAll) {
        this.gSClearAll = gSClearAll;
    }

    public Boolean getgSItemLength() {
        return gSItemLength;
    }

    public void setgSItemLength(Boolean gSItemLength) {
        this.gSItemLength = gSItemLength;
    }

    public Boolean getgSTotalAmt() {
        return gSTotalAmt;
    }

    public void setgSTotalAmt(Boolean gSTotalAmt) {
        this.gSTotalAmt = gSTotalAmt;
    }

    public Boolean getgSCheckOut() {
        return gSCheckOut;
    }

    public void setgSCheckOut(Boolean gSCheckOut) {
        this.gSCheckOut = gSCheckOut;
    }

    public Boolean getgSAdvancePayment() {
        return gSAdvancePayment;
    }

    public void setgSAdvancePayment(Boolean gSAdvancePayment) {
        this.gSAdvancePayment = gSAdvancePayment;
    }

    public Boolean getgSPrintList() {
        return gSPrintList;
    }

    public void setgSPrintList(Boolean gSPrintList) {
        this.gSPrintList = gSPrintList;
    }

    public Boolean getgSSaveSalesOrder() {
        return gSSaveSalesOrder;
    }

    public void setgSSaveSalesOrder(Boolean gSSaveSalesOrder) {
        this.gSSaveSalesOrder = gSSaveSalesOrder;
    }

    public Boolean getgSSalesInvoke() {
        return gSSalesInvoke;
    }

    public void setgSSalesInvoke(Boolean gSSalesInvoke) {
        this.gSSalesInvoke = gSSalesInvoke;
    }

    public Boolean getgSReturn() {
        return gSReturn;
    }

    public void setgSReturn(Boolean gSReturn) {
        this.gSReturn = gSReturn;
    }

    public Boolean getgSAdvancePaymentList() {
        return gSAdvancePaymentList;
    }

    public void setgSAdvancePaymentList(Boolean gSAdvancePaymentList) {
        this.gSAdvancePaymentList = gSAdvancePaymentList;
    }

    public Boolean getgSRecievePayment() {
        return gSRecievePayment;
    }

    public void setgSRecievePayment(Boolean gSRecievePayment) {
        this.gSRecievePayment = gSRecievePayment;
    }

    public Boolean getgSDraftInvoke() {
        return gSDraftInvoke;
    }

    public void setgSDraftInvoke(Boolean gSDraftInvoke) {
        this.gSDraftInvoke = gSDraftInvoke;
    }

    public Boolean getgSDraftClear() {
        return gSDraftClear;
    }

    public void setgSDraftClear(Boolean gSDraftClear) {
        this.gSDraftClear = gSDraftClear;
    }

    public Boolean getgSEmpty1() {
        return gSEmpty1;
    }

    public void setgSEmpty1(Boolean gSEmpty1) {
        this.gSEmpty1 = gSEmpty1;
    }

    public Boolean getgSEmpty2() {
        return gSEmpty2;
    }

    public void setgSEmpty2(Boolean gSEmpty2) {
        this.gSEmpty2 = gSEmpty2;
    }

    public Boolean getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Boolean restaurant) {
        this.restaurant = restaurant;
    }

    public Boolean getRestItemSearch() {
        return restItemSearch;
    }

    public void setRestItemSearch(Boolean restItemSearch) {
        this.restItemSearch = restItemSearch;
    }

    public Boolean getRestBarcode() {
        return restBarcode;
    }

    public void setRestBarcode(Boolean restBarcode) {
        this.restBarcode = restBarcode;
    }

    public Boolean getRestAppendItem() {
        return restAppendItem;
    }

    public void setRestAppendItem(Boolean restAppendItem) {
        this.restAppendItem = restAppendItem;
    }

    public Boolean getRestItemAdd() {
        return restItemAdd;
    }

    public void setRestItemAdd(Boolean restItemAdd) {
        this.restItemAdd = restItemAdd;
    }

    public Boolean getRestRemoveItem() {
        return restRemoveItem;
    }

    public void setRestRemoveItem(Boolean restRemoveItem) {
        this.restRemoveItem = restRemoveItem;
    }

    public Boolean getRestTax() {
        return restTax;
    }

    public void setRestTax(Boolean restTax) {
        this.restTax = restTax;
    }

    public Boolean getRestAppendCustomer() {
        return restAppendCustomer;
    }

    public void setRestAppendCustomer(Boolean restAppendCustomer) {
        this.restAppendCustomer = restAppendCustomer;
    }

    public Boolean getRestCustomerAdd() {
        return restCustomerAdd;
    }

    public void setRestCustomerAdd(Boolean restCustomerAdd) {
        this.restCustomerAdd = restCustomerAdd;
    }

    public Boolean getRestTableAppend() {
        return restTableAppend;
    }

    public void setRestTableAppend(Boolean restTableAppend) {
        this.restTableAppend = restTableAppend;
    }

    public Boolean getRestSelectCategory() {
        return restSelectCategory;
    }

    public void setRestSelectCategory(Boolean restSelectCategory) {
        this.restSelectCategory = restSelectCategory;
    }

    public Boolean getRestSelectItem() {
        return restSelectItem;
    }

    public void setRestSelectItem(Boolean restSelectItem) {
        this.restSelectItem = restSelectItem;
    }

    public Boolean getRestTotalAmt() {
        return restTotalAmt;
    }

    public void setRestTotalAmt(Boolean restTotalAmt) {
        this.restTotalAmt = restTotalAmt;
    }

    public Boolean getRestOpenDrawer() {
        return restOpenDrawer;
    }

    public void setRestOpenDrawer(Boolean restOpenDrawer) {
        this.restOpenDrawer = restOpenDrawer;
    }

    public Boolean getRestClearAll() {
        return restClearAll;
    }

    public void setRestClearAll(Boolean restClearAll) {
        this.restClearAll = restClearAll;
    }

    public Boolean getRestPayment() {
        return restPayment;
    }

    public void setRestPayment(Boolean restPayment) {
        this.restPayment = restPayment;
    }

    public Boolean getRestSplitBill() {
        return restSplitBill;
    }

    public void setRestSplitBill(Boolean restSplitBill) {
        this.restSplitBill = restSplitBill;
    }

    public Boolean getRestRestaurentTable() {
        return restRestaurentTable;
    }

    public void setRestRestaurentTable(Boolean restRestaurentTable) {
        this.restRestaurentTable = restRestaurentTable;
    }

    public Boolean getRestPrint() {
        return restPrint;
    }

    public void setRestPrint(Boolean restPrint) {
        this.restPrint = restPrint;
    }
}
