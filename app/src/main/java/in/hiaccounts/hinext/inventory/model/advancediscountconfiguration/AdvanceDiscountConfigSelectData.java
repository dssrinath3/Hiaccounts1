package in.hiaccounts.hinext.inventory.model.advancediscountconfiguration;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by shrinath on 12/15/2017.
 */

public class AdvanceDiscountConfigSelectData implements Serializable {
    @SerializedName("discountId")
    @Expose
    private Long discountId;
    @SerializedName("fromDate")
    @Expose
    private String fromDate;
    @SerializedName("toDate")
    @Expose
    private String toDate;
    @SerializedName("amountordiscount")
    @Expose
    private Long amountordiscount;
    @SerializedName("customerId")
    @Expose
    private Long customerId;
    @SerializedName("customerName")
    @Expose
    private String customerName;
    @SerializedName("itemName")
    @Expose
    private String itemName;
    @SerializedName("itemId")
    @Expose
    private Long itemId;
    @SerializedName("checkBoxValue")
    @Expose
    private String checkBoxValue;
    @SerializedName("customerID")
    @Expose
    private Long customerID;
    @SerializedName("itemID")
    @Expose
    private Long itemID;
    @SerializedName("promotionName")
    @Expose
    private String promotionName;
    @SerializedName("minPurchaseAmt")
    @Expose
    private Long minPurchaseAmt;
    @SerializedName("promoCode")
    @Expose
    private String promoCode;
    @SerializedName("useCode")
    @Expose
    private String useCode;
    @SerializedName("percentageFixed")
    @Expose
    private String percentageFixed;
    @SerializedName("maxDiscount")
    @Expose
    private String maxDiscount;
    @SerializedName("freeProduct")
    @Expose
    private String freeProduct;
    @SerializedName("freeProductQty")
    @Expose
    private String freeProductQty;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("customerDTOList")
    @Expose
    private String customerDTOList;
    @SerializedName("itemDTOList")
    @Expose
    private String itemDTOList;

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

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
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

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getCheckBoxValue() {
        return checkBoxValue;
    }

    public void setCheckBoxValue(String checkBoxValue) {
        this.checkBoxValue = checkBoxValue;
    }

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public Long getItemID() {
        return itemID;
    }

    public void setItemID(Long itemID) {
        this.itemID = itemID;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public Long getMinPurchaseAmt() {
        return minPurchaseAmt;
    }

    public void setMinPurchaseAmt(Long minPurchaseAmt) {
        this.minPurchaseAmt = minPurchaseAmt;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public String getUseCode() {
        return useCode;
    }

    public void setUseCode(String useCode) {
        this.useCode = useCode;
    }

    public String getPercentageFixed() {
        return percentageFixed;
    }

    public void setPercentageFixed(String percentageFixed) {
        this.percentageFixed = percentageFixed;
    }

    public String getMaxDiscount() {
        return maxDiscount;
    }

    public void setMaxDiscount(String maxDiscount) {
        this.maxDiscount = maxDiscount;
    }

    public String getFreeProduct() {
        return freeProduct;
    }

    public void setFreeProduct(String freeProduct) {
        this.freeProduct = freeProduct;
    }

    public String getFreeProductQty() {
        return freeProductQty;
    }

    public void setFreeProductQty(String freeProductQty) {
        this.freeProductQty = freeProductQty;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomerDTOList() {
        return customerDTOList;
    }

    public void setCustomerDTOList(String customerDTOList) {
        this.customerDTOList = customerDTOList;
    }

    public String getItemDTOList() {
        return itemDTOList;
    }

    public void setItemDTOList(String itemDTOList) {
        this.itemDTOList = itemDTOList;
    }
}
