package in.hiaccounts.model.retailpos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Admin on 2/13/2017.
 */

public class UserRights implements Serializable {

    @SerializedName("unitPriceEditable")
    @Expose
    private boolean unitPriceEditable;
    @SerializedName("discount")
    @Expose
    private boolean discount;

    public UserRights(boolean unitPriceEditable, boolean discount) {
        this.unitPriceEditable = unitPriceEditable;
        this.discount = discount;
    }

    public boolean isUnitPriceEditable() {
        return unitPriceEditable;
    }

    public void setUnitPriceEditable(boolean unitPriceEditable) {
        this.unitPriceEditable = unitPriceEditable;
    }

    public boolean isDiscount() {
        return discount;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }
}


