package in.hiaccounts.hinext.item.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Prateek on 7/25/2017.
 */

public class ItemIPTaxDTOList implements Serializable {

    @SerializedName("linkedId")
    @Expose
    private Object linkedId;
    @SerializedName("taxIPId")
    @Expose
    private long taxIPId;
    @SerializedName("tax_Per")
    @Expose
    private double taxPer;
    @SerializedName("taxName")
    @Expose
    private String taxName;
    @SerializedName("taxDescription")
    @Expose
    private String taxDescription;
    @SerializedName("taxCode")
    @Expose
    private String taxCode;
    @SerializedName("taxGroup")
    @Expose
    private String taxGroup;
    @SerializedName("taxStatus")
    @Expose
    private String taxStatus;

    public Object getLinkedId() {
        return linkedId;
    }

    public void setLinkedId(Object linkedId) {
        this.linkedId = linkedId;
    }

    public long getTaxIPId() {
        return taxIPId;
    }

    public void setTaxIPId(long taxIPId) {
        this.taxIPId = taxIPId;
    }

    public double getTaxPer() {
        return taxPer;
    }

    public void setTaxPer(double taxPer) {
        this.taxPer = taxPer;
    }

    public String getTaxName() {
        return taxName;
    }

    public void setTaxName(String taxName) {
        this.taxName = taxName;
    }

    public String getTaxDescription() {
        return taxDescription;
    }

    public void setTaxDescription(String taxDescription) {
        this.taxDescription = taxDescription;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getTaxGroup() {
        return taxGroup;
    }

    public void setTaxGroup(String taxGroup) {
        this.taxGroup = taxGroup;
    }

    public String getTaxStatus() {
        return taxStatus;
    }

    public void setTaxStatus(String taxStatus) {
        this.taxStatus = taxStatus;
    }


    @Override
    public String toString() {
        return taxCode+":"+taxPer;
    }
}
