
package in.hiaccounts.hinext.purchase.model.purchase_saveresponses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SelectedItemsList implements Serializable{

    @SerializedName("itemName")
    @Expose
    public String itemName;
    @SerializedName("itemId")
    @Expose
    public Long itemId;
    @SerializedName("unitPrice")
    @Expose
    public double unitPrice;
    @SerializedName("qty")
    @Expose
    public int qty;
    @SerializedName("returnQty")
    @Expose
    public double returnQty;
    @SerializedName("remainingQty")
    @Expose
    public double remainingQty;
    @SerializedName("amtexclusivetax")
    @Expose
    public double amtexclusivetax;
    @SerializedName("taxid")
    @Expose
    public Long taxid;
    @SerializedName("taxpercent")
    @Expose
    public double taxpercent;
    @SerializedName("taxamt")
    @Expose
    public double taxamt;
    @SerializedName("amtinclusivetax")
    @Expose
    public double amtinclusivetax;
    @SerializedName("discountAmt")
    @Expose
    public double discountAmt;
    @SerializedName("itemDescription")
    @Expose
    public String itemDescription;
    @SerializedName("taxName")
    @Expose
    public String taxName;
    @SerializedName("serializableItemId")
    @Expose
    public Object serializableItemId;
    @SerializedName("serializableNumbers")
    @Expose
    public Object serializableNumbers;
    @SerializedName("itemCode")
    @Expose
    public String itemCode;
    @SerializedName("makingCharge")
    @Expose
    public double makingCharge;
    @SerializedName("actualWeight")
    @Expose
    public double actualWeight;
    @SerializedName("cess")
    @Expose
    public double cess;
    @SerializedName("cgstsgstsplitvalues")
    @Expose
    public double cgstsgstsplitvalues;
    @SerializedName("taxPercentageSplit")
    @Expose
    public double taxPercentageSplit;
    @SerializedName("cessTaxAmt")
    @Expose
    public double cessTaxAmt;
    @SerializedName("hsnCode")
    @Expose
    public String hsnCode;
    @SerializedName("uomName")
    @Expose
    public String uomName;
    @SerializedName("salesOrderId")
    @Expose
    public Object salesOrderId;
    @SerializedName("salesOrderDetailsId")
    @Expose
    public Object salesOrderDetailsId;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getReturnQty() {
        return returnQty;
    }

    public void setReturnQty(double returnQty) {
        this.returnQty = returnQty;
    }

    public double getRemainingQty() {
        return remainingQty;
    }

    public void setRemainingQty(double remainingQty) {
        this.remainingQty = remainingQty;
    }

    public double getAmtexclusivetax() {
        return amtexclusivetax;
    }

    public void setAmtexclusivetax(double amtexclusivetax) {
        this.amtexclusivetax = amtexclusivetax;
    }

    public Long getTaxid() {
        return taxid;
    }

    public void setTaxid(Long taxid) {
        this.taxid = taxid;
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

    public double getAmtinclusivetax() {
        return amtinclusivetax;
    }

    public void setAmtinclusivetax(double amtinclusivetax) {
        this.amtinclusivetax = amtinclusivetax;
    }

    public double getDiscountAmt() {
        return discountAmt;
    }

    public void setDiscountAmt(double discountAmt) {
        this.discountAmt = discountAmt;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getTaxName() {
        return taxName;
    }

    public void setTaxName(String taxName) {
        this.taxName = taxName;
    }

    public Object getSerializableItemId() {
        return serializableItemId;
    }

    public void setSerializableItemId(Object serializableItemId) {
        this.serializableItemId = serializableItemId;
    }

    public Object getSerializableNumbers() {
        return serializableNumbers;
    }

    public void setSerializableNumbers(Object serializableNumbers) {
        this.serializableNumbers = serializableNumbers;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
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

    public double getTaxPercentageSplit() {
        return taxPercentageSplit;
    }

    public void setTaxPercentageSplit(double taxPercentageSplit) {
        this.taxPercentageSplit = taxPercentageSplit;
    }

    public double getCessTaxAmt() {
        return cessTaxAmt;
    }

    public void setCessTaxAmt(double cessTaxAmt) {
        this.cessTaxAmt = cessTaxAmt;
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

    public Object getSalesOrderId() {
        return salesOrderId;
    }

    public void setSalesOrderId(Object salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public Object getSalesOrderDetailsId() {
        return salesOrderDetailsId;
    }

    public void setSalesOrderDetailsId(Object salesOrderDetailsId) {
        this.salesOrderDetailsId = salesOrderDetailsId;
    }
}
