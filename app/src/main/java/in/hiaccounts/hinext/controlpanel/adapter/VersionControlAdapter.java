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
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.versioncontrol.VersionControlData;

/**
 * Created by shrinath on 11/27/2017.
 */

public class VersionControlAdapter extends ArrayAdapter<VersionControlData> {
    private LayoutInflater layoutInflater;
    private Activity activity;
    List<VersionControlData> versionControlDataList;

    public VersionControlAdapter(Activity activity, List<VersionControlData> versionControlDataList) {
        super(activity, 0, versionControlDataList);
        this.activity = activity;
        this.versionControlDataList = versionControlDataList;
        layoutInflater = LayoutInflater.from(activity);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup viewGroup) {
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = layoutInflater.inflate(R.layout.controlpanel_adapter_versioncontrol, null);

            holder.setTvProjectName((TextView) convertView.findViewById(R.id.tvProjectName));
            holder.setTvProjectDesc((TextView) convertView.findViewById(R.id.tvProjectDesc));
            holder.setTvPermissions((TextView) convertView.findViewById(R.id.tvEditPermissions));
            holder.setTvEdit((TextView) convertView.findViewById(R.id.tvEdit));
            holder.setImageViewPermissions((ImageView) convertView.findViewById(R.id.imgviewEditPermissions));
            holder.setImgviewEdit((ImageView) convertView.findViewById(R.id.imgviewEdit));

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.getTvProjectName().setVisibility(View.GONE);
        holder.getTvProjectDesc().setVisibility(View.GONE);
        holder.getTvPermissions().setVisibility(View.GONE);
        holder.getTvEdit().setVisibility(View.GONE);
        holder.getImageViewPermissions().setVisibility(View.GONE);
        holder.getImgviewEdit().setVisibility(View.GONE);
       final VersionControlData versionData = getItem(position);

            if (versionData != null) {
                if (versionData.getProjectname() != null)
                    holder.getTvProjectName().setText(versionData.getProjectname());
                if (versionData.getDescription() != null)
                    holder.getTvProjectDesc().setText(versionData.getDescription());


                holder.getImageViewPermissions().setVisibility(View.VISIBLE);
                holder.getImgviewEdit().setVisibility(View.VISIBLE);
                holder.getTvProjectName().setVisibility(View.VISIBLE);
                holder.getTvProjectDesc().setVisibility(View.VISIBLE);
            }

            holder.getImgviewEdit().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((ListView) viewGroup).performItemClick(v, position, 0);

                }
            });
        holder.getImageViewPermissions().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ListView) viewGroup).performItemClick(v, position, 0);

            }
        });



        return convertView;
    }

    public class Holder {


        TextView tvProjectName;
        TextView tvProjectDesc;
        TextView tvPermissions;
        TextView tvEdit;

        ImageView imageViewPermissions;
        ImageView imgviewEdit;

        public TextView getTvProjectName() {
            return tvProjectName;
        }

        public void setTvProjectName(TextView tvProjectName) {
            this.tvProjectName = tvProjectName;
        }

        public TextView getTvProjectDesc() {
            return tvProjectDesc;
        }

        public void setTvProjectDesc(TextView tvProjectDesc) {
            this.tvProjectDesc = tvProjectDesc;
        }

        public TextView getTvPermissions() {
            return tvPermissions;
        }

        public void setTvPermissions(TextView tvPermissions) {
            this.tvPermissions = tvPermissions;
        }

        public TextView getTvEdit() {
            return tvEdit;
        }

        public void setTvEdit(TextView tvEdit) {
            this.tvEdit = tvEdit;
        }

        public ImageView getImageViewPermissions() {
            return imageViewPermissions;
        }

        public void setImageViewPermissions(ImageView imageViewPermissions) {
            this.imageViewPermissions = imageViewPermissions;
        }

        public ImageView getImgviewEdit() {
            return imgviewEdit;
        }

        public void setImgviewEdit(ImageView imgviewEdit) {
            this.imgviewEdit = imgviewEdit;
        }
    }
}
