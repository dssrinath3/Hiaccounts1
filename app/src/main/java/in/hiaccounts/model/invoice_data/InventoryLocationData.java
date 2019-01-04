
package in.hiaccounts.model.invoice_data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class InventoryLocationData implements Serializable {


    @SerializedName("inventoryLocationName")
    @Expose
    private String inventoryLocationName;
    @SerializedName("inventoryLocationId")
    @Expose
    private long inventoryLocationId;
    @SerializedName("useraccount_id")
    @Expose
    private String useraccountId;
    @SerializedName("inventoryLocationContact")
    @Expose
    private String inventoryLocationContact;
    @SerializedName("inventoryLocationEmail")
    @Expose
    private String inventoryLocationEmail;
    @SerializedName("inventoryLocationStatus")
    @Expose
    private String inventoryLocationStatus;
    @SerializedName("inventoryLocationFaxNo")
    @Expose
    private String inventoryLocationFaxNo;
    @SerializedName("inventoryLocationAddress")
    @Expose
    private String inventoryLocationAddress;
    @SerializedName("companyId")
    @Expose
    private CompanyId companyId;
    @SerializedName("inventoryLocationContactPerson")
    @Expose
    private String inventoryLocationContactPerson;

    /**
     * No args constructor for use in serialization
     *
     */
    public InventoryLocationData() {
    }

    /**
     *
     * @param inventoryLocationContactPerson
     * @param inventoryLocationAddress
     * @param inventoryLocationFaxNo
     * @param inventoryLocationContact
     * @param inventoryLocationStatus
     * @param useraccountId
     * @param inventoryLocationName
     * @param companyId
     * @param inventoryLocationEmail
     * @param inventoryLocationId
     */
    public InventoryLocationData(String inventoryLocationName, long inventoryLocationId, String useraccountId, String inventoryLocationContact, String inventoryLocationEmail, String inventoryLocationStatus, String inventoryLocationFaxNo, String inventoryLocationAddress, CompanyId companyId, String inventoryLocationContactPerson) {
        super();
        this.inventoryLocationName = inventoryLocationName;
        this.inventoryLocationId = inventoryLocationId;
        this.useraccountId = useraccountId;
        this.inventoryLocationContact = inventoryLocationContact;
        this.inventoryLocationEmail = inventoryLocationEmail;
        this.inventoryLocationStatus = inventoryLocationStatus;
        this.inventoryLocationFaxNo = inventoryLocationFaxNo;
        this.inventoryLocationAddress = inventoryLocationAddress;
        this.companyId = companyId;
        this.inventoryLocationContactPerson = inventoryLocationContactPerson;
    }

    public String getInventoryLocationName() {
        return inventoryLocationName;
    }

    public void setInventoryLocationName(String inventoryLocationName) {
        this.inventoryLocationName = inventoryLocationName;
    }

    public long getInventoryLocationId() {
        return inventoryLocationId;
    }

    public void setInventoryLocationId(long inventoryLocationId) {
        this.inventoryLocationId = inventoryLocationId;
    }

    public String getUseraccountId() {
        return useraccountId;
    }

    public void setUseraccountId(String useraccountId) {
        this.useraccountId = useraccountId;
    }

    public String getInventoryLocationContact() {
        return inventoryLocationContact;
    }

    public void setInventoryLocationContact(String inventoryLocationContact) {
        this.inventoryLocationContact = inventoryLocationContact;
    }

    public String getInventoryLocationEmail() {
        return inventoryLocationEmail;
    }

    public void setInventoryLocationEmail(String inventoryLocationEmail) {
        this.inventoryLocationEmail = inventoryLocationEmail;
    }

    public String getInventoryLocationStatus() {
        return inventoryLocationStatus;
    }

    public void setInventoryLocationStatus(String inventoryLocationStatus) {
        this.inventoryLocationStatus = inventoryLocationStatus;
    }

    public String getInventoryLocationFaxNo() {
        return inventoryLocationFaxNo;
    }

    public void setInventoryLocationFaxNo(String inventoryLocationFaxNo) {
        this.inventoryLocationFaxNo = inventoryLocationFaxNo;
    }

    public String getInventoryLocationAddress() {
        return inventoryLocationAddress;
    }

    public void setInventoryLocationAddress(String inventoryLocationAddress) {
        this.inventoryLocationAddress = inventoryLocationAddress;
    }

    public CompanyId getCompanyId() {
        return companyId;
    }

    public void setCompanyId(CompanyId companyId) {
        this.companyId = companyId;
    }

    public String getInventoryLocationContactPerson() {
        return inventoryLocationContactPerson;
    }

    public void setInventoryLocationContactPerson(String inventoryLocationContactPerson) {
        this.inventoryLocationContactPerson = inventoryLocationContactPerson;
    }

}
