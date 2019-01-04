package in.hiaccounts.hinext.restaurant.model.checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TaxWiseSummaryList implements Serializable{
    @SerializedName("taxName")
    @Expose
    public Object taxName;
    @SerializedName("taxId")
    @Expose
    public Long taxId;
    @SerializedName("taxAmount")
    @Expose
    public Double taxAmount;
    @SerializedName("taxPercent")
    @Expose
    public Double taxPercent;
    @SerializedName("amt")
    @Expose
    public Double amt;
    @SerializedName("taxPercentageSplit")
    @Expose
    public Double taxPercentageSplit;
    @SerializedName("cessAmt")
    @Expose
    public Double cessAmt;
    @SerializedName("taxableAmt")
    @Expose
    public Double taxableAmt;
    @SerializedName("cessPercent")
    @Expose
    public Double cessPercent;

    public Object getTaxName() {
        return taxName;
    }

    public void setTaxName(Object taxName) {
        this.taxName = taxName;
    }

    public Long getTaxId() {
        return taxId;
    }

    public void setTaxId(Long taxId) {
        this.taxId = taxId;
    }

    public Double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(Double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Double getTaxPercent() {
        return taxPercent;
    }

    public void setTaxPercent(Double taxPercent) {
        this.taxPercent = taxPercent;
    }

    public Double getAmt() {
        return amt;
    }

    public void setAmt(Double amt) {
        this.amt = amt;
    }

    public Double getTaxPercentageSplit() {
        return taxPercentageSplit;
    }

    public void setTaxPercentageSplit(Double taxPercentageSplit) {
        this.taxPercentageSplit = taxPercentageSplit;
    }

    public Double getCessAmt() {
        return cessAmt;
    }

    public void setCessAmt(Double cessAmt) {
        this.cessAmt = cessAmt;
    }

    public Double getTaxableAmt() {
        return taxableAmt;
    }

    public void setTaxableAmt(Double taxableAmt) {
        this.taxableAmt = taxableAmt;
    }

    public Double getCessPercent() {
        return cessPercent;
    }

    public void setCessPercent(Double cessPercent) {
        this.cessPercent = cessPercent;
    }
}
