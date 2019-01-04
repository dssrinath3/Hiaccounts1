package in.hiaccounts.hinext.tax.activity;

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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.hiaccounts.R;
import in.hiaccounts.hinext.tax.fragment.gst.Fragment_GstTax1;
import in.hiaccounts.hinext.tax.fragment.gst.Fragment_GstTax1A;
import in.hiaccounts.hinext.tax.fragment.gst.Fragment_GstTax2;
import in.hiaccounts.hinext.tax.fragment.gst.Fragment_GstTaxSummaryReport;
import in.hiaccounts.hinext.tax.fragment.taxconfiguration.Fragment_TaxConfigTax;
import in.hiaccounts.hinext.tax.fragment.taxconfiguration.Fragment_TaxConfigTaxType;

/**
 * Created by Prateek on 2/9/2017.
 */

public class Activity_TaxSubmenu extends AppCompatActivity {


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

                if (menuTitle.equals(getResources().getString(R.string.taxConfigTaxType))) {
                    showtaxConfigTaxTypeFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.taxConfigTax))) {
                    showtaxConfigTaxFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.taxGST1))) {
                    showGstTax1Fragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.taxGST1A))) {
                    showGstTax1AFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.taxGST2))) {
                    showGstTax2Fragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.taxGSTSummaryReport))) {
                    showGstTaxSummaryReportFragment();
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

    public void showtaxConfigTaxFragment() {
        showScreen(Fragment_TaxConfigTax.newInstance(), Fragment_TaxConfigTax.TAG, false, true);
    }
    public void showtaxConfigTaxTypeFragment() {
        showScreen(Fragment_TaxConfigTaxType.newInstance(), Fragment_TaxConfigTaxType.TAG, false, true);
    }
    public void showGstTax1Fragment() {
        showScreen(Fragment_GstTax1.newInstance(), Fragment_GstTax1.TAG, false, true);
    }
    public void showGstTax1AFragment() {
        showScreen(Fragment_GstTax1A.newInstance(), Fragment_GstTax1A.TAG, false, true);
    }
    public void showGstTax2Fragment() {
        showScreen(Fragment_GstTax2.newInstance(), Fragment_GstTax2.TAG, false, true);
    }
    public void showGstTaxSummaryReportFragment() {
        showScreen(Fragment_GstTaxSummaryReport.newInstance(), Fragment_GstTaxSummaryReport.TAG, false, true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        List<Fragment> frgamentList=getSupportFragmentManager().getFragments();

        for (int i=0;i<frgamentList.size();i++){
            Fragment fragment=frgamentList.get(i);

            if (fragment instanceof Fragment_TaxConfigTax){
                fragment.onActivityResult(requestCode,resultCode,data);
            }
        }
    }
}
