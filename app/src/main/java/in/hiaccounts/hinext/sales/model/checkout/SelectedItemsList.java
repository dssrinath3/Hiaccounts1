
package in.hiaccounts.hinext.sales.model.checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import in.hiaccounts.hinext.sales.model.sales_notifications.MyArrayList;

public class SelectedItemsList implements Serializable{


    @SerializedName("salesDeliverOrderId")
    @Expose
    public String  salesDeliverOrderId;
    @SerializedName("salesDeliverOrderDetailsId")
    @Expose
    public String  salesDeliverOrderDetailsId;
    @SerializedName("outputTaxId")
    @Expose
    public Object outputTaxId;

    @SerializedName("salesPrice")
    @Expose
    public double salesPrice;

    @SerializedName("purchasePrice")
    @Expose
    public double purchasePrice;

    @SerializedName("itemTypeName")
    @Expose
    public String itemTypeName;

    @SerializedName("itemCategoryName")
    @Expose
    public String itemCategoryName;
    @SerializedName("brandName")
    @Expose
    public Object brandName;

    @SerializedName("serializableItemId")
    @Expose
    public Object serializableItemId;

    @SerializedName("certificateno")
    @Expose
    public Object certificateno;
    @SerializedName("countType")
    @Expose
    public Object countType;
    @SerializedName("itemCategoryId")
    @Expose
    public Object itemCategoryId;
    @SerializedName("itemTypeId")
    @Expose
    public Object itemTypeId;
    @SerializedName("mscid")
    @Expose
    public Object mscid;
    @SerializedName("inputTaxId")
    @Expose
    public Object inputTaxId;
    @SerializedName("brandId")
    @Expose
    public Object brandId;
    @SerializedName("inventoryMovementId")
    @Expose
    public Object inventoryMovementId;
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

    @SerializedName("fileName")
    @Expose
    public Object fileName;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("serviceDescription")
    @Expose
    public String serviceDescription;

    @SerializedName("delStatus")
    @Expose
    public String delStatus;

    @SerializedName("unitOfMeasurementId")
    @Expose
    public Object unitOfMeasurementId;
    @SerializedName("itemDesc")
    @Expose
    public String itemDesc;

    @SerializedName("salesOrderDetailsId")
    @Expose
    private String salesOrderDetailsId;
    @SerializedName("salesOrderId")
    @Expose
    private String salesOrderId;
    @SerializedName("salesDeliveryOrderNO")
    @Expose
    private String salesDeliveryOrderNO;

    @SerializedName("itemCode")
    @Expose
    private String itemCode;
    @SerializedName("itemId")
    @Expose
    private long itemId;
    @SerializedName("itemName")
    @Expose
    private String itemName;
    @SerializedName("serializableStatus")
    @Expose
    private String serializableStatus;
    @SerializedName("unitPrice")
    @Expose
    private double unitPrice;
    @SerializedName("unitPriceIn")
    @Expose
    private double unitPriceIn;
    @SerializedName("discountAmt")
    @Expose
    private double discountAmt;

    @SerializedName("inclusiveJSON")
    @Expose
    private String inclusiveJSON;
    @SerializedName("isDiscountInPercent")
    @Expose
    private boolean isDiscountInPercent;
    @SerializedName("cess")
    @Expose
    private double cess;
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
    @SerializedName("taxid")
    @Expose
    private long taxid;
    @SerializedName("uom")
    @Expose
    public Long uom;
    @SerializedName("convertedQuantity")
    @Expose
    public Long convertedQuantity;
    @SerializedName("uomConvertorList")
    @Expose
    public List<UomConvertorList> uomConvertorList = null;
    @SerializedName("taxpercent")
    @Expose
    private String taxpercent;
    @SerializedName("taxName")
    @Expose
    private String taxName;
    @SerializedName("taxamt")
    @Expose
    private double taxamt;
    @SerializedName("igTax")
    @Expose
    private double igTax;
    @SerializedName("amtinclusivetax")
    @Expose
    private double amtinclusivetax;
    @SerializedName("taxAmountSplit")
    @Expose
    private String taxAmountSplit;

    @SerializedName("itemDescription")
    @Expose
    private String itemDescription;
    @SerializedName("hsnCode")
    @Expose
    private String hsnCode;
    @SerializedName("uomName")
    @Expose
    private String uomName;

    @SerializedName("cessTaxAmt")
    @Expose
    private double cessTaxAmt;

    @SerializedName("stock")
    @Expose
    private long stock;
    @SerializedName("taxPercentageSplit")
    @Expose
    private double taxPercentageSplit;

