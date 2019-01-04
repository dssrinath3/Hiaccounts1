package in.hiaccounts.hinext.inventory.model.assestscategory;

/**
 * Created by shrinath on 12/4/2017.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AssestsCategory {

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
    @SerializedName("restaurentStatus")
    @Expose
    private Boolean restaurentStatus;
    @SerializedName("status")
    @Expose
    private String status;


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

    public Boolean getRestaurentStatus() {
        return restaurentStatus;
    }

    public void setRestaurentStatus(Boolean restaurentStatus) {
        this.restaurentStatus = restaurentStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
