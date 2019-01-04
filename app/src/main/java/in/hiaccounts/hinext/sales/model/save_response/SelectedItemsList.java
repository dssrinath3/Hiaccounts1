
package in.hiaccounts.hinext.sales.model.save_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import in.hiaccounts.hinext.sales.model.checkout.UomConvertorList;

public class SelectedItemsList implements Serializable {

    @SerializedName("itemName")
    @Expose
    private String itemName;
    @SerializedName("itemId")
    @Expose
    private Long itemId;
    @SerializedName("unitPrice")
    @Expose
    private Double unitPrice;
    @SerializedName("qty")
    @Expose
    private int qty;
    @SerializedName("returnQty")
    @Expose
    private int returnQty;
    @SerializedName("remainingQty")
    @Expose
    private int remainingQty;
    @SerializedName("amtexclusivetax")
    @Expose
    private Double amtexclusivetax;
    @SerializedName("taxid")
    @Expose
    private long taxid;
    @SerializedName("taxpercent")
    @Expose
    private Double taxpercent;
    @SerializedName("taxamt")
    @Expose
    private Double taxamt;
    @SerializedName("amtinclusivetax")
    @Expose
    private Double amtinclusivetax;
    @SerializedName("discountAmt")
    @Expose
    private Double discountAmt;
    @SerializedName("itemDescription")
    @Expose
    private String itemDescription;
    @SerializedName("taxName")
    @Expose
    private String taxName;
    @SerializedName("serializableItemId")
    @Expose
    private Object serializableItemId;
    @SerializedName("serializableNumbers")
    @Expose
    private Object serializableNumbers;
    @SerializedName("itemCode")
    @Expose
    private String itemCode;
    @SerializedName("makingCharge")
    @Expose
    private Double makingCharge;
    @SerializedName("actualWeight")
    @Expose
    private Double actualWeight;
    @SerializedName("cess")
    @Expose
    private Double cess;
    @SerializedName("cgstsgstsplitvalues")
    @Expose
    private Double cgstsgstsplitvalues;
    @SerializedName("taxPercentageSplit")
    @Expose
    private Double taxPercentageSplit;
    @SerializedName("cessTaxAmt")
    @Expose
    private Double cessTaxAmt;
    @SerializedName("hsnCode")
    @Expose
    private String hsnCode;
    @SerializedName("uomName")
    @Expose
    private String uomName;
    @SerializedName("salesOrderId")
    @Expose
    private String salesOrderId;
    @SerializedName("salesOrderDetailsId")
    @Expose
    private String salesOrderDetailsId;
    @SerializedName("uomConvertorList")
    @Expose
    private List<UomConvertorList> uomConvertorList;

    public String getSalesOrderId() {
        return salesOrderId;
    }

    public void setSalesOrderId(String salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public String getSalesOrderDetailsId() {
        return salesOrderDetailsId;
    }

    public void setSalesOrderDetailsId(String salesOrderDetailsId) {
        this.salesOrderDetailsId = salesOrderDetailsId;
    }

    public List<UomConvertorList> getUomConvertorList() {
        return uomConvertorList;
    }

    public void setUomConvertorList(List<UomConvertorList> uomConvertorList) {
        this.uomConvertorList = uomConvertorList;
    }

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

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getReturnQty() {
        return returnQty;
    }

    public void setReturnQty(int returnQty) {
        this.returnQty = returnQty;
    }

    public int getRemainingQty() {
        return remainingQty;
    }

    public void setRemainingQty(int remainingQty) {
        this.remainingQty = remainingQty;
    }

    public Double getAmtexclusivetax() {
        return amtexclusivetax;
    }

    public void setAmtexclusivetax(Double amtexclusivetax) {
        this.amtexclusivetax = amtexclusivetax;
    }

    public long getTaxid() {
        return taxid;
    }

    public void setTaxid(long taxid) {
        this.taxid = taxid;
    }

    public Double getTaxpercent() {
        return taxpercent;
    }

    public void setTaxpercent(Double taxpercent) {
        this.taxpercent = taxpercent;
    }

    public Double getTaxamt() {
        return taxamt;
    }

    public void setTaxamt(Double taxamt) {
        this.taxamt = taxamt;
    }

    public Double getAmtinclusivetax() {
        return amtinclusivetax;
    }

    public void setAmtinclusivetax(Double amtinclusivetax) {
        this.amtinclusivetax = amtinclusivetax;
    }

    public Double getDiscountAmt() {
        return discountAmt;
    }

    public void setDiscountAmt(Double discountAmt) {
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

    public Double getMakingCharge() {
        return makingCharge;
    }

    public void setMakingCharge(Double makingCharge) {
        this.makingCharge = makingCharge;
    }

    public Double getActualWeight() {
        return actualWeight;
    }

    public void setActualWeight(Double actualWeight) {
        this.actualWeight = actualWeight;
    }

    public Double getCess() {
        return cess;
    }

    public void setCess(Double cess) {
        this.cess = cess;
    }

    public Double getCgstsgstsplitvalues() {
        return cgstsgstsplitvalues;
    }

    public void setCgstsgstsplitvalues(Double cgstsgstsplitvalues) {
        this.cgstsgstsplitvalues = cgstsgstsplitvalues;
    }

    public Double getTaxPercentageSplit() {
        return taxPercentageSplit;
    }

    public void setTaxPercentageSplit(Double taxPercentageSplit) {
        this.taxPercentageSplit = taxPercentageSplit;
    }

    public Double getCessTaxAmt() {
        return cessTaxAmt;
    }

    public void setCessTaxAmt(Double cessTaxAmt) {
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
}
