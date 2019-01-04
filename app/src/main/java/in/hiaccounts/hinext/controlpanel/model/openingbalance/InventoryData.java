
package in.hiaccounts.hinext.controlpanel.model.openingbalance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class InventoryData implements Serializable{

    @SerializedName("id")
    @Expose
    public Object id;
    @SerializedName("item")
    @Expose
    public Object item;
    @SerializedName("itemCode")
    @Expose
    public String itemCode;
    @SerializedName("itemName")
    @Expose
    public String itemName;
    @SerializedName("warehouse")
    @Expose
    public Object warehouse;
    @SerializedName("initialQty")
    @Expose
    public double initialQty;
    @SerializedName("openingBalance")
    @Expose
    public double openingBalance;
    @SerializedName("calculatedPrice")
    @Expose
    public double calculatedPrice;
    @SerializedName("companyId")
    @Expose
    public Object companyId;
    @SerializedName("locationId")
    @Expose
    public LocationId locationId;
    @SerializedName("useraccount_id")
    @Expose
    public Object useraccountId;
    @SerializedName("accountGroup")
    @Expose
    public Object accountGroup;

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Object getItem() {
        return item;
    }

    public void setItem(Object item) {
        this.item = item;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Object getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Object warehouse) {
        this.warehouse = warehouse;
    }

    public double getInitialQty() {
        return initialQty;
    }

    public void setInitialQty(double initialQty) {
        this.initialQty = initialQty;
    }

    public double getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(double openingBalance) {
        this.openingBalance = openingBalance;
    }

    public double getCalculatedPrice() {
        return calculatedPrice;
    }

    public void setCalculatedPrice(double calculatedPrice) {
        this.calculatedPrice = calculatedPrice;
    }

    public Object getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Object companyId) {
        this.companyId = companyId;
    }

    public LocationId getLocationId() {
        return locationId;
    }

    public void setLocationId(LocationId locationId) {
        this.locationId = locationId;
    }

    public Object getUseraccountId() {
        return useraccountId;
    }

    public void setUseraccountId(Object useraccountId) {
        this.useraccountId = useraccountId;
    }

    public Object getAccountGroup() {
        return accountGroup;
    }

    public void setAccountGroup(Object accountGroup) {
        this.accountGroup = accountGroup;
    }
}
