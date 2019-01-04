package in.hiaccounts.hinext.inventory.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import in.hiaccounts.R;
import in.hiaccounts.hinext.inventory.model.inventorylocationtransfer.InventoryLocationTransfer;

/**
 * Created by administrator on 27/1/18.
 */

public class InventoryLocationTransferAdapter extends ArrayAdapter<InventoryLocationTransfer> {
    private LayoutInflater layoutInflater;
    private Activity activity;
    private boolean isInternetPresent = false;
    private String serverUrl;
    List<InventoryLocationTransfer> locationTransferList;

    public InventoryLocationTransferAdapter(Context context, List<InventoryLocationTransfer> locationTransferList) {
        super(context,0, locationTransferList);
        layoutInflater = LayoutInflater.from(context);
        activity = (Activity) context;
        this.locationTransferList = locationTransferList;

    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // holder pattern
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = layoutInflater.inflate(R.layout.inventory_adapter_inventorylocationtransferview, null);
            holder.setTvTransferredDate((TextView) convertView.findViewById(R.id.tvTransferredDate));
            holder.setTvItemName((TextView) convertView.findViewById(R.id.tvItemName));
            holder.setTvItemCode((TextView) convertView.findViewById(R.id.tvItemCode));
            holder.setTvFromLocation((TextView) convertView.findViewById(R.id.tvFromLocation));
            holder.setTvToLocation((TextView) convertView.findViewById(R.id.tvToLocation));
            holder.setTvQuantity((TextView) convertView.findViewById(R.id.tvQuantity));

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.getTvTransferredDate().setVisibility(View.GONE);
        holder.getTvItemName().setVisibility(View.GONE);
        holder.getTvItemCode().setVisibility(View.GONE);
        holder.getTvFromLocation().setVisibility(View.GONE);
        holder.getTvToLocation().setVisibility(View.GONE);
        holder.getTvQuantity().setVisibility(View.GONE);


            final InventoryLocationTransfer location = locationTransferList.get(position);

            if (location != null) {
                holder.getTvTransferredDate().setText(location.getDate());
                holder.getTvItemName().setText(location.getItemName());
                holder.getTvItemCode().setText(location.getItemCode());
                holder.getTvFromLocation().setText(location.getFromLocationName());
                holder.getTvToLocation().setText(location.getToLocationName());
                holder.getTvQuantity().setText(location.getQuantity().toString());



                holder.getTvTransferredDate().setVisibility(View.VISIBLE);
                holder.getTvItemName().setVisibility(View.VISIBLE);
                holder.getTvItemCode().setVisibility(View.VISIBLE);
                holder.getTvFromLocation().setVisibility(View.VISIBLE);
                holder.getTvToLocation().setVisibility(View.VISIBLE);
                holder.getTvQuantity().setVisibility(View.VISIBLE);

            }


        return convertView;
    }


    public class Holder {
        TextView tvTransferredDate;
        TextView tvItemName;
        TextView tvItemCode;
        TextView tvFromLocation;
        TextView tvToLocation;
        TextView tvQuantity;

        public TextView getTvTransferredDate() {
            return tvTransferredDate;
        }

        public void setTvTransferredDate(TextView tvTransferredDate) {
            this.tvTransferredDate = tvTransferredDate;
        }

        public TextView getTvItemName() {
            return tvItemName;
        }

        public void setTvItemName(TextView tvItemName) {
            this.tvItemName = tvItemName;
        }

        public TextView getTvItemCode() {
            return tvItemCode;
        }

        public void setTvItemCode(TextView tvItemCode) {
            this.tvItemCode = tvItemCode;
        }

        public TextView getTvFromLocation() {
            return tvFromLocation;
        }

        public void setTvFromLocation(TextView tvFromLocation) {
            this.tvFromLocation = tvFromLocation;
        }

        public TextView getTvToLocation() {
            return tvToLocation;
        }

        public void setTvToLocation(TextView tvToLocation) {
            this.tvToLocation = tvToLocation;
        }

        public TextView getTvQuantity() {
            return tvQuantity;
        }

        public void setTvQuantity(TextView tvQuantity) {
            this.tvQuantity = tvQuantity;
        }
    }
}
