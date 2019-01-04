
package in.hiaccounts.model.item;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemTypeDTOList implements Serializable
{

    @SerializedName("itemTypeName")
    @Expose
    private String itemTypeName;
    @SerializedName("itemTypeId")
    @Expose
    private long itemTypeId;
    @SerializedName("itemTypeDesc")
    @Expose
    private String itemTypeDesc;
    private final static long serialVersionUID = -282146842155713812L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ItemTypeDTOList() {
    }

    /**
     * 
     * @param itemTypeName
     * @param itemTypeDesc
     * @param itemTypeId
     */
    public ItemTypeDTOList(String itemTypeName, long itemTypeId, String itemTypeDesc) {
        super();
        this.itemTypeName = itemTypeName;
        this.itemTypeId = itemTypeId;
        this.itemTypeDesc = itemTypeDesc;
    }

    public String getItemTypeName() {
        return itemTypeName;
    }

    public void setItemTypeName(String itemTypeName) {
        this.itemTypeName = itemTypeName;
    }

    public long getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(long itemTypeId) {
        this.itemTypeId = itemTypeId;
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
