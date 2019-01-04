
package in.hiaccounts.hinext.purchase.model.purchase_saveresponses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class InventoryLocationData implements Serializable{

  /*  @SerializedName("stateId")
    @Expose
    public StateId stateId;
    @SerializedName("companyId")
    @Expose
    public CompanyId companyId;*/
    @SerializedName("useraccount_id")
    @Expose
    public Object useraccountId;
    @SerializedName("nextref")
    @Expose
    public Object nextref;
    @SerializedName("inventoryLocationContact")
    @Expose
    public String inventoryLocationContact;
    @SerializedName("inventoryLocationId")
    @Expose
    public Long inventoryLocationId;
    @SerializedName("companyIdForLoc")
    @Expose
    public String companyIdForLoc;
    @SerializedName("inventoryLocationAddress")
    @Expose
    public String inventoryLocationAddress;
    @SerializedName("inventoryLocationName")
    @Expose
    public String inventoryLocationName;
    @SerializedName("inventoryLocationEmail")
    @Expose
    public String inventoryLocationEmail;
    @SerializedName("inventoryLocationFaxNo")
    @Expose
    public String inventoryLocationFaxNo;
  /*  @SerializedName("supplierIdForLoc")
    @Expose
    public Long supplierIdForLoc;
    @SerializedName("customerIdForLoc")
    @Expose
    public Long customerIdForLoc;*/
    @SerializedName("inventoryLocationStatus")
    @Expose
    public String inventoryLocationStatus;
    @SerializedName("inventoryLocationContactPerson")
    @Expose
    public String inventoryLocationContactPerson;
    @SerializedName("gstin")
    @Expose
    public String gstin;



   /* public StateId getStateId() {
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
    }*/

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

    public String getInventoryLocationContact() {
        return inventoryLocationContact;
    }

    public void setInventoryLocationContact(String inventoryLocationContact) {
        this.inventoryLocationContact = inventoryLocationContact;
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

    public String getInventoryLocationAddress() {
        return inventoryLocationAddress;
    }

    public void setInventoryLocationAddress(String inventoryLocationAddress) {
        this.inventoryLocationAddress = inventoryLocationAddress;
    }

    public String getInventoryLocationName() {
        return inventoryLocationName;
    }

    public void setInventoryLocationName(String inventoryLocationName) {
        this.inventoryLocationName = inventoryLocationName;
    }

    public String getInventoryLocationEmail() {
        return inventoryLocationEmail;
    }

    public void setInventoryLocationEmail(String inventoryLocationEmail) {
        this.inventoryLocationEmail = inventoryLocationEmail;
    }

    public String getInventoryLocationFaxNo() {
        return inventoryLocationFaxNo;
    }

    public void setInventoryLocationFaxNo(String inventoryLocationFaxNo) {
        this.inventoryLocationFaxNo = inventoryLocationFaxNo;
    }

/*
    public Long getSupplierIdForLoc() {
        return supplierIdForLoc;
    }

    public void setSupplierIdForLoc(Long supplierIdForLoc) {
        this.supplierIdForLoc = supplierIdForLoc;
    }

    public Long getCustomerIdForLoc() {
        return customerIdForLoc;
    }

    public void setCustomerIdForLoc(Long customerIdForLoc) {
        this.customerIdForLoc = customerIdForLoc;
    }
*/

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

    public String getGstin() {
        return gstin;
    }

    public void setGstin(String gstin) {
        this.gstin = gstin;
    }
}
