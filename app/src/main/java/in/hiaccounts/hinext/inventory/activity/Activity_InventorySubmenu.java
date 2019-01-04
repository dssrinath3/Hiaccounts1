package in.hiaccounts.hinext.inventory.activity;

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
import in.hiaccounts.hinext.inventory.fragment.Fragment_HsnCodes;
import in.hiaccounts.hinext.inventory.fragment.Fragment_InventoryAdvanceDiscountConfiguration;
import in.hiaccounts.hinext.inventory.fragment.Fragment_InventoryAssests;
import in.hiaccounts.hinext.inventory.fragment.Fragment_InventoryAssests_Category;
import in.hiaccounts.hinext.inventory.fragment.Fragment_InventoryAttribute;
import in.hiaccounts.hinext.inventory.fragment.Fragment_InventoryAttributeConfigurator;
import in.hiaccounts.hinext.inventory.fragment.Fragment_InventoryBrand;
import in.hiaccounts.hinext.inventory.fragment.Fragment_InventoryCategory;
import in.hiaccounts.hinext.inventory.fragment.Fragment_InventoryCountAdjusment;
import in.hiaccounts.hinext.inventory.fragment.Fragment_InventoryCountType;
import in.hiaccounts.hinext.inventory.fragment.Fragment_InventoryISDN;
import in.hiaccounts.hinext.inventory.fragment.Fragment_InventoryItem;
import in.hiaccounts.hinext.inventory.fragment.Fragment_InventoryItemCommission;
import in.hiaccounts.hinext.inventory.fragment.Fragment_InventoryLocation;
import in.hiaccounts.hinext.inventory.fragment.Fragment_InventoryLocationTransfer;
import in.hiaccounts.hinext.inventory.fragment.Fragment_InventoryLoyalty;
import in.hiaccounts.hinext.inventory.fragment.Fragment_InventoryPaymentVoucher;
import in.hiaccounts.hinext.inventory.fragment.Fragment_InventoryProduct;
import in.hiaccounts.hinext.inventory.fragment.Fragment_InventoryProductMerger;
import in.hiaccounts.hinext.inventory.fragment.Fragment_InventoryPurchasePricing;
import in.hiaccounts.hinext.inventory.fragment.Fragment_InventoryRedemption;
import in.hiaccounts.hinext.inventory.fragment.Fragment_InventorySalesDiscountConfig;
import in.hiaccounts.hinext.inventory.fragment.Fragment_InventorySalesPricing;
import in.hiaccounts.hinext.inventory.fragment.Fragment_InventoryUOM;
import in.hiaccounts.hinext.inventory.fragment.Fragment_InventoryUOMCOnvertor;
import in.hiaccounts.hinext.inventory.fragment.Fragment_InventoryVoucher;

/**
 * Created by Prateek on 2/9/2017.
 */

public class Activity_InventorySubmenu extends AppCompatActivity {


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

