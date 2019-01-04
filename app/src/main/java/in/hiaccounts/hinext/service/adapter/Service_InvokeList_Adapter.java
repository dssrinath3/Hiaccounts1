package in.hiaccounts.hinext.service.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import in.hiaccounts.R;
import in.hiaccounts.hinext.service.model.service_invoke.InvokeList_Data;


/**
 * Created by administrator on 26/2/18.
 */

public class Service_InvokeList_Adapter extends BaseAdapter {
    private List<InvokeList_Data> invoiceList;
    private LayoutInflater layoutInflater;


    public Service_InvokeList_Adapter(Activity activity, List<InvokeList_Data> invoiceList) {
        super();
        this.invoiceList = invoiceList;
        layoutInflater = LayoutInflater.from(activity);

    }

    @Override
    public int getCount() {
        return invoiceList.size();
    }

    @Override
    public InvokeList_Data getItem(int position) {
        return invoiceList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View v = layoutInflater.inflate(R.layout.adapter_service_invokelist, null);

        TextView tv_formno = v.findViewById(R.id.tvFormno);
        TextView tv_customername = v.findViewById(R.id.tvCustomername);

        final ImageView imgviewPrint = v.findViewById(R.id.imgviewPrint);
        final ImageView imgviewCancel = v.findViewById(R.id.imgviewCancel);
        final ImageView imgviewEdit = v.findViewById(R.id.imgviewEdit);


        tv_customername.setVisibility(View.GONE);
        tv_formno.setVisibility(View.GONE);

        imgviewPrint.setVisibility(View.GONE);
        imgviewCancel.setVisibility(View.GONE);
        imgviewEdit.setVisibility(View.GONE);



        InvokeList_Data invoice = getItem(position);

        if (invoice != null) {
            if (invoice.getSrNo() != null)
                tv_formno.setText(invoice.getSrNo());

            if (invoice.getName() != null)
                tv_customername.setText(invoice.getName());




            tv_customername.setVisibility(View.VISIBLE);
            tv_formno.setVisibility(View.VISIBLE);

            imgviewPrint.setVisibility(View.VISIBLE);
            imgviewCancel.setVisibility(View.VISIBLE);
            imgviewEdit.setVisibility(View.VISIBLE);

        }
        imgviewPrint.setOnClickListener(new View.OnClickListener() {
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

        imgviewEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ListView) parent).performItemClick(v, position, 0);
            }
        });


        return v;
    }
}
