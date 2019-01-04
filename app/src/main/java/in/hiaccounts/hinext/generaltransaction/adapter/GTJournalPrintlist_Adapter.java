package in.hiaccounts.hinext.generaltransaction.adapter;

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
import in.hiaccounts.hinext.generaltransaction.model.response.GTPrintlistData;

public class GTJournalPrintlist_Adapter extends BaseAdapter {
    private List<GTPrintlistData> printList;
    private LayoutInflater layoutInflater;


    public GTJournalPrintlist_Adapter(Activity activity, List<GTPrintlistData> printList) {
        super();
        this.printList = printList;
        layoutInflater = LayoutInflater.from(activity);

    }
    @Override
    public int getCount() {
        return printList.size();
    }

    @Override
    public GTPrintlistData getItem(int position) {
        return printList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View v = layoutInflater.inflate(R.layout.adapter_generaltransaction_journal_printlist, null);

        TextView tv_formno = v.findViewById(R.id.tv_formno);
        TextView tv_Total_Debit = v.findViewById(R.id.tv_Total_Debit);
        TextView tv_Total_Credit = v.findViewById(R.id.tv_Total_Credit);
        final ImageView imgview_duplicateprint = v.findViewById(R.id.imgview_duplicateprint);
        final ImageView imgviewEmail = v.findViewById(R.id.imgviewEmail);


        imgviewEmail.setVisibility(View.GONE);
        imgview_duplicateprint.setVisibility(View.GONE);
        tv_Total_Debit.setVisibility(View.GONE);
        tv_Total_Credit.setVisibility(View.GONE);
        tv_formno.setVisibility(View.GONE);


        GTPrintlistData orderDetail = getItem(position);

        if (orderDetail != null) {
            if (orderDetail.getTotalDebit() != null)
                tv_Total_Debit.setText(""+orderDetail.getTotalDebit());

            if (orderDetail.getFormNo() != null)
                tv_formno.setText(orderDetail.getFormNo());

            if (orderDetail.getTotalCredit()!=null)
            tv_Total_Credit.setText("" + orderDetail.getTotalCredit());


            imgviewEmail.setVisibility(View.VISIBLE);
            imgview_duplicateprint.setVisibility(View.VISIBLE);
            tv_Total_Debit.setVisibility(View.VISIBLE);
            tv_Total_Credit.setVisibility(View.VISIBLE);
            tv_formno.setVisibility(View.VISIBLE);

        }


        imgview_duplicateprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ListView) parent).performItemClick(v, position, 0);
            }
        });
        imgviewEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ListView) parent).performItemClick(v, position, 0);
            }
        });


        return v;
    }
}
