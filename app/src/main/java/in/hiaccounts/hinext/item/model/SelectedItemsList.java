package in.hiaccounts.hinext.item.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import in.hiaccounts.hinext.sales.model.checkout.UomConvertorList;
import in.hiaccounts.model.pos.SerializableItemsDTOList;

public class SelectedItemsList implements Serializable {
    String taxTypeSelection;
    boolean isSelect;
    boolean selectSerialItem;
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
    @SerializedName("itemId")
    @Expose
    private long itemId;
    @SerializedName("itemCategoryName")
    @Expose
    private String itemCategoryName;
    @SerializedName("serializableItemId")
    @Expose
    private long serializableItemId;
    @SerializedName("itemCode")
    @Expose
    private String itemCode;
    @SerializedName("itemDesc")
    @Expose
    private String itemDesc;
    @SerializedName("isDiscountInPercent")
    @Expose
    private boolean isDiscountInPercent;
    @SerializedName("serializableItemsDTOList")
    @Expose
    private List<SerializableItemsDTOList> serializableItemsDTOList;
    @SerializedName("discountAmountRpercent")
    @Expose
    private double discountAmountRpercent;
    @SerializedName("itemName")
    @Expose
    private String itemName;
    @SerializedName("unitPrice")
    @Expose
    private double unitPrice;
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
    @SerializedName("taxPer")
    @Expose
    private long taxPer;
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
    /*
        List<ItemCategoryDTO> itemCategoryDTOList;
        List<ItemTypeDTO> itemTypeDTOList;
        List<ItemUOMTypeDTO> itemUOMTypeDTOList;
        List<ItemMSICDTO> itemMSICDTOList;
        List<ItemBrandDTO> itemBrandDTOList;
        List<ItemCountTypeDTO> itemCountTypeDTOList;
        List<ItemIPTaxDTO> itemIPTaxDTOList;
        List<ItemOPTaxDTO> itemOPTaxDTOList;
    */
    @SerializedName("itemCategoryDTOList")
    @Expose
    private List<ItemCategoryDTOList> itemCategoryDTOList = null;
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
    private List<UomConvertorList> uomConvertorList = null;
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
    private BigDecimal price;
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
    private Long inputTaxId;

    @SerializedName("outputTaxId")
    @Expose
    private Long ouputTaxId;

    @SerializedName("imageLocation")
    @Expose
    private String imageLocation;
    @SerializedName("itemImage")
    @Expose
    private String itemImage;
    @SerializedName("attributeJSON")
    @Expose
    private String attributeJSON;








 /*
    public SelectedItemsList(long itemId, String itemName, double unitPrice, double discountAmt, boolean isDiscountInPercent, long qty, long returnQty, long remainingQty, double amtexclusivetax, long taxid, String taxpercent, String taxName, double taxamt, double amtinclusivetax, String serializableStatus, String itemCode, boolean isSelect, long itemQuantity, String serializableNumbers, double salesPrice) {
        super();
        this.itemId = itemId;
        this.itemName = itemName;
        this.unitPrice = unitPrice;
        this.discountAmt = discountAmt;
        this.isDiscountInPercent = isDiscountInPercent;
        this.qty = qty;
        this.returnQty = returnQty;
        this.remainingQty = remainingQty;
        this.amtexclusivetax = amtexclusivetax;
        this.taxid = taxid;
        this.taxpercent = taxpercent;
        this.taxName = taxName;
        this.taxamt = taxamt;
        this.amtinclusivetax = amtinclusivetax;
        this.serializableStatus = serializableStatus;
        this.itemCode = itemCode;
        this.isSelect = isSelect;
        this.itemQuantity = itemQuantity;
        this.serializableNumbers = serializableNumbers;
        this.salesPrice = salesPrice;

    }

    public SelectedItemsList(long itemId, String itemName, double unitPrice, double discountAmt, boolean isDiscountInPercent, long qty, long returnQty, long remainingQty, double amtexclusivetax, long taxid, String taxpercent, String taxName, double taxamt, double amtinclusivetax, String serializableStatus, String itemCode, boolean isSelect, long itemQuantity, String serializableNumbers, List<SerializableItemsDTOList> serializableItemsDTOList, double salesPrice) {
        super();
        this.itemId = itemId;
        this.itemName = itemName;
        this.unitPrice = unitPrice;
        this.discountAmt = discountAmt;
        this.isDiscountInPercent = isDiscountInPercent;
        this.qty = qty;
        this.returnQty = returnQty;
        this.remainingQty = remainingQty;
        this.amtexclusivetax = amtexclusivetax;
        this.taxid = taxid;
        this.taxpercent = taxpercent;
        this.taxName = taxName;
        this.taxamt = taxamt;
        this.amtinclusivetax = amtinclusivetax;
        this.serializableStatus = serializableStatus;
        this.itemCode = itemCode;
        this.isSelect = isSelect;
        this.itemQuantity = itemQuantity;
        this.serializableNumbers = serializableNumbers;
        this.serializableItemsDTOList = serializableItemsDTOList;
        this.salesPrice = salesPrice;

    }*//*

*/

