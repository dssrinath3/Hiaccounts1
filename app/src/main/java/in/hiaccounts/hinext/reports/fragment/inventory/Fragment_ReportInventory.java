package in.hiaccounts.hinext.reports.fragment.inventory;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.hiaccounts.R;
import in.hiaccounts.hinext.reports.adapter.ReportMenuAdapter;
import in.hiaccounts.model.GridMenu;
import in.hiaccounts.hinext.tax.adapter.TaxMenuAdapter;

/**
 * Created by Prateek on 2/9/2017.
 */

public class Fragment_ReportInventory extends Fragment {


    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    Unbinder unbinder;
    Activity mActivity;
    private GridLayoutManager gridLayoutManager;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes
        View view = inflater.inflate(R.layout.fragment_reportinventory, container, false);


        unbinder = ButterKnife.bind(this, view);
        initComponents();

        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initComponents() {
        gridLayoutManager = new GridLayoutManager(mActivity, 2);
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(gridLayoutManager);
        ReportMenuAdapter menuAdapter = new ReportMenuAdapter(mActivity, getAllMenuList());
        recycleView.setAdapter(menuAdapter);
    }

    private ArrayList<GridMenu> getAllMenuList() {

        ArrayList<GridMenu>reportSubmenus = new ArrayList<GridMenu>();

        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportInvGRNValuation), R.drawable.ic_report_inventory_valuationr));
        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportInvPlanning), R.drawable.ic_report_inventory_inventoryplanning));
        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportInvValuation), R.drawable.ic_report_inventory_valuation));
        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportInvSales), R.drawable.ic_report_inventory_sales));
        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportInvNonMovement), R.drawable.ic_report_inventory_inventorynonmovement));
        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportInvMovement), R.drawable.ic_report_inventroy_movement));
        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportInvList), R.drawable.ic_report_inventory_inventorylist));
        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportInvOutstandingGNR), R.drawable.ic_report_inventory_outstandinggrn));
        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportInvStockCheck), R.drawable.ic_report_inventory_stockcheckreport));
        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportInvAppleStock), R.drawable.ic_report_inventory_applestockcheck));
        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportInvAppleSales), R.drawable.ic_report_inventory_applesalesstock));


        return reportSubmenus;

    }




}
