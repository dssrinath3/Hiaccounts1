package in.hiaccounts.hinext.sales.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import in.hiaccounts.R;
import in.hiaccounts.hinext.sales.model.sales_delivery_order.Sales_DeliveryOrderListData;

/**
 * Created by srinath on 12/1/18.
 */

public class Sales_DeliveryOrderListAdapter extends BaseAdapter{
    private List<Sales_DeliveryOrderListData> paymentInvoiceList;
    private LayoutInflater layoutInflater;

    public Sales_DeliveryOrderListAdapter(Activity activity, List<Sales_DeliveryOrderListData> paymentInvoiceList) {
        super();
        this.paymentInvoiceList = paymentInvoiceList;
        layoutInflater = LayoutInflater.from(activity);
    }


    @Override
    public int getCount() {
        return paymentInvoiceList.size();
    }

    @Override
    public Sales_DeliveryOrderListData getItem(int position) {
        return paymentInvoiceList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View v = layoutInflater.inflate(R.layout.adapter_sales_deliveryorderitemlist, null);



        TextView tv_formno = v.findViewById(R.id.tv_formno);
        TextView tv_customername = v.findViewById(R.id.tv_customername);
        TextView tv_totalamount = v.findViewById(R.id.tv_totalamount);
        final ImageView imgview_duplicateprint = v.findViewById(R.id.imgview_duplicateprint);
        final ImageView imgviewEdit = v.findViewById(R.id.imgviewEdit);

        tv_formno.setVisibility(View.GONE);
        tv_customername.setVisibility(View.GONE);
        tv_totalamount.setVisibility(View.GONE);
        imgview_duplicateprint.setVisibility(View.GONE);
        imgviewEdit.setVisibility(View.GONE);

        Sales_DeliveryOrderListData invoice = getItem(position);
        if (invoice != null) {
            if (invoice.getCustomerName() != null)
                tv_customername.setText(invoice.getCustomerName());
            if (invoice.getFormNo() != null)
                tv_formno.setText(invoice.getFormNo());
            tv_totalamount.setText("" + invoice.getAmount());

            tv_formno.setVisibility(View.VISIBLE);
            tv_customername.setVisibility(View.VISIBLE);
            tv_totalamount.setVisibility(View.VISIBLE);
            imgview_duplicateprint.setVisibility(View.VISIBLE);
            imgviewEdit.setVisibility(View.VISIBLE);
        }
        imgview_duplicateprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ListView) parent).performItemClick(v, position, 0);
            }
        });


        imgviewEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ListView) parent).performItemClick(v, position, 0);
            }
        });

        return v;
    }

}
