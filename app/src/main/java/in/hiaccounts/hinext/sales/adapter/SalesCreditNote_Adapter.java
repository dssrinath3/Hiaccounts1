package in.hiaccounts.hinext.sales.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.hiaccounts.R;
import in.hiaccounts.hinext.sales.model.sales_creditnote.CreditNoteData;

/**
 * Created by administrator on 22/2/18.
 */

public class SalesCreditNote_Adapter extends BaseAdapter {
    private List<CreditNoteData> paymentInvoiceList;
    private LayoutInflater layoutInflater;

    public SalesCreditNote_Adapter(Activity activity, List<CreditNoteData> paymentInvoiceList) {
        super();
        this.paymentInvoiceList = paymentInvoiceList;
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
            convertView = layoutInflater.inflate(R.layout.adapter_sales_creditnotelist, parent, false);
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
}