    @SerializedName("discountAmountRpercent")
    @Expose
    private double discountAmountRpercent;
    @SerializedName("salesQuotationId")
    @Expose
    private Long salesQuotationId;
    @SerializedName("salesQuotationDetailsId")
    @Expose
    private Long salesQuotationDetailsId;
    @SerializedName("proFormaSalesInvoiceId")
    @Expose
    private Long proFormaSalesInvoiceId;
    @SerializedName("proFormaSalesInvoiceDetailsId")
    @Expose
    private Long proFormaSalesInvoiceDetailsId;
    @SerializedName("myArrayList")
    @Expose
    public List<MyArrayList> myArrayList = null;
    @SerializedName("discountConfigAmt")
    @Expose
    public Double discountConfigAmt;

    String taxTypeSelection;
    private BigDecimal price;
    private long itemQuantity;
    private double itemTotalAmount;
    boolean isSelect;
    boolean selectSerialItem;

    public Long getProFormaSalesInvoiceId() {
        return proFormaSalesInvoiceId;
    }

    public void setProFormaSalesInvoiceId(Long proFormaSalesInvoiceId) {
        this.proFormaSalesInvoiceId = proFormaSalesInvoiceId;
    }

    public Long getProFormaSalesInvoiceDetailsId() {
        return proFormaSalesInvoiceDetailsId;
    }

    public void setProFormaSalesInvoiceDetailsId(Long proFormaSalesInvoiceDetailsId) {
        this.proFormaSalesInvoiceDetailsId = proFormaSalesInvoiceDetailsId;
    }

    public Double getDiscountConfigAmt() {
        return discountConfigAmt;
    }

