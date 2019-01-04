package in.hiaccounts.hinext.sales.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.hiaccounts.R;
import in.hiaccounts.hinext.sales.model.save_response.TaxSummaryList;

/**
 * Created by Prateek on 6/15/2017.
 */

public class PosGSTSummary_Adapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private List<TaxSummaryList> taxList;

    public PosGSTSummary_Adapter(Activity activity, List<TaxSummaryList> taxList) {
        this.taxList = taxList;
        layoutInflater = LayoutInflater.from(activity);
    }

    @Override
    public int getCount() {
        return taxList.size();
    }

    @Override
    public TaxSummaryList getItem(int position) {
        return taxList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.adapter_posgstsummary, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        final TaxSummaryList taxData = getItem(position);
        viewHolder.tvGSTSummary.setVisibility(View.GONE);
        viewHolder.tvGSTAmount.setVisibility(View.GONE);
        viewHolder.tvGSTTax.setVisibility(View.GONE);
        String taxName = "", taxPercentage = "";
        if (taxData != null) {
            if (taxData.getTaxName() != null)
                taxName = taxData.getTaxName();
            if (taxData.getTaxPercent() != null)
                taxPercentage = "" + taxData.getTaxPercent();
            viewHolder.tvGSTSummary.setText(taxName + " = " + taxPercentage + "%");

            if (taxData.getAmt() != null)
                viewHolder.tvGSTAmount.setText("" + taxData.getAmt());


            if (taxData.getTaxAmount() != null)
                viewHolder.tvGSTTax.setText("" + taxData.getTaxAmount());

            viewHolder.tvGSTSummary.setVisibility(View.VISIBLE);
            viewHolder.tvGSTAmount.setVisibility(View.VISIBLE);
            viewHolder.tvGSTTax.setVisibility(View.VISIBLE);

        }
        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.tvGSTSummary)
        TextView tvGSTSummary;
        @BindView(R.id.tvGSTAmount)
        TextView tvGSTAmount;
        @BindView(R.id.tvGSTTax)
        TextView tvGSTTax;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

