package in.hiaccounts.hinext.controlpanel.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import in.hiaccounts.R;
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.state.AddStateDatum;

/**
 * Created by administrator on 30/1/18.
 */

public class StateAdapter extends ArrayAdapter<AddStateDatum> {
    private LayoutInflater layoutInflater;
    private Activity activity;
    List<AddStateDatum> stateDatumList;

    public StateAdapter(Activity activity, List<AddStateDatum> stateDatumList) {
        super(activity, 0, stateDatumList);
        this.activity = activity;
        this.stateDatumList = stateDatumList;
        layoutInflater = LayoutInflater.from(activity);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup viewGroup) {
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = layoutInflater.inflate(R.layout.controlpanel_adapter_state, null);

            holder.setTvCountryName((TextView) convertView.findViewById(R.id.tvCountryName));
            holder.setTvStateName((TextView) convertView.findViewById(R.id.tvStateName));
            holder.setTvStatus((TextView) convertView.findViewById(R.id.tvStatus));
            holder.setTvEdit((TextView) convertView.findViewById(R.id.tvEdit));

            holder.setImgviewEdit((ImageView) convertView.findViewById(R.id.imgviewEdit));

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.getTvCountryName().setVisibility(View.GONE);
        holder.getTvStateName().setVisibility(View.GONE);
        holder.getTvStatus().setVisibility(View.GONE);
        holder.getTvEdit().setVisibility(View.GONE);
        holder.getImgviewEdit().setVisibility(View.GONE);


        final AddStateDatum stateDatum = getItem(position);

        if (stateDatum != null) {
            if (stateDatum.getCountryId() != null)
                holder.getTvCountryName().setText(stateDatum.getCountryId().getCountryName());

            if (stateDatum.getStateName() != null)
                holder.getTvStateName().setText(stateDatum.getStateName());

            if (stateDatum.getStatus() != null)
                holder.getTvStatus().setText(stateDatum.getStatus());

            holder.getTvCountryName().setVisibility(View.VISIBLE);
            holder.getTvStateName().setVisibility(View.VISIBLE);
            holder.getTvStatus().setVisibility(View.VISIBLE);
            holder.getTvEdit().setVisibility(View.GONE);
            holder.getImgviewEdit().setVisibility(View.VISIBLE);
        }

        holder.getImgviewEdit().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ListView) viewGroup).performItemClick(v, position, 0);

            }
        });




        return convertView;
    }

    public class Holder {


        TextView tvCountryName;
        TextView tvStateName;
        TextView tvStatus;
        TextView tvEdit;
        ImageView imgviewEdit;

        public TextView getTvCountryName() {
            return tvCountryName;
        }

        public void setTvCountryName(TextView tvCountryName) {
            this.tvCountryName = tvCountryName;
        }

        public TextView getTvStateName() {
            return tvStateName;
        }

        public void setTvStateName(TextView tvStateName) {
            this.tvStateName = tvStateName;
        }

        public TextView getTvStatus() {
            return tvStatus;
        }

        public void setTvStatus(TextView tvStatus) {
            this.tvStatus = tvStatus;
        }

        public TextView getTvEdit() {
            return tvEdit;
        }

        public void setTvEdit(TextView tvEdit) {
            this.tvEdit = tvEdit;
        }

        public ImageView getImgviewEdit() {
            return imgviewEdit;
        }

        public void setImgviewEdit(ImageView imgviewEdit) {
            this.imgviewEdit = imgviewEdit;
        }
    }
}
