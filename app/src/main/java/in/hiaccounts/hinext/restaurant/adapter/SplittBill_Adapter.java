package in.hiaccounts.hinext.restaurant.adapter;

import android.app.Activity;
import android.content.Context;
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
import in.hiaccounts.hinext.restaurant.model.category_item.SelectedItemsList;
import in.hiaccounts.hinext.restaurant.model.restra_pos.RestraPosCartItem;
import in.hiaccounts.utility.SharedPreference;

/**
 * Created by administrator on 15/2/18.
 */

public class SplittBill_Adapter extends BaseAdapter {
    private List<SelectedItemsList> posCartItems;
    private LayoutInflater layoutInflater;
    private static final String TAG = SplittBill_Adapter.class.getSimpleName();
    private final Context context;

    RestraPosCartItem cartItem;
    SharedPreference sharedPreference;
    Activity mActivity;

    public SplittBill_Adapter(Context context, List<SelectedItemsList> posCartItems) {
        super();
        this.posCartItems = posCartItems;
        this.context = context;
        mActivity= (Activity) context;
        layoutInflater = LayoutInflater.from(context);
        sharedPreference=SharedPreference.getInstance(context);


    }

    @Override
    public int getCount() {
        return posCartItems.size();
    }

    @Override
    public SelectedItemsList getItem(int position) {
        return posCartItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void updateCartItems(List<SelectedItemsList> posCartItems) {
        this.posCartItems = posCartItems;
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.restaurent_splittbill_items, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.edItemName.setVisibility(View.GONE);
        }

        final SelectedItemsList posCartItem = getItem(position);

        viewHolder.edPrice.setVisibility(View.GONE);
        viewHolder.edItemQty.setVisibility(View.GONE);

        if (posCartItem != null) {
            if (posCartItem != null) {
                if (posCartItem.getItemName() != null)
                    viewHolder.edItemName.setText(posCartItem.getItemName());
                if (posCartItem.getUnitPrice() != null){
                    viewHolder.edPrice.setText(String.format("%.2f",posCartItem.getUnitPrice()));
                }


                viewHolder.edItemQty.setText("" + posCartItem.getItemQuantity());
                viewHolder.edItemName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((ListView) parent).performItemClick(view, position, 0);
                    }
                });


                viewHolder.edItemName.setVisibility(View.VISIBLE);
                viewHolder.edPrice.setVisibility(View.VISIBLE);
                viewHolder.edItemQty.setVisibility(View.VISIBLE);

            }
        }
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.edItemName)
        TextView edItemName;
        @BindView(R.id.edPrice)
        TextView edPrice;
        @BindView(R.id.edItemQty)
        TextView edItemQty;


        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }



}
