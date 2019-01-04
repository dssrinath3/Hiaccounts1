package in.hiaccounts.hinext.inventory.model.assests;

/**
 * Created by shrinath on 11/30/2017.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class AssestsSelectData implements Serializable{

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
    @SerializedName("taxId")
    @Expose
    private Object taxId;
    @SerializedName("outputTaxId")
    @Expose
    private Long outputTaxId;
    @SerializedName("stock")
    @Expose
    private Long stock;
    @SerializedName("discountAmountRpercent")
    @Expose
    private Long discountAmountRpercent;
    @SerializedName("itemTypeName")
    @Expose
    private Object itemTypeName;
    @SerializedName("salesPrice")
    @Expose
    private Long salesPrice;
    @SerializedName("purchasePrice")
    @Expose
    private Long purchasePrice;
    @SerializedName("assestTypeName")
    @Expose
    private Object assestTypeName;
    @SerializedName("assestsCategoryName")
    @Expose
    private String assestsCategoryName;
    @SerializedName("brandName")
    @Expose
    private Object brandName;
    @SerializedName("unitPrice")
    @Expose
    private Long unitPrice;
    @SerializedName("serializableItemId")
    @Expose
    private Object serializableItemId;
    @SerializedName("serializableStatus")
    @Expose
    private String serializableStatus;
    @SerializedName("certificateno")
    @Expose
    private String certificateno;
    @SerializedName("countType")
    @Expose
    private Object countType;
    @SerializedName("assestCategoryId")
    @Expose
    private Long assestCategoryId;
    @SerializedName("itemTypeId")
    @Expose
    private Long itemTypeId;
    @SerializedName("mscid")
    @Expose
    private Long mscid;
    @SerializedName("inputTaxId")
    @Expose
    private Long inputTaxId;
    @SerializedName("brandId")
    @Expose
    private Object brandId;
    @SerializedName("inventoryMovementId")
    @Expose
    private Long inventoryMovementId;
    @SerializedName("fileName")
    @Expose
    private Object fileName;
    @SerializedName("cess")
    @Expose
    private Long cess;
    @SerializedName("hsnCode")
    @Expose
    private String hsnCode;
    @SerializedName("uomName")
    @Expose
    private String uomName;
    @SerializedName("serializableItemsDTOList")
    @Expose
    private Object serializableItemsDTOList;
    @SerializedName("assestCategoryDTOList")
    @Expose
    private List<AssestCategoryDTOList> itemCategoryDTOList = null;
    @SerializedName("itemTypeDTOList")
    @Expose
    private List<ItemTypeDTOList> itemTypeDTOList = null;
    @SerializedName("itemUOMTypeDTOList")
    @Expose
    private List<ItemUOMTypeDTOList> itemUOMTypeDTOList = null;
    @SerializedName("itemMSICDTOList")
    @Expose
    private List<ItemMSICDTOList> itemMSICDTOList = null;
    @SerializedName("itemBrandDTOList")
    @Expose
    private List<ItemBrandDTOList> itemBrandDTOList = null;
    @SerializedName("itemCountTypeDTOList")
    @Expose
    private List<ItemCountTypeDTOList> itemCountTypeDTOList = null;
    @SerializedName("itemIPTaxDTOList")
    @Expose
    private List<ItemIPTaxDTOList> itemIPTaxDTOList = null;
    @SerializedName("itemOPTaxDTOList")
    @Expose
    private List<ItemOPTaxDTOList> itemOPTaxDTOList = null;
    @SerializedName("uomConvertorDTOList")
    @Expose
    private Object uomConvertorDTOList;
    @SerializedName("inclusiveJSON")
    @Expose
    private String inclusiveJSON;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("discountInPercent")
    @Expose
    private Boolean discountInPercent;
    @SerializedName("unitOfMeasurementId")
    @Expose
    private Long unitOfMeasurementId;

    public Long getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(Long itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    public Long getAssestCategoryId() {
        return assestCategoryId;
    }

    public void setAssestCategoryId(Long assestCategoryId) {
        this.assestCategoryId = assestCategoryId;
    }

    public String getInclusiveJSON() {
        return inclusiveJSON;
    }

    public void setInclusiveJSON(String inclusiveJSON) {
        this.inclusiveJSON = inclusiveJSON;
    }

    public String getCertificateno() {
        return certificateno;
    }

    public void setCertificateno(String certificateno) {
        this.certificateno = certificateno;
    }

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

    public Object getTaxId() {
        return taxId;
    }

    public void setTaxId(Object taxId) {
        this.taxId = taxId;
    }

    public Long getOutputTaxId() {
        return outputTaxId;
    }

    public void setOutputTaxId(Long outputTaxId) {
        this.outputTaxId = outputTaxId;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public Long getDiscountAmountRpercent() {
        return discountAmountRpercent;
    }

    public void setDiscountAmountRpercent(Long discountAmountRpercent) {
        this.discountAmountRpercent = discountAmountRpercent;
    }

    public Object getItemTypeName() {
        return itemTypeName;
    }

    public void setItemTypeName(Object itemTypeName) {
        this.itemTypeName = itemTypeName;
    }

    public Long getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(Long salesPrice) {
        this.salesPrice = salesPrice;
    }

    public Long getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Long purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Object getAssestTypeName() {
        return assestTypeName;
    }

    public void setAssestTypeName(Object assestTypeName) {
        this.assestTypeName = assestTypeName;
    }

    public String getAssestsCategoryName() {
        return assestsCategoryName;
    }

    public void setAssestsCategoryName(String assestsCategoryName) {
        this.assestsCategoryName = assestsCategoryName;
    }

    public Object getBrandName() {
        return brandName;
    }

    public void setBrandName(Object brandName) {
        this.brandName = brandName;
    }

    public Long getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Long unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Object getSerializableItemId() {
        return serializableItemId;
    }

    public void setSerializableItemId(Object serializableItemId) {
        this.serializableItemId = serializableItemId;
    }

    public String getSerializableStatus() {
        return serializableStatus;
    }

    public void setSerializableStatus(String serializableStatus) {
        this.serializableStatus = serializableStatus;
    }


    public Object getCountType() {
        return countType;
    }

    public void setCountType(Object countType) {
        this.countType = countType;
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

    public Object getBrandId() {
        return brandId;
    }

    public void setBrandId(Object brandId) {
        this.brandId = brandId;
    }

    public Long getInventoryMovementId() {
        return inventoryMovementId;
    }

    public void setInventoryMovementId(Long inventoryMovementId) {
        this.inventoryMovementId = inventoryMovementId;
    }

    public Object getFileName() {
        return fileName;
    }

    public void setFileName(Object fileName) {
        this.fileName = fileName;
    }

    public Long getCess() {
        return cess;
    }

    public void setCess(Long cess) {
        this.cess = cess;
    }

    public String getHsnCode() {
        return hsnCode;
    }

    public void setHsnCode(String hsnCode) {
        this.hsnCode = hsnCode;
    }

    public String getUomName() {
        return uomName;
    }

    public void setUomName(String uomName) {
        this.uomName = uomName;
    }

    public Object getSerializableItemsDTOList() {
        return serializableItemsDTOList;
    }

    public void setSerializableItemsDTOList(Object serializableItemsDTOList) {
        this.serializableItemsDTOList = serializableItemsDTOList;
    }

    public List<AssestCategoryDTOList> getItemCategoryDTOList() {
        return itemCategoryDTOList;
    }

    public void setItemCategoryDTOList(List<AssestCategoryDTOList> itemCategoryDTOList) {
        this.itemCategoryDTOList = itemCategoryDTOList;
    }

    public List<ItemTypeDTOList> getItemTypeDTOList() {
        return itemTypeDTOList;
    }

    public void setItemTypeDTOList(List<ItemTypeDTOList> itemTypeDTOList) {
        this.itemTypeDTOList = itemTypeDTOList;
    }

    public List<ItemUOMTypeDTOList> getItemUOMTypeDTOList() {
        return itemUOMTypeDTOList;
    }

    public void setItemUOMTypeDTOList(List<ItemUOMTypeDTOList> itemUOMTypeDTOList) {
        this.itemUOMTypeDTOList = itemUOMTypeDTOList;
    }

    public List<ItemMSICDTOList> getItemMSICDTOList() {
        return itemMSICDTOList;
    }

    public void setItemMSICDTOList(List<ItemMSICDTOList> itemMSICDTOList) {
        this.itemMSICDTOList = itemMSICDTOList;
    }

    public List<ItemBrandDTOList> getItemBrandDTOList() {
        return itemBrandDTOList;
    }

    public void setItemBrandDTOList(List<ItemBrandDTOList> itemBrandDTOList) {
        this.itemBrandDTOList = itemBrandDTOList;
    }

    public List<ItemCountTypeDTOList> getItemCountTypeDTOList() {
        return itemCountTypeDTOList;
    }

    public void setItemCountTypeDTOList(List<ItemCountTypeDTOList> itemCountTypeDTOList) {
        this.itemCountTypeDTOList = itemCountTypeDTOList;
    }

    public List<ItemIPTaxDTOList> getItemIPTaxDTOList() {
        return itemIPTaxDTOList;
    }

    public void setItemIPTaxDTOList(List<ItemIPTaxDTOList> itemIPTaxDTOList) {
        this.itemIPTaxDTOList = itemIPTaxDTOList;
    }

    public List<ItemOPTaxDTOList> getItemOPTaxDTOList() {
        return itemOPTaxDTOList;
    }

    public void setItemOPTaxDTOList(List<ItemOPTaxDTOList> itemOPTaxDTOList) {
        this.itemOPTaxDTOList = itemOPTaxDTOList;
    }

    public Object getUomConvertorDTOList() {
        return uomConvertorDTOList;
    }

    public void setUomConvertorDTOList(Object uomConvertorDTOList) {
        this.uomConvertorDTOList = uomConvertorDTOList;
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

    public Long getUnitOfMeasurementId() {
        return unitOfMeasurementId;
    }

    public void setUnitOfMeasurementId(Long unitOfMeasurementId) {
        this.unitOfMeasurementId = unitOfMeasurementId;
    }
}
