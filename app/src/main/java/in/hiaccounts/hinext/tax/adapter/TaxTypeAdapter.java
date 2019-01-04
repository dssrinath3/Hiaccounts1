package in.hiaccounts.hinext.tax.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import in.hiaccounts.R;
import in.hiaccounts.hinext.tax.model.taxconfig.TaxTypeDatum;

/**
 * Custom adapter with "View Holder Pattern".
 *
 * @author Prateek
 */
public class TaxTypeAdapter extends ArrayAdapter<TaxTypeDatum> {

    private LayoutInflater layoutInflater;

    public TaxTypeAdapter(Context context, List<TaxTypeDatum> taxTypeDatumList) {
        super(context, 0, taxTypeDatumList);
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        // holder pattern
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = layoutInflater.inflate(R.layout.tax_adapter_taxtype, null);
            holder.setTvAddTaxType((TextView) convertView.findViewById(R.id.tvAddTaxType));
            holder.setTvTaxType((TextView) convertView.findViewById(R.id.tvTaxType));
            holder.setTvTaxDescription((TextView) convertView.findViewById(R.id.tvTaxDescription));
            holder.setImgviewEdit((ImageView) convertView.findViewById(R.id.imgviewEdit));
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.getTvAddTaxType().setVisibility(View.GONE);
        holder.getTvTaxType().setVisibility(View.GONE);
        holder.getTvTaxDescription().setVisibility(View.GONE);
        holder.getImgviewEdit().setVisibility(View.GONE);

        final TaxTypeDatum taxTypeDatum = getItem(position);
        if (taxTypeDatum != null) {
            if (taxTypeDatum.getTaxClassId() != null) {
                if (taxTypeDatum.getTaxClassId().getTaxClassName() != null)
                    holder.getTvAddTaxType().setText(taxTypeDatum.getTaxClassId().getTaxClassName());
            }
            if (taxTypeDatum.getTaxTypeName() != null)
                holder.getTvTaxType().setText(taxTypeDatum.getTaxTypeName());
            if (taxTypeDatum.getTaxTypeDesc() != null)
                holder.getTvTaxDescription().setText(taxTypeDatum.getTaxTypeDesc());
            holder.getTvAddTaxType().setVisibility(View.VISIBLE);
            holder.getTvTaxType().setVisibility(View.VISIBLE);
            holder.getTvTaxDescription().setVisibility(View.VISIBLE);
            holder.getImgviewEdit().setVisibility(View.VISIBLE);
        }

        holder.getImgviewEdit().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ListView) parent).performItemClick(v, position, 0);
            }
        });
        return convertView;
    }

    public class Holder {
        TextView tvAddTaxType;
        TextView tvTaxType;
        TextView tvTaxDescription;
        ImageView imgviewEdit;

        public TextView getTvAddTaxType() {
            return tvAddTaxType;
        }

        public void setTvAddTaxType(TextView tvAddTaxType) {
            this.tvAddTaxType = tvAddTaxType;
        }

        public TextView getTvTaxType() {
            return tvTaxType;
        }

        public void setTvTaxType(TextView tvTaxType) {
            this.tvTaxType = tvTaxType;
        }

        public TextView getTvTaxDescription() {
            return tvTaxDescription;
        }

        public void setTvTaxDescription(TextView tvTaxDescription) {
            this.tvTaxDescription = tvTaxDescription;
        }

        public ImageView getImgviewEdit() {
            return imgviewEdit;
        }

        public void setImgviewEdit(ImageView imgviewEdit) {
            this.imgviewEdit = imgviewEdit;
        }
    }
}
