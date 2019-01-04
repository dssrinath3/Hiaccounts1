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
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.hiaccounts.R;
import in.hiaccounts.hinext.purchase.model.purchase_creditnote.CreditNoteData;
import in.hiaccounts.utility.UtilView;

/**
 * Created by administrator on 29/12/17.
 */

public class PurchaseCreditNote_Adapter extends BaseAdapter implements Filterable {
    private List<CreditNoteData> paymentInvoiceList;
    private LayoutInflater layoutInflater;
    private List<CreditNoteData> filterInvoiceList;
    private ItemFilter mFilter = new ItemFilter();

    public PurchaseCreditNote_Adapter(Activity activity, List<CreditNoteData> paymentInvoiceList) {
        super();
        this.paymentInvoiceList = paymentInvoiceList;
        filterInvoiceList=paymentInvoiceList;
        layoutInflater = LayoutInflater.from(activity);
    }


    @Override
    public int getCount() {
        return paymentInvoiceList.size();
    }

    @Override
    public CreditNoteData getItem(int position) {
        return paymentInvoiceList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.adapter_purchase_creditnotelist, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        CreditNoteData paymentInvoice = getItem(position);

        viewHolder.tvFormNo.setVisibility(View.GONE);
        viewHolder.tvSupplierName.setVisibility(View.GONE);
        viewHolder.tvInvoiceDate.setVisibility(View.GONE);
        viewHolder.tvTotalRecieved.setVisibility(View.GONE);
        viewHolder.tvCreditNote.setVisibility(View.GONE);
        viewHolder.tvCreateCreditNote.setVisibility(View.GONE);
        viewHolder.imageviewReturn.setVisibility(View.GONE);

        if (paymentInvoice != null) {

            if (paymentInvoice.getApBalance() > 0) {
                viewHolder.tvCreateCreditNote.setVisibility(View.VISIBLE);
            } else {
                viewHolder.tvCreditNote.setVisibility(View.VISIBLE);
            }

            if (paymentInvoice.getFormNo() != null)
                viewHolder.tvFormNo.setText(paymentInvoice.getFormNo());

            if (paymentInvoice.getCustomerName() != null)
                viewHolder.tvSupplierName.setText(paymentInvoice.getCustomerName());

            if (paymentInvoice.getSiDate() != null)
                viewHolder.tvInvoiceDate.setText(paymentInvoice.getSiDate());

            if (paymentInvoice.getAmount() != null)
                viewHolder.tvTotalRecieved.setText(""+paymentInvoice.getAmount());

            viewHolder.tvCreateCreditNote.setText("Create Credit Note");

            viewHolder.imageviewReturn.setVisibility(View.GONE);
            viewHolder.tvFormNo.setVisibility(View.VISIBLE);
            viewHolder.tvSupplierName.setVisibility(View.VISIBLE);
            viewHolder.tvInvoiceDate.setVisibility(View.VISIBLE);
            viewHolder.tvTotalRecieved.setVisibility(View.VISIBLE);
            viewHolder.tvCreateCreditNote.setVisibility(View.VISIBLE);
            viewHolder.rl1.setVisibility(View.VISIBLE);
        }
        viewHolder.imageviewReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ListView) parent).performItemClick(v, position, 0);
            }
        });
        viewHolder.tvCreateCreditNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ListView) parent).performItemClick(v, position, 0);
            }
        });
        viewHolder.tvCreditNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ListView) parent).performItemClick(v, position, 0);
            }
        });

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tvFormNo)
        TextView tvFormNo;
        @BindView(R.id.tvSupplierName)
        TextView tvSupplierName;
        @BindView(R.id.tvTotalRecieved)
        TextView tvTotalRecieved;
        @BindView(R.id.tvInvoiceDate)
        TextView tvInvoiceDate;
        @BindView(R.id.tvCreditNote)
        TextView tvCreditNote;
        @BindView(R.id.tvCreateCreditNote)
        TextView tvCreateCreditNote;
        @BindView(R.id.imageviewReturn)
        ImageView imageviewReturn;
        @BindView(R.id.rl1)
        RelativeLayout rl1;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
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

            int count =getCount();
            final ArrayList<CreditNoteData> nlist = new ArrayList<CreditNoteData>(count);

            String filterableString ;

            for (int i = 0; i < count; i++) {
                filterableString = getItem(i).getSupplierName();

                if (filterableString.toLowerCase().contains(filterString)) {
                    UtilView.showLogCat("","Filter text "+filterableString+" : "+filterString);
                    nlist.add( getItem(i));
                }
            }

            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            paymentInvoiceList = (ArrayList<CreditNoteData>) results.values;
            if (paymentInvoiceList!=null)
                notifyDataSetChanged();
        }

    }
}
