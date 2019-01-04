package in.hiaccounts.hinext.application.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.rey.material.widget.ProgressView;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import in.hiaccounts.R;
import in.hiaccounts.hinext.application.adapter.NavigationMenu_Adapter;
import in.hiaccounts.hinext.application.fragment.Fragment_DashBoard;
import in.hiaccounts.hinext.application.model.PageLoadDataForAll;
import in.hiaccounts.hinext.contact.activity.Activity_Contact;
import in.hiaccounts.hinext.controlpanel.fragment.accountmaintenance.Fragment_AccountMaintenance;
import in.hiaccounts.hinext.controlpanel.fragment.companysetup.Fragment_CompanySetup;
import in.hiaccounts.hinext.controlpanel.fragment.configurator.Fragment_Configurator;
import in.hiaccounts.hinext.controlpanel.fragment.displaysetup.Fragment_DisplaySetup;
import in.hiaccounts.hinext.controlpanel.fragment.openingbalance.Fragment_OpeningBalance;
import in.hiaccounts.hinext.controlpanel.fragment.useraccountsetup.Fragment_UserAccountSetup;
import in.hiaccounts.hinext.controlpanel.model.company_setup.compnayinformation.CompanyData;
import in.hiaccounts.hinext.crm.fragment.Fragment_Crm;
import in.hiaccounts.hinext.customer.activity.Activity_Customer;
import in.hiaccounts.hinext.generaltransaction.fragment.Fragment_TransactionExpenses;
import in.hiaccounts.hinext.generaltransaction.fragment.Fragment_TransactionJournalEntry;
import in.hiaccounts.hinext.generaltransaction.fragment.Fragment_TransactionReceipt;
import in.hiaccounts.hinext.help.fragment.Fragment_HelpManual;
import in.hiaccounts.hinext.help.fragment.Fragment_HelpSubscription;
import in.hiaccounts.hinext.help.fragment.Fragment_HelpSupport;
import in.hiaccounts.hinext.help.fragment.Fragment_HelpVideo;
import in.hiaccounts.hinext.inventory.fragment.Fragment_Inventory;
import in.hiaccounts.hinext.inventory.fragment.Fragment_InventoryBrand;
import in.hiaccounts.hinext.inventory.fragment.Fragment_InventoryCategory;
import in.hiaccounts.hinext.inventory.fragment.Fragment_InventoryCountType;
import in.hiaccounts.hinext.inventory.fragment.Fragment_InventoryItem;
import in.hiaccounts.hinext.inventory.fragment.Fragment_InventoryLocation;
import in.hiaccounts.hinext.inventory.fragment.Fragment_InventoryPurchasePricing;
import in.hiaccounts.hinext.inventory.fragment.Fragment_InventorySalesDiscountConfig;
import in.hiaccounts.hinext.inventory.fragment.Fragment_InventorySalesPricing;
import in.hiaccounts.hinext.inventory.fragment.Fragment_InventoryUOM;
import in.hiaccounts.hinext.manufacturing.fragment.Fragment_Manufacturing;
import in.hiaccounts.hinext.purchase.fragment.Fragment_Purchase;
import in.hiaccounts.hinext.reports.fragment.analysis.Fragment_ReportAnalysis;
import in.hiaccounts.hinext.reports.fragment.bankreconciliation.Fragment_ReportBankReconciliation;
import in.hiaccounts.hinext.reports.fragment.financialstatement.Fragment_ReportFinancialStatement;
import in.hiaccounts.hinext.reports.fragment.inventory.Fragment_ReportInventory;
import in.hiaccounts.hinext.reports.fragment.purchase.Fragment_ReportPurchase;
import in.hiaccounts.hinext.reports.fragment.restaurent.Fragment_ReportRestaurent;
import in.hiaccounts.hinext.reports.fragment.sales.Fragment_ReportSales;
import in.hiaccounts.hinext.restaurant.fragment.Fragment_Restaurant;
import in.hiaccounts.hinext.sales.fragment.Fragment_Sales;
import in.hiaccounts.hinext.service.fragment.Fragment_Service;
import in.hiaccounts.hinext.supplier.activity.Activity_Supplier;
import in.hiaccounts.hinext.tax.fragment.gst.Fragment_GST;
import in.hiaccounts.hinext.tax.fragment.taxconfiguration.Fragment_TaxConfiguration;
import in.hiaccounts.model.NavigationHeaderMenu;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 4/25/2017.
 */

public class NavigationDrawer_Activity extends AppCompatActivity implements ExpandableListView.OnChildClickListener, ExpandableListView.OnGroupClickListener {

    //CompanyList<String> listDataHeader;
    List<NavigationHeaderMenu> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    List<NavigationHeaderMenu> listDataHeaderRight;
    HashMap<String, List<String>> listDataChildRight;
    Resources res;
    FrameLayout fragment_layout;
    SharedPreference sharedPreference;
    Activity mActivity = NavigationDrawer_Activity.this;
    ProgressView progress_bar;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ExpandableListView drawerList,rightDrawerList;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    public static final String TAG = NavigationDrawer_Activity.class.getSimpleName();

