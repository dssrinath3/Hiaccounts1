package in.hiaccounts.hinext.checkout.model.sales;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InventoryLocationData {
    @SerializedName("inventoryLocationId")
    @Expose
    public Long inventoryLocationId;
    @SerializedName("inventoryLocationName")
    @Expose
    public String inventoryLocationName;
    @SerializedName("inventoryLocationAddress")
    @Expose
    public Object inventoryLocationAddress;
    @SerializedName("inventoryLocationContactPerson")
    @Expose
    public String inventoryLocationContactPerson;
    @SerializedName("inventoryLocationContact")
    @Expose
    public String inventoryLocationContact;
    @SerializedName("inventoryLocationFaxNo")
    @Expose
    public Object inventoryLocationFaxNo;
    @SerializedName("inventoryLocationEmail")
    @Expose
    public String inventoryLocationEmail;
    @SerializedName("inventoryLocationStatus")
    @Expose
    public String inventoryLocationStatus;
    @SerializedName("gSTIN")
    @Expose
    public Object gSTIN;
    @SerializedName("stateName")
    @Expose
    public String stateName;
    @SerializedName("companyIdForLoc")
    @Expose
    public Object companyIdForLoc;
    @SerializedName("supplierIdForLoc")
    @Expose
    public Object supplierIdForLoc;
    @SerializedName("customerIdForLoc")
    @Expose
    public Object customerIdForLoc;
    @SerializedName("stateIdForLoc")
    @Expose
    public Long stateIdForLoc;
    @SerializedName("nextRef")
    @Expose
    public Long nextRef;
    @SerializedName("prefix")
    @Expose
    public String prefix;
    @SerializedName("cmpyCountry")
    @Expose
    public Object cmpyCountry;
    @SerializedName("status")
    @Expose
    public Object status;
    @SerializedName("stateDTOList")
    @Expose
    public Object stateDTOList;

    public Long getInventoryLocationId() {
        return inventoryLocationId;
    }

    public void setInventoryLocationId(Long inventoryLocationId) {
        this.inventoryLocationId = inventoryLocationId;
    }

    public String getInventoryLocationName() {
        return inventoryLocationName;
    }

    public void setInventoryLocationName(String inventoryLocationName) {
        this.inventoryLocationName = inventoryLocationName;
    }

    public Object getInventoryLocationAddress() {
        return inventoryLocationAddress;
    }

    public void setInventoryLocationAddress(Object inventoryLocationAddress) {
        this.inventoryLocationAddress = inventoryLocationAddress;
    }

    public String getInventoryLocationContactPerson() {
        return inventoryLocationContactPerson;
    }

    public void setInventoryLocationContactPerson(String inventoryLocationContactPerson) {
        this.inventoryLocationContactPerson = inventoryLocationContactPerson;
    }

    public String getInventoryLocationContact() {
        return inventoryLocationContact;
    }

    public void setInventoryLocationContact(String inventoryLocationContact) {
        this.inventoryLocationContact = inventoryLocationContact;
    }

    public Object getInventoryLocationFaxNo() {
        return inventoryLocationFaxNo;
    }

    public void setInventoryLocationFaxNo(Object inventoryLocationFaxNo) {
        this.inventoryLocationFaxNo = inventoryLocationFaxNo;
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

    public Object getgSTIN() {
        return gSTIN;
    }

    public void setgSTIN(Object gSTIN) {
        this.gSTIN = gSTIN;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
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

    public Object getCustomerIdForLoc() {
        return customerIdForLoc;
    }

    public void setCustomerIdForLoc(Object customerIdForLoc) {
        this.customerIdForLoc = customerIdForLoc;
    }

    public Long getStateIdForLoc() {
        return stateIdForLoc;
    }

    public void setStateIdForLoc(Long stateIdForLoc) {
        this.stateIdForLoc = stateIdForLoc;
    }

    public Long getNextRef() {
        return nextRef;
    }

    public void setNextRef(Long nextRef) {
        this.nextRef = nextRef;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Object getCmpyCountry() {
        return cmpyCountry;
    }

    public void setCmpyCountry(Object cmpyCountry) {
        this.cmpyCountry = cmpyCountry;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public Object getStateDTOList() {
        return stateDTOList;
    }

    public void setStateDTOList(Object stateDTOList) {
        this.stateDTOList = stateDTOList;
    }
}
