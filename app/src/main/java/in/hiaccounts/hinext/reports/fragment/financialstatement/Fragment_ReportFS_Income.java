package in.hiaccounts.hinext.reports.fragment.financialstatement;

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

public class Fragment_ReportFS_Income extends Fragment {


    public static String TAG=Fragment_ReportFS_Income.class.getSimpleName();


    public static Fragment_ReportFS_Income newInstance(){
        Fragment_ReportFS_Income fragment=new Fragment_ReportFS_Income();
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
        View view = inflater.inflate(R.layout.fragment_reportps_income, container, false);





        return view;
    }


}
