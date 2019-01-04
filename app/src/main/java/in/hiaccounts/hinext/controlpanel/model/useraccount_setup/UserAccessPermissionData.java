package in.hiaccounts.hinext.controlpanel.model.useraccount_setup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by administrator on 9/2/18.
 */

public class UserAccessPermissionData implements Serializable {
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("id")
    @Expose
    public Long userAccountSetupID;
    @SerializedName("userAccountSetupID")
    @Expose
    public Long id;
    @SerializedName("projectModuleId")
    @Expose
    public Long projectModuleId;
    @SerializedName("projectModuleName")
    @Expose
    public String projectModuleName;
    @SerializedName("projectModuleDescription")
    @Expose
    public String projectModuleDescription;
    @SerializedName("projectModuleStatus")
    @Expose
    public String projectModuleStatus;
    @SerializedName("VCuserAccessRightsDTO")
    @Expose
    public VCuserAccessRightsDTO vCuserAccessRightsDTO;
    @SerializedName("vcgPRefNo")
    @Expose
    public Boolean vcgPRefNo;
    @SerializedName("vcgPRemoveItem")
    @Expose
    public Boolean vcgPRemoveItem;
    @SerializedName("vcgPAgentAdd")
    @Expose
    public Boolean vcgPAgentAdd;
    @SerializedName("vcgPClearDraft")
    @Expose
    public Boolean vcgPClearDraft;
    @SerializedName("vcgtExpense")
    @Expose
    public Boolean vcgtExpense;
    @SerializedName("vcgPSavesDraft")
    @Expose
    public Boolean vcgPSavesDraft;
    @SerializedName("vcreportSaleDA")
    @Expose
    public Boolean vcreportSaleDA;
    @SerializedName("vctaxGST")
    @Expose
    public Boolean vctaxGST;
    @SerializedName("vcgPSM")
    @Expose
    public Boolean vcgPSM;
    @SerializedName("vcgPSMAdd")
    @Expose
    public Boolean vcgPSMAdd;
    @SerializedName("vcgPState")
    @Expose
    public Boolean vcgPState;
    @SerializedName("vcgPStateAdd")
    @Expose
    public Boolean vcgPStateAdd;
    @SerializedName("vcgPTandC")
    @Expose
    public Boolean vcgPTandC;
    @SerializedName("vcgPCurrency")
    @Expose
    public Boolean vcgPCurrency;
    @SerializedName("vccontact")
    @Expose
    public Boolean vccontact;
    @SerializedName("vcgPCustomer")
    @Expose
    public Boolean vcgPCustomer;
    @SerializedName("vcgPImportItem")
    @Expose
    public Boolean vcgPImportItem;
    @SerializedName("vcgPItemAdd")
    @Expose
    public Boolean vcgPItemAdd;
    @SerializedName("vcgPTaxType")
    @Expose
    public Boolean vcgPTaxType;
    @SerializedName("vcgPTndCAdd")
    @Expose
    public Boolean vcgPTndCAdd;
    @SerializedName("vcpurchase")
    @Expose
    public Boolean vcpurchase;
    @SerializedName("vcsalesReturn")
    @Expose
    public Boolean vcsalesReturn;
    @SerializedName("vcgPAgent")
    @Expose
    public Boolean vcgPAgent;
    @SerializedName("vcsalesItemAdd")
    @Expose
    public Boolean vcsalesItemAdd;
    @SerializedName("vcinventoryUOM")
    @Expose
    public Boolean vcinventoryUOM;
    @SerializedName("vcreportSalePR")
    @Expose
    public Boolean vcreportSalePR;
    @SerializedName("vctax")
    @Expose
    public Boolean vctax;
    @SerializedName("vcreportSaleCA")
    @Expose
    public Boolean vcreportSaleCA;
    @SerializedName("vcreportSaleCL")
    @Expose
    public Boolean vcreportSaleCL;
    @SerializedName("vcgt")
    @Expose
    public Boolean vcgt;
    @SerializedName("vcgPClearAll")
    @Expose
    public Boolean vcgPClearAll;
    @SerializedName("vcreportSalePL")
    @Expose
    public Boolean vcreportSalePL;
    @SerializedName("vcreportSaleAL")
    @Expose
    public Boolean vcreportSaleAL;
    @SerializedName("vcsales")
    @Expose
    public Boolean vcsales;
    @SerializedName("vcgtReciepts")
    @Expose
    public Boolean vcgtReciepts;
    @SerializedName("vcreportSaleCP")
    @Expose
    public Boolean vcreportSaleCP;
    @SerializedName("vcgPBarCode")
    @Expose
    public Boolean vcgPBarCode;
    @SerializedName("vcgPCheckOut")
    @Expose
    public Boolean vcgPCheckOut;
    @SerializedName("vccontrolPanel")
    @Expose
    public Boolean vccontrolPanel;
    @SerializedName("vcreportSaleSA")
    @Expose
    public Boolean vcreportSaleSA;
    @SerializedName("vcgPCC")
    @Expose
    public Boolean vcgPCC;
    @SerializedName("vcgPContactAdd")
    @Expose
    public Boolean vcgPContactAdd;
    @SerializedName("vcreportSaleML")
    @Expose
    public Boolean vcreportSaleML;
    @SerializedName("vcgPDebitNote")
    @Expose
    public Boolean vcgPDebitNote;
    @SerializedName("vcgPEmployee")
    @Expose
    public Boolean vcgPEmployee;
    @SerializedName("vcgPInvLocAdd")
    @Expose
    public Boolean vcgPInvLocAdd;
    @SerializedName("vcreport")
    @Expose
    public Boolean vcreport;
    @SerializedName("vcreportSale")
    @Expose
    public Boolean vcreportSale;
    @SerializedName("vcreportSaleCS")
    @Expose
    public Boolean vcreportSaleCS;
    @SerializedName("vcgPReturn")
    @Expose
    public Boolean vcgPReturn;
    @SerializedName("vcgPMore")
    @Expose
    public Boolean vcgPMore;
    @SerializedName("vcgPSaveOrder")
    @Expose
    public Boolean vcgPSaveOrder;
    @SerializedName("vcdashboard")
    @Expose
    public Boolean vcdashboard;
    @SerializedName("vcgPCCAdd")
    @Expose
    public Boolean vcgPCCAdd;
    @SerializedName("vcinventory")
    @Expose
    public Boolean vcinventory;
    @SerializedName("vcgPInvLoc")
    @Expose
    public Boolean vcgPInvLoc;
    @SerializedName("vcgPContact")
    @Expose
    public Boolean vcgPContact;
    @SerializedName("vcgPPrintList")
    @Expose
    public Boolean vcgPPrintList;
    @SerializedName("vcpCContact")
    @Expose
    public Boolean vcpCContact;
    @SerializedName("vcpCContactAdd")
    @Expose
    public Boolean vcpCContactAdd;
    @SerializedName("vcpCAgentAdd")
    @Expose
    public Boolean vcpCAgentAdd;
    @SerializedName("vcpCAgent")
    @Expose
    public Boolean vcpCAgent;
    @SerializedName("vcpCPrintList")
    @Expose
    public Boolean vcpCPrintList;
    @SerializedName("vcpCReturn")
    @Expose
    public Boolean vcpCReturn;
    @SerializedName("vcpCImportItem")
    @Expose
    public Boolean vcpCImportItem;
    @SerializedName("vcpCCurrency")
    @Expose
    public Boolean vcpCCurrency;
    @SerializedName("vcmSAgent")
    @Expose
    public Boolean vcmSAgent;
    @SerializedName("vcmSDebitNote")
    @Expose
    public Boolean vcmSDebitNote;
    @SerializedName("vcpCEmployee")
    @Expose
    public Boolean vcpCEmployee;
    @SerializedName("vcmSState")
    @Expose
    public Boolean vcmSState;
    @SerializedName("vcpCTndCAdd")
    @Expose
    public Boolean vcpCTndCAdd;
    @SerializedName("vcpCTandC")
    @Expose
    public Boolean vcpCTandC;
    @SerializedName("vcpCCCAdd")
    @Expose
    public Boolean vcpCCCAdd;
    @SerializedName("vcpCCC")
    @Expose
    public Boolean vcpCCC;
    @SerializedName("vcmSItemAdd")
    @Expose
    public Boolean vcmSItemAdd;
    @SerializedName("vcmSContactAdd")
    @Expose
    public Boolean vcmSContactAdd;
    @SerializedName("vcpCSMAdd")
    @Expose
    public Boolean vcpCSMAdd;
    @SerializedName("vcpCSM")
    @Expose
    public Boolean vcpCSM;
    @SerializedName("vcpCRefNo")
    @Expose
    public Boolean vcpCRefNo;
    @SerializedName("vcmSInvLoc")
    @Expose
    public Boolean vcmSInvLoc;
    @SerializedName("vcmSStateAdd")
    @Expose
    public Boolean vcmSStateAdd;
    @SerializedName("vcpCItemAdd")
    @Expose
    public Boolean vcpCItemAdd;
    @SerializedName("vcpCClearDraft")
    @Expose
    public Boolean vcpCClearDraft;
    @SerializedName("vcmSCheckOut")
    @Expose
    public Boolean vcmSCheckOut;
    @SerializedName("vcmSTandC")
    @Expose
    public Boolean vcmSTandC;
    @SerializedName("vcmSCCAdd")
    @Expose
    public Boolean vcmSCCAdd;
    @SerializedName("vcmSContact")
    @Expose
    public Boolean vcmSContact;
    @SerializedName("vcmSReturn")
    @Expose
    public Boolean vcmSReturn;
    @SerializedName("vcmSSM")
    @Expose
    public Boolean vcmSSM;
    @SerializedName("vcmSImportItem")
    @Expose
    public Boolean vcmSImportItem;
    @SerializedName("vcpCSaveOrder")
    @Expose
    public Boolean vcpCSaveOrder;
    @SerializedName("vcpCCheckOut")
    @Expose
    public Boolean vcpCCheckOut;
    @SerializedName("vcmSTaxType")
    @Expose
    public Boolean vcmSTaxType;
    @SerializedName("vcpCCustomer")
    @Expose
    public Boolean vcpCCustomer;
    @SerializedName("vcpCRemoveItem")
    @Expose
    public Boolean vcpCRemoveItem;
    @SerializedName("vcmSClearAll")
    @Expose
    public Boolean vcmSClearAll;
    @SerializedName("vcmSInvLocAdd")
    @Expose
    public Boolean vcmSInvLocAdd;
    @SerializedName("vcmobileSales")
    @Expose
    public Boolean vcmobileSales;
    @SerializedName("vcmSPrintList")
    @Expose
    public Boolean vcmSPrintList;
    @SerializedName("vcmSSavesDraft")
    @Expose
    public Boolean vcmSSavesDraft;
    @SerializedName("vcmSCurrency")
    @Expose
    public Boolean vcmSCurrency;
    @SerializedName("vcmSSMAdd")
    @Expose
    public Boolean vcmSSMAdd;
    @SerializedName("vcmSAgentAdd")
    @Expose
    public Boolean vcmSAgentAdd;
    @SerializedName("vcpCSavesDraft")
    @Expose
    public Boolean vcpCSavesDraft;
    @SerializedName("vcmSMore")
    @Expose
    public Boolean vcmSMore;
    @SerializedName("vcpCTaxType")
    @Expose
    public Boolean vcpCTaxType;
    @SerializedName("vcpCInvLocAdd")
    @Expose
    public Boolean vcpCInvLocAdd;
    @SerializedName("vcmSClearDraft")
    @Expose
    public Boolean vcmSClearDraft;
    @SerializedName("vcmSCustomer")
    @Expose
    public Boolean vcmSCustomer;
    @SerializedName("vcmSCC")
    @Expose
    public Boolean vcmSCC;
    @SerializedName("vcmSRefNo")
    @Expose
    public Boolean vcmSRefNo;
    @SerializedName("vcmSRemoveItem")
    @Expose
    public Boolean vcmSRemoveItem;
    @SerializedName("vcpCBarCode")
    @Expose
    public Boolean vcpCBarCode;
    @SerializedName("vcmSEmployee")
    @Expose
    public Boolean vcmSEmployee;
    @SerializedName("vcpCClearAll")
    @Expose
    public Boolean vcpCClearAll;
    @SerializedName("vcmSBarCode")
    @Expose
    public Boolean vcmSBarCode;
    @SerializedName("vcmSSaveOrder")
    @Expose
    public Boolean vcmSSaveOrder;
    @SerializedName("vcmSTndCAdd")
    @Expose
    public Boolean vcmSTndCAdd;
    @SerializedName("vcpCInvLoc")
    @Expose
    public Boolean vcpCInvLoc;
    @SerializedName("vcpCStateAdd")
    @Expose
    public Boolean vcpCStateAdd;
    @SerializedName("vcpCMore")
    @Expose
    public Boolean vcpCMore;
    @SerializedName("vcpCState")
    @Expose
    public Boolean vcpCState;
    @SerializedName("vcgSReturn")
    @Expose
    public Boolean vcgSReturn;
    @SerializedName("vcpSStateAdd")
    @Expose
    public Boolean vcpSStateAdd;
    @SerializedName("vcgSEmpty1")
    @Expose
    public Boolean vcgSEmpty1;
    @SerializedName("vcgSEmpty2")
    @Expose
    public Boolean vcgSEmpty2;
    @SerializedName("vcpSAgent")
    @Expose
    public Boolean vcpSAgent;
    @SerializedName("vcrestaurant")
    @Expose
    public Boolean vcrestaurant;
    @SerializedName("vcgSCheckOut")
    @Expose
    public Boolean vcgSCheckOut;
    @SerializedName("vcpSRefNo")
    @Expose
    public Boolean vcpSRefNo;
    @SerializedName("vcgSClearAll")
    @Expose
    public Boolean vcgSClearAll;
    @SerializedName("vcgSItemLength")
    @Expose
    public Boolean vcgSItemLength;
    @SerializedName("vcgSPrintList")
    @Expose
    public Boolean vcgSPrintList;
    @SerializedName("vcpSInvLocAdd")
    @Expose
    public Boolean vcpSInvLocAdd;
    @SerializedName("vcgSBarCode")
    @Expose
    public Boolean vcgSBarCode;
    @SerializedName("vcrestBarcode")
    @Expose
    public Boolean vcrestBarcode;
    @SerializedName("vcrestTax")
    @Expose
    public Boolean vcrestTax;
    @SerializedName("vcpSCheckOut")
    @Expose
    public Boolean vcpSCheckOut;
    @SerializedName("vcrestItemAdd")
    @Expose
    public Boolean vcrestItemAdd;
    @SerializedName("vcrestTotalAmt")
    @Expose
    public Boolean vcrestTotalAmt;
    @SerializedName("vcrestClearAll")
    @Expose
    public Boolean vcrestClearAll;
    @SerializedName("vcrestPayment")
    @Expose
    public Boolean vcrestPayment;
    @SerializedName("vcrestPrint")
    @Expose
    public Boolean vcrestPrint;
    @SerializedName("vcservice")
    @Expose
    public Boolean vcservice;
    @SerializedName("vcserviceState")
    @Expose
    public Boolean vcserviceState;
    @SerializedName("vcserviceMore")
    @Expose
    public Boolean vcserviceMore;
    @SerializedName("vcserviceAgent")
    @Expose
    public Boolean vcserviceAgent;
    @SerializedName("vcserviceTandC")
    @Expose
    public Boolean vcserviceTandC;
    @SerializedName("vcpCDebitNote")
    @Expose
    public Boolean vcpCDebitNote;
    @SerializedName("vcpSInvLoc")
    @Expose
    public Boolean vcpSInvLoc;
    @SerializedName("vcpSMore")
    @Expose
    public Boolean vcpSMore;
    @SerializedName("vcpSPrintList")
    @Expose
    public Boolean vcpSPrintList;
    @SerializedName("vcpSTandC")
    @Expose
    public Boolean vcpSTandC;
    @SerializedName("vcpSContactAdd")
    @Expose
    public Boolean vcpSContactAdd;
    @SerializedName("vcpSItemAdd")
    @Expose
    public Boolean vcpSItemAdd;
    @SerializedName("vcpSReturn")
    @Expose
    public Boolean vcpSReturn;
    @SerializedName("vcpSTndCAdd")
    @Expose
    public Boolean vcpSTndCAdd;
    @SerializedName("vcpSSMAdd")
    @Expose
    public Boolean vcpSSMAdd;
    @SerializedName("vcpSClearDraft")
    @Expose
    public Boolean vcpSClearDraft;
    @SerializedName("vcpSContact")
    @Expose
    public Boolean vcpSContact;
    @SerializedName("vcpSImportItem")
    @Expose
    public Boolean vcpSImportItem;
    @SerializedName("vcpSCCAdd")
    @Expose
    public Boolean vcpSCCAdd;
    @SerializedName("vcpSSM")
    @Expose
    public Boolean vcpSSM;
    @SerializedName("vcpSDebitNote")
    @Expose
    public Boolean vcpSDebitNote;
    @SerializedName("vcgSItemAdd")
    @Expose
    public Boolean vcgSItemAdd;
    @SerializedName("vcpSBarCode")
    @Expose
    public Boolean vcpSBarCode;
    @SerializedName("vcpSCC")
    @Expose
    public Boolean vcpSCC;
    @SerializedName("vcpSSaveOrder")
    @Expose
    public Boolean vcpSSaveOrder;
    @SerializedName("vcpSEmployee")
    @Expose
    public Boolean vcpSEmployee;
    @SerializedName("vcpSCurrency")
    @Expose
    public Boolean vcpSCurrency;
    @SerializedName("vcpSRemoveItem")
    @Expose
    public Boolean vcpSRemoveItem;
    @SerializedName("vcgoldsales")
    @Expose
    public Boolean vcgoldsales;
    @SerializedName("vcgSItemList")
    @Expose
    public Boolean vcgSItemList;
    @SerializedName("vcpSCustomer")
    @Expose
    public Boolean vcpSCustomer;
    @SerializedName("vcpSState")
    @Expose
    public Boolean vcpSState;
    @SerializedName("vcgSDate")
    @Expose
    public Boolean vcgSDate;
    @SerializedName("vcpSClearAll")
    @Expose
    public Boolean vcpSClearAll;
    @SerializedName("vcpSSavesDraft")
    @Expose
    public Boolean vcpSSavesDraft;
    @SerializedName("vcgSTax")
    @Expose
    public Boolean vcgSTax;
    @SerializedName("vcpSAgentAdd")
    @Expose
    public Boolean vcpSAgentAdd;
    @SerializedName("vcgSImportItem")
    @Expose
    public Boolean vcgSImportItem;
    @SerializedName("vcgSRemoveItem")
    @Expose
    public Boolean vcgSRemoveItem;
    @SerializedName("vcpSTaxType")
    @Expose
    public Boolean vcpSTaxType;
    @SerializedName("vcpharmaSales")
    @Expose
    public Boolean vcpharmaSales;
    @SerializedName("vcgSTotalAmt")
    @Expose
    public Boolean vcgSTotalAmt;
    @SerializedName("vcgSDraftClear")
    @Expose
    public Boolean vcgSDraftClear;
    @SerializedName("vcserviceCCAdd")
    @Expose
    public Boolean vcserviceCCAdd;
    @SerializedName("vcserviceSM")
    @Expose
    public Boolean vcserviceSM;
    @SerializedName("vcserviceSMAdd")
    @Expose
    public Boolean vcserviceSMAdd;
    @SerializedName("vcserviceCC")
    @Expose
    public Boolean vcserviceCC;
    @SerializedName("vcserviceRefNo")
    @Expose
    public Boolean vcserviceRefNo;
    @SerializedName("vcreportInventoryASCR")
    @Expose
    public Boolean vcreportInventoryASCR;
    @SerializedName("vcreportInventoryBER")
    @Expose
    public Boolean vcreportInventoryBER;
    @SerializedName("vcpurchaseSupplierAdd")
    @Expose
    public Boolean vcpurchaseSupplierAdd;
    @SerializedName("vcreportInventoryBI")
    @Expose
    public Boolean vcreportInventoryBI;
    @SerializedName("vcreportInventoryOGRNR")
    @Expose
    public Boolean vcreportInventoryOGRNR;
    @SerializedName("vcreportPurchase")
    @Expose
    public Boolean vcreportPurchase;
    @SerializedName("vcpurchaseInvokePurchaseOrder")
    @Expose
    public Boolean vcpurchaseInvokePurchaseOrder;
    @SerializedName("vcreportInventoryilSCR")
    @Expose
    public Boolean vcreportInventoryilSCR;
    @SerializedName("vcreportFStatementTB")
    @Expose
    public Boolean vcreportFStatementTB;
    @SerializedName("vcreportFStatementVL")
    @Expose
    public Boolean vcreportFStatementVL;
    @SerializedName("vcreportPurchaseCPA")
    @Expose
    public Boolean vcreportPurchaseCPA;
    @SerializedName("vcreportInventoryIVR")
    @Expose
    public Boolean vcreportInventoryIVR;
    @SerializedName("vcreportPurchasePA")
    @Expose
    public Boolean vcreportPurchasePA;
    @SerializedName("vcreportFStatementPL")
    @Expose
    public Boolean vcreportFStatementPL;
    @SerializedName("vcreportAnalysis")
    @Expose
    public Boolean vcreportAnalysis;
    @SerializedName("vcreportAnalysisCIP")
    @Expose
    public Boolean vcreportAnalysisCIP;
    @SerializedName("vcreportFStatementBS")
    @Expose
    public Boolean vcreportFStatementBS;
    @SerializedName("vcreportPurchasePIL")
    @Expose
    public Boolean vcreportPurchasePIL;
    @SerializedName("vcpurchaseClearAll")
    @Expose
    public Boolean vcpurchaseClearAll;
    @SerializedName("vcreportInventoryADVSCR")
    @Expose
    public Boolean vcreportInventoryADVSCR;
    @SerializedName("vcreportPurchaseSL")
    @Expose
    public Boolean vcreportPurchaseSL;
    @SerializedName("vcreportPurchaseSP")
    @Expose
    public Boolean vcreportPurchaseSP;
    @SerializedName("vcreportPurchaseSSL")
    @Expose
    public Boolean vcreportPurchaseSSL;
    @SerializedName("vcreportPurchaseSSR")
    @Expose
    public Boolean vcreportPurchaseSSR;
    @SerializedName("vcreportAnalysisCRR")
    @Expose
    public Boolean vcreportAnalysisCRR;
    @SerializedName("vcreportInventoryRPL")
    @Expose
    public Boolean vcreportInventoryRPL;
    @SerializedName("vcreportPurchaseRIL")
    @Expose
    public Boolean vcreportPurchaseRIL;
    @SerializedName("vcreportInventorySI")
    @Expose
    public Boolean vcreportInventorySI;
    @SerializedName("vcreportInventoryASSCR")
    @Expose
    public Boolean vcreportInventoryASSCR;
    @SerializedName("vcreportPurchaseCA")
    @Expose
    public Boolean vcreportPurchaseCA;
    @SerializedName("vcpurchaseSelfBilled")
    @Expose
    public Boolean vcpurchaseSelfBilled;
    @SerializedName("vcreportAnalysisTopItem")
    @Expose
    public Boolean vcreportAnalysisTopItem;
    @SerializedName("vcreportSaleCLP")
    @Expose
    public Boolean vcreportSaleCLP;
    @SerializedName("vcpurchaseSavePurchaseOrder")
    @Expose
    public Boolean vcpurchaseSavePurchaseOrder;
    @SerializedName("vcpurchaseItemAdd")
    @Expose
    public Boolean vcpurchaseItemAdd;
    @SerializedName("vcreportPurchasePRL")
    @Expose
    public Boolean vcreportPurchasePRL;
    @SerializedName("vcreportAnalysisTopVendor")
    @Expose
    public Boolean vcreportAnalysisTopVendor;
    @SerializedName("vcreportInventoryDEL")
    @Expose
    public Boolean vcreportInventoryDEL;
    @SerializedName("vcreportInventoryGRNVR")
    @Expose
    public Boolean vcreportInventoryGRNVR;
    @SerializedName("vcreportPurchasePOL")
    @Expose
    public Boolean vcreportPurchasePOL;
    @SerializedName("vcpurchasePrintList")
    @Expose
    public Boolean vcpurchasePrintList;
    @SerializedName("vcreportAnalysisSIP")
    @Expose
    public Boolean vcreportAnalysisSIP;
    @SerializedName("vcreportBankRecon")
    @Expose
    public Boolean vcreportBankRecon;
    @SerializedName("vcpurchaseCheckOut")
    @Expose
    public Boolean vcpurchaseCheckOut;
    @SerializedName("vcreportFStatementGL")
    @Expose
    public Boolean vcreportFStatementGL;
    @SerializedName("vcreportInventoryIL")
    @Expose
    public Boolean vcreportInventoryIL;
    @SerializedName("vcreportInventoryINMR")
    @Expose
    public Boolean vcreportInventoryINMR;
    @SerializedName("vcreportInventoryIMR")
    @Expose
    public Boolean vcreportInventoryIMR;
    @SerializedName("vcreportInventoryIPR")
    @Expose
    public Boolean vcreportInventoryIPR;
    @SerializedName("vcreportInventoryRPR")
    @Expose
    public Boolean vcreportInventoryRPR;
    @SerializedName("vcreportInventory")
    @Expose
    public Boolean vcreportInventory;
    @SerializedName("vcreportAnalysisTopCustomer")
    @Expose
    public Boolean vcreportAnalysisTopCustomer;
    @SerializedName("vcreportInventoryADVSSR")
    @Expose
    public Boolean vcreportInventoryADVSSR;
    @SerializedName("vcpurchaseReturn")
    @Expose
    public Boolean vcpurchaseReturn;
    @SerializedName("vcreportFStatementGJ")
    @Expose
    public Boolean vcreportFStatementGJ;
    @SerializedName("vcreportFinancialStatement")
    @Expose
    public Boolean vcreportFinancialStatement;
    @SerializedName("vcreportInventoryISR")
    @Expose
    public Boolean vcreportInventoryISR;
    @SerializedName("vcpurchaseSupplierPayment")
    @Expose
    public Boolean vcpurchaseSupplierPayment;
    @SerializedName("vcreportBankReconRatio")
    @Expose
    public Boolean vcreportBankReconRatio;
    @SerializedName("vcpurSaveRecItem")
    @Expose
    public Boolean vcpurSaveRecItem;
    @SerializedName("vcsalesStateAdd")
    @Expose
    public Boolean vcsalesStateAdd;
    @SerializedName("vcpurInvokeRecItem")
    @Expose
    public Boolean vcpurInvokeRecItem;
    @SerializedName("vcsalesCurrency")
    @Expose
    public Boolean vcsalesCurrency;
    @SerializedName("vcsalesSaveProforma")
    @Expose
    public Boolean vcsalesSaveProforma;
    @SerializedName("vcpurReturnRecItem")
    @Expose
    public Boolean vcpurReturnRecItem;
    @SerializedName("vcpurInvLocAdd")
    @Expose
    public Boolean vcpurInvLocAdd;
    @SerializedName("vcsalesInvokeDraft")
    @Expose
    public Boolean vcsalesInvokeDraft;
    @SerializedName("vcsalesSerializeItem")
    @Expose
    public Boolean vcsalesSerializeItem;
    @SerializedName("vcpurRemoveItem")
    @Expose
    public Boolean vcpurRemoveItem;
    @SerializedName("vcsalesAdvancePayment")
    @Expose
    public Boolean vcsalesAdvancePayment;
    @SerializedName("vcsalesEmployeeAdd")
    @Expose
    public Boolean vcsalesEmployeeAdd;
    @SerializedName("vcsalesTndCAdd")
    @Expose
    public Boolean vcsalesTndCAdd;
    @SerializedName("vcsalesReturnDeliveryOrder")
    @Expose
    public Boolean vcsalesReturnDeliveryOrder;
    @SerializedName("vcsalesSavesDraft")
    @Expose
    public Boolean vcsalesSavesDraft;
    @SerializedName("vcsalesClearAll")
    @Expose
    public Boolean vcsalesClearAll;
    @SerializedName("vcsalesCustomer")
    @Expose
    public Boolean vcsalesCustomer;
    @SerializedName("vcsalesInvokeSalesOrder")
    @Expose
    public Boolean vcsalesInvokeSalesOrder;
    @SerializedName("vcreportSaleSRL")
    @Expose
    public Boolean vcreportSaleSRL;
    @SerializedName("vcsalesRemoveItem")
    @Expose
    public Boolean vcsalesRemoveItem;
    @SerializedName("vcsalesContact")
    @Expose
    public Boolean vcsalesContact;
    @SerializedName("vcsalesCurrencyAdd")
    @Expose
    public Boolean vcsalesCurrencyAdd;
    @SerializedName("vcsalesSaveSalesQuotation")
    @Expose
    public Boolean vcsalesSaveSalesQuotation;
    @SerializedName("vcsalesInvokeSalesQuotation")
    @Expose
    public Boolean vcsalesInvokeSalesQuotation;
    @SerializedName("vcsalesClearDraft")
    @Expose
    public Boolean vcsalesClearDraft;
    @SerializedName("vctaxTaxConfiguration")
    @Expose
    public Boolean vctaxTaxConfiguration;
    @SerializedName("vcreportSaleCSI")
    @Expose
    public Boolean vcreportSaleCSI;
    @SerializedName("vcreportSaleSQL")
    @Expose
    public Boolean vcreportSaleSQL;
    @SerializedName("vcsalesEmployee")
    @Expose
    public Boolean vcsalesEmployee;
    @SerializedName("vcreportSalePDL")
    @Expose
    public Boolean vcreportSalePDL;
    @SerializedName("vcsalesSaveSalesOrder")
    @Expose
    public Boolean vcsalesSaveSalesOrder;
    @SerializedName("vcsalesInvLocAdd")
    @Expose
    public Boolean vcsalesInvLocAdd;
    @SerializedName("vcreportSaleICL")
    @Expose
    public Boolean vcreportSaleICL;
    @SerializedName("vcsalesPrintList")
    @Expose
    public Boolean vcsalesPrintList;
    @SerializedName("vcsalesSearchSerializeItem")
    @Expose
    public Boolean vcsalesSearchSerializeItem;
    @SerializedName("vcsalesImportItem")
    @Expose
    public Boolean vcsalesImportItem;
    @SerializedName("vcsalesAgentAdd")
    @Expose
    public Boolean vcsalesAgentAdd;
    @SerializedName("vcsalesExchangeRateAdd")
    @Expose
    public Boolean vcsalesExchangeRateAdd;
    @SerializedName("vcsalesCustomerAdd")
    @Expose
    public Boolean vcsalesCustomerAdd;
    @SerializedName("vcsalesExchangeRate")
    @Expose
    public Boolean vcsalesExchangeRate;
    @SerializedName("vcsalesCheckOut")
    @Expose
    public Boolean vcsalesCheckOut;
    @SerializedName("vcsalesShippingRefNo")
    @Expose
    public Boolean vcsalesShippingRefNo;
    @SerializedName("vcsalesInvokeProforma")
    @Expose
    public Boolean vcsalesInvokeProforma;
    @SerializedName("vcreportSaleSIL")
    @Expose
    public Boolean vcreportSaleSIL;
    @SerializedName("vcreportSaleSOL")
    @Expose
    public Boolean vcreportSaleSOL;
    @SerializedName("vcsalesRecievePayment")
    @Expose
    public Boolean vcsalesRecievePayment;
    @SerializedName("vcsalesBarCode")
    @Expose
    public Boolean vcsalesBarCode;
    @SerializedName("vcsalesAdvancePaymentList")
    @Expose
    public Boolean vcsalesAdvancePaymentList;
    @SerializedName("vcsalesSearchBarCode")
    @Expose
    public Boolean vcsalesSearchBarCode;
    @SerializedName("vcsalesTaxType")
    @Expose
    public Boolean vcsalesTaxType;
    @SerializedName("vcsalesContactAdd")
    @Expose
    public Boolean vcsalesContactAdd;
    @SerializedName("vcsalesSaveDeliveryOrder")
    @Expose
    public Boolean vcsalesSaveDeliveryOrder;
    @SerializedName("vcsalesDebitNote")
    @Expose
    public Boolean vcsalesDebitNote;
    @SerializedName("vcdraftInvoice")
    @Expose
    public Boolean vcdraftInvoice;
    @SerializedName("vcsalesInvokeDeliveryOrder")
    @Expose
    public Boolean vcsalesInvokeDeliveryOrder;
    @SerializedName("vcpurSearchBarCode")
    @Expose
    public Boolean vcpurSearchBarCode;
    @SerializedName("vcrevChargeInv")
    @Expose
    public Boolean vcrevChargeInv;
    @SerializedName("vcpurCreditNote")
    @Expose
    public Boolean vcpurCreditNote;
    @SerializedName("vcgstIndiaGSTR1Tool")
    @Expose
    public Boolean vcgstIndiaGSTR1Tool;
    @SerializedName("vcmanufacturingAP")
    @Expose
    public Boolean vcmanufacturingAP;
    @SerializedName("vcgPSearchBarCode")
    @Expose
    public Boolean vcgPSearchBarCode;
    @SerializedName("vcgPCustomerAdd")
    @Expose
    public Boolean vcgPCustomerAdd;
    @SerializedName("vcinventoryInvLocTransfer")
    @Expose
    public Boolean vcinventoryInvLocTransfer;
    @SerializedName("vcreportFStatementDB")
    @Expose
    public Boolean vcreportFStatementDB;
    @SerializedName("vcreportBankReconCheques")
    @Expose
    public Boolean vcreportBankReconCheques;
    @SerializedName("vcgPSerializeItem")
    @Expose
    public Boolean vcgPSerializeItem;
    @SerializedName("vcgPAdvancePayment")
    @Expose
    public Boolean vcgPAdvancePayment;
    @SerializedName("vcpurCurrencyAdd")
    @Expose
    public Boolean vcpurCurrencyAdd;
    @SerializedName("vcgPSaveQuotation")
    @Expose
    public Boolean vcgPSaveQuotation;
    @SerializedName("vcinventoryRedemption")
    @Expose
    public Boolean vcinventoryRedemption;
    @SerializedName("vcgPInvokeQuotation")
    @Expose
    public Boolean vcgPInvokeQuotation;
    @SerializedName("vcgPAdvancePaymentList")
    @Expose
    public Boolean vcgPAdvancePaymentList;
    @SerializedName("vccPCompanySetupBandR")
    @Expose
    public Boolean vccPCompanySetupBandR;
    @SerializedName("vcinventoryAdvDisConfig")
    @Expose
    public Boolean vcinventoryAdvDisConfig;
    @SerializedName("vccPCompanySetupRS")
    @Expose
    public Boolean vccPCompanySetupRS;
    @SerializedName("vcgPInvokeOrder")
    @Expose
    public Boolean vcgPInvokeOrder;
    @SerializedName("vcgPRecievePayment")
    @Expose
    public Boolean vcgPRecievePayment;
    @SerializedName("vcinventoryLoyality")
    @Expose
    public Boolean vcinventoryLoyality;
    @SerializedName("vcinventoryInvCountType")
    @Expose
    public Boolean vcinventoryInvCountType;
    @SerializedName("vcinventoryItemCommission")
    @Expose
    public Boolean vcinventoryItemCommission;
    @SerializedName("vccPCompanySetupAlerts")
    @Expose
    public Boolean vccPCompanySetupAlerts;
    @SerializedName("vcreportBankReconStmnt")
    @Expose
    public Boolean vcreportBankReconStmnt;
    @SerializedName("vcgstIndiaGSTR2Tool")
    @Expose
    public Boolean vcgstIndiaGSTR2Tool;
    @SerializedName("vcreportFStatementPLLocWise")
    @Expose
    public Boolean vcreportFStatementPLLocWise;
    @SerializedName("vcmanufacturing")
    @Expose
    public Boolean vcmanufacturing;
    @SerializedName("vcgoldPurchases")
    @Expose
    public Boolean vcgoldPurchases;
    @SerializedName("vcmanufacturingCR")
    @Expose
    public Boolean vcmanufacturingCR;
    @SerializedName("vcinventoryPayVoucher")
    @Expose
    public Boolean vcinventoryPayVoucher;
    @SerializedName("vcpurExchangeRateAdd")
    @Expose
    public Boolean vcpurExchangeRateAdd;
    @SerializedName("vcmanufacturingFSCR")
    @Expose
    public Boolean vcmanufacturingFSCR;
    @SerializedName("vcgstIndiaRIPC")
    @Expose
    public Boolean vcgstIndiaRIPC;
    @SerializedName("vcgstMalGAFExport")
    @Expose
    public Boolean vcgstMalGAFExport;
    @SerializedName("vcgstIndiaGST3B")
    @Expose
    public Boolean vcgstIndiaGST3B;
    @SerializedName("vcgstMalGSTReturn")
    @Expose
    public Boolean vcgstMalGSTReturn;
    @SerializedName("vcinventoryAttributeConfig")
    @Expose
    public Boolean vcinventoryAttributeConfig;
    @SerializedName("vcinventoryVoucher")
    @Expose
    public Boolean vcinventoryVoucher;
    @SerializedName("vcinventoryJSDN")
    @Expose
    public Boolean vcinventoryJSDN;
    @SerializedName("vcmanufacturingAICAR")
    @Expose
    public Boolean vcmanufacturingAICAR;
    @SerializedName("vcgstIndiaGSTTRANS1")
    @Expose
    public Boolean vcgstIndiaGSTTRANS1;
    @SerializedName("vccPConfiguratorServiceCharge")
    @Expose
    public Boolean vccPConfiguratorServiceCharge;
    @SerializedName("vccontrolPanelDSTableConfig")
    @Expose
    public Boolean vccontrolPanelDSTableConfig;
    @SerializedName("vcpurShippingRefNo")
    @Expose
    public Boolean vcpurShippingRefNo;
    @SerializedName("vcinventoryItemCountAdjust")
    @Expose
    public Boolean vcinventoryItemCountAdjust;
    @SerializedName("vcpurExchangeRate")
    @Expose
    public Boolean vcpurExchangeRate;
    @SerializedName("vcgstIndiaGSTSR")
    @Expose
    public Boolean vcgstIndiaGSTSR;
    @SerializedName("vccPConfiguratorOffersConfig")
    @Expose
    public Boolean vccPConfiguratorOffersConfig;
    @SerializedName("vccontrolPanelDSEandSMSConfig")
    @Expose
    public Boolean vccontrolPanelDSEandSMSConfig;
    @SerializedName("vcpurEmployeeAdd")
    @Expose
    public Boolean vcpurEmployeeAdd;
    @SerializedName("vcinventoryAssetsCat")
    @Expose
    public Boolean vcinventoryAssetsCat;
    @SerializedName("vcmanufacturingPM")
    @Expose
    public Boolean vcmanufacturingPM;
    @SerializedName("vccPConfiguratorCountry")
    @Expose
    public Boolean vccPConfiguratorCountry;
    @SerializedName("vcinventoryAssets")
    @Expose
    public Boolean vcinventoryAssets;
    @SerializedName("vcinventoryAttribute")
    @Expose
    public Boolean vcinventoryAttribute;
    @SerializedName("vcgPSearchSerializeItem")
    @Expose
    public Boolean vcgPSearchSerializeItem;
    @SerializedName("vcpurImportItem")
    @Expose
    public Boolean vcpurImportItem;
    @SerializedName("vcinventoryHsnCode")
    @Expose
    public Boolean vcinventoryHsnCode;
    @SerializedName("vcinventoryUomConverter")
    @Expose
    public Boolean vcinventoryUomConverter;
    @SerializedName("vcinventoryAdvTableTransfer")
    @Expose
    public Boolean vcinventoryAdvTableTransfer;
    @SerializedName("vccontrolPanelDSTable")
    @Expose
    public Boolean vccontrolPanelDSTable;
    @SerializedName("vcpurContactAdd")
    @Expose
    public Boolean vcpurContactAdd;
    @SerializedName("vcmSAdvancePaymentList")
    @Expose
    public Boolean vcmSAdvancePaymentList;
    @SerializedName("vcmSSearchSerializeItem")
    @Expose
    public Boolean vcmSSearchSerializeItem;
    @SerializedName("vcgPInvokeDraft")
    @Expose
    public Boolean vcgPInvokeDraft;
    @SerializedName("vcgPExchangeRate")
    @Expose
    public Boolean vcgPExchangeRate;
    @SerializedName("vcpCCurrencyAdd")
    @Expose
    public Boolean vcpCCurrencyAdd;
    @SerializedName("vcmSCustomerAdd")
    @Expose
    public Boolean vcmSCustomerAdd;
    @SerializedName("vcpCSaveQuotation")
    @Expose
    public Boolean vcpCSaveQuotation;
    @SerializedName("vcpCEmployeeAdd")
    @Expose
    public Boolean vcpCEmployeeAdd;
    @SerializedName("vcpCExchangeRateAdd")
    @Expose
    public Boolean vcpCExchangeRateAdd;
    @SerializedName("vcpCExchangeRate")
    @Expose
    public Boolean vcpCExchangeRate;
    @SerializedName("vcpCShippingRefNo")
    @Expose
    public Boolean vcpCShippingRefNo;
    @SerializedName("vcgPReturnDeliveryOrder")
    @Expose
    public Boolean vcgPReturnDeliveryOrder;
    @SerializedName("vcmSCurrencyAdd")
    @Expose
    public Boolean vcmSCurrencyAdd;
    @SerializedName("vcpCRecievePayment")
    @Expose
    public Boolean vcpCRecievePayment;
    @SerializedName("vcmSSaveProforma")
    @Expose
    public Boolean vcmSSaveProforma;
    @SerializedName("vcpCInvokeQuotation")
    @Expose
    public Boolean vcpCInvokeQuotation;
    @SerializedName("vcmSDraftInvoice")
    @Expose
    public Boolean vcmSDraftInvoice;
    @SerializedName("vcgPEmployeeAdd")
    @Expose
    public Boolean vcgPEmployeeAdd;
    @SerializedName("vcmSSerializeItem")
    @Expose
    public Boolean vcmSSerializeItem;
    @SerializedName("vcmSShippingRefNo")
    @Expose
    public Boolean vcmSShippingRefNo;
    @SerializedName("vcmSExchangeRate")
    @Expose
    public Boolean vcmSExchangeRate;
    @SerializedName("vcmSInvokeQuotation")
    @Expose
    public Boolean vcmSInvokeQuotation;
    @SerializedName("vcpurchasesConstruction")
    @Expose
    public Boolean vcpurchasesConstruction;
    @SerializedName("vcgPExchangeRateAdd")
    @Expose
    public Boolean vcgPExchangeRateAdd;
    @SerializedName("vcmSInvokeDeliveryOrder")
    @Expose
    public Boolean vcmSInvokeDeliveryOrder;
    @SerializedName("vcgPSaveProforma")
    @Expose
    public Boolean vcgPSaveProforma;
    @SerializedName("vcpCInvokeDraft")
    @Expose
    public Boolean vcpCInvokeDraft;
    @SerializedName("vcmSReturnDeliveryOrder")
    @Expose
    public Boolean vcmSReturnDeliveryOrder;
    @SerializedName("vcpCSearchBarCode")
    @Expose
    public Boolean vcpCSearchBarCode;
    @SerializedName("vcpCSerializeItem")
    @Expose
    public Boolean vcpCSerializeItem;
    @SerializedName("vcpCAdvancePaymentList")
    @Expose
    public Boolean vcpCAdvancePaymentList;
    @SerializedName("vcgPInvokeProforma")
    @Expose
    public Boolean vcgPInvokeProforma;
    @SerializedName("vcmSInvokeOrder")
    @Expose
    public Boolean vcmSInvokeOrder;
    @SerializedName("vcmSEmployeeAdd")
    @Expose
    public Boolean vcmSEmployeeAdd;
    @SerializedName("vcmSExchangeRateAdd")
    @Expose
    public Boolean vcmSExchangeRateAdd;
    @SerializedName("vcpCSearchSerializeItem")
    @Expose
    public Boolean vcpCSearchSerializeItem;
    @SerializedName("vcpCAdvancePayment")
    @Expose
    public Boolean vcpCAdvancePayment;
    @SerializedName("vcgPShippingRefNo")
    @Expose
    public Boolean vcgPShippingRefNo;
    @SerializedName("vcgPSaveDeliveryOrder")
    @Expose
    public Boolean vcgPSaveDeliveryOrder;
    @SerializedName("vcgPDraftInvoice")
    @Expose
    public Boolean vcgPDraftInvoice;
    @SerializedName("vcmSRecievePayment")
    @Expose
    public Boolean vcmSRecievePayment;
    @SerializedName("vcmSInvokeDraft")
    @Expose
    public Boolean vcmSInvokeDraft;
    @SerializedName("vcmSInvokeProforma")
    @Expose
    public Boolean vcmSInvokeProforma;
    @SerializedName("vcmSSaveDeliveryOrder")
    @Expose
    public Boolean vcmSSaveDeliveryOrder;
    @SerializedName("vcpCCustomerAdd")
    @Expose
    public Boolean vcpCCustomerAdd;
    @SerializedName("vcmSAdvancePayment")
    @Expose
    public Boolean vcmSAdvancePayment;
    @SerializedName("vcpCInvokeOrder")
    @Expose
    public Boolean vcpCInvokeOrder;
    @SerializedName("vcgPCurrencyAdd")
    @Expose
    public Boolean vcgPCurrencyAdd;
    @SerializedName("vcmSSearchBarCode")
    @Expose
    public Boolean vcmSSearchBarCode;
    @SerializedName("vcgPInvokeDeliveryOrder")
    @Expose
    public Boolean vcgPInvokeDeliveryOrder;
    @SerializedName("vcmSSaveQuotation")
    @Expose
    public Boolean vcmSSaveQuotation;
    @SerializedName("vcrestItemSearch")
    @Expose
    public Boolean vcrestItemSearch;
    @SerializedName("vcrestCustomerAdd")
    @Expose
    public Boolean vcrestCustomerAdd;
    @SerializedName("vcrestSelectCategory")
    @Expose
    public Boolean vcrestSelectCategory;
    @SerializedName("vcgSCustomerList")
    @Expose
    public Boolean vcgSCustomerList;
    @SerializedName("vcpCSaveProforma")
    @Expose
    public Boolean vcpCSaveProforma;
    @SerializedName("vcpCReturnDeliveryOrder")
    @Expose
    public Boolean vcpCReturnDeliveryOrder;
    @SerializedName("vcrestRemoveItem")
    @Expose
    public Boolean vcrestRemoveItem;
    @SerializedName("vcrestTableAppend")
    @Expose
    public Boolean vcrestTableAppend;
    @SerializedName("vcpSAdvancePayment")
    @Expose
    public Boolean vcpSAdvancePayment;
    @SerializedName("vcrestSelectItem")
    @Expose
    public Boolean vcrestSelectItem;
    @SerializedName("vcpSSaveQuotation")
    @Expose
    public Boolean vcpSSaveQuotation;
    @SerializedName("vcrestOpenDrawer")
    @Expose
    public Boolean vcrestOpenDrawer;
    @SerializedName("vcrestRestaurentTable")
    @Expose
    public Boolean vcrestRestaurentTable;
    @SerializedName("vcserviceItemAdd")
    @Expose
    public Boolean vcserviceItemAdd;
    @SerializedName("vcserviceCustomerAdd")
    @Expose
    public Boolean vcserviceCustomerAdd;
    @SerializedName("vcrestSplitBill")
    @Expose
    public Boolean vcrestSplitBill;
    @SerializedName("vcpSSerializeItem")
    @Expose
    public Boolean vcpSSerializeItem;
    @SerializedName("vcserviceSavesDraft")
    @Expose
    public Boolean vcserviceSavesDraft;
    @SerializedName("vcrestAppendItem")
    @Expose
    public Boolean vcrestAppendItem;
    @SerializedName("vcpSDraftInvoice")
    @Expose
    public Boolean vcpSDraftInvoice;
    @SerializedName("vcrestAppendCustomer")
    @Expose
    public Boolean vcrestAppendCustomer;
    @SerializedName("vcgSSearchSerializeItem")
    @Expose
    public Boolean vcgSSearchSerializeItem;
    @SerializedName("vcpSExchangeRate")
    @Expose
    public Boolean vcpSExchangeRate;
    @SerializedName("vcgSSaveSalesOrder")
    @Expose
    public Boolean vcgSSaveSalesOrder;
    @SerializedName("vcgSSerializeItem")
    @Expose
    public Boolean vcgSSerializeItem;
    @SerializedName("vcpCSaveDeliveryOrder")
    @Expose
    public Boolean vcpCSaveDeliveryOrder;
    @SerializedName("vcpCInvokeDeliveryOrder")
    @Expose
    public Boolean vcpCInvokeDeliveryOrder;
    @SerializedName("vcpSInvokeOrder")
    @Expose
    public Boolean vcpSInvokeOrder;
    @SerializedName("vcpSSearchSerializeItem")
    @Expose
    public Boolean vcpSSearchSerializeItem;
    @SerializedName("vcpSExchangeRateAdd")
    @Expose
    public Boolean vcpSExchangeRateAdd;
    @SerializedName("vcpSInvokeDeliveryOrder")
    @Expose
    public Boolean vcpSInvokeDeliveryOrder;
    @SerializedName("vcpCDraftInvoice")
    @Expose
    public Boolean vcpCDraftInvoice;
    @SerializedName("vcpSInvokeQuotation")
    @Expose
    public Boolean vcpSInvokeQuotation;
    @SerializedName("vcpSInvokeDraft")
    @Expose
    public Boolean vcpSInvokeDraft;
    @SerializedName("vcpSCustomerAdd")
    @Expose
    public Boolean vcpSCustomerAdd;
    @SerializedName("vcpSSearchBarCode")
    @Expose
    public Boolean vcpSSearchBarCode;
    @SerializedName("vcpSCurrencyAdd")
    @Expose
    public Boolean vcpSCurrencyAdd;
    @SerializedName("vcgSAdvancePayment")
    @Expose
    public Boolean vcgSAdvancePayment;
    @SerializedName("vcpSRecievePayment")
    @Expose
    public Boolean vcpSRecievePayment;
    @SerializedName("vcgSAdvancePaymentList")
    @Expose
    public Boolean vcgSAdvancePaymentList;
    @SerializedName("vcgSRecievePayment")
    @Expose
    public Boolean vcgSRecievePayment;
    @SerializedName("vcgSDraftInvoke")
    @Expose
    public Boolean vcgSDraftInvoke;
    @SerializedName("vcpSSaveDeliveryOrder")
    @Expose
    public Boolean vcpSSaveDeliveryOrder;
    @SerializedName("vcgSSearchBarCode")
    @Expose
    public Boolean vcgSSearchBarCode;
    @SerializedName("vcpSAdvancePaymentList")
    @Expose
    public Boolean vcpSAdvancePaymentList;
    @SerializedName("vcpSSaveProforma")
    @Expose
    public Boolean vcpSSaveProforma;
    @SerializedName("vcgSSalesInvoke")
    @Expose
    public Boolean vcgSSalesInvoke;
    @SerializedName("vcpCInvokeProforma")
    @Expose
    public Boolean vcpCInvokeProforma;
    @SerializedName("vcpSEmployeeAdd")
    @Expose
    public Boolean vcpSEmployeeAdd;
    @SerializedName("vcpSShippingRefNo")
    @Expose
    public Boolean vcpSShippingRefNo;
    @SerializedName("vcpSInvokeProforma")
    @Expose
    public Boolean vcpSInvokeProforma;
    @SerializedName("vcpSReturnDeliveryOrder")
    @Expose
    public Boolean vcpSReturnDeliveryOrder;
    @SerializedName("vcgSCustomerAdd")
    @Expose
    public Boolean vcgSCustomerAdd;
    @SerializedName("vcserviceExchangeRate")
    @Expose
    public Boolean vcserviceExchangeRate;
    @SerializedName("vcserviceSaveDeliveryOrder")
    @Expose
    public Boolean vcserviceSaveDeliveryOrder;
    @SerializedName("vcserviceInvokeSalesQuotation")
    @Expose
    public Boolean vcserviceInvokeSalesQuotation;
    @SerializedName("vcserviceRecievePayment")
    @Expose
    public Boolean vcserviceRecievePayment;
    @SerializedName("vcserviceCustomer")
    @Expose
    public Boolean vcserviceCustomer;
    @SerializedName("vcserviceSearchSerializeItem")
    @Expose
    public Boolean vcserviceSearchSerializeItem;
    @SerializedName("vcserviceReturnDeliveryOrder")
    @Expose
    public Boolean vcserviceReturnDeliveryOrder;
    @SerializedName("vcserviceSaveserviceOrder")
    @Expose
    public Boolean vcserviceSaveserviceOrder;
    @SerializedName("vcserviceDebitNote")
    @Expose
    public Boolean vcserviceDebitNote;
    @SerializedName("vcserviceInvokeSalesOrder")
    @Expose
    public Boolean vcserviceInvokeSalesOrder;
    @SerializedName("vcserviceClearAll")
    @Expose
    public Boolean vcserviceClearAll;
    @SerializedName("vcserviceContactAdd")
    @Expose
    public Boolean vcserviceContactAdd;
    @SerializedName("vcserviceAgentAdd")
    @Expose
    public Boolean vcserviceAgentAdd;
    @SerializedName("vcserviceTndCAdd")
    @Expose
    public Boolean vcserviceTndCAdd;
    @SerializedName("vcserviceBarCode")
    @Expose
    public Boolean vcserviceBarCode;
    @SerializedName("vcserviceSaveProforma")
    @Expose
    public Boolean vcserviceSaveProforma;
    @SerializedName("vcserviceContact")
    @Expose
    public Boolean vcserviceContact;
    @SerializedName("vcserviceInvokeDraft")
    @Expose
    public Boolean vcserviceInvokeDraft;
    @SerializedName("vcserviceSerializeItem")
    @Expose
    public Boolean vcserviceSerializeItem;
    @SerializedName("vcserviceAdvancePayment")
    @Expose
    public Boolean vcserviceAdvancePayment;
    @SerializedName("vcserviceInvokeDeliveryOrder")
    @Expose
    public Boolean vcserviceInvokeDeliveryOrder;
    @SerializedName("vcserviceStateAdd")
    @Expose
    public Boolean vcserviceStateAdd;
    @SerializedName("vcserviceEmployeeAdd")
    @Expose
    public Boolean vcserviceEmployeeAdd;
    @SerializedName("vcserviceClearDraft")
    @Expose
    public Boolean vcserviceClearDraft;
    @SerializedName("vcserviceInvLoc")
    @Expose
    public Boolean vcserviceInvLoc;
    @SerializedName("vcserviceImportItem")
    @Expose
    public Boolean vcserviceImportItem;
    @SerializedName("vcserviceEmployee")
    @Expose
    public Boolean vcserviceEmployee;
    @SerializedName("vcservicedraftInvoice")
    @Expose
    public Boolean vcservicedraftInvoice;
    @SerializedName("vcserviceTaxType")
    @Expose
    public Boolean vcserviceTaxType;
    @SerializedName("vcserviceCurrencyAdd")
    @Expose
    public Boolean vcserviceCurrencyAdd;
    @SerializedName("vcserviceExchangeRateAdd")
    @Expose
    public Boolean vcserviceExchangeRateAdd;
    @SerializedName("vcserviceAdvancePaymentList")
    @Expose
    public Boolean vcserviceAdvancePaymentList;
    @SerializedName("vcserviceSearchBarCode")
    @Expose
    public Boolean vcserviceSearchBarCode;
    @SerializedName("vcserviceSaveSalesQuotation")
    @Expose
    public Boolean vcserviceSaveSalesQuotation;
    @SerializedName("vcservicePrintList")
    @Expose
    public Boolean vcservicePrintList;
    @SerializedName("vcserviceCurrency")
    @Expose
    public Boolean vcserviceCurrency;
    @SerializedName("vcserviceInvokeProforma")
    @Expose
    public Boolean vcserviceInvokeProforma;
    @SerializedName("vcserviceInvLocAdd")
    @Expose
    public Boolean vcserviceInvLocAdd;
    @SerializedName("vcserviceCheckOut")
    @Expose
    public Boolean vcserviceCheckOut;
    @SerializedName("vcserviceShippingRefNo")
    @Expose
    public Boolean vcserviceShippingRefNo;
    @SerializedName("vcserviceReturn")
    @Expose
    public Boolean vcserviceReturn;
    @SerializedName("vcserviceRemoveItem")
    @Expose
    public Boolean vcserviceRemoveItem;
    @SerializedName("vccPConfiguratorPaymentMethod")
    @Expose
    public Boolean vccPConfiguratorPaymentMethod;
    @SerializedName("vccontactCustomer")
    @Expose
    public Boolean vccontactCustomer;
    @SerializedName("vccPConfiguratorConfiguration")
    @Expose
    public Boolean vccPConfiguratorConfiguration;
    @SerializedName("vccontrolPanelAMag")
    @Expose
    public Boolean vccontrolPanelAMag;
    @SerializedName("vccontrolPanelAMbudget")
    @Expose
    public Boolean vccontrolPanelAMbudget;
    @SerializedName("vccontrolPanelCompanySetup")
    @Expose
    public Boolean vccontrolPanelCompanySetup;
    @SerializedName("vccontrolPanelDSfs")
    @Expose
    public Boolean vccontrolPanelDSfs;
    @SerializedName("vccontrolPanelDSlayout")
    @Expose
    public Boolean vccontrolPanelDSlayout;
    @SerializedName("vccontrolPanelDStermsCond")
    @Expose
    public Boolean vccontrolPanelDStermsCond;
    @SerializedName("vccontrolPanelConfigurator")
    @Expose
    public Boolean vccontrolPanelConfigurator;
    @SerializedName("vccontrolPanelDisplaySetup")
    @Expose
    public Boolean vccontrolPanelDisplaySetup;
    @SerializedName("vccontrolPanelLastYearFigure")
    @Expose
    public Boolean vccontrolPanelLastYearFigure;
    @SerializedName("vccontrolPanelOBofBS")
    @Expose
    public Boolean vccontrolPanelOBofBS;
    @SerializedName("vccPConfiguratorProjectTitle")
    @Expose
    public Boolean vccPConfiguratorProjectTitle;
    @SerializedName("vccontrolPanelMonthEnd")
    @Expose
    public Boolean vccontrolPanelMonthEnd;
    @SerializedName("vccontrolPanelOBofINV")
    @Expose
    public Boolean vccontrolPanelOBofINV;
    @SerializedName("vccPCompanySetupFY")
    @Expose
    public Boolean vccPCompanySetupFY;
    @SerializedName("vccontactSupplier")
    @Expose
    public Boolean vccontactSupplier;
    @SerializedName("vccPCompanySetupHSN")
    @Expose
    public Boolean vccPCompanySetupHSN;
    @SerializedName("vccontrolPanelAMcoa")
    @Expose
    public Boolean vccontrolPanelAMcoa;
    @SerializedName("vccPConfiguratorAgent")
    @Expose
    public Boolean vccPConfiguratorAgent;
    @SerializedName("vccPConfiguratorEmailserver")
    @Expose
    public Boolean vccPConfiguratorEmailserver;
    @SerializedName("vccPCompanySetupCI")
    @Expose
    public Boolean vccPCompanySetupCI;
    @SerializedName("vccPConfiguratorEmployee")
    @Expose
    public Boolean vccPConfiguratorEmployee;
    @SerializedName("vccPCompanySetupCA")
    @Expose
    public Boolean vccPCompanySetupCA;
    @SerializedName("vccPConfiguratorCurrency")
    @Expose
    public Boolean vccPConfiguratorCurrency;
    @SerializedName("vccPConfiguratorBank")
    @Expose
    public Boolean vccPConfiguratorBank;
    @SerializedName("vccPConfiguratorExchangeRate")
    @Expose
    public Boolean vccPConfiguratorExchangeRate;
    @SerializedName("vccontactContacts")
    @Expose
    public Boolean vccontactContacts;
    @SerializedName("vcdashboardTotalPayable")
    @Expose
    public Boolean vcdashboardTotalPayable;
    @SerializedName("vccontrolPanelYearEnd")
    @Expose
    public Boolean vccontrolPanelYearEnd;
    @SerializedName("vccontrolPanelUASat")
    @Expose
    public Boolean vccontrolPanelUASat;
    @SerializedName("vcinventoryCategory")
    @Expose
    public Boolean vcinventoryCategory;
    @SerializedName("vcinventoryInventoryLocation")
    @Expose
    public Boolean vcinventoryInventoryLocation;
    @SerializedName("vcinventoryItem")
    @Expose
    public Boolean vcinventoryItem;
    @SerializedName("vccontrolPanelOBofPI")
    @Expose
    public Boolean vccontrolPanelOBofPI;
    @SerializedName("vcdashboardCashInHand")
    @Expose
    public Boolean vcdashboardCashInHand;
    @SerializedName("vcdashboardExtra1")
    @Expose
    public Boolean vcdashboardExtra1;
    @SerializedName("vccontrolPanelOBofSI")
    @Expose
    public Boolean vccontrolPanelOBofSI;
    @SerializedName("vcdashboardTotalReceivable")
    @Expose
    public Boolean vcdashboardTotalReceivable;
    @SerializedName("vcgtJournalEntry")
    @Expose
    public Boolean vcgtJournalEntry;
    @SerializedName("vcdashboardExtra2")
    @Expose
    public Boolean vcdashboardExtra2;
    @SerializedName("vcinventoryBrand")
    @Expose
    public Boolean vcinventoryBrand;
    @SerializedName("vcpurchaseAdvancePaymentList")
    @Expose
    public Boolean vcpurchaseAdvancePaymentList;
    @SerializedName("vcpurchaseAdvancePayment")
    @Expose
    public Boolean vcpurchaseAdvancePayment;
    @SerializedName("vcdashboardDateTime")
    @Expose
    public Boolean vcdashboardDateTime;
    @SerializedName("vcinventoryPurchasePricing")
    @Expose
    public Boolean vcinventoryPurchasePricing;
    @SerializedName("vccontrolPanelUAS")
    @Expose
    public Boolean vccontrolPanelUAS;
    @SerializedName("vcinventorySalesPricing")
    @Expose
    public Boolean vcinventorySalesPricing;
    @SerializedName("vccontrolPanelUASuas")
    @Expose
    public Boolean vccontrolPanelUASuas;
    @SerializedName("vcdashboardCashInBank")
    @Expose
    public Boolean vcdashboardCashInBank;
    @SerializedName("vcinventorySalesDiscountConfig")
    @Expose
    public Boolean vcinventorySalesDiscountConfig;
    @SerializedName("vccPConfiguratorVersionControl")
    @Expose
    public Boolean vccPConfiguratorVersionControl;
    @SerializedName("vcuserAccessRightsDTO")
    @Expose
    public VcuserAccessRightsDTO vcuserAccessRightsDTO;
    @SerializedName("vccPConfiguratorShippingMethod")
    @Expose
    public Boolean vccPConfiguratorShippingMethod;
    @SerializedName("vcreportAnalysisAccountBalance")
    @Expose
    public Boolean vcreportAnalysisAccountBalance;
    @SerializedName("vcpurContact")
    @Expose
    public Boolean vcpurContact;
    @SerializedName("vcsalesSMAdd")
    @Expose
    public Boolean vcsalesSMAdd;
    @SerializedName("vcpurTaxType")
    @Expose
    public Boolean vcpurTaxType;
    @SerializedName("vcsalesRefNo")
    @Expose
    public Boolean vcsalesRefNo;
    @SerializedName("vcpurBarCode")
    @Expose
    public Boolean vcpurBarCode;
    @SerializedName("vcpurState")
    @Expose
    public Boolean vcpurState;
    @SerializedName("vcsalesInvLoc")
    @Expose
    public Boolean vcsalesInvLoc;
    @SerializedName("vcsalesMore")
    @Expose
    public Boolean vcsalesMore;
    @SerializedName("vcpurStateAdd")
    @Expose
    public Boolean vcpurStateAdd;
    @SerializedName("vcsalesTandC")
    @Expose
    public Boolean vcsalesTandC;
    @SerializedName("vcsalesCCAdd")
    @Expose
    public Boolean vcsalesCCAdd;
    @SerializedName("vcsalesSM")
    @Expose
    public Boolean vcsalesSM;
    @SerializedName("vcsalesCC")
    @Expose
    public Boolean vcsalesCC;
    @SerializedName("vcpurMore")
    @Expose
    public Boolean vcpurMore;
    @SerializedName("vcsalesState")
    @Expose
    public Boolean vcsalesState;
    @SerializedName("vcsalesAgent")
    @Expose
    public Boolean vcsalesAgent;
    @SerializedName("vcpurInvLoc")
    @Expose
    public Boolean vcpurInvLoc;
    @SerializedName("vctaxTax")
    @Expose
    public Boolean vctaxTax;
    @SerializedName("vcgstMalGSTSR")
    @Expose
    public Boolean vcgstMalGSTSR;
    @SerializedName("vcpurTandC")
    @Expose
    public Boolean vcpurTandC;
    @SerializedName("vctaxTaxtype")
    @Expose
    public Boolean vctaxTaxtype;
    @SerializedName("vcpurCurrency")
    @Expose
    public Boolean vcpurCurrency;
    @SerializedName("vcpurSMAdd")
    @Expose
    public Boolean vcpurSMAdd;
    @SerializedName("vcpurCCAdd")
    @Expose
    public Boolean vcpurCCAdd;
    @SerializedName("vcpurEmployee")
    @Expose
    public Boolean vcpurEmployee;
    @SerializedName("vcpurRefNo")
    @Expose
    public Boolean vcpurRefNo;
    @SerializedName("vcpurAgent")
    @Expose
    public Boolean vcpurAgent;
    @SerializedName("vcpurTndCAdd")
    @Expose
    public Boolean vcpurTndCAdd;
    @SerializedName("vcpurCC")
    @Expose
    public Boolean vcpurCC;
    @SerializedName("vccrm")
    @Expose
    public Boolean vccrm;
    @SerializedName("vcpurAgentAdd")
    @Expose
    public Boolean vcpurAgentAdd;
    @SerializedName("vcpurSM")
    @Expose
    public Boolean vcpurSM;
    @SerializedName("vcevent")
    @Expose
    public Boolean vcevent;
    @SerializedName("vcleads")
    @Expose
    public Boolean vcleads;
    @SerializedName("vccontrolPanelAccountMaintenance")
    @Expose
    public Boolean vccontrolPanelAccountMaintenance;
    @SerializedName("vcinventoryInventoryMovementType")
    @Expose
    public Boolean vcinventoryInventoryMovementType;
    @SerializedName("vcpurchaseSavePurchaseQuotation")
    @Expose
    public Boolean vcpurchaseSavePurchaseQuotation;
    @SerializedName("vcpurchaseInvokePurchaseQuotation")
    @Expose
    public Boolean vcpurchaseInvokePurchaseQuotation;
    @SerializedName("vccPConfiguratorLabelPrintConfig")
    @Expose
    public Boolean vccPConfiguratorLabelPrintConfig;
    @SerializedName("vcmanufacturingAdvItemCountAdjust")
    @Expose
    public Boolean vcmanufacturingAdvItemCountAdjust;

