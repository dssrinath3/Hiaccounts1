package in.hiaccounts.hinext.reports.adapter.restuarant;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import in.hiaccounts.R;
import in.hiaccounts.hinext.reports.model.restuarant.SalesReportList;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

public class RestuarantItemMonthEndAdapter extends ArrayAdapter<SalesReportList> {
    private LayoutInflater layoutInflater;
    private Activity activity;
    private ServiceHandler serviceHandler;
    private boolean isInternetPresent = false;
    private String serverUrl;


    public RestuarantItemMonthEndAdapter(Activity context, List<SalesReportList> objectList) {
        super(context, 0,objectList);
        layoutInflater = LayoutInflater.from(context);
        activity = (Activity) context;
        serviceHandler = new ServiceHandler(activity);
        serverUrl= UtilView.getUrl(activity);
    }




    public View getView(final int position, View convertView, final ViewGroup parent) {
        RestuarantItemMonthEndAdapter.Holder holder = null;
        if(convertView == null){
            holder = new RestuarantItemMonthEndAdapter.Holder();
            convertView = layoutInflater.inflate(R.layout.report_restuarant_month_end_view, null);
            holder.setTvCategoryName((TextView) convertView.findViewById(R.id.tvCategoryName));
            holder.setTvItemName((TextView) convertView.findViewById(R.id.tvItemName));
            holder.setTvQty((TextView) convertView.findViewById(R.id.tvQty));
            holder.setTvTotalAmount((TextView) convertView.findViewById(R.id.tvTotalAmount));
            convertView.setTag(holder);
        }else {
            holder = (RestuarantItemMonthEndAdapter.Holder) convertView.getTag();
        }
        holder.getTvCategoryName().setVisibility(View.GONE);
        holder.getTvItemName().setVisibility(View.GONE);
        holder.getTvQty().setVisibility(View.GONE);
        holder.getTvTotalAmount().setVisibility(View.GONE);

        final SalesReportList salesReportList = getItem(position);
        if (salesReportList != null) {

            if (salesReportList.getItemCategoryName() != null)
                holder.getTvCategoryName().setText(""+salesReportList.getItemCategoryName());

            if (salesReportList.getItemName() != null)
                holder.getTvItemName().setText(""+salesReportList.getItemName());

            if (salesReportList.getQty() != null)
                holder.getTvQty().setText(""+salesReportList.getQty());

            if (salesReportList.getInvoiceAmount() != null)
                holder.getTvTotalAmount().setText(""+salesReportList.getInvoiceAmount());



            holder.getTvCategoryName().setVisibility(View.VISIBLE);
            holder.getTvItemName().setVisibility(View.VISIBLE);
            holder.getTvQty().setVisibility(View.VISIBLE);
            holder.getTvTotalAmount().setVisibility(View.VISIBLE);
        }


        return convertView;
    }



    private class Holder{
        TextView tvCategoryName;
        TextView tvItemName;
        TextView tvTotalAmount;
        TextView tvQty;

        public TextView getTvCategoryName() {
            return tvCategoryName;
        }

        public void setTvCategoryName(TextView tvCategoryName) {
            this.tvCategoryName = tvCategoryName;
        }

        public TextView getTvItemName() {
            return tvItemName;
        }

        public void setTvItemName(TextView tvItemName) {
            this.tvItemName = tvItemName;
        }

        public TextView getTvTotalAmount() {
            return tvTotalAmount;
        }

        public void setTvTotalAmount(TextView tvTotalAmount) {
            this.tvTotalAmount = tvTotalAmount;
        }

        public TextView getTvQty() {
            return tvQty;
        }

        public void setTvQty(TextView tvQty) {
            this.tvQty = tvQty;
        }
    }
}
