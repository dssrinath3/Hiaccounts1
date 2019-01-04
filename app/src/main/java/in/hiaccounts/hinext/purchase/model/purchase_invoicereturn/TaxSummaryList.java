
package in.hiaccounts.hinext.purchase.model.purchase_invoicereturn;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TaxSummaryList implements Serializable{

    @SerializedName("taxName")
    @Expose
    private Object taxName;
    @SerializedName("taxId")
    @Expose
    private Integer taxId;
    @SerializedName("taxAmount")
    @Expose
    private Double taxAmount;
    @SerializedName("taxPercent")
    @Expose
    private Double taxPercent;
    @SerializedName("amt")
    @Expose
    private Double amt;

    public Object getTaxName() {
        return taxName;
    }

    public void setTaxName(Object taxName) {
        this.taxName = taxName;
    }

    public Integer getTaxId() {
        return taxId;
    }

    public void setTaxId(Integer taxId) {
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

}
