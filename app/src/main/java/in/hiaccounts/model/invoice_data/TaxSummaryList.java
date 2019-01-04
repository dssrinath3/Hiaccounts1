
package in.hiaccounts.model.invoice_data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TaxSummaryList implements Serializable {

    @SerializedName("taxPercent")
    @Expose
    private double taxPercent;
    @SerializedName("taxId")
    @Expose
    private long taxId;
    @SerializedName("taxAmount")
    @Expose
    private double taxAmount;
    @SerializedName("amt")
    @Expose
    private double amt;
    @SerializedName("taxName")
    @Expose
    private String taxName;

    /**
     * No args constructor for use in serialization
     *
     */
    public TaxSummaryList() {
    }

    /**
     *
     * @param amt
     * @param taxAmount
     * @param taxId
     * @param taxName
     * @param taxPercent
     */
    public TaxSummaryList(double taxPercent, long taxId, double taxAmount, double amt, String taxName) {
        super();
        this.taxPercent = taxPercent;
        this.taxId = taxId;
        this.taxAmount = taxAmount;
        this.amt = amt;
        this.taxName = taxName;
    }

    public double getTaxPercent() {
        return taxPercent;
    }

    public void setTaxPercent(double taxPercent) {
        this.taxPercent = taxPercent;
    }

    public long getTaxId() {
        return taxId;
    }

    public void setTaxId(long taxId) {
        this.taxId = taxId;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public double getAmt() {
        return amt;
    }

    public void setAmt(double amt) {
        this.amt = amt;
    }

    public String getTaxName() {
        return taxName;
    }

    public void setTaxName(String taxName) {
        this.taxName = taxName;
    }

}
