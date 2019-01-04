package in.hiaccounts.hinext.purchase.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.hiaccounts.R;
import in.hiaccounts.hinext.purchase.model.purchase_debitnote.Purchase_DebitNoteList;

/**
 * Created by administrator on 22/2/18.
 */

public class PurchaseDebitNote_Adapter extends BaseAdapter {
    private List<Purchase_DebitNoteList> paymentInvoiceList;
    private LayoutInflater layoutInflater;

    public PurchaseDebitNote_Adapter(Activity activity, List<Purchase_DebitNoteList> paymentInvoiceList) {
        super();
        this.paymentInvoiceList = paymentInvoiceList;
        layoutInflater = LayoutInflater.from(activity);
    }


    @Override
    public int getCount() {
        return paymentInvoiceList.size();
    }

    @Override
    public Purchase_DebitNoteList getItem(int position) {
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
            convertView = layoutInflater.inflate(R.layout.adapter_sales_debitnotelist, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Purchase_DebitNoteList paymentInvoice = getItem(position);

        viewHolder.tvFormNo.setVisibility(View.GONE);
        viewHolder.tvSupplierName.setVisibility(View.GONE);
        viewHolder.tvInvoiceDate.setVisibility(View.GONE);
        viewHolder.tvTotalRecieved.setVisibility(View.GONE);
        viewHolder.tvDebitNote.setVisibility(View.GONE);
        viewHolder.tvCreateDebitNote.setVisibility(View.GONE);


        if (paymentInvoice != null) {

            if (paymentInvoice.getApBalance() > 0) {
                viewHolder.tvCreateDebitNote.setVisibility(View.VISIBLE);
            } else {
                viewHolder.tvDebitNote.setVisibility(View.VISIBLE);
            }

            if (paymentInvoice.getFormNo() != null)
                viewHolder.tvFormNo.setText(paymentInvoice.getFormNo());

            if (paymentInvoice.getCustomerName() != null)
                viewHolder.tvSupplierName.setText(paymentInvoice.getCustomerName());

            if (paymentInvoice.getSiDate() != null)
                viewHolder.tvInvoiceDate.setText(paymentInvoice.getSiDate());

            if (paymentInvoice.getAmount() != null)
                viewHolder.tvTotalRecieved.setText(""+paymentInvoice.getAmount());

            viewHolder.tvCreateDebitNote.setText("Create Debit Note");


            viewHolder.tvFormNo.setVisibility(View.VISIBLE);
            viewHolder.tvSupplierName.setVisibility(View.VISIBLE);
            viewHolder.tvInvoiceDate.setVisibility(View.VISIBLE);
            viewHolder.tvTotalRecieved.setVisibility(View.VISIBLE);
            viewHolder.tvCreateDebitNote.setVisibility(View.VISIBLE);
            viewHolder.rl1.setVisibility(View.VISIBLE);
        }

        viewHolder.tvCreateDebitNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ListView) parent).performItemClick(v, position, 0);
            }
        });
        viewHolder.tvDebitNote.setOnClickListener(new View.OnClickListener() {
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
        @BindView(R.id.tvDebitNote)
        TextView tvDebitNote;
        @BindView(R.id.tvCreateDebitNote)
        TextView tvCreateDebitNote;

        @BindView(R.id.rl1)
        RelativeLayout rl1;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
