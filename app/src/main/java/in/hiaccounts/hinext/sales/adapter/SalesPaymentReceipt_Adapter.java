package in.hiaccounts.hinext.sales.adapter;

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
import in.hiaccounts.hinext.sales.model.printlist.payement_receipt.PaymentRecieptDatumData;


/**
 * Created by Prateek on 2/10/2017.
 */

public class SalesPaymentReceipt_Adapter extends BaseAdapter{
    private List<PaymentRecieptDatumData> paymentReceiptList;
    private LayoutInflater layoutInflater;


    public SalesPaymentReceipt_Adapter(Activity activity, List<PaymentRecieptDatumData> paymentReceiptList) {
        super();
        this.paymentReceiptList = paymentReceiptList;
        layoutInflater = LayoutInflater.from(activity);

    }

    @Override
    public int getCount() {
        return paymentReceiptList.size();
    }

    @Override
    public PaymentRecieptDatumData getItem(int position) {
        return paymentReceiptList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View v = layoutInflater.inflate(R.layout.adapter_sales_paymentreciept, null);

        TextView tvCustomerName = v.findViewById(R.id.tvCustomerName);
        TextView tvTotalAmount = v.findViewById(R.id.tvTotalAmount);
        TextView tvAmountPaid = v.findViewById(R.id.tvAmountPaid);
        TextView tvRemainingAmount = v.findViewById(R.id.tvRemainingAmount);
        final ImageView imgviewReceiptPrint = v.findViewById(R.id.imgviewReceiptPrint);

        PaymentRecieptDatumData paymentRecieptDatum = getItem(position);
        tvCustomerName.setVisibility(View.GONE);
        tvTotalAmount.setVisibility(View.GONE);
        tvAmountPaid.setVisibility(View.GONE);
        tvRemainingAmount.setVisibility(View.GONE);
        imgviewReceiptPrint.setVisibility(View.GONE);

        if (paymentRecieptDatum != null) {
            if (paymentRecieptDatum.getCustomerName() != null)
                tvCustomerName.setText(paymentRecieptDatum.getCustomerName());
            if (paymentRecieptDatum.getTotalAmount() != null)
                tvTotalAmount.setText("" + paymentRecieptDatum.getTotalAmount());
            if (paymentRecieptDatum.getAmountPaid() != null)
                tvAmountPaid.setText("" + paymentRecieptDatum.getAmountPaid());
            if (paymentRecieptDatum.getRemaininAmt() != null)
                tvRemainingAmount.setText("" + paymentRecieptDatum.getRemaininAmt());

            tvCustomerName.setVisibility(View.VISIBLE);
            tvTotalAmount.setVisibility(View.VISIBLE);
            tvAmountPaid.setVisibility(View.VISIBLE);
            tvRemainingAmount.setVisibility(View.VISIBLE);
            imgviewReceiptPrint.setVisibility(View.VISIBLE);
        }
        imgviewReceiptPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ListView) parent).performItemClick(v, position, 0);
            }
        });
        return v;
    }




}
