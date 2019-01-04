
package in.hiaccounts.model.item;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemIPTaxDTOList implements Serializable
{

    @SerializedName("tax_Per")
    @Expose
    private long taxPer;
    @SerializedName("taxIPId")
    @Expose
    private long taxIPId;
    @SerializedName("linkedId")
    @Expose
    private long linkedId;
    @SerializedName("taxCode")
    @Expose
    private String taxCode;
    @SerializedName("taxName")
    @Expose
    private String taxName;
    @SerializedName("taxGroup")
    @Expose
    private String taxGroup;
    @SerializedName("taxDescription")
    @Expose
    private String taxDescription;
    @SerializedName("taxStatus")
    @Expose
    private String taxStatus;
    private final static long serialVersionUID = -5397793564698368549L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ItemIPTaxDTOList() {
    }

    /**
     * 
     * @param taxIPId
     * @param taxStatus
     * @param taxName
     * @param taxGroup
     * @param taxDescription
     * @param linkedId
     * @param taxPer
     * @param taxCode
     */
    public ItemIPTaxDTOList(long taxPer, long taxIPId, long linkedId, String taxCode, String taxName, String taxGroup, String taxDescription, String taxStatus) {
        super();
        this.taxPer = taxPer;
        this.taxIPId = taxIPId;
        this.linkedId = linkedId;
        this.taxCode = taxCode;
        this.taxName = taxName;
        this.taxGroup = taxGroup;
        this.taxDescription = taxDescription;
        this.taxStatus = taxStatus;
    }

    public long getTaxPer() {
        return taxPer;
    }

    public void setTaxPer(long taxPer) {
        this.taxPer = taxPer;
    }

    public long getTaxIPId() {
        return taxIPId;
    }

    public void setTaxIPId(long taxIPId) {
        this.taxIPId = taxIPId;
    }

    public long getLinkedId() {
        return linkedId;
    }

    public void setLinkedId(long linkedId) {
        this.linkedId = linkedId;
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

    public String getTaxStatus() {
        return taxStatus;
    }

    public void setTaxStatus(String taxStatus) {
        this.taxStatus = taxStatus;
    }


    @Override
    public String toString() {
        return taxName;
    }

}
