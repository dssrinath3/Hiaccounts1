package in.hiaccounts.hinext.controlpanel.model.configurator_bank.employee;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by administrator on 20/12/17.
 */

public class EmployeeList implements Serializable {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("locationId")
    @Expose
    private Long locationId;
    @SerializedName("useraccount_id")
    @Expose
    private Long useraccountId;
    @SerializedName("effectiveDate")
    @Expose
    private String effectiveDate;
    @SerializedName("status1")
    @Expose
    private String status1;
    @SerializedName("employeeAccountMaster")
    @Expose
    private String employeeAccountMaster;
    @SerializedName("accountCode")
    @Expose
    private String accountCode;
    @SerializedName("employeeId")
    @Expose
    private Long employeeId;
    @SerializedName("employeeDOJ")
    @Expose
    private String employeeDOJ;
    @SerializedName("employeePhone")
    @Expose
    private String employeePhone;
    @SerializedName("employeeCode")
    @Expose
    private String employeeCode;
    @SerializedName("employeeName")
    @Expose
    private String employeeName;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("employeeDOB")
    @Expose
    private String employeeDOB;
    @SerializedName("employeeAddr")
    @Expose
    private String employeeAddr;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Long getUseraccountId() {
        return useraccountId;
    }

    public void setUseraccountId(Long useraccountId) {
        this.useraccountId = useraccountId;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getStatus1() {
        return status1;
    }

    public void setStatus1(String status1) {
        this.status1 = status1;
    }

    public String getEmployeeAccountMaster() {
        return employeeAccountMaster;
    }

    public void setEmployeeAccountMaster(String employeeAccountMaster) {
        this.employeeAccountMaster = employeeAccountMaster;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeDOJ() {
        return employeeDOJ;
    }

    public void setEmployeeDOJ(String employeeDOJ) {
        this.employeeDOJ = employeeDOJ;
    }

    public String getEmployeePhone() {
        return employeePhone;
    }

    public void setEmployeePhone(String employeePhone) {
        this.employeePhone = employeePhone;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmployeeDOB() {
        return employeeDOB;
    }

    public void setEmployeeDOB(String employeeDOB) {
        this.employeeDOB = employeeDOB;
    }

    public String getEmployeeAddr() {
        return employeeAddr;
    }

    public void setEmployeeAddr(String employeeAddr) {
        this.employeeAddr = employeeAddr;
    }
}
