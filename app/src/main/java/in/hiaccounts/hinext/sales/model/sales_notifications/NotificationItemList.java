package in.hiaccounts.hinext.sales.model.sales_notifications;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import in.hiaccounts.hinext.restaurant.model.restaurant_pagedata.InclusiveJson;

public class NotificationItemList {
    @SerializedName("amtexclusivetax")
    @Expose
    public String amtexclusivetax;
    @SerializedName("amtinclusivetax")
    @Expose
    public String amtinclusivetax;
    @SerializedName("flag")
    @Expose
    public Boolean flag;
    @SerializedName("gstItemTax")
    @Expose
    public Double gstItemTax;
    @SerializedName("gstTaxPercantage")
    @Expose
    public Double gstTaxPercantage;
    @SerializedName("inclusiveJSON")
    @Expose
    public InclusiveJSON inclusiveJSON;
    @SerializedName("inputTaxId")
    @Expose
    public Long inputTaxId;
    @SerializedName("itemCategoryId")
    @Expose
    public Long itemCategoryId;
    @SerializedName("itemCategoryName")
    @Expose
    public String itemCategoryName;
    @SerializedName("itemCode")
    @Expose
    public String itemCode;
    @SerializedName("itemId")
    @Expose
    public Long itemId;
    @SerializedName("itemName")
    @Expose
    public String itemName;
    @SerializedName("itemTypeId")
    @Expose
    public Long itemTypeId;
    @SerializedName("itemTypeName")
    @Expose
    public String itemTypeName;
    @SerializedName("outputTaxId")
    @Expose
    public Long outputTaxId;
    @SerializedName("qty")
    @Expose
    public Long qty;
    @SerializedName("taxId")
    @Expose
    public Long taxId;
    @SerializedName("taxamt")
    @Expose
    public Double taxamt;
    @SerializedName("taxid")
    @Expose
    public Long taxid;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("unitPrice")
    @Expose
    public String unitPrice;
    @SerializedName("unitPriceIn")
    @Expose
    public String unitPriceIn;
    @SerializedName("serializableStatus")
    @Expose
    public String serializableStatus;
    @SerializedName("hsnCode")
    @Expose
    public String hsnCode;
    @SerializedName("itemTotalAmount")
    @Expose
    public String itemTotalAmount;

    public String getAmtexclusivetax() {
        return amtexclusivetax;
    }

    public void setAmtexclusivetax(String amtexclusivetax) {
        this.amtexclusivetax = amtexclusivetax;
    }

    public String getAmtinclusivetax() {
        return amtinclusivetax;
    }

    public void setAmtinclusivetax(String amtinclusivetax) {
        this.amtinclusivetax = amtinclusivetax;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Double getGstItemTax() {
        return gstItemTax;
    }

    public void setGstItemTax(Double gstItemTax) {
        this.gstItemTax = gstItemTax;
    }

    public Double getGstTaxPercantage() {
        return gstTaxPercantage;
    }

    public void setGstTaxPercantage(Double gstTaxPercantage) {
        this.gstTaxPercantage = gstTaxPercantage;
    }

    public InclusiveJSON getInclusiveJSON() {
        return inclusiveJSON;
    }

    public void setInclusiveJSON(InclusiveJSON inclusiveJSON) {
        this.inclusiveJSON = inclusiveJSON;
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

    public Long getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(Long itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    public String getItemTypeName() {
        return itemTypeName;
    }

    public void setItemTypeName(String itemTypeName) {
        this.itemTypeName = itemTypeName;
    }

    public Long getInputTaxId() {
        return inputTaxId;
    }

    public void setInputTaxId(Long inputTaxId) {
        this.inputTaxId = inputTaxId;
    }

    public Long getOutputTaxId() {
        return outputTaxId;
    }

    public void setOutputTaxId(Long outputTaxId) {
        this.outputTaxId = outputTaxId;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public Long getTaxId() {
        return taxId;
    }

    public void setTaxId(Long taxId) {
        this.taxId = taxId;
    }

    public Double getTaxamt() {
        return taxamt;
    }

    public void setTaxamt(Double taxamt) {
        this.taxamt = taxamt;
    }

    public Long getTaxid() {
        return taxid;
    }

    public void setTaxid(Long taxid) {
        this.taxid = taxid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getUnitPriceIn() {
        return unitPriceIn;
    }

    public void setUnitPriceIn(String unitPriceIn) {
        this.unitPriceIn = unitPriceIn;
    }

    public String getSerializableStatus() {
        return serializableStatus;
    }

    public void setSerializableStatus(String serializableStatus) {
        this.serializableStatus = serializableStatus;
    }

    public String getHsnCode() {
        return hsnCode;
    }

    public void setHsnCode(String hsnCode) {
        this.hsnCode = hsnCode;
    }

    public String getItemTotalAmount() {
        return itemTotalAmount;
    }

    public void setItemTotalAmount(String itemTotalAmount) {
        this.itemTotalAmount = itemTotalAmount;
    }
}
