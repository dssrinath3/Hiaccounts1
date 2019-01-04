package in.hiaccounts.hinext.sales.model.sales_notifications;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SelectPendingNotifications implements Serializable {
    @SerializedName("subscriptionType")
    @Expose
    public String subscriptionType;
    @SerializedName("selectedItemsdata")
    @Expose
    public SelectedItemsdata selectedItemsdata;
    @SerializedName("piNo")
    @Expose
    public String piNo;
    @SerializedName("piStatus")
    @Expose
    public String piStatus;
    @SerializedName("totalCheckOutamt")
    @Expose
    public Double totalCheckOutamt;
    @SerializedName("totalTaxAmt")
    @Expose
    public Double totalTaxAmt;
    @SerializedName("typeDoc")
    @Expose
    public String typeDoc;
    @SerializedName("typeFlag")
    @Expose
    public String typeFlag;
    @SerializedName("to_reg")
    @Expose
    public String to_reg;
    @SerializedName("from_reg")
    @Expose
    public String from_reg;
    @SerializedName("supplierId")
    @Expose
    public Long supplierId;
    @SerializedName("supplierName")
    @Expose
    public String supplierName;
    @SerializedName("custNotiId")
    @Expose
    public String custNotiId;
    private long timestamp;

    public SelectedItemsdata getSelectedItemsdata() {
        return selectedItemsdata;
    }

    public void setSelectedItemsdata(SelectedItemsdata selectedItemsdata) {
        this.selectedItemsdata = selectedItemsdata;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public String getPiNo() {
        return piNo;
    }

    public void setPiNo(String piNo) {
        this.piNo = piNo;
    }

    public String getPiStatus() {
        return piStatus;
    }

    public void setPiStatus(String piStatus) {
        this.piStatus = piStatus;
    }

    public Double getTotalCheckOutamt() {
        return totalCheckOutamt;
    }

    public void setTotalCheckOutamt(Double totalCheckOutamt) {
        this.totalCheckOutamt = totalCheckOutamt;
    }

    public Double getTotalTaxAmt() {
        return totalTaxAmt;
    }

    public void setTotalTaxAmt(Double totalTaxAmt) {
        this.totalTaxAmt = totalTaxAmt;
    }

    public String getTypeDoc() {
        return typeDoc;
    }

    public void setTypeDoc(String typeDoc) {
        this.typeDoc = typeDoc;
    }

    public String getTypeFlag() {
        return typeFlag;
    }

    public void setTypeFlag(String typeFlag) {
        this.typeFlag = typeFlag;
    }

    public String getTo_reg() {
        return to_reg;
    }

    public void setTo_reg(String to_reg) {
        this.to_reg = to_reg;
    }

    public String getFrom_reg() {
        return from_reg;
    }

    public void setFrom_reg(String from_reg) {
        this.from_reg = from_reg;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getCustNotiId() {
        return custNotiId;
    }

    public void setCustNotiId(String custNotiId) {
        this.custNotiId = custNotiId;
    }
}
