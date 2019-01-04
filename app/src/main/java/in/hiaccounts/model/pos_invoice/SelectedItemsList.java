
package in.hiaccounts.model.pos_invoice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SelectedItemsList implements Serializable {

    @SerializedName("remainingQty")
    @Expose
    private double remainingQty;
    @SerializedName("taxpercent")
    @Expose
    private double taxpercent;
    @SerializedName("taxamt")
    @Expose
    private double taxamt;
    @SerializedName("discountAmt")
    @Expose
    private double discountAmt;
    @SerializedName("returnQty")
    @Expose
    private double returnQty;
    @SerializedName("taxName")
    @Expose
    private String taxName;
    @SerializedName("unitPrice")
    @Expose
    private double unitPrice;
    @SerializedName("qty")
    @Expose
    private double qty;
    @SerializedName("itemName")
    @Expose
    private String itemName;
    @SerializedName("amtinclusivetax")
    @Expose
    private double amtinclusivetax;
    @SerializedName("amtexclusivetax")
    @Expose
    private double amtexclusivetax;
    @SerializedName("itemId")
    @Expose
    private int itemId;
    @SerializedName("taxid")
    @Expose
    private int taxid;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SelectedItemsList() {
    }

    /**
     * 
     * @param amtinclusivetax
     * @param itemName
     * @param amtexclusivetax
     * @param taxName
     * @param taxamt
     * @param qty
     * @param unitPrice
     * @param returnQty
     * @param remainingQty
     * @param itemId
     * @param discountAmt
     * @param taxpercent
     * @param taxid
     */
    public SelectedItemsList(double remainingQty, double taxpercent, double taxamt, double discountAmt, double returnQty, String taxName, double unitPrice, double qty, String itemName, double amtinclusivetax, double amtexclusivetax, int itemId, int taxid) {
        super();
        this.remainingQty = remainingQty;
        this.taxpercent = taxpercent;
        this.taxamt = taxamt;
        this.discountAmt = discountAmt;
        this.returnQty = returnQty;
        this.taxName = taxName;
        this.unitPrice = unitPrice;
        this.qty = qty;
        this.itemName = itemName;
        this.amtinclusivetax = amtinclusivetax;
        this.amtexclusivetax = amtexclusivetax;
        this.itemId = itemId;
        this.taxid = taxid;
    }

    public double getRemainingQty() {
        return remainingQty;
    }

    public void setRemainingQty(double remainingQty) {
        this.remainingQty = remainingQty;
    }

    public double getTaxpercent() {
        return taxpercent;
    }

    public void setTaxpercent(double taxpercent) {
        this.taxpercent = taxpercent;
    }

    public double getTaxamt() {
        return taxamt;
    }

    public void setTaxamt(double taxamt) {
        this.taxamt = taxamt;
    }

    public double getDiscountAmt() {
        return discountAmt;
    }

    public void setDiscountAmt(double discountAmt) {
        this.discountAmt = discountAmt;
    }

    public double getReturnQty() {
        return returnQty;
    }

    public void setReturnQty(double returnQty) {
        this.returnQty = returnQty;
    }

    public String getTaxName() {
        return taxName;
    }

    public void setTaxName(String taxName) {
        this.taxName = taxName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getAmtinclusivetax() {
        return amtinclusivetax;
    }

    public void setAmtinclusivetax(double amtinclusivetax) {
        this.amtinclusivetax = amtinclusivetax;
    }

    public double getAmtexclusivetax() {
        return amtexclusivetax;
    }

    public void setAmtexclusivetax(double amtexclusivetax) {
        this.amtexclusivetax = amtexclusivetax;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getTaxid() {
        return taxid;
    }

    public void setTaxid(int taxid) {
        this.taxid = taxid;
    }

}
