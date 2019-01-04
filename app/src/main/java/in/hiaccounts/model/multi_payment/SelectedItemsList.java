
package in.hiaccounts.model.multi_payment;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SelectedItemsList implements Serializable
{

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
    @SerializedName("amtinclusivetax")
    @Expose
    private String amtinclusivetax;

    @SerializedName("serializableItemId")
    @Expose
    private Long serializableItemId;

    @SerializedName("serializableNumbers")
    @Expose
    private String serializableNumbers;


    @SerializedName("cess")
    @Expose
    private double cess;

    @SerializedName("igTax")
    @Expose
    private double cgstsgstsplitvalues;

    @SerializedName("taxAmountSplit")
    @Expose
    private String taxPercentageSplit;

    @SerializedName("cessTaxAmt")
    @Expose
    private double cessTaxAmt;

    @SerializedName("uomName")
    @Expose
    private String uomName;

    @SerializedName("hsnCode")
    @Expose
    private String hsnCode;




    private final static long serialVersionUID = 954294171890066514L;

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
     * @param unitPrice
     * @param qty
     * @param returnQty
     * @param remainingQty
     * @param taxpercent
     * @param itemId
     * @param isDiscountInPercent
     * @param amtexclusivetax
     * @param serializableStatus
     * @param taxamt
     * @param discountAmt
     * @param itemCode
     * @param taxid
     */
    public SelectedItemsList(String itemCode, long itemId, String itemName, String serializableStatus, String unitPrice, double discountAmt, boolean isDiscountInPercent, long qty, long returnQty, long remainingQty, String amtexclusivetax, long taxid, String taxpercent, String taxName, String taxamt, String amtinclusivetax) {
        super();
        this.itemCode = itemCode;
        this.itemId = itemId;
        this.itemName = itemName;
        this.serializableStatus = serializableStatus;
        this.unitPrice = unitPrice;
        this.discountAmt = discountAmt;
        this.isDiscountInPercent = isDiscountInPercent;
        this.qty = qty;
        this.returnQty = returnQty;
        this.remainingQty = remainingQty;
        this.amtexclusivetax = amtexclusivetax;
        this.taxid = taxid;
        this.taxpercent = taxpercent;
        this.taxName = taxName;
        this.taxamt = taxamt;
        this.amtinclusivetax = amtinclusivetax;
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

    public boolean isIsDiscountInPercent() {
        return isDiscountInPercent;
    }

    public void setIsDiscountInPercent(boolean isDiscountInPercent) {
        this.isDiscountInPercent = isDiscountInPercent;
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

    public String getAmtinclusivetax() {
        return amtinclusivetax;
    }

    public void setAmtinclusivetax(String amtinclusivetax) {
        this.amtinclusivetax = amtinclusivetax;
    }

    public boolean isDiscountInPercent() {
        return isDiscountInPercent;
    }

    public void setDiscountInPercent(boolean discountInPercent) {
        isDiscountInPercent = discountInPercent;
    }


    public Long getSerializableItemId() {
        return serializableItemId;
    }

    public void setSerializableItemId(Long serializableItemId) {
        this.serializableItemId = serializableItemId;
    }

    public String getSerializableNumbers() {
        return serializableNumbers;
    }

    public void setSerializableNumbers(String serializableNumbers) {
        this.serializableNumbers = serializableNumbers;
    }

    public double getCess() {
        return cess;
    }

    public void setCess(double cess) {
        this.cess = cess;
    }

    public double getCgstsgstsplitvalues() {
        return cgstsgstsplitvalues;
    }

    public void setCgstsgstsplitvalues(double cgstsgstsplitvalues) {
        this.cgstsgstsplitvalues = cgstsgstsplitvalues;
    }

    public String getTaxPercentageSplit() {
        return taxPercentageSplit;
    }

    public void setTaxPercentageSplit(String taxPercentageSplit) {
        this.taxPercentageSplit = taxPercentageSplit;
    }

    public double getCessTaxAmt() {
        return cessTaxAmt;
    }

    public void setCessTaxAmt(double cessTaxAmt) {
        this.cessTaxAmt = cessTaxAmt;
    }

    public String getUomName() {
        return uomName;
    }

    public void setUomName(String uomName) {
        this.uomName = uomName;
    }

    public String getHsnCode() {
        return hsnCode;
    }

    public void setHsnCode(String hsnCode) {
        this.hsnCode = hsnCode;
    }

}
