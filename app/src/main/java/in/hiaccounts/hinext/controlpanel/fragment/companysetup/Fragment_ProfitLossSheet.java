package in.hiaccounts.hinext.controlpanel.fragment.companysetup;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.hiaccounts.R;
import in.hiaccounts.hinext.controlpanel.adapter.ChartListAdapter;
import in.hiaccounts.hinext.controlpanel.model.company_setup.chartsofaccount.ProfitLossAccnt;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 2/9/2017.
 */

public class Fragment_ProfitLossSheet extends Fragment {


    public static String TAG = Fragment_ProfitLossSheet.class.getSimpleName();

    @BindView(R.id.listview)
    ListView listview;
    Unbinder unbinder;
    @BindView(R.id.tv)
    TextView tv;
    ChartListAdapter chartListAdapter;
    Activity mActivity;

    public static Fragment_ProfitLossSheet newInstance() {
        Fragment_ProfitLossSheet fragment = new Fragment_ProfitLossSheet();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;

    }

    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Returning the layout file after inflating
        View view = inflater.inflate(R.layout.fragment_csetupprofitloss, container, false);
        UtilView.showLogCat(TAG, "" + Fragment_ChartsOfAccount.profitLossAccntsList.size());
        unbinder = ButterKnife.bind(this, view);
        initCompoenetView();
        return view;
    }

    private void initCompoenetView() {
        List<ProfitLossAccnt> profitLossAccnts = Fragment_ChartsOfAccount.profitLossAccntsList;
        if (profitLossAccnts != null && profitLossAccnts.size() > 0) {
            LayoutInflater inflater = mActivity.getLayoutInflater();
            ViewGroup header = (ViewGroup) inflater.inflate(R.layout.companysetup_adapter_profitloss, null, false);

            TextView tvActCode = header.findViewById(R.id.tvActCode);
            TextView tvActName = header.findViewById(R.id.tvActName);
            TextView tvActClass = header.findViewById(R.id.tvActClass);
            TextView tvActGroup = header.findViewById(R.id.tvActGroup);

            tvActCode.setText("Account Code");
            tvActName.setText("Account Name");
            tvActClass.setText("Account Class");
            tvActGroup.setText("Group Type");

            UtilView.setTextAppearanceSmall(mActivity, tvActCode);
            UtilView.setTextAppearanceSmall(mActivity, tvActName);
            UtilView.setTextAppearanceSmall(mActivity, tvActClass);
            UtilView.setTextAppearanceSmall(mActivity, tvActGroup);

            UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvActCode);
            UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvActName);
            UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvActClass);
            UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tvActGroup);

            if (listview != null) {
                listview.addHeaderView(header);
                List<Object> objectList = new ArrayList<>();
                objectList.addAll(profitLossAccnts);
                chartListAdapter = new ChartListAdapter(mActivity, objectList);
                listview.setAdapter(chartListAdapter);
                chartListAdapter.notifyDataSetChanged();
                listview.setVisibility(View.VISIBLE);
                if (tv != null)
                    tv.setVisibility(View.GONE);
            }
        } else {
            if (listview != null)
                listview.setVisibility(View.GONE);
            if (tv != null)
                tv.setVisibility(View.VISIBLE);
        }


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
