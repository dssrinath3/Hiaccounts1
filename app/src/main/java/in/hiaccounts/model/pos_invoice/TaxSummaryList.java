
package in.hiaccounts.model.pos_invoice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TaxSummaryList implements Serializable {

    @SerializedName("taxPercent")
    @Expose
    private double taxPercent;
    @SerializedName("taxName")
    @Expose
    private String taxName;
    @SerializedName("amt")
    @Expose
    private double amt;
    @SerializedName("taxId")
    @Expose
    private int taxId;
    @SerializedName("taxAmount")
    @Expose
    private double taxAmount;

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
    public TaxSummaryList(double taxPercent, String taxName, double amt, int taxId, double taxAmount) {
        super();
        this.taxPercent = taxPercent;
        this.taxName = taxName;
        this.amt = amt;
        this.taxId = taxId;
        this.taxAmount = taxAmount;
    }

    public double getTaxPercent() {
        return taxPercent;
    }

    public void setTaxPercent(double taxPercent) {
        this.taxPercent = taxPercent;
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

    public int getTaxId() {
        return taxId;
    }

    public void setTaxId(int taxId) {
        this.taxId = taxId;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(double taxAmount) {
        this.taxAmount = taxAmount;
    }

}
