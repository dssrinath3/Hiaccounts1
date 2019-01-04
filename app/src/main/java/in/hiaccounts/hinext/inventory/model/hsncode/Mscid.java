
package in.hiaccounts.hinext.inventory.model.hsncode;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Mscid implements Serializable{

    @SerializedName("mscid")
    @Expose
    public Long mscid;
    @SerializedName("code")
    @Expose
    public String code;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("locationId")
    @Expose
    public String locationId;
    @SerializedName("useraccount_id")
    @Expose
    public String useraccountId;
    @SerializedName("status")
    @Expose
    public String status;

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getUseraccountId() {
        return useraccountId;
    }

    public void setUseraccountId(String useraccountId) {
        this.useraccountId = useraccountId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getMscid() {
        return mscid;
    }

    public void setMscid(Long mscid) {
        this.mscid = mscid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
