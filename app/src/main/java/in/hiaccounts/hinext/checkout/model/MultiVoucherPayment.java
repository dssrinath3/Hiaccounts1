
package in.hiaccounts.hinext.checkout.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MultiVoucherPayment implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("voucherNo")
    @Expose
    private String voucherNo;
    @SerializedName("amt")
    @Expose
    private double amt;
    @SerializedName("voucherAmt")
    @Expose
    private double voucherAmt;

    @SerializedName("paymentType")
    @Expose
    private Long paymentType;

    public Long getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Long paymentType) {
        this.paymentType = paymentType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVoucherNo() {
        return voucherNo;
    }

    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo;
    }

    public double getAmt() {
        return amt;
    }

    public void setAmt(double amt) {
        this.amt = amt;
    }

    public double getVoucherAmt() {
        return voucherAmt;
    }

    public void setVoucherAmt(double voucherAmt) {
        this.voucherAmt = voucherAmt;
    }
}
