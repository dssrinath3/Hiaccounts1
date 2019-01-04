package in.hiaccounts.hinext.inventory.model.purchasepricing;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Prateek on 8/1/2017.
 */

public class InventoryPurchasePricing {
    @SerializedName("purchasePriceId")
    @Expose
    private String purchasePriceId;
    @SerializedName("itemName")
    @Expose
    private String itemName;
    @SerializedName("supplierId")
    @Expose
    private long supplierId;
    @SerializedName("itemId")
    @Expose
    private long itemId;
    @SerializedName("supplierName")
    @Expose
    private String supplierName;
    @SerializedName("supplierID")
    @Expose
    private long supplierID;
    @SerializedName("itemID")
    @Expose
    private long itemID;
    @SerializedName("price")
    @Expose
    private double price;
    @SerializedName("supplierCode")
    @Expose
    private Object supplierCode;
    @SerializedName("supplierDTOList")
    @Expose
    private Object supplierDTOList;
    @SerializedName("itemDTOList")
    @Expose
    private Object itemDTOList;

    public String getPurchasePriceId() {
        return purchasePriceId;
    }

    public void setPurchasePriceId(String purchasePriceId) {
        this.purchasePriceId = purchasePriceId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(long supplierId) {
        this.supplierId = supplierId;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public long getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(long supplierID) {
        this.supplierID = supplierID;
    }

    public long getItemID() {
        return itemID;
    }

    public void setItemID(long itemID) {
        this.itemID = itemID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Object getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(Object supplierCode) {
        this.supplierCode = supplierCode;
    }

    public Object getSupplierDTOList() {
        return supplierDTOList;
    }

    public void setSupplierDTOList(Object supplierDTOList) {
        this.supplierDTOList = supplierDTOList;
    }

    public Object getItemDTOList() {
        return itemDTOList;
    }

    public void setItemDTOList(Object itemDTOList) {
        this.itemDTOList = itemDTOList;
    }
}
