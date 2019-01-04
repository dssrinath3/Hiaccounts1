package in.hiaccounts.hinext.restaurant.model.category_item;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by shrinath on 11/22/2017.
 */


public class SelectedItemsList implements Serializable {

    @SerializedName("itemId")
    @Expose
    private Long itemId;
    @SerializedName("itemCode")
    @Expose
    private String itemCode;
    @SerializedName("itemName")
    @Expose
    private String itemName;
    @SerializedName("taxId")
    @Expose
    private Long taxId;
    @SerializedName("outputTaxId")
    @Expose
    private Long outputTaxId;
    @SerializedName("qty")
    @Expose
    private Long qty;
    @SerializedName("stock")
    @Expose
    private Double stock;
    @SerializedName("discountAmountRpercent")
    @Expose
    private Double discountAmountRpercent;
    @SerializedName("isDiscountInPercent")
    @Expose
    private Boolean isDiscountInPercent;
    @SerializedName("salesPrice")
    @Expose
    private Double salesPrice;
    @SerializedName("purchasePrice")
    @Expose
    private Double purchasePrice;
    @SerializedName("itemTypeName")
    @Expose
    private String itemTypeName;
    @SerializedName("itemCategoryName")
    @Expose
    private String itemCategoryName;
    @SerializedName("brandName")
    @Expose
    private Object brandName;
    @SerializedName("unitPrice")
    @Expose
    private Double unitPrice;
    @SerializedName("unitPriceIn")
    @Expose
    private Double unitPriceIn;
    @SerializedName("serializableItemId")
    @Expose
    private Object serializableItemId;
    @SerializedName("serializableStatus")
    @Expose
    private String serializableStatus;
    @SerializedName("certificateno")
    @Expose
    private Object certificateno;
    @SerializedName("countType")
    @Expose
    private Object countType;
    @SerializedName("itemCategoryId")
    @Expose
    private Long itemCategoryId;
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
    private Object inventoryMovementId;
    @SerializedName("serializableItemsDTOList")
    @Expose
    private Object serializableItemsDTOList;
    @SerializedName("itemCategoryDTOList")
    @Expose
    private Object itemCategoryDTOList;
    @SerializedName("itemTypeDTOList")
    @Expose
    private Object itemTypeDTOList;
    @SerializedName("itemUOMTypeDTOList")
    @Expose
    private Object itemUOMTypeDTOList;
    @SerializedName("itemMSICDTOList")
    @Expose
    private Object itemMSICDTOList;
    @SerializedName("itemBrandDTOList")
    @Expose
    private Object itemBrandDTOList;
    @SerializedName("itemCountTypeDTOList")
    @Expose
    private Object itemCountTypeDTOList;
    @SerializedName("itemIPTaxDTOList")
    @Expose
    private Object itemIPTaxDTOList;
    @SerializedName("itemOPTaxDTOList")
    @Expose
    private Object itemOPTaxDTOList;
    @SerializedName("uomConvertorDTOList")
    @Expose
    private Object uomConvertorDTOList;
    @SerializedName("itemBatchNoDtos")
    @Expose
    private Object itemBatchNoDtos;
    @SerializedName("itemSerializeNoDtos")
    @Expose
    private Object itemSerializeNoDtos;
    @SerializedName("cess")
    @Expose
    private Double cess;
    @SerializedName("hsnCode")
    @Expose
    private String hsnCode;
    @SerializedName("uomName")
    @Expose
    private String uomName;
    @SerializedName("inclusiveJSON")
    @Expose
    private String inclusiveJSON;
    @SerializedName("assertId")
    @Expose
    private Object assertId;
    @SerializedName("convertedQuantity")
    @Expose
    private Double convertedQuantity;
    @SerializedName("amtexclusivetax")
    @Expose
    private Double amtexclusivetax;
    @SerializedName("amtinclusivetax")
    @Expose
    private Double amtinclusivetax;
    @SerializedName("taxid")
    @Expose
    private Long taxid;
    @SerializedName("taxamt")
    @Expose
    private Double taxamt;
    @SerializedName("gstItemTax")
    @Expose
    private Double gstItemTax;

    @SerializedName("customerId")
    @Expose
    private Long customerId;

    @SerializedName("fileName")
    @Expose
    private String fileName;
    @SerializedName("itemDesc")
    @Expose
    private String itemDesc;
    @SerializedName("itemDescription")
    @Expose
    private String itemDescription;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("unitOfMeasurementId")
    @Expose
    private Integer unitOfMeasurementId;
    @SerializedName("uom")
    @Expose
    public Long uom;
    @SerializedName("uomValue")
    @Expose
    public Long uomValue;
    @SerializedName("uomConvertedName")
    @Expose
    public String uomConvertedName;
    @SerializedName("taxName")
    @Expose
    public String taxName;
    @SerializedName("discountAmt")
    @Expose
    public Double discountAmt;



