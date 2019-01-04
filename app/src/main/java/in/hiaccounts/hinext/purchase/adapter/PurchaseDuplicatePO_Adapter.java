package in.hiaccounts.hinext.purchase.adapter;

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
import in.hiaccounts.hinext.purchase.model.purchase_payments.PurchaseAdvancePayment;


/**
 * Created by Prateek on 2/10/2017.
 */

public class PurchaseDuplicatePO_Adapter extends BaseAdapter implements Filterable {
    private List<PurchaseAdvancePayment> orderList;
    private LayoutInflater layoutInflater;

    private ItemFilter mFilter = new ItemFilter();
    private List<PurchaseAdvancePayment> searchInvoiceList;

    public PurchaseDuplicatePO_Adapter(Activity activity, ArrayList<PurchaseAdvancePayment> orderList) {
        super();
        this.orderList = orderList;
        layoutInflater = LayoutInflater.from(activity);

        searchInvoiceList=new ArrayList<>();
        searchInvoiceList=orderList;
    }

    @Override
    public int getCount() {
        return orderList.size();
    }

    @Override
    public PurchaseAdvancePayment getItem(int position) {
        return orderList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View v = layoutInflater.inflate(R.layout.adapter_purchase_orderitemlist, null);

        TextView tv_formno = v.findViewById(R.id.tv_formno);
        TextView tv_customername = v.findViewById(R.id.tv_customername);
        TextView tv_totalamount = v.findViewById(R.id.tv_totalamount);
        final ImageView imgview_duplicateprint = v.findViewById(R.id.imgview_duplicateprint);
        final ImageView imgviewCancel = v.findViewById(R.id.imgviewCancel);
        final ImageView imgviewEdit = v.findViewById(R.id.imgviewEdit);

        tv_formno.setVisibility(View.GONE);
        tv_customername.setVisibility(View.GONE);
        tv_totalamount.setVisibility(View.GONE);
        imgview_duplicateprint.setVisibility(View.GONE);
        imgviewCancel.setVisibility(View.GONE);
        imgviewEdit.setVisibility(View.GONE);

        PurchaseAdvancePayment invoice = getItem(position);
        if (invoice != null) {
            if (invoice.getSupplierName() != null)
                tv_customername.setText(invoice.getSupplierName());
            if (invoice.getFormNo() != null)
                tv_formno.setText(invoice.getFormNo());
            tv_totalamount.setText("" + invoice.getAmount());

            tv_formno.setVisibility(View.VISIBLE);
            tv_customername.setVisibility(View.VISIBLE);
            tv_totalamount.setVisibility(View.VISIBLE);
            imgview_duplicateprint.setVisibility(View.VISIBLE);
            imgviewCancel.setVisibility(View.VISIBLE);
            imgviewEdit.setVisibility(View.VISIBLE);
        }
        imgview_duplicateprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ListView) parent).performItemClick(v, position, 0);
            }
        });

        imgviewCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ListView) parent).performItemClick(v, position, 0);
            }
        });

        imgviewEdit.setOnClickListener(new View.OnClickListener() {
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

            final List<PurchaseAdvancePayment> list = orderList;

            int count = list.size();
            final ArrayList<PurchaseAdvancePayment> nlist = new ArrayList<PurchaseAdvancePayment>(count);

            String filterableString ;

            for (int i = 0; i < count; i++) {
                if (list.get(i).getSupplierName().toLowerCase().contains(filterString)) {
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
            orderList = (ArrayList<PurchaseAdvancePayment>) results.values;
            if (orderList==null || orderList.size()==0){
                orderList=searchInvoiceList;
                notifyDataSetChanged();
            }else {
                notifyDataSetChanged();
            }

        }

    }
}

