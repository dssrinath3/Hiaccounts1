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
import in.hiaccounts.hinext.purchase.model.purchase_payments.PurchaseAdvancePayment;
import in.hiaccounts.utility.UtilView;


/**
 * Created by Prateek on 2/10/2017.
 */

public class PurchaseAdvancePayment_Adapter extends BaseAdapter implements Filterable {
    private List<PurchaseAdvancePayment> paymentInvoiceList;
    private LayoutInflater layoutInflater;
    private List<PurchaseAdvancePayment> filterInvoiceList;
    private ItemFilter mFilter = new ItemFilter();

    public PurchaseAdvancePayment_Adapter(Activity activity, List<PurchaseAdvancePayment> paymentInvoiceList) {
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
    public PurchaseAdvancePayment getItem(int position) {
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
            convertView = layoutInflater.inflate(R.layout.adapter_purchase_advancepaymentlist, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        PurchaseAdvancePayment paymentInvoice = getItem(position);

        viewHolder.tvFormNo.setVisibility(View.GONE);
        viewHolder.tvSupplierName.setVisibility(View.GONE);
        viewHolder.tvTotalRecieved.setVisibility(View.GONE);
        viewHolder.tvRemainingAmount.setVisibility(View.GONE);
        viewHolder.tvCreateInvoice.setVisibility(View.GONE);
        viewHolder.tvRemainingPayment.setVisibility(View.GONE);
        viewHolder.imageviewReturn.setVisibility(View.GONE);

        if (paymentInvoice != null) {

            if (paymentInvoice.getApBalance() > 0) {
                viewHolder.tvRemainingPayment.setVisibility(View.VISIBLE);
            } else {
                viewHolder.tvCreateInvoice.setVisibility(View.VISIBLE);
            }

            if (paymentInvoice.getFormNo() != null)
                viewHolder.tvFormNo.setText(paymentInvoice.getFormNo());
            if (paymentInvoice.getSupplierName() != null)
                viewHolder.tvSupplierName.setText(paymentInvoice.getSupplierName());
            viewHolder.tvTotalRecieved.setText("" + paymentInvoice.getAmount());
            viewHolder.tvRemainingAmount.setText("" + paymentInvoice.getApBalance());
            viewHolder.imageviewReturn.setVisibility(View.GONE);
            viewHolder.tvFormNo.setVisibility(View.VISIBLE);
            viewHolder.tvSupplierName.setVisibility(View.VISIBLE);
            viewHolder.tvTotalRecieved.setVisibility(View.VISIBLE);
            viewHolder.tvRemainingAmount.setVisibility(View.VISIBLE);
            viewHolder.rl1.setVisibility(View.VISIBLE);
        }
        viewHolder.imageviewReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ListView) parent).performItemClick(v, position, 0);
            }
        });
        viewHolder.tvRemainingPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ListView) parent).performItemClick(v, position, 0);
            }
        });
        viewHolder.tvCreateInvoice.setOnClickListener(new View.OnClickListener() {
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
        @BindView(R.id.tvRemainingAmount)
        TextView tvRemainingAmount;
        @BindView(R.id.tvCreateInvoice)
        TextView tvCreateInvoice;
        @BindView(R.id.tvRemainingPayment)
        TextView tvRemainingPayment;
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
            final ArrayList<PurchaseAdvancePayment> nlist = new ArrayList<PurchaseAdvancePayment>(count);

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
            paymentInvoiceList = (ArrayList<PurchaseAdvancePayment>) results.values;
            if (paymentInvoiceList!=null)
            notifyDataSetChanged();
        }

    }
}

