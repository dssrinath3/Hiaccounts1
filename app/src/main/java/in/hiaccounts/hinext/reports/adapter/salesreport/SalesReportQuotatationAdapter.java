package in.hiaccounts.hinext.reports.adapter.salesreport;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import in.hiaccounts.R;
import in.hiaccounts.hinext.reports.model.SalesReportList;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.PostDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

public class SalesReportQuotatationAdapter  extends ArrayAdapter<Object> {
    private LayoutInflater layoutInflater;
    private Activity activity;
    private ServiceHandler serviceHandler;
    private boolean isInternetPresent = false;
    private String serverUrl;


    public SalesReportQuotatationAdapter(Context context, List<Object> objectList) {
        super(context, 0,objectList);
        layoutInflater = LayoutInflater.from(context);
        activity = (Activity) context;
        serviceHandler = new ServiceHandler(activity);
        serverUrl= UtilView.getUrl(activity);
    }


    public View getView(final int position, View convertView, final ViewGroup parent) {
        SalesReportQuotatationAdapter.Holder holder = null;
        if(convertView == null){
            holder = new SalesReportQuotatationAdapter.Holder();
            convertView = layoutInflater.inflate(R.layout.report_sales_quotation_view, null);
            holder.setTvSalesDate((TextView) convertView.findViewById(R.id.tvSalesDate));
            holder.setTvSalesSqNo((TextView) convertView.findViewById(R.id.tvSalesSqNo));
            holder.setTvSalesCustomerName((TextView) convertView.findViewById(R.id.tvSalesCustomerName));
            holder.setTvSalesTotalAmt((TextView) convertView.findViewById(R.id.tvSalesTotalAmt));
            holder.setTvSalesStatus((TextView) convertView.findViewById(R.id.tvSalesStatus));
            convertView.setTag(holder);
        }else {
            holder = (SalesReportQuotatationAdapter.Holder) convertView.getTag();
        }
        holder.getTvSalesDate().setVisibility(View.GONE);
        holder.getTvSalesSqNo().setVisibility(View.GONE);
        holder.getTvSalesCustomerName().setVisibility(View.GONE);
        holder.getTvSalesTotalAmt().setVisibility(View.GONE);
        holder.getTvSalesStatus().setVisibility(View.GONE);
        final Object obj = getItem(position);
        if (obj instanceof SalesReportList) {
            final SalesReportList salesReportList = (SalesReportList) obj;
            if (salesReportList != null) {

                if (salesReportList.getDate() != null)
                    holder.getTvSalesDate().setText(salesReportList.getDate());

                if (salesReportList.getSqno() != null)
                    holder.getTvSalesSqNo().setText(salesReportList.getSqno());

                if (salesReportList.getCustomerName() != null)
                    holder.getTvSalesCustomerName().setText(salesReportList.getCustomerName());

                if (salesReportList.getTotalReceivable() != null)
                    holder.getTvSalesTotalAmt().setText(""+salesReportList.getTotalReceivable());

                if (salesReportList.getSqstatus() != null)
                    holder.getTvSalesStatus().setText(salesReportList.getSqstatus());

                holder.getTvSalesDate().setVisibility(View.VISIBLE);
                holder.getTvSalesSqNo().setVisibility(View.VISIBLE);
                holder.getTvSalesCustomerName().setVisibility(View.VISIBLE);
                holder.getTvSalesTotalAmt().setVisibility(View.VISIBLE);
                holder.getTvSalesStatus().setVisibility(View.VISIBLE);
            }




        }

        return convertView;
    }



    private class Holder{
        TextView tvSalesDate;
        TextView tvSalesSqNo;
        TextView tvSalesCustomerName;
        TextView tvSalesTotalAmt;
        TextView tvSalesStatus;

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

        public TextView getTvSalesStatus() {
            return tvSalesStatus;
        }

        public void setTvSalesStatus(TextView tvSalesStatus) {
            this.tvSalesStatus = tvSalesStatus;
        }
    }
}
