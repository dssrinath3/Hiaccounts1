package in.hiaccounts.hinext.sales.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.hiaccounts.R;
import in.hiaccounts.hinext.sales.model.return_posinvoices.Retrun_PosInvocies;

/**
 * Created by Prateek on 6/15/2017.
 */

public class SalesReturn_Adapter extends BaseAdapter implements Filterable{
    private List<Retrun_PosInvocies> posInvocies;
    private LayoutInflater layoutInflater;

    private ItemFilter mFilter = new ItemFilter();
    private List<Retrun_PosInvocies> searchInvoiceList;

    public SalesReturn_Adapter(Activity mActivity, List<Retrun_PosInvocies> posInvocies) {
        super();
        this.posInvocies = posInvocies;
        layoutInflater = LayoutInflater.from(mActivity);

        this.searchInvoiceList = new ArrayList<Retrun_PosInvocies>();
        this.searchInvoiceList=posInvocies;
    }

    @Override
    public int getCount() {
        return posInvocies.size();
    }

    @Override
    public Retrun_PosInvocies getItem(int position) {
        return posInvocies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.adapter_salesreturn, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Retrun_PosInvocies posInvocie = getItem(position);
        viewHolder.tvFormNumber.setVisibility(View.GONE);
        viewHolder.tvCustomerName.setVisibility(View.GONE);
        viewHolder.tvTotalAmount.setVisibility(View.GONE);

        if (posInvocie != null) {
            if (posInvocie.getFormNo() != null)
                viewHolder.tvFormNumber.setText(posInvocie.getFormNo());
            if (posInvocie.getCustomerName() != null)
                viewHolder.tvCustomerName.setText(posInvocie.getCustomerName());
            viewHolder.tvTotalAmount.setText("" + posInvocie.getAmount());
            viewHolder.tvFormNumber.setVisibility(View.VISIBLE);
            viewHolder.tvCustomerName.setVisibility(View.VISIBLE);
            viewHolder.tvTotalAmount.setVisibility(View.VISIBLE);
        }
        return convertView;
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

            final List<Retrun_PosInvocies> list = posInvocies;

            int count = list.size();
            final ArrayList<Retrun_PosInvocies> nlist = new ArrayList<Retrun_PosInvocies>(count);

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
            posInvocies = (ArrayList<Retrun_PosInvocies>) results.values;
            if (posInvocies!=null && posInvocies.size()==0){
                posInvocies=searchInvoiceList;
                notifyDataSetChanged();
            }else {
                notifyDataSetChanged();
            }

        }

    }
    static class ViewHolder {
        @BindView(R.id.tvFormNumber)
        TextView tvFormNumber;
        @BindView(R.id.tvCustomerName)
        TextView tvCustomerName;
        @BindView(R.id.tvTotalAmount)
        TextView tvTotalAmount;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

