
package in.hiaccounts.model.purchase.hinextpurchase;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Prateek on 05/08/2017.
 */

public class TaxList implements Serializable
{

    @SerializedName("taxid")
    @Expose
    private Long taxid;
    @SerializedName("taxString")
    @Expose
    private String taxString;

    /**
     * No args constructor for use in serialization
     * 
     */
    public TaxList() {
    }

    /**
     * 
     * @param taxString
     * @param taxid
     */
    public TaxList(Long taxid, String taxString) {
        this.taxid = taxid;
        this.taxString = taxString;
    }

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

    @Override
    public String toString() {
        return taxString;
    }
}
