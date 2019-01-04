package in.hiaccounts.hinext.restaurant.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import in.hiaccounts.R;
import in.hiaccounts.hinext.restaurant.model.restaurant_pagedata.SubRow;
import in.hiaccounts.hinext.restaurant.model.restaurant_pagedata.TableRowData;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;

/**
 * Created by administrator on 17/2/18.
 */

public class RecyclerRestuarantTableAdapter extends RecyclerView.Adapter<RecyclerRestuarantTableAdapter.RestuarantViewHolder> {
    public interface OnItemClickListener {
        void onItemClick(SubRow item);

    }
    private final OnItemClickListener listener;
    private List<SubRow> tableModels;
    private Context context;
    Activity mActivity;
    private SharedPreference sharedPreference;
    private List<SubRow> tableBooked;


    public RecyclerRestuarantTableAdapter(Activity mActivity, List<SubRow> tableModels, OnItemClickListener listener) {
        sharedPreference = SharedPreference.getInstance(mActivity);
        this.tableModels = tableModels;
        this.mActivity = mActivity;
        this.listener=listener;

    }

    @Override
    public RecyclerRestuarantTableAdapter.RestuarantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.restaurent_table_listview, parent, false);
        RecyclerRestuarantTableAdapter.RestuarantViewHolder myViewHolder = new RecyclerRestuarantTableAdapter.RestuarantViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerRestuarantTableAdapter.RestuarantViewHolder holder, int position) {
        SubRow subRow = tableModels.get(position);




        if (subRow.getOccupied()!=null && subRow.getOccupied().equals("red")){
            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(mActivity, R.color.colorNoIceSugar));

        }else if (subRow.getOccupied()!=null && subRow.getOccupied().equals("green")){
            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(mActivity, R.color.colorKulfi));

        }else if (subRow.getOccupied()!=null && subRow.getOccupied().equals("yellow")){
            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(mActivity, R.color.colorFalloda));

        }


        // Log.e("TableName position", String.valueOf(position));
        Log.e("Tablesta in grid", String.valueOf(subRow.getSelected()));
        Log.e("TableName in grid",subRow.getTableName());
        holder.tableName.setText(tableModels.get(position).getTableName());

        holder.bind(tableModels.get(position),listener);


    }

    @Override
    public int getItemCount() {
        return tableModels.size();
    }




    public class RestuarantViewHolder extends RecyclerView.ViewHolder {
        private TextView tableName;
        CardView cardView;
        public RestuarantViewHolder(View itemView) {
            super(itemView);
            tableName = itemView.findViewById(R.id.id_tableName);
            cardView=itemView.findViewById(R.id.card_view);
        }

        public void bind(final SubRow restuarentTableData, final RecyclerRestuarantTableAdapter.OnItemClickListener listener) {
            tableName.setText(restuarentTableData.getTableName());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(restuarentTableData);
                }
            });


        }
    }
}
