package in.hiaccounts.hinext.sales.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import in.hiaccounts.R;
import in.hiaccounts.hinext.sales.model.checkout.SelectedItemsList;

/**
 * Created by Prateek on 2/10/2017.
 */

public class SalesShowStockItems_Adapter extends BaseAdapter {

    Activity activity;
    List<Object> objectList;
    LayoutInflater layoutInflater;

    public SalesShowStockItems_Adapter(Activity activity, List<Object> objectList) {
        super();
        this.activity = activity;
        this.objectList = objectList;
        layoutInflater = LayoutInflater.from(activity);
    }

    @Override
    public int getCount() {
        return objectList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = layoutInflater.inflate(R.layout.adapter_showstockitems, null);
        TextView tv_itemcategory = v.findViewById(R.id.tv_itemcategoryname);
        TextView tv_itmecode = v.findViewById(R.id.tv_itemcode);
        TextView tv_itemname = v.findViewById(R.id.tv_itemname);
        TextView tv_itmeavailablequantity = v.findViewById(R.id.tv_itmeavailablequantity);
        tv_itemcategory.setVisibility(View.GONE);
        tv_itmecode.setVisibility(View.GONE);
        tv_itemname.setVisibility(View.GONE);
        tv_itmeavailablequantity.setVisibility(View.GONE);


        if (objectList.get(position) instanceof SelectedItemsList) {
            SelectedItemsList item = (SelectedItemsList) objectList.get(position);
            if (item.getItemCategoryName() != null) {
                tv_itemcategory.setText(item.getItemCategoryName());
            }
            if (item.getItemCode() != null) {
                tv_itmecode.setText(item.getItemCode());
            }
            if (item.getItemName() != null) {
                tv_itemname.setText(item.getItemName());
            }
            if (item.getStock() != 0) {
                tv_itmeavailablequantity.setText("" + item.getStock());
            }
            if (item.getStock() == 0) {
                tv_itmeavailablequantity.setText("0");
            }
            tv_itemcategory.setVisibility(View.VISIBLE);
            tv_itmecode.setVisibility(View.VISIBLE);
            tv_itemname.setVisibility(View.VISIBLE);
            tv_itmeavailablequantity.setVisibility(View.VISIBLE);
        }

        return v;
    }

}

