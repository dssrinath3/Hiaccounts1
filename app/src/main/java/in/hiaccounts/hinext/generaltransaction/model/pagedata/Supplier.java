
package in.hiaccounts.hinext.generaltransaction.model.pagedata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Supplier implements Serializable{

    @SerializedName("supplierId")
    @Expose
    private long supplierId;
    @SerializedName("supplierName")
    @Expose
    private String supplierName;
    @SerializedName("supplierNumber")
    @Expose
    private String supplierNumber;
    @SerializedName("supplierEmail")
    @Expose
    private String supplierEmail;
    @SerializedName("billingAddress")
    @Expose
    private String billingAddress;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("phoneNumber1")
    @Expose
    private String phoneNumber1;
    @SerializedName("phoneNumber2")
    @Expose
    private String phoneNumber2;
    @SerializedName("fax")
    @Expose
    private String fax;
    @SerializedName("contactName")
    @Expose
    private String contactName;
    @SerializedName("generalNote")
    @Expose
    private String generalNote;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Supplier() {
    }

    /**
     * 
     * @param generalNote
     * @param fax
     * @param supplierName
     * @param supplierId
     * @param website
     * @param contactName
     * @param phoneNumber2
     * @param phoneNumber1
     * @param billingAddress
     * @param supplierEmail
     * @param supplierNumber
     */
    public Supplier(long supplierId, String supplierName, String supplierNumber, String supplierEmail, String billingAddress, String website, String phoneNumber1, String phoneNumber2, String fax, String contactName, String generalNote) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.supplierNumber = supplierNumber;
        this.supplierEmail = supplierEmail;
        this.billingAddress = billingAddress;
        this.website = website;
        this.phoneNumber1 = phoneNumber1;
        this.phoneNumber2 = phoneNumber2;
        this.fax = fax;
        this.contactName = contactName;
        this.generalNote = generalNote;
    }

    public long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierNumber() {
        return supplierNumber;
    }

    public void setSupplierNumber(String supplierNumber) {
        this.supplierNumber = supplierNumber;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhoneNumber1() {
        return phoneNumber1;
    }

    public void setPhoneNumber1(String phoneNumber1) {
        this.phoneNumber1 = phoneNumber1;
    }

    public String getPhoneNumber2() {
        return phoneNumber2;
    }

    public void setPhoneNumber2(String phoneNumber2) {
        this.phoneNumber2 = phoneNumber2;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getGeneralNote() {
        return generalNote;
    }

    public void setGeneralNote(String generalNote) {
        this.generalNote = generalNote;
    }
}
