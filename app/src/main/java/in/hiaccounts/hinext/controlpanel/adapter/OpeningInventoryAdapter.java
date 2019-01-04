package in.hiaccounts.hinext.controlpanel.adapter;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import in.hiaccounts.R;
import in.hiaccounts.hinext.controlpanel.model.openingbalance.InventoryData;
import in.hiaccounts.utility.ServiceHandler;


/**
 * Custom adapter with "View Holder Pattern".
 *
 * @author Prateek
 */
public class OpeningInventoryAdapter extends ArrayAdapter<Object> {
    private LayoutInflater layoutInflater;
    private Activity activity;
    private ServiceHandler serviceHandler;
    private boolean isInternetPresent = false;

    public OpeningInventoryAdapter(Context context, List<Object> objects) {
        super(context, 0, objects);
        layoutInflater = LayoutInflater.from(context);
        activity = (Activity) context;
        serviceHandler = new ServiceHandler(activity);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        // holder pattern
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();

            convertView = layoutInflater.inflate(R.layout.oepningbalance_adapter_inventory, null);

            holder.setTvItemName((TextView) convertView.findViewById(R.id.tvItemName));
            holder.setTvInventoryLocation((TextView) convertView.findViewById(R.id.tvInventoryLocation));
            holder.setTvItemQuantity((TextView) convertView.findViewById(R.id.tvItemQuantity));
            holder.setTvOpeningBalance((TextView) convertView.findViewById(R.id.tvOpeningBalance));
            holder.setTvCalPrice((TextView) convertView.findViewById(R.id.tvCalPrice));


            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }


        holder.getTvInventoryLocation().setVisibility(View.GONE);
        holder.getTvCalPrice().setVisibility(View.GONE);
        holder.getTvItemName().setVisibility(View.GONE);
        holder.getTvItemQuantity().setVisibility(View.GONE);
        holder.getTvOpeningBalance().setVisibility(View.GONE);

        final Object obj = getItem(position);
        if (obj instanceof InventoryData) {
            final InventoryData data = (InventoryData) obj;

            if (data != null) {

                if (data.getItemName() != null)
                    holder.getTvItemName().setText(data.getItemName());

                holder.getTvItemQuantity().setText("" + data.getInitialQty());

                holder.getTvOpeningBalance().setText("" + data.getOpeningBalance());
                holder.getTvCalPrice().setText("" + data.getCalculatedPrice());

                if (data.getLocationId() != null)
                    if (data.getLocationId().getInventoryLocationName() != null)
                        holder.getTvInventoryLocation().setText(data.getLocationId().getInventoryLocationName());


                holder.getTvInventoryLocation().setVisibility(View.VISIBLE);
                holder.getTvCalPrice().setVisibility(View.VISIBLE);
                holder.getTvItemName().setVisibility(View.VISIBLE);
                holder.getTvItemQuantity().setVisibility(View.VISIBLE);
                holder.getTvOpeningBalance().setVisibility(View.VISIBLE);
            }
        }
        return convertView;
    }


    public class Holder {


        TextView tvItemName;
        TextView tvInventoryLocation;
        TextView tvItemQuantity;
        TextView tvOpeningBalance;
        TextView tvCalPrice;

        public TextView getTvItemName() {
            return tvItemName;
        }

        public void setTvItemName(TextView tvItemName) {
            this.tvItemName = tvItemName;
        }

        public TextView getTvInventoryLocation() {
            return tvInventoryLocation;
        }

        public void setTvInventoryLocation(TextView tvInventoryLocation) {
            this.tvInventoryLocation = tvInventoryLocation;
        }

        public TextView getTvItemQuantity() {
            return tvItemQuantity;
        }

        public void setTvItemQuantity(TextView tvItemQuantity) {
            this.tvItemQuantity = tvItemQuantity;
        }

        public TextView getTvOpeningBalance() {
            return tvOpeningBalance;
        }

        public void setTvOpeningBalance(TextView tvOpeningBalance) {
            this.tvOpeningBalance = tvOpeningBalance;
        }

        public TextView getTvCalPrice() {
            return tvCalPrice;
        }

        public void setTvCalPrice(TextView tvCalPrice) {
            this.tvCalPrice = tvCalPrice;
        }
    }
}