package in.hiaccounts.hinext.company_setup.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.NavigationDrawer_Activity;
import in.hiaccounts.hinext.company_setup.fragment.Fragment_CompanyChartsAccounts;
import in.hiaccounts.hinext.company_setup.fragment.Fragment_CompanyConfiguration;
import in.hiaccounts.hinext.company_setup.fragment.Fragment_CompanyFinancialYear;
import in.hiaccounts.hinext.company_setup.fragment.Fragment_CompanyInformation;
import in.hiaccounts.hinext.company_setup.fragment.Fragment_CompanyIntroduction;
import in.hiaccounts.hinext.company_setup.fragment.Fragment_CompanySubscription;
import in.hiaccounts.utility.Constant;

/**
 * Created by Prateek on 6/20/2017.
 */

public class Activity_CompanySubcription extends AppCompatActivity {

    @BindView(R.id.fragment_layout)
    FrameLayout fragmentLayout;

    String pageNumber="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_companysubcription);
        initView(savedInstanceState);

    }

    private void initView(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        Intent intent=getIntent();
        pageNumber=intent.getStringExtra(Constant.NAVIGATION_REDIRECTPAGE);

       /* if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_layout,new Fragment_CompanyIntroduction()).commit();
        }*/
        callFragment();
    }

    // function for call fragment view.
    private void showScreen(Fragment content, String contentTag, boolean addToBackStack, boolean clearBackStack) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(R.anim.left_slide_in, R.anim.left_slide_out, R.anim.right_slide_in, R.anim.right_slide_out);
        ft.replace(R.id.fragment_layout, content, contentTag);

        if (clearBackStack) {

            fm.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }

        if (addToBackStack) {

            ft.addToBackStack(String.valueOf(System.identityHashCode(content)));
        }

        ft.commitAllowingStateLoss();
        fm.executePendingTransactions();
    }

    public void callFragment(){

        if (pageNumber!=null && !pageNumber.equals("")){
            switch (pageNumber){

                case Constant.PAGE_DAHSBOARD:
                    Intent intent = new Intent(this, NavigationDrawer_Activity.class);
                    intent.putExtra(Constant.NAVIGATION_GROUPHEADER, Constant.NAVIGATION_DASHBOARD);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                    break;
                case Constant.PAGE_SUBSCRIPTION:
                    showCompanySubscription();
                    break;
                case Constant.PAGE_CONFIG:
                    showCompanyConfiguration();
                    break;
                case Constant.PAGE_SETUP:
                    showCompanyIntroduction();
                    break;
            }
        }

    }

    public void showCompanyInformation() {
        showScreen(Fragment_CompanyInformation.newInstance(), Fragment_CompanyInformation.TAG, true, false);
    }

    public void showCompanyIntroduction() {
        showScreen(Fragment_CompanyIntroduction.newInstance(), Fragment_CompanyIntroduction.TAG, false, false);
    }
    public void showCompanyFinancialYear() {
        showScreen(Fragment_CompanyFinancialYear.newInstance(), Fragment_CompanyFinancialYear.TAG, true, false);
    }
    public void showCompanyChartsAccount() {
        showScreen(Fragment_CompanyChartsAccounts.newInstance(), Fragment_CompanyChartsAccounts.TAG, true, false );
    }
    public void showCompanyConfiguration() {
        showScreen(Fragment_CompanyConfiguration.newInstance(), Fragment_CompanyConfiguration.TAG, true, false );
    }
    public void showCompanySubscription() {
        showScreen(Fragment_CompanySubscription.newInstance(), Fragment_CompanySubscription.TAG, true, false );
    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
