
package in.hiaccounts.model.restrapos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetItemDatum {

    @SerializedName("itemName")
    @Expose
    private String itemName;
    @SerializedName("itemId")
    @Expose
    private int itemId;
    @SerializedName("itemCode")
    @Expose
    private String itemCode;
    @SerializedName("stock")
    @Expose
    private float stock;
    @SerializedName("unitPrice")
    @Expose
    private float unitPrice;
    @SerializedName("itemDesc")
    @Expose
    private String itemDesc;
    @SerializedName("taxId")
    @Expose
    private int taxId;
    @SerializedName("itemCategoryName")
    @Expose
    private String itemCategoryName;
    @SerializedName("salesPrice")
    @Expose
    private float salesPrice;
    @SerializedName("isDiscountInPercent")
    @Expose
    private boolean isDiscountInPercent;
    @SerializedName("discountAmountRpercent")
    @Expose
    private float discountAmountRpercent;

    /**
     * No args constructor for use in serialization
     * 
     */
    public GetItemDatum() {
    }

    /**
     * 
     * @param salesPrice
     * @param isDiscountInPercent
     * @param itemName
     * @param stock
     * @param itemCategoryName
     * @param taxId
     * @param unitPrice
     * @param itemDesc
     * @param itemId
     * @param discountAmountRpercent
     * @param itemCode
     */
    public GetItemDatum(String itemName, int itemId, String itemCode, float stock, float unitPrice, String itemDesc, int taxId, String itemCategoryName, float salesPrice, boolean isDiscountInPercent, float discountAmountRpercent) {
        super();
        this.itemName = itemName;
        this.itemId = itemId;
        this.itemCode = itemCode;
        this.stock = stock;
        this.unitPrice = unitPrice;
        this.itemDesc = itemDesc;
        this.taxId = taxId;
        this.itemCategoryName = itemCategoryName;
        this.salesPrice = salesPrice;
        this.isDiscountInPercent = isDiscountInPercent;
        this.discountAmountRpercent = discountAmountRpercent;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public float getStock() {
        return stock;
    }

    public void setStock(float stock) {
        this.stock = stock;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public int getTaxId() {
        return taxId;
    }

    public void setTaxId(int taxId) {
        this.taxId = taxId;
    }

    public String getItemCategoryName() {
        return itemCategoryName;
    }

    public void setItemCategoryName(String itemCategoryName) {
        this.itemCategoryName = itemCategoryName;
    }

    public float getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(float salesPrice) {
        this.salesPrice = salesPrice;
    }

    public boolean isIsDiscountInPercent() {
        return isDiscountInPercent;
    }

    public void setIsDiscountInPercent(boolean isDiscountInPercent) {
        this.isDiscountInPercent = isDiscountInPercent;
    }

    public float getDiscountAmountRpercent() {
        return discountAmountRpercent;
    }

    public void setDiscountAmountRpercent(float discountAmountRpercent) {
        this.discountAmountRpercent = discountAmountRpercent;
    }

}
