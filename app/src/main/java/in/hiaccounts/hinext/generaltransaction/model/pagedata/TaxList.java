
package in.hiaccounts.hinext.generaltransaction.model.pagedata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TaxList implements Serializable {

    @SerializedName("taxid")
    @Expose
    private long taxid;
    @SerializedName("taxString")
    @Expose
    private String taxString;
    @SerializedName("taxTypeId")
    @Expose
    private long taxTypeId;
    @SerializedName("taxClassId")
    @Expose
    private long taxClassId;
    @SerializedName("linkedAccountId")
    @Expose
    private long linkedAccountId;
    @SerializedName("taxGroup")
    @Expose
    private String taxGroup;
    @SerializedName("taxCode")
    @Expose
    private String taxCode;
    @SerializedName("taxName")
    @Expose
    private String taxName;
    @SerializedName("taxPercentage")
    @Expose
    private String taxPercentage;
    @SerializedName("flag")
    @Expose
    private boolean flag;
    @SerializedName("effectiveDate")
    @Expose
    private String effectiveDate;
    @SerializedName("expiryDate")
    @Expose
    private String expiryDate;
    @SerializedName("taxStatus")
    @Expose
    private String taxStatus;
    @SerializedName("accountCode")
    @Expose
    private String accountCode;

    /**
     * No args constructor for use in serialization
     * 
     */
    public TaxList() {
    }

    /**
     * 
     * @param taxStatus
     * @param taxName
     * @param expiryDate
     * @param taxClassId
     * @param taxGroup
     * @param taxString
     * @param taxTypeId
     * @param taxCode
     * @param accountCode
     * @param flag
     * @param linkedAccountId
     * @param taxPercentage
     * @param effectiveDate
     * @param taxid
     */
    public TaxList(long taxid, String taxString, long taxTypeId, long taxClassId, long linkedAccountId, String taxGroup, String taxCode, String taxName, String taxPercentage, boolean flag, String effectiveDate, String expiryDate, String taxStatus, String accountCode) {
        this.taxid = taxid;
        this.taxString = taxString;
        this.taxTypeId = taxTypeId;
        this.taxClassId = taxClassId;
        this.linkedAccountId = linkedAccountId;
        this.taxGroup = taxGroup;
        this.taxCode = taxCode;
        this.taxName = taxName;
        this.taxPercentage = taxPercentage;
        this.flag = flag;
        this.effectiveDate = effectiveDate;
        this.expiryDate = expiryDate;
        this.taxStatus = taxStatus;
        this.accountCode = accountCode;
    }

    public long getTaxid() {
        return taxid;
    }

    public void setTaxid(long taxid) {
        this.taxid = taxid;
    }

    public String getTaxString() {
        return taxString;
    }

    public void setTaxString(String taxString) {
        this.taxString = taxString;
    }

    public long getTaxTypeId() {
        return taxTypeId;
    }

    public void setTaxTypeId(long taxTypeId) {
        this.taxTypeId = taxTypeId;
    }

    public long getTaxClassId() {
        return taxClassId;
    }

    public void setTaxClassId(long taxClassId) {
        this.taxClassId = taxClassId;
    }

    public long getLinkedAccountId() {
        return linkedAccountId;
    }

    public void setLinkedAccountId(long linkedAccountId) {
        this.linkedAccountId = linkedAccountId;
    }

    public String getTaxGroup() {
        return taxGroup;
    }

    public void setTaxGroup(String taxGroup) {
        this.taxGroup = taxGroup;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getTaxName() {
        return taxName;
    }

    public void setTaxName(String taxName) {
        this.taxName = taxName;
    }

    public String getTaxPercentage() {
        return taxPercentage;
    }

    public void setTaxPercentage(String taxPercentage) {
        this.taxPercentage = taxPercentage;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getTaxStatus() {
        return taxStatus;
    }

    public void setTaxStatus(String taxStatus) {
        this.taxStatus = taxStatus;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }
    @Override
    public String toString() {
        return taxString;
    }

}
