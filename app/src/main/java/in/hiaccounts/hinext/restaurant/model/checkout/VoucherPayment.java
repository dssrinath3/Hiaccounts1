package in.hiaccounts.hinext.restaurant.model.checkout;

/**
 * Created by Admin on 11/22/2017.
 */

public class VoucherPayment {

    Long paymentType;
    String voucherNo;
    double voucherAmt;

    public Long getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Long paymentType) {
        this.paymentType = paymentType;
    }

    public String getVoucherNo() {
        return voucherNo;
    }

    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo;
    }

    public double getVoucherAmt() {
        return voucherAmt;
    }

    public void setVoucherAmt(double voucherAmt) {
        this.voucherAmt = voucherAmt;
    }
}
