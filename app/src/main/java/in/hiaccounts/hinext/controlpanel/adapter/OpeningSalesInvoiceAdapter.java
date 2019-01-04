package in.hiaccounts.hinext.controlpanel.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import in.hiaccounts.R;
import in.hiaccounts.hinext.controlpanel.model.openingbalance.PurchaseInvoiceData;
import in.hiaccounts.hinext.controlpanel.model.openingbalance.SalesInvoiceData;


/**
 * Custom adapter with "View Holder Pattern".
 *
 * @author Prateek
 */
public class OpeningSalesInvoiceAdapter extends ArrayAdapter<Object> {
    private LayoutInflater layoutInflater;


    public OpeningSalesInvoiceAdapter(Context context, List<Object> objects) {
        super(context, 0, objects);
        layoutInflater = LayoutInflater.from(context);

    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        // holder pattern
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();

            convertView = layoutInflater.inflate(R.layout.oepningbalance_adapter_salesinventory, null);

            holder.setTvDate((TextView) convertView.findViewById(R.id.tvDate));
            holder.setTvInvoiceNumber((TextView) convertView.findViewById(R.id.tvInvoiceNumber));
            holder.setTvCustomerName((TextView) convertView.findViewById(R.id.tvCustomerName));
            holder.setTvInvoiceAmount((TextView) convertView.findViewById(R.id.tvInvoiceAmount));
            holder.setTvBalanceAmt((TextView) convertView.findViewById(R.id.tvBalanceAmt));


            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }


        holder.getTvCustomerName().setVisibility(View.GONE);
        holder.getTvDate().setVisibility(View.GONE);
        holder.getTvInvoiceNumber().setVisibility(View.GONE);
        holder.getTvBalanceAmt().setVisibility(View.GONE);
        holder.getTvInvoiceAmount().setVisibility(View.GONE);

        final Object obj = getItem(position);
        if (obj instanceof SalesInvoiceData) {
            final SalesInvoiceData data = (SalesInvoiceData) obj;

            if (data!=null) {

                if (data.getCustomerName() != null)
                    holder.getTvCustomerName().setText(data.getCustomerName());

                if (data.getSiDate() != null)
                    holder.getTvDate().setText(data.getSiDate());

                if (data.getFormNo() != null)
                    holder.getTvInvoiceNumber().setText(data.getFormNo());
                holder.getTvBalanceAmt().setText("" + data.getBalanceAmount());

                holder.getTvInvoiceAmount().setText("" + data.getTotalReceivable());

                holder.getTvCustomerName().setVisibility(View.VISIBLE);
                holder.getTvDate().setVisibility(View.VISIBLE);
                holder.getTvInvoiceNumber().setVisibility(View.VISIBLE);
                holder.getTvBalanceAmt().setVisibility(View.VISIBLE);
                holder.getTvInvoiceAmount().setVisibility(View.VISIBLE);
            }
        }

        if (obj instanceof PurchaseInvoiceData) {
            final PurchaseInvoiceData data = (PurchaseInvoiceData) obj;

            if (data!=null) {
                if (data.getSupplierName() != null)
                    holder.getTvCustomerName().setText(data.getSupplierName());

                if (data.getPiDate() != null)
                    holder.getTvDate().setText(data.getPiDate());

                if (data.getFormNo() != null)
                    holder.getTvInvoiceNumber().setText(data.getFormNo());
                holder.getTvBalanceAmt().setText("" + data.getBalanceAmount());

                holder.getTvInvoiceAmount().setText("" + data.getTotalPayable());

                holder.getTvCustomerName().setVisibility(View.VISIBLE);
                holder.getTvDate().setVisibility(View.VISIBLE);
                holder.getTvInvoiceNumber().setVisibility(View.VISIBLE);
                holder.getTvBalanceAmt().setVisibility(View.VISIBLE);
                holder.getTvInvoiceAmount().setVisibility(View.VISIBLE);
            }
        }


        return convertView;
    }


    public class Holder {


        TextView tvDate;
        TextView tvInvoiceNumber;
        TextView tvCustomerName;
        TextView tvInvoiceAmount;
        TextView tvBalanceAmt;

        public TextView getTvDate() {
            return tvDate;
        }

        public void setTvDate(TextView tvDate) {
            this.tvDate = tvDate;
        }

        public TextView getTvInvoiceNumber() {
            return tvInvoiceNumber;
        }

        public void setTvInvoiceNumber(TextView tvInvoiceNumber) {
            this.tvInvoiceNumber = tvInvoiceNumber;
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

        public TextView getTvBalanceAmt() {
            return tvBalanceAmt;
        }

        public void setTvBalanceAmt(TextView tvBalanceAmt) {
            this.tvBalanceAmt = tvBalanceAmt;
        }
    }
}