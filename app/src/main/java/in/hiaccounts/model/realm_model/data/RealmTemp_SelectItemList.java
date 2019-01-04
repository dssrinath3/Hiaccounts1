package in.hiaccounts.model.realm_model.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RealmTemp_SelectItemList extends RealmObject {
    @PrimaryKey
    private int id;
    String taxTypeSelection;
    boolean isSelect;
    boolean selectSerialItem;
    @SerializedName("itemId")
    @Expose
    private long itemId;
    @SerializedName("itemCode")
    @Expose
    private String itemCode;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("fileName")
    @Expose
    private String fileName;
    @SerializedName("salesPrice")
    @Expose
    private double salesPrice;
    @SerializedName("serializableStatus")
    @Expose
    private String serializableStatus;
    @SerializedName("stock")
    @Expose
    private long stock;
    @SerializedName("taxId")
    @Expose
    private long taxId;

    @SerializedName("itemCategoryName")
    @Expose
    private String itemCategoryName;
    @SerializedName("serializableItemId")
    @Expose
    private long serializableItemId;

    @SerializedName("itemDesc")
    @Expose
    private String itemDesc;
    @SerializedName("isDiscountInPercent")
    @Expose
    private boolean isDiscountInPercent;
    @SerializedName("discountAmountRpercent")
    @Expose
    private double discountAmountRpercent;
    @SerializedName("itemName")
    @Expose
    private String itemName;
    @SerializedName("unitPrice")
    @Expose
    private double unitPrice;
    @SerializedName("unitPriceIn")
    @Expose
    private double unitPriceIn;


    @SerializedName("discountAmt")
    @Expose
    private Double discountAmt;
    @SerializedName("qty")
    @Expose
    private long qty;
    @SerializedName("returnQty")
    @Expose
    private long returnQty;
    @SerializedName("remainingQty")
    @Expose
    private long remainingQty;
    @SerializedName("amtexclusivetax")
    @Expose
    private double amtexclusivetax;
    @SerializedName("amtinclusivetax")
    @Expose
    private double amtinclusivetax;
    @SerializedName("taxpercent")
    @Expose
    private String taxpercent;
    @SerializedName("taxName")
    @Expose
    private String taxName;
    @SerializedName("taxamt")
    @Expose
    private double taxamt;
    @SerializedName("serializableNumbers")
    @Expose
    private String serializableNumbers;
    @SerializedName("purchasePrice")
    @Expose
    private double purchasePrice;
    @SerializedName("itemTypeName")
    @Expose
    private String itemTypeName;
    @SerializedName("certificateno")
    @Expose
    private String certificateno;
    @SerializedName("taxid")
    @Expose
    private long taxid;
    @SerializedName("inclusiveJSON")
    @Expose
    private String inclusiveJSON;

    @SerializedName("itemCountTypeDTO")
    @Expose
    private Long itemCountTypeDTO;
    @SerializedName("cess")
    @Expose
    private String cess;
    @SerializedName("igTax")
    @Expose
    private String cgstsgstsplitvalues;
    @SerializedName("taxAmountSplit")
    @Expose
    private String taxPercentageSplit;
    @SerializedName("cessTaxAmt")
    @Expose
    private String cessTaxAmt;
    @SerializedName("uomName")
    @Expose
    private String uomName;
    @SerializedName("hsnCode")
    @Expose
    private String hsnCode;
    private String price;
    private long itemQuantity;
    private double itemTotalAmount;
    @SerializedName("itemCategoryId")
    @Expose
    private long itemCategoryId;
    @SerializedName("itemTypeId")
    @Expose
    private long itemTypeId;

    @SerializedName("unitOfMeasurementId")
    @Expose
    private long unitOfMeasurementId;
    @SerializedName("mscid")
    @Expose
    private long mscid;
    @SerializedName("brandId")
    @Expose
    private long brandId;
    @SerializedName("inventoryMovementId")
    @Expose
    private long inventoryMovementId;

    @SerializedName("itemBrandID")
    @Expose
    private long itemBrandID;

    @SerializedName("itemIPTaxID")
    @Expose
    private long itemIPTaxID;
    @SerializedName("itemOPTaxID")
    @Expose
    private long itemOPTaxID;


    @SerializedName("inputTaxId")
    @Expose
    private long inputTaxId;

    @SerializedName("outputTaxId")
    @Expose
    private long ouputTaxId;

    @SerializedName("imageLocation")
    @Expose
    private String imageLocation;
    @SerializedName("itemImage")
    @Expose
    private String itemImage;
    @SerializedName("attributeJSON")
    @Expose
    private String attributeJSON;
    @SerializedName("discountInPercent")
    @Expose
    private Boolean discountInPercent;

    @SerializedName("productionItem")
    @Expose
    private String productionItem;

    @SerializedName("itemStatus")
    @Expose
    private String itemStatus;
    @SerializedName("salesOrderDetailsId")
    @Expose
    private String salesOrderDetailsId;
    @SerializedName("salesOrderId")
    @Expose
    private String salesOrderId;
    @SerializedName("taxAmountSplit")
    @Expose
    private String taxAmountSplit;
    @SerializedName("uom")
    @Expose
    private String uom;
    @SerializedName("itemDescription")
    @Expose
    private String itemDescription;
    @SerializedName("salesQuotationId")
    @Expose
    private long salesQuotationId;
    @SerializedName("salesQuotationDetailsId")
    @Expose
    private long salesQuotationDetailsId;
    @SerializedName("salesDeliverOrderDetailsId")
    @Expose
    private long salesDeliverOrderDetailsId;
    @SerializedName("salesDeliverOrderId")
    @Expose
    private long salesDeliverOrderId;
    @SerializedName("customerId")
    @Expose
    private Long customerId;
    private double itemTotalAmountInTax;
    private double itemTotalAmountExTax;
    private double gstTaxPercantage;
    private double gstTaxAmt;
    private String tableId;
    private String tableName;
    private boolean kotSelect;
    private String floorId;
    private String type;
    private String waiterName;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaxTypeSelection() {
        return taxTypeSelection;
    }

    public void setTaxTypeSelection(String taxTypeSelection) {
        this.taxTypeSelection = taxTypeSelection;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public boolean isSelectSerialItem() {
        return selectSerialItem;
    }

    public void setSelectSerialItem(boolean selectSerialItem) {
        this.selectSerialItem = selectSerialItem;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(double salesPrice) {
        this.salesPrice = salesPrice;
    }

    public String getSerializableStatus() {
        return serializableStatus;
    }

    public void setSerializableStatus(String serializableStatus) {
        this.serializableStatus = serializableStatus;
    }

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }

    public long getTaxId() {
        return taxId;
    }

    public void setTaxId(long taxId) {
        this.taxId = taxId;
    }

    public String getItemCategoryName() {
        return itemCategoryName;
    }

    public void setItemCategoryName(String itemCategoryName) {
        this.itemCategoryName = itemCategoryName;
    }

    public long getSerializableItemId() {
        return serializableItemId;
    }

    public void setSerializableItemId(long serializableItemId) {
        this.serializableItemId = serializableItemId;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public boolean isDiscountInPercent() {
        return isDiscountInPercent;
    }

    public void setDiscountInPercent(boolean discountInPercent) {
        isDiscountInPercent = discountInPercent;
    }

    public double getDiscountAmountRpercent() {
        return discountAmountRpercent;
    }

    public void setDiscountAmountRpercent(double discountAmountRpercent) {
        this.discountAmountRpercent = discountAmountRpercent;
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

    public double getUnitPriceIn() {
        return unitPriceIn;
    }

    public void setUnitPriceIn(double unitPriceIn) {
        this.unitPriceIn = unitPriceIn;
    }

    public Double getDiscountAmt() {
        return discountAmt;
    }

    public void setDiscountAmt(Double discountAmt) {
        this.discountAmt = discountAmt;
    }

    public long getQty() {
        return qty;
    }

    public void setQty(long qty) {
        this.qty = qty;
    }

    public long getReturnQty() {
        return returnQty;
    }

    public void setReturnQty(long returnQty) {
        this.returnQty = returnQty;
    }

    public long getRemainingQty() {
        return remainingQty;
    }

    public void setRemainingQty(long remainingQty) {
        this.remainingQty = remainingQty;
    }

    public double getAmtexclusivetax() {
        return amtexclusivetax;
    }

    public void setAmtexclusivetax(double amtexclusivetax) {
        this.amtexclusivetax = amtexclusivetax;
    }

    public double getAmtinclusivetax() {
        return amtinclusivetax;
    }

    public void setAmtinclusivetax(double amtinclusivetax) {
        this.amtinclusivetax = amtinclusivetax;
    }

    public String getTaxpercent() {
        return taxpercent;
    }

    public void setTaxpercent(String taxpercent) {
        this.taxpercent = taxpercent;
    }

    public String getTaxName() {
        return taxName;
    }

    public void setTaxName(String taxName) {
        this.taxName = taxName;
    }

    public double getTaxamt() {
        return taxamt;
    }

    public void setTaxamt(double taxamt) {
        this.taxamt = taxamt;
    }

    public String getSerializableNumbers() {
        return serializableNumbers;
    }

    public void setSerializableNumbers(String serializableNumbers) {
        this.serializableNumbers = serializableNumbers;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getItemTypeName() {
        return itemTypeName;
    }

    public void setItemTypeName(String itemTypeName) {
        this.itemTypeName = itemTypeName;
    }

    public String getCertificateno() {
        return certificateno;
    }

    public void setCertificateno(String certificateno) {
        this.certificateno = certificateno;
    }

    public long getTaxid() {
        return taxid;
    }

    public void setTaxid(long taxid) {
        this.taxid = taxid;
    }

    public String getInclusiveJSON() {
        return inclusiveJSON;
    }

    public void setInclusiveJSON(String inclusiveJSON) {
        this.inclusiveJSON = inclusiveJSON;
    }

    public Long getItemCountTypeDTO() {
        return itemCountTypeDTO;
    }

    public void setItemCountTypeDTO(Long itemCountTypeDTO) {
        this.itemCountTypeDTO = itemCountTypeDTO;
    }

    public String getCess() {
        return cess;
    }

    public void setCess(String cess) {
        this.cess = cess;
    }

    public String getCgstsgstsplitvalues() {
        return cgstsgstsplitvalues;
    }

    public void setCgstsgstsplitvalues(String cgstsgstsplitvalues) {
        this.cgstsgstsplitvalues = cgstsgstsplitvalues;
    }

    public String getTaxPercentageSplit() {
        return taxPercentageSplit;
    }

    public void setTaxPercentageSplit(String taxPercentageSplit) {
        this.taxPercentageSplit = taxPercentageSplit;
    }

    public String getCessTaxAmt() {
        return cessTaxAmt;
    }

    public void setCessTaxAmt(String cessTaxAmt) {
        this.cessTaxAmt = cessTaxAmt;
    }

    public String getUomName() {
        return uomName;
    }

    public void setUomName(String uomName) {
        this.uomName = uomName;
    }

    public String getHsnCode() {
        return hsnCode;
    }

    public void setHsnCode(String hsnCode) {
        this.hsnCode = hsnCode;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
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

    public long getItemCategoryId() {
        return itemCategoryId;
    }

    public void setItemCategoryId(long itemCategoryId) {
        this.itemCategoryId = itemCategoryId;
    }

    public long getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(long itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    public long getUnitOfMeasurementId() {
        return unitOfMeasurementId;
    }

    public void setUnitOfMeasurementId(long unitOfMeasurementId) {
        this.unitOfMeasurementId = unitOfMeasurementId;
    }

    public long getMscid() {
        return mscid;
    }

    public void setMscid(long mscid) {
        this.mscid = mscid;
    }

    public long getBrandId() {
        return brandId;
    }

    public void setBrandId(long brandId) {
        this.brandId = brandId;
    }

    public long getInventoryMovementId() {
        return inventoryMovementId;
    }

    public void setInventoryMovementId(long inventoryMovementId) {
        this.inventoryMovementId = inventoryMovementId;
    }

    public long getItemBrandID() {
        return itemBrandID;
    }

    public void setItemBrandID(long itemBrandID) {
        this.itemBrandID = itemBrandID;
    }

    public long getItemIPTaxID() {
        return itemIPTaxID;
    }

    public void setItemIPTaxID(long itemIPTaxID) {
        this.itemIPTaxID = itemIPTaxID;
    }

    public long getItemOPTaxID() {
        return itemOPTaxID;
    }

    public void setItemOPTaxID(long itemOPTaxID) {
        this.itemOPTaxID = itemOPTaxID;
    }

    public long getInputTaxId() {
        return inputTaxId;
    }

    public void setInputTaxId(long inputTaxId) {
        this.inputTaxId = inputTaxId;
    }

    public long getOuputTaxId() {
        return ouputTaxId;
    }

    public void setOuputTaxId(long ouputTaxId) {
        this.ouputTaxId = ouputTaxId;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public String getAttributeJSON() {
        return attributeJSON;
    }

    public void setAttributeJSON(String attributeJSON) {
        this.attributeJSON = attributeJSON;
    }

    public Boolean getDiscountInPercent() {
        return discountInPercent;
    }

    public void setDiscountInPercent(Boolean discountInPercent) {
        this.discountInPercent = discountInPercent;
    }

    public String getProductionItem() {
        return productionItem;
    }

    public void setProductionItem(String productionItem) {
        this.productionItem = productionItem;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

    public String getSalesOrderDetailsId() {
        return salesOrderDetailsId;
    }

    public void setSalesOrderDetailsId(String salesOrderDetailsId) {
        this.salesOrderDetailsId = salesOrderDetailsId;
    }

    public String getSalesOrderId() {
        return salesOrderId;
    }

    public void setSalesOrderId(String salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public String getTaxAmountSplit() {
        return taxAmountSplit;
    }

    public void setTaxAmountSplit(String taxAmountSplit) {
        this.taxAmountSplit = taxAmountSplit;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public long getSalesQuotationId() {
        return salesQuotationId;
    }

    public void setSalesQuotationId(long salesQuotationId) {
        this.salesQuotationId = salesQuotationId;
    }

    public long getSalesQuotationDetailsId() {
        return salesQuotationDetailsId;
    }

    public void setSalesQuotationDetailsId(long salesQuotationDetailsId) {
        this.salesQuotationDetailsId = salesQuotationDetailsId;
    }

    public long getSalesDeliverOrderDetailsId() {
        return salesDeliverOrderDetailsId;
    }

    public void setSalesDeliverOrderDetailsId(long salesDeliverOrderDetailsId) {
        this.salesDeliverOrderDetailsId = salesDeliverOrderDetailsId;
    }

    public long getSalesDeliverOrderId() {
        return salesDeliverOrderId;
    }

    public void setSalesDeliverOrderId(long salesDeliverOrderId) {
        this.salesDeliverOrderId = salesDeliverOrderId;
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

    public boolean isKotSelect() {
        return kotSelect;
    }

    public void setKotSelect(boolean kotSelect) {
        this.kotSelect = kotSelect;
    }

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }
}
