package in.hiaccounts.hinext.reports.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.hiaccounts.R;
import in.hiaccounts.hinext.reports.fragment.analysis.Fragment_Analysis_ActBalance;
import in.hiaccounts.hinext.reports.fragment.analysis.Fragment_Analysis_CustomerInvoices;
import in.hiaccounts.hinext.reports.fragment.analysis.Fragment_Analysis_CustomerReceipt;
import in.hiaccounts.hinext.reports.fragment.analysis.Fragment_Analysis_Expenses;
import in.hiaccounts.hinext.reports.fragment.analysis.Fragment_Analysis_Income;
import in.hiaccounts.hinext.reports.fragment.analysis.Fragment_Analysis_SupplierInvocies;
import in.hiaccounts.hinext.reports.fragment.analysis.Fragment_Analysis_SupplierPayment;
import in.hiaccounts.hinext.reports.fragment.analysis.Fragment_Analysis_TopCustomer;
import in.hiaccounts.hinext.reports.fragment.analysis.Fragment_Analysis_TopItem;
import in.hiaccounts.hinext.reports.fragment.analysis.Fragment_Analysis_TopVendors;
import in.hiaccounts.hinext.reports.fragment.bankreconciliation.Fragment_ReportBR_Banks;
import in.hiaccounts.hinext.reports.fragment.bankreconciliation.Fragment_ReportBR_Cheques;
import in.hiaccounts.hinext.reports.fragment.bankreconciliation.Fragment_ReportBR_Ratios;
import in.hiaccounts.hinext.reports.fragment.financialstatement.Fragment_ReportFS_FinancialPosition;
import in.hiaccounts.hinext.reports.fragment.financialstatement.Fragment_ReportFS_GeneralLedger;
import in.hiaccounts.hinext.reports.fragment.financialstatement.Fragment_ReportFS_Income;
import in.hiaccounts.hinext.reports.fragment.financialstatement.Fragment_ReportFS_Journal;
import in.hiaccounts.hinext.reports.fragment.financialstatement.Fragment_ReportFS_TrialBalance;
import in.hiaccounts.hinext.reports.fragment.financialstatement.Fragment_ReportFS_ViewLedger;
import in.hiaccounts.hinext.reports.fragment.inventory.Fragment_Inventory_AppleSales;
import in.hiaccounts.hinext.reports.fragment.inventory.Fragment_Inventory_AppleStock;
import in.hiaccounts.hinext.reports.fragment.inventory.Fragment_Inventory_GRNOutstanding;
import in.hiaccounts.hinext.reports.fragment.inventory.Fragment_Inventory_GRNValuation;
import in.hiaccounts.hinext.reports.fragment.inventory.Fragment_Inventory_InvList;
import in.hiaccounts.hinext.reports.fragment.inventory.Fragment_Inventory_Movement;
import in.hiaccounts.hinext.reports.fragment.inventory.Fragment_Inventory_NonMovement;
import in.hiaccounts.hinext.reports.fragment.inventory.Fragment_Inventory_Planning;
import in.hiaccounts.hinext.reports.fragment.inventory.Fragment_Inventory_Sales;
import in.hiaccounts.hinext.reports.fragment.inventory.Fragment_Inventory_StockCheck;
import in.hiaccounts.hinext.reports.fragment.inventory.Fragment_Inventory_Valuation;
import in.hiaccounts.hinext.reports.fragment.purchase.Fragment_RepPurchase_Adjustment;
import in.hiaccounts.hinext.reports.fragment.purchase.Fragment_RepPurchase_CacnelledInovices;
import in.hiaccounts.hinext.reports.fragment.purchase.Fragment_RepPurchase_Creditors;
import in.hiaccounts.hinext.reports.fragment.purchase.Fragment_RepPurchase_Inovices;
import in.hiaccounts.hinext.reports.fragment.purchase.Fragment_RepPurchase_PurchaseOrder;
import in.hiaccounts.hinext.reports.fragment.purchase.Fragment_RepPurchase_RecieveItem;
import in.hiaccounts.hinext.reports.fragment.purchase.Fragment_RepPurchase_Returns;
import in.hiaccounts.hinext.reports.fragment.purchase.Fragment_RepPurchase_SupplierPayment;
import in.hiaccounts.hinext.reports.fragment.purchase.Fragment_RepPurchase_SupplierSales;
import in.hiaccounts.hinext.reports.fragment.purchase.Fragment_RepPurchase_SupplierStatment;
import in.hiaccounts.hinext.reports.fragment.purchase.Fragment_RepPurchase_Suppliers;
import in.hiaccounts.hinext.reports.fragment.restaurent.Fragment_RestaurentCancelledItemReports;
import in.hiaccounts.hinext.reports.fragment.restaurent.Fragment_RestaurentDayEndRegister;
import in.hiaccounts.hinext.reports.fragment.restaurent.Fragment_RestaurentDayEndReport;
import in.hiaccounts.hinext.reports.fragment.restaurent.Fragment_RestaurentInvoiceRegister;
import in.hiaccounts.hinext.reports.fragment.restaurent.Fragment_RestaurentItemSalesReport;
import in.hiaccounts.hinext.reports.fragment.restaurent.Fragment_RestaurentMonthEndReport;
import in.hiaccounts.hinext.reports.fragment.sales.Fragment_Sales_Adjustment;
import in.hiaccounts.hinext.reports.fragment.sales.Fragment_Sales_Agent;
import in.hiaccounts.hinext.reports.fragment.sales.Fragment_Sales_CancelledInvoices;
import in.hiaccounts.hinext.reports.fragment.sales.Fragment_Sales_CommissionAging;
import in.hiaccounts.hinext.reports.fragment.sales.Fragment_Sales_Customer;
import in.hiaccounts.hinext.reports.fragment.sales.Fragment_Sales_CustomerPayment;
import in.hiaccounts.hinext.reports.fragment.sales.Fragment_Sales_CustomerStatement;
import in.hiaccounts.hinext.reports.fragment.sales.Fragment_Sales_DebtorsAging;
import in.hiaccounts.hinext.reports.fragment.sales.Fragment_Sales_Invoices;
import in.hiaccounts.hinext.reports.fragment.sales.Fragment_Sales_ItemCommission;
import in.hiaccounts.hinext.reports.fragment.sales.Fragment_Sales_Patient;
import in.hiaccounts.hinext.reports.fragment.sales.Fragment_Sales_PaymentReport;
import in.hiaccounts.hinext.reports.fragment.sales.Fragment_Sales_ProductDelivery;
import in.hiaccounts.hinext.reports.fragment.sales.Fragment_Sales_Quotations;
import in.hiaccounts.hinext.reports.fragment.sales.Fragment_Sales_Return;
import in.hiaccounts.hinext.reports.fragment.sales.Fragment_Sales_Salesorder;
import in.hiaccounts.hinext.reports.fragment.sales.Fragment_Sales_Salesorder_details;
import in.hiaccounts.hinext.reports.mvpdagger2retrofitjava.mvp.RestuarantDayEndRegisterActivity;

