package in.hiaccounts.hinext.restaurant.model.restaurant_pagedata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Admin on 12/5/2017.
 */

public class TaxList implements Serializable {

    @SerializedName("taxid")
    @Expose
    private Long taxid;
    @SerializedName("taxString")
    @Expose
    private String taxString;

    @SerializedName("taxPercentage")
    @Expose
    private double taxPercantage;

    public Long getTaxid() {
        return taxid;
    }

    public void setTaxid(Long taxid) {
        this.taxid = taxid;
    }

    public String getTaxString() {
        return taxString;
    }

    public void setTaxString(String taxString) {
        this.taxString = taxString;
    }

    public double getTaxPercantage() {
        return taxPercantage;
    }

    public void setTaxPercantage(double taxPercantage) {
        this.taxPercantage = taxPercantage;
    }
}
