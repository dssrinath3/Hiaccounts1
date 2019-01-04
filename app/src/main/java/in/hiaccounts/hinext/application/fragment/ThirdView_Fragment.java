package in.hiaccounts.hinext.application.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.hiaccounts.R;

/**
 * Created by android on 26/8/16.
 */
public class ThirdView_Fragment extends Fragment {

    /*
   * Fragment for App Intro screen first.
   * */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    /*
    * Fragment for App Intro screen third.
    * */
        View view = inflater.inflate(R.layout.fragment_introview_third, container, false);
        return view;
    }
}
