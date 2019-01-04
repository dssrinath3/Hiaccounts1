
package in.hiaccounts.hinext.sales.model.item_validation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SelectedItemsList implements Serializable{

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
    @SerializedName("isDiscountInPercent")
    @Expose
    private boolean isDiscountInPercent;
    @SerializedName("cess")
    @Expose
    private String cess;
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
    private String amtexclusivetax;
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
    private String taxamt;
    @SerializedName("igTax")
    @Expose
    private double igTax;
    @SerializedName("amtinclusivetax")
    @Expose
    private String amtinclusivetax;
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

    public boolean isDiscountInPercent() {
        return isDiscountInPercent;
    }

    public void setDiscountInPercent(boolean discountInPercent) {
        isDiscountInPercent = discountInPercent;
    }

    public String getCess() {
        return cess;
    }

    public void setCess(String cess) {
        this.cess = cess;
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

    public String getAmtexclusivetax() {
        return amtexclusivetax;
    }

    public void setAmtexclusivetax(String amtexclusivetax) {
        this.amtexclusivetax = amtexclusivetax;
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

    public String getTaxamt() {
        return taxamt;
    }

    public void setTaxamt(String taxamt) {
        this.taxamt = taxamt;
    }

    public double getIgTax() {
        return igTax;
    }

    public void setIgTax(double igTax) {
        this.igTax = igTax;
    }

    public String getAmtinclusivetax() {
        return amtinclusivetax;
    }

    public void setAmtinclusivetax(String amtinclusivetax) {
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
}
