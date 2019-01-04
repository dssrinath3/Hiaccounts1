package in.hiaccounts.hinext.inventory.model.hsncode;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Prateek on 8/24/2017.
 */

public class SearchHsnCode implements Serializable {

    @SerializedName("code")
    @Expose
    public String code;
    @SerializedName("description")
    @Expose
    public String description;

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
