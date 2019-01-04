package in.hiaccounts.hinext.reports.fragment.restaurent;


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

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_ReportRestaurent extends Fragment {


    Activity mActivity;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    Unbinder unbinder;
    private GridLayoutManager gridLayoutManager;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_report_restaurent, container, false);
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
        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportSRestaurentInvoiceRegister), R.drawable.ic_report_sales_salesinvoicelisting));
        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportSRestaurentDayEndRegister), R.drawable.ic_report_purchase_supplierstatementlisting));
        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportSRestaurentItemSales), R.drawable.ic_report_purchase_supplierstatementlisting));
        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportSRestaurentDayEnd), R.drawable.ic_report_sales_salesorderlisting));
        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportSRestaurentMonthEnd), R.drawable.ic_report_sales_salesorderlisting));
        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportSRestaurentCancelledItemReports), R.drawable.ic_report_purchase_cancelledpurchaseinvoice));

        return reportSubmenus;

    }
}
