package in.hiaccounts.hinext.crm.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import in.hiaccounts.R;
import in.hiaccounts.hinext.crm.activity.Activity_CrmSubmenu;
import in.hiaccounts.model.GridMenu;

/**
 * Created by administrator on 19/12/17.
 */

public class CrmMenuAdapter extends RecyclerView.Adapter<CrmMenuAdapter.RecyclerViewHolder> {
    Activity mActivity;
    private ArrayList<GridMenu> menuList;
    private Context context;

    public CrmMenuAdapter(Context context,ArrayList<GridMenu> menuList) {
        this.menuList = menuList;
        this.context = context;
        mActivity = (Activity) context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_crmmenuview, null);
        RecyclerViewHolder rcv = new RecyclerViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.tvMenuTitle.setText(menuList.get(position).getMenuTitle());
        holder.imgviewCompanyLogo.setImageResource(menuList.get(position).getResId());
    }

    @Override
    public int getItemCount() {
        return this.menuList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvMenuTitle;
        public ImageView imgviewCompanyLogo;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imgviewCompanyLogo = itemView.findViewById(R.id.imgviewCompanyLogo);
            tvMenuTitle = itemView.findViewById(R.id.tvMenuTitle);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(mActivity, Activity_CrmSubmenu.class);
            intent.putExtra("menu", menuList.get(getPosition()).getMenuTitle());
            mActivity.startActivity(intent);
        }
    }
}
