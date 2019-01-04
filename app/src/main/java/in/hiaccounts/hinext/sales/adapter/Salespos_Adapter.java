package in.hiaccounts.hinext.sales.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.hiaccounts.R;
import in.hiaccounts.hinext.sales.fragment.Fragment_Sales;
import in.hiaccounts.hinext.sales.model.sales_pos.SalesPosCartItem;
import in.hiaccounts.utility.Constant;

/**
 * Created by Prateek on 6/15/2017.
 */

public class Salespos_Adapter extends BaseAdapter {
    private List<SalesPosCartItem> posCartItems;
    private LayoutInflater layoutInflater;
    String operationType;


    public Salespos_Adapter(Activity mActivity, List<SalesPosCartItem> posCartItems, String operationType) {
        super();
        this.posCartItems = posCartItems;
        layoutInflater = LayoutInflater.from(mActivity);
        this.operationType=operationType;

    }

    @Override
    public int getCount() {
        return posCartItems.size();
    }

    @Override
    public SalesPosCartItem getItem(int position) {
        return posCartItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.adapter_salespositem, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.tvItmename.setVisibility(View.GONE);
        }

        final SalesPosCartItem posCartItem = getItem(position);
        viewHolder.tvItemQty.setVisibility(View.GONE);
        viewHolder.tvItemUnitprice.setVisibility(View.GONE);
        viewHolder.tvItmeTotalAmt.setVisibility(View.GONE);
        viewHolder.chkbxSelection.setVisibility(View.GONE);


        if (posCartItem != null) {
            if (posCartItem.getItem() != null) {
                if (posCartItem.getItem().getItemName() != null)
                    viewHolder.tvItmename.setText(posCartItem.getItem().getItemName());

                if (operationType.equals(Constant.OPERATION_RETURN)) {
                    viewHolder.tvItemQty.setText("" + posCartItem.getItem().getReturnQty());
                } else {
                    viewHolder.tvItemQty.setText("" + posCartItem.getItem().getItemQuantity());
                }
                if (posCartItem.getItem().getUnitPrice()!=0){

                    viewHolder.tvItemUnitprice.setText(String.format("%.2f",posCartItem.getItem().getUnitPrice()));

                }

                viewHolder.tvItmeTotalAmt.setText(String.format("%.2f",posCartItem.getItem().getItemTotalAmount()));
            viewHolder.chkbxSelection.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        posCartItem.getItem().setSelect(isChecked);
                        Fragment_Sales.selectItemForDelete.add(posCartItem.getItem());
                    } else {
                        posCartItem.getItem().setSelect(isChecked);
                        Fragment_Sales.selectItemForDelete.remove(posCartItem.getItem());
                    }
                }
            });
            viewHolder.tvItmename.setVisibility(View.VISIBLE);
            viewHolder.tvItemQty.setVisibility(View.VISIBLE);
            viewHolder.tvItemUnitprice.setVisibility(View.VISIBLE);
            viewHolder.tvItmeTotalAmt.setVisibility(View.VISIBLE);
            viewHolder.chkbxSelection.setVisibility(View.VISIBLE);
        }
    }

        return convertView;
    }

    public void updateCartItems(List<SalesPosCartItem> posCartItems) {
        this.posCartItems = posCartItems;
        notifyDataSetChanged();
    }

    static class ViewHolder {
        @BindView(R.id.tvItmename)
        TextView tvItmename;
        @BindView(R.id.tvItemQty)
        TextView tvItemQty;
        @BindView(R.id.tvItemUnitprice)
        TextView tvItemUnitprice;
        @BindView(R.id.tvItmeTotalAmt)
        TextView tvItmeTotalAmt;
        @BindView(R.id.chkbxSelection)
        CheckBox chkbxSelection;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

