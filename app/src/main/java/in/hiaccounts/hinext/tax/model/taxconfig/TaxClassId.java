
package in.hiaccounts.hinext.tax.model.taxconfig;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TaxClassId implements Serializable {

    @SerializedName("taxClassId")
    @Expose
    public Long taxClassId;
    @SerializedName("taxClassName")
    @Expose
    public String taxClassName;
    @SerializedName("flag")
    @Expose
    public boolean flag;
    @SerializedName("locationId")
    @Expose
    public Object locationId;
    @SerializedName("useraccount_id")
    @Expose
    public Object useraccountId;

    public Long getTaxClassId() {
        return taxClassId;
    }

    public void setTaxClassId(Long taxClassId) {
        this.taxClassId = taxClassId;
    }

    public String getTaxClassName() {
        return taxClassName;
    }

    public void setTaxClassName(String taxClassName) {
        this.taxClassName = taxClassName;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Object getLocationId() {
        return locationId;
    }

    public void setLocationId(Object locationId) {
        this.locationId = locationId;
    }

    public Object getUseraccountId() {
        return useraccountId;
    }

    public void setUseraccountId(Object useraccountId) {
        this.useraccountId = useraccountId;
    }

    @Override
    public String toString() {
        return taxClassName;
    }
}