    public Long getUserAccountSetupID() {
        return userAccountSetupID;
    }

    public void setUserAccountSetupID(Long userAccountSetupID) {
        this.userAccountSetupID = userAccountSetupID;
    }

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

    public Long getProjectModuleId() {
        return projectModuleId;
    }

    public void setProjectModuleId(Long projectModuleId) {
        this.projectModuleId = projectModuleId;
    }

    public String getProjectModuleName() {
        return projectModuleName;
    }

    public void setProjectModuleName(String projectModuleName) {
        this.projectModuleName = projectModuleName;
    }

    public String getProjectModuleDescription() {
        return projectModuleDescription;
    }

    public void setProjectModuleDescription(String projectModuleDescription) {
        this.projectModuleDescription = projectModuleDescription;
    }

    public String getProjectModuleStatus() {
        return projectModuleStatus;
    }

    public void setProjectModuleStatus(String projectModuleStatus) {
        this.projectModuleStatus = projectModuleStatus;
    }

    public VCuserAccessRightsDTO getvCuserAccessRightsDTO() {
        return vCuserAccessRightsDTO;
    }

    public void setvCuserAccessRightsDTO(VCuserAccessRightsDTO vCuserAccessRightsDTO) {
        this.vCuserAccessRightsDTO = vCuserAccessRightsDTO;
    }

