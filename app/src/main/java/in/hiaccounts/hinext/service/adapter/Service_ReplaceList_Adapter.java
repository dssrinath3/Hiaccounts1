package in.hiaccounts.hinext.service.adapter;

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
import in.hiaccounts.hinext.service.model.SelectedItemsList;

/**
 * Created by administrator on 26/2/18.
 */

public class Service_ReplaceList_Adapter extends BaseAdapter {
    private List<SelectedItemsList> orderList;
    private LayoutInflater layoutInflater;

    public Service_ReplaceList_Adapter(Activity mActivity, List<SelectedItemsList> orderList) {
        super();
        this.orderList = orderList;
        layoutInflater = LayoutInflater.from(mActivity);
    }

    @Override
    public int getCount() {
        return orderList.size();
    }

    @Override
    public SelectedItemsList getItem(int position) {
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
            convertView = layoutInflater.inflate(R.layout.adapter_serivce_replaceitem_details, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvItemCode.setVisibility(View.GONE);
        viewHolder.tvItemName.setVisibility(View.GONE);
        viewHolder.tvDescription.setVisibility(View.GONE);

        SelectedItemsList orderNumber = getItem(position);
        if (orderNumber != null) {


            if (orderNumber.getItemCode() != null)
                viewHolder.tvItemCode.setText(orderNumber.getItemCode());

            if (orderNumber.getItemName() != null)
                viewHolder.tvItemName.setText(orderNumber.getItemName());

            if (orderNumber.getItemDesc() != null)
                viewHolder.tvDescription.setText(orderNumber.getItemDesc());



            viewHolder.tvItemCode.setVisibility(View.VISIBLE);
            viewHolder.tvItemName.setVisibility(View.VISIBLE);
            viewHolder.tvDescription.setVisibility(View.VISIBLE);


        }

        viewHolder.tvItemCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ListView) parent).performItemClick(v, position, 0);
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tvItemCode)
        TextView tvItemCode;
        @BindView(R.id.tvItemName)
        TextView tvItemName;
        @BindView(R.id.tvDescription)
        TextView tvDescription;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
