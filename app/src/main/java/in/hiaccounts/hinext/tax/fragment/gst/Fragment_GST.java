package in.hiaccounts.hinext.tax.fragment.gst;

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
import in.hiaccounts.model.GridMenu;
import in.hiaccounts.hinext.tax.adapter.TaxMenuAdapter;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.SharedPreference;

/**
 * Created by Prateek on 2/9/2017.
 */

public class Fragment_GST extends Fragment {
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    Unbinder unbinder;
     Activity mActivity;
    SharedPreference sharedPreference;
    private PageLoadDataForAll pageData;
    private GridLayoutManager gridLayoutManager;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Returning the layout file after inflating
        View view = inflater.inflate(R.layout.fragment_taxgst, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponents();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null)
            unbinder.unbind();
    }

    private void initComponents() {
        gridLayoutManager = new GridLayoutManager(mActivity, 2);
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(gridLayoutManager);
        sharedPreference = SharedPreference.getInstance(mActivity);
        TaxMenuAdapter menuAdapter = new TaxMenuAdapter(mActivity, getAllMenuList());
        recycleView.setAdapter(menuAdapter);
    }

    private ArrayList<GridMenu> getAllMenuList() {
        ArrayList<GridMenu> taxSubmenus = new ArrayList<GridMenu>();


        String getPageLoadData = sharedPreference.getData(Constant.GETPAGELOADDATA_KEY);
        if (getPageLoadData !=null) {

            Gson gson = new Gson();
            pageData = gson.fromJson(getPageLoadData, PageLoadDataForAll.class);
            if (pageData != null) {


                if (pageData.getUserAccessRights().getGstIndiaGSTSR() == true)
                {
                    taxSubmenus.add(new GridMenu(getResources().getString(R.string.taxGSTSummaryReport), R.drawable.ic_taxgstsummaryreport));
                }


            }

        }

        /*  taxSubmenus.add(new GridMenu(getResources().getString(R.string.taxGST1), R.drawable.icon));
        taxSubmenus.add(new GridMenu(getResources().getString(R.string.taxGST1A), R.drawable.icon1));
        taxSubmenus.add(new GridMenu(getResources().getString(R.string.taxGST2), R.drawable.icon));*/

        return taxSubmenus;
    }
}
