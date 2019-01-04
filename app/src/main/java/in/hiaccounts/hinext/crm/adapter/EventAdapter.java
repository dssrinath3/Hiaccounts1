package in.hiaccounts.hinext.crm.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

import in.hiaccounts.R;
import in.hiaccounts.hinext.crm.activity.Activity_AddEvent;
import in.hiaccounts.hinext.crm.model.event.SelectEventData;
import in.hiaccounts.utility.Constant;

/**
 * Created by administrator on 21/12/17.
 */

public class EventAdapter extends ArrayAdapter<SelectEventData> {
    private LayoutInflater layoutInflater;
    private Activity activity;
    List<SelectEventData> eventDataList;

    public EventAdapter(Activity activity,List<SelectEventData> eventDataList) {
        super(activity, 0,eventDataList);
        this.activity = activity;
        this.eventDataList = eventDataList;
        layoutInflater = LayoutInflater.from(activity);
    }
    public View getView(final int position, View convertView, final ViewGroup parent) {
        Holder holder = null;
        if(convertView == null){
            holder = new Holder();
            convertView = layoutInflater.inflate(R.layout.crm_adapter_eventview,null);
            holder.setTv_eventName((TextView) convertView.findViewById(R.id.tv_EventName));
            holder.setTv_eventDate((TextView) convertView.findViewById(R.id.tv_EventDate));
            holder.setTv_AssignedTo((TextView) convertView.findViewById(R.id.tv_AssignedTo));
            holder.setTv_Priority((TextView) convertView.findViewById(R.id.tv_Priority));
            holder.setTv_edit((TextView) convertView.findViewById(R.id.tv_Edit));
            holder.setImageViewEdit((ImageView) convertView.findViewById(R.id.imgviewEdit));


            convertView.setTag(holder);
        }
        else
        {
            holder = (Holder) convertView.getTag();
        }


        holder.getTv_eventName().setVisibility(View.GONE);
        holder.getTv_eventDate().setVisibility(View.GONE);
        holder.getTv_AssignedTo().setVisibility(View.GONE);
        holder.getTv_Priority().setVisibility(View.GONE);
        holder.getTv_edit().setVisibility(View.GONE);
        holder.getImageViewEdit().setVisibility(View.GONE);

        final SelectEventData selectEventData = getItem(position);
        if(selectEventData != null){
            if (selectEventData.getEventName() != null) {
                holder.getTv_eventName().setText(selectEventData.getEventName());
            }
            if (selectEventData.getEventDate() != null){
                Calendar calendar1 = Calendar.getInstance();
                calendar1.setTimeInMillis(Long.parseLong(selectEventData.getEventDate()));
                int year1 = calendar1.get(Calendar.YEAR);
                int month1 = calendar1.get(Calendar.MONTH);
                final int day1 = calendar1.get(Calendar.DAY_OF_MONTH);
                String eventDate = String.valueOf(new StringBuilder().append(year1).append("-").append(month1 + 1).append("-").append(day1));

                holder.getTv_eventDate().setText(""+eventDate);
            }


            if (selectEventData.getEmployeeName() != null)
                holder.getTv_AssignedTo().setText(selectEventData.getEmployeeName());

            if (selectEventData.getPriority() != null)
                holder.getTv_Priority().setText(selectEventData.getPriority());




            holder.getTv_eventName().setVisibility(View.VISIBLE);
            holder.getTv_eventDate().setVisibility(View.VISIBLE);
            holder.getTv_AssignedTo().setVisibility(View.VISIBLE);
            holder.getTv_Priority().setVisibility(View.VISIBLE);
            holder.getTv_edit().setVisibility(View.GONE);
            holder.getImageViewEdit().setVisibility(View.VISIBLE);
        }

        holder.getImageViewEdit().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectEventData != null) {
                    Intent intent = new Intent(activity, Activity_AddEvent.class);
                    intent.putExtra("eventData", selectEventData);
                    intent.putExtra("callingFor", Constant.CALL_EDITEVENT);
                    activity.startActivity(intent);
                }
            }
        });
        return convertView;
    }

    public class Holder{
        TextView tv_eventName;
        TextView tv_eventDate;
        TextView tv_AssignedTo;
        TextView tv_Priority;
        TextView tv_edit;
        ImageView imageViewEdit;

        public TextView getTv_eventName() {
            return tv_eventName;
        }

        public void setTv_eventName(TextView tv_eventName) {
            this.tv_eventName = tv_eventName;
        }

        public TextView getTv_eventDate() {
            return tv_eventDate;
        }

        public void setTv_eventDate(TextView tv_eventDate) {
            this.tv_eventDate = tv_eventDate;
        }

        public TextView getTv_AssignedTo() {
            return tv_AssignedTo;
        }

        public void setTv_AssignedTo(TextView tv_AssignedTo) {
            this.tv_AssignedTo = tv_AssignedTo;
        }

        public TextView getTv_Priority() {
            return tv_Priority;
        }

        public void setTv_Priority(TextView tv_Priority) {
            this.tv_Priority = tv_Priority;
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