    public void setDiscountConfigAmt(Double discountConfigAmt) {
        this.discountConfigAmt = discountConfigAmt;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public List<MyArrayList> getMyArrayList() {
        return myArrayList;
    }

    public void setMyArrayList(List<MyArrayList> myArrayList) {
        this.myArrayList = myArrayList;
    }

    public String getSalesDeliverOrderId() {
        return salesDeliverOrderId;
    }

    public void setSalesDeliverOrderId(String salesDeliverOrderId) {
        this.salesDeliverOrderId = salesDeliverOrderId;
    }

    public String getSalesDeliverOrderDetailsId() {
        return salesDeliverOrderDetailsId;
    }

    public void setSalesDeliverOrderDetailsId(String salesDeliverOrderDetailsId) {
        this.salesDeliverOrderDetailsId = salesDeliverOrderDetailsId;
    }

    public String getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(String delStatus) {
        this.delStatus = delStatus;
    }

    public Long getSalesQuotationId() {
        return salesQuotationId;
    }

    public void setSalesQuotationId(Long salesQuotationId) {
        this.salesQuotationId = salesQuotationId;
    }

    public Long getSalesQuotationDetailsId() {
        return salesQuotationDetailsId;
    }

    public void setSalesQuotationDetailsId(Long salesQuotationDetailsId) {
        this.salesQuotationDetailsId = salesQuotationDetailsId;
    }

    public double getTaxPercentageSplit() {
        return taxPercentageSplit;
    }

    public void setTaxPercentageSplit(double taxPercentageSplit) {
        this.taxPercentageSplit = taxPercentageSplit;
    }

    public String getInclusiveJSON() {
        return inclusiveJSON;
    }

    public void setInclusiveJSON(String inclusiveJSON) {
        this.inclusiveJSON = inclusiveJSON;
    }

    public Long getUom() {
        return uom;
    }

    public void setUom(Long uom) {
        this.uom = uom;
    }

    public Long getConvertedQuantity() {
        return convertedQuantity;
    }

    public void setConvertedQuantity(Long convertedQuantity) {
        this.convertedQuantity = convertedQuantity;
    }

    public List<UomConvertorList> getUomConvertorList() {
        return uomConvertorList;
    }

    public void setUomConvertorList(List<UomConvertorList> uomConvertorList) {
        this.uomConvertorList = uomConvertorList;
    }

    public double getDiscountAmountRpercent() {
        return discountAmountRpercent;
    }

    public void setDiscountAmountRpercent(double discountAmountRpercent) {
        this.discountAmountRpercent = discountAmountRpercent;
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

    public String getSalesDeliveryOrderNO() {
        return salesDeliveryOrderNO;
    }

    public void setSalesDeliveryOrderNO(String salesDeliveryOrderNO) {
        this.salesDeliveryOrderNO = salesDeliveryOrderNO;
    }

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }

    public String getItemCode() {
        return itemCode;
    }

    public long getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getSerializableStatus() {
        return serializableStatus;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getDiscountAmt() {
        return discountAmt;
    }

    public boolean isDiscountInPercent() {
        return isDiscountInPercent;
    }

    public double getCess() {
        return cess;
    }

    public long getQty() {
        return qty;
    }

    public long getReturnQty() {
        return returnQty;
    }

    public long getRemainingQty() {
        return remainingQty;
    }

    public double getAmtexclusivetax() {
        return amtexclusivetax;
    }

    public long getTaxid() {
        return taxid;
    }

    public String getTaxpercent() {
        return taxpercent;
    }

    public String getTaxName() {
        return taxName;
    }

    public double getTaxamt() {
        return taxamt;
    }

    public double getIgTax() {
        return igTax;
    }

    public double getAmtinclusivetax() {
        return amtinclusivetax;
    }

    public String getTaxAmountSplit() {
        return taxAmountSplit;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public String getHsnCode() {
        return hsnCode;
    }

    public String getUomName() {
        return uomName;
    }

    public double getCessTaxAmt() {
        return cessTaxAmt;
    }

    public String getTaxTypeSelection() {
        return taxTypeSelection;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public long getItemQuantity() {
        return itemQuantity;
    }

    public double getItemTotalAmount() {
        return itemTotalAmount;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public boolean isSelectSerialItem() {
        return selectSerialItem;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setSerializableStatus(String serializableStatus) {
        this.serializableStatus = serializableStatus;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setDiscountAmt(double discountAmt) {
        this.discountAmt = discountAmt;
    }

    public void setDiscountInPercent(boolean discountInPercent) {
        isDiscountInPercent = discountInPercent;
    }

    public void setCess(double cess) {
        this.cess = cess;
    }

    public void setQty(long qty) {
        this.qty = qty;
    }

    public void setReturnQty(long returnQty) {
        this.returnQty = returnQty;
    }

    public void setRemainingQty(long remainingQty) {
        this.remainingQty = remainingQty;
    }

    public void setAmtexclusivetax(double amtexclusivetax) {
        this.amtexclusivetax = amtexclusivetax;
    }

    public void setTaxid(long taxid) {
        this.taxid = taxid;
    }

    public void setTaxpercent(String taxpercent) {
        this.taxpercent = taxpercent;
    }

    public void setTaxName(String taxName) {
        this.taxName = taxName;
    }

    public void setTaxamt(double taxamt) {
        this.taxamt = taxamt;
    }

    public void setIgTax(double igTax) {
        this.igTax = igTax;
    }

    public void setAmtinclusivetax(double amtinclusivetax) {
        this.amtinclusivetax = amtinclusivetax;
    }

    public void setTaxAmountSplit(String taxAmountSplit) {
        this.taxAmountSplit = taxAmountSplit;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public void setHsnCode(String hsnCode) {
        this.hsnCode = hsnCode;
    }

    public void setUomName(String uomName) {
        this.uomName = uomName;
    }

    public void setCessTaxAmt(double cessTaxAmt) {
        this.cessTaxAmt = cessTaxAmt;
    }

    public void setTaxTypeSelection(String taxTypeSelection) {
        this.taxTypeSelection = taxTypeSelection;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setItemQuantity(long itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public void setItemTotalAmount(double itemTotalAmount) {
        this.itemTotalAmount = itemTotalAmount;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public void setSelectSerialItem(boolean selectSerialItem) {
        this.selectSerialItem = selectSerialItem;
    }

    public Object getOutputTaxId() {
        return outputTaxId;
    }

    public void setOutputTaxId(Object outputTaxId) {
        this.outputTaxId = outputTaxId;
    }

    public double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(double salesPrice) {
        this.salesPrice = salesPrice;
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

    public Object getSerializableItemId() {
        return serializableItemId;
    }

    public void setSerializableItemId(Object serializableItemId) {
        this.serializableItemId = serializableItemId;
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

    public Object getItemCategoryId() {
        return itemCategoryId;
    }

    public void setItemCategoryId(Object itemCategoryId) {
        this.itemCategoryId = itemCategoryId;
    }

    public Object getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(Object itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    public Object getMscid() {
        return mscid;
    }

    public void setMscid(Object mscid) {
        this.mscid = mscid;
    }

    public Object getInputTaxId() {
        return inputTaxId;
    }

    public void setInputTaxId(Object inputTaxId) {
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

    public Object getFileName() {
        return fileName;
    }

    public void setFileName(Object fileName) {
        this.fileName = fileName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getUnitOfMeasurementId() {
        return unitOfMeasurementId;
    }

    public void setUnitOfMeasurementId(Object unitOfMeasurementId) {
        this.unitOfMeasurementId = unitOfMeasurementId;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public double getUnitPriceIn() {
        return unitPriceIn;
    }

    public void setUnitPriceIn(double unitPriceIn) {
        this.unitPriceIn = unitPriceIn;
    }
}
