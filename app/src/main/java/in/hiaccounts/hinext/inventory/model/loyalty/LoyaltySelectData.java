package in.hiaccounts.hinext.inventory.model.loyalty;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by shrinath on 12/11/2017.
 */

public class LoyaltySelectData implements Serializable {
    @SerializedName("loyaltyId")
    @Expose
    private Long loyaltyId;
    @SerializedName("loyaltyProgramName")
    @Expose
    private String loyaltyProgramName;
    @SerializedName("perCurrency")
    @Expose
    private String perCurrency;
    @SerializedName("perOrder")
    @Expose
    private String perOrder;
    @SerializedName("perProduct")
    @Expose
    private String perProduct;
    @SerializedName("loyaltyProgramNamePoints")
    @Expose
    private String loyaltyProgramNamePoints;
    @SerializedName("perCurrencyPoints")
    @Expose
    private String perCurrencyPoints;
    @SerializedName("perOrderPoints")
    @Expose
    private String perOrderPoints;
    @SerializedName("perProductPoints")
    @Expose
    private String perProductPoints;
    @SerializedName("itemID")
    @Expose
    private Long itemID;
    @SerializedName("itemId")
    @Expose
    private Long itemId;
    @SerializedName("itemDTOList")
    @Expose
    private String itemDTOList;
    @SerializedName("loyaltyType")
    @Expose
    private String loyaltyType;
    @SerializedName("fromDate")
    @Expose
    private String fromDate;
    @SerializedName("toDate")
    @Expose
    private String toDate;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("amountOrPercent")
    @Expose
    private String amountOrPercent;
    @SerializedName("percentage")
    @Expose
    private Long percentage;
    @SerializedName("minQty")
    @Expose
    private Long minQty;
    @SerializedName("status")
    @Expose
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getLoyaltyId() {
        return loyaltyId;
    }

    public void setLoyaltyId(Long loyaltyId) {
        this.loyaltyId = loyaltyId;
    }

    public String getLoyaltyProgramName() {
        return loyaltyProgramName;
    }

    public void setLoyaltyProgramName(String loyaltyProgramName) {
        this.loyaltyProgramName = loyaltyProgramName;
    }

    public String getPerCurrency() {
        return perCurrency;
    }

    public void setPerCurrency(String perCurrency) {
        this.perCurrency = perCurrency;
    }

    public String getPerOrder() {
        return perOrder;
    }

    public void setPerOrder(String perOrder) {
        this.perOrder = perOrder;
    }

    public String getPerProduct() {
        return perProduct;
    }

    public void setPerProduct(String perProduct) {
        this.perProduct = perProduct;
    }

    public String getLoyaltyProgramNamePoints() {
        return loyaltyProgramNamePoints;
    }

    public void setLoyaltyProgramNamePoints(String loyaltyProgramNamePoints) {
        this.loyaltyProgramNamePoints = loyaltyProgramNamePoints;
    }

    public String getPerCurrencyPoints() {
        return perCurrencyPoints;
    }

    public void setPerCurrencyPoints(String perCurrencyPoints) {
        this.perCurrencyPoints = perCurrencyPoints;
    }

    public String getPerOrderPoints() {
        return perOrderPoints;
    }

    public void setPerOrderPoints(String perOrderPoints) {
        this.perOrderPoints = perOrderPoints;
    }

    public String getPerProductPoints() {
        return perProductPoints;
    }

    public void setPerProductPoints(String perProductPoints) {
        this.perProductPoints = perProductPoints;
    }

    public Long getItemID() {
        return itemID;
    }

    public void setItemID(Long itemID) {
        this.itemID = itemID;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemDTOList() {
        return itemDTOList;
    }

    public void setItemDTOList(String itemDTOList) {
        this.itemDTOList = itemDTOList;
    }

    public String getLoyaltyType() {
        return loyaltyType;
    }

    public void setLoyaltyType(String loyaltyType) {
        this.loyaltyType = loyaltyType;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAmountOrPercent() {
        return amountOrPercent;
    }

    public void setAmountOrPercent(String amountOrPercent) {
        this.amountOrPercent = amountOrPercent;
    }

    public Long getPercentage() {
        return percentage;
    }

    public void setPercentage(Long percentage) {
        this.percentage = percentage;
    }

    public Long getMinQty() {
        return minQty;
    }

    public void setMinQty(Long minQty) {
        this.minQty = minQty;
    }
}
