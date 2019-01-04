package in.hiaccounts.hinext.item.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ItemModelData implements Serializable {
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
    public Object taxId;
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
    public Boolean isDiscountInPercent;
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
    public Object brandName;
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
    public Object serializableItemId;
    @SerializedName("serializableStatus")
    @Expose
    public String serializableStatus;
    @SerializedName("certificateno")
    @Expose
    public Object certificateno;
    @SerializedName("countType")
    @Expose
    public Object countType;
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
    public Object qty;
    @SerializedName("brandId")
    @Expose
    public Long brandId;
    @SerializedName("doId")
    @Expose
    public Object doId;
    @SerializedName("inventoryMovementId")
    @Expose
    public Long inventoryMovementId;
    @SerializedName("inventoryMovementName")
    @Expose
    public Object inventoryMovementName;
    @SerializedName("mrp")
    @Expose
    public Object mrp;
    @SerializedName("seializeNo")
    @Expose
    public Object seializeNo;
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
    @SerializedName("addedCartsList")
    @Expose
    public Object addedCartsList;
    @SerializedName("itemCommission")
    @Expose
    public Object itemCommission;
    @SerializedName("discountAmt")
    @Expose
    public Double discountAmt;
    @SerializedName("taxPer")
    @Expose
    public Double taxPer;
    @SerializedName("type")
    @Expose
    public Object type;
    @SerializedName("cartStatus")
    @Expose
    public Object cartStatus;
    @SerializedName("cartValue")
    @Expose
    public Object cartValue;
    @SerializedName("productionName")
    @Expose
    public String productionName;
    @SerializedName("serviceDescription")
    @Expose
    public String serviceDescription;
    @SerializedName("taxid")
    @Expose
    public Long taxid;
    @SerializedName("itemCategoryDTO")
    @Expose
    public Object itemCategoryDTO;
    @SerializedName("productionItem")
    @Expose
    public Object productionItem;
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
    public Object assertId;
    @SerializedName("convertedQuantity")
    @Expose
    public Double convertedQuantity;
    @SerializedName("barCode")
    @Expose
    public Object barCode;
    @SerializedName("reOrderLevel")
    @Expose
    public Double reOrderLevel;
    @SerializedName("fileName")
    @Expose
    public String fileName;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("unitOfMeasurementId")
    @Expose
    public Long unitOfMeasurementId;
    @SerializedName("discountInPercent")
    @Expose
    public Boolean discountInPercent;
    @SerializedName("itemDesc")
    @Expose
    public String itemDesc;

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

    public Boolean getDiscountInPercent() {
        return isDiscountInPercent;
    }

    public void setDiscountInPercent(Boolean discountInPercent) {
        isDiscountInPercent = discountInPercent;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
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

    public Object getBrandName() {
        return brandName;
    }

    public void setBrandName(Object brandName) {
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

    public Object getCertificateno() {
        return certificateno;
    }

    public void setCertificateno(Object certificateno) {
        this.certificateno = certificateno;
    }

    public Object getCountType() {
        return countType;
    }

    public void setCountType(Object countType) {
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

    public Object getQty() {
        return qty;
    }

    public void setQty(Object qty) {
        this.qty = qty;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Object getDoId() {
        return doId;
    }

    public void setDoId(Object doId) {
        this.doId = doId;
    }

    public Long getInventoryMovementId() {
        return inventoryMovementId;
    }

    public void setInventoryMovementId(Long inventoryMovementId) {
        this.inventoryMovementId = inventoryMovementId;
    }

    public Object getInventoryMovementName() {
        return inventoryMovementName;
    }

    public void setInventoryMovementName(Object inventoryMovementName) {
        this.inventoryMovementName = inventoryMovementName;
    }

    public Object getMrp() {
        return mrp;
    }

    public void setMrp(Object mrp) {
        this.mrp = mrp;
    }

    public Object getSeializeNo() {
        return seializeNo;
    }

    public void setSeializeNo(Object seializeNo) {
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

    public Object getAddedCartsList() {
        return addedCartsList;
    }

    public void setAddedCartsList(Object addedCartsList) {
        this.addedCartsList = addedCartsList;
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

    public Double getTaxPer() {
        return taxPer;
    }

    public void setTaxPer(Double taxPer) {
        this.taxPer = taxPer;
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

    public String getProductionName() {
        return productionName;
    }

    public void setProductionName(String productionName) {
        this.productionName = productionName;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public Long getTaxid() {
        return taxid;
    }

    public void setTaxid(Long taxid) {
        this.taxid = taxid;
    }

    public Object getItemCategoryDTO() {
        return itemCategoryDTO;
    }

    public void setItemCategoryDTO(Object itemCategoryDTO) {
        this.itemCategoryDTO = itemCategoryDTO;
    }

    public Object getProductionItem() {
        return productionItem;
    }

    public void setProductionItem(Object productionItem) {
        this.productionItem = productionItem;
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

    public Object getAssertId() {
        return assertId;
    }

    public void setAssertId(Object assertId) {
        this.assertId = assertId;
    }

    public Double getConvertedQuantity() {
        return convertedQuantity;
    }

    public void setConvertedQuantity(Double convertedQuantity) {
        this.convertedQuantity = convertedQuantity;
    }

    public Object getBarCode() {
        return barCode;
    }

    public void setBarCode(Object barCode) {
        this.barCode = barCode;
    }

    public Double getReOrderLevel() {
        return reOrderLevel;
    }

    public void setReOrderLevel(Double reOrderLevel) {
        this.reOrderLevel = reOrderLevel;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUnitOfMeasurementId() {
        return unitOfMeasurementId;
    }

    public void setUnitOfMeasurementId(Long unitOfMeasurementId) {
        this.unitOfMeasurementId = unitOfMeasurementId;
    }
}
