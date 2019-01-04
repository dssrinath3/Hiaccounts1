package in.hiaccounts.hinext.purchase.adapter;

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
import in.hiaccounts.hinext.purchase.fragment.Fragment_Purchase;
import in.hiaccounts.hinext.purchase.model.purchase_pos.PurchasePosCartItem;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 6/15/2017.
 */
public class Purchasepos_Adapter extends BaseAdapter {
    Activity activity;
    private List<PurchasePosCartItem> posCartItems;
    private LayoutInflater layoutInflater;
    private String operationType;

    public Purchasepos_Adapter(Activity mActivity, List<PurchasePosCartItem> posCartItems, String operationType) {
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
    public PurchasePosCartItem getItem(int position) {
        return posCartItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void updateCartItems(List<PurchasePosCartItem> posCartItems) {
        this.posCartItems = posCartItems;
        notifyDataSetChanged();
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

        final PurchasePosCartItem posCartItem = getItem(position);

        viewHolder.tvItemQty.setVisibility(View.GONE);
        viewHolder.tvItemUnitprice.setVisibility(View.GONE);
        viewHolder.tvItmeTotalAmt.setVisibility(View.GONE);
        viewHolder.chkbxSelection.setVisibility(View.GONE);

        if (posCartItem != null) {
            if (posCartItem.getItem() != null) {
                if (posCartItem.getItem().getItemName() != null)
                    viewHolder.tvItmename.setText(posCartItem.getItem().getItemName());
                if (posCartItem.getItem().getUnitPrice() != null){
                    viewHolder.tvItemUnitprice.setText(String.format("%.2f",Double.valueOf(posCartItem.getItem().getUnitPrice())));
                }



                if (operationType.equals(Constant.OPERATION_RETURN)){


                    viewHolder.tvItemQty.setText(""+posCartItem.getItem().getReturnQty());


                }else {

                    viewHolder.tvItemQty.setText("" + posCartItem.getItem().getItemQuantity());
                }
                viewHolder.tvItmeTotalAmt.setText("" + posCartItem.getItem().getItemTotalAmount());

                viewHolder.chkbxSelection.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            posCartItem.getItem().setSelect(isChecked);
                            Fragment_Purchase.selectItemForDelete.add(posCartItem.getItem());

                        } else {
                            posCartItem.getItem().setSelect(isChecked);
                            Fragment_Purchase.selectItemForDelete.remove(posCartItem.getItem());

                        }

                    }
                });

                if (posCartItem.getItem().getPrice()!=null){
                    UtilView.showLogCat("@FLow","Bigdecimal Price in Adapter "+posCartItem.getItem().getPrice());
                }else {
                    UtilView.showLogCat("@FLow","Bigdecimal Price in Adapter is null");
                }

                viewHolder.tvItmename.setVisibility(View.VISIBLE);
                viewHolder.tvItemQty.setVisibility(View.VISIBLE);
                viewHolder.tvItemUnitprice.setVisibility(View.VISIBLE);
                viewHolder.tvItmeTotalAmt.setVisibility(View.VISIBLE);
                viewHolder.chkbxSelection.setVisibility(View.VISIBLE);
            }
        }
        return convertView;
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