    public Boolean getVcgPRefNo() {
        return vcgPRefNo;
    }

    public void setVcgPRefNo(Boolean vcgPRefNo) {
        this.vcgPRefNo = vcgPRefNo;
    }

    public Boolean getVcgPRemoveItem() {
        return vcgPRemoveItem;
    }

    public void setVcgPRemoveItem(Boolean vcgPRemoveItem) {
        this.vcgPRemoveItem = vcgPRemoveItem;
    }

    public Boolean getVcgPAgentAdd() {
        return vcgPAgentAdd;
    }

    public void setVcgPAgentAdd(Boolean vcgPAgentAdd) {
        this.vcgPAgentAdd = vcgPAgentAdd;
    }

    public Boolean getVcgPClearDraft() {
        return vcgPClearDraft;
    }

    public void setVcgPClearDraft(Boolean vcgPClearDraft) {
        this.vcgPClearDraft = vcgPClearDraft;
    }

    public Boolean getVcgtExpense() {
        return vcgtExpense;
    }

    public void setVcgtExpense(Boolean vcgtExpense) {
        this.vcgtExpense = vcgtExpense;
    }

    public Boolean getVcgPSavesDraft() {
        return vcgPSavesDraft;
    }

    public void setVcgPSavesDraft(Boolean vcgPSavesDraft) {
        this.vcgPSavesDraft = vcgPSavesDraft;
    }

    public Boolean getVcreportSaleDA() {
        return vcreportSaleDA;
    }

    public void setVcreportSaleDA(Boolean vcreportSaleDA) {
        this.vcreportSaleDA = vcreportSaleDA;
    }

    public Boolean getVctaxGST() {
        return vctaxGST;
    }

    public void setVctaxGST(Boolean vctaxGST) {
        this.vctaxGST = vctaxGST;
    }

    public Boolean getVcgPSM() {
        return vcgPSM;
    }

    public void setVcgPSM(Boolean vcgPSM) {
        this.vcgPSM = vcgPSM;
    }

    public Boolean getVcgPSMAdd() {
        return vcgPSMAdd;
    }

    public void setVcgPSMAdd(Boolean vcgPSMAdd) {
        this.vcgPSMAdd = vcgPSMAdd;
    }

    public Boolean getVcgPState() {
        return vcgPState;
    }

    public void setVcgPState(Boolean vcgPState) {
        this.vcgPState = vcgPState;
    }

    public Boolean getVcgPStateAdd() {
        return vcgPStateAdd;
    }

    public void setVcgPStateAdd(Boolean vcgPStateAdd) {
        this.vcgPStateAdd = vcgPStateAdd;
    }

    public Boolean getVcgPTandC() {
        return vcgPTandC;
    }

    public void setVcgPTandC(Boolean vcgPTandC) {
        this.vcgPTandC = vcgPTandC;
    }

    public Boolean getVcgPCurrency() {
        return vcgPCurrency;
    }

    public void setVcgPCurrency(Boolean vcgPCurrency) {
        this.vcgPCurrency = vcgPCurrency;
    }

    public Boolean getVccontact() {
        return vccontact;
    }

    public void setVccontact(Boolean vccontact) {
        this.vccontact = vccontact;
    }

    public Boolean getVcgPCustomer() {
        return vcgPCustomer;
    }

    public void setVcgPCustomer(Boolean vcgPCustomer) {
        this.vcgPCustomer = vcgPCustomer;
    }

    public Boolean getVcgPImportItem() {
        return vcgPImportItem;
    }

    public void setVcgPImportItem(Boolean vcgPImportItem) {
        this.vcgPImportItem = vcgPImportItem;
    }

    public Boolean getVcgPItemAdd() {
        return vcgPItemAdd;
    }

    public void setVcgPItemAdd(Boolean vcgPItemAdd) {
        this.vcgPItemAdd = vcgPItemAdd;
    }

    public Boolean getVcgPTaxType() {
        return vcgPTaxType;
    }

    public void setVcgPTaxType(Boolean vcgPTaxType) {
        this.vcgPTaxType = vcgPTaxType;
    }

    public Boolean getVcgPTndCAdd() {
        return vcgPTndCAdd;
    }

    public void setVcgPTndCAdd(Boolean vcgPTndCAdd) {
        this.vcgPTndCAdd = vcgPTndCAdd;
    }

    public Boolean getVcpurchase() {
        return vcpurchase;
    }

    public void setVcpurchase(Boolean vcpurchase) {
        this.vcpurchase = vcpurchase;
    }

    public Boolean getVcsalesReturn() {
        return vcsalesReturn;
    }

    public void setVcsalesReturn(Boolean vcsalesReturn) {
        this.vcsalesReturn = vcsalesReturn;
    }

    public Boolean getVcgPAgent() {
        return vcgPAgent;
    }

    public void setVcgPAgent(Boolean vcgPAgent) {
        this.vcgPAgent = vcgPAgent;
    }

    public Boolean getVcsalesItemAdd() {
        return vcsalesItemAdd;
    }

    public void setVcsalesItemAdd(Boolean vcsalesItemAdd) {
        this.vcsalesItemAdd = vcsalesItemAdd;
    }

    public Boolean getVcinventoryUOM() {
        return vcinventoryUOM;
    }

    public void setVcinventoryUOM(Boolean vcinventoryUOM) {
        this.vcinventoryUOM = vcinventoryUOM;
    }

    public Boolean getVcreportSalePR() {
        return vcreportSalePR;
    }

    public void setVcreportSalePR(Boolean vcreportSalePR) {
        this.vcreportSalePR = vcreportSalePR;
    }

    public Boolean getVctax() {
        return vctax;
    }

    public void setVctax(Boolean vctax) {
        this.vctax = vctax;
    }

    public Boolean getVcreportSaleCA() {
        return vcreportSaleCA;
    }

    public void setVcreportSaleCA(Boolean vcreportSaleCA) {
        this.vcreportSaleCA = vcreportSaleCA;
    }

    public Boolean getVcreportSaleCL() {
        return vcreportSaleCL;
    }

    public void setVcreportSaleCL(Boolean vcreportSaleCL) {
        this.vcreportSaleCL = vcreportSaleCL;
    }

    public Boolean getVcgt() {
        return vcgt;
    }

    public void setVcgt(Boolean vcgt) {
        this.vcgt = vcgt;
    }

    public Boolean getVcgPClearAll() {
        return vcgPClearAll;
    }

    public void setVcgPClearAll(Boolean vcgPClearAll) {
        this.vcgPClearAll = vcgPClearAll;
    }

    public Boolean getVcreportSalePL() {
        return vcreportSalePL;
    }

    public void setVcreportSalePL(Boolean vcreportSalePL) {
        this.vcreportSalePL = vcreportSalePL;
    }

    public Boolean getVcreportSaleAL() {
        return vcreportSaleAL;
    }

    public void setVcreportSaleAL(Boolean vcreportSaleAL) {
        this.vcreportSaleAL = vcreportSaleAL;
    }

    public Boolean getVcsales() {
        return vcsales;
    }

    public void setVcsales(Boolean vcsales) {
        this.vcsales = vcsales;
    }

    public Boolean getVcgtReciepts() {
        return vcgtReciepts;
    }

    public void setVcgtReciepts(Boolean vcgtReciepts) {
        this.vcgtReciepts = vcgtReciepts;
    }

    public Boolean getVcreportSaleCP() {
        return vcreportSaleCP;
    }

    public void setVcreportSaleCP(Boolean vcreportSaleCP) {
        this.vcreportSaleCP = vcreportSaleCP;
    }

    public Boolean getVcgPBarCode() {
        return vcgPBarCode;
    }

    public void setVcgPBarCode(Boolean vcgPBarCode) {
        this.vcgPBarCode = vcgPBarCode;
    }

    public Boolean getVcgPCheckOut() {
        return vcgPCheckOut;
    }

    public void setVcgPCheckOut(Boolean vcgPCheckOut) {
        this.vcgPCheckOut = vcgPCheckOut;
    }

    public Boolean getVccontrolPanel() {
        return vccontrolPanel;
    }

    public void setVccontrolPanel(Boolean vccontrolPanel) {
        this.vccontrolPanel = vccontrolPanel;
    }

    public Boolean getVcreportSaleSA() {
        return vcreportSaleSA;
    }

    public void setVcreportSaleSA(Boolean vcreportSaleSA) {
        this.vcreportSaleSA = vcreportSaleSA;
    }

    public Boolean getVcgPCC() {
        return vcgPCC;
    }

    public void setVcgPCC(Boolean vcgPCC) {
        this.vcgPCC = vcgPCC;
    }

    public Boolean getVcgPContactAdd() {
        return vcgPContactAdd;
    }

    public void setVcgPContactAdd(Boolean vcgPContactAdd) {
        this.vcgPContactAdd = vcgPContactAdd;
    }

    public Boolean getVcreportSaleML() {
        return vcreportSaleML;
    }

    public void setVcreportSaleML(Boolean vcreportSaleML) {
        this.vcreportSaleML = vcreportSaleML;
    }

    public Boolean getVcgPDebitNote() {
        return vcgPDebitNote;
    }

    public void setVcgPDebitNote(Boolean vcgPDebitNote) {
        this.vcgPDebitNote = vcgPDebitNote;
    }

    public Boolean getVcgPEmployee() {
        return vcgPEmployee;
    }

    public void setVcgPEmployee(Boolean vcgPEmployee) {
        this.vcgPEmployee = vcgPEmployee;
    }

    public Boolean getVcgPInvLocAdd() {
        return vcgPInvLocAdd;
    }

    public void setVcgPInvLocAdd(Boolean vcgPInvLocAdd) {
        this.vcgPInvLocAdd = vcgPInvLocAdd;
    }

    public Boolean getVcreport() {
        return vcreport;
    }

    public void setVcreport(Boolean vcreport) {
        this.vcreport = vcreport;
    }

    public Boolean getVcreportSale() {
        return vcreportSale;
    }

    public void setVcreportSale(Boolean vcreportSale) {
        this.vcreportSale = vcreportSale;
    }

    public Boolean getVcreportSaleCS() {
        return vcreportSaleCS;
    }

    public void setVcreportSaleCS(Boolean vcreportSaleCS) {
        this.vcreportSaleCS = vcreportSaleCS;
    }

    public Boolean getVcgPReturn() {
        return vcgPReturn;
    }

    public void setVcgPReturn(Boolean vcgPReturn) {
        this.vcgPReturn = vcgPReturn;
    }

    public Boolean getVcgPMore() {
        return vcgPMore;
    }

    public void setVcgPMore(Boolean vcgPMore) {
        this.vcgPMore = vcgPMore;
    }

    public Boolean getVcgPSaveOrder() {
        return vcgPSaveOrder;
    }

    public void setVcgPSaveOrder(Boolean vcgPSaveOrder) {
        this.vcgPSaveOrder = vcgPSaveOrder;
    }

    public Boolean getVcdashboard() {
        return vcdashboard;
    }

    public void setVcdashboard(Boolean vcdashboard) {
        this.vcdashboard = vcdashboard;
    }

    public Boolean getVcgPCCAdd() {
        return vcgPCCAdd;
    }

    public void setVcgPCCAdd(Boolean vcgPCCAdd) {
        this.vcgPCCAdd = vcgPCCAdd;
    }

    public Boolean getVcinventory() {
        return vcinventory;
    }

    public void setVcinventory(Boolean vcinventory) {
        this.vcinventory = vcinventory;
    }

    public Boolean getVcgPInvLoc() {
        return vcgPInvLoc;
    }

    public void setVcgPInvLoc(Boolean vcgPInvLoc) {
        this.vcgPInvLoc = vcgPInvLoc;
    }

    public Boolean getVcgPContact() {
        return vcgPContact;
    }

    public void setVcgPContact(Boolean vcgPContact) {
        this.vcgPContact = vcgPContact;
    }

    public Boolean getVcgPPrintList() {
        return vcgPPrintList;
    }

    public void setVcgPPrintList(Boolean vcgPPrintList) {
        this.vcgPPrintList = vcgPPrintList;
    }

    public Boolean getVcpCContact() {
        return vcpCContact;
    }

    public void setVcpCContact(Boolean vcpCContact) {
        this.vcpCContact = vcpCContact;
    }

    public Boolean getVcpCContactAdd() {
        return vcpCContactAdd;
    }

    public void setVcpCContactAdd(Boolean vcpCContactAdd) {
        this.vcpCContactAdd = vcpCContactAdd;
    }

    public Boolean getVcpCAgentAdd() {
        return vcpCAgentAdd;
    }

    public void setVcpCAgentAdd(Boolean vcpCAgentAdd) {
        this.vcpCAgentAdd = vcpCAgentAdd;
    }

    public Boolean getVcpCAgent() {
        return vcpCAgent;
    }

    public void setVcpCAgent(Boolean vcpCAgent) {
        this.vcpCAgent = vcpCAgent;
    }

    public Boolean getVcpCPrintList() {
        return vcpCPrintList;
    }

    public void setVcpCPrintList(Boolean vcpCPrintList) {
        this.vcpCPrintList = vcpCPrintList;
    }

