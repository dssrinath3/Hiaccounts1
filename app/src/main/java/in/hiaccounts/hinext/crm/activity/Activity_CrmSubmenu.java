package in.hiaccounts.hinext.crm.activity;

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
import in.hiaccounts.hinext.crm.fragment.Fragment_CrmEvent;
import in.hiaccounts.hinext.crm.fragment.Fragment_CrmLeads;

public class Activity_CrmSubmenu extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fragment_container_id)
    FrameLayout fragmentContainerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crm_submenu);
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


                if (menuTitle.equals(getResources().getString(R.string.crmLeads))) {
                    showCrmLeadsFragment();
                }
                if (menuTitle.equals(getResources().getString(R.string.crmEvent))) {
                    showCrmEventFragment();
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
    public void showCrmLeadsFragment() {
        showScreen(Fragment_CrmLeads.newInstance(), Fragment_CrmLeads.TAG, false, true);
    }

    public void showCrmEventFragment() {
        showScreen(Fragment_CrmEvent.newInstance(), Fragment_CrmEvent.TAG, false, true);
    }
}
