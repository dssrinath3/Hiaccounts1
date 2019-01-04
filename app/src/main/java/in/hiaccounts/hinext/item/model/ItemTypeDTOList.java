package in.hiaccounts.hinext.item.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Prateek on 7/25/2017.
 */

public class ItemTypeDTOList implements Serializable {


    @SerializedName("itemTypeId")
    @Expose
    private long itemTypeId;
    @SerializedName("itemTypeName")
    @Expose
    private String itemTypeName;
    @SerializedName("itemTypeDesc")
    @Expose
    private String itemTypeDesc;

    public long getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(long itemTypeId) {
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