    public Boolean getVcpCReturn() {
        return vcpCReturn;
    }

    public void setVcpCReturn(Boolean vcpCReturn) {
        this.vcpCReturn = vcpCReturn;
    }

    public Boolean getVcpCImportItem() {
        return vcpCImportItem;
    }

    public void setVcpCImportItem(Boolean vcpCImportItem) {
        this.vcpCImportItem = vcpCImportItem;
    }

    public Boolean getVcpCCurrency() {
        return vcpCCurrency;
    }

    public void setVcpCCurrency(Boolean vcpCCurrency) {
        this.vcpCCurrency = vcpCCurrency;
    }

    public Boolean getVcmSAgent() {
        return vcmSAgent;
    }

    public void setVcmSAgent(Boolean vcmSAgent) {
        this.vcmSAgent = vcmSAgent;
    }

    public Boolean getVcmSDebitNote() {
        return vcmSDebitNote;
    }

    public void setVcmSDebitNote(Boolean vcmSDebitNote) {
        this.vcmSDebitNote = vcmSDebitNote;
    }

    public Boolean getVcpCEmployee() {
        return vcpCEmployee;
    }

    public void setVcpCEmployee(Boolean vcpCEmployee) {
        this.vcpCEmployee = vcpCEmployee;
    }

    public Boolean getVcmSState() {
        return vcmSState;
    }

    public void setVcmSState(Boolean vcmSState) {
        this.vcmSState = vcmSState;
    }

    public Boolean getVcpCTndCAdd() {
        return vcpCTndCAdd;
    }

    public void setVcpCTndCAdd(Boolean vcpCTndCAdd) {
        this.vcpCTndCAdd = vcpCTndCAdd;
    }

    public Boolean getVcpCTandC() {
        return vcpCTandC;
    }

    public void setVcpCTandC(Boolean vcpCTandC) {
        this.vcpCTandC = vcpCTandC;
    }

    public Boolean getVcpCCCAdd() {
        return vcpCCCAdd;
    }

    public void setVcpCCCAdd(Boolean vcpCCCAdd) {
        this.vcpCCCAdd = vcpCCCAdd;
    }

    public Boolean getVcpCCC() {
        return vcpCCC;
    }

    public void setVcpCCC(Boolean vcpCCC) {
        this.vcpCCC = vcpCCC;
    }

    public Boolean getVcmSItemAdd() {
        return vcmSItemAdd;
    }

    public void setVcmSItemAdd(Boolean vcmSItemAdd) {
        this.vcmSItemAdd = vcmSItemAdd;
    }

    public Boolean getVcmSContactAdd() {
        return vcmSContactAdd;
    }

    public void setVcmSContactAdd(Boolean vcmSContactAdd) {
        this.vcmSContactAdd = vcmSContactAdd;
    }

    public Boolean getVcpCSMAdd() {
        return vcpCSMAdd;
    }

    public void setVcpCSMAdd(Boolean vcpCSMAdd) {
        this.vcpCSMAdd = vcpCSMAdd;
    }

    public Boolean getVcpCSM() {
        return vcpCSM;
    }

    public void setVcpCSM(Boolean vcpCSM) {
        this.vcpCSM = vcpCSM;
    }

    public Boolean getVcpCRefNo() {
        return vcpCRefNo;
    }

    public void setVcpCRefNo(Boolean vcpCRefNo) {
        this.vcpCRefNo = vcpCRefNo;
    }

    public Boolean getVcmSInvLoc() {
        return vcmSInvLoc;
    }

    public void setVcmSInvLoc(Boolean vcmSInvLoc) {
        this.vcmSInvLoc = vcmSInvLoc;
    }

    public Boolean getVcmSStateAdd() {
        return vcmSStateAdd;
    }

    public void setVcmSStateAdd(Boolean vcmSStateAdd) {
        this.vcmSStateAdd = vcmSStateAdd;
    }

    public Boolean getVcpCItemAdd() {
        return vcpCItemAdd;
    }

    public void setVcpCItemAdd(Boolean vcpCItemAdd) {
        this.vcpCItemAdd = vcpCItemAdd;
    }

    public Boolean getVcpCClearDraft() {
        return vcpCClearDraft;
    }

    public void setVcpCClearDraft(Boolean vcpCClearDraft) {
        this.vcpCClearDraft = vcpCClearDraft;
    }

    public Boolean getVcmSCheckOut() {
        return vcmSCheckOut;
    }

    public void setVcmSCheckOut(Boolean vcmSCheckOut) {
        this.vcmSCheckOut = vcmSCheckOut;
    }

    public Boolean getVcmSTandC() {
        return vcmSTandC;
    }

    public void setVcmSTandC(Boolean vcmSTandC) {
        this.vcmSTandC = vcmSTandC;
    }

    public Boolean getVcmSCCAdd() {
        return vcmSCCAdd;
    }

    public void setVcmSCCAdd(Boolean vcmSCCAdd) {
        this.vcmSCCAdd = vcmSCCAdd;
    }

    public Boolean getVcmSContact() {
        return vcmSContact;
    }

    public void setVcmSContact(Boolean vcmSContact) {
        this.vcmSContact = vcmSContact;
    }

    public Boolean getVcmSReturn() {
        return vcmSReturn;
    }

    public void setVcmSReturn(Boolean vcmSReturn) {
        this.vcmSReturn = vcmSReturn;
    }

    public Boolean getVcmSSM() {
        return vcmSSM;
    }

    public void setVcmSSM(Boolean vcmSSM) {
        this.vcmSSM = vcmSSM;
    }

    public Boolean getVcmSImportItem() {
        return vcmSImportItem;
    }

    public void setVcmSImportItem(Boolean vcmSImportItem) {
        this.vcmSImportItem = vcmSImportItem;
    }

    public Boolean getVcpCSaveOrder() {
        return vcpCSaveOrder;
    }

    public void setVcpCSaveOrder(Boolean vcpCSaveOrder) {
        this.vcpCSaveOrder = vcpCSaveOrder;
    }

    public Boolean getVcpCCheckOut() {
        return vcpCCheckOut;
    }

    public void setVcpCCheckOut(Boolean vcpCCheckOut) {
        this.vcpCCheckOut = vcpCCheckOut;
    }

    public Boolean getVcmSTaxType() {
        return vcmSTaxType;
    }

    public void setVcmSTaxType(Boolean vcmSTaxType) {
        this.vcmSTaxType = vcmSTaxType;
    }

    public Boolean getVcpCCustomer() {
        return vcpCCustomer;
    }

    public void setVcpCCustomer(Boolean vcpCCustomer) {
        this.vcpCCustomer = vcpCCustomer;
    }

    public Boolean getVcpCRemoveItem() {
        return vcpCRemoveItem;
    }

    public void setVcpCRemoveItem(Boolean vcpCRemoveItem) {
        this.vcpCRemoveItem = vcpCRemoveItem;
    }

    public Boolean getVcmSClearAll() {
        return vcmSClearAll;
    }

    public void setVcmSClearAll(Boolean vcmSClearAll) {
        this.vcmSClearAll = vcmSClearAll;
    }

    public Boolean getVcmSInvLocAdd() {
        return vcmSInvLocAdd;
    }

    public void setVcmSInvLocAdd(Boolean vcmSInvLocAdd) {
        this.vcmSInvLocAdd = vcmSInvLocAdd;
    }

    public Boolean getVcmobileSales() {
        return vcmobileSales;
    }

    public void setVcmobileSales(Boolean vcmobileSales) {
        this.vcmobileSales = vcmobileSales;
    }

    public Boolean getVcmSPrintList() {
        return vcmSPrintList;
    }

    public void setVcmSPrintList(Boolean vcmSPrintList) {
        this.vcmSPrintList = vcmSPrintList;
    }

    public Boolean getVcmSSavesDraft() {
        return vcmSSavesDraft;
    }

    public void setVcmSSavesDraft(Boolean vcmSSavesDraft) {
        this.vcmSSavesDraft = vcmSSavesDraft;
    }

    public Boolean getVcmSCurrency() {
        return vcmSCurrency;
    }

    public void setVcmSCurrency(Boolean vcmSCurrency) {
        this.vcmSCurrency = vcmSCurrency;
    }

    public Boolean getVcmSSMAdd() {
        return vcmSSMAdd;
    }

    public void setVcmSSMAdd(Boolean vcmSSMAdd) {
        this.vcmSSMAdd = vcmSSMAdd;
    }

    public Boolean getVcmSAgentAdd() {
        return vcmSAgentAdd;
    }

    public void setVcmSAgentAdd(Boolean vcmSAgentAdd) {
        this.vcmSAgentAdd = vcmSAgentAdd;
    }

    public Boolean getVcpCSavesDraft() {
        return vcpCSavesDraft;
    }

    public void setVcpCSavesDraft(Boolean vcpCSavesDraft) {
        this.vcpCSavesDraft = vcpCSavesDraft;
    }

    public Boolean getVcmSMore() {
        return vcmSMore;
    }

    public void setVcmSMore(Boolean vcmSMore) {
        this.vcmSMore = vcmSMore;
    }

    public Boolean getVcpCTaxType() {
        return vcpCTaxType;
    }

    public void setVcpCTaxType(Boolean vcpCTaxType) {
        this.vcpCTaxType = vcpCTaxType;
    }

    public Boolean getVcpCInvLocAdd() {
        return vcpCInvLocAdd;
    }

    public void setVcpCInvLocAdd(Boolean vcpCInvLocAdd) {
        this.vcpCInvLocAdd = vcpCInvLocAdd;
    }

    public Boolean getVcmSClearDraft() {
        return vcmSClearDraft;
    }

    public void setVcmSClearDraft(Boolean vcmSClearDraft) {
        this.vcmSClearDraft = vcmSClearDraft;
    }

    public Boolean getVcmSCustomer() {
        return vcmSCustomer;
    }

    public void setVcmSCustomer(Boolean vcmSCustomer) {
        this.vcmSCustomer = vcmSCustomer;
    }

    public Boolean getVcmSCC() {
        return vcmSCC;
    }

    public void setVcmSCC(Boolean vcmSCC) {
        this.vcmSCC = vcmSCC;
    }

    public Boolean getVcmSRefNo() {
        return vcmSRefNo;
    }

    public void setVcmSRefNo(Boolean vcmSRefNo) {
        this.vcmSRefNo = vcmSRefNo;
    }

    public Boolean getVcmSRemoveItem() {
        return vcmSRemoveItem;
    }

    public void setVcmSRemoveItem(Boolean vcmSRemoveItem) {
        this.vcmSRemoveItem = vcmSRemoveItem;
    }

    public Boolean getVcpCBarCode() {
        return vcpCBarCode;
    }

    public void setVcpCBarCode(Boolean vcpCBarCode) {
        this.vcpCBarCode = vcpCBarCode;
    }

    public Boolean getVcmSEmployee() {
        return vcmSEmployee;
    }

    public void setVcmSEmployee(Boolean vcmSEmployee) {
        this.vcmSEmployee = vcmSEmployee;
    }

    public Boolean getVcpCClearAll() {
        return vcpCClearAll;
    }

    public void setVcpCClearAll(Boolean vcpCClearAll) {
        this.vcpCClearAll = vcpCClearAll;
    }

    public Boolean getVcmSBarCode() {
        return vcmSBarCode;
    }

    public void setVcmSBarCode(Boolean vcmSBarCode) {
        this.vcmSBarCode = vcmSBarCode;
    }

    public Boolean getVcmSSaveOrder() {
        return vcmSSaveOrder;
    }

    public void setVcmSSaveOrder(Boolean vcmSSaveOrder) {
        this.vcmSSaveOrder = vcmSSaveOrder;
    }

    public Boolean getVcmSTndCAdd() {
        return vcmSTndCAdd;
    }

    public void setVcmSTndCAdd(Boolean vcmSTndCAdd) {
        this.vcmSTndCAdd = vcmSTndCAdd;
    }

    public Boolean getVcpCInvLoc() {
        return vcpCInvLoc;
    }

    public void setVcpCInvLoc(Boolean vcpCInvLoc) {
        this.vcpCInvLoc = vcpCInvLoc;
    }

    public Boolean getVcpCStateAdd() {
        return vcpCStateAdd;
    }

    public void setVcpCStateAdd(Boolean vcpCStateAdd) {
        this.vcpCStateAdd = vcpCStateAdd;
    }

    public Boolean getVcpCMore() {
        return vcpCMore;
    }

    public void setVcpCMore(Boolean vcpCMore) {
        this.vcpCMore = vcpCMore;
    }

    public Boolean getVcpCState() {
        return vcpCState;
    }

    public void setVcpCState(Boolean vcpCState) {
        this.vcpCState = vcpCState;
    }

    public Boolean getVcgSReturn() {
        return vcgSReturn;
    }

    public void setVcgSReturn(Boolean vcgSReturn) {
        this.vcgSReturn = vcgSReturn;
    }

    public Boolean getVcpSStateAdd() {
        return vcpSStateAdd;
    }

    public void setVcpSStateAdd(Boolean vcpSStateAdd) {
        this.vcpSStateAdd = vcpSStateAdd;
    }

    public Boolean getVcgSEmpty1() {
        return vcgSEmpty1;
    }

    public void setVcgSEmpty1(Boolean vcgSEmpty1) {
        this.vcgSEmpty1 = vcgSEmpty1;
    }

    public Boolean getVcgSEmpty2() {
        return vcgSEmpty2;
    }

    public void setVcgSEmpty2(Boolean vcgSEmpty2) {
        this.vcgSEmpty2 = vcgSEmpty2;
    }

    public Boolean getVcpSAgent() {
        return vcpSAgent;
    }

    public void setVcpSAgent(Boolean vcpSAgent) {
        this.vcpSAgent = vcpSAgent;
    }

    public Boolean getVcrestaurant() {
        return vcrestaurant;
    }

    public void setVcrestaurant(Boolean vcrestaurant) {
        this.vcrestaurant = vcrestaurant;
    }

    public Boolean getVcgSCheckOut() {
        return vcgSCheckOut;
    }

    public void setVcgSCheckOut(Boolean vcgSCheckOut) {
        this.vcgSCheckOut = vcgSCheckOut;
    }

    public Boolean getVcpSRefNo() {
        return vcpSRefNo;
    }

    public void setVcpSRefNo(Boolean vcpSRefNo) {
        this.vcpSRefNo = vcpSRefNo;
    }

    public Boolean getVcgSClearAll() {
        return vcgSClearAll;
    }

    public void setVcgSClearAll(Boolean vcgSClearAll) {
        this.vcgSClearAll = vcgSClearAll;
    }

    public Boolean getVcgSItemLength() {
        return vcgSItemLength;
    }

    public void setVcgSItemLength(Boolean vcgSItemLength) {
        this.vcgSItemLength = vcgSItemLength;
    }

    public Boolean getVcgSPrintList() {
        return vcgSPrintList;
    }

    public void setVcgSPrintList(Boolean vcgSPrintList) {
        this.vcgSPrintList = vcgSPrintList;
    }

    public Boolean getVcpSInvLocAdd() {
        return vcpSInvLocAdd;
    }

    public void setVcpSInvLocAdd(Boolean vcpSInvLocAdd) {
        this.vcpSInvLocAdd = vcpSInvLocAdd;
    }

    public Boolean getVcgSBarCode() {
        return vcgSBarCode;
    }

    public void setVcgSBarCode(Boolean vcgSBarCode) {
        this.vcgSBarCode = vcgSBarCode;
    }

    public Boolean getVcrestBarcode() {
        return vcrestBarcode;
    }

    public void setVcrestBarcode(Boolean vcrestBarcode) {
        this.vcrestBarcode = vcrestBarcode;
    }

    public Boolean getVcrestTax() {
        return vcrestTax;
    }

    public void setVcrestTax(Boolean vcrestTax) {
        this.vcrestTax = vcrestTax;
    }

    public Boolean getVcpSCheckOut() {
        return vcpSCheckOut;
    }

    public void setVcpSCheckOut(Boolean vcpSCheckOut) {
        this.vcpSCheckOut = vcpSCheckOut;
    }

    public Boolean getVcrestItemAdd() {
        return vcrestItemAdd;
    }

    public void setVcrestItemAdd(Boolean vcrestItemAdd) {
        this.vcrestItemAdd = vcrestItemAdd;
    }

    public Boolean getVcrestTotalAmt() {
        return vcrestTotalAmt;
    }

    public void setVcrestTotalAmt(Boolean vcrestTotalAmt) {
        this.vcrestTotalAmt = vcrestTotalAmt;
    }

    public Boolean getVcrestClearAll() {
        return vcrestClearAll;
    }

    public void setVcrestClearAll(Boolean vcrestClearAll) {
        this.vcrestClearAll = vcrestClearAll;
    }

    public Boolean getVcrestPayment() {
        return vcrestPayment;
    }

    public void setVcrestPayment(Boolean vcrestPayment) {
        this.vcrestPayment = vcrestPayment;
    }

    public Boolean getVcrestPrint() {
        return vcrestPrint;
    }

    public void setVcrestPrint(Boolean vcrestPrint) {
        this.vcrestPrint = vcrestPrint;
    }

    public Boolean getVcservice() {
        return vcservice;
    }

    public void setVcservice(Boolean vcservice) {
        this.vcservice = vcservice;
    }

    public Boolean getVcserviceState() {
        return vcserviceState;
    }

    public void setVcserviceState(Boolean vcserviceState) {
        this.vcserviceState = vcserviceState;
    }

    public Boolean getVcserviceMore() {
        return vcserviceMore;
    }

    public void setVcserviceMore(Boolean vcserviceMore) {
        this.vcserviceMore = vcserviceMore;
    }

    public Boolean getVcserviceAgent() {
        return vcserviceAgent;
    }

    public void setVcserviceAgent(Boolean vcserviceAgent) {
        this.vcserviceAgent = vcserviceAgent;
    }

    public Boolean getVcserviceTandC() {
        return vcserviceTandC;
    }

    public void setVcserviceTandC(Boolean vcserviceTandC) {
        this.vcserviceTandC = vcserviceTandC;
    }

    public Boolean getVcpCDebitNote() {
        return vcpCDebitNote;
    }

    public void setVcpCDebitNote(Boolean vcpCDebitNote) {
        this.vcpCDebitNote = vcpCDebitNote;
    }

    public Boolean getVcpSInvLoc() {
        return vcpSInvLoc;
    }

    public void setVcpSInvLoc(Boolean vcpSInvLoc) {
        this.vcpSInvLoc = vcpSInvLoc;
    }

    public Boolean getVcpSMore() {
        return vcpSMore;
    }

    public void setVcpSMore(Boolean vcpSMore) {
        this.vcpSMore = vcpSMore;
    }

    public Boolean getVcpSPrintList() {
        return vcpSPrintList;
    }

    public void setVcpSPrintList(Boolean vcpSPrintList) {
        this.vcpSPrintList = vcpSPrintList;
    }

    public Boolean getVcpSTandC() {
        return vcpSTandC;
    }

    public void setVcpSTandC(Boolean vcpSTandC) {
        this.vcpSTandC = vcpSTandC;
    }

    public Boolean getVcpSContactAdd() {
        return vcpSContactAdd;
    }

    public void setVcpSContactAdd(Boolean vcpSContactAdd) {
        this.vcpSContactAdd = vcpSContactAdd;
    }

    public Boolean getVcpSItemAdd() {
        return vcpSItemAdd;
    }

    public void setVcpSItemAdd(Boolean vcpSItemAdd) {
        this.vcpSItemAdd = vcpSItemAdd;
    }

    public Boolean getVcpSReturn() {
        return vcpSReturn;
    }

    public void setVcpSReturn(Boolean vcpSReturn) {
        this.vcpSReturn = vcpSReturn;
    }

    public Boolean getVcpSTndCAdd() {
        return vcpSTndCAdd;
    }

    public void setVcpSTndCAdd(Boolean vcpSTndCAdd) {
        this.vcpSTndCAdd = vcpSTndCAdd;
    }

    public Boolean getVcpSSMAdd() {
        return vcpSSMAdd;
    }

    public void setVcpSSMAdd(Boolean vcpSSMAdd) {
        this.vcpSSMAdd = vcpSSMAdd;
    }

    public Boolean getVcpSClearDraft() {
        return vcpSClearDraft;
    }

    public void setVcpSClearDraft(Boolean vcpSClearDraft) {
        this.vcpSClearDraft = vcpSClearDraft;
    }

    public Boolean getVcpSContact() {
        return vcpSContact;
    }

    public void setVcpSContact(Boolean vcpSContact) {
        this.vcpSContact = vcpSContact;
    }

    public Boolean getVcpSImportItem() {
        return vcpSImportItem;
    }

    public void setVcpSImportItem(Boolean vcpSImportItem) {
        this.vcpSImportItem = vcpSImportItem;
    }

    public Boolean getVcpSCCAdd() {
        return vcpSCCAdd;
    }

    public void setVcpSCCAdd(Boolean vcpSCCAdd) {
        this.vcpSCCAdd = vcpSCCAdd;
    }

    public Boolean getVcpSSM() {
        return vcpSSM;
    }

    public void setVcpSSM(Boolean vcpSSM) {
        this.vcpSSM = vcpSSM;
    }

    public Boolean getVcpSDebitNote() {
        return vcpSDebitNote;
    }

    public void setVcpSDebitNote(Boolean vcpSDebitNote) {
        this.vcpSDebitNote = vcpSDebitNote;
    }

    public Boolean getVcgSItemAdd() {
        return vcgSItemAdd;
    }

    public void setVcgSItemAdd(Boolean vcgSItemAdd) {
        this.vcgSItemAdd = vcgSItemAdd;
    }

    public Boolean getVcpSBarCode() {
        return vcpSBarCode;
    }

    public void setVcpSBarCode(Boolean vcpSBarCode) {
        this.vcpSBarCode = vcpSBarCode;
    }

    public Boolean getVcpSCC() {
        return vcpSCC;
    }

    public void setVcpSCC(Boolean vcpSCC) {
        this.vcpSCC = vcpSCC;
    }

    public Boolean getVcpSSaveOrder() {
        return vcpSSaveOrder;
    }

    public void setVcpSSaveOrder(Boolean vcpSSaveOrder) {
        this.vcpSSaveOrder = vcpSSaveOrder;
    }

    public Boolean getVcpSEmployee() {
        return vcpSEmployee;
    }

    public void setVcpSEmployee(Boolean vcpSEmployee) {
        this.vcpSEmployee = vcpSEmployee;
    }

    public Boolean getVcpSCurrency() {
        return vcpSCurrency;
    }

    public void setVcpSCurrency(Boolean vcpSCurrency) {
        this.vcpSCurrency = vcpSCurrency;
    }

    public Boolean getVcpSRemoveItem() {
        return vcpSRemoveItem;
    }

    public void setVcpSRemoveItem(Boolean vcpSRemoveItem) {
        this.vcpSRemoveItem = vcpSRemoveItem;
    }

    public Boolean getVcgoldsales() {
        return vcgoldsales;
    }

    public void setVcgoldsales(Boolean vcgoldsales) {
        this.vcgoldsales = vcgoldsales;
    }

    public Boolean getVcgSItemList() {
        return vcgSItemList;
    }

    public void setVcgSItemList(Boolean vcgSItemList) {
        this.vcgSItemList = vcgSItemList;
    }

    public Boolean getVcpSCustomer() {
        return vcpSCustomer;
    }

    public void setVcpSCustomer(Boolean vcpSCustomer) {
        this.vcpSCustomer = vcpSCustomer;
    }

    public Boolean getVcpSState() {
        return vcpSState;
    }

    public void setVcpSState(Boolean vcpSState) {
        this.vcpSState = vcpSState;
    }

    public Boolean getVcgSDate() {
        return vcgSDate;
    }

    public void setVcgSDate(Boolean vcgSDate) {
        this.vcgSDate = vcgSDate;
    }

    public Boolean getVcpSClearAll() {
        return vcpSClearAll;
    }

    public void setVcpSClearAll(Boolean vcpSClearAll) {
        this.vcpSClearAll = vcpSClearAll;
    }

    public Boolean getVcpSSavesDraft() {
        return vcpSSavesDraft;
    }

    public void setVcpSSavesDraft(Boolean vcpSSavesDraft) {
        this.vcpSSavesDraft = vcpSSavesDraft;
    }

    public Boolean getVcgSTax() {
        return vcgSTax;
    }

    public void setVcgSTax(Boolean vcgSTax) {
        this.vcgSTax = vcgSTax;
    }

    public Boolean getVcpSAgentAdd() {
        return vcpSAgentAdd;
    }

    public void setVcpSAgentAdd(Boolean vcpSAgentAdd) {
        this.vcpSAgentAdd = vcpSAgentAdd;
    }

    public Boolean getVcgSImportItem() {
        return vcgSImportItem;
    }

    public void setVcgSImportItem(Boolean vcgSImportItem) {
        this.vcgSImportItem = vcgSImportItem;
    }

    public Boolean getVcgSRemoveItem() {
        return vcgSRemoveItem;
    }

    public void setVcgSRemoveItem(Boolean vcgSRemoveItem) {
        this.vcgSRemoveItem = vcgSRemoveItem;
    }

    public Boolean getVcpSTaxType() {
        return vcpSTaxType;
    }

    public void setVcpSTaxType(Boolean vcpSTaxType) {
        this.vcpSTaxType = vcpSTaxType;
    }

    public Boolean getVcpharmaSales() {
        return vcpharmaSales;
    }

    public void setVcpharmaSales(Boolean vcpharmaSales) {
        this.vcpharmaSales = vcpharmaSales;
    }

    public Boolean getVcgSTotalAmt() {
        return vcgSTotalAmt;
    }

    public void setVcgSTotalAmt(Boolean vcgSTotalAmt) {
        this.vcgSTotalAmt = vcgSTotalAmt;
    }

    public Boolean getVcgSDraftClear() {
        return vcgSDraftClear;
    }

    public void setVcgSDraftClear(Boolean vcgSDraftClear) {
        this.vcgSDraftClear = vcgSDraftClear;
    }

    public Boolean getVcserviceCCAdd() {
        return vcserviceCCAdd;
    }

    public void setVcserviceCCAdd(Boolean vcserviceCCAdd) {
        this.vcserviceCCAdd = vcserviceCCAdd;
    }

    public Boolean getVcserviceSM() {
        return vcserviceSM;
    }

    public void setVcserviceSM(Boolean vcserviceSM) {
        this.vcserviceSM = vcserviceSM;
    }

    public Boolean getVcserviceSMAdd() {
        return vcserviceSMAdd;
    }

    public void setVcserviceSMAdd(Boolean vcserviceSMAdd) {
        this.vcserviceSMAdd = vcserviceSMAdd;
    }

    public Boolean getVcserviceCC() {
        return vcserviceCC;
    }

    public void setVcserviceCC(Boolean vcserviceCC) {
        this.vcserviceCC = vcserviceCC;
    }

    public Boolean getVcserviceRefNo() {
        return vcserviceRefNo;
    }

    public void setVcserviceRefNo(Boolean vcserviceRefNo) {
        this.vcserviceRefNo = vcserviceRefNo;
    }

    public Boolean getVcreportInventoryASCR() {
        return vcreportInventoryASCR;
    }

