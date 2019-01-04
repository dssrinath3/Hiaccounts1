package in.hiaccounts.hinext.inventory.model.assests;

/**
 * Created by shrinath on 11/30/2017.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AddNewAssests implements Serializable{
    @SerializedName("assestId")
    @Expose
    private Long assestId;
    @SerializedName("assestCode")
    @Expose
    private String assestCode;
    @SerializedName("assestName")
    @Expose
    private String assestName;
    @SerializedName("assestDesc")
    @Expose
    private String assestDesc;
    @SerializedName("assestAccountCode")
    @Expose
    private String assestAccountCode;
    @SerializedName("taxId")
    @Expose
    private Long taxId;
    @SerializedName("stock")
    @Expose
    private Double stock;
    @SerializedName("discountAmountRpercent")
    @Expose
    private Double discountAmountRpercent;
    @SerializedName("salesPrice")
    @Expose
    private String salesPrice;
    @SerializedName("purchasePrice")
    @Expose
    private String purchasePrice;
    @SerializedName("itemCategoryName")
    @Expose
    private String itemCategoryName;
    @SerializedName("unitPrice")
    @Expose
    private Double unitPrice;
    @SerializedName("serializableItemId")
    @Expose
    private String serializableItemId;
    @SerializedName("serializableStatus")
    @Expose
    private String serializableStatus;
    @SerializedName("certificateno")
    @Expose
    private String certificateno;
    @SerializedName("cess")
    @Expose
    private String cess;
    @SerializedName("itemCategoryDTO")
    @Expose
    private String itemCategoryDTO;
    @SerializedName("assestsCategoryDTO")
    @Expose
    private AssestCategoryDTOList assestsCategoryDTO;
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
    @SerializedName("itemCatID")
    @Expose
    private Long itemCatID;
    @SerializedName("itemTyID")
    @Expose
    private Long itemTyID;
    @SerializedName("itemUOMTyID")
    @Expose
    private Long itemUOMTyID;
    @SerializedName("itemMSICID")
    @Expose
    private Long itemMSICID;
    @SerializedName("itemBrandID")
    @Expose
    private Long itemBrandID;
    @SerializedName("itemOPTaxID")
    @Expose
    private Long itemOPTaxID;
    @SerializedName("itemIPTaxID")
    @Expose
    private Long itemIPTaxID;
    @SerializedName("inclusiveJSON")
    @Expose
    private String inclusiveJSON;
    @SerializedName("itemImage")
    @Expose
    private String itemImage;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("discountInPercent")
    @Expose
    private Boolean discountInPercent;

    public Long getAssestId() {
        return assestId;
    }

    public void setAssestId(Long assestId) {
        this.assestId = assestId;
    }

    public String getAssestCode() {
        return assestCode;
    }

    public void setAssestCode(String assestCode) {
        this.assestCode = assestCode;
    }

    public String getAssestName() {
        return assestName;
    }

    public void setAssestName(String assestName) {
        this.assestName = assestName;
    }

    public String getAssestDesc() {
        return assestDesc;
    }

    public void setAssestDesc(String assestDesc) {
        this.assestDesc = assestDesc;
    }

    public String getAssestAccountCode() {
        return assestAccountCode;
    }

    public void setAssestAccountCode(String assestAccountCode) {
        this.assestAccountCode = assestAccountCode;
    }

    public Long getTaxId() {
        return taxId;
    }

    public void setTaxId(Long taxId) {
        this.taxId = taxId;
    }

    public Double getStock() {
        return stock;
    }

    public void setStock(Double stock) {
        this.stock = stock;
    }

    public Double getDiscountAmountRpercent() {
        return discountAmountRpercent;
    }

    public void setDiscountAmountRpercent(Double discountAmountRpercent) {
        this.discountAmountRpercent = discountAmountRpercent;
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

    public String getSerializableItemId() {
        return serializableItemId;
    }

    public void setSerializableItemId(String serializableItemId) {
        this.serializableItemId = serializableItemId;
    }

    public String getSerializableStatus() {
        return serializableStatus;
    }

    public void setSerializableStatus(String serializableStatus) {
        this.serializableStatus = serializableStatus;
    }

    public String getCertificateno() {
        return certificateno;
    }

    public void setCertificateno(String certificateno) {
        this.certificateno = certificateno;
    }

    public String getCess() {
        return cess;
    }

    public void setCess(String cess) {
        this.cess = cess;
    }

    public String getItemCategoryDTO() {
        return itemCategoryDTO;
    }

    public void setItemCategoryDTO(String itemCategoryDTO) {
        this.itemCategoryDTO = itemCategoryDTO;
    }

    public AssestCategoryDTOList getAssestsCategoryDTO() {
        return assestsCategoryDTO;
    }

    public void setAssestsCategoryDTO(AssestCategoryDTOList assestsCategoryDTO) {
        this.assestsCategoryDTO = assestsCategoryDTO;
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

    public Long getItemCatID() {
        return itemCatID;
    }

    public void setItemCatID(Long itemCatID) {
        this.itemCatID = itemCatID;
    }

    public Long getItemTyID() {
        return itemTyID;
    }

    public void setItemTyID(Long itemTyID) {
        this.itemTyID = itemTyID;
    }

    public Long getItemUOMTyID() {
        return itemUOMTyID;
    }

    public void setItemUOMTyID(Long itemUOMTyID) {
        this.itemUOMTyID = itemUOMTyID;
    }

    public Long getItemMSICID() {
        return itemMSICID;
    }

    public void setItemMSICID(Long itemMSICID) {
        this.itemMSICID = itemMSICID;
    }

    public Long getItemBrandID() {
        return itemBrandID;
    }

    public void setItemBrandID(Long itemBrandID) {
        this.itemBrandID = itemBrandID;
    }

    public Long getItemOPTaxID() {
        return itemOPTaxID;
    }

    public void setItemOPTaxID(Long itemOPTaxID) {
        this.itemOPTaxID = itemOPTaxID;
    }

    public Long getItemIPTaxID() {
        return itemIPTaxID;
    }

    public void setItemIPTaxID(Long itemIPTaxID) {
        this.itemIPTaxID = itemIPTaxID;
    }

    public String getInclusiveJSON() {
        return inclusiveJSON;
    }

    public void setInclusiveJSON(String inclusiveJSON) {
        this.inclusiveJSON = inclusiveJSON;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getDiscountInPercent() {
        return discountInPercent;
    }

    public void setDiscountInPercent(Boolean discountInPercent) {
        this.discountInPercent = discountInPercent;
    }
}
