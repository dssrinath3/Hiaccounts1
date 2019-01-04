package in.hiaccounts.hinext.tax.fragment.taxconfiguration;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.model.PageLoadDataForAll;
import in.hiaccounts.model.GridMenu;
import in.hiaccounts.hinext.tax.adapter.TaxMenuAdapter;
import in.hiaccounts.model.NavigationHeaderMenu;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.SharedPreference;

/**
 * Created by Prateek on 2/9/2017.
 */

public class Fragment_TaxConfiguration extends Fragment {


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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Returning the layout file after inflating
        View view = inflater.inflate(R.layout.fragment_taxconfiguration, container, false);
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
        sharedPreference = SharedPreference.getInstance(mActivity);
        gridLayoutManager = new GridLayoutManager(mActivity, 2);
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(gridLayoutManager);
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


                if (pageData.getUserAccessRights().getTaxTax() == true)
                {
                    taxSubmenus.add(new GridMenu(getResources().getString(R.string.taxConfigTax), R.drawable.ic_taxtax));
                }
                if (pageData.getUserAccessRights().getTaxTaxtype() == true)
                {
                    taxSubmenus.add(new GridMenu(getResources().getString(R.string.taxConfigTaxType), R.drawable.ic_taxtaxtype));
                }

            }

        }





        return taxSubmenus;
    }


}

