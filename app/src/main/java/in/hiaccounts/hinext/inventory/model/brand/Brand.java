package in.hiaccounts.hinext.inventory.model.brand;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Prateek on 7/8/2017.
 */

public class Brand implements Serializable{
    @SerializedName("brandId")
    @Expose
    private long brandId;
    @SerializedName("brandName")
    @Expose
    private String brandName;
    @SerializedName("brandDescription")
    @Expose
    private String brandDescription;

    @SerializedName("status")
    @Expose
    private String status;
    public Brand(){

    }

    public Brand(long brandId, String brandName, String brandDescription) {
        this.brandId = brandId;
        this.brandName = brandName;
        this.brandDescription = brandDescription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getBrandId() {
        return brandId;
    }

    public void setBrandId(long brandId) {
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
}
