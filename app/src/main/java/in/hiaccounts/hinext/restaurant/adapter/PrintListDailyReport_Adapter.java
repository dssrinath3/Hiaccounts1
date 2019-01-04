package in.hiaccounts.hinext.restaurant.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import in.hiaccounts.R;
import in.hiaccounts.hinext.restaurant.model.category_item.DailyReportItemData;

/**
 * Created by administrator on 15/2/18.
 */

public class PrintListDailyReport_Adapter extends BaseExpandableListAdapter {

    private static final String TAG = PrintListDailyReport_Adapter.class.getSimpleName();

    private Context context;
    private List<String> listDataHeader;
    private HashMap<String, List<DailyReportItemData>> listChildData;
    private LayoutInflater layoutInflater;


    public PrintListDailyReport_Adapter(Context context, List<String> listDataHeader, HashMap<String, List<DailyReportItemData>> listChildData) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listChildData = listChildData;
    }

    public List<String> get_listDataHeader() {
        return listDataHeader;
    }

    public void set_listDataHeader(List<String> listDataHeader) {
        this.listDataHeader = listDataHeader;
    }

    public HashMap<String, List<DailyReportItemData>> get_listDataChild() {
        return listChildData;
    }

    public void set_listDataChild(HashMap<String, List<DailyReportItemData>> listDataChild) {
        this.listChildData = listChildData;
    }

    /**/
    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.listChildData.get(this.listDataHeader.get(groupPosition)).get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final DailyReportItemData item = (DailyReportItemData) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.restaurent_dailyreport_items, null);
        }

        TextView tvItemName = convertView.findViewById(R.id.tvItemName);
        TextView tvQty = convertView.findViewById(R.id.tvQty);
        TextView tvTotalPrice = convertView.findViewById(R.id.tvTotalPrice);


        Log.e("ItemName..45",item.getItemName());

        tvItemName.setText(item.getItemName());
        tvQty.setText("" + item.getItemQuantity());
        tvTotalPrice.setText("" +String.format("%.2f",item.getItemTotalAmountInTax()));

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.listChildData.get(this.listDataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {


        String groupMenu = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.restaurent_dailyreport_items, null);
        }


        ((ExpandableListView) parent.findViewById(R.id.lvInvoiceItems)).expandGroup(groupPosition);


        TextView tvItemCategoryName = convertView.findViewById(R.id.tvItemName);
        tvItemCategoryName.setTypeface(null, Typeface.BOLD);
        tvItemCategoryName.setText(groupMenu);


        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


}
