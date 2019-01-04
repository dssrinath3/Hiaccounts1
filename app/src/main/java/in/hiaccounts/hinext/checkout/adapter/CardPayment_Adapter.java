package in.hiaccounts.hinext.checkout.adapter;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.hiaccounts.R;
import in.hiaccounts.hinext.checkout.activity.Activity_Checkout;
import in.hiaccounts.hinext.checkout.model.CardPaymentList;

/**
 * Custom adapter with "View Holder Pattern".
 *
 * @author Prateek
 */
public class CardPayment_Adapter extends BaseAdapter {

    private Activity activity;
    private List<CardPaymentList> cardPaymentList;
    private LayoutInflater layoutInflater;

    private int pos;

    public CardPayment_Adapter(Activity activity, List<CardPaymentList> cardPaymentList) {
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
    public View getView(int position, View convertView, final ViewGroup parent) {
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
        viewHolder.edCardDate.setVisibility(View.GONE);
        viewHolder.edCardNumber.setVisibility(View.GONE);



        final CardPaymentList cardPayment = cardPaymentList.get(position);

        if (cardPayment != null) {
            AmountTextWatcher oldAmountWatcher = (AmountTextWatcher) viewHolder.edCardamount.getTag();
            if (oldAmountWatcher != null)
                viewHolder.edCardamount.removeTextChangedListener(oldAmountWatcher);
            AmountTextWatcher amountTextWatcher = new AmountTextWatcher(cardPayment, viewHolder);
            viewHolder.edCardamount.setTag(amountTextWatcher);
            viewHolder.edCardamount.addTextChangedListener(amountTextWatcher);


            TransactionNumberTextWatcher oldTransNoAmountWatcher = (TransactionNumberTextWatcher) viewHolder.edTransacionno.getTag();
            if (oldTransNoAmountWatcher != null)
                viewHolder.edTransacionno.removeTextChangedListener(oldTransNoAmountWatcher);
            TransactionNumberTextWatcher transNoAmountWatcher = new TransactionNumberTextWatcher(cardPayment, viewHolder);
            viewHolder.edTransacionno.setTag(transNoAmountWatcher);
            viewHolder.edTransacionno.addTextChangedListener(transNoAmountWatcher);

            CardNumberTextWatcher oldCardNoAmountWatcher = (CardNumberTextWatcher) viewHolder.edCardNumber.getTag();
            if (oldCardNoAmountWatcher != null)
                viewHolder.edCardNumber.removeTextChangedListener(oldCardNoAmountWatcher);
            CardNumberTextWatcher cardNoAmountWatcher = new CardNumberTextWatcher(cardPayment, viewHolder);
            viewHolder.edCardNumber.setTag(cardNoAmountWatcher);
            viewHolder.edCardNumber.addTextChangedListener(cardNoAmountWatcher);



            viewHolder.edCardDate.setText("");
            viewHolder.edTransacionno.setText("");
            viewHolder.edCardNumber.setText("");

            viewHolder.edCardamount.setText("" + cardPayment.getCardAmount());
            if (cardPayment.getTransactionNo()!=null)
            viewHolder.edTransacionno.setText(cardPayment.getTransactionNo());
            if (cardPayment.getCardNo()!=null)
            viewHolder.edCardNumber.setText(cardPayment.getCardNo());


            if (cardPayment.getCardDate()!=null && !cardPayment.getCardDate().equals("")) {
                final TimeZone utc = TimeZone.getTimeZone("UTC");
                final SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                f.setTimeZone(utc);
                try {
                    Date date = f.parse(cardPayment.getCardDate());
                    int year = date.getYear()+1900;
                    int month = date.getMonth();
                    final int day = date.getDate();
                    viewHolder.edCardDate.setText(String.valueOf(new StringBuilder().append(day).append("-").append(month + 1).append("-").append(year)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }else {

            }




            viewHolder.imageviewAddcard.setVisibility(View.VISIBLE);
            viewHolder.imageviewRemovecard.setVisibility(View.VISIBLE);
            viewHolder.edCardamount.setVisibility(View.VISIBLE);
            viewHolder.edTransacionno.setVisibility(View.VISIBLE);
            viewHolder.edCardDate.setVisibility(View.VISIBLE);
            viewHolder.edCardNumber.setVisibility(View.VISIBLE);

        }

        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.edCardDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CardPaymentList cardPayment1=cardPayment;
                getDatePicker(activity, finalViewHolder,cardPayment1);

            }
        });


        viewHolder.imageviewAddcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ListView) parent).performItemClick(v, pos, 0);
            }
        });
        viewHolder.imageviewRemovecard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ListView) parent).performItemClick(v, pos, 0);
            }
        });

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
        @BindView(R.id.edCardNumber)
        EditText edCardNumber;
        @BindView(R.id.edCardDate)
        EditText edCardDate;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    private class AmountTextWatcher implements TextWatcher {
        private CardPaymentList cardPayment;
        ViewHolder holder;

        private AmountTextWatcher(CardPaymentList cardPayment, ViewHolder holder) {
            this.cardPayment = cardPayment;
            this.holder = holder;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            if (charSequence.toString().equals("")) {
                holder.edCardamount.setHint("0.00");
                holder.edCardamount.setError(null);
                cardPayment.setCardAmount(0.00);
                ((Activity_Checkout) activity).setTenderedAmount();
            } else {
                try {
                    holder.edCardamount.setError(null);
                    double amount = Double.parseDouble(charSequence.toString().trim());
                    BigDecimal big = new BigDecimal(amount);
                    cardPayment.setCardAmount(Double.parseDouble(String.valueOf(big.setScale(2, RoundingMode.HALF_UP))));
                    cardPayment.setCardAmt(Double.parseDouble(String.valueOf(big.setScale(2, RoundingMode.HALF_UP))));
                    ((Activity_Checkout) activity).setTenderedAmount();

                } catch (NumberFormatException e) {
                    holder.edCardamount.setError(activity.getResources().getString(R.string.error_numberformate));
                }
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {


        }
    }

    private class TransactionNumberTextWatcher implements TextWatcher {
        private CardPaymentList cardPayment;
        ViewHolder holder;

        private TransactionNumberTextWatcher(CardPaymentList cardPayment, ViewHolder holder) {
            this.cardPayment = cardPayment;
            this.holder = holder;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            if (!s.toString().trim().equalsIgnoreCase("")) {
                cardPayment.setTransactionNo(s.toString());
            } else {

            }
        }
    }

    private class CardNumberTextWatcher implements TextWatcher {
        private CardPaymentList cardPayment;
        ViewHolder holder;

        private CardNumberTextWatcher(CardPaymentList cardPayment, ViewHolder holder) {
            this.cardPayment = cardPayment;
            this.holder = holder;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            if (!s.toString().trim().equalsIgnoreCase("")) {
                cardPayment.setCardNo(s.toString());
            } else {

            }
        }
    }

    public void getDatePicker(Activity mActivity, final ViewHolder finalViewHolder, final CardPaymentList cardPayment) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final TimeZone utc = TimeZone.getTimeZone("UTC");
        final SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        f.setTimeZone(utc);


        DatePickerDialog datePickerDialog = new DatePickerDialog(mActivity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                finalViewHolder.edCardDate.setText(String.valueOf(new StringBuilder().append(dayOfMonth).append("-").append(month + 1).append("-").append(year)));
                calendar.set(year, month, dayOfMonth);
                String selectedDate = f.format(calendar.getTime());
                cardPayment.setCardDate(selectedDate);

            }
        }, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();


    }
}