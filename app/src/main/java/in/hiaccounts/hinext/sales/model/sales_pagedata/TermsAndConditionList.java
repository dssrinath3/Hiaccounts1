
package in.hiaccounts.hinext.sales.model.sales_pagedata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TermsAndConditionList implements Serializable{

    @SerializedName("termsAndConditionId")
    @Expose
    public long termsAndConditionId;
    @SerializedName("termsAndConditionName")
    @Expose
    public String termsAndConditionName;
    @SerializedName("termsAndConditionDesc")
    @Expose
    public String termsAndConditionDesc;
    @SerializedName("discountId")
    @Expose
    public Object discountId;
    @SerializedName("earlyPaymentId")
    @Expose
    public Object earlyPaymentId;
    @SerializedName("latePaymentCharges")
    @Expose
    public Object latePaymentCharges;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("duedays")
    @Expose
    public long duedays;

    public long getTermsAndConditionId() {
        return termsAndConditionId;
    }

    public void setTermsAndConditionId(long termsAndConditionId) {
        this.termsAndConditionId = termsAndConditionId;
    }

    public String getTermsAndConditionName() {
        return termsAndConditionName;
    }

    public void setTermsAndConditionName(String termsAndConditionName) {
        this.termsAndConditionName = termsAndConditionName;
    }

    public String getTermsAndConditionDesc() {
        return termsAndConditionDesc;
    }

    public void setTermsAndConditionDesc(String termsAndConditionDesc) {
        this.termsAndConditionDesc = termsAndConditionDesc;
    }

    public Object getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Object discountId) {
        this.discountId = discountId;
    }

    public Object getEarlyPaymentId() {
        return earlyPaymentId;
    }

    public void setEarlyPaymentId(Object earlyPaymentId) {
        this.earlyPaymentId = earlyPaymentId;
    }

    public Object getLatePaymentCharges() {
        return latePaymentCharges;
    }

    public void setLatePaymentCharges(Object latePaymentCharges) {
        this.latePaymentCharges = latePaymentCharges;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getDuedays() {
        return duedays;
    }

    public void setDuedays(long duedays) {
        this.duedays = duedays;
    }
}
