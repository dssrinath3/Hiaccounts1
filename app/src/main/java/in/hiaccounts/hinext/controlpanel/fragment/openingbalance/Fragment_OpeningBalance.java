package in.hiaccounts.hinext.controlpanel.fragment.openingbalance;

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

public class Fragment_OpeningBalance extends Fragment {


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
        View view = inflater.inflate(R.layout.fragment_openingbalance, container, false);
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
                if (pageData.getUserAccessRights().getControlPanelOBofBS() == true)
                {
                    cPanelSubmenus.add(new GridMenu(getResources().getString(R.string.cPanelOpBalBalanceSheets), R.drawable.ic_controlpanel_ob_balancesheet));
                }
                if (pageData.getUserAccessRights().getControlPanelOBofPI() == true)
                {
                    cPanelSubmenus.add(new GridMenu(getResources().getString(R.string.cPanelOpBalPurchaseInvoices), R.drawable.ic_controlpanel_ob_purchaseinvoice));
                }
                if (pageData.getUserAccessRights().getControlPanelOBofSI() == true)
                {
                    cPanelSubmenus.add(new GridMenu(getResources().getString(R.string.cPanelOpBalSalesInvocies), R.drawable.ic_controlpanel_ob_salesinvoice));
                }
                if (pageData.getUserAccessRights().getControlPanelOBofINV() == true)
                {
                    cPanelSubmenus.add(new GridMenu(getResources().getString(R.string.cPanelOpBalInventory), R.drawable.ic_controlpanel_ob_inventory));
                }
            }
        }





       /* cPanelSubmenus.add(new GridMenu(getResources().getString(R.string.cPanelOpBalLastyrfigure), R.drawable.icon1));
        cPanelSubmenus.add(new GridMenu(getResources().getString(R.string.cPanelOpBalmonthend), R.drawable.icon));
        cPanelSubmenus.add(new GridMenu(getResources().getString(R.string.cPanelOpBalyrend), R.drawable.icon));*/


        return cPanelSubmenus;

    }


}