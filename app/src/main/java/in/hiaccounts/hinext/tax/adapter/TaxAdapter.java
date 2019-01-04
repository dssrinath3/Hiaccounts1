package in.hiaccounts.hinext.tax.adapter;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.hiaccounts.R;
import in.hiaccounts.hinext.tax.fragment.taxconfiguration.Fragment_TaxConfigTax;
import in.hiaccounts.hinext.tax.model.taxconfig.TaxDatum;


/**
 * Custom adapter with "View Holder Pattern".
 *
 * @author Prateek
 */
public class TaxAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private Activity activity;
    private List<TaxDatum> taxDatumList;

    public TaxAdapter(Context context, List<TaxDatum> taxDatumList) {
        super();
        layoutInflater = LayoutInflater.from(context);
        activity = (Activity) context;
        this.taxDatumList = taxDatumList;

    }

    @Override
    public int getCount() {
        return taxDatumList.size();
    }

    @Override
    public TaxDatum getItem(int position) {
        return taxDatumList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.tax_adapter_tax, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        viewHolder.tvTaxName.setVisibility(View.GONE);
        viewHolder.tvTaxDescription.setVisibility(View.GONE);
        viewHolder.tvTaxStatus.setVisibility(View.GONE);
        viewHolder.chkbx.setVisibility(View.GONE);
        viewHolder.imgviewEdit.setVisibility(View.GONE);
        final TaxDatum taxDatum = getItem(position);
        if (taxDatum != null) {
            if (taxDatum.getTaxName() != null)
                viewHolder.tvTaxName.setText(taxDatum.getTaxName());

            if (taxDatum.getTaxDescription() != null)
                viewHolder.tvTaxDescription.setText(taxDatum.getTaxDescription());

            if (taxDatum.getTaxStatus() != null)
                viewHolder.tvTaxStatus.setText(taxDatum.getTaxStatus());


            viewHolder.tvTaxName.setVisibility(View.VISIBLE);
            viewHolder.tvTaxDescription.setVisibility(View.VISIBLE);
            viewHolder.tvTaxStatus.setVisibility(View.VISIBLE);
            viewHolder.chkbx.setVisibility(View.VISIBLE);
            viewHolder.imgviewEdit.setVisibility(View.VISIBLE);

        }
        viewHolder.chkbx.setId(position);

        viewHolder.chkbx.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                CheckBox cb = (CheckBox) v;
                int id = cb.getId();
                if (Fragment_TaxConfigTax.thumbnailsselection[id]) {
                    cb.setChecked(false);
                    Fragment_TaxConfigTax.thumbnailsselection[id] = false;
                } else {
                    cb.setChecked(true);
                    Fragment_TaxConfigTax.thumbnailsselection[id] = true;
                }
            }
        });

        viewHolder.chkbx.setChecked(Fragment_TaxConfigTax.thumbnailsselection[position]);


        viewHolder.imgviewEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ListView) parent).performItemClick(v, position, 0);
            }
        });


        return convertView;
    }


    public class ViewHolder {

        @BindView(R.id.tvTaxName)
        TextView tvTaxName;
        @BindView(R.id.tvTaxDescription)
        TextView tvTaxDescription;
        @BindView(R.id.tvTaxStatus)
        TextView tvTaxStatus;
        @BindView(R.id.imgviewEdit)
        ImageView imgviewEdit;
        @BindView(R.id.chkbx)
        CheckBox chkbx;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }
}
