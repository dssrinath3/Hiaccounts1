
package in.hiaccounts.hinext.purchase.model.purchase_pagedata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TermsAndConditionList implements Serializable{

    @SerializedName("termsAndConditionId")
    @Expose
    private long termsAndConditionId;
    @SerializedName("termsAndConditionName")
    @Expose
    private String termsAndConditionName;
    @SerializedName("termsAndConditionDesc")
    @Expose
    private String termsAndConditionDesc;
    @SerializedName("discountId")
    @Expose
    private long discountId;
    @SerializedName("earlyPaymentId")
    @Expose
    private long earlyPaymentId;
    @SerializedName("latePaymentCharges")
    @Expose
    private String latePaymentCharges;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("duedays")
    @Expose
    private int duedays;

    /**
     * No args constructor for use in serialization
     * 
     */
    public TermsAndConditionList() {
    }

    /**
     * 
     * @param duedays
     * @param latePaymentCharges
     * @param termsAndConditionId
     * @param termsAndConditionName
     * @param earlyPaymentId
     * @param status
     * @param termsAndConditionDesc
     * @param discountId
     */
    public TermsAndConditionList(long termsAndConditionId, String termsAndConditionName, String termsAndConditionDesc, long discountId, long earlyPaymentId, String latePaymentCharges, String status, int duedays) {
        this.termsAndConditionId = termsAndConditionId;
        this.termsAndConditionName = termsAndConditionName;
        this.termsAndConditionDesc = termsAndConditionDesc;
        this.discountId = discountId;
        this.earlyPaymentId = earlyPaymentId;
        this.latePaymentCharges = latePaymentCharges;
        this.status = status;
        this.duedays = duedays;
    }

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

    public long getDiscountId() {
        return discountId;
    }

    public void setDiscountId(long discountId) {
        this.discountId = discountId;
    }

    public long getEarlyPaymentId() {
        return earlyPaymentId;
    }

    public void setEarlyPaymentId(long earlyPaymentId) {
        this.earlyPaymentId = earlyPaymentId;
    }

    public String getLatePaymentCharges() {
        return latePaymentCharges;
    }

    public void setLatePaymentCharges(String latePaymentCharges) {
        this.latePaymentCharges = latePaymentCharges;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getDuedays() {
        return duedays;
    }

    public void setDuedays(int duedays) {
        this.duedays = duedays;
    }
}
