package in.hiaccounts.hinext.sales.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.hiaccounts.R;
import in.hiaccounts.hinext.sales.model.sales_quotation.Sales_QuotationListData;

/**
 * Created by administrator on 9/1/18.
 */

public class Sales_QuotationAdapater extends BaseAdapter {
    private List<Sales_QuotationListData> paymentInvoiceList;
    private LayoutInflater layoutInflater;

    public Sales_QuotationAdapater(Activity activity, List<Sales_QuotationListData> paymentInvoiceList) {
        super();
        this.paymentInvoiceList = paymentInvoiceList;
        layoutInflater = LayoutInflater.from(activity);
    }


    @Override
    public int getCount() {
        return paymentInvoiceList.size();
    }

    @Override
    public Sales_QuotationListData getItem(int position) {
        return paymentInvoiceList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.adapter_sales_quotationlist, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Sales_QuotationListData paymentInvoice = getItem(position);

        viewHolder.tvFormNo.setVisibility(View.GONE);
        viewHolder.tvCustomerName.setVisibility(View.GONE);
        viewHolder.tvTotalRecieved.setVisibility(View.GONE);

        if (paymentInvoice != null) {


            if (paymentInvoice.getFormNo() != null)
                viewHolder.tvFormNo.setText(paymentInvoice.getFormNo());

            if (paymentInvoice.getCustomerName() != null)
                viewHolder.tvCustomerName.setText(paymentInvoice.getCustomerName());

            if (paymentInvoice.getAmount() != null)
                viewHolder.tvTotalRecieved.setText(""+paymentInvoice.getAmount());



            viewHolder.tvFormNo.setVisibility(View.VISIBLE);
            viewHolder.tvCustomerName.setVisibility(View.VISIBLE);
            viewHolder.tvTotalRecieved.setVisibility(View.VISIBLE);

            //viewHolder.rl1.setVisibility(View.VISIBLE);
        }

        viewHolder.tvFormNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ListView) parent).performItemClick(v, position, 0);
            }
        });

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tvFormNo)
        TextView tvFormNo;
        @BindView(R.id.tvCustomerName)
        TextView tvCustomerName;
        @BindView(R.id.tvTotalRecieved)
        TextView tvTotalRecieved;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
