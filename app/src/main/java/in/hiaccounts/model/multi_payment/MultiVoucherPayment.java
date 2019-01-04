
package in.hiaccounts.model.multi_payment;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MultiVoucherPayment implements Serializable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("voucherNo")
    @Expose
    private String voucherNo;
    @SerializedName("amt")
    @Expose
    private double amt;
    private final static long serialVersionUID = -4576227256463299027L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MultiVoucherPayment() {
    }

    /**
     * 
     * @param id
     * @param amt
     * @param voucherNo
     */
    public MultiVoucherPayment(String id, String voucherNo, double amt) {
        super();
        this.id = id;
        this.voucherNo = voucherNo;
        this.amt = amt;
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

}
