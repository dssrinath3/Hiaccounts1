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
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.activity.NavigationDrawer_Activity;

/**
 * Created by Prateek on 2/9/2017.
 */

public class Fragment_AboutUs extends Fragment {


    Activity activity;
    @BindView(R.id.tvAbouthiaacounts)
    TextView tvAbouthiaacounts;
    @BindView(R.id.tvAboutcompany)
    TextView tvAboutcompany;
    @BindView(R.id.tvAboutvision)
    TextView tvAboutvision;
    @BindView(R.id.tvAboutourteam)
    TextView tvAboutourteam;
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
        View view = inflater.inflate(R.layout.fragment_aboutus, container, false);

        initComponents(view);



        return view;
    }

    private void initComponents(View view) {
        setHasOptionsMenu(true);
        unbinder = ButterKnife.bind(this,view);


        tvAbouthiaacounts.setText(getResources().getString(R.string.AboutHiAcocunt));
        tvAboutcompany.setText(getResources().getString(R.string.AboutCompany));
        tvAboutvision.setText(getResources().getString(R.string.AboutVision));
        tvAboutourteam.setText(getResources().getString(R.string.AboutOurTeam));

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
              //  ((NavigationDrawer_Activity) activity).changeServerDetail();
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
