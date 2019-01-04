package in.hiaccounts.hinext.controlpanel.model.configurator_bank.servicecharge;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by shrinath on 11/28/2017.
 */

public class ServiceChargeData implements Serializable{

    @SerializedName("serviceChargeId")
    @Expose
    private Long serviceChargeId;
    @SerializedName("serviceChargeName")
    @Expose
    private String serviceChargeName;
    @SerializedName("servicePercentage")
    @Expose
    private Double servicePercentage;
    @SerializedName("effectiveDate")
    @Expose
    private String effectiveDate;
    @SerializedName("locationId")
    @Expose
    private Object locationId;
    @SerializedName("useraccount_id")
    @Expose
    private Object useraccountId;

    public Long getServiceChargeId() {
        return serviceChargeId;
    }

    public void setServiceChargeId(Long serviceChargeId) {
        this.serviceChargeId = serviceChargeId;
    }

    public String getServiceChargeName() {
        return serviceChargeName;
    }

    public void setServiceChargeName(String serviceChargeName) {
        this.serviceChargeName = serviceChargeName;
    }

    public Double getServicePercentage() {
        return servicePercentage;
    }

    public void setServicePercentage(Double servicePercentage) {
        this.servicePercentage = servicePercentage;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
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
}
