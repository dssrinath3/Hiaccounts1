package in.hiaccounts.hinext.reports.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IneventoryReportlist {
    @SerializedName("itemName")
    @Expose
    public String itemName;
    @SerializedName("itemCode")
    @Expose
    public String itemCode;
    @SerializedName("categoryName")
    @Expose
    public Object categoryName;
    @SerializedName("qty")
    @Expose
    public Double qty;
    @SerializedName("purchasPrice")
    @Expose
    public Double purchasPrice;
    @SerializedName("salesPricing")
    @Expose
    public Double salesPricing;
    @SerializedName("price")
    @Expose
    public Double price;
    @SerializedName("itemQuantityProduction")
    @Expose
    public Double itemQuantityProduction;
    @SerializedName("stock")
    @Expose
    public Double stock;
    @SerializedName("soordered")
    @Expose
    public Double soordered;
    @SerializedName("coordered")
    @Expose
    public Double coordered;
    @SerializedName("no")
    @Expose
    public String no;
    @SerializedName("itemAmount")
    @Expose
    public Object itemAmount;
    @SerializedName("id")
    @Expose
    public Object id;
    @SerializedName("qtyOrdered")
    @Expose
    public Double qtyOrdered;
    @SerializedName("qtyReceived")
    @Expose
    public Double qtyReceived;
    @SerializedName("batchNo")
    @Expose
    public Object batchNo;
    @SerializedName("expireDate")
    @Expose
    public String expireDate;
    @SerializedName("posting")
    @Expose
    public String posting;
    @SerializedName("itemCommisionAmount")
    @Expose
    public Double itemCommisionAmount;
    @SerializedName("itemExAmt")
    @Expose
    public Double itemExAmt;
    @SerializedName("itemTax")
    @Expose
    public Double itemTax;
    @SerializedName("itemTaxPer")
    @Expose
    public Double itemTaxPer;
    @SerializedName("itemIncAmt")
    @Expose
    public Double itemIncAmt;
    @SerializedName("employeeName")
    @Expose
    public String employeeName;
    @SerializedName("commConfig")
    @Expose
    public Object commConfig;
    @SerializedName("variance")
    @Expose
    public Object variance;
    @SerializedName("formNo")
    @Expose
    public Object formNo;
    @SerializedName("scrapItem")
    @Expose
    public Object scrapItem;
    @SerializedName("cost")
    @Expose
    public Double cost;
    @SerializedName("avgCost")
    @Expose
    public Double avgCost;
    @SerializedName("brand")
    @Expose
    public Object brand;
    @SerializedName("certificateno")
    @Expose
    public Object certificateno;
    @SerializedName("quantity")
    @Expose
    public Object quantity;
    @SerializedName("itemQty")
    @Expose
    public Object itemQty;
    @SerializedName("uom")
    @Expose
    public Object uom;
    @SerializedName("totalAmount")
    @Expose
    public Double totalAmount;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public Object getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(Object categoryName) {
        this.categoryName = categoryName;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public Double getPurchasPrice() {
        return purchasPrice;
    }

    public void setPurchasPrice(Double purchasPrice) {
        this.purchasPrice = purchasPrice;
    }

    public Double getSalesPricing() {
        return salesPricing;
    }

    public void setSalesPricing(Double salesPricing) {
        this.salesPricing = salesPricing;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getItemQuantityProduction() {
        return itemQuantityProduction;
    }

    public void setItemQuantityProduction(Double itemQuantityProduction) {
        this.itemQuantityProduction = itemQuantityProduction;
    }

    public Double getStock() {
        return stock;
    }

    public void setStock(Double stock) {
        this.stock = stock;
    }

    public Double getSoordered() {
        return soordered;
    }

    public void setSoordered(Double soordered) {
        this.soordered = soordered;
    }

    public Double getCoordered() {
        return coordered;
    }

    public void setCoordered(Double coordered) {
        this.coordered = coordered;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Object getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(Object itemAmount) {
        this.itemAmount = itemAmount;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Double getQtyOrdered() {
        return qtyOrdered;
    }

    public void setQtyOrdered(Double qtyOrdered) {
        this.qtyOrdered = qtyOrdered;
    }

    public Double getQtyReceived() {
        return qtyReceived;
    }

    public void setQtyReceived(Double qtyReceived) {
        this.qtyReceived = qtyReceived;
    }

    public Object getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(Object batchNo) {
        this.batchNo = batchNo;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getPosting() {
        return posting;
    }

    public void setPosting(String posting) {
        this.posting = posting;
    }

    public Double getItemCommisionAmount() {
        return itemCommisionAmount;
    }

    public void setItemCommisionAmount(Double itemCommisionAmount) {
        this.itemCommisionAmount = itemCommisionAmount;
    }

    public Double getItemExAmt() {
        return itemExAmt;
    }

    public void setItemExAmt(Double itemExAmt) {
        this.itemExAmt = itemExAmt;
    }

    public Double getItemTax() {
        return itemTax;
    }

    public void setItemTax(Double itemTax) {
        this.itemTax = itemTax;
    }

    public Double getItemTaxPer() {
        return itemTaxPer;
    }

    public void setItemTaxPer(Double itemTaxPer) {
        this.itemTaxPer = itemTaxPer;
    }

    public Double getItemIncAmt() {
        return itemIncAmt;
    }

    public void setItemIncAmt(Double itemIncAmt) {
        this.itemIncAmt = itemIncAmt;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Object getCommConfig() {
        return commConfig;
    }

    public void setCommConfig(Object commConfig) {
        this.commConfig = commConfig;
    }

    public Object getVariance() {
        return variance;
    }

    public void setVariance(Object variance) {
        this.variance = variance;
    }

    public Object getFormNo() {
        return formNo;
    }

    public void setFormNo(Object formNo) {
        this.formNo = formNo;
    }

    public Object getScrapItem() {
        return scrapItem;
    }

    public void setScrapItem(Object scrapItem) {
        this.scrapItem = scrapItem;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getAvgCost() {
        return avgCost;
    }

    public void setAvgCost(Double avgCost) {
        this.avgCost = avgCost;
    }

    public Object getBrand() {
        return brand;
    }

    public void setBrand(Object brand) {
        this.brand = brand;
    }

    public Object getCertificateno() {
        return certificateno;
    }

    public void setCertificateno(Object certificateno) {
        this.certificateno = certificateno;
    }

    public Object getQuantity() {
        return quantity;
    }

    public void setQuantity(Object quantity) {
        this.quantity = quantity;
    }

    public Object getItemQty() {
        return itemQty;
    }

    public void setItemQty(Object itemQty) {
        this.itemQty = itemQty;
    }

    public Object getUom() {
        return uom;
    }

    public void setUom(Object uom) {
        this.uom = uom;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