    private ServiceHandler serviceHandler;
    Boolean isInternetPresent = false;
    private PageLoadDataForAll pageData;
    private String  serverUrl;
    NavigationMenu_Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigationdrawer);
        initView();

    }


    private void initView() {
        sharedPreference = SharedPreference.getInstance(mActivity);
        serviceHandler = new ServiceHandler(mActivity);

        res = getResources();
        progress_bar = (ProgressView) findViewById(R.id.progress_bar);
        fragment_layout = (FrameLayout) findViewById(R.id.fragment_layout);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        toolbar.setNavigationIcon(R.drawable.ic_menu);




        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        hideRightDrawer();
        drawerList = (ExpandableListView) findViewById(R.id.left_drawer);
        rightDrawerList = (ExpandableListView) findViewById(R.id.right_drawer);



        LayoutInflater layout = LayoutInflater.from(this);

        View headerView = layout.inflate(R.layout.navigation_header_layout, null);

        CircularImageView imgviewComapnyLogo = headerView.findViewById(R.id.imgviewCompanyLogo);
        TextView tvCompanyName = headerView.findViewById(R.id.tvCompanyName);
        TextView tvCompanyUserName = headerView.findViewById(R.id.tvCompanyUserName);
        drawerList.addHeaderView(headerView);






        String applicationDataJson = sharedPreference.getData(Constant.COMPANYDATA);


        if (applicationDataJson != null) {
            Gson gson = new Gson();
            CompanyData companyData = gson.fromJson(applicationDataJson, CompanyData.class);


            if (companyData != null) {
                if (companyData.getCompanyName()!=null){
                    tvCompanyName.setText(companyData.getCompanyName());
                }

                String userName=sharedPreference.getData(Constant.USERNAME);
                if (userName!=null)
                tvCompanyUserName.setText(userName);

                //if (companyData.getFileName() != null) {
                    String url=UtilView.getUrl(mActivity);

                    if (url!=null) {
                        Picasso.with(this).load(url + companyData.getFileName())
                                .networkPolicy(NetworkPolicy.NO_CACHE)
                                .memoryPolicy(MemoryPolicy.NO_CACHE)
                                .placeholder(R.drawable.ic_progress)
                                .error(R.drawable.ic_app_icon)
                                .into(imgviewComapnyLogo);
                    }else {
                        UtilView.gotToLogin(mActivity);
                    }
               // }
            } else {
                imgviewComapnyLogo.setImageDrawable(getResources().getDrawable(R.drawable.ic_app_icon));
            }
        }


        // preparing list data
        prepareListData();


        drawerList.setOnChildClickListener(this);
        drawerList.setOnGroupClickListener(this);



        rightDrawerList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int groupPosition, long l) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_layout);

                Fragment_Purchase fragmentPurchase = null;
                Fragment_Sales fragmentSales = null;
                Fragment_TransactionExpenses fragment_transactionExpenses = null;
                Fragment_TransactionReceipt fragment_transactionReceipt = null;
                Fragment_TransactionJournalEntry fragment_transactionJournalEntry = null;

                Fragment_Service fragmentService = null;
                if (fragment instanceof Fragment_Purchase){
                    fragmentPurchase = (Fragment_Purchase) fragment;

                }

                if (fragment instanceof Fragment_Sales){
                    fragmentSales= (Fragment_Sales) fragment;
                }
                if (fragment instanceof Fragment_TransactionExpenses){
                    fragment_transactionExpenses= (Fragment_TransactionExpenses) fragment;
                }
                if (fragment instanceof Fragment_TransactionReceipt){
                    fragment_transactionReceipt= (Fragment_TransactionReceipt) fragment;
                }
                if (fragment instanceof Fragment_TransactionJournalEntry){
                    fragment_transactionJournalEntry= (Fragment_TransactionJournalEntry) fragment;
                }


                if (fragment instanceof Fragment_Service){
                    fragmentService= (Fragment_Service) fragment;
                }



                if (listDataChildRight.get(listDataHeaderRight.get(groupPosition).getMenuTitle())!=null && listDataChildRight.get(listDataHeaderRight.get(groupPosition).getMenuTitle()).size() > 0) {


                } else {

                    String headerMenu = null;
                    switch ((listDataHeaderRight.get(groupPosition).getMenuTitle())){

                        case "Print List":
                            drawer.closeDrawer(GravityCompat.END);
                            headerMenu = (listDataHeaderRight.get(groupPosition).getMenuTitle());

                            if (fragment_transactionExpenses !=null)
                                fragment_transactionExpenses.callGTExpensePrintList();

                            if (fragment_transactionReceipt !=null)
                                fragment_transactionReceipt.callGTReceiptPrintList();

                            if (fragment_transactionJournalEntry !=null)
                                fragment_transactionJournalEntry.callGTJournalPrintList();

                            break;

                        case "Clear All":
                            drawer.closeDrawer(GravityCompat.END);
                            headerMenu = (listDataHeaderRight.get(groupPosition).getMenuTitle());

                            if (fragment_transactionExpenses !=null)
                                fragment_transactionExpenses.callClearAll();

                            if (fragment_transactionReceipt !=null)
                                fragment_transactionReceipt.callClearAll();

                            if (fragmentSales !=null)
                                fragmentSales.callClearAll();

                            if (fragmentPurchase !=null)
                                fragmentPurchase.callClearAll();

                            break;

                        case "Credit Note":
                                drawer.closeDrawer(GravityCompat.END);
                                headerMenu = (listDataHeaderRight.get(groupPosition).getMenuTitle());
                                if (fragmentPurchase!=null)
                                fragmentPurchase.callCreditNoteItem();

                            if (fragmentSales!=null)
                                fragmentSales.callCreditNote();


                            break;
                        case "PIReturn Item":
                                drawer.closeDrawer(GravityCompat.END);
                                headerMenu = (listDataHeaderRight.get(groupPosition).getMenuTitle());
                                fragmentPurchase.callPIReturnItem();
                            break;
                        case "Debit Note":
                            drawer.closeDrawer(GravityCompat.END);
                            headerMenu = (listDataHeaderRight.get(groupPosition).getMenuTitle());
                            if (fragmentSales!=null)
                            fragmentSales.callDebitNote();


                            if (fragmentPurchase!=null)
                                fragmentPurchase.callDebitNoteItem();
                            break;
                        case "Draft Invoice":
                            drawer.closeDrawer(GravityCompat.END);
                            headerMenu = (listDataHeaderRight.get(groupPosition).getMenuTitle());
                            fragmentSales.callDraftInvoice();
                            break;
                        case "Return Item":
                            drawer.closeDrawer(GravityCompat.END);
                            headerMenu = (listDataHeaderRight.get(groupPosition).getMenuTitle());
                            fragmentSales.callSalesReturnItem();
                            break;

                        case "Export Invoice":
                            drawer.closeDrawer(GravityCompat.END);
                            headerMenu = (listDataHeaderRight.get(groupPosition).getMenuTitle());
                            fragmentSales.callExportInvoice();

                            break;
                        case "Payment":
                            drawer.closeDrawer(GravityCompat.END);
                            if (listDataHeaderRight.get(groupPosition).getMenuTitle().equals("Payment"))
                            {
                                Toast.makeText(mActivity, "Permission Denyied", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case "Sales Order":
                            drawer.closeDrawer(GravityCompat.END);
                            if (listDataHeaderRight.get(groupPosition).getMenuTitle().equals("Sales Order"))
                            {
                                Toast.makeText(mActivity, "Permission Denyied", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case "Purchase Order":
                            drawer.closeDrawer(GravityCompat.END);
                            if (listDataHeaderRight.get(groupPosition).getMenuTitle().equals("Purchase Order"))
                            {
                                Toast.makeText(mActivity, "Permission Denyied", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case "Quotation":
                            drawer.closeDrawer(GravityCompat.END);
                            if (listDataHeaderRight.get(groupPosition).getMenuTitle().equals("Quotation"))
                            {
                                Toast.makeText(mActivity, "Permission Denyied", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case "Pro Forma":
                            drawer.closeDrawer(GravityCompat.END);
                            if (listDataHeaderRight.get(groupPosition).getMenuTitle().equals("Pro Forma"))
                            {
                                Toast.makeText(mActivity, "Permission Denyied", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case "Drafts":
                            drawer.closeDrawer(GravityCompat.END);
                            if (listDataHeaderRight.get(groupPosition).getMenuTitle().equals("Drafts"))
                            {
                                Toast.makeText(mActivity, "Permission Denyied", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case "Delivery Order":
                            drawer.closeDrawer(GravityCompat.END);
                            headerMenu = (listDataHeaderRight.get(groupPosition).getMenuTitle());
                            /*if (fragmentService!=null)
                                fragmentService.callServiceDelivery();*/
                            break;

                        case "Select Invoice":
                            drawer.closeDrawer(GravityCompat.END);
                            headerMenu = (listDataHeaderRight.get(groupPosition).getMenuTitle());
                           if (fragmentService!=null)
                               fragmentService.callInvoiceList();
                            break;



                    }

                }
                return false;
            }
        });

        rightDrawerList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_layout);

                Fragment_Purchase fragmentPurchase = null;
                Fragment_Sales fragmentSales = null;
                Fragment_Service fragmentService = null;

                if (fragment instanceof Fragment_Purchase){
                    fragmentPurchase = (Fragment_Purchase) fragment;

                }

                if (fragment instanceof Fragment_Sales){
                    fragmentSales= (Fragment_Sales) fragment;
                }
                if (fragment instanceof Fragment_Service){
                    fragmentService= (Fragment_Service) fragment;
                }
                if (listDataHeaderRight.get(groupPosition).getMenuTitle().equals(res.getString(R.string.module_menupayment))) {

                    String childMenu=listDataChildRight.get(listDataHeaderRight.get(groupPosition).getMenuTitle()).get(childPosition);


                    if (childMenu.equals(res.getString(R.string.recievePayment))){
                        if(fragmentSales !=null){
                            fragmentSales.callRecievePayment();

                        }
                    }

                    if (childMenu.equals(res.getString(R.string.supplierPayment))){
                        fragmentPurchase.callSupplierPaymet();
                    }

                    if (childMenu.equals(res.getString(R.string.advacnePayment))){

                        if(fragmentSales !=null){
                            fragmentSales.callSalesAdvancePayment();
                        }
                        if(fragmentPurchase !=null){
                            fragmentPurchase.callPurchaseAdvancePayment();
                        }

                    }





                }

                if (listDataHeaderRight.get(groupPosition).getMenuTitle().equals(res.getString(R.string.module_menurecievitem))) {

                    String childMenu=listDataChildRight.get(listDataHeaderRight.get(groupPosition).getMenuTitle()).get(childPosition);


                    if (childMenu.equals(res.getString(R.string.recieveSave))){
                        fragmentPurchase.callSaveRecieveItem();
                    }

                    if (childMenu.equals(res.getString(R.string.recieveInvoke))){
                        fragmentPurchase.callInvokeRecieveItem();
                    }

                    if (childMenu.equals(res.getString(R.string.recieveReturn))){
                        fragmentPurchase.callReturnRecieveItem();
                    }

                }

                if (listDataHeaderRight.get(groupPosition).getMenuTitle().equals(res.getString(R.string.module_menupurchaseorder))) {

                    String childMenu=listDataChildRight.get(listDataHeaderRight.get(groupPosition).getMenuTitle()).get(childPosition);

                    if (childMenu.equals(res.getString(R.string.orderSave))){
                        fragmentPurchase.callSavePurchaseOrderApi();


                    }

                    if (childMenu.equals(res.getString(R.string.orderInvoke))){
                        fragmentPurchase.callInvokePurchaseOrderApi();
                    }
                }

                if (listDataHeaderRight.get(groupPosition).getMenuTitle().equals(res.getString(R.string.module_menuquotation))) {

                    String childMenu=listDataChildRight.get(listDataHeaderRight.get(groupPosition).getMenuTitle()).get(childPosition);

                    if (childMenu.equals(res.getString(R.string.quotationsave))){
                        if (fragmentPurchase!=null){
                            fragmentPurchase.callSaveQuotation();
                        }
                        if (fragmentSales!=null){
                            fragmentSales.callSaveQuotation();
                        }
                    }


                    if (childMenu.equals(res.getString(R.string.quotationinvoke))){
                        if (fragmentPurchase!=null){
                            fragmentPurchase.callInvokeQuotation();
                        }
                        if (fragmentSales!=null){
                            fragmentSales.callInvokeQuotation();
                        }

                    }
                }

                if (listDataHeaderRight.get(groupPosition).getMenuTitle().equals(res.getString(R.string.module_menuprintlist))) {

                    String childMenu=listDataChildRight.get(listDataHeaderRight.get(groupPosition).getMenuTitle()).get(childPosition);

                    if (childMenu.equals(res.getString(R.string.printlistcreditnote))){
                        fragmentPurchase.callPrintListCreditNote();
                    }

                    if (childMenu.equals(res.getString(R.string.printlistinvoice))){
                        fragmentPurchase.callPrintListInvoice();
                    }
                    if (childMenu.equals(res.getString(R.string.printlistpurchaseorder))){
                        fragmentPurchase.callPrintListPurchaseOrder();
                    }
                    if (childMenu.equals(res.getString(R.string.printlistpurchasereturn))){
                        fragmentPurchase.callPrintListPurchaseReturn();
                    }
                    if (childMenu.equals(res.getString(R.string.printlistpurchasequotation))){
                        fragmentPurchase.callPrintListPurchaseQuotation();
                    }
                    if (childMenu.equals(res.getString(R.string.printlistrecieveitem))){
                        fragmentPurchase.callPrintListReceiveItem();
                    }
                    if (childMenu.equals(res.getString(R.string.printlistpaymentlisting))){
                        fragmentPurchase.callPrintListPaymentlist();
                    }

                }

                if (listDataHeaderRight.get(groupPosition).getMenuTitle().equals(res.getString(R.string.module_menusalesorder))) {

                    String childMenu=listDataChildRight.get(listDataHeaderRight.get(groupPosition).getMenuTitle()).get(childPosition);

                    if (childMenu.equals(res.getString(R.string.orderSave))){
                        if (fragmentSales !=null)
                            fragmentSales.callSaveSalesOrder();


                    }

                    if (childMenu.equals(res.getString(R.string.orderInvoke))){
                        if (fragmentSales !=null)
                            fragmentSales.callInvokeSalesOrder();

                    }
                }
                if (listDataHeaderRight.get(groupPosition).getMenuTitle().equals(res.getString(R.string.module_menuproforma))) {

                    String childMenu=listDataChildRight.get(listDataHeaderRight.get(groupPosition).getMenuTitle()).get(childPosition);

                    if (childMenu.equals(res.getString(R.string.proformasave))){
                        if (fragmentSales!=null)
                            fragmentSales.callSaveProForma();
                    }

                    if (childMenu.equals(res.getString(R.string.proformainvoke))){
                        if (fragmentSales!=null)
                            fragmentSales.callInvokeProForma();

                    }
                }
                if (listDataHeaderRight.get(groupPosition).getMenuTitle().equals(res.getString(R.string.module_menudeliveryorder))) {

                    String childMenu=listDataChildRight.get(listDataHeaderRight.get(groupPosition).getMenuTitle()).get(childPosition);


                    if (childMenu.equals(res.getString(R.string.deliveryorderSave))){
                        if (fragmentSales!=null)
                            fragmentSales.callValidateDeliveryOrder();

                    }

                    if (childMenu.equals(res.getString(R.string.deliveryorderInvoke))){
                        if (fragmentSales!=null)
                            fragmentSales.callInvokeDeliveryOrder();
                    }

                    if (childMenu.equals(res.getString(R.string.deliveryorderReturn))){
                        if (fragmentSales!=null)
                            fragmentSales.callReturnDeliveryOrder();

                    }

                }
                if (listDataHeaderRight.get(groupPosition).getMenuTitle().equals(res.getString(R.string.module_menuloandeliveryorder))) {

                    String childMenu=listDataChildRight.get(listDataHeaderRight.get(groupPosition).getMenuTitle()).get(childPosition);


                    if (childMenu.equals(res.getString(R.string.deliveryorderSave))){
                        if (fragmentSales!=null)
                            fragmentSales.callValidateLoanDeliveryOrder();

                    }

                    if (childMenu.equals(res.getString(R.string.deliveryorderInvoke))){
                        if (fragmentSales!=null)
                            fragmentSales.callInvokeLoanDeliveryOrder();
                    }

                    if (childMenu.equals(res.getString(R.string.deliveryorderReturn))){
                        if (fragmentSales!=null)
                            fragmentSales.callReturnLoanDeliveryOrder();

                    }

                }

                if (listDataHeaderRight.get(groupPosition).getMenuTitle().equals(res.getString(R.string.module_menuprintlist))) {

                    String childMenu=listDataChildRight.get(listDataHeaderRight.get(groupPosition).getMenuTitle()).get(childPosition);

                    if (childMenu.equals(res.getString(R.string.printlistdebitnote))){
                        if (fragmentSales!=null)
                            fragmentSales.callPrintlistSalesDebitNote();


                    }
                    if (childMenu.equals(res.getString(R.string.printlistsalesinvoices))){
                        if (fragmentSales!=null)
                            fragmentSales.callPrintlistInvoice();

                    }
                    if (childMenu.equals(res.getString(R.string.printlistsalesorder))){
                        if (fragmentSales!=null)
                            fragmentSales.callPrintlistSalesOrder();

                    }
                    if (childMenu.equals(res.getString(R.string.printlistsalesreturn))){
                        if (fragmentSales!=null)
                            fragmentSales.callPrintlistSalesReturn();
                    }
                    if (childMenu.equals(res.getString(R.string.printlistsalesquotation))){
                        if (fragmentSales!=null)
                            fragmentSales.callPrintlistSalesQuotation();

                    }
                    if (childMenu.equals(res.getString(R.string.printlistdeliveryorder))){
                        if (fragmentSales!=null)
                            fragmentSales.callPrintlistDeliveryOrder();

                    }
                    if (childMenu.equals(res.getString(R.string.printlistloandeliveryorder))){
                        if (fragmentSales!=null)
                            fragmentSales.callPrintlistLoanDeliveryOrder();

                    }
                    if (childMenu.equals(res.getString(R.string.printlistreceivedpaymentlisting))){
                        if (fragmentSales!=null)
                            fragmentSales.callPrintlistRecievedPaymentList();

                    }

                    if (childMenu.equals(res.getString(R.string.printlistadvancepaymentlisting))){
                        if (fragmentSales!=null)
                            fragmentSales.callPrintlistAdvancePaymentList();

                    }

                    if (childMenu.equals(res.getString(R.string.printlistproforma))){
                        if (fragmentSales!=null)
                            fragmentSales.callPrintlistProForma();
                    }

                }

                if (listDataHeaderRight.get(groupPosition).getMenuTitle().equals(res.getString(R.string.module_menudrafts))) {

                    String childMenu=listDataChildRight.get(listDataHeaderRight.get(groupPosition).getMenuTitle()).get(childPosition);


                    if (childMenu.equals(res.getString(R.string.draftsSave))){
                        if (fragmentSales!=null)
                            fragmentSales.callDraftSave();

                    }

                    if (childMenu.equals(res.getString(R.string.draftsInvoke))){
                        if (fragmentSales!=null)
                            fragmentSales.invokeDraft();
                    }

                    if (childMenu.equals(res.getString(R.string.draftsClear))){
                        if (fragmentSales!=null)
                            fragmentSales.invokeClearDraft();

                    }

                }
                if (listDataHeaderRight.get(groupPosition).getMenuTitle().equals(res.getString(R.string.module_menurepair))) {

                    String childMenu=listDataChildRight.get(listDataHeaderRight.get(groupPosition).getMenuTitle()).get(childPosition);


                    if (childMenu.equals(res.getString(R.string.repairdeliveredlist))){
                        if (fragmentService!=null)
                            fragmentService.callDeliveredList();

                    }

                    if (childMenu.equals(res.getString(R.string.repairsave))){

                        if (fragmentService!=null)
                            fragmentService.callRepairSave();
                    }


                }
                if (listDataHeaderRight.get(groupPosition).getMenuTitle().equals(res.getString(R.string.module_menudraftrepair))) {

                    String childMenu=listDataChildRight.get(listDataHeaderRight.get(groupPosition).getMenuTitle()).get(childPosition);


                    if (childMenu.equals(res.getString(R.string.repairdraft))){

                        if (fragmentService!=null)
                            fragmentService.callRepairDraft();

                    }

                    if (childMenu.equals(res.getString(R.string.repairinvoke))){

                        if (fragmentService!=null)
                            fragmentService.callRepairInvoke();
                    }


                }
                if (listDataHeaderRight.get(groupPosition).getMenuTitle().equals(res.getString(R.string.module_menureplace))) {

                    String childMenu=listDataChildRight.get(listDataHeaderRight.get(groupPosition).getMenuTitle()).get(childPosition);


                    if (childMenu.equals(res.getString(R.string.replacing))){

                        if (fragmentService!=null)
                            fragmentService.callReplacing();

                    }

                    if (childMenu.equals(res.getString(R.string.replacesave))){

                        if (fragmentService!=null)
                            fragmentService.callReplacSave();
                    }


                }
                if (listDataHeaderRight.get(groupPosition).getMenuTitle().equals(res.getString(R.string.module_menudraftreplace))) {

                    String childMenu=listDataChildRight.get(listDataHeaderRight.get(groupPosition).getMenuTitle()).get(childPosition);


                    if (childMenu.equals(res.getString(R.string.draftreplace))){

                        if (fragmentService!=null)
                            fragmentService.callReplaceDraft();

                    }

                    if (childMenu.equals(res.getString(R.string.draftinvoke))){

                        if (fragmentService!=null)
                            fragmentService.callReplaceInvoke();
                    }


                }
                if (listDataHeaderRight.get(groupPosition).getMenuTitle().equals(res.getString(R.string.module_menuprintlist))) {

                    String childMenu=listDataChildRight.get(listDataHeaderRight.get(groupPosition).getMenuTitle()).get(childPosition);


                    if (childMenu.equals(res.getString(R.string.serviceprintlistrepair))){

                        if (fragmentService!=null)
                            fragmentService.callPrintlistRepair();

                    }

                    if (childMenu.equals(res.getString(R.string.serviceprintlistreplace))){

                        if (fragmentService!=null)
                            fragmentService.callPrintlistReplace();
                    }
                    if (childMenu.equals(res.getString(R.string.serviceprintlistdeliver))){

                        if (fragmentService!=null)
                            fragmentService.callPrintlistDeliver();
                    }


                }




                drawer.closeDrawer(GravityCompat.END);

                return false;
            }
        });

        /*actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) ;
        actionBarDrawerToggle.syncState();*/

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we don't want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we don't want anything to happen so we leave this blank

                if (drawerView.getId()==R.id.left_drawer){
                    //calling sync state is necessay or else your hamburger icon wont show up

                    prepareListData();
                    adapter.notifyDataSetChanged();



                }
                if (drawerView.getId()==R.id.right_drawer){

                  ;
                }


                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawer.setDrawerListener(actionBarDrawerToggle);


        Intent intent = getIntent();
        if (intent != null) {
            String groupHeader = intent.getStringExtra(Constant.NAVIGATION_GROUPHEADER);
            //String childHeader=intent.getStringExtra(Constant.NAVIGATION_CHILDHEADER);
            if (groupHeader.equals(Constant.NAVIGATION_DASHBOARD)) {
                displayDashBoardViews("Dashboard");
            }
            if (groupHeader.equals(Constant.NAVIGATION_INVENTORYMENU_ITEM)) {
                displayInventoryViews(Constant.NAVIGATION_INVENTORYMENU_ITEM);
            }
            if (groupHeader.equals(Constant.NAVIGATION_GROUP_PURCHASE)) {
                displayPurchaseViews("Purchases");
            }
            if (groupHeader.equals(Constant.NAVIGATION_GROUP_RETAIL)) {
                displaySalesViews("Sales");
            }

        } else {
            displayDashBoardViews("Dashboard");
        }

    }


    /*
     * Preparing the list data
         */
    private void prepareListData() {
        try {
            List<String> reposrtSubmenu = null, transactionSubMenu = null, inventorySubMneu = null, taxSubMneu, cPanelSubMneu, helpSubMneu;

            listDataHeader = new ArrayList<NavigationHeaderMenu>();
            listDataChild = new HashMap<String, List<String>>();

            String getPageLoadData = sharedPreference.getData(Constant.GETPAGELOADDATA_KEY);
            if (getPageLoadData !=null)
            {

                Gson gson = new Gson();
                pageData = gson.fromJson(getPageLoadData.toString(),PageLoadDataForAll.class);
                if (pageData != null){
                    if (pageData.getUserAccessRights().getDashboard() == true)
                    {
                        listDataHeader.add(new NavigationHeaderMenu(res.getString(R.string.navitemDashboard), res.getDrawable(R.drawable.ic_dashboard)));
                    }

                    if (pageData.getUserAccessRights().getSales() == true)
                    {

                        listDataHeader.add(new NavigationHeaderMenu(res.getString(R.string.navitemSales), res.getDrawable(R.drawable.ic_sales)));
                    }
                    if (pageData.getUserAccessRights().getPurchase() == true)
                    {
                        listDataHeader.add(new NavigationHeaderMenu(res.getString(R.string.navitemPurchases), res.getDrawable(R.drawable.ic_purchase)));
                    }

                    listDataHeader.add(new NavigationHeaderMenu(res.getString(R.string.navitemService), res.getDrawable(R.drawable.ic_generaltransactrion)));

                    if (pageData.getUserAccessRights().getGt() == true)
                    {
                        listDataHeader.add(new NavigationHeaderMenu(res.getString(R.string.navitemTransaction), res.getDrawable(R.drawable.ic_generaltransactrion)));
                    }
                    if (pageData.getUserAccessRights().getInventory() == true)
                    {
                        listDataHeader.add(new NavigationHeaderMenu(res.getString(R.string.navitemInventory), res.getDrawable(R.drawable.ic_inventory)));
                    }
                    if (pageData.getUserAccessRights().getManufacturing() == true)
                    {
                        listDataHeader.add(new NavigationHeaderMenu(res.getString(R.string.navitemManufacturing), res.getDrawable(R.drawable.ic_inventory)));

                    }
                    if (pageData.getUserAccessRights().getCrm() == true)
                    {
                        listDataHeader.add(new NavigationHeaderMenu(res.getString(R.string.navitemCRM), res.getDrawable(R.drawable.ic_sales)));

                    }
                    if (pageData.getUserAccessRights().getTax() == true)
                    {
                        listDataHeader.add(new NavigationHeaderMenu(res.getString(R.string.navitemTax), res.getDrawable(R.drawable.ic_tax36)));

                    }
                    if (pageData.getUserAccessRights().getControlPanel() == true)
                    {
                        listDataHeader.add(new NavigationHeaderMenu(res.getString(R.string.navitemControlPanel), res.getDrawable(R.drawable.ic_controlpanel)));

                    }
                    if (pageData.getUserAccessRights().getContact() == true)
                    {
                        listDataHeader.add(new NavigationHeaderMenu(res.getString(R.string.navitemContact), res.getDrawable(R.drawable.ic_contact)));


                    }
                    if (pageData.getUserAccessRights().getReport() == true)
                    {
                          listDataHeader.add(new NavigationHeaderMenu(res.getString(R.string.navitemReport), res.getDrawable(R.drawable.ic_report)));

                    }

                }

                // listDataHeader.add(new NavigationHeaderMenu(res.getString(R.string.navitemRestaurant), res.getDrawable(R.drawable.ic_generaltransactrion)));
                //  listDataHeader.add(new NavigationHeaderMenu(res.getString(R.string.navitemHelp), res.getDrawable(R.drawable.ic_help)));
                listDataHeader.add(new NavigationHeaderMenu(res.getString(R.string.navitemLogOut), res.getDrawable(R.drawable.ic_logout)));


            /*
            UtilView.showLogCat(TAG,"Header lsit sales status "+pageData.getUserAccessRights().getSales());

            UtilView.showLogCat(TAG,"Header lsit "+listDataHeader.size());*/
                //Adding child data
                // Static method

        /*hiposretail = Arrays.asList(res.getStringArray(R.array.elements_hiposretail));
        hipospurchase = Arrays.asList(res.getStringArray(R.array.elements_hipospurchase));
        hiposreport = Arrays.asList(res.getStringArray(R.array.elements_hiposreport));
*/
                // contactSubMenu = Arrays.asList(res.getStringArray(R.array.contactSubMenu));
                //taxSubMneu = Arrays.asList(res.getStringArray(R.array.taxSubMenu));


                List<String> contactSubMenu;



                // cPanelSubMneu = Arrays.asList(res.getStringArray(R.array.cPanelSubMenu));
                helpSubMneu = Arrays.asList(res.getStringArray(R.array.helpSubMenu));
                for (int i = 0; i < listDataHeader.size(); i++) {

                    if (listDataHeader.get(i).getMenuTitle().equals(res.getString(R.string.navitemTransaction))) {
                        transactionSubMenu = new ArrayList<>();
                        if (pageData.getUserAccessRights().getGtExpense() == true)
                        {
                            transactionSubMenu.add(res.getString(R.string.transactionPurchase));
                            listDataChild.put(listDataHeader.get(i).getMenuTitle(), transactionSubMenu); // Header, Child data
                        }
                        if (pageData.getUserAccessRights().getGtReciepts() == true)
                        {
                            transactionSubMenu.add(res.getString(R.string.transactionReceipt));
                            listDataChild.put(listDataHeader.get(i).getMenuTitle(), transactionSubMenu); // Header, Child data
                        }
                        if (pageData.getUserAccessRights().getGtJournalEntry() == true)
                        {
                            transactionSubMenu.add(res.getString(R.string.transactionJournalEntry));
                            listDataChild.put(listDataHeader.get(i).getMenuTitle(), transactionSubMenu); // Header, Child data
                        }

                        if (pageData.getUserAccessRights().getGtExpense() == false && pageData.getUserAccessRights().getGtReciepts() == false && pageData.getUserAccessRights().getGtJournalEntry() == false){

                            listDataChild.put(listDataHeader.get(i).getMenuTitle(), new ArrayList<String>());


                        }

                    }
                    else if (listDataHeader.get(i).getMenuTitle().equals(res.getString(R.string.navitemTax))) {
                        taxSubMneu = new ArrayList<>();

                        if (pageData.getUserAccessRights().getTaxTaxConfiguration() == true)
                        {
                            taxSubMneu.add(res.getString(R.string.taxTaxConfiguration));
                            listDataChild.put(listDataHeader.get(i).getMenuTitle(), taxSubMneu); // Header, Child data
                        }
                        if (pageData.getUserAccessRights().getTaxGST() == true)
                        {
                            taxSubMneu.add(res.getString(R.string.taxGST));
                            listDataChild.put(listDataHeader.get(i).getMenuTitle(), taxSubMneu); // Header, Child data
                        }

                        if (pageData.getUserAccessRights().getTaxTaxConfiguration() == false && pageData.getUserAccessRights().getTaxGST() == false ){
                            listDataChild.put(listDataHeader.get(i).getMenuTitle(), new ArrayList<String>());

                        }


                    } else if (listDataHeader.get(i).getMenuTitle().equals(res.getString(R.string.navitemControlPanel))) {
                        cPanelSubMneu = new ArrayList<>();
                        if (pageData.getUserAccessRights().getControlPanelOBofBS() == true)
                        {
                            cPanelSubMneu.add(res.getString(R.string.cPanelOpeningBalance));
                            listDataChild.put(listDataHeader.get(i).getMenuTitle(), cPanelSubMneu);
                        }
                        if (pageData.getUserAccessRights().getControlPanelConfigurator() == true)
                        {
                            cPanelSubMneu.add(res.getString(R.string.cPanelConfigurator));
                            listDataChild.put(listDataHeader.get(i).getMenuTitle(), cPanelSubMneu);
                        }
                        if (pageData.getUserAccessRights().getControlPanelCompanySetup() == true)
                        {
                            cPanelSubMneu.add(res.getString(R.string.cPanelCompnaySetup));
                            listDataChild.put(listDataHeader.get(i).getMenuTitle(), cPanelSubMneu);
                        }
                        if (pageData.getUserAccessRights().getControlPanelUAS() == true)
                        {
                            cPanelSubMneu.add(res.getString(R.string.cPanelUserAccountSetup));
                            listDataChild.put(listDataHeader.get(i).getMenuTitle(), cPanelSubMneu);
                        }
                        if (pageData.getUserAccessRights().getControlPanelAccountMaintenance() == true)
                        {
                            cPanelSubMneu.add(res.getString(R.string.cPanelAccountMaintenance));
                            listDataChild.put(listDataHeader.get(i).getMenuTitle(), cPanelSubMneu);
                        }
                        if (pageData.getUserAccessRights().getControlPanelDisplaySetup() == true)
                        {
                            cPanelSubMneu.add(res.getString(R.string.cPanelDisplaySetup));
                            listDataChild.put(listDataHeader.get(i).getMenuTitle(), cPanelSubMneu);
                        }

                        if (pageData.getUserAccessRights().getControlPanelOBofBS() == false &&
                                pageData.getUserAccessRights().getControlPanelConfigurator() == false &&
                                pageData.getUserAccessRights().getControlPanelCompanySetup() == false &&
                                pageData.getUserAccessRights().getControlPanelUAS() == false &&
                                pageData.getUserAccessRights().getControlPanelAccountMaintenance() == false &&
                                pageData.getUserAccessRights().getControlPanelDisplaySetup() == false){

                            listDataChild.put(listDataHeader.get(i).getMenuTitle(), new ArrayList<String>());
                        }

                        // Header, Child data
                    }  else if (listDataHeader.get(i).getMenuTitle().equals(res.getString(R.string.navitemContact))) {
                        contactSubMenu = new ArrayList<>();

                        if (pageData.getUserAccessRights().getContactSupplier() == true)
                        {
                            contactSubMenu.add(res.getString(R.string.contactSupplier));
                            listDataChild.put(listDataHeader.get(i).getMenuTitle(), contactSubMenu); // Header, Child data

                        }
                        if (pageData.getUserAccessRights().getContactContacts() == true)
                        {
                            contactSubMenu.add(res.getString(R.string.contactContacts));
                            listDataChild.put(listDataHeader.get(i).getMenuTitle(), contactSubMenu); // Header, Child data

                        }
                        if (pageData.getUserAccessRights().getContactCustomer() == true)
                        {
                            contactSubMenu.add(res.getString(R.string.contactCusotmer));
                            listDataChild.put(listDataHeader.get(i).getMenuTitle(), contactSubMenu); // Header, Child data

                        }
                        if (pageData.getUserAccessRights().getContactSupplier() == false && pageData.getUserAccessRights().getContactContacts() == false && pageData.getUserAccessRights().getContactContacts() == false){
                            listDataChild.put(listDataHeader.get(i).getMenuTitle(), new ArrayList<String>());
                        }
                    }else if (listDataHeader.get(i).getMenuTitle().equals(res.getString(R.string.navitemReport))) {
                        reposrtSubmenu = new ArrayList<>();
                        if (pageData.getUserAccessRights().getReportPurchase() == true)
                        {
                            reposrtSubmenu.add(res.getString(R.string.reportsPurchase));
                            listDataChild.put(listDataHeader.get(i).getMenuTitle(), reposrtSubmenu); // Header, Child data

                        }
                        if (pageData.getUserAccessRights().getReportSale() == true)
                        {
                            reposrtSubmenu.add(res.getString(R.string.reportsSales));
                            listDataChild.put(listDataHeader.get(i).getMenuTitle(), reposrtSubmenu); // Header, Child data

                        }
                        if (pageData.getUserAccessRights().getRestaurant() == true)
                        {
                            reposrtSubmenu.add(res.getString(R.string.reportsRestaurant));
                            listDataChild.put(listDataHeader.get(i).getMenuTitle(), reposrtSubmenu); // Header, Child data

                        }
                        if (pageData.getUserAccessRights().getInventory() == true)
                        {
                            reposrtSubmenu.add(res.getString(R.string.reportsInventory));
                            listDataChild.put(listDataHeader.get(i).getMenuTitle(), reposrtSubmenu); // Header, Child data

                        }
                        if (pageData.getUserAccessRights().getReportAnalysis() == true)
                        {
                            reposrtSubmenu.add(res.getString(R.string.reportsAnalysis));
                            listDataChild.put(listDataHeader.get(i).getMenuTitle(), reposrtSubmenu); // Header, Child data

                        }
                        if (pageData.getUserAccessRights().getReportFinancialStatement() == true)
                        {
                            reposrtSubmenu.add(res.getString(R.string.reportsFinancial));
                            listDataChild.put(listDataHeader.get(i).getMenuTitle(), reposrtSubmenu); // Header, Child data

                        }
                        if (pageData.getUserAccessRights().getReportBankRecon() == true)
                        {
                            reposrtSubmenu.add(res.getString(R.string.reportsBank));
                            listDataChild.put(listDataHeader.get(i).getMenuTitle(), reposrtSubmenu); // Header, Child data

                        }
                       // listDataChild.put(listDataHeader.get(i).getMenuTitle(), reposrtSubmenu); // Header, Child data
                    }else {
                        listDataChild.put(listDataHeader.get(i).getMenuTitle(), new ArrayList<String>());
                    }


                    if (listDataHeader.get(i).getMenuTitle().equals(res.getString(R.string.navitemHelp))) {
                        listDataChild.put(listDataHeader.get(i).getMenuTitle(), helpSubMneu); // Header, Child data
                    }




                }
                adapter=new NavigationMenu_Adapter(this, listDataHeader, listDataChild);
                drawerList.setAdapter(adapter);
            }
        }catch (Exception e){
            Log.e("Pageloadata",e.toString());
        }



    }


    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {


        if (listDataHeader.get(groupPosition).getMenuTitle().equals(res.getString(R.string.navitemReport))) {
            displayReportViews(listDataChild.get(listDataHeader.get(groupPosition).getMenuTitle()).get(childPosition));
        }
        if (listDataHeader.get(groupPosition).getMenuTitle().equals(res.getString(R.string.navitemTransaction))) {
            displayGeneralTransactionViews(listDataChild.get(listDataHeader.get(groupPosition).getMenuTitle()).get(childPosition));
        }
        if (listDataHeader.get(groupPosition).getMenuTitle().equals(res.getString(R.string.navitemInventory))) {
            displayInventoryViews(listDataChild.get(listDataHeader.get(groupPosition).getMenuTitle()).get(childPosition));
        }
        if (listDataHeader.get(groupPosition).getMenuTitle().equals(res.getString(R.string.navitemTax))) {
            displayTaxViews(listDataChild.get(listDataHeader.get(groupPosition).getMenuTitle()).get(childPosition));
        }
        if (listDataHeader.get(groupPosition).getMenuTitle().equals(res.getString(R.string.navitemControlPanel))) {
            displayControlPanelViews(listDataChild.get(listDataHeader.get(groupPosition).getMenuTitle()).get(childPosition));
        }
        if (listDataHeader.get(groupPosition).getMenuTitle().equals(res.getString(R.string.navitemHelp))) {
            displayHelpViews(listDataChild.get(listDataHeader.get(groupPosition).getMenuTitle()).get(childPosition));
        }
        if (listDataHeader.get(groupPosition).getMenuTitle().equals(res.getString(R.string.navitemContact))) {
            displayContactViews(listDataChild.get(listDataHeader.get(groupPosition).getMenuTitle()).get(childPosition));
        }


        return false;
    }


    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);

        if (listDataChild.get(listDataHeader.get(groupPosition).getMenuTitle()).size() > 0) {

        } else {
            switch (listDataHeader.get(groupPosition).getMenuTitle()) {

                case "Dashboard":
                    drawer.closeDrawer(GravityCompat.START);
                    fragment = new Fragment_DashBoard();
                    title = "Dashboard";
                    break;
                case "Sales":
                    drawer.closeDrawer(GravityCompat.START);
                    fragment = new Fragment_Sales();
                    title = "Sales";
                    break;
                case "Purchases":
                    drawer.closeDrawer(GravityCompat.START);
                    fragment = new Fragment_Purchase();
                    title = "Purchase";
                    break;
                case "Service":
                    drawer.closeDrawer(GravityCompat.START);
                    fragment = new Fragment_Service();
                    title = "Service";
                    break;
                case "Restaurant":
                    drawer.closeDrawer(GravityCompat.START);
                    fragment = new Fragment_Restaurant();
                    title = "Restaurant";
                    break;

                case "Inventory":
                    drawer.closeDrawer(GravityCompat.START);
                    fragment = new Fragment_Inventory();
                    title = "Inventory";
                    break;

                case "Manufacturing":
                    drawer.closeDrawer(GravityCompat.START);
                    fragment = new Fragment_Manufacturing();
                    title = "Manufacturing";
                    break;

                case "CRM":
                    drawer.closeDrawer(GravityCompat.START);
                    fragment = new Fragment_Crm();
                    title = "CRM";
                    break;
                case "Tax":
                    drawer.closeDrawer(GravityCompat.START);
                    if (listDataHeader.get(groupPosition).getMenuTitle().equals("Tax"))
                    {
                        Toast.makeText(mActivity, "Permission Denyied", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case "Contact":
                    drawer.closeDrawer(GravityCompat.START);
                    if (listDataHeader.get(groupPosition).getMenuTitle().equals("Contact"))
                    {
                        Toast.makeText(mActivity, "Permission Denyied", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case "General Transaction":
                    drawer.closeDrawer(GravityCompat.START);
                    if (listDataHeader.get(groupPosition).getMenuTitle().equals("General Transaction"))
                    {
                        Toast.makeText(mActivity, "Permission Denyied", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case "Control Panel":
                    drawer.closeDrawer(GravityCompat.START);
                    if (listDataHeader.get(groupPosition).getMenuTitle().equals("Control Panel"))
                    {
                        Toast.makeText(mActivity, "Permission Denyied", Toast.LENGTH_SHORT).show();
                    }
                    break;


                case "Log Out":
                    drawer.closeDrawer(GravityCompat.START);
                    logout();
                    break;


            }
            if (fragment != null) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_layout, fragment);
                ft.commit();
            }

            // set the toolbar title
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(title);
            }


            drawer.closeDrawer(GravityCompat.START);
        }


        return false;

    }


    public void displayPurchaseViews(String purchaseMenu) {

        Fragment fragment = null;
        String title = getString(R.string.app_name);

        switch (purchaseMenu) {
            case "Purchases":
                fragment = new Fragment_Purchase();
                title = "Purchase";
                break;

            default:
                break;

        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_layout, fragment);
            ft.commit();
        }

        // set the toolbar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }


        drawer.closeDrawer(GravityCompat.START);

    }

    public void displaySalesViews(String salesMenu) {

        Fragment fragment = null;
        String title = getString(R.string.app_name);

        switch (salesMenu) {
            case "Sales":
               // fragment = new Fragment_Floatingbtn();
                fragment = new Fragment_Sales();

                title = "Sales";
                break;

            default:
                break;

        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_layout, fragment);
            ft.commit();
        }

        // set the toolbar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }


        drawer.closeDrawer(GravityCompat.START);

    }

    public void displayDashBoardViews(String dashBoardMenu) {

        Fragment fragment = null;
        String title = getString(R.string.app_name);

        switch (dashBoardMenu) {
            case "Dashboard":
                fragment = new Fragment_DashBoard();
                title = "Dashboard";
                break;

            default:
                break;

        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_layout, fragment);
            ft.commit();
        }

        // set the toolbar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }


        drawer.closeDrawer(GravityCompat.START);

    }

    public void displayReportViews(String hiposReportMenus) {

        Fragment fragment = null;
        String title = getString(R.string.app_name);

        switch (hiposReportMenus) {

            case "Purchase":
                fragment = new Fragment_ReportPurchase();
                title = "Purchase";
                break;

            case "Sales":
                fragment = new Fragment_ReportSales();
                title = "Sales";
                break;
            case "Inventory":
                fragment = new Fragment_ReportInventory();
                title = "Inventory";
                break;
            case "Restaurant":
                fragment = new Fragment_ReportRestaurent();
                title = "Restaurant";
                break;
            case "Analysis":
                fragment = new Fragment_ReportAnalysis();
                title = "Analysis";
                break;
            case "Financial Statement":
                fragment = new Fragment_ReportFinancialStatement();
                title = "Financial Statement";
                break;

            case "Bank Reconciliation":
                fragment = new Fragment_ReportBankReconciliation();
                title = "Bank Reconciliation";
                break;
            default:
                break;

        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_layout, fragment);
            ft.commit();
        }

        // set the toolbar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }


        drawer.closeDrawer(GravityCompat.START);

    }

    public void displayGeneralTransactionViews(String transactionMenus) {

        Fragment fragment = null;
        String title = getString(R.string.app_name);

        switch (transactionMenus) {

            case "Expenses":
                fragment = new Fragment_TransactionExpenses();
                title = "Expenses";
                break;

            case "Receipt":
                fragment = new Fragment_TransactionReceipt();
                title = "Receipt";
                break;
            case "Journal Entry":
                fragment = new Fragment_TransactionJournalEntry();
                title = "Journal Entry";

            default:
                break;

        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_layout, fragment);
            ft.commit();
        }

        // set the toolbar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }


        drawer.closeDrawer(GravityCompat.START);

    }

    public void displayInventoryViews(String inventroyMenus) {

        Fragment fragment = null;
        String title = getString(R.string.app_name);

        switch (inventroyMenus) {

            case "Item":
                fragment = new Fragment_InventoryItem();
                title = "Item";
                break;

            case "Category":
                fragment = new Fragment_InventoryCategory();
                title = "Category";
                break;
            case "Brand":
                fragment = new Fragment_InventoryBrand();
                title = "Brand";
                break;
            case "UOM":
                fragment = new Fragment_InventoryUOM();
                title = "UOM";
                break;
            case "Sales Pricing":
                fragment = new Fragment_InventorySalesPricing();
                title = "Sales Pricing";
                break;
            case "Sales Discount Configuration":
                fragment = new Fragment_InventorySalesDiscountConfig();
                title = "Sales Discount Configuration";
                break;
            case "Purchase Pricing":
                fragment = new Fragment_InventoryPurchasePricing();
                title = "Purchase Pricing";
                break;
            case "Inventory Location":
                fragment = new Fragment_InventoryLocation();
                title = "Inventory Location";
                break;
            case "Inventory Movement Type":
                fragment = new Fragment_InventoryCountType();
                title = ">Inventory Movement Type";
                break;

            default:
                break;

        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_layout, fragment);
            ft.commit();
        }

        // set the toolbar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }


        drawer.closeDrawer(GravityCompat.START);

    }


    public void displayTaxViews(String taxMenus) {

        Fragment fragment = null;
        String title = getString(R.string.app_name);

        switch (taxMenus) {
            case "Tax Configuration":
                fragment = new Fragment_TaxConfiguration();
                title = "Tax Configuration";
                break;


            case "GST":
                fragment = new Fragment_GST();
                title = "GST";
                break;
            default:
                break;
        }
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_layout, fragment);
            ft.commit();
        }

        // set the toolbar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }


        drawer.closeDrawer(GravityCompat.START);

    }

    public void displayContactViews(String contactSubmenu) {

        String title = getString(R.string.app_name);
        Intent intent = null;

        switch (contactSubmenu) {

            case "Supplier":
                intent = new Intent(this, Activity_Supplier.class);
                intent.putExtra("callingFrom", Constant.NAVIGATION_GROUP_CONTACT);
                startActivity(intent);

                break;

            case "Customer":
                intent = new Intent(this, Activity_Customer.class);
                intent.putExtra("callingFrom", Constant.NAVIGATION_GROUP_CONTACT);
                startActivity(intent);

                break;

            case "Contacts":
                intent = new Intent(this, Activity_Contact.class);
                intent.putExtra("callingFrom", Constant.NAVIGATION_GROUP_CONTACT);
                startActivity(intent);

                break;

        }
        drawer.closeDrawer(GravityCompat.START);
    }

    public void displayHelpViews(String helpMenus) {

        Fragment fragment = null;
        String title = getString(R.string.app_name);

        switch (helpMenus) {

            case "Help Manual":
                fragment = new Fragment_HelpManual();
                title = "Help Manual";
                break;

            case "Help Video":
                fragment = new Fragment_HelpVideo();
                title = "Help Video";
                break;

            case "Support":
                fragment = new Fragment_HelpSupport();
                title = "Support";
                break;

            case "Subcription":
                fragment = new Fragment_HelpSubscription();
                title = "Subcription";
                break;

            default:
                break;
        }
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_layout, fragment);
            ft.commit();
        }

        // set the toolbar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }


        drawer.closeDrawer(GravityCompat.START);

    }

    public void displayControlPanelViews(String cPanelMenus) {

        Fragment fragment = null;
        String title = getString(R.string.app_name);

        switch (cPanelMenus) {

            case "Opening Balance":
                fragment = new Fragment_OpeningBalance();
                title = "Opening Balance";
                break;

            case "Configurator":
                fragment = new Fragment_Configurator();
                title = "Configurator";
                break;

            case "Company Setup":
                fragment = new Fragment_CompanySetup();
                title = "Company Setup";
                break;

            case "User Account Setup":
                fragment = new Fragment_UserAccountSetup();
                title = "User Account Setup";
                break;
            case "Account Maintenance":
                fragment = new Fragment_AccountMaintenance();
                title = "Account Maintenance";
                break;
            case "Display setup":
                fragment = new Fragment_DisplaySetup();
                title = "Display setup";
                break;


            default:
                break;
        }
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_layout, fragment);
            ft.commit();
        }

        // set the toolbar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }


        drawer.closeDrawer(GravityCompat.START);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        for (Fragment fragment : getSupportFragmentManager().getFragments()) {

            if (fragment instanceof Fragment_TransactionExpenses) {
                fragment.onActivityResult(requestCode, resultCode, data);
            }
            if (fragment instanceof Fragment_TransactionReceipt) {
                fragment.onActivityResult(requestCode, resultCode, data);
            }
            if (fragment instanceof Fragment_Sales) {
                fragment.onActivityResult(requestCode, resultCode, data);
            }
            if (fragment instanceof Fragment_Purchase) {
                fragment.onActivityResult(requestCode, resultCode, data);
            }

            if (fragment instanceof Fragment_Restaurant) {
                fragment.onActivityResult(requestCode, resultCode, data);
            }

        }

    }

    public void logout() {
        /*PosCreator posCreator = new PosCreator();
        posCreator.clear();
        sharedPreference.setRemovePrefrence(Constant.LOGINDATA_KEY);
        sharedPreference.setRemovePrefrence(Constant.COMPANYDETAIL);
        sharedPreference.setRemovePrefrence(Constant.ACCESSTOKEN);
        sharedPreference.setRemovePrefrence(Constant.USERNAME);
        Intent intent = new Intent(mActivity, Activity_Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();*/
        isInternetPresent = serviceHandler.isConnectingToInternet();
        if (isInternetPresent) {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put(Constant.ACCESSTOKEN, sharedPreference.getData(Constant.ACCESSTOKEN));
                UtilView.showLogCat(TAG, sharedPreference.getData(Constant.ACCESSTOKEN));

                String url=UtilView.getUrl(mActivity);
                if (url!=null){

                    UtilView.showProgessBar(mActivity, progress_bar);
                    PostDataTask postDataTask = new PostDataTask(mActivity, new AsyncTaskCompleteListener<String>() {
                        @Override
                        public void onTaskComplete(String result) {
                            UtilView.hideProgessBar(mActivity, progress_bar);
                            if (result != null) {
                                if (result.equals("invalid")) {
                                    UtilView.showToast(mActivity, getResources().getString(R.string.accesstoken_error));
                                    Intent intent = new Intent(mActivity, Activity_Login.class);
                                    startActivity(intent);
                                    mActivity.finish();
                                } else {
                                    try {
                                        JSONObject jsonObject = new JSONObject(result.toString());
                                        if (jsonObject.getString("message").equals("success")) {
                                            UtilView.gotToLogin(mActivity);
                                        } else {
                                            UtilView.showToast(mActivity, "Some error in logout.");
                                        }
                                    } catch (Exception e) {
                                        UtilView.showLogCat(TAG, "DataTaskListener Exception " + e.toString());
                                    }
                                }
                            } else {
                                UtilView.showErrorDialog(getResources().getString(R.string.error_null), mActivity);
                            }
                        }

                    }, false);
                    postDataTask.execute(jsonObject.toString(), url+"/hipos/"+Constant.FUNTION_LOGOUT, "");

                }else {
                    UtilView.gotToLogin(mActivity);
                }
            } catch (Exception e) {

            }
        } else {

            UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), mActivity);
        }

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        builder.setMessage("Do you want to Exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    finishAffinity();
                }
                System.exit(0);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }




    public void openRightDrawer(String moduleType){
        if (rightDrawerList!=null) {
            drawer.openDrawer(Gravity.RIGHT);
            NavigationMenu_Adapter adapter=new NavigationMenu_Adapter(this, moduleType);
            rightDrawerList.setAdapter(adapter);
            listDataChildRight=adapter.get_listDataChild();
            listDataHeaderRight=adapter.get_listDataHeader();

        }
    }


    public void hideRightDrawer(){
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
    }

    @Override
    protected void onResume() {
        super.onResume();
        prepareListData();

    }
}
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.retail_pos, menu);
        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){

            case R.id.chengeSetting:
                sharedPreference.setEmptyPrefrence();
                Intent intent=new Intent(this,Activity_ServerConfiguration.class);
                startActivity(intent);
                finish();
                break;


            case R.id.delete_items:
                item.setVisible(false);
                break;

            case R.id.save_order:
                item.setVisible(false);
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);

    }*/

