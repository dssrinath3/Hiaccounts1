package in.hiaccounts.hinext.tax.model.taxconfig;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Prateek on 8/18/2017.
 */

public class AddTax implements Serializable {

    @SerializedName("taxid")
    @Expose
    public Long taxid;

    @SerializedName("taxTypeId")
    @Expose
    public Long taxTypeId;

    @SerializedName("taxTypeName")
    @Expose
    public String taxTypeName;
    @SerializedName("taxTypeDesc")
    @Expose
    public String taxTypeDesc;
    @SerializedName("taxClassId")
    @Expose
    public Long taxClassId;
    @SerializedName("taxGroupId")
    @Expose
    public Long taxGroupId;
    @SerializedName("taxGroup")
    @Expose
    public Long taxGroup;

    @SerializedName("taxCode")
    @Expose
    public String taxCode;

    @SerializedName("taxName")
    @Expose
    public String taxName;

    @SerializedName("taxPercentage")
    @Expose
    public double taxPercentage;

    @SerializedName("taxDescription")
    @Expose
    public String taxDescription;

    @SerializedName("taxStatus")
    @Expose
    public String taxStatus;

    @SerializedName("accountCode")
    @Expose
    public String accountCode;

    @SerializedName("linkedAccountId")
    @Expose
    public String linkedAccountId;

    @SerializedName("flag")
    @Expose
    public boolean flag;

    @SerializedName("effectiveDate")
    @Expose
    public String effectiveDate;
    @SerializedName("expiryDate")
    @Expose
    public String expiryDate;

    public Long getTaxGroup() {
        return taxGroup;
    }

    public void setTaxGroup(Long taxGroup) {
        this.taxGroup = taxGroup;
    }

    public String getTaxTypeName() {
        return taxTypeName;
    }

    public void setTaxTypeName(String taxTypeName) {
        this.taxTypeName = taxTypeName;
    }

    public String getTaxTypeDesc() {
        return taxTypeDesc;
    }

    public void setTaxTypeDesc(String taxTypeDesc) {
        this.taxTypeDesc = taxTypeDesc;
    }

    public Long getTaxClassId() {
        return taxClassId;
    }

    public void setTaxClassId(Long taxClassId) {
        this.taxClassId = taxClassId;
    }

    public Long getTaxGroupId() {
        return taxGroupId;
    }

    public void setTaxGroupId(Long taxGroupId) {
        this.taxGroupId = taxGroupId;
    }

    public Long getTaxTypeId() {
        return taxTypeId;
    }

    public void setTaxTypeId(Long taxTypeId) {
        this.taxTypeId = taxTypeId;
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

    public double getTaxPercentage() {
        return taxPercentage;
    }

    public void setTaxPercentage(double taxPercentage) {
        this.taxPercentage = taxPercentage;
    }

    public String getTaxDescription() {
        return taxDescription;
    }

    public void setTaxDescription(String taxDescription) {
        this.taxDescription = taxDescription;
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

    public String getLinkedAccountId() {
        return linkedAccountId;
    }

    public void setLinkedAccountId(String linkedAccountId) {
        this.linkedAccountId = linkedAccountId;
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

    public Long getTaxid() {
        return taxid;
    }

    public void setTaxid(Long taxid) {
        this.taxid = taxid;
    }
}
