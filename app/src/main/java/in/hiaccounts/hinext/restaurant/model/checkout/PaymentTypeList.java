package in.hiaccounts.hinext.restaurant.model.checkout;

/**
 * Created by Admin on 11/22/2017.
 */

public class PaymentTypeList {

    PaymentType paymentType;
    CashPayment cashPayment;
    CardPayment cardPayment;
    VoucherPayment voucherPayment;

    Long paymentOptionid;


    public Long getPaymentOptionid() {
        return paymentOptionid;
    }

    public void setPaymentOptionid(Long paymentOptionid) {
        this.paymentOptionid = paymentOptionid;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public CashPayment getCashPayment() {
        return cashPayment;
    }

    public void setCashPayment(CashPayment cashPayment) {
        this.cashPayment = cashPayment;
    }

    public CardPayment getCardPayment() {
        return cardPayment;
    }

    public void setCardPayment(CardPayment cardPayment) {
        this.cardPayment = cardPayment;
    }

    public VoucherPayment getVoucherPayment() {
        return voucherPayment;
    }

    public void setVoucherPayment(VoucherPayment voucherPayment) {
        this.voucherPayment = voucherPayment;
    }
}
