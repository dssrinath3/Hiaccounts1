
package in.hiaccounts.model.invoice_data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MultiVoucherPayment implements Serializable {

    @SerializedName("voucherNo")
    @Expose
    private String voucherNo;
    @SerializedName("amt")
    @Expose
    private double amt;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MultiVoucherPayment() {
    }

    /**
     * 
     * @param amt
     * @param voucherNo
     */
    public MultiVoucherPayment(String voucherNo, double amt) {
        super();
        this.voucherNo = voucherNo;
        this.amt = amt;
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

}
