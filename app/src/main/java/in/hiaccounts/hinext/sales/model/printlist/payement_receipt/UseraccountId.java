
package in.hiaccounts.hinext.sales.model.printlist.payement_receipt;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UseraccountId implements Serializable {

    @SerializedName("useraccount_id")
    @Expose
    public long useraccountId;
    @SerializedName("userRights")
    @Expose
    public UserRights userRights;
    @SerializedName("phone")
    @Expose
    public String phone;
    @SerializedName("user_loginId")
    @Expose
    public String userLoginId;
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
    public CompanyId companyId;
    @SerializedName("company")
    @Expose
    public Company company;
    @SerializedName("location")
    @Expose
    public Location location;

    public long getUseraccountId() {
        return useraccountId;
    }

    public void setUseraccountId(long useraccountId) {
        this.useraccountId = useraccountId;
    }

    public UserRights getUserRights() {
        return userRights;
    }

    public void setUserRights(UserRights userRights) {
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

    public CompanyId getCompanyId() {
        return companyId;
    }

    public void setCompanyId(CompanyId companyId) {
        this.companyId = companyId;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
