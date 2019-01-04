
package in.hiaccounts.model.invoice_data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SelectedItemsList implements Serializable {

    @SerializedName("itemDescription")
    @Expose
    private Object itemDescription;
    @SerializedName("discountAmt")
    @Expose
    private double discountAmt;
    @SerializedName("taxamt")
    @Expose
    private double taxamt;
    @SerializedName("taxpercent")
    @Expose
    private double taxpercent;
    @SerializedName("returnQty")
    @Expose
    private double returnQty;
    @SerializedName("itemName")
    @Expose
    private String itemName;
    @SerializedName("itemCode")
    @Expose
    private String itemCode;
    @SerializedName("itemId")
    @Expose
    private long itemId;
    @SerializedName("remainingQty")
    @Expose
    private double remainingQty;
    @SerializedName("amtinclusivetax")
    @Expose
    private double amtinclusivetax;
    @SerializedName("amtexclusivetax")
    @Expose
    private double amtexclusivetax;
    @SerializedName("taxid")
    @Expose
    private long taxid;
    @SerializedName("makingCharge")
    @Expose
    private double makingCharge;
    @SerializedName("actualWeight")
    @Expose
    private double actualWeight;
    @SerializedName("serializableNumbers")
    @Expose
    private Object serializableNumbers;
    @SerializedName("serializableItemId")
    @Expose
    private Object serializableItemId;
    @SerializedName("unitPrice")
    @Expose
    private double unitPrice;
    @SerializedName("qty")
    @Expose
    private double qty;
    @SerializedName("taxName")
    @Expose
    private String taxName;

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
     * @param makingCharge
     * @param taxName
     * @param qty
     * @param unitPrice
     * @param serializableNumbers
     * @param returnQty
     * @param remainingQty
     * @param taxpercent
     * @param itemId
     * @param itemDescription
     * @param actualWeight
     * @param amtexclusivetax
     * @param taxamt
     * @param serializableItemId
     * @param discountAmt
     * @param itemCode
     * @param taxid
     */
    public SelectedItemsList(Object itemDescription, double discountAmt, double taxamt, double taxpercent, double returnQty, String itemName, String itemCode, long itemId, double remainingQty, double amtinclusivetax, double amtexclusivetax, long taxid, double makingCharge, double actualWeight, Object serializableNumbers, Object serializableItemId, double unitPrice, double qty, String taxName) {
        super();
        this.itemDescription = itemDescription;
        this.discountAmt = discountAmt;
        this.taxamt = taxamt;
        this.taxpercent = taxpercent;
        this.returnQty = returnQty;
        this.itemName = itemName;
        this.itemCode = itemCode;
        this.itemId = itemId;
        this.remainingQty = remainingQty;
        this.amtinclusivetax = amtinclusivetax;
        this.amtexclusivetax = amtexclusivetax;
        this.taxid = taxid;
        this.makingCharge = makingCharge;
        this.actualWeight = actualWeight;
        this.serializableNumbers = serializableNumbers;
        this.serializableItemId = serializableItemId;
        this.unitPrice = unitPrice;
        this.qty = qty;
        this.taxName = taxName;
    }

    public Object getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(Object itemDescription) {
        this.itemDescription = itemDescription;
    }

    public double getDiscountAmt() {
        return discountAmt;
    }

    public void setDiscountAmt(double discountAmt) {
        this.discountAmt = discountAmt;
    }

    public double getTaxamt() {
        return taxamt;
    }

    public void setTaxamt(double taxamt) {
        this.taxamt = taxamt;
    }

    public double getTaxpercent() {
        return taxpercent;
    }

    public void setTaxpercent(double taxpercent) {
        this.taxpercent = taxpercent;
    }

    public double getReturnQty() {
        return returnQty;
    }

    public void setReturnQty(double returnQty) {
        this.returnQty = returnQty;
    }

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

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public double getRemainingQty() {
        return remainingQty;
    }

    public void setRemainingQty(double remainingQty) {
        this.remainingQty = remainingQty;
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

    public long getTaxid() {
        return taxid;
    }

    public void setTaxid(long taxid) {
        this.taxid = taxid;
    }

    public double getMakingCharge() {
        return makingCharge;
    }

    public void setMakingCharge(double makingCharge) {
        this.makingCharge = makingCharge;
    }

    public double getActualWeight() {
        return actualWeight;
    }

    public void setActualWeight(double actualWeight) {
        this.actualWeight = actualWeight;
    }

    public Object getSerializableNumbers() {
        return serializableNumbers;
    }

    public void setSerializableNumbers(Object serializableNumbers) {
        this.serializableNumbers = serializableNumbers;
    }

    public Object getSerializableItemId() {
        return serializableItemId;
    }

    public void setSerializableItemId(Object serializableItemId) {
        this.serializableItemId = serializableItemId;
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

    public String getTaxName() {
        return taxName;
    }

    public void setTaxName(String taxName) {
        this.taxName = taxName;
    }

}
