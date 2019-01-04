package in.hiaccounts.hinext.controlpanel.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import in.hiaccounts.hinext.controlpanel.model.useraccount_setup.UserAccountData;
import in.hiaccounts.hinext.controlpanel.model.useraccount_setup.UserPermissionData;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 8/21/2017.
 */

public class Activity_Config_UserPermission extends AppCompatActivity {

   // public static final String TAG = Activity_Config_UserPermission.class.getSimpleName();
    UserPermissionData userPermissions;
    UserAccountData userDetail;


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.btnClose)
    Button btnClose;
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
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
    @BindView(R.id.cbReportsAnalysis)
    CheckBox cbReportsAnalysis;
    @BindView(R.id.cbReportsVendor)
    CheckBox cbReportsVendor;
    @BindView(R.id.cbReportsItem)
    CheckBox cbReportsItem;
    @BindView(R.id.llReport7)
    LinearLayout llReport7;
    @BindView(R.id.cbReportsCustomer)
    CheckBox cbReportsCustomer;
    @BindView(R.id.ccbReportsStatment)
    CheckBox ccbReportsStatment;
    @BindView(R.id.cbReportsStatementPL)
    CheckBox cbReportsStatementPL;
    @BindView(R.id.llReport8)
    LinearLayout llReport8;
    @BindView(R.id.cbReportsStatementBS)
    CheckBox cbReportsStatementBS;
    @BindView(R.id.cbReportsStatementTB)
    CheckBox cbReportsStatementTB;
    @BindView(R.id.cbReportsStatementGL)
    CheckBox cbReportsStatementGL;
    @BindView(R.id.llReport9)
    LinearLayout llReport9;
    @BindView(R.id.cbReportsStatementVL)
    CheckBox cbReportsStatementVL;
    @BindView(R.id.cbReportsStatementGJ)
    CheckBox cbReportsStatementGJ;
    @BindView(R.id.cbReportsBankRecon)
    CheckBox cbReportsBankRecon;
    @BindView(R.id.llReport10)
    LinearLayout llReport10;
    @BindView(R.id.cbReportsBankReconRatio)
    CheckBox cbReportsBankReconRatio;
    @BindView(R.id.llReport11)
    LinearLayout llReport11;
    @BindView(R.id.llContent)
    LinearLayout llContent;
    @BindView(R.id.ll1)
    LinearLayout ll1;
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
    private Activity activity;
    private ServiceHandler serviceHandler;
    private boolean isInternatePrsenet = false;
    String callingFor, serverUrl;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uaccount_userpermission);
        ButterKnife.bind(this);
        initComponentView();
    }

    private void initComponentView() {

        activity = this;
        serviceHandler = new ServiceHandler(activity);
        serverUrl = UtilView.getUrl(activity);
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
                                    userPermissions = gson.fromJson(jsonObject.toString(), UserPermissionData.class);
                                    if (userPermissions != null) {
                                        setUpView(userPermissions);
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

    private void setUpView(UserPermissionData userPermissions) {

        cbContact.setChecked(userPermissions.isContact());
        cbContactContacts.setChecked(userPermissions.isContactContacts());
        cbContactSuppllier.setChecked(userPermissions.isContactSupplier());
        cbContactCustomer.setChecked(userPermissions.isContactCustomer());

        cbInventory.setChecked(userPermissions.isInventory());
        cbInventoryBrand.setChecked(userPermissions.isInventoryBrand());
        cbInventoryCategory.setChecked(userPermissions.isInventoryCategory());
        cbInventoryUOM.setChecked(userPermissions.isInventoryUOM());
        cbInventoryItem.setChecked(userPermissions.isInventoryItem());
        cbInventorySalesPricing.setChecked(userPermissions.isInventorySalesPricing());
        cbInventoryPurchasePricing.setChecked(userPermissions.isInventoryPurchasePricing());
        cbInventoryLocation.setChecked(userPermissions.isInventoryInventoryLocation());
        cbInventoryMovementType.setChecked(userPermissions.isInventoryInventoryMovementType());

        cbTax.setChecked(userPermissions.isTax());
        cbTaxConfig.setChecked(userPermissions.isTaxTaxConfiguration());
        cbTaxGST.setChecked(userPermissions.isTaxGST());


        cbCPanel.setChecked(userPermissions.isControlPanel());
        cbCPanellOBofBS.setChecked(userPermissions.isControlPanelOBofBS());
        cbCPanellOBofPI.setChecked(userPermissions.isControlPanelOBofPI());
        cbCPanellOBofSI.setChecked(userPermissions.isControlPanelOBofSI());
        cbCPanellOBofINV.setChecked(userPermissions.isControlPanelOBofINV());
        cbCPanelLastYrFig.setChecked(userPermissions.isControlPanelLastYearFigure());
        cbCPanelMonthEnd.setChecked(userPermissions.isControlPanelMonthEnd());
        cbCPanelYearEnd.setChecked(userPermissions.isControlPanelYearEnd());
        cbCPanelConfigurator.setChecked(userPermissions.isControlPanelConfigurator());
        cbCPanelConfigBank.setChecked(userPermissions.iscPConfiguratorBank());
        cbCPanelConfigAgent.setChecked(userPermissions.iscPConfiguratorAgent());
        cbCPanelConfigEmployee.setChecked(userPermissions.iscPConfiguratorEmployee());
        cbCPanelConfigCurrency.setChecked(userPermissions.iscPConfiguratorCurrency());
        cbCPanelConfigExRate.setChecked(userPermissions.iscPConfiguratorCurrency());
        cbCPanelConfigProject.setChecked(userPermissions.iscPConfiguratorProjectTitle());
        cbCPanelShipping.setChecked(userPermissions.iscPConfiguratorShippingMethod());
        cbCPanelConfigEmail.setChecked(userPermissions.iscPConfiguratorEmailserver());
        cbCPanelConfigPayment.setChecked(userPermissions.iscPConfiguratorPaymentMethod());
        cbCPanelConfigConfig.setChecked(userPermissions.iscPConfiguratorConfiguration());
        cbCPanelCompnaySetup.setChecked(userPermissions.isControlPanelCompanySetup());
        cbCPanelCompnaySetupCI.setChecked(userPermissions.iscPCompanySetupCI());
        cbCPanelCompnaySetupFY.setChecked(userPermissions.iscPCompanySetupFY());
        cbCPanelCompnaySetupCA.setChecked(userPermissions.iscPCompanySetupCA());
        cbCPanelCompnaySetupHsn.setChecked(userPermissions.iscPCompanySetupHSN());
        cbCPanelUAS.setChecked(userPermissions.isControlPanelUAS());
        cbCPanelUASuas.setChecked(userPermissions.isControlPanelUASuas());
        cbCPanelUASat.setChecked(userPermissions.isControlPanelUASat());
        cbCPanelActMaintenance.setChecked(userPermissions.isControlPanelAccountMaintenance());
        cbCPanellAMag.setChecked(userPermissions.isControlPanelAMag());
        cbCPanellAMcoa.setChecked(userPermissions.isControlPanelAMcoa());
        cbCPanellAMbudget.setChecked(userPermissions.isControlPanelAMbudget());
        cbCPanelDisplaySetup.setChecked(userPermissions.isControlPanelDisplaySetup());
        cbCPanellDSfs.setChecked(userPermissions.isControlPanelDSfs());
        cbCPanelDSlayout.setChecked(userPermissions.isControlPanelDSlayout());
        cblDStermsCond.setChecked(userPermissions.isControlPanelDStermsCond());



        cbReports.setChecked(userPermissions.isReport());
        cbReportsPurchase.setChecked(userPermissions.isReportPurchase());
        cbReportsPurchaseSSR.setChecked(userPermissions.isReportPurchaseSSR());
        cbReportsPurchasePOL.setChecked(userPermissions.isReportPurchasePOL());
        cbReportsContacts.setChecked(userPermissions.isContact());
        cbReportsPurchasePRL.setChecked(userPermissions.isReportPurchasePRL());
        cbReportsPurchaseSP.setChecked(userPermissions.isReportPurchaseSP());
        cbReportsPurchasePA.setChecked(userPermissions.isReportPurchasePA());
        cbReportsSale.setChecked(userPermissions.isReportSale());
        cbReportsSaleSOL.setChecked(userPermissions.isReportSaleSOL());
        cbReportsSaleICL.setChecked(userPermissions.isReportSaleICL());
        cbReportsSaleSIL.setChecked(userPermissions.isReportSaleSIL());
        cbReportsSaleSRL.setChecked(userPermissions.isReportSaleSRL());
        cbReportsSalePR.setChecked(userPermissions.isReportSalePR());
        cbReportsSalePL.setChecked(userPermissions.isReportSalePL());
        cbReportsSaleCA.setChecked(userPermissions.isReportSaleCA());
        cbReportsSaleCP.setChecked(userPermissions.isReportSaleCP());
        cbReportsSaleSA.setChecked(userPermissions.isReportSaleSA());
        cbReportsInventory.setChecked(userPermissions.isReportInventory());
        cbReportsAnalysis.setChecked(userPermissions.isReportAnalysis());
        cbReportsVendor.setChecked(userPermissions.isReportAnalysisTopVendor());
        cbReportsItem.setChecked(userPermissions.isReportAnalysisTopItem());
        cbReportsCustomer.setChecked(userPermissions.isReportAnalysisTopCustomer());
        ccbReportsStatment.setChecked(userPermissions.isReportFinancialStatement());
        cbReportsStatementPL.setChecked(userPermissions.isReportFStatementPL());
        cbReportsStatementBS.setChecked(userPermissions.isReportFStatementBS());
        cbReportsStatementTB.setChecked(userPermissions.isReportFStatementTB());
        cbReportsStatementGL.setChecked(userPermissions.isReportFStatementGL());
        cbReportsStatementVL.setChecked(userPermissions.isReportFStatementVL());
        cbReportsStatementGJ.setChecked(userPermissions.isReportFStatementGJ());
        cbReportsBankRecon.setChecked(userPermissions.isReportBankRecon());
        cbReportsBankReconRatio.setChecked(userPermissions.isReportBankReconRatio());


        cbDashBoard.setChecked(userPermissions.isDashboard());
        cbDashBoardDateTime.setChecked(userPermissions.isDashboardDateTime());
        cbDashBoardCashInHand.setChecked(userPermissions.isDashboardCashInHand());
        cbDashBoardCashInBank.setChecked(userPermissions.isDashboardCashInBank());
        cbDashBoardTotalRecievable.setChecked(userPermissions.isDashboardTotalReceivable());
        cbDashBoardTotalPayable.setChecked(userPermissions.isDashboardTotalPayable());

        cbSales.setChecked(userPermissions.isSales());
        cbSalesItems.setChecked(userPermissions.isSalesItemAdd());
        cbSalesCustomer.setChecked(userPermissions.isSalesCustomerAdd());
        cbSalesSaveDraft.setChecked(userPermissions.isSalesSavesDraft());
        cbSalesClearDraft.setChecked(userPermissions.isSalesClearDraft());
        cbSalesPrintList.setChecked(userPermissions.isSalesPrintList());
        cbSalesAdvacnePayment.setChecked(userPermissions.isSalesAdvancePayment());
        cbSalesSaveSalesOrder.setChecked(userPermissions.isSalesSaveSalesOrder());
        cbSalesAdvancePrintList.setChecked(userPermissions.isSalesAdvancePaymentList());
        cbSalesInvokeSalesOrder.setChecked(userPermissions.isSalesInvokeSalesOrder());
        cbSalesClearAll.setChecked(userPermissions.isSalesClearAll());
        cbSalesCheckout.setChecked(userPermissions.isSalesCheckOut());
        cbSalesReturn.setChecked(userPermissions.isSalesReturn());
        cbSalesRecievePayment.setChecked(userPermissions.isSalesRecievePayment());
        cbSalesInvokeDraft.setChecked(userPermissions.isSalesInvokeDraft());


        cbPurchase.setChecked(userPermissions.isPurchase());
        cbPurchaseItems.setChecked(userPermissions.isPurchaseItemAdd());
        cbPurchaseSupplier.setChecked(userPermissions.isPurchaseSupplierAdd());
        cbPurchasePrintList.setChecked(userPermissions.isPurchasePrintList());
        cbPurchaseAdvancePayment.setChecked(userPermissions.isPurchaseAdvancePayment());
        cbPurchaseSavePurchaseOrder.setChecked(userPermissions.isPurchaseSavePurchaseOrder());
        cbPruchaseAdvPaymentList.setChecked(userPermissions.isPurchaseAdvancePaymentList());
        cbPurchaseInvokePo.setChecked(userPermissions.isPurchaseInvokePurchaseOrder());
        cbPurchaseClearAll.setChecked(userPermissions.isPurchaseClearAll());
        cbPurchaseCheckOut.setChecked(userPermissions.isPurchaseCheckOut());
        cbPurchasaeReturn.setChecked(userPermissions.isPurchaseReturn());
        cbPurchaseSupplierPayment.setChecked(userPermissions.isPurchaseSupplierPayment());
        cbPurchaseSelfBilled.setChecked(userPermissions.isPurchaseSelfBilled());

        cbGeneralTransaction.setChecked(userPermissions.isGt());
        cbGTExpense.setChecked(userPermissions.isGtExpense());
        cbGTReciepts.setChecked(userPermissions.isGtReciepts());
        cbGTJournalEntry.setChecked(userPermissions.isGtJournalEntry());


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

        userPermissions.setContact(cbContact.isChecked());
        userPermissions.setContactContacts(cbContactContacts.isChecked());
        userPermissions.setContactSupplier(cbContactSuppllier.isChecked());
        userPermissions.setContactCustomer(cbContactCustomer.isChecked());

        userPermissions.setInventory(cbInventory.isChecked());
        userPermissions.setInventoryBrand(cbInventoryBrand.isChecked());
        userPermissions.setInventoryCategory(cbInventoryCategory.isChecked());
        userPermissions.setInventoryUOM(cbInventoryUOM.isChecked());
        userPermissions.setInventoryItem(cbInventoryItem.isChecked());
        userPermissions.setInventorySalesPricing(cbInventorySalesPricing.isChecked());
        userPermissions.setInventoryPurchasePricing(cbInventoryPurchasePricing.isChecked());
        userPermissions.setInventoryInventoryLocation(cbInventoryLocation.isChecked());
        userPermissions.setInventoryInventoryMovementType(cbInventoryMovementType.isChecked());

        userPermissions.setTax(cbTax.isChecked());
        userPermissions.setTaxTaxConfiguration(cbTaxConfig.isChecked());
        userPermissions.setTaxGST(cbTaxGST.isChecked());

        userPermissions.setControlPanel(cbCPanel.isChecked());
        userPermissions.setControlPanelOBofBS(cbCPanellOBofBS.isChecked());
        userPermissions.setControlPanelOBofPI(cbCPanellOBofPI.isChecked());
        userPermissions.setControlPanelOBofSI(cbCPanellOBofSI.isChecked());
        userPermissions.setControlPanelOBofINV(cbCPanellOBofINV.isChecked());
        userPermissions.setControlPanelLastYearFigure(cbCPanelLastYrFig.isChecked());
        userPermissions.setControlPanelMonthEnd(cbCPanelMonthEnd.isChecked());
        userPermissions.setControlPanelYearEnd(cbCPanelYearEnd.isChecked());
        userPermissions.setControlPanelConfigurator(cbCPanelConfigurator.isChecked());
        userPermissions.setcPConfiguratorBank(cbCPanelConfigBank.isChecked());
        userPermissions.setcPConfiguratorAgent(cbCPanelConfigAgent.isChecked());
        userPermissions.setcPConfiguratorEmployee(cbCPanelConfigEmployee.isChecked());
        userPermissions.setcPConfiguratorCurrency(cbCPanelConfigCurrency.isChecked());
        userPermissions.setcPConfiguratorCurrency(cbCPanelConfigExRate.isChecked());
        userPermissions.setcPConfiguratorProjectTitle(cbCPanelConfigProject.isChecked());
        userPermissions.setcPConfiguratorShippingMethod(cbCPanelShipping.isChecked());
        userPermissions.setcPConfiguratorEmailserver(cbCPanelConfigEmail.isChecked());
        userPermissions.setcPConfiguratorPaymentMethod(cbCPanelConfigPayment.isChecked());
        userPermissions.setcPConfiguratorConfiguration(cbCPanelConfigConfig.isChecked());
        userPermissions.setControlPanelCompanySetup(cbCPanelCompnaySetup.isChecked());
        userPermissions.setcPCompanySetupCI(cbCPanelCompnaySetupCI.isChecked());
        userPermissions.setcPCompanySetupFY(cbCPanelCompnaySetupFY.isChecked());
        userPermissions.setcPCompanySetupCA(cbCPanelCompnaySetupCA.isChecked());
        userPermissions.setcPCompanySetupHSN(cbCPanelCompnaySetupHsn.isChecked());
        userPermissions.setControlPanelUAS(cbCPanelUAS.isChecked());
        userPermissions.setControlPanelUASuas(cbCPanelUASuas.isChecked());
        userPermissions.setControlPanelUASat(cbCPanelUASat.isChecked());
        userPermissions.setControlPanelAccountMaintenance(cbCPanelActMaintenance.isChecked());
        userPermissions.setControlPanelAMag(cbCPanellAMag.isChecked());
        userPermissions.setControlPanelAMcoa(cbCPanellAMcoa.isChecked());
        userPermissions.setControlPanelAMbudget(cbCPanellAMbudget.isChecked());
        userPermissions.setControlPanelDisplaySetup(cbCPanelDisplaySetup.isChecked());
        userPermissions.setControlPanelDSfs(cbCPanellDSfs.isChecked());
        userPermissions.setControlPanelDSlayout(cbCPanelDSlayout.isChecked());
        userPermissions.setControlPanelDStermsCond(cblDStermsCond.isChecked());


        userPermissions.setReport(cbReports.isChecked());
        userPermissions.setReportPurchase(cbReportsPurchase.isChecked());
        userPermissions.setReportPurchaseSSR(cbReportsPurchaseSSR.isChecked());
        userPermissions.setReportPurchasePOL(cbReportsPurchasePOL.isChecked());
        //cbReportsContacts.setChecked(userPermissions.isContact());
        userPermissions.setReportPurchasePRL(cbReportsPurchasePRL.isChecked());
        userPermissions.setReportPurchaseSP(cbReportsPurchaseSP.isChecked());
        userPermissions.setReportPurchasePA(cbReportsPurchasePA.isChecked());
        userPermissions.setReportSale(cbReportsSale.isChecked());
        userPermissions.setReportSaleSOL(cbReportsSaleSOL.isChecked());
        userPermissions.setReportSaleICL(cbReportsSaleICL.isChecked());
        userPermissions.setReportSaleSIL(cbReportsSaleSIL.isChecked());
        userPermissions.setReportSaleSRL(cbReportsSaleSRL.isChecked());
        userPermissions.setReportSalePR(cbReportsSalePR.isChecked());
        userPermissions.setReportSalePL(cbReportsSalePL.isChecked());
        userPermissions.setReportSaleCA(cbReportsSaleCA.isChecked());
        userPermissions.setReportSaleCP(cbReportsSaleCP.isChecked());
        userPermissions.setReportSaleSA(cbReportsSaleSA.isChecked());
        userPermissions.setReportInventory(cbReportsInventory.isChecked());
        userPermissions.setReportAnalysis(cbReportsAnalysis.isChecked());
        userPermissions.setReportAnalysisTopVendor(cbReportsVendor.isChecked());
        userPermissions.setReportAnalysisTopItem(cbReportsItem.isChecked());
        userPermissions.setReportAnalysisTopCustomer(cbReportsCustomer.isChecked());
        userPermissions.setReportFinancialStatement(ccbReportsStatment.isChecked());
        userPermissions.setReportFStatementPL(cbReportsStatementPL.isChecked());
        userPermissions.setReportFStatementBS(cbReportsStatementBS.isChecked());
        userPermissions.setReportFStatementTB(cbReportsStatementTB.isChecked());
        userPermissions.setReportFStatementGL(cbReportsStatementGL.isChecked());
        userPermissions.setReportFStatementVL(cbReportsStatementVL.isChecked());
        userPermissions.setReportFStatementGJ(cbReportsStatementGJ.isChecked());
        userPermissions.setReportBankRecon(cbReportsBankRecon.isChecked());
        userPermissions.setReportBankReconRatio(cbReportsBankReconRatio.isChecked());


        userPermissions.setDashboard(cbDashBoard.isChecked());
        userPermissions.setDashboardDateTime(cbDashBoardDateTime.isChecked());
        userPermissions.setDashboardCashInHand(cbDashBoardCashInHand.isChecked());
        userPermissions.setDashboardCashInBank(cbDashBoardCashInBank.isChecked());
        userPermissions.setDashboardTotalReceivable(cbDashBoardTotalRecievable.isChecked());
        userPermissions.setDashboardTotalPayable(cbDashBoardTotalPayable.isChecked());

        userPermissions.setSales(cbSales.isChecked());
        userPermissions.setSalesItemAdd(cbSalesItems.isChecked());
        userPermissions.setSalesCustomerAdd(cbSalesCustomer.isChecked());
        userPermissions.setSalesSavesDraft(cbSalesSaveDraft.isChecked());
        userPermissions.setSalesClearDraft(cbSalesClearDraft.isChecked());
        userPermissions.setSalesPrintList(cbSalesPrintList.isChecked());
        userPermissions.setSalesAdvancePayment(cbSalesAdvacnePayment.isChecked());
        userPermissions.setSalesSaveSalesOrder(cbSalesSaveSalesOrder.isChecked());
        userPermissions.setSalesAdvancePaymentList(cbSalesAdvancePrintList.isChecked());
        userPermissions.setSalesInvokeSalesOrder(cbSalesInvokeSalesOrder.isChecked());
        userPermissions.setSalesClearAll(cbSalesClearAll.isChecked());
        userPermissions.setSalesCheckOut(cbSalesCheckout.isChecked());
        userPermissions.setSalesReturn(cbSalesReturn.isChecked());
        userPermissions.setSalesRecievePayment(cbSalesRecievePayment.isChecked());
        userPermissions.setSalesInvokeDraft(cbSalesInvokeDraft.isChecked());


        userPermissions.setPurchase(cbPurchase.isChecked());
        userPermissions.setPurchaseItemAdd(cbPurchaseItems.isChecked());
        userPermissions.setPurchaseSupplierAdd(cbPurchaseSupplier.isChecked());
        userPermissions.setPurchasePrintList(cbPurchasePrintList.isChecked());
        userPermissions.setPurchaseAdvancePayment(cbPurchaseAdvancePayment.isChecked());
        userPermissions.setPurchaseSavePurchaseOrder(cbPurchaseSavePurchaseOrder.isChecked());
        userPermissions.setPurchaseAdvancePaymentList(cbPruchaseAdvPaymentList.isChecked());
        userPermissions.setPurchaseInvokePurchaseOrder(cbPurchaseInvokePo.isChecked());
        userPermissions.setPurchaseClearAll(cbPurchaseClearAll.isChecked());
        userPermissions.setPurchaseCheckOut(cbPurchaseCheckOut.isChecked());
        userPermissions.setPurchaseReturn(cbPurchasaeReturn.isChecked());
        userPermissions.setPurchaseSupplierPayment(cbPurchaseSupplierPayment.isChecked());
        userPermissions.setPurchaseSelfBilled(cbPurchaseSelfBilled.isChecked());

        userPermissions.setGt(cbGeneralTransaction.isChecked());
        userPermissions.setGtExpense(cbGTExpense.isChecked());
        userPermissions.setGtReciepts(cbGTReciepts.isChecked());
        userPermissions.setGtJournalEntry(cbGTJournalEntry.isChecked());

        userPermissions.setUserAccountSetupID(userDetail.getUseraccountId());

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
                                userPermissions = gson.fromJson(jsonObject.toString(), UserPermissionData.class);
                                if (userPermissions != null) {
                                    UtilView.showToast(activity, "Permission update Successfully");
                                } else {
                                    UtilView.showToast(activity, "Permission didn't update Successfully");
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
            task.execute(new Gson().toJson(userPermissions).toString(), url, "");
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
