package in.hiaccounts.hinext.restaurant.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import java.util.List;

import in.hiaccounts.R;
import in.hiaccounts.hinext.checkout.model.PaymentType;

/**
 * Created by Prateek on 6/15/2017.
 */

public class PaymentType_Adapter extends RecyclerView.Adapter<PaymentType_Adapter.MyViewHolder> {

    private List<PaymentType> paymentTypeList;
    Activity mActivity;
    PaymentType paymentType;

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public interface OnItemClickListener {
        void onItemClick(PaymentType paymentType);

    }

    private final OnItemClickListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
      //  public TextView tvPaymentTypeName;
        public CheckBox checkBoxPaymentType;

        public MyViewHolder(View view) {
            super(view);
     //       tvPaymentTypeName = (TextView) view.findViewById(R.id.tvPaymentTypeName);
            checkBoxPaymentType = view.findViewById(R.id.checkBoxPaymentType);

        }
    }

    public PaymentType_Adapter(Activity mActivity, List<PaymentType> paymentTypeList, OnItemClickListener listener) {
        this.paymentTypeList = paymentTypeList;
        this.mActivity = mActivity;
        this.listener=listener;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_paymnettype, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final PaymentType paymentType = paymentTypeList.get(position);

        if (!paymentType.getPaymentmethodName().equals("Cash")){
            setPaymentType(paymentType);
            //  holder.tvPaymentTypeName.setText(paymentType.getPaymentmethodName());
            holder.checkBoxPaymentType.setText(paymentType.getPaymentmethodName());
            holder.checkBoxPaymentType.setChecked(paymentType.isChecked());
            holder.checkBoxPaymentType.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    paymentType.setChecked(holder.checkBoxPaymentType.isChecked());
                    listener.onItemClick(paymentType);
                }
            });
        }else {
            holder.checkBoxPaymentType.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
        return paymentTypeList.size();
    }

}

