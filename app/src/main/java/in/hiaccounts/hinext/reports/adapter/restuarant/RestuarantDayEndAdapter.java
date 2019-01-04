package in.hiaccounts.hinext.reports.adapter.restuarant;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import in.hiaccounts.R;
import in.hiaccounts.hinext.reports.model.restuarant.Restaurant_Day_EndData;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;


public class RestuarantDayEndAdapter extends ArrayAdapter<Restaurant_Day_EndData> {
    private LayoutInflater layoutInflater;
    private Activity activity;
    private ServiceHandler serviceHandler;
    private boolean isInternetPresent = false;
    private String serverUrl;


    public RestuarantDayEndAdapter(Activity context, List<Restaurant_Day_EndData> objectList) {
        super(context, 0,objectList);
        layoutInflater = LayoutInflater.from(context);
        activity = (Activity) context;
        serviceHandler = new ServiceHandler(activity);
        serverUrl= UtilView.getUrl(activity);
    }




    public View getView(final int position, View convertView, final ViewGroup parent) {
        RestuarantDayEndAdapter.Holder holder = null;
        if(convertView == null){
            holder = new RestuarantDayEndAdapter.Holder();
            convertView = layoutInflater.inflate(R.layout.report_restuarant_day_end_view, null);
            holder.setTvSalesDate((TextView) convertView.findViewById(R.id.tvSalesDate));
            holder.setTvTotalAmount((TextView) convertView.findViewById(R.id.tvTotalAmount));
            holder.setTvNoOfItems((TextView) convertView.findViewById(R.id.tvNoOfItems));
            holder.setTvEdit((TextView) convertView.findViewById(R.id.tvEdit));
            holder.setTvImgviewEdit((ImageView) convertView.findViewById(R.id.imgviewEdit));
            convertView.setTag(holder);
        }else {
            holder = (RestuarantDayEndAdapter.Holder) convertView.getTag();
        }
        holder.getTvSalesDate().setVisibility(View.GONE);
        holder.getTvTotalAmount().setVisibility(View.GONE);
        holder.getTvNoOfItems().setVisibility(View.GONE);
        holder.getTvEdit().setVisibility(View.GONE);
        holder.getTvImgviewEdit().setVisibility(View.GONE);

        final Restaurant_Day_EndData restaurantDayEndData = getItem(position);
        if (restaurantDayEndData != null) {

            if (restaurantDayEndData.getDate() != null){
                long foo = restaurantDayEndData.getDate();

                Date date = new Date(foo);
                DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                holder.getTvSalesDate().setText(""+formatter.format(date));
            }


            if (restaurantDayEndData.getAmount() != null)
                holder.getTvTotalAmount().setText(""+restaurantDayEndData.getAmount());

            if (restaurantDayEndData.getTotalItems() != null)
                holder.getTvNoOfItems().setText(""+restaurantDayEndData.getTotalItems());


            holder.getTvSalesDate().setVisibility(View.VISIBLE);
            holder.getTvTotalAmount().setVisibility(View.VISIBLE);
            holder.getTvNoOfItems().setVisibility(View.VISIBLE);
            holder.getTvImgviewEdit().setVisibility(View.VISIBLE);

            holder.getTvImgviewEdit().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ((ListView) parent).performItemClick(v, position, 0);

                }
            });
        }


        return convertView;
    }



    private class Holder{
        TextView tvSalesDate;
        TextView tvTotalAmount;
        TextView tvNoOfItems;
        TextView tvEdit;
        ImageView tvImgviewEdit;

        public ImageView getTvImgviewEdit() {
            return tvImgviewEdit;
        }

        public void setTvImgviewEdit(ImageView tvImgviewEdit) {
            this.tvImgviewEdit = tvImgviewEdit;
        }

        public TextView getTvSalesDate() {
            return tvSalesDate;
        }

        public void setTvSalesDate(TextView tvSalesDate) {
            this.tvSalesDate = tvSalesDate;
        }

        public TextView getTvTotalAmount() {
            return tvTotalAmount;
        }

        public void setTvTotalAmount(TextView tvTotalAmount) {
            this.tvTotalAmount = tvTotalAmount;
        }

        public TextView getTvNoOfItems() {
            return tvNoOfItems;
        }

        public void setTvNoOfItems(TextView tvNoOfItems) {
            this.tvNoOfItems = tvNoOfItems;
        }

        public TextView getTvEdit() {
            return tvEdit;
        }

        public void setTvEdit(TextView tvEdit) {
            this.tvEdit = tvEdit;
        }
    }
}
