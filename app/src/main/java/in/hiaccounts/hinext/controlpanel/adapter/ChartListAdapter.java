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
import in.hiaccounts.hinext.controlpanel.model.accountmaintance.AccountData;
import in.hiaccounts.hinext.controlpanel.model.accountmaintance.ChartAccountDatum;
import in.hiaccounts.hinext.controlpanel.model.company_setup.chartsofaccount.BalanceSheetAccnt;
import in.hiaccounts.hinext.controlpanel.model.company_setup.chartsofaccount.ProfitLossAccnt;
import in.hiaccounts.utility.ServiceHandler;

import static in.hiaccounts.R.id.tvActClass;
import static in.hiaccounts.R.id.tvActCode;
import static in.hiaccounts.R.id.tvActGroup;
import static in.hiaccounts.R.id.tvActName;


/**
 * Custom adapter with "View Holder Pattern".
 *
 * @author danielme.com
 */
public class ChartListAdapter extends ArrayAdapter<Object> {
    private LayoutInflater layoutInflater;
    private Activity activity;
    private ServiceHandler serviceHandler;
    private boolean isInternetPresent = false;

    public ChartListAdapter(Context context, List<Object> objects) {
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

            convertView = layoutInflater.inflate(R.layout.companysetup_adapter_profitloss, null);


            holder.setTvActCode((TextView) convertView.findViewById(tvActCode));
            holder.setTvActName((TextView) convertView.findViewById(tvActName));
            holder.setTvActClass((TextView) convertView.findViewById(tvActClass));
            holder.setTvActGroup((TextView) convertView.findViewById(tvActGroup));


            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }


        holder.getTvActClass().setVisibility(View.GONE);
        holder.getTvActCode().setVisibility(View.GONE);
        holder.getTvActGroup().setVisibility(View.GONE);
        holder.getTvActClass().setVisibility(View.GONE);

        final Object obj = getItem(position);
        if (obj instanceof ProfitLossAccnt) {
            final ProfitLossAccnt profitLossAccnt = (ProfitLossAccnt) obj;


            if (profitLossAccnt != null) {
                if (profitLossAccnt.getCaaccountName() != null)
                    holder.getTvActClass().setText(profitLossAccnt.getCaaccountName());
                if (profitLossAccnt.getCaaccountCode() != null)
                    holder.getTvActCode().setText(profitLossAccnt.getCaaccountCode());

                if (profitLossAccnt.getCaagId() != null) {
                    if (profitLossAccnt.getCaagId().getAgName() != null) {
                        holder.getTvActGroup().setText(profitLossAccnt.getCaagId().getAgName());
                    }
                }

                if (profitLossAccnt.getCaagId() != null) {
                    if (profitLossAccnt.getCaagId().getAcId() != null) {
                        if (profitLossAccnt.getCaagId().getAcId().getAcName() != null) {
                            holder.getTvActName().setText(profitLossAccnt.getCaagId().getAcId().getAcName());
                        }
                    }
                }
                holder.getTvActClass().setVisibility(View.VISIBLE);
                holder.getTvActCode().setVisibility(View.VISIBLE);
                holder.getTvActGroup().setVisibility(View.VISIBLE);
                holder.getTvActClass().setVisibility(View.VISIBLE);
            }

        }

        if (obj instanceof BalanceSheetAccnt) {
            final BalanceSheetAccnt balanceSheetAcct = (BalanceSheetAccnt) obj;

            if (balanceSheetAcct != null) {
                if (balanceSheetAcct.getCaaccountName() != null)
                    holder.getTvActClass().setText(balanceSheetAcct.getCaaccountName());

                if (balanceSheetAcct.getCaaccountCode() != null)
                    holder.getTvActCode().setText(balanceSheetAcct.getCaaccountCode());

                if (balanceSheetAcct.getCaagId() != null) {
                    if (balanceSheetAcct.getCaagId().getAgName() != null) {
                        holder.getTvActGroup().setText(balanceSheetAcct.getCaagId().getAgName());
                    }
                }
                if (balanceSheetAcct.getCaagId() != null) {
                    if (balanceSheetAcct.getCaagId().getAcId() != null) {
                        if (balanceSheetAcct.getCaagId().getAcId().getAcName() != null) {
                            holder.getTvActName().setText(balanceSheetAcct.getCaagId().getAcId().getAcName());
                        }
                    }
                }


                holder.getTvActClass().setVisibility(View.VISIBLE);
                holder.getTvActCode().setVisibility(View.VISIBLE);
                holder.getTvActGroup().setVisibility(View.VISIBLE);
                holder.getTvActClass().setVisibility(View.VISIBLE);
            }

        }


        if (obj instanceof AccountData) {
            final AccountData accountData = (AccountData) obj;

            if (accountData != null) {

                if (accountData.getAccGroup() != null)
                    holder.getTvActClass().setText(accountData.getAccGroup());

                if (accountData.getAgName() != null)
                    holder.getTvActName().setText(accountData.getAgName());

                if (accountData.getAccountCode1() != null) {
                    holder.getTvActCode().setText(accountData.getAccountCode1());
                }
                if (accountData.getStatus() != null) {
                    holder.getTvActGroup().setText(accountData.getStatus());
                }
                holder.getTvActClass().setVisibility(View.VISIBLE);
                holder.getTvActCode().setVisibility(View.VISIBLE);
                holder.getTvActGroup().setVisibility(View.VISIBLE);
                holder.getTvActClass().setVisibility(View.VISIBLE);
            }

        }


        if (obj instanceof ChartAccountDatum) {
            final ChartAccountDatum accountData = (ChartAccountDatum) obj;

            if (accountData != null) {
                if (accountData.getStringAccountCode() != null)
                    holder.getTvActCode().setText(accountData.getStringAccountCode());

                if (accountData.getAccount_name() != null)
                    holder.getTvActName().setText(accountData.getAccount_name());

                if (accountData.getAccountGroupName() != null) {
                    holder.getTvActGroup().setText(accountData.getAccountGroupName());
                }
                if (accountData.getAparcode() != null) {
                    holder.getTvActClass().setText(accountData.getAparcode());
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
