
package in.hiaccounts.hinext.sales.model.sales_pagedata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserRights {

    @SerializedName("discount")
    @Expose
    public boolean discount;
    @SerializedName("unitPriceEditable")
    @Expose
    public boolean unitPriceEditable;

    public boolean isDiscount() {
        return discount;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }

    public boolean isUnitPriceEditable() {
        return unitPriceEditable;
    }

    public void setUnitPriceEditable(boolean unitPriceEditable) {
        this.unitPriceEditable = unitPriceEditable;
    }
}
