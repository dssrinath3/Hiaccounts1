package in.hiaccounts.hinext.inventory.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import in.hiaccounts.R;
import in.hiaccounts.hinext.inventory.model.salesdiscountconfiguration.SalesDiscountConfigDataDatum;
import in.hiaccounts.hinext.inventory.model.salesdiscountconfiguration.SalesDiscountConfigSelectData;

/**
 * Created by shrinath on 12/14/2017.
 */

public class SalesDiscountConfigAdapter extends ArrayAdapter<SalesDiscountConfigDataDatum> {
    List<SalesDiscountConfigDataDatum> salesDiscountConfigSelectDataList;
    private LayoutInflater layoutInflater;
    private Activity activity;

    public SalesDiscountConfigAdapter(Activity activity, List<SalesDiscountConfigDataDatum> salesDiscountConfigSelectDataList) {
        super(activity, 0,salesDiscountConfigSelectDataList);
        this.activity = activity;
        this.salesDiscountConfigSelectDataList = salesDiscountConfigSelectDataList;
        layoutInflater = LayoutInflater.from(activity);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup viewGroup) {
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = layoutInflater.inflate(R.layout.inventory_adapter_sales_discount_configurationview, null);
            holder.setTv_itemName((TextView) convertView.findViewById(R.id.tv_itemName));
            holder.setTv_itemDiscountType((TextView) convertView.findViewById(R.id.tv_discountType));
            holder.setTv_itemDiscountValue((TextView) convertView.findViewById(R.id.tv_discountValue));
            holder.setTv_itemeEdit((TextView) convertView.findViewById(R.id.tv_discountEdit));
            holder.setImageViewEdit((ImageView) convertView.findViewById(R.id.imgviewEdit));
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.getTv_itemName().setVisibility(View.GONE);
        holder.getTv_itemDiscountType().setVisibility(View.GONE);
        holder.getTv_itemDiscountValue().setVisibility(View.GONE);
        holder.getTv_itemeEdit().setVisibility(View.GONE);
        holder.getImageViewEdit().setVisibility(View.GONE);

        final SalesDiscountConfigDataDatum salesDiscountConfigSelectData = getItem(position);
        if (salesDiscountConfigSelectData != null) {
            if (salesDiscountConfigSelectData.getItemName().toString() != null) {
                holder.getTv_itemName().setText(salesDiscountConfigSelectData.getItemName().toString());
            }
            if (salesDiscountConfigSelectData.getCheckBoxValue() != null)
                holder.getTv_itemDiscountType().setText(salesDiscountConfigSelectData.getCheckBoxValue());

            if (salesDiscountConfigSelectData.getAmountordiscount() != null)
                holder.getTv_itemDiscountValue().setText(salesDiscountConfigSelectData.getAmountordiscount().toString());



            holder.getTv_itemName().setVisibility(View.VISIBLE);
            holder.getTv_itemDiscountType().setVisibility(View.VISIBLE);
            holder.getTv_itemDiscountValue().setVisibility(View.VISIBLE);
            holder.getTv_itemeEdit().setVisibility(View.GONE);
            holder.getImageViewEdit().setVisibility(View.VISIBLE);
        }

        holder.getImageViewEdit().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ListView) viewGroup).performItemClick(v, position, 0);

            }
        });


        return convertView;
    }

    public class Holder{
        TextView tv_itemName;
        TextView tv_itemDiscountType;
        TextView tv_itemDiscountValue;
        TextView tv_itemeEdit;
        ImageView imageViewEdit;

        public TextView getTv_itemName() {
            return tv_itemName;
        }

        public void setTv_itemName(TextView tv_itemName) {
            this.tv_itemName = tv_itemName;
        }

        public TextView getTv_itemDiscountType() {
            return tv_itemDiscountType;
        }

        public void setTv_itemDiscountType(TextView tv_itemDiscountType) {
            this.tv_itemDiscountType = tv_itemDiscountType;
        }

        public TextView getTv_itemDiscountValue() {
            return tv_itemDiscountValue;
        }

        public void setTv_itemDiscountValue(TextView tv_itemDiscountValue) {
            this.tv_itemDiscountValue = tv_itemDiscountValue;
        }

        public TextView getTv_itemeEdit() {
            return tv_itemeEdit;
        }

        public void setTv_itemeEdit(TextView tv_itemeEdit) {
            this.tv_itemeEdit = tv_itemeEdit;
        }

        public ImageView getImageViewEdit() {
            return imageViewEdit;
        }

        public void setImageViewEdit(ImageView imageViewEdit) {
            this.imageViewEdit = imageViewEdit;
        }
    }
}
