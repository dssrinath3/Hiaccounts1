package in.hiaccounts.hinext.purchase.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.hiaccounts.R;
import in.hiaccounts.hinext.purchase.model.purchase_invoicereturn.PurchaseInvoiceReturnData;

/**
 * Created by Prateek on 6/15/2017.
 */

public class PurchaseReturn_Adapter extends BaseAdapter{
    private List<PurchaseInvoiceReturnData> posInvocies;
    private LayoutInflater layoutInflater;


    public PurchaseReturn_Adapter(Activity mActivity, List<PurchaseInvoiceReturnData> posInvocies) {
        super();
        this.posInvocies = posInvocies;
        layoutInflater = LayoutInflater.from(mActivity);

    }

    @Override
    public int getCount() {
        return posInvocies.size();
    }

    @Override
    public PurchaseInvoiceReturnData getItem(int position) {
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
        final PurchaseInvoiceReturnData posInvocie = getItem(position);
        viewHolder.tvFormNumber.setVisibility(View.GONE);
        viewHolder.tvCustomerName.setVisibility(View.GONE);
        viewHolder.tvTotalAmount.setVisibility(View.GONE);

        if (posInvocie != null) {
            if (posInvocie.getPiNo() != null)
                viewHolder.tvFormNumber.setText(posInvocie.getPiNo());
            if (posInvocie.getSupplierName() != null)
                viewHolder.tvCustomerName.setText(posInvocie.getSupplierName());
            if (posInvocie.getTotalTenderedAmount() != null)
                viewHolder.tvTotalAmount.setText("" + posInvocie.getTotalTenderedAmount());


            viewHolder.tvFormNumber.setVisibility(View.VISIBLE);
            viewHolder.tvCustomerName.setVisibility(View.VISIBLE);
            viewHolder.tvTotalAmount.setVisibility(View.VISIBLE);
        }
        return convertView;
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

