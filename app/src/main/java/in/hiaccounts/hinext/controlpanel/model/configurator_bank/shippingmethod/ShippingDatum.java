package in.hiaccounts.hinext.controlpanel.model.configurator_bank.shippingmethod;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Prateek on 8/22/2017.
 */

public class ShippingDatum implements Serializable {

    @Expose
    private Long shippingMethodId;
    @SerializedName("shippingMethodName")
    @Expose
    private String shippingMethodName;
    @SerializedName("shippingMethodDescription")
    @Expose
    private String shippingMethodDescription;
    @SerializedName("locationId")
    @Expose
    private Object locationId;
    @SerializedName("useraccount_id")
    @Expose
    private Object useraccountId;
    @SerializedName("status")
    @Expose
    private Object status;

    public Long getShippingMethodId() {
        return shippingMethodId;
    }

    public void setShippingMethodId(Long shippingMethodId) {
        this.shippingMethodId = shippingMethodId;
    }

    public String getShippingMethodName() {
        return shippingMethodName;
    }

    public void setShippingMethodName(String shippingMethodName) {
        this.shippingMethodName = shippingMethodName;
    }

    public String getShippingMethodDescription() {
        return shippingMethodDescription;
    }

    public void setShippingMethodDescription(String shippingMethodDescription) {
        this.shippingMethodDescription = shippingMethodDescription;
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

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }
}
