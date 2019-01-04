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
import in.hiaccounts.hinext.inventory.activity.Activity_EditAssests;
import in.hiaccounts.hinext.inventory.model.assests.AssestsSelectData;
import in.hiaccounts.utility.Constant;

/**
 * Created by shrinath on 11/30/2017.
 */

public class AssestsAdapter extends ArrayAdapter<AssestsSelectData> {

    List<AssestsSelectData> assestsDataList;
    private LayoutInflater layoutInflater;
    private Activity activity;

    public AssestsAdapter(Activity activity, List<AssestsSelectData> assestsDataList) {
        super(activity,0, assestsDataList);
        this.activity = activity;
        this.assestsDataList = assestsDataList;
        layoutInflater = LayoutInflater.from(activity);
    }



    @Override
    public View getView(final int position, View convertView, final ViewGroup viewGroup) {
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = layoutInflater.inflate(R.layout.inventory_adapter_assestsview, null);
            holder.setTv_assestscode((TextView) convertView.findViewById(R.id.tv_assestscode));
            holder.setTv_assestsname((TextView) convertView.findViewById(R.id.tv_assestsname));
            holder.setTv_assestsstock((TextView) convertView.findViewById(R.id.tv_assestsstock));
            holder.setTv_assestsedit((TextView) convertView.findViewById(R.id.tv_assestsEdit));
            holder.setImageViewEdit((ImageView) convertView.findViewById(R.id.imgviewEdit));
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.getTv_assestscode().setVisibility(View.GONE);
        holder.getTv_assestsname().setVisibility(View.GONE);
        holder.getTv_assestsstock().setVisibility(View.GONE);
        holder.getTv_assestsedit().setVisibility(View.GONE);
        holder.getImageViewEdit().setVisibility(View.GONE);

        final AssestsSelectData assestsData = getItem(position);
        if (assestsData != null) {
            if (assestsData.getAssestCode() != null)
                holder.getTv_assestscode().setText(assestsData.getAssestCode());
            if (assestsData.getAssestName() != null)
                holder.getTv_assestsname().setText(assestsData.getAssestName());
            if (assestsData.getStock() != null)
                holder.getTv_assestsstock().setText(assestsData.getStock().toString());


            holder.getTv_assestscode().setVisibility(View.VISIBLE);
            holder.getTv_assestsname().setVisibility(View.VISIBLE);
            holder.getTv_assestsstock().setVisibility(View.VISIBLE);
            holder.getTv_assestsedit().setVisibility(View.GONE);
            holder.getImageViewEdit().setVisibility(View.VISIBLE);
        }

        holder.getImageViewEdit().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (assestsData != null) {
                    Intent intent = new Intent(activity, Activity_EditAssests.class);
                    intent.putExtra("assests", assestsData);
                    intent.putExtra("callingfrom", Constant.NAVIGATION_INVENTORY_ASSESTS_EDIT);
                    activity.startActivity(intent);
                }
            }
        });


        return convertView;
    }

    public class Holder{
        TextView tv_assestscategory;
        TextView tv_assestscode;
        TextView tv_assestsname;
        TextView tv_assestsstock;
        TextView tv_assestsedit;
        ImageView imageViewEdit;

        public TextView getTv_assestscategory() {
            return tv_assestscategory;
        }

        public void setTv_assestscategory(TextView tv_assestscategory) {
            this.tv_assestscategory = tv_assestscategory;
        }

        public TextView getTv_assestscode() {
            return tv_assestscode;
        }

        public void setTv_assestscode(TextView tv_assestscode) {
            this.tv_assestscode = tv_assestscode;
        }

        public TextView getTv_assestsname() {
            return tv_assestsname;
        }

        public void setTv_assestsname(TextView tv_assestsname) {
            this.tv_assestsname = tv_assestsname;
        }

        public TextView getTv_assestsstock() {
            return tv_assestsstock;
        }

        public void setTv_assestsstock(TextView tv_assestsstock) {
            this.tv_assestsstock = tv_assestsstock;
        }

        public TextView getTv_assestsedit() {
            return tv_assestsedit;
        }

        public void setTv_assestsedit(TextView tv_assestsedit) {
            this.tv_assestsedit = tv_assestsedit;
        }

        public ImageView getImageViewEdit() {
            return imageViewEdit;
        }

        public void setImageViewEdit(ImageView imageViewEdit) {
            this.imageViewEdit = imageViewEdit;
        }
    }
}
