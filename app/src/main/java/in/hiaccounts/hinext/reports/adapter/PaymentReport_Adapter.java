package in.hiaccounts.hinext.reports.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.hiaccounts.R;
import in.hiaccounts.model.paymentreport.PaymentReport;

/**
 * Created by Prateek on 6/15/2017.
 */

public class PaymentReport_Adapter extends BaseAdapter {

    Activity activity;
    List<PaymentReport> paymentReportList;
    LayoutInflater layoutInflater;


    public PaymentReport_Adapter(Activity activity, List<PaymentReport> paymentReportList) {
        super();
        this.activity = activity;
        this.paymentReportList = paymentReportList;
        layoutInflater = LayoutInflater.from(activity);
    }


    @Override
    public int getCount() {
        return paymentReportList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.adapter_paymentreport, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        PaymentReport paymentReport = paymentReportList.get(position);


        viewHolder.tvFormNumber.setVisibility(View.GONE);
        viewHolder.tvCustomerName.setVisibility(View.GONE);
        viewHolder.tvInvoiceDate.setVisibility(View.GONE);
        viewHolder.tvInvoiceAmount.setVisibility(View.GONE);
        viewHolder.tvInvoiceApplied.setVisibility(View.GONE);
        viewHolder.tvInvoiceArBalance.setVisibility(View.GONE);



        viewHolder.tvFormNumber.setText(paymentReport.getFormNo());
        viewHolder.tvCustomerName.setText(paymentReport.getCustomerName());
        viewHolder.tvInvoiceDate.setText(paymentReport.getInvoiceDate());
        viewHolder.tvInvoiceAmount.setText(""+paymentReport.getInvoiceAmount());
        viewHolder.tvInvoiceApplied.setText(""+paymentReport.getAmountApplied());
        viewHolder.tvInvoiceArBalance.setText(""+paymentReport.getArAmount());

        viewHolder.tvFormNumber.setVisibility(View.VISIBLE);
        viewHolder.tvCustomerName.setVisibility(View.VISIBLE);
        viewHolder.tvInvoiceDate.setVisibility(View.VISIBLE);
        viewHolder.tvInvoiceAmount.setVisibility(View.VISIBLE);
        viewHolder.tvInvoiceApplied.setVisibility(View.VISIBLE);
        viewHolder.tvInvoiceArBalance.setVisibility(View.VISIBLE);




        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.tvFormNumber)
        TextView tvFormNumber;
        @BindView(R.id.tvInvoiceDate)
        TextView tvInvoiceDate;
        @BindView(R.id.tvCustomerName)
        TextView tvCustomerName;
        @BindView(R.id.tvInvoiceAmount)
        TextView tvInvoiceAmount;
        @BindView(R.id.tvInvoiceApplied)
        TextView tvInvoiceApplied;
        @BindView(R.id.tvInvoiceArBalance)
        TextView tvInvoiceArBalance;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

