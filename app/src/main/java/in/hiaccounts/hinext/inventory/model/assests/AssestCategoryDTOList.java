package in.hiaccounts.hinext.inventory.model.assests;

/**
 * Created by shrinath on 11/29/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AssestCategoryDTOList implements Serializable {

    @SerializedName("assestsCategoryId")
    @Expose
    private Long assestsCategoryId;
    @SerializedName("assestsCategoryCode")
    @Expose
    private String assestsCategoryCode;
    @SerializedName("assestsCategoryName")
    @Expose
    private String assestsCategoryName;
    @SerializedName("assestsCategoryDesc")
    @Expose
    private String assestsCategoryDesc;
    @SerializedName("ledgerINV")
    @Expose
    private String ledgerINV;
    @SerializedName("ledgerCOGS")
    @Expose
    private String ledgerCOGS;
    @SerializedName("ledgerIncome")
    @Expose
    private String ledgerIncome;
    @SerializedName("ledgerAdjustment")
    @Expose
    private String ledgerAdjustment;
    @SerializedName("restaurentStatus")
    @Expose
    private String restaurentStatus;

    public Long getAssestsCategoryId() {
        return assestsCategoryId;
    }

    public void setAssestsCategoryId(Long assestsCategoryId) {
        this.assestsCategoryId = assestsCategoryId;
    }

    public String getAssestsCategoryCode() {
        return assestsCategoryCode;
    }

    public void setAssestsCategoryCode(String assestsCategoryCode) {
        this.assestsCategoryCode = assestsCategoryCode;
    }

    public String getAssestsCategoryName() {
        return assestsCategoryName;
    }

    public void setAssestsCategoryName(String assestsCategoryName) {
        this.assestsCategoryName = assestsCategoryName;
    }

    public String getAssestsCategoryDesc() {
        return assestsCategoryDesc;
    }

    public void setAssestsCategoryDesc(String assestsCategoryDesc) {
        this.assestsCategoryDesc = assestsCategoryDesc;
    }

    public String getLedgerINV() {
        return ledgerINV;
    }

    public void setLedgerINV(String ledgerINV) {
        this.ledgerINV = ledgerINV;
    }

    public String getLedgerCOGS() {
        return ledgerCOGS;
    }

    public void setLedgerCOGS(String ledgerCOGS) {
        this.ledgerCOGS = ledgerCOGS;
    }

    public String getLedgerIncome() {
        return ledgerIncome;
    }

    public void setLedgerIncome(String ledgerIncome) {
        this.ledgerIncome = ledgerIncome;
    }

    public String getLedgerAdjustment() {
        return ledgerAdjustment;
    }

    public void setLedgerAdjustment(String ledgerAdjustment) {
        this.ledgerAdjustment = ledgerAdjustment;
    }

    public String getRestaurentStatus() {
        return restaurentStatus;
    }

    public void setRestaurentStatus(String restaurentStatus) {
        this.restaurentStatus = restaurentStatus;
    }

    @Override
    public String toString() {
        return assestsCategoryName;
    }
}
