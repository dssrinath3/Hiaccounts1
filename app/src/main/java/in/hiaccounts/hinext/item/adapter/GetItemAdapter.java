package in.hiaccounts.hinext.item.adapter;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import in.hiaccounts.R;
import in.hiaccounts.hinext.inventory.model.inventory_pos.InventorySelectItemData;
import in.hiaccounts.hinext.item.model.SelectedItemsList;


/**
 * Custom adapter with "View Holder Pattern".
 *
 * @author Srinath
 */
public class GetItemAdapter extends ArrayAdapter<Object> {
    private LayoutInflater layoutInflater;

    public GetItemAdapter(Context context, List<Object> objects) {
        super(context, 0, objects);
        layoutInflater = LayoutInflater.from(context);
    }

    public static ArrayAdapter<String> setupOrderListAdapter(Activity activity, Spinner spinner, String[] stringArray) {


        String[] adapter_array = new String[0];
        if (stringArray.length > 0) {
            adapter_array = new String[stringArray.length + 1];
            adapter_array[0] = activity.getResources().getString(R.string.select_ordernumber);
            for (int i = 0; i < stringArray.length; i++) {
                adapter_array[i + 1] = stringArray[i];
            }
        }
        if (stringArray.length <= 0) {
            adapter_array = new String[1];
            adapter_array[0] = activity.getResources().getString(R.string.select_ordernumber);

        }
        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(activity, R.layout.spinner_textitem, adapter_array) {
            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);

                TextView tv = view.findViewById(R.id.textView1);
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_textitem);
        spinner.setAdapter(spinnerArrayAdapter);

        return spinnerArrayAdapter;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // holder pattern
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = layoutInflater.inflate(R.layout.listview_selectitems, null);
            holder.setTv_itmecode((TextView) convertView.findViewById(R.id.tv_itmecode));
            holder.setTv_itemname((TextView) convertView.findViewById(R.id.tv_itemname));
            holder.setTv_itemdescription((TextView) convertView.findViewById(R.id.tv_itemdescription));
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.getTv_itmecode().setVisibility(View.GONE);
        holder.getTv_itemname().setVisibility(View.GONE);
        holder.getTv_itemdescription().setVisibility(View.GONE);


        Object obj = getItem(position);
        if (obj instanceof SelectedItemsList) {

            SelectedItemsList item = (SelectedItemsList) obj;

            if (item != null) {
                if (item.getItemName() != null)
                    holder.getTv_itemname().setText(item.getItemName());
                if (item.getItemCode() != null)
                    holder.getTv_itmecode().setText(item.getItemCode());

                holder.getTv_itemdescription().setText("" + item.getStock());

                holder.getTv_itmecode().setVisibility(View.VISIBLE);
                holder.getTv_itemname().setVisibility(View.VISIBLE);
                holder.getTv_itemdescription().setVisibility(View.VISIBLE);
            }

        } else if (obj instanceof InventorySelectItemData) {

            InventorySelectItemData item = (InventorySelectItemData) obj;

            if (item != null) {
                if (item.getItemId() != null)
                    holder.getTv_itemname().setText(item.getItemId().getItemName());

                if (item.getItemCode() != null)
                    holder.getTv_itmecode().setText(item.getItemCode());

                holder.getTv_itemdescription().setText("" + item.getStock());

                holder.getTv_itmecode().setVisibility(View.VISIBLE);
                holder.getTv_itemname().setVisibility(View.VISIBLE);
                holder.getTv_itemdescription().setVisibility(View.VISIBLE);
            }

        }
        return convertView;
    }

    public class Holder {
        TextView tv_itmecode;
        TextView tv_itemname;
        TextView tv_itemdescription;

        public TextView getTv_itmecode() {
            return tv_itmecode;
        }

        public void setTv_itmecode(TextView tv_itmecode) {
            this.tv_itmecode = tv_itmecode;
        }

        public TextView getTv_itemname() {
            return tv_itemname;
        }

        public void setTv_itemname(TextView tv_itemname) {
            this.tv_itemname = tv_itemname;
        }

        public TextView getTv_itemdescription() {
            return tv_itemdescription;
        }

        public void setTv_itemdescription(TextView tv_itemdescription) {
            this.tv_itemdescription = tv_itemdescription;
        }
    }

}
