
package in.hiaccounts.hinext.purchase.model.purchase_saveresponses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TaxSummaryList implements Serializable {

    @SerializedName("taxName")
    @Expose
    public String taxName;
    @SerializedName("taxId")
    @Expose
    public Long taxId;
    @SerializedName("taxAmount")
    @Expose
    public double taxAmount;
    @SerializedName("taxPercent")
    @Expose
    public double taxPercent;
    @SerializedName("amt")
    @Expose
    public double amt;
    @SerializedName("taxPercentageSplit")
    @Expose
    public double taxPercentageSplit;

    public String getTaxName() {
        return taxName;
    }

    public void setTaxName(String taxName) {
        this.taxName = taxName;
    }

    public Long getTaxId() {
        return taxId;
    }

    public void setTaxId(Long taxId) {
        this.taxId = taxId;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public double getTaxPercent() {
        return taxPercent;
    }

    public void setTaxPercent(double taxPercent) {
        this.taxPercent = taxPercent;
    }

    public double getAmt() {
        return amt;
    }

    public void setAmt(double amt) {
        this.amt = amt;
    }

    public double getTaxPercentageSplit() {
        return taxPercentageSplit;
    }

    public void setTaxPercentageSplit(double taxPercentageSplit) {
        this.taxPercentageSplit = taxPercentageSplit;
    }
}
