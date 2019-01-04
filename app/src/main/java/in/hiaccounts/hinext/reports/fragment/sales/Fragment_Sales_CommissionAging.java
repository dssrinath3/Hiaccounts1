package in.hiaccounts.hinext.reports.fragment.sales;

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

public class Fragment_Sales_CommissionAging extends Fragment {


    public static String TAG=Fragment_Sales_CommissionAging.class.getSimpleName();


    public static Fragment_Sales_CommissionAging newInstance(){
        Fragment_Sales_CommissionAging fragment=new Fragment_Sales_CommissionAging();
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
        View view = inflater.inflate(R.layout.fragment_reportsales_commissionaging, container, false);





        return view;
    }


}
