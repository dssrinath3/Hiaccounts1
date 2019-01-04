package in.hiaccounts.hinext.checkout.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Admin on 12/12/2017.
 */

public class PaymentType implements Serializable {

    @SerializedName("paymentmethodId")
    @Expose
    public Long paymentmethodId;
    @SerializedName("paymentmethodName")
    @Expose
    public String paymentmethodName;
    @SerializedName("paymentmethodDescription")
    @Expose
    public Object paymentmethodDescription;
    @SerializedName("paymentmethodType")
    @Expose
    public String paymentmethodType;
    @SerializedName("companyId")
    @Expose
    public Object companyId;
    @SerializedName("accountMaster")
    @Expose
    public Object accountMaster;
    @SerializedName("status")
    @Expose
    public Object status;
    @SerializedName("defaultType")
    @Expose
    public String defaultType;
    @SerializedName("accountMasterId")
    @Expose
    public Long accountMasterId;
    @SerializedName("validateVoucher")
    @Expose
    public Object validateVoucher;
    @SerializedName("accountMasterDTOList")
    @Expose
    public Object accountMasterDTOList;
    boolean isChecked;


    public Long getPaymentmethodId() {
        return paymentmethodId;
    }

    public void setPaymentmethodId(Long paymentmethodId) {
        this.paymentmethodId = paymentmethodId;
    }

    public String getPaymentmethodName() {
        return paymentmethodName;
    }

    public void setPaymentmethodName(String paymentmethodName) {
        this.paymentmethodName = paymentmethodName;
    }

    public Object getPaymentmethodDescription() {
        return paymentmethodDescription;
    }

    public void setPaymentmethodDescription(Object paymentmethodDescription) {
        this.paymentmethodDescription = paymentmethodDescription;
    }

    public String getPaymentmethodType() {
        return paymentmethodType;
    }

    public void setPaymentmethodType(String paymentmethodType) {
        this.paymentmethodType = paymentmethodType;
    }

    public Object getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Object companyId) {
        this.companyId = companyId;
    }

    public Object getAccountMaster() {
        return accountMaster;
    }

    public void setAccountMaster(Object accountMaster) {
        this.accountMaster = accountMaster;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public String getDefaultType() {
        return defaultType;
    }

    public void setDefaultType(String defaultType) {
        this.defaultType = defaultType;
    }

    public Long getAccountMasterId() {
        return accountMasterId;
    }

    public void setAccountMasterId(Long accountMasterId) {
        this.accountMasterId = accountMasterId;
    }

    public Object getValidateVoucher() {
        return validateVoucher;
    }

    public void setValidateVoucher(Object validateVoucher) {
        this.validateVoucher = validateVoucher;
    }

    public Object getAccountMasterDTOList() {
        return accountMasterDTOList;
    }

    public void setAccountMasterDTOList(Object accountMasterDTOList) {
        this.accountMasterDTOList = accountMasterDTOList;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
