package in.hiaccounts.hinext.inventory.model.inventorylocation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Admin on 8/1/2017.
 */

public class InventoryLocation implements Serializable {

    @SerializedName("stateDTOList")
    @Expose
    private List<StateDTOList> stateDTOList = null;
    @SerializedName("inventoryLocationId")
    @Expose
    private Long inventoryLocationId;
    @SerializedName("inventoryLocationName")
    @Expose
    private String inventoryLocationName;
    @SerializedName("inventoryLocationAddress")
    @Expose
    private String inventoryLocationAddress;
    @SerializedName("inventoryLocationContactPerson")
    @Expose
    private String inventoryLocationContactPerson;
    @SerializedName("inventoryLocationContact")
    @Expose
    private String inventoryLocationContact;
    @SerializedName("inventoryLocationFaxNo")
    @Expose
    private String inventoryLocationFaxNo;
    @SerializedName("inventoryLocationEmail")
    @Expose
    private String inventoryLocationEmail;
    @SerializedName("inventoryLocationStatus")
    @Expose
    private String inventoryLocationStatus;
    @SerializedName("gSTIN")
    @Expose
    private String gSTIN;
    @SerializedName("stateName")
    @Expose
    private String stateName;
    @SerializedName("companyIdForLoc")
    @Expose
    private Object companyIdForLoc;
    @SerializedName("supplierIdForLoc")
    @Expose
    private String supplierIdForLoc;
    @SerializedName("customerIdForLoc")
    @Expose
    private String customerIdForLoc;
    @SerializedName("stateIdForLoc")
    @Expose
    private Long stateIdForLoc;
    @SerializedName("prefix")
    @Expose
    private String prefix;
    @SerializedName("status")
    @Expose
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public List<StateDTOList> getStateDTOList() {
        return stateDTOList;
    }

    public void setStateDTOList(List<StateDTOList> stateDTOList) {
        this.stateDTOList = stateDTOList;
    }

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

    public String getInventoryLocationAddress() {
        return inventoryLocationAddress;
    }

    public void setInventoryLocationAddress(String inventoryLocationAddress) {
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

    public String getInventoryLocationFaxNo() {
        return inventoryLocationFaxNo;
    }

    public void setInventoryLocationFaxNo(String inventoryLocationFaxNo) {
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

    public String getgSTIN() {
        return gSTIN;
    }

    public void setgSTIN(String gSTIN) {
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

    public String getSupplierIdForLoc() {
        return supplierIdForLoc;
    }

    public void setSupplierIdForLoc(String supplierIdForLoc) {
        this.supplierIdForLoc = supplierIdForLoc;
    }

    public String getCustomerIdForLoc() {
        return customerIdForLoc;
    }

    public void setCustomerIdForLoc(String customerIdForLoc) {
        this.customerIdForLoc = customerIdForLoc;
    }

    public Long getStateIdForLoc() {
        return stateIdForLoc;
    }

    public void setStateIdForLoc(Long stateIdForLoc) {
        this.stateIdForLoc = stateIdForLoc;
    }
}
