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
import in.hiaccounts.hinext.reports.model.SalesReportList;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

public class SalesReportCustomerStatementAdapter extends ArrayAdapter<SalesReportList> {
    private LayoutInflater layoutInflater;
    private Activity activity;
    private ServiceHandler serviceHandler;
    private boolean isInternetPresent = false;
    private String serverUrl;


    public SalesReportCustomerStatementAdapter(Context context, List<SalesReportList> objectList) {
        super(context, 0,objectList);
        layoutInflater = LayoutInflater.from(context);
        activity = (Activity) context;
        serviceHandler = new ServiceHandler(activity);
        serverUrl= UtilView.getUrl(activity);
    }


    public View getView(final int position, View convertView, final ViewGroup parent) {
        SalesReportCustomerStatementAdapter.Holder holder = null;
        if(convertView == null){
            holder = new SalesReportCustomerStatementAdapter.Holder();
            convertView = layoutInflater.inflate(R.layout.report_sales_invoice_register_view, null);
            holder.setTvSalesDate((TextView) convertView.findViewById(R.id.tvSalesDate));
            holder.setTvSalesInvoiceNo((TextView) convertView.findViewById(R.id.tvSalesInvoiceNo));
            holder.setTvCustomerName((TextView) convertView.findViewById(R.id.tvCustomerName));
            holder.setTvInvoiceAmount((TextView) convertView.findViewById(R.id.tvInvoiceAmount));
            holder.setTvCurrency((TextView) convertView.findViewById(R.id.tvCurrency));
            holder.setTvStatus((TextView) convertView.findViewById(R.id.tvStatus));
            convertView.setTag(holder);
        }else {
            holder = (SalesReportCustomerStatementAdapter.Holder) convertView.getTag();
        }
        holder.getTvSalesDate().setVisibility(View.GONE);
        holder.getTvSalesInvoiceNo().setVisibility(View.GONE);
        holder.getTvCustomerName().setVisibility(View.GONE);
        holder.getTvCurrency().setVisibility(View.GONE);
        holder.getTvInvoiceAmount().setVisibility(View.GONE);
        holder.getTvStatus().setVisibility(View.GONE);
        final SalesReportList salesReportList = getItem(position);
        if (salesReportList != null) {

            if (salesReportList.getDate() != null)
                holder.getTvSalesDate().setText(salesReportList.getDate());

            if (salesReportList.getSqno() != null)
                holder.getTvSalesInvoiceNo().setText(""+salesReportList.getSqno());

            if (salesReportList.getCustomerName() != null)
                holder.getTvCustomerName().setText(salesReportList.getCustomerName());

            if (salesReportList.getTotalReceivable() != null)
                holder.getTvInvoiceAmount().setText(""+salesReportList.getTotalReceivable());

            if (salesReportList.getCurrencySymbol() != null)
                holder.getTvCurrency().setText(""+salesReportList.getCurrencySymbol());

            if (salesReportList.getSqstatus() != null)
                holder.getTvStatus().setText(""+salesReportList.getSqstatus());

            holder.getTvSalesDate().setVisibility(View.VISIBLE);
            holder.getTvSalesInvoiceNo().setVisibility(View.VISIBLE);
            holder.getTvCustomerName().setVisibility(View.VISIBLE);
            holder.getTvCurrency().setVisibility(View.VISIBLE);
            holder.getTvInvoiceAmount().setVisibility(View.VISIBLE);
            holder.getTvStatus().setVisibility(View.VISIBLE);
        }


        return convertView;
    }



    private class Holder{
        TextView tvSalesDate;
        TextView tvSalesInvoiceNo;
        TextView tvCustomerName;
        TextView tvInvoiceAmount;
        TextView tvCurrency;
        TextView tvStatus;

        public TextView getTvSalesDate() {
            return tvSalesDate;
        }

        public void setTvSalesDate(TextView tvSalesDate) {
            this.tvSalesDate = tvSalesDate;
        }

        public TextView getTvSalesInvoiceNo() {
            return tvSalesInvoiceNo;
        }

        public void setTvSalesInvoiceNo(TextView tvSalesInvoiceNo) {
            this.tvSalesInvoiceNo = tvSalesInvoiceNo;
        }

        public TextView getTvCustomerName() {
            return tvCustomerName;
        }

        public void setTvCustomerName(TextView tvCustomerName) {
            this.tvCustomerName = tvCustomerName;
        }

        public TextView getTvInvoiceAmount() {
            return tvInvoiceAmount;
        }

        public void setTvInvoiceAmount(TextView tvInvoiceAmount) {
            this.tvInvoiceAmount = tvInvoiceAmount;
        }

        public TextView getTvCurrency() {
            return tvCurrency;
        }

        public void setTvCurrency(TextView tvCurrency) {
            this.tvCurrency = tvCurrency;
        }

        public TextView getTvStatus() {
            return tvStatus;
        }

        public void setTvStatus(TextView tvStatus) {
            this.tvStatus = tvStatus;
        }
    }
}
