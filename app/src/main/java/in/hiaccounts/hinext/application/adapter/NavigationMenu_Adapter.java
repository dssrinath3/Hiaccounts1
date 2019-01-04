package in.hiaccounts.hinext.application.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import in.hiaccounts.R;
import in.hiaccounts.hinext.application.model.PageLoadDataForAll;
import in.hiaccounts.model.NavigationHeaderMenu;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 4/25/2017.
 */

public class NavigationMenu_Adapter extends BaseExpandableListAdapter {


    public LayoutInflater minflater;
    private SharedPreference sharedPreference;
    private Context _context;
    private Resources res;
    private Activity activity;
    private List<NavigationHeaderMenu> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;
    private PageLoadDataForAll pageData;

    public List<NavigationHeaderMenu> get_listDataHeader() {
        return _listDataHeader;
    }

    public void set_listDataHeader(List<NavigationHeaderMenu> _listDataHeader) {
        this._listDataHeader = _listDataHeader;
    }

    public HashMap<String, List<String>> get_listDataChild() {
        return _listDataChild;
    }

    public void set_listDataChild(HashMap<String, List<String>> _listDataChild) {
        this._listDataChild = _listDataChild;
    }

    public NavigationMenu_Adapter(Context context, List<NavigationHeaderMenu> listDataHeader, HashMap<String, List<String>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;

    }

    public NavigationMenu_Adapter(Context context, String moduleType) {
        this._context = context;
        activity = (Activity) context;
        sharedPreference = SharedPreference.getInstance(_context);
        res = context.getResources();
        prepareRightDrawerListData(moduleType);

    }


    public void setInflater(LayoutInflater mInflater, Activity act) {
        this.minflater = mInflater;
    }