/**
 * Created by Prateek on 2/9/2017.
 */

public class Activity_ReportSubmenu extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fragment_container_id)
    FrameLayout fragmentContainerId;

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

    public void initComponentView() {

        Intent intent = getIntent();
        if (intent != null) {
            String menuTitle = intent.getStringExtra("menu");

            if (menuTitle != null) {

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

                if (menuTitle.equals(getResources().getString(R.string.reportPurSupplierSalesReport))) {
                    showPurSupplierSalesReportFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.reportPurReceiveItem))) {
                    showPurRecieveItemFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.reportPurSupplierStatment))) {
                    showPurSupplierStatementFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.reportPurPO))) {
                    showPurPOFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.reportPurSuppliers))) {
                    showPurSuppliersFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.reportPurInvoices))) {
                    showPurInovicesFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.reportPurReturn))) {
                    showOurReturnFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.reportPurSuppPayments))) {
                    showPurSuppPaymentsFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.reportPurCreditors))) {
                    showPurCreditorsFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.reportPurAdjusment))) {
                    showPurAdjustmentFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.reportPurCancelledInovices))) {
                    showPurCancelledInovicesFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.reportbkBank))) {
                    showBRBankFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.reportbkCheques))) {
                    showBRChequesFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.reportbkRatios))) {
                    showBRRatiosFragment();
                }

                if (menuTitle.equals(getResources().getString(R.string.reportfsComprehensiveincome))) {
                    showFSComprehensiveIncomeFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.reportfsFinancialPosition))) {
                    showFSFinancialPositionFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.reportfsTrialBalance))) {
                    showFSTrialBalanceFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.reportfsGeneralLedger))) {
                    showFSGeneralLedgerFragment();
                }

                if (menuTitle.equals(getResources().getString(R.string.reportfsViewLedger))) {
                    showFSViewLedgerFragment();
                }

                if (menuTitle.equals(getResources().getString(R.string.reportfsJournal))) {
                    showFSJournalFragment();
                }


                if (menuTitle.equals(getResources().getString(R.string.reportSalesQuotation))) {
                    showSalesQuotationFragment();
                }

                if (menuTitle.equals(getResources().getString(R.string.reportSalesOrders))) {
                    showSalesOrdersFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.reportSalesOrdersDetails))) {
                    showSalesOrdersDetailsFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.reportSalesItemCommission))) {
                    showSalesItemCommissionFragment();
                }

                if (menuTitle.equals(getResources().getString(R.string.reportSalesInvoices))) {
                    showSalesInvoicesFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.reportSalesReturn))) {
                    showSalesReturnFragment();
                }

                if (menuTitle.equals(getResources().getString(R.string.reportSalesDelivery))) {
                    showSalesDeliveryFragment();
                }


                if (menuTitle.equals(getResources().getString(R.string.reportSalesCustomerStatement))) {
                    showSalesCustomerStatmentFragment();
                }

                if (menuTitle.equals(getResources().getString(R.string.reportSalesPaymentReport))) {
                    showSalesPaymentReportFragment();
                }

                if (menuTitle.equals(getResources().getString(R.string.reportSalesCommissionAging))) {
                    showSalesCommissionAgingFragment();
                }

                if (menuTitle.equals(getResources().getString(R.string.reportSalesDebtorsAging))) {
                    showSalesDebtorsAgingFragment();
                }

                if (menuTitle.equals(getResources().getString(R.string.reportSalesCustomers))) {
                    showSalesCustomersFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.reportSalesAgent))) {
                    showSalesAgentFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.reportSalesPatient))) {
                    showSalesPatientFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.reportSalesCustomerPayment))) {
                    showSalesCustomerPaymentFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.reportSalesAdjustment))) {
                    showSalesAdjusmentFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.reportSalesCancelledInvoices))) {
                    showSalesCancelledInvocieFragment();
                }

                if (menuTitle.equals(getResources().getString(R.string.reportInvGRNValuation))) {
                    showInvGRNValuationFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.reportInvPlanning))) {
                    showInvPlanningFragment();
                }

                if (menuTitle.equals(getResources().getString(R.string.reportInvValuation))) {
                    showInvValuationFragment();
                }

                if (menuTitle.equals(getResources().getString(R.string.reportInvSales))) {
                    showInvSalesFragment();
                }

                if (menuTitle.equals(getResources().getString(R.string.reportInvNonMovement))) {
                    showInvNonMovementFragment();
                }

                if (menuTitle.equals(getResources().getString(R.string.reportInvMovement))) {
                    showInvMovementFragment();
                }

                if (menuTitle.equals(getResources().getString(R.string.reportInvList))) {
                    showInvListFragment();
                }

                if (menuTitle.equals(getResources().getString(R.string.reportInvOutstandingGNR))) {
                    showInvOutstandingGNRFragment();
                }

                if (menuTitle.equals(getResources().getString(R.string.reportInvStockCheck))) {

                    showInvStockCheckFragment();
                }

                if (menuTitle.equals(getResources().getString(R.string.reportInvAppleStock))) {
                    showInvAppleStockFragment();
                }

                if (menuTitle.equals(getResources().getString(R.string.reportInvAppleSales))) {
                    showInvAppleSalesFragment();
                }


                if (menuTitle.equals(getResources().getString(R.string.reportAnalysisActBalance))) {
                    showAnalysisActBalanceFragment();
                }

                if (menuTitle.equals(getResources().getString(R.string.reportAnalysisCustomerReciept))) {
                    showAnalysisCustomerRecieptFragment();
                }

                if (menuTitle.equals(getResources().getString(R.string.reportAnalysisSuppPayment))) {
                    showAnalysisSuppPaymentFragment();
                }

                if (menuTitle.equals(getResources().getString(R.string.reportAnalysisIncome))) {
                    showAnalysisIncomeFragment();
                }

                if (menuTitle.equals(getResources().getString(R.string.reportAnalysisExpense))) {
                    showAnalysisExpenesesFragment();
                }

                if (menuTitle.equals(getResources().getString(R.string.reportAnalysisCustInvoices))) {
                    showAnalysisCustomerInvoicesFragment();
                }

                if (menuTitle.equals(getResources().getString(R.string.reportAnalysisSuppInovices))) {
                    showAnalysisSuppInovicesFragment();
                }

                if (menuTitle.equals(getResources().getString(R.string.reportAnalysisTopVendors))) {
                    showAnalysisTopVendorsFragment();
                }

                if (menuTitle.equals(getResources().getString(R.string.reportAnalysisTopItems))) {
                    showAnalysisTopItemsFragment();
                }

                if (menuTitle.equals(getResources().getString(R.string.reportAnalysisTopCustomers))) {
                    showAnalysisTopCusotmersFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.reportSRestaurentInvoiceRegister))) {
                    showRestaurentInvoiceRegisterFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.reportSRestaurentDayEndRegister))) {
                    showRestaurentDayEndRegisterFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.reportSRestaurentMonthEnd))) {
                    showRestaurentMonthEndRegisterFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.reportSRestaurentItemSales))) {
                    showRestaurentItemSalesFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.reportSRestaurentDayEnd))) {
                    showRestaurentDayEndFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.reportSRestaurentCancelledItemReports))) {
                    showRestaurentCancelledItemReportsFragment();
                }
            }
        }
    }




    // function for call fragment view.
    public void showScreen(Fragment content, String contentTag, boolean addToBackStack, boolean clearBackStack) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(R.anim.left_slide_in, R.anim.left_slide_out, R.anim.right_slide_in, R.anim.right_slide_out);
        ft.replace(R.id.fragment_container_id, content, contentTag);

        if (clearBackStack) {

            fm.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }

        if (addToBackStack) {

            ft.addToBackStack(String.valueOf(System.identityHashCode(content)));
        }

        ft.commitAllowingStateLoss();
        fm.executePendingTransactions();
    }

    private void showRestaurentInvoiceRegisterFragment() {
        showScreen(Fragment_RestaurentInvoiceRegister.newInstance(), Fragment_RestaurentInvoiceRegister.TAG, false, true);
    }
    private void showRestaurentDayEndRegisterFragment() {
      /*  Intent in = new Intent(Activity_ReportSubmenu.this,RestuarantDayEndRegisterActivity.class);
        startActivity(in);*/

        showScreen(Fragment_RestaurentDayEndRegister.newInstance(), Fragment_RestaurentDayEndRegister.TAG, false, true);
    }
    private void showRestaurentItemSalesFragment() {
        showScreen(Fragment_RestaurentItemSalesReport.newInstance(), Fragment_RestaurentItemSalesReport.TAG, false, true);

    }
    private void showRestaurentDayEndFragment() {
        showScreen(Fragment_RestaurentDayEndReport.newInstance(), Fragment_RestaurentDayEndReport.TAG, false, true);
    }
    private void showRestaurentMonthEndRegisterFragment() {
        showScreen(Fragment_RestaurentMonthEndReport.newInstance(), Fragment_RestaurentMonthEndReport.TAG, false, true);
    }
    private void showRestaurentCancelledItemReportsFragment() {
        showScreen(Fragment_RestaurentCancelledItemReports.newInstance(), Fragment_RestaurentCancelledItemReports.TAG, false, true);
    }

    public void showPurSupplierSalesReportFragment() {
        showScreen(Fragment_RepPurchase_SupplierSales.newInstance(), Fragment_RepPurchase_SupplierSales.TAG, false, true);
    }

    public void showPurRecieveItemFragment() {
        showScreen(Fragment_RepPurchase_RecieveItem.newInstance(), Fragment_RepPurchase_RecieveItem.TAG, false, true);
    }

    public void showPurSupplierStatementFragment() {
        showScreen(Fragment_RepPurchase_SupplierStatment.newInstance(), Fragment_RepPurchase_SupplierStatment.TAG, false, true);
    }

    public void showPurPOFragment() {
        showScreen(Fragment_RepPurchase_PurchaseOrder.newInstance(), Fragment_RepPurchase_PurchaseOrder.TAG, false, true);
    }

    public void showPurSuppliersFragment() {
        showScreen(Fragment_RepPurchase_Suppliers.newInstance(), Fragment_RepPurchase_Suppliers.TAG, false, true);
    }

    public void showPurInovicesFragment() {
        showScreen(Fragment_RepPurchase_Inovices.newInstance(), Fragment_RepPurchase_Inovices.TAG, false, true);
    }

    public void showOurReturnFragment() {
        showScreen(Fragment_RepPurchase_Returns.newInstance(), Fragment_RepPurchase_Returns.TAG, false, true);
    }

    public void showPurSuppPaymentsFragment() {
        showScreen(Fragment_RepPurchase_SupplierPayment.newInstance(), Fragment_RepPurchase_SupplierPayment.TAG, false, true);
    }

    public void showPurCreditorsFragment() {
        showScreen(Fragment_RepPurchase_Creditors.newInstance(), Fragment_RepPurchase_Creditors.TAG, false, true);
    }

    public void showPurAdjustmentFragment() {
        showScreen(Fragment_RepPurchase_Adjustment.newInstance(), Fragment_RepPurchase_Adjustment.TAG, false, true);
    }

    public void showPurCancelledInovicesFragment() {
        showScreen(Fragment_RepPurchase_CacnelledInovices.newInstance(), Fragment_RepPurchase_CacnelledInovices.TAG, false, true);
    }

    public void showBRBankFragment() {
        showScreen(Fragment_ReportBR_Banks.newInstance(), Fragment_ReportBR_Banks.TAG, false, true);
    }

    public void showBRChequesFragment() {
        showScreen(Fragment_ReportBR_Cheques.newInstance(), Fragment_ReportBR_Cheques.TAG, false, true);
    }

    public void showBRRatiosFragment() {
        showScreen(Fragment_ReportBR_Ratios.newInstance(), Fragment_ReportBR_Ratios.TAG, false, true);
    }


    public void showFSComprehensiveIncomeFragment() {
        showScreen(Fragment_ReportFS_Income.newInstance(), Fragment_ReportFS_Income.TAG, false, true);
    }

    public void showFSFinancialPositionFragment() {
        showScreen(Fragment_ReportFS_FinancialPosition.newInstance(), Fragment_ReportFS_FinancialPosition.TAG, false, true);
    }

    public void showFSTrialBalanceFragment() {
        showScreen(Fragment_ReportFS_TrialBalance.newInstance(), Fragment_ReportFS_TrialBalance.TAG, false, true);
    }

    public void showFSGeneralLedgerFragment() {
        showScreen(Fragment_ReportFS_GeneralLedger.newInstance(), Fragment_ReportFS_GeneralLedger.TAG, false, true);
    }

    public void showFSViewLedgerFragment() {
        showScreen(Fragment_ReportFS_ViewLedger.newInstance(), Fragment_ReportFS_ViewLedger.TAG, false, true);
    }

    public void showFSJournalFragment() {
        showScreen(Fragment_ReportFS_Journal.newInstance(), Fragment_ReportFS_Journal.TAG, false, true);
    }

    public void showSalesDeliveryFragment() {
        showScreen(Fragment_Sales_ProductDelivery.newInstance(), Fragment_Sales_ProductDelivery.TAG, false, true);
    }

    public void showSalesCancelledInvocieFragment() {
        showScreen(Fragment_Sales_CancelledInvoices.newInstance(), Fragment_Sales_CancelledInvoices.TAG, false, true);
    }

    public void showSalesAdjusmentFragment() {
        showScreen(Fragment_Sales_Adjustment.newInstance(), Fragment_Sales_Adjustment.TAG, false, true);
    }

    public void showSalesCustomerPaymentFragment() {
        showScreen(Fragment_Sales_CustomerPayment.newInstance(), Fragment_Sales_CustomerPayment.TAG, false, true);
    }

    public void showSalesPatientFragment() {
        showScreen(Fragment_Sales_Patient.newInstance(), Fragment_Sales_Patient.TAG, false, true);
    }

    public void showSalesAgentFragment() {
        showScreen(Fragment_Sales_Agent.newInstance(), Fragment_Sales_Agent.TAG, false, true);
    }

    public void showSalesCustomersFragment() {
        showScreen(Fragment_Sales_Customer.newInstance(), Fragment_Sales_Customer.TAG, false, true);
    }

    public void showSalesDebtorsAgingFragment() {
        showScreen(Fragment_Sales_DebtorsAging.newInstance(), Fragment_Sales_DebtorsAging.TAG, false, true);
    }

    public void showSalesCommissionAgingFragment() {
        showScreen(Fragment_Sales_CommissionAging.newInstance(), Fragment_Sales_CommissionAging.TAG, false, true);
    }

    public void showSalesQuotationFragment() {
        showScreen(Fragment_Sales_Quotations.newInstance(), Fragment_Sales_Quotations.TAG, false, true);
    }

    public void showSalesPaymentReportFragment() {
        showScreen(Fragment_Sales_PaymentReport.newInstance(), Fragment_Sales_PaymentReport.TAG, false, true);
    }

    public void showSalesOrdersFragment() {
        showScreen(Fragment_Sales_Salesorder.newInstance(), Fragment_Sales_Salesorder.TAG, false, true);
    }
    public void showSalesOrdersDetailsFragment() {
        showScreen(Fragment_Sales_Salesorder_details.newInstance(), Fragment_Sales_Salesorder_details.TAG, false, true);
    }
    public void showSalesItemCommissionFragment() {
        showScreen(Fragment_Sales_ItemCommission.newInstance(), Fragment_Sales_ItemCommission.TAG, false, true);
    }

    public void showSalesInvoicesFragment() {
        showScreen(Fragment_Sales_Invoices.newInstance(), Fragment_Sales_Invoices.TAG, false, true);
    }

    public void showSalesReturnFragment() {
        showScreen(Fragment_Sales_Return.newInstance(), Fragment_Sales_Return.TAG, false, true);
    }

    public void showSalesCustomerStatmentFragment() {
        showScreen(Fragment_Sales_CustomerStatement.newInstance(), Fragment_Sales_CustomerStatement.TAG, false, true);
    }

    public void showAnalysisTopCusotmersFragment() {
        showScreen(Fragment_Analysis_TopCustomer.newInstance(), Fragment_Analysis_TopCustomer.TAG, false, true);
    }

    public void showAnalysisTopItemsFragment() {
        showScreen(Fragment_Analysis_TopItem.newInstance(), Fragment_Analysis_TopItem.TAG, false, true);
    }

    public void showAnalysisTopVendorsFragment() {
        showScreen(Fragment_Analysis_TopVendors.newInstance(), Fragment_Analysis_TopVendors.TAG, false, true);
    }

    public void showAnalysisSuppInovicesFragment() {
        showScreen(Fragment_Analysis_SupplierInvocies.newInstance(), Fragment_Analysis_SupplierInvocies.TAG, false, true);
    }

    public void showAnalysisCustomerInvoicesFragment() {
        showScreen(Fragment_Analysis_CustomerInvoices.newInstance(), Fragment_Analysis_CustomerInvoices.TAG, false, true);
    }

    public void showAnalysisExpenesesFragment() {
        showScreen(Fragment_Analysis_Expenses.newInstance(), Fragment_Analysis_Expenses.TAG, false, true);
    }

    public void showAnalysisIncomeFragment() {
        showScreen(Fragment_Analysis_Income.newInstance(), Fragment_Analysis_Income.TAG, false, true);
    }

    public void showAnalysisSuppPaymentFragment() {
        showScreen(Fragment_Analysis_SupplierPayment.newInstance(), Fragment_Analysis_SupplierPayment.TAG, false, true);
    }

    public void showAnalysisCustomerRecieptFragment() {
        showScreen(Fragment_Analysis_CustomerReceipt.newInstance(), Fragment_Analysis_CustomerReceipt.TAG, false, true);
    }

    public void showAnalysisActBalanceFragment() {
        showScreen(Fragment_Analysis_ActBalance.newInstance(), Fragment_Analysis_ActBalance.TAG, false, true);
    }

    public void showInvMovementFragment() {
        showScreen(Fragment_Inventory_Movement.newInstance(), Fragment_Inventory_Movement.TAG, false, true);
    }

    public void showInvNonMovementFragment() {
        showScreen(Fragment_Inventory_NonMovement.newInstance(), Fragment_Inventory_NonMovement.TAG, false, true);
    }

    public void showInvListFragment() {
        showScreen(Fragment_Inventory_InvList.newInstance(), Fragment_Inventory_InvList.TAG, false, true);
    }

    public void showInvSalesFragment() {
        showScreen(Fragment_Inventory_Sales.newInstance(), Fragment_Inventory_Sales.TAG, false, true);
    }

    public void showInvOutstandingGNRFragment() {
        showScreen(Fragment_Inventory_GRNOutstanding.newInstance(), Fragment_Inventory_GRNOutstanding.TAG, false, true);
    }

    public void showInvStockCheckFragment() {
        showScreen(Fragment_Inventory_StockCheck.newInstance(), Fragment_Inventory_StockCheck.TAG, false, true);
    }

    public void showInvAppleStockFragment() {
        showScreen(Fragment_Inventory_AppleStock.newInstance(), Fragment_Inventory_AppleStock.TAG, false, true);
    }

    public void showInvAppleSalesFragment() {
        showScreen(Fragment_Inventory_AppleSales.newInstance(), Fragment_Inventory_AppleSales.TAG, false, true);
    }

    public void showInvValuationFragment() {
        showScreen(Fragment_Inventory_Valuation.newInstance(), Fragment_Inventory_Valuation.TAG, false, true);
    }


    public void showInvPlanningFragment() {
        showScreen(Fragment_Inventory_Planning.newInstance(), Fragment_Inventory_Planning.TAG, false, true);

    }

    public void showInvGRNValuationFragment() {
        showScreen(Fragment_Inventory_GRNValuation.newInstance(), Fragment_Inventory_GRNValuation.TAG, false, true);

    }


}
