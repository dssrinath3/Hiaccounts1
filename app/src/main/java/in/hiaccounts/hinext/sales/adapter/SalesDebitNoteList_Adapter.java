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
import in.hiaccounts.hinext.sales.model.printlist.debitnote.Printlist_DebitnoteData;

/**
 * Created by administrator on 16/1/18.
 */

public class SalesDebitNoteList_Adapter extends BaseAdapter {
    private List<Printlist_DebitnoteData> invoiceList;
    private LayoutInflater layoutInflater;



    public SalesDebitNoteList_Adapter(Activity activity, List<Printlist_DebitnoteData> invoiceList) {
        super();
        this.invoiceList = invoiceList;
        layoutInflater = LayoutInflater.from(activity);

    }

    @Override
    public int getCount() {
        return invoiceList.size();
    }

    @Override
    public Printlist_DebitnoteData getItem(int position) {
        return invoiceList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View v = layoutInflater.inflate(R.layout.adapter_sales_debitnote_list, null);

        TextView tv_formno = v.findViewById(R.id.tv_formno);
        TextView tv_customername = v.findViewById(R.id.tv_customername);
        TextView tv_totalamount = v.findViewById(R.id.tv_totalamount);

        final ImageView imgview_edit = v.findViewById(R.id.imgviewEdit);
        final ImageView imgview_duplicateprint = v.findViewById(R.id.imgview_duplicateprint);
        final ImageView imgviewCancel = v.findViewById(R.id.imgviewCancel);
        final ImageView imgviewPost = v.findViewById(R.id.imgviewPost);


        tv_formno.setVisibility(View.GONE);
        tv_customername.setVisibility(View.GONE);
        tv_totalamount.setVisibility(View.GONE);
        imgview_duplicateprint.setVisibility(View.GONE);
        imgviewCancel.setVisibility(View.GONE);
        imgview_edit.setVisibility(View.GONE);
        imgviewPost.setVisibility(View.GONE);

        Printlist_DebitnoteData invoice = getItem(position);


        if (invoice != null) {
            if (invoice.getCustomerName() != null)
                tv_customername.setText(invoice.getCustomerName());
            if (invoice.getFormNo() != null)
                tv_formno.setText(invoice.getFormNo());

            tv_totalamount.setText("" + invoice.getAmount());

            tv_formno.setVisibility(View.VISIBLE);
            tv_customername.setVisibility(View.VISIBLE);
            tv_totalamount.setVisibility(View.VISIBLE);
            imgview_duplicateprint.setVisibility(View.VISIBLE);
            imgviewCancel.setVisibility(View.VISIBLE);
            imgview_edit.setVisibility(View.VISIBLE);
            imgviewPost.setVisibility(View.VISIBLE);
        }

        imgview_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ListView) parent).performItemClick(v, position, 0);
            }
        });
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
        imgviewPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ListView) parent).performItemClick(v, position, 0);
            }
        });
        return v;
    }



}
