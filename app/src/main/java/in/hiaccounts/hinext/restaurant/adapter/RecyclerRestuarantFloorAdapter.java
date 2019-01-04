package in.hiaccounts.hinext.restaurant.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import in.hiaccounts.R;

import in.hiaccounts.hinext.restaurant.model.restaurant_pagedata.RestuarentFloorData;

/**
 * Created by administrator on 17/2/18.
 */

public class RecyclerRestuarantFloorAdapter extends RecyclerView.Adapter<RecyclerRestuarantFloorAdapter.RestuarantViewHolder> {
    public interface OnItemClickListener {
        void onItemClick(RestuarentFloorData item);

    }
    private final OnItemClickListener listener;
    private List<RestuarentFloorData> floorModels;
    private Context context;
    Activity mActivity;

    public RecyclerRestuarantFloorAdapter(Activity mActivity, List<RestuarentFloorData> floorModels, OnItemClickListener listener) {
        this.floorModels = floorModels;
        this.mActivity = mActivity;
        this.listener=listener;
    }

    @Override
    public RecyclerRestuarantFloorAdapter.RestuarantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.restuarant_floor_list_items, parent, false);
        RecyclerRestuarantFloorAdapter.RestuarantViewHolder myViewHolder = new RecyclerRestuarantFloorAdapter.RestuarantViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerRestuarantFloorAdapter.RestuarantViewHolder holder, int position) {
         holder.floorName.setText(floorModels.get(position).getConfigurationname());
        holder.bind(floorModels.get(position),listener);


    }

    @Override
    public int getItemCount() {
        return floorModels.size();
    }




    public class RestuarantViewHolder extends RecyclerView.ViewHolder {
        private TextView floorName;
        CardView cardView;
        public RestuarantViewHolder(View itemView) {
            super(itemView);
            floorName = itemView.findViewById(R.id.id_floorName);
            cardView=itemView.findViewById(R.id.card_view);
        }

        public void bind(final RestuarentFloorData restuarentFloorData, final RecyclerRestuarantFloorAdapter.OnItemClickListener listener) {
            floorName.setText(restuarentFloorData.getConfigurationname());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(restuarentFloorData);
                }
            });


            if (restuarentFloorData.getConfigurationname()!=null) {
                switch (restuarentFloorData.getConfigurationname()){

                   /* case "LASSI":
                        cardView.setCardBackgroundColor(mActivity.getResources().getColor(R.color.colorLassi));
                        break;

                    case "FALOODA":
                        cardView.setCardBackgroundColor(mActivity.getResources().getColor(R.color.colorFalloda));
                        break;

                    case "KULFI":
                        cardView.setCardBackgroundColor(mActivity.getResources().getColor(R.color.colorKulfi));
                        break;

                    case "PROTEIN":
                        cardView.setCardBackgroundColor(mActivity.getResources().getColor(R.color.colorProtein));
                        break;

                    case "ICE CREAM":
                        cardView.setCardBackgroundColor(mActivity.getResources().getColor(R.color.colorIcecream));
                        break;

                    case "JUICE":
                        cardView.setCardBackgroundColor(mActivity.getResources().getColor(R.color.colorJuice));
                        break;

                    case "MOCKTAILS 30 50":
                        cardView.setCardBackgroundColor(mActivity.getResources().getColor(R.color.colorMocktails));
                        break;

                    case "MOCKTAILS 60 90":
                        cardView.setCardBackgroundColor(mActivity.getResources().getColor(R.color.colorMocktails));
                        break;

                    case "SMOOTHIE":
                        cardView.setCardBackgroundColor(mActivity.getResources().getColor(R.color.colorSmoothie));
                        break;

                    case "NO ICE SUGAR WATER":
                        cardView.setCardBackgroundColor(mActivity.getResources().getColor(R.color.colorNoIceSugar));
                        break;
                    case "COLD COFFEE":
                        cardView.setCardBackgroundColor(mActivity.getResources().getColor(R.color.colorColdCoffee));
                        break;*/

                }
            }


/*
            Picasso.with(itemView.getContext()).load(item.imageUrl).into(image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });

*/


        }
    }

}