    public void setVcreportInventoryASCR(Boolean vcreportInventoryASCR) {
        this.vcreportInventoryASCR = vcreportInventoryASCR;
    }

    public Boolean getVcreportInventoryBER() {
        return vcreportInventoryBER;
    }

    public void setVcreportInventoryBER(Boolean vcreportInventoryBER) {
        this.vcreportInventoryBER = vcreportInventoryBER;
    }

    public Boolean getVcpurchaseSupplierAdd() {
        return vcpurchaseSupplierAdd;
    }

    public void setVcpurchaseSupplierAdd(Boolean vcpurchaseSupplierAdd) {
        this.vcpurchaseSupplierAdd = vcpurchaseSupplierAdd;
    }

    public Boolean getVcreportInventoryBI() {
        return vcreportInventoryBI;
    }

    public void setVcreportInventoryBI(Boolean vcreportInventoryBI) {
        this.vcreportInventoryBI = vcreportInventoryBI;
    }

    public Boolean getVcreportInventoryOGRNR() {
        return vcreportInventoryOGRNR;
    }

    public void setVcreportInventoryOGRNR(Boolean vcreportInventoryOGRNR) {
        this.vcreportInventoryOGRNR = vcreportInventoryOGRNR;
    }

    public Boolean getVcreportPurchase() {
        return vcreportPurchase;
    }

    public void setVcreportPurchase(Boolean vcreportPurchase) {
        this.vcreportPurchase = vcreportPurchase;
    }

    public Boolean getVcpurchaseInvokePurchaseOrder() {
        return vcpurchaseInvokePurchaseOrder;
    }

    public void setVcpurchaseInvokePurchaseOrder(Boolean vcpurchaseInvokePurchaseOrder) {
        this.vcpurchaseInvokePurchaseOrder = vcpurchaseInvokePurchaseOrder;
    }

    public Boolean getVcreportInventoryilSCR() {
        return vcreportInventoryilSCR;
    }

    public void setVcreportInventoryilSCR(Boolean vcreportInventoryilSCR) {
        this.vcreportInventoryilSCR = vcreportInventoryilSCR;
    }

    public Boolean getVcreportFStatementTB() {
        return vcreportFStatementTB;
    }

    public void setVcreportFStatementTB(Boolean vcreportFStatementTB) {
        this.vcreportFStatementTB = vcreportFStatementTB;
    }

    public Boolean getVcreportFStatementVL() {
        return vcreportFStatementVL;
    }

    public void setVcreportFStatementVL(Boolean vcreportFStatementVL) {
        this.vcreportFStatementVL = vcreportFStatementVL;
    }

    public Boolean getVcreportPurchaseCPA() {
        return vcreportPurchaseCPA;
    }

    public void setVcreportPurchaseCPA(Boolean vcreportPurchaseCPA) {
        this.vcreportPurchaseCPA = vcreportPurchaseCPA;
    }

    public Boolean getVcreportInventoryIVR() {
        return vcreportInventoryIVR;
    }

    public void setVcreportInventoryIVR(Boolean vcreportInventoryIVR) {
        this.vcreportInventoryIVR = vcreportInventoryIVR;
    }

    public Boolean getVcreportPurchasePA() {
        return vcreportPurchasePA;
    }

    public void setVcreportPurchasePA(Boolean vcreportPurchasePA) {
        this.vcreportPurchasePA = vcreportPurchasePA;
    }

    public Boolean getVcreportFStatementPL() {
        return vcreportFStatementPL;
    }

    public void setVcreportFStatementPL(Boolean vcreportFStatementPL) {
        this.vcreportFStatementPL = vcreportFStatementPL;
    }

    public Boolean getVcreportAnalysis() {
        return vcreportAnalysis;
    }

    public void setVcreportAnalysis(Boolean vcreportAnalysis) {
        this.vcreportAnalysis = vcreportAnalysis;
    }

    public Boolean getVcreportAnalysisCIP() {
        return vcreportAnalysisCIP;
    }

    public void setVcreportAnalysisCIP(Boolean vcreportAnalysisCIP) {
        this.vcreportAnalysisCIP = vcreportAnalysisCIP;
    }

    public Boolean getVcreportFStatementBS() {
        return vcreportFStatementBS;
    }

    public void setVcreportFStatementBS(Boolean vcreportFStatementBS) {
        this.vcreportFStatementBS = vcreportFStatementBS;
    }

    public Boolean getVcreportPurchasePIL() {
        return vcreportPurchasePIL;
    }

    public void setVcreportPurchasePIL(Boolean vcreportPurchasePIL) {
        this.vcreportPurchasePIL = vcreportPurchasePIL;
    }

    public Boolean getVcpurchaseClearAll() {
        return vcpurchaseClearAll;
    }

    public void setVcpurchaseClearAll(Boolean vcpurchaseClearAll) {
        this.vcpurchaseClearAll = vcpurchaseClearAll;
    }

    public Boolean getVcreportInventoryADVSCR() {
        return vcreportInventoryADVSCR;
    }

    public void setVcreportInventoryADVSCR(Boolean vcreportInventoryADVSCR) {
        this.vcreportInventoryADVSCR = vcreportInventoryADVSCR;
    }

    public Boolean getVcreportPurchaseSL() {
        return vcreportPurchaseSL;
    }

    public void setVcreportPurchaseSL(Boolean vcreportPurchaseSL) {
        this.vcreportPurchaseSL = vcreportPurchaseSL;
    }

    public Boolean getVcreportPurchaseSP() {
        return vcreportPurchaseSP;
    }

    public void setVcreportPurchaseSP(Boolean vcreportPurchaseSP) {
        this.vcreportPurchaseSP = vcreportPurchaseSP;
    }

    public Boolean getVcreportPurchaseSSL() {
        return vcreportPurchaseSSL;
    }

    public void setVcreportPurchaseSSL(Boolean vcreportPurchaseSSL) {
        this.vcreportPurchaseSSL = vcreportPurchaseSSL;
    }

    public Boolean getVcreportPurchaseSSR() {
        return vcreportPurchaseSSR;
    }

    public void setVcreportPurchaseSSR(Boolean vcreportPurchaseSSR) {
        this.vcreportPurchaseSSR = vcreportPurchaseSSR;
    }

    public Boolean getVcreportAnalysisCRR() {
        return vcreportAnalysisCRR;
    }

    public void setVcreportAnalysisCRR(Boolean vcreportAnalysisCRR) {
        this.vcreportAnalysisCRR = vcreportAnalysisCRR;
    }

    public Boolean getVcreportInventoryRPL() {
        return vcreportInventoryRPL;
    }

    public void setVcreportInventoryRPL(Boolean vcreportInventoryRPL) {
        this.vcreportInventoryRPL = vcreportInventoryRPL;
    }

    public Boolean getVcreportPurchaseRIL() {
        return vcreportPurchaseRIL;
    }

    public void setVcreportPurchaseRIL(Boolean vcreportPurchaseRIL) {
        this.vcreportPurchaseRIL = vcreportPurchaseRIL;
    }

    public Boolean getVcreportInventorySI() {
        return vcreportInventorySI;
    }

    public void setVcreportInventorySI(Boolean vcreportInventorySI) {
        this.vcreportInventorySI = vcreportInventorySI;
    }

    public Boolean getVcreportInventoryASSCR() {
        return vcreportInventoryASSCR;
    }

    public void setVcreportInventoryASSCR(Boolean vcreportInventoryASSCR) {
        this.vcreportInventoryASSCR = vcreportInventoryASSCR;
    }

    public Boolean getVcreportPurchaseCA() {
        return vcreportPurchaseCA;
    }

    public void setVcreportPurchaseCA(Boolean vcreportPurchaseCA) {
        this.vcreportPurchaseCA = vcreportPurchaseCA;
    }

    public Boolean getVcpurchaseSelfBilled() {
        return vcpurchaseSelfBilled;
    }

    public void setVcpurchaseSelfBilled(Boolean vcpurchaseSelfBilled) {
        this.vcpurchaseSelfBilled = vcpurchaseSelfBilled;
    }

    public Boolean getVcreportAnalysisTopItem() {
        return vcreportAnalysisTopItem;
    }

    public void setVcreportAnalysisTopItem(Boolean vcreportAnalysisTopItem) {
        this.vcreportAnalysisTopItem = vcreportAnalysisTopItem;
    }

    public Boolean getVcreportSaleCLP() {
        return vcreportSaleCLP;
    }

    public void setVcreportSaleCLP(Boolean vcreportSaleCLP) {
        this.vcreportSaleCLP = vcreportSaleCLP;
    }

    public Boolean getVcpurchaseSavePurchaseOrder() {
        return vcpurchaseSavePurchaseOrder;
    }

    public void setVcpurchaseSavePurchaseOrder(Boolean vcpurchaseSavePurchaseOrder) {
        this.vcpurchaseSavePurchaseOrder = vcpurchaseSavePurchaseOrder;
    }

    public Boolean getVcpurchaseItemAdd() {
        return vcpurchaseItemAdd;
    }

    public void setVcpurchaseItemAdd(Boolean vcpurchaseItemAdd) {
        this.vcpurchaseItemAdd = vcpurchaseItemAdd;
    }

    public Boolean getVcreportPurchasePRL() {
        return vcreportPurchasePRL;
    }

    public void setVcreportPurchasePRL(Boolean vcreportPurchasePRL) {
        this.vcreportPurchasePRL = vcreportPurchasePRL;
    }

    public Boolean getVcreportAnalysisTopVendor() {
        return vcreportAnalysisTopVendor;
    }

    public void setVcreportAnalysisTopVendor(Boolean vcreportAnalysisTopVendor) {
        this.vcreportAnalysisTopVendor = vcreportAnalysisTopVendor;
    }

    public Boolean getVcreportInventoryDEL() {
        return vcreportInventoryDEL;
    }

    public void setVcreportInventoryDEL(Boolean vcreportInventoryDEL) {
        this.vcreportInventoryDEL = vcreportInventoryDEL;
    }

    public Boolean getVcreportInventoryGRNVR() {
        return vcreportInventoryGRNVR;
    }

    public void setVcreportInventoryGRNVR(Boolean vcreportInventoryGRNVR) {
        this.vcreportInventoryGRNVR = vcreportInventoryGRNVR;
    }

    public Boolean getVcreportPurchasePOL() {
        return vcreportPurchasePOL;
    }

    public void setVcreportPurchasePOL(Boolean vcreportPurchasePOL) {
        this.vcreportPurchasePOL = vcreportPurchasePOL;
    }

    public Boolean getVcpurchasePrintList() {
        return vcpurchasePrintList;
    }

    public void setVcpurchasePrintList(Boolean vcpurchasePrintList) {
        this.vcpurchasePrintList = vcpurchasePrintList;
    }

    public Boolean getVcreportAnalysisSIP() {
        return vcreportAnalysisSIP;
    }

    public void setVcreportAnalysisSIP(Boolean vcreportAnalysisSIP) {
        this.vcreportAnalysisSIP = vcreportAnalysisSIP;
    }

    public Boolean getVcreportBankRecon() {
        return vcreportBankRecon;
    }

    public void setVcreportBankRecon(Boolean vcreportBankRecon) {
        this.vcreportBankRecon = vcreportBankRecon;
    }

    public Boolean getVcpurchaseCheckOut() {
        return vcpurchaseCheckOut;
    }

    public void setVcpurchaseCheckOut(Boolean vcpurchaseCheckOut) {
        this.vcpurchaseCheckOut = vcpurchaseCheckOut;
    }

    public Boolean getVcreportFStatementGL() {
        return vcreportFStatementGL;
    }

    public void setVcreportFStatementGL(Boolean vcreportFStatementGL) {
        this.vcreportFStatementGL = vcreportFStatementGL;
    }

    public Boolean getVcreportInventoryIL() {
        return vcreportInventoryIL;
    }

    public void setVcreportInventoryIL(Boolean vcreportInventoryIL) {
        this.vcreportInventoryIL = vcreportInventoryIL;
    }

    public Boolean getVcreportInventoryINMR() {
        return vcreportInventoryINMR;
    }

    public void setVcreportInventoryINMR(Boolean vcreportInventoryINMR) {
        this.vcreportInventoryINMR = vcreportInventoryINMR;
    }

    public Boolean getVcreportInventoryIMR() {
        return vcreportInventoryIMR;
    }

    public void setVcreportInventoryIMR(Boolean vcreportInventoryIMR) {
        this.vcreportInventoryIMR = vcreportInventoryIMR;
    }

    public Boolean getVcreportInventoryIPR() {
        return vcreportInventoryIPR;
    }

    public void setVcreportInventoryIPR(Boolean vcreportInventoryIPR) {
        this.vcreportInventoryIPR = vcreportInventoryIPR;
    }

    public Boolean getVcreportInventoryRPR() {
        return vcreportInventoryRPR;
    }

    public void setVcreportInventoryRPR(Boolean vcreportInventoryRPR) {
        this.vcreportInventoryRPR = vcreportInventoryRPR;
    }

    public Boolean getVcreportInventory() {
        return vcreportInventory;
    }

    public void setVcreportInventory(Boolean vcreportInventory) {
        this.vcreportInventory = vcreportInventory;
    }

    public Boolean getVcreportAnalysisTopCustomer() {
        return vcreportAnalysisTopCustomer;
    }

    public void setVcreportAnalysisTopCustomer(Boolean vcreportAnalysisTopCustomer) {
        this.vcreportAnalysisTopCustomer = vcreportAnalysisTopCustomer;
    }

    public Boolean getVcreportInventoryADVSSR() {
        return vcreportInventoryADVSSR;
    }

    public void setVcreportInventoryADVSSR(Boolean vcreportInventoryADVSSR) {
        this.vcreportInventoryADVSSR = vcreportInventoryADVSSR;
    }

    public Boolean getVcpurchaseReturn() {
        return vcpurchaseReturn;
    }

    public void setVcpurchaseReturn(Boolean vcpurchaseReturn) {
        this.vcpurchaseReturn = vcpurchaseReturn;
    }

    public Boolean getVcreportFStatementGJ() {
        return vcreportFStatementGJ;
    }

    public void setVcreportFStatementGJ(Boolean vcreportFStatementGJ) {
        this.vcreportFStatementGJ = vcreportFStatementGJ;
    }

    public Boolean getVcreportFinancialStatement() {
        return vcreportFinancialStatement;
    }

    public void setVcreportFinancialStatement(Boolean vcreportFinancialStatement) {
        this.vcreportFinancialStatement = vcreportFinancialStatement;
    }

    public Boolean getVcreportInventoryISR() {
        return vcreportInventoryISR;
    }

    public void setVcreportInventoryISR(Boolean vcreportInventoryISR) {
        this.vcreportInventoryISR = vcreportInventoryISR;
    }

    public Boolean getVcpurchaseSupplierPayment() {
        return vcpurchaseSupplierPayment;
    }

    public void setVcpurchaseSupplierPayment(Boolean vcpurchaseSupplierPayment) {
        this.vcpurchaseSupplierPayment = vcpurchaseSupplierPayment;
    }

    public Boolean getVcreportBankReconRatio() {
        return vcreportBankReconRatio;
    }

    public void setVcreportBankReconRatio(Boolean vcreportBankReconRatio) {
        this.vcreportBankReconRatio = vcreportBankReconRatio;
    }

    public Boolean getVcpurSaveRecItem() {
        return vcpurSaveRecItem;
    }

    public void setVcpurSaveRecItem(Boolean vcpurSaveRecItem) {
        this.vcpurSaveRecItem = vcpurSaveRecItem;
    }

    public Boolean getVcsalesStateAdd() {
        return vcsalesStateAdd;
    }

    public void setVcsalesStateAdd(Boolean vcsalesStateAdd) {
        this.vcsalesStateAdd = vcsalesStateAdd;
    }

    public Boolean getVcpurInvokeRecItem() {
        return vcpurInvokeRecItem;
    }

    public void setVcpurInvokeRecItem(Boolean vcpurInvokeRecItem) {
        this.vcpurInvokeRecItem = vcpurInvokeRecItem;
    }

    public Boolean getVcsalesCurrency() {
        return vcsalesCurrency;
    }

    public void setVcsalesCurrency(Boolean vcsalesCurrency) {
        this.vcsalesCurrency = vcsalesCurrency;
    }

    public Boolean getVcsalesSaveProforma() {
        return vcsalesSaveProforma;
    }

    public void setVcsalesSaveProforma(Boolean vcsalesSaveProforma) {
        this.vcsalesSaveProforma = vcsalesSaveProforma;
    }

    public Boolean getVcpurReturnRecItem() {
        return vcpurReturnRecItem;
    }

    public void setVcpurReturnRecItem(Boolean vcpurReturnRecItem) {
        this.vcpurReturnRecItem = vcpurReturnRecItem;
    }

    public Boolean getVcpurInvLocAdd() {
        return vcpurInvLocAdd;
    }

    public void setVcpurInvLocAdd(Boolean vcpurInvLocAdd) {
        this.vcpurInvLocAdd = vcpurInvLocAdd;
    }

    public Boolean getVcsalesInvokeDraft() {
        return vcsalesInvokeDraft;
    }

    public void setVcsalesInvokeDraft(Boolean vcsalesInvokeDraft) {
        this.vcsalesInvokeDraft = vcsalesInvokeDraft;
    }

    public Boolean getVcsalesSerializeItem() {
        return vcsalesSerializeItem;
    }

    public void setVcsalesSerializeItem(Boolean vcsalesSerializeItem) {
        this.vcsalesSerializeItem = vcsalesSerializeItem;
    }

    public Boolean getVcpurRemoveItem() {
        return vcpurRemoveItem;
    }

    public void setVcpurRemoveItem(Boolean vcpurRemoveItem) {
        this.vcpurRemoveItem = vcpurRemoveItem;
    }

    public Boolean getVcsalesAdvancePayment() {
        return vcsalesAdvancePayment;
    }

    public void setVcsalesAdvancePayment(Boolean vcsalesAdvancePayment) {
        this.vcsalesAdvancePayment = vcsalesAdvancePayment;
    }

    public Boolean getVcsalesEmployeeAdd() {
        return vcsalesEmployeeAdd;
    }

    public void setVcsalesEmployeeAdd(Boolean vcsalesEmployeeAdd) {
        this.vcsalesEmployeeAdd = vcsalesEmployeeAdd;
    }

    public Boolean getVcsalesTndCAdd() {
        return vcsalesTndCAdd;
    }

    public void setVcsalesTndCAdd(Boolean vcsalesTndCAdd) {
        this.vcsalesTndCAdd = vcsalesTndCAdd;
    }

    public Boolean getVcsalesReturnDeliveryOrder() {
        return vcsalesReturnDeliveryOrder;
    }

    public void setVcsalesReturnDeliveryOrder(Boolean vcsalesReturnDeliveryOrder) {
        this.vcsalesReturnDeliveryOrder = vcsalesReturnDeliveryOrder;
    }

    public Boolean getVcsalesSavesDraft() {
        return vcsalesSavesDraft;
    }

    public void setVcsalesSavesDraft(Boolean vcsalesSavesDraft) {
        this.vcsalesSavesDraft = vcsalesSavesDraft;
    }

    public Boolean getVcsalesClearAll() {
        return vcsalesClearAll;
    }

    public void setVcsalesClearAll(Boolean vcsalesClearAll) {
        this.vcsalesClearAll = vcsalesClearAll;
    }

    public Boolean getVcsalesCustomer() {
        return vcsalesCustomer;
    }

    public void setVcsalesCustomer(Boolean vcsalesCustomer) {
        this.vcsalesCustomer = vcsalesCustomer;
    }

    public Boolean getVcsalesInvokeSalesOrder() {
        return vcsalesInvokeSalesOrder;
    }

    public void setVcsalesInvokeSalesOrder(Boolean vcsalesInvokeSalesOrder) {
        this.vcsalesInvokeSalesOrder = vcsalesInvokeSalesOrder;
    }

    public Boolean getVcreportSaleSRL() {
        return vcreportSaleSRL;
    }

    public void setVcreportSaleSRL(Boolean vcreportSaleSRL) {
        this.vcreportSaleSRL = vcreportSaleSRL;
    }

    public Boolean getVcsalesRemoveItem() {
        return vcsalesRemoveItem;
    }

    public void setVcsalesRemoveItem(Boolean vcsalesRemoveItem) {
        this.vcsalesRemoveItem = vcsalesRemoveItem;
    }

    public Boolean getVcsalesContact() {
        return vcsalesContact;
    }

    public void setVcsalesContact(Boolean vcsalesContact) {
        this.vcsalesContact = vcsalesContact;
    }

    public Boolean getVcsalesCurrencyAdd() {
        return vcsalesCurrencyAdd;
    }

    public void setVcsalesCurrencyAdd(Boolean vcsalesCurrencyAdd) {
        this.vcsalesCurrencyAdd = vcsalesCurrencyAdd;
    }

    public Boolean getVcsalesSaveSalesQuotation() {
        return vcsalesSaveSalesQuotation;
    }

    public void setVcsalesSaveSalesQuotation(Boolean vcsalesSaveSalesQuotation) {
        this.vcsalesSaveSalesQuotation = vcsalesSaveSalesQuotation;
    }

    public Boolean getVcsalesInvokeSalesQuotation() {
        return vcsalesInvokeSalesQuotation;
    }

    public void setVcsalesInvokeSalesQuotation(Boolean vcsalesInvokeSalesQuotation) {
        this.vcsalesInvokeSalesQuotation = vcsalesInvokeSalesQuotation;
    }

    public Boolean getVcsalesClearDraft() {
        return vcsalesClearDraft;
    }

    public void setVcsalesClearDraft(Boolean vcsalesClearDraft) {
        this.vcsalesClearDraft = vcsalesClearDraft;
    }

    public Boolean getVctaxTaxConfiguration() {
        return vctaxTaxConfiguration;
    }

    public void setVctaxTaxConfiguration(Boolean vctaxTaxConfiguration) {
        this.vctaxTaxConfiguration = vctaxTaxConfiguration;
    }

    public Boolean getVcreportSaleCSI() {
        return vcreportSaleCSI;
    }

    public void setVcreportSaleCSI(Boolean vcreportSaleCSI) {
        this.vcreportSaleCSI = vcreportSaleCSI;
    }

    public Boolean getVcreportSaleSQL() {
        return vcreportSaleSQL;
    }

    public void setVcreportSaleSQL(Boolean vcreportSaleSQL) {
        this.vcreportSaleSQL = vcreportSaleSQL;
    }

    public Boolean getVcsalesEmployee() {
        return vcsalesEmployee;
    }

    public void setVcsalesEmployee(Boolean vcsalesEmployee) {
        this.vcsalesEmployee = vcsalesEmployee;
    }

    public Boolean getVcreportSalePDL() {
        return vcreportSalePDL;
    }

    public void setVcreportSalePDL(Boolean vcreportSalePDL) {
        this.vcreportSalePDL = vcreportSalePDL;
    }

    public Boolean getVcsalesSaveSalesOrder() {
        return vcsalesSaveSalesOrder;
    }

    public void setVcsalesSaveSalesOrder(Boolean vcsalesSaveSalesOrder) {
        this.vcsalesSaveSalesOrder = vcsalesSaveSalesOrder;
    }

    public Boolean getVcsalesInvLocAdd() {
        return vcsalesInvLocAdd;
    }

    public void setVcsalesInvLocAdd(Boolean vcsalesInvLocAdd) {
        this.vcsalesInvLocAdd = vcsalesInvLocAdd;
    }

    public Boolean getVcreportSaleICL() {
        return vcreportSaleICL;
    }

    public void setVcreportSaleICL(Boolean vcreportSaleICL) {
        this.vcreportSaleICL = vcreportSaleICL;
    }

    public Boolean getVcsalesPrintList() {
        return vcsalesPrintList;
    }

    public void setVcsalesPrintList(Boolean vcsalesPrintList) {
        this.vcsalesPrintList = vcsalesPrintList;
    }

    public Boolean getVcsalesSearchSerializeItem() {
        return vcsalesSearchSerializeItem;
    }

    public void setVcsalesSearchSerializeItem(Boolean vcsalesSearchSerializeItem) {
        this.vcsalesSearchSerializeItem = vcsalesSearchSerializeItem;
    }

    public Boolean getVcsalesImportItem() {
        return vcsalesImportItem;
    }

    public void setVcsalesImportItem(Boolean vcsalesImportItem) {
        this.vcsalesImportItem = vcsalesImportItem;
    }

    public Boolean getVcsalesAgentAdd() {
        return vcsalesAgentAdd;
    }

    public void setVcsalesAgentAdd(Boolean vcsalesAgentAdd) {
        this.vcsalesAgentAdd = vcsalesAgentAdd;
    }

    public Boolean getVcsalesExchangeRateAdd() {
        return vcsalesExchangeRateAdd;
    }

    public void setVcsalesExchangeRateAdd(Boolean vcsalesExchangeRateAdd) {
        this.vcsalesExchangeRateAdd = vcsalesExchangeRateAdd;
    }

    public Boolean getVcsalesCustomerAdd() {
        return vcsalesCustomerAdd;
    }

    public void setVcsalesCustomerAdd(Boolean vcsalesCustomerAdd) {
        this.vcsalesCustomerAdd = vcsalesCustomerAdd;
    }

    public Boolean getVcsalesExchangeRate() {
        return vcsalesExchangeRate;
    }

    public void setVcsalesExchangeRate(Boolean vcsalesExchangeRate) {
        this.vcsalesExchangeRate = vcsalesExchangeRate;
    }

    public Boolean getVcsalesCheckOut() {
        return vcsalesCheckOut;
    }

    public void setVcsalesCheckOut(Boolean vcsalesCheckOut) {
        this.vcsalesCheckOut = vcsalesCheckOut;
    }

    public Boolean getVcsalesShippingRefNo() {
        return vcsalesShippingRefNo;
    }

    public void setVcsalesShippingRefNo(Boolean vcsalesShippingRefNo) {
        this.vcsalesShippingRefNo = vcsalesShippingRefNo;
    }

    public Boolean getVcsalesInvokeProforma() {
        return vcsalesInvokeProforma;
    }

    public void setVcsalesInvokeProforma(Boolean vcsalesInvokeProforma) {
        this.vcsalesInvokeProforma = vcsalesInvokeProforma;
    }

    public Boolean getVcreportSaleSIL() {
        return vcreportSaleSIL;
    }

    public void setVcreportSaleSIL(Boolean vcreportSaleSIL) {
        this.vcreportSaleSIL = vcreportSaleSIL;
    }

    public Boolean getVcreportSaleSOL() {
        return vcreportSaleSOL;
    }

    public void setVcreportSaleSOL(Boolean vcreportSaleSOL) {
        this.vcreportSaleSOL = vcreportSaleSOL;
    }

    public Boolean getVcsalesRecievePayment() {
        return vcsalesRecievePayment;
    }

    public void setVcsalesRecievePayment(Boolean vcsalesRecievePayment) {
        this.vcsalesRecievePayment = vcsalesRecievePayment;
    }

    public Boolean getVcsalesBarCode() {
        return vcsalesBarCode;
    }

    public void setVcsalesBarCode(Boolean vcsalesBarCode) {
        this.vcsalesBarCode = vcsalesBarCode;
    }

