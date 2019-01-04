package in.hiaccounts.hinext.controlpanel.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import in.hiaccounts.R;
import in.hiaccounts.hinext.controlpanel.model.display_setup.chairs.ChairsDataList;
import in.hiaccounts.utility.ServiceHandler;

public class ChairDataAdapter extends ArrayAdapter<ChairsDataList> {
    private LayoutInflater layoutInflater;
    private Activity activity;
    private ServiceHandler serviceHandler;
    private boolean isInternetPresent = false;

    public ChairDataAdapter(Context context, List<ChairsDataList> objects) {
        super(context, 0, objects);
        layoutInflater = LayoutInflater.from(context);
        activity = (Activity) context;
        serviceHandler = new ServiceHandler(activity);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        // holder pattern
        ChairDataAdapter.Holder holder = null;
        if (convertView == null) {
            holder = new ChairDataAdapter.Holder();

            convertView = layoutInflater.inflate(R.layout.controlpanel_adapter_chairs, null);

            holder.setTvNoChairs((TextView) convertView.findViewById(R.id.tvNoChairs));
            holder.setTvTableName((TextView) convertView.findViewById(R.id.tvTableName));
            holder.setTvConfigName((TextView) convertView.findViewById(R.id.tvConfigName));
            holder.setTvEdit((TextView) convertView.findViewById(R.id.tvEdit));
            holder.setImageViewEdit((ImageView) convertView.findViewById(R.id.imgviewEdit));

            convertView.setTag(holder);
        } else {
            holder = (ChairDataAdapter.Holder) convertView.getTag();
        }
        holder.getTvEdit().setVisibility(View.GONE);
        holder.getImageViewEdit().setVisibility(View.GONE);
        holder.getTvConfigName().setVisibility(View.GONE);
        holder.getTvNoChairs().setVisibility(View.GONE);
        holder.getTvTableName().setVisibility(View.GONE);
        final ChairsDataList configData = getItem(position);



        if (configData != null) {
            if (configData.getNoOfChairs() != null)
                holder.getTvNoChairs().setText(""+configData.getNoOfChairs());


            if (configData.getTable() != null)
                holder.getTvTableName().setText(configData.getTable());

            if (configData.getConfig() != null)
                holder.getTvConfigName().setText(String.valueOf(configData.getConfig()));



        }

        holder.getImageViewEdit().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ListView) parent).performItemClick(v, position, 0);

            }
        });

        holder.getTvEdit().setVisibility(View.GONE);
        holder.getImageViewEdit().setVisibility(View.VISIBLE);
        holder.getTvConfigName().setVisibility(View.VISIBLE);
        holder.getTvTableName().setVisibility(View.VISIBLE);
        holder.getTvNoChairs().setVisibility(View.VISIBLE);
        return convertView;
    }

    public class Holder {

        TextView tvNoChairs;
        TextView tvTableName;
        TextView tvConfigName;
        TextView tvEdit;
        ImageView imageViewEdit;

        public TextView getTvNoChairs() {
            return tvNoChairs;
        }

        public void setTvNoChairs(TextView tvNoChairs) {
            this.tvNoChairs = tvNoChairs;
        }

        public TextView getTvTableName() {
            return tvTableName;
        }

        public void setTvTableName(TextView tvTableName) {
            this.tvTableName = tvTableName;
        }

        public TextView getTvConfigName() {
            return tvConfigName;
        }

        public void setTvConfigName(TextView tvConfigName) {
            this.tvConfigName = tvConfigName;
        }

        public TextView getTvEdit() {
            return tvEdit;
        }

        public void setTvEdit(TextView tvEdit) {
            this.tvEdit = tvEdit;
        }

        public ImageView getImageViewEdit() {
            return imageViewEdit;
        }

        public void setImageViewEdit(ImageView imageViewEdit) {
            this.imageViewEdit = imageViewEdit;
        }
    }
}
