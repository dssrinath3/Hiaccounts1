package in.hiaccounts.hinext.crm.fragment;


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
import in.hiaccounts.hinext.crm.adapter.CrmMenuAdapter;
import in.hiaccounts.model.GridMenu;

/**
 * Created by Srinath on 19/12/2017.
 */
public class Fragment_Crm extends Fragment {



    private Activity mActivity;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    private Unbinder unbinder;
    private GridLayoutManager gridLayoutManager;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crm, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponents();
        return view;
    }

    private void initComponents() {
        gridLayoutManager = new GridLayoutManager(mActivity, 2);
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(gridLayoutManager);
        CrmMenuAdapter menuAdapter = new CrmMenuAdapter(mActivity, getAllMenuList());
        recycleView.setAdapter(menuAdapter);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private ArrayList<GridMenu> getAllMenuList() {

        ArrayList<GridMenu> inventorySubmenus = new ArrayList<GridMenu>();


        inventorySubmenus.add(new GridMenu(getResources().getString(R.string.crmLeads), R.drawable.ic_salesdiscountconfiguration));
        inventorySubmenus.add(new GridMenu(getResources().getString(R.string.crmEvent), R.drawable.ic_salesdiscountconfiguration));

        return inventorySubmenus;
    }

}