    public Boolean getVcsalesAdvancePaymentList() {
        return vcsalesAdvancePaymentList;
    }

    public void setVcsalesAdvancePaymentList(Boolean vcsalesAdvancePaymentList) {
        this.vcsalesAdvancePaymentList = vcsalesAdvancePaymentList;
    }

    public Boolean getVcsalesSearchBarCode() {
        return vcsalesSearchBarCode;
    }

    public void setVcsalesSearchBarCode(Boolean vcsalesSearchBarCode) {
        this.vcsalesSearchBarCode = vcsalesSearchBarCode;
    }

    public Boolean getVcsalesTaxType() {
        return vcsalesTaxType;
    }

    public void setVcsalesTaxType(Boolean vcsalesTaxType) {
        this.vcsalesTaxType = vcsalesTaxType;
    }

    public Boolean getVcsalesContactAdd() {
        return vcsalesContactAdd;
    }

    public void setVcsalesContactAdd(Boolean vcsalesContactAdd) {
        this.vcsalesContactAdd = vcsalesContactAdd;
    }

    public Boolean getVcsalesSaveDeliveryOrder() {
        return vcsalesSaveDeliveryOrder;
    }

    public void setVcsalesSaveDeliveryOrder(Boolean vcsalesSaveDeliveryOrder) {
        this.vcsalesSaveDeliveryOrder = vcsalesSaveDeliveryOrder;
    }

    public Boolean getVcsalesDebitNote() {
        return vcsalesDebitNote;
    }

    public void setVcsalesDebitNote(Boolean vcsalesDebitNote) {
        this.vcsalesDebitNote = vcsalesDebitNote;
    }

    public Boolean getVcdraftInvoice() {
        return vcdraftInvoice;
    }

    public void setVcdraftInvoice(Boolean vcdraftInvoice) {
        this.vcdraftInvoice = vcdraftInvoice;
    }

    public Boolean getVcsalesInvokeDeliveryOrder() {
        return vcsalesInvokeDeliveryOrder;
    }

    public void setVcsalesInvokeDeliveryOrder(Boolean vcsalesInvokeDeliveryOrder) {
        this.vcsalesInvokeDeliveryOrder = vcsalesInvokeDeliveryOrder;
    }

    public Boolean getVcpurSearchBarCode() {
        return vcpurSearchBarCode;
    }

    public void setVcpurSearchBarCode(Boolean vcpurSearchBarCode) {
        this.vcpurSearchBarCode = vcpurSearchBarCode;
    }

    public Boolean getVcrevChargeInv() {
        return vcrevChargeInv;
    }

    public void setVcrevChargeInv(Boolean vcrevChargeInv) {
        this.vcrevChargeInv = vcrevChargeInv;
    }

    public Boolean getVcpurCreditNote() {
        return vcpurCreditNote;
    }

    public void setVcpurCreditNote(Boolean vcpurCreditNote) {
        this.vcpurCreditNote = vcpurCreditNote;
    }

    public Boolean getVcgstIndiaGSTR1Tool() {
        return vcgstIndiaGSTR1Tool;
    }

    public void setVcgstIndiaGSTR1Tool(Boolean vcgstIndiaGSTR1Tool) {
        this.vcgstIndiaGSTR1Tool = vcgstIndiaGSTR1Tool;
    }

    public Boolean getVcmanufacturingAP() {
        return vcmanufacturingAP;
    }

    public void setVcmanufacturingAP(Boolean vcmanufacturingAP) {
        this.vcmanufacturingAP = vcmanufacturingAP;
    }

    public Boolean getVcgPSearchBarCode() {
        return vcgPSearchBarCode;
    }

    public void setVcgPSearchBarCode(Boolean vcgPSearchBarCode) {
        this.vcgPSearchBarCode = vcgPSearchBarCode;
    }

    public Boolean getVcgPCustomerAdd() {
        return vcgPCustomerAdd;
    }

    public void setVcgPCustomerAdd(Boolean vcgPCustomerAdd) {
        this.vcgPCustomerAdd = vcgPCustomerAdd;
    }

    public Boolean getVcinventoryInvLocTransfer() {
        return vcinventoryInvLocTransfer;
    }

    public void setVcinventoryInvLocTransfer(Boolean vcinventoryInvLocTransfer) {
        this.vcinventoryInvLocTransfer = vcinventoryInvLocTransfer;
    }

    public Boolean getVcreportFStatementDB() {
        return vcreportFStatementDB;
    }

    public void setVcreportFStatementDB(Boolean vcreportFStatementDB) {
        this.vcreportFStatementDB = vcreportFStatementDB;
    }

    public Boolean getVcreportBankReconCheques() {
        return vcreportBankReconCheques;
    }

    public void setVcreportBankReconCheques(Boolean vcreportBankReconCheques) {
        this.vcreportBankReconCheques = vcreportBankReconCheques;
    }

    public Boolean getVcgPSerializeItem() {
        return vcgPSerializeItem;
    }

    public void setVcgPSerializeItem(Boolean vcgPSerializeItem) {
        this.vcgPSerializeItem = vcgPSerializeItem;
    }

    public Boolean getVcgPAdvancePayment() {
        return vcgPAdvancePayment;
    }

    public void setVcgPAdvancePayment(Boolean vcgPAdvancePayment) {
        this.vcgPAdvancePayment = vcgPAdvancePayment;
    }

    public Boolean getVcpurCurrencyAdd() {
        return vcpurCurrencyAdd;
    }

    public void setVcpurCurrencyAdd(Boolean vcpurCurrencyAdd) {
        this.vcpurCurrencyAdd = vcpurCurrencyAdd;
    }

    public Boolean getVcgPSaveQuotation() {
        return vcgPSaveQuotation;
    }

    public void setVcgPSaveQuotation(Boolean vcgPSaveQuotation) {
        this.vcgPSaveQuotation = vcgPSaveQuotation;
    }

    public Boolean getVcinventoryRedemption() {
        return vcinventoryRedemption;
    }

    public void setVcinventoryRedemption(Boolean vcinventoryRedemption) {
        this.vcinventoryRedemption = vcinventoryRedemption;
    }

    public Boolean getVcgPInvokeQuotation() {
        return vcgPInvokeQuotation;
    }

    public void setVcgPInvokeQuotation(Boolean vcgPInvokeQuotation) {
        this.vcgPInvokeQuotation = vcgPInvokeQuotation;
    }

    public Boolean getVcgPAdvancePaymentList() {
        return vcgPAdvancePaymentList;
    }

    public void setVcgPAdvancePaymentList(Boolean vcgPAdvancePaymentList) {
        this.vcgPAdvancePaymentList = vcgPAdvancePaymentList;
    }

    public Boolean getVccPCompanySetupBandR() {
        return vccPCompanySetupBandR;
    }

    public void setVccPCompanySetupBandR(Boolean vccPCompanySetupBandR) {
        this.vccPCompanySetupBandR = vccPCompanySetupBandR;
    }

    public Boolean getVcinventoryAdvDisConfig() {
        return vcinventoryAdvDisConfig;
    }

    public void setVcinventoryAdvDisConfig(Boolean vcinventoryAdvDisConfig) {
        this.vcinventoryAdvDisConfig = vcinventoryAdvDisConfig;
    }

    public Boolean getVccPCompanySetupRS() {
        return vccPCompanySetupRS;
    }

    public void setVccPCompanySetupRS(Boolean vccPCompanySetupRS) {
        this.vccPCompanySetupRS = vccPCompanySetupRS;
    }

    public Boolean getVcgPInvokeOrder() {
        return vcgPInvokeOrder;
    }

    public void setVcgPInvokeOrder(Boolean vcgPInvokeOrder) {
        this.vcgPInvokeOrder = vcgPInvokeOrder;
    }

    public Boolean getVcgPRecievePayment() {
        return vcgPRecievePayment;
    }

    public void setVcgPRecievePayment(Boolean vcgPRecievePayment) {
        this.vcgPRecievePayment = vcgPRecievePayment;
    }

    public Boolean getVcinventoryLoyality() {
        return vcinventoryLoyality;
    }

    public void setVcinventoryLoyality(Boolean vcinventoryLoyality) {
        this.vcinventoryLoyality = vcinventoryLoyality;
    }

    public Boolean getVcinventoryInvCountType() {
        return vcinventoryInvCountType;
    }

    public void setVcinventoryInvCountType(Boolean vcinventoryInvCountType) {
        this.vcinventoryInvCountType = vcinventoryInvCountType;
    }

    public Boolean getVcinventoryItemCommission() {
        return vcinventoryItemCommission;
    }

    public void setVcinventoryItemCommission(Boolean vcinventoryItemCommission) {
        this.vcinventoryItemCommission = vcinventoryItemCommission;
    }

    public Boolean getVccPCompanySetupAlerts() {
        return vccPCompanySetupAlerts;
    }

    public void setVccPCompanySetupAlerts(Boolean vccPCompanySetupAlerts) {
        this.vccPCompanySetupAlerts = vccPCompanySetupAlerts;
    }

    public Boolean getVcreportBankReconStmnt() {
        return vcreportBankReconStmnt;
    }

    public void setVcreportBankReconStmnt(Boolean vcreportBankReconStmnt) {
        this.vcreportBankReconStmnt = vcreportBankReconStmnt;
    }

    public Boolean getVcgstIndiaGSTR2Tool() {
        return vcgstIndiaGSTR2Tool;
    }

    public void setVcgstIndiaGSTR2Tool(Boolean vcgstIndiaGSTR2Tool) {
        this.vcgstIndiaGSTR2Tool = vcgstIndiaGSTR2Tool;
    }

    public Boolean getVcreportFStatementPLLocWise() {
        return vcreportFStatementPLLocWise;
    }

    public void setVcreportFStatementPLLocWise(Boolean vcreportFStatementPLLocWise) {
        this.vcreportFStatementPLLocWise = vcreportFStatementPLLocWise;
    }

    public Boolean getVcmanufacturing() {
        return vcmanufacturing;
    }

    public void setVcmanufacturing(Boolean vcmanufacturing) {
        this.vcmanufacturing = vcmanufacturing;
    }

    public Boolean getVcgoldPurchases() {
        return vcgoldPurchases;
    }

    public void setVcgoldPurchases(Boolean vcgoldPurchases) {
        this.vcgoldPurchases = vcgoldPurchases;
    }

    public Boolean getVcmanufacturingCR() {
        return vcmanufacturingCR;
    }

    public void setVcmanufacturingCR(Boolean vcmanufacturingCR) {
        this.vcmanufacturingCR = vcmanufacturingCR;
    }

    public Boolean getVcinventoryPayVoucher() {
        return vcinventoryPayVoucher;
    }

    public void setVcinventoryPayVoucher(Boolean vcinventoryPayVoucher) {
        this.vcinventoryPayVoucher = vcinventoryPayVoucher;
    }

    public Boolean getVcpurExchangeRateAdd() {
        return vcpurExchangeRateAdd;
    }

    public void setVcpurExchangeRateAdd(Boolean vcpurExchangeRateAdd) {
        this.vcpurExchangeRateAdd = vcpurExchangeRateAdd;
    }

    public Boolean getVcmanufacturingFSCR() {
        return vcmanufacturingFSCR;
    }

    public void setVcmanufacturingFSCR(Boolean vcmanufacturingFSCR) {
        this.vcmanufacturingFSCR = vcmanufacturingFSCR;
    }

    public Boolean getVcgstIndiaRIPC() {
        return vcgstIndiaRIPC;
    }

    public void setVcgstIndiaRIPC(Boolean vcgstIndiaRIPC) {
        this.vcgstIndiaRIPC = vcgstIndiaRIPC;
    }

    public Boolean getVcgstMalGAFExport() {
        return vcgstMalGAFExport;
    }

    public void setVcgstMalGAFExport(Boolean vcgstMalGAFExport) {
        this.vcgstMalGAFExport = vcgstMalGAFExport;
    }

    public Boolean getVcgstIndiaGST3B() {
        return vcgstIndiaGST3B;
    }

    public void setVcgstIndiaGST3B(Boolean vcgstIndiaGST3B) {
        this.vcgstIndiaGST3B = vcgstIndiaGST3B;
    }

    public Boolean getVcgstMalGSTReturn() {
        return vcgstMalGSTReturn;
    }

    public void setVcgstMalGSTReturn(Boolean vcgstMalGSTReturn) {
        this.vcgstMalGSTReturn = vcgstMalGSTReturn;
    }

    public Boolean getVcinventoryAttributeConfig() {
        return vcinventoryAttributeConfig;
    }

    public void setVcinventoryAttributeConfig(Boolean vcinventoryAttributeConfig) {
        this.vcinventoryAttributeConfig = vcinventoryAttributeConfig;
    }

    public Boolean getVcinventoryVoucher() {
        return vcinventoryVoucher;
    }

    public void setVcinventoryVoucher(Boolean vcinventoryVoucher) {
        this.vcinventoryVoucher = vcinventoryVoucher;
    }

    public Boolean getVcinventoryJSDN() {
        return vcinventoryJSDN;
    }

    public void setVcinventoryJSDN(Boolean vcinventoryJSDN) {
        this.vcinventoryJSDN = vcinventoryJSDN;
    }

    public Boolean getVcmanufacturingAICAR() {
        return vcmanufacturingAICAR;
    }

    public void setVcmanufacturingAICAR(Boolean vcmanufacturingAICAR) {
        this.vcmanufacturingAICAR = vcmanufacturingAICAR;
    }

    public Boolean getVcgstIndiaGSTTRANS1() {
        return vcgstIndiaGSTTRANS1;
    }

    public void setVcgstIndiaGSTTRANS1(Boolean vcgstIndiaGSTTRANS1) {
        this.vcgstIndiaGSTTRANS1 = vcgstIndiaGSTTRANS1;
    }

    public Boolean getVccPConfiguratorServiceCharge() {
        return vccPConfiguratorServiceCharge;
    }

    public void setVccPConfiguratorServiceCharge(Boolean vccPConfiguratorServiceCharge) {
        this.vccPConfiguratorServiceCharge = vccPConfiguratorServiceCharge;
    }

    public Boolean getVccontrolPanelDSTableConfig() {
        return vccontrolPanelDSTableConfig;
    }

    public void setVccontrolPanelDSTableConfig(Boolean vccontrolPanelDSTableConfig) {
        this.vccontrolPanelDSTableConfig = vccontrolPanelDSTableConfig;
    }

    public Boolean getVcpurShippingRefNo() {
        return vcpurShippingRefNo;
    }

    public void setVcpurShippingRefNo(Boolean vcpurShippingRefNo) {
        this.vcpurShippingRefNo = vcpurShippingRefNo;
    }

    public Boolean getVcinventoryItemCountAdjust() {
        return vcinventoryItemCountAdjust;
    }

    public void setVcinventoryItemCountAdjust(Boolean vcinventoryItemCountAdjust) {
        this.vcinventoryItemCountAdjust = vcinventoryItemCountAdjust;
    }

    public Boolean getVcpurExchangeRate() {
        return vcpurExchangeRate;
    }

    public void setVcpurExchangeRate(Boolean vcpurExchangeRate) {
        this.vcpurExchangeRate = vcpurExchangeRate;
    }

    public Boolean getVcgstIndiaGSTSR() {
        return vcgstIndiaGSTSR;
    }

    public void setVcgstIndiaGSTSR(Boolean vcgstIndiaGSTSR) {
        this.vcgstIndiaGSTSR = vcgstIndiaGSTSR;
    }

    public Boolean getVccPConfiguratorOffersConfig() {
        return vccPConfiguratorOffersConfig;
    }

    public void setVccPConfiguratorOffersConfig(Boolean vccPConfiguratorOffersConfig) {
        this.vccPConfiguratorOffersConfig = vccPConfiguratorOffersConfig;
    }

    public Boolean getVccontrolPanelDSEandSMSConfig() {
        return vccontrolPanelDSEandSMSConfig;
    }

    public void setVccontrolPanelDSEandSMSConfig(Boolean vccontrolPanelDSEandSMSConfig) {
        this.vccontrolPanelDSEandSMSConfig = vccontrolPanelDSEandSMSConfig;
    }

    public Boolean getVcpurEmployeeAdd() {
        return vcpurEmployeeAdd;
    }

    public void setVcpurEmployeeAdd(Boolean vcpurEmployeeAdd) {
        this.vcpurEmployeeAdd = vcpurEmployeeAdd;
    }

    public Boolean getVcinventoryAssetsCat() {
        return vcinventoryAssetsCat;
    }

    public void setVcinventoryAssetsCat(Boolean vcinventoryAssetsCat) {
        this.vcinventoryAssetsCat = vcinventoryAssetsCat;
    }

    public Boolean getVcmanufacturingPM() {
        return vcmanufacturingPM;
    }

    public void setVcmanufacturingPM(Boolean vcmanufacturingPM) {
        this.vcmanufacturingPM = vcmanufacturingPM;
    }

    public Boolean getVccPConfiguratorCountry() {
        return vccPConfiguratorCountry;
    }

    public void setVccPConfiguratorCountry(Boolean vccPConfiguratorCountry) {
        this.vccPConfiguratorCountry = vccPConfiguratorCountry;
    }

    public Boolean getVcinventoryAssets() {
        return vcinventoryAssets;
    }

    public void setVcinventoryAssets(Boolean vcinventoryAssets) {
        this.vcinventoryAssets = vcinventoryAssets;
    }

    public Boolean getVcinventoryAttribute() {
        return vcinventoryAttribute;
    }

    public void setVcinventoryAttribute(Boolean vcinventoryAttribute) {
        this.vcinventoryAttribute = vcinventoryAttribute;
    }

    public Boolean getVcgPSearchSerializeItem() {
        return vcgPSearchSerializeItem;
    }

    public void setVcgPSearchSerializeItem(Boolean vcgPSearchSerializeItem) {
        this.vcgPSearchSerializeItem = vcgPSearchSerializeItem;
    }

    public Boolean getVcpurImportItem() {
        return vcpurImportItem;
    }

    public void setVcpurImportItem(Boolean vcpurImportItem) {
        this.vcpurImportItem = vcpurImportItem;
    }

    public Boolean getVcinventoryHsnCode() {
        return vcinventoryHsnCode;
    }

    public void setVcinventoryHsnCode(Boolean vcinventoryHsnCode) {
        this.vcinventoryHsnCode = vcinventoryHsnCode;
    }

    public Boolean getVcinventoryUomConverter() {
        return vcinventoryUomConverter;
    }

    public void setVcinventoryUomConverter(Boolean vcinventoryUomConverter) {
        this.vcinventoryUomConverter = vcinventoryUomConverter;
    }

    public Boolean getVcinventoryAdvTableTransfer() {
        return vcinventoryAdvTableTransfer;
    }

    public void setVcinventoryAdvTableTransfer(Boolean vcinventoryAdvTableTransfer) {
        this.vcinventoryAdvTableTransfer = vcinventoryAdvTableTransfer;
    }

    public Boolean getVccontrolPanelDSTable() {
        return vccontrolPanelDSTable;
    }

    public void setVccontrolPanelDSTable(Boolean vccontrolPanelDSTable) {
        this.vccontrolPanelDSTable = vccontrolPanelDSTable;
    }

    public Boolean getVcpurContactAdd() {
        return vcpurContactAdd;
    }

    public void setVcpurContactAdd(Boolean vcpurContactAdd) {
        this.vcpurContactAdd = vcpurContactAdd;
    }

    public Boolean getVcmSAdvancePaymentList() {
        return vcmSAdvancePaymentList;
    }

    public void setVcmSAdvancePaymentList(Boolean vcmSAdvancePaymentList) {
        this.vcmSAdvancePaymentList = vcmSAdvancePaymentList;
    }

    public Boolean getVcmSSearchSerializeItem() {
        return vcmSSearchSerializeItem;
    }

    public void setVcmSSearchSerializeItem(Boolean vcmSSearchSerializeItem) {
        this.vcmSSearchSerializeItem = vcmSSearchSerializeItem;
    }

    public Boolean getVcgPInvokeDraft() {
        return vcgPInvokeDraft;
    }

    public void setVcgPInvokeDraft(Boolean vcgPInvokeDraft) {
        this.vcgPInvokeDraft = vcgPInvokeDraft;
    }

    public Boolean getVcgPExchangeRate() {
        return vcgPExchangeRate;
    }

    public void setVcgPExchangeRate(Boolean vcgPExchangeRate) {
        this.vcgPExchangeRate = vcgPExchangeRate;
    }

    public Boolean getVcpCCurrencyAdd() {
        return vcpCCurrencyAdd;
    }

    public void setVcpCCurrencyAdd(Boolean vcpCCurrencyAdd) {
        this.vcpCCurrencyAdd = vcpCCurrencyAdd;
    }

    public Boolean getVcmSCustomerAdd() {
        return vcmSCustomerAdd;
    }

    public void setVcmSCustomerAdd(Boolean vcmSCustomerAdd) {
        this.vcmSCustomerAdd = vcmSCustomerAdd;
    }

    public Boolean getVcpCSaveQuotation() {
        return vcpCSaveQuotation;
    }

    public void setVcpCSaveQuotation(Boolean vcpCSaveQuotation) {
        this.vcpCSaveQuotation = vcpCSaveQuotation;
    }

    public Boolean getVcpCEmployeeAdd() {
        return vcpCEmployeeAdd;
    }

    public void setVcpCEmployeeAdd(Boolean vcpCEmployeeAdd) {
        this.vcpCEmployeeAdd = vcpCEmployeeAdd;
    }

    public Boolean getVcpCExchangeRateAdd() {
        return vcpCExchangeRateAdd;
    }

    public void setVcpCExchangeRateAdd(Boolean vcpCExchangeRateAdd) {
        this.vcpCExchangeRateAdd = vcpCExchangeRateAdd;
    }

    public Boolean getVcpCExchangeRate() {
        return vcpCExchangeRate;
    }

    public void setVcpCExchangeRate(Boolean vcpCExchangeRate) {
        this.vcpCExchangeRate = vcpCExchangeRate;
    }

    public Boolean getVcpCShippingRefNo() {
        return vcpCShippingRefNo;
    }

    public void setVcpCShippingRefNo(Boolean vcpCShippingRefNo) {
        this.vcpCShippingRefNo = vcpCShippingRefNo;
    }

    public Boolean getVcgPReturnDeliveryOrder() {
        return vcgPReturnDeliveryOrder;
    }

    public void setVcgPReturnDeliveryOrder(Boolean vcgPReturnDeliveryOrder) {
        this.vcgPReturnDeliveryOrder = vcgPReturnDeliveryOrder;
    }

    public Boolean getVcmSCurrencyAdd() {
        return vcmSCurrencyAdd;
    }

    public void setVcmSCurrencyAdd(Boolean vcmSCurrencyAdd) {
        this.vcmSCurrencyAdd = vcmSCurrencyAdd;
    }

    public Boolean getVcpCRecievePayment() {
        return vcpCRecievePayment;
    }

    public void setVcpCRecievePayment(Boolean vcpCRecievePayment) {
        this.vcpCRecievePayment = vcpCRecievePayment;
    }

    public Boolean getVcmSSaveProforma() {
        return vcmSSaveProforma;
    }

    public void setVcmSSaveProforma(Boolean vcmSSaveProforma) {
        this.vcmSSaveProforma = vcmSSaveProforma;
    }

    public Boolean getVcpCInvokeQuotation() {
        return vcpCInvokeQuotation;
    }

    public void setVcpCInvokeQuotation(Boolean vcpCInvokeQuotation) {
        this.vcpCInvokeQuotation = vcpCInvokeQuotation;
    }

    public Boolean getVcmSDraftInvoice() {
        return vcmSDraftInvoice;
    }

    public void setVcmSDraftInvoice(Boolean vcmSDraftInvoice) {
        this.vcmSDraftInvoice = vcmSDraftInvoice;
    }

    public Boolean getVcgPEmployeeAdd() {
        return vcgPEmployeeAdd;
    }

    public void setVcgPEmployeeAdd(Boolean vcgPEmployeeAdd) {
        this.vcgPEmployeeAdd = vcgPEmployeeAdd;
    }

    public Boolean getVcmSSerializeItem() {
        return vcmSSerializeItem;
    }

    public void setVcmSSerializeItem(Boolean vcmSSerializeItem) {
        this.vcmSSerializeItem = vcmSSerializeItem;
    }

    public Boolean getVcmSShippingRefNo() {
        return vcmSShippingRefNo;
    }

    public void setVcmSShippingRefNo(Boolean vcmSShippingRefNo) {
        this.vcmSShippingRefNo = vcmSShippingRefNo;
    }

    public Boolean getVcmSExchangeRate() {
        return vcmSExchangeRate;
    }

    public void setVcmSExchangeRate(Boolean vcmSExchangeRate) {
        this.vcmSExchangeRate = vcmSExchangeRate;
    }

    public Boolean getVcmSInvokeQuotation() {
        return vcmSInvokeQuotation;
    }

    public void setVcmSInvokeQuotation(Boolean vcmSInvokeQuotation) {
        this.vcmSInvokeQuotation = vcmSInvokeQuotation;
    }

    public Boolean getVcpurchasesConstruction() {
        return vcpurchasesConstruction;
    }

    public void setVcpurchasesConstruction(Boolean vcpurchasesConstruction) {
        this.vcpurchasesConstruction = vcpurchasesConstruction;
    }

    public Boolean getVcgPExchangeRateAdd() {
        return vcgPExchangeRateAdd;
    }

    public void setVcgPExchangeRateAdd(Boolean vcgPExchangeRateAdd) {
        this.vcgPExchangeRateAdd = vcgPExchangeRateAdd;
    }

    public Boolean getVcmSInvokeDeliveryOrder() {
        return vcmSInvokeDeliveryOrder;
    }

    public void setVcmSInvokeDeliveryOrder(Boolean vcmSInvokeDeliveryOrder) {
        this.vcmSInvokeDeliveryOrder = vcmSInvokeDeliveryOrder;
    }

    public Boolean getVcgPSaveProforma() {
        return vcgPSaveProforma;
    }

    public void setVcgPSaveProforma(Boolean vcgPSaveProforma) {
        this.vcgPSaveProforma = vcgPSaveProforma;
    }

    public Boolean getVcpCInvokeDraft() {
        return vcpCInvokeDraft;
    }

    public void setVcpCInvokeDraft(Boolean vcpCInvokeDraft) {
        this.vcpCInvokeDraft = vcpCInvokeDraft;
    }

    public Boolean getVcmSReturnDeliveryOrder() {
        return vcmSReturnDeliveryOrder;
    }

    public void setVcmSReturnDeliveryOrder(Boolean vcmSReturnDeliveryOrder) {
        this.vcmSReturnDeliveryOrder = vcmSReturnDeliveryOrder;
    }

    public Boolean getVcpCSearchBarCode() {
        return vcpCSearchBarCode;
    }

    public void setVcpCSearchBarCode(Boolean vcpCSearchBarCode) {
        this.vcpCSearchBarCode = vcpCSearchBarCode;
    }

    public Boolean getVcpCSerializeItem() {
        return vcpCSerializeItem;
    }

    public void setVcpCSerializeItem(Boolean vcpCSerializeItem) {
        this.vcpCSerializeItem = vcpCSerializeItem;
    }

    public Boolean getVcpCAdvancePaymentList() {
        return vcpCAdvancePaymentList;
    }

