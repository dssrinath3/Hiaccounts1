
package in.hiaccounts.model.item;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemMSICDTOList implements Serializable
{

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("mscid")
    @Expose
    private long mscid;
    private final static long serialVersionUID = 1069243055994902722L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ItemMSICDTOList() {
    }

    /**
     * 
     * @param mscid
     * @param description
     * @param code
     */
    public ItemMSICDTOList(String description, String code, long mscid) {
        super();
        this.description = description;
        this.code = code;
        this.mscid = mscid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getMscid() {
        return mscid;
    }

    public void setMscid(long mscid) {
        this.mscid = mscid;
    }


    @Override
    public String toString() {
        return description;
    }
}
