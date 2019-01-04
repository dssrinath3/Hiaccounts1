package in.hiaccounts.hinext.company_setup.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import in.hiaccounts.R;
import in.hiaccounts.hinext.company_setup.model.BalanceAccountDatum;

/**
 * Created by Prateek on 2/10/2017.
 */

public class AccountList_Adapter extends BaseAdapter {

    Activity activity;
    List<BalanceAccountDatum> accountList;
    LayoutInflater layoutInflater;


    public AccountList_Adapter(Activity activity, List<BalanceAccountDatum> accountList) {

        super();
        this.activity = activity;
        this.accountList = accountList;

        layoutInflater = LayoutInflater.from(activity);
    }


    @Override
    public int getCount() {
        return accountList.size();
    }

    @Override
    public BalanceAccountDatum getItem(int position) {
        return accountList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        // holder pattern
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();

            convertView = layoutInflater.inflate(R.layout.companysetup_adapter_profitloss, null);


            holder.setTvActCode((TextView) convertView.findViewById(R.id.tvActCode));
            holder.setTvActName((TextView) convertView.findViewById(R.id.tvActName));
            holder.setTvActClass((TextView) convertView.findViewById(R.id.tvActClass));
            holder.setTvActGroup((TextView) convertView.findViewById(R.id.tvActGroup));


            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }


        holder.getTvActClass().setVisibility(View.GONE);
        holder.getTvActCode().setVisibility(View.GONE);
        holder.getTvActGroup().setVisibility(View.GONE);
        holder.getTvActClass().setVisibility(View.GONE);

        final Object obj = getItem(position);
        if (obj instanceof BalanceAccountDatum) {
            final BalanceAccountDatum account = (BalanceAccountDatum) obj;


            if (account != null) {
                if (account.getCaaccountName() != null)
                    holder.getTvActClass().setText(account.getCaaccountName());
                if (account.getCastringAccountCode() != null)
                    holder.getTvActCode().setText(account.getCastringAccountCode());

                if (account.getCaagId() != null) {
                    if (account.getCaagId().getAgName() != null) {
                        holder.getTvActGroup().setText(account.getCaagId().getAgName());
                    }
                }

                if (account.getCaagId() != null) {
                    if (account.getCaagId().getAcId() != null) {
                        if (account.getCaagId().getAcId().getAcName() != null) {
                            holder.getTvActName().setText(account.getCaagId().getAcId().getAcName());
                        }
                    }
                }
                holder.getTvActClass().setVisibility(View.VISIBLE);
                holder.getTvActCode().setVisibility(View.VISIBLE);
                holder.getTvActGroup().setVisibility(View.VISIBLE);
                holder.getTvActClass().setVisibility(View.VISIBLE);
            }

        }
        return convertView;
    }


    public class Holder {

        TextView tvActCode;
        TextView tvActName;
        TextView tvActClass;
        TextView tvActGroup;

        public TextView getTvActCode() {
            return tvActCode;
        }

        public void setTvActCode(TextView tvActCode) {
            this.tvActCode = tvActCode;
        }

        public TextView getTvActName() {
            return tvActName;
        }

        public void setTvActName(TextView tvActName) {
            this.tvActName = tvActName;
        }

        public TextView getTvActClass() {
            return tvActClass;
        }

        public void setTvActClass(TextView tvActClass) {
            this.tvActClass = tvActClass;
        }

        public TextView getTvActGroup() {
            return tvActGroup;
        }

        public void setTvActGroup(TextView tvActGroup) {
            this.tvActGroup = tvActGroup;
        }
    }

}

