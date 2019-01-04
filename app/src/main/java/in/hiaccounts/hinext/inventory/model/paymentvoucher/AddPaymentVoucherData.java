package in.hiaccounts.hinext.inventory.model.paymentvoucher;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by shrinath on 12/9/2017.
 */

public class AddPaymentVoucherData implements Serializable {
    @SerializedName("pvId")
    @Expose
    private Long pvId;
    @SerializedName("paymentmethodId")
    @Expose
    private Long paymentmethodId;
    @SerializedName("pvno")
    @Expose
    private String pvno;
    @SerializedName("pvdate")
    @Expose
    private String pvdate;
    @SerializedName("reason")
    @Expose
    private String reason;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("taxamount")
    @Expose
    private String taxamount;
    @SerializedName("totalamount")
    @Expose
    private String totalamount;
    @SerializedName("flag")
    @Expose
    private String flag;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("fromDate")
    @Expose
    private String fromDate;
    @SerializedName("toDate")
    @Expose
    private String toDate;

    public Long getPvId() {
        return pvId;
    }

    public Long getPaymentmethodId() {
        return paymentmethodId;
    }

    public void setPaymentmethodId(Long paymentmethodId) {
        this.paymentmethodId = paymentmethodId;
    }

    public void setPvId(Long pvId) {
        this.pvId = pvId;
    }

    public String getPvno() {
        return pvno;
    }

    public void setPvno(String pvno) {
        this.pvno = pvno;
    }

    public String getPvdate() {
        return pvdate;
    }

    public void setPvdate(String pvdate) {
        this.pvdate = pvdate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTaxamount() {
        return taxamount;
    }

    public void setTaxamount(String taxamount) {
        this.taxamount = taxamount;
    }

    public String getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(String totalamount) {
        this.totalamount = totalamount;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
}
