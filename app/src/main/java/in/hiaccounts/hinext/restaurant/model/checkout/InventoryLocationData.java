
package in.hiaccounts.hinext.restaurant.model.checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class InventoryLocationData implements Serializable {

    @SerializedName("stateId")
    @Expose
    public StateId stateId;
    @SerializedName("companyId")
    @Expose
    public CompanyId companyId;
    @SerializedName("useraccount_id")
    @Expose
    public Object useraccountId;
    @SerializedName("nextref")
    @Expose
    public Object nextref;
    @SerializedName("inventoryLocationId")
    @Expose
    public long inventoryLocationId;
    @SerializedName("customerIdForLoc")
    @Expose
    public Object customerIdForLoc;
    @SerializedName("supplierIdForLoc")
    @Expose
    public Object supplierIdForLoc;
    @SerializedName("inventoryLocationName")
    @Expose
    public String inventoryLocationName;
    @SerializedName("companyIdForLoc")
    @Expose
    public String companyIdForLoc;
    @SerializedName("inventoryLocationAddress")
    @Expose
    public Object inventoryLocationAddress;
    @SerializedName("inventoryLocationContact")
    @Expose
    public String inventoryLocationContact;
    @SerializedName("inventoryLocationEmail")
    @Expose
    public String inventoryLocationEmail;
    @SerializedName("inventoryLocationFaxNo")
    @Expose
    public Object inventoryLocationFaxNo;
    @SerializedName("inventoryLocationStatus")
    @Expose
    public String inventoryLocationStatus;
    @SerializedName("gstin")
    @Expose
    public Object gstin;
    @SerializedName("inventoryLocationContactPerson")
    @Expose
    public String inventoryLocationContactPerson;

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

    public Object getUseraccountId() {
        return useraccountId;
    }

    public void setUseraccountId(Object useraccountId) {
        this.useraccountId = useraccountId;
    }

    public Object getNextref() {
        return nextref;
    }

    public void setNextref(Object nextref) {
        this.nextref = nextref;
    }

    public long getInventoryLocationId() {
        return inventoryLocationId;
    }

    public void setInventoryLocationId(long inventoryLocationId) {
        this.inventoryLocationId = inventoryLocationId;
    }

    public Object getCustomerIdForLoc() {
        return customerIdForLoc;
    }

    public void setCustomerIdForLoc(Object customerIdForLoc) {
        this.customerIdForLoc = customerIdForLoc;
    }

    public Object getSupplierIdForLoc() {
        return supplierIdForLoc;
    }

    public void setSupplierIdForLoc(Object supplierIdForLoc) {
        this.supplierIdForLoc = supplierIdForLoc;
    }

    public String getInventoryLocationName() {
        return inventoryLocationName;
    }

    public void setInventoryLocationName(String inventoryLocationName) {
        this.inventoryLocationName = inventoryLocationName;
    }

    public String getCompanyIdForLoc() {
        return companyIdForLoc;
    }

    public void setCompanyIdForLoc(String companyIdForLoc) {
        this.companyIdForLoc = companyIdForLoc;
    }

    public Object getInventoryLocationAddress() {
        return inventoryLocationAddress;
    }

    public void setInventoryLocationAddress(Object inventoryLocationAddress) {
        this.inventoryLocationAddress = inventoryLocationAddress;
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

    public Object getInventoryLocationFaxNo() {
        return inventoryLocationFaxNo;
    }

    public void setInventoryLocationFaxNo(Object inventoryLocationFaxNo) {
        this.inventoryLocationFaxNo = inventoryLocationFaxNo;
    }

    public String getInventoryLocationStatus() {
        return inventoryLocationStatus;
    }

    public void setInventoryLocationStatus(String inventoryLocationStatus) {
        this.inventoryLocationStatus = inventoryLocationStatus;
    }

    public Object getGstin() {
        return gstin;
    }

    public void setGstin(Object gstin) {
        this.gstin = gstin;
    }

    public String getInventoryLocationContactPerson() {
        return inventoryLocationContactPerson;
    }

    public void setInventoryLocationContactPerson(String inventoryLocationContactPerson) {
        this.inventoryLocationContactPerson = inventoryLocationContactPerson;
    }
}
