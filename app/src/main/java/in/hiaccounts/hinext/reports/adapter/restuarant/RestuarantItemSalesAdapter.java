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

public class RestuarantItemSalesAdapter extends ArrayAdapter<SalesReportList> {
    private LayoutInflater layoutInflater;
    private Activity activity;
    private ServiceHandler serviceHandler;
    private boolean isInternetPresent = false;
    private String serverUrl;


    public RestuarantItemSalesAdapter(Activity context, List<SalesReportList> objectList) {
        super(context, 0,objectList);
        layoutInflater = LayoutInflater.from(context);
        activity = (Activity) context;
        serviceHandler = new ServiceHandler(activity);
        serverUrl= UtilView.getUrl(activity);
    }




    public View getView(final int position, View convertView, final ViewGroup parent) {
        RestuarantItemSalesAdapter.Holder holder = null;
        if(convertView == null){
            holder = new RestuarantItemSalesAdapter.Holder();
            convertView = layoutInflater.inflate(R.layout.report_restuarant_item_sales_view, null);
            holder.setTvSalesDate((TextView) convertView.findViewById(R.id.tvSalesDate));
            holder.setTvCategoryName((TextView) convertView.findViewById(R.id.tvCategoryName));
            holder.setTvItemName((TextView) convertView.findViewById(R.id.tvItemName));
            holder.setTvSalesTotalAmt((TextView) convertView.findViewById(R.id.tvSalesTotalAmt));
            holder.setTvQty((TextView) convertView.findViewById(R.id.tvQty));
            convertView.setTag(holder);
        }else {
            holder = (RestuarantItemSalesAdapter.Holder) convertView.getTag();
        }
        holder.getTvSalesDate().setVisibility(View.GONE);
        holder.getTvCategoryName().setVisibility(View.GONE);
        holder.getTvItemName().setVisibility(View.GONE);
        holder.getTvSalesTotalAmt().setVisibility(View.GONE);
        holder.getTvQty().setVisibility(View.GONE);
        final SalesReportList salesReportList = getItem(position);
        if (salesReportList != null) {

            if (salesReportList.getDate() != null)
                holder.getTvSalesDate().setText(salesReportList.getDate());

            if (salesReportList.getItemCategoryName() != null)
                holder.getTvCategoryName().setText(""+salesReportList.getItemCategoryName());

            if (salesReportList.getItemName() != null)
                holder.getTvItemName().setText(""+salesReportList.getItemName());

            if (salesReportList.getInvoiceAmount() != null)
                holder.getTvSalesTotalAmt().setText(""+salesReportList.getInvoiceAmount());

            if (salesReportList.getQty() != null)
                holder.getTvQty().setText(""+salesReportList.getQty());

            holder.getTvSalesDate().setVisibility(View.VISIBLE);
            holder.getTvCategoryName().setVisibility(View.VISIBLE);
            holder.getTvItemName().setVisibility(View.VISIBLE);
            holder.getTvSalesTotalAmt().setVisibility(View.VISIBLE);
            holder.getTvQty().setVisibility(View.VISIBLE);
        }


        return convertView;
    }



    private class Holder{
        TextView tvSalesDate;
        TextView tvCategoryName;
        TextView tvItemName;
        TextView tvSalesTotalAmt;
        TextView tvQty;

        public TextView getTvSalesDate() {
            return tvSalesDate;
        }

        public void setTvSalesDate(TextView tvSalesDate) {
            this.tvSalesDate = tvSalesDate;
        }

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

        public TextView getTvSalesTotalAmt() {
            return tvSalesTotalAmt;
        }

        public void setTvSalesTotalAmt(TextView tvSalesTotalAmt) {
            this.tvSalesTotalAmt = tvSalesTotalAmt;
        }

        public TextView getTvQty() {
            return tvQty;
        }

        public void setTvQty(TextView tvQty) {
            this.tvQty = tvQty;
        }
    }
}
