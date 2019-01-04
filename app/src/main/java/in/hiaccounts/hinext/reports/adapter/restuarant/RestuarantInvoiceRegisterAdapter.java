package in.hiaccounts.hinext.reports.adapter.restuarant;

import android.app.Activity;
import android.content.Context;
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

public class RestuarantInvoiceRegisterAdapter extends ArrayAdapter<SalesReportList> {
    private LayoutInflater layoutInflater;
    private Activity activity;
    private ServiceHandler serviceHandler;
    private boolean isInternetPresent = false;
    private String serverUrl;


    public RestuarantInvoiceRegisterAdapter(Activity context, List<SalesReportList> objectList) {
        super(context, 0,objectList);
        layoutInflater = LayoutInflater.from(context);
        activity = (Activity) context;
        serviceHandler = new ServiceHandler(activity);
        serverUrl= UtilView.getUrl(activity);
    }




    public View getView(final int position, View convertView, final ViewGroup parent) {
        RestuarantInvoiceRegisterAdapter.Holder holder = null;
        if(convertView == null){
            holder = new RestuarantInvoiceRegisterAdapter.Holder();
            convertView = layoutInflater.inflate(R.layout.report_restuarant_invoice_register_view, null);
            holder.setTvSalesDate((TextView) convertView.findViewById(R.id.tvSalesDate));
            holder.setTvSalesSqNo((TextView) convertView.findViewById(R.id.tvSalesSqNo));
            holder.setTvSalesCustomerName((TextView) convertView.findViewById(R.id.tvSalesCustomerName));
            holder.setTvSalesTotalAmt((TextView) convertView.findViewById(R.id.tvSalesTotalAmt));
            holder.setTvPaymentType((TextView) convertView.findViewById(R.id.tvPaymentType));
            convertView.setTag(holder);
        }else {
            holder = (RestuarantInvoiceRegisterAdapter.Holder) convertView.getTag();
        }
        holder.getTvSalesDate().setVisibility(View.GONE);
        holder.getTvSalesSqNo().setVisibility(View.GONE);
        holder.getTvSalesCustomerName().setVisibility(View.GONE);
        holder.getTvSalesTotalAmt().setVisibility(View.GONE);
        holder.getTvPaymentType().setVisibility(View.GONE);
        final SalesReportList salesReportList = getItem(position);
            if (salesReportList != null) {

                if (salesReportList.getDate() != null)
                    holder.getTvSalesDate().setText(salesReportList.getDate());

                if (salesReportList.getSqno() != null)
                    holder.getTvSalesSqNo().setText(salesReportList.getSqno());

                if (salesReportList.getCustomerName() != null)
                    holder.getTvSalesCustomerName().setText(salesReportList.getCustomerName());

                if (salesReportList.getTotalReceivable() != null)
                    holder.getTvSalesTotalAmt().setText(""+salesReportList.getTotalReceivable());

                if (salesReportList.getPaymentName() != null)
                    holder.getTvPaymentType().setText(salesReportList.getPaymentName());

                holder.getTvSalesDate().setVisibility(View.VISIBLE);
                holder.getTvSalesSqNo().setVisibility(View.VISIBLE);
                holder.getTvSalesCustomerName().setVisibility(View.VISIBLE);
                holder.getTvSalesTotalAmt().setVisibility(View.VISIBLE);
                holder.getTvPaymentType().setVisibility(View.VISIBLE);
            }


        return convertView;
    }



    private class Holder{
        TextView tvSalesDate;
        TextView tvSalesSqNo;
        TextView tvSalesCustomerName;
        TextView tvSalesTotalAmt;
        TextView tvPaymentType;

        public TextView getTvSalesDate() {
            return tvSalesDate;
        }

        public void setTvSalesDate(TextView tvSalesDate) {
            this.tvSalesDate = tvSalesDate;
        }

        public TextView getTvSalesSqNo() {
            return tvSalesSqNo;
        }

        public void setTvSalesSqNo(TextView tvSalesSqNo) {
            this.tvSalesSqNo = tvSalesSqNo;
        }

        public TextView getTvSalesCustomerName() {
            return tvSalesCustomerName;
        }

        public void setTvSalesCustomerName(TextView tvSalesCustomerName) {
            this.tvSalesCustomerName = tvSalesCustomerName;
        }

        public TextView getTvSalesTotalAmt() {
            return tvSalesTotalAmt;
        }

        public void setTvSalesTotalAmt(TextView tvSalesTotalAmt) {
            this.tvSalesTotalAmt = tvSalesTotalAmt;
        }

        public TextView getTvPaymentType() {
            return tvPaymentType;
        }

        public void setTvPaymentType(TextView tvPaymentType) {
            this.tvPaymentType = tvPaymentType;
        }
    }
}
