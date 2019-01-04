package in.hiaccounts.hinext.company_setup.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.hiaccounts.R;
import in.hiaccounts.hinext.company_setup.activity.Activity_CompanySubcription;

/**
 * Created by Prateek on 6/20/2017.
 */

public class Fragment_CompanyIntroduction extends Fragment {


    public static final String TAG = Fragment_CompanyIntroduction.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tvBack)
    TextView tvBack;
    @BindView(R.id.tvNext)
    TextView tvNext;
    Unbinder unbinder;
    // call when instace of Fra_GeneralInfo is created.
    public static Fragment_CompanyIntroduction newInstance() {
        Fragment_CompanyIntroduction fragment = new Fragment_CompanyIntroduction();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.compnaysubcription_introduction, container, false);
        unbinder = ButterKnife.bind(this, view);
        toolbar.setTitle(getString(R.string.comapnyIntroduction));
        toolbar.setTitleTextColor(Color.WHITE);
        return view;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.tvNext)
    public void onViewClicked(View view) {

        switch (view.getId()){

            case R.id.tvNext:

                ((Activity_CompanySubcription) getActivity()).showCompanyInformation();
        }
    }
}
