
package in.hiaccounts.hinext.tax.model.taxconfig;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TaxDatum implements Serializable{

    @SerializedName("taxTypeId")
    @Expose
    public TaxTypeDatum taxTypeId;
    @SerializedName("gstITax")
    @Expose
    public Object gstITax;
    @SerializedName("suspendGstITax")
    @Expose
    public Object suspendGstITax;
    @SerializedName("linkedId")
    @Expose
    public LinkedId linkedId;
    @SerializedName("flag")
    @Expose
    public boolean flag;
    @SerializedName("companyId")
    @Expose
    public Object companyId;
    @SerializedName("effectiveDate")
    @Expose
    public Object effectiveDate;
    @SerializedName("expiryDate")
    @Expose
    public Object expiryDate;
    @SerializedName("taxId")
    @Expose
    public long taxId;
    @SerializedName("tax_Per")
    @Expose
    public double taxPer;
    @SerializedName("taxCode")
    @Expose
    public String taxCode;
    @SerializedName("taxStatus")
    @Expose
    public String taxStatus;
    @SerializedName("taxName")
    @Expose
    public String taxName;
    @SerializedName("taxGroup")
    @Expose
    public String taxGroup;
    @SerializedName("taxDescription")
    @Expose
    public String taxDescription;
    @SerializedName("tcid")
    @Expose
    public Tcid tcid;
    @SerializedName("accountCode")
    @Expose
    public Object accountCode;
    @SerializedName("taxAccountCode")
    @Expose
    public Object taxAccountCode;
    @SerializedName("gstTypeId")
    @Expose
    public Object gstTypeId;


    boolean select;

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public TaxTypeDatum getTaxTypeId() {
        return taxTypeId;
    }

    public void setTaxTypeId(TaxTypeDatum taxTypeId) {
        this.taxTypeId = taxTypeId;
    }

    public Object getGstITax() {
        return gstITax;
    }

    public void setGstITax(Object gstITax) {
        this.gstITax = gstITax;
    }

    public Object getSuspendGstITax() {
        return suspendGstITax;
    }

    public void setSuspendGstITax(Object suspendGstITax) {
        this.suspendGstITax = suspendGstITax;
    }

    public LinkedId getLinkedId() {
        return linkedId;
    }

    public void setLinkedId(LinkedId linkedId) {
        this.linkedId = linkedId;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Object getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Object companyId) {
        this.companyId = companyId;
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

    public long getTaxId() {
        return taxId;
    }

    public void setTaxId(long taxId) {
        this.taxId = taxId;
    }

    public double getTaxPer() {
        return taxPer;
    }

    public void setTaxPer(double taxPer) {
        this.taxPer = taxPer;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getTaxStatus() {
        return taxStatus;
    }

    public void setTaxStatus(String taxStatus) {
        this.taxStatus = taxStatus;
    }

    public String getTaxName() {
        return taxName;
    }

    public void setTaxName(String taxName) {
        this.taxName = taxName;
    }

    public String getTaxGroup() {
        return taxGroup;
    }

    public void setTaxGroup(String taxGroup) {
        this.taxGroup = taxGroup;
    }

    public String getTaxDescription() {
        return taxDescription;
    }

    public void setTaxDescription(String taxDescription) {
        this.taxDescription = taxDescription;
    }

    public Tcid getTcid() {
        return tcid;
    }

    public void setTcid(Tcid tcid) {
        this.tcid = tcid;
    }

    public Object getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(Object accountCode) {
        this.accountCode = accountCode;
    }

    public Object getTaxAccountCode() {
        return taxAccountCode;
    }

    public void setTaxAccountCode(Object taxAccountCode) {
        this.taxAccountCode = taxAccountCode;
    }

    public Object getGstTypeId() {
        return gstTypeId;
    }

    public void setGstTypeId(Object gstTypeId) {
        this.gstTypeId = gstTypeId;
    }


    @Override
    public String toString() {
        return taxDescription;
    }
}
