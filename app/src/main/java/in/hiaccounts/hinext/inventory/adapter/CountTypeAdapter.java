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
import in.hiaccounts.hinext.inventory.model.counttype.InventoryCountType;

/**
 * Custom adapter with "View Holder Pattern".
 *
 * @author Prateek
 */
public class CountTypeAdapter extends ArrayAdapter<Object> {
    private LayoutInflater layoutInflater;
    Activity activity;

    public CountTypeAdapter(Context context, List<Object> objects) {
        super(context, 0, objects);
        layoutInflater = LayoutInflater.from(context);
        activity = (Activity) context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // holder pattern
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = layoutInflater.inflate(R.layout.inventory_adapter_counttype, null);

            holder.setTvName((TextView) convertView.findViewById(R.id.tvName));
            holder.setTvDescription((TextView) convertView.findViewById(R.id.tvDescription));
            holder.setTvStatus((TextView) convertView.findViewById(R.id.tvStatus));

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.getTvStatus().setVisibility(View.GONE);
        holder.getTvDescription().setVisibility(View.GONE);
        holder.getTvName().setVisibility(View.GONE);

        Object obj = getItem(position);
        if (obj instanceof InventoryCountType) {
            final InventoryCountType countType = (InventoryCountType) obj;
            if (countType != null) {
                if (countType.getInventoryMovementName() != null) {
                    holder.getTvName().setText(countType.getInventoryMovementName());
                }
                if (countType.getInventoryMovementDesc() != null) {
                    holder.getTvDescription().setText(countType.getInventoryMovementDesc());
                }
                if (countType.getInventoryMovementStatus() != null) {
                    holder.getTvStatus().setText(countType.getInventoryMovementStatus());
                }
            }
            holder.getTvStatus().setVisibility(View.VISIBLE);
            holder.getTvDescription().setVisibility(View.VISIBLE);
            holder.getTvName().setVisibility(View.VISIBLE);
        }
        return convertView;
    }

    public class Holder {
        TextView tvName;
        TextView tvDescription;
        TextView tvStatus;

        public TextView getTvName() {
            return tvName;
        }

        public void setTvName(TextView tvName) {
            this.tvName = tvName;
        }

        public TextView getTvDescription() {
            return tvDescription;
        }

        public void setTvDescription(TextView tvDescription) {
            this.tvDescription = tvDescription;
        }

        public TextView getTvStatus() {
            return tvStatus;
        }

        public void setTvStatus(TextView tvStatus) {
            this.tvStatus = tvStatus;
        }
    }
}
