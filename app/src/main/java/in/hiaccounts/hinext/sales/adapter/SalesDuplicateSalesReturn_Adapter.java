package in.hiaccounts.hinext.sales.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import in.hiaccounts.R;
import in.hiaccounts.hinext.sales.model.printlist.duplicate_salesreturn.Duplicate_SalesReturn;

/**
 * Created by Prateek on 2/10/2017.
 */

public class SalesDuplicateSalesReturn_Adapter extends BaseAdapter implements Filterable {
    private List<Duplicate_SalesReturn> duplicateReturnList;
    private LayoutInflater layoutInflater;


    private ItemFilter mFilter = new ItemFilter();
    private List<Duplicate_SalesReturn> searchInvoiceList;

    public SalesDuplicateSalesReturn_Adapter(Activity activity, List<Duplicate_SalesReturn> duplicateReturnList) {
        super();
        this.duplicateReturnList = duplicateReturnList;
        layoutInflater = LayoutInflater.from(activity);

        searchInvoiceList=new ArrayList<>();
        searchInvoiceList=duplicateReturnList;
    }

    @Override
    public int getCount() {
        return duplicateReturnList.size();
    }

    @Override
    public Duplicate_SalesReturn getItem(int position) {
        return duplicateReturnList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View v = layoutInflater.inflate(R.layout.adapter_sales_duplicatereturn, null);

        TextView tv_formno = v.findViewById(R.id.tv_formno);
        TextView tv_customername = v.findViewById(R.id.tv_customername);
        TextView tv_totalamount = v.findViewById(R.id.tv_totalamount);
        final ImageView imgview_duplicateprint = v.findViewById(R.id.imgview_duplicateprint);




        imgview_duplicateprint.setVisibility(View.GONE);
        tv_customername.setVisibility(View.GONE);
        tv_totalamount.setVisibility(View.GONE);
        tv_formno.setVisibility(View.GONE);


        Duplicate_SalesReturn orderDetail = getItem(position);
        if (orderDetail != null) {
            if (orderDetail.getCustomerName() != null)
                tv_customername.setText(orderDetail.getCustomerName());
            if (orderDetail.getFormNo() != null)
                tv_formno.setText(orderDetail.getFormNo());
            tv_totalamount.setText("" + orderDetail.getAmount());


            imgview_duplicateprint.setVisibility(View.VISIBLE);
            tv_customername.setVisibility(View.VISIBLE);
            tv_totalamount.setVisibility(View.VISIBLE);
            tv_formno.setVisibility(View.VISIBLE);

        }


        imgview_duplicateprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ListView) parent).performItemClick(v, position, 0);
            }
        });


        return v;
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            final List<Duplicate_SalesReturn> list = duplicateReturnList;

            int count = list.size();
            final ArrayList<Duplicate_SalesReturn> nlist = new ArrayList<Duplicate_SalesReturn>(count);

            String filterableString ;

            for (int i = 0; i < count; i++) {
                if (list.get(i).getCustomerName().toLowerCase().contains(filterString)) {
                    nlist.add(list.get(i));
                }
            }

            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            duplicateReturnList = (ArrayList<Duplicate_SalesReturn>) results.values;
            if (duplicateReturnList!=null && duplicateReturnList.size()==0){
                duplicateReturnList=searchInvoiceList;
                notifyDataSetChanged();
            }else {
                notifyDataSetChanged();
            }

        }

    }

}

