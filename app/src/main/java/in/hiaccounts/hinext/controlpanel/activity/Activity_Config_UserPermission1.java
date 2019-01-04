package in.hiaccounts.hinext.controlpanel.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.Activity_Login;
import in.hiaccounts.hinext.application.model.PageLoadDataForAll;
import in.hiaccounts.hinext.controlpanel.model.useraccount_setup.AddUserAccessPermissionData;
import in.hiaccounts.hinext.controlpanel.model.useraccount_setup.UserAccessPermissionData;
import in.hiaccounts.hinext.controlpanel.model.useraccount_setup.UserAccountData;
import in.hiaccounts.hinext.controlpanel.model.useraccount_setup.UserPermissionData;
import in.hiaccounts.hinext.controlpanel.model.useraccount_setup.VCuserAccessRightsDTO;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;

import static in.hiaccounts.R.string.response_error;

public class Activity_Config_UserPermission1 extends AppCompatActivity {

    UserAccessPermissionData userPermissions;
    UserAccountData userDetail;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.cbDashBoard)
    CheckBox cbDashBoard;
    @BindView(R.id.cbDashBoardDateTime)
    CheckBox cbDashBoardDateTime;
    @BindView(R.id.cbDashBoardCashInHand)
    CheckBox cbDashBoardCashInHand;
    @BindView(R.id.cbDashBoardCashInBank)
    CheckBox cbDashBoardCashInBank;
    @BindView(R.id.cbDashBoardTotalRecievable)
    CheckBox cbDashBoardTotalRecievable;
    @BindView(R.id.cbDashBoardTotalPayable)
    CheckBox cbDashBoardTotalPayable;
    @BindView(R.id.llDashBoard)
    LinearLayout llDashBoard;
    @BindView(R.id.cbSales)
    CheckBox cbSales;
    @BindView(R.id.cbSalesItems)
    CheckBox cbSalesItems;
    @BindView(R.id.cbSalesAddCustomer)
    CheckBox cbSalesAddCustomer;
    @BindView(R.id.cbSalesSaveDraft)
    CheckBox cbSalesSaveDraft;
    @BindView(R.id.cbSalesClearDraft)
    CheckBox cbSalesClearDraft;
    @BindView(R.id.cbSalesPrintList)
    CheckBox cbSalesPrintList;
    @BindView(R.id.cbSalesAdvacnePayment)
    CheckBox cbSalesAdvacnePayment;
    @BindView(R.id.cbSalesSaveSalesOrder)
    CheckBox cbSalesSaveSalesOrder;
    @BindView(R.id.cbSalesAdvancePrintList)
    CheckBox cbSalesAdvancePrintList;
    @BindView(R.id.cbSalesInvokeSalesOrder)
    CheckBox cbSalesInvokeSalesOrder;
    @BindView(R.id.cbSalesClearAll)
    CheckBox cbSalesClearAll;
    @BindView(R.id.cbSalesCheckout)
    CheckBox cbSalesCheckout;
    @BindView(R.id.cbSalesReturn)
    CheckBox cbSalesReturn;
    @BindView(R.id.cbSalesRecievePayment)
    CheckBox cbSalesRecievePayment;
    @BindView(R.id.cbSalesInvokeDraft)
    CheckBox cbSalesInvokeDraft;
    @BindView(R.id.cbSalesSearchBarCode)
    CheckBox cbSalesSearchBarCode;
    @BindView(R.id.cbSalesBarCode)
    CheckBox cbSalesBarCode;
    @BindView(R.id.cbSalesSearchSerializeItem)
    CheckBox cbSalesSearchSerializeItem;
    @BindView(R.id.cbSalesSerializeItem)
    CheckBox cbSalesSerializeItem;
    @BindView(R.id.cbSalesCustomer)
    CheckBox cbSalesCustomer;
    @BindView(R.id.cbSalesImportItem)
    CheckBox cbSalesImportItem;
    @BindView(R.id.cbSalesTaxType)
    CheckBox cbSalesTaxType;
    @BindView(R.id.cbSalesInvLocAdd)
    CheckBox cbSalesInvLocAdd;
    @BindView(R.id.cbSalesInvLoc)
    CheckBox cbSalesInvLoc;
    @BindView(R.id.cbSalesStateAdd)
    CheckBox cbSalesStateAdd;
    @BindView(R.id.cbSalesState)
    CheckBox cbSalesState;
    @BindView(R.id.cbSalesRemoveItem)
    CheckBox cbSalesRemoveItem;
    @BindView(R.id.cbSalesMore)
    CheckBox cbSalesMore;
    @BindView(R.id.cbSalesContactAdd)
    CheckBox cbSalesContactAdd;
    @BindView(R.id.cbSalesContact)
    CheckBox cbSalesContact;
    @BindView(R.id.cbSalesAgentAdd)
    CheckBox cbSalesAgentAdd;
    @BindView(R.id.cbSalesAgent)
    CheckBox cbSalesAgent;
    @BindView(R.id.cbSalesCurrencyAdd)
    CheckBox cbSalesCurrencyAdd;
    @BindView(R.id.cbSalesCurrency)
    CheckBox cbSalesCurrency;
    @BindView(R.id.cbSalesEmpoloyeeAdd)
    CheckBox cbSalesEmpoloyeeAdd;
    @BindView(R.id.cbSalesEmpoloyee)
    CheckBox cbSalesEmpoloyee;
    @BindView(R.id.cbSalesExchangeRateAdd)
    CheckBox cbSalesExchangeRateAdd;
    @BindView(R.id.cbSalesExchangeRate)
    CheckBox cbSalesExchangeRate;
    @BindView(R.id.cbSalesTndCAdd)
    CheckBox cbSalesTndCAdd;
    @BindView(R.id.cbSalesTandc)
    CheckBox cbSalesTandc;
    @BindView(R.id.cbSalesCostCentreAdd)
    CheckBox cbSalesCostCentreAdd;
    @BindView(R.id.cbSalesCostCentre)
    CheckBox cbSalesCostCentre;
    @BindView(R.id.cbSalesShippingMethodAdd)
    CheckBox cbSalesShippingMethodAdd;
    @BindView(R.id.cbSalesShippingMethod)
    CheckBox cbSalesShippingMethod;
    @BindView(R.id.cbSalesShippingRefNo)
    CheckBox cbSalesShippingRefNo;
    @BindView(R.id.cbSalesReferenceNo)
    CheckBox cbSalesReferenceNo;
    @BindView(R.id.cbSalesProforma)
    CheckBox cbSalesProforma;
    @BindView(R.id.cbSalesInvokeProforma)
    CheckBox cbSalesInvokeProforma;
    @BindView(R.id.cbSalesDeliveryOrder)
    CheckBox cbSalesDeliveryOrder;
    @BindView(R.id.cbSalesInvokeDelivery)
    CheckBox cbSalesInvokeDelivery;
    @BindView(R.id.cbSalesReturnDelivery)
    CheckBox cbSalesReturnDelivery;
    @BindView(R.id.cbSalesDebitNote)
    CheckBox cbSalesDebitNote;
    @BindView(R.id.cbSalesDraftInvoice)
    CheckBox cbSalesDraftInvoice;
    @BindView(R.id.cbSalesQuotation)
    CheckBox cbSalesQuotation;
    @BindView(R.id.cbSalesInvokeQuotation)
    CheckBox cbSalesInvokeQuotation;
    @BindView(R.id.llSales)
    LinearLayout llSales;
    @BindView(R.id.cbPurchase)
    CheckBox cbPurchase;
    @BindView(R.id.cbPurchaseItems)
    CheckBox cbPurchaseItems;
    @BindView(R.id.cbPurchaseSupplier)
    CheckBox cbPurchaseSupplier;
    @BindView(R.id.cbPurchasePrintList)
    CheckBox cbPurchasePrintList;
    @BindView(R.id.cbPurchaseAdvancePayment)
    CheckBox cbPurchaseAdvancePayment;
    @BindView(R.id.cbPurchaseSavePurchaseOrder)
    CheckBox cbPurchaseSavePurchaseOrder;
    @BindView(R.id.cbPruchaseAdvPaymentList)
    CheckBox cbPruchaseAdvPaymentList;
    @BindView(R.id.cbPurchaseInvokePo)
    CheckBox cbPurchaseInvokePo;
    @BindView(R.id.cbPurchaseClearAll)
    CheckBox cbPurchaseClearAll;
    @BindView(R.id.cbPurchaseCheckOut)
    CheckBox cbPurchaseCheckOut;
    @BindView(R.id.cbPurchasaeReturn)
    CheckBox cbPurchasaeReturn;
    @BindView(R.id.cbPurchaseSupplierPayment)
    CheckBox cbPurchaseSupplierPayment;
    @BindView(R.id.cbPurchaseSelfBilled)
    CheckBox cbPurchaseSelfBilled;
    @BindView(R.id.cbPurchasaeSearchBarcode)
    CheckBox cbPurchasaeSearchBarcode;
    @BindView(R.id.cbPurchaseBarcode)
    CheckBox cbPurchaseBarcode;
    @BindView(R.id.cbPurchaseMore)
    CheckBox cbPurchaseMore;
    @BindView(R.id.cbPurchasaeCreditNote)
    CheckBox cbPurchasaeCreditNote;
    @BindView(R.id.cbPurchaseRemoveItem)
    CheckBox cbPurchaseRemoveItem;
    @BindView(R.id.cbPurchaseInVokeRecievedItem)
    CheckBox cbPurchaseInVokeRecievedItem;
    @BindView(R.id.cbPurchasaeReturnReceivedItem)
    CheckBox cbPurchasaeReturnReceivedItem;
    @BindView(R.id.cbPurchaseSaveQuotation)
    CheckBox cbPurchaseSaveQuotation;
    @BindView(R.id.cbPurchaseInvokeQuotation)
    CheckBox cbPurchaseInvokeQuotation;
    @BindView(R.id.cbPurchasaeTaxType)
    CheckBox cbPurchasaeTaxType;
    @BindView(R.id.cbPurchaseInventoryLocationAdd)
    CheckBox cbPurchaseInventoryLocationAdd;
    @BindView(R.id.cbPurchaseInventoryLocation)
    CheckBox cbPurchaseInventoryLocation;
    @BindView(R.id.cbPurchasaeStateAdd)
    CheckBox cbPurchasaeStateAdd;
    @BindView(R.id.cbPurchaseState)
    CheckBox cbPurchaseState;
    @BindView(R.id.cbPurchaseAddContact)
    CheckBox cbPurchaseAddContact;
    @BindView(R.id.cbPurchasaeContact)
    CheckBox cbPurchasaeContact;
    @BindView(R.id.cbPurchaseAgentAdd)
    CheckBox cbPurchaseAgentAdd;
    @BindView(R.id.cbPurchaseAgent)
    CheckBox cbPurchaseAgent;
    @BindView(R.id.cbPurchaseAddCurrency)
    CheckBox cbPurchaseAddCurrency;
    @BindView(R.id.cbPurchasecurrency)
    CheckBox cbPurchasecurrency;
    @BindView(R.id.cbPurchaseAddEmployee)
    CheckBox cbPurchaseAddEmployee;
    @BindView(R.id.cbPurchaseEmployee)
    CheckBox cbPurchaseEmployee;
    @BindView(R.id.cbPurchaseAddExchangeRate)
    CheckBox cbPurchaseAddExchangeRate;
    @BindView(R.id.cbPurchaseExchangeRate)
    CheckBox cbPurchaseExchangeRate;
    @BindView(R.id.cbPurchaseTendCAdd)
    CheckBox cbPurchaseTendCAdd;
    @BindView(R.id.cbPurchaseTendC)
    CheckBox cbPurchaseTendC;
    @BindView(R.id.cbPurchaseCostCentreAdd)
    CheckBox cbPurchaseCostCentreAdd;
    @BindView(R.id.cbPurchaseCostCentre)
    CheckBox cbPurchaseCostCentre;
    @BindView(R.id.cbPurchaseAddShippingMethod)
    CheckBox cbPurchaseAddShippingMethod;
    @BindView(R.id.cbPurchaseShippingMethod)
    CheckBox cbPurchaseShippingMethod;
    @BindView(R.id.cbPurchaseShippingRefNo)
    CheckBox cbPurchaseShippingRefNo;
    @BindView(R.id.cbPurchaseRefenceNo)
    CheckBox cbPurchaseRefenceNo;
    @BindView(R.id.cbPurchaseImportItem)
    CheckBox cbPurchaseImportItem;
    @BindView(R.id.llPurchase)
    LinearLayout llPurchase;
    @BindView(R.id.cbGeneralTransaction)
    CheckBox cbGeneralTransaction;
    @BindView(R.id.cbGTExpense)
    CheckBox cbGTExpense;
    @BindView(R.id.cbGTReciepts)
    CheckBox cbGTReciepts;
    @BindView(R.id.cbGTJournalEntry)
    CheckBox cbGTJournalEntry;
    @BindView(R.id.llGeneralTransaction)
    LinearLayout llGeneralTransaction;
    @BindView(R.id.cbInventory)
    CheckBox cbInventory;
    @BindView(R.id.cbInventoryCategory)
    CheckBox cbInventoryCategory;
    @BindView(R.id.cbInventoryBrand)
    CheckBox cbInventoryBrand;
    @BindView(R.id.cbInventoryUOM)
    CheckBox cbInventoryUOM;
    @BindView(R.id.llInventory1)
    LinearLayout llInventory1;
    @BindView(R.id.cbInventoryItem)
    CheckBox cbInventoryItem;
    @BindView(R.id.cbInventorySalesPricing)
    CheckBox cbInventorySalesPricing;
    @BindView(R.id.cbInventoryPurchasePricing)
    CheckBox cbInventoryPurchasePricing;
    @BindView(R.id.llInventory2)
    LinearLayout llInventory2;
    @BindView(R.id.cbInventoryLocation)
    CheckBox cbInventoryLocation;
    @BindView(R.id.cbInventoryMovementType)
    CheckBox cbInventoryMovementType;
    @BindView(R.id.cbInventoryAttribute)
    CheckBox cbInventoryAttribute;
    @BindView(R.id.llInventory3)
    LinearLayout llInventory3;
    @BindView(R.id.cbInventoryAttributeConfig)
    CheckBox cbInventoryAttributeConfig;
    @BindView(R.id.cbInventoryLocationTransfer)
    CheckBox cbInventoryLocationTransfer;
    @BindView(R.id.cbInventoryCountType)
    CheckBox cbInventoryCountType;
    @BindView(R.id.llInventory4)
    LinearLayout llInventory4;
    @BindView(R.id.cbInventorySalesDiscountConfiguration)
    CheckBox cbInventorySalesDiscountConfiguration;
    @BindView(R.id.cbInventoryVoucher)
    CheckBox cbInventoryVoucher;
    @BindView(R.id.cbInventoryItemCountAdjust)
    CheckBox cbInventoryItemCountAdjust;
    @BindView(R.id.llInventory5)
    LinearLayout llInventory5;
    @BindView(R.id.cbInventoryAdvanceDiscountConfiguration)
    CheckBox cbInventoryAdvanceDiscountConfiguration;
    @BindView(R.id.cbInventoryHSNCode)
    CheckBox cbInventoryHSNCode;
    @BindView(R.id.cbInventoryItemCommission)
    CheckBox cbInventoryItemCommission;
    @BindView(R.id.llInventory6)
    LinearLayout llInventory6;
    @BindView(R.id.cbInventoryAssests)
    CheckBox cbInventoryAssests;
    @BindView(R.id.cbInventoryUOMConverter)
    CheckBox cbInventoryUOMConverter;
    @BindView(R.id.cbInventoryISDN)
    CheckBox cbInventoryISDN;
    @BindView(R.id.llInventory7)
    LinearLayout llInventory7;
    @BindView(R.id.cbTax)
    CheckBox cbTax;
    @BindView(R.id.cbTaxConfig)
    CheckBox cbTaxConfig;
    @BindView(R.id.cbTaxGST)
    CheckBox cbTaxGST;
    @BindView(R.id.cbTaxTax)
    CheckBox cbTaxTax;
    @BindView(R.id.llTax1)
    LinearLayout llTax1;
    @BindView(R.id.cbTaxTaxType)
    CheckBox cbTaxTaxType;
    @BindView(R.id.cbTaxIndiaGSTR)
    CheckBox cbTaxIndiaGSTR;
    @BindView(R.id.cbTaxIndiaGSTR1)
    CheckBox cbTaxIndiaGSTR1;
    @BindView(R.id.llTax2)
    LinearLayout llTax2;
    @BindView(R.id.cbTaxIndiaGSTR2)
    CheckBox cbTaxIndiaGSTR2;
    @BindView(R.id.cbTaxIndiaGST3B)
    CheckBox cbTaxIndiaGST3B;
    @BindView(R.id.cbTaxReversalOfInputCredit)
    CheckBox cbTaxReversalOfInputCredit;
    @BindView(R.id.llTax3)
    LinearLayout llTax3;
    @BindView(R.id.cbTaxGSTTRAN1)
    CheckBox cbTaxGSTTRAN1;
    @BindView(R.id.cbTaxMalaysiaGSTR)
    CheckBox cbTaxMalaysiaGSTR;
    @BindView(R.id.cbTaxMalaysiaGAFExport)
    CheckBox cbTaxMalaysiaGAFExport;
    @BindView(R.id.llTax4)
    LinearLayout llTax4;
    @BindView(R.id.cbTaxMalaysiaGSTReturn)
    CheckBox cbTaxMalaysiaGSTReturn;
    @BindView(R.id.llTax5)
    LinearLayout llTax5;
    @BindView(R.id.cbCPanel)
    CheckBox cbCPanel;
    @BindView(R.id.cbCPanellOBofBS)
    CheckBox cbCPanellOBofBS;
    @BindView(R.id.cbCPanellOBofPI)
    CheckBox cbCPanellOBofPI;
    @BindView(R.id.cbCPanellOBofSI)
    CheckBox cbCPanellOBofSI;
    @BindView(R.id.llControlPanel1)
    LinearLayout llControlPanel1;
    @BindView(R.id.cbCPanellOBofINV)
    CheckBox cbCPanellOBofINV;
    @BindView(R.id.cbCPanelLastYrFig)
    CheckBox cbCPanelLastYrFig;
    @BindView(R.id.cbCPanelMonthEnd)
    CheckBox cbCPanelMonthEnd;
    @BindView(R.id.llControlPanel2)
    LinearLayout llControlPanel2;
    @BindView(R.id.cbCPanelYearEnd)
    CheckBox cbCPanelYearEnd;
    @BindView(R.id.cbCPanelConfigurator)
    CheckBox cbCPanelConfigurator;
    @BindView(R.id.cbCPanelConfigBank)
    CheckBox cbCPanelConfigBank;
    @BindView(R.id.llControlPanel3)
    LinearLayout llControlPanel3;
    @BindView(R.id.cbCPanelConfigAgent)
    CheckBox cbCPanelConfigAgent;
    @BindView(R.id.cbCPanelConfigEmployee)
    CheckBox cbCPanelConfigEmployee;
    @BindView(R.id.cbCPanelConfigCurrency)
    CheckBox cbCPanelConfigCurrency;
    @BindView(R.id.llControlPanel4)
    LinearLayout llControlPanel4;
    @BindView(R.id.cbCPanelConfigExRate)
    CheckBox cbCPanelConfigExRate;
    @BindView(R.id.cbCPanelConfigProject)
    CheckBox cbCPanelConfigProject;
    @BindView(R.id.cbCPanelShipping)
    CheckBox cbCPanelShipping;
    @BindView(R.id.llControlPanel5)
    LinearLayout llControlPanel5;
    @BindView(R.id.cbCPanelConfigEmail)
    CheckBox cbCPanelConfigEmail;
    @BindView(R.id.cbCPanelConfigPayment)
    CheckBox cbCPanelConfigPayment;
    @BindView(R.id.cbCPanelConfigConfig)
    CheckBox cbCPanelConfigConfig;
    @BindView(R.id.llControlPanel6)
    LinearLayout llControlPanel6;
    @BindView(R.id.cbCPanelCompnaySetup)
    CheckBox cbCPanelCompnaySetup;
    @BindView(R.id.cbCPanelCompnaySetupCI)
    CheckBox cbCPanelCompnaySetupCI;
    @BindView(R.id.cbCPanelCompnaySetupFY)
    CheckBox cbCPanelCompnaySetupFY;
    @BindView(R.id.llControlPanel7)
    LinearLayout llControlPanel7;
    @BindView(R.id.cbCPanelCompnaySetupCA)
    CheckBox cbCPanelCompnaySetupCA;
    @BindView(R.id.cbCPanelCompnaySetupHsn)
    CheckBox cbCPanelCompnaySetupHsn;
    @BindView(R.id.cbCPanelUAS)
    CheckBox cbCPanelUAS;
    @BindView(R.id.llControlPanel8)
    LinearLayout llControlPanel8;
    @BindView(R.id.cbCPanelUASuas)
    CheckBox cbCPanelUASuas;
    @BindView(R.id.cbCPanelUASat)
    CheckBox cbCPanelUASat;
    @BindView(R.id.cbCPanelActMaintenance)
    CheckBox cbCPanelActMaintenance;
    @BindView(R.id.llControlPanel9)
    LinearLayout llControlPanel9;
    @BindView(R.id.cbCPanellAMag)
    CheckBox cbCPanellAMag;
    @BindView(R.id.cbCPanellAMcoa)
    CheckBox cbCPanellAMcoa;
    @BindView(R.id.cbCPanellAMbudget)
    CheckBox cbCPanellAMbudget;
    @BindView(R.id.llControlPanel10)
    LinearLayout llControlPanel10;
    @BindView(R.id.cbCPanelDisplaySetup)
    CheckBox cbCPanelDisplaySetup;
    @BindView(R.id.cbCPanellDSfs)
    CheckBox cbCPanellDSfs;
    @BindView(R.id.cbCPanelDSlayout)
    CheckBox cbCPanelDSlayout;
    @BindView(R.id.llControlPanel11)
    LinearLayout llControlPanel11;
    @BindView(R.id.cblDStermsCond)
    CheckBox cblDStermsCond;
    @BindView(R.id.cblDSCountry)
    CheckBox cblDSCountry;
    @BindView(R.id.cblDSVersionControl)
    CheckBox cblDSVersionControl;
    @BindView(R.id.llControlPanel12)
    LinearLayout llControlPanel12;
    @BindView(R.id.cblDSLabelPrintConfigurator)
    CheckBox cblDSLabelPrintConfigurator;
    @BindView(R.id.cblDSBackUpandRestore)
    CheckBox cblDSBackUpandRestore;
    @BindView(R.id.cblDSTableConfiguration)
    CheckBox cblDSTableConfiguration;
    @BindView(R.id.llControlPanel13)
    LinearLayout llControlPanel13;
    @BindView(R.id.cbContact)
    CheckBox cbContact;
    @BindView(R.id.cbContactSuppllier)
    CheckBox cbContactSuppllier;
    @BindView(R.id.cbContactCustomer)
    CheckBox cbContactCustomer;
    @BindView(R.id.cbContactContacts)
    CheckBox cbContactContacts;
    @BindView(R.id.llContact1)
    LinearLayout llContact1;
    @BindView(R.id.cbReports)
    CheckBox cbReports;
    @BindView(R.id.cbReportsSupplierSalesReport)
    CheckBox cbReportsSupplierSalesReport;
    @BindView(R.id.cbReportsPurchaseOrderRegister)
    CheckBox cbReportsPurchaseOrderRegister;
    @BindView(R.id.cbReportsPurchaseInvoiceRegister)
    CheckBox cbReportsPurchaseInvoiceRegister;
    @BindView(R.id.llReport1)
    LinearLayout llReport1;
    @BindView(R.id.cbReportsPurchaseReturnRegister)
    CheckBox cbReportsPurchaseReturnRegister;
    @BindView(R.id.cbReportsSupplierPayment)
    CheckBox cbReportsSupplierPayment;
    @BindView(R.id.cbReportsSupplierItemStockCheck)
    CheckBox cbReportsSupplierItemStockCheck;
    @BindView(R.id.llreport2)
    LinearLayout llreport2;
    @BindView(R.id.cbReportsReceiveItemRegister)
    CheckBox cbReportsReceiveItemRegister;
    @BindView(R.id.cbReportsSupplierStatementRegister)
    CheckBox cbReportsSupplierStatementRegister;
    @BindView(R.id.cbReportsReportSale)
    CheckBox cbReportsReportSale;
    @BindView(R.id.llReport3)
    LinearLayout llReport3;
    @BindView(R.id.cbReportsSalesOrderRegister)
    CheckBox cbReportsSalesOrderRegister;
    @BindView(R.id.cbReportsItemCommissionRegister)
    CheckBox cbReportsItemCommissionRegister;
    @BindView(R.id.cbReportsSalesInvoiceRegister)
    CheckBox cbReportsSalesInvoiceRegister;
    @BindView(R.id.llReport4)
    LinearLayout llReport4;
    @BindView(R.id.cbReportsSalesReturnRegister)
    CheckBox cbReportsSalesReturnRegister;
    @BindView(R.id.cbReportsSalePR)
    CheckBox cbReportsSalePR;
    @BindView(R.id.cbReportsSalePriceRegister)
    CheckBox cbReportsSalePriceRegister;
    @BindView(R.id.llReport5)
    LinearLayout llReport5;
    @BindView(R.id.cbReportsCustomerDues)
    CheckBox cbReportsCustomerDues;
    @BindView(R.id.cbReportsCustomerPayment)
    CheckBox cbReportsCustomerPayment;
    @BindView(R.id.cbReportsSaleSA)
    CheckBox cbReportsSaleSA;
    @BindView(R.id.llReport6)
    LinearLayout llReport6;
    @BindView(R.id.cbReportsSalesQuotationRegister)
    CheckBox cbReportsSalesQuotationRegister;
    @BindView(R.id.cbReportsProductDeliveryregister)
    CheckBox cbReportsProductDeliveryregister;
    @BindView(R.id.cbReportsCustomerStatementRegister)
    CheckBox cbReportsCustomerStatementRegister;
    @BindView(R.id.llReport7)
    LinearLayout llReport7;
    @BindView(R.id.cbReportsSalesAdjustment)
    CheckBox cbReportsSalesAdjustment;
    @BindView(R.id.ccbReportsCustomerRegister)
    CheckBox ccbReportsCustomerRegister;
    @BindView(R.id.cbReportsAgentRegister)
    CheckBox cbReportsAgentRegister;
    @BindView(R.id.llReport8)
    LinearLayout llReport8;
    @BindView(R.id.cbReportsCancelledSalesInvoiceRegister)
    CheckBox cbReportsCancelledSalesInvoiceRegister;
    @BindView(R.id.cbReportsInventoryValuation)
    CheckBox cbReportsInventoryValuation;
    @BindView(R.id.cbReportsInventorySales)
    CheckBox cbReportsInventorySales;
    @BindView(R.id.llReport9)
    LinearLayout llReport9;
    @BindView(R.id.cbReportsGRNValuation)
    CheckBox cbReportsGRNValuation;
    @BindView(R.id.cbReportsInventoryPlanning)
    CheckBox cbReportsInventoryPlanning;
    @BindView(R.id.cbReportsInventoryNonMovement)
    CheckBox cbReportsInventoryNonMovement;
    @BindView(R.id.llReport10)
    LinearLayout llReport10;
    @BindView(R.id.cbReportsInventoryMovement)
    CheckBox cbReportsInventoryMovement;
    @BindView(R.id.cbReportsInventoryRegister)
    CheckBox cbReportsInventoryRegister;
    @BindView(R.id.cbReportsOutstandingGRN)
    CheckBox cbReportsOutstandingGRN;
    @BindView(R.id.llReport11)
    LinearLayout llReport11;
    @BindView(R.id.cbReportsBatchExpiry)
    CheckBox cbReportsBatchExpiry;
    @BindView(R.id.cbReportsSerializationItems)
    CheckBox cbReportsSerializationItems;
    @BindView(R.id.cbReportsBatchItems)
    CheckBox cbReportsBatchItems;
    @BindView(R.id.llReport12)
    LinearLayout llReport12;
    @BindView(R.id.cbReportsAdvanceStockCheck)
    CheckBox cbReportsAdvanceStockCheck;
    @BindView(R.id.cbReportsAdvanceSuppliersales)
    CheckBox cbReportsAdvanceSuppliersales;
    @BindView(R.id.cbReportsReportAnalysis)
    CheckBox cbReportsReportAnalysis;
    @BindView(R.id.llReport13)
    LinearLayout llReport13;
    @BindView(R.id.cbReportsTopVendor)
    CheckBox cbReportsTopVendor;
    @BindView(R.id.cbReportsTopItem)
    CheckBox cbReportsTopItem;
    @BindView(R.id.cbReportsTopCustomer)
    CheckBox cbReportsTopCustomer;
    @BindView(R.id.llReport14)
    LinearLayout llReport14;
    @BindView(R.id.cbReportsAccountBalance)
    CheckBox cbReportsAccountBalance;
    @BindView(R.id.cbReportsCustomerReport)
    CheckBox cbReportsCustomerReport;
    @BindView(R.id.cbReportsCustomerPayment2)
    CheckBox cbReportsCustomerPayment2;
    @BindView(R.id.llReport15)
    LinearLayout llReport15;
    @BindView(R.id.cbReportsSupplierPayment2)
    CheckBox cbReportsSupplierPayment2;
    @BindView(R.id.cbReportsFinancialStatement)
    CheckBox cbReportsFinancialStatement;
    @BindView(R.id.cbReportsBankReconciliation)
    CheckBox cbReportsBankReconciliation;
    @BindView(R.id.llReport16)
    LinearLayout llReport16;
    @BindView(R.id.cbReportsBankReconCheque)
    CheckBox cbReportsBankReconCheque;
    @BindView(R.id.cbReportsBankReconciliationStatement)
    CheckBox cbReportsBankReconciliationStatement;
    @BindView(R.id.llReport17)
    LinearLayout llReport17;
    @BindView(R.id.llContent)
    LinearLayout llContent;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.btnClose)
    Button btnClose;
    @BindView(R.id.ll1)
    LinearLayout ll1;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;

    private Activity activity;
    private ServiceHandler serviceHandler;
    private boolean isInternatePrsenet = false;
    String callingFor, serverUrl;
    private SharedPreference sharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_user_permission1);
        ButterKnife.bind(this);
        initComponentView();
    }

    private void initComponentView() {
        activity = this;
        serviceHandler = new ServiceHandler(activity);
        serverUrl = UtilView.getUrl(activity);
        sharedPreference = SharedPreference.getInstance(activity);
        Intent intent = getIntent();
        userDetail = (UserAccountData) intent.getSerializableExtra("userData");

        if (userDetail != null && userDetail.getUseraccountId() != null) {
            getUserAccountSetupDetail();
        }

        toolbar.setTitle("Update User Permission");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void getUserAccountSetupDetail() {
        String url = serverUrl + "/hipos//0/" + Constant.FUNTION_GTEUSERPERMISSION + userDetail.getUseraccountId();
        // prepare the Request

        isInternatePrsenet = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternatePrsenet) {
                UtilView.showProgessBar(activity, progressBar);
                PostDataTask task = new PostDataTask(activity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(activity, progressBar);

                        if (result != null) {
                            if (result.equals("invalid")) {
                                UtilView.showToast(activity, getResources().getString(R.string.accesstoken_error));
                                Intent intent = new Intent(activity, Activity_Login.class);
                                activity.startActivity(intent);
                                activity.finish();
                            } else {

                                Gson gson = new Gson();
                                try {
                                    JSONObject jsonObject = new JSONObject(result.toString());
                                    userPermissions = gson.fromJson(jsonObject.toString(), UserAccessPermissionData.class);
                                    if (userPermissions != null) {
                                        VCuserAccessRightsDTO vCuserAccessRightsDTO = userPermissions.getvCuserAccessRightsDTO();
                                        setUpView(vCuserAccessRightsDTO);
                                    }


                                } catch (Exception e) {
                                    // UtilView.showLogCat(TAG, "TaskCompleteListener Exception " + e.toString());
                                }
                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.error_null), activity);
                        }
                    }
                }, false);
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("userIdSearchText", userDetail.getUseraccountId());
                    task.execute(jsonObject.toString(), url, "");
                } catch (JSONException je) {

                }

            } else {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
            }
        } else {
            UtilView.gotToLogin(activity);
        }
    }

    private void setUpView(VCuserAccessRightsDTO userPermissions) {

        cbDashBoard.setChecked(userPermissions.getDashboard());
        cbDashBoardDateTime.setChecked(userPermissions.getDashboardDateTime());
        cbDashBoardCashInHand.setChecked(userPermissions.getDashboardCashInHand());
        cbDashBoardCashInBank.setChecked(userPermissions.getDashboardCashInBank());
        cbDashBoardTotalRecievable.setChecked(userPermissions.getDashboardTotalReceivable());
        cbDashBoardTotalPayable.setChecked(userPermissions.getDashboardTotalPayable());


        cbTax.setChecked(userPermissions.getTax());
        cbTaxConfig.setChecked(userPermissions.getTaxTaxConfiguration());
        cbTaxGST.setChecked(userPermissions.getTaxGST());
        cbTaxTax.setChecked(userPermissions.getTaxTax());
        cbTaxTaxType.setChecked(userPermissions.getTaxTaxtype());
        cbTaxIndiaGSTR.setChecked(userPermissions.getGstIndiaGSTSR());
        cbTaxIndiaGSTR1.setChecked(userPermissions.getGstIndiaGSTR1Tool());
        cbTaxIndiaGSTR2.setChecked(userPermissions.getGstIndiaGSTR2Tool());
        cbTaxIndiaGST3B.setChecked(userPermissions.getGstIndiaGST3B());
        cbTaxGSTTRAN1.setChecked(userPermissions.getGstIndiaGSTTRANS1());
        cbTaxMalaysiaGSTR.setChecked(userPermissions.getGstMalGSTSR());
        cbTaxMalaysiaGAFExport.setChecked(userPermissions.getGstMalGAFExport());
        cbTaxMalaysiaGSTReturn.setChecked(userPermissions.getGstMalGSTReturn());
        cbTaxReversalOfInputCredit.setChecked(userPermissions.getGstIndiaRIPC());



        cbGeneralTransaction.setChecked(userPermissions.getGt());
        cbGTExpense.setChecked(userPermissions.getGtExpense());
        cbGTReciepts.setChecked(userPermissions.getGtReciepts());
        cbGTJournalEntry.setChecked(userPermissions.getGtJournalEntry());

        cbContact.setChecked(userPermissions.getContact());
        cbContactContacts.setChecked(userPermissions.getContactContacts());
        cbContactSuppllier.setChecked(userPermissions.getContactSupplier());
        cbContactCustomer.setChecked(userPermissions.getContactCustomer());


        cbInventory.setChecked(userPermissions.getInventory());
        cbInventoryBrand.setChecked(userPermissions.getInventoryBrand());
        cbInventoryCategory.setChecked(userPermissions.getInventoryCategory());
        cbInventoryUOM.setChecked(userPermissions.getInventoryUOM());
        cbInventoryItem.setChecked(userPermissions.getInventoryItem());
        cbInventorySalesPricing.setChecked(userPermissions.getInventorySalesPricing());
        cbInventoryPurchasePricing.setChecked(userPermissions.getInventoryPurchasePricing());
        cbInventoryLocation.setChecked(userPermissions.getInventoryInventoryLocation());
        cbInventoryMovementType.setChecked(userPermissions.getInventoryInventoryMovementType());
        cbInventoryAttribute.setChecked(userPermissions.getInventoryAttribute());
        cbInventoryAttributeConfig.setChecked(userPermissions.getInventoryAttributeConfig());
        cbInventoryLocationTransfer.setChecked(userPermissions.getInventoryInvLocTransfer());
        cbInventoryCountType.setChecked(userPermissions.getInventoryInvCountType());
        cbInventorySalesDiscountConfiguration.setChecked(userPermissions.getInventorySalesDiscountConfig());
        cbInventoryVoucher.setChecked(userPermissions.getInventoryVoucher());
        cbInventoryItemCountAdjust.setChecked(userPermissions.getInventoryItemCountAdjust());
        cbInventoryHSNCode.setChecked(userPermissions.getInventoryHsnCode());
        cbInventoryItemCommission.setChecked(userPermissions.getInventoryItemCommission());
        cbInventoryAssests.setChecked(userPermissions.getInventoryAssets());
        cbInventoryUOMConverter.setChecked(userPermissions.getInventoryUomConverter());
        cbInventoryISDN.setChecked(userPermissions.getInventoryJSDN());
        cbInventoryAdvanceDiscountConfiguration.setChecked(userPermissions.getInventoryAdvDisConfig());

        cbSales.setChecked(userPermissions.getSales());
        cbSalesItems.setChecked(userPermissions.getSalesItemAdd());
        cbSalesAddCustomer.setChecked(userPermissions.getSalesCustomerAdd());
        cbSalesSaveDraft.setChecked(userPermissions.getSalesSavesDraft());
        cbSalesClearDraft.setChecked(userPermissions.getSalesClearDraft());
        cbSalesPrintList.setChecked(userPermissions.getSalesPrintList());
        cbSalesAdvacnePayment.setChecked(userPermissions.getSalesAdvancePayment());
        cbSalesSaveSalesOrder.setChecked(userPermissions.getSalesSaveSalesOrder());
        cbSalesAdvancePrintList.setChecked(userPermissions.getSalesAdvancePaymentList());
        cbSalesInvokeSalesOrder.setChecked(userPermissions.getSalesInvokeSalesOrder());
        cbSalesClearAll.setChecked(userPermissions.getSalesClearAll());
        cbSalesCheckout.setChecked(userPermissions.getSalesCheckOut());
        cbSalesReturn.setChecked(userPermissions.getSalesReturn());
        cbSalesRecievePayment.setChecked(userPermissions.getSalesRecievePayment());
        cbSalesInvokeDraft.setChecked(userPermissions.getSalesInvokeDraft());
        cbSalesSearchBarCode.setChecked(userPermissions.getSalesSearchBarCode());
        cbSalesBarCode.setChecked(userPermissions.getSalesBarCode());
        cbSalesSearchSerializeItem.setChecked(userPermissions.getSalesSearchSerializeItem());
        cbSalesSerializeItem.setChecked(userPermissions.getSalesSerializeItem());
        cbSalesCustomer.setChecked(userPermissions.getSalesCustomer());
        cbSalesImportItem.setChecked(userPermissions.getSalesImportItem());
        cbSalesTaxType.setChecked(userPermissions.getSalesTaxType());
        cbSalesInvLocAdd.setChecked(userPermissions.getSalesInvLocAdd());
        cbSalesInvLoc.setChecked(userPermissions.getSalesInvLoc());
        cbSalesStateAdd.setChecked(userPermissions.getSalesStateAdd());
        cbSalesState.setChecked(userPermissions.getSalesState());
        cbSalesRemoveItem.setChecked(userPermissions.getSalesRemoveItem());
        cbSalesMore.setChecked(userPermissions.getSalesMore());
        cbSalesContactAdd.setChecked(userPermissions.getSalesContactAdd());
        cbSalesContact.setChecked(userPermissions.getSalesContact());
        cbSalesAgentAdd.setChecked(userPermissions.getSalesAgentAdd());
        cbSalesAgent.setChecked(userPermissions.getSalesAgent());
        cbSalesCurrencyAdd.setChecked(userPermissions.getSalesCurrencyAdd());
        cbSalesCurrency.setChecked(userPermissions.getSalesCurrency());
        cbSalesEmpoloyeeAdd.setChecked(userPermissions.getSalesEmployeeAdd());
        cbSalesEmpoloyee.setChecked(userPermissions.getSalesEmployee());
        cbSalesExchangeRate.setChecked(userPermissions.getSalesExchangeRate());
        cbSalesExchangeRateAdd.setChecked(userPermissions.getSalesExchangeRateAdd());
        cbSalesTndCAdd.setChecked(userPermissions.getSalesTndCAdd());
        cbSalesTandc.setChecked(userPermissions.getSalesTandC());
        cbSalesCostCentreAdd.setChecked(userPermissions.getSalesCCAdd());
        cbSalesCostCentre.setChecked(userPermissions.getSalesCC());
        cbSalesShippingMethodAdd.setChecked(userPermissions.getSalesSMAdd());
        cbSalesShippingMethod.setChecked(userPermissions.getSalesSM());
        cbSalesShippingRefNo.setChecked(userPermissions.getSalesShippingRefNo());
        cbSalesReferenceNo.setChecked(userPermissions.getSalesRefNo());
        cbSalesProforma.setChecked(userPermissions.getSalesSaveProforma());
        cbSalesInvokeProforma.setChecked(userPermissions.getSalesInvokeProforma());
        cbSalesDeliveryOrder.setChecked(userPermissions.getSalesSaveDeliveryOrder());
        cbSalesInvokeDelivery.setChecked(userPermissions.getSalesInvokeDeliveryOrder());
        cbSalesReturnDelivery.setChecked(userPermissions.getSalesReturnDeliveryOrder());
        cbSalesDebitNote.setChecked(userPermissions.getSalesDebitNote());
        cbSalesDraftInvoice.setChecked(userPermissions.getDraftInvoice());
        cbSalesQuotation.setChecked(userPermissions.getSalesSaveSalesQuotation());
        cbSalesInvokeQuotation.setChecked(userPermissions.getSalesInvokeSalesQuotation());




        cbPurchase.setChecked(userPermissions.getPurchase());
        cbPurchaseItems.setChecked(userPermissions.getPurchaseItemAdd());
        cbPurchaseSupplier.setChecked(userPermissions.getPurchaseSupplierAdd());
        cbPurchasePrintList.setChecked(userPermissions.getPurchasePrintList());
        cbPurchaseAdvancePayment.setChecked(userPermissions.getPurchaseAdvancePayment());
        cbPurchaseSavePurchaseOrder.setChecked(userPermissions.getPurchaseSavePurchaseOrder());
        cbPruchaseAdvPaymentList.setChecked(userPermissions.getPurchaseAdvancePaymentList());
        cbPurchaseInvokePo.setChecked(userPermissions.getPurchaseInvokePurchaseOrder());
        cbPurchaseClearAll.setChecked(userPermissions.getPurchaseClearAll());
        cbPurchaseCheckOut.setChecked(userPermissions.getPurchaseCheckOut());
        cbPurchasaeReturn.setChecked(userPermissions.getPurchaseReturn());
        cbPurchaseSupplierPayment.setChecked(userPermissions.getPurchaseSupplierPayment());
        cbPurchaseSelfBilled.setChecked(userPermissions.getPurchaseSelfBilled());
        cbPurchasaeSearchBarcode.setChecked(userPermissions.getPurSearchBarCode());
        cbPurchaseBarcode.setChecked(userPermissions.getPurBarCode());
        cbPurchaseMore.setChecked(userPermissions.getPurMore());
        cbPurchasaeCreditNote.setChecked(userPermissions.getPurCreditNote());
        cbPurchaseRemoveItem.setChecked(userPermissions.getPurRemoveItem());
        cbPurchaseInVokeRecievedItem.setChecked(userPermissions.getPurInvokeRecItem());
        cbPurchasaeReturnReceivedItem.setChecked(userPermissions.getPurReturnRecItem());
        cbPurchaseSaveQuotation.setChecked(userPermissions.getPurchaseSavePurchaseQuotation());
        cbPurchaseInvokeQuotation.setChecked(userPermissions.getPurchaseInvokePurchaseQuotation());
        cbPurchasaeTaxType.setChecked(userPermissions.getPurTaxType());
        cbPurchaseInventoryLocationAdd.setChecked(userPermissions.getPurInvLocAdd());
        cbPurchaseInventoryLocation.setChecked(userPermissions.getPurInvLoc());
        cbPurchasaeStateAdd.setChecked(userPermissions.getPurStateAdd());
        cbPurchaseState.setChecked(userPermissions.getPurState());
        cbPurchaseAddContact.setChecked(userPermissions.getPurContactAdd());
        cbPurchasaeContact.setChecked(userPermissions.getPurContact());
        cbPurchaseAgentAdd.setChecked(userPermissions.getPurAgentAdd());
        cbPurchaseAgent.setChecked(userPermissions.getPurAgent());
        cbPurchaseAddCurrency.setChecked(userPermissions.getPurCurrencyAdd());
        cbPurchasecurrency.setChecked(userPermissions.getPurCurrency());
        cbPurchaseAddEmployee.setChecked(userPermissions.getPurEmployeeAdd());
        cbPurchaseEmployee.setChecked(userPermissions.getPurEmployee());
        cbPurchaseAddExchangeRate.setChecked(userPermissions.getPurExchangeRateAdd());
        cbPurchaseExchangeRate.setChecked(userPermissions.getPurExchangeRate());
        cbPurchaseTendCAdd.setChecked(userPermissions.getPurTndCAdd());
        cbPurchaseTendC.setChecked(userPermissions.getPurTandC());
        cbPurchaseCostCentre.setChecked(userPermissions.getPurCC());
        cbPurchaseCostCentreAdd.setChecked(userPermissions.getPurCCAdd());
        cbPurchaseAddShippingMethod.setChecked(userPermissions.getPurSMAdd());
        cbPurchaseShippingMethod.setChecked(userPermissions.getPurSM());
        cbPurchaseShippingRefNo.setChecked(userPermissions.getPurShippingRefNo());
        cbPurchaseRefenceNo.setChecked(userPermissions.getPurRefNo());
        cbPurchaseImportItem.setChecked(userPermissions.getPurImportItem());



        cbCPanel.setChecked(userPermissions.getControlPanel());
        cbCPanellOBofBS.setChecked(userPermissions.getControlPanelOBofBS());
        cbCPanellOBofPI.setChecked(userPermissions.getControlPanelOBofPI());
        cbCPanellOBofSI.setChecked(userPermissions.getControlPanelOBofSI());
        cbCPanellOBofINV.setChecked(userPermissions.getControlPanelOBofINV());
        cbCPanelLastYrFig.setChecked(userPermissions.getControlPanelLastYearFigure());
        cbCPanelMonthEnd.setChecked(userPermissions.getControlPanelMonthEnd());
        cbCPanelYearEnd.setChecked(userPermissions.getControlPanelYearEnd());
        cbCPanelConfigurator.setChecked(userPermissions.getControlPanelConfigurator());
        cbCPanelConfigBank.setChecked(userPermissions.getcPConfiguratorBank());
        cbCPanelConfigAgent.setChecked(userPermissions.getcPConfiguratorAgent());
        cbCPanelConfigEmployee.setChecked(userPermissions.getcPConfiguratorEmployee());
        cbCPanelConfigCurrency.setChecked(userPermissions.getcPConfiguratorCurrency());
        cbCPanelConfigExRate.setChecked(userPermissions.getcPConfiguratorCurrency());
        cbCPanelConfigProject.setChecked(userPermissions.getcPConfiguratorProjectTitle());
        cbCPanelShipping.setChecked(userPermissions.getcPConfiguratorShippingMethod());
        cbCPanelConfigEmail.setChecked(userPermissions.getcPConfiguratorEmailserver());
        cbCPanelConfigPayment.setChecked(userPermissions.getcPConfiguratorPaymentMethod());
        cbCPanelConfigConfig.setChecked(userPermissions.getcPConfiguratorConfiguration());
        cbCPanelCompnaySetup.setChecked(userPermissions.getControlPanelCompanySetup());
        cbCPanelCompnaySetupCI.setChecked(userPermissions.getcPCompanySetupCI());
        cbCPanelCompnaySetupFY.setChecked(userPermissions.getcPCompanySetupFY());
        cbCPanelCompnaySetupCA.setChecked(userPermissions.getcPCompanySetupCA());
        cbCPanelCompnaySetupHsn.setChecked(userPermissions.getcPCompanySetupHSN());
        cbCPanelUAS.setChecked(userPermissions.getControlPanelUAS());
        cbCPanelUASuas.setChecked(userPermissions.getControlPanelUASuas());
        cbCPanelUASat.setChecked(userPermissions.getControlPanelUASat());
        cbCPanelActMaintenance.setChecked(userPermissions.getControlPanelAccountMaintenance());
        cbCPanellAMag.setChecked(userPermissions.getControlPanelAMag());
        cbCPanellAMcoa.setChecked(userPermissions.getControlPanelAMcoa());
        cbCPanellAMbudget.setChecked(userPermissions.getControlPanelAMbudget());
        cbCPanelDisplaySetup.setChecked(userPermissions.getControlPanelDisplaySetup());
        cbCPanellDSfs.setChecked(userPermissions.getControlPanelDSfs());
        cbCPanelDSlayout.setChecked(userPermissions.getControlPanelDSlayout());
        cblDStermsCond.setChecked(userPermissions.getControlPanelDStermsCond());
        cblDSCountry.setChecked(userPermissions.getcPConfiguratorCountry());
        cblDSVersionControl.setChecked(userPermissions.getcPConfiguratorVersionControl());
        cblDSLabelPrintConfigurator.setChecked(userPermissions.getcPConfiguratorLabelPrintConfig());
        cblDSBackUpandRestore.setChecked(userPermissions.getcPCompanySetupBandR());
        cblDSTableConfiguration.setChecked(userPermissions.getControlPanelDSTableConfig());

        cbReports.setChecked(userPermissions.getReport());
        cbReportsSupplierSalesReport.setChecked(userPermissions.getReportPurchaseSSR());
        cbReportsPurchaseOrderRegister.setChecked(userPermissions.getReportPurchasePOL());
        cbReportsPurchaseInvoiceRegister.setChecked(userPermissions.getReportPurchasePIL());
        cbReportsPurchaseReturnRegister.setChecked(userPermissions.getReportPurchasePRL());
        cbReportsSupplierPayment2.setChecked(userPermissions.getReportPurchaseSP());
        cbReportsSupplierItemStockCheck.setChecked(userPermissions.getReportPurchasePA());
        cbReportsReceiveItemRegister.setChecked(userPermissions.getReportPurchaseRIL());
        cbReportsSupplierStatementRegister.setChecked(userPermissions.getReportPurchaseSSL());
        cbReportsReportSale.setChecked(userPermissions.getReportPurchaseSL());
        cbReportsSalesOrderRegister.setChecked(userPermissions.getReportSaleSOL());
        cbReportsItemCommissionRegister.setChecked(userPermissions.getReportSaleICL());
        cbReportsSalesInvoiceRegister.setChecked(userPermissions.getReportSaleSIL());
        cbReportsSalesReturnRegister.setChecked(userPermissions.getReportSaleSRL());
        cbReportsSalePR.setChecked(userPermissions.getReportSalePR());
        cbReportsSalePriceRegister.setChecked(userPermissions.getReportSalePL());
        cbReportsCustomerDues.setChecked(userPermissions.getReportSaleCA());
        cbReportsCustomerPayment2.setChecked(userPermissions.getReportSaleCP());
        cbReportsSaleSA.setChecked(userPermissions.getReportSaleSA());
        cbReportsSalesQuotationRegister.setChecked(userPermissions.getReportSaleSQL());
        cbReportsProductDeliveryregister.setChecked(userPermissions.getReportSalePDL());
        cbReportsCustomerStatementRegister.setChecked(userPermissions.getReportSaleCS());
        cbReportsSalesAdjustment.setChecked(userPermissions.getReportSaleSA());
        ccbReportsCustomerRegister.setChecked(userPermissions.getReportSaleCL());
        cbReportsAgentRegister.setChecked(userPermissions.getReportSaleAL());
        cbReportsCancelledSalesInvoiceRegister.setChecked(userPermissions.getReportSaleCSI());
        cbReportsInventoryValuation.setChecked(userPermissions.getReportInventoryIVR());
        cbReportsInventorySales.setChecked(userPermissions.getReportInventorySI());
        cbReportsGRNValuation.setChecked(userPermissions.getReportInventoryGRNVR());
        cbReportsInventoryPlanning.setChecked(userPermissions.getReportInventoryIPR());
        cbReportsInventoryNonMovement.setChecked(userPermissions.getReportInventoryINMR());
        cbReportsInventoryMovement.setChecked(userPermissions.getReportInventoryIMR());
        cbReportsInventoryRegister.setChecked(userPermissions.getReportInventoryIL());
        cbReportsOutstandingGRN.setChecked(userPermissions.getReportInventoryOGRNR());
        cbReportsBatchExpiry.setChecked(userPermissions.getReportInventoryBER());
        cbReportsSerializationItems.setChecked(userPermissions.getReportInventorySI());
        cbReportsBatchItems.setChecked(userPermissions.getReportInventoryBI());
        cbReportsAdvanceStockCheck.setChecked(userPermissions.getReportInventoryADVSCR());
        cbReportsAdvanceSuppliersales.setChecked(userPermissions.getReportInventoryADVSSR());
        cbReportsReportAnalysis.setChecked(userPermissions.getReportAnalysis());
        cbReportsTopVendor.setChecked(userPermissions.getReportAnalysisTopVendor());
        cbReportsTopItem.setChecked(userPermissions.getReportAnalysisTopItem());
        cbReportsTopCustomer.setChecked(userPermissions.getReportAnalysisTopCustomer());
        cbReportsAccountBalance.setChecked(userPermissions.getReportAnalysisAccountBalance());
        cbReportsCustomerReport.setChecked(userPermissions.getReportAnalysisCRR());
        cbReportsCustomerPayment.setChecked(userPermissions.getReportAnalysisCIP());
        cbReportsSupplierPayment.setChecked(userPermissions.getReportAnalysisSIP());
        cbReportsFinancialStatement.setChecked(userPermissions.getReportFinancialStatement());
        cbReportsBankReconciliation.setChecked(userPermissions.getReportBankRecon());
        cbReportsBankReconCheque.setChecked(userPermissions.getReportBankReconCheques());
        cbReportsBankReconciliationStatement.setChecked(userPermissions.getReportBankReconStmnt());

    }

    @OnClick({R.id.cbDashBoard, R.id.cbSales, R.id.cbPurchase, R.id.cbGeneralTransaction, R.id.cbInventory, R.id.cbTax, R.id.cbCPanel, R.id.cbContact, R.id.cbReports, R.id.btnSave, R.id.btnClose})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cbDashBoard:
                 setAllDashBoradChecked(((CheckBox) view).isChecked());
                break;
            case R.id.cbSales:
                setAllSalesChecked(((CheckBox) view).isChecked());
                break;
            case R.id.cbPurchase:
                 setAllPurchaseChecked(((CheckBox) view).isChecked());
                break;
            case R.id.cbGeneralTransaction:
                setAllGenralTransationChecked(((CheckBox) view).isChecked());

                break;
            case R.id.cbInventory:
                setAllInventoryChecked(((CheckBox) view).isChecked());
                break;
            case R.id.cbTax:
                 setAllTaxChecked(((CheckBox) view).isChecked());
                break;
            case R.id.cbCPanel:
                 setAllCPanelChecked(((CheckBox) view).isChecked());
                break;
            case R.id.cbContact:
                setAllContactChecked(((CheckBox) view).isChecked());
                break;
            case R.id.cbReports:
                setAllReportChecked(((CheckBox) view).isChecked());
                break;
            case R.id.btnSave:
                isInternatePrsenet = serviceHandler.isConnectingToInternet();
                if (isInternatePrsenet) {
                    updateUserPermission();
                } else {
                    UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
                }
                break;
            case R.id.btnClose:
                finish();
                break;
        }
    }

    private void setAllReportChecked(boolean isChecked) {


        cbReportsSupplierSalesReport.setChecked(isChecked);
        cbReportsPurchaseOrderRegister.setChecked(isChecked);
        cbReportsPurchaseInvoiceRegister.setChecked(isChecked);
        cbReportsPurchaseReturnRegister.setChecked(isChecked);
        cbReportsSupplierPayment2.setChecked(isChecked);
        cbReportsSupplierItemStockCheck.setChecked(isChecked);
        cbReportsReceiveItemRegister.setChecked(isChecked);
        cbReportsSupplierStatementRegister.setChecked(isChecked);
        cbReportsReportSale.setChecked(isChecked);
        cbReportsSalesOrderRegister.setChecked(isChecked);
        cbReportsItemCommissionRegister.setChecked(isChecked);
        cbReportsSalesInvoiceRegister.setChecked(isChecked);
        cbReportsSalesReturnRegister.setChecked(isChecked);
        cbReportsSalePR.setChecked(isChecked);
        cbReportsSalePriceRegister.setChecked(isChecked);
        cbReportsCustomerDues.setChecked(isChecked);
        cbReportsCustomerPayment2.setChecked(isChecked);
        cbReportsSaleSA.setChecked(isChecked);
        cbReportsSalesQuotationRegister.setChecked(isChecked);
        cbReportsProductDeliveryregister.setChecked(isChecked);
        cbReportsCustomerStatementRegister.setChecked(isChecked);
        cbReportsSalesAdjustment.setChecked(isChecked);
        ccbReportsCustomerRegister.setChecked(isChecked);
        cbReportsAgentRegister.setChecked(isChecked);
        cbReportsCancelledSalesInvoiceRegister.setChecked(isChecked);
        cbReportsInventoryValuation.setChecked(isChecked);
        cbReportsInventorySales.setChecked(isChecked);
        cbReportsGRNValuation.setChecked(isChecked);
        cbReportsInventoryPlanning.setChecked(isChecked);
        cbReportsInventoryNonMovement.setChecked(isChecked);
        cbReportsInventoryMovement.setChecked(isChecked);
        cbReportsInventoryRegister.setChecked(isChecked);
        cbReportsOutstandingGRN.setChecked(isChecked);
        cbReportsBatchExpiry.setChecked(isChecked);
        cbReportsSerializationItems.setChecked(isChecked);
        cbReportsBatchItems.setChecked(isChecked);
        cbReportsAdvanceStockCheck.setChecked(isChecked);
        cbReportsAdvanceSuppliersales.setChecked(isChecked);
        cbReportsReportAnalysis.setChecked(isChecked);
        cbReportsTopVendor.setChecked(isChecked);
        cbReportsTopItem.setChecked(isChecked);
        cbReportsTopCustomer.setChecked(isChecked);
        cbReportsAccountBalance.setChecked(isChecked);
        cbReportsCustomerReport.setChecked(isChecked);
        cbReportsCustomerPayment.setChecked(isChecked);
        cbReportsSupplierPayment.setChecked(isChecked);
        cbReportsFinancialStatement.setChecked(isChecked);
        cbReportsBankReconciliation.setChecked(isChecked);
        cbReportsBankReconCheque.setChecked(isChecked);
        cbReportsBankReconciliationStatement.setChecked(isChecked);


        if (isChecked) {
            llReport17.setVisibility(View.VISIBLE);
            llReport16.setVisibility(View.VISIBLE);
            llReport15.setVisibility(View.VISIBLE);
            llReport14.setVisibility(View.VISIBLE);
            llReport13.setVisibility(View.VISIBLE);
            llReport12.setVisibility(View.VISIBLE);
            llReport11.setVisibility(View.VISIBLE);
            llReport10.setVisibility(View.VISIBLE);
            llReport9.setVisibility(View.VISIBLE);
            llReport8.setVisibility(View.VISIBLE);
            llReport7.setVisibility(View.VISIBLE);
            llReport6.setVisibility(View.VISIBLE);
            llReport5.setVisibility(View.VISIBLE);
            llReport4.setVisibility(View.VISIBLE);
            llReport3.setVisibility(View.VISIBLE);
            llreport2.setVisibility(View.VISIBLE);
            llReport1.setVisibility(View.VISIBLE);
        }
        if (!isChecked) {

            llReport17.setVisibility(View.GONE);
            llReport16.setVisibility(View.GONE);
            llReport15.setVisibility(View.GONE);
            llReport14.setVisibility(View.GONE);
            llReport13.setVisibility(View.GONE);
            llReport12.setVisibility(View.GONE);
            llReport11.setVisibility(View.GONE);
            llReport10.setVisibility(View.GONE);
            llReport9.setVisibility(View.GONE);
            llReport8.setVisibility(View.GONE);
            llReport7.setVisibility(View.GONE);
            llReport6.setVisibility(View.GONE);
            llReport5.setVisibility(View.GONE);
            llReport4.setVisibility(View.GONE);
            llReport3.setVisibility(View.GONE);
            llreport2.setVisibility(View.GONE);
            llReport1.setVisibility(View.GONE);
        }

    }

    private void setAllCPanelChecked(boolean isChecked) {

        cbCPanellOBofBS.setChecked(isChecked);
        cbCPanellOBofPI.setChecked(isChecked);
        cbCPanellOBofSI.setChecked(isChecked);
        cbCPanellOBofINV.setChecked(isChecked);
        cbCPanelLastYrFig.setChecked(isChecked);
        cbCPanelMonthEnd.setChecked(isChecked);
        cbCPanelYearEnd.setChecked(isChecked);
        cbCPanelConfigurator.setChecked(isChecked);
        cbCPanelConfigBank.setChecked(isChecked);
        cbCPanelConfigAgent.setChecked(isChecked);
        cbCPanelConfigEmployee.setChecked(isChecked);
        cbCPanelConfigCurrency.setChecked(isChecked);
        cbCPanelConfigExRate.setChecked(isChecked);
        cbCPanelConfigProject.setChecked(isChecked);
        cbCPanelShipping.setChecked(isChecked);
        cbCPanelConfigEmail.setChecked(isChecked);
        cbCPanelConfigPayment.setChecked(isChecked);
        cbCPanelConfigConfig.setChecked(isChecked);
        cbCPanelCompnaySetup.setChecked(isChecked);
        cbCPanelCompnaySetupCI.setChecked(isChecked);
        cbCPanelCompnaySetupFY.setChecked(isChecked);
        cbCPanelCompnaySetupCA.setChecked(isChecked);
        cbCPanelCompnaySetupHsn.setChecked(isChecked);
        cbCPanelUAS.setChecked(isChecked);
        cbCPanelUASuas.setChecked(isChecked);
        cbCPanelUASat.setChecked(isChecked);
        cbCPanelActMaintenance.setChecked(isChecked);
        cbCPanellAMag.setChecked(isChecked);
        cbCPanellAMcoa.setChecked(isChecked);
        cbCPanellAMbudget.setChecked(isChecked);
        cbCPanelDisplaySetup.setChecked(isChecked);
        cbCPanellDSfs.setChecked(isChecked);
        cbCPanelDSlayout.setChecked(isChecked);
        cblDStermsCond.setChecked(isChecked);
        cblDSCountry.setChecked(isChecked);
        cblDSVersionControl.setChecked(isChecked);
        cblDSLabelPrintConfigurator.setChecked(isChecked);
        cblDSBackUpandRestore.setChecked(isChecked);
        cblDSTableConfiguration.setChecked(isChecked);

        if (isChecked) {
            llControlPanel1.setVisibility(View.VISIBLE);
            llControlPanel2.setVisibility(View.VISIBLE);
            llControlPanel3.setVisibility(View.VISIBLE);
            llControlPanel4.setVisibility(View.VISIBLE);
            llControlPanel5.setVisibility(View.VISIBLE);
            llControlPanel6.setVisibility(View.VISIBLE);
            llControlPanel7.setVisibility(View.VISIBLE);
            llControlPanel8.setVisibility(View.VISIBLE);
            llControlPanel9.setVisibility(View.VISIBLE);
            llControlPanel10.setVisibility(View.VISIBLE);
            llControlPanel11.setVisibility(View.VISIBLE);
            llControlPanel12.setVisibility(View.VISIBLE);
            llControlPanel13.setVisibility(View.VISIBLE);


        }
        if (!isChecked) {
            llControlPanel1.setVisibility(View.GONE);
            llControlPanel2.setVisibility(View.GONE);
            llControlPanel3.setVisibility(View.GONE);
            llControlPanel4.setVisibility(View.GONE);
            llControlPanel5.setVisibility(View.GONE);
            llControlPanel6.setVisibility(View.GONE);
            llControlPanel7.setVisibility(View.GONE);
            llControlPanel8.setVisibility(View.GONE);
            llControlPanel9.setVisibility(View.GONE);
            llControlPanel10.setVisibility(View.GONE);
            llControlPanel11.setVisibility(View.GONE);
            llControlPanel12.setVisibility(View.GONE);
            llControlPanel13.setVisibility(View.GONE);
        }

    }


    private void setAllInventoryChecked(boolean isChecked) {

        cbInventoryBrand.setChecked(isChecked);
        cbInventoryCategory.setChecked(isChecked);
        cbInventoryUOM.setChecked(isChecked);
        cbInventoryItem.setChecked(isChecked);
        cbInventorySalesPricing.setChecked(isChecked);
        cbInventoryPurchasePricing.setChecked(isChecked);
        cbInventoryLocation.setChecked(isChecked);
        cbInventoryMovementType.setChecked(isChecked);
        cbInventoryAttribute.setChecked(isChecked);
        cbInventoryAttributeConfig.setChecked(isChecked);
        cbInventoryLocationTransfer.setChecked(isChecked);
        cbInventoryCountType.setChecked(isChecked);
        cbInventorySalesDiscountConfiguration.setChecked(isChecked);
        cbInventoryVoucher.setChecked(isChecked);
        cbInventoryItemCountAdjust.setChecked(isChecked);
        cbInventoryHSNCode.setChecked(isChecked);
        cbInventoryItemCommission.setChecked(isChecked);
        cbInventoryAssests.setChecked(isChecked);
        cbInventoryUOMConverter.setChecked(isChecked);
        cbInventoryISDN.setChecked(isChecked);
        cbInventoryAdvanceDiscountConfiguration.setChecked(isChecked);


        if (isChecked) {
            llInventory1.setVisibility(View.VISIBLE);
            llInventory2.setVisibility(View.VISIBLE);
            llInventory3.setVisibility(View.VISIBLE);
            llInventory4.setVisibility(View.VISIBLE);
            llInventory5.setVisibility(View.VISIBLE);
            llInventory6.setVisibility(View.VISIBLE);
            llInventory7.setVisibility(View.VISIBLE);

        }
        if (!isChecked) {
            llInventory1.setVisibility(View.GONE);
            llInventory2.setVisibility(View.GONE);
            llInventory3.setVisibility(View.GONE);
            llInventory4.setVisibility(View.GONE);
            llInventory5.setVisibility(View.GONE);
            llInventory6.setVisibility(View.GONE);
            llInventory7.setVisibility(View.GONE);
        }
    }

    private void setAllPurchaseChecked(boolean isChecked) {
        cbPurchase.setChecked(isChecked);
        cbPurchaseItems.setChecked(isChecked);
        cbPurchaseSupplier.setChecked(isChecked);
        cbPurchasePrintList.setChecked(isChecked);
        cbPurchaseAdvancePayment.setChecked(isChecked);
        cbPurchaseSavePurchaseOrder.setChecked(isChecked);
        cbPruchaseAdvPaymentList.setChecked(isChecked);
        cbPurchaseInvokePo.setChecked(isChecked);
        cbPurchaseClearAll.setChecked(isChecked);
        cbPurchaseCheckOut.setChecked(isChecked);
        cbPurchasaeReturn.setChecked(isChecked);
        cbPurchaseSupplierPayment.setChecked(isChecked);
        cbPurchaseSelfBilled.setChecked(isChecked);
        cbPurchasaeSearchBarcode.setChecked(isChecked);
        cbPurchaseBarcode.setChecked(isChecked);
        cbPurchaseMore.setChecked(isChecked);
        cbPurchasaeCreditNote.setChecked(isChecked);
        cbPurchaseRemoveItem.setChecked(isChecked);
        cbPurchaseInVokeRecievedItem.setChecked(isChecked);
        cbPurchasaeReturnReceivedItem.setChecked(isChecked);
        cbPurchaseSaveQuotation.setChecked(isChecked);
        cbPurchaseInvokeQuotation.setChecked(isChecked);
        cbPurchasaeTaxType.setChecked(isChecked);
        cbPurchaseInventoryLocationAdd.setChecked(isChecked);
        cbPurchaseInventoryLocation.setChecked(isChecked);
        cbPurchasaeStateAdd.setChecked(isChecked);
        cbPurchaseState.setChecked(isChecked);
        cbPurchaseAddContact.setChecked(isChecked);
        cbPurchasaeContact.setChecked(isChecked);
        cbPurchaseAgentAdd.setChecked(isChecked);
        cbPurchaseAgent.setChecked(isChecked);
        cbPurchaseAddCurrency.setChecked(isChecked);
        cbPurchasecurrency.setChecked(isChecked);
        cbPurchaseAddEmployee.setChecked(isChecked);
        cbPurchaseEmployee.setChecked(isChecked);
        cbPurchaseAddExchangeRate.setChecked(isChecked);
        cbPurchaseExchangeRate.setChecked(isChecked);
        cbPurchaseTendCAdd.setChecked(isChecked);
        cbPurchaseTendC.setChecked(isChecked);
        cbPurchaseCostCentre.setChecked(isChecked);
        cbPurchaseCostCentreAdd.setChecked(isChecked);
        cbPurchaseAddShippingMethod.setChecked(isChecked);
        cbPurchaseShippingMethod.setChecked(isChecked);
        cbPurchaseShippingRefNo.setChecked(isChecked);
        cbPurchaseRefenceNo.setChecked(isChecked);
        cbPurchaseImportItem.setChecked(isChecked);

        if (isChecked) {
            llPurchase.setVisibility(View.VISIBLE);
        }
        if (!isChecked) {
            llPurchase.setVisibility(View.GONE);

        }
    }

    private void setAllSalesChecked(boolean isChecked) {
        cbSalesItems.setChecked(isChecked);
        cbSalesAddCustomer.setChecked(isChecked);
        cbSalesSaveDraft.setChecked(isChecked);
        cbSalesClearDraft.setChecked(isChecked);
        cbSalesPrintList.setChecked(isChecked);
        cbSalesAdvacnePayment.setChecked(isChecked);
        cbSalesSaveSalesOrder.setChecked(isChecked);
        cbSalesAdvancePrintList.setChecked(isChecked);
        cbSalesInvokeSalesOrder.setChecked(isChecked);
        cbSalesClearAll.setChecked(isChecked);
        cbSalesCheckout.setChecked(isChecked);
        cbSalesReturn.setChecked(isChecked);
        cbSalesRecievePayment.setChecked(isChecked);
        cbSalesInvokeDraft.setChecked(isChecked);
        cbSalesSearchBarCode.setChecked(isChecked);
        cbSalesBarCode.setChecked(isChecked);
        cbSalesSearchSerializeItem.setChecked(isChecked);
        cbSalesSerializeItem.setChecked(isChecked);
        cbSalesCustomer.setChecked(isChecked);
        cbSalesImportItem.setChecked(isChecked);
        cbSalesTaxType.setChecked(isChecked);
        cbSalesInvLocAdd.setChecked(isChecked);
        cbSalesInvLoc.setChecked(isChecked);
        cbSalesStateAdd.setChecked(isChecked);
        cbSalesState.setChecked(isChecked);
        cbSalesRemoveItem.setChecked(isChecked);
        cbSalesMore.setChecked(isChecked);
        cbSalesContactAdd.setChecked(isChecked);
        cbSalesContact.setChecked(isChecked);
        cbSalesAgentAdd.setChecked(isChecked);
        cbSalesAgent.setChecked(isChecked);
        cbSalesCurrencyAdd.setChecked(isChecked);
        cbSalesCurrency.setChecked(isChecked);
        cbSalesEmpoloyeeAdd.setChecked(isChecked);
        cbSalesEmpoloyee.setChecked(isChecked);
        cbSalesExchangeRate.setChecked(isChecked);
        cbSalesExchangeRateAdd.setChecked(isChecked);
        cbSalesTndCAdd.setChecked(isChecked);
        cbSalesTandc.setChecked(isChecked);
        cbSalesCostCentreAdd.setChecked(isChecked);
        cbSalesCostCentre.setChecked(isChecked);
        cbSalesShippingMethodAdd.setChecked(isChecked);
        cbSalesShippingMethod.setChecked(isChecked);
        cbSalesShippingRefNo.setChecked(isChecked);
        cbSalesReferenceNo.setChecked(isChecked);
        cbSalesProforma.setChecked(isChecked);
        cbSalesInvokeProforma.setChecked(isChecked);
        cbSalesDeliveryOrder.setChecked(isChecked);
        cbSalesInvokeDelivery.setChecked(isChecked);
        cbSalesReturnDelivery.setChecked(isChecked);
        cbSalesDebitNote.setChecked(isChecked);
        cbSalesDraftInvoice.setChecked(isChecked);
        cbSalesQuotation.setChecked(isChecked);
        cbSalesInvokeQuotation.setChecked(isChecked);
        if (isChecked) {
            llSales.setVisibility(View.VISIBLE);
        }
        if (!isChecked) {
            llSales.setVisibility(View.GONE);

        }
    }

    private void setAllContactChecked(boolean isChecked) {
        cbContactContacts.setChecked(isChecked);
        cbContactSuppllier.setChecked(isChecked);
        cbContactCustomer.setChecked(isChecked);
        if (isChecked) {
            llContact1.setVisibility(View.VISIBLE);
        }
        if (!isChecked) {
            llContact1.setVisibility(View.GONE);

        }
    }

    private void setAllTaxChecked(boolean isChecked) {
        cbTaxConfig.setChecked(isChecked);
        cbTaxGST.setChecked(isChecked);
        cbTaxTax.setChecked(isChecked);
        cbTaxTaxType.setChecked(isChecked);
        cbTaxIndiaGSTR.setChecked(isChecked);
        cbTaxIndiaGSTR1.setChecked(isChecked);
        cbTaxIndiaGSTR2.setChecked(isChecked);
        cbTaxIndiaGST3B.setChecked(isChecked);
        cbTaxGSTTRAN1.setChecked(isChecked);
        cbTaxMalaysiaGSTR.setChecked(isChecked);
        cbTaxMalaysiaGAFExport.setChecked(isChecked);
        cbTaxMalaysiaGSTReturn.setChecked(isChecked);

        if (isChecked) {
            llTax1.setVisibility(View.VISIBLE);
            llTax2.setVisibility(View.VISIBLE);
            llTax3.setVisibility(View.VISIBLE);
            llTax4.setVisibility(View.VISIBLE);
            llTax5.setVisibility(View.VISIBLE);
        }
        if (!isChecked) {
            llTax1.setVisibility(View.GONE);
            llTax2.setVisibility(View.GONE);
            llTax3.setVisibility(View.GONE);
            llTax4.setVisibility(View.GONE);
            llTax5.setVisibility(View.GONE);

        }
    }

    private void setAllGenralTransationChecked(boolean isChecked) {
        cbGTExpense.setChecked(isChecked);
        cbGTJournalEntry.setChecked(isChecked);
        cbGTReciepts.setChecked(isChecked);

        if (isChecked) {
            llGeneralTransaction.setVisibility(View.VISIBLE);
        }
        if (!isChecked) {
            llGeneralTransaction.setVisibility(View.GONE);

        }
    }

    private void setAllDashBoradChecked(boolean isChecked) {
        cbDashBoardDateTime.setChecked(isChecked);
        cbDashBoardCashInHand.setChecked(isChecked);
        cbDashBoardCashInBank.setChecked(isChecked);
        cbDashBoardTotalRecievable.setChecked(isChecked);
        cbDashBoardTotalPayable.setChecked(isChecked);

        if (isChecked) {
            llDashBoard.setVisibility(View.VISIBLE);
        }
        if (!isChecked) {
            llDashBoard.setVisibility(View.GONE);

        }
    }

    private void updateUserPermission() {

        AddUserAccessPermissionData addUserPermissions = new AddUserAccessPermissionData();


        addUserPermissions.setDashboard(cbDashBoard.isChecked());
        addUserPermissions.setDashboardDateTime(cbDashBoardDateTime.isChecked());
        addUserPermissions.setDashboardCashInHand(cbDashBoardCashInHand.isChecked());
        addUserPermissions.setDashboardCashInBank(cbDashBoardCashInBank.isChecked());
        addUserPermissions.setDashboardTotalReceivable(cbDashBoardTotalRecievable.isChecked());
        addUserPermissions.setDashboardTotalPayable(cbDashBoardTotalPayable.isChecked());


        addUserPermissions.setContact(cbContact.isChecked());
        addUserPermissions.setContactContacts(cbContactContacts.isChecked());
        addUserPermissions.setContactSupplier(cbContactSuppllier.isChecked());
        addUserPermissions.setContactCustomer(cbContactCustomer.isChecked());

        addUserPermissions.setGt(cbGeneralTransaction.isChecked());
        addUserPermissions.setGtExpense(cbGTExpense.isChecked());
        addUserPermissions.setGtReciepts(cbGTReciepts.isChecked());
        addUserPermissions.setGtJournalEntry(cbGTJournalEntry.isChecked());

        addUserPermissions.setSales(cbSales.isChecked());
        addUserPermissions.setSalesItemAdd(cbSalesItems.isChecked());
        addUserPermissions.setSalesCustomerAdd(cbSalesAddCustomer.isChecked());
        addUserPermissions.setSalesSavesDraft(cbSalesSaveDraft.isChecked());
        addUserPermissions.setSalesClearDraft(cbSalesClearDraft.isChecked());
        addUserPermissions.setSalesPrintList(cbSalesPrintList.isChecked());
        addUserPermissions.setSalesAdvancePayment(cbSalesAdvacnePayment.isChecked());
        addUserPermissions.setSalesSaveSalesOrder(cbSalesSaveSalesOrder.isChecked());
        addUserPermissions.setSalesAdvancePaymentList(cbSalesAdvancePrintList.isChecked());
        addUserPermissions.setSalesInvokeSalesOrder(cbSalesInvokeSalesOrder.isChecked());
        addUserPermissions.setSalesClearAll(cbSalesClearAll.isChecked());
        addUserPermissions.setSalesCheckOut(cbSalesCheckout.isChecked());
        addUserPermissions.setSalesReturn(cbSalesReturn.isChecked());
        addUserPermissions.setSalesRecievePayment(cbSalesRecievePayment.isChecked());
        addUserPermissions.setSalesInvokeDraft(cbSalesInvokeDraft.isChecked());
        addUserPermissions.setSalesSearchBarCode(cbSalesSearchBarCode.isChecked());
        addUserPermissions.setSalesBarCode(cbSalesBarCode.isChecked());
        addUserPermissions.setSalesSearchSerializeItem(cbSalesSearchSerializeItem.isChecked());
        addUserPermissions.setSalesSerializeItem(cbSalesSerializeItem.isChecked());
        addUserPermissions.setSalesCustomer(cbSalesCustomer.isChecked());
        addUserPermissions.setSalesImportItem(cbSalesImportItem.isChecked());
        addUserPermissions.setSalesTaxType(cbSalesTaxType.isChecked());
        addUserPermissions.setSalesInvLocAdd(cbSalesInvLocAdd.isChecked());
        addUserPermissions.setSalesInvLoc(cbSalesInvLoc.isChecked());
        addUserPermissions.setSalesStateAdd(cbSalesStateAdd.isChecked());
        addUserPermissions.setSalesState(cbSalesState.isChecked());
        addUserPermissions.setSalesRemoveItem(cbSalesRemoveItem.isChecked());
        addUserPermissions.setSalesMore(cbSalesMore.isChecked());
        addUserPermissions.setSalesContactAdd(cbSalesContactAdd.isChecked());
        addUserPermissions.setSalesContact(cbSalesContact.isChecked());
        addUserPermissions.setSalesAgentAdd(cbSalesAgentAdd.isChecked());
        addUserPermissions.setSalesAgent(cbSalesAgent.isChecked());
        addUserPermissions.setSalesCurrencyAdd(cbSalesCurrencyAdd.isChecked());
        addUserPermissions.setSalesCurrency(cbSalesCurrency.isChecked());
        addUserPermissions.setSalesEmployeeAdd(cbSalesEmpoloyeeAdd.isChecked());
        addUserPermissions.setSalesEmployee(cbSalesEmpoloyee.isChecked());
        addUserPermissions.setSalesExchangeRate(cbSalesExchangeRate.isChecked());
        addUserPermissions.setSalesExchangeRateAdd(cbSalesExchangeRateAdd.isChecked());
        addUserPermissions.setSalesTndCAdd(cbSalesTndCAdd.isChecked());
        addUserPermissions.setSalesTandC(cbSalesTandc.isChecked());
        addUserPermissions.setSalesCCAdd(cbSalesCostCentreAdd.isChecked());
        addUserPermissions.setSalesCC(cbSalesCostCentre.isChecked());
        addUserPermissions.setSalesSMAdd(cbSalesShippingMethodAdd.isChecked());
        addUserPermissions.setSalesSM(cbSalesShippingMethod.isChecked());
        addUserPermissions.setSalesShippingRefNo(cbSalesShippingRefNo.isChecked());
        addUserPermissions.setSalesRefNo(cbSalesReferenceNo.isChecked());
        addUserPermissions.setSalesSaveProforma(cbSalesProforma.isChecked());
        addUserPermissions.setSalesInvokeProforma(cbSalesInvokeProforma.isChecked());
        addUserPermissions.setSalesSaveDeliveryOrder(cbSalesDeliveryOrder.isChecked());
        addUserPermissions.setSalesInvokeDeliveryOrder(cbSalesInvokeDelivery.isChecked());
        addUserPermissions.setSalesReturnDeliveryOrder(cbSalesReturnDelivery.isChecked());
        addUserPermissions.setSalesDebitNote(cbSalesDebitNote.isChecked());
        addUserPermissions.setDraftInvoice(cbSalesDraftInvoice.isChecked());
        addUserPermissions.setSalesSaveSalesQuotation(cbSalesQuotation.isChecked());
        addUserPermissions.setSalesInvokeSalesQuotation(cbSalesInvokeQuotation.isChecked());


        addUserPermissions.setPurchase(cbPurchase.isChecked());
        addUserPermissions.setPurchaseItemAdd(cbPurchaseItems.isChecked());
        addUserPermissions.setPurchaseSupplierAdd(cbPurchaseSupplier.isChecked());
        addUserPermissions.setPurchasePrintList(cbPurchasePrintList.isChecked());
        addUserPermissions.setPurchaseAdvancePayment(cbPurchaseAdvancePayment.isChecked());
        addUserPermissions.setPurchaseSavePurchaseOrder(cbPurchaseSavePurchaseOrder.isChecked());
        addUserPermissions.setPurchaseAdvancePaymentList(cbPruchaseAdvPaymentList.isChecked());
        addUserPermissions.setPurchaseInvokePurchaseOrder(cbPurchaseInvokePo.isChecked());
        addUserPermissions.setPurchaseClearAll(cbPurchaseClearAll.isChecked());
        addUserPermissions.setPurchaseCheckOut(cbPurchaseCheckOut.isChecked());
        addUserPermissions.setPurchaseReturn(cbPurchasaeReturn.isChecked());
        addUserPermissions.setPurchaseSupplierPayment(cbPurchaseSupplierPayment.isChecked());
        addUserPermissions.setPurchaseSelfBilled(cbPurchaseSelfBilled.isChecked());
        addUserPermissions.setPurSearchBarCode(cbPurchasaeSearchBarcode.isChecked());
        addUserPermissions.setPurBarCode(cbPurchaseBarcode.isChecked());
        addUserPermissions.setPurMore(cbPurchaseMore.isChecked());
        addUserPermissions.setPurCreditNote(cbPurchasaeCreditNote.isChecked());
        addUserPermissions.setPurRemoveItem(cbPurchaseRemoveItem.isChecked());
        addUserPermissions.setPurInvokeRecItem(cbPurchaseInVokeRecievedItem.isChecked());
        addUserPermissions.setPurReturnRecItem(cbPurchasaeReturnReceivedItem.isChecked());
        addUserPermissions.setPurchaseSavePurchaseQuotation(cbPurchaseSaveQuotation.isChecked());
        addUserPermissions.setPurchaseInvokePurchaseQuotation(cbPurchaseInvokeQuotation.isChecked());
        addUserPermissions.setPurTaxType(cbPurchasaeTaxType.isChecked());
        addUserPermissions.setPurInvLocAdd(cbPurchaseInventoryLocationAdd.isChecked());
        addUserPermissions.setPurInvLoc(cbPurchaseInventoryLocation.isChecked());
        addUserPermissions.setPurStateAdd(cbPurchasaeStateAdd.isChecked());
        addUserPermissions.setPurState(cbPurchaseState.isChecked());
        addUserPermissions.setPurContactAdd(cbPurchaseAddContact.isChecked());
        addUserPermissions.setPurContact(cbPurchasaeContact.isChecked());
        addUserPermissions.setPurAgentAdd(cbPurchaseAgentAdd.isChecked());
        addUserPermissions.setPurAgent(cbPurchaseAgent.isChecked());
        addUserPermissions.setPurCurrencyAdd(cbPurchaseAddCurrency.isChecked());
        addUserPermissions.setPurCurrency(cbPurchasecurrency.isChecked());
        addUserPermissions.setPurEmployeeAdd(cbPurchaseAddEmployee.isChecked());
        addUserPermissions.setPurEmployee(cbPurchaseEmployee.isChecked());
        addUserPermissions.setPurExchangeRateAdd(cbPurchaseAddExchangeRate.isChecked());
        addUserPermissions.setPurExchangeRate(cbPurchaseExchangeRate.isChecked());
        addUserPermissions.setPurTndCAdd(cbPurchaseTendCAdd.isChecked());
        addUserPermissions.setPurTandC(cbPurchaseTendC.isChecked());
        addUserPermissions.setPurCC(cbPurchaseCostCentre.isChecked());
        addUserPermissions.setPurCCAdd(cbPurchaseCostCentreAdd.isChecked());
        addUserPermissions.setPurSMAdd(cbPurchaseAddShippingMethod.isChecked());
        addUserPermissions.setPurSM(cbPurchaseShippingMethod.isChecked());
        addUserPermissions.setPurShippingRefNo(cbPurchaseShippingRefNo.isChecked());
        addUserPermissions.setPurRefNo(cbPurchaseRefenceNo.isChecked());
        addUserPermissions.setPurImportItem(cbPurchaseImportItem.isChecked());

        addUserPermissions.setTax(cbTax.isChecked());
        addUserPermissions.setTaxTaxConfiguration(cbTaxConfig.isChecked());
        addUserPermissions.setTaxGST(cbTaxGST.isChecked());
        addUserPermissions.setTaxTax(cbTaxTax.isChecked());
        addUserPermissions.setTaxTaxtype(cbTaxTaxType.isChecked());
        addUserPermissions.setGstIndiaGSTSR(cbTaxIndiaGSTR.isChecked());
        addUserPermissions.setGstIndiaGSTR1Tool(cbTaxIndiaGSTR1.isChecked());
        addUserPermissions.setGstIndiaGSTR2Tool(cbTaxIndiaGSTR2.isChecked());
        addUserPermissions.setGstIndiaGST3B(cbTaxIndiaGST3B.isChecked());
        addUserPermissions.setGstIndiaGSTTRANS1(cbTaxGSTTRAN1.isChecked());
        addUserPermissions.setGstMalGSTSR(cbTaxMalaysiaGSTR.isChecked());
        addUserPermissions.setGstMalGAFExport(cbTaxMalaysiaGAFExport.isChecked());
        addUserPermissions.setGstMalGSTReturn(cbTaxMalaysiaGSTReturn.isChecked());
        addUserPermissions.setGstIndiaRIPC(cbTaxReversalOfInputCredit.isChecked());

        addUserPermissions.setInventory(cbInventory.isChecked());
        addUserPermissions.setInventoryBrand(cbInventoryBrand.isChecked());
        addUserPermissions.setInventoryCategory(cbInventoryCategory.isChecked());
        addUserPermissions.setInventoryUOM(cbInventoryUOM.isChecked());
        addUserPermissions.setInventoryItem(cbInventoryItem.isChecked());
        addUserPermissions.setInventorySalesPricing(cbInventorySalesPricing.isChecked());
        addUserPermissions.setInventoryPurchasePricing(cbInventoryPurchasePricing.isChecked());
        addUserPermissions.setInventoryInventoryLocation(cbInventoryLocation.isChecked());
        addUserPermissions.setInventoryInventoryMovementType(cbInventoryMovementType.isChecked());
        addUserPermissions.setInventoryAttribute(cbInventoryAttribute.isChecked());
        addUserPermissions.setInventoryAttributeConfig(cbInventoryAttributeConfig.isChecked());
        addUserPermissions.setInventoryInvLocTransfer(cbInventoryLocationTransfer.isChecked());
        addUserPermissions.setInventoryInvCountType(cbInventoryCountType.isChecked());
        addUserPermissions.setInventorySalesDiscountConfig(cbInventorySalesDiscountConfiguration.isChecked());
        addUserPermissions.setInventoryVoucher(cbInventoryVoucher.isChecked());
        addUserPermissions.setInventoryItemCountAdjust(cbInventoryItemCountAdjust.isChecked());
        addUserPermissions.setInventoryHsnCode(cbInventoryHSNCode.isChecked());
        addUserPermissions.setInventoryItemCommission(cbInventoryItemCommission.isChecked());
        addUserPermissions.setInventoryAssets(cbInventoryAssests.isChecked());
        addUserPermissions.setInventoryUomConverter(cbInventoryUOMConverter.isChecked());
        addUserPermissions.setInventoryJSDN(cbInventoryISDN.isChecked());
        addUserPermissions.setInventoryAdvDisConfig(cbInventoryAdvanceDiscountConfiguration.isChecked());




        addUserPermissions.setControlPanel(cbCPanel.isChecked());
        addUserPermissions.setControlPanelOBofBS(cbCPanellOBofBS.isChecked());
        addUserPermissions.setControlPanelOBofPI(cbCPanellOBofPI.isChecked());
        addUserPermissions.setControlPanelOBofSI(cbCPanellOBofSI.isChecked());
        addUserPermissions.setControlPanelOBofINV(cbCPanellOBofINV.isChecked());
        addUserPermissions.setControlPanelLastYearFigure(cbCPanelLastYrFig.isChecked());
        addUserPermissions.setControlPanelMonthEnd(cbCPanelMonthEnd.isChecked());
        addUserPermissions.setControlPanelYearEnd(cbCPanelYearEnd.isChecked());
        addUserPermissions.setControlPanelConfigurator(cbCPanelConfigurator.isChecked());
        addUserPermissions.setcPConfiguratorBank(cbCPanelConfigBank.isChecked());
        addUserPermissions.setcPConfiguratorAgent(cbCPanelConfigAgent.isChecked());
        addUserPermissions.setcPConfiguratorEmployee(cbCPanelConfigEmployee.isChecked());
        addUserPermissions.setcPConfiguratorCurrency(cbCPanelConfigCurrency.isChecked());
        addUserPermissions.setcPConfiguratorCurrency(cbCPanelConfigExRate.isChecked());
        addUserPermissions.setcPConfiguratorProjectTitle(cbCPanelConfigProject.isChecked());
        addUserPermissions.setcPConfiguratorShippingMethod(cbCPanelShipping.isChecked());
        addUserPermissions.setcPConfiguratorEmailserver(cbCPanelConfigEmail.isChecked());
        addUserPermissions.setcPConfiguratorPaymentMethod(cbCPanelConfigPayment.isChecked());
        addUserPermissions.setcPConfiguratorConfiguration(cbCPanelConfigConfig.isChecked());
        addUserPermissions.setControlPanelCompanySetup(cbCPanelCompnaySetup.isChecked());
        addUserPermissions.setcPCompanySetupCI(cbCPanelCompnaySetupCI.isChecked());
        addUserPermissions.setcPCompanySetupFY(cbCPanelCompnaySetupFY.isChecked());
        addUserPermissions.setcPCompanySetupCA(cbCPanelCompnaySetupCA.isChecked());
        addUserPermissions.setcPCompanySetupHSN(cbCPanelCompnaySetupHsn.isChecked());
        addUserPermissions.setControlPanelUAS(cbCPanelUAS.isChecked());
        addUserPermissions.setControlPanelUASuas(cbCPanelUASuas.isChecked());
        addUserPermissions.setControlPanelUASat(cbCPanelUASat.isChecked());
        addUserPermissions.setControlPanelAccountMaintenance(cbCPanelActMaintenance.isChecked());
        addUserPermissions.setControlPanelAMag(cbCPanellAMag.isChecked());
        addUserPermissions.setControlPanelAMcoa(cbCPanellAMcoa.isChecked());
        addUserPermissions.setControlPanelAMbudget(cbCPanellAMbudget.isChecked());
        addUserPermissions.setControlPanelDisplaySetup(cbCPanelDisplaySetup.isChecked());
        addUserPermissions.setControlPanelDSfs(cbCPanellDSfs.isChecked());
        addUserPermissions.setControlPanelDSlayout(cbCPanelDSlayout.isChecked());
        addUserPermissions.setControlPanelDStermsCond(cblDStermsCond.isChecked());
        addUserPermissions.setcPConfiguratorCountry(cblDSCountry.isChecked());
        addUserPermissions.setcPConfiguratorVersionControl(cblDSVersionControl.isChecked());
        addUserPermissions.setcPConfiguratorLabelPrintConfig(cblDSLabelPrintConfigurator.isChecked());
        addUserPermissions.setcPCompanySetupBandR(cblDSBackUpandRestore.isChecked());
        addUserPermissions.setControlPanelDSTableConfig(cblDSTableConfiguration.isChecked());

        addUserPermissions.setReport(cbReports.isChecked());
        addUserPermissions.setReportPurchaseSSR(cbReportsSupplierSalesReport.isChecked());
        addUserPermissions.setReportPurchasePOL(cbReportsPurchaseOrderRegister.isChecked());
        addUserPermissions.setReportPurchasePIL(cbReportsPurchaseInvoiceRegister.isChecked());
        addUserPermissions.setReportPurchasePRL(cbReportsPurchaseReturnRegister.isChecked());
        addUserPermissions.setReportPurchaseSP(cbReportsSupplierPayment2.isChecked());
        addUserPermissions.setReportPurchasePA(cbReportsSupplierItemStockCheck.isChecked());
        addUserPermissions.setReportPurchaseRIL(cbReportsReceiveItemRegister.isChecked());
        addUserPermissions.setReportPurchaseSSL(cbReportsSupplierStatementRegister.isChecked());
        addUserPermissions.setReportPurchaseSL(cbReportsReportSale.isChecked());
        addUserPermissions.setReportSaleSOL(cbReportsSalesOrderRegister.isChecked());
        addUserPermissions.setReportSaleICL(cbReportsItemCommissionRegister.isChecked());
        addUserPermissions.setReportSaleSIL(cbReportsSalesInvoiceRegister.isChecked());
        addUserPermissions.setReportSaleSRL(cbReportsSalesReturnRegister.isChecked());
        addUserPermissions.setReportSalePR(cbReportsSalePR.isChecked());
        addUserPermissions.setReportSalePL(cbReportsSalePriceRegister.isChecked());
        addUserPermissions.setReportSaleCA(cbReportsCustomerDues.isChecked());
        addUserPermissions.setReportSaleCP(cbReportsCustomerPayment.isChecked());
        addUserPermissions.setReportSaleSA(cbReportsSaleSA.isChecked());
        addUserPermissions.setReportSaleSQL(cbReportsSalesQuotationRegister.isChecked());
        addUserPermissions.setReportSalePDL(cbReportsProductDeliveryregister.isChecked());
        addUserPermissions.setReportSaleCS(cbReportsCustomerStatementRegister.isChecked());
        addUserPermissions.setReportSaleSA(cbReportsSalesAdjustment.isChecked());
        addUserPermissions.setReportSaleCL(ccbReportsCustomerRegister.isChecked());
        addUserPermissions.setReportSaleAL(cbReportsAgentRegister.isChecked());
        addUserPermissions.setReportSaleCSI(cbReportsCancelledSalesInvoiceRegister.isChecked());
        addUserPermissions.setReportInventoryIVR(cbReportsInventoryValuation.isChecked());
        addUserPermissions.setReportInventorySI(cbReportsInventorySales.isChecked());
        addUserPermissions.setReportInventoryGRNVR(cbReportsGRNValuation.isChecked());
        addUserPermissions.setReportInventoryIPR(cbReportsInventoryPlanning.isChecked());
        addUserPermissions.setReportInventoryINMR(cbReportsInventoryNonMovement.isChecked());
        addUserPermissions.setReportInventoryIMR(cbReportsInventoryMovement.isChecked());
        addUserPermissions.setReportInventoryIL(cbReportsInventoryRegister.isChecked());
        addUserPermissions.setReportInventoryOGRNR(cbReportsOutstandingGRN.isChecked());
        addUserPermissions.setReportInventoryBER(cbReportsBatchExpiry.isChecked());
        addUserPermissions.setReportInventorySI(cbReportsSerializationItems.isChecked());
        addUserPermissions.setReportInventoryBI(cbReportsBatchItems.isChecked());
        addUserPermissions.setReportInventoryADVSCR(cbReportsAdvanceStockCheck.isChecked());
        addUserPermissions.setReportInventoryADVSSR(cbReportsAdvanceSuppliersales.isChecked());
        addUserPermissions.setReportAnalysis(cbReportsReportAnalysis.isChecked());
        addUserPermissions.setReportAnalysisTopVendor(cbReportsTopVendor.isChecked());
        addUserPermissions.setReportAnalysisTopItem(cbReportsTopItem.isChecked());
        addUserPermissions.setReportAnalysisTopCustomer(cbReportsTopCustomer.isChecked());
        addUserPermissions.setReportAnalysisAccountBalance(cbReportsAccountBalance.isChecked());
        addUserPermissions.setReportAnalysisCRR(cbReportsCustomerReport.isChecked());
        addUserPermissions.setReportAnalysisCIP(cbReportsCustomerPayment.isChecked());
        addUserPermissions.setReportAnalysisSIP(cbReportsSupplierPayment.isChecked());
        addUserPermissions.setReportFinancialStatement(cbReportsFinancialStatement.isChecked());
        addUserPermissions.setReportBankRecon(cbReportsBankReconciliation.isChecked());
        addUserPermissions.setReportBankReconCheques(cbReportsBankReconCheque.isChecked());
        addUserPermissions.setReportBankReconStmnt(cbReportsBankReconciliationStatement.isChecked());



        addUserPermissions.setUserAccountSetupID(userDetail.getUseraccountId());

        String url = serverUrl + "/hipos//0/" + Constant.FUNTION_SAVEUSERPERMISSION;
        // prepare the Request
        if (serverUrl != null) {

            UtilView.showProgessBar(activity, progressBar);
            PostDataTask task = new PostDataTask(activity, new AsyncTaskCompleteListener<String>() {
                @Override
                public void onTaskComplete(String result) {
                    UtilView.hideProgessBar(activity, progressBar);

                    if (result != null) {
                        if (result.equals("invalid")) {
                            UtilView.showToast(activity, getResources().getString(R.string.accesstoken_error));
                            Intent intent = new Intent(activity, Activity_Login.class);
                            activity.startActivity(intent);
                            activity.finish();
                        } else {

                            Gson gson = new Gson();
                            try {
                                JSONObject jsonObject = new JSONObject(result.toString());
                                AddUserAccessPermissionData  addUserPermissions = gson.fromJson(jsonObject.toString(), AddUserAccessPermissionData.class);
                                if (addUserPermissions != null) {
                                    getPageLoadDataForAll();
                                    UtilView.showToast(activity, "Permission update Successfully");
                                    Intent returnIntent = new Intent();
                                    activity.setResult(Activity.RESULT_OK, returnIntent);
                                    activity.finish();

                                } else {
                                    UtilView.showToast(activity, "Permission didn't update Successfully");
                                    Intent returnIntent = new Intent();
                                    activity.setResult(Activity.RESULT_OK, returnIntent);
                                    activity.finish();
                                }


                            } catch (Exception e) {
                                // UtilView.showLogCat(TAG, "TaskCompleteListener Exception " + e.toString());
                            }
                        }
                    } else {
                        UtilView.showErrorDialog(getResources().getString(R.string.error_null), activity);
                    }
                }
            }, false);
            task.execute(new Gson().toJson(addUserPermissions).toString(), url, "");
        } else {
            UtilView.gotToLogin(activity);
        }
    }

    private void getPageLoadDataForAll() {

        String url = serverUrl + "/hipos//1/" + Constant.FUNCTION_GETPAGELOADDATA;
        isInternatePrsenet = serviceHandler.isConnectingToInternet();
        if (serverUrl != null) {
            if (isInternatePrsenet) {
                UtilView.showProgessBar(activity, progressBar);
                GetDataTask getDataTask = new GetDataTask(activity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(activity, progressBar);
                        if (result != null) {
                            try {

                                sharedPreference.saveData(Constant.GETPAGELOADDATA_KEY, result.toString());
                                Gson gson = new Gson();
                                PageLoadDataForAll pageLoadDataForAll = gson.fromJson(result.toString(), PageLoadDataForAll.class);

                            } catch (Exception e) {
                                UtilView.showErrorDialog(getResources().getString(response_error), activity);
                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(response_error), activity);
                        }
                    }
                }, false);
                getDataTask.execute(url, "");
            } else {
                UtilView.showErrorDialog(activity.getResources().getString(R.string.intertnet_status), activity);
            }
        } else {
            UtilView.gotToLogin(activity);
        }
    }
}
