package in.hiaccounts.hinext.purchase.adapter;

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
import in.hiaccounts.hinext.purchase.model.purchase_purchaseorder.PurchaseInvokeOrderListData;


/**
 * Created by Prateek on 6/15/2017.
 */

public class PurchaseInvokeOrder_Adapter extends BaseAdapter {

    private List<PurchaseInvokeOrderListData> orderList;
    private LayoutInflater layoutInflater;

    public PurchaseInvokeOrder_Adapter(Activity mActivity, List<PurchaseInvokeOrderListData> orderList) {
        super();
        this.orderList = orderList;
        layoutInflater = LayoutInflater.from(mActivity);
    }

    @Override
    public int getCount() {
        return orderList.size();
    }

    @Override
    public PurchaseInvokeOrderListData getItem(int position) {
        return orderList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.adapter_purchase_invokeorderdetail, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvFormNo.setVisibility(View.GONE);
        viewHolder.tvSupplierName.setVisibility(View.GONE);
        viewHolder.tvTotalRecieved.setVisibility(View.GONE);

        PurchaseInvokeOrderListData orderNumber = getItem(position);
        if (orderNumber != null) {


            if (orderNumber.getFormNo() != null)
                viewHolder.tvFormNo.setText(orderNumber.getFormNo());

            if (orderNumber.getCustomerName() != null)
                viewHolder.tvSupplierName.setText(orderNumber.getCustomerName());

            if (orderNumber.getAmount() != null)
                viewHolder.tvTotalRecieved.setText(""+orderNumber.getAmount());



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

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

