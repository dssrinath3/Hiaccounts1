package in.hiaccounts.hinext.inventory.adapter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import in.hiaccounts.R;
import in.hiaccounts.hinext.item.activity.Activity_EditItem;
import in.hiaccounts.hinext.item.model.ItemModelData;
import in.hiaccounts.hinext.item.model.SelectedItemsList;
import in.hiaccounts.utility.Constant;


/**
 * Custom adapter with "View Holder Pattern".
 *
 * @author Prateek
 */
public class ViewItemAdapter extends ArrayAdapter<Object> {
    private LayoutInflater layoutInflater;
    Activity activity;

    public ViewItemAdapter(Context context, List<Object> objects) {
        super(context, 0, objects);
        layoutInflater = LayoutInflater.from(context);
        activity = (Activity) context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // holder pattern
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();

            convertView = layoutInflater.inflate(R.layout.inventory_adapter_itemview, null);

            //   holder.setTv_itemcategory((TextView) convertView.findViewById(R.id.tv_itemcategory));
            holder.setTv_itmecode((TextView) convertView.findViewById(R.id.tv_itmecode));
            holder.setTv_itemname((TextView) convertView.findViewById(R.id.tv_itemname));
            holder.setTv_itemdescription((TextView) convertView.findViewById(R.id.tv_itemdescription));
            holder.setTv_itemedit((TextView) convertView.findViewById(R.id.tv_itemEdit));
            holder.setImageViewEdit((ImageView) convertView.findViewById(R.id.imgviewEdit));
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.getTv_itmecode().setVisibility(View.GONE);
        holder.getTv_itemname().setVisibility(View.GONE);
        holder.getTv_itemdescription().setVisibility(View.GONE);
        holder.getTv_itemedit().setVisibility(View.GONE);
        holder.getImageViewEdit().setVisibility(View.GONE);
        Object obj = getItem(position);
        if (obj instanceof ItemModelData) {
            final ItemModelData item = (ItemModelData) obj;
            if (item != null) {
                if (item.getItemName() != null)
                    holder.getTv_itemname().setText(item.getItemName());
                if (item.getItemCode() != null)
                    holder.getTv_itmecode().setText(item.getItemCode());
                if (item.getItemCode() != null)
                    holder.getTv_itemdescription().setText("" + item.getItemCode());

                holder.getTv_itmecode().setVisibility(View.VISIBLE);
                holder.getTv_itemname().setVisibility(View.VISIBLE);
                holder.getTv_itemdescription().setVisibility(View.VISIBLE);
                holder.getTv_itemedit().setVisibility(View.GONE);
                holder.getImageViewEdit().setVisibility(View.VISIBLE);

            }
            holder.getImageViewEdit().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (item != null) {
                        Intent intent = new Intent(activity, Activity_EditItem.class);
                        intent.putExtra("item", item);
                        intent.putExtra("callingfrom", Constant.NAVIGATION_INVENTORY_ITEM_EDIT);
                        activity.startActivityForResult(intent, Constant.RESQUSTCODE_ADDITEM);
                    }
                }
            });
        }
        return convertView;
    }

    public class Holder {
        TextView tv_itemcategory;
        TextView tv_itmecode;
        TextView tv_itemname;
        TextView tv_itemdescription;
        TextView tv_itemedit;
        ImageView imageViewEdit;

        public ImageView getImageViewEdit() {
            return imageViewEdit;
        }

        public void setImageViewEdit(ImageView imageViewEdit) {
            this.imageViewEdit = imageViewEdit;
        }

        public TextView getTv_itemedit() {
            return tv_itemedit;
        }

        public void setTv_itemedit(TextView tv_itemedit) {
            this.tv_itemedit = tv_itemedit;
        }

        public TextView getTv_itemcategory() {
            return tv_itemcategory;
        }

        public void setTv_itemcategory(TextView tv_itemcategory) {
            this.tv_itemcategory = tv_itemcategory;
        }

        public TextView getTv_itmecode() {
            return tv_itmecode;
        }

        public void setTv_itmecode(TextView tv_itmecode) {
            this.tv_itmecode = tv_itmecode;
        }

        public TextView getTv_itemname() {
            return tv_itemname;
        }

        public void setTv_itemname(TextView tv_itemname) {
            this.tv_itemname = tv_itemname;
        }

        public TextView getTv_itemdescription() {
            return tv_itemdescription;
        }

        public void setTv_itemdescription(TextView tv_itemdescription) {
            this.tv_itemdescription = tv_itemdescription;
        }
    }
}
