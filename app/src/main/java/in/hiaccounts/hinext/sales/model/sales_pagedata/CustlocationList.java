
package in.hiaccounts.hinext.sales.model.sales_pagedata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CustlocationList implements Serializable {

    @SerializedName("stateId")
    @Expose
    public StateId stateId;
    @SerializedName("companyId")
    @Expose
    public CompanyId companyId;

    @SerializedName("nextRef")
    @Expose
    public Long nextref;
    @SerializedName("inventoryLocationName")
    @Expose
    public String inventoryLocationName;
    @SerializedName("companyIdForLoc")
    @Expose
    public String companyIdForLoc;
    @SerializedName("inventoryLocationContactPerson")
    @Expose
    public String inventoryLocationContactPerson;

    @SerializedName("inventoryLocationId")
    @Expose
    public long inventoryLocationId;
    @SerializedName("inventoryLocationContact")
    @Expose
    public String inventoryLocationContact;
    @SerializedName("inventoryLocationEmail")
    @Expose
    public String inventoryLocationEmail;
    @SerializedName("inventoryLocationStatus")
    @Expose
    public String inventoryLocationStatus;
    @SerializedName("inventoryLocationAddress")
    @Expose
    public String inventoryLocationAddress;
    @SerializedName("gstin")
    @Expose
    public String gstin;
    @SerializedName("customerIdForLoc")
    @Expose
    public String customerIdForLoc;
    @SerializedName("inventoryLocationFaxNo")
    @Expose
    public String inventoryLocationFaxNo;
    @SerializedName("stateName")
    @Expose
    public String stateName;
    public Long stateID;

    public Long getStateID() {
        return stateID;
    }

    public void setStateID(Long stateID) {
        this.stateID = stateID;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

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

    public Long getNextref() {
        return nextref;
    }

    public void setNextref(Long nextref) {
        this.nextref = nextref;
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

    public String getInventoryLocationContactPerson() {
        return inventoryLocationContactPerson;
    }

    public void setInventoryLocationContactPerson(String inventoryLocationContactPerson) {
        this.inventoryLocationContactPerson = inventoryLocationContactPerson;
    }



    public long getInventoryLocationId() {
        return inventoryLocationId;
    }

    public void setInventoryLocationId(long inventoryLocationId) {
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


    public String getInventoryLocationAddress() {
        return inventoryLocationAddress;
    }

    public void setInventoryLocationAddress(String inventoryLocationAddress) {
        this.inventoryLocationAddress = inventoryLocationAddress;
    }

    public String getGstin() {
        return gstin;
    }

    public void setGstin(String gstin) {
        this.gstin = gstin;
    }

    public String getCustomerIdForLoc() {
        return customerIdForLoc;
    }

    public void setCustomerIdForLoc(String customerIdForLoc) {
        this.customerIdForLoc = customerIdForLoc;
    }

    public String getInventoryLocationFaxNo() {
        return inventoryLocationFaxNo;
    }

    public void setInventoryLocationFaxNo(String inventoryLocationFaxNo) {
        this.inventoryLocationFaxNo = inventoryLocationFaxNo;
    }

    @Override
    public String toString() {
        return inventoryLocationName;
    }
}
