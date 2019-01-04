package in.hiaccounts.hinext.controlpanel.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.hiaccounts.R;
import in.hiaccounts.hinext.controlpanel.activity.Activity_CoSetup_SearchHsn;
import in.hiaccounts.hinext.inventory.model.hsncode.SearchHsnCode;


/**
 * Custom adapter with "View Holder Pattern".
 *
 * @author Prateek
 */
public class SearchHsnAdapter extends BaseAdapter{

    private LayoutInflater layoutInflater;


    List<SearchHsnCode> hsncodeList;

    public SearchHsnAdapter(Context context, List<SearchHsnCode> hsncodeList) {
        super();
        layoutInflater = LayoutInflater.from(context);
        this.hsncodeList=hsncodeList;

    }
    @Override
    public int getCount() {
        return hsncodeList.size();
    }

    @Override
    public SearchHsnCode getItem(int position) {
        return hsncodeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {


        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.controlpanel_adapter_searchhsn, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }

        final SearchHsnCode searchHsnCode =getItem(position);


        viewHolder.tvCode.setVisibility(View.GONE);
        viewHolder.tvDescription.setVisibility(View.GONE);
        viewHolder.chkbx.setVisibility(View.GONE);



        if (searchHsnCode!=null) {

            if (searchHsnCode.getCode()!=null)
                viewHolder.tvCode.setText(searchHsnCode.getCode());
            if (searchHsnCode.getDescription()!=null)
                viewHolder.tvDescription.setText(searchHsnCode.getDescription());


            viewHolder.chkbx.setId(position);

            viewHolder.chkbx.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    CheckBox cb = (CheckBox) v;
                    int id = cb.getId();
                    if (Activity_CoSetup_SearchHsn.thumbnailsselection[id]) {
                        cb.setChecked(false);
                        Activity_CoSetup_SearchHsn.thumbnailsselection[id] = false;
                    } else {
                        cb.setChecked(true);
                        Activity_CoSetup_SearchHsn.thumbnailsselection[id] = true;
                    }
                }
            });

            viewHolder.chkbx.setChecked(Activity_CoSetup_SearchHsn.thumbnailsselection[position]);

            viewHolder.tvCode.setVisibility(View.VISIBLE);
            viewHolder.tvDescription.setVisibility(View.VISIBLE);
            viewHolder.chkbx.setVisibility(View.VISIBLE);
            viewHolder.chkbx.setVisibility(View.VISIBLE);

        }

        return convertView;
    }


    public class ViewHolder {

        @BindView(R.id.tvCode)
        TextView tvCode;
        @BindView(R.id.tvDescription)
        TextView tvDescription;
        @BindView(R.id.chkbx)
        CheckBox chkbx;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }
}
