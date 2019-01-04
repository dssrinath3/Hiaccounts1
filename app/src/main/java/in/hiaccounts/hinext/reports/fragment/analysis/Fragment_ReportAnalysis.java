package in.hiaccounts.hinext.reports.fragment.analysis;

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

public class Fragment_ReportAnalysis extends Fragment {



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
        View view = inflater.inflate(R.layout.fragment_reportanalysis, container, false);


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

        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportAnalysisActBalance), R.drawable.ic_report_analysis_accountbalance));
        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportAnalysisCustomerReciept), R.drawable.ic_report_analysis_cusreceipt));
        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportAnalysisSuppPayment), R.drawable.ic_report_analysis_suppayment));
        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportAnalysisExpense), R.drawable.ic_report_analysis_expensecomparison));
        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportAnalysisIncome), R.drawable.ic_report_analysis_incomecomparison));
        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportAnalysisCustInvoices), R.drawable.ic_report_analysis_cusinvpayment));
        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportAnalysisSuppInovices), R.drawable.ic_report_analysis_supinvpayment));
        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportAnalysisTopVendors), R.drawable.ic_report_topvendors));
        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportAnalysisTopItems), R.drawable.ic_report_analysis_topitems));
        reportSubmenus.add(new GridMenu(getResources().getString(R.string.reportAnalysisTopCustomers), R.drawable.ic_report_analysis_topcustomers));


        return reportSubmenus;

    }



}
