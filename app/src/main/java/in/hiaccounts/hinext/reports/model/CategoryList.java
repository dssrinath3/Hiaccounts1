package in.hiaccounts.hinext.reports.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CategoryList implements Serializable {
    @SerializedName("itemCategoryId")
    @Expose
    public Long itemCategoryId;
    @SerializedName("itemCategoryCode")
    @Expose
    public String itemCategoryCode;
    @SerializedName("itemCategoryName")
    @Expose
    public String itemCategoryName;
    @SerializedName("itemCategoryDesc")
    @Expose
    public Object itemCategoryDesc;
    @SerializedName("ledgerINV")
    @Expose
    public Object ledgerINV;
    @SerializedName("ledgerCOGS")
    @Expose
    public Object ledgerCOGS;
    @SerializedName("ledgerIncome")
    @Expose
    public Object ledgerIncome;
    @SerializedName("ledgerAdjustment")
    @Expose
    public Object ledgerAdjustment;
    @SerializedName("defaultType")
    @Expose
    public String defaultType;
    @SerializedName("itemCode")
    @Expose
    public Object itemCode;
    @SerializedName("status")
    @Expose
    public String status;

    public Long getItemCategoryId() {
        return itemCategoryId;
    }

    public void setItemCategoryId(Long itemCategoryId) {
        this.itemCategoryId = itemCategoryId;
    }

    public String getItemCategoryCode() {
        return itemCategoryCode;
    }

    public void setItemCategoryCode(String itemCategoryCode) {
        this.itemCategoryCode = itemCategoryCode;
    }

    public String getItemCategoryName() {
        return itemCategoryName;
    }

    public void setItemCategoryName(String itemCategoryName) {
        this.itemCategoryName = itemCategoryName;
    }

    public Object getItemCategoryDesc() {
        return itemCategoryDesc;
    }

    public void setItemCategoryDesc(Object itemCategoryDesc) {
        this.itemCategoryDesc = itemCategoryDesc;
    }

    public Object getLedgerINV() {
        return ledgerINV;
    }

    public void setLedgerINV(Object ledgerINV) {
        this.ledgerINV = ledgerINV;
    }

    public Object getLedgerCOGS() {
        return ledgerCOGS;
    }

    public void setLedgerCOGS(Object ledgerCOGS) {
        this.ledgerCOGS = ledgerCOGS;
    }

    public Object getLedgerIncome() {
        return ledgerIncome;
    }

    public void setLedgerIncome(Object ledgerIncome) {
        this.ledgerIncome = ledgerIncome;
    }

    public Object getLedgerAdjustment() {
        return ledgerAdjustment;
    }

    public void setLedgerAdjustment(Object ledgerAdjustment) {
        this.ledgerAdjustment = ledgerAdjustment;
    }

    public String getDefaultType() {
        return defaultType;
    }

    public void setDefaultType(String defaultType) {
        this.defaultType = defaultType;
    }

    public Object getItemCode() {
        return itemCode;
    }

    public void setItemCode(Object itemCode) {
        this.itemCode = itemCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
