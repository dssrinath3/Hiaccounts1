package in.hiaccounts.hinext.inventory.model.assests;

/**
 * Created by shrinath on 11/29/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemTypeDTOList {

    @SerializedName("itemTypeId")
    @Expose
    private Long itemTypeId;
    @SerializedName("itemTypeName")
    @Expose
    private String itemTypeName;
    @SerializedName("itemTypeDesc")
    @Expose
    private String itemTypeDesc;


    public Long getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(Long itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    public String getItemTypeName() {
        return itemTypeName;
    }

    public void setItemTypeName(String itemTypeName) {
        this.itemTypeName = itemTypeName;
    }

    public String getItemTypeDesc() {
        return itemTypeDesc;
    }

    public void setItemTypeDesc(String itemTypeDesc) {
        this.itemTypeDesc = itemTypeDesc;
    }
    @Override
    public String toString() {
        return itemTypeName;
    }
}