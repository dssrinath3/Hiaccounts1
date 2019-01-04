package in.hiaccounts.hinext.restaurant.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

import in.hiaccounts.R;
import in.hiaccounts.hinext.restaurant.model.category_item.SelectedItemsList;
import in.hiaccounts.utility.UtilView;

/**
 * Created by shrinath on 11/22/2017.
 */

public class RecyclerRestuarantItemsAdapter extends RecyclerView.Adapter<RecyclerRestuarantItemsAdapter.RestuarantViewHolder> {


    private final OnItemClickListener listener;
    Activity mActivity;
    boolean isImageShow;
    private List<SelectedItemsList> itemModels;
    private Context context;
    private String serverUrl;

    public RecyclerRestuarantItemsAdapter(boolean isImageShow, Activity mActivity, List<SelectedItemsList> itemModels, OnItemClickListener listener) {
        this.itemModels = itemModels;
        this.mActivity = mActivity;
        this.listener=listener;
        this.isImageShow=isImageShow;
    }

    public boolean isImageShow() {
        return isImageShow;
    }

    public void setImageShow(boolean imageShow) {
        isImageShow = imageShow;
    }

    @Override
    public RestuarantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.restaurent_item_listview, parent, false);
        RestuarantViewHolder myViewHolder = new RestuarantViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RestuarantViewHolder holder, int position) {
        holder.bind(itemModels.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return itemModels.size();
    }

    public void showItemWithImgaes(boolean isImageShow) {
        setImageShow(isImageShow);
        notifyDataSetChanged();
    }


    public interface OnItemClickListener {
        void onItemClick(SelectedItemsList item);

    }

    public class RestuarantViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageItem;
        private TextView itemName;
        private TextView tvItemPrice;
        private LinearLayout llItemDetail;
        public RestuarantViewHolder(View itemView) {
            super(itemView);
            imageItem = itemView.findViewById(R.id.imgviewItem);
            itemName = itemView.findViewById(R.id.id_ItemName);
            tvItemPrice=itemView.findViewById(R.id.tvItemPrice);
            llItemDetail=itemView.findViewById(R.id.llItemDetail);
        }

        public void bind(final SelectedItemsList restaurentItemModel, final OnItemClickListener listener) {
            if (restaurentItemModel!=null) {
                if (restaurentItemModel.getItemName() != null)
                    itemName.setText(restaurentItemModel.getItemName());

                if (restaurentItemModel.getSalesPrice()!=null) {
                    tvItemPrice.setText("" + restaurentItemModel.getSalesPrice() + " Rs");
                }else {
                    tvItemPrice.setText("0.00 Rs");
                }
                String url = UtilView.getUrl(mActivity);
                if (url != null) {
                    Picasso.with(mActivity).load(url + restaurentItemModel.getFileName())
                            .networkPolicy(NetworkPolicy.NO_CACHE)
                            .memoryPolicy(MemoryPolicy.NO_CACHE)
                            .placeholder(R.drawable.ic_progress)
                            .fit()
                            .error(R.drawable.ic_noimage)
                            .into(imageItem);
                } else {
                    UtilView.gotToLogin(mActivity);
                }
                if (isImageShow()){
                    imageItem.setVisibility(View.VISIBLE);
                }else {
                    imageItem.setVisibility(View.GONE);
                }


                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onItemClick(restaurentItemModel);
                    }
                });

                if (restaurentItemModel.getItemCategoryName()!=null) {
                    switch (restaurentItemModel.getItemCategoryName()) {

                        case "LASSI":
                            llItemDetail.setBackgroundColor(mActivity.getResources().getColor(R.color.colorLassi));
                            tvItemPrice.setTextColor(mActivity.getResources().getColor(R.color.colorWhite));
                            itemName.setTextColor(mActivity.getResources().getColor(R.color.colorWhite));
                            break;

                        case "FALOODA":
                            llItemDetail.setBackgroundColor(mActivity.getResources().getColor(R.color.colorFalloda));
                            tvItemPrice.setTextColor(mActivity.getResources().getColor(R.color.colorWhite));
                            itemName.setTextColor(mActivity.getResources().getColor(R.color.colorWhite));
                            break;

                        case "KULFI":
                            llItemDetail.setBackgroundColor(mActivity.getResources().getColor(R.color.colorKulfi));
                            tvItemPrice.setTextColor(mActivity.getResources().getColor(R.color.colorWhite));
                            itemName.setTextColor(mActivity.getResources().getColor(R.color.colorWhite));
                            break;

                        case "PROTEIN":
                            llItemDetail.setBackgroundColor(mActivity.getResources().getColor(R.color.colorProtein));
                            tvItemPrice.setTextColor(mActivity.getResources().getColor(R.color.colorWhite));
                            itemName.setTextColor(mActivity.getResources().getColor(R.color.colorWhite));
                            break;

                        case "ICE CREAM":
                            llItemDetail.setBackgroundColor(mActivity.getResources().getColor(R.color.colorIcecream));
                            tvItemPrice.setTextColor(mActivity.getResources().getColor(R.color.colorWhite));
                            itemName.setTextColor(mActivity.getResources().getColor(R.color.colorWhite));
                            break;

                        case "JUICE":
                            llItemDetail.setBackgroundColor(mActivity.getResources().getColor(R.color.colorJuice));
                            tvItemPrice.setTextColor(mActivity.getResources().getColor(R.color.colorWhite));
                            itemName.setTextColor(mActivity.getResources().getColor(R.color.colorWhite));
                            break;

                        case "MOCKTAILS 30 50":
                            llItemDetail.setBackgroundColor(mActivity.getResources().getColor(R.color.colorMocktails));
                            tvItemPrice.setTextColor(mActivity.getResources().getColor(R.color.colorWhite));
                            itemName.setTextColor(mActivity.getResources().getColor(R.color.colorWhite));
                            break;
                        case "MOCKTAILS 60 90":
                            llItemDetail.setBackgroundColor(mActivity.getResources().getColor(R.color.colorMocktails));
                            tvItemPrice.setTextColor(mActivity.getResources().getColor(R.color.colorWhite));
                            itemName.setTextColor(mActivity.getResources().getColor(R.color.colorWhite));
                            break;

                        case "SMOOTHIE":
                            llItemDetail.setBackgroundColor(mActivity.getResources().getColor(R.color.colorSmoothie));
                            tvItemPrice.setTextColor(mActivity.getResources().getColor(R.color.colorWhite));
                            itemName.setTextColor(mActivity.getResources().getColor(R.color.colorWhite));
                            break;

                        case "NO ICE SUGAR WATER":
                            llItemDetail.setBackgroundColor(mActivity.getResources().getColor(R.color.colorNoIceSugar));
                            tvItemPrice.setTextColor(mActivity.getResources().getColor(R.color.colorWhite));
                            itemName.setTextColor(mActivity.getResources().getColor(R.color.colorWhite));
                            break;
                        case "COLD COFFEE":
                            llItemDetail.setBackgroundColor(mActivity.getResources().getColor(R.color.colorColdCoffee));
                            tvItemPrice.setTextColor(mActivity.getResources().getColor(R.color.colorWhite));
                            itemName.setTextColor(mActivity.getResources().getColor(R.color.colorWhite));
                            break;

                    }
                }

            }
        }
    }
}
