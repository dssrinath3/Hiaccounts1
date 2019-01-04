package in.hiaccounts.hinext.crm.adapter;

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
import in.hiaccounts.hinext.crm.activity.Activity_AddLeads;
import in.hiaccounts.hinext.crm.model.leads.SelectLeadsData;
import in.hiaccounts.utility.Constant;

/**
 * Created by administrator on 20/12/17.
 */

public class LeadsAdapter extends ArrayAdapter<SelectLeadsData> {
    private LayoutInflater layoutInflater;
    private Activity activity;
    List<SelectLeadsData> leadsDataList;

    public LeadsAdapter(Activity activity,List<SelectLeadsData> leadsDataList) {
        super(activity, 0,leadsDataList);
        this.activity = activity;
        this.leadsDataList = leadsDataList;
        layoutInflater = LayoutInflater.from(activity);
    }

    public View getView(final int position, View convertView, final ViewGroup parent) {
        Holder holder = null;
        if(convertView == null){
            holder = new Holder();
            convertView = layoutInflater.inflate(R.layout.crm_adapter_leadsview,null);
            holder.setTv_name((TextView) convertView.findViewById(R.id.tv_Name));
            holder.setTv_subject((TextView) convertView.findViewById(R.id.tv_Subject));
            holder.setTv_description((TextView) convertView.findViewById(R.id.tv_Description));
            holder.setTv_edit((TextView) convertView.findViewById(R.id.tv_Edit));
            holder.setImageViewEdit((ImageView) convertView.findViewById(R.id.imgviewEdit));


            convertView.setTag(holder);
        }
        else
        {
            holder = (Holder) convertView.getTag();
        }


        holder.getTv_name().setVisibility(View.GONE);
        holder.getTv_description().setVisibility(View.GONE);
        holder.getTv_subject().setVisibility(View.GONE);
        holder.getTv_edit().setVisibility(View.GONE);
        holder.getImageViewEdit().setVisibility(View.GONE);

        final SelectLeadsData selectLeadsData = getItem(position);
        if(selectLeadsData != null){
            if (selectLeadsData.getFullName() != null) {
                holder.getTv_name().setText(selectLeadsData.getFullName());
            }
            if (selectLeadsData.getSubject() != null)
                holder.getTv_subject().setText(selectLeadsData.getSubject());

            if (selectLeadsData.getDescription() != null)
                holder.getTv_description().setText(selectLeadsData.getDescription());





            holder.getTv_name().setVisibility(View.VISIBLE);
            holder.getTv_description().setVisibility(View.VISIBLE);
            holder.getTv_subject().setVisibility(View.VISIBLE);
            holder.getTv_edit().setVisibility(View.GONE);
            holder.getImageViewEdit().setVisibility(View.VISIBLE);
        }

        holder.getImageViewEdit().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectLeadsData != null) {
                    Intent intent = new Intent(activity, Activity_AddLeads.class);
                    intent.putExtra("leadsData", selectLeadsData);
                    intent.putExtra("callingFor", Constant.CALL_EDITLEADS);
                    activity.startActivity(intent);
                }
            }
        });
        return convertView;
    }

    public class Holder{
        TextView tv_name;
        TextView tv_description;
        TextView tv_subject;
        TextView tv_edit;
        ImageView imageViewEdit;

        public TextView getTv_name() {
            return tv_name;
        }

        public void setTv_name(TextView tv_name) {
            this.tv_name = tv_name;
        }

        public TextView getTv_description() {
            return tv_description;
        }

        public void setTv_description(TextView tv_description) {
            this.tv_description = tv_description;
        }

        public TextView getTv_subject() {
            return tv_subject;
        }

        public void setTv_subject(TextView tv_subject) {
            this.tv_subject = tv_subject;
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