    @SerializedName("attributeConfiguratorDTOList")
    @Expose
    private List<AttributeConfiguratorDTOList> attributeConfiguratorDTOList = null;
    @SerializedName("discountInPercent")
    @Expose
    private Boolean discountInPercent;

    @SerializedName("productionItem")
    @Expose
    private String productionItem;

    @SerializedName("itemStatus")
    @Expose
    private String itemStatus;
    @SerializedName("serviceDescription")
    @Expose
    private String serviceDescription;
    @SerializedName("cartValue")
    @Expose
    private String cartValue;

    public String getCartValue() {
        return cartValue;
    }

    public void setCartValue(String cartValue) {
        this.cartValue = cartValue;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public long getTaxPer() {
        return taxPer;
    }

    public void setTaxPer(long taxPer) {
        this.taxPer = taxPer;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
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

    public String getProductionItem() {
        return productionItem;
    }

    public void setProductionItem(String productionItem) {
        this.productionItem = productionItem;
    }

    public Long getItemCountTypeDTO() {
        return itemCountTypeDTO;
    }

    public void setItemCountTypeDTO(Long itemCountTypeDTO) {
        this.itemCountTypeDTO = itemCountTypeDTO;
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

    public List<UomConvertorList> getUomConvertorList() {
        return uomConvertorList;
    }

    public void setUomConvertorList(List<UomConvertorList> uomConvertorList) {
        this.uomConvertorList = uomConvertorList;
    }

    public String getInclusiveJSON() {
        return inclusiveJSON;
    }

    public void setInclusiveJSON(String inclusiveJSON) {
        this.inclusiveJSON = inclusiveJSON;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getTaxTypeSelection() {
        return taxTypeSelection;
    }

    public void setTaxTypeSelection(String taxTypeSelection) {
        this.taxTypeSelection = taxTypeSelection;
    }

    public String getCertificateno() {
        return certificateno;
    }

    public void setCertificateno(String certificateno) {
        this.certificateno = certificateno;
    }


    public String getItemTypeName() {
        return itemTypeName;
    }

    public void setItemTypeName(String itemTypeName) {
        this.itemTypeName = itemTypeName;
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

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
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

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
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

    public List<SerializableItemsDTOList> getSerializableItemsDTOList() {
        return serializableItemsDTOList;
    }

    public void setSerializableItemsDTOList(List<SerializableItemsDTOList> serializableItemsDTOList) {
        this.serializableItemsDTOList = serializableItemsDTOList;
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

    public long getTaxid() {
        return taxid;
    }

    public void setTaxid(long taxid) {
        this.taxid = taxid;
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

    public boolean isSelectSerialItem() {
        return selectSerialItem;
    }

    public void setSelectSerialItem(boolean selectSerialItem) {
        this.selectSerialItem = selectSerialItem;
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

    public List<ItemCategoryDTOList> getItemCategoryDTOList() {
        return itemCategoryDTOList;
    }

    public void setItemCategoryDTOList(List<ItemCategoryDTOList> itemCategoryDTOList) {
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

    public Long getInputTaxId() {
        return inputTaxId;
    }

    public void setInputTaxId(Long inputTaxId) {
        this.inputTaxId = inputTaxId;
    }

    public Long getOuputTaxId() {
        return ouputTaxId;
    }

    public void setOuputTaxId(Long ouputTaxId) {
        this.ouputTaxId = ouputTaxId;
    }
}
