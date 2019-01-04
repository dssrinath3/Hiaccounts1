package in.hiaccounts.hinext.controlpanel.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import in.hiaccounts.R;
import in.hiaccounts.hinext.controlpanel.activity.Activity_ControlPanelSubmenu;
import in.hiaccounts.model.GridMenu;

/**
 * Created by Prateek on 7/10/2017.
 */

public class ControlPanelMenuAdapter extends RecyclerView.Adapter<ControlPanelMenuAdapter.RecyclerViewHolders> {

    private ArrayList<GridMenu> menuList;
    private Context context;
    Activity mActivity;

    public ControlPanelMenuAdapter(Context context, ArrayList<GridMenu> menuList) {
        this.menuList = menuList;
        this.context = context;
        mActivity=(Activity)context;
    }



    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_inventorymenuview, null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        return rcv;

    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {

        if (menuList!=null){
            if (menuList.get(position)!=null){
                if (menuList.get(position).getMenuTitle()!=null)
                    holder.tvMenuTitle.setText(menuList.get(position).getMenuTitle());

                    holder.imgviewCompanyLogo.setImageResource(menuList.get(position).getResId());

            }
        }



    }

    @Override
    public int getItemCount() {
        return this.menuList.size();
    }

    class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{

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
            Intent intent=new Intent(mActivity,Activity_ControlPanelSubmenu.class);
            intent.putExtra("menu",menuList.get(getPosition()).getMenuTitle());
            mActivity.startActivity(intent);
        }
    }
}
