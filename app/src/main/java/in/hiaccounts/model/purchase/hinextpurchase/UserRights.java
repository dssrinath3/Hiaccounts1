
package in.hiaccounts.model.purchase.hinextpurchase;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Created by Prateek on 05/08/2017.
 */


public class UserRights implements Serializable
{

    @SerializedName("discount")
    @Expose
    private boolean discount;
    @SerializedName("unitPriceEditable")
    @Expose
    private boolean unitPriceEditable;


    /**
     * No args constructor for use in serialization
     * 
     */
    public UserRights() {
    }

    /**
     * 
     * @param unitPriceEditable
     * @param discount
     */
    public UserRights(boolean discount, boolean unitPriceEditable) {
        super();
        this.discount = discount;
        this.unitPriceEditable = unitPriceEditable;
    }

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
