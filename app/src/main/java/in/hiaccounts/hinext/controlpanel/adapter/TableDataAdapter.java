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
import in.hiaccounts.hinext.controlpanel.model.display_setup.table_configuration.table.TableData;
import in.hiaccounts.hinext.controlpanel.model.display_setup.table_configuration.table.TableDataList;
import in.hiaccounts.utility.ServiceHandler;


public class TableDataAdapter extends ArrayAdapter<TableDataList> {
    private LayoutInflater layoutInflater;
    private Activity activity;
    private ServiceHandler serviceHandler;
    private boolean isInternetPresent = false;

    public TableDataAdapter(Context context, List<TableDataList> objects) {
        super(context, 0, objects);
        layoutInflater = LayoutInflater.from(context);
        activity = (Activity) context;
        serviceHandler = new ServiceHandler(activity);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        // holder pattern
        TableDataAdapter.Holder holder = null;
        if (convertView == null) {
            holder = new TableDataAdapter.Holder();

            convertView = layoutInflater.inflate(R.layout.controlpanel_adapter_table, null);

            holder.setTvTableName((TextView) convertView.findViewById(R.id.tvTableName));
            holder.setTvConfigName((TextView) convertView.findViewById(R.id.tvConfigName));
            holder.setTvRows((TextView) convertView.findViewById(R.id.tvRows));
            holder.setTvColumns((TextView) convertView.findViewById(R.id.tvColumns));
            holder.setTvEdit((TextView) convertView.findViewById(R.id.tvEdit));
            holder.setImageViewEdit((ImageView) convertView.findViewById(R.id.imgviewEdit));

            convertView.setTag(holder);
        } else {
            holder = (TableDataAdapter.Holder) convertView.getTag();
        }
        holder.getTvEdit().setVisibility(View.GONE);
        holder.getImageViewEdit().setVisibility(View.GONE);
        holder.getTvConfigName().setVisibility(View.GONE);
        holder.getTvRows().setVisibility(View.GONE);
        holder.getTvColumns().setVisibility(View.GONE);
        holder.getTvTableName().setVisibility(View.GONE);
        final TableDataList configData = getItem(position);



        if (configData != null) {
            if (configData.getTableName() != null)
                holder.getTvTableName().setText(configData.getTableName());


            if (configData.getConfigurationname() != null)
                holder.getTvConfigName().setText(configData.getConfigurationname());

            if (configData.getGridLocationH() != null)
                holder.getTvRows().setText(String.valueOf(configData.getGridLocationH()));

            if (configData.getGridLocationV() != null)
                holder.getTvColumns().setText(String.valueOf(configData.getGridLocationV()));


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
        holder.getTvRows().setVisibility(View.VISIBLE);
        holder.getTvColumns().setVisibility(View.VISIBLE);
        holder.getTvTableName().setVisibility(View.VISIBLE);
        return convertView;
    }

    public class Holder {

        TextView tvTableName;
        TextView tvConfigName;
        TextView tvRows;
        TextView tvColumns;
        TextView tvEdit;
        ImageView imageViewEdit;

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

        public TextView getTvRows() {
            return tvRows;
        }

        public void setTvRows(TextView tvRows) {
            this.tvRows = tvRows;
        }

        public TextView getTvColumns() {
            return tvColumns;
        }

        public void setTvColumns(TextView tvColumns) {
            this.tvColumns = tvColumns;
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
