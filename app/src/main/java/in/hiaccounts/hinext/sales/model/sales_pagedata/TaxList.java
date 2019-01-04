
package in.hiaccounts.hinext.sales.model.sales_pagedata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TaxList implements Serializable {

    @SerializedName("taxid")
    @Expose
    public long taxid;
    @SerializedName("taxString")
    @Expose
    public String taxString;
    @SerializedName("taxTypeId")
    @Expose
    public Object taxTypeId;
    @SerializedName("taxClassId")
    @Expose
    public Object taxClassId;
    @SerializedName("linkedAccountId")
    @Expose
    public Object linkedAccountId;
    @SerializedName("taxGroup")
    @Expose
    public Object taxGroup;
    @SerializedName("taxCode")
    @Expose
    public Object taxCode;
    @SerializedName("taxName")
    @Expose
    public Object taxName;
    @SerializedName("taxPercentage")
    @Expose
    public double taxPercentage;
    @SerializedName("flag")
    @Expose
    public boolean flag;
    @SerializedName("effectiveDate")
    @Expose
    public Object effectiveDate;
    @SerializedName("expiryDate")
    @Expose
    public Object expiryDate;
    @SerializedName("taxStatus")
    @Expose
    public Object taxStatus;
    @SerializedName("accountCode")
    @Expose
    public Object accountCode;
    @SerializedName("taxDescription")
    @Expose
    public Object taxDescription;

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

    public Object getTaxTypeId() {
        return taxTypeId;
    }

    public void setTaxTypeId(Object taxTypeId) {
        this.taxTypeId = taxTypeId;
    }

    public Object getTaxClassId() {
        return taxClassId;
    }

    public void setTaxClassId(Object taxClassId) {
        this.taxClassId = taxClassId;
    }

    public Object getLinkedAccountId() {
        return linkedAccountId;
    }

    public void setLinkedAccountId(Object linkedAccountId) {
        this.linkedAccountId = linkedAccountId;
    }

    public Object getTaxGroup() {
        return taxGroup;
    }

    public void setTaxGroup(Object taxGroup) {
        this.taxGroup = taxGroup;
    }

    public Object getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(Object taxCode) {
        this.taxCode = taxCode;
    }

    public Object getTaxName() {
        return taxName;
    }

    public void setTaxName(Object taxName) {
        this.taxName = taxName;
    }

    public double getTaxPercentage() {
        return taxPercentage;
    }

    public void setTaxPercentage(double taxPercentage) {
        this.taxPercentage = taxPercentage;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Object getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Object effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Object getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Object expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Object getTaxStatus() {
        return taxStatus;
    }

    public void setTaxStatus(Object taxStatus) {
        this.taxStatus = taxStatus;
    }

    public Object getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(Object accountCode) {
        this.accountCode = accountCode;
    }

    public Object getTaxDescription() {
        return taxDescription;
    }

    public void setTaxDescription(Object taxDescription) {
        this.taxDescription = taxDescription;
    }

    @Override
    public String toString() {
        return taxString;
    }
}
