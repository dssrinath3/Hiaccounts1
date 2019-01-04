package in.hiaccounts.hinext.controlpanel.model.configurator_bank.agent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Prateek on 8/21/2017.
 */

public class AgentDatum implements Serializable{

    @SerializedName("agentAccountCode")
    @Expose
    private Object agentAccountCode;
    @SerializedName("effectiveDate")
    @Expose
    private String effectiveDate;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("commissionExpense")
    @Expose
    private Object commissionExpense;
    @SerializedName("commissionPay")
    @Expose
    private Object commissionPay;
    @SerializedName("gstinNo")
    @Expose
    private String gstinNo;
    @SerializedName("ecommerce")
    @Expose
    private String ecommerce;
    @SerializedName("commissionExpenseList")
    @Expose
    private Object commissionExpenseList;
    @SerializedName("commissionPayList")
    @Expose
    private Object commissionPayList;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("accountNo")
    @Expose
    private String accountNo;
    @SerializedName("agentId")
    @Expose
    private Long agentId;
    @SerializedName("agentName")
    @Expose
    private String agentName;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("commission")
    @Expose
    private double commission;

    public Object getAgentAccountCode() {
        return agentAccountCode;
    }

    public void setAgentAccountCode(Object agentAccountCode) {
        this.agentAccountCode = agentAccountCode;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getCommissionExpense() {
        return commissionExpense;
    }

    public void setCommissionExpense(Object commissionExpense) {
        this.commissionExpense = commissionExpense;
    }

    public Object getCommissionPay() {
        return commissionPay;
    }

    public void setCommissionPay(Object commissionPay) {
        this.commissionPay = commissionPay;
    }

    public String getGstinNo() {
        return gstinNo;
    }

    public void setGstinNo(String gstinNo) {
        this.gstinNo = gstinNo;
    }

    public String getEcommerce() {
        return ecommerce;
    }

    public void setEcommerce(String ecommerce) {
        this.ecommerce = ecommerce;
    }

    public Object getCommissionExpenseList() {
        return commissionExpenseList;
    }

    public void setCommissionExpenseList(Object commissionExpenseList) {
        this.commissionExpenseList = commissionExpenseList;
    }

    public Object getCommissionPayList() {
        return commissionPayList;
    }

    public void setCommissionPayList(Object commissionPayList) {
        this.commissionPayList = commissionPayList;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }
}
