package in.hiaccounts.hinext.controlpanel.adapter;


import android.app.Activity;
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
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.currency.CurrencyDatum;


/**
 * Custom adapter with "View Holder Pattern".
 *
 * @author danielme.com
 */
public class CurrencyAdapter extends ArrayAdapter<Object> {
    private LayoutInflater layoutInflater;
    private Activity activity;

    public CurrencyAdapter(Context context, List<Object> objects) {
        super(context, 0, objects);
        layoutInflater = LayoutInflater.from(context);
        activity = (Activity) context;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        // holder pattern
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();

            convertView = layoutInflater.inflate(R.layout.controlpanel_adapter_currency, null);

            holder.setTvCurrencyCode((TextView) convertView.findViewById(R.id.tvCurrencyCode));
            holder.setTvCurrencyName((TextView) convertView.findViewById(R.id.tvCurrencyName));
            holder.setTvCurrencySymbol((TextView) convertView.findViewById(R.id.tvCurrencySymbol));
            holder.setTvEdit((TextView) convertView.findViewById(R.id.tvEdit));
            holder.setImageViewEdit((ImageView) convertView.findViewById(R.id.imgviewEdit));
            holder.setImgviewDelete((ImageView) convertView.findViewById(R.id.imgviewDelete));

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.getTvEdit().setVisibility(View.GONE);
        holder.getImageViewEdit().setVisibility(View.GONE);
        holder.getImgviewDelete().setVisibility(View.GONE);
        holder.getTvCurrencyCode().setVisibility(View.GONE);
        holder.getTvCurrencyName().setVisibility(View.GONE);
        holder.getTvCurrencySymbol().setVisibility(View.GONE);
        final Object obj = getItem(position);

        if (obj instanceof CurrencyDatum) {
            final CurrencyDatum currencyData = (CurrencyDatum) obj;

            if (currencyData != null) {
                if (currencyData.getCurrencyCode() != null)
                    holder.getTvCurrencyCode().setText(currencyData.getCurrencyCode());
                if (currencyData.getCurrencyName() != null)
                    holder.getTvCurrencyName().setText(currencyData.getCurrencyName());
                if (currencyData.getCurrencySymbol() != null)
                    holder.getTvCurrencySymbol().setText(currencyData.getCurrencySymbol());

                holder.getImageViewEdit().setVisibility(View.VISIBLE);
                holder.getImgviewDelete().setVisibility(View.GONE);
                holder.getTvCurrencyCode().setVisibility(View.VISIBLE);
                holder.getTvCurrencyName().setVisibility(View.VISIBLE);
                holder.getTvCurrencySymbol().setVisibility(View.VISIBLE);
            }

            holder.getImageViewEdit().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((ListView) parent).performItemClick(v, position, 0);

                }
            });


        }
        return convertView;
    }


    public class Holder {


        TextView tvCurrencyCode;
        TextView tvCurrencyName;
        TextView tvCurrencySymbol;
        TextView tvEdit;

        ImageView imageViewEdit;
        ImageView imgviewDelete;

        public TextView getTvCurrencyCode() {
            return tvCurrencyCode;
        }

        public void setTvCurrencyCode(TextView tvCurrencyCode) {
            this.tvCurrencyCode = tvCurrencyCode;
        }

        public TextView getTvCurrencyName() {
            return tvCurrencyName;
        }

        public void setTvCurrencyName(TextView tvCurrencyName) {
            this.tvCurrencyName = tvCurrencyName;
        }

        public TextView getTvCurrencySymbol() {
            return tvCurrencySymbol;
        }

        public void setTvCurrencySymbol(TextView tvCurrencySymbol) {
            this.tvCurrencySymbol = tvCurrencySymbol;
        }

        public TextView getTvEdit() {
            return tvEdit;
        }

        public void setTvEdit(TextView tvEdit) {
            this.tvEdit = tvEdit;
        }

        public ImageView getImageViewEdit() {
            return imageViewEdit;
        }

        public void setImageViewEdit(ImageView imageViewEdit) {
            this.imageViewEdit = imageViewEdit;
        }

        public ImageView getImgviewDelete() {
            return imgviewDelete;
        }

        public void setImgviewDelete(ImageView imgviewDelete) {
            this.imgviewDelete = imgviewDelete;
        }
    }
}
