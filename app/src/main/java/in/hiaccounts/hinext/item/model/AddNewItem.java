package in.hiaccounts.hinext.item.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


/**
 * Created by Admin on 5/18/2017.
 */

public class AddNewItem {
    @SerializedName("itemId")
    @Expose
    public Long itemId;
    @SerializedName("itemCode")
    @Expose
    private String itemCode;
    @SerializedName("itemName")
    @Expose
    private String itemName;
    @SerializedName("itemDesc")
    @Expose
    private String itemDisc;
    @SerializedName("salesPrice")
    @Expose
    private String salesPrice;
    @SerializedName("purchasePrice")
    @Expose
    private String purchasePrice;
    @SerializedName("itemCategoryDTO")
    @Expose
    private ItemCategoryDTOList itemCategoryDTO;
    @SerializedName("itemTypeDTO")
    @Expose
    private ItemTypeDTOList itemTypeDTO;
    @SerializedName("itemUOMTypeDTO")
    @Expose
    private ItemUOMTypeDTOList itemUOMTypeDTO;
    @SerializedName("itemMSICDTO")
    @Expose
    private ItemMSICDTOList itemMSICDTO;
    @SerializedName("itemBrandDTO")
    @Expose
    private ItemBrandDTOList itemBrandDTO;
    @SerializedName("itemCountTypeDTO")
    @Expose
    private ItemCountTypeDTOList itemCountTypeDTO;
    @SerializedName("itemIPTaxDTO")
    @Expose
    private ItemIPTaxDTOList itemIPTaxDTO;
    @SerializedName("itemOPTaxDTO")
    @Expose
    private ItemOPTaxDTOList itemOPTaxDTO;
    @SerializedName("inclusiveJSON")
    @Expose
    private String inclusiveJSON;
    @SerializedName("itemImage")
    @Expose
    private String itemImage;
    @SerializedName("cess")
    @Expose
    private String cess;
    @SerializedName("itemStatus")
    @Expose
    private String itemStatus;
    @SerializedName("productionItem")
    @Expose
    private String productionItem;
    @SerializedName("discountInPercent")
    @Expose
    private Boolean discountInPercent;
    @SerializedName("attributeConfiguratorDTOList")
    @Expose
    private List<AttributeConfiguratorDTOList> attributeConfiguratorDTOList = null;
    @SerializedName("addedCartsList")
    @Expose
    public List<AddedCartsList> addedCartsList = null;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public List<AddedCartsList> getAddedCartsList() {
        return addedCartsList;
    }

    public void setAddedCartsList(List<AddedCartsList> addedCartsList) {
        this.addedCartsList = addedCartsList;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public String getProductionItem() {
        return productionItem;
    }

    public void setProductionItem(String productionItem) {
        this.productionItem = productionItem;
    }

    public Boolean getDiscountInPercent() {
        return discountInPercent;
    }

    public void setDiscountInPercent(Boolean discountInPercent) {
        this.discountInPercent = discountInPercent;
    }

    public List<AttributeConfiguratorDTOList> getAttributeConfiguratorDTOList() {
        return attributeConfiguratorDTOList;
    }

    public void setAttributeConfiguratorDTOList(List<AttributeConfiguratorDTOList> attributeConfiguratorDTOList) {
        this.attributeConfiguratorDTOList = attributeConfiguratorDTOList;
    }

    public String getInclusiveJSON() {
        return inclusiveJSON;
    }

    public void setInclusiveJSON(String inclusiveJSON) {
        this.inclusiveJSON = inclusiveJSON;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

    public String getCess() {
        return cess;
    }

    public void setCess(String cess) {
        this.cess = cess;
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

    public String getItemDisc() {
        return itemDisc;
    }

    public void setItemDisc(String itemDisc) {
        this.itemDisc = itemDisc;
    }

    public String getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(String salesPrice) {
        this.salesPrice = salesPrice;
    }

    public String getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(String purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public ItemCategoryDTOList getItemCategoryDTO() {
        return itemCategoryDTO;
    }

    public void setItemCategoryDTO(ItemCategoryDTOList itemCategoryDTO) {
        this.itemCategoryDTO = itemCategoryDTO;
    }

    public ItemTypeDTOList getItemTypeDTO() {
        return itemTypeDTO;
    }

    public void setItemTypeDTO(ItemTypeDTOList itemTypeDTO) {
        this.itemTypeDTO = itemTypeDTO;
    }

    public ItemUOMTypeDTOList getItemUOMTypeDTO() {
        return itemUOMTypeDTO;
    }

    public void setItemUOMTypeDTO(ItemUOMTypeDTOList itemUOMTypeDTO) {
        this.itemUOMTypeDTO = itemUOMTypeDTO;
    }

    public ItemMSICDTOList getItemMSICDTO() {
        return itemMSICDTO;
    }

    public void setItemMSICDTO(ItemMSICDTOList itemMSICDTO) {
        this.itemMSICDTO = itemMSICDTO;
    }

    public ItemBrandDTOList getItemBrandDTO() {
        return itemBrandDTO;
    }

    public void setItemBrandDTO(ItemBrandDTOList itemBrandDTO) {
        this.itemBrandDTO = itemBrandDTO;
    }

    public ItemCountTypeDTOList getItemCountTypeDTO() {
        return itemCountTypeDTO;
    }

    public void setItemCountTypeDTO(ItemCountTypeDTOList itemCountTypeDTO) {
        this.itemCountTypeDTO = itemCountTypeDTO;
    }

    public ItemIPTaxDTOList getItemIPTaxDTO() {
        return itemIPTaxDTO;
    }

    public void setItemIPTaxDTO(ItemIPTaxDTOList itemIPTaxDTO) {
        this.itemIPTaxDTO = itemIPTaxDTO;
    }

    public ItemOPTaxDTOList getItemOPTaxDTO() {
        return itemOPTaxDTO;
    }

    public void setItemOPTaxDTO(ItemOPTaxDTOList itemOPTaxDTO) {
        this.itemOPTaxDTO = itemOPTaxDTO;
    }
}
