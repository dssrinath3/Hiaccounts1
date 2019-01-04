package in.hiaccounts.hinext.inventory.model.inventory_pos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import in.hiaccounts.hinext.inventory.model.inventorylocationtransfer.LocationId;
import in.hiaccounts.hinext.item.model.SelectItemIDData;


/**
 * Created by administrator on 27/1/18.
 */

public class InventorySelectItemData implements Serializable {

    @SerializedName("supplierID")
    @Expose
    public String supplierID;
    boolean isSelect;
    @SerializedName("companyId")
    @Expose
    private String companyId;
    @SerializedName("itemCode")
    @Expose
    private String itemCode;
    @SerializedName("stock")
    @Expose
    private Long stock;
    @SerializedName("locId")
    @Expose
    private Long locId;
    @SerializedName("itemId")
    @Expose
    private SelectItemIDData itemId;

    @SerializedName("locationId")
    @Expose
    public LocationId locationId;

    public LocationId getLocationId() {
        return locationId;
    }

    public void setLocationId(LocationId locationId) {
        this.locationId = locationId;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public Long getLocId() {
        return locId;
    }

    public void setLocId(Long locId) {
        this.locId = locId;
    }

    public SelectItemIDData getItemId() {
        return itemId;
    }

    public void setItemId(SelectItemIDData itemId) {
        this.itemId = itemId;
    }
}
