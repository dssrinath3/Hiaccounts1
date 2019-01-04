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
import in.hiaccounts.hinext.sales.model.printlist.duplicate_invoice.PrintList_PosInvoices;


/**
 * Created by Prateek on 2/10/2017.
 */

public class SalesDuplicateInvoice_Adapter extends BaseAdapter implements Filterable {
    private List<PrintList_PosInvoices> invoiceList;
    private LayoutInflater layoutInflater;


    private ItemFilter mFilter = new ItemFilter();
    private List<PrintList_PosInvoices> searchInvoiceList;

    public SalesDuplicateInvoice_Adapter(Activity activity, ArrayList<PrintList_PosInvoices> invoiceList) {
        super();
        this.invoiceList = invoiceList;
        layoutInflater = LayoutInflater.from(activity);
        searchInvoiceList=new ArrayList<>();
        searchInvoiceList=invoiceList;
    }

    @Override
    public int getCount() {
        return invoiceList.size();
    }

    @Override
    public PrintList_PosInvoices getItem(int position) {
        return invoiceList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View v = layoutInflater.inflate(R.layout.adapter_sales_duplicateinvoice, null);

        TextView tv_formno = v.findViewById(R.id.tv_formno);
        TextView tv_customername = v.findViewById(R.id.tv_customername);
        TextView tv_totalamount = v.findViewById(R.id.tv_totalamount);
        final ImageView imgview_duplicateprint = v.findViewById(R.id.imgview_duplicateprint);
        final ImageView imgviewCancel = v.findViewById(R.id.imgviewCancel);
        final ImageView imgviewEdit = v.findViewById(R.id.imgviewEdit);
        final ImageView imgviewCancelDraft = v.findViewById(R.id.imgviewCancelDraft);

        tv_customername.setVisibility(View.GONE);
        tv_formno.setVisibility(View.GONE);
        tv_totalamount.setVisibility(View.GONE);
        imgview_duplicateprint.setVisibility(View.GONE);
        imgviewCancel.setVisibility(View.GONE);
        imgviewEdit.setVisibility(View.GONE);
        imgviewCancelDraft.setVisibility(View.GONE);


        PrintList_PosInvoices invoice = getItem(position);

        if (invoice != null) {
            if (invoice.getCustomerName() != null)
                tv_customername.setText(invoice.getCustomerName());
            if (invoice.getFormNo() != null)
                tv_formno.setText(invoice.getFormNo());
            tv_totalamount.setText("" + invoice.getAmount());

            tv_customername.setVisibility(View.VISIBLE);
            tv_formno.setVisibility(View.VISIBLE);
            tv_totalamount.setVisibility(View.VISIBLE);
            imgview_duplicateprint.setVisibility(View.VISIBLE);
            imgviewCancel.setVisibility(View.VISIBLE);
            imgviewEdit.setVisibility(View.VISIBLE);
            imgviewCancelDraft.setVisibility(View.VISIBLE);
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
        imgviewCancelDraft.setOnClickListener(new View.OnClickListener() {
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

            final List<PrintList_PosInvoices> list = invoiceList;

            int count = list.size();
            final ArrayList<PrintList_PosInvoices> nlist = new ArrayList<PrintList_PosInvoices>(count);

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
            invoiceList = (ArrayList<PrintList_PosInvoices>) results.values;
            if (invoiceList!=null && invoiceList.size()==0){
                invoiceList=searchInvoiceList;
                notifyDataSetChanged();
            }else {
                notifyDataSetChanged();
            }

        }

    }
}

