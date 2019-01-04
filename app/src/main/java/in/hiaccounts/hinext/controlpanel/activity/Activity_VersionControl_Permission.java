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
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.versioncontrol.VersionControlData;
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.versioncontrol.VersionControlPermissions;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

public class Activity_VersionControl_Permission extends AppCompatActivity {

    public static String TAG = Activity_VersionControl_Permission.class.getSimpleName();
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
    @BindView(R.id.cbSalesCustomer)
    CheckBox cbSalesCustomer;
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
    @BindView(R.id.llInventory3)
    LinearLayout llInventory3;
    @BindView(R.id.cbTax)
    CheckBox cbTax;
    @BindView(R.id.cbTaxConfig)
    CheckBox cbTaxConfig;
    @BindView(R.id.cbTaxGST)
    CheckBox cbTaxGST;
    @BindView(R.id.llTax1)
    LinearLayout llTax1;
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
    @BindView(R.id.llControlPanel12)
    LinearLayout llControlPanel12;
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
    @BindView(R.id.cbReportsPurchase)
    CheckBox cbReportsPurchase;
    @BindView(R.id.cbReportsPurchaseSSR)
    CheckBox cbReportsPurchaseSSR;
    @BindView(R.id.cbReportsPurchasePOL)
    CheckBox cbReportsPurchasePOL;
    @BindView(R.id.llReport1)
    LinearLayout llReport1;
    @BindView(R.id.cbReportsContacts)
    CheckBox cbReportsContacts;
    @BindView(R.id.cbReportsPurchasePRL)
    CheckBox cbReportsPurchasePRL;
    @BindView(R.id.cbReportsPurchaseSP)
    CheckBox cbReportsPurchaseSP;
    @BindView(R.id.llreport2)
    LinearLayout llreport2;
    @BindView(R.id.cbReportsPurchasePA)
    CheckBox cbReportsPurchasePA;
    @BindView(R.id.cbReportsSale)
    CheckBox cbReportsSale;
    @BindView(R.id.cbReportsSaleSOL)
    CheckBox cbReportsSaleSOL;
    @BindView(R.id.llReport3)
    LinearLayout llReport3;
    @BindView(R.id.cbReportsSaleICL)
    CheckBox cbReportsSaleICL;
    @BindView(R.id.cbReportsSaleSIL)
    CheckBox cbReportsSaleSIL;
    @BindView(R.id.cbReportsSaleSRL)
    CheckBox cbReportsSaleSRL;
    @BindView(R.id.llReport4)
    LinearLayout llReport4;
    @BindView(R.id.cbReportsSalePR)
    CheckBox cbReportsSalePR;
    @BindView(R.id.cbReportsSalePL)
    CheckBox cbReportsSalePL;
    @BindView(R.id.cbReportsSaleCA)
    CheckBox cbReportsSaleCA;
    @BindView(R.id.llReport5)
    LinearLayout llReport5;
    @BindView(R.id.cbReportsSaleCP)
    CheckBox cbReportsSaleCP;
    @BindView(R.id.cbReportsSaleSA)
    CheckBox cbReportsSaleSA;
    @BindView(R.id.cbReportsInventory)
    CheckBox cbReportsInventory;
    @BindView(R.id.llReport6)
    LinearLayout llReport6;
    @BindView(R.id.cbReportsInventryIVR)
    CheckBox cbReportsInventryIVR;
    @BindView(R.id.cbReportsInventryISR)
    CheckBox cbReportsInventryISR;
    @BindView(R.id.cbReportsInventryISCR)
    CheckBox cbReportsInventryISCR;
    @BindView(R.id.llReport7)
    LinearLayout llReport7;
    @BindView(R.id.cbReportsAnalysis)
    CheckBox cbReportsAnalysis;
    @BindView(R.id.cbReportsVendor)
    CheckBox cbReportsVendor;
    @BindView(R.id.cbReportsItem)
    CheckBox cbReportsItem;
    @BindView(R.id.llReport8)
    LinearLayout llReport8;
    @BindView(R.id.cbReportsCustomer)
    CheckBox cbReportsCustomer;
    @BindView(R.id.ccbReportsStatment)
    CheckBox ccbReportsStatment;
    @BindView(R.id.cbReportsStatementPL)
    CheckBox cbReportsStatementPL;
    @BindView(R.id.llReport9)
    LinearLayout llReport9;
    @BindView(R.id.cbReportsStatementBS)
    CheckBox cbReportsStatementBS;
    @BindView(R.id.cbReportsStatementTB)
    CheckBox cbReportsStatementTB;
    @BindView(R.id.cbReportsStatementGL)
    CheckBox cbReportsStatementGL;
    @BindView(R.id.llReport10)
    LinearLayout llReport10;
    @BindView(R.id.cbReportsStatementVL)
    CheckBox cbReportsStatementVL;
    @BindView(R.id.cbReportsStatementGJ)
    CheckBox cbReportsStatementGJ;
    @BindView(R.id.cbReportsBankRecon)
    CheckBox cbReportsBankRecon;
    @BindView(R.id.llReport11)
    LinearLayout llReport11;
    @BindView(R.id.cbReportsBankReconRatio)
    CheckBox cbReportsBankReconRatio;
    @BindView(R.id.llReport12)
    LinearLayout llReport12;
    @BindView(R.id.cbHelp)
    CheckBox cbHelp;
    @BindView(R.id.cbHelpItem)
    CheckBox cbHelpItem;
    @BindView(R.id.cbHelpCustomer)
    CheckBox cbHelpCustomer;
    @BindView(R.id.cbHelpSalesOrder)
    CheckBox cbHelpSalesOrder;
    @BindView(R.id.llHelp1)
    LinearLayout llHelp1;
    @BindView(R.id.cbHelpAdvPaymnet)
    CheckBox cbHelpAdvPaymnet;
    @BindView(R.id.cbHelpInvokeDraft)
    CheckBox cbHelpInvokeDraft;
    @BindView(R.id.cbHelpRecievePayment)
    CheckBox cbHelpRecievePayment;
    @BindView(R.id.llHelp2)
    LinearLayout llHelp2;
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
    String callingFor, serverUrl;
    VersionControlData vcontrolPermissiondetails;
    VersionControlPermissions vPermissions;
    private Activity activity;
    private ServiceHandler serviceHandler;
    private boolean isInternatePrsenet = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version_control_permission);
        ButterKnife.bind(this);
        initComponentView();
    }

    private void initComponentView() {
        activity = this;
        serviceHandler = new ServiceHandler(activity);
        serverUrl = UtilView.getUrl(activity);
        Intent intent = getIntent();
        vcontrolPermissiondetails = (VersionControlData) intent.getSerializableExtra("versionPermissionData");

        if (vcontrolPermissiondetails != null && vcontrolPermissiondetails.getId() != null) {
            getVersionControlPermissionsSetup();
        }

        toolbar.setTitle("Update Version Control Permission");
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

    private void getVersionControlPermissionsSetup() {
        String url = serverUrl + "/version//1/" + Constant.FUNTION_GTEVERSIONCONTROLPERMISSION + vcontrolPermissiondetails.getId();
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
                                    vPermissions = gson.fromJson(jsonObject.toString(), VersionControlPermissions.class);
                                    if (vPermissions != null) {
                                        setUpView(vPermissions);
                                    }


                                } catch (Exception e) {
                                    UtilView.showLogCat(TAG, "TaskCompleteListener Exception " + e.toString());
                                }
                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.error_null), activity);
                        }
                    }
                }, false);
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("versionControlIdSearchText", vcontrolPermissiondetails.getId());
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

    private void setUpView(VersionControlPermissions versionPermissions) {

        cbContact.setChecked(versionPermissions.getContact());
        cbContactContacts.setChecked(versionPermissions.getContactContacts());
        cbContactSuppllier.setChecked(versionPermissions.getContactSupplier());
        cbContactCustomer.setChecked(versionPermissions.getContactCustomer());

        cbInventory.setChecked(versionPermissions.getInventory());
        cbInventoryBrand.setChecked(versionPermissions.getInventoryBrand());
        cbInventoryCategory.setChecked(versionPermissions.getInventoryCategory());
        cbInventoryUOM.setChecked(versionPermissions.getInventoryUOM());
        cbInventoryItem.setChecked(versionPermissions.getInventoryItem());
        cbInventorySalesPricing.setChecked(versionPermissions.getInventorySalesPricing());
        cbInventoryPurchasePricing.setChecked(versionPermissions.getInventoryPurchasePricing());
        cbInventoryLocation.setChecked(versionPermissions.getInventoryInventoryLocation());
        cbInventoryMovementType.setChecked(versionPermissions.getInventoryInventoryMovementType());

        cbTax.setChecked(versionPermissions.getTax());
        cbTaxConfig.setChecked(versionPermissions.getTaxTaxConfiguration());
        cbTaxGST.setChecked(versionPermissions.getTaxGST());


        cbCPanel.setChecked(versionPermissions.getControlPanel());
        cbCPanellOBofBS.setChecked(versionPermissions.getControlPanelOBofBS());
        cbCPanellOBofPI.setChecked(versionPermissions.getControlPanelOBofPI());
        cbCPanellOBofSI.setChecked(versionPermissions.getControlPanelOBofSI());
        cbCPanellOBofINV.setChecked(versionPermissions.getControlPanelOBofINV());
        cbCPanelLastYrFig.setChecked(versionPermissions.getControlPanelLastYearFigure());
        cbCPanelMonthEnd.setChecked(versionPermissions.getControlPanelMonthEnd());
        cbCPanelYearEnd.setChecked(versionPermissions.getControlPanelYearEnd());
        cbCPanelConfigurator.setChecked(versionPermissions.getControlPanelConfigurator());
        cbCPanelConfigBank.setChecked(versionPermissions.getCPConfiguratorBank());
        cbCPanelConfigAgent.setChecked(versionPermissions.getCPConfiguratorAgent());
        cbCPanelConfigEmployee.setChecked(versionPermissions.getCPConfiguratorEmployee());
        cbCPanelConfigCurrency.setChecked(versionPermissions.getCPConfiguratorCurrency());
        cbCPanelConfigExRate.setChecked(versionPermissions.getCPConfiguratorCurrency());
        cbCPanelConfigProject.setChecked(versionPermissions.getCPConfiguratorProjectTitle());
        cbCPanelShipping.setChecked(versionPermissions.getCPConfiguratorShippingMethod());
        cbCPanelConfigEmail.setChecked(versionPermissions.getCPConfiguratorEmailserver());
        cbCPanelConfigPayment.setChecked(versionPermissions.getCPConfiguratorPaymentMethod());
        cbCPanelConfigConfig.setChecked(versionPermissions.getCPConfiguratorConfiguration());
        cbCPanelCompnaySetup.setChecked(versionPermissions.getControlPanelCompanySetup());
        cbCPanelCompnaySetupCI.setChecked(versionPermissions.getCPCompanySetupCI());
        cbCPanelCompnaySetupFY.setChecked(versionPermissions.getCPCompanySetupFY());
        cbCPanelCompnaySetupCA.setChecked(versionPermissions.getCPCompanySetupCA());
        cbCPanelCompnaySetupHsn.setChecked(versionPermissions.getCPCompanySetupHSN());
        cbCPanelUAS.setChecked(versionPermissions.getControlPanelUAS());
        cbCPanelUASuas.setChecked(versionPermissions.getControlPanelUASuas());
        cbCPanelUASat.setChecked(versionPermissions.getControlPanelUASat());
        cbCPanelActMaintenance.setChecked(versionPermissions.getControlPanelAccountMaintenance());
        cbCPanellAMag.setChecked(versionPermissions.getControlPanelAMag());
        cbCPanellAMcoa.setChecked(versionPermissions.getControlPanelAMcoa());
        cbCPanellAMbudget.setChecked(versionPermissions.getControlPanelAMbudget());
        cbCPanelDisplaySetup.setChecked(versionPermissions.getControlPanelDisplaySetup());
        cbCPanellDSfs.setChecked(versionPermissions.getControlPanelDSfs());
        cbCPanelDSlayout.setChecked(versionPermissions.getControlPanelDSlayout());
        cblDStermsCond.setChecked(versionPermissions.getControlPanelDStermsCond());

        cbReports.setChecked(versionPermissions.getReport());
        cbReportsPurchase.setChecked(versionPermissions.getReportPurchase());
        cbReportsPurchaseSSR.setChecked(versionPermissions.getReportPurchaseSSR());
        cbReportsPurchasePOL.setChecked(versionPermissions.getReportPurchasePOL());
        cbReportsContacts.setChecked(versionPermissions.getContact());
        cbReportsPurchasePRL.setChecked(versionPermissions.getReportPurchasePRL());
        cbReportsPurchaseSP.setChecked(versionPermissions.getReportPurchaseSP());
        cbReportsPurchasePA.setChecked(versionPermissions.getReportPurchasePA());
        cbReportsSale.setChecked(versionPermissions.getReportSale());
        cbReportsSaleSOL.setChecked(versionPermissions.getReportSaleSOL());
        cbReportsSaleICL.setChecked(versionPermissions.getReportSaleICL());
        cbReportsSaleSIL.setChecked(versionPermissions.getReportSaleSIL());
        cbReportsSaleSRL.setChecked(versionPermissions.getReportSaleSRL());
        cbReportsSalePR.setChecked(versionPermissions.getReportSalePR());
        cbReportsSalePL.setChecked(versionPermissions.getReportSalePL());
        cbReportsSaleCA.setChecked(versionPermissions.getReportSaleCA());
        cbReportsSaleCP.setChecked(versionPermissions.getReportSaleCP());
        cbReportsSaleSA.setChecked(versionPermissions.getReportSaleSA());
        cbReportsInventory.setChecked(versionPermissions.getReportInventory());
        cbReportsInventryIVR.setChecked(versionPermissions.getReportInventoryIVR());
        cbReportsInventryISR.setChecked(versionPermissions.getReportInventoryISR());
        cbReportsInventryISCR.setChecked(versionPermissions.getReportInventoryilSCR());
        cbReportsAnalysis.setChecked(versionPermissions.getReportAnalysis());
        cbReportsVendor.setChecked(versionPermissions.getReportAnalysisTopVendor());
        cbReportsItem.setChecked(versionPermissions.getReportAnalysisTopItem());
        cbReportsCustomer.setChecked(versionPermissions.getReportAnalysisTopCustomer());
        ccbReportsStatment.setChecked(versionPermissions.getReportFinancialStatement());
        cbReportsStatementPL.setChecked(versionPermissions.getReportFStatementPL());
        cbReportsStatementBS.setChecked(versionPermissions.getReportFStatementBS());
        cbReportsStatementTB.setChecked(versionPermissions.getReportFStatementTB());
        cbReportsStatementGL.setChecked(versionPermissions.getReportFStatementGL());
        cbReportsStatementVL.setChecked(versionPermissions.getReportFStatementVL());
        cbReportsStatementGJ.setChecked(versionPermissions.getReportFStatementGJ());
        cbReportsBankRecon.setChecked(versionPermissions.getReportBankRecon());
        cbReportsBankReconRatio.setChecked(versionPermissions.getReportBankReconRatio());

        //cbHelp.setChecked(versionPermissions.getReport());
        cbReportsPurchase.setChecked(versionPermissions.getReportPurchase());

        cbDashBoard.setChecked(versionPermissions.getDashboard());
        cbDashBoardDateTime.setChecked(versionPermissions.getDashboardDateTime());
        cbDashBoardCashInHand.setChecked(versionPermissions.getDashboardCashInHand());
        cbDashBoardCashInBank.setChecked(versionPermissions.getDashboardCashInBank());
        cbDashBoardTotalRecievable.setChecked(versionPermissions.getDashboardTotalReceivable());
        cbDashBoardTotalPayable.setChecked(versionPermissions.getDashboardTotalPayable());

        cbSales.setChecked(versionPermissions.getSales());
        cbSalesItems.setChecked(versionPermissions.getSalesItemAdd());
        cbSalesCustomer.setChecked(versionPermissions.getSalesCustomerAdd());
        cbSalesSaveDraft.setChecked(versionPermissions.getSalesSavesDraft());
        cbSalesClearDraft.setChecked(versionPermissions.getSalesClearDraft());
        cbSalesPrintList.setChecked(versionPermissions.getSalesPrintList());
        cbSalesAdvacnePayment.setChecked(versionPermissions.getSalesAdvancePayment());
        cbSalesSaveSalesOrder.setChecked(versionPermissions.getSalesSaveSalesOrder());
        cbSalesAdvancePrintList.setChecked(versionPermissions.getSalesAdvancePaymentList());
        cbSalesInvokeSalesOrder.setChecked(versionPermissions.getSalesInvokeSalesOrder());
        cbSalesClearAll.setChecked(versionPermissions.getSalesClearAll());
        cbSalesCheckout.setChecked(versionPermissions.getSalesCheckOut());
        cbSalesReturn.setChecked(versionPermissions.getSalesReturn());
        cbSalesRecievePayment.setChecked(versionPermissions.getSalesRecievePayment());
        cbSalesInvokeDraft.setChecked(versionPermissions.getSalesInvokeDraft());


        cbPurchase.setChecked(versionPermissions.getPurchase());
        cbPurchaseItems.setChecked(versionPermissions.getPurchaseItemAdd());
        cbPurchaseSupplier.setChecked(versionPermissions.getPurchaseSupplierAdd());
        cbPurchasePrintList.setChecked(versionPermissions.getPurchasePrintList());
        cbPurchaseAdvancePayment.setChecked(versionPermissions.getPurchaseAdvancePayment());
        cbPurchaseSavePurchaseOrder.setChecked(versionPermissions.getPurchaseSavePurchaseOrder());
        cbPruchaseAdvPaymentList.setChecked(versionPermissions.getPurchaseAdvancePaymentList());
        cbPurchaseInvokePo.setChecked(versionPermissions.getPurchaseInvokePurchaseOrder());
        cbPurchaseClearAll.setChecked(versionPermissions.getPurchaseClearAll());
        cbPurchaseCheckOut.setChecked(versionPermissions.getPurchaseCheckOut());
        cbPurchasaeReturn.setChecked(versionPermissions.getPurchaseReturn());
        cbPurchaseSupplierPayment.setChecked(versionPermissions.getPurchaseSupplierPayment());
        cbPurchaseSelfBilled.setChecked(versionPermissions.getPurchaseSelfBilled());

        cbGeneralTransaction.setChecked(versionPermissions.getGt());
        cbGTExpense.setChecked(versionPermissions.getGtExpense());
        cbGTReciepts.setChecked(versionPermissions.getGtReciepts());
        cbGTJournalEntry.setChecked(versionPermissions.getGtJournalEntry());


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

    private void setAllSalesChecked(boolean isChecked) {
        cbSalesItems.setChecked(isChecked);
        cbSalesCustomer.setChecked(isChecked);
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

        if (isChecked) {
            llSales.setVisibility(View.VISIBLE);
        }
        if (!isChecked) {
            llSales.setVisibility(View.GONE);

        }
    }

    private void setAllPurchaseChecked(boolean isChecked) {
        cbPurchaseItems.setChecked(isChecked);
        cbPurchaseSupplier.setChecked(isChecked);
        cbPurchasePrintList.setChecked(isChecked);
        cbPurchaseAdvancePayment.setChecked(isChecked);
        cbPurchaseSavePurchaseOrder.setChecked(isChecked);
        cbPruchaseAdvPaymentList.setChecked(isChecked);
        cbPurchaseClearAll.setChecked(isChecked);
        cbPurchaseInvokePo.setChecked(isChecked);
        cbPurchasaeReturn.setChecked(isChecked);
        cbPurchaseCheckOut.setChecked(isChecked);
        cbPurchaseSupplierPayment.setChecked(isChecked);
        cbPurchaseSelfBilled.setChecked(isChecked);


        if (isChecked) {
            llPurchase.setVisibility(View.VISIBLE);
        }
        if (!isChecked) {
            llPurchase.setVisibility(View.GONE);

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

        vPermissions.setContact(cbContact.isChecked());
        vPermissions.setContactContacts(cbContactContacts.isChecked());
        vPermissions.setContactSupplier(cbContactSuppllier.isChecked());
        vPermissions.setContactCustomer(cbContactCustomer.isChecked());

        vPermissions.setInventory(cbInventory.isChecked());
        vPermissions.setInventoryBrand(cbInventoryBrand.isChecked());
        vPermissions.setInventoryCategory(cbInventoryCategory.isChecked());
        vPermissions.setInventoryUOM(cbInventoryUOM.isChecked());
        vPermissions.setInventoryItem(cbInventoryItem.isChecked());
        vPermissions.setInventorySalesPricing(cbInventorySalesPricing.isChecked());
        vPermissions.setInventoryPurchasePricing(cbInventoryPurchasePricing.isChecked());
        vPermissions.setInventoryInventoryLocation(cbInventoryLocation.isChecked());
        vPermissions.setInventoryInventoryMovementType(cbInventoryMovementType.isChecked());

        vPermissions.setTax(cbTax.isChecked());
        vPermissions.setTaxTaxConfiguration(cbTaxConfig.isChecked());
        vPermissions.setTaxGST(cbTaxGST.isChecked());

        vPermissions.setControlPanel(cbCPanel.isChecked());
        vPermissions.setControlPanelOBofBS(cbCPanellOBofBS.isChecked());
        vPermissions.setControlPanelOBofPI(cbCPanellOBofPI.isChecked());
        vPermissions.setControlPanelOBofSI(cbCPanellOBofSI.isChecked());
        vPermissions.setControlPanelOBofINV(cbCPanellOBofINV.isChecked());
        vPermissions.setControlPanelLastYearFigure(cbCPanelLastYrFig.isChecked());
        vPermissions.setControlPanelMonthEnd(cbCPanelMonthEnd.isChecked());
        vPermissions.setControlPanelYearEnd(cbCPanelYearEnd.isChecked());
        vPermissions.setControlPanelConfigurator(cbCPanelConfigurator.isChecked());
        vPermissions.setCPConfiguratorBank(cbCPanelConfigBank.isChecked());
        vPermissions.setCPConfiguratorAgent(cbCPanelConfigAgent.isChecked());
        vPermissions.setCPConfiguratorEmployee(cbCPanelConfigEmployee.isChecked());
        vPermissions.setCPConfiguratorCurrency(cbCPanelConfigCurrency.isChecked());
        vPermissions.setCPConfiguratorCurrency(cbCPanelConfigExRate.isChecked());
        vPermissions.setCPConfiguratorProjectTitle(cbCPanelConfigProject.isChecked());
        vPermissions.setCPConfiguratorShippingMethod(cbCPanelShipping.isChecked());
        vPermissions.setCPConfiguratorEmailserver(cbCPanelConfigEmail.isChecked());
        vPermissions.setCPConfiguratorPaymentMethod(cbCPanelConfigPayment.isChecked());
        vPermissions.setCPConfiguratorConfiguration(cbCPanelConfigConfig.isChecked());
        vPermissions.setControlPanelCompanySetup(cbCPanelCompnaySetup.isChecked());
        vPermissions.setCPCompanySetupCI(cbCPanelCompnaySetupCI.isChecked());
        vPermissions.setCPCompanySetupFY(cbCPanelCompnaySetupFY.isChecked());
        vPermissions.setCPCompanySetupCA(cbCPanelCompnaySetupCA.isChecked());
        vPermissions.setCPCompanySetupHSN(cbCPanelCompnaySetupHsn.isChecked());
        vPermissions.setControlPanelUAS(cbCPanelUAS.isChecked());
        vPermissions.setControlPanelUASuas(cbCPanelUASuas.isChecked());
        vPermissions.setControlPanelUASat(cbCPanelUASat.isChecked());
        vPermissions.setControlPanelAccountMaintenance(cbCPanelActMaintenance.isChecked());
        vPermissions.setControlPanelAMag(cbCPanellAMag.isChecked());
        vPermissions.setControlPanelAMcoa(cbCPanellAMcoa.isChecked());
        vPermissions.setControlPanelAMbudget(cbCPanellAMbudget.isChecked());
        vPermissions.setControlPanelDisplaySetup(cbCPanelDisplaySetup.isChecked());
        vPermissions.setControlPanelDSfs(cbCPanellDSfs.isChecked());
        vPermissions.setControlPanelDSlayout(cbCPanelDSlayout.isChecked());
        vPermissions.setControlPanelDStermsCond(cblDStermsCond.isChecked());


        vPermissions.setReport(cbReports.isChecked());
        vPermissions.setReportPurchase(cbReportsPurchase.isChecked());
        vPermissions.setReportPurchaseSSR(cbReportsPurchaseSSR.isChecked());
        vPermissions.setReportPurchasePOL(cbReportsPurchasePOL.isChecked());
        //cbReportsContacts.setChecked(userPermissions.isContact());
        vPermissions.setReportPurchasePRL(cbReportsPurchasePRL.isChecked());
        vPermissions.setReportPurchaseSP(cbReportsPurchaseSP.isChecked());
        vPermissions.setReportPurchasePA(cbReportsPurchasePA.isChecked());
        vPermissions.setReportSale(cbReportsSale.isChecked());
        vPermissions.setReportSaleSOL(cbReportsSaleSOL.isChecked());
        vPermissions.setReportSaleICL(cbReportsSaleICL.isChecked());
        vPermissions.setReportSaleSIL(cbReportsSaleSIL.isChecked());
        vPermissions.setReportSaleSRL(cbReportsSaleSRL.isChecked());
        vPermissions.setReportSalePR(cbReportsSalePR.isChecked());
        vPermissions.setReportSalePL(cbReportsSalePL.isChecked());
        vPermissions.setReportSaleCA(cbReportsSaleCA.isChecked());
        vPermissions.setReportSaleCP(cbReportsSaleCP.isChecked());
        vPermissions.setReportSaleSA(cbReportsSaleSA.isChecked());
        vPermissions.setReportInventory(cbReportsInventory.isChecked());
        vPermissions.setReportInventory(cbReportsInventryIVR.isChecked());
        vPermissions.setReportInventory(cbReportsInventryISR.isChecked());
        vPermissions.setReportInventory(cbReportsInventryISCR.isChecked());
        vPermissions.setReportAnalysis(cbReportsAnalysis.isChecked());
        vPermissions.setReportAnalysisTopVendor(cbReportsVendor.isChecked());
        vPermissions.setReportAnalysisTopItem(cbReportsItem.isChecked());
        vPermissions.setReportAnalysisTopCustomer(cbReportsCustomer.isChecked());
        vPermissions.setReportFinancialStatement(ccbReportsStatment.isChecked());
        vPermissions.setReportFStatementPL(cbReportsStatementPL.isChecked());
        vPermissions.setReportFStatementBS(cbReportsStatementBS.isChecked());
        vPermissions.setReportFStatementTB(cbReportsStatementTB.isChecked());
        vPermissions.setReportFStatementGL(cbReportsStatementGL.isChecked());
        vPermissions.setReportFStatementVL(cbReportsStatementVL.isChecked());
        vPermissions.setReportFStatementGJ(cbReportsStatementGJ.isChecked());
        vPermissions.setReportBankRecon(cbReportsBankRecon.isChecked());
        vPermissions.setReportBankReconRatio(cbReportsBankReconRatio.isChecked());


        vPermissions.setDashboard(cbDashBoard.isChecked());
        vPermissions.setDashboardDateTime(cbDashBoardDateTime.isChecked());
        vPermissions.setDashboardCashInHand(cbDashBoardCashInHand.isChecked());
        vPermissions.setDashboardCashInBank(cbDashBoardCashInBank.isChecked());
        vPermissions.setDashboardTotalReceivable(cbDashBoardTotalRecievable.isChecked());
        vPermissions.setDashboardTotalPayable(cbDashBoardTotalPayable.isChecked());

        vPermissions.setSales(cbSales.isChecked());
        vPermissions.setSalesItemAdd(cbSalesItems.isChecked());
        vPermissions.setSalesCustomerAdd(cbSalesCustomer.isChecked());
        vPermissions.setSalesSavesDraft(cbSalesSaveDraft.isChecked());
        vPermissions.setSalesClearDraft(cbSalesClearDraft.isChecked());
        vPermissions.setSalesPrintList(cbSalesPrintList.isChecked());
        vPermissions.setSalesAdvancePayment(cbSalesAdvacnePayment.isChecked());
        vPermissions.setSalesSaveSalesOrder(cbSalesSaveSalesOrder.isChecked());
        vPermissions.setSalesAdvancePaymentList(cbSalesAdvancePrintList.isChecked());
        vPermissions.setSalesInvokeSalesOrder(cbSalesInvokeSalesOrder.isChecked());
        vPermissions.setSalesClearAll(cbSalesClearAll.isChecked());
        vPermissions.setSalesCheckOut(cbSalesCheckout.isChecked());
        vPermissions.setSalesReturn(cbSalesReturn.isChecked());
        vPermissions.setSalesRecievePayment(cbSalesRecievePayment.isChecked());
        vPermissions.setSalesInvokeDraft(cbSalesInvokeDraft.isChecked());


        vPermissions.setPurchase(cbPurchase.isChecked());
        vPermissions.setPurchaseItemAdd(cbPurchaseItems.isChecked());
        vPermissions.setPurchaseSupplierAdd(cbPurchaseSupplier.isChecked());
        vPermissions.setPurchasePrintList(cbPurchasePrintList.isChecked());
        vPermissions.setPurchaseAdvancePayment(cbPurchaseAdvancePayment.isChecked());
        vPermissions.setPurchaseSavePurchaseOrder(cbPurchaseSavePurchaseOrder.isChecked());
        vPermissions.setPurchaseAdvancePaymentList(cbPruchaseAdvPaymentList.isChecked());
        vPermissions.setPurchaseInvokePurchaseOrder(cbPurchaseInvokePo.isChecked());
        vPermissions.setPurchaseClearAll(cbPurchaseClearAll.isChecked());
        vPermissions.setPurchaseCheckOut(cbPurchaseCheckOut.isChecked());
        vPermissions.setPurchaseReturn(cbPurchasaeReturn.isChecked());
        vPermissions.setPurchaseSupplierPayment(cbPurchaseSupplierPayment.isChecked());
        vPermissions.setPurchaseSelfBilled(cbPurchaseSelfBilled.isChecked());

        vPermissions.setGt(cbGeneralTransaction.isChecked());
        vPermissions.setGtExpense(cbGTExpense.isChecked());
        vPermissions.setGtReciepts(cbGTReciepts.isChecked());
        vPermissions.setGtJournalEntry(cbGTJournalEntry.isChecked());

        vPermissions.setId(vcontrolPermissiondetails.getId());

        String url = serverUrl + "/version//1/" + Constant.FUNTION_SAVEVERSIONPERMISSION;
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
                                vPermissions = gson.fromJson(jsonObject.toString(), VersionControlPermissions.class);
                                if (vPermissions != null) {
                                    UtilView.showToast(activity, "Permission update Successfully");
                                } else {
                                    UtilView.showToast(activity, "Permission didn't update Successfully");
                                }


                            } catch (Exception e) {
                                UtilView.showLogCat(TAG, "TaskCompleteListener Exception " + e.toString());
                            }
                        }

                        Intent returnIntent = new Intent();
                        activity.setResult(Activity.RESULT_OK, returnIntent);
                        activity.finish();
                    } else {
                        UtilView.showErrorDialog(getResources().getString(R.string.error_null), activity);
                    }
                }
            }, false);
            task.execute(new Gson().toJson(vPermissions).toString(), url, "");
        } else {
            UtilView.gotToLogin(activity);
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

    private void setAllReportChecked(boolean isChecked) {
        cbReportsPurchase.setChecked(isChecked);
        cbReportsPurchaseSSR.setChecked(isChecked);
        cbReportsPurchasePOL.setChecked(isChecked);
        cbReportsContacts.setChecked(isChecked);
        cbReportsPurchasePRL.setChecked(isChecked);
        cbReportsPurchaseSP.setChecked(isChecked);
        cbReportsPurchasePA.setChecked(isChecked);
        cbReportsSale.setChecked(isChecked);
        cbReportsSaleSOL.setChecked(isChecked);
        cbReportsSaleICL.setChecked(isChecked);
        cbReportsSaleSIL.setChecked(isChecked);
        cbReportsSaleSRL.setChecked(isChecked);
        cbReportsSalePR.setChecked(isChecked);
        cbReportsSalePL.setChecked(isChecked);
        cbReportsSaleCA.setChecked(isChecked);
        cbReportsSaleCP.setChecked(isChecked);
        cbReportsSaleSA.setChecked(isChecked);
        cbReportsInventory.setChecked(isChecked);
        cbReportsInventryIVR.setChecked(isChecked);
        cbReportsInventryISR.setChecked(isChecked);
        cbReportsInventryISCR.setChecked(isChecked);
        cbReportsAnalysis.setChecked(isChecked);
        cbReportsVendor.setChecked(isChecked);
        cbReportsItem.setChecked(isChecked);
        cbReportsCustomer.setChecked(isChecked);
        ccbReportsStatment.setChecked(isChecked);
        cbReportsStatementPL.setChecked(isChecked);
        cbReportsStatementBS.setChecked(isChecked);
        cbReportsStatementTB.setChecked(isChecked);
        cbReportsStatementGL.setChecked(isChecked);
        cbReportsStatementVL.setChecked(isChecked);
        cbReportsStatementGJ.setChecked(isChecked);
        cbReportsBankRecon.setChecked(isChecked);
        cbReportsBankReconRatio.setChecked(isChecked);


        if (isChecked) {
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

    private void setAllHelpChecked(boolean isChecked) {
        cbHelpItem.setChecked(isChecked);
        cbHelpCustomer.setChecked(isChecked);
        cbHelpSalesOrder.setChecked(isChecked);
        cbHelpAdvPaymnet.setChecked(isChecked);
        cbHelpInvokeDraft.setChecked(isChecked);
        cbHelpRecievePayment.setChecked(isChecked);
        if (isChecked) {
            llHelp1.setVisibility(View.VISIBLE);
            llHelp2.setVisibility(View.VISIBLE);

        }
        if (!isChecked) {
            llHelp1.setVisibility(View.GONE);
            llHelp2.setVisibility(View.GONE);
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
        }

    }

    private void setAllTaxChecked(boolean isChecked) {
        cbTaxConfig.setChecked(isChecked);
        cbTaxGST.setChecked(isChecked);
        if (isChecked) {
            llTax1.setVisibility(View.VISIBLE);
        }
        if (!isChecked) {
            llTax1.setVisibility(View.GONE);

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
        if (isChecked) {
            llInventory1.setVisibility(View.VISIBLE);
            llInventory2.setVisibility(View.VISIBLE);
            llInventory3.setVisibility(View.VISIBLE);
        }
        if (!isChecked) {
            llInventory1.setVisibility(View.GONE);
            llInventory2.setVisibility(View.GONE);
            llInventory3.setVisibility(View.GONE);
        }


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
           /* case R.id.cbHelp:
                setAllHelpChecked(((CheckBox) view).isChecked());
                break;*/
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


}
