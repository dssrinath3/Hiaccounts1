package in.hiaccounts.hinext.sales.adapter;

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
import in.hiaccounts.hinext.sales.model.save_response.SelectedItemsList;
import in.hiaccounts.utility.Constant;

/**
 * Created by Prateek on 6/15/2017.
 */

public class PosItem_Adapter extends BaseAdapter {
    private List<SelectedItemsList> itemList;
    private LayoutInflater layoutInflater;
    private String operationType;

    public PosItem_Adapter(Activity activity, List<SelectedItemsList> itemList) {
        this.itemList = itemList;
        layoutInflater = LayoutInflater.from(activity);
        this.operationType=operationType;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public SelectedItemsList getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.adapter_positem, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }

        final SelectedItemsList item = getItem(position);
        viewHolder.tvItemQty.setVisibility(View.GONE);
        viewHolder.tvItemUnitprice.setVisibility(View.GONE);
        viewHolder.tvItmeTotalAmt.setVisibility(View.GONE);
        viewHolder.tvItemDescription.setVisibility(View.GONE);
        viewHolder.tvItmeTaxName.setVisibility(View.GONE);

        if (item != null) {
            if (item.getItemDescription() != null)
                viewHolder.tvItemDescription.setText(item.getItemDescription());
            if (operationType!=null){
                if (operationType.equals(Constant.OPERATION_RETURN)) {
                    viewHolder.tvItemQty.setText("" + item.getReturnQty());
                }else {
                    viewHolder.tvItemQty.setText("" + item.getQty());
                }
            }else {
                viewHolder.tvItemQty.setText("" + item.getQty());
            }

            if (item.getUnitPrice() != null)
                viewHolder.tvItemUnitprice.setText("" + item.getUnitPrice());
            if (item.getAmtinclusivetax() != null)
                viewHolder.tvItmeTotalAmt.setText("" + item.getAmtinclusivetax());
            if (item.getTaxName() != null)
                viewHolder.tvItmeTaxName.setText(item.getTaxName());
            viewHolder.tvItemQty.setVisibility(View.VISIBLE);
            viewHolder.tvItemUnitprice.setVisibility(View.VISIBLE);
            viewHolder.tvItmeTotalAmt.setVisibility(View.VISIBLE);
            viewHolder.tvItemDescription.setVisibility(View.VISIBLE);
            viewHolder.tvItmeTaxName.setVisibility(View.VISIBLE);
        }
        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.tvItemDescription)
        TextView tvItemDescription;
        @BindView(R.id.tvItemUnitprice)
        TextView tvItemUnitprice;
        @BindView(R.id.tvItemQty)
        TextView tvItemQty;
        @BindView(R.id.tvItmeTotalAmt)
        TextView tvItmeTotalAmt;
        @BindView(R.id.tvItmeTaxName)
        TextView tvItmeTaxName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

