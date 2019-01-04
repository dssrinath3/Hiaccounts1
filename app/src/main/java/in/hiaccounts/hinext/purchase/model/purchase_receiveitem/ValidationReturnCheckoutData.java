package in.hiaccounts.hinext.purchase.model.purchase_receiveitem;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by administrator on 1/1/18.
 */

public class ValidationReturnCheckoutData implements Serializable {
    @SerializedName("itemId")
    @Expose
    public Long itemId;
    @SerializedName("itemCode")
    @Expose
    public String itemCode;
    @SerializedName("itemName")
    @Expose
    public String itemName;
    @SerializedName("taxId")
    @Expose
    public Long taxId;
    @SerializedName("outputTaxId")
    @Expose
    public Long outputTaxId;
    @SerializedName("stock")
    @Expose
    public Double stock;
    @SerializedName("discountAmountRpercent")
    @Expose
    public Double discountAmountRpercent;
    @SerializedName("isDiscountInPercent")
    @Expose
    public Long isDiscountInPercent;
    @SerializedName("salesPrice")
    @Expose
    public Double salesPrice;
    @SerializedName("purchasePrice")
    @Expose
    public Double purchasePrice;
    @SerializedName("itemTypeName")
    @Expose
    public String itemTypeName;
    @SerializedName("itemCategoryName")
    @Expose
    public String itemCategoryName;
    @SerializedName("brandName")
    @Expose
    public String brandName;
    @SerializedName("attributeJSON")
    @Expose
    public String attributeJSON;
    @SerializedName("unitPrice")
    @Expose
    public Double unitPrice;
    @SerializedName("unitPriceIn")
    @Expose
    public Double unitPriceIn;
    @SerializedName("serializableItemId")
    @Expose
    public Long serializableItemId;
    @SerializedName("serializableStatus")
    @Expose
    public String serializableStatus;
    @SerializedName("certificateno")
    @Expose
    public String certificateno;
    @SerializedName("countType")
    @Expose
    public String countType;
    @SerializedName("itemCategoryId")
    @Expose
    public Long itemCategoryId;
    @SerializedName("itemTypeId")
    @Expose
    public Long itemTypeId;
    @SerializedName("mscid")
    @Expose
    public Long mscid;
    @SerializedName("inputTaxId")
    @Expose
    public Long inputTaxId;
    @SerializedName("qty")
    @Expose
    public String qty;
    @SerializedName("brandId")
    @Expose
    public Long brandId;
    @SerializedName("inventoryMovementId")
    @Expose
    public Long inventoryMovementId;
    @SerializedName("inventoryMovementName")
    @Expose
    public String inventoryMovementName;
    @SerializedName("mrp")
    @Expose
    public Long mrp;
    @SerializedName("seializeNo")
    @Expose
    public String seializeNo;
    @SerializedName("serializableItemsDTOList")
    @Expose
    public Object serializableItemsDTOList;
    @SerializedName("itemCategoryDTOList")
    @Expose
    public Object itemCategoryDTOList;
    @SerializedName("itemTypeDTOList")
    @Expose
    public Object itemTypeDTOList;
    @SerializedName("itemUOMTypeDTOList")
    @Expose
    public Object itemUOMTypeDTOList;
    @SerializedName("itemMSICDTOList")
    @Expose
    public Object itemMSICDTOList;
    @SerializedName("itemBrandDTOList")
    @Expose
    public Object itemBrandDTOList;
    @SerializedName("itemCountTypeDTOList")
    @Expose
    public Object itemCountTypeDTOList;
    @SerializedName("itemIPTaxDTOList")
    @Expose
    public Object itemIPTaxDTOList;
    @SerializedName("itemOPTaxDTOList")
    @Expose
    public Object itemOPTaxDTOList;
    @SerializedName("uomConvertorDTOList")
    @Expose
    public Object uomConvertorDTOList;
    @SerializedName("itemBatchNoDtos")
    @Expose
    public Object itemBatchNoDtos;
    @SerializedName("itemSerializeNoDtos")
    @Expose
    public Object itemSerializeNoDtos;
    @SerializedName("attributeConfiguratorDTOList")
    @Expose
    public Object attributeConfiguratorDTOList;
    @SerializedName("attributeDTOList")
    @Expose
    public Object attributeDTOList;
    @SerializedName("a1List")
    @Expose
    public Object a1List;
    @SerializedName("a2List")
    @Expose
    public Object a2List;
    @SerializedName("a3List")
    @Expose
    public Object a3List;
    @SerializedName("a4List")
    @Expose
    public Object a4List;
    @SerializedName("a5List")
    @Expose
    public Object a5List;
    @SerializedName("a6List")
    @Expose
    public Object a6List;
    @SerializedName("a7List")
    @Expose
    public Object a7List;
    @SerializedName("a8List")
    @Expose
    public Object a8List;
    @SerializedName("a9List")
    @Expose
    public Object a9List;
    @SerializedName("a10List")
    @Expose
    public Object a10List;
    @SerializedName("tablesPosDTOList")
    @Expose
    public Object tablesPosDTOList;
    @SerializedName("itemcommisionlist")
    @Expose
    public Object itemcommisionlist;
    @SerializedName("itemCommission")
    @Expose
    public Object itemCommission;
    @SerializedName("discountAmt")
    @Expose
    public Double discountAmt;
    @SerializedName("type")
    @Expose
    public Object type;
    @SerializedName("cartStatus")
    @Expose
    public Object cartStatus;
    @SerializedName("cartValue")
    @Expose
    public Object cartValue;
    @SerializedName("imageLocation")
    @Expose
    public Object imageLocation;
    @SerializedName("cess")
    @Expose
    public Double cess;
    @SerializedName("hsnCode")
    @Expose
    public String hsnCode;
    @SerializedName("uomName")
    @Expose
    public String uomName;
    @SerializedName("inclusiveJSON")
    @Expose
    public String inclusiveJSON;
    @SerializedName("assertId")
    @Expose
    public Long assertId;
    @SerializedName("convertedQuantity")
    @Expose
    public Double convertedQuantity;
    @SerializedName("barCode")
    @Expose
    public String barCode;
    @SerializedName("fileName")
    @Expose
    public String fileName;
    @SerializedName("itemDesc")
    @Expose
    public String itemDesc;
    @SerializedName("unitOfMeasurementId")
    @Expose
    public Long unitOfMeasurementId;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("discountInPercent")
    @Expose
    public Long discountInPercent;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
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

