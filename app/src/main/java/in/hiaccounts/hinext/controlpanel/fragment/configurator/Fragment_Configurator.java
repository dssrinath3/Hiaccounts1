package in.hiaccounts.hinext.controlpanel.fragment.configurator;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.model.PageLoadDataForAll;
import in.hiaccounts.hinext.controlpanel.adapter.ControlPanelMenuAdapter;
import in.hiaccounts.model.GridMenu;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.SharedPreference;

/**
 * Created by Prateek on 2/9/2017.
 */

public class Fragment_Configurator extends Fragment {

    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    Unbinder unbinder;
    Activity mActivity;
    private GridLayoutManager gridLayoutManager;
    SharedPreference sharedPreference;
    private PageLoadDataForAll pageData;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }


    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Returning the layout file after inflating
        View view = inflater.inflate(R.layout.fragment_configurator, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponents();
        return view;
    }

    private void initComponents() {
        sharedPreference = SharedPreference.getInstance(mActivity);
        gridLayoutManager = new GridLayoutManager(mActivity, 2);
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(gridLayoutManager);

        ControlPanelMenuAdapter menuAdapter = new ControlPanelMenuAdapter(mActivity, getAllMenuList());
        recycleView.setAdapter(menuAdapter);
    }

    private ArrayList<GridMenu> getAllMenuList() {

        ArrayList<GridMenu> cPanelSubmenus = new ArrayList<GridMenu>();

        String getPageLoadData = sharedPreference.getData(Constant.GETPAGELOADDATA_KEY);
        if (getPageLoadData !=null) {

            Gson gson = new Gson();
            pageData = gson.fromJson(getPageLoadData, PageLoadDataForAll.class);
            if (pageData != null) {
                if (pageData.getUserAccessRights().getcPConfiguratorBank() == true)
                {
                    cPanelSubmenus.add(new GridMenu(getResources().getString(R.string.cPanelConfigBank), R.drawable.ic_controlpanel_config_bank));

                }
                if (pageData.getUserAccessRights().getcPConfiguratorAgent() == true)
                {
                    cPanelSubmenus.add(new GridMenu(getResources().getString(R.string.cPanelConfigAgent), R.drawable.ic_controlpanel_config_agent));

                }
                if (pageData.getUserAccessRights().getcPConfiguratorCurrency() == true)
                {
                    cPanelSubmenus.add(new GridMenu(getResources().getString(R.string.cPanelConfigCurrency), R.drawable.ic_controlpanel_config_currency));

                }
                if (pageData.getUserAccessRights().getcPConfiguratorEmployee() == true)
                {
                    cPanelSubmenus.add(new GridMenu(getResources().getString(R.string.cPanelConfigEmployee), R.drawable.ic_controlpanel_config_employee));

                }
                if (pageData.getUserAccessRights().getcPConfiguratorCountry() == true)
                {
                    cPanelSubmenus.add(new GridMenu(getResources().getString(R.string.cPanelConfigCountry), R.drawable.ic_controlpanel_config_country));

                }
                if (pageData.getUserAccessRights().getcPConfiguratorExchangeRate() == true)
                {
                    cPanelSubmenus.add(new GridMenu(getResources().getString(R.string.cPanelConfigExRate), R.drawable.ic_controlpanel_config_exchangerate));

                }
                if (pageData.getUserAccessRights().getcPConfiguratorProjectTitle() == true)
                {
                    cPanelSubmenus.add(new GridMenu(getResources().getString(R.string.cPanelConfigProject), R.drawable.ic_controlpanel_config_project));

                }
                if (pageData.getUserAccessRights().getcPConfiguratorShippingMethod() == true)
                {
                    cPanelSubmenus.add(new GridMenu(getResources().getString(R.string.cPanelConfigShiipingMethod), R.drawable.ic_controlpanel_config_shippingmethod));

                }
                if (pageData.getUserAccessRights().getcPConfiguratorEmailserver() == true)
                {
                    cPanelSubmenus.add(new GridMenu(getResources().getString(R.string.cPanelConfigEmailServer), R.drawable.ic_controlpanel_config_emailserver));

                }
                if (pageData.getUserAccessRights().getcPConfiguratorPaymentMethod() == true)
                {
                    cPanelSubmenus.add(new GridMenu(getResources().getString(R.string.cPanelConfigPaymentMethod), R.drawable.ic_controlpanel_config_paymentmethod));

                }
                if (pageData.getUserAccessRights().getcPConfiguratorConfiguration() == true)
                {
                    cPanelSubmenus.add(new GridMenu(getResources().getString(R.string.cPanelConfiguration), R.drawable.ic_controlpanel_config_configuration));

                }
                if (pageData.getUserAccessRights().getcPConfiguratorVersionControl() == true)
                {
                    cPanelSubmenus.add(new GridMenu(getResources().getString(R.string.cPanelVersionControl), R.drawable.ic_controlpanel_config_configuration));

                }
                if (pageData.getUserAccessRights().getcPConfiguratorServiceCharge() == true)
                {
                    cPanelSubmenus.add(new GridMenu(getResources().getString(R.string.cPanelServiceCharge), R.drawable.ic_controlpanel_config_currency));

                }

            }
        }

        cPanelSubmenus.add(new GridMenu(getResources().getString(R.string.cPanelCart), R.drawable.ic_controlpanel_config_shippingmethod));
        cPanelSubmenus.add(new GridMenu(getResources().getString(R.string.cPanelState), R.drawable.ic_controlpanel_config_country));

        return cPanelSubmenus;

    }

}