    private BigDecimal price;
    private long itemQuantity;
    private double itemTotalAmountInTax;
    private double itemTotalAmountExTax;
    private double itemTotalAmount;
    boolean isSelect;
    private double gstTaxPercantage;
    private double gstTaxAmt;
    private String tableId;
    private String tableName;
    private Boolean kotSelect;
    private String floorId;
    private String type;
    private String waiterName;

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getWaiterName() {
        return waiterName;
    }

    public void setWaiterName(String waiterName) {
        this.waiterName = waiterName;
    }

    public Double getDiscountAmt() {
        return discountAmt;
    }

    public void setDiscountAmt(Double discountAmt) {
        this.discountAmt = discountAmt;
    }

    public String getTaxName() {
        return taxName;
    }

    public void setTaxName(String taxName) {
        this.taxName = taxName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    public Boolean getKotSelect() {
        return kotSelect;
    }

    public void setKotSelect(Boolean kotSelect) {
        this.kotSelect = kotSelect;
    }

    public Long getUom() {
        return uom;
    }

    public void setUom(Long uom) {
        this.uom = uom;
    }

    public Long getUomValue() {
        return uomValue;
    }

    public void setUomValue(Long uomValue) {
        this.uomValue = uomValue;
    }

    public String getUomConvertedName() {
        return uomConvertedName;
    }

    public void setUomConvertedName(String uomConvertedName) {
        this.uomConvertedName = uomConvertedName;
    }

    public Double getAmtexclusivetax() {
        return amtexclusivetax;
    }

    public void setAmtexclusivetax(Double amtexclusivetax) {
        this.amtexclusivetax = amtexclusivetax;
    }

    public Double getAmtinclusivetax() {
        return amtinclusivetax;
    }

    public void setAmtinclusivetax(Double amtinclusivetax) {
        this.amtinclusivetax = amtinclusivetax;
    }

    public Long getTaxid() {
        return taxid;
    }

    public void setTaxid(Long taxid) {
        this.taxid = taxid;
    }

    public Double getTaxamt() {
        return taxamt;
    }

    public void setTaxamt(Double taxamt) {
        this.taxamt = taxamt;
    }

    public Double getGstItemTax() {
        return gstItemTax;
    }

    public void setGstItemTax(Double gstItemTax) {
        this.gstItemTax = gstItemTax;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public double getGstTaxPercantage() {
        return gstTaxPercantage;
    }

    public void setGstTaxPercantage(double gstTaxPercantage) {
        this.gstTaxPercantage = gstTaxPercantage;
    }

    public double getGstTaxAmt() {
        return gstTaxAmt;
    }

    public void setGstTaxAmt(double gstTaxAmt) {
        this.gstTaxAmt = gstTaxAmt;
    }

    public double getItemTotalAmountInTax() {
        return itemTotalAmountInTax;
    }

    public void setItemTotalAmountInTax(double itemTotalAmountInTax) {
        this.itemTotalAmountInTax = itemTotalAmountInTax;
    }

    public double getItemTotalAmountExTax() {
        return itemTotalAmountExTax;
    }

    public void setItemTotalAmountExTax(double itemTotalAmountExTax) {
        this.itemTotalAmountExTax = itemTotalAmountExTax;
    }

    public Boolean getDiscountInPercent() {
        return isDiscountInPercent;
    }

    public void setDiscountInPercent(Boolean discountInPercent) {
        isDiscountInPercent = discountInPercent;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public long getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(long itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public double getItemTotalAmount() {
        return itemTotalAmount;
    }

    public void setItemTotalAmount(double itemTotalAmount) {
        this.itemTotalAmount = itemTotalAmount;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

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

    public Boolean getIsDiscountInPercent() {
        return isDiscountInPercent;
    }

    public void setIsDiscountInPercent(Boolean isDiscountInPercent) {
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

    public Object getBrandName() {
        return brandName;
    }

    public void setBrandName(Object brandName) {
        this.brandName = brandName;
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

    public Object getBrandId() {
        return brandId;
    }

    public void setBrandId(Object brandId) {
        this.brandId = brandId;
    }

    public Object getInventoryMovementId() {
        return inventoryMovementId;
    }

    public void setInventoryMovementId(Object inventoryMovementId) {
        this.inventoryMovementId = inventoryMovementId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getUnitOfMeasurementId() {
        return unitOfMeasurementId;
    }

    public void setUnitOfMeasurementId(Integer unitOfMeasurementId) {
        this.unitOfMeasurementId = unitOfMeasurementId;
    }

    @Override
    public String toString() {
        return itemName ;
    }
}
