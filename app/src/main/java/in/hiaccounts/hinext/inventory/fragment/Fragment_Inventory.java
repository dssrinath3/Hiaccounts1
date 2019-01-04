package in.hiaccounts.hinext.inventory.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.model.PageLoadDataForAll;
import in.hiaccounts.hinext.inventory.adapter.InventoryMenuAdapter;
import in.hiaccounts.model.GridMenu;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.SharedPreference;

/**
 * Created by Prateek on 2/9/2017.
 */

public class Fragment_Inventory extends Fragment {
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    private Activity mActivity;
    private Unbinder unbinder;
    private GridLayoutManager gridLayoutManager;
    SharedPreference sharedPreference;
    private PageLoadDataForAll pageData;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Returning the layout file after inflating
        View view = inflater.inflate(R.layout.fragment_inventory, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponents();
        return view;
    }

    private void initComponents() {
        sharedPreference = SharedPreference.getInstance(mActivity);
        gridLayoutManager = new GridLayoutManager(mActivity, 2);
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(gridLayoutManager);
        InventoryMenuAdapter menuAdapter = new InventoryMenuAdapter(mActivity, getAllMenuList());
        recycleView.setAdapter(menuAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private ArrayList<GridMenu> getAllMenuList() {

        ArrayList<GridMenu> inventorySubmenus = new ArrayList<GridMenu>();

        String getPageLoadData = sharedPreference.getData(Constant.GETPAGELOADDATA_KEY);
        if (getPageLoadData !=null) {

            Gson gson = new Gson();
            pageData = gson.fromJson(getPageLoadData, PageLoadDataForAll.class);
            if (pageData != null) {


                if (pageData.getUserAccessRights().getInventoryItem() == true)
                {
                    inventorySubmenus.add(new GridMenu(getResources().getString(R.string.inventoryItem), R.drawable.ic_invitems));
                }
                if (pageData.getUserAccessRights().getInventoryCategory() == true)
                {
                    inventorySubmenus.add(new GridMenu(getResources().getString(R.string.inventoryCategory), R.drawable.ic_invcategory));
                }
                if (pageData.getUserAccessRights().getInventoryBrand() == true)
                {
                    inventorySubmenus.add(new GridMenu(getResources().getString(R.string.inventoryBrand), R.drawable.ic_invbrand));
                }
                if (pageData.getUserAccessRights().getInventoryUOM() == true)
                {
                    inventorySubmenus.add(new GridMenu(getResources().getString(R.string.inventoryUOM), R.drawable.ic_invuom));

                }
                if (pageData.getUserAccessRights().getInventorySalesPricing() == true)
                {
                    inventorySubmenus.add(new GridMenu(getResources().getString(R.string.inventorySalesPricing), R.drawable.ic_invsalespricing));

                }
                if (pageData.getUserAccessRights().getInventoryPurchasePricing() == true)
                {
                    inventorySubmenus.add(new GridMenu(getResources().getString(R.string.inventoryPurchasePricing), R.drawable.ic_invpurchasepricing));

                }
                if (pageData.getUserAccessRights().getInventoryInventoryLocation() == true)
                {
                    inventorySubmenus.add(new GridMenu(getResources().getString(R.string.inventoryLocation), R.drawable.ic_invlocation));

                }
                if (pageData.getUserAccessRights().getInventoryInvLocTransfer() == true)
                {
                    inventorySubmenus.add(new GridMenu(getResources().getString(R.string.inventoryLocationtransfer), R.drawable.ic_invlocation));

                }
                if (pageData.getUserAccessRights().getInventoryInventoryMovementType() == true)
                {
                    inventorySubmenus.add(new GridMenu(getResources().getString(R.string.inventoryMovementType), R.drawable.ic_invcounttype));

                }
                if (pageData.getUserAccessRights().getInventoryHsnCode() == true)
                {
                    inventorySubmenus.add(new GridMenu(getResources().getString(R.string.cPanelcsetupHSNCode), R.drawable.ic_invhsncode));

                }
                if (pageData.getUserAccessRights().getInventoryAssets() == true)
                {
                    inventorySubmenus.add(new GridMenu(getResources().getString(R.string.cPanelcsetupAssests), R.drawable.ic_invitems));

                }
                if (pageData.getUserAccessRights().getInventoryUomConverter() == true)
                {
                    inventorySubmenus.add(new GridMenu(getResources().getString(R.string.cPanelcsetupUomConvertor), R.drawable.ic_invuom));

                }
                if (pageData.getUserAccessRights().getInventoryAssetsCat() == true)
                {
                    inventorySubmenus.add(new GridMenu(getResources().getString(R.string.cPanelcsetupAssestsCategory), R.drawable.ic_invcategory));

                }
                if (pageData.getUserAccessRights().getInventoryJSDN() == true)
                {
                    inventorySubmenus.add(new GridMenu(getResources().getString(R.string.cPanelcsetupISDN), R.drawable.ic_invitems));

                }
                if (pageData.getUserAccessRights().getInventoryAttribute() == true)
                {
                    inventorySubmenus.add(new GridMenu(getResources().getString(R.string.cPanelcsetupAttribute), R.drawable.ic_invuom));

                }
                if (pageData.getUserAccessRights().getInventoryAttributeConfig() == true)
                {
                    inventorySubmenus.add(new GridMenu(getResources().getString(R.string.cPanelcsetupAttributeConfigurtor), R.drawable.ic_invuom));

                }
                if (pageData.getUserAccessRights().getInventoryPayVoucher() == true)
                {
                    inventorySubmenus.add(new GridMenu(getResources().getString(R.string.cPanelcsetupPaymentVoucher), R.drawable.ic_invitems));

                }
                if (pageData.getUserAccessRights().getInventorySalesDiscountConfig() == true)
                {
                    inventorySubmenus.add(new GridMenu(getResources().getString(R.string.cPanelcsetupSalesDiscount), R.drawable.ic_salesdiscountconfiguration));

                }
                if (pageData.getUserAccessRights().getInventoryAdvDisConfig() == true)
                {
                    inventorySubmenus.add(new GridMenu(getResources().getString(R.string.cPanelcsetupAdvanceDiscount), R.drawable.ic_salesdiscountconfiguration));

                }
                if (pageData.getUserAccessRights().getInventoryVoucher() == true)
                {
                    inventorySubmenus.add(new GridMenu(getResources().getString(R.string.cPanelcsetupVoucher), R.drawable.ic_salesdiscountconfiguration));

                }
                if (pageData.getUserAccessRights().getInventoryLoyality() == true)
                {
                    inventorySubmenus.add(new GridMenu(getResources().getString(R.string.cPanelcsetupLoyalty), R.drawable.ic_salesdiscountconfiguration));

                }
                if (pageData.getUserAccessRights().getInventoryItemCommission() == true)
                {
                    inventorySubmenus.add(new GridMenu(getResources().getString(R.string.inventoryItemCommission), R.drawable.ic_itemcommission));

                }
                if (pageData.getUserAccessRights().getInventoryRedemption() == true)
                {
                    inventorySubmenus.add(new GridMenu(getResources().getString(R.string.inventoryRedemption), R.drawable.redemption));

                }
            }

        }



        //inventorySubmenus.add(new GridMenu(getResources().getString(R.string.inventorySalesDiscountConfig), R.drawable.icon1));

       /*
        inventorySubmenus.add(new GridMenu(getResources().getString(R.string.inventoryCountAdjustment), R.drawable.icon));
        inventorySubmenus.add(new GridMenu(getResources().getString(R.string.invnetoryProductMerger), R.drawable.icon1));
        inventorySubmenus.add(new GridMenu(getResources().getString(R.string.inventoryProduct), R.drawable.icon));
        inventorySubmenus.add(new GridMenu(getResources().getString(R.string.inventoryItemCommission), R.drawable.icon1));*/

        return inventorySubmenus;
    }
}
