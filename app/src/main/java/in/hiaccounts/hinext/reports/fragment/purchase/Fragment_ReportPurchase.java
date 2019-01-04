package in.hiaccounts.hinext.reports.fragment.purchase;

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

public class Fragment_ReportPurchase extends Fragment {

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
        View view = inflater.inflate(R.layout.fragment_reportpurchase, container, false);


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


        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportPurSupplierSalesReport), R.drawable.ic_report_purchase_suppliersalesreport));
        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportPurReceiveItem), R.drawable.ic_report_purchase_receiveitemlisting));
        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportPurSupplierStatment), R.drawable.ic_report_purchase_supplierstatementlisting));
        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportPurPO), R.drawable.ic_report_purchase_orderlisting));
        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportPurSuppliers), R.drawable.ic_report_purchase_supplierlisting));
        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportPurInvoices), R.drawable.ic_report_purchase_invoicelisting));
        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportPurReturn), R.drawable.ic_report_purchase_returnlisting));
        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportPurSuppPayments), R.drawable.ic_report_purchase_supplierpayment));
        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportPurCreditors), R.drawable.ic_report_purchase_creditorsageing));
        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportPurAdjusment), R.drawable.ic_report_purchase_adjustment));
        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportPurCancelledInovices), R.drawable.ic_report_purchase_cancelledpurchaseinvoice));


        return reportSubmenus;

    }

}
