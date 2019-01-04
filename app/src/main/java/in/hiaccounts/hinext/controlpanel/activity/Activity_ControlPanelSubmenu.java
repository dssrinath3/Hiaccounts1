package in.hiaccounts.hinext.controlpanel.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.hiaccounts.R;
import in.hiaccounts.hinext.controlpanel.fragment.accountmaintenance.Fragment_AccountGroup;
import in.hiaccounts.hinext.controlpanel.fragment.accountmaintenance.Fragment_Budget;
import in.hiaccounts.hinext.controlpanel.fragment.accountmaintenance.Fragment_CreateChart;
import in.hiaccounts.hinext.controlpanel.fragment.companysetup.Fragment_ChartsOfAccount;
import in.hiaccounts.hinext.controlpanel.fragment.companysetup.Fragment_CompanyInformation;
import in.hiaccounts.hinext.controlpanel.fragment.companysetup.Fragment_FinancialYear;
import in.hiaccounts.hinext.controlpanel.fragment.configurator.Fragment_Cart;
import in.hiaccounts.hinext.controlpanel.fragment.configurator.Fragment_ConfigAgent;
import in.hiaccounts.hinext.controlpanel.fragment.configurator.Fragment_ConfigBank;
import in.hiaccounts.hinext.controlpanel.fragment.configurator.Fragment_ConfigCountry;
import in.hiaccounts.hinext.controlpanel.fragment.configurator.Fragment_ConfigCurrency;
import in.hiaccounts.hinext.controlpanel.fragment.configurator.Fragment_ConfigEmailServer;
import in.hiaccounts.hinext.controlpanel.fragment.configurator.Fragment_ConfigEmployee;
import in.hiaccounts.hinext.controlpanel.fragment.configurator.Fragment_ConfigExRate;
import in.hiaccounts.hinext.controlpanel.fragment.configurator.Fragment_ConfigPaymentMethod;
import in.hiaccounts.hinext.controlpanel.fragment.configurator.Fragment_ConfigProject;
import in.hiaccounts.hinext.controlpanel.fragment.configurator.Fragment_ConfigShippingMethod;
import in.hiaccounts.hinext.controlpanel.fragment.configurator.Fragment_Configuration;
import in.hiaccounts.hinext.controlpanel.fragment.configurator.Fragment_ServiceCharge;
import in.hiaccounts.hinext.controlpanel.fragment.configurator.Fragment_State;
import in.hiaccounts.hinext.controlpanel.fragment.configurator.Fragment_VersionControl;
import in.hiaccounts.hinext.controlpanel.fragment.displaysetup.Fragment_TableChairSetup;
import in.hiaccounts.hinext.controlpanel.fragment.displaysetup.Fragment_TableConfiguarationSetup;
import in.hiaccounts.hinext.controlpanel.fragment.displaysetup.Fragment_TableSetup;
import in.hiaccounts.hinext.controlpanel.fragment.openingbalance.Fragment_BalanceSheetOpening;
import in.hiaccounts.hinext.controlpanel.fragment.openingbalance.Fragment_InventoryOpening;
import in.hiaccounts.hinext.controlpanel.fragment.openingbalance.Fragment_LastYearOpening;
import in.hiaccounts.hinext.controlpanel.fragment.openingbalance.Fragment_MonthEndOpening;
import in.hiaccounts.hinext.controlpanel.fragment.openingbalance.Fragment_PurchaseInvoiceOpening;
import in.hiaccounts.hinext.controlpanel.fragment.openingbalance.Fragment_SalesInvoiceOpening;
import in.hiaccounts.hinext.controlpanel.fragment.openingbalance.Fragment_YearEndOpening;
import in.hiaccounts.hinext.controlpanel.fragment.useraccountsetup.Fragment_UserActAuditTrial;
import in.hiaccounts.hinext.controlpanel.fragment.useraccountsetup.Fragment_UserActSetup;
import in.hiaccounts.hinext.inventory.fragment.Fragment_HsnCodes;

/**
 * Created by Prateek on 2/9/2017.
 */

