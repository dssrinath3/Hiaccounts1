
package in.hiaccounts.model.item;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemCategoryDTOList implements Serializable
{

    @SerializedName("ledgerCOGS")
    @Expose
    private Object ledgerCOGS;
    @SerializedName("ledgerINV")
    @Expose
    private Object ledgerINV;
    @SerializedName("ledgerIncome")
    @Expose
    private Object ledgerIncome;
    @SerializedName("itemCategoryId")
    @Expose
    private long itemCategoryId;
    @SerializedName("itemCategoryCode")
    @Expose
    private String itemCategoryCode;
    @SerializedName("itemCategoryName")
    @Expose
    private String itemCategoryName;
    @SerializedName("ledgerAdjustment")
    @Expose
    private Object ledgerAdjustment;
    @SerializedName("itemCategoryDesc")
    @Expose
    private Object itemCategoryDesc;
    private final static long serialVersionUID = -3480559450208599980L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ItemCategoryDTOList() {
    }

    /**
     * 
     * @param itemCategoryDesc
     * @param itemCategoryName
     * @param ledgerAdjustment
     * @param ledgerINV
     * @param itemCategoryCode
     * @param itemCategoryId
     * @param ledgerIncome
     * @param ledgerCOGS
     */
    public ItemCategoryDTOList(Object ledgerCOGS, Object ledgerINV, Object ledgerIncome, long itemCategoryId, String itemCategoryCode, String itemCategoryName, Object ledgerAdjustment, Object itemCategoryDesc) {
        super();
        this.ledgerCOGS = ledgerCOGS;
        this.ledgerINV = ledgerINV;
        this.ledgerIncome = ledgerIncome;
        this.itemCategoryId = itemCategoryId;
        this.itemCategoryCode = itemCategoryCode;
        this.itemCategoryName = itemCategoryName;
        this.ledgerAdjustment = ledgerAdjustment;
        this.itemCategoryDesc = itemCategoryDesc;
    }

    public Object getLedgerCOGS() {
        return ledgerCOGS;
    }

    public void setLedgerCOGS(Object ledgerCOGS) {
        this.ledgerCOGS = ledgerCOGS;
    }

    public Object getLedgerINV() {
        return ledgerINV;
    }

    public void setLedgerINV(Object ledgerINV) {
        this.ledgerINV = ledgerINV;
    }

    public Object getLedgerIncome() {
        return ledgerIncome;
    }

    public void setLedgerIncome(Object ledgerIncome) {
        this.ledgerIncome = ledgerIncome;
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

    public Object getLedgerAdjustment() {
        return ledgerAdjustment;
    }

    public void setLedgerAdjustment(Object ledgerAdjustment) {
        this.ledgerAdjustment = ledgerAdjustment;
    }

    public Object getItemCategoryDesc() {
        return itemCategoryDesc;
    }

    public void setItemCategoryDesc(Object itemCategoryDesc) {
        this.itemCategoryDesc = itemCategoryDesc;
    }

    @Override
    public String toString() {
        return itemCategoryName;
    }
}
