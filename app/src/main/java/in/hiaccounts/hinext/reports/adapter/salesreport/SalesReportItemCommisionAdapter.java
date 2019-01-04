package in.hiaccounts.hinext.reports.adapter.salesreport;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import in.hiaccounts.R;
import in.hiaccounts.hinext.reports.model.sales.Sales_ItemCommision_ReportData;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

public class SalesReportItemCommisionAdapter extends ArrayAdapter<Sales_ItemCommision_ReportData> {
    private LayoutInflater layoutInflater;
    private Activity activity;
    private ServiceHandler serviceHandler;
    private boolean isInternetPresent = false;
    private String serverUrl;


    public SalesReportItemCommisionAdapter(Context context, List<Sales_ItemCommision_ReportData> objectList) {
        super(context, 0,objectList);
        layoutInflater = LayoutInflater.from(context);
        activity = (Activity) context;
        serviceHandler = new ServiceHandler(activity);
        serverUrl= UtilView.getUrl(activity);
    }


    public View getView(final int position, View convertView, final ViewGroup parent) {
        SalesReportItemCommisionAdapter.Holder holder = null;
        if(convertView == null){
            holder = new SalesReportItemCommisionAdapter.Holder();
            convertView = layoutInflater.inflate(R.layout.report_sales_item_commision_view, null);
            holder.setTvSalesDate((TextView) convertView.findViewById(R.id.tvSalesDate));
            holder.setTvEmployeeName((TextView) convertView.findViewById(R.id.tvEmployeeName));
            holder.setTvItemName((TextView) convertView.findViewById(R.id.tvItemName));
            holder.setTvUnitPrice((TextView) convertView.findViewById(R.id.tvUnitPrice));
            holder.setTvQty((TextView) convertView.findViewById(R.id.tvQty));
            holder.setTvAmount((TextView) convertView.findViewById(R.id.tvAmount));
            convertView.setTag(holder);
        }else {
            holder = (SalesReportItemCommisionAdapter.Holder) convertView.getTag();
        }
        holder.getTvSalesDate().setVisibility(View.GONE);
        holder.getTvEmployeeName().setVisibility(View.GONE);
        holder.getTvItemName().setVisibility(View.GONE);
        holder.getTvQty().setVisibility(View.GONE);
        holder.getTvUnitPrice().setVisibility(View.GONE);
        holder.getTvAmount().setVisibility(View.GONE);
        final Sales_ItemCommision_ReportData salesReportList = getItem(position);
            if (salesReportList != null) {

                if (salesReportList.getExpireDate() != null)
                    holder.getTvSalesDate().setText(salesReportList.getExpireDate());

                if (salesReportList.getEmployeeName() != null)
                    holder.getTvEmployeeName().setText(salesReportList.getEmployeeName());

                if (salesReportList.getItemName() != null)
                    holder.getTvItemName().setText(salesReportList.getItemName());

                if (salesReportList.getPrice() != null)
                    holder.getTvUnitPrice().setText(""+salesReportList.getPrice());

                if (salesReportList.getQty() != null)
                    holder.getTvQty().setText(""+salesReportList.getQty());

                if (salesReportList.getItemIncAmt() != null)
                    holder.getTvAmount().setText(""+salesReportList.getItemIncAmt());

                holder.getTvSalesDate().setVisibility(View.VISIBLE);
                holder.getTvEmployeeName().setVisibility(View.VISIBLE);
                holder.getTvItemName().setVisibility(View.VISIBLE);
                holder.getTvQty().setVisibility(View.VISIBLE);
                holder.getTvUnitPrice().setVisibility(View.VISIBLE);
                holder.getTvAmount().setVisibility(View.VISIBLE);
            }


        return convertView;
    }



    private class Holder{
        TextView tvSalesDate;
        TextView tvEmployeeName;
        TextView tvItemName;
        TextView tvUnitPrice;
        TextView tvQty;
        TextView tvAmount;

        public TextView getTvSalesDate() {
            return tvSalesDate;
        }

        public void setTvSalesDate(TextView tvSalesDate) {
            this.tvSalesDate = tvSalesDate;
        }

        public TextView getTvEmployeeName() {
            return tvEmployeeName;
        }

        public void setTvEmployeeName(TextView tvEmployeeName) {
            this.tvEmployeeName = tvEmployeeName;
        }

        public TextView getTvItemName() {
            return tvItemName;
        }

        public void setTvItemName(TextView tvItemName) {
            this.tvItemName = tvItemName;
        }

        public TextView getTvUnitPrice() {
            return tvUnitPrice;
        }

        public void setTvUnitPrice(TextView tvUnitPrice) {
            this.tvUnitPrice = tvUnitPrice;
        }

        public TextView getTvQty() {
            return tvQty;
        }

        public void setTvQty(TextView tvQty) {
            this.tvQty = tvQty;
        }

        public TextView getTvAmount() {
            return tvAmount;
        }

        public void setTvAmount(TextView tvAmount) {
            this.tvAmount = tvAmount;
        }
    }

}
