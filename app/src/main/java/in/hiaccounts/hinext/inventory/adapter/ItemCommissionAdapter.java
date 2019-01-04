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
import in.hiaccounts.hinext.inventory.model.itemcommission.ItemCommissionSelectData;

/**
 * Created by shrinath on 12/13/2017.
 */

public class ItemCommissionAdapter extends ArrayAdapter<ItemCommissionSelectData> {
    List<ItemCommissionSelectData> itemCommissionSelectDataList;
    private LayoutInflater layoutInflater;
    private Activity activity;

    public ItemCommissionAdapter(Activity activity,List<ItemCommissionSelectData> itemCommissionSelectDataList) {
        super(activity, 0,itemCommissionSelectDataList);
        this.activity = activity;
        this.itemCommissionSelectDataList = itemCommissionSelectDataList;
        layoutInflater = LayoutInflater.from(activity);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup viewGroup) {
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = layoutInflater.inflate(R.layout.inventory_adapter_item_commissionview, null);
            holder.setTv_itemName((TextView) convertView.findViewById(R.id.tv_itemName));
            holder.setTv_itemCommissionType((TextView) convertView.findViewById(R.id.tv_itemCommissionType));
            holder.setTv_itemCommissionValue((TextView) convertView.findViewById(R.id.tv_itemCommissionValue));
            holder.setTv_itemeEdit((TextView) convertView.findViewById(R.id.tv_itemCommissionEdit));
            holder.setImageViewEdit((ImageView) convertView.findViewById(R.id.imgviewEdit));
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.getTv_itemName().setVisibility(View.GONE);
        holder.getTv_itemCommissionType().setVisibility(View.GONE);
        holder.getTv_itemCommissionValue().setVisibility(View.GONE);
        holder.getTv_itemeEdit().setVisibility(View.GONE);
        holder.getImageViewEdit().setVisibility(View.GONE);

        final ItemCommissionSelectData itemCommissionSelectData = getItem(position);
        if (itemCommissionSelectData != null) {
            if (itemCommissionSelectData.getItemName().toString() != null) {
                holder.getTv_itemName().setText(itemCommissionSelectData.getItemName().toString());
            }
            if (itemCommissionSelectData.getCheckBoxValue().toString() != null)
                holder.getTv_itemCommissionType().setText(itemCommissionSelectData.getCheckBoxValue().toString());

            if (itemCommissionSelectData.getAmountordiscount().toString() != null)
                holder.getTv_itemCommissionValue().setText(itemCommissionSelectData.getAmountordiscount().toString());



            holder.getTv_itemName().setVisibility(View.VISIBLE);
            holder.getTv_itemCommissionType().setVisibility(View.VISIBLE);
            holder.getTv_itemCommissionValue().setVisibility(View.VISIBLE);
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
        TextView tv_itemCommissionType;
        TextView tv_itemCommissionValue;
        TextView tv_itemeEdit;
        ImageView imageViewEdit;

        public TextView getTv_itemName() {
            return tv_itemName;
        }

        public void setTv_itemName(TextView tv_itemName) {
            this.tv_itemName = tv_itemName;
        }

        public TextView getTv_itemCommissionType() {
            return tv_itemCommissionType;
        }

        public void setTv_itemCommissionType(TextView tv_itemCommissionType) {
            this.tv_itemCommissionType = tv_itemCommissionType;
        }

        public TextView getTv_itemCommissionValue() {
            return tv_itemCommissionValue;
        }

        public void setTv_itemCommissionValue(TextView tv_itemCommissionValue) {
            this.tv_itemCommissionValue = tv_itemCommissionValue;
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
