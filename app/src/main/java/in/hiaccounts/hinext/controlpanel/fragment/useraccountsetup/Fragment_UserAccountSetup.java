package in.hiaccounts.hinext.controlpanel.fragment.useraccountsetup;

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

public class Fragment_UserAccountSetup extends Fragment {

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
        //Change R.layout.tab1 in you classes
        View view = inflater.inflate(R.layout.fragment_useraccountsetup, container, false);

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
                if (pageData.getUserAccessRights().getControlPanelUAS() == true) {
                    cPanelSubmenus.add(new GridMenu(getResources().getString(R.string.cPaneluseractSetup), R.drawable.ic_controlpanel_us_account));

                }
            }
        }

        //  cPanelSubmenus.add(new GridMenu(getResources().getString(R.string.cPaneluseractAuditTrial), R.drawable.icon1));
        return cPanelSubmenus;

    }


}
