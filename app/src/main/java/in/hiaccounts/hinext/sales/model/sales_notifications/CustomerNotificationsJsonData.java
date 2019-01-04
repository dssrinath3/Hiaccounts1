package in.hiaccounts.hinext.sales.model.sales_notifications;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class CustomerNotificationsJsonData{
    @SerializedName("supplierName")
    @Expose
    public String supplierName;
    @SerializedName("totalTaxAmt")
    @Expose
    public Long totalTaxAmt;
    @SerializedName("supplierId")
    @Expose
    public Long supplierId;
    @SerializedName("subscriptionType")
    @Expose
    public String subscriptionType;
    @SerializedName("typeDoc")
    @Expose
    public String typeDoc;
    @SerializedName("totalCheckOutamt")
    @Expose
    public String totalCheckOutamt;
    @SerializedName("from_reg")
    @Expose
    public String fromReg;
    @SerializedName("to_reg")
    @Expose
    public String toReg;
    @SerializedName("piNo")
    @Expose
    public String piNo;
    @SerializedName("selectedItemsList")
    @Expose
    public List<NotificationItemList> selectedItemsList;
    @SerializedName("typeFlag")
    @Expose
    public String typeFlag;
    @SerializedName("customer_name")
    @Expose
    public String customer_name;

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Long getTotalTaxAmt() {
        return totalTaxAmt;
    }

    public void setTotalTaxAmt(Long totalTaxAmt) {
        this.totalTaxAmt = totalTaxAmt;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public String getTypeDoc() {
        return typeDoc;
    }

    public void setTypeDoc(String typeDoc) {
        this.typeDoc = typeDoc;
    }

    public String getTotalCheckOutamt() {
        return totalCheckOutamt;
    }

    public void setTotalCheckOutamt(String totalCheckOutamt) {
        this.totalCheckOutamt = totalCheckOutamt;
    }

    public String getFromReg() {
        return fromReg;
    }

    public void setFromReg(String fromReg) {
        this.fromReg = fromReg;
    }

    public String getToReg() {
        return toReg;
    }

    public void setToReg(String toReg) {
        this.toReg = toReg;
    }

    public String getPiNo() {
        return piNo;
    }

    public void setPiNo(String piNo) {
        this.piNo = piNo;
    }

    public List<NotificationItemList> getSelectedItemsList() {
        return selectedItemsList;
    }

    public void setSelectedItemsList(List<NotificationItemList> selectedItemsList) {
        this.selectedItemsList = selectedItemsList;
    }



    public String getTypeFlag() {
        return typeFlag;
    }

    public void setTypeFlag(String typeFlag) {
        this.typeFlag = typeFlag;
    }
}
