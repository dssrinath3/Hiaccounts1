
package in.hiaccounts.model.item;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemBrandDTOList implements Serializable
{

    @SerializedName("brandId")
    @Expose
    private long brandId;
    @SerializedName("brandName")
    @Expose
    private String brandName;
    @SerializedName("locationId")
    @Expose
    private long locationId;
    @SerializedName("useraccount_id")
    @Expose
    private long useraccountId;
    @SerializedName("brandDescription")
    @Expose
    private String brandDescription;
    private final static long serialVersionUID = -1944246318727214276L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ItemBrandDTOList() {
    }

    /**
     * 
     * @param brandDescription
     * @param locationId
     * @param useraccountId
     * @param brandName
     * @param brandId
     */
    public ItemBrandDTOList(long brandId, String brandName, long locationId, long useraccountId, String brandDescription) {
        super();
        this.brandId = brandId;
        this.brandName = brandName;
        this.locationId = locationId;
        this.useraccountId = useraccountId;
        this.brandDescription = brandDescription;
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

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public long getUseraccountId() {
        return useraccountId;
    }

    public void setUseraccountId(long useraccountId) {
        this.useraccountId = useraccountId;
    }

    public String getBrandDescription() {
        return brandDescription;
    }

    public void setBrandDescription(String brandDescription) {
        this.brandDescription = brandDescription;
    }

    @Override
    public String toString() {
        return brandName;
    }


}
