
package in.hiaccounts.model.purchase.hinextpurchase;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Prateek on 05/08/2017.
 */

public class Supplier implements Serializable
{

    @SerializedName("contactName")
    @Expose
    private String contactName;
    @SerializedName("phoneNumber1")
    @Expose
    private String phoneNumber1;
    @SerializedName("generalNote")
    @Expose
    private String generalNote;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("phoneNumber2")
    @Expose
    private String phoneNumber2;
    @SerializedName("fax")
    @Expose
    private String fax;
    @SerializedName("supplierName")
    @Expose
    private String supplierName;
    @SerializedName("supplierId")
    @Expose
    private Long supplierId;
    @SerializedName("supplierNumber")
    @Expose
    private String supplierNumber;
    @SerializedName("supplierEmail")
    @Expose
    private String supplierEmail;
    @SerializedName("billingAddress")
    @Expose
    private String billingAddress;


    public Supplier(){
    }

    public Supplier(String contactName, String phoneNumber1, String generalNote, String website, String phoneNumber2, String fax, String supplierName, Long supplierId, String supplierNumber, String supplierEmail, String billingAddress) {
        this.contactName = contactName;
        this.phoneNumber1 = phoneNumber1;
        this.generalNote = generalNote;
        this.website = website;
        this.phoneNumber2 = phoneNumber2;
        this.fax = fax;
        this.supplierName = supplierName;
        this.supplierId = supplierId;
        this.supplierNumber = supplierNumber;
        this.supplierEmail = supplierEmail;
        this.billingAddress = billingAddress;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhoneNumber1() {
        return phoneNumber1;
    }

    public void setPhoneNumber1(String phoneNumber1) {
        this.phoneNumber1 = phoneNumber1;
    }

    public String getGeneralNote() {
        return generalNote;
    }

    public void setGeneralNote(String generalNote) {
        this.generalNote = generalNote;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
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

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
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
}
