
package in.hiaccounts.hinext.restaurant.model.restaurant_pagedata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserRights implements Serializable{

    @SerializedName("discount")
    @Expose
    private boolean discount;
    @SerializedName("unitPriceEditable")
    @Expose
    private boolean unitPriceEditable;

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
