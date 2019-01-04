package in.hiaccounts.hinext.controlpanel.model.configurator_bank.employee;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Prateek on 8/22/2017.
 */

public class EmployeeDatum implements Serializable {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("locationId")
    @Expose
    private Object locationId;
    @SerializedName("useraccount_id")
    @Expose
    private Object useraccountId;
    @SerializedName("effectiveDate")
    @Expose
    private Object effectiveDate;
    @SerializedName("status1")
    @Expose
    private String status1;
    @SerializedName("employeeAddr")
    @Expose
    private String employeeAddr;
    @SerializedName("employeeCode")
    @Expose
    private String employeeCode;
    @SerializedName("employeeDOB")
    @Expose
    private String employeeDOB;
    @SerializedName("employeeDOJ")
    @Expose
    private String employeeDOJ;
    @SerializedName("employeeName")
    @Expose
    private String employeeName;
    @SerializedName("employeePhone")
    @Expose
    private String employeePhone;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("employeeAccountMaster")
    @Expose
    private Object employeeAccountMaster;
    @SerializedName("accountCode")
    @Expose
    private String accountCode;
    @SerializedName("employeeId")
    @Expose
    private Long employeeId;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getLocationId() {
        return locationId;
    }

    public void setLocationId(Object locationId) {
        this.locationId = locationId;
    }

    public Object getUseraccountId() {
        return useraccountId;
    }

    public void setUseraccountId(Object useraccountId) {
        this.useraccountId = useraccountId;
    }

    public Object getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Object effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getStatus1() {
        return status1;
    }

    public void setStatus1(String status1) {
        this.status1 = status1;
    }

    public String getEmployeeAddr() {
        return employeeAddr;
    }

    public void setEmployeeAddr(String employeeAddr) {
        this.employeeAddr = employeeAddr;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getEmployeeDOB() {
        return employeeDOB;
    }

    public void setEmployeeDOB(String employeeDOB) {
        this.employeeDOB = employeeDOB;
    }

    public String getEmployeeDOJ() {
        return employeeDOJ;
    }

    public void setEmployeeDOJ(String employeeDOJ) {
        this.employeeDOJ = employeeDOJ;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeePhone() {
        return employeePhone;
    }

    public void setEmployeePhone(String employeePhone) {
        this.employeePhone = employeePhone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Object getEmployeeAccountMaster() {
        return employeeAccountMaster;
    }

    public void setEmployeeAccountMaster(Object employeeAccountMaster) {
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
}
