package in.hiaccounts.hinext.inventory.model.assests;

/**
 * Created by shrinath on 11/29/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemIPTaxDTOList {

    @SerializedName("linkedId")
    @Expose
    private Long linkedId;
    @SerializedName("taxDescription")
    @Expose
    private String taxDescription;
    @SerializedName("taxIPId")
    @Expose
    private Long taxIPId;
    @SerializedName("taxCode")
    @Expose
    private String taxCode;
    @SerializedName("tax_Per")
    @Expose
    private Double taxPer;
    @SerializedName("taxStatus")
    @Expose
    private String taxStatus;
    @SerializedName("taxGroup")
    @Expose
    private String taxGroup;
    @SerializedName("taxName")
    @Expose
    private String taxName;

    public Long getLinkedId() {
        return linkedId;
    }

    public void setLinkedId(Long linkedId) {
        this.linkedId = linkedId;
    }

    public String getTaxDescription() {
        return taxDescription;
    }

    public void setTaxDescription(String taxDescription) {
        this.taxDescription = taxDescription;
    }

    public Long getTaxIPId() {
        return taxIPId;
    }

    public void setTaxIPId(Long taxIPId) {
        this.taxIPId = taxIPId;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public Double getTaxPer() {
        return taxPer;
    }

    public void setTaxPer(Double taxPer) {
        this.taxPer = taxPer;
    }

    public String getTaxStatus() {
        return taxStatus;
    }

    public void setTaxStatus(String taxStatus) {
        this.taxStatus = taxStatus;
    }

    public String getTaxGroup() {
        return taxGroup;
    }

    public void setTaxGroup(String taxGroup) {
        this.taxGroup = taxGroup;
    }

    public String getTaxName() {
        return taxName;
    }

    public void setTaxName(String taxName) {
        this.taxName = taxName;
    }
    @Override
    public String toString() {
        return taxCode+":"+taxPer;
    }
}