public class Activity_ControlPanelSubmenu extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fragment_container_id)
    FrameLayout fragmentContainerId;
    String callingFrom="";
    /**
     * Called when the activity is first created.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory_activity_inventorysubmenu);
        ButterKnife.bind(this);


        initComponentView();
    }

    private void initComponentView() {

        Intent intent = getIntent();
        if (intent != null) {

            callingFrom=intent.getStringExtra("callingFrom");
            if (callingFrom!=null) {
                if (callingFrom.equals("Restra")) {
                    this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    toolbar.setTitle(getResources().getString(R.string.cPanelConfigEmployee));
                    toolbar.setTitleTextColor(Color.WHITE);
                    toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
                    setSupportActionBar(toolbar);
                    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                        }
                    });

                    showConfigEmployeeFragment();
                }
            }


            String menuTitle = intent.getStringExtra("menu");

            if (menuTitle != null) {
                this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


                toolbar.setTitle(menuTitle);
                toolbar.setTitleTextColor(Color.WHITE);
                toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
                setSupportActionBar(toolbar);
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });

                if (menuTitle.equals(getResources().getString(R.string.cPanelactmainActGrp))) {
                    showactmainActGrpFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.cPanelactmainCreateChart))) {
                    showactmainCrateChartFragment();
                }
              /*  if (menuTitle.equals(getResources().getString(R.string.cPanelactmainBudget))) {
                    showactmainBudgetFragment();
                }*/

                if (menuTitle.equals(getResources().getString(R.string.cPanelcsetupCInfo))) {
                    showacSetupCInfoFragment();
                }

                if (menuTitle.equals(getResources().getString(R.string.cPanelcsetupFYear))) {
                    showacSetupFYearFragment();
                }

                if (menuTitle.equals(getResources().getString(R.string.cPanelcsetupCharts))) {
                    showacSetupChartsFragment();
                }

                if (menuTitle.equals(getResources().getString(R.string.cPanelcsetupHSNCode))) {
                    showacSetupAlertsFragment();
                }

                if (menuTitle.equals(getResources().getString(R.string.cPanelOpBalBalanceSheets))) {
                    showBalanceSheetsFragment();
                }

                if (menuTitle.equals(getResources().getString(R.string.cPanelOpBalPurchaseInvoices))) {
                    showBalanceSheetsPurchaseInvoiceFragment();
                }

                if (menuTitle.equals(getResources().getString(R.string.cPanelOpBalSalesInvocies))) {
                    showBalanceSheetsSalesInvoiceFragment();
                }

                if (menuTitle.equals(getResources().getString(R.string.cPanelOpBalInventory))) {
                    showBalanceSheetsInventoryFragment();
                }

                if (menuTitle.equals(getResources().getString(R.string.cPanelOpBalLastyrfigure))) {
                    showBalanceLastYearFragment();
                }

                if (menuTitle.equals(getResources().getString(R.string.cPanelOpBalmonthend))) {
                    showBalanceMonthEndFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.cPanelOpBalyrend))) {
                    showBalanceYearEndFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.cPaneluseractSetup))) {
                    showUserActSetupFragment();
                }
            /*    if (menuTitle.equals(getResources().getString(R.string.cPaneluseractAuditTrial))) {
                    showUserActAuditTrialFragment();
                }*/
                if (menuTitle.equals(getResources().getString(R.string.cPaneldisFormSetup))) {
                    showFormSetupFragment();

                }
                if (menuTitle.equals(getResources().getString(R.string.cPaneldisTable))) {
                    showTableFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.cPaneldisTableConfiguration))) {
                    showTableConfigFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.cPaneldisChair))) {
                    showTableChairFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.cPaneldisTermscondition))) {
                    showTermsConditionFragment();
                }

                if (menuTitle.equals(getResources().getString(R.string.cPanelConfigBank))) {
                    showConfigBankFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.cPanelConfigAgent))) {
                    showConfigAgentFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.cPanelConfigCurrency))) {
                    showConfigCurrencyFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.cPanelConfigEmployee))) {
                    showConfigEmployeeFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.cPanelConfigCountry))) {
                    showConfigCountryFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.cPanelConfigExRate))) {
                    showConfigExRateFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.cPanelConfigProject))) {
                    showConfigProjectFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.cPanelConfigShiipingMethod))) {
                    showConfigShippingmethodFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.cPanelConfigEmailServer))) {
                    showConfigEmailServerFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.cPanelConfigPaymentMethod))) {
                    showConfigPaymentmethodFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.cPanelConfiguration))) {
                    showConfigurationFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.cPanelVersionControl))) {
                    showVersionControlFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.cPanelServiceCharge))) {
                    showServiceChargeFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.cPanelCart))) {
                    showConfigCartFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.cPanelState))) {
                    showConfigStateFragment();
                }

            }
        }
    }




    // function for call fragment view.
    private void showScreen(Fragment content, String contentTag, boolean addToBackStack, boolean clearBackStack) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(R.anim.left_slide_in, R.anim.left_slide_out, R.anim.right_slide_in, R.anim.right_slide_out);

        if (callingFrom !=null && callingFrom.equals("Restra")){
            Bundle bundle=new Bundle();
            bundle.putString("Restra", callingFrom);
            content.setArguments(bundle);
            ft.replace(R.id.fragment_container_id, content, contentTag);

        }else{
            ft.replace(R.id.fragment_container_id, content, contentTag);

        }

        //ft.replace(R.id.fragment_container_id, content, contentTag);

        if (clearBackStack) {

            fm.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }

        if (addToBackStack) {

            ft.addToBackStack(String.valueOf(System.identityHashCode(content)));
        }

        ft.commitAllowingStateLoss();
        fm.executePendingTransactions();
    }

    private void showTableChairFragment() {
        showScreen(Fragment_TableChairSetup.newInstance(), Fragment_TableChairSetup.TAG, false, true);
    }

    public void showConfigStateFragment() {

        showScreen(Fragment_State.newInstance(), Fragment_State.TAG, false, true);

    }

    public void showConfigCartFragment() {
        showScreen(Fragment_Cart.newInstance(), Fragment_Cart.TAG, false, true);

    }
    public void showactmainActGrpFragment() {
        showScreen(Fragment_AccountGroup.newInstance(), Fragment_AccountGroup.TAG, false, true);
    }

    public void showactmainCrateChartFragment() {
        showScreen(Fragment_CreateChart.newInstance(), Fragment_CreateChart.TAG, false, true);
    }

   /* public void showactmainBudgetFragment() {
        showScreen(Fragment_Budget.newInstance(), Fragment_Budget.TAG, false, true);
    }*/

    public void showacSetupCInfoFragment() {
        showScreen(Fragment_CompanyInformation.newInstance(), Fragment_CompanyInformation.TAG, false, true);
    }

    public void showacSetupFYearFragment() {
        showScreen(Fragment_FinancialYear.newInstance(), Fragment_FinancialYear.TAG, false, true);
    }

    public void showacSetupChartsFragment() {
        showScreen(Fragment_ChartsOfAccount.newInstance(), Fragment_ChartsOfAccount.TAG, false, true);
    }

    public void showacSetupAlertsFragment() {
        showScreen(Fragment_HsnCodes.newInstance(), Fragment_HsnCodes.TAG, false, true);
    }

    public void showBalanceSheetsFragment() {
        showScreen(Fragment_BalanceSheetOpening.newInstance(), Fragment_BalanceSheetOpening.TAG, false, true);
    }

    public void showBalanceSheetsPurchaseInvoiceFragment() {
        showScreen(Fragment_PurchaseInvoiceOpening.newInstance(), Fragment_PurchaseInvoiceOpening.TAG, false, true);
    }

    public void showBalanceSheetsSalesInvoiceFragment() {
        showScreen(Fragment_SalesInvoiceOpening.newInstance(), Fragment_SalesInvoiceOpening.TAG, false, true);
    }

    public void showBalanceSheetsInventoryFragment() {
        showScreen(Fragment_InventoryOpening.newInstance(), Fragment_InventoryOpening.TAG, false, true);
    }

    public void showBalanceLastYearFragment() {
        showScreen(Fragment_LastYearOpening.newInstance(), Fragment_LastYearOpening.TAG, false, true);
    }

    public void showBalanceMonthEndFragment() {
        showScreen(Fragment_MonthEndOpening.newInstance(), Fragment_MonthEndOpening.TAG, false, true);
    }

    public void showBalanceYearEndFragment() {
        showScreen(Fragment_YearEndOpening.newInstance(), Fragment_YearEndOpening.TAG, false, true);
    }

    public void showUserActSetupFragment() {
        showScreen(Fragment_UserActSetup.newInstance(), Fragment_UserActSetup.TAG, false, true);
    }

    public void showUserActAuditTrialFragment() {
        showScreen(Fragment_UserActAuditTrial.newInstance(), Fragment_UserActAuditTrial.TAG, false, true);
    }

    public void showFormSetupFragment() {
        showScreen(Fragment_UserActAuditTrial.newInstance(), Fragment_UserActAuditTrial.TAG, false, true);
    }

    public void showTableFragment() {
        showScreen(Fragment_TableSetup.newInstance(), Fragment_TableSetup.TAG, false, true);
    }

    public void showTableConfigFragment() {
        showScreen(Fragment_TableConfiguarationSetup.newInstance(), Fragment_TableConfiguarationSetup.TAG, false, true);
    }

    public void showTermsConditionFragment() {
       // showScreen(Fragment_UserActAuditTrial.newInstance(), Fragment_UserActAuditTrial.TAG, false, true);
    }


    public void showConfigBankFragment() {
        showScreen(Fragment_ConfigBank.newInstance(), Fragment_ConfigBank.TAG, false, true);
    }

    public void showConfigAgentFragment() {
        showScreen(Fragment_ConfigAgent.newInstance(), Fragment_ConfigAgent.TAG, false, true);
    }


    public void showConfigCurrencyFragment() {
        showScreen(Fragment_ConfigCurrency.newInstance(), Fragment_ConfigCurrency.TAG, false, true);
    }


    public void showConfigEmployeeFragment() {
        showScreen(Fragment_ConfigEmployee.newInstance(), Fragment_ConfigEmployee.TAG, false, true);
    }


    public void showConfigCountryFragment() {
        showScreen(Fragment_ConfigCountry.newInstance(), Fragment_ConfigCountry.TAG, false, true);
    }


    public void showConfigExRateFragment() {
        showScreen(Fragment_ConfigExRate.newInstance(), Fragment_ConfigExRate.TAG, false, true);
    }


    public void showConfigProjectFragment() {
        showScreen(Fragment_ConfigProject.newInstance(), Fragment_ConfigProject.TAG, false, true);
    }


    public void showConfigShippingmethodFragment() {
        showScreen(Fragment_ConfigShippingMethod.newInstance(), Fragment_ConfigShippingMethod.TAG, false, true);
    }


    public void showConfigEmailServerFragment() {
        showScreen(Fragment_ConfigEmailServer.newInstance(), Fragment_ConfigEmailServer.TAG, false, true);
    }


    public void showConfigPaymentmethodFragment() {
        showScreen(Fragment_ConfigPaymentMethod.newInstance(), Fragment_ConfigPaymentMethod.TAG, false, true);
    }


    public void showConfigurationFragment() {
        showScreen(Fragment_Configuration.newInstance(), Fragment_Configuration.TAG, false, true);
    }
    public void showVersionControlFragment() {
        showScreen(Fragment_VersionControl.newInstance(), Fragment_VersionControl.TAG, false, true);
    }
    public void showServiceChargeFragment() {
        showScreen(Fragment_ServiceCharge.newInstance(), Fragment_ServiceCharge.TAG, false, true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();

        for (int i = 0; i < fragmentList.size(); i++) {

            Fragment fragment = fragmentList.get(i);

            if (fragment instanceof Fragment_ConfigBank) {
                fragment.onActivityResult(requestCode, resultCode, data);
            }

        }
    }
}
