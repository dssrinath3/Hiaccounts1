package in.hiaccounts.hinext.item.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Prateek on 7/25/2017.
 */

public class ItemMSICDTOList implements Serializable {

    @SerializedName("mscid")
    @Expose
    private long mscid;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("description")
    @Expose
    private String description;

    public long getMscid() {
        return mscid;
    }

    public void setMscid(long mscid) {
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


    @Override
    public String toString() {
        return code;
    }
}
