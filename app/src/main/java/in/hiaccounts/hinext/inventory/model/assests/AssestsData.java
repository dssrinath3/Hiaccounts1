package in.hiaccounts.hinext.inventory.model.assests;

/**
 * Created by shrinath on 11/29/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class AssestsData implements Serializable {

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
    private Long taxId;
    @SerializedName("outputTaxId")
    @Expose
    private Long outputTaxId;
    @SerializedName("stock")
    @Expose
    private Long stock;
    @SerializedName("discountAmountRpercent")
    @Expose
    private Double discountAmountRpercent;
    @SerializedName("itemTypeName")
    @Expose
    private String itemTypeName;
    @SerializedName("salesPrice")
    @Expose
    private Double salesPrice;
    @SerializedName("purchasePrice")
    @Expose
    private Double purchasePrice;
    @SerializedName("assestTypeName")
    @Expose
    private String assestTypeName;
    @SerializedName("assestsCategoryName")
    @Expose
    private String assestsCategoryName;
    @SerializedName("brandName")
    @Expose
    private String brandName;
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
    @SerializedName("countType")
    @Expose
    private String countType;
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
    private Long brandId;
    @SerializedName("inventoryMovementId")
    @Expose
    private Long inventoryMovementId;
    @SerializedName("fileName")
    @Expose
    private String fileName;
    @SerializedName("cess")
    @Expose
    private String cess;
    @SerializedName("hsnCode")
    @Expose
    private String hsnCode;
    @SerializedName("uomName")
    @Expose
    private String uomName;
    @SerializedName("serializableItemsDTOList")
    @Expose
    private String serializableItemsDTOList;
    @SerializedName("assestCategoryDTOList")
    @Expose
    private List<AssestCategoryDTOList> assestCategoryDTOList = null;
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
    private Object inclusiveJSON;
    @SerializedName("status")
    @Expose
    private Object status;
    @SerializedName("discountInPercent")
    @Expose
    private Boolean discountInPercent;
    @SerializedName("unitOfMeasurementId")
    @Expose
    private Object unitOfMeasurementId;


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

    public Long getTaxId() {
        return taxId;
    }

    public void setTaxId(Long taxId) {
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

    public Double getDiscountAmountRpercent() {
        return discountAmountRpercent;
    }

    public void setDiscountAmountRpercent(Double discountAmountRpercent) {
        this.discountAmountRpercent = discountAmountRpercent;
    }

    public String getItemTypeName() {
        return itemTypeName;
    }

    public void setItemTypeName(String itemTypeName) {
        this.itemTypeName = itemTypeName;
    }

    public Double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(Double salesPrice) {
        this.salesPrice = salesPrice;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getAssestTypeName() {
        return assestTypeName;
    }

    public void setAssestTypeName(String assestTypeName) {
        this.assestTypeName = assestTypeName;
    }

    public String getAssestsCategoryName() {
        return assestsCategoryName;
    }

    public void setAssestsCategoryName(String assestsCategoryName) {
        this.assestsCategoryName = assestsCategoryName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
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

    public String getCountType() {
        return countType;
    }

    public void setCountType(String countType) {
        this.countType = countType;
    }

    public Long getAssestCategoryId() {
        return assestCategoryId;
    }

    public void setAssestCategoryId(Long assestCategoryId) {
        this.assestCategoryId = assestCategoryId;
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

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getInventoryMovementId() {
        return inventoryMovementId;
    }

    public void setInventoryMovementId(Long inventoryMovementId) {
        this.inventoryMovementId = inventoryMovementId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getCess() {
        return cess;
    }

    public void setCess(String cess) {
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

    public String getSerializableItemsDTOList() {
        return serializableItemsDTOList;
    }

    public void setSerializableItemsDTOList(String serializableItemsDTOList) {
        this.serializableItemsDTOList = serializableItemsDTOList;
    }

    public List<AssestCategoryDTOList> getAssestCategoryDTOList() {
        return assestCategoryDTOList;
    }

    public void setAssestCategoryDTOList(List<AssestCategoryDTOList> assestCategoryDTOList) {
        this.assestCategoryDTOList = assestCategoryDTOList;
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

    public Object getInclusiveJSON() {
        return inclusiveJSON;
    }

    public void setInclusiveJSON(Object inclusiveJSON) {
        this.inclusiveJSON = inclusiveJSON;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public Boolean getDiscountInPercent() {
        return discountInPercent;
    }

    public void setDiscountInPercent(Boolean discountInPercent) {
        this.discountInPercent = discountInPercent;
    }

    public Object getUnitOfMeasurementId() {
        return unitOfMeasurementId;
    }

    public void setUnitOfMeasurementId(Object unitOfMeasurementId) {
        this.unitOfMeasurementId = unitOfMeasurementId;
    }
}
