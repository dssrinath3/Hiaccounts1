package in.hiaccounts.hinext.inventory.adapter;

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
import in.hiaccounts.hinext.inventory.model.inventory_pos.InventoryPosCartItem;

import static in.hiaccounts.hinext.inventory.activity.Activity_InventoryLocationTransfer.selectItemForDelete;

/**
 * Created by administrator on 27/1/18.
 */

public class InventoryItemListAdapter extends BaseAdapter {
    Activity activity;
    private List<InventoryPosCartItem> posCartItems;
    private LayoutInflater layoutInflater;

    public InventoryItemListAdapter(Activity mActivity, List<InventoryPosCartItem> posCartItems) {
        super();
        this.posCartItems = posCartItems;
        layoutInflater = LayoutInflater.from(mActivity);
        activity = mActivity;
    }


    @Override
    public int getCount() {
        return posCartItems.size();
    }

    @Override
    public InventoryPosCartItem getItem(int position) {
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
            convertView = layoutInflater.inflate(R.layout.adapter_inventorypositem, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.tvItemCode.setVisibility(View.GONE);
        }


        final InventoryPosCartItem posCartItem = getItem(position);

        viewHolder.tvItemCode.setVisibility(View.GONE);
        viewHolder.tvItemQty.setVisibility(View.GONE);
        viewHolder.tvItemDesc.setVisibility(View.GONE);
        viewHolder.tvItemStock.setVisibility(View.GONE);
        viewHolder.chkbxSelection.setVisibility(View.GONE);


        if (posCartItem !=null){

            if (posCartItem.getItemDatum() != null) {




                if (posCartItem.getItemDatum().getItemCode() != null)
                    viewHolder.tvItemCode.setText(posCartItem.getItemDatum().getItemCode());

                viewHolder.tvItemDesc.setText(posCartItem.getItemDatum().getItemId().getItemDesc());

                if (posCartItem.getItemDatum().getItemId().getItemQuantityProduction() != null) {
                    viewHolder.tvItemQty.setText(posCartItem.getItemDatum().getItemId().getItemQuantityProduction().toString());
                } else {
                    viewHolder.tvItemQty.setText("0");
                }



                viewHolder.tvItemStock.setText("" +posCartItem.getItemDatum().getStock());

                viewHolder.chkbxSelection.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            Toast.makeText(activity, "check", Toast.LENGTH_SHORT).show();
                            posCartItem.getItemDatum().setSelect(isChecked);
                            selectItemForDelete.add(posCartItem.getItemDatum());


                        } else {
                            Toast.makeText(activity, "uncheck", Toast.LENGTH_SHORT).show();
                            posCartItem.getItemDatum().setSelect(isChecked);
                            selectItemForDelete.remove(posCartItem.getItemDatum());
                        }

                    }
                });
                viewHolder.tvItemCode.setVisibility(View.VISIBLE);
                viewHolder.tvItemQty.setVisibility(View.VISIBLE);
                viewHolder.tvItemDesc.setVisibility(View.VISIBLE);
                viewHolder.tvItemStock.setVisibility(View.VISIBLE);
                viewHolder.chkbxSelection.setVisibility(View.VISIBLE);

            }
        }


        return convertView;
    }

    public void updateCartItems(List<InventoryPosCartItem> posCartItems) {
        this.posCartItems = posCartItems;
        notifyDataSetChanged();
    }
    static class ViewHolder {
        @BindView(R.id.tvItemCode)
        TextView tvItemCode;
        @BindView(R.id.tvItemDesc)
        TextView tvItemDesc;
        @BindView(R.id.tvItemQty)
        TextView tvItemQty;
        @BindView(R.id.tvItemStock)
        TextView tvItemStock;
        @BindView(R.id.chkbxSelection)
        CheckBox chkbxSelection;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
