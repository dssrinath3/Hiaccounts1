
package in.hiaccounts.hinext.tax.model.taxconfig;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TaxGroupId implements Serializable {

    @SerializedName("taxGroupId")
    @Expose
    public Long taxGroupId;
    @SerializedName("taxGroupName")
    @Expose
    public String taxGroupName;

    public Long getTaxGroupId() {
        return taxGroupId;
    }

    public void setTaxGroupId(Long taxGroupId) {
        this.taxGroupId = taxGroupId;
    }

    public String getTaxGroupName() {
        return taxGroupName;
    }

    public void setTaxGroupName(String taxGroupName) {
        this.taxGroupName = taxGroupName;
    }

    @Override
    public String toString() {
        return taxGroupName;
    }
}