    /**/
    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition).getMenuTitle()).get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.adapter_navigationmenu_child, null);
        }

        TextView txtListChild = convertView
                .findViewById(R.id.lblListItem);

        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition).getMenuTitle()).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {


        NavigationHeaderMenu groupMenu = (NavigationHeaderMenu) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.adapter_navigationmenu_group, null);
        }

        ImageView imageViewMenu = convertView.findViewById(R.id.imgviewMenuGroup);
        imageViewMenu.setImageDrawable(groupMenu.getMenuResId());

        TextView lblListHeader = convertView.findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(groupMenu.getMenuTitle());

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    public void prepareRightDrawerListData(String moduleType) {



        _listDataHeader = new ArrayList<NavigationHeaderMenu>();
        _listDataChild = new HashMap<String, List<String>>();


        //List<String> paymentSubMenu = new ArrayList<String>();
        List<String> recieveItemSubMenu = new ArrayList<String>();
        List<String> orderItemSubMenu = new ArrayList<String>();
        //List<String> quotationItemSubMenu = new ArrayList<String>();
        List<String> printlistItemSubMenu = new ArrayList<String>();
        List<String> proFormaItemSubMenu = new ArrayList<String>();
        List<String> deliveryOrderItemSubMenu = new ArrayList<String>();
        //List<String> salesPrintlistItemSubMenu = new ArrayList<String>();
        List<String> draftsItemSubMenu = new ArrayList<String>();

        String getPageLoadData = sharedPreference.getData(Constant.GETPAGELOADDATA_KEY);
        if (getPageLoadData !=null)
        {
                    Gson gson = new Gson();
                    pageData = gson.fromJson(getPageLoadData,PageLoadDataForAll.class);
                    if (pageData != null){
                        if (moduleType.equals(Constant.MODULE_SALES)) {

                    _listDataHeader.add(new NavigationHeaderMenu(_context.getResources().getString(R.string.module_menupayment), _context.getResources().getDrawable(R.drawable.ic_payment1)));
                    _listDataHeader.add(new NavigationHeaderMenu(_context.getResources().getString(R.string.module_menusalesorder), _context.getResources().getDrawable(R.drawable.ic_sales_order)));
                    _listDataHeader.add(new NavigationHeaderMenu(_context.getResources().getString(R.string.module_menuquotation), _context.getResources().getDrawable(R.drawable.ic_quotation)));
                    _listDataHeader.add(new NavigationHeaderMenu(_context.getResources().getString(R.string.module_menuproforma), _context.getResources().getDrawable(R.drawable.ic_proforma)));
                    _listDataHeader.add(new NavigationHeaderMenu(_context.getResources().getString(R.string.module_menudeliveryorder), _context.getResources().getDrawable(R.drawable.ic_delivery)));
                    _listDataHeader.add(new NavigationHeaderMenu(_context.getResources().getString(R.string.module_menuloandeliveryorder), _context.getResources().getDrawable(R.drawable.ic_loan_delivery)));
                    _listDataHeader.add(new NavigationHeaderMenu(_context.getResources().getString(R.string.module_menudrafts), _context.getResources().getDrawable(R.drawable.ic_draft)));

                    if (pageData.getUserAccessRights().getSalesPrintList() == true)
                    {
                        _listDataHeader.add(new NavigationHeaderMenu(_context.getResources().getString(R.string.module_menuprintlist), _context.getResources().getDrawable(R.drawable.ic_printlist)));
                    }
                    if (pageData.getUserAccessRights().getSalesDebitNote() == true)
                    {
                        _listDataHeader.add(new NavigationHeaderMenu(_context.getResources().getString(R.string.module_menudebitnote), _context.getResources().getDrawable(R.drawable.ic_debit_note)));
                    }

                        _listDataHeader.add(new NavigationHeaderMenu(_context.getResources().getString(R.string.module_menucreditnote), _context.getResources().getDrawable(R.drawable.ic_credit_note)));

                    if (pageData.getUserAccessRights().getDraftInvoice() == true)
                    {
                        _listDataHeader.add(new NavigationHeaderMenu(_context.getResources().getString(R.string.module_menudraftinvoice), _context.getResources().getDrawable(R.drawable.ic_inventory)));
                    }


                    _listDataHeader.add(new NavigationHeaderMenu(_context.getResources().getString(R.string.module_menureturnitem), _context.getResources().getDrawable(R.drawable.ic_inventory)));
                    _listDataHeader.add(new NavigationHeaderMenu(_context.getResources().getString(R.string.module_menuexportinvoice), _context.getResources().getDrawable(R.drawable.ic_inventory)));


                    if (pageData.getUserAccessRights().getSalesClearAll() == true)
                    {
                        _listDataHeader.add(new NavigationHeaderMenu(_context.getResources().getString(R.string.module_menugtclearall), _context.getResources().getDrawable(R.drawable.ic_inventory)));
                    }

                    //paymentSubMenu = Arrays.asList(_context.getResources().getStringArray(R.array.module_sales_submenupayment));
                    //orderItemSubMenu = Arrays.asList(_context.getResources().getStringArray(R.array.module_submenuorder));
                   // quotationItemSubMenu = Arrays.asList(_context.getResources().getStringArray(R.array.module_submenuquotation));
                    proFormaItemSubMenu = Arrays.asList(_context.getResources().getStringArray(R.array.module_submenuproforma));
                    deliveryOrderItemSubMenu = Arrays.asList(_context.getResources().getStringArray(R.array.module_purchase_submenudeliveryorder));
                    draftsItemSubMenu = Arrays.asList(_context.getResources().getStringArray(R.array.module_purchase_submenudrafts));
                    printlistItemSubMenu = Arrays.asList(_context.getResources().getStringArray(R.array.module_sales_submenuprintlist));
                }

                if (moduleType.equals(Constant.MODULE_PURCHASE)) {
                    _listDataHeader.add(new NavigationHeaderMenu(_context.getResources().getString(R.string.module_menupayment), _context.getResources().getDrawable(R.drawable.ic_payment1)));
                    _listDataHeader.add(new NavigationHeaderMenu(_context.getResources().getString(R.string.module_menurecievitem), _context.getResources().getDrawable(R.drawable.ic_inventory)));
                    _listDataHeader.add(new NavigationHeaderMenu(_context.getResources().getString(R.string.module_menupurchaseorder), _context.getResources().getDrawable(R.drawable.ic_inventory)));
                    _listDataHeader.add(new NavigationHeaderMenu(_context.getResources().getString(R.string.module_menuquotation), _context.getResources().getDrawable(R.drawable.ic_quotation)));

                    if (pageData.getUserAccessRights().getPurchasePrintList() == true)
                    {
                        _listDataHeader.add(new NavigationHeaderMenu(_context.getResources().getString(R.string.module_menuprintlist), _context.getResources().getDrawable(R.drawable.ic_printlist)));
                    }
                    if (pageData.getUserAccessRights().getPurCreditNote() == true)
                    {
                        _listDataHeader.add(new NavigationHeaderMenu(_context.getResources().getString(R.string.module_menucreditnote), _context.getResources().getDrawable(R.drawable.ic_credit_note)));
                    }
                    _listDataHeader.add(new NavigationHeaderMenu(_context.getResources().getString(R.string.module_menudebitnote), _context.getResources().getDrawable(R.drawable.ic_debit_note)));

                    if (pageData.getUserAccessRights().getPurchaseReturn() == true)
                    {
                        _listDataHeader.add(new NavigationHeaderMenu(_context.getResources().getString(R.string.module_menupiretrurnitem), _context.getResources().getDrawable(R.drawable.ic_inventory)));
                    }
                    if (pageData.getUserAccessRights().getPurchaseClearAll() == true)
                    {
                        _listDataHeader.add(new NavigationHeaderMenu(_context.getResources().getString(R.string.module_menugtclearall), _context.getResources().getDrawable(R.drawable.ic_inventory)));
                    }


                    //paymentSubMenu = Arrays.asList(_context.getResources().getStringArray(R.array.module_purchase_submenupayment));
                    recieveItemSubMenu = Arrays.asList(_context.getResources().getStringArray(R.array.module_purchase_submenurecieveitem));
                    //orderItemSubMenu = Arrays.asList(_context.getResources().getStringArray(R.array.module_submenuorder));
                  //  quotationItemSubMenu = Arrays.asList(_context.getResources().getStringArray(R.array.module_submenuquotation));
                    printlistItemSubMenu = Arrays.asList(_context.getResources().getStringArray(R.array.module_purchase_submenuprintlist));


                }

                if (moduleType.equals(Constant.MODULE_GENERAL_TRANSACTION)) {
                    _listDataHeader.add(new NavigationHeaderMenu(_context.getResources().getString(R.string.module_menugtprinlist), _context.getResources().getDrawable(R.drawable.ic_inventory)));
                    _listDataHeader.add(new NavigationHeaderMenu(_context.getResources().getString(R.string.module_menugtclearall), _context.getResources().getDrawable(R.drawable.ic_inventory)));


                }

                if (moduleType.equals(Constant.MODULE_GENERAL_TRANSACTION_JOURNAL)) {
                    _listDataHeader.add(new NavigationHeaderMenu(_context.getResources().getString(R.string.module_menugtprinlist), _context.getResources().getDrawable(R.drawable.ic_inventory)));


                }
                if (moduleType.equals(Constant.MODULE_SERVICE)) {
                    _listDataHeader.add(new NavigationHeaderMenu(_context.getResources().getString(R.string.module_menuselectinvoice), _context.getResources().getDrawable(R.drawable.ic_inventory)));
                    _listDataHeader.add(new NavigationHeaderMenu(_context.getResources().getString(R.string.module_menugtprinlist), _context.getResources().getDrawable(R.drawable.ic_inventory)));
                    _listDataHeader.add(new NavigationHeaderMenu(_context.getResources().getString(R.string.module_menurepair), _context.getResources().getDrawable(R.drawable.ic_inventory)));
                    _listDataHeader.add(new NavigationHeaderMenu(_context.getResources().getString(R.string.module_menudraftrepair), _context.getResources().getDrawable(R.drawable.ic_inventory)));
                    _listDataHeader.add(new NavigationHeaderMenu(_context.getResources().getString(R.string.module_menureplace), _context.getResources().getDrawable(R.drawable.ic_inventory)));
                    _listDataHeader.add(new NavigationHeaderMenu(_context.getResources().getString(R.string.module_menudraftreplace), _context.getResources().getDrawable(R.drawable.ic_inventory)));

                }

                for (int i = 0; i < _listDataHeader.size(); i++) {
                    List<String> salesRecievePayment;
                    List<String> salesSaveQuotation;
                    List<String> purchaseSaveQuotation;
                    List<String> salesOrder = new ArrayList<>();
                    List<String> purchaseOrder = new ArrayList<>();
                    List<String> salesProforma;
                    List<String> salesDeliverOrder;
                    List<String> salesDraft;
                    List<String> servicePrintList;

                    List<String> repair = new ArrayList<>();

                    if (_listDataHeader.get(i).getMenuTitle().equals(_context.getResources().getString(R.string.module_menupayment))) {
                        salesRecievePayment = new ArrayList<>();

                        if (moduleType.equals(Constant.MODULE_SALES)){
                                if (pageData.getUserAccessRights().getSalesRecievePayment() == true) {
                                    salesRecievePayment.add(res.getString(R.string.recievePayment));
                                    _listDataChild.put(_listDataHeader.get(i).getMenuTitle(), salesRecievePayment); // Header, Child data
                                }
                                if (pageData.getUserAccessRights().getSalesAdvancePayment() == true) {
                                    salesRecievePayment.add(res.getString(R.string.advacnePayment));
                                    _listDataChild.put(_listDataHeader.get(i).getMenuTitle(), salesRecievePayment); // Header, Child data
                                }
                               if (pageData.getUserAccessRights().getSalesRecievePayment() == false && pageData.getUserAccessRights().getSalesAdvancePayment() == false)
                               {
                                   _listDataChild.put(_listDataHeader.get(i).getMenuTitle(), new ArrayList<String>());
                               }

                           /* if (pageData.getUserAccessRights().getSalesPrintList() == true) {
                                salesRecievePayment.add(res.getString(R.string.module_menuprintlist));
                                _listDataChild.put(_listDataHeader.get(i).getMenuTitle(), salesRecievePayment); // Header, Child data
                            }*/

                        }

                        if (moduleType.equals(Constant.MODULE_PURCHASE)){
                            if (pageData.getUserAccessRights().getPurchaseSupplierPayment() == true) {
                                salesRecievePayment.add(res.getString(R.string.supplierPayment));
                                _listDataChild.put(_listDataHeader.get(i).getMenuTitle(), salesRecievePayment); // Header, Child data
                            }
                             if (pageData.getUserAccessRights().getPurchaseAdvancePayment() == true) {
                                 salesRecievePayment.add(res.getString(R.string.advacnePayment));
                                _listDataChild.put(_listDataHeader.get(i).getMenuTitle(), salesRecievePayment); // Header, Child data
                            }
                            if (pageData.getUserAccessRights().getPurchaseSupplierPayment() == false && pageData.getUserAccessRights().getPurchaseAdvancePayment() == false)
                            {
                                _listDataChild.put(_listDataHeader.get(i).getMenuTitle(), new ArrayList<String>());
                            }
                        }



                    } else if (_listDataHeader.get(i).getMenuTitle().equals(_context.getResources().getString(R.string.module_menusalesorder))) {
                        if (pageData.getUserAccessRights().getSalesSaveSalesOrder() == true) {
                            salesOrder.add(res.getString(R.string.orderSave));
                            _listDataChild.put(_listDataHeader.get(i).getMenuTitle(), salesOrder); // Header, Child data

                        }
                        if (pageData.getUserAccessRights().getSalesInvokeSalesOrder() == true) {
                            salesOrder.add(res.getString(R.string.orderInvoke));
                            _listDataChild.put(_listDataHeader.get(i).getMenuTitle(), salesOrder); // Header, Child data

                        }
                        if (pageData.getUserAccessRights().getSalesSaveSalesOrder() == false && pageData.getUserAccessRights().getSalesInvokeSalesOrder() == false)
                        {
                            _listDataChild.put(_listDataHeader.get(i).getMenuTitle(), new ArrayList<String>());
                        }

                    } else if (_listDataHeader.get(i).getMenuTitle().equals(_context.getResources().getString(R.string.module_menupurchaseorder))) {

                        if (pageData.getUserAccessRights().getPurchaseSavePurchaseOrder() == true) {
                            purchaseOrder.add(res.getString(R.string.orderSave));
                            _listDataChild.put(_listDataHeader.get(i).getMenuTitle(), purchaseOrder); // Header, Child data

                        }
                        if (pageData.getUserAccessRights().getPurchaseInvokePurchaseOrder() == true) {
                            purchaseOrder.add(res.getString(R.string.orderInvoke));
                            _listDataChild.put(_listDataHeader.get(i).getMenuTitle(), purchaseOrder); // Header, Child data

                        }

                        if (pageData.getUserAccessRights().getPurchaseSavePurchaseOrder() == false && pageData.getUserAccessRights().getPurchaseInvokePurchaseOrder() == false)
                        {
                            _listDataChild.put(_listDataHeader.get(i).getMenuTitle(), new ArrayList<String>());
                        }
                    } else if (_listDataHeader.get(i).getMenuTitle().equals(_context.getResources().getString(R.string.module_menuquotation))) {


                        if (moduleType.equals(Constant.MODULE_SALES)){
                            salesSaveQuotation = new ArrayList<>();
                            if (pageData.getUserAccessRights().getSalesSaveSalesQuotation() == true) {
                                salesSaveQuotation.add(res.getString(R.string.quotationsave));
                                _listDataChild.put(_listDataHeader.get(i).getMenuTitle(), salesSaveQuotation); // Header, Child data

                            }
                            if (pageData.getUserAccessRights().getSalesInvokeSalesQuotation() == true) {
                                salesSaveQuotation.add(res.getString(R.string.quotationinvoke));
                                _listDataChild.put(_listDataHeader.get(i).getMenuTitle(), salesSaveQuotation); // Header, Child data

                            }

                            if (pageData.getUserAccessRights().getSalesSaveSalesQuotation() == false && pageData.getUserAccessRights().getSalesInvokeSalesQuotation() == false)
                            {
                                //_listDataHeader.remove(i);
                                _listDataChild.put(_listDataHeader.get(i).getMenuTitle(), new ArrayList<String>());
                                //UtilView.showToast(activity,"Permission Denying.");
                            }


                        }

                        if (moduleType.equals(Constant.MODULE_PURCHASE)){
                            purchaseSaveQuotation =  new ArrayList<>();
                             if (pageData.getUserAccessRights().getPurchaseSavePurchaseQuotation() == true) {
                                 purchaseSaveQuotation.add(res.getString(R.string.quotationinvoke));
                                _listDataChild.put(_listDataHeader.get(i).getMenuTitle(), purchaseSaveQuotation); // Header, Child data
                            }
                            if (pageData.getUserAccessRights().getPurchaseInvokePurchaseQuotation() == true) {
                                purchaseSaveQuotation.add(res.getString(R.string.quotationsave));
                                _listDataChild.put(_listDataHeader.get(i).getMenuTitle(), purchaseSaveQuotation); // Header, Child data
                            }
                            if (pageData.getUserAccessRights().getPurchaseSavePurchaseQuotation() == false && pageData.getUserAccessRights().getPurchaseInvokePurchaseQuotation() == false)
                            {
                                _listDataChild.put(_listDataHeader.get(i).getMenuTitle(), new ArrayList<String>());
                               // UtilView.showToast(activity,"Permission Denying.");
                            }
                        }



                    } else if (_listDataHeader.get(i).getMenuTitle().equals(_context.getResources().getString(R.string.module_menuprintlist))) {
                        _listDataChild.put(_listDataHeader.get(i).getMenuTitle(), printlistItemSubMenu); // Header, Child data

                    } else if (_listDataHeader.get(i).getMenuTitle().equals(_context.getResources().getString(R.string.module_menuproforma))) {
                        salesProforma = new ArrayList<>();
                        if (pageData.getUserAccessRights().getSalesSaveProforma() == true) {
                            salesProforma.add(res.getString(R.string.proformainvoke));
                            _listDataChild.put(_listDataHeader.get(i).getMenuTitle(), salesProforma); // Header, Child data

                        }
                        if (pageData.getUserAccessRights().getSalesInvokeProforma() == true) {
                            salesProforma.add(res.getString(R.string.proformasave));
                            _listDataChild.put(_listDataHeader.get(i).getMenuTitle(), salesProforma); // Header, Child data

                        }

                        if (pageData.getUserAccessRights().getSalesSaveProforma() == false && pageData.getUserAccessRights().getSalesInvokeProforma() == false)
                        {
                            _listDataChild.put(_listDataHeader.get(i).getMenuTitle(), new ArrayList<String>());
                        }

                    } else if (_listDataHeader.get(i).getMenuTitle().equals(_context.getResources().getString(R.string.module_menudeliveryorder))) {
                        salesDeliverOrder = new ArrayList<>();

                        if (pageData.getUserAccessRights().getSalesSaveDeliveryOrder() == true) {
                            salesDeliverOrder.add(res.getString(R.string.deliveryorderSave));
                            _listDataChild.put(_listDataHeader.get(i).getMenuTitle(), salesDeliverOrder); // Header, Child data

                        }
                        if (pageData.getUserAccessRights().getSalesInvokeDeliveryOrder() == true) {
                            salesDeliverOrder.add(res.getString(R.string.deliveryorderInvoke));
                            _listDataChild.put(_listDataHeader.get(i).getMenuTitle(), salesDeliverOrder); // Header, Child data

                        }
                        if (pageData.getUserAccessRights().getSalesReturnDeliveryOrder() == true) {
                            salesDeliverOrder.add(res.getString(R.string.deliveryorderReturn));
                            _listDataChild.put(_listDataHeader.get(i).getMenuTitle(), salesDeliverOrder); // Header, Child data

                        }

                        if (pageData.getUserAccessRights().getSalesSaveDeliveryOrder() == false && pageData.getUserAccessRights().getSalesInvokeDeliveryOrder() == false && pageData.getUserAccessRights().getSalesReturnDeliveryOrder() == false)
                        {
                            _listDataChild.put(_listDataHeader.get(i).getMenuTitle(), new ArrayList<String>());
                           // UtilView.showToast(activity,"Permission Denying.");
                        }

                    }
                    else if (_listDataHeader.get(i).getMenuTitle().equals(_context.getResources().getString(R.string.module_menudrafts))) {
                        salesDraft = new ArrayList<>();
                        if (pageData.getUserAccessRights().getSalesSavesDraft() == true) {
                            salesDraft.add(res.getString(R.string.draftsSave));
                            _listDataChild.put(_listDataHeader.get(i).getMenuTitle(), salesDraft); // Header, Child data

                        }
                        if (pageData.getUserAccessRights().getSalesInvokeDraft() == true) {
                            salesDraft.add(res.getString(R.string.draftsInvoke));
                            _listDataChild.put(_listDataHeader.get(i).getMenuTitle(), salesDraft); // Header, Child data

                        }
                        if (pageData.getUserAccessRights().getSalesClearDraft() == true) {
                            salesDraft.add(res.getString(R.string.draftsClear));
                            _listDataChild.put(_listDataHeader.get(i).getMenuTitle(), salesDraft); // Header, Child data

                        }
                        if (pageData.getUserAccessRights().getSalesSavesDraft() == false && pageData.getUserAccessRights().getSalesInvokeDraft() == false && pageData.getUserAccessRights().getSalesClearDraft() == false)
                        {
                            _listDataChild.put(_listDataHeader.get(i).getMenuTitle(), new ArrayList<String>());
                        }
                    }else {
                        _listDataChild.put(_listDataHeader.get(i).getMenuTitle(), new ArrayList<String>());
                    }

                     if (_listDataHeader.get(i).getMenuTitle().equals(_context.getResources().getString(R.string.module_menurecievitem))) {
                        if (moduleType.equals(Constant.MODULE_PURCHASE)){
                            _listDataChild.put(_listDataHeader.get(i).getMenuTitle(), recieveItemSubMenu); // Header, Child data
                        }


                    }
                    if (_listDataHeader.get(i).getMenuTitle().equals(_context.getResources().getString(R.string.module_menurepair))){
                        repair.add(res.getString(R.string.repairdeliveredlist));
                        _listDataChild.put(_listDataHeader.get(i).getMenuTitle(), repair); // Header, Child data


                    }
                    if (_listDataHeader.get(i).getMenuTitle().equals(_context.getResources().getString(R.string.module_menurepair))){
                        repair.add(res.getString(R.string.repairsave));
                        _listDataChild.put(_listDataHeader.get(i).getMenuTitle(), repair); // Header, Child data


                    }
                    if (_listDataHeader.get(i).getMenuTitle().equals(_context.getResources().getString(R.string.module_menudraftrepair))){
                        repair.add(res.getString(R.string.repairdraft));
                        _listDataChild.put(_listDataHeader.get(i).getMenuTitle(), repair); // Header, Child data


                    }
                    if (_listDataHeader.get(i).getMenuTitle().equals(_context.getResources().getString(R.string.module_menudraftrepair))){
                        repair.add(res.getString(R.string.repairinvoke));
                        _listDataChild.put(_listDataHeader.get(i).getMenuTitle(), repair); // Header, Child data


                    }
                    if (_listDataHeader.get(i).getMenuTitle().equals(_context.getResources().getString(R.string.module_menureplace))){
                        repair.add(res.getString(R.string.replacing));
                        _listDataChild.put(_listDataHeader.get(i).getMenuTitle(), repair); // Header, Child data


                    }
                    if (_listDataHeader.get(i).getMenuTitle().equals(_context.getResources().getString(R.string.module_menureplace))){
                        repair.add(res.getString(R.string.replacesave));
                        _listDataChild.put(_listDataHeader.get(i).getMenuTitle(), repair); // Header, Child data


                    }
                    if (_listDataHeader.get(i).getMenuTitle().equals(_context.getResources().getString(R.string.module_menudraftreplace))){
                        repair.add(res.getString(R.string.draftreplace));
                        _listDataChild.put(_listDataHeader.get(i).getMenuTitle(), repair); // Header, Child data


                    }
                    if (_listDataHeader.get(i).getMenuTitle().equals(_context.getResources().getString(R.string.module_menudraftreplace))){
                        repair.add(res.getString(R.string.draftinvoke));
                        _listDataChild.put(_listDataHeader.get(i).getMenuTitle(), repair); // Header, Child data


                    }

                    if (_listDataHeader.get(i).getMenuTitle().equals(_context.getResources().getString(R.string.module_menuloandeliveryorder))) {
                        if (moduleType.equals(Constant.MODULE_SALES)){
                            _listDataChild.put(_listDataHeader.get(i).getMenuTitle(), deliveryOrderItemSubMenu); // Header, Child data

                          /*  _listDataChild.put(_listDataHeader.get(i).getMenuTitle(), new ArrayList<String>());
                            UtilView.showToast(activity,"Permission Denying.");*/
                        }


                     }

                    if (moduleType.equals(Constant.MODULE_SERVICE)){
                        if (_listDataHeader.get(i).getMenuTitle().equals(_context.getResources().getString(R.string.module_menugtprinlist))){
                            servicePrintList = new ArrayList<>();
                            servicePrintList.add(res.getString(R.string.serviceprintlistrepair));
                            _listDataChild.put(_listDataHeader.get(i).getMenuTitle(), servicePrintList); // Header, Child data

                            servicePrintList.add(res.getString(R.string.serviceprintlistreplace));
                            _listDataChild.put(_listDataHeader.get(i).getMenuTitle(), servicePrintList); // Header, Child data

                            servicePrintList.add(res.getString(R.string.serviceprintlistdeliver));
                            _listDataChild.put(_listDataHeader.get(i).getMenuTitle(), servicePrintList); // Header, Child data


                        }
                    }




                }

                set_listDataChild(_listDataChild);
                set_listDataHeader(_listDataHeader);

            }
        }
    }


}