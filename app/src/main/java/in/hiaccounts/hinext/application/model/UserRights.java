package in.hiaccounts.hinext.application.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by administrator on 12/2/18.
 */

public class UserRights implements Serializable {
    @SerializedName("discount")
    @Expose
    public Boolean discount;
    @SerializedName("unitPriceEditable")
    @Expose
    public Boolean unitPriceEditable;

    public Boolean getDiscount() {
        return discount;
    }

    public void setDiscount(Boolean discount) {
        this.discount = discount;
    }

    public Boolean getUnitPriceEditable() {
        return unitPriceEditable;
    }

    public void setUnitPriceEditable(Boolean unitPriceEditable) {
        this.unitPriceEditable = unitPriceEditable;
    }
}
