package in.hiaccounts.hinext.purchase.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.hiaccounts.R;
import in.hiaccounts.hinext.purchase.model.purchase_receiveitem.PurchaseRecieveItemListData;

/**
 * Created by administrator on 30/12/17.
 */

public class Purchase_ReceiveItemAdapater extends BaseAdapter {
    private List<PurchaseRecieveItemListData> paymentRecieveItemList;
    private LayoutInflater layoutInflater;

    public Purchase_ReceiveItemAdapater(Activity activity, List<PurchaseRecieveItemListData> paymentRecieveItemList) {
        super();
        this.paymentRecieveItemList = paymentRecieveItemList;
        layoutInflater = LayoutInflater.from(activity);
    }


    @Override
    public int getCount() {
        return paymentRecieveItemList.size();
    }

    @Override
    public PurchaseRecieveItemListData getItem(int position) {
        return paymentRecieveItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.adapter_purchase_receiveitemlist, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        PurchaseRecieveItemListData paymentInvoice = getItem(position);

        viewHolder.tvFormNo.setVisibility(View.GONE);
        viewHolder.tvSupplierName.setVisibility(View.GONE);
        viewHolder.tvTotalRecieved.setVisibility(View.GONE);

        if (paymentInvoice != null) {


            if (paymentInvoice.getFormNo() != null)
                viewHolder.tvFormNo.setText(paymentInvoice.getFormNo());

            if (paymentInvoice.getCustomerName() != null)
                viewHolder.tvSupplierName.setText(paymentInvoice.getCustomerName());

            if (paymentInvoice.getGtAmountPaid() != null)
                viewHolder.tvTotalRecieved.setText(""+paymentInvoice.getGtAmountPaid());



            viewHolder.tvFormNo.setVisibility(View.VISIBLE);
            viewHolder.tvSupplierName.setVisibility(View.VISIBLE);
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
        @BindView(R.id.tvSupplierName)
        TextView tvSupplierName;
        @BindView(R.id.tvTotalRecieved)
        TextView tvTotalRecieved;
        @BindView(R.id.rl1)
        RelativeLayout rl1;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
