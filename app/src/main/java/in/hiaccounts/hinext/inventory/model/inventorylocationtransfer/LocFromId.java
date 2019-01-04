package in.hiaccounts.hinext.inventory.model.inventorylocationtransfer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by administrator on 2/2/18.
 */

public class LocFromId implements Serializable {
    @SerializedName("stateId")
    @Expose
    private StateId stateId;
    @SerializedName("companyId")
    @Expose
    private CompanyId companyId;
    @SerializedName("useraccount_id")
    @Expose
    private String useraccountId;
    @SerializedName("nextref")
    @Expose
    private Integer nextref;
    @SerializedName("prefix")
    @Expose
    private String prefix;
    @SerializedName("inventoryLocationContactPerson")
    @Expose
    private String inventoryLocationContactPerson;
    @SerializedName("gstin")
    @Expose
    private String gstin;
    @SerializedName("inventoryLocationAddress")
    @Expose
    private String inventoryLocationAddress;
    @SerializedName("supplierIdForLoc")
    @Expose
    private Object supplierIdForLoc;
    @SerializedName("inventoryLocationContact")
    @Expose
    private String inventoryLocationContact;
    @SerializedName("inventoryLocationName")
    @Expose
    private String inventoryLocationName;
    @SerializedName("customerIdForLoc")
    @Expose
    private String customerIdForLoc;
    @SerializedName("inventoryLocationStatus")
    @Expose
    private String inventoryLocationStatus;
    @SerializedName("inventoryLocationId")
    @Expose
    private Long inventoryLocationId;
    @SerializedName("companyIdForLoc")
    @Expose
    private String companyIdForLoc;
    @SerializedName("inventoryLocationEmail")
    @Expose
    private String inventoryLocationEmail;
    @SerializedName("inventoryLocationFaxNo")
    @Expose
    private Object inventoryLocationFaxNo;

    public StateId getStateId() {
        return stateId;
    }

    public void setStateId(StateId stateId) {
        this.stateId = stateId;
    }

    public CompanyId getCompanyId() {
        return companyId;
    }

    public void setCompanyId(CompanyId companyId) {
        this.companyId = companyId;
    }

    public String getUseraccountId() {
        return useraccountId;
    }

    public void setUseraccountId(String useraccountId) {
        this.useraccountId = useraccountId;
    }

    public Integer getNextref() {
        return nextref;
    }

    public void setNextref(Integer nextref) {
        this.nextref = nextref;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getInventoryLocationContactPerson() {
        return inventoryLocationContactPerson;
    }

    public void setInventoryLocationContactPerson(String inventoryLocationContactPerson) {
        this.inventoryLocationContactPerson = inventoryLocationContactPerson;
    }

    public String getGstin() {
        return gstin;
    }

    public void setGstin(String gstin) {
        this.gstin = gstin;
    }

    public String getInventoryLocationAddress() {
        return inventoryLocationAddress;
    }

    public void setInventoryLocationAddress(String inventoryLocationAddress) {
        this.inventoryLocationAddress = inventoryLocationAddress;
    }

    public Object getSupplierIdForLoc() {
        return supplierIdForLoc;
    }

    public void setSupplierIdForLoc(Object supplierIdForLoc) {
        this.supplierIdForLoc = supplierIdForLoc;
    }

    public String getInventoryLocationContact() {
        return inventoryLocationContact;
    }

    public void setInventoryLocationContact(String inventoryLocationContact) {
        this.inventoryLocationContact = inventoryLocationContact;
    }

    public String getInventoryLocationName() {
        return inventoryLocationName;
    }

    public void setInventoryLocationName(String inventoryLocationName) {
        this.inventoryLocationName = inventoryLocationName;
    }

    public String getCustomerIdForLoc() {
        return customerIdForLoc;
    }

    public void setCustomerIdForLoc(String customerIdForLoc) {
        this.customerIdForLoc = customerIdForLoc;
    }

    public String getInventoryLocationStatus() {
        return inventoryLocationStatus;
    }

    public void setInventoryLocationStatus(String inventoryLocationStatus) {
        this.inventoryLocationStatus = inventoryLocationStatus;
    }

    public Long getInventoryLocationId() {
        return inventoryLocationId;
    }

    public void setInventoryLocationId(Long inventoryLocationId) {
        this.inventoryLocationId = inventoryLocationId;
    }

    public String getCompanyIdForLoc() {
        return companyIdForLoc;
    }

    public void setCompanyIdForLoc(String companyIdForLoc) {
        this.companyIdForLoc = companyIdForLoc;
    }

    public String getInventoryLocationEmail() {
        return inventoryLocationEmail;
    }

    public void setInventoryLocationEmail(String inventoryLocationEmail) {
        this.inventoryLocationEmail = inventoryLocationEmail;
    }

    public Object getInventoryLocationFaxNo() {
        return inventoryLocationFaxNo;
    }

    public void setInventoryLocationFaxNo(Object inventoryLocationFaxNo) {
        this.inventoryLocationFaxNo = inventoryLocationFaxNo;
    }
}
