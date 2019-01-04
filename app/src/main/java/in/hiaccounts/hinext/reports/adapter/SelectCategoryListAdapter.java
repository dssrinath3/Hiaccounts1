package in.hiaccounts.hinext.reports.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import in.hiaccounts.R;
import in.hiaccounts.hinext.reports.model.CategoryList;

public class SelectCategoryListAdapter extends ArrayAdapter<CategoryList> {
    private LayoutInflater layoutInflater;
    private Activity activity;
    List<CategoryList> empDataList;

    public SelectCategoryListAdapter(Activity activity,List<CategoryList> empDataList) {
        super(activity, 0,empDataList);
        this.activity = activity;
        this.empDataList = empDataList;
        layoutInflater = LayoutInflater.from(activity);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        // holder pattern
        SelectCategoryListAdapter.Holder holder = null;
        if (convertView == null) {
            holder = new SelectCategoryListAdapter.Holder();

            convertView = layoutInflater.inflate(R.layout.adapter_category_list, null);

            holder.setTvCategoryId((TextView) convertView.findViewById(R.id.tvCategoryId));
            holder.setTvCategoryName((TextView) convertView.findViewById(R.id.tvCategoryName));
            holder.setTvCategoryDesc((TextView) convertView.findViewById(R.id.tvCategoryDesc));

            convertView.setTag(holder);
        } else {
            holder = (SelectCategoryListAdapter.Holder) convertView.getTag();
        }


        holder.getTvCategoryId().setVisibility(View.GONE);
        holder.getTvCategoryName().setVisibility(View.GONE);
        holder.getTvCategoryDesc().setVisibility(View.GONE);

        final CategoryList categoryData = getItem(position);

        if (categoryData != null) {
            if (categoryData.getItemCategoryId() != null)
                holder.getTvCategoryId().setText(categoryData.getItemCategoryCode());
            if (categoryData.getItemCategoryName() != null)
                holder.getTvCategoryName().setText(categoryData.getItemCategoryName());
            if (categoryData.getItemCategoryDesc() != null)
                holder.getTvCategoryDesc().setText(""+categoryData.getItemCategoryDesc());


            holder.getTvCategoryId().setVisibility(View.VISIBLE);
            holder.getTvCategoryName().setVisibility(View.VISIBLE);
            holder.getTvCategoryDesc().setVisibility(View.VISIBLE);

        }



        return convertView;
    }



    public class Holder {


        TextView tvCategoryId;
        TextView tvCategoryName;
        TextView tvCategoryDesc;

        public TextView getTvCategoryId() {
            return tvCategoryId;
        }

        public void setTvCategoryId(TextView tvCategoryId) {
            this.tvCategoryId = tvCategoryId;
        }

        public TextView getTvCategoryName() {
            return tvCategoryName;
        }

        public void setTvCategoryName(TextView tvCategoryName) {
            this.tvCategoryName = tvCategoryName;
        }

        public TextView getTvCategoryDesc() {
            return tvCategoryDesc;
        }

        public void setTvCategoryDesc(TextView tvCategoryDesc) {
            this.tvCategoryDesc = tvCategoryDesc;
        }
    }
}
