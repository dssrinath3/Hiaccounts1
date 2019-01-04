
package in.hiaccounts.model.duplicate_invoicedata;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SelectedItemsList implements Serializable
{

    @SerializedName("itemId")
    @Expose
    private long itemId;
    @SerializedName("serializableNumbers")
    @Expose
    private String serializableNumbers;
    @SerializedName("serializableItemId")
    @Expose
    private long serializableItemId;
    @SerializedName("remainingQty")
    @Expose
    private double remainingQty;
    @SerializedName("taxpercent")
    @Expose
    private double taxpercent;
    @SerializedName("itemCode")
    @Expose
    private long itemCode;
    @SerializedName("taxid")
    @Expose
    private long taxid;
    @SerializedName("taxName")
    @Expose
    private String taxName;
    @SerializedName("amtinclusivetax")
    @Expose
    private double amtinclusivetax;
    @SerializedName("amtexclusivetax")
    @Expose
    private double amtexclusivetax;
    @SerializedName("itemDescription")
    @Expose
    private String itemDescription;
    @SerializedName("itemName")
    @Expose
    private String itemName;
    @SerializedName("unitPrice")
    @Expose
    private double unitPrice;
    @SerializedName("qty")
    @Expose
    private double qty;
    @SerializedName("taxamt")
    @Expose
    private double taxamt;
    @SerializedName("returnQty")
    @Expose
    private double returnQty;
    @SerializedName("discountAmt")
    @Expose
    private double discountAmt;
    private final static long serialVersionUID = -5967399438098063530L;

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
     * @param taxName
     * @param qty
     * @param unitPrice
     * @param serializableNumbers
     * @param returnQty
     * @param remainingQty
     * @param itemId
     * @param taxpercent
     * @param itemDescription
     * @param amtexclusivetax
     * @param taxamt
     * @param discountAmt
     * @param serializableItemId
     * @param itemCode
     * @param taxid
     */
    public SelectedItemsList(long itemId, String serializableNumbers, long serializableItemId, double remainingQty, double taxpercent, long itemCode, long taxid, String taxName, double amtinclusivetax, double amtexclusivetax, String itemDescription, String itemName, double unitPrice, double qty, double taxamt, double returnQty, double discountAmt) {
        this.itemId = itemId;
        this.serializableNumbers = serializableNumbers;
        this.serializableItemId = serializableItemId;
        this.remainingQty = remainingQty;
        this.taxpercent = taxpercent;
        this.itemCode = itemCode;
        this.taxid = taxid;
        this.taxName = taxName;
        this.amtinclusivetax = amtinclusivetax;
        this.amtexclusivetax = amtexclusivetax;
        this.itemDescription = itemDescription;
        this.itemName = itemName;
        this.unitPrice = unitPrice;
        this.qty = qty;
        this.taxamt = taxamt;
        this.returnQty = returnQty;
        this.discountAmt = discountAmt;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getSerializableNumbers() {
        return serializableNumbers;
    }

    public void setSerializableNumbers(String serializableNumbers) {
        this.serializableNumbers = serializableNumbers;
    }

    public long getSerializableItemId() {
        return serializableItemId;
    }

    public void setSerializableItemId(long serializableItemId) {
        this.serializableItemId = serializableItemId;
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

    public long getItemCode() {
        return itemCode;
    }

    public void setItemCode(long itemCode) {
        this.itemCode = itemCode;
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

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
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

    public double getTaxamt() {
        return taxamt;
    }

    public void setTaxamt(double taxamt) {
        this.taxamt = taxamt;
    }

    public double getReturnQty() {
        return returnQty;
    }

    public void setReturnQty(double returnQty) {
        this.returnQty = returnQty;
    }

    public double getDiscountAmt() {
        return discountAmt;
    }

    public void setDiscountAmt(double discountAmt) {
        this.discountAmt = discountAmt;
    }
}
