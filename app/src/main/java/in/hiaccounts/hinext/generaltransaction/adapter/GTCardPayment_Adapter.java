package in.hiaccounts.hinext.generaltransaction.adapter;


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
import in.hiaccounts.hinext.generaltransaction.activity.Activity_RecieptPayment;
import in.hiaccounts.hinext.generaltransaction.model.checkout.CardPaymentList;

/**
 * Custom adapter with "View Holder Pattern".
 *
 * @author Prateek
 */
public class GTCardPayment_Adapter extends BaseAdapter {

    private Activity activity;
    private List<CardPaymentList> cardPaymentList;
    private LayoutInflater layoutInflater;
    private CardPaymentList cardPayment;
    private int pos;

    public GTCardPayment_Adapter(Activity activity, List<CardPaymentList> cardPaymentList) {
        super();
        this.activity = activity;
        this.cardPaymentList = cardPaymentList;
        layoutInflater = LayoutInflater.from(activity);
    }

    @Override
    public int getCount() {
        return cardPaymentList.size();
    }

    @Override
    public CardPaymentList getItem(int position) {
        return cardPaymentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(activity).inflate(R.layout.adapter_cardpayment, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        pos = position;

        viewHolder.imageviewAddcard.setVisibility(View.GONE);
        viewHolder.imageviewRemovecard.setVisibility(View.GONE);
        viewHolder.edCardamount.setVisibility(View.GONE);
        viewHolder.edTransacionno.setVisibility(View.GONE);


        cardPayment = cardPaymentList.get(position);
        if (cardPayment != null) {

            if (viewHolder.textWatcher_cardAmount != null) {
                viewHolder.edCardamount.removeTextChangedListener(viewHolder.textWatcher_cardAmount);
            }
            if (viewHolder.textWatcher_cardTransactoionNo != null) {
                viewHolder.edTransacionno.removeTextChangedListener(viewHolder.textWatcher_cardTransactoionNo);
            }
            viewHolder.imageviewAddcard.setVisibility(View.VISIBLE);
            viewHolder.imageviewRemovecard.setVisibility(View.VISIBLE);

            viewHolder.edCardamount.setVisibility(View.VISIBLE);
            viewHolder.edTransacionno.setVisibility(View.VISIBLE);


            viewHolder.edCardamount.setText("" + cardPayment.getCardAmount());

            if (cardPayment.getTransactionNo() != null)
                viewHolder.edTransacionno.setText(cardPayment.getTransactionNo());
        }
        final ViewHolder finalViewHolder = viewHolder;


        viewHolder.textWatcher_cardTransactoionNo = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                cardPayment.setTransactionNo(s.toString().toLowerCase());
            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        };

        viewHolder.textWatcher_cardAmount = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.toString().equals("")) {
                    finalViewHolder.edCardamount.setHint("0.00");
                    finalViewHolder.edCardamount.setError(null);
                    cardPayment.setCardAmount(0.00);
                    ((Activity_RecieptPayment) activity).setTenderedAmount();
                } else {
                    try {
                        finalViewHolder.edCardamount.setError(null);
                        double amount = Double.parseDouble(s.toString().trim());
                        BigDecimal big = new BigDecimal(amount);
                        cardPayment.setCardAmount(Double.parseDouble(String.valueOf(big.setScale(2, RoundingMode.HALF_UP))));
                        cardPayment.setTransactionNo(finalViewHolder.edTransacionno.getText().toString().toLowerCase());
                        ((Activity_RecieptPayment) activity).setTenderedAmount();

                    } catch (NumberFormatException e) {
                        finalViewHolder.edCardamount.setError(activity.getResources().getString(R.string.error_numberformate));

                    }
                }


            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        };

        viewHolder.imageviewAddcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ListView) parent).performItemClick(v, position, 0);
            }
        });
        viewHolder.imageviewRemovecard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ListView) parent).performItemClick(v, position, 0);
            }
        });
        viewHolder.edCardamount.addTextChangedListener(viewHolder.textWatcher_cardAmount);
        viewHolder.edTransacionno.addTextChangedListener(viewHolder.textWatcher_cardTransactoionNo);


        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.imageview_addcard)
        ImageView imageviewAddcard;
        @BindView(R.id.imageview_removecard)
        ImageView imageviewRemovecard;
        @BindView(R.id.ed_cardamount)
        EditText edCardamount;
        @BindView(R.id.ed_transacionno)
        EditText edTransacionno;

        TextWatcher textWatcher_cardAmount;
        TextWatcher textWatcher_cardTransactoionNo;


        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}