    public void setVcpCAdvancePaymentList(Boolean vcpCAdvancePaymentList) {
        this.vcpCAdvancePaymentList = vcpCAdvancePaymentList;
    }

    public Boolean getVcgPInvokeProforma() {
        return vcgPInvokeProforma;
    }

    public void setVcgPInvokeProforma(Boolean vcgPInvokeProforma) {
        this.vcgPInvokeProforma = vcgPInvokeProforma;
    }

    public Boolean getVcmSInvokeOrder() {
        return vcmSInvokeOrder;
    }

    public void setVcmSInvokeOrder(Boolean vcmSInvokeOrder) {
        this.vcmSInvokeOrder = vcmSInvokeOrder;
    }

    public Boolean getVcmSEmployeeAdd() {
        return vcmSEmployeeAdd;
    }

    public void setVcmSEmployeeAdd(Boolean vcmSEmployeeAdd) {
        this.vcmSEmployeeAdd = vcmSEmployeeAdd;
    }

    public Boolean getVcmSExchangeRateAdd() {
        return vcmSExchangeRateAdd;
    }

    public void setVcmSExchangeRateAdd(Boolean vcmSExchangeRateAdd) {
        this.vcmSExchangeRateAdd = vcmSExchangeRateAdd;
    }

    public Boolean getVcpCSearchSerializeItem() {
        return vcpCSearchSerializeItem;
    }

    public void setVcpCSearchSerializeItem(Boolean vcpCSearchSerializeItem) {
        this.vcpCSearchSerializeItem = vcpCSearchSerializeItem;
    }

    public Boolean getVcpCAdvancePayment() {
        return vcpCAdvancePayment;
    }

    public void setVcpCAdvancePayment(Boolean vcpCAdvancePayment) {
        this.vcpCAdvancePayment = vcpCAdvancePayment;
    }

    public Boolean getVcgPShippingRefNo() {
        return vcgPShippingRefNo;
    }

    public void setVcgPShippingRefNo(Boolean vcgPShippingRefNo) {
        this.vcgPShippingRefNo = vcgPShippingRefNo;
    }

    public Boolean getVcgPSaveDeliveryOrder() {
        return vcgPSaveDeliveryOrder;
    }

    public void setVcgPSaveDeliveryOrder(Boolean vcgPSaveDeliveryOrder) {
        this.vcgPSaveDeliveryOrder = vcgPSaveDeliveryOrder;
    }

    public Boolean getVcgPDraftInvoice() {
        return vcgPDraftInvoice;
    }

    public void setVcgPDraftInvoice(Boolean vcgPDraftInvoice) {
        this.vcgPDraftInvoice = vcgPDraftInvoice;
    }

    public Boolean getVcmSRecievePayment() {
        return vcmSRecievePayment;
    }

    public void setVcmSRecievePayment(Boolean vcmSRecievePayment) {
        this.vcmSRecievePayment = vcmSRecievePayment;
    }

    public Boolean getVcmSInvokeDraft() {
        return vcmSInvokeDraft;
    }

    public void setVcmSInvokeDraft(Boolean vcmSInvokeDraft) {
        this.vcmSInvokeDraft = vcmSInvokeDraft;
    }

    public Boolean getVcmSInvokeProforma() {
        return vcmSInvokeProforma;
    }

    public void setVcmSInvokeProforma(Boolean vcmSInvokeProforma) {
        this.vcmSInvokeProforma = vcmSInvokeProforma;
    }

    public Boolean getVcmSSaveDeliveryOrder() {
        return vcmSSaveDeliveryOrder;
    }

    public void setVcmSSaveDeliveryOrder(Boolean vcmSSaveDeliveryOrder) {
        this.vcmSSaveDeliveryOrder = vcmSSaveDeliveryOrder;
    }

    public Boolean getVcpCCustomerAdd() {
        return vcpCCustomerAdd;
    }

    public void setVcpCCustomerAdd(Boolean vcpCCustomerAdd) {
        this.vcpCCustomerAdd = vcpCCustomerAdd;
    }

    public Boolean getVcmSAdvancePayment() {
        return vcmSAdvancePayment;
    }

    public void setVcmSAdvancePayment(Boolean vcmSAdvancePayment) {
        this.vcmSAdvancePayment = vcmSAdvancePayment;
    }

    public Boolean getVcpCInvokeOrder() {
        return vcpCInvokeOrder;
    }

    public void setVcpCInvokeOrder(Boolean vcpCInvokeOrder) {
        this.vcpCInvokeOrder = vcpCInvokeOrder;
    }

    public Boolean getVcgPCurrencyAdd() {
        return vcgPCurrencyAdd;
    }

    public void setVcgPCurrencyAdd(Boolean vcgPCurrencyAdd) {
        this.vcgPCurrencyAdd = vcgPCurrencyAdd;
    }

    public Boolean getVcmSSearchBarCode() {
        return vcmSSearchBarCode;
    }

    public void setVcmSSearchBarCode(Boolean vcmSSearchBarCode) {
        this.vcmSSearchBarCode = vcmSSearchBarCode;
    }

    public Boolean getVcgPInvokeDeliveryOrder() {
        return vcgPInvokeDeliveryOrder;
    }

    public void setVcgPInvokeDeliveryOrder(Boolean vcgPInvokeDeliveryOrder) {
        this.vcgPInvokeDeliveryOrder = vcgPInvokeDeliveryOrder;
    }

    public Boolean getVcmSSaveQuotation() {
        return vcmSSaveQuotation;
    }

    public void setVcmSSaveQuotation(Boolean vcmSSaveQuotation) {
        this.vcmSSaveQuotation = vcmSSaveQuotation;
    }

    public Boolean getVcrestItemSearch() {
        return vcrestItemSearch;
    }

    public void setVcrestItemSearch(Boolean vcrestItemSearch) {
        this.vcrestItemSearch = vcrestItemSearch;
    }

    public Boolean getVcrestCustomerAdd() {
        return vcrestCustomerAdd;
    }

    public void setVcrestCustomerAdd(Boolean vcrestCustomerAdd) {
        this.vcrestCustomerAdd = vcrestCustomerAdd;
    }

    public Boolean getVcrestSelectCategory() {
        return vcrestSelectCategory;
    }

    public void setVcrestSelectCategory(Boolean vcrestSelectCategory) {
        this.vcrestSelectCategory = vcrestSelectCategory;
    }

    public Boolean getVcgSCustomerList() {
        return vcgSCustomerList;
    }

    public void setVcgSCustomerList(Boolean vcgSCustomerList) {
        this.vcgSCustomerList = vcgSCustomerList;
    }

    public Boolean getVcpCSaveProforma() {
        return vcpCSaveProforma;
    }

    public void setVcpCSaveProforma(Boolean vcpCSaveProforma) {
        this.vcpCSaveProforma = vcpCSaveProforma;
    }

    public Boolean getVcpCReturnDeliveryOrder() {
        return vcpCReturnDeliveryOrder;
    }

    public void setVcpCReturnDeliveryOrder(Boolean vcpCReturnDeliveryOrder) {
        this.vcpCReturnDeliveryOrder = vcpCReturnDeliveryOrder;
    }

    public Boolean getVcrestRemoveItem() {
        return vcrestRemoveItem;
    }

    public void setVcrestRemoveItem(Boolean vcrestRemoveItem) {
        this.vcrestRemoveItem = vcrestRemoveItem;
    }

    public Boolean getVcrestTableAppend() {
        return vcrestTableAppend;
    }

    public void setVcrestTableAppend(Boolean vcrestTableAppend) {
        this.vcrestTableAppend = vcrestTableAppend;
    }

    public Boolean getVcpSAdvancePayment() {
        return vcpSAdvancePayment;
    }

    public void setVcpSAdvancePayment(Boolean vcpSAdvancePayment) {
        this.vcpSAdvancePayment = vcpSAdvancePayment;
    }

    public Boolean getVcrestSelectItem() {
        return vcrestSelectItem;
    }

    public void setVcrestSelectItem(Boolean vcrestSelectItem) {
        this.vcrestSelectItem = vcrestSelectItem;
    }

    public Boolean getVcpSSaveQuotation() {
        return vcpSSaveQuotation;
    }

    public void setVcpSSaveQuotation(Boolean vcpSSaveQuotation) {
        this.vcpSSaveQuotation = vcpSSaveQuotation;
    }

    public Boolean getVcrestOpenDrawer() {
        return vcrestOpenDrawer;
    }

    public void setVcrestOpenDrawer(Boolean vcrestOpenDrawer) {
        this.vcrestOpenDrawer = vcrestOpenDrawer;
    }

    public Boolean getVcrestRestaurentTable() {
        return vcrestRestaurentTable;
    }

    public void setVcrestRestaurentTable(Boolean vcrestRestaurentTable) {
        this.vcrestRestaurentTable = vcrestRestaurentTable;
    }

    public Boolean getVcserviceItemAdd() {
        return vcserviceItemAdd;
    }

    public void setVcserviceItemAdd(Boolean vcserviceItemAdd) {
        this.vcserviceItemAdd = vcserviceItemAdd;
    }

    public Boolean getVcserviceCustomerAdd() {
        return vcserviceCustomerAdd;
    }

    public void setVcserviceCustomerAdd(Boolean vcserviceCustomerAdd) {
        this.vcserviceCustomerAdd = vcserviceCustomerAdd;
    }

    public Boolean getVcrestSplitBill() {
        return vcrestSplitBill;
    }

    public void setVcrestSplitBill(Boolean vcrestSplitBill) {
        this.vcrestSplitBill = vcrestSplitBill;
    }

    public Boolean getVcpSSerializeItem() {
        return vcpSSerializeItem;
    }

    public void setVcpSSerializeItem(Boolean vcpSSerializeItem) {
        this.vcpSSerializeItem = vcpSSerializeItem;
    }

    public Boolean getVcserviceSavesDraft() {
        return vcserviceSavesDraft;
    }

    public void setVcserviceSavesDraft(Boolean vcserviceSavesDraft) {
        this.vcserviceSavesDraft = vcserviceSavesDraft;
    }

    public Boolean getVcrestAppendItem() {
        return vcrestAppendItem;
    }

    public void setVcrestAppendItem(Boolean vcrestAppendItem) {
        this.vcrestAppendItem = vcrestAppendItem;
    }

    public Boolean getVcpSDraftInvoice() {
        return vcpSDraftInvoice;
    }

    public void setVcpSDraftInvoice(Boolean vcpSDraftInvoice) {
        this.vcpSDraftInvoice = vcpSDraftInvoice;
    }

    public Boolean getVcrestAppendCustomer() {
        return vcrestAppendCustomer;
    }

    public void setVcrestAppendCustomer(Boolean vcrestAppendCustomer) {
        this.vcrestAppendCustomer = vcrestAppendCustomer;
    }

    public Boolean getVcgSSearchSerializeItem() {
        return vcgSSearchSerializeItem;
    }

    public void setVcgSSearchSerializeItem(Boolean vcgSSearchSerializeItem) {
        this.vcgSSearchSerializeItem = vcgSSearchSerializeItem;
    }

    public Boolean getVcpSExchangeRate() {
        return vcpSExchangeRate;
    }

    public void setVcpSExchangeRate(Boolean vcpSExchangeRate) {
        this.vcpSExchangeRate = vcpSExchangeRate;
    }

    public Boolean getVcgSSaveSalesOrder() {
        return vcgSSaveSalesOrder;
    }

    public void setVcgSSaveSalesOrder(Boolean vcgSSaveSalesOrder) {
        this.vcgSSaveSalesOrder = vcgSSaveSalesOrder;
    }

    public Boolean getVcgSSerializeItem() {
        return vcgSSerializeItem;
    }

    public void setVcgSSerializeItem(Boolean vcgSSerializeItem) {
        this.vcgSSerializeItem = vcgSSerializeItem;
    }

    public Boolean getVcpCSaveDeliveryOrder() {
        return vcpCSaveDeliveryOrder;
    }

    public void setVcpCSaveDeliveryOrder(Boolean vcpCSaveDeliveryOrder) {
        this.vcpCSaveDeliveryOrder = vcpCSaveDeliveryOrder;
    }

    public Boolean getVcpCInvokeDeliveryOrder() {
        return vcpCInvokeDeliveryOrder;
    }

    public void setVcpCInvokeDeliveryOrder(Boolean vcpCInvokeDeliveryOrder) {
        this.vcpCInvokeDeliveryOrder = vcpCInvokeDeliveryOrder;
    }

    public Boolean getVcpSInvokeOrder() {
        return vcpSInvokeOrder;
    }

    public void setVcpSInvokeOrder(Boolean vcpSInvokeOrder) {
        this.vcpSInvokeOrder = vcpSInvokeOrder;
    }

    public Boolean getVcpSSearchSerializeItem() {
        return vcpSSearchSerializeItem;
    }

    public void setVcpSSearchSerializeItem(Boolean vcpSSearchSerializeItem) {
        this.vcpSSearchSerializeItem = vcpSSearchSerializeItem;
    }

    public Boolean getVcpSExchangeRateAdd() {
        return vcpSExchangeRateAdd;
    }

    public void setVcpSExchangeRateAdd(Boolean vcpSExchangeRateAdd) {
        this.vcpSExchangeRateAdd = vcpSExchangeRateAdd;
    }

    public Boolean getVcpSInvokeDeliveryOrder() {
        return vcpSInvokeDeliveryOrder;
    }

    public void setVcpSInvokeDeliveryOrder(Boolean vcpSInvokeDeliveryOrder) {
        this.vcpSInvokeDeliveryOrder = vcpSInvokeDeliveryOrder;
    }

    public Boolean getVcpCDraftInvoice() {
        return vcpCDraftInvoice;
    }

    public void setVcpCDraftInvoice(Boolean vcpCDraftInvoice) {
        this.vcpCDraftInvoice = vcpCDraftInvoice;
    }

    public Boolean getVcpSInvokeQuotation() {
        return vcpSInvokeQuotation;
    }

    public void setVcpSInvokeQuotation(Boolean vcpSInvokeQuotation) {
        this.vcpSInvokeQuotation = vcpSInvokeQuotation;
    }

    public Boolean getVcpSInvokeDraft() {
        return vcpSInvokeDraft;
    }

    public void setVcpSInvokeDraft(Boolean vcpSInvokeDraft) {
        this.vcpSInvokeDraft = vcpSInvokeDraft;
    }

    public Boolean getVcpSCustomerAdd() {
        return vcpSCustomerAdd;
    }

    public void setVcpSCustomerAdd(Boolean vcpSCustomerAdd) {
        this.vcpSCustomerAdd = vcpSCustomerAdd;
    }

    public Boolean getVcpSSearchBarCode() {
        return vcpSSearchBarCode;
    }

    public void setVcpSSearchBarCode(Boolean vcpSSearchBarCode) {
        this.vcpSSearchBarCode = vcpSSearchBarCode;
    }

    public Boolean getVcpSCurrencyAdd() {
        return vcpSCurrencyAdd;
    }

    public void setVcpSCurrencyAdd(Boolean vcpSCurrencyAdd) {
        this.vcpSCurrencyAdd = vcpSCurrencyAdd;
    }

    public Boolean getVcgSAdvancePayment() {
        return vcgSAdvancePayment;
    }

    public void setVcgSAdvancePayment(Boolean vcgSAdvancePayment) {
        this.vcgSAdvancePayment = vcgSAdvancePayment;
    }

    public Boolean getVcpSRecievePayment() {
        return vcpSRecievePayment;
    }

    public void setVcpSRecievePayment(Boolean vcpSRecievePayment) {
        this.vcpSRecievePayment = vcpSRecievePayment;
    }

    public Boolean getVcgSAdvancePaymentList() {
        return vcgSAdvancePaymentList;
    }

    public void setVcgSAdvancePaymentList(Boolean vcgSAdvancePaymentList) {
        this.vcgSAdvancePaymentList = vcgSAdvancePaymentList;
    }

    public Boolean getVcgSRecievePayment() {
        return vcgSRecievePayment;
    }

    public void setVcgSRecievePayment(Boolean vcgSRecievePayment) {
        this.vcgSRecievePayment = vcgSRecievePayment;
    }

    public Boolean getVcgSDraftInvoke() {
        return vcgSDraftInvoke;
    }

    public void setVcgSDraftInvoke(Boolean vcgSDraftInvoke) {
        this.vcgSDraftInvoke = vcgSDraftInvoke;
    }

    public Boolean getVcpSSaveDeliveryOrder() {
        return vcpSSaveDeliveryOrder;
    }

    public void setVcpSSaveDeliveryOrder(Boolean vcpSSaveDeliveryOrder) {
        this.vcpSSaveDeliveryOrder = vcpSSaveDeliveryOrder;
    }

    public Boolean getVcgSSearchBarCode() {
        return vcgSSearchBarCode;
    }

    public void setVcgSSearchBarCode(Boolean vcgSSearchBarCode) {
        this.vcgSSearchBarCode = vcgSSearchBarCode;
    }

    public Boolean getVcpSAdvancePaymentList() {
        return vcpSAdvancePaymentList;
    }

    public void setVcpSAdvancePaymentList(Boolean vcpSAdvancePaymentList) {
        this.vcpSAdvancePaymentList = vcpSAdvancePaymentList;
    }

    public Boolean getVcpSSaveProforma() {
        return vcpSSaveProforma;
    }

    public void setVcpSSaveProforma(Boolean vcpSSaveProforma) {
        this.vcpSSaveProforma = vcpSSaveProforma;
    }

    public Boolean getVcgSSalesInvoke() {
        return vcgSSalesInvoke;
    }

    public void setVcgSSalesInvoke(Boolean vcgSSalesInvoke) {
        this.vcgSSalesInvoke = vcgSSalesInvoke;
    }

    public Boolean getVcpCInvokeProforma() {
        return vcpCInvokeProforma;
    }

    public void setVcpCInvokeProforma(Boolean vcpCInvokeProforma) {
        this.vcpCInvokeProforma = vcpCInvokeProforma;
    }

    public Boolean getVcpSEmployeeAdd() {
        return vcpSEmployeeAdd;
    }

    public void setVcpSEmployeeAdd(Boolean vcpSEmployeeAdd) {
        this.vcpSEmployeeAdd = vcpSEmployeeAdd;
    }

    public Boolean getVcpSShippingRefNo() {
        return vcpSShippingRefNo;
    }

    public void setVcpSShippingRefNo(Boolean vcpSShippingRefNo) {
        this.vcpSShippingRefNo = vcpSShippingRefNo;
    }

    public Boolean getVcpSInvokeProforma() {
        return vcpSInvokeProforma;
    }

    public void setVcpSInvokeProforma(Boolean vcpSInvokeProforma) {
        this.vcpSInvokeProforma = vcpSInvokeProforma;
    }

    public Boolean getVcpSReturnDeliveryOrder() {
        return vcpSReturnDeliveryOrder;
    }

    public void setVcpSReturnDeliveryOrder(Boolean vcpSReturnDeliveryOrder) {
        this.vcpSReturnDeliveryOrder = vcpSReturnDeliveryOrder;
    }

    public Boolean getVcgSCustomerAdd() {
        return vcgSCustomerAdd;
    }

    public void setVcgSCustomerAdd(Boolean vcgSCustomerAdd) {
        this.vcgSCustomerAdd = vcgSCustomerAdd;
    }

    public Boolean getVcserviceExchangeRate() {
        return vcserviceExchangeRate;
    }

    public void setVcserviceExchangeRate(Boolean vcserviceExchangeRate) {
        this.vcserviceExchangeRate = vcserviceExchangeRate;
    }

    public Boolean getVcserviceSaveDeliveryOrder() {
        return vcserviceSaveDeliveryOrder;
    }

    public void setVcserviceSaveDeliveryOrder(Boolean vcserviceSaveDeliveryOrder) {
        this.vcserviceSaveDeliveryOrder = vcserviceSaveDeliveryOrder;
    }

    public Boolean getVcserviceInvokeSalesQuotation() {
        return vcserviceInvokeSalesQuotation;
    }

    public void setVcserviceInvokeSalesQuotation(Boolean vcserviceInvokeSalesQuotation) {
        this.vcserviceInvokeSalesQuotation = vcserviceInvokeSalesQuotation;
    }

    public Boolean getVcserviceRecievePayment() {
        return vcserviceRecievePayment;
    }

    public void setVcserviceRecievePayment(Boolean vcserviceRecievePayment) {
        this.vcserviceRecievePayment = vcserviceRecievePayment;
    }

    public Boolean getVcserviceCustomer() {
        return vcserviceCustomer;
    }

    public void setVcserviceCustomer(Boolean vcserviceCustomer) {
        this.vcserviceCustomer = vcserviceCustomer;
    }

    public Boolean getVcserviceSearchSerializeItem() {
        return vcserviceSearchSerializeItem;
    }

    public void setVcserviceSearchSerializeItem(Boolean vcserviceSearchSerializeItem) {
        this.vcserviceSearchSerializeItem = vcserviceSearchSerializeItem;
    }

    public Boolean getVcserviceReturnDeliveryOrder() {
        return vcserviceReturnDeliveryOrder;
    }

    public void setVcserviceReturnDeliveryOrder(Boolean vcserviceReturnDeliveryOrder) {
        this.vcserviceReturnDeliveryOrder = vcserviceReturnDeliveryOrder;
    }

    public Boolean getVcserviceSaveserviceOrder() {
        return vcserviceSaveserviceOrder;
    }

    public void setVcserviceSaveserviceOrder(Boolean vcserviceSaveserviceOrder) {
        this.vcserviceSaveserviceOrder = vcserviceSaveserviceOrder;
    }

    public Boolean getVcserviceDebitNote() {
        return vcserviceDebitNote;
    }

    public void setVcserviceDebitNote(Boolean vcserviceDebitNote) {
        this.vcserviceDebitNote = vcserviceDebitNote;
    }

    public Boolean getVcserviceInvokeSalesOrder() {
        return vcserviceInvokeSalesOrder;
    }

    public void setVcserviceInvokeSalesOrder(Boolean vcserviceInvokeSalesOrder) {
        this.vcserviceInvokeSalesOrder = vcserviceInvokeSalesOrder;
    }

    public Boolean getVcserviceClearAll() {
        return vcserviceClearAll;
    }

    public void setVcserviceClearAll(Boolean vcserviceClearAll) {
        this.vcserviceClearAll = vcserviceClearAll;
    }

    public Boolean getVcserviceContactAdd() {
        return vcserviceContactAdd;
    }

    public void setVcserviceContactAdd(Boolean vcserviceContactAdd) {
        this.vcserviceContactAdd = vcserviceContactAdd;
    }

    public Boolean getVcserviceAgentAdd() {
        return vcserviceAgentAdd;
    }

    public void setVcserviceAgentAdd(Boolean vcserviceAgentAdd) {
        this.vcserviceAgentAdd = vcserviceAgentAdd;
    }

    public Boolean getVcserviceTndCAdd() {
        return vcserviceTndCAdd;
    }

    public void setVcserviceTndCAdd(Boolean vcserviceTndCAdd) {
        this.vcserviceTndCAdd = vcserviceTndCAdd;
    }

    public Boolean getVcserviceBarCode() {
        return vcserviceBarCode;
    }

    public void setVcserviceBarCode(Boolean vcserviceBarCode) {
        this.vcserviceBarCode = vcserviceBarCode;
    }

    public Boolean getVcserviceSaveProforma() {
        return vcserviceSaveProforma;
    }

    public void setVcserviceSaveProforma(Boolean vcserviceSaveProforma) {
        this.vcserviceSaveProforma = vcserviceSaveProforma;
    }

    public Boolean getVcserviceContact() {
        return vcserviceContact;
    }

    public void setVcserviceContact(Boolean vcserviceContact) {
        this.vcserviceContact = vcserviceContact;
    }

    public Boolean getVcserviceInvokeDraft() {
        return vcserviceInvokeDraft;
    }

    public void setVcserviceInvokeDraft(Boolean vcserviceInvokeDraft) {
        this.vcserviceInvokeDraft = vcserviceInvokeDraft;
    }

    public Boolean getVcserviceSerializeItem() {
        return vcserviceSerializeItem;
    }

    public void setVcserviceSerializeItem(Boolean vcserviceSerializeItem) {
        this.vcserviceSerializeItem = vcserviceSerializeItem;
    }

    public Boolean getVcserviceAdvancePayment() {
        return vcserviceAdvancePayment;
    }

    public void setVcserviceAdvancePayment(Boolean vcserviceAdvancePayment) {
        this.vcserviceAdvancePayment = vcserviceAdvancePayment;
    }

    public Boolean getVcserviceInvokeDeliveryOrder() {
        return vcserviceInvokeDeliveryOrder;
    }

    public void setVcserviceInvokeDeliveryOrder(Boolean vcserviceInvokeDeliveryOrder) {
        this.vcserviceInvokeDeliveryOrder = vcserviceInvokeDeliveryOrder;
    }

    public Boolean getVcserviceStateAdd() {
        return vcserviceStateAdd;
    }

    public void setVcserviceStateAdd(Boolean vcserviceStateAdd) {
        this.vcserviceStateAdd = vcserviceStateAdd;
    }

    public Boolean getVcserviceEmployeeAdd() {
        return vcserviceEmployeeAdd;
    }

    public void setVcserviceEmployeeAdd(Boolean vcserviceEmployeeAdd) {
        this.vcserviceEmployeeAdd = vcserviceEmployeeAdd;
    }

    public Boolean getVcserviceClearDraft() {
        return vcserviceClearDraft;
    }

    public void setVcserviceClearDraft(Boolean vcserviceClearDraft) {
        this.vcserviceClearDraft = vcserviceClearDraft;
    }

    public Boolean getVcserviceInvLoc() {
        return vcserviceInvLoc;
    }

    public void setVcserviceInvLoc(Boolean vcserviceInvLoc) {
        this.vcserviceInvLoc = vcserviceInvLoc;
    }

    public Boolean getVcserviceImportItem() {
        return vcserviceImportItem;
    }

    public void setVcserviceImportItem(Boolean vcserviceImportItem) {
        this.vcserviceImportItem = vcserviceImportItem;
    }

    public Boolean getVcserviceEmployee() {
        return vcserviceEmployee;
    }

    public void setVcserviceEmployee(Boolean vcserviceEmployee) {
        this.vcserviceEmployee = vcserviceEmployee;
    }

    public Boolean getVcservicedraftInvoice() {
        return vcservicedraftInvoice;
    }

    public void setVcservicedraftInvoice(Boolean vcservicedraftInvoice) {
        this.vcservicedraftInvoice = vcservicedraftInvoice;
    }

    public Boolean getVcserviceTaxType() {
        return vcserviceTaxType;
    }

    public void setVcserviceTaxType(Boolean vcserviceTaxType) {
        this.vcserviceTaxType = vcserviceTaxType;
    }

    public Boolean getVcserviceCurrencyAdd() {
        return vcserviceCurrencyAdd;
    }

    public void setVcserviceCurrencyAdd(Boolean vcserviceCurrencyAdd) {
        this.vcserviceCurrencyAdd = vcserviceCurrencyAdd;
    }

    public Boolean getVcserviceExchangeRateAdd() {
        return vcserviceExchangeRateAdd;
    }

