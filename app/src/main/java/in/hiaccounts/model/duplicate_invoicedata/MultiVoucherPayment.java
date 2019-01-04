
package in.hiaccounts.model.duplicate_invoicedata;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MultiVoucherPayment implements Serializable
{

    @SerializedName("voucherNo")
    @Expose
    private String voucherNo;
    @SerializedName("amt")
    @Expose
    private double amt;
    private final static long serialVersionUID = -2681030423249418633L;

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
