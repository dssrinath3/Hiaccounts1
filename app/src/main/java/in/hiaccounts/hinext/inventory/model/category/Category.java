package in.hiaccounts.hinext.inventory.model.category;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Prateek on 7/8/2017.
 */

public class Category {
    @SerializedName("itemCategoryId")
    @Expose
    private long itemCategoryId;
    @SerializedName("itemCategoryCode")
    @Expose
    private String itemCategoryCode;
    @SerializedName("itemCategoryName")
    @Expose
    private String itemCategoryName;
    @SerializedName("itemCategoryDesc")
    @Expose
    private String itemCategoryDesc;
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
    @SerializedName("defaultType")
    @Expose
    private Boolean defaultType;
    @SerializedName("status")
    @Expose
    private String status;


    /**
     * No args constructor for use in serialization
     *
     */
    public Category() {
    }

/**
 *
 * @param itemCategoryDesc
 * @param itemCategoryName
 * @param ledgerAdjustment
 * @param ledgerINV
 * @param ledgerIncome
 * @param ledgerCOGS
 * @param itemCategoryCode
 * @param itemCategoryId
 */
    public Category(long itemCategoryId, String itemCategoryCode, String itemCategoryName, String itemCategoryDesc, Object ledgerINV, Object ledgerCOGS, Object ledgerIncome, Object ledgerAdjustment) {
        this.itemCategoryId = itemCategoryId;
        this.itemCategoryCode = itemCategoryCode;
        this.itemCategoryName = itemCategoryName;
        this.itemCategoryDesc = itemCategoryDesc;
        this.ledgerINV = ledgerINV;
        this.ledgerCOGS = ledgerCOGS;
        this.ledgerIncome = ledgerIncome;
        this.ledgerAdjustment = ledgerAdjustment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getItemCategoryId() {
        return itemCategoryId;
    }

    public void setItemCategoryId(long itemCategoryId) {
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

    public String getItemCategoryDesc() {
        return itemCategoryDesc;
    }

    public void setItemCategoryDesc(String itemCategoryDesc) {
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
    public Boolean getDefaultType() {
        return defaultType;
    }

    public void setDefaultType(Boolean defaultType) {
        this.defaultType = defaultType;
    }
}
