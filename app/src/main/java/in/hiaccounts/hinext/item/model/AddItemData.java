
package in.hiaccounts.hinext.item.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class AddItemData implements Serializable
{


    @SerializedName("fileName")
    @Expose
    private String fileName;
    @SerializedName("serializableStatus")
    @Expose
    private String serializableStatus;
    @SerializedName("itemCode")
    @Expose
    private String itemCode;
    @SerializedName("taxId")
    @Expose
    private String taxId;
    @SerializedName("itemName")
    @Expose
    private String itemName;
    @SerializedName("itemId")
    @Expose
    private String itemId;
    @SerializedName("itemDesc")
    @Expose
    private String itemDesc;
    @SerializedName("itemCountTypeDTOList")
    @Expose
    private List<ItemCountTypeDTOList> itemCountTypeDTOList = null;
    @SerializedName("itemMSICDTOList")
    @Expose
    private List<ItemMSICDTOList> itemMSICDTOList = null;
    @SerializedName("itemOPTaxDTOList")
    @Expose
    private List<ItemOPTaxDTOList> itemOPTaxDTOList = null;
    @SerializedName("itemIPTaxDTOList")
    @Expose
    private List<ItemIPTaxDTOList> itemIPTaxDTOList = null;
    @SerializedName("itemBrandDTOList")
    @Expose
    private List<ItemBrandDTOList> itemBrandDTOList = null;
    @SerializedName("itemTypeDTOList")
    @Expose
    private List<ItemTypeDTOList> itemTypeDTOList = null;
    @SerializedName("itemCategoryDTOList")
    @Expose
    private List<ItemCategoryDTOList> itemCategoryDTOList = null;
    @SerializedName("itemUOMTypeDTOList")
    @Expose
    private List<ItemUOMTypeDTOList> itemUOMTypeDTOList = null;
    @SerializedName("isDiscountInPercent")
    @Expose
    private boolean isDiscountInPercent;
    @SerializedName("serializableItemsDTOList")
    @Expose
    private String serializableItemsDTOList;
    @SerializedName("itemTypeName")
    @Expose
    private String itemTypeName;
    @SerializedName("stock")
    @Expose
    private long stock;
    @SerializedName("discountAmountRpercent")
    @Expose
    private double discountAmountRpercent;
    @SerializedName("purchasePrice")
    @Expose
    private double purchasePrice;
    @SerializedName("salesPrice")
    @Expose
    private double salesPrice;
    @SerializedName("itemCategoryName")
    @Expose
    private String itemCategoryName;
    @SerializedName("unitPrice")
    @Expose
    private double unitPrice;
    @SerializedName("serializableItemId")
    @Expose
    private String serializableItemId;
    @SerializedName("attributeConfiguratorDTOList")
    @Expose
    private List<AttributeConfiguratorDTOList> attributeConfiguratorDTOList = null;
    @SerializedName("discountInPercent")
    @Expose
    private Boolean discountInPercent;

    @SerializedName("addedCartsList")
    @Expose
    public List<AddedCartsList> addedCartsList = null;

    public List<AddedCartsList> getAddedCartsList() {
        return addedCartsList;
    }

    public void setAddedCartsList(List<AddedCartsList> addedCartsList) {
        this.addedCartsList = addedCartsList;
    }

    public List<AttributeConfiguratorDTOList> getAttributeConfiguratorDTOList() {
        return attributeConfiguratorDTOList;
    }

    public void setAttributeConfiguratorDTOList(List<AttributeConfiguratorDTOList> attributeConfiguratorDTOList) {
        this.attributeConfiguratorDTOList = attributeConfiguratorDTOList;
    }

    public Boolean getDiscountInPercent() {
        return discountInPercent;
    }

    public void setDiscountInPercent(Boolean discountInPercent) {
        this.discountInPercent = discountInPercent;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSerializableStatus() {
        return serializableStatus;
    }

    public void setSerializableStatus(String serializableStatus) {
        this.serializableStatus = serializableStatus;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public List<ItemCountTypeDTOList> getItemCountTypeDTOList() {
        return itemCountTypeDTOList;
    }

    public void setItemCountTypeDTOList(List<ItemCountTypeDTOList> itemCountTypeDTOList) {
        this.itemCountTypeDTOList = itemCountTypeDTOList;
    }

    public List<ItemMSICDTOList> getItemMSICDTOList() {
        return itemMSICDTOList;
    }

    public void setItemMSICDTOList(List<ItemMSICDTOList> itemMSICDTOList) {
        this.itemMSICDTOList = itemMSICDTOList;
    }

    public List<ItemOPTaxDTOList> getItemOPTaxDTOList() {
        return itemOPTaxDTOList;
    }

    public void setItemOPTaxDTOList(List<ItemOPTaxDTOList> itemOPTaxDTOList) {
        this.itemOPTaxDTOList = itemOPTaxDTOList;
    }

    public List<ItemIPTaxDTOList> getItemIPTaxDTOList() {
        return itemIPTaxDTOList;
    }

    public void setItemIPTaxDTOList(List<ItemIPTaxDTOList> itemIPTaxDTOList) {
        this.itemIPTaxDTOList = itemIPTaxDTOList;
    }

    public List<ItemBrandDTOList> getItemBrandDTOList() {
        return itemBrandDTOList;
    }

    public void setItemBrandDTOList(List<ItemBrandDTOList> itemBrandDTOList) {
        this.itemBrandDTOList = itemBrandDTOList;
    }

    public List<ItemTypeDTOList> getItemTypeDTOList() {
        return itemTypeDTOList;
    }

    public void setItemTypeDTOList(List<ItemTypeDTOList> itemTypeDTOList) {
        this.itemTypeDTOList = itemTypeDTOList;
    }

    public List<ItemCategoryDTOList> getItemCategoryDTOList() {
        return itemCategoryDTOList;
    }

    public void setItemCategoryDTOList(List<ItemCategoryDTOList> itemCategoryDTOList) {
        this.itemCategoryDTOList = itemCategoryDTOList;
    }

    public List<ItemUOMTypeDTOList> getItemUOMTypeDTOList() {
        return itemUOMTypeDTOList;
    }

    public void setItemUOMTypeDTOList(List<ItemUOMTypeDTOList> itemUOMTypeDTOList) {
        this.itemUOMTypeDTOList = itemUOMTypeDTOList;
    }

    public boolean isDiscountInPercent() {
        return isDiscountInPercent;
    }

    public void setDiscountInPercent(boolean discountInPercent) {
        isDiscountInPercent = discountInPercent;
    }

    public String getSerializableItemsDTOList() {
        return serializableItemsDTOList;
    }

    public void setSerializableItemsDTOList(String serializableItemsDTOList) {
        this.serializableItemsDTOList = serializableItemsDTOList;
    }

    public String getItemTypeName() {
        return itemTypeName;
    }

    public void setItemTypeName(String itemTypeName) {
        this.itemTypeName = itemTypeName;
    }

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }

    public double getDiscountAmountRpercent() {
        return discountAmountRpercent;
    }

    public void setDiscountAmountRpercent(double discountAmountRpercent) {
        this.discountAmountRpercent = discountAmountRpercent;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(double salesPrice) {
        this.salesPrice = salesPrice;
    }

    public String getItemCategoryName() {
        return itemCategoryName;
    }

    public void setItemCategoryName(String itemCategoryName) {
        this.itemCategoryName = itemCategoryName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getSerializableItemId() {
        return serializableItemId;
    }

    public void setSerializableItemId(String serializableItemId) {
        this.serializableItemId = serializableItemId;
    }
}
