package in.hiaccounts.hinext.service.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.hiaccounts.R;
import in.hiaccounts.hinext.service.fragment.Fragment_Service;
import in.hiaccounts.hinext.service.model.service_pos.ServicePosCartItem;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.UtilView;

/**
 * Created by administrator on 23/2/18.
 */

public class Servicepos_Adapter extends BaseAdapter {
    private List<ServicePosCartItem> posCartItems;
    private LayoutInflater layoutInflater;
    String operationType;
    Activity activity;


    public Servicepos_Adapter(Activity mActivity, List<ServicePosCartItem> posCartItems, String operationType) {
        super();
        this.posCartItems = posCartItems;
        layoutInflater = LayoutInflater.from(mActivity);
        this.operationType=operationType;
        activity = mActivity;
    }

    @Override
    public int getCount() {
        return posCartItems.size();
    }

    @Override
    public ServicePosCartItem getItem(int position) {
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

        final ServicePosCartItem posCartItem = getItem(position);
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

                viewHolder.tvItmeTotalAmt.setText(String.format("%.2f",posCartItem.getItem().getItemTotalAmount()) );
                viewHolder.chkbxSelection.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            posCartItem.getItem().setSelect(isChecked);
                            Fragment_Service.selectItemForDelete.add(posCartItem.getItem());
                        } else {
                            posCartItem.getItem().setSelect(isChecked);
                            Fragment_Service.selectItemForDelete.remove(posCartItem.getItem());
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

    public void updateCartItems(List<ServicePosCartItem> posCartItems) {
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
