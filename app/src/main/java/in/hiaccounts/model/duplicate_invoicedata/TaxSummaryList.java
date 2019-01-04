
package in.hiaccounts.model.duplicate_invoicedata;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TaxSummaryList implements Serializable
{

    @SerializedName("taxAmount")
    @Expose
    private double taxAmount;
    @SerializedName("taxId")
    @Expose
    private long taxId;
    @SerializedName("taxName")
    @Expose
    private String taxName;
    @SerializedName("amt")
    @Expose
    private double amt;
    @SerializedName("taxPercent")
    @Expose
    private double taxPercent;
    private final static long serialVersionUID = -1697791046380757279L;

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
    public TaxSummaryList(double taxAmount, long taxId, String taxName, double amt, double taxPercent) {
        super();
        this.taxAmount = taxAmount;
        this.taxId = taxId;
        this.taxName = taxName;
        this.amt = amt;
        this.taxPercent = taxPercent;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public long getTaxId() {
        return taxId;
    }

    public void setTaxId(long taxId) {
        this.taxId = taxId;
    }

    public String getTaxName() {
        return taxName;
    }

    public void setTaxName(String taxName) {
        this.taxName = taxName;
    }

    public double getAmt() {
        return amt;
    }

    public void setAmt(double amt) {
        this.amt = amt;
    }

    public double getTaxPercent() {
        return taxPercent;
    }

    public void setTaxPercent(double taxPercent) {
        this.taxPercent = taxPercent;
    }

}
