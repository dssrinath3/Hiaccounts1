package in.hiaccounts.hinext.reports.adapter.restuarant;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import in.hiaccounts.R;
import in.hiaccounts.hinext.reports.model.restuarant.CancelledItems;

public class CancelledItemsAdapter extends ArrayAdapter<CancelledItems> {
    private LayoutInflater layoutInflater;
    private Activity activity;
    List<CancelledItems> empDataList;

    public CancelledItemsAdapter(Activity activity,List<CancelledItems> empDataList) {
        super(activity, 0,empDataList);
        this.activity = activity;
        this.empDataList = empDataList;
        layoutInflater = LayoutInflater.from(activity);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        // holder pattern
        CancelledItemsAdapter.Holder holder = null;
        if (convertView == null) {
            holder = new CancelledItemsAdapter.Holder();

            convertView = layoutInflater.inflate(R.layout.report_adapter_cancelleditems_list, null);

            holder.setTvDate((TextView) convertView.findViewById(R.id.tvDate));
            holder.setTvTableName((TextView) convertView.findViewById(R.id.tvTableName));
            holder.setTvWaiterName((TextView) convertView.findViewById(R.id.tvWaiterName));
            holder.setTvTokenNo((TextView) convertView.findViewById(R.id.tvTokenNo));
            holder.setTvCancelledItems((TextView) convertView.findViewById(R.id.tvCancelledItems));

            convertView.setTag(holder);
        } else {
            holder = (CancelledItemsAdapter.Holder) convertView.getTag();
        }


        holder.getTvDate().setVisibility(View.GONE);
        holder.getTvTableName().setVisibility(View.GONE);
        holder.getTvWaiterName().setVisibility(View.GONE);
        holder.getTvTokenNo().setVisibility(View.GONE);
        holder.getTvCancelledItems().setVisibility(View.GONE);

        final CancelledItems categoryData = getItem(position);

        if (categoryData != null) {
            if (categoryData.getDate() != null){
                    long foo = categoryData.getDate();

                    Date date = new Date(foo);
                    DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    holder.getTvDate().setText(""+formatter.format(date));
                }
            if (categoryData.getTableName() != null)
                holder.getTvTableName().setText(categoryData.getTableName());
            if (categoryData.getWaiterName() != null)
                holder.getTvWaiterName().setText(""+categoryData.getWaiterName());
            if (categoryData.getTokenNo() != null)
                holder.getTvTokenNo().setText(""+categoryData.getTokenNo());
            if (categoryData.getItemName() != null)
                holder.getTvCancelledItems().setText(""+categoryData.getItemName());


            holder.getTvDate().setVisibility(View.VISIBLE);
            holder.getTvTableName().setVisibility(View.VISIBLE);
            holder.getTvWaiterName().setVisibility(View.VISIBLE);
            holder.getTvTokenNo().setVisibility(View.VISIBLE);
            holder.getTvCancelledItems().setVisibility(View.VISIBLE);
        }



        return convertView;
    }



    public class Holder {


        TextView tvDate;
        TextView tvTableName;
        TextView tvWaiterName;
        TextView tvTokenNo;
        TextView tvCancelledItems;

        public TextView getTvDate() {
            return tvDate;
        }

        public void setTvDate(TextView tvDate) {
            this.tvDate = tvDate;
        }

        public TextView getTvTableName() {
            return tvTableName;
        }

        public void setTvTableName(TextView tvTableName) {
            this.tvTableName = tvTableName;
        }

        public TextView getTvWaiterName() {
            return tvWaiterName;
        }

        public void setTvWaiterName(TextView tvWaiterName) {
            this.tvWaiterName = tvWaiterName;
        }

        public TextView getTvTokenNo() {
            return tvTokenNo;
        }

        public void setTvTokenNo(TextView tvTokenNo) {
            this.tvTokenNo = tvTokenNo;
        }

        public TextView getTvCancelledItems() {
            return tvCancelledItems;
        }

        public void setTvCancelledItems(TextView tvCancelledItems) {
            this.tvCancelledItems = tvCancelledItems;
        }
    }

}
