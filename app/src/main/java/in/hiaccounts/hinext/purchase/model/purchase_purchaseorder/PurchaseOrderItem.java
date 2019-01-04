package in.hiaccounts.hinext.purchase.model.purchase_purchaseorder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import in.hiaccounts.hinext.sales.model.checkout.UomConvertorList;

/**
 * Created by Admin on 7/29/2017.
 */

public class PurchaseOrderItem {
    @SerializedName("itemDescription")
    @Expose
    private String itemDescription;
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
    private String unitPrice;
    @SerializedName("discountAmt")
    @Expose
    private double discountAmt;
    @SerializedName("cess")
    @Expose
    private String cess;
    @SerializedName("isDiscountInPercent")
    @Expose
    private boolean isDiscountInPercent;
    @SerializedName("qty")
    @Expose
    private String qty;
    @SerializedName("returnQty")
    @Expose
    private long returnQty;
    @SerializedName("remainingQty")
    @Expose
    private String remainingQty;
    @SerializedName("amtexclusivetax")
    @Expose
    private double amtexclusivetax;
    @SerializedName("taxid")
    @Expose
    private double taxid;
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
    @SerializedName("hsnCode")
    @Expose
    private String hsnCode;
    @SerializedName("uomName")
    @Expose
    private String uomName;
    @SerializedName("cessTaxAmt")
    @Expose
    private double cessTaxAmt;
    @SerializedName("taxAmountSplit")
    @Expose
    private String taxAmountSplit;
    @SerializedName("uomConvertorList")
    @Expose
    public List<UomConvertorList> uomConvertorList = null;
    @SerializedName("inclusiveJSON")
    @Expose
    public String inclusiveJSON;
    @SerializedName("itemCategoryId")
    @Expose
    public Long itemCategoryId;
    @SerializedName("itemCategoryName")
    @Expose
    public String itemCategoryName;
    @SerializedName("discountConfigAmt")
    @Expose
    public Double discountConfigAmt;

    public String getInclusiveJSON() {
        return inclusiveJSON;
    }

    public void setInclusiveJSON(String inclusiveJSON) {
        this.inclusiveJSON = inclusiveJSON;
    }

    public Long getItemCategoryId() {
        return itemCategoryId;
    }

    public void setItemCategoryId(Long itemCategoryId) {
        this.itemCategoryId = itemCategoryId;
    }

    public String getItemCategoryName() {
        return itemCategoryName;
    }

    public void setItemCategoryName(String itemCategoryName) {
        this.itemCategoryName = itemCategoryName;
    }

    public Double getDiscountConfigAmt() {
        return discountConfigAmt;
    }

    public void setDiscountConfigAmt(Double discountConfigAmt) {
        this.discountConfigAmt = discountConfigAmt;
    }

    public List<UomConvertorList> getUomConvertorList() {
        return uomConvertorList;
    }

    public void setUomConvertorList(List<UomConvertorList> uomConvertorList) {
        this.uomConvertorList = uomConvertorList;
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

    public String getSerializableStatus() {
        return serializableStatus;
    }

    public void setSerializableStatus(String serializableStatus) {
        this.serializableStatus = serializableStatus;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getDiscountAmt() {
        return discountAmt;
    }

    public void setDiscountAmt(double discountAmt) {
        this.discountAmt = discountAmt;
    }

    public String getCess() {
        return cess;
    }

    public void setCess(String cess) {
        this.cess = cess;
    }

    public boolean isDiscountInPercent() {
        return isDiscountInPercent;
    }

    public void setDiscountInPercent(boolean discountInPercent) {
        isDiscountInPercent = discountInPercent;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public long getReturnQty() {
        return returnQty;
    }

    public void setReturnQty(long returnQty) {
        this.returnQty = returnQty;
    }

    public String getRemainingQty() {
        return remainingQty;
    }

    public void setRemainingQty(String remainingQty) {
        this.remainingQty = remainingQty;
    }

    public double getAmtexclusivetax() {
        return amtexclusivetax;
    }

    public void setAmtexclusivetax(double amtexclusivetax) {
        this.amtexclusivetax = amtexclusivetax;
    }

    public double getTaxid() {
        return taxid;
    }

    public void setTaxid(double taxid) {
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

    public double getIgTax() {
        return igTax;
    }

    public void setIgTax(double igTax) {
        this.igTax = igTax;
    }

    public double getAmtinclusivetax() {
        return amtinclusivetax;
    }

    public void setAmtinclusivetax(double amtinclusivetax) {
        this.amtinclusivetax = amtinclusivetax;
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

    public double getCessTaxAmt() {
        return cessTaxAmt;
    }

    public void setCessTaxAmt(double cessTaxAmt) {
        this.cessTaxAmt = cessTaxAmt;
    }

    public String getTaxAmountSplit() {
        return taxAmountSplit;
    }

    public void setTaxAmountSplit(String taxAmountSplit) {
        this.taxAmountSplit = taxAmountSplit;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }
}