    private void initComponentView() {

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

                if (menuTitle.equals(getResources().getString(R.string.inventoryItem))) {
                    showItemFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.inventoryCategory))) {
                    showCategoryFragment();
                }

                if (menuTitle.equals(getResources().getString(R.string.inventoryBrand))) {
                    showBrandFragment();
                }

                if (menuTitle.equals(getResources().getString(R.string.inventoryUOM))) {
                    showUOMFragment();
                }

                if (menuTitle.equals(getResources().getString(R.string.inventorySalesPricing))) {
                    showSalesPricingFragment();
                }

                if (menuTitle.equals(getResources().getString(R.string.inventorySalesDiscountConfig))) {
                    showSalesDiscountConfigFragment();
                }

                if (menuTitle.equals(getResources().getString(R.string.inventoryPurchasePricing))) {
                    showPurchasePricingFragment();
                }

                if (menuTitle.equals(getResources().getString(R.string.inventoryLocation))) {
                    showinventoryLocationFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.inventoryMovementType))) {
                    showinventoryCountTypeFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.inventoryLocationtransfer))) {
                    showinventoryLocationTransferFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.inventoryCountAdjustment))) {
                    showinventoryCountAdjustmentFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.invnetoryProductMerger))) {
                    showinventoryProductMergerFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.inventoryProduct))) {
                    showinventoryProductFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.inventoryItemCommission))) {
                    showinventoryItemCommissionFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.cPanelcsetupHSNCode))) {
                    showinventoryHsnCodeFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.cPanelcsetupAssests))) {
                    showinventoryAssetsFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.cPanelcsetupAssestsCategory))) {
                    showinventoryAssetsCategoryFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.cPanelcsetupUomConvertor))) {
                    showinventoryUOMCOnvertorFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.cPanelcsetupISDN))) {
                    showinventoryISDNFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.cPanelcsetupAttribute))) {
                    showinventoryAttributeFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.cPanelcsetupAttributeConfigurtor))) {
                    showinventoryAttributeConfigaratorFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.cPanelcsetupPaymentVoucher))) {
                    showinventoryPaymentVoucherFragment();
                }

                if (menuTitle.equals(getResources().getString(R.string.cPanelcsetupAdvanceDiscount))) {
                    showinventoryAdvanceDiscountConfigurationFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.cPanelcsetupVoucher))) {
                    showinventoryVoucherFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.cPanelcsetupLoyalty))) {
                    showinventoryLoyaltyFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.cPanelcsetupRedemption))) {
                    showinventoryRedemptionFragment();
                }
            }
        }
    }




    // function for call fragment view.
    private void showScreen(Fragment content, String contentTag, boolean addToBackStack, boolean clearBackStack) {

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

    public void showinventoryHsnCodeFragment() {
        showScreen(Fragment_HsnCodes.newInstance(), Fragment_HsnCodes.TAG, false, true);
    }
    public void showItemFragment() {
        showScreen(Fragment_InventoryItem.newInstance(), Fragment_InventoryItem.TAG, false, true);
    }

    public void showCategoryFragment() {
        showScreen(Fragment_InventoryCategory.newInstance(), Fragment_InventoryCategory.TAG, false, true);
    }

    public void showBrandFragment() {
        showScreen(Fragment_InventoryBrand.newInstance(), Fragment_InventoryBrand.TAG, false, true);
    }

    public void showSalesPricingFragment() {
        showScreen(Fragment_InventorySalesPricing.newInstance(), Fragment_InventorySalesPricing.TAG, false, true);
    }


    public void showUOMFragment() {
        showScreen(Fragment_InventoryUOM.newInstance(), Fragment_InventoryUOM.TAG, false, true);
    }

    public void showSalesDiscountConfigFragment() {
        showScreen(Fragment_InventorySalesDiscountConfig.newInstance(), Fragment_InventorySalesDiscountConfig.TAG, false, true);
    }

    public void showPurchasePricingFragment() {
        showScreen(Fragment_InventoryPurchasePricing.newInstance(), Fragment_InventoryPurchasePricing.TAG, false, true);
    }

    public void showinventoryLocationFragment() {
        showScreen(Fragment_InventoryLocation.newInstance(), Fragment_InventoryLocation.TAG, false, true);
    }

    public void showinventoryCountTypeFragment() {
        showScreen(Fragment_InventoryCountType.newInstance(), Fragment_InventoryCountType.TAG, false, true);
    }

    public void showinventoryLocationTransferFragment() {
        showScreen(Fragment_InventoryLocationTransfer.newInstance(), Fragment_InventoryLocationTransfer.TAG, false, true);
    }

    public void showinventoryCountAdjustmentFragment() {
        showScreen(Fragment_InventoryCountAdjusment.newInstance(), Fragment_InventoryCountAdjusment.TAG, false, true);
    }

    public void showinventoryProductMergerFragment() {
        showScreen(Fragment_InventoryProductMerger.newInstance(), Fragment_InventoryProductMerger.TAG, false, true);
    }

    public void showinventoryProductFragment() {
        showScreen(Fragment_InventoryProduct.newInstance(), Fragment_InventoryProduct.TAG, false, true);
    }

    public void showinventoryItemCommissionFragment() {
        showScreen(Fragment_InventoryItemCommission.newInstance(), Fragment_InventoryItemCommission.TAG, false, true);
    }
    public void showinventoryAssetsFragment() {
        showScreen(Fragment_InventoryAssests.newInstance(), Fragment_InventoryAssests.TAG, false, true);
    }
    public void showinventoryAssetsCategoryFragment() {
        showScreen(Fragment_InventoryAssests_Category.newInstance(), Fragment_InventoryAssests_Category.TAG, false, true);
    }
    public void showinventoryISDNFragment() {
        showScreen(Fragment_InventoryISDN.newInstance(), Fragment_InventoryISDN.TAG, false, true);
    }
    public void showinventoryUOMCOnvertorFragment() {
        showScreen(Fragment_InventoryUOMCOnvertor.newInstance(), Fragment_InventoryUOMCOnvertor.TAG, false, true);
    }
    public void showinventoryAttributeFragment() {
        showScreen(Fragment_InventoryAttribute.newInstance(), Fragment_InventoryAttribute.TAG, false, true);
    }

    public void showinventoryAttributeConfigaratorFragment() {
        showScreen(Fragment_InventoryAttributeConfigurator.newInstance(), Fragment_InventoryAttributeConfigurator.TAG, false, true);
    }

    public void showinventoryPaymentVoucherFragment() {
        showScreen(Fragment_InventoryPaymentVoucher.newInstance(), Fragment_InventoryPaymentVoucher.TAG, false, true);
    }
    public void showinventoryLoyaltyFragment() {
        showScreen(Fragment_InventoryLoyalty.newInstance(), Fragment_InventoryLoyalty.TAG, false, true);
    }
    private void showinventoryVoucherFragment() {
        showScreen(Fragment_InventoryVoucher.newInstance(), Fragment_InventoryVoucher.TAG, false, true);
    }

    public void showinventoryAdvanceDiscountConfigurationFragment() {
        showScreen(Fragment_InventoryAdvanceDiscountConfiguration.newInstance(), Fragment_InventoryAdvanceDiscountConfiguration.TAG, false, true);
    }
    private void showinventoryRedemptionFragment() {
        showScreen(Fragment_InventoryRedemption.newInstance(), Fragment_InventoryRedemption.TAG, false, true);
    }
}
