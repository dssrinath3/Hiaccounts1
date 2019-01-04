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
import in.hiaccounts.hinext.sales.model.advance_payment.Sales_AdvancedPaymentListData;

/**
 * Created by administrator on 13/1/18.
 */

public class Sales_AdvancedPaymentListAdapter extends BaseAdapter {
    private List<Sales_AdvancedPaymentListData> advancedList;
    private LayoutInflater layoutInflater;

    public Sales_AdvancedPaymentListAdapter(Activity activity, List<Sales_AdvancedPaymentListData> advancedList) {
        super();
        this.advancedList = advancedList;
        layoutInflater = LayoutInflater.from(activity);

    }

    @Override
    public int getCount() {
        return advancedList.size();
    }

    @Override
    public Sales_AdvancedPaymentListData getItem(int position) {
        return advancedList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View v = layoutInflater.inflate(R.layout.adapter_sales_duplicatereturn, null);

        TextView tv_formno = v.findViewById(R.id.tv_formno);
        TextView tv_customername = v.findViewById(R.id.tv_customername);
        TextView tv_totalamount = v.findViewById(R.id.tv_totalamount);
        final ImageView imgview_duplicateprint = v.findViewById(R.id.imgview_duplicateprint);




        imgview_duplicateprint.setVisibility(View.GONE);
        tv_customername.setVisibility(View.GONE);
        tv_totalamount.setVisibility(View.GONE);
        tv_formno.setVisibility(View.GONE);


        Sales_AdvancedPaymentListData orderDetail = getItem(position);
        if (orderDetail != null) {
            if (orderDetail.getCustomerName() != null)
                tv_customername.setText(orderDetail.getCustomerName());
            if (orderDetail.getFormNo() != null)
                tv_formno.setText(orderDetail.getFormNo());
            tv_totalamount.setText("" + orderDetail.getAmount());


            imgview_duplicateprint.setVisibility(View.VISIBLE);
            tv_customername.setVisibility(View.VISIBLE);
            tv_totalamount.setVisibility(View.VISIBLE);
            tv_formno.setVisibility(View.VISIBLE);

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
