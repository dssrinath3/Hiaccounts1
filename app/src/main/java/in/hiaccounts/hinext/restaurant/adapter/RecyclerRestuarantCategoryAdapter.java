package in.hiaccounts.hinext.restaurant.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import in.hiaccounts.R;
import in.hiaccounts.hinext.restaurant.model.restaurant_pagedata.ItemCategory;

/**
 * Created by shrinath on 11/21/2017.
 */

public class RecyclerRestuarantCategoryAdapter extends RecyclerView.Adapter<RecyclerRestuarantCategoryAdapter.RestuarantViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(ItemCategory item);

    }
    private final OnItemClickListener listener;
    private List<ItemCategory> categoryModels;

    List<String> itemColors = new ArrayList<>();
    private Context context;
    Activity mActivity;
    private String serverUrl;
    private Random mRandom = new Random();
    public RecyclerRestuarantCategoryAdapter(Activity mActivity, List<ItemCategory> categoryModels, OnItemClickListener listener) {
        this.categoryModels = categoryModels;
        this.mActivity = mActivity;
        this.listener=listener;
    }

    @Override
    public RestuarantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.restuarant_category_list_items, parent, false);
        RestuarantViewHolder myViewHolder = new RestuarantViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RestuarantViewHolder holder, int position) {
       // holder.categoryName.setText(categoryModels.get(position).getItemCategoryName());
        holder.bind(categoryModels.get(position),listener);


/*
        holder.categoryName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              *//*  Bundle bundle=new Bundle();
                bundle.putString("itemId", "From Activity");
                Fragment_Restaurant fragobj=new Fragment_Restaurant();
                fragobj.setArguments(bundle);
                Toast.makeText(mActivity, ""+categoryModels.get(position).getItemCategoryId(), Toast.LENGTH_SHORT).show();
                ((AppCompatActivity) mActivity).getSupportFragmentManager().beginTransaction().replace(R.id.restaaurent, new Fragment_Restaurant()).commit();
*//*
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return categoryModels.size();
    }




    public class RestuarantViewHolder extends RecyclerView.ViewHolder {
        private TextView categoryName;
        CardView cardView;
        public RestuarantViewHolder(View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.id_categoryName);
            cardView=itemView.findViewById(R.id.card_view);
        }

        public void bind(final ItemCategory itemCategory, final OnItemClickListener listener) {
            categoryName.setText(itemCategory.getItemCategoryName());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(itemCategory);
                }
            });


            if (itemCategory.getItemCategoryName()!=null) {

            /*    itemColors.add(itemCategory.getItemCategoryName());
                Log.e("itemColors", String.valueOf(itemColors.size()));
*/

                   switch (itemCategory.getItemCategoryName()){

                 case "LASSI":
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
                        break;

                }
            }
     /*       String[] colorsTxt = mActivity.getResources().getStringArray(R.array.colors);

            if (itemColors!=null && itemColors.size()>0){
                for (int i=0;i<itemColors.size();i++){
                    if (itemColors.get(i).equals(itemCategory.getItemCategoryName())){
                        Log.e("itemColorsName", itemColors.get(i));
                        try {
                            if (colorsTxt!=null && colorsTxt.length > 0) {
                                int newColor = Color.parseColor(colorsTxt[i]);
                                cardView.setCardBackgroundColor(newColor);
                            }
                        }catch (Exception e){

                        }


                    }
                }
            }*/

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
