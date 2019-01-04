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
import in.hiaccounts.hinext.reports.model.restuarant.Restaurant_Day_EndData_Register_Data;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

public class RestuarantDayEndRegisterAdapter extends ArrayAdapter<Restaurant_Day_EndData_Register_Data> {
    private LayoutInflater layoutInflater;
    private Activity activity;
    private ServiceHandler serviceHandler;
    private boolean isInternetPresent = false;
    private String serverUrl;


    public RestuarantDayEndRegisterAdapter(Activity context, List<Restaurant_Day_EndData_Register_Data> objectList) {
        super(context, 0,objectList);
        layoutInflater = LayoutInflater.from(context);
        activity = (Activity) context;
        serviceHandler = new ServiceHandler(activity);
        serverUrl= UtilView.getUrl(activity);
    }




    public View getView(final int position, View convertView, final ViewGroup parent) {
        RestuarantDayEndRegisterAdapter.Holder holder = null;
        if(convertView == null){
            holder = new RestuarantDayEndRegisterAdapter.Holder();
            convertView = layoutInflater.inflate(R.layout.report_restuarant_day_end_register_view, null);
            holder.setTvSalesDate((TextView) convertView.findViewById(R.id.tvSalesDate));
            holder.setTvInvoiceNo((TextView) convertView.findViewById(R.id.tvInvoiceNo));
            holder.setTvCustomerName((TextView) convertView.findViewById(R.id.tvCustomerName));
            holder.setTvEmployeeName((TextView) convertView.findViewById(R.id.tvEmployeeName));
            holder.setTvAmount((TextView) convertView.findViewById(R.id.tvAmount));
            convertView.setTag(holder);
        }else {
            holder = (RestuarantDayEndRegisterAdapter.Holder) convertView.getTag();
        }
        holder.getTvSalesDate().setVisibility(View.GONE);
        holder.getTvCustomerName().setVisibility(View.GONE);
        holder.getTvInvoiceNo().setVisibility(View.GONE);
        holder.getTvEmployeeName().setVisibility(View.GONE);
        holder.getTvAmount().setVisibility(View.GONE);

        final Restaurant_Day_EndData_Register_Data restaurantDayEndData = getItem(position);
        if (restaurantDayEndData != null) {

            if (restaurantDayEndData.getDate() != null){
                long foo = restaurantDayEndData.getDayEndDate();

                Date date = new Date(foo);
                DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                holder.getTvSalesDate().setText(""+formatter.format(date));
            }


            if (restaurantDayEndData.getSqno() != null)
                holder.getTvInvoiceNo().setText(""+restaurantDayEndData.getSqno());

            if (restaurantDayEndData.getCustomerName() != null)
                holder.getTvCustomerName().setText(""+restaurantDayEndData.getCustomerName());

            if (restaurantDayEndData.getEmployeeName() != null)
                holder.getTvEmployeeName().setText(""+restaurantDayEndData.getEmployeeName());

            if (restaurantDayEndData.getTotalAmount() != null)
                holder.getTvAmount().setText(""+restaurantDayEndData.getTotalAmount());

            holder.getTvSalesDate().setVisibility(View.VISIBLE);
            holder.getTvCustomerName().setVisibility(View.VISIBLE);
            holder.getTvInvoiceNo().setVisibility(View.VISIBLE);
            holder.getTvEmployeeName().setVisibility(View.VISIBLE);
            holder.getTvAmount().setVisibility(View.VISIBLE);

        }


        return convertView;
    }



    private class Holder{
        TextView tvSalesDate;
        TextView tvInvoiceNo;
        TextView tvCustomerName;
        TextView tvEmployeeName;
        TextView tvAmount;

        public TextView getTvSalesDate() {
            return tvSalesDate;
        }

        public void setTvSalesDate(TextView tvSalesDate) {
            this.tvSalesDate = tvSalesDate;
        }

        public TextView getTvInvoiceNo() {
            return tvInvoiceNo;
        }

        public void setTvInvoiceNo(TextView tvInvoiceNo) {
            this.tvInvoiceNo = tvInvoiceNo;
        }

        public TextView getTvCustomerName() {
            return tvCustomerName;
        }

        public void setTvCustomerName(TextView tvCustomerName) {
            this.tvCustomerName = tvCustomerName;
        }

        public TextView getTvEmployeeName() {
            return tvEmployeeName;
        }

        public void setTvEmployeeName(TextView tvEmployeeName) {
            this.tvEmployeeName = tvEmployeeName;
        }

        public TextView getTvAmount() {
            return tvAmount;
        }

        public void setTvAmount(TextView tvAmount) {
            this.tvAmount = tvAmount;
        }
    }
}
