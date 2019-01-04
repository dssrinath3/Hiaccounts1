
package in.hiaccounts.hinext.restaurant.model.restaurant_pagedata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ItemCategory implements Serializable{

    @SerializedName("itemCategoryId")
    @Expose
    private Long itemCategoryId;
    @SerializedName("itemCategoryCode")
    @Expose
    private String itemCategoryCode;
    @SerializedName("itemCategoryName")
    @Expose
    private String itemCategoryName;
    @SerializedName("itemCategoryDesc")
    @Expose
    private Object itemCategoryDesc;
    @SerializedName("companyid")
    @Expose
    private Object companyid;
    @SerializedName("locationId")
    @Expose
    private Object locationId;
    @SerializedName("useraccount_id")
    @Expose
    private Object useraccountId;
    @SerializedName("ledgerINV")
    @Expose
    private Object ledgerINV;
    @SerializedName("ledgerCOGS")
    @Expose
    private Object ledgerCOGS;
    @SerializedName("ledgerIncome")
    @Expose
    private Object ledgerIncome;
    @SerializedName("ledgerAdjustment")
    @Expose
    private Object ledgerAdjustment;
    @SerializedName("status")
    @Expose
    private Object status;
    @SerializedName("defaultType")
    @Expose
    private String defaultType;

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

    public Object getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Object companyid) {
        this.companyid = companyid;
    }

    public Object getLocationId() {
        return locationId;
    }

    public void setLocationId(Object locationId) {
        this.locationId = locationId;
    }

    public Object getUseraccountId() {
        return useraccountId;
    }

    public void setUseraccountId(Object useraccountId) {
        this.useraccountId = useraccountId;
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

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public String getDefaultType() {
        return defaultType;
    }

    public void setDefaultType(String defaultType) {
        this.defaultType = defaultType;
    }
}
