package in.hiaccounts.hinext.restaurant.model.checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Admin on 11/23/2017.
 */

public class RestraCheckoutItem implements Serializable{
    @SerializedName("itemCode")
    @Expose
    public String itemCode;
    @SerializedName("itemId")
    @Expose
    public Long itemId;
    @SerializedName("itemName")
    @Expose
    public String itemName;
    @SerializedName("unitPrice")
    @Expose
    public double unitPrice;
    @SerializedName("unitPriceIn")
    @Expose
    public double unitPriceIn;
    @SerializedName("gstItemTax")
    @Expose
    public double gstItemTax;
    @SerializedName("taxamt")
    @Expose
    public double taxamt;
    @SerializedName("amtinclusivetax")
    @Expose
    public double amtinclusivetax;
    @SerializedName("qty")
    @Expose
    public long qty;
    @SerializedName("amtexclusivetax")
    @Expose
    public double amtexclusivetax;
    @SerializedName("itemCategoryId")
    @Expose
    private Long itemCategoryId;
    @SerializedName("taxPercentageSplit")
    @Expose
    private Double taxPercentageSplit;
    @SerializedName("itemCategoryName")
    @Expose
    private String itemCategoryName;
    @SerializedName("inclusiveJSON")
    @Expose
    private String inclusiveJSON;
    @SerializedName("taxId")
    @Expose
    private Long taxId;
    @SerializedName("taxid")
    @Expose
    private Long taxid;

    @SerializedName("itemTypeId")
    @Expose
    private Long itemTypeId;
    @SerializedName("mscid")
    @Expose
    private Long mscid;
    @SerializedName("inputTaxId")
    @Expose
    private Long inputTaxId;
    @SerializedName("outputTaxId")
    @Expose
    private Long outputTaxId;
    @SerializedName("itemTypeName")
    @Expose
    private String itemTypeName;
    @SerializedName("itemDesc")
    @Expose
    private String itemDesc;
    @SerializedName("itemDescription")
    @Expose
    private String itemDescription;
    @SerializedName("uom")
    @Expose
    public Long uom;
    @SerializedName("discountAmt")
    @Expose
    public Double discountAmt;
    @SerializedName("discountConfigAmt")
    @Expose
    public Double discountConfigAmt;


    private double gstTaxPercantage;
    private boolean flag;
    private String type;
    private String tableId;
    private String tableName;
    private Boolean kotSelect;
    private String floorId;
    private String waiterName;

    public Double getDiscountAmt() {
        return discountAmt;
    }

    public void setDiscountAmt(Double discountAmt) {
        this.discountAmt = discountAmt;
    }

    public Double getDiscountConfigAmt() {
        return discountConfigAmt;
    }

    public void setDiscountConfigAmt(Double discountConfigAmt) {
        this.discountConfigAmt = discountConfigAmt;
    }

    public String getWaiterName() {
        return waiterName;
    }

    public void setWaiterName(String waiterName) {
        this.waiterName = waiterName;
    }

    public double getUnitPriceIn() {
        return unitPriceIn;
    }

    public void setUnitPriceIn(double unitPriceIn) {
        this.unitPriceIn = unitPriceIn;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Boolean getKotSelect() {
        return kotSelect;
    }

    public void setKotSelect(Boolean kotSelect) {
        this.kotSelect = kotSelect;
    }

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public double getTaxamt() {
        return taxamt;
    }

    public void setTaxamt(double taxamt) {
        this.taxamt = taxamt;
    }

    public Long getUom() {
        return uom;
    }

    public void setUom(Long uom) {
        this.uom = uom;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public Long getTaxid() {
        return taxid;
    }

    public void setTaxid(Long taxid) {
        this.taxid = taxid;
    }

    public Double getTaxPercentageSplit() {
        return taxPercentageSplit;
    }

    public void setTaxPercentageSplit(Double taxPercentageSplit) {
        this.taxPercentageSplit = taxPercentageSplit;
    }

    public double getGstTaxPercantage() {
        return gstTaxPercantage;
    }

    public void setGstTaxPercantage(double gstTaxPercantage) {
        this.gstTaxPercantage = gstTaxPercantage;
    }

    public Long getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(Long itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    public Long getMscid() {
        return mscid;
    }

    public void setMscid(Long mscid) {
        this.mscid = mscid;
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

    public String getItemTypeName() {
        return itemTypeName;
    }

    public void setItemTypeName(String itemTypeName) {
        this.itemTypeName = itemTypeName;
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

    public String getInclusiveJSON() {
        return inclusiveJSON;
    }

    public void setInclusiveJSON(String inclusiveJSON) {
        this.inclusiveJSON = inclusiveJSON;
    }

    public Long getTaxId() {
        return taxId;
    }

    public void setTaxId(Long taxId) {
        this.taxId = taxId;
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

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getGstItemTax() {
        return gstItemTax;
    }

    public void setGstItemTax(double gstItemTax) {
        this.gstItemTax = gstItemTax;
    }

    public double getAmtinclusivetax() {
        return amtinclusivetax;
    }

    public void setAmtinclusivetax(double amtinclusivetax) {
        this.amtinclusivetax = amtinclusivetax;
    }

    public long getQty() {
        return qty;
    }

    public void setQty(long qty) {
        this.qty = qty;
    }

    public double getAmtexclusivetax() {
        return amtexclusivetax;
    }

    public void setAmtexclusivetax(double amtexclusivetax) {
        this.amtexclusivetax = amtexclusivetax;
    }
}
