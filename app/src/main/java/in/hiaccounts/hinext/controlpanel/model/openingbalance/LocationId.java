
package in.hiaccounts.hinext.controlpanel.model.openingbalance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LocationId implements Serializable{

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
    @SerializedName("inventoryLocationFaxNo")
    @Expose
    public Object inventoryLocationFaxNo;
    @SerializedName("inventoryLocationAddress")
    @Expose
    public Object inventoryLocationAddress;
    @SerializedName("customerIdForLoc")
    @Expose
    public Object customerIdForLoc;
    @SerializedName("companyIdForLoc")
    @Expose
    public String companyIdForLoc;
    @SerializedName("supplierIdForLoc")
    @Expose
    public Object supplierIdForLoc;
    @SerializedName("gstin")
    @Expose
    public Object gstin;
    @SerializedName("inventoryLocationName")
    @Expose
    public String inventoryLocationName;
    @SerializedName("inventoryLocationId")
    @Expose
    public Long inventoryLocationId;
    @SerializedName("inventoryLocationContact")
    @Expose
    public String inventoryLocationContact;
    @SerializedName("inventoryLocationEmail")
    @Expose
    public String inventoryLocationEmail;
    @SerializedName("inventoryLocationStatus")
    @Expose
    public String inventoryLocationStatus;
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

    public Object getInventoryLocationFaxNo() {
        return inventoryLocationFaxNo;
    }

    public void setInventoryLocationFaxNo(Object inventoryLocationFaxNo) {
        this.inventoryLocationFaxNo = inventoryLocationFaxNo;
    }

    public Object getInventoryLocationAddress() {
        return inventoryLocationAddress;
    }

    public void setInventoryLocationAddress(Object inventoryLocationAddress) {
        this.inventoryLocationAddress = inventoryLocationAddress;
    }

    public Object getCustomerIdForLoc() {
        return customerIdForLoc;
    }

    public void setCustomerIdForLoc(Object customerIdForLoc) {
        this.customerIdForLoc = customerIdForLoc;
    }

    public String getCompanyIdForLoc() {
        return companyIdForLoc;
    }

    public void setCompanyIdForLoc(String companyIdForLoc) {
        this.companyIdForLoc = companyIdForLoc;
    }

    public Object getSupplierIdForLoc() {
        return supplierIdForLoc;
    }

    public void setSupplierIdForLoc(Object supplierIdForLoc) {
        this.supplierIdForLoc = supplierIdForLoc;
    }

    public Object getGstin() {
        return gstin;
    }

    public void setGstin(Object gstin) {
        this.gstin = gstin;
    }

    public String getInventoryLocationName() {
        return inventoryLocationName;
    }

    public void setInventoryLocationName(String inventoryLocationName) {
        this.inventoryLocationName = inventoryLocationName;
    }

    public Long getInventoryLocationId() {
        return inventoryLocationId;
    }

    public void setInventoryLocationId(Long inventoryLocationId) {
        this.inventoryLocationId = inventoryLocationId;
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

    public String getInventoryLocationContactPerson() {
        return inventoryLocationContactPerson;
    }

    public void setInventoryLocationContactPerson(String inventoryLocationContactPerson) {
        this.inventoryLocationContactPerson = inventoryLocationContactPerson;
    }

    @Override
    public String toString() {
        return inventoryLocationName;
    }
}
