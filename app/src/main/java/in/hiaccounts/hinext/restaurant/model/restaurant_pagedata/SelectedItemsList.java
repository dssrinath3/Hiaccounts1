package in.hiaccounts.hinext.restaurant.model.restaurant_pagedata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by administrator on 17/2/18.
 */

public class SelectedItemsList implements Serializable {


    @SerializedName("itemCode")
    @Expose
    public String itemCode;
    @SerializedName("itemId")
    @Expose
    public Long itemId;
    @SerializedName("itemName")
    @Expose
    public String itemName;
    @SerializedName("itemCategoryId")
    @Expose
    public Long itemCategoryId;
    @SerializedName("itemCategoryName")
    @Expose
    public String itemCategoryName;
    @SerializedName("unitPrice")
    @Expose
    public Double unitPrice;
    @SerializedName("gstItemTax")
    @Expose
    public Double gstItemTax;
    @SerializedName("amtinclusivetax")
    @Expose
    public Long amtinclusivetax;
    @SerializedName("qty")
    @Expose
    public Long qty;
    @SerializedName("taxid")
    @Expose
    public Long taxid;
    @SerializedName("amtexclusivetax")
    @Expose
    public Double amtexclusivetax;
    @SerializedName("inclusiveJSON")
    @Expose
    public String inclusiveJSON;

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Long getItemCategoryId() {
        return itemCategoryId;
    }

    public void setItemCategoryId(Long itemCategoryId) {
        this.itemCategoryId = itemCategoryId;
    }

    public String getItemCategoryName() {
        return itemCategoryName;
    }

    public void setItemCategoryName(String itemCategoryName) {
        this.itemCategoryName = itemCategoryName;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getGstItemTax() {
        return gstItemTax;
    }

    public void setGstItemTax(Double gstItemTax) {
        this.gstItemTax = gstItemTax;
    }

    public Long getAmtinclusivetax() {
        return amtinclusivetax;
    }

    public void setAmtinclusivetax(Long amtinclusivetax) {
        this.amtinclusivetax = amtinclusivetax;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public Long getTaxid() {
        return taxid;
    }

    public void setTaxid(Long taxid) {
        this.taxid = taxid;
    }

    public Double getAmtexclusivetax() {
        return amtexclusivetax;
    }

    public void setAmtexclusivetax(Double amtexclusivetax) {
        this.amtexclusivetax = amtexclusivetax;
    }

    public String getInclusiveJSON() {
        return inclusiveJSON;
    }

    public void setInclusiveJSON(String inclusiveJSON) {
        this.inclusiveJSON = inclusiveJSON;
    }
}
