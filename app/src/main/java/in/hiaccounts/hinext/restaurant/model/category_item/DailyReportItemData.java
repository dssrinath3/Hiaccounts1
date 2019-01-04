package in.hiaccounts.hinext.restaurant.model.category_item;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigDecimal;

import io.realm.annotations.Ignore;

public class DailyReportItemData implements Serializable {
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
    @SerializedName("stock")
    @Expose
    private long stock;
    @SerializedName("discountAmountRpercent")
    @Expose
    private double discountAmountRpercent;
    @SerializedName("isDiscountInPercent")
    @Expose
    private Boolean isDiscountInPercent;
    @SerializedName("salesPrice")
    @Expose
    private double salesPrice;
    @SerializedName("purchasePrice")
    @Expose
    private double purchasePrice;
    @SerializedName("itemTypeName")
    @Expose
    private String itemTypeName;
    @SerializedName("itemCategoryName")
    @Expose
    private String itemCategoryName;
    @SerializedName("brandName")
    @Expose
    private String brandName;
    @SerializedName("unitPrice")
    @Expose
    private double unitPrice;
    @SerializedName("unitPriceIn")
    @Expose
    private double unitPriceIn;
    @SerializedName("serializableStatus")
    @Expose
    private String serializableStatus;
    @SerializedName("countType")
    @Expose
    private String countType;
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
    @SerializedName("cess")
    @Expose
    private double cess;
    @SerializedName("hsnCode")
    @Expose
    private String hsnCode;
    @SerializedName("uomName")
    @Expose
    private String uomName;
    @SerializedName("inclusiveJSON")
    @Expose
    private String inclusiveJSON;
    @SerializedName("itemDesc")
    @Expose
    private String itemDesc;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("unitOfMeasurementId")
    @Expose
    private Integer unitOfMeasurementId;
    @Ignore
    private BigDecimal price;
    private Double itemQuantity;
    private double itemTotalAmountInTax;
    private double itemTotalAmountExTax;
    private double itemTotalAmount;
    private double gstTaxPercantage;
    private double gstTaxAmt;
    @SerializedName("QtySold")
    @Expose
    public Double qtySold;
    @SerializedName("total")
    @Expose
    public String total;
    @SerializedName("totalAmtReceived")
    @Expose
    public Double totalAmtReceived;

    public Double getQtySold() {
        return qtySold;
    }

    public void setQtySold(Double qtySold) {
        this.qtySold = qtySold;
    }

    public Double getTotalAmtReceived() {
        return totalAmtReceived;
    }

    public void setTotalAmtReceived(Double totalAmtReceived) {
        this.totalAmtReceived = totalAmtReceived;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
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

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }

    public double getDiscountAmountRpercent() {
        return discountAmountRpercent;
    }

    public void setDiscountAmountRpercent(double discountAmountRpercent) {
        this.discountAmountRpercent = discountAmountRpercent;
    }

    public Boolean getDiscountInPercent() {
        return isDiscountInPercent;
    }

    public void setDiscountInPercent(Boolean discountInPercent) {
        isDiscountInPercent = discountInPercent;
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

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
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

    public String getSerializableStatus() {
        return serializableStatus;
    }

    public void setSerializableStatus(String serializableStatus) {
        this.serializableStatus = serializableStatus;
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

    public double getCess() {
        return cess;
    }

    public void setCess(double cess) {
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Double getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(Double itemQuantity) {
        this.itemQuantity = itemQuantity;
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

    public double getItemTotalAmount() {
        return itemTotalAmount;
    }

    public void setItemTotalAmount(double itemTotalAmount) {
        this.itemTotalAmount = itemTotalAmount;
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
}
