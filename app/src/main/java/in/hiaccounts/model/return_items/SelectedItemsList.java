
package in.hiaccounts.model.return_items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SelectedItemsList implements Serializable{

    @SerializedName("amtinclusivetax")
    @Expose
    private float amtinclusivetax;
    @SerializedName("itemName")
    @Expose
    private String itemName;
    @SerializedName("taxName")
    @Expose
    private String taxName;
    @SerializedName("serializableNumbers")
    @Expose
    private String serializableNumbers;
    @SerializedName("unitPrice")
    @Expose
    private float unitPrice;
    @SerializedName("qty")
    @Expose
    private int qty;
    @SerializedName("remainingQty")
    @Expose
    private int remainingQty;
    @SerializedName("returnQty")
    @Expose
    private int returnQty;
    @SerializedName("itemId")
    @Expose
    private int itemId;
    @SerializedName("taxpercent")
    @Expose
    private int taxpercent;
    @SerializedName("itemDescription")
    @Expose
    private String itemDescription;
    @SerializedName("amtexclusivetax")
    @Expose
    private float amtexclusivetax;
    @SerializedName("taxamt")
    @Expose
    private float taxamt;
    @SerializedName("serializableItemId")
    @Expose
    private long serializableItemId;
    @SerializedName("discountAmt")
    @Expose
    private float discountAmt;
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
     * @param taxName
     * @param serializableNumbers
     * @param unitPrice
     * @param qty
     * @param remainingQty
     * @param returnQty
     * @param itemId
     * @param taxpercent
     * @param itemDescription
     * @param amtexclusivetax
     * @param taxamt
     * @param discountAmt
     * @param serializableItemId
     * @param taxid
     */
    public SelectedItemsList(float amtinclusivetax, String itemName, String  taxName, String serializableNumbers, float unitPrice, int qty, int remainingQty, int returnQty, int itemId, int taxpercent, String itemDescription, float amtexclusivetax, float taxamt, long serializableItemId, float discountAmt, int taxid) {
        super();
        this.amtinclusivetax = amtinclusivetax;
        this.itemName = itemName;
        this.taxName = taxName;
        this.serializableNumbers = serializableNumbers;
        this.unitPrice = unitPrice;
        this.qty = qty;
        this.remainingQty = remainingQty;
        this.returnQty = returnQty;
        this.itemId = itemId;
        this.taxpercent = taxpercent;
        this.itemDescription = itemDescription;
        this.amtexclusivetax = amtexclusivetax;
        this.taxamt = taxamt;
        this.serializableItemId = serializableItemId;
        this.discountAmt = discountAmt;
        this.taxid = taxid;
    }

    public float getAmtinclusivetax() {
        return amtinclusivetax;
    }

    public void setAmtinclusivetax(float amtinclusivetax) {
        this.amtinclusivetax = amtinclusivetax;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getTaxName() {
        return taxName;
    }

    public void setTaxName(String taxName) {
        this.taxName = taxName;
    }

    public String getSerializableNumbers() {
        return serializableNumbers;
    }

    public void setSerializableNumbers(String  serializableNumbers) {
        this.serializableNumbers = serializableNumbers;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getRemainingQty() {
        return remainingQty;
    }

    public void setRemainingQty(int remainingQty) {
        this.remainingQty = remainingQty;
    }

    public int getReturnQty() {
        return returnQty;
    }

    public void setReturnQty(int returnQty) {
        this.returnQty = returnQty;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getTaxpercent() {
        return taxpercent;
    }

    public void setTaxpercent(int taxpercent) {
        this.taxpercent = taxpercent;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public float getAmtexclusivetax() {
        return amtexclusivetax;
    }

    public void setAmtexclusivetax(float amtexclusivetax) {
        this.amtexclusivetax = amtexclusivetax;
    }

    public float getTaxamt() {
        return taxamt;
    }

    public void setTaxamt(float taxamt) {
        this.taxamt = taxamt;
    }

    public long getSerializableItemId() {
        return serializableItemId;
    }

    public void setSerializableItemId(long serializableItemId) {
        this.serializableItemId = serializableItemId;
    }

    public float getDiscountAmt() {
        return discountAmt;
    }

    public void setDiscountAmt(float discountAmt) {
        this.discountAmt = discountAmt;
    }

    public int getTaxid() {
        return taxid;
    }

    public void setTaxid(int taxid) {
        this.taxid = taxid;
    }

}
