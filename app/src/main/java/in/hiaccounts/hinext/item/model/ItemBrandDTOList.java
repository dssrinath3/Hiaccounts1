package in.hiaccounts.hinext.item.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Prateek on 7/25/2017.
 */

public class ItemBrandDTOList implements Serializable{


    @SerializedName("brandId")
    @Expose
    private Long brandId;
    @SerializedName("brandName")
    @Expose
    private String brandName;
    @SerializedName("brandDescription")
    @Expose
    private String brandDescription;
    @SerializedName("locationId")
    @Expose
    private Long locationId;
    @SerializedName("useraccount_id")
    @Expose
    private Long useraccountId;
    @SerializedName("status")
    @Expose
    private String status;

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandDescription() {
        return brandDescription;
    }

    public void setBrandDescription(String brandDescription) {
        this.brandDescription = brandDescription;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return brandName;
    }
}
