package in.hiaccounts.model.retailpos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Admin on 2/13/2017.
 */

public class TaxList implements Serializable {

    @SerializedName("taxid")
    @Expose
    private int taxid;
    @SerializedName("taxString")
    @Expose
    private String taxString;

    public TaxList(int taxid, String taxString) {
        this.taxid = taxid;
        this.taxString = taxString;
    }

    public int getTaxid() {
        return taxid;
    }

    public void setTaxid(int taxid) {
        this.taxid = taxid;
    }

    public String getTaxString() {
        return taxString;
    }

    public void setTaxString(String taxString) {
        this.taxString = taxString;
    }

    @Override
    public String toString() {
        return taxString;
    }
}