    public Long getIsDiscountInPercent() {
        return isDiscountInPercent;
    }

    public void setIsDiscountInPercent(Long isDiscountInPercent) {
        this.isDiscountInPercent = isDiscountInPercent;
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

    public String getItemTypeName() {
        return itemTypeName;
    }

    public void setItemTypeName(String itemTypeName) {
        this.itemTypeName = itemTypeName;
    }

    public String getItemCategoryName() {
        return itemCategoryName;
    }

    public void setItemCategoryName(String itemCategoryName) {
        this.itemCategoryName = itemCategoryName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getAttributeJSON() {
        return attributeJSON;
    }

    public void setAttributeJSON(String attributeJSON) {
        this.attributeJSON = attributeJSON;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getUnitPriceIn() {
        return unitPriceIn;
    }

    public void setUnitPriceIn(Double unitPriceIn) {
        this.unitPriceIn = unitPriceIn;
    }

    public Long getSerializableItemId() {
        return serializableItemId;
    }

    public void setSerializableItemId(Long serializableItemId) {
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

    public Long getItemCategoryId() {
        return itemCategoryId;
    }

    public void setItemCategoryId(Long itemCategoryId) {
        this.itemCategoryId = itemCategoryId;
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

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
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

    public String getInventoryMovementName() {
        return inventoryMovementName;
    }

    public void setInventoryMovementName(String inventoryMovementName) {
        this.inventoryMovementName = inventoryMovementName;
    }

    public Long getMrp() {
        return mrp;
    }

    public void setMrp(Long mrp) {
        this.mrp = mrp;
    }

    public String getSeializeNo() {
        return seializeNo;
    }

    public void setSeializeNo(String seializeNo) {
        this.seializeNo = seializeNo;
    }

    public Object getSerializableItemsDTOList() {
        return serializableItemsDTOList;
    }

    public void setSerializableItemsDTOList(Object serializableItemsDTOList) {
        this.serializableItemsDTOList = serializableItemsDTOList;
    }

    public Object getItemCategoryDTOList() {
        return itemCategoryDTOList;
    }

    public void setItemCategoryDTOList(Object itemCategoryDTOList) {
        this.itemCategoryDTOList = itemCategoryDTOList;
    }

    public Object getItemTypeDTOList() {
        return itemTypeDTOList;
    }

    public void setItemTypeDTOList(Object itemTypeDTOList) {
        this.itemTypeDTOList = itemTypeDTOList;
    }

    public Object getItemUOMTypeDTOList() {
        return itemUOMTypeDTOList;
    }

    public void setItemUOMTypeDTOList(Object itemUOMTypeDTOList) {
        this.itemUOMTypeDTOList = itemUOMTypeDTOList;
    }

    public Object getItemMSICDTOList() {
        return itemMSICDTOList;
    }

    public void setItemMSICDTOList(Object itemMSICDTOList) {
        this.itemMSICDTOList = itemMSICDTOList;
    }

    public Object getItemBrandDTOList() {
        return itemBrandDTOList;
    }

    public void setItemBrandDTOList(Object itemBrandDTOList) {
        this.itemBrandDTOList = itemBrandDTOList;
    }

    public Object getItemCountTypeDTOList() {
        return itemCountTypeDTOList;
    }

    public void setItemCountTypeDTOList(Object itemCountTypeDTOList) {
        this.itemCountTypeDTOList = itemCountTypeDTOList;
    }

    public Object getItemIPTaxDTOList() {
        return itemIPTaxDTOList;
    }

    public void setItemIPTaxDTOList(Object itemIPTaxDTOList) {
        this.itemIPTaxDTOList = itemIPTaxDTOList;
    }

    public Object getItemOPTaxDTOList() {
        return itemOPTaxDTOList;
    }

    public void setItemOPTaxDTOList(Object itemOPTaxDTOList) {
        this.itemOPTaxDTOList = itemOPTaxDTOList;
    }

    public Object getUomConvertorDTOList() {
        return uomConvertorDTOList;
    }

    public void setUomConvertorDTOList(Object uomConvertorDTOList) {
        this.uomConvertorDTOList = uomConvertorDTOList;
    }

    public Object getItemBatchNoDtos() {
        return itemBatchNoDtos;
    }

    public void setItemBatchNoDtos(Object itemBatchNoDtos) {
        this.itemBatchNoDtos = itemBatchNoDtos;
    }

    public Object getItemSerializeNoDtos() {
        return itemSerializeNoDtos;
    }

    public void setItemSerializeNoDtos(Object itemSerializeNoDtos) {
        this.itemSerializeNoDtos = itemSerializeNoDtos;
    }

    public Object getAttributeConfiguratorDTOList() {
        return attributeConfiguratorDTOList;
    }

    public void setAttributeConfiguratorDTOList(Object attributeConfiguratorDTOList) {
        this.attributeConfiguratorDTOList = attributeConfiguratorDTOList;
    }

    public Object getAttributeDTOList() {
        return attributeDTOList;
    }

    public void setAttributeDTOList(Object attributeDTOList) {
        this.attributeDTOList = attributeDTOList;
    }

    public Object getA1List() {
        return a1List;
    }

    public void setA1List(Object a1List) {
        this.a1List = a1List;
    }

    public Object getA2List() {
        return a2List;
    }

    public void setA2List(Object a2List) {
        this.a2List = a2List;
    }

    public Object getA3List() {
        return a3List;
    }

    public void setA3List(Object a3List) {
        this.a3List = a3List;
    }

    public Object getA4List() {
        return a4List;
    }

    public void setA4List(Object a4List) {
        this.a4List = a4List;
    }

    public Object getA5List() {
        return a5List;
    }

    public void setA5List(Object a5List) {
        this.a5List = a5List;
    }

    public Object getA6List() {
        return a6List;
    }

    public void setA6List(Object a6List) {
        this.a6List = a6List;
    }

    public Object getA7List() {
        return a7List;
    }

    public void setA7List(Object a7List) {
        this.a7List = a7List;
    }

    public Object getA8List() {
        return a8List;
    }

    public void setA8List(Object a8List) {
        this.a8List = a8List;
    }

    public Object getA9List() {
        return a9List;
    }

    public void setA9List(Object a9List) {
        this.a9List = a9List;
    }

    public Object getA10List() {
        return a10List;
    }

    public void setA10List(Object a10List) {
        this.a10List = a10List;
    }

    public Object getTablesPosDTOList() {
        return tablesPosDTOList;
    }

    public void setTablesPosDTOList(Object tablesPosDTOList) {
        this.tablesPosDTOList = tablesPosDTOList;
    }

    public Object getItemcommisionlist() {
        return itemcommisionlist;
    }

    public void setItemcommisionlist(Object itemcommisionlist) {
        this.itemcommisionlist = itemcommisionlist;
    }

    public Object getItemCommission() {
        return itemCommission;
    }

    public void setItemCommission(Object itemCommission) {
        this.itemCommission = itemCommission;
    }

    public Double getDiscountAmt() {
        return discountAmt;
    }

    public void setDiscountAmt(Double discountAmt) {
        this.discountAmt = discountAmt;
    }

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }

    public Object getCartStatus() {
        return cartStatus;
    }

    public void setCartStatus(Object cartStatus) {
        this.cartStatus = cartStatus;
    }

    public Object getCartValue() {
        return cartValue;
    }

    public void setCartValue(Object cartValue) {
        this.cartValue = cartValue;
    }

    public Object getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(Object imageLocation) {
        this.imageLocation = imageLocation;
    }

    public Double getCess() {
        return cess;
    }

    public void setCess(Double cess) {
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

    public String getInclusiveJSON() {
        return inclusiveJSON;
    }

    public void setInclusiveJSON(String inclusiveJSON) {
        this.inclusiveJSON = inclusiveJSON;
    }

    public Long getAssertId() {
        return assertId;
    }

    public void setAssertId(Long assertId) {
        this.assertId = assertId;
    }

    public Double getConvertedQuantity() {
        return convertedQuantity;
    }

    public void setConvertedQuantity(Double convertedQuantity) {
        this.convertedQuantity = convertedQuantity;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public Long getUnitOfMeasurementId() {
        return unitOfMeasurementId;
    }

    public void setUnitOfMeasurementId(Long unitOfMeasurementId) {
        this.unitOfMeasurementId = unitOfMeasurementId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getDiscountInPercent() {
        return discountInPercent;
    }

    public void setDiscountInPercent(Long discountInPercent) {
        this.discountInPercent = discountInPercent;
    }
}
