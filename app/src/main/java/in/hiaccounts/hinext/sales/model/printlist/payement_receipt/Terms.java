
package in.hiaccounts.hinext.sales.model.printlist.payement_receipt;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Terms implements Serializable {

    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("duedays")
    @Expose
    public long duedays;
    @SerializedName("companyId")
    @Expose
    public Object companyId;
    @SerializedName("termsAndConditionName")
    @Expose
    public String termsAndConditionName;
    @SerializedName("termsAndConditionDesc")
    @Expose
    public String termsAndConditionDesc;
    @SerializedName("termsAndConditionId")
    @Expose
    public long termsAndConditionId;
    @SerializedName("discountId")
    @Expose
    public Object discountId;
    @SerializedName("earlyPaymentId")
    @Expose
    public Object earlyPaymentId;
    @SerializedName("latePaymentCharges")
    @Expose
    public Object latePaymentCharges;

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

    public Object getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Object companyId) {
        this.companyId = companyId;
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

    public long getTermsAndConditionId() {
        return termsAndConditionId;
    }

    public void setTermsAndConditionId(long termsAndConditionId) {
        this.termsAndConditionId = termsAndConditionId;
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
}
