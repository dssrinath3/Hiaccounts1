package in.hiaccounts.model.multi_payment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Prateek on 7/20/2017.
 */

public class PurchaseItem {

    @SerializedName("amtexclusivetax")
    @Expose
    private double amtexclusivetax;
    @SerializedName("amtinclusivetax")
    @Expose
    private double amtinclusivetax;
    @SerializedName("cess")
    @Expose
    private String cess;
    @SerializedName("cessTaxAmt")
    @Expose
    private double cessTaxAmt;
    @SerializedName("discountAmt")
    @Expose
    private double discountAmt;
    @SerializedName("igTax")
    @Expose
    private double igTax;
    @SerializedName("isDiscountInPercent")
    @Expose
    private boolean isDiscountInPercent;
    @SerializedName("itemCode")
    @Expose
    private String itemCode;
    @SerializedName("itemId")
    @Expose
    private long itemId;
    @SerializedName("itemName")
    @Expose
    private String itemName;
    @SerializedName("qty")
    @Expose
    private String qty;
    @SerializedName("remainingQty")
    @Expose
    private String remainingQty;
    @SerializedName("returnQty")
    @Expose
    private long returnQty;
    @SerializedName("serializableStatus")
    @Expose
    private String serializableStatus;
    @SerializedName("taxAmountSplit")
    @Expose
    private double taxAmountSplit;
    @SerializedName("taxamt")
    @Expose
    private double taxamt;
    @SerializedName("taxid")
    @Expose
    private long taxid;
    @SerializedName("taxName")
    @Expose
    private String taxName;
    @SerializedName("taxpercent")
    @Expose
    private String taxpercent;
    @SerializedName("unitPrice")
    @Expose
    private String unitPrice;

    public PurchaseItem(double amtexclusivetax, double amtinclusivetax, String cess, double cessTaxAmt, double discountAmt, double igTax, boolean isDiscountInPercent, String itemCode, long itemId, String itemName, String qty, String remainingQty, long returnQty, String serializableStatus, double taxAmountSplit, double taxamt, long taxid, String taxName, String taxpercent, String unitPrice) {
        this.amtexclusivetax = amtexclusivetax;
        this.amtinclusivetax = amtinclusivetax;
        this.cess = cess;
        this.cessTaxAmt = cessTaxAmt;
        this.discountAmt = discountAmt;
        this.igTax = igTax;
        this.isDiscountInPercent = isDiscountInPercent;
        this.itemCode = itemCode;
        this.itemId = itemId;
        this.itemName = itemName;
        this.qty = qty;
        this.remainingQty = remainingQty;
        this.returnQty = returnQty;
        this.serializableStatus = serializableStatus;
        this.taxAmountSplit = taxAmountSplit;
        this.taxamt = taxamt;
        this.taxid = taxid;
        this.taxName = taxName;
        this.taxpercent = taxpercent;
        this.unitPrice = unitPrice;
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

    public String getCess() {
        return cess;
    }

    public void setCess(String cess) {
        this.cess = cess;
    }

    public double getCessTaxAmt() {
        return cessTaxAmt;
    }

    public void setCessTaxAmt(double cessTaxAmt) {
        this.cessTaxAmt = cessTaxAmt;
    }

    public double getDiscountAmt() {
        return discountAmt;
    }

    public void setDiscountAmt(double discountAmt) {
        this.discountAmt = discountAmt;
    }

    public double getIgTax() {
        return igTax;
    }

    public void setIgTax(double igTax) {
        this.igTax = igTax;
    }

    public boolean isDiscountInPercent() {
        return isDiscountInPercent;
    }

    public void setDiscountInPercent(boolean discountInPercent) {
        isDiscountInPercent = discountInPercent;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getRemainingQty() {
        return remainingQty;
    }

    public void setRemainingQty(String remainingQty) {
        this.remainingQty = remainingQty;
    }

    public long getReturnQty() {
        return returnQty;
    }

    public void setReturnQty(long returnQty) {
        this.returnQty = returnQty;
    }

    public String getSerializableStatus() {
        return serializableStatus;
    }

    public void setSerializableStatus(String serializableStatus) {
        this.serializableStatus = serializableStatus;
    }

    public double getTaxAmountSplit() {
        return taxAmountSplit;
    }

    public void setTaxAmountSplit(double taxAmountSplit) {
        this.taxAmountSplit = taxAmountSplit;
    }

    public double getTaxamt() {
        return taxamt;
    }

    public void setTaxamt(double taxamt) {
        this.taxamt = taxamt;
    }

    public long getTaxid() {
        return taxid;
    }

    public void setTaxid(long taxid) {
        this.taxid = taxid;
    }

    public String getTaxName() {
        return taxName;
    }

    public void setTaxName(String taxName) {
        this.taxName = taxName;
    }

    public String getTaxpercent() {
        return taxpercent;
    }

    public void setTaxpercent(String taxpercent) {
        this.taxpercent = taxpercent;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }
}
