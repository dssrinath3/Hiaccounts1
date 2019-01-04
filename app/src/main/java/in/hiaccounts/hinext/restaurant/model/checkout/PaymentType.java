package in.hiaccounts.hinext.restaurant.model.checkout;

import java.util.List;

/**
 * Created by Admin on 11/22/2017.
 */

public class PaymentType {


    List<PaymentOption> paymentOptionList;

    public List<PaymentOption> getPaymentOptionList() {
        return paymentOptionList;
    }

    public void setPaymentOptionList(List<PaymentOption> paymentOptionList) {
        this.paymentOptionList = paymentOptionList;
    }
}
