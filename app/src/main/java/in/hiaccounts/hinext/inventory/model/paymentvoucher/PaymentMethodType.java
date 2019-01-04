package in.hiaccounts.hinext.inventory.model.paymentvoucher;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by shrinath on 12/15/2017.
 */

public class PaymentMethodType implements Serializable {
    @SerializedName("paymentmethodId")
    @Expose
    private Long paymentmethodId;
    @SerializedName("paymentmethodName")
    @Expose
    private String paymentmethodName;
    @SerializedName("paymentmethodDescription")
    @Expose
    private String paymentmethodDescription;
    @SerializedName("paymentmethodType")
    @Expose
    private String paymentmethodType;
    @SerializedName("companyId")
    @Expose
    private Long companyId;
    @SerializedName("accountMaster")
    @Expose
    private String accountMaster;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("defaultType")
    @Expose
    private String defaultType;
    @SerializedName("accountMasterId")
    @Expose
    private Long accountMasterId;
    @SerializedName("validateVoucher")
    @Expose
    private String validateVoucher;
    @SerializedName("accountMasterDTOList")
    @Expose
    private String accountMasterDTOList;

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

    public String getPaymentmethodDescription() {
        return paymentmethodDescription;
    }

    public void setPaymentmethodDescription(String paymentmethodDescription) {
        this.paymentmethodDescription = paymentmethodDescription;
    }

    public String getPaymentmethodType() {
        return paymentmethodType;
    }

    public void setPaymentmethodType(String paymentmethodType) {
        this.paymentmethodType = paymentmethodType;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getAccountMaster() {
        return accountMaster;
    }

    public void setAccountMaster(String accountMaster) {
        this.accountMaster = accountMaster;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public String getValidateVoucher() {
        return validateVoucher;
    }

    public void setValidateVoucher(String validateVoucher) {
        this.validateVoucher = validateVoucher;
    }

    public String getAccountMasterDTOList() {
        return accountMasterDTOList;
    }

    public void setAccountMasterDTOList(String accountMasterDTOList) {
        this.accountMasterDTOList = accountMasterDTOList;
    }

    @Override
    public String toString() {
        return paymentmethodName;
    }
}
