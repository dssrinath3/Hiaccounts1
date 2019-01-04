package in.hiaccounts.hinext.inventory.model.paymentvoucher;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by shrinath on 12/9/2017.
 */

public class PaymentVoucherSelectData implements Serializable {
    @SerializedName("pvId")
    @Expose
    private Long pvId;
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
    private Long amount;
    @SerializedName("taxamount")
    @Expose
    private Long taxamount;
    @SerializedName("totalamount")
    @Expose
    private Long totalamount;
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
    @SerializedName("voucherNo")
    @Expose
    private String voucherNo;
    @SerializedName("paymentmethodId")
    @Expose
    private Long paymentmethodId;

    public Long getPvId() {
        return pvId;
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

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getTaxamount() {
        return taxamount;
    }

    public void setTaxamount(Long taxamount) {
        this.taxamount = taxamount;
    }

    public Long getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(Long totalamount) {
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

    public String getVoucherNo() {
        return voucherNo;
    }

    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo;
    }

    public Long getPaymentmethodId() {
        return paymentmethodId;
    }

    public void setPaymentmethodId(Long paymentmethodId) {
        this.paymentmethodId = paymentmethodId;
    }
}
