package in.hiaccounts.hinext.service.model.service_invoke;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import in.hiaccounts.hinext.service.model.SelectedItemsList;

/**
 * Created by administrator on 26/2/18.
 */

public class InvokeList_Data implements Serializable {
    @SerializedName("selectedItemsList")
    @Expose
    public List<SelectedItemsList> selectedItemsList = null;
    @SerializedName("id")
    @Expose
    public Long id;
    @SerializedName("srNo")
    @Expose
    public String srNo;
    @SerializedName("deliveredQuantity")
    @Expose
    public Double deliveredQuantity;
    @SerializedName("date")
    @Expose
    public Long date;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("qty")
    @Expose
    public Double qty;
    @SerializedName("itemName")
    @Expose
    public String itemName;
    @SerializedName("detailstatus")
    @Expose
    public String detailstatus;
    @SerializedName("remaningQty")
    @Expose
    public Double remaningQty;
    @SerializedName("repairQty")
    @Expose
    public Double repairQty;
    @SerializedName("delivaryQty")
    @Expose
    public Double delivaryQty;
    @SerializedName("referenceno")
    @Expose
    public String referenceno;
    @SerializedName("requiredQuantity")
    @Expose
    public Double requiredQuantity;
    @SerializedName("invoiceNo")
    @Expose
    public String invoiceNo;
    @SerializedName("price")
    @Expose
    public Double price;
    @SerializedName("status")
    @Expose
    public String status;

    public List<SelectedItemsList> getSelectedItemsList() {
        return selectedItemsList;
    }

    public void setSelectedItemsList(List<SelectedItemsList> selectedItemsList) {
        this.selectedItemsList = selectedItemsList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSrNo() {
        return srNo;
    }

    public void setSrNo(String srNo) {
        this.srNo = srNo;
    }

    public Double getDeliveredQuantity() {
        return deliveredQuantity;
    }

    public void setDeliveredQuantity(Double deliveredQuantity) {
        this.deliveredQuantity = deliveredQuantity;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDetailstatus() {
        return detailstatus;
    }

    public void setDetailstatus(String detailstatus) {
        this.detailstatus = detailstatus;
    }

    public Double getRemaningQty() {
        return remaningQty;
    }

    public void setRemaningQty(Double remaningQty) {
        this.remaningQty = remaningQty;
    }

    public Double getRepairQty() {
        return repairQty;
    }

    public void setRepairQty(Double repairQty) {
        this.repairQty = repairQty;
    }

    public Double getDelivaryQty() {
        return delivaryQty;
    }

    public void setDelivaryQty(Double delivaryQty) {
        this.delivaryQty = delivaryQty;
    }

    public String getReferenceno() {
        return referenceno;
    }

    public void setReferenceno(String referenceno) {
        this.referenceno = referenceno;
    }

    public Double getRequiredQuantity() {
        return requiredQuantity;
    }

    public void setRequiredQuantity(Double requiredQuantity) {
        this.requiredQuantity = requiredQuantity;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
