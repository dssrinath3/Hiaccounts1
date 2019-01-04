package in.hiaccounts.hinext.application.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.NavigationDrawer_Activity;
import in.hiaccounts.hinext.application.adapter.ContactUs_Adapter;
import in.hiaccounts.model.company.ContactUs;

/**
 * Created by Prateek on 2/9/2017.
 */

public class Fragment_ContactUs extends Fragment {


    Activity activity;
    @BindView(R.id.lvCompany)
    ListView lvCompany;
    Unbinder unbinder;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }

    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes
        View view = inflater.inflate(R.layout.fragment_contactus, container, false);

        initContentView(view);



        return view;
    }

    private void initContentView(View view) {
        setHasOptionsMenu(true);
        unbinder = ButterKnife.bind(this, view);


        ArrayList<ContactUs>compnayContactList=new ArrayList<>();

        compnayContactList.add(new ContactUs("MyHyva (1003157-K) IT Solutions Sdn Bhd","HI Accounts Experience Center","No. 23-2, Jalan Wangsa Delima 1A,Seksyen 5, Pusat Bandar Wangsa Maju, 53300 Kuala Lumpur","Tel: +603-4141-5073"));
        compnayContactList.add(new ContactUs("","East Coast office","First Floor, Terengganu, \nK-5372 Jalan Jakar 24000 Kemaman, Terengganu ",""));
        compnayContactList.add(new ContactUs("","UK","Bolehill, Wingerworth, Chesterfied, Derbys S42 6RG ",""));
        compnayContactList.add(new ContactUs("HYVA IT Solutions","India","HYVA Primus, #45/155, 5th Main, BTM 1st Stage, Bangalore – 560076, Karnataka, INDIA.","Tel: +91 9902811552"));

        ContactUs_Adapter adapter=new ContactUs_Adapter(activity,compnayContactList);
        lvCompany.setAdapter(adapter);
        adapter.notifyDataSetChanged();



    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.retail_pos, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {

        menu.findItem(R.id.save_order).setVisible(false);
        menu.findItem(R.id.delete_items).setVisible(false);
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {

            case R.id.changeCompany:
                //((NavigationDrawer_Activity) activity).changeServerDetail();
                break;
            case R.id.logout:
                ((NavigationDrawer_Activity) activity).logout();

                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
