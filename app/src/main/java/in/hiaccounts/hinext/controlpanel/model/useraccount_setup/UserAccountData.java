
package in.hiaccounts.hinext.controlpanel.model.useraccount_setup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserAccountData implements Serializable {

    @SerializedName("useraccount_id")
    @Expose
    public Long useraccountId;
    @SerializedName("userRights")
    @Expose
    public Object userRights;
    @SerializedName("phone")
    @Expose
    public String phone;
    @SerializedName("user_loginId")
    @Expose
    public String userLoginId;
    @SerializedName("currentPassword")
    @Expose
    public String currentPassword;
    @SerializedName("passwordUser")
    @Expose
    public String passwordUser;
    @SerializedName("full_name")
    @Expose
    public String fullName;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("securityQuestion")
    @Expose
    public String securityQuestion;
    @SerializedName("securityAnswer")
    @Expose
    public String securityAnswer;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("companyId")
    @Expose
    public Object companyId;
    @SerializedName("company")
    @Expose
    public Object company;
    @SerializedName("location")
    @Expose
    public Location location;
    @SerializedName("apimessage")
    @Expose
    public String apimessage;
    @SerializedName("userAccessRightsId")
    @Expose
    public Long userAccessRightsId;


    public Long getUserAccessRightsId() {
        return userAccessRightsId;
    }

    public void setUserAccessRightsId(Long userAccessRightsId) {
        this.userAccessRightsId = userAccessRightsId;
    }

    public Long getUseraccountId() {
        return useraccountId;
    }

    public void setUseraccountId(Long useraccountId) {
        this.useraccountId = useraccountId;
    }

    public Object getUserRights() {
        return userRights;
    }

    public void setUserRights(Object userRights) {
        this.userRights = userRights;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserLoginId() {
        return userLoginId;
    }

    public void setUserLoginId(String userLoginId) {
        this.userLoginId = userLoginId;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
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

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Object companyId) {
        this.companyId = companyId;
    }

    public Object getCompany() {
        return company;
    }

    public void setCompany(Object company) {
        this.company = company;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getApimessage() {
        return apimessage;
    }

    public void setApimessage(String apimessage) {
        this.apimessage = apimessage;
    }
}
