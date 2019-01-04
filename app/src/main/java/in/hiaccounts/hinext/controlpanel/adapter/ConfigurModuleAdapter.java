package in.hiaccounts.hinext.controlpanel.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import in.hiaccounts.R;
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.configuration.ProjectModuleDTOList;

/**
 * Created by shrinath on 11/25/2017.
 */

public class ConfigurModuleAdapter extends BaseAdapter {
    private Activity activity;
    List<ProjectModuleDTOList> lists;
    private LayoutInflater layoutInflater;

    public ConfigurModuleAdapter(Activity activity, List<ProjectModuleDTOList> lists) {
        this.activity = activity;
        this.lists = lists;
        layoutInflater = LayoutInflater.from(activity);
    }

    @Override
    public int getCount() {

        return lists.size();
    }

    @Override
    public ProjectModuleDTOList getItem(int i) {
        return lists.get(i);
    }

    @Override
    public long getItemId(int i) {
       return lists.indexOf(getItem(i));
    }

    @Override
    public View getView(final int position, View view, final ViewGroup viewGroup) {
     ViewHolder viewHolder = null;

        ProjectModuleDTOList items = lists.get(position);

        if(view == null) {
            viewHolder = new ViewHolder();
            view = layoutInflater.inflate(R.layout.module_configure_list_items, null);
            viewHolder.checkBox = view.findViewById(R.id.id_moduleCheck);
            viewHolder.name = view.findViewById(R.id.id_moduleName);
            view.setTag(viewHolder);
        }else
            viewHolder = (ViewHolder) view.getTag();
        viewHolder.name.setText(items.getProjectname());
        if(items.getStatus()){
            viewHolder.checkBox.setChecked(true);
        }
        else {
            viewHolder.checkBox.setChecked(false);
        }


        final ViewHolder finalViewHolder = viewHolder;
     /*   viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UtilView.showToast(activity,"Chcekbox status "+ finalViewHolder.checkBox.isChecked());


            }
        });*/
        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ListView) viewGroup).performItemClick(view, position, 0);
            }
        });
        return view;
    }

    public class ViewHolder{
        TextView name;
        CheckBox checkBox;
    }


}