    public void setVcserviceExchangeRateAdd(Boolean vcserviceExchangeRateAdd) {
        this.vcserviceExchangeRateAdd = vcserviceExchangeRateAdd;
    }

    public Boolean getVcserviceAdvancePaymentList() {
        return vcserviceAdvancePaymentList;
    }

    public void setVcserviceAdvancePaymentList(Boolean vcserviceAdvancePaymentList) {
        this.vcserviceAdvancePaymentList = vcserviceAdvancePaymentList;
    }

    public Boolean getVcserviceSearchBarCode() {
        return vcserviceSearchBarCode;
    }

    public void setVcserviceSearchBarCode(Boolean vcserviceSearchBarCode) {
        this.vcserviceSearchBarCode = vcserviceSearchBarCode;
    }

    public Boolean getVcserviceSaveSalesQuotation() {
        return vcserviceSaveSalesQuotation;
    }

    public void setVcserviceSaveSalesQuotation(Boolean vcserviceSaveSalesQuotation) {
        this.vcserviceSaveSalesQuotation = vcserviceSaveSalesQuotation;
    }

    public Boolean getVcservicePrintList() {
        return vcservicePrintList;
    }

    public void setVcservicePrintList(Boolean vcservicePrintList) {
        this.vcservicePrintList = vcservicePrintList;
    }

    public Boolean getVcserviceCurrency() {
        return vcserviceCurrency;
    }

    public void setVcserviceCurrency(Boolean vcserviceCurrency) {
        this.vcserviceCurrency = vcserviceCurrency;
    }

    public Boolean getVcserviceInvokeProforma() {
        return vcserviceInvokeProforma;
    }

    public void setVcserviceInvokeProforma(Boolean vcserviceInvokeProforma) {
        this.vcserviceInvokeProforma = vcserviceInvokeProforma;
    }

    public Boolean getVcserviceInvLocAdd() {
        return vcserviceInvLocAdd;
    }

    public void setVcserviceInvLocAdd(Boolean vcserviceInvLocAdd) {
        this.vcserviceInvLocAdd = vcserviceInvLocAdd;
    }

    public Boolean getVcserviceCheckOut() {
        return vcserviceCheckOut;
    }

    public void setVcserviceCheckOut(Boolean vcserviceCheckOut) {
        this.vcserviceCheckOut = vcserviceCheckOut;
    }

    public Boolean getVcserviceShippingRefNo() {
        return vcserviceShippingRefNo;
    }

    public void setVcserviceShippingRefNo(Boolean vcserviceShippingRefNo) {
        this.vcserviceShippingRefNo = vcserviceShippingRefNo;
    }

    public Boolean getVcserviceReturn() {
        return vcserviceReturn;
    }

    public void setVcserviceReturn(Boolean vcserviceReturn) {
        this.vcserviceReturn = vcserviceReturn;
    }

    public Boolean getVcserviceRemoveItem() {
        return vcserviceRemoveItem;
    }

    public void setVcserviceRemoveItem(Boolean vcserviceRemoveItem) {
        this.vcserviceRemoveItem = vcserviceRemoveItem;
    }

    public Boolean getVccPConfiguratorPaymentMethod() {
        return vccPConfiguratorPaymentMethod;
    }

    public void setVccPConfiguratorPaymentMethod(Boolean vccPConfiguratorPaymentMethod) {
        this.vccPConfiguratorPaymentMethod = vccPConfiguratorPaymentMethod;
    }

    public Boolean getVccontactCustomer() {
        return vccontactCustomer;
    }

    public void setVccontactCustomer(Boolean vccontactCustomer) {
        this.vccontactCustomer = vccontactCustomer;
    }

    public Boolean getVccPConfiguratorConfiguration() {
        return vccPConfiguratorConfiguration;
    }

    public void setVccPConfiguratorConfiguration(Boolean vccPConfiguratorConfiguration) {
        this.vccPConfiguratorConfiguration = vccPConfiguratorConfiguration;
    }

    public Boolean getVccontrolPanelAMag() {
        return vccontrolPanelAMag;
    }

    public void setVccontrolPanelAMag(Boolean vccontrolPanelAMag) {
        this.vccontrolPanelAMag = vccontrolPanelAMag;
    }

    public Boolean getVccontrolPanelAMbudget() {
        return vccontrolPanelAMbudget;
    }

    public void setVccontrolPanelAMbudget(Boolean vccontrolPanelAMbudget) {
        this.vccontrolPanelAMbudget = vccontrolPanelAMbudget;
    }

    public Boolean getVccontrolPanelCompanySetup() {
        return vccontrolPanelCompanySetup;
    }

    public void setVccontrolPanelCompanySetup(Boolean vccontrolPanelCompanySetup) {
        this.vccontrolPanelCompanySetup = vccontrolPanelCompanySetup;
    }

    public Boolean getVccontrolPanelDSfs() {
        return vccontrolPanelDSfs;
    }

    public void setVccontrolPanelDSfs(Boolean vccontrolPanelDSfs) {
        this.vccontrolPanelDSfs = vccontrolPanelDSfs;
    }

    public Boolean getVccontrolPanelDSlayout() {
        return vccontrolPanelDSlayout;
    }

    public void setVccontrolPanelDSlayout(Boolean vccontrolPanelDSlayout) {
        this.vccontrolPanelDSlayout = vccontrolPanelDSlayout;
    }

    public Boolean getVccontrolPanelDStermsCond() {
        return vccontrolPanelDStermsCond;
    }

    public void setVccontrolPanelDStermsCond(Boolean vccontrolPanelDStermsCond) {
        this.vccontrolPanelDStermsCond = vccontrolPanelDStermsCond;
    }

    public Boolean getVccontrolPanelConfigurator() {
        return vccontrolPanelConfigurator;
    }

    public void setVccontrolPanelConfigurator(Boolean vccontrolPanelConfigurator) {
        this.vccontrolPanelConfigurator = vccontrolPanelConfigurator;
    }

    public Boolean getVccontrolPanelDisplaySetup() {
        return vccontrolPanelDisplaySetup;
    }

    public void setVccontrolPanelDisplaySetup(Boolean vccontrolPanelDisplaySetup) {
        this.vccontrolPanelDisplaySetup = vccontrolPanelDisplaySetup;
    }

    public Boolean getVccontrolPanelLastYearFigure() {
        return vccontrolPanelLastYearFigure;
    }

    public void setVccontrolPanelLastYearFigure(Boolean vccontrolPanelLastYearFigure) {
        this.vccontrolPanelLastYearFigure = vccontrolPanelLastYearFigure;
    }

    public Boolean getVccontrolPanelOBofBS() {
        return vccontrolPanelOBofBS;
    }

    public void setVccontrolPanelOBofBS(Boolean vccontrolPanelOBofBS) {
        this.vccontrolPanelOBofBS = vccontrolPanelOBofBS;
    }

    public Boolean getVccPConfiguratorProjectTitle() {
        return vccPConfiguratorProjectTitle;
    }

    public void setVccPConfiguratorProjectTitle(Boolean vccPConfiguratorProjectTitle) {
        this.vccPConfiguratorProjectTitle = vccPConfiguratorProjectTitle;
    }

    public Boolean getVccontrolPanelMonthEnd() {
        return vccontrolPanelMonthEnd;
    }

    public void setVccontrolPanelMonthEnd(Boolean vccontrolPanelMonthEnd) {
        this.vccontrolPanelMonthEnd = vccontrolPanelMonthEnd;
    }

    public Boolean getVccontrolPanelOBofINV() {
        return vccontrolPanelOBofINV;
    }

    public void setVccontrolPanelOBofINV(Boolean vccontrolPanelOBofINV) {
        this.vccontrolPanelOBofINV = vccontrolPanelOBofINV;
    }

    public Boolean getVccPCompanySetupFY() {
        return vccPCompanySetupFY;
    }

    public void setVccPCompanySetupFY(Boolean vccPCompanySetupFY) {
        this.vccPCompanySetupFY = vccPCompanySetupFY;
    }

    public Boolean getVccontactSupplier() {
        return vccontactSupplier;
    }

    public void setVccontactSupplier(Boolean vccontactSupplier) {
        this.vccontactSupplier = vccontactSupplier;
    }

    public Boolean getVccPCompanySetupHSN() {
        return vccPCompanySetupHSN;
    }

    public void setVccPCompanySetupHSN(Boolean vccPCompanySetupHSN) {
        this.vccPCompanySetupHSN = vccPCompanySetupHSN;
    }

    public Boolean getVccontrolPanelAMcoa() {
        return vccontrolPanelAMcoa;
    }

    public void setVccontrolPanelAMcoa(Boolean vccontrolPanelAMcoa) {
        this.vccontrolPanelAMcoa = vccontrolPanelAMcoa;
    }

    public Boolean getVccPConfiguratorAgent() {
        return vccPConfiguratorAgent;
    }

    public void setVccPConfiguratorAgent(Boolean vccPConfiguratorAgent) {
        this.vccPConfiguratorAgent = vccPConfiguratorAgent;
    }

    public Boolean getVccPConfiguratorEmailserver() {
        return vccPConfiguratorEmailserver;
    }

    public void setVccPConfiguratorEmailserver(Boolean vccPConfiguratorEmailserver) {
        this.vccPConfiguratorEmailserver = vccPConfiguratorEmailserver;
    }

    public Boolean getVccPCompanySetupCI() {
        return vccPCompanySetupCI;
    }

    public void setVccPCompanySetupCI(Boolean vccPCompanySetupCI) {
        this.vccPCompanySetupCI = vccPCompanySetupCI;
    }

    public Boolean getVccPConfiguratorEmployee() {
        return vccPConfiguratorEmployee;
    }

    public void setVccPConfiguratorEmployee(Boolean vccPConfiguratorEmployee) {
        this.vccPConfiguratorEmployee = vccPConfiguratorEmployee;
    }

    public Boolean getVccPCompanySetupCA() {
        return vccPCompanySetupCA;
    }

    public void setVccPCompanySetupCA(Boolean vccPCompanySetupCA) {
        this.vccPCompanySetupCA = vccPCompanySetupCA;
    }

    public Boolean getVccPConfiguratorCurrency() {
        return vccPConfiguratorCurrency;
    }

    public void setVccPConfiguratorCurrency(Boolean vccPConfiguratorCurrency) {
        this.vccPConfiguratorCurrency = vccPConfiguratorCurrency;
    }

    public Boolean getVccPConfiguratorBank() {
        return vccPConfiguratorBank;
    }

    public void setVccPConfiguratorBank(Boolean vccPConfiguratorBank) {
        this.vccPConfiguratorBank = vccPConfiguratorBank;
    }

    public Boolean getVccPConfiguratorExchangeRate() {
        return vccPConfiguratorExchangeRate;
    }

    public void setVccPConfiguratorExchangeRate(Boolean vccPConfiguratorExchangeRate) {
        this.vccPConfiguratorExchangeRate = vccPConfiguratorExchangeRate;
    }

    public Boolean getVccontactContacts() {
        return vccontactContacts;
    }

    public void setVccontactContacts(Boolean vccontactContacts) {
        this.vccontactContacts = vccontactContacts;
    }

    public Boolean getVcdashboardTotalPayable() {
        return vcdashboardTotalPayable;
    }

    public void setVcdashboardTotalPayable(Boolean vcdashboardTotalPayable) {
        this.vcdashboardTotalPayable = vcdashboardTotalPayable;
    }

    public Boolean getVccontrolPanelYearEnd() {
        return vccontrolPanelYearEnd;
    }

    public void setVccontrolPanelYearEnd(Boolean vccontrolPanelYearEnd) {
        this.vccontrolPanelYearEnd = vccontrolPanelYearEnd;
    }

    public Boolean getVccontrolPanelUASat() {
        return vccontrolPanelUASat;
    }

    public void setVccontrolPanelUASat(Boolean vccontrolPanelUASat) {
        this.vccontrolPanelUASat = vccontrolPanelUASat;
    }

    public Boolean getVcinventoryCategory() {
        return vcinventoryCategory;
    }

    public void setVcinventoryCategory(Boolean vcinventoryCategory) {
        this.vcinventoryCategory = vcinventoryCategory;
    }

    public Boolean getVcinventoryInventoryLocation() {
        return vcinventoryInventoryLocation;
    }

    public void setVcinventoryInventoryLocation(Boolean vcinventoryInventoryLocation) {
        this.vcinventoryInventoryLocation = vcinventoryInventoryLocation;
    }

    public Boolean getVcinventoryItem() {
        return vcinventoryItem;
    }

    public void setVcinventoryItem(Boolean vcinventoryItem) {
        this.vcinventoryItem = vcinventoryItem;
    }

    public Boolean getVccontrolPanelOBofPI() {
        return vccontrolPanelOBofPI;
    }

    public void setVccontrolPanelOBofPI(Boolean vccontrolPanelOBofPI) {
        this.vccontrolPanelOBofPI = vccontrolPanelOBofPI;
    }

    public Boolean getVcdashboardCashInHand() {
        return vcdashboardCashInHand;
    }

    public void setVcdashboardCashInHand(Boolean vcdashboardCashInHand) {
        this.vcdashboardCashInHand = vcdashboardCashInHand;
    }

    public Boolean getVcdashboardExtra1() {
        return vcdashboardExtra1;
    }

    public void setVcdashboardExtra1(Boolean vcdashboardExtra1) {
        this.vcdashboardExtra1 = vcdashboardExtra1;
    }

    public Boolean getVccontrolPanelOBofSI() {
        return vccontrolPanelOBofSI;
    }

    public void setVccontrolPanelOBofSI(Boolean vccontrolPanelOBofSI) {
        this.vccontrolPanelOBofSI = vccontrolPanelOBofSI;
    }

    public Boolean getVcdashboardTotalReceivable() {
        return vcdashboardTotalReceivable;
    }

    public void setVcdashboardTotalReceivable(Boolean vcdashboardTotalReceivable) {
        this.vcdashboardTotalReceivable = vcdashboardTotalReceivable;
    }

    public Boolean getVcgtJournalEntry() {
        return vcgtJournalEntry;
    }

    public void setVcgtJournalEntry(Boolean vcgtJournalEntry) {
        this.vcgtJournalEntry = vcgtJournalEntry;
    }

    public Boolean getVcdashboardExtra2() {
        return vcdashboardExtra2;
    }

    public void setVcdashboardExtra2(Boolean vcdashboardExtra2) {
        this.vcdashboardExtra2 = vcdashboardExtra2;
    }

    public Boolean getVcinventoryBrand() {
        return vcinventoryBrand;
    }

    public void setVcinventoryBrand(Boolean vcinventoryBrand) {
        this.vcinventoryBrand = vcinventoryBrand;
    }

    public Boolean getVcpurchaseAdvancePaymentList() {
        return vcpurchaseAdvancePaymentList;
    }

    public void setVcpurchaseAdvancePaymentList(Boolean vcpurchaseAdvancePaymentList) {
        this.vcpurchaseAdvancePaymentList = vcpurchaseAdvancePaymentList;
    }

    public Boolean getVcpurchaseAdvancePayment() {
        return vcpurchaseAdvancePayment;
    }

    public void setVcpurchaseAdvancePayment(Boolean vcpurchaseAdvancePayment) {
        this.vcpurchaseAdvancePayment = vcpurchaseAdvancePayment;
    }

    public Boolean getVcdashboardDateTime() {
        return vcdashboardDateTime;
    }

    public void setVcdashboardDateTime(Boolean vcdashboardDateTime) {
        this.vcdashboardDateTime = vcdashboardDateTime;
    }

    public Boolean getVcinventoryPurchasePricing() {
        return vcinventoryPurchasePricing;
    }

    public void setVcinventoryPurchasePricing(Boolean vcinventoryPurchasePricing) {
        this.vcinventoryPurchasePricing = vcinventoryPurchasePricing;
    }

    public Boolean getVccontrolPanelUAS() {
        return vccontrolPanelUAS;
    }

    public void setVccontrolPanelUAS(Boolean vccontrolPanelUAS) {
        this.vccontrolPanelUAS = vccontrolPanelUAS;
    }

    public Boolean getVcinventorySalesPricing() {
        return vcinventorySalesPricing;
    }

    public void setVcinventorySalesPricing(Boolean vcinventorySalesPricing) {
        this.vcinventorySalesPricing = vcinventorySalesPricing;
    }

    public Boolean getVccontrolPanelUASuas() {
        return vccontrolPanelUASuas;
    }

    public void setVccontrolPanelUASuas(Boolean vccontrolPanelUASuas) {
        this.vccontrolPanelUASuas = vccontrolPanelUASuas;
    }

    public Boolean getVcdashboardCashInBank() {
        return vcdashboardCashInBank;
    }

    public void setVcdashboardCashInBank(Boolean vcdashboardCashInBank) {
        this.vcdashboardCashInBank = vcdashboardCashInBank;
    }

    public Boolean getVcinventorySalesDiscountConfig() {
        return vcinventorySalesDiscountConfig;
    }

    public void setVcinventorySalesDiscountConfig(Boolean vcinventorySalesDiscountConfig) {
        this.vcinventorySalesDiscountConfig = vcinventorySalesDiscountConfig;
    }

    public Boolean getVccPConfiguratorVersionControl() {
        return vccPConfiguratorVersionControl;
    }

    public void setVccPConfiguratorVersionControl(Boolean vccPConfiguratorVersionControl) {
        this.vccPConfiguratorVersionControl = vccPConfiguratorVersionControl;
    }

    public VcuserAccessRightsDTO getVcuserAccessRightsDTO() {
        return vcuserAccessRightsDTO;
    }

    public void setVcuserAccessRightsDTO(VcuserAccessRightsDTO vcuserAccessRightsDTO) {
        this.vcuserAccessRightsDTO = vcuserAccessRightsDTO;
    }

    public Boolean getVccPConfiguratorShippingMethod() {
        return vccPConfiguratorShippingMethod;
    }

    public void setVccPConfiguratorShippingMethod(Boolean vccPConfiguratorShippingMethod) {
        this.vccPConfiguratorShippingMethod = vccPConfiguratorShippingMethod;
    }

    public Boolean getVcreportAnalysisAccountBalance() {
        return vcreportAnalysisAccountBalance;
    }

    public void setVcreportAnalysisAccountBalance(Boolean vcreportAnalysisAccountBalance) {
        this.vcreportAnalysisAccountBalance = vcreportAnalysisAccountBalance;
    }

    public Boolean getVcpurContact() {
        return vcpurContact;
    }

    public void setVcpurContact(Boolean vcpurContact) {
        this.vcpurContact = vcpurContact;
    }

    public Boolean getVcsalesSMAdd() {
        return vcsalesSMAdd;
    }

    public void setVcsalesSMAdd(Boolean vcsalesSMAdd) {
        this.vcsalesSMAdd = vcsalesSMAdd;
    }

    public Boolean getVcpurTaxType() {
        return vcpurTaxType;
    }

    public void setVcpurTaxType(Boolean vcpurTaxType) {
        this.vcpurTaxType = vcpurTaxType;
    }

    public Boolean getVcsalesRefNo() {
        return vcsalesRefNo;
    }

    public void setVcsalesRefNo(Boolean vcsalesRefNo) {
        this.vcsalesRefNo = vcsalesRefNo;
    }

    public Boolean getVcpurBarCode() {
        return vcpurBarCode;
    }

    public void setVcpurBarCode(Boolean vcpurBarCode) {
        this.vcpurBarCode = vcpurBarCode;
    }

    public Boolean getVcpurState() {
        return vcpurState;
    }

    public void setVcpurState(Boolean vcpurState) {
        this.vcpurState = vcpurState;
    }

    public Boolean getVcsalesInvLoc() {
        return vcsalesInvLoc;
    }

    public void setVcsalesInvLoc(Boolean vcsalesInvLoc) {
        this.vcsalesInvLoc = vcsalesInvLoc;
    }

    public Boolean getVcsalesMore() {
        return vcsalesMore;
    }

    public void setVcsalesMore(Boolean vcsalesMore) {
        this.vcsalesMore = vcsalesMore;
    }

    public Boolean getVcpurStateAdd() {
        return vcpurStateAdd;
    }

    public void setVcpurStateAdd(Boolean vcpurStateAdd) {
        this.vcpurStateAdd = vcpurStateAdd;
    }

    public Boolean getVcsalesTandC() {
        return vcsalesTandC;
    }

    public void setVcsalesTandC(Boolean vcsalesTandC) {
        this.vcsalesTandC = vcsalesTandC;
    }

    public Boolean getVcsalesCCAdd() {
        return vcsalesCCAdd;
    }

    public void setVcsalesCCAdd(Boolean vcsalesCCAdd) {
        this.vcsalesCCAdd = vcsalesCCAdd;
    }

    public Boolean getVcsalesSM() {
        return vcsalesSM;
    }

    public void setVcsalesSM(Boolean vcsalesSM) {
        this.vcsalesSM = vcsalesSM;
    }

    public Boolean getVcsalesCC() {
        return vcsalesCC;
    }

    public void setVcsalesCC(Boolean vcsalesCC) {
        this.vcsalesCC = vcsalesCC;
    }

    public Boolean getVcpurMore() {
        return vcpurMore;
    }

    public void setVcpurMore(Boolean vcpurMore) {
        this.vcpurMore = vcpurMore;
    }

    public Boolean getVcsalesState() {
        return vcsalesState;
    }

    public void setVcsalesState(Boolean vcsalesState) {
        this.vcsalesState = vcsalesState;
    }

    public Boolean getVcsalesAgent() {
        return vcsalesAgent;
    }

    public void setVcsalesAgent(Boolean vcsalesAgent) {
        this.vcsalesAgent = vcsalesAgent;
    }

    public Boolean getVcpurInvLoc() {
        return vcpurInvLoc;
    }

    public void setVcpurInvLoc(Boolean vcpurInvLoc) {
        this.vcpurInvLoc = vcpurInvLoc;
    }

    public Boolean getVctaxTax() {
        return vctaxTax;
    }

    public void setVctaxTax(Boolean vctaxTax) {
        this.vctaxTax = vctaxTax;
    }

    public Boolean getVcgstMalGSTSR() {
        return vcgstMalGSTSR;
    }

    public void setVcgstMalGSTSR(Boolean vcgstMalGSTSR) {
        this.vcgstMalGSTSR = vcgstMalGSTSR;
    }

    public Boolean getVcpurTandC() {
        return vcpurTandC;
    }

    public void setVcpurTandC(Boolean vcpurTandC) {
        this.vcpurTandC = vcpurTandC;
    }

    public Boolean getVctaxTaxtype() {
        return vctaxTaxtype;
    }

    public void setVctaxTaxtype(Boolean vctaxTaxtype) {
        this.vctaxTaxtype = vctaxTaxtype;
    }

    public Boolean getVcpurCurrency() {
        return vcpurCurrency;
    }

    public void setVcpurCurrency(Boolean vcpurCurrency) {
        this.vcpurCurrency = vcpurCurrency;
    }

    public Boolean getVcpurSMAdd() {
        return vcpurSMAdd;
    }

    public void setVcpurSMAdd(Boolean vcpurSMAdd) {
        this.vcpurSMAdd = vcpurSMAdd;
    }

    public Boolean getVcpurCCAdd() {
        return vcpurCCAdd;
    }

    public void setVcpurCCAdd(Boolean vcpurCCAdd) {
        this.vcpurCCAdd = vcpurCCAdd;
    }

    public Boolean getVcpurEmployee() {
        return vcpurEmployee;
    }

    public void setVcpurEmployee(Boolean vcpurEmployee) {
        this.vcpurEmployee = vcpurEmployee;
    }

    public Boolean getVcpurRefNo() {
        return vcpurRefNo;
    }

    public void setVcpurRefNo(Boolean vcpurRefNo) {
        this.vcpurRefNo = vcpurRefNo;
    }

    public Boolean getVcpurAgent() {
        return vcpurAgent;
    }

    public void setVcpurAgent(Boolean vcpurAgent) {
        this.vcpurAgent = vcpurAgent;
    }

    public Boolean getVcpurTndCAdd() {
        return vcpurTndCAdd;
    }

    public void setVcpurTndCAdd(Boolean vcpurTndCAdd) {
        this.vcpurTndCAdd = vcpurTndCAdd;
    }

    public Boolean getVcpurCC() {
        return vcpurCC;
    }

    public void setVcpurCC(Boolean vcpurCC) {
        this.vcpurCC = vcpurCC;
    }

    public Boolean getVccrm() {
        return vccrm;
    }

    public void setVccrm(Boolean vccrm) {
        this.vccrm = vccrm;
    }

    public Boolean getVcpurAgentAdd() {
        return vcpurAgentAdd;
    }

    public void setVcpurAgentAdd(Boolean vcpurAgentAdd) {
        this.vcpurAgentAdd = vcpurAgentAdd;
    }

    public Boolean getVcpurSM() {
        return vcpurSM;
    }

    public void setVcpurSM(Boolean vcpurSM) {
        this.vcpurSM = vcpurSM;
    }

    public Boolean getVcevent() {
        return vcevent;
    }

    public void setVcevent(Boolean vcevent) {
        this.vcevent = vcevent;
    }

    public Boolean getVcleads() {
        return vcleads;
    }

    public void setVcleads(Boolean vcleads) {
        this.vcleads = vcleads;
    }

    public Boolean getVccontrolPanelAccountMaintenance() {
        return vccontrolPanelAccountMaintenance;
    }

    public void setVccontrolPanelAccountMaintenance(Boolean vccontrolPanelAccountMaintenance) {
        this.vccontrolPanelAccountMaintenance = vccontrolPanelAccountMaintenance;
    }

    public Boolean getVcinventoryInventoryMovementType() {
        return vcinventoryInventoryMovementType;
    }

    public void setVcinventoryInventoryMovementType(Boolean vcinventoryInventoryMovementType) {
        this.vcinventoryInventoryMovementType = vcinventoryInventoryMovementType;
    }

    public Boolean getVcpurchaseSavePurchaseQuotation() {
        return vcpurchaseSavePurchaseQuotation;
    }

    public void setVcpurchaseSavePurchaseQuotation(Boolean vcpurchaseSavePurchaseQuotation) {
        this.vcpurchaseSavePurchaseQuotation = vcpurchaseSavePurchaseQuotation;
    }

    public Boolean getVcpurchaseInvokePurchaseQuotation() {
        return vcpurchaseInvokePurchaseQuotation;
    }

    public void setVcpurchaseInvokePurchaseQuotation(Boolean vcpurchaseInvokePurchaseQuotation) {
        this.vcpurchaseInvokePurchaseQuotation = vcpurchaseInvokePurchaseQuotation;
    }

    public Boolean getVccPConfiguratorLabelPrintConfig() {
        return vccPConfiguratorLabelPrintConfig;
    }

    public void setVccPConfiguratorLabelPrintConfig(Boolean vccPConfiguratorLabelPrintConfig) {
        this.vccPConfiguratorLabelPrintConfig = vccPConfiguratorLabelPrintConfig;
    }

    public Boolean getVcmanufacturingAdvItemCountAdjust() {
        return vcmanufacturingAdvItemCountAdjust;
    }

    public void setVcmanufacturingAdvItemCountAdjust(Boolean vcmanufacturingAdvItemCountAdjust) {
        this.vcmanufacturingAdvItemCountAdjust = vcmanufacturingAdvItemCountAdjust;
    }
}
