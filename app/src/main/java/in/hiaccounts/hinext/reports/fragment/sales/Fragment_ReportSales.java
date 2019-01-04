package in.hiaccounts.hinext.reports.fragment.sales;

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

public class Fragment_ReportSales extends Fragment {

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
        View view = inflater.inflate(R.layout.fragment_reportsales, container, false);


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
        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportSalesQuotation), R.drawable.ic_report_sales_salesquotationlisting));
        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportSalesOrders), R.drawable.ic_report_sales_salesorderlisting));
        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportSalesOrdersDetails), R.drawable.ic_report_sales_salesorderlisting));
        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportSalesItemCommission), R.drawable.ic_report_sales_itemcommissionlisting));
        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportSalesInvoices), R.drawable.ic_report_sales_salesinvoicelisting));
        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportSalesReturn), R.drawable.ic_report_sales_salesreturnlisting));
        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportSalesDelivery), R.drawable.ic_report_sales_productdeliverylistic_report_sales_ing));
        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportSalesCustomerStatement), R.drawable.ic_report_sales_customerstatement));
        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportSalesPaymentReport), R.drawable.ic_report_sales_paymentreport));
        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportSalesPrice), R.drawable.ic_report_sales_pricelisting));
        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportSalesCommissionAging), R.drawable.ic_report_sales_commissionaging));
        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportSalesDebtorsAging), R.drawable.ic_report_sales_debtor));
        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportSalesCustomers), R.drawable.ic_report_sales_customerlisting));
        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportSalesAgent), R.drawable.ic_report_sales_agentlisting));
        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportSalesPatient), R.drawable.ic_report_sales_patientlisting));
        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportSalesCustomerPayment), R.drawable.ic_report_sales_customerpayment));
        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportSalesAdjustment), R.drawable.ic_report_sales_salesadjustment));
        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportSalesCancelledInvoices), R.drawable.ic_report_sales_cancelledsalesinvoice));


        return reportSubmenus;

    }




}
