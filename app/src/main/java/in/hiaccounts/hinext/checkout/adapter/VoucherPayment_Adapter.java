package in.hiaccounts.hinext.checkout.adapter;


import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.hiaccounts.R;
import in.hiaccounts.hinext.checkout.activity.Activity_Checkout;
import in.hiaccounts.hinext.checkout.model.MultiVoucherPayment;

/**
 * Custom adapter with "View Holder Pattern".
 *
 * @author Prateek
 */
public class VoucherPayment_Adapter extends BaseAdapter {
    Activity activity;
    private List<MultiVoucherPayment> voucherPaymentList;
    private LayoutInflater layoutInflater;

    public VoucherPayment_Adapter(Activity activity, List<MultiVoucherPayment> voucherPaymentList) {
        super();
        this.activity = activity;
        this.voucherPaymentList = voucherPaymentList;
        layoutInflater = LayoutInflater.from(activity);
    }

    @Override
    public int getCount() {
        return voucherPaymentList.size();
    }

    @Override
    public MultiVoucherPayment getItem(int position) {
        return voucherPaymentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.adapter_voucherpayment, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final ViewHolder finalViewHolder = viewHolder;

        viewHolder.imageview_voucheradd.setVisibility(View.GONE);
        viewHolder.imageview_voucherremove.setVisibility(View.GONE);
        viewHolder.ed_voucheramount.setVisibility(View.GONE);
        viewHolder.ed_vouchernumber.setVisibility(View.GONE);

        if (viewHolder.textWatcher_voucherAmount != null) {
            viewHolder.ed_voucheramount.removeTextChangedListener(viewHolder.textWatcher_voucherAmount);
        }
        if (viewHolder.textWatcher_voucherNo != null) {
            viewHolder.ed_vouchernumber.removeTextChangedListener(viewHolder.textWatcher_voucherNo);
        }

        final MultiVoucherPayment voucherPayment = getItem(position);

        if (voucherPayment != null) {

            viewHolder.ed_voucheramount.setText("" + voucherPayment.getAmt());

            if (voucherPayment.getVoucherNo() != null)
                viewHolder.ed_vouchernumber.setText(voucherPayment.getVoucherNo());

            viewHolder.imageview_voucheradd.setVisibility(View.VISIBLE);
            viewHolder.imageview_voucherremove.setVisibility(View.VISIBLE);
            viewHolder.ed_vouchernumber.setVisibility(View.VISIBLE);
            viewHolder.ed_voucheramount.setVisibility(View.VISIBLE);
        }
        viewHolder.imageview_voucheradd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ListView) parent).performItemClick(v, position, 0);
            }
        });
        viewHolder.imageview_voucherremove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ListView) parent).performItemClick(v, position, 0);
            }
        });

        viewHolder.textWatcher_voucherNo = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                voucherPayment.setVoucherNo(s.toString().toLowerCase());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        viewHolder.textWatcher_voucherAmount = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if (s.toString().equals("")) {

                    finalViewHolder.ed_voucheramount.setHint("0.00");
                    finalViewHolder.ed_voucheramount.setError(null);
                    voucherPayment.setAmt(0.00);
                    ((Activity_Checkout) activity).setTenderedAmount();
                } else {
                    try {
                        finalViewHolder.ed_voucheramount.setError(null);
                        double amount = Double.parseDouble(s.toString().trim());
                        BigDecimal big = new BigDecimal(amount);
                        voucherPayment.setAmt(Double.parseDouble(String.valueOf(big.setScale(2, RoundingMode.HALF_UP))));
                        voucherPayment.setVoucherAmt(Double.parseDouble(String.valueOf(big.setScale(2, RoundingMode.HALF_UP))));
                        voucherPayment.setVoucherNo(finalViewHolder.ed_vouchernumber.getText().toString().toLowerCase());
                        ((Activity_Checkout) activity).setTenderedAmount();

                    } catch (NumberFormatException e) {
                        finalViewHolder.ed_voucheramount.setError(activity.getResources().getString(R.string.error_numberformate));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        viewHolder.ed_voucheramount.addTextChangedListener(viewHolder.textWatcher_voucherAmount);
        viewHolder.ed_vouchernumber.addTextChangedListener(viewHolder.textWatcher_voucherNo);
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.imageview_voucheradd)
        ImageView imageview_voucheradd;
        @BindView(R.id.imageview_voucherremove)
        ImageView imageview_voucherremove;
        @BindView(R.id.ed_vouchernumber)
        EditText ed_vouchernumber;
        @BindView(R.id.ed_voucheramount)
        EditText ed_voucheramount;

        TextWatcher textWatcher_voucherAmount;
        TextWatcher textWatcher_voucherNo;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}