package in.hiaccounts.hinext.controlpanel.adapter;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import in.hiaccounts.R;
import in.hiaccounts.hinext.controlpanel.model.openingbalance.BalanceSheetData;
import in.hiaccounts.utility.ServiceHandler;


/**
 * Custom adapter with "View Holder Pattern".
 *
 * @author danielme.com
 */
public class OpeningBalanceSheetAdapter extends ArrayAdapter<Object> {
    private LayoutInflater layoutInflater;
    private Activity activity;
    private ServiceHandler serviceHandler;
    private boolean isInternetPresent = false;

    public OpeningBalanceSheetAdapter(Context context, List<Object> objects) {
        super(context, 0, objects);
        layoutInflater = LayoutInflater.from(context);
        activity = (Activity) context;
        serviceHandler = new ServiceHandler(activity);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        // holder pattern
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();

            convertView = layoutInflater.inflate(R.layout.oepningbalance_adapter_balancesheet, null);

            holder.setTvAccount((TextView) convertView.findViewById(R.id.tvAmount));
            holder.setTvDebit((TextView) convertView.findViewById(R.id.tvDebit));
            holder.setTvCredit((TextView) convertView.findViewById(R.id.tvCredit));


            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }


        holder.getTvAccount().setVisibility(View.GONE);
        holder.getTvCredit().setVisibility(View.GONE);
        holder.getTvDebit().setVisibility(View.GONE);

        final Object obj = getItem(position);
        if (obj instanceof BalanceSheetData) {
            final BalanceSheetData data = (BalanceSheetData) obj;

            if (data != null) {

                if (data.getAccountid() != null)
                    holder.getTvAccount().setText(data.getAccountid());

                if (data.getDebitAmount() != null)
                    holder.getTvDebit().setText(data.getDebitAmount());

                if (data.getCreditAmount() != null)
                    holder.getTvCredit().setText(data.getCreditAmount());

                holder.getTvAccount().setVisibility(View.VISIBLE);
                holder.getTvCredit().setVisibility(View.VISIBLE);
                holder.getTvDebit().setVisibility(View.VISIBLE);

            }
        }


        return convertView;
    }


    public class Holder {


        TextView tvAccount;
        TextView tvCredit;
        TextView tvDebit;

        public TextView getTvAccount() {
            return tvAccount;
        }

        public void setTvAccount(TextView tvAccount) {
            this.tvAccount = tvAccount;
        }

        public TextView getTvCredit() {
            return tvCredit;
        }

        public void setTvCredit(TextView tvCredit) {
            this.tvCredit = tvCredit;
        }

        public TextView getTvDebit() {
            return tvDebit;
        }

        public void setTvDebit(TextView tvDebit) {
            this.tvDebit = tvDebit;
        }
    }
}