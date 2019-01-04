package in.hiaccounts.hinext.inventory.adapter;

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
import in.hiaccounts.hinext.inventory.activity.Activity_InventorySubmenu;
import in.hiaccounts.model.GridMenu;

/**
 * Created by Prateek on 7/10/2017.
 */

public class InventoryMenuAdapter extends RecyclerView.Adapter<InventoryMenuAdapter.RecyclerViewHolders> {

    private ArrayList<GridMenu> menuList;
    private Context context;
    Activity mActivity;

    public InventoryMenuAdapter(Context context, ArrayList<GridMenu> menuList) {
        this.menuList = menuList;
        this.context = context;
        mActivity = (Activity) context;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_inventorymenuview, null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        holder.tvMenuTitle.setText(menuList.get(position).getMenuTitle());
        holder.imgviewCompanyLogo.setImageResource(menuList.get(position).getResId());
    }

    @Override
    public int getItemCount() {
        return this.menuList.size();
    }

    class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView tvMenuTitle;
        public ImageView imgviewCompanyLogo;

        public RecyclerViewHolders(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imgviewCompanyLogo = itemView.findViewById(R.id.imgviewCompanyLogo);
            tvMenuTitle = itemView.findViewById(R.id.tvMenuTitle);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(mActivity, Activity_InventorySubmenu.class);
            intent.putExtra("menu", menuList.get(getPosition()).getMenuTitle());
            mActivity.startActivity(intent);
        }
    }
}
