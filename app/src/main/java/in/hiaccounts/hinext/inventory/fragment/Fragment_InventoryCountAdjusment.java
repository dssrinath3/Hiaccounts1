package in.hiaccounts.hinext.inventory.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.hiaccounts.R;

/**
 * Created by Prateek on 2/9/2017.
 */

public class Fragment_InventoryCountAdjusment extends Fragment {

    public static final String TAG=Fragment_InventoryCountAdjusment.class.getSimpleName();

    public static Fragment_InventoryCountAdjusment newInstance() {

        Fragment_InventoryCountAdjusment fragment = new Fragment_InventoryCountAdjusment();
        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes
        View view = inflater.inflate(R.layout.fragment_inventorycountadjustment, container, false);





        return view;
    }


}
