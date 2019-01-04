
package in.hiaccounts.hinext.sales.model.save_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class InventoryLocationData implements Serializable {

    @SerializedName("stateId")
    @Expose
    private State stateId;
    @SerializedName("companyId")
    @Expose
    private CompanyId companyId;
    @SerializedName("useraccount_id")
    @Expose
    private Object useraccountId;
    @SerializedName("gstin")
    @Expose
    private String gstin;
    @SerializedName("inventoryLocationId")
    @Expose
    private Long inventoryLocationId;
    @SerializedName("inventoryLocationEmail")
    @Expose
    private String inventoryLocationEmail;
    @SerializedName("inventoryLocationAddress")
    @Expose
    private String inventoryLocationAddress;
    @SerializedName("inventoryLocationContact")
    @Expose
    private String inventoryLocationContact;
    @SerializedName("inventoryLocationName")
    @Expose
    private String inventoryLocationName;
    @SerializedName("inventoryLocationFaxNo")
    @Expose
    private Object inventoryLocationFaxNo;
    @SerializedName("customerIdForLoc")
    @Expose
    private Object customerIdForLoc;
    @SerializedName("companyIdForLoc")
    @Expose
    private Object companyIdForLoc;
    @SerializedName("supplierIdForLoc")
    @Expose
    private Object supplierIdForLoc;
    @SerializedName("inventoryLocationStatus")
    @Expose
    private String inventoryLocationStatus;
    @SerializedName("inventoryLocationContactPerson")
    @Expose
    private String inventoryLocationContactPerson;


    public State getStateId() {
        return stateId;
    }

    public void setStateId(State stateId) {
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

    public String getGstin() {
        return gstin;
    }

    public void setGstin(String gstin) {
        this.gstin = gstin;
    }

    public Long getInventoryLocationId() {
        return inventoryLocationId;
    }

    public void setInventoryLocationId(Long inventoryLocationId) {
        this.inventoryLocationId = inventoryLocationId;
    }

    public String getInventoryLocationEmail() {
        return inventoryLocationEmail;
    }

    public void setInventoryLocationEmail(String inventoryLocationEmail) {
        this.inventoryLocationEmail = inventoryLocationEmail;
    }

    public String getInventoryLocationAddress() {
        return inventoryLocationAddress;
    }

    public void setInventoryLocationAddress(String inventoryLocationAddress) {
        this.inventoryLocationAddress = inventoryLocationAddress;
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

    public Object getInventoryLocationFaxNo() {
        return inventoryLocationFaxNo;
    }

    public void setInventoryLocationFaxNo(Object inventoryLocationFaxNo) {
        this.inventoryLocationFaxNo = inventoryLocationFaxNo;
    }

    public Object getCustomerIdForLoc() {
        return customerIdForLoc;
    }

    public void setCustomerIdForLoc(Object customerIdForLoc) {
        this.customerIdForLoc = customerIdForLoc;
    }

    public Object getCompanyIdForLoc() {
        return companyIdForLoc;
    }

    public void setCompanyIdForLoc(Object companyIdForLoc) {
        this.companyIdForLoc = companyIdForLoc;
    }

    public Object getSupplierIdForLoc() {
        return supplierIdForLoc;
    }

    public void setSupplierIdForLoc(Object supplierIdForLoc) {
        this.supplierIdForLoc = supplierIdForLoc;
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

}
