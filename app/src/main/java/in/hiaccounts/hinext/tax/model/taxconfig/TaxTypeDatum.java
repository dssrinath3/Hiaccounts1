
package in.hiaccounts.hinext.tax.model.taxconfig;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TaxTypeDatum implements Serializable{

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
    public TaxClassId taxClassId;
    @SerializedName("taxGroupId")
    @Expose
    public TaxGroupId taxGroupId;
    @SerializedName("flag")
    @Expose
    public boolean flag;
    @SerializedName("companyId")
    @Expose
    public Object companyId;

    public Long getTaxTypeId() {
        return taxTypeId;
    }

    public void setTaxTypeId(Long taxTypeId) {
        this.taxTypeId = taxTypeId;
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

    public TaxClassId getTaxClassId() {
        return taxClassId;
    }

    public void setTaxClassId(TaxClassId taxClassId) {
        this.taxClassId = taxClassId;
    }

    public TaxGroupId getTaxGroupId() {
        return taxGroupId;
    }

    public void setTaxGroupId(TaxGroupId taxGroupId) {
        this.taxGroupId = taxGroupId;
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

    @Override
    public String toString() {
        return taxTypeName;
    }
}
