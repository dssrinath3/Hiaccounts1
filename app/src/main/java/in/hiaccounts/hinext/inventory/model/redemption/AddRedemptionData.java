package in.hiaccounts.hinext.inventory.model.redemption;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Srinath on 23/12/17.
 */

public class AddRedemptionData implements Serializable {
    @SerializedName("redeemId")
    @Expose
    private Long redeemId;
    @SerializedName("redeemProgramName")
    @Expose
    private String redeemProgramName;
    @SerializedName("perCurrency")
    @Expose
    private String perCurrency;
    @SerializedName("perOrder")
    @Expose
    private String perOrder;
    @SerializedName("perProduct")
    @Expose
    private String perProduct;
    @SerializedName("redeemProgramNamePoints")
    @Expose
    private String redeemProgramNamePoints;
    @SerializedName("perCurrencyPoints")
    @Expose
    private String perCurrencyPoints;
    @SerializedName("perOrderPoints")
    @Expose
    private String perOrderPoints;
    @SerializedName("perProductPoints")
    @Expose
    private String perProductPoints;
    @SerializedName("redeemType")
    @Expose
    private String redeemType;
    @SerializedName("amountOrPercent")
    @Expose
    private String amountOrPercent;
    @SerializedName("percentage")
    @Expose
    private String percentage;
    @SerializedName("itemID")
    @Expose
    private Long itemID;
    @SerializedName("fromDate")
    @Expose
    private String fromDate;
    @SerializedName("toDate")
    @Expose
    private String toDate;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("maxQty")
    @Expose
    private Long maxQty;

    public Long getMaxQty() {
        return maxQty;
    }

    public void setMaxQty(Long maxQty) {
        this.maxQty = maxQty;
    }

    public Long getRedeemId() {
        return redeemId;
    }

    public void setRedeemId(Long redeemId) {
        this.redeemId = redeemId;
    }

    public String getRedeemProgramName() {
        return redeemProgramName;
    }

    public void setRedeemProgramName(String redeemProgramName) {
        this.redeemProgramName = redeemProgramName;
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

    public String getRedeemProgramNamePoints() {
        return redeemProgramNamePoints;
    }

    public void setRedeemProgramNamePoints(String redeemProgramNamePoints) {
        this.redeemProgramNamePoints = redeemProgramNamePoints;
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

    public String getRedeemType() {
        return redeemType;
    }

    public void setRedeemType(String redeemType) {
        this.redeemType = redeemType;
    }

    public String getAmountOrPercent() {
        return amountOrPercent;
    }

    public void setAmountOrPercent(String amountOrPercent) {
        this.amountOrPercent = amountOrPercent;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public Long getItemID() {
        return itemID;
    }

    public void setItemID(Long itemID) {
        this.itemID = itemID;
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


}
