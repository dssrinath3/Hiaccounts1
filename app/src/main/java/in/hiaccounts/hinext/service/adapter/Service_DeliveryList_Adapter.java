package in.hiaccounts.hinext.service.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.hiaccounts.R;
import in.hiaccounts.hinext.service.model.SelectedItemsList;
import in.hiaccounts.hinext.service.model.service_deliverylist.DeliveryList_Data;

/**
 * Created by administrator on 24/2/18.
 */

public class Service_DeliveryList_Adapter extends BaseAdapter {
    private List<DeliveryList_Data> orderList;
    private LayoutInflater layoutInflater;

    public Service_DeliveryList_Adapter(Activity mActivity, List<DeliveryList_Data> orderList) {
        super();
        this.orderList = orderList;
        layoutInflater = LayoutInflater.from(mActivity);
    }

    @Override
    public int getCount() {
        return orderList.size();
    }

    @Override
    public DeliveryList_Data getItem(int position) {
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
            convertView = layoutInflater.inflate(R.layout.adapter_serivce_ideliverylistdetail, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvFormNo.setVisibility(View.GONE);
        viewHolder.tvSupplierName.setVisibility(View.GONE);
        viewHolder.tvTotalRecieved.setVisibility(View.GONE);

        DeliveryList_Data orderNumber = getItem(position);
        if (orderNumber != null) {


            if (orderNumber.getSrNo() != null)
                viewHolder.tvFormNo.setText(orderNumber.getSrNo());

            if (orderNumber.getName() != null)
                viewHolder.tvSupplierName.setText(orderNumber.getName());

            if (orderNumber.getPrice() != null)
                viewHolder.tvTotalRecieved.setText(""+orderNumber.getPrice());



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
