package in.hiaccounts.hinext.controlpanel.model.openingbalance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Admin on 8/26/2017.
 */

public class SaveInventoryData implements Serializable {

    @SerializedName("itemName")
    @Expose
    public String itemName;
    @SerializedName("itemCode")
    @Expose
    public String itemCode;
    @SerializedName("openingBalance")
    @Expose
    public String openingBalance;
    @SerializedName("locationId")
    @Expose
    public LocationId locationId;
    @SerializedName("initialQty")
    @Expose
    public String initialQty;
    @SerializedName("calculatedPrice")
    @Expose
    public double calculatedPrice;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(String openingBalance) {
        this.openingBalance = openingBalance;
    }

    public LocationId getLocationId() {
        return locationId;
    }

    public void setLocationId(LocationId locationId) {
        this.locationId = locationId;
    }

    public String getInitialQty() {
        return initialQty;
    }

    public void setInitialQty(String initialQty) {
        this.initialQty = initialQty;
    }

    public double getCalculatedPrice() {
        return calculatedPrice;
    }

    public void setCalculatedPrice(double calculatedPrice) {
        this.calculatedPrice = calculatedPrice;
    }
}
