package in.hiaccounts.hinext.generaltransaction.model.payto_contact;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Admin on 8/3/2017.
 */

public class Contact implements Serializable {

    @SerializedName("otherContactId")
    @Expose
    Long otherContactId;
    @SerializedName("contactNumber")
    @Expose
    String contactNumber;
    @SerializedName("fullName")
    @Expose
    String fullName;
    @SerializedName("email")
    @Expose
    String email;


    public Long getOtherContactId() {
        return otherContactId;
    }

    public void setOtherContactId(Long otherContactId) {
        this.otherContactId = otherContactId;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
