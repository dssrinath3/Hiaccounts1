package in.hiaccounts.hinext.inventory.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import in.hiaccounts.R;
import in.hiaccounts.hinext.inventory.activity.Activity_AddAdvanceDiscountConfiguration;
import in.hiaccounts.hinext.inventory.model.advancediscountconfiguration.AdvanceDiscountConfigSelectData;
import in.hiaccounts.utility.Constant;

/**
 * Created by shrinath on 12/15/2017.
 */

public class AdvanceDiscountConfigAdapter extends ArrayAdapter<AdvanceDiscountConfigSelectData> {

    private LayoutInflater layoutInflater;
    private Activity activity;
    List<AdvanceDiscountConfigSelectData> advDiscountConfigSelectDataList;

    public AdvanceDiscountConfigAdapter(Activity activity,List<AdvanceDiscountConfigSelectData> advDiscountConfigSelectDataList) {
        super(activity, 0,advDiscountConfigSelectDataList);
        this.activity = activity;
        this.advDiscountConfigSelectDataList = advDiscountConfigSelectDataList;
        layoutInflater = LayoutInflater.from(activity);
    }


    public View getView(final int position,View convertView,final ViewGroup parent) {
        Holder holder = null;
        if(convertView == null){
            holder = new Holder();
            convertView = layoutInflater.inflate(R.layout.inventory_adapter_advance_discount_configurationview,null);
            holder.setTv_customerName((TextView) convertView.findViewById(R.id.tv_customerName));
            holder.setTv_itemName((TextView) convertView.findViewById(R.id.tv_itemName));
            holder.setTv_promotionName((TextView) convertView.findViewById(R.id.tv_promotionName));
            holder.setTv_edit((TextView) convertView.findViewById(R.id.tv_Edit));
            holder.setImageViewEdit((ImageView) convertView.findViewById(R.id.imgviewEdit));


            convertView.setTag(holder);
        }
        else
        {
            holder = (Holder) convertView.getTag();
        }


        holder.getTv_customerName().setVisibility(View.GONE);
        holder.getTv_itemName().setVisibility(View.GONE);
        holder.getTv_promotionName().setVisibility(View.GONE);
        holder.getTv_edit().setVisibility(View.GONE);
        holder.getImageViewEdit().setVisibility(View.GONE);

        final AdvanceDiscountConfigSelectData advConfigSelectData = getItem(position);
        if(advConfigSelectData != null){
            if (advConfigSelectData.getCustomerName() != null) {
                holder.getTv_customerName().setText(advConfigSelectData.getCustomerName());
            }

            if (advConfigSelectData.getItemName().toString() != null)
                holder.getTv_itemName().setText(advConfigSelectData.getItemName());

            if (advConfigSelectData.getPromotionName() != null)
                holder.getTv_promotionName().setText(advConfigSelectData.getPromotionName());



            holder.getTv_customerName().setVisibility(View.VISIBLE);
            holder.getTv_itemName().setVisibility(View.VISIBLE);
            holder.getTv_promotionName().setVisibility(View.VISIBLE);
            holder.getTv_edit().setVisibility(View.GONE);
            holder.getImageViewEdit().setVisibility(View.VISIBLE);
        }

        holder.getImageViewEdit().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (advConfigSelectData != null) {
                    Intent intent = new Intent(activity, Activity_AddAdvanceDiscountConfiguration.class);
                    intent.putExtra("advanceDiscountConfigData", advConfigSelectData);
                    intent.putExtra("callingFor", Constant.CALL_EDITADVANCESDISCOUNTCONFIG);
                    activity.startActivity(intent);
                }
            }
        });
        return convertView;
    }

    public class Holder{
        TextView tv_customerName;
        TextView tv_itemName;
        TextView tv_promotionName;
        TextView tv_edit;
        ImageView imageViewEdit;

        public TextView getTv_customerName() {
            return tv_customerName;
        }

        public void setTv_customerName(TextView tv_customerName) {
            this.tv_customerName = tv_customerName;
        }

        public TextView getTv_itemName() {
            return tv_itemName;
        }

        public void setTv_itemName(TextView tv_itemName) {
            this.tv_itemName = tv_itemName;
        }

        public TextView getTv_promotionName() {
            return tv_promotionName;
        }

        public void setTv_promotionName(TextView tv_promotionName) {
            this.tv_promotionName = tv_promotionName;
        }

        public TextView getTv_edit() {
            return tv_edit;
        }

        public void setTv_edit(TextView tv_edit) {
            this.tv_edit = tv_edit;
        }

        public ImageView getImageViewEdit() {
            return imageViewEdit;
        }

        public void setImageViewEdit(ImageView imageViewEdit) {
            this.imageViewEdit = imageViewEdit;
        }
    }

}
