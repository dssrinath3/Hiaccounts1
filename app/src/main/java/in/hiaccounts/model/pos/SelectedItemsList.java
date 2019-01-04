package in.hiaccounts.model.pos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class SelectedItemsList implements Serializable {


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
    private double discountAmt;
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
    @SerializedName("taxid")
    @Expose
    private long taxid;
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

    String taxTypeSelection;
    private BigDecimal price;
    private long itemQuantity;
    private double itemTotalAmount;
    boolean isSelect;
    boolean selectSerialItem;

    /**
     * No args constructor for use in serialization
     */
    public SelectedItemsList() {
    }

    /**
     * @param amtinclusivetax
     * @param isDiscountInPercent
     * @param itemName
     * @param amtexclusivetax
     * @param taxName
     * @param taxamt
     * @param qty
     * @param unitPrice
     * @param remainingQty
     * @param returnQty
     * @param taxpercent
     * @param discountAmt
     * @param itemId
     * @param taxid
     * @param serializableStatus
     */
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

    public double getDiscountAmt() {
        return discountAmt;
    }

    public void setDiscountAmt(double discountAmt) {
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
}
