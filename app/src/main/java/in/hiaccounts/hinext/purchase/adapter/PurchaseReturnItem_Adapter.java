package in.hiaccounts.hinext.purchase.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import in.hiaccounts.R;
import in.hiaccounts.hinext.purchase.model.purchase_payments.PurchaseAdvancePayment;

/**
 * Created by srinath on 4/1/18.
 */

public class PurchaseReturnItem_Adapter extends BaseAdapter {
    private List<PurchaseAdvancePayment> paymentInvoiceList;
    private LayoutInflater layoutInflater;

    public PurchaseReturnItem_Adapter(Activity activity, List<PurchaseAdvancePayment> paymentInvoiceList) {
        super();
        this.paymentInvoiceList = paymentInvoiceList;
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
        View v = layoutInflater.inflate(R.layout.adapter_purchase_returnitemlist, null);



        TextView tv_formno = v.findViewById(R.id.tv_formno);
        TextView tv_customername = v.findViewById(R.id.tv_customername);
        TextView tv_totalamount = v.findViewById(R.id.tv_totalamount);
        final ImageView imgview_duplicateprint = v.findViewById(R.id.imgview_duplicateprint);


        tv_formno.setVisibility(View.GONE);
        tv_customername.setVisibility(View.GONE);
        tv_totalamount.setVisibility(View.GONE);
        imgview_duplicateprint.setVisibility(View.GONE);

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
        }
        imgview_duplicateprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ListView) parent).performItemClick(v, position, 0);
            }
        });




        return v;
    }

}
