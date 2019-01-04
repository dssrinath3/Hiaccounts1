package in.hiaccounts.hinext.company_setup.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import in.hiaccounts.R;
import in.hiaccounts.hinext.company_setup.model.ConfigurationMenuType;

/**
 * Created by Admin on 11/15/2017.
 */

public class ConfigurationMenuAdapter extends RecyclerView.Adapter<ConfigurationMenuAdapter.ViewHolder> {

    private List<ConfigurationMenuType> selectedMenuList;

    public ConfigurationMenuAdapter(List<ConfigurationMenuType> menuTypeList) {
        this.selectedMenuList = menuTypeList;

    }

    // Create new views
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.adapter_configurationmenutype, null);

        // create ViewHolder

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        final int pos = position;

        ConfigurationMenuType menu=selectedMenuList.get(pos);

        if (menu!=null){

            if (menu.getProjectname()!=null)
                viewHolder.tvName.setText(menu.getProjectname());

            viewHolder.chkSelected.setChecked(menu.isStatus());

            viewHolder.chkSelected.setTag(selectedMenuList.get(pos));

        }


        viewHolder.chkSelected.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                ConfigurationMenuType menuType = (ConfigurationMenuType) cb.getTag();

                menuType.setStatus(cb.isChecked());
                selectedMenuList.get(pos).setStatus(cb.isChecked());
/*
                Toast.makeText(
                        v.getContext(),
                        "Clicked on Checkbox: " + cb.getText() + " is "
                                + cb.isChecked(), Toast.LENGTH_LONG).show();*/
            }
        });

    }

    // Return the size arraylist
    @Override
    public int getItemCount() {
        return selectedMenuList.size();
    }

    // method to access in activity after updating selection
    public List<ConfigurationMenuType> getMenuTypeList() {
        return selectedMenuList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName;
        public CheckBox chkSelected;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            tvName = itemLayoutView.findViewById(R.id.tvName);
            chkSelected = itemLayoutView.findViewById(R.id.chkSelected);
        }

    }

}
