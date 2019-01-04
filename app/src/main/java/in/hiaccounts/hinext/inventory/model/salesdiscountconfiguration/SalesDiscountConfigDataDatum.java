package in.hiaccounts.hinext.inventory.model.salesdiscountconfiguration;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SalesDiscountConfigDataDatum implements Serializable {
    @SerializedName("discountId")
    @Expose
    public Long discountId;
    @SerializedName("fromDate")
    @Expose
    public String fromDate;
    @SerializedName("toDate")
    @Expose
    public String toDate;
    @SerializedName("amountordiscount")
    @Expose
    public Long amountordiscount;
    @SerializedName("customerId")
    @Expose
    public Object customerId;
    @SerializedName("customerName")
    @Expose
    public String customerName;
    @SerializedName("itemName")
    @Expose
    public String itemName;
    @SerializedName("itemId")
    @Expose
    public Object itemId;
    @SerializedName("checkBoxValue")
    @Expose
    public String checkBoxValue;
    @SerializedName("customerID")
    @Expose
    public Object customerID;
    @SerializedName("itemID")
    @Expose
    public Long itemID;
    @SerializedName("promotionName")
    @Expose
    public Object promotionName;
    @SerializedName("minPurchaseAmt")
    @Expose
    public Object minPurchaseAmt;
    @SerializedName("promoCode")
    @Expose
    public Object promoCode;
    @SerializedName("useCode")
    @Expose
    public Object useCode;
    @SerializedName("percentageFixed")
    @Expose
    public Object percentageFixed;
    @SerializedName("maxDiscount")
    @Expose
    public Object maxDiscount;
    @SerializedName("freeProduct")
    @Expose
    public Object freeProduct;
    @SerializedName("freeProductQty")
    @Expose
    public Object freeProductQty;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("qty")
    @Expose
    public Object qty;
    @SerializedName("quantity")
    @Expose
    public Object quantity;
    @SerializedName("discountValue")
    @Expose
    public Object discountValue;
    @SerializedName("customerDTOList")
    @Expose
    public Object customerDTOList;
    @SerializedName("itemDTOList")
    @Expose
    public Object itemDTOList;

    public Long getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Long discountId) {
        this.discountId = discountId;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public Long getAmountordiscount() {
        return amountordiscount;
    }

    public void setAmountordiscount(Long amountordiscount) {
        this.amountordiscount = amountordiscount;
    }

    public Object getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Object customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Object getItemId() {
        return itemId;
    }

    public void setItemId(Object itemId) {
        this.itemId = itemId;
    }

    public String getCheckBoxValue() {
        return checkBoxValue;
    }

    public void setCheckBoxValue(String checkBoxValue) {
        this.checkBoxValue = checkBoxValue;
    }

    public Object getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Object customerID) {
        this.customerID = customerID;
    }

    public Long getItemID() {
        return itemID;
    }

    public void setItemID(Long itemID) {
        this.itemID = itemID;
    }

    public Object getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(Object promotionName) {
        this.promotionName = promotionName;
    }

    public Object getMinPurchaseAmt() {
        return minPurchaseAmt;
    }

    public void setMinPurchaseAmt(Object minPurchaseAmt) {
        this.minPurchaseAmt = minPurchaseAmt;
    }

    public Object getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(Object promoCode) {
        this.promoCode = promoCode;
    }

    public Object getUseCode() {
        return useCode;
    }

    public void setUseCode(Object useCode) {
        this.useCode = useCode;
    }

    public Object getPercentageFixed() {
        return percentageFixed;
    }

    public void setPercentageFixed(Object percentageFixed) {
        this.percentageFixed = percentageFixed;
    }

    public Object getMaxDiscount() {
        return maxDiscount;
    }

    public void setMaxDiscount(Object maxDiscount) {
        this.maxDiscount = maxDiscount;
    }

    public Object getFreeProduct() {
        return freeProduct;
    }

    public void setFreeProduct(Object freeProduct) {
        this.freeProduct = freeProduct;
    }

    public Object getFreeProductQty() {
        return freeProductQty;
    }

    public void setFreeProductQty(Object freeProductQty) {
        this.freeProductQty = freeProductQty;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getQty() {
        return qty;
    }

    public void setQty(Object qty) {
        this.qty = qty;
    }

    public Object getQuantity() {
        return quantity;
    }

    public void setQuantity(Object quantity) {
        this.quantity = quantity;
    }

    public Object getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(Object discountValue) {
        this.discountValue = discountValue;
    }

    public Object getCustomerDTOList() {
        return customerDTOList;
    }

    public void setCustomerDTOList(Object customerDTOList) {
        this.customerDTOList = customerDTOList;
    }

    public Object getItemDTOList() {
        return itemDTOList;
    }

    public void setItemDTOList(Object itemDTOList) {
        this.itemDTOList = itemDTOList;
    }
